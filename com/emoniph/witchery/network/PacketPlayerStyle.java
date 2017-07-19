/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PacketPlayerStyle implements IMessage
/*    */ {
/*    */   private String username;
/*    */   private int grotesqueTicks;
/*    */   private int nightmare;
/*    */   private boolean ghost;
/*    */   private int creatureType;
/*    */   private int blood;
/*    */   private String playerSkin;
/*    */   
/*    */   public PacketPlayerStyle() {}
/*    */   
/*    */   public PacketPlayerStyle(EntityPlayer player)
/*    */   {
/* 30 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*    */     
/* 32 */     this.username = player.func_70005_c_();
/* 33 */     this.grotesqueTicks = (nbtPlayer.func_74764_b("witcheryGrotesque") ? nbtPlayer.func_74762_e("witcheryGrotesque") : 0);
/* 34 */     this.nightmare = WorldProviderDreamWorld.getPlayerHasNightmare(nbtPlayer);
/* 35 */     this.ghost = WorldProviderDreamWorld.getPlayerIsGhost(nbtPlayer);
/* 36 */     ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 37 */     this.creatureType = playerEx.getCreatureTypeOrdinal();
/* 38 */     this.blood = playerEx.getHumanBlood();
/* 39 */     this.playerSkin = playerEx.getOtherPlayerSkin();
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 44 */     ByteBufUtils.writeUTF8String(buffer, this.username);
/* 45 */     buffer.writeInt(this.grotesqueTicks);
/* 46 */     buffer.writeInt(this.nightmare);
/* 47 */     buffer.writeBoolean(this.ghost);
/* 48 */     buffer.writeInt(this.creatureType);
/* 49 */     buffer.writeInt(this.blood);
/* 50 */     ByteBufUtils.writeUTF8String(buffer, this.playerSkin);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 55 */     this.username = ByteBufUtils.readUTF8String(buffer);
/* 56 */     this.grotesqueTicks = buffer.readInt();
/* 57 */     this.nightmare = buffer.readInt();
/* 58 */     this.ghost = buffer.readBoolean();
/* 59 */     this.creatureType = buffer.readInt();
/* 60 */     this.blood = buffer.readInt();
/* 61 */     this.playerSkin = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */   
/*    */   public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketPlayerStyle, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketPlayerStyle message, MessageContext ctx) {
/* 67 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 68 */       EntityPlayer otherPlayer = player.field_70170_p.func_72924_a(message.username);
/* 69 */       if (otherPlayer != null) {
/* 70 */         NBTTagCompound nbtOtherPlayer = Infusion.getNBT(otherPlayer);
/* 71 */         if (message.grotesqueTicks > 0) {
/* 72 */           nbtOtherPlayer.func_74768_a("witcheryGrotesque", message.grotesqueTicks);
/* 73 */         } else if (nbtOtherPlayer.func_74764_b("witcheryGrotesque")) {
/* 74 */           nbtOtherPlayer.func_82580_o("witcheryGrotesque");
/*    */         }
/* 76 */         WorldProviderDreamWorld.setPlayerHasNightmare(nbtOtherPlayer, message.nightmare > 0, message.nightmare > 1);
/* 77 */         WorldProviderDreamWorld.setPlayerIsGhost(nbtOtherPlayer, message.ghost);
/* 78 */         ExtendedPlayer playerEx = ExtendedPlayer.get(otherPlayer);
/* 79 */         playerEx.setCreatureTypeOrdinal(message.creatureType);
/* 80 */         playerEx.setHumanBlood(message.blood);
/* 81 */         playerEx.setOtherPlayerSkin(message.playerSkin);
/*    */       }
/* 83 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketPlayerStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */