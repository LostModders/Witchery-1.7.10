/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityGoblin;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIPickUpBlocks extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   protected final EntityGoblin entity;
/*    */   protected final double range;
/*    */   
/*    */   public EntityAIPickUpBlocks(EntityGoblin entity, double range)
/*    */   {
/* 16 */     this.entity = entity;
/* 17 */     this.range = range;
/* 18 */     func_75248_a(7);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 22 */     return (this.entity != null) && (!this.entity.isWorshipping()) && (this.entity.func_70694_bm() == null) && (this.entity.func_110167_bD()) && (isItemInReachableDistance());
/*    */   }
/*    */   
/*    */   public void func_75249_e() {
/* 26 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.entity.field_70165_t - this.range, this.entity.field_70163_u - this.range, this.entity.field_70161_v - this.range, this.entity.field_70165_t + this.range, this.entity.field_70163_u + this.range, this.entity.field_70161_v + this.range);
/* 27 */     List<EntityItem> items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
/* 28 */     double SPEED = 0.6D;
/* 29 */     for (EntityItem item : items) {
/* 30 */       if (this.entity.func_70661_as().func_75497_a(item, 0.6D)) {
/*    */         break;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_75253_b() {
/* 37 */     return (this.entity != null) && (!this.entity.isWorshipping()) && (this.entity.func_70694_bm() == null) && (this.entity.func_110167_bD()) && (isItemInReachableDistance());
/*    */   }
/*    */   
/*    */   public void func_75246_d() {
/* 41 */     if (this.entity.func_70661_as().func_75500_f()) {
/* 42 */       AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.entity.field_70165_t - this.range, this.entity.field_70163_u - this.range, this.entity.field_70161_v - this.range, this.entity.field_70165_t + this.range, this.entity.field_70163_u + this.range, this.entity.field_70161_v + this.range);
/* 43 */       List<EntityItem> items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
/* 44 */       double SPEED = 0.6D;
/* 45 */       for (EntityItem item : items) {
/* 46 */         if (this.entity.func_70661_as().func_75497_a(item, 0.6D)) {
/*    */           break;
/*    */         }
/*    */       }
/*    */     } else {
/* 51 */       double PICKUP_RANGE = 1.5D;
/* 52 */       AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.entity.field_70165_t - 1.5D, this.entity.field_70163_u - 1.5D, this.entity.field_70161_v - 1.5D, this.entity.field_70165_t + 1.5D, this.entity.field_70163_u + 1.5D, this.entity.field_70161_v + 1.5D);
/* 53 */       List<EntityItem> items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
/* 54 */       if (!items.isEmpty()) {
/* 55 */         this.entity.func_70062_b(0, ((EntityItem)items.get(0)).func_92059_d());
/* 56 */         if (!this.entity.field_70170_p.field_72995_K) {
/* 57 */           ((EntityItem)items.get(0)).func_70106_y();
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected boolean isItemInReachableDistance() {
/* 64 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.entity.field_70165_t - this.range, this.entity.field_70163_u - this.range, this.entity.field_70161_v - this.range, this.entity.field_70165_t + this.range, this.entity.field_70163_u + this.range, this.entity.field_70161_v + this.range);
/* 65 */     List<EntityItem> items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
/* 66 */     double SPEED = 0.1D;
/* 67 */     for (EntityItem item : items) {
/* 68 */       if (this.entity.func_70661_as().func_75494_a(item) != null) {
/* 69 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIPickUpBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */