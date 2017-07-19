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
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockDemonHeart
/*     */   extends BlockBaseContainer
/*     */ {
/*     */   public BlockDemonHeart()
/*     */   {
/*  27 */     super(Material.field_151578_c, TileEntityDemonHeart.class);
/*  28 */     this.registerWithCreateTab = false;
/*     */     
/*  30 */     func_149715_a(0.2F);
/*  31 */     func_149711_c(1.0F);
/*  32 */     func_149672_a(field_149767_g);
/*     */     
/*  34 */     func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.8F, 0.75F);
/*     */   }
/*     */   
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/*  39 */     int l = MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/*  41 */     if (l == 0) {
/*  42 */       par1World.func_72921_c(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/*  45 */     if (l == 1) {
/*  46 */       par1World.func_72921_c(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/*  49 */     if (l == 2) {
/*  50 */       par1World.func_72921_c(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/*  53 */     if (l == 3) {
/*  54 */       par1World.func_72921_c(par2, par3, par4, 4, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/*  60 */     return new TileEntityDemonHeart();
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  75 */     return 1;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random rand, int fortune)
/*     */   {
/*  80 */     return Witchery.Items.GENERIC;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/*  85 */     return Witchery.Items.GENERIC.itemDemonHeart.damageValue;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  90 */     return Witchery.Items.GENERIC.itemDemonHeart.createStack();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 102 */     double yMid = y + 0.8D;
/* 103 */     double mid1 = 0.35D + 0.3D * rand.nextDouble();
/* 104 */     double mid2 = 0.35D + 0.3D * rand.nextDouble();
/*     */     
/* 106 */     if (rand.nextInt(10) == 0) {
/* 107 */       world.func_72869_a(ParticleEffect.FLAME.toString(), x + mid1, yMid, z + mid2, 0.0D, 0.0D, 0.0D);
/* 108 */       world.func_72869_a(ParticleEffect.SMOKE.toString(), x + mid1, yMid, z + mid2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityDemonHeart extends TileEntityBase {
/*     */     public long totalTicks() {
/* 114 */       return this.ticks;
/*     */     }
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 119 */       super.func_145845_h();
/* 120 */       if ((this.field_145850_b.field_72995_K) && 
/* 121 */         (this.ticks % 25L == 0L)) {
/* 122 */         this.field_145850_b.func_72980_b(0.5D + this.field_145851_c, 0.5D + this.field_145848_d, 0.5D + this.field_145849_e, "witchery:random.heartbeat", 0.8F, 1.0F, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockDemonHeart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */