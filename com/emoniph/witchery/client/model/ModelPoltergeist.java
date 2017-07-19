/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityPoltergeist;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelPoltergeist extends ModelBase
/*     */ {
/*     */   ModelRenderer bipedHead;
/*     */   ModelRenderer bipedBody;
/*     */   ModelRenderer bipedRightArm;
/*     */   ModelRenderer bipedRightArm2;
/*     */   ModelRenderer bipedLeftArm;
/*     */   ModelRenderer bipedLeftArm2;
/*     */   ModelRenderer bipedRightLeg;
/*     */   ModelRenderer bipedLeftLeg;
/*     */   
/*     */   public ModelPoltergeist()
/*     */   {
/*  26 */     this.field_78090_t = 64;
/*  27 */     this.field_78089_u = 32;
/*     */     
/*  29 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  30 */     this.bipedHead.func_78789_a(-4.0F, -8.0F, -3.0F, 8, 8, 6);
/*  31 */     this.bipedHead.func_78793_a(0.0F, 0.0F, 0.0F);
/*  32 */     this.bipedHead.func_78787_b(64, 32);
/*  33 */     this.bipedHead.field_78809_i = true;
/*  34 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  35 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  36 */     this.bipedBody.func_78789_a(-4.0F, 0.0F, -1.0F, 8, 11, 2);
/*  37 */     this.bipedBody.func_78793_a(0.0F, 0.0F, 0.0F);
/*  38 */     this.bipedBody.func_78787_b(64, 32);
/*  39 */     this.bipedBody.field_78809_i = true;
/*  40 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  41 */     this.bipedRightArm = new ModelRenderer(this, 40, 0);
/*  42 */     this.bipedRightArm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
/*  43 */     this.bipedRightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  44 */     this.bipedRightArm.func_78787_b(64, 32);
/*  45 */     this.bipedRightArm.field_78809_i = true;
/*  46 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  47 */     this.bipedRightArm2 = new ModelRenderer(this, 40, 0);
/*  48 */     this.bipedRightArm2.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
/*  49 */     this.bipedRightArm2.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  50 */     this.bipedRightArm2.func_78787_b(64, 32);
/*  51 */     this.bipedRightArm2.field_78809_i = true;
/*  52 */     setRotation(this.bipedRightArm2, 0.0F, 0.0F, 0.0F);
/*  53 */     this.bipedLeftArm = new ModelRenderer(this, 40, 0);
/*  54 */     this.bipedLeftArm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
/*  55 */     this.bipedLeftArm.func_78793_a(5.0F, 2.0F, 0.0F);
/*  56 */     this.bipedLeftArm.func_78787_b(64, 32);
/*  57 */     this.bipedLeftArm.field_78809_i = true;
/*  58 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  59 */     this.bipedLeftArm2 = new ModelRenderer(this, 40, 0);
/*  60 */     this.bipedLeftArm2.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
/*  61 */     this.bipedLeftArm2.func_78793_a(5.0F, 2.0F, 0.0F);
/*  62 */     this.bipedLeftArm2.func_78787_b(64, 32);
/*  63 */     this.bipedLeftArm2.field_78809_i = true;
/*  64 */     setRotation(this.bipedLeftArm2, 0.0F, 0.0F, 0.0F);
/*  65 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  66 */     this.bipedRightLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 13, 2);
/*  67 */     this.bipedRightLeg.func_78793_a(-2.0F, 11.0F, 0.0F);
/*  68 */     this.bipedRightLeg.func_78787_b(64, 32);
/*  69 */     this.bipedRightLeg.field_78809_i = true;
/*  70 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  71 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  72 */     this.bipedLeftLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 13, 2);
/*  73 */     this.bipedLeftLeg.func_78793_a(2.0F, 11.0F, 0.0F);
/*  74 */     this.bipedLeftLeg.func_78787_b(64, 32);
/*  75 */     this.bipedLeftLeg.field_78809_i = true;
/*  76 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  81 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  82 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  83 */     this.bipedHead.func_78785_a(f5);
/*  84 */     this.bipedBody.func_78785_a(f5);
/*  85 */     this.bipedRightArm.func_78785_a(f5);
/*  86 */     this.bipedRightArm2.func_78785_a(f5);
/*  87 */     this.bipedLeftArm.func_78785_a(f5);
/*  88 */     this.bipedLeftArm2.func_78785_a(f5);
/*  89 */     this.bipedRightLeg.func_78785_a(f5);
/*  90 */     this.bipedLeftLeg.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  94 */     model.field_78795_f = x;
/*  95 */     model.field_78796_g = y;
/*  96 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 101 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
/* 102 */     this.bipedHead.field_78796_g = (par4 / 57.295776F);
/* 103 */     this.bipedHead.field_78795_f = (par5 / 57.295776F);
/*     */     
/* 105 */     this.bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 106 */     this.bipedRightArm2.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.25F);
/*     */     
/* 108 */     this.bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 109 */     this.bipedLeftArm2.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.25F);
/*     */     
/* 111 */     this.bipedRightArm.field_78796_g = 0.0F;
/* 112 */     this.bipedRightArm2.field_78796_g = 0.0F;
/*     */     
/* 114 */     this.bipedLeftArm.field_78796_g = 0.0F;
/* 115 */     this.bipedLeftArm2.field_78796_g = 0.0F;
/*     */     
/* 117 */     this.bipedRightArm.field_78808_h = 0.0F;
/* 118 */     this.bipedRightArm2.field_78808_h = 0.0F;
/*     */     
/* 120 */     this.bipedLeftArm.field_78808_h = 0.0F;
/* 121 */     this.bipedLeftArm2.field_78808_h = 0.0F;
/*     */     
/*     */ 
/* 124 */     this.bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 125 */     this.bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 126 */     this.bipedRightLeg.field_78796_g = 0.0F;
/* 127 */     this.bipedLeftLeg.field_78796_g = 0.0F;
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
/* 162 */     this.bipedBody.field_78795_f = 0.0F;
/* 163 */     this.bipedRightLeg.field_78798_e = 0.1F;
/* 164 */     this.bipedLeftLeg.field_78798_e = 0.1F;
/* 165 */     this.bipedRightLeg.field_78797_d = 12.0F;
/* 166 */     this.bipedLeftLeg.field_78797_d = 12.0F;
/* 167 */     this.bipedHead.field_78797_d = 0.0F;
/*     */     
/*     */ 
/* 170 */     this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 171 */     this.bipedRightArm2.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/*     */     
/* 173 */     this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 174 */     this.bipedLeftArm2.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 175 */     this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 176 */     this.bipedRightArm2.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 177 */     this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 178 */     this.bipedLeftArm2.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     
/*     */ 
/* 181 */     EntityPoltergeist entityDemon = (EntityPoltergeist)entity;
/* 182 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 184 */     if (i > 0) {
/* 185 */       this.bipedRightArm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/* 186 */       this.bipedLeftArm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/*     */       
/* 188 */       this.bipedRightArm2.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/* 189 */       this.bipedLeftArm2.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/*     */       
/* 191 */       this.bipedRightArm.field_78808_h = (-(-1.5F + 1.5F * func_78172_a(i - par4, 15.0F)));
/* 192 */       this.bipedLeftArm.field_78808_h = (-1.5F + 1.5F * func_78172_a(i - par4, 15.0F));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private float func_78172_a(float par1, float par2)
/*     */   {
/* 208 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelPoltergeist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */