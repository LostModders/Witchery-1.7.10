/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PacketPartialExtendedPlayerSync implements IMessage
/*    */ {
/*    */   private int entityId;
/*    */   private int blood;
/*    */   
/*    */   public PacketPartialExtendedPlayerSync() {}
/*    */   
/*    */   public PacketPartialExtendedPlayerSync(ExtendedPlayer playerEx, EntityPlayer player)
/*    */   {
/* 22 */     this.entityId = player.func_145782_y();
/* 23 */     this.blood = playerEx.getHumanBlood();
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 28 */     buffer.writeInt(this.entityId);
/* 29 */     buffer.writeInt(this.blood);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 34 */     this.entityId = buffer.readInt();
/* 35 */     this.blood = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketPartialExtendedPlayerSync, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketPartialExtendedPlayerSync message, MessageContext ctx) {
/* 41 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 42 */       if (player != null) {
/* 43 */         Entity entity = player.field_70170_p.func_73045_a(message.entityId);
/* 44 */         if ((entity instanceof EntityPlayer)) {
/* 45 */           ExtendedPlayer ext = ExtendedPlayer.get((EntityPlayer)entity);
/* 46 */           if (ext != null) {
/* 47 */             ext.setHumanBlood(message.blood);
/*    */           }
/*    */         }
/*    */       }
/* 51 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketPartialExtendedPlayerSync.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */