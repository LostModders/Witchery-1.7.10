/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ 
/*    */ public class EntityPosition
/*    */ {
/*    */   public final double x;
/*    */   public final double y;
/*    */   public final double z;
/*    */   
/*    */   public EntityPosition(int x, int y, int z)
/*    */   {
/* 14 */     this(0.5D + x, 0.5D + y, 0.5D + z);
/*    */   }
/*    */   
/*    */   public EntityPosition(double x, double y, double z) {
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.z = z;
/*    */   }
/*    */   
/*    */   public EntityPosition(BlockPosition position) {
/* 24 */     this.x = position.x;
/* 25 */     this.y = position.y;
/* 26 */     this.z = position.z;
/*    */   }
/*    */   
/*    */   public EntityPosition(Entity entity) {
/* 30 */     this(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*    */   }
/*    */   
/*    */   public EntityPosition(MovingObjectPosition mop) {
/* 34 */     if (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) {
/* 35 */       this.x = mop.field_72308_g.field_70165_t;
/* 36 */       this.y = mop.field_72308_g.field_70163_u;
/* 37 */       this.z = mop.field_72308_g.field_70161_v;
/* 38 */     } else if (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) {
/* 39 */       this.x = (mop.field_72311_b + 0.5D);
/* 40 */       this.y = (mop.field_72312_c + 0.5D);
/* 41 */       this.z = (mop.field_72309_d + 0.5D);
/*    */     } else {
/* 43 */       this.x = 0.0D;
/* 44 */       this.y = 0.0D;
/* 45 */       this.z = 0.0D;
/*    */     }
/*    */   }
/*    */   
/*    */   public double getDistanceSqToEntity(Entity entity) {
/* 50 */     double d0 = this.x - entity.field_70165_t;
/* 51 */     double d1 = this.y - entity.field_70163_u;
/* 52 */     double d2 = this.z - entity.field_70161_v;
/* 53 */     return d0 * d0 + d1 * d1 + d2 * d2;
/*    */   }
/*    */   
/*    */   public net.minecraft.util.AxisAlignedBB getBounds(double radius) {
/* 57 */     net.minecraft.util.AxisAlignedBB aabb = net.minecraft.util.AxisAlignedBB.func_72330_a(this.x - radius, this.y - radius, this.z - radius, this.x + radius, this.y + radius, this.z + radius);
/* 58 */     return aabb;
/*    */   }
/*    */   
/*    */   public boolean occupiedBy(Entity entity) {
/* 62 */     return (entity != null) && (entity.field_70165_t == this.x) && (entity.field_70163_u == this.y) && (entity.field_70161_v == this.z);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/EntityPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */