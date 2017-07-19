/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class BlockCotton extends BlockBaseBush
/*     */ {
/*     */   public BlockCotton()
/*     */   {
/*  18 */     super(net.minecraft.block.material.Material.field_151585_k);
/*     */     
/*  20 */     func_149711_c(0.0F);
/*  21 */     func_149672_a(field_149779_h).func_149675_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/*  28 */     if ((world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) && ((world.field_73011_w instanceof WorldProviderDreamWorld)) && (((WorldProviderDreamWorld)world.field_73011_w).isNightmare()))
/*     */     {
/*  30 */       ArrayList<ItemStack> ret = new ArrayList();
/*     */       
/*  32 */       int count = quantityDropped(metadata, fortune, world.field_73012_v);
/*  33 */       for (int i = 0; i < count; i++) {
/*  34 */         ret.add(Witchery.Items.GENERIC.itemDisturbedCotton.createStack());
/*     */       }
/*  36 */       return ret;
/*     */     }
/*  38 */     return super.getDrops(world, x, y, z, metadata, fortune);
/*     */   }
/*     */   
/*     */   public boolean canBlockSpread(World world, int posX, int posY, int posZ)
/*     */   {
/*  43 */     if ((world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) && (func_149718_j(world, posX, posY, posZ))) {
/*  44 */       Block blockBelow = world.func_147439_a(posX, posY - 1, posZ);
/*  45 */       return (blockBelow == net.minecraft.init.Blocks.field_150346_d) || (blockBelow == net.minecraft.init.Blocks.field_150349_c);
/*     */     }
/*  47 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isBlockMatch(World world, int x, int y, int z, Block block, int metadata) {
/*  51 */     return (world.func_147439_a(x, y, z) == block) && (world.func_72805_g(x, y, z) == metadata);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/*  56 */     if ((!par1World.field_72995_K) && (par1World.field_73012_v.nextInt(6) == 0)) {
/*  57 */       if ((par1World.field_73011_w.field_76574_g != Config.instance().dimensionDreamID) || ((!isBlockMatch(par1World, par2 + 1, par3 - 1, par4, Witchery.Blocks.FLOWING_SPIRIT, 0)) && (!isBlockMatch(par1World, par2 - 1, par3 - 1, par4, Witchery.Blocks.FLOWING_SPIRIT, 0)) && (!isBlockMatch(par1World, par2, par3 - 1, par4 + 1, Witchery.Blocks.FLOWING_SPIRIT, 0)) && (!isBlockMatch(par1World, par2, par3 - 1, par4 - 1, Witchery.Blocks.FLOWING_SPIRIT, 0))))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*  62 */         return;
/*     */       }
/*  64 */       byte b0 = 4;
/*  65 */       int l = 5;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  70 */       for (int i1 = par2 - b0; i1 <= par2 + b0; i1++) {
/*  71 */         for (int j1 = par4 - b0; j1 <= par4 + b0; j1++) {
/*  72 */           for (int k1 = par3 - 1; k1 <= par3 + 1; k1++) {
/*  73 */             if (par1World.func_147439_a(i1, k1, j1) == this) {
/*  74 */               l--;
/*     */               
/*  76 */               if (l <= 0) {
/*  77 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  84 */       i1 = par2 + par5Random.nextInt(3) - 1;
/*  85 */       int j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
/*  86 */       int k1 = par4 + par5Random.nextInt(3) - 1;
/*     */       
/*  88 */       for (int l1 = 0; l1 < 4; l1++) {
/*  89 */         if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
/*  90 */           par2 = i1;
/*  91 */           par3 = j1;
/*  92 */           par4 = k1;
/*     */         }
/*     */         
/*  95 */         i1 = par2 + par5Random.nextInt(3) - 1;
/*  96 */         j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
/*  97 */         k1 = par4 + par5Random.nextInt(3) - 1;
/*     */       }
/*     */       
/* 100 */       if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
/* 101 */         par1World.func_147465_d(i1, j1, k1, this, 0, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockCotton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */