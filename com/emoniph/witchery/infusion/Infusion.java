/*      */ package com.emoniph.witchery.infusion;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.common.ExtendedPlayer;
/*      */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*      */ import com.emoniph.witchery.dimension.WorldProviderTorment;
/*      */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*      */ import com.emoniph.witchery.entity.EntityDemon;
/*      */ import com.emoniph.witchery.entity.EntityIllusion;
/*      */ import com.emoniph.witchery.entity.EntityIllusionCreeper;
/*      */ import com.emoniph.witchery.entity.EntityIllusionSpider;
/*      */ import com.emoniph.witchery.entity.EntityIllusionZombie;
/*      */ import com.emoniph.witchery.entity.EntityNightmare;
/*      */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*      */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*      */ import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
/*      */ import com.emoniph.witchery.familiar.Familiar;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemHunterClothes;
/*      */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.network.PacketPlayerStyle;
/*      */ import com.emoniph.witchery.network.PacketPlayerSync;
/*      */ import com.emoniph.witchery.predictions.PredictionManager;
/*      */ import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
/*      */ import com.emoniph.witchery.util.ChatUtil;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.Dye;
/*      */ import com.emoniph.witchery.util.Log;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.TimeUtil;
/*      */ import cpw.mods.fml.common.eventhandler.Event.Result;
/*      */ import cpw.mods.fml.common.eventhandler.EventPriority;
/*      */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.boss.IBossDisplayData;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityCreeper;
/*      */ import net.minecraft.entity.monster.EntityGolem;
/*      */ import net.minecraft.entity.monster.EntityWitch;
/*      */ import net.minecraft.entity.monster.EntityZombie;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.passive.EntityVillager;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.entity.player.PlayerCapabilities;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemDye;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ import net.minecraftforge.event.ServerChatEvent;
/*      */ import net.minecraftforge.event.entity.living.EnderTeleportEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
/*      */ import net.minecraftforge.event.entity.player.FillBucketEvent;
/*      */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*      */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Infusion
/*      */ {
/*      */   public static class EventHooks
/*      */   {
/*      */     private boolean isBannedSpiritObject(ItemStack stack)
/*      */     {
/*  168 */       if (stack != null) {
/*  169 */         Item item = stack.func_77973_b();
/*  170 */         return (item == Items.field_151079_bi) || (item == Items.field_151065_br);
/*      */       }
/*  172 */       return false;
/*      */     }
/*      */     
/*      */     @SubscribeEvent(priority=EventPriority.NORMAL)
/*      */     public void onEnderTeleport(EnderTeleportEvent event)
/*      */     {
/*  178 */       if ((!event.isCanceled()) && (event.entityLiving != null) && (!event.entityLiving.field_70170_p.field_72995_K) && 
/*  179 */         ((event.entityLiving instanceof EntityPlayer)) && (ItemHunterClothes.isFullSetWorn(event.entityLiving, false)))
/*      */       {
/*  181 */         event.setCanceled(true);
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent(priority=EventPriority.NORMAL)
/*      */     public void FillBucket(FillBucketEvent event)
/*      */     {
/*  188 */       ItemStack result = attemptFill(event.world, event.target);
/*  189 */       if (result != null) {
/*  190 */         event.result = result;
/*  191 */         event.setResult(Event.Result.ALLOW);
/*      */       }
/*      */     }
/*      */     
/*      */     private ItemStack attemptFill(World world, MovingObjectPosition p) {
/*  196 */       Block id = world.func_147439_a(p.field_72311_b, p.field_72312_c, p.field_72309_d);
/*      */       
/*  198 */       if (id == Witchery.Blocks.FLOWING_SPIRIT) {
/*  199 */         if (world.func_72805_g(p.field_72311_b, p.field_72312_c, p.field_72309_d) == 0) {
/*  200 */           world.func_147449_b(p.field_72311_b, p.field_72312_c, p.field_72309_d, Blocks.field_150350_a);
/*  201 */           return new ItemStack(Witchery.Items.BUCKET_FLOWINGSPIRIT);
/*      */         }
/*  203 */       } else if ((id == Witchery.Blocks.HOLLOW_TEARS) && 
/*  204 */         (world.func_72805_g(p.field_72311_b, p.field_72312_c, p.field_72309_d) == 0)) {
/*  205 */         world.func_147449_b(p.field_72311_b, p.field_72312_c, p.field_72309_d, Blocks.field_150350_a);
/*  206 */         return new ItemStack(Witchery.Items.BUCKET_HOLLOWTEARS);
/*      */       }
/*      */       
/*      */ 
/*  210 */       return null;
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onLivingDamage(LivingHurtEvent event) {
/*  215 */       if ((event.entityLiving != null) && (event.entityLiving.field_70170_p != null) && (!event.entityLiving.field_70170_p.field_72995_K) && ((event.entityLiving instanceof EntityPlayer)) && (!event.isCanceled()))
/*      */       {
/*      */ 
/*  218 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/*  219 */         PredictionManager.instance().checkIfFulfilled(player, event);
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onServerChat(ServerChatEvent event)
/*      */     {
/*  226 */       if ((event.player != null) && (!event.isCanceled()) && (!event.player.field_70170_p.field_72995_K) && (event.message != null))
/*      */       {
/*  228 */         Witchery.Items.RUBY_SLIPPERS.trySayTheresNoPlaceLikeHome(event.player, event.message);
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
/*  234 */       if ((event.harvester != null) && (event.harvester.field_70170_p != null) && (!event.harvester.field_70170_p.field_72995_K)) {
/*  235 */         PredictionManager.instance().checkIfFulfilled(event.harvester, event);
/*  236 */         PlayerEffects.onHarvestDrops(event.harvester, event);
/*  237 */         EntityAIDigBlocks.onHarvestDrops(event.harvester, event);
/*      */       }
/*      */       
/*  240 */       if ((!event.world.field_72995_K) && (event.world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) && (!event.isCanceled()))
/*      */       {
/*  242 */         Iterator<ItemStack> iterator = event.drops.iterator();
/*  243 */         while (iterator.hasNext()) {
/*  244 */           ItemStack stack = (ItemStack)iterator.next();
/*  245 */           if ((stack != null) && (isBannedSpiritObject(stack))) {
/*  246 */             iterator.remove();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onPlayerInteract(PlayerInteractEvent event) {
/*  254 */       if ((event.entityLiving != null) && (event.entityLiving.field_70170_p != null) && (!event.entityLiving.field_70170_p.field_72995_K) && ((event.entityLiving instanceof EntityPlayer)) && (!event.isCanceled()))
/*      */       {
/*      */ 
/*  257 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/*  258 */         PredictionManager.instance().checkIfFulfilled(player, event);
/*  259 */         PlayerEffects.onInteract(player, event);
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  265 */       long counter = event.entityLiving.field_70170_p.func_82737_E();
/*      */       
/*  267 */       if ((event.entityLiving instanceof EntityPlayer)) {
/*  268 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/*      */         
/*  270 */         if (!event.entityLiving.field_70170_p.field_72995_K) {
/*  271 */           long time = TimeUtil.getServerTimeInTicks();
/*  272 */           if (counter % 4L == 0L) {
/*  273 */             NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  274 */             handleBrewGrotesqueEffect(player, nbtPlayer);
/*      */             
/*  276 */             WorldProviderDreamWorld.updatePlayerEffects(player.field_70170_p, player, nbtPlayer, time, counter);
/*      */             
/*  278 */             WorldProviderTorment.updatePlayerEffects(player.field_70170_p, player, nbtPlayer, time, counter);
/*      */             
/*      */ 
/*  281 */             if (counter % 20L == 0L) {
/*  282 */               handleSyncEffects(player, nbtPlayer);
/*  283 */               handleBrewDepthsEffect(player, nbtPlayer);
/*  284 */               handleCurseEffects(player, nbtPlayer);
/*  285 */               handleSeepingShoesEffect(player, nbtPlayer);
/*  286 */               InfusedBrewEffect.checkActiveEffects(player.field_70170_p, player, nbtPlayer, counter % 1200L == 0L, time);
/*      */             }
/*      */             
/*      */ 
/*  290 */             if ((counter % 100L == 0L) && (!event.isCanceled())) {
/*  291 */               PredictionManager.instance().checkIfFulfilled(player, event);
/*      */               
/*  293 */               if ((Config.instance().allowCovenWitchVisits) && (nbtPlayer.func_74764_b("WITCCoven")) && (player.field_70170_p.field_73012_v.nextInt(20) == 0))
/*      */               {
/*  295 */                 ChunkCoordinates coords = player.getBedLocation(player.field_71093_bK);
/*  296 */                 if ((coords != null) && (coords.func_71569_e((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v) < 256.0F))
/*      */                 {
/*      */ 
/*  299 */                   NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*      */                   
/*  301 */                   if (nbtCovenList.func_74745_c() > 0) {
/*  302 */                     EntityCovenWitch.summonCovenMember(player.field_70170_p, player, 90);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  309 */           PlayerEffects.onUpdate(player, time);
/*      */           
/*  311 */           if (counter % 100L == 1L) {
/*  312 */             EntityWitchHunter.handleWitchHunterEffects(player, time);
/*      */           }
/*      */         }
/*      */         
/*  316 */         handleIcySlippersEffect(player);
/*      */         
/*  318 */         handleFamiliarFollowerSync(player);
/*      */ 
/*      */       }
/*  321 */       else if ((!event.entityLiving.field_70170_p.field_72995_K) && 
/*  322 */         (counter % 20L == 0L)) {
/*  323 */         handleCurseEffects(event.entityLiving, event.entityLiving.getEntityData());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  328 */       if (counter % 100L == 0L) {
/*  329 */         ItemStack belt = event.entityLiving.func_71124_b(2);
/*  330 */         if ((belt != null) && (belt.func_77973_b() == Witchery.Items.BARK_BELT)) {
/*  331 */           Block blockID = event.entityLiving.field_70170_p.func_147439_a(MathHelper.func_76128_c(event.entityLiving.field_70165_t), MathHelper.func_76128_c(event.entityLiving.field_70163_u) - 1, MathHelper.func_76128_c(event.entityLiving.field_70161_v));
/*      */           
/*      */ 
/*      */ 
/*  335 */           if ((blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150391_bh)) {
/*  336 */             int maxChargeLevel = Witchery.Items.BARK_BELT.getMaxChargeLevel(event.entityLiving);
/*  337 */             int currentChargeLevel = Witchery.Items.BARK_BELT.getChargeLevel(belt);
/*  338 */             if (currentChargeLevel < maxChargeLevel) {
/*  339 */               Witchery.Items.BARK_BELT.setChargeLevel(belt, Math.min(currentChargeLevel + 1, maxChargeLevel));
/*      */               
/*  341 */               event.entityLiving.field_70170_p.func_72956_a(event.entityLiving, "witchery:random.wood_creak", 0.5F, (float)(0.8D + 2.0D * event.entityLiving.field_70170_p.field_73012_v.nextGaussian()));
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private void handleSeepingShoesEffect(EntityPlayer player, NBTTagCompound nbtTag)
/*      */     {
/*  352 */       if (!player.field_70122_E) {
/*  353 */         return;
/*      */       }
/*      */       
/*  356 */       if ((!player.func_70644_a(Potion.field_76436_u)) && (!player.func_70644_a(Potion.field_82731_v))) {
/*  357 */         return;
/*      */       }
/*      */       
/*  360 */       ItemStack shoes = player.func_71124_b(1);
/*  361 */       if ((shoes == null) || (shoes.func_77973_b() != Witchery.Items.SEEPING_SHOES)) {
/*  362 */         return;
/*      */       }
/*      */       
/*  365 */       boolean poisonRemoved = false;
/*  366 */       if (player.func_70644_a(Potion.field_76436_u)) {
/*  367 */         player.func_82170_o(Potion.field_76436_u.field_76415_H);
/*  368 */         poisonRemoved = true;
/*      */       }
/*      */       
/*  371 */       if (player.func_70644_a(Potion.field_82731_v)) {
/*  372 */         player.func_82170_o(Potion.field_82731_v.field_76415_H);
/*  373 */         poisonRemoved = true;
/*      */       }
/*      */       
/*  376 */       if (poisonRemoved) {
/*  377 */         int x = MathHelper.func_76128_c(player.field_70165_t);
/*  378 */         int z = MathHelper.func_76128_c(player.field_70161_v);
/*  379 */         int y = MathHelper.func_76128_c(player.field_70163_u);
/*  380 */         int RADIUS = 3;
/*  381 */         int RADIUS_SQ = 9;
/*  382 */         for (int dx = x - 3; dx <= x + 3; dx++) {
/*  383 */           for (int dz = z - 3; dz <= z + 3; dz++) {
/*  384 */             for (int dy = y - 1; dy <= y + 1; dy++) {
/*  385 */               if ((Coord.distanceSq(dx, 1.0D, dy, x, 1.0D, dy) <= 9.0D) && (player.field_70170_p.func_147437_c(dx, dy + 1, dz)) && (!player.field_70170_p.func_147437_c(dx, dy, dz)))
/*      */               {
/*      */ 
/*  388 */                 ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), player.field_70170_p, dx, dy, dz, player);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void handleSyncEffects(EntityPlayer player, NBTTagCompound nbtPlayer)
/*      */     {
/*  398 */       if ((!player.field_70170_p.field_72995_K) && (nbtPlayer.func_74764_b("WITCResyncLook"))) {
/*  399 */         long nextSync = nbtPlayer.func_74763_f("WITCResyncLook");
/*  400 */         if (nextSync <= MinecraftServer.func_130071_aq()) {
/*  401 */           nbtPlayer.func_82580_o("WITCResyncLook");
/*  402 */           Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), player.field_71093_bK);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void handleFamiliarFollowerSync(EntityPlayer player) {
/*  408 */       if (!player.field_70170_p.field_72995_K) {
/*  409 */         NBTTagCompound compound = player.getEntityData();
/*  410 */         if (compound.func_74764_b("WITC_LASTPOS")) {
/*  411 */           NBTTagCompound pos = compound.func_74775_l("WITC_LASTPOS");
/*  412 */           int lastDimension = pos.func_74762_e("D");
/*  413 */           if ((lastDimension != player.field_71093_bK) || (Math.abs(pos.func_74769_h("X") - player.field_70165_t) > 32.0D) || (Math.abs(pos.func_74769_h("Z") - player.field_70161_v) > 32.0D))
/*      */           {
/*      */ 
/*  416 */             if (((lastDimension != player.field_71093_bK) && (player.field_71093_bK == -1)) || (lastDimension == -1)) {
/*  417 */               NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  418 */               nbtPlayer.func_74757_a("WITCVisitedNether", true);
/*      */             }
/*      */             
/*  421 */             if (Familiar.hasActiveFamiliar(player)) {
/*  422 */               EntityTameable familiar = Familiar.getFamiliarEntity(player);
/*  423 */               if ((familiar != null) && (!familiar.func_70906_o())) {
/*  424 */                 int ipx = MathHelper.func_76128_c(player.field_70165_t) - 2;
/*  425 */                 int j = MathHelper.func_76128_c(player.field_70161_v) - 2;
/*  426 */                 int k = MathHelper.func_76128_c(player.field_70121_D.field_72338_b) - 2;
/*      */                 
/*  428 */                 boolean done = false;
/*  429 */                 for (int l = 0; (l <= 4) && (!done); l++) {
/*  430 */                   for (int i1 = 0; (i1 <= 4) && (!done); i1++) {
/*  431 */                     for (int dy = 0; (dy <= 4) && (!done); dy++) {
/*  432 */                       if ((player.field_70170_p.func_147439_a(ipx + l, k + dy - 1, j + i1).isSideSolid(player.field_70170_p, ipx + l, k + dy - 1, j + i1, ForgeDirection.UP)) && (!player.field_70170_p.func_147439_a(ipx + l, k + dy, j + i1).func_149721_r()) && (!player.field_70170_p.func_147439_a(ipx + l, k + dy + 1, j + i1).func_149721_r()))
/*      */                       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  440 */                         ItemGeneral.teleportToLocation(player.field_70170_p, 0.5D + ipx + l, k + dy, 0.5D + j + i1, player.field_71093_bK, familiar, true);
/*      */                         
/*      */ 
/*      */ 
/*  444 */                         done = true;
/*      */                       }
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  453 */           pos.func_74780_a("X", player.field_70165_t);
/*  454 */           pos.func_74780_a("Z", player.field_70161_v);
/*  455 */           pos.func_74768_a("D", player.field_71093_bK);
/*      */         } else {
/*  457 */           NBTTagCompound pos = new NBTTagCompound();
/*  458 */           pos.func_74780_a("X", player.field_70165_t);
/*  459 */           pos.func_74780_a("Z", player.field_70161_v);
/*  460 */           pos.func_74768_a("D", player.field_71093_bK);
/*  461 */           pos.func_74757_a("visitedNether", player.field_71093_bK == -1);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void handleIcySlippersEffect(EntityPlayer player) {
/*  467 */       ItemStack shoes = player.func_82169_q(0);
/*  468 */       if ((shoes != null) && (shoes.func_77973_b() == Witchery.Items.ICY_SLIPPERS)) {
/*  469 */         int k = MathHelper.func_76128_c(player.field_70163_u - 1.0D);
/*  470 */         for (int i = 0; i < 4; i++) {
/*  471 */           int j = MathHelper.func_76128_c(player.field_70165_t + (i % 2 * 2 - 1) * 0.5F);
/*  472 */           int l = MathHelper.func_76128_c(player.field_70161_v + (i / 2 % 2 * 2 - 1) * 0.5F);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  477 */           Block blockID = player.field_70170_p.func_147439_a(j, k, l);
/*  478 */           if ((blockID == Blocks.field_150358_i) || (blockID == Blocks.field_150355_j)) {
/*  479 */             player.field_70170_p.func_147449_b(j, k, l, Blocks.field_150432_aD);
/*  480 */           } else if ((blockID == Blocks.field_150356_k) || (blockID == Blocks.field_150353_l)) {
/*  481 */             player.field_70170_p.func_147449_b(j, k, l, Blocks.field_150343_Z);
/*  482 */             if (player.field_70170_p.field_73012_v.nextInt(10) == 0) {
/*  483 */               shoes.func_77972_a(1, player);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void handleBrewDepthsEffect(EntityPlayer player, NBTTagCompound nbtTag) {
/*  491 */       if (nbtTag.func_74764_b("witcheryDepths")) {
/*  492 */         int timeLeft = nbtTag.func_74762_e("witcheryDepths");
/*  493 */         if (timeLeft > 0) {
/*  494 */           if (!player.func_70644_a(Potion.field_76427_o)) {
/*  495 */             player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 6000));
/*      */           }
/*  497 */           if (!player.func_70055_a(Material.field_151586_h)) {
/*  498 */             if (!player.func_70644_a(Potion.field_82731_v)) {
/*  499 */               player.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100, 1));
/*      */             }
/*      */           }
/*  502 */           else if (player.func_70644_a(Potion.field_82731_v)) {
/*  503 */             player.func_82170_o(Potion.field_82731_v.field_76415_H);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  508 */         timeLeft--; if (timeLeft <= 0) {
/*  509 */           nbtTag.func_82580_o("witcheryDepths");
/*  510 */           if (player.func_70644_a(Potion.field_76427_o)) {
/*  511 */             player.func_82170_o(Potion.field_76427_o.field_76415_H);
/*      */           }
/*  513 */           if (player.func_70644_a(Potion.field_76436_u)) {
/*  514 */             player.func_82170_o(Potion.field_76436_u.field_76415_H);
/*      */           }
/*      */         } else {
/*  517 */           nbtTag.func_74768_a("witcheryDepths", timeLeft);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void handleBrewGrotesqueEffect(EntityPlayer player, NBTTagCompound nbtTag) {
/*  523 */       if (nbtTag.func_74764_b("witcheryGrotesque")) {
/*  524 */         int timeLeft = nbtTag.func_74762_e("witcheryGrotesque");
/*  525 */         Iterator iterator; if (timeLeft > 0) {
/*  526 */           float radius = 4.0F;
/*  527 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 4.0D, player.field_70163_u - 4.0D, player.field_70161_v - 4.0D, player.field_70165_t + 4.0D, player.field_70163_u + 4.0D, player.field_70161_v + 4.0D);
/*      */           
/*      */ 
/*  530 */           List list = player.field_70170_p.func_72872_a(EntityLiving.class, bounds);
/*      */           
/*  532 */           for (iterator = list.iterator(); iterator.hasNext();) {
/*  533 */             EntityLiving entity = (EntityLiving)iterator.next();
/*  534 */             boolean victim = (!(entity instanceof EntityDemon)) && (!(entity instanceof IBossDisplayData)) && (!(entity instanceof EntityGolem)) && (!(entity instanceof EntityWitch));
/*      */             
/*  536 */             if ((victim) && (Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, player.field_70165_t, player.field_70163_u, player.field_70161_v) < 4.0D))
/*      */             {
/*      */ 
/*  539 */               RiteProtectionCircleRepulsive.push(player.field_70170_p, entity, player.field_70165_t, player.field_70163_u, player.field_70161_v);
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  545 */         timeLeft--; if (timeLeft <= 0) {
/*  546 */           nbtTag.func_82580_o("witcheryGrotesque");
/*  547 */           Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), player.field_71093_bK);
/*      */         } else {
/*  549 */           nbtTag.func_74768_a("witcheryGrotesque", timeLeft);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void handleCurseEffects(EntityLivingBase entity, NBTTagCompound nbtTag) {
/*  555 */       if ((entity != null) && (nbtTag != null)) {
/*  556 */         if ((!(entity instanceof EntityPlayer)) && (nbtTag.func_74764_b("witcherySinking"))) {
/*  557 */           int level = nbtTag.func_74762_e("witcherySinking");
/*  558 */           if (level > 0) {
/*  559 */             if ((entity.func_70090_H()) || (((entity instanceof EntityPlayer)) && (!entity.field_70122_E))) {
/*  560 */               if (entity.field_70181_x < 0.0D) {
/*  561 */                 entity.field_70181_x *= (1.0D + Math.min(0.1D * level, 0.4D));
/*  562 */               } else if (entity.field_70181_x > 0.0D) {
/*  563 */                 entity.field_70181_x *= (1.0D - Math.min(0.1D * level, 0.4D));
/*      */               }
/*      */             }
/*      */           } else {
/*  567 */             nbtTag.func_82580_o("witcherySinking");
/*      */           }
/*      */         }
/*      */         
/*  571 */         if (nbtTag.func_74764_b("witcheryCursed")) {
/*  572 */           int level = nbtTag.func_74762_e("witcheryCursed");
/*  573 */           if (level > 0) {
/*  574 */             if ((!entity.func_82165_m(Potion.field_76440_q.field_76415_H)) && (!entity.func_82165_m(Potion.field_76437_t.field_76415_H)) && (!entity.func_82165_m(Potion.field_76419_f.field_76415_H)) && (!entity.func_82165_m(Potion.field_76421_d.field_76415_H)) && (!entity.func_82165_m(Potion.field_76436_u.field_76415_H)))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*  579 */               if (entity.field_70170_p.field_73012_v.nextInt(20) == 0) {
/*  580 */                 switch (entity.field_70170_p.field_73012_v.nextInt(level >= 2 ? 3 : level >= 3 ? 4 : level >= 4 ? 5 : level >= 5 ? 6 : 2))
/*      */                 {
/*      */                 case 0: 
/*  583 */                   entity.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 600, Math.min(level - 1, 4)));
/*      */                   
/*  585 */                   break;
/*      */                 case 1: 
/*  587 */                   entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 600, Math.min(level - 1, 4)));
/*      */                   
/*  589 */                   break;
/*      */                 case 2: 
/*  591 */                   entity.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, (13 + 2 * level) * 20, Math.min(level - 2, 4)));
/*      */                   
/*  593 */                   break;
/*      */                 case 3: 
/*  595 */                   entity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 5 * level * 20));
/*      */                   
/*  597 */                   if (level > 5) {
/*  598 */                     entity.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 5 * level * 20));
/*      */                   }
/*      */                   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   break;
/*      */                 case 5: 
/*  610 */                   if ((entity instanceof EntityPlayer)) {
/*  611 */                     EntityPlayer player = (EntityPlayer)entity;
/*  612 */                     int heldItemIndex = player.field_71071_by.field_70461_c;
/*  613 */                     if (player.field_71071_by.field_70462_a[heldItemIndex] != null) {
/*  614 */                       player.func_71019_a(player.field_71071_by.field_70462_a[heldItemIndex], true);
/*      */                       
/*  616 */                       player.field_71071_by.field_70462_a[heldItemIndex] = null;
/*      */                     }
/*      */                   } else {
/*  619 */                     ItemStack heldItem = entity.func_70694_bm();
/*  620 */                     if (heldItem != null) {
/*  621 */                       Infusion.dropEntityItemWithRandomChoice(entity, heldItem, true);
/*  622 */                       entity.func_70062_b(0, null);
/*      */                     }
/*      */                   }
/*      */                   break;
/*      */                 }
/*      */               }
/*      */             }
/*      */           } else {
/*  630 */             nbtTag.func_82580_o("witcheryCursed");
/*      */           }
/*      */         }
/*      */         
/*  634 */         if (nbtTag.func_74764_b("witcheryOverheating")) {
/*  635 */           int level = nbtTag.func_74762_e("witcheryOverheating");
/*  636 */           if (level > 0) {
/*  637 */             World world = entity.field_70170_p;
/*  638 */             if (!entity.func_70027_ad()) if (world.field_73012_v.nextInt(level > 1 ? 25 : level > 2 ? 20 : 30) == 0) {
/*  639 */                 int x = MathHelper.func_76128_c(entity.field_70165_t);
/*  640 */                 int z = MathHelper.func_76128_c(entity.field_70161_v);
/*  641 */                 BiomeGenBase biome = world.func_72807_a(x, z);
/*  642 */                 if ((biome.field_76750_F >= 1.5D) && ((!biome.func_76738_d()) || (!world.func_72896_J())) && (!entity.func_70090_H()))
/*      */                 {
/*  644 */                   entity.func_70015_d(Math.min(world.field_73012_v.nextInt(level < 4 ? 2 : level - 1) + 1, 4));
/*      */                 }
/*      */               }
/*      */           } else {
/*  648 */             nbtTag.func_82580_o("witcheryOverheating");
/*      */           }
/*      */         }
/*      */         
/*  652 */         if ((nbtTag.func_74764_b("witcheryWakingNightmare")) && ((entity instanceof EntityPlayer))) {
/*  653 */           EntityPlayer player = (EntityPlayer)entity;
/*  654 */           int level = nbtTag.func_74762_e("witcheryWakingNightmare");
/*  655 */           if ((level > 0) && (player.field_71093_bK != Config.instance().dimensionDreamID)) {
/*  656 */             World world = player.field_70170_p;
/*  657 */             if (world.field_73012_v.nextInt(level > 2 ? 60 : level > 4 ? 30 : 180) == 0) {
/*  658 */               double R = 16.0D;
/*  659 */               double H = 8.0D;
/*  660 */               AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(entity.field_70165_t - 16.0D, entity.field_70163_u - 8.0D, entity.field_70161_v - 16.0D, entity.field_70165_t + 16.0D, entity.field_70163_u + 8.0D, entity.field_70161_v + 16.0D);
/*      */               
/*  662 */               List entities = world.func_72872_a(EntityNightmare.class, bounds);
/*  663 */               boolean doNothing = false;
/*  664 */               for (Object obj : entities) {
/*  665 */                 EntityNightmare nightmare = (EntityNightmare)obj;
/*  666 */                 if (nightmare.getVictimName().equalsIgnoreCase(player.func_70005_c_())) {
/*  667 */                   doNothing = true;
/*  668 */                   break;
/*      */                 }
/*      */               }
/*  671 */               if (!doNothing) {
/*  672 */                 Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), player, 2, 6);
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  679 */             nbtTag.func_82580_o("witcheryWakingNightmare");
/*      */           }
/*      */         }
/*      */         
/*  683 */         if (((entity instanceof EntityPlayer)) && (nbtTag.func_74764_b("witcheryInsanity"))) {
/*  684 */           int level = nbtTag.func_74762_e("witcheryInsanity");
/*  685 */           if (level > 0) {
/*  686 */             World world = entity.field_70170_p;
/*  687 */             int x = MathHelper.func_76128_c(entity.field_70165_t);
/*  688 */             int y = MathHelper.func_76128_c(entity.field_70163_u);
/*  689 */             int z = MathHelper.func_76128_c(entity.field_70161_v);
/*  690 */             if (world.field_73012_v.nextInt(level > 1 ? 30 : level > 2 ? 25 : 35) == 0) {
/*  691 */               Class<? extends EntityCreature> creatureType = null;
/*  692 */               switch (world.field_73012_v.nextInt(3)) {
/*      */               case 0: 
/*      */               default: 
/*  695 */                 creatureType = EntityIllusionCreeper.class;
/*  696 */                 break;
/*      */               case 1: 
/*  698 */                 creatureType = EntityIllusionSpider.class;
/*  699 */                 break;
/*      */               case 2: 
/*  701 */                 creatureType = EntityIllusionZombie.class;
/*      */               }
/*      */               
/*  704 */               int MAX_DISTANCE = 9;
/*  705 */               int MIN_DISTANCE = 4;
/*  706 */               Infusion.spawnCreature(world, creatureType, x, y, z, (EntityPlayer)entity, 4, 9);
/*      */             }
/*  708 */             else if ((level >= 4) && (world.field_73012_v.nextInt(20) == 0)) {
/*  709 */               SoundEffect sound = SoundEffect.NONE;
/*  710 */               switch (world.field_73012_v.nextInt(3)) {
/*      */               case 0: case 2: 
/*      */               case 3: 
/*      */               default: 
/*  714 */                 sound = SoundEffect.RANDOM_EXPLODE;
/*  715 */                 break;
/*      */               case 1: 
/*  717 */                 sound = SoundEffect.MOB_ENDERMAN_IDLE;
/*      */               }
/*      */               
/*      */               
/*  721 */               sound.playOnlyTo((EntityPlayer)entity, 1.0F, 1.0F);
/*      */             }
/*      */           } else {
/*  724 */             nbtTag.func_82580_o("witcheryInsanity");
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent(priority=EventPriority.HIGH)
/*      */     public void onLivingDeath(LivingDeathEvent event) {
/*  732 */       if ((!event.entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled())) {
/*  733 */         if ((event.entityLiving instanceof EntityPlayer)) {
/*  734 */           EntityPlayer player = (EntityPlayer)event.entity;
/*  735 */           World world = player.field_70170_p;
/*  736 */           NBTTagCompound nbtTag = Infusion.getNBT(player);
/*  737 */           if (nbtTag.func_74764_b("witcheryDepths")) {
/*  738 */             nbtTag.func_82580_o("witcheryDepths");
/*      */           }
/*  740 */           PlayerEffects.onDeath(player);
/*      */         }
/*  742 */         Familiar.handleLivingDeath(event);
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onLivingSetAttackTarget(LivingSetAttackTargetEvent event)
/*      */     {
/*  749 */       if ((event.target != null) && ((event.entityLiving instanceof EntityLiving))) {
/*  750 */         EntityLiving aggressorEntity = (EntityLiving)event.entityLiving;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  759 */         if ((event.target instanceof EntityPlayer))
/*      */         {
/*  761 */           EntityPlayer player = (EntityPlayer)event.target;
/*  762 */           if (player.func_82150_aj())
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*  767 */             if (aggressorEntity.field_70170_p.func_72846_b(aggressorEntity.field_70165_t, aggressorEntity.field_70163_u, aggressorEntity.field_70161_v, 16.0D) != event.target)
/*      */             {
/*  769 */               aggressorEntity.func_70624_b(null);
/*      */             }
/*  771 */           } else if (aggressorEntity.func_70644_a(Potion.field_76440_q)) {
/*  772 */             aggressorEntity.func_70624_b(null);
/*  773 */           } else if ((aggressorEntity instanceof EntityCreeper)) {
/*  774 */             ItemStack stack = player.field_71071_by.func_70440_f(2);
/*  775 */             if ((stack != null) && (stack.func_77973_b() == Witchery.Items.WITCH_ROBES)) {
/*  776 */               aggressorEntity.func_70624_b(null);
/*      */             }
/*  778 */           } else if (aggressorEntity.func_70662_br()) {
/*  779 */             if (((aggressorEntity instanceof EntityZombie)) && (ExtendedPlayer.get(player).getVampireLevel() >= 10)) {
/*  780 */               aggressorEntity.func_70624_b(null);
/*      */             } else {
/*  782 */               ItemStack stack = player.field_71071_by.func_70440_f(2);
/*  783 */               if ((stack != null) && (stack.func_77973_b() == Witchery.Items.NECROMANCERS_ROBES)) {
/*  784 */                 aggressorEntity.func_70624_b(null);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  790 */         if (((event.target instanceof EntityVillageGuard)) && ((event.entityLiving instanceof EntityGolem))) {
/*  791 */           aggressorEntity.func_70624_b(null);
/*  792 */         } else if ((Config.instance().isZombeIgnoreVillagerActive()) && ((event.target instanceof EntityVillager)) && ((event.entityLiving instanceof EntityZombie))) {
/*  793 */           aggressorEntity.func_70624_b(null);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onLivingFall(LivingFallEvent event) {
/*  800 */       if ((event.entityLiving instanceof EntityPlayer)) {
/*  801 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/*      */         
/*  803 */         Infusion.Registry.instance().get(player).onFalling(player.field_70170_p, player, event);
/*      */       }
/*      */     }
/*      */     
/*      */     @SubscribeEvent
/*      */     public void onLivingHurt(LivingHurtEvent event) {
/*  809 */       if ((event.entityLiving instanceof EntityPlayer)) {
/*  810 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/*      */         
/*      */ 
/*  813 */         if ((event.source.func_76347_k()) && (event.isCancelable()) && (!event.isCanceled()))
/*      */         {
/*  815 */           if ((player.func_82169_q(2) != null) && (player.func_82169_q(2).func_77973_b() == Witchery.Items.DEATH_ROBE))
/*      */           {
/*  817 */             if (!player.func_82165_m(Potion.field_76426_n.field_76415_H)) {
/*  818 */               player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 100, 0));
/*      */             }
/*      */             
/*  821 */             event.setCanceled(true);
/*      */           }
/*      */         }
/*      */         
/*  825 */         if (!event.isCanceled()) {
/*  826 */           Infusion.Registry.instance().get(player).onHurt(player.field_70170_p, player, event);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static EntityItem dropEntityItemWithRandomChoice(EntityLivingBase entity, ItemStack par1ItemStack, boolean par2)
/*      */   {
/*  834 */     if ((par1ItemStack == null) || (entity == null))
/*  835 */       return null;
/*  836 */     if (par1ItemStack.field_77994_a == 0) {
/*  837 */       return null;
/*      */     }
/*  839 */     EntityItem entityitem = new EntityItem(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u - 0.30000001192092896D + entity.func_70047_e(), entity.field_70161_v, par1ItemStack);
/*      */     
/*  841 */     entityitem.field_145804_b = 40;
/*  842 */     float f = 0.1F;
/*      */     
/*      */ 
/*  845 */     if (par2) {
/*  846 */       float f1 = entity.field_70170_p.field_73012_v.nextFloat() * 0.5F;
/*  847 */       float f2 = entity.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/*  848 */       entityitem.field_70159_w = (-MathHelper.func_76126_a(f2) * f1);
/*  849 */       entityitem.field_70179_y = (MathHelper.func_76134_b(f2) * f1);
/*  850 */       entityitem.field_70181_x = 0.20000000298023224D;
/*      */     } else {
/*  852 */       f = 0.3F;
/*  853 */       entityitem.field_70159_w = (-MathHelper.func_76126_a(entity.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(entity.field_70125_A / 180.0F * 3.1415927F) * f);
/*      */       
/*  855 */       entityitem.field_70179_y = (MathHelper.func_76134_b(entity.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(entity.field_70125_A / 180.0F * 3.1415927F) * f);
/*      */       
/*  857 */       entityitem.field_70181_x = (-MathHelper.func_76126_a(entity.field_70125_A / 180.0F * 3.1415927F) * f + 0.1F);
/*      */       
/*  859 */       f = 0.02F;
/*  860 */       float f1 = entity.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/*  861 */       f *= entity.field_70170_p.field_73012_v.nextFloat();
/*  862 */       entityitem.field_70159_w += Math.cos(f1) * f;
/*  863 */       entityitem.field_70181_x += (entity.field_70170_p.field_73012_v.nextFloat() - entity.field_70170_p.field_73012_v.nextFloat()) * 0.1F;
/*      */       
/*  865 */       entityitem.field_70179_y += Math.sin(f1) * f;
/*      */     }
/*      */     
/*  868 */     entity.field_70170_p.func_72838_d(entityitem);
/*  869 */     return entityitem;
/*      */   }
/*      */   
/*      */ 
/*      */   public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, EntityLivingBase victim, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound)
/*      */   {
/*  875 */     int x = MathHelper.func_76128_c(victim.field_70165_t);
/*  876 */     int y = MathHelper.func_76128_c(victim.field_70163_u);
/*  877 */     int z = MathHelper.func_76128_c(victim.field_70161_v);
/*  878 */     return spawnCreature(world, creatureType, x, y, z, victim, minRange, maxRange, effect, effectSound);
/*      */   }
/*      */   
/*      */   public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, int x, int y, int z, EntityPlayer victim, int minRange, int maxRange)
/*      */   {
/*  883 */     return spawnCreature(world, creatureType, x, y, z, victim, minRange, maxRange, null, SoundEffect.NONE);
/*      */   }
/*      */   
/*      */ 
/*      */   public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, int x, int y, int z, EntityLivingBase victim, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound)
/*      */   {
/*  889 */     if (!world.field_72995_K) {
/*  890 */       int activeRadius = maxRange - minRange;
/*  891 */       int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  892 */       if (ax > activeRadius) {
/*  893 */         ax += minRange * 2;
/*      */       }
/*      */       
/*  896 */       int nx = x - maxRange + ax;
/*      */       
/*  898 */       int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  899 */       if (az > activeRadius) {
/*  900 */         az += minRange * 2;
/*      */       }
/*      */       
/*  903 */       int nz = z - maxRange + az;
/*      */       
/*  905 */       int ny = y;
/*  906 */       while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
/*  907 */         ny++;
/*      */       }
/*      */       
/*      */ 
/*  911 */       while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
/*  912 */         ny--;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  917 */       int hy = 0;
/*  918 */       while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
/*  919 */         hy++;
/*      */       }
/*      */       
/*  922 */       Log.instance().debug("Creature: hy: " + hy + " (" + nx + "," + ny + "," + nz + ")");
/*      */       
/*  924 */       if (hy >= 2) {
/*      */         try {
/*  926 */           Constructor ctor = creatureType.getConstructor(new Class[] { World.class });
/*  927 */           EntityCreature creature = (EntityCreature)ctor.newInstance(new Object[] { world });
/*  928 */           if ((victim instanceof EntityPlayer)) {
/*  929 */             EntityPlayer player = (EntityPlayer)victim;
/*  930 */             if ((creature instanceof EntityIllusion)) {
/*  931 */               ((EntityIllusion)creature).setVictim(player.func_70005_c_());
/*  932 */             } else if ((creature instanceof EntityNightmare)) {
/*  933 */               ((EntityNightmare)creature).setVictim(player.func_70005_c_());
/*  934 */               creature.func_70624_b(victim);
/*      */             }
/*      */           }
/*      */           
/*  938 */           creature.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
/*  939 */           world.func_72838_d(creature);
/*  940 */           if (effect != null) {
/*  941 */             effect.send(effectSound, world, 0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 1.0D, creature.field_70131_O, 16);
/*      */           }
/*      */           
/*  944 */           return creature;
/*      */         }
/*      */         catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  952 */     return null;
/*      */   }
/*      */   
/*      */   public static boolean isOnCooldown(World world, ItemStack stack) {
/*  956 */     if (!world.field_72995_K) {
/*  957 */       NBTTagCompound nbtTag = stack.func_77978_p();
/*  958 */       if ((nbtTag != null) && (nbtTag.func_74764_b("WITCCooldown"))) {
/*  959 */         long currentTime = MinecraftServer.func_130071_aq();
/*  960 */         if (currentTime < nbtTag.func_74763_f("WITCCooldown")) {
/*  961 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  965 */     return false;
/*      */   }
/*      */   
/*      */   public static void setCooldown(World world, ItemStack stack, int milliseconds) {
/*  969 */     if (!world.field_72995_K) {
/*  970 */       if (!stack.func_77942_o()) {
/*  971 */         stack.func_77982_d(new NBTTagCompound());
/*      */       }
/*  973 */       NBTTagCompound nbtTag = stack.func_77978_p();
/*  974 */       if (nbtTag != null) {
/*  975 */         long currentTime = MinecraftServer.func_130071_aq();
/*  976 */         nbtTag.func_74772_a("WITCCooldown", currentTime + milliseconds);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*  981 */   public static final Infusion DEFUSED = new Infusion(0);
/*      */   
/*      */   public static final String INFUSION_CHARGES_KEY = "witcheryInfusionCharges";
/*      */   
/*      */   public static final String INFUSION_ID_KEY = "witcheryInfusionID";
/*      */   public static final String INFUSION_MAX_CHARGES_KEY = "witcheryInfusionChargesMax";
/*      */   public static final String INFUSION_NEXTSYNC = "WITCResyncLook";
/*      */   public static final String INFUSION_GROTESQUE = "witcheryGrotesque";
/*      */   public static final String INFUSION_DEPTHS = "witcheryDepths";
/*      */   public static final String INFUSION_CURSED = "witcheryCursed";
/*      */   public static final String INFUSION_INSANITY = "witcheryInsanity";
/*      */   public static final String INFUSION_SINKING = "witcherySinking";
/*      */   public static final String INFUSION_OVERHEAT = "witcheryOverheating";
/*      */   public static final String INFUSION_NIGHTMARE = "witcheryWakingNightmare";
/*      */   public final int infusionID;
/*      */   protected static final int DEFAULT_CHARGE_COST = 1;
/*      */   
/*      */   public Infusion(int infusionID)
/*      */   {
/* 1000 */     this.infusionID = infusionID;
/*      */   }
/*      */   
/*      */ 
/*      */   public void onHurt(World worldObj, EntityPlayer player, LivingHurtEvent event) {}
/*      */   
/*      */ 
/*      */   public void onFalling(World world, EntityPlayer player, LivingFallEvent event) {}
/*      */   
/*      */   public IIcon getPowerBarIcon(EntityPlayer player, int index)
/*      */   {
/* 1011 */     return Blocks.field_150344_f.func_149691_a(0, 0);
/*      */   }
/*      */   
/*      */ 
/*      */   protected boolean consumeCharges(World world, EntityPlayer player, int cost, boolean playFailSound)
/*      */   {
/* 1017 */     if (player.field_71075_bZ.field_75098_d) {
/* 1018 */       return true;
/*      */     }
/*      */     
/* 1021 */     int charges = getCurrentEnergy(player);
/* 1022 */     if (charges - cost < 0) {
/* 1023 */       world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/* 1024 */       clearInfusion(player);
/* 1025 */       return false;
/*      */     }
/* 1027 */     setCurrentEnergy(player, charges - cost);
/*      */     
/*      */ 
/* 1030 */     return true;
/*      */   }
/*      */   
/*      */   public void onUpdate(ItemStack itemstack, World world, EntityPlayer player, int par4, boolean par5) {}
/*      */   
/*      */   public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
/*      */   {
/* 1037 */     if (!world.field_72995_K) {
/* 1038 */       world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/*      */     }
/*      */   }
/*      */   
/*      */   public int getMaxItemUseDuration(ItemStack itemstack) {
/* 1043 */     return 400;
/*      */   }
/*      */   
/*      */   public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown) {}
/*      */   
/*      */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*      */   {
/* 1050 */     if (!world.field_72995_K) {
/* 1051 */       world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/*      */     }
/*      */   }
/*      */   
/*      */   public void playSound(World world, EntityPlayer player, String sound) {
/* 1056 */     world.func_72956_a(player, sound, 0.5F, 0.4F / ((float)world.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*      */   }
/*      */   
/*      */   public void playFailSound(World world, EntityPlayer player) {
/* 1060 */     playSound(world, player, "note.snare");
/*      */   }
/*      */   
/*      */ 
/*      */   public static NBTTagCompound getNBT(Entity player)
/*      */   {
/* 1066 */     NBTTagCompound entityData = player.getEntityData();
/* 1067 */     if (player.field_70170_p.field_72995_K) {
/* 1068 */       return entityData;
/*      */     }
/* 1070 */     NBTTagCompound persistedData = entityData.func_74775_l("PlayerPersisted");
/* 1071 */     if (!entityData.func_74764_b("PlayerPersisted")) {
/* 1072 */       entityData.func_74782_a("PlayerPersisted", persistedData);
/*      */     }
/* 1074 */     return persistedData;
/*      */   }
/*      */   
/*      */   public void infuse(EntityPlayer player, int charges)
/*      */   {
/* 1079 */     if (!player.field_70170_p.field_72995_K) {
/* 1080 */       NBTTagCompound nbt = getNBT(player);
/* 1081 */       nbt.func_74768_a("witcheryInfusionID", this.infusionID);
/* 1082 */       nbt.func_74768_a("witcheryInfusionCharges", charges);
/* 1083 */       nbt.func_74768_a("witcheryInfusionChargesMax", charges);
/* 1084 */       CreaturePower.setCreaturePowerID(player, 0, 0);
/* 1085 */       syncPlayer(player.field_70170_p, player);
/*      */     }
/*      */   }
/*      */   
/*      */   private void clearInfusion(EntityPlayer player) {
/* 1090 */     if (!player.field_70170_p.field_72995_K) {
/* 1091 */       NBTTagCompound nbt = getNBT(player);
/*      */       
/*      */ 
/* 1094 */       nbt.func_82580_o("witcheryInfusionCharges");
/*      */       
/* 1096 */       syncPlayer(player.field_70170_p, player);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void setCurrentEnergy(EntityPlayer player, int currentEnergy) {
/* 1101 */     if (!player.field_70170_p.field_72995_K) {
/* 1102 */       NBTTagCompound nbt = getNBT(player);
/* 1103 */       nbt.func_74768_a("witcheryInfusionCharges", currentEnergy);
/* 1104 */       syncPlayer(player.field_70170_p, player);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void syncPlayer(World world, EntityPlayer player) {
/* 1109 */     if (!world.field_72995_K) {
/* 1110 */       Witchery.packetPipeline.sendTo(new PacketPlayerSync(player), player);
/*      */     }
/*      */   }
/*      */   
/*      */   public static int getInfusionID(EntityPlayer player) {
/* 1115 */     NBTTagCompound nbt = getNBT(player);
/* 1116 */     return nbt.func_74764_b("witcheryInfusionID") ? nbt.func_74762_e("witcheryInfusionID") : 0;
/*      */   }
/*      */   
/*      */   public static int getCurrentEnergy(EntityPlayer player) {
/* 1120 */     NBTTagCompound nbt = getNBT(player);
/* 1121 */     return nbt.func_74764_b("witcheryInfusionCharges") ? nbt.func_74762_e("witcheryInfusionCharges") : 0;
/*      */   }
/*      */   
/*      */   public static int getMaxEnergy(EntityPlayer player) {
/* 1125 */     NBTTagCompound nbt = getNBT(player);
/* 1126 */     return nbt.func_74764_b("witcheryInfusionChargesMax") ? nbt.func_74762_e("witcheryInfusionChargesMax") : 0;
/*      */   }
/*      */   
/*      */   public static void setEnergy(EntityPlayer player, int infusionID, int currentEnergy, int maxEnergy) {
/* 1130 */     if (player.field_70170_p.field_72995_K) {
/* 1131 */       NBTTagCompound nbt = getNBT(player);
/* 1132 */       nbt.func_74768_a("witcheryInfusionID", infusionID);
/* 1133 */       nbt.func_74768_a("witcheryInfusionCharges", currentEnergy);
/* 1134 */       nbt.func_74768_a("witcheryInfusionChargesMax", maxEnergy);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void setSinkingCurseLevel(EntityPlayer playerEntity, int sinkingLevel) {
/* 1139 */     if (playerEntity.field_70170_p.field_72995_K) {
/* 1140 */       NBTTagCompound nbt = getNBT(playerEntity);
/* 1141 */       if ((nbt.func_74764_b("witcherySinking")) && (sinkingLevel <= 0)) {
/* 1142 */         nbt.func_82580_o("witcherySinking");
/*      */       }
/* 1144 */       nbt.func_74768_a("witcherySinking", sinkingLevel);
/*      */     }
/*      */   }
/*      */   
/*      */   public static int getSinkingCurseLevel(EntityPlayer player) {
/* 1149 */     NBTTagCompound nbtTag = getNBT(player);
/* 1150 */     return nbtTag.func_74764_b("witcherySinking") ? nbtTag.func_74762_e("witcherySinking") : 0;
/*      */   }
/*      */   
/*      */   public static boolean aquireEnergy(World world, EntityPlayer player, int cost, boolean showMessages) {
/* 1154 */     NBTTagCompound nbtPlayer = getNBT(player);
/* 1155 */     if (nbtPlayer != null) {
/* 1156 */       return aquireEnergy(world, player, nbtPlayer, cost, showMessages);
/*      */     }
/* 1158 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean aquireEnergy(World world, EntityPlayer player, NBTTagCompound nbtPlayer, int cost, boolean showMessages)
/*      */   {
/* 1164 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witcheryInfusionID")) && (nbtPlayer.func_74764_b("witcheryInfusionCharges")))
/*      */     {
/* 1166 */       if ((player.field_71075_bZ.field_75098_d) || (nbtPlayer.func_74762_e("witcheryInfusionCharges") >= cost)) {
/* 1167 */         if (!player.field_71075_bZ.field_75098_d) {
/* 1168 */           setCurrentEnergy(player, nbtPlayer.func_74762_e("witcheryInfusionCharges") - cost);
/*      */         }
/* 1170 */         return true;
/*      */       }
/* 1172 */       if (showMessages) {
/* 1173 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.nocharges", new Object[0]);
/*      */         
/* 1175 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */       }
/* 1177 */       return false;
/*      */     }
/*      */     
/* 1180 */     if (showMessages) {
/* 1181 */       ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.infusionrequired", new Object[0]);
/* 1182 */       SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */     }
/* 1184 */     return false;
/*      */   }
/*      */   
/*      */   public static class Registry
/*      */   {
/* 1189 */     private static final Registry INSTANCE = new Registry();
/*      */     
/*      */     public static Registry instance() {
/* 1192 */       return INSTANCE;
/*      */     }
/*      */     
/* 1195 */     private final ArrayList<Infusion> registry = new ArrayList();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     public void add(Infusion infusion)
/*      */     {
/* 1202 */       if (infusion.infusionID == this.registry.size() + 1) {
/* 1203 */         this.registry.add(infusion);
/* 1204 */       } else if (infusion.infusionID > this.registry.size() + 1) {
/* 1205 */         for (int i = this.registry.size(); i < infusion.infusionID; i++) {
/* 1206 */           this.registry.add(null);
/*      */         }
/* 1208 */         this.registry.add(infusion);
/*      */       } else {
/* 1210 */         Infusion existingInfusion = (Infusion)this.registry.get(infusion.infusionID);
/* 1211 */         if (existingInfusion != null) {
/* 1212 */           Log.instance().warning(String.format("Creature power %s at id %d is being overwritten by another creature power %s.", new Object[] { existingInfusion, Integer.valueOf(infusion.infusionID), infusion }));
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1218 */         this.registry.set(infusion.infusionID, infusion);
/*      */       }
/*      */     }
/*      */     
/*      */     public Infusion get(EntityPlayer player) {
/* 1223 */       int infusionID = Infusion.getInfusionID(player);
/* 1224 */       return infusionID > 0 ? (Infusion)this.registry.get(infusionID - 1) : Infusion.DEFUSED;
/*      */     }
/*      */     
/*      */     public Infusion get(int infusionID) {
/* 1228 */       return infusionID > 0 ? (Infusion)this.registry.get(infusionID - 1) : Infusion.DEFUSED;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/Infusion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */