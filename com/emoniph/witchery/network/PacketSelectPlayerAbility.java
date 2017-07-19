/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketSelectPlayerAbility
/*    */   implements IMessage
/*    */ {
/*    */   private byte vampirePower;
/*    */   private boolean trigger;
/*    */   
/*    */   public PacketSelectPlayerAbility() {}
/*    */   
/*    */   public PacketSelectPlayerAbility(ExtendedPlayer playerEx, boolean trigger)
/*    */   {
/* 25 */     this.vampirePower = ((byte)playerEx.getSelectedVampirePower().ordinal());
/* 26 */     this.trigger = trigger;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 31 */     buffer.writeByte(this.vampirePower);
/* 32 */     buffer.writeBoolean(this.trigger);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 37 */     this.vampirePower = buffer.readByte();
/* 38 */     this.trigger = buffer.readBoolean();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketSelectPlayerAbility, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketSelectPlayerAbility message, MessageContext ctx) {
/* 44 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 45 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 46 */       if (playerEx != null) {
/* 47 */         playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[message.vampirePower], false);
/* 48 */         if (message.trigger) {
/* 49 */           playerEx.triggerSelectedVampirePower();
/*    */         }
/*    */       }
/* 52 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketSelectPlayerAbility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */