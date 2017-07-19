/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityDemon;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ 
/*    */ public class EntityAIDemonicBarginPlayer extends EntityAIBase
/*    */ {
/*    */   private EntityDemon trader;
/*    */   
/*    */   public EntityAIDemonicBarginPlayer(EntityDemon trader)
/*    */   {
/* 15 */     this.trader = trader;
/* 16 */     func_75248_a(5);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 24 */     if (!this.trader.func_70089_S())
/*    */     {
/* 26 */       return false;
/*    */     }
/* 28 */     if (this.trader.func_70090_H())
/*    */     {
/* 30 */       return false;
/*    */     }
/* 32 */     if (!this.trader.field_70122_E)
/*    */     {
/* 34 */       return false;
/*    */     }
/* 36 */     if (this.trader.field_70133_I)
/*    */     {
/* 38 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 42 */     EntityPlayer entityplayer = this.trader.func_70931_l_();
/* 43 */     return this.trader.func_70068_e(entityplayer) > 16.0D ? false : entityplayer == null ? false : entityplayer.field_71070_bA instanceof Container;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 52 */     this.trader.func_70661_as().func_75499_g();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75251_c()
/*    */   {
/* 60 */     this.trader.func_70932_a_((EntityPlayer)null);
/* 61 */     this.trader.field_70715_bh.func_75774_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIDemonicBarginPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */