/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockWitchesOven.TileEntityWitchesOven;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelWitchesOven extends ModelBase
/*     */ {
/*     */   ModelRenderer body;
/*     */   ModelRenderer lidBottom;
/*     */   ModelRenderer lidTop;
/*     */   ModelRenderer chimney;
/*     */   ModelRenderer chimneyTop;
/*     */   ModelRenderer legBackRight;
/*     */   ModelRenderer legFrontRight;
/*     */   ModelRenderer legBackLeft;
/*     */   ModelRenderer legFrontLeft;
/*     */   
/*     */   public ModelWitchesOven()
/*     */   {
/*  25 */     this.field_78090_t = 64;
/*  26 */     this.field_78089_u = 64;
/*  27 */     func_78085_a("legBackRight.legBackRightH", 0, 0);
/*  28 */     func_78085_a("legBackRight.legBackRightV", 0, 2);
/*  29 */     func_78085_a("legFrontRight.legFrontRightH", 0, 0);
/*  30 */     func_78085_a("legFrontRight.legFrontRightV", 0, 2);
/*  31 */     func_78085_a("legBackLeft.legBackLeftH", 0, 0);
/*  32 */     func_78085_a("legBackLeft.legBackLeftV", 0, 2);
/*  33 */     func_78085_a("legFrontLeft.legFrontLeftH", 0, 0);
/*  34 */     func_78085_a("legFrontLeft.legFrontLeftV", 0, 2);
/*     */     
/*  36 */     this.body = new ModelRenderer(this, 0, 0);
/*  37 */     this.body.func_78789_a(0.0F, 1.0F, 0.0F, 12, 8, 12);
/*  38 */     this.body.func_78793_a(-6.0F, 14.0F, -6.0F);
/*  39 */     this.body.func_78787_b(64, 64);
/*  40 */     this.body.field_78809_i = true;
/*  41 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*  42 */     this.lidBottom = new ModelRenderer(this, 0, 20);
/*  43 */     this.lidBottom.func_78789_a(0.0F, 0.0F, 0.0F, 14, 1, 14);
/*  44 */     this.lidBottom.func_78793_a(-7.0F, 14.0F, -7.0F);
/*  45 */     this.lidBottom.func_78787_b(64, 64);
/*  46 */     this.lidBottom.field_78809_i = true;
/*  47 */     setRotation(this.lidBottom, 0.0F, 0.0F, 0.0F);
/*  48 */     this.lidTop = new ModelRenderer(this, 8, 35);
/*  49 */     this.lidTop.func_78789_a(0.0F, 0.0F, 0.0F, 10, 1, 10);
/*  50 */     this.lidTop.func_78793_a(-5.0F, 13.0F, -5.0F);
/*  51 */     this.lidTop.func_78787_b(64, 64);
/*  52 */     this.lidTop.field_78809_i = true;
/*  53 */     setRotation(this.lidTop, 0.0F, 0.0F, 0.0F);
/*  54 */     this.chimney = new ModelRenderer(this, 48, 0);
/*     */     
/*     */ 
/*  57 */     this.chimney.func_78789_a(0.0F, 0.0F, 0.0F, 4, 13, 4);
/*  58 */     this.chimney.func_78793_a(-2.0F, 8.0F, 3.0F);
/*  59 */     this.chimney.func_78787_b(64, 64);
/*  60 */     this.chimney.field_78809_i = true;
/*  61 */     setRotation(this.chimney, 0.0F, 0.0F, 0.0F);
/*  62 */     this.chimneyTop = new ModelRenderer(this, 38, 0);
/*  63 */     this.chimneyTop.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 1);
/*     */     
/*  65 */     this.chimneyTop.func_78793_a(-2.0F, 8.0F, 7.0F);
/*  66 */     this.chimneyTop.func_78787_b(64, 64);
/*  67 */     this.chimneyTop.field_78809_i = true;
/*  68 */     setRotation(this.chimneyTop, 0.0F, 0.0F, 0.0F);
/*  69 */     this.legBackRight = new ModelRenderer(this, "legBackRight");
/*  70 */     this.legBackRight.func_78793_a(-5.0F, 21.0F, -7.0F);
/*  71 */     setRotation(this.legBackRight, 0.0F, 0.0F, 0.0F);
/*  72 */     this.legBackRight.field_78809_i = true;
/*  73 */     this.legBackRight.func_78786_a("legBackRightH", -2.0F, 0.0F, 0.0F, 2, 1, 1);
/*  74 */     this.legBackRight.func_78786_a("legBackRightV", -3.0F, 0.0F, 0.0F, 1, 3, 1);
/*  75 */     this.legFrontRight = new ModelRenderer(this, "legFrontRight");
/*  76 */     this.legFrontRight.func_78793_a(-5.0F, 21.0F, 6.0F);
/*  77 */     setRotation(this.legFrontRight, 0.0F, 0.0F, 0.0F);
/*  78 */     this.legFrontRight.field_78809_i = true;
/*  79 */     this.legFrontRight.func_78786_a("legFrontRightH", -2.0F, 0.0F, 0.0F, 2, 1, 1);
/*  80 */     this.legFrontRight.func_78786_a("legFrontRightV", -3.0F, 0.0F, 0.0F, 1, 3, 1);
/*  81 */     this.legBackLeft = new ModelRenderer(this, "legBackLeft");
/*  82 */     this.legBackLeft.func_78793_a(5.0F, 21.0F, -7.0F);
/*  83 */     setRotation(this.legBackLeft, 0.0F, 0.0F, 0.0F);
/*  84 */     this.legBackLeft.field_78809_i = true;
/*  85 */     this.legBackLeft.func_78786_a("legBackLeftH", 0.0F, 0.0F, 0.0F, 2, 1, 1);
/*  86 */     this.legBackLeft.func_78786_a("legBackLeftV", 2.0F, 0.0F, 0.0F, 1, 3, 1);
/*  87 */     this.legFrontLeft = new ModelRenderer(this, "legFrontLeft");
/*  88 */     this.legFrontLeft.func_78793_a(5.0F, 21.0F, 6.0F);
/*  89 */     setRotation(this.legFrontLeft, 0.0F, 0.0F, 0.0F);
/*  90 */     this.legFrontLeft.field_78809_i = true;
/*  91 */     this.legFrontLeft.func_78786_a("legFrontLeftH", 0.0F, 0.0F, 0.0F, 2, 1, 1);
/*  92 */     this.legFrontLeft.func_78786_a("legFrontLeftV", 2.0F, 0.0F, 0.0F, 1, 3, 1);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockWitchesOven.TileEntityWitchesOven te) {
/*  96 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  97 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  98 */     this.body.func_78785_a(f5);
/*  99 */     this.lidBottom.func_78785_a(f5);
/* 100 */     this.lidTop.func_78785_a(f5);
/* 101 */     this.chimney.func_78785_a(f5);
/* 102 */     this.chimneyTop.func_78785_a(f5);
/* 103 */     this.legBackRight.func_78785_a(f5);
/* 104 */     this.legFrontRight.func_78785_a(f5);
/* 105 */     this.legBackLeft.func_78785_a(f5);
/* 106 */     this.legFrontLeft.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 110 */     model.field_78795_f = x;
/* 111 */     model.field_78796_g = y;
/* 112 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 117 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelWitchesOven.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */