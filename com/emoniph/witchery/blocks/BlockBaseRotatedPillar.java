/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockRotatedPillar;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ public abstract class BlockBaseRotatedPillar
/*    */   extends BlockRotatedPillar
/*    */ {
/* 15 */   protected boolean registerBlockName = true;
/* 16 */   protected boolean registerWithCreateTab = true;
/*    */   protected final Class<? extends ItemBlock> clazzItem;
/*    */   
/*    */   public BlockBaseRotatedPillar(Material material, Class<? extends ItemBlock> clazzItem)
/*    */   {
/* 21 */     super(material);
/*    */     
/* 23 */     this.clazzItem = clazzItem;
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 28 */     if (this.registerWithCreateTab) {
/* 29 */       func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */     }
/*    */     
/* 32 */     if (this.registerBlockName) {
/* 33 */       if (this.clazzItem == null) {
/* 34 */         BlockUtil.registerBlock(this, blockName);
/*    */       } else {
/* 36 */         BlockUtil.registerBlock(this, this.clazzItem, blockName);
/*    */       }
/*    */     }
/*    */     
/* 40 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   protected abstract IIcon func_150163_b(int paramInt);
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBaseRotatedPillar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */