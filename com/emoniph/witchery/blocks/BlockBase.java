/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ 
/*    */ 
/*    */ public class BlockBase
/*    */   extends Block
/*    */ {
/* 13 */   protected boolean registerBlockName = true;
/* 14 */   protected boolean registerWithCreateTab = true;
/*    */   protected final Class<? extends ItemBlock> clazzItem;
/*    */   
/*    */   public BlockBase(Material material, Class<? extends ItemBlock> clazzItem)
/*    */   {
/* 19 */     super(material);
/*    */     
/* 21 */     this.clazzItem = clazzItem;
/*    */   }
/*    */   
/*    */   public BlockBase(Material material) {
/* 25 */     this(material, null);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 30 */     if (this.registerWithCreateTab) {
/* 31 */       func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */     }
/*    */     
/* 34 */     if (this.registerBlockName) {
/* 35 */       if (this.clazzItem == null) {
/* 36 */         BlockUtil.registerBlock(this, blockName);
/*    */       } else {
/* 38 */         BlockUtil.registerBlock(this, this.clazzItem, blockName);
/*    */       }
/*    */     }
/*    */     
/* 42 */     return super.func_149663_c(blockName);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */