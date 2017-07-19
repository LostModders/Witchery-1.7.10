/*     */ package com.emoniph.witchery.network;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPipeline
/*     */ {
/*     */   private SimpleNetworkWrapper CHANNEL;
/*     */   
/*     */   public void preInit()
/*     */   {
/*  24 */     this.CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("witchery".toLowerCase());
/*     */   }
/*     */   
/*     */   public void init() {
/*  28 */     this.CHANNEL.registerMessage(PacketBrewPrepared.Handler.class, PacketBrewPrepared.class, 1, Side.SERVER);
/*  29 */     this.CHANNEL.registerMessage(PacketParticles.Handler.class, PacketParticles.class, 2, Side.CLIENT);
/*  30 */     this.CHANNEL.registerMessage(PacketCamPos.Handler.class, PacketCamPos.class, 3, Side.CLIENT);
/*  31 */     this.CHANNEL.registerMessage(PacketItemUpdate.Handler.class, PacketItemUpdate.class, 4, Side.SERVER);
/*  32 */     this.CHANNEL.registerMessage(PacketPlayerStyle.Handler.class, PacketPlayerStyle.class, 5, Side.CLIENT);
/*  33 */     this.CHANNEL.registerMessage(PacketPlayerSync.Handler.class, PacketPlayerSync.class, 6, Side.CLIENT);
/*  34 */     this.CHANNEL.registerMessage(PacketPushTarget.Handler.class, PacketPushTarget.class, 7, Side.CLIENT);
/*  35 */     this.CHANNEL.registerMessage(PacketSound.Handler.class, PacketSound.class, 8, Side.CLIENT);
/*  36 */     this.CHANNEL.registerMessage(PacketSpellPrepared.Handler.class, PacketSpellPrepared.class, 9, Side.SERVER);
/*  37 */     this.CHANNEL.registerMessage(PacketClearFallDamage.Handler.class, PacketClearFallDamage.class, 10, Side.SERVER);
/*  38 */     this.CHANNEL.registerMessage(PacketSyncEntitySize.Handler.class, PacketSyncEntitySize.class, 11, Side.CLIENT);
/*  39 */     this.CHANNEL.registerMessage(PacketSyncMarkupBook.Handler.class, PacketSyncMarkupBook.class, 12, Side.SERVER);
/*  40 */     this.CHANNEL.registerMessage(PacketExtendedPlayerSync.Handler.class, PacketExtendedPlayerSync.class, 13, Side.CLIENT);
/*  41 */     this.CHANNEL.registerMessage(PacketHowl.Handler.class, PacketHowl.class, 14, Side.SERVER);
/*  42 */     this.CHANNEL.registerMessage(PacketExtendedVillagerSync.Handler.class, PacketExtendedVillagerSync.class, 15, Side.CLIENT);
/*  43 */     this.CHANNEL.registerMessage(PacketSelectPlayerAbility.Handler.class, PacketSelectPlayerAbility.class, 16, Side.SERVER);
/*  44 */     this.CHANNEL.registerMessage(PacketExtendedEntityRequestSyncToClient.Handler.class, PacketExtendedEntityRequestSyncToClient.class, 17, Side.SERVER);
/*  45 */     this.CHANNEL.registerMessage(PacketPartialExtendedPlayerSync.Handler.class, PacketPartialExtendedPlayerSync.class, 18, Side.CLIENT);
/*  46 */     this.CHANNEL.registerMessage(PacketSetClientPlayerFacing.Handler.class, PacketSetClientPlayerFacing.class, 19, Side.CLIENT);
/*     */   }
/*     */   
/*     */   public void sendTo(IMessage message, EntityPlayer player) {
/*  50 */     if ((player instanceof EntityPlayerMP)) {
/*  51 */       this.CHANNEL.sendTo(message, (EntityPlayerMP)player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendTo(IMessage message, EntityPlayerMP player) {
/*  56 */     this.CHANNEL.sendTo(message, player);
/*     */   }
/*     */   
/*     */   public void sendToServer(IMessage message) {
/*  60 */     this.CHANNEL.sendToServer(message);
/*     */   }
/*     */   
/*     */   public void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint targetPoint) {
/*  64 */     this.CHANNEL.sendToAllAround(message, targetPoint);
/*     */   }
/*     */   
/*     */   public void sendToAll(IMessage message) {
/*  68 */     this.CHANNEL.sendToAll(message);
/*     */   }
/*     */   
/*     */   public void sendToDimension(IMessage message, int dimensionId) {
/*  72 */     this.CHANNEL.sendToDimension(message, dimensionId);
/*     */   }
/*     */   
/*     */   public void sendTo(Packet packet, EntityPlayer player) {
/*  76 */     if ((player instanceof EntityPlayerMP)) {
/*  77 */       EntityPlayerMP mp = (EntityPlayerMP)player;
/*  78 */       mp.field_71135_a.func_147359_a(packet);
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendToDimension(Packet packet, World world) {
/*  83 */     for (Object obj : world.field_73010_i) {
/*  84 */       if ((obj instanceof EntityPlayerMP)) {
/*  85 */         EntityPlayerMP mp = (EntityPlayerMP)obj;
/*  86 */         mp.field_71135_a.func_147359_a(packet);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendToAll(Packet packet) {
/*  92 */     for (WorldServer world : MinecraftServer.func_71276_C().field_71305_c) {
/*  93 */       sendToDimension(packet, world);
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendToAllAround(Packet packet, World world, NetworkRegistry.TargetPoint point) {
/*  98 */     double RANGE_SQ = point.range * point.range;
/*  99 */     for (Object obj : world.field_73010_i) {
/* 100 */       if ((obj instanceof EntityPlayerMP)) {
/* 101 */         EntityPlayerMP mp = (EntityPlayerMP)obj;
/* 102 */         if (mp.func_70092_e(point.x, point.y, point.z) <= RANGE_SQ) {
/* 103 */           mp.field_71135_a.func_147359_a(packet);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketPipeline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */