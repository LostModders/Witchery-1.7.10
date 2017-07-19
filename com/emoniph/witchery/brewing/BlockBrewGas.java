/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryFluids;
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.EntityPosition;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ 
/*     */ public class BlockBrewGas
/*     */   extends BlockBaseContainer
/*     */   implements IFluidBlock
/*     */ {
/*  32 */   public static final Material MATERIAL = new Material(MapColor.field_151660_b)
/*     */   {
/*     */     public boolean func_76220_a() {
/*  35 */       return false;
/*     */     }
/*     */     
/*     */     public boolean func_76230_c()
/*     */     {
/*  40 */       return false;
/*     */     }
/*  32 */   }.func_76231_i();
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
/*     */   public BlockBrewGas()
/*     */   {
/*  45 */     super(MATERIAL, TileEntityBrewFluid.class);
/*  46 */     this.registerWithCreateTab = false;
/*  47 */     func_149675_a(true);
/*  48 */     func_149649_H();
/*  49 */     func_149722_s();
/*     */   }
/*     */   
/*     */   public boolean isAir(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  54 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149720_d(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  59 */     TileEntityBrewFluid gas = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
/*  60 */     if (gas != null) {
/*  61 */       return gas.color;
/*     */     }
/*  63 */     return 3385907;
/*     */   }
/*     */   
/*     */   public int func_149741_i(int metadata)
/*     */   {
/*  68 */     return 3385907;
/*     */   }
/*     */   
/*     */   public int func_149635_D()
/*     */   {
/*  73 */     return 3385907;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  83 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149678_a(int p_149678_1_, boolean p_149678_2_)
/*     */   {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune)
/*     */   {
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 104 */     return 0;
/*     */   }
/*     */   
/*     */   public int func_149738_a(World world)
/*     */   {
/* 109 */     return 5;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 114 */     Block block = world.func_147439_a(x, y, z);
/* 115 */     return block == this ? false : super.func_149646_a(world, x, y, z, side);
/*     */   }
/*     */   
/*     */   public boolean func_149747_d(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 120 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   public Fluid getFluid()
/*     */   {
/* 130 */     return Witchery.Fluids.BREW_GAS;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 135 */     return Witchery.proxy.getGasRenderId();
/*     */   }
/*     */   
/*     */   public int func_149701_w()
/*     */   {
/* 140 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/* 145 */     world.func_147464_a(x, y, z, this, 5);
/*     */   }
/*     */   
/*     */   public void onNeighborBlockChange(World world, int x, int y, int z, int side) {
/* 149 */     func_149726_b(world, x, y, z);
/*     */   }
/*     */   
/*     */   public FluidStack drain(World world, int x, int y, int z, boolean doDrain)
/*     */   {
/* 154 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canDrain(World world, int x, int y, int z)
/*     */   {
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   public float getFilledPercentage(World world, int x, int y, int z)
/*     */   {
/* 164 */     return 0.0F;
/*     */   }
/*     */   
/*     */   private boolean isTargetBlock(Block block) {
/* 168 */     return (block != null) && (block != Blocks.field_150350_a) && (block != this);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/* 173 */     if (!world.field_72995_K) {
/* 174 */       int initialMetadata = world.func_72805_g(x, y, z);
/* 175 */       TileEntityBrewFluid oldTile = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
/* 176 */       if (oldTile == null) {
/* 177 */         world.func_147468_f(x, y, z);
/* 178 */         return;
/*     */       }
/*     */       
/* 181 */       int maxMeta = oldTile.expansion;
/* 182 */       if (oldTile.incRunTicks() > 120) {
/* 183 */         world.func_147468_f(x, y, z);
/* 184 */         return;
/*     */       }
/*     */       
/* 187 */       if (initialMetadata >= maxMeta) {
/* 188 */         if (oldTile != null ? (oldTile.duration == 0) || ((oldTile.duration > 0) && (rand.nextInt(oldTile.duration) == 0)) : rand.nextInt(40) == 0)
/*     */         {
/* 190 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       }
/*     */       else {
/* 194 */         int metadata = initialMetadata;
/* 195 */         double[] pb = { 0.2D, 0.4D, 0.8D, 0.8D, 0.8D, 0.8D };
/* 196 */         int[] dX = { 0, 0, 1, -1, 0, 0 };
/* 197 */         int[] dY = { 1, -1, 0, 0, 0, 0 };
/* 198 */         int[] dZ = { 0, 0, 0, 0, 1, -1 };
/*     */         
/* 200 */         boolean expanded = false;
/*     */         
/* 202 */         if (oldTile != null) {
/* 203 */           for (int i = 0; (i < pb.length) && (metadata < maxMeta); i++) {
/* 204 */             if (rand.nextDouble() < pb[i]) {
/* 205 */               int newX = x + dX[i];
/* 206 */               int newY = y + dY[i];
/* 207 */               int newZ = z + dZ[i];
/* 208 */               Block block = world.func_147439_a(newX, newY, newZ);
/* 209 */               if ((block == Blocks.field_150350_a) || (block == Blocks.field_150431_aC)) {
/* 210 */                 world.func_147465_d(newX, newY, newZ, this, Math.min(metadata + 1, maxMeta), 3);
/* 211 */                 TileEntityBrewFluid newTile = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, newX, newY, newZ, TileEntityBrewFluid.class);
/*     */                 
/* 213 */                 newTile.nbtEffect = ((NBTTagCompound)oldTile.nbtEffect.func_74737_b());
/* 214 */                 newTile.expansion = oldTile.expansion;
/* 215 */                 newTile.color = oldTile.color;
/* 216 */                 newTile.duration = oldTile.duration;
/* 217 */                 newTile.thrower = oldTile.thrower;
/* 218 */                 expanded = true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 224 */         if (expanded) {
/* 225 */           world.func_72921_c(x, y, z, Math.min(metadata + 1, maxMeta), 3);
/*     */         }
/*     */       }
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
/* 246 */       world.func_147464_a(x, y, z, this, 5);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 252 */     if ((entity != null) && ((entity instanceof EntityLivingBase)) && 
/* 253 */       (!world.field_72995_K) && (world.field_73012_v.nextInt(10) == 4)) {
/* 254 */       TileEntityBrewFluid gas = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
/* 255 */       if ((gas != null) && (gas.nbtEffect != null)) {
/* 256 */         EntityLivingBase living = (EntityLivingBase)entity;
/*     */         
/* 258 */         ModifiersEffect modifiers = new ModifiersEffect(0.25D, 0.5D, false, new EntityPosition(x, y, z), false, 0, world.func_72924_a(gas.thrower));
/*     */         
/*     */ 
/* 261 */         modifiers.protectedFromNegativePotions = living.func_70644_a(Witchery.Potions.GAS_MASK);
/*     */         
/* 263 */         WitcheryBrewRegistry.INSTANCE.applyToEntity(world, living, gas.nbtEffect, modifiers);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockBrewGas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */