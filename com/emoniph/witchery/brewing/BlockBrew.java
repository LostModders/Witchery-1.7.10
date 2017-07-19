/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.BlockFluidClassic;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ 
/*    */ public class BlockBrew extends BlockFluidClassic
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected IIcon[] icons;
/*    */   
/*    */   public BlockBrew(Fluid fluid)
/*    */   {
/* 20 */     super(fluid, Material.field_151586_h);
/* 21 */     this.quantaPerBlock = 2;
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 26 */     com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
/*    */     
/*    */ 
/* 29 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta)
/*    */   {
/* 38 */     return (side != 0) && (side != 1) ? this.icons[1] : this.icons[0];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister iconRegister)
/*    */   {
/* 44 */     this.icons = new IIcon[] { iconRegister.func_94245_a(func_149641_N() + "_still"), iconRegister.func_94245_a(func_149641_N() + "_flow") };
/*    */     
/*    */ 
/*    */ 
/* 48 */     com.emoniph.witchery.Witchery.Fluids.BREW.setIcons(this.icons[0], this.icons[1]);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean canDisplace(net.minecraft.world.IBlockAccess world, int x, int y, int z)
/*    */   {
/* 54 */     if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
/* 55 */       return false;
/*    */     }
/* 57 */     return super.canDisplace(world, x, y, z);
/*    */   }
/*    */   
/*    */   public boolean displaceIfPossible(World world, int x, int y, int z)
/*    */   {
/* 62 */     if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
/* 63 */       return false;
/*    */     }
/* 65 */     return super.displaceIfPossible(world, x, y, z);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockBrew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */