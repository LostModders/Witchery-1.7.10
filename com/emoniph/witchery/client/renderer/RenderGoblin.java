/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelGoblin;
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import com.google.common.collect.Maps;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.ForgeHooksClient;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderGoblin
/*     */   extends RenderLiving
/*     */ {
/*     */   public ModelGoblin modelBipedMain;
/*     */   protected float field_77070_b;
/*     */   protected ModelGoblin field_82423_g;
/*     */   protected ModelGoblin field_82425_h;
/*  41 */   private static final Map field_110859_k = ;
/*  42 */   public static String[] bipedArmorFilenamePrefix = { "leather", "chainmail", "iron", "diamond", "gold" };
/*     */   
/*     */   public RenderGoblin(ModelGoblin par1ModelBiped, float par2) {
/*  45 */     this(par1ModelBiped, par2, 1.0F);
/*     */   }
/*     */   
/*     */   public RenderGoblin(ModelGoblin par1ModelBiped, float par2, float par3) {
/*  49 */     super(par1ModelBiped, par2);
/*  50 */     this.modelBipedMain = par1ModelBiped;
/*  51 */     this.field_77070_b = par3;
/*  52 */     func_82421_b();
/*     */   }
/*     */   
/*     */   protected void func_82421_b() {
/*  56 */     this.field_82423_g = new ModelGoblin(1.0F);
/*  57 */     this.field_82425_h = new ModelGoblin(0.5F);
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
/*     */   protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
/*     */   {
/*  83 */     ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
/*     */     
/*  85 */     if (itemstack != null) {
/*  86 */       Item item = itemstack.func_77973_b();
/*     */       
/*  88 */       if ((item instanceof ItemArmor)) {
/*  89 */         ItemArmor itemarmor = (ItemArmor)item;
/*  90 */         func_110776_a(getArmorResource(par1EntityLiving, itemstack, par2, null));
/*  91 */         ModelGoblin modelbiped = par2 == 2 ? this.field_82425_h : this.field_82423_g;
/*  92 */         modelbiped.bipedHead.field_78806_j = (par2 == 0);
/*     */         
/*  94 */         modelbiped.bipedBody.field_78806_j = ((par2 == 1) || (par2 == 2));
/*  95 */         modelbiped.bipedRightArm.field_78806_j = (par2 == 1);
/*  96 */         modelbiped.bipedLeftArm.field_78806_j = (par2 == 1);
/*  97 */         modelbiped.bipedRightLeg.field_78806_j = ((par2 == 2) || (par2 == 3));
/*  98 */         modelbiped.bipedLeftLeg.field_78806_j = ((par2 == 2) || (par2 == 3));
/*     */         
/* 100 */         func_77042_a(modelbiped);
/* 101 */         modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
/* 102 */         modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
/* 103 */         modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
/*     */         
/*     */ 
/*     */ 
/* 107 */         int j = itemarmor.func_82814_b(itemstack);
/* 108 */         if (j != -1) {
/* 109 */           float f1 = (j >> 16 & 0xFF) / 255.0F;
/* 110 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 111 */           float f3 = (j & 0xFF) / 255.0F;
/* 112 */           GL11.glColor3f(f1, f2, f3);
/*     */           
/* 114 */           if (itemstack.func_77948_v()) {
/* 115 */             return 31;
/*     */           }
/*     */           
/* 118 */           return 16;
/*     */         }
/*     */         
/* 121 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */         
/* 123 */         if (itemstack.func_77948_v()) {
/* 124 */           return 15;
/*     */         }
/*     */         
/* 127 */         return 1;
/*     */       }
/*     */     }
/*     */     
/* 131 */     return -1;
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLiving par1EntityLivingBase, int par2, float par3) {
/* 135 */     ItemStack itemstack = par1EntityLivingBase.func_130225_q(3 - par2);
/*     */     
/* 137 */     if (itemstack != null) {
/* 138 */       Item item = itemstack.func_77973_b();
/*     */       
/* 140 */       if ((item instanceof ItemArmor)) {
/* 141 */         func_110776_a(getArmorResource(par1EntityLivingBase, itemstack, par2, "overlay"));
/* 142 */         float f1 = 1.0F;
/* 143 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 150 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 151 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/* 152 */     func_82420_a(par1EntityLiving, itemstack);
/* 153 */     double d3 = par4 - par1EntityLiving.field_70129_M;
/*     */     
/* 155 */     if (par1EntityLiving.func_70093_af()) {
/* 156 */       d3 -= 0.125D;
/*     */     }
/*     */     
/* 159 */     super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
/* 160 */     this.field_82423_g.aimedBow = (this.field_82425_h.aimedBow = this.modelBipedMain.aimedBow = 0);
/* 161 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = 0);
/* 162 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 0);
/*     */   }
/*     */   
/* 165 */   private static final ResourceLocation TEXTURE0_URL = new ResourceLocation("witchery", "textures/entities/goblin.png");
/* 166 */   private static final ResourceLocation TEXTURE1_URL = new ResourceLocation("witchery", "textures/entities/goblin2.png");
/* 167 */   private static final ResourceLocation TEXTURE2_URL = new ResourceLocation("witchery", "textures/entities/goblin3.png");
/* 168 */   private static final ResourceLocation TEXTURE3_URL = new ResourceLocation("witchery", "textures/entities/goblin4.png");
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLiving entity) {
/* 171 */     if ((entity instanceof EntityGoblin)) {
/* 172 */       switch (((EntityGoblin)entity).getProfession()) {
/*     */       case 0: 
/* 174 */         return TEXTURE1_URL;
/*     */       case 1: 
/* 176 */         return TEXTURE0_URL;
/*     */       case 2: 
/* 178 */         return TEXTURE3_URL;
/*     */       case 3: 
/* 180 */         return TEXTURE2_URL;
/*     */       }
/*     */     }
/* 183 */     return TEXTURE0_URL;
/*     */   }
/*     */   
/*     */   protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
/* 187 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = par2ItemStack != null ? 1 : 0);
/*     */     
/* 189 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = par1EntityLiving.func_70093_af());
/*     */   }
/*     */   
/*     */   protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
/* 193 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 194 */     super.func_77029_c(par1EntityLiving, par2);
/* 195 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/* 196 */     ItemStack itemstack1 = par1EntityLiving.func_130225_q(3);
/*     */     
/*     */ 
/*     */ 
/* 200 */     if (itemstack1 != null) {
/* 201 */       GL11.glPushMatrix();
/* 202 */       this.modelBipedMain.bipedHead.func_78794_c(0.0625F);
/* 203 */       Item item = itemstack1.func_77973_b();
/*     */       
/* 205 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
/* 206 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 208 */       if ((item instanceof ItemBlock)) {
/* 209 */         if ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))) {
/* 210 */           float f1 = 0.625F;
/* 211 */           GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 212 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 213 */           GL11.glScalef(f1, -f1, -f1);
/*     */         }
/*     */         
/* 216 */         this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack1, 0);
/*     */       }
/*     */       
/* 219 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 222 */     if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
/* 223 */       Item item = itemstack.func_77973_b();
/* 224 */       GL11.glPushMatrix();
/*     */       
/* 226 */       if (this.field_77045_g.field_78091_s) {
/* 227 */         float f1 = 0.5F;
/* 228 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/* 229 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/* 230 */         GL11.glScalef(f1, f1, f1);
/*     */       }
/*     */       
/* 233 */       this.modelBipedMain.bipedRightArm.func_78794_c(0.0625F);
/* 234 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 236 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 237 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 239 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b())))) {
/* 240 */         float f1 = 0.5F;
/* 241 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 242 */         f1 *= 0.75F;
/* 243 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 244 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 245 */         GL11.glScalef(-f1, -f1, f1);
/* 246 */       } else if (item == Items.field_151031_f) {
/* 247 */         float f1 = 0.625F;
/* 248 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 249 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 250 */         GL11.glScalef(f1, -f1, f1);
/* 251 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 252 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 253 */       } else if (item.func_77662_d()) {
/* 254 */         float f1 = 0.625F;
/*     */         
/* 256 */         if (item.func_77629_n_()) {
/* 257 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 258 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/* 261 */         func_82422_c();
/* 262 */         GL11.glScalef(f1, -f1, f1);
/* 263 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 264 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 266 */         float f1 = 0.375F;
/* 267 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 268 */         GL11.glScalef(f1, f1, f1);
/* 269 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 270 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 271 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 278 */       if (itemstack.func_77973_b().func_77623_v()) {
/* 279 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++) {
/* 280 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 281 */           float f2 = (j >> 16 & 0xFF) / 255.0F;
/* 282 */           float f3 = (j >> 8 & 0xFF) / 255.0F;
/* 283 */           float f4 = (j & 0xFF) / 255.0F;
/* 284 */           GL11.glColor4f(f2, f3, f4, 1.0F);
/* 285 */           this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, i);
/*     */         }
/*     */       }
/* 288 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 289 */       float f5 = (i >> 16 & 0xFF) / 255.0F;
/* 290 */       float f2 = (i >> 8 & 0xFF) / 255.0F;
/* 291 */       float f3 = (i & 0xFF) / 255.0F;
/* 292 */       GL11.glColor4f(f5, f2, f3, 1.0F);
/* 293 */       this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, 0);
/*     */       
/*     */ 
/* 296 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82422_c() {
/* 301 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/*     */   {
/* 306 */     func_82408_c((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */   
/*     */   protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/*     */   {
/* 311 */     return shouldRenderPass((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */   
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 316 */     renderEquippedItems((EntityLiving)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 321 */     func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 326 */     return getEntityTexture((EntityLiving)par1Entity);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 331 */     func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
/* 335 */     ItemArmor item = (ItemArmor)stack.func_77973_b();
/* 336 */     String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { bipedArmorFilenamePrefix[item.field_77880_c], Integer.valueOf(slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", new Object[] { type }) });
/*     */     
/*     */ 
/* 339 */     s1 = ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
/* 340 */     ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);
/*     */     
/* 342 */     if (resourcelocation == null) {
/* 343 */       resourcelocation = new ResourceLocation(s1);
/* 344 */       field_110859_k.put(s1, resourcelocation);
/*     */     }
/*     */     
/* 347 */     return resourcelocation;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderGoblin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */