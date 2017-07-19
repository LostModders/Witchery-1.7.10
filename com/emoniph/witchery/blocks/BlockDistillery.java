/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.crafting.DistilleryRecipes;
/*     */ import com.emoniph.witchery.crafting.DistilleryRecipes.DistilleryRecipe;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.BlockSide;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SlotClayJar;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.inventory.SlotFurnace;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDistillery extends BlockBaseContainer
/*     */ {
/*  48 */   private final Random furnaceRand = new Random();
/*     */   
/*     */   private final boolean isActive;
/*     */   
/*     */   private static boolean keepFurnaceInventory;
/*     */   
/*     */ 
/*     */   public BlockDistillery(boolean burning)
/*     */   {
/*  57 */     super(Material.field_151573_f, TileEntityDistillery.class);
/*  58 */     this.registerTileEntity = (!burning);
/*  59 */     this.registerWithCreateTab = (!burning);
/*     */     
/*  61 */     this.isActive = burning;
/*     */     
/*  63 */     func_149711_c(3.5F);
/*  64 */     func_149672_a(field_149777_j);
/*  65 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  67 */     if (burning) {
/*  68 */       func_149715_a(0.4F);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/*  90 */     this.field_149761_L = par1IconRegister.func_94245_a(func_149641_N());
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/*  95 */     return Item.func_150898_a(Witchery.Blocks.DISTILLERY_IDLE);
/*     */   }
/*     */   
/*     */   public void func_149726_b(World par1World, int par2, int par3, int par4)
/*     */   {
/* 100 */     super.func_149726_b(par1World, par2, par3, par4);
/* 101 */     BlockUtil.setBlockDefaultDirection(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
/*     */   {
/* 106 */     if (par1World.field_72995_K) {
/* 107 */       return true;
/*     */     }
/* 109 */     TileEntityDistillery tileentityfurnace = (TileEntityDistillery)par1World.func_147438_o(par2, par3, par4);
/*     */     
/* 111 */     if (tileentityfurnace != null) {
/* 112 */       par5EntityPlayer.openGui(Witchery.instance, 3, par1World, par2, par3, par4);
/*     */     }
/*     */     
/* 115 */     return true;
/*     */   }
/*     */   
/*     */   public static void updateDistilleryBlockState(boolean par0, World par1World, int par2, int par3, int par4)
/*     */   {
/* 120 */     int l = par1World.func_72805_g(par2, par3, par4);
/* 121 */     TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
/* 122 */     keepFurnaceInventory = true;
/*     */     
/* 124 */     if (par0) {
/* 125 */       par1World.func_147449_b(par2, par3, par4, Witchery.Blocks.DISTILLERY_BURNING);
/*     */     } else {
/* 127 */       par1World.func_147449_b(par2, par3, par4, Witchery.Blocks.DISTILLERY_IDLE);
/*     */     }
/*     */     
/* 130 */     keepFurnaceInventory = false;
/* 131 */     par1World.func_72921_c(par2, par3, par4, l, 2);
/*     */     
/* 133 */     if (tileentity != null) {
/* 134 */       tileentity.func_145829_t();
/* 135 */       par1World.func_147455_a(par2, par3, par4, tileentity);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 142 */     if (this.isActive) {
/* 143 */       double d0 = par2 + 0.4F + par5Random.nextFloat() * 0.2F;
/* 144 */       double d1 = par3 + 1.0F + par5Random.nextFloat() * 0.3F;
/* 145 */       double d2 = par4 + 0.4F + par5Random.nextFloat() * 0.2F;
/* 146 */       par1World.func_72869_a(ParticleEffect.INSTANT_SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/* 152 */     int l = net.minecraft.util.MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/* 154 */     if (l == 0) {
/* 155 */       par1World.func_72921_c(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/* 158 */     if (l == 1) {
/* 159 */       par1World.func_72921_c(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/* 162 */     if (l == 2) {
/* 163 */       par1World.func_72921_c(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/* 166 */     if (l == 3) {
/* 167 */       par1World.func_72921_c(par2, par3, par4, 4, 2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 177 */     if (!keepFurnaceInventory) {
/* 178 */       TileEntityDistillery tileentityfurnace = (TileEntityDistillery)par1World.func_147438_o(par2, par3, par4);
/*     */       
/* 180 */       if (tileentityfurnace != null) {
/* 181 */         for (int j1 = 0; j1 < tileentityfurnace.func_70302_i_(); j1++) {
/* 182 */           ItemStack itemstack = tileentityfurnace.func_70301_a(j1);
/*     */           
/* 184 */           if (itemstack != null) {
/* 185 */             float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/* 186 */             float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/* 187 */             float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/*     */             
/* 189 */             while (itemstack.field_77994_a > 0) {
/* 190 */               int k1 = this.furnaceRand.nextInt(21) + 10;
/*     */               
/* 192 */               if (k1 > itemstack.field_77994_a) {
/* 193 */                 k1 = itemstack.field_77994_a;
/*     */               }
/*     */               
/* 196 */               itemstack.field_77994_a -= k1;
/* 197 */               EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/*     */               
/*     */ 
/* 200 */               if (itemstack.func_77942_o()) {
/* 201 */                 entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */               }
/*     */               
/* 204 */               float f3 = 0.05F;
/* 205 */               entityitem.field_70159_w = ((float)this.furnaceRand.nextGaussian() * f3);
/* 206 */               entityitem.field_70181_x = ((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
/* 207 */               entityitem.field_70179_y = ((float)this.furnaceRand.nextGaussian() * f3);
/* 208 */               par1World.func_72838_d(entityitem);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 213 */         par1World.func_147453_f(par2, par3, par4, par5);
/*     */       }
/*     */     }
/*     */     
/* 217 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 222 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World par1World, int par2, int par3, int par4, int par5)
/*     */   {
/* 227 */     return Container.func_94526_b((IInventory)par1World.func_147438_o(par2, par3, par4));
/*     */   }
/*     */   
/*     */   public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
/*     */   {
/* 232 */     return Item.func_150898_a(Witchery.Blocks.DISTILLERY_IDLE);
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
/*     */   public static class TileEntityDistillery
/*     */     extends TileEntityBase
/*     */     implements net.minecraft.inventory.ISidedInventory
/*     */   {
/* 248 */     private ItemStack[] furnaceItemStacks = new ItemStack[7];
/*     */     
/*     */     public int currentItemBurnTime;
/*     */     
/*     */     public int furnaceCookTime;
/*     */     
/*     */     public int powerLevel;
/*     */     
/*     */     static final int COOK_TIME = 800;
/*     */     
/*     */     Coord powerSourceCoord;
/*     */     
/*     */     static final int POWER_SOURCE_RADIUS = 16;
/*     */     
/*     */     static final float POWER_PER_TICK = 0.6F;
/*     */     
/*     */     public int func_70302_i_()
/*     */     {
/* 266 */       return this.furnaceItemStacks.length;
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int par1)
/*     */     {
/* 271 */       return this.furnaceItemStacks[par1];
/*     */     }
/*     */     
/*     */     public ItemStack func_70298_a(int par1, int par2)
/*     */     {
/* 276 */       if (this.furnaceItemStacks[par1] != null)
/*     */       {
/*     */ 
/* 279 */         if (this.furnaceItemStacks[par1].field_77994_a <= par2) {
/* 280 */           ItemStack itemstack = this.furnaceItemStacks[par1];
/* 281 */           this.furnaceItemStacks[par1] = null;
/* 282 */           return itemstack;
/*     */         }
/* 284 */         ItemStack itemstack = this.furnaceItemStacks[par1].func_77979_a(par2);
/*     */         
/* 286 */         if (this.furnaceItemStacks[par1].field_77994_a == 0) {
/* 287 */           this.furnaceItemStacks[par1] = null;
/*     */         }
/*     */         
/* 290 */         return itemstack;
/*     */       }
/*     */       
/* 293 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public ItemStack func_70304_b(int par1)
/*     */     {
/* 299 */       if (this.furnaceItemStacks[par1] != null) {
/* 300 */         ItemStack itemstack = this.furnaceItemStacks[par1];
/* 301 */         this.furnaceItemStacks[par1] = null;
/* 302 */         return itemstack;
/*     */       }
/* 304 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */     {
/* 310 */       this.furnaceItemStacks[par1] = par2ItemStack;
/*     */       
/* 312 */       if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_())) {
/* 313 */         par2ItemStack.field_77994_a = func_70297_j_();
/*     */       }
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 319 */       return func_145838_q().func_149732_F();
/*     */     }
/*     */     
/*     */     public boolean func_145818_k_()
/*     */     {
/* 324 */       return true;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 329 */       super.func_145839_a(par1NBTTagCompound);
/* 330 */       NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
/* 331 */       this.furnaceItemStacks = new ItemStack[func_70302_i_()];
/*     */       
/* 333 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 334 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 335 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */         
/* 337 */         if ((b0 >= 0) && (b0 < this.furnaceItemStacks.length)) {
/* 338 */           this.furnaceItemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 343 */       this.furnaceCookTime = par1NBTTagCompound.func_74765_d("CookTime");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 350 */       super.func_145841_b(par1NBTTagCompound);
/*     */       
/*     */ 
/* 353 */       par1NBTTagCompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
/* 354 */       NBTTagList nbttaglist = new NBTTagList();
/*     */       
/* 356 */       for (int i = 0; i < this.furnaceItemStacks.length; i++) {
/* 357 */         if (this.furnaceItemStacks[i] != null) {
/* 358 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 359 */           nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 360 */           this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
/* 361 */           nbttaglist.func_74742_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 365 */       par1NBTTagCompound.func_74782_a("Items", nbttaglist);
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 370 */       return 64;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public int getCookProgressScaled(int par1) {
/* 375 */       return this.furnaceCookTime * par1 / 800;
/*     */     }
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
/*     */     IPowerSource getPowerSource()
/*     */     {
/* 396 */       if ((this.powerSourceCoord == null) || (this.ticks % 100L == 0L)) {
/* 397 */         return findNewPowerSource();
/*     */       }
/* 399 */       TileEntity tileEntity = this.powerSourceCoord.getBlockTileEntity(this.field_145850_b);
/* 400 */       if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
/* 401 */         return findNewPowerSource();
/*     */       }
/* 403 */       BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
/* 404 */       if (!altarTileEntity.isValid()) {
/* 405 */         return findNewPowerSource();
/*     */       }
/* 407 */       return altarTileEntity;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private IPowerSource findNewPowerSource()
/*     */     {
/* 416 */       List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(this.field_145850_b, new Coord(this), 16) : null;
/* 417 */       return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_145845_h()
/*     */     {
/* 424 */       super.func_145845_h();
/*     */       
/* 426 */       boolean flag1 = false;
/*     */       
/* 428 */       if (!this.field_145850_b.field_72995_K) {
/* 429 */         boolean cooking = this.furnaceCookTime > 0;
/* 430 */         boolean powered = this.powerLevel > 0;
/* 431 */         if (canSmelt()) {
/* 432 */           IPowerSource powerSource = getPowerSource();
/* 433 */           if ((powerSource != null) && (!powerSource.isLocationEqual(this.powerSourceCoord))) {
/* 434 */             this.powerSourceCoord = powerSource.getLocation();
/*     */           } else {
/* 436 */             this.powerSourceCoord = null;
/*     */           }
/*     */           
/* 439 */           this.powerLevel = (powerSource == null ? 0 : 1);
/*     */           
/* 441 */           if ((powerSource != null) && (powerSource.consumePower(0.6F))) {
/* 442 */             this.furnaceCookTime += 1;
/*     */             
/* 444 */             if (this.furnaceCookTime == 800) {
/* 445 */               this.furnaceCookTime = 0;
/* 446 */               smeltItem();
/* 447 */               flag1 = true;
/*     */             }
/*     */           } else {
/* 450 */             this.powerLevel = 0;
/*     */           }
/*     */         } else {
/* 453 */           if (this.ticks % 40L == 0L) {
/* 454 */             IPowerSource powerSource = getPowerSource();
/* 455 */             if ((powerSource != null) && (!powerSource.isLocationEqual(this.powerSourceCoord))) {
/* 456 */               this.powerSourceCoord = powerSource.getLocation();
/*     */             }
/*     */             
/* 459 */             this.powerLevel = (powerSource == null ? 0 : 1);
/*     */           }
/*     */           
/* 462 */           this.furnaceCookTime = 0;
/*     */         }
/*     */         
/* 465 */         if (cooking != this.furnaceCookTime > 0) {
/* 466 */           BlockDistillery.updateDistilleryBlockState((this.furnaceCookTime > 0) && (this.powerLevel > 0), this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 467 */           this.lastUpdate = this.ticks;
/* 468 */           this.needUpdate = false;
/* 469 */         } else if (powered != this.powerLevel > 0) {
/* 470 */           if (this.ticks - this.lastUpdate > 20L) {
/* 471 */             BlockDistillery.updateDistilleryBlockState((this.furnaceCookTime > 0) && (this.powerLevel > 0), this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 472 */             this.lastUpdate = this.ticks;
/* 473 */             this.needUpdate = false;
/*     */           } else {
/* 475 */             this.needUpdate = true;
/*     */           }
/* 477 */         } else if ((this.needUpdate) && (this.ticks - this.lastUpdate > 20L)) {
/* 478 */           BlockDistillery.updateDistilleryBlockState((this.furnaceCookTime > 0) && (this.powerLevel > 0), this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 479 */           this.lastUpdate = this.ticks;
/* 480 */           this.needUpdate = false;
/*     */         }
/*     */         
/* 483 */         if (flag1) {
/* 484 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 489 */     private long lastUpdate = 0L;
/* 490 */     private boolean needUpdate = false;
/*     */     private static final int THROTTLE = 20;
/*     */     
/*     */     private boolean canSmelt() {
/* 494 */       DistilleryRecipes.DistilleryRecipe recipe = getActiveRecipe();
/*     */       
/* 496 */       if (recipe == null) {
/* 497 */         return false;
/*     */       }
/*     */       
/* 500 */       ItemStack[] itemstacks = recipe.getOutputs();
/*     */       
/* 502 */       for (int i = 0; i < itemstacks.length; i++) {
/* 503 */         ItemStack current = this.furnaceItemStacks[(i + 3)];
/* 504 */         if ((itemstacks[i] != null) && (current != null) && (current.func_77969_a(itemstacks[i]))) {
/* 505 */           int newSize = current.field_77994_a + itemstacks[i].field_77994_a;
/* 506 */           if ((newSize > func_70297_j_()) || (newSize > current.func_77976_d())) {
/* 507 */             return false;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 512 */       return true;
/*     */     }
/*     */     
/*     */     public DistilleryRecipes.DistilleryRecipe getActiveRecipe() {
/* 516 */       if ((this.furnaceItemStacks[0] == null) && (this.furnaceItemStacks[1] == null)) {
/* 517 */         return null;
/*     */       }
/*     */       
/* 520 */       DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().getDistillingResult(this.furnaceItemStacks[0], this.furnaceItemStacks[1], this.furnaceItemStacks[2]);
/*     */       
/*     */ 
/* 523 */       return recipe;
/*     */     }
/*     */     
/*     */     public void smeltItem() {
/* 527 */       if (canSmelt()) {
/* 528 */         DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().getDistillingResult(this.furnaceItemStacks[0], this.furnaceItemStacks[1], this.furnaceItemStacks[2]);
/*     */         
/* 530 */         ItemStack[] itemstacks = recipe.getOutputs();
/*     */         
/* 532 */         for (int i = 0; i < itemstacks.length; i++) {
/* 533 */           int furnaceIndex = i + 3;
/* 534 */           if (itemstacks[i] != null) {
/* 535 */             if (this.furnaceItemStacks[furnaceIndex] == null) {
/* 536 */               this.furnaceItemStacks[furnaceIndex] = itemstacks[i].func_77946_l();
/* 537 */             } else if (this.furnaceItemStacks[furnaceIndex].func_77969_a(itemstacks[i])) {
/* 538 */               this.furnaceItemStacks[furnaceIndex].field_77994_a += itemstacks[i].field_77994_a;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 543 */         if (this.furnaceItemStacks[0] != null) {
/* 544 */           this.furnaceItemStacks[0].field_77994_a -= 1;
/*     */           
/* 546 */           if (this.furnaceItemStacks[0].field_77994_a <= 0) {
/* 547 */             this.furnaceItemStacks[0] = null;
/*     */           }
/*     */         }
/*     */         
/* 551 */         if (this.furnaceItemStacks[1] != null) {
/* 552 */           this.furnaceItemStacks[1].field_77994_a -= 1;
/*     */           
/* 554 */           if (this.furnaceItemStacks[1].field_77994_a <= 0) {
/* 555 */             this.furnaceItemStacks[1] = null;
/*     */           }
/*     */         }
/*     */         
/* 559 */         if (this.furnaceItemStacks[2] != null) {
/* 560 */           this.furnaceItemStacks[2].field_77994_a -= recipe.getJars();
/*     */           
/* 562 */           if (this.furnaceItemStacks[2].field_77994_a <= 0) {
/* 563 */             this.furnaceItemStacks[2] = null;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */     {
/* 572 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_70295_k_() {}
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_70305_f() {}
/*     */     
/*     */ 
/*     */     public boolean func_94041_b(int slot, ItemStack itemstack)
/*     */     {
/* 586 */       if (slot > 3)
/* 587 */         return false;
/* 588 */       if (slot == 2) {
/* 589 */         return Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(itemstack);
/*     */       }
/* 591 */       return !Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(itemstack);
/*     */     }
/*     */     
/*     */ 
/* 595 */     private static final int[] slots_top = { 0, 1, 2 };
/* 596 */     private static final int[] slots_bottom = { 0, 1, 2 };
/* 597 */     private static final int[] slots_sides = { 0, 1, 2, 3, 4, 5, 6 };
/*     */     
/*     */     public int[] func_94128_d(int side)
/*     */     {
/* 601 */       return BlockSide.TOP.isEqual(side) ? slots_top : BlockSide.BOTTOM.isEqual(side) ? slots_bottom : slots_sides;
/*     */     }
/*     */     
/*     */     public boolean func_102007_a(int slot, ItemStack itemstack, int par3)
/*     */     {
/* 606 */       return func_94041_b(slot, itemstack);
/*     */     }
/*     */     
/*     */     public boolean func_102008_b(int slot, ItemStack itemstack, int side)
/*     */     {
/* 611 */       return (side != 0) && (side != 1) && ((slot == 3) || (slot == 4) || (slot == 5) || (slot == 6));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Packet func_145844_m()
/*     */     {
/* 624 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 625 */       func_145841_b(nbtTag);
/* 626 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 631 */       super.onDataPacket(net, packet);
/* 632 */       func_145839_a(packet.func_148857_g());
/* 633 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class ContainerDistillery
/*     */     extends Container
/*     */   {
/*     */     private BlockDistillery.TileEntityDistillery furnace;
/*     */     
/*     */     private int lastCookTime;
/*     */     
/*     */     private int lastPowerLevel;
/*     */     
/*     */     public ContainerDistillery(InventoryPlayer par1InventoryPlayer, BlockDistillery.TileEntityDistillery par2TileEntityFurnace)
/*     */     {
/* 649 */       this.furnace = par2TileEntityFurnace;
/* 650 */       func_75146_a(new Slot(par2TileEntityFurnace, 0, 48, 16));
/* 651 */       func_75146_a(new Slot(par2TileEntityFurnace, 1, 48, 34));
/* 652 */       func_75146_a(new SlotClayJar(par2TileEntityFurnace, 2, 48, 54));
/* 653 */       func_75146_a(new SlotFurnace(par1InventoryPlayer.field_70458_d, par2TileEntityFurnace, 3, 110, 16));
/* 654 */       func_75146_a(new SlotFurnace(par1InventoryPlayer.field_70458_d, par2TileEntityFurnace, 4, 128, 16));
/* 655 */       func_75146_a(new SlotFurnace(par1InventoryPlayer.field_70458_d, par2TileEntityFurnace, 5, 110, 34));
/* 656 */       func_75146_a(new SlotFurnace(par1InventoryPlayer.field_70458_d, par2TileEntityFurnace, 6, 128, 34));
/*     */       
/*     */ 
/*     */ 
/* 660 */       for (int i = 0; i < 3; i++) {
/* 661 */         for (int j = 0; j < 9; j++) {
/* 662 */           func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */         }
/*     */       }
/*     */       
/* 666 */       for (i = 0; i < 9; i++) {
/* 667 */         func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_75132_a(ICrafting par1ICrafting)
/*     */     {
/* 673 */       super.func_75132_a(par1ICrafting);
/* 674 */       par1ICrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/* 675 */       par1ICrafting.func_71112_a(this, 1, this.furnace.powerLevel);
/*     */     }
/*     */     
/*     */     public void func_75142_b()
/*     */     {
/* 680 */       super.func_75142_b();
/*     */       
/* 682 */       for (int i = 0; i < this.field_75149_d.size(); i++) {
/* 683 */         ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */         
/* 685 */         if (this.lastCookTime != this.furnace.furnaceCookTime) {
/* 686 */           icrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*     */         }
/*     */         
/* 689 */         if (this.lastPowerLevel != this.furnace.powerLevel) {
/* 690 */           icrafting.func_71112_a(this, 1, this.furnace.powerLevel);
/*     */         }
/*     */       }
/*     */       
/* 694 */       this.lastCookTime = this.furnace.furnaceCookTime;
/* 695 */       this.lastPowerLevel = this.furnace.powerLevel;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void func_75137_b(int par1, int par2)
/*     */     {
/* 701 */       if (par1 == 0) {
/* 702 */         this.furnace.furnaceCookTime = par2;
/*     */       }
/*     */       
/* 705 */       if (par1 == 1) {
/* 706 */         this.furnace.powerLevel = par2;
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */     {
/* 712 */       return this.furnace.func_70300_a(par1EntityPlayer);
/*     */     }
/*     */     
/*     */     public ItemStack func_82846_b(EntityPlayer player, int slotIndex)
/*     */     {
/* 717 */       ItemStack itemstack = null;
/* 718 */       Slot slot = (Slot)this.field_75151_b.get(slotIndex);
/*     */       
/* 720 */       if ((slot != null) && (slot.func_75216_d())) {
/* 721 */         ItemStack itemstack1 = slot.func_75211_c();
/* 722 */         itemstack = itemstack1.func_77946_l();
/*     */         
/* 724 */         if ((slotIndex >= 3) && (slotIndex <= 6)) {
/* 725 */           if (!func_75135_a(itemstack1, 7, 43, true)) {
/* 726 */             return null;
/*     */           }
/*     */           
/* 729 */           slot.func_75220_a(itemstack1, itemstack);
/* 730 */         } else if ((slotIndex != 1) && (slotIndex != 0) && (slotIndex != 2)) {
/* 731 */           if (FurnaceRecipes.func_77602_a().func_151395_a(itemstack1) != null) {
/* 732 */             if (!func_75135_a(itemstack1, 0, 2, false)) {
/* 733 */               return null;
/*     */             }
/* 735 */           } else if (Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(itemstack1)) {
/* 736 */             if (!func_75135_a(itemstack1, 2, 3, false)) {
/* 737 */               return null;
/*     */             }
/* 739 */           } else if ((slotIndex >= 7) && (slotIndex < 34)) {
/* 740 */             if (!func_75135_a(itemstack1, 34, 43, false)) {
/* 741 */               return null;
/*     */             }
/* 743 */           } else if ((slotIndex >= 34) && (slotIndex < 43) && (!func_75135_a(itemstack1, 7, 34, false))) {
/* 744 */             return null;
/*     */           }
/* 746 */         } else if (!func_75135_a(itemstack1, 7, 43, false)) {
/* 747 */           return null;
/*     */         }
/*     */         
/* 750 */         if (itemstack1.field_77994_a == 0) {
/* 751 */           slot.func_75215_d((ItemStack)null);
/*     */         } else {
/* 753 */           slot.func_75218_e();
/*     */         }
/*     */         
/* 756 */         if (itemstack1.field_77994_a == itemstack.field_77994_a) {
/* 757 */           return null;
/*     */         }
/*     */         
/* 760 */         slot.func_82870_a(player, itemstack1);
/*     */       }
/*     */       
/* 763 */       return itemstack;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockDistillery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */