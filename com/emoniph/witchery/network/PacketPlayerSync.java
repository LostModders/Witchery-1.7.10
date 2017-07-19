/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.infusion.InfusedBrewEffect;
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class PacketPlayerSync
/*    */   implements IMessage
/*    */ {
/*    */   private int infusionID;
/*    */   private int curEnergy;
/*    */   private int maxEnergy;
/*    */   private int creatureID;
/*    */   private int creatureCharges;
/*    */   private int sinkingCurseLevel;
/*    */   private int brewEffect;
/*    */   private long brewTime;
/*    */   
/*    */   public PacketPlayerSync() {}
/*    */   
/*    */   public PacketPlayerSync(EntityPlayer player)
/*    */   {
/* 32 */     this.infusionID = Infusion.getInfusionID(player);
/* 33 */     this.curEnergy = Infusion.getCurrentEnergy(player);
/* 34 */     this.maxEnergy = Infusion.getMaxEnergy(player);
/* 35 */     this.creatureID = CreaturePower.getCreaturePowerID(player);
/* 36 */     this.creatureCharges = CreaturePower.getCreaturePowerCharges(player);
/* 37 */     this.sinkingCurseLevel = Infusion.getSinkingCurseLevel(player);
/*    */     
/* 39 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 40 */     InfusedBrewEffect brew = InfusedBrewEffect.getActiveBrew(nbtPlayer);
/* 41 */     long time = InfusedBrewEffect.getActiveBrewStartTime(nbtPlayer);
/* 42 */     this.brewEffect = (brew != null ? brew.id : 0);
/* 43 */     this.brewTime = 0L;
/* 44 */     if (brew != null) {
/* 45 */       long remainingTicks = brew.durationTicks - (TimeUtil.getServerTimeInTicks() - time);
/* 46 */       if (remainingTicks > 0L) {
/* 47 */         this.brewTime = ((int)Math.ceil(remainingTicks / 1200.0D));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 54 */     buffer.writeInt(this.infusionID);
/* 55 */     buffer.writeInt(this.curEnergy);
/* 56 */     buffer.writeInt(this.maxEnergy);
/* 57 */     buffer.writeInt(this.creatureID);
/* 58 */     buffer.writeInt(this.creatureCharges);
/* 59 */     buffer.writeInt(this.sinkingCurseLevel);
/* 60 */     buffer.writeInt(this.brewEffect);
/* 61 */     buffer.writeLong(this.brewTime);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 66 */     this.infusionID = buffer.readInt();
/* 67 */     this.curEnergy = buffer.readInt();
/* 68 */     this.maxEnergy = buffer.readInt();
/* 69 */     this.creatureID = buffer.readInt();
/* 70 */     this.creatureCharges = buffer.readInt();
/* 71 */     this.sinkingCurseLevel = buffer.readInt();
/* 72 */     this.brewEffect = buffer.readInt();
/* 73 */     this.brewTime = buffer.readLong();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketPlayerSync, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketPlayerSync message, MessageContext ctx) {
/* 79 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 80 */       if ((player != null) && (message != null)) {
/* 81 */         Infusion.setEnergy(player, message.infusionID, message.curEnergy, message.maxEnergy);
/* 82 */         CreaturePower.setCreaturePowerID(player, message.creatureID, message.creatureCharges);
/* 83 */         Infusion.setSinkingCurseLevel(player, message.sinkingCurseLevel);
/* 84 */         if (message.brewEffect > 0) {
/* 85 */           InfusedBrewEffect.setActiveBrewInfo(Infusion.getNBT(player), message.brewEffect, message.brewTime);
/*    */         }
/*    */       }
/* 88 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketPlayerSync.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */