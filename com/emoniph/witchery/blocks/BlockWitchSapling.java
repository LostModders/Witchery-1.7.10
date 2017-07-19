/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import com.emoniph.witchery.worldgen.WorldGenLargeWitchTree;
/*     */ import com.emoniph.witchery.worldgen.WorldGenWitchTree;
/*     */ import cpw.mods.fml.common.IFuelHandler;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.event.terraingen.TerrainGen;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWitchSapling
/*     */   extends BlockBaseBush
/*     */   implements IFuelHandler, IGrowable
/*     */ {
/*  33 */   private static final String[] WOOD_TYPES = { "rowan", "alder", "hawthorn" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] saplingIcon;
/*     */   
/*  37 */   public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  42 */       return BlockWitchSapling.WOOD_TYPES;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public IIcon func_77617_a(int par1)
/*     */     {
/*  48 */       return this.field_150939_a.func_149691_a(0, par1);
/*     */     }
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
/*     */   public BlockWitchSapling()
/*     */   {
/*  71 */     super(Material.field_151585_k, ClassItemBlock.class);
/*     */     
/*  73 */     func_149711_c(0.0F);
/*  74 */     func_149672_a(Block.field_149779_h);
/*  75 */     float f = 0.4F;
/*  76 */     func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
/*     */     
/*  78 */     GameRegistry.registerFuelHandler(this);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/*  83 */     if (!world.field_72995_K) {
/*  84 */       super.func_149674_a(world, x, y, z, rand);
/*     */       
/*  86 */       if ((world.func_72957_l(x, y + 1, z) >= 9) && (rand.nextInt(7) == 0)) {
/*  87 */         markOrGrowMarked(world, x, y, z, rand);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int metadata)
/*     */   {
/*  95 */     metadata &= 0x3;
/*  96 */     if ((metadata < 0) || (metadata >= this.saplingIcon.length)) {
/*  97 */       metadata = 0;
/*     */     }
/*  99 */     return this.saplingIcon[metadata];
/*     */   }
/*     */   
/*     */   public static void markOrGrowMarked(World world, int x, int y, int z, Random rand) {
/* 103 */     int l = world.func_72805_g(x, y, z);
/*     */     
/* 105 */     if ((l & 0x8) == 0) {
/* 106 */       world.func_72921_c(x, y, z, l | 0x8, 4);
/*     */     } else {
/* 108 */       growTree(world, x, y, z, rand);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void growTree(World world, int x, int y, int z, Random rand) {
/* 113 */     if (!TerrainGen.saplingGrowTree(world, rand, x, y, z)) {
/* 114 */       return;
/*     */     }
/* 116 */     int l = world.func_72805_g(x, y, z) & 0x3;
/* 117 */     Object object = null;
/* 118 */     int i1 = 0;
/* 119 */     int j1 = 0;
/* 120 */     boolean flag = false;
/*     */     
/* 122 */     if (l == 1) {
/* 123 */       WorldGenLargeWitchTree tree = new WorldGenLargeWitchTree(true, 1, 1, 0.5D);
/* 124 */       tree.func_76487_a(0.6D, 0.5D, 0.5D);
/* 125 */       object = tree;
/*     */     }
/* 127 */     else if (l == 2) {
/* 128 */       WorldGenLargeWitchTree tree = new WorldGenLargeWitchTree(true, 2, 2);
/* 129 */       tree.func_76487_a(0.8D, 1.2D, 1.0D);
/* 130 */       object = tree;
/*     */     } else {
/* 132 */       object = new WorldGenWitchTree(true, 5, 0, 0, 1, false);
/*     */     }
/*     */     
/* 135 */     if (flag) {
/* 136 */       world.func_147465_d(x + i1, y, z + j1, Blocks.field_150350_a, 0, 4);
/* 137 */       world.func_147465_d(x + i1 + 1, y, z + j1, Blocks.field_150350_a, 0, 4);
/* 138 */       world.func_147465_d(x + i1, y, z + j1 + 1, Blocks.field_150350_a, 0, 4);
/* 139 */       world.func_147465_d(x + i1 + 1, y, z + j1 + 1, Blocks.field_150350_a, 0, 4);
/*     */     } else {
/* 141 */       world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 4);
/*     */     }
/*     */     
/* 144 */     if (!((WorldGenerator)object).func_76484_a(world, rand, x + i1, y, z + j1)) {
/* 145 */       if (flag) {
/* 146 */         world.func_147465_d(x + i1, y, z + j1, Witchery.Blocks.SAPLING, l, 4);
/* 147 */         world.func_147465_d(x + i1 + 1, y, z + j1, Witchery.Blocks.SAPLING, l, 4);
/* 148 */         world.func_147465_d(x + i1, y, z + j1 + 1, Witchery.Blocks.SAPLING, l, 4);
/* 149 */         world.func_147465_d(x + i1 + 1, y, z + j1 + 1, Witchery.Blocks.SAPLING, l, 4);
/*     */       } else {
/* 151 */         world.func_147465_d(x, y, z, Witchery.Blocks.SAPLING, l, 4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isSameSapling(World world, int x, int y, int z, int metadata) {
/* 157 */     return (world.func_147439_a(x, y, z) == this) && ((world.func_72805_g(x, y, z) & 0x3) == metadata);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 162 */     return metadata & 0x3;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs creativeTabs, List list)
/*     */   {
/* 168 */     for (int i = 0; i < WOOD_TYPES.length; i++) {
/* 169 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 176 */     this.saplingIcon = new IIcon[WOOD_TYPES.length];
/* 177 */     for (int i = 0; i < this.saplingIcon.length; i++) {
/* 178 */       this.saplingIcon[i] = iconRegister.func_94245_a(func_149641_N() + "_" + WOOD_TYPES[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getBurnTime(ItemStack fuel)
/*     */   {
/* 184 */     if (Item.func_150898_a(this) == fuel.func_77973_b()) {
/* 185 */       return 100;
/*     */     }
/* 187 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean func_149851_a(World world, int rand, int x, int y, boolean z)
/*     */   {
/* 192 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149852_a(World world, Random rand, int x, int y, int z)
/*     */   {
/* 197 */     return world.field_73012_v.nextFloat() < 0.75D;
/*     */   }
/*     */   
/*     */   public void func_149853_b(World world, Random rand, int x, int y, int z)
/*     */   {
/* 202 */     markOrGrowMarked(world, x, y, z, rand);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchSapling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */