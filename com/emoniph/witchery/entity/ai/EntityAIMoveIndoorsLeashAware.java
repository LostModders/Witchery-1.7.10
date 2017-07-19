/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ 
/*    */ public class EntityAIMoveIndoorsLeashAware extends net.minecraft.entity.ai.EntityAIMoveIndoors
/*    */ {
/*    */   private EntityCreature creature;
/*    */   
/*    */   public EntityAIMoveIndoorsLeashAware(EntityCreature creature) {
/* 10 */     super(creature);
/* 11 */     this.creature = creature;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 16 */     return (this.creature != null) && (!this.creature.func_110167_bD()) && (super.func_75250_a());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIMoveIndoorsLeashAware.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */