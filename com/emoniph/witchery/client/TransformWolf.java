/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityBroom;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.model.ModelWolf;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RenderWolf;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class TransformWolf
/*     */ {
/*     */   private EntityWolf proxyEntity;
/*  30 */   private RenderWolf proxyRenderer = new RenderWolf(new ModelWolf(), new ModelWolf(), 0.5F);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityLivingBase getModel()
/*     */   {
/*  37 */     return this.proxyEntity;
/*     */   }
/*     */   
/*     */   public void syncModelWith(EntityLivingBase entity, boolean frontface) {
/*  41 */     if (this.proxyEntity == null) {
/*  42 */       this.proxyEntity = new EntityWolf(entity.field_70170_p);
/*  43 */     } else if (this.proxyEntity.field_70170_p != entity.field_70170_p) {
/*  44 */       this.proxyEntity.func_70029_a(entity.field_70170_p);
/*     */     }
/*     */     
/*  47 */     this.proxyEntity.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*     */     
/*  49 */     this.proxyEntity.field_70142_S = entity.field_70142_S;
/*  50 */     this.proxyEntity.field_70137_T = entity.field_70137_T;
/*  51 */     this.proxyEntity.field_70136_U = entity.field_70136_U;
/*     */     
/*  53 */     this.proxyEntity.field_70159_w = entity.field_70159_w;
/*  54 */     this.proxyEntity.field_70181_x = entity.field_70181_x;
/*  55 */     this.proxyEntity.field_70179_y = entity.field_70179_y;
/*     */     
/*  57 */     this.proxyEntity.field_70701_bs = entity.field_70701_bs;
/*  58 */     this.proxyEntity.field_70702_br = entity.field_70702_br;
/*     */     
/*  60 */     this.proxyEntity.field_70122_E = entity.field_70122_E;
/*     */     
/*  62 */     this.proxyEntity.field_70169_q = entity.field_70169_q;
/*  63 */     this.proxyEntity.field_70167_r = entity.field_70167_r;
/*  64 */     this.proxyEntity.field_70166_s = entity.field_70166_s;
/*     */     
/*  66 */     this.proxyEntity.field_70125_A = entity.field_70125_A;
/*  67 */     this.proxyEntity.field_70177_z = entity.field_70177_z;
/*  68 */     this.proxyEntity.field_70759_as = entity.field_70759_as;
/*     */     
/*  70 */     this.proxyEntity.field_70127_C = entity.field_70127_C;
/*  71 */     this.proxyEntity.field_70126_B = entity.field_70126_B;
/*  72 */     this.proxyEntity.field_70758_at = entity.field_70758_at;
/*     */     
/*  74 */     this.proxyEntity.field_70754_ba = entity.field_70754_ba;
/*     */     
/*  76 */     this.proxyEntity.field_70721_aZ = entity.field_70721_aZ;
/*  77 */     this.proxyEntity.field_70722_aY = entity.field_70722_aY;
/*  78 */     this.proxyEntity.field_82175_bq = entity.field_82175_bq;
/*     */     
/*  80 */     this.proxyEntity.field_70733_aJ = entity.field_70733_aJ;
/*  81 */     this.proxyEntity.field_70732_aI = entity.field_70732_aI;
/*     */     
/*  83 */     this.proxyEntity.field_70761_aq = (frontface ? 0.0F : entity.field_70761_aq);
/*  84 */     this.proxyEntity.field_70760_ar = (frontface ? 0.0F : entity.field_70760_ar);
/*     */     
/*     */ 
/*  87 */     this.proxyEntity.field_70173_aa = entity.field_70173_aa;
/*  88 */     this.proxyEntity.field_70128_L = false;
/*  89 */     this.proxyEntity.field_70160_al = entity.field_70160_al;
/*     */     
/*  91 */     this.proxyEntity.field_70129_M = 0.0F;
/*     */     
/*  93 */     this.proxyEntity.func_70095_a(entity.func_70093_af());
/*  94 */     this.proxyEntity.func_70031_b(entity.func_70051_ag());
/*  95 */     this.proxyEntity.func_82142_c(entity.func_82150_aj());
/*     */     
/*  97 */     this.proxyEntity.func_70904_g(entity.func_70115_ae());
/*     */   }
/*     */   
/*     */   public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface)
/*     */   {
/* 102 */     syncModelWith(entity, frontface);
/* 103 */     this.proxyRenderer.func_76976_a(RenderManager.field_78727_a);
/* 104 */     float f1 = this.proxyEntity.field_70126_B + (this.proxyEntity.field_70177_z - this.proxyEntity.field_70126_B) * partialTicks;
/*     */     
/* 106 */     double d3 = -this.proxyEntity.field_70129_M;
/* 107 */     if ((this.proxyEntity.func_70093_af()) && (!(entity instanceof EntityPlayerSP))) {
/* 108 */       d3 -= 0.125D;
/*     */     }
/*     */     
/* 111 */     if (entity.func_70115_ae()) {
/* 112 */       Entity ridden = entity.field_70154_o;
/* 113 */       d3 += ridden.func_70042_X() + ((entity.field_70154_o instanceof EntityBroom) ? ridden.field_70131_O - 0.2D : 0.0D);
/*     */     }
/*     */     
/* 116 */     float f2 = 1.0F;
/* 117 */     GL11.glColor3f(f2, f2, f2);
/* 118 */     this.proxyRenderer.func_76986_a(this.proxyEntity, x, y + d3, z, frontface ? 0.0F : f1, partialTicks);
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
/*     */   protected void renderEquippedItems(ItemRenderer itemRenderer, EntityLivingBase p_77029_1_, float p_77029_2_)
/*     */   {
/* 148 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */     
/* 150 */     ItemStack itemstack = p_77029_1_.func_70694_bm();
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
/* 202 */     if ((itemstack != null) && (itemstack.func_77973_b() != null))
/*     */     {
/* 204 */       Item item = itemstack.func_77973_b();
/* 205 */       GL11.glPushMatrix();
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
/* 216 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 218 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 219 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 221 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
/*     */       {
/* 223 */         float f1 = 0.5F;
/* 224 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 225 */         f1 *= 0.75F;
/* 226 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 227 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 228 */         GL11.glScalef(-f1, -f1, f1);
/*     */       }
/* 230 */       else if (item == Items.field_151031_f)
/*     */       {
/* 232 */         float f1 = 0.625F;
/* 233 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 234 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 235 */         GL11.glScalef(f1, -f1, f1);
/* 236 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 237 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 239 */       else if (item.func_77662_d())
/*     */       {
/* 241 */         float f1 = 0.625F;
/*     */         
/* 243 */         if (item.func_77629_n_())
/*     */         {
/* 245 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 246 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/*     */ 
/* 250 */         GL11.glScalef(f1, -f1, f1);
/* 251 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 252 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else
/*     */       {
/* 256 */         float f1 = 0.375F;
/* 257 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 258 */         GL11.glScalef(f1, f1, f1);
/* 259 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 260 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 261 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 268 */       if (itemstack.func_77973_b().func_77623_v())
/*     */       {
/* 270 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++)
/*     */         {
/* 272 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 273 */           float f5 = (j >> 16 & 0xFF) / 255.0F;
/* 274 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 275 */           float f3 = (j & 0xFF) / 255.0F;
/* 276 */           GL11.glColor4f(f5, f2, f3, 1.0F);
/* 277 */           itemRenderer.func_78443_a(p_77029_1_, itemstack, i);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 282 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 283 */       float f4 = (i >> 16 & 0xFF) / 255.0F;
/* 284 */       float f5 = (i >> 8 & 0xFF) / 255.0F;
/* 285 */       float f2 = (i & 0xFF) / 255.0F;
/* 286 */       GL11.glColor4f(f4, f5, f2, 1.0F);
/* 287 */       itemRenderer.func_78443_a(p_77029_1_, itemstack, 0);
/*     */       
/*     */ 
/* 290 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/TransformWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */