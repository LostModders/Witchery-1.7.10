/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.client.renderer.RenderOtherPlayer;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TransformOtherPlayer
/*     */ {
/*  28 */   private RenderOtherPlayer proxyRenderer = new RenderOtherPlayer();
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
/*     */   public void syncModelWith(EntityLivingBase entity, boolean frontface) {}
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
/*     */   public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface)
/*     */   {
/* 109 */     EntityPlayer player = (EntityPlayer)entity;
/* 110 */     this.proxyRenderer.func_76976_a(RenderManager.field_78727_a);
/* 111 */     float f1 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * partialTicks;
/*     */     
/*     */ 
/* 114 */     double d3 = player.field_70129_M;
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
/* 126 */     this.proxyRenderer.func_76986_a(player, x, y + d3, z, frontface ? 0.0F : f1, partialTicks);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void renderEquippedItems(ItemRenderer itemRenderer, EntityLivingBase p_77029_1_, float p_77029_2_)
/*     */   {
/* 161 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */     
/* 163 */     ItemStack itemstack = p_77029_1_.func_70694_bm();
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
/* 226 */     if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
/* 227 */       Item item = itemstack.func_77973_b();
/* 228 */       GL11.glPushMatrix();
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
/* 239 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 241 */       IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
/*     */       
/* 243 */       boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
/*     */       
/*     */ 
/*     */ 
/* 247 */       if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
/*     */       {
/* 249 */         float f1 = 0.5F;
/* 250 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 251 */         f1 *= 0.75F;
/* 252 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 253 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 254 */         GL11.glScalef(-f1, -f1, f1);
/* 255 */       } else if (item == Items.field_151031_f) {
/* 256 */         float f1 = 0.625F;
/* 257 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 258 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 259 */         GL11.glScalef(f1, -f1, f1);
/* 260 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 261 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 262 */       } else if (item.func_77662_d()) {
/* 263 */         float f1 = 0.625F;
/*     */         
/* 265 */         if (item.func_77629_n_()) {
/* 266 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 267 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/*     */ 
/* 271 */         GL11.glScalef(f1, -f1, f1);
/* 272 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 273 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 275 */         float f1 = 0.375F;
/* 276 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 277 */         GL11.glScalef(f1, f1, f1);
/* 278 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 279 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 280 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 287 */       if (itemstack.func_77973_b().func_77623_v()) {
/* 288 */         for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++) {
/* 289 */           int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
/* 290 */           float f5 = (j >> 16 & 0xFF) / 255.0F;
/* 291 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 292 */           float f3 = (j & 0xFF) / 255.0F;
/* 293 */           GL11.glColor4f(f5, f2, f3, 1.0F);
/* 294 */           itemRenderer.func_78443_a(p_77029_1_, itemstack, i);
/*     */         }
/*     */       }
/* 297 */       int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
/* 298 */       float f4 = (i >> 16 & 0xFF) / 255.0F;
/* 299 */       float f5 = (i >> 8 & 0xFF) / 255.0F;
/* 300 */       float f2 = (i & 0xFF) / 255.0F;
/* 301 */       GL11.glColor4f(f4, f5, f2, 1.0F);
/* 302 */       itemRenderer.func_78443_a(p_77029_1_, itemstack, 0);
/*     */       
/*     */ 
/* 305 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/TransformOtherPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */