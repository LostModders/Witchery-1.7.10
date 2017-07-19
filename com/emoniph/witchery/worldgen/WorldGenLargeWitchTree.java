/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenLargeWitchTree
/*     */   extends WorldGenerator
/*     */ {
/*  20 */   static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
/*     */   
/*     */ 
/*  23 */   Random rand = new Random();
/*     */   
/*     */   World worldObj;
/*     */   
/*  27 */   int[] basePos = { 0, 0, 0 };
/*     */   int heightLimit;
/*     */   int height;
/*  30 */   double heightAttenuation = 0.618D;
/*  31 */   double branchDensity = 1.0D;
/*  32 */   double branchSlope = 0.381D;
/*  33 */   double scaleWidth = 1.0D;
/*  34 */   double leafDensity = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  40 */   int trunkSize = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  45 */   int heightLimitLimit = 12;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  51 */   int leafDistanceLimit = 4;
/*     */   
/*     */   int[][] leafNodes;
/*     */   
/*     */   final int leafMeta;
/*     */   final int logMeta;
/*     */   
/*     */   public WorldGenLargeWitchTree(boolean par1, int logMeta, int leafMeta)
/*     */   {
/*  60 */     this(par1, logMeta, leafMeta, 0.618D, 0.381D);
/*     */   }
/*     */   
/*     */   public WorldGenLargeWitchTree(boolean par1, int logMeta, int leafMeta, double branchSlope) {
/*  64 */     this(par1, logMeta, leafMeta, 0.618D, branchSlope);
/*     */   }
/*     */   
/*     */   public WorldGenLargeWitchTree(boolean par1, int logMeta, int leafMeta, double attenuation, double branchSlope) {
/*  68 */     super(par1);
/*  69 */     this.logMeta = logMeta;
/*  70 */     this.leafMeta = leafMeta;
/*  71 */     this.heightAttenuation = attenuation;
/*  72 */     this.branchSlope = branchSlope;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeList()
/*     */   {
/*  80 */     this.height = ((int)(this.heightLimit * this.heightAttenuation));
/*     */     
/*  82 */     if (this.height >= this.heightLimit) {
/*  83 */       this.height = (this.heightLimit - 1);
/*     */     }
/*     */     
/*  86 */     int i = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  88 */     if (i < 1) {
/*  89 */       i = 1;
/*     */     }
/*     */     
/*  92 */     int[][] aint = new int[i * this.heightLimit][4];
/*  93 */     int j = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  94 */     int k = 1;
/*  95 */     int l = this.basePos[1] + this.height;
/*  96 */     int i1 = j - this.basePos[1];
/*  97 */     aint[0][0] = this.basePos[0];
/*  98 */     aint[0][1] = j;
/*  99 */     aint[0][2] = this.basePos[2];
/* 100 */     aint[0][3] = l;
/* 101 */     j--;
/*     */     
/* 103 */     while (i1 >= 0) {
/* 104 */       int j1 = 0;
/* 105 */       float f = layerSize(i1);
/*     */       
/* 107 */       if (f < 0.0F) {
/* 108 */         j--;
/* 109 */         i1--;
/*     */       } else {
/* 111 */         for (double d0 = 0.5D; j1 < i; j1++) {
/* 112 */           double d1 = this.scaleWidth * f * (this.rand.nextFloat() + 0.328D);
/* 113 */           double d2 = this.rand.nextFloat() * 2.0D * 3.141592653589793D;
/* 114 */           int k1 = MathHelper.func_76128_c(d1 * Math.sin(d2) + this.basePos[0] + d0);
/* 115 */           int l1 = MathHelper.func_76128_c(d1 * Math.cos(d2) + this.basePos[2] + d0);
/* 116 */           int[] aint1 = { k1, j, l1 };
/* 117 */           int[] aint2 = { k1, j + this.leafDistanceLimit, l1 };
/*     */           
/* 119 */           if (checkBlockLine(aint1, aint2) == -1) {
/* 120 */             int[] aint3 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 121 */             double d3 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - aint1[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - aint1[2]), 2.0D));
/*     */             
/* 123 */             double d4 = d3 * this.branchSlope;
/*     */             
/* 125 */             if (aint1[1] - d4 > l) {
/* 126 */               aint3[1] = l;
/*     */             } else {
/* 128 */               aint3[1] = ((int)(aint1[1] - d4));
/*     */             }
/*     */             
/* 131 */             if (checkBlockLine(aint3, aint1) == -1) {
/* 132 */               aint[k][0] = k1;
/* 133 */               aint[k][1] = j;
/* 134 */               aint[k][2] = l1;
/* 135 */               aint[k][3] = aint3[1];
/* 136 */               k++;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 141 */         j--;
/* 142 */         i1--;
/*     */       }
/*     */     }
/*     */     
/* 146 */     this.leafNodes = new int[k][4];
/* 147 */     System.arraycopy(aint, 0, this.leafNodes, 0, k);
/*     */   }
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block blockID, int meta) {
/* 151 */     int i1 = (int)(par4 + 0.618D);
/* 152 */     byte b1 = otherCoordPairs[par5];
/* 153 */     byte b2 = otherCoordPairs[(par5 + 3)];
/* 154 */     int[] aint = { par1, par2, par3 };
/* 155 */     int[] aint1 = { 0, 0, 0 };
/* 156 */     int j1 = -i1;
/* 157 */     int k1 = -i1;
/*     */     
/* 159 */     for (aint1[par5] = aint[par5]; j1 <= i1; j1++) {
/* 160 */       aint[b1] += j1;
/* 161 */       k1 = -i1;
/*     */       
/* 163 */       while (k1 <= i1) {
/* 164 */         double d0 = Math.pow(Math.abs(j1) + 0.5D, 2.0D) + Math.pow(Math.abs(k1) + 0.5D, 2.0D);
/*     */         
/* 166 */         if (d0 > par4 * par4) {
/* 167 */           k1++;
/*     */         } else {
/* 169 */           aint[b2] += k1;
/* 170 */           Block l1 = this.worldObj.func_147439_a(aint1[0], aint1[1], aint1[2]);
/*     */           
/* 172 */           if ((l1 != Blocks.field_150350_a) && (l1 != Witchery.Blocks.LEAVES)) {
/* 173 */             k1++;
/*     */           } else {
/* 175 */             func_150516_a(this.worldObj, aint1[0], aint1[1], aint1[2], blockID, meta);
/* 176 */             k1++;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   float layerSize(int par1)
/*     */   {
/* 187 */     if (par1 < this.heightLimit * 0.3D) {
/* 188 */       return -1.618F;
/*     */     }
/* 190 */     float f = this.heightLimit / 2.0F;
/* 191 */     float f1 = this.heightLimit / 2.0F - par1;
/*     */     float f2;
/*     */     float f2;
/* 194 */     if (f1 == 0.0F) {
/* 195 */       f2 = f; } else { float f2;
/* 196 */       if (Math.abs(f1) >= f) {
/* 197 */         f2 = 0.0F;
/*     */       } else {
/* 199 */         f2 = (float)Math.sqrt(Math.pow(Math.abs(f), 2.0D) - Math.pow(Math.abs(f1), 2.0D));
/*     */       }
/*     */     }
/* 202 */     f2 *= 0.5F;
/* 203 */     return f2;
/*     */   }
/*     */   
/*     */   float leafSize(int par1)
/*     */   {
/* 208 */     return (par1 >= 0) && (par1 < this.leafDistanceLimit) ? 2.0F : (par1 != 0) && (par1 != this.leafDistanceLimit - 1) ? 3.0F : -1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNode(int par1, int par2, int par3)
/*     */   {
/* 216 */     int l = par2;
/*     */     
/* 218 */     for (int i1 = par2 + this.leafDistanceLimit; l < i1; l++) {
/* 219 */       float f = leafSize(l - par2);
/* 220 */       genTreeLayer(par1, l, par3, f, (byte)1, Witchery.Blocks.LEAVES, this.leafMeta);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block blockId, int meta)
/*     */   {
/* 229 */     int[] aint2 = { 0, 0, 0 };
/* 230 */     byte b0 = 0;
/*     */     
/*     */ 
/* 233 */     for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1)) {
/* 234 */       par2ArrayOfInteger[b0] -= par1ArrayOfInteger[b0];
/*     */       
/* 236 */       if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
/* 237 */         b1 = b0;
/*     */       }
/*     */     }
/*     */     
/* 241 */     if (aint2[b1] != 0) {
/* 242 */       byte b2 = otherCoordPairs[b1];
/* 243 */       byte b3 = otherCoordPairs[(b1 + 3)];
/*     */       byte b4;
/*     */       byte b4;
/* 246 */       if (aint2[b1] > 0) {
/* 247 */         b4 = 1;
/*     */       } else {
/* 249 */         b4 = -1;
/*     */       }
/*     */       
/* 252 */       double d0 = aint2[b2] / aint2[b1];
/* 253 */       double d1 = aint2[b3] / aint2[b1];
/* 254 */       int[] aint3 = { 0, 0, 0 };
/* 255 */       int j = 0;
/*     */       
/* 257 */       for (int k = aint2[b1] + b4; j != k; j += b4) {
/* 258 */         aint3[b1] = MathHelper.func_76128_c(par1ArrayOfInteger[b1] + j + 0.5D);
/* 259 */         aint3[b2] = MathHelper.func_76128_c(par1ArrayOfInteger[b2] + j * d0 + 0.5D);
/* 260 */         aint3[b3] = MathHelper.func_76128_c(par1ArrayOfInteger[b3] + j * d1 + 0.5D);
/* 261 */         byte b5 = 0;
/* 262 */         int l = Math.abs(aint3[0] - par1ArrayOfInteger[0]);
/* 263 */         int i1 = Math.abs(aint3[2] - par1ArrayOfInteger[2]);
/* 264 */         int j1 = Math.max(l, i1);
/*     */         
/* 266 */         if (j1 > 0) {
/* 267 */           if (l == j1) {
/* 268 */             b5 = 4;
/* 269 */           } else if (i1 == j1) {
/* 270 */             b5 = 8;
/*     */           }
/*     */         }
/*     */         
/* 274 */         func_150516_a(this.worldObj, aint3[0], aint3[1], aint3[2], blockId, meta);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeaves()
/*     */   {
/* 284 */     int i = 0;
/*     */     
/* 286 */     for (int j = this.leafNodes.length; i < j; i++) {
/* 287 */       int k = this.leafNodes[i][0];
/* 288 */       int l = this.leafNodes[i][1];
/* 289 */       int i1 = this.leafNodes[i][2];
/* 290 */       generateLeafNode(k, l, i1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean leafNodeNeedsBase(int par1)
/*     */   {
/* 299 */     return par1 >= this.heightLimit * 0.2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateTrunk()
/*     */   {
/* 307 */     int i = this.basePos[0];
/* 308 */     int j = this.basePos[1];
/* 309 */     int k = this.basePos[1] + this.height;
/* 310 */     int l = this.basePos[2];
/* 311 */     int[] aint = { i, j, l };
/* 312 */     int[] aint1 = { i, k, l };
/* 313 */     placeBlockLine(aint, aint1, Witchery.Blocks.LOG, this.logMeta);
/*     */     
/* 315 */     if (this.trunkSize == 2) {
/* 316 */       aint[0] += 1;
/* 317 */       aint1[0] += 1;
/* 318 */       placeBlockLine(aint, aint1, Witchery.Blocks.LOG, this.logMeta);
/* 319 */       aint[2] += 1;
/* 320 */       aint1[2] += 1;
/* 321 */       placeBlockLine(aint, aint1, Witchery.Blocks.LOG, this.logMeta);
/* 322 */       aint[0] += -1;
/* 323 */       aint1[0] += -1;
/* 324 */       placeBlockLine(aint, aint1, Witchery.Blocks.LOG, this.logMeta);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void generateLeafNodeBases()
/*     */   {
/* 333 */     int i = 0;
/* 334 */     int j = this.leafNodes.length;
/*     */     
/* 336 */     for (int[] aint = { this.basePos[0], this.basePos[1], this.basePos[2] }; i < j; i++) {
/* 337 */       int[] aint1 = this.leafNodes[i];
/* 338 */       int[] aint2 = { aint1[0], aint1[1], aint1[2] };
/* 339 */       aint[1] = aint1[3];
/* 340 */       int k = aint[1] - this.basePos[1];
/*     */       
/* 342 */       if (leafNodeNeedsBase(k)) {
/* 343 */         placeBlockLine(aint, aint2, Witchery.Blocks.LOG, this.logMeta);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger)
/*     */   {
/* 354 */     int[] aint2 = { 0, 0, 0 };
/* 355 */     byte b0 = 0;
/*     */     
/*     */ 
/* 358 */     for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1)) {
/* 359 */       par2ArrayOfInteger[b0] -= par1ArrayOfInteger[b0];
/*     */       
/* 361 */       if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
/* 362 */         b1 = b0;
/*     */       }
/*     */     }
/*     */     
/* 366 */     if (aint2[b1] == 0) {
/* 367 */       return -1;
/*     */     }
/* 369 */     byte b2 = otherCoordPairs[b1];
/* 370 */     byte b3 = otherCoordPairs[(b1 + 3)];
/*     */     byte b4;
/*     */     byte b4;
/* 373 */     if (aint2[b1] > 0) {
/* 374 */       b4 = 1;
/*     */     } else {
/* 376 */       b4 = -1;
/*     */     }
/*     */     
/* 379 */     double d0 = aint2[b2] / aint2[b1];
/* 380 */     double d1 = aint2[b3] / aint2[b1];
/* 381 */     int[] aint3 = { 0, 0, 0 };
/* 382 */     int i = 0;
/*     */     
/*     */ 
/* 385 */     for (int j = aint2[b1] + b4; i != j; i += b4) {
/* 386 */       par1ArrayOfInteger[b1] += i;
/* 387 */       aint3[b2] = MathHelper.func_76128_c(par1ArrayOfInteger[b2] + i * d0);
/* 388 */       aint3[b3] = MathHelper.func_76128_c(par1ArrayOfInteger[b3] + i * d1);
/* 389 */       Block k = this.worldObj.func_147439_a(aint3[0], aint3[1], aint3[2]);
/*     */       
/* 391 */       if ((k != Blocks.field_150350_a) && (k != Witchery.Blocks.LEAVES)) {
/*     */         break;
/*     */       }
/*     */     }
/*     */     
/* 396 */     return i == j ? -1 : Math.abs(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   boolean validTreeLocation()
/*     */   {
/* 405 */     int[] aint = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 406 */     int[] aint1 = { this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2] };
/* 407 */     Block i = this.worldObj.func_147439_a(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
/*     */     
/* 409 */     Block soil = i;
/* 410 */     boolean isValidSoil = (soil != null) && (soil.canSustainPlant(this.worldObj, this.basePos[0], this.basePos[1] - 1, this.basePos[2], ForgeDirection.UP, (IPlantable)Witchery.Blocks.SAPLING));
/*     */     
/* 412 */     if (!isValidSoil) {
/* 413 */       return false;
/*     */     }
/* 415 */     int j = checkBlockLine(aint, aint1);
/*     */     
/* 417 */     if (j == -1)
/* 418 */       return true;
/* 419 */     if (j < 6) {
/* 420 */       return false;
/*     */     }
/* 422 */     this.heightLimit = j;
/* 423 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76487_a(double heightFactor, double widthFactor, double leafDensity)
/*     */   {
/* 433 */     this.heightLimitLimit = ((int)(heightFactor * 12.0D));
/*     */     
/* 435 */     if (this.height > 0.5D) {
/* 436 */       this.leafDistanceLimit = 5;
/*     */     }
/*     */     
/* 439 */     this.scaleWidth = widthFactor;
/* 440 */     this.leafDensity = leafDensity;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5)
/*     */   {
/* 445 */     this.worldObj = par1World;
/* 446 */     long l = par2Random.nextLong();
/* 447 */     this.rand.setSeed(l);
/* 448 */     this.basePos[0] = par3;
/* 449 */     this.basePos[1] = par4;
/* 450 */     this.basePos[2] = par5;
/*     */     
/* 452 */     if (this.heightLimit == 0) {
/* 453 */       this.heightLimit = (5 + this.rand.nextInt(this.heightLimitLimit));
/*     */     }
/*     */     
/* 456 */     if (!validTreeLocation()) {
/* 457 */       return false;
/*     */     }
/* 459 */     generateLeafNodeList();
/* 460 */     generateLeaves();
/* 461 */     generateTrunk();
/* 462 */     generateLeafNodeBases();
/* 463 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WorldGenLargeWitchTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */