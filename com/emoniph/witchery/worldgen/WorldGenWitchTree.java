/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenWitchTree
/*     */   extends WorldGenerator
/*     */ {
/*     */   private final int minTreeHeight;
/*     */   private final boolean vinesGrow;
/*     */   private final int metaWood;
/*     */   private final int metaLeaves;
/*     */   private final int spread;
/*     */   
/*     */   public WorldGenWitchTree(boolean update, int minHeight, int woodMeta, int leavesMeta, int spread, boolean growVines)
/*     */   {
/*  31 */     super(update);
/*  32 */     this.minTreeHeight = minHeight;
/*  33 */     this.metaWood = woodMeta;
/*  34 */     this.metaLeaves = leavesMeta;
/*  35 */     this.vinesGrow = growVines;
/*  36 */     this.spread = spread;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5)
/*     */   {
/*  41 */     int l = par2Random.nextInt(3) + this.minTreeHeight;
/*  42 */     boolean flag = true;
/*     */     
/*  44 */     if ((par4 >= 1) && (par4 + l + 1 <= 256))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  50 */       for (int i1 = par4; i1 <= par4 + 1 + l; i1++) {
/*  51 */         byte b0 = 1;
/*     */         
/*  53 */         if (i1 == par4) {
/*  54 */           b0 = 0;
/*     */         }
/*     */         
/*  57 */         if (i1 >= par4 + 1 + l - 2) {
/*  58 */           b0 = 2;
/*     */         }
/*     */         
/*  61 */         for (int l1 = par3 - b0; (l1 <= par3 + b0) && (flag); l1++) {
/*  62 */           for (int j1 = par5 - b0; (j1 <= par5 + b0) && (flag); j1++) {
/*  63 */             if ((i1 >= 0) && (i1 < 256))
/*     */             {
/*  65 */               Block block = par1World.func_147439_a(l1, i1, j1);
/*  66 */               boolean isAir = par1World.func_147437_c(l1, i1, j1);
/*     */               
/*  68 */               if ((!isAir) && (!block.isLeaves(par1World, l1, i1, j1)) && (block != Blocks.field_150349_c) && (block != Blocks.field_150346_d) && (!block.isWood(par1World, l1, i1, j1)))
/*     */               {
/*  70 */                 flag = false;
/*     */               }
/*     */             } else {
/*  73 */               flag = false;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  79 */       if (!flag) {
/*  80 */         return false;
/*     */       }
/*  82 */       Block soil = par1World.func_147439_a(par3, par4 - 1, par5);
/*  83 */       boolean isSoil = (soil != null) && (soil.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (IPlantable)Witchery.Blocks.SAPLING));
/*     */       
/*     */ 
/*  86 */       if ((isSoil) && (par4 < 256 - l - 1)) {
/*  87 */         soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
/*  88 */         byte b0 = 3;
/*  89 */         byte b1 = 0;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*  94 */         for (int j1 = par4 - b0 + l; j1 <= par4 + l; j1++) {
/*  95 */           int k1 = j1 - (par4 + l);
/*  96 */           int i2 = b1 + 1 - k1 / 2 + this.spread;
/*     */           
/*  98 */           for (int j2 = par3 - i2; j2 <= par3 + i2; j2++) {
/*  99 */             int k2 = j2 - par3;
/*     */             
/* 101 */             for (int l2 = par5 - i2; l2 <= par5 + i2; l2++) {
/* 102 */               int i3 = l2 - par5;
/*     */               
/* 104 */               if ((Math.abs(k2) != i2) || (Math.abs(i3) != i2) || ((par2Random.nextInt(2) != 0) && (k1 != 0))) {
/* 105 */                 Block block = par1World.func_147439_a(j2, j1, l2);
/*     */                 
/* 107 */                 if ((block == Blocks.field_150350_a) || (block.canBeReplacedByLeaves(par1World, j2, j1, l2))) {
/* 108 */                   func_150516_a(par1World, j2, j1, l2, Witchery.Blocks.LEAVES, this.metaLeaves);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 115 */         for (j1 = 0; j1 < l; j1++)
/*     */         {
/* 117 */           Block block = par1World.func_147439_a(par3, par4 + j1, par5);
/*     */           
/* 119 */           if ((block == Blocks.field_150350_a) || (block.isLeaves(par1World, par3, par4 + j1, par5))) {
/* 120 */             func_150516_a(par1World, par3, par4 + j1, par5, Witchery.Blocks.LOG, this.metaWood);
/*     */             
/* 122 */             if ((this.vinesGrow) && (j1 > 0)) {
/* 123 */               if ((par2Random.nextInt(3) > 0) && (par1World.func_147437_c(par3 - 1, par4 + j1, par5))) {
/* 124 */                 func_150516_a(par1World, par3 - 1, par4 + j1, par5, Blocks.field_150395_bd, 8);
/*     */               }
/*     */               
/* 127 */               if ((par2Random.nextInt(3) > 0) && (par1World.func_147437_c(par3 + 1, par4 + j1, par5))) {
/* 128 */                 func_150516_a(par1World, par3 + 1, par4 + j1, par5, Blocks.field_150395_bd, 2);
/*     */               }
/*     */               
/* 131 */               if ((par2Random.nextInt(3) > 0) && (par1World.func_147437_c(par3, par4 + j1, par5 - 1))) {
/* 132 */                 func_150516_a(par1World, par3, par4 + j1, par5 - 1, Blocks.field_150395_bd, 1);
/*     */               }
/*     */               
/* 135 */               if ((par2Random.nextInt(3) > 0) && (par1World.func_147437_c(par3, par4 + j1, par5 + 1))) {
/* 136 */                 func_150516_a(par1World, par3, par4 + j1, par5 + 1, Blocks.field_150395_bd, 4);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 142 */         if (this.vinesGrow) {
/* 143 */           for (j1 = par4 - 3 + l; j1 <= par4 + l; j1++) {
/* 144 */             int k1 = j1 - (par4 + l);
/* 145 */             int i2 = 2 - k1 / 2;
/*     */             
/* 147 */             for (int j2 = par3 - i2; j2 <= par3 + i2; j2++) {
/* 148 */               for (int k2 = par5 - i2; k2 <= par5 + i2; k2++) {
/* 149 */                 Block block = par1World.func_147439_a(j2, j1, k2);
/* 150 */                 if ((block != null) && (block.isLeaves(par1World, j2, j1, k2))) {
/* 151 */                   if ((par2Random.nextInt(4) == 0) && (par1World.func_147437_c(j2 - 1, j1, k2))) {
/* 152 */                     growVines(par1World, j2 - 1, j1, k2, 8);
/*     */                   }
/*     */                   
/* 155 */                   if ((par2Random.nextInt(4) == 0) && (par1World.func_147437_c(j2 + 1, j1, k2))) {
/* 156 */                     growVines(par1World, j2 + 1, j1, k2, 2);
/*     */                   }
/*     */                   
/* 159 */                   if ((par2Random.nextInt(4) == 0) && (par1World.func_147437_c(j2, j1, k2 - 1))) {
/* 160 */                     growVines(par1World, j2, j1, k2 - 1, 1);
/*     */                   }
/*     */                   
/* 163 */                   if ((par2Random.nextInt(4) == 0) && (par1World.func_147437_c(j2, j1, k2 + 1))) {
/* 164 */                     growVines(par1World, j2, j1, k2 + 1, 4);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 171 */           if ((par2Random.nextInt(5) == 0) && (l > 5)) {
/* 172 */             for (j1 = 0; j1 < 2; j1++) {
/* 173 */               for (int k1 = 0; k1 < 4; k1++) {
/* 174 */                 if (par2Random.nextInt(4 - j1) == 0) {
/* 175 */                   int i2 = par2Random.nextInt(3);
/* 176 */                   func_150516_a(par1World, par3 + net.minecraft.util.Direction.field_71583_a[net.minecraft.util.Direction.field_71580_e[k1]], par4 + l - 5 + j1, par5 + net.minecraft.util.Direction.field_71581_b[net.minecraft.util.Direction.field_71580_e[k1]], Blocks.field_150375_by, i2 << 2 | k1);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 185 */         return true;
/*     */       }
/* 187 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 191 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void growVines(World par1World, int par2, int par3, int par4, int par5)
/*     */   {
/* 200 */     func_150516_a(par1World, par2, par3, par4, Blocks.field_150395_bd, par5);
/* 201 */     int i1 = 4;
/*     */     for (;;)
/*     */     {
/* 204 */       par3--;
/*     */       
/* 206 */       if ((!par1World.func_147437_c(par2, par3, par4)) || (i1 <= 0)) {
/* 207 */         return;
/*     */       }
/*     */       
/* 210 */       func_150516_a(par1World, par2, par3, par4, Blocks.field_150395_bd, par5);
/* 211 */       i1--;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WorldGenWitchTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */