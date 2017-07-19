/*     */ package com.emoniph.witchery.client.model;
/*     */ 
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
/*     */ public class ModelGoblinMog extends ModelBase
/*     */ {
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public ModelRenderer bipedChest;
/*     */   public ModelRenderer bipedSkirt;
/*     */   public ModelRenderer bipedHead;
/*     */   public int heldItemLeft;
/*     */   public int heldItemRight;
/*     */   public boolean isSneak;
/*     */   public boolean aimedBow;
/*     */   
/*     */   public ModelGoblinMog()
/*     */   {
/*  30 */     this(0.0F);
/*     */   }
/*     */   
/*     */   public ModelGoblinMog(float f) {
/*  34 */     this.field_78090_t = 64;
/*  35 */     this.field_78089_u = 64;
/*  36 */     func_78085_a("bipedHead.face", 0, 0);
/*  37 */     func_78085_a("bipedHead.tuskright", 0, 4);
/*  38 */     func_78085_a("bipedHead.tuskleft", 0, 4);
/*  39 */     func_78085_a("bipedHead.nose", 25, 0);
/*  40 */     func_78085_a("bipedHead.lip", 34, 0);
/*     */     
/*  42 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  43 */     this.bipedBody.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
/*  44 */     this.bipedBody.func_78793_a(0.0F, 0.0F, 0.0F);
/*  45 */     this.bipedBody.func_78787_b(64, 64);
/*  46 */     this.bipedBody.field_78809_i = true;
/*  47 */     setRotation(this.bipedBody, 0.0F, 0.0F, 0.0F);
/*  48 */     this.bipedRightArm = new ModelRenderer(this, 40, 14);
/*  49 */     this.bipedRightArm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 14, 4);
/*  50 */     this.bipedRightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  51 */     this.bipedRightArm.func_78787_b(64, 64);
/*  52 */     this.bipedRightArm.field_78809_i = true;
/*  53 */     setRotation(this.bipedRightArm, 0.0F, 0.0F, 0.0F);
/*  54 */     this.bipedLeftArm = new ModelRenderer(this, 40, 14);
/*  55 */     this.bipedLeftArm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 14, 4);
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
/*  72 */     this.bipedChest = new ModelRenderer(this, 35, 5);
/*  73 */     this.bipedChest.func_78789_a(-4.0F, -2.0F, -5.0F, 8, 4, 4);
/*  74 */     this.bipedChest.func_78793_a(0.0F, 2.0F, 0.0F);
/*  75 */     this.bipedChest.func_78787_b(64, 64);
/*  76 */     this.bipedChest.field_78809_i = true;
/*  77 */     setRotation(this.bipedChest, 0.7853982F, 0.0F, 0.0F);
/*  78 */     this.bipedSkirt = new ModelRenderer(this, 14, 34);
/*  79 */     this.bipedSkirt.func_78789_a(-4.5F, 0.0F, -2.5F, 9, 11, 5);
/*  80 */     this.bipedSkirt.func_78793_a(0.0F, 12.0F, 0.0F);
/*  81 */     this.bipedSkirt.func_78787_b(64, 64);
/*  82 */     this.bipedSkirt.field_78809_i = true;
/*  83 */     setRotation(this.bipedSkirt, 0.0F, 0.0F, 0.0F);
/*  84 */     this.bipedHead = new ModelRenderer(this, "bipedHead");
/*  85 */     this.bipedHead.func_78793_a(0.0F, 0.0F, 0.0F);
/*  86 */     setRotation(this.bipedHead, 0.0F, 0.0F, 0.0F);
/*  87 */     this.bipedHead.field_78809_i = true;
/*  88 */     this.bipedHead.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  89 */     this.bipedHead.func_78786_a("tuskright", -2.0F, -4.0F, -5.0F, 1, 2, 1);
/*  90 */     this.bipedHead.func_78786_a("tuskleft", 1.0F, -4.0F, -5.0F, 1, 2, 1);
/*  91 */     this.bipedHead.func_78786_a("nose", -1.0F, -6.0F, -6.0F, 2, 3, 2);
/*  92 */     this.bipedHead.func_78786_a("lip", -2.0F, -2.0F, -6.0F, 4, 1, 2);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  96 */     model.field_78795_f = x;
/*  97 */     model.field_78796_g = y;
/*  98 */     model.field_78808_h = z;
/*     */   }
/*     */   
/* 101 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */   
/*     */   public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/* 105 */     func_78087_a(par2, par3, par4, par5, par6, par7, entity);
/*     */     
/* 107 */     doRender(par7);
/*     */     
/* 109 */     Minecraft mc = Minecraft.func_71410_x();
/* 110 */     if ((mc.field_71474_y.field_74347_j) && (Config.instance().renderHuntsmanGlintEffect)) {
/* 111 */       float f9 = entity.field_70173_aa;
/* 112 */       mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
/* 113 */       GL11.glEnable(3042);
/* 114 */       float f10 = 0.5F;
/* 115 */       GL11.glColor4f(f10, f10, f10, 1.0F);
/* 116 */       GL11.glDepthFunc(514);
/* 117 */       GL11.glDepthMask(false);
/*     */       
/* 119 */       for (int k = 0; k < 2; k++)
/*     */       {
/* 121 */         GL11.glDisable(2896);
/* 122 */         float f11 = 0.76F;
/* 123 */         GL11.glColor4f(0.2F * f11, 0.7F * f11, 0.7F * f11, 1.0F);
/* 124 */         GL11.glBlendFunc(768, 1);
/* 125 */         GL11.glMatrixMode(5890);
/* 126 */         GL11.glLoadIdentity();
/* 127 */         float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
/* 128 */         float f13 = 0.33333334F;
/* 129 */         GL11.glScalef(f13, f13, f13);
/* 130 */         GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
/* 131 */         GL11.glTranslatef(0.0F, f12, 0.0F);
/* 132 */         GL11.glMatrixMode(5888);
/*     */         
/* 134 */         doRender(par7);
/*     */       }
/*     */       
/* 137 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 138 */       GL11.glMatrixMode(5890);
/* 139 */       GL11.glDepthMask(true);
/* 140 */       GL11.glLoadIdentity();
/* 141 */       GL11.glMatrixMode(5888);
/* 142 */       GL11.glEnable(2896);
/* 143 */       GL11.glDisable(3042);
/* 144 */       GL11.glDepthFunc(515);
/*     */     }
/*     */   }
/*     */   
/*     */   private void doRender(float par7) {
/* 149 */     if (this.field_78091_s) {
/* 150 */       float f6 = 2.0F;
/* 151 */       GL11.glPushMatrix();
/* 152 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 153 */       GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
/* 154 */       this.bipedHead.func_78785_a(par7);
/* 155 */       GL11.glPopMatrix();
/* 156 */       GL11.glPushMatrix();
/* 157 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 158 */       GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
/* 159 */       this.bipedBody.func_78785_a(par7);
/* 160 */       this.bipedChest.func_78785_a(par7);
/* 161 */       this.bipedSkirt.func_78785_a(par7);
/* 162 */       this.bipedRightArm.func_78785_a(par7);
/* 163 */       this.bipedLeftArm.func_78785_a(par7);
/* 164 */       this.bipedRightLeg.func_78785_a(par7);
/* 165 */       this.bipedLeftLeg.func_78785_a(par7);
/* 166 */       GL11.glPopMatrix();
/*     */     } else {
/* 168 */       this.bipedHead.func_78785_a(par7);
/* 169 */       this.bipedChest.func_78785_a(par7);
/* 170 */       this.bipedBody.func_78785_a(par7);
/* 171 */       this.bipedSkirt.func_78785_a(par7);
/* 172 */       this.bipedRightArm.func_78785_a(par7);
/* 173 */       this.bipedLeftArm.func_78785_a(par7);
/* 174 */       this.bipedRightLeg.func_78785_a(par7);
/* 175 */       this.bipedLeftLeg.func_78785_a(par7);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 187 */     this.bipedHead.field_78796_g = (par4 / 57.295776F);
/* 188 */     this.bipedHead.field_78795_f = (par5 / 57.295776F);
/* 189 */     this.bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 190 */     this.bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 191 */     this.bipedRightArm.field_78808_h = 0.0F;
/* 192 */     this.bipedLeftArm.field_78808_h = 0.0F;
/* 193 */     this.bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
/* 194 */     this.bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
/* 195 */     this.bipedRightLeg.field_78796_g = 0.0F;
/* 196 */     this.bipedLeftLeg.field_78796_g = 0.0F;
/*     */     
/* 198 */     if (this.field_78093_q) {
/* 199 */       this.bipedRightArm.field_78795_f += -0.62831855F;
/* 200 */       this.bipedLeftArm.field_78795_f += -0.62831855F;
/* 201 */       this.bipedRightLeg.field_78795_f = -1.2566371F;
/* 202 */       this.bipedLeftLeg.field_78795_f = -1.2566371F;
/* 203 */       this.bipedRightLeg.field_78796_g = 0.31415927F;
/* 204 */       this.bipedLeftLeg.field_78796_g = -0.31415927F;
/*     */     }
/*     */     
/* 207 */     if (this.heldItemLeft != 0) {
/* 208 */       this.bipedLeftArm.field_78795_f = (this.bipedLeftArm.field_78795_f * 0.5F - 0.31415927F * this.heldItemLeft);
/*     */     }
/*     */     
/* 211 */     if (this.heldItemRight != 0) {
/* 212 */       this.bipedRightArm.field_78795_f = (this.bipedRightArm.field_78795_f * 0.5F - 0.31415927F * this.heldItemRight);
/*     */     }
/*     */     
/* 215 */     this.bipedRightArm.field_78796_g = 0.0F;
/* 216 */     this.bipedLeftArm.field_78796_g = 0.0F;
/*     */     
/*     */ 
/*     */ 
/* 220 */     if (this.field_78095_p > -9990.0F) {
/* 221 */       float f6 = this.field_78095_p;
/* 222 */       this.bipedBody.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
/* 223 */       this.bipedRightArm.field_78798_e = (MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 224 */       this.bipedRightArm.field_78800_c = (-MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 225 */       this.bipedLeftArm.field_78798_e = (-MathHelper.func_76126_a(this.bipedBody.field_78796_g) * 5.0F);
/* 226 */       this.bipedLeftArm.field_78800_c = (MathHelper.func_76134_b(this.bipedBody.field_78796_g) * 5.0F);
/* 227 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g;
/* 228 */       this.bipedLeftArm.field_78796_g += this.bipedBody.field_78796_g;
/* 229 */       this.bipedLeftArm.field_78795_f += this.bipedBody.field_78796_g;
/* 230 */       f6 = 1.0F - this.field_78095_p;
/* 231 */       f6 *= f6;
/* 232 */       f6 *= f6;
/* 233 */       f6 = 1.0F - f6;
/* 234 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 235 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.bipedHead.field_78795_f - 0.7F) * 0.75F;
/* 236 */       this.bipedRightArm.field_78795_f = ((float)(this.bipedRightArm.field_78795_f - (f7 * 1.2D + f8)));
/* 237 */       this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0F;
/* 238 */       this.bipedRightArm.field_78808_h = (MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F);
/*     */     }
/*     */     
/* 241 */     if (this.isSneak) {
/* 242 */       this.bipedBody.field_78795_f = 0.5F;
/* 243 */       this.bipedRightArm.field_78795_f += 0.4F;
/* 244 */       this.bipedLeftArm.field_78795_f += 0.4F;
/* 245 */       this.bipedRightLeg.field_78798_e = 4.0F;
/* 246 */       this.bipedLeftLeg.field_78798_e = 4.0F;
/* 247 */       this.bipedRightLeg.field_78797_d = 9.0F;
/* 248 */       this.bipedLeftLeg.field_78797_d = 9.0F;
/* 249 */       this.bipedHead.field_78797_d = 1.0F;
/*     */     } else {
/* 251 */       this.bipedBody.field_78795_f = 0.0F;
/* 252 */       this.bipedRightLeg.field_78798_e = 0.1F;
/* 253 */       this.bipedLeftLeg.field_78798_e = 0.1F;
/* 254 */       this.bipedRightLeg.field_78797_d = 12.0F;
/* 255 */       this.bipedLeftLeg.field_78797_d = 12.0F;
/* 256 */       this.bipedHead.field_78797_d = 0.0F;
/*     */     }
/*     */     
/* 259 */     this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 260 */     this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 261 */     this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 262 */     this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     
/* 264 */     if (this.aimedBow) {
/* 265 */       float f6 = 0.0F;
/* 266 */       float f7 = 0.0F;
/* 267 */       this.bipedRightArm.field_78808_h = 0.0F;
/* 268 */       this.bipedLeftArm.field_78808_h = 0.0F;
/* 269 */       this.bipedRightArm.field_78796_g = (-(0.1F - f6 * 0.6F) + this.bipedHead.field_78796_g);
/* 270 */       this.bipedLeftArm.field_78796_g = (0.1F - f6 * 0.6F + this.bipedHead.field_78796_g + 0.4F);
/* 271 */       this.bipedRightArm.field_78795_f = (-1.5707964F + this.bipedHead.field_78795_f);
/* 272 */       this.bipedLeftArm.field_78795_f = (-1.5707964F + this.bipedHead.field_78795_f);
/* 273 */       this.bipedRightArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 274 */       this.bipedLeftArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 275 */       this.bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 276 */       this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 277 */       this.bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 278 */       this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGoblinMog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */