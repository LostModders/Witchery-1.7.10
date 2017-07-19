/*    */ package com.emoniph.witchery.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ class GuiButtonBookmark extends GuiButton
/*    */ {
/*    */   public final int nextPage;
/*    */   
/*    */   public GuiButtonBookmark(int commandID, int x, int y, int pageIndex, String label)
/*    */   {
/* 16 */     super(commandID, x, y, 60, 11, label);
/* 17 */     this.nextPage = pageIndex;
/*    */   }
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int par2, int par3)
/*    */   {
/* 22 */     if (this.field_146125_m) {
/* 23 */       boolean flag = (par2 >= this.field_146128_h) && (par3 >= this.field_146129_i) && (par2 < this.field_146128_h + this.field_146120_f) && (par3 < this.field_146129_i + this.field_146121_g);
/* 24 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 25 */       mc.func_110434_K().func_110577_a(GuiScreenWitchcraftBook.DOUBLE_BOOK_TEXTURE);
/*    */       
/* 27 */       func_73729_b(this.field_146128_h, this.field_146129_i, 26, 'Ü' + (flag ? this.field_146121_g : 0), this.field_146120_f, this.field_146121_g);
/* 28 */       mc.field_71466_p.func_78276_b(this.field_146126_j, this.field_146128_h + 2, this.field_146129_i + 2, 0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/gui/GuiButtonBookmark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */