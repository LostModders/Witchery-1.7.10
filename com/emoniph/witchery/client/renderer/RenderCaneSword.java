/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.model.ModelCaneSword;
/*     */ import com.emoniph.witchery.item.ItemCaneSword;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*     */ import net.minecraft.client.gui.inventory.GuiInventory;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
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
/*     */ public class RenderCaneSword implements IItemRenderer
/*     */ {
/*     */   private final ModelCaneSword model;
/*  28 */   private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/canesword.png");
/*     */   
/*     */   public RenderCaneSword()
/*     */   {
/*  32 */     this.model = new ModelCaneSword();
/*     */   }
/*     */   
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  37 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  40 */       return true;
/*     */     }
/*  42 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  48 */     return false;
/*     */   }
/*     */   
/*  51 */   double rx = 100.0D; double ry = -51.0D; double rz = -81.0D;
/*  52 */   double tx = 0.125D; double ty = 0.12D; double tz = -0.85D;
/*  53 */   double scale = 1.0D;
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  57 */     switch (type) {
/*     */     case EQUIPPED: 
/*     */     case EQUIPPED_FIRST_PERSON: 
/*  60 */       GL11.glPushMatrix();
/*     */       
/*  62 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
/*     */       
/*  64 */       GL11.glRotatef((float)this.rx, 1.0F, 0.0F, 0.0F);
/*  65 */       GL11.glRotatef((float)this.ry + 70.0F, 0.0F, 1.0F, 0.0F);
/*  66 */       GL11.glRotatef((float)this.rz - 5.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/*  68 */       GL11.glTranslatef((float)this.tx + 0.35F, (float)this.ty + 0.0F, (float)this.tz + 0.85F);
/*     */       
/*  70 */       float SCALE = 1.0F;
/*  71 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/*     */       
/*  73 */       if ((data.length > 1) && (data[1] != null)) {
/*  74 */         boolean deployed = (data[1] instanceof EntityLivingBase) ? com.emoniph.witchery.Witchery.Items.CANE_SWORD.isDrawn((EntityLivingBase)data[1]) : false;
/*     */         
/*  76 */         if ((data[1] instanceof EntityPlayer)) {
/*  77 */           EntityPlayer player = (EntityPlayer)data[1];
/*  78 */           if (((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h) || (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) || ((((Minecraft.func_71410_x().field_71462_r instanceof GuiInventory)) || ((Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative))) && (RenderManager.field_78727_a.field_78735_i == 180.0F)))
/*     */           {
/*     */ 
/*  81 */             if (deployed) {
/*  82 */               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
/*  83 */               GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/*  84 */               GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*  85 */               GL11.glTranslatef(0.0F, -0.3F, -0.5F);
/*     */             }
/*     */             
/*  88 */             renderModel(player, false, deployed, item);
/*     */           } else {
/*  90 */             if (deployed) {
/*  91 */               GL11.glTranslatef(0.4F, 0.3F, -0.2F);
/*  92 */               GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  93 */               GL11.glRotatef(1.0F, 0.0F, 0.0F, 1.0F);
/*  94 */               GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
/*     */             }
/*     */             else
/*     */             {
/*  98 */               GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
/*  99 */               GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/* 100 */               GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/* 101 */               GL11.glTranslatef(0.0F, -0.5F, -0.9F);
/*     */             }
/*     */             
/* 104 */             renderModel(player, true, deployed, item);
/*     */           }
/*     */         } else {
/* 107 */           if (deployed) {
/* 108 */             GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
/* 109 */             GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 110 */             GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/* 111 */             GL11.glTranslatef(0.0F, -0.3F, -0.5F);
/*     */           }
/* 113 */           renderModel((Entity)data[1], false, deployed, item);
/*     */         }
/*     */       }
/*     */       
/* 117 */       GL11.glPopMatrix();
/* 118 */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   private void renderModel(Entity player, boolean firstPerson, boolean deployed, ItemStack item)
/*     */   {
/* 126 */     this.model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, firstPerson, deployed);
/*     */     
/* 128 */     Minecraft mc = Minecraft.func_71410_x();
/* 129 */     if ((mc.field_71474_y.field_74347_j) && (Config.instance().render3dGlintEffect) && 
/* 130 */       (item != null) && (item.func_77948_v())) {
/* 131 */       float f9 = player.field_70173_aa;
/* 132 */       mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
/* 133 */       GL11.glEnable(3042);
/* 134 */       float f10 = 0.5F;
/* 135 */       GL11.glColor4f(f10, f10, f10, 1.0F);
/* 136 */       GL11.glDepthFunc(514);
/* 137 */       GL11.glDepthMask(false);
/*     */       
/* 139 */       for (int k = 0; k < 2; k++) {
/* 140 */         GL11.glDisable(2896);
/* 141 */         float f11 = 0.76F;
/*     */         
/* 143 */         GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
/* 144 */         GL11.glBlendFunc(768, 1);
/* 145 */         GL11.glMatrixMode(5890);
/* 146 */         GL11.glLoadIdentity();
/* 147 */         float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
/* 148 */         float f13 = 0.33333334F;
/* 149 */         GL11.glScalef(f13, f13, f13);
/* 150 */         GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
/* 151 */         GL11.glTranslatef(0.0F, f12, 0.0F);
/* 152 */         GL11.glMatrixMode(5888);
/* 153 */         this.model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, firstPerson, deployed);
/*     */       }
/*     */       
/* 156 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 157 */       GL11.glMatrixMode(5890);
/* 158 */       GL11.glDepthMask(true);
/* 159 */       GL11.glLoadIdentity();
/* 160 */       GL11.glMatrixMode(5888);
/* 161 */       GL11.glEnable(2896);
/* 162 */       GL11.glDisable(3042);
/* 163 */       GL11.glDepthFunc(515);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 168 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderCaneSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */