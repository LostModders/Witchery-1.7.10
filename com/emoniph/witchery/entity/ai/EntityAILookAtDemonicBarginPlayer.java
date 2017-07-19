/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityDemon;
/*    */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class EntityAILookAtDemonicBarginPlayer
/*    */   extends EntityAIWatchClosest
/*    */ {
/*    */   private final EntityDemon theMerchant;
/*    */   
/*    */   public EntityAILookAtDemonicBarginPlayer(EntityDemon trader)
/*    */   {
/* 14 */     super(trader, EntityPlayer.class, 8.0F);
/* 15 */     this.theMerchant = trader;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 23 */     if (this.theMerchant.isTrading())
/*    */     {
/* 25 */       this.field_75334_a = this.theMerchant.func_70931_l_();
/* 26 */       return true;
/*    */     }
/*    */     
/*    */ 
/* 30 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAILookAtDemonicBarginPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */