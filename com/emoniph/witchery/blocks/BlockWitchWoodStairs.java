/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockFire;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ 
/*    */ public class BlockWitchWoodStairs extends BlockStairs
/*    */ {
/*    */   private final int encouragement;
/*    */   private final int flammibility;
/*    */   
/*    */   public BlockWitchWoodStairs(Block baseBlock, int baseMeta, int encouragement, int flammibility)
/*    */   {
/* 14 */     super(baseBlock, baseMeta);
/* 15 */     this.flammibility = flammibility;
/* 16 */     this.encouragement = encouragement;
/* 17 */     func_149647_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 22 */     com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
/* 23 */     super.func_149663_c(blockName);
/* 24 */     net.minecraft.init.Blocks.field_150480_ab.setFireInfo(this, this.encouragement, this.flammibility);
/* 25 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchWoodStairs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */