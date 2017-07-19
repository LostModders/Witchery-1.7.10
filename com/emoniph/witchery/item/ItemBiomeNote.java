/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemBiomeNote extends ItemBase
/*    */ {
/*    */   public ItemBiomeNote() {
/*  8 */     func_77627_a(true);
/*  9 */     func_77656_e(0);
/*    */   }
/*    */   
/*    */   public String func_77653_i(ItemStack stack)
/*    */   {
/* 14 */     String name = super.func_77653_i(stack);
/* 15 */     net.minecraft.world.biome.BiomeGenBase biome = ItemBook.getSelectedBiome(stack.func_77960_j());
/* 16 */     if (biome != null) {
/* 17 */       return String.format(name, new Object[] { biome.field_76791_y });
/*    */     }
/* 19 */     return String.format(name, new Object[] { "" }).trim();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBiomeNote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */