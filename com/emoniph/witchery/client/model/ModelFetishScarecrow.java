/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelFetishScarecrow
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer poleVertical;
/*     */   ModelRenderer poleHorizontal;
/*     */   ModelRenderer head;
/*     */   ModelRenderer headInner;
/*     */   ModelRenderer body;
/*     */   ModelRenderer armLeft;
/*     */   ModelRenderer armRight;
/*     */   ModelRenderer armLeftInner;
/*     */   ModelRenderer armRightInner;
/*     */   
/*     */   public ModelFetishScarecrow()
/*     */   {
/*  28 */     this.field_78090_t = 64;
/*  29 */     this.field_78089_u = 64;
/*     */     
/*  31 */     this.poleVertical = new ModelRenderer(this, 0, 2);
/*  32 */     this.poleVertical.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 15, 2);
/*  33 */     this.poleVertical.func_78793_a(0.0F, 9.0F, 0.0F);
/*  34 */     this.poleVertical.func_78787_b(64, 64);
/*  35 */     this.poleVertical.field_78809_i = true;
/*  36 */     setRotation(this.poleVertical, 0.0F, 0.0F, 0.0F);
/*  37 */     this.poleHorizontal = new ModelRenderer(this, 0, 0);
/*  38 */     this.poleHorizontal.func_78789_a(-8.0F, 0.0F, -0.5F, 16, 1, 1);
/*  39 */     this.poleHorizontal.func_78793_a(0.0F, 13.0F, 0.0F);
/*  40 */     this.poleHorizontal.func_78787_b(64, 64);
/*  41 */     this.poleHorizontal.field_78809_i = true;
/*  42 */     setRotation(this.poleHorizontal, 0.0F, 0.0F, 0.0F);
/*     */     
/*  44 */     this.head = new ModelRenderer(this, 12, 21);
/*  45 */     this.head.func_78789_a(-2.0F, -4.0F, -2.0F, 4, 5, 4);
/*  46 */     this.head.func_78793_a(0.0F, 12.0F, 0.0F);
/*  47 */     this.head.func_78787_b(64, 64);
/*  48 */     this.head.field_78809_i = true;
/*  49 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/*     */     
/*  51 */     this.headInner = new ModelRenderer(this, 29, 25);
/*  52 */     this.headInner.func_78789_a(-2.0F, -4.0F, -1.9F, 4, 5, 0);
/*  53 */     this.headInner.func_78793_a(0.0F, 12.0F, 0.0F);
/*  54 */     this.headInner.func_78787_b(64, 64);
/*  55 */     this.headInner.field_78809_i = true;
/*  56 */     setRotation(this.headInner, 0.0F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*  60 */     this.body = new ModelRenderer(this, 8, 2);
/*  61 */     this.body.func_78789_a(-3.0F, 0.0F, -1.5F, 6, 9, 3);
/*  62 */     this.body.func_78793_a(0.0F, 12.5F, 0.0F);
/*  63 */     this.body.func_78787_b(64, 64);
/*  64 */     this.body.field_78809_i = true;
/*  65 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/*     */     
/*  67 */     this.armLeft = new ModelRenderer(this, 0, 23);
/*  68 */     this.armLeft.func_78789_a(0.0F, -0.5F, -1.5F, 3, 4, 3);
/*  69 */     this.armLeft.func_78793_a(3.0F, 13.0F, 0.0F);
/*  70 */     this.armLeft.func_78787_b(64, 64);
/*  71 */     this.armLeft.field_78809_i = true;
/*  72 */     setRotation(this.armLeft, 0.0F, 0.0F, 0.0F);
/*     */     
/*  74 */     this.armLeftInner = new ModelRenderer(this, 29, 25);
/*  75 */     this.armLeftInner.func_78789_a(2.9F, -0.5F, -1.5F, 0, 4, 3);
/*  76 */     this.armLeftInner.func_78793_a(3.0F, 13.0F, 0.0F);
/*  77 */     this.armLeftInner.func_78787_b(64, 64);
/*  78 */     this.armLeftInner.field_78809_i = true;
/*  79 */     setRotation(this.armLeftInner, 0.0F, 0.0F, 0.0F);
/*     */     
/*  81 */     this.armRight = new ModelRenderer(this, 0, 23);
/*  82 */     this.armRight.func_78789_a(-3.0F, -0.5F, -1.5F, 3, 4, 3);
/*  83 */     this.armRight.func_78793_a(-3.0F, 13.0F, 0.0F);
/*  84 */     this.armRight.func_78787_b(64, 64);
/*  85 */     this.armRight.field_78809_i = true;
/*  86 */     setRotation(this.armRight, 0.0F, 0.0F, 0.0F);
/*     */     
/*  88 */     this.armRightInner = new ModelRenderer(this, 29, 25);
/*  89 */     this.armRightInner.func_78789_a(-2.9F, -0.5F, -1.5F, 0, 4, 3);
/*  90 */     this.armRightInner.func_78793_a(-3.0F, 13.0F, 0.0F);
/*  91 */     this.armRightInner.func_78787_b(64, 64);
/*  92 */     this.armRightInner.field_78809_i = true;
/*  93 */     setRotation(this.armRightInner, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  97 */     model.field_78795_f = x;
/*  98 */     model.field_78796_g = y;
/*  99 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockFetish.TileEntityFetish tile) {
/* 103 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 104 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 105 */     this.poleVertical.func_78785_a(f5);
/* 106 */     this.poleHorizontal.func_78785_a(f5);
/* 107 */     this.headInner.func_78785_a(f5);
/* 108 */     this.armLeftInner.func_78785_a(f5);
/* 109 */     this.armRightInner.func_78785_a(f5);
/*     */     
/* 111 */     int colorIndex = 9;
/* 112 */     float alpha = 1.0F;
/* 113 */     if (tile != null) {
/* 114 */       int color = tile.getColor();
/* 115 */       if ((color >= 0) && (color <= 15)) {
/* 116 */         colorIndex = color;
/*     */       }
/* 118 */       if (tile.isSpectral()) {
/* 119 */         alpha = 0.7F;
/*     */       }
/*     */     }
/*     */     
/* 123 */     GL11.glColor4f(ModelBroom.fleeceColorTable[colorIndex][0], ModelBroom.fleeceColorTable[colorIndex][1], ModelBroom.fleeceColorTable[colorIndex][2], alpha);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 128 */     this.head.func_78785_a(f5);
/* 129 */     this.body.func_78785_a(f5);
/* 130 */     this.armLeft.func_78785_a(f5);
/* 131 */     this.armRight.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 136 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelFetishScarecrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */