/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityBroom;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelBroom
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer handle;
/*     */   ModelRenderer bristle1;
/*     */   ModelRenderer bristle2;
/*     */   ModelRenderer bristle3;
/*     */   ModelRenderer bristle4;
/*     */   ModelRenderer bristle5;
/*     */   ModelRenderer bristle6;
/*     */   ModelRenderer bristle7;
/*     */   ModelRenderer bristle8;
/*     */   ModelRenderer bristle9;
/*     */   
/*     */   public ModelBroom()
/*     */   {
/*  28 */     this.field_78090_t = 32;
/*  29 */     this.field_78089_u = 32;
/*     */     
/*  31 */     this.handle = new ModelRenderer(this, 24, 0);
/*  32 */     this.handle.func_78789_a(-1.0F, -10.0F, -1.0F, 2, 24, 2);
/*  33 */     this.handle.func_78793_a(0.0F, 11.0F, -5.0F);
/*  34 */     this.handle.func_78787_b(32, 32);
/*  35 */     this.handle.field_78809_i = true;
/*  36 */     setRotation(this.handle, 1.570796F, 0.0F, 0.0F);
/*  37 */     this.bristle1 = new ModelRenderer(this, 0, 0);
/*  38 */     this.bristle1.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
/*  39 */     this.bristle1.func_78793_a(-1.0F, 10.0F, 9.0F);
/*  40 */     this.bristle1.func_78787_b(32, 32);
/*  41 */     this.bristle1.field_78809_i = true;
/*  42 */     setRotation(this.bristle1, 0.1858931F, -0.1487144F, 0.0F);
/*  43 */     this.bristle2 = new ModelRenderer(this, 0, 0);
/*  44 */     this.bristle2.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
/*  45 */     this.bristle2.func_78793_a(1.0F, 12.0F, 9.0F);
/*  46 */     this.bristle2.func_78787_b(32, 32);
/*  47 */     this.bristle2.field_78809_i = true;
/*  48 */     setRotation(this.bristle2, -0.1487144F, 0.1858931F, 0.0F);
/*  49 */     this.bristle3 = new ModelRenderer(this, 0, 12);
/*  50 */     this.bristle3.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 9);
/*  51 */     this.bristle3.func_78793_a(1.0F, 10.0F, 9.0F);
/*  52 */     this.bristle3.func_78787_b(32, 32);
/*  53 */     this.bristle3.field_78809_i = true;
/*  54 */     setRotation(this.bristle3, 0.2230717F, 0.1858931F, 0.0F);
/*  55 */     this.bristle4 = new ModelRenderer(this, 0, 0);
/*  56 */     this.bristle4.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
/*  57 */     this.bristle4.func_78793_a(0.0F, 10.0F, 9.0F);
/*  58 */     this.bristle4.func_78787_b(32, 32);
/*  59 */     this.bristle4.field_78809_i = true;
/*  60 */     setRotation(this.bristle4, 0.2230717F, 0.0743572F, 0.0F);
/*  61 */     this.bristle5 = new ModelRenderer(this, 0, 0);
/*  62 */     this.bristle5.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
/*  63 */     this.bristle5.func_78793_a(-1.0F, 12.0F, 9.0F);
/*  64 */     this.bristle5.func_78787_b(32, 32);
/*  65 */     this.bristle5.field_78809_i = true;
/*  66 */     setRotation(this.bristle5, -0.2230717F, -0.1487144F, 0.0F);
/*  67 */     this.bristle6 = new ModelRenderer(this, 0, 0);
/*  68 */     this.bristle6.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
/*  69 */     this.bristle6.func_78793_a(0.0F, 11.0F, 9.0F);
/*  70 */     this.bristle6.func_78787_b(32, 32);
/*  71 */     this.bristle6.field_78809_i = true;
/*  72 */     setRotation(this.bristle6, -0.0371786F, 0.0743572F, 0.0F);
/*  73 */     this.bristle7 = new ModelRenderer(this, 0, 0);
/*  74 */     this.bristle7.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
/*  75 */     this.bristle7.func_78793_a(1.0F, 11.0F, 9.0F);
/*  76 */     this.bristle7.func_78787_b(32, 32);
/*  77 */     this.bristle7.field_78809_i = true;
/*  78 */     setRotation(this.bristle7, -0.0371786F, 0.2230717F, 0.0F);
/*  79 */     this.bristle8 = new ModelRenderer(this, 0, 12);
/*  80 */     this.bristle8.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 9);
/*  81 */     this.bristle8.func_78793_a(-1.0F, 11.0F, 9.0F);
/*  82 */     this.bristle8.func_78787_b(32, 32);
/*  83 */     this.bristle8.field_78809_i = true;
/*  84 */     setRotation(this.bristle8, -0.0743572F, -0.1487144F, 0.0F);
/*  85 */     this.bristle9 = new ModelRenderer(this, 0, 12);
/*  86 */     this.bristle9.func_78789_a(-0.5333334F, -0.5F, 0.0F, 1, 1, 9);
/*  87 */     this.bristle9.func_78793_a(0.0F, 12.0F, 9.0F);
/*  88 */     this.bristle9.func_78787_b(32, 32);
/*  89 */     this.bristle9.field_78809_i = true;
/*  90 */     setRotation(this.bristle9, -0.1858931F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*  93 */   public static final float[][] fleeceColorTable = { { 1.0F, 1.0F, 1.0F }, { 0.85F, 0.5F, 0.2F }, { 0.7F, 0.3F, 0.85F }, { 0.4F, 0.6F, 0.85F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.5F, 0.65F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.5F, 0.6F }, { 0.5F, 0.25F, 0.7F }, { 0.2F, 0.3F, 0.7F }, { 0.4F, 0.3F, 0.2F }, { 0.4F, 0.5F, 0.2F }, { 0.6F, 0.2F, 0.2F }, { 0.1F, 0.1F, 0.1F } };
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
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 113 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 114 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 115 */     this.handle.func_78785_a(f5);
/*     */     
/* 117 */     if ((entity != null) && ((entity instanceof EntityBroom))) {
/* 118 */       int j = ((EntityBroom)entity).getBrushColor();
/* 119 */       if ((j < 0) || (j > 15)) {
/* 120 */         j = 12;
/*     */       }
/*     */       
/* 123 */       GL11.glColor3f(fleeceColorTable[j][0], fleeceColorTable[j][1], fleeceColorTable[j][2]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 128 */     this.bristle1.func_78785_a(f5);
/* 129 */     this.bristle2.func_78785_a(f5);
/* 130 */     this.bristle3.func_78785_a(f5);
/* 131 */     this.bristle4.func_78785_a(f5);
/* 132 */     this.bristle5.func_78785_a(f5);
/* 133 */     this.bristle6.func_78785_a(f5);
/* 134 */     this.bristle7.func_78785_a(f5);
/* 135 */     this.bristle8.func_78785_a(f5);
/* 136 */     this.bristle9.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 140 */     model.field_78795_f = x;
/* 141 */     model.field_78796_g = y;
/* 142 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 147 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelBroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */