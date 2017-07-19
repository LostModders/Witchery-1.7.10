/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntitySpellEffect;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffectProjectile;
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderSpellEffect
/*     */   extends Render
/*     */ {
/*     */   private float field_77002_a;
/*     */   
/*     */   public RenderSpellEffect(float par1)
/*     */   {
/*  29 */     this.field_77002_a = par1;
/*     */   }
/*     */   
/*     */   public void doRenderSpellEffect(EntitySpellEffect effectEntity, double par2, double par4, double par6, float par8, float par9) {
/*  33 */     GL11.glPushMatrix();
/*  34 */     func_110777_b(effectEntity);
/*  35 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  40 */     RenderUtil.blend(true);
/*     */     
/*     */ 
/*  43 */     float scale = 1.0F;
/*  44 */     int color = 16711680;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  51 */     IIcon icon2 = Items.field_151126_ay.func_77617_a(0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  56 */     SymbolEffect effect = EffectRegistry.instance().getEffect(effectEntity.getEffectID());
/*  57 */     if ((effect != null) && ((effect instanceof SymbolEffectProjectile))) {
/*  58 */       SymbolEffectProjectile projectileEffect = (SymbolEffectProjectile)effect;
/*  59 */       color = projectileEffect.getColor();
/*  60 */       scale = projectileEffect.getSize();
/*     */     }
/*     */     
/*  63 */     float f2 = this.field_77002_a * scale * 0.65F;
/*  64 */     GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
/*     */     
/*  66 */     float red = (color >>> 16 & 0xFF) / 256.0F;
/*  67 */     float green = (color >>> 8 & 0xFF) / 256.0F;
/*  68 */     float blue = (color & 0xFF) / 256.0F;
/*  69 */     GL11.glColor4f(red, green, blue, 0.55F);
/*     */     
/*  71 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  72 */     float f3 = icon2.func_94209_e();
/*  73 */     float f4 = icon2.func_94212_f();
/*  74 */     float f5 = icon2.func_94206_g();
/*  75 */     float f6 = icon2.func_94210_h();
/*     */     
/*  77 */     float f7 = 1.0F;
/*  78 */     float f8 = 0.5F;
/*  79 */     float f9 = 0.25F;
/*  80 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  81 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/*  82 */     tessellator.func_78382_b();
/*  83 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  84 */     tessellator.func_78374_a(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
/*  85 */     tessellator.func_78374_a(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
/*  86 */     tessellator.func_78374_a(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
/*  87 */     tessellator.func_78374_a(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
/*  88 */     tessellator.func_78381_a();
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
/* 104 */     RenderUtil.blend(false);
/* 105 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/* 108 */   private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("witchery", "textures/entities/spelleffect.png");
/*     */   
/*     */   protected ResourceLocation getSpellEffectTextures(EntitySpellEffect effect) {
/* 111 */     return TextureMap.field_110576_c;
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 116 */     return getSpellEffectTextures((EntitySpellEffect)par1Entity);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 121 */     doRenderSpellEffect((EntitySpellEffect)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderSpellEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */