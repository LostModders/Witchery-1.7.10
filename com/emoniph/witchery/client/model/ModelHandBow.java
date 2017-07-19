/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.BoltType;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelHandBow extends ModelBase
/*     */ {
/*     */   ModelRenderer stockTop;
/*     */   ModelRenderer stockBottom;
/*     */   ModelRenderer stockCatch;
/*     */   ModelRenderer grip;
/*     */   ModelRenderer cross;
/*     */   ModelRenderer drawnCrossOuterR;
/*     */   ModelRenderer drawnCrossInnerR;
/*     */   ModelRenderer drawnCrossOuterL;
/*     */   ModelRenderer drawnCrossInnerL;
/*     */   ModelRenderer drawnStringInnerR;
/*     */   ModelRenderer drawnStringMidR;
/*     */   ModelRenderer drawnStringOuterR;
/*     */   ModelRenderer drawnStringInnerL;
/*     */   ModelRenderer drawnStringMidL;
/*     */   ModelRenderer drawnStringOuterL;
/*     */   ModelRenderer drawnStringCenter;
/*     */   ModelRenderer boltStake;
/*     */   ModelRenderer boltDraining;
/*     */   ModelRenderer boltHoly;
/*     */   ModelRenderer boltSplitting;
/*     */   ModelRenderer boltSplitting2;
/*     */   ModelRenderer boltSilver;
/*     */   
/*     */   public ModelHandBow()
/*     */   {
/*  41 */     this.field_78090_t = 64;
/*  42 */     this.field_78089_u = 32;
/*     */     
/*  44 */     this.stockTop = new ModelRenderer(this, 2, 2);
/*  45 */     this.stockTop.func_78789_a(-1.0F, 0.0F, -5.0F, 2, 1, 7);
/*  46 */     this.stockTop.func_78793_a(0.0F, 0.0F, 0.0F);
/*  47 */     this.stockTop.func_78787_b(64, 32);
/*  48 */     this.stockTop.field_78809_i = true;
/*  49 */     setRotation(this.stockTop, 0.0F, 0.0F, 0.0F);
/*  50 */     this.stockBottom = new ModelRenderer(this, 0, 10);
/*  51 */     this.stockBottom.func_78789_a(-0.5F, 0.0F, -6.0F, 1, 1, 8);
/*  52 */     this.stockBottom.func_78793_a(0.0F, 1.0F, 0.0F);
/*  53 */     this.stockBottom.func_78787_b(64, 32);
/*  54 */     this.stockBottom.field_78809_i = true;
/*  55 */     setRotation(this.stockBottom, 0.0F, 0.0F, 0.0F);
/*  56 */     this.stockCatch = new ModelRenderer(this, 1, 11);
/*  57 */     this.stockCatch.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 1, 1);
/*  58 */     this.stockCatch.func_78793_a(0.0F, -1.0F, 1.0F);
/*  59 */     this.stockCatch.func_78787_b(64, 32);
/*  60 */     this.stockCatch.field_78809_i = true;
/*  61 */     setRotation(this.stockCatch, 0.0F, 0.0F, 0.0F);
/*  62 */     this.grip = new ModelRenderer(this, 0, 3);
/*  63 */     this.grip.func_78789_a(-0.5F, 0.0F, -1.0F, 1, 3, 2);
/*  64 */     this.grip.func_78793_a(0.0F, 2.0F, 0.0F);
/*  65 */     this.grip.func_78787_b(64, 32);
/*  66 */     this.grip.field_78809_i = true;
/*  67 */     setRotation(this.grip, 0.0F, 0.0F, 0.0F);
/*  68 */     this.cross = new ModelRenderer(this, 1, 19);
/*  69 */     this.cross.func_78789_a(-3.0F, 0.0F, 0.0F, 6, 1, 2);
/*  70 */     this.cross.func_78793_a(0.0F, 0.0F, -7.0F);
/*  71 */     this.cross.func_78787_b(64, 32);
/*  72 */     this.cross.field_78809_i = true;
/*  73 */     setRotation(this.cross, 0.0F, 0.0F, 0.0F);
/*  74 */     this.drawnCrossOuterR = new ModelRenderer(this, 0, 14);
/*  75 */     this.drawnCrossOuterR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 2);
/*  76 */     this.drawnCrossOuterR.func_78793_a(-4.0F, 0.0F, -4.0F);
/*  77 */     this.drawnCrossOuterR.func_78787_b(64, 32);
/*  78 */     this.drawnCrossOuterR.field_78809_i = true;
/*  79 */     setRotation(this.drawnCrossOuterR, 0.0F, 0.0F, 0.0F);
/*  80 */     this.drawnCrossInnerR = new ModelRenderer(this, 0, 14);
/*  81 */     this.drawnCrossInnerR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 2);
/*  82 */     this.drawnCrossInnerR.func_78793_a(-3.0F, 0.0F, -6.0F);
/*  83 */     this.drawnCrossInnerR.func_78787_b(64, 32);
/*  84 */     this.drawnCrossInnerR.field_78809_i = true;
/*  85 */     setRotation(this.drawnCrossInnerR, 0.0F, 0.0F, 0.0F);
/*  86 */     this.drawnCrossOuterL = new ModelRenderer(this, 0, 14);
/*  87 */     this.drawnCrossOuterL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
/*  88 */     this.drawnCrossOuterL.func_78793_a(4.0F, 0.0F, -4.0F);
/*  89 */     this.drawnCrossOuterL.func_78787_b(64, 32);
/*  90 */     this.drawnCrossOuterL.field_78809_i = true;
/*  91 */     setRotation(this.drawnCrossOuterL, 0.0F, 0.0F, 0.0F);
/*  92 */     this.drawnCrossInnerL = new ModelRenderer(this, 0, 14);
/*  93 */     this.drawnCrossInnerL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
/*  94 */     this.drawnCrossInnerL.func_78793_a(3.0F, 0.0F, -6.0F);
/*  95 */     this.drawnCrossInnerL.func_78787_b(64, 32);
/*  96 */     this.drawnCrossInnerL.field_78809_i = true;
/*  97 */     setRotation(this.drawnCrossInnerL, 0.0F, 0.0F, 0.0F);
/*  98 */     this.drawnStringInnerR = new ModelRenderer(this, 0, 0);
/*  99 */     this.drawnStringInnerR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 1);
/* 100 */     this.drawnStringInnerR.func_78793_a(-2.0F, 0.0F, -1.0F);
/* 101 */     this.drawnStringInnerR.func_78787_b(64, 32);
/* 102 */     this.drawnStringInnerR.field_78809_i = true;
/* 103 */     setRotation(this.drawnStringInnerR, 0.0F, 0.0F, 0.0F);
/* 104 */     this.drawnStringMidR = new ModelRenderer(this, 0, 0);
/* 105 */     this.drawnStringMidR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 1);
/* 106 */     this.drawnStringMidR.func_78793_a(-1.0F, 0.0F, 0.0F);
/* 107 */     this.drawnStringMidR.func_78787_b(64, 32);
/* 108 */     this.drawnStringMidR.field_78809_i = true;
/* 109 */     setRotation(this.drawnStringMidR, 0.0F, 0.0F, 0.0F);
/* 110 */     this.drawnStringOuterR = new ModelRenderer(this, 0, 0);
/* 111 */     this.drawnStringOuterR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 1);
/* 112 */     this.drawnStringOuterR.func_78793_a(-3.0F, 0.0F, -2.0F);
/* 113 */     this.drawnStringOuterR.func_78787_b(64, 32);
/* 114 */     this.drawnStringOuterR.field_78809_i = true;
/* 115 */     setRotation(this.drawnStringOuterR, 0.0F, 0.0F, 0.0F);
/* 116 */     this.drawnStringInnerL = new ModelRenderer(this, 0, 0);
/* 117 */     this.drawnStringInnerL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
/* 118 */     this.drawnStringInnerL.func_78793_a(2.0F, 0.0F, -1.0F);
/* 119 */     this.drawnStringInnerL.func_78787_b(64, 32);
/* 120 */     this.drawnStringInnerL.field_78809_i = true;
/* 121 */     setRotation(this.drawnStringInnerL, 0.0F, 0.0F, 0.0F);
/* 122 */     this.drawnStringMidL = new ModelRenderer(this, 0, 0);
/* 123 */     this.drawnStringMidL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
/* 124 */     this.drawnStringMidL.func_78793_a(1.0F, 0.0F, 0.0F);
/* 125 */     this.drawnStringMidL.func_78787_b(64, 32);
/* 126 */     this.drawnStringMidL.field_78809_i = true;
/* 127 */     setRotation(this.drawnStringMidL, 0.0F, 0.0F, 0.0F);
/* 128 */     this.drawnStringOuterL = new ModelRenderer(this, 0, 0);
/* 129 */     this.drawnStringOuterL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
/* 130 */     this.drawnStringOuterL.func_78793_a(3.0F, 0.0F, -2.0F);
/* 131 */     this.drawnStringOuterL.func_78787_b(64, 32);
/* 132 */     this.drawnStringOuterL.field_78809_i = true;
/* 133 */     setRotation(this.drawnStringOuterL, 0.0F, 0.0F, 0.0F);
/* 134 */     this.drawnStringCenter = new ModelRenderer(this, 4, 0);
/* 135 */     this.drawnStringCenter.func_78789_a(-1.5F, 0.0F, -0.5F, 3, 1, 1);
/* 136 */     this.drawnStringCenter.func_78793_a(0.0F, -0.1F, 1.0F);
/* 137 */     this.drawnStringCenter.func_78787_b(64, 32);
/* 138 */     this.drawnStringCenter.field_78809_i = true;
/* 139 */     setRotation(this.drawnStringCenter, 0.0F, 0.0174533F, 0.0F);
/*     */     
/* 141 */     this.boltStake = new ModelRenderer(this, 0, 22);
/* 142 */     this.boltStake.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
/* 143 */     this.boltStake.func_78793_a(0.0F, -1.0F, -2.0F);
/* 144 */     this.boltStake.func_78787_b(64, 32);
/* 145 */     this.boltStake.field_78809_i = true;
/* 146 */     setRotation(this.boltStake, 0.0F, 0.0F, 0.0F);
/*     */     
/* 148 */     this.boltDraining = new ModelRenderer(this, 20, 22);
/* 149 */     this.boltDraining.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
/* 150 */     this.boltDraining.func_78793_a(0.0F, -1.0F, -2.0F);
/* 151 */     this.boltDraining.func_78787_b(64, 32);
/* 152 */     this.boltDraining.field_78809_i = true;
/* 153 */     setRotation(this.boltDraining, 0.0F, 0.0F, 0.0F);
/*     */     
/* 155 */     this.boltHoly = new ModelRenderer(this, 40, 22);
/* 156 */     this.boltHoly.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
/* 157 */     this.boltHoly.func_78793_a(0.0F, -1.0F, -2.0F);
/* 158 */     this.boltHoly.func_78787_b(64, 32);
/* 159 */     this.boltHoly.field_78809_i = true;
/* 160 */     setRotation(this.boltHoly, 0.0F, 0.0F, 0.0F);
/*     */     
/* 162 */     this.boltSplitting = new ModelRenderer(this, 20, 12);
/* 163 */     this.boltSplitting.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
/* 164 */     this.boltSplitting.func_78793_a(0.0F, -1.0F, -2.0F);
/* 165 */     this.boltSplitting.func_78787_b(64, 32);
/* 166 */     this.boltSplitting.field_78809_i = true;
/* 167 */     setRotation(this.boltSplitting, 0.0F, 0.0F, 0.0F);
/*     */     
/* 169 */     this.boltSplitting2 = new ModelRenderer(this, 17, 11);
/* 170 */     this.boltSplitting2.func_78789_a(-0.5F, 0.5F, -6.0F, 2, 1, 4);
/* 171 */     this.boltSplitting2.func_78793_a(-0.5F, -1.5F, -1.0F);
/* 172 */     this.boltSplitting2.func_78787_b(64, 32);
/* 173 */     this.boltSplitting2.field_78809_i = true;
/* 174 */     setRotation(this.boltSplitting2, 0.0F, 0.0F, 0.0F);
/*     */     
/*     */ 
/* 177 */     this.boltSilver = new ModelRenderer(this, 40, 12);
/* 178 */     this.boltSilver.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
/* 179 */     this.boltSilver.func_78793_a(0.0F, -1.0F, -2.0F);
/* 180 */     this.boltSilver.func_78787_b(64, 32);
/* 181 */     this.boltSilver.field_78809_i = true;
/* 182 */     setRotation(this.boltSplitting, 0.0F, 0.0F, 0.0F);
/*     */     
/* 184 */     this.cross.field_78797_d = -0.3F;
/* 185 */     this.drawnCrossInnerL.field_78797_d = (this.drawnCrossInnerR.field_78797_d = -0.15F);
/* 186 */     this.drawnStringMidL.field_78797_d = (this.drawnStringMidR.field_78797_d = -0.1F);
/* 187 */     this.drawnStringInnerL.field_78797_d = (this.drawnStringInnerR.field_78797_d = -0.05F);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ItemGeneral.BoltType boltType, int useCount) {
/* 191 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 192 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 193 */     this.stockTop.func_78785_a(f5);
/* 194 */     this.stockBottom.func_78785_a(f5);
/* 195 */     this.stockCatch.func_78785_a(f5);
/* 196 */     this.grip.func_78785_a(f5);
/* 197 */     this.cross.func_78785_a(f5);
/*     */     
/* 199 */     if (useCount > 10) {
/* 200 */       this.drawnCrossInnerL.field_78798_e = (this.drawnCrossInnerR.field_78798_e = -6.0F);
/* 201 */       this.drawnCrossOuterL.field_78798_e = (this.drawnCrossOuterR.field_78798_e = -4.0F);
/*     */       
/* 203 */       this.drawnStringInnerL.field_78798_e = (this.drawnStringInnerR.field_78798_e = -1.0F);
/* 204 */       this.drawnStringMidL.field_78798_e = (this.drawnStringMidR.field_78798_e = 0.0F);
/* 205 */       this.drawnStringOuterL.field_78798_e = (this.drawnStringOuterR.field_78798_e = -2.0F);
/* 206 */       this.drawnStringCenter.field_78798_e = 1.0F;
/* 207 */     } else if (useCount > 5) {
/* 208 */       this.drawnCrossInnerL.field_78798_e = (this.drawnCrossInnerR.field_78798_e = -6.0F);
/* 209 */       this.drawnCrossOuterL.field_78798_e = (this.drawnCrossOuterR.field_78798_e = -5.0F);
/*     */       
/* 211 */       this.drawnStringInnerL.field_78798_e = (this.drawnStringInnerR.field_78798_e = -2.0F);
/* 212 */       this.drawnStringMidL.field_78798_e = (this.drawnStringMidR.field_78798_e = -2.0F);
/* 213 */       this.drawnStringOuterL.field_78798_e = (this.drawnStringOuterR.field_78798_e = -3.0F);
/*     */       
/* 215 */       this.drawnStringCenter.field_78798_e = -1.0F;
/* 216 */     } else if (useCount == 0) {
/* 217 */       this.drawnCrossInnerL.field_78798_e = (this.drawnCrossInnerR.field_78798_e = -7.0F);
/* 218 */       this.drawnCrossOuterL.field_78798_e = (this.drawnCrossOuterR.field_78798_e = -6.0F);
/*     */       
/* 220 */       this.drawnStringInnerL.field_78798_e = (this.drawnStringInnerR.field_78798_e = -4.0F);
/* 221 */       this.drawnStringMidL.field_78798_e = (this.drawnStringMidR.field_78798_e = -4.0F);
/* 222 */       this.drawnStringOuterL.field_78798_e = (this.drawnStringOuterR.field_78798_e = -4.0F);
/* 223 */       this.drawnStringCenter.field_78798_e = -3.25F;
/*     */     }
/*     */     
/*     */ 
/* 227 */     this.drawnCrossOuterR.func_78785_a(f5);
/* 228 */     this.drawnCrossOuterL.func_78785_a(f5);
/* 229 */     this.drawnCrossInnerR.func_78785_a(f5);
/* 230 */     this.drawnCrossInnerL.func_78785_a(f5);
/*     */     
/* 232 */     this.drawnStringInnerR.func_78785_a(f5);
/* 233 */     this.drawnStringMidR.func_78785_a(f5);
/* 234 */     this.drawnStringOuterR.func_78785_a(f5);
/* 235 */     this.drawnStringInnerL.func_78785_a(f5);
/* 236 */     this.drawnStringMidL.func_78785_a(f5);
/* 237 */     this.drawnStringOuterL.func_78785_a(f5);
/* 238 */     this.drawnStringCenter.func_78785_a(f5);
/*     */     
/* 240 */     if (boltType == Witchery.Items.GENERIC.itemBoltStake) {
/* 241 */       this.boltStake.func_78785_a(f5);
/* 242 */     } else if (boltType == Witchery.Items.GENERIC.itemBoltAntiMagic) {
/* 243 */       this.boltDraining.func_78785_a(f5);
/* 244 */     } else if (boltType == Witchery.Items.GENERIC.itemBoltHoly) {
/* 245 */       this.boltHoly.func_78785_a(f5);
/* 246 */     } else if (boltType == Witchery.Items.GENERIC.itemBoltSilver) {
/* 247 */       this.boltSilver.func_78785_a(f5);
/* 248 */     } else if (boltType == Witchery.Items.GENERIC.itemBoltSplitting) {
/* 249 */       this.boltSplitting.func_78785_a(f5);
/* 250 */       this.boltSplitting2.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 255 */     model.field_78795_f = x;
/* 256 */     model.field_78796_g = y;
/* 257 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 261 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelHandBow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */