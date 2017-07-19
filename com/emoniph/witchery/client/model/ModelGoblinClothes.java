/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelGoblinClothes extends ModelBiped
/*    */ {
/*    */   ModelRenderer head;
/*    */   ModelRenderer body;
/*    */   ModelRenderer rightarm;
/*    */   ModelRenderer leftarm;
/*    */   ModelRenderer rightleg;
/*    */   ModelRenderer leftleg;
/*    */   ModelRenderer quiver;
/*    */   ModelRenderer arrow1;
/*    */   ModelRenderer feathers;
/*    */   ModelRenderer arrow2;
/*    */   
/*    */   public ModelGoblinClothes(float scale)
/*    */   {
/* 24 */     super(scale, 0.0F, 64, 32);
/*    */     
/* 26 */     this.field_78090_t = 64;
/* 27 */     this.field_78089_u = 32;
/*    */     
/* 29 */     this.quiver = new ModelRenderer(this, 33, 0);
/* 30 */     this.quiver.func_78789_a(-2.0F, -3.0F, 0.0F, 4, 7, 1);
/* 31 */     this.quiver.func_78793_a(0.0F, 7.0F, 2.0F);
/* 32 */     this.quiver.func_78787_b(64, 32);
/* 33 */     this.quiver.field_78809_i = true;
/* 34 */     setRotation(this.quiver, 0.0F, 0.0F, -0.3490659F);
/* 35 */     this.arrow1 = new ModelRenderer(this, 44, 4);
/* 36 */     this.arrow1.func_78789_a(0.5F, -5.0F, 0.0F, 1, 2, 1);
/* 37 */     this.arrow1.func_78793_a(0.0F, 7.0F, 2.0F);
/* 38 */     this.arrow1.func_78787_b(64, 32);
/* 39 */     this.arrow1.field_78809_i = true;
/* 40 */     setRotation(this.arrow1, 0.0F, 0.0F, -0.3490659F);
/* 41 */     this.feathers = new ModelRenderer(this, 44, 0);
/* 42 */     this.feathers.func_78789_a(-2.0F, -7.0F, 0.0F, 4, 2, 1);
/* 43 */     this.feathers.func_78793_a(0.0F, 7.0F, 2.0F);
/* 44 */     this.feathers.func_78787_b(64, 32);
/* 45 */     this.feathers.field_78809_i = true;
/* 46 */     setRotation(this.feathers, 0.0F, 0.0F, -0.3490659F);
/* 47 */     this.arrow2 = new ModelRenderer(this, 44, 4);
/* 48 */     this.arrow2.func_78789_a(-1.5F, -5.0F, 0.0F, 1, 2, 1);
/* 49 */     this.arrow2.func_78793_a(0.0F, 7.0F, 2.0F);
/* 50 */     this.arrow2.func_78787_b(64, 32);
/* 51 */     this.arrow2.field_78809_i = true;
/* 52 */     setRotation(this.arrow2, 0.0F, 0.0F, -0.3490659F);
/*    */     
/* 54 */     this.field_78115_e.func_78792_a(this.quiver);
/* 55 */     this.field_78115_e.func_78792_a(this.arrow1);
/* 56 */     this.field_78115_e.func_78792_a(this.arrow2);
/* 57 */     this.field_78115_e.func_78792_a(this.feathers);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 61 */     model.field_78795_f = x;
/* 62 */     model.field_78796_g = y;
/* 63 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 68 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 69 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*    */   {
/* 74 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelGoblinClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */