/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockChalice.TileEntityChalice;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ModelChalice extends ModelBase
/*    */ {
/*    */   ModelRenderer chalice;
/*    */   ModelRenderer liquid;
/*    */   
/*    */   public ModelChalice()
/*    */   {
/* 18 */     this.field_78090_t = 32;
/* 19 */     this.field_78089_u = 32;
/* 20 */     func_78085_a("chalice.sideRight", 0, -5);
/* 21 */     func_78085_a("chalice.sideLeft", 0, -5);
/* 22 */     func_78085_a("chalice.sideBack", 0, 0);
/* 23 */     func_78085_a("chalice.sideFront", 0, 0);
/* 24 */     func_78085_a("chalice.sideBottom", -5, 4);
/* 25 */     func_78085_a("chalice.neck", 4, 10);
/* 26 */     func_78085_a("chalice.base", 0, 13);
/*    */     
/* 28 */     this.chalice = new ModelRenderer(this, "chalice");
/* 29 */     this.chalice.func_78793_a(-1.0F, 23.0F, -1.0F);
/* 30 */     setRotation(this.chalice, 0.0F, 0.0F, 0.0F);
/* 31 */     this.chalice.field_78809_i = true;
/* 32 */     this.chalice.func_78786_a("sideRight", 4.0F, -6.0F, -1.0F, 0, 4, 5);
/* 33 */     this.chalice.func_78786_a("sideLeft", -1.0F, -6.0F, -1.0F, 0, 4, 5);
/* 34 */     this.chalice.func_78786_a("sideBack", -1.0F, -6.0F, 4.0F, 5, 4, 0);
/* 35 */     this.chalice.func_78786_a("sideFront", -1.0F, -6.0F, -1.0F, 5, 4, 0);
/* 36 */     this.chalice.func_78786_a("sideBottom", -1.0F, -2.0F, -1.0F, 5, 0, 5);
/* 37 */     this.chalice.func_78786_a("neck", 1.0F, -2.0F, 1.0F, 1, 2, 1);
/* 38 */     this.chalice.func_78786_a("base", 0.0F, 0.0F, 0.0F, 3, 1, 3);
/* 39 */     this.liquid = new ModelRenderer(this, -4, 18);
/* 40 */     this.liquid.func_78789_a(0.0F, 0.0F, 0.0F, 5, 0, 5);
/* 41 */     this.liquid.func_78793_a(-2.0F, 19.0F, -2.0F);
/* 42 */     this.liquid.func_78787_b(32, 32);
/* 43 */     this.liquid.field_78809_i = true;
/* 44 */     setRotation(this.liquid, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockChalice.TileEntityChalice tileEntityChalice) {
/* 48 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 49 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 50 */     this.chalice.func_78785_a(f5);
/* 51 */     if ((tileEntityChalice != null) && (tileEntityChalice.isFilled())) {
/* 52 */       this.liquid.func_78785_a(f5);
/*    */     }
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 57 */     model.field_78795_f = x;
/* 58 */     model.field_78796_g = y;
/* 59 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*    */   {
/* 64 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelChalice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */