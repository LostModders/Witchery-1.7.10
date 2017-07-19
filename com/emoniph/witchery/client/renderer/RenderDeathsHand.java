/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.model.ModelWitchHand;
/*     */ import com.emoniph.witchery.item.ItemDeathsHand;
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*     */ import net.minecraft.client.gui.inventory.GuiInventory;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class RenderDeathsHand implements IItemRenderer
/*     */ {
/*     */   protected ModelWitchHand witchHandModel;
/*  27 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/deathshand.png");
/*     */   
/*     */   public RenderDeathsHand() {
/*  30 */     this.witchHandModel = new ModelWitchHand(-0.3F);
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
/*  50 */   double tx = 0.125D; double ty = 0.12D; double tz = -0.85D;
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
/*  68 */       float SCALE = 1.0F;
/*  69 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  74 */       RenderUtil.blend(true);
/*     */       
/*  76 */       if ((data.length > 1) && (data[1] != null)) {
/*  77 */         boolean deployed = (data[1] instanceof EntityLivingBase) ? com.emoniph.witchery.Witchery.Items.DEATH_HAND.isDeployed((EntityLivingBase)data[1]) : false;
/*  78 */         if ((data[1] instanceof EntityPlayer)) {
/*  79 */           EntityPlayer player = (EntityPlayer)data[1];
/*  80 */           if (((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h) || (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) || ((((Minecraft.func_71410_x().field_71462_r instanceof GuiInventory)) || ((Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative))) && (RenderManager.field_78727_a.field_78735_i == 180.0F))) {
/*  81 */             if (deployed) {
/*  82 */               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  83 */               GL11.glTranslatef(0.19F, -0.12F, 0.01F);
/*     */             }
/*  85 */             if (!player.func_82150_aj()) {
/*  86 */               this.witchHandModel.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, false, deployed);
/*     */             }
/*     */           } else {
/*  89 */             if (deployed) {
/*  90 */               GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */             }
/*  92 */             if (player.func_82150_aj()) {
/*  93 */               GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
/*     */             }
/*     */             
/*  96 */             this.witchHandModel.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, true, deployed);
/*     */           }
/*     */         } else {
/*  99 */           if (deployed) {
/* 100 */             GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 101 */             GL11.glTranslatef(0.2F, -0.12F, 0.01F);
/*     */           }
/* 103 */           this.witchHandModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, false, deployed);
/*     */         }
/*     */       }
/*     */       
/* 107 */       RenderUtil.blend(false);
/*     */       
/* 109 */       GL11.glPopMatrix();
/* 110 */       break;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderDeathsHand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */