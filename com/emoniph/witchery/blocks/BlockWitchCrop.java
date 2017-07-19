/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.EntityMandrake;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.network.PacketParticles;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockWitchCrop extends BlockBaseBush implements IGrowable
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] iconArray;
/*     */   private ItemStack seedItemPrototype;
/*     */   private ItemStack cropItemPrototype;
/*     */   private final int growthStages;
/*     */   private final boolean waterPlant;
/*     */   private final boolean canFertilize;
/*     */   private static final int MIN_LIGHT_LEVEL = 9;
/*     */   private static final double NIGHT_MANDRAKE_SPAWN_CHANCE = 0.1D;
/*     */   private static final double DAY_MANDRAKE_SPAWN_CHANCE = 0.9D;
/*     */   
/*     */   public BlockWitchCrop(boolean waterPlant)
/*     */   {
/*  48 */     this(waterPlant, 4, true);
/*     */   }
/*     */   
/*     */   public BlockWitchCrop(boolean waterPlant, int growthStages, boolean canFertilize) {
/*  52 */     super(Material.field_151585_k);
/*  53 */     this.registerWithCreateTab = false;
/*     */     
/*  55 */     this.growthStages = growthStages;
/*  56 */     this.waterPlant = waterPlant;
/*  57 */     this.canFertilize = canFertilize;
/*  58 */     func_149675_a(true);
/*     */     
/*  60 */     float f = 0.5F;
/*  61 */     func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
/*  62 */     func_149711_c(0.0F);
/*  63 */     func_149672_a(Block.field_149779_h);
/*  64 */     func_149649_H();
/*     */   }
/*     */   
/*     */   public BlockWitchCrop setSeedItem(ItemStack stack) {
/*  68 */     this.seedItemPrototype = stack;
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public BlockWitchCrop setCropItem(ItemStack stack) {
/*  73 */     this.cropItemPrototype = (stack != null ? stack : this.seedItemPrototype.func_77946_l());
/*  74 */     return this;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/*  80 */     this.iconArray = new IIcon[getNumGrowthStages() + 1];
/*     */     
/*  82 */     for (int i = 0; i < this.iconArray.length; i++) {
/*  83 */       this.iconArray[i] = iconRegister.func_94245_a(func_149641_N() + "_stage_" + i);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean func_149854_a(Block block)
/*     */   {
/*  89 */     return block == net.minecraft.init.Blocks.field_150355_j;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getNumGrowthStages()
/*     */   {
/*  96 */     return this.growthStages;
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int posX, int posY, int posZ, Random rand)
/*     */   {
/* 101 */     super.func_149674_a(world, posX, posY, posZ, rand);
/*     */     
/* 103 */     if (world.func_72957_l(posX, posY + 1, posZ) >= 9) {
/* 104 */       int l = world.func_72805_g(posX, posY, posZ);
/*     */       
/* 106 */       if (l < getNumGrowthStages()) {
/* 107 */         float f = getGrowthRate(world, posX, posY, posZ);
/*     */         
/* 109 */         if (rand.nextInt((int)(25.0F / f) + 1) == 0) {
/* 110 */           l++;
/* 111 */           world.func_72921_c(posX, posY, posZ, l, 2);
/*     */         }
/* 113 */       } else if (this == Witchery.Blocks.CROP_WORMWOOD) {
/* 114 */         Block blockBelow = BlockUtil.getBlock(world, posX, posY - 1, posZ);
/* 115 */         if ((blockBelow != this) && (world.func_147437_c(posX, posY + 1, posZ))) {
/* 116 */           BlockUtil.setBlock(world, posX, posY + 1, posZ, this, 0, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean fertilize(World world, int posX, int posY, int posZ) {
/* 123 */     if (!world.field_72995_K) {
/* 124 */       int stages = getNumGrowthStages();
/* 125 */       int current = world.func_72805_g(posX, posY, posZ);
/* 126 */       if (current == stages) {
/* 127 */         return false;
/*     */       }
/*     */       int l;
/*     */       int l;
/* 131 */       if (!this.canFertilize) {
/* 132 */         l = current + 1;
/*     */       } else {
/* 134 */         l = current + MathHelper.func_76136_a(world.field_73012_v, 2, stages);
/*     */       }
/*     */       
/* 137 */       if (l > stages) {
/* 138 */         l = stages;
/*     */       }
/*     */       
/* 141 */       world.func_72921_c(posX, posY, posZ, l, 2);
/*     */       
/* 143 */       return true;
/*     */     }
/*     */     
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149851_a(World world, int x, int y, int z, boolean flag)
/*     */   {
/* 151 */     return world.func_72805_g(x, y, z) != getNumGrowthStages();
/*     */   }
/*     */   
/*     */   public boolean func_149852_a(World world, Random rand, int x, int y, int z)
/*     */   {
/* 156 */     return true;
/*     */   }
/*     */   
/*     */   public void func_149853_b(World world, Random rand, int x, int y, int z)
/*     */   {
/* 161 */     fertilize(world, x, y, z);
/*     */   }
/*     */   
/*     */   private float getGrowthRate(World world, int posX, int posY, int posZ) {
/* 165 */     float f = 1.0F;
/* 166 */     Block l = world.func_147439_a(posX, posY, posZ - 1);
/* 167 */     Block i1 = world.func_147439_a(posX, posY, posZ + 1);
/* 168 */     Block j1 = world.func_147439_a(posX - 1, posY, posZ);
/* 169 */     Block k1 = world.func_147439_a(posX + 1, posY, posZ);
/* 170 */     Block l1 = world.func_147439_a(posX - 1, posY, posZ - 1);
/* 171 */     Block i2 = world.func_147439_a(posX + 1, posY, posZ - 1);
/* 172 */     Block j2 = world.func_147439_a(posX + 1, posY, posZ + 1);
/* 173 */     Block k2 = world.func_147439_a(posX - 1, posY, posZ + 1);
/* 174 */     boolean flag = (j1 == this) || (k1 == this);
/* 175 */     boolean flag1 = (l == this) || (i1 == this);
/* 176 */     boolean flag2 = (l1 == this) || (i2 == this) || (j2 == this) || (k2 == this);
/*     */     
/* 178 */     for (int l2 = posX - 1; l2 <= posX + 1; l2++) {
/* 179 */       for (int i3 = posZ - 1; i3 <= posZ + 1; i3++) {
/* 180 */         Block j3 = world.func_147439_a(l2, posY - 1, i3);
/* 181 */         float f1 = 0.0F;
/*     */         
/* 183 */         if ((j3 != null) && (j3.canSustainPlant(world, l2, posY - 1, i3, ForgeDirection.UP, this))) {
/* 184 */           f1 = 1.0F;
/*     */           
/* 186 */           if (j3.isFertile(world, l2, posY - 1, i3)) {
/* 187 */             f1 = 3.0F;
/*     */           }
/*     */         }
/*     */         
/* 191 */         if ((l2 != posX) || (i3 != posZ)) {
/* 192 */           f1 /= 4.0F;
/*     */         }
/*     */         
/* 195 */         f += f1;
/*     */       }
/*     */     }
/*     */     
/* 199 */     if ((flag2) || ((flag) && (flag1))) {
/* 200 */       f /= 2.0F;
/*     */     }
/*     */     
/*     */ 
/* 204 */     if (this.cropItemPrototype.func_77973_b() == Witchery.Items.SEEDS_MINDRAKE) {
/* 205 */       f /= 1.5F;
/*     */     }
/*     */     
/* 208 */     return f;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/* 214 */     int stages = getNumGrowthStages();
/* 215 */     if ((par2 < 0) || (par2 > stages)) {
/* 216 */       par2 = stages;
/*     */     }
/*     */     
/* 219 */     return this.iconArray != null ? this.iconArray[par2] : null;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 224 */     return (this == Witchery.Blocks.CROP_SNOWBELL) || (this == Witchery.Blocks.CROP_WOLFSBANE) || (this == Witchery.Blocks.CROP_WORMWOOD) ? 1 : 6;
/*     */   }
/*     */   
/*     */   protected ItemStack getSeedItemStack()
/*     */   {
/* 229 */     return this.seedItemPrototype.func_77946_l();
/*     */   }
/*     */   
/*     */   protected ItemStack getCropItemStack() {
/* 233 */     return this.cropItemPrototype.func_77946_l();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149690_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
/*     */   {
/* 239 */     super.func_149690_a(par1World, par2, par3, par4, par5, par6, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 247 */     ArrayList<ItemStack> ret = new ArrayList();
/* 248 */     Block block = world.func_147439_a(x, y, z);
/* 249 */     if (metadata >= getNumGrowthStages()) {
/* 250 */       if ((!Witchery.Items.GENERIC.itemMandrakeRoot.isMatch(this.cropItemPrototype)) || (world.field_73013_u == EnumDifficulty.PEACEFUL) || ((world.field_73011_w.isDaytime()) && (world.field_73012_v.nextDouble() > 0.9D)) || ((!world.field_73011_w.isDaytime()) && (world.field_73012_v.nextDouble() > 0.1D)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 255 */         if (this.cropItemPrototype.func_77973_b() == Witchery.Items.SEEDS_MINDRAKE) {
/* 256 */           ret.add(getSeedItemStack());
/* 257 */           if (world.field_73012_v.nextInt(4) == 0) {
/* 258 */             ret.add(getCropItemStack());
/*     */           }
/*     */         } else {
/* 261 */           for (int n = 0; n < 3 + fortune; n++) {
/* 262 */             if (world.field_73012_v.nextInt(15) <= 7) {
/* 263 */               ret.add(getSeedItemStack());
/*     */             }
/*     */           }
/*     */           
/* 267 */           for (int i = 0; i < func_149745_a(world.field_73012_v); i++) {
/* 268 */             ret.add(getCropItemStack());
/*     */           }
/*     */           
/* 271 */           if ((this.seedItemPrototype.func_77973_b() == Witchery.Items.SEEDS_SNOWBELL) && (world.field_73012_v.nextDouble() <= 0.2D))
/*     */           {
/* 273 */             ret.add(Witchery.Items.GENERIC.itemIcyNeedle.createStack());
/*     */           }
/*     */         }
/*     */       }
/* 277 */       else if (!world.field_72995_K) {
/* 278 */         EntityMandrake mandrake = new EntityMandrake(world);
/* 279 */         mandrake.func_70012_b(0.5D + x, 0.05D + y, 0.5D + z, 0.0F, 0.0F);
/* 280 */         world.func_72838_d(mandrake);
/* 281 */         Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.EXPLODE, com.emoniph.witchery.util.SoundEffect.NONE, mandrake, 0.5D, 1.0D), com.emoniph.witchery.util.TargetPointUtil.from(mandrake, 16.0D));
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 286 */       for (int i = 0; i < func_149745_a(world.field_73012_v); i++) {
/* 287 */         ret.add(getSeedItemStack());
/*     */       }
/*     */     }
/*     */     
/* 291 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_)
/*     */   {
/* 297 */     if ((!p_149642_1_.field_72995_K) && (p_149642_1_.func_82736_K().func_82766_b("doTileDrops"))) {
/* 298 */       float f = 0.7F;
/* 299 */       double d0 = p_149642_1_.field_73012_v.nextFloat() * f + (1.0F - f) * 0.5D;
/* 300 */       double d1 = p_149642_1_.field_73012_v.nextFloat() * f + (1.0F - f) * 0.5D;
/* 301 */       double d2 = p_149642_1_.field_73012_v.nextFloat() * f + (1.0F - f) * 0.5D;
/* 302 */       EntityItem entityitem = new EntityItem(p_149642_1_, p_149642_2_ + d0, p_149642_3_ + d1, p_149642_4_ + d2, p_149642_5_);
/*     */       
/* 304 */       entityitem.field_145804_b = 10;
/* 305 */       if ((p_149642_5_ != null) && (p_149642_5_.func_77973_b() == Witchery.Items.SEEDS_MINDRAKE)) {
/* 306 */         entityitem.lifespan = com.emoniph.witchery.util.TimeUtil.secsToTicks(3);
/*     */       }
/* 308 */       p_149642_1_.func_72838_d(entityitem);
/*     */     }
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random rand, int par3)
/*     */   {
/* 314 */     return par1 == getNumGrowthStages() ? this.cropItemPrototype.func_77973_b() : this.seedItemPrototype.func_77973_b();
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 319 */     return par1 == getNumGrowthStages() ? this.cropItemPrototype.func_77960_j() : this.seedItemPrototype.func_77960_j();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149745_a(Random rand)
/*     */   {
/* 325 */     return 1;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 330 */     return this.seedItemPrototype;
/*     */   }
/*     */   
/*     */   public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 335 */     if (this.waterPlant) {
/* 336 */       return EnumPlantType.Water;
/*     */     }
/* 338 */     return super.getPlantType(world, x, y, z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchCrop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */