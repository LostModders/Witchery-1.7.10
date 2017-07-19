/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.crafting.KettleRecipes;
/*     */ import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.FluidTankInfo;
/*     */ 
/*     */ public class BlockKettle extends BlockBaseContainer
/*     */ {
/*     */   static final int POWER_SOURCE_RADIUS = 16;
/*     */   
/*     */   public BlockKettle()
/*     */   {
/*  54 */     super(Material.field_151574_g, TileEntityKettle.class);
/*     */     
/*  56 */     func_149711_c(2.0F);
/*  57 */     func_149672_a(field_149777_j);
/*  58 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  63 */     float f = 0.0625F;
/*  64 */     return AxisAlignedBB.func_72330_a(x + f, y, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/*  71 */     return super.func_149633_g(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  81 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/*  86 */     func_111046_k(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
/*  90 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/*  91 */       par1World.func_147468_f(par2, par3, par4);
/*  92 */       return false;
/*     */     }
/*  94 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/* 105 */     int l = net.minecraft.util.MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/* 107 */     if (l == 0) {
/* 108 */       par1World.func_72921_c(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/* 111 */     if (l == 1) {
/* 112 */       par1World.func_72921_c(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/* 115 */     if (l == 2) {
/* 116 */       par1World.func_72921_c(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/* 119 */     if (l == 3) {
/* 120 */       par1World.func_72921_c(par2, par3, par4, 4, 2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 128 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
/*     */   {
/* 136 */     List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new com.emoniph.witchery.util.Coord(posX, posY, posZ), 16) : null;
/*     */     
/* 138 */     return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */   }
/*     */   
/*     */   private static ItemStack consumeItem(ItemStack stack) {
/* 142 */     if (stack.field_77994_a == 1) {
/* 143 */       if (stack.func_77973_b().hasContainerItem(stack)) {
/* 144 */         return stack.func_77973_b().getContainerItem(stack);
/*     */       }
/* 146 */       return null;
/*     */     }
/*     */     
/* 149 */     stack.func_77979_a(1);
/*     */     
/* 151 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 158 */     TileEntityKettle tileEntity = (TileEntityKettle)world.func_147438_o(x, y, z);
/* 159 */     if (tileEntity != null) {
/* 160 */       double d0 = x + 0.45F;
/* 161 */       double d1 = y + 0.4F;
/* 162 */       double d2 = z + 0.5F;
/* 163 */       if (tileEntity.isRuined()) {
/* 164 */         world.func_72869_a(ParticleEffect.LARGE_SMOKE.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/* 165 */       } else if (tileEntity.isReady()) {
/* 166 */         world.func_72869_a(ParticleEffect.SLIME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/* 167 */         if (tileEntity.isPowered) {
/* 168 */           world.func_72869_a(ParticleEffect.SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */         } else {
/* 170 */           world.func_72869_a(ParticleEffect.MOB_SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       }
/* 173 */       else if (tileEntity.isBrewing()) {
/* 174 */         world.func_72869_a(ParticleEffect.MOB_SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean tryFillWith(World world, int x, int y, int z, FluidStack fluidStack) {
/* 180 */     if (world.field_72995_K) {
/* 181 */       return true;
/*     */     }
/* 183 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 184 */     if ((tile == null) || (!(tile instanceof TileEntityKettle))) {
/* 185 */       return false;
/*     */     }
/*     */     
/* 188 */     TileEntityKettle tank = (TileEntityKettle)tile;
/*     */     
/* 190 */     if ((tank != null) && (tank.canFill(ForgeDirection.UNKNOWN, fluidStack.getFluid()))) {
/* 191 */       int qty = tank.fill(ForgeDirection.UNKNOWN, fluidStack, true);
/* 192 */       fluidStack.amount -= qty;
/* 193 */       if (fluidStack.amount < 0) {
/* 194 */         fluidStack.amount = 0;
/*     */       }
/* 196 */       if (qty > 0) {
/* 197 */         world.func_147471_g(x, y, z);
/* 198 */         SoundEffect.WATER_SWIM.playAt(world, x, y, z);
/*     */       }
/* 200 */       return qty > 0;
/*     */     }
/* 202 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/* 207 */     ItemStack current = player.field_71071_by.func_70448_g();
/* 208 */     if (current != null) {
/* 209 */       TileEntity tile = world.func_147438_o(posX, posY, posZ);
/* 210 */       if ((tile == null) || (!(tile instanceof TileEntityKettle))) {
/* 211 */         return false;
/*     */       }
/*     */       
/* 214 */       TileEntityKettle tank = (TileEntityKettle)tile;
/*     */       
/* 216 */       if ((current.func_77973_b() == Items.field_151069_bo) && (tank.isReady()))
/*     */       {
/* 218 */         if (KettleRecipes.instance().isBrewableBy(tank.furnaceItemStacks[6], player))
/*     */         {
/* 220 */           ItemStack itemstack1 = null;
/*     */           try {
/* 222 */             tank.setConsumeBottle(false);
/* 223 */             itemstack1 = tank.func_70298_a(6, 1);
/*     */           } finally {
/* 225 */             tank.setConsumeBottle(true);
/*     */           }
/* 227 */           double bonusChance = 0.0D;
/* 228 */           double bonusChance2 = 0.0D;
/* 229 */           if ((player.field_71071_by.func_70440_f(3) != null) && (player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.WITCH_HAT)) {
/* 230 */             bonusChance += 0.35D;
/* 231 */           } else if ((player.field_71071_by.func_70440_f(3) != null) && (player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.BABAS_HAT)) {
/* 232 */             bonusChance += 0.25D;
/* 233 */             bonusChance2 += 0.25D;
/*     */           }
/*     */           
/* 236 */           if ((!Witchery.Items.GENERIC.itemBrewOfRaising.isMatch(itemstack1)) && (Witchery.Items.WITCH_ROBES.isRobeWorn(player))) {
/* 237 */             bonusChance += 0.35D;
/* 238 */           } else if ((Witchery.Items.GENERIC.itemBrewOfRaising.isMatch(itemstack1)) && (Witchery.Items.NECROMANCERS_ROBES.isRobeWorn(player))) {
/* 239 */             bonusChance += 0.35D;
/*     */           }
/*     */           
/* 242 */           if (com.emoniph.witchery.familiar.Familiar.hasActiveBrewMasteryFamiliar(player)) {
/* 243 */             bonusChance += 0.05D;
/* 244 */             if ((player.field_71071_by.func_70440_f(3) != null) && (player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.BABAS_HAT)) {
/* 245 */               bonusChance2 += 0.05D;
/*     */             }
/*     */           }
/*     */           
/* 249 */           if ((bonusChance > 0.0D) && (world.field_73012_v.nextDouble() <= bonusChance)) {
/* 250 */             itemstack1.field_77994_a += KettleRecipes.instance().getHatBonus(itemstack1);
/*     */           }
/*     */           
/* 253 */           if ((bonusChance2 > 0.0D) && (world.field_73012_v.nextDouble() <= bonusChance2)) {
/* 254 */             itemstack1.field_77994_a += KettleRecipes.instance().getHatBonus(itemstack1);
/*     */           }
/*     */           
/* 257 */           if (!world.field_72995_K) {
/* 258 */             if (current.field_77994_a == 1) {
/* 259 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, itemstack1);
/* 260 */               if ((player instanceof EntityPlayerMP)) {
/* 261 */                 ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */               }
/*     */             } else {
/* 264 */               if (!player.field_71071_by.func_70441_a(itemstack1)) {
/* 265 */                 world.func_72838_d(new EntityItem(world, posX + 0.5D, posY + 1.5D, posZ + 0.5D, itemstack1));
/* 266 */               } else if ((player instanceof EntityPlayerMP)) {
/* 267 */                 ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */               }
/*     */               
/* 270 */               current.field_77994_a -= 1;
/*     */               
/* 272 */               if (current.field_77994_a <= 0) {
/* 273 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 279 */           SoundEffect.WATER_SWIM.playAtPlayer(world, player);
/*     */         }
/*     */         
/* 282 */         return true;
/*     */       }
/*     */       
/* 285 */       FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(current);
/*     */       
/* 287 */       if (liquid != null) {
/* 288 */         if (tank.canFill(ForgeDirection.UNKNOWN, liquid.getFluid())) {
/* 289 */           int qty = tank.fill(ForgeDirection.UNKNOWN, liquid, true);
/*     */           
/* 291 */           if ((qty != 0) && (!player.field_71075_bZ.field_75098_d)) {
/* 292 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(current));
/*     */           }
/* 294 */           tank.reset(false);
/* 295 */           SoundEffect.WATER_SWIM.playAtPlayer(world, player);
/*     */         }
/*     */         
/* 298 */         return true; }
/* 299 */       if (current.func_77973_b() == Witchery.Items.BREW_ENDLESS_WATER) {
/* 300 */         if (tryFillWith(world, posX, posY, posZ, new FluidStack(FluidRegistry.WATER, 1000))) {
/* 301 */           current.func_77972_a(1, player);
/*     */         }
/* 303 */         return true;
/*     */       }
/* 305 */       FluidStack available = tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;
/* 306 */       if (available != null) {
/* 307 */         ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);
/*     */         
/* 309 */         liquid = FluidContainerRegistry.getFluidForFilledItem(filled);
/*     */         
/* 311 */         if (liquid != null) {
/* 312 */           if (!player.field_71075_bZ.field_75098_d) {
/* 313 */             if (current.field_77994_a > 1) {
/* 314 */               if (!player.field_71071_by.func_70441_a(filled)) {
/* 315 */                 return false;
/*     */               }
/* 317 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(current));
/*     */             }
/*     */             else {
/* 320 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(current));
/* 321 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, filled);
/*     */             }
/*     */           }
/* 324 */           tank.drain(ForgeDirection.UNKNOWN, liquid.amount, true);
/* 325 */           tank.reset(false);
/* 326 */           SoundEffect.WATER_SWIM.playAtPlayer(world, player);
/* 327 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 334 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*     */   {
/* 339 */     if ((!world.field_72995_K) && ((entity instanceof EntityItem))) {
/* 340 */       TileEntityKettle tileEntity = (TileEntityKettle)world.func_147438_o(posX, posY, posZ);
/* 341 */       if (tileEntity != null) {
/* 342 */         EntityItem itemEntity = (EntityItem)entity;
/* 343 */         if (itemEntity.func_92059_d().func_77973_b() == Items.field_151069_bo)
/*     */         {
/* 345 */           ItemStack stack = tileEntity.func_70301_a(7);
/* 346 */           if (stack == null) {
/* 347 */             tileEntity.func_70299_a(7, itemEntity.func_92059_d());
/* 348 */             itemEntity.func_70106_y();
/* 349 */           } else if (stack.field_77994_a + itemEntity.func_92059_d().field_77994_a <= tileEntity.func_70297_j_()) {
/* 350 */             stack.field_77994_a += itemEntity.func_92059_d().field_77994_a;
/* 351 */             tileEntity.func_70299_a(7, stack);
/* 352 */             itemEntity.func_70106_y();
/*     */           }
/* 354 */         } else if (tileEntity.isFilled())
/*     */         {
/* 356 */           boolean spaceFound = false;
/* 357 */           for (int i = 0; i < tileEntity.func_70302_i_() - 2; i++) {
/* 358 */             if (tileEntity.func_70301_a(i) == null) {
/* 359 */               tileEntity.func_70299_a(i, itemEntity.func_92059_d());
/* 360 */               spaceFound = true;
/* 361 */               break;
/*     */             }
/*     */           }
/*     */           
/* 365 */           if ((!spaceFound) && (!tileEntity.isRuined())) {
/* 366 */             tileEntity.setRuined();
/*     */           }
/*     */           
/* 369 */           itemEntity.func_70106_y();
/* 370 */           ParticleEffect.SPLASH.send(SoundEffect.WATER_SPLASH, world, posX + 0.5D, posY + 0.2D, posZ + 0.5D, 0.5D, 0.5D, 5);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityKettle extends TileEntityBase implements net.minecraft.inventory.ISidedInventory, net.minecraftforge.fluids.IFluidHandler {
/*     */     private static final int RESULT_SLOT = 6;
/*     */     private static final int BOTTLE_SLOT = 7;
/* 379 */     private ItemStack[] furnaceItemStacks = new ItemStack[8];
/* 380 */     private boolean isRuined = false;
/* 381 */     private boolean isPowered = false;
/*     */     private int color;
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 386 */       super.func_145845_h();
/*     */       
/* 388 */       if ((!this.field_145850_b.field_72995_K) && 
/* 389 */         (!this.isRuined) && (this.ticks % 20L == 0L) && (isFilled()) && ((someFilled()) || (this.furnaceItemStacks[6] != null))) {
/* 390 */         boolean sendPacket = false;
/* 391 */         if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149688_o() != Material.field_151581_o) {
/* 392 */           this.isRuined = true;
/* 393 */           this.color = 0;
/* 394 */           this.furnaceItemStacks[6] = null;
/* 395 */         } else if (this.furnaceItemStacks[6] == null) {
/* 396 */           if (allFilled()) {
/* 397 */             KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().getResult(this.furnaceItemStacks, this.furnaceItemStacks.length - 2, false, this.field_145850_b);
/*     */             
/* 399 */             if (recipe == null) {
/* 400 */               this.color = 0;
/* 401 */               this.isRuined = true;
/* 402 */               this.furnaceItemStacks[6] = null;
/*     */             } else {
/* 404 */               this.color = recipe.getColor();
/* 405 */               boolean wasPowered = this.isPowered;
/* 406 */               float powerNeeded = recipe.getRequiredPower();
/* 407 */               if (powerNeeded == 0.0F) {
/* 408 */                 this.isPowered = true;
/*     */               } else {
/* 410 */                 IPowerSource powerSource = BlockKettle.findNewPowerSource(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 411 */                 this.isPowered = ((powerSource != null) && (powerSource.consumePower(powerNeeded)));
/*     */               }
/*     */               
/* 414 */               if (this.isPowered) {
/* 415 */                 this.furnaceItemStacks[6] = recipe.getOutput(null, true);
/* 416 */                 for (int i = 0; i < this.furnaceItemStacks.length - 2; i++) {
/* 417 */                   this.furnaceItemStacks[i] = null;
/*     */                 }
/*     */               }
/*     */               
/* 421 */               sendPacket = (this.isPowered) || (wasPowered != this.isPowered);
/*     */             }
/*     */           } else {
/* 424 */             KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().getResult(this.furnaceItemStacks, this.furnaceItemStacks.length - 2, true, this.field_145850_b);
/* 425 */             if ((recipe == null) || (recipe.getColor() == 0)) {
/* 426 */               this.color = 0;
/* 427 */               this.isRuined = true;
/* 428 */               this.furnaceItemStacks[6] = null;
/* 429 */             } else if (recipe.getColor() != this.color) {
/* 430 */               this.color = recipe.getColor();
/* 431 */               sendPacket = true;
/*     */             }
/*     */             
/* 434 */             if (!this.isRuined) {
/* 435 */               boolean wasPowered = this.isPowered;
/* 436 */               float powerNeeded = recipe.getRequiredPower();
/* 437 */               if (powerNeeded == 0.0F) {
/* 438 */                 this.isPowered = true;
/*     */               } else {
/* 440 */                 IPowerSource powerSource = BlockKettle.findNewPowerSource(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 441 */                 this.isPowered = ((powerSource != null) && (powerSource.getCurrentPower() >= powerNeeded));
/*     */               }
/* 443 */               sendPacket = wasPowered != this.isPowered;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 448 */         if ((this.isRuined) || (sendPacket)) {
/* 449 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void reset(boolean flushWater)
/*     */     {
/* 456 */       if (!this.field_145850_b.field_72995_K) {
/* 457 */         Log.instance().debug(String.format("Reset kettle %s", new Object[] { flushWater ? "Flush" : "No Flush" }));
/* 458 */         if (flushWater) {
/* 459 */           FluidStack drained = this.tank.drain(this.tank.getFluidAmount(), true);
/* 460 */           Log.instance().debug(String.format("Drained %d remaining %d of  %d", new Object[] { Integer.valueOf(drained.amount), Integer.valueOf(this.tank.getFluidAmount()), Integer.valueOf(this.tank.getCapacity()) }));
/*     */         }
/* 462 */         this.isRuined = false;
/* 463 */         this.isPowered = false;
/* 464 */         for (int i = 0; i < this.furnaceItemStacks.length - 1; i++) {
/* 465 */           this.furnaceItemStacks[i] = null;
/*     */         }
/* 467 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean allFilled() {
/* 472 */       for (int i = 0; i < this.furnaceItemStacks.length - 2; i++) {
/* 473 */         if (this.furnaceItemStacks[i] == null) {
/* 474 */           return false;
/*     */         }
/*     */       }
/* 477 */       return true;
/*     */     }
/*     */     
/*     */     public boolean someFilled() {
/* 481 */       for (int i = 0; i < this.furnaceItemStacks.length - 2; i++) {
/* 482 */         if (this.furnaceItemStacks[i] != null) {
/* 483 */           return true;
/*     */         }
/*     */       }
/* 486 */       return false;
/*     */     }
/*     */     
/*     */     public int func_70302_i_()
/*     */     {
/* 491 */       return this.furnaceItemStacks.length;
/*     */     }
/*     */     
/*     */     public boolean func_94041_b(int slot, ItemStack itemstack)
/*     */     {
/* 496 */       Log.instance().debug(String.format("isItemValidForSlot(%d, %s)", new Object[] { Integer.valueOf(slot), itemstack.toString() }));
/* 497 */       ItemStack stackInSlot = func_70301_a(slot);
/* 498 */       if (slot == 6)
/* 499 */         return true;
/* 500 */       if (slot == 7) {
/* 501 */         if (itemstack.func_77973_b() == Items.field_151069_bo) {} return (stackInSlot != null ? stackInSlot.field_77994_a : 0) + itemstack.field_77994_a <= func_70297_j_();
/*     */       }
/* 503 */       if (func_70301_a(6) == null) {} return (stackInSlot != null ? stackInSlot.field_77994_a : 0) + itemstack.field_77994_a <= func_70297_j_();
/*     */     }
/*     */     
/*     */ 
/* 507 */     private static final int[] side_slots = { 0, 1, 2, 3, 4, 5, 6, 7 };
/*     */     
/*     */     public int[] func_94128_d(int var1)
/*     */     {
/* 511 */       return side_slots;
/*     */     }
/*     */     
/*     */     public boolean func_102007_a(int slot, ItemStack stack, int side)
/*     */     {
/* 516 */       ItemStack stackInSlot = func_70301_a(slot);
/* 517 */       if (slot == 6)
/* 518 */         return false;
/* 519 */       if (slot == 7) {
/* 520 */         if (stack.func_77973_b() == Items.field_151069_bo) {} return (stackInSlot != null ? stackInSlot.field_77994_a : 0) + stack.field_77994_a <= func_70297_j_();
/*     */       }
/* 522 */       return (stack.func_77973_b() != Items.field_151069_bo) && (func_70301_a(6) == null) && (isFilled());
/*     */     }
/*     */     
/*     */ 
/* 526 */     private int lastExtractionQuantity = 0;
/*     */     
/*     */     public boolean func_102008_b(int slot, ItemStack stack, int side)
/*     */     {
/* 530 */       Log.instance().debug(String.format("canExtract(%d, %s, %d)", new Object[] { Integer.valueOf(slot), stack.toString(), Integer.valueOf(side) }));
/* 531 */       ItemStack bottles = func_70301_a(7);
/*     */       
/* 533 */       boolean canExtract = (slot == 6) && (isFilled()) && (isReady()) && (bottles != null) && (bottles.field_77994_a >= stack.field_77994_a);
/*     */       
/* 535 */       if (canExtract) {
/* 536 */         if (!KettleRecipes.instance().isBrewableBy(stack, null)) {
/* 537 */           return false;
/*     */         }
/* 539 */         this.lastExtractionQuantity = stack.field_77994_a;
/*     */       }
/* 541 */       return canExtract;
/*     */     }
/*     */     
/*     */     public int getLiquidColor() {
/* 545 */       return this.color;
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int par1)
/*     */     {
/* 550 */       return this.furnaceItemStacks[par1];
/*     */     }
/*     */     
/*     */     public void func_70299_a(int slot, ItemStack stack)
/*     */     {
/* 555 */       Log.instance().debug("setInventorySlotContents");
/*     */       
/* 557 */       if ((slot == 6) && 
/* 558 */         (this.consumeBottles)) {
/* 559 */         ItemStack resultStack = func_70301_a(6);
/* 560 */         ItemStack bottleStack = func_70301_a(7);
/*     */         
/* 562 */         if ((stack == null) && (resultStack != null) && (bottleStack != null)) {
/* 563 */           bottleStack.field_77994_a -= resultStack.field_77994_a;
/* 564 */           if (bottleStack.field_77994_a <= 0) {
/* 565 */             this.furnaceItemStacks[7] = null;
/*     */           }
/* 567 */         } else if ((stack != null) && (resultStack != null) && (bottleStack != null)) {
/* 568 */           int reduction = resultStack.field_77994_a - stack.field_77994_a;
/* 569 */           if (reduction == 0) {
/* 570 */             reduction = this.lastExtractionQuantity;
/*     */           }
/* 572 */           this.lastExtractionQuantity = 0;
/* 573 */           Log.instance().debug(String.format("bottles; %d %s %s", new Object[] { Integer.valueOf(reduction), stack.toString(), resultStack.toString() }));
/* 574 */           bottleStack.field_77994_a -= reduction;
/* 575 */           if (bottleStack.field_77994_a <= 0) {
/* 576 */             this.furnaceItemStacks[7] = null;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 582 */       this.furnaceItemStacks[slot] = stack;
/* 583 */       if ((stack != null) && (stack.field_77994_a > func_70297_j_())) {
/* 584 */         stack.field_77994_a = func_70297_j_();
/*     */       }
/* 586 */       else if ((stack == null) && (slot == 6)) {
/* 587 */         reset(true);
/* 588 */         return;
/*     */       }
/* 590 */       if (!this.field_145850_b.field_72995_K) {
/* 591 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/* 595 */     private boolean consumeBottles = true;
/*     */     
/* 597 */     public void setConsumeBottle(boolean consume) { this.consumeBottles = consume; }
/*     */     
/*     */ 
/*     */     public ItemStack func_70298_a(int slot, int quantity)
/*     */     {
/* 602 */       Log.instance().debug("decrStackSize");
/* 603 */       if (this.furnaceItemStacks[slot] != null)
/*     */       {
/*     */ 
/* 606 */         ItemStack bottles = func_70301_a(7);
/* 607 */         if ((this.consumeBottles) && 
/* 608 */           (bottles != null)) {
/* 609 */           bottles.field_77994_a -= quantity;
/*     */         }
/*     */         
/* 612 */         if ((bottles != null) && (bottles.field_77994_a <= 0)) {
/* 613 */           this.furnaceItemStacks[7] = null;
/*     */         }
/*     */         
/* 616 */         if (this.furnaceItemStacks[slot].field_77994_a <= quantity) {
/* 617 */           ItemStack itemstack = this.furnaceItemStacks[slot];
/* 618 */           this.furnaceItemStacks[slot] = null;
/* 619 */           if (slot == 6) {
/* 620 */             reset(true);
/*     */           }
/* 622 */           else if (!this.field_145850_b.field_72995_K) {
/* 623 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           }
/*     */           
/* 626 */           return itemstack;
/*     */         }
/* 628 */         ItemStack itemstack = this.furnaceItemStacks[slot].func_77979_a(quantity);
/*     */         
/* 630 */         if (this.furnaceItemStacks[slot].field_77994_a == 0) {
/* 631 */           this.furnaceItemStacks[slot] = null;
/* 632 */           if (slot == 6) {
/* 633 */             reset(true);
/*     */           }
/* 635 */           else if (!this.field_145850_b.field_72995_K) {
/* 636 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           }
/*     */           
/*     */         }
/* 640 */         else if (!this.field_145850_b.field_72995_K) {
/* 641 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */         
/*     */ 
/* 645 */         return itemstack;
/*     */       }
/*     */       
/* 648 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public ItemStack func_70304_b(int par1)
/*     */     {
/* 654 */       Log.instance().debug("getStackInSlotOnClosing");
/* 655 */       if (this.furnaceItemStacks[par1] != null) {
/* 656 */         ItemStack itemstack = this.furnaceItemStacks[par1];
/* 657 */         this.furnaceItemStacks[par1] = null;
/* 658 */         if (par1 == 6) {
/* 659 */           reset(true);
/*     */         }
/* 661 */         else if (!this.field_145850_b.field_72995_K) {
/* 662 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */         
/* 665 */         return itemstack;
/*     */       }
/* 667 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public String func_145825_b()
/*     */     {
/* 673 */       return func_145838_q().func_149732_F();
/*     */     }
/*     */     
/*     */     public boolean func_145818_k_()
/*     */     {
/* 678 */       return true;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 683 */       super.func_145839_a(par1NBTTagCompound);
/* 684 */       if (this.tank.getFluidAmount() > 0) {
/* 685 */         this.tank.drain(this.tank.getFluidAmount(), true);
/*     */       }
/* 687 */       this.tank.readFromNBT(par1NBTTagCompound);
/*     */       
/* 689 */       NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
/* 690 */       this.furnaceItemStacks = new ItemStack[func_70302_i_()];
/*     */       
/* 692 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 693 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 694 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */         
/* 696 */         if ((b0 >= 0) && (b0 < this.furnaceItemStacks.length)) {
/* 697 */           this.furnaceItemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 701 */       this.isRuined = par1NBTTagCompound.func_74767_n("Ruined");
/* 702 */       this.isPowered = par1NBTTagCompound.func_74767_n("Powered");
/* 703 */       this.color = par1NBTTagCompound.func_74762_e("LiquidColor");
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 708 */       super.func_145841_b(par1NBTTagCompound);
/* 709 */       par1NBTTagCompound.func_74757_a("Ruined", this.isRuined);
/* 710 */       par1NBTTagCompound.func_74757_a("Powered", this.isPowered);
/* 711 */       par1NBTTagCompound.func_74768_a("LiquidColor", this.color);
/* 712 */       NBTTagList nbttaglist = new NBTTagList();
/*     */       
/* 714 */       for (int i = 0; i < this.furnaceItemStacks.length; i++) {
/* 715 */         if (this.furnaceItemStacks[i] != null) {
/* 716 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 717 */           nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 718 */           this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
/* 719 */           nbttaglist.func_74742_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 723 */       par1NBTTagCompound.func_74782_a("Items", nbttaglist);
/* 724 */       this.tank.writeToNBT(par1NBTTagCompound);
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 729 */       return 64;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_70295_k_() {}
/*     */     
/*     */ 
/*     */     public void func_70305_f() {}
/*     */     
/*     */ 
/*     */     public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */     {
/* 742 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */     }
/*     */     
/*     */ 
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 748 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 749 */       func_145841_b(nbtTag);
/* 750 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 755 */       super.onDataPacket(net, packet);
/* 756 */       func_145839_a(packet.func_148857_g());
/* 757 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public boolean isFilled() {
/* 761 */       return this.tank.getFluidAmount() == this.tank.getCapacity();
/*     */     }
/*     */     
/*     */     public boolean isBrewing() {
/* 765 */       return (isFilled()) && (someFilled()) && (!isRuined());
/*     */     }
/*     */     
/*     */     public boolean isReady() {
/* 769 */       return (!isRuined()) && (this.furnaceItemStacks[6] != null);
/*     */     }
/*     */     
/*     */     public boolean isRuined() {
/* 773 */       return this.isRuined;
/*     */     }
/*     */     
/*     */     public void setRuined() {
/* 777 */       this.isRuined = true;
/* 778 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/* 781 */     private FluidTank tank = new FluidTank(1000);
/*     */     
/*     */     public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
/*     */     {
/* 785 */       int result = this.tank.fill(resource, doFill);
/*     */       
/* 787 */       return result;
/*     */     }
/*     */     
/*     */     public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
/*     */     {
/* 792 */       if ((resource == null) || (!resource.isFluidEqual(this.tank.getFluid()))) {
/* 793 */         return null;
/*     */       }
/* 795 */       return this.tank.drain(resource.amount, doDrain);
/*     */     }
/*     */     
/*     */     public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
/*     */     {
/* 800 */       return this.tank.drain(maxDrain, doDrain);
/*     */     }
/*     */     
/*     */     public boolean canFill(ForgeDirection from, Fluid fluid)
/*     */     {
/* 805 */       if (fluid == null) {
/* 806 */         return false;
/*     */       }
/* 808 */       return fluid.getName().equals(FluidRegistry.WATER.getName());
/*     */     }
/*     */     
/*     */     public boolean canDrain(ForgeDirection from, Fluid fluid)
/*     */     {
/* 813 */       if (fluid == null) {
/* 814 */         return false;
/*     */       }
/* 816 */       return fluid.getName().equals(FluidRegistry.WATER.getName());
/*     */     }
/*     */     
/*     */     public FluidTankInfo[] getTankInfo(ForgeDirection from)
/*     */     {
/* 821 */       return new FluidTankInfo[] { this.tank.getInfo() };
/*     */     }
/*     */     
/*     */     public int bottleCount() {
/* 825 */       ItemStack stack = func_70301_a(7);
/* 826 */       return stack != null ? stack.field_77994_a : 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockKettle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */