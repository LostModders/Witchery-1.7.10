/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.client.renderer.RenderInfusionEnergyBar;
/*     */ import com.emoniph.witchery.infusion.InfusedBrewEffect;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.infusion.Infusion.Registry;
/*     */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
/*     */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePower.Registry;
/*     */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSpeed;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.integration.ModHookManager;
/*     */ import com.emoniph.witchery.item.ItemBrewBag.InventoryBrewBag;
/*     */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.EntitySizeInfo;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.RenderUtil;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class PlayerRender
/*     */ {
/*     */   protected static RenderInfusionEnergyBar infusionEnergyBar;
/*     */   protected static RenderInfusionEnergyBar creatureEnergyBar;
/*  63 */   private boolean remoteViewingActive = true;
/*     */   
/*  65 */   public static long ticksSinceActive = 0L;
/*  66 */   public static boolean moveCameraActive = false;
/*  67 */   public static int moveCameraToEntityID = 0;
/*     */   
/*  69 */   private static final ResourceLocation RADIAL_LOCATION = new ResourceLocation("witchery", "textures/gui/radial.png");
/*     */   
/*     */   EntityRenderer prevRender;
/*     */   
/*     */   RenderEntityViewer renderer;
/*  74 */   int currentBeastForm = 0;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderTick(TickEvent.RenderTickEvent event) {
/*  78 */     if (event.phase == TickEvent.Phase.START) {
/*  79 */       EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
/*  80 */       Minecraft mc = Minecraft.func_71410_x();
/*  81 */       if ((player != null) && (mc.field_71462_r == null)) {
/*  82 */         if (Minecraft.func_71386_F() - ticksSinceActive > 3000L) {
/*  83 */           moveCameraActive = false;
/*     */         }
/*  85 */         this.remoteViewingActive = moveCameraActive;
/*  86 */         if (this.remoteViewingActive) {
/*  87 */           for (Iterator i$ = player.field_70170_p.field_72996_f.iterator(); i$.hasNext(); 
/*     */               
/*     */ 
/*     */ 
/*  91 */               goto 150)
/*     */           {
/*  87 */             Object entity = i$.next();
/*  88 */             if ((((Entity)entity).func_145782_y() == moveCameraToEntityID) && ((entity instanceof EntityLivingBase))) {
/*  89 */               EntityLivingBase living = (EntityLivingBase)entity;
/*  90 */               if (living.field_70128_L) break;
/*  91 */               Minecraft.func_71410_x().field_71451_h = living;
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/*  98 */           EntitySizeInfo size = new EntitySizeInfo(player);
/*  99 */           PotionEffect shrunk = Witchery.Potions.RESIZING != null ? player.func_70660_b(Witchery.Potions.RESIZING) : null;
/* 100 */           if (((shrunk != null) || (!size.isDefault)) && (!com.emoniph.witchery.integration.ModHookMorph.isMorphed(player, true))) {
/* 101 */             if (this.renderer == null) {
/* 102 */               this.renderer = new RenderEntityViewer(mc);
/*     */             }
/* 104 */             if (mc.field_71474_y.field_74320_O == 0) {
/* 105 */               if (mc.field_71460_t != this.renderer) {
/* 106 */                 this.prevRender = mc.field_71460_t;
/* 107 */                 mc.field_71460_t = this.renderer;
/*     */               }
/* 109 */             } else if (this.prevRender != null) {
/* 110 */               mc.field_71460_t = this.prevRender;
/*     */             }
/*     */             
/* 113 */             float normalSize = 1.8F;
/* 114 */             float scale = size.defaultHeight / 1.8F * (shrunk != null ? com.emoniph.witchery.brewing.potions.PotionResizing.getScaleFactor(shrunk.func_76458_c()) : 1.0F);
/*     */             
/* 116 */             if (scale < 1.0F) {
/* 117 */               float requiredSize = 1.8F * (1.0F - scale);
/* 118 */               float currentSize = this.renderer.getOffset();
/* 119 */               if (currentSize < requiredSize) {
/* 120 */                 currentSize = Math.min(currentSize + 0.01F, requiredSize);
/* 121 */               } else if (currentSize > requiredSize) {
/* 122 */                 currentSize = Math.min(currentSize - 0.01F, requiredSize);
/*     */               }
/* 124 */               this.renderer.setOffset(currentSize);
/*     */             } else {
/* 126 */               float requiredSize = -(1.8F * scale - 1.8F);
/* 127 */               float currentSize = this.renderer.getOffset();
/* 128 */               if (currentSize > requiredSize) {
/* 129 */                 currentSize = Math.max(currentSize - 0.01F, requiredSize);
/*     */               }
/* 131 */               this.renderer.setOffset(currentSize);
/*     */             }
/* 133 */           } else if ((this.prevRender != null) && (mc.field_71460_t != this.prevRender)) {
/* 134 */             if (this.renderer != null) {
/* 135 */               this.renderer.setOffset(0.0F);
/*     */             }
/* 137 */             mc.field_71460_t = this.prevRender;
/*     */           }
/*     */         }
/*     */       }
/* 141 */     } else if (event.phase == TickEvent.Phase.END) {
/* 142 */       EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
/* 143 */       if ((player != null) && (Minecraft.func_71410_x().field_71462_r == null)) {
/* 144 */         Minecraft mc = Minecraft.func_71410_x();
/*     */         
/* 146 */         if (this.remoteViewingActive) {
/* 147 */           Minecraft.func_71410_x().field_71451_h = player;
/*     */         }
/*     */         
/*     */ 
/* 151 */         ScaledResolution screen = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/*     */         
/*     */ 
/* 154 */         int maxEnergy = Infusion.getMaxEnergy(player);
/* 155 */         if (maxEnergy > 0) {
/* 156 */           if (infusionEnergyBar == null) {
/* 157 */             infusionEnergyBar = new RenderInfusionEnergyBar(true);
/*     */           }
/*     */           
/* 160 */           double left = Config.instance().guiOnLeft ? 20.0D : screen.func_78326_a() - 20;
/* 161 */           double top = screen.func_78328_b() / 2.0D - 16.0D;
/* 162 */           Infusion infusion = Infusion.Registry.instance().get(player);
/* 163 */           infusionEnergyBar.draw(left, top, Infusion.getCurrentEnergy(player) / maxEnergy, player, infusion.infusionID);
/*     */         }
/*     */         
/*     */ 
/* 167 */         int powerID = CreaturePower.getCreaturePowerID(player);
/* 168 */         int charges = CreaturePower.getCreaturePowerCharges(player);
/* 169 */         if (powerID > 0) {
/* 170 */           if (creatureEnergyBar == null) {
/* 171 */             creatureEnergyBar = new RenderInfusionEnergyBar(false);
/*     */           }
/*     */           
/* 174 */           double left = Config.instance().guiOnLeft ? 30.0D : screen.func_78326_a() - 30;
/*     */           
/* 176 */           double top = screen.func_78328_b() / 2.0D - 16.0D;
/* 177 */           creatureEnergyBar.draw(left, top, charges, player, powerID);
/*     */         }
/*     */         
/*     */ 
/* 181 */         ItemStack belt = player.func_71124_b(2);
/* 182 */         if ((belt != null) && (belt.func_77973_b() == Witchery.Items.BARK_BELT)) {
/* 183 */           int beltCharges = Math.min(Witchery.Items.BARK_BELT.getChargeLevel(belt), Witchery.Items.BARK_BELT.getMaxChargeLevel(player));
/* 184 */           drawBarkBeltCharges(player, beltCharges, screen);
/*     */         }
/*     */         
/*     */ 
/* 188 */         drawInfusedBrews(player, screen);
/*     */         
/*     */ 
/* 191 */         ItemStack stack = player.func_71011_bu();
/* 192 */         if ((stack != null) && (stack.func_77973_b() == Witchery.Items.MYSTIC_BRANCH)) {
/* 193 */           byte[] strokes = player.getEntityData().func_74770_j("Strokes");
/*     */           
/* 195 */           mc.func_110434_K().func_110577_a(TEXTURE_GRID);
/* 196 */           GL11.glPushMatrix();
/* 197 */           int iconOffset = 0;
/* 198 */           if (Config.instance().branchIconSet == 1) {
/* 199 */             iconOffset = 64;
/*     */           }
/*     */           try {
/* 202 */             int x = screen.func_78326_a() / 2 - 8;
/* 203 */             int y = screen.func_78328_b() / 2 - 8;
/* 204 */             int DELAY = 8;
/* 205 */             this.lastY = (this.lastY == 120 ? 0 : this.lastY + 1);
/* 206 */             int tempIndex = this.lastY / 8;
/* 207 */             int imageIndex = tempIndex > 7 ? 15 - tempIndex : tempIndex;
/* 208 */             for (int i = 0; i < strokes.length; i++) {
/* 209 */               x += glyphOffsetX[strokes[i]] * 16;
/* 210 */               y += glyphOffsetY[strokes[i]] * 16;
/*     */               
/* 212 */               drawTexturedModalRect(x, y, strokes[i] * 16 + iconOffset, imageIndex * 16, 16, 16);
/*     */             }
/* 214 */             SymbolEffect effect = EffectRegistry.instance().getEffect(strokes);
/* 215 */             if (effect != null) {
/* 216 */               String text = effect.getLocalizedName();
/* 217 */               int tx = screen.func_78326_a() / 2 - (int)(getStringWidth(text) / 2.0D);
/* 218 */               int ty = screen.func_78328_b() / 2 + 20;
/* 219 */               drawString(text, tx, ty, 16777215);
/*     */             }
/*     */           } finally {
/* 222 */             GL11.glPopMatrix();
/*     */           }
/* 224 */         } else if ((stack != null) && (stack.func_77973_b() == Witchery.Items.BREW_BAG) && (!player.func_70093_af()))
/*     */         {
/* 226 */           ItemBrewBag.InventoryBrewBag inv = new ItemBrewBag.InventoryBrewBag(player);
/* 227 */           byte[] strokes = player.getEntityData().func_74770_j("Strokes");
/*     */           
/* 229 */           GL11.glPushMatrix();
/*     */           try
/*     */           {
/* 232 */             int x = screen.func_78326_a() / 2 - 8;
/* 233 */             int y = screen.func_78328_b() / 2 - 8;
/*     */             
/* 235 */             if (strokes.length == 0) {
/* 236 */               mc.func_110434_K().func_110577_a(RADIAL_LOCATION);
/*     */               
/* 238 */               GL11.glPushMatrix();
/* 239 */               float scale = 0.33333334F;
/* 240 */               GL11.glTranslatef(x - 42 + 5, y - 42 + 5, 0.0F);
/* 241 */               GL11.glScalef(scale, scale, scale);
/* 242 */               int color = com.emoniph.witchery.item.ItemBrewBag.getColor(stack);
/*     */               
/* 244 */               float red = (color >>> 16 & 0xFF) / 256.0F;
/* 245 */               float green = (color >>> 8 & 0xFF) / 256.0F;
/* 246 */               float blue = (color & 0xFF) / 256.0F;
/* 247 */               GL11.glColor4f(red, green, blue, 1.0F);
/* 248 */               drawTexturedModalRect(8, 8, 0, 0, 256, 256);
/* 249 */               GL11.glPopMatrix();
/*     */             }
/*     */             
/* 252 */             drawBrewInSlot(inv, 0, strokes, x + 0, y - 32, 0, -11, 1);
/* 253 */             drawBrewInSlot(inv, 1, strokes, x + 24, y - 24, 23, 6, 0);
/* 254 */             drawBrewInSlot(inv, 2, strokes, x + 32, y - 0, 23, 6, 0);
/* 255 */             drawBrewInSlot(inv, 3, strokes, x + 24, y + 24, 23, 6, 0);
/* 256 */             drawBrewInSlot(inv, 4, strokes, x + 0, y + 32, 0, 19, 1);
/* 257 */             drawBrewInSlot(inv, 5, strokes, x - 24, y + 24, -5, 6, 2);
/* 258 */             drawBrewInSlot(inv, 6, strokes, x - 32, y - 0, -5, 6, 2);
/* 259 */             drawBrewInSlot(inv, 7, strokes, x - 24, y - 24, -5, 6, 2);
/*     */           } finally {
/* 261 */             GL11.glPopMatrix();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawInfusedBrews(EntityClientPlayerMP player, ScaledResolution screen) {
/* 269 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 270 */     InfusedBrewEffect effect = InfusedBrewEffect.getActiveBrew(nbtPlayer);
/* 271 */     if (effect != null) {
/* 272 */       String remainingTime = InfusedBrewEffect.getMinutesRemaining(player.field_70170_p, nbtPlayer, effect);
/* 273 */       if ((remainingTime != null) && (!remainingTime.isEmpty())) {
/* 274 */         Minecraft mc = Minecraft.func_71410_x();
/* 275 */         mc.func_110434_K().func_110577_a(BARK_TEXTURES);
/* 276 */         int tx = screen.func_78326_a() / 2 - 91;
/* 277 */         int screenHeight = screen.func_78328_b();
/* 278 */         int top = screen.func_78328_b() / 2 + 26;
/* 279 */         int screenMid = screenHeight / 2;
/*     */         
/* 281 */         int left = Config.instance().guiOnLeft ? 17 : screen.func_78326_a() - 23;
/*     */         
/* 283 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/* 285 */         int ICON_WIDTH = 16;
/* 286 */         int ICON_HEIGHT = 16;
/*     */         
/* 288 */         drawTexturedModalRect(left, top, effect.imageMapX, effect.imageMapY, 16, 16);
/*     */         
/* 290 */         double width = getStringWidth(remainingTime) / 2.0D;
/* 291 */         drawString(remainingTime, left + 8 - width, top + 10, -285212673);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 296 */   private static final ResourceLocation BARK_TEXTURES = new ResourceLocation("witchery", "textures/gui/creatures.png");
/*     */   
/*     */   private void drawBarkBeltCharges(EntityClientPlayerMP player, int beltCharges, ScaledResolution screen) {
/* 299 */     if ((beltCharges > 0) && (!player.field_71075_bZ.field_75098_d)) {
/* 300 */       Minecraft mc = Minecraft.func_71410_x();
/* 301 */       mc.func_110434_K().func_110577_a(BARK_TEXTURES);
/*     */       
/* 303 */       int tx = screen.func_78326_a() / 2 - 91;
/* 304 */       int par2 = screen.func_78328_b();
/* 305 */       int ty = par2 / 2;
/* 306 */       IAttributeInstance attributeinstance = mc.field_71439_g.func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 307 */       int i2 = par2 - 39;
/* 308 */       float f = (float)attributeinstance.func_111126_e();
/* 309 */       float f1 = mc.field_71439_g.func_110139_bj();
/* 310 */       int j2 = MathHelper.func_76123_f((f + f1) / 2.0F / 10.0F);
/* 311 */       int k2 = Math.max(10 - (j2 - 2), 3);
/* 312 */       int l2 = Witchery.modHooks.isTinkersPresent ? i2 - 10 : i2 - (j2 - 1) * k2 - 10;
/*     */       
/* 314 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 315 */       int iconOffsetX = 0;
/*     */       
/* 317 */       int ICON_WIDTH = 8;
/* 318 */       int ICON_HEIGHT = 8;
/* 319 */       int iconOffsetY = 248;
/* 320 */       for (int i = 0; i < beltCharges; i++) {
/* 321 */         drawTexturedModalRect(tx + i * 8, l2, 0, 248, 8, 8);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawBrewInSlot(ItemBrewBag.InventoryBrewBag inv, int slot, byte[] strokes, int x, int y, int fx, int fy, int align) {
/* 327 */     ItemStack brew = inv.func_70301_a(slot);
/* 328 */     if ((brew != null) && (
/* 329 */       (strokes.length == 0) || (strokes[0] == com.emoniph.witchery.infusion.infusions.symbols.StrokeSet.Stroke.INDEX_TO_STROKE[slot]))) {
/* 330 */       drawItem(x, y, brew);
/* 331 */       String s = brew.func_82833_r();
/* 332 */       if (s != null) {
/* 333 */         s.trim();
/* 334 */         double fontX = x + fx;
/* 335 */         double fontY = y + fy;
/* 336 */         if (align != 0) {
/* 337 */           double width = getStringWidth(s);
/* 338 */           if (align == 1) {
/* 339 */             fontX -= width / 2.0D;
/* 340 */           } else if (align == 2) {
/* 341 */             fontX -= width;
/*     */           }
/*     */         }
/*     */         
/* 345 */         drawString(s, fontX, fontY, 2013265919);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static FontRenderer getFontRenderer(ItemStack stack)
/*     */   {
/* 352 */     if ((stack != null) && (stack.func_77973_b() != null)) {
/* 353 */       FontRenderer f = stack.func_77973_b().getFontRenderer(stack);
/* 354 */       if (f != null)
/* 355 */         return f;
/*     */     }
/* 357 */     return Minecraft.func_71410_x().field_71466_p;
/*     */   }
/*     */   
/*     */   private static void drawItem(int i, int j, ItemStack itemstack) {
/* 361 */     drawItem(i, j, itemstack, getFontRenderer(itemstack));
/*     */   }
/*     */   
/* 364 */   private static RenderItem drawItems = new RenderItem();
/*     */   
/*     */   private static void drawItem(int i, int j, ItemStack itemstack, FontRenderer fontRenderer) {
/* 367 */     Minecraft mc = Minecraft.func_71410_x();
/* 368 */     GL11.glEnable(2896);
/* 369 */     GL11.glEnable(2929);
/* 370 */     drawItems.field_77023_b += 100.0F;
/*     */     try {
/* 372 */       drawItems.func_82406_b(fontRenderer, mc.field_71446_o, itemstack, i, j);
/* 373 */       drawItems.func_77021_b(fontRenderer, mc.field_71446_o, itemstack, i, j);
/*     */     }
/*     */     catch (Exception e) {}
/* 376 */     drawItems.field_77023_b -= 100.0F;
/* 377 */     GL11.glDisable(2896);
/* 378 */     GL11.glDisable(2929);
/*     */   }
/*     */   
/*     */ 
/*     */   public static void drawString(String s, double x, double y, int color)
/*     */   {
/* 384 */     RenderHelper.func_74518_a();
/* 385 */     RenderUtil.blend(true);
/* 386 */     RenderUtil.render2d(true);
/*     */     
/* 388 */     Minecraft.func_71410_x().field_71466_p.func_78261_a(s, (int)x, (int)y, color);
/*     */     
/* 390 */     RenderUtil.render2d(false);
/* 391 */     RenderUtil.blend(false);
/*     */   }
/*     */   
/*     */   public static double getStringWidth(String s)
/*     */   {
/* 396 */     GL11.glPushAttrib(262144);
/*     */     double val;
/* 398 */     try { val = Minecraft.func_71410_x().field_71466_p.func_78256_a(s);
/*     */     } finally {
/* 400 */       GL11.glPopAttrib();
/*     */     }
/* 402 */     return val;
/*     */   }
/*     */   
/* 405 */   private int lastY = 0;
/*     */   
/* 407 */   private static final int[] glyphOffsetX = { 0, 0, 1, -1, 1, -1, -1, 1 };
/* 408 */   private static final int[] glyphOffsetY = { -1, 1, 0, 0, -1, 1, -1, 1 };
/*     */   
/* 410 */   private static final ResourceLocation TEXTURE_GRID = new ResourceLocation("witchery", "textures/gui/grid.png");
/*     */   
/*     */   private static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 413 */     double zLevel = 0.0D;
/* 414 */     float f = 0.00390625F;
/* 415 */     float f1 = 0.00390625F;
/* 416 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 417 */     tessellator.func_78382_b();
/* 418 */     tessellator.func_78374_a(par1 + 0, par2 + par6, zLevel, (par3 + 0) * f, (par4 + par6) * f1);
/*     */     
/* 420 */     tessellator.func_78374_a(par1 + par5, par2 + par6, zLevel, (par3 + par5) * f, (par4 + par6) * f1);
/*     */     
/* 422 */     tessellator.func_78374_a(par1 + par5, par2 + 0, zLevel, (par3 + par5) * f, (par4 + 0) * f1);
/*     */     
/* 424 */     tessellator.func_78374_a(par1 + 0, par2 + 0, zLevel, (par3 + 0) * f, (par4 + 0) * f1);
/*     */     
/* 426 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/* 429 */   private Field fieldAccess = null;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onClientTick(TickEvent.ClientTickEvent event) {
/* 433 */     if (event.phase == TickEvent.Phase.START) {
/* 434 */       Minecraft minecraft = Minecraft.func_71410_x();
/* 435 */       EntityClientPlayerMP player = minecraft.field_71439_g;
/* 436 */       if (player != null)
/*     */       {
/* 438 */         boolean allowSpeedUp = true;
/* 439 */         int creaturePowerID = CreaturePower.getCreaturePowerID(player);
/* 440 */         if (creaturePowerID > 0) {
/* 441 */           CreaturePower power = CreaturePower.Registry.instance().get(creaturePowerID);
/* 442 */           if (power != null) {
/* 443 */             power.onUpdate(player.field_70170_p, player);
/* 444 */             allowSpeedUp = !(power instanceof CreaturePowerSpeed);
/*     */           }
/*     */         }
/*     */         
/* 448 */         if ((player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() != null) && (allowSpeedUp) && ((player.func_70694_bm().func_77973_b() == Witchery.Items.MYSTIC_BRANCH) || (player.func_70694_bm().func_77973_b() == Witchery.Items.BREW_BAG)) && (player.func_71039_bw())) {
/* 449 */           boolean canGo = (Math.abs(player.field_70159_w) <= 0.1D) && (Math.abs(player.field_70179_y) <= 0.1D);
/* 450 */           if (player.field_70170_p.func_147439_a(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u) - 2, MathHelper.func_76128_c(player.field_70161_v)) != net.minecraft.init.Blocks.field_150432_aD) {
/* 451 */             if (player.field_70122_E) {
/* 452 */               if (!player.func_70090_H()) {
/* 453 */                 if (canGo) {
/* 454 */                   player.field_70159_w *= 1.6500000476837158D;
/* 455 */                   player.field_70179_y *= 1.6500000476837158D;
/*     */                 }
/*     */               }
/* 458 */               else if (canGo) {
/* 459 */                 player.field_70159_w *= 1.100000023841858D;
/* 460 */                 player.field_70179_y *= 1.100000023841858D;
/*     */               }
/*     */               
/*     */             }
/*     */           }
/* 465 */           else if (canGo) {
/* 466 */             player.field_70159_w *= 1.100000023841858D;
/* 467 */             player.field_70179_y *= 1.100000023841858D;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 472 */         int sinkingCurseLevel = Infusion.getSinkingCurseLevel(player);
/* 473 */         if ((sinkingCurseLevel > 0) && (player.func_70090_H())) {
/* 474 */           if ((player.field_70181_x < -0.03D) && (!player.field_70122_E)) {
/* 475 */             player.field_70181_x *= (1.5D + Math.min(0.05D * (sinkingCurseLevel - 1), 0.2D));
/* 476 */           } else if ((player.field_70122_E) || (!player.func_70055_a(Material.field_151586_h)) || (player.field_70181_x <= 0.0D)) {}
/*     */ 
/*     */         }
/* 479 */         else if (sinkingCurseLevel > 0) {
/* 480 */           if ((!player.field_71075_bZ.field_75098_d) && (player.field_71075_bZ.field_75101_c) && (player.field_71075_bZ.field_75100_b)) {
/* 481 */             player.field_70181_x = -0.20000000298023224D;
/*     */           }
/*     */           
/*     */         }
/* 485 */         else if ((player.func_70644_a(Potion.field_76421_d)) && 
/* 486 */           (!player.field_71075_bZ.field_75098_d) && (player.field_71075_bZ.field_75101_c) && (player.field_71075_bZ.field_75100_b)) {
/* 487 */           PotionEffect effect = player.func_70660_b(Potion.field_76421_d);
/* 488 */           if ((effect != null) && (effect.func_76458_c() > 4)) {
/* 489 */             player.field_70181_x = -0.20000000298023224D;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 495 */         if ((sinkingCurseLevel == 0) && (BlockUtil.getBlockMaterial(player).func_76224_d()) && (player.func_82169_q(0) != null) && (player.func_82169_q(0).func_77973_b() == Witchery.Items.DEATH_FEET) && 
/* 496 */           (player.field_70181_x < 0.0D)) {
/* 497 */           player.field_70181_x += 0.1D;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 502 */         if ((player.field_70122_E) && (com.emoniph.witchery.util.KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74314_A))) {
/* 503 */           int x = MathHelper.func_76128_c(player.field_70165_t);
/* 504 */           int y = MathHelper.func_76128_c(player.field_70163_u);
/* 505 */           int z = MathHelper.func_76128_c(player.field_70161_v);
/* 506 */           if (player.field_70170_p.func_147439_a(x, y - 1, z) == Witchery.Blocks.LEAPING_LILY) {
/* 507 */             player.func_85030_a("random.bowhit", 1.0F, 0.4F / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */           }
/*     */         }
/*     */       }
/* 511 */     } else if (event.phase == TickEvent.Phase.END) {
/* 512 */       Minecraft minecraft = Minecraft.func_71410_x();
/* 513 */       EntityClientPlayerMP player = minecraft.field_71439_g;
/* 514 */       if ((player != null) && 
/* 515 */         (minecraft.field_71462_r != null) && ((minecraft.field_71462_r instanceof net.minecraft.client.renderer.InventoryEffectRenderer)) && 
/* 516 */         (player.field_71093_bK == Config.instance().dimensionDreamID) && (!player.field_71075_bZ.field_75098_d)) {
/* 517 */         if (this.fieldAccess == null) {
/*     */           try {
/* 519 */             Field[] fields = net.minecraft.client.gui.GuiScreen.class.getDeclaredFields();
/* 520 */             if (fields.length > 3) {
/* 521 */               if (fields[3].getType() == List.class) {
/* 522 */                 Field field = fields[3];
/* 523 */                 field.setAccessible(true);
/* 524 */                 this.fieldAccess = field;
/*     */               } else {
/* 526 */                 for (Field field : fields) {
/* 527 */                   if (field.getType() == List.class) {
/* 528 */                     field.setAccessible(true);
/* 529 */                     this.fieldAccess = field;
/* 530 */                     break;
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           } catch (Exception e) {
/* 536 */             Log.instance().debug(String.format("Exception occurred setting player gui. %s", new Object[] { e.toString() }));
/*     */           }
/*     */         }
/* 539 */         if (this.fieldAccess != null) {
/*     */           try {
/* 541 */             List list = (List)this.fieldAccess.get(minecraft.field_71462_r);
/* 542 */             if (list.size() > 0) {
/* 543 */               list.clear();
/*     */             }
/*     */           } catch (IllegalAccessException e) {
/* 546 */             Log.instance().warning(e, "Exception occurred setting player gui screen");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/PlayerRender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */