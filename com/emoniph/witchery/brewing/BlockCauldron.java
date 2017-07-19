/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.client.particle.BubblesFX;
/*     */ import com.emoniph.witchery.client.particle.NaturePowerFX;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTankInfo;
/*     */ 
/*     */ public class BlockCauldron extends BlockBaseContainer
/*     */ {
/*     */   public BlockCauldron()
/*     */   {
/*  44 */     super(net.minecraft.block.material.Material.field_151573_f, TileEntityCauldron.class);
/*  45 */     func_149711_c(2.0F);
/*  46 */     func_149672_a(field_149777_j);
/*  47 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.85F, 1.0F);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  52 */     float f = 0.0625F;
/*  53 */     return AxisAlignedBB.func_72330_a(x + 0.0625F, y, z + 0.0625F, x + 1 - 0.0625F, y + 1 - 0.0625F, z + 1 - 0.0625F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/*  75 */     TileEntityCauldron cauldron = (TileEntityCauldron)BlockUtil.getTileEntity(world, x, y, z, TileEntityCauldron.class);
/*  76 */     if ((cauldron != null) && (cauldron.isBoiling())) {
/*  77 */       double yPos = 0.2D + cauldron.getPercentFilled() * 0.5D;
/*     */       
/*  79 */       int color = cauldron.getColor();
/*  80 */       if (color == -1) {
/*  81 */         color = 3432410;
/*     */       }
/*  83 */       else if (rand.nextInt(5) == 0) {
/*  84 */         world.func_72980_b(x, y, z, "witchery:random.blop", 0.8F + rand.nextFloat() * 0.2F, 0.8F + rand.nextFloat() * 0.2F, false);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  89 */       float red = (color >>> 16 & 0xFF) / 256.0F;
/*  90 */       float green = (color >>> 8 & 0xFF) / 256.0F;
/*  91 */       float blue = (color & 0xFF) / 256.0F;
/*     */       
/*  93 */       for (int i = 0; i < 2; i++) {
/*  94 */         double width = 0.6D;
/*  95 */         double xPos = 0.2D + rand.nextDouble() * 0.6D;
/*  96 */         double zPos = 0.2D + rand.nextDouble() * 0.6D;
/*     */         
/*  98 */         BubblesFX sparkle = new BubblesFX(world, x + xPos, y + yPos, z + zPos);
/*  99 */         sparkle.setScale(0.4F);
/* 100 */         if (rand.nextInt(4) == 0) {
/* 101 */           sparkle.setGravity(-0.02F);
/* 102 */           sparkle.setCanMove(true);
/* 103 */           sparkle.setMaxAge(15 + rand.nextInt(10));
/*     */         } else {
/* 105 */           sparkle.setGravity(0.0F);
/* 106 */           sparkle.setCanMove(false);
/* 107 */           sparkle.setMaxAge(10 + rand.nextInt(10));
/*     */         }
/*     */         
/* 110 */         sparkle.func_70538_b(red, green, blue);
/* 111 */         sparkle.func_82338_g(0.2F);
/* 112 */         Minecraft.func_71410_x().field_71452_i.func_78873_a(sparkle);
/*     */       }
/*     */       
/* 115 */       if (cauldron.isPowered()) {
/* 116 */         for (int i = 0; i < 1 + Math.min(cauldron.getRitualSeconds(), 5); i++) {
/* 117 */           double width = 0.4D;
/* 118 */           double xPos = 0.3D + rand.nextDouble() * 0.4D;
/* 119 */           double zPos = 0.3D + rand.nextDouble() * 0.4D;
/* 120 */           double d0 = x + xPos;
/* 121 */           double d1 = y + yPos;
/* 122 */           double d2 = z + zPos;
/*     */           
/* 124 */           NaturePowerFX sparkle = new NaturePowerFX(world, d0, d1, d2);
/* 125 */           sparkle.setCircling(cauldron.isRitualInProgress());
/* 126 */           sparkle.setScale(0.6F);
/* 127 */           sparkle.setGravity(0.25F);
/* 128 */           sparkle.setCanMove(true);
/* 129 */           double maxSpeed = 0.04D;
/* 130 */           double doubleSpeed = 0.08D;
/* 131 */           sparkle.func_70016_h(rand.nextDouble() * 0.08D - 0.04D, rand.nextDouble() * 0.05D + 0.08D, rand.nextDouble() * 0.08D - 0.04D);
/*     */           
/* 133 */           sparkle.setMaxAge(25 + rand.nextInt(cauldron.isRitualInProgress() ? 10 : 10));
/*     */           
/* 135 */           float maxColorShift = 0.2F;
/* 136 */           float doubleColorShift = maxColorShift * 2.0F;
/* 137 */           float colorshiftR = rand.nextFloat() * doubleColorShift - maxColorShift;
/* 138 */           float colorshiftG = rand.nextFloat() * doubleColorShift - maxColorShift;
/* 139 */           float colorshiftB = rand.nextFloat() * doubleColorShift - maxColorShift;
/* 140 */           sparkle.func_70538_b(red + colorshiftR, green + colorshiftG, blue + colorshiftB);
/* 141 */           sparkle.func_82338_g(0.1F);
/*     */           
/* 143 */           Minecraft.func_71410_x().field_71452_i.func_78873_a(sparkle);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 151 */     if ((!world.field_72995_K) && 
/* 152 */       ((entity instanceof EntityItem))) {
/* 153 */       TileEntityCauldron cauldron = (TileEntityCauldron)BlockUtil.getTileEntity(world, x, y, z, TileEntityCauldron.class);
/*     */       
/* 155 */       if (cauldron != null) {
/* 156 */         EntityItem itemEntity = (EntityItem)entity;
/* 157 */         if (cauldron.isFilled()) {
/* 158 */           if (Witchery.Items.GENERIC.itemGypsum.isMatch(itemEntity.func_92059_d())) {
/* 159 */             entity.func_70106_y();
/* 160 */             SoundEffect.RANDOM_FIZZ.playAt(world, x + 0.5D, y + 0.6D, z + 0.5D, 1.0F, 2.0F);
/* 161 */             ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x + 0.5D, y + 0.6D, z + 0.5D, 0.5D, 1.0D, 8);
/*     */             
/* 163 */             cauldron.drain(ForgeDirection.UNKNOWN, cauldron.getLiquidQuantity(), true);
/*     */             
/* 165 */             cauldron.markBlockForUpdate(true);
/* 166 */           } else if (Witchery.Items.GENERIC.itemQuicklime.isMatch(itemEntity.func_92059_d())) {
/* 167 */             EntityPlayer nearestPlayer = (EntityPlayer)EntityUtil.findNearestEntityWithinAABB(world, EntityPlayer.class, entity.field_70121_D.func_72314_b(5.0D, 5.0D, 5.0D), entity);
/*     */             
/* 169 */             if ((nearestPlayer != null) && (cauldron.explodeBrew(nearestPlayer))) {
/* 170 */               ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x + 0.5D, y + 0.6D, z + 0.5D, 0.5D, 1.0D, 8);
/*     */               
/* 172 */               cauldron.drain(ForgeDirection.UNKNOWN, cauldron.getLiquidQuantity(), true);
/* 173 */               entity.func_70106_y();
/* 174 */               cauldron.markBlockForUpdate(true);
/*     */             }
/*     */           }
/* 177 */           else if (cauldron.isBoiling()) {
/* 178 */             com.emoniph.witchery.brewing.action.BrewAction brewAction = WitcheryBrewRegistry.INSTANCE.getActionForItemStack(itemEntity.func_92059_d());
/*     */             
/* 180 */             if ((brewAction != null) && 
/* 181 */               (cauldron.addItem(brewAction, itemEntity.func_92059_d()))) {
/* 182 */               Item containerItem = itemEntity.func_92059_d().func_77973_b().func_77668_q();
/* 183 */               if (containerItem != null) {
/* 184 */                 EntityUtil.spawnEntityInWorld(world, new EntityItem(world, 0.5D + x, 1.0D + y, 0.5D + z, new ItemStack(containerItem)));
/*     */               }
/*     */               
/* 187 */               entity.func_70106_y();
/* 188 */               ParticleEffect.SPLASH.send(SoundEffect.WATER_SPLASH, world, x + 0.5D, y + 0.6D, z + 0.5D, 0.5D, 0.5D, 8);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149740_M()
/*     */   {
/* 201 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World world, int x, int y, int z, int side)
/*     */   {
/* 206 */     TileEntityCauldron cauldron = (TileEntityCauldron)BlockUtil.getTileEntity(world, x, y, z, TileEntityCauldron.class);
/* 207 */     int signal = 0;
/* 208 */     if (cauldron != null) {
/* 209 */       return cauldron.getRedstoneSignalStrength();
/*     */     }
/* 211 */     return signal;
/*     */   }
/*     */   
/*     */   public boolean tryFillWith(World world, int x, int y, int z, FluidStack fluidStack) {
/* 215 */     if (world.field_72995_K) {
/* 216 */       return true;
/*     */     }
/*     */     
/* 219 */     TileEntityCauldron cauldron = (TileEntityCauldron)BlockUtil.getTileEntity(world, x, y, z, TileEntityCauldron.class);
/* 220 */     if (cauldron != null) {
/* 221 */       FluidStack fluidStackToFill = new FluidStack(FluidRegistry.WATER.getID(), 1000);
/*     */       
/* 223 */       if (cauldron.canFill(ForgeDirection.UNKNOWN, fluidStack.getFluid())) {
/* 224 */         int quantity = cauldron.fill(ForgeDirection.UNKNOWN, fluidStack, true);
/* 225 */         fluidStack.amount -= quantity;
/* 226 */         if (fluidStack.amount < 0) {
/* 227 */           fluidStack.amount = 0;
/*     */         }
/* 229 */         if (quantity > 0) {
/* 230 */           SoundEffect.WATER_SWIM.playAt(world, x, y, z);
/* 231 */           cauldron.markBlockForUpdate(true);
/*     */         }
/* 233 */         return quantity > 0;
/*     */       }
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 242 */     if (world.field_72995_K) {
/* 243 */       return true;
/*     */     }
/* 245 */     TileEntityCauldron cauldron = (TileEntityCauldron)BlockUtil.getTileEntity(world, x, y, z, TileEntityCauldron.class);
/* 246 */     ItemStack heldStack = player.func_70694_bm();
/*     */     
/* 248 */     if ((cauldron != null) && (heldStack != null)) {
/* 249 */       FluidStack fluidStackToFill = FluidContainerRegistry.getFluidForFilledItem(heldStack);
/* 250 */       if (fluidStackToFill != null) {
/* 251 */         fluidStackToFill.tag = (heldStack.func_77942_o() ? (NBTTagCompound)heldStack.func_77978_p().func_74737_b() : null);
/*     */         
/* 253 */         if (cauldron.canFill(ForgeDirection.UNKNOWN, fluidStackToFill.getFluid())) {
/* 254 */           int quantityFilled = cauldron.fill(ForgeDirection.UNKNOWN, fluidStackToFill, true);
/* 255 */           if (quantityFilled != 0) {
/* 256 */             if (!player.field_71075_bZ.field_75098_d) {
/* 257 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(heldStack));
/*     */             }
/*     */             
/* 260 */             SoundEffect.WATER_SWIM.playAtPlayer(world, player);
/* 261 */             cauldron.markBlockForUpdate(true);
/*     */           }
/*     */         }
/*     */         
/* 265 */         return true; }
/* 266 */       if (heldStack.func_77973_b() == Witchery.Items.BREW_ENDLESS_WATER) {
/* 267 */         if (tryFillWith(world, x, y, z, new FluidStack(FluidRegistry.WATER, 3000)))
/*     */         {
/* 269 */           heldStack.func_77972_a(1, player);
/*     */         }
/* 271 */         return true;
/*     */       }
/*     */       
/* 274 */       FluidStack fluidStackInCauldron = cauldron.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;
/* 275 */       if (fluidStackInCauldron != null) {
/* 276 */         ItemStack filledBucketStack = FluidContainerRegistry.fillFluidContainer(fluidStackInCauldron, heldStack);
/*     */         
/* 278 */         FluidStack fluidStackToEmpty = FluidContainerRegistry.getFluidForFilledItem(filledBucketStack);
/*     */         
/* 280 */         if (fluidStackToEmpty != null) {
/* 281 */           if (fluidStackInCauldron.tag != null) {
/* 282 */             filledBucketStack.func_77982_d((NBTTagCompound)fluidStackInCauldron.tag.func_74737_b());
/*     */           }
/*     */           
/* 285 */           if (!player.field_71075_bZ.field_75098_d) {
/* 286 */             if (heldStack.field_77994_a > 1) {
/* 287 */               if (!player.field_71071_by.func_70441_a(filledBucketStack)) {
/* 288 */                 return false;
/*     */               }
/* 290 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(heldStack));
/*     */             }
/*     */             else
/*     */             {
/* 294 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(heldStack));
/*     */               
/* 296 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, filledBucketStack);
/*     */             }
/*     */           }
/*     */           
/* 300 */           cauldron.drain(ForgeDirection.UNKNOWN, fluidStackToEmpty.amount, true);
/* 301 */           SoundEffect.WATER_SWIM.playAtPlayer(world, player);
/* 302 */           world.func_147471_g(x, y, z);
/* 303 */           cauldron.markBlockForUpdate(true);
/*     */         }
/* 305 */         else if (heldStack.func_77973_b() == net.minecraft.init.Items.field_151069_bo)
/*     */         {
/* 307 */           int drainAmount = getDrainAmount(player, fluidStackInCauldron.tag);
/* 308 */           if ((drainAmount > 0) && (cauldron.isPowered()) && (cauldron.isBoiling())) {
/* 309 */             NBTTagCompound nbtFluid = (NBTTagCompound)fluidStackInCauldron.tag.func_74737_b();
/* 310 */             boolean enoughLiquid = drainAmount <= cauldron.getLiquidQuantity();
/*     */             
/* 312 */             if (enoughLiquid) {
/* 313 */               IPowerSource source = com.emoniph.witchery.common.PowerSources.findClosestPowerSource(cauldron);
/* 314 */               int power = cauldron.getPower();
/* 315 */               if ((power == 0) || ((source != null) && (source.consumePower(cauldron.getPower())))) {
/* 316 */                 cauldron.drain(ForgeDirection.UNKNOWN, Math.min(drainAmount, cauldron.getLiquidQuantity()), true);
/*     */                 
/* 318 */                 SoundEffect.WATER_SWIM.playAtPlayer(world, player);
/*     */                 
/* 320 */                 cauldron.markBlockForUpdate(true);
/* 321 */                 processSkillChanges(player, fluidStackInCauldron.tag);
/* 322 */                 ItemStack brewStack = new ItemStack(Witchery.Items.BREW);
/* 323 */                 brewStack.func_77982_d(nbtFluid);
/*     */                 
/* 325 */                 if (heldStack.field_77994_a > 1) {
/* 326 */                   if (player.field_71071_by.func_70441_a(brewStack)) {
/* 327 */                     player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(heldStack));
/*     */                     
/* 329 */                     EntityUtil.syncInventory(player);
/* 330 */                     return false;
/*     */                   }
/* 332 */                   return true;
/*     */                 }
/*     */                 
/* 335 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, consumeItem(heldStack));
/*     */                 
/* 337 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, brewStack);
/*     */                 
/* 339 */                 EntityUtil.syncInventory(player);
/* 340 */                 return true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 349 */     return super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */   public ItemStack fillBottleFromCauldron(World world, int x, int y, int z, int drainAmount) {
/* 353 */     TileEntityCauldron cauldron = (TileEntityCauldron)BlockUtil.getTileEntity(world, x, y, z, TileEntityCauldron.class);
/* 354 */     if (cauldron != null) {
/* 355 */       FluidStack fluidStackInCauldron = cauldron.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;
/* 356 */       if ((fluidStackInCauldron != null) && 
/* 357 */         (drainAmount > 0) && (cauldron.isPowered()) && (cauldron.isBoiling())) {
/* 358 */         NBTTagCompound nbtFluid = (NBTTagCompound)fluidStackInCauldron.tag.func_74737_b();
/* 359 */         boolean enoughLiquid = drainAmount <= cauldron.getLiquidQuantity();
/* 360 */         cauldron.drain(ForgeDirection.UNKNOWN, Math.min(drainAmount, cauldron.getLiquidQuantity()), true);
/*     */         
/* 362 */         SoundEffect.WATER_SWIM.playAt(world, x, y, z);
/*     */         
/* 364 */         cauldron.markBlockForUpdate(true);
/* 365 */         if (enoughLiquid)
/*     */         {
/* 367 */           ItemStack brewStack = new ItemStack(Witchery.Items.BREW);
/* 368 */           brewStack.func_77982_d(nbtFluid);
/* 369 */           return brewStack;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 374 */     return null;
/*     */   }
/*     */   
/*     */   private void processSkillChanges(EntityPlayer player, NBTTagCompound nbtBrew) {
/* 378 */     ExtendedPlayer props = ExtendedPlayer.get(player);
/* 379 */     if (props != null) {
/* 380 */       EffectLevelCounter levels = WitcheryBrewRegistry.INSTANCE.getBrewLevel(nbtBrew);
/* 381 */       int currentLevel = props.getSkillPotionBottling();
/* 382 */       if (levels.canIncreasePlayerSkill(currentLevel)) {
/* 383 */         props.increaseSkillPotionBottling();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private int getDrainAmount(EntityPlayer player, NBTTagCompound nbtFluid) {
/* 389 */     ModifierYield yieldModifier = WitcheryBrewRegistry.INSTANCE.getYieldModifier(nbtFluid);
/*     */     
/* 391 */     int[][] yieldLevels = { { 1, 3000 }, { 2, 1500 }, { 3, 1000 }, { 4, 750 }, { 5, 600 }, { 6, 500 }, { 8, 375 }, { 10, 300 }, { 15, 200 }, { 30, 100 } };
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
/* 405 */     int yield = 0;
/*     */     
/* 407 */     ExtendedPlayer props = ExtendedPlayer.get(player);
/* 408 */     if (props != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 413 */       int levelSkill = props.getSkillPotionBottling() / 30;
/*     */       
/* 415 */       PotionEffect potionEffect = player.func_70660_b(Witchery.Potions.BREWING_EXPERT);
/* 416 */       int levelPotion = potionEffect != null ? potionEffect.func_76458_c() + 1 : 0;
/*     */       
/* 418 */       int gearLevel = 0;
/* 419 */       ItemStack headItem = player.field_71071_by.func_70440_f(3);
/* 420 */       gearLevel += (Witchery.Items.WITCH_HAT.isHatWorn(player) ? 1 : 0);
/* 421 */       gearLevel += (Witchery.Items.BABAS_HAT.isHatWorn(player) ? 2 : 0);
/* 422 */       gearLevel += (Witchery.Items.WITCH_ROBES.isRobeWorn(player) ? 1 : 0);
/* 423 */       gearLevel += (Witchery.Items.NECROMANCERS_ROBES.isRobeWorn(player) ? 1 : 0);
/*     */       
/* 425 */       int familiarLevel = com.emoniph.witchery.familiar.Familiar.hasActiveBrewMasteryFamiliar(player) ? 1 : 0;
/*     */       
/* 427 */       if (levelSkill == 0) {
/* 428 */         yield = 0;
/* 429 */       } else if (levelSkill == 1) {
/* 430 */         if ((gearLevel >= 1) || (levelPotion >= 1)) {
/* 431 */           yield = 2;
/*     */         } else {
/* 433 */           yield = 1;
/*     */         }
/* 435 */       } else if (levelSkill >= 2) {
/* 436 */         if ((gearLevel >= 3) && (levelPotion >= 3) && (familiarLevel >= 1)) {
/* 437 */           yield = 9;
/* 438 */         } else if ((gearLevel >= 2) && (levelPotion >= 3) && (familiarLevel >= 1)) {
/* 439 */           yield = 8;
/* 440 */         } else if ((gearLevel >= 2) && (levelPotion >= 3)) {
/* 441 */           yield = 7;
/* 442 */         } else if ((gearLevel >= 2) && (levelPotion >= 2)) {
/* 443 */           yield = 6;
/* 444 */         } else if ((gearLevel >= 2) && (levelPotion >= 1)) {
/* 445 */           yield = 5;
/* 446 */         } else if ((gearLevel >= 2) || (levelPotion >= 1)) {
/* 447 */           yield = 4;
/* 448 */         } else if ((gearLevel >= 1) || (levelPotion >= 1)) {
/* 449 */           yield = 3;
/*     */         } else {
/* 451 */           yield = 2;
/*     */         }
/*     */       }
/*     */     }
/* 455 */     return yieldLevels[Math.max(yield - yieldModifier.getYieldModification(), 0)][1];
/*     */   }
/*     */   
/*     */   private static ItemStack consumeItem(ItemStack stack) {
/* 459 */     if (stack.field_77994_a == 1) {
/* 460 */       if (stack.func_77973_b().hasContainerItem(stack)) {
/* 461 */         return stack.func_77973_b().getContainerItem(stack);
/*     */       }
/* 463 */       return null;
/*     */     }
/*     */     
/* 466 */     stack.func_77979_a(1);
/* 467 */     return stack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockCauldron.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */