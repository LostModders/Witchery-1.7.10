/*    */ package com.emoniph.witchery.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ class GuiButtonNextPage extends GuiButton
/*    */ {
/*    */   private final boolean nextPage;
/*    */   
/*    */   public GuiButtonNextPage(int par1, int par2, int par3, boolean par4)
/*    */   {
/* 16 */     super(par1, par2, par3, 23, 13, "");
/* 17 */     this.nextPage = par4;
/*    */   }
/*    */   
/*    */   public void func_146112_a(Minecraft par1Minecraft, int par2, int par3)
/*    */   {
/* 22 */     if (this.field_146125_m) {
/* 23 */       boolean flag = (par2 >= this.field_146128_h) && (par3 >= this.field_146129_i) && (par2 < this.field_146128_h + this.field_146120_f) && (par3 < this.field_146129_i + this.field_146121_g);
/* 24 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 25 */       par1Minecraft.func_110434_K().func_110577_a(GuiScreenWitchcraftBook.func_110404_g());
/* 26 */       int k = 0;
/* 27 */       int l = 192;
/*    */       
/* 29 */       if (flag) {
/* 30 */         k += 23;
/*    */       }
/*    */       
/* 33 */       if (!this.nextPage) {
/* 34 */         l += 13;
/*    */       }
/*    */       
/* 37 */       func_73729_b(this.field_146128_h, this.field_146129_i, k, l, 23, 13);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/gui/GuiButtonNextPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */