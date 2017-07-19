/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockBush;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockBaseBush
/*    */   extends BlockBush
/*    */ {
/* 15 */   protected boolean registerBlockName = true;
/* 16 */   protected boolean registerWithCreateTab = true;
/*    */   protected final Class<? extends ItemBlock> clazzItem;
/*    */   
/*    */   public BlockBaseBush(Material material)
/*    */   {
/* 21 */     this(material, null);
/*    */   }
/*    */   
/*    */   public BlockBaseBush(Material material, Class<? extends ItemBlock> clazzItem) {
/* 25 */     super(material);
/*    */     
/* 27 */     this.clazzItem = clazzItem;
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 32 */     if (this.registerWithCreateTab) {
/* 33 */       func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */     } else {
/* 35 */       func_149647_a(null);
/*    */     }
/*    */     
/* 38 */     if (this.registerBlockName) {
/* 39 */       if (this.clazzItem == null) {
/* 40 */         BlockUtil.registerBlock(this, blockName);
/*    */       } else {
/* 42 */         BlockUtil.registerBlock(this, this.clazzItem, blockName);
/*    */       }
/*    */     }
/*    */     
/* 46 */     return super.func_149663_c(blockName);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBaseBush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */