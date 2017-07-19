/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelVillageGuard;
/*    */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderBiped;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderVillageGuard
/*    */   extends RenderBiped
/*    */ {
/* 16 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/villageguard.png");
/*    */   
/*    */   public RenderVillageGuard() {
/* 19 */     super(new ModelVillageGuard(), 0.5F);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 24 */     return func_110832_a((EntityVillageGuard)entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntityVillageGuard entity) {
/* 28 */     return TEXTURE_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderVillageGuard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */