/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryCreativeTab;
/*     */ import com.emoniph.witchery.dimension.WorldProviderTorment;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBreakable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockTormentPortal extends BlockBreakable
/*     */ {
/*     */   private static final double MORE_TORMENT_CHANCE = 0.05D;
/*     */   
/*     */   public BlockTormentPortal()
/*     */   {
/*  28 */     super("portal", net.minecraft.block.material.Material.field_151567_E, false);
/*  29 */     func_149675_a(true);
/*  30 */     func_149722_s();
/*  31 */     func_149752_b(9999.0F);
/*  32 */     func_149672_a(field_149778_k);
/*  33 */     func_149715_a(0.75F);
/*  34 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  39 */     BlockUtil.registerBlock(this, blockName);
/*  40 */     return super.func_149663_c(blockName);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  45 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/*  53 */     if ((par1IBlockAccess.func_147439_a(par2 - 1, par3, par4) == Blocks.field_150350_a) && (par1IBlockAccess.func_147439_a(par2 + 1, par3, par4) == Blocks.field_150350_a)) {
/*  54 */       float f = 0.125F;
/*  55 */       float f1 = 0.5F;
/*  56 */       func_149676_a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
/*     */     } else {
/*  58 */       float f = 0.5F;
/*  59 */       float f1 = 0.125F;
/*  60 */       func_149676_a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/*  78 */     if (par1IBlockAccess.func_147439_a(par2, par3, par4) == this) {
/*  79 */       return false;
/*     */     }
/*  81 */     boolean flag = (par1IBlockAccess.func_147439_a(par2 - 1, par3, par4) == this) && (par1IBlockAccess.func_147439_a(par2 - 2, par3, par4) != this);
/*  82 */     boolean flag1 = (par1IBlockAccess.func_147439_a(par2 + 1, par3, par4) == this) && (par1IBlockAccess.func_147439_a(par2 + 2, par3, par4) != this);
/*  83 */     boolean flag2 = (par1IBlockAccess.func_147439_a(par2, par3, par4 - 1) == this) && (par1IBlockAccess.func_147439_a(par2, par3, par4 - 2) != this);
/*  84 */     boolean flag3 = (par1IBlockAccess.func_147439_a(par2, par3, par4 + 1) == this) && (par1IBlockAccess.func_147439_a(par2, par3, par4 + 2) != this);
/*  85 */     boolean flag4 = (flag) || (flag1);
/*  86 */     boolean flag5 = (flag2) || (flag3);
/*  87 */     return (flag4) && (par5 == 4);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/*  93 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 100 */     if ((!world.field_72995_K) && ((entity instanceof EntityPlayer)) && (entity.field_70154_o == null) && (entity.field_70153_n == null) && ((entity instanceof EntityPlayer)))
/*     */     {
/*     */ 
/* 103 */       if ((entity.field_71093_bK != Config.instance().dimensionTormentID) || (world.field_73012_v.nextDouble() < 0.05D)) {
/* 104 */         WorldProviderTorment.setPlayerMustTorment((EntityPlayer)entity, 1, -1);
/*     */       } else {
/* 106 */         WorldProviderTorment.setPlayerMustTorment((EntityPlayer)entity, 3, -2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149701_w()
/*     */   {
/* 114 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_149720_d(IBlockAccess iblockaccess, int x, int y, int z)
/*     */   {
/* 119 */     return 16711714;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 125 */     if (par5Random.nextInt(100) == 0) {
/* 126 */       par1World.func_72980_b(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 131 */     for (int l = 0; l < 2; l++) {
/* 132 */       double d0 = par2 + par5Random.nextFloat();
/* 133 */       double d1 = par3 + par5Random.nextFloat();
/* 134 */       double d2 = par4 + par5Random.nextFloat();
/* 135 */       par1World.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 141 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockTormentPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */