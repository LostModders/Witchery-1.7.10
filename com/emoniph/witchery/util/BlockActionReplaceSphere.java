/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public abstract class BlockActionReplaceSphere
/*    */ {
/*    */   protected abstract boolean onShouldReplace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Block paramBlock);
/*    */   
/*    */   protected abstract void onReplaceBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Block paramBlock);
/*    */   
/*    */   protected void onComplete() {}
/*    */   
/*    */   public void replaceBlocks(World world, int x0, int y0, int z0, int radius)
/*    */   {
/* 17 */     replaceBlocks(world, x0, y0, z0, x0, y0, z0, radius);
/* 18 */     onComplete();
/*    */   }
/*    */   
/*    */   private void replaceBlocks(World world, int x, int y, int z, int x0, int y0, int z0, int range) {
/* 22 */     double rangeSq = range * range;
/* 23 */     if ((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y) + (z0 - z) * (z0 - z) >= rangeSq) {
/* 24 */       return;
/*    */     }
/* 26 */     if (replaceBlock(world, x + 1, y, z)) {
/* 27 */       replaceBlocks(world, x + 1, y, z, x0, y0, z0, range);
/*    */     }
/*    */     
/* 30 */     if (replaceBlock(world, x - 1, y, z)) {
/* 31 */       replaceBlocks(world, x - 1, y, z, x0, y0, z0, range);
/*    */     }
/*    */     
/* 34 */     if (replaceBlock(world, x, y, z + 1)) {
/* 35 */       replaceBlocks(world, x, y, z + 1, x0, y0, z0, range);
/*    */     }
/*    */     
/* 38 */     if (replaceBlock(world, x, y, z - 1)) {
/* 39 */       replaceBlocks(world, x, y, z - 1, x0, y0, z0, range);
/*    */     }
/*    */     
/* 42 */     if (replaceBlock(world, x, y + 1, z)) {
/* 43 */       replaceBlocks(world, x, y + 1, z, x0, y0, z0, range);
/*    */     }
/*    */     
/* 46 */     if (replaceBlock(world, x, y - 1, z)) {
/* 47 */       replaceBlocks(world, x, y - 1, z, x0, y0, z0, range);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean replaceBlock(World world, int x, int y, int z) {
/* 52 */     Block block = world.func_147439_a(x, y, z);
/* 53 */     if (onShouldReplace(world, x, y, z, block)) {
/* 54 */       onReplaceBlock(world, x, y, z, block);
/* 55 */       return true;
/*    */     }
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BlockActionReplaceSphere.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */