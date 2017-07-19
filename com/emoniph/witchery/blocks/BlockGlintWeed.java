/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockGlintWeed
/*     */   extends BlockBaseBush
/*     */ {
/*     */   public BlockGlintWeed()
/*     */   {
/*  17 */     super(Material.field_151585_k);
/*     */     
/*  19 */     func_149715_a(0.9375F);
/*  20 */     func_149711_c(0.0F);
/*  21 */     func_149672_a(Block.field_149779_h);
/*  22 */     func_149649_H();
/*  23 */     func_149675_a(true);
/*     */     
/*  25 */     float f = 0.45F;
/*  26 */     func_149676_a(0.050000012F, 0.0F, 0.050000012F, 0.95F, 1.0F, 0.95F);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/*  31 */     if ((!par1World.field_72995_K) && (par1World.field_73012_v.nextInt(6) == 0)) {
/*  32 */       byte b0 = 4;
/*  33 */       int l = 5;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  38 */       for (int i1 = par2 - b0; i1 <= par2 + b0; i1++) {
/*  39 */         for (int j1 = par4 - b0; j1 <= par4 + b0; j1++) {
/*  40 */           for (int k1 = par3 - 1; k1 <= par3 + 1; k1++) {
/*  41 */             if (par1World.func_147439_a(i1, k1, j1) == this) {
/*  42 */               l--;
/*     */               
/*  44 */               if (l <= 0) {
/*  45 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  52 */       i1 = par2 + par5Random.nextInt(3) - 1;
/*  53 */       int j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
/*  54 */       int k1 = par4 + par5Random.nextInt(3) - 1;
/*     */       
/*  56 */       for (int l1 = 0; l1 < 4; l1++) {
/*  57 */         if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
/*  58 */           par2 = i1;
/*  59 */           par3 = j1;
/*  60 */           par4 = k1;
/*     */         }
/*     */         
/*  63 */         i1 = par2 + par5Random.nextInt(3) - 1;
/*  64 */         j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
/*  65 */         k1 = par4 + par5Random.nextInt(3) - 1;
/*     */       }
/*     */       
/*  68 */       if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
/*  69 */         par1World.func_147465_d(i1, j1, k1, this, 0, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/*  77 */     double d0 = x + 0.4F + rand.nextInt(3) * 0.1F;
/*  78 */     double d1 = y + 0.4F + rand.nextInt(3) * 0.1F;
/*  79 */     double d2 = z + 0.4F + rand.nextInt(3) * 0.1F;
/*  80 */     world.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/*  85 */     return (super.func_149742_c(par1World, par2, par3, par4)) && (func_149718_j(par1World, par2, par3, par4));
/*     */   }
/*     */   
/*     */   protected boolean func_149854_a(Block block)
/*     */   {
/*  90 */     return (block != null) && (block.func_149662_c());
/*     */   }
/*     */   
/*     */   public boolean canBlockSpread(World world, int posX, int posY, int posZ) {
/*  94 */     Block block = world.func_147439_a(posX, posY - 1, posZ);
/*  95 */     return (func_149718_j(world, posX, posY, posZ)) && ((block == Blocks.field_150346_d) || (block == Blocks.field_150349_c) || (block == Blocks.field_150391_bh) || (block == Blocks.field_150354_m) || (block == Blocks.field_150458_ak));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int posX, int posY, int posZ)
/*     */   {
/* 101 */     Material material = world.func_147439_a(posX, posY - 1, posZ).func_149688_o();
/* 102 */     Material material2 = world.func_147439_a(posX, posY + 1, posZ).func_149688_o();
/* 103 */     return ((material != null) && (material.func_76220_a())) || ((material2 != null) && (material2.func_76220_a()));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockGlintWeed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */