/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.IMerchant;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class EntityAITradePlayerGeneric extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   private IMerchant merchant;
/*    */   private EntityLiving entity;
/*    */   
/*    */   public EntityAITradePlayerGeneric(IMerchant merchant, EntityLiving entity)
/*    */   {
/* 14 */     this.merchant = merchant;
/* 15 */     this.entity = entity;
/* 16 */     func_75248_a(5);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 20 */     if (!this.entity.func_70089_S())
/* 21 */       return false;
/* 22 */     if (this.entity.func_70090_H())
/* 23 */       return false;
/* 24 */     if (!this.entity.field_70122_E)
/* 25 */       return false;
/* 26 */     if (this.entity.field_70133_I) {
/* 27 */       return false;
/*    */     }
/* 29 */     EntityPlayer entityplayer = this.merchant.func_70931_l_();
/* 30 */     return this.entity.func_70068_e(entityplayer) > 16.0D ? false : entityplayer == null ? false : entityplayer.field_71070_bA instanceof net.minecraft.inventory.Container;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 36 */     this.entity.func_70661_as().func_75499_g();
/*    */   }
/*    */   
/*    */   public void func_75251_c() {
/* 40 */     this.merchant.func_70932_a_((EntityPlayer)null);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAITradePlayerGeneric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */