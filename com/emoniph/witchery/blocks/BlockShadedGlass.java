/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryCreativeTab;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import com.google.common.collect.Lists;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBreakable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemDye;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockShadedGlass
/*     */   extends BlockBreakable
/*     */ {
/*  29 */   private final IIcon[] icons = new IIcon[16];
/*     */   
/*  31 */   private static final String[] colors = (String[])Lists.reverse(Arrays.asList(ItemDye.field_150921_b)).toArray(new String[ItemDye.field_150921_b.length]);
/*     */   private boolean shaded;
/*     */   
/*     */   public static class ClassItemBlock extends MultiItemBlock {
/*  35 */     public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  40 */       return BlockShadedGlass.colors;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public BlockShadedGlass(boolean shaded)
/*     */   {
/*  47 */     super(shaded ? "witchery:shadedglass" : "glass", shaded ? Material.field_151576_e : Material.field_151592_s, false);
/*  48 */     this.shaded = shaded;
/*  49 */     func_149711_c(0.3F);
/*  50 */     if (shaded) {
/*  51 */       func_149713_g(15);
/*     */     }
/*     */     
/*  54 */     func_149672_a(field_149778_k);
/*  55 */     if (!shaded) {
/*  56 */       func_149647_a(WitcheryCreativeTab.INSTANCE);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
/*     */   {
/*  63 */     return super.func_149660_a(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/*  69 */     updatePoweredState(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  75 */     BlockUtil.registerBlock(this, ClassItemBlock.class, blockName);
/*  76 */     return super.func_149663_c(blockName);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int p_149691_1_, int meta) {
/*  81 */     return this.icons[(meta % this.icons.length)];
/*     */   }
/*     */   
/*     */   public int func_149692_a(int meta) {
/*  85 */     return meta;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand) {
/*  89 */     return 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int func_149997_b(int meta) {
/*  94 */     return (meta ^ 0xFFFFFFFF) & 0xF;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List list) {
/*  99 */     for (int i = 0; i < this.icons.length; i++) {
/* 100 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149701_w() {
/* 106 */     return 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister) {
/* 111 */     for (int i = 0; i < this.icons.length; i++) {
/* 112 */       this.icons[i] = iconRegister.func_94245_a(func_149641_N() + "_" + ItemDye.field_150921_b[func_149997_b(i)]);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean func_149700_E()
/*     */   {
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 127 */     updatePoweredState(world, x, y, z);
/*     */   }
/*     */   
/*     */   public void updatePoweredState(World world, int x, int y, int z) {
/* 131 */     if (world.func_72864_z(x, y, z)) {
/* 132 */       if (!this.shaded) {
/* 133 */         world.func_147465_d(x, y, z, Witchery.Blocks.SHADED_GLASS_ON, world.func_72805_g(x, y, z), 3);
/*     */       }
/*     */     }
/* 136 */     else if (this.shaded) {
/* 137 */       world.func_147465_d(x, y, z, Witchery.Blocks.SHADED_GLASS, world.func_72805_g(x, y, z), 3);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockShadedGlass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */