/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class BlockPitGrass extends BlockBase
/*     */ {
/*  22 */   private static final Material passThrough = new Material(MapColor.field_151661_c)
/*     */   {
/*     */     public boolean func_76230_c() {
/*  25 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  30 */     public boolean func_76218_k() { return false; }
/*     */   };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconTop;
/*     */   
/*  35 */   public BlockPitGrass() { super(passThrough);
/*  36 */     func_149711_c(0.6F);
/*  37 */     func_149672_a(field_149779_h);
/*  38 */     func_149675_a(false);
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  43 */     return Witchery.proxy.getPitGrassRenderId();
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  48 */     return null;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random rand, int p_149650_3_)
/*     */   {
/*  53 */     return Item.func_150898_a(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconSideSnowed;
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconSideOverlay;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
/*     */   {
/*  78 */     return p_149691_1_ == 0 ? Blocks.field_150346_d.func_149733_h(p_149691_1_) : p_149691_1_ == 1 ? this.iconTop : this.field_149761_L;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  85 */     if (side == 1)
/*  86 */       return this.iconTop;
/*  87 */     if (side == 0) {
/*  88 */       return Blocks.field_150346_d.func_149733_h(side);
/*     */     }
/*  90 */     Material material = world.func_147439_a(x, y + 1, z).func_149688_o();
/*  91 */     return (material != Material.field_151597_y) && (material != Material.field_151596_z) ? this.field_149761_L : this.iconSideSnowed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/*  99 */     this.field_149761_L = iconRegister.func_94245_a(func_149641_N() + "_side");
/* 100 */     this.iconTop = iconRegister.func_94245_a(func_149641_N() + "_top");
/* 101 */     this.iconSideSnowed = iconRegister.func_94245_a(func_149641_N() + "_side_snowed");
/* 102 */     this.iconSideOverlay = iconRegister.func_94245_a(func_149641_N() + "_side_overlay");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D()
/*     */   {
/* 108 */     double d0 = 0.5D;
/* 109 */     double d1 = 1.0D;
/* 110 */     return ColorizerGrass.func_77480_a(d0, d1);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149741_i(int meta)
/*     */   {
/* 116 */     return func_149635_D();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 122 */     int l = 0;
/* 123 */     int i1 = 0;
/* 124 */     int j1 = 0;
/*     */     
/* 126 */     for (int k1 = -1; k1 <= 1; k1++) {
/* 127 */       for (int l1 = -1; l1 <= 1; l1++) {
/* 128 */         int i2 = world.func_72807_a(x + l1, z + k1).func_150558_b(x + l1, y, z + k1);
/* 129 */         l += ((i2 & 0xFF0000) >> 16);
/* 130 */         i1 += ((i2 & 0xFF00) >> 8);
/* 131 */         j1 += (i2 & 0xFF);
/*     */       }
/*     */     }
/*     */     
/* 135 */     return (l / 9 & 0xFF) << 16 | (i1 / 9 & 0xFF) << 8 | j1 / 9 & 0xFF;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPitGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */