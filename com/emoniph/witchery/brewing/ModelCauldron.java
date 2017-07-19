/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class ModelCauldron extends ModelBase
/*     */ {
/*     */   ModelRenderer base;
/*     */   ModelRenderer bottomF;
/*     */   ModelRenderer bottomB;
/*     */   ModelRenderer bottomL;
/*     */   ModelRenderer bottomR;
/*     */   ModelRenderer sideF;
/*     */   ModelRenderer sideB;
/*     */   ModelRenderer sideL;
/*     */   ModelRenderer sideR;
/*     */   ModelRenderer neckF;
/*     */   ModelRenderer neckB;
/*     */   ModelRenderer neckL;
/*     */   ModelRenderer neckR;
/*     */   ModelRenderer lipF;
/*     */   ModelRenderer lipB;
/*     */   ModelRenderer lipL;
/*     */   ModelRenderer lipR;
/*     */   ModelRenderer legFL;
/*     */   ModelRenderer legFR;
/*     */   ModelRenderer legBL;
/*     */   ModelRenderer legBR;
/*     */   
/*     */   public ModelCauldron()
/*     */   {
/*  34 */     this.field_78090_t = 64;
/*  35 */     this.field_78089_u = 64;
/*     */     
/*  37 */     this.base = new ModelRenderer(this, 0, 53);
/*  38 */     this.base.func_78789_a(-5.0F, 5.0F, -5.0F, 10, 1, 10);
/*  39 */     this.base.func_78793_a(0.0F, 16.0F, 0.0F);
/*  40 */     this.base.func_78787_b(64, 64);
/*  41 */     this.base.field_78809_i = true;
/*  42 */     setRotation(this.base, 0.0F, 0.0F, 0.0F);
/*  43 */     this.bottomF = new ModelRenderer(this, 0, 50);
/*  44 */     this.bottomF.func_78789_a(-5.0F, 4.0F, -6.0F, 10, 1, 1);
/*  45 */     this.bottomF.func_78793_a(0.0F, 16.0F, 0.0F);
/*  46 */     this.bottomF.func_78787_b(64, 64);
/*  47 */     this.bottomF.field_78809_i = true;
/*  48 */     setRotation(this.bottomF, 0.0F, 0.0F, 0.0F);
/*  49 */     this.bottomB = new ModelRenderer(this, 0, 50);
/*  50 */     this.bottomB.func_78789_a(-5.0F, 4.0F, 5.0F, 10, 1, 1);
/*  51 */     this.bottomB.func_78793_a(0.0F, 16.0F, 0.0F);
/*  52 */     this.bottomB.func_78787_b(64, 64);
/*  53 */     this.bottomB.field_78809_i = true;
/*  54 */     setRotation(this.bottomB, 0.0F, 0.0F, 0.0F);
/*  55 */     this.bottomL = new ModelRenderer(this, 0, 36);
/*  56 */     this.bottomL.func_78789_a(5.0F, 4.0F, -6.0F, 1, 1, 12);
/*  57 */     this.bottomL.func_78793_a(0.0F, 16.0F, 0.0F);
/*  58 */     this.bottomL.func_78787_b(64, 64);
/*  59 */     this.bottomL.field_78809_i = true;
/*  60 */     setRotation(this.bottomL, 0.0F, 0.0F, 0.0F);
/*  61 */     this.bottomR = new ModelRenderer(this, 0, 36);
/*  62 */     this.bottomR.func_78789_a(-6.0F, 4.0F, -6.0F, 1, 1, 12);
/*  63 */     this.bottomR.func_78793_a(0.0F, 16.0F, 0.0F);
/*  64 */     this.bottomR.func_78787_b(64, 64);
/*  65 */     this.bottomR.field_78809_i = true;
/*  66 */     setRotation(this.bottomR, 0.0F, 0.0F, 0.0F);
/*  67 */     this.sideF = new ModelRenderer(this, 27, 45);
/*  68 */     this.sideF.func_78789_a(-6.0F, -2.0F, -7.0F, 12, 6, 1);
/*  69 */     this.sideF.func_78793_a(0.0F, 16.0F, 0.0F);
/*  70 */     this.sideF.func_78787_b(64, 64);
/*  71 */     this.sideF.field_78809_i = true;
/*  72 */     setRotation(this.sideF, 0.0F, 0.0F, 0.0F);
/*  73 */     this.sideB = new ModelRenderer(this, 27, 45);
/*  74 */     this.sideB.func_78789_a(-6.0F, -2.0F, 6.0F, 12, 6, 1);
/*  75 */     this.sideB.func_78793_a(0.0F, 16.0F, 0.0F);
/*  76 */     this.sideB.func_78787_b(64, 64);
/*  77 */     this.sideB.field_78809_i = true;
/*  78 */     setRotation(this.sideB, 0.0F, 0.0F, 0.0F);
/*  79 */     this.sideL = new ModelRenderer(this, 27, 24);
/*  80 */     this.sideL.func_78789_a(6.0F, -2.0F, -7.0F, 1, 6, 14);
/*  81 */     this.sideL.func_78793_a(0.0F, 16.0F, 0.0F);
/*  82 */     this.sideL.func_78787_b(64, 64);
/*  83 */     this.sideL.field_78809_i = true;
/*  84 */     setRotation(this.sideL, 0.0F, 0.0F, 0.0F);
/*  85 */     this.sideR = new ModelRenderer(this, 27, 24);
/*  86 */     this.sideR.func_78789_a(-7.0F, -2.0F, -7.0F, 1, 6, 14);
/*  87 */     this.sideR.func_78793_a(0.0F, 16.0F, 0.0F);
/*  88 */     this.sideR.func_78787_b(64, 64);
/*  89 */     this.sideR.field_78809_i = true;
/*  90 */     setRotation(this.sideR, 0.0F, 0.0F, 0.0F);
/*  91 */     this.neckF = new ModelRenderer(this, 0, 32);
/*  92 */     this.neckF.func_78789_a(-5.0F, -4.0F, -6.0F, 10, 2, 1);
/*  93 */     this.neckF.func_78793_a(0.0F, 16.0F, 0.0F);
/*  94 */     this.neckF.func_78787_b(64, 64);
/*  95 */     this.neckF.field_78809_i = true;
/*  96 */     setRotation(this.neckF, 0.0F, 0.0F, 0.0F);
/*  97 */     this.neckB = new ModelRenderer(this, 0, 32);
/*  98 */     this.neckB.func_78789_a(-5.0F, -4.0F, 5.0F, 10, 2, 1);
/*  99 */     this.neckB.func_78793_a(0.0F, 16.0F, 0.0F);
/* 100 */     this.neckB.func_78787_b(64, 64);
/* 101 */     this.neckB.field_78809_i = true;
/* 102 */     setRotation(this.neckB, 0.0F, 0.0F, 0.0F);
/* 103 */     this.neckL = new ModelRenderer(this, 0, 17);
/* 104 */     this.neckL.func_78789_a(5.0F, -4.0F, -6.0F, 1, 2, 12);
/* 105 */     this.neckL.func_78793_a(0.0F, 16.0F, 0.0F);
/* 106 */     this.neckL.func_78787_b(64, 64);
/* 107 */     this.neckL.field_78809_i = true;
/* 108 */     setRotation(this.neckL, 0.0F, 0.0F, 0.0F);
/* 109 */     this.neckR = new ModelRenderer(this, 0, 17);
/* 110 */     this.neckR.func_78789_a(-6.0F, -4.0F, -6.0F, 1, 2, 12);
/* 111 */     this.neckR.func_78793_a(0.0F, 16.0F, 0.0F);
/* 112 */     this.neckR.func_78787_b(64, 64);
/* 113 */     this.neckR.field_78809_i = true;
/* 114 */     setRotation(this.neckR, 0.0F, 0.0F, 0.0F);
/* 115 */     this.lipF = new ModelRenderer(this, 27, 21);
/* 116 */     this.lipF.func_78789_a(-6.0F, -5.0F, -7.0F, 12, 1, 1);
/* 117 */     this.lipF.func_78793_a(0.0F, 16.0F, 0.0F);
/* 118 */     this.lipF.func_78787_b(64, 64);
/* 119 */     this.lipF.field_78809_i = true;
/* 120 */     setRotation(this.lipF, 0.0F, 0.0F, 0.0F);
/* 121 */     this.lipB = new ModelRenderer(this, 27, 21);
/* 122 */     this.lipB.func_78789_a(-6.0F, -5.0F, 6.0F, 12, 1, 1);
/* 123 */     this.lipB.func_78793_a(0.0F, 16.0F, 0.0F);
/* 124 */     this.lipB.func_78787_b(64, 64);
/* 125 */     this.lipB.field_78809_i = true;
/* 126 */     setRotation(this.lipB, 0.0F, 0.0F, 0.0F);
/* 127 */     this.lipL = new ModelRenderer(this, 27, 5);
/* 128 */     this.lipL.func_78789_a(6.0F, -5.0F, -7.0F, 1, 1, 14);
/* 129 */     this.lipL.func_78793_a(0.0F, 16.0F, 0.0F);
/* 130 */     this.lipL.func_78787_b(64, 64);
/* 131 */     this.lipL.field_78809_i = true;
/* 132 */     setRotation(this.lipL, 0.0F, 0.0F, 0.0F);
/* 133 */     this.lipR = new ModelRenderer(this, 27, 5);
/* 134 */     this.lipR.func_78789_a(-7.0F, -5.0F, -7.0F, 1, 1, 14);
/* 135 */     this.lipR.func_78793_a(0.0F, 16.0F, 0.0F);
/* 136 */     this.lipR.func_78787_b(64, 64);
/* 137 */     this.lipR.field_78809_i = true;
/* 138 */     setRotation(this.lipR, 0.0F, 0.0F, 0.0F);
/* 139 */     this.legFL = new ModelRenderer(this, 0, 0);
/* 140 */     this.legFL.func_78789_a(1.5F, 7.5F, -1.5F, 1, 3, 1);
/* 141 */     this.legFL.func_78793_a(0.0F, 16.0F, 0.0F);
/* 142 */     this.legFL.func_78787_b(64, 64);
/* 143 */     this.legFL.field_78809_i = true;
/* 144 */     setRotation(this.legFL, -0.3490659F, 0.0F, -0.3490659F);
/* 145 */     this.legFR = new ModelRenderer(this, 0, 0);
/* 146 */     this.legFR.func_78789_a(-2.5F, 7.5F, -1.5F, 1, 3, 1);
/* 147 */     this.legFR.func_78793_a(0.0F, 16.0F, 0.0F);
/* 148 */     this.legFR.func_78787_b(64, 64);
/* 149 */     this.legFR.field_78809_i = true;
/* 150 */     setRotation(this.legFR, -0.3490659F, 0.0F, 0.3490659F);
/* 151 */     this.legBL = new ModelRenderer(this, 0, 0);
/* 152 */     this.legBL.func_78789_a(1.5F, 7.5F, 0.5F, 1, 3, 1);
/* 153 */     this.legBL.func_78793_a(0.0F, 16.0F, 0.0F);
/* 154 */     this.legBL.func_78787_b(64, 64);
/* 155 */     this.legBL.field_78809_i = true;
/* 156 */     setRotation(this.legBL, 0.3490659F, 0.0F, -0.3490659F);
/* 157 */     this.legBR = new ModelRenderer(this, 0, 0);
/* 158 */     this.legBR.func_78789_a(-2.5F, 7.5F, 0.5F, 1, 3, 1);
/* 159 */     this.legBR.func_78793_a(0.0F, 16.0F, 0.0F);
/* 160 */     this.legBR.func_78787_b(64, 64);
/* 161 */     this.legBR.field_78809_i = true;
/* 162 */     setRotation(this.legBR, 0.3490659F, 0.0F, 0.3490659F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 166 */     model.field_78795_f = x;
/* 167 */     model.field_78796_g = y;
/* 168 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 173 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 174 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 175 */     this.base.func_78785_a(f5);
/* 176 */     this.bottomF.func_78785_a(f5);
/* 177 */     this.bottomB.func_78785_a(f5);
/* 178 */     this.bottomL.func_78785_a(f5);
/* 179 */     this.bottomR.func_78785_a(f5);
/* 180 */     this.sideF.func_78785_a(f5);
/* 181 */     this.sideB.func_78785_a(f5);
/* 182 */     this.sideL.func_78785_a(f5);
/* 183 */     this.sideR.func_78785_a(f5);
/* 184 */     this.neckF.func_78785_a(f5);
/* 185 */     this.neckB.func_78785_a(f5);
/* 186 */     this.neckL.func_78785_a(f5);
/* 187 */     this.neckR.func_78785_a(f5);
/* 188 */     this.lipF.func_78785_a(f5);
/* 189 */     this.lipB.func_78785_a(f5);
/* 190 */     this.lipL.func_78785_a(f5);
/* 191 */     this.lipR.func_78785_a(f5);
/* 192 */     this.legFL.func_78785_a(f5);
/* 193 */     this.legFR.func_78785_a(f5);
/* 194 */     this.legBL.func_78785_a(f5);
/* 195 */     this.legBR.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 200 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/ModelCauldron.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */