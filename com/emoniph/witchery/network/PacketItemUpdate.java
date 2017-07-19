/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.item.ItemBook;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class PacketItemUpdate implements IMessage
/*    */ {
/*    */   private int slot;
/*    */   private int damage;
/*    */   private int page;
/*    */   
/*    */   public PacketItemUpdate() {}
/*    */   
/*    */   public PacketItemUpdate(int slot, int page, ItemStack stack)
/*    */   {
/* 25 */     this.slot = slot;
/* 26 */     this.damage = stack.func_77960_j();
/* 27 */     this.page = page;
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 32 */     buffer.writeInt(this.slot);
/* 33 */     buffer.writeInt(this.damage);
/* 34 */     buffer.writeInt(this.page);
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 39 */     this.slot = buffer.readInt();
/* 40 */     this.damage = buffer.readInt();
/* 41 */     this.page = buffer.readInt();
/*    */   }
/*    */   
/*    */   public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketItemUpdate, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketItemUpdate message, MessageContext ctx) {
/* 47 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 48 */       if ((message.slot >= 0) && (message.slot < player.field_71071_by.func_70302_i_())) {
/* 49 */         ItemStack stack = player.field_71071_by.func_70301_a(message.slot);
/* 50 */         if ((stack != null) && (stack.func_77960_j() == message.damage) && (message.page >= 0) && (message.page < 1000))
/*    */         {
/* 52 */           if (Witchery.Items.GENERIC.isBook(stack)) {
/* 53 */             if (!stack.func_77942_o()) {
/* 54 */               stack.func_77982_d(new NBTTagCompound());
/*    */             }
/* 56 */             stack.func_77978_p().func_74768_a("CurrentPage", message.page);
/* 57 */           } else if (stack.func_77973_b() == Witchery.Items.BIOME_BOOK) {
/* 58 */             ItemBook.setSelectedBiome(stack, message.page);
/*    */           }
/*    */         }
/*    */       }
/* 62 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketItemUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */