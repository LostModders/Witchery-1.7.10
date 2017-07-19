/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ 
/*     */ public class ModelMirror extends net.minecraft.client.model.ModelBase
/*     */ {
/*     */   public ModelRenderer backMiddle;
/*     */   public ModelRenderer backRight;
/*     */   public ModelRenderer backLeft;
/*     */   public ModelRenderer frameOuterRight;
/*     */   public ModelRenderer frameOuterCurveLowerRight;
/*     */   public ModelRenderer frameOuterCurveMidRight;
/*     */   public ModelRenderer frameOuterCurveUpperRight;
/*     */   public ModelRenderer frameOuterTop;
/*     */   public ModelRenderer frameOuterCurveUpperLeft;
/*     */   public ModelRenderer frameOuterCurveMidLeft;
/*     */   public ModelRenderer frameOuterCurveLowerLeft;
/*     */   public ModelRenderer frameOuterLeft;
/*     */   public ModelRenderer frameInnerRight;
/*     */   public ModelRenderer frameInnerCurveLowerRight;
/*     */   public ModelRenderer frameInnerCurveUpperRight;
/*     */   public ModelRenderer frameInnerTop;
/*     */   public ModelRenderer frameInnerCurveUpperRight_1;
/*     */   public ModelRenderer frameInnerCurveLowerLeft;
/*     */   public ModelRenderer frameInnerLeft;
/*     */   
/*     */   public ModelMirror()
/*     */   {
/*  29 */     this.field_78090_t = 32;
/*  30 */     this.field_78089_u = 32;
/*  31 */     this.frameInnerRight = new ModelRenderer(this, 5, 5);
/*  32 */     this.frameInnerRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  33 */     this.frameInnerRight.func_78790_a(-6.0F, -2.0F, -1.0F, 1, 10, 1, 0.0F);
/*  34 */     this.frameOuterCurveLowerLeft = new ModelRenderer(this, 5, 0);
/*  35 */     this.frameOuterCurveLowerLeft.field_78809_i = true;
/*  36 */     this.frameOuterCurveLowerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  37 */     this.frameOuterCurveLowerLeft.func_78790_a(7.0F, -5.0F, -1.0F, 1, 2, 1, 0.0F);
/*  38 */     this.frameOuterLeft = new ModelRenderer(this, 0, 0);
/*  39 */     this.frameOuterLeft.field_78809_i = true;
/*  40 */     this.frameOuterLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  41 */     this.frameOuterLeft.func_78790_a(6.5F, -3.0F, -1.0F, 1, 11, 1, 0.0F);
/*  42 */     this.backRight = new ModelRenderer(this, 0, 17);
/*  43 */     this.backRight.func_78793_a(0.0F, 0.0F, 1.0F);
/*  44 */     this.backRight.func_78790_a(-7.0F, -6.0F, -1.0F, 2, 14, 1, 0.0F);
/*  45 */     this.backLeft = new ModelRenderer(this, 0, 17);
/*  46 */     this.backLeft.field_78809_i = true;
/*  47 */     this.backLeft.func_78793_a(0.0F, 0.0F, 1.0F);
/*  48 */     this.backLeft.func_78790_a(5.0F, -6.0F, -1.0F, 2, 14, 1, 0.0F);
/*  49 */     this.frameOuterTop = new ModelRenderer(this, 4, 3);
/*  50 */     this.frameOuterTop.func_78793_a(0.0F, 0.0F, 0.0F);
/*  51 */     this.frameOuterTop.func_78790_a(-5.0F, -7.5F, -1.0F, 10, 1, 1, 0.0F);
/*  52 */     this.frameInnerLeft = new ModelRenderer(this, 5, 5);
/*  53 */     this.frameInnerLeft.field_78809_i = true;
/*  54 */     this.frameInnerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  55 */     this.frameInnerLeft.func_78790_a(5.0F, -2.0F, -1.0F, 1, 10, 1, 0.0F);
/*  56 */     this.frameInnerCurveLowerLeft = new ModelRenderer(this, 10, 6);
/*  57 */     this.frameInnerCurveLowerLeft.field_78809_i = true;
/*  58 */     this.frameInnerCurveLowerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  59 */     this.frameInnerCurveLowerLeft.func_78790_a(4.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F);
/*  60 */     this.frameInnerCurveLowerRight = new ModelRenderer(this, 10, 6);
/*  61 */     this.frameInnerCurveLowerRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  62 */     this.frameInnerCurveLowerRight.func_78790_a(-5.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F);
/*  63 */     this.frameOuterRight = new ModelRenderer(this, 0, 0);
/*  64 */     this.frameOuterRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  65 */     this.frameOuterRight.func_78790_a(-7.5F, -3.0F, -1.0F, 1, 11, 1, 0.0F);
/*  66 */     this.frameOuterCurveUpperLeft = new ModelRenderer(this, 17, 0);
/*  67 */     this.frameOuterCurveUpperLeft.field_78809_i = true;
/*  68 */     this.frameOuterCurveUpperLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.frameOuterCurveUpperLeft.func_78790_a(5.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F);
/*  70 */     this.frameInnerCurveUpperRight_1 = new ModelRenderer(this, 15, 6);
/*  71 */     this.frameInnerCurveUpperRight_1.field_78809_i = true;
/*  72 */     this.frameInnerCurveUpperRight_1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  73 */     this.frameInnerCurveUpperRight_1.func_78790_a(3.0F, -4.0F, -1.0F, 1, 2, 1, 0.0F);
/*  74 */     this.frameOuterCurveLowerRight = new ModelRenderer(this, 5, 0);
/*  75 */     this.frameOuterCurveLowerRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  76 */     this.frameOuterCurveLowerRight.func_78790_a(-8.0F, -5.0F, -1.0F, 1, 2, 1, 0.0F);
/*  77 */     this.frameInnerTop = new ModelRenderer(this, 10, 10);
/*  78 */     this.frameInnerTop.func_78793_a(0.0F, 0.0F, 0.0F);
/*  79 */     this.frameInnerTop.func_78790_a(-3.0F, -4.0F, -1.0F, 6, 1, 1, 0.0F);
/*  80 */     this.frameInnerCurveUpperRight = new ModelRenderer(this, 15, 6);
/*  81 */     this.frameInnerCurveUpperRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  82 */     this.frameInnerCurveUpperRight.func_78790_a(-4.0F, -4.0F, -1.0F, 1, 2, 1, 0.0F);
/*  83 */     this.frameOuterCurveMidRight = new ModelRenderer(this, 10, 0);
/*  84 */     this.frameOuterCurveMidRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  85 */     this.frameOuterCurveMidRight.func_78790_a(-7.5F, -6.0F, -1.0F, 2, 1, 1, 0.0F);
/*  86 */     this.frameOuterCurveUpperRight = new ModelRenderer(this, 17, 0);
/*  87 */     this.frameOuterCurveUpperRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  88 */     this.frameOuterCurveUpperRight.func_78790_a(-6.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F);
/*  89 */     this.frameOuterCurveMidLeft = new ModelRenderer(this, 10, 0);
/*  90 */     this.frameOuterCurveMidLeft.field_78809_i = true;
/*  91 */     this.frameOuterCurveMidLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  92 */     this.frameOuterCurveMidLeft.func_78790_a(5.5F, -6.0F, -1.0F, 2, 1, 1, 0.0F);
/*  93 */     this.backMiddle = new ModelRenderer(this, 7, 16);
/*  94 */     this.backMiddle.func_78793_a(0.0F, 0.0F, 1.0F);
/*  95 */     this.backMiddle.func_78790_a(-5.0F, -7.0F, -1.0F, 10, 15, 1, 0.0F);
/*     */   }
/*     */   
/*     */   private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/*  99 */     modelRenderer.field_78795_f = x;
/* 100 */     modelRenderer.field_78796_g = y;
/* 101 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78088_a(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 106 */     this.frameInnerRight.func_78785_a(f5);
/* 107 */     this.frameOuterCurveLowerLeft.func_78785_a(f5);
/* 108 */     this.frameOuterLeft.func_78785_a(f5);
/* 109 */     this.backRight.func_78785_a(f5);
/* 110 */     this.backLeft.func_78785_a(f5);
/* 111 */     this.frameOuterTop.func_78785_a(f5);
/* 112 */     this.frameInnerLeft.func_78785_a(f5);
/* 113 */     this.frameInnerCurveLowerLeft.func_78785_a(f5);
/* 114 */     this.frameInnerCurveLowerRight.func_78785_a(f5);
/* 115 */     this.frameOuterRight.func_78785_a(f5);
/* 116 */     this.frameOuterCurveUpperLeft.func_78785_a(f5);
/* 117 */     this.frameInnerCurveUpperRight_1.func_78785_a(f5);
/* 118 */     this.frameOuterCurveLowerRight.func_78785_a(f5);
/* 119 */     this.frameInnerTop.func_78785_a(f5);
/* 120 */     this.frameInnerCurveUpperRight.func_78785_a(f5);
/* 121 */     this.frameOuterCurveMidRight.func_78785_a(f5);
/* 122 */     this.frameOuterCurveUpperRight.func_78785_a(f5);
/* 123 */     this.frameOuterCurveMidLeft.func_78785_a(f5);
/* 124 */     this.backMiddle.func_78785_a(f5);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */