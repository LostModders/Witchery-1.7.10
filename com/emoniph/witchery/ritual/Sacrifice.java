/*    */ package com.emoniph.witchery.ritual;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Sacrifice
/*    */ {
/*    */   public abstract boolean isMatch(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, ArrayList<Entity> paramArrayList, ArrayList<ItemStack> paramArrayList1);
/*    */   
/*    */   protected static double distance(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ)
/*    */   {
/* 17 */     double dX = firstX - secondX;
/* 18 */     double dY = firstY - secondY;
/* 19 */     double dZ = firstZ - secondZ;
/* 20 */     double distance = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
/* 21 */     return distance;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance) {}
/*    */   
/*    */   public void addDescription(StringBuffer sb) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/Sacrifice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */