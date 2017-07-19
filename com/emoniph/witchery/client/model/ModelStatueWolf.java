/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*     */ public class ModelStatueWolf extends ModelBase
/*     */ {
/*     */   ModelRenderer WolfHead;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Mane;
/*     */   ModelRenderer Leg1;
/*     */   ModelRenderer Leg2;
/*     */   ModelRenderer Leg3;
/*     */   ModelRenderer Leg4;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer Ear1;
/*     */   ModelRenderer Ear2;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer Shape1;
/*     */   
/*     */   public ModelStatueWolf()
/*     */   {
/*  26 */     this.field_78090_t = 64;
/*  27 */     this.field_78089_u = 32;
/*     */     
/*  29 */     this.WolfHead = new ModelRenderer(this, 0, 0);
/*  30 */     this.WolfHead.func_78789_a(-3.0F, -3.0F, -2.0F, 6, 6, 4);
/*  31 */     this.WolfHead.func_78793_a(-0.5F, 13.5F, -7.0F);
/*  32 */     this.WolfHead.func_78787_b(64, 32);
/*  33 */     this.WolfHead.field_78809_i = true;
/*  34 */     setRotation(this.WolfHead, 0.0F, 0.0F, 0.0F);
/*  35 */     this.Body = new ModelRenderer(this, 18, 14);
/*  36 */     this.Body.func_78789_a(-4.0F, -2.0F, -3.0F, 6, 9, 6);
/*  37 */     this.Body.func_78793_a(0.5F, 14.0F, 2.0F);
/*  38 */     this.Body.func_78787_b(64, 32);
/*  39 */     this.Body.field_78809_i = true;
/*  40 */     setRotation(this.Body, 1.570796F, 0.0F, 0.0F);
/*  41 */     this.Mane = new ModelRenderer(this, 21, 0);
/*  42 */     this.Mane.func_78789_a(-4.0F, -3.0F, -3.0F, 8, 6, 7);
/*  43 */     this.Mane.func_78793_a(-0.5F, 14.0F, -3.0F);
/*  44 */     this.Mane.func_78787_b(64, 32);
/*  45 */     this.Mane.field_78809_i = true;
/*  46 */     setRotation(this.Mane, 1.570796F, 0.0F, 0.0F);
/*  47 */     this.Leg1 = new ModelRenderer(this, 0, 18);
/*  48 */     this.Leg1.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  49 */     this.Leg1.func_78793_a(-2.0F, 16.0F, 7.0F);
/*  50 */     this.Leg1.func_78787_b(64, 32);
/*  51 */     this.Leg1.field_78809_i = true;
/*  52 */     setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
/*  53 */     this.Leg2 = new ModelRenderer(this, 0, 18);
/*  54 */     this.Leg2.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  55 */     this.Leg2.func_78793_a(1.0F, 16.0F, 7.0F);
/*  56 */     this.Leg2.func_78787_b(64, 32);
/*  57 */     this.Leg2.field_78809_i = true;
/*  58 */     setRotation(this.Leg2, 0.0F, 0.0F, 0.0F);
/*  59 */     this.Leg3 = new ModelRenderer(this, 0, 18);
/*  60 */     this.Leg3.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  61 */     this.Leg3.func_78793_a(-2.0F, 16.0F, -4.0F);
/*  62 */     this.Leg3.func_78787_b(64, 32);
/*  63 */     this.Leg3.field_78809_i = true;
/*  64 */     setRotation(this.Leg3, 0.0F, 0.0F, 0.0F);
/*  65 */     this.Leg4 = new ModelRenderer(this, 0, 18);
/*  66 */     this.Leg4.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  67 */     this.Leg4.func_78793_a(1.0F, 16.0F, -4.0F);
/*  68 */     this.Leg4.func_78787_b(64, 32);
/*  69 */     this.Leg4.field_78809_i = true;
/*  70 */     setRotation(this.Leg4, 0.0F, 0.0F, 0.0F);
/*  71 */     this.Tail = new ModelRenderer(this, 9, 18);
/*  72 */     this.Tail.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
/*  73 */     this.Tail.func_78793_a(-0.5F, 12.0F, 8.0F);
/*  74 */     this.Tail.func_78787_b(64, 32);
/*  75 */     this.Tail.field_78809_i = true;
/*  76 */     setRotation(this.Tail, 1.130069F, 0.0F, 0.0F);
/*  77 */     this.Ear1 = new ModelRenderer(this, 16, 14);
/*  78 */     this.Ear1.func_78789_a(-3.0F, -5.0F, 0.0F, 2, 2, 1);
/*  79 */     this.Ear1.func_78793_a(-0.5F, 13.5F, -7.0F);
/*  80 */     this.Ear1.func_78787_b(64, 32);
/*  81 */     this.Ear1.field_78809_i = true;
/*  82 */     setRotation(this.Ear1, 0.0F, 0.0F, 0.0F);
/*  83 */     this.Ear2 = new ModelRenderer(this, 16, 14);
/*  84 */     this.Ear2.func_78789_a(1.0F, -5.0F, 0.0F, 2, 2, 1);
/*  85 */     this.Ear2.func_78793_a(-0.5F, 13.5F, -7.0F);
/*  86 */     this.Ear2.func_78787_b(64, 32);
/*  87 */     this.Ear2.field_78809_i = true;
/*  88 */     setRotation(this.Ear2, 0.0F, 0.0F, 0.0F);
/*  89 */     this.Nose = new ModelRenderer(this, 0, 10);
/*  90 */     this.Nose.func_78789_a(-2.0F, 0.0F, -5.0F, 3, 3, 4);
/*  91 */     this.Nose.func_78793_a(0.0F, 13.5F, -7.0F);
/*  92 */     this.Nose.func_78787_b(64, 32);
/*  93 */     this.Nose.field_78809_i = true;
/*  94 */     setRotation(this.Nose, 0.0F, 0.0F, 0.0F);
/*     */     
/*  96 */     this.Shape1 = new ModelRenderer(this, 22, 18);
/*  97 */     this.Shape1.func_78789_a(0.0F, 0.0F, 0.0F, 8, 1, 13);
/*  98 */     this.Shape1.func_78793_a(-4.5F, 23.0F, -5.0F);
/*  99 */     this.Shape1.func_78787_b(64, 32);
/* 100 */     this.Shape1.field_78809_i = true;
/* 101 */     setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 105 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 106 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 107 */     this.WolfHead.func_78785_a(f5);
/* 108 */     this.Body.func_78785_a(f5);
/* 109 */     this.Mane.func_78785_a(f5);
/* 110 */     this.Leg1.func_78785_a(f5);
/* 111 */     this.Leg2.func_78785_a(f5);
/* 112 */     this.Leg3.func_78785_a(f5);
/* 113 */     this.Leg4.func_78785_a(f5);
/* 114 */     this.Tail.func_78785_a(f5);
/* 115 */     this.Ear1.func_78785_a(f5);
/* 116 */     this.Ear2.func_78785_a(f5);
/* 117 */     this.Nose.func_78785_a(f5);
/* 118 */     this.Shape1.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 122 */     model.field_78795_f = x;
/* 123 */     model.field_78796_g = y;
/* 124 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 128 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelStatueWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */