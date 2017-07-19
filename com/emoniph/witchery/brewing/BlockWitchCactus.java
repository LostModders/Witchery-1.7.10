/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBase;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockWitchCactus
/*     */   extends BlockBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconTop;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconBottom;
/*     */   
/*     */   public BlockWitchCactus()
/*     */   {
/*  30 */     super(Material.field_151570_A);
/*  31 */     func_149711_c(0.4F);
/*  32 */     func_149672_a(field_149775_l);
/*  33 */     this.registerWithCreateTab = false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  38 */     float f = 0.0625F;
/*  39 */     return AxisAlignedBB.func_72330_a(x + f, y, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z)
/*     */   {
/*  46 */     float f = 0.0625F;
/*  47 */     return AxisAlignedBB.func_72330_a(x + f, y, z + f, x + 1 - f, y + 1, z + 1 - f);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  54 */     return side == 0 ? this.iconBottom : side == 1 ? this.iconTop : this.field_149761_L;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  64 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  69 */     return 13;
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  74 */     entity.func_70097_a(DamageSource.field_76367_g, 1.0F);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/*  80 */     this.field_149761_L = iconRegister.func_94245_a(func_149641_N() + "_side");
/*  81 */     this.iconTop = iconRegister.func_94245_a(func_149641_N() + "_top");
/*  82 */     this.iconBottom = iconRegister.func_94245_a(func_149641_N() + "_bottom");
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int meta, Random random, int fortune)
/*     */   {
/*  87 */     return null;
/*     */   }
/*     */   
/*     */   public int quantityDropped(int meta, int fortune, Random random)
/*     */   {
/*  92 */     return 0;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/* 102 */     return !BlockUtil.isReplaceableBlock(world, x, y - 1, z);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 107 */     if (!func_149718_j(world, x, y, z)) {
/* 108 */       world.func_147480_a(x, y, z, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockWitchCactus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */