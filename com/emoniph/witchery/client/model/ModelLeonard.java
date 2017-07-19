/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityLeonard;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelLeonard extends ModelBase
/*     */ {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer beard;
/*     */   private final ModelRenderer earLeft;
/*     */   private final ModelRenderer earRight;
/*     */   private final ModelRenderer hornLeft;
/*     */   private final ModelRenderer hornMiddle;
/*     */   private final ModelRenderer hornRight;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer gownLowerRight;
/*     */   private final ModelRenderer rightarm;
/*     */   private final ModelRenderer leftarm;
/*     */   private final ModelRenderer rightleg;
/*     */   private final ModelRenderer leftleg;
/*     */   private final ModelRenderer gownLowerLeft;
/*     */   
/*     */   public ModelLeonard()
/*     */   {
/*  33 */     this.field_78090_t = 64;
/*  34 */     this.field_78089_u = 64;
/*     */     
/*  36 */     this.neck = new ModelRenderer(this, 48, 0);
/*  37 */     this.neck.func_78789_a(-2.0F, -1.0F, -2.0F, 4, 2, 4);
/*  38 */     this.neck.func_78793_a(0.0F, 0.0F, 0.0F);
/*  39 */     this.neck.func_78787_b(64, 64);
/*  40 */     this.neck.field_78809_i = true;
/*  41 */     setRotation(this.neck, 0.1745329F, 0.0F, 0.0F);
/*     */     
/*  43 */     this.head = new ModelRenderer(this, 0, 0);
/*  44 */     this.head.func_78789_a(-3.0F, -5.0F, -1.0F, 6, 4, 4);
/*  45 */     this.head.func_78793_a(0.0F, 0.0F, 0.0F);
/*  46 */     this.head.func_78787_b(64, 64);
/*  47 */     this.head.field_78809_i = true;
/*  48 */     this.neck.func_78792_a(this.head);
/*  49 */     setRotation(this.head, 0.1745329F, 0.0F, 0.0F);
/*  50 */     this.snout = new ModelRenderer(this, 16, 2);
/*  51 */     this.snout.func_78789_a(-2.0F, -5.0F, -7.0F, 4, 4, 7);
/*  52 */     this.snout.func_78793_a(0.0F, 0.0F, 0.0F);
/*  53 */     this.snout.func_78787_b(64, 64);
/*  54 */     this.snout.field_78809_i = true;
/*  55 */     setRotation(this.snout, 0.1745329F, 0.0F, 0.0F);
/*  56 */     this.head.func_78792_a(this.snout);
/*  57 */     this.beard = new ModelRenderer(this, 0, 10);
/*  58 */     this.beard.func_78789_a(-2.0F, -0.2F, -7.0F, 4, 2, 2);
/*  59 */     this.beard.func_78793_a(0.0F, 0.0F, 0.0F);
/*  60 */     this.beard.func_78787_b(64, 64);
/*  61 */     this.beard.field_78809_i = true;
/*  62 */     setRotation(this.beard, -0.0113601F, 0.0F, 0.0F);
/*  63 */     this.head.func_78792_a(this.beard);
/*  64 */     this.earLeft = new ModelRenderer(this, 38, 0);
/*  65 */     this.earLeft.func_78789_a(3.5F, 1.0F, -0.5F, 1, 3, 1);
/*  66 */     this.earLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  67 */     this.earLeft.func_78787_b(64, 64);
/*  68 */     this.earLeft.field_78809_i = true;
/*  69 */     setRotation(this.earLeft, -0.5129616F, -0.2617994F, -1.180008F);
/*  70 */     this.head.func_78792_a(this.earLeft);
/*  71 */     this.earRight = new ModelRenderer(this, 38, 0);
/*  72 */     this.earRight.func_78789_a(-4.5F, 1.0F, 0.5F, 1, 3, 1);
/*  73 */     this.earRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  74 */     this.earRight.func_78787_b(64, 64);
/*  75 */     this.earRight.field_78809_i = true;
/*  76 */     setRotation(this.earRight, -0.3346075F, 0.0371786F, 1.226894F);
/*  77 */     this.head.func_78792_a(this.earRight);
/*  78 */     this.hornLeft = new ModelRenderer(this, 43, 0);
/*  79 */     this.hornLeft.func_78789_a(-0.5F, -12.0F, -0.5F, 1, 8, 1);
/*  80 */     this.hornLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  81 */     this.hornLeft.func_78787_b(64, 64);
/*  82 */     this.hornLeft.field_78809_i = true;
/*  83 */     setRotation(this.hornLeft, -0.2268928F, 0.0F, 0.3665191F);
/*  84 */     this.head.func_78792_a(this.hornLeft);
/*  85 */     this.hornMiddle = new ModelRenderer(this, 43, 0);
/*  86 */     this.hornMiddle.func_78789_a(-0.5F, -10.0F, -0.5F, 1, 6, 1);
/*  87 */     this.hornMiddle.func_78793_a(0.0F, 0.0F, 0.0F);
/*  88 */     this.hornMiddle.func_78787_b(64, 64);
/*  89 */     this.hornMiddle.field_78809_i = true;
/*  90 */     setRotation(this.hornMiddle, -0.2974289F, 0.0F, 0.0F);
/*  91 */     this.head.func_78792_a(this.hornMiddle);
/*  92 */     this.hornRight = new ModelRenderer(this, 43, 0);
/*  93 */     this.hornRight.func_78789_a(-0.5F, -12.0F, -0.5F, 1, 8, 1);
/*  94 */     this.hornRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  95 */     this.hornRight.func_78787_b(64, 64);
/*  96 */     this.hornRight.field_78809_i = true;
/*  97 */     setRotation(this.hornRight, -0.2268928F, 0.0F, -0.3665191F);
/*  98 */     this.head.func_78792_a(this.hornRight);
/*     */     
/* 100 */     this.body = new ModelRenderer(this, 16, 16);
/* 101 */     this.body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
/* 102 */     this.body.func_78793_a(0.0F, 0.0F, 0.0F);
/* 103 */     this.body.func_78787_b(64, 64);
/* 104 */     this.body.field_78809_i = true;
/* 105 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/* 106 */     this.gownLowerRight = new ModelRenderer(this, 0, 33);
/* 107 */     this.gownLowerRight.func_78789_a(-5.0F, 12.0F, -2.5F, 5, 11, 5);
/* 108 */     this.gownLowerRight.func_78793_a(0.0F, 0.0F, 0.0F);
/* 109 */     this.gownLowerRight.func_78787_b(64, 64);
/* 110 */     this.gownLowerRight.field_78809_i = true;
/* 111 */     setRotation(this.gownLowerRight, 0.0F, 0.0F, 0.0F);
/* 112 */     this.rightarm = new ModelRenderer(this, 40, 16);
/* 113 */     this.rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
/* 114 */     this.rightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
/* 115 */     this.rightarm.func_78787_b(64, 64);
/* 116 */     this.rightarm.field_78809_i = true;
/* 117 */     setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
/* 118 */     this.leftarm = new ModelRenderer(this, 40, 16);
/* 119 */     this.leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
/* 120 */     this.leftarm.func_78793_a(5.0F, 2.0F, 0.0F);
/* 121 */     this.leftarm.func_78787_b(64, 64);
/* 122 */     this.leftarm.field_78809_i = true;
/* 123 */     setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
/* 124 */     this.rightleg = new ModelRenderer(this, 0, 16);
/* 125 */     this.rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/* 126 */     this.rightleg.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 127 */     this.rightleg.func_78787_b(64, 64);
/* 128 */     this.rightleg.field_78809_i = true;
/* 129 */     setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
/* 130 */     this.rightleg.func_78792_a(this.gownLowerRight);
/* 131 */     this.leftleg = new ModelRenderer(this, 0, 16);
/* 132 */     this.leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/* 133 */     this.leftleg.func_78793_a(2.0F, 12.0F, 0.0F);
/* 134 */     this.leftleg.func_78787_b(64, 64);
/* 135 */     this.leftleg.field_78809_i = true;
/* 136 */     setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
/* 137 */     this.gownLowerLeft = new ModelRenderer(this, 21, 33);
/* 138 */     this.gownLowerLeft.func_78789_a(0.0F, 12.0F, -2.5F, 5, 11, 5);
/* 139 */     this.gownLowerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/* 140 */     this.gownLowerLeft.func_78787_b(64, 64);
/* 141 */     this.gownLowerLeft.field_78809_i = true;
/* 142 */     setRotation(this.gownLowerLeft, 0.0F, 0.0F, 0.0F);
/* 143 */     this.leftleg.func_78792_a(this.gownLowerLeft);
/*     */     
/* 145 */     this.neck.field_78795_f = 0.1745329F;
/* 146 */     this.head.field_78795_f = 0.1745329F;
/* 147 */     setRotation(this.earRight, -0.3346075F, 0.0371786F, 1.226894F);
/* 148 */     this.gownLowerLeft.func_78793_a(-2.0F, -12.0F, 0.0F);
/* 149 */     this.gownLowerRight.func_78793_a(2.0F, -12.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 154 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 155 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 156 */     this.neck.func_78785_a(f5);
/* 157 */     this.body.func_78785_a(f5);
/* 158 */     this.rightarm.func_78785_a(f5);
/* 159 */     this.leftarm.func_78785_a(f5);
/* 160 */     this.rightleg.func_78785_a(f5);
/* 161 */     this.leftleg.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 165 */     model.field_78795_f = x;
/* 166 */     model.field_78796_g = y;
/* 167 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 172 */     this.neck.field_78796_g = (par4 / 57.295776F);
/* 173 */     this.neck.field_78795_f = (par5 / 57.295776F);
/* 174 */     this.rightarm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 175 */     this.leftarm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 176 */     this.rightarm.field_78808_h = 0.0F;
/* 177 */     this.leftarm.field_78808_h = 0.0F;
/* 178 */     this.rightleg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 179 */     this.leftleg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 180 */     this.rightleg.field_78796_g = 0.0F;
/* 181 */     this.leftleg.field_78796_g = 0.0F;
/*     */     
/* 183 */     if (this.field_78093_q) {
/* 184 */       this.rightarm.field_78795_f += -0.62831855F;
/* 185 */       this.leftarm.field_78795_f += -0.62831855F;
/* 186 */       this.rightleg.field_78795_f = -1.2566371F;
/* 187 */       this.leftleg.field_78795_f = -1.2566371F;
/* 188 */       this.rightleg.field_78796_g = 0.31415927F;
/* 189 */       this.leftleg.field_78796_g = -0.31415927F;
/*     */     }
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
/* 202 */     this.rightarm.field_78796_g = 0.0F;
/* 203 */     this.leftarm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 207 */     if (this.field_78095_p > -9990.0F) {
/* 208 */       float f6 = this.field_78095_p;
/* 209 */       this.body.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/* 210 */       this.rightarm.field_78798_e = (MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F);
/* 211 */       this.rightarm.field_78800_c = (-MathHelper.func_76134_b(this.body.field_78796_g) * 5.0F);
/* 212 */       this.leftarm.field_78798_e = (-MathHelper.func_76126_a(this.body.field_78796_g) * 5.0F);
/* 213 */       this.leftarm.field_78800_c = (MathHelper.func_76134_b(this.body.field_78796_g) * 5.0F);
/* 214 */       this.rightarm.field_78796_g += this.body.field_78796_g;
/* 215 */       this.leftarm.field_78796_g += this.body.field_78796_g;
/* 216 */       this.leftarm.field_78795_f += this.body.field_78796_g;
/* 217 */       f6 = 1.0F - this.field_78095_p;
/* 218 */       f6 *= f6;
/* 219 */       f6 *= f6;
/* 220 */       f6 = 1.0F - f6;
/* 221 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 222 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.neck.field_78795_f - 0.7F) * 0.75F;
/* 223 */       this.rightarm.field_78795_f = ((float)(this.rightarm.field_78795_f - (f7 * 1.2D + f8)));
/* 224 */       this.rightarm.field_78796_g += this.body.field_78796_g * 2.0F;
/* 225 */       this.rightarm.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 228 */     boolean sneaking = false;
/* 229 */     if (sneaking) {
/* 230 */       this.body.field_78795_f = 0.5F;
/* 231 */       this.rightarm.field_78795_f += 0.4F;
/* 232 */       this.leftarm.field_78795_f += 0.4F;
/* 233 */       this.rightleg.field_78798_e = 4.0F;
/* 234 */       this.leftleg.field_78798_e = 4.0F;
/* 235 */       this.rightleg.field_78797_d = 9.0F;
/* 236 */       this.leftleg.field_78797_d = 9.0F;
/* 237 */       this.neck.field_78797_d = 1.0F;
/*     */     } else {
/* 239 */       this.body.field_78795_f = 0.0F;
/* 240 */       this.rightleg.field_78798_e = 0.1F;
/* 241 */       this.leftleg.field_78798_e = 0.1F;
/* 242 */       this.rightleg.field_78797_d = 12.0F;
/* 243 */       this.leftleg.field_78797_d = 12.0F;
/* 244 */       this.neck.field_78797_d = 0.0F;
/*     */     }
/*     */     
/* 247 */     this.rightarm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 248 */     this.leftarm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 249 */     this.rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 250 */     this.leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     
/* 252 */     boolean shootingBow = false;
/* 253 */     if (shootingBow) {
/* 254 */       float f6 = 0.0F;
/* 255 */       float f7 = 0.0F;
/* 256 */       this.rightarm.field_78808_h = 0.0F;
/* 257 */       this.leftarm.field_78808_h = 0.0F;
/* 258 */       this.rightarm.field_78796_g = (-(0.1F - f6 * 0.6F) + this.neck.field_78796_g);
/* 259 */       this.leftarm.field_78796_g = (0.1F - f6 * 0.6F + this.neck.field_78796_g + 0.4F);
/* 260 */       this.rightarm.field_78795_f = (-1.5707964F + this.neck.field_78795_f);
/* 261 */       this.leftarm.field_78795_f = (-1.5707964F + this.neck.field_78795_f);
/* 262 */       this.rightarm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 263 */       this.leftarm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 264 */       this.rightarm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 265 */       this.leftarm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 266 */       this.rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 267 */       this.leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     }
/*     */     
/* 270 */     EntityLeonard entityDemon = (EntityLeonard)entity;
/* 271 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 273 */     if (i > 0) {
/* 274 */       float di = 10.0F;
/* 275 */       this.rightarm.field_78795_f = (-2.0F + 1.5F * (Math.abs((i - par4) % 10.0F - di * 0.5F) - di * 0.25F) / (di * 0.25F));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelLeonard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */