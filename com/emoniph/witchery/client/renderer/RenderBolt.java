/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityBolt;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderBolt
/*     */   extends Render
/*     */ {
/*  20 */   private static final ResourceLocation arrowTextures = new ResourceLocation("witchery", "textures/entities/bolt.png");
/*  21 */   private static final ResourceLocation arrowTextures2 = new ResourceLocation("witchery", "textures/entities/bolt2.png");
/*  22 */   private static final ResourceLocation arrowTextures3 = new ResourceLocation("witchery", "textures/entities/bolt3.png");
/*  23 */   private static final ResourceLocation arrowTextures4 = new ResourceLocation("witchery", "textures/entities/bolt4.png");
/*     */   
/*     */   public void renderArrow(EntityBolt par1EntityArrow, double par2, double par4, double par6, float par8, float par9) {
/*  26 */     func_110777_b(par1EntityArrow);
/*  27 */     GL11.glPushMatrix();
/*  28 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*  29 */     GL11.glRotatef(par1EntityArrow.field_70126_B + (par1EntityArrow.field_70177_z - par1EntityArrow.field_70126_B) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
/*  30 */     GL11.glRotatef(par1EntityArrow.field_70127_C + (par1EntityArrow.field_70125_A - par1EntityArrow.field_70127_C) * par9, 0.0F, 0.0F, 1.0F);
/*  31 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  32 */     byte b0 = 0;
/*  33 */     float f2 = 0.0F;
/*  34 */     float f3 = 0.3F;
/*  35 */     float f4 = (0 + b0 * 10) / 32.0F;
/*  36 */     float f5 = (5 + b0 * 10) / 32.0F;
/*     */     
/*     */ 
/*  39 */     float f6 = 0.0F;
/*  40 */     float f7 = 0.15625F;
/*  41 */     float f8 = (5 + b0 * 10) / 32.0F;
/*  42 */     float f9 = (10 + b0 * 10) / 32.0F;
/*  43 */     float f10 = 0.05625F;
/*  44 */     GL11.glEnable(32826);
/*  45 */     float f11 = par1EntityArrow.arrowShake - par9;
/*     */     
/*  47 */     if (f11 > 0.0F) {
/*  48 */       float f12 = -MathHelper.func_76126_a(f11 * 3.0F) * f11;
/*  49 */       GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     
/*  52 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/*  53 */     GL11.glScalef(f10, f10, f10);
/*  54 */     GL11.glTranslatef(-3.0F, 0.0F, 0.0F);
/*  55 */     GL11.glNormal3f(f10, 0.0F, 0.0F);
/*     */     
/*     */ 
/*  58 */     tessellator.func_78382_b();
/*  59 */     tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f8);
/*  60 */     tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f8);
/*  61 */     tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f9);
/*  62 */     tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f9);
/*  63 */     tessellator.func_78381_a();
/*     */     
/*     */ 
/*  66 */     GL11.glNormal3f(-f10, 0.0F, 0.0F);
/*  67 */     tessellator.func_78382_b();
/*  68 */     tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f8);
/*  69 */     tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f8);
/*  70 */     tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f9);
/*  71 */     tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f9);
/*  72 */     tessellator.func_78381_a();
/*     */     
/*  74 */     GL11.glTranslatef(0.9F, 0.0F, 0.0F);
/*  75 */     for (int i = 0; i < 4; i++) {
/*  76 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  77 */       GL11.glNormal3f(0.0F, 0.0F, f10);
/*  78 */       tessellator.func_78382_b();
/*  79 */       tessellator.func_78374_a(-8.0D, -2.0D, 0.0D, f2, f4);
/*  80 */       tessellator.func_78374_a(8.0D, -2.0D, 0.0D, f3, f4);
/*  81 */       tessellator.func_78374_a(8.0D, 2.0D, 0.0D, f3, f5);
/*  82 */       tessellator.func_78374_a(-8.0D, 2.0D, 0.0D, f2, f5);
/*  83 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  86 */     GL11.glDisable(32826);
/*  87 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected ResourceLocation getArrowTextures(EntityBolt bolt) {
/*  91 */     if (bolt.isHolyDamage())
/*  92 */       return arrowTextures3;
/*  93 */     if (bolt.isSilverDamage())
/*  94 */       return arrowTextures4;
/*  95 */     if (bolt.isDraining()) {
/*  96 */       return arrowTextures2;
/*     */     }
/*  98 */     return arrowTextures;
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 104 */     return getArrowTextures((EntityBolt)par1Entity);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 109 */     renderArrow((EntityBolt)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBolt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */