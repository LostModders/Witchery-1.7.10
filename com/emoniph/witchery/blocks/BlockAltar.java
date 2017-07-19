/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.particle.NaturePowerFX;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockCrops;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ public class BlockAltar extends BlockBaseContainer
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon blockIconTop;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon blockIconJoined;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon blockIconTopJoined;
/*     */   private static final int ELEMENTS_IN_COMPLETE_ALTAR = 6;
/*     */   
/*     */   public BlockAltar()
/*     */   {
/*  49 */     super(net.minecraft.block.material.Material.field_151576_e, TileEntityAltar.class);
/*  50 */     func_149711_c(2.0F);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/*  55 */     return new TileEntityAltar();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int metadata)
/*     */   {
/*  61 */     switch (com.emoniph.witchery.util.BlockSide.fromInteger(side)) {
/*     */     case TOP: 
/*  63 */       return metadata == 0 ? this.blockIconTop : this.blockIconTopJoined;
/*     */     case BOTTOM: 
/*  65 */       return this.blockIconTop;
/*     */     }
/*  67 */     return metadata == 0 ? this.field_149761_L : this.blockIconJoined;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/*  74 */     int i = Minecraft.func_71410_x().field_71474_y.field_74362_aa;
/*     */     
/*  76 */     if ((i == 2) || ((i == 1) && (rand.nextInt(3) == 0)))
/*     */     {
/*  78 */       return;
/*     */     }
/*     */     
/*  81 */     if (world.func_72805_g(x, y, z) == 1)
/*     */     {
/*  83 */       int RADIUS = 16;
/*  84 */       int VERT = 4;
/*  85 */       int plantX = x - 16 + rand.nextInt(32) + 1;
/*  86 */       int plantY = y - 4 + rand.nextInt(8) + 1;
/*  87 */       int plantZ = z - 16 + rand.nextInt(32) + 1;
/*  88 */       Block block = world.func_147439_a(plantX, plantY, plantZ);
/*     */       
/*  90 */       if ((block != null) && (
/*  91 */         ((block instanceof BlockFlower)) || ((block instanceof BlockLeaves)) || ((block instanceof BlockCrops)) || ((block instanceof net.minecraftforge.common.IPlantable)))) {
/*  92 */         int dir_x = x - plantX;
/*  93 */         int dir_y = y - plantY;
/*  94 */         int dir_z = z - plantZ;
/*  95 */         double distance = Math.sqrt(dir_x * dir_x + dir_y * dir_y + dir_z * dir_z);
/*  96 */         double speed = 0.25D;
/*  97 */         double factor = speed / distance;
/*  98 */         double vel_x = dir_x * factor;
/*  99 */         double vel_y = dir_y * factor;
/* 100 */         double vel_z = dir_z * factor;
/*     */         
/* 102 */         NaturePowerFX sparkle = new NaturePowerFX(world, 0.5D + plantX, 0.5D + plantY, 0.5D + plantZ);
/* 103 */         sparkle.setMaxAge((int)(distance / speed)).setGravity(0.0F).setScale(1.0F).func_70016_h(vel_x, vel_y, vel_z);
/* 104 */         sparkle.setCanMove(true);
/* 105 */         sparkle.func_70538_b(0.2F, 0.8F, 0.0F);
/* 106 */         Minecraft.func_71410_x().field_71452_i.func_78873_a(sparkle);
/*     */       }
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/* 122 */     this.field_149761_L = iconRegister.func_94245_a(func_149641_N());
/* 123 */     this.blockIconTop = iconRegister.func_94245_a(func_149641_N() + "_top");
/* 124 */     this.blockIconJoined = iconRegister.func_94245_a(func_149641_N() + "_joined");
/* 125 */     this.blockIconTopJoined = iconRegister.func_94245_a(func_149641_N() + "_joined_top");
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/* 130 */     TileEntityAltar tileEntity = (TileEntityAltar)world.func_147438_o(x, y, z);
/* 131 */     if (tileEntity.isValidAndUpdate()) {
/* 132 */       player.openGui(Witchery.instance, 0, world, x, y, z);
/* 133 */       return true;
/*     */     }
/* 135 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/* 141 */     super.func_149689_a(world, x, y, z, par5EntityLivingBase, par6ItemStack);
/*     */     
/* 143 */     updateMultiblock(world, x, y, z, null);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block, int par6)
/*     */   {
/* 148 */     updateMultiblock(world, x, y, z, new Coord(x, y, z));
/* 149 */     super.func_149749_a(world, x, y, z, block, par6);
/*     */   }
/*     */   
/*     */   public void func_149723_a(World world, int posX, int posY, int posZ, Explosion explosion)
/*     */   {
/* 154 */     TileEntityAltar tileEntity = (TileEntityAltar)world.func_147438_o(posX, posY, posZ);
/* 155 */     updateMultiblock(world, posX, posY, posZ, null);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int posX, int posY, int posZ, Block block)
/*     */   {
/* 160 */     TileEntity tileEntity = world.func_147438_o(posX, posY, posZ);
/* 161 */     if ((tileEntity != null) && ((tileEntity instanceof TileEntityAltar)) && (!world.field_72995_K)) {
/* 162 */       TileEntityAltar tileEntityAltar = (TileEntityAltar)tileEntity;
/* 163 */       tileEntityAltar.updateCoreArtefacts();
/*     */     }
/* 165 */     super.func_149695_a(world, posX, posY, posZ, block);
/*     */   }
/*     */   
/*     */ 
/*     */   private void updateMultiblock(World world, int x, int y, int z, Coord exclude)
/*     */   {
/* 171 */     if (!world.field_72995_K) {
/* 172 */       ArrayList<Coord> visited = new ArrayList();
/* 173 */       ArrayList<Coord> toVisit = new ArrayList();
/* 174 */       toVisit.add(new Coord(x, y, z));
/* 175 */       boolean valid = true;
/* 176 */       while (toVisit.size() > 0) {
/* 177 */         Coord coord = (Coord)toVisit.get(0);
/* 178 */         toVisit.remove(0);
/* 179 */         int neighbours = 0;
/* 180 */         for (Coord newCoord : new Coord[] { coord.north(), coord.south(), coord.east(), coord.west() }) {
/* 181 */           if (newCoord.getBlock(world) == this) {
/* 182 */             if ((!visited.contains(newCoord)) && (!toVisit.contains(newCoord))) {
/* 183 */               toVisit.add(newCoord);
/*     */             }
/* 185 */             neighbours++;
/*     */           }
/*     */         }
/*     */         
/* 189 */         if (!coord.equals(exclude)) {
/* 190 */           if ((neighbours < 2) || (neighbours > 3)) {
/* 191 */             valid = false;
/*     */           }
/*     */           
/* 194 */           visited.add(coord);
/*     */         }
/*     */       }
/*     */       
/* 198 */       Coord newCore = (valid) && (visited.size() == 6) ? (Coord)visited.get(0) : null;
/*     */       
/*     */ 
/* 201 */       for (Coord coord : visited) {
/* 202 */         TileEntity te = coord.getBlockTileEntity(world);
/* 203 */         if ((te != null) && ((te instanceof TileEntityAltar))) {
/* 204 */           TileEntityAltar tile = (TileEntityAltar)te;
/* 205 */           tile.setCore(newCore);
/*     */         }
/*     */       }
/*     */       
/* 209 */       if (exclude != null) {
/* 210 */         TileEntity te = exclude.getBlockTileEntity(world);
/* 211 */         if ((te != null) && ((te instanceof TileEntityAltar))) {
/* 212 */           TileEntityAltar tile = (TileEntityAltar)te;
/* 213 */           tile.setCore(null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityAltar extends TileEntityBase implements com.emoniph.witchery.common.IPowerSource
/*     */   {
/*     */     private Coord core;
/*     */     private float power;
/*     */     private float maxPower;
/*     */     private int powerScale;
/*     */     private int rechargeScale;
/*     */     private int enhancementLevel;
/* 227 */     private int rangeScale = 1;
/*     */     
/*     */     public boolean isPowerInvalid()
/*     */     {
/* 231 */       return func_145837_r();
/*     */     }
/*     */     
/*     */     protected void initiate()
/*     */     {
/* 236 */       super.initiate();
/* 237 */       if ((!this.field_145850_b.field_72995_K) && (isCore())) {
/* 238 */         if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == Witchery.Blocks.ALTAR) {
/* 239 */           Log.instance().debug("Initiating altar tile at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
/* 240 */           PowerSources.instance().registerPowerSource(this);
/*     */         } else {
/* 242 */           Log.instance().warning("Altar tile entity exists without a corresponding block at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145843_s()
/*     */     {
/* 249 */       super.func_145843_s();
/* 250 */       if (!this.field_145850_b.field_72995_K) {
/* 251 */         if (isCore()) {
/* 252 */           Log.instance().debug("Invalidating void bramble tile at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
/*     */         }
/* 254 */         PowerSources.instance().removePowerSource(this);
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 260 */       super.func_145845_h();
/*     */       
/* 262 */       if (!this.field_145850_b.field_72995_K) {
/* 263 */         float maxPowerScaled = this.maxPower * this.powerScale;
/* 264 */         if (isCore()) {
/* 265 */           if (this.power < maxPowerScaled) {
/* 266 */             float basePowerPerUpdate = 10.0F;
/* 267 */             if (this.ticks % 20L == 0L) {
/* 268 */               this.power = ((int)Math.min(this.power + 10.0F * this.rechargeScale, maxPowerScaled));
/*     */               
/* 270 */               this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */             }
/* 272 */           } else if ((this.power > maxPowerScaled) && 
/* 273 */             (this.ticks % 20L == 0L)) {
/* 274 */             this.power = maxPowerScaled;
/* 275 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 282 */     long lastPowerUpdate = 0L;
/*     */     private static final int SCAN_DISTANCE = 14;
/*     */     
/*     */     public float getRange() {
/* 286 */       return 16 * this.rangeScale;
/*     */     }
/*     */     
/*     */     public int getEnhancementLevel()
/*     */     {
/* 291 */       return this.enhancementLevel;
/*     */     }
/*     */     
/*     */     public boolean isValidAndUpdate() {
/* 295 */       if ((isValid()) && (!this.field_145850_b.field_72995_K)) {
/* 296 */         TileEntity tile = this.core.getBlockTileEntity(this.field_145850_b);
/* 297 */         if ((tile != null) && ((tile instanceof TileEntityAltar))) {
/* 298 */           TileEntityAltar tileEntity = (TileEntityAltar)tile;
/* 299 */           tileEntity.updateArtefacts();
/* 300 */           tileEntity.updatePower(true);
/* 301 */           return true;
/*     */         }
/* 303 */         return false;
/*     */       }
/* 305 */       if (isValid()) {
/* 306 */         return true;
/*     */       }
/* 308 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 314 */       if (this.core != null) {
/* 315 */         this.core.setNBT(nbtTag, "Core");
/*     */       }
/* 317 */       if (isCore()) {
/* 318 */         nbtTag.func_74776_a("Power", this.power);
/* 319 */         nbtTag.func_74776_a("MaxPower", this.maxPower);
/* 320 */         nbtTag.func_74768_a("PowerScale", this.powerScale);
/* 321 */         nbtTag.func_74768_a("RechargeScale", this.rechargeScale);
/* 322 */         nbtTag.func_74768_a("RangeScale", this.rangeScale);
/* 323 */         nbtTag.func_74768_a("EnhancementLevel", this.enhancementLevel);
/*     */       }
/* 325 */       super.func_145841_b(nbtTag);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 330 */       this.core = Coord.createFrom(nbtTag, "Core");
/* 331 */       this.power = nbtTag.func_74760_g("Power");
/* 332 */       this.maxPower = nbtTag.func_74760_g("MaxPower");
/* 333 */       this.powerScale = nbtTag.func_74762_e("PowerScale");
/* 334 */       this.rechargeScale = nbtTag.func_74762_e("RechargeScale");
/* 335 */       if (nbtTag.func_74764_b("RangeScale")) {
/* 336 */         this.rangeScale = nbtTag.func_74762_e("RangeScale");
/*     */       } else {
/* 338 */         this.rangeScale = 1;
/*     */       }
/* 340 */       if (nbtTag.func_74764_b("EnhancementLevel")) {
/* 341 */         this.enhancementLevel = nbtTag.func_74762_e("EnhancementLevel");
/*     */       } else {
/* 343 */         this.enhancementLevel = 0;
/*     */       }
/* 345 */       super.func_145839_a(nbtTag);
/*     */     }
/*     */     
/*     */     private void setCore(Coord coord) {
/* 349 */       this.core = coord;
/* 350 */       if (isCore()) {
/* 351 */         updatePower(false);
/* 352 */         PowerSources.instance().registerPowerSource(this);
/*     */       }
/* 354 */       if (coord == null) {
/* 355 */         PowerSources.instance().removePowerSource(this);
/* 356 */         this.power = 0.0F;
/* 357 */         this.maxPower = 0.0F;
/* 358 */         this.powerScale = 1;
/* 359 */         this.rechargeScale = 1;
/* 360 */         this.rangeScale = 1;
/* 361 */         this.enhancementLevel = 0;
/*     */       }
/* 363 */       this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, coord != null ? 1 : 0, 3);
/* 364 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     private boolean isCore() {
/* 368 */       return (this.core != null) && (this.core.isAtPosition(this));
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 373 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 374 */       func_145841_b(nbtTag);
/* 375 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 380 */       super.onDataPacket(net, packet);
/* 381 */       func_145839_a(packet.func_148857_g());
/* 382 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public float getCorePower() {
/* 386 */       if (this.core != null) {
/* 387 */         TileEntity te = this.core.getBlockTileEntity(this.field_145850_b);
/* 388 */         if ((te != null) && ((te instanceof TileEntityAltar))) {
/* 389 */           TileEntityAltar tileEntity = (TileEntityAltar)te;
/* 390 */           return tileEntity.power;
/*     */         }
/*     */       }
/*     */       
/* 394 */       return 0.0F;
/*     */     }
/*     */     
/*     */     private void updateCoreArtefacts() {
/* 398 */       if (this.core != null) {
/* 399 */         TileEntity tile = this.core.getBlockTileEntity(this.field_145850_b);
/* 400 */         if ((tile != null) && ((tile instanceof TileEntityAltar))) {
/* 401 */           TileEntityAltar tileEntity = (TileEntityAltar)tile;
/* 402 */           tileEntity.updateArtefacts();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean consumePower(float requiredPower)
/*     */     {
/* 409 */       if (this.core != null) {
/* 410 */         TileEntityAltar tileEntity = (TileEntityAltar)this.core.getBlockTileEntity(this.field_145850_b);
/* 411 */         if (tileEntity != null) {
/* 412 */           return tileEntity.consumeOurPower(requiredPower);
/*     */         }
/*     */       }
/* 415 */       return false;
/*     */     }
/*     */     
/*     */     private boolean consumeOurPower(float requiredPower) {
/* 419 */       if ((!this.field_145850_b.field_72995_K) && (this.power >= requiredPower)) {
/* 420 */         this.power -= requiredPower;
/* 421 */         return true;
/*     */       }
/* 423 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public float getCurrentPower()
/*     */     {
/* 429 */       if (this.core != null) {
/* 430 */         TileEntityAltar tileEntity = (TileEntityAltar)this.core.getBlockTileEntity(this.field_145850_b);
/* 431 */         if (tileEntity != null) {
/* 432 */           return tileEntity.getOurCurrentPower();
/*     */         }
/*     */       }
/* 435 */       return -1.0F;
/*     */     }
/*     */     
/*     */     private float getOurCurrentPower() {
/* 439 */       if (!this.field_145850_b.field_72995_K) {
/* 440 */         return this.power;
/*     */       }
/* 442 */       return -2.0F;
/*     */     }
/*     */     
/*     */     public float getCoreMaxPower()
/*     */     {
/* 447 */       if (this.core != null) {
/* 448 */         TileEntity tile = this.core.getBlockTileEntity(this.field_145850_b);
/* 449 */         if ((tile != null) && ((tile instanceof TileEntityAltar))) {
/* 450 */           TileEntityAltar tileEntity = (TileEntityAltar)tile;
/* 451 */           return tileEntity.maxPower * tileEntity.powerScale;
/*     */         }
/*     */       }
/*     */       
/* 455 */       return 0.0F;
/*     */     }
/*     */     
/*     */     public int getCoreSpeed() {
/* 459 */       if (this.core != null) {
/* 460 */         TileEntity tile = this.core.getBlockTileEntity(this.field_145850_b);
/* 461 */         if ((tile != null) && ((tile instanceof TileEntityAltar))) {
/* 462 */           TileEntityAltar tileEntity = (TileEntityAltar)tile;
/* 463 */           return tileEntity.rechargeScale;
/*     */         }
/*     */       }
/*     */       
/* 467 */       return 0;
/*     */     }
/*     */     
/*     */     public boolean isValid() {
/* 471 */       return this.core != null;
/*     */     }
/*     */     
/*     */     public World getWorld()
/*     */     {
/* 476 */       return this.field_145850_b;
/*     */     }
/*     */     
/*     */     public Coord getLocation()
/*     */     {
/* 481 */       return new Coord(this);
/*     */     }
/*     */     
/*     */     public boolean isLocationEqual(Coord location)
/*     */     {
/* 486 */       return (location != null) && (location.isAtPosition(this));
/*     */     }
/*     */     
/*     */     static class PowerSource {
/*     */       private final Block block;
/*     */       private final int factor;
/*     */       private final int limit;
/*     */       private int count;
/*     */       
/*     */       public PowerSource(Block block, int factor, int limit) {
/* 496 */         this.block = block;
/* 497 */         this.factor = factor;
/* 498 */         this.limit = limit;
/* 499 */         this.count = 0;
/*     */       }
/*     */       
/*     */       public int getPower() {
/* 503 */         return Math.min(this.count, this.limit) * this.factor;
/*     */       }
/*     */       
/*     */       static void createInMap(Map<Block, PowerSource> map, Block block, int factor, int limit) {
/* 507 */         PowerSource source = new PowerSource(block, factor, limit);
/* 508 */         map.put(block, source);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 514 */     private ArrayList<Block> extraNatureIDs = null;
/*     */     
/*     */     private void updatePower(boolean throttle)
/*     */     {
/* 518 */       if ((!this.field_145850_b.field_72995_K) && ((!throttle) || (this.ticks - this.lastPowerUpdate <= 0L) || (this.ticks - this.lastPowerUpdate > 100L))) {
/* 519 */         this.lastPowerUpdate = this.ticks;
/*     */         
/* 521 */         Hashtable<Block, PowerSource> powerObjectTable = new Hashtable();
/*     */         try
/*     */         {
/* 524 */           for (ItemStack blockItem : OreDictionary.getOres("treeSapling")) {
/* 525 */             Block block = Block.func_149634_a(blockItem.func_77973_b());
/* 526 */             if (block != null) {
/* 527 */               PowerSource.createInMap(powerObjectTable, block, 4, 20);
/*     */             }
/*     */           }
/*     */         } catch (Exception e) {
/* 531 */           Log.instance().warning(e, "Exception occurred while creating power source list for sapling ores");
/*     */         }
/*     */         try
/*     */         {
/* 535 */           for (ItemStack blockItem : OreDictionary.getOres("logWood")) {
/* 536 */             Block block = Block.func_149634_a(blockItem.func_77973_b());
/* 537 */             if (block != null) {
/* 538 */               PowerSource.createInMap(powerObjectTable, block, 2, 50);
/*     */             }
/*     */           }
/*     */         } catch (Exception e) {
/* 542 */           Log.instance().warning(e, "Exception occurred while creating power source list for log ores");
/*     */         }
/*     */         try
/*     */         {
/* 546 */           for (ItemStack blockItem : OreDictionary.getOres("treeLeaves")) {
/* 547 */             Block block = Block.func_149634_a(blockItem.func_77973_b());
/* 548 */             if (block != null) {
/* 549 */               PowerSource.createInMap(powerObjectTable, block, 3, 100);
/*     */             }
/*     */           }
/*     */         } catch (Exception e) {
/* 553 */           Log.instance().warning(e, "Exception occurred while creating power source list for leaf ores");
/*     */         }
/*     */         
/* 556 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150349_c, 2, 80);
/* 557 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150346_d, 1, 80);
/* 558 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150458_ak, 1, 100);
/* 559 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150329_H, 3, 50);
/* 560 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150327_N, 4, 30);
/* 561 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150328_O, 4, 30);
/* 562 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150464_aj, 4, 20);
/*     */         
/* 564 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150355_j, 1, 50);
/* 565 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150337_Q, 3, 20);
/* 566 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150338_P, 3, 20);
/* 567 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150434_aF, 3, 50);
/* 568 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150436_aH, 3, 50);
/* 569 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150423_aK, 4, 20);
/* 570 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150393_bb, 3, 20);
/* 571 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150420_aW, 3, 20);
/* 572 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150419_aX, 3, 20);
/* 573 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150440_ba, 4, 20);
/* 574 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150394_bc, 3, 20);
/* 575 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150395_bd, 2, 50);
/* 576 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150391_bh, 1, 80);
/* 577 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150380_bt, 250, 1);
/* 578 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.DEMON_HEART, 40, 2);
/* 579 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150375_by, 3, 20);
/* 580 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150459_bM, 4, 20);
/* 581 */         PowerSource.createInMap(powerObjectTable, Blocks.field_150469_bN, 4, 20);
/*     */         
/* 583 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.CROP_BELLADONNA, 4, 20);
/* 584 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.CROP_MANDRAKE, 4, 20);
/* 585 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.CROP_ARTICHOKE, 4, 20);
/* 586 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.CROP_SNOWBELL, 4, 20);
/* 587 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.EMBER_MOSS, 4, 20);
/* 588 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.LEAVES, 4, 50);
/* 589 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.LOG, 3, 100);
/* 590 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.SPANISH_MOSS, 3, 20);
/* 591 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.GLINT_WEED, 2, 20);
/* 592 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.CRITTER_SNARE, 2, 10);
/* 593 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.BLOOD_ROSE, 2, 10);
/* 594 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.GRASSPER, 2, 10);
/* 595 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.WISPY_COTTON, 3, 20);
/* 596 */         PowerSource.createInMap(powerObjectTable, Witchery.Blocks.INFINITY_EGG, 1000, 1);
/*     */         
/* 598 */         if (this.extraNatureIDs == null) {
/*     */           try {
/* 600 */             this.extraNatureIDs = new ArrayList();
/* 601 */             Iterator iterator = Block.field_149771_c.iterator();
/* 602 */             while (iterator.hasNext()) {
/* 603 */               Block block = (Block)iterator.next();
/* 604 */               if ((((block instanceof BlockFlower)) || ((block instanceof BlockCrops))) && 
/* 605 */                 (!powerObjectTable.containsKey(block))) {
/* 606 */                 this.extraNatureIDs.add(block);
/* 607 */                 Log.instance().debug(block.func_149739_a());
/*     */               }
/*     */             }
/*     */           }
/*     */           catch (Exception e) {
/* 612 */             Log.instance().warning(e, "Exception occurred while creating power source list for other mod flowers and crops");
/*     */           }
/*     */         }
/*     */         
/* 616 */         for (Block block : this.extraNatureIDs) {
/* 617 */           PowerSource.createInMap(powerObjectTable, block, 2, 4);
/*     */         }
/*     */         
/* 620 */         for (int y = this.field_145848_d - 14; y <= this.field_145848_d + 14; y++) {
/* 621 */           for (int z = this.field_145849_e + 14; z >= this.field_145849_e - 14; z--) {
/* 622 */             for (int x = this.field_145851_c - 14; x <= this.field_145851_c + 14; x++) {
/* 623 */               Block block = this.field_145850_b.func_147439_a(x, y, z);
/* 624 */               PowerSource source = (PowerSource)powerObjectTable.get(block);
/* 625 */               if (source != null) {
/* 626 */                 PowerSource.access$204(source);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 632 */         float newMax = 0.0F;
/*     */         
/* 634 */         for (PowerSource source : powerObjectTable.values()) {
/* 635 */           newMax += source.getPower();
/*     */         }
/* 637 */         if (newMax != this.maxPower) {
/* 638 */           this.maxPower = newMax;
/* 639 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private void updateArtefacts() {
/* 645 */       ArrayList<Coord> visited = new ArrayList();
/* 646 */       ArrayList<Coord> toVisit = new ArrayList();
/* 647 */       toVisit.add(new Coord(this.field_145851_c, this.field_145848_d, this.field_145849_e));
/*     */       
/* 649 */       boolean headfound = false;
/* 650 */       boolean candlefound = false;
/* 651 */       boolean cupfound = false;
/* 652 */       boolean knifeFound = false;
/* 653 */       boolean wandFound = false;
/* 654 */       boolean pentacleFound = false;
/* 655 */       boolean infinityFound = false;
/*     */       
/* 657 */       int newPowerScale = 1;
/* 658 */       int newRechargeScale = 1;
/* 659 */       int newRangeScale = 1;
/* 660 */       int newEnhancementLevel = 0;
/*     */       
/* 662 */       while (toVisit.size() > 0) {
/* 663 */         Coord coord = (Coord)toVisit.get(0);
/* 664 */         toVisit.remove(0);
/* 665 */         for (Coord newCoord : new Coord[] { coord.north(), coord.south(), coord.east(), coord.west() }) {
/* 666 */           if ((newCoord.getBlock(this.field_145850_b) == Witchery.Blocks.ALTAR) && 
/* 667 */             (!visited.contains(newCoord)) && (!toVisit.contains(newCoord))) {
/* 668 */             toVisit.add(newCoord);
/*     */           }
/*     */         }
/*     */         
/* 672 */         visited.add(coord);
/* 673 */         int offsetY = 1;
/* 674 */         Block block = coord.getBlock(this.field_145850_b, 0, 1, 0);
/* 675 */         if ((!headfound) && (block == Blocks.field_150465_bP)) {
/* 676 */           TileEntity tile = coord.getBlockTileEntity(this.field_145850_b, 0, 1, 0);
/* 677 */           if ((tile != null) && ((tile instanceof TileEntitySkull))) {
/* 678 */             TileEntitySkull skullTileEntity = (TileEntitySkull)tile;
/* 679 */             switch (skullTileEntity.func_145904_a()) {
/*     */             case 0: 
/* 681 */               newRechargeScale++;
/* 682 */               newPowerScale++;
/* 683 */               headfound = true;
/* 684 */               break;
/*     */             case 1: 
/* 686 */               newRechargeScale += 2;
/* 687 */               newPowerScale += 2;
/* 688 */               headfound = true;
/* 689 */               break;
/*     */             case 3: 
/* 691 */               newRechargeScale += 3;
/* 692 */               newPowerScale += 3;
/* 693 */               headfound = true;
/* 694 */               break;
/*     */             
/*     */ 
/*     */             }
/*     */             
/*     */           }
/*     */         }
/* 701 */         else if ((!candlefound) && (block == Witchery.Blocks.CANDELABRA)) {
/* 702 */           candlefound = true;
/* 703 */           newRechargeScale += 2;
/* 704 */         } else if ((!candlefound) && (block == Blocks.field_150478_aa)) {
/* 705 */           candlefound = true;
/* 706 */           newRechargeScale++;
/* 707 */         } else if (block == Witchery.Blocks.PLACED_ITEMSTACK) {
/* 708 */           TileEntity tile = coord.getBlockTileEntity(this.field_145850_b, 0, 1, 0);
/* 709 */           if ((tile != null) && ((tile instanceof BlockPlacedItem.TileEntityPlacedItem))) {
/* 710 */             BlockPlacedItem.TileEntityPlacedItem placeItem = (BlockPlacedItem.TileEntityPlacedItem)tile;
/* 711 */             if (placeItem.getStack() != null) {
/* 712 */               if ((!knifeFound) && (placeItem.getStack().func_77973_b() == Witchery.Items.ARTHANA)) {
/* 713 */                 knifeFound = true;
/* 714 */                 newRangeScale++;
/* 715 */               } else if ((!wandFound) && (placeItem.getStack().func_77973_b() == Witchery.Items.MYSTIC_BRANCH)) {
/* 716 */                 wandFound = true;
/* 717 */                 newEnhancementLevel++;
/* 718 */               } else if ((!pentacleFound) && (Witchery.Items.GENERIC.itemKobolditePentacle.isMatch(placeItem.getStack()))) {
/* 719 */                 pentacleFound = true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 724 */         else if ((!cupfound) && (block == Witchery.Blocks.CHALICE)) {
/* 725 */           cupfound = true;
/* 726 */           TileEntity tile = coord.getBlockTileEntity(this.field_145850_b, 0, 1, 0);
/* 727 */           if ((tile != null) && ((tile instanceof BlockChalice.TileEntityChalice))) {
/* 728 */             BlockChalice.TileEntityChalice tileEntityChalice = (BlockChalice.TileEntityChalice)tile;
/* 729 */             newPowerScale += ((tileEntityChalice != null) && (tileEntityChalice.isFilled()) ? 2 : 1);
/*     */           }
/* 731 */         } else if ((!infinityFound) && (block == Witchery.Blocks.INFINITY_EGG)) {
/* 732 */           infinityFound = true;
/*     */         }
/*     */       }
/*     */       
/* 736 */       if (pentacleFound) {
/* 737 */         newRechargeScale *= 2;
/*     */       }
/*     */       
/* 740 */       if (infinityFound) {
/* 741 */         newRechargeScale *= 10;
/* 742 */         newPowerScale *= 10;
/*     */       }
/*     */       
/* 745 */       if ((newRechargeScale != this.rechargeScale) || (newPowerScale != this.powerScale) || (newRangeScale != this.rangeScale) || (newEnhancementLevel != this.enhancementLevel)) {
/* 746 */         this.rechargeScale = newRechargeScale;
/* 747 */         this.powerScale = newPowerScale;
/* 748 */         this.rangeScale = newRangeScale;
/* 749 */         this.enhancementLevel = newEnhancementLevel;
/* 750 */         if (!this.field_145850_b.field_72995_K) {
/* 751 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockAltar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */