/*     */ package com.emoniph.witchery.client.model;
/*     */ 
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelCrystalBall
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer baseBottom;
/*     */   ModelRenderer baseMiddle;
/*     */   ModelRenderer baseTop;
/*     */   ModelRenderer globeInner;
/*     */   ModelRenderer globeMiddle;
/*     */   ModelRenderer globeOuter;
/*     */   
/*     */   public ModelCrystalBall()
/*     */   {
/*  26 */     this.field_78090_t = 32;
/*  27 */     this.field_78089_u = 32;
/*     */     
/*  29 */     this.baseBottom = new ModelRenderer(this, 0, 25);
/*  30 */     this.baseBottom.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 6);
/*  31 */     this.baseBottom.func_78793_a(-3.0F, 23.0F, -3.0F);
/*  32 */     this.baseBottom.func_78787_b(32, 32);
/*  33 */     this.baseBottom.field_78809_i = true;
/*  34 */     setRotation(this.baseBottom, 0.0F, 0.0F, 0.0F);
/*  35 */     this.baseMiddle = new ModelRenderer(this, 0, 20);
/*  36 */     this.baseMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 4, 1, 4);
/*  37 */     this.baseMiddle.func_78793_a(-2.0F, 22.0F, -2.0F);
/*  38 */     this.baseMiddle.func_78787_b(32, 32);
/*  39 */     this.baseMiddle.field_78809_i = true;
/*  40 */     setRotation(this.baseMiddle, 0.0F, 0.0F, 0.0F);
/*  41 */     this.baseTop = new ModelRenderer(this, 0, 17);
/*  42 */     this.baseTop.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 2);
/*  43 */     this.baseTop.func_78793_a(-1.0F, 21.0F, -1.0F);
/*  44 */     this.baseTop.func_78787_b(32, 32);
/*  45 */     this.baseTop.field_78809_i = true;
/*  46 */     setRotation(this.baseTop, 0.0F, 0.0F, 0.0F);
/*  47 */     this.globeInner = new ModelRenderer(this, 4, 0);
/*  48 */     this.globeInner.func_78789_a(0.0F, 0.0F, 0.0F, 2, 2, 2);
/*  49 */     this.globeInner.func_78793_a(-1.0F, 17.0F, -1.0F);
/*  50 */     this.globeInner.func_78787_b(32, 32);
/*  51 */     this.globeInner.field_78809_i = true;
/*  52 */     setRotation(this.globeInner, 0.0F, 0.0F, 0.0F);
/*  53 */     this.globeMiddle = new ModelRenderer(this, 12, 0);
/*  54 */     this.globeMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
/*  55 */     this.globeMiddle.func_78793_a(-2.0F, 16.0F, -2.0F);
/*  56 */     this.globeMiddle.func_78787_b(32, 32);
/*  57 */     this.globeMiddle.field_78809_i = true;
/*  58 */     setRotation(this.globeMiddle, 0.0F, 0.0F, 0.0F);
/*  59 */     this.globeOuter = new ModelRenderer(this, 8, 8);
/*  60 */     this.globeOuter.func_78789_a(0.0F, 0.0F, 0.0F, 6, 6, 6);
/*  61 */     this.globeOuter.func_78793_a(-3.0F, 15.0F, -3.0F);
/*  62 */     this.globeOuter.func_78787_b(32, 32);
/*  63 */     this.globeOuter.field_78809_i = true;
/*  64 */     setRotation(this.globeOuter, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  68 */     model.field_78795_f = x;
/*  69 */     model.field_78796_g = y;
/*  70 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tile) {
/*  74 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  75 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  76 */     this.baseBottom.func_78785_a(f5);
/*  77 */     this.baseMiddle.func_78785_a(f5);
/*  78 */     this.baseTop.func_78785_a(f5);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */     RenderUtil.blend(true);
/*  85 */     if ((tile != null) && (tile.func_145831_w() != null)) {
/*  86 */       long time = tile.func_145831_w().func_72820_D();
/*  87 */       long scale = 100L - Math.abs(time % 160L - 80L);
/*     */       
/*  89 */       GL11.glColor3f(0.01F * (float)scale, 0.01F * (float)scale, 0.01F * (float)scale);
/*     */     }
/*  91 */     this.globeInner.func_78785_a(f5);
/*  92 */     GL11.glColor3f(0.8F, 0.8F, 1.0F);
/*  93 */     this.globeMiddle.func_78785_a(f5);
/*  94 */     this.globeOuter.func_78785_a(f5);
/*     */     
/*  96 */     RenderUtil.blend(false);
/*     */   }
/*     */   
/*     */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*     */   {
/* 101 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelCrystalBall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */