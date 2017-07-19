/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PacketSound implements IMessage
/*    */ {
/*    */   private SoundEffect effect;
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private float volume;
/*    */   private float pitch;
/*    */   
/*    */   public PacketSound() {}
/*    */   
/*    */   public PacketSound(SoundEffect soundEffect, Entity location)
/*    */   {
/* 27 */     this(soundEffect, location, -1.0F, -1.0F);
/*    */   }
/*    */   
/*    */   public PacketSound(SoundEffect soundEffect, Entity location, float volume, float pitch) {
/* 31 */     this.effect = soundEffect;
/* 32 */     this.x = location.field_70165_t;
/* 33 */     this.y = location.field_70163_u;
/* 34 */     this.z = location.field_70161_v;
/* 35 */     this.volume = volume;
/* 36 */     this.pitch = pitch;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 41 */     buffer.writeInt(this.effect.ordinal());
/* 42 */     buffer.writeDouble(this.x);
/* 43 */     buffer.writeDouble(this.y);
/* 44 */     buffer.writeDouble(this.z);
/* 45 */     buffer.writeFloat(this.volume);
/* 46 */     buffer.writeFloat(this.pitch);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 51 */     this.effect = SoundEffect.values()[buffer.readInt()];
/* 52 */     this.x = buffer.readDouble();
/* 53 */     this.y = buffer.readDouble();
/* 54 */     this.z = buffer.readDouble();
/* 55 */     this.volume = buffer.readFloat();
/* 56 */     this.pitch = buffer.readFloat();
/*    */   }
/*    */   
/*    */   public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketSound, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketSound message, MessageContext ctx) {
/* 62 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 63 */       if (message.volume == -1.0F) {
/* 64 */         message.volume = 0.5F;
/*    */       }
/*    */       
/* 67 */       if (message.pitch == -1.0F) {
/* 68 */         message.pitch = (0.4F / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*    */       }
/*    */       
/* 71 */       player.field_70170_p.func_72980_b(message.x, message.y, message.z, message.effect.toString(), message.volume, message.pitch, false);
/* 72 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */