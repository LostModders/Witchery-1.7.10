/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class InvUtil
/*    */ {
/*    */   public static int getSlotContainingItem(InventoryPlayer inventory, Item item, int damage)
/*    */   {
/* 13 */     for (int k = 0; k < inventory.field_70462_a.length; k++) {
/* 14 */       ItemStack stack = inventory.field_70462_a[k];
/* 15 */       if ((stack != null) && (stack.func_77973_b() == item) && (stack.func_77960_j() == damage)) {
/* 16 */         return k;
/*    */       }
/*    */     }
/*    */     
/* 20 */     return -1;
/*    */   }
/*    */   
/*    */   public static int getSlotContainingItem(InventoryPlayer inventory, Item item) {
/* 24 */     for (int k = 0; k < inventory.field_70462_a.length; k++) {
/* 25 */       ItemStack stack = inventory.field_70462_a[k];
/* 26 */       if ((stack != null) && (stack.func_77973_b() == item)) {
/* 27 */         return k;
/*    */       }
/*    */     }
/*    */     
/* 31 */     return -1;
/*    */   }
/*    */   
/*    */   public static int getSlotContainingItem(IInventory inventory, Item item, int damage) {
/* 35 */     for (int k = 0; k < inventory.func_70302_i_(); k++) {
/* 36 */       ItemStack stack = inventory.func_70301_a(k);
/* 37 */       if ((stack != null) && (stack.func_77973_b() == item) && (stack.func_77960_j() == damage)) {
/* 38 */         return k;
/*    */       }
/*    */     }
/*    */     
/* 42 */     return -1;
/*    */   }
/*    */   
/*    */   public static boolean hasItem(InventoryPlayer inventory, Item item, int damage) {
/* 46 */     return getSlotContainingItem(inventory, item, damage) >= 0;
/*    */   }
/*    */   
/*    */   public static boolean consumeItem(InventoryPlayer inventory, Item item, int damage) {
/* 50 */     int j = getSlotContainingItem(inventory, item, damage);
/*    */     
/* 52 */     if (j < 0) {
/* 53 */       return false;
/*    */     }
/* 55 */     if (--inventory.field_70462_a[j].field_77994_a <= 0) {
/* 56 */       inventory.field_70462_a[j] = null;
/*    */     }
/*    */     
/* 59 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public static int getItemStackCount(IInventory inv)
/*    */   {
/* 65 */     int itemCount = 0;
/* 66 */     if (inv != null) {
/* 67 */       for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 68 */         ItemStack stack = inv.func_70301_a(i);
/* 69 */         if (stack != null) {
/* 70 */           itemCount++;
/*    */         }
/*    */       }
/*    */     }
/* 74 */     return itemCount;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/InvUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */