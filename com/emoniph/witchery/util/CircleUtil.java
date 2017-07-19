/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class CircleUtil
/*    */ {
/*    */   public static boolean isSmallCircle(World world, int x, int y, int z, Block block)
/*    */   {
/* 10 */     int[][] circle = { { x, z - 2 }, { x + 1, z - 2 }, { x + 2, z - 1 }, { x + 2, z }, { x + 2, z + 1 }, { x + 1, z + 2 }, { x, z + 2 }, { x - 1, z + 2 }, { x - 2, z + 1 }, { x - 2, z }, { x - 2, z - 1 }, { x - 1, z - 2 } };
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 23 */     for (int[] coord : circle) {
/* 24 */       if (world.func_147439_a(coord[0], y, coord[1]) != block) {
/* 25 */         return false;
/*    */       }
/*    */     }
/* 28 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean isMediumCircle(World world, int x, int y, int z, Block block) {
/* 32 */     int[][] circle = { { x, z - 4 }, { x + 1, z - 4 }, { x + 2, z - 4 }, { x + 3, z - 3 }, { x + 4, z - 2 }, { x + 4, z - 1 }, { x + 4, z }, { x + 4, z + 1 }, { x + 4, z + 2 }, { x + 3, z + 3 }, { x + 2, z + 4 }, { x + 1, z + 4 }, { x, z + 4 }, { x - 1, z + 4 }, { x - 2, z + 4 }, { x - 3, z + 3 }, { x - 4, z + 2 }, { x - 4, z + 1 }, { x - 4, z }, { x - 4, z - 1 }, { x - 4, z - 2 }, { x - 3, z - 3 }, { x - 2, z - 4 }, { x - 1, z - 4 } };
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 59 */     for (int[] coord : circle) {
/* 60 */       if (world.func_147439_a(coord[0], y, coord[1]) != block) {
/* 61 */         return false;
/*    */       }
/*    */     }
/* 64 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/CircleUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */