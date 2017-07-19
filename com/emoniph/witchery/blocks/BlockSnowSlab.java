/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockSlab;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockSnowSlab extends BlockSlab
/*    */ {
/*    */   public BlockSnowSlab(boolean doubleSlab)
/*    */   {
/* 26 */     super(doubleSlab, Material.field_151597_y);
/* 27 */     func_149711_c(0.2F);
/* 28 */     func_149672_a(field_149773_n);
/* 29 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/* 30 */     this.field_149787_q = false;
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 35 */     BlockUtil.registerBlock(this, null, blockName);
/*    */     
/*    */ 
/* 38 */     super.func_149663_c(blockName);
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_149646_a(IBlockAccess block, int x, int y, int z, int side)
/*    */   {
/* 45 */     if (this.field_150004_a)
/* 46 */       return super.func_149646_a(block, x, y, z, side);
/* 47 */     if ((side != 1) && (side != 0) && (!super.func_149646_a(block, x, y, z, side))) {
/* 48 */       return false;
/*    */     }
/* 50 */     int i1 = x + net.minecraft.util.Facing.field_71586_b[net.minecraft.util.Facing.field_71588_a[side]];
/* 51 */     int j1 = y + net.minecraft.util.Facing.field_71587_c[net.minecraft.util.Facing.field_71588_a[side]];
/* 52 */     int k1 = z + net.minecraft.util.Facing.field_71585_d[net.minecraft.util.Facing.field_71588_a[side]];
/* 53 */     boolean flag = (block.func_72805_g(i1, j1, k1) & 0x8) != 0;
/* 54 */     return side == 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   private boolean func_150003_aa(Block p_150003_0_)
/*    */   {
/* 66 */     return (p_150003_0_ == Witchery.Blocks.SNOW_SLAB_SINGLE) || (p_150003_0_ == Witchery.Blocks.SNOW_SLAB_DOUBLE);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
/*    */   {
/* 72 */     return Blocks.field_150433_aE.func_149691_a(p_149691_1_, p_149691_2_ & 0x7);
/*    */   }
/*    */   
/*    */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*    */   {
/* 77 */     return Item.func_150898_a(Witchery.Blocks.SNOW_SLAB_SINGLE);
/*    */   }
/*    */   
/*    */   protected ItemStack func_149644_j(int p_149644_1_)
/*    */   {
/* 82 */     return new ItemStack(Item.func_150898_a(Witchery.Blocks.SNOW_SLAB_SINGLE), 2, p_149644_1_ & 0x7);
/*    */   }
/*    */   
/*    */   public String func_150002_b(int p_150002_1_)
/*    */   {
/* 87 */     return super.func_149739_a();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
/*    */   {
/* 93 */     if (this.field_150004_a) {
/* 94 */       return Witchery.Items.SNOW_SLAB_DOUBLE;
/*    */     }
/* 96 */     return Witchery.Items.SNOW_SLAB_SINGLE;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister p_149651_1_) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockSnowSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */