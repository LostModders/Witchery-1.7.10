/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelOwl;
/*     */ import com.emoniph.witchery.entity.EntityOwl;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderOwl
/*     */   extends RenderLiving
/*     */ {
/*  30 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/owl.png");
/*     */   
/*     */   public RenderOwl(ModelBase par1ModelBase, float par2) {
/*  33 */     super(par1ModelBase, par2);
/*     */   }
/*     */   
/*     */ 
/*  37 */   public static final float[][] fleeceColorTable = { { 1.0F, 1.0F, 1.0F }, { 0.85F, 0.5F, 0.2F }, { 0.7F, 0.3F, 0.85F }, { 0.4F, 0.6F, 0.85F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.5F, 0.65F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.5F, 0.6F }, { 0.5F, 0.25F, 0.7F }, { 0.2F, 0.3F, 0.7F }, { 0.4F, 0.3F, 0.2F }, { 0.4F, 0.5F, 0.2F }, { 0.6F, 0.2F, 0.2F }, { 0.1F, 0.1F, 0.1F } };
/*     */   
/*     */ 
/*     */ 
/*     */   public void doRenderOwl(EntityOwl entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  43 */     float f1 = 1.0F;
/*  44 */     int j = entity.getFeatherColor();
/*  45 */     if (j == 0) {
/*  46 */       GL11.glColor3f(f1 * fleeceColorTable[j][0], f1 * fleeceColorTable[j][1], f1 * fleeceColorTable[j][2]);
/*     */     } else {
/*  48 */       float alpha = 0.84313726F;
/*  49 */       float bR = 0.41568628F;
/*  50 */       float bG = 0.3137255F;
/*  51 */       float bB = 0.24313726F;
/*  52 */       GL11.glColor3f(f1 * fleeceColorTable[j][0] * 0.15686274F + 0.41568628F, f1 * fleeceColorTable[j][1] * 0.15686274F + 0.3137255F, f1 * fleeceColorTable[j][2] * 0.15686274F + 0.24313726F);
/*     */     }
/*     */     
/*  55 */     super.func_76986_a(entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected void rotateOwlCorpse(EntityOwl entity, float par2, float par3, float par4) {
/*  59 */     super.func_77043_a(entity, par2, par3, par4);
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
/*     */   public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  73 */     doRenderOwl((EntityOwl)entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
/*     */   {
/*  78 */     rotateOwlCorpse((EntityOwl)par1EntityLivingBase, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  83 */     doRenderOwl((EntityOwl)par1, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  88 */     doRenderOwl((EntityOwl)entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/*  93 */     return TEXTURE_URL;
/*     */   }
/*     */   
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/*  98 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*  99 */     super.func_77029_c(par1EntityLiving, par2);
/* 100 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/*     */     
/*     */ 
/*     */ 
/* 104 */     if ((itemstack != null) && (itemstack.func_77973_b() != null))
/*     */     {
/* 106 */       Item item = itemstack.func_77973_b();
/* 107 */       GL11.glPushMatrix();
/*     */       
/* 109 */       if (this.field_77045_g.field_78091_s)
/*     */       {
/* 111 */         float f1 = 0.5F;
/* 112 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/* 113 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/* 114 */         GL11.glScalef(f1, f1, f1);
/*     */       }
/*     */       
/*     */ 
/* 118 */       if ((par1EntityLiving != null) && ((par1EntityLiving instanceof EntityOwl)) && (ModelOwl.isLanded(par1EntityLiving))) {
/* 119 */         GL11.glTranslatef(-0.0625F, 1.1375F, 0.0625F);
/*     */       } else {
/* 121 */         GL11.glTranslatef(-0.0625F, 1.375F, 0.3F);
/*     */       }
/*     */       
/* 124 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 125 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 127 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
/*     */       {
/* 129 */         float f1 = 0.5F;
/* 130 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 131 */         f1 *= 0.75F;
/* 132 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 133 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 134 */         GL11.glScalef(-f1, -f1, f1);
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
/*     */       }
/*     */       else
/*     */       {
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
/* 162 */         float f1 = 0.375F;
/* 163 */         GL11.glTranslatef(0.25F, 0.1875F, -0.3F);
/* 164 */         GL11.glScalef(f1, f1, f1);
/* 165 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 166 */         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
/* 167 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 174 */       if (itemstack.func_77973_b().func_77623_v())
/*     */       {
/* 176 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++)
/*     */         {
/* 178 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 179 */           float f2 = (j >> 16 & 0xFF) / 255.0F;
/* 180 */           float f3 = (j >> 8 & 0xFF) / 255.0F;
/* 181 */           float f4 = (j & 0xFF) / 255.0F;
/* 182 */           GL11.glColor4f(f2, f3, f4, 1.0F);
/* 183 */           this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, i);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 188 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 189 */       float f5 = (i >> 16 & 0xFF) / 255.0F;
/* 190 */       float f2 = (i >> 8 & 0xFF) / 255.0F;
/* 191 */       float f3 = (i & 0xFF) / 255.0F;
/* 192 */       GL11.glColor4f(f5, f2, f3, 1.0F);
/* 193 */       this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, 0);
/*     */       
/*     */ 
/* 196 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82422_c() {
/* 201 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderOwl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */