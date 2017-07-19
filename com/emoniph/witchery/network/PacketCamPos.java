/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ public class PacketCamPos
/*    */   implements IMessage
/*    */ {
/*    */   private boolean active;
/*    */   private boolean updatePosition;
/*    */   private int entityID;
/*    */   
/*    */   public PacketCamPos() {}
/*    */   
/*    */   public PacketCamPos(boolean active, boolean updatePosition, Entity entity)
/*    */   {
/* 25 */     this.active = active;
/* 26 */     this.updatePosition = updatePosition;
/* 27 */     this.entityID = (entity != null ? entity.func_145782_y() : 0);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 32 */     buffer.writeBoolean(this.active);
/* 33 */     buffer.writeBoolean(this.updatePosition);
/* 34 */     buffer.writeInt(this.entityID);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 39 */     this.active = buffer.readBoolean();
/* 40 */     this.updatePosition = buffer.readBoolean();
/* 41 */     this.entityID = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketCamPos, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketCamPos message, MessageContext ctx) {
/* 47 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 48 */       com.emoniph.witchery.client.PlayerRender.moveCameraActive = message.active;
/* 49 */       if (message.active) {
/* 50 */         Minecraft.func_71410_x();com.emoniph.witchery.client.PlayerRender.ticksSinceActive = Minecraft.func_71386_F();
/* 51 */         if (message.updatePosition) {
/* 52 */           com.emoniph.witchery.client.PlayerRender.moveCameraToEntityID = message.entityID;
/*    */         }
/*    */       }
/* 55 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketCamPos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */