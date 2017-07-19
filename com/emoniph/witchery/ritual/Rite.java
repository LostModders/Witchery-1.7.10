/*    */ package com.emoniph.witchery.ritual;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class Rite
/*    */ {
/*    */   protected boolean canRelocate;
/*    */   
/*    */   public Rite()
/*    */   {
/* 15 */     this.canRelocate = false;
/*    */   }
/*    */   
/*    */   public abstract void addSteps(ArrayList<RitualStep> paramArrayList, int paramInt);
/*    */   
/*    */   public ArrayList<EntityItem> getItemsInRadius(World world, int x, int y, int z, float radius) {
/* 21 */     float RADIUS_SQ = radius * radius;
/* 22 */     double midX = 0.5D + x;
/* 23 */     double midZ = 0.5D + z;
/* 24 */     ArrayList<EntityItem> resultList = new ArrayList();
/* 25 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(midX - radius, y, midZ - radius, midX + radius, 1.0D + y, midZ + radius);
/* 26 */     List items = world.func_72872_a(EntityItem.class, bounds);
/* 27 */     for (Object obj : items) {
/* 28 */       EntityItem entity = (EntityItem)obj;
/* 29 */       if (entity.func_70092_e(midX, y, midZ) <= RADIUS_SQ) {
/* 30 */         resultList.add(entity);
/*    */       }
/*    */     }
/* 33 */     return resultList;
/*    */   }
/*    */   
/*    */   public boolean relocatable() {
/* 37 */     return this.canRelocate;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/Rite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */