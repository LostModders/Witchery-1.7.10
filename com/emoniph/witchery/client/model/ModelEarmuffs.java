/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */ public class ModelEarmuffs extends ModelBiped
/*    */ {
/*    */   private ModelRenderer earRight;
/*    */   private ModelRenderer earLeft;
/*    */   private ModelRenderer bandLeft;
/*    */   private ModelRenderer bandTop;
/*    */   private ModelRenderer bandRight;
/*    */   
/*    */   public ModelEarmuffs()
/*    */   {
/* 18 */     this.field_78090_t = 64;
/* 19 */     this.field_78089_u = 64;
/*    */     
/* 21 */     this.bandTop = new ModelRenderer(this, 46, 38);
/* 22 */     this.bandTop.func_78789_a(-4.0F, -10.0F, -0.5F, 8, 1, 1);
/* 23 */     this.bandTop.func_78793_a(0.0F, 0.0F, 0.0F);
/* 24 */     this.bandTop.func_78787_b(64, 64);
/* 25 */     this.bandTop.field_78809_i = true;
/* 26 */     setRotation(this.bandTop, 0.0F, 0.0F, 0.0F);
/*    */     
/* 28 */     this.earRight = new ModelRenderer(this, 33, 32);
/* 29 */     this.earRight.func_78789_a(-6.0F, -6.0F, -2.0F, 2, 4, 4);
/* 30 */     this.earRight.func_78793_a(0.0F, 0.0F, 0.0F);
/* 31 */     this.earRight.func_78787_b(64, 64);
/* 32 */     this.earRight.field_78809_i = true;
/* 33 */     setRotation(this.earRight, 0.0F, 0.0F, 0.0F);
/*    */     
/*    */ 
/* 36 */     this.earLeft = new ModelRenderer(this, 33, 32);
/* 37 */     this.earLeft.func_78789_a(4.0F, -6.0F, -2.0F, 2, 4, 4);
/* 38 */     this.earLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/* 39 */     this.earLeft.func_78787_b(64, 64);
/* 40 */     this.earLeft.field_78809_i = true;
/* 41 */     setRotation(this.earLeft, 0.0F, 0.0F, 0.0F);
/*    */     
/*    */ 
/* 44 */     this.bandLeft = new ModelRenderer(this, 46, 32);
/* 45 */     this.bandLeft.func_78789_a(4.0F, -10.0F, -0.5F, 1, 4, 1);
/* 46 */     this.bandLeft.func_78793_a(0.0F, 0.0F, 0.0F);
/* 47 */     this.bandLeft.func_78787_b(64, 64);
/* 48 */     this.bandLeft.field_78809_i = true;
/* 49 */     setRotation(this.bandLeft, 0.0F, 0.0F, 0.0F);
/*    */     
/*    */ 
/* 52 */     this.bandRight = new ModelRenderer(this, 46, 32);
/* 53 */     this.bandRight.func_78789_a(-5.0F, -10.0F, -0.5F, 1, 4, 1);
/* 54 */     this.bandRight.func_78793_a(0.0F, 0.0F, 0.0F);
/* 55 */     this.bandRight.func_78787_b(64, 64);
/* 56 */     this.bandRight.field_78809_i = true;
/* 57 */     setRotation(this.bandRight, 0.0F, 0.0F, 0.0F);
/*    */     
/* 59 */     this.field_78116_c.func_78792_a(this.earRight);
/* 60 */     this.field_78116_c.func_78792_a(this.earLeft);
/* 61 */     this.field_78116_c.func_78792_a(this.bandLeft);
/* 62 */     this.field_78116_c.func_78792_a(this.bandRight);
/* 63 */     this.field_78116_c.func_78792_a(this.bandTop);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 68 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 69 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 73 */     model.field_78795_f = x;
/* 74 */     model.field_78796_g = y;
/* 75 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*    */   {
/* 80 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelEarmuffs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */