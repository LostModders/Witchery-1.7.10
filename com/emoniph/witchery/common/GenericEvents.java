/*      */ package com.emoniph.witchery.common;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.blocks.BlockVoidBramble;
/*      */ import com.emoniph.witchery.brewing.potions.PotionResizing;
/*      */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*      */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*      */ import com.emoniph.witchery.entity.EntityDemon;
/*      */ import com.emoniph.witchery.entity.EntityFollower;
/*      */ import com.emoniph.witchery.entity.EntityGoblin;
/*      */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*      */ import com.emoniph.witchery.entity.EntityItemWaystone;
/*      */ import com.emoniph.witchery.entity.EntityMindrake;
/*      */ import com.emoniph.witchery.entity.EntityOwl;
/*      */ import com.emoniph.witchery.entity.EntitySummonedUndead;
/*      */ import com.emoniph.witchery.entity.EntityToad;
/*      */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*      */ import com.emoniph.witchery.entity.EntityVillagerWere;
/*      */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*      */ import com.emoniph.witchery.entity.EntityWolfman;
/*      */ import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
/*      */ import com.emoniph.witchery.entity.ai.EntityAISleep;
/*      */ import com.emoniph.witchery.infusion.InfusedBrewEffect;
/*      */ import com.emoniph.witchery.infusion.Infusion;
/*      */ import com.emoniph.witchery.item.ItemCaneSword;
/*      */ import com.emoniph.witchery.item.ItemDeathsClothes;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*      */ import com.emoniph.witchery.item.ItemGlassGoblet;
/*      */ import com.emoniph.witchery.item.ItemHunterClothes;
/*      */ import com.emoniph.witchery.item.ItemMoonCharm;
/*      */ import com.emoniph.witchery.item.ItemPoppet;
/*      */ import com.emoniph.witchery.item.ItemTaglockKit;
/*      */ import com.emoniph.witchery.item.ItemVampireClothes;
/*      */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.network.PacketPlayerStyle;
/*      */ import com.emoniph.witchery.util.BlockUtil;
/*      */ import com.emoniph.witchery.util.BoltDamageSource;
/*      */ import com.emoniph.witchery.util.ChatUtil;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.CreatureUtil;
/*      */ import com.emoniph.witchery.util.EntityUtil;
/*      */ import com.emoniph.witchery.util.EntityUtil.DamageSourceVampireFire;
/*      */ import com.emoniph.witchery.util.Log;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.TameableUtil;
/*      */ import com.emoniph.witchery.util.TimeUtil;
/*      */ import com.emoniph.witchery.util.TransformCreature;
/*      */ import cpw.mods.fml.common.eventhandler.EventPriority;
/*      */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.command.IEntitySelector;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*      */ import net.minecraft.entity.ai.EntityAITasks;
/*      */ import net.minecraft.entity.ai.EntitySenses;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.boss.IBossDisplayData;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityCreeper;
/*      */ import net.minecraft.entity.monster.EntityEnderman;
/*      */ import net.minecraft.entity.monster.EntityPigZombie;
/*      */ import net.minecraft.entity.monster.EntitySkeleton;
/*      */ import net.minecraft.entity.monster.EntityZombie;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.passive.EntitySheep;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.passive.EntityVillager;
/*      */ import net.minecraft.entity.passive.EntityWolf;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayer.EnumStatus;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.entity.player.PlayerCapabilities;
/*      */ import net.minecraft.event.ClickEvent;
/*      */ import net.minecraft.event.ClickEvent.Action;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemArmor;
/*      */ import net.minecraft.item.ItemPotion;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.ItemSword;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.ChatStyle;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EntityDamageSource;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.village.MerchantRecipeList;
/*      */ import net.minecraft.village.Village;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraftforge.common.ForgeHooks;
/*      */ import net.minecraftforge.event.ServerChatEvent;
/*      */ import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
/*      */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*      */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*      */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*      */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*      */ import net.minecraftforge.event.entity.player.EntityInteractEvent;
/*      */ import net.minecraftforge.event.entity.player.PlayerDropsEvent;
/*      */ import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
/*      */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*      */ import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
/*      */ import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
/*      */ import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
/*      */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*      */ 
/*      */ public class GenericEvents
/*      */ {
/*      */   @SubscribeEvent
/*      */   public void onServerChat(ServerChatEvent event)
/*      */   {
/*  141 */     boolean chatMasqueradeAllowed = Config.instance().allowChatMasquerading;
/*  142 */     ExtendedPlayer playerEx = ExtendedPlayer.get(event.player);
/*  143 */     String disguise; if ((playerEx != null) && (chatMasqueradeAllowed) && 
/*  144 */       (playerEx.getCreatureType() == TransformCreature.PLAYER) && (playerEx.getOtherPlayerSkin() != null) && (!playerEx.getOtherPlayerSkin().isEmpty()))
/*      */     {
/*  146 */       disguise = playerEx.getOtherPlayerSkin();
/*  147 */       ChatComponentTranslation comp = new ChatComponentTranslation("chat.type.text", new Object[] { getPlayerChatName(event.player, disguise), ForgeHooks.newChatWithLinks(event.message) });
/*      */       
/*  149 */       event.component = comp;
/*      */       
/*  151 */       if (!event.player.field_70170_p.field_72995_K) {
/*  152 */         for (Object otherPlayerObj : event.player.field_70170_p.field_73010_i) {
/*  153 */           EntityPlayer otherPlayer = (EntityPlayer)otherPlayerObj;
/*  154 */           if ((otherPlayer.field_71075_bZ.field_75098_d) && (MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(otherPlayer.func_146103_bH()))) {
/*  155 */             ChatUtil.sendTranslated(EnumChatFormatting.GOLD, otherPlayer, "witchery.rite.mirrormirror.opchatreveal", new Object[] { disguise, event.player.func_70005_c_() });
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private net.minecraft.util.IChatComponent getPlayerChatName(EntityPlayerMP player, String otherName)
/*      */   {
/*  164 */     ChatComponentText chatcomponenttext = new ChatComponentText(ScorePlayerTeam.func_96667_a(player.func_96124_cp(), otherName));
/*  165 */     chatcomponenttext.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.func_70005_c_() + " "));
/*  166 */     return chatcomponenttext;
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onEntityConstructing(EntityEvent.EntityConstructing event) {
/*  171 */     if (((event.entity instanceof EntityPlayer)) && (ExtendedPlayer.get((EntityPlayer)event.entity) == null)) {
/*  172 */       ExtendedPlayer.register((EntityPlayer)event.entity);
/*  173 */     } else if (((event.entity instanceof EntityVillager)) && (ExtendedVillager.get((EntityVillager)event.entity) == null))
/*      */     {
/*  175 */       ExtendedVillager.register((EntityVillager)event.entity);
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent(priority=EventPriority.HIGH)
/*      */   public void onEntityJoinWorld(EntityJoinWorldEvent event) {
/*  181 */     if ((event.entity instanceof EntityLivingBase)) {
/*  182 */       NBTTagCompound nbtData = event.entity.getEntityData();
/*  183 */       nbtData.func_74776_a("WITCInitialWidth", event.entity.field_70130_N);
/*  184 */       nbtData.func_74776_a("WITCInitialHeight", event.entity.field_70131_O);
/*      */     }
/*      */     
/*  187 */     if ((!event.entity.field_70170_p.field_72995_K) && ((event.entity instanceof EntityPlayer))) {
/*  188 */       EntityPlayer player = (EntityPlayer)event.entity;
/*  189 */       ExtendedPlayer.loadProxyData(player);
/*  190 */       Shapeshift.INSTANCE.initCurrentShift(player);
/*  191 */       Infusion.syncPlayer(event.world, player);
/*      */       
/*  193 */       for (Object obj : event.world.field_73010_i) {
/*  194 */         EntityPlayer otherPlayer = (EntityPlayer)obj;
/*  195 */         if (otherPlayer != player)
/*      */         {
/*      */ 
/*  198 */           Witchery.packetPipeline.sendTo(new PacketPlayerStyle(otherPlayer), player);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  203 */       Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), event.world.field_73011_w.field_76574_g);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  208 */       if ((player.field_71093_bK != Config.instance().dimensionDreamID) && (WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) && (!WorldProviderDreamWorld.getPlayerIsGhost(player)))
/*      */       {
/*      */ 
/*  211 */         WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
/*  212 */       } else if ((player.field_71093_bK == Config.instance().dimensionDreamID) && (!WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)))
/*      */       {
/*  214 */         WorldProviderDreamWorld.changeDimension(player, 0);
/*  215 */         WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
/*      */       }
/*  217 */     } else if ((event.world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) && (isDisallowedEntity(event.entity)))
/*      */     {
/*  219 */       event.setCanceled(true);
/*      */     }
/*      */     
/*  222 */     if (((event.entity instanceof EntityVillager)) && (!(event.entity instanceof EntityVillagerWere)) && (!(event.entity instanceof EntityVillageGuard)))
/*      */     {
/*  224 */       EntityVillager villager = (EntityVillager)event.entity;
/*  225 */       villager.field_70714_bg.func_75776_a(1, new EntityAISleep(villager));
/*  226 */     } else if ((event.entity instanceof EntityZombie)) {
/*  227 */       EntityZombie creature = (EntityZombie)event.entity;
/*  228 */       creature.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(creature, EntityFollower.class, 0, false, false, new IEntitySelector()
/*      */       {
/*      */         public boolean func_82704_a(Entity entity)
/*      */         {
/*  232 */           return ((entity instanceof EntityFollower)) && (((EntityFollower)entity).getFollowerType() == 0);
/*      */         }
/*      */       }));
/*      */     }
/*  236 */     else if ((event.entity instanceof EntitySkeleton)) {
/*  237 */       EntitySkeleton creature = (EntitySkeleton)event.entity;
/*  238 */       creature.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(creature, EntityFollower.class, 0, true, false, new IEntitySelector()
/*      */       {
/*      */         public boolean func_82704_a(Entity entity)
/*      */         {
/*  242 */           return ((entity instanceof EntityFollower)) && (((EntityFollower)entity).getFollowerType() == 0);
/*      */         }
/*      */       }));
/*      */     }
/*      */     
/*      */ 
/*  248 */     if ((event.entity.field_70170_p.field_72995_K) && ((event.entity instanceof EntityPlayer))) {
/*  249 */       Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketExtendedEntityRequestSyncToClient((EntityLivingBase)event.entity));
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onPlayerCloneEvent(PlayerEvent.Clone event)
/*      */   {
/*  256 */     NBTTagCompound oldPlayerNBT = new NBTTagCompound();
/*  257 */     ExtendedPlayer oldPlayerEx = ExtendedPlayer.get(event.original);
/*  258 */     oldPlayerEx.saveNBTData(oldPlayerNBT);
/*      */     
/*  260 */     ExtendedPlayer newPlayerEx = ExtendedPlayer.get(event.entityPlayer);
/*  261 */     newPlayerEx.loadNBTData(oldPlayerNBT);
/*  262 */     newPlayerEx.restorePlayerInventoryFrom(oldPlayerEx);
/*      */   }
/*      */   
/*      */   private static boolean isDisallowedEntity(Entity entity) {
/*  266 */     if ((entity instanceof EntityLiving)) {
/*  267 */       Class cls = entity.getClass();
/*  268 */       String packageName = cls.getCanonicalName();
/*  269 */       if ((packageName.startsWith("net.minecraft.entity")) || (packageName.startsWith("com.emoniph.witchery"))) {
/*  270 */         return entity instanceof EntityEnderman;
/*      */       }
/*  272 */       return true;
/*      */     }
/*      */     
/*  275 */     return false;
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onPlayerWakeUpEvent(PlayerWakeUpEvent event)
/*      */   {
/*  281 */     World world = event.entityPlayer.field_70170_p;
/*  282 */     if (!world.field_72995_K) {
/*  283 */       EntityPlayer player = event.entityPlayer;
/*  284 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  285 */       if ((playerEx.isVampire()) && (player.func_71026_bH())) {
/*  286 */         int x = MathHelper.func_76128_c(player.field_70165_t);
/*  287 */         int y = MathHelper.func_76128_c(player.field_70163_u);
/*  288 */         int z = MathHelper.func_76128_c(player.field_70161_v);
/*  289 */         if (world.func_147439_a(x, y, z) == Witchery.Blocks.COFFIN) {
/*  290 */           Iterator iterator = world.field_73010_i.iterator();
/*      */           EntityPlayer entityplayer;
/*      */           do {
/*  293 */             if (!iterator.hasNext())
/*      */             {
/*  295 */               long currentTime = world.func_72820_D() - 11000L;
/*      */               
/*      */ 
/*  298 */               world.func_72877_b(currentTime);
/*      */               
/*  300 */               break;
/*      */             }
/*  302 */             entityplayer = (EntityPlayer)iterator.next();
/*      */           }
/*  304 */           while (entityplayer.func_71026_bH());
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onPlayerSleepInBed(PlayerSleepInBedEvent event) {
/*  312 */     World world = event.entityPlayer.field_70170_p;
/*  313 */     EntityPlayer player = event.entityPlayer;
/*  314 */     if (CreatureUtil.isWerewolf(event.entityPlayer)) {
/*  315 */       if (!world.field_72995_K) {
/*  316 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, event.entityPlayer, "witchery.nosleep.wolf", new Object[0]);
/*  317 */         event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
/*      */       }
/*  319 */     } else if (event.entityPlayer.func_70644_a(Witchery.Potions.RESIZING)) {
/*  320 */       if (!world.field_72995_K) {
/*  321 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, event.entityPlayer, "witchery.nosleep.resized", new Object[0]);
/*  322 */         event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
/*      */       }
/*  324 */     } else if ((ExtendedPlayer.get(event.entityPlayer).isVampire()) && (world.func_147439_a(event.x, event.y, event.z) == Witchery.Blocks.COFFIN))
/*      */     {
/*  326 */       if (!event.entityPlayer.field_70170_p.func_72935_r()) {
/*  327 */         if (!world.field_72995_K) {
/*  328 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, event.entityPlayer, "witchery.nosleep.dayonly", new Object[0]);
/*  329 */           event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
/*      */         }
/*      */       } else {
/*  332 */         if (!world.field_72995_K) {
/*  333 */           if ((player.func_70608_bn()) || (!player.func_70089_S())) {
/*  334 */             return;
/*      */           }
/*      */           
/*  337 */           if (!world.field_73011_w.func_76569_d()) {
/*  338 */             return;
/*      */           }
/*      */           
/*  341 */           if (!world.func_72935_r()) {
/*  342 */             event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
/*  343 */             return;
/*      */           }
/*      */           
/*  346 */           if ((Math.abs(player.field_70165_t - event.x) > 3.0D) || (Math.abs(player.field_70163_u - event.y) > 2.0D) || (Math.abs(player.field_70161_v - event.z) > 3.0D))
/*      */           {
/*      */ 
/*  349 */             event.result = EntityPlayer.EnumStatus.TOO_FAR_AWAY;
/*  350 */             return;
/*      */           }
/*      */           
/*  353 */           double d0 = 8.0D;
/*  354 */           double d1 = 5.0D;
/*  355 */           List list = world.func_72872_a(net.minecraft.entity.monster.EntityMob.class, AxisAlignedBB.func_72330_a(event.x - d0, event.y - d1, event.z - d0, event.x + d0, event.y + d1, event.z + d0));
/*      */           
/*      */ 
/*      */ 
/*  359 */           if (!list.isEmpty()) {
/*  360 */             event.result = EntityPlayer.EnumStatus.NOT_SAFE;
/*  361 */             return;
/*      */           }
/*      */         }
/*      */         
/*  365 */         if (player.func_70115_ae()) {
/*  366 */           player.func_70078_a((Entity)null);
/*      */         }
/*      */         
/*  369 */         PotionResizing.setEntitySize(player, 0.2F, 0.2F);
/*      */         
/*      */ 
/*  372 */         player.field_70129_M = 0.2F;
/*      */         
/*  374 */         if (world.func_72899_e(event.x, event.y, event.z)) {
/*  375 */           int l = world.func_147439_a(event.x, event.y, event.z).getBedDirection(world, event.x, event.y, event.z);
/*      */           
/*  377 */           float f1 = 0.5F;
/*  378 */           float f = 0.5F;
/*      */           
/*  380 */           switch (l) {
/*      */           case 0: 
/*  382 */             f = 0.9F;
/*  383 */             break;
/*      */           case 1: 
/*  385 */             f1 = 0.1F;
/*  386 */             break;
/*      */           case 2: 
/*  388 */             f = 0.1F;
/*  389 */             break;
/*      */           case 3: 
/*  391 */             f1 = 0.9F;
/*      */           }
/*      */           
/*  394 */           player.field_71079_bU = 0.0F;
/*  395 */           player.field_71089_bV = 0.0F;
/*      */           
/*  397 */           switch (l) {
/*      */           case 0: 
/*  399 */             player.field_71089_bV = -1.8F;
/*  400 */             break;
/*      */           case 1: 
/*  402 */             player.field_71079_bU = 1.8F;
/*  403 */             break;
/*      */           case 2: 
/*  405 */             player.field_71089_bV = 1.8F;
/*  406 */             break;
/*      */           case 3: 
/*  408 */             player.field_71079_bU = -1.8F;
/*      */           }
/*      */           
/*  411 */           player.func_70107_b(event.x + f1, event.y + 0.9375F, event.z + f);
/*      */         }
/*      */         else {
/*  414 */           player.func_70107_b(event.x + 0.5F, event.y + 0.9375F, event.z + 0.5F);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  419 */         player.field_71083_bS = true;
/*  420 */         player.field_71076_b = 0;
/*  421 */         player.field_71081_bT = new ChunkCoordinates(event.x, event.y, event.z);
/*  422 */         player.field_70159_w = (player.field_70179_y = player.field_70181_x = 0.0D);
/*      */         
/*  424 */         if (!world.field_72995_K) {
/*  425 */           world.func_72854_c();
/*      */         }
/*      */         
/*  428 */         event.result = EntityPlayer.EnumStatus.OK;
/*  429 */         return;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @SubscribeEvent
/*      */   public void onLivingDrops(LivingDropsEvent event)
/*      */   {
/*  444 */     if ((!event.isCanceled()) && (event.entityLiving != null) && (!event.entityLiving.field_70170_p.field_72995_K)) {
/*  445 */       if (((event.entityLiving instanceof EntityLiving)) && 
/*  446 */         (EntityUtil.isNoDrops((EntityLiving)event.entityLiving))) {
/*  447 */         event.setCanceled(true);
/*  448 */         return;
/*      */       }
/*      */       
/*      */ 
/*  452 */       if ((event.entityLiving instanceof EntityHorse)) {
/*  453 */         EntityHorse horse = (EntityHorse)event.entityLiving;
/*  454 */         NBTTagCompound nbtHorse = horse.getEntityData();
/*  455 */         if ((nbtHorse != null) && (nbtHorse.func_74767_n("WITCIsBinky"))) {
/*  456 */           event.drops.clear();
/*  457 */           event.drops.add(new EntityItem(horse.field_70170_p, horse.field_70165_t, horse.field_70163_u, horse.field_70161_v, Witchery.Items.GENERIC.itemBinkyHead.createStack()));
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onItemToss(ItemTossEvent event)
/*      */   {
/*  466 */     if ((!event.isCanceled()) && (!event.player.field_70170_p.field_72995_K) && 
/*  467 */       (event.entityItem != null) && (event.entityItem.func_92059_d() != null)) {
/*  468 */       if (event.entityItem.func_92059_d().func_77973_b() == Witchery.Items.SEEDS_MINDRAKE) {
/*  469 */         event.entityItem.lifespan = TimeUtil.secsToTicks(3);
/*  470 */         NBTTagCompound nbtItem = event.entityItem.getEntityData();
/*  471 */         nbtItem.func_74778_a("WITCThrower", event.player.func_70005_c_());
/*  472 */       } else if ((Witchery.Items.GENERIC.itemWaystone.isMatch(event.entityItem.func_92059_d())) || (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(event.entityItem.func_92059_d())) || (Witchery.Items.GENERIC.itemAttunedStone.isMatch(event.entityItem.func_92059_d())) || (Witchery.Items.GENERIC.itemSubduedSpirit.isMatch(event.entityItem.func_92059_d())) || (Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(event.entityItem.func_92059_d())))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  478 */         EntityUtil.spawnEntityInWorld(event.entity.field_70170_p, new EntityItemWaystone(event.entityItem));
/*  479 */         event.setCanceled(true);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onItemExpireEvent(ItemExpireEvent event)
/*      */   {
/*  487 */     if ((!event.isCanceled()) && (!event.entityItem.field_70170_p.field_72995_K) && 
/*  488 */       (event.entityItem != null) && (event.entityItem.func_92059_d() != null) && (event.entityItem.func_92059_d().func_77973_b() == Witchery.Items.SEEDS_MINDRAKE))
/*      */     {
/*  490 */       for (int i = 0; i < event.entityItem.func_92059_d().field_77994_a; i++) {
/*  491 */         EntityMindrake mindrake = new EntityMindrake(event.entityItem.field_70170_p);
/*  492 */         mindrake.func_70012_b(event.entityItem.field_70165_t, event.entityItem.field_70163_u, event.entityItem.field_70161_v, 0.0F, 0.0F);
/*      */         
/*  494 */         NBTTagCompound nbtItem = event.entityItem.getEntityData();
/*  495 */         if (nbtItem.func_74764_b("WITCThrower")) {
/*  496 */           String thrower = nbtItem.func_74779_i("WITCThrower");
/*  497 */           if ((thrower != null) && (!thrower.isEmpty())) {
/*  498 */             mindrake.func_110163_bv();
/*  499 */             mindrake.func_70903_f(true);
/*  500 */             TameableUtil.setOwnerByUsername(mindrake, thrower);
/*      */           }
/*      */         }
/*  503 */         ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, mindrake, 1.0D, 1.0D, 16);
/*  504 */         event.entityItem.field_70170_p.func_72838_d(mindrake);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent(priority=EventPriority.HIGH)
/*      */   public void onPlayerDrops(PlayerDropsEvent event) {
/*      */     int ticks;
/*  512 */     if ((!event.entityPlayer.field_70170_p.field_72995_K) && (!event.isCanceled()) && 
/*  513 */       (ExtendedPlayer.get(event.entityPlayer).isVampire())) {
/*  514 */       ticks = TimeUtil.minsToTicks(MathHelper.func_76125_a(Config.instance().vampireDeathItemKeepAliveMins, 5, 30));
/*      */       
/*  516 */       for (EntityItem item : event.drops) {
/*  517 */         item.lifespan = ticks;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   @SubscribeEvent(priority=EventPriority.HIGH)
/*      */   public void onEntityInteract(EntityInteractEvent event)
/*      */   {
/*  526 */     PotionEffect effect = event.entityPlayer.func_70660_b(Witchery.Potions.PARALYSED);
/*  527 */     if ((effect != null) && (effect.func_76458_c() >= 4)) {
/*  528 */       event.setCanceled(true);
/*  529 */       return;
/*      */     }
/*      */     
/*      */ 
/*  533 */     ExtendedPlayer playerEx = ExtendedPlayer.get(event.entityPlayer);
/*  534 */     ExtendedPlayer.VampirePower power = playerEx.getSelectedVampirePower();
/*  535 */     if (power != ExtendedPlayer.VampirePower.NONE) {
/*  536 */       if ((power == ExtendedPlayer.VampirePower.DRINK) && ((event.target instanceof EntityLivingBase))) {
/*  537 */         if (!event.entityPlayer.field_70170_p.field_72995_K) {
/*  538 */           float RANGE = ((EntityLivingBase)event.target).func_70644_a(Witchery.Potions.PARALYSED) ? 2.1F : 1.3F;
/*      */           
/*  540 */           if (event.target.func_70092_e(event.entityPlayer.field_70165_t, event.target.field_70163_u, event.entityPlayer.field_70161_v) <= RANGE * RANGE)
/*      */           {
/*      */ 
/*  543 */             int drinkAmount = ItemVampireClothes.isDrinkBoostActive(event.entityPlayer) ? 15 : 10;
/*  544 */             if (CreatureUtil.isWerewolf(event.target, true)) {
/*  545 */               event.entityPlayer.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), event.entityPlayer), 4.0F);
/*      */               
/*      */ 
/*  548 */               ParticleEffect.FLAME.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, event.target.field_70165_t, event.target.field_70163_u + event.target.field_70131_O * 0.8D, event.target.field_70161_v, 0.5D, 0.2D, 16);
/*      */ 
/*      */             }
/*  551 */             else if ((event.target instanceof EntityVillageGuard)) {
/*  552 */               EntityVillageGuard target = (EntityVillageGuard)event.target;
/*  553 */               playerEx.increaseBloodPower(target.takeBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, event.entityPlayer));
/*      */               
/*      */ 
/*  556 */               ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, target.field_70165_t, target.field_70163_u + target.field_70131_O * 0.8D, target.field_70161_v, 0.5D, 0.2D, 16);
/*      */               
/*      */ 
/*  559 */               checkForBloodDrinkingWitnesses(event.entityPlayer, target);
/*  560 */             } else if ((event.target instanceof EntityVillager)) {
/*  561 */               EntityVillager target = (EntityVillager)event.target;
/*  562 */               ExtendedVillager villagerEx = ExtendedVillager.get(target);
/*  563 */               playerEx.increaseBloodPower(villagerEx.takeBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, event.entityPlayer));
/*      */               
/*      */ 
/*  566 */               ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, target.field_70165_t, target.field_70163_u + target.field_70131_O * 0.8D, target.field_70161_v, 0.5D, 0.2D, 16);
/*      */               
/*      */ 
/*  569 */               checkForBloodDrinkingWitnesses(event.entityPlayer, target);
/*  570 */               if (playerEx.getVampireLevel() == 2) {
/*  571 */                 if ((Config.instance().allowVampireQuests) && (villagerEx.getBlood() >= 250) && (villagerEx.getBlood() <= 280))
/*      */                 {
/*  573 */                   if (playerEx.getVampireQuestCounter() >= 5) {
/*  574 */                     playerEx.increaseVampireLevel();
/*      */                   } else {
/*  576 */                     SoundEffect.NOTE_PLING.playOnlyTo(event.entityPlayer, 1.0F, 1.0F);
/*  577 */                     playerEx.increaseVampireQuestCounter();
/*      */                   }
/*  579 */                 } else if (villagerEx.getBlood() < 240) {
/*  580 */                   playerEx.resetVampireQuestCounter();
/*      */                 }
/*  582 */               } else if ((playerEx.getVampireLevel() == 8) && (playerEx.canIncreaseVampireLevel()) && 
/*  583 */                 (villagerIsInCage(target))) {
/*  584 */                 if ((villagerEx.getBlood() >= 250) && (villagerEx.getBlood() <= 280)) {
/*  585 */                   if (playerEx.getVampireQuestCounter() >= 5) {
/*  586 */                     playerEx.increaseVampireLevel();
/*      */                   } else {
/*  588 */                     SoundEffect.NOTE_PLING.playOnlyTo(event.entityPlayer, 1.0F, 1.0F);
/*  589 */                     playerEx.increaseVampireQuestCounter();
/*      */                   }
/*  591 */                 } else if (villagerEx.getBlood() < 240) {
/*  592 */                   playerEx.resetVampireQuestCounter();
/*      */                 }
/*      */               }
/*      */             }
/*  596 */             else if ((event.target instanceof EntityPlayer)) {
/*  597 */               EntityPlayer target = (EntityPlayer)event.target;
/*  598 */               playerEx.increaseBloodPower(ExtendedPlayer.get(target).takeHumanBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, event.entityPlayer));
/*      */               
/*      */ 
/*  601 */               ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, target.field_70165_t, target.field_70163_u + target.field_70131_O * 0.8D, target.field_70161_v, 0.5D, 0.2D, 16);
/*      */ 
/*      */             }
/*  604 */             else if ((event.target instanceof net.minecraft.entity.passive.IAnimals)) {
/*  605 */               EntityLivingBase target = (EntityLivingBase)event.target;
/*  606 */               target.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), event.entityPlayer), 1.0F);
/*      */               
/*  608 */               playerEx.increaseBloodPower(2, (int)Math.ceil(playerEx.getMaxBloodPower() * 0.25F));
/*  609 */               ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, target.field_70165_t, target.field_70163_u + target.field_70131_O * 0.8D, target.field_70161_v, 0.5D, 0.2D, 16);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  614 */           event.setCanceled(true);
/*      */         }
/*  616 */       } else if (power == ExtendedPlayer.VampirePower.MESMERIZE) {
/*  617 */         if (!event.entityPlayer.field_70170_p.field_72995_K) {
/*  618 */           if ((event.entityPlayer.func_70093_af()) && (playerEx.getVampireLevel() >= 2)) {
/*  619 */             playerEx.toggleVampireVision();
/*  620 */           } else if ((playerEx.getCreatureType() == TransformCreature.NONE) && (playerEx.getVampireLevel() >= 2))
/*      */           {
/*  622 */             if ((((event.target instanceof EntityVillager)) && (!(event.target instanceof EntityVillagerWere))) || ((event.target instanceof EntityPlayer)) || ((event.target instanceof EntityVillageGuard)))
/*      */             {
/*      */ 
/*  625 */               EntityLivingBase victim = (EntityLivingBase)event.target;
/*  626 */               if (!victim.func_70644_a(Witchery.Potions.PARALYSED)) {
/*  627 */                 if (playerEx.decreaseBloodPower(ExtendedPlayer.VampirePower.MESMERIZE.INITIAL_COST, true))
/*      */                 {
/*  629 */                   victim.func_70690_d(new PotionEffect(Witchery.Potions.PARALYSED.field_76415_H, TimeUtil.secsToTicks(5 + playerEx.getVampireLevel() / 2 + Math.max(0, (playerEx.getVampireLevel() - 4) / 2) + (ItemVampireClothes.isMezmeriseBoostActive(event.entityPlayer) ? 3 : 0)), playerEx.getVampireLevel() >= 8 ? 5 : 4));
/*      */                   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  637 */                   SoundEffect.WITCHERY_RANDOM_HYPNOSIS.playAtPlayer(event.entity.field_70170_p, event.entityPlayer, 0.5F, 1.0F);
/*      */                 }
/*      */                 else {
/*  640 */                   SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0F, 0.5F);
/*      */                 }
/*      */               } else {
/*  643 */                 SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0F, 0.5F);
/*      */               }
/*      */             } else {
/*  646 */               SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0F, 0.5F);
/*      */             }
/*      */           } else {
/*  649 */             SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0F, 0.5F);
/*      */           }
/*  651 */           event.setCanceled(true);
/*      */         }
/*      */       } else {
/*  654 */         event.setCanceled(true);
/*      */       }
/*      */     }
/*      */     
/*  658 */     if (event.isCanceled()) {
/*  659 */       return;
/*      */     }
/*      */     
/*      */ 
/*  663 */     if ((event.target != null) && (!event.target.field_70170_p.field_72995_K) && ((event.target instanceof EntityLiving)) && 
/*  664 */       (com.emoniph.witchery.brewing.potions.PotionEnslaved.isMobEnslavedBy((EntityLiving)event.target, event.entityPlayer))) {
/*  665 */       EntityPlayer player = event.entityPlayer;
/*  666 */       EntityLiving creature = (EntityLiving)event.target;
/*  667 */       ItemStack heldObject = player.func_70694_bm();
/*  668 */       if ((Witchery.Items.GENERIC.itemGraveyardDust.isMatch(heldObject)) && ((creature instanceof EntitySummonedUndead)))
/*      */       {
/*  670 */         float maxHealth = creature.func_110138_aP() + 2.0F;
/*  671 */         if (maxHealth <= 50.0F) {
/*  672 */           IAttributeInstance attribute = creature.func_110148_a(SharedMonsterAttributes.field_111267_a);
/*      */           
/*  674 */           if (attribute != null) {
/*  675 */             attribute.func_111128_a(maxHealth);
/*  676 */             creature.func_70606_j(maxHealth);
/*  677 */             creature.func_110163_bv();
/*  678 */             Witchery.packetPipeline.sendToAllAround(new com.emoniph.witchery.network.PacketParticles(ParticleEffect.INSTANT_SPELL, SoundEffect.MOB_SILVERFISH_KILL, creature, 0.5D, 1.0D), com.emoniph.witchery.util.TargetPointUtil.from(creature, 16.0D));
/*      */             
/*      */ 
/*  681 */             if (!player.field_71075_bZ.field_75098_d) {
/*  682 */               heldObject.field_77994_a -= 1;
/*  683 */               if ((player instanceof EntityPlayerMP)) {
/*  684 */                 ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*  689 */       } else if ((heldObject != null) && (((creature instanceof EntityZombie)) || ((creature instanceof EntityPigZombie)) || ((creature instanceof EntitySkeleton))))
/*      */       {
/*  691 */         if ((heldObject.func_77973_b() instanceof ItemArmor)) {
/*  692 */           ItemArmor armor = (ItemArmor)heldObject.func_77973_b();
/*  693 */           if (creature.func_71124_b(4 - armor.field_77881_a) == null) {
/*  694 */             creature.func_70062_b(4 - armor.field_77881_a, heldObject.func_77979_a(1));
/*  695 */             creature.func_110163_bv();
/*  696 */             if ((player instanceof EntityPlayerMP)) {
/*  697 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*      */             }
/*      */           }
/*  700 */         } else if (((heldObject.func_77973_b() instanceof ItemSword)) && 
/*  701 */           (creature.func_71124_b(0) == null)) {
/*  702 */           creature.func_70062_b(0, heldObject.func_77979_a(1));
/*  703 */           creature.func_110163_bv();
/*  704 */           if ((player instanceof EntityPlayerMP)) {
/*  705 */             ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  714 */     if ((event.target != null) && (!event.target.field_70170_p.field_72995_K) && ((event.target instanceof EntityVillager))) {
/*  715 */       EntityVillager villager = (EntityVillager)event.target;
/*  716 */       ItemStack heldObject = event.entityPlayer.func_70694_bm();
/*  717 */       if ((!villager.func_70631_g_()) && (heldObject != null) && (heldObject.func_77973_b() == Items.field_151027_R) && (event.entityPlayer.func_70093_af()))
/*      */       {
/*  719 */         Village village = villager.field_70954_d;
/*  720 */         if (village != null) {
/*  721 */           int rep = village.func_82684_a(event.entityPlayer.func_70005_c_());
/*  722 */           if (rep >= 10) {
/*  723 */             if (village.func_75562_e() > 8) {
/*  724 */               List list = event.entity.field_70170_p.func_72872_a(EntityVillageGuard.class, AxisAlignedBB.func_72330_a(village.func_75577_a().field_71574_a - village.func_75568_b(), village.func_75577_a().field_71572_b - 4, village.func_75577_a().field_71573_c - village.func_75568_b(), village.func_75577_a().field_71574_a + village.func_75568_b(), village.func_75577_a().field_71572_b + 4, village.func_75577_a().field_71573_c + village.func_75568_b()));
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  732 */               int numGuards = list.size();
/*  733 */               if (numGuards < MathHelper.func_76128_c(village.func_75562_e() * 0.25D)) {
/*  734 */                 int villagerNumTrades = villager.field_70963_i == null ? 1 : villager.field_70963_i.size();
/*      */                 
/*  736 */                 if ((!CreatureUtil.isWerewolf(event.target, true)) && (event.target.field_70170_p.field_73012_v.nextInt(villagerNumTrades * 2 + 1) == 0))
/*      */                 {
/*  738 */                   villager.func_85030_a("mob.villager.yew", 1.0F, (villager.field_70170_p.field_73012_v.nextFloat() - villager.field_70170_p.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/*      */                   
/*  740 */                   ChatUtil.sendTranslated(EnumChatFormatting.GREEN, event.entityPlayer, "witchery.village.villageracceptsguardduty", new Object[0]);
/*      */                   
/*  742 */                   EntityVillageGuard.createFrom(villager);
/*      */                 } else {
/*  744 */                   ChatUtil.sendTranslated(EnumChatFormatting.RED, event.entityPlayer, "witchery.village.villagerrefusesguardduty", new Object[0]);
/*      */                   
/*  746 */                   villager.func_85030_a("mob.villager.no", 1.0F, (villager.field_70170_p.field_73012_v.nextFloat() - villager.field_70170_p.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/*      */                 }
/*      */               }
/*      */               else {
/*  750 */                 ChatUtil.sendTranslated(EnumChatFormatting.BLUE, event.entityPlayer, "witchery.village.toomanyguards", new Object[0]);
/*      */                 
/*  752 */                 SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer);
/*      */               }
/*      */             } else {
/*  755 */               ChatUtil.sendTranslated(EnumChatFormatting.BLUE, event.entityPlayer, "witchery.village.villagetoosmall", new Object[0]);
/*      */               
/*  757 */               SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer);
/*      */             }
/*      */           } else {
/*  760 */             ChatUtil.sendTranslated(EnumChatFormatting.BLUE, event.entityPlayer, "witchery.village.reptoolow", new Object[0]);
/*      */             
/*  762 */             SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  769 */     if ((!event.entity.field_70170_p.field_72995_K) && (event.target != null) && ((event.target instanceof EntityWolf))) {
/*  770 */       EntityWolf wolf = (EntityWolf)event.target;
/*  771 */       if ((playerEx.getWerewolfLevel() == 7) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED) && (playerEx.getCreatureType() == TransformCreature.WOLF) && (!wolf.func_70909_n()) && (!wolf.func_70919_bu()))
/*      */       {
/*  773 */         if (wolf.field_70170_p.field_73012_v.nextInt(3) == 0) {
/*  774 */           wolf.func_70903_f(true);
/*  775 */           wolf.func_70778_a((net.minecraft.pathfinding.PathEntity)null);
/*  776 */           wolf.func_70624_b((EntityLivingBase)null);
/*  777 */           wolf.func_70907_r().func_75270_a(true);
/*  778 */           wolf.func_70606_j(20.0F);
/*  779 */           wolf.func_152115_b(event.entityPlayer.func_110124_au().toString());
/*  780 */           playTameEffect(wolf, true);
/*  781 */           wolf.field_70170_p.func_72960_a(wolf, (byte)7);
/*  782 */           playerEx.increaseWolfmanQuestCounter();
/*      */         } else {
/*  784 */           playTameEffect(wolf, false);
/*  785 */           wolf.field_70170_p.func_72960_a(wolf, (byte)6);
/*  786 */           if (wolf.field_70170_p.field_73012_v.nextInt(10) == 0) {
/*  787 */             wolf.func_70916_h(true);
/*  788 */             wolf.func_70624_b(event.entityPlayer);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  794 */     ItemStack heldStack = event.entityPlayer.func_70694_bm();
/*  795 */     if (heldStack != null)
/*      */     {
/*  797 */       if (heldStack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) {
/*  798 */         Witchery.Items.TAGLOCK_KIT.onEntityInteract(event.entityPlayer.field_70170_p, event.entityPlayer, heldStack, event);
/*      */         
/*  800 */         if (event.isCanceled()) {
/*  801 */           return;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  806 */       if (heldStack.func_77973_b() == Witchery.Items.BLOOD_GOBLET) {
/*  807 */         Witchery.Items.BLOOD_GOBLET.onEntityInteract(event.entityPlayer.field_70170_p, event.entityPlayer, heldStack, event);
/*      */         
/*  809 */         if (event.isCanceled()) {
/*  810 */           return;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  815 */       if ((Witchery.Items.GENERIC.itemWoodenStake.isMatch(heldStack)) && (Config.instance().allowStakingVampires))
/*      */       {
/*  817 */         if ((event.target instanceof EntityPlayer)) {
/*  818 */           EntityPlayer victim = (EntityPlayer)event.target;
/*  819 */           if ((ExtendedPlayer.get(victim).isVampire()) && (victim.field_71083_bS)) {
/*  820 */             ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, victim.field_70170_p, event.target.field_70165_t, event.target.field_70163_u, event.target.field_70161_v, event.target.field_70130_N, event.target.field_70131_O, 16);
/*      */             
/*      */ 
/*  823 */             EntityUtil.instantDeath(victim, event.entityPlayer);
/*  824 */             if (!event.entityPlayer.field_71075_bZ.field_75098_d) {
/*  825 */               heldStack.field_77994_a -= 1;
/*      */             }
/*  827 */             event.setCanceled(true);
/*  828 */             return;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean villagerIsInCage(EntityVillager target) {
/*  836 */     int ogX = MathHelper.func_76128_c(target.field_70165_t);
/*  837 */     int ogY = MathHelper.func_76128_c(target.field_70163_u);
/*  838 */     int ogZ = MathHelper.func_76128_c(target.field_70161_v);
/*      */     
/*  840 */     if (isCaged(target.field_70170_p, ogX, ogY, ogZ))
/*  841 */       return true;
/*  842 */     if (isCaged(target.field_70170_p, ogX + 1, ogY, ogZ))
/*  843 */       return true;
/*  844 */     if (isCaged(target.field_70170_p, ogX, ogY, ogZ + 1))
/*  845 */       return true;
/*  846 */     if (isCaged(target.field_70170_p, ogX - 1, ogY, ogZ))
/*  847 */       return true;
/*  848 */     if (isCaged(target.field_70170_p, ogX, ogY, ogZ - 1))
/*  849 */       return true;
/*  850 */     if (isCaged(target.field_70170_p, ogX + 1, ogY, ogZ + 1))
/*  851 */       return true;
/*  852 */     if (isCaged(target.field_70170_p, ogX + 1, ogY, ogZ - 1))
/*  853 */       return true;
/*  854 */     if (isCaged(target.field_70170_p, ogX - 1, ogY, ogZ + 1))
/*  855 */       return true;
/*  856 */     if (isCaged(target.field_70170_p, ogX - 1, ogY, ogZ - 1)) {
/*  857 */       return true;
/*      */     }
/*  859 */     return false;
/*      */   }
/*      */   
/*      */   private boolean isCaged(World world, int x, int y, int z) {
/*  863 */     int count = 0;
/*  864 */     Block bars = Blocks.field_150411_aY;
/*  865 */     count += (world.func_147439_a(x + 1, y, z) == bars ? 1 : 0);
/*  866 */     count += (world.func_147439_a(x, y, z + 1) == bars ? 1 : 0);
/*  867 */     count += (world.func_147439_a(x - 1, y, z) == bars ? 1 : 0);
/*  868 */     count += (world.func_147439_a(x, y, z - 1) == bars ? 1 : 0);
/*  869 */     count += (world.func_147439_a(x + 1, y, z + 1) == bars ? 1 : 0);
/*  870 */     count += (world.func_147439_a(x - 1, y, z + 1) == bars ? 1 : 0);
/*  871 */     count += (world.func_147439_a(x + 1, y, z - 1) == bars ? 1 : 0);
/*  872 */     count += (world.func_147439_a(x - 1, y, z - 1) == bars ? 1 : 0);
/*      */     
/*  874 */     y++;
/*      */     
/*  876 */     count += (world.func_147439_a(x + 1, y, z) == bars ? 1 : 0);
/*  877 */     count += (world.func_147439_a(x, y, z + 1) == bars ? 1 : 0);
/*  878 */     count += (world.func_147439_a(x - 1, y, z) == bars ? 1 : 0);
/*  879 */     count += (world.func_147439_a(x, y, z - 1) == bars ? 1 : 0);
/*  880 */     count += (world.func_147439_a(x + 1, y, z + 1) == bars ? 1 : 0);
/*  881 */     count += (world.func_147439_a(x - 1, y, z + 1) == bars ? 1 : 0);
/*  882 */     count += (world.func_147439_a(x + 1, y, z - 1) == bars ? 1 : 0);
/*  883 */     count += (world.func_147439_a(x - 1, y, z - 1) == bars ? 1 : 0);
/*      */     
/*  885 */     if (count < 15) {
/*  886 */       return false;
/*      */     }
/*      */     
/*  889 */     count = 0;
/*      */     
/*  891 */     y++;
/*      */     
/*  893 */     count += (!BlockUtil.isReplaceableBlock(world, x + 1, y, z) ? 1 : 0);
/*  894 */     count += (!BlockUtil.isReplaceableBlock(world, x, y, z + 1) ? 1 : 0);
/*  895 */     count += (!BlockUtil.isReplaceableBlock(world, x - 1, y, z) ? 1 : 0);
/*  896 */     count += (!BlockUtil.isReplaceableBlock(world, x, y, z - 1) ? 1 : 0);
/*  897 */     count += (!BlockUtil.isReplaceableBlock(world, x + 1, y, z + 1) ? 1 : 0);
/*  898 */     count += (!BlockUtil.isReplaceableBlock(world, x - 1, y, z + 1) ? 1 : 0);
/*  899 */     count += (!BlockUtil.isReplaceableBlock(world, x + 1, y, z - 1) ? 1 : 0);
/*  900 */     count += (!BlockUtil.isReplaceableBlock(world, x - 1, y, z - 1) ? 1 : 0);
/*  901 */     count += (!BlockUtil.isReplaceableBlock(world, x, y, z) ? 1 : 0);
/*      */     
/*  903 */     if (count < 9) {
/*  904 */       return false;
/*      */     }
/*      */     
/*  907 */     return true;
/*      */   }
/*      */   
/*      */   private void checkForBloodDrinkingWitnesses(EntityPlayer player, EntityLivingBase victim) {
/*  911 */     AxisAlignedBB bounds = victim.field_70121_D.func_72314_b(16.0D, 8.0D, 16.0D);
/*  912 */     List<EntityVillageGuard> guards = victim.field_70170_p.func_72872_a(EntityVillageGuard.class, bounds);
/*  913 */     for (EntityVillageGuard guard : guards) {
/*  914 */       if ((!guard.func_70644_a(Witchery.Potions.PARALYSED)) && (guard.func_70635_at().func_75522_a(victim))) {
/*  915 */         guard.func_70624_b(player);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onPlayerInteract(PlayerInteractEvent event) {
/*  922 */     PotionEffect effect = event.entityPlayer.func_70660_b(Witchery.Potions.PARALYSED);
/*  923 */     if ((effect != null) && (effect.func_76458_c() >= 4)) {
/*  924 */       event.setCanceled(true);
/*  925 */       return;
/*      */     }
/*      */     
/*  928 */     ExtendedPlayer playerEx = ExtendedPlayer.get(event.entityPlayer);
/*  929 */     if (playerEx.getSelectedVampirePower() != ExtendedPlayer.VampirePower.NONE) {
/*  930 */       if ((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) || (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
/*  931 */         switch (playerEx.getSelectedVampirePower()) {
/*      */         case MESMERIZE: 
/*      */         case SPEED: 
/*      */         case BAT: 
/*      */         case ULTIMATE: 
/*  936 */           Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketSelectPlayerAbility(playerEx, true));
/*  937 */           break;
/*      */         }
/*      */         
/*      */         
/*  941 */         event.setCanceled(true);
/*      */       }
/*      */     }
/*  944 */     else if (event.entityPlayer.field_70170_p.field_72995_K) {
/*  945 */       if (((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) || (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) && 
/*  946 */         (event.entityPlayer.field_70125_A == -90.0F) && (event.entityPlayer.func_70093_af())) {
/*  947 */         Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketHowl());
/*      */       }
/*      */       
/*      */     }
/*  951 */     else if ((event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) && (!event.entityPlayer.field_71075_bZ.field_75098_d))
/*      */     {
/*      */ 
/*  954 */       if ((playerEx.isVampire()) && (event.world.func_147439_a(event.x, event.y, event.z) == Witchery.Blocks.GARLIC_GARLAND))
/*      */       {
/*  956 */         event.entityPlayer.func_70015_d(1);
/*  957 */         event.setCanceled(true);
/*  958 */       } else if ((playerEx.getCreatureType() == TransformCreature.WOLF) && (playerEx.getWerewolfLevel() >= 3) && (event.entityPlayer.func_70093_af()))
/*      */       {
/*  960 */         Block block = event.world.func_147439_a(event.x, event.y, event.z);
/*  961 */         if ((block == Blocks.field_150349_c) || (block == Blocks.field_150354_m) || (block == Blocks.field_150346_d) || (block == Blocks.field_150391_bh) || (block == Blocks.field_150351_n))
/*      */         {
/*      */ 
/*  964 */           EntityAIDigBlocks.tryHarvestBlock(event.world, event.x, event.y, event.z, event.entityPlayer, event.entityPlayer);
/*      */           
/*  966 */           event.setCanceled(true);
/*      */         }
/*  968 */       } else if ((playerEx.getVampireLevel() >= 6) && (playerEx.getCreatureType() == TransformCreature.NONE) && (event.entityPlayer.func_70093_af()) && ((event.entityPlayer.func_70694_bm() == null) || (!event.entityPlayer.func_70694_bm().func_77973_b().func_150897_b(Blocks.field_150348_b))) && (event.entityPlayer.func_71024_bL().func_75116_a() > 0))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  974 */         Block block = event.world.func_147439_a(event.x, event.y, event.z);
/*  975 */         if ((block == Blocks.field_150348_b) || (block == Blocks.field_150424_aL) || (block == Blocks.field_150347_e)) {
/*  976 */           EntityAIDigBlocks.tryHarvestBlock(event.world, event.x, event.y, event.z, event.entityPlayer, event.entityPlayer);
/*      */           
/*  978 */           event.entityPlayer.func_71020_j(10.0F);
/*  979 */           event.setCanceled(true);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void playTameEffect(EntityTameable entity, boolean tamed)
/*      */   {
/*  988 */     String s = "heart";
/*      */     
/*  990 */     if (!tamed) {
/*  991 */       s = "smoke";
/*      */     }
/*      */     
/*  994 */     for (int i = 0; i < 7; i++) {
/*  995 */       double d0 = entity.field_70170_p.field_73012_v.nextGaussian() * 0.02D;
/*  996 */       double d1 = entity.field_70170_p.field_73012_v.nextGaussian() * 0.02D;
/*  997 */       double d2 = entity.field_70170_p.field_73012_v.nextGaussian() * 0.02D;
/*  998 */       entity.field_70170_p.func_72869_a(s, entity.field_70165_t + entity.field_70170_p.field_73012_v.nextFloat() * entity.field_70130_N * 2.0F - entity.field_70130_N, entity.field_70163_u + 0.5D + entity.field_70170_p.field_73012_v.nextFloat() * entity.field_70131_O, entity.field_70161_v + entity.field_70170_p.field_73012_v.nextFloat() * entity.field_70130_N * 2.0F - entity.field_70130_N, d0, d1, d2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   @SubscribeEvent
/*      */   public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
/*      */   {
/* 1008 */     if ((!event.entity.field_70170_p.field_72995_K) && ((event.entity instanceof EntityPlayer))) {
/* 1009 */       EntityPlayer player = (EntityPlayer)event.entity;
/* 1010 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*      */       
/* 1012 */       Shapeshift.INSTANCE.updatePlayerState(player, playerEx);
/* 1013 */       playerEx.tick();
/* 1014 */       if (playerEx.isVampire()) {
/* 1015 */         int prevHunger = player.func_71024_bL().field_75124_e;
/* 1016 */         int hunger = player.func_71024_bL().func_75116_a();
/* 1017 */         if (prevHunger < hunger) {
/* 1018 */           player.func_71024_bL().func_75122_a(-player.func_71024_bL().func_75116_a(), 0.0F);
/*      */         }
/*      */       }
/*      */       
/* 1022 */       if (event.entity.field_70173_aa % 40 == 1) {
/* 1023 */         if (playerEx.getWerewolfLevel() > 0) {
/* 1024 */           boolean isFullMoon = CreatureUtil.isFullMoon(player.field_70170_p);
/*      */           
/* 1026 */           switch (playerEx.getCreatureType()) {
/*      */           case WOLF: 
/*      */           case WOLFMAN: 
/* 1029 */             boolean isWolfman = playerEx.getCreatureType() == TransformCreature.WOLFMAN;
/* 1030 */             if ((!isFullMoon) && (!player.field_71071_by.func_146028_b(Witchery.Items.MOON_CHARM)) && (!ItemMoonCharm.isWolfsbaneActive(player, playerEx)))
/*      */             {
/* 1032 */               Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
/* 1033 */               ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, player, 1.5D, 1.5D, 16);
/*      */             } else {
/* 1035 */               updateWerewolfEffects(player, isWolfman);
/*      */             }
/* 1037 */             break;
/*      */           case NONE: 
/* 1039 */             if ((isFullMoon) && (!player.field_71071_by.func_146028_b(Witchery.Items.MOON_CHARM)) && (!ItemMoonCharm.isWolfsbaneActive(player, playerEx)))
/*      */             {
/* 1041 */               Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLF);
/* 1042 */               ParticleEffect.EXPLODE.send(SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL, player, 1.5D, 1.5D, 16);
/*      */               
/* 1044 */               updateWerewolfEffects(player, false);
/*      */             }
/*      */             
/*      */             break;
/*      */           }
/*      */           
/*      */         }
/*      */         
/* 1052 */         if (playerEx.isVampire()) {
/* 1053 */           if (player.func_70090_H()) {
/* 1054 */             player.func_70050_g(300);
/*      */           }
/*      */           
/* 1057 */           if ((playerEx.getCreatureType() == TransformCreature.BAT) && 
/* 1058 */             (!playerEx.decreaseBloodPower(ExtendedPlayer.VampirePower.BAT.UPKEEP_COST, true))) {
/* 1059 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
/*      */           }
/*      */           
/*      */ 
/* 1063 */           if ((playerEx.getVampireLevel() == 3) && (!player.field_70170_p.func_72935_r())) {
/* 1064 */             if ((playerEx.getVampireQuestCounter() >= 300) || ((playerEx.getVampireQuestCounter() >= 10) && (player.field_71075_bZ.field_75098_d)))
/*      */             {
/* 1066 */               if (playerEx.canIncreaseVampireLevel()) {
/* 1067 */                 playerEx.increaseVampireLevel();
/*      */               }
/*      */             }
/* 1070 */             else if (Config.instance().allowVampireQuests) {
/* 1071 */               playerEx.increaseVampireQuestCounter();
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1076 */           if ((playerEx.getVampireLevel() == 7) && (playerEx.canIncreaseVampireLevel())) {
/* 1077 */             Village closestVillage = player.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), 32);
/*      */             
/*      */ 
/* 1080 */             if ((closestVillage != null) && (playerEx.storeVampireQuestChunk(closestVillage.func_75577_a().field_71574_a >> 4, closestVillage.func_75577_a().field_71573_c >> 4)))
/*      */             {
/*      */ 
/* 1083 */               if (playerEx.getVampireQuestCounter() >= 3) {
/* 1084 */                 playerEx.increaseVampireLevel();
/*      */               } else {
/* 1086 */                 playerEx.increaseVampireQuestCounter();
/* 1087 */                 SoundEffect.NOTE_PLING.playOnlyTo(player, 1.0F, 1.0F);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 1092 */           if (playerEx.isVampireVisionActive()) {
/* 1093 */             player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 400, 0, true));
/*      */           }
/*      */           
/* 1096 */           if (player.func_70644_a(Potion.field_76436_u)) {
/* 1097 */             player.func_82170_o(Potion.field_76436_u.field_76415_H);
/*      */           }
/*      */           
/* 1100 */           if ((player.func_70027_ad()) && (player.func_70644_a(Potion.field_76426_n))) {
/* 1101 */             player.func_70097_a(EntityUtil.DamageSourceVampireFire.SOURCE, 2.0F);
/*      */           }
/*      */           
/* 1104 */           while ((player.func_71024_bL().func_75116_a() < 20) && (playerEx.decreaseBloodPower(5, true))) {
/* 1105 */             player.func_71024_bL().func_75122_a(1, 4.0F);
/*      */           }
/*      */           
/* 1108 */           if ((playerEx.getBloodPower() == 0) && (player.func_71024_bL().func_75116_a() == 0)) {
/* 1109 */             player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, TimeUtil.secsToTicks(10), 8, true));
/*      */             
/* 1111 */             player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, TimeUtil.secsToTicks(10), 1, true));
/*      */             
/* 1113 */             player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, TimeUtil.secsToTicks(10), 1, true));
/*      */           }
/*      */           
/*      */ 
/* 1117 */           if ((CreatureUtil.isInSunlight(player)) && (!player.field_71075_bZ.field_75098_d)) {
/* 1118 */             if ((playerEx.getBloodPower() == 0) && (player.field_70173_aa > 400)) {
/* 1119 */               EntityUtil.instantDeath(player, null);
/*      */             }
/* 1121 */             if (playerEx.getVampireLevel() >= 5) {
/* 1122 */               playerEx.decreaseBloodPower(60, false);
/* 1123 */               player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, TimeUtil.secsToTicks(10), 3, false));
/*      */               
/* 1125 */               player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, TimeUtil.secsToTicks(10), 0, true));
/*      */               
/* 1127 */               player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, TimeUtil.secsToTicks(10), 0, true));
/*      */             }
/*      */             else {
/* 1130 */               playerEx.setBloodPower(0);
/*      */             }
/*      */             
/* 1133 */             if (playerEx.getBloodPower() == 0) {
/* 1134 */               player.func_70015_d(5);
/*      */             }
/*      */           }
/*      */         }
/*      */         else {
/* 1139 */           playerEx.giveHumanBlood(2);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static void updateWerewolfEffects(EntityPlayer player, boolean isWolfman) {
/* 1146 */     player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 400, 0, true));
/* 1147 */     if (player.func_70644_a(Potion.field_76436_u)) {
/* 1148 */       player.func_82170_o(Potion.field_76436_u.field_76415_H);
/*      */     }
/*      */     
/*      */ 
/* 1152 */     for (int slot = isWolfman ? 0 : 1; slot <= 4; slot++) {
/* 1153 */       ItemStack stack = player.func_71124_b(slot);
/* 1154 */       if ((stack != null) && (stack.func_77973_b() != Witchery.Items.MOON_CHARM) && (
/* 1155 */         (player.field_71070_bA == null) || (player.field_71070_bA.field_75152_c == 0) || (slot != 0))) {
/* 1156 */         player.func_70099_a(stack, 1.0F);
/* 1157 */         player.func_70062_b(slot, null);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onLivingFall(LivingFallEvent event)
/*      */   {
/* 1165 */     if ((!event.entity.field_70170_p.field_72995_K) && ((event.entity instanceof EntityPlayer))) {
/* 1166 */       event.distance = Shapeshift.INSTANCE.updateFallState((EntityPlayer)event.entity, event.distance);
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
/* 1172 */     if ((!event.world.field_72995_K) && (event.harvester != null) && (!event.isCanceled())) {
/* 1173 */       ExtendedPlayer playerEx = ExtendedPlayer.get(event.harvester);
/* 1174 */       Shapeshift.INSTANCE.processDigging(event, event.harvester, playerEx);
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent(priority=EventPriority.HIGHEST)
/*      */   public void onLivingHurt(LivingHurtEvent event) {
/* 1180 */     if ((!event.entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled()))
/*      */     {
/* 1182 */       checkForChargeDamage(event);
/*      */       
/* 1184 */       if ((event.entityLiving instanceof EntityPlayer)) {
/* 1185 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/* 1186 */         float playerHealth = player.func_110143_aJ();
/*      */         
/* 1188 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 1189 */         if ((event.source == DamageSource.field_76369_e) && (playerEx.isVampire())) {
/* 1190 */           event.setCanceled(true);
/* 1191 */           return;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1196 */         boolean wolfForm = (playerEx.getWerewolfLevel() > 0) && ((playerEx.getCreatureType() == TransformCreature.WOLF) || (playerEx.getCreatureType() == TransformCreature.WOLFMAN));
/*      */         
/*      */ 
/* 1199 */         if ((wolfForm) && (event.source != DamageSource.field_76380_i) && (event.source != DamageSource.field_76368_d) && (event.source != DamageSource.field_76369_e) && (event.source != DamageSource.field_76379_h))
/*      */         {
/* 1201 */           if (!event.source.func_76347_k()) {
/* 1202 */             float damageReduction = Shapeshift.INSTANCE.getResistance(player, playerEx);
/* 1203 */             event.ammount = Math.max(0.0F, event.ammount - damageReduction);
/*      */           }
/*      */           
/* 1206 */           if (!CreatureUtil.isWerewolf(event.source.func_76364_f())) {
/* 1207 */             if (!CreatureUtil.isSilverDamage(event.source)) {
/* 1208 */               event.ammount = Math.max(Math.min(event.ammount, Shapeshift.INSTANCE.getDamageCap(player, playerEx)), 0.5F);
/*      */             }
/*      */             else
/*      */             {
/* 1212 */               event.ammount += 5.0F;
/*      */             }
/*      */           }
/*      */           
/* 1216 */           if (event.ammount <= 0.0F) {
/* 1217 */             event.setCanceled(true);
/* 1218 */             return;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1223 */         if ((ItemDeathsClothes.isFullSetWorn(player)) && (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == Witchery.Items.DEATH_HAND))
/*      */         {
/* 1225 */           event.ammount = Math.min(event.ammount, 7.0F);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1231 */         float healthAfterDamage = EntityUtil.getHealthAfterDamage(event, playerHealth, player);
/*      */         
/*      */ 
/* 1234 */         if ((player.field_71093_bK == Config.instance().dimensionDreamID) || (WorldProviderDreamWorld.getPlayerIsGhost(player)))
/*      */         {
/* 1236 */           if ((healthAfterDamage <= 0.0F) && (!player.field_71075_bZ.field_75098_d)) {
/* 1237 */             event.setCanceled(true);
/* 1238 */             event.setResult(cpw.mods.fml.common.eventhandler.Event.Result.DENY);
/* 1239 */             WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
/* 1240 */             return;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1245 */         dropItemsOnHit(player);
/*      */         
/* 1247 */         boolean ignoreProtection = (wolfForm) || (((event.source instanceof BoltDamageSource)) && (((BoltDamageSource)event.source).isPoweredDraining));
/*      */         
/* 1249 */         boolean hasHunterSet = ItemHunterClothes.isFullSetWorn(player, false);
/*      */         
/*      */ 
/* 1252 */         if ((hasHunterSet) && (event.source.func_82725_o()) && (player.field_70170_p.field_73012_v.nextDouble() < 0.25D)) {
/* 1253 */           event.setCanceled(true);
/* 1254 */           return;
/*      */         }
/*      */         
/* 1257 */         if ((((event.source instanceof EntityDamageSource)) || (event.source.func_94541_c())) && 
/* 1258 */           (!ignoreProtection))
/*      */         {
/* 1260 */           ItemStack hat = player.func_71124_b(4);
/* 1261 */           if ((hat != null) && (hat.func_77973_b() == Witchery.Items.BABAS_HAT) && 
/* 1262 */             (player.field_71093_bK != Config.instance().dimensionTormentID)) {
/* 1263 */             int TELEPORT_COST = 5;
/* 1264 */             double TELEPORT_CHANCE = 0.25D;
/* 1265 */             int TELEPORT_DISTANCE = 6;
/* 1266 */             if ((player.field_70170_p.field_73012_v.nextDouble() < 0.25D) && (Infusion.aquireEnergy(player.field_70170_p, player, 5, true)))
/*      */             {
/* 1268 */               BlockVoidBramble.teleportRandomly(player.field_70170_p, MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), player, 6);
/*      */               
/*      */ 
/*      */ 
/* 1272 */               event.setCanceled(true);
/* 1273 */               return;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1280 */         if ((event.source instanceof EntityDamageSource))
/*      */         {
/* 1282 */           EntityDamageSource entitySource = (EntityDamageSource)event.source;
/*      */           
/*      */ 
/* 1285 */           ItemStack belt = player.func_71124_b(2);
/* 1286 */           if ((belt != null) && (belt.func_77973_b() == Witchery.Items.BARK_BELT) && (!CreatureUtil.isWoodenDamage(event.source)))
/*      */           {
/* 1288 */             int currentLevel = Math.min(Witchery.Items.BARK_BELT.getChargeLevel(belt), Witchery.Items.BARK_BELT.getMaxChargeLevel(player));
/*      */             
/* 1290 */             if (currentLevel > 0) {
/* 1291 */               World world = player.field_70170_p;
/* 1292 */               Random rand = world.field_73012_v;
/* 1293 */               int cost = (currentLevel > 1) && (rand.nextDouble() < 0.25D) ? 2 : 1;
/* 1294 */               Witchery.Items.BARK_BELT.setChargeLevel(belt, Math.max(currentLevel - cost, 0));
/* 1295 */               event.setCanceled(true);
/*      */               
/* 1297 */               for (int i = 0; i < cost; i++) {
/* 1298 */                 double dx = 1.0D * (rand.nextInt(2) == 0 ? -1 : 1);
/* 1299 */                 double dy = 1.0D * (rand.nextInt(2) == 0 ? -1 : 1);
/* 1300 */                 EntityItem item = new EntityItem(world, player.field_70165_t + dx, player.field_70163_u + 1.5D, player.field_70161_v + dy, new ItemStack(Items.field_151055_y));
/*      */                 
/* 1302 */                 item.field_145804_b = 60;
/* 1303 */                 item.lifespan = 60;
/*      */                 
/* 1305 */                 world.func_72838_d(item);
/*      */               }
/* 1307 */               return;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1312 */           double MOB_SPAWN_CHANCE = 0.25D;
/* 1313 */           if ((player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == Witchery.Items.HUNTSMANS_SPEAR) && (player.func_70632_aY()) && (player.field_70170_p.field_73012_v.nextDouble() < 0.25D) && (entitySource.func_76346_g() != null) && ((entitySource.func_76346_g() instanceof EntityLivingBase)))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1318 */             EntityLivingBase living = (EntityLivingBase)entitySource.func_76346_g();
/* 1319 */             if (living.func_70089_S()) {
/* 1320 */               EntityWolf wolf = new EntityWolf(player.field_70170_p);
/* 1321 */               wolf.func_70012_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70125_A, player.field_70759_as);
/*      */               
/* 1323 */               wolf.func_70624_b(living);
/* 1324 */               wolf.func_70784_b(living);
/* 1325 */               wolf.func_70916_h(true);
/* 1326 */               wolf.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 12000, 1));
/* 1327 */               player.field_70170_p.func_72838_d(wolf);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1332 */           boolean louseUsed = false;
/* 1333 */           for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 1334 */             ItemStack stack = player.field_71071_by.func_70301_a(i);
/* 1335 */             if ((stack != null) && (stack.func_77973_b() == Witchery.Items.PARASYTIC_LOUSE) && (stack.func_77960_j() > 0))
/*      */             {
/* 1337 */               List list = Items.field_151068_bn.func_77834_f(stack.func_77960_j());
/*      */               
/* 1339 */               if ((list != null) && (!list.isEmpty())) {
/* 1340 */                 PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
/* 1341 */                 if ((isPotionAggressive(effect.func_76456_a())) && (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityLivingBase)))
/*      */                 {
/* 1343 */                   ((EntityLivingBase)event.source.func_76346_g()).func_70690_d(effect);
/* 1344 */                 } else { if (effect.func_76456_a() != Potion.field_76428_l.field_76415_H) {
/*      */                     continue;
/*      */                   }
/* 1347 */                   player.func_70690_d(effect);
/*      */                 }
/*      */                 
/* 1350 */                 player.func_70097_a(DamageSource.field_76376_m, 1.0F);
/* 1351 */                 stack.func_77964_b(0);
/* 1352 */                 louseUsed = true;
/* 1353 */                 break;
/*      */               } else {
/* 1355 */                 stack.func_77964_b(0);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1361 */           if ((!louseUsed) && (Witchery.Items.BITING_BELT.isBeltWorn(player))) {
/* 1362 */             ItemStack stack = player.field_71071_by.func_70440_f(1);
/* 1363 */             if ((stack != null) && (stack.func_77942_o())) {
/* 1364 */               boolean done = false;
/* 1365 */               if (stack.func_77978_p().func_74764_b("WITCPotion")) {
/* 1366 */                 int potion = stack.func_77978_p().func_74762_e("WITCPotion");
/*      */                 
/* 1368 */                 List list = Items.field_151068_bn.func_77834_f(potion);
/* 1369 */                 if ((list != null) && (!list.isEmpty())) {
/* 1370 */                   PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
/* 1371 */                   if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() != Potion.field_76428_l.field_76415_H))
/*      */                   {
/* 1373 */                     done = true;
/* 1374 */                     if ((isPotionAggressive(effect.func_76456_a())) && (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityLivingBase)))
/*      */                     {
/*      */ 
/* 1377 */                       ((EntityLivingBase)event.source.func_76346_g()).func_70690_d(effect);
/*      */                     } else {
/* 1379 */                       player.func_70690_d(effect);
/*      */                     }
/* 1381 */                     player.func_70097_a(DamageSource.field_76376_m, 1.0F);
/* 1382 */                     stack.func_77978_p().func_82580_o("WITCPotion");
/* 1383 */                     if (stack.func_77978_p().func_82582_d()) {
/* 1384 */                       stack.func_77982_d(null);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/* 1390 */               if ((!done) && (stack.func_77978_p().func_74764_b("WITCPotion2"))) {
/* 1391 */                 int potion = stack.func_77978_p().func_74762_e("WITCPotion2");
/*      */                 
/* 1393 */                 List list = Items.field_151068_bn.func_77834_f(potion);
/* 1394 */                 if ((list != null) && (!list.isEmpty())) {
/* 1395 */                   PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
/* 1396 */                   if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() != Potion.field_76428_l.field_76415_H))
/*      */                   {
/* 1398 */                     if ((isPotionAggressive(effect.func_76456_a())) && (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityLivingBase)))
/*      */                     {
/*      */ 
/* 1401 */                       ((EntityLivingBase)event.source.func_76346_g()).func_70690_d(effect);
/*      */                     } else {
/* 1403 */                       player.func_70690_d(effect);
/*      */                     }
/* 1405 */                     player.func_70097_a(DamageSource.field_76376_m, 1.0F);
/* 1406 */                     stack.func_77978_p().func_82580_o("WITCPotion2");
/* 1407 */                     if (stack.func_77978_p().func_82582_d()) {
/* 1408 */                       stack.func_77982_d(null);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1417 */           checkForRendArmor(event);
/*      */           
/*      */ 
/* 1420 */           if ((!ignoreProtection) && (!playerEx.isVampire())) {
/* 1421 */             ItemStack vampiricPoppetStack = ItemPoppet.findBoundPoppetInWorld(Witchery.Items.POPPET.vampiricPoppet, player, 66, true, false);
/*      */             
/* 1423 */             if (vampiricPoppetStack != null)
/*      */             {
/* 1425 */               EntityWitchHunter.blackMagicPerformed(player);
/* 1426 */               EntityLivingBase targetEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(player.field_70170_p, player, vampiricPoppetStack, Integer.valueOf(2));
/*      */               
/* 1428 */               if ((targetEntity != null) && (!Witchery.Items.POPPET.voodooProtectionActivated(player, vampiricPoppetStack, targetEntity, true, false)))
/*      */               {
/*      */ 
/*      */ 
/* 1432 */                 if (!ItemHunterClothes.isFullSetWorn(targetEntity, false))
/*      */                 {
/* 1434 */                   if ((targetEntity instanceof EntityPlayer)) {
/* 1435 */                     targetEntity.func_70097_a(event.source, event.ammount);
/* 1436 */                     event.setCanceled(true);
/*      */                   }
/* 1438 */                   else if (((targetEntity instanceof EntityLiving)) && (targetEntity.func_70089_S())) {
/* 1439 */                     targetEntity.func_70097_a(event.source, Math.min(event.ammount, 15.0F));
/*      */                     
/* 1441 */                     if (!targetEntity.func_70089_S()) {
/* 1442 */                       Witchery.Items.TAGLOCK_KIT.clearTaglock(vampiricPoppetStack, Integer.valueOf(2));
/*      */                     }
/* 1444 */                     event.setCanceled(true);
/*      */                   }
/*      */                   
/* 1447 */                   return;
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1454 */           if (!louseUsed) {
/* 1455 */             for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
/* 1456 */               ItemStack stack = player.field_71071_by.func_70301_a(i);
/* 1457 */               if ((stack != null) && (stack.func_77973_b() == Witchery.Items.PARASYTIC_LOUSE) && (stack.func_77960_j() > 0))
/*      */               {
/* 1459 */                 List list = Items.field_151068_bn.func_77834_f(stack.func_77960_j());
/*      */                 
/* 1461 */                 if ((list != null) && (!list.isEmpty())) {
/* 1462 */                   PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
/* 1463 */                   if (effect.func_76456_a() == Potion.field_76428_l.field_76415_H) {
/* 1464 */                     player.func_70690_d(effect);
/*      */                   }
/*      */                   
/* 1467 */                   player.func_70097_a(DamageSource.field_76376_m, 1.0F);
/* 1468 */                   stack.func_77964_b(0);
/* 1469 */                   louseUsed = true;
/* 1470 */                   break;
/*      */                 }
/* 1472 */                 stack.func_77964_b(0);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1479 */           if ((!louseUsed) && (Witchery.Items.BITING_BELT.isBeltWorn(player))) {
/* 1480 */             ItemStack stack = player.field_71071_by.func_70440_f(1);
/* 1481 */             if ((stack != null) && (stack.func_77942_o())) {
/* 1482 */               boolean done = false;
/* 1483 */               if (stack.func_77978_p().func_74764_b("WITCPotion")) {
/* 1484 */                 int potion = stack.func_77978_p().func_74762_e("WITCPotion");
/*      */                 
/* 1486 */                 List list = Items.field_151068_bn.func_77834_f(potion);
/* 1487 */                 if ((list != null) && (!list.isEmpty())) {
/* 1488 */                   PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
/* 1489 */                   if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() == Potion.field_76428_l.field_76415_H))
/*      */                   {
/* 1491 */                     done = true;
/* 1492 */                     player.func_70690_d(effect);
/* 1493 */                     player.func_70097_a(DamageSource.field_76376_m, 1.0F);
/* 1494 */                     stack.func_77978_p().func_82580_o("WITCPotion");
/* 1495 */                     if (stack.func_77978_p().func_82582_d()) {
/* 1496 */                       stack.func_77982_d(null);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/* 1502 */               if ((!done) && (stack.func_77978_p().func_74764_b("WITCPotion2"))) {
/* 1503 */                 int potion = stack.func_77978_p().func_74762_e("WITCPotion2");
/*      */                 
/* 1505 */                 List list = Items.field_151068_bn.func_77834_f(potion);
/* 1506 */                 if ((list != null) && (!list.isEmpty())) {
/* 1507 */                   PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
/* 1508 */                   if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() == Potion.field_76428_l.field_76415_H))
/*      */                   {
/* 1510 */                     player.func_70690_d(effect);
/* 1511 */                     player.func_70097_a(DamageSource.field_76376_m, 1.0F);
/* 1512 */                     stack.func_77978_p().func_82580_o("WITCPotion2");
/* 1513 */                     if (stack.func_77978_p().func_82582_d()) {
/* 1514 */                       stack.func_77982_d(null);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1524 */         if ((healthAfterDamage <= 0.0F) && (!wolfForm) && (!playerEx.isVampire())) {
/* 1525 */           Log.instance().debug(String.format("player terminal damage", new Object[0]));
/* 1526 */           if ((event.source.field_76373_n.equals(DamageSource.field_76379_h.field_76373_n)) || (event.source.field_76373_n.equals(DamageSource.field_82729_p.field_76373_n)))
/*      */           {
/* 1528 */             Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.earthPoppet, event, false);
/*      */           }
/* 1530 */           else if ((event.source.func_76347_k()) || (event.source.func_94541_c())) {
/* 1531 */             Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.firePoppet, event, true);
/*      */             
/* 1533 */             if (event.isCanceled()) {
/* 1534 */               player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 60, 0));
/*      */             }
/*      */           }
/* 1537 */           else if (event.source.field_76373_n.equals(DamageSource.field_76369_e.field_76373_n)) {
/* 1538 */             Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.waterPoppet, event, true);
/*      */             
/* 1540 */             if (event.isCanceled()) {
/* 1541 */               player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 60, 0));
/*      */             }
/*      */           }
/* 1544 */           else if (event.source.field_76373_n.equals(DamageSource.field_76366_f.field_76373_n)) {
/* 1545 */             Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.foodPoppet, event, true);
/*      */             
/* 1547 */             if (event.isCanceled()) {
/* 1548 */               player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 60, 0));
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1553 */           if (!event.isCanceled())
/*      */           {
/* 1555 */             Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.deathPoppet, event, true, ignoreProtection);
/*      */             
/* 1557 */             if (event.isCanceled()) {
/* 1558 */               if ((player.func_70027_ad()) || (event.source.func_76347_k()) || (event.source.func_94541_c())) {
/* 1559 */                 player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 120, 0));
/*      */               }
/* 1561 */               else if (event.source.field_76373_n.equals(DamageSource.field_76369_e.field_76373_n)) {
/* 1562 */                 player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 120, 0));
/*      */               }
/* 1564 */               else if (event.source.field_76373_n.equals(DamageSource.field_76366_f.field_76373_n)) {
/* 1565 */                 player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 120, 0));
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1572 */         if ((!event.isCanceled()) && (healthAfterDamage <= 2.0F) && 
/* 1573 */           (event.source.field_76373_n.equals(DamageSource.field_76366_f.field_76373_n))) {
/* 1574 */           Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.foodPoppet, event, true);
/*      */           
/* 1576 */           if (event.isCanceled()) {
/* 1577 */             player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 60, 0));
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1583 */         com.emoniph.witchery.familiar.Familiar.handlePlayerHurt(event, player);
/* 1584 */         checkForWolfInfection(event, healthAfterDamage);
/*      */         
/* 1586 */         Witchery.Items.POPPET.checkForArmorProtection(player);
/*      */       } else {
/* 1588 */         if (((event.entityLiving instanceof EntityGoblin)) && 
/* 1589 */           (event.source == DamageSource.field_76379_h)) {
/* 1590 */           event.setCanceled(true);
/* 1591 */           return;
/*      */         }
/*      */         
/*      */ 
/* 1595 */         if (((event.entityLiving instanceof EntityVillager)) && 
/* 1596 */           (event.source != null) && (event.source.func_76346_g() != null) && (
/* 1597 */           ((event.source.func_76346_g() instanceof EntityVillageGuard)) || ((event.source.func_76346_g() instanceof EntityWitchHunter))))
/*      */         {
/* 1599 */           event.setCanceled(true);
/* 1600 */           return;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1605 */         if ((Config.instance().isReduceZombeVillagerDamageActive()) && ((event.entityLiving instanceof EntityVillager)))
/*      */         {
/* 1607 */           if ((event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityZombie))) {
/* 1608 */             event.ammount = 0.5F;
/*      */           }
/*      */         }
/*      */         
/* 1612 */         checkForRendArmor(event);
/* 1613 */         checkForWolfInfection(event, EntityUtil.getHealthAfterDamage(event, event.entityLiving.func_110143_aJ(), event.entityLiving));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void checkForRendArmor(LivingHurtEvent event)
/*      */   {
/* 1620 */     if ((event.source.field_76373_n.equals("player")) && (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityPlayer)))
/*      */     {
/* 1622 */       EntityPlayer attackingPlayer = (EntityPlayer)event.source.func_76346_g();
/* 1623 */       ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
/* 1624 */       Shapeshift.INSTANCE.rendArmor(event.entityLiving, attackingPlayer, playerEx);
/*      */     }
/*      */   }
/*      */   
/*      */   public void checkForWolfInfection(LivingHurtEvent event, float health) {
/* 1629 */     if (!event.isCanceled()) {
/* 1630 */       if ((event.source.field_76373_n.equals("player")) && (event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityPlayer)))
/*      */       {
/* 1632 */         EntityPlayer attackingPlayer = (EntityPlayer)event.source.func_76346_g();
/* 1633 */         ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
/* 1634 */         Shapeshift.INSTANCE.processWolfInfection(event.entityLiving, attackingPlayer, playerEx, health);
/* 1635 */       } else if ((event.source.field_76373_n.equals("mob")) && ((event.source.func_76364_f() instanceof EntityWolfman)))
/*      */       {
/* 1637 */         Shapeshift.INSTANCE.processWolfInfection(event.entityLiving, (EntityWolfman)event.source.func_76364_f(), health);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void checkForChargeDamage(LivingHurtEvent event)
/*      */   {
/* 1644 */     if ((event.source.field_76373_n.equals("player")) && (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityPlayer)))
/*      */     {
/* 1646 */       EntityPlayer attackingPlayer = (EntityPlayer)event.source.func_76346_g();
/* 1647 */       ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
/* 1648 */       Shapeshift.INSTANCE.updateChargeDamage(event, attackingPlayer, playerEx);
/*      */     }
/*      */   }
/*      */   
/*      */   private static boolean isPotionAggressive(int potionID) {
/* 1653 */     return (potionID == Potion.field_76419_f.field_76415_H) || (potionID == Potion.field_76421_d.field_76415_H) || (potionID == Potion.field_76436_u.field_76415_H) || (potionID == Potion.field_82731_v.field_76415_H) || (potionID == Potion.field_76437_t.field_76415_H) || (potionID == Potion.field_76438_s.field_76415_H);
/*      */   }
/*      */   
/*      */ 
/*      */   private static void dropItemsOnHit(EntityPlayer player)
/*      */   {
/* 1659 */     for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
/* 1660 */       ItemStack stack = player.field_71071_by.field_70462_a[i];
/* 1661 */       if (Witchery.Items.GENERIC.itemBatBall.isMatch(stack)) {
/* 1662 */         player.func_71019_a(stack, true);
/* 1663 */         player.field_71071_by.field_70462_a[i] = null;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent(priority=EventPriority.HIGH)
/*      */   public void onLivingDeath(LivingDeathEvent event) {
/* 1670 */     if ((event.entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled())) {
/* 1671 */       if ((event.entityLiving instanceof EntityPlayer)) {
/* 1672 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/* 1673 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 1674 */         if (playerEx.isVampire()) {
/* 1675 */           event.setCanceled(true);
/* 1676 */           player.func_70606_j(1.0F);
/* 1677 */           return;
/*      */         }
/*      */       }
/* 1680 */     } else if ((!event.entityLiving.field_70170_p.field_72995_K) && ((!event.isCancelable()) || (!event.isCanceled())))
/*      */     {
/* 1682 */       if ((event.entityLiving instanceof EntityPlayer)) {
/* 1683 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/* 1684 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 1685 */         if (playerEx.isVampire())
/*      */         {
/* 1687 */           if (player.func_110143_aJ() > 0.0F) {
/* 1688 */             event.setCanceled(true);
/* 1689 */             return;
/*      */           }
/* 1691 */           if (!CreatureUtil.checkForVampireDeath(player, event.source)) {
/* 1692 */             event.setCanceled(true);
/* 1693 */             return;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1698 */       dropExtraItemsFromNBT(event);
/*      */       
/*      */ 
/* 1701 */       Entity attacker = event.source.func_76346_g();
/* 1702 */       if ((attacker != null) && ((attacker instanceof EntityPlayer))) {
/* 1703 */         EntityPlayer player = (EntityPlayer)attacker;
/* 1704 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 1705 */         if (((event.entity instanceof EntityHornedHuntsman)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
/*      */         {
/* 1707 */           playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
/*      */         }
/*      */         
/* 1710 */         if (playerEx.hasVampireBook()) {
/* 1711 */           boolean dropPage = ((event.entityLiving instanceof IBossDisplayData)) || (((!(event.entityLiving instanceof EntityPigZombie)) && (!(event.entityLiving instanceof EntityEnderman))) || ((player.field_70170_p.field_73012_v.nextDouble() < 0.09D) || ((com.emoniph.witchery.brewing.potions.PotionParalysis.isVillager(event.entityLiving)) && (player.field_70170_p.field_73012_v.nextDouble() < 0.1D)) || ((event.entityLiving.func_70662_br()) && (player.field_70170_p.field_73012_v.nextDouble() < 0.02D))));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1718 */           if (dropPage) {
/* 1719 */             EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0D, event.entityLiving.field_70161_v, Witchery.Items.GENERIC.itemVampireBookPage.createStack());
/*      */             
/*      */ 
/* 1722 */             event.entityLiving.field_70170_p.func_72838_d(entityItem);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1728 */       Entity entitySource = event.source.func_76364_f();
/* 1729 */       if ((entitySource != null) && ((entitySource instanceof EntityPlayer))) {
/* 1730 */         EntityPlayer player = (EntityPlayer)entitySource;
/* 1731 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 1732 */         boolean hasArthana = (player.field_71071_by.func_70448_g() != null) && (player.field_71071_by.func_70448_g().func_77973_b() == Witchery.Items.ARTHANA);
/*      */         
/* 1734 */         boolean hasCaneSword = (player.field_71071_by.func_70448_g() != null) && (player.field_71071_by.func_70448_g().func_77973_b() == Witchery.Items.CANE_SWORD) && (Witchery.Items.CANE_SWORD.isDrawn(player)) && (playerEx.isVampire());
/*      */         
/*      */ 
/* 1737 */         ItemStack itemstack = null;
/*      */         
/* 1739 */         Shapeshift.INSTANCE.processCreatureKilled(event, player, playerEx);
/*      */         
/* 1741 */         if ((playerEx.getWerewolfLevel() == 5) && (Shapeshift.INSTANCE.isWolfAnimalForm(playerEx)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
/*      */         {
/* 1743 */           if (((event.entity instanceof net.minecraft.entity.monster.IMob)) && (!player.field_70122_E)) {
/* 1744 */             playerEx.increaseWolfmanQuestCounter();
/*      */           }
/* 1746 */         } else if ((playerEx.getWerewolfLevel() == 8) && (playerEx.getCreatureType() == TransformCreature.WOLFMAN) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
/*      */         {
/*      */ 
/* 1749 */           if ((event.entity instanceof EntityPigZombie)) {
/* 1750 */             playerEx.increaseWolfmanQuestCounter();
/*      */           }
/* 1752 */         } else if ((playerEx.getWerewolfLevel() == 9) && (Shapeshift.INSTANCE.isWolfAnimalForm(playerEx)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
/*      */         {
/* 1754 */           if (((event.entity instanceof EntityVillager)) || ((event.entity instanceof EntityPlayer))) {
/* 1755 */             playerEx.increaseWolfmanQuestCounter();
/*      */           }
/*      */         }
/*      */         
/* 1759 */         if ((playerEx.getVampireLevel() == 5) && (playerEx.canIncreaseVampireLevel()) && 
/* 1760 */           ((event.entity instanceof net.minecraft.entity.monster.EntityBlaze))) {
/* 1761 */           if (playerEx.getVampireQuestCounter() >= 19) {
/* 1762 */             playerEx.increaseVampireLevel();
/*      */           } else {
/* 1764 */             playerEx.increaseVampireQuestCounter();
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1769 */         int baseLooting = net.minecraft.enchantment.EnchantmentHelper.func_77519_f(player);
/* 1770 */         double lootingFactor = 1.0D + baseLooting;
/* 1771 */         double halfLooting = 1.0D + baseLooting / 2;
/*      */         
/* 1773 */         if (InfusedBrewEffect.getActiveBrew(player) == InfusedBrewEffect.Grave) {
/* 1774 */           float maxHealth = player.func_110138_aP();
/* 1775 */           if ((event.entityLiving instanceof EntityPlayer)) {
/* 1776 */             player.func_71024_bL().func_75122_a(20, 0.9F);
/* 1777 */             player.func_70691_i(maxHealth * 0.6F);
/* 1778 */           } else if ((event.entityLiving instanceof EntityVillager)) {
/* 1779 */             player.func_71024_bL().func_75122_a(20, 0.9F);
/* 1780 */             player.func_70691_i(maxHealth * 0.4F);
/* 1781 */           } else if ((event.entityLiving instanceof net.minecraft.entity.passive.EntityAnimal)) {
/* 1782 */             player.func_71024_bL().func_75122_a(8, 0.8F);
/* 1783 */             player.func_70691_i(maxHealth * 0.1F);
/*      */           }
/*      */         }
/*      */         
/* 1787 */         Witchery.Items.BLOOD_GOBLET.handleCreatureDeath(player.field_70170_p, player, event.entityLiving);
/*      */         
/* 1789 */         boolean allowDrops = !EntityUtil.isNoDrops(event.entityLiving);
/* 1790 */         if (allowDrops) {
/* 1791 */           if ((event.entityLiving instanceof EntityVillager)) {
/* 1792 */             ExtendedVillager villagerEx = ExtendedVillager.get((EntityVillager)event.entityLiving);
/* 1793 */             playerEx.fillBloodReserve(villagerEx.getBlood());
/* 1794 */           } else if ((event.entityLiving instanceof EntityVillageGuard)) {
/* 1795 */             EntityVillageGuard guard = (EntityVillageGuard)event.entityLiving;
/* 1796 */             playerEx.fillBloodReserve(guard.getBlood());
/* 1797 */           } else if ((event.entityLiving instanceof EntityPlayer)) {
/* 1798 */             ExtendedPlayer targetEx = ExtendedPlayer.get((EntityPlayer)event.entityLiving);
/* 1799 */             playerEx.fillBloodReserve(targetEx.getHumanBlood());
/* 1800 */           } else if ((event.entityLiving instanceof EntitySkeleton)) {
/* 1801 */             EntitySkeleton skeleton = (EntitySkeleton)event.entityLiving;
/* 1802 */             if ((hasArthana) && (skeleton.func_82202_m() == 0) && (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.05D * lootingFactor, 1.0D)))
/*      */             {
/*      */ 
/*      */ 
/* 1806 */               itemstack = new ItemStack(Items.field_151144_bL, 1, 0);
/* 1807 */             } else if ((hasArthana) && (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.04D * lootingFactor, 1.0D)))
/*      */             {
/*      */ 
/* 1810 */               itemstack = Witchery.Items.GENERIC.itemSpectralDust.createStack();
/*      */             }
/* 1812 */           } else if ((event.entityLiving instanceof EntityZombie)) {
/* 1813 */             if ((hasArthana) && (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.02D * lootingFactor, 1.0D)))
/*      */             {
/*      */ 
/* 1816 */               itemstack = new ItemStack(Items.field_151144_bL, 1, 2);
/* 1817 */             } else if ((hasArthana) && (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.03D * lootingFactor, 1.0D)))
/*      */             {
/*      */ 
/* 1820 */               itemstack = Witchery.Items.GENERIC.itemSpectralDust.createStack();
/*      */             }
/* 1822 */           } else if ((event.entityLiving instanceof EntityCreeper)) {
/* 1823 */             if ((hasArthana) && (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.01D * lootingFactor, 1.0D)))
/*      */             {
/*      */ 
/* 1826 */               itemstack = new ItemStack(Items.field_151144_bL, 1, 4);
/* 1827 */             } else if (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min((hasArthana ? 0.08D : 0.02D) * lootingFactor, 1.0D))
/*      */             {
/* 1829 */               itemstack = Witchery.Items.GENERIC.itemCreeperHeart.createStack();
/*      */             }
/* 1831 */           } else if ((event.entityLiving instanceof EntityDemon)) {
/* 1832 */             if ((hasArthana) && (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.33D * halfLooting, 1.0D)))
/*      */             {
/*      */ 
/* 1835 */               itemstack = Witchery.Items.GENERIC.itemDemonHeart.createStack();
/*      */             }
/* 1837 */           } else if ((event.entityLiving instanceof EntityPlayer)) {
/* 1838 */             if ((hasArthana) && (event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.1D * halfLooting, 1.0D)))
/*      */             {
/*      */ 
/* 1841 */               EntityPlayer victim = (EntityPlayer)event.entityLiving;
/* 1842 */               itemstack = new ItemStack(Items.field_151144_bL, 1, 3);
/* 1843 */               NBTTagCompound tag = itemstack.func_77978_p();
/* 1844 */               if (tag == null) {
/* 1845 */                 tag = new NBTTagCompound();
/* 1846 */                 itemstack.func_77982_d(tag);
/*      */               }
/* 1848 */               tag.func_74778_a("SkullOwner", victim.func_70005_c_());
/*      */             }
/* 1850 */           } else if ((event.entityLiving instanceof net.minecraft.entity.passive.EntityBat)) {
/* 1851 */             if (player.field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
/*      */             {
/* 1853 */               itemstack = Witchery.Items.GENERIC.itemBatWool.createStack();
/*      */             }
/* 1855 */           } else if ((event.entityLiving instanceof EntityWolf)) {
/* 1856 */             if (player.field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
/*      */             {
/* 1858 */               itemstack = Witchery.Items.GENERIC.itemDogTongue.createStack();
/*      */             }
/*      */             
/* 1861 */             if (player.field_70170_p.field_73012_v.nextInt(12) <= Math.min(baseLooting, 3)) {
/* 1862 */               event.entityLiving.func_70099_a(new ItemStack(Witchery.Blocks.WOLFHEAD, 1, 0), 0.0F);
/*      */             }
/* 1864 */           } else if ((event.entityLiving instanceof EntityOwl)) {
/* 1865 */             if (!((EntityOwl)event.entityLiving).isTemp()) if (player.field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.5D : baseLooting > 0 ? 1.0D : 0.2D))
/*      */               {
/*      */ 
/* 1868 */                 itemstack = Witchery.Items.GENERIC.itemOwletsWing.createStack();
/*      */               }
/* 1870 */           } else if ((event.entityLiving instanceof EntitySheep)) {
/* 1871 */             if ((CreatureUtil.isWerewolf(entitySource, false)) && (!((EntitySheep)event.entityLiving).func_70631_g_()))
/*      */             {
/* 1873 */               if (event.entityLiving.field_70170_p.field_73012_v.nextInt(4) != 0) {
/* 1874 */                 itemstack = Witchery.Items.GENERIC.itemMuttonRaw.createStack();
/*      */               }
/*      */             }
/* 1877 */           } else if ((event.entityLiving instanceof EntityToad)) {
/* 1878 */             if (!((EntityToad)event.entityLiving).isTemp()) if (player.field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.5D : baseLooting > 0 ? 1.0D : 0.2D))
/*      */               {
/*      */ 
/* 1881 */                 itemstack = Witchery.Items.GENERIC.itemToeOfFrog.createStack();
/*      */               }
/*      */           } else {
/*      */             try {
/* 1885 */               Class theClass = event.entityLiving.getClass();
/* 1886 */               if (theClass != null) {
/* 1887 */                 String name = theClass.getSimpleName();
/* 1888 */                 if ((name != null) && (!name.isEmpty())) {
/* 1889 */                   String upperName = name.toUpperCase(java.util.Locale.ROOT);
/* 1890 */                   if ((upperName.contains("WOLF")) || (name.contains("Dog")) || (name.contains("Fox"))) {
/* 1891 */                     if (player.field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
/*      */                     {
/*      */ 
/* 1894 */                       itemstack = Witchery.Items.GENERIC.itemDogTongue.createStack();
/*      */                     }
/*      */                     
/* 1897 */                     if (((upperName.contains("WOLF")) || (name.contains("Dog"))) && (player.field_70170_p.field_73012_v.nextInt(12) <= Math.min(baseLooting, 3)))
/*      */                     {
/* 1899 */                       event.entityLiving.func_70099_a(new ItemStack(Witchery.Blocks.WOLFHEAD, 1, 0), 0.0F);
/*      */                     }
/*      */                   }
/* 1902 */                   else if ((upperName.contains("FIREBAT")) || (name.contains("Bat"))) {
/* 1903 */                     if (player.field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
/*      */                     {
/*      */ 
/* 1906 */                       itemstack = Witchery.Items.GENERIC.itemBatWool.createStack();
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             } catch (Exception e) {
/* 1912 */               Log.instance().debug(String.format("Exception occurred while determining dead creature type: %s", new Object[] { e.toString() }));
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1919 */         if (itemstack != null) {
/* 1920 */           EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0D, event.entityLiving.field_70161_v, itemstack);
/*      */           
/*      */ 
/* 1923 */           event.entityLiving.field_70170_p.func_72838_d(entityItem);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void dropExtraItemsFromNBT(LivingDeathEvent event) {
/* 1930 */     if (!event.entityLiving.field_70170_p.field_72995_K) {
/* 1931 */       NBTTagCompound nbtEntityData = event.entityLiving.getEntityData();
/* 1932 */       if (nbtEntityData.func_74764_b("WITCExtraDrops")) {
/* 1933 */         NBTTagList nbtExtraDrops = nbtEntityData.func_150295_c("WITCExtraDrops", 10);
/*      */         
/* 1935 */         for (int i = 0; i < nbtExtraDrops.func_74745_c(); i++) {
/* 1936 */           NBTTagCompound nbtTag = nbtExtraDrops.func_150305_b(i);
/* 1937 */           ItemStack extraStack = ItemStack.func_77949_a(nbtTag);
/* 1938 */           if (extraStack != null) {
/* 1939 */             EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0D, event.entityLiving.field_70161_v, extraStack);
/*      */             
/*      */ 
/* 1942 */             event.entityLiving.field_70170_p.func_72838_d(entityItem);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/GenericEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */