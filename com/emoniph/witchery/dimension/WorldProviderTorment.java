/*     */ package com.emoniph.witchery.dimension;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ public class WorldProviderTorment extends WorldProvider
/*     */ {
/*     */   public static final String SPIRIT_WORLD_TORMENT_PLAYER_KEY = "WITCForceTorment";
/*     */   public static final String SPIRIT_WORLD_TORMENT_LEVEL_KEY = "WITCForceTormentLevel";
/*     */   public static final int TORMENT_NONE = 0;
/*     */   public static final int TORMENT_BEGIN = 1;
/*     */   public static final int TORMENT_BEGIN_WITH_BOSS = 2;
/*     */   public static final int TORMENT_END = 3;
/*     */   
/*     */   public String func_80007_l()
/*     */   {
/*  35 */     return "Torment";
/*     */   }
/*     */   
/*     */   public IChunkProvider func_76555_c()
/*     */   {
/*  40 */     return new WorldChunkManagerTorment(this.field_76579_a);
/*     */   }
/*     */   
/*     */   public boolean func_76567_e()
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_76569_d()
/*     */   {
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canDoLightning(Chunk chunk)
/*     */   {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isBlockHighHumidity(int x, int y, int z)
/*     */   {
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isDaytime()
/*     */   {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public ChunkCoordinates getSpawnPoint()
/*     */   {
/*  70 */     return new ChunkCoordinates(8, 14, 8);
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public boolean func_76561_g()
/*     */   {
/*  76 */     return true;
/*     */   }
/*     */   
/*     */   public float func_76563_a(long par1, float par3)
/*     */   {
/*  81 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
/*     */   {
/*  89 */     float f1 = this.field_76579_a.func_72826_c(partialTicks);
/*  90 */     float f2 = MathHelper.func_76134_b(f1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*     */     
/*  92 */     if (f2 < 0.0F)
/*     */     {
/*  94 */       f2 = 0.0F;
/*     */     }
/*     */     
/*  97 */     if (f2 > 1.0F)
/*     */     {
/*  99 */       f2 = 1.0F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 104 */     int i = MathHelper.func_76128_c(cameraEntity.field_70165_t);
/* 105 */     int j = MathHelper.func_76128_c(cameraEntity.field_70163_u);
/* 106 */     int k = MathHelper.func_76128_c(cameraEntity.field_70161_v);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 116 */     GameSettings settings = Minecraft.func_71410_x().field_71474_y;
/* 117 */     int[] ranges = net.minecraftforge.common.ForgeModContainer.blendRanges;
/* 118 */     int distance = 0;
/* 119 */     if ((settings.field_74347_j) && (settings.field_151451_c >= 0) && (settings.field_151451_c < ranges.length))
/*     */     {
/* 121 */       distance = ranges[settings.field_151451_c];
/*     */     }
/*     */     
/* 124 */     int r = 0;
/* 125 */     int g = 0;
/* 126 */     int b = 0;
/*     */     
/* 128 */     int divider = 0;
/* 129 */     for (int x = -distance; x <= distance; x++)
/*     */     {
/* 131 */       for (int z = -distance; z <= distance; z++)
/*     */       {
/* 133 */         net.minecraft.world.biome.BiomeGenBase biome = this.field_76579_a.func_72807_a(i + x, k + z);
/*     */         
/* 135 */         int colour = 16711680;
/* 136 */         r += ((colour & 0xFF0000) >> 16);
/* 137 */         g += ((colour & 0xFF00) >> 8);
/* 138 */         b += (colour & 0xFF);
/* 139 */         divider++;
/*     */       }
/*     */     }
/*     */     
/* 143 */     int multiplier = (r / divider & 0xFF) << 16 | (g / divider & 0xFF) << 8 | b / divider & 0xFF;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 150 */     int l = multiplier;
/*     */     
/* 152 */     float f4 = (l >> 16 & 0xFF) / 255.0F;
/* 153 */     float f5 = (l >> 8 & 0xFF) / 255.0F;
/* 154 */     float f6 = (l & 0xFF) / 255.0F;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 159 */     return Vec3.func_72443_a(f4, f5, f6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setPlayerMustTorment(EntityPlayer player, int torment, int presetLevel)
/*     */   {
/* 172 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 173 */     setPlayerMustTorment(nbtPlayer, torment, presetLevel);
/*     */   }
/*     */   
/*     */   public static void setPlayerMustTorment(NBTTagCompound nbtPlayer, int torment, int presetLevel)
/*     */   {
/* 178 */     nbtPlayer.func_74768_a("WITCForceTorment", torment);
/* 179 */     if (presetLevel > -1) {
/* 180 */       nbtPlayer.func_74768_a("WITCForceTormentLevel", presetLevel);
/* 181 */     } else if ((presetLevel == -2) && (nbtPlayer.func_74764_b("WITCForceTormentLevel"))) {
/* 182 */       nbtPlayer.func_82580_o("WITCForceTormentLevel");
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getRandomTormentLevel(World world) {
/* 187 */     return world.field_73012_v.nextInt(6);
/*     */   }
/*     */   
/*     */   public static int getPlayerMustTorment(EntityPlayer player) {
/* 191 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 192 */     return getPlayerMustTorment(nbtPlayer);
/*     */   }
/*     */   
/*     */   public static int getPlayerMustTorment(NBTTagCompound nbtPlayer) {
/* 196 */     return nbtPlayer.func_74762_e("WITCForceTorment");
/*     */   }
/*     */   
/*     */   public static void updatePlayerEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, long time, long counter) {
/* 200 */     if (!world.field_72995_K) {
/* 201 */       boolean done = false;
/* 202 */       if (counter % 20L == 0L) {
/* 203 */         int mustTorment = getPlayerMustTorment(nbtPlayer);
/* 204 */         if ((mustTorment == 1) || (mustTorment == 2)) {
/* 205 */           int level = mustTorment == 2 ? nbtPlayer.func_74762_e("WITCForceTormentLevel") : getRandomTormentLevel(world);
/* 206 */           setPlayerMustTorment(nbtPlayer, 0, -1);
/* 207 */           if (player.func_70115_ae()) {
/* 208 */             player.func_70078_a(null);
/*     */           }
/*     */           
/* 211 */           ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
/*     */           
/* 213 */           int yPos = 12 + level * 15;
/* 214 */           player.func_70634_a(8.0D, yPos, 8.0D);
/* 215 */           ItemGeneral.travelToDimension(player, Config.instance().dimensionTormentID);
/* 216 */           player.func_70634_a(8.0D, yPos, 8.0D);
/* 217 */           ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
/* 218 */           World tormentWorld = com.emoniph.witchery.util.ServerUtil.getWorld(Config.instance().dimensionTormentID);
/* 219 */           int midX = 8;
/* 220 */           int midZ = 8;
/* 221 */           for (int x = midX - 1; x <= midX + 1; x++) {
/* 222 */             for (int z = midZ - 1; z <= midZ + 1; z++) {
/* 223 */               if (!tormentWorld.func_147437_c(x, yPos, z)) {
/* 224 */                 tormentWorld.func_147468_f(x, yPos, z);
/*     */               }
/* 226 */               if (!tormentWorld.func_147437_c(x, yPos + 1, z)) {
/* 227 */                 tormentWorld.func_147468_f(x, yPos + 1, z);
/*     */               }
/*     */             }
/*     */           }
/* 231 */           if (mustTorment == 2) {
/* 232 */             boolean found = false;
/* 233 */             if (player.field_70170_p.field_73011_w.field_76574_g == Config.instance().dimensionTormentID) {
/* 234 */               for (Object obj : player.field_70170_p.field_72996_f) {
/* 235 */                 if ((obj instanceof EntityLordOfTorment)) {
/* 236 */                   EntityLordOfTorment lot = (EntityLordOfTorment)obj;
/* 237 */                   if ((lot.field_70163_u >= yPos - 2) && (lot.field_70163_u <= yPos + 6 - 2)) {
/* 238 */                     found = true;
/* 239 */                     break;
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/* 244 */             if (!found)
/*     */             {
/* 246 */               if (tormentWorld != null) {
/* 247 */                 EntityLordOfTorment lot = new EntityLordOfTorment(tormentWorld);
/* 248 */                 lot.func_70080_a(9.0D, yPos - 1, 36.0D, 0.0F, 0.0F);
/* 249 */                 lot.func_110163_bv();
/* 250 */                 lot.func_70606_j(lot.func_110138_aP() * 0.5F);
/* 251 */                 tormentWorld.func_72838_d(lot);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 256 */         else if (mustTorment == 3) {
/* 257 */           setPlayerMustTorment(nbtPlayer, 0, -2);
/* 258 */           if (player.func_70115_ae()) {
/* 259 */             player.func_70078_a(null);
/*     */           }
/*     */           
/* 262 */           World overworld = net.minecraft.server.MinecraftServer.func_71276_C().field_71305_c[0];
/* 263 */           ChunkCoordinates coords = player.getBedLocation(0);
/* 264 */           int dimension = 0;
/* 265 */           if (coords == null) {
/* 266 */             coords = overworld.func_72861_E();
/*     */           }
/* 268 */           if (coords != null) {
/* 269 */             int mod = 0;
/* 270 */             int origY = coords.field_71572_b;
/* 271 */             while ((!isSafeBlock(overworld, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c)) && (coords.field_71572_b > 1) && (coords.field_71572_b < 255)) {
/* 272 */               coords.field_71572_b = (origY + mod);
/* 273 */               if (origY - mod > 1) {
/* 274 */                 mod = -mod;
/*     */               }
/* 276 */               if (mod >= 0) {
/* 277 */                 mod++;
/*     */               }
/*     */             }
/*     */             
/* 281 */             ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
/* 282 */             ItemGeneral.teleportToLocation(player.field_70170_p, coords.field_71574_a, coords.field_71572_b + 1, coords.field_71573_c, dimension, player, true);
/* 283 */             ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isSafeBlock(World world, int posX, int posY, int posZ) {
/* 291 */     boolean base = BlockUtil.isSolid(world, posX, posY, posZ);
/* 292 */     boolean air1 = !BlockUtil.isSolid(world, posX, posY + 1, posZ);
/* 293 */     boolean air2 = !BlockUtil.isSolid(world, posX, posY + 2, posZ);
/* 294 */     boolean isSafe = (base) && (air1) && (air2);
/* 295 */     return isSafe;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/dimension/WorldProviderTorment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */