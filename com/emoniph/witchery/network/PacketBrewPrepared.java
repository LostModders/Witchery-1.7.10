/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class PacketBrewPrepared implements IMessage
/*    */ {
/*    */   private int brewIndex;
/*    */   
/*    */   public PacketBrewPrepared() {}
/*    */   
/*    */   public PacketBrewPrepared(int brewIndex)
/*    */   {
/* 21 */     this.brewIndex = brewIndex;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 26 */     buffer.writeInt(this.brewIndex);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 31 */     this.brewIndex = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketBrewPrepared, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketBrewPrepared message, MessageContext ctx) {
/* 37 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 38 */       player.getEntityData().func_74768_a("WITCLastBrewIndex", message.brewIndex);
/* 39 */       SoundEffect.RANDOM_POP.playAtPlayer(player.field_70170_p, player, 1.0F);
/* 40 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketBrewPrepared.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */