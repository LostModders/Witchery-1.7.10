/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ public class ItemUtil
/*    */ {
/*    */   public static void registerItem(Item item, String itemName) {
/*  8 */     int index = itemName.indexOf(':');
/*  9 */     if (index != -1) {
/* 10 */       itemName = itemName.substring(index + 1);
/*    */     }
/* 12 */     cpw.mods.fml.common.registry.GameRegistry.registerItem(item, itemName);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/ItemUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */