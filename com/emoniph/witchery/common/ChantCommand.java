/*    */ package com.emoniph.witchery.common;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*    */ import com.emoniph.witchery.util.ChatUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import joptsimple.internal.Strings;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ChantCommand implements net.minecraft.command.ICommand
/*    */ {
/*    */   private final List aliases;
/*    */   
/*    */   public ChantCommand()
/*    */   {
/* 20 */     this.aliases = new ArrayList();
/* 21 */     this.aliases.add(func_71517_b());
/*    */   }
/*    */   
/*    */   public String func_71517_b()
/*    */   {
/* 26 */     return "chant";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender icommandsender)
/*    */   {
/* 31 */     return func_71517_b() + " <message>";
/*    */   }
/*    */   
/*    */   public List func_71514_a()
/*    */   {
/* 36 */     return this.aliases;
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args)
/*    */   {
/* 41 */     if (args.length == 0) {
/* 42 */       ChatUtil.sendTranslated(EnumChatFormatting.RED, sender, "witchery.rite.unknownchant", new Object[0]);
/* 43 */       return;
/*    */     }
/*    */     
/* 46 */     World world = sender.func_130014_f_();
/* 47 */     if (world != null) {
/* 48 */       String strings = Strings.join(args, " ");
/* 49 */       EntityPlayer player = world.func_72924_a(sender.func_70005_c_());
/* 50 */       if (player != null) {
/* 51 */         if (Witchery.Items.RUBY_SLIPPERS.trySayTheresNoPlaceLikeHome(player, strings)) {
/* 52 */           return;
/*    */         }
/*    */         
/* 55 */         if (Witchery.Blocks.MIRROR.trySayMirrorMirrorSendMeHome(player, strings)) {
/* 56 */           return;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 61 */     ChatUtil.sendTranslated(EnumChatFormatting.RED, sender, "witchery.rite.unknownchant", new Object[0]);
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender)
/*    */   {
/* 66 */     return true;
/*    */   }
/*    */   
/*    */   public List func_71516_a(ICommandSender icommandsender, String[] astring)
/*    */   {
/* 71 */     return null;
/*    */   }
/*    */   
/*    */   public boolean func_82358_a(String[] astring, int i)
/*    */   {
/* 76 */     return false;
/*    */   }
/*    */   
/*    */   public int compareTo(Object arg0)
/*    */   {
/* 81 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/ChantCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */