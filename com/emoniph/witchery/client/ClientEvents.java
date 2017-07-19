/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.client.model.ModelGrotesque;
/*     */ import com.emoniph.witchery.client.renderer.RenderOtherPlayer;
/*     */ import com.emoniph.witchery.client.renderer.RenderVillagerBed;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer.VampireUltimate;
/*     */ import com.emoniph.witchery.common.ExtendedVillager;
/*     */ import com.emoniph.witchery.common.Shapeshift;
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import com.emoniph.witchery.util.TransformCreature;
/*     */ import cpw.mods.fml.common.eventhandler.EventPriority;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiIngame;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.MouseEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent.Pre;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class ClientEvents
/*     */ {
/*     */   public static class GUIOverlay extends GuiIngame
/*     */   {
/*  69 */     public static final GUIOverlay INSTANCE = new GUIOverlay();
/*     */     
/*  71 */     private static final ResourceLocation WIDGITS = new ResourceLocation("textures/gui/widgets.png");
/*  72 */     private static final ResourceLocation TEETH = new ResourceLocation("witchery", "textures/items/vteeth.png");
/*     */     
/*  74 */     private static final ResourceLocation EYE = new ResourceLocation("witchery", "textures/items/veye.png");
/*     */     
/*  76 */     private static final ResourceLocation BAT = new ResourceLocation("witchery", "textures/items/vbat.png");
/*     */     
/*  78 */     private static final ResourceLocation RUN = new ResourceLocation("witchery", "textures/items/vspeed.png");
/*     */     
/*  80 */     private static final ResourceLocation FIST = new ResourceLocation("witchery", "textures/items/vfist.png");
/*     */     
/*  82 */     private static final ResourceLocation STORM = new ResourceLocation("witchery", "textures/items/vstorm.png");
/*     */     
/*  84 */     private static final ResourceLocation COFFIN = new ResourceLocation("witchery", "textures/items/vcoffin.png");
/*     */     private static final int WHITE = 16777215;
/*     */     
/*     */     public GUIOverlay() {
/*  88 */       super();
/*     */     }
/*     */     
/*     */     public void renderHotbar(RenderGameOverlayEvent.Pre event) {
/*  92 */       ExtendedPlayer playerEx = ExtendedPlayer.get(this.field_73839_d.field_71439_g);
/*  93 */       if (playerEx.isVampire()) {
/*  94 */         int width = event.resolution.func_78326_a();
/*  95 */         int height = event.resolution.func_78328_b();
/*  96 */         this.field_73839_d.field_71424_I.func_76320_a("actionBar");
/*     */         
/*  98 */         GL11.glEnable(3042);
/*  99 */         GL11.glBlendFunc(770, 771);
/* 100 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 101 */         this.field_73839_d.field_71446_o.func_110577_a(WIDGITS);
/*     */         
/* 103 */         InventoryPlayer inv = this.field_73839_d.field_71439_g.field_71071_by;
/* 104 */         func_73729_b(width / 2 - 91, height - 22, 0, 0, 182, 22);
/* 105 */         int vpower = playerEx.getSelectedVampirePower().ordinal();
/* 106 */         if (vpower != 0) {
/* 107 */           func_73729_b(width / 2 - 91 - 1 + -vpower * 20, height - 22 - 1, 0, 22, 24, 22);
/*     */         } else {
/* 109 */           func_73729_b(width / 2 - 91 - 1 + inv.field_70461_c * 20, height - 22 - 1, 0, 22, 24, 22);
/*     */         }
/*     */         
/*     */ 
/* 113 */         GL11.glDisable(3042);
/* 114 */         GL11.glEnable(32826);
/* 115 */         RenderHelper.func_74520_c();
/*     */         
/* 117 */         this.field_73735_i += 50.0F;
/* 118 */         int x = width / 2 - 90 + -20 + 2;
/* 119 */         int z = height - 16 - 3;
/*     */         
/* 121 */         if (playerEx.getVampireLevel() >= 1) {
/* 122 */           GL11.glEnable(3042);
/* 123 */           GL11.glBlendFunc(770, 771);
/*     */           
/* 125 */           this.field_73839_d.field_71446_o.func_110577_a(TEETH);
/* 126 */           func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */           
/* 128 */           if (playerEx.getVampireLevel() >= 2) {
/* 129 */             x = width / 2 - 90 + -40 + 2;
/*     */             
/* 131 */             this.field_73839_d.field_71446_o.func_110577_a(EYE);
/* 132 */             func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */             
/* 134 */             if (playerEx.getVampireLevel() >= 4) {
/* 135 */               x = width / 2 - 90 + -60 + 2;
/* 136 */               this.field_73839_d.field_71446_o.func_110577_a(RUN);
/* 137 */               func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */               
/* 139 */               if (playerEx.getVampireLevel() >= 7) {
/* 140 */                 x = width / 2 - 90 + -80 + 2;
/* 141 */                 this.field_73839_d.field_71446_o.func_110577_a(BAT);
/* 142 */                 func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */                 
/* 144 */                 if (playerEx.getVampireLevel() >= 10) {
/* 145 */                   x = width / 2 - 90 + -100 + 2;
/* 146 */                   switch (ClientEvents.1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampireUltimate[playerEx.getVampireUltimate().ordinal()]) {
/*     */                   case 1: 
/* 148 */                     this.field_73839_d.field_71446_o.func_110577_a(COFFIN);
/* 149 */                     break;
/*     */                   case 2: 
/* 151 */                     this.field_73839_d.field_71446_o.func_110577_a(STORM);
/* 152 */                     break;
/*     */                   case 3: 
/*     */                   default: 
/* 155 */                     this.field_73839_d.field_71446_o.func_110577_a(FIST);
/*     */                   }
/*     */                   
/* 158 */                   if ((playerEx.getVampireUltimateCharges() == 0) || (playerEx.getVampireUltimate() == ExtendedPlayer.VampireUltimate.NONE))
/*     */                   {
/*     */ 
/* 161 */                     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
/* 162 */                     func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */                   }
/*     */                   else {
/* 165 */                     func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 173 */           GL11.glDisable(3042);
/*     */         }
/*     */         
/* 176 */         this.field_73735_i -= 50.0F;
/*     */         
/* 178 */         for (int i = 0; i < 9; i++) {
/* 179 */           x = width / 2 - 90 + i * 20 + 2;
/* 180 */           z = height - 16 - 3;
/* 181 */           func_73832_a(i, x, z, event.partialTicks);
/*     */         }
/*     */         
/* 184 */         RenderHelper.func_74518_a();
/* 185 */         GL11.glDisable(32826);
/* 186 */         this.field_73839_d.field_71424_I.func_76319_b();
/*     */         
/* 188 */         renderToolHightlight(playerEx, width, height);
/*     */         
/* 190 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     protected void renderToolHightlight(ExtendedPlayer playerEx, int width, int height)
/*     */     {
/* 197 */       Minecraft mc = Minecraft.func_71410_x();
/* 198 */       if (mc.field_71474_y.field_92117_D) {
/* 199 */         if (--playerEx.highlightTicks > 0)
/*     */         {
/*     */ 
/*     */ 
/* 203 */           String name = "";
/* 204 */           switch (ClientEvents.1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampirePower[playerEx.getSelectedVampirePower().ordinal()]) {
/*     */           case 1: 
/* 206 */             name = Witchery.resource("witchery.vampirepower.feed");
/* 207 */             break;
/*     */           case 2: 
/* 209 */             name = Witchery.resource("witchery.vampirepower.eye");
/* 210 */             break;
/*     */           case 3: 
/* 212 */             name = Witchery.resource("witchery.vampirepower.speed");
/* 213 */             break;
/*     */           case 4: 
/* 215 */             name = Witchery.resource("witchery.vampirepower.bat");
/* 216 */             break;
/*     */           case 5: 
/* 218 */             switch (ClientEvents.1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampireUltimate[playerEx.getVampireUltimate().ordinal()]) {
/*     */             case 4: 
/* 220 */               name = String.format(Witchery.resource("witchery.vampirepower.unone"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
/*     */               
/* 222 */               break;
/*     */             case 1: 
/* 224 */               name = String.format(Witchery.resource("witchery.vampirepower.uteleport"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
/*     */               
/* 226 */               break;
/*     */             case 3: 
/* 228 */               name = String.format(Witchery.resource("witchery.vampirepower.ubats"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
/*     */               
/* 230 */               break;
/*     */             case 2: 
/* 232 */               name = String.format(Witchery.resource("witchery.vampirepower.ustorm"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
/*     */             }
/*     */             
/*     */             
/*     */ 
/* 237 */             break;
/*     */           }
/*     */           
/*     */           
/*     */ 
/* 242 */           if (name.equals("")) {
/* 243 */             return;
/*     */           }
/*     */           
/* 246 */           int opacity = (int)(playerEx.highlightTicks * 256.0F / 10.0F);
/* 247 */           if (opacity > 255) {
/* 248 */             opacity = 255;
/*     */           }
/* 250 */           if (opacity > 0) {
/* 251 */             int y = height - 69;
/* 252 */             if (!mc.field_71442_b.func_78755_b()) {
/* 253 */               y += 14;
/*     */             }
/* 255 */             GL11.glPushMatrix();
/* 256 */             GL11.glEnable(3042);
/* 257 */             OpenGlHelper.func_148821_a(770, 771, 1, 0);
/* 258 */             FontRenderer font = RenderManager.field_78727_a.func_78716_a();
/*     */             
/*     */ 
/* 261 */             if (font != null) {
/* 262 */               int x = (width - font.func_78256_a(name)) / 2;
/* 263 */               font.func_78261_a(name, x, y, 0xFFFFFF | opacity << 24);
/*     */             }
/* 265 */             GL11.glDisable(3042);
/* 266 */             GL11.glPopMatrix();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void renderExtraInventorySlot(ItemStack itemstack, int p_73832_2_, int p_73832_3_, float p_73832_4_)
/*     */     {
/* 274 */       if (itemstack != null) {
/* 275 */         float f1 = itemstack.field_77992_b - p_73832_4_;
/*     */         
/* 277 */         if (f1 > 0.0F) {
/* 278 */           GL11.glPushMatrix();
/* 279 */           float f2 = 1.0F + f1 / 5.0F;
/* 280 */           GL11.glTranslatef(p_73832_2_ + 8, p_73832_3_ + 12, 0.0F);
/* 281 */           GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
/* 282 */           GL11.glTranslatef(-(p_73832_2_ + 8), -(p_73832_3_ + 12), 0.0F);
/*     */         }
/*     */         
/* 285 */         field_73841_b.func_82406_b(this.field_73839_d.field_71466_p, this.field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);
/*     */         
/*     */ 
/* 288 */         if (f1 > 0.0F) {
/* 289 */           GL11.glPopMatrix();
/*     */         }
/*     */         
/* 292 */         field_73841_b.func_77021_b(this.field_73839_d.field_71466_p, this.field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onMouseEvent(MouseEvent event)
/*     */   {
/* 300 */     Minecraft mc = Minecraft.func_71410_x();
/* 301 */     ExtendedPlayer playerEx = ExtendedPlayer.get(mc.field_71439_g);
/* 302 */     if ((playerEx.isVampire()) && 
/* 303 */       (event.dwheel != 0)) {
/* 304 */       int MAXPOWER = playerEx.getMaxAvailablePowerOrdinal();
/* 305 */       if (mc.field_71439_g.field_71071_by.field_70461_c == 0) {
/* 306 */         int power = playerEx.getSelectedVampirePower().ordinal();
/* 307 */         if (event.dwheel > 0) {
/* 308 */           if (power == MAXPOWER) {
/* 309 */             playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
/*     */           } else {
/* 311 */             playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[(power + 1)], true);
/* 312 */             event.setCanceled(true);
/*     */           }
/*     */         }
/* 315 */         else if (power > 0) {
/* 316 */           playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[(power - 1)], true);
/* 317 */           event.setCanceled(true);
/*     */         }
/*     */       }
/* 320 */       else if ((mc.field_71439_g.field_71071_by.field_70461_c == 8) && 
/* 321 */         (event.dwheel < 0)) {
/* 322 */         playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[MAXPOWER], true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 329 */   private static final ResourceLocation BLOODDROP_BG = new ResourceLocation("witchery:textures/gui/bdropfaded.png");
/*     */   
/* 331 */   private static final ResourceLocation BLOODDROP = new ResourceLocation("witchery:textures/gui/bdrop.png");
/*     */   
/*     */   @SubscribeEvent(priority=EventPriority.LOWEST)
/*     */   public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
/* 335 */     switch (event.type) {
/*     */     case HOTBAR: 
/* 337 */       GUIOverlay.INSTANCE.renderHotbar(event);
/* 338 */       break;
/*     */     case TEXT: 
/* 340 */       EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
/* 341 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 342 */       if (playerEx.isVampire())
/*     */       {
/* 344 */         float left = Config.instance().guiOnLeft ? 10.0F : event.resolution.func_78326_a() - 10;
/* 345 */         float top = event.resolution.func_78328_b() * 0.5F + 16.0F;
/* 346 */         int maxBlood = playerEx.getMaxBloodPower();
/*     */         
/* 348 */         int pscale = 250;
/* 349 */         Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP_BG);
/* 350 */         for (int j = 0; j < maxBlood / 250; j++) {
/* 351 */           drawTexturedRect(left, top - j * 8, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
/*     */         }
/*     */         
/* 354 */         Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP);
/* 355 */         int pblood = playerEx.getBloodPower();
/* 356 */         for (j = 0; j < pblood / 250; j++) {
/* 357 */           drawTexturedRect(left, top - j * 8, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
/*     */         }
/* 359 */         if (pblood % 250 > 0) {
/* 360 */           float remainder = 8.0F * (pblood % 250.0F) / 250.0F;
/* 361 */           drawTexturedRect(left, top - j * 8 + 8.0F - remainder, 0.0F, 8.0F - remainder, 8.0F, remainder, 8.0F, 8.0F);
/*     */         }
/*     */         
/*     */ 
/* 365 */         MovingObjectPosition movingPosition = InfusionOtherwhere.raytraceEntities(Minecraft.func_71410_x().field_71441_e, Minecraft.func_71410_x().field_71439_g, true, 5.0D);
/*     */         
/* 367 */         if ((movingPosition != null) && (movingPosition.field_72308_g != null)) {
/* 368 */           int blood = -1;
/* 369 */           if ((movingPosition.field_72308_g instanceof EntityVillager)) {
/* 370 */             EntityVillager villager = (EntityVillager)movingPosition.field_72308_g;
/* 371 */             ExtendedVillager villagerEx = ExtendedVillager.get(villager);
/* 372 */             if (villagerEx.isClientSynced()) {
/* 373 */               blood = villagerEx.getBlood();
/*     */             }
/* 375 */           } else if ((movingPosition.field_72308_g instanceof EntityVillageGuard)) {
/* 376 */             EntityVillageGuard guard = (EntityVillageGuard)movingPosition.field_72308_g;
/* 377 */             blood = guard.getBlood();
/*     */           }
/* 379 */           else if ((movingPosition.field_72308_g instanceof EntityPlayer)) {
/* 380 */             EntityPlayer targetPlayer = (EntityPlayer)movingPosition.field_72308_g;
/* 381 */             ExtendedPlayer targetPlayerEx = ExtendedPlayer.get(targetPlayer);
/* 382 */             if (!targetPlayerEx.isVampire()) {
/* 383 */               blood = targetPlayerEx.getHumanBlood();
/*     */             }
/*     */           }
/*     */           
/* 387 */           if (blood >= 0) {
/* 388 */             int tscale = 25;
/* 389 */             int percent = (int)(blood / 500.0F * 100.0F);
/* 390 */             float midX = event.resolution.func_78326_a() / 2;
/* 391 */             float midY = event.resolution.func_78328_b() / 2;
/*     */             
/* 393 */             Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP_BG);
/*     */             
/* 395 */             for (int i = 0; i < 4; i++) {
/* 396 */               drawTexturedRect(midX - 16.0F + i * 8, midY + 10.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
/*     */             }
/*     */             
/* 399 */             Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP);
/* 400 */             for (i = 0; i < percent / 25; i++) {
/* 401 */               drawTexturedRect(midX - 16.0F + i * 8, midY + 10.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
/*     */             }
/*     */             
/* 404 */             if (percent % 25 > 0) {
/* 405 */               float remainder = 8.0F * (percent % 25.0F) / 25.0F;
/* 406 */               float scale = remainder / 8.0F;
/* 407 */               float offset = 8.0F - remainder;
/* 408 */               drawTexturedRect(midX - 16.0F + i * 8, midY + 10.0F + offset, 0.0F, offset, 8.0F, remainder, 8.0F, 8.0F);
/*     */             }
/* 410 */             if (Config.instance().hudShowVampireTargetBloodText) {
/* 411 */               float scale = 0.5F;
/* 412 */               GL11.glScalef(scale, scale, scale);
/* 413 */               String text = String.format("%d/%d", new Object[] { Integer.valueOf(blood), Integer.valueOf(500) });
/* 414 */               int width = RenderManager.field_78727_a.func_78716_a().func_78256_a(text);
/* 415 */               RenderManager.field_78727_a.func_78716_a().func_78276_b(text, (int)((event.resolution.func_78326_a() / 2 - width / 4) / scale), (int)((event.resolution.func_78328_b() / 2 + 22) / scale), 13369344);
/*     */               
/*     */ 
/* 418 */               GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
/*     */             }
/*     */           }
/*     */         } }
/* 422 */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void drawTexturedRect(float p_146110_0_, float p_146110_1_, float p_146110_2_, float p_146110_3_, float p_146110_4_, float p_146110_5_, float p_146110_6_, float p_146110_7_)
/*     */   {
/* 431 */     float f4 = 1.0F / p_146110_6_;
/* 432 */     float f5 = 1.0F / p_146110_7_;
/* 433 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 434 */     tessellator.func_78382_b();
/* 435 */     tessellator.func_78374_a(p_146110_0_, p_146110_1_ + p_146110_5_, 0.0D, p_146110_2_ * f4, (p_146110_3_ + p_146110_5_) * f5);
/*     */     
/* 437 */     tessellator.func_78374_a(p_146110_0_ + p_146110_4_, p_146110_1_ + p_146110_5_, 0.0D, (p_146110_2_ + p_146110_4_) * f4, (p_146110_3_ + p_146110_5_) * f5);
/*     */     
/*     */ 
/* 440 */     tessellator.func_78374_a(p_146110_0_ + p_146110_4_, p_146110_1_, 0.0D, (p_146110_2_ + p_146110_4_) * f4, p_146110_3_ * f5);
/*     */     
/* 442 */     tessellator.func_78374_a(p_146110_0_, p_146110_1_, 0.0D, p_146110_2_ * f4, p_146110_3_ * f5);
/*     */     
/* 444 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/* 447 */   private static final ModelGrotesque DEMON_HEAD_MODEL = new ModelGrotesque();
/* 448 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witchery", "textures/entities/Demon.png");
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onSetArmorModel(RenderPlayerEvent.SetArmorModel event)
/*     */   {
/* 453 */     EntityPlayer player = event.entityPlayer;
/* 454 */     if ((!player.func_82150_aj()) && (Infusion.getNBT(player).func_74764_b("witcheryGrotesque"))) {
/* 455 */       GL11.glPushMatrix();
/* 456 */       Minecraft mc = Minecraft.func_71410_x();
/* 457 */       mc.func_110434_K().func_110577_a(TEXTURE);
/*     */       
/* 459 */       float par9 = event.partialRenderTick;
/* 460 */       float f6 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * par9;
/*     */       
/* 462 */       float f2 = interpolateRotation(player.field_70760_ar, player.field_70761_aq, par9);
/* 463 */       float f3 = interpolateRotation(player.field_70758_at, player.field_70759_as, par9);
/*     */       
/* 465 */       DEMON_HEAD_MODEL.func_78088_a(event.entityPlayer, 0.0F, 0.0F, 0.0F, f3 - f2, f6, 0.0625F);
/*     */       
/* 467 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLivingJump(LivingEvent.LivingJumpEvent event) {
/* 473 */     if ((event.entity instanceof EntityPlayer)) {
/* 474 */       EntityPlayer player = (EntityPlayer)event.entity;
/* 475 */       Shapeshift.INSTANCE.updateJump(player);
/*     */     }
/*     */   }
/*     */   
/* 479 */   TransformWolf wolf = new TransformWolf();
/* 480 */   TransformWolfman wolfman = new TransformWolfman();
/* 481 */   TransformBat bat = new TransformBat();
/* 482 */   TransformOtherPlayer otherPlayer = new TransformOtherPlayer();
/*     */   
/* 484 */   RenderVillagerBed renderBed = new RenderVillagerBed();
/*     */   
/*     */   @SubscribeEvent(priority=EventPriority.HIGH)
/*     */   public void onPlayerPreRender(RenderLivingEvent.Pre event) {
/* 488 */     if ((event.entity instanceof EntityVillager)) {
/* 489 */       ExtendedVillager ext = ExtendedVillager.get((EntityVillager)event.entity);
/* 490 */       GL11.glPushMatrix();
/* 491 */       if ((ext != null) && (ext.isSleeping())) {
/* 492 */         GL11.glTranslated(event.x, event.y, event.z);
/*     */         
/* 494 */         this.renderBed.render((float)event.x, (float)event.y, (float)event.z, 0);
/* 495 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 496 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 497 */         GL11.glTranslated(0.5D, -1.25D, 0.0D);
/*     */         
/* 499 */         event.entity.func_70034_d(90.0F);
/*     */         
/* 501 */         GL11.glTranslated(-event.x, -event.y, -event.z);
/*     */       }
/* 503 */     } else if ((event.entity instanceof EntityPlayer)) {
/* 504 */       EntityPlayer player = (EntityPlayer)event.entity;
/* 505 */       if (WorldProviderDreamWorld.getPlayerIsGhost(Infusion.getNBT(player))) {
/* 506 */         RenderUtil.blend(true);
/* 507 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.51F);
/*     */       }
/*     */       
/* 510 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 511 */       int creatureType = playerEx.getCreatureTypeOrdinal();
/* 512 */       if ((creatureType > 0) && (!(event.renderer instanceof RenderOtherPlayer))) {
/* 513 */         event.setCanceled(true);
/*     */         
/* 515 */         PotionEffect pe = player.func_70660_b(Witchery.Potions.RESIZING);
/* 516 */         if (pe != null) {
/* 517 */           GL11.glPushMatrix();
/* 518 */           GL11.glTranslated(event.x, event.y, event.z);
/* 519 */           float scale = com.emoniph.witchery.brewing.potions.PotionResizing.getModifiedScaleFactor(player, pe.func_76458_c());
/*     */           
/* 521 */           GL11.glScalef(scale, scale, scale);
/* 522 */           GL11.glTranslated(-event.x, -event.y, -event.z);
/*     */         }
/*     */         
/* 525 */         boolean gui = (player.field_70759_as == player.field_70177_z) && (player.field_70758_at == player.field_70177_z) && (RenderManager.field_78727_a.field_78735_i == 180.0F) && (Minecraft.func_71410_x().field_71462_r != null);
/*     */         
/*     */ 
/*     */ 
/* 529 */         float partialTicks = gui ? 0.0F : com.emoniph.witchery.brewing.potions.ModelOverlayRenderer.getRenderPartialTicks();
/*     */         
/* 531 */         if (creatureType == 1)
/*     */         {
/* 533 */           this.wolf.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
/*     */         }
/* 535 */         else if (creatureType == 2)
/*     */         {
/* 537 */           this.wolfman.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
/*     */         }
/* 539 */         else if (creatureType == 3) {
/* 540 */           this.bat.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
/*     */         }
/* 542 */         else if ((creatureType == 4) && (playerEx.getOtherPlayerSkin() != null) && (!playerEx.getOtherPlayerSkin().equals("")))
/*     */         {
/* 544 */           this.otherPlayer.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
/*     */         }
/*     */         
/*     */ 
/* 548 */         if (pe != null) {
/* 549 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderLivingSpecialsPre(RenderLivingEvent.Specials.Pre event) {
/* 557 */     if ((!event.isCanceled()) && 
/* 558 */       (Config.instance().allowNameplateMasquerading) && ((event.entity instanceof EntityPlayer))) {
/* 559 */       EntityPlayer player = (EntityPlayer)event.entity;
/* 560 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 561 */       if (playerEx.getCreatureType() == TransformCreature.PLAYER) {
/* 562 */         event.setCanceled(true);
/*     */         
/* 564 */         GL11.glAlphaFunc(516, 0.1F);
/*     */         
/* 566 */         EntityLivingBase p_77033_1_ = event.entity;
/* 567 */         double p_77033_2_ = event.x;double p_77033_4_ = event.y;double p_77033_6_ = event.z;
/*     */         
/* 569 */         RenderManager renderManager = RenderManager.field_78727_a;
/*     */         
/* 571 */         if ((Minecraft.func_71382_s()) && (p_77033_1_ != renderManager.field_78734_h) && (!p_77033_1_.func_98034_c(Minecraft.func_71410_x().field_71439_g)) && (p_77033_1_.field_70153_n == null))
/*     */         {
/*     */ 
/* 574 */           float f = 1.6F;
/* 575 */           float f1 = 0.016666668F * f;
/* 576 */           double d3 = p_77033_1_.func_70068_e(renderManager.field_78734_h);
/* 577 */           float f2 = p_77033_1_.func_70093_af() ? 32.0F : 64.0F;
/*     */           
/* 579 */           if (d3 < f2 * f2) {
/* 580 */             String skin = playerEx.getOtherPlayerSkin();
/* 581 */             if ((skin == null) || (skin.isEmpty())) {
/* 582 */               return;
/*     */             }
/* 584 */             String s = new ChatComponentText(skin).func_150254_d();
/*     */             
/* 586 */             if (p_77033_1_.func_70093_af()) {
/* 587 */               FontRenderer fontrenderer = renderManager.func_78716_a();
/* 588 */               GL11.glPushMatrix();
/* 589 */               GL11.glTranslatef((float)p_77033_2_ + 0.0F, (float)p_77033_4_ + p_77033_1_.field_70131_O + 0.5F, (float)p_77033_6_);
/*     */               
/* 591 */               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 592 */               GL11.glRotatef(-renderManager.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 593 */               GL11.glRotatef(renderManager.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 594 */               GL11.glScalef(-f1, -f1, f1);
/* 595 */               GL11.glDisable(2896);
/* 596 */               GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
/* 597 */               GL11.glDepthMask(false);
/* 598 */               GL11.glEnable(3042);
/* 599 */               OpenGlHelper.func_148821_a(770, 771, 1, 0);
/* 600 */               Tessellator tessellator = Tessellator.field_78398_a;
/* 601 */               GL11.glDisable(3553);
/* 602 */               tessellator.func_78382_b();
/* 603 */               int i = fontrenderer.func_78256_a(s) / 2;
/* 604 */               tessellator.func_78369_a(0.0F, 0.0F, 0.0F, 0.25F);
/* 605 */               tessellator.func_78377_a(-i - 1, -1.0D, 0.0D);
/* 606 */               tessellator.func_78377_a(-i - 1, 8.0D, 0.0D);
/* 607 */               tessellator.func_78377_a(i + 1, 8.0D, 0.0D);
/* 608 */               tessellator.func_78377_a(i + 1, -1.0D, 0.0D);
/* 609 */               tessellator.func_78381_a();
/* 610 */               GL11.glEnable(3553);
/* 611 */               GL11.glDepthMask(true);
/* 612 */               fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 553648127);
/* 613 */               GL11.glEnable(2896);
/* 614 */               GL11.glDisable(3042);
/* 615 */               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 616 */               GL11.glPopMatrix();
/*     */             } else {
/* 618 */               func_96449_a(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_, s, f1, d3);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_96449_a(EntityLivingBase p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_)
/*     */   {
/* 630 */     if (!p_96449_1_.func_70608_bn())
/*     */     {
/* 632 */       func_147906_a(p_96449_1_, p_96449_8_, p_96449_2_, p_96449_4_, p_96449_6_, 64);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_147906_a(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_)
/*     */   {
/* 638 */     RenderManager renderManager = RenderManager.field_78727_a;
/*     */     
/* 640 */     double d3 = p_147906_1_.func_70068_e(renderManager.field_78734_h);
/*     */     
/* 642 */     if (d3 <= p_147906_9_ * p_147906_9_) {
/* 643 */       FontRenderer fontrenderer = renderManager.func_78716_a();
/* 644 */       float f = 1.6F;
/* 645 */       float f1 = 0.016666668F * f;
/* 646 */       GL11.glPushMatrix();
/* 647 */       GL11.glTranslatef((float)p_147906_3_ + 0.0F, (float)p_147906_5_ + p_147906_1_.field_70131_O + 0.5F, (float)p_147906_7_);
/*     */       
/* 649 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 650 */       GL11.glRotatef(-renderManager.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 651 */       GL11.glRotatef(renderManager.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 652 */       GL11.glScalef(-f1, -f1, f1);
/* 653 */       GL11.glDisable(2896);
/* 654 */       GL11.glDepthMask(false);
/* 655 */       GL11.glDisable(2929);
/* 656 */       GL11.glEnable(3042);
/* 657 */       OpenGlHelper.func_148821_a(770, 771, 1, 0);
/* 658 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 659 */       byte b0 = 0;
/*     */       
/* 661 */       if (p_147906_2_.equals("deadmau5")) {
/* 662 */         b0 = -10;
/*     */       }
/*     */       
/* 665 */       GL11.glDisable(3553);
/* 666 */       tessellator.func_78382_b();
/* 667 */       int j = fontrenderer.func_78256_a(p_147906_2_) / 2;
/* 668 */       tessellator.func_78369_a(0.0F, 0.0F, 0.0F, 0.25F);
/* 669 */       tessellator.func_78377_a(-j - 1, -1 + b0, 0.0D);
/* 670 */       tessellator.func_78377_a(-j - 1, 8 + b0, 0.0D);
/* 671 */       tessellator.func_78377_a(j + 1, 8 + b0, 0.0D);
/* 672 */       tessellator.func_78377_a(j + 1, -1 + b0, 0.0D);
/* 673 */       tessellator.func_78381_a();
/* 674 */       GL11.glEnable(3553);
/* 675 */       fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, 553648127);
/* 676 */       GL11.glEnable(2929);
/* 677 */       GL11.glDepthMask(true);
/* 678 */       fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, -1);
/* 679 */       GL11.glEnable(2896);
/* 680 */       GL11.glDisable(3042);
/* 681 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 682 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerPostRender(RenderLivingEvent.Post event) {
/* 688 */     if ((event.entity instanceof EntityVillager)) {
/* 689 */       GL11.glPopMatrix();
/* 690 */     } else if ((event.entity instanceof EntityPlayer)) {
/* 691 */       EntityPlayer player = (EntityPlayer)event.entity;
/* 692 */       if (WorldProviderDreamWorld.getPlayerIsGhost(Infusion.getNBT(player))) {
/* 693 */         RenderUtil.blend(false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderHand(RenderHandEvent event) {
/* 700 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 702 */     ExtendedPlayer playerEx = ExtendedPlayer.get(mc.field_71439_g);
/* 703 */     TransformCreature creatureType = playerEx.getCreatureType();
/* 704 */     if ((creatureType != TransformCreature.NONE) && ((mc.field_71439_g.func_70694_bm() == null) || ((creatureType != TransformCreature.WOLFMAN) && (creatureType != TransformCreature.PLAYER))))
/*     */     {
/* 706 */       event.setCanceled(true);
/* 707 */       renderArm(event.renderPass, event.partialTicks, mc, mc.field_71439_g.func_70694_bm() != null, creatureType, playerEx);
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderArm(int renderPass, float partialTicks, Minecraft mc, boolean hasItem, TransformCreature creatureType, ExtendedPlayer playerEx)
/*     */   {
/* 713 */     GL11.glClear(256);
/*     */     
/* 715 */     float farPlaneDistance = mc.field_71474_y.field_151451_c * 16;
/* 716 */     double cameraZoom = 1.0D;
/* 717 */     double cameraYaw = 0.0D;
/* 718 */     double cameraPitch = 0.0D;
/*     */     
/* 720 */     if (mc.field_71460_t.field_78532_q <= 0) {
/* 721 */       GL11.glMatrixMode(5889);
/* 722 */       GL11.glLoadIdentity();
/* 723 */       float f1 = 0.07F;
/*     */       
/* 725 */       if (mc.field_71474_y.field_74337_g) {
/* 726 */         GL11.glTranslatef(-(renderPass * 2 - 1) * f1, 0.0F, 0.0F);
/*     */       }
/*     */       
/* 729 */       if (cameraZoom != 1.0D) {
/* 730 */         GL11.glTranslatef((float)cameraYaw, (float)-cameraPitch, 0.0F);
/* 731 */         GL11.glScaled(cameraZoom, cameraZoom, 1.0D);
/*     */       }
/*     */       
/* 734 */       org.lwjgl.util.glu.Project.gluPerspective(getFOVModifier(partialTicks, mc.field_71460_t, mc), mc.field_71443_c / mc.field_71440_d, 0.05F, farPlaneDistance * 2.0F);
/*     */       
/*     */ 
/* 737 */       if (mc.field_71442_b.func_78747_a()) {
/* 738 */         float f2 = 0.6666667F;
/* 739 */         GL11.glScalef(1.0F, f2, 1.0F);
/*     */       }
/*     */       
/* 742 */       GL11.glMatrixMode(5888);
/* 743 */       GL11.glLoadIdentity();
/*     */       
/* 745 */       if (mc.field_71474_y.field_74337_g) {
/* 746 */         GL11.glTranslatef((renderPass * 2 - 1) * 0.1F, 0.0F, 0.0F);
/*     */       }
/*     */       
/* 749 */       GL11.glPushMatrix();
/* 750 */       hurtCameraEffect(partialTicks, mc);
/*     */       
/* 752 */       if (mc.field_71474_y.field_74336_f) {
/* 753 */         setupViewBobbing(partialTicks, mc);
/*     */       }
/*     */       
/* 756 */       if ((mc.field_71474_y.field_74320_O == 0) && (!mc.field_71451_h.func_70608_bn()) && (!mc.field_71474_y.field_74319_N) && (!mc.field_71442_b.func_78747_a()))
/*     */       {
/* 758 */         mc.field_71460_t.func_78463_b(partialTicks);
/* 759 */         if ((hasItem) && ((creatureType == TransformCreature.WOLF) || (creatureType == TransformCreature.BAT))) {
/* 760 */           if (mc.field_71439_g.func_71052_bv() == 0) {
/* 761 */             GL11.glTranslatef(-0.4F, 0.1F, 0.0F);
/* 762 */             GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
/*     */           }
/* 764 */           mc.field_71460_t.field_78516_c.func_78440_a(partialTicks);
/*     */         }
/* 766 */         else if ((creatureType == TransformCreature.WOLF) || (creatureType == TransformCreature.PLAYER) || (creatureType == TransformCreature.WOLFMAN)) {
/* 767 */           renderEmptyHand(mc, partialTicks, creatureType, playerEx);
/*     */         }
/*     */         
/*     */ 
/* 771 */         mc.field_71460_t.func_78483_a(partialTicks);
/*     */       }
/*     */       
/* 774 */       GL11.glPopMatrix();
/*     */       
/* 776 */       if ((mc.field_71474_y.field_74320_O == 0) && (!mc.field_71451_h.func_70608_bn())) {
/* 777 */         mc.field_71460_t.field_78516_c.func_78447_b(partialTicks);
/* 778 */         hurtCameraEffect(partialTicks, mc);
/*     */       }
/*     */       
/* 781 */       if (mc.field_71474_y.field_74336_f) {
/* 782 */         setupViewBobbing(partialTicks, mc);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 787 */   private static final ResourceLocation wolfSkin = new ResourceLocation("witchery", "textures/entities/werewolf_man.png");
/*     */   
/*     */   private void renderEmptyHand(Minecraft mc, float p_78440_1_, TransformCreature creatureType, ExtendedPlayer playerEx)
/*     */   {
/* 791 */     float f1 = 1.0F;
/*     */     
/*     */ 
/* 794 */     EntityClientPlayerMP entityclientplayermp = mc.field_71439_g;
/* 795 */     float f2 = entityclientplayermp.field_70127_C + (entityclientplayermp.field_70125_A - entityclientplayermp.field_70127_C) * p_78440_1_;
/*     */     
/* 797 */     GL11.glPushMatrix();
/* 798 */     GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
/* 799 */     GL11.glRotatef(entityclientplayermp.field_70126_B + (entityclientplayermp.field_70177_z - entityclientplayermp.field_70126_B) * p_78440_1_, 0.0F, 1.0F, 0.0F);
/*     */     
/*     */ 
/* 802 */     RenderHelper.func_74519_b();
/* 803 */     GL11.glPopMatrix();
/* 804 */     EntityPlayerSP entityplayersp = entityclientplayermp;
/* 805 */     float f3 = entityplayersp.field_71164_i + (entityplayersp.field_71155_g - entityplayersp.field_71164_i) * p_78440_1_;
/*     */     
/* 807 */     float f4 = entityplayersp.field_71163_h + (entityplayersp.field_71154_f - entityplayersp.field_71163_h) * p_78440_1_;
/*     */     
/* 809 */     GL11.glRotatef((entityclientplayermp.field_70125_A - f3) * 0.1F, 1.0F, 0.0F, 0.0F);
/* 810 */     GL11.glRotatef((entityclientplayermp.field_70177_z - f4) * 0.1F, 0.0F, 1.0F, 0.0F);
/*     */     
/* 812 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 814 */     if (!entityclientplayermp.func_82150_aj()) {
/* 815 */       GL11.glPushMatrix();
/* 816 */       float f13 = 0.8F;
/* 817 */       float f5 = entityclientplayermp.func_70678_g(p_78440_1_);
/* 818 */       float f6 = MathHelper.func_76126_a(f5 * 3.1415927F);
/* 819 */       float f7 = MathHelper.func_76126_a(MathHelper.func_76129_c(f5) * 3.1415927F);
/* 820 */       GL11.glTranslatef(-f7 * 0.3F, MathHelper.func_76126_a(MathHelper.func_76129_c(f5) * 3.1415927F * 2.0F) * 0.4F, -f6 * 0.4F);
/*     */       
/* 822 */       GL11.glTranslatef(0.8F * f13, -0.75F * f13 - (1.0F - f1) * 0.6F, -0.9F * f13);
/* 823 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 824 */       GL11.glEnable(32826);
/* 825 */       f5 = entityclientplayermp.func_70678_g(p_78440_1_);
/* 826 */       f6 = MathHelper.func_76126_a(f5 * f5 * 3.1415927F);
/* 827 */       f7 = MathHelper.func_76126_a(MathHelper.func_76129_c(f5) * 3.1415927F);
/* 828 */       GL11.glRotatef(f7 * 70.0F, 0.0F, 1.0F, 0.0F);
/* 829 */       GL11.glRotatef(-f6 * 20.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/* 831 */       if (creatureType == TransformCreature.WOLF) {
/* 832 */         float scale = 1.5F;
/*     */         
/* 834 */         GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
/* 835 */         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
/* 836 */         GL11.glTranslatef(-0.3F, 0.1F, -0.5F);
/* 837 */         GL11.glScalef(1.0F, 1.0F, 2.0F);
/* 838 */         mc.func_110434_K().func_110577_a(wolfSkin);
/* 839 */       } else if (creatureType == TransformCreature.WOLFMAN) {
/* 840 */         mc.func_110434_K().func_110577_a(wolfSkin);
/* 841 */       } else if (creatureType == TransformCreature.PLAYER) {
/* 842 */         mc.func_110434_K().func_110577_a(playerEx.getLocationSkin());
/*     */       }
/*     */       
/* 845 */       GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
/* 846 */       GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
/* 847 */       GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
/* 848 */       GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
/* 849 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 850 */       GL11.glTranslatef(5.6F, 0.0F, 0.0F);
/* 851 */       Render render = RenderManager.field_78727_a.func_78713_a(mc.field_71439_g);
/* 852 */       RenderPlayer renderplayer = (RenderPlayer)render;
/* 853 */       float f10 = 1.0F;
/* 854 */       GL11.glScalef(f10, f10, f10);
/* 855 */       renderplayer.func_82441_a(mc.field_71439_g);
/* 856 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 859 */     GL11.glDisable(32826);
/* 860 */     RenderHelper.func_74518_a();
/*     */   }
/*     */   
/*     */   private void hurtCameraEffect(float p_78482_1_, Minecraft mc) {
/* 864 */     EntityLivingBase entitylivingbase = mc.field_71451_h;
/* 865 */     float f1 = entitylivingbase.field_70737_aN - p_78482_1_;
/*     */     
/*     */ 
/* 868 */     if (entitylivingbase.func_110143_aJ() <= 0.0F) {
/* 869 */       float f2 = entitylivingbase.field_70725_aQ + p_78482_1_;
/* 870 */       GL11.glRotatef(40.0F - 8000.0F / (f2 + 200.0F), 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     
/* 873 */     if (f1 >= 0.0F) {
/* 874 */       f1 /= entitylivingbase.field_70738_aO;
/* 875 */       f1 = MathHelper.func_76126_a(f1 * f1 * f1 * f1 * 3.1415927F);
/* 876 */       float f2 = entitylivingbase.field_70739_aP;
/* 877 */       GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
/* 878 */       GL11.glRotatef(-f1 * 14.0F, 0.0F, 0.0F, 1.0F);
/* 879 */       GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupViewBobbing(float p_78475_1_, Minecraft mc) {
/* 884 */     if ((mc.field_71451_h instanceof EntityPlayer)) {
/* 885 */       EntityPlayer entityplayer = (EntityPlayer)mc.field_71451_h;
/* 886 */       float f1 = entityplayer.field_70140_Q - entityplayer.field_70141_P;
/* 887 */       float f2 = -(entityplayer.field_70140_Q + f1 * p_78475_1_);
/* 888 */       float f3 = entityplayer.field_71107_bF + (entityplayer.field_71109_bG - entityplayer.field_71107_bF) * p_78475_1_;
/*     */       
/* 890 */       float f4 = entityplayer.field_70727_aS + (entityplayer.field_70726_aT - entityplayer.field_70727_aS) * p_78475_1_;
/*     */       
/* 892 */       GL11.glTranslatef(MathHelper.func_76126_a(f2 * 3.1415927F) * f3 * 0.5F, -Math.abs(MathHelper.func_76134_b(f2 * 3.1415927F) * f3), 0.0F);
/*     */       
/* 894 */       GL11.glRotatef(MathHelper.func_76126_a(f2 * 3.1415927F) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
/* 895 */       GL11.glRotatef(Math.abs(MathHelper.func_76134_b(f2 * 3.1415927F - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
/* 896 */       GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private float getFOVModifier(float partialTicks, EntityRenderer er, Minecraft mc) {
/* 901 */     if (er.field_78532_q > 0) {
/* 902 */       return 90.0F;
/*     */     }
/* 904 */     EntityLivingBase entityplayer = mc.field_71451_h;
/* 905 */     float f1 = 70.0F;
/*     */     
/* 907 */     if (entityplayer.func_110143_aJ() <= 0.0F) {
/* 908 */       float f2 = entityplayer.field_70725_aQ + partialTicks;
/* 909 */       f1 /= ((1.0F - 500.0F / (f2 + 500.0F)) * 2.0F + 1.0F);
/*     */     }
/*     */     
/* 912 */     Block block = net.minecraft.client.renderer.ActiveRenderInfo.func_151460_a(mc.field_71441_e, entityplayer, partialTicks);
/*     */     
/* 914 */     if (block.func_149688_o() == Material.field_151586_h) {
/* 915 */       f1 = f1 * 60.0F / 70.0F;
/*     */     }
/*     */     
/* 918 */     return f1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private float interpolateRotation(float par1, float par2, float par3)
/*     */   {
/* 925 */     for (float f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F) {}
/*     */     
/*     */ 
/*     */ 
/* 929 */     while (f3 >= 180.0F) {
/* 930 */       f3 -= 360.0F;
/*     */     }
/*     */     
/* 933 */     return par1 + par3 * f3;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/ClientEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */