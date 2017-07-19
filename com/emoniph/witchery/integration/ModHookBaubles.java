/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import baubles.api.BaublesApi;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ModHookBaubles extends ModHook
/*    */ {
/*    */   public String getModID()
/*    */   {
/* 13 */     return "Baubles";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void doInit() {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected void doPostInit() {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
/*    */   
/*    */ 
/*    */   public boolean canVampireBeKilled(EntityPlayer player)
/*    */   {
/* 31 */     return IntegrateBaubles.canVampireBeVilled(player);
/*    */   }
/*    */   
/* 34 */   private static final String[] BANNED_ITEMS = { "item.superLavaPendant", "item.lavaPendant", "item.odinRing", "item.aesirRing" };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private static class IntegrateBaubles
/*    */   {
/*    */     public static boolean canVampireBeVilled(EntityPlayer player)
/*    */     {
/* 44 */       IInventory inv = BaublesApi.getBaubles(player);
/* 45 */       if (inv == null) {
/* 46 */         return false;
/*    */       }
/*    */       
/*    */ 
/* 50 */       for (int slot = 0; slot < inv.func_70302_i_(); slot++) {
/* 51 */         ItemStack stack = inv.func_70301_a(slot);
/* 52 */         if (stack != null) {
/* 53 */           for (String badItem : ModHookBaubles.BANNED_ITEMS) {
/* 54 */             if (badItem.equals(stack.func_77977_a())) {
/* 55 */               return true;
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 61 */       return false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookBaubles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */