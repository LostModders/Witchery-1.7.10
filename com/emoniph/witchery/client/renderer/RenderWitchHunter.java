/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelWitchHunter;
/*    */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.entity.RenderBiped;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderWitchHunter
/*    */   extends RenderBiped
/*    */ {
/* 18 */   private static final ResourceLocation TEXTURE1_URL = new ResourceLocation("witchery", "textures/entities/witchhunter1.png");
/* 19 */   private static final ResourceLocation TEXTURE2_URL = new ResourceLocation("witchery", "textures/entities/witchhunter2.png");
/* 20 */   private static final ResourceLocation TEXTURE3_URL = new ResourceLocation("witchery", "textures/entities/witchhunter3.png");
/*    */   
/*    */   public RenderWitchHunter() {
/* 23 */     super(new ModelWitchHunter(), 0.5F);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 28 */     return func_110832_a((EntityWitchHunter)entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110832_a(EntityWitchHunter entity) {
/* 32 */     switch (entity.getHunterType()) {
/*    */     default: 
/* 34 */       return TEXTURE1_URL;
/*    */     case 1: 
/* 36 */       return TEXTURE2_URL;
/*    */     }
/* 38 */     return TEXTURE3_URL;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderWitchHunter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */