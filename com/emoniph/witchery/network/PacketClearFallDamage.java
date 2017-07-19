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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketClearFallDamage
/*    */   implements IMessage
/*    */ {
/*    */   public void toBytes(ByteBuf buffer) {}
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {}
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<PacketClearFallDamage, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketClearFallDamage message, MessageContext ctx)
/*    */     {
/* 28 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 29 */       if (player != null) {
/* 30 */         player.field_70143_R = 0.0F;
/*    */       }
/* 32 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketClearFallDamage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */