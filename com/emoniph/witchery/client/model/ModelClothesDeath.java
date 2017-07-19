/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelClothesDeath extends ModelBiped
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer rightleg;
/*     */   ModelRenderer leftleg;
/*     */   ModelRenderer cowl;
/*     */   ModelRenderer robe;
/*     */   ModelRenderer rightsleave;
/*     */   ModelRenderer leftsleave;
/*     */   
/*     */   public ModelClothesDeath(float scale)
/*     */   {
/*  25 */     super(0.0F, 0.0F, 128, 64);
/*     */     
/*  27 */     int off = 56;
/*  28 */     this.head = new ModelRenderer(this, 0 + off, 0);
/*  29 */     this.head.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
/*  30 */     this.head.func_78793_a(0.0F, 0.0F, 0.0F);
/*  31 */     this.head.func_78787_b(128, 64);
/*  32 */     this.head.field_78809_i = true;
/*  33 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  34 */     this.field_78116_c.func_78792_a(this.head);
/*     */     
/*  36 */     this.cowl = new ModelRenderer(this, 0, 45);
/*  37 */     this.cowl.func_78790_a(-4.5F, -8.5F, -4.5F, 9, 10, 9, 0.4F);
/*  38 */     this.cowl.func_78793_a(0.0F, 0.0F, 0.0F);
/*  39 */     this.cowl.func_78787_b(128, 64);
/*  40 */     this.cowl.field_78809_i = true;
/*  41 */     setRotation(this.cowl, 0.0F, 0.0F, 0.0F);
/*  42 */     this.field_78116_c.func_78792_a(this.cowl);
/*     */     
/*  44 */     this.body = new ModelRenderer(this, 16 + off, 16);
/*  45 */     this.body.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
/*  46 */     this.body.func_78793_a(0.0F, 0.0F, 0.0F);
/*  47 */     this.body.func_78787_b(128, 64);
/*  48 */     this.body.field_78809_i = true;
/*  49 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  50 */     this.field_78115_e.func_78792_a(this.body);
/*     */     
/*  52 */     this.rightarm = new ModelRenderer(this, 40 + off, 16);
/*  53 */     this.rightarm.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.05F);
/*  54 */     this.rightarm.func_78787_b(128, 64);
/*  55 */     this.rightarm.field_78809_i = true;
/*  56 */     setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
/*  57 */     this.field_78112_f.func_78792_a(this.rightarm);
/*     */     
/*  59 */     this.leftarm = new ModelRenderer(this, 40 + off, 16);
/*  60 */     this.leftarm.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.05F);
/*  61 */     this.leftarm.func_78787_b(128, 64);
/*  62 */     this.leftarm.field_78809_i = true;
/*  63 */     setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
/*  64 */     this.field_78113_g.func_78792_a(this.leftarm);
/*     */     
/*  66 */     this.rightleg = new ModelRenderer(this, 0 + off, 16);
/*  67 */     this.rightleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.05F);
/*  68 */     this.rightleg.func_78787_b(128, 64);
/*  69 */     this.rightleg.field_78809_i = true;
/*  70 */     setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
/*  71 */     this.field_78123_h.func_78792_a(this.rightleg);
/*     */     
/*  73 */     this.leftleg = new ModelRenderer(this, 0 + off, 16);
/*  74 */     this.leftleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.05F);
/*  75 */     this.leftleg.func_78787_b(128, 64);
/*  76 */     this.leftleg.field_78809_i = true;
/*  77 */     setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
/*  78 */     this.field_78124_i.func_78792_a(this.leftleg);
/*     */     
/*  80 */     this.robe = new ModelRenderer(this, 36, 37);
/*  81 */     this.robe.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 22, 5, 0.1F);
/*  82 */     this.robe.func_78793_a(0.0F, 0.0F, 0.0F);
/*  83 */     this.robe.func_78787_b(128, 64);
/*  84 */     this.robe.field_78809_i = true;
/*  85 */     setRotation(this.robe, 0.0F, 0.0F, 0.0F);
/*  86 */     this.field_78115_e.func_78792_a(this.robe);
/*     */     
/*  88 */     this.rightsleave = new ModelRenderer(this, 64, 50);
/*  89 */     this.rightsleave.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 10, 4, 0.1F);
/*  90 */     this.rightsleave.func_78787_b(128, 64);
/*  91 */     this.rightsleave.field_78809_i = true;
/*  92 */     setRotation(this.rightsleave, 0.0F, 0.0F, 0.0F);
/*  93 */     this.field_78112_f.func_78792_a(this.rightsleave);
/*     */     
/*  95 */     this.leftsleave = new ModelRenderer(this, 64, 50);
/*  96 */     this.leftsleave.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 10, 4, 0.1F);
/*  97 */     this.leftsleave.func_78787_b(128, 64);
/*  98 */     this.leftsleave.field_78809_i = true;
/*  99 */     setRotation(this.leftsleave, 0.0F, 0.0F, 0.0F);
/* 100 */     this.field_78113_g.func_78792_a(this.leftsleave);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 104 */     model.field_78795_f = x;
/* 105 */     model.field_78796_g = y;
/* 106 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 111 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 112 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 117 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelClothesDeath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */