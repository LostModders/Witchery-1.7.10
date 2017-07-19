/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityGoblinGulg;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelGoblinGulg extends ModelBase
/*     */ {
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public ModelRenderer bipedHead;
/*     */   public ModelRenderer bipedChest;
/*     */   public int heldItemLeft;
/*     */   public int heldItemRight;
/*     */   public boolean isSneak;
/*     */   public boolean aimedBow;
/*     */   
/*     */   public ModelGoblinGulg()
/*     */   {
/*  30 */     this(0.0F);
/*     */   }
/*     */   
/*     */   public ModelGoblinGulg(float f) {
/*  34 */     this.field_78090_t = 64;
/*  35 */     this.field_78089_u = 64;
/*  36 */     func_78085_a("bipedHead.face", 0, 0);
/*  37 */     func_78085_a("bipedHead.tuskright", 0, 4);
/*  38 */     func_78085_a("bipedHead.tuskleft", 0, 4);
/*  39 */     func_78085_a("bipedHead.nose", 25, 0);
/*  40 */     func_78085_a("bipedHead.lip", 34, 0);
/*     */     
/*  42 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  43 */     this.bipedBody.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 4, 4);
/*  44 */     this.bipedBody.func_78793_a(0.0F, 8.0F, 0.0F);
/*  45 */     this.bipedBody.func_78787_b(64, 64);
/*  46 */     this.bipedBody.field_78809_i = true;
/*  47 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  48 */     this.bipedRightArm = new ModelRenderer(this, 40, 14);
/*  49 */     this.bipedRightArm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 16, 4);
/*  50 */     this.bipedRightArm.func_78793_a(-6.0F, 2.0F, 0.0F);
/*  51 */     this.bipedRightArm.func_78787_b(64, 64);
/*  52 */     this.bipedRightArm.field_78809_i = true;
/*  53 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  54 */     this.bipedLeftArm = new ModelRenderer(this, 40, 14);
/*  55 */     this.bipedLeftArm.func_78789_a(0.0F, -2.0F, -2.0F, 4, 16, 4);
/*  56 */     this.bipedLeftArm.func_78793_a(5.0F, 2.0F, 0.0F);
/*  57 */     this.bipedLeftArm.func_78787_b(64, 64);
/*  58 */     this.bipedLeftArm.field_78809_i = true;
/*  59 */     setRotation(this.bipedLeftArm, 0.0F, 0.0F, 0.0F);
/*  60 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  61 */     this.bipedRightLeg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  62 */     this.bipedRightLeg.func_78793_a(-2.0F, 12.0F, 0.0F);
/*  63 */     this.bipedRightLeg.func_78787_b(64, 64);
/*  64 */     this.bipedRightLeg.field_78809_i = true;
/*  65 */     setRotation(this.bipedRightLeg, 0.0F, 0.0F, 0.0F);
/*  66 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  67 */     this.bipedLeftLeg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
/*  68 */     this.bipedLeftLeg.func_78793_a(2.0F, 12.0F, 0.0F);
/*  69 */     this.bipedLeftLeg.func_78787_b(64, 64);
/*  70 */     this.bipedLeftLeg.field_78809_i = true;
/*  71 */     setRotation(this.bipedLeftLeg, 0.0F, 0.0F, 0.0F);
/*  72 */     this.bipedHead = new ModelRenderer(this, "bipedHead");
/*  73 */     this.bipedHead.func_78793_a(0.0F, 0.0F, 0.0F);
/*  74 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  75 */     this.bipedHead.field_78809_i = true;
/*  76 */     this.bipedHead.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  77 */     this.bipedHead.func_78786_a("tuskright", -2.0F, -4.0F, -5.0F, 1, 2, 1);
/*  78 */     this.bipedHead.func_78786_a("tuskleft", 1.0F, -4.0F, -5.0F, 1, 2, 1);
/*  79 */     this.bipedHead.func_78786_a("nose", -1.0F, -6.0F, -6.0F, 2, 3, 2);
/*  80 */     this.bipedHead.func_78786_a("lip", -2.0F, -2.0F, -6.0F, 4, 1, 2);
/*  81 */     this.bipedChest = new ModelRenderer(this, 12, 35);
/*  82 */     this.bipedChest.func_78789_a(-5.0F, 0.0F, -3.0F, 10, 8, 6);
/*  83 */     this.bipedChest.func_78793_a(0.0F, 0.0F, 0.0F);
/*  84 */     this.bipedChest.func_78787_b(64, 64);
/*  85 */     this.bipedChest.field_78809_i = true;
/*  86 */     setRotation(this.bipedChest, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  90 */     model.field_78795_f = x;
/*  91 */     model.field_78796_g = y;
/*  92 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*  95 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */   
/*     */   public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  99 */     func_78087_a(par2, par3, par4, par5, par6, par7, entity);
/*     */     
/* 101 */     doRender(par7);
/*     */     
/* 103 */     Minecraft mc = Minecraft.func_71410_x();
/* 104 */     if ((mc.field_71474_y.field_74347_j) && (Config.instance().renderHuntsmanGlintEffect)) {
/* 105 */       float f9 = entity.field_70173_aa;
/* 106 */       mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
/* 107 */       GL11.glEnable(3042);
/* 108 */       float f10 = 0.5F;
/* 109 */       GL11.glColor4f(f10, f10, f10, 1.0F);
/* 110 */       GL11.glDepthFunc(514);
/* 111 */       GL11.glDepthMask(false);
/*     */       
/* 113 */       for (int k = 0; k < 2; k++)
/*     */       {
/* 115 */         GL11.glDisable(2896);
/* 116 */         float f11 = 0.76F;
/* 117 */         GL11.glColor4f(0.2F * f11, 0.7F * f11, 0.7F * f11, 1.0F);
/* 118 */         GL11.glBlendFunc(768, 1);
/* 119 */         GL11.glMatrixMode(5890);
/* 120 */         GL11.glLoadIdentity();
/* 121 */         float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
/* 122 */         float f13 = 0.33333334F;
/* 123 */         GL11.glScalef(f13, f13, f13);
/* 124 */         GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
/* 125 */         GL11.glTranslatef(0.0F, f12, 0.0F);
/* 126 */         GL11.glMatrixMode(5888);
/*     */         
/* 128 */         doRender(par7);
/*     */       }
/*     */       
/* 131 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 132 */       GL11.glMatrixMode(5890);
/* 133 */       GL11.glDepthMask(true);
/* 134 */       GL11.glLoadIdentity();
/* 135 */       GL11.glMatrixMode(5888);
/* 136 */       GL11.glEnable(2896);
/* 137 */       GL11.glDisable(3042);
/* 138 */       GL11.glDepthFunc(515);
/*     */     }
/*     */   }
/*     */   
/*     */   private void doRender(float par7) {
/* 143 */     if (this.field_78091_s) {
/* 144 */       float f6 = 2.0F;
/* 145 */       GL11.glPushMatrix();
/* 146 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 147 */       GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
/* 148 */       this.bipedHead.func_78785_a(par7);
/* 149 */       GL11.glPopMatrix();
/* 150 */       GL11.glPushMatrix();
/* 151 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 152 */       GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
/* 153 */       this.bipedBody.func_78785_a(par7);
/* 154 */       this.bipedChest.func_78785_a(par7);
/* 155 */       this.bipedRightArm.func_78785_a(par7);
/* 156 */       this.bipedLeftArm.func_78785_a(par7);
/* 157 */       this.bipedRightLeg.func_78785_a(par7);
/* 158 */       this.bipedLeftLeg.func_78785_a(par7);
/* 159 */       GL11.glPopMatrix();
/*     */     } else {
/* 161 */       this.bipedHead.func_78785_a(par7);
/* 162 */       this.bipedChest.func_78785_a(par7);
/* 163 */       this.bipedBody.func_78785_a(par7);
/* 164 */       this.bipedRightArm.func_78785_a(par7);
/* 165 */       this.bipedLeftArm.func_78785_a(par7);
/* 166 */       this.bipedRightLeg.func_78785_a(par7);
/* 167 */       this.bipedLeftLeg.func_78785_a(par7);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 179 */     this.bipedHead.field_78796_g = (par4 / 57.295776F);
/* 180 */     this.bipedHead.field_78795_f = (par5 / 57.295776F);
/* 181 */     this.bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 182 */     this.bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 183 */     this.bipedRightArm.field_78808_h = 0.0F;
/* 184 */     this.bipedLeftArm.field_78808_h = 0.0F;
/* 185 */     this.bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 186 */     this.bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 187 */     this.bipedRightLeg.field_78796_g = 0.0F;
/* 188 */     this.bipedLeftLeg.field_78796_g = 0.0F;
/*     */     
/* 190 */     if (this.field_78093_q) {
/* 191 */       this.bipedRightArm.field_78795_f += -0.62831855F;
/* 192 */       this.bipedLeftArm.field_78795_f += -0.62831855F;
/* 193 */       this.bipedRightLeg.field_78795_f = -1.2566371F;
/* 194 */       this.bipedLeftLeg.field_78795_f = -1.2566371F;
/* 195 */       this.bipedRightLeg.field_78796_g = 0.31415927F;
/* 196 */       this.bipedLeftLeg.field_78796_g = -0.31415927F;
/*     */     }
/*     */     
/* 199 */     if (this.heldItemLeft != 0) {
/* 200 */       this.bipedLeftArm.field_78795_f = (this.bipedLeftArm.field_78795_f * 0.5F - 0.31415927F * this.heldItemLeft);
/*     */     }
/*     */     
/* 203 */     if (this.heldItemRight != 0) {
/* 204 */       this.bipedRightArm.field_78795_f = (this.bipedRightArm.field_78795_f * 0.5F - 0.31415927F * this.heldItemRight);
/*     */     }
/*     */     
/* 207 */     this.bipedRightArm.field_78796_g = 0.0F;
/* 208 */     this.bipedLeftArm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 212 */     if (this.field_78095_p > -9990.0F) {
/* 213 */       float f6 = this.field_78095_p;
/* 214 */       this.bipedBody.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/* 215 */       this.bipedRightArm.field_78798_e = (MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 216 */       this.bipedRightArm.field_78800_c = (-MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 217 */       this.bipedLeftArm.field_78798_e = (-MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 218 */       this.bipedLeftArm.field_78800_c = (MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 219 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g;
/* 220 */       this.bipedLeftArm.field_78796_g += this.bipedBody.field_78796_g;
/* 221 */       this.bipedLeftArm.field_78795_f += this.bipedBody.field_78796_g;
/* 222 */       f6 = 1.0F - this.field_78095_p;
/* 223 */       f6 *= f6;
/* 224 */       f6 *= f6;
/* 225 */       f6 = 1.0F - f6;
/* 226 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 227 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.bipedHead.field_78795_f - 0.7F) * 0.75F;
/* 228 */       this.bipedRightArm.field_78795_f = ((float)(this.bipedRightArm.field_78795_f - (f7 * 1.2D + f8)));
/* 229 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0F;
/* 230 */       this.bipedRightArm.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 233 */     if (this.isSneak) {
/* 234 */       this.bipedBody.field_78795_f = 0.5F;
/* 235 */       this.bipedRightArm.field_78795_f += 0.4F;
/* 236 */       this.bipedLeftArm.field_78795_f += 0.4F;
/* 237 */       this.bipedRightLeg.field_78798_e = 4.0F;
/* 238 */       this.bipedLeftLeg.field_78798_e = 4.0F;
/* 239 */       this.bipedRightLeg.field_78797_d = 9.0F;
/* 240 */       this.bipedLeftLeg.field_78797_d = 9.0F;
/* 241 */       this.bipedHead.field_78797_d = 1.0F;
/*     */     } else {
/* 243 */       this.bipedBody.field_78795_f = 0.0F;
/* 244 */       this.bipedRightLeg.field_78798_e = 0.1F;
/* 245 */       this.bipedLeftLeg.field_78798_e = 0.1F;
/* 246 */       this.bipedRightLeg.field_78797_d = 12.0F;
/* 247 */       this.bipedLeftLeg.field_78797_d = 12.0F;
/* 248 */       this.bipedHead.field_78797_d = 0.0F;
/*     */     }
/*     */     
/* 251 */     this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 252 */     this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 253 */     this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 254 */     this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     
/* 256 */     if (this.aimedBow) {
/* 257 */       float f6 = 0.0F;
/* 258 */       float f7 = 0.0F;
/* 259 */       this.bipedRightArm.field_78808_h = 0.0F;
/* 260 */       this.bipedLeftArm.field_78808_h = 0.0F;
/* 261 */       this.bipedRightArm.field_78796_g = (-(0.1F - f6 * 0.6F) + this.bipedHead.field_78796_g);
/* 262 */       this.bipedLeftArm.field_78796_g = (0.1F - f6 * 0.6F + this.bipedHead.field_78796_g + 0.4F);
/* 263 */       this.bipedRightArm.field_78795_f = (-1.5707964F + this.bipedHead.field_78795_f);
/* 264 */       this.bipedLeftArm.field_78795_f = (-1.5707964F + this.bipedHead.field_78795_f);
/* 265 */       this.bipedRightArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 266 */       this.bipedLeftArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 267 */       this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 268 */       this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 269 */       this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 270 */       this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     }
/*     */     
/* 273 */     EntityGoblinGulg entityDemon = (EntityGoblinGulg)entity;
/* 274 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 276 */     if (i > 0) {
/* 277 */       this.bipedRightArm.field_78795_f = (-2.0F + 1.5F * func_78172_a(i - par4, 10.0F));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private float func_78172_a(float par1, float par2)
/*     */   {
/* 284 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGoblinGulg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */