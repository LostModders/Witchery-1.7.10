/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ 
/*     */ public class ModelGrassper extends net.minecraft.client.model.ModelBase
/*     */ {
/*     */   ModelRenderer stalkTop;
/*     */   ModelRenderer leafRight;
/*     */   ModelRenderer leafFront;
/*     */   ModelRenderer leafback;
/*     */   ModelRenderer leafLeft;
/*     */   ModelRenderer petalBackRight;
/*     */   ModelRenderer stalkBottom;
/*     */   ModelRenderer petalFrontRight;
/*     */   ModelRenderer petalBackLeft;
/*     */   ModelRenderer petalFrontLeft;
/*     */   
/*     */   public ModelGrassper()
/*     */   {
/*  20 */     this.field_78090_t = 64;
/*  21 */     this.field_78089_u = 64;
/*     */     
/*  23 */     this.stalkTop = new ModelRenderer(this, 0, 4);
/*  24 */     this.stalkTop.func_78789_a(-1.0F, -4.0F, -1.0F, 2, 4, 2);
/*  25 */     this.stalkTop.func_78793_a(2.0F, 21.0F, 0.0F);
/*  26 */     this.stalkTop.func_78787_b(64, 64);
/*  27 */     this.stalkTop.field_78809_i = true;
/*  28 */     setRotation(this.stalkTop, 0.0F, 0.0F, -0.5235988F);
/*  29 */     this.leafRight = new ModelRenderer(this, 0, 8);
/*  30 */     this.leafRight.func_78789_a(0.0F, 0.0F, -4.0F, 8, 0, 8);
/*  31 */     this.leafRight.func_78793_a(0.0F, 24.0F, 0.0F);
/*  32 */     this.leafRight.func_78787_b(64, 64);
/*  33 */     this.leafRight.field_78809_i = true;
/*     */     
/*  35 */     this.leafFront = new ModelRenderer(this, 0, 0);
/*  36 */     this.leafFront.func_78789_a(-4.0F, 0.0F, -8.0F, 8, 0, 8);
/*  37 */     this.leafFront.func_78793_a(0.0F, 24.0F, 0.0F);
/*  38 */     this.leafFront.func_78787_b(64, 64);
/*  39 */     this.leafFront.field_78809_i = true;
/*     */     
/*  41 */     this.leafback = new ModelRenderer(this, 0, 0);
/*  42 */     this.leafback.func_78789_a(-4.0F, 0.0F, -8.0F, 8, 0, 8);
/*  43 */     this.leafback.func_78793_a(0.0F, 24.0F, 0.0F);
/*  44 */     this.leafback.func_78787_b(64, 64);
/*  45 */     this.leafback.field_78809_i = true;
/*     */     
/*  47 */     this.leafLeft = new ModelRenderer(this, 0, 8);
/*  48 */     this.leafLeft.func_78789_a(0.0F, 0.0F, -4.0F, 8, 0, 8);
/*  49 */     this.leafLeft.func_78793_a(0.0F, 24.0F, 0.0F);
/*  50 */     this.leafLeft.func_78787_b(64, 64);
/*  51 */     this.leafLeft.field_78809_i = true;
/*     */     
/*  53 */     this.petalBackRight = new ModelRenderer(this, 0, 0);
/*  54 */     this.petalBackRight.func_78789_a(-1.0F, -2.0F, 0.0F, 1, 2, 1);
/*  55 */     this.petalBackRight.func_78793_a(0.0F, 18.0F, 0.0F);
/*  56 */     this.petalBackRight.func_78787_b(64, 64);
/*  57 */     this.petalBackRight.field_78809_i = true;
/*  58 */     setRotation(this.petalBackRight, -0.5235988F, 0.0F, -0.7853982F);
/*  59 */     this.stalkBottom = new ModelRenderer(this, 0, 10);
/*  60 */     this.stalkBottom.func_78789_a(-1.0F, -4.0F, -1.0F, 2, 4, 2);
/*  61 */     this.stalkBottom.func_78793_a(0.0F, 24.0F, 0.0F);
/*  62 */     this.stalkBottom.func_78787_b(64, 64);
/*  63 */     this.stalkBottom.field_78809_i = true;
/*  64 */     setRotation(this.stalkBottom, 0.0F, 0.0F, 0.5235988F);
/*  65 */     this.petalFrontRight = new ModelRenderer(this, 0, 0);
/*  66 */     this.petalFrontRight.func_78789_a(-1.0F, -2.0F, -1.0F, 1, 2, 1);
/*  67 */     this.petalFrontRight.func_78793_a(0.0F, 18.0F, 0.0F);
/*  68 */     this.petalFrontRight.func_78787_b(64, 64);
/*  69 */     this.petalFrontRight.field_78809_i = true;
/*  70 */     setRotation(this.petalFrontRight, 0.5235988F, 0.0F, -0.7853982F);
/*  71 */     this.petalBackLeft = new ModelRenderer(this, 0, 0);
/*  72 */     this.petalBackLeft.func_78789_a(0.0F, -2.0F, 0.0F, 1, 2, 1);
/*  73 */     this.petalBackLeft.func_78793_a(0.0F, 18.0F, 0.0F);
/*  74 */     this.petalBackLeft.func_78787_b(64, 64);
/*  75 */     this.petalBackLeft.field_78809_i = true;
/*  76 */     setRotation(this.petalBackLeft, -0.3490659F, 0.0F, 0.2617994F);
/*  77 */     this.petalFrontLeft = new ModelRenderer(this, 0, 0);
/*  78 */     this.petalFrontLeft.func_78789_a(0.0F, -2.0F, -1.0F, 1, 2, 1);
/*  79 */     this.petalFrontLeft.func_78793_a(0.0F, 18.0F, 0.0F);
/*  80 */     this.petalFrontLeft.func_78787_b(64, 64);
/*  81 */     this.petalFrontLeft.field_78809_i = true;
/*  82 */     setRotation(this.petalFrontLeft, 0.3490659F, 0.0F, 0.2617994F);
/*     */     
/*  84 */     setRotation(this.leafRight, 0.0F, 3.141593F, 0.5235988F);
/*  85 */     setRotation(this.leafLeft, 0.0F, 0.0F, -0.5235988F);
/*  86 */     setRotation(this.leafFront, -0.5235988F, 0.0F, 0.0F);
/*  87 */     setRotation(this.leafback, -0.5235988F, -3.141593F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  92 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  93 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  94 */     this.stalkTop.func_78785_a(f5);
/*  95 */     this.leafRight.func_78785_a(f5);
/*  96 */     this.leafFront.func_78785_a(f5);
/*  97 */     this.leafback.func_78785_a(f5);
/*  98 */     this.leafLeft.func_78785_a(f5);
/*  99 */     this.petalBackRight.func_78785_a(f5);
/* 100 */     this.stalkBottom.func_78785_a(f5);
/* 101 */     this.petalFrontRight.func_78785_a(f5);
/* 102 */     this.petalBackLeft.func_78785_a(f5);
/* 103 */     this.petalFrontLeft.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 107 */     model.field_78795_f = x;
/* 108 */     model.field_78796_g = y;
/* 109 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, net.minecraft.entity.Entity entity) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGrassper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */