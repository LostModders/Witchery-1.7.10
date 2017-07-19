/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EffectSpiral {
/*    */   private final ISpiralBlockAction action;
/*    */   
/*    */   public EffectSpiral(ISpiralBlockAction action) {
/*  9 */     this.action = action;
/*    */   }
/*    */   
/*    */   public void apply(World world, int midX, int midY, int midZ, int dimX, int dimZ)
/*    */   {
/* 14 */     this.action.onSpiralActionStart(world, midX, midY, midZ);
/*    */     
/* 16 */     int x = 0;
/* 17 */     int z = 0;
/* 18 */     int dx = 0;
/* 19 */     int dz = -1;
/* 20 */     int t = Math.max(dimX, dimZ);
/* 21 */     int maxI = t * t;
/* 22 */     for (int i = 0; i < maxI; i++) {
/* 23 */       if ((-dimX / 2 <= x) && (x <= dimX / 2) && (-dimZ / 2 <= z) && (z <= dimZ / 2) && 
/* 24 */         (!this.action.onSpiralBlockAction(world, midX + x, midY, midZ + z))) {
/*    */         break;
/*    */       }
/*    */       
/* 28 */       if ((x == z) || ((x < 0) && (x == -z)) || ((x > 0) && (x == 1 - z))) {
/* 29 */         t = dx;
/* 30 */         dx = -dz;
/* 31 */         dz = t;
/*    */       }
/* 33 */       x += dx;
/* 34 */       z += dz;
/*    */     }
/*    */     
/* 37 */     this.action.onSpiralActionStop(world, midX, midY, midZ);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/EffectSpiral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */