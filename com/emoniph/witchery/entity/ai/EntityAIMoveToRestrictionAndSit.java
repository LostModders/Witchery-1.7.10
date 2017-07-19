/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class EntityAIMoveToRestrictionAndSit extends EntityAIBase
/*    */ {
/*    */   private EntityCovenWitch theEntity;
/*    */   private double movePosX;
/*    */   private double movePosY;
/*    */   private double movePosZ;
/*    */   private double movementSpeed;
/*    */   
/*    */   public EntityAIMoveToRestrictionAndSit(EntityCovenWitch p_i2347_1_, double p_i2347_2_)
/*    */   {
/* 19 */     this.theEntity = p_i2347_1_;
/* 20 */     this.movementSpeed = p_i2347_2_;
/* 21 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 26 */     if (this.theEntity.func_110173_bK()) {
/* 27 */       return false;
/*    */     }
/* 29 */     ChunkCoordinates chunkcoordinates = this.theEntity.func_110172_bL();
/* 30 */     Vec3 vec3 = net.minecraft.entity.ai.RandomPositionGenerator.func_75464_a(this.theEntity, 16, 7, Vec3.func_72443_a(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c));
/*    */     
/*    */ 
/*    */ 
/* 34 */     if (vec3 == null) {
/* 35 */       return false;
/*    */     }
/* 37 */     this.movePosX = vec3.field_72450_a;
/* 38 */     this.movePosY = vec3.field_72448_b;
/* 39 */     this.movePosZ = vec3.field_72449_c;
/* 40 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 47 */     return !this.theEntity.func_70661_as().func_75500_f();
/*    */   }
/*    */   
/*    */   public void func_75249_e()
/*    */   {
/* 52 */     this.theEntity.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 58 */     super.func_75251_c();
/* 59 */     if (this.theEntity.func_110173_bK()) {
/* 60 */       this.theEntity.standStill();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIMoveToRestrictionAndSit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */