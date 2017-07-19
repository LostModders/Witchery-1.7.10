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
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PacketExtendedVillagerSync
/*    */   implements IMessage
/*    */ {
/*    */   private int entityId;
/*    */   private int blood;
/*    */   private boolean sleeping;
/*    */   
/*    */   public PacketExtendedVillagerSync() {}
/*    */   
/*    */   public PacketExtendedVillagerSync(ExtendedVillager extendedVillager)
/*    */   {
/* 26 */     this.entityId = extendedVillager.getVillager().func_145782_y();
/* 27 */     this.blood = extendedVillager.getBlood();
/* 28 */     this.sleeping = extendedVillager.isSleeping();
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 33 */     buffer.writeInt(this.entityId);
/* 34 */     buffer.writeInt(this.blood);
/* 35 */     buffer.writeBoolean(this.sleeping);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 40 */     this.entityId = buffer.readInt();
/* 41 */     this.blood = buffer.readInt();
/* 42 */     this.sleeping = buffer.readBoolean();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketExtendedVillagerSync, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketExtendedVillagerSync message, MessageContext ctx) {
/* 48 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 49 */       if (player != null) {
/* 50 */         Entity entity = player.field_70170_p.func_73045_a(message.entityId);
/* 51 */         if ((entity instanceof EntityVillager)) {
/* 52 */           ExtendedVillager ext = ExtendedVillager.get((EntityVillager)entity);
/* 53 */           if (ext != null) {
/* 54 */             ext.synced = true;
/* 55 */             ext.setBlood(message.blood);
/* 56 */             ext.setSleeping(message.sleeping);
/*    */           }
/*    */         }
/*    */       }
/* 60 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketExtendedVillagerSync.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */