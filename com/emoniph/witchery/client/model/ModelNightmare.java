/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityNightmare;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelNightmare extends ModelBase
/*     */ {
/*     */   ModelRenderer head;
/*     */   ModelRenderer body;
/*     */   ModelRenderer leftarm;
/*     */   ModelRenderer leg7;
/*     */   ModelRenderer leg6;
/*     */   ModelRenderer leg5;
/*     */   ModelRenderer leg4;
/*     */   ModelRenderer leg3;
/*     */   ModelRenderer leg2;
/*     */   ModelRenderer leg1;
/*     */   ModelRenderer rightfingerlittle;
/*     */   ModelRenderer rightfingerindex;
/*     */   ModelRenderer rightfingerthumb;
/*     */   ModelRenderer rightarm;
/*     */   ModelRenderer leftfingerlittle;
/*     */   ModelRenderer leftfingerindex;
/*     */   ModelRenderer leftfingerthumb;
/*     */   
/*     */   public ModelNightmare()
/*     */   {
/*  35 */     this.field_78090_t = 64;
/*  36 */     this.field_78089_u = 64;
/*     */     
/*  38 */     this.head = new ModelRenderer(this, 0, 0);
/*  39 */     this.head.func_78789_a(-4.0F, -8.0F, -3.0F, 8, 8, 6);
/*  40 */     this.head.func_78793_a(0.0F, -6.0F, 0.0F);
/*  41 */     this.head.func_78787_b(64, 64);
/*  42 */     this.head.field_78809_i = true;
/*  43 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*  44 */     this.body = new ModelRenderer(this, 16, 16);
/*  45 */     this.body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
/*  46 */     this.body.func_78793_a(0.0F, -6.0F, 0.0F);
/*  47 */     this.body.func_78787_b(64, 64);
/*  48 */     this.body.field_78809_i = true;
/*  49 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  50 */     this.leftarm = new ModelRenderer(this, 40, 16);
/*  51 */     this.leftarm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 16, 2);
/*  52 */     this.leftarm.func_78793_a(5.0F, -4.0F, 0.0F);
/*  53 */     this.leftarm.func_78787_b(64, 64);
/*  54 */     this.leftarm.field_78809_i = true;
/*  55 */     setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
/*  56 */     this.leg7 = new ModelRenderer(this, 12, 16);
/*  57 */     this.leg7.func_78789_a(0.0F, 0.0F, 0.0F, 1, 16, 1);
/*  58 */     this.leg7.func_78793_a(-1.0F, 6.0F, 0.0F);
/*  59 */     this.leg7.func_78787_b(64, 64);
/*  60 */     this.leg7.field_78809_i = true;
/*  61 */     setRotation(this.leg7, 0.0F, 0.0F, 0.0F);
/*  62 */     this.leg6 = new ModelRenderer(this, 8, 16);
/*  63 */     this.leg6.func_78789_a(0.0F, 0.0F, 0.0F, 1, 17, 1);
/*  64 */     this.leg6.func_78793_a(1.0F, 6.0F, -1.0F);
/*  65 */     this.leg6.func_78787_b(64, 64);
/*  66 */     this.leg6.field_78809_i = true;
/*  67 */     setRotation(this.leg6, 0.0F, 0.0F, 0.0F);
/*  68 */     this.leg5 = new ModelRenderer(this, 4, 16);
/*  69 */     this.leg5.func_78789_a(0.0F, 0.0F, 0.0F, 1, 12, 1);
/*  70 */     this.leg5.func_78793_a(-3.0F, 6.0F, 0.0F);
/*  71 */     this.leg5.func_78787_b(64, 64);
/*  72 */     this.leg5.field_78809_i = true;
/*  73 */     setRotation(this.leg5, 0.0F, 0.0F, 0.0F);
/*  74 */     this.leg4 = new ModelRenderer(this, 8, 16);
/*  75 */     this.leg4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 14, 1);
/*  76 */     this.leg4.func_78793_a(-2.0F, 6.0F, -1.0F);
/*  77 */     this.leg4.func_78787_b(64, 64);
/*  78 */     this.leg4.field_78809_i = true;
/*  79 */     setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
/*  80 */     this.leg3 = new ModelRenderer(this, 12, 16);
/*  81 */     this.leg3.func_78789_a(0.0F, 0.0F, 0.0F, 1, 11, 1);
/*  82 */     this.leg3.func_78793_a(0.0F, 6.0F, -1.0F);
/*  83 */     this.leg3.func_78787_b(64, 64);
/*  84 */     this.leg3.field_78809_i = true;
/*  85 */     setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
/*  86 */     this.leg2 = new ModelRenderer(this, 0, 16);
/*  87 */     this.leg2.func_78789_a(0.0F, 0.0F, 0.0F, 1, 14, 1);
/*  88 */     this.leg2.func_78793_a(2.0F, 6.0F, 0.0F);
/*  89 */     this.leg2.func_78787_b(64, 64);
/*  90 */     this.leg2.field_78809_i = true;
/*  91 */     setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
/*  92 */     this.leg1 = new ModelRenderer(this, 0, 16);
/*  93 */     this.leg1.func_78789_a(0.0F, 0.0F, 0.0F, 1, 3, 1);
/*  94 */     this.leg1.func_78793_a(-3.0F, 6.0F, -1.0F);
/*  95 */     this.leg1.func_78787_b(64, 64);
/*  96 */     this.leg1.field_78809_i = true;
/*  97 */     setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
/*  98 */     this.rightfingerlittle = new ModelRenderer(this, 0, 46);
/*  99 */     this.rightfingerlittle.func_78789_a(-1.0F, 0.0F, -1.0F, 1, 6, 1);
/* 100 */     this.rightfingerlittle.func_78793_a(-5.0F, 8.0F, 0.0F);
/* 101 */     this.rightfingerlittle.func_78787_b(64, 64);
/* 102 */     this.rightfingerlittle.field_78809_i = true;
/* 103 */     setRotation(this.rightfingerlittle, -0.148353F, 0.0F, 0.1134464F);
/* 104 */     this.rightfingerindex = new ModelRenderer(this, 4, 46);
/* 105 */     this.rightfingerindex.func_78789_a(0.0F, 0.0F, -1.0F, 1, 6, 1);
/* 106 */     this.rightfingerindex.func_78793_a(-5.0F, 8.0F, 0.0F);
/* 107 */     this.rightfingerindex.func_78787_b(64, 64);
/* 108 */     this.rightfingerindex.field_78809_i = true;
/* 109 */     setRotation(this.rightfingerindex, -0.148353F, 0.0F, -0.1134464F);
/* 110 */     this.rightfingerthumb = new ModelRenderer(this, 8, 46);
/* 111 */     this.rightfingerthumb.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 6, 1);
/* 112 */     this.rightfingerthumb.func_78793_a(-5.0F, 8.0F, 0.0F);
/* 113 */     this.rightfingerthumb.func_78787_b(64, 64);
/* 114 */     this.rightfingerthumb.field_78809_i = true;
/* 115 */     setRotation(this.rightfingerthumb, 0.1396263F, 0.0F, 0.0F);
/* 116 */     this.rightarm = new ModelRenderer(this, 40, 16);
/* 117 */     this.rightarm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 16, 2);
/* 118 */     this.rightarm.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 119 */     this.rightarm.func_78787_b(64, 64);
/* 120 */     this.rightarm.field_78809_i = true;
/* 121 */     setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
/* 122 */     this.leftfingerlittle = new ModelRenderer(this, 8, 53);
/* 123 */     this.leftfingerlittle.func_78789_a(0.0F, 0.0F, -1.0F, 1, 6, 1);
/* 124 */     this.leftfingerlittle.func_78793_a(5.0F, 8.0F, 0.0F);
/* 125 */     this.leftfingerlittle.func_78787_b(64, 64);
/* 126 */     this.leftfingerlittle.field_78809_i = true;
/* 127 */     setRotation(this.leftfingerlittle, -0.148353F, 0.0F, -0.1134464F);
/* 128 */     this.leftfingerindex = new ModelRenderer(this, 4, 53);
/* 129 */     this.leftfingerindex.func_78789_a(-1.0F, 0.0F, -1.0F, 1, 6, 1);
/* 130 */     this.leftfingerindex.func_78793_a(5.0F, 8.0F, 0.0F);
/* 131 */     this.leftfingerindex.func_78787_b(64, 64);
/* 132 */     this.leftfingerindex.field_78809_i = true;
/* 133 */     setRotation(this.leftfingerindex, -0.148353F, 0.0F, 0.1134464F);
/* 134 */     this.leftfingerthumb = new ModelRenderer(this, 0, 53);
/* 135 */     this.leftfingerthumb.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 6, 1);
/* 136 */     this.leftfingerthumb.func_78793_a(5.0F, 8.0F, 0.0F);
/* 137 */     this.leftfingerthumb.func_78787_b(64, 64);
/* 138 */     this.leftfingerthumb.field_78809_i = true;
/* 139 */     setRotation(this.leftfingerthumb, 0.1396263F, 0.0F, 0.0F);
/*     */     
/* 141 */     this.leftarm.func_78792_a(this.leftfingerindex);
/* 142 */     this.leftarm.func_78792_a(this.leftfingerlittle);
/* 143 */     this.leftarm.func_78792_a(this.leftfingerthumb);
/*     */     
/* 145 */     this.rightarm.func_78792_a(this.rightfingerindex);
/* 146 */     this.rightarm.func_78792_a(this.rightfingerlittle);
/* 147 */     this.rightarm.func_78792_a(this.rightfingerthumb);
/*     */     
/* 149 */     this.leftfingerlittle.func_78793_a(0.0F, 12.0F, 0.0F);
/* 150 */     this.leftfingerindex.func_78793_a(0.0F, 12.0F, 0.0F);
/* 151 */     this.leftfingerthumb.func_78793_a(0.0F, 12.0F, 0.0F);
/*     */     
/* 153 */     this.rightfingerlittle.func_78793_a(0.0F, 12.0F, 0.0F);
/* 154 */     this.rightfingerindex.func_78793_a(0.0F, 12.0F, 0.0F);
/* 155 */     this.rightfingerthumb.func_78793_a(0.0F, 12.0F, 0.0F);
/*     */     
/* 157 */     this.leg1.field_78800_c = -2.0F;
/* 158 */     this.leg2.field_78800_c = 2.0F;
/* 159 */     this.leg3.field_78800_c = 1.0F;
/* 160 */     this.leg4.field_78800_c = -1.0F;
/* 161 */     this.leg5.field_78800_c = -2.0F;
/* 162 */     this.leg6.field_78800_c = 3.0F;
/* 163 */     this.leg7.field_78800_c = 0.0F;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 168 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 169 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 170 */     this.head.func_78785_a(f5);
/* 171 */     this.body.func_78785_a(f5);
/* 172 */     this.leftarm.func_78785_a(f5);
/* 173 */     this.leg7.func_78785_a(f5);
/* 174 */     this.leg6.func_78785_a(f5);
/* 175 */     this.leg5.func_78785_a(f5);
/* 176 */     this.leg4.func_78785_a(f5);
/* 177 */     this.leg3.func_78785_a(f5);
/* 178 */     this.leg2.func_78785_a(f5);
/* 179 */     this.leg1.func_78785_a(f5);
/* 180 */     this.rightarm.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 184 */     model.field_78795_f = x;
/* 185 */     model.field_78796_g = y;
/* 186 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/* 191 */     EntityNightmare entityDemon = (EntityNightmare)par1EntityLiving;
/* 192 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 194 */     if (i > 0) {
/* 195 */       this.rightarm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/* 196 */       this.leftarm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
/*     */       
/* 198 */       this.rightarm.field_78808_h = (-(-1.5F + 1.5F * func_78172_a(i - par4, 15.0F)));
/* 199 */       this.leftarm.field_78808_h = (-1.5F + 1.5F * func_78172_a(i - par4, 15.0F));
/*     */     } else {
/* 201 */       this.leftarm.field_78795_f = -0.1F;
/* 202 */       this.rightarm.field_78795_f = -0.1F;
/*     */       
/* 204 */       this.leftarm.field_78808_h = 0.0F;
/* 205 */       this.rightarm.field_78808_h = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
/* 210 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
/*     */     
/* 212 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 213 */     this.head.field_78795_f = (par5 / 57.295776F);
/*     */     
/*     */ 
/*     */ 
/* 217 */     float f6 = 0.01F * (entity.func_145782_y() % 10);
/* 218 */     this.leg7.field_78795_f = (-1.8F + MathHelper.func_76126_a(entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 219 */     this.leg7.field_78796_g = -1.7F;
/* 220 */     this.leg7.field_78808_h = (1.839205F + MathHelper.func_76134_b(entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */     
/*     */ 
/* 223 */     f6 = 0.01F * (entity.func_145782_y() % 10);
/* 224 */     this.leg6.field_78795_f = (-1.8F + MathHelper.func_76126_a(entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 225 */     this.leg6.field_78796_g = -1.7F;
/* 226 */     this.leg6.field_78808_h = (1.839205F + MathHelper.func_76134_b(entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */     
/* 228 */     f6 = 0.01F * (entity.func_145782_y() % 12);
/* 229 */     this.leg5.field_78795_f = (-1.8F + MathHelper.func_76126_a(entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 230 */     this.leg5.field_78796_g = -1.7F;
/* 231 */     this.leg5.field_78808_h = (1.839205F + MathHelper.func_76134_b(entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */     
/* 233 */     f6 = 0.01F * (entity.func_145782_y() % 10);
/* 234 */     this.leg4.field_78795_f = (-1.8F + MathHelper.func_76126_a(entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 235 */     this.leg4.field_78796_g = -1.7F;
/* 236 */     this.leg4.field_78808_h = (1.839205F + MathHelper.func_76134_b(entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */     
/* 238 */     f6 = 0.01F * (entity.func_145782_y() % 13);
/* 239 */     this.leg3.field_78795_f = (-1.8F + MathHelper.func_76126_a(entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 240 */     this.leg3.field_78796_g = -1.7F;
/* 241 */     this.leg3.field_78808_h = (1.839205F + MathHelper.func_76134_b(entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */     
/* 243 */     f6 = 0.01F * (entity.func_145782_y() % 12);
/* 244 */     this.leg2.field_78795_f = (-1.8F + MathHelper.func_76126_a(entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 245 */     this.leg2.field_78796_g = -1.7F;
/* 246 */     this.leg2.field_78808_h = (1.839205F + MathHelper.func_76134_b(entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */     
/* 248 */     f6 = 0.01F * (entity.func_145782_y() % 12);
/* 249 */     this.leg1.field_78795_f = (-1.8F + MathHelper.func_76126_a(entity.field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
/* 250 */     this.leg1.field_78796_g = -1.7F;
/* 251 */     this.leg1.field_78808_h = (1.839205F + MathHelper.func_76134_b(entity.field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
/*     */   }
/*     */   
/*     */   private float func_78172_a(float par1, float par2) {
/* 255 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelNightmare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */