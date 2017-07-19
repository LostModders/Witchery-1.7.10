/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ 
/*    */ public class BlockPerpertualIceStairs extends BlockStairs
/*    */ {
/*    */   public BlockPerpertualIceStairs(Block baseBlock, int baseMeta)
/*    */   {
/* 12 */     super(baseBlock, baseMeta);
/* 13 */     this.field_149765_K = 0.98F;
/* 14 */     func_149713_g(3);
/* 15 */     func_149711_c(2.0F);
/* 16 */     func_149752_b(5.0F);
/* 17 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 22 */     BlockUtil.registerBlock(this, blockName);
/* 23 */     super.func_149663_c(blockName);
/* 24 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPerpertualIceStairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */