/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelMysticBranch;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderMysticBranch implements IItemRenderer
/*     */ {
/*     */   protected ModelMysticBranch model;
/*  26 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/mysticbranch.png");
/*     */   
/*     */   public RenderMysticBranch() {
/*  29 */     this.model = new ModelMysticBranch();
/*     */   }
/*     */   
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  34 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  37 */       return true;
/*     */     }
/*  39 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*  48 */   double rx = 100.0D; double ry = -51.0D; double rz = -81.0D;
/*  49 */   double tx = 0.125D; double ty = 0.12D; double tz = -0.85D;
/*  50 */   double scale = 1.0D;
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  54 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  57 */       GL11.glPushMatrix();
/*     */       
/*  59 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
/*     */       
/*  61 */       GL11.glRotatef((float)this.rx, 1.0F, 0.0F, 0.0F);
/*  62 */       GL11.glRotatef((float)this.ry, 0.0F, 1.0F, 0.0F);
/*  63 */       GL11.glRotatef((float)this.rz, 0.0F, 0.0F, 1.0F);
/*     */       
/*  65 */       GL11.glTranslatef((float)this.tx - 0.08F, (float)this.ty + 0.25F, (float)this.tz + 0.1F);
/*     */       
/*  67 */       float SCALE = (float)this.scale;
/*  68 */       GL11.glScalef(SCALE, SCALE, SCALE);
/*     */       
/*  70 */       if ((data.length > 1) && (data[1] != null)) {
/*  71 */         if ((data[1] instanceof EntityPlayer)) {
/*  72 */           EntityPlayer player = (EntityPlayer)data[1];
/*  73 */           if (((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h) || (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) || ((((Minecraft.func_71410_x().field_71462_r instanceof net.minecraft.client.gui.inventory.GuiInventory)) || ((Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative))) && (RenderManager.field_78727_a.field_78735_i == 180.0F))) {
/*  74 */             if (!player.func_82150_aj()) {
/*  75 */               renderModel(player);
/*     */             }
/*     */             
/*     */           }
/*     */           else
/*     */           {
/*  81 */             if (player.func_82150_aj()) {
/*  82 */               RenderUtil.blend(true);
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  89 */             renderModel(player);
/*     */             
/*  91 */             if (player.func_82150_aj()) {
/*  92 */               RenderUtil.blend(false);
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/*  97 */           renderModel((Entity)data[1]);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 102 */       GL11.glPopMatrix();
/* 103 */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   private void renderModel(Entity player)
/*     */   {
/* 111 */     this.model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/* 112 */     Minecraft mc = Minecraft.func_71410_x();
/* 113 */     if ((mc.field_71474_y.field_74347_j) && (Config.instance().render3dGlintEffect)) {
/* 114 */       float f9 = player.field_70173_aa;
/* 115 */       mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
/* 116 */       GL11.glEnable(3042);
/* 117 */       float f10 = 0.5F;
/* 118 */       GL11.glColor4f(f10, f10, f10, 1.0F);
/* 119 */       GL11.glDepthFunc(514);
/* 120 */       GL11.glDepthMask(false);
/*     */       
/* 122 */       for (int k = 0; k < 2; k++)
/*     */       {
/* 124 */         GL11.glDisable(2896);
/* 125 */         float f11 = 0.76F;
/*     */         
/* 127 */         GL11.glColor4f(0.0F * f11, 0.5F * f11, 0.0F * f11, 1.0F);
/* 128 */         GL11.glBlendFunc(768, 1);
/* 129 */         GL11.glMatrixMode(5890);
/* 130 */         GL11.glLoadIdentity();
/* 131 */         float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
/* 132 */         float f13 = 0.33333334F;
/* 133 */         GL11.glScalef(f13, f13, f13);
/* 134 */         GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
/* 135 */         GL11.glTranslatef(0.0F, f12, 0.0F);
/* 136 */         GL11.glMatrixMode(5888);
/* 137 */         this.model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*     */       }
/*     */       
/* 140 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 141 */       GL11.glMatrixMode(5890);
/* 142 */       GL11.glDepthMask(true);
/* 143 */       GL11.glLoadIdentity();
/* 144 */       GL11.glMatrixMode(5888);
/* 145 */       GL11.glEnable(2896);
/* 146 */       GL11.glDisable(3042);
/* 147 */       GL11.glDepthFunc(515);
/*     */     }
/*     */   }
/*     */   
/* 151 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderMysticBranch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */