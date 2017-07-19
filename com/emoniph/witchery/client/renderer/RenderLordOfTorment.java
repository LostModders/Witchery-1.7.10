/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelLordOfTorment;
/*    */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
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
/*    */ public class RenderLordOfTorment
/*    */   extends RenderLiving
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/lordoftorment.png");
/*    */   
/*    */   public RenderLordOfTorment() {
/* 24 */     super(new ModelLordOfTorment(), 0.5F);
/*    */   }
/*    */   
/*    */   public void doRenderDemon(EntityLordOfTorment entity, double par2, double par4, double par6, float par8, float par9) {
/* 28 */     BossStatus.func_82824_a(entity, true);
/* 29 */     super.func_76986_a(entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void rotateDemonCorpse(EntityLordOfTorment entity, float par2, float par3, float par4) {
/* 33 */     super.func_77043_a(entity, par2, par3, par4);
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
/* 45 */     doRenderDemon((EntityLordOfTorment)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
/*    */   {
/* 50 */     rotateDemonCorpse((EntityLordOfTorment)par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 55 */     doRenderDemon((EntityLordOfTorment)par1, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 60 */     doRenderDemon((EntityLordOfTorment)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*    */   {
/* 65 */     return func_110832_a((EntityLordOfTorment)par1Entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntityLordOfTorment par1Entity) {
/* 69 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderLordOfTorment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */