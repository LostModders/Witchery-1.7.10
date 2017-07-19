/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelBrewBottle extends ModelBase
/*    */ {
/*    */   private ModelRenderer Bottle;
/*    */   private ModelRenderer Stopper;
/*    */   
/*    */   public ModelBrewBottle()
/*    */   {
/* 17 */     this.field_78090_t = 32;
/* 18 */     this.field_78089_u = 32;
/* 19 */     func_78085_a("Bottle.BodyOuter", 0, 14);
/* 20 */     func_78085_a("Bottle.BodyInner", 2, 8);
/* 21 */     func_78085_a("Bottle.Neck", 4, 4);
/* 22 */     func_78085_a("Bottle.Stopper", 2, 0);
/*    */     
/* 24 */     this.Bottle = new ModelRenderer(this, "Bottle");
/* 25 */     this.Bottle.func_78793_a(0.0F, 0.0F, 0.0F);
/* 26 */     setRotation(this.Bottle, 0.0F, 0.0F, 0.0F);
/* 27 */     this.Bottle.field_78809_i = true;
/* 28 */     this.Bottle.func_78786_a("BodyOuter", -1.5F, -2.0F, -1.5F, 3, 2, 3);
/* 29 */     this.Bottle.func_78786_a("BodyInner", -1.0F, -2.5F, -1.0F, 2, 3, 2);
/* 30 */     this.Bottle.func_78786_a("Neck", -0.5F, -4.0F, -0.5F, 1, 2, 1);
/*    */     
/*    */ 
/* 33 */     this.Stopper = new ModelRenderer(this, 2, 0);
/* 34 */     this.Stopper.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 2);
/* 35 */     this.Stopper.func_78787_b(32, 32);
/* 36 */     this.Stopper.func_78793_a(-1.0F, -4.5F, -1.0F);
/* 37 */     setRotation(this.Stopper, 0.0F, 0.0F, 0.0F);
/* 38 */     this.Stopper.field_78809_i = true;
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 43 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 44 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 45 */     this.Bottle.func_78785_a(f5);
/* 46 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 47 */     this.Stopper.func_78785_a(f5);
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


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelBrewBottle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */