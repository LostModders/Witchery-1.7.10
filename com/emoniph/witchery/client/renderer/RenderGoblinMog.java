/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelGoblinMog;
/*     */ import com.emoniph.witchery.entity.EntityGoblinMog;
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
/*     */ import net.minecraft.entity.boss.BossStatus;
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
/*     */ public class RenderGoblinMog
/*     */   extends RenderLiving
/*     */ {
/*     */   public ModelGoblinMog modelBipedMain;
/*     */   protected float field_77070_b;
/*     */   protected ModelGoblinMog field_82423_g;
/*     */   protected ModelGoblinMog field_82425_h;
/*  42 */   private static final Map field_110859_k = ;
/*  43 */   public static String[] bipedArmorFilenamePrefix = { "leather", "chainmail", "iron", "diamond", "gold" };
/*     */   
/*     */   public RenderGoblinMog(ModelGoblinMog par1ModelBiped, float par2) {
/*  46 */     this(par1ModelBiped, par2, 1.0F);
/*     */   }
/*     */   
/*     */   public RenderGoblinMog(ModelGoblinMog par1ModelBiped, float par2, float par3) {
/*  50 */     super(par1ModelBiped, par2);
/*  51 */     this.modelBipedMain = par1ModelBiped;
/*  52 */     this.field_77070_b = par3;
/*  53 */     func_82421_b();
/*     */   }
/*     */   
/*     */   protected void func_82421_b() {
/*  57 */     this.field_82423_g = new ModelGoblinMog(1.0F);
/*  58 */     this.field_82425_h = new ModelGoblinMog(0.5F);
/*     */   }
/*     */   
/*     */   protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
/*  62 */     ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
/*     */     
/*  64 */     if (itemstack != null) {
/*  65 */       Item item = itemstack.func_77973_b();
/*     */       
/*  67 */       if ((item instanceof ItemArmor)) {
/*  68 */         ItemArmor itemarmor = (ItemArmor)item;
/*  69 */         func_110776_a(getArmorResource(par1EntityLiving, itemstack, par2, null));
/*  70 */         ModelGoblinMog modelbiped = par2 == 2 ? this.field_82425_h : this.field_82423_g;
/*  71 */         modelbiped.bipedHead.field_78806_j = (par2 == 0);
/*  72 */         modelbiped.bipedBody.field_78806_j = ((par2 == 1) || (par2 == 2));
/*  73 */         modelbiped.bipedRightArm.field_78806_j = (par2 == 1);
/*  74 */         modelbiped.bipedLeftArm.field_78806_j = (par2 == 1);
/*  75 */         modelbiped.bipedRightLeg.field_78806_j = ((par2 == 2) || (par2 == 3));
/*  76 */         modelbiped.bipedLeftLeg.field_78806_j = ((par2 == 2) || (par2 == 3));
/*  77 */         func_77042_a(modelbiped);
/*  78 */         modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
/*  79 */         modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
/*  80 */         modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
/*     */         
/*     */ 
/*     */ 
/*  84 */         int j = itemarmor.func_82814_b(itemstack);
/*  85 */         if (j != -1) {
/*  86 */           float f1 = (j >> 16 & 0xFF) / 255.0F;
/*  87 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/*  88 */           float f3 = (j & 0xFF) / 255.0F;
/*  89 */           GL11.glColor3f(f1, f2, f3);
/*     */           
/*  91 */           if (itemstack.func_77948_v()) {
/*  92 */             return 31;
/*     */           }
/*     */           
/*  95 */           return 16;
/*     */         }
/*     */         
/*  98 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */         
/* 100 */         if (itemstack.func_77948_v()) {
/* 101 */           return 15;
/*     */         }
/*     */         
/* 104 */         return 1;
/*     */       }
/*     */     }
/*     */     
/* 108 */     return -1;
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLiving par1EntityLivingBase, int par2, float par3) {
/* 112 */     ItemStack itemstack = par1EntityLivingBase.func_130225_q(3 - par2);
/*     */     
/* 114 */     if (itemstack != null) {
/* 115 */       Item item = itemstack.func_77973_b();
/*     */       
/* 117 */       if ((item instanceof ItemArmor)) {
/* 118 */         func_110776_a(getArmorResource(par1EntityLivingBase, itemstack, par2, "overlay"));
/* 119 */         float f1 = 1.0F;
/* 120 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 127 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 128 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/* 129 */     func_82420_a(par1EntityLiving, itemstack);
/* 130 */     double d3 = par4 - par1EntityLiving.field_70129_M;
/* 131 */     BossStatus.func_82824_a((EntityGoblinMog)par1EntityLiving, true);
/* 132 */     if (par1EntityLiving.func_70093_af()) {
/* 133 */       d3 -= 0.125D;
/*     */     }
/*     */     
/* 136 */     super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
/* 137 */     this.field_82423_g.aimedBow = (this.field_82425_h.aimedBow = this.modelBipedMain.aimedBow = 0);
/* 138 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = 0);
/* 139 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 0);
/*     */   }
/*     */   
/* 142 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/mog.png");
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLiving entity) {
/* 145 */     return TEXTURE_URL;
/*     */   }
/*     */   
/*     */   protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
/* 149 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = par2ItemStack != null ? 1 : 0);
/*     */     
/* 151 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = par1EntityLiving.func_70093_af());
/*     */   }
/*     */   
/*     */   protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
/* 155 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 156 */     super.func_77029_c(par1EntityLiving, par2);
/* 157 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/* 158 */     ItemStack itemstack1 = par1EntityLiving.func_130225_q(3);
/*     */     
/*     */ 
/*     */ 
/* 162 */     if (itemstack1 != null) {
/* 163 */       GL11.glPushMatrix();
/* 164 */       this.modelBipedMain.bipedHead.func_78794_c(0.0625F);
/* 165 */       Item item = itemstack1.func_77973_b();
/*     */       
/* 167 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
/* 168 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 170 */       if ((item instanceof ItemBlock)) {
/* 171 */         if ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))) {
/* 172 */           float f1 = 0.625F;
/* 173 */           GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 174 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 175 */           GL11.glScalef(f1, -f1, -f1);
/*     */         }
/*     */         
/* 178 */         this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack1, 0);
/*     */       }
/*     */       
/* 181 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 184 */     if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
/* 185 */       Item item = itemstack.func_77973_b();
/* 186 */       GL11.glPushMatrix();
/*     */       
/* 188 */       if (this.field_77045_g.field_78091_s) {
/* 189 */         float f1 = 0.5F;
/* 190 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/* 191 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/* 192 */         GL11.glScalef(f1, f1, f1);
/*     */       }
/*     */       
/* 195 */       this.modelBipedMain.bipedRightArm.func_78794_c(0.0625F);
/* 196 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 198 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 199 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 201 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b())))) {
/* 202 */         float f1 = 0.5F;
/* 203 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 204 */         f1 *= 0.75F;
/* 205 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 206 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 207 */         GL11.glScalef(-f1, -f1, f1);
/* 208 */       } else if (item == Items.field_151031_f) {
/* 209 */         float f1 = 0.625F;
/* 210 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 211 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 212 */         GL11.glScalef(f1, -f1, f1);
/* 213 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 214 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 215 */       } else if (item.func_77662_d()) {
/* 216 */         float f1 = 0.625F;
/*     */         
/* 218 */         if (item.func_77629_n_()) {
/* 219 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 220 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/* 223 */         func_82422_c();
/* 224 */         GL11.glScalef(f1, -f1, f1);
/* 225 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 226 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 228 */         float f1 = 0.375F;
/* 229 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 230 */         GL11.glScalef(f1, f1, f1);
/* 231 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 232 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 233 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 240 */       if (itemstack.func_77973_b().func_77623_v()) {
/* 241 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++) {
/* 242 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 243 */           float f2 = (j >> 16 & 0xFF) / 255.0F;
/* 244 */           float f3 = (j >> 8 & 0xFF) / 255.0F;
/* 245 */           float f4 = (j & 0xFF) / 255.0F;
/* 246 */           GL11.glColor4f(f2, f3, f4, 1.0F);
/* 247 */           this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, i);
/*     */         }
/*     */       }
/* 250 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 251 */       float f5 = (i >> 16 & 0xFF) / 255.0F;
/* 252 */       float f2 = (i >> 8 & 0xFF) / 255.0F;
/* 253 */       float f3 = (i & 0xFF) / 255.0F;
/* 254 */       GL11.glColor4f(f5, f2, f3, 1.0F);
/* 255 */       this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, 0);
/*     */       
/*     */ 
/* 258 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82422_c() {
/* 263 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/*     */   {
/* 268 */     func_82408_c((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */   
/*     */   protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/*     */   {
/* 273 */     return shouldRenderPass((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */   
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 278 */     renderEquippedItems((EntityLiving)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 283 */     func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 288 */     return getEntityTexture((EntityLiving)par1Entity);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 293 */     func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
/* 297 */     ItemArmor item = (ItemArmor)stack.func_77973_b();
/* 298 */     String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { bipedArmorFilenamePrefix[item.field_77880_c], Integer.valueOf(slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", new Object[] { type }) });
/*     */     
/*     */ 
/* 301 */     s1 = ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
/* 302 */     ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);
/*     */     
/* 304 */     if (resourcelocation == null) {
/* 305 */       resourcelocation = new ResourceLocation(s1);
/* 306 */       field_110859_k.put(s1, resourcelocation);
/*     */     }
/*     */     
/* 309 */     return resourcelocation;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderGoblinMog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */