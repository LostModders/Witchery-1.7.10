/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockLight extends BlockBase
/*    */ {
/*    */   public BlockLight()
/*    */   {
/*  9 */     super(net.minecraft.block.material.Material.field_151579_a);
/* 10 */     func_149715_a(1.0F);
/* 11 */     this.registerWithCreateTab = false;
/*    */   }
/*    */   
/*    */   public int func_149645_b()
/*    */   {
/* 16 */     return -1;
/*    */   }
/*    */   
/*    */   public net.minecraft.util.AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*    */   {
/* 21 */     return null;
/*    */   }
/*    */   
/*    */   public boolean func_149662_c()
/*    */   {
/* 26 */     return false;
/*    */   }
/*    */   
/*    */   public boolean func_149678_a(int p_149678_1_, boolean p_149678_2_)
/*    */   {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public void func_149690_a(World world, int x, int y, int z, int side, float p_149690_6_, int p_149690_7_) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */