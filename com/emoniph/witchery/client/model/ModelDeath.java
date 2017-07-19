/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelDeath extends ModelBase
/*     */ {
/*     */   ModelRenderer bipedHead;
/*     */   ModelRenderer bipedBody;
/*     */   ModelRenderer bipedRightArm;
/*     */   ModelRenderer bipedLeftArm;
/*     */   ModelRenderer bipedRightLeg;
/*     */   ModelRenderer bipedLeftLeg;
/*     */   ModelRenderer robe;
/*     */   ModelRenderer scythe;
/*     */   
/*     */   public ModelDeath()
/*     */   {
/*  25 */     this.field_78090_t = 64;
/*  26 */     this.field_78089_u = 64;
/*  27 */     func_78085_a("scythe.shaft", 58, 5);
/*  28 */     func_78085_a("scythe.blade", 36, 0);
/*     */     
/*  30 */     this.bipedHead = new ModelRenderer(this, 27, 43);
/*  31 */     this.bipedHead.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 10, 8);
/*  32 */     this.bipedHead.func_78793_a(0.0F, 0.0F, 0.0F);
/*  33 */     this.bipedHead.func_78787_b(64, 64);
/*  34 */     this.bipedHead.field_78809_i = true;
/*  35 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  36 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  37 */     this.bipedBody.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
/*  38 */     this.bipedBody.func_78793_a(0.0F, 0.0F, 0.0F);
/*  39 */     this.bipedBody.func_78787_b(64, 64);
/*  40 */     this.bipedBody.field_78809_i = true;
/*  41 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  42 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  43 */     this.bipedRightArm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
/*  44 */     this.bipedRightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  45 */     this.bipedRightArm.func_78787_b(64, 64);
/*  46 */     this.bipedRightArm.field_78809_i = true;
/*  47 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  48 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  49 */     this.bipedLeftArm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
/*  50 */     this.bipedLeftArm.func_78793_a(5.0F, 2.0F, 0.0F);
/*  51 */     this.bipedLeftArm.func_78787_b(64, 64);
/*  52 */     this.bipedLeftArm.field_78809_i = true;
/*  53 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  54 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  55 */     this.bipedRightLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
/*  56 */     this.bipedRightLeg.func_78793_a(-2.0F, 12.0F, 0.0F);
/*  57 */     this.bipedRightLeg.func_78787_b(64, 64);
/*  58 */     this.bipedRightLeg.field_78809_i = true;
/*  59 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  60 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  61 */     this.bipedLeftLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
/*  62 */     this.bipedLeftLeg.func_78793_a(2.0F, 12.0F, 0.0F);
/*  63 */     this.bipedLeftLeg.func_78787_b(64, 64);
/*  64 */     this.bipedLeftLeg.field_78809_i = true;
/*  65 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  66 */     this.robe = new ModelRenderer(this, 0, 33);
/*  67 */     this.robe.func_78789_a(-4.0F, 0.0F, -2.5F, 8, 23, 5);
/*  68 */     this.robe.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.robe.func_78787_b(64, 64);
/*  70 */     this.robe.field_78809_i = true;
/*  71 */     setRotation(this.robe, 0.0F, 0.0F, 0.0F);
/*  72 */     this.scythe = new ModelRenderer(this, "scythe");
/*  73 */     this.scythe.func_78793_a(-6.0F, 10.0F, 0.0F);
/*  74 */     setRotation(this.scythe, 0.0F, 0.0F, 0.0F);
/*  75 */     this.scythe.field_78809_i = true;
/*  76 */     this.scythe.func_78786_a("shaft", -0.5F, -16.0F, -0.5F, 1, 35, 1);
/*  77 */     this.scythe.func_78786_a("blade", 0.0F, -16.0F, 0.0F, 13, 4, 0);
/*  78 */     this.bipedRightArm.func_78792_a(this.scythe);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  82 */     model.field_78795_f = x;
/*  83 */     model.field_78796_g = y;
/*  84 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  89 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  90 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  91 */     this.bipedHead.func_78785_a(f5);
/*  92 */     this.bipedBody.func_78785_a(f5);
/*  93 */     this.bipedRightArm.func_78785_a(f5);
/*  94 */     this.bipedLeftArm.func_78785_a(f5);
/*  95 */     this.bipedRightLeg.func_78785_a(f5);
/*  96 */     this.bipedLeftLeg.func_78785_a(f5);
/*  97 */     GL11.glScalef(1.05F, 1.0F, 1.05F);
/*  98 */     this.robe.func_78785_a(f5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 105 */     this.scythe.field_78800_c = -0.8F;
/* 106 */     this.scythe.field_78798_e = 0.0F;
/* 107 */     this.scythe.field_78797_d = 8.1F;
/* 108 */     this.scythe.field_78795_f = 1.5707964F;
/*     */     
/* 110 */     this.bipedHead.field_78796_g = (par4 / 57.295776F);
/* 111 */     this.bipedHead.field_78795_f = (par5 / 57.295776F);
/*     */     
/* 113 */     this.bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F - 1.5707964F);
/* 114 */     this.bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 115 */     this.bipedRightArm.field_78808_h = 0.0F;
/* 116 */     this.bipedLeftArm.field_78808_h = 0.0F;
/* 117 */     this.bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 118 */     this.bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 119 */     this.bipedRightLeg.field_78796_g = 0.0F;
/* 120 */     this.bipedLeftLeg.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 138 */     this.bipedRightArm.field_78796_g = 0.0F;
/* 139 */     this.bipedLeftArm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 143 */     if (this.field_78095_p > -9990.0F) {
/* 144 */       float f6 = this.field_78095_p;
/* 145 */       this.bipedBody.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/* 146 */       this.bipedRightArm.field_78798_e = (MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 147 */       this.bipedRightArm.field_78800_c = (-MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 148 */       this.bipedLeftArm.field_78798_e = (-MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 149 */       this.bipedLeftArm.field_78800_c = (MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 150 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g;
/* 151 */       this.bipedLeftArm.field_78796_g += this.bipedBody.field_78796_g;
/* 152 */       this.bipedLeftArm.field_78795_f += this.bipedBody.field_78796_g;
/* 153 */       f6 = 1.0F - this.field_78095_p;
/* 154 */       f6 *= f6;
/* 155 */       f6 *= f6;
/* 156 */       f6 = 1.0F - f6;
/* 157 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 158 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.bipedHead.field_78795_f - 0.7F) * 0.75F;
/* 159 */       this.bipedRightArm.field_78795_f = ((float)(this.bipedRightArm.field_78795_f - (f7 * 1.2D + f8)));
/* 160 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0F;
/* 161 */       this.bipedRightArm.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 164 */     this.bipedBody.field_78795_f = 0.0F;
/* 165 */     this.bipedRightLeg.field_78798_e = 0.1F;
/* 166 */     this.bipedLeftLeg.field_78798_e = 0.1F;
/* 167 */     this.bipedRightLeg.field_78797_d = 12.0F;
/* 168 */     this.bipedLeftLeg.field_78797_d = 12.0F;
/* 169 */     this.bipedHead.field_78797_d = 0.0F;
/*     */     
/* 171 */     this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 172 */     this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 173 */     this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 174 */     this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelDeath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */