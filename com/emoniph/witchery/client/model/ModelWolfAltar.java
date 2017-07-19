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
/*     */ public class ModelWolfAltar extends ModelBase
/*     */ {
/*     */   public ModelRenderer shape1;
/*     */   public ModelRenderer shape1_1;
/*     */   public ModelRenderer shape1_2;
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer wolfBody;
/*     */   public ModelRenderer wolfBody_1;
/*     */   public ModelRenderer shape37;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer wolfHeadMain0;
/*     */   public ModelRenderer bipedRightLegLower;
/*     */   public ModelRenderer shaft;
/*     */   public ModelRenderer wolfHeadMain3;
/*     */   public ModelRenderer wolfHeadMain1;
/*     */   public ModelRenderer wolfHeadMain2;
/*     */   public ModelRenderer wolfMane;
/*     */   public ModelRenderer wolfHeadMain0_1;
/*     */   public ModelRenderer wolfTail;
/*     */   public ModelRenderer wolfLeg2;
/*     */   public ModelRenderer wolfLeg1;
/*     */   public ModelRenderer wolfLeg4;
/*     */   public ModelRenderer wolfLeg3;
/*     */   public ModelRenderer wolfHeadMain3_1;
/*     */   public ModelRenderer wolfHeadMain2_1;
/*     */   public ModelRenderer wolfHeadMain1_1;
/*     */   public ModelRenderer wolfMane_1;
/*     */   public ModelRenderer wolfHeadMain0_2;
/*     */   public ModelRenderer wolfTail_1;
/*     */   public ModelRenderer wolfLeg2_1;
/*     */   public ModelRenderer wolfLeg1_1;
/*     */   public ModelRenderer wolfLeg4_1;
/*     */   public ModelRenderer wolfLeg3_1;
/*     */   public ModelRenderer wolfHeadMain3_2;
/*     */   public ModelRenderer wolfHeadMain2_2;
/*     */   public ModelRenderer wolfHeadMain1_2;
/*     */   
/*     */   public ModelWolfAltar()
/*     */   {
/*  53 */     this.field_78090_t = 128;
/*  54 */     this.field_78089_u = 128;
/*  55 */     this.wolfHeadMain1_2 = new ModelRenderer(this, 16, 14);
/*  56 */     this.wolfHeadMain1_2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  57 */     this.wolfHeadMain1_2.func_78789_a(-3.0F, -4.5F, -10.0F, 2, 2, 1);
/*     */     
/*  59 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  60 */     this.bipedBody.func_78793_a(0.0F, 3.1F, 0.0F);
/*  61 */     this.bipedBody.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
/*  62 */     this.wolfHeadMain2_2 = new ModelRenderer(this, 16, 14);
/*  63 */     this.wolfHeadMain2_2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  64 */     this.wolfHeadMain2_2.func_78789_a(1.0F, -4.5F, -10.0F, 2, 2, 1);
/*     */     
/*  66 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  67 */     this.bipedRightLeg.field_78809_i = true;
/*  68 */     this.bipedRightLeg.func_78793_a(0.0F, 0.0F, 0.0F);
/*  69 */     this.bipedRightLeg.func_78789_a(-4.0F, 7.4F, 8.0F, 4, 6, 4);
/*  70 */     setRotateAngle(this.bipedRightLeg, -1.0471976F, 0.0F, 0.0F);
/*     */     
/*  72 */     this.wolfMane = new ModelRenderer(this, 21, 0);
/*  73 */     this.wolfMane.func_78793_a(0.0F, 0.0F, 0.0F);
/*  74 */     this.wolfMane.func_78789_a(-4.0F, -3.0F, -9.0F, 8, 7, 6);
/*  75 */     setRotateAngle(this.wolfMane, 0.0F, -0.13665928F, 0.0F);
/*     */     
/*  77 */     this.wolfMane_1 = new ModelRenderer(this, 21, 0);
/*  78 */     this.wolfMane_1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  79 */     this.wolfMane_1.func_78789_a(-4.0F, -3.0F, -9.0F, 8, 7, 6);
/*     */     
/*  81 */     this.bipedRightLegLower = new ModelRenderer(this, 18, 0);
/*  82 */     this.bipedRightLegLower.func_78793_a(0.0F, 0.0F, 0.0F);
/*  83 */     this.bipedRightLegLower.func_78789_a(-4.0F, 15.7F, -6.2F, 4, 6, 4);
/*  84 */     setRotateAngle(this.bipedRightLegLower, 0.95609134F, 0.0F, 0.0F);
/*     */     
/*  86 */     this.wolfHeadMain3 = new ModelRenderer(this, 0, 10);
/*  87 */     this.wolfHeadMain3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  88 */     this.wolfHeadMain3.func_78789_a(-1.5F, -3.1F, -5.0F, 3, 3, 4);
/*     */     
/*  90 */     this.shape37 = new ModelRenderer(this, 0, 0);
/*  91 */     this.shape37.func_78793_a(-3.9F, 18.0F, -6.5F);
/*  92 */     this.shape37.func_78789_a(0.0F, 0.0F, 0.0F, 4, 2, 4);
/*  93 */     this.wolfLeg3 = new ModelRenderer(this, 0, 18);
/*  94 */     this.wolfLeg3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  95 */     this.wolfLeg3.func_78789_a(-2.5F, 4.0F, -8.0F, 2, 8, 2);
/*  96 */     setRotateAngle(this.wolfLeg3, 0.0F, -0.091106184F, 0.0F);
/*     */     
/*  98 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  99 */     this.bipedRightArm.field_78809_i = true;
/* 100 */     this.bipedRightArm.func_78793_a(0.0F, 2.0F, 0.0F);
/* 101 */     this.bipedRightArm.func_78789_a(-8.0F, -2.0F, -2.0F, 4, 12, 4);
/* 102 */     setRotateAngle(this.bipedRightArm, -2.4586453F, 0.4098033F, 0.0F);
/*     */     
/* 104 */     this.shape1_2 = new ModelRenderer(this, 0, 0);
/* 105 */     this.shape1_2.func_78793_a(-8.0F, 20.0F, -8.0F);
/* 106 */     this.shape1_2.func_78789_a(0.0F, 0.0F, 0.0F, 16, 1, 16);
/* 107 */     this.wolfLeg2_1 = new ModelRenderer(this, 0, 18);
/* 108 */     this.wolfLeg2_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 109 */     this.wolfLeg2_1.func_78789_a(0.5F, 4.0F, 3.0F, 2, 8, 2);
/*     */     
/* 111 */     this.wolfHeadMain1_1 = new ModelRenderer(this, 16, 14);
/* 112 */     this.wolfHeadMain1_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 113 */     this.wolfHeadMain1_1.func_78789_a(-3.0F, -4.5F, -10.0F, 2, 2, 1);
/*     */     
/* 115 */     this.wolfLeg4 = new ModelRenderer(this, 0, 18);
/* 116 */     this.wolfLeg4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 117 */     this.wolfLeg4.func_78789_a(0.5F, 4.0F, -8.0F, 2, 8, 2);
/* 118 */     setRotateAngle(this.wolfLeg4, 0.0F, -0.091106184F, 0.0F);
/*     */     
/* 120 */     this.shaft = new ModelRenderer(this, 0, 33);
/* 121 */     this.shaft.func_78793_a(0.0F, 0.0F, 0.0F);
/* 122 */     this.shaft.func_78789_a(-6.5F, 8.0F, -17.9F, 1, 1, 40);
/*     */     
/* 124 */     this.wolfHeadMain0_2 = new ModelRenderer(this, 0, 0);
/* 125 */     this.wolfHeadMain0_2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 126 */     this.wolfHeadMain0_2.func_78789_a(-3.0F, -2.5F, -13.0F, 6, 6, 4);
/*     */     
/* 128 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/* 129 */     this.bipedLeftLeg.field_78809_i = true;
/* 130 */     this.bipedLeftLeg.func_78793_a(0.0F, 0.0F, 0.0F);
/* 131 */     this.bipedLeftLeg.func_78789_a(0.0F, 12.0F, -2.0F, 4, 12, 4);
/*     */     
/*     */ 
/* 134 */     this.shape1 = new ModelRenderer(this, 0, 0);
/* 135 */     this.shape1.func_78793_a(-8.0F, 22.0F, -8.0F);
/* 136 */     this.shape1.func_78789_a(0.0F, 0.0F, 0.0F, 16, 2, 16);
/* 137 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/* 138 */     this.bipedLeftArm.field_78809_i = true;
/* 139 */     this.bipedLeftArm.func_78793_a(0.0F, 2.0F, 0.0F);
/* 140 */     this.bipedLeftArm.func_78789_a(4.0F, -2.0F, -2.0F, 4, 12, 4);
/* 141 */     setRotateAngle(this.bipedLeftArm, -0.4553564F, -0.13665928F, 0.0F);
/*     */     
/* 143 */     this.wolfLeg4_1 = new ModelRenderer(this, 0, 18);
/* 144 */     this.wolfLeg4_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 145 */     this.wolfLeg4_1.func_78789_a(0.5F, 4.0F, -8.0F, 2, 8, 2);
/*     */     
/* 147 */     this.wolfHeadMain2_1 = new ModelRenderer(this, 16, 14);
/* 148 */     this.wolfHeadMain2_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 149 */     this.wolfHeadMain2_1.func_78789_a(1.0F, -4.5F, -10.0F, 2, 2, 1);
/*     */     
/* 151 */     this.wolfHeadMain3_2 = new ModelRenderer(this, 0, 10);
/* 152 */     this.wolfHeadMain3_2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 153 */     this.wolfHeadMain3_2.func_78789_a(-1.5F, 0.5F, -17.1F, 3, 3, 4);
/*     */     
/* 155 */     this.wolfLeg1_1 = new ModelRenderer(this, 0, 18);
/* 156 */     this.wolfLeg1_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 157 */     this.wolfLeg1_1.func_78789_a(-2.5F, 4.0F, 3.0F, 2, 8, 2);
/*     */     
/* 159 */     this.wolfLeg3_1 = new ModelRenderer(this, 0, 18);
/* 160 */     this.wolfLeg3_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 161 */     this.wolfLeg3_1.func_78789_a(-2.5F, 4.0F, -8.0F, 2, 8, 2);
/*     */     
/* 163 */     this.wolfBody = new ModelRenderer(this, 18, 14);
/* 164 */     this.wolfBody.func_78793_a(-5.0F, 14.0F, 3.5F);
/* 165 */     this.wolfBody.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 6, 9);
/* 166 */     setRotateAngle(this.wolfBody, 0.0F, 0.27314404F, 0.0F);
/* 167 */     this.wolfLeg2 = new ModelRenderer(this, 0, 18);
/* 168 */     this.wolfLeg2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 169 */     this.wolfLeg2.func_78789_a(0.5F, 4.0F, 3.0F, 2, 8, 2);
/*     */     
/* 171 */     this.shape1_1 = new ModelRenderer(this, 0, 0);
/* 172 */     this.shape1_1.func_78793_a(-7.0F, 21.0F, -7.0F);
/* 173 */     this.shape1_1.func_78789_a(0.0F, 0.0F, 0.0F, 14, 1, 14);
/* 174 */     this.wolfTail = new ModelRenderer(this, 9, 18);
/* 175 */     this.wolfTail.func_78793_a(0.0F, 0.6F, 0.0F);
/* 176 */     this.wolfTail.func_78789_a(-1.0F, 0.0F, 4.0F, 2, 8, 2);
/* 177 */     setRotateAngle(this.wolfTail, 0.31869712F, 0.0F, 0.0F);
/*     */     
/* 179 */     this.wolfHeadMain3_1 = new ModelRenderer(this, 0, 10);
/* 180 */     this.wolfHeadMain3_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 181 */     this.wolfHeadMain3_1.func_78789_a(-1.5F, 0.5F, -17.1F, 3, 3, 4);
/*     */     
/* 183 */     this.wolfTail_1 = new ModelRenderer(this, 9, 18);
/* 184 */     this.wolfTail_1.func_78793_a(0.0F, 0.6F, 0.0F);
/* 185 */     this.wolfTail_1.func_78789_a(-1.0F, 0.0F, 4.0F, 2, 8, 2);
/* 186 */     setRotateAngle(this.wolfTail_1, 0.31869712F, 0.0F, 0.0F);
/*     */     
/* 188 */     this.wolfLeg1 = new ModelRenderer(this, 0, 18);
/* 189 */     this.wolfLeg1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 190 */     this.wolfLeg1.func_78789_a(-2.5F, 4.0F, 3.0F, 2, 8, 2);
/*     */     
/* 192 */     this.wolfHeadMain1 = new ModelRenderer(this, 16, 14);
/* 193 */     this.wolfHeadMain1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 194 */     this.wolfHeadMain1.func_78789_a(-3.0F, -8.0F, 2.0F, 2, 2, 1);
/*     */     
/* 196 */     this.wolfBody_1 = new ModelRenderer(this, 18, 14);
/* 197 */     this.wolfBody_1.func_78793_a(5.5F, 14.0F, -2.0F);
/* 198 */     this.wolfBody_1.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 6, 9);
/* 199 */     setRotateAngle(this.wolfBody_1, 0.0F, 0.31869712F, 0.0F);
/* 200 */     this.wolfHeadMain2 = new ModelRenderer(this, 16, 14);
/* 201 */     this.wolfHeadMain2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 202 */     this.wolfHeadMain2.func_78789_a(1.0F, -8.0F, 2.0F, 2, 2, 1);
/*     */     
/* 204 */     this.wolfHeadMain0 = new ModelRenderer(this, 0, 42);
/* 205 */     this.wolfHeadMain0.func_78793_a(0.0F, 0.0F, 0.0F);
/* 206 */     this.wolfHeadMain0.func_78789_a(-3.0F, -6.0F, -2.0F, 6, 6, 5);
/* 207 */     setRotateAngle(this.wolfHeadMain0, 0.13665928F, 0.13665928F, 0.0F);
/*     */     
/* 209 */     this.wolfHeadMain0_1 = new ModelRenderer(this, 0, 0);
/* 210 */     this.wolfHeadMain0_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 211 */     this.wolfHeadMain0_1.func_78789_a(-3.0F, -2.5F, -13.0F, 6, 6, 4);
/* 212 */     setRotateAngle(this.wolfHeadMain0_1, -0.22759093F, -0.22759093F, 0.0F);
/*     */     
/* 214 */     this.bipedBody.func_78792_a(this.bipedLeftLeg);
/* 215 */     this.wolfHeadMain0_1.func_78792_a(this.wolfHeadMain1_1);
/* 216 */     this.bipedRightLeg.func_78792_a(this.bipedRightLegLower);
/* 217 */     this.wolfHeadMain0.func_78792_a(this.wolfHeadMain3);
/* 218 */     this.wolfBody.func_78792_a(this.wolfHeadMain0_1);
/* 219 */     this.bipedBody.func_78792_a(this.wolfHeadMain0);
/* 220 */     this.wolfHeadMain0.func_78792_a(this.wolfHeadMain2);
/* 221 */     this.wolfHeadMain0.func_78792_a(this.wolfHeadMain1);
/* 222 */     this.wolfBody.func_78792_a(this.wolfLeg1);
/* 223 */     this.wolfBody_1.func_78792_a(this.wolfTail_1);
/* 224 */     this.wolfHeadMain0_1.func_78792_a(this.wolfHeadMain3_1);
/* 225 */     this.wolfBody.func_78792_a(this.wolfTail);
/* 226 */     this.wolfBody.func_78792_a(this.wolfLeg2);
/* 227 */     this.wolfBody_1.func_78792_a(this.wolfLeg3_1);
/* 228 */     this.wolfBody_1.func_78792_a(this.wolfLeg1_1);
/* 229 */     this.wolfHeadMain0_2.func_78792_a(this.wolfHeadMain3_2);
/* 230 */     this.wolfHeadMain0_1.func_78792_a(this.wolfHeadMain2_1);
/* 231 */     this.wolfBody_1.func_78792_a(this.wolfLeg4_1);
/* 232 */     this.bipedBody.func_78792_a(this.bipedLeftArm);
/* 233 */     this.wolfBody_1.func_78792_a(this.wolfHeadMain0_2);
/* 234 */     this.bipedRightArm.func_78792_a(this.shaft);
/* 235 */     this.wolfBody.func_78792_a(this.wolfLeg4);
/* 236 */     this.wolfBody_1.func_78792_a(this.wolfLeg2_1);
/* 237 */     this.bipedBody.func_78792_a(this.bipedRightArm);
/* 238 */     this.wolfBody.func_78792_a(this.wolfLeg3);
/* 239 */     this.wolfBody_1.func_78792_a(this.wolfMane_1);
/* 240 */     this.wolfBody.func_78792_a(this.wolfMane);
/* 241 */     this.bipedBody.func_78792_a(this.bipedRightLeg);
/* 242 */     this.wolfHeadMain0_2.func_78792_a(this.wolfHeadMain1_2);
/* 243 */     this.wolfHeadMain0_2.func_78792_a(this.wolfHeadMain2_2);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 248 */     GL11.glPushMatrix();
/* 249 */     GL11.glTranslatef(this.bipedBody.field_82906_o, this.bipedBody.field_82908_p, this.bipedBody.field_82907_q);
/* 250 */     GL11.glTranslatef(this.bipedBody.field_78800_c * f5, this.bipedBody.field_78797_d * f5, this.bipedBody.field_78798_e * f5);
/* 251 */     GL11.glScaled(0.7D, 0.7D, 0.7D);
/* 252 */     GL11.glTranslatef(-this.bipedBody.field_82906_o, -this.bipedBody.field_82908_p, -this.bipedBody.field_82907_q);
/* 253 */     GL11.glTranslatef(-this.bipedBody.field_78800_c * f5, -this.bipedBody.field_78797_d * f5, -this.bipedBody.field_78798_e * f5);
/* 254 */     this.bipedBody.func_78785_a(f5);
/* 255 */     GL11.glPopMatrix();
/* 256 */     this.shape37.func_78785_a(f5);
/* 257 */     this.shape1_2.func_78785_a(f5);
/* 258 */     this.shape1.func_78785_a(f5);
/* 259 */     GL11.glPushMatrix();
/* 260 */     GL11.glTranslatef(this.wolfBody.field_82906_o, this.wolfBody.field_82908_p, this.wolfBody.field_82907_q);
/* 261 */     GL11.glTranslatef(this.wolfBody.field_78800_c * f5, this.wolfBody.field_78797_d * f5, this.wolfBody.field_78798_e * f5);
/* 262 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 263 */     GL11.glTranslatef(-this.wolfBody.field_82906_o, -this.wolfBody.field_82908_p, -this.wolfBody.field_82907_q);
/* 264 */     GL11.glTranslatef(-this.wolfBody.field_78800_c * f5, -this.wolfBody.field_78797_d * f5, -this.wolfBody.field_78798_e * f5);
/* 265 */     this.wolfBody.func_78785_a(f5);
/* 266 */     GL11.glPopMatrix();
/* 267 */     this.shape1_1.func_78785_a(f5);
/* 268 */     GL11.glPushMatrix();
/* 269 */     GL11.glTranslatef(this.wolfBody_1.field_82906_o, this.wolfBody_1.field_82908_p, this.wolfBody_1.field_82907_q);
/* 270 */     GL11.glTranslatef(this.wolfBody_1.field_78800_c * f5, this.wolfBody_1.field_78797_d * f5, this.wolfBody_1.field_78798_e * f5);
/* 271 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 272 */     GL11.glTranslatef(-this.wolfBody_1.field_82906_o, -this.wolfBody_1.field_82908_p, -this.wolfBody_1.field_82907_q);
/* 273 */     GL11.glTranslatef(-this.wolfBody_1.field_78800_c * f5, -this.wolfBody_1.field_78797_d * f5, -this.wolfBody_1.field_78798_e * f5);
/* 274 */     this.wolfBody_1.func_78785_a(f5);
/* 275 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
/*     */   {
/* 282 */     modelRenderer.field_78795_f = x;
/* 283 */     modelRenderer.field_78796_g = y;
/* 284 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelWolfAltar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */