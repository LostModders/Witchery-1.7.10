/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ 
/*     */ 
/*     */ public class BlockEmberMoss
/*     */   extends BlockBaseBush
/*     */   implements IShearable
/*     */ {
/*     */   public BlockEmberMoss()
/*     */   {
/*  29 */     super(Material.field_151585_k);
/*     */     
/*  31 */     func_149675_a(true);
/*  32 */     func_149711_c(0.0F);
/*  33 */     func_149715_a(0.4F);
/*  34 */     func_149672_a(field_149779_h);
/*     */     
/*  36 */     float f = 0.4F;
/*  37 */     func_149676_a(0.099999994F, 0.0F, 0.099999994F, 0.9F, 0.4F, 0.9F);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*     */   {
/*  42 */     if ((!world.field_72995_K) && 
/*  43 */       ((entity instanceof EntityLivingBase)) && (!entity.func_70027_ad()) && 
/*  44 */       (!entity.func_70045_F()) && ((!(entity instanceof EntityPlayer)) || (!((EntityPlayer)entity).field_71075_bZ.field_75098_d))) {
/*  45 */       entity.func_70015_d(3);
/*  46 */       ParticleEffect.FLAME.send(SoundEffect.MOB_GHAST_FIREBALL, world, 0.5D + posX, 0.05D + posY, 0.5D + posZ, 0.5D, 1.0D, 16);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/*  54 */     if ((!par1World.field_72995_K) && (par1World.field_73012_v.nextInt(6) == 0)) {
/*  55 */       byte b0 = 4;
/*  56 */       int l = 5;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  61 */       for (int i1 = par2 - b0; i1 <= par2 + b0; i1++) {
/*  62 */         for (int j1 = par4 - b0; j1 <= par4 + b0; j1++) {
/*  63 */           for (int k1 = par3 - 1; k1 <= par3 + 1; k1++) {
/*  64 */             if (par1World.func_147439_a(i1, k1, j1) == this) {
/*  65 */               l--;
/*     */               
/*  67 */               if (l <= 0) {
/*  68 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  75 */       i1 = par2 + par5Random.nextInt(3) - 1;
/*  76 */       int j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
/*  77 */       int k1 = par4 + par5Random.nextInt(3) - 1;
/*     */       
/*  79 */       for (int l1 = 0; l1 < 4; l1++) {
/*  80 */         if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
/*  81 */           par2 = i1;
/*  82 */           par3 = j1;
/*  83 */           par4 = k1;
/*     */         }
/*     */         
/*  86 */         i1 = par2 + par5Random.nextInt(3) - 1;
/*  87 */         j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
/*  88 */         k1 = par4 + par5Random.nextInt(3) - 1;
/*     */       }
/*     */       
/*  91 */       if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
/*  92 */         par1World.func_147465_d(i1, j1, k1, this, 0, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 100 */     if (rand.nextInt(100) == 0) {
/* 101 */       double d0 = x + 0.2F + rand.nextFloat() * 0.8F;
/* 102 */       double d1 = y + 0.15F + rand.nextFloat() * 0.3F;
/* 103 */       double d2 = z + 0.2F + rand.nextFloat() * 0.8F;
/* 104 */       world.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/* 110 */     return (super.func_149742_c(par1World, par2, par3, par4)) && (func_149718_j(par1World, par2, par3, par4));
/*     */   }
/*     */   
/*     */   protected boolean func_149854_a(Block block)
/*     */   {
/* 115 */     return (block != null) && (block.func_149662_c());
/*     */   }
/*     */   
/*     */   public boolean canBlockSpread(World world, int posX, int posY, int posZ) {
/* 119 */     Block block = world.func_147439_a(posX, posY - 1, posZ);
/* 120 */     return (func_149718_j(world, posX, posY, posZ)) && ((block == Blocks.field_150346_d) || (block == Blocks.field_150349_c) || (block == Blocks.field_150391_bh) || (block == Blocks.field_150354_m) || (block == Blocks.field_150458_ak));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int posX, int posY, int posZ)
/*     */   {
/* 126 */     Material material = world.func_147439_a(posX, posY - 1, posZ).func_149688_o();
/* 127 */     return (material != null) && (material.func_76220_a());
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random rand, int fortune)
/*     */   {
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 137 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_149636_a(World par3World, EntityPlayer player, int par4, int par5, int par6, int damageValue)
/*     */   {
/* 142 */     super.func_149636_a(par3World, player, par4, par5, par6, damageValue);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
/*     */   {
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
/*     */   {
/* 153 */     ArrayList<ItemStack> ret = new ArrayList();
/* 154 */     ret.add(new ItemStack(this, 1, 0));
/* 155 */     return ret;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockEmberMoss.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */