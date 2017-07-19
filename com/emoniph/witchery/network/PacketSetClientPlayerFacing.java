/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class PacketSetClientPlayerFacing
/*    */   implements IMessage
/*    */ {
/*    */   private float pitch;
/*    */   private float yaw;
/*    */   
/*    */   public PacketSetClientPlayerFacing() {}
/*    */   
/*    */   public PacketSetClientPlayerFacing(EntityPlayer player)
/*    */   {
/* 21 */     this.pitch = player.field_70125_A;
/* 22 */     this.yaw = player.field_70177_z;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 27 */     buffer.writeFloat(this.pitch);
/* 28 */     buffer.writeFloat(this.yaw);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 33 */     this.pitch = buffer.readFloat();
/* 34 */     this.yaw = buffer.readFloat();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketSetClientPlayerFacing, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketSetClientPlayerFacing message, MessageContext ctx) {
/* 40 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 41 */       player.func_70080_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, message.yaw, message.pitch);
/* 42 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketSetClientPlayerFacing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */