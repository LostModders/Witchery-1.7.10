/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.crafting.BrazierRecipes;
/*     */ import com.emoniph.witchery.crafting.BrazierRecipes.BrazierRecipe;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.BlockSide;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockBrazier extends BlockBaseContainer
/*     */ {
/*     */   public BlockBrazier()
/*     */   {
/*  43 */     super(Material.field_151573_f, TileEntityBrazier.class);
/*  44 */     func_149711_c(3.5F);
/*  45 */     func_149672_a(field_149777_j);
/*  46 */     func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.95F, 0.8F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  51 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/*  56 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/*  66 */     super.func_149726_b(world, x, y, z);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/*  72 */     TileEntity tile = world.func_147438_o(x, y, z);
/*  73 */     if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
/*  74 */       TileEntityBrazier brazier = (TileEntityBrazier)tile;
/*  75 */       if (brazier.isBurning()) {
/*  76 */         double d0 = x + 0.4F + rand.nextInt(3) * 0.1F;
/*  77 */         double d1 = y + 1.1F + rand.nextInt(2) * 0.1F;
/*  78 */         double d2 = z + 0.4F + rand.nextInt(3) * 0.1F;
/*  79 */         world.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block oldBlockID, int oldBlockMetadata)
/*     */   {
/*  86 */     TileEntity tile = world.func_147438_o(x, y, z);
/*  87 */     if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
/*  88 */       TileEntityBrazier brazier = (TileEntityBrazier)tile;
/*  89 */       if (!brazier.isBurning()) {
/*  90 */         for (int j1 = 0; j1 < brazier.func_70302_i_(); j1++) {
/*  91 */           ItemStack itemstack = brazier.func_70301_a(j1);
/*     */           
/*  93 */           dropItemFromBrokenBlock(world, x, y, z, itemstack);
/*     */           
/*  95 */           world.func_147453_f(x, y, z, oldBlockID);
/*     */         }
/*     */       } else {
/*  98 */         dropItemFromBrokenBlock(world, x, y, z, Witchery.Items.GENERIC.itemAshWood.createStack());
/*     */         
/* 100 */         world.func_147453_f(x, y, z, oldBlockID);
/*     */       }
/*     */     }
/*     */     
/* 104 */     super.func_149749_a(world, x, y, z, oldBlockID, oldBlockMetadata);
/*     */   }
/*     */   
/*     */   private void dropItemFromBrokenBlock(World world, int x, int y, int z, ItemStack itemstack) {
/* 108 */     if (itemstack != null) {
/* 109 */       float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 110 */       float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 111 */       float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*     */       
/* 113 */       while (itemstack.field_77994_a > 0) {
/* 114 */         int k1 = world.field_73012_v.nextInt(21) + 10;
/*     */         
/* 116 */         if (k1 > itemstack.field_77994_a) {
/* 117 */           k1 = itemstack.field_77994_a;
/*     */         }
/*     */         
/* 120 */         itemstack.field_77994_a -= k1;
/* 121 */         EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/*     */         
/*     */ 
/* 124 */         if (itemstack.func_77942_o()) {
/* 125 */           entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
/*     */         }
/*     */         
/* 128 */         float f3 = 0.05F;
/* 129 */         entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 130 */         entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
/* 131 */         entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
/* 132 */         world.func_72838_d(entityitem);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World world, int x, int y, int z, int side)
/*     */   {
/* 144 */     return Container.func_94526_b((IInventory)world.func_147438_o(x, y, z));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/* 150 */     if (world.field_72995_K) {
/* 151 */       return true;
/*     */     }
/* 153 */     TileEntity tile = world.func_147438_o(posX, posY, posZ);
/* 154 */     if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
/* 155 */       TileEntityBrazier brazier = (TileEntityBrazier)tile;
/* 156 */       ItemStack stack = player.func_70694_bm();
/* 157 */       if (stack == null) {
/* 158 */         return false;
/*     */       }
/*     */       
/* 161 */       if ((stack.func_77973_b() == Items.field_151068_bn) && (stack.func_77960_j() == 0)) {
/* 162 */         if (!brazier.isEmpty()) {
/* 163 */           brazier.reset();
/* 164 */           if ((!player.field_71075_bZ.field_75098_d) && (player.field_71071_by != null)) {
/* 165 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, new ItemStack(Items.field_151069_bo));
/* 166 */             if ((player instanceof EntityPlayerMP)) {
/* 167 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */           }
/* 170 */           SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
/*     */         }
/* 172 */       } else if (stack.func_77973_b() == Items.field_151131_as) {
/* 173 */         if (!brazier.isEmpty()) {
/* 174 */           brazier.reset();
/* 175 */           if ((!player.field_71075_bZ.field_75098_d) && (player.field_71071_by != null)) {
/* 176 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, new ItemStack(Items.field_151133_ar));
/* 177 */             if ((player instanceof EntityPlayerMP)) {
/* 178 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */           }
/* 181 */           SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
/*     */         }
/* 183 */       } else { if (stack.func_77973_b() == Items.field_151033_d) {
/* 184 */           if (!brazier.isEmpty()) {
/* 185 */             brazier.begin();
/*     */           }
/* 187 */           return false;
/*     */         }
/* 189 */         boolean added = false;
/* 190 */         for (int i = 0; i < brazier.func_70302_i_() - 1; i++) {
/* 191 */           if (brazier.func_70301_a(i) == null) {
/* 192 */             if ((!player.field_71075_bZ.field_75098_d) && (player.field_71071_by != null)) {
/* 193 */               ItemStack newStack = stack.func_77979_a(1);
/* 194 */               brazier.func_70299_a(i, newStack);
/* 195 */               if (stack.field_77994_a == 0) {
/* 196 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */               }
/*     */             } else {
/* 199 */               brazier.func_70299_a(i, new ItemStack(stack.func_77973_b(), 1, stack.func_77960_j()));
/*     */             }
/* 201 */             added = true;
/* 202 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 208 */     return true;
/*     */   }
/*     */   
/*     */   public static void tryIgnite(World world, int x, int y, int z)
/*     */   {
/* 213 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 214 */     if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
/* 215 */       TileEntityBrazier brazier = (TileEntityBrazier)tile;
/* 216 */       if (!brazier.isEmpty()) {
/* 217 */         brazier.begin();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 225 */     boolean flag = par1World.func_72864_z(par2, par3, par4);
/* 226 */     TileEntity tile = par1World.func_147438_o(par2, par3, par4);
/* 227 */     if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
/* 228 */       TileEntityBrazier brazier = (TileEntityBrazier)tile;
/* 229 */       if ((brazier.previousRedstoneState != flag) && 
/* 230 */         (flag) && (!brazier.isEmpty())) {
/* 231 */         brazier.begin();
/*     */       }
/*     */       
/*     */ 
/* 235 */       brazier.previousRedstoneState = flag;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class TileEntityBrazier
/*     */     extends TileEntityBase
/*     */     implements net.minecraft.inventory.ISidedInventory
/*     */   {
/* 246 */     private ItemStack[] slots = new ItemStack[4];
/* 247 */     private int furnaceCookTime = 0;
/*     */     public boolean previousRedstoneState;
/*     */     private Coord powerSourceCoord;
/*     */     
/*     */     public int func_70302_i_() {
/* 252 */       return this.slots.length;
/*     */     }
/*     */     
/*     */     public void begin() {
/* 256 */       func_70299_a(3, Witchery.Items.GENERIC.itemAshWood.createStack());
/*     */     }
/*     */     
/*     */     public ItemStack func_70301_a(int slot)
/*     */     {
/* 261 */       return this.slots[slot];
/*     */     }
/*     */     
/*     */     public boolean isBurning() {
/* 265 */       for (int i = 0; i < func_70302_i_(); i++) {
/* 266 */         if (func_70301_a(i) == null) {
/* 267 */           return false;
/*     */         }
/*     */       }
/* 270 */       return true;
/*     */     }
/*     */     
/*     */     public ItemStack func_70298_a(int slot, int quantity)
/*     */     {
/* 275 */       if (this.slots[slot] != null)
/*     */       {
/*     */ 
/* 278 */         if (this.slots[slot].field_77994_a <= quantity) {
/* 279 */           ItemStack itemstack = this.slots[slot];
/* 280 */           this.slots[slot] = null;
/* 281 */           return itemstack;
/*     */         }
/* 283 */         ItemStack itemstack = this.slots[slot].func_77979_a(quantity);
/*     */         
/* 285 */         if (this.slots[slot].field_77994_a == 0) {
/* 286 */           this.slots[slot] = null;
/*     */         }
/*     */         
/* 289 */         return itemstack;
/*     */       }
/*     */       
/* 292 */       return null;
/*     */     }
/*     */     
/*     */     public boolean isFull()
/*     */     {
/* 297 */       if (func_70301_a(3) != null) {
/* 298 */         return true;
/*     */       }
/*     */       
/* 301 */       for (int slot = 0; slot < 3; slot++) {
/* 302 */         if (func_70301_a(slot) == null) {
/* 303 */           return false;
/*     */         }
/*     */       }
/* 306 */       return true;
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 310 */       for (int slot = 0; slot < 3; slot++) {
/* 311 */         if (func_70301_a(slot) != null) {
/* 312 */           return false;
/*     */         }
/*     */       }
/* 315 */       return true;
/*     */     }
/*     */     
/*     */     public int getIngredientCount() {
/* 319 */       int count = 0;
/* 320 */       for (int slot = 0; slot < 3; slot++) {
/* 321 */         if (func_70301_a(slot) != null) {
/* 322 */           count++;
/*     */         }
/*     */       }
/* 325 */       return count;
/*     */     }
/*     */     
/*     */     public void reset() {
/* 329 */       for (int slot = 0; slot < func_70302_i_(); slot++) {
/* 330 */         func_70299_a(slot, null);
/*     */       }
/*     */     }
/*     */     
/*     */     public ItemStack func_70304_b(int slot)
/*     */     {
/* 336 */       if (this.slots[slot] != null) {
/* 337 */         ItemStack itemstack = this.slots[slot];
/* 338 */         this.slots[slot] = null;
/* 339 */         return itemstack;
/*     */       }
/* 341 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_70299_a(int slot, ItemStack stack)
/*     */     {
/* 347 */       this.slots[slot] = stack;
/*     */       
/* 349 */       if ((stack != null) && (stack.field_77994_a > func_70297_j_())) {
/* 350 */         stack.field_77994_a = func_70297_j_();
/*     */       }
/* 352 */       func_70296_d();
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 357 */       return func_145838_q().func_149732_F();
/*     */     }
/*     */     
/*     */     public boolean func_145818_k_()
/*     */     {
/* 362 */       return true;
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 367 */       super.func_145839_a(nbtRoot);
/* 368 */       NBTTagList nbtSlotList = nbtRoot.func_150295_c("Items", 10);
/* 369 */       this.slots = new ItemStack[func_70302_i_()];
/*     */       
/* 371 */       for (int i = 0; i < nbtSlotList.func_74745_c(); i++) {
/* 372 */         NBTTagCompound nbtSlot = nbtSlotList.func_150305_b(i);
/* 373 */         byte b0 = nbtSlot.func_74771_c("Slot");
/*     */         
/* 375 */         if ((b0 >= 0) && (b0 < this.slots.length)) {
/* 376 */           this.slots[b0] = ItemStack.func_77949_a(nbtSlot);
/*     */         }
/*     */       }
/*     */       
/* 380 */       this.furnaceCookTime = nbtRoot.func_74765_d("CookTime");
/* 381 */       this.powerLevel = nbtRoot.func_74765_d("PowerLevel");
/* 382 */       this.storage = nbtRoot.func_74763_f("PowerStorage");
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 387 */       super.func_145841_b(nbtRoot);
/* 388 */       nbtRoot.func_74777_a("CookTime", (short)this.furnaceCookTime);
/* 389 */       nbtRoot.func_74777_a("PowerLevel", (short)this.powerLevel);
/* 390 */       nbtRoot.func_74772_a("PowerStorage", this.storage);
/* 391 */       NBTTagList nbtSlotList = new NBTTagList();
/*     */       
/* 393 */       for (int i = 0; i < this.slots.length; i++) {
/* 394 */         if (this.slots[i] != null) {
/* 395 */           NBTTagCompound nbtSlot = new NBTTagCompound();
/* 396 */           nbtSlot.func_74774_a("Slot", (byte)i);
/* 397 */           this.slots[i].func_77955_b(nbtSlot);
/* 398 */           nbtSlotList.func_74742_a(nbtSlot);
/*     */         }
/*     */       }
/*     */       
/* 402 */       nbtRoot.func_74782_a("Items", nbtSlotList);
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 407 */       return 64;
/*     */     }
/*     */     
/*     */ 
/*     */     private IPowerSource getPowerSource()
/*     */     {
/* 413 */       if ((this.powerSourceCoord == null) || (this.ticks % 100L == 0L)) {
/* 414 */         return findNewPowerSource();
/*     */       }
/* 416 */       TileEntity tileEntity = this.powerSourceCoord.getBlockTileEntity(this.field_145850_b);
/* 417 */       if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
/* 418 */         return findNewPowerSource();
/*     */       }
/* 420 */       BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
/* 421 */       if (!altarTileEntity.isValid()) {
/* 422 */         return findNewPowerSource();
/*     */       }
/* 424 */       return altarTileEntity;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     private IPowerSource findNewPowerSource()
/*     */     {
/* 431 */       List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(this.field_145850_b, new Coord(this), 16) : null;
/*     */       
/* 433 */       return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */     }
/*     */     
/*     */ 
/*     */     private static final int POWER_SOURCE_RADIUS = 16;
/*     */     
/*     */     private static final float POWER_PER_TICK = 1.0F;
/*     */     public int powerLevel;
/*     */     public void func_145845_h()
/*     */     {
/* 443 */       super.func_145845_h();
/*     */       
/* 445 */       boolean update = false;
/* 446 */       boolean cooking = this.furnaceCookTime > 0;
/*     */       
/* 448 */       if (!this.field_145850_b.field_72995_K) {
/* 449 */         boolean powered = this.powerLevel > 0;
/* 450 */         BrazierRecipes.BrazierRecipe recipe = BrazierRecipes.instance().getRecipe(new ItemStack[] { this.slots[0], this.slots[1], this.slots[2] });
/*     */         
/* 452 */         if ((recipe != null) && (func_70301_a(3) != null)) {
/* 453 */           IPowerSource powerSource = getPowerSource();
/* 454 */           if ((powerSource != null) && (!powerSource.isLocationEqual(this.powerSourceCoord))) {
/* 455 */             this.powerSourceCoord = powerSource.getLocation();
/*     */           } else {
/* 457 */             this.powerSourceCoord = null;
/*     */           }
/*     */           
/* 460 */           boolean needsPower = recipe.getNeedsPower();
/* 461 */           this.powerLevel = ((needsPower) && (powerSource == null) ? 0 : 1);
/*     */           
/* 463 */           if ((!recipe.getNeedsPower()) || ((powerSource != null) && (powerSource.consumePower(1.0F)))) {
/* 464 */             update = this.furnaceCookTime == 0;
/* 465 */             this.furnaceCookTime += 1;
/* 466 */             if (this.furnaceCookTime == recipe.burnTicks + this.storage * 400L) {
/* 467 */               this.furnaceCookTime = 0;
/* 468 */               recipe.onBurnt(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.ticks, this);
/* 469 */               func_70299_a(0, null);
/* 470 */               func_70299_a(1, null);
/* 471 */               func_70299_a(2, null);
/* 472 */               update = true;
/*     */             } else {
/* 474 */               this.storage += recipe.onBurning(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.ticks, this);
/* 475 */               if (this.storage == Long.MAX_VALUE) {
/* 476 */                 this.storage = 0L;
/*     */               }
/*     */             }
/* 479 */             if (powered != this.powerLevel > 0) {
/* 480 */               update = true;
/*     */             }
/*     */           }
/*     */           else {
/* 484 */             this.powerLevel = 0;
/* 485 */             if (powered != this.powerLevel > 0) {
/* 486 */               update = true;
/*     */             }
/*     */           }
/*     */         } else {
/* 490 */           if (func_70301_a(3) != null) {
/* 491 */             reset();
/* 492 */             ParticleEffect.SMOKE.send(SoundEffect.RANDOM_FIZZ, this.field_145850_b, 0.5D + this.field_145851_c, 1.0D + this.field_145848_d, 0.5D + this.field_145849_e, 0.5D, 0.5D, 8);
/*     */           }
/* 494 */           if (this.ticks % 40L == 0L) {
/* 495 */             IPowerSource powerSource = getPowerSource();
/* 496 */             if ((powerSource != null) && (!powerSource.isLocationEqual(this.powerSourceCoord))) {
/* 497 */               this.powerSourceCoord = powerSource.getLocation();
/*     */             }
/*     */             
/* 500 */             this.powerLevel = (powerSource == null ? 0 : 1);
/*     */           }
/*     */           
/* 503 */           if (this.furnaceCookTime <= 0) {} update = powered != this.powerLevel > 0;
/* 504 */           this.furnaceCookTime = 0;
/*     */         }
/*     */       }
/*     */       
/* 508 */       if (update) {
/* 509 */         func_70296_d();
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_70296_d()
/*     */     {
/* 515 */       super.func_70296_d();
/* 516 */       if (!this.field_145850_b.field_72995_K) {
/* 517 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 523 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 524 */       func_145841_b(nbtTag);
/* 525 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 530 */       super.onDataPacket(net, packet);
/* 531 */       func_145839_a(packet.func_148857_g());
/* 532 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */     {
/* 537 */       return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 551 */       if ((slot == 3) || (itemstack == null))
/* 552 */         return false;
/* 553 */       if (itemstack.field_77994_a != 1)
/* 554 */         return false;
/* 555 */       if ((slot < 0) || (slot >= this.slots.length))
/* 556 */         return false;
/* 557 */       if (this.slots[slot] != null) {
/* 558 */         return false;
/*     */       }
/* 560 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */     private long storage;
/*     */     private static final int SLOT_1 = 0;
/*     */     private static final int SLOT_2 = 1;
/*     */     private static final int SLOT_3 = 2;
/*     */     private static final int SLOT_RESULT = 3;
/* 569 */     private static final int[] slots_top = { 0, 1, 2 };
/* 570 */     private static final int[] slots_bottom = { 0, 1, 2 };
/* 571 */     private static final int[] slots_sides = { 0, 1, 2 };
/*     */     
/*     */     public int[] func_94128_d(int side)
/*     */     {
/* 575 */       return BlockSide.TOP.isEqual(side) ? slots_top : BlockSide.BOTTOM.isEqual(side) ? slots_bottom : slots_sides;
/*     */     }
/*     */     
/*     */     public boolean func_102007_a(int slot, ItemStack itemstack, int par3)
/*     */     {
/* 580 */       return func_94041_b(slot, itemstack);
/*     */     }
/*     */     
/*     */     public boolean func_102008_b(int slot, ItemStack stack, int side)
/*     */     {
/* 585 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBrazier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */