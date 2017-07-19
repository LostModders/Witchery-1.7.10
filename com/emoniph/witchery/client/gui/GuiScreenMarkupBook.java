/*     */ package com.emoniph.witchery.client.gui;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*     */ import com.emoniph.witchery.network.PacketSyncMarkupBook;
/*     */ import com.emoniph.witchery.util.NBT;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class GuiScreenMarkupBook extends GuiScreen
/*     */ {
/*  38 */   private static final ResourceLocation BACKGROUND = new ResourceLocation("witchery:textures/gui/bookSingle.png");
/*     */   
/*     */   private final EntityPlayer player;
/*     */   
/*     */   private final ItemStack itemstack;
/*     */   private final int meta;
/*     */   private int updateCount;
/*  45 */   private int bookImageWidth = 192;
/*  46 */   private int bookImageHeight = 192;
/*     */   
/*     */   private GuiButtonNavigate buttonTopPage;
/*     */   
/*     */   private GuiButtonNavigate buttonPreviousPage;
/*     */   
/*     */   private GuiButtonNavigate buttonNextPage;
/*  53 */   private final List<String> pageStack = new ArrayList();
/*     */   
/*     */   public GuiScreenMarkupBook(EntityPlayer player, ItemStack itemstack) {
/*  56 */     this.player = player;
/*  57 */     this.itemstack = itemstack;
/*  58 */     this.meta = (itemstack != null ? itemstack.func_77960_j() : 0);
/*     */     
/*  60 */     NBTTagList nbtPageStack = NBT.get(itemstack).func_150295_c("pageStack", 8);
/*  61 */     for (int i = 0; i < nbtPageStack.func_74745_c(); i++) {
/*  62 */       this.pageStack.add(nbtPageStack.func_150307_f(i));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_73876_c()
/*     */   {
/*  68 */     super.func_73876_c();
/*  69 */     this.updateCount += 1;
/*     */   }
/*     */   
/*     */   public void func_73866_w_()
/*     */   {
/*  74 */     Keyboard.enableRepeatEvents(true);
/*  75 */     constructPage();
/*     */   }
/*     */   
/*  78 */   final List<Element> elements = new ArrayList();
/*     */   private NextPage nextPage;
/*     */   
/*  81 */   private void constructPage() { String page = this.pageStack.size() > 0 ? (String)this.pageStack.get(this.pageStack.size() - 1) : "toc";
/*     */     
/*  83 */     this.field_146292_n.clear();
/*  84 */     this.elements.clear();
/*     */     
/*  86 */     byte b0 = 2;
/*  87 */     int mid = (this.field_146294_l - this.bookImageWidth) / 2;
/*  88 */     this.field_146292_n.add(this.buttonTopPage = new GuiButtonNavigate(1, mid + 120, b0 + 16, 2, BACKGROUND));
/*  89 */     this.field_146292_n.add(this.buttonPreviousPage = new GuiButtonNavigate(2, mid + 34, b0 + 16, 1, BACKGROUND));
/*  90 */     this.field_146292_n.add(this.buttonNextPage = new GuiButtonNavigate(3, mid + 120, b0 + 16, 0, BACKGROUND));
/*     */     
/*  92 */     String itemName = Item.field_150901_e.func_148750_c(this.itemstack.func_77973_b());
/*  93 */     String untranslated = itemName + "." + page;
/*  94 */     StringBuilder markup = new StringBuilder(StatCollector.func_74838_a(untranslated));
/*  95 */     if ((markup == null) || (markup.toString().equals(untranslated))) {
/*  96 */       return;
/*     */     }
/*     */     
/*  99 */     for (int i = 0; i < markup.length(); i++) {
/* 100 */       char c = markup.charAt(i);
/* 101 */       switch (c) {
/*     */       case '[': 
/* 103 */         this.elements.add(new Element(null));
/* 104 */         ((Element)this.elements.get(this.elements.size() - 1)).append(c);
/* 105 */         break;
/*     */       case ']': 
/* 107 */         Element e = (Element)this.elements.get(this.elements.size() - 1);
/* 108 */         if (e.tag.toString().equals("template")) {
/* 109 */           String templatePathRoot = Item.field_150901_e.func_148750_c(this.itemstack.func_77973_b());
/* 110 */           String templatePath = templatePathRoot + "." + e.attribute;
/* 111 */           String template = StatCollector.func_74838_a(templatePath);
/* 112 */           if (!template.isEmpty()) {
/* 113 */             String[] parms = e.text.toString().split("\\s");
/* 114 */             Object[] components = new Object[parms.length];
/* 115 */             for (int j = 0; j < parms.length; j++) {
/* 116 */               String[] kv = parms[j].split("=");
/* 117 */               if (kv.length == 2) {
/* 118 */                 if (kv[0].matches("stack\\|\\d+")) {
/* 119 */                   StringBuilder stackList = new StringBuilder();
/* 120 */                   for (String stack : kv[1].split(",")) {
/* 121 */                     stackList.append(String.format("[stack=%s]", new Object[] { stack }));
/*     */                   }
/* 123 */                   int index = Math.min(Integer.parseInt(kv[0].substring(kv[0].indexOf('|') + 1)), components.length - 1);
/*     */                   
/*     */ 
/* 126 */                   components[index] = stackList.toString();
/* 127 */                 } else if (kv[0].matches("\\d+")) {
/* 128 */                   int index = Math.min(Integer.parseInt(kv[0]), components.length - 1);
/* 129 */                   components[index] = kv[1];
/*     */                 }
/*     */               }
/*     */             }
/* 133 */             markup.insert(i + 1, String.format(template, components));
/*     */             
/* 135 */             this.elements.remove(this.elements.size() - 1);
/*     */           }
/*     */         }
/*     */         
/* 139 */         this.elements.add(new Element(null));
/* 140 */         break;
/*     */       default: 
/* 142 */         if (this.elements.size() == 0) {
/* 143 */           this.elements.add(new Element(null));
/*     */         }
/* 145 */         ((Element)this.elements.get(this.elements.size() - 1)).append(c);
/*     */       }
/*     */       
/*     */     }
/*     */     
/* 150 */     this.nextPage = null;
/* 151 */     for (Element element : this.elements) {
/* 152 */       NextPage defaultNextPage = element.constructButtons(this.field_146292_n, this.itemstack);
/* 153 */       if (defaultNextPage != null) {
/* 154 */         this.nextPage = defaultNextPage;
/*     */       }
/*     */     }
/*     */     
/* 158 */     updateButtons();
/*     */   }
/*     */   
/*     */   private static class NextPage {
/*     */     public final String pageName;
/*     */     public final boolean visible;
/*     */     
/* 165 */     public NextPage(String attrib, ItemStack book) { int pipeIndex = attrib.indexOf('|');
/* 166 */       if (pipeIndex != -1) {
/* 167 */         this.pageName = attrib.substring(0, pipeIndex);
/* 168 */         this.visible = (book.func_77960_j() >= Integer.parseInt(attrib.substring(pipeIndex + 1)));
/*     */       } else {
/* 170 */         this.pageName = attrib;
/* 171 */         this.visible = true;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_146281_b()
/*     */   {
/* 179 */     Keyboard.enableRepeatEvents(false);
/* 180 */     sendBookToServer();
/*     */   }
/*     */   
/*     */   private void updateButtons() {
/* 184 */     this.buttonNextPage.field_146125_m = ((this.nextPage != null) && (this.nextPage.visible));
/* 185 */     this.buttonPreviousPage.field_146125_m = (this.pageStack.size() > 0);
/* 186 */     this.buttonTopPage.field_146125_m = (this.pageStack.size() > 0);
/*     */   }
/*     */   
/*     */   private void sendBookToServer() {
/* 190 */     if (this.player != null) {
/* 191 */       Witchery.packetPipeline.sendToServer(new PacketSyncMarkupBook(this.player.field_71071_by.field_70461_c, this.pageStack));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_146284_a(GuiButton button)
/*     */   {
/* 198 */     if (button.field_146124_l) {
/* 199 */       if (button.field_146127_k == 0) {
/* 200 */         this.field_146297_k.func_147108_a((GuiScreen)null);
/*     */       }
/* 202 */       else if (button.field_146127_k == 1) {
/* 203 */         if (this.pageStack.size() > 0) {
/* 204 */           this.pageStack.remove(this.pageStack.size() - 1);
/* 205 */           for (int i = this.pageStack.size() - 1; i >= 0; i--) {
/* 206 */             if (((String)this.pageStack.get(i)).startsWith("toc/")) {
/*     */               break;
/*     */             }
/* 209 */             this.pageStack.remove(i);
/*     */           }
/*     */         }
/*     */         
/* 213 */         constructPage();
/* 214 */       } else if (button.field_146127_k == 2) {
/* 215 */         if (this.pageStack.size() > 0) {
/* 216 */           this.pageStack.remove(this.pageStack.size() - 1);
/* 217 */           constructPage();
/*     */         }
/* 219 */       } else if (button.field_146127_k == 3)
/*     */       {
/* 221 */         this.pageStack.add(this.nextPage.pageName);
/* 222 */         constructPage();
/*     */       }
/* 224 */       else if (button.field_146127_k == 4) {
/* 225 */         this.pageStack.add(((GuiButtonUrl)button).nextPage);
/* 226 */         constructPage();
/*     */       }
/*     */       
/* 229 */       updateButtons();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_73869_a(char par1, int par2)
/*     */   {
/* 235 */     super.func_73869_a(par1, par2);
/*     */   }
/*     */   
/*     */   private static class Element {
/*     */     private static enum Capture {
/* 240 */       TAG,  ATTRIB,  TEXT;
/*     */       
/*     */       private Capture() {} }
/* 243 */     private final StringBuilder tag = new StringBuilder();
/* 244 */     private final StringBuilder attribute = new StringBuilder();
/* 245 */     private final StringBuilder text = new StringBuilder();
/*     */     
/* 247 */     private Capture capture = Capture.TEXT;
/*     */     private static final String FORMAT_CHAR = "§";
/*     */     private static final String FORMAT_CLEAR = "§r";
/*     */     
/* 251 */     public String toString() { return String.format("tag=%s attribute=%s text=%s", new Object[] { this.tag, this.attribute, this.text }); }
/*     */     
/*     */     public void append(char c)
/*     */     {
/* 255 */       switch (c) {
/*     */       case '[': 
/* 257 */         this.capture = Capture.TAG;
/* 258 */         break;
/*     */       case '=': 
/* 260 */         if (this.capture == Capture.TAG)
/* 261 */           this.capture = Capture.ATTRIB;
/* 262 */         break;
/*     */       
/*     */       case '\t': 
/*     */       case ' ': 
/* 266 */         if ((this.capture == Capture.TAG) || (this.capture == Capture.ATTRIB))
/* 267 */           this.capture = Capture.TEXT;
/* 268 */         break;
/*     */       }
/*     */       
/* 271 */       if (this.capture == Capture.TAG) {
/* 272 */         this.tag.append(c);
/* 273 */       } else if (this.capture == Capture.ATTRIB) {
/* 274 */         this.attribute.append(c);
/*     */       } else {
/* 276 */         this.text.append(c);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 285 */     private static final Hashtable<String, String> FORMATS = ;
/*     */     private GuiButtonUrl button;
/*     */     
/* 288 */     private static Hashtable<String, String> getFormats() { Hashtable<String, String> formats = new Hashtable();
/* 289 */       formats.put("black", "§0");
/* 290 */       formats.put("darkblue", "§1");
/* 291 */       formats.put("darkgreen", "§2");
/* 292 */       formats.put("darkaqua", "§3");
/* 293 */       formats.put("darkred", "§4");
/* 294 */       formats.put("darkpurple", "§5");
/* 295 */       formats.put("darkyellow", "§6");
/* 296 */       formats.put("gray", "§7");
/* 297 */       formats.put("darkgray", "§8");
/* 298 */       formats.put("blue", "§9");
/* 299 */       formats.put("green", "§a");
/* 300 */       formats.put("aqua", "§b");
/* 301 */       formats.put("red", "§c");
/* 302 */       formats.put("purple", "§d");
/* 303 */       formats.put("yellow", "§e");
/* 304 */       formats.put("white", "§f");
/* 305 */       formats.put("b", "§l");
/* 306 */       formats.put("s", "§m");
/* 307 */       formats.put("u", "§n");
/* 308 */       formats.put("i", "§o");
/* 309 */       formats.put("h1", "§3§o");
/* 310 */       return formats;
/*     */     }
/*     */     
/*     */ 
/*     */     public GuiScreenMarkupBook.NextPage constructButtons(List buttonList, ItemStack stack)
/*     */     {
/* 316 */       String tag = this.tag.toString();
/* 317 */       if (tag.equals("url")) {
/* 318 */         String attrib = this.attribute.toString();
/* 319 */         int pipeIndex = attrib.indexOf('|');
/* 320 */         if (pipeIndex != -1) {
/* 321 */           attrib = attrib.substring(0, pipeIndex);
/*     */         }
/* 323 */         this.button = new GuiButtonUrl(4, 0, 0, attrib, this.text.toString());
/* 324 */         buttonList.add(this.button);
/* 325 */       } else if (tag.equals("next")) {
/* 326 */         return new GuiScreenMarkupBook.NextPage(this.attribute.toString(), stack);
/*     */       }
/* 328 */       return null;
/*     */     }
/*     */     
/*     */     public void draw(int[] pos, int marginX, int maxWidth, GuiScreenMarkupBook.RenderState state)
/*     */     {
/* 333 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 335 */       String tag = this.tag.toString();
/* 336 */       if (tag.equals("br")) {
/* 337 */         state.newline(pos);
/* 338 */         return;
/*     */       }
/*     */       
/* 341 */       if (tag.equals("tab")) {
/* 342 */         int TAB_SPACE = 10;
/* 343 */         if (pos[0] + 10 > maxWidth) {
/* 344 */           state.newline(pos);
/*     */         } else {
/* 346 */           pos[0] += 10;
/*     */         }
/* 348 */         return;
/*     */       }
/*     */       
/* 351 */       if (tag.equals("img"))
/*     */       {
/* 353 */         String[] parms = this.attribute.toString().split("\\|");
/* 354 */         int defaultWidth = 32;
/* 355 */         String url = parms.length > 0 ? parms[0] : "";
/* 356 */         String halign = parms.length > 1 ? parms[1] : "left";
/* 357 */         String valign = parms.length > 2 ? parms[2] : "top";
/* 358 */         int width = parms.length > 3 ? parseInt(parms[3], 32) : 32;
/* 359 */         int height = parms.length > 4 ? parseInt(parms[4], width) : width;
/*     */         
/* 361 */         if (!url.isEmpty()) {
/* 362 */           ResourceLocation location = new ResourceLocation(url);
/* 363 */           Minecraft.func_71410_x().func_110434_K().func_110577_a(location);
/*     */           
/* 365 */           if (halign.equals("right")) {
/* 366 */             pos[0] = (maxWidth - width);
/* 367 */           } else if (halign.equals("center")) {
/* 368 */             pos[0] = (maxWidth / 2 - width / 2);
/*     */           }
/*     */           
/* 371 */           if (pos[0] + width > maxWidth) {
/* 372 */             state.newline(pos);
/*     */           }
/*     */           
/* 375 */           int y = pos[1];
/* 376 */           if (state.lineheight > height) {
/* 377 */             if (valign.equals("bottom")) {
/* 378 */               y += state.lineheight - height;
/* 379 */             } else if (valign.equals("middle")) {
/* 380 */               y += state.lineheight / 2 - height / 2;
/*     */             }
/*     */           }
/*     */           
/* 384 */           drawTexturedQuadFit(pos[0] + marginX, y, width, height, state.zLevel);
/*     */           
/* 386 */           pos[0] += width;
/* 387 */           state.adjustLineHeight(height);
/*     */         }
/* 389 */         return;
/*     */       }
/*     */       
/* 392 */       if (tag.equals("url"))
/*     */       {
/* 394 */         this.button.field_146121_g = state.font.field_78288_b;
/* 395 */         this.button.field_146120_f = state.font.func_78256_a(this.text.toString());
/* 396 */         if (pos[0] + this.button.field_146120_f > maxWidth) {
/* 397 */           state.newline(pos);
/*     */         }
/* 399 */         String[] parms = this.attribute.toString().split("\\|");
/* 400 */         String url = parms.length > 0 ? parms[0] : "";
/* 401 */         String valign = parms.length > 1 ? parms[1] : "top";
/* 402 */         this.button.field_146128_h = (pos[0] + marginX);
/* 403 */         int y = pos[1];
/* 404 */         if (state.lineheight > this.button.field_146121_g) {
/* 405 */           if (valign.equals("bottom")) {
/* 406 */             y += state.lineheight - this.button.field_146121_g;
/* 407 */           } else if (valign.equals("middle")) {
/* 408 */             y += state.lineheight / 2 - this.button.field_146121_g / 2;
/*     */           }
/*     */         }
/* 411 */         this.button.field_146129_i = y;
/* 412 */         pos[0] += this.button.field_146120_f;
/* 413 */         return;
/*     */       }
/*     */       
/* 416 */       if (tag.equals("locked")) {
/* 417 */         return;
/*     */       }
/*     */       
/* 420 */       if (tag.equals("stack")) {
/* 421 */         String[] parms = this.attribute.toString().split("\\|");
/*     */         
/* 423 */         String name = parms.length > 0 ? parms[0] : "";
/* 424 */         int damage = 0;
/* 425 */         int size = 1;
/* 426 */         int offset = 1;
/* 427 */         if ((parms.length > offset) && (parms[offset].matches("\\d+"))) {
/* 428 */           damage = parseInt(parms[offset], 0);
/* 429 */           offset++;
/*     */         }
/* 431 */         if ((parms.length > offset) && (parms[offset].matches("\\d+"))) {
/* 432 */           size = parseInt(parms[offset], 1);
/* 433 */           offset++;
/*     */         }
/* 435 */         String halign = parms.length > offset ? parms[offset] : "left";
/* 436 */         offset++;
/* 437 */         String valign = parms.length > offset ? parms[offset] : "top";
/*     */         
/* 439 */         if (!name.isEmpty()) {
/* 440 */           boolean empty = name.equals("empty");
/* 441 */           Item item = !empty ? (Item)Item.field_150901_e.func_82594_a(name) : null;
/* 442 */           ItemStack stack = !empty ? new ItemStack(item, size, damage) : null;
/* 443 */           int width = 18;
/* 444 */           int height = 18;
/*     */           
/* 446 */           if (halign.equals("right")) {
/* 447 */             pos[0] = (maxWidth - width);
/* 448 */           } else if (halign.equals("center")) {
/* 449 */             pos[0] = (maxWidth / 2 - width / 2);
/*     */           }
/*     */           
/* 452 */           if (pos[0] + width > maxWidth) {
/* 453 */             state.newline(pos);
/*     */           }
/*     */           
/* 456 */           int y = pos[1];
/* 457 */           if (state.lineheight > height) {
/* 458 */             if (valign.equals("bottom")) {
/* 459 */               y += state.lineheight - height;
/* 460 */             } else if (valign.equals("middle")) {
/* 461 */               y += state.lineheight / 2 - height / 2;
/*     */             }
/*     */           }
/*     */           
/* 465 */           if (!empty) {
/* 466 */             RenderItem render = new RenderItem();
/* 467 */             GL11.glPushMatrix();
/* 468 */             GL11.glEnable(3042);
/* 469 */             GL11.glBlendFunc(770, 771);
/* 470 */             RenderHelper.func_74520_c();
/* 471 */             GL11.glEnable(32826);
/* 472 */             GL11.glEnable(2929);
/* 473 */             int x = pos[0] + marginX;
/*     */             
/* 475 */             render.func_82406_b(state.font, Minecraft.func_71410_x().func_110434_K(), stack, x, y);
/*     */             
/* 477 */             render.func_77021_b(state.font, Minecraft.func_71410_x().func_110434_K(), stack, x, y);
/*     */             
/* 479 */             RenderHelper.func_74518_a();
/* 480 */             GL11.glPopMatrix();
/*     */             
/* 482 */             if ((state.mouseX >= x) && (state.mouseY >= y) && (state.mouseX <= x + width) && (state.mouseY <= y + height))
/*     */             {
/* 484 */               state.tooltipStack = stack;
/*     */             }
/* 486 */             GL11.glDisable(2896);
/*     */           }
/*     */           
/* 489 */           pos[0] += width;
/* 490 */           state.adjustLineHeight(height);
/*     */           
/* 492 */           String[] words = this.text.toString().split("(?<=\\s)");
/* 493 */           for (String word : words) {
/* 494 */             int textWidth = state.font.func_78256_a(word);
/* 495 */             if (pos[0] + textWidth > maxWidth) {
/* 496 */               state.newline(pos);
/* 497 */               y = pos[1];
/*     */             }
/* 499 */             state.font.func_78276_b(word, marginX + pos[0], y + (height - state.font.field_78288_b) / 2, 0);
/*     */             
/* 501 */             pos[0] += textWidth;
/*     */           }
/*     */         }
/* 504 */         return;
/*     */       }
/*     */       
/* 507 */       if (tag.equals("next"))
/*     */       {
/* 509 */         return;
/*     */       }
/*     */       
/* 512 */       String preText = FORMATS.containsKey(tag) ? (String)FORMATS.get(tag) : "";
/* 513 */       String postText = FORMATS.containsKey(tag) ? "§r" : "";
/*     */       
/* 515 */       String[] words = this.text.toString().split("(?<=\\s)");
/* 516 */       for (String word : words) {
/* 517 */         int width = state.font.func_78256_a(word);
/* 518 */         if (pos[0] + width > maxWidth) {
/* 519 */           state.newline(pos);
/*     */         }
/* 521 */         if ((pos[0] != 0) || (!word.trim().isEmpty()))
/*     */         {
/*     */ 
/* 524 */           state.font.func_78276_b(preText + word + postText, marginX + pos[0], pos[1], 0);
/* 525 */           pos[0] += width;
/*     */         }
/*     */       }
/* 528 */       if (tag.equals("h1")) {
/* 529 */         state.adjustLineHeight((int)Math.ceil(state.lineheight * 1.5F));
/* 530 */         state.newline(pos);
/*     */       }
/*     */     }
/*     */     
/*     */     private int parseInt(String text, int defaultValue) {
/*     */       try {
/* 536 */         return Integer.parseInt(text);
/*     */       } catch (NumberFormatException ex) {}
/* 538 */       return defaultValue;
/*     */     }
/*     */     
/*     */     public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel)
/*     */     {
/* 543 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 544 */       tessellator.func_78382_b();
/* 545 */       tessellator.func_78374_a(x + 0.0D, y + height, zLevel, 0.0D, 1.0D);
/* 546 */       tessellator.func_78374_a(x + width, y + height, zLevel, 1.0D, 1.0D);
/* 547 */       tessellator.func_78374_a(x + width, y + 0.0D, zLevel, 1.0D, 0.0D);
/* 548 */       tessellator.func_78374_a(x + 0.0D, y + 0.0D, zLevel, 0.0D, 0.0D);
/* 549 */       tessellator.func_78381_a();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class RenderState {
/*     */     final FontRenderer font;
/*     */     final float zLevel;
/*     */     final int mouseX;
/*     */     final int mouseY;
/*     */     ItemStack tooltipStack;
/*     */     int lineheight;
/*     */     
/*     */     public RenderState(FontRenderer font, float zLevel, int mouseX, int mouseY) {
/* 562 */       this.font = font;
/* 563 */       this.zLevel = zLevel;
/* 564 */       this.mouseX = mouseX;
/* 565 */       this.mouseY = mouseY;
/* 566 */       this.lineheight = font.field_78288_b;
/*     */     }
/*     */     
/*     */     public void newline(int[] pos) {
/* 570 */       pos[0] = 0;
/* 571 */       pos[1] += this.lineheight + 1;
/* 572 */       this.lineheight = this.font.field_78288_b;
/*     */     }
/*     */     
/*     */     public void adjustLineHeight(int newHeight) {
/* 576 */       if (newHeight > this.lineheight) {
/* 577 */         this.lineheight = newHeight;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float par3)
/*     */   {
/* 584 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 586 */     this.field_146297_k.func_110434_K().func_110577_a(BACKGROUND);
/* 587 */     int k = (this.field_146294_l - this.bookImageWidth) / 2;
/* 588 */     byte b0 = 2;
/* 589 */     func_73729_b(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
/*     */     
/* 591 */     int maxWidth = 116;
/* 592 */     int marginX = k + 36;
/*     */     
/* 594 */     this.buttonPreviousPage.field_146128_h = marginX;
/* 595 */     this.buttonPreviousPage.field_146129_i = 16;
/*     */     
/* 597 */     this.buttonTopPage.field_146128_h = (k + this.bookImageWidth / 2 - this.buttonTopPage.field_146120_f / 2 - 4);
/* 598 */     this.buttonTopPage.field_146129_i = 16;
/*     */     
/* 600 */     this.buttonNextPage.field_146128_h = (k + this.bookImageWidth - this.buttonNextPage.field_146120_f - 44);
/* 601 */     this.buttonNextPage.field_146129_i = 16;
/*     */     
/* 603 */     int[] pos = { 0, 32 };
/*     */     
/* 605 */     RenderState state = new RenderState(this.field_146289_q, this.field_73735_i, mouseX, mouseY);
/* 606 */     for (Element element : this.elements) {
/* 607 */       element.draw(pos, marginX, 116, state);
/*     */     }
/*     */     
/* 610 */     super.func_73863_a(mouseX, mouseY, par3);
/*     */     
/* 612 */     if (state.tooltipStack != null)
/*     */     {
/* 614 */       func_146285_a(state.tooltipStack, mouseX, mouseY + 16);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_146285_a(ItemStack stack, int x, int y)
/*     */   {
/* 621 */     List list = stack.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/* 622 */     if (list != null) {
/* 623 */       int power = WitcheryBrewRegistry.INSTANCE.getAltarPower(stack);
/* 624 */       if (power >= 0) {
/* 625 */         list.add(String.format(Witchery.resource("witchery.brewing.ingredientpowercost"), new Object[] { Integer.valueOf(power), Integer.valueOf(MathHelper.func_76143_f(1.4D * power)) }));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 630 */     for (int k = 0; k < list.size(); k++) {
/* 631 */       if (k == 0) {
/* 632 */         list.set(k, stack.func_77953_t().field_77937_e + (String)list.get(k));
/*     */       } else {
/* 634 */         list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
/*     */       }
/*     */     }
/*     */     
/* 638 */     FontRenderer font = stack.func_77973_b().getFontRenderer(stack);
/* 639 */     drawHoveringText(list, x, y, font == null ? this.field_146289_q : font);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/gui/GuiScreenMarkupBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */