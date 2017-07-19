/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*    */ 
/*    */ public class EntityAIAttackOnCollide2 extends EntityAIAttackOnCollide
/*    */ {
/*    */   private Class clazz;
/*    */   
/*    */   public EntityAIAttackOnCollide2(net.minecraft.entity.EntityCreature creature, Class classToAttack, double speedTowardsTarget, boolean longMemory)
/*    */   {
/* 11 */     super(creature, classToAttack, speedTowardsTarget, longMemory);
/* 12 */     this.clazz = classToAttack;
/*    */   }
/*    */   
/*    */   public boolean appliesToClass(Class victimClass) {
/* 16 */     return victimClass == this.clazz;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIAttackOnCollide2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */