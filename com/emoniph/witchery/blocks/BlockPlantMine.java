/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityWitchProjectile;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDeadBush;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPlantMine
/*     */   extends BlockBase
/*     */   implements IPlantable
/*     */ {
/*  36 */   private static final String[] woodType = { "rose_webs", "rose_ink", "rose_thorns", "rose_sprouting", "dandelion_webs", "dandelion_ink", "dandelion_thorns", "dandelion_sprouting", "grass_webs", "grass_ink", "grass_thorns", "grass_sprouting" };
/*     */   
/*     */   public static class ClassItemBlock extends MultiItemBlock
/*     */   {
/*     */     public ClassItemBlock(Block block) {
/*  41 */       super();
/*     */     }
/*     */     
/*     */     protected String[] getNames()
/*     */     {
/*  46 */       return BlockPlantMine.woodType;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public IIcon func_77617_a(int par1)
/*     */     {
/*  52 */       return this.field_150939_a.func_149691_a(0, par1);
/*     */     }
/*     */   }
/*     */   
/*  56 */   private final float RADIUS = 0.2F;
/*     */   public static final int MINE_ROSE_WEBS = 0;
/*     */   
/*  59 */   public BlockPlantMine() { super(Material.field_151585_k, ClassItemBlock.class);
/*     */     
/*  61 */     func_149675_a(true);
/*  62 */     func_149711_c(6.0F);
/*  63 */     func_149752_b(1000.0F);
/*  64 */     func_149672_a(field_149779_h);
/*     */     
/*  66 */     func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*     */   {
/*  71 */     if (!world.field_72995_K) {
/*  72 */       int metadata = world.func_72805_g(posX, posY, posZ);
/*  73 */       int effect = metadata & 0x3;
/*     */       
/*  75 */       world.func_147468_f(posX, posY, posZ);
/*     */       
/*  77 */       ParticleEffect.MAGIC_CRIT.send(SoundEffect.RANDOM_EXPLODE, world, 0.5D + posX, 0.05D + posY, 0.5D + posZ, 0.5D, 1.0D, 16);
/*     */       
/*     */ 
/*  80 */       switch (effect) {
/*     */       case 0: 
/*  82 */         EntityWitchProjectile.explodeWeb(world, posX, posY, posZ, 1, false);
/*  83 */         break;
/*     */       case 1: 
/*  85 */         EntityWitchProjectile.explodeInk(world, posX, posY, posZ, entity, AxisAlignedBB.func_72330_a(posX, posY, posZ, posX + 1, posY + 1, posZ + 1), false);
/*     */         
/*  87 */         break;
/*     */       case 2: 
/*  89 */         EntityWitchProjectile.plantCactus(world, posX, posY, posZ, 4);
/*  90 */         break;
/*     */       case 3: 
/*  92 */         EntityWitchProjectile.growBranch(posX, posY, posZ, world, 1, 10, AxisAlignedBB.func_72330_a(posX, posY, posZ, posX + 1, posY + 1, posZ + 1));
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */   public static final int MINE_ROSE_INK = 1;
/*     */   public static final int MINE_ROSE_THORNS = 2;
/*     */   public Item func_149650_a(int par1, Random rand, int fortune)
/*     */   {
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 107 */     return 0;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 112 */     return new ItemStack(this, 1, world.func_72805_g(x, y, z));
/*     */   }
/*     */   
/*     */ 
/*     */   public static final int MINE_ROSE_SPROUTING = 3;
/*     */   
/*     */   public static final int MINE_DANDELION_WEBS = 4;
/*     */   
/*     */   public static final int MINE_DANDELION_INK = 5;
/*     */   
/*     */   public static final int MINE_DANDELION_THORNS = 6;
/*     */   public static final int MINE_DANDELION_SPROUTING = 7;
/*     */   public static final int MINE_SHRUB_WEBS = 8;
/*     */   public static final int MINE_SHRUB_INK = 9;
/*     */   public static final int MINE_SHRUB_THORNS = 10;
/*     */   public static final int MINE_SHRUB_SPROUTING = 11;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs creativeTabs, List list)
/*     */   {
/* 131 */     list.add(new ItemStack(item, 1, 0));
/* 132 */     list.add(new ItemStack(item, 1, 1));
/* 133 */     list.add(new ItemStack(item, 1, 2));
/* 134 */     list.add(new ItemStack(item, 1, 3));
/*     */     
/* 136 */     list.add(new ItemStack(item, 1, 4));
/* 137 */     list.add(new ItemStack(item, 1, 5));
/* 138 */     list.add(new ItemStack(item, 1, 6));
/* 139 */     list.add(new ItemStack(item, 1, 7));
/*     */     
/* 141 */     list.add(new ItemStack(item, 1, 8));
/* 142 */     list.add(new ItemStack(item, 1, 9));
/* 143 */     list.add(new ItemStack(item, 1, 10));
/* 144 */     list.add(new ItemStack(item, 1, 11));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int metadata)
/*     */   {
/* 150 */     metadata = metadata >>> 2 & 0x3;
/* 151 */     if ((metadata < 0) || (metadata >= 4)) {
/* 152 */       metadata = 0;
/*     */     }
/* 154 */     switch (metadata) {
/*     */     case 0: 
/*     */     default: 
/* 157 */       return Blocks.field_150328_O.func_149691_a(0, 0);
/*     */     case 1: 
/* 159 */       return Blocks.field_150327_N.func_149691_a(0, 0);
/*     */     }
/* 161 */     return Blocks.field_150330_I.func_149691_a(0, 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/* 167 */     return (super.func_149742_c(par1World, par2, par3, par4)) && (func_149718_j(par1World, par2, par3, par4));
/*     */   }
/*     */   
/*     */   protected boolean canPlaceBlockOn(Block block) {
/* 171 */     return (block == Blocks.field_150349_c) || (block == Blocks.field_150346_d) || (block == Blocks.field_150458_ak) || (block == Blocks.field_150354_m) || (block == Blocks.field_150391_bh);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 177 */     super.func_149695_a(par1World, par2, par3, par4, par5);
/* 178 */     checkFlowerChange(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 183 */     checkFlowerChange(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   protected final void checkFlowerChange(World par1World, int par2, int par3, int par4) {
/* 187 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/* 188 */       func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/* 189 */       par1World.func_147465_d(par2, par3, par4, Blocks.field_150350_a, 0, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149718_j(World par1World, int par2, int par3, int par4)
/*     */   {
/* 195 */     Block soil = par1World.func_147439_a(par2, par3 - 1, par4);
/* 196 */     return ((par1World.func_72883_k(par2, par3, par4) >= 8) || (par1World.func_72937_j(par2, par3, par4))) && (soil != null) && ((soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this)) || (soil == Blocks.field_150354_m) || (soil == Blocks.field_150391_bh));
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/* 202 */     float f = 0.0625F;
/* 203 */     return AxisAlignedBB.func_72330_a(par2 + 0.5F - 0.2F + f, par3, par4 + 0.5F - 0.2F + f, par2 + 0.5F + 0.2F - f, par3 + 0.6F - f, par4 + 0.5F + 0.2F - f);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/* 210 */     float f = 0.0625F;
/* 211 */     return AxisAlignedBB.func_72330_a(par2 + 0.5F - 0.2F + f, par3, par4 + 0.5F - 0.2F + f, par2 + 0.5F + 0.2F - f, par3 + 0.6F - f, par4 + 0.5F + 0.2F - f);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 217 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 227 */     return 1;
/*     */   }
/*     */   
/*     */   public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 232 */     return EnumPlantType.Plains;
/*     */   }
/*     */   
/*     */   public Block getPlant(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 237 */     return this;
/*     */   }
/*     */   
/*     */   public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 242 */     return world.func_72805_g(x, y, z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPlantMine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */