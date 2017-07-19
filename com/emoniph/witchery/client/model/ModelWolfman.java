/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityReflection;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelWolfman
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer headMain;
/*     */   public ModelRenderer bodyUpper;
/*     */   public ModelRenderer legRightUpper;
/*     */   public ModelRenderer legLeftUpper;
/*     */   public ModelRenderer armLeft;
/*     */   public ModelRenderer armRight;
/*     */   public ModelRenderer tail;
/*     */   public ModelRenderer bodyLower;
/*     */   public ModelRenderer legRightLower;
/*     */   public ModelRenderer legLeftLower;
/*     */   public int heldItemLeft;
/*     */   public int heldItemRight;
/*     */   public boolean isSneak;
/*     */   public boolean aimedBow;
/*     */   
/*     */   public ModelWolfman()
/*     */   {
/*  34 */     this(0.0F);
/*     */   }
/*     */   
/*     */   public ModelWolfman(float scale) {
/*  38 */     this.field_78090_t = 64;
/*  39 */     this.field_78089_u = 64;
/*     */     
/*  41 */     float headScale = 0.05F;
/*  42 */     this.headMain = new ModelRenderer(this, 0, 0);
/*  43 */     this.headMain.func_78790_a(-3.0F, -6.0F, -2.0F, 6, 6, 4, 0.05F);
/*  44 */     this.headMain.func_78793_a(0.0F, 0.0F, -2.0F);
/*     */     
/*  46 */     float f = 0.0F;
/*  47 */     this.headMain.func_78784_a(16, 14).func_78790_a(-3.0F, -8.0F, 1.0F, 2, 2, 1, 0.0F);
/*  48 */     this.headMain.func_78784_a(16, 14).func_78790_a(1.0F, -8.0F, 1.0F, 2, 2, 1, 0.0F);
/*  49 */     this.headMain.func_78784_a(0, 10).func_78790_a(-1.5F, -3.1F, -5.0F, 3, 3, 4, 0.0F);
/*     */     
/*  51 */     this.bodyUpper = new ModelRenderer(this, 0, 35);
/*  52 */     this.bodyUpper.func_78793_a(0.0F, -0.1F, -2.0F);
/*  53 */     this.bodyUpper.func_78790_a(-5.0F, 0.0F, -3.9F, 10, 7, 8, scale);
/*  54 */     setRotateAngle(this.bodyUpper, 0.4098033F, 0.0F, 0.0F);
/*  55 */     this.bodyLower = new ModelRenderer(this, 3, 50);
/*  56 */     this.bodyLower.func_78793_a(0.0F, 5.0F, -1.5F);
/*  57 */     this.bodyLower.func_78790_a(-4.0F, 2.0F, -2.3F, 8, 7, 5, scale);
/*  58 */     this.bodyUpper.func_78792_a(this.bodyLower);
/*     */     
/*  60 */     this.tail = new ModelRenderer(this, 55, 52);
/*  61 */     this.tail.func_78793_a(0.0F, 11.9F, 3.6F);
/*  62 */     this.tail.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 10, 2, scale);
/*  63 */     setRotateAngle(this.tail, 0.59184116F, 0.0F, 0.0F);
/*     */     
/*  65 */     this.legLeftUpper = new ModelRenderer(this, 38, 0);
/*  66 */     this.legLeftUpper.field_78809_i = true;
/*  67 */     this.legLeftUpper.func_78793_a(2.0F, 12.0F, 0.0F);
/*  68 */     this.legLeftUpper.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 7, 4, scale);
/*  69 */     setRotateAngle(this.legLeftUpper, -0.4098033F, 0.0F, 0.0F);
/*  70 */     this.legLeftLower = new ModelRenderer(this, 38, 13);
/*  71 */     this.legLeftLower.func_78793_a(0.0F, 0.0F, 0.0F);
/*  72 */     this.legLeftLower.func_78790_a(-2.0F, 3.5F, 2.0F, 4, 8, 4, scale);
/*  73 */     this.legLeftUpper.func_78792_a(this.legLeftLower);
/*     */     
/*  75 */     this.legRightUpper = new ModelRenderer(this, 38, 0);
/*  76 */     this.legRightUpper.func_78793_a(-2.0F, 12.0F, 0.0F);
/*  77 */     this.legRightUpper.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 7, 4, scale);
/*  78 */     setRotateAngle(this.legRightUpper, -0.4098033F, 0.0F, 0.0F);
/*  79 */     this.legRightLower = new ModelRenderer(this, 38, 13);
/*  80 */     this.legRightLower.func_78793_a(0.0F, 0.0F, 0.0F);
/*  81 */     this.legRightLower.func_78790_a(-2.0F, 3.5F, 2.0F, 4, 8, 4, scale);
/*  82 */     this.legRightUpper.func_78792_a(this.legRightLower);
/*     */     
/*  84 */     this.armLeft = new ModelRenderer(this, 38, 46);
/*  85 */     this.armLeft.field_78809_i = true;
/*  86 */     this.armLeft.func_78793_a(6.0F, 2.0F, 0.0F);
/*  87 */     this.armLeft.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 14, 4, scale);
/*     */     
/*  89 */     this.armRight = new ModelRenderer(this, 38, 46);
/*  90 */     this.armRight.func_78793_a(-5.8F, 2.0F, 0.0F);
/*  91 */     this.armRight.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 14, 4, scale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
/*     */   {
/* 100 */     func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
/*     */     
/* 102 */     this.headMain.func_78785_a(p_78088_7_);
/* 103 */     this.bodyUpper.func_78785_a(p_78088_7_);
/* 104 */     this.armRight.func_78785_a(p_78088_7_);
/* 105 */     this.legLeftUpper.func_78785_a(p_78088_7_);
/* 106 */     this.tail.func_78785_a(p_78088_7_);
/* 107 */     this.armLeft.func_78785_a(p_78088_7_);
/* 108 */     this.legRightUpper.func_78785_a(p_78088_7_);
/*     */   }
/*     */   
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 112 */     modelRenderer.field_78795_f = x;
/* 113 */     modelRenderer.field_78796_g = y;
/* 114 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78086_a(EntityLivingBase entity, float par2, float par3, float par4)
/*     */   {
/* 119 */     float i = 0.0F;
/* 120 */     if ((entity instanceof EntityWolfman)) {
/* 121 */       EntityWolfman wolfman = (EntityWolfman)entity;
/* 122 */       i = wolfman.getAttackTimer();
/* 123 */       this.field_78093_q = wolfman.isSitting();
/* 124 */     } else if ((entity instanceof EntityReflection)) {
/* 125 */       EntityReflection wolfman = (EntityReflection)entity;
/* 126 */       i = wolfman.getAttackTimer();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 131 */     if (i > 0.0F) {
/* 132 */       this.armRight.field_78795_f = (-2.0F + 1.5F * interpolateRotation(i - par4, 10.0F));
/* 133 */       this.armLeft.field_78795_f = (-1.0F + 0.9F * interpolateRotation(i - par4, 10.0F));
/*     */     } else {
/* 135 */       this.armRight.field_78795_f = (MathHelper.func_76134_b(par2 * 0.6662F + 3.1415927F) * 2.0F * par3 * 0.5F);
/* 136 */       this.armLeft.field_78795_f = (MathHelper.func_76134_b(par2 * 0.6662F) * 2.0F * par3 * 0.5F);
/*     */     }
/*     */   }
/*     */   
/*     */   private float interpolateRotation(float par1, float par2) {
/* 141 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 147 */     this.headMain.field_78796_g = (p_78087_4_ / 57.295776F);
/* 148 */     this.headMain.field_78795_f = (p_78087_5_ / 57.295776F);
/*     */     
/* 150 */     this.armRight.field_78808_h = 0.0F;
/* 151 */     this.armLeft.field_78808_h = 0.0F;
/*     */     
/* 153 */     this.legRightUpper.field_78795_f = Math.max(-0.4098033F + MathHelper.func_76134_b(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_, -0.8F);
/*     */     
/* 155 */     this.legLeftUpper.field_78795_f = Math.max(-0.4098033F + MathHelper.func_76134_b(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_, -0.8F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 161 */     this.legRightUpper.field_78796_g = 0.0F;
/* 162 */     this.legLeftUpper.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 166 */     if (this.field_78093_q) {
/* 167 */       this.armRight.field_78795_f += -0.62831855F;
/* 168 */       this.armLeft.field_78795_f += -0.62831855F;
/* 169 */       this.legRightUpper.field_78795_f = -1.2566371F;
/* 170 */       this.legLeftUpper.field_78795_f = -1.2566371F;
/* 171 */       this.legRightUpper.field_78796_g = 0.31415927F;
/* 172 */       this.legLeftUpper.field_78796_g = -0.31415927F;
/*     */     }
/*     */     
/* 175 */     if (this.heldItemLeft != 0) {
/* 176 */       this.armLeft.field_78795_f = (this.armLeft.field_78795_f * 0.5F - 0.31415927F * this.heldItemLeft);
/*     */     }
/*     */     
/*     */ 
/* 180 */     if (this.heldItemRight != 0) {
/* 181 */       this.armRight.field_78795_f = (this.armRight.field_78795_f * 0.5F - 0.31415927F * this.heldItemRight);
/*     */     }
/*     */     
/*     */ 
/* 185 */     this.armRight.field_78796_g = 0.0F;
/* 186 */     this.armLeft.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 190 */     if (this.field_78095_p > -9990.0F) {
/* 191 */       float f6 = this.field_78095_p;
/* 192 */       this.bodyUpper.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/* 193 */       this.armRight.field_78798_e = (MathHelper.func_76126_a(this.bodyUpper.field_78796_g) * 5.0F);
/* 194 */       this.armRight.field_78800_c = (-MathHelper.func_76134_b(this.bodyUpper.field_78796_g) * 5.0F);
/* 195 */       this.armLeft.field_78798_e = (-MathHelper.func_76126_a(this.bodyUpper.field_78796_g) * 5.0F);
/* 196 */       this.armLeft.field_78800_c = (MathHelper.func_76134_b(this.bodyUpper.field_78796_g) * 5.0F);
/* 197 */       this.armRight.field_78796_g += this.bodyUpper.field_78796_g;
/* 198 */       this.armLeft.field_78796_g += this.bodyUpper.field_78796_g;
/* 199 */       this.armLeft.field_78795_f += this.bodyUpper.field_78796_g;
/* 200 */       f6 = 1.0F - this.field_78095_p;
/* 201 */       f6 *= f6;
/* 202 */       f6 *= f6;
/* 203 */       f6 = 1.0F - f6;
/* 204 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 205 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.headMain.field_78795_f - 0.7F) * 0.75F;
/*     */       
/* 207 */       this.armRight.field_78795_f = ((float)(this.armRight.field_78795_f - (f7 * 1.2D + f8)));
/* 208 */       this.armRight.field_78796_g += this.bodyUpper.field_78796_g * 2.0F;
/* 209 */       this.armRight.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 212 */     if (this.isSneak) {
/* 213 */       this.bodyUpper.field_78795_f = 0.5F;
/* 214 */       this.armRight.field_78795_f += 0.4F;
/* 215 */       this.armLeft.field_78795_f += 0.4F;
/* 216 */       this.legRightUpper.field_78798_e = 4.0F;
/* 217 */       this.legLeftUpper.field_78798_e = 4.0F;
/* 218 */       this.legRightUpper.field_78797_d = 9.0F;
/* 219 */       this.legLeftUpper.field_78797_d = 9.0F;
/* 220 */       this.headMain.field_78797_d = 0.0F;
/*     */     }
/*     */     else {
/* 223 */       setRotateAngle(this.bodyUpper, 0.4098033F, 0.0F, 0.0F);
/*     */       
/*     */ 
/* 226 */       this.legRightUpper.field_78798_e = 0.1F;
/* 227 */       this.legLeftUpper.field_78798_e = 0.1F;
/* 228 */       this.legRightUpper.field_78797_d = 12.0F;
/* 229 */       this.legLeftUpper.field_78797_d = 12.0F;
/* 230 */       this.headMain.field_78797_d = 0.0F;
/*     */     }
/*     */     
/* 233 */     setRotateAngle(this.tail, 0.59184116F, 0.0F, 0.0F);
/*     */     
/* 235 */     if (p_78087_2_ > 0.1D) {
/* 236 */       ModelRenderer tmp750_747 = this.tail;tmp750_747.field_78795_f = ((float)(tmp750_747.field_78795_f + (p_78087_2_ - 0.1D)));
/* 237 */       this.tail.field_78808_h += 5.0F * MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/*     */     }
/*     */     else {
/* 240 */       this.tail.field_78808_h += 3.0F * MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/*     */     }
/*     */     
/* 243 */     this.armRight.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 244 */     this.armLeft.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 245 */     this.armRight.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 246 */     this.armLeft.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */     
/* 248 */     if (this.aimedBow) {
/* 249 */       float f6 = 0.0F;
/* 250 */       float f7 = 0.0F;
/* 251 */       this.armRight.field_78808_h = 0.0F;
/* 252 */       this.armLeft.field_78808_h = 0.0F;
/* 253 */       this.armRight.field_78796_g = (-(0.1F - f6 * 0.6F) + this.headMain.field_78796_g);
/* 254 */       this.armLeft.field_78796_g = (0.1F - f6 * 0.6F + this.headMain.field_78796_g + 0.4F);
/* 255 */       this.armRight.field_78795_f = (-1.5707964F + this.headMain.field_78795_f);
/* 256 */       this.armLeft.field_78795_f = (-1.5707964F + this.headMain.field_78795_f);
/* 257 */       this.armRight.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 258 */       this.armLeft.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 259 */       this.armRight.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 260 */       this.armLeft.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 261 */       this.armRight.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 262 */       this.armLeft.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelWolfman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */