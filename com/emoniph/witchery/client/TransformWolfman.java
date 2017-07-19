/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelWolfman;
/*     */ import com.emoniph.witchery.client.renderer.RenderWolfman;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
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
/*     */ public class TransformWolfman
/*     */ {
/*     */   private EntityWolfman proxyEntity;
/*  30 */   private RenderWolfman proxyRenderer = new RenderWolfman(new ModelWolfman(), 0.5F);
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
/*  42 */       this.proxyEntity = new EntityWolfman(entity.field_70170_p);
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
/*  96 */     this.proxyEntity.func_70062_b(0, entity.func_70694_bm());
/*  97 */     this.proxyEntity.func_70019_c(entity.func_70113_ah());
/*  98 */     this.proxyEntity.setSitting(entity.func_70115_ae());
/*     */     
/* 100 */     if ((entity instanceof EntityPlayer)) {
/* 101 */       EntityPlayer player = (EntityPlayer)entity;
/* 102 */       this.proxyEntity.setItemInUse(player.func_71011_bu() == null ? null : player.func_71011_bu().func_77946_l(), player.func_71052_bv());
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface)
/*     */   {
/* 108 */     syncModelWith(entity, frontface);
/* 109 */     this.proxyRenderer.func_76976_a(RenderManager.field_78727_a);
/* 110 */     float f1 = this.proxyEntity.field_70126_B + (this.proxyEntity.field_70177_z - this.proxyEntity.field_70126_B) * partialTicks;
/*     */     
/* 112 */     double d3 = 0.0D - this.proxyEntity.field_70129_M;
/* 113 */     if ((this.proxyEntity.func_70093_af()) && (!(entity instanceof EntityPlayerSP))) {
/* 114 */       d3 -= 0.125D;
/*     */     }
/*     */     
/* 117 */     if (this.proxyEntity.func_70115_ae()) {
/* 118 */       Entity ridden = this.proxyEntity.field_70154_o;
/* 119 */       d3 += ridden.func_70042_X();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 124 */     this.proxyRenderer.func_76986_a(this.proxyEntity, x, y + d3, z, frontface ? 0.0F : f1, partialTicks);
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
/* 154 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */     
/* 156 */     ItemStack itemstack = p_77029_1_.func_70694_bm();
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
/* 208 */     if ((itemstack != null) && (itemstack.func_77973_b() != null))
/*     */     {
/* 210 */       Item item = itemstack.func_77973_b();
/* 211 */       GL11.glPushMatrix();
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
/* 222 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 224 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 225 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 227 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
/*     */       {
/* 229 */         float f1 = 0.5F;
/* 230 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 231 */         f1 *= 0.75F;
/* 232 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 233 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 234 */         GL11.glScalef(-f1, -f1, f1);
/*     */       }
/* 236 */       else if (item == Items.field_151031_f)
/*     */       {
/* 238 */         float f1 = 0.625F;
/* 239 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 240 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 241 */         GL11.glScalef(f1, -f1, f1);
/* 242 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 243 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 245 */       else if (item.func_77662_d())
/*     */       {
/* 247 */         float f1 = 0.625F;
/*     */         
/* 249 */         if (item.func_77629_n_())
/*     */         {
/* 251 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 252 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/*     */ 
/* 256 */         GL11.glScalef(f1, -f1, f1);
/* 257 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 258 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else
/*     */       {
/* 262 */         float f1 = 0.375F;
/* 263 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 264 */         GL11.glScalef(f1, f1, f1);
/* 265 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 266 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 267 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 274 */       if (itemstack.func_77973_b().func_77623_v())
/*     */       {
/* 276 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++)
/*     */         {
/* 278 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 279 */           float f5 = (j >> 16 & 0xFF) / 255.0F;
/* 280 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 281 */           float f3 = (j & 0xFF) / 255.0F;
/* 282 */           GL11.glColor4f(f5, f2, f3, 1.0F);
/* 283 */           itemRenderer.func_78443_a(p_77029_1_, itemstack, i);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 288 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 289 */       float f4 = (i >> 16 & 0xFF) / 255.0F;
/* 290 */       float f5 = (i >> 8 & 0xFF) / 255.0F;
/* 291 */       float f2 = (i & 0xFF) / 255.0F;
/* 292 */       GL11.glColor4f(f4, f5, f2, 1.0F);
/* 293 */       itemRenderer.func_78443_a(p_77029_1_, itemstack, 0);
/*     */       
/*     */ 
/* 296 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/TransformWolfman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */