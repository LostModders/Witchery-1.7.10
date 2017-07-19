/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.ItemUtil;
/*    */ import net.minecraft.block.BlockSlab;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemSlab;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemWitchSlab extends ItemSlab
/*    */ {
/*    */   public ItemWitchSlab(BlockSlab slab, BlockSlab singleSlab, BlockSlab doubleSlab)
/*    */   {
/* 14 */     super(slab, singleSlab, doubleSlab, slab == doubleSlab);
/*    */     
/* 16 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public ItemBlock func_77655_b(String itemName)
/*    */   {
/* 21 */     ItemUtil.registerItem(this, itemName);
/* 22 */     return super.func_77655_b(itemName);
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack itemStack)
/*    */   {
/* 28 */     return super.func_77667_c(itemStack);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemWitchSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */