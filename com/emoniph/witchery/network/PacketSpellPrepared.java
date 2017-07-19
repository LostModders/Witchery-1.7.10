/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class PacketSpellPrepared implements IMessage
/*    */ {
/*    */   private int effectID;
/*    */   private int level;
/*    */   
/*    */   public PacketSpellPrepared() {}
/*    */   
/*    */   public PacketSpellPrepared(SymbolEffect effect, int level)
/*    */   {
/* 23 */     this.effectID = effect.getEffectID();
/* 24 */     this.level = level;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 29 */     buffer.writeInt(this.effectID);
/* 30 */     buffer.writeInt(this.level);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 35 */     this.effectID = buffer.readInt();
/* 36 */     this.level = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketSpellPrepared, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketSpellPrepared message, MessageContext ctx) {
/* 42 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 43 */       player.getEntityData().func_74768_a("WITCSpellEffectID", message.effectID);
/* 44 */       player.getEntityData().func_74768_a("WITCSpellEffectEnhanced", message.level);
/* 45 */       SoundEffect.NOTE_HARP.playAtPlayer(player.field_70170_p, player, 1.0F);
/* 46 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketSpellPrepared.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */