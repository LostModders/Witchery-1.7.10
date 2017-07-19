/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockIce;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class BlockPerpetualIce extends BlockIce
/*    */ {
/*    */   public BlockPerpetualIce()
/*    */   {
/* 16 */     func_149675_a(false);
/* 17 */     this.field_149765_K = 0.98F;
/* 18 */     func_149713_g(3);
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
/*    */ 
/*    */   public void func_149674_a(World world, int x, int y, int z, Random rand) {}
/*    */   
/*    */ 
/*    */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*    */   {
/* 36 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_149730_j()
/*    */   {
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPerpetualIce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */