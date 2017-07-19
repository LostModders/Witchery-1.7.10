/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelBroom;
/*    */ import com.emoniph.witchery.entity.EntityBroom;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderBroom
/*    */   extends Render
/*    */ {
/* 24 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/Broom.png");
/*    */   protected ModelBase modelBroom;
/*    */   
/* 27 */   protected ResourceLocation func_110775_a(Entity par1Entity) { return func_110832_a((EntityBroom)par1Entity); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public RenderBroom()
/*    */   {
/* 34 */     this.field_76989_e = 0.5F;
/* 35 */     this.modelBroom = new ModelBroom();
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntityBroom par1Entity) {
/* 39 */     return TEXTURE_URL;
/*    */   }
/*    */   
/*    */   public void renderBroom(EntityBroom par1EntityBoat, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 44 */     GL11.glPushMatrix();
/* 45 */     GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6);
/* 46 */     GL11.glRotatef(90.0F - par8, 0.0F, 1.0F, 0.0F);
/* 47 */     float f2 = par1EntityBoat.getTimeSinceHit() - par9;
/* 48 */     float f3 = par1EntityBoat.getDamageTaken() - par9;
/*    */     
/* 50 */     if (f3 < 0.0F)
/*    */     {
/* 52 */       f3 = 0.0F;
/*    */     }
/*    */     
/* 55 */     if (f2 > 0.0F)
/*    */     {
/* 57 */       GL11.glRotatef(MathHelper.func_76126_a(f2) * f2 * f3 / 10.0F * par1EntityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
/*    */     }
/*    */     
/* 60 */     float f4 = 0.75F;
/* 61 */     GL11.glScalef(f4, f4, f4);
/* 62 */     GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
/* 63 */     func_110777_b(par1EntityBoat);
/* 64 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 65 */     this.modelBroom.func_78088_a(par1EntityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 66 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 72 */     renderBroom((EntityBroom)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */