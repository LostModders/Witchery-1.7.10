/*     */ package com.emoniph.witchery.common;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.network.PacketPlayerStyle;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class ServerTickEvents
/*     */ {
/*  29 */   public static final ArrayList<ServerTickTask> TASKS = new ArrayList();
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onServerTick(TickEvent.ServerTickEvent event) {
/*  33 */     if ((event.side == Side.SERVER) && (event.phase == TickEvent.Phase.START) && 
/*  34 */       (TASKS.size() > 0)) {
/*  35 */       ArrayList<ServerTickTask> completedTasks = new ArrayList();
/*  36 */       for (ServerTickTask task : TASKS) {
/*  37 */         if (task.process()) {
/*  38 */           completedTasks.add(task);
/*     */         }
/*     */       }
/*     */       
/*  42 */       for (ServerTickTask task : completedTasks) {
/*  43 */         TASKS.remove(task);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerTick(TickEvent.PlayerTickEvent event)
/*     */   {
/*  51 */     if ((event.side == Side.SERVER) && (!event.player.field_70170_p.field_72995_K)) {
/*  52 */       if (event.phase == TickEvent.Phase.START) {
/*  53 */         Collection<PotionEffect> activeEffects = event.player.func_70651_bq();
/*  54 */         ExtendedPlayer playerExt = ExtendedPlayer.get(event.player);
/*  55 */         if (playerExt != null) {
/*  56 */           playerExt.updateWorship();
/*  57 */           if (activeEffects.size() > 0) {
/*  58 */             playerExt.cacheIncurablePotionEffect(activeEffects);
/*     */           }
/*  60 */           playerExt.checkSleep(true);
/*     */         }
/*  62 */       } else if (event.phase == TickEvent.Phase.END) {
/*  63 */         ExtendedPlayer playerExt = ExtendedPlayer.get(event.player);
/*  64 */         if (playerExt != null) {
/*  65 */           playerExt.restoreIncurablePotionEffects();
/*  66 */           playerExt.checkSleep(false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class ServerTickTask
/*     */   {
/*     */     protected final World world;
/*     */     
/*     */     public ServerTickTask(World world) {
/*  77 */       this.world = world;
/*     */     }
/*     */     
/*     */     public abstract boolean process();
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
/*  85 */     EntityPlayer player = event.player;
/*  86 */     World world = event.player.field_70170_p;
/*  87 */     Shapeshift.INSTANCE.initCurrentShift(player);
/*  88 */     Infusion.syncPlayer(world, player);
/*     */     
/*  90 */     ExtendedPlayer.get(player).scheduleSync();
/*     */     
/*     */ 
/*  93 */     Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), world.field_73011_w.field_76574_g);
/*     */     
/*     */ 
/*     */ 
/*  97 */     if ((player.field_71093_bK != Config.instance().dimensionDreamID) && (WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) && (!WorldProviderDreamWorld.getPlayerIsGhost(player)))
/*     */     {
/*     */ 
/* 100 */       WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
/* 101 */     } else if ((player.field_71093_bK == Config.instance().dimensionDreamID) && (!WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)))
/*     */     {
/* 103 */       WorldProviderDreamWorld.changeDimension(player, 0);
/* 104 */       WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
/*     */   {
/* 111 */     if (!event.player.field_70170_p.field_72995_K) {
/* 112 */       NBTTagCompound nbtPlayer = Infusion.getNBT(event.player);
/* 113 */       if (nbtPlayer.func_74764_b("WITCPoSpawn")) {
/* 114 */         NBTTagList nbtRestoredEffectList = nbtPlayer.func_150295_c("WITCPoSpawn", 10);
/* 115 */         if (nbtRestoredEffectList.func_74745_c() > 0) {
/* 116 */           for (int i = 0; i < nbtRestoredEffectList.func_74745_c(); i++) {
/* 117 */             PotionEffect restoredEffect = PotionEffect.func_82722_b(nbtRestoredEffectList.func_150305_b(i));
/*     */             
/* 119 */             if (!event.player.func_82165_m(restoredEffect.func_76456_a())) {
/* 120 */               event.player.func_70690_d(restoredEffect);
/*     */             }
/*     */           }
/*     */         }
/* 124 */         nbtPlayer.func_82580_o("WITCPoSpawn");
/*     */       }
/*     */       
/* 127 */       EntityPlayer player = event.player;
/* 128 */       World world = event.player.field_70170_p;
/*     */       
/* 130 */       Shapeshift.INSTANCE.initCurrentShift(player);
/* 131 */       Infusion.syncPlayer(world, player);
/*     */       
/*     */ 
/* 134 */       ExtendedPlayer.get(player).scheduleSync();
/* 135 */       Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), world.field_73011_w.field_76574_g);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
/* 141 */     if (!event.player.field_70170_p.field_72995_K) {
/* 142 */       EntityPlayer player = event.player;
/*     */       
/* 144 */       if ((player != null) && (player.field_70170_p != null) && (!player.field_70170_p.field_72995_K))
/*     */       {
/*     */ 
/* 147 */         long nextUpdate = MinecraftServer.func_130071_aq() + 1500L;
/* 148 */         ExtendedPlayer.get(player).scheduleSync();
/* 149 */         for (Object obj : player.field_70170_p.field_73010_i) {
/* 150 */           EntityPlayer otherPlayer = (EntityPlayer)obj;
/* 151 */           NBTTagCompound nbtOtherPlayer = Infusion.getNBT(otherPlayer);
/* 152 */           if (otherPlayer != player)
/*     */           {
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
/* 165 */             nbtOtherPlayer.func_74772_a("WITCResyncLook", nextUpdate);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 172 */         NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 177 */         Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), player.field_70170_p.field_73011_w.field_76574_g);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 183 */         if ((player.field_71093_bK != Config.instance().dimensionDreamID) && (WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) && (!WorldProviderDreamWorld.getPlayerIsGhost(player)))
/*     */         {
/*     */ 
/* 186 */           WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/ServerTickEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */