/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryFluids;
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.EntityPosition;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.google.common.collect.Maps;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ 
/*     */ public class BlockBrewLiquidEffect
/*     */   extends BlockBaseContainer implements IFluidBlock
/*     */ {
/*  39 */   protected static final Map<Block, Boolean> defaultDisplacements = ;
/*     */   
/*     */   static {
/*  42 */     defaultDisplacements.put(Blocks.field_150466_ao, Boolean.valueOf(false));
/*  43 */     defaultDisplacements.put(Blocks.field_150454_av, Boolean.valueOf(false));
/*  44 */     defaultDisplacements.put(Blocks.field_150472_an, Boolean.valueOf(false));
/*  45 */     defaultDisplacements.put(Blocks.field_150444_as, Boolean.valueOf(false));
/*  46 */     defaultDisplacements.put(Blocks.field_150436_aH, Boolean.valueOf(false)); }
/*     */   
/*  48 */   protected Map<Block, Boolean> displacements = Maps.newHashMap();
/*     */   
/*  50 */   protected int quantaPerBlock = 6;
/*  51 */   protected float quantaPerBlockFloat = 8.0F;
/*  52 */   protected int density = 1;
/*  53 */   protected int densityDir = -1;
/*  54 */   protected int temperature = 295;
/*     */   
/*  56 */   protected int tickRate = 20;
/*  57 */   protected int renderPass = 1;
/*  58 */   protected int maxScaledLight = 0;
/*     */   
/*  60 */   protected boolean[] isOptimalFlowDirection = new boolean[4];
/*  61 */   protected int[] flowCost = new int[4];
/*     */   protected FluidStack stack;
/*     */   protected final String fluidName;
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon[] icons;
/*     */   
/*     */   public BlockBrewLiquidEffect() {
/*  68 */     super(Material.field_151586_h, TileEntityBrewFluid.class);
/*  69 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  70 */     func_149675_a(true);
/*  71 */     this.registerWithCreateTab = false;
/*  72 */     func_149649_H();
/*     */     
/*  74 */     Fluid fluid = Witchery.Fluids.BREW_LIQUID;
/*  75 */     this.fluidName = fluid.getName();
/*  76 */     this.density = fluid.getDensity();
/*  77 */     this.temperature = fluid.getTemperature();
/*  78 */     this.maxScaledLight = fluid.getLuminosity();
/*  79 */     this.tickRate = (fluid.getViscosity() / 200);
/*  80 */     this.densityDir = (fluid.getDensity() > 0 ? -1 : 1);
/*  81 */     fluid.setBlock(this);
/*     */     
/*  83 */     this.stack = new FluidStack(fluid, 1000);
/*     */     
/*  85 */     this.displacements.putAll(defaultDisplacements);
/*     */   }
/*     */   
/*     */   public int func_149720_d(IBlockAccess world, int x, int y, int z) {
/*  89 */     TileEntityBrewFluid fluid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
/*  90 */     if (fluid != null) {
/*  91 */       return fluid.color;
/*     */     }
/*  93 */     return 68;
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setFluidStack(FluidStack stack) {
/*  97 */     this.stack = stack;
/*  98 */     return this;
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setFluidStackAmount(int amount) {
/* 102 */     this.stack.amount = amount;
/* 103 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/* 112 */     return (side != 0) && (side != 1) ? this.icons[1] : this.icons[0];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 118 */     this.icons = new IIcon[] { iconRegister.func_94245_a(func_149641_N() + "_still"), iconRegister.func_94245_a(func_149641_N() + "_flow") };
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setQuantaPerBlock(int quantaPerBlock)
/*     */   {
/* 123 */     if ((quantaPerBlock > 16) || (quantaPerBlock < 1))
/* 124 */       quantaPerBlock = 8;
/* 125 */     this.quantaPerBlock = quantaPerBlock;
/* 126 */     this.quantaPerBlockFloat = quantaPerBlock;
/* 127 */     return this;
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setDensity(int density) {
/* 131 */     if (density == 0)
/* 132 */       density = 1;
/* 133 */     this.density = density;
/* 134 */     this.densityDir = (density > 0 ? -1 : 1);
/* 135 */     return this;
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setTemperature(int temperature) {
/* 139 */     this.temperature = temperature;
/* 140 */     return this;
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setTickRate(int tickRate) {
/* 144 */     if (tickRate <= 0)
/* 145 */       tickRate = 20;
/* 146 */     this.tickRate = tickRate;
/* 147 */     return this;
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setRenderPass(int renderPass) {
/* 151 */     this.renderPass = renderPass;
/* 152 */     return this;
/*     */   }
/*     */   
/*     */   public BlockBrewLiquidEffect setMaxScaledLight(int maxScaledLight) {
/* 156 */     this.maxScaledLight = maxScaledLight;
/* 157 */     return this;
/*     */   }
/*     */   
/*     */   public boolean canDisplace(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 162 */     if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
/* 163 */       return true;
/*     */     }
/* 165 */     if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
/* 166 */       return false;
/*     */     }
/*     */     
/* 169 */     Block block = world.func_147439_a(x, y, z);
/*     */     
/* 171 */     if (block == this) {
/* 172 */       return false;
/*     */     }
/*     */     
/* 175 */     if (this.displacements.containsKey(block)) {
/* 176 */       return ((Boolean)this.displacements.get(block)).booleanValue();
/*     */     }
/*     */     
/* 179 */     Material material = block.func_149688_o();
/* 180 */     if ((material.func_76230_c()) || (material == Material.field_151567_E)) {
/* 181 */       return false;
/*     */     }
/*     */     
/* 184 */     int density = getDensity(world, x, y, z);
/* 185 */     if (density == Integer.MAX_VALUE) {
/* 186 */       return true;
/*     */     }
/*     */     
/* 189 */     if (this.density > density) {
/* 190 */       return true;
/*     */     }
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   public boolean displaceIfPossible(World world, int x, int y, int z)
/*     */   {
/* 197 */     if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
/* 198 */       return true;
/*     */     }
/*     */     
/* 201 */     if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
/* 202 */       return false;
/*     */     }
/*     */     
/* 205 */     Block block = world.func_147439_a(x, y, z);
/* 206 */     if (block == this) {
/* 207 */       return false;
/*     */     }
/*     */     
/* 210 */     if (this.displacements.containsKey(block)) {
/* 211 */       if (((Boolean)this.displacements.get(block)).booleanValue()) {
/* 212 */         block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 213 */         return true;
/*     */       }
/* 215 */       return false;
/*     */     }
/*     */     
/* 218 */     Material material = block.func_149688_o();
/* 219 */     if ((material.func_76230_c()) || (material == Material.field_151567_E)) {
/* 220 */       return false;
/*     */     }
/*     */     
/* 223 */     int density = getDensity(world, x, y, z);
/* 224 */     if (density == Integer.MAX_VALUE) {
/* 225 */       block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 226 */       return true;
/*     */     }
/*     */     
/* 229 */     if (this.density > density) {
/* 230 */       return true;
/*     */     }
/* 232 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/* 238 */     world.func_147464_a(x, y, z, this, this.tickRate);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 243 */     world.func_147464_a(x, y, z, this, this.tickRate);
/*     */   }
/*     */   
/*     */   public boolean func_149698_L()
/*     */   {
/* 248 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/* 258 */     return null;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/* 263 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 268 */     return 0;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 273 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149738_a(World world)
/*     */   {
/* 278 */     return this.tickRate;
/*     */   }
/*     */   
/*     */   public void func_149640_a(World world, int x, int y, int z, Entity entity, Vec3 vec)
/*     */   {
/* 283 */     if (this.densityDir > 0)
/* 284 */       return;
/* 285 */     Vec3 vec_flow = getFlowVector(world, x, y, z);
/* 286 */     vec.field_72450_a += vec_flow.field_72450_a * (this.quantaPerBlock * 4);
/* 287 */     vec.field_72448_b += vec_flow.field_72448_b * (this.quantaPerBlock * 4);
/* 288 */     vec.field_72449_c += vec_flow.field_72449_c * (this.quantaPerBlock * 4);
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 293 */     return Witchery.proxy.getBrewLiquidRenderId();
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 298 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 303 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149677_c(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 308 */     int lightThis = world.func_72802_i(x, y, z, 0);
/* 309 */     int lightUp = world.func_72802_i(x, y + 1, z, 0);
/* 310 */     int lightThisBase = lightThis & 0xFF;
/* 311 */     int lightUpBase = lightUp & 0xFF;
/* 312 */     int lightThisExt = lightThis >> 16 & 0xFF;
/* 313 */     int lightUpExt = lightUp >> 16 & 0xFF;
/* 314 */     return (lightThisBase > lightUpBase ? lightThisBase : lightUpBase) | (lightThisExt > lightUpExt ? lightThisExt : lightUpExt) << 16;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149701_w()
/*     */   {
/* 320 */     return this.renderPass;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 325 */     Block block = world.func_147439_a(x, y, z);
/* 326 */     if (block != this) {
/* 327 */       return !block.func_149662_c();
/*     */     }
/* 329 */     return block.func_149688_o() == func_149688_o() ? false : super.func_149646_a(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   public static final int getDensity(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 334 */     Block block = world.func_147439_a(x, y, z);
/* 335 */     if (!(block instanceof BlockBrewLiquidEffect)) {
/* 336 */       return Integer.MAX_VALUE;
/*     */     }
/* 338 */     return ((BlockBrewLiquidEffect)block).density;
/*     */   }
/*     */   
/*     */   public static final int getTemperature(IBlockAccess world, int x, int y, int z) {
/* 342 */     Block block = world.func_147439_a(x, y, z);
/* 343 */     if (!(block instanceof BlockBrewLiquidEffect)) {
/* 344 */       return Integer.MAX_VALUE;
/*     */     }
/* 346 */     return ((BlockBrewLiquidEffect)block).temperature;
/*     */   }
/*     */   
/*     */   public static double getFlowDirection(IBlockAccess world, int x, int y, int z) {
/* 350 */     Block block = world.func_147439_a(x, y, z);
/* 351 */     if (!block.func_149688_o().func_76224_d()) {
/* 352 */       return -1000.0D;
/*     */     }
/* 354 */     Vec3 vec = ((BlockBrewLiquidEffect)block).getFlowVector(world, x, y, z);
/* 355 */     return (vec.field_72450_a == 0.0D) && (vec.field_72449_c == 0.0D) ? -1000.0D : Math.atan2(vec.field_72449_c, vec.field_72450_a) - 1.5707963267948966D;
/*     */   }
/*     */   
/*     */   public final int getQuantaValueBelow(IBlockAccess world, int x, int y, int z, int belowThis)
/*     */   {
/* 360 */     int quantaRemaining = getQuantaValue(world, x, y, z);
/* 361 */     if (quantaRemaining >= belowThis) {
/* 362 */       return -1;
/*     */     }
/* 364 */     return quantaRemaining;
/*     */   }
/*     */   
/*     */   public final int getQuantaValueAbove(IBlockAccess world, int x, int y, int z, int aboveThis) {
/* 368 */     int quantaRemaining = getQuantaValue(world, x, y, z);
/* 369 */     if (quantaRemaining <= aboveThis) {
/* 370 */       return -1;
/*     */     }
/* 372 */     return quantaRemaining;
/*     */   }
/*     */   
/*     */   public final float getQuantaPercentage(IBlockAccess world, int x, int y, int z) {
/* 376 */     int quantaRemaining = getQuantaValue(world, x, y, z);
/* 377 */     return quantaRemaining / this.quantaPerBlockFloat;
/*     */   }
/*     */   
/*     */   public Vec3 getFlowVector(IBlockAccess world, int x, int y, int z) {
/* 381 */     Vec3 vec = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/* 382 */     int decay = this.quantaPerBlock - getQuantaValue(world, x, y, z);
/*     */     
/* 384 */     for (int side = 0; side < 4; side++) {
/* 385 */       int x2 = x;
/* 386 */       int z2 = z;
/*     */       
/* 388 */       switch (side) {
/*     */       case 0: 
/* 390 */         x2--;
/* 391 */         break;
/*     */       case 1: 
/* 393 */         z2--;
/* 394 */         break;
/*     */       case 2: 
/* 396 */         x2++;
/* 397 */         break;
/*     */       case 3: 
/* 399 */         z2++;
/*     */       }
/*     */       
/*     */       
/* 403 */       int otherDecay = this.quantaPerBlock - getQuantaValue(world, x2, y, z2);
/* 404 */       if (otherDecay >= this.quantaPerBlock) {
/* 405 */         if (!world.func_147439_a(x2, y, z2).func_149688_o().func_76230_c()) {
/* 406 */           otherDecay = this.quantaPerBlock - getQuantaValue(world, x2, y - 1, z2);
/* 407 */           if (otherDecay >= 0) {
/* 408 */             int power = otherDecay - (decay - this.quantaPerBlock);
/* 409 */             vec = vec.func_72441_c((x2 - x) * power, (y - y) * power, (z2 - z) * power);
/*     */           }
/*     */         }
/* 412 */       } else if (otherDecay >= 0) {
/* 413 */         int power = otherDecay - decay;
/* 414 */         vec = vec.func_72441_c((x2 - x) * power, (y - y) * power, (z2 - z) * power);
/*     */       }
/*     */     }
/*     */     
/* 418 */     if (world.func_147439_a(x, y + 1, z) == this) {
/* 419 */       boolean flag = (func_149747_d(world, x, y, z - 1, 2)) || (func_149747_d(world, x, y, z + 1, 3)) || (func_149747_d(world, x - 1, y, z, 4)) || (func_149747_d(world, x + 1, y, z, 5)) || (func_149747_d(world, x, y + 1, z - 1, 2)) || (func_149747_d(world, x, y + 1, z + 1, 3)) || (func_149747_d(world, x - 1, y + 1, z, 4)) || (func_149747_d(world, x + 1, y + 1, z, 5));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 424 */       if (flag) {
/* 425 */         vec = vec.func_72432_b().func_72441_c(0.0D, -6.0D, 0.0D);
/*     */       }
/*     */     }
/* 428 */     vec = vec.func_72432_b();
/* 429 */     return vec;
/*     */   }
/*     */   
/*     */   public Fluid getFluid()
/*     */   {
/* 434 */     return FluidRegistry.getFluid(this.fluidName);
/*     */   }
/*     */   
/*     */   public float getFilledPercentage(World world, int x, int y, int z)
/*     */   {
/* 439 */     int quantaRemaining = getQuantaValue(world, x, y, z) + 1;
/* 440 */     float remaining = quantaRemaining / this.quantaPerBlockFloat;
/* 441 */     if (remaining > 1.0F)
/* 442 */       remaining = 1.0F;
/* 443 */     return remaining * (this.density > 0 ? 1 : -1);
/*     */   }
/*     */   
/*     */   public int getQuantaValue(IBlockAccess world, int x, int y, int z) {
/* 447 */     if (world.func_147439_a(x, y, z) == Blocks.field_150350_a) {
/* 448 */       return 0;
/*     */     }
/*     */     
/* 451 */     if (world.func_147439_a(x, y, z) != this) {
/* 452 */       return -1;
/*     */     }
/*     */     
/* 455 */     int quantaRemaining = this.quantaPerBlock - world.func_72805_g(x, y, z);
/* 456 */     return quantaRemaining;
/*     */   }
/*     */   
/*     */   public boolean func_149678_a(int meta, boolean fullHit)
/*     */   {
/* 461 */     return (fullHit) && (meta == 0);
/*     */   }
/*     */   
/*     */   public int getMaxRenderHeightMeta() {
/* 465 */     return 0;
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 470 */     if (this.maxScaledLight == 0) {
/* 471 */       return super.getLightValue(world, x, y, z);
/*     */     }
/* 473 */     int data = this.quantaPerBlock - world.func_72805_g(x, y, z) - 1;
/* 474 */     return (int)(data / this.quantaPerBlockFloat * this.maxScaledLight);
/*     */   }
/*     */   
/*     */   private boolean isTargetBlock(World world, Block block, int x, int y, int z) {
/* 478 */     return (block != null) && ((block != Blocks.field_150350_a) || (world.func_147439_a(x, y - 1, z).func_149688_o().func_76220_a())) && (block != this);
/*     */   }
/*     */   
/*     */   public boolean isFlowingVertically(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 483 */     return (world.func_147439_a(x, y + this.densityDir, z) == this) || ((world.func_147439_a(x, y, z) == this) && (canFlowInto(world, x, y + this.densityDir, z)));
/*     */   }
/*     */   
/*     */   public boolean isSourceBlock(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 488 */     return (world.func_147439_a(x, y, z) == this) && (world.func_72805_g(x, y, z) == 0);
/*     */   }
/*     */   
/*     */   protected boolean[] getOptimalFlowDirections(World world, int x, int y, int z) {
/* 492 */     for (int side = 0; side < 4; side++) {
/* 493 */       this.flowCost[side] = 1000;
/*     */       
/* 495 */       int x2 = x;
/* 496 */       int y2 = y;
/* 497 */       int z2 = z;
/*     */       
/* 499 */       switch (side) {
/*     */       case 0: 
/* 501 */         x2--;
/* 502 */         break;
/*     */       case 1: 
/* 504 */         x2++;
/* 505 */         break;
/*     */       case 2: 
/* 507 */         z2--;
/* 508 */         break;
/*     */       case 3: 
/* 510 */         z2++;
/*     */       }
/*     */       
/*     */       
/* 514 */       if ((canFlowInto(world, x2, y2, z2)) && (!isSourceBlock(world, x2, y2, z2)))
/*     */       {
/*     */ 
/*     */ 
/* 518 */         if (canFlowInto(world, x2, y2 + this.densityDir, z2)) {
/* 519 */           this.flowCost[side] = 0;
/*     */         } else {
/* 521 */           this.flowCost[side] = calculateFlowCost(world, x2, y2, z2, 1, side);
/*     */         }
/*     */       }
/*     */     }
/* 525 */     int min = this.flowCost[0];
/* 526 */     for (int side = 1; side < 4; side++) {
/* 527 */       if (this.flowCost[side] < min) {
/* 528 */         min = this.flowCost[side];
/*     */       }
/*     */     }
/* 531 */     for (int side = 0; side < 4; side++) {
/* 532 */       this.isOptimalFlowDirection[side] = (this.flowCost[side] == min ? 1 : false);
/*     */     }
/* 534 */     return this.isOptimalFlowDirection;
/*     */   }
/*     */   
/*     */   protected int calculateFlowCost(World world, int x, int y, int z, int recurseDepth, int side) {
/* 538 */     int cost = 1000;
/* 539 */     for (int adjSide = 0; adjSide < 4; adjSide++)
/* 540 */       if (((adjSide != 0) || (side != 1)) && ((adjSide != 1) || (side != 0)) && ((adjSide != 2) || (side != 3)) && ((adjSide != 3) || (side != 2)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 545 */         int x2 = x;
/* 546 */         int y2 = y;
/* 547 */         int z2 = z;
/*     */         
/* 549 */         switch (adjSide) {
/*     */         case 0: 
/* 551 */           x2--;
/* 552 */           break;
/*     */         case 1: 
/* 554 */           x2++;
/* 555 */           break;
/*     */         case 2: 
/* 557 */           z2--;
/* 558 */           break;
/*     */         case 3: 
/* 560 */           z2++;
/*     */         }
/*     */         
/*     */         
/* 564 */         if ((canFlowInto(world, x2, y2, z2)) && (!isSourceBlock(world, x2, y2, z2)))
/*     */         {
/*     */ 
/*     */ 
/* 568 */           if (canFlowInto(world, x2, y2 + this.densityDir, z2)) {
/* 569 */             return recurseDepth;
/*     */           }
/*     */           
/* 572 */           if (recurseDepth < 4)
/*     */           {
/*     */ 
/*     */ 
/* 576 */             int min = calculateFlowCost(world, x2, y2, z2, recurseDepth + 1, adjSide);
/* 577 */             if (min < cost)
/* 578 */               cost = min;
/*     */           }
/*     */         } }
/* 581 */     return cost;
/*     */   }
/*     */   
/*     */   protected void flowIntoBlock(World world, int x, int y, int z, int meta, TileEntityBrewFluid sourceFluid) {
/* 585 */     if (meta < 0)
/* 586 */       return;
/* 587 */     if (displaceIfPossible(world, x, y, z)) {
/* 588 */       world.func_147465_d(x, y, z, this, meta, 3);
/* 589 */       TileEntityBrewFluid targetFluid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
/*     */       
/* 591 */       if ((targetFluid != null) && (sourceFluid != null) && (sourceFluid.nbtEffect != null)) {
/* 592 */         targetFluid.nbtEffect = ((NBTTagCompound)sourceFluid.nbtEffect.func_74737_b());
/* 593 */         targetFluid.expansion = sourceFluid.expansion;
/* 594 */         targetFluid.color = sourceFluid.color;
/* 595 */         targetFluid.duration = sourceFluid.duration;
/* 596 */         targetFluid.thrower = sourceFluid.thrower;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean canFlowInto(IBlockAccess world, int x, int y, int z) {
/* 602 */     if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
/* 603 */       return true;
/*     */     }
/* 605 */     Block block = world.func_147439_a(x, y, z);
/* 606 */     if (block == this) {
/* 607 */       return true;
/*     */     }
/*     */     
/* 610 */     if (this.displacements.containsKey(block)) {
/* 611 */       return ((Boolean)this.displacements.get(block)).booleanValue();
/*     */     }
/*     */     
/* 614 */     Material material = block.func_149688_o();
/* 615 */     if ((material.func_76230_c()) || (material == Material.field_151586_h) || (material == Material.field_151587_i) || (material == Material.field_151567_E))
/*     */     {
/* 617 */       return false;
/*     */     }
/*     */     
/* 620 */     int density = getDensity(world, x, y, z);
/* 621 */     if (density == Integer.MAX_VALUE) {
/* 622 */       return true;
/*     */     }
/*     */     
/* 625 */     if (this.density > density) {
/* 626 */       return true;
/*     */     }
/* 628 */     return false;
/*     */   }
/*     */   
/*     */   protected int getLargerQuanta(IBlockAccess world, int x, int y, int z, int compare)
/*     */   {
/* 633 */     int quantaRemaining = getQuantaValue(world, x, y, z);
/* 634 */     if (quantaRemaining <= 0) {
/* 635 */       return compare;
/*     */     }
/* 637 */     return quantaRemaining >= compare ? quantaRemaining : compare;
/*     */   }
/*     */   
/*     */   public FluidStack drain(World world, int x, int y, int z, boolean doDrain)
/*     */   {
/* 642 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canDrain(World world, int x, int y, int z)
/*     */   {
/* 647 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/* 652 */     if (world.field_72995_K) {
/* 653 */       return;
/*     */     }
/*     */     
/* 656 */     boolean evaporated = false;
/* 657 */     TileEntityBrewFluid fluid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
/*     */     
/* 659 */     if ((!world.field_72995_K) && (fluid != null) && (isSourceBlock(world, x, y, z))) {
/* 660 */       if ((++fluid.updateCount > 3) && ((fluid.duration == 0) || (rand.nextInt(fluid.duration) == 0))) {
/* 661 */         world.func_147468_f(x, y, z);
/* 662 */         evaporated = true;
/*     */       } else {
/* 664 */         world.func_147464_a(x, y, z, this, this.tickRate);
/*     */       }
/*     */     }
/*     */     
/* 668 */     if (!evaporated) {
/* 669 */       int quantaRemaining = this.quantaPerBlock - world.func_72805_g(x, y, z);
/* 670 */       int expQuanta = -101;
/*     */       
/*     */ 
/* 673 */       if (quantaRemaining < this.quantaPerBlock) {
/* 674 */         int y2 = y - this.densityDir;
/*     */         
/* 676 */         if ((world.func_147439_a(x, y2, z) == this) || (world.func_147439_a(x - 1, y2, z) == this) || (world.func_147439_a(x + 1, y2, z) == this) || (world.func_147439_a(x, y2, z - 1) == this) || (world.func_147439_a(x, y2, z + 1) == this))
/*     */         {
/*     */ 
/* 679 */           expQuanta = this.quantaPerBlock - 1;
/*     */         } else {
/* 681 */           int maxQuanta = -100;
/* 682 */           maxQuanta = getLargerQuanta(world, x - 1, y, z, maxQuanta);
/* 683 */           maxQuanta = getLargerQuanta(world, x + 1, y, z, maxQuanta);
/* 684 */           maxQuanta = getLargerQuanta(world, x, y, z - 1, maxQuanta);
/* 685 */           maxQuanta = getLargerQuanta(world, x, y, z + 1, maxQuanta);
/*     */           
/* 687 */           expQuanta = maxQuanta - 1;
/*     */         }
/*     */         
/*     */ 
/* 691 */         if (expQuanta != quantaRemaining) {
/* 692 */           quantaRemaining = expQuanta;
/*     */           
/* 694 */           if (expQuanta <= 0) {
/* 695 */             world.func_147449_b(x, y, z, Blocks.field_150350_a);
/*     */           } else {
/* 697 */             world.func_72921_c(x, y, z, this.quantaPerBlock - expQuanta, 3);
/* 698 */             world.func_147464_a(x, y, z, this, this.tickRate);
/* 699 */             world.func_147459_d(x, y, z, this);
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 706 */       else if (quantaRemaining >= this.quantaPerBlock) {
/* 707 */         world.func_72921_c(x, y, z, 0, 2);
/*     */       }
/*     */       
/*     */ 
/* 711 */       if (canDisplace(world, x, y + this.densityDir, z)) {
/* 712 */         flowIntoBlock(world, x, y + this.densityDir, z, 1, fluid);
/* 713 */         return;
/*     */       }
/*     */       
/*     */ 
/* 717 */       int flowMeta = this.quantaPerBlock - quantaRemaining + 1;
/* 718 */       if (flowMeta >= this.quantaPerBlock) {
/* 719 */         return;
/*     */       }
/*     */       
/* 722 */       if ((isSourceBlock(world, x, y, z)) || (!isFlowingVertically(world, x, y, z))) {
/* 723 */         if (world.func_147439_a(x, y - this.densityDir, z) == this) {
/* 724 */           flowMeta = 1;
/*     */         }
/* 726 */         boolean[] flowTo = getOptimalFlowDirections(world, x, y, z);
/*     */         
/* 728 */         if (flowTo[0] != 0)
/* 729 */           flowIntoBlock(world, x - 1, y, z, flowMeta, fluid);
/* 730 */         if (flowTo[1] != 0)
/* 731 */           flowIntoBlock(world, x + 1, y, z, flowMeta, fluid);
/* 732 */         if (flowTo[2] != 0)
/* 733 */           flowIntoBlock(world, x, y, z - 1, flowMeta, fluid);
/* 734 */         if (flowTo[3] != 0) {
/* 735 */           flowIntoBlock(world, x, y, z + 1, flowMeta, fluid);
/*     */         }
/*     */       }
/* 738 */       if ((fluid != null) && (fluid.nbtEffect != null)) {
/* 739 */         for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
/* 740 */           int x2 = x + direction.offsetX;int y2 = y + direction.offsetY;int z2 = z + direction.offsetZ;
/* 741 */           if ((world.field_73012_v.nextDouble() < 0.01D) && (isTargetBlock(world, world.func_147439_a(x2, y2, z2), x2, y2, z2)))
/*     */           {
/* 743 */             ModifiersEffect modifiers = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(x + 0.5D, y, z + 0.5D), false, 0, EntityUtil.playerOrFake(world, fluid.thrower));
/*     */             
/* 745 */             modifiers.strengthPenalty += 1;
/* 746 */             WitcheryBrewRegistry.INSTANCE.applyToBlock(world, x2, y2, z2, direction.getOpposite(), 1, fluid.nbtEffect, modifiers);
/*     */           }
/*     */         }
/*     */         
/* 750 */         world.func_147464_a(x, y, z, this, this.tickRate);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity) {
/* 756 */     if ((entity != null) && ((entity instanceof EntityLivingBase)) && 
/* 757 */       (!world.field_72995_K) && (world.field_73012_v.nextInt(10) == 4)) {
/* 758 */       TileEntityBrewFluid liquid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
/*     */       
/* 760 */       if ((liquid != null) && (liquid.nbtEffect != null)) {
/* 761 */         EntityLivingBase living = (EntityLivingBase)entity;
/*     */         
/* 763 */         WitcheryBrewRegistry.INSTANCE.applyToEntity(world, living, liquid.nbtEffect, new ModifiersEffect(0.25D, 0.5D, false, new EntityPosition(x, y, z), false, 0, EntityUtil.playerOrFake(world, liquid.thrower)));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockBrewLiquidEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */