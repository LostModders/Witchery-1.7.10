/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemCloth;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.ForgeHooksClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderItem3d
/*     */   extends Render
/*     */ {
/*  38 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*  39 */   private RenderBlocks renderBlocksRi = new RenderBlocks();
/*     */   
/*  41 */   private Random random = new Random();
/*  42 */   public boolean renderWithColor = true;
/*     */   
/*     */   public float zLevel;
/*     */   public static boolean renderInFrame;
/*     */   private static final String __OBFID = "CL_00001003";
/*     */   protected final boolean alwaysFancy;
/*     */   
/*     */   public RenderItem3d(boolean alwaysFancy)
/*     */   {
/*  51 */     this.field_76989_e = 0.15F;
/*  52 */     this.field_76987_f = 0.75F;
/*  53 */     this.alwaysFancy = alwaysFancy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void doRender(EntityItem par1EntityItem, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  65 */     ItemStack itemstack = par1EntityItem.func_92059_d();
/*     */     
/*  67 */     if (itemstack.func_77973_b() != null) {
/*  68 */       func_110777_b(par1EntityItem);
/*  69 */       this.random.setSeed(187L);
/*  70 */       GL11.glPushMatrix();
/*  71 */       float f2 = shouldBob() ? MathHelper.func_76126_a((par1EntityItem.field_70292_b + par9) / 10.0F + par1EntityItem.field_70290_d) * 0.1F + 0.1F : 0.0F;
/*     */       
/*  73 */       float f3 = ((par1EntityItem.field_70292_b + par9) / 20.0F + par1EntityItem.field_70290_d) * 57.295776F;
/*  74 */       byte b0 = 1;
/*     */       
/*  76 */       if (par1EntityItem.func_92059_d().field_77994_a > 1) {
/*  77 */         b0 = 2;
/*     */       }
/*     */       
/*  80 */       if (par1EntityItem.func_92059_d().field_77994_a > 5) {
/*  81 */         b0 = 3;
/*     */       }
/*     */       
/*  84 */       if (par1EntityItem.func_92059_d().field_77994_a > 20) {
/*  85 */         b0 = 4;
/*     */       }
/*     */       
/*  88 */       if (par1EntityItem.func_92059_d().field_77994_a > 40) {
/*  89 */         b0 = 5;
/*     */       }
/*     */       
/*  92 */       b0 = getMiniBlockCount(itemstack, b0);
/*     */       
/*  94 */       GL11.glTranslatef((float)par2, (float)par4 + f2, (float)par6);
/*  95 */       GL11.glEnable(32826);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 100 */       if (!ForgeHooksClient.renderEntityItem(par1EntityItem, itemstack, f2, f3, this.random, this.field_76990_c.field_78724_e, this.field_147909_c, b0))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 105 */         if ((itemstack.func_94608_d() == 0) && ((itemstack.func_77973_b() instanceof ItemBlock)) && (RenderBlocks.func_147739_a(Block.func_149634_a(itemstack.func_77973_b()).func_149645_b())))
/*     */         {
/* 107 */           Block block = Block.func_149634_a(itemstack.func_77973_b());
/* 108 */           GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
/*     */           
/* 110 */           if (renderInFrame) {
/* 111 */             GL11.glScalef(1.25F, 1.25F, 1.25F);
/* 112 */             GL11.glTranslatef(0.0F, 0.05F, 0.0F);
/* 113 */             GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */           }
/*     */           
/* 116 */           float f9 = 0.25F;
/* 117 */           int k = block.func_149645_b();
/*     */           
/* 119 */           if ((k == 1) || (k == 19) || (k == 12) || (k == 2)) {
/* 120 */             f9 = 0.5F;
/*     */           }
/*     */           
/* 123 */           if (block.func_149701_w() > 0) {
/* 124 */             GL11.glAlphaFunc(516, 0.1F);
/* 125 */             GL11.glEnable(3042);
/* 126 */             OpenGlHelper.func_148821_a(770, 771, 1, 0);
/*     */           }
/*     */           
/* 129 */           GL11.glScalef(f9, f9, f9);
/*     */           
/* 131 */           for (int l = 0; l < b0; l++) {
/* 132 */             GL11.glPushMatrix();
/*     */             
/* 134 */             if (l > 0) {
/* 135 */               float f6 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
/* 136 */               float f7 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
/* 137 */               float f8 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
/* 138 */               GL11.glTranslatef(f6, f7, f8);
/*     */             }
/*     */             
/* 141 */             this.renderBlocksRi.func_147800_a(block, itemstack.func_77960_j(), 1.0F);
/* 142 */             GL11.glPopMatrix();
/*     */           }
/*     */           
/* 145 */           if (block.func_149701_w() > 0) {
/* 146 */             GL11.glDisable(3042);
/*     */           }
/*     */           
/*     */ 
/*     */         }
/* 151 */         else if (itemstack.func_77973_b().func_77623_v()) {
/* 152 */           if (renderInFrame) {
/* 153 */             GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
/* 154 */             GL11.glTranslatef(0.0F, -0.05F, 0.0F);
/*     */           } else {
/* 156 */             GL11.glScalef(0.5F, 0.5F, 0.5F);
/*     */           }
/*     */           
/* 159 */           for (int j = 0; j < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); j++) {
/* 160 */             this.random.setSeed(187L);
/* 161 */             IIcon iicon1 = itemstack.func_77973_b().getIcon(itemstack, j);
/*     */             
/* 163 */             if (this.renderWithColor) {
/* 164 */               int k = itemstack.func_77973_b().func_82790_a(itemstack, j);
/* 165 */               float f5 = (k >> 16 & 0xFF) / 255.0F;
/* 166 */               float f6 = (k >> 8 & 0xFF) / 255.0F;
/* 167 */               float f7 = (k & 0xFF) / 255.0F;
/* 168 */               GL11.glColor4f(f5, f6, f7, 1.0F);
/* 169 */               renderDroppedItem(par1EntityItem, iicon1, b0, par9, f5, f6, f7, j);
/*     */             } else {
/* 171 */               renderDroppedItem(par1EntityItem, iicon1, b0, par9, 1.0F, 1.0F, 1.0F, j);
/*     */             }
/*     */           }
/*     */         } else {
/* 175 */           if ((itemstack != null) && ((itemstack.func_77973_b() instanceof ItemCloth))) {
/* 176 */             GL11.glAlphaFunc(516, 0.1F);
/* 177 */             GL11.glEnable(3042);
/* 178 */             OpenGlHelper.func_148821_a(770, 771, 1, 0);
/*     */           }
/*     */           
/* 181 */           if (renderInFrame) {
/* 182 */             GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
/* 183 */             GL11.glTranslatef(0.0F, -0.05F, 0.0F);
/*     */           } else {
/* 185 */             GL11.glScalef(0.5F, 0.5F, 0.5F);
/*     */           }
/*     */           
/* 188 */           IIcon iicon = itemstack.func_77954_c();
/*     */           
/* 190 */           if (this.renderWithColor) {
/* 191 */             int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 192 */             float f4 = (i >> 16 & 0xFF) / 255.0F;
/* 193 */             float f5 = (i >> 8 & 0xFF) / 255.0F;
/* 194 */             float f6 = (i & 0xFF) / 255.0F;
/* 195 */             renderDroppedItem(par1EntityItem, iicon, b0, par9, f4, f5, f6);
/*     */           } else {
/* 197 */             renderDroppedItem(par1EntityItem, iicon, b0, par9, 1.0F, 1.0F, 1.0F);
/*     */           }
/*     */           
/* 200 */           if ((itemstack != null) && ((itemstack.func_77973_b() instanceof ItemCloth))) {
/* 201 */             GL11.glDisable(3042);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 206 */       GL11.glDisable(32826);
/* 207 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ResourceLocation getEntityTexture(EntityItem par1EntityItem)
/*     */   {
/* 216 */     return this.field_76990_c.field_78724_e.func_130087_a(par1EntityItem.func_92059_d().func_94608_d());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void renderDroppedItem(EntityItem par1EntityItem, IIcon par2Icon, int par3, float par4, float par5, float par6, float par7)
/*     */   {
/* 223 */     renderDroppedItem(par1EntityItem, par2Icon, par3, par4, par5, par6, par7, 0);
/*     */   }
/*     */   
/*     */   private void renderDroppedItem(EntityItem par1EntityItem, IIcon par2Icon, int par3, float par4, float par5, float par6, float par7, int pass)
/*     */   {
/* 228 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 230 */     if (par2Icon == null) {
/* 231 */       TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
/* 232 */       ResourceLocation resourcelocation = texturemanager.func_130087_a(par1EntityItem.func_92059_d().func_94608_d());
/* 233 */       par2Icon = ((TextureMap)texturemanager.func_110581_b(resourcelocation)).func_110572_b("missingno");
/*     */     }
/*     */     
/* 236 */     float f14 = par2Icon.func_94209_e();
/* 237 */     float f15 = par2Icon.func_94212_f();
/* 238 */     float f4 = par2Icon.func_94206_g();
/* 239 */     float f5 = par2Icon.func_94210_h();
/* 240 */     float f6 = 1.0F;
/* 241 */     float f7 = 0.5F;
/* 242 */     float f8 = 0.25F;
/*     */     
/*     */ 
/* 245 */     if ((this.alwaysFancy) || (this.field_76990_c.field_78733_k.field_74347_j)) {
/* 246 */       GL11.glPushMatrix();
/*     */       
/* 248 */       if (renderInFrame) {
/* 249 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 251 */         GL11.glRotatef(((par1EntityItem.field_70292_b + par4) / 20.0F + par1EntityItem.field_70290_d) * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       
/*     */ 
/* 255 */       float f9 = 0.0625F;
/* 256 */       float f10 = 0.021875F;
/* 257 */       ItemStack itemstack = par1EntityItem.func_92059_d();
/* 258 */       int j = itemstack.field_77994_a;
/*     */       
/*     */       byte b0;
/* 261 */       if (j < 2) {
/* 262 */         b0 = 1; } else { byte b0;
/* 263 */         if (j < 16) {
/* 264 */           b0 = 2; } else { byte b0;
/* 265 */           if (j < 32) {
/* 266 */             b0 = 3;
/*     */           } else
/* 268 */             b0 = 4;
/*     */         }
/*     */       }
/* 271 */       byte b0 = getMiniItemCount(itemstack, b0);
/*     */       
/* 273 */       GL11.glTranslatef(-f7, -f8, -((f9 + f10) * b0 / 2.0F));
/*     */       
/* 275 */       for (int k = 0; k < b0; k++)
/*     */       {
/*     */ 
/* 278 */         if ((k > 0) && (shouldSpreadItems())) {
/* 279 */           float x = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
/* 280 */           float y = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
/* 281 */           float z = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
/* 282 */           GL11.glTranslatef(x, y, f9 + f10);
/*     */         } else {
/* 284 */           GL11.glTranslatef(0.0F, 0.0F, f9 + f10);
/*     */         }
/*     */         
/* 287 */         if (itemstack.func_94608_d() == 0) {
/* 288 */           func_110776_a(TextureMap.field_110575_b);
/*     */         } else {
/* 290 */           func_110776_a(TextureMap.field_110576_c);
/*     */         }
/*     */         
/* 293 */         GL11.glColor4f(par5, par6, par7, 1.0F);
/* 294 */         ItemRenderer.func_78439_a(tessellator, f15, f4, f14, f5, par2Icon.func_94211_a(), par2Icon.func_94216_b(), f9);
/*     */         
/*     */ 
/* 297 */         if (itemstack.hasEffect(pass)) {
/* 298 */           GL11.glDepthFunc(514);
/* 299 */           GL11.glDisable(2896);
/* 300 */           this.field_76990_c.field_78724_e.func_110577_a(RES_ITEM_GLINT);
/* 301 */           GL11.glEnable(3042);
/* 302 */           GL11.glBlendFunc(768, 1);
/* 303 */           float f11 = 0.76F;
/* 304 */           GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
/* 305 */           GL11.glMatrixMode(5890);
/* 306 */           GL11.glPushMatrix();
/* 307 */           float f12 = 0.125F;
/* 308 */           GL11.glScalef(f12, f12, f12);
/* 309 */           float f13 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F * 8.0F;
/* 310 */           GL11.glTranslatef(f13, 0.0F, 0.0F);
/* 311 */           GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 312 */           ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
/* 313 */           GL11.glPopMatrix();
/* 314 */           GL11.glPushMatrix();
/* 315 */           GL11.glScalef(f12, f12, f12);
/* 316 */           f13 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0F * 8.0F;
/* 317 */           GL11.glTranslatef(-f13, 0.0F, 0.0F);
/* 318 */           GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 319 */           ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
/* 320 */           GL11.glPopMatrix();
/* 321 */           GL11.glMatrixMode(5888);
/* 322 */           GL11.glDisable(3042);
/* 323 */           GL11.glEnable(2896);
/* 324 */           GL11.glDepthFunc(515);
/*     */         }
/*     */       }
/*     */       
/* 328 */       GL11.glPopMatrix();
/*     */     } else {
/* 330 */       for (int l = 0; l < par3; l++) {
/* 331 */         GL11.glPushMatrix();
/*     */         
/* 333 */         if (l > 0) {
/* 334 */           float f10 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
/* 335 */           float f16 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
/* 336 */           float f17 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
/* 337 */           GL11.glTranslatef(f10, f16, f17);
/*     */         }
/*     */         
/* 340 */         if (!renderInFrame) {
/* 341 */           GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 344 */         GL11.glColor4f(par5, par6, par7, 1.0F);
/* 345 */         tessellator.func_78382_b();
/* 346 */         tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 347 */         tessellator.func_78374_a(0.0F - f7, 0.0F - f8, 0.0D, f14, f5);
/* 348 */         tessellator.func_78374_a(f6 - f7, 0.0F - f8, 0.0D, f15, f5);
/* 349 */         tessellator.func_78374_a(f6 - f7, 1.0F - f8, 0.0D, f15, f4);
/* 350 */         tessellator.func_78374_a(0.0F - f7, 1.0F - f8, 0.0D, f14, f4);
/* 351 */         tessellator.func_78381_a();
/* 352 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderItemIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5)
/*     */   {
/* 362 */     renderItemIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5, false);
/*     */   }
/*     */   
/*     */   public void renderItemIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5, boolean renderEffect)
/*     */   {
/* 367 */     int k = par3ItemStack.func_77960_j();
/* 368 */     Object object = par3ItemStack.func_77954_c();
/* 369 */     GL11.glEnable(3042);
/* 370 */     OpenGlHelper.func_148821_a(770, 771, 1, 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 376 */     if ((par3ItemStack.func_94608_d() == 0) && (RenderBlocks.func_147739_a(Block.func_149634_a(par3ItemStack.func_77973_b()).func_149645_b())))
/*     */     {
/* 378 */       par2TextureManager.func_110577_a(TextureMap.field_110575_b);
/* 379 */       Block block = Block.func_149634_a(par3ItemStack.func_77973_b());
/* 380 */       GL11.glPushMatrix();
/* 381 */       GL11.glTranslatef(par4 - 2, par5 + 3, -3.0F + this.zLevel);
/* 382 */       GL11.glScalef(10.0F, 10.0F, 10.0F);
/* 383 */       GL11.glTranslatef(1.0F, 0.5F, 1.0F);
/* 384 */       GL11.glScalef(1.0F, 1.0F, -1.0F);
/* 385 */       GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
/* 386 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 387 */       int l = par3ItemStack.func_77973_b().func_82790_a(par3ItemStack, 0);
/* 388 */       float f3 = (l >> 16 & 0xFF) / 255.0F;
/* 389 */       float f4 = (l >> 8 & 0xFF) / 255.0F;
/* 390 */       float f = (l & 0xFF) / 255.0F;
/*     */       
/* 392 */       if (this.renderWithColor) {
/* 393 */         GL11.glColor4f(f3, f4, f, 1.0F);
/*     */       }
/*     */       
/* 396 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 397 */       this.renderBlocksRi.field_147844_c = this.renderWithColor;
/* 398 */       this.renderBlocksRi.func_147800_a(block, k, 1.0F);
/* 399 */       this.renderBlocksRi.field_147844_c = true;
/* 400 */       GL11.glPopMatrix();
/* 401 */     } else if (par3ItemStack.func_77973_b().func_77623_v()) {
/* 402 */       GL11.glDisable(2896);
/* 403 */       GL11.glEnable(3008);
/* 404 */       par2TextureManager.func_110577_a(TextureMap.field_110576_c);
/* 405 */       GL11.glDisable(3008);
/* 406 */       GL11.glDisable(3553);
/* 407 */       GL11.glEnable(3042);
/* 408 */       OpenGlHelper.func_148821_a(0, 0, 0, 0);
/* 409 */       GL11.glColorMask(false, false, false, true);
/* 410 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 411 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 412 */       tessellator.func_78382_b();
/* 413 */       tessellator.func_78378_d(-1);
/* 414 */       tessellator.func_78377_a(par4 - 2, par5 + 18, this.zLevel);
/* 415 */       tessellator.func_78377_a(par4 + 18, par5 + 18, this.zLevel);
/* 416 */       tessellator.func_78377_a(par4 + 18, par5 - 2, this.zLevel);
/* 417 */       tessellator.func_78377_a(par4 - 2, par5 - 2, this.zLevel);
/* 418 */       tessellator.func_78381_a();
/* 419 */       GL11.glColorMask(true, true, true, true);
/* 420 */       GL11.glEnable(3553);
/* 421 */       GL11.glEnable(3008);
/*     */       
/* 423 */       Item item = par3ItemStack.func_77973_b();
/* 424 */       for (int l = 0; l < item.getRenderPasses(k); l++) {
/* 425 */         OpenGlHelper.func_148821_a(770, 771, 1, 0);
/* 426 */         par2TextureManager.func_110577_a(item.func_94901_k() == 0 ? TextureMap.field_110575_b : TextureMap.field_110576_c);
/*     */         
/* 428 */         IIcon iicon = item.getIcon(par3ItemStack, l);
/* 429 */         int i1 = par3ItemStack.func_77973_b().func_82790_a(par3ItemStack, l);
/* 430 */         float f = (i1 >> 16 & 0xFF) / 255.0F;
/* 431 */         float f1 = (i1 >> 8 & 0xFF) / 255.0F;
/* 432 */         float f2 = (i1 & 0xFF) / 255.0F;
/*     */         
/* 434 */         if (this.renderWithColor) {
/* 435 */           GL11.glColor4f(f, f1, f2, 1.0F);
/*     */         }
/*     */         
/* 438 */         GL11.glDisable(2896);
/*     */         
/*     */ 
/*     */ 
/* 442 */         GL11.glEnable(3008);
/*     */         
/* 444 */         renderIcon(par4, par5, iicon, 16, 16);
/*     */         
/* 446 */         GL11.glDisable(3008);
/* 447 */         GL11.glEnable(2896);
/*     */         
/* 449 */         if ((renderEffect) && (par3ItemStack.hasEffect(l))) {
/* 450 */           renderEffect(par2TextureManager, par4, par5);
/*     */         }
/*     */       }
/*     */       
/* 454 */       GL11.glDisable(3008);
/* 455 */       GL11.glEnable(2896);
/*     */     } else {
/* 457 */       GL11.glDisable(2896);
/* 458 */       ResourceLocation resourcelocation = par2TextureManager.func_130087_a(par3ItemStack.func_94608_d());
/* 459 */       par2TextureManager.func_110577_a(resourcelocation);
/*     */       
/* 461 */       if (object == null) {
/* 462 */         object = ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(resourcelocation)).func_110572_b("missingno");
/*     */       }
/*     */       
/*     */ 
/* 466 */       int l = par3ItemStack.func_77973_b().func_82790_a(par3ItemStack, 0);
/* 467 */       float f3 = (l >> 16 & 0xFF) / 255.0F;
/* 468 */       float f4 = (l >> 8 & 0xFF) / 255.0F;
/* 469 */       float f = (l & 0xFF) / 255.0F;
/*     */       
/* 471 */       if (this.renderWithColor) {
/* 472 */         GL11.glColor4f(f3, f4, f, 1.0F);
/*     */       }
/*     */       
/* 475 */       GL11.glDisable(2896);
/*     */       
/*     */ 
/*     */ 
/* 479 */       GL11.glEnable(3008);
/*     */       
/* 481 */       renderIcon(par4, par5, (IIcon)object, 16, 16);
/*     */       
/* 483 */       GL11.glDisable(3008);
/* 484 */       GL11.glEnable(2896);
/*     */       
/* 486 */       if ((renderEffect) && (par3ItemStack.hasEffect(0))) {
/* 487 */         renderEffect(par2TextureManager, par4, par5);
/*     */       }
/* 489 */       GL11.glEnable(2896);
/*     */     }
/*     */     
/* 492 */     GL11.glEnable(2884);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderItemAndEffectIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, final ItemStack par3ItemStack, int par4, int par5)
/*     */   {
/* 501 */     if (par3ItemStack != null) {
/* 502 */       this.zLevel += 50.0F;
/*     */       try
/*     */       {
/* 505 */         if (!ForgeHooksClient.renderInventoryItem(this.field_147909_c, par2TextureManager, par3ItemStack, this.renderWithColor, this.zLevel, par4, par5))
/*     */         {
/* 507 */           renderItemIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5, true);
/*     */         }
/*     */       } catch (Throwable throwable) {
/* 510 */         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering item");
/* 511 */         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being rendered");
/* 512 */         crashreportcategory.func_71500_a("Item Type", new Callable() {
/*     */           private static final String __OBFID = "CL_00001004";
/*     */           
/*     */           public String call() {
/* 516 */             return String.valueOf(par3ItemStack.func_77973_b());
/*     */           }
/* 518 */         });
/* 519 */         crashreportcategory.func_71500_a("Item Aux", new Callable() {
/*     */           private static final String __OBFID = "CL_00001005";
/*     */           
/*     */           public String call() {
/* 523 */             return String.valueOf(par3ItemStack.func_77960_j());
/*     */           }
/* 525 */         });
/* 526 */         crashreportcategory.func_71500_a("Item NBT", new Callable() {
/*     */           private static final String __OBFID = "CL_00001006";
/*     */           
/*     */           public String call() {
/* 530 */             return String.valueOf(par3ItemStack.func_77978_p());
/*     */           }
/* 532 */         });
/* 533 */         crashreportcategory.func_71500_a("Item Foil", new Callable() {
/*     */           private static final String __OBFID = "CL_00001007";
/*     */           
/*     */           public String call() {
/* 537 */             return String.valueOf(par3ItemStack.func_77962_s());
/*     */           }
/* 539 */         });
/* 540 */         throw new ReportedException(crashreport);
/*     */       }
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
/* 560 */       this.zLevel -= 50.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderEffect(TextureManager manager, int x, int y) {
/* 565 */     GL11.glDepthFunc(514);
/* 566 */     GL11.glDisable(2896);
/* 567 */     GL11.glDepthMask(false);
/* 568 */     manager.func_110577_a(RES_ITEM_GLINT);
/* 569 */     GL11.glEnable(3008);
/* 570 */     GL11.glEnable(3042);
/* 571 */     GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
/* 572 */     renderGlint(x * 431278612 + y * 32178161, x - 2, y - 2, 20, 20);
/* 573 */     GL11.glDepthMask(true);
/* 574 */     GL11.glDisable(3042);
/* 575 */     GL11.glDisable(3008);
/* 576 */     GL11.glEnable(2896);
/* 577 */     GL11.glDepthFunc(515);
/*     */   }
/*     */   
/*     */   private void renderGlint(int par1, int par2, int par3, int par4, int par5) {
/* 581 */     for (int j1 = 0; j1 < 2; j1++) {
/* 582 */       OpenGlHelper.func_148821_a(772, 1, 0, 0);
/* 583 */       float f = 0.00390625F;
/* 584 */       float f1 = 0.00390625F;
/* 585 */       float f2 = (float)(Minecraft.func_71386_F() % (3000 + j1 * 1873)) / (3000.0F + j1 * 1873) * 256.0F;
/* 586 */       float f3 = 0.0F;
/* 587 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 588 */       float f4 = 4.0F;
/*     */       
/* 590 */       if (j1 == 1) {
/* 591 */         f4 = -1.0F;
/*     */       }
/*     */       
/* 594 */       tessellator.func_78382_b();
/* 595 */       tessellator.func_78374_a(par2 + 0, par3 + par5, this.zLevel, (f2 + par5 * f4) * f, (f3 + par5) * f1);
/*     */       
/* 597 */       tessellator.func_78374_a(par2 + par4, par3 + par5, this.zLevel, (f2 + par4 + par5 * f4) * f, (f3 + par5) * f1);
/*     */       
/* 599 */       tessellator.func_78374_a(par2 + par4, par3 + 0, this.zLevel, (f2 + par4) * f, (f3 + 0.0F) * f1);
/*     */       
/* 601 */       tessellator.func_78374_a(par2 + 0, par3 + 0, this.zLevel, (f2 + 0.0F) * f, (f3 + 0.0F) * f1);
/*     */       
/* 603 */       tessellator.func_78381_a();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5)
/*     */   {
/* 613 */     renderItemOverlayIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5, (String)null);
/*     */   }
/*     */   
/*     */   public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5, String par6Str)
/*     */   {
/* 618 */     if (par3ItemStack != null) {
/* 619 */       if ((par3ItemStack.field_77994_a > 1) || (par6Str != null)) {
/* 620 */         String s1 = par6Str == null ? String.valueOf(par3ItemStack.field_77994_a) : par6Str;
/* 621 */         GL11.glDisable(2896);
/* 622 */         GL11.glDisable(2929);
/* 623 */         GL11.glDisable(3042);
/* 624 */         par1FontRenderer.func_78261_a(s1, par4 + 19 - 2 - par1FontRenderer.func_78256_a(s1), par5 + 6 + 3, 16777215);
/* 625 */         GL11.glEnable(2896);
/* 626 */         GL11.glEnable(2929);
/*     */       }
/*     */       
/* 629 */       if (par3ItemStack.func_77973_b().showDurabilityBar(par3ItemStack)) {
/* 630 */         double health = par3ItemStack.func_77973_b().getDurabilityForDisplay(par3ItemStack);
/* 631 */         int j1 = (int)Math.round(13.0D - health * 13.0D);
/* 632 */         int k = (int)Math.round(255.0D - health * 255.0D);
/* 633 */         GL11.glDisable(2896);
/* 634 */         GL11.glDisable(2929);
/* 635 */         GL11.glDisable(3553);
/* 636 */         GL11.glDisable(3008);
/* 637 */         GL11.glDisable(3042);
/* 638 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 639 */         int l = 255 - k << 16 | k << 8;
/* 640 */         int i1 = (255 - k) / 4 << 16 | 0x3F00;
/* 641 */         renderQuad(tessellator, par4 + 2, par5 + 13, 13, 2, 0);
/* 642 */         renderQuad(tessellator, par4 + 2, par5 + 13, 12, 1, i1);
/* 643 */         renderQuad(tessellator, par4 + 2, par5 + 13, j1, 1, l);
/* 644 */         GL11.glEnable(3553);
/* 645 */         GL11.glEnable(2896);
/* 646 */         GL11.glEnable(2929);
/* 647 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void renderQuad(Tessellator par1Tessellator, int par2, int par3, int par4, int par5, int par6)
/*     */   {
/* 657 */     par1Tessellator.func_78382_b();
/* 658 */     par1Tessellator.func_78378_d(par6);
/* 659 */     par1Tessellator.func_78377_a(par2 + 0, par3 + 0, 0.0D);
/* 660 */     par1Tessellator.func_78377_a(par2 + 0, par3 + par5, 0.0D);
/* 661 */     par1Tessellator.func_78377_a(par2 + par4, par3 + par5, 0.0D);
/* 662 */     par1Tessellator.func_78377_a(par2 + par4, par3 + 0, 0.0D);
/* 663 */     par1Tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5) {
/* 667 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 668 */     tessellator.func_78382_b();
/* 669 */     tessellator.func_78374_a(par1 + 0, par2 + par5, this.zLevel, par3Icon.func_94209_e(), par3Icon.func_94210_h());
/*     */     
/* 671 */     tessellator.func_78374_a(par1 + par4, par2 + par5, this.zLevel, par3Icon.func_94212_f(), par3Icon.func_94210_h());
/*     */     
/* 673 */     tessellator.func_78374_a(par1 + par4, par2 + 0, this.zLevel, par3Icon.func_94212_f(), par3Icon.func_94206_g());
/*     */     
/* 675 */     tessellator.func_78374_a(par1 + 0, par2 + 0, this.zLevel, par3Icon.func_94209_e(), par3Icon.func_94206_g());
/*     */     
/* 677 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 685 */     return getEntityTexture((EntityItem)par1Entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 697 */     doRender((EntityItem)par1Entity, par2, par4, par6, par8, par9);
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
/*     */   public boolean shouldSpreadItems()
/*     */   {
/* 711 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean shouldBob()
/*     */   {
/* 720 */     return true;
/*     */   }
/*     */   
/*     */   public byte getMiniBlockCount(ItemStack stack, byte original) {
/* 724 */     return original;
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
/*     */   public byte getMiniItemCount(ItemStack stack, byte original)
/*     */   {
/* 738 */     return original;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderItem3d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */