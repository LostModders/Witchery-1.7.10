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
/*     */ public class ModelGarlicGarland extends ModelBase
/*     */ {
/*     */   public ModelRenderer garlicC;
/*     */   public ModelRenderer garlicA;
/*     */   public ModelRenderer garlicE;
/*     */   public ModelRenderer garlicD;
/*     */   public ModelRenderer garlicB;
/*     */   public ModelRenderer string3;
/*     */   public ModelRenderer string4;
/*     */   public ModelRenderer string1;
/*     */   public ModelRenderer string2;
/*     */   public ModelRenderer garlic1;
/*     */   public ModelRenderer garlic2;
/*     */   public ModelRenderer garlic3;
/*     */   public ModelRenderer garlic4;
/*     */   public ModelRenderer garlic1_1;
/*     */   public ModelRenderer garlic2_1;
/*     */   public ModelRenderer garlic3_1;
/*     */   public ModelRenderer garlic4_1;
/*     */   public ModelRenderer garlic1_2;
/*     */   public ModelRenderer garlic2_2;
/*     */   public ModelRenderer garlic3_2;
/*     */   public ModelRenderer garlic4_2;
/*     */   public ModelRenderer garlic1_3;
/*     */   public ModelRenderer garlic2_3;
/*     */   public ModelRenderer garlic3_3;
/*     */   public ModelRenderer garlic4_3;
/*     */   public ModelRenderer garlic1_4;
/*     */   public ModelRenderer garlic2_4;
/*     */   public ModelRenderer garlic3_4;
/*     */   public ModelRenderer garlic4_4;
/*     */   
/*     */   public ModelGarlicGarland()
/*     */   {
/*  45 */     this.field_78090_t = 32;
/*  46 */     this.field_78089_u = 32;
/*  47 */     this.garlic4 = new ModelRenderer(this, 0, 23);
/*  48 */     this.garlic4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  49 */     this.garlic4.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
/*  50 */     this.garlic3 = new ModelRenderer(this, 0, 13);
/*  51 */     this.garlic3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  52 */     this.garlic3.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
/*  53 */     this.garlicC = new ModelRenderer(this, 0, 0);
/*  54 */     this.garlicC.func_78793_a(0.0F, 0.0F, 7.0F);
/*  55 */     this.garlicC.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
/*  56 */     this.garlic1_2 = new ModelRenderer(this, 0, 3);
/*  57 */     this.garlic1_2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  58 */     this.garlic1_2.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
/*  59 */     this.garlicD = new ModelRenderer(this, 0, 0);
/*  60 */     this.garlicD.func_78793_a(2.5F, 1.5F, 7.0F);
/*  61 */     this.garlicD.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
/*  62 */     this.garlic3_4 = new ModelRenderer(this, 0, 13);
/*  63 */     this.garlic3_4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  64 */     this.garlic3_4.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
/*  65 */     this.garlic1 = new ModelRenderer(this, 0, 3);
/*  66 */     this.garlic1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  67 */     this.garlic1.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
/*  68 */     this.garlic1_3 = new ModelRenderer(this, 0, 3);
/*  69 */     this.garlic1_3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  70 */     this.garlic1_3.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
/*  71 */     this.garlic4_1 = new ModelRenderer(this, 0, 23);
/*  72 */     this.garlic4_1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  73 */     this.garlic4_1.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
/*  74 */     this.garlic1_1 = new ModelRenderer(this, 0, 3);
/*  75 */     this.garlic1_1.func_78793_a(0.0F, 0.0F, 0.0F);
/*  76 */     this.garlic1_1.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
/*  77 */     this.string2 = new ModelRenderer(this, 6, 0);
/*  78 */     this.string2.func_78793_a(-3.0F, 1.8F, 7.0F);
/*  79 */     this.string2.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
/*  80 */     setRotateAngle(this.string2, 0.0F, 0.0F, -0.5462881F);
/*  81 */     this.garlic3_3 = new ModelRenderer(this, 0, 13);
/*  82 */     this.garlic3_3.func_78793_a(0.0F, 0.0F, 0.0F);
/*  83 */     this.garlic3_3.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
/*  84 */     this.garlicE = new ModelRenderer(this, 0, 0);
/*  85 */     this.garlicE.func_78793_a(5.0F, 0.0F, 7.0F);
/*  86 */     this.garlicE.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
/*  87 */     this.garlic1_4 = new ModelRenderer(this, 0, 3);
/*  88 */     this.garlic1_4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  89 */     this.garlic1_4.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
/*  90 */     this.garlic2_4 = new ModelRenderer(this, 0, 7);
/*  91 */     this.garlic2_4.func_78793_a(0.0F, 0.0F, 0.0F);
/*  92 */     this.garlic2_4.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
/*  93 */     this.garlic2 = new ModelRenderer(this, 0, 7);
/*  94 */     this.garlic2.func_78793_a(0.0F, 0.0F, 0.0F);
/*  95 */     this.garlic2.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
/*  96 */     this.string3 = new ModelRenderer(this, 6, 0);
/*  97 */     this.string3.func_78793_a(-0.4F, -0.3F, 7.0F);
/*  98 */     this.string3.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
/*  99 */     setRotateAngle(this.string3, 0.0F, 0.0F, 0.5462881F);
/* 100 */     this.garlic4_3 = new ModelRenderer(this, 0, 23);
/* 101 */     this.garlic4_3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 102 */     this.garlic4_3.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
/* 103 */     this.string1 = new ModelRenderer(this, 6, 0);
/* 104 */     this.string1.func_78793_a(-5.4F, -0.3F, 7.0F);
/* 105 */     this.string1.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
/* 106 */     setRotateAngle(this.string1, 0.0F, 0.0F, 0.5462881F);
/* 107 */     this.garlic2_1 = new ModelRenderer(this, 0, 7);
/* 108 */     this.garlic2_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 109 */     this.garlic2_1.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
/* 110 */     this.string4 = new ModelRenderer(this, 6, 0);
/* 111 */     this.string4.func_78793_a(2.0F, 1.8F, 7.0F);
/* 112 */     this.string4.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
/* 113 */     setRotateAngle(this.string4, 0.0F, 0.0F, -0.5462881F);
/* 114 */     this.garlic3_2 = new ModelRenderer(this, 0, 13);
/* 115 */     this.garlic3_2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 116 */     this.garlic3_2.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
/* 117 */     this.garlicA = new ModelRenderer(this, 0, 0);
/* 118 */     this.garlicA.func_78793_a(-5.0F, 0.0F, 7.0F);
/* 119 */     this.garlicA.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
/* 120 */     this.garlic4_4 = new ModelRenderer(this, 0, 23);
/* 121 */     this.garlic4_4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 122 */     this.garlic4_4.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
/* 123 */     this.garlicB = new ModelRenderer(this, 0, 0);
/* 124 */     this.garlicB.func_78793_a(-2.5F, 1.5F, 7.0F);
/* 125 */     this.garlicB.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
/* 126 */     this.garlic4_2 = new ModelRenderer(this, 0, 23);
/* 127 */     this.garlic4_2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 128 */     this.garlic4_2.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
/* 129 */     this.garlic2_3 = new ModelRenderer(this, 0, 7);
/* 130 */     this.garlic2_3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 131 */     this.garlic2_3.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
/* 132 */     this.garlic2_2 = new ModelRenderer(this, 0, 7);
/* 133 */     this.garlic2_2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 134 */     this.garlic2_2.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
/* 135 */     this.garlic3_1 = new ModelRenderer(this, 0, 13);
/* 136 */     this.garlic3_1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 137 */     this.garlic3_1.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
/* 138 */     this.garlicC.func_78792_a(this.garlic4);
/* 139 */     this.garlicC.func_78792_a(this.garlic3);
/* 140 */     this.garlicE.func_78792_a(this.garlic1_2);
/* 141 */     this.garlicB.func_78792_a(this.garlic3_4);
/* 142 */     this.garlicC.func_78792_a(this.garlic1);
/* 143 */     this.garlicD.func_78792_a(this.garlic1_3);
/* 144 */     this.garlicA.func_78792_a(this.garlic4_1);
/* 145 */     this.garlicA.func_78792_a(this.garlic1_1);
/* 146 */     this.garlicD.func_78792_a(this.garlic3_3);
/* 147 */     this.garlicB.func_78792_a(this.garlic1_4);
/* 148 */     this.garlicB.func_78792_a(this.garlic2_4);
/* 149 */     this.garlicC.func_78792_a(this.garlic2);
/* 150 */     this.garlicD.func_78792_a(this.garlic4_3);
/* 151 */     this.garlicA.func_78792_a(this.garlic2_1);
/* 152 */     this.garlicE.func_78792_a(this.garlic3_2);
/* 153 */     this.garlicB.func_78792_a(this.garlic4_4);
/* 154 */     this.garlicE.func_78792_a(this.garlic4_2);
/* 155 */     this.garlicD.func_78792_a(this.garlic2_3);
/* 156 */     this.garlicE.func_78792_a(this.garlic2_2);
/* 157 */     this.garlicA.func_78792_a(this.garlic3_1);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 162 */     double SCALE = 0.21D;
/* 163 */     GL11.glPushMatrix();
/* 164 */     GL11.glTranslatef(this.garlicC.field_82906_o, this.garlicC.field_82908_p, this.garlicC.field_82907_q);
/* 165 */     GL11.glTranslatef(this.garlicC.field_78800_c * f5, this.garlicC.field_78797_d * f5, this.garlicC.field_78798_e * f5);
/*     */     
/* 167 */     GL11.glScaled(0.21D, 0.21D, 0.21D);
/* 168 */     GL11.glTranslatef(-this.garlicC.field_82906_o, -this.garlicC.field_82908_p, -this.garlicC.field_82907_q);
/* 169 */     GL11.glTranslatef(-this.garlicC.field_78800_c * f5, -this.garlicC.field_78797_d * f5, -this.garlicC.field_78798_e * f5);
/*     */     
/* 171 */     this.garlicC.func_78785_a(f5);
/* 172 */     GL11.glPopMatrix();
/* 173 */     GL11.glPushMatrix();
/* 174 */     GL11.glTranslatef(this.garlicD.field_82906_o, this.garlicD.field_82908_p, this.garlicD.field_82907_q);
/* 175 */     GL11.glTranslatef(this.garlicD.field_78800_c * f5, this.garlicD.field_78797_d * f5, this.garlicD.field_78798_e * f5);
/*     */     
/* 177 */     GL11.glScaled(0.21D, 0.21D, 0.21D);
/* 178 */     GL11.glTranslatef(-this.garlicD.field_82906_o, -this.garlicD.field_82908_p, -this.garlicD.field_82907_q);
/* 179 */     GL11.glTranslatef(-this.garlicD.field_78800_c * f5, -this.garlicD.field_78797_d * f5, -this.garlicD.field_78798_e * f5);
/*     */     
/* 181 */     this.garlicD.func_78785_a(f5);
/* 182 */     GL11.glPopMatrix();
/* 183 */     this.string2.func_78785_a(f5);
/* 184 */     GL11.glPushMatrix();
/* 185 */     GL11.glTranslatef(this.garlicE.field_82906_o, this.garlicE.field_82908_p, this.garlicE.field_82907_q);
/* 186 */     GL11.glTranslatef(this.garlicE.field_78800_c * f5, this.garlicE.field_78797_d * f5, this.garlicE.field_78798_e * f5);
/*     */     
/* 188 */     GL11.glScaled(0.21D, 0.21D, 0.21D);
/* 189 */     GL11.glTranslatef(-this.garlicE.field_82906_o, -this.garlicE.field_82908_p, -this.garlicE.field_82907_q);
/* 190 */     GL11.glTranslatef(-this.garlicE.field_78800_c * f5, -this.garlicE.field_78797_d * f5, -this.garlicE.field_78798_e * f5);
/*     */     
/* 192 */     this.garlicE.func_78785_a(f5);
/* 193 */     GL11.glPopMatrix();
/* 194 */     this.string3.func_78785_a(f5);
/* 195 */     this.string1.func_78785_a(f5);
/* 196 */     this.string4.func_78785_a(f5);
/* 197 */     GL11.glPushMatrix();
/* 198 */     GL11.glTranslatef(this.garlicA.field_82906_o, this.garlicA.field_82908_p, this.garlicA.field_82907_q);
/* 199 */     GL11.glTranslatef(this.garlicA.field_78800_c * f5, this.garlicA.field_78797_d * f5, this.garlicA.field_78798_e * f5);
/*     */     
/* 201 */     GL11.glScaled(0.21D, 0.21D, 0.21D);
/* 202 */     GL11.glTranslatef(-this.garlicA.field_82906_o, -this.garlicA.field_82908_p, -this.garlicA.field_82907_q);
/* 203 */     GL11.glTranslatef(-this.garlicA.field_78800_c * f5, -this.garlicA.field_78797_d * f5, -this.garlicA.field_78798_e * f5);
/*     */     
/* 205 */     this.garlicA.func_78785_a(f5);
/* 206 */     GL11.glPopMatrix();
/* 207 */     GL11.glPushMatrix();
/* 208 */     GL11.glTranslatef(this.garlicB.field_82906_o, this.garlicB.field_82908_p, this.garlicB.field_82907_q);
/* 209 */     GL11.glTranslatef(this.garlicB.field_78800_c * f5, this.garlicB.field_78797_d * f5, this.garlicB.field_78798_e * f5);
/*     */     
/* 211 */     GL11.glScaled(0.21D, 0.21D, 0.21D);
/* 212 */     GL11.glTranslatef(-this.garlicB.field_82906_o, -this.garlicB.field_82908_p, -this.garlicB.field_82907_q);
/* 213 */     GL11.glTranslatef(-this.garlicB.field_78800_c * f5, -this.garlicB.field_78797_d * f5, -this.garlicB.field_78798_e * f5);
/*     */     
/* 215 */     this.garlicB.func_78785_a(f5);
/* 216 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 220 */     modelRenderer.field_78795_f = x;
/* 221 */     modelRenderer.field_78796_g = y;
/* 222 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGarlicGarland.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */