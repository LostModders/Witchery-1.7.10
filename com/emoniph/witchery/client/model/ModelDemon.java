/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelDemon
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer rightleg;
/*     */   ModelRenderer leftleg;
/*     */   ModelRenderer wingRight;
/*     */   ModelRenderer wingLeft;
/*     */   
/*     */   public ModelDemon()
/*     */   {
/*  28 */     this.field_78090_t = 128;
/*  29 */     this.field_78089_u = 32;
/*     */     
/*  31 */     func_78085_a("head.face", 0, 0);
/*  32 */     func_78085_a("head.leftHorn", 0, 16);
/*  33 */     func_78085_a("head.rightHorn", 0, 16);
/*  34 */     func_78085_a("head.leftTusk", 4, 16);
/*  35 */     func_78085_a("head.rightTusk", 4, 16);
/*  36 */     func_78085_a("head.snout", 20, 16);
/*  37 */     func_78085_a("head.bottomLip", 8, 16);
/*  38 */     this.head = new ModelRenderer(this, "head");
/*  39 */     this.head.func_78784_a(0, 0);
/*  40 */     this.head.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  41 */     this.head.func_78786_a("leftHorn", 4.0F, -12.0F, -0.5F, 1, 8, 1);
/*  42 */     this.head.func_78786_a("rightHorn", -5.0F, -12.0F, -0.5F, 1, 8, 1);
/*  43 */     this.head.func_78786_a("leftTusk", 1.0F, -4.0F, -5.0F, 1, 2, 1);
/*  44 */     this.head.func_78786_a("bottomLip", -2.0F, -2.0F, -6.0F, 4, 1, 2);
/*  45 */     this.head.func_78786_a("snout", -1.0F, -6.0F, -6.0F, 2, 3, 2);
/*  46 */     this.head.func_78786_a("rightTusk", -2.0F, -4.0F, -5.0F, 1, 2, 1);
/*  47 */     this.head.func_78793_a(0.0F, -9.0F, 0.0F);
/*  48 */     this.head.func_78787_b(128, 32);
/*  49 */     this.head.field_78809_i = true;
/*     */     
/*  51 */     this.body = new ModelRenderer(this, 64, 0);
/*  52 */     this.body.func_78789_a(-4.0F, 0.0F, -3.0F, 8, 14, 6);
/*  53 */     this.body.func_78793_a(0.0F, -9.0F, 0.0F);
/*  54 */     this.body.func_78787_b(128, 32);
/*  55 */     this.body.field_78809_i = true;
/*     */     
/*  57 */     this.rightarm = new ModelRenderer(this, 48, 0);
/*  58 */     this.rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 20, 4);
/*  59 */     this.rightarm.func_78793_a(-5.0F, -7.0F, 0.0F);
/*  60 */     this.rightarm.func_78787_b(128, 32);
/*  61 */     this.rightarm.field_78809_i = true;
/*     */     
/*  63 */     this.leftarm = new ModelRenderer(this, 48, 0);
/*  64 */     this.leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 20, 4);
/*  65 */     this.leftarm.func_78793_a(5.0F, -7.0F, 0.0F);
/*  66 */     this.leftarm.func_78787_b(128, 32);
/*  67 */     this.leftarm.field_78809_i = true;
/*     */     
/*  69 */     this.rightleg = new ModelRenderer(this, 32, 0);
/*  70 */     this.rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 20, 4);
/*  71 */     this.rightleg.func_78793_a(-2.0F, 4.0F, 0.0F);
/*  72 */     this.rightleg.func_78787_b(128, 32);
/*  73 */     this.rightleg.field_78809_i = true;
/*     */     
/*  75 */     this.leftleg = new ModelRenderer(this, 32, 0);
/*  76 */     this.leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 20, 4);
/*  77 */     this.leftleg.func_78793_a(2.0F, 4.0F, 0.0F);
/*  78 */     this.leftleg.func_78787_b(128, 32);
/*  79 */     this.leftleg.field_78809_i = false;
/*     */     
/*     */ 
/*  82 */     this.wingRight = new ModelRenderer(this, 93, 0);
/*  83 */     this.wingRight.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
/*  84 */     this.wingRight.func_78793_a(1.0F, -8.0F, 3.0F);
/*  85 */     this.wingRight.func_78787_b(128, 32);
/*  86 */     this.wingRight.field_78809_i = true;
/*  87 */     setRotation(this.wingRight, 0.3047198F, -0.6698132F, -0.6283185F);
/*     */     
/*  89 */     this.wingLeft = new ModelRenderer(this, 93, 0);
/*  90 */     this.wingLeft.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
/*  91 */     this.wingLeft.func_78793_a(-1.0F, -8.0F, 3.0F);
/*  92 */     this.wingLeft.func_78787_b(128, 32);
/*  93 */     this.wingLeft.field_78809_i = true;
/*     */     
/*  95 */     setRotation(this.wingLeft, -0.3047198F, 3.811406F, 0.6283185F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  99 */     model.field_78795_f = x;
/* 100 */     model.field_78796_g = y;
/* 101 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 109 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 110 */     this.head.func_78785_a(f5);
/* 111 */     this.body.func_78785_a(f5);
/*     */     
/* 113 */     this.rightarm.func_78785_a(f5);
/* 114 */     this.leftarm.func_78785_a(f5);
/* 115 */     this.rightleg.func_78785_a(f5);
/* 116 */     this.leftleg.func_78785_a(f5);
/* 117 */     this.wingLeft.func_78785_a(f5);
/* 118 */     this.wingRight.func_78785_a(f5);
/*     */   }
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
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 132 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 133 */     this.head.field_78795_f = (par5 / 57.295776F);
/* 134 */     this.leftleg.field_78795_f = (-1.5F * func_78172_a(par1, 13.0F) * par2);
/* 135 */     this.rightleg.field_78795_f = (1.5F * func_78172_a(par1, 13.0F) * par2);
/* 136 */     this.leftleg.field_78796_g = 0.0F;
/* 137 */     this.rightleg.field_78796_g = 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/* 147 */     EntityDemon entityDemon = (EntityDemon)par1EntityLiving;
/* 148 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 150 */     if (i > 0) {
/* 151 */       this.rightarm.field_78795_f = (-2.0F + 1.5F * func_78172_a(i - par4, 10.0F));
/*     */     }
/*     */     else
/*     */     {
/* 155 */       this.rightarm.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3);
/* 156 */       this.leftarm.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3);
/*     */     }
/*     */   }
/*     */   
/*     */   private float func_78172_a(float par1, float par2) {
/* 161 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelDemon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */