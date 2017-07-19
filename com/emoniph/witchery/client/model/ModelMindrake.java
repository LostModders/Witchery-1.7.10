/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */ public class ModelMindrake extends ModelBase
/*    */ {
/*    */   ModelRenderer leaves;
/*    */   ModelRenderer bodyTop;
/*    */   ModelRenderer bodyBottom;
/*    */   ModelRenderer legLeft;
/*    */   ModelRenderer legRight;
/*    */   
/*    */   public ModelMindrake()
/*    */   {
/* 18 */     this.field_78090_t = 64;
/* 19 */     this.field_78089_u = 32;
/*    */     
/* 21 */     this.leaves = new ModelRenderer(this, 0, 0);
/* 22 */     this.leaves.func_78789_a(-3.0F, 0.0F, 0.0F, 6, 6, 0);
/* 23 */     this.leaves.func_78793_a(0.0F, 13.0F, 0.0F);
/* 24 */     this.leaves.func_78787_b(64, 32);
/* 25 */     this.leaves.field_78809_i = true;
/* 26 */     setRotation(this.leaves, 0.0F, 0.0F, 0.0F);
/* 27 */     this.bodyTop = new ModelRenderer(this, 0, 7);
/* 28 */     this.bodyTop.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 1, 3);
/* 29 */     this.bodyTop.func_78793_a(0.0F, 19.0F, 0.0F);
/* 30 */     this.bodyTop.func_78787_b(64, 32);
/* 31 */     this.bodyTop.field_78809_i = true;
/* 32 */     setRotation(this.bodyTop, 0.0F, 0.0F, 0.0F);
/* 33 */     this.bodyBottom = new ModelRenderer(this, 0, 12);
/* 34 */     this.bodyBottom.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 3, 4);
/* 35 */     this.bodyBottom.func_78793_a(0.0F, 20.0F, 0.0F);
/* 36 */     this.bodyBottom.func_78787_b(64, 32);
/* 37 */     this.bodyBottom.field_78809_i = true;
/* 38 */     setRotation(this.bodyBottom, 0.0F, 0.0F, 0.0F);
/* 39 */     this.legLeft = new ModelRenderer(this, 0, 20);
/* 40 */     this.legLeft.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 1, 1);
/* 41 */     this.legLeft.func_78793_a(1.0F, 23.0F, 0.0F);
/* 42 */     this.legLeft.func_78787_b(64, 32);
/* 43 */     this.legLeft.field_78809_i = true;
/* 44 */     setRotation(this.legLeft, 0.0F, 0.0F, 0.0F);
/* 45 */     this.legRight = new ModelRenderer(this, 0, 20);
/* 46 */     this.legRight.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 1, 1);
/* 47 */     this.legRight.func_78793_a(-1.0F, 23.0F, 0.0F);
/* 48 */     this.legRight.func_78787_b(64, 32);
/* 49 */     this.legRight.field_78809_i = true;
/* 50 */     setRotation(this.legRight, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 55 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 56 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 57 */     this.leaves.func_78785_a(f5);
/* 58 */     this.bodyTop.func_78785_a(f5);
/* 59 */     this.bodyBottom.func_78785_a(f5);
/* 60 */     this.legLeft.func_78785_a(f5);
/* 61 */     this.legRight.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 65 */     model.field_78795_f = x;
/* 66 */     model.field_78796_g = y;
/* 67 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*    */   {
/* 72 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelMindrake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */