/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityHellhound;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelHellhound extends ModelBase
/*     */ {
/*     */   public ModelRenderer wolfHeadMain;
/*     */   public ModelRenderer wolfBody;
/*     */   public ModelRenderer wolfLeg1;
/*     */   public ModelRenderer wolfLeg2;
/*     */   public ModelRenderer wolfLeg3;
/*     */   public ModelRenderer wolfLeg4;
/*     */   ModelRenderer wolfTail;
/*     */   ModelRenderer wolfMane;
/*     */   
/*     */   public ModelHellhound()
/*     */   {
/*  26 */     this.field_78090_t = 64;
/*  27 */     this.field_78089_u = 32;
/*  28 */     this.wolfBody = new ModelRenderer(this, 18, 17);
/*  29 */     this.wolfBody.func_78793_a(0.0F, 12.5F, 2.0F);
/*  30 */     this.wolfBody.func_78789_a(-4.0F, -2.0F, -3.0F, 6, 9, 6);
/*  31 */     setRotateAngle(this.wolfBody, 1.1838568F, 0.0F, 0.0F);
/*  32 */     this.wolfTail = new ModelRenderer(this, 9, 19);
/*  33 */     this.wolfTail.func_78793_a(-1.0F, 12.0F, 8.0F);
/*  34 */     this.wolfTail.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 6, 2);
/*  35 */     this.wolfLeg2 = new ModelRenderer(this, 0, 19);
/*  36 */     this.wolfLeg2.func_78793_a(0.5F, 16.0F, 7.0F);
/*  37 */     this.wolfLeg2.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
/*  38 */     this.wolfLeg4 = new ModelRenderer(this, 0, 19);
/*  39 */     this.wolfLeg4.func_78793_a(0.5F, 16.0F, -4.0F);
/*  40 */     this.wolfLeg4.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
/*  41 */     this.wolfMane = new ModelRenderer(this, 22, 0);
/*  42 */     this.wolfMane.func_78793_a(-1.0F, 14.0F, -3.0F);
/*  43 */     this.wolfMane.func_78789_a(-4.0F, -3.0F, -3.0F, 8, 8, 9);
/*  44 */     setRotateAngle(this.wolfMane, 1.5707964F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  51 */     this.wolfLeg3 = new ModelRenderer(this, 0, 19);
/*  52 */     this.wolfLeg3.func_78793_a(-2.5F, 16.0F, -4.0F);
/*  53 */     this.wolfLeg3.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
/*  54 */     this.wolfHeadMain = new ModelRenderer(this, 0, 0);
/*  55 */     this.wolfHeadMain.func_78793_a(-1.0F, 10.6F, -7.5F);
/*  56 */     this.wolfHeadMain.func_78789_a(-3.0F, -3.0F, -2.0F, 6, 6, 4);
/*  57 */     setRotateAngle(this.wolfHeadMain, 0.22759093F, 0.0F, 0.0F);
/*  58 */     this.wolfLeg1 = new ModelRenderer(this, 0, 19);
/*  59 */     this.wolfLeg1.func_78793_a(-2.5F, 16.0F, 7.0F);
/*  60 */     this.wolfLeg1.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  67 */     float f = 0.0F;
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
/*  96 */     this.wolfHeadMain.func_78784_a(16, 14).func_78790_a(-3.0F, -5.0F, 1.0F, 2, 2, 1, f);
/*  97 */     this.wolfHeadMain.func_78784_a(16, 14).func_78790_a(1.0F, -5.0F, 1.0F, 2, 2, 1, f);
/*  98 */     this.wolfHeadMain.func_78784_a(0, 10).func_78790_a(-1.5F, 0.0F, -5.0F, 3, 3, 4, f);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
/*     */   {
/* 103 */     super.func_78088_a(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
/* 104 */     func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*     */     
/* 106 */     this.wolfHeadMain.func_78791_b(p_78088_7_);
/* 107 */     this.wolfBody.func_78785_a(p_78088_7_);
/* 108 */     this.wolfLeg1.func_78785_a(p_78088_7_);
/* 109 */     this.wolfLeg2.func_78785_a(p_78088_7_);
/* 110 */     this.wolfLeg3.func_78785_a(p_78088_7_);
/* 111 */     this.wolfLeg4.func_78785_a(p_78088_7_);
/* 112 */     this.wolfTail.func_78791_b(p_78088_7_);
/* 113 */     this.wolfMane.func_78785_a(p_78088_7_);
/*     */   }
/*     */   
/*     */   public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
/*     */   {
/* 118 */     EntityHellhound entitywolf = (EntityHellhound)p_78086_1_;
/*     */     
/*     */ 
/* 121 */     setRotateAngle(this.wolfTail, entitywolf.isConverting() ? 2.0F : 0.59184116F, 0.0F, 0.0F);
/*     */     
/* 123 */     if (p_78086_3_ > 0.1D) {
/* 124 */       ModelRenderer tmp43_40 = this.wolfTail;tmp43_40.field_78795_f = ((float)(tmp43_40.field_78795_f + (1.5F * p_78086_3_ - 0.1D)));
/*     */     }
/*     */     
/* 127 */     this.wolfBody.func_78793_a(0.0F, 14.0F, 2.0F);
/* 128 */     this.wolfBody.func_78793_a(0.0F, 12.5F, 2.0F);
/* 129 */     this.wolfBody.field_78795_f = 1.5707964F;
/* 130 */     setRotateAngle(this.wolfBody, 1.1838568F, 0.0F, 0.0F);
/* 131 */     this.wolfMane.func_78793_a(-1.0F, 14.0F, -3.0F);
/* 132 */     this.wolfMane.field_78795_f = 1.5707964F;
/* 133 */     this.wolfTail.func_78793_a(-1.0F, 12.0F, 8.0F);
/* 134 */     this.wolfLeg1.func_78793_a(-2.5F, 16.0F, 7.0F);
/* 135 */     this.wolfLeg2.func_78793_a(0.5F, 16.0F, 7.0F);
/* 136 */     this.wolfLeg3.func_78793_a(-2.5F, 16.0F, -4.0F);
/* 137 */     this.wolfLeg4.func_78793_a(0.5F, 16.0F, -4.0F);
/* 138 */     this.wolfLeg1.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_);
/* 139 */     this.wolfLeg2.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F + 3.1415927F) * 1.4F * p_78086_3_);
/* 140 */     this.wolfLeg3.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F + 3.1415927F) * 1.4F * p_78086_3_);
/* 141 */     this.wolfLeg4.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_);
/*     */     
/* 143 */     this.wolfTail.func_78793_a(-1.0F, 13.0F, 8.0F);
/* 144 */     this.wolfHeadMain.field_78808_h = (entitywolf.getInterestedAngle(p_78086_4_) + entitywolf.getShakeAngle(p_78086_4_, 0.0F));
/*     */     
/* 146 */     this.wolfMane.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.08F);
/* 147 */     this.wolfBody.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.16F);
/* 148 */     this.wolfTail.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.2F);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 153 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 154 */     EntityHellhound entitywolf = (EntityHellhound)p_78087_7_;
/* 155 */     this.wolfHeadMain.field_78795_f = (p_78087_5_ / 57.295776F + (entitywolf.isConverting() ? 0.5F : 0.15F));
/* 156 */     this.wolfHeadMain.field_78796_g = (p_78087_4_ / 57.295776F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
/*     */   {
/* 162 */     modelRenderer.field_78795_f = x;
/* 163 */     modelRenderer.field_78796_g = y;
/* 164 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelHellhound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */