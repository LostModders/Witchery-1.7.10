/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
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
/*    */ 
/*    */ public class PacketParticles
/*    */   implements IMessage
/*    */ {
/*    */   private ParticleEffect particleEffect;
/*    */   private SoundEffect soundEffect;
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private double width;
/*    */   private double height;
/*    */   private int color;
/*    */   
/*    */   public PacketParticles() {}
/*    */   
/*    */   public PacketParticles(ParticleEffect particleEffect, SoundEffect soundEffect, double x, double y, double z, double width, double height, int color)
/*    */   {
/* 35 */     this.particleEffect = particleEffect;
/* 36 */     this.soundEffect = (soundEffect != null ? soundEffect : SoundEffect.NONE);
/* 37 */     this.x = x;
/* 38 */     this.y = y;
/* 39 */     this.z = z;
/* 40 */     this.width = width;
/* 41 */     this.height = height;
/* 42 */     this.color = color;
/*    */   }
/*    */   
/*    */   public PacketParticles(ParticleEffect particleEffect, SoundEffect soundEffect, Entity targetEntity, double width, double height)
/*    */   {
/* 47 */     this(particleEffect, soundEffect, targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, width, height, 16777215);
/*    */   }
/*    */   
/*    */   public PacketParticles(ParticleEffect particleEffect, SoundEffect soundEffect, Entity targetEntity, double width, double height, int color)
/*    */   {
/* 52 */     this(particleEffect, soundEffect, targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, width, height, color);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 57 */     buffer.writeInt(this.particleEffect.ordinal());
/* 58 */     buffer.writeInt(this.soundEffect.ordinal());
/* 59 */     buffer.writeDouble(this.x);
/* 60 */     buffer.writeDouble(this.y);
/* 61 */     buffer.writeDouble(this.z);
/* 62 */     buffer.writeDouble(this.width);
/* 63 */     buffer.writeDouble(this.height);
/* 64 */     buffer.writeInt(this.color);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 69 */     int ordinalParticle = buffer.readInt();
/* 70 */     this.particleEffect = ParticleEffect.values()[ordinalParticle];
/* 71 */     int ordinalSound = buffer.readInt();
/* 72 */     this.soundEffect = SoundEffect.values()[ordinalSound];
/* 73 */     this.x = buffer.readDouble();
/* 74 */     this.y = buffer.readDouble();
/* 75 */     this.z = buffer.readDouble();
/* 76 */     this.width = buffer.readDouble();
/* 77 */     this.height = buffer.readDouble();
/* 78 */     this.color = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketParticles, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketParticles message, MessageContext ctx) {
/* 84 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 85 */       World world = player.field_70170_p;
/*    */       
/* 87 */       double x = message.x;
/* 88 */       double y = message.y;
/* 89 */       double z = message.z;
/* 90 */       double width = message.width;
/* 91 */       double height = message.height;
/* 92 */       SoundEffect sound = message.soundEffect;
/* 93 */       int color = message.color;
/* 94 */       ParticleEffect particle = message.particleEffect;
/*    */       
/* 96 */       Witchery.proxy.showParticleEffect(world, x, y, z, width, height, sound, color, particle);
/* 97 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketParticles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */