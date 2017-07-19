/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockFire;
/*    */ import net.minecraft.block.BlockSlab;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockWitchWoodSlab extends BlockSlab
/*    */ {
/* 25 */   public static final String[] BLOCK_TYPES = { "rowan", "alder", "hawthorn" };
/*    */   
/*    */   public BlockWitchWoodSlab(boolean doubleSlab) {
/* 28 */     super(doubleSlab, net.minecraft.block.material.Material.field_151575_d);
/* 29 */     func_149711_c(2.0F);
/* 30 */     func_149752_b(5.0F);
/* 31 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 36 */     BlockUtil.registerBlock(this, null, blockName);
/* 37 */     super.func_149663_c(blockName);
/* 38 */     Blocks.field_150480_ab.setFireInfo(this, 5, 20);
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
/*    */   {
/* 45 */     return Witchery.Blocks.PLANKS.func_149691_a(p_149691_1_, p_149691_2_ & 0x7);
/*    */   }
/*    */   
/*    */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*    */   {
/* 50 */     return Item.func_150898_a(Witchery.Blocks.WOOD_SLAB_SINGLE);
/*    */   }
/*    */   
/*    */   protected ItemStack func_149644_j(int p_149644_1_)
/*    */   {
/* 55 */     return new ItemStack(Item.func_150898_a(Witchery.Blocks.WOOD_SLAB_SINGLE), 2, p_149644_1_ & 0x7);
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_149739_a()
/*    */   {
/* 61 */     return super.func_149739_a();
/*    */   }
/*    */   
/*    */   public String func_150002_b(int p_150002_1_)
/*    */   {
/* 66 */     if ((p_150002_1_ < 0) || (p_150002_1_ >= BLOCK_TYPES.length)) {
/* 67 */       p_150002_1_ = 0;
/*    */     }
/*    */     
/* 70 */     return super.func_149739_a() + "." + BLOCK_TYPES[p_150002_1_];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
/*    */   {
/* 76 */     if (this.field_150004_a) {
/* 77 */       return Witchery.Items.SLAB_DOUBLE;
/*    */     }
/* 79 */     return Witchery.Items.SLAB_SINGLE;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
/*    */   {
/* 86 */     if (p_149666_1_ != Item.func_150898_a(Witchery.Blocks.WOOD_SLAB_DOUBLE)) {}
/*    */     
/*    */ 
/* 89 */     for (int i = 0; i < BLOCK_TYPES.length; i++) {
/* 90 */       p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister p_149651_1_) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchWoodSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */