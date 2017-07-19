/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelWitchHand
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer wrist;
/*     */   ModelRenderer palmUpper;
/*     */   ModelRenderer palmLower;
/*     */   ModelRenderer finger1Upper;
/*     */   ModelRenderer finger2Upper;
/*     */   ModelRenderer finger3Upper;
/*     */   ModelRenderer finger1Lower;
/*     */   ModelRenderer finger2Lower;
/*     */   ModelRenderer finger3Lower;
/*     */   ModelRenderer rightPalm;
/*     */   ModelRenderer rightFingerUpper;
/*     */   ModelRenderer rightFingerLower;
/*     */   ModelRenderer rightThumbUpper;
/*     */   ModelRenderer rightThumbLower;
/*     */   ModelRenderer leftPalm;
/*     */   ModelRenderer leftFingerUpper;
/*     */   ModelRenderer leftFingerLower;
/*     */   ModelRenderer leftThumbUpper;
/*     */   ModelRenderer leftThumbLower;
/*     */   ModelRenderer scythe;
/*     */   
/*     */   public ModelWitchHand(float scale)
/*     */   {
/*  41 */     this.field_78090_t = 64;
/*  42 */     this.field_78089_u = 64;
/*     */     
/*  44 */     this.wrist = new ModelRenderer(this, 0, 0);
/*  45 */     this.wrist.func_78789_a(0.0F, 0.0F, 0.0F, 5, 5, 2);
/*  46 */     this.wrist.func_78793_a(-3.0F, 0.0F, 0.0F);
/*  47 */     this.wrist.func_78787_b(64, 64);
/*  48 */     this.wrist.field_78809_i = true;
/*  49 */     setRotation(this.wrist, 0.0F, 0.0F, 0.0F);
/*  50 */     this.palmUpper = new ModelRenderer(this, 0, 7);
/*  51 */     this.palmUpper.func_78790_a(0.0F, 0.0F, 0.0F, 5, 1, 5, scale);
/*  52 */     this.palmUpper.func_78793_a(-3.0F, 0.0F, -5.0F);
/*  53 */     this.palmUpper.func_78787_b(64, 64);
/*  54 */     this.palmUpper.field_78809_i = true;
/*  55 */     setRotation(this.palmUpper, 0.0F, 0.0F, 0.0F);
/*  56 */     this.palmLower = new ModelRenderer(this, 0, 13);
/*  57 */     this.palmLower.func_78790_a(0.0F, 0.0F, 0.0F, 5, 2, 3, scale);
/*  58 */     this.palmLower.func_78793_a(-3.0F, 1.0F, -3.0F);
/*  59 */     this.palmLower.func_78787_b(64, 64);
/*  60 */     this.palmLower.field_78809_i = true;
/*  61 */     setRotation(this.palmLower, 0.0F, 0.0F, 0.0F);
/*  62 */     this.finger1Upper = new ModelRenderer(this, 0, 18);
/*  63 */     this.finger1Upper.func_78790_a(0.0F, 0.0F, -2.0F, 1, 1, 4, scale);
/*  64 */     this.finger1Upper.func_78793_a(-3.0F, 1.0F, -7.0F);
/*  65 */     this.finger1Upper.func_78787_b(64, 64);
/*  66 */     this.finger1Upper.field_78809_i = true;
/*  67 */     setRotation(this.finger1Upper, 0.4833219F, 0.0F, 0.0F);
/*  68 */     this.finger2Upper = new ModelRenderer(this, 6, 19);
/*  69 */     this.finger2Upper.func_78790_a(0.0F, 0.0F, -2.0F, 1, 1, 4, scale);
/*  70 */     this.finger2Upper.func_78793_a(-1.0F, 1.0F, -7.0F);
/*  71 */     this.finger2Upper.func_78787_b(64, 64);
/*  72 */     this.finger2Upper.field_78809_i = true;
/*  73 */     setRotation(this.finger2Upper, 0.4833219F, 0.0F, 0.0F);
/*  74 */     this.finger3Upper = new ModelRenderer(this, 12, 18);
/*  75 */     this.finger3Upper.func_78790_a(0.0F, 0.0F, -2.0F, 1, 1, 4, scale);
/*  76 */     this.finger3Upper.func_78793_a(1.0F, 1.0F, -7.0F);
/*  77 */     this.finger3Upper.func_78787_b(64, 64);
/*  78 */     this.finger3Upper.field_78809_i = true;
/*  79 */     setRotation(this.finger3Upper, 0.4833219F, 0.0F, 0.0F);
/*  80 */     this.finger1Lower = new ModelRenderer(this, 0, 23);
/*  81 */     this.finger1Lower.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
/*  82 */     this.finger1Lower.func_78793_a(-3.0F, 2.0F, -9.0F);
/*  83 */     this.finger1Lower.func_78787_b(64, 64);
/*  84 */     this.finger1Lower.field_78809_i = true;
/*  85 */     setRotation(this.finger1Lower, 2.044824F, 0.0F, 0.0F);
/*  86 */     this.finger2Lower = new ModelRenderer(this, 6, 24);
/*  87 */     this.finger2Lower.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
/*  88 */     this.finger2Lower.func_78793_a(-1.0F, 2.0F, -9.0F);
/*  89 */     this.finger2Lower.func_78787_b(64, 64);
/*  90 */     this.finger2Lower.field_78809_i = true;
/*  91 */     setRotation(this.finger2Lower, 2.044824F, 0.0F, 0.0F);
/*  92 */     this.finger3Lower = new ModelRenderer(this, 12, 23);
/*  93 */     this.finger3Lower.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
/*  94 */     this.finger3Lower.func_78793_a(1.0F, 2.0F, -9.0F);
/*  95 */     this.finger3Lower.func_78787_b(64, 64);
/*  96 */     this.finger3Lower.field_78809_i = true;
/*  97 */     setRotation(this.finger3Lower, 2.044824F, 0.0F, 0.0F);
/*  98 */     this.rightPalm = new ModelRenderer(this, 16, 0);
/*  99 */     this.rightPalm.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 6, scale);
/* 100 */     this.rightPalm.func_78793_a(2.0F, 0.0F, -5.0F);
/* 101 */     this.rightPalm.func_78787_b(64, 64);
/* 102 */     this.rightPalm.field_78809_i = true;
/* 103 */     setRotation(this.rightPalm, 0.0F, 0.0F, 0.0F);
/* 104 */     this.rightFingerUpper = new ModelRenderer(this, 20, 7);
/* 105 */     this.rightFingerUpper.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
/* 106 */     this.rightFingerUpper.func_78793_a(3.0F, 0.0F, -5.0F);
/* 107 */     this.rightFingerUpper.func_78787_b(64, 64);
/* 108 */     this.rightFingerUpper.field_78809_i = true;
/* 109 */     setRotation(this.rightFingerUpper, -0.5205006F, 0.0F, 0.0F);
/* 110 */     this.rightFingerLower = new ModelRenderer(this, 20, 12);
/* 111 */     this.rightFingerLower.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 4, scale);
/* 112 */     this.rightFingerLower.func_78793_a(3.0F, -1.0F, -8.0F);
/* 113 */     this.rightFingerLower.func_78787_b(64, 64);
/* 114 */     this.rightFingerLower.field_78809_i = true;
/* 115 */     setRotation(this.rightFingerLower, -2.732628F, 0.0F, 0.0F);
/* 116 */     this.rightThumbUpper = new ModelRenderer(this, 22, 17);
/* 117 */     this.rightThumbUpper.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, scale);
/* 118 */     this.rightThumbUpper.func_78793_a(3.5F, 1.0F, 0.0F);
/* 119 */     this.rightThumbUpper.func_78787_b(64, 64);
/* 120 */     this.rightThumbUpper.field_78809_i = true;
/* 121 */     setRotation(this.rightThumbUpper, -2.7F, -0.7F, -0.669215F);
/* 122 */     this.rightThumbLower = new ModelRenderer(this, 22, 21);
/* 123 */     this.rightThumbLower.func_78790_a(0.0F, -1.0F, -1.0F, 1, 1, 3, scale);
/* 124 */     this.rightThumbLower.func_78793_a(5.0F, 3.0F, -2.0F);
/* 125 */     this.rightThumbLower.func_78787_b(64, 64);
/* 126 */     this.rightThumbLower.field_78809_i = true;
/* 127 */     setRotation(this.rightThumbLower, -1.896109F, 0.2602503F, 0.3717861F);
/* 128 */     this.leftPalm = new ModelRenderer(this, 16, 0);
/* 129 */     this.leftPalm.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 6, scale);
/* 130 */     this.leftPalm.func_78793_a(-5.0F, 0.0F, -5.0F);
/* 131 */     this.leftPalm.func_78787_b(64, 64);
/* 132 */     this.leftPalm.field_78809_i = true;
/* 133 */     setRotation(this.leftPalm, 0.0F, 0.0F, 0.0F);
/* 134 */     this.leftFingerUpper = new ModelRenderer(this, 20, 7);
/* 135 */     this.leftFingerUpper.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
/* 136 */     this.leftFingerUpper.func_78793_a(-5.0F, 0.0F, -5.0F);
/* 137 */     this.leftFingerUpper.func_78787_b(64, 64);
/* 138 */     this.leftFingerUpper.field_78809_i = true;
/* 139 */     setRotation(this.leftFingerUpper, -0.5205006F, 0.0F, 0.0F);
/* 140 */     this.leftFingerLower = new ModelRenderer(this, 20, 12);
/* 141 */     this.leftFingerLower.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 4, scale);
/* 142 */     this.leftFingerLower.func_78793_a(-5.0F, -1.0F, -8.0F);
/* 143 */     this.leftFingerLower.func_78787_b(64, 64);
/* 144 */     this.leftFingerLower.field_78809_i = true;
/* 145 */     setRotation(this.leftFingerLower, -2.732628F, 0.0F, 0.0F);
/* 146 */     this.leftThumbUpper = new ModelRenderer(this, 22, 17);
/* 147 */     this.leftThumbUpper.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, scale);
/* 148 */     this.leftThumbUpper.func_78793_a(-5.0F, 1.0F, 0.0F);
/* 149 */     this.leftThumbUpper.func_78787_b(64, 64);
/* 150 */     this.leftThumbUpper.field_78809_i = true;
/* 151 */     setRotation(this.leftThumbUpper, -1.7F, 0.8F, 0.148711F);
/* 152 */     this.leftThumbLower = new ModelRenderer(this, 22, 21);
/* 153 */     this.leftThumbLower.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, scale);
/* 154 */     this.leftThumbLower.func_78793_a(-6.0F, 4.0F, -1.0F);
/* 155 */     this.leftThumbLower.func_78787_b(64, 64);
/* 156 */     this.leftThumbLower.field_78809_i = true;
/* 157 */     setRotation(this.leftThumbLower, -2.082002F, 0.0371828F, -0.6320403F);
/*     */     
/* 159 */     func_78085_a("scythe.shaft", 58, 5);
/* 160 */     func_78085_a("scythe.blade", 36, 0);
/*     */     
/* 162 */     this.scythe = new ModelRenderer(this, "scythe");
/* 163 */     this.scythe.func_78793_a(-6.0F, 10.0F, 0.0F);
/* 164 */     setRotation(this.scythe, 0.0F, 0.0F, 0.0F);
/* 165 */     this.scythe.field_78809_i = true;
/* 166 */     this.scythe.func_78786_a("shaft", -0.5F, -16.0F, -0.5F, 1, 35, 1);
/* 167 */     this.scythe.func_78786_a("blade", 0.0F, -16.0F, 0.0F, 13, 4, 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean firstPerson, boolean deployed)
/*     */   {
/* 173 */     this.rightFingerUpper.func_78793_a(3.0F, 0.0F, -5.0F);
/* 174 */     this.rightFingerLower.func_78793_a(3.0F, -1.0F, -8.0F);
/*     */     
/* 176 */     this.leftFingerUpper.func_78793_a(-5.0F, 0.0F, -5.0F);
/* 177 */     this.leftFingerLower.func_78793_a(-5.0F, -1.0F, -8.0F);
/*     */     
/* 179 */     if (deployed) {
/* 180 */       this.rightFingerUpper.func_78793_a(3.0F, 4.0F, -4.0F);
/* 181 */       this.rightFingerLower.func_78793_a(3.0F, 1.0F, -4.0F);
/*     */       
/* 183 */       this.leftFingerUpper.func_78793_a(-5.0F, 4.0F, -4.0F);
/* 184 */       this.leftFingerLower.func_78793_a(-5.0F, 1.0F, -4.0F);
/*     */     }
/*     */     
/* 187 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*     */     
/* 189 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 191 */     this.wrist.func_78791_b(f5);
/* 192 */     this.palmUpper.func_78791_b(f5);
/* 193 */     this.palmLower.func_78791_b(f5);
/* 194 */     this.finger1Upper.func_78791_b(f5);
/* 195 */     this.finger2Upper.func_78791_b(f5);
/* 196 */     this.finger3Upper.func_78791_b(f5);
/* 197 */     this.finger1Lower.func_78791_b(f5);
/* 198 */     this.finger2Lower.func_78791_b(f5);
/* 199 */     this.finger3Lower.func_78791_b(f5);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 205 */     if (firstPerson) {
/* 206 */       this.rightPalm.func_78791_b(f5);
/* 207 */       this.rightFingerUpper.func_78791_b(f5);
/* 208 */       this.rightFingerLower.func_78791_b(f5);
/* 209 */       this.rightThumbUpper.func_78791_b(f5);
/* 210 */       this.rightThumbLower.func_78791_b(f5);
/*     */     } else {
/* 212 */       this.leftPalm.func_78791_b(f5);
/* 213 */       this.leftFingerUpper.func_78791_b(f5);
/* 214 */       this.leftFingerLower.func_78791_b(f5);
/* 215 */       this.leftThumbUpper.func_78791_b(f5);
/* 216 */       this.leftThumbLower.func_78791_b(f5);
/*     */     }
/*     */     
/* 219 */     if (deployed) {
/* 220 */       GL11.glScalef(1.2F, 1.2F, 1.2F);
/* 221 */       this.scythe.field_78808_h = -1.5707964F;
/* 222 */       this.scythe.field_78800_c = -5.0F;
/* 223 */       this.scythe.field_78798_e = -3.0F;
/* 224 */       this.scythe.field_78797_d = 2.0F;
/* 225 */       this.scythe.field_78795_f = 3.1415927F;
/* 226 */       this.scythe.field_78796_g = 0.0F;
/* 227 */       if (firstPerson) {
/* 228 */         this.scythe.field_78796_g = -3.1415927F;
/* 229 */         this.scythe.field_78800_c = 6.0F;
/*     */       }
/*     */       
/* 232 */       this.scythe.func_78791_b(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 237 */     model.field_78795_f = x;
/* 238 */     model.field_78796_g = y;
/* 239 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 244 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelWitchHand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */