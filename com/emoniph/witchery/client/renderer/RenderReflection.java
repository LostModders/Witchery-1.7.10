/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelWolfman;
/*    */ import com.emoniph.witchery.entity.EntityReflection;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.entity.RenderBiped;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderReflection
/*    */   extends RenderBiped
/*    */ {
/* 20 */   RenderWolfman wolfman = new RenderWolfman(new ModelWolfman(), 0.5F);
/*    */   
/*    */   public RenderReflection() {
/* 23 */     super(new ModelBiped(), 0.5F);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*    */   {
/* 29 */     if (((EntityReflection)entity).getModel() == 0) {
/* 30 */       super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */     } else {
/* 32 */       this.wolfman.func_76976_a(this.field_76990_c);
/* 33 */       this.wolfman.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLiving entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*    */   {
/* 40 */     if (((EntityReflection)entity).getModel() == 0) {
/* 41 */       super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */     } else {
/* 43 */       this.wolfman.func_76976_a(this.field_76990_c);
/* 44 */       this.wolfman.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76986_a(EntityLivingBase entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*    */   {
/* 51 */     if (((EntityReflection)entity).getModel() == 0) {
/* 52 */       super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */     } else {
/* 54 */       this.wolfman.func_76976_a(this.field_76990_c);
/* 55 */       this.wolfman.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_76979_b(Entity entity, double p_76979_2_, double p_76979_4_, double p_76979_6_, float p_76979_8_, float p_76979_9_)
/*    */   {
/* 62 */     if (((EntityReflection)entity).getModel() == 0) {
/* 63 */       super.func_76979_b(entity, p_76979_2_, p_76979_4_, p_76979_6_, p_76979_8_, p_76979_9_);
/*    */     } else {
/* 65 */       this.wolfman.func_76976_a(this.field_76990_c);
/* 66 */       this.wolfman.func_76979_b(entity, p_76979_2_, p_76979_4_, p_76979_6_, p_76979_8_, p_76979_9_);
/*    */     }
/*    */   }
/*    */   
/*    */   protected ResourceLocation func_110775_a(Entity entity)
/*    */   {
/* 72 */     return getEntityTexture((EntityReflection)entity);
/*    */   }
/*    */   
/* 75 */   public static final ResourceLocation SKIN = new ResourceLocation("witchery", "textures/entities/reflection.png");
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityReflection entity)
/*    */   {
/* 79 */     if (entity.getModel() == 0) {
/* 80 */       ResourceLocation skin = entity.getLocationSkin();
/* 81 */       if (skin == null) {
/* 82 */         skin = SKIN;
/*    */       }
/* 84 */       return skin;
/*    */     }
/* 86 */     return RenderWolfman.TEXTURE;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderReflection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */