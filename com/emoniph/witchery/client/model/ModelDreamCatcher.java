/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.blocks.BlockDreamCatcher.TileEntityDreamCatcher;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import com.emoniph.witchery.item.ItemGeneral.DreamWeave;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelDreamCatcher extends ModelBase
/*    */ {
/*    */   final ModelRenderer frameLeft;
/*    */   final ModelRenderer frameRight;
/*    */   final ModelRenderer frameTop;
/*    */   final ModelRenderer frameBottom;
/*    */   final ModelRenderer[] nets;
/*    */   final ModelRenderer decoration;
/*    */   
/*    */   public ModelDreamCatcher()
/*    */   {
/* 26 */     this.field_78090_t = 32;
/* 27 */     this.field_78089_u = 32;
/*    */     
/* 29 */     this.frameLeft = new ModelRenderer(this, 0, 2);
/* 30 */     this.frameLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 8, 1);
/* 31 */     this.frameLeft.func_78793_a(-4.0F, 10.0F, 7.0F);
/* 32 */     this.frameLeft.func_78787_b(32, 32);
/* 33 */     this.frameLeft.field_78809_i = true;
/* 34 */     setRotation(this.frameLeft, 0.0F, 0.0F, 0.0F);
/* 35 */     this.frameRight = new ModelRenderer(this, 0, 2);
/* 36 */     this.frameRight.func_78789_a(0.0F, 0.0F, 0.0F, 1, 8, 1);
/* 37 */     this.frameRight.func_78793_a(3.0F, 10.0F, 7.0F);
/* 38 */     this.frameRight.func_78787_b(32, 32);
/* 39 */     this.frameRight.field_78809_i = true;
/* 40 */     setRotation(this.frameRight, 0.0F, 0.0F, 0.0F);
/* 41 */     this.frameTop = new ModelRenderer(this, 0, 0);
/* 42 */     this.frameTop.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 1);
/* 43 */     this.frameTop.func_78793_a(-3.0F, 10.0F, 7.0F);
/* 44 */     this.frameTop.func_78787_b(32, 32);
/* 45 */     this.frameTop.field_78809_i = true;
/* 46 */     setRotation(this.frameTop, 0.0F, 0.0F, 0.0F);
/* 47 */     this.frameBottom = new ModelRenderer(this, 0, 0);
/* 48 */     this.frameBottom.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 1);
/* 49 */     this.frameBottom.func_78793_a(-3.0F, 17.0F, 7.0F);
/* 50 */     this.frameBottom.func_78787_b(32, 32);
/* 51 */     this.frameBottom.field_78809_i = true;
/* 52 */     setRotation(this.frameBottom, 0.0F, 0.0F, 0.0F);
/*    */     
/* 54 */     this.nets = new ModelRenderer[Witchery.Items.GENERIC.weaves.size()];
/* 55 */     for (int i = 0; i < Witchery.Items.GENERIC.weaves.size(); i++) {
/* 56 */       ItemGeneral.DreamWeave weave = (ItemGeneral.DreamWeave)Witchery.Items.GENERIC.weaves.get(i);
/* 57 */       this.nets[i] = new ModelRenderer(this, weave.textureOffsetX, weave.textureOffsetY);
/* 58 */       this.nets[i].func_78789_a(0.0F, 0.0F, 0.0F, 6, 6, 0);
/* 59 */       this.nets[i].func_78793_a(-3.0F, 11.0F, 8.0F);
/* 60 */       this.nets[i].func_78787_b(32, 32);
/* 61 */       this.nets[i].field_78809_i = true;
/* 62 */       setRotation(this.nets[i], 0.0F, 0.0F, 0.0F);
/*    */     }
/*    */     
/* 65 */     this.decoration = new ModelRenderer(this, 0, 12);
/* 66 */     this.decoration.func_78789_a(0.0F, 0.0F, 0.0F, 8, 6, 0);
/* 67 */     this.decoration.func_78793_a(-4.0F, 18.0F, 7.0F);
/* 68 */     this.decoration.func_78787_b(32, 32);
/* 69 */     this.decoration.field_78809_i = true;
/* 70 */     setRotation(this.decoration, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockDreamCatcher.TileEntityDreamCatcher tileEntity) {
/* 74 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 75 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 76 */     this.frameLeft.func_78785_a(f5);
/* 77 */     this.frameRight.func_78785_a(f5);
/* 78 */     this.frameTop.func_78785_a(f5);
/* 79 */     this.frameBottom.func_78785_a(f5);
/*    */     
/* 81 */     ItemGeneral.DreamWeave weave = tileEntity.getWeave();
/* 82 */     if (weave != null) {
/* 83 */       this.nets[weave.weaveID].func_78785_a(f5);
/*    */     }
/*    */     
/* 86 */     this.decoration.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 90 */     model.field_78795_f = x;
/* 91 */     model.field_78796_g = y;
/* 92 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 96 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelDreamCatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */