/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFumeFunnel
/*     */   extends BlockBaseContainer
/*     */ {
/*     */   private final boolean filtered;
/*     */   
/*     */   public BlockFumeFunnel(boolean filtered)
/*     */   {
/*  28 */     super(Material.field_151573_f, TileEntityFumeFunnel.class);
/*  29 */     this.registerTileEntity = (!filtered);
/*     */     
/*  31 */     this.filtered = filtered;
/*     */     
/*  33 */     func_149711_c(3.5F);
/*  34 */     func_149672_a(field_149777_j);
/*  35 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean isFiltered()
/*     */   {
/*  40 */     return this.filtered;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/*  45 */     if (world.field_72995_K) {
/*  46 */       return true;
/*     */     }
/*     */     
/*  49 */     int meta = world.func_72805_g(posX, posY, posZ);
/*     */     
/*     */ 
/*  52 */     switch (meta) {
/*     */     case 2: 
/*     */     case 3: 
/*  55 */       if (BlockWitchesOven.isOven(world.func_147439_a(posX + 1, posY, posZ))) {
/*  56 */         posX++;
/*  57 */       } else if (BlockWitchesOven.isOven(world.func_147439_a(posX - 1, posY, posZ))) {
/*  58 */         posX--;
/*  59 */       } else if (BlockWitchesOven.isOven(world.func_147439_a(posX, posY - 1, posZ))) {
/*  60 */         posY--;
/*     */       }
/*     */       break;
/*     */     case 4: 
/*     */     case 5: 
/*  65 */       if (BlockWitchesOven.isOven(world.func_147439_a(posX, posY, posZ + 1))) {
/*  66 */         posZ++;
/*  67 */       } else if (BlockWitchesOven.isOven(world.func_147439_a(posX, posY, posZ - 1))) {
/*  68 */         posZ--;
/*  69 */       } else if (BlockWitchesOven.isOven(world.func_147439_a(posX, posY - 1, posZ))) {
/*  70 */         posY--;
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/*  75 */     if ((world.func_147438_o(posX, posY, posZ) instanceof BlockWitchesOven.TileEntityWitchesOven)) {
/*  76 */       player.openGui(Witchery.instance, 2, world, posX, posY, posZ);
/*     */     }
/*     */     
/*  79 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  95 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/* 100 */     int l = MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/* 102 */     if (l == 0) {
/* 103 */       par1World.func_72921_c(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/* 106 */     if (l == 1) {
/* 107 */       par1World.func_72921_c(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/* 110 */     if (l == 2) {
/* 111 */       par1World.func_72921_c(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/* 114 */     if (l == 3) {
/* 115 */       par1World.func_72921_c(par2, par3, par4, 4, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 128 */     int metadata = world.func_72805_g(x, y, z);
/* 129 */     if (metadata == 1) {
/* 130 */       double d0 = x + 0.45F;
/* 131 */       double d1 = y + 0.4F;
/* 132 */       double d2 = z + 0.5F;
/* 133 */       world.func_72869_a(ParticleEffect.SMOKE.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityFumeFunnel extends TileEntity
/*     */   {
/*     */     public boolean canUpdate() {
/* 140 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockFumeFunnel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */