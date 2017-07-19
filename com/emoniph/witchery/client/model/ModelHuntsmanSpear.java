/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */ public class ModelHuntsmanSpear extends ModelBase
/*    */ {
/*    */   ModelRenderer shaft;
/*    */   ModelRenderer headFront;
/*    */   ModelRenderer headSide;
/*    */   
/*    */   public ModelHuntsmanSpear()
/*    */   {
/* 16 */     this.field_78090_t = 64;
/* 17 */     this.field_78089_u = 64;
/*    */     
/* 19 */     this.shaft = new ModelRenderer(this, 0, 0);
/* 20 */     this.shaft.func_78789_a(-0.5F, -18.0F, -0.5F, 1, 32, 1);
/* 21 */     this.shaft.func_78793_a(0.0F, 0.0F, 0.0F);
/* 22 */     this.shaft.func_78787_b(64, 64);
/* 23 */     this.shaft.field_78809_i = true;
/* 24 */     setRotation(this.shaft, 0.0F, 0.0F, 0.0F);
/* 25 */     this.headFront = new ModelRenderer(this, 6, 3);
/* 26 */     this.headFront.func_78789_a(-1.5F, -6.0F, 0.0F, 3, 6, 0);
/* 27 */     this.headFront.func_78793_a(0.0F, -17.0F, 0.0F);
/* 28 */     this.headFront.func_78787_b(64, 64);
/* 29 */     this.headFront.field_78809_i = true;
/* 30 */     setRotation(this.headFront, 0.0F, 0.0F, 0.0F);
/* 31 */     this.headSide = new ModelRenderer(this, 6, 0);
/* 32 */     this.headSide.func_78789_a(0.0F, -6.0F, -1.5F, 0, 6, 3);
/* 33 */     this.headSide.func_78793_a(0.0F, -17.0F, 0.0F);
/* 34 */     this.headSide.func_78787_b(64, 64);
/* 35 */     this.headSide.field_78809_i = true;
/* 36 */     setRotation(this.headSide, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 40 */     model.field_78795_f = x;
/* 41 */     model.field_78796_g = y;
/* 42 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 47 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 48 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 49 */     this.shaft.func_78785_a(f5);
/* 50 */     this.headFront.func_78785_a(f5);
/* 51 */     this.headSide.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*    */   {
/* 56 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelHuntsmanSpear.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */