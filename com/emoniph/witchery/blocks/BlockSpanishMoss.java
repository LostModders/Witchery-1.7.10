/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockFire;
/*    */ import net.minecraft.block.BlockVine;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.ColorizerFoliage;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSpanishMoss
/*    */   extends BlockVine
/*    */ {
/*    */   public BlockSpanishMoss()
/*    */   {
/* 24 */     func_149711_c(0.2F);
/* 25 */     func_149672_a(Block.field_149779_h);
/*    */     
/*    */ 
/*    */ 
/* 29 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 34 */     BlockUtil.registerBlock(this, blockName);
/* 35 */     super.func_149663_c(blockName);
/* 36 */     Blocks.field_150480_ab.setFireInfo(this, 15, 100);
/* 37 */     return this;
/*    */   }
/*    */   
/*    */   public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
/*    */   {
/* 42 */     return false;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149635_D()
/*    */   {
/* 48 */     return ColorizerFoliage.func_77469_b();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149741_i(int par1)
/*    */   {
/* 54 */     return ColorizerFoliage.func_77469_b();
/*    */   }
/*    */   
/*    */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*    */   {
/* 59 */     for (int i = 0; i < 2; i++) {
/* 60 */       super.func_149674_a(par1World, par2, par3, par4, par5Random);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockSpanishMoss.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */