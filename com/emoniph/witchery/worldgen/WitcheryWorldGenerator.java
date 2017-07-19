/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.feature.WorldGenFlowers;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ 
/*     */ 
/*     */ public class WitcheryWorldGenerator
/*     */   implements IWorldGenerator
/*     */ {
/*  26 */   private LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();
/*     */   private final WorldHandlerCoven covenGen;
/*     */   private final WorldHandlerWickerMan wickerManGen;
/*     */   private final WorldHandlerShack shackGen;
/*     */   private final List<IWorldGenHandler> generators;
/*     */   private int midX;
/*     */   private int midZ;
/*     */   int field_82665_g;
/*  34 */   int field_82666_h = 8;
/*     */   
/*     */   public WitcheryWorldGenerator() {
/*  37 */     this.covenGen = new WorldHandlerCoven(1.0D, 400);
/*  38 */     this.wickerManGen = new WorldHandlerWickerMan(1.0D, 400);
/*  39 */     this.shackGen = new WorldHandlerShack(1.0D, 400);
/*     */     
/*  41 */     IWorldGenHandler goblinHut = new WorldHandlerClonedStructure(ComponentGoblinHut.class, 1.0D, 400, 7, 7, 7);
/*  42 */     this.generators = Arrays.asList(new IWorldGenHandler[] { this.wickerManGen, this.covenGen, this.shackGen, goblinHut });
/*     */     
/*  44 */     this.field_82665_g = (8 + Math.max(Math.min(Config.instance().worldGenFrequency, 64), 1));
/*     */     
/*  46 */     this.midX = 0;
/*  47 */     this.midZ = 0;
/*  48 */     for (IWorldGenHandler gen : this.generators) {
/*  49 */       this.midX = Math.max(this.midX, gen.getExtentX() / 2);
/*  50 */       this.midZ = Math.max(this.midZ, gen.getExtentZ() / 2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
/*     */   {
/*  57 */     if (world.field_73011_w.field_76574_g == 0) {
/*  58 */       generateOverworld(world, world.field_73012_v, chunkX * 16, chunkZ * 16);
/*  59 */     } else if ((Config.instance().worldGenTwilightForest) && (world.field_73011_w.func_80007_l().equals("Twilight Forest"))) {
/*  60 */       generateOverworld(world, world.field_73012_v, chunkX * 16, chunkZ * 16);
/*  61 */     } else if (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) {
/*  62 */       generateDreamworld(world, world.field_73012_v, chunkX * 16, chunkZ * 16);
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateDreamworld(World world, Random random, int chunkX, int chunkZ) {
/*  67 */     int flowerPerChunk = 1;
/*  68 */     BiomeGenBase biome = world.func_72807_a(chunkX, chunkZ);
/*  69 */     if (((!BiomeManager.DISALLOWED_BIOMES.contains(Integer.valueOf(biome.field_76756_M))) || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE))) && (random.nextInt(4) != 0)) {
/*  70 */       for (int j = 0; j < 1; j++)
/*     */       {
/*  72 */         int k = chunkX + random.nextInt(16) + 8;
/*     */         
/*     */ 
/*  75 */         int i1 = chunkZ + random.nextInt(16) + 8;
/*  76 */         int l = random.nextInt(world.func_72976_f(k, i1) + 32);
/*  77 */         new WorldGenFlowers(Witchery.Blocks.WISPY_COTTON).func_76484_a(world, random, k, l, i1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void generateOverworld(World world, Random random, int x, int z) {
/*  83 */     boolean gen = false;
/*  84 */     if (!BiomeManager.DISALLOWED_BIOMES.contains(Integer.valueOf(world.func_72807_a(x + this.midX, z + this.midZ).field_76756_M))) {
/*  85 */       Collections.shuffle(this.generators, random);
/*  86 */       for (IWorldGenHandler generator : this.generators) {
/*  87 */         if ((nonInRange(world, x, z, generator.getRange())) && (generator.generate(world, random, x, z))) {
/*  88 */           this.structuresList.add(new ChunkCoordIntPair(x, z));
/*  89 */           gen = true;
/*  90 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean nonInRange(World worldObj, int x, int z, int range)
/*     */   {
/* 102 */     int par1 = x / 16;
/* 103 */     int par2 = z / 16;
/*     */     
/* 105 */     int k = par1;
/* 106 */     int l = par2;
/*     */     
/* 108 */     if (par1 < 0)
/*     */     {
/* 110 */       par1 -= this.field_82665_g - 1;
/*     */     }
/*     */     
/* 113 */     if (par2 < 0)
/*     */     {
/* 115 */       par2 -= this.field_82665_g - 1;
/*     */     }
/*     */     
/* 118 */     int i1 = par1 / this.field_82665_g;
/* 119 */     int j1 = par2 / this.field_82665_g;
/* 120 */     Random random = worldObj.func_72843_D(i1, j1, 10387312);
/* 121 */     i1 *= this.field_82665_g;
/* 122 */     j1 *= this.field_82665_g;
/* 123 */     i1 += random.nextInt(this.field_82665_g - this.field_82666_h);
/* 124 */     j1 += random.nextInt(this.field_82665_g - this.field_82666_h);
/*     */     
/* 126 */     return (k == i1) && (l == j1);
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
/*     */ 
/*     */   public void initiate()
/*     */   {
/* 140 */     this.structuresList.clear();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WitcheryWorldGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */