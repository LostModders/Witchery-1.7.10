/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */ public class ModelWolfHead extends ModelBase
/*    */ {
/*    */   public ModelRenderer skeletonHead;
/*    */   
/*    */   public ModelWolfHead()
/*    */   {
/* 14 */     this(0, 35, 64, 32);
/*    */   }
/*    */   
/*    */   public ModelWolfHead(int textureOffsetX, int textureoffsetY, int textureWidth, int textureHeight) {
/* 18 */     this.field_78090_t = textureWidth;
/* 19 */     this.field_78089_u = textureHeight;
/* 20 */     this.skeletonHead = new ModelRenderer(this, 0, 0);
/* 21 */     this.skeletonHead.func_78790_a(-3.0F, -6.0F, 0.0F, 6, 6, 4, 0.0F);
/* 22 */     this.skeletonHead.func_78793_a(0.0F, 0.0F, 0.0F);
/*    */     
/* 24 */     float f = 0.0F;
/* 25 */     this.skeletonHead.func_78784_a(16, 14).func_78790_a(-3.0F, -8.0F, 3.0F, 2, 2, 1, f);
/* 26 */     this.skeletonHead.func_78784_a(16, 14).func_78790_a(1.0F, -8.0F, 3.0F, 2, 2, 1, f);
/* 27 */     this.skeletonHead.func_78784_a(0, 10).func_78790_a(-1.5F, -3.1F, -3.0F, 3, 3, 4, f);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
/*    */   {
/* 32 */     func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
/* 33 */     this.skeletonHead.func_78785_a(p_78088_7_);
/*    */   }
/*    */   
/*    */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*    */   {
/* 38 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 39 */     this.skeletonHead.field_78796_g = (p_78087_4_ / 57.295776F);
/* 40 */     this.skeletonHead.field_78795_f = (p_78087_5_ / 57.295776F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelWolfHead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */