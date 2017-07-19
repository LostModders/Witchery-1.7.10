/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelGoblinGulg;
/*     */ import com.emoniph.witchery.entity.EntityGoblinGulg;
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
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderGoblinGulg
/*     */   extends RenderLiving
/*     */ {
/*     */   public ModelGoblinGulg modelBipedMain;
/*     */   protected float field_77070_b;
/*     */   protected ModelGoblinGulg field_82423_g;
/*     */   protected ModelGoblinGulg field_82425_h;
/*  43 */   private static final Map field_110859_k = ;
/*  44 */   public static String[] bipedArmorFilenamePrefix = { "leather", "chainmail", "iron", "diamond", "gold" };
/*     */   
/*     */   public RenderGoblinGulg(ModelGoblinGulg par1ModelBiped, float par2) {
/*  47 */     this(par1ModelBiped, par2, 1.0F);
/*     */   }
/*     */   
/*     */   public RenderGoblinGulg(ModelGoblinGulg par1ModelBiped, float par2, float par3) {
/*  51 */     super(par1ModelBiped, par2);
/*  52 */     this.modelBipedMain = par1ModelBiped;
/*  53 */     this.field_77070_b = par3;
/*  54 */     func_82421_b();
/*     */   }
/*     */   
/*     */   protected void func_82421_b() {
/*  58 */     this.field_82423_g = new ModelGoblinGulg(1.0F);
/*  59 */     this.field_82425_h = new ModelGoblinGulg(0.5F);
/*     */   }
/*     */   
/*     */   protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
/*  63 */     ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
/*     */     
/*  65 */     if (itemstack != null) {
/*  66 */       Item item = itemstack.func_77973_b();
/*     */       
/*  68 */       if ((item instanceof ItemArmor)) {
/*  69 */         ItemArmor itemarmor = (ItemArmor)item;
/*  70 */         func_110776_a(getArmorResource(par1EntityLiving, itemstack, par2, null));
/*  71 */         ModelGoblinGulg modelbiped = par2 == 2 ? this.field_82425_h : this.field_82423_g;
/*  72 */         modelbiped.bipedHead.field_78806_j = (par2 == 0);
/*  73 */         modelbiped.bipedBody.field_78806_j = ((par2 == 1) || (par2 == 2));
/*  74 */         modelbiped.bipedRightArm.field_78806_j = (par2 == 1);
/*  75 */         modelbiped.bipedLeftArm.field_78806_j = (par2 == 1);
/*  76 */         modelbiped.bipedRightLeg.field_78806_j = ((par2 == 2) || (par2 == 3));
/*  77 */         modelbiped.bipedLeftLeg.field_78806_j = ((par2 == 2) || (par2 == 3));
/*  78 */         func_77042_a(modelbiped);
/*  79 */         modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
/*  80 */         modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
/*  81 */         modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
/*     */         
/*     */ 
/*     */ 
/*  85 */         int j = itemarmor.func_82814_b(itemstack);
/*  86 */         if (j != -1) {
/*  87 */           float f1 = (j >> 16 & 0xFF) / 255.0F;
/*  88 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/*  89 */           float f3 = (j & 0xFF) / 255.0F;
/*  90 */           GL11.glColor3f(f1, f2, f3);
/*     */           
/*  92 */           if (itemstack.func_77948_v()) {
/*  93 */             return 31;
/*     */           }
/*     */           
/*  96 */           return 16;
/*     */         }
/*     */         
/*  99 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */         
/* 101 */         if (itemstack.func_77948_v()) {
/* 102 */           return 15;
/*     */         }
/*     */         
/* 105 */         return 1;
/*     */       }
/*     */     }
/*     */     
/* 109 */     return -1;
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLiving par1EntityLivingBase, int par2, float par3) {
/* 113 */     ItemStack itemstack = par1EntityLivingBase.func_130225_q(3 - par2);
/*     */     
/* 115 */     if (itemstack != null) {
/* 116 */       Item item = itemstack.func_77973_b();
/*     */       
/* 118 */       if ((item instanceof ItemArmor)) {
/* 119 */         func_110776_a(getArmorResource(par1EntityLivingBase, itemstack, par2, "overlay"));
/* 120 */         float f1 = 1.0F;
/* 121 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 128 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 129 */     BossStatus.func_82824_a((EntityGoblinGulg)par1EntityLiving, true);
/* 130 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/* 131 */     func_82420_a(par1EntityLiving, itemstack);
/* 132 */     double d3 = par4 - par1EntityLiving.field_70129_M;
/*     */     
/* 134 */     if (par1EntityLiving.func_70093_af()) {
/* 135 */       d3 -= 0.125D;
/*     */     }
/*     */     
/* 138 */     super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
/* 139 */     this.field_82423_g.aimedBow = (this.field_82425_h.aimedBow = this.modelBipedMain.aimedBow = 0);
/* 140 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = 0);
/* 141 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 0);
/*     */   }
/*     */   
/* 144 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/gulg.png");
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLiving entity) {
/* 147 */     return TEXTURE_URL;
/*     */   }
/*     */   
/*     */   protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
/* 151 */     this.field_82423_g.heldItemRight = (this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = par2ItemStack != null ? 1 : 0);
/*     */     
/* 153 */     this.field_82423_g.isSneak = (this.field_82425_h.isSneak = this.modelBipedMain.isSneak = par1EntityLiving.func_70093_af());
/*     */   }
/*     */   
/*     */   protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
/* 157 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 158 */     super.func_77029_c(par1EntityLiving, par2);
/* 159 */     ItemStack itemstack = par1EntityLiving.func_70694_bm();
/* 160 */     ItemStack itemstack1 = par1EntityLiving.func_130225_q(3);
/*     */     
/*     */ 
/*     */ 
/* 164 */     if (itemstack1 != null) {
/* 165 */       GL11.glPushMatrix();
/* 166 */       this.modelBipedMain.bipedHead.func_78794_c(0.0625F);
/* 167 */       Item item = itemstack1.func_77973_b();
/*     */       
/* 169 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
/* 170 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 172 */       if ((item instanceof ItemBlock)) {
/* 173 */         if ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))) {
/* 174 */           float f1 = 0.625F;
/* 175 */           GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 176 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 177 */           GL11.glScalef(f1, -f1, -f1);
/*     */         }
/*     */         
/* 180 */         this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack1, 0);
/*     */       }
/*     */       
/* 183 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 186 */     if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
/* 187 */       Item item = itemstack.func_77973_b();
/* 188 */       GL11.glPushMatrix();
/*     */       
/* 190 */       if (this.field_77045_g.field_78091_s) {
/* 191 */         float f1 = 0.5F;
/* 192 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/* 193 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/* 194 */         GL11.glScalef(f1, f1, f1);
/*     */       }
/*     */       
/* 197 */       this.modelBipedMain.bipedRightArm.func_78794_c(0.0625F);
/* 198 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 200 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 201 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 203 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b())))) {
/* 204 */         float f1 = 0.5F;
/* 205 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 206 */         f1 *= 0.75F;
/* 207 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 208 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 209 */         GL11.glScalef(-f1, -f1, f1);
/* 210 */       } else if (item == Items.field_151031_f) {
/* 211 */         float f1 = 0.625F;
/* 212 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 213 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 214 */         GL11.glScalef(f1, -f1, f1);
/* 215 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 216 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 217 */       } else if (item.func_77662_d()) {
/* 218 */         float f1 = 0.625F;
/*     */         
/* 220 */         if (item.func_77629_n_()) {
/* 221 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 222 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/* 225 */         func_82422_c();
/* 226 */         GL11.glScalef(f1, -f1, f1);
/* 227 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 228 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 230 */         float f1 = 0.375F;
/* 231 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 232 */         GL11.glScalef(f1, f1, f1);
/* 233 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 234 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 235 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 242 */       if (itemstack.func_77973_b().func_77623_v()) {
/* 243 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++) {
/* 244 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 245 */           float f2 = (j >> 16 & 0xFF) / 255.0F;
/* 246 */           float f3 = (j >> 8 & 0xFF) / 255.0F;
/* 247 */           float f4 = (j & 0xFF) / 255.0F;
/* 248 */           GL11.glColor4f(f2, f3, f4, 1.0F);
/* 249 */           this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, i);
/*     */         }
/*     */       }
/* 252 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 253 */       float f5 = (i >> 16 & 0xFF) / 255.0F;
/* 254 */       float f2 = (i >> 8 & 0xFF) / 255.0F;
/* 255 */       float f3 = (i & 0xFF) / 255.0F;
/* 256 */       GL11.glColor4f(f5, f2, f3, 1.0F);
/* 257 */       this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, 0);
/*     */       
/*     */ 
/* 260 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82422_c() {
/* 265 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/*     */   {
/* 270 */     func_82408_c((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */   
/*     */   protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/*     */   {
/* 275 */     return shouldRenderPass((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */   
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 280 */     renderEquippedItems((EntityLiving)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 285 */     func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 290 */     return getEntityTexture((EntityLiving)par1Entity);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 295 */     func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
/* 299 */     ItemArmor item = (ItemArmor)stack.func_77973_b();
/* 300 */     String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { bipedArmorFilenamePrefix[item.field_77880_c], Integer.valueOf(slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", new Object[] { type }) });
/*     */     
/*     */ 
/* 303 */     s1 = ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
/* 304 */     ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);
/*     */     
/* 306 */     if (resourcelocation == null) {
/* 307 */       resourcelocation = new ResourceLocation(s1);
/* 308 */       field_110859_k.put(s1, resourcelocation);
/*     */     }
/*     */     
/* 311 */     return resourcelocation;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderGoblinGulg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */