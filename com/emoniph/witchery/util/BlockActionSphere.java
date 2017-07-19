/*     */ package com.emoniph.witchery.util;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockActionSphere
/*     */ {
/*     */   protected abstract void onBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   protected void onComplete() {}
/*     */   
/*     */   public void drawHollowSphere(World world, int x0, int y0, int z0, int radius)
/*     */   {
/*  17 */     if (radius == 1) {
/*  18 */       drawPixel(world, x0, z0, y0);
/*     */     } else {
/*  20 */       radius--;
/*  21 */       int x = radius;
/*  22 */       int y = 0;
/*  23 */       int radiusError = 1 - x;
/*     */       
/*  25 */       while (x >= y) {
/*  26 */         drawCircle(world, x0, y0, z0, y, x, radiusError);
/*  27 */         y++;
/*  28 */         if (radiusError < 0) {
/*  29 */           radiusError += 2 * y + 1;
/*     */         } else {
/*  31 */           x--;
/*  32 */           radiusError += 2 * (y - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  37 */     onComplete();
/*     */   }
/*     */   
/*     */   private boolean drawCircle(World world, int x0, int y0, int z0, int y1, int radius, int error0)
/*     */   {
/*  42 */     int x = radius;
/*  43 */     int z = 0;
/*  44 */     int radiusError = error0;
/*     */     
/*  46 */     while (x >= z)
/*     */     {
/*  48 */       drawPixel(world, x0 + x, z0 + z, y0 + y1);
/*  49 */       drawPixel(world, x0 - x, z0 + z, y0 + y1);
/*  50 */       drawPixel(world, x0 + x, z0 + z, y0 - y1);
/*  51 */       drawPixel(world, x0 - x, z0 + z, y0 - y1);
/*  52 */       drawPixel(world, x0 + x, z0 - z, y0 + y1);
/*  53 */       drawPixel(world, x0 - x, z0 - z, y0 + y1);
/*  54 */       drawPixel(world, x0 + x, z0 - z, y0 - y1);
/*  55 */       drawPixel(world, x0 - x, z0 - z, y0 - y1);
/*     */       
/*  57 */       drawPixel(world, x0 + z, z0 + x, y0 + y1);
/*  58 */       drawPixel(world, x0 - z, z0 + x, y0 + y1);
/*  59 */       drawPixel(world, x0 + z, z0 + x, y0 - y1);
/*  60 */       drawPixel(world, x0 - z, z0 + x, y0 - y1);
/*  61 */       drawPixel(world, x0 + z, z0 - x, y0 + y1);
/*  62 */       drawPixel(world, x0 - z, z0 - x, y0 + y1);
/*  63 */       drawPixel(world, x0 + z, z0 - x, y0 - y1);
/*  64 */       drawPixel(world, x0 - z, z0 - x, y0 - y1);
/*     */       
/*  66 */       drawPixel(world, x0 + y1, z0 + z, y0 + x);
/*  67 */       drawPixel(world, x0 - y1, z0 + z, y0 + x);
/*  68 */       drawPixel(world, x0 + y1, z0 + z, y0 - x);
/*  69 */       drawPixel(world, x0 - y1, z0 + z, y0 - x);
/*  70 */       drawPixel(world, x0 + y1, z0 - z, y0 + x);
/*  71 */       drawPixel(world, x0 - y1, z0 - z, y0 + x);
/*  72 */       drawPixel(world, x0 + y1, z0 - z, y0 - x);
/*  73 */       drawPixel(world, x0 - y1, z0 - z, y0 - x);
/*     */       
/*  75 */       drawPixel(world, x0 + z, z0 + y1, y0 + x);
/*  76 */       drawPixel(world, x0 - z, z0 + y1, y0 + x);
/*  77 */       drawPixel(world, x0 + z, z0 + y1, y0 - x);
/*  78 */       drawPixel(world, x0 - z, z0 + y1, y0 - x);
/*  79 */       drawPixel(world, x0 + z, z0 - y1, y0 + x);
/*  80 */       drawPixel(world, x0 - z, z0 - y1, y0 + x);
/*  81 */       drawPixel(world, x0 + z, z0 - y1, y0 - x);
/*  82 */       drawPixel(world, x0 - z, z0 - y1, y0 - x);
/*     */       
/*  84 */       z++;
/*     */       
/*  86 */       if (radiusError < 0) {
/*  87 */         radiusError += 2 * z + 1;
/*     */       } else {
/*  89 */         x--;
/*  90 */         radiusError += 2 * (z - x + 1);
/*     */       }
/*     */     }
/*     */     
/*  94 */     return true;
/*     */   }
/*     */   
/*     */   public void drawFilledSphere(World world, int x0, int y0, int z0, int radius) {
/*  98 */     if (radius == 1) {
/*  99 */       drawPixel(world, x0, z0, y0);
/*     */     } else {
/* 101 */       int radiusSq = radius * radius;
/* 102 */       for (int x = x0 - radius; x <= x0 + radius; x++) {
/* 103 */         for (int z = z0 - radius; z <= z0 + radius; z++) {
/* 104 */           for (int y = y0 - radius; y <= y0 + radius; y++) {
/* 105 */             if (Coord.distanceSq(x, y, z, x0, y0, z0) < radiusSq - 1) {
/* 106 */               drawPixel(world, x, z, y);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 113 */     onComplete();
/*     */   }
/*     */   
/*     */   private void drawPixel(World world, int x, int z, int y) {
/* 117 */     onBlock(world, x, y, z);
/*     */   }
/*     */   
/*     */   protected void fillWith(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock)
/*     */   {
/* 122 */     fillHalfWithAirY(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
/* 123 */     fillHalfWithAirY(world, posX, posY - 1, posZ, -1, radius, fillBlock, edgeBlock);
/*     */   }
/*     */   
/*     */   private void fillHalfWithAirY(World world, int posX, int posY, int posZ, int dy, int radius, Block fillBlock, Block edgeBlock) {
/* 127 */     for (int y = 0; y <= radius; y++) {
/* 128 */       int realY = posY + y * dy;
/* 129 */       if (world.func_147439_a(posX, realY, posZ) == edgeBlock) {
/*     */         break;
/*     */       }
/* 132 */       fillSliceWithAir(world, posX, realY, posZ, radius, fillBlock, edgeBlock);
/*     */     }
/*     */   }
/*     */   
/*     */   private void fillSliceWithAir(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock) {
/* 137 */     fillHalfWithAirX(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
/* 138 */     fillHalfWithAirX(world, posX - 1, posY, posZ, -1, radius, fillBlock, edgeBlock);
/*     */   }
/*     */   
/*     */   private void fillHalfWithAirX(World world, int posX, int posY, int posZ, int dx, int radius, Block fillBlock, Block edgeBlock) {
/* 142 */     for (int x = 0; x <= radius; x++) {
/* 143 */       int realX = posX + x * dx;
/* 144 */       if (world.func_147439_a(realX, x, posZ) == edgeBlock) {
/*     */         break;
/*     */       }
/* 147 */       fillLineWithAir(world, realX, posY, posZ, radius, fillBlock, edgeBlock);
/*     */     }
/*     */   }
/*     */   
/*     */   private void fillLineWithAir(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock) {
/* 152 */     fillHalfWithAirZ(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
/* 153 */     fillHalfWithAirZ(world, posX, posY, posZ - 1, -1, radius, fillBlock, edgeBlock);
/*     */   }
/*     */   
/*     */   private void fillHalfWithAirZ(World world, int posX, int posY, int posZ, int dz, int radius, Block fillBlock, Block edgeBlock) {
/* 157 */     for (int z = 0; z <= radius; z++) {
/* 158 */       int realZ = posZ + z * dz;
/* 159 */       Block foundBlock = world.func_147439_a(posX, posY, realZ);
/* 160 */       if (foundBlock == edgeBlock) {
/*     */         break;
/*     */       }
/* 163 */       if ((foundBlock != fillBlock) && ((foundBlock == Blocks.field_150355_j) || (foundBlock == Blocks.field_150358_i) || (foundBlock == Witchery.Blocks.BREW_GAS) || (foundBlock == Witchery.Blocks.BREW_LIQUID))) {
/* 164 */         world.func_147449_b(posX, posY, realZ, fillBlock);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BlockActionSphere.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */