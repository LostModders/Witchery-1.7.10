/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPitDirt extends BlockBase
/*     */ {
/*  21 */   private static final Material passThrough = new Material(MapColor.field_151664_l)
/*     */   {
/*     */     public boolean func_76230_c() {
/*  24 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  29 */     public boolean func_76218_k() { return false; }
/*     */   };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconPodzolTop;
/*     */   
/*  34 */   public BlockPitDirt() { super(passThrough);
/*  35 */     func_149711_c(0.5F);
/*  36 */     func_149672_a(field_149767_g);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  41 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  46 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconPodzolSide;
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
/*     */   {
/*  58 */     if (p_149691_2_ == 2) {
/*  59 */       if (p_149691_1_ == 1) {
/*  60 */         return this.iconPodzolTop;
/*     */       }
/*     */       
/*  63 */       if (p_149691_1_ != 0) {
/*  64 */         return this.iconPodzolSide;
/*     */       }
/*     */     }
/*     */     
/*  68 */     return this.field_149761_L;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int meta)
/*     */   {
/*  73 */     return 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  79 */     int i1 = world.func_72805_g(x, y, z);
/*     */     
/*  81 */     if (i1 == 2) {
/*  82 */       if (side == 1) {
/*  83 */         return this.iconPodzolTop;
/*     */       }
/*     */       
/*  86 */       if (side != 0) {
/*  87 */         Material material = world.func_147439_a(x, y + 1, z).func_149688_o();
/*     */         
/*  89 */         if ((material == Material.field_151597_y) || (material == Material.field_151596_z)) {
/*  90 */           return Blocks.field_150349_c.func_149673_e(world, x, y, z, side);
/*     */         }
/*     */         
/*  93 */         Block block = world.func_147439_a(x, y + 1, z);
/*     */         
/*  95 */         if ((block != Blocks.field_150346_d) && (block != Blocks.field_150349_c)) {
/*  96 */           return this.iconPodzolSide;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 101 */     return this.field_149761_L;
/*     */   }
/*     */   
/*     */   protected ItemStack func_149644_j(int p_149644_1_)
/*     */   {
/* 106 */     if (p_149644_1_ == 1) {
/* 107 */       p_149644_1_ = 0;
/*     */     }
/*     */     
/* 110 */     return super.func_149644_j(p_149644_1_);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List list)
/*     */   {
/* 116 */     list.add(new ItemStack(this, 1, 0));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 122 */     super.func_149651_a(iconRegister);
/* 123 */     this.iconPodzolTop = iconRegister.func_94245_a(func_149641_N() + "_" + "podzol_top");
/* 124 */     this.iconPodzolSide = iconRegister.func_94245_a(func_149641_N() + "_" + "podzol_side");
/*     */   }
/*     */   
/*     */   public int func_149643_k(World world, int x, int y, int z)
/*     */   {
/* 129 */     int l = world.func_72805_g(x, y, z);
/*     */     
/* 131 */     if (l == 1) {
/* 132 */       l = 0;
/*     */     }
/*     */     
/* 135 */     return l;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPitDirt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */