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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelGrotesque
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer head;
/*     */   
/*     */   public ModelGrotesque()
/*     */   {
/*  28 */     this.field_78090_t = 128;
/*  29 */     this.field_78089_u = 32;
/*     */     
/*  31 */     func_78085_a("head.face", 0, 0);
/*  32 */     func_78085_a("head.leftHorn", 0, 16);
/*  33 */     func_78085_a("head.rightHorn", 0, 16);
/*  34 */     func_78085_a("head.leftTusk", 4, 16);
/*  35 */     func_78085_a("head.rightTusk", 4, 16);
/*  36 */     func_78085_a("head.snout", 20, 16);
/*  37 */     func_78085_a("head.bottomLip", 8, 16);
/*  38 */     this.head = new ModelRenderer(this, "head");
/*  39 */     this.head.func_78784_a(0, 0);
/*  40 */     this.head.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  41 */     this.head.func_78786_a("leftHorn", 4.0F, -12.0F, -0.5F, 1, 8, 1);
/*  42 */     this.head.func_78786_a("rightHorn", -5.0F, -12.0F, -0.5F, 1, 8, 1);
/*  43 */     this.head.func_78786_a("leftTusk", 1.0F, -4.0F, -5.0F, 1, 2, 1);
/*  44 */     this.head.func_78786_a("bottomLip", -2.0F, -2.0F, -6.0F, 4, 1, 2);
/*  45 */     this.head.func_78786_a("snout", -1.0F, -6.0F, -6.0F, 2, 3, 2);
/*  46 */     this.head.func_78786_a("rightTusk", -2.0F, -4.0F, -5.0F, 1, 2, 1);
/*  47 */     this.head.func_78793_a(0.0F, -9.0F, 0.0F);
/*  48 */     this.head.func_78787_b(128, 32);
/*  49 */     this.head.field_78809_i = true;
/*     */   }
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
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/*  99 */     model.field_78795_f = x;
/* 100 */     model.field_78796_g = y;
/* 101 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 108 */     GL11.glTranslatef(0.0F, 0.735F, 0.0F);
/* 109 */     float scale = 1.3F;
/* 110 */     GL11.glScalef(scale, scale, scale);
/* 111 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 116 */     this.head.func_78785_a(f5);
/*     */   }
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
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 138 */     this.head.field_78796_g = (par4 / 57.295776F);
/* 139 */     this.head.field_78795_f = (par5 / 57.295776F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGrotesque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */