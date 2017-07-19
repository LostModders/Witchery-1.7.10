/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */ public class ModelPoppetChest extends ModelBase
/*    */ {
/*    */   ModelRenderer table;
/*    */   
/*    */   public ModelPoppetChest()
/*    */   {
/* 14 */     this.field_78090_t = 64;
/* 15 */     this.field_78089_u = 64;
/*    */     
/* 17 */     this.table = new ModelRenderer(this, 0, 0);
/* 18 */     this.table.func_78789_a(0.0F, 0.0F, 0.0F, 16, 8, 16);
/* 19 */     this.table.func_78793_a(0.0F, 0.0F, 0.0F);
/* 20 */     this.table.func_78787_b(64, 64);
/* 21 */     this.table.field_78809_i = true;
/* 22 */     setRotation(this.table, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 27 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 28 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 29 */     this.table.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 33 */     model.field_78795_f = x;
/* 34 */     model.field_78796_g = y;
/* 35 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
/*    */   {
/* 40 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelPoppetChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */