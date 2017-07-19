/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelWolfman;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class RenderWolfman extends RenderLiving
/*     */ {
/*     */   public ModelWolfman modelBipedMain;
/*     */   protected float field_77070_b;
/*     */   protected ModelWolfman field_82423_g;
/*     */   protected ModelWolfman field_82425_h;
/*  41 */   private static final Map field_110859_k = ;
/*  42 */   public static String[] bipedArmorFilenamePrefix = { "leather", "chainmail", "iron", "diamond", "gold" };
/*     */   private static final String __OBFID = "CL_00001001";
/*     */   
/*     */   public RenderWolfman(ModelWolfman model, float shadow)
/*     */   {
/*  47 */     this(model, shadow, 1.0F);
/*     */   }
/*     */   
/*     */   public RenderWolfman(ModelWolfman p_i1258_1_, float p_i1258_2_, float p_i1258_3_) {
/*  51 */     super(p_i1258_1_, p_i1258_2_);
/*  52 */     this.modelBipedMain = p_i1258_1_;
/*  53 */     this.field_77070_b = p_i1258_3_;
/*  54 */     func_82421_b();
/*     */   }
/*     */   
/*     */   protected void func_82421_b() {
/*  58 */     this.field_82423_g = new ModelWolfman(1.0F);
/*  59 */     this.field_82425_h = new ModelWolfman(0.5F);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static ResourceLocation func_110857_a(ItemArmor p_110857_0_, int p_110857_1_)
/*     */   {
/*  65 */     return func_110858_a(p_110857_0_, p_110857_1_, (String)null);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static ResourceLocation func_110858_a(ItemArmor p_110858_0_, int p_110858_1_, String p_110858_2_)
/*     */   {
/*  71 */     String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { bipedArmorFilenamePrefix[p_110858_0_.field_77880_c], Integer.valueOf(p_110858_1_ == 2 ? 2 : 1), p_110858_2_ == null ? "" : String.format("_%s", new Object[] { p_110858_2_ }) });
/*     */     
/*     */ 
/*  74 */     ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);
/*     */     
/*  76 */     if (resourcelocation == null) {
/*  77 */       resourcelocation = new ResourceLocation(s1);
/*  78 */       field_110859_k.put(s1, resourcelocation);
/*     */     }
/*     */     
/*  81 */     return resourcelocation;
/*     */   }
/*     */   
/*     */   protected int shouldRenderPass(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_) {
/*  85 */     ItemStack itemstack = p_77032_1_.func_130225_q(3 - p_77032_2_);
/*     */     
/*  87 */     if (itemstack != null) {
/*  88 */       Item item = itemstack.func_77973_b();
/*     */       
/*  90 */       if ((item instanceof ItemArmor)) {
/*  91 */         ItemArmor itemarmor = (ItemArmor)item;
/*  92 */         func_110776_a(getArmorResource(p_77032_1_, itemstack, p_77032_2_, null));
/*  93 */         ModelWolfman modelbiped = p_77032_2_ == 2 ? this.field_82425_h : this.field_82423_g;
/*  94 */         modelbiped.headMain.field_78806_j = (p_77032_2_ == 0);
/*  95 */         modelbiped.bodyUpper.field_78806_j = ((p_77032_2_ == 1) || (p_77032_2_ == 2));
/*  96 */         modelbiped.armRight.field_78806_j = (p_77032_2_ == 1);
/*  97 */         modelbiped.armLeft.field_78806_j = (p_77032_2_ == 1);
/*  98 */         modelbiped.legRightUpper.field_78806_j = ((p_77032_2_ == 2) || (p_77032_2_ == 3));
/*  99 */         modelbiped.legLeftUpper.field_78806_j = ((p_77032_2_ == 2) || (p_77032_2_ == 3));
/*     */         
/*     */ 
/*     */ 
/* 103 */         func_77042_a(modelbiped);
/* 104 */         modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
/* 105 */         modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
/* 106 */         modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
/*     */         
/*     */ 
/*     */ 
/* 110 */         int j = itemarmor.func_82814_b(itemstack);
/* 111 */         if (j != -1) {
/* 112 */           float f1 = (j >> 16 & 0xFF) / 255.0F;
/* 113 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 114 */           float f3 = (j & 0xFF) / 255.0F;
/* 115 */           GL11.glColor3f(f1, f2, f3);
/*     */           
/* 117 */           if (itemstack.func_77948_v()) {
/* 118 */             return 31;
/*     */           }
/*     */           
/* 121 */           return 16;
/*     */         }
/*     */         
/* 124 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */         
/* 126 */         if (itemstack.func_77948_v()) {
/* 127 */           return 15;
/*     */         }
/*     */         
/* 130 */         return 1;
/*     */       }
/*     */     }
/*     */     
/* 134 */     return -1;
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLiving p_82408_1_, int p_82408_2_, float p_82408_3_) {
/* 138 */     ItemStack itemstack = p_82408_1_.func_130225_q(3 - p_82408_2_);
/*     */     
/* 140 */     if (itemstack != null) {
/* 141 */       Item item = itemstack.func_77973_b();
/*     */       
/* 143 */       if ((item instanceof ItemArmor)) {
/* 144 */         func_110776_a(getArmorResource(p_82408_1_, itemstack, p_82408_2_, "overlay"));
/* 145 */         float f1 = 1.0F;
/* 146 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/* 154 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 155 */     ItemStack itemstack = p_76986_1_.func_70694_bm();
/* 156 */     func_82420_a(p_76986_1_, itemstack);
/* 157 */     double d3 = p_76986_4_ - p_76986_1_.field_70129_M;
/*     */     
/* 159 */     if (p_76986_1_.func_70093_af()) {
/* 160 */       d3 -= 0.125D;
/*     */     }
/* 162 */     if ((p_76986_1_ instanceof EntityWolfman)) {
/* 163 */       EntityWolfman wolfman = (EntityWolfman)p_76986_1_;
/* 164 */       if ((itemstack != null) && (wolfman.itemInUseCount > 0)) {
/* 165 */         EnumAction enumaction = itemstack.func_77975_n();
/*     */         
/* 167 */         if (enumaction == EnumAction.block) {
/* 168 */           this.modelBipedMain.heldItemRight = 3;
/* 169 */         } else if (enumaction == EnumAction.bow) {
/* 170 */           this.modelBipedMain.aimedBow = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 178 */     super.func_76986_a(p_76986_1_, p_76986_2_, d3, p_76986_6_, p_76986_8_, p_76986_9_);
/* 179 */     this.field_82423_g.aimedBow = (this.field_82425_h.aimedBow = this.modelBipedMain.aimedBow = 0);
/* 180 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = 0);
/* 181 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 0);
/* 182 */     this.modelBipedMain.field_78093_q = false;
/*     */   }
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_)
/*     */   {
/* 187 */     return TEXTURE;
/*     */   }
/*     */   
/* 190 */   public static final ResourceLocation TEXTURE = new ResourceLocation("witchery", "textures/entities/wolfman.png");
/*     */   
/*     */   protected void func_82420_a(EntityLiving p_82420_1_, ItemStack p_82420_2_)
/*     */   {
/* 194 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = p_82420_2_ != null ? 1 : 0);
/*     */     
/* 196 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = p_82420_1_.func_70093_af());
/*     */   }
/*     */   
/*     */   protected void renderEquippedItems(EntityLiving p_77029_1_, float p_77029_2_)
/*     */   {
/* 201 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 202 */     super.func_77029_c(p_77029_1_, p_77029_2_);
/* 203 */     ItemStack itemstack = p_77029_1_.func_70694_bm();
/* 204 */     ItemStack itemstack1 = p_77029_1_.func_130225_q(3);
/*     */     
/*     */ 
/*     */ 
/* 208 */     if (itemstack1 != null) {
/* 209 */       GL11.glPushMatrix();
/* 210 */       this.modelBipedMain.headMain.func_78794_c(0.0625F);
/* 211 */       Item item = itemstack1.func_77973_b();
/*     */       
/* 213 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
/*     */       
/* 215 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/*     */ 
/*     */ 
/* 219 */       if ((item instanceof ItemBlock)) {
/* 220 */         if ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))) {
/* 221 */           float f1 = 0.625F;
/* 222 */           GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 223 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 224 */           GL11.glScalef(f1, -f1, -f1);
/*     */         }
/*     */         
/* 227 */         this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack1, 0);
/* 228 */       } else if (item == Items.field_151144_bL) {
/* 229 */         float f1 = 1.0625F;
/* 230 */         GL11.glScalef(f1, -f1, -f1);
/* 231 */         GameProfile gameprofile = null;
/*     */         
/* 233 */         if (itemstack1.func_77942_o()) {
/* 234 */           NBTTagCompound nbttagcompound = itemstack1.func_77978_p();
/*     */           
/* 236 */           if (nbttagcompound.func_150297_b("SkullOwner", 10)) {
/* 237 */             gameprofile = net.minecraft.nbt.NBTUtil.func_152459_a(nbttagcompound.func_74775_l("SkullOwner"));
/* 238 */           } else if ((nbttagcompound.func_150297_b("SkullOwner", 8)) && (!StringUtils.func_151246_b(nbttagcompound.func_74779_i("SkullOwner"))))
/*     */           {
/* 240 */             gameprofile = new GameProfile((java.util.UUID)null, nbttagcompound.func_74779_i("SkullOwner"));
/*     */           }
/*     */         }
/*     */         
/* 244 */         TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack1.func_77960_j(), gameprofile);
/*     */       }
/*     */       
/*     */ 
/* 248 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 251 */     if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
/* 252 */       Item item = itemstack.func_77973_b();
/* 253 */       GL11.glPushMatrix();
/*     */       
/* 255 */       if (this.field_77045_g.field_78091_s) {
/* 256 */         float f1 = 0.5F;
/* 257 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/* 258 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/* 259 */         GL11.glScalef(f1, f1, f1);
/*     */       }
/*     */       
/* 262 */       this.modelBipedMain.armRight.func_78794_c(0.0625F);
/* 263 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 265 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/*     */       
/* 267 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/*     */ 
/*     */ 
/* 271 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
/*     */       {
/* 273 */         float f1 = 0.5F;
/* 274 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 275 */         f1 *= 0.75F;
/* 276 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 277 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 278 */         GL11.glScalef(-f1, -f1, f1);
/* 279 */       } else if (item == Items.field_151031_f) {
/* 280 */         float f1 = 0.625F;
/* 281 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 282 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 283 */         GL11.glScalef(f1, -f1, f1);
/* 284 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 285 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 286 */       } else if (item.func_77662_d()) {
/* 287 */         float f1 = 0.625F;
/*     */         
/* 289 */         if (item.func_77629_n_()) {
/* 290 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 291 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/* 294 */         func_82422_c();
/* 295 */         GL11.glScalef(f1, -f1, f1);
/* 296 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 297 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 299 */         float f1 = 0.375F;
/* 300 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 301 */         GL11.glScalef(f1, f1, f1);
/* 302 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 303 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 304 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 311 */       if (itemstack.func_77973_b().func_77623_v()) {
/* 312 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++) {
/* 313 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 314 */           float f5 = (j >> 16 & 0xFF) / 255.0F;
/* 315 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 316 */           float f3 = (j & 0xFF) / 255.0F;
/* 317 */           GL11.glColor4f(f5, f2, f3, 1.0F);
/* 318 */           this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack, i);
/*     */         }
/*     */       }
/* 321 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 322 */       float f4 = (i >> 16 & 0xFF) / 255.0F;
/* 323 */       float f5 = (i >> 8 & 0xFF) / 255.0F;
/* 324 */       float f2 = (i & 0xFF) / 255.0F;
/* 325 */       GL11.glColor4f(f4, f5, f2, 1.0F);
/* 326 */       this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack, 0);
/*     */       
/*     */ 
/* 329 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82422_c() {
/* 334 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_)
/*     */   {
/* 339 */     func_82408_c((EntityLiving)p_82408_1_, p_82408_2_, p_82408_3_);
/*     */   }
/*     */   
/*     */   protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
/*     */   {
/* 344 */     return shouldRenderPass((EntityLiving)p_77032_1_, p_77032_2_, p_77032_3_);
/*     */   }
/*     */   
/*     */   protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_)
/*     */   {
/* 349 */     renderEquippedItems((EntityLiving)p_77029_1_, p_77029_2_);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/* 355 */     func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity p_110775_1_)
/*     */   {
/* 360 */     return getEntityTexture((EntityLiving)p_110775_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/* 366 */     func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */   
/*     */   public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
/* 370 */     ItemArmor item = (ItemArmor)stack.func_77973_b();
/* 371 */     String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { bipedArmorFilenamePrefix[item.field_77880_c], Integer.valueOf(slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", new Object[] { type }) });
/*     */     
/*     */ 
/*     */ 
/* 375 */     s1 = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
/* 376 */     ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);
/*     */     
/* 378 */     if (resourcelocation == null) {
/* 379 */       resourcelocation = new ResourceLocation(s1);
/* 380 */       field_110859_k.put(s1, resourcelocation);
/*     */     }
/*     */     
/* 383 */     return resourcelocation;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderWolfman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */