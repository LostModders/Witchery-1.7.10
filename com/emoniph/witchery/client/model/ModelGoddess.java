/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class ModelGoddess extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer cleave;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer rightleg;
/*     */   ModelRenderer leftleg;
/*     */   ModelRenderer dressLower;
/*     */   ModelRenderer dressMiddle;
/*     */   
/*     */   public ModelGoddess()
/*     */   {
/*  22 */     this.field_78090_t = 64;
/*  23 */     this.field_78089_u = 64;
/*     */     
/*  25 */     this.head = new ModelRenderer(this, 0, 0);
/*  26 */     this.head.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  27 */     this.head.func_78793_a(0.0F, 0.0F, 0.0F);
/*  28 */     this.head.func_78787_b(64, 64);
/*  29 */     this.head.field_78809_i = true;
/*  30 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  31 */     this.cleave = new ModelRenderer(this, 35, 0);
/*  32 */     this.cleave.func_78789_a(0.0F, -2.0F, -2.0F, 8, 4, 4);
/*  33 */     this.cleave.func_78793_a(-4.0F, 3.0F, -2.0F);
/*  34 */     this.cleave.func_78787_b(64, 64);
/*  35 */     this.cleave.field_78809_i = true;
/*  36 */     setRotation(this.cleave, -0.7853982F, 0.0F, 0.0F);
/*  37 */     this.body = new ModelRenderer(this, 16, 16);
/*  38 */     this.body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
/*  39 */     this.body.func_78793_a(0.0F, 0.0F, 0.0F);
/*  40 */     this.body.func_78787_b(64, 64);
/*  41 */     this.body.field_78809_i = true;
/*  42 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  43 */     this.rightarm = new ModelRenderer(this, 40, 16);
/*  44 */     this.rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
/*  45 */     this.rightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  46 */     this.rightarm.func_78787_b(64, 64);
/*  47 */     this.rightarm.field_78809_i = true;
/*  48 */     setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
/*  49 */     this.leftarm = new ModelRenderer(this, 40, 16);
/*  50 */     this.leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
/*  51 */     this.leftarm.func_78793_a(5.0F, 2.0F, 0.0F);
/*  52 */     this.leftarm.func_78787_b(64, 64);
/*  53 */     this.leftarm.field_78809_i = true;
/*  54 */     setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
/*  55 */     this.rightleg = new ModelRenderer(this, 0, 16);
/*  56 */     this.rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  57 */     this.rightleg.func_78793_a(-2.0F, 12.0F, 0.0F);
/*  58 */     this.rightleg.func_78787_b(64, 64);
/*  59 */     this.rightleg.field_78809_i = true;
/*  60 */     setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
/*  61 */     this.leftleg = new ModelRenderer(this, 0, 16);
/*  62 */     this.leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  63 */     this.leftleg.func_78793_a(2.0F, 12.0F, 0.0F);
/*  64 */     this.leftleg.func_78787_b(64, 64);
/*  65 */     this.leftleg.field_78809_i = true;
/*  66 */     setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
/*  67 */     this.dressLower = new ModelRenderer(this, 0, 45);
/*  68 */     this.dressLower.func_78789_a(-6.0F, 0.0F, -4.0F, 12, 7, 8);
/*  69 */     this.dressLower.func_78793_a(0.0F, 17.0F, 0.0F);
/*  70 */     this.dressLower.func_78787_b(64, 64);
/*  71 */     this.dressLower.field_78809_i = true;
/*  72 */     setRotation(this.dressLower, 0.0F, 0.0F, 0.0F);
/*  73 */     this.dressMiddle = new ModelRenderer(this, 0, 33);
/*  74 */     this.dressMiddle.func_78789_a(-5.0F, 0.0F, -3.0F, 10, 5, 6);
/*  75 */     this.dressMiddle.func_78793_a(0.0F, 12.0F, 0.0F);
/*  76 */     this.dressMiddle.func_78787_b(64, 64);
/*  77 */     this.dressMiddle.field_78809_i = true;
/*  78 */     setRotation(this.dressMiddle, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  82 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  83 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  84 */     this.head.func_78785_a(f5);
/*  85 */     this.cleave.func_78785_a(f5);
/*  86 */     this.body.func_78785_a(f5);
/*  87 */     this.rightarm.func_78785_a(f5);
/*  88 */     this.leftarm.func_78785_a(f5);
/*  89 */     this.rightleg.func_78785_a(f5);
/*  90 */     this.leftleg.func_78785_a(f5);
/*  91 */     this.dressLower.func_78785_a(f5);
/*  92 */     this.dressMiddle.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  96 */     model.field_78795_f = x;
/*  97 */     model.field_78796_g = y;
/*  98 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 102 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 103 */     this.leftarm.field_78795_f = -0.6F;
/* 104 */     this.leftarm.field_78808_h = -0.1F;
/* 105 */     this.leftarm.field_78796_g = -0.6F;
/* 106 */     this.rightarm.field_78795_f = -0.6F;
/* 107 */     this.rightarm.field_78808_h = 0.1F;
/* 108 */     this.rightarm.field_78796_g = 0.6F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGoddess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */