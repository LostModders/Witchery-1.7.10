/*    */ package com.emoniph.witchery.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class RenderEntityViewer extends net.minecraft.client.renderer.EntityRenderer
/*    */ {
/*    */   private final Minecraft mc;
/*    */   private float offsetY;
/*    */   
/*    */   public RenderEntityViewer(Minecraft mc) {
/* 11 */     super(mc, mc.func_110442_L());
/* 12 */     this.mc = mc;
/*    */   }
/*    */   
/*    */   public void setOffset(float offset) {
/* 16 */     this.offsetY = offset;
/*    */   }
/*    */   
/*    */   public float getOffset() {
/* 20 */     return this.offsetY;
/*    */   }
/*    */   
/*    */   private boolean canShiftView() {
/* 24 */     return (this.mc.field_71439_g != null) && (!this.mc.field_71439_g.func_70608_bn()) && (!this.mc.field_71439_g.func_70115_ae());
/*    */   }
/*    */   
/*    */   public void func_78480_b(float partialTicks)
/*    */   {
/* 29 */     if (canShiftView()) {
/* 30 */       this.mc.field_71439_g.field_70163_u += -this.offsetY;
/* 31 */       this.mc.field_71439_g.field_70137_T += -this.offsetY;
/* 32 */       this.mc.field_71439_g.field_70167_r += -this.offsetY;
/*    */       
/* 34 */       float savedHeight = this.mc.field_71439_g.eyeHeight;
/* 35 */       this.mc.field_71439_g.eyeHeight = this.mc.field_71439_g.getDefaultEyeHeight();
/* 36 */       super.func_78480_b(partialTicks);
/* 37 */       this.mc.field_71439_g.eyeHeight = savedHeight;
/* 38 */       this.mc.field_71439_g.field_70163_u -= -this.offsetY;
/* 39 */       this.mc.field_71439_g.field_70137_T -= -this.offsetY;
/* 40 */       this.mc.field_71439_g.field_70167_r -= -this.offsetY;
/*    */     } else {
/* 42 */       super.func_78480_b(partialTicks);
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_78473_a(float partialTicks)
/*    */   {
/* 48 */     if (canShiftView()) {
/* 49 */       this.mc.field_71439_g.field_70163_u += -this.offsetY;
/* 50 */       this.mc.field_71439_g.field_70167_r += -this.offsetY;
/* 51 */       this.mc.field_71439_g.field_70137_T += -this.offsetY;
/* 52 */       float savedHeight = this.mc.field_71439_g.eyeHeight;
/* 53 */       this.mc.field_71439_g.eyeHeight = this.mc.field_71439_g.getDefaultEyeHeight();
/* 54 */       super.func_78473_a(partialTicks);
/* 55 */       this.mc.field_71439_g.eyeHeight = savedHeight;
/* 56 */       this.mc.field_71439_g.field_70163_u -= -this.offsetY;
/* 57 */       this.mc.field_71439_g.field_70167_r -= -this.offsetY;
/* 58 */       this.mc.field_71439_g.field_70137_T -= -this.offsetY;
/*    */     } else {
/* 60 */       super.func_78473_a(partialTicks);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/RenderEntityViewer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */