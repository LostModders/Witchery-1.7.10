/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityLilith;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelLilith extends ModelBase
/*     */ {
/*     */   public ModelRenderer legRight;
/*     */   public ModelRenderer legLeft;
/*     */   public ModelRenderer bodyChest;
/*     */   public ModelRenderer bodyWaist;
/*     */   public ModelRenderer skirt1;
/*     */   public ModelRenderer skirt2;
/*     */   public ModelRenderer bodyShoulders;
/*     */   public ModelRenderer armRight;
/*     */   public ModelRenderer armLeft;
/*     */   public ModelRenderer neck;
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer legRightLower;
/*     */   public ModelRenderer legLeftLower;
/*     */   public ModelRenderer armRightLower;
/*     */   public ModelRenderer armRightWing;
/*     */   public ModelRenderer armLeftLower;
/*     */   public ModelRenderer armLeftWing;
/*     */   public ModelRenderer head2;
/*     */   public ModelRenderer hornRight;
/*     */   public ModelRenderer hornLeft;
/*     */   public ModelRenderer nose;
/*     */   public ModelRenderer toothRight;
/*     */   public ModelRenderer toothLeft;
/*     */   public ModelRenderer head3;
/*     */   
/*     */   public ModelLilith()
/*     */   {
/*  41 */     this.field_78090_t = 64;
/*  42 */     this.field_78089_u = 64;
/*  43 */     this.neck = new ModelRenderer(this, 24, 0);
/*  44 */     this.neck.func_78793_a(0.0F, -13.0F, 0.0F);
/*  45 */     this.neck.func_78790_a(-1.5F, -1.5F, -1.5F, 3, 2, 3, 0.0F);
/*  46 */     this.legLeftLower = new ModelRenderer(this, 48, 47);
/*  47 */     this.legLeftLower.field_78809_i = true;
/*  48 */     this.legLeftLower.func_78793_a(0.0F, 0.0F, 0.0F);
/*  49 */     this.legLeftLower.func_78790_a(-2.0F, 8.0F, 2.0F, 4, 13, 4, 0.0F);
/*  50 */     this.hornLeft = new ModelRenderer(this, 52, 30);
/*  51 */     this.hornLeft.field_78809_i = true;
/*  52 */     this.hornLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/*  53 */     this.hornLeft.func_78790_a(1.0F, -12.3F, 0.0F, 6, 10, 0, 0.0F);
/*  54 */     setRotateAngle(this.hornLeft, -0.18203785F, 0.0F, 0.0F);
/*  55 */     this.bodyChest = new ModelRenderer(this, 17, 17);
/*  56 */     this.bodyChest.func_78793_a(0.0F, -9.8F, -1.9F);
/*  57 */     this.bodyChest.func_78790_a(-4.0F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
/*  58 */     setRotateAngle(this.bodyChest, 0.7853982F, 0.0F, 0.0F);
/*  59 */     this.nose = new ModelRenderer(this, 41, 0);
/*  60 */     this.nose.func_78793_a(0.0F, 0.0F, 0.0F);
/*  61 */     this.nose.func_78790_a(-0.5F, -3.6F, -4.0F, 1, 2, 1, 0.0F);
/*  62 */     this.armLeftLower = new ModelRenderer(this, 8, 25);
/*  63 */     this.armLeftLower.field_78809_i = true;
/*  64 */     this.armLeftLower.func_78793_a(0.0F, 0.0F, 0.0F);
/*  65 */     this.armLeftLower.func_78790_a(-0.5F, 9.8F, 0.8F, 3, 13, 3, 0.0F);
/*  66 */     setRotateAngle(this.armLeftLower, -0.22759093F, 0.0F, 0.0F);
/*  67 */     this.skirt1 = new ModelRenderer(this, 0, 49);
/*  68 */     this.skirt1.func_78793_a(0.0F, -0.9F, 0.0F);
/*  69 */     this.skirt1.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 10, 5, 0.0F);
/*     */     
/*  71 */     this.skirt2 = new ModelRenderer(this, 0, 49);
/*  72 */     this.skirt2.func_78793_a(0.0F, -0.9F, 0.0F);
/*  73 */     this.skirt2.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 10, 5, 0.0F);
/*     */     
/*  75 */     this.armLeftWing = new ModelRenderer(this, 0, 13);
/*  76 */     this.armLeftWing.field_78809_i = true;
/*  77 */     this.armLeftWing.func_78793_a(0.0F, 0.0F, 0.0F);
/*  78 */     this.armLeftWing.func_78790_a(1.0F, -19.6F, -12.7F, 0, 30, 4, 0.0F);
/*  79 */     setRotateAngle(this.armLeftWing, 2.5497515F, 0.17453292F, 0.0F);
/*  80 */     this.legRightLower = new ModelRenderer(this, 48, 47);
/*  81 */     this.legRightLower.func_78793_a(0.0F, 0.0F, 0.0F);
/*  82 */     this.legRightLower.func_78790_a(-2.0F, 8.0F, 2.0F, 4, 13, 4, 0.0F);
/*  83 */     this.armLeft = new ModelRenderer(this, 0, 0);
/*  84 */     this.armLeft.field_78809_i = true;
/*  85 */     this.armLeft.func_78793_a(4.4F, -11.5F, 0.0F);
/*  86 */     this.armLeft.func_78790_a(-0.5F, -1.5F, -1.5F, 3, 13, 3, 0.0F);
/*  87 */     this.hornRight = new ModelRenderer(this, 52, 30);
/*  88 */     this.hornRight.func_78793_a(0.0F, 0.0F, 0.0F);
/*  89 */     this.hornRight.func_78790_a(-7.0F, -12.3F, 0.0F, 6, 10, 0, 0.0F);
/*  90 */     setRotateAngle(this.hornRight, -0.18203785F, 0.0F, 0.0F);
/*  91 */     this.legLeft = new ModelRenderer(this, 36, 30);
/*  92 */     this.legLeft.field_78809_i = true;
/*  93 */     this.legLeft.func_78793_a(2.1F, 2.5F, 0.0F);
/*  94 */     this.legLeft.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
/*  95 */     setRotateAngle(this.legLeft, -0.27314404F, 0.0F, 0.0F);
/*  96 */     this.bodyShoulders = new ModelRenderer(this, 15, 6);
/*  97 */     this.bodyShoulders.func_78793_a(0.0F, -12.7F, 0.0F);
/*  98 */     this.bodyShoulders.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 6, 4, 0.0F);
/*  99 */     this.armRightLower = new ModelRenderer(this, 8, 25);
/* 100 */     this.armRightLower.func_78793_a(0.0F, 0.0F, 0.0F);
/* 101 */     this.armRightLower.func_78790_a(-2.5F, 9.8F, 0.8F, 3, 13, 3, 0.0F);
/* 102 */     setRotateAngle(this.armRightLower, -0.22759093F, 0.0F, 0.0F);
/* 103 */     this.head3 = new ModelRenderer(this, 44, 22);
/* 104 */     this.head3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */     this.head3.func_78790_a(-2.0F, -4.7F, 5.6F, 4, 4, 4, 0.0F);
/* 106 */     this.head2 = new ModelRenderer(this, 42, 12);
/* 107 */     this.head2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 108 */     this.head2.func_78790_a(-2.5F, -5.5F, 1.0F, 5, 5, 5, 0.0F);
/* 109 */     setRotateAngle(this.head2, -0.18203785F, 0.0F, 0.0F);
/* 110 */     this.armRightWing = new ModelRenderer(this, 0, 13);
/* 111 */     this.armRightWing.func_78793_a(0.0F, 0.0F, 0.0F);
/* 112 */     this.armRightWing.func_78790_a(-1.0F, -19.6F, -12.7F, 0, 30, 4, 0.0F);
/* 113 */     setRotateAngle(this.armRightWing, 2.5497515F, -0.17453292F, 0.0F);
/* 114 */     this.legRight = new ModelRenderer(this, 36, 30);
/* 115 */     this.legRight.func_78793_a(-2.1F, 2.5F, 0.0F);
/* 116 */     this.legRight.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
/* 117 */     setRotateAngle(this.legRight, -0.27314404F, 0.0F, 0.0F);
/* 118 */     this.armRight = new ModelRenderer(this, 0, 0);
/* 119 */     this.armRight.func_78793_a(-4.5F, -11.5F, 0.0F);
/* 120 */     this.armRight.func_78790_a(-2.5F, -1.5F, -1.5F, 3, 13, 3, 0.0F);
/* 121 */     this.toothLeft = new ModelRenderer(this, 20, 0);
/* 122 */     this.toothLeft.field_78809_i = true;
/* 123 */     this.toothLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/* 124 */     this.toothLeft.func_78790_a(0.5F, -1.6F, -3.6F, 1, 3, 1, -0.35F);
/* 125 */     this.toothRight = new ModelRenderer(this, 20, 0);
/* 126 */     this.toothRight.func_78793_a(0.0F, 0.0F, 0.0F);
/* 127 */     this.toothRight.func_78790_a(-1.5F, -1.6F, -3.6F, 1, 3, 1, -0.35F);
/* 128 */     this.bodyWaist = new ModelRenderer(this, 20, 24);
/* 129 */     this.bodyWaist.func_78793_a(0.0F, -7.5F, 0.0F);
/* 130 */     this.bodyWaist.func_78790_a(-3.0F, 0.0F, -1.0F, 6, 10, 2, 0.0F);
/* 131 */     this.head = new ModelRenderer(this, 40, 0);
/* 132 */     this.head.func_78793_a(0.0F, -13.5F, 0.0F);
/* 133 */     this.head.func_78790_a(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);
/* 134 */     this.legLeft.func_78792_a(this.legLeftLower);
/* 135 */     this.head.func_78792_a(this.hornLeft);
/* 136 */     this.head.func_78792_a(this.nose);
/* 137 */     this.armLeft.func_78792_a(this.armLeftLower);
/* 138 */     this.armLeft.func_78792_a(this.armLeftWing);
/* 139 */     this.legRight.func_78792_a(this.legRightLower);
/* 140 */     this.head.func_78792_a(this.hornRight);
/* 141 */     this.armRight.func_78792_a(this.armRightLower);
/* 142 */     this.head2.func_78792_a(this.head3);
/* 143 */     this.head.func_78792_a(this.head2);
/* 144 */     this.armRight.func_78792_a(this.armRightWing);
/* 145 */     this.head.func_78792_a(this.toothLeft);
/* 146 */     this.head.func_78792_a(this.toothRight);
/*     */   }
/*     */   
/*     */   private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 150 */     modelRenderer.field_78795_f = x;
/* 151 */     modelRenderer.field_78796_g = y;
/* 152 */     modelRenderer.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 157 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 158 */     this.neck.func_78785_a(f5);
/* 159 */     this.bodyChest.func_78785_a(f5);
/* 160 */     this.skirt1.func_78785_a(f5);
/* 161 */     this.skirt2.func_78785_a(f5);
/* 162 */     this.armLeft.func_78785_a(f5);
/* 163 */     this.legLeft.func_78785_a(f5);
/* 164 */     this.bodyShoulders.func_78785_a(f5);
/* 165 */     this.legRight.func_78785_a(f5);
/* 166 */     this.armRight.func_78785_a(f5);
/* 167 */     this.bodyWaist.func_78785_a(f5);
/* 168 */     this.head.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 173 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 174 */     this.head.field_78795_f = (par5 / 57.295776F);
/* 175 */     this.armRight.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 176 */     this.armLeft.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 177 */     this.armRight.field_78808_h = 0.0F;
/* 178 */     this.armLeft.field_78808_h = 0.0F;
/* 179 */     this.legRight.field_78795_f = Math.max(MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2 - 0.27314404F, -0.8F);
/*     */     
/* 181 */     this.legLeft.field_78795_f = Math.max(MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2 - 0.27314404F, -0.8F);
/*     */     
/* 183 */     this.legRight.field_78796_g = 0.0F;
/* 184 */     this.legLeft.field_78796_g = 0.0F;
/*     */     
/* 186 */     this.skirt1.field_78795_f = Math.min(this.legRight.field_78795_f, this.legLeft.field_78795_f);
/* 187 */     this.skirt2.field_78795_f = Math.max(Math.max(this.legRight.field_78795_f, this.legLeft.field_78795_f), 0.2F);
/*     */     
/* 189 */     if (this.field_78093_q) {
/* 190 */       this.armRight.field_78795_f += -0.62831855F;
/* 191 */       this.armLeft.field_78795_f += -0.62831855F;
/* 192 */       this.legRight.field_78795_f = -1.2566371F;
/* 193 */       this.legLeft.field_78795_f = -1.2566371F;
/* 194 */       this.legRight.field_78796_g = 0.31415927F;
/* 195 */       this.legLeft.field_78796_g = -0.31415927F;
/*     */     }
/*     */     
/* 198 */     this.armRight.field_78796_g = 0.0F;
/* 199 */     this.armLeft.field_78796_g = 0.0F;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 224 */     this.armRight.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 225 */     this.armLeft.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 226 */     this.armRight.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 227 */     this.armLeft.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 247 */     EntityLilith entityDemon = (EntityLilith)entity;
/* 248 */     int i = entityDemon.getAttackTimer();
/*     */     
/* 250 */     if (i > 0) {
/* 251 */       float di = 10.0F;
/* 252 */       this.armRight.field_78795_f = (-2.0F + 1.5F * (Math.abs((i - par4) % 10.0F - di * 0.5F) - di * 0.25F) / (di * 0.25F));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelLilith.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */