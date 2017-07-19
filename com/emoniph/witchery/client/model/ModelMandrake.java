/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityMandrake;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class ModelMandrake extends ModelBase
/*    */ {
/*    */   ModelRenderer head;
/*    */   ModelRenderer body;
/*    */   ModelRenderer rightarm;
/*    */   ModelRenderer leftarm;
/*    */   ModelRenderer rightleg;
/*    */   ModelRenderer leftleg;
/*    */   
/*    */   public ModelMandrake()
/*    */   {
/* 23 */     this.field_78090_t = 64;
/* 24 */     this.field_78089_u = 32;
/*    */     
/* 26 */     func_78085_a("head.face", 0, 8);
/* 27 */     func_78085_a("head.leaves", 0, 0);
/* 28 */     func_78085_a("body.bodyChest", 21, 0);
/* 29 */     func_78085_a("body.bodyBelly", 17, 7);
/* 30 */     this.head = new ModelRenderer(this, "head");
/* 31 */     this.head.func_78793_a(0.0F, 16.0F, 0.0F);
/* 32 */     setRotation(this.head, 0.0F, 0.0F, 0.0F);
/* 33 */     this.head.field_78809_i = true;
/* 34 */     this.head.func_78786_a("face", -2.0F, -4.0F, -2.0F, 4, 4, 4);
/* 35 */     this.head.func_78786_a("leaves", -4.0F, -12.0F, 0.0F, 8, 8, 0);
/* 36 */     this.body = new ModelRenderer(this, "body");
/* 37 */     this.body.func_78793_a(0.0F, 16.0F, 0.0F);
/* 38 */     setRotation(this.body, 0.0F, 0.0F, 0.0F);
/* 39 */     this.body.field_78809_i = true;
/* 40 */     this.body.func_78786_a("bodyChest", -2.5F, 0.0F, -2.5F, 5, 2, 5);
/* 41 */     this.body.func_78786_a("bodyBelly", -3.5F, 2.0F, -3.5F, 7, 3, 7);
/* 42 */     this.rightarm = new ModelRenderer(this, 37, 0);
/* 43 */     this.rightarm.func_78789_a(-1.0F, 0.0F, -0.5F, 1, 3, 1);
/* 44 */     this.rightarm.func_78793_a(-2.0F, 17.0F, 0.0F);
/* 45 */     this.rightarm.func_78787_b(64, 32);
/* 46 */     this.rightarm.field_78809_i = true;
/* 47 */     setRotation(this.rightarm, 0.0F, 0.0F, 1.047198F);
/* 48 */     this.leftarm = new ModelRenderer(this, 37, 0);
/* 49 */     this.leftarm.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 1);
/* 50 */     this.leftarm.func_78793_a(2.0F, 17.0F, 0.0F);
/* 51 */     this.leftarm.func_78787_b(64, 32);
/* 52 */     this.leftarm.field_78809_i = true;
/* 53 */     setRotation(this.leftarm, 0.0F, 0.0F, -1.047198F);
/* 54 */     this.rightleg = new ModelRenderer(this, 27, 18);
/* 55 */     this.rightleg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/* 56 */     this.rightleg.func_78793_a(-1.0F, 21.0F, 0.0F);
/* 57 */     this.rightleg.func_78787_b(64, 32);
/* 58 */     this.rightleg.field_78809_i = true;
/* 59 */     setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
/* 60 */     this.leftleg = new ModelRenderer(this, 27, 18);
/* 61 */     this.leftleg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/* 62 */     this.leftleg.func_78793_a(1.0F, 21.0F, 0.0F);
/* 63 */     this.leftleg.func_78787_b(64, 32);
/* 64 */     this.leftleg.field_78809_i = true;
/* 65 */     setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 70 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 71 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/* 72 */     this.head.func_78785_a(f5);
/* 73 */     this.body.func_78785_a(f5);
/* 74 */     this.rightarm.func_78785_a(f5);
/* 75 */     this.leftarm.func_78785_a(f5);
/* 76 */     this.rightleg.func_78785_a(f5);
/* 77 */     this.leftleg.func_78785_a(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 81 */     model.field_78795_f = x;
/* 82 */     model.field_78796_g = y;
/* 83 */     model.field_78808_h = z;
/*    */   }
/*    */   
/*    */   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 87 */     super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*    */   }
/*    */   
/*    */   public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*    */   {
/* 92 */     EntityMandrake entityDemon = (EntityMandrake)par1EntityLiving;
/*    */     
/* 94 */     this.rightarm.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3);
/* 95 */     this.leftarm.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3);
/*    */   }
/*    */   
/*    */   private float func_78172_a(float par1, float par2) {
/* 99 */     return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelMandrake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */