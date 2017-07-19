/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockFenceGate;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class BlockPerpetualIceGate
/*    */   extends BlockFenceGate
/*    */ {
/*    */   public BlockPerpetualIceGate()
/*    */   {
/* 17 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/* 18 */     func_149713_g(3);
/* 19 */     func_149711_c(2.0F);
/* 20 */     func_149752_b(5.0F);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 25 */     BlockUtil.registerBlock(this, blockName);
/* 26 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149701_w()
/*    */   {
/* 32 */     return 1;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
/*    */   {
/* 38 */     return Blocks.field_150432_aD.func_149733_h(p_149691_1_);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPerpetualIceGate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */