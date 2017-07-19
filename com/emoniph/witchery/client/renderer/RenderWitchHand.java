/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelWitchHand;
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*     */ import net.minecraft.client.gui.inventory.GuiInventory;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
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
/*     */ public class RenderWitchHand implements IItemRenderer
/*     */ {
/*     */   protected ModelWitchHand witchHandModel;
/*  25 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/WitchHand.png");
/*     */   
/*     */   public RenderWitchHand() {
/*  28 */     this.witchHandModel = new ModelWitchHand(0.0F);
/*     */   }
/*     */   
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  33 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  36 */       return true;
/*     */     }
/*  38 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  44 */     return false;
/*     */   }
/*     */   
/*  47 */   double rx = 100.0D; double ry = -51.0D; double rz = -81.0D;
/*  48 */   double tx = 0.125D; double ty = 0.12D; double tz = -0.85D;
/*  49 */   double scale = 1.0D;
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  53 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  56 */       GL11.glPushMatrix();
/*     */       
/*  58 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
/*     */       
/*  60 */       GL11.glRotatef((float)this.rx, 1.0F, 0.0F, 0.0F);
/*  61 */       GL11.glRotatef((float)this.ry, 0.0F, 1.0F, 0.0F);
/*  62 */       GL11.glRotatef((float)this.rz, 0.0F, 0.0F, 1.0F);
/*     */       
/*  64 */       GL11.glTranslatef((float)this.tx, (float)this.ty, (float)this.tz);
/*     */       
/*  66 */       float SCALE = (float)this.scale;
/*  67 */       GL11.glScalef(SCALE, SCALE, SCALE);
/*     */       
/*  69 */       if ((data.length > 1) && (data[1] != null)) {
/*  70 */         if ((data[1] instanceof EntityPlayer)) {
/*  71 */           EntityPlayer player = (EntityPlayer)data[1];
/*  72 */           if (((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h) || (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) || ((((Minecraft.func_71410_x().field_71462_r instanceof GuiInventory)) || ((Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative))) && (RenderManager.field_78727_a.field_78735_i == 180.0F)))
/*     */           {
/*     */ 
/*  75 */             if (!player.func_82150_aj()) {
/*  76 */               this.witchHandModel.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, false, false);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/*  81 */             if (player.func_82150_aj())
/*     */             {
/*     */ 
/*     */ 
/*  85 */               RenderUtil.blend(true);
/*  86 */               GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
/*     */             }
/*     */             
/*  89 */             this.witchHandModel.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, true, false);
/*  90 */             if (player.func_82150_aj()) {
/*  91 */               RenderUtil.blend(false);
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/*  96 */           this.witchHandModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, false, false);
/*     */         }
/*     */       }
/*     */       
/* 100 */       GL11.glPopMatrix();
/* 101 */       break;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderWitchHand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */