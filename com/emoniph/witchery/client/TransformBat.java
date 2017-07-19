/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityBroom;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderBat;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TransformBat
/*     */ {
/*     */   private EntityBat proxyEntity;
/*  31 */   private RenderBat proxyRenderer = new RenderBat();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityLivingBase getModel()
/*     */   {
/*  38 */     return this.proxyEntity;
/*     */   }
/*     */   
/*     */   public void syncModelWith(EntityLivingBase entity, boolean frontface) {
/*  42 */     if (this.proxyEntity == null) {
/*  43 */       this.proxyEntity = new EntityBat(entity.field_70170_p);
/*  44 */     } else if (this.proxyEntity.field_70170_p != entity.field_70170_p) {
/*  45 */       this.proxyEntity.func_70029_a(entity.field_70170_p);
/*     */     }
/*     */     
/*  48 */     this.proxyEntity.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*     */     
/*  50 */     this.proxyEntity.field_70142_S = entity.field_70142_S;
/*  51 */     this.proxyEntity.field_70137_T = entity.field_70137_T;
/*  52 */     this.proxyEntity.field_70136_U = entity.field_70136_U;
/*     */     
/*  54 */     this.proxyEntity.field_70159_w = entity.field_70159_w;
/*  55 */     this.proxyEntity.field_70181_x = entity.field_70181_x;
/*  56 */     this.proxyEntity.field_70179_y = entity.field_70179_y;
/*     */     
/*  58 */     this.proxyEntity.field_70701_bs = entity.field_70701_bs;
/*  59 */     this.proxyEntity.field_70702_br = entity.field_70702_br;
/*     */     
/*  61 */     this.proxyEntity.field_70122_E = entity.field_70122_E;
/*     */     
/*  63 */     this.proxyEntity.field_70169_q = entity.field_70169_q;
/*  64 */     this.proxyEntity.field_70167_r = entity.field_70167_r;
/*  65 */     this.proxyEntity.field_70166_s = entity.field_70166_s;
/*     */     
/*  67 */     this.proxyEntity.field_70125_A = entity.field_70125_A;
/*  68 */     this.proxyEntity.field_70177_z = entity.field_70177_z;
/*  69 */     this.proxyEntity.field_70759_as = entity.field_70759_as;
/*     */     
/*  71 */     this.proxyEntity.field_70127_C = entity.field_70127_C;
/*  72 */     this.proxyEntity.field_70126_B = entity.field_70126_B;
/*  73 */     this.proxyEntity.field_70758_at = entity.field_70758_at;
/*     */     
/*  75 */     this.proxyEntity.field_70754_ba = entity.field_70754_ba;
/*     */     
/*  77 */     this.proxyEntity.field_70721_aZ = entity.field_70721_aZ;
/*  78 */     this.proxyEntity.field_70722_aY = entity.field_70722_aY;
/*  79 */     this.proxyEntity.field_82175_bq = entity.field_82175_bq;
/*     */     
/*  81 */     this.proxyEntity.field_70733_aJ = entity.field_70733_aJ;
/*  82 */     this.proxyEntity.field_70732_aI = entity.field_70732_aI;
/*     */     
/*  84 */     this.proxyEntity.field_70761_aq = (frontface ? 0.0F : entity.field_70761_aq);
/*  85 */     this.proxyEntity.field_70760_ar = (frontface ? 0.0F : entity.field_70760_ar);
/*     */     
/*     */ 
/*  88 */     this.proxyEntity.field_70173_aa = entity.field_70173_aa;
/*  89 */     this.proxyEntity.field_70128_L = false;
/*  90 */     this.proxyEntity.field_70160_al = entity.field_70160_al;
/*  91 */     this.proxyEntity.func_82236_f(false);
/*     */     
/*  93 */     this.proxyEntity.field_70129_M = 0.0F;
/*     */     
/*  95 */     this.proxyEntity.func_70095_a(entity.func_70093_af());
/*  96 */     this.proxyEntity.func_70031_b(entity.func_70051_ag());
/*  97 */     this.proxyEntity.func_82142_c(entity.func_82150_aj());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface)
/*     */   {
/* 104 */     syncModelWith(entity, frontface);
/* 105 */     this.proxyRenderer.func_76976_a(RenderManager.field_78727_a);
/* 106 */     float f1 = this.proxyEntity.field_70126_B + (this.proxyEntity.field_70177_z - this.proxyEntity.field_70126_B) * partialTicks;
/*     */     
/* 108 */     double d3 = -this.proxyEntity.field_70129_M;
/* 109 */     if ((this.proxyEntity.func_70093_af()) && (!(entity instanceof EntityPlayerSP))) {
/* 110 */       d3 -= 0.125D;
/*     */     }
/*     */     
/* 113 */     if (entity.func_70115_ae()) {
/* 114 */       Entity ridden = entity.field_70154_o;
/* 115 */       d3 += ridden.func_70042_X() + ((entity.field_70154_o instanceof EntityBroom) ? ridden.field_70131_O - 0.2D : 0.0D);
/*     */     }
/*     */     
/* 118 */     float f2 = 1.0F;
/* 119 */     GL11.glColor3f(f2, f2, f2);
/* 120 */     this.proxyRenderer.func_76986_a(this.proxyEntity, x, y + d3, z, frontface ? 0.0F : f1, partialTicks);
/*     */     
/*     */ 
/*     */ 
/* 124 */     Vec3 vec = this.proxyEntity.func_70040_Z();
/* 125 */     vec.func_72442_b(90.0F);
/* 126 */     GL11.glPushMatrix();
/* 127 */     GL11.glScalef(0.8F, 0.8F, 0.8F);
/* 128 */     this.proxyEntity.field_70173_aa += 2;
/* 129 */     this.proxyRenderer.func_76986_a(this.proxyEntity, x + vec.field_72450_a * 0.75D, y + d3 - 0.6D, z + vec.field_72449_c * 0.75D, frontface ? 0.0F : f1, partialTicks);
/*     */     
/* 131 */     this.proxyEntity.field_70173_aa += 5;
/* 132 */     vec.func_72442_b(-180.0F);
/* 133 */     this.proxyRenderer.func_76986_a(this.proxyEntity, x + vec.field_72450_a * 0.75D, y + d3 - 0.6D, z + vec.field_72449_c * 0.75D, frontface ? 0.0F : f1, partialTicks);
/* 134 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void renderEquippedItems(ItemRenderer itemRenderer, EntityLivingBase p_77029_1_, float p_77029_2_)
/*     */   {
/* 141 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */     
/* 143 */     ItemStack itemstack = p_77029_1_.func_70694_bm();
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
/* 195 */     if ((itemstack != null) && (itemstack.func_77973_b() != null))
/*     */     {
/* 197 */       Item item = itemstack.func_77973_b();
/* 198 */       GL11.glPushMatrix();
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
/* 209 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 211 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/* 212 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/* 214 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
/*     */       {
/* 216 */         float f1 = 0.5F;
/* 217 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 218 */         f1 *= 0.75F;
/* 219 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 220 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 221 */         GL11.glScalef(-f1, -f1, f1);
/*     */       }
/* 223 */       else if (item == Items.field_151031_f)
/*     */       {
/* 225 */         float f1 = 0.625F;
/* 226 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 227 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 228 */         GL11.glScalef(f1, -f1, f1);
/* 229 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 230 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 232 */       else if (item.func_77662_d())
/*     */       {
/* 234 */         float f1 = 0.625F;
/*     */         
/* 236 */         if (item.func_77629_n_())
/*     */         {
/* 238 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 239 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/*     */ 
/* 243 */         GL11.glScalef(f1, -f1, f1);
/* 244 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 245 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else
/*     */       {
/* 249 */         float f1 = 0.375F;
/* 250 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 251 */         GL11.glScalef(f1, f1, f1);
/* 252 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 253 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 254 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 261 */       if (itemstack.func_77973_b().func_77623_v())
/*     */       {
/* 263 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++)
/*     */         {
/* 265 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 266 */           float f5 = (j >> 16 & 0xFF) / 255.0F;
/* 267 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 268 */           float f3 = (j & 0xFF) / 255.0F;
/* 269 */           GL11.glColor4f(f5, f2, f3, 1.0F);
/* 270 */           itemRenderer.func_78443_a(p_77029_1_, itemstack, i);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 275 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 276 */       float f4 = (i >> 16 & 0xFF) / 255.0F;
/* 277 */       float f5 = (i >> 8 & 0xFF) / 255.0F;
/* 278 */       float f2 = (i & 0xFF) / 255.0F;
/* 279 */       GL11.glColor4f(f4, f5, f2, 1.0F);
/* 280 */       itemRenderer.func_78443_a(p_77029_1_, itemstack, 0);
/*     */       
/*     */ 
/* 283 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/TransformBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */