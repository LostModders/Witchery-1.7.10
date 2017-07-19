/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBreakable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockSpiritPortal extends BlockBreakable
/*     */ {
/*     */   private final Block portalFrameBlock;
/*     */   
/*     */   public BlockSpiritPortal(Block portalFrameBlock)
/*     */   {
/*  29 */     super("portal", Material.field_151567_E, false);
/*  30 */     func_149675_a(true);
/*  31 */     this.portalFrameBlock = portalFrameBlock;
/*  32 */     func_149711_c(-1.0F);
/*  33 */     func_149672_a(field_149778_k);
/*  34 */     func_149715_a(0.75F);
/*  35 */     func_149647_a(null);
/*     */   }
/*     */   
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  40 */     BlockUtil.registerBlock(this, blockName);
/*  41 */     return super.func_149663_c(blockName);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/*  46 */     super.func_149674_a(par1World, par2, par3, par4, par5Random);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  70 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/*  78 */     if ((par1IBlockAccess.func_147439_a(par2 - 1, par3, par4) != this) && (par1IBlockAccess.func_147439_a(par2 + 1, par3, par4) != this)) {
/*  79 */       float f = 0.125F;
/*  80 */       float f1 = 0.5F;
/*  81 */       func_149676_a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
/*     */     } else {
/*  83 */       float f = 0.5F;
/*  84 */       float f1 = 0.125F;
/*  85 */       func_149676_a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 101 */     byte b0 = 0;
/* 102 */     byte b1 = 1;
/*     */     
/* 104 */     if ((par1World.func_147439_a(par2 - 1, par3, par4) == this) || (par1World.func_147439_a(par2 + 1, par3, par4) == this)) {
/* 105 */       b0 = 1;
/* 106 */       b1 = 0;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 111 */     for (int i1 = par3; par1World.func_147439_a(par2, i1 - 1, par4) == this; i1--) {}
/*     */     
/*     */ 
/*     */ 
/* 115 */     if (par1World.func_147439_a(par2, i1 - 1, par4) != this.portalFrameBlock) {
/* 116 */       par1World.func_147468_f(par2, par3, par4);
/*     */     }
/*     */     else
/*     */     {
/* 120 */       for (int j1 = 1; (j1 < 3) && (par1World.func_147439_a(par2, i1 + j1, par4) == this); j1++) {}
/*     */       
/*     */ 
/*     */ 
/* 124 */       if ((j1 == 2) && (par1World.func_147439_a(par2, i1 + j1, par4) == this.portalFrameBlock)) {
/* 125 */         boolean flag = (par1World.func_147439_a(par2 - 1, par3, par4) == this) || (par1World.func_147439_a(par2 + 1, par3, par4) == this);
/* 126 */         boolean flag1 = (par1World.func_147439_a(par2, par3, par4 - 1) == this) || (par1World.func_147439_a(par2, par3, par4 + 1) == this);
/*     */         
/* 128 */         if ((flag) && (flag1)) {
/* 129 */           par1World.func_147468_f(par2, par3, par4);
/*     */         }
/* 131 */         else if ((par1World.func_147439_a(par2 + b0, par3, par4 + b1) != this.portalFrameBlock) && (par1World.func_147439_a(par2 - b0, par3, par4 - b1) != this.portalFrameBlock))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 173 */           par1World.func_147468_f(par2, par3, par4);
/*     */         }
/*     */       }
/*     */       else {
/* 177 */         par1World.func_147468_f(par2, par3, par4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 185 */     if (par1IBlockAccess.func_147439_a(par2, par3, par4) == this) {
/* 186 */       return false;
/*     */     }
/* 188 */     boolean flag = (par1IBlockAccess.func_147439_a(par2 - 1, par3, par4) == this) && (par1IBlockAccess.func_147439_a(par2 - 2, par3, par4) != this);
/*     */     
/* 190 */     boolean flag1 = (par1IBlockAccess.func_147439_a(par2 + 1, par3, par4) == this) && (par1IBlockAccess.func_147439_a(par2 + 2, par3, par4) != this);
/*     */     
/* 192 */     boolean flag2 = (par1IBlockAccess.func_147439_a(par2, par3, par4 - 1) == this) && (par1IBlockAccess.func_147439_a(par2, par3, par4 - 2) != this);
/*     */     
/* 194 */     boolean flag3 = (par1IBlockAccess.func_147439_a(par2, par3, par4 + 1) == this) && (par1IBlockAccess.func_147439_a(par2, par3, par4 + 2) != this);
/*     */     
/* 196 */     boolean flag4 = (flag) || (flag1);
/* 197 */     boolean flag5 = (flag2) || (flag3);
/* 198 */     return (flag4) && (par5 == 4);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 204 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 209 */     if ((!world.field_72995_K) && ((entity instanceof EntityPlayer)) && (entity.field_71093_bK == Config.instance().dimensionDreamID) && (entity.field_70154_o == null) && (entity.field_70153_n == null) && (WorldProviderDreamWorld.canPlayerManifest((EntityPlayer)entity)))
/*     */     {
/*     */ 
/* 212 */       WorldProviderDreamWorld.manifestPlayerInOverworldAsGhost((EntityPlayer)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149701_w()
/*     */   {
/* 219 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_149720_d(IBlockAccess iblockaccess, int x, int y, int z)
/*     */   {
/* 224 */     return 65382;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 236 */     for (int l = 0; l < 2; l++) {
/* 237 */       double d0 = par2 + par5Random.nextFloat();
/* 238 */       double d1 = par3 + par5Random.nextFloat();
/* 239 */       double d2 = par4 + par5Random.nextFloat();
/* 240 */       double d3 = 0.0D;
/* 241 */       double d4 = 0.0D;
/* 242 */       double d5 = 0.0D;
/* 243 */       int i1 = par5Random.nextInt(2) * 2 - 1;
/* 244 */       d3 = (par5Random.nextFloat() - 0.5D) * 0.5D;
/* 245 */       d4 = (par5Random.nextFloat() - 0.5D) * 0.5D;
/* 246 */       d5 = (par5Random.nextFloat() - 0.5D) * 0.5D;
/*     */       
/* 248 */       if ((par1World.func_147439_a(par2 - 1, par3, par4) != this) && (par1World.func_147439_a(par2 + 1, par3, par4) != this)) {
/* 249 */         d0 = par2 + 0.5D + 0.25D * i1;
/* 250 */         d3 = par5Random.nextFloat() * 2.0F * i1;
/*     */       } else {
/* 252 */         d2 = par4 + 0.5D + 0.25D * i1;
/* 253 */         d5 = par5Random.nextFloat() * 2.0F * i1;
/*     */       }
/*     */       
/* 256 */       par1World.func_72869_a(ParticleEffect.DRIP_WATER.toString(), d0, d1, d2, d3, d4, d5);
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 262 */     return null;
/*     */   }
/*     */   
/*     */   public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4) {
/* 266 */     byte b0 = 0;
/* 267 */     byte b1 = 0;
/*     */     
/* 269 */     if ((par1World.func_147439_a(par2 - 1, par3, par4) == this.portalFrameBlock) || (par1World.func_147439_a(par2 + 1, par3, par4) == this.portalFrameBlock)) {
/* 270 */       b0 = 1;
/*     */     }
/*     */     
/* 273 */     if ((par1World.func_147439_a(par2, par3, par4 - 1) == this.portalFrameBlock) || (par1World.func_147439_a(par2, par3, par4 + 1) == this.portalFrameBlock)) {
/* 274 */       b1 = 1;
/*     */     }
/*     */     
/* 277 */     if (b0 == b1) {
/* 278 */       return false;
/*     */     }
/* 280 */     if (par1World.func_147437_c(par2 - b0, par3, par4 - b1)) {
/* 281 */       par2 -= b0;
/* 282 */       par4 -= b1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 288 */     int WIDTH = 2;
/* 289 */     int HEIGHT = 2;
/* 290 */     for (int l = -1; l <= 2; l++) {
/* 291 */       for (int i1 = -1; i1 <= 2; i1++) {
/* 292 */         boolean flag = (l == -1) || (l == 2) || (i1 == -1) || (i1 == 2);
/*     */         
/* 294 */         if (((l != -1) && (l != 2)) || ((i1 != -1) && (i1 != 2))) {
/* 295 */           Block j1 = par1World.func_147439_a(par2 + b0 * l, par3 + i1, par4 + b1 * l);
/* 296 */           boolean isAirBlock = par1World.func_147437_c(par2 + b0 * l, par3 + i1, par4 + b1 * l);
/*     */           
/* 298 */           if (flag) {
/* 299 */             if (j1 != this.portalFrameBlock) {
/* 300 */               return false;
/*     */             }
/* 302 */           } else if ((!isAirBlock) && (j1 != Witchery.Blocks.FLOWING_SPIRIT)) {
/* 303 */             return false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 309 */     for (l = 0; l < 2; l++) {
/* 310 */       for (int i1 = 0; i1 < 2; i1++) {
/* 311 */         par1World.func_147465_d(par2 + b0 * l, par3 + i1, par4 + b1 * l, this, 0, 2);
/*     */       }
/*     */     }
/*     */     
/* 315 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockSpiritPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */