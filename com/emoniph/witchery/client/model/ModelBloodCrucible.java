/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ public class ModelBloodCrucible extends net.minecraft.client.model.ModelBase
/*    */ {
/*    */   private ModelRenderer right1;
/*    */   private ModelRenderer right2;
/*    */   private ModelRenderer left1;
/*    */   private ModelRenderer left2;
/*    */   private ModelRenderer back1;
/*    */   private ModelRenderer back2;
/*    */   private ModelRenderer front1;
/*    */   private ModelRenderer front2;
/*    */   private ModelRenderer bottom;
/*    */   
/*    */   public ModelBloodCrucible()
/*    */   {
/* 19 */     this.field_78090_t = 32;
/* 20 */     this.field_78089_u = 32;
/* 21 */     this.left2 = new ModelRenderer(this, 17, 11);
/* 22 */     this.left2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 23 */     this.left2.func_78790_a(2.0F, -2.0F, -2.0F, 1, 1, 4, 0.0F);
/* 24 */     this.right2 = new ModelRenderer(this, 17, 11);
/* 25 */     this.right2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 26 */     this.right2.func_78790_a(-3.0F, -2.0F, -2.0F, 1, 1, 4, 0.0F);
/* 27 */     this.front2 = new ModelRenderer(this, 17, 19);
/* 28 */     this.front2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 29 */     this.front2.func_78790_a(-3.0F, -2.0F, -3.0F, 6, 1, 1, 0.0F);
/* 30 */     this.front1 = new ModelRenderer(this, 0, 17);
/* 31 */     this.front1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 32 */     this.front1.func_78790_a(-3.5F, -5.0F, -4.0F, 7, 3, 1, 0.0F);
/* 33 */     this.right1 = new ModelRenderer(this, 0, 6);
/* 34 */     this.right1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 35 */     this.right1.func_78790_a(-4.0F, -5.0F, -3.7F, 1, 3, 7, 0.0F);
/* 36 */     this.bottom = new ModelRenderer(this, 0, 0);
/* 37 */     this.bottom.func_78793_a(0.0F, 0.0F, 0.0F);
/* 38 */     this.bottom.func_78790_a(-2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F);
/* 39 */     this.back2 = new ModelRenderer(this, 17, 19);
/* 40 */     this.back2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 41 */     this.back2.func_78790_a(-3.0F, -2.0F, 2.0F, 6, 1, 1, 0.0F);
/* 42 */     this.back1 = new ModelRenderer(this, 0, 17);
/* 43 */     this.back1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 44 */     this.back1.func_78790_a(-3.5F, -5.0F, 3.0F, 7, 3, 1, 0.0F);
/* 45 */     this.left1 = new ModelRenderer(this, 0, 6);
/* 46 */     this.left1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 47 */     this.left1.func_78790_a(3.0F, -5.0F, -3.5F, 1, 3, 7, 0.0F);
/*    */   }
/*    */   
/*    */   private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 51 */     modelRenderer.field_78795_f = x;
/* 52 */     modelRenderer.field_78796_g = y;
/* 53 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78088_a(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 58 */     this.left2.func_78785_a(f5);
/* 59 */     this.right2.func_78785_a(f5);
/* 60 */     this.front2.func_78785_a(f5);
/* 61 */     this.front1.func_78785_a(f5);
/* 62 */     this.right1.func_78785_a(f5);
/* 63 */     this.bottom.func_78785_a(f5);
/* 64 */     this.back2.func_78785_a(f5);
/* 65 */     this.back1.func_78785_a(f5);
/* 66 */     this.left1.func_78785_a(f5);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelBloodCrucible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */