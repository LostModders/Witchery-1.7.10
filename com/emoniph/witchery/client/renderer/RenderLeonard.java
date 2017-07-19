/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityLeonard;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.BossStatus;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderLeonard
/*    */   extends RenderLiving
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/leonard.png");
/*    */   
/*    */   public RenderLeonard(ModelBase model, float shadowSize)
/*    */   {
/* 25 */     super(model, shadowSize);
/*    */   }
/*    */   
/*    */   public void doRenderLeonard(EntityLeonard entity, double x, double y, double z, float par8, float par9) {
/* 29 */     BossStatus.func_82824_a(entity, true);
/* 30 */     super.func_76986_a(entity, x, y, z, par8, par9);
/*    */   }
/*    */   
/*    */   protected void rotateLeonardCorpse(EntityLeonard entity, float x, float y, float z) {
/* 34 */     super.func_77043_a(entity, x, y, z);
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
/* 46 */     doRenderLeonard((EntityLeonard)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
/*    */   {
/* 51 */     rotateLeonardCorpse((EntityLeonard)par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 56 */     doRenderLeonard((EntityLeonard)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 61 */     doRenderLeonard((EntityLeonard)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 66 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderLeonard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */