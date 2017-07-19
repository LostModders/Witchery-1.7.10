/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityLilith;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderLilith
/*    */   extends RenderLiving
/*    */ {
/* 19 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/lilith.png");
/*    */   
/*    */   public RenderLilith(ModelBase model, float shadowSize)
/*    */   {
/* 23 */     super(model, shadowSize);
/*    */   }
/*    */   
/*    */   public void doRenderLilith(EntityLilith entity, double x, double y, double z, float par8, float par9) {
/* 27 */     BossStatus.func_82824_a(entity, true);
/* 28 */     super.func_76986_a(entity, x, y, z, par8, par9);
/*    */   }
/*    */   
/*    */   protected void rotateLilithCorpse(EntityLilith entity, float x, float y, float z) {
/* 32 */     super.func_77043_a(entity, x, y, z);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 37 */     doRenderLilith((EntityLilith)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
/*    */   {
/* 42 */     rotateLilithCorpse((EntityLilith)par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 47 */     doRenderLilith((EntityLilith)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 52 */     doRenderLilith((EntityLilith)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 57 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderLilith.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */