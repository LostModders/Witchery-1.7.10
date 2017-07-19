/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryFluids;
/*     */ import com.emoniph.witchery.blocks.TileEntityBase;
/*     */ import com.emoniph.witchery.brewing.action.BrewAction;
/*     */ import com.emoniph.witchery.brewing.potions.PotionBase;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import com.emoniph.witchery.entity.EntityLeonard;
/*     */ import com.emoniph.witchery.util.CircleUtil;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.FluidTankInfo;
/*     */ import net.minecraftforge.fluids.IFluidHandler;
/*     */ 
/*     */ public class TileEntityCauldron extends TileEntityBase implements IFluidHandler
/*     */ {
/*     */   private int ticksHeated;
/*     */   private boolean powered;
/*     */   private int ritualTicks;
/*  57 */   private static final int TICKS_TO_BOIL = TimeUtil.secsToTicks(5);
/*     */   
/*     */   public boolean isBoiling() {
/*  60 */     return this.ticksHeated == TICKS_TO_BOIL;
/*     */   }
/*     */   
/*     */   public boolean isPowered() {
/*  64 */     return this.powered;
/*     */   }
/*     */   
/*     */   public int getRedstoneSignalStrength()
/*     */   {
/*  69 */     if (!isFilled()) {
/*  70 */       return 0;
/*     */     }
/*     */     
/*  73 */     if (!isBoiling()) {
/*  74 */       return 3;
/*     */     }
/*     */     
/*  77 */     NBTTagCompound nbtRoot = this.tank.getFluid().tag;
/*     */     
/*  79 */     if ((nbtRoot == null) || (nbtRoot.func_74762_e("EffectCount") == 0)) {
/*  80 */       return 6;
/*     */     }
/*     */     
/*  83 */     if (!isPowered()) {
/*  84 */       return 9;
/*     */     }
/*     */     
/*  87 */     if (nbtRoot.func_74762_e("RemainingCapacity") > 0) {
/*  88 */       return 12;
/*     */     }
/*     */     
/*  91 */     return 15;
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  96 */     super.func_145845_h();
/*  97 */     if (!this.field_145850_b.field_72995_K) {
/*  98 */       boolean sync = false;
/*     */       
/* 100 */       Block blockBelow = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/* 101 */       if ((blockBelow == Blocks.field_150480_ab) && (isFilled())) {
/* 102 */         if ((this.ticksHeated < TICKS_TO_BOIL) && 
/* 103 */           (++this.ticksHeated == TICKS_TO_BOIL)) {
/* 104 */           sync = true;
/*     */         }
/*     */         
/*     */       }
/* 108 */       else if (this.ticksHeated > 0) {
/* 109 */         this.ticksHeated = 0;
/* 110 */         sync = true;
/*     */       }
/*     */       
/*     */ 
/* 114 */       if ((isBoiling()) && (this.ticks % 20L == 7L)) {
/* 115 */         boolean wasPowered = this.powered;
/* 116 */         int power = getPower();
/* 117 */         if (power == 0) {
/* 118 */           this.powered = true;
/* 119 */         } else if (power > 0) {
/* 120 */           IPowerSource source = PowerSources.findClosestPowerSource(this);
/*     */           double powerNeeded;
/* 122 */           double powerNeeded; if ((this.tank.getFluid() != null) && (this.tank.getFluid().tag != null) && (this.tank.getFluidAmount() == 3000) && (this.tank.getFluid().tag.func_74767_n("RitualTriggered")))
/*     */           {
/*     */ 
/* 125 */             boolean small = CircleUtil.isSmallCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_RITUAL);
/*     */             
/* 127 */             boolean smallPower = CircleUtil.isSmallCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_INFERNAL);
/*     */             
/* 129 */             boolean medium = CircleUtil.isMediumCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_RITUAL);
/*     */             
/* 131 */             boolean mediumPower = CircleUtil.isMediumCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_INFERNAL);
/*     */             
/*     */ 
/* 134 */             double powerScale = 1.4D;
/* 135 */             if (small) {
/* 136 */               powerScale -= 0.2D;
/*     */             }
/*     */             
/* 139 */             if (medium) {
/* 140 */               powerScale -= 0.2D;
/*     */             }
/*     */             
/* 143 */             if (smallPower) {
/* 144 */               powerScale -= 0.37D;
/*     */             }
/*     */             
/* 147 */             if (mediumPower) {
/* 148 */               powerScale -= 0.37D;
/*     */             }
/* 150 */             powerNeeded = power * powerScale;
/*     */           } else {
/* 152 */             powerNeeded = power;
/*     */           }
/*     */           
/* 155 */           this.powered = ((power == 0) || ((source != null) && (powerNeeded <= source.getCurrentPower())));
/*     */         } else {
/* 157 */           this.powered = false;
/*     */         }
/*     */         
/* 160 */         if (wasPowered != this.powered) {
/* 161 */           sync = true;
/*     */         }
/*     */       }
/*     */       
/* 165 */       if (this.ticks % 10L == 8L) {
/* 166 */         int oldRitualTicks = this.ritualTicks;
/* 167 */         int UPDATES_TO_ACTIVATE = 20;
/* 168 */         if ((isBoiling()) && (isPowered()) && (this.tank.getFluid() != null) && (this.tank.getFluid().tag != null) && (this.tank.getFluidAmount() == 3000))
/*     */         {
/* 170 */           NBTTagCompound nbtRoot = this.tank.getFluid().tag;
/* 171 */           if (nbtRoot.func_74767_n("RitualTriggered")) {
/* 172 */             this.ritualTicks += 1;
/*     */             
/* 174 */             int witchCount = 0;
/* 175 */             List<EntityCovenWitch> covenWitches = EntityUtil.getEntitiesInRadius(EntityCovenWitch.class, this, 6.0D);
/*     */             
/* 177 */             for (EntityCovenWitch witch : covenWitches) {
/* 178 */               if (witch.func_70902_q() != null) {
/* 179 */                 witchCount++;
/*     */               }
/*     */             }
/*     */             
/* 183 */             List<EntityPlayer> playerWitches = EntityUtil.getEntitiesInRadius(EntityPlayer.class, this, 6.0D);
/*     */             
/* 185 */             boolean playerCoven = false;
/* 186 */             for (EntityPlayer player : playerWitches) {
/* 187 */               if (EntityCovenWitch.getCovenSize(player) > 0) {
/* 188 */                 if (playerCoven) {
/* 189 */                   witchCount++;
/*     */                 } else {
/* 191 */                   playerCoven = true;
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/* 196 */             if (this.ritualTicks > 20) {
/* 197 */               IPowerSource powerSource = PowerSources.findClosestPowerSource(this);
/*     */               
/* 199 */               int neededPower = getPower();
/*     */               
/* 201 */               boolean small = CircleUtil.isSmallCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_RITUAL);
/*     */               
/* 203 */               boolean smallPower = CircleUtil.isSmallCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_INFERNAL);
/*     */               
/* 205 */               boolean medium = CircleUtil.isMediumCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_RITUAL);
/*     */               
/* 207 */               boolean mediumPower = CircleUtil.isMediumCircle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Witchery.Blocks.GLYPH_INFERNAL);
/*     */               
/*     */ 
/* 210 */               double powerScale = 1.4D;
/* 211 */               if (small) {
/* 212 */                 powerScale -= 0.2D;
/*     */               }
/*     */               
/* 215 */               if (medium) {
/* 216 */                 powerScale -= 0.2D;
/*     */               }
/*     */               
/* 219 */               int risk = 0;
/* 220 */               if ((!small) && (!medium)) {
/* 221 */                 risk++;
/*     */               }
/*     */               
/* 224 */               if (smallPower) {
/* 225 */                 risk++;
/* 226 */                 powerScale -= 0.37D;
/*     */               }
/*     */               
/* 229 */               if (mediumPower) {
/* 230 */                 risk++;
/* 231 */                 powerScale -= 0.37D;
/*     */               }
/*     */               
/* 234 */               if ((neededPower == 0) || ((powerSource != null) && (powerSource.consumePower((int)Math.floor(neededPower * powerScale)))))
/*     */               {
/*     */ 
/*     */ 
/* 238 */                 double R = 16.0D;
/* 239 */                 AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.field_145851_c - R, this.field_145848_d - R, this.field_145849_e - R, this.field_145851_c + R, this.field_145848_d + R, this.field_145849_e + R);
/*     */                 
/* 241 */                 List<EntityLeonard> leonards = this.field_145850_b.func_72872_a(EntityLeonard.class, bb);
/*     */                 
/* 243 */                 boolean lenny = false;
/* 244 */                 for (EntityLeonard leonard : leonards) {
/* 245 */                   if ((!leonard.field_70128_L) && (leonard.func_110143_aJ() > 1.0F)) {
/* 246 */                     lenny = true;
/* 247 */                     break;
/*     */                   }
/*     */                 }
/*     */                 
/* 251 */                 RitualStatus status = WitcheryBrewRegistry.INSTANCE.updateRitual(MinecraftServer.func_71276_C(), this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, nbtRoot, witchCount, this.ritualTicks - 20, lenny);
/*     */                 
/*     */ 
/* 254 */                 boolean failed = false;
/* 255 */                 switch (status)
/*     */                 {
/*     */                 case ONGOING: 
/* 258 */                   checkForMisfortune(risk + (lenny ? 1 : 0), neededPower);
/*     */                   
/* 260 */                   break;
/*     */                 case COMPLETE: 
/* 262 */                   ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, 0.5D, 1.0D, 8);
/*     */                   
/* 264 */                   drain(ForgeDirection.UNKNOWN, getLiquidQuantity(), true);
/* 265 */                   this.ritualTicks = 0;
/* 266 */                   this.powered = false;
/* 267 */                   this.ticksHeated = 0;
/* 268 */                   sync = true;
/*     */                   
/* 270 */                   checkForMisfortune(risk + (lenny ? 1 : 0), neededPower);
/*     */                   
/* 272 */                   break;
/*     */                 
/*     */                 case FAILED_DISTANCE: 
/* 275 */                   ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, 0.5D, 1.0D, 8, -16742400);
/*     */                   
/* 277 */                   failed = true;
/* 278 */                   break;
/*     */                 case FAILED_NO_COVEN: 
/* 280 */                   ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, 0.5D, 1.0D, 8, -16777080);
/*     */                   
/* 282 */                   failed = true;
/* 283 */                   break;
/*     */                 case FAILED_INVALID_CIRCLES: 
/* 285 */                   ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7864320);
/*     */                   
/* 287 */                   failed = true;
/* 288 */                   break;
/*     */                 case FAILED: 
/* 290 */                   ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7864184);
/*     */                   
/* 292 */                   failed = true;
/*     */                 }
/*     */                 
/*     */                 
/* 296 */                 if (failed) {
/* 297 */                   NBTTagList nbtItems = nbtRoot.func_150295_c("Items", 10);
/* 298 */                   ItemStack stack = ItemStack.func_77949_a(nbtItems.func_150305_b(nbtItems.func_74745_c() - 1));
/*     */                   
/* 300 */                   nbtItems.func_74744_a(nbtItems.func_74745_c() - 1);
/* 301 */                   EntityUtil.spawnEntityInWorld(this.field_145850_b, new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, stack));
/*     */                   
/* 303 */                   nbtRoot.func_74757_a("RitualTriggered", false);
/* 304 */                   this.ritualTicks = 0;
/* 305 */                   sync = true;
/*     */                 }
/*     */               }
/* 308 */               else if (this.ritualTicks > 21) {
/* 309 */                 drain(ForgeDirection.UNKNOWN, getLiquidQuantity(), true);
/* 310 */                 this.ritualTicks = 0;
/* 311 */                 this.powered = false;
/* 312 */                 this.ticksHeated = 0;
/* 313 */                 ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7829504);
/*     */                 
/* 315 */                 sync = true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 322 */           if (this.ritualTicks > 20) {
/* 323 */             drain(ForgeDirection.UNKNOWN, getLiquidQuantity(), true);
/* 324 */             this.ritualTicks = 0;
/* 325 */             this.powered = false;
/* 326 */             this.ticksHeated = 0;
/* 327 */             ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7829504);
/*     */             
/* 329 */             sync = true;
/*     */           }
/* 331 */           this.ritualTicks = 0;
/*     */         }
/* 333 */         if (this.ritualTicks != oldRitualTicks) {
/* 334 */           sync = true;
/*     */         }
/*     */       }
/*     */       
/* 338 */       if (sync) {
/* 339 */         markBlockForUpdate(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkForMisfortune(int risk, int power) {
/* 345 */     if ((risk > 0) && (power > 0)) {
/* 346 */       double roll = this.field_145850_b.field_73012_v.nextDouble() * (1.0D + (risk - 1) * 0.2D);
/* 347 */       if (roll < 0.5D)
/* 348 */         return;
/* 349 */       if (roll < 0.75D) {
/* 350 */         applyToAllNear(new PotionEffect(Potion.field_76421_d.field_76415_H, TimeUtil.secsToTicks(60), 1));
/* 351 */       } else if (roll < 0.9D) {
/* 352 */         applyToAllNear(new PotionEffect(Witchery.Potions.PARALYSED.field_76415_H, TimeUtil.secsToTicks(20), 2));
/* 353 */       } else if (roll < 0.98D) {
/* 354 */         applyToAllNear(new PotionEffect(Witchery.Potions.INSANITY.field_76415_H, TimeUtil.minsToTicks(3), 2));
/*     */       } else {
/* 356 */         applyToAllNear(new PotionEffect(Witchery.Potions.PARALYSED.field_76415_H, TimeUtil.secsToTicks(10), 2));
/* 357 */         for (int i = 0; i < this.field_145850_b.field_73012_v.nextInt(3) + 2; i++) {
/* 358 */           spawnBolt(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 4);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void applyToAllNear(PotionEffect effect) {
/* 365 */     double R = 16.0D;
/* 366 */     double RSq = 256.0D;
/* 367 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(0.5D + this.field_145851_c - 16.0D, this.field_145848_d - 2, 0.5D + this.field_145849_e - 16.0D, 0.5D + this.field_145851_c + 16.0D, this.field_145848_d + 4, 0.5D + this.field_145849_e + 16.0D);
/*     */     
/* 369 */     List<EntityLivingBase> entities = this.field_145850_b.func_72872_a(EntityLivingBase.class, bounds);
/* 370 */     for (EntityLivingBase entity : entities) {
/* 371 */       if ((entity.func_70092_e(0.5D + this.field_145851_c, entity.field_70163_u, 0.5D + this.field_145849_e) < 256.0D) && (!(entity instanceof IMob)) && (!(entity instanceof IBossDisplayData)))
/*     */       {
/* 373 */         List<Potion> effectsToRemove = new ArrayList();
/* 374 */         Collection<PotionEffect> effects = entity.func_70651_bq();
/* 375 */         for (PotionEffect buffs : effects) {
/* 376 */           Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/* 377 */           if (!PotionBase.isDebuff(potion)) {
/* 378 */             effectsToRemove.add(potion);
/*     */           }
/*     */         }
/* 381 */         for (Potion potion : effectsToRemove) {
/* 382 */           entity.func_82170_o(potion.field_76415_H);
/*     */         }
/* 384 */         entity.func_70690_d(new PotionEffect(effect));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void spawnBolt(World world, int posX, int posY, int posZ, int min, int max) {
/* 390 */     int activeRadius = max - min;
/* 391 */     int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 392 */     if (ax > activeRadius) {
/* 393 */       ax += min * 2;
/*     */     }
/* 395 */     int x = posX - max + ax;
/*     */     
/* 397 */     int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 398 */     if (az > activeRadius) {
/* 399 */       az += min * 2;
/*     */     }
/*     */     
/* 402 */     int z = posZ - max + az;
/*     */     
/* 404 */     EntityLightningBolt bolt = new EntityLightningBolt(world, x, posY, z);
/* 405 */     world.func_72942_c(bolt);
/*     */   }
/*     */   
/*     */   public boolean isRitualInProgress() {
/* 409 */     return this.ritualTicks > 0;
/*     */   }
/*     */   
/*     */   public boolean addItem(BrewAction brewAction, ItemStack entityItem) {
/* 413 */     if (this.field_145850_b.field_72995_K) {
/* 414 */       return false;
/*     */     }
/*     */     
/* 417 */     if (this.tank.getFluid().getFluid().getName().equals(FluidRegistry.WATER.getName())) {
/* 418 */       NBTTagCompound nbtTest = new NBTTagCompound();
/* 419 */       nbtTest.func_74782_a("Items", new NBTTagList());
/* 420 */       if (!WitcheryBrewRegistry.INSTANCE.canAdd(nbtTest, brewAction, this.tank.getFluidAmount() == this.tank.getCapacity()))
/*     */       {
/* 422 */         return false;
/*     */       }
/* 424 */       this.tank.setFluid(new FluidStack(Witchery.Fluids.BREW, this.tank.getFluid().amount));
/* 425 */       this.tank.getFluid().tag = nbtTest;
/*     */     }
/*     */     
/* 428 */     if (this.tank.getFluid().tag == null) {
/* 429 */       this.tank.getFluid().tag = new NBTTagCompound();
/*     */     }
/*     */     
/* 432 */     NBTTagCompound nbtRoot = this.tank.getFluid().tag;
/* 433 */     if (!nbtRoot.func_74764_b("Items")) {
/* 434 */       nbtRoot.func_74782_a("Items", new NBTTagList());
/*     */     }
/*     */     
/* 437 */     if (!WitcheryBrewRegistry.INSTANCE.canAdd(nbtRoot, brewAction, this.tank.getFluidAmount() == this.tank.getCapacity()))
/*     */     {
/* 439 */       return false;
/*     */     }
/*     */     
/* 442 */     if (!brewAction.removeWhenAddedToCauldron(this.field_145850_b)) {
/* 443 */       NBTTagList nbtItems = nbtRoot.func_150295_c("Items", 10);
/* 444 */       NBTTagCompound nbtItem = new NBTTagCompound();
/* 445 */       WitcheryBrewRegistry.INSTANCE.nullifyItems(nbtRoot, nbtItems, brewAction);
/* 446 */       entityItem.func_77955_b(nbtItem);
/* 447 */       nbtItems.func_74742_a(nbtItem);
/*     */     }
/*     */     
/* 450 */     int color = brewAction.augmentColor(nbtRoot.func_74762_e("Color"));
/* 451 */     nbtRoot.func_74768_a("Color", color);
/*     */     
/* 453 */     AltarPower powerNeeded = WitcheryBrewRegistry.INSTANCE.getPowerRequired(nbtRoot);
/* 454 */     nbtRoot.func_74768_a("Power", powerNeeded.getPower());
/*     */     
/* 456 */     nbtRoot.func_74778_a("BrewName", WitcheryBrewRegistry.INSTANCE.getBrewName(nbtRoot));
/* 457 */     WitcheryBrewRegistry.INSTANCE.updateBrewInformation(nbtRoot);
/* 458 */     nbtRoot.func_74768_a("BrewDrinkSpeed", WitcheryBrewRegistry.INSTANCE.getBrewDrinkSpeed(nbtRoot));
/*     */     
/* 460 */     if (brewAction.createsSplash()) {
/* 461 */       nbtRoot.func_74757_a("Splash", true);
/*     */     }
/*     */     
/* 464 */     if (brewAction.triggersRitual()) {
/* 465 */       nbtRoot.func_74757_a("RitualTriggered", true);
/* 466 */       this.ritualTicks = 0;
/*     */     }
/*     */     
/* 469 */     markBlockForUpdate(true);
/* 470 */     return true;
/*     */   }
/*     */   
/*     */   public boolean explodeBrew(EntityPlayer nearestPlayer) {
/* 474 */     if ((this.field_145850_b.field_72995_K) || (nearestPlayer == null)) {
/* 475 */       return false;
/*     */     }
/*     */     
/* 478 */     if (this.tank.getFluid() == null) {
/* 479 */       return false;
/*     */     }
/*     */     
/* 482 */     if (this.tank.getFluid().getFluid().getName().equals(FluidRegistry.WATER.getName())) {
/* 483 */       return false;
/*     */     }
/*     */     
/* 486 */     if (this.tank.getFluid().tag == null) {
/* 487 */       return false;
/*     */     }
/*     */     
/* 490 */     NBTTagCompound nbtRoot = this.tank.getFluid().tag;
/* 491 */     if (!nbtRoot.func_74764_b("Items")) {
/* 492 */       return false;
/*     */     }
/*     */     
/* 495 */     WitcheryBrewRegistry.INSTANCE.explodeBrew(this.field_145850_b, nbtRoot, nearestPlayer, 0.5D + this.field_145851_c, 1.5D + this.field_145848_d, 0.5D + this.field_145849_e);
/*     */     
/* 497 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getColor() {
/* 502 */     NBTTagCompound nbtRoot = this.tank.getFluid() != null ? this.tank.getFluid().tag : null;
/* 503 */     if (nbtRoot != null) {
/* 504 */       int color = nbtRoot.func_74762_e("Color");
/* 505 */       return color;
/*     */     }
/* 507 */     return -1;
/*     */   }
/*     */   
/*     */   public int getPower()
/*     */   {
/* 512 */     NBTTagCompound nbtRoot = this.tank.getFluid() != null ? this.tank.getFluid().tag : null;
/* 513 */     if (nbtRoot != null) {
/* 514 */       int power = nbtRoot.func_74762_e("Power");
/* 515 */       return power;
/*     */     }
/* 517 */     return -1;
/*     */   }
/*     */   
/*     */ 
/* 521 */   private FluidTank tank = new FluidTank(3000);
/*     */   
/*     */   public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
/*     */   {
/* 525 */     if (this.tank.getFluid() == null) {
/* 526 */       int filled = this.tank.fill(resource, doFill);
/* 527 */       FluidStack newStack = this.tank.getFluid();
/* 528 */       if (newStack != null) {
/* 529 */         newStack.tag = (resource.tag != null ? (NBTTagCompound)resource.tag.func_74737_b() : null);
/* 530 */         markBlockForUpdate(false);
/*     */       }
/* 532 */       return filled; }
/* 533 */     if ((resource.isFluidEqual(this.tank.getFluid())) && ((this.tank.getFluid().tag == null) || ((resource.tag != null) && (this.tank.getFluid().tag.func_150295_c("Items", 10).equals(resource.tag.func_150295_c("Items", 10))))))
/*     */     {
/*     */ 
/* 536 */       int filled = this.tank.fill(resource, doFill);
/* 537 */       FluidStack newStack = this.tank.getFluid();
/* 538 */       if (newStack != null) {
/* 539 */         newStack.tag = (resource.tag != null ? (NBTTagCompound)resource.tag.func_74737_b() : null);
/*     */       }
/* 541 */       markBlockForUpdate(false);
/* 542 */       return filled;
/*     */     }
/* 544 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
/*     */   {
/* 551 */     if ((resource == null) || (!resource.isFluidEqual(this.tank.getFluid()))) {
/* 552 */       return null;
/*     */     }
/*     */     
/* 555 */     NBTTagCompound oldTag = (this.tank.getFluid() != null) && (this.tank.getFluid().tag != null) ? this.tank.getFluid().tag : null;
/*     */     
/* 557 */     FluidStack drained = this.tank.drain(resource.amount, doDrain);
/* 558 */     drained.tag = (oldTag != null ? (NBTTagCompound)oldTag.func_74737_b() : null);
/* 559 */     if (this.tank.getFluidAmount() == 0) {
/* 560 */       this.powered = false;
/*     */     }
/* 562 */     markBlockForUpdate(false);
/* 563 */     return drained;
/*     */   }
/*     */   
/*     */   public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
/*     */   {
/* 568 */     NBTTagCompound oldTag = (this.tank.getFluid() != null) && (this.tank.getFluid().tag != null) ? this.tank.getFluid().tag : null;
/*     */     
/* 570 */     FluidStack fluid = this.tank.drain(maxDrain, doDrain);
/* 571 */     if (fluid != null) {
/* 572 */       fluid.tag = (oldTag != null ? (NBTTagCompound)oldTag.func_74737_b() : null);
/*     */     }
/* 574 */     if (this.tank.getFluidAmount() == 0) {
/* 575 */       this.powered = false;
/*     */     }
/* 577 */     markBlockForUpdate(false);
/* 578 */     return fluid;
/*     */   }
/*     */   
/*     */   public boolean canFill(ForgeDirection from, Fluid fluid)
/*     */   {
/* 583 */     if (fluid == null) {
/* 584 */       return false;
/*     */     }
/* 586 */     return (fluid.getName().equals(FluidRegistry.WATER.getName())) || (fluid == Witchery.Fluids.BREW);
/*     */   }
/*     */   
/*     */   public boolean canDrain(ForgeDirection from, Fluid fluid)
/*     */   {
/* 591 */     if (fluid == null) {
/* 592 */       return false;
/*     */     }
/* 594 */     return (fluid.getName().equals(FluidRegistry.WATER.getName())) || (fluid == Witchery.Fluids.BREW);
/*     */   }
/*     */   
/*     */   public FluidTankInfo[] getTankInfo(ForgeDirection from)
/*     */   {
/* 599 */     return new FluidTankInfo[] { this.tank.getInfo() };
/*     */   }
/*     */   
/*     */   public boolean isFilled() {
/* 603 */     return this.tank.getFluid() != null;
/*     */   }
/*     */   
/*     */   public int getMaxLiquidQuantity() {
/* 607 */     return this.tank.getCapacity();
/*     */   }
/*     */   
/*     */   public int getLiquidQuantity() {
/* 611 */     return this.tank.getFluidAmount();
/*     */   }
/*     */   
/*     */   public double getPercentFilled() {
/* 615 */     return this.tank.getFluidAmount() / this.tank.getCapacity();
/*     */   }
/*     */   
/*     */   public Packet func_145844_m()
/*     */   {
/* 620 */     NBTTagCompound nbtTag = new NBTTagCompound();
/* 621 */     func_145841_b(nbtTag);
/* 622 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */   {
/* 627 */     super.onDataPacket(net, packet);
/* 628 */     func_145839_a(packet.func_148857_g());
/* 629 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbtRoot)
/*     */   {
/* 634 */     super.func_145839_a(nbtRoot);
/* 635 */     if (this.tank.getFluidAmount() > 0) {
/* 636 */       this.tank.drain(this.tank.getFluidAmount(), true);
/*     */     }
/* 638 */     this.tank.readFromNBT(nbtRoot);
/*     */     
/* 640 */     this.ticksHeated = nbtRoot.func_74762_e("TicksHeated");
/* 641 */     this.powered = nbtRoot.func_74767_n("Powered");
/* 642 */     this.ritualTicks = nbtRoot.func_74762_e("RitualTicks");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbtRoot)
/*     */   {
/* 647 */     super.func_145841_b(nbtRoot);
/* 648 */     this.tank.writeToNBT(nbtRoot);
/* 649 */     nbtRoot.func_74768_a("TicksHeated", this.ticksHeated);
/* 650 */     nbtRoot.func_74757_a("Powered", this.powered);
/* 651 */     nbtRoot.func_74768_a("RitualTicks", this.ritualTicks);
/*     */   }
/*     */   
/*     */   public int getRitualSeconds() {
/* 655 */     return this.ritualTicks;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/TileEntityCauldron.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */