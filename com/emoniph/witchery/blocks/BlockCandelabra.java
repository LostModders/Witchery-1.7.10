/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCandelabra extends BlockBaseContainer
/*     */ {
/*     */   public BlockCandelabra()
/*     */   {
/*  23 */     super(Material.field_151574_g, TileEntityCandelabra.class);
/*  24 */     this.registerWithCreateTab = false;
/*     */     
/*  26 */     func_149715_a(1.0F);
/*  27 */     func_149711_c(2.0F);
/*  28 */     func_149672_a(field_149777_j);
/*     */     
/*  30 */     func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  35 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  40 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  45 */     return 1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/*  50 */     return Witchery.Items.GENERIC;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/*  55 */     return Witchery.Items.GENERIC.itemCandelabra.damageValue;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/*  60 */     func_111046_k(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
/*  64 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/*  65 */       if (!par1World.field_72995_K) {
/*  66 */         func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/*  67 */         par1World.func_147468_f(par2, par3, par4);
/*     */       }
/*  69 */       return false;
/*     */     }
/*  71 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public net.minecraft.item.ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  77 */     return Witchery.Items.GENERIC.itemCandelabra.createStack();
/*     */   }
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/*  82 */     Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
/*  83 */     return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/*  95 */     double yMid = y + 1.05D;
/*  96 */     double yOuter = y + 0.9D;
/*  97 */     double mid = 0.5D;
/*  98 */     double near = 0.2D;
/*  99 */     double far = 0.8D;
/*     */     
/* 101 */     if (rand.nextInt(4) != 0) {
/* 102 */       world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.5D, yMid, z + 0.5D, 0.0D, 0.0D, 0.0D);
/* 103 */       world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.5D, yMid, z + 0.5D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 106 */     if (rand.nextInt(4) != 0) {
/* 107 */       world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.8D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
/* 108 */       world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.8D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 111 */     if (rand.nextInt(4) != 0) {
/* 112 */       world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.2D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
/* 113 */       world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.2D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 116 */     if (rand.nextInt(4) != 0) {
/* 117 */       world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.5D, yOuter, z + 0.8D, 0.0D, 0.0D, 0.0D);
/* 118 */       world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.5D, yOuter, z + 0.8D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 121 */     if (rand.nextInt(4) != 0) {
/* 122 */       world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.5D, yOuter, z + 0.2D, 0.0D, 0.0D, 0.0D);
/* 123 */       world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.5D, yOuter, z + 0.2D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityCandelabra extends TileEntity
/*     */   {
/*     */     public boolean canUpdate() {
/* 130 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockCandelabra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */