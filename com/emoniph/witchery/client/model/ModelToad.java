/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityToad;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelToad
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer armRight;
/*     */   ModelRenderer armLeft;
/*     */   ModelRenderer legRight;
/*     */   ModelRenderer legLeft;
/*     */   
/*     */   public ModelToad()
/*     */   {
/*  27 */     this.field_78090_t = 32;
/*  28 */     this.field_78089_u = 32;
/*  29 */     func_78085_a("head.nose", 0, 5);
/*  30 */     func_78085_a("head.eyeRight", 0, 0);
/*  31 */     func_78085_a("head.eyeLeft", 8, 0);
/*  32 */     func_78085_a("legRight.thighRight", 0, 20);
/*  33 */     func_78085_a("legRight.footRight", 0, 26);
/*  34 */     func_78085_a("legLeft.thighLeft", 11, 20);
/*  35 */     func_78085_a("legLeft.footLeft", 0, 26);
/*     */     
/*  37 */     this.head = new ModelRenderer(this, "head");
/*  38 */     this.head.func_78793_a(0.0F, 20.0F, -1.0F);
/*  39 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  40 */     this.head.field_78809_i = true;
/*  41 */     this.head.func_78786_a("nose", -2.0F, -1.0F, -4.0F, 4, 2, 4);
/*  42 */     this.head.func_78786_a("eyeRight", -2.5F, -3.0F, -3.0F, 2, 2, 2);
/*  43 */     this.head.func_78786_a("eyeLeft", 0.5F, -3.0F, -3.0F, 2, 2, 2);
/*  44 */     this.body = new ModelRenderer(this, 0, 12);
/*  45 */     this.body.func_78789_a(-2.0F, -1.0F, 0.0F, 4, 2, 5);
/*  46 */     this.body.func_78793_a(0.0F, 20.0F, -1.0F);
/*  47 */     this.body.func_78787_b(32, 32);
/*  48 */     this.body.field_78809_i = true;
/*  49 */     setRotation(this.body, -0.4833219F, 0.0F, 0.0F);
/*  50 */     this.armRight = new ModelRenderer(this, 13, 26);
/*  51 */     this.armRight.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 4, 1);
/*  52 */     this.armRight.func_78793_a(-2.0F, 20.0F, -1.0F);
/*  53 */     this.armRight.func_78787_b(32, 32);
/*  54 */     this.armRight.field_78809_i = true;
/*  55 */     setRotation(this.armRight, -0.3346075F, 0.0F, 0.0F);
/*  56 */     this.armLeft = new ModelRenderer(this, 18, 26);
/*  57 */     this.armLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 4, 1);
/*  58 */     this.armLeft.func_78793_a(2.0F, 20.0F, -1.0F);
/*  59 */     this.armLeft.func_78787_b(32, 32);
/*  60 */     this.armLeft.field_78809_i = true;
/*  61 */     setRotation(this.armLeft, -0.3346075F, 0.0F, 0.0F);
/*  62 */     this.legRight = new ModelRenderer(this, "legRight");
/*  63 */     this.legRight.func_78793_a(-2.0F, 23.0F, 3.0F);
/*  64 */     setRotation(this.legRight, 0.0F, 0.0F, 0.0F);
/*  65 */     this.legRight.field_78809_i = true;
/*  66 */     this.legRight.func_78786_a("thighRight", -2.0F, -1.0F, -2.0F, 2, 2, 3);
/*  67 */     this.legRight.func_78786_a("footRight", -3.0F, 1.0F, -4.0F, 3, 0, 3);
/*  68 */     this.legLeft = new ModelRenderer(this, "legLeft");
/*  69 */     this.legLeft.func_78793_a(2.0F, 23.0F, 3.0F);
/*  70 */     setRotation(this.legLeft, 0.0F, 0.0F, 0.0F);
/*  71 */     this.legLeft.field_78809_i = true;
/*  72 */     this.legLeft.func_78786_a("thighLeft", 0.0F, -1.0F, -2.0F, 2, 2, 3);
/*  73 */     this.legLeft.func_78786_a("footLeft", 0.0F, 1.0F, -4.0F, 3, 0, 3);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  78 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  79 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/*     */ 
/*     */ 
/*  83 */     this.head.field_78796_g = (f3 / 57.295776F);
/*  84 */     this.head.field_78795_f = (f4 / 57.295776F);
/*     */     
/*  86 */     if (this.field_78091_s) {
/*  87 */       float p6 = 2.0F;
/*  88 */       GL11.glPushMatrix();
/*  89 */       GL11.glScalef(1.5F / p6, 1.5F / p6, 1.5F / p6);
/*  90 */       GL11.glTranslatef(0.0F, 10.0F * f5, 0.0F);
/*  91 */       this.head.func_78785_a(f5);
/*  92 */       GL11.glPopMatrix();
/*  93 */       GL11.glPushMatrix();
/*  94 */       GL11.glScalef(1.0F / p6, 1.0F / p6, 1.0F / p6);
/*  95 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/*  96 */       this.body.func_78785_a(f5);
/*  97 */       this.armRight.func_78785_a(f5);
/*  98 */       this.armLeft.func_78785_a(f5);
/*  99 */       this.legRight.func_78785_a(f5);
/* 100 */       this.legLeft.func_78785_a(f5);
/* 101 */       GL11.glPopMatrix();
/*     */     } else {
/* 103 */       this.head.func_78785_a(f5);
/* 104 */       this.body.func_78785_a(f5);
/* 105 */       this.armRight.func_78785_a(f5);
/* 106 */       this.armLeft.func_78785_a(f5);
/* 107 */       this.legRight.func_78785_a(f5);
/* 108 */       this.legLeft.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 113 */     model.field_78795_f = x;
/* 114 */     model.field_78796_g = y;
/* 115 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 120 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
/*     */   {
/* 126 */     EntityToad toad = (EntityToad)par1EntityLivingBase;
/*     */     
/* 128 */     if (toad.func_70906_o())
/*     */     {
/* 130 */       this.legLeft.field_78795_f = -0.3926991F;
/* 131 */       this.legRight.field_78795_f = this.legLeft.field_78795_f;
/*     */     }
/*     */     else
/*     */     {
/* 135 */       this.legLeft.field_78795_f = (MathHelper.func_76134_b(par2 * 0.6662F) * 1.4F * par3 + ((par3 > 0.1D) || (par3 < -0.1D) ? 0.5F : 0.0F));
/* 136 */       this.legRight.field_78795_f = this.legLeft.field_78795_f;
/* 137 */       this.armLeft.field_78795_f = (MathHelper.func_76134_b(par2 * 0.6662F + 3.1415927F) * 1.4F * par3);
/* 138 */       this.armRight.field_78795_f = (MathHelper.func_76134_b(par2 * 0.6662F) * 1.4F * par3);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelToad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */