/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */ public class ModelWitchHunter extends ModelBiped
/*    */ {
/*    */   ModelRenderer coatBottom;
/*    */   ModelRenderer hatBrim;
/*    */   ModelRenderer hatTop;
/*    */   
/*    */   public ModelWitchHunter()
/*    */   {
/* 16 */     super(0.0F, 0.0F, 64, 64);
/*    */     
/* 18 */     this.coatBottom = new ModelRenderer(this, 1, 47);
/* 19 */     this.coatBottom.func_78789_a(-5.0F, 0.0F, -2.5F, 10, 11, 5);
/* 20 */     this.coatBottom.func_78793_a(0.0F, 11.0F, 0.0F);
/* 21 */     this.coatBottom.func_78787_b(64, 64);
/* 22 */     this.coatBottom.field_78809_i = true;
/* 23 */     setRotation(this.coatBottom, 0.0F, 0.0F, 0.0F);
/* 24 */     this.field_78115_e.func_78792_a(this.coatBottom);
/*    */     
/*    */ 
/* 27 */     this.hatBrim = new ModelRenderer(this, 0, 32);
/* 28 */     this.hatBrim.func_78789_a(-7.0F, 0.0F, -7.0F, 14, 1, 14);
/* 29 */     this.hatBrim.func_78793_a(0.0F, -6.0F, 0.0F);
/* 30 */     this.hatBrim.func_78787_b(64, 64);
/* 31 */     this.hatBrim.field_78809_i = true;
/* 32 */     setRotation(this.hatBrim, 0.0F, 0.0F, 0.0F);
/* 33 */     this.field_78116_c.func_78792_a(this.hatBrim);
/*    */     
/* 35 */     this.hatTop = new ModelRenderer(this, 33, 48);
/* 36 */     this.hatTop.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 2, 6);
/* 37 */     this.hatTop.func_78793_a(0.0F, -10.0F, 0.0F);
/* 38 */     this.hatTop.func_78787_b(64, 64);
/* 39 */     this.hatTop.field_78809_i = true;
/* 40 */     setRotation(this.hatTop, 0.0F, 0.0F, 0.0F);
/* 41 */     this.field_78116_c.func_78792_a(this.hatTop);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 46 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 47 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 51 */     model.field_78795_f = x;
/* 52 */     model.field_78796_g = y;
/* 53 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*    */   {
/* 58 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelWitchHunter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */