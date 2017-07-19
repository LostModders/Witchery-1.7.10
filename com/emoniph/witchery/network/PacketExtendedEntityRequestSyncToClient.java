/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.common.ExtendedVillager;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PacketExtendedEntityRequestSyncToClient implements IMessage
/*    */ {
/*    */   private int entityId;
/*    */   
/*    */   public PacketExtendedEntityRequestSyncToClient() {}
/*    */   
/*    */   public PacketExtendedEntityRequestSyncToClient(EntityLivingBase villager)
/*    */   {
/* 24 */     this.entityId = villager.func_145782_y();
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeInt(this.entityId);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 34 */     this.entityId = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketExtendedEntityRequestSyncToClient, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketExtendedEntityRequestSyncToClient message, MessageContext ctx) {
/* 40 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 41 */       if (player != null) {
/* 42 */         Entity entity = player.field_70170_p.func_73045_a(message.entityId);
/* 43 */         if ((entity instanceof EntityVillager)) {
/* 44 */           ExtendedVillager ext = ExtendedVillager.get((EntityVillager)entity);
/* 45 */           if (ext != null) {
/* 46 */             return new PacketExtendedVillagerSync(ext);
/*    */           }
/* 48 */         } else if ((entity instanceof EntityPlayer)) {
/* 49 */           return new PacketPlayerStyle((EntityPlayer)entity);
/*    */         }
/*    */       }
/* 52 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketExtendedEntityRequestSyncToClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */