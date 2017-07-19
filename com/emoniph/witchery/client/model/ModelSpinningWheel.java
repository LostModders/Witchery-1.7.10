/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelSpinningWheel extends ModelBase
/*     */ {
/*     */   private ModelRenderer seat;
/*     */   private ModelRenderer legBackRight;
/*     */   private ModelRenderer legBackLeft;
/*     */   private ModelRenderer legFrontRight;
/*     */   private ModelRenderer legFrontLeft;
/*     */   private ModelRenderer thread;
/*     */   private ModelRenderer threadPole;
/*     */   private ModelRenderer armRight;
/*     */   private ModelRenderer armLeft;
/*     */   private ModelRenderer wheel;
/*     */   
/*     */   public ModelSpinningWheel()
/*     */   {
/*  27 */     this.field_78090_t = 64;
/*  28 */     this.field_78089_u = 64;
/*  29 */     func_78085_a("wheel.spokes", 0, -6);
/*  30 */     func_78085_a("wheel.top", 0, 7);
/*  31 */     func_78085_a("wheel.bottom", 0, 7);
/*  32 */     func_78085_a("wheel.back", 23, 5);
/*  33 */     func_78085_a("wheel.front", 23, 5);
/*     */     
/*  35 */     this.seat = new ModelRenderer(this, 0, 0);
/*  36 */     this.seat.func_78789_a(-2.0F, -1.0F, -7.0F, 4, 1, 14);
/*  37 */     this.seat.func_78793_a(0.0F, 18.0F, 0.0F);
/*  38 */     this.seat.func_78787_b(64, 64);
/*  39 */     this.seat.field_78809_i = true;
/*  40 */     setRotation(this.seat, 0.2602503F, 0.0F, 0.0F);
/*  41 */     this.legBackRight = new ModelRenderer(this, 32, 0);
/*  42 */     this.legBackRight.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 9, 1);
/*  43 */     this.legBackRight.func_78793_a(-1.0F, 16.0F, 5.0F);
/*  44 */     this.legBackRight.func_78787_b(64, 64);
/*  45 */     this.legBackRight.field_78809_i = true;
/*  46 */     setRotation(this.legBackRight, 0.1745329F, 0.0F, 0.1745329F);
/*  47 */     this.legBackLeft = new ModelRenderer(this, 32, 0);
/*  48 */     this.legBackLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 9, 1);
/*  49 */     this.legBackLeft.func_78793_a(1.0F, 16.0F, 5.0F);
/*  50 */     this.legBackLeft.func_78787_b(64, 64);
/*  51 */     this.legBackLeft.field_78809_i = true;
/*  52 */     setRotation(this.legBackLeft, 0.1745329F, 0.0F, -0.1745329F);
/*  53 */     this.legFrontRight = new ModelRenderer(this, 0, 6);
/*  54 */     this.legFrontRight.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 6, 1);
/*  55 */     this.legFrontRight.func_78793_a(-1.0F, 19.0F, -6.0F);
/*  56 */     this.legFrontRight.func_78787_b(64, 64);
/*  57 */     this.legFrontRight.field_78809_i = true;
/*  58 */     setRotation(this.legFrontRight, -0.1745329F, 0.0F, 0.1745329F);
/*  59 */     this.legFrontLeft = new ModelRenderer(this, 0, 6);
/*  60 */     this.legFrontLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 6, 1);
/*  61 */     this.legFrontLeft.func_78793_a(1.0F, 19.0F, -6.0F);
/*  62 */     this.legFrontLeft.func_78787_b(64, 64);
/*  63 */     this.legFrontLeft.field_78809_i = true;
/*  64 */     setRotation(this.legFrontLeft, -0.1745329F, 0.0F, -0.1745329F);
/*  65 */     this.thread = new ModelRenderer(this, 23, 0);
/*  66 */     this.thread.func_78789_a(-1.0F, -3.0F, -1.0F, 2, 3, 2);
/*  67 */     this.thread.func_78793_a(0.0F, 12.0F, 5.0F);
/*  68 */     this.thread.func_78787_b(64, 64);
/*  69 */     this.thread.field_78809_i = true;
/*  70 */     setRotation(this.thread, 0.0F, 0.0F, 0.0F);
/*  71 */     this.threadPole = new ModelRenderer(this, 9, 7);
/*  72 */     this.threadPole.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/*  73 */     this.threadPole.func_78793_a(0.0F, 12.0F, 5.0F);
/*  74 */     this.threadPole.func_78787_b(64, 64);
/*  75 */     this.threadPole.field_78809_i = true;
/*  76 */     setRotation(this.threadPole, 0.0F, 0.0F, 0.0F);
/*  77 */     this.armRight = new ModelRenderer(this, 28, 6);
/*  78 */     this.armRight.func_78789_a(-0.5F, -7.0F, -0.5F, 1, 7, 1);
/*  79 */     this.armRight.func_78793_a(-1.0F, 18.0F, -2.0F);
/*  80 */     this.armRight.func_78787_b(64, 64);
/*  81 */     this.armRight.field_78809_i = true;
/*  82 */     setRotation(this.armRight, 0.2268928F, 0.0F, 0.0F);
/*  83 */     this.armLeft = new ModelRenderer(this, 28, 6);
/*  84 */     this.armLeft.func_78789_a(-0.5F, -7.0F, -0.5F, 1, 7, 1);
/*  85 */     this.armLeft.func_78793_a(1.0F, 18.0F, -2.0F);
/*  86 */     this.armLeft.func_78787_b(64, 64);
/*  87 */     this.armLeft.field_78809_i = true;
/*  88 */     setRotation(this.armLeft, 0.2268928F, 0.0F, 0.0F);
/*  89 */     this.wheel = new ModelRenderer(this, "wheel");
/*  90 */     this.wheel.func_78793_a(0.0F, 12.0F, -3.5F);
/*  91 */     setRotation(this.wheel, 0.0F, 0.0F, 0.0F);
/*  92 */     this.wheel.field_78809_i = true;
/*  93 */     this.wheel.func_78786_a("spokes", 0.0F, -3.0F, -3.0F, 0, 6, 6);
/*  94 */     this.wheel.func_78786_a("top", -0.5F, -4.0F, -3.0F, 1, 1, 6);
/*  95 */     this.wheel.func_78786_a("bottom", -0.5F, 3.0F, -3.0F, 1, 1, 6);
/*  96 */     this.wheel.func_78786_a("back", -0.5F, -4.0F, 3.0F, 1, 8, 1);
/*  97 */     this.wheel.func_78786_a("front", -0.5F, -4.0F, -4.0F, 1, 8, 1);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 101 */     model.field_78795_f = x;
/* 102 */     model.field_78796_g = y;
/* 103 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 108 */     render(entity, f, f1, f2, f3, f4, f5, null);
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockSpinningWheel.TileEntitySpinningWheel spinningWheel) {
/* 112 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 113 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity, spinningWheel);
/*     */     
/* 115 */     this.seat.func_78785_a(f5);
/* 116 */     this.legBackRight.func_78785_a(f5);
/* 117 */     this.legBackLeft.func_78785_a(f5);
/* 118 */     this.legFrontRight.func_78785_a(f5);
/* 119 */     this.legFrontLeft.func_78785_a(f5);
/* 120 */     this.thread.func_78785_a(f5);
/* 121 */     this.threadPole.func_78785_a(f5);
/* 122 */     this.armRight.func_78785_a(f5);
/* 123 */     this.armLeft.func_78785_a(f5);
/* 124 */     this.wheel.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 129 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity, null);
/*     */   }
/*     */   
/*     */   private void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity, BlockSpinningWheel.TileEntitySpinningWheel spinningWheel) {
/* 133 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 135 */     if (spinningWheel != null) {
/* 136 */       boolean spinning = (spinningWheel.getCookTime() > 0) && (spinningWheel.getCookTime() < spinningWheel.getTotalCookTime()) && (spinningWheel.powerLevel > 0);
/* 137 */       Minecraft.func_71410_x();long ticks = Minecraft.func_71386_F() / 25L;
/*     */       
/* 139 */       this.wheel.field_78795_f = (spinning ? (float)(-(ticks / 3L) % 360L) : 0.0F);
/* 140 */       this.thread.field_78796_g = (spinning ? (float)(ticks / 2L % 360L) : 0.0F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelSpinningWheel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */