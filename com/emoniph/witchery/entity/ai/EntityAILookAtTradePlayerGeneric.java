/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.IMerchant;
/*    */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*    */ 
/*    */ public class EntityAILookAtTradePlayerGeneric extends EntityAIWatchClosest
/*    */ {
/*    */   private final IMerchant merchant;
/*    */   
/*    */   public EntityAILookAtTradePlayerGeneric(net.minecraft.entity.EntityLiving entity, IMerchant merchant)
/*    */   {
/* 12 */     super(entity, net.minecraft.entity.player.EntityPlayer.class, 8.0F);
/* 13 */     this.merchant = merchant;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 17 */     net.minecraft.entity.player.EntityPlayer customer = this.merchant.func_70931_l_();
/* 18 */     if (customer != null) {
/* 19 */       this.field_75334_a = customer;
/* 20 */       return true;
/*    */     }
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAILookAtTradePlayerGeneric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */