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
/*     */ public class ModelDemonHeart extends ModelBase
/*     */ {
/*     */   ModelRenderer Shape1;
/*     */   ModelRenderer Shape2;
/*     */   ModelRenderer Shape3;
/*     */   ModelRenderer Shape4;
/*     */   ModelRenderer bigTube1;
/*     */   ModelRenderer tube1;
/*     */   ModelRenderer tube2;
/*     */   ModelRenderer tube3;
/*     */   ModelRenderer tube4;
/*     */   ModelRenderer tube5;
/*     */   
/*     */   public ModelDemonHeart()
/*     */   {
/*  26 */     this.field_78090_t = 32;
/*  27 */     this.field_78089_u = 32;
/*     */     
/*  29 */     this.Shape1 = new ModelRenderer(this, 14, 20);
/*  30 */     this.Shape1.func_78789_a(0.0F, 0.0F, 0.0F, 5, 8, 4);
/*  31 */     this.Shape1.func_78793_a(-3.0F, 14.0F, 0.0F);
/*  32 */     this.Shape1.func_78787_b(32, 32);
/*  33 */     this.Shape1.field_78809_i = true;
/*  34 */     setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
/*  35 */     this.Shape2 = new ModelRenderer(this, 0, 7);
/*  36 */     this.Shape2.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 6);
/*  37 */     this.Shape2.func_78793_a(-4.0F, 15.0F, -1.0F);
/*  38 */     this.Shape2.func_78787_b(32, 32);
/*  39 */     this.Shape2.field_78809_i = true;
/*  40 */     setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
/*  41 */     this.Shape3 = new ModelRenderer(this, 13, 0);
/*  42 */     this.Shape3.func_78789_a(0.0F, 0.0F, 0.0F, 1, 6, 4);
/*  43 */     this.Shape3.func_78793_a(-5.0F, 16.0F, 0.0F);
/*  44 */     this.Shape3.func_78787_b(32, 32);
/*  45 */     this.Shape3.field_78809_i = true;
/*  46 */     setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
/*  47 */     this.Shape4 = new ModelRenderer(this, 0, 0);
/*  48 */     this.Shape4.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 2);
/*  49 */     this.Shape4.func_78793_a(-3.0F, 13.0F, 1.0F);
/*  50 */     this.Shape4.func_78787_b(32, 32);
/*  51 */     this.Shape4.field_78809_i = true;
/*  52 */     setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
/*  53 */     this.bigTube1 = new ModelRenderer(this, 3, 3);
/*  54 */     this.bigTube1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 2, 2);
/*  55 */     this.bigTube1.func_78793_a(-2.0F, 15.0F, -1.0F);
/*  56 */     this.bigTube1.func_78787_b(32, 32);
/*  57 */     this.bigTube1.field_78809_i = true;
/*  58 */     setRotation(this.bigTube1, 0.0F, 0.3717861F, 0.2230717F);
/*  59 */     this.tube1 = new ModelRenderer(this, 19, 11);
/*  60 */     this.tube1.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
/*  61 */     this.tube1.func_78793_a(-3.0F, 14.0F, 1.0F);
/*  62 */     this.tube1.func_78787_b(32, 32);
/*  63 */     this.tube1.field_78809_i = true;
/*  64 */     setRotation(this.tube1, 0.4089647F, 0.6320364F, 0.0F);
/*  65 */     this.tube2 = new ModelRenderer(this, 19, 11);
/*  66 */     this.tube2.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
/*  67 */     this.tube2.func_78793_a(-2.0F, 14.0F, 1.0F);
/*  68 */     this.tube2.func_78787_b(32, 32);
/*  69 */     this.tube2.field_78809_i = true;
/*  70 */     setRotation(this.tube2, -0.2974289F, -0.2230717F, -0.3346075F);
/*  71 */     this.tube3 = new ModelRenderer(this, 19, 11);
/*  72 */     this.tube3.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
/*  73 */     this.tube3.func_78793_a(1.0F, 13.0F, -0.8F);
/*  74 */     this.tube3.func_78787_b(32, 32);
/*  75 */     this.tube3.field_78809_i = true;
/*  76 */     setRotation(this.tube3, -0.0743572F, 0.1487144F, -0.2602503F);
/*  77 */     this.tube4 = new ModelRenderer(this, 19, 11);
/*  78 */     this.tube4.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
/*  79 */     this.tube4.func_78793_a(0.0F, 15.0F, 0.0F);
/*  80 */     this.tube4.func_78787_b(32, 32);
/*  81 */     this.tube4.field_78809_i = true;
/*  82 */     setRotation(this.tube4, 0.2602503F, 0.0F, 0.4089647F);
/*  83 */     this.tube5 = new ModelRenderer(this, 19, 11);
/*  84 */     this.tube5.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
/*  85 */     this.tube5.func_78793_a(0.0F, 14.0F, 1.0F);
/*  86 */     this.tube5.func_78787_b(32, 32);
/*  87 */     this.tube5.field_78809_i = true;
/*  88 */     setRotation(this.tube5, -0.2602503F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, long ticks) {
/*  92 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  93 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  94 */     this.tube1.func_78785_a(f5);
/*  95 */     this.tube2.func_78785_a(f5);
/*  96 */     this.tube3.func_78785_a(f5);
/*  97 */     this.tube4.func_78785_a(f5);
/*  98 */     this.tube5.func_78785_a(f5);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 103 */     this.Shape4.func_78785_a(f5);
/*     */     
/* 105 */     GL11.glTranslatef(0.0F, 1.0F, 0.0F);
/*     */     
/* 107 */     double size = 0.165D * (7.0D + 0.25D * Math.sin(0.25132741228718347D * ticks));
/* 108 */     GL11.glScaled(size, size, size);
/*     */     
/* 110 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/* 111 */     this.bigTube1.func_78785_a(f5);
/* 112 */     this.Shape1.func_78785_a(f5);
/* 113 */     this.Shape2.func_78785_a(f5);
/* 114 */     this.Shape3.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 119 */     model.field_78795_f = x;
/* 120 */     model.field_78796_g = y;
/* 121 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 125 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelDemonHeart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */