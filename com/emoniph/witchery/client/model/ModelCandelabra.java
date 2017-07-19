/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class ModelCandelabra extends ModelBase
/*     */ {
/*     */   ModelRenderer candleLeft;
/*     */   ModelRenderer candleRight;
/*     */   ModelRenderer candleFront;
/*     */   ModelRenderer candleBack;
/*     */   ModelRenderer candleMiddle;
/*     */   ModelRenderer supportLR;
/*     */   ModelRenderer supportFB;
/*     */   ModelRenderer sconceLeft;
/*     */   ModelRenderer sconceRight;
/*     */   ModelRenderer sconceFront;
/*     */   ModelRenderer sconceBack;
/*     */   ModelRenderer sconceMiddle;
/*     */   ModelRenderer baseTop;
/*     */   ModelRenderer baseBottom;
/*     */   
/*     */   public ModelCandelabra()
/*     */   {
/*  27 */     this.field_78090_t = 32;
/*  28 */     this.field_78089_u = 32;
/*     */     
/*  30 */     this.candleLeft = new ModelRenderer(this, 0, 0);
/*  31 */     this.candleLeft.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
/*  32 */     this.candleLeft.func_78793_a(-6.0F, 11.0F, -1.0F);
/*  33 */     this.candleLeft.func_78787_b(32, 32);
/*  34 */     this.candleLeft.field_78809_i = true;
/*  35 */     setRotation(this.candleLeft, 0.0F, 0.0F, 0.0F);
/*  36 */     this.candleRight = new ModelRenderer(this, 0, 0);
/*  37 */     this.candleRight.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
/*  38 */     this.candleRight.func_78793_a(4.0F, 11.0F, -1.0F);
/*  39 */     this.candleRight.func_78787_b(32, 32);
/*  40 */     this.candleRight.field_78809_i = true;
/*  41 */     setRotation(this.candleRight, 0.0F, 0.0F, 0.0F);
/*  42 */     this.candleFront = new ModelRenderer(this, 0, 0);
/*  43 */     this.candleFront.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
/*  44 */     this.candleFront.func_78793_a(-1.0F, 11.0F, -6.0F);
/*  45 */     this.candleFront.func_78787_b(32, 32);
/*  46 */     this.candleFront.field_78809_i = true;
/*  47 */     setRotation(this.candleFront, 0.0F, 0.0F, 0.0F);
/*  48 */     this.candleBack = new ModelRenderer(this, 0, 0);
/*  49 */     this.candleBack.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
/*  50 */     this.candleBack.func_78793_a(-1.0F, 11.0F, 4.0F);
/*  51 */     this.candleBack.func_78787_b(32, 32);
/*  52 */     this.candleBack.field_78809_i = true;
/*  53 */     setRotation(this.candleBack, 0.0F, 0.0F, 0.0F);
/*  54 */     this.candleMiddle = new ModelRenderer(this, 0, 0);
/*  55 */     this.candleMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 2, 13, 2);
/*  56 */     this.candleMiddle.func_78793_a(-1.0F, 9.0F, -1.0F);
/*  57 */     this.candleMiddle.func_78787_b(32, 32);
/*  58 */     this.candleMiddle.field_78809_i = true;
/*  59 */     setRotation(this.candleMiddle, 0.0F, 0.0F, 0.0F);
/*  60 */     this.supportLR = new ModelRenderer(this, 0, 17);
/*  61 */     this.supportLR.func_78789_a(0.0F, 0.0F, 0.0F, 12, 1, 2);
/*  62 */     this.supportLR.func_78793_a(-6.0F, 19.0F, -1.0F);
/*  63 */     this.supportLR.func_78787_b(32, 32);
/*  64 */     this.supportLR.field_78809_i = true;
/*  65 */     setRotation(this.supportLR, 0.0F, 0.0F, 0.0F);
/*  66 */     this.supportFB = new ModelRenderer(this, 0, 4);
/*  67 */     this.supportFB.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 12);
/*  68 */     this.supportFB.func_78793_a(-1.0F, 19.0F, -6.0F);
/*  69 */     this.supportFB.func_78787_b(32, 32);
/*  70 */     this.supportFB.field_78809_i = true;
/*  71 */     setRotation(this.supportFB, 0.0F, 0.0F, 0.0F);
/*  72 */     this.sconceLeft = new ModelRenderer(this, 0, 20);
/*  73 */     this.sconceLeft.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
/*  74 */     this.sconceLeft.func_78793_a(-6.5F, 17.0F, -1.5F);
/*  75 */     this.sconceLeft.func_78787_b(32, 32);
/*  76 */     this.sconceLeft.field_78809_i = true;
/*  77 */     setRotation(this.sconceLeft, 0.0F, 0.0F, 0.0F);
/*  78 */     this.sconceRight = new ModelRenderer(this, 0, 20);
/*  79 */     this.sconceRight.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
/*  80 */     this.sconceRight.func_78793_a(3.5F, 17.0F, -1.5F);
/*  81 */     this.sconceRight.func_78787_b(32, 32);
/*  82 */     this.sconceRight.field_78809_i = true;
/*  83 */     setRotation(this.sconceRight, 0.0F, 0.0F, 0.0F);
/*  84 */     this.sconceFront = new ModelRenderer(this, 0, 20);
/*  85 */     this.sconceFront.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
/*  86 */     this.sconceFront.func_78793_a(-1.5F, 17.0F, -6.5F);
/*  87 */     this.sconceFront.func_78787_b(32, 32);
/*  88 */     this.sconceFront.field_78809_i = true;
/*  89 */     setRotation(this.sconceFront, 0.0F, 0.0F, 0.0F);
/*  90 */     this.sconceBack = new ModelRenderer(this, 0, 20);
/*  91 */     this.sconceBack.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
/*  92 */     this.sconceBack.func_78793_a(-1.5F, 17.0F, 3.5F);
/*  93 */     this.sconceBack.func_78787_b(32, 32);
/*  94 */     this.sconceBack.field_78809_i = true;
/*  95 */     setRotation(this.sconceBack, 0.0F, 0.0F, 0.0F);
/*  96 */     this.sconceMiddle = new ModelRenderer(this, 0, 20);
/*  97 */     this.sconceMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
/*  98 */     this.sconceMiddle.func_78793_a(-1.5F, 15.0F, -1.5F);
/*  99 */     this.sconceMiddle.func_78787_b(32, 32);
/* 100 */     this.sconceMiddle.field_78809_i = true;
/* 101 */     setRotation(this.sconceMiddle, 0.0F, 0.0F, 0.0F);
/* 102 */     this.baseTop = new ModelRenderer(this, 12, 20);
/* 103 */     this.baseTop.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
/* 104 */     this.baseTop.func_78793_a(-1.5F, 22.0F, -1.5F);
/* 105 */     this.baseTop.func_78787_b(32, 32);
/* 106 */     this.baseTop.field_78809_i = true;
/* 107 */     setRotation(this.baseTop, 0.0F, 0.0F, 0.0F);
/* 108 */     this.baseBottom = new ModelRenderer(this, 8, 24);
/* 109 */     this.baseBottom.func_78789_a(-2.5F, 0.0F, -2.5F, 5, 1, 5);
/* 110 */     this.baseBottom.func_78793_a(0.0F, 23.0F, 0.0F);
/* 111 */     this.baseBottom.func_78787_b(32, 32);
/* 112 */     this.baseBottom.field_78809_i = true;
/* 113 */     setRotation(this.baseBottom, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 118 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 119 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 120 */     this.candleLeft.func_78785_a(f5);
/* 121 */     this.candleRight.func_78785_a(f5);
/* 122 */     this.candleFront.func_78785_a(f5);
/* 123 */     this.candleBack.func_78785_a(f5);
/* 124 */     this.candleMiddle.func_78785_a(f5);
/* 125 */     this.supportLR.func_78785_a(f5);
/* 126 */     this.supportFB.func_78785_a(f5);
/* 127 */     this.sconceLeft.func_78785_a(f5);
/* 128 */     this.sconceRight.func_78785_a(f5);
/* 129 */     this.sconceFront.func_78785_a(f5);
/* 130 */     this.sconceBack.func_78785_a(f5);
/* 131 */     this.sconceMiddle.func_78785_a(f5);
/* 132 */     this.baseTop.func_78785_a(f5);
/* 133 */     this.baseBottom.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 137 */     model.field_78795_f = x;
/* 138 */     model.field_78796_g = y;
/* 139 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 144 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelCandelabra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */