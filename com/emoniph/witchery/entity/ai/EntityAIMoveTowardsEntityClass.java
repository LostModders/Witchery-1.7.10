/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIMoveTowardsEntityClass extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntity;
/*    */   private EntityLivingBase targetEntity;
/*    */   private Class<? extends EntityLiving> targetType;
/*    */   private double movePosX;
/*    */   private double movePosY;
/*    */   private double movePosZ;
/*    */   private double speed;
/*    */   private float maxTargetDistance;
/*    */   private float minTargetDistance;
/*    */   
/*    */   public EntityAIMoveTowardsEntityClass(EntityCreature par1EntityCreature, Class<? extends EntityLiving> targetType, double par2, float par4, float par5)
/*    */   {
/* 27 */     this.theEntity = par1EntityCreature;
/* 28 */     this.targetType = targetType;
/* 29 */     this.speed = par2;
/* 30 */     this.minTargetDistance = par4;
/* 31 */     this.maxTargetDistance = par5;
/*    */     
/* 33 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */   private EntityLiving getDistanceSqToPartner() {
/* 37 */     double R = this.maxTargetDistance;
/* 38 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.theEntity.field_70165_t - R, this.theEntity.field_70163_u - R, this.theEntity.field_70161_v - R, this.theEntity.field_70165_t + R, this.theEntity.field_70163_u + R, this.theEntity.field_70161_v + R);
/*    */     
/* 40 */     java.util.List mogs = this.theEntity.field_70170_p.func_72872_a(this.targetType, bb);
/* 41 */     double minDistance = Double.MAX_VALUE;
/* 42 */     EntityLiving target = null;
/* 43 */     for (Object obj : mogs) {
/* 44 */       EntityLiving mog = (EntityLiving)obj;
/* 45 */       double distance = this.theEntity.func_70068_e(mog);
/* 46 */       if (distance < minDistance) {
/* 47 */         minDistance = distance;
/* 48 */         target = mog;
/*    */       }
/*    */     }
/* 51 */     return target;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 55 */     if (this.theEntity.field_70170_p.field_73012_v.nextInt(20) != 0) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     this.targetEntity = getDistanceSqToPartner();
/*    */     
/* 61 */     if (this.targetEntity == null) {
/* 62 */       return false;
/*    */     }
/*    */     
/* 65 */     double dist = this.targetEntity.func_70068_e(this.theEntity);
/*    */     
/* 67 */     if (dist > this.maxTargetDistance * this.maxTargetDistance)
/* 68 */       return false;
/* 69 */     if (dist < this.minTargetDistance * this.minTargetDistance) {
/* 70 */       return false;
/*    */     }
/* 72 */     Vec3 vec3 = RandomPositionGenerator.func_75464_a(this.theEntity, 16, 7, Vec3.func_72443_a(this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v));
/*    */     
/* 74 */     if (vec3 == null) {
/* 75 */       return false;
/*    */     }
/* 77 */     this.movePosX = vec3.field_72450_a;
/* 78 */     this.movePosY = vec3.field_72448_b;
/* 79 */     this.movePosZ = vec3.field_72449_c;
/* 80 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 86 */     return (!this.theEntity.func_70661_as().func_75500_f()) && (this.targetEntity.func_70089_S()) && (this.targetEntity.func_70068_e(this.theEntity) < this.maxTargetDistance * this.maxTargetDistance);
/*    */   }
/*    */   
/*    */   public void func_75251_c()
/*    */   {
/* 91 */     this.targetEntity = null;
/*    */   }
/*    */   
/*    */   public void func_75249_e() {
/* 95 */     this.theEntity.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.speed);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIMoveTowardsEntityClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */