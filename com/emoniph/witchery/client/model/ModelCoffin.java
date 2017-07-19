/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelCoffin extends net.minecraft.client.model.ModelBase
/*    */ {
/*    */   public ModelRenderer sideLeft;
/*    */   public ModelRenderer sideRight;
/*    */   public ModelRenderer sideEnd;
/*    */   public ModelRenderer base;
/*    */   public ModelRenderer baseLower;
/*    */   public ModelRenderer lid;
/*    */   public ModelRenderer lidMid;
/*    */   public ModelRenderer lidTop;
/*    */   
/*    */   public ModelCoffin()
/*    */   {
/* 21 */     this.field_78090_t = 128;
/* 22 */     this.field_78089_u = 64;
/*    */     
/* 24 */     this.lid = new ModelRenderer(this, 60, 0);
/* 25 */     this.lid.func_78793_a(-7.0F, -5.0F, 0.0F);
/* 26 */     this.lid.func_78790_a(-1.0F, 0.0F, -8.0F, 16, 1, 16, 0.0F);
/*    */     
/* 28 */     this.lidTop = new ModelRenderer(this, 67, 35);
/* 29 */     this.lidTop.func_78793_a(0.0F, 0.0F, 0.0F);
/* 30 */     this.lidTop.func_78790_a(1.0F, -2.0F, -8.0F, 12, 1, 14, 0.0F);
/*    */     
/* 32 */     this.lidMid = new ModelRenderer(this, 64, 18);
/* 33 */     this.lidMid.func_78793_a(0.0F, 0.0F, 0.0F);
/* 34 */     this.lidMid.func_78790_a(0.0F, -1.0F, -8.0F, 14, 1, 15, 0.0F);
/* 35 */     this.lid.func_78792_a(this.lidTop);
/* 36 */     this.lid.func_78792_a(this.lidMid);
/*    */     
/* 38 */     this.sideEnd = new ModelRenderer(this, 33, 51);
/* 39 */     this.sideEnd.func_78793_a(0.0F, -4.0F, 0.0F);
/* 40 */     this.sideEnd.func_78790_a(-6.0F, 0.0F, 6.0F, 12, 7, 1, 0.0F);
/*    */     
/* 42 */     this.sideRight = new ModelRenderer(this, 0, 37);
/* 43 */     this.sideRight.func_78793_a(0.0F, -4.0F, 0.0F);
/* 44 */     this.sideRight.func_78790_a(-7.0F, 0.0F, -8.0F, 1, 7, 15, 0.0F);
/*    */     
/* 46 */     this.sideLeft = new ModelRenderer(this, 0, 37);
/* 47 */     this.sideLeft.field_78809_i = true;
/* 48 */     this.sideLeft.func_78793_a(0.0F, -4.0F, 0.0F);
/* 49 */     this.sideLeft.func_78790_a(6.0F, 0.0F, -8.0F, 1, 7, 15, 0.0F);
/*    */     
/* 51 */     this.baseLower = new ModelRenderer(this, 0, 20);
/* 52 */     this.baseLower.func_78793_a(0.0F, 8.0F, 0.0F);
/* 53 */     this.baseLower.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 1, 16, 0.0F);
/*    */     
/* 55 */     this.base = new ModelRenderer(this, 0, 0);
/* 56 */     this.base.func_78793_a(0.0F, 3.0F, 0.0F);
/* 57 */     this.base.func_78790_a(-7.0F, 0.0F, -8.0F, 14, 5, 15, 0.0F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 66 */     this.sideLeft.func_78785_a(f5);
/* 67 */     this.baseLower.func_78785_a(f5);
/* 68 */     this.sideEnd.func_78785_a(f5);
/* 69 */     this.base.func_78785_a(f5);
/* 70 */     this.sideRight.func_78785_a(f5);
/* 71 */     this.lid.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 75 */     modelRenderer.field_78795_f = x;
/* 76 */     modelRenderer.field_78796_g = y;
/* 77 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelCoffin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */