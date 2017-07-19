/*     */ package com.emoniph.witchery.brewing.action.effect;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.BrewNamePart;
/*     */ import com.emoniph.witchery.brewing.EffectLevel;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*     */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionList;
/*     */ import com.emoniph.witchery.item.ItemBook;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S26PacketMapChunkBulk;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BrewActionBiomeChange extends BrewActionEffect
/*     */ {
/*     */   public BrewActionBiomeChange(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel)
/*     */   {
/*  35 */     super(itemKey, namePart, powerCost, baseProbability, effectLevel);
/*     */   }
/*     */   
/*     */   public void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers)
/*     */   {
/*  40 */     super.prepareSplashPotion(world, actionList, modifiers);
/*  41 */     modifiers.setOnlyInstant();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  47 */     BiomeGenBase biome = ItemBook.getSelectedBiome(stack.func_77960_j());
/*  48 */     int maxRadius = 16 + modifiers.getStrength() * 16;
/*  49 */     changeBiome(world, new Coord(x, y, z), maxRadius, biome);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*     */   {
/*  55 */     BiomeGenBase biome = ItemBook.getSelectedBiome(actionStack.func_77960_j());
/*  56 */     changeBiome(world, new Coord(x, y, z), 1 + modifiers.getStrength(), biome);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack) {}
/*     */   
/*     */ 
/*     */   protected void changeBiome(World world, Coord coord, int radius, BiomeGenBase biome)
/*     */   {
/*  65 */     HashMap<ChunkCoord, byte[]> chunkMap = new HashMap();
/*  66 */     drawFilledCircle(world, coord.x, coord.z, radius, chunkMap, biome);
/*  67 */     ArrayList<Chunk> chunks = new ArrayList();
/*  68 */     for (Map.Entry<ChunkCoord, byte[]> entry : chunkMap.entrySet()) {
/*  69 */       Chunk chunk = ((ChunkCoord)entry.getKey()).getChunk(world);
/*  70 */       chunk.func_76616_a((byte[])entry.getValue());
/*  71 */       chunks.add(chunk);
/*     */     }
/*     */     
/*  74 */     S26PacketMapChunkBulk packet = new S26PacketMapChunkBulk(chunks);
/*  75 */     Witchery.packetPipeline.sendToDimension(packet, world);
/*     */     
/*  77 */     for (Chunk chunk : chunks) {
/*  78 */       for (Object tileObj : chunk.field_150816_i.values()) {
/*  79 */         TileEntity tile = (TileEntity)tileObj;
/*  80 */         Packet packet2 = tile.func_145844_m();
/*  81 */         if (packet2 != null) {
/*  82 */           world.func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawFilledCircle(World world, int x0, int z0, int radius, HashMap<ChunkCoord, byte[]> chunkMap, BiomeGenBase biome)
/*     */   {
/*  90 */     if (radius == 1) {
/*  91 */       drawLine(world, x0, x0, z0, chunkMap, biome);
/*     */     } else {
/*  93 */       radius--;
/*  94 */       int x = radius;
/*  95 */       int z = 0;
/*  96 */       int radiusError = 1 - x;
/*     */       
/*  98 */       while (x >= z) {
/*  99 */         drawLine(world, -x + x0, x + x0, z + z0, chunkMap, biome);
/* 100 */         drawLine(world, -z + x0, z + x0, x + z0, chunkMap, biome);
/* 101 */         drawLine(world, -x + x0, x + x0, -z + z0, chunkMap, biome);
/* 102 */         drawLine(world, -z + x0, z + x0, -x + z0, chunkMap, biome);
/*     */         
/* 104 */         z++;
/*     */         
/* 106 */         if (radiusError < 0) {
/* 107 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 109 */           x--;
/* 110 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawLine(World world, int x1, int x2, int z, HashMap<ChunkCoord, byte[]> chunkMap, BiomeGenBase biome)
/*     */   {
/* 118 */     for (int x = x1; x <= x2; x++) {
/* 119 */       ChunkCoord coord = new ChunkCoord(x >> 4, z >> 4);
/* 120 */       byte[] map = (byte[])chunkMap.get(coord);
/* 121 */       if (map == null) {
/* 122 */         Chunk chunk = world.func_72938_d(x, z);
/* 123 */         map = (byte[])chunk.func_76605_m().clone();
/* 124 */         chunkMap.put(coord, map);
/*     */       }
/*     */       
/* 127 */       map[((z & 0xF) << 4 | x & 0xF)] = ((byte)biome.field_76756_M);
/*     */       
/* 129 */       if (biome.field_76751_G == 0.0F) {
/* 130 */         int y = world.func_72825_h(x, z);
/* 131 */         if (world.func_147439_a(x, y, z) == net.minecraft.init.Blocks.field_150431_aC) {
/* 132 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ChunkCoord {
/*     */     public final int X;
/*     */     public final int Z;
/*     */     
/*     */     public ChunkCoord(int x, int z) {
/* 143 */       this.X = x;
/* 144 */       this.Z = z;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj)
/*     */     {
/* 149 */       if (obj == this) {
/* 150 */         return true;
/*     */       }
/*     */       
/* 153 */       if ((obj == null) || (obj.getClass() != getClass())) {
/* 154 */         return false;
/*     */       }
/*     */       
/* 157 */       ChunkCoord other = (ChunkCoord)obj;
/* 158 */       return (this.X == other.X) && (this.Z == other.Z);
/*     */     }
/*     */     
/*     */     public int hashCode()
/*     */     {
/* 163 */       int result = this.X ^ this.X >>> 32;
/* 164 */       result = 31 * result + (this.Z ^ this.Z >>> 32);
/* 165 */       return result;
/*     */     }
/*     */     
/*     */     public Chunk getChunk(World world) {
/* 169 */       return world.func_72964_e(this.X, this.Z);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionBiomeChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */