/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAISitAndStay
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityTameable theEntity;
/*    */   
/*    */   public EntityAISitAndStay(EntityTameable par1EntityTameable)
/*    */   {
/* 16 */     this.theEntity = par1EntityTameable;
/* 17 */     func_75248_a(5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 25 */     return this.theEntity.func_70906_o();
/*    */   }
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
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 50 */     this.theEntity.func_70661_as().func_75499_g();
/*    */   }
/*    */   
/*    */   public void func_75251_c() {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAISitAndStay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */