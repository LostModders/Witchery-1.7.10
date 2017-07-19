/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class ModelTreefyd extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer ShapeV;
/*     */   ModelRenderer leavesH;
/*     */   ModelRenderer base;
/*     */   ModelRenderer leg3;
/*     */   ModelRenderer leg4;
/*     */   ModelRenderer leg1;
/*     */   ModelRenderer leg2;
/*     */   
/*     */   public ModelTreefyd()
/*     */   {
/*  23 */     this.field_78090_t = 64;
/*  24 */     this.field_78089_u = 32;
/*  25 */     func_78085_a("head.face", 0, 24);
/*  26 */     func_78085_a("head.petals", 0, 0);
/*  27 */     func_78085_a("head.tongue", 25, 18);
/*     */     
/*  29 */     this.head = new ModelRenderer(this, "head");
/*  30 */     this.head.func_78793_a(0.0F, 3.0F, 0.0F);
/*  31 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  32 */     this.head.field_78809_i = true;
/*  33 */     this.head.func_78786_a("face", -2.0F, -4.0F, -2.0F, 4, 4, 4);
/*  34 */     this.head.func_78786_a("petals", -5.0F, -7.0F, 0.0F, 10, 10, 0);
/*  35 */     this.head.func_78786_a("tongue", 0.0F, -3.0F, -6.0F, 0, 10, 4);
/*  36 */     this.body = new ModelRenderer(this, 16, 14);
/*  37 */     this.body.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 16, 2);
/*  38 */     this.body.func_78793_a(0.0F, 3.0F, 0.0F);
/*  39 */     this.body.func_78787_b(64, 32);
/*  40 */     this.body.field_78809_i = true;
/*  41 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  42 */     this.ShapeV = new ModelRenderer(this, 40, 6);
/*  43 */     this.ShapeV.func_78789_a(0.0F, 0.0F, -6.0F, 0, 14, 12);
/*  44 */     this.ShapeV.func_78793_a(0.0F, 6.0F, 0.0F);
/*  45 */     this.ShapeV.func_78787_b(64, 32);
/*  46 */     this.ShapeV.field_78809_i = true;
/*  47 */     setRotation(this.ShapeV, 0.0F, 0.7853982F, 0.0F);
/*  48 */     this.leavesH = new ModelRenderer(this, 40, 0);
/*  49 */     this.leavesH.func_78789_a(-6.0F, 0.0F, 0.0F, 12, 14, 0);
/*  50 */     this.leavesH.func_78793_a(0.0F, 6.0F, 0.0F);
/*  51 */     this.leavesH.func_78787_b(64, 32);
/*  52 */     this.leavesH.field_78809_i = true;
/*  53 */     setRotation(this.leavesH, 0.0F, 0.7853982F, 0.0F);
/*  54 */     this.base = new ModelRenderer(this, 15, 6);
/*  55 */     this.base.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 6);
/*  56 */     this.base.func_78793_a(-3.0F, 19.0F, -3.0F);
/*  57 */     this.base.func_78787_b(64, 32);
/*  58 */     this.base.field_78809_i = true;
/*  59 */     setRotation(this.base, 0.0F, 0.0F, 0.0F);
/*  60 */     this.leg3 = new ModelRenderer(this, 0, 16);
/*  61 */     this.leg3.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
/*  62 */     this.leg3.func_78793_a(-2.0F, 20.0F, -4.0F);
/*  63 */     this.leg3.func_78787_b(64, 32);
/*  64 */     this.leg3.field_78809_i = true;
/*  65 */     setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
/*  66 */     this.leg4 = new ModelRenderer(this, 0, 16);
/*  67 */     this.leg4.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
/*  68 */     this.leg4.func_78793_a(2.0F, 20.0F, -4.0F);
/*  69 */     this.leg4.func_78787_b(64, 32);
/*  70 */     this.leg4.field_78809_i = true;
/*  71 */     setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
/*  72 */     this.leg1 = new ModelRenderer(this, 0, 16);
/*  73 */     this.leg1.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
/*  74 */     this.leg1.func_78793_a(-2.0F, 20.0F, 4.0F);
/*  75 */     this.leg1.func_78787_b(64, 32);
/*  76 */     this.leg1.field_78809_i = true;
/*  77 */     setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
/*  78 */     this.leg2 = new ModelRenderer(this, 0, 16);
/*  79 */     this.leg2.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
/*  80 */     this.leg2.func_78793_a(2.0F, 20.0F, 4.0F);
/*  81 */     this.leg2.func_78787_b(64, 32);
/*  82 */     this.leg2.field_78809_i = true;
/*  83 */     setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  88 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  89 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  90 */     this.head.func_78785_a(f5);
/*  91 */     this.body.func_78785_a(f5);
/*  92 */     this.ShapeV.func_78785_a(f5);
/*  93 */     this.leavesH.func_78785_a(f5);
/*  94 */     this.base.func_78785_a(f5);
/*  95 */     this.leg3.func_78785_a(f5);
/*  96 */     this.leg4.func_78785_a(f5);
/*  97 */     this.leg1.func_78785_a(f5);
/*  98 */     this.leg2.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 102 */     model.field_78795_f = x;
/* 103 */     model.field_78796_g = y;
/* 104 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 110 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 111 */     this.head.field_78795_f = (par5 / 57.295776F);
/* 112 */     this.leg1.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 113 */     this.leg2.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 114 */     this.leg3.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 115 */     this.leg4.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelTreefyd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */