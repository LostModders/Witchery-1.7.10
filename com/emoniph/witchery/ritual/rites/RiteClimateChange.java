/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.play.server.S26PacketMapChunkBulk;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ 
/*     */ public class RiteClimateChange extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   protected final int radius;
/*     */   
/*     */   public static enum WeatherChange
/*     */   {
/*  40 */     NONE,  SUN,  RAIN,  THUNDER;
/*     */     
/*     */     private WeatherChange() {} }
/*     */   
/*  44 */   public RiteClimateChange(int radius) { this.radius = radius; }
/*     */   
/*     */ 
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  49 */     steps.add(new StepClimateChange(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepClimateChange extends RitualStep
/*     */   {
/*     */     private final RiteClimateChange rite;
/*  55 */     private int stage = 0;
/*     */     private boolean activated;
/*     */     
/*     */     public StepClimateChange(RiteClimateChange rite, int initialStage) {
/*  59 */       super();
/*  60 */       this.rite = rite;
/*  61 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  66 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     private static class ChunkCoord {
/*     */       public final int X;
/*     */       public final int Z;
/*     */       
/*     */       public ChunkCoord(int x, int z) {
/*  74 */         this.X = x;
/*  75 */         this.Z = z;
/*     */       }
/*     */       
/*     */       public boolean equals(Object obj)
/*     */       {
/*  80 */         if (obj == this) {
/*  81 */           return true;
/*     */         }
/*     */         
/*  84 */         if ((obj == null) || (obj.getClass() != getClass())) {
/*  85 */           return false;
/*     */         }
/*     */         
/*  88 */         ChunkCoord other = (ChunkCoord)obj;
/*  89 */         return (this.X == other.X) && (this.Z == other.Z);
/*     */       }
/*     */       
/*     */       public int hashCode()
/*     */       {
/*  94 */         int result = this.X ^ this.X >>> 32;
/*  95 */         result = 31 * result + (this.Z ^ this.Z >>> 32);
/*  96 */         return result;
/*     */       }
/*     */       
/*     */       public Chunk getChunk(World world) {
/* 100 */         return world.func_72964_e(this.X, this.Z);
/*     */       }
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/* 106 */       if (!this.activated) {
/* 107 */         if (ticks % 20L != 0L) {
/* 108 */           return RitualStep.Result.STARTING;
/*     */         }
/* 110 */         this.activated = true;
/* 111 */         SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
/*     */       }
/*     */       
/*     */ 
/* 115 */       if (!world.field_72995_K)
/*     */       {
/*     */ 
/* 118 */         net.minecraft.entity.player.EntityPlayer player = ritual.getInitiatingPlayer(world);
/*     */         
/* 120 */         if (!Config.instance().allowBiomeChanging) {
/* 121 */           SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/* 122 */           if (player != null) {
/* 123 */             ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.disabled", new Object[0]);
/*     */           }
/* 125 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/* 128 */         BiomeGenBase biome = world.func_72807_a(posX, posZ);
/* 129 */         if ((world.field_73011_w.field_76574_g == 1) || (world.field_73011_w.field_76574_g == -1) || (biome == BiomeGenBase.field_76779_k) || (biome == BiomeGenBase.field_76778_j)) {
/* 130 */           SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/* 131 */           if (player != null) {
/* 132 */             ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.wrongdimension", new Object[0]);
/*     */           }
/* 134 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/* 137 */         if (ritual.covenSize < 4) {
/* 138 */           SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/* 139 */           if (player != null) {
/* 140 */             ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
/*     */           }
/* 142 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/* 145 */         if (ticks % 20L == 0L) {
/* 146 */           this.stage += 1;
/*     */           
/* 148 */           if (this.stage < 5) {
/* 149 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, this.stage * 1.5F, this.stage * 1.1F, 16);
/*     */           }
/* 151 */           else if (this.stage == 5) {
/* 152 */             ParticleEffect.HUGE_EXPLOSION.send(SoundEffect.NONE, world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, this.stage * 2.0F, this.stage * 1.5F, 16);
/*     */             
/* 154 */             double RADIUS = 8.0D;
/* 155 */             java.util.List items = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(posX - 8.0D, posY - 2, posZ - 8.0D, posX + 8.0D, posY + 2, posZ + 8.0D));
/*     */             
/*     */ 
/* 158 */             BiomeDictionary.Type biomeType = BiomeDictionary.Type.END;
/* 159 */             RiteClimateChange.WeatherChange weather = RiteClimateChange.WeatherChange.NONE;
/* 160 */             int glowstone = 0;
/* 161 */             for (Object obj : items) {
/* 162 */               EntityItem item = (EntityItem)obj;
/* 163 */               ItemStack stack = item.func_92059_d();
/* 164 */               if (stack.func_77969_a(new ItemStack(Blocks.field_150345_g, 1, 0))) {
/* 165 */                 biomeType = BiomeDictionary.Type.FOREST;
/* 166 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150329_H, 1, 1))) {
/* 167 */                 biomeType = BiomeDictionary.Type.PLAINS;
/* 168 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150343_Z))) {
/* 169 */                 biomeType = BiomeDictionary.Type.MOUNTAIN;
/* 170 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150348_b))) {
/* 171 */                 biomeType = BiomeDictionary.Type.HILLS;
/* 172 */               } else if (stack.func_77969_a(new ItemStack(Items.field_151123_aH))) {
/* 173 */                 biomeType = BiomeDictionary.Type.SWAMP;
/* 174 */               } else if (stack.func_77969_a(new ItemStack(Items.field_151131_as))) {
/* 175 */                 biomeType = BiomeDictionary.Type.WATER;
/* 176 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150434_aF))) {
/* 177 */                 biomeType = BiomeDictionary.Type.DESERT;
/* 178 */                 weather = RiteClimateChange.WeatherChange.SUN;
/* 179 */               } else if (stack.func_77969_a(Witchery.Items.GENERIC.itemIcyNeedle.createStack())) {
/* 180 */                 biomeType = BiomeDictionary.Type.FROZEN;
/* 181 */                 weather = RiteClimateChange.WeatherChange.RAIN;
/* 182 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150345_g, 1, 3))) {
/* 183 */                 biomeType = BiomeDictionary.Type.JUNGLE;
/* 184 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150424_aL))) {
/* 185 */                 biomeType = BiomeDictionary.Type.WASTELAND;
/* 186 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150354_m))) {
/* 187 */                 biomeType = BiomeDictionary.Type.BEACH;
/* 188 */               } else if (stack.func_77969_a(new ItemStack(Blocks.field_150337_Q))) {
/* 189 */                 biomeType = BiomeDictionary.Type.MUSHROOM;
/* 190 */               } else if (stack.func_77969_a(new ItemStack(Items.field_151144_bL))) {
/* 191 */                 biomeType = BiomeDictionary.Type.MAGICAL;
/* 192 */               } else { if (stack.func_77973_b() != Items.field_151114_aO) continue;
/* 193 */                 glowstone += stack.field_77994_a;
/*     */               }
/*     */               
/*     */ 
/* 197 */               world.func_72900_e(item);
/* 198 */               ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, item, 0.5D, 1.0D, 16);
/*     */             }
/*     */             
/* 201 */             if (biomeType == BiomeDictionary.Type.END) {
/* 202 */               SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/*     */               
/* 204 */               if (player != null) {
/* 205 */                 ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.missingbiomefoci", new Object[0]);
/*     */               }
/* 207 */               return RitualStep.Result.ABORTED_REFUND;
/*     */             }
/*     */             
/* 210 */             BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(biomeType);
/* 211 */             if ((biomes == null) || (biomes.length == 0)) {
/* 212 */               SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/*     */               
/* 214 */               if (player != null) {
/* 215 */                 ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "witchery.rite.missingbiomefoci", new Object[0]);
/*     */               }
/* 217 */               return RitualStep.Result.ABORTED_REFUND;
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 222 */             int biomeID = biomes[0].field_76756_M;
/*     */             
/* 224 */             int maxRadius = this.rite.radius * (ritual.covenSize - 3);
/*     */             
/* 226 */             HashMap<ChunkCoord, byte[]> chunkMap = new HashMap();
/*     */             
/* 228 */             drawFilledCircle(world, posX, posZ, maxRadius, chunkMap, weather, biomeID);
/*     */             
/* 230 */             ArrayList chunks = new ArrayList();
/*     */             
/* 232 */             for (Map.Entry<ChunkCoord, byte[]> entry : chunkMap.entrySet()) {
/* 233 */               Chunk chunk = ((ChunkCoord)entry.getKey()).getChunk(world);
/* 234 */               chunk.func_76616_a((byte[])entry.getValue());
/*     */               
/*     */ 
/* 237 */               chunks.add(chunk);
/*     */             }
/*     */             
/*     */ 
/* 241 */             S26PacketMapChunkBulk packet = new S26PacketMapChunkBulk(chunks);
/* 242 */             Witchery.packetPipeline.sendToDimension(packet, world);
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 251 */             for (Object obj : chunks) {
/* 252 */               Chunk chunk = (Chunk)obj;
/* 253 */               for (Object tileObj : chunk.field_150816_i.values()) {
/* 254 */                 TileEntity tile = (TileEntity)tileObj;
/* 255 */                 net.minecraft.network.Packet packet2 = tile.func_145844_m();
/* 256 */                 if (packet2 != null) {
/* 257 */                   world.func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */ 
/* 263 */             if ((world instanceof WorldServer))
/*     */             {
/* 265 */               WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
/* 266 */               int i = (300 + world.field_73012_v.nextInt(600)) * 20;
/* 267 */               switch (RiteClimateChange.1.$SwitchMap$com$emoniph$witchery$ritual$rites$RiteClimateChange$WeatherChange[weather.ordinal()]) {
/*     */               case 1: 
/* 269 */                 if ((world.func_72896_J()) || (world.func_72911_I())) {
/* 270 */                   worldinfo.func_76080_g(0);
/* 271 */                   worldinfo.func_76090_f(0);
/* 272 */                   worldinfo.func_76084_b(false);
/* 273 */                   worldinfo.func_76069_a(false);
/*     */                 }
/*     */                 break;
/*     */               case 2: 
/* 277 */                 if ((!world.func_72896_J()) && (!world.func_72911_I())) {
/* 278 */                   worldinfo.func_76080_g(i);
/* 279 */                   worldinfo.func_76090_f(i);
/* 280 */                   worldinfo.func_76084_b(true);
/* 281 */                   worldinfo.func_76069_a(false);
/*     */                 }
/*     */                 break;
/*     */               case 3: 
/* 285 */                 if (!world.func_72911_I()) {
/* 286 */                   worldinfo.func_76080_g(i);
/* 287 */                   worldinfo.func_76090_f(i);
/* 288 */                   worldinfo.func_76084_b(true);
/* 289 */                   worldinfo.func_76069_a(true);
/*     */                 }
/*     */                 
/*     */                 break;
/*     */               }
/*     */               
/*     */             }
/*     */             
/* 297 */             return RitualStep.Result.COMPLETED;
/*     */           }
/*     */           
/* 300 */           return RitualStep.Result.UPKEEP;
/*     */         }
/* 302 */         return RitualStep.Result.UPKEEP;
/*     */       }
/*     */       
/* 305 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     private static byte[] rotateMatrix(byte[] matrix, int n) {
/* 309 */       byte[] ret = new byte[matrix.length];
/*     */       
/* 311 */       for (int i = 0; i < matrix.length / n; i++) {
/* 312 */         for (int j = 0; j < n; j++) {
/* 313 */           ret[(j * n + i)] = matrix[(i * n + n - j)];
/*     */         }
/*     */       }
/*     */       
/* 317 */       return ret;
/*     */     }
/*     */     
/*     */     protected void drawFilledCircle(World world, int x0, int z0, int radius, HashMap<ChunkCoord, byte[]> chunkMap, RiteClimateChange.WeatherChange weather, int biomeID) {
/* 321 */       int x = radius;
/* 322 */       int z = 0;
/* 323 */       int radiusError = 1 - x;
/*     */       
/* 325 */       while (x >= z) {
/* 326 */         drawLine(world, -x + x0, x + x0, z + z0, chunkMap, weather, biomeID);
/* 327 */         drawLine(world, -z + x0, z + x0, x + z0, chunkMap, weather, biomeID);
/* 328 */         drawLine(world, -x + x0, x + x0, -z + z0, chunkMap, weather, biomeID);
/* 329 */         drawLine(world, -z + x0, z + x0, -x + z0, chunkMap, weather, biomeID);
/*     */         
/* 331 */         z++;
/*     */         
/* 333 */         if (radiusError < 0) {
/* 334 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 336 */           x--;
/* 337 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawLine(World world, int x1, int x2, int z, HashMap<ChunkCoord, byte[]> chunkMap, RiteClimateChange.WeatherChange weather, int biomeID) {
/* 343 */       for (int x = x1; x <= x2; x++) {
/* 344 */         ChunkCoord coord = new ChunkCoord(x >> 4, z >> 4);
/* 345 */         byte[] map = (byte[])chunkMap.get(coord);
/* 346 */         if (map == null) {
/* 347 */           Chunk chunk = world.func_72938_d(x, z);
/* 348 */           map = (byte[])chunk.func_76605_m().clone();
/* 349 */           chunkMap.put(coord, map);
/*     */         }
/*     */         
/* 352 */         map[((z & 0xF) << 4 | x & 0xF)] = ((byte)biomeID);
/*     */         
/* 354 */         if (weather == RiteClimateChange.WeatherChange.SUN) {
/* 355 */           int y = world.func_72825_h(x, z);
/* 356 */           if (world.func_147439_a(x, y, z) == Blocks.field_150433_aE) {
/* 357 */             world.func_147468_f(x, y, z);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteClimateChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */