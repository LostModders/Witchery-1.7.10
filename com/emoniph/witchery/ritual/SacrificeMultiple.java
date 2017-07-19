/*    */ package com.emoniph.witchery.ritual;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class SacrificeMultiple extends Sacrifice
/*    */ {
/*    */   private final Sacrifice[] sacrifices;
/*    */   
/*    */   public SacrificeMultiple(Sacrifice... sacrifices)
/*    */   {
/* 15 */     this.sacrifices = sacrifices;
/*    */   }
/*    */   
/*    */   public void addDescription(StringBuffer sb)
/*    */   {
/* 20 */     for (Sacrifice sacrifice : this.sacrifices) {
/* 21 */       sacrifice.addDescription(sb);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
/*    */   {
/* 27 */     for (Sacrifice sacrifice : this.sacrifices) {
/* 28 */       if (!sacrifice.isMatch(world, posX, posY, posZ, maxDistance, entities, grassperStacks)) {
/* 29 */         return false;
/*    */       }
/*    */     }
/*    */     
/* 33 */     return true;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance)
/*    */   {
/* 38 */     for (Sacrifice sacrifice : this.sacrifices) {
/* 39 */       sacrifice.addSteps(steps, bounds, maxDistance);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/SacrificeMultiple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */