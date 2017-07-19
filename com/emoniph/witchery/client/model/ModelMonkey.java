/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityWingedMonkey;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelMonkey extends ModelBase
/*     */ {
/*     */   private ModelRenderer tail;
/*     */   private ModelRenderer armLeft;
/*     */   private ModelRenderer legRight;
/*     */   private ModelRenderer bodyShoulder;
/*     */   private ModelRenderer body;
/*     */   private ModelRenderer legLeft;
/*     */   private ModelRenderer head;
/*     */   private ModelRenderer armRight;
/*     */   private ModelRenderer wingRight;
/*     */   private ModelRenderer wingLeft;
/*     */   private ModelRenderer headFace;
/*     */   private ModelRenderer headNose;
/*     */   private ModelRenderer headEarLeft;
/*     */   private ModelRenderer headEarRight;
/*     */   
/*     */   public ModelMonkey()
/*     */   {
/*  31 */     this.field_78090_t = 64;
/*  32 */     this.field_78089_u = 32;
/*  33 */     this.headEarRight = new ModelRenderer(this, 18, 14);
/*  34 */     this.headEarRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  35 */     this.headEarRight.func_78790_a(-4.5F, -2.5F, -1.5F, 2, 3, 1, 0.0F);
/*  36 */     this.tail = new ModelRenderer(this, 18, 23);
/*     */     
/*  38 */     this.tail.func_78790_a(-0.5F, -7.8F, -0.5F, 1, 8, 1, 0.0F);
/*  39 */     this.tail.func_78793_a(0.0F, 18.5F, 5.3F);
/*  40 */     setRotateAngle(this.tail, -0.63739425F, 0.0F, 0.0F);
/*  41 */     this.armRight = new ModelRenderer(this, 0, 19);
/*  42 */     this.armRight.func_78793_a(-3.5F, 14.0F, 0.0F);
/*  43 */     this.armRight.func_78790_a(-2.0F, -1.1F, -1.0F, 2, 11, 2, 0.0F);
/*  44 */     setRotateAngle(this.armRight, -0.18203785F, 0.0F, 0.0F);
/*  45 */     this.legRight = new ModelRenderer(this, 9, 25);
/*  46 */     this.legRight.func_78793_a(-1.4F, 19.0F, 4.7F);
/*  47 */     this.legRight.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
/*  48 */     this.bodyShoulder = new ModelRenderer(this, 32, 0);
/*  49 */     this.bodyShoulder.func_78793_a(0.0F, 16.0F, 1.0F);
/*  50 */     this.bodyShoulder.func_78790_a(-3.5F, -3.0F, -3.0F, 7, 5, 7, 0.0F);
/*  51 */     setRotateAngle(this.bodyShoulder, 1.5707964F, 0.0F, 0.0F);
/*  52 */     this.wingRight = new ModelRenderer(this, 28, 25);
/*  53 */     this.wingRight.func_78793_a(-1.0F, 14.0F, 2.5F);
/*  54 */     this.wingRight.func_78790_a(-12.0F, -0.5F, -3.0F, 12, 1, 6, 0.0F);
/*  55 */     setRotateAngle(this.wingRight, -0.68294734F, 0.3642502F, 0.5462881F);
/*  56 */     this.headEarLeft = new ModelRenderer(this, 18, 14);
/*  57 */     this.headEarLeft.field_78809_i = true;
/*  58 */     this.headEarLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  59 */     this.headEarLeft.func_78790_a(2.5F, -2.5F, -1.5F, 2, 3, 1, 0.0F);
/*  60 */     this.headFace = new ModelRenderer(this, 5, 12);
/*  61 */     this.headFace.func_78793_a(0.0F, 0.0F, 0.0F);
/*  62 */     this.headFace.func_78790_a(-2.5F, -3.5F, -3.5F, 5, 5, 1, 0.0F);
/*  63 */     this.body = new ModelRenderer(this, 36, 12);
/*  64 */     this.body.func_78793_a(0.0F, 15.8F, 2.0F);
/*  65 */     this.body.func_78790_a(-2.5F, -2.0F, -3.0F, 5, 7, 5, 0.0F);
/*  66 */     setRotateAngle(this.body, 0.59184116F, 0.0F, 0.0F);
/*  67 */     this.legLeft = new ModelRenderer(this, 9, 25);
/*  68 */     this.legLeft.field_78809_i = true;
/*  69 */     this.legLeft.func_78793_a(1.4F, 19.0F, 4.7F);
/*  70 */     this.legLeft.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
/*  71 */     this.armLeft = new ModelRenderer(this, 0, 19);
/*  72 */     this.armLeft.field_78809_i = true;
/*  73 */     this.armLeft.func_78793_a(4.0F, 14.0F, 0.0F);
/*  74 */     this.armLeft.func_78790_a(-0.5F, -1.0F, -1.0F, 2, 11, 2, 0.0F);
/*  75 */     setRotateAngle(this.armLeft, -0.18203785F, 0.0F, 0.0F);
/*  76 */     this.wingLeft = new ModelRenderer(this, 28, 25);
/*  77 */     this.wingLeft.field_78809_i = true;
/*  78 */     this.wingLeft.func_78793_a(1.0F, 14.0F, 2.5F);
/*  79 */     this.wingLeft.func_78790_a(0.0F, -0.5F, -3.0F, 12, 1, 6, 0.0F);
/*  80 */     setRotateAngle(this.wingLeft, -0.68294734F, -0.3642502F, -0.5462881F);
/*  81 */     this.head = new ModelRenderer(this, 0, 0);
/*  82 */     this.head.func_78793_a(0.0F, 12.0F, -1.5F);
/*  83 */     this.head.func_78790_a(-3.0F, -4.0F, -3.0F, 6, 6, 5, 0.0F);
/*  84 */     this.headNose = new ModelRenderer(this, 9, 19);
/*  85 */     this.headNose.func_78793_a(0.0F, 0.0F, 0.0F);
/*  86 */     this.headNose.func_78790_a(-2.0F, -1.5F, -4.5F, 4, 3, 1, 0.0F);
/*  87 */     this.head.func_78792_a(this.headEarRight);
/*  88 */     this.head.func_78792_a(this.headEarLeft);
/*  89 */     this.head.func_78792_a(this.headFace);
/*  90 */     this.head.func_78792_a(this.headNose);
/*     */   }
/*     */   
/*     */   private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/*  94 */     modelRenderer.field_78795_f = x;
/*  95 */     modelRenderer.field_78796_g = y;
/*  96 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 102 */     EntityWingedMonkey entitybat = (EntityWingedMonkey)entity;
/*     */     
/*     */ 
/* 105 */     this.armRight.field_78808_h = 0.0F;
/* 106 */     this.armLeft.field_78808_h = 0.0F;
/*     */     
/* 108 */     this.legRight.field_78808_h = 0.0F;
/* 109 */     this.legLeft.field_78808_h = 0.0F;
/*     */     
/* 111 */     boolean landed = (entity.field_70181_x == 0.0D) && (entity.field_70159_w == 0.0D) && (entity.field_70179_y == 0.0D) && (ModelOwl.isLanded(entity));
/* 112 */     if (landed) {
/* 113 */       setRotateAngle(this.wingLeft, -0.68294734F, -0.3642502F, -0.5462881F);
/* 114 */       setRotateAngle(this.wingRight, -0.68294734F, 0.3642502F, 0.5462881F);
/*     */       
/* 116 */       this.armLeft.field_78795_f = -0.18203785F;
/* 117 */       this.armRight.field_78795_f = -0.18203785F;
/* 118 */       this.legLeft.field_78795_f = 0.0F;
/* 119 */       this.legRight.field_78795_f = 0.0F;
/* 120 */       this.wingLeft.field_78797_d = 14.0F;
/* 121 */       this.wingRight.field_78797_d = 14.0F;
/*     */     } else {
/* 123 */       this.wingRight.field_78808_h = (MathHelper.func_76134_b(f2 * 0.5F) * 3.1415927F * 0.2F * 2.0F + 0.2F);
/* 124 */       this.wingRight.field_78795_f = 0.0F;
/* 125 */       this.wingRight.field_78797_d = 12.0F;
/* 126 */       this.wingLeft.field_78808_h = (-this.wingRight.field_78808_h);
/* 127 */       this.wingLeft.field_78795_f = 0.0F;
/* 128 */       this.wingLeft.field_78797_d = 12.0F;
/*     */       
/* 130 */       this.armLeft.field_78795_f = 0.2F;
/* 131 */       this.armRight.field_78795_f = 0.2F;
/* 132 */       this.legLeft.field_78795_f = 0.1F;
/* 133 */       this.legRight.field_78795_f = 0.1F;
/*     */       
/* 135 */       this.armRight.field_78808_h += MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
/* 136 */       this.armLeft.field_78808_h -= MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
/* 137 */       this.armRight.field_78795_f += MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
/* 138 */       this.armLeft.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
/*     */       
/*     */ 
/*     */ 
/* 142 */       this.legRight.field_78795_f += MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
/* 143 */       this.legLeft.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
/*     */     }
/*     */     
/*     */ 
/* 147 */     if (entitybat.func_70906_o()) {
/* 148 */       this.legRight.field_78795_f = -1.3F;
/* 149 */       this.legRight.field_78798_e = 2.0F;
/* 150 */       this.legRight.field_78797_d = 21.0F;
/* 151 */       this.legLeft.field_78795_f = -1.3F;
/* 152 */       this.legLeft.field_78798_e = 2.0F;
/* 153 */       this.legLeft.field_78797_d = 21.0F;
/* 154 */       this.body.field_78795_f = 0.1F;
/* 155 */       this.body.field_78797_d = 17.0F;
/* 156 */       this.tail.func_78793_a(0.0F, 18.5F, 5.3F);
/* 157 */       this.tail.field_78798_e = 4.0F;
/* 158 */       this.tail.field_78797_d = 20.0F;
/* 159 */       setRotateAngle(this.tail, -0.9F, 0.0F, 0.0F);
/*     */       
/*     */ 
/* 162 */       this.armRight.field_78808_h += MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
/* 163 */       this.armLeft.field_78808_h -= MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
/* 164 */       this.armRight.field_78795_f += MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
/* 165 */       this.armLeft.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
/*     */       
/* 167 */       if (!landed) {
/* 168 */         this.legRight.field_78795_f = 0.0F;
/* 169 */         this.legLeft.field_78795_f = 0.0F;
/*     */       }
/*     */     }
/*     */     else {
/* 173 */       this.body.field_78795_f = 0.59184116F;
/* 174 */       this.body.func_78793_a(0.0F, 15.8F, 2.0F);
/* 175 */       this.legRight.func_78793_a(-1.4F, 19.0F, 4.7F);
/* 176 */       this.legLeft.func_78793_a(1.4F, 19.0F, 4.7F);
/* 177 */       this.legRight.field_78795_f = 0.0F;
/* 178 */       this.legLeft.field_78795_f = 0.0F;
/*     */       
/* 180 */       this.tail.func_78793_a(0.0F, 18.5F, 5.3F);
/* 181 */       setRotateAngle(this.tail, -0.63739425F, 0.0F, 0.0F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 187 */     if (f1 > 0.1D) {
/* 188 */       ModelRenderer tmp763_760 = this.tail;tmp763_760.field_78795_f = ((float)(tmp763_760.field_78795_f + (-f1 - 0.1D)));
/* 189 */       this.tail.field_78808_h += 5.0F * MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
/*     */     }
/*     */     else {
/* 192 */       this.tail.field_78808_h += 3.0F * MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
/*     */     }
/*     */     
/* 195 */     this.head.field_78796_g = (f3 / 57.295776F);
/* 196 */     this.head.field_78795_f = (f4 / 57.295776F);
/*     */     
/* 198 */     int i = entitybat.getAttackTimer();
/*     */     
/* 200 */     if (i > 0) {
/* 201 */       float di = 10.0F;
/* 202 */       this.armRight.field_78795_f = (-2.0F + 1.5F * (Math.abs((i - f3) % 10.0F - di * 0.5F) - di * 0.25F) / (di * 0.25F));
/*     */     }
/*     */     
/*     */ 
/* 206 */     this.tail.func_78785_a(f5);
/* 207 */     this.armRight.func_78785_a(f5);
/* 208 */     this.legRight.func_78785_a(f5);
/* 209 */     this.bodyShoulder.func_78785_a(f5);
/* 210 */     this.wingRight.func_78785_a(f5);
/* 211 */     this.body.func_78785_a(f5);
/* 212 */     this.legLeft.func_78785_a(f5);
/* 213 */     this.armLeft.func_78785_a(f5);
/* 214 */     this.wingLeft.func_78785_a(f5);
/* 215 */     this.head.func_78785_a(f5);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelMonkey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */