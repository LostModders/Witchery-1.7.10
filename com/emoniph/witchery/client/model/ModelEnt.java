/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityEnt;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelEnt extends ModelBase
/*     */ {
/*     */   ModelRenderer ArmLeft;
/*     */   ModelRenderer ArmRight;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Face;
/*     */   ModelRenderer Leg8;
/*     */   ModelRenderer Leg6;
/*     */   ModelRenderer Leg4;
/*     */   ModelRenderer Leg2;
/*     */   ModelRenderer Leg7;
/*     */   ModelRenderer Leg5;
/*     */   ModelRenderer Leg3;
/*     */   ModelRenderer Leg1;
/*     */   ModelRenderer LeavesBase;
/*     */   ModelRenderer LeavesTop;
/*     */   ModelRenderer LeavesBaseInner;
/*     */   ModelRenderer LeavesTopInner;
/*     */   
/*     */   public ModelEnt()
/*     */   {
/*  34 */     this.field_78090_t = 256;
/*  35 */     this.field_78089_u = 256;
/*     */     
/*  37 */     this.ArmLeft = new ModelRenderer(this, 82, 0);
/*  38 */     this.ArmLeft.func_78789_a(0.0F, -22.0F, -3.0F, 6, 24, 6);
/*  39 */     this.ArmLeft.func_78793_a(8.0F, -4.0F, 0.0F);
/*  40 */     this.ArmLeft.func_78787_b(256, 256);
/*  41 */     this.ArmLeft.field_78809_i = true;
/*  42 */     setRotation(this.ArmLeft, 0.0F, 0.0F, 0.0F);
/*  43 */     this.ArmRight = new ModelRenderer(this, 82, 0);
/*  44 */     this.ArmRight.func_78789_a(-6.0F, -22.0F, -3.0F, 6, 24, 6);
/*  45 */     this.ArmRight.func_78793_a(-8.0F, -4.0F, 0.0F);
/*  46 */     this.ArmRight.func_78787_b(256, 256);
/*  47 */     this.ArmRight.field_78809_i = true;
/*  48 */     setRotation(this.ArmRight, 0.0F, 0.0F, 0.0F);
/*  49 */     this.Body = new ModelRenderer(this, 0, 50);
/*  50 */     this.Body.func_78789_a(-8.0F, -46.0F, -8.0F, 16, 48, 16);
/*  51 */     this.Body.func_78793_a(0.0F, 20.0F, 0.0F);
/*  52 */     this.Body.func_78787_b(256, 256);
/*  53 */     this.Body.field_78809_i = true;
/*  54 */     setRotation(this.Body, 0.0F, 0.0F, 0.0F);
/*     */     
/*  56 */     this.Face = new ModelRenderer(this, 0, 116);
/*  57 */     this.Face.func_78789_a(-8.0F, -46.0F, -9.0F, 16, 24, 16);
/*  58 */     this.Face.func_78793_a(0.0F, 20.0F, 0.0F);
/*  59 */     this.Face.func_78787_b(256, 256);
/*  60 */     this.Face.field_78809_i = true;
/*  61 */     setRotation(this.Face, 0.0F, 0.0F, 0.0F);
/*     */     
/*  63 */     this.Leg8 = new ModelRenderer(this, 18, 0);
/*  64 */     this.Leg8.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
/*  65 */     this.Leg8.func_78793_a(4.0F, 20.0F, -1.0F);
/*  66 */     this.Leg8.func_78787_b(256, 256);
/*  67 */     this.Leg8.field_78809_i = true;
/*  68 */     setRotation(this.Leg8, 0.0F, 0.5759587F, 0.1919862F);
/*  69 */     this.Leg6 = new ModelRenderer(this, 18, 0);
/*  70 */     this.Leg6.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
/*  71 */     this.Leg6.func_78793_a(4.0F, 20.0F, 0.0F);
/*  72 */     this.Leg6.func_78787_b(256, 256);
/*  73 */     this.Leg6.field_78809_i = true;
/*  74 */     setRotation(this.Leg6, 0.0F, 0.2792527F, 0.1919862F);
/*  75 */     this.Leg4 = new ModelRenderer(this, 18, 0);
/*  76 */     this.Leg4.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
/*  77 */     this.Leg4.func_78793_a(4.0F, 20.0F, 1.0F);
/*  78 */     this.Leg4.func_78787_b(256, 256);
/*  79 */     this.Leg4.field_78809_i = true;
/*  80 */     setRotation(this.Leg4, 0.0F, -0.2792527F, 0.1919862F);
/*  81 */     this.Leg2 = new ModelRenderer(this, 18, 0);
/*  82 */     this.Leg2.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
/*  83 */     this.Leg2.func_78793_a(4.0F, 20.0F, 2.0F);
/*  84 */     this.Leg2.func_78787_b(256, 256);
/*  85 */     this.Leg2.field_78809_i = true;
/*  86 */     setRotation(this.Leg2, 0.0F, -0.5759587F, 0.1919862F);
/*  87 */     this.Leg7 = new ModelRenderer(this, 18, 0);
/*  88 */     this.Leg7.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
/*  89 */     this.Leg7.func_78793_a(-4.0F, 20.0F, -1.0F);
/*  90 */     this.Leg7.func_78787_b(256, 256);
/*  91 */     this.Leg7.field_78809_i = true;
/*  92 */     setRotation(this.Leg7, 0.0F, -0.5759587F, -0.1919862F);
/*  93 */     this.Leg5 = new ModelRenderer(this, 18, 0);
/*  94 */     this.Leg5.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
/*  95 */     this.Leg5.func_78793_a(-4.0F, 20.0F, 0.0F);
/*  96 */     this.Leg5.func_78787_b(256, 256);
/*  97 */     this.Leg5.field_78809_i = true;
/*  98 */     setRotation(this.Leg5, 0.0F, -0.2792527F, -0.1919862F);
/*  99 */     this.Leg3 = new ModelRenderer(this, 18, 0);
/* 100 */     this.Leg3.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
/* 101 */     this.Leg3.func_78793_a(-4.0F, 20.0F, 1.0F);
/* 102 */     this.Leg3.func_78787_b(256, 256);
/* 103 */     this.Leg3.field_78809_i = true;
/* 104 */     setRotation(this.Leg3, 0.0F, 0.2792527F, -0.1919862F);
/* 105 */     this.Leg1 = new ModelRenderer(this, 18, 0);
/* 106 */     this.Leg1.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
/* 107 */     this.Leg1.func_78793_a(-4.0F, 20.0F, 2.0F);
/* 108 */     this.Leg1.func_78787_b(256, 256);
/* 109 */     this.Leg1.field_78809_i = true;
/* 110 */     setRotation(this.Leg1, 0.0F, 0.5759587F, -0.1919862F);
/* 111 */     this.LeavesBase = new ModelRenderer(this, 0, 180);
/* 112 */     this.LeavesBase.func_78789_a(0.0F, 0.0F, 0.0F, 60, 16, 60);
/* 113 */     this.LeavesBase.func_78793_a(-30.0F, -42.0F, -30.0F);
/* 114 */     this.LeavesBase.func_78787_b(256, 256);
/* 115 */     this.LeavesBase.field_78809_i = true;
/* 116 */     setRotation(this.LeavesBase, 0.0F, 0.0F, 0.0F);
/* 117 */     this.LeavesTop = new ModelRenderer(this, 56, 130);
/* 118 */     this.LeavesTop.func_78789_a(0.0F, 0.0F, 0.0F, 32, 16, 32);
/* 119 */     this.LeavesTop.func_78793_a(-16.0F, -58.0F, -16.0F);
/* 120 */     this.LeavesTop.func_78787_b(256, 256);
/* 121 */     this.LeavesTop.field_78809_i = true;
/* 122 */     setRotation(this.LeavesTop, 0.0F, 0.0F, 0.0F);
/*     */     
/* 124 */     this.LeavesBaseInner = new ModelRenderer(this, 24, 59);
/* 125 */     this.LeavesBaseInner.func_78789_a(0.0F, 0.0F, 0.0F, 56, 14, 56);
/* 126 */     this.LeavesBaseInner.func_78793_a(-28.0F, -41.0F, -28.0F);
/* 127 */     this.LeavesBaseInner.func_78787_b(64, 32);
/* 128 */     this.LeavesBaseInner.field_78809_i = true;
/* 129 */     setRotation(this.LeavesBaseInner, 0.0F, 0.0F, 0.0F);
/*     */     
/* 131 */     this.LeavesTopInner = new ModelRenderer(this, 108, 14);
/* 132 */     this.LeavesTopInner.func_78789_a(0.0F, 0.0F, 0.0F, 28, 14, 28);
/* 133 */     this.LeavesTopInner.func_78793_a(-14.0F, -57.0F, -14.0F);
/* 134 */     this.LeavesTopInner.func_78787_b(64, 32);
/* 135 */     this.LeavesTopInner.field_78809_i = true;
/* 136 */     setRotation(this.LeavesTopInner, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 141 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 142 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 143 */     this.ArmLeft.func_78785_a(f5);
/* 144 */     this.ArmRight.func_78785_a(f5);
/* 145 */     this.Body.func_78785_a(f5);
/* 146 */     this.Leg8.func_78785_a(f5);
/* 147 */     this.Leg6.func_78785_a(f5);
/* 148 */     this.Leg4.func_78785_a(f5);
/* 149 */     this.Leg2.func_78785_a(f5);
/* 150 */     this.Leg7.func_78785_a(f5);
/* 151 */     this.Leg5.func_78785_a(f5);
/* 152 */     this.Leg3.func_78785_a(f5);
/* 153 */     this.Leg1.func_78785_a(f5);
/*     */     
/* 155 */     this.LeavesBaseInner.func_78785_a(f5);
/* 156 */     this.LeavesTopInner.func_78785_a(f5);
/*     */     
/* 158 */     this.LeavesBase.func_78785_a(f5);
/* 159 */     this.LeavesTop.func_78785_a(f5);
/*     */     
/* 161 */     if ((entity != null) && ((entity instanceof EntityEnt)) && (((EntityEnt)entity).isScreaming())) {
/* 162 */       this.Face.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 167 */     model.field_78795_f = x;
/* 168 */     model.field_78796_g = y;
/* 169 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/* 174 */     EntityEnt entity = (EntityEnt)par1EntityLiving;
/* 175 */     int i = entity.getAttackTimer();
/*     */     
/* 177 */     if (i > 0) {
/* 178 */       this.ArmRight.field_78795_f = (3.0F - 1.3F * func_78172_a(i - par4, 10.0F));
/* 179 */       this.ArmLeft.field_78795_f = (2.5F - 1.2F * func_78172_a(i - par4, 10.0F));
/*     */     } else {
/* 181 */       this.ArmRight.field_78795_f = 0.0F;
/* 182 */       this.ArmLeft.field_78795_f = 0.0F;
/* 183 */       this.ArmRight.field_78808_h = ((-0.2F + 0.1F * func_78172_a(par2, 13.0F)) * par3 - 0.1F);
/* 184 */       this.ArmLeft.field_78808_h = ((0.2F - 0.1F * func_78172_a(par2, 13.0F)) * par3 + 0.1F);
/*     */     }
/*     */   }
/*     */   
/*     */   private float func_78172_a(float par1, float par2) {
/* 189 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 194 */     super.func_78087_a(par1, par2, f2, f3, f4, f5, entity);
/*     */     
/* 196 */     float f6 = 0.7853982F;
/* 197 */     this.Leg1.field_78808_h = (-f6);
/* 198 */     this.Leg2.field_78808_h = f6;
/* 199 */     this.Leg3.field_78808_h = (-f6 * 0.74F);
/* 200 */     this.Leg4.field_78808_h = (f6 * 0.74F);
/* 201 */     this.Leg5.field_78808_h = (-f6 * 0.74F);
/* 202 */     this.Leg6.field_78808_h = (f6 * 0.74F);
/* 203 */     this.Leg7.field_78808_h = (-f6);
/* 204 */     this.Leg8.field_78808_h = f6;
/* 205 */     float f7 = -0.0F;
/* 206 */     float f8 = 0.3926991F;
/* 207 */     this.Leg1.field_78796_g = (f8 * 2.0F + f7);
/* 208 */     this.Leg2.field_78796_g = (-f8 * 2.0F - f7);
/* 209 */     this.Leg3.field_78796_g = (f8 * 1.0F + f7);
/* 210 */     this.Leg4.field_78796_g = (-f8 * 1.0F - f7);
/* 211 */     this.Leg5.field_78796_g = (-f8 * 1.0F + f7);
/* 212 */     this.Leg6.field_78796_g = (f8 * 1.0F - f7);
/* 213 */     this.Leg7.field_78796_g = (-f8 * 2.0F + f7);
/* 214 */     this.Leg8.field_78796_g = (f8 * 2.0F - f7);
/* 215 */     float f9 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
/* 216 */     float f10 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * par2;
/* 217 */     float f11 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 1.5707964F) * 0.4F) * par2;
/* 218 */     float f12 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 4.712389F) * 0.4F) * par2;
/* 219 */     float f13 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
/* 220 */     float f14 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 3.1415927F) * 0.4F) * par2;
/* 221 */     float f15 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 1.5707964F) * 0.4F) * par2;
/* 222 */     float f16 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 4.712389F) * 0.4F) * par2;
/* 223 */     this.Leg1.field_78796_g += f9;
/* 224 */     this.Leg2.field_78796_g += -f9;
/* 225 */     this.Leg3.field_78796_g += f10;
/* 226 */     this.Leg4.field_78796_g += -f10;
/* 227 */     this.Leg5.field_78796_g += f11;
/* 228 */     this.Leg6.field_78796_g += -f11;
/* 229 */     this.Leg7.field_78796_g += f12;
/* 230 */     this.Leg8.field_78796_g += -f12;
/* 231 */     this.Leg1.field_78808_h += f13;
/* 232 */     this.Leg2.field_78808_h += -f13;
/* 233 */     this.Leg3.field_78808_h += f14;
/* 234 */     this.Leg4.field_78808_h += -f14;
/* 235 */     this.Leg5.field_78808_h += f15;
/* 236 */     this.Leg6.field_78808_h += -f15;
/* 237 */     this.Leg7.field_78808_h += f16;
/* 238 */     this.Leg8.field_78808_h += -f16;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelEnt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */