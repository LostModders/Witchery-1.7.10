/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.brewing.potions.PotionResizing;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketSyncEntitySize
/*    */   implements IMessage
/*    */ {
/*    */   private int entityID;
/*    */   private float width;
/*    */   private float height;
/*    */   private float stepSize;
/*    */   private float eyeHeight;
/*    */   
/*    */   public PacketSyncEntitySize() {}
/*    */   
/*    */   public PacketSyncEntitySize(Entity entity)
/*    */   {
/* 30 */     this.entityID = (entity != null ? entity.func_145782_y() : 0);
/* 31 */     this.width = entity.field_70130_N;
/* 32 */     this.height = entity.field_70131_O;
/* 33 */     this.stepSize = entity.field_70138_W;
/* 34 */     if ((entity instanceof EntityPlayer)) {
/* 35 */       this.eyeHeight = ((EntityPlayer)entity).eyeHeight;
/*    */     } else {
/* 37 */       this.eyeHeight = -1.0F;
/*    */     }
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 43 */     buffer.writeInt(this.entityID);
/* 44 */     buffer.writeFloat(this.width);
/* 45 */     buffer.writeFloat(this.height);
/* 46 */     buffer.writeFloat(this.stepSize);
/* 47 */     buffer.writeFloat(this.eyeHeight);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 52 */     this.entityID = buffer.readInt();
/* 53 */     this.width = buffer.readFloat();
/* 54 */     this.height = buffer.readFloat();
/* 55 */     this.stepSize = buffer.readFloat();
/* 56 */     this.eyeHeight = buffer.readFloat();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketSyncEntitySize, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketSyncEntitySize message, MessageContext ctx) {
/* 62 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 63 */       for (Object obj : player.field_70170_p.field_72996_f) {
/* 64 */         Entity entity = (Entity)obj;
/* 65 */         if (entity.func_145782_y() == message.entityID) {
/* 66 */           PotionResizing.setEntitySize(entity, message.width, message.height);
/* 67 */           entity.field_70138_W = message.stepSize;
/* 68 */           if (((entity instanceof EntityPlayer)) && (message.eyeHeight != -1.0F)) {}
/*    */           
/*    */ 
/* 71 */           return null;
/*    */         }
/*    */       }
/* 74 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketSyncEntitySize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */