/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.model.ModelVillager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelBabaYaga extends ModelVillager
/*     */ {
/*     */   public boolean field_82900_g;
/*  15 */   private ModelRenderer field_82901_h = new ModelRenderer(this).func_78787_b(64, 128);
/*     */   private ModelRenderer witchHat;
/*     */   private ModelRenderer mortar;
/*     */   private ModelRenderer pestle;
/*     */   public ModelRenderer bipedCloak;
/*     */   
/*     */   public ModelBabaYaga(float par1) {
/*  22 */     super(par1, 0.0F, 128, 128);
/*  23 */     this.bipedCloak = new ModelRenderer(this, 94, 0);
/*  24 */     this.bipedCloak.func_78787_b(128, 128);
/*  25 */     this.bipedCloak.func_78789_a(0.0F, 0.0F, 0.0F, 8, 10, 0);
/*  26 */     this.bipedCloak.field_78795_f = 0.1F;
/*     */     
/*     */ 
/*     */ 
/*  30 */     this.field_82901_h.func_78793_a(0.0F, -2.0F, 0.0F);
/*  31 */     this.field_82901_h.func_78784_a(0, 0).func_78790_a(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.15F);
/*  32 */     this.field_82898_f.func_78792_a(this.field_82901_h);
/*     */     
/*     */ 
/*  35 */     this.witchHat = new ModelRenderer(this).func_78787_b(128, 128);
/*  36 */     this.witchHat.func_78793_a(-7.0F, -10.03125F, -7.0F);
/*  37 */     this.witchHat.func_78784_a(0, 98).func_78789_a(0.0F, 0.0F, 0.0F, 14, 2, 14);
/*  38 */     this.field_78191_a.func_78792_a(this.witchHat);
/*     */     
/*  40 */     ModelRenderer modelrenderer = new ModelRenderer(this).func_78787_b(128, 128);
/*  41 */     modelrenderer.func_78793_a(3.75F, -4.0F, 4.0F);
/*  42 */     modelrenderer.func_78784_a(0, 76).func_78789_a(0.0F, 0.0F, 0.0F, 7, 4, 7);
/*  43 */     modelrenderer.field_78795_f = -0.05235988F;
/*  44 */     modelrenderer.field_78808_h = 0.02617994F;
/*  45 */     this.witchHat.func_78792_a(modelrenderer);
/*     */     
/*  47 */     ModelRenderer modelrenderer1 = new ModelRenderer(this).func_78787_b(128, 128);
/*  48 */     modelrenderer1.func_78793_a(1.75F, -4.0F, 2.0F);
/*  49 */     modelrenderer1.func_78784_a(0, 87).func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
/*  50 */     modelrenderer1.field_78795_f = -0.10471976F;
/*  51 */     modelrenderer1.field_78808_h = 0.05235988F;
/*  52 */     modelrenderer.func_78792_a(modelrenderer1);
/*     */     
/*  54 */     ModelRenderer modelrenderer2 = new ModelRenderer(this).func_78787_b(128, 128);
/*  55 */     modelrenderer2.func_78793_a(1.75F, -2.0F, 2.0F);
/*  56 */     modelrenderer2.func_78784_a(0, 95).func_78790_a(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
/*  57 */     modelrenderer2.field_78795_f = -0.20943952F;
/*  58 */     modelrenderer2.field_78808_h = 0.10471976F;
/*  59 */     modelrenderer1.func_78792_a(modelrenderer2);
/*     */     
/*     */ 
/*  62 */     func_78085_a("mortar.bottom", 80, 88);
/*  63 */     func_78085_a("mortar.top", 72, 107);
/*  64 */     func_78085_a("pestle.upper", 124, 0);
/*  65 */     func_78085_a("pestle.lower", 116, 13);
/*     */     
/*  67 */     this.mortar = new ModelRenderer(this, "mortar");
/*  68 */     this.mortar.func_78787_b(128, 128);
/*  69 */     this.mortar.func_78793_a(-7.0F, 10.0F, -8.0F);
/*  70 */     setRotation(this.mortar, 0.0F, 0.0F, 0.0F);
/*  71 */     this.mortar.field_78809_i = true;
/*  72 */     this.mortar.func_78786_a("bottom", 1.0F, 7.0F, 2.0F, 12, 7, 12);
/*  73 */     this.mortar.func_78786_a("top", 0.0F, 0.0F, 1.0F, 14, 7, 14);
/*  74 */     this.pestle = new ModelRenderer(this, "pestle");
/*  75 */     this.pestle.func_78787_b(128, 128);
/*  76 */     this.pestle.func_78793_a(-3.0F, 6.0F, -4.0F);
/*  77 */     setRotation(this.pestle, -1.152537F, -2.305074F, 1.839205F);
/*  78 */     this.pestle.field_78809_i = true;
/*  79 */     this.pestle.func_78786_a("upper", -1.0F, -7.0F, 0.0F, 1, 12, 1);
/*  80 */     this.pestle.func_78786_a("lower", -2.0F, 5.0F, -1.0F, 3, 12, 3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  86 */     GL11.glTranslatef(0.0F, -0.2F, 0.0F);
/*  87 */     super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
/*  88 */     this.mortar.func_78785_a(par7);
/*  89 */     this.pestle.func_78785_a(par7);
/*     */     
/*  91 */     this.bipedCloak.func_78785_a(par7);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  95 */     model.field_78795_f = x;
/*  96 */     model.field_78796_g = y;
/*  97 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 102 */     this.field_78191_a.field_78796_g = (par4 / 57.295776F);
/* 103 */     this.field_78191_a.field_78795_f = (par5 / 57.295776F);
/* 104 */     this.field_78190_c.field_78797_d = 3.0F;
/* 105 */     this.field_78190_c.field_78798_e = -1.0F;
/* 106 */     this.field_78190_c.field_78795_f = -0.75F;
/*     */     
/* 108 */     this.bipedCloak.func_78793_a(-3.5F, -0.5F, 3.5F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 116 */     this.field_82898_f.field_82906_o = (this.field_82898_f.field_82908_p = this.field_82898_f.field_82907_q = 0.0F);
/* 117 */     float f6 = 0.01F * (par7Entity.func_145782_y() % 10);
/* 118 */     this.field_82898_f.field_78795_f = (MathHelper.func_76126_a(par7Entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 119 */     this.field_82898_f.field_78796_g = 0.0F;
/* 120 */     this.field_82898_f.field_78808_h = (MathHelper.func_76134_b(par7Entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */     
/* 122 */     if (this.field_82900_g) {
/* 123 */       this.field_82898_f.field_78795_f = -0.9F;
/* 124 */       this.field_82898_f.field_82907_q = -0.09375F;
/* 125 */       this.field_82898_f.field_82908_p = 0.1875F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 130 */     this.pestle.field_78795_f = (-1.152537F + MathHelper.func_76126_a(par7Entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 131 */     this.pestle.field_78796_g = -2.305074F;
/* 132 */     this.pestle.field_78808_h = (1.839205F + MathHelper.func_76134_b(par7Entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelBabaYaga.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */