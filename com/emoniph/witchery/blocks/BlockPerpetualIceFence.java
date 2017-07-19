/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockFence;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ public class BlockPerpetualIceFence extends BlockFence
/*    */ {
/*    */   public BlockPerpetualIceFence(String name)
/*    */   {
/* 17 */     super(name, net.minecraft.block.material.Material.field_151588_w);
/*    */     
/* 19 */     func_149711_c(2.0F);
/* 20 */     func_149752_b(5.0F);
/* 21 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 26 */     BlockUtil.registerBlock(this, blockName);
/* 27 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149701_w()
/*    */   {
/* 33 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean func_149826_e(IBlockAccess world, int x, int y, int z)
/*    */   {
/* 38 */     Block block = world.func_147439_a(x, y, z);
/*    */     
/* 40 */     return (super.func_149826_e(world, x, y, z)) || (block == Witchery.Blocks.PERPETUAL_ICE_FENCE_GATE);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPerpetualIceFence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */