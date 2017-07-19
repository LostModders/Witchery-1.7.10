/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.ChatStyle;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ public class ChatUtil
/*    */ {
/*    */   public static void sendTranslated(ICommandSender player, String key, Object... params)
/*    */   {
/* 12 */     player.func_145747_a(new net.minecraft.util.ChatComponentTranslation(key, params));
/*    */   }
/*    */   
/*    */   public static void sendTranslated(EnumChatFormatting color, ICommandSender player, String key, Object... params) {
/* 16 */     player.func_145747_a(new net.minecraft.util.ChatComponentTranslation(key, params).func_150255_a(new ChatStyle().func_150238_a(color)));
/*    */   }
/*    */   
/*    */   public static void sendPlain(ICommandSender player, String text) {
/* 20 */     player.func_145747_a(new ChatComponentText(text));
/*    */   }
/*    */   
/*    */   public static void sendPlain(EnumChatFormatting color, ICommandSender player, String text) {
/* 24 */     player.func_145747_a(new ChatComponentText(text).func_150255_a(new ChatStyle().func_150238_a(color)));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/ChatUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */