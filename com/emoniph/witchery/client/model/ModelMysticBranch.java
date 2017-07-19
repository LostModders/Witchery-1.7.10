/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class ModelMysticBranch extends ModelBase
/*    */ {
/*    */   ModelRenderer Shape1;
/*    */   ModelRenderer Shape2;
/*    */   ModelRenderer Shape3;
/*    */   ModelRenderer Shape4;
/*    */   ModelRenderer Shape5;
/*    */   ModelRenderer Shape6;
/*    */   
/*    */   public ModelMysticBranch()
/*    */   {
/* 18 */     this.field_78090_t = 32;
/* 19 */     this.field_78089_u = 32;
/*    */     
/* 21 */     this.Shape1 = new ModelRenderer(this, 12, 0);
/* 22 */     this.Shape1.func_78789_a(0.0F, -4.0F, 0.0F, 1, 4, 1);
/* 23 */     this.Shape1.func_78793_a(0.0F, -8.0F, 0.0F);
/* 24 */     this.Shape1.func_78787_b(32, 32);
/* 25 */     this.Shape1.field_78809_i = true;
/* 26 */     setRotation(this.Shape1, 0.1858931F, 0.0F, 0.2230717F);
/* 27 */     this.Shape2 = new ModelRenderer(this, 20, 0);
/* 28 */     this.Shape2.func_78789_a(-0.5F, -5.2F, -1.2F, 1, 5, 1);
/* 29 */     this.Shape2.func_78793_a(1.0F, -9.0F, 1.0F);
/* 30 */     this.Shape2.func_78787_b(32, 32);
/* 31 */     this.Shape2.field_78809_i = true;
/* 32 */     setRotation(this.Shape2, -0.2230717F, 0.0F, -0.4089647F);
/* 33 */     this.Shape3 = new ModelRenderer(this, 4, 0);
/* 34 */     this.Shape3.func_78789_a(-1.0F, -6.0F, 0.0F, 1, 6, 1);
/* 35 */     this.Shape3.func_78793_a(1.0F, -4.0F, 0.0F);
/* 36 */     this.Shape3.func_78787_b(32, 32);
/* 37 */     this.Shape3.field_78809_i = true;
/* 38 */     setRotation(this.Shape3, 0.2230717F, -0.0371786F, 0.4089647F);
/* 39 */     this.Shape4 = new ModelRenderer(this, 8, 0);
/* 40 */     this.Shape4.func_78789_a(-1.0F, -3.5F, -0.5F, 1, 4, 1);
/* 41 */     this.Shape4.func_78793_a(1.0F, -4.9F, 1.0F);
/* 42 */     this.Shape4.func_78787_b(32, 32);
/* 43 */     this.Shape4.field_78809_i = true;
/* 44 */     setRotation(this.Shape4, -0.5948578F, 0.0F, -0.4089647F);
/* 45 */     this.Shape5 = new ModelRenderer(this, 16, 0);
/* 46 */     this.Shape5.func_78789_a(-0.2F, -4.8F, 0.4F, 1, 5, 1);
/* 47 */     this.Shape5.func_78793_a(1.0F, -12.0F, -1.0F);
/* 48 */     this.Shape5.func_78787_b(32, 32);
/* 49 */     this.Shape5.field_78809_i = true;
/* 50 */     setRotation(this.Shape5, -0.3717861F, 0.0F, 0.0F);
/* 51 */     this.Shape6 = new ModelRenderer(this, 0, 0);
/* 52 */     this.Shape6.func_78789_a(0.0F, -8.0F, 0.0F, 1, 8, 1);
/* 53 */     this.Shape6.func_78793_a(0.0F, 0.0F, 0.0F);
/* 54 */     this.Shape6.func_78787_b(32, 32);
/* 55 */     this.Shape6.field_78809_i = true;
/* 56 */     setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 60 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 61 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */     
/* 63 */     this.Shape1.func_78785_a(f5);
/* 64 */     this.Shape2.func_78785_a(f5);
/* 65 */     this.Shape3.func_78785_a(f5);
/* 66 */     this.Shape4.func_78785_a(f5);
/* 67 */     this.Shape5.func_78785_a(f5);
/* 68 */     this.Shape6.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 72 */     model.field_78795_f = x;
/* 73 */     model.field_78796_g = y;
/* 74 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 78 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelMysticBranch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */