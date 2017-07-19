/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityWingedMonkey;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
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
/*    */ public class RenderWingedMonkey
/*    */   extends RenderLiving
/*    */ {
/* 20 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/wingedmonkey.png");
/*    */   
/*    */   public RenderWingedMonkey(ModelBase par1ModelBase, float par2)
/*    */   {
/* 24 */     super(par1ModelBase, par2);
/*    */   }
/*    */   
/* 27 */   public static final float[][] fleeceColorTable = { { 1.0F, 1.0F, 1.0F }, { 0.85F, 0.5F, 0.2F }, { 0.7F, 0.3F, 0.85F }, { 0.4F, 0.6F, 0.85F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.5F, 0.65F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.5F, 0.6F }, { 0.5F, 0.25F, 0.7F }, { 0.2F, 0.3F, 0.7F }, { 0.4F, 0.3F, 0.2F }, { 0.4F, 0.5F, 0.2F }, { 0.6F, 0.2F, 0.2F }, { 0.1F, 0.1F, 0.1F } };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void doRenderMonkey(EntityWingedMonkey entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 35 */     float f1 = 1.0F;
/* 36 */     int j = entity.getFeatherColor();
/* 37 */     if (j != 0)
/*    */     {
/*    */ 
/* 40 */       float alpha = 0.84313726F;
/* 41 */       float bR = 0.41568628F;
/* 42 */       float bG = 0.3137255F;
/* 43 */       float bB = 0.24313726F;
/* 44 */       GL11.glColor3f(f1 * fleeceColorTable[j][0] * 0.15686274F + 0.41568628F, f1 * fleeceColorTable[j][1] * 0.15686274F + 0.3137255F, f1 * fleeceColorTable[j][2] * 0.15686274F + 0.24313726F);
/*    */     }
/*    */     
/* 47 */     super.func_76986_a(entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 52 */     doRenderMonkey((EntityWingedMonkey)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 57 */     doRenderMonkey((EntityWingedMonkey)par1, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 62 */     doRenderMonkey((EntityWingedMonkey)entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*    */   {
/* 67 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderWingedMonkey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */