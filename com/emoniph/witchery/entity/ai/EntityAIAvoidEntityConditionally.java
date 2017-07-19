/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
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
/*    */ public class EntityAIAvoidEntityConditionally
/*    */   extends EntityAIAvoidEntity
/*    */ {
/*    */   private final IAvoidEntities condition;
/*    */   
/*    */   public EntityAIAvoidEntityConditionally(EntityCreature entity, Class targetClass, float distanceFromEntity, double farSpeed, double nearSpeed, IAvoidEntities condition)
/*    */   {
/* 22 */     super(entity, targetClass, distanceFromEntity, farSpeed, nearSpeed);
/* 23 */     this.condition = condition;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 27 */     return (super.func_75250_a()) && (!this.condition.shouldAvoid());
/*    */   }
/*    */   
/*    */   public static abstract interface IAvoidEntities
/*    */   {
/*    */     public abstract boolean shouldAvoid();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIAvoidEntityConditionally.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */