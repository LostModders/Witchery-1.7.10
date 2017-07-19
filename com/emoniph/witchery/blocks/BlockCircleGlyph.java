/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.particle.NaturePowerFX;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCircleGlyph extends BlockBase
/*     */ {
/*     */   private int color;
/*     */   private boolean charged;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] icons;
/*     */   
/*     */   public BlockCircleGlyph(int color, boolean charged)
/*     */   {
/*  30 */     super(Material.field_151582_l);
/*  31 */     this.registerWithCreateTab = false;
/*     */     
/*  33 */     this.color = color;
/*  34 */     this.charged = charged;
/*     */     
/*  36 */     func_149752_b(1000.0F);
/*  37 */     func_149711_c(2.0F);
/*     */     
/*  39 */     float f = 0.5F;
/*  40 */     float f1 = 0.015625F;
/*  41 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_149699_a(World world, int posX, int posY, int posZ, EntityPlayer player)
/*     */   {
/*  46 */     if (!world.field_72995_K) {
/*  47 */       ItemStack itemstack = player.func_70694_bm();
/*  48 */       if ((itemstack != null) && (
/*  49 */         (Witchery.Items.GENERIC.itemBroom.isMatch(itemstack)) || (Witchery.Items.GENERIC.itemBroomEnchanted.isMatch(itemstack)))) {
/*  50 */         world.func_147480_a(posX, posY, posZ, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149636_a(World world, EntityPlayer player, int posX, int posY, int posZ, int meta) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegistrar)
/*     */   {
/*  67 */     this.icons = new IIcon[12];
/*  68 */     for (int glyph = 0; glyph < this.icons.length; glyph++) {
/*  69 */       this.icons[glyph] = iconRegistrar.func_94245_a(func_149641_N() + String.format("%d.%d", new Object[] { Integer.valueOf(this.color + 1), Integer.valueOf(glyph + 1) }));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int face, int metadata)
/*     */   {
/*  76 */     return this.icons[net.minecraft.util.MathHelper.func_76125_a(metadata, 0, 12)];
/*     */   }
/*     */   
/*     */   public net.minecraft.util.AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  81 */     return null;
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
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  96 */     return 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 102 */     if (this.color > 0) {
/* 103 */       double d0 = x + 0.4F + rand.nextFloat() * 0.2F;
/* 104 */       double d1 = y + 0.1F + rand.nextFloat() * 0.3F;
/* 105 */       double d2 = z + 0.4F + rand.nextFloat() * 0.2F;
/* 106 */       world.func_72869_a(this.color == 2 ? ParticleEffect.FLAME.toString() : ParticleEffect.PORTAL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/* 107 */     } else if (this.charged) {
/* 108 */       double d0 = x + 0.3F + rand.nextFloat() * 0.4F;
/* 109 */       double d1 = y + 0.1F + rand.nextFloat() * 0.3F;
/* 110 */       double d2 = z + 0.3F + rand.nextFloat() * 0.4F;
/*     */       
/* 112 */       NaturePowerFX sparkle = new NaturePowerFX(world, d0, d1, d2);
/* 113 */       sparkle.setScale(0.6F);
/* 114 */       sparkle.setGravity(0.01F);
/* 115 */       sparkle.setCanMove(true);
/* 116 */       double maxSpeed = 0.01D;
/* 117 */       double doubleSpeed = 0.02D;
/* 118 */       sparkle.func_70016_h(rand.nextDouble() * 0.02D - 0.01D, rand.nextDouble() * 0.02D + 0.01D, rand.nextDouble() * 0.02D - 0.01D);
/* 119 */       sparkle.setMaxAge(10 + rand.nextInt(5));
/* 120 */       float maxColorShift = 0.2F;
/* 121 */       float doubleColorShift = maxColorShift * 2.0F;
/* 122 */       float colorshiftR = rand.nextFloat() * doubleColorShift - maxColorShift;
/* 123 */       float colorshiftG = rand.nextFloat() * doubleColorShift - maxColorShift;
/* 124 */       float colorshiftB = rand.nextFloat() * doubleColorShift - maxColorShift;
/* 125 */       float red = 1.0F;
/* 126 */       float green = 0.8F;
/* 127 */       float blue = 0.2F;
/* 128 */       sparkle.func_70538_b(red + colorshiftR, green + colorshiftG, blue + colorshiftB);
/* 129 */       sparkle.func_82338_g(0.1F);
/* 130 */       net.minecraft.client.Minecraft.func_71410_x().field_71452_i.func_78873_a(sparkle);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 136 */     func_111046_k(world, x, y, z);
/*     */   }
/*     */   
/*     */   private boolean func_111046_k(World world, int x, int y, int z) {
/* 140 */     if (!func_149718_j(world, x, y, z)) {
/* 141 */       world.func_147468_f(x, y, z);
/* 142 */       return false;
/*     */     }
/* 144 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/* 150 */     Material materialBelow = world.func_147439_a(x, y - 1, z).func_149688_o();
/* 151 */     return (!world.func_147437_c(x, y - 1, z)) && (materialBelow != null) && (materialBelow.func_76218_k()) && (materialBelow.func_76220_a());
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess blockAccess, int x, int y, int z, int side)
/*     */   {
/* 157 */     return side == 1;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(net.minecraft.util.MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 162 */     Block block = world.func_147439_a(x, y, z);
/* 163 */     if (block == Witchery.Blocks.GLYPH_INFERNAL)
/* 164 */       return new ItemStack(Witchery.Items.CHALK_INFERNAL);
/* 165 */     if (block == Witchery.Blocks.GLYPH_OTHERWHERE) {
/* 166 */       return new ItemStack(Witchery.Items.CHALK_OTHERWHERE);
/*     */     }
/* 168 */     return new ItemStack(Witchery.Items.CHALK_RITUAL);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockCircleGlyph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */