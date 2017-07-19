/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
/*    */ import com.emoniph.witchery.common.ExtendedPlayer.VampireUltimate;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class PacketExtendedPlayerSync
/*    */   implements IMessage
/*    */ {
/*    */   private int werewolfLevel;
/*    */   private int vampireLevel;
/*    */   private int bloodLevel;
/*    */   private int ultimate;
/*    */   private int creatureOrdinal;
/*    */   private int selected;
/*    */   private int ultimateCharges;
/*    */   private int reserveBlood;
/*    */   
/*    */   public PacketExtendedPlayerSync() {}
/*    */   
/*    */   public PacketExtendedPlayerSync(ExtendedPlayer extendedPlayer)
/*    */   {
/* 30 */     this.werewolfLevel = extendedPlayer.getWerewolfLevel();
/* 31 */     this.creatureOrdinal = extendedPlayer.getCreatureTypeOrdinal();
/* 32 */     this.vampireLevel = extendedPlayer.getVampireLevel();
/* 33 */     this.bloodLevel = extendedPlayer.getBloodPower();
/* 34 */     this.selected = extendedPlayer.getSelectedVampirePower().ordinal();
/* 35 */     this.ultimate = extendedPlayer.getVampireUltimate().ordinal();
/* 36 */     this.ultimateCharges = extendedPlayer.getVampireUltimateCharges();
/* 37 */     this.reserveBlood = extendedPlayer.getBloodReserve();
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 42 */     buffer.writeInt(this.werewolfLevel);
/* 43 */     buffer.writeInt(this.creatureOrdinal);
/* 44 */     buffer.writeInt(this.vampireLevel);
/* 45 */     buffer.writeInt(this.bloodLevel);
/* 46 */     buffer.writeInt(this.selected);
/* 47 */     buffer.writeInt(this.ultimate);
/* 48 */     buffer.writeInt(this.ultimateCharges);
/* 49 */     buffer.writeInt(this.reserveBlood);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 54 */     this.werewolfLevel = buffer.readInt();
/* 55 */     this.creatureOrdinal = buffer.readInt();
/* 56 */     this.vampireLevel = buffer.readInt();
/* 57 */     this.bloodLevel = buffer.readInt();
/* 58 */     this.selected = buffer.readInt();
/* 59 */     this.ultimate = buffer.readInt();
/* 60 */     this.ultimateCharges = buffer.readInt();
/* 61 */     this.reserveBlood = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketExtendedPlayerSync, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketExtendedPlayerSync message, MessageContext ctx) {
/* 67 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 68 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 69 */       playerEx.setWerewolfLevel(message.werewolfLevel);
/* 70 */       playerEx.setCreatureTypeOrdinal(message.creatureOrdinal);
/* 71 */       playerEx.setVampireLevel(message.vampireLevel);
/* 72 */       playerEx.setBloodPower(message.bloodLevel);
/* 73 */       playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[message.selected], false);
/* 74 */       playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.values()[message.ultimate], message.ultimateCharges);
/* 75 */       playerEx.setBloodReserve(message.reserveBlood);
/* 76 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketExtendedPlayerSync.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */