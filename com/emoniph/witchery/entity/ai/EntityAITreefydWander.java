/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityTreefyd;
/*    */ 
/*    */ public class EntityAITreefydWander extends net.minecraft.entity.ai.EntityAIWander
/*    */ {
/*    */   private final EntityTreefyd treefyd;
/*    */   
/*    */   public EntityAITreefydWander(EntityTreefyd treefyd, double speed)
/*    */   {
/* 11 */     super(treefyd, speed);
/* 12 */     this.treefyd = treefyd;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 17 */     return (!this.treefyd.isSentinal()) && (super.func_75250_a());
/*    */   }
/*    */   
/*    */   public boolean func_75253_b()
/*    */   {
/* 22 */     return (!this.treefyd.isSentinal()) && (super.func_75253_b());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAITreefydWander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */