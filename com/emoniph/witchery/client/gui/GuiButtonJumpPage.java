/*    */ package com.emoniph.witchery.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ class GuiButtonJumpPage extends GuiButton
/*    */ {
/*    */   public final int nextPage;
/*    */   public final int iconX;
/*    */   public final int iconY;
/*    */   
/*    */   public GuiButtonJumpPage(int commandID, int x, int y, int pageIndex, int iconX, int iconY)
/*    */   {
/* 18 */     super(commandID, x, y, 20, 20, "");
/* 19 */     this.nextPage = pageIndex;
/* 20 */     this.iconX = iconX;
/* 21 */     this.iconY = iconY;
/*    */   }
/*    */   
/*    */   public void func_146112_a(Minecraft par1Minecraft, int par2, int par3)
/*    */   {
/* 26 */     if (this.field_146125_m) {
/* 27 */       boolean flag = (par2 >= this.field_146128_h) && (par3 >= this.field_146129_i) && (par2 < this.field_146128_h + this.field_146120_f) && (par3 < this.field_146129_i + this.field_146121_g);
/* 28 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 29 */       par1Minecraft.func_110434_K().func_110577_a(GuiScreenWitchcraftBook.DOUBLE_BOOK_TEXTURE);
/* 30 */       int k = 3;
/* 31 */       int l = 220;
/*    */       
/* 33 */       if (flag) {
/* 34 */         k += 12;
/*    */       }
/*    */       
/* 37 */       func_73729_b(this.field_146128_h, this.field_146129_i, k, l, 9, 24);
/* 38 */       if ((this.iconX >= 0) && (this.iconY >= 0)) {
/* 39 */         func_73729_b(this.field_146128_h, this.field_146129_i + 9, this.iconX, this.iconY, 8, 8);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/gui/GuiButtonJumpPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */