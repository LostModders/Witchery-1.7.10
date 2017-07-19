/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class ComponentWickerMan extends WitcheryComponent
/*     */ {
/*     */   public static final int DIM_X = 6;
/*     */   public static final int DIM_Y = 8;
/*     */   public static final int DIM_Z = 5;
/*     */   
/*     */   public ComponentWickerMan() {}
/*     */   
/*     */   public ComponentWickerMan(int direction, Random random, int x, int z)
/*     */   {
/*  20 */     super(direction, random, x, z, 6, 8, 5);
/*     */   }
/*     */   
/*     */   public boolean addComponentParts(World world, Random random) {
/*  24 */     BiomeGenBase biom = world.func_72807_a(func_74865_a(0, 0), func_74873_b(0, 0));
/*  25 */     int groundAvg = calcGroundHeight(world, this.field_74887_e);
/*     */     
/*  27 */     if (groundAvg < 0) {
/*  28 */       return true;
/*     */     }
/*     */     
/*  31 */     this.field_74887_e.func_78886_a(0, groundAvg - this.field_74887_e.field_78894_e + 8 - 1, 0);
/*     */     
/*     */ 
/*     */ 
/*  35 */     Block wicker = Blocks.field_150407_cf;
/*     */     
/*  37 */     Block plant = Blocks.field_150328_O;
/*  38 */     Block groundID; Block undergroundID; if ((biom.field_76756_M == BiomeGenBase.field_76769_d.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76786_s.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76787_r.field_76756_M)) {
/*  39 */       Block groundID = Blocks.field_150354_m;
/*  40 */       Block undergroundID = Blocks.field_150354_m;
/*  41 */       plant = Blocks.field_150330_I;
/*     */     } else {
/*  43 */       groundID = Blocks.field_150349_c;
/*  44 */       undergroundID = Blocks.field_150346_d;
/*     */     }
/*     */     
/*  47 */     boolean flip = (this.field_74885_f == 0) || (this.field_74885_f == 2);
/*     */     
/*     */ 
/*  50 */     func_74878_a(world, this.field_74887_e, 1, 1, 0, 4, 7, 4);
/*  51 */     func_74878_a(world, this.field_74887_e, 0, 1, 2, 5, 7, 2);
/*     */     
/*  53 */     func_151549_a(world, this.field_74887_e, 1, 0, 0, 4, 0, 4, groundID, groundID, false);
/*  54 */     func_151549_a(world, this.field_74887_e, 0, 0, 2, 5, 0, 2, groundID, groundID, false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  63 */     int ground = 1;
/*     */     
/*  65 */     place(plant, 0, 1, ground, 0, this.field_74887_e, world);
/*  66 */     place(plant, 0, 4, ground, 0, this.field_74887_e, world);
/*  67 */     place(plant, 0, 0, ground, 2, this.field_74887_e, world);
/*  68 */     place(plant, 0, 5, ground, 2, this.field_74887_e, world);
/*  69 */     place(plant, 0, 4, ground, 4, this.field_74887_e, world);
/*  70 */     place(plant, 0, 1, ground, 4, this.field_74887_e, world);
/*     */     
/*  72 */     int horz = 0;
/*     */     
/*  74 */     int vert = flip ? 4 : 8;
/*  75 */     int flat = flip ? 8 : 4;
/*     */     
/*  77 */     int spawnables = Config.instance().strawmanSpawnerRules.length;
/*  78 */     String spawnable = spawnables > 0 ? Config.instance().strawmanSpawnerRules[world.field_73012_v.nextInt(spawnables)] : "Zombie";
/*  79 */     setSpawner(2, 0, 2, (spawnable != null) && (!spawnable.isEmpty()) ? spawnable : "Zombie", world);
/*     */     
/*  81 */     place(wicker, vert, 2, ground, 2, this.field_74887_e, world);
/*  82 */     place(wicker, vert, 3, ground, 2, this.field_74887_e, world);
/*     */     
/*  84 */     place(wicker, vert, 2, ground + 1, 2, this.field_74887_e, world);
/*  85 */     place(wicker, vert, 3, ground + 1, 2, this.field_74887_e, world);
/*     */     
/*  87 */     place(wicker, flat, 1, ground + 2, 2, this.field_74887_e, world);
/*  88 */     place(wicker, vert, 2, ground + 2, 2, this.field_74887_e, world);
/*  89 */     place(wicker, vert, 3, ground + 2, 2, this.field_74887_e, world);
/*  90 */     place(wicker, flat, 4, ground + 2, 2, this.field_74887_e, world);
/*     */     
/*  92 */     place(wicker, vert, 1, ground + 3, 2, this.field_74887_e, world);
/*  93 */     place(wicker, 0, 2, ground + 3, 2, this.field_74887_e, world);
/*  94 */     place(wicker, 0, 3, ground + 3, 2, this.field_74887_e, world);
/*  95 */     place(wicker, vert, 4, ground + 3, 2, this.field_74887_e, world);
/*     */     
/*  97 */     place(wicker, 0, 1, ground + 4, 2, this.field_74887_e, world);
/*  98 */     place(wicker, 0, 2, ground + 4, 2, this.field_74887_e, world);
/*  99 */     place(wicker, 0, 3, ground + 4, 2, this.field_74887_e, world);
/* 100 */     place(wicker, 0, 4, ground + 4, 2, this.field_74887_e, world);
/*     */     
/* 102 */     place(wicker, flat, 2, ground + 5, 2, this.field_74887_e, world);
/* 103 */     place(wicker, flat, 3, ground + 5, 2, this.field_74887_e, world);
/*     */     
/* 105 */     place(wicker, flat, 2, ground + 6, 2, this.field_74887_e, world);
/* 106 */     place(wicker, flat, 3, ground + 6, 2, this.field_74887_e, world);
/*     */     
/* 108 */     for (int x = 0; x < 6; x++) {
/* 109 */       for (int z = 0; z < 5; z++) {
/* 110 */         func_151554_b(world, undergroundID, 0, x, 0, z, this.field_74887_e);
/* 111 */         func_74871_b(world, x, 8, z, this.field_74887_e);
/*     */       }
/*     */     }
/*     */     
/* 115 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentWickerMan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */