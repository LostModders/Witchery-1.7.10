/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Timer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelOverlayRenderer
/*     */ {
/*     */   private static Field fieldMainModel;
/*     */   private static Field fieldTimer;
/*     */   private static Method methodRotateCorpse;
/*     */   private static Method methodHandleRotationFloat;
/*     */   private static Method methodPreRenderCallback;
/*     */   private static Timer timer;
/*     */   
/*     */   public static float getRenderPartialTicks()
/*     */   {
/*  38 */     if (fieldTimer == null) {
/*  39 */       fieldTimer = ReflectionHelper.findField(Minecraft.class, new String[] { "timer", "field_71428_T", "Q" });
/*     */       try {
/*  41 */         Minecraft mc = Minecraft.func_71410_x();
/*  42 */         if (timer == null) {
/*  43 */           timer = (Timer)fieldTimer.get(mc);
/*     */         }
/*     */       }
/*     */       catch (IllegalAccessException ex) {}
/*     */     }
/*  48 */     if (timer != null) {
/*  49 */       return timer.field_74281_c;
/*     */     }
/*  51 */     return 0.0F;
/*     */   }
/*     */   
/*     */   private static void ensureInitialized(RendererLivingEntity originalRenderer)
/*     */   {
/*  56 */     if (fieldTimer == null) {
/*  57 */       fieldTimer = ReflectionHelper.findField(Minecraft.class, new String[] { "timer", "field_71428_T", "Q" });
/*     */       try {
/*  59 */         Minecraft mc = Minecraft.func_71410_x();
/*  60 */         if (timer == null) {
/*  61 */           timer = (Timer)fieldTimer.get(mc);
/*     */         }
/*     */       }
/*     */       catch (IllegalAccessException ex) {}
/*     */     }
/*  66 */     if (fieldMainModel == null) {
/*  67 */       fieldMainModel = ReflectionHelper.findField(RendererLivingEntity.class, new String[] { "mainModel", "field_77045_g", "i" });
/*     */     }
/*  69 */     if (methodRotateCorpse == null) {
/*  70 */       methodRotateCorpse = ReflectionHelper.findMethod(RendererLivingEntity.class, originalRenderer, new String[] { "rotateCorpse", "func_77043_a", "a" }, new Class[] { EntityLivingBase.class, Float.TYPE, Float.TYPE, Float.TYPE });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  75 */     if (methodHandleRotationFloat == null) {
/*  76 */       methodHandleRotationFloat = ReflectionHelper.findMethod(RendererLivingEntity.class, originalRenderer, new String[] { "handleRotationFloat", "func_77044_a", "b" }, new Class[] { EntityLivingBase.class, Float.TYPE });
/*     */     }
/*     */     
/*     */ 
/*  80 */     if (methodPreRenderCallback == null) {
/*  81 */       methodPreRenderCallback = ReflectionHelper.findMethod(RendererLivingEntity.class, originalRenderer, new String[] { "preRenderCallback", "func_77041_b", "a" }, new Class[] { EntityLivingBase.class, Float.TYPE });
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void render(EntityLivingBase entity, double x, double y, double z, RendererLivingEntity originalRenderer)
/*     */   {
/*     */     try
/*     */     {
/*  90 */       ensureInitialized(originalRenderer);
/*  91 */       ModelBase mainModel = (ModelBase)fieldMainModel.get(originalRenderer);
/*  92 */       renderModel(entity, x, y, z, originalRenderer, mainModel);
/*     */     }
/*     */     catch (IllegalAccessException ex) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public static void renderModel(EntityLivingBase entity, double x, double y, double z, RendererLivingEntity originalRenderer, ModelBase model)
/*     */   {
/* 100 */     ensureInitialized(originalRenderer);
/*     */     
/* 102 */     if (timer != null) {
/* 103 */       renderModelAsOverlay(entity, model, x, y, z, timer.field_74281_c, originalRenderer);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void renderModelAsOverlay(EntityLivingBase entity, ModelBase mainModel, double x, double y, double z, float partialRenderTicks, RendererLivingEntity originalRenderer)
/*     */   {
/*     */     try
/*     */     {
/* 111 */       GL11.glPushMatrix();
/* 112 */       mainModel.field_78095_p = renderSwingProgress(entity, partialRenderTicks);
/* 113 */       mainModel.field_78093_q = entity.func_70115_ae();
/* 114 */       mainModel.field_78091_s = entity.func_70631_g_();
/*     */       
/* 116 */       float f2 = interpolateRotation(entity.field_70760_ar, entity.field_70761_aq, partialRenderTicks);
/* 117 */       float f3 = interpolateRotation(entity.field_70758_at, entity.field_70759_as, partialRenderTicks);
/*     */       
/*     */ 
/* 120 */       if ((entity.func_70115_ae()) && ((entity.field_70154_o instanceof EntityLivingBase))) {
/* 121 */         EntityLivingBase entitylivingbase1 = (EntityLivingBase)entity.field_70154_o;
/* 122 */         f2 = interpolateRotation(entitylivingbase1.field_70760_ar, entitylivingbase1.field_70761_aq, partialRenderTicks);
/*     */         
/* 124 */         float f4 = MathHelper.func_76142_g(f3 - f2);
/*     */         
/* 126 */         if (f4 < -85.0F) {
/* 127 */           f4 = -85.0F;
/*     */         }
/*     */         
/* 130 */         if (f4 >= 85.0F) {
/* 131 */           f4 = 85.0F;
/*     */         }
/*     */         
/* 134 */         f2 = f3 - f4;
/*     */         
/* 136 */         if (f4 * f4 > 2500.0F) {
/* 137 */           f2 += f4 * 0.2F;
/*     */         }
/*     */       }
/*     */       
/* 141 */       float f13 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialRenderTicks;
/*     */       
/* 143 */       renderLivingAt(entity, x, y, z);
/*     */       
/* 145 */       float f4 = ((Float)methodHandleRotationFloat.invoke(originalRenderer, new Object[] { entity, Float.valueOf(partialRenderTicks) })).floatValue();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 150 */       methodRotateCorpse.invoke(originalRenderer, new Object[] { entity, Float.valueOf(f4), Float.valueOf(f2), Float.valueOf(partialRenderTicks) });
/*     */       
/*     */ 
/*     */ 
/* 154 */       float f5 = 0.0625F;
/* 155 */       GL11.glEnable(32826);
/* 156 */       GL11.glScalef(-1.0F, -1.0F, 1.0F);
/*     */       
/* 158 */       methodPreRenderCallback.invoke(originalRenderer, new Object[] { entity, Float.valueOf(partialRenderTicks) });
/*     */       
/* 160 */       GL11.glTranslatef(0.0F, -24.0F * f5 - 0.0078125F, 0.0F);
/* 161 */       float f6 = entity.field_70722_aY + (entity.field_70721_aZ - entity.field_70722_aY) * partialRenderTicks;
/*     */       
/* 163 */       float f7 = entity.field_70754_ba - entity.field_70721_aZ * (1.0F - partialRenderTicks);
/*     */       
/* 165 */       if (entity.func_70631_g_()) {
/* 166 */         f7 *= 3.0F;
/*     */       }
/*     */       
/* 169 */       if (f6 > 1.0F) {
/* 170 */         f6 = 1.0F;
/*     */       }
/*     */       
/* 173 */       GL11.glEnable(3008);
/* 174 */       mainModel.func_78086_a(entity, f7, f6, partialRenderTicks);
/* 175 */       float SCALE = 1.01F;
/* 176 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/*     */       
/* 178 */       renderModel(entity, f7, f6, f4, f3 - f2, f13, f5, mainModel);
/*     */     }
/*     */     catch (IllegalAccessException ex) {}catch (InvocationTargetException ex) {}finally
/*     */     {
/* 182 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static float interpolateRotation(float par1, float par2, float partialRenderTicks)
/*     */   {
/* 189 */     for (float f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F) {}
/*     */     
/*     */ 
/*     */ 
/* 193 */     while (f3 >= 180.0F) {
/* 194 */       f3 -= 360.0F;
/*     */     }
/*     */     
/* 197 */     return par1 + partialRenderTicks * f3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void renderLivingAt(EntityLivingBase entity, double p_77039_2_, double p_77039_4_, double p_77039_6_)
/*     */   {
/* 214 */     GL11.glTranslatef((float)p_77039_2_, (float)p_77039_4_, (float)p_77039_6_);
/* 215 */     if ((entity != null) && (entity.func_70644_a(Witchery.Potions.RESIZING))) {
/* 216 */       PotionEffect resizing = entity.func_70660_b(Witchery.Potions.RESIZING);
/* 217 */       if (resizing != null) {
/* 218 */         float scale = PotionResizing.getModifiedScaleFactor(entity, resizing.func_76458_c());
/* 219 */         GL11.glScalef(scale, scale, scale);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static float renderSwingProgress(EntityLivingBase entity, float partialRenderTicks)
/*     */   {
/* 252 */     return entity.func_70678_g(partialRenderTicks);
/*     */   }
/*     */   
/*     */   private static void renderModel(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_, ModelBase mainModel)
/*     */   {
/* 257 */     if (!entity.func_82150_aj()) {
/* 258 */       mainModel.func_78088_a(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
/* 259 */     } else if (!entity.func_98034_c(Minecraft.func_71410_x().field_71439_g))
/*     */     {
/* 261 */       GL11.glPushMatrix();
/* 262 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
/* 263 */       GL11.glDepthMask(false);
/* 264 */       GL11.glEnable(3042);
/* 265 */       GL11.glBlendFunc(770, 771);
/* 266 */       GL11.glAlphaFunc(516, 0.003921569F);
/*     */       
/* 268 */       mainModel.func_78088_a(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
/* 269 */       GL11.glDisable(3042);
/* 270 */       GL11.glAlphaFunc(516, 0.1F);
/* 271 */       GL11.glPopMatrix();
/* 272 */       GL11.glDepthMask(true);
/*     */     } else {
/* 274 */       mainModel.func_78087_a(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, entity);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/ModelOverlayRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */