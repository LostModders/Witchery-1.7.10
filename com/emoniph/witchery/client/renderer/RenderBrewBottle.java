/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelBrewBottle;
/*     */ import com.emoniph.witchery.util.RenderUtil;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderBrewBottle
/*     */   implements IItemRenderer
/*     */ {
/*     */   protected ModelBrewBottle model;
/*  42 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/brewbottle.png");
/*     */   
/*     */   public RenderBrewBottle() {
/*  45 */     this.model = new ModelBrewBottle();
/*     */   }
/*     */   
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  50 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  53 */       return true;
/*     */     }
/*  55 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  61 */     return false;
/*     */   }
/*     */   
/*  64 */   double rx = 100.0D; double ry = -51.0D; double rz = -81.0D;
/*  65 */   double tx = 0.125D; double ty = 0.12D; double tz = -0.85D;
/*  66 */   double scale = 1.0D;
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  70 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  73 */       GL11.glPushMatrix();
/*     */       
/*  75 */       if ((data.length > 1) && (data[1] != null))
/*     */       {
/*  77 */         if ((data[1] instanceof EntityPlayer)) {
/*  78 */           EntityPlayer player = (EntityPlayer)data[1];
/*     */           
/*  80 */           GL11.glRotatef((float)this.rx, 1.0F, 0.0F, 0.0F);
/*  81 */           GL11.glRotatef((float)this.ry + 10.0F, 0.0F, 1.0F, 0.0F);
/*  82 */           GL11.glRotatef((float)this.rz, 0.0F, 0.0F, 1.0F);
/*     */           
/*  84 */           GL11.glTranslatef((float)this.tx - 0.05F, (float)this.ty + 0.2F, (float)this.tz + 0.1F);
/*     */           
/*  86 */           Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  92 */           RenderUtil.blend(true);
/*     */           
/*  94 */           if (((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h) || (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) || ((((Minecraft.func_71410_x().field_71462_r instanceof GuiInventory)) || ((Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative))) && (RenderManager.field_78727_a.field_78735_i == 180.0F)))
/*     */           {
/*  96 */             float SCALE = 1.3F;
/*  97 */             GL11.glScalef(1.3F, 1.3F, 1.3F);
/*  98 */             this.model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*     */           }
/*     */           else {
/* 101 */             GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/* 102 */             GL11.glTranslatef(0.3F, 0.05F, 0.0F);
/*     */             
/* 104 */             float SCALE = 1.5F;
/* 105 */             GL11.glScalef(1.5F, 1.5F, 1.5F);
/* 106 */             this.model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*     */           }
/*     */           
/* 109 */           RenderUtil.blend(false);
/*     */         }
/*     */         else {
/* 112 */           Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
/*     */           
/* 114 */           GL11.glRotatef((float)this.rx, 1.0F, 0.0F, 0.0F);
/* 115 */           GL11.glRotatef((float)this.ry, 0.0F, 1.0F, 0.0F);
/* 116 */           GL11.glRotatef((float)this.rz, 0.0F, 0.0F, 1.0F);
/*     */           
/* 118 */           GL11.glTranslatef((float)this.tx, (float)this.ty, (float)this.tz);
/*     */           
/* 120 */           float SCALE = (float)this.scale;
/* 121 */           GL11.glScalef(SCALE, SCALE, SCALE);
/*     */           
/* 123 */           this.model.func_78088_a((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*     */         }
/*     */       }
/*     */       
/* 127 */       GL11.glPopMatrix();
/* 128 */       break;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBrewBottle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */