/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ 
/*    */ public class BlockSnowStairs extends BlockStairs
/*    */ {
/*    */   public BlockSnowStairs(Block baseBlock, int baseMeta)
/*    */   {
/* 12 */     super(baseBlock, baseMeta);
/* 13 */     func_149711_c(0.2F);
/* 14 */     func_149672_a(field_149773_n);
/* 15 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 20 */     BlockUtil.registerBlock(this, blockName);
/* 21 */     super.func_149663_c(blockName);
/* 22 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockSnowStairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */