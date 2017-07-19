/*    */ package com.emoniph.witchery.network;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.common.CommonProxy;
/*    */ import com.emoniph.witchery.item.ItemMarkupBook;
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraft.nbt.NBTTagString;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketSyncMarkupBook
/*    */   implements IMessage
/*    */ {
/*    */   private int slot;
/*    */   private List<String> pages;
/*    */   
/*    */   public PacketSyncMarkupBook() {}
/*    */   
/*    */   public PacketSyncMarkupBook(int slot, List<String> pageStack)
/*    */   {
/* 32 */     this.slot = slot;
/* 33 */     this.pages = new ArrayList(pageStack);
/*    */   }
/*    */   
/*    */   public void toBytes(ByteBuf buffer)
/*    */   {
/* 38 */     buffer.writeInt(this.slot);
/* 39 */     buffer.writeInt(this.pages.size());
/* 40 */     for (String s : this.pages) {
/* 41 */       ByteBufUtils.writeUTF8String(buffer, s);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer)
/*    */   {
/* 47 */     this.slot = buffer.readInt();
/* 48 */     int size = buffer.readInt();
/* 49 */     this.pages = new ArrayList(size);
/* 50 */     for (int i = 0; i < size; i++) {
/* 51 */       this.pages.add(ByteBufUtils.readUTF8String(buffer));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Handler implements IMessageHandler<PacketSyncMarkupBook, IMessage>
/*    */   {
/*    */     public IMessage onMessage(PacketSyncMarkupBook message, MessageContext ctx) {
/* 58 */       EntityPlayer player = Witchery.proxy.getPlayer(ctx);
/* 59 */       if ((message.slot >= 0) && (message.slot < player.field_71071_by.func_70302_i_())) {
/* 60 */         ItemStack stack = player.field_71071_by.func_70301_a(message.slot);
/* 61 */         if ((stack != null) && ((stack.func_77973_b() instanceof ItemMarkupBook))) {
/* 62 */           if (!stack.func_77942_o()) {
/* 63 */             stack.func_77982_d(new NBTTagCompound());
/*    */           }
/*    */           
/* 66 */           NBTTagList pageStack = new NBTTagList();
/* 67 */           for (String s : message.pages) {
/* 68 */             pageStack.func_74742_a(new NBTTagString(s));
/*    */           }
/* 70 */           stack.func_77978_p().func_74782_a("pageStack", pageStack);
/* 71 */           ((ItemMarkupBook)stack.func_77973_b()).onBookRead(stack, player.field_70170_p, player);
/*    */         }
/*    */       }
/* 74 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/network/PacketSyncMarkupBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */