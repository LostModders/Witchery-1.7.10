/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelHuntsmanSpear;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*     */ import net.minecraft.client.gui.inventory.GuiInventory;
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
/*     */ public class RenderHuntsmanSpear
/*     */   implements IItemRenderer
/*     */ {
/*     */   protected ModelHuntsmanSpear model;
/*  27 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/huntsmanspear.png");
/*     */   
/*     */   public RenderHuntsmanSpear() {
/*  30 */     this.model = new ModelHuntsmanSpear();
/*     */   }
/*     */   
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  35 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  38 */       return true;
/*     */     }
/*  40 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  46 */     return false;
/*     */   }
/*     */   
/*  49 */   double rx = 100.0D; double ry = -51.0D; double rz = -81.0D;
/*  50 */   double tx = 0.1D; double ty = 0.12D; double tz = -0.72D;
/*  51 */   double scale = 1.0D;
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  55 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  58 */       GL11.glPushMatrix();
/*     */       
/*  60 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
/*     */       
/*  62 */       GL11.glRotatef((float)this.rx, 1.0F, 0.0F, 0.0F);
/*  63 */       GL11.glRotatef((float)this.ry, 0.0F, 1.0F, 0.0F);
/*  64 */       GL11.glRotatef((float)this.rz, 0.0F, 0.0F, 1.0F);
/*     */       
/*  66 */       GL11.glTranslatef((float)this.tx, (float)this.ty, (float)this.tz);
/*     */       
/*  68 */       if ((data.length > 1) && (data[1] != null)) {
/*  69 */         if ((data[1] instanceof EntityPlayer)) {
/*  70 */           EntityPlayer player = (EntityPlayer)data[1];
/*  71 */           if (((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h) || (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) || ((((Minecraft.func_71410_x().field_71462_r instanceof GuiInventory)) || ((Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative))) && (RenderManager.field_78727_a.field_78735_i == 180.0F))) {
/*  72 */             if (player.field_70733_aJ > 0.0F) {
/*  73 */               if (player.field_70733_aJ > 0.3D) {
/*  74 */                 GL11.glRotatef(50.0F, 1.0F, 0.0F, 0.0F);
/*     */               }
/*  76 */               GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/*     */             }
/*  78 */             renderModel(player);
/*     */           } else {
/*  80 */             if (player.field_70733_aJ > 0.0F) {
/*  81 */               GL11.glTranslatef(0.0F, 0.15F, 0.0F);
/*     */             }
/*  83 */             GL11.glRotatef(50.0F * ((player.field_70733_aJ > 0.5D) || (player.field_70733_aJ == 0.0F) ? player.field_70733_aJ : 1.0F - player.field_70733_aJ), 1.0F, 0.0F, 0.0F);
/*  84 */             renderModel(player);
/*     */           }
/*     */         } else {
/*  87 */           renderModel((Entity)data[1]);
/*     */         }
/*     */       }
/*     */       
/*  91 */       GL11.glPopMatrix();
/*  92 */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void renderModel(Entity player)
/*     */   {
/* 101 */     this.model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*     */     
/* 103 */     Minecraft mc = Minecraft.func_71410_x();
/* 104 */     if ((mc.field_71474_y.field_74347_j) && (Config.instance().render3dGlintEffect)) {
/* 105 */       float f9 = player.field_70173_aa;
/* 106 */       mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
/* 107 */       GL11.glEnable(3042);
/* 108 */       float f10 = 0.5F;
/* 109 */       GL11.glColor4f(f10, f10, f10, 1.0F);
/* 110 */       GL11.glDepthFunc(514);
/* 111 */       GL11.glDepthMask(false);
/*     */       
/* 113 */       for (int k = 0; k < 2; k++)
/*     */       {
/* 115 */         GL11.glDisable(2896);
/* 116 */         float f11 = 0.76F;
/* 117 */         GL11.glColor4f(0.0F * f11, 0.5F * f11, 0.0F * f11, 1.0F);
/*     */         
/* 119 */         GL11.glBlendFunc(768, 1);
/* 120 */         GL11.glMatrixMode(5890);
/* 121 */         GL11.glLoadIdentity();
/* 122 */         float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
/* 123 */         float f13 = 0.33333334F;
/* 124 */         GL11.glScalef(f13, f13, f13);
/* 125 */         GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
/* 126 */         GL11.glTranslatef(0.0F, f12, 0.0F);
/* 127 */         GL11.glMatrixMode(5888);
/* 128 */         this.model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*     */       }
/*     */       
/* 131 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 132 */       GL11.glMatrixMode(5890);
/* 133 */       GL11.glDepthMask(true);
/* 134 */       GL11.glLoadIdentity();
/* 135 */       GL11.glMatrixMode(5888);
/* 136 */       GL11.glEnable(2896);
/* 137 */       GL11.glDisable(3042);
/* 138 */       GL11.glDepthFunc(515);
/*     */     }
/*     */   }
/*     */   
/* 142 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderHuntsmanSpear.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */