/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.model.ModelHandBow;
/*     */ import com.emoniph.witchery.item.ItemGeneral.BoltType;
/*     */ import com.emoniph.witchery.item.ItemHandBow;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiInventory;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class RenderHandBow implements IItemRenderer
/*     */ {
/*     */   protected ModelHandBow model;
/*  26 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/handbow.png");
/*     */   
/*     */   public RenderHandBow() {
/*  29 */     this.model = new ModelHandBow();
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
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data)
/*     */   {
/*  54 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  57 */       GL11.glPushMatrix();
/*     */       
/*  59 */       ItemGeneral.BoltType boltType = ItemHandBow.getLoadedBoltType(stack);
/*  60 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
/*     */       
/*  62 */       GL11.glRotatef((float)this.rx, 1.0F, 0.0F, 0.0F);
/*  63 */       GL11.glRotatef((float)this.ry, 0.0F, 1.0F, 0.0F);
/*  64 */       GL11.glRotatef((float)this.rz, 0.0F, 0.0F, 1.0F);
/*     */       
/*  66 */       GL11.glTranslatef((float)this.tx - 0.03F, (float)this.ty - 0.13F, (float)this.tz + 0.13F);
/*     */       
/*  68 */       float SCALE = (float)this.scale;
/*  69 */       GL11.glScalef(SCALE, SCALE, SCALE);
/*     */       
/*  71 */       if ((data.length > 1) && (data[1] != null)) {
/*  72 */         if ((data[1] instanceof EntityPlayer)) {
/*  73 */           EntityPlayer player = (EntityPlayer)data[1];
/*  74 */           int useCount = player.func_71052_bv() > 0 ? com.emoniph.witchery.Witchery.Items.CROSSBOW_PISTOL.func_77626_a(stack) - player.func_71052_bv() : 0;
/*     */           
/*  76 */           if (((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h) || (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) || ((((Minecraft.func_71410_x().field_71462_r instanceof GuiInventory)) || ((Minecraft.func_71410_x().field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainerCreative))) && (RenderManager.field_78727_a.field_78735_i == 180.0F)))
/*     */           {
/*     */ 
/*  79 */             renderModel(player, boltType, useCount);
/*     */           } else {
/*  81 */             if (player.func_71052_bv() > 0) {
/*  82 */               GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
/*  83 */               GL11.glTranslatef(0.0F, 0.1F, 0.0F);
/*     */             }
/*     */             
/*  86 */             GL11.glTranslatef(0.2F, 0.1F, 0.0F);
/*  87 */             renderModel(player, boltType, useCount);
/*     */           }
/*     */         } else {
/*  90 */           renderModel((Entity)data[1], boltType, -1);
/*     */         }
/*     */       }
/*     */       
/*  94 */       GL11.glPopMatrix();
/*  95 */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   private void renderModel(Entity player, ItemGeneral.BoltType boltType, int useCount)
/*     */   {
/* 103 */     if (boltType != null) {
/* 104 */       useCount = 100;
/* 105 */     } else if ((!player.func_70093_af()) || (useCount == -1)) {
/* 106 */       useCount = 0;
/*     */     }
/* 108 */     this.model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, boltType, useCount);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 151 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderHandBow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */