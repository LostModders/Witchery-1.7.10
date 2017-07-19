/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public enum ClothColor
/*    */ {
/*  7 */   WHITE(0), 
/*  8 */   ORANGE(1), 
/*  9 */   MAGENTA(2), 
/* 10 */   LIGHT_BLUE(3), 
/* 11 */   YELLOW(4), 
/* 12 */   LIME(5), 
/* 13 */   PINK(6), 
/* 14 */   GRAY(7), 
/* 15 */   LIGHT_GRAY(8), 
/* 16 */   CYAN(9), 
/* 17 */   PURPLE(10), 
/* 18 */   BLUE(11), 
/* 19 */   BROWN(12), 
/* 20 */   GREEN(13), 
/* 21 */   RED(14), 
/* 22 */   BLACK(15);
/*    */   
/*    */   public final int damageValue;
/*    */   
/*    */   private ClothColor(int damageValue) {
/* 27 */     this.damageValue = damageValue;
/*    */   }
/*    */   
/*    */   public ItemStack createStack() {
/* 31 */     return createStack(1);
/*    */   }
/*    */   
/*    */   private ItemStack createStack(int quantity) {
/* 35 */     return new ItemStack(net.minecraft.init.Blocks.field_150325_L, quantity, this.damageValue);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/ClothColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */