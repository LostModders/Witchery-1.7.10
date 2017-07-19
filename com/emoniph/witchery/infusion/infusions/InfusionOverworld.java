/*     */ package com.emoniph.witchery.infusion.infusions;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.ServerTickEvents.ServerTickTask;
/*     */ import com.emoniph.witchery.entity.EntityWitchProjectile;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.network.PacketPushTarget;
/*     */ import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
/*     */ import com.emoniph.witchery.util.BlockProtect;
/*     */ import com.emoniph.witchery.util.BlockSide;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.EarthItems;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*     */ 
/*     */ public class InfusionOverworld extends Infusion
/*     */ {
/*     */   public InfusionOverworld(int infusionID)
/*     */   {
/*  43 */     super(infusionID);
/*     */   }
/*     */   
/*     */   public IIcon getPowerBarIcon(EntityPlayer player, int index)
/*     */   {
/*  48 */     return Blocks.field_150346_d.func_149691_a(0, 0);
/*     */   }
/*     */   
/*     */   public void onFalling(World world, EntityPlayer player, LivingFallEvent event)
/*     */   {
/*  53 */     if (event.distance > 3.0F) {
/*  54 */       int blockX = MathHelper.func_76128_c(player.field_70165_t);
/*  55 */       int blockY = MathHelper.func_76128_c(player.field_70163_u) - 1;
/*  56 */       int blockZ = MathHelper.func_76128_c(player.field_70161_v);
/*     */       
/*  58 */       Block blockID = world.func_147439_a(blockX, blockY, blockZ);
/*  59 */       if ((blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150391_bh) || (blockID == Blocks.field_150351_n) || (blockID == Blocks.field_150354_m) || (blockID == Blocks.field_150433_aE))
/*     */       {
/*  61 */         if (player.func_70093_af()) {
/*  62 */           if (consumeCharges(world, player, 10, true)) {
/*  63 */             event.distance = 0.0F;
/*  64 */             int EXPLOSION_STRENGTH = 3;
/*  65 */             world.func_72876_a(player, player.field_70165_t, blockY + 0.5D, player.field_70161_v, 3.0F, true);
/*     */           }
/*     */         }
/*  68 */         else if (consumeCharges(world, player, 5, true)) {
/*  69 */           event.distance = 0.0F;
/*  70 */           world.func_147468_f(blockX, blockY, blockZ);
/*  71 */           ItemStack itemstack = new ItemStack(blockID, 1, 0);
/*  72 */           EntityItem blockEntity = new EntityItem(world, blockX, blockY, blockZ, itemstack);
/*  73 */           world.func_72838_d(blockEntity);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
/*     */   {
/*  83 */     if (world.field_72995_K) {
/*  84 */       return;
/*     */     }
/*     */     
/*  87 */     if ((otherEntity instanceof EntityLivingBase)) {
/*  88 */       EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
/*  89 */       int posX = MathHelper.func_76128_c(player.field_70165_t);
/*  90 */       int posY = MathHelper.func_76128_c(player.field_70163_u);
/*  91 */       int posZ = MathHelper.func_76128_c(player.field_70161_v);
/*  92 */       boolean isWearingMetalArmour = false;
/*  93 */       for (int i = 0; i < 5; i++) {
/*  94 */         ItemStack heldStack = otherLivingEntity.func_71124_b(i);
/*  95 */         if (EarthItems.instance().isMatch(heldStack)) {
/*  96 */           isWearingMetalArmour = true;
/*  97 */           break;
/*     */         }
/*     */       }
/*     */       
/* 101 */       if (isWearingMetalArmour) {
/* 102 */         double ACCELERATION = 3.0D;
/* 103 */         if (player.func_70093_af()) {
/* 104 */           if (consumeCharges(world, player, 4, true)) {
/* 105 */             Vec3 look = player.func_70040_Z();
/* 106 */             double motionX = look.field_72450_a * 0.8D * 3.0D;
/* 107 */             double motionY = 1.5D;
/* 108 */             double motionZ = look.field_72449_c * 0.8D * 3.0D;
/* 109 */             if ((otherLivingEntity instanceof EntityPlayer)) {
/* 110 */               EntityPlayer targetPlayer = (EntityPlayer)otherLivingEntity;
/* 111 */               Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, 1.5D, motionZ), targetPlayer);
/*     */             } else {
/* 113 */               otherLivingEntity.field_70159_w = motionX;
/* 114 */               otherLivingEntity.field_70181_x = 1.5D;
/* 115 */               otherLivingEntity.field_70179_y = motionZ;
/*     */             }
/*     */           }
/*     */         }
/* 119 */         else if (consumeCharges(world, player, 2, true)) {
/* 120 */           Vec3 look = player.func_70040_Z();
/* 121 */           double motionX = look.field_72450_a * 0.8D * 3.0D;
/* 122 */           double motionY = 0.30000000000000004D;
/* 123 */           double motionZ = look.field_72449_c * 0.8D * 3.0D;
/* 124 */           if ((otherLivingEntity instanceof EntityPlayer)) {
/* 125 */             EntityPlayer targetPlayer = (EntityPlayer)otherLivingEntity;
/* 126 */             Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, 0.30000000000000004D, motionZ), targetPlayer);
/*     */           } else {
/* 128 */             otherLivingEntity.field_70159_w = motionX;
/* 129 */             otherLivingEntity.field_70181_x = 0.30000000000000004D;
/* 130 */             otherLivingEntity.field_70179_y = motionZ;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 140 */     if (!world.field_72995_K) {
/* 141 */       int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/* 142 */       int seconds = elapsedTicks / 20;
/*     */       
/* 144 */       if (player.func_70093_af()) {
/* 145 */         if ((seconds >= 2) && (elapsedTicks % 4 == 0) && (consumeCharges(world, player, 1, true)))
/*     */         {
/* 147 */           int AreaOfEffect = 6;
/*     */           
/* 149 */           List entities = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(player.field_70165_t - 6.0D, player.field_70163_u - 6.0D, player.field_70161_v - 6.0D, player.field_70165_t + 6.0D, player.field_70163_u + 6.0D, player.field_70161_v + 6.0D));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 155 */           for (int i = 0; i < entities.size(); i++) {
/* 156 */             EntityItem entity = (EntityItem)entities.get(i);
/* 157 */             if (EarthItems.instance().isMatch(entity.func_92059_d())) {
/* 158 */               double d0 = 8.0D;
/* 159 */               double motionX = 0.0D;double motionY = 0.0D;double motionZ = 0.0D;
/* 160 */               double d1 = (player.field_70165_t - entity.field_70165_t) / d0;
/* 161 */               double d2 = (player.field_70163_u + player.func_70047_e() - entity.field_70163_u) / d0;
/* 162 */               double d3 = (player.field_70161_v - entity.field_70161_v) / d0;
/* 163 */               double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/* 164 */               double d5 = 2.0D;
/*     */               
/* 166 */               if (d5 > 0.0D) {
/* 167 */                 d5 *= d5;
/* 168 */                 motionX += d1 / Math.max(Math.abs(d1), 0.0D) * 1.0D;
/* 169 */                 motionY += d2 / Math.max(Math.abs(d1), 0.0D) * 1.0D;
/* 170 */                 motionZ += d3 / Math.max(Math.abs(d1), 0.0D) * 1.0D;
/*     */               }
/*     */               
/* 173 */               boolean oldClip = entity.field_70145_X;
/* 174 */               entity.field_70145_X = true;
/* 175 */               entity.func_70091_d(motionX, motionY, motionZ);
/* 176 */               entity.field_70145_X = oldClip;
/*     */             }
/*     */           }
/*     */           
/* 180 */           int AreaOfEffect2 = 6;
/*     */           
/* 182 */           for (int x = (int)player.field_70165_t - 6; x <= (int)player.field_70165_t + 6; x++) {
/* 183 */             for (int y = (int)player.field_70163_u - 3; y <= (int)player.field_70163_u + 3; y++) {
/* 184 */               for (int z = (int)player.field_70161_v - 6; z <= (int)player.field_70161_v + 6; z++) {
/* 185 */                 Block id = world.func_147439_a(x, y, z);
/* 186 */                 if (id != Blocks.field_150350_a) {
/* 187 */                   Item ingot = EarthItems.instance().oreToIngot(id);
/* 188 */                   if ((ingot != null) && (!world.field_72995_K) && (consumeCharges(world, player, 2, true))) {
/* 189 */                     world.func_147465_d(x, y, z, Blocks.field_150348_b, 0, 3);
/* 190 */                     world.func_72838_d(new EntityItem(world, x, y, z, new ItemStack(ingot)));
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 198 */       else if ((seconds >= 2) && (elapsedTicks % 20 == 0)) {
/* 199 */         playSound(world, player, "random.orb");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 207 */     if (world.field_72995_K) {
/* 208 */       return;
/*     */     }
/*     */     
/* 211 */     int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/*     */     
/* 213 */     MovingObjectPosition hit = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0D);
/* 214 */     if (hit != null) {
/* 215 */       switch (hit.field_72313_a) {
/*     */       case ENTITY: 
/* 217 */         if ((!player.func_70093_af()) && 
/* 218 */           ((hit.field_72308_g instanceof EntityLiving)) && (consumeCharges(world, player, 2, true))) {
/* 219 */           EntityLiving entity = (EntityLiving)hit.field_72308_g;
/* 220 */           ItemStack heldItem = entity.func_70694_bm();
/*     */           
/* 222 */           if ((heldItem != null) && (EarthItems.instance().isMatch(heldItem)) && 
/* 223 */             (!world.field_72995_K)) {
/* 224 */             entity.func_70099_a(heldItem, 2.0F);
/* 225 */             entity.func_70062_b(0, null);
/*     */           }
/*     */           
/*     */ 
/*     */           return;
/*     */         }
/*     */         
/*     */         break;
/*     */       case BLOCK: 
/* 234 */         int DEPTH = 3;
/* 235 */         if ((!player.func_70093_af()) && (BlockSide.TOP.isEqual(hit.field_72310_e)) && (world.func_147439_a(hit.field_72311_b, hit.field_72312_c - 9 - 1, hit.field_72309_d).func_149688_o().func_76220_a()) && (consumeCharges(world, player, 2, true)))
/*     */         {
/*     */ 
/* 238 */           for (int h = 0; h < 6; h++) {
/* 239 */             int originY = hit.field_72312_c - h;
/* 240 */             Block blockID = world.func_147439_a(hit.field_72311_b, originY, hit.field_72309_d);
/* 241 */             if (BlockProtect.canBreak(blockID, world)) {
/* 242 */               int blockMetadata = world.func_72805_g(hit.field_72311_b, originY, hit.field_72309_d);
/* 243 */               world.func_147468_f(hit.field_72311_b, originY, hit.field_72309_d);
/* 244 */               if (BlockProtect.canBreak(hit.field_72311_b, originY + 3, hit.field_72309_d, world)) {
/* 245 */                 world.func_147465_d(hit.field_72311_b, originY + 3, hit.field_72309_d, blockID, blockMetadata, 3);
/*     */               }
/*     */               
/* 248 */               AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, hit.field_72311_b + 1, hit.field_72312_c + 2, hit.field_72309_d + 1);
/* 249 */               for (Object obj : world.func_72872_a(Entity.class, bounds)) {
/* 250 */                 Entity entity = (Entity)obj;
/* 251 */                 if ((entity instanceof EntityLivingBase)) {
/* 252 */                   ((EntityLivingBase)entity).func_70634_a(entity.field_70165_t, entity.field_70163_u + 3.0D, entity.field_70161_v);
/*     */                 } else {
/* 254 */                   entity.func_70107_b(entity.field_70165_t, entity.field_70163_u + 3.0D, entity.field_70161_v);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 259 */         } else if ((!player.func_70093_af()) && (!BlockSide.BOTTOM.isEqual(hit.field_72310_e)) && (!BlockSide.TOP.isEqual(hit.field_72310_e)))
/*     */         {
/* 261 */           if ((isThrowableRock(world, hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, hit.field_72310_e)) && (consumeCharges(world, player, 3, true)))
/*     */           {
/* 263 */             world.func_147468_f(hit.field_72311_b, hit.field_72312_c, hit.field_72309_d);
/*     */             
/* 265 */             ParticleEffect.EXPLODE.send(com.emoniph.witchery.util.SoundEffect.RANDOM_EXPLODE, world, hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, 0.5D, 0.5D, 8);
/*     */             
/* 267 */             EntityWitchProjectile rockEntity = new EntityWitchProjectile(world, player, Witchery.Items.GENERIC.itemRock);
/* 268 */             rockEntity.func_70107_b(hit.field_72311_b + 0.5D, hit.field_72312_c + 0.5D, hit.field_72309_d + 0.5D);
/* 269 */             world.func_72838_d(rockEntity);
/*     */           }
/* 271 */         } else if (player.func_70093_af())
/*     */         {
/* 273 */           if (consumeCharges(world, player, 2, true)) {
/* 274 */             Block blockID = world.func_147439_a(hit.field_72311_b, hit.field_72312_c, hit.field_72309_d);
/* 275 */             Item ingot = EarthItems.instance().oreToIngot(blockID);
/* 276 */             if (ingot != null) {
/* 277 */               world.func_147465_d(hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, Blocks.field_150348_b, 0, 3);
/* 278 */               if (!world.field_72995_K)
/*     */               {
/* 280 */                 world.func_72838_d(new EntityItem(world, hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, new ItemStack(ingot, 2, 0)));
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 286 */         return;
/*     */       }
/*     */       
/*     */     }
/*     */     
/*     */ 
/* 292 */     int seconds = elapsedTicks / 20;
/*     */     
/* 294 */     if ((seconds >= 2) && (!player.func_70093_af()) && (consumeCharges(world, player, 6 * seconds, true))) {
/* 295 */       com.emoniph.witchery.common.ServerTickEvents.TASKS.add(new ShockwaveTask(player, 2 * seconds));
/*     */     } else {
/* 297 */       playFailSound(world, player);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isThrowableRock(World world, int blockX, int blockY, int blockZ, int sideHit) {
/* 302 */     Block[] blocks = { Blocks.field_150346_d, Blocks.field_150349_c, Blocks.field_150348_b, Blocks.field_150347_e, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150322_A, Blocks.field_150333_U, Blocks.field_150336_V, Blocks.field_150341_Y, Blocks.field_150349_c, Blocks.field_150446_ar, Blocks.field_150435_aG, Blocks.field_150425_aM, Blocks.field_150417_aV, Blocks.field_150389_bf, Blocks.field_150390_bg, Blocks.field_150391_bh, Blocks.field_150385_bj, Blocks.field_150387_bl, Blocks.field_150372_bz, Blocks.field_150405_ch, Blocks.field_150402_ci, Blocks.field_150424_aL };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 308 */     Block blockID = world.func_147439_a(blockX, blockY, blockZ);
/* 309 */     if (!Arrays.asList(blocks).contains(blockID)) {
/* 310 */       return false;
/*     */     }
/*     */     
/* 313 */     boolean northValid = (BlockSide.NORTH.isEqual(sideHit)) && (!world.func_147439_a(blockX + 1, blockY, blockZ).func_149688_o().func_76220_a());
/* 314 */     boolean southValid = (BlockSide.SOUTH.isEqual(sideHit)) && (!world.func_147439_a(blockX - 1, blockY, blockZ).func_149688_o().func_76220_a());
/* 315 */     boolean eastValid = (BlockSide.EAST.isEqual(sideHit)) && (!world.func_147439_a(blockX, blockY, blockZ + 1).func_149688_o().func_76220_a());
/* 316 */     boolean westValid = (BlockSide.WEST.isEqual(sideHit)) && (!world.func_147439_a(blockX, blockY, blockZ - 1).func_149688_o().func_76220_a());
/*     */     
/* 318 */     return (northValid) || (southValid) || (eastValid) || (westValid);
/*     */   }
/*     */   
/*     */   private static class ShockwaveTask
/*     */     extends ServerTickEvents.ServerTickTask
/*     */   {
/*     */     final Coord center;
/*     */     final EntityPlayer creator;
/*     */     final int maxRadius;
/* 327 */     final int MIN_RADIUS = 2;
/*     */     
/* 329 */     int stage = 0;
/*     */     
/*     */     public ShockwaveTask(EntityPlayer creator, int maxRadius) {
/* 332 */       super();
/* 333 */       this.center = new Coord((int)creator.field_70165_t, (int)creator.field_70163_u - 1, (int)creator.field_70161_v);
/* 334 */       this.creator = creator;
/* 335 */       this.maxRadius = (maxRadius + 2);
/*     */     }
/*     */     
/*     */     public boolean process()
/*     */     {
/* 340 */       this.stage += 1;
/*     */       
/* 342 */       Block centerBlock = this.center.getBlock(this.world);
/*     */       
/* 344 */       if (this.stage == 1) {
/* 345 */         drawCircle(this.world, this.center.x, this.center.y, this.center.z, this.stage + 2, 2, 1);
/*     */       } else {
/* 347 */         drawCircle(this.world, this.center.x, this.center.y + 2, this.center.z, this.stage + 2, 2, -1);
/* 348 */         drawCircle(this.world, this.center.x, this.center.y + 1, this.center.z, this.stage + 2 - 1, 2, -1);
/*     */       }
/*     */       
/* 351 */       if (this.stage < this.maxRadius) {
/* 352 */         drawCircle(this.world, this.center.x, this.center.y, this.center.z, this.stage + 2 + 1, 2, 2);
/*     */       } else {
/* 354 */         drawCircle(this.world, this.center.x, this.center.y + 1, this.center.z, this.stage + 2, 2, -1);
/*     */       }
/*     */       
/* 357 */       int r = this.stage + 2;
/*     */       
/* 359 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.center.x - r, this.center.y + 1, this.center.z - r, this.center.x + r, this.center.y + 3, this.center.z + r);
/* 360 */       for (Object obj : this.world.func_72872_a(EntityLivingBase.class, bounds)) {
/* 361 */         EntityLivingBase entity = (EntityLivingBase)obj;
/* 362 */         Coord position = new Coord(entity);
/* 363 */         double dist = this.center.distanceTo(position);
/* 364 */         if ((dist <= r + 1) && (dist >= r)) {
/* 365 */           entity.func_70097_a(DamageSource.func_76365_a(this.creator), 8.0F);
/* 366 */           RiteProtectionCircleRepulsive.push(this.world, entity, this.center.x, this.center.y, this.center.z);
/*     */         }
/*     */       }
/*     */       
/* 370 */       return this.stage == this.maxRadius;
/*     */     }
/*     */     
/*     */     protected void drawCircle(World world, int x0, int y0, int z0, int radius, int blocksToMove, int direction) {
/* 374 */       int x = radius;
/* 375 */       int z = 0;
/* 376 */       int radiusError = 1 - x;
/*     */       
/* 378 */       while (x >= z) {
/* 379 */         drawPixel(world, x + x0, y0, z + z0, blocksToMove, direction);
/* 380 */         drawPixel(world, z + x0, y0, x + z0, blocksToMove, direction);
/* 381 */         drawPixel(world, -x + x0, y0, z + z0, blocksToMove, direction);
/* 382 */         drawPixel(world, -z + x0, y0, x + z0, blocksToMove, direction);
/* 383 */         drawPixel(world, -x + x0, y0, -z + z0, blocksToMove, direction);
/* 384 */         drawPixel(world, -z + x0, y0, -x + z0, blocksToMove, direction);
/* 385 */         drawPixel(world, x + x0, y0, -z + z0, blocksToMove, direction);
/* 386 */         drawPixel(world, z + x0, y0, -x + z0, blocksToMove, direction);
/*     */         
/* 388 */         z++;
/* 389 */         if (radiusError < 0) {
/* 390 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 392 */           x--;
/* 393 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawPixel(World world, int x, int y, int z, int blocksToMove, int direction) {
/* 399 */       if (direction > 0) {
/* 400 */         if ((world.func_147437_c(x, y - blocksToMove + 1, z)) || (world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a())) {
/* 401 */           return;
/*     */         }
/* 403 */         for (int i = 0; i < blocksToMove; i++) {
/* 404 */           Block blockID = world.func_147439_a(x, y - i, z);
/*     */           
/* 406 */           int blockMetadata = world.func_72805_g(x, y - i, z);
/*     */           
/* 408 */           if (BlockProtect.canBreak(blockID, world)) {
/* 409 */             world.func_147468_f(x, y - i, z);
/*     */           }
/* 411 */           if (BlockProtect.canBreak(x, y - i + direction, z, world)) {
/* 412 */             world.func_147465_d(x, y - i + direction, z, blockID, blockMetadata, 3);
/*     */           }
/*     */         }
/*     */       } else {
/* 416 */         if ((world.func_147437_c(x, y, z)) || (world.func_147439_a(x, y + direction - 1, z).func_149688_o().func_76220_a())) {
/* 417 */           return;
/*     */         }
/* 419 */         for (int i = blocksToMove - 1; i >= 0; i--) {
/* 420 */           Block blockID = world.func_147439_a(x, y - i, z);
/*     */           
/* 422 */           int blockMetadata = world.func_72805_g(x, y - i, z);
/* 423 */           if (BlockProtect.canBreak(blockID, world)) {
/* 424 */             world.func_147468_f(x, y - i, z);
/*     */           }
/* 426 */           if (BlockProtect.canBreak(x, y - i + direction, z, world)) {
/* 427 */             world.func_147465_d(x, y - i + direction, z, blockID, blockMetadata, 3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/InfusionOverworld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */