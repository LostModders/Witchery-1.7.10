/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelLordOfTorment
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer rightarm1;
/*     */   ModelRenderer rightarm2;
/*     */   ModelRenderer leftarm1;
/*     */   ModelRenderer leftarm2;
/*     */   ModelRenderer rightleg;
/*     */   ModelRenderer leftleg;
/*     */   ModelRenderer wingsLeft;
/*     */   ModelRenderer wingsRight;
/*     */   ModelRenderer hornLeft;
/*     */   ModelRenderer hornRight;
/*     */   
/*     */   public ModelLordOfTorment()
/*     */   {
/*  30 */     this.field_78090_t = 128;
/*  31 */     this.field_78089_u = 128;
/*     */     
/*  33 */     func_78085_a("head.skull", 0, 0);
/*  34 */     func_78085_a("head.beard1", 34, 0);
/*  35 */     func_78085_a("head.beard2", 34, 0);
/*  36 */     func_78085_a("head.beard3", 34, 0);
/*  37 */     func_78085_a("head.beard4", 34, 0);
/*  38 */     func_78085_a("head.beard5", 34, 0);
/*  39 */     func_78085_a("head.beard6", 34, 0);
/*     */     
/*  41 */     func_78085_a("head.nose", 40, 0);
/*  42 */     func_78085_a("head.nose2", 40, 6);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  47 */     this.head = new ModelRenderer(this, "head");
/*  48 */     this.head.func_78793_a(0.0F, -6.0F, 0.0F);
/*  49 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  50 */     this.head.field_78809_i = true;
/*  51 */     this.head.func_78786_a("skull", -4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  52 */     this.head.func_78786_a("beard1", -3.0F, 0.0F, -4.0F, 1, 7, 1);
/*  53 */     this.head.func_78786_a("beard2", -2.0F, 0.0F, -5.0F, 1, 5, 1);
/*  54 */     this.head.func_78786_a("beard3", -1.0F, 0.0F, -4.0F, 1, 9, 1);
/*  55 */     this.head.func_78786_a("beard4", 0.0F, 0.0F, -5.0F, 1, 6, 1);
/*  56 */     this.head.func_78786_a("beard5", 1.0F, 0.0F, -4.0F, 1, 4, 1);
/*  57 */     this.head.func_78786_a("beard6", 2.0F, 0.0F, -5.0F, 1, 8, 1);
/*  58 */     this.head.func_78786_a("nose", -3.0F, -4.0F, -5.0F, 6, 4, 1);
/*  59 */     this.head.func_78786_a("nose2", -2.0F, -6.0F, -5.0F, 4, 2, 1);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  68 */     this.hornRight = new ModelRenderer(this, 55, 0);
/*  69 */     this.hornRight.func_78789_a(-2.0F, -15.0F, 0.0F, 1, 9, 1);
/*  70 */     this.hornRight.func_78793_a(0.0F, -6.0F, 0.0F);
/*  71 */     this.hornRight.func_78787_b(128, 128);
/*  72 */     this.hornRight.field_78809_i = true;
/*  73 */     setRotation(this.hornRight, 0.5948578F, 0.0F, -0.1858931F);
/*  74 */     this.head.func_78792_a(this.hornRight);
/*     */     
/*  76 */     this.hornLeft = new ModelRenderer(this, 55, 0);
/*  77 */     this.hornLeft.func_78789_a(1.0F, -15.0F, 0.0F, 1, 9, 1);
/*  78 */     this.hornLeft.func_78793_a(0.0F, -6.0F, 0.0F);
/*  79 */     this.hornLeft.func_78787_b(128, 128);
/*  80 */     this.hornLeft.field_78809_i = true;
/*  81 */     setRotation(this.hornLeft, 0.5948578F, 0.0F, 0.1858931F);
/*  82 */     this.head.func_78792_a(this.hornLeft);
/*     */     
/*  84 */     this.body = new ModelRenderer(this, 16, 16);
/*  85 */     this.body.func_78789_a(-4.0F, -6.0F, -2.0F, 8, 14, 4);
/*  86 */     this.body.func_78793_a(0.0F, 0.0F, 0.0F);
/*  87 */     this.body.func_78787_b(128, 128);
/*  88 */     this.body.field_78809_i = true;
/*  89 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  90 */     this.rightarm1 = new ModelRenderer(this, 40, 16);
/*  91 */     this.rightarm1.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 20, 4);
/*  92 */     this.rightarm1.func_78793_a(-5.0F, -4.0F, 0.0F);
/*  93 */     this.rightarm1.func_78787_b(128, 128);
/*  94 */     this.rightarm1.field_78809_i = true;
/*  95 */     setRotation(this.rightarm1, 0.0F, 0.0F, 0.0F);
/*  96 */     this.rightarm2 = new ModelRenderer(this, 40, 16);
/*  97 */     this.rightarm2.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 20, 4);
/*  98 */     this.rightarm2.func_78793_a(-5.0F, -4.0F, 0.0F);
/*  99 */     this.rightarm2.func_78787_b(128, 128);
/* 100 */     this.rightarm2.field_78809_i = true;
/* 101 */     setRotation(this.rightarm2, 0.0F, 0.0F, 0.0F);
/* 102 */     this.leftarm1 = new ModelRenderer(this, 40, 16);
/* 103 */     this.leftarm1.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 20, 4);
/* 104 */     this.leftarm1.func_78793_a(5.0F, -4.0F, 0.0F);
/* 105 */     this.leftarm1.func_78787_b(128, 128);
/* 106 */     this.leftarm1.field_78809_i = true;
/* 107 */     setRotation(this.leftarm1, 0.0F, 0.0F, 0.0F);
/* 108 */     this.leftarm2 = new ModelRenderer(this, 40, 16);
/* 109 */     this.leftarm2.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 20, 4);
/* 110 */     this.leftarm2.func_78793_a(5.0F, -4.0F, 0.0F);
/* 111 */     this.leftarm2.func_78787_b(128, 128);
/* 112 */     this.leftarm2.field_78809_i = true;
/* 113 */     setRotation(this.leftarm2, 0.0F, 0.0F, 0.0F);
/* 114 */     this.rightleg = new ModelRenderer(this, 0, 16);
/* 115 */     this.rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 15, 4);
/* 116 */     this.rightleg.func_78793_a(-2.0F, 8.0F, 0.0F);
/* 117 */     this.rightleg.func_78787_b(128, 128);
/* 118 */     this.rightleg.field_78809_i = true;
/* 119 */     setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
/* 120 */     this.leftleg = new ModelRenderer(this, 0, 16);
/* 121 */     this.leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 15, 4);
/* 122 */     this.leftleg.func_78793_a(2.0F, 8.0F, 0.0F);
/* 123 */     this.leftleg.func_78787_b(128, 128);
/* 124 */     this.leftleg.field_78809_i = true;
/* 125 */     setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
/*     */     
/* 127 */     this.wingsLeft = new ModelRenderer(this, 0, 42);
/* 128 */     this.wingsLeft.func_78789_a(-20.0F, -20.0F, 0.0F, 20, 40, 0);
/* 129 */     this.wingsLeft.func_78793_a(0.0F, 1.0F, 5.0F);
/* 130 */     this.wingsLeft.func_78787_b(128, 128);
/* 131 */     this.wingsLeft.field_78809_i = true;
/* 132 */     setRotation(this.wingsLeft, 0.0F, 0.0F, 0.0F);
/*     */     
/* 134 */     this.wingsRight = new ModelRenderer(this, 0, 82);
/* 135 */     this.wingsRight.func_78789_a(0.0F, -20.0F, 0.0F, 20, 40, 0);
/* 136 */     this.wingsRight.func_78793_a(0.0F, 1.0F, 5.0F);
/* 137 */     this.wingsRight.func_78787_b(128, 128);
/* 138 */     this.wingsRight.field_78809_i = true;
/* 139 */     setRotation(this.wingsRight, 0.0F, 0.0F, 0.0F);
/*     */     
/* 141 */     this.hornRight.func_78793_a(0.0F, 0.0F, 0.0F);
/* 142 */     this.hornLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 146 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 147 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/*     */ 
/*     */ 
/* 151 */     this.head.func_78785_a(f5);
/* 152 */     this.body.func_78785_a(f5);
/* 153 */     this.rightarm1.func_78785_a(f5);
/* 154 */     this.rightarm2.func_78785_a(f5);
/* 155 */     this.leftarm1.func_78785_a(f5);
/* 156 */     this.leftarm2.func_78785_a(f5);
/* 157 */     this.rightleg.func_78785_a(f5);
/* 158 */     this.leftleg.func_78785_a(f5);
/* 159 */     this.wingsLeft.func_78785_a(f5);
/* 160 */     this.wingsRight.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 164 */     model.field_78795_f = x;
/* 165 */     model.field_78796_g = y;
/* 166 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 171 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 172 */     this.head.field_78795_f = (par5 / 57.295776F);
/*     */     
/* 174 */     this.rightarm1.field_78795_f = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.5F);
/* 175 */     this.rightarm2.field_78795_f = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.25F);
/*     */     
/* 177 */     this.leftarm1.field_78795_f = (MathHelper.func_76134_b(0.6662F) * 2.0F * par2 * 0.5F);
/* 178 */     this.leftarm2.field_78795_f = (MathHelper.func_76134_b(0.6662F) * 2.0F * par2 * 0.25F);
/*     */     
/* 180 */     boolean inMotion = (entity.field_70159_w > 0.0D) || (entity.field_70179_y > 0.0D);
/* 181 */     if (inMotion) {
/* 182 */       this.wingsLeft.field_78796_g = 0.4F;
/* 183 */       this.wingsRight.field_78796_g = -0.4F;
/*     */     } else {
/* 185 */       this.wingsLeft.field_78796_g = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.5F + MathHelper.func_76134_b(par3 * 0.09F) * 0.3F);
/* 186 */       this.wingsRight.field_78796_g = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.5F - MathHelper.func_76134_b(par3 * 0.09F) * 0.3F);
/*     */     }
/*     */     
/* 189 */     this.rightarm1.field_78796_g = 0.0F;
/* 190 */     this.rightarm2.field_78796_g = 0.0F;
/*     */     
/* 192 */     this.leftarm1.field_78796_g = 0.0F;
/* 193 */     this.leftarm2.field_78796_g = 0.0F;
/*     */     
/* 195 */     this.rightarm1.field_78808_h = 0.0F;
/* 196 */     this.rightarm2.field_78808_h = 0.0F;
/*     */     
/* 198 */     this.leftarm1.field_78808_h = 0.0F;
/* 199 */     this.leftarm2.field_78808_h = 0.0F;
/*     */     
/* 201 */     this.rightarm1.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 202 */     this.rightarm2.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/*     */     
/* 204 */     this.leftarm1.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 205 */     this.leftarm2.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 206 */     this.rightarm1.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 207 */     this.rightarm2.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 208 */     this.leftarm1.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 209 */     this.leftarm2.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     
/*     */ 
/* 212 */     EntityLordOfTorment entityDemon = (EntityLordOfTorment)entity;
/* 213 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 215 */     if (i > 0) {
/* 216 */       this.rightarm1.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/* 217 */       this.leftarm1.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/*     */       
/* 219 */       this.rightarm1.field_78808_h = (-(-1.5F + 1.5F * func_78172_a(i - par4, 15.0F)));
/* 220 */       this.leftarm1.field_78808_h = (-1.5F + 1.5F * func_78172_a(i - par4, 15.0F));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 225 */       this.rightarm2.field_78808_h = (-(-1.0F + 1.5F * func_78172_a(i - par4, 15.0F)));
/* 226 */       this.leftarm2.field_78808_h = (-1.0F + 1.5F * func_78172_a(i - par4, 15.0F));
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private float func_78172_a(float par1, float par2)
/*     */   {
/* 246 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelLordOfTorment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */