/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ public class KeyBindHelper
/*    */ {
/*    */   public static boolean isKeyBindDown(KeyBinding keyBinding)
/*    */   {
/* 10 */     return keyBinding.func_151463_i() >= 0 ? org.lwjgl.input.Keyboard.isKeyDown(keyBinding.func_151463_i()) : Mouse.isButtonDown(keyBinding.func_151463_i() + 100);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/KeyBindHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */