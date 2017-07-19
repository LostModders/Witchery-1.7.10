/*    */ package com.emoniph.witchery.crafting;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.item.ItemBook;
/*    */ import com.emoniph.witchery.util.InvUtil;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.ShapelessRecipes;
/*    */ 
/*    */ public class RecipeShapelessBiomeCopy extends ShapelessRecipes
/*    */ {
/*    */   public RecipeShapelessBiomeCopy(ItemStack result, ItemStack... ingredients)
/*    */   {
/* 16 */     super(result, Arrays.asList(ingredients));
/*    */   }
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting matrix)
/*    */   {
/* 21 */     ItemStack result = super.func_77572_b(matrix);
/*    */     
/* 23 */     int slot = InvUtil.getSlotContainingItem(matrix, Witchery.Items.BIOME_BOOK, 0);
/* 24 */     if (slot != -1) {
/* 25 */       ItemStack stack = matrix.func_70301_a(slot);
/* 26 */       int biomeNumber = ItemBook.getSelectedBiome(stack, 1000);
/* 27 */       result.func_77964_b(biomeNumber);
/*    */     }
/*    */     
/* 30 */     return result;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/RecipeShapelessBiomeCopy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */