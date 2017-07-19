/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelPoltergeist;
/*    */ import com.emoniph.witchery.entity.EntityPoltergeist;
/*    */ import com.emoniph.witchery.util.RenderUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderPoltergeist
/*    */   extends RenderLiving
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/poltergeist.png");
/*    */   
/*    */   public RenderPoltergeist() {
/* 24 */     super(new ModelPoltergeist(), 0.0F);
/*    */   }
/*    */   
/*    */   public void doRenderNightmare(EntityPoltergeist entity, double par2, double par4, double par6, float par8, float par9) {
/* 28 */     GL11.glPushMatrix();
/* 29 */     RenderUtil.blend(true);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 34 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
/*    */     
/* 36 */     super.func_76986_a(entity, par2, par4, par6, par8, par9);
/* 37 */     RenderUtil.blend(false);
/* 38 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   protected void rotateNightmareCorpse(EntityPoltergeist entity, float par2, float par3, float par4) {
/* 42 */     super.func_77043_a(entity, par2, par3, par4);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 54 */     doRenderNightmare((EntityPoltergeist)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase entity, float par2, float par3, float par4)
/*    */   {
/* 59 */     rotateNightmareCorpse((EntityPoltergeist)entity, par2, par3, par4);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 71 */     doRenderNightmare((EntityPoltergeist)par1, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 76 */     doRenderNightmare((EntityPoltergeist)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*    */   {
/* 81 */     return func_110832_a((EntityPoltergeist)par1Entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntityPoltergeist par1Entity) {
/* 85 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderPoltergeist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */