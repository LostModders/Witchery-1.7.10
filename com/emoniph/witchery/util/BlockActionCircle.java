/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class BlockActionCircle
/*    */ {
/*    */   public abstract void onBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
/*    */   
/*    */   public void onComplete() {}
/*    */   
/*    */   public void processHollowCircle(World world, int x0, int y0, int z0, int radius) {
/* 12 */     if (radius == 1) {
/* 13 */       drawPixel(world, x0, z0, y0);
/*    */     } else {
/* 15 */       radius--;
/* 16 */       int x = radius;
/* 17 */       int z = 0;
/* 18 */       int radiusError = 1 - x;
/*    */       
/* 20 */       while (x >= z) {
/* 21 */         drawPixel(world, x + x0, z + z0, y0);
/* 22 */         drawPixel(world, z + x0, x + z0, y0);
/* 23 */         drawPixel(world, -x + x0, z + z0, y0);
/* 24 */         drawPixel(world, -z + x0, x + z0, y0);
/* 25 */         drawPixel(world, -x + x0, -z + z0, y0);
/* 26 */         drawPixel(world, -z + x0, -x + z0, y0);
/* 27 */         drawPixel(world, x + x0, -z + z0, y0);
/* 28 */         drawPixel(world, z + x0, -x + z0, y0);
/*    */         
/* 30 */         z++;
/* 31 */         if (radiusError < 0) {
/* 32 */           radiusError += 2 * z + 1;
/*    */         } else {
/* 34 */           x--;
/* 35 */           radiusError += 2 * (z - x + 1);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 40 */     onComplete();
/*    */   }
/*    */   
/*    */   public void processFilledCircle(World world, int x0, int y0, int z0, int radius) {
/* 44 */     if (radius == 1) {
/* 45 */       drawPixel(world, x0, z0, y0);
/*    */     } else {
/* 47 */       radius--;
/* 48 */       int x = radius;
/* 49 */       int z = 0;
/* 50 */       int radiusError = 1 - x;
/*    */       
/* 52 */       int obsidianMelted = 0;
/* 53 */       while (x >= z) {
/* 54 */         drawLine(world, -x + x0, x + x0, z + z0, y0);
/* 55 */         drawLine(world, -z + x0, z + x0, x + z0, y0);
/* 56 */         drawLine(world, -x + x0, x + x0, -z + z0, y0);
/* 57 */         drawLine(world, -z + x0, z + x0, -x + z0, y0);
/*    */         
/* 59 */         z++;
/*    */         
/* 61 */         if (radiusError < 0) {
/* 62 */           radiusError += 2 * z + 1;
/*    */         } else {
/* 64 */           x--;
/* 65 */           radiusError += 2 * (z - x + 1);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 70 */     onComplete();
/*    */   }
/*    */   
/*    */   private void drawLine(World world, int x1, int x2, int z, int y) {
/* 74 */     for (int x = x1; x <= x2; x++) {
/* 75 */       drawPixel(world, x, z, y);
/*    */     }
/*    */   }
/*    */   
/*    */   private void drawPixel(World world, int x, int z, int y) {
/* 80 */     onBlock(world, x, y, z);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BlockActionCircle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */