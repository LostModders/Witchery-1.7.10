/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityHellhound;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderHellhound
/*    */   extends RenderLiving
/*    */ {
/* 17 */   private static final ResourceLocation anrgyWolfTextures = new ResourceLocation("witchery", "textures/entities/hellhound.png");
/*    */   
/*    */ 
/*    */   public RenderHellhound(ModelBase model, float shadow)
/*    */   {
/* 22 */     super(model, shadow);
/*    */   }
/*    */   
/*    */   protected float handleRotationFloat(EntityHellhound entity, float p_77044_2_) {
/* 26 */     return entity.getTailRotation();
/*    */   }
/*    */   
/*    */   protected int shouldRenderPass(EntityHellhound entity, int p_77032_2_, float p_77032_3_) {
/* 30 */     return -1;
/*    */   }
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityHellhound entity) {
/* 34 */     return anrgyWolfTextures;
/*    */   }
/*    */   
/*    */   protected int func_77032_a(EntityLivingBase entity, int p_77032_2_, float p_77032_3_)
/*    */   {
/* 39 */     return shouldRenderPass((EntityHellhound)entity, p_77032_2_, p_77032_3_);
/*    */   }
/*    */   
/*    */   protected float func_77044_a(EntityLivingBase entity, float p_77044_2_)
/*    */   {
/* 44 */     return handleRotationFloat((EntityHellhound)entity, p_77044_2_);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 49 */     return getEntityTexture((EntityHellhound)entity);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderHellhound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */