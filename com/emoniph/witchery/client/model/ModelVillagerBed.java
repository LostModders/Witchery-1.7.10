/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelVillagerBed extends net.minecraft.client.model.ModelBase
/*    */ {
/*    */   public ModelRenderer base;
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer legFL;
/*    */   public ModelRenderer legFR;
/*    */   public ModelRenderer legBL;
/*    */   public ModelRenderer legBR;
/*    */   
/*    */   public ModelVillagerBed()
/*    */   {
/* 19 */     this.field_78090_t = 128;
/* 20 */     this.field_78089_u = 32;
/* 21 */     this.legBR = new ModelRenderer(this, 0, 0);
/* 22 */     this.legBR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 23 */     this.legBR.func_78790_a(-5.0F, 1.0F, 14.0F, 1, 3, 1, 0.0F);
/* 24 */     this.base = new ModelRenderer(this, 0, 0);
/* 25 */     this.base.func_78793_a(0.0F, 0.0F, 0.0F);
/* 26 */     this.base.func_78790_a(-5.0F, 0.0F, -15.0F, 10, 1, 30, 0.0F);
/* 27 */     this.head = new ModelRenderer(this, 0, 4);
/* 28 */     this.head.func_78793_a(0.0F, 0.0F, 0.0F);
/* 29 */     this.head.func_78790_a(-5.0F, -4.0F, 14.0F, 10, 4, 1, 0.0F);
/* 30 */     this.legFL = new ModelRenderer(this, 0, 0);
/* 31 */     this.legFL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 32 */     this.legFL.func_78790_a(4.0F, 1.0F, -15.0F, 1, 3, 1, 0.0F);
/* 33 */     this.legBL = new ModelRenderer(this, 0, 0);
/* 34 */     this.legBL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 35 */     this.legBL.func_78790_a(4.0F, 1.0F, 14.0F, 1, 3, 1, 0.0F);
/* 36 */     this.legFR = new ModelRenderer(this, 0, 0);
/* 37 */     this.legFR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 38 */     this.legFR.func_78790_a(-5.0F, 1.0F, -15.0F, 1, 3, 1, 0.0F);
/* 39 */     this.base.func_78792_a(this.legBR);
/* 40 */     this.base.func_78792_a(this.head);
/* 41 */     this.base.func_78792_a(this.legFL);
/* 42 */     this.base.func_78792_a(this.legBL);
/* 43 */     this.base.func_78792_a(this.legFR);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 48 */     this.base.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 52 */     modelRenderer.field_78795_f = x;
/* 53 */     modelRenderer.field_78796_g = y;
/* 54 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelVillagerBed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */