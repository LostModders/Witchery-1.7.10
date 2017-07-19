/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ModelHunterClothes
/*    */   extends ModelBiped
/*    */ {
/*    */   ModelRenderer coat;
/*    */   ModelRenderer hatBrim;
/*    */   ModelRenderer hatTop;
/*    */   ModelRenderer hatMid;
/*    */   
/*    */   public ModelHunterClothes(float scale, boolean shoulders)
/*    */   {
/* 21 */     super(scale, 0.0F, 128, 64);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 36 */     float hatScale = 0.52F;
/* 37 */     this.hatBrim = new ModelRenderer(this, 0, 50);
/* 38 */     this.hatBrim.func_78790_a(-6.5F, 0.0F, -6.5F, 13, 1, 13, hatScale - 0.2F);
/* 39 */     this.hatBrim.func_78793_a(0.0F, -6.0F, 0.0F);
/* 40 */     this.hatBrim.func_78787_b(128, 64);
/* 41 */     this.hatBrim.field_78809_i = true;
/* 42 */     setRotation(this.hatBrim, 0.0F, 0.0F, 0.0F);
/* 43 */     this.field_78116_c.func_78792_a(this.hatBrim);
/*    */     
/* 45 */     this.hatMid = new ModelRenderer(this, 40, 52);
/* 46 */     this.hatMid.func_78790_a(-4.0F, 0.0F, -4.0F, 8, 2, 8, hatScale);
/* 47 */     this.hatMid.func_78793_a(0.0F, -2.0F, 0.0F);
/* 48 */     this.hatMid.func_78787_b(128, 64);
/* 49 */     this.hatMid.field_78809_i = true;
/* 50 */     setRotation(this.hatMid, 0.0F, 0.0F, 0.0F);
/* 51 */     this.hatBrim.func_78792_a(this.hatMid);
/*    */     
/* 53 */     this.hatTop = new ModelRenderer(this, 12, 41);
/* 54 */     this.hatTop.func_78790_a(-3.5F, 0.0F, -3.5F, 7, 2, 7, hatScale);
/* 55 */     this.hatTop.func_78793_a(0.0F, -2.0F, 0.0F);
/* 56 */     this.hatTop.func_78787_b(128, 64);
/* 57 */     this.hatTop.field_78809_i = true;
/* 58 */     setRotation(this.hatTop, 0.0F, 0.0F, 0.0F);
/* 59 */     this.hatMid.func_78792_a(this.hatTop);
/*    */     
/*    */ 
/* 62 */     this.coat = new ModelRenderer(this, 41, 33);
/* 63 */     this.coat.func_78790_a(-5.5F, 0.0F, -3.0F, 11, 10, 6, -0.3F);
/* 64 */     this.coat.func_78793_a(0.0F, 12.0F, 0.0F);
/* 65 */     this.coat.func_78787_b(128, 64);
/* 66 */     this.coat.field_78809_i = true;
/* 67 */     setRotation(this.coat, 0.0F, 0.0F, 0.0F);
/* 68 */     this.field_78115_e.func_78792_a(this.coat);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 73 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 74 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 78 */     model.field_78795_f = x;
/* 79 */     model.field_78796_g = y;
/* 80 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelHunterClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */