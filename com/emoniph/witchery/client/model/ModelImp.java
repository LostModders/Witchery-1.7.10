/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityImp;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelImp
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer rightleg;
/*     */   ModelRenderer leftleg;
/*     */   ModelRenderer chest;
/*     */   ModelRenderer hornLeft;
/*     */   ModelRenderer hornRight;
/*     */   ModelRenderer nose;
/*     */   ModelRenderer wingRight;
/*     */   ModelRenderer wingLeft;
/*     */   
/*     */   public ModelImp()
/*     */   {
/*  28 */     this.field_78090_t = 64;
/*  29 */     this.field_78089_u = 64;
/*     */     
/*  31 */     this.head = new ModelRenderer(this, 0, 0);
/*  32 */     this.head.func_78789_a(-5.0F, -8.0F, -4.0F, 10, 8, 10);
/*  33 */     this.head.func_78793_a(0.0F, 8.0F, 0.0F);
/*  34 */     this.head.func_78787_b(64, 64);
/*  35 */     this.head.field_78809_i = true;
/*  36 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  37 */     this.body = new ModelRenderer(this, 0, 48);
/*  38 */     this.body.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 9, 7);
/*  39 */     this.body.func_78793_a(0.0F, 9.0F, 0.0F);
/*  40 */     this.body.func_78787_b(64, 64);
/*  41 */     this.body.field_78809_i = true;
/*  42 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  43 */     this.rightarm = new ModelRenderer(this, 41, 0);
/*  44 */     this.rightarm.func_78789_a(-2.0F, -2.0F, -1.5F, 3, 13, 3);
/*  45 */     this.rightarm.func_78793_a(-5.0F, 11.0F, 0.0F);
/*  46 */     this.rightarm.func_78787_b(64, 64);
/*  47 */     this.rightarm.field_78809_i = true;
/*  48 */     setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
/*  49 */     this.leftarm = new ModelRenderer(this, 41, 0);
/*  50 */     this.leftarm.func_78789_a(-1.0F, -2.0F, -1.5F, 3, 13, 3);
/*  51 */     this.leftarm.func_78793_a(5.0F, 11.0F, 0.0F);
/*  52 */     this.leftarm.func_78787_b(64, 64);
/*  53 */     this.leftarm.field_78809_i = true;
/*  54 */     setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
/*  55 */     this.rightleg = new ModelRenderer(this, 33, 48);
/*  56 */     this.rightleg.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/*  57 */     this.rightleg.func_78793_a(-1.5F, 18.0F, 0.0F);
/*  58 */     this.rightleg.func_78787_b(64, 64);
/*  59 */     this.rightleg.field_78809_i = true;
/*  60 */     setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
/*  61 */     this.leftleg = new ModelRenderer(this, 33, 48);
/*  62 */     this.leftleg.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/*  63 */     this.leftleg.func_78793_a(1.5F, 18.0F, 0.0F);
/*  64 */     this.leftleg.func_78787_b(64, 64);
/*  65 */     this.leftleg.field_78809_i = true;
/*  66 */     setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
/*  67 */     this.chest = new ModelRenderer(this, 4, 41);
/*  68 */     this.chest.func_78789_a(-4.0F, 0.0F, -2.0F, 6, 2, 4);
/*  69 */     this.chest.func_78793_a(1.0F, 8.0F, 0.0F);
/*  70 */     this.chest.func_78787_b(64, 64);
/*  71 */     this.chest.field_78809_i = true;
/*  72 */     setRotation(this.chest, 0.0F, 0.0F, 0.0F);
/*  73 */     this.hornLeft = new ModelRenderer(this, 0, 21);
/*  74 */     this.hornLeft.func_78789_a(-1.0F, -5.0F, -1.0F, 2, 5, 2);
/*  75 */     this.hornLeft.func_78793_a(4.0F, 2.0F, 0.0F);
/*  76 */     this.hornLeft.func_78787_b(64, 64);
/*  77 */     this.hornLeft.field_78809_i = true;
/*  78 */     setRotation(this.hornLeft, 0.4089647F, 0.0F, 0.7435722F);
/*  79 */     this.head.func_78792_a(this.hornLeft);
/*  80 */     this.hornRight = new ModelRenderer(this, 0, 21);
/*  81 */     this.hornRight.func_78789_a(-1.0F, -5.0F, -1.0F, 2, 5, 2);
/*  82 */     this.hornRight.func_78793_a(-4.0F, 2.0F, 0.0F);
/*  83 */     this.hornRight.func_78787_b(64, 64);
/*  84 */     this.hornRight.field_78809_i = true;
/*  85 */     setRotation(this.hornRight, 0.4089647F, 0.0F, -0.7435722F);
/*  86 */     this.head.func_78792_a(this.hornRight);
/*  87 */     this.nose = new ModelRenderer(this, 9, 21);
/*  88 */     this.nose.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 4, 2);
/*  89 */     this.nose.func_78793_a(0.0F, 3.0F, -3.0F);
/*  90 */     this.nose.func_78787_b(64, 64);
/*  91 */     this.nose.field_78809_i = true;
/*  92 */     setRotation(this.nose, -0.9666439F, 0.0F, 0.0F);
/*  93 */     this.head.func_78792_a(this.nose);
/*     */     
/*  95 */     this.wingRight = new ModelRenderer(this, 23, 21);
/*  96 */     this.wingRight.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
/*  97 */     this.wingRight.func_78793_a(1.0F, -8.0F, 3.0F);
/*  98 */     this.wingRight.func_78787_b(128, 32);
/*  99 */     this.wingRight.field_78809_i = true;
/* 100 */     setRotation(this.wingRight, 0.3047198F, -0.6698132F, -0.6283185F);
/*     */     
/* 102 */     this.wingLeft = new ModelRenderer(this, 23, 21);
/* 103 */     this.wingLeft.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
/* 104 */     this.wingLeft.func_78793_a(-1.0F, -8.0F, 3.0F);
/* 105 */     this.wingLeft.func_78787_b(128, 32);
/* 106 */     this.wingLeft.field_78809_i = true;
/*     */     
/* 108 */     setRotation(this.wingLeft, -0.3047198F, 3.811406F, 0.6283185F);
/*     */     
/* 110 */     this.wingRight.func_78793_a(-2.0F, 10.0F, -1.0F);
/* 111 */     this.wingLeft.func_78793_a(2.0F, 10.0F, -1.0F);
/*     */     
/* 113 */     this.leftleg.func_78793_a(1.5F, 18.0F, 0.0F);
/* 114 */     this.rightleg.func_78793_a(-1.5F, 18.0F, 0.0F);
/* 115 */     this.chest.func_78793_a(1.0F, 8.0F, 0.0F);
/* 116 */     this.head.func_78793_a(0.0F, 8.0F, 0.0F);
/* 117 */     this.hornRight.func_78793_a(-4.0F, -5.0F, 0.0F);
/* 118 */     this.hornLeft.func_78793_a(4.0F, -5.0F, 0.0F);
/* 119 */     this.nose.func_78793_a(0.0F, -4.0F, -3.0F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 123 */     model.field_78795_f = x;
/* 124 */     model.field_78796_g = y;
/* 125 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 133 */     boolean scaled = false;
/* 134 */     if ((entity != null) && ((entity instanceof EntityImp))) {
/* 135 */       EntityImp imp = (EntityImp)entity;
/* 136 */       if (imp.isPowered()) {
/* 137 */         scaled = true;
/* 138 */         GL11.glScalef(1.5F, 1.0F, 1.5F);
/*     */       }
/*     */     }
/*     */     
/* 142 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 143 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 145 */     this.leftleg.func_78793_a(1.5F, 18.0F, 0.0F);
/* 146 */     this.rightleg.func_78793_a(-1.5F, 18.0F, 0.0F);
/* 147 */     this.chest.func_78793_a(1.0F, 8.0F, 0.0F);
/* 148 */     this.head.func_78793_a(0.0F, 8.0F, 0.0F);
/* 149 */     this.hornRight.func_78793_a(-4.0F, -5.0F, 0.0F);
/* 150 */     this.hornLeft.func_78793_a(4.0F, -5.0F, 0.0F);
/* 151 */     this.nose.func_78793_a(0.0F, -4.0F, -3.0F);
/*     */     
/* 153 */     this.head.func_78785_a(f5);
/* 154 */     this.body.func_78785_a(f5);
/* 155 */     this.rightarm.func_78785_a(f5);
/* 156 */     this.leftarm.func_78785_a(f5);
/* 157 */     this.rightleg.func_78785_a(f5);
/* 158 */     this.leftleg.func_78785_a(f5);
/* 159 */     this.body.func_78785_a(f5);
/* 160 */     this.chest.func_78785_a(f5);
/*     */     
/* 162 */     this.wingLeft.func_78785_a(f5);
/* 163 */     this.wingRight.func_78785_a(f5);
/*     */     
/* 165 */     if (scaled) {
/* 166 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 173 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 174 */     this.head.field_78795_f = (par5 / 57.295776F);
/* 175 */     this.rightarm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 176 */     this.leftarm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 177 */     this.rightarm.field_78808_h = 0.0F;
/* 178 */     this.leftarm.field_78808_h = 0.0F;
/* 179 */     this.rightleg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 180 */     this.leftleg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 181 */     this.rightleg.field_78796_g = 0.0F;
/* 182 */     this.leftleg.field_78796_g = 0.0F;
/*     */     
/* 184 */     if (this.field_78093_q) {
/* 185 */       this.rightarm.field_78795_f += -0.62831855F;
/* 186 */       this.leftarm.field_78795_f += -0.62831855F;
/* 187 */       this.rightleg.field_78795_f = -1.2566371F;
/* 188 */       this.leftleg.field_78795_f = -1.2566371F;
/* 189 */       this.rightleg.field_78796_g = 0.31415927F;
/* 190 */       this.leftleg.field_78796_g = -0.31415927F;
/*     */     }
/*     */     
/* 193 */     this.rightarm.field_78796_g = 0.0F;
/* 194 */     this.leftarm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 198 */     if (this.field_78095_p > -9990.0F) {
/* 199 */       float f6 = this.field_78095_p;
/* 200 */       this.body.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/* 201 */       this.rightarm.field_78798_e = (MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F);
/* 202 */       this.rightarm.field_78800_c = (-MathHelper.func_76134_b(this.body.field_78796_g) * 5.0F);
/* 203 */       this.leftarm.field_78798_e = (-MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F);
/* 204 */       this.leftarm.field_78800_c = (MathHelper.func_76134_b(this.body.field_78796_g) * 5.0F);
/* 205 */       this.rightarm.field_78796_g += this.body.field_78796_g;
/* 206 */       this.leftarm.field_78796_g += this.body.field_78796_g;
/* 207 */       this.leftarm.field_78795_f += this.body.field_78796_g;
/* 208 */       f6 = 1.0F - this.field_78095_p;
/* 209 */       f6 *= f6;
/* 210 */       f6 *= f6;
/* 211 */       f6 = 1.0F - f6;
/* 212 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 213 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.head.field_78795_f - 0.7F) * 0.75F;
/* 214 */       this.rightarm.field_78795_f = ((float)(this.rightarm.field_78795_f - (f7 * 1.2D + f8)));
/* 215 */       this.rightarm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 216 */       this.rightarm.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 219 */     this.body.field_78795_f = 0.0F;
/* 220 */     this.rightleg.field_78798_e = 0.1F;
/* 221 */     this.leftleg.field_78798_e = 0.1F;
/* 222 */     this.rightleg.field_78797_d = 12.0F;
/* 223 */     this.leftleg.field_78797_d = 12.0F;
/* 224 */     this.head.field_78797_d = 0.0F;
/*     */     
/* 226 */     this.rightarm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 227 */     this.leftarm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 228 */     this.rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 229 */     this.leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelImp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */