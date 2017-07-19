/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.BossStatus;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderHornedAvatar
/*    */   extends RenderLiving
/*    */ {
/* 21 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/avatar.png");
/*    */   
/*    */   public RenderHornedAvatar(ModelBase par1ModelBase, float par2) {
/* 24 */     super(par1ModelBase, par2);
/*    */   }
/*    */   
/*    */   public void doRenderHornedAvatar(EntityHornedHuntsman entity, double par2, double par4, double par6, float par8, float par9) {
/* 28 */     BossStatus.func_82824_a(entity, true);
/* 29 */     super.func_76986_a(entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void rotateHornedAvatarCorpse(EntityHornedHuntsman entity, float par2, float par3, float par4) {
/* 33 */     super.func_77043_a(entity, par2, par3, par4);
/*    */     
/* 35 */     if (entity.field_70721_aZ >= 0.01D) {
/* 36 */       float f3 = 13.0F;
/* 37 */       float f4 = entity.field_70754_ba - entity.field_70721_aZ * (1.0F - par4) + 6.0F;
/* 38 */       float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
/* 39 */       GL11.glRotatef(5.5F * f5, 0.0F, 0.0F, 1.0F);
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 45 */     doRenderHornedAvatar((EntityHornedHuntsman)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
/*    */   {
/* 50 */     rotateHornedAvatarCorpse((EntityHornedHuntsman)par1EntityLivingBase, par2, par3, par4);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 55 */     doRenderHornedAvatar((EntityHornedHuntsman)par1, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 60 */     doRenderHornedAvatar((EntityHornedHuntsman)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*    */   {
/* 65 */     return func_110832_a((EntityHornedHuntsman)par1Entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntityHornedHuntsman par1Entity) {
/* 69 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderHornedAvatar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */