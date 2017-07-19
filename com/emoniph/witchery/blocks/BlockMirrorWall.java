/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class BlockMirrorWall extends BlockBase
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected IIcon[] icons;
/*    */   
/*    */   public BlockMirrorWall()
/*    */   {
/* 16 */     super(net.minecraft.block.material.Material.field_151576_e);
/* 17 */     func_149722_s();
/* 18 */     func_149752_b(9999.0F);
/*    */     
/* 20 */     func_149649_H();
/*    */   }
/*    */   
/*    */   public int func_149645_b() {
/* 24 */     return super.func_149645_b();
/*    */   }
/*    */   
/*    */   public int func_149635_D()
/*    */   {
/* 29 */     return 13426175;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149720_d(net.minecraft.world.IBlockAccess world, int x, int y, int z)
/*    */   {
/* 35 */     return func_149635_D();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149741_i(int par1)
/*    */   {
/* 41 */     return func_149635_D();
/*    */   }
/*    */   
/*    */   protected boolean func_149700_E()
/*    */   {
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public int func_149745_a(Random rand)
/*    */   {
/* 51 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta)
/*    */   {
/* 60 */     return (side != 0) && (side != 1) ? this.icons[1] : this.icons[0];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister iconRegister)
/*    */   {
/* 66 */     this.icons = new IIcon[] { iconRegister.func_94245_a(func_149641_N() + "_still"), iconRegister.func_94245_a(func_149641_N() + "_flow") };
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockMirrorWall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */