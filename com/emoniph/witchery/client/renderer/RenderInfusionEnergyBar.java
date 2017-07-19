/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.infusion.Infusion.Registry;
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderInfusionEnergyBar
/*     */ {
/*  24 */   int xsize = 8;
/*  25 */   int ysize = 32;
/*     */   
/*  27 */   private static final ResourceLocation GLASS = new ResourceLocation("witchery", "textures/gui/glass.png");
/*  28 */   private static final ResourceLocation BLOCK_TEXTURES = new ResourceLocation("textures/atlas/blocks.png");
/*  29 */   private static final ResourceLocation CREATURES = new ResourceLocation("witchery", "textures/gui/creatures.png");
/*     */   final boolean primary;
/*     */   
/*     */   public RenderInfusionEnergyBar(boolean primary)
/*     */   {
/*  34 */     this.primary = primary;
/*     */   }
/*     */   
/*     */   public void draw(double xpos, double ypos, double value, EntityPlayer player, int powerID) {
/*  38 */     Minecraft mc = Minecraft.func_71410_x();
/*  39 */     mc.func_110434_K().func_110577_a(BLOCK_TEXTURES);
/*  40 */     GL11.glPushMatrix();
/*     */     try {
/*  42 */       RenderUtil.blend(true);
/*  43 */       drawFluid(xpos, ypos, value, this.primary ? Infusion.Registry.instance().get(powerID).getPowerBarIcon(player, 0) : Blocks.field_150435_aG.func_149691_a(0, 0));
/*  44 */       int iconOffsetX = 0;
/*  45 */       int iconOffsetY = (powerID - 1) * 8;
/*  46 */       if (this.primary) {
/*  47 */         drawGlass(xpos, ypos);
/*  48 */         iconOffsetX = 8;
/*     */       }
/*     */       
/*  51 */       int width = 8;
/*  52 */       int height = 8;
/*  53 */       int xPosition = MathHelper.func_76128_c(xpos);
/*  54 */       int yPosition = MathHelper.func_76128_c(ypos) + 33;
/*     */       
/*  56 */       mc.func_110434_K().func_110577_a(CREATURES);
/*  57 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  58 */       drawTexturedModalRect(xPosition, yPosition, iconOffsetX, iconOffsetY, width, height);
/*     */     } finally {
/*  60 */       RenderUtil.blend(false);
/*  61 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   public void drawFluid(double xpos, double ypos, double value, IIcon icon) {
/*  66 */     double bottomY = ypos + this.ysize;
/*  67 */     double topY = ypos + this.ysize * (1.0D - value);
/*     */     
/*  69 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/*  70 */     if (this.primary) {
/*  71 */       while (bottomY - 8.0D > topY) {
/*  72 */         drawIconPartial(xpos * 2.0D, (bottomY - 8.0D) * 2.0D, icon, 0.0D, 0.0D, 16.0D, 16.0D);
/*  73 */         bottomY -= 8.0D;
/*     */       }
/*  75 */       drawIconPartial(xpos * 2.0D, (bottomY - 8.0D) * 2.0D, icon, 0.0D, (topY - bottomY + 8.0D) * 2.0D, 16.0D, 16.0D);
/*     */     } else {
/*  77 */       for (int i = 0; i < value; i++) {
/*  78 */         drawIconPartial(xpos * 2.0D, (bottomY - i * 2) * 2.0D - 2.0D, icon, 0.0D, 0.0D, 16.0D, 2.0D);
/*     */       }
/*     */     }
/*  81 */     GL11.glScaled(2.0D, 2.0D, 2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
/*     */   {
/*  87 */     double zLevel = 0.0D;
/*  88 */     float f = 0.00390625F;
/*  89 */     float f1 = 0.00390625F;
/*  90 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  91 */     tessellator.func_78382_b();
/*  92 */     tessellator.func_78374_a(par1 + 0, par2 + par6, zLevel, (par3 + 0) * f, (par4 + par6) * f1);
/*  93 */     tessellator.func_78374_a(par1 + par5, par2 + par6, zLevel, (par3 + par5) * f, (par4 + par6) * f1);
/*  94 */     tessellator.func_78374_a(par1 + par5, par2 + 0, zLevel, (par3 + par5) * f, (par4 + 0) * f1);
/*  95 */     tessellator.func_78374_a(par1 + 0, par2 + 0, zLevel, (par3 + 0) * f, (par4 + 0) * f1);
/*  96 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   public static void drawIconPartial(double x, double y, IIcon icon, double left, double top, double right, double bottom) {
/* 100 */     if (icon == null) {
/* 101 */       return;
/*     */     }
/*     */     
/*     */ 
/* 105 */     RenderUtil.render2d(true);
/*     */     
/*     */ 
/* 108 */     GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
/*     */     
/* 110 */     Tessellator tess = Tessellator.field_78398_a;
/* 111 */     tess.func_78382_b();
/* 112 */     float u1 = icon.func_94209_e();
/* 113 */     float v1 = icon.func_94206_g();
/* 114 */     float u2 = icon.func_94212_f();
/* 115 */     float v2 = icon.func_94210_h();
/* 116 */     double xoffset1 = left * (u2 - u1) / 16.0D;
/* 117 */     double yoffset1 = top * (v2 - v1) / 16.0D;
/* 118 */     double xoffset2 = right * (u2 - u1) / 16.0D;
/* 119 */     double yoffset2 = bottom * (v2 - v1) / 16.0D;
/*     */     
/* 121 */     tess.func_78374_a(x + left, y + top, 0.0D, u1 + xoffset1, v1 + yoffset1);
/* 122 */     tess.func_78374_a(x + left, y + bottom, 0.0D, u1 + xoffset1, v1 + yoffset2);
/* 123 */     tess.func_78374_a(x + right, y + bottom, 0.0D, u1 + xoffset2, v1 + yoffset2);
/* 124 */     tess.func_78374_a(x + right, y + top, 0.0D, u1 + xoffset2, v1 + yoffset1);
/* 125 */     tess.func_78381_a();
/*     */     
/*     */ 
/* 128 */     RenderUtil.render2d(false);
/*     */   }
/*     */   
/*     */   public void drawGlass(double xpos, double ypos)
/*     */   {
/* 133 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(GLASS);
/* 134 */     GL11.glBegin(7);
/* 135 */     GL11.glTexCoord2d(0.0D, 0.0D);
/* 136 */     GL11.glVertex2d(xpos, ypos);
/* 137 */     GL11.glTexCoord2d(0.0D, 1.0D);
/* 138 */     GL11.glVertex2d(xpos, ypos + this.ysize);
/* 139 */     GL11.glTexCoord2d(1.0D, 1.0D);
/* 140 */     GL11.glVertex2d(xpos + this.xsize, ypos + this.ysize);
/* 141 */     GL11.glTexCoord2d(1.0D, 0.0D);
/* 142 */     GL11.glVertex2d(xpos + this.xsize, ypos);
/* 143 */     GL11.glEnd();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderInfusionEnergyBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */