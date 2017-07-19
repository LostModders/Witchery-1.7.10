/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelLeechChest extends net.minecraft.client.model.ModelBase
/*    */ {
/*    */   public ModelRenderer chestBelow;
/*    */   public ModelRenderer chestLidBL;
/*    */   public ModelRenderer chestLidFR;
/*    */   public ModelRenderer chestLidBR;
/*    */   public ModelRenderer chestLidFL;
/*    */   public ModelRenderer sac1;
/*    */   public ModelRenderer sac2;
/*    */   public ModelRenderer sac3;
/*    */   
/*    */   public ModelLeechChest()
/*    */   {
/* 20 */     this.field_78090_t = 64;
/* 21 */     this.field_78089_u = 64;
/*    */     
/* 23 */     this.chestBelow = new ModelRenderer(this, 0, 0);
/* 24 */     this.chestBelow.func_78789_a(0.0F, 0.0F, 0.0F, 14, 9, 14);
/* 25 */     this.chestBelow.func_78793_a(1.0F, 7.0F, 1.0F);
/* 26 */     this.chestBelow.func_78787_b(64, 64);
/* 27 */     this.chestBelow.field_78809_i = true;
/* 28 */     setRotation(this.chestBelow, 0.0F, 0.0F, 0.0F);
/* 29 */     this.chestLidBL = new ModelRenderer(this, 28, 24);
/* 30 */     this.chestLidBL.func_78789_a(-6.0F, -5.0F, -6.0F, 6, 5, 6);
/* 31 */     this.chestLidBL.func_78793_a(14.0F, 7.0F, 14.0F);
/* 32 */     this.chestLidBL.func_78787_b(64, 64);
/* 33 */     this.chestLidBL.field_78809_i = true;
/* 34 */     setRotation(this.chestLidBL, 0.0F, 0.0F, 0.0F);
/* 35 */     this.chestLidFR = new ModelRenderer(this, 0, 36);
/* 36 */     this.chestLidFR.func_78789_a(0.0F, -5.0F, 0.0F, 6, 5, 6);
/* 37 */     this.chestLidFR.func_78793_a(2.0F, 7.0F, 2.0F);
/* 38 */     this.chestLidFR.func_78787_b(64, 64);
/* 39 */     this.chestLidFR.field_78809_i = true;
/* 40 */     setRotation(this.chestLidFR, 0.0F, 0.0F, 0.0F);
/* 41 */     this.chestLidBR = new ModelRenderer(this, 0, 24);
/* 42 */     this.chestLidBR.func_78789_a(0.0F, -5.0F, -6.0F, 6, 5, 6);
/* 43 */     this.chestLidBR.func_78793_a(2.0F, 7.0F, 14.0F);
/* 44 */     this.chestLidBR.func_78787_b(64, 64);
/* 45 */     this.chestLidBR.field_78809_i = true;
/* 46 */     setRotation(this.chestLidBR, 0.0F, 0.0F, 0.0F);
/* 47 */     this.chestLidFL = new ModelRenderer(this, 28, 36);
/* 48 */     this.chestLidFL.func_78789_a(-6.0F, -5.0F, 0.0F, 6, 5, 6);
/* 49 */     this.chestLidFL.func_78793_a(14.0F, 7.0F, 2.0F);
/* 50 */     this.chestLidFL.func_78787_b(64, 64);
/* 51 */     this.chestLidFL.field_78809_i = true;
/* 52 */     setRotation(this.chestLidFL, 0.0F, 0.0F, 0.0F);
/* 53 */     this.sac1 = new ModelRenderer(this, 0, 8);
/* 54 */     this.sac1.func_78789_a(0.0F, 0.0F, 0.0F, 2, 3, 1);
/* 55 */     this.sac1.func_78793_a(3.0F, 8.0F, 0.0F);
/* 56 */     this.sac1.func_78787_b(64, 64);
/* 57 */     this.sac1.field_78809_i = true;
/* 58 */     setRotation(this.sac1, 0.0F, 0.0F, 0.0F);
/* 59 */     this.sac2 = new ModelRenderer(this, 0, 3);
/* 60 */     this.sac2.func_78789_a(0.0F, 0.0F, 0.0F, 3, 2, 1);
/* 61 */     this.sac2.func_78793_a(9.0F, 13.0F, 0.0F);
/* 62 */     this.sac2.func_78787_b(64, 64);
/* 63 */     this.sac2.field_78809_i = true;
/* 64 */     setRotation(this.sac2, 0.0F, 0.0F, 0.0F);
/* 65 */     this.sac3 = new ModelRenderer(this, 0, 0);
/* 66 */     this.sac3.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 1);
/* 67 */     this.sac3.func_78793_a(9.0F, 9.0F, 0.0F);
/* 68 */     this.sac3.func_78787_b(64, 64);
/* 69 */     this.sac3.field_78809_i = true;
/* 70 */     setRotation(this.sac3, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void renderAll(int count) {
/* 74 */     this.chestLidBL.func_78785_a(0.0625F);
/* 75 */     this.chestLidFL.func_78785_a(0.0625F);
/* 76 */     this.chestLidBR.func_78785_a(0.0625F);
/* 77 */     this.chestLidFR.func_78785_a(0.0625F);
/* 78 */     this.chestBelow.func_78785_a(0.0625F);
/* 79 */     if (count >= 1) {
/* 80 */       this.sac1.func_78785_a(0.0625F);
/*    */     }
/* 82 */     if (count >= 2) {
/* 83 */       this.sac2.func_78785_a(0.0625F);
/*    */     }
/* 85 */     if (count >= 3) {
/* 86 */       this.sac3.func_78785_a(0.0625F);
/*    */     }
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 91 */     model.field_78795_f = x;
/* 92 */     model.field_78796_g = y;
/* 93 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelLeechChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */