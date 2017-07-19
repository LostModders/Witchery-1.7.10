/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelSpirit;
/*    */ import com.emoniph.witchery.entity.EntitySpirit;
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
/*    */ public class RenderSpirit
/*    */   extends RenderLiving
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/spirit.png");
/*    */   
/*    */   public RenderSpirit() {
/* 24 */     super(new ModelSpirit(), 0.0F);
/*    */   }
/*    */   
/*    */   public void doRenderSpirit(EntitySpirit entity, double par2, double par4, double par6, float par8, float par9) {
/* 28 */     GL11.glPushMatrix();
/*    */     
/*    */ 
/*    */ 
/* 32 */     RenderUtil.blend(true);
/*    */     
/* 34 */     int color = entity.getFeatherColor();
/* 35 */     if (color > 0) {
/* 36 */       float red = (color >> 16 & 0xFF) / 255.0F;
/* 37 */       float green = (color >> 8 & 0xFF) / 255.0F;
/* 38 */       float blue = (color & 0xFF) / 255.0F;
/* 39 */       GL11.glColor4f(red, green, blue, 0.6F);
/*    */     } else {
/* 41 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
/*    */     }
/*    */     
/* 44 */     super.func_76986_a(entity, par2, par4, par6, par8, par9);
/* 45 */     RenderUtil.blend(false);
/* 46 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   protected void rotateSpiritCorpse(EntitySpirit entity, float par2, float par3, float par4) {
/* 50 */     super.func_77043_a(entity, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 55 */     doRenderSpirit((EntitySpirit)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase entity, float par2, float par3, float par4)
/*    */   {
/* 60 */     rotateSpiritCorpse((EntitySpirit)entity, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 65 */     doRenderSpirit((EntitySpirit)par1, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 70 */     doRenderSpirit((EntitySpirit)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*    */   {
/* 75 */     return func_110832_a((EntitySpirit)par1Entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntitySpirit par1Entity) {
/* 79 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderSpirit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */