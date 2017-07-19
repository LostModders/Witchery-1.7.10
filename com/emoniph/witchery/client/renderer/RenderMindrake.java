/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelMindrake;
/*    */ import com.emoniph.witchery.entity.EntityMindrake;
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
/*    */ public class RenderMindrake
/*    */   extends RenderLiving
/*    */ {
/* 20 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/mindrake.png");
/*    */   
/*    */   public RenderMindrake() {
/* 23 */     super(new ModelMindrake(), 0.25F);
/*    */   }
/*    */   
/*    */   public void doRenderMandrake(EntityMindrake entity, double par2, double par4, double par6, float par8, float par9) {
/* 27 */     super.func_76986_a(entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void rotateMandrakeCorpse(EntityMindrake entity, float par2, float par3, float par4) {
/* 31 */     super.func_77043_a(entity, par2, par3, par4);
/*    */     
/* 33 */     if (entity.field_70721_aZ >= 0.01D) {
/* 34 */       float f3 = 13.0F;
/* 35 */       float f4 = entity.field_70754_ba - entity.field_70721_aZ * (1.0F - par4) + 6.0F;
/* 36 */       float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
/* 37 */       GL11.glRotatef(6.5F * f5, 0.0F, 0.0F, 1.0F);
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 43 */     doRenderMandrake((EntityMindrake)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
/*    */   {
/* 48 */     rotateMandrakeCorpse((EntityMindrake)par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 53 */     doRenderMandrake((EntityMindrake)par1, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 58 */     doRenderMandrake((EntityMindrake)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*    */   {
/* 63 */     return func_110832_a((EntityMindrake)par1Entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntityMindrake par1Entity) {
/* 67 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderMindrake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */