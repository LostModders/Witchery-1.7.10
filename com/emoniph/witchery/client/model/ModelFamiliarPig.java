/*    */ package com.emoniph.witchery.client.model;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityFamiliar;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.client.model.ModelPig;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
/*    */ public class ModelFamiliarPig extends ModelPig
/*    */ {
/*    */   public ModelFamiliarPig()
/*    */   {
/* 16 */     this(0.0F);
/*    */   }
/*    */   
/*    */   public ModelFamiliarPig(float par1) {
/* 20 */     super(par1);
/*    */   }
/*    */   
/*    */   public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 24 */     EntityFamiliar entityocelot = (EntityFamiliar)par1EntityLivingBase;
/*    */     
/* 26 */     this.field_78150_a.func_78793_a(0.0F, 12.0F, -6.0F);
/* 27 */     this.field_78148_b.func_78793_a(0.0F, 11.0F, 2.0F);
/* 28 */     this.field_78149_c.func_78793_a(-3.0F, 18.0F, 7.0F);
/* 29 */     this.field_78146_d.func_78793_a(3.0F, 18.0F, 7.0F);
/* 30 */     this.field_78147_e.func_78793_a(-3.0F, 18.0F, -5.0F);
/* 31 */     this.field_78144_f.func_78793_a(3.0F, 18.0F, -5.0F);
/*    */     
/* 33 */     if (entityocelot.func_70906_o()) {
/* 34 */       this.field_78148_b.field_78795_f = 0.7853982F;
/* 35 */       this.field_78148_b.field_78797_d += 3.5F;
/* 36 */       this.field_78148_b.field_78798_e += 0.0F;
/*    */       
/*    */ 
/* 39 */       this.field_78149_c.field_78795_f = (this.field_78146_d.field_78795_f = -0.15707964F);
/* 40 */       this.field_78149_c.field_78797_d = (this.field_78146_d.field_78797_d = 15.8F);
/* 41 */       this.field_78149_c.field_78798_e = (this.field_78146_d.field_78798_e = -7.0F);
/* 42 */       this.field_78147_e.field_78795_f = (this.field_78144_f.field_78795_f = -1.5707964F);
/* 43 */       this.field_78147_e.field_78797_d = (this.field_78144_f.field_78797_d = 21.0F);
/* 44 */       this.field_78147_e.field_78798_e = (this.field_78144_f.field_78798_e = 1.0F);
/* 45 */       this.field_78163_i = 3;
/*    */     } else {
/* 47 */       this.field_78163_i = 1;
/*    */     }
/*    */   }
/*    */   
/* 51 */   int field_78163_i = 1;
/*    */   
/*    */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
/* 54 */     this.field_78150_a.field_78795_f = (par5 / 57.295776F);
/* 55 */     this.field_78150_a.field_78796_g = (par4 / 57.295776F);
/*    */     
/* 57 */     if (this.field_78163_i != 3) {
/* 58 */       this.field_78148_b.field_78795_f = 1.5707964F;
/*    */       
/* 60 */       this.field_78147_e.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.0F * par2);
/* 61 */       this.field_78144_f.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.0F * par2);
/* 62 */       this.field_78149_c.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.0F * par2);
/* 63 */       this.field_78146_d.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.0F * par2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/model/ModelFamiliarPig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */