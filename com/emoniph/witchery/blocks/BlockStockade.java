/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockStockade
/*     */   extends BlockBase
/*     */ {
/*  28 */   public static final String[] WOOD_TEXTURES = { "log_oak", "log_spruce", "log_birch", "log_jungle", "witchery:log_rowan", "witchery:log_alder", "witchery:log_hawthorn", "log_acacia", "log_big_oak" };
/*     */   
/*     */ 
/*     */ 
/*  32 */   public static final String[] WOOD_NAMES = { "oak", "spruce", "birch", "jungle", "rowan", "alder", "hawthorn", "acacia", "big_oak" };
/*     */   
/*     */ 
/*  35 */   public static final String[] ICE_TEXTURES = { "ice" };
/*     */   
/*  37 */   public static final String[] ICE_NAMES = { "ice" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] tree;
/*     */   
/*  41 */   public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  46 */       return ((BlockStockade)this.field_150939_a).alpha ? BlockStockade.ICE_NAMES : BlockStockade.WOOD_NAMES;
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata) {
/*  51 */     if (metadata >= 0) { if (metadata < (this.alpha ? ICE_NAMES.length : WOOD_NAMES.length)) {}
/*  52 */     } else { metadata = 0;
/*     */     }
/*  54 */     return metadata;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] tree_top;
/*     */   
/*     */   private final boolean alpha;
/*     */   
/*     */   private boolean tipTexturing;
/*     */   public BlockStockade(boolean alpha)
/*     */   {
/*  66 */     super(alpha ? Material.field_151588_w : Material.field_151575_d, ClassItemBlock.class);
/*     */     
/*  68 */     func_149711_c(25.0F);
/*  69 */     func_149752_b(20.0F);
/*     */     
/*  71 */     this.alpha = alpha;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  76 */     return Witchery.proxy.getStockageRenderId();
/*     */   }
/*     */   
/*     */   public void func_149724_b(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  81 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase))) {
/*  82 */       EntityLivingBase living = (EntityLivingBase)entity;
/*  83 */       living.func_70097_a(DamageSource.field_76367_g, 3.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity)
/*     */   {
/*  90 */     boolean connectN = canConnectFenceTo(world, x, y, z - 1);
/*  91 */     boolean connectS = canConnectFenceTo(world, x, y, z + 1);
/*  92 */     boolean connectW = canConnectFenceTo(world, x - 1, y, z);
/*  93 */     boolean connectE = canConnectFenceTo(world, x + 1, y, z);
/*  94 */     float f = 0.375F;
/*  95 */     float f1 = 0.625F;
/*  96 */     float f2 = 0.375F;
/*  97 */     float f3 = 0.625F;
/*     */     
/*  99 */     if (connectN) {
/* 100 */       f2 = 0.0F;
/*     */     }
/*     */     
/* 103 */     if (connectS) {
/* 104 */       f3 = 1.0F;
/*     */     }
/*     */     
/* 107 */     if ((!connectN) && (!connectS) && (!connectE) && (!connectW)) {
/* 108 */       func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.9F, 0.7F);
/* 109 */       super.func_149743_a(world, x, y, z, bb, list, entity);
/*     */     }
/*     */     
/* 112 */     if ((connectN) || (connectS)) {
/* 113 */       func_149676_a(0.3F, 0.0F, 0.05F, 0.7F, (connectE) || (connectW) ? 1.0F : 0.9F, 0.95F);
/* 114 */       super.func_149743_a(world, x, y, z, bb, list, entity);
/*     */     }
/*     */     
/* 117 */     if ((connectE) || (connectW)) {
/* 118 */       func_149676_a(0.05F, 0.0F, 0.3F, 0.55F, (connectN) || (connectS) ? 1.0F : 0.9F, 0.7F);
/* 119 */       super.func_149743_a(world, x, y, z, bb, list, entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 125 */     boolean connectN = canConnectFenceTo(world, x, y, z - 1);
/* 126 */     boolean connectS = canConnectFenceTo(world, x, y, z + 1);
/* 127 */     boolean connectW = canConnectFenceTo(world, x - 1, y, z);
/* 128 */     boolean connectE = canConnectFenceTo(world, x + 1, y, z);
/* 129 */     float f = 0.3F;
/* 130 */     float f1 = 0.3F;
/* 131 */     float f2 = 0.7F;
/* 132 */     float f3 = 0.7F;
/*     */     
/* 134 */     if ((connectN) || (connectS))
/*     */     {
/* 136 */       f1 = 0.05F;
/* 137 */       f3 = 0.95F;
/*     */     }
/*     */     
/* 140 */     if ((connectE) || (connectW)) {
/* 141 */       f = 0.05F;
/* 142 */       f2 = 0.95F;
/*     */     }
/*     */     
/* 145 */     func_149676_a(f, 0.0F, f1, f2, ((connectN) || (connectS)) && ((connectW) || (connectE)) ? 1.0F : 0.9F, f3);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149701_w()
/*     */   {
/* 160 */     return this.alpha ? 1 : super.func_149701_w();
/*     */   }
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_)
/*     */   {
/* 165 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_) {
/* 169 */     Block block = p_149826_1_.func_147439_a(p_149826_2_, p_149826_3_, p_149826_4_);
/* 170 */     return (block == this) || (block == Blocks.field_150396_be) || (block == Witchery.Blocks.PERPETUAL_ICE_FENCE_GATE);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 177 */     if (world.func_147439_a(x, y, z) == this)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 185 */       if (side == 1) {
/* 186 */         boolean aboveX = (world.func_147439_a(x + 1, y, z) == this) || (world.func_147439_a(x - 1, y, z) == this);
/* 187 */         boolean aboveZ = (world.func_147439_a(x, y, z + 1) == this) || (world.func_147439_a(x, y, z - 1) == this);
/* 188 */         boolean sideX = (world.func_147439_a(x + 1, y - 1, z) == this) || (world.func_147439_a(x - 1, y - 1, z) == this);
/* 189 */         boolean sideZ = (world.func_147439_a(x, y - 1, z + 1) == this) || (world.func_147439_a(x, y - 1, z - 1) == this);
/*     */         
/* 191 */         if ((aboveX) && (sideX) && (aboveZ) && (sideZ))
/* 192 */           return false;
/* 193 */         if ((sideX) && (!aboveX))
/* 194 */           return true;
/* 195 */         if ((sideZ) && (!aboveZ)) {
/* 196 */           return true;
/*     */         }
/* 198 */         return false;
/*     */       }
/* 200 */       if (side == 0) {
/* 201 */         boolean sideX = (world.func_147439_a(x + 1, y, z) == this) || (world.func_147439_a(x - 1, y, z) == this);
/* 202 */         boolean sideZ = (world.func_147439_a(x, y, z + 1) == this) || (world.func_147439_a(x, y, z - 1) == this);
/* 203 */         boolean aboveX = (world.func_147439_a(x + 1, y + 1, z) == this) || (world.func_147439_a(x - 1, y + 1, z) == this);
/* 204 */         boolean aboveZ = (world.func_147439_a(x, y + 1, z + 1) == this) || (world.func_147439_a(x, y + 1, z - 1) == this);
/*     */         
/* 206 */         if ((aboveX) && (sideX) && (aboveZ) && (sideZ))
/* 207 */           return false;
/* 208 */         if ((sideX) && (!aboveX))
/* 209 */           return true;
/* 210 */         if ((sideZ) && (!aboveZ)) {
/* 211 */           return true;
/*     */         }
/* 213 */         return false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 218 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 223 */     if (meta >= 0) { if (meta < (this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length)) {}
/* 224 */     } else { meta = 0;
/*     */     }
/* 226 */     return (side == 1) || (side == 0) || (this.tipTexturing) ? this.tree_top[meta] : this.tree[meta];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item block, CreativeTabs creativeTabs, List list)
/*     */   {
/* 232 */     for (int i = 0; i < (this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length); i++) {
/* 233 */       list.add(new ItemStack(this, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 240 */     this.tree = new IIcon[this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length];
/* 241 */     this.tree_top = new IIcon[this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length];
/*     */     
/* 243 */     if (this.alpha) {
/* 244 */       for (int i = 0; i < this.tree.length; i++) {
/* 245 */         this.tree[i] = iconRegister.func_94245_a(ICE_TEXTURES[i]);
/* 246 */         this.tree_top[i] = iconRegister.func_94245_a(ICE_TEXTURES[i] + (ICE_TEXTURES[i].equals("ice") ? "" : "_top"));
/*     */       }
/*     */       
/*     */     } else {
/* 250 */       for (int i = 0; i < this.tree.length; i++) {
/* 251 */         this.tree[i] = iconRegister.func_94245_a(WOOD_TEXTURES[i]);
/* 252 */         this.tree_top[i] = iconRegister.func_94245_a(WOOD_TEXTURES[i] + "_top");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTipTexture(boolean b)
/*     */   {
/* 260 */     this.tipTexturing = b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockStockade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */