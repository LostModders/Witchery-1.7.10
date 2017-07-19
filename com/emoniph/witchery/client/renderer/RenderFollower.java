/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityFollower;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.entity.RenderBiped;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderFollower extends RenderBiped
/*    */ {
/*    */   private final ModelBiped model;
/*    */   
/*    */   public RenderFollower(ModelBiped model)
/*    */   {
/* 15 */     this(model, 1.0F);
/*    */   }
/*    */   
/*    */   public RenderFollower(ModelBiped model, float scale) {
/* 19 */     super(model, 0.5F, scale);
/* 20 */     this.model = model;
/*    */   }
/*    */   
/* 23 */   private static final ResourceLocation TEXTURE_ELLE = new ResourceLocation("witchery", "textures/entities/lilithfol1.png");
/* 24 */   private static final ResourceLocation TEXTURE_FAIREST1 = new ResourceLocation("witchery", "textures/entities/fairest1.png");
/* 25 */   private static final ResourceLocation TEXTURE_FAIREST2 = new ResourceLocation("witchery", "textures/entities/fairest2.png");
/* 26 */   private static final ResourceLocation TEXTURE_FAIREST3 = new ResourceLocation("witchery", "textures/entities/fairest3.png");
/* 27 */   private static final ResourceLocation TEXTURE_FAIREST4 = new ResourceLocation("witchery", "textures/entities/fairest4.png");
/*    */   
/*    */   protected ResourceLocation func_110775_a(EntityLiving entity)
/*    */   {
/* 31 */     return getEntityTexture((EntityFollower)entity);
/*    */   }
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityFollower entity) {
/* 35 */     switch (entity.getFollowerType()) {
/*    */     case 0: 
/* 37 */       return TEXTURE_ELLE;
/*    */     case 1: 
/* 39 */       return TEXTURE_FAIREST1;
/*    */     case 2: 
/* 41 */       return TEXTURE_FAIREST2;
/*    */     case 3: 
/* 43 */       return TEXTURE_FAIREST3;
/*    */     case 4: 
/* 45 */       return TEXTURE_FAIREST4;
/*    */     case 5: 
/* 47 */       return entity.getLocationSkin();
/*    */     }
/* 49 */     return TEXTURE_FAIREST1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderFollower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */