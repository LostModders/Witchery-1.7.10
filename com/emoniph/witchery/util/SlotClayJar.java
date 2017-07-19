/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotClayJar extends Slot
/*    */ {
/*    */   public SlotClayJar(IInventory par2IInventory, int par3, int par4, int par5)
/*    */   {
/* 11 */     super(par2IInventory, par3, par4, par5);
/*    */   }
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack)
/*    */   {
/* 16 */     return (itemstack != null) && (com.emoniph.witchery.Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(itemstack));
/*    */   }
/*    */   
/*    */   public int func_75219_a()
/*    */   {
/* 21 */     return 64;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/SlotClayJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */