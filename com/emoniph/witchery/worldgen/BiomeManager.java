/*    */ package com.emoniph.witchery.worldgen;
/*    */ 
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraftforge.common.BiomeDictionary;
/*    */ import net.minecraftforge.common.BiomeDictionary.Type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeManager
/*    */ {
/* 16 */   public static final ArrayList DISALLOWED_BIOMES = new ArrayList();
/*    */   
/*    */   public static void addModBiomes() {
/* 19 */     DISALLOWED_BIOMES.clear();
/*    */     
/* 21 */     ArrayList list = new ArrayList();
/*    */     
/* 23 */     for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP)) {
/* 24 */       if (!list.contains(Integer.valueOf(biome.field_76756_M))) {
/* 25 */         list.add(Integer.valueOf(biome.field_76756_M));
/*    */       }
/*    */     }
/* 28 */     for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.RIVER)) {
/* 29 */       if (!list.contains(Integer.valueOf(biome.field_76756_M))) {
/* 30 */         list.add(Integer.valueOf(biome.field_76756_M));
/*    */       }
/*    */     }
/* 33 */     for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.OCEAN)) {
/* 34 */       if (!list.contains(Integer.valueOf(biome.field_76756_M))) {
/* 35 */         list.add(Integer.valueOf(biome.field_76756_M));
/*    */       }
/*    */     }
/* 38 */     for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MOUNTAIN)) {
/* 39 */       if (!list.contains(Integer.valueOf(biome.field_76756_M))) {
/* 40 */         list.add(Integer.valueOf(biome.field_76756_M));
/*    */       }
/*    */     }
/* 43 */     for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE)) {
/* 44 */       if (!list.contains(Integer.valueOf(biome.field_76756_M))) {
/* 45 */         list.add(Integer.valueOf(biome.field_76756_M));
/*    */       }
/*    */     }
/* 48 */     for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.BEACH)) {
/* 49 */       if (!list.contains(Integer.valueOf(biome.field_76756_M))) {
/* 50 */         list.add(Integer.valueOf(biome.field_76756_M));
/*    */       }
/*    */     }
/* 53 */     Log.instance().debug("Found " + list.size() + " biomes to ignore for world gen.");
/* 54 */     if (list.size() > 0) {
/* 55 */       DISALLOWED_BIOMES.addAll(list);
/*    */     }
/*    */   }
/*    */   
/*    */   public static BiomeGenBase[] biomesWithout(BiomeDictionary.Type... biomesWithout) {
/* 60 */     ArrayList<BiomeGenBase> biomes = new ArrayList();
/* 61 */     for (BiomeGenBase biome : BiomeGenBase.func_150565_n()) {
/* 62 */       if (biome != null) {
/* 63 */         boolean skip = false;
/* 64 */         for (int i = 0; i < biomesWithout.length; i++) {
/* 65 */           if (BiomeDictionary.isBiomeOfType(biome, biomesWithout[i])) {
/* 66 */             skip = true;
/* 67 */             break;
/*    */           }
/*    */         }
/* 70 */         if (!skip) {
/* 71 */           biomes.add(biome);
/*    */         }
/*    */       }
/*    */     }
/* 75 */     return (BiomeGenBase[])biomes.toArray(new BiomeGenBase[biomes.size()]);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/BiomeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */