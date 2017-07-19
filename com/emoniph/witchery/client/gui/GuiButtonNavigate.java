/*    */ package com.emoniph.witchery.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ class GuiButtonNavigate extends GuiButton
/*    */ {
/*    */   private final int type;
/*    */   private final ResourceLocation texture;
/*    */   
/*    */   public GuiButtonNavigate(int commandID, int x, int y, int type, ResourceLocation texture)
/*    */   {
/* 18 */     super(commandID, x, y, 23, 13, "");
/* 19 */     this.type = type;
/* 20 */     this.texture = texture;
/*    */   }
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int mouseX, int mouseY)
/*    */   {
/* 25 */     if (this.field_146125_m) {
/* 26 */       boolean mouseOver = (mouseX >= this.field_146128_h) && (mouseY >= this.field_146129_i) && (mouseX < this.field_146128_h + this.field_146120_f) && (mouseY < this.field_146129_i + this.field_146121_g);
/*    */       
/* 28 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 29 */       mc.func_110434_K().func_110577_a(this.texture);
/* 30 */       int k = 0;
/* 31 */       int l = 192;
/*    */       
/* 33 */       if (mouseOver) {
/* 34 */         k += 23;
/*    */       }
/*    */       
/* 37 */       l += 13 * this.type;
/*    */       
/* 39 */       func_73729_b(this.field_146128_h, this.field_146129_i, k, l, 23, 13);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/gui/GuiButtonNavigate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */