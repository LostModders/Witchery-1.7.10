/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderUtil
/*    */ {
/*    */   public static void blend(boolean on)
/*    */   {
/* 13 */     if (on) {
/* 14 */       GL11.glPushAttrib(16448);
/* 15 */       GL11.glShadeModel(7425);
/* 16 */       GL11.glDisable(3008);
/* 17 */       GL11.glEnable(3042);
/* 18 */       GL11.glBlendFunc(770, 771);
/*    */     } else {
/* 20 */       GL11.glPopAttrib();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void render2d(boolean on) {
/* 25 */     if (on) {
/* 26 */       GL11.glPushAttrib(8192);
/* 27 */       GL11.glDisable(2929);
/* 28 */       GL11.glDisable(2884);
/* 29 */       GL11.glDisable(2896);
/*    */     } else {
/* 31 */       GL11.glPopAttrib();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/RenderUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */