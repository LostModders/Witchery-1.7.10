/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryCreativeTab;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFire;
/*     */ import net.minecraft.block.BlockLeavesBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ 
/*     */ public class BlockWitchLeaves extends BlockLeavesBase implements IShearable
/*     */ {
/*  31 */   private static final String[] LEAF_TYPES = { "rowan", "alder", "hawthorn" };
/*     */   
/*     */   public static class ClassItemBlock extends MultiItemBlock {
/*     */     public ClassItemBlock(Block block) {
/*  35 */       super();
/*     */     }
/*     */     
/*     */     protected String[] getNames()
/*     */     {
/*  40 */       return BlockWitchLeaves.LEAF_TYPES;
/*     */     }
/*     */   }
/*     */   
/*  44 */   private static final String[][] field_94396_b = { { "_rowan", "_alder", "_hawthorn" }, { "_rowan_opaque", "_alder_opaque", "_hawthorn_opaque" } };
/*     */   
/*     */ 
/*     */   private int field_94394_cP;
/*     */   
/*     */   private int[] adjacentTreeBlocks;
/*     */   
/*  51 */   private IIcon[][] iconsForModes = new IIcon[2][];
/*     */   private int[] decayMatrix;
/*     */   
/*  54 */   public BlockWitchLeaves() { super(Material.field_151584_j, false);
/*     */     
/*  56 */     func_149711_c(0.2F);
/*  57 */     func_149713_g(1);
/*  58 */     func_149672_a(Block.field_149779_h);
/*  59 */     func_149675_a(true);
/*  60 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */ 
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  66 */     com.emoniph.witchery.util.BlockUtil.registerBlock(this, ClassItemBlock.class, blockName);
/*  67 */     super.func_149663_c(blockName);
/*  68 */     net.minecraft.init.Blocks.field_150480_ab.setFireInfo(this, 30, 60);
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D()
/*     */   {
/*  75 */     double d0 = 0.5D;
/*  76 */     double d1 = 1.0D;
/*  77 */     return net.minecraft.world.ColorizerFoliage.func_77470_a(d0, d1);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149741_i(int par1)
/*     */   {
/*  83 */     return (par1 & 0x3) == 2 ? getFoliageColorHawthorn() : (par1 & 0x3) == 1 ? getFoliageColorAlder() : getFoliageColorBasic();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int getFoliageColorAlder()
/*     */   {
/*  89 */     return 3774771;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int getFoliageColorHawthorn() {
/*  94 */     return 6728294;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int getFoliageColorBasic() {
/*  99 */     return 4764952;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 105 */     int l = world.func_72805_g(x, y, z);
/*     */     
/* 107 */     if ((l & 0x3) == 1)
/* 108 */       return getFoliageColorAlder();
/* 109 */     if ((l & 0x3) == 2) {
/* 110 */       return getFoliageColorHawthorn();
/*     */     }
/* 112 */     int i1 = 0;
/* 113 */     int j1 = 0;
/* 114 */     int k1 = 0;
/*     */     
/* 116 */     for (int l1 = -1; l1 <= 1; l1++) {
/* 117 */       for (int i2 = -1; i2 <= 1; i2++) {
/* 118 */         int j2 = world.func_72807_a(x + i2, z + l1).func_150571_c(x + l1, y, z + k1);
/* 119 */         i1 += ((j2 & 0xFF0000) >> 16);
/* 120 */         j1 += ((j2 & 0xFF00) >> 8);
/* 121 */         k1 += (j2 & 0xFF);
/*     */       }
/*     */     }
/*     */     
/* 125 */     return (i1 / 9 & 0xFF) << 16 | (j1 / 9 & 0xFF) << 8 | k1 / 9 & 0xFF;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block0, int meta0)
/*     */   {
/* 152 */     byte b0 = 1;
/* 153 */     int i1 = b0 + 1;
/*     */     
/* 155 */     if (world.func_72904_c(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
/* 156 */       for (int j1 = -b0; j1 <= b0; j1++) {
/* 157 */         for (int k1 = -b0; k1 <= b0; k1++) {
/* 158 */           for (int l1 = -b0; l1 <= b0; l1++) {
/* 159 */             Block block = world.func_147439_a(x + j1, y + k1, z + l1);
/* 160 */             if (block.isLeaves(world, x + j1, y + k1, z + l1)) {
/* 161 */               block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/* 172 */     if (!world.field_72995_K) {
/* 173 */       int meta = world.func_72805_g(x, y, z);
/*     */       
/* 175 */       if (((meta & 0x8) != 0) && ((meta & 0x4) == 0)) {
/* 176 */         byte b0 = 4;
/* 177 */         int i1 = b0 + 1;
/* 178 */         byte b1 = 32;
/* 179 */         int j1 = b1 * b1;
/* 180 */         int k1 = b1 / 2;
/*     */         
/* 182 */         if (this.decayMatrix == null) {
/* 183 */           this.decayMatrix = new int[b1 * b1 * b1];
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 188 */         if (world.func_72904_c(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
/*     */         {
/*     */ 
/*     */ 
/* 192 */           for (int l1 = -b0; l1 <= b0; l1++) {
/* 193 */             for (int i2 = -b0; i2 <= b0; i2++) {
/* 194 */               for (int j2 = -b0; j2 <= b0; j2++) {
/* 195 */                 Block block = world.func_147439_a(x + l1, y + i2, z + j2);
/*     */                 
/* 197 */                 if (!block.canSustainLeaves(world, x + l1, y + i2, z + j2)) {
/* 198 */                   if (block.isLeaves(world, x + l1, y + i2, z + j2)) {
/* 199 */                     this.decayMatrix[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = -2;
/*     */                   } else {
/* 201 */                     this.decayMatrix[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = -1;
/*     */                   }
/*     */                 } else {
/* 204 */                   this.decayMatrix[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = 0;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 210 */           for (l1 = 1; l1 <= 4; l1++) {
/* 211 */             for (int i2 = -b0; i2 <= b0; i2++) {
/* 212 */               for (int j2 = -b0; j2 <= b0; j2++) {
/* 213 */                 for (int k2 = -b0; k2 <= b0; k2++) {
/* 214 */                   if (this.decayMatrix[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1)] == l1 - 1) {
/* 215 */                     if (this.decayMatrix[((i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1)] == -2) {
/* 216 */                       this.decayMatrix[((i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1)] = l1;
/*     */                     }
/*     */                     
/* 219 */                     if (this.decayMatrix[((i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1)] == -2) {
/* 220 */                       this.decayMatrix[((i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1)] = l1;
/*     */                     }
/*     */                     
/* 223 */                     if (this.decayMatrix[((i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1)] == -2) {
/* 224 */                       this.decayMatrix[((i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1)] = l1;
/*     */                     }
/*     */                     
/* 227 */                     if (this.decayMatrix[((i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1)] == -2) {
/* 228 */                       this.decayMatrix[((i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1)] = l1;
/*     */                     }
/*     */                     
/* 231 */                     if (this.decayMatrix[((i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1))] == -2) {
/* 232 */                       this.decayMatrix[((i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1))] = l1;
/*     */                     }
/*     */                     
/* 235 */                     if (this.decayMatrix[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1)] == -2) {
/* 236 */                       this.decayMatrix[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1)] = l1;
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 245 */         int l1 = this.decayMatrix[(k1 * j1 + k1 * b1 + k1)];
/*     */         
/* 247 */         if (l1 >= 0) {
/* 248 */           world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 4);
/*     */         } else {
/* 250 */           removeLeaves(world, x, y, z);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 259 */     if ((world.func_72951_B(x, y + 1, z)) && (!World.func_147466_a(world, x, y - 1, z)) && (rand.nextInt(15) == 1))
/*     */     {
/* 261 */       double d0 = x + rand.nextFloat();
/* 262 */       double d1 = y - 0.05D;
/* 263 */       double d2 = z + rand.nextFloat();
/* 264 */       world.func_72869_a("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   private void removeLeaves(World world, int x, int y, int z) {
/* 269 */     func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 270 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/* 275 */     return rand.nextInt(20) == 0 ? 1 : 0;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 279 */     return Item.func_150898_a(Witchery.Blocks.SAPLING);
/*     */   }
/*     */   
/*     */   public void func_149690_a(World world, int x, int y, int z, int par5, float par6, int par7)
/*     */   {
/* 284 */     if (!world.field_72995_K) {
/* 285 */       int j1 = 20;
/*     */       
/* 287 */       if ((par5 & 0x3) == 3) {
/* 288 */         j1 = 40;
/*     */       }
/*     */       
/* 291 */       if (par7 > 0) {
/* 292 */         j1 -= (2 << par7);
/*     */         
/* 294 */         if (j1 < 10) {
/* 295 */           j1 = 10;
/*     */         }
/*     */       }
/*     */       
/* 299 */       if (world.field_73012_v.nextInt(j1) == 0) {
/* 300 */         Item k1 = func_149650_a(par5, world.field_73012_v, par7);
/* 301 */         func_149642_a(world, x, y, z, new ItemStack(k1, 1, func_149692_a(par5)));
/*     */       }
/*     */       
/* 304 */       j1 = 200;
/*     */       
/* 306 */       if (par7 > 0) {
/* 307 */         j1 -= (10 << par7);
/*     */         
/* 309 */         if (j1 < 40) {
/* 310 */           j1 = 40;
/*     */         }
/*     */       }
/*     */       
/* 314 */       if (((par5 & 0x3) == 0) && (world.field_73012_v.nextInt(j1) == 0)) {
/* 315 */         func_149642_a(world, x, y, z, Witchery.Items.GENERIC.itemRowanBerries.createStack());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer player, int par3, int par4, int par5, int par6)
/*     */   {
/* 322 */     super.func_149636_a(world, player, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 327 */     return par1 & 0x3;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 332 */     setGraphicsLevel(Witchery.proxy.getGraphicsLevel());
/* 333 */     return !this.field_150121_P;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/* 339 */     return (par2 & 0x3) == 2 ? this.iconsForModes[this.field_94394_cP][2] : (par2 & 0x3) == 1 ? this.iconsForModes[this.field_94394_cP][1] : this.iconsForModes[this.field_94394_cP][0];
/*     */   }
/*     */   
/*     */ 
/*     */   public void setGraphicsLevel(boolean par1)
/*     */   {
/* 345 */     this.field_150121_P = par1;
/* 346 */     this.field_94394_cP = (par1 ? 0 : 1);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs creativeTabs, List list)
/*     */   {
/* 352 */     for (int i = 0; i < LEAF_TYPES.length; i++) {
/* 353 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   protected ItemStack func_149644_j(int par1)
/*     */   {
/* 359 */     return new ItemStack(this, 1, par1 & 0x3);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/* 365 */     for (int i = 0; i < field_94396_b.length; i++) {
/* 366 */       this.iconsForModes[i] = new IIcon[field_94396_b[i].length];
/*     */       
/* 368 */       for (int j = 0; j < field_94396_b[i].length; j++) {
/* 369 */         this.iconsForModes[i][j] = par1IconRegister.func_94245_a(func_149641_N() + field_94396_b[i][j]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
/*     */   {
/* 377 */     return true;
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
/*     */   {
/* 382 */     ArrayList<ItemStack> ret = new ArrayList();
/* 383 */     ret.add(new ItemStack(this, 1, world.func_72805_g(x, y, z) & 0x3));
/* 384 */     return ret;
/*     */   }
/*     */   
/*     */   public void beginLeavesDecay(World world, int x, int y, int z)
/*     */   {
/* 389 */     world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) | 0x8, 4);
/*     */   }
/*     */   
/*     */   public boolean isLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 394 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchLeaves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */