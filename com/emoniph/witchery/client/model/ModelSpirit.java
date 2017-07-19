/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelSpirit extends ModelBase
/*    */ {
/*    */   ModelRenderer Piece1;
/*    */   
/*    */   public ModelSpirit()
/*    */   {
/* 16 */     this.field_78090_t = 32;
/* 17 */     this.field_78089_u = 32;
/* 18 */     func_78085_a("Piece1.Shape1", 2, 5);
/* 19 */     func_78085_a("Piece1.Shape2", 2, 21);
/* 20 */     func_78085_a("Piece1.Shape3", 0, 12);
/* 21 */     func_78085_a("Piece1.Shape4", 6, 0);
/* 22 */     func_78085_a("Piece1.Shape5", 6, 28);
/*    */     
/* 24 */     this.Piece1 = new ModelRenderer(this, "Piece1");
/* 25 */     this.Piece1.func_78793_a(0.0F, 21.0F, 0.0F);
/* 26 */     setRotation(this.Piece1, 0.0F, 0.0F, 0.0F);
/* 27 */     this.Piece1.field_78809_i = true;
/* 28 */     this.Piece1.func_78786_a("Shape1", -2.5F, -2.0F, -2.5F, 5, 1, 5);
/* 29 */     this.Piece1.func_78786_a("Shape2", -2.5F, 1.0F, -2.5F, 5, 1, 5);
/* 30 */     this.Piece1.func_78786_a("Shape3", -3.0F, -1.0F, -3.0F, 6, 2, 6);
/* 31 */     this.Piece1.func_78786_a("Shape4", -1.5F, -3.0F, -1.5F, 3, 1, 3);
/* 32 */     this.Piece1.func_78786_a("Shape5", -1.5F, 2.0F, -1.5F, 3, 1, 3);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 40 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 41 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 42 */     float SCALE = 0.5F;
/* 43 */     GL11.glTranslatef(0.0F, 0.65F, 0.0F);
/* 44 */     GL11.glScalef(SCALE, SCALE, SCALE);
/* 45 */     this.Piece1.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 49 */     model.field_78795_f = x;
/* 50 */     model.field_78796_g = y;
/* 51 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 55 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelSpirit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */