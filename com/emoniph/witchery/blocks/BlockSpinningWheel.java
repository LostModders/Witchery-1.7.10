/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.crafting.SpinningRecipes;
/*     */ import com.emoniph.witchery.crafting.SpinningRecipes.SpinningRecipe;
/*     */ import com.emoniph.witchery.util.BlockSide;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.inventory.SlotFurnace;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSpinningWheel
/*     */   extends BlockBaseContainer
/*     */ {
/*     */   public BlockSpinningWheel()
/*     */   {
/*  47 */     super(Material.field_151575_d, TileEntitySpinningWheel.class);
/*  48 */     func_149711_c(3.5F);
/*  49 */     func_149672_a(field_149766_f);
/*  50 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/*  70 */     super.func_149726_b(world, x, y, z);
/*  71 */     BlockUtil.setBlockDefaultDirection(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  76 */     if (world.field_72995_K) {
/*  77 */       return true;
/*     */     }
/*  79 */     TileEntity tile = world.func_147438_o(x, y, z);
/*  80 */     if ((tile != null) && ((tile instanceof TileEntitySpinningWheel))) {
/*  81 */       TileEntitySpinningWheel spinningWheel = (TileEntitySpinningWheel)tile;
/*  82 */       player.openGui(Witchery.instance, 4, world, x, y, z);
/*     */     }
/*     */     
/*  85 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */ 
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
/*     */   {
/*  96 */     int l = MathHelper.func_76128_c(entity.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  97 */     switch (l) {
/*     */     case 0: 
/*     */     default: 
/* 100 */       world.func_72921_c(x, y, z, 2, 2);
/* 101 */       break;
/*     */     case 1: 
/* 103 */       world.func_72921_c(x, y, z, 5, 2);
/* 104 */       break;
/*     */     case 2: 
/* 106 */       world.func_72921_c(x, y, z, 3, 2);
/* 107 */       break;
/*     */     case 3: 
/* 109 */       world.func_72921_c(x, y, z, 4, 2);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block oldBlockID, int oldBlockMetadata)
/*     */   {
/* 116 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 117 */     if ((tile != null) && ((tile instanceof TileEntitySpinningWheel))) {
/* 118 */       TileEntitySpinningWheel tileentityfurnace = (TileEntitySpinningWheel)tile;
/*     */       
/* 120 */       if (tileentityfurnace != null) {
/* 121 */         for (int j1 = 0; j1 < tileentityfurnace.func_70302_i_(); j1++) {
/* 122 */           ItemStack itemstack = tileentityfurnace.func_70301_a(j1);
/*     */           
/* 124 */           if (itemstack != null) {
/* 125 */             float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 126 */             float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 127 */             float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*     */             
/* 129 */             while (itemstack.field_77994_a > 0) {
/* 130 */               int k1 = world.field_73012_v.nextInt(21) + 10;
/*     */               
/* 132 */               if (k1 > itemstack.field_77994_a) {
/* 133 */                 k1 = itemstack.field_77994_a;
/*     */               }
/*     */               
/* 136 */               itemstack.field_77994_a -= k1;
/* 137 */               EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/*     */               
/*     */ 
/* 140 */               if (itemstack.func_77942_o()) {
/* 141 */                 entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */               }
/*     */               
/* 144 */               float f3 = 0.05F;
/* 145 */               entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 146 */               entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
/* 147 */               entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 148 */               world.func_72838_d(entityitem);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 153 */         world.func_147453_f(x, y, z, oldBlockID);
/*     */       }
/*     */     }
/*     */     
/* 157 */     super.func_149749_a(world, x, y, z, oldBlockID, oldBlockMetadata);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 162 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World world, int x, int y, int z, int side)
/*     */   {
/* 167 */     return Container.func_94526_b((IInventory)world.func_147438_o(x, y, z));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class TileEntitySpinningWheel
/*     */     extends TileEntityBase
/*     */     implements ISidedInventory
/*     */   {
/* 181 */     private ItemStack[] slots = new ItemStack[5];
/*     */     
/*     */ 
/* 184 */     public int furnaceCookTime = 0;
/*     */     
/*     */     public int func_70302_i_()
/*     */     {
/* 188 */       return this.slots.length;
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int slot)
/*     */     {
/* 193 */       return this.slots[slot];
/*     */     }
/*     */     
/*     */     public ItemStack func_70298_a(int slot, int quantity)
/*     */     {
/* 198 */       if (this.slots[slot] != null)
/*     */       {
/*     */ 
/* 201 */         if (this.slots[slot].field_77994_a <= quantity) {
/* 202 */           ItemStack itemstack = this.slots[slot];
/* 203 */           this.slots[slot] = null;
/* 204 */           return itemstack;
/*     */         }
/* 206 */         ItemStack itemstack = this.slots[slot].func_77979_a(quantity);
/*     */         
/* 208 */         if (this.slots[slot].field_77994_a == 0) {
/* 209 */           this.slots[slot] = null;
/*     */         }
/*     */         
/* 212 */         return itemstack;
/*     */       }
/*     */       
/* 215 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public ItemStack func_70304_b(int slot)
/*     */     {
/* 221 */       if (this.slots[slot] != null) {
/* 222 */         ItemStack itemstack = this.slots[slot];
/* 223 */         this.slots[slot] = null;
/* 224 */         return itemstack;
/*     */       }
/* 226 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_70299_a(int slot, ItemStack stack)
/*     */     {
/* 232 */       this.slots[slot] = stack;
/*     */       
/* 234 */       if ((stack != null) && (stack.field_77994_a > func_70297_j_())) {
/* 235 */         stack.field_77994_a = func_70297_j_();
/*     */       }
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 241 */       return func_145838_q().func_149732_F();
/*     */     }
/*     */     
/*     */     public boolean func_145818_k_()
/*     */     {
/* 246 */       return true;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 251 */       super.func_145839_a(nbtRoot);
/* 252 */       NBTTagList nbtSlotList = nbtRoot.func_150295_c("Items", 10);
/* 253 */       this.slots = new ItemStack[func_70302_i_()];
/*     */       
/* 255 */       for (int i = 0; i < nbtSlotList.func_74745_c(); i++) {
/* 256 */         NBTTagCompound nbtSlot = nbtSlotList.func_150305_b(i);
/* 257 */         byte b0 = nbtSlot.func_74771_c("Slot");
/*     */         
/* 259 */         if ((b0 >= 0) && (b0 < this.slots.length)) {
/* 260 */           this.slots[b0] = ItemStack.func_77949_a(nbtSlot);
/*     */         }
/*     */       }
/*     */       
/* 264 */       this.furnaceCookTime = nbtRoot.func_74765_d("CookTime");
/* 265 */       this.powerLevel = nbtRoot.func_74765_d("PowerLevel");
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 270 */       super.func_145841_b(nbtRoot);
/* 271 */       nbtRoot.func_74777_a("CookTime", (short)this.furnaceCookTime);
/* 272 */       nbtRoot.func_74777_a("PowerLevel", (short)this.powerLevel);
/* 273 */       NBTTagList nbtSlotList = new NBTTagList();
/*     */       
/* 275 */       for (int i = 0; i < this.slots.length; i++) {
/* 276 */         if (this.slots[i] != null) {
/* 277 */           NBTTagCompound nbtSlot = new NBTTagCompound();
/* 278 */           nbtSlot.func_74774_a("Slot", (byte)i);
/* 279 */           this.slots[i].func_77955_b(nbtSlot);
/* 280 */           nbtSlotList.func_74742_a(nbtSlot);
/*     */         }
/*     */       }
/*     */       
/* 284 */       nbtRoot.func_74782_a("Items", nbtSlotList);
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 289 */       return 64;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public int getCookProgressScaled(int par1) {
/* 294 */       return this.furnaceCookTime * par1 / getTotalCookTime();
/*     */     }
/*     */     
/* 297 */     private final int TICKS_PER_SPIN = 20;
/* 298 */     private final int SPINS_PER_STEP = 3;
/* 299 */     private final int STEPS_TO_COMPLETE = 5;
/*     */     Coord powerSourceCoord;
/*     */     
/* 302 */     public int getTotalCookTime() { int time = 300;
/* 303 */       return 300;
/*     */     }
/*     */     
/*     */     public int getCookTime() {
/* 307 */       return this.furnaceCookTime;
/*     */     }
/*     */     
/*     */ 
/*     */     IPowerSource getPowerSource()
/*     */     {
/* 313 */       if ((this.powerSourceCoord == null) || (this.ticks % 100L == 0L)) {
/* 314 */         return findNewPowerSource();
/*     */       }
/* 316 */       TileEntity tileEntity = this.powerSourceCoord.getBlockTileEntity(this.field_145850_b);
/* 317 */       if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
/* 318 */         return findNewPowerSource();
/*     */       }
/* 320 */       BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
/* 321 */       if (!altarTileEntity.isValid()) {
/* 322 */         return findNewPowerSource();
/*     */       }
/* 324 */       return altarTileEntity;
/*     */     }
/*     */     
/*     */ 
/*     */     static final int POWER_SOURCE_RADIUS = 16;
/*     */     private IPowerSource findNewPowerSource()
/*     */     {
/* 331 */       List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(this.field_145850_b, new Coord(this), 16) : null;
/*     */       
/* 333 */       return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */     }
/*     */     
/*     */ 
/*     */     static final float POWER_PER_TICK = 0.6F;
/*     */     
/*     */     public int powerLevel;
/*     */     public void func_145845_h()
/*     */     {
/* 342 */       super.func_145845_h();
/*     */       
/* 344 */       boolean update = false;
/* 345 */       boolean cooking = this.furnaceCookTime > 0;
/*     */       
/* 347 */       if (!this.field_145850_b.field_72995_K) {
/* 348 */         boolean powered = this.powerLevel > 0;
/* 349 */         if (canSmelt()) {
/* 350 */           IPowerSource powerSource = getPowerSource();
/* 351 */           if ((powerSource != null) && (!powerSource.isLocationEqual(this.powerSourceCoord))) {
/* 352 */             this.powerSourceCoord = powerSource.getLocation();
/*     */           } else {
/* 354 */             this.powerSourceCoord = null;
/*     */           }
/*     */           
/* 357 */           this.powerLevel = (powerSource == null ? 0 : 1);
/*     */           
/* 359 */           if ((powerSource != null) && (powerSource.consumePower(0.6F))) {
/* 360 */             update = this.furnaceCookTime == 0;
/* 361 */             this.furnaceCookTime += 1;
/* 362 */             if (this.furnaceCookTime == getTotalCookTime()) {
/* 363 */               this.furnaceCookTime = 0;
/* 364 */               smeltItem();
/* 365 */               update = true;
/*     */             }
/* 367 */             if (powered != this.powerLevel > 0) {
/* 368 */               update = true;
/*     */             }
/*     */           }
/*     */           else {
/* 372 */             this.powerLevel = 0;
/* 373 */             if (powered != this.powerLevel > 0) {
/* 374 */               update = true;
/*     */             }
/*     */           }
/*     */         } else {
/* 378 */           if (this.ticks % 40L == 0L) {
/* 379 */             IPowerSource powerSource = getPowerSource();
/* 380 */             if ((powerSource != null) && (!powerSource.isLocationEqual(this.powerSourceCoord))) {
/* 381 */               this.powerSourceCoord = powerSource.getLocation();
/*     */             }
/*     */             
/* 384 */             this.powerLevel = (powerSource == null ? 0 : 1);
/*     */           }
/*     */           
/* 387 */           if (this.furnaceCookTime <= 0) {} update = powered != this.powerLevel > 0;
/* 388 */           this.furnaceCookTime = 0;
/*     */         }
/*     */       }
/*     */       
/* 392 */       if (update) {
/* 393 */         func_70296_d();
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_70296_d()
/*     */     {
/* 399 */       super.func_70296_d();
/* 400 */       if (!this.field_145850_b.field_72995_K) {
/* 401 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 407 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 408 */       func_145841_b(nbtTag);
/* 409 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 414 */       super.onDataPacket(net, packet);
/* 415 */       func_145839_a(packet.func_148857_g());
/* 416 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     private boolean canSmelt() {
/* 420 */       if (this.slots[0] == null) {
/* 421 */         return false;
/*     */       }
/* 423 */       SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().getRecipe(this.slots[0], new ItemStack[] { this.slots[1], this.slots[3], this.slots[4] });
/*     */       
/*     */ 
/* 426 */       if (recipe == null) {
/* 427 */         return false;
/*     */       }
/* 429 */       if (this.slots[2] == null) {
/* 430 */         return true;
/*     */       }
/* 432 */       ItemStack itemstack = recipe.getResult();
/*     */       
/* 434 */       if (!this.slots[2].func_77969_a(itemstack)) {
/* 435 */         return false;
/*     */       }
/* 437 */       int result = this.slots[2].field_77994_a + itemstack.field_77994_a;
/* 438 */       return (result <= func_70297_j_()) && (result <= itemstack.func_77976_d());
/*     */     }
/*     */     
/*     */     public void smeltItem()
/*     */     {
/* 443 */       if (canSmelt()) {
/* 444 */         SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().getRecipe(this.slots[0], new ItemStack[] { this.slots[1], this.slots[3], this.slots[4] });
/*     */         
/*     */ 
/* 447 */         ItemStack itemstack = recipe.getResult();
/*     */         
/* 449 */         if (this.slots[2] == null) {
/* 450 */           this.slots[2] = itemstack.func_77946_l();
/* 451 */         } else if (this.slots[2].func_77969_a(itemstack)) {
/* 452 */           this.slots[2].field_77994_a += itemstack.field_77994_a;
/*     */         }
/*     */         
/* 455 */         this.slots[0].field_77994_a -= recipe.fibre.field_77994_a;
/*     */         
/* 457 */         if (this.slots[0].field_77994_a <= 0) {
/* 458 */           this.slots[0] = null;
/*     */         }
/*     */         
/* 461 */         ArrayList<ItemStack> available = recipe.getMutableModifiersList();
/*     */         
/* 463 */         updateIfContained(available, 1);
/* 464 */         updateIfContained(available, 3);
/* 465 */         updateIfContained(available, 4);
/*     */       }
/*     */     }
/*     */     
/*     */     private void updateIfContained(ArrayList<ItemStack> available, int slot) {
/* 470 */       if (this.slots[slot] != null) {
/* 471 */         for (int i = 0; i < available.size(); i++) {
/* 472 */           if (((ItemStack)available.get(i)).func_77969_a(this.slots[slot])) {
/* 473 */             this.slots[slot].field_77994_a -= 1;
/*     */             
/* 475 */             if (this.slots[slot].field_77994_a <= 0) {
/* 476 */               this.slots[slot] = null;
/*     */             }
/*     */             
/* 479 */             available.remove(i);
/* 480 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */     {
/* 488 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 502 */       if (slot == 2) {
/* 503 */         return false;
/*     */       }
/* 505 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */     private static final int SLOT_TO_SPIN = 0;
/*     */     
/*     */     private static final int SLOT_SPUN = 2;
/*     */     private static final int SLOT_FUEL = 1;
/*     */     private static final int SLOT_BY_PRODUCT = 3;
/*     */     private static final int SLOT_JARS = 4;
/* 515 */     private static final int[] slots_top = { 0 };
/* 516 */     private static final int[] slots_bottom = { 4, 1, 3 };
/* 517 */     private static final int[] slots_sides = { 3, 2, 4, 1 };
/*     */     
/*     */     public int[] func_94128_d(int side)
/*     */     {
/* 521 */       return BlockSide.TOP.isEqual(side) ? slots_top : BlockSide.BOTTOM.isEqual(side) ? slots_bottom : slots_sides;
/*     */     }
/*     */     
/*     */     public boolean func_102007_a(int slot, ItemStack itemstack, int par3)
/*     */     {
/* 526 */       return func_94041_b(slot, itemstack);
/*     */     }
/*     */     
/*     */     public boolean func_102008_b(int slot, ItemStack stack, int side)
/*     */     {
/* 531 */       if (BlockSide.TOP.isEqual(side)) {
/* 532 */         return false;
/*     */       }
/*     */       
/* 535 */       if (BlockSide.BOTTOM.isEqual(side)) {
/* 536 */         return false;
/*     */       }
/*     */       
/* 539 */       return slot == 2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class ContainerSpinningWheel
/*     */     extends Container
/*     */   {
/*     */     private BlockSpinningWheel.TileEntitySpinningWheel furnace;
/*     */     
/*     */     private int lastCookTime;
/*     */     
/*     */     private int lastPowerLevel;
/*     */     
/*     */     public ContainerSpinningWheel(InventoryPlayer par1InventoryPlayer, BlockSpinningWheel.TileEntitySpinningWheel par2TileEntityFurnace)
/*     */     {
/* 555 */       this.furnace = par2TileEntityFurnace;
/* 556 */       func_75146_a(new Slot(par2TileEntityFurnace, 0, 56, 20));
/* 557 */       func_75146_a(new Slot(par2TileEntityFurnace, 1, 56, 53));
/* 558 */       func_75146_a(new SlotFurnace(par1InventoryPlayer.field_70458_d, par2TileEntityFurnace, 2, 118, 21));
/* 559 */       func_75146_a(new Slot(par2TileEntityFurnace, 3, 74, 53));
/* 560 */       func_75146_a(new Slot(par2TileEntityFurnace, 4, 92, 53));
/*     */       
/*     */ 
/* 563 */       for (int i = 0; i < 3; i++) {
/* 564 */         for (int j = 0; j < 9; j++) {
/* 565 */           func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */         }
/*     */       }
/*     */       
/* 569 */       for (i = 0; i < 9; i++) {
/* 570 */         func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_75132_a(ICrafting par1ICrafting)
/*     */     {
/* 576 */       super.func_75132_a(par1ICrafting);
/* 577 */       par1ICrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/* 578 */       par1ICrafting.func_71112_a(this, 1, this.furnace.powerLevel);
/*     */     }
/*     */     
/*     */     public void func_75142_b()
/*     */     {
/* 583 */       super.func_75142_b();
/*     */       
/* 585 */       for (int i = 0; i < this.field_75149_d.size(); i++) {
/* 586 */         ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */         
/* 588 */         if (this.lastCookTime != this.furnace.furnaceCookTime) {
/* 589 */           icrafting.func_71112_a(this, 0, this.furnace.furnaceCookTime);
/*     */         }
/*     */         
/* 592 */         if (this.lastPowerLevel != this.furnace.powerLevel) {
/* 593 */           icrafting.func_71112_a(this, 1, this.furnace.powerLevel);
/*     */         }
/*     */       }
/*     */       
/* 597 */       this.lastCookTime = this.furnace.furnaceCookTime;
/* 598 */       this.lastPowerLevel = this.furnace.powerLevel;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void func_75137_b(int par1, int par2)
/*     */     {
/* 604 */       if (par1 == 0) {
/* 605 */         this.furnace.furnaceCookTime = par2;
/*     */       }
/*     */       
/* 608 */       if (par1 == 1) {
/* 609 */         this.furnace.powerLevel = par2;
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */     {
/* 615 */       return this.furnace.func_70300_a(par1EntityPlayer);
/*     */     }
/*     */     
/*     */     public ItemStack func_82846_b(EntityPlayer player, int slotIndex)
/*     */     {
/* 620 */       ItemStack itemstack = null;
/* 621 */       Slot slot = (Slot)this.field_75151_b.get(slotIndex);
/*     */       
/* 623 */       if ((slot != null) && (slot.func_75216_d())) {
/* 624 */         ItemStack itemstack1 = slot.func_75211_c();
/* 625 */         itemstack = itemstack1.func_77946_l();
/*     */         
/* 627 */         if (slotIndex == 2) {
/* 628 */           if (!func_75135_a(itemstack1, 5, 41, true)) {
/* 629 */             return null;
/*     */           }
/*     */           
/* 632 */           slot.func_75220_a(itemstack1, itemstack);
/* 633 */         } else if ((slotIndex != 1) && (slotIndex != 0) && (slotIndex != 4) && (slotIndex != 3))
/*     */         {
/* 635 */           if ((SpinningRecipes.instance().findRecipeUsingFibre(itemstack1) != null) && ((this.furnace.func_70301_a(0) == null) || (this.furnace.func_70301_a(0).func_77969_a(itemstack1))))
/*     */           {
/* 637 */             if (!func_75135_a(itemstack1, 0, 1, false)) {
/* 638 */               return null;
/*     */             }
/* 640 */           } else if (SpinningRecipes.instance().findRecipeUsing(itemstack1) != null) {
/* 641 */             if ((!func_75135_a(itemstack1, 1, 2, false)) && 
/* 642 */               (!func_75135_a(itemstack1, 3, 4, false)) && 
/* 643 */               (!func_75135_a(itemstack1, 4, 5, false))) {
/* 644 */               return null;
/*     */             }
/*     */             
/*     */           }
/* 648 */           else if ((slotIndex >= 5) && (slotIndex < 32)) {
/* 649 */             if (!func_75135_a(itemstack1, 32, 41, false)) {
/* 650 */               return null;
/*     */             }
/* 652 */           } else if ((slotIndex >= 32) && (slotIndex < 41) && (!func_75135_a(itemstack1, 5, 32, false))) {
/* 653 */             return null;
/*     */           }
/* 655 */         } else if (!func_75135_a(itemstack1, 5, 41, false)) {
/* 656 */           return null;
/*     */         }
/*     */         
/* 659 */         if (itemstack1.field_77994_a == 0) {
/* 660 */           slot.func_75215_d((ItemStack)null);
/*     */         } else {
/* 662 */           slot.func_75218_e();
/*     */         }
/*     */         
/* 665 */         if (itemstack1.field_77994_a == itemstack.field_77994_a) {
/* 666 */           return null;
/*     */         }
/*     */         
/* 669 */         slot.func_82870_a(player, itemstack1);
/*     */       }
/*     */       
/* 672 */       return itemstack;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockSpinningWheel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */