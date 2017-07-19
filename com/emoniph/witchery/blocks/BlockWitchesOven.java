/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.BlockSide;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.SlotClayJar;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.inventory.SlotFurnace;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockWitchesOven extends BlockBaseContainer
/*     */ {
/*  45 */   private final Random furnaceRand = new Random();
/*     */   
/*     */   private final boolean isActive;
/*     */   
/*     */   private static boolean keepFurnaceInventory;
/*     */   
/*     */ 
/*     */   public BlockWitchesOven(boolean burning)
/*     */   {
/*  54 */     super(Material.field_151573_f, TileEntityWitchesOven.class);
/*  55 */     this.registerTileEntity = (!burning);
/*  56 */     this.registerWithCreateTab = (!burning);
/*     */     
/*  58 */     this.isActive = burning;
/*     */     
/*  60 */     func_149711_c(3.5F);
/*  61 */     func_149672_a(field_149777_j);
/*  62 */     if (this.isActive) {
/*  63 */       func_149715_a(0.875F);
/*     */     }
/*  65 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/*  85 */     return Item.func_150898_a(Witchery.Blocks.OVEN_IDLE);
/*     */   }
/*     */   
/*     */   public void func_149726_b(World par1World, int par2, int par3, int par4)
/*     */   {
/*  90 */     super.func_149726_b(par1World, par2, par3, par4);
/*  91 */     BlockUtil.setBlockDefaultDirection(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public static boolean isOven(Block block) {
/*  95 */     return (block == Witchery.Blocks.OVEN_IDLE) || (block == Witchery.Blocks.OVEN_BURNING);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
/*     */   {
/* 101 */     if (par1World.field_72995_K) {
/* 102 */       return true;
/*     */     }
/* 104 */     TileEntity tileentityfurnace = par1World.func_147438_o(par2, par3, par4);
/*     */     
/* 106 */     if (tileentityfurnace != null) {
/* 107 */       par5EntityPlayer.openGui(Witchery.instance, 2, par1World, par2, par3, par4);
/*     */     }
/*     */     
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public static void updateWitchesOvenBlockState(boolean par0, World par1World, int par2, int par3, int par4)
/*     */   {
/* 115 */     int l = par1World.func_72805_g(par2, par3, par4);
/* 116 */     TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
/* 117 */     keepFurnaceInventory = true;
/*     */     
/* 119 */     if (par0) {
/* 120 */       par1World.func_147449_b(par2, par3, par4, Witchery.Blocks.OVEN_BURNING);
/*     */     } else {
/* 122 */       par1World.func_147449_b(par2, par3, par4, Witchery.Blocks.OVEN_IDLE);
/*     */     }
/*     */     
/* 125 */     keepFurnaceInventory = false;
/* 126 */     par1World.func_72921_c(par2, par3, par4, l, 2);
/*     */     
/* 128 */     if (tileentity != null) {
/* 129 */       tileentity.func_145829_t();
/* 130 */       par1World.func_147455_a(par2, par3, par4, tileentity);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
/*     */   {
/* 137 */     if (this.isActive) {
/* 138 */       int l = par1World.func_72805_g(par2, par3, par4);
/* 139 */       float f = par2 + 0.5F;
/* 140 */       float f1 = par3 + 0.2F + par5Random.nextFloat() * 6.0F / 16.0F;
/* 141 */       float f2 = par4 + 0.5F;
/* 142 */       float f3 = 0.52F;
/* 143 */       float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
/*     */       
/* 145 */       if (l == 4) {
/* 146 */         par1World.func_72869_a("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 147 */         par1World.func_72869_a("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 148 */       } else if (l == 5) {
/* 149 */         par1World.func_72869_a("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 150 */         par1World.func_72869_a("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 151 */       } else if (l == 2) {
/* 152 */         par1World.func_72869_a("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/* 153 */         par1World.func_72869_a("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/* 154 */       } else if (l == 3) {
/* 155 */         par1World.func_72869_a("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/* 156 */         par1World.func_72869_a("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/* 164 */     int l = MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/* 166 */     if (l == 0) {
/* 167 */       par1World.func_72921_c(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/* 170 */     if (l == 1) {
/* 171 */       par1World.func_72921_c(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/* 174 */     if (l == 2) {
/* 175 */       par1World.func_72921_c(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/* 178 */     if (l == 3) {
/* 179 */       par1World.func_72921_c(par2, par3, par4, 4, 2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 190 */     if (!keepFurnaceInventory) {
/* 191 */       TileEntity tile = par1World.func_147438_o(par2, par3, par4);
/* 192 */       TileEntityWitchesOven tileentityfurnace = (TileEntityWitchesOven)BlockUtil.getTileEntity(par1World, par2, par3, par4, TileEntityWitchesOven.class);
/*     */       
/* 194 */       if (tileentityfurnace != null)
/*     */       {
/* 196 */         for (int j1 = 0; j1 < tileentityfurnace.func_70302_i_(); j1++) {
/* 197 */           ItemStack itemstack = tileentityfurnace.func_70301_a(j1);
/*     */           
/* 199 */           if (itemstack != null) {
/* 200 */             float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/* 201 */             float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/* 202 */             float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
/*     */             
/* 204 */             while (itemstack.field_77994_a > 0) {
/* 205 */               int k1 = this.furnaceRand.nextInt(21) + 10;
/*     */               
/* 207 */               if (k1 > itemstack.field_77994_a) {
/* 208 */                 k1 = itemstack.field_77994_a;
/*     */               }
/*     */               
/* 211 */               itemstack.field_77994_a -= k1;
/* 212 */               EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/*     */               
/*     */ 
/* 215 */               if (itemstack.func_77942_o()) {
/* 216 */                 entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */               }
/*     */               
/*     */ 
/* 220 */               float f3 = 0.05F;
/* 221 */               entityitem.field_70159_w = ((float)this.furnaceRand.nextGaussian() * f3);
/* 222 */               entityitem.field_70181_x = ((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
/* 223 */               entityitem.field_70179_y = ((float)this.furnaceRand.nextGaussian() * f3);
/* 224 */               par1World.func_72838_d(entityitem);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 229 */         par1World.func_147453_f(par2, par3, par4, par5);
/*     */       }
/*     */     }
/*     */     
/* 233 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 238 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World par1World, int par2, int par3, int par4, int par5)
/*     */   {
/* 243 */     TileEntity te = par1World.func_147438_o(par2, par3, par4);
/* 244 */     return (te != null) && ((te instanceof IInventory)) ? Container.func_94526_b((IInventory)te) : 0;
/*     */   }
/*     */   
/*     */   public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
/*     */   {
/* 249 */     return Item.func_150898_a(Witchery.Blocks.OVEN_IDLE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class TileEntityWitchesOven
/*     */     extends TileEntity
/*     */     implements ISidedInventory
/*     */   {
/* 263 */     private ItemStack[] furnaceItemStacks = new ItemStack[5];
/*     */     public int furnaceBurnTime;
/*     */     public int currentItemBurnTime;
/*     */     public int furnaceCookTime;
/*     */     static final int COOK_TIME = 180;
/*     */     private static final double FUNNEL_CHANCE = 0.25D;
/*     */     private static final double FILTERED_FUNNEL_CHANCE = 0.3D;
/*     */     private static final double DOUBLED_FILTERED_FUNNEL_CHANCE = 0.8D;
/*     */     private static final int SLOT_TO_COOK = 0;
/*     */     private static final int SLOT_FUEL = 1;
/*     */     private static final int SLOT_COOKED = 2;
/*     */     private static final int SLOT_BY_PRODUCT = 3;
/*     */     private static final int SLOT_JARS = 4;
/*     */     
/*     */     public int func_70302_i_()
/*     */     {
/* 279 */       return this.furnaceItemStacks.length;
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int par1)
/*     */     {
/* 284 */       return this.furnaceItemStacks[par1];
/*     */     }
/*     */     
/*     */     public ItemStack func_70298_a(int par1, int par2)
/*     */     {
/* 289 */       if (this.furnaceItemStacks[par1] != null)
/*     */       {
/*     */ 
/* 292 */         if (this.furnaceItemStacks[par1].field_77994_a <= par2) {
/* 293 */           ItemStack itemstack = this.furnaceItemStacks[par1];
/* 294 */           this.furnaceItemStacks[par1] = null;
/* 295 */           return itemstack;
/*     */         }
/* 297 */         ItemStack itemstack = this.furnaceItemStacks[par1].func_77979_a(par2);
/*     */         
/* 299 */         if (this.furnaceItemStacks[par1].field_77994_a == 0) {
/* 300 */           this.furnaceItemStacks[par1] = null;
/*     */         }
/*     */         
/* 303 */         return itemstack;
/*     */       }
/*     */       
/* 306 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public ItemStack func_70304_b(int par1)
/*     */     {
/* 312 */       if (this.furnaceItemStacks[par1] != null) {
/* 313 */         ItemStack itemstack = this.furnaceItemStacks[par1];
/* 314 */         this.furnaceItemStacks[par1] = null;
/* 315 */         return itemstack;
/*     */       }
/* 317 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */     {
/* 323 */       this.furnaceItemStacks[par1] = par2ItemStack;
/*     */       
/* 325 */       if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_())) {
/* 326 */         par2ItemStack.field_77994_a = func_70297_j_();
/*     */       }
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 332 */       return func_145838_q().func_149732_F();
/*     */     }
/*     */     
/*     */     public boolean func_145818_k_()
/*     */     {
/* 337 */       return true;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 342 */       super.func_145839_a(par1NBTTagCompound);
/* 343 */       NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
/* 344 */       this.furnaceItemStacks = new ItemStack[func_70302_i_()];
/*     */       
/* 346 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 347 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 348 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */         
/* 350 */         if ((b0 >= 0) && (b0 < this.furnaceItemStacks.length)) {
/* 351 */           this.furnaceItemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 355 */       this.furnaceBurnTime = par1NBTTagCompound.func_74765_d("BurnTime");
/* 356 */       this.furnaceCookTime = par1NBTTagCompound.func_74765_d("CookTime");
/* 357 */       this.currentItemBurnTime = TileEntityFurnace.func_145952_a(this.furnaceItemStacks[1]);
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 362 */       super.func_145841_b(par1NBTTagCompound);
/* 363 */       par1NBTTagCompound.func_74777_a("BurnTime", (short)this.furnaceBurnTime);
/* 364 */       par1NBTTagCompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
/* 365 */       NBTTagList nbttaglist = new NBTTagList();
/*     */       
/* 367 */       for (int i = 0; i < this.furnaceItemStacks.length; i++) {
/* 368 */         if (this.furnaceItemStacks[i] != null) {
/* 369 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 370 */           nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 371 */           this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
/* 372 */           nbttaglist.func_74742_a(nbttagcompound1);
/*     */         }
/*     */       }
/*     */       
/* 376 */       par1NBTTagCompound.func_74782_a("Items", nbttaglist);
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 381 */       return 64;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public int getCookProgressScaled(int par1) {
/* 386 */       return this.furnaceCookTime * par1 / getCookTime();
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public int getBurnTimeRemainingScaled(int par1) {
/* 391 */       if (this.currentItemBurnTime == 0) {
/* 392 */         this.currentItemBurnTime = 200;
/*     */       }
/*     */       
/* 395 */       return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
/*     */     }
/*     */     
/*     */     public boolean isBurning() {
/* 399 */       return this.furnaceBurnTime > 0;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_145845_h()
/*     */     {
/* 406 */       boolean flag = this.furnaceBurnTime > 0;
/* 407 */       boolean flag1 = false;
/*     */       
/* 409 */       if (this.furnaceBurnTime > 0) {
/* 410 */         this.furnaceBurnTime -= 1;
/*     */       }
/*     */       
/* 413 */       if (!this.field_145850_b.field_72995_K) {
/* 414 */         if ((this.furnaceBurnTime == 0) && (canSmelt())) {
/* 415 */           this.currentItemBurnTime = (this.furnaceBurnTime = TileEntityFurnace.func_145952_a(this.furnaceItemStacks[1]));
/*     */           
/*     */ 
/* 418 */           if (this.furnaceBurnTime > 0) {
/* 419 */             flag1 = true;
/*     */             
/* 421 */             if (this.furnaceItemStacks[1] != null) {
/* 422 */               this.furnaceItemStacks[1].field_77994_a -= 1;
/*     */               
/* 424 */               if (this.furnaceItemStacks[1].field_77994_a == 0) {
/* 425 */                 this.furnaceItemStacks[1] = this.furnaceItemStacks[1].func_77973_b().getContainerItem(this.furnaceItemStacks[1]);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 432 */         if ((isBurning()) && (canSmelt())) {
/* 433 */           this.furnaceCookTime += 1;
/*     */           
/* 435 */           if (this.furnaceCookTime >= getCookTime()) {
/* 436 */             this.furnaceCookTime = 0;
/* 437 */             smeltItem();
/* 438 */             flag1 = true;
/*     */           }
/*     */         } else {
/* 441 */           this.furnaceCookTime = 0;
/*     */         }
/*     */         
/* 444 */         if (flag != this.furnaceBurnTime > 0) {
/* 445 */           flag1 = true;
/* 446 */           BlockWitchesOven.updateWitchesOvenBlockState(this.furnaceBurnTime > 0, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 451 */       if (flag1) {
/* 452 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     private boolean canSmelt() {
/* 457 */       if (this.furnaceItemStacks[0] == null) {
/* 458 */         return false;
/*     */       }
/* 460 */       ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.furnaceItemStacks[0]);
/*     */       
/* 462 */       if (itemstack == null) {
/* 463 */         return false;
/*     */       }
/* 465 */       Item item = itemstack.func_77973_b();
/*     */       
/* 467 */       if ((item != Items.field_151044_h) && (!(item instanceof ItemFood)) && (!Witchery.Items.GENERIC.itemAshWood.isMatch(itemstack)))
/*     */       {
/* 469 */         return false;
/*     */       }
/* 471 */       if (this.furnaceItemStacks[2] == null) {
/* 472 */         return true;
/*     */       }
/* 474 */       if (!this.furnaceItemStacks[2].func_77969_a(itemstack)) {
/* 475 */         return false;
/*     */       }
/* 477 */       int result = this.furnaceItemStacks[2].field_77994_a + itemstack.field_77994_a;
/* 478 */       return (result <= func_70297_j_()) && (result <= itemstack.func_77976_d());
/*     */     }
/*     */     
/*     */     public void smeltItem()
/*     */     {
/* 483 */       if (canSmelt()) {
/* 484 */         ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.furnaceItemStacks[0]);
/*     */         
/* 486 */         if (this.furnaceItemStacks[2] == null) {
/* 487 */           this.furnaceItemStacks[2] = itemstack.func_77946_l();
/* 488 */         } else if (this.furnaceItemStacks[2].func_77969_a(itemstack)) {
/* 489 */           this.furnaceItemStacks[2].field_77994_a += itemstack.field_77994_a;
/*     */         }
/*     */         
/* 492 */         generateByProduct(itemstack);
/*     */         
/* 494 */         this.furnaceItemStacks[0].field_77994_a -= 1;
/*     */         
/* 496 */         if (this.furnaceItemStacks[0].field_77994_a <= 0) {
/* 497 */           this.furnaceItemStacks[0] = null;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private int getFumeFunnels() {
/* 503 */       int funnels = 0;
/* 504 */       int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 505 */       switch (meta) {
/*     */       case 2: 
/*     */       case 3: 
/* 508 */         funnels += (isFumeFunnel(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, meta) ? 1 : 0);
/* 509 */         funnels += (isFumeFunnel(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, meta) ? 1 : 0);
/* 510 */         break;
/*     */       case 4: 
/*     */       case 5: 
/* 513 */         funnels += (isFumeFunnel(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, meta) ? 1 : 0);
/* 514 */         funnels += (isFumeFunnel(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, meta) ? 1 : 0);
/*     */       }
/*     */       
/* 517 */       funnels += (isFumeFunnel(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, meta) ? 1 : 0);
/* 518 */       return funnels;
/*     */     }
/*     */     
/*     */     private boolean isFumeFunnel(int xCoord, int yCoord, int zCoord, int meta) {
/* 522 */       Block block = this.field_145850_b.func_147439_a(xCoord, yCoord, zCoord);
/* 523 */       return ((block == Witchery.Blocks.OVEN_FUMEFUNNEL) || (block == Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED)) && (this.field_145850_b.func_72805_g(xCoord, yCoord, zCoord) == meta);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private double getFumeFunnelsChance()
/*     */     {
/* 532 */       double funnels = 0.0D;
/* 533 */       switch (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*     */       case 2: 
/* 535 */         funnels += getFumeFunnelChance(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, 2);
/* 536 */         funnels += getFumeFunnelChance(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, 2);
/* 537 */         break;
/*     */       case 3: 
/* 539 */         funnels += getFumeFunnelChance(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, 3);
/* 540 */         funnels += getFumeFunnelChance(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, 3);
/* 541 */         break;
/*     */       case 4: 
/* 543 */         funnels += getFumeFunnelChance(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, 4);
/* 544 */         funnels += getFumeFunnelChance(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, 4);
/* 545 */         break;
/*     */       case 5: 
/* 547 */         funnels += getFumeFunnelChance(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, 5);
/* 548 */         funnels += getFumeFunnelChance(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, 5);
/*     */       }
/*     */       
/* 551 */       return funnels;
/*     */     }
/*     */     
/*     */     private double getFumeFunnelChance(int x, int y, int z, int meta) {
/* 555 */       Block block = this.field_145850_b.func_147439_a(x, y, z);
/* 556 */       if (block == Witchery.Blocks.OVEN_FUMEFUNNEL) {
/* 557 */         if (this.field_145850_b.func_72805_g(x, y, z) == meta) {
/* 558 */           return 0.25D;
/*     */         }
/* 560 */       } else if ((block == Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED) && 
/* 561 */         (this.field_145850_b.func_72805_g(x, y, z) == meta)) {
/* 562 */         return Config.instance().doubleFumeFilterChance ? 0.8D : 0.3D;
/*     */       }
/*     */       
/*     */ 
/* 566 */       return 0.0D;
/*     */     }
/*     */     
/*     */     private int getCookTime() {
/* 570 */       int time = 180 - 20 * getFumeFunnels();
/* 571 */       return time;
/*     */     }
/*     */     
/*     */     private void generateByProduct(ItemStack itemstack) {
/*     */       try {
/* 576 */         double BASE_CHANCE = 0.3D;
/* 577 */         double funnels = getFumeFunnelsChance();
/*     */         
/* 579 */         Log.instance().debug("" + this.furnaceItemStacks[0] + ": " + this.furnaceItemStacks[0].func_77973_b().func_77658_a());
/*     */         
/*     */ 
/*     */ 
/* 583 */         if ((this.field_145850_b.field_73012_v.nextDouble() <= Math.min(0.3D + funnels, 1.0D)) && (this.furnaceItemStacks[4] != null))
/*     */         {
/* 585 */           if ((this.furnaceItemStacks[0].func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150345_g)) && (this.furnaceItemStacks[0].func_77960_j() != 3))
/*     */           {
/* 587 */             switch (this.furnaceItemStacks[0].func_77960_j()) {
/*     */             case 0: 
/* 589 */               createByProduct(Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(1));
/* 590 */               break;
/*     */             case 1: 
/* 592 */               createByProduct(Witchery.Items.GENERIC.itemHintOfRebirth.createStack(1));
/* 593 */               break;
/*     */             case 2: 
/* 595 */               createByProduct(Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(1));
/*     */             }
/*     */             
/*     */           }
/* 599 */           else if (this.furnaceItemStacks[0].func_77973_b() == Item.func_150898_a(Witchery.Blocks.SAPLING))
/*     */           {
/* 601 */             switch (this.furnaceItemStacks[0].func_77960_j()) {
/*     */             case 0: 
/* 603 */               createByProduct(Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(1));
/* 604 */               break;
/*     */             case 1: 
/* 606 */               createByProduct(Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(1));
/* 607 */               break;
/*     */             case 2: 
/* 609 */               createByProduct(Witchery.Items.GENERIC.itemOdourOfPurity.createStack(1));
/*     */             }
/*     */           }
/* 612 */           else if ((this.furnaceItemStacks[0].func_77977_a().equals("tile.bop.saplings")) && (this.furnaceItemStacks[0].func_77960_j() == 6))
/*     */           {
/*     */ 
/* 615 */             createByProduct(Witchery.Items.GENERIC.itemHintOfRebirth.createStack(1));
/* 616 */           } else if ((this.furnaceItemStacks[0].func_77942_o()) && (this.furnaceItemStacks[0].func_77978_p().func_74764_b("Genome")))
/*     */           {
/* 618 */             NBTBase tag = this.furnaceItemStacks[0].func_77978_p().func_74781_a("Genome");
/* 619 */             if ((tag != null) && ((tag instanceof NBTTagCompound))) {
/* 620 */               NBTTagCompound compound = (NBTTagCompound)tag;
/* 621 */               if ((compound.func_74764_b("Chromosomes")) && ((compound.func_74781_a("Chromosomes") instanceof NBTTagList)))
/*     */               {
/* 623 */                 NBTTagList list = compound.func_150295_c("Chromosomes", 10);
/*     */                 
/* 625 */                 if ((list != null) && (list.func_74745_c() > 0)) {
/* 626 */                   NBTBase chromoBase = list.func_150305_b(0);
/* 627 */                   if ((chromoBase != null) && ((chromoBase instanceof NBTTagCompound))) {
/* 628 */                     NBTTagCompound chromosome = (NBTTagCompound)chromoBase;
/* 629 */                     if (chromosome.func_74764_b("UID0")) {
/* 630 */                       String treeType = chromosome.func_74779_i("UID0");
/* 631 */                       if (treeType != null) {
/* 632 */                         Log.instance().debug("Forestry tree: " + treeType);
/* 633 */                         if (treeType.equals("forestry.treeOak")) {
/* 634 */                           createByProduct(Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(1));
/*     */                         }
/* 636 */                         else if (treeType.equals("forestry.treeSpruce")) {
/* 637 */                           createByProduct(Witchery.Items.GENERIC.itemHintOfRebirth.createStack(1));
/*     */                         }
/* 639 */                         else if (treeType.equals("forestry.treeBirch")) {
/* 640 */                           createByProduct(Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(1));
/*     */                         }
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 651 */             createByProduct(Witchery.Items.GENERIC.itemFoulFume.createStack(1));
/*     */           }
/*     */         }
/*     */       } catch (Throwable e) {
/* 655 */         Log.instance().warning(e, "Exception occured while generating a by product from a witches oven");
/*     */       }
/*     */     }
/*     */     
/*     */     private void createByProduct(ItemStack byProduct) {
/* 660 */       int BY_PRODUCT_INDEX = 3;
/* 661 */       if (this.furnaceItemStacks[3] == null) {
/* 662 */         this.furnaceItemStacks[3] = byProduct;
/*     */         
/* 664 */         if (--this.furnaceItemStacks[4].field_77994_a <= 0) {
/* 665 */           this.furnaceItemStacks[4] = null;
/*     */         }
/* 667 */       } else if ((this.furnaceItemStacks[3].func_77969_a(byProduct)) && (this.furnaceItemStacks[3].field_77994_a + byProduct.field_77994_a < this.furnaceItemStacks[3].func_77976_d()))
/*     */       {
/*     */ 
/* 670 */         this.furnaceItemStacks[3].field_77994_a += byProduct.field_77994_a;
/*     */         
/* 672 */         if (--this.furnaceItemStacks[4].field_77994_a <= 0) {
/* 673 */           this.furnaceItemStacks[4] = null;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */     {
/* 681 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 695 */       if ((slot == 2) || (slot == 3))
/* 696 */         return false;
/* 697 */       if (slot == 1)
/* 698 */         return TileEntityFurnace.func_145954_b(itemstack);
/* 699 */       if (slot == 4)
/* 700 */         return Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(itemstack);
/* 701 */       if ((slot == 0) && (Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(itemstack))) {
/* 702 */         return false;
/*     */       }
/* 704 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 714 */     private static final int[] slots_top = { 0, 4 };
/* 715 */     private static final int[] slots_bottom = { 4, 1 };
/* 716 */     private static final int[] slots_sides = { 3, 2, 4, 1 };
/*     */     
/*     */     public int[] func_94128_d(int side)
/*     */     {
/* 720 */       return BlockSide.TOP.isEqual(side) ? slots_top : BlockSide.BOTTOM.isEqual(side) ? slots_bottom : slots_sides;
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean func_102007_a(int slot, ItemStack itemstack, int par3)
/*     */     {
/* 726 */       return func_94041_b(slot, itemstack);
/*     */     }
/*     */     
/*     */     public boolean func_102008_b(int slot, ItemStack stack, int side)
/*     */     {
/* 731 */       if (BlockSide.TOP.isEqual(side)) {
/* 732 */         return false;
/*     */       }
/*     */       
/* 735 */       if (BlockSide.BOTTOM.isEqual(side)) {
/* 736 */         return (slot == 1) && (stack.func_77973_b() == Items.field_151133_ar);
/*     */       }
/*     */       
/* 739 */       return (slot == 3) || (slot == 2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class ContainerWitchesOven
/*     */     extends Container
/*     */   {
/*     */     private BlockWitchesOven.TileEntityWitchesOven furnace;
/*     */     
/*     */     private int lastCookTime;
/*     */     
/*     */     private int lastBurnTime;
/*     */     
/*     */     private int lastItemBurnTime;
/*     */     
/*     */     public ContainerWitchesOven(InventoryPlayer par1InventoryPlayer, BlockWitchesOven.TileEntityWitchesOven par2TileEntityFurnace)
/*     */     {
/* 757 */       this.furnace = par2TileEntityFurnace;
/* 758 */       func_75146_a(new Slot(par2TileEntityFurnace, 0, 56, 17));
/* 759 */       func_75146_a(new Slot(par2TileEntityFurnace, 1, 56, 53));
/* 760 */       func_75146_a(new SlotFurnace(par1InventoryPlayer.field_70458_d, par2TileEntityFurnace, 2, 118, 21));
/* 761 */       func_75146_a(new SlotFurnace(par1InventoryPlayer.field_70458_d, par2TileEntityFurnace, 3, 118, 53));
/* 762 */       func_75146_a(new SlotClayJar(par2TileEntityFurnace, 4, 83, 53));
/*     */       
/*     */ 
/* 765 */       for (int i = 0; i < 3; i++) {
/* 766 */         for (int j = 0; j < 9; j++) {
/* 767 */           func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */         }
/*     */       }
/*     */       
/* 771 */       for (i = 0; i < 9; i++) {
/* 772 */         func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_75132_a(ICrafting par1ICrafting)
/*     */     {
/* 778 */       super.func_75132_a(par1ICrafting);
/* 779 */       par1ICrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/* 780 */       par1ICrafting.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/* 781 */       par1ICrafting.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*     */     }
/*     */     
/*     */     public void func_75142_b()
/*     */     {
/* 786 */       super.func_75142_b();
/*     */       
/* 788 */       for (int i = 0; i < this.field_75149_d.size(); i++) {
/* 789 */         ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */         
/* 791 */         if (this.lastCookTime != this.furnace.furnaceCookTime) {
/* 792 */           icrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*     */         }
/*     */         
/* 795 */         if (this.lastBurnTime != this.furnace.furnaceBurnTime) {
/* 796 */           icrafting.func_71112_a(this, 1, this.furnace.furnaceBurnTime);
/*     */         }
/*     */         
/* 799 */         if (this.lastItemBurnTime != this.furnace.currentItemBurnTime) {
/* 800 */           icrafting.func_71112_a(this, 2, this.furnace.currentItemBurnTime);
/*     */         }
/*     */       }
/*     */       
/* 804 */       this.lastCookTime = this.furnace.furnaceCookTime;
/* 805 */       this.lastBurnTime = this.furnace.furnaceBurnTime;
/* 806 */       this.lastItemBurnTime = this.furnace.currentItemBurnTime;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void func_75137_b(int par1, int par2)
/*     */     {
/* 812 */       if (par1 == 0) {
/* 813 */         this.furnace.furnaceCookTime = par2;
/*     */       }
/*     */       
/* 816 */       if (par1 == 1) {
/* 817 */         this.furnace.furnaceBurnTime = par2;
/*     */       }
/*     */       
/* 820 */       if (par1 == 2) {
/* 821 */         this.furnace.currentItemBurnTime = par2;
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */     {
/* 827 */       return this.furnace.func_70300_a(par1EntityPlayer);
/*     */     }
/*     */     
/*     */     public ItemStack func_82846_b(EntityPlayer player, int slotIndex)
/*     */     {
/* 832 */       ItemStack itemstack = null;
/* 833 */       Slot slot = (Slot)this.field_75151_b.get(slotIndex);
/*     */       
/* 835 */       if ((slot != null) && (slot.func_75216_d())) {
/* 836 */         ItemStack itemstack1 = slot.func_75211_c();
/* 837 */         itemstack = itemstack1.func_77946_l();
/*     */         
/* 839 */         if ((slotIndex == 2) || (slotIndex == 3)) {
/* 840 */           if (!func_75135_a(itemstack1, 5, 41, true)) {
/* 841 */             return null;
/*     */           }
/*     */           
/* 844 */           slot.func_75220_a(itemstack1, itemstack);
/* 845 */         } else if ((slotIndex != 1) && (slotIndex != 0) && (slotIndex != 4)) {
/* 846 */           if (FurnaceRecipes.func_77602_a().func_151395_a(itemstack1) != null) {
/* 847 */             if (!func_75135_a(itemstack1, 0, 1, false)) {
/* 848 */               return null;
/*     */             }
/* 850 */           } else if (TileEntityFurnace.func_145954_b(itemstack1)) {
/* 851 */             if (!func_75135_a(itemstack1, 1, 2, false)) {
/* 852 */               return null;
/*     */             }
/* 854 */           } else if (Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(itemstack1)) {
/* 855 */             if (!func_75135_a(itemstack1, 4, 5, false)) {
/* 856 */               return null;
/*     */             }
/* 858 */           } else if ((slotIndex >= 5) && (slotIndex < 32)) {
/* 859 */             if (!func_75135_a(itemstack1, 32, 41, false)) {
/* 860 */               return null;
/*     */             }
/* 862 */           } else if ((slotIndex >= 32) && (slotIndex < 41) && (!func_75135_a(itemstack1, 5, 32, false))) {
/* 863 */             return null;
/*     */           }
/* 865 */         } else if (!func_75135_a(itemstack1, 5, 41, false)) {
/* 866 */           return null;
/*     */         }
/*     */         
/* 869 */         if (itemstack1.field_77994_a == 0) {
/* 870 */           slot.func_75215_d((ItemStack)null);
/*     */         } else {
/* 872 */           slot.func_75218_e();
/*     */         }
/*     */         
/* 875 */         if (itemstack1.field_77994_a == itemstack.field_77994_a) {
/* 876 */           return null;
/*     */         }
/*     */         
/* 879 */         slot.func_82870_a(player, itemstack1);
/*     */       }
/*     */       
/* 882 */       return itemstack;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchesOven.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */