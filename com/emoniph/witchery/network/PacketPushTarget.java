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
/*    */ public class PacketPushTarget
/*    */   implements IMessage
/*    */ {
/*    */   private double motionX;
/*    */   private double motionY;
/*    */   private double motionZ;
/*    */   
/*    */   public PacketPushTarget() {}
/*    */   
/*    */   public PacketPushTarget(double motionX, double motionY, double motionZ)
/*    */   {
/* 22 */     this.motionX = motionX;
/* 23 */     this.motionY = motionY;
/* 24 */     this.motionZ = motionZ;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeDouble(this.motionX);
/* 30 */     buffer.writeDouble(this.motionY);
/* 31 */     buffer.writeDouble(this.motionZ);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 36 */     this.motionX = buffer.readDouble();
/* 37 */     this.motionY = buffer.readDouble();
/* 38 */     this.motionZ = buffer.readDouble();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketPushTarget, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketPushTarget message, MessageContext ctx) {
/* 44 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 45 */       player.field_70159_w = message.motionX;
/* 46 */       player.field_70181_x = message.motionY;
/* 47 */       player.field_70179_y = message.motionZ;
/* 48 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketPushTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */