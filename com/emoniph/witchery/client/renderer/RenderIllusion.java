/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityIllusion;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderIllusion extends RenderLiving
/*    */ {
/*    */   private final ResourceLocation textures;
/*    */   
/*    */   public RenderIllusion(ModelBase model, ResourceLocation resource)
/*    */   {
/* 21 */     super(model, 0.5F);
/* 22 */     this.field_76989_e = 0.0F;
/* 23 */     this.textures = resource;
/*    */   }
/*    */   
/*    */   public void renderLivingIllusion(EntityIllusion illusionEntity, double par2, double par4, double par6, float par8, float par9) {
/* 27 */     if (Minecraft.func_71410_x().field_71439_g.func_70005_c_().equals(illusionEntity.getVictimName())) {
/* 28 */       super.func_76986_a(illusionEntity, par2, par4, par6, par8, par9);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void preRenderIllusion(EntityIllusion illusionEntity, float par2) {
/* 33 */     super.func_77041_b(illusionEntity, par2);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 38 */     renderLivingIllusion((EntityIllusion)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2)
/*    */   {
/* 43 */     preRenderIllusion((EntityIllusion)par1EntityLivingBase, par2);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 48 */     renderLivingIllusion((EntityIllusion)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*    */   {
/* 53 */     return this.textures;
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 58 */     renderLivingIllusion((EntityIllusion)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderIllusion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */