/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelLouse;
/*    */ import com.emoniph.witchery.entity.EntityParasyticLouse;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderParasyticLouse
/*    */   extends RenderLiving
/*    */ {
/* 18 */   private static final ResourceLocation LouseTextures = new ResourceLocation("witchery", "textures/entities/louse.png");
/*    */   
/*    */   public RenderParasyticLouse() {
/* 21 */     super(new ModelLouse(), 0.3F);
/*    */   }
/*    */   
/*    */   protected float getLouseDeathRotation(EntityParasyticLouse par1EntityLouse) {
/* 25 */     return 180.0F;
/*    */   }
/*    */   
/*    */   public void renderLouse(EntityParasyticLouse par1EntityLouse, double par2, double par4, double par6, float par8, float par9) {
/* 29 */     super.func_76986_a(par1EntityLouse, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation getLouseTextures(EntityParasyticLouse par1EntityLouse) {
/* 33 */     return LouseTextures;
/*    */   }
/*    */   
/*    */   protected int shouldLouseRenderPass(EntityParasyticLouse par1EntityLouse, int par2, float par3) {
/* 37 */     return -1;
/*    */   }
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 41 */     renderLouse((EntityParasyticLouse)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected float func_77037_a(EntityLivingBase par1EntityLivingBase) {
/* 45 */     return getLouseDeathRotation((EntityParasyticLouse)par1EntityLivingBase);
/*    */   }
/*    */   
/*    */   protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 49 */     return shouldLouseRenderPass((EntityParasyticLouse)par1EntityLivingBase, par2, par3);
/*    */   }
/*    */   
/*    */   public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 53 */     renderLouse((EntityParasyticLouse)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity) {
/* 57 */     return getLouseTextures((EntityParasyticLouse)par1Entity);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 61 */     renderLouse((EntityParasyticLouse)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderParasyticLouse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */