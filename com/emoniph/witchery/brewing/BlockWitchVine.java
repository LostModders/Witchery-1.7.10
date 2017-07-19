/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockBase;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class BlockWitchVine extends BlockBase
/*     */ {
/*     */   public BlockWitchVine()
/*     */   {
/*  23 */     super(new Material(Material.field_151582_l.func_151565_r()) {});
/*  29 */     this.registerWithCreateTab = false;
/*  30 */     func_149711_c(0.2F);
/*  31 */     func_149672_a(field_149779_h);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  36 */     func_149719_a(world, x, y, z);
/*  37 */     return super.func_149668_a(world, x, y, z);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  42 */     setBoundsBasedOnMetadata(world.func_72805_g(x, y, z));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z)
/*     */   {
/*  48 */     func_149719_a(world, x, y, z);
/*  49 */     return super.func_149633_g(world, x, y, z);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D()
/*     */   {
/*  55 */     return ColorizerFoliage.func_77468_c();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149741_i(int meta)
/*     */   {
/*  61 */     return ColorizerFoliage.func_77468_c();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  67 */     return world.func_72807_a(x, z).func_150571_c(x, y, z);
/*     */   }
/*     */   
/*     */   public void setBoundsBasedOnMetadata(int meta) {
/*  71 */     float f = 0.125F;
/*     */     
/*  73 */     if (meta == 2) {
/*  74 */       func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*  75 */     } else if (meta == 3) {
/*  76 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*  77 */     } else if (meta == 4) {
/*  78 */       func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  79 */     } else if (meta == 5) {
/*  80 */       func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  96 */     return Witchery.proxy.getVineRenderId();
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune)
/*     */   {
/* 101 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/* 106 */     return 0;
/*     */   }
/*     */   
/*     */   public net.minecraft.item.ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 111 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
/*     */   {
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockWitchVine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */