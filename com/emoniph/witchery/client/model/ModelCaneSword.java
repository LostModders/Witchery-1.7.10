/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelCaneSword extends ModelBase
/*     */ {
/*     */   private ModelRenderer sheath;
/*     */   private ModelRenderer ball;
/*     */   private ModelRenderer blade1;
/*     */   private ModelRenderer blade2;
/*     */   private ModelRenderer blade3;
/*     */   
/*     */   public ModelCaneSword()
/*     */   {
/*  21 */     this.field_78090_t = 32;
/*  22 */     this.field_78089_u = 32;
/*  23 */     this.blade3 = new ModelRenderer(this, 24, 0);
/*  24 */     this.blade3.func_78793_a(-5.8F, 11.0F, -1.6F);
/*  25 */     this.blade3.func_78790_a(-1.0F, -17.0F, -1.0F, 2, 2, 2, 0.0F);
/*  26 */     this.blade2 = new ModelRenderer(this, 24, 5);
/*  27 */     this.blade2.func_78793_a(-5.8F, 11.0F, -1.6F);
/*  28 */     this.blade2.func_78790_a(-1.0F, -15.0F, -1.0F, 2, 4, 2, 0.0F);
/*  29 */     this.ball = new ModelRenderer(this, 0, 0);
/*  30 */     this.ball.func_78793_a(-5.8F, 10.0F, -1.6F);
/*  31 */     this.ball.func_78790_a(-1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F);
/*  32 */     this.blade1 = new ModelRenderer(this, 24, 12);
/*  33 */     this.blade1.func_78793_a(-5.8F, 8.0F, -1.6F);
/*  34 */     this.blade1.func_78790_a(-1.0F, -8.0F, -1.0F, 2, 9, 2, 0.0F);
/*  35 */     this.sheath = new ModelRenderer(this, 0, 6);
/*  36 */     this.sheath.func_78793_a(-5.8F, 11.0F, -1.6F);
/*  37 */     this.sheath.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 13, 2, 0.0F);
/*     */   }
/*     */   
/*     */   private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/*  41 */     modelRenderer.field_78795_f = x;
/*  42 */     modelRenderer.field_78796_g = y;
/*  43 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean firstPerson, boolean deployed) {
/*  47 */     if (deployed) {
/*  48 */       GL11.glPushMatrix();
/*  49 */       GL11.glTranslatef(this.blade3.field_82906_o, this.blade3.field_82908_p, this.blade3.field_82907_q);
/*  50 */       GL11.glTranslatef(this.blade3.field_78800_c * f5, this.blade3.field_78797_d * f5, this.blade3.field_78798_e * f5);
/*     */       
/*  52 */       GL11.glTranslatef(0.0F, 3.15F, 0.0F);
/*  53 */       GL11.glScaled(0.2D, 4.3D, 0.2D);
/*  54 */       GL11.glTranslatef(-this.blade3.field_82906_o, -this.blade3.field_82908_p, -this.blade3.field_82907_q);
/*  55 */       GL11.glTranslatef(-this.blade3.field_78800_c * f5, -this.blade3.field_78797_d * f5, -this.blade3.field_78798_e * f5);
/*     */       
/*  57 */       this.blade3.func_78785_a(f5);
/*  58 */       GL11.glPopMatrix();
/*  59 */       GL11.glPushMatrix();
/*  60 */       GL11.glTranslatef(this.blade2.field_82906_o, this.blade2.field_82908_p, this.blade2.field_82907_q);
/*  61 */       GL11.glTranslatef(this.blade2.field_78800_c * f5, this.blade2.field_78797_d * f5, this.blade2.field_78798_e * f5);
/*     */       
/*  63 */       GL11.glTranslatef(0.0F, 2.1F, 0.0F);
/*  64 */       GL11.glScaled(0.15D, 3.7D, 0.7D);
/*  65 */       GL11.glTranslatef(-this.blade2.field_82906_o, -this.blade2.field_82908_p, -this.blade2.field_82907_q);
/*  66 */       GL11.glTranslatef(-this.blade2.field_78800_c * f5, -this.blade2.field_78797_d * f5, -this.blade2.field_78798_e * f5);
/*     */       
/*  68 */       this.blade2.func_78785_a(f5);
/*  69 */       GL11.glPopMatrix();
/*  70 */       GL11.glPushMatrix();
/*  71 */       GL11.glTranslatef(this.blade1.field_82906_o, this.blade1.field_82908_p, this.blade1.field_82907_q);
/*  72 */       GL11.glTranslatef(this.blade1.field_78800_c * f5, this.blade1.field_78797_d * f5, this.blade1.field_78798_e * f5);
/*     */       
/*  74 */       GL11.glScaled(0.21D, 1.4D, 0.5D);
/*  75 */       GL11.glTranslatef(-this.blade1.field_82906_o, -this.blade1.field_82908_p, -this.blade1.field_82907_q);
/*  76 */       GL11.glTranslatef(-this.blade1.field_78800_c * f5, -this.blade1.field_78797_d * f5, -this.blade1.field_78798_e * f5);
/*     */       
/*  78 */       this.blade1.func_78785_a(f5);
/*  79 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  82 */     GL11.glPushMatrix();
/*  83 */     GL11.glTranslatef(this.ball.field_82906_o, this.ball.field_82908_p, this.ball.field_82907_q);
/*  84 */     GL11.glTranslatef(this.ball.field_78800_c * f5, this.ball.field_78797_d * f5, this.ball.field_78798_e * f5);
/*     */     
/*  86 */     GL11.glScaled(0.8D, 1.1D, 0.8D);
/*  87 */     GL11.glTranslatef(-this.ball.field_82906_o, -this.ball.field_82908_p, -this.ball.field_82907_q);
/*  88 */     GL11.glTranslatef(-this.ball.field_78800_c * f5, -this.ball.field_78797_d * f5, -this.ball.field_78798_e * f5);
/*     */     
/*  90 */     this.ball.func_78785_a(f5);
/*  91 */     GL11.glPopMatrix();
/*     */     
/*  93 */     if (!deployed)
/*     */     {
/*  95 */       GL11.glPushMatrix();
/*  96 */       GL11.glTranslatef(this.sheath.field_82906_o, this.sheath.field_82908_p, this.sheath.field_82907_q);
/*  97 */       GL11.glTranslatef(this.sheath.field_78800_c * f5, this.sheath.field_78797_d * f5, this.sheath.field_78798_e * f5);
/*     */       
/*  99 */       GL11.glScaled(0.8D, 1.0D, 0.8D);
/* 100 */       GL11.glTranslatef(-this.sheath.field_82906_o, -this.sheath.field_82908_p, -this.sheath.field_82907_q);
/* 101 */       GL11.glTranslatef(-this.sheath.field_78800_c * f5, -this.sheath.field_78797_d * f5, -this.sheath.field_78798_e * f5);
/*     */       
/* 103 */       this.sheath.func_78785_a(f5);
/* 104 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelCaneSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */