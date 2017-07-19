/*     */ package com.emoniph.witchery.client.gui;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.crafting.BrazierRecipes;
/*     */ import com.emoniph.witchery.crafting.BrazierRecipes.BrazierRecipe;
/*     */ import com.emoniph.witchery.crafting.DistilleryRecipes;
/*     */ import com.emoniph.witchery.crafting.DistilleryRecipes.DistilleryRecipe;
/*     */ import com.emoniph.witchery.crafting.KettleRecipes;
/*     */ import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
/*     */ import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.ritual.RiteRegistry;
/*     */ import com.emoniph.witchery.ritual.RiteRegistry.Ritual;
/*     */ import com.emoniph.witchery.util.Const;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class GuiScreenWitchcraftBook extends GuiScreen
/*     */ {
/*  40 */   private static final ResourceLocation field_110405_a = new ResourceLocation("textures/gui/book.png");
/*  41 */   public static final ResourceLocation DOUBLE_BOOK_TEXTURE = new ResourceLocation("witchery", "textures/gui/bookDouble.png");
/*     */   
/*  43 */   private static final ResourceLocation[] field_110405_b = { new ResourceLocation("witchery", "textures/gui/circle_white_large.png"), new ResourceLocation("witchery", "textures/gui/circle_blue_large.png"), new ResourceLocation("witchery", "textures/gui/circle_red_large.png"), new ResourceLocation("witchery", "textures/gui/circle_white_medium.png"), new ResourceLocation("witchery", "textures/gui/circle_blue_medium.png"), new ResourceLocation("witchery", "textures/gui/circle_red_medium.png"), new ResourceLocation("witchery", "textures/gui/circle_white_small.png"), new ResourceLocation("witchery", "textures/gui/circle_blue_small.png"), new ResourceLocation("witchery", "textures/gui/circle_red_small.png") };
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
/*  55 */   private static final String[] sizes = { "§715x15§0", "§515x15§0", "§415x15§0", "§711x11§0", "§511x11§0", "§411x11§0", "§77x7§0", "§57x7§0", "§47x7§0" };
/*     */   
/*     */   private final EntityPlayer player;
/*     */   
/*     */   private final ItemStack itemstack;
/*     */   
/*     */   private int updateCount;
/*  62 */   private int bookImageWidth = 192;
/*  63 */   private int bookImageHeight = 192;
/*  64 */   private int bookTotalPages = 1;
/*     */   private int currPage;
/*     */   private NBTTagList bookPages;
/*  67 */   private String bookTitle = "";
/*     */   private GuiButtonNextPage buttonNextPage;
/*     */   private GuiButtonNextPage buttonPreviousPage;
/*     */   private GuiButton buttonDone;
/*     */   private GuiButtonJumpPage buttonJumpPage1;
/*     */   private GuiButtonJumpPage buttonJumpPage2;
/*     */   private GuiButtonJumpPage buttonJumpPage3;
/*     */   private GuiButtonJumpPage buttonJumpPage4;
/*     */   private GuiButtonJumpPage buttonJumpPage5;
/*     */   private GuiButtonJumpPage buttonJumpPage6;
/*     */   private GuiButtonJumpPage buttonJumpPage7;
/*     */   private static final String CURRENT_PAGE_KEY = "CurrentPage";
/*     */   
/*     */   public GuiScreenWitchcraftBook(EntityPlayer player, ItemStack itemstack) {
/*  81 */     this.player = player;
/*  82 */     this.itemstack = itemstack;
/*     */     
/*  84 */     this.bookTitle = itemstack.func_82833_r();
/*     */     
/*     */ 
/*  87 */     this.bookPages = new NBTTagList();
/*  88 */     NBTTagCompound compound; if (Witchery.Items.GENERIC.itemBookOven.isMatch(itemstack)) {
/*  89 */       NBTTagCompound compound = new NBTTagCompound();
/*  90 */       compound.func_74778_a("Summary", Witchery.resource("witchery.book.oven1"));
/*  91 */       this.bookPages.func_74742_a(compound);
/*     */       
/*  93 */       compound = new NBTTagCompound();
/*  94 */       compound.func_74778_a("Summary", Witchery.resource("witchery.book.oven2"));
/*  95 */       this.bookPages.func_74742_a(compound);
/*     */       
/*  97 */       compound = new NBTTagCompound();
/*  98 */       compound.func_74778_a("Summary", Witchery.resource("witchery.book.oven3"));
/*  99 */       this.bookPages.func_74742_a(compound); } else { NBTTagCompound compound;
/* 100 */       if (Witchery.Items.GENERIC.itemBookDistilling.isMatch(itemstack))
/*     */       {
/* 102 */         compound = new NBTTagCompound();
/* 103 */         String intro = Witchery.resource("witchery.book.distillery1");
/* 104 */         compound.func_74778_a("Summary", intro);
/* 105 */         this.bookPages.func_74742_a(compound);
/*     */         
/* 107 */         for (DistilleryRecipes.DistilleryRecipe recipe : DistilleryRecipes.instance().recipes) {
/* 108 */           compound = new NBTTagCompound();
/* 109 */           compound.func_74778_a("Summary", recipe.getDescription());
/* 110 */           this.bookPages.func_74742_a(compound); } } else { NBTTagCompound compound;
/*     */         String anyCircles;
/* 112 */         if (Witchery.Items.GENERIC.itemBookCircleMagic.isMatch(itemstack))
/*     */         {
/* 114 */           compound = new NBTTagCompound();
/* 115 */           String intro = Witchery.resource("witchery.book.rites1");
/* 116 */           String intro2 = Witchery.resource("witchery.book.rites2");
/* 117 */           anyCircles = Witchery.resource("witchery.book.rites.anycircle");
/* 118 */           compound.func_74778_a("Summary", intro);
/* 119 */           compound.func_74778_a("Summary2", intro2);
/* 120 */           compound.func_74773_a("Circles", new byte[] { 0, 3, 6 });
/* 121 */           this.bookPages.func_74742_a(compound);
/*     */           
/*     */ 
/* 124 */           for (RiteRegistry.Ritual ritual : RiteRegistry.instance().getSortedRituals())
/* 125 */             if (ritual.showInBook()) {
/* 126 */               compound = new NBTTagCompound();
/* 127 */               compound.func_74778_a("Summary", ritual.getDescription());
/* 128 */               byte[] circles = ritual.getCircles();
/* 129 */               compound.func_74773_a("Circles", circles);
/* 130 */               if (circles.length == 0) {
/* 131 */                 compound.func_74778_a("Summary2", anyCircles);
/*     */               } else {
/* 133 */                 StringBuilder sb = new StringBuilder();
/* 134 */                 for (byte cir : circles) {
/* 135 */                   if (sb.length() > 0) {
/* 136 */                     sb.append(", ");
/*     */                   }
/* 138 */                   sb.append(sizes[cir]);
/*     */                 }
/* 140 */                 compound.func_74778_a("Summary2", sb.toString());
/*     */               }
/* 142 */               this.bookPages.func_74742_a(compound);
/*     */             }
/*     */         } else { NBTTagCompound compound;
/* 145 */           if (Witchery.Items.GENERIC.itemBookInfusions.isMatch(itemstack))
/*     */           {
/* 147 */             compound = new NBTTagCompound();
/* 148 */             String intro = Witchery.resource("witchery.book.brews1");
/* 149 */             compound.func_74778_a("Summary", intro);
/* 150 */             this.bookPages.func_74742_a(compound);
/*     */             
/* 152 */             for (KettleRecipes.KettleRecipe recipe : KettleRecipes.instance().recipes)
/* 153 */               if (recipe.inBook) {
/* 154 */                 compound = new NBTTagCompound();
/* 155 */                 compound.func_74778_a("Summary", recipe.getDescription());
/* 156 */                 this.bookPages.func_74742_a(compound);
/*     */               }
/*     */           } else { NBTTagCompound compound;
/* 159 */             if (Witchery.Items.GENERIC.itemBookBurning.isMatch(itemstack))
/*     */             {
/* 161 */               compound = new NBTTagCompound();
/* 162 */               String intro = Witchery.resource("witchery.book.burning1");
/* 163 */               compound.func_74778_a("Summary", intro);
/* 164 */               this.bookPages.func_74742_a(compound);
/*     */               
/* 166 */               for (BrazierRecipes.BrazierRecipe recipe : BrazierRecipes.instance().recipes) {
/* 167 */                 if (recipe.inBook) {
/* 168 */                   compound = new NBTTagCompound();
/* 169 */                   compound.func_74778_a("Summary", recipe.getDescription());
/* 170 */                   this.bookPages.func_74742_a(compound);
/*     */                 }
/*     */               }
/*     */               
/* 174 */               compound = new NBTTagCompound();
/* 175 */               String intro2 = Witchery.resource("witchery.book.burning2");
/* 176 */               compound.func_74778_a("Summary", intro2);
/* 177 */               this.bookPages.func_74742_a(compound);
/*     */               
/* 179 */               for (InfusedSpiritEffect effect : InfusedSpiritEffect.effectList) {
/* 180 */                 if ((effect != null) && (effect.isInBook())) {
/* 181 */                   compound = new NBTTagCompound();
/* 182 */                   compound.func_74778_a("Summary", effect.getDescription());
/* 183 */                   this.bookPages.func_74742_a(compound);
/*     */                 }
/*     */               }
/*     */             }
/* 187 */             else if (Witchery.Items.GENERIC.itemBookHerbology.isMatch(itemstack))
/*     */             {
/* 189 */               NBTTagCompound compound = new NBTTagCompound();
/* 190 */               String intro = Witchery.resource("witchery.book.herbology1");
/* 191 */               compound.func_74778_a("Summary", intro);
/* 192 */               this.bookPages.func_74742_a(compound);
/*     */               
/* 194 */               addPlantPage(Witchery.Blocks.CROP_BELLADONNA, "witchery.book.herbology.belladonna", "witchery:textures/blocks/belladonna_stage_4.png");
/* 195 */               addPlantPage(Witchery.Blocks.EMBER_MOSS, "witchery.book.herbology.embermoss", "witchery:textures/blocks/embermoss.png");
/* 196 */               addPlantPage(Witchery.Blocks.GLINT_WEED, "witchery.book.herbology.glintweed", "witchery:textures/blocks/glintWeed.png");
/* 197 */               addPlantPage(Witchery.Blocks.CROP_MANDRAKE, "witchery.book.herbology.mandrake", "witchery:textures/blocks/mandrake_stage_4.png");
/* 198 */               addPlantPage(Witchery.Blocks.CROP_SNOWBELL, "witchery.book.herbology.snowbell", "witchery:textures/blocks/snowbell_stage_4.png");
/* 199 */               addPlantPage(Witchery.Blocks.SPANISH_MOSS, "witchery.book.herbology.spanishmoss", "witchery:textures/blocks/spanishMoss.png");
/* 200 */               addPlantPage(new ItemStack(Witchery.Blocks.BRAMBLE, 1, 1), "witchery.book.herbology.wildbramble", "witchery:textures/blocks/bramble_wild.png");
/* 201 */               addPlantPage(new ItemStack(Witchery.Blocks.BRAMBLE, 1, 0), "witchery.book.herbology.enderbramble", "witchery:textures/blocks/bramble_ender.png");
/* 202 */               addPlantPage(Witchery.Blocks.VOID_BRAMBLE, "witchery.book.herbology.voidbramble", "witchery:textures/blocks/voidBramble.png");
/* 203 */               addPlantPage(Witchery.Blocks.CROP_ARTICHOKE, "witchery.book.herbology.artichoke", "witchery:textures/blocks/artichoke_stage_4.png");
/* 204 */               addPlantPage(Witchery.Blocks.GRASSPER, "witchery.book.herbology.grassper", "witchery:textures/blocks/grassperIcon.png");
/* 205 */               addPlantPage(Witchery.Blocks.CRITTER_SNARE, "witchery.book.herbology.crittersnare", "witchery:textures/blocks/critterSnare_empty.png");
/* 206 */               addPlantPage(Witchery.Blocks.BLOOD_ROSE, "witchery.book.herbology.bloodrose", "witchery:textures/blocks/bloodrose.png");
/* 207 */               addPlantPage(Witchery.Blocks.WISPY_COTTON, "witchery.book.herbology.somniancotton", "witchery:textures/blocks/somnianCotton.png");
/* 208 */               addPlantPage(Witchery.Blocks.CROP_WOLFSBANE, "witchery.book.herbology.wolfsbane", "witchery:textures/blocks/wolfsbane_stage_7.png");
/* 209 */               addPlantPage(Witchery.Blocks.CROP_GARLIC, "witchery.book.herbology.garlic", "witchery:textures/blocks/garlic_stage_5.png");
/*     */               
/* 211 */               addPlantPage(new ItemStack(Witchery.Blocks.SAPLING, 1, 1), "witchery.book.herbology.alder", "witchery:textures/blocks/sapling_alder.png");
/* 212 */               addPlantPage(new ItemStack(Witchery.Blocks.SAPLING, 1, 2), "witchery.book.herbology.hawthorn", "witchery:textures/blocks/sapling_hawthorn.png");
/* 213 */               addPlantPage(new ItemStack(Witchery.Blocks.SAPLING, 1, 0), "witchery.book.herbology.rowan", "witchery:textures/blocks/sapling_rowan.png");
/* 214 */             } else if (Witchery.Items.GENERIC.itemBookBiomes.isMatch(itemstack)) {
/* 215 */               NBTTagCompound compound = new NBTTagCompound();
/* 216 */               String intro = Witchery.resource("witchery.book.biomes1");
/* 217 */               compound.func_74778_a("Summary", intro);
/* 218 */               this.bookPages.func_74742_a(compound);
/*     */               
/* 220 */               addBiomes(BiomeDictionary.Type.FOREST);
/* 221 */               addBiomes(BiomeDictionary.Type.PLAINS);
/* 222 */               addBiomes(BiomeDictionary.Type.MOUNTAIN);
/* 223 */               addBiomes(BiomeDictionary.Type.HILLS);
/* 224 */               addBiomes(BiomeDictionary.Type.SWAMP);
/* 225 */               addBiomes(BiomeDictionary.Type.WATER);
/* 226 */               addBiomes(BiomeDictionary.Type.DESERT);
/* 227 */               addBiomes(BiomeDictionary.Type.FROZEN);
/* 228 */               addBiomes(BiomeDictionary.Type.JUNGLE);
/* 229 */               addBiomes(BiomeDictionary.Type.WASTELAND);
/* 230 */               addBiomes(BiomeDictionary.Type.BEACH);
/* 231 */               addBiomes(BiomeDictionary.Type.MUSHROOM);
/* 232 */               addBiomes(BiomeDictionary.Type.MAGICAL);
/* 233 */             } else if (Witchery.Items.GENERIC.itemBookWands.isMatch(itemstack)) {
/* 234 */               compound = new NBTTagCompound();
/* 235 */               String intro = Witchery.resource("witchery.book.wands1");
/* 236 */               compound.func_74778_a("Summary", intro);
/* 237 */               this.bookPages.func_74742_a(compound);
/*     */               
/* 239 */               for (SymbolEffect recipe : com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry.instance().getEffects())
/* 240 */                 if (recipe.isVisible(player)) {
/* 241 */                   compound = new NBTTagCompound();
/* 242 */                   compound.func_74778_a("Summary", recipe.getDescription());
/* 243 */                   this.bookPages.func_74742_a(compound);
/*     */                 }
/*     */             }
/*     */           }
/*     */         } } }
/* 248 */     this.bookTotalPages = this.bookPages.func_74745_c();
/*     */     
/* 250 */     NBTTagCompound stackCompound = itemstack.func_77978_p();
/* 251 */     if ((stackCompound != null) && (stackCompound.func_74764_b("CurrentPage"))) {
/* 252 */       this.currPage = Math.min(Math.max(stackCompound.func_74762_e("CurrentPage"), 0), Math.max(this.bookTotalPages, 1) - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addBiomes(BiomeDictionary.Type biomeType) {
/* 257 */     String biomeKey = biomeType.toString().toLowerCase();
/* 258 */     String title = "§n" + Witchery.resource(new StringBuilder().append("witchery.book.biomes.").append(biomeKey).append(".name").toString()) + "§r" + "\n\n" + "§8" + Witchery.resource("witchery.book.biomes.foci") + ": " + Witchery.resource(new StringBuilder().append("witchery.book.biomes.").append(biomeKey).append(".item").toString()) + "§0" + Const.BOOK_NEWLINE;
/*     */     
/* 260 */     BiomeGenBase[] biomes = net.minecraftforge.common.BiomeDictionary.getBiomesForType(biomeType);
/* 261 */     int ITEMS_PER_PAGE = 8;
/* 262 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 264 */     for (int glowstone = 1; glowstone <= biomes.length; glowstone++) {
/* 265 */       sb.append(glowstone);
/* 266 */       sb.append(" : ");
/* 267 */       sb.append(biomes[(glowstone - 1)].field_76791_y);
/* 268 */       sb.append(Const.BOOK_NEWLINE);
/* 269 */       if ((glowstone % 8 == 0) || (glowstone == biomes.length)) {
/* 270 */         NBTTagCompound compound = new NBTTagCompound();
/* 271 */         compound.func_74778_a("Summary", title + Const.BOOK_NEWLINE + sb.toString());
/* 272 */         this.bookPages.func_74742_a(compound);
/* 273 */         sb = new StringBuilder();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addPlantPage(ItemStack plantStack, String descriptionResourceID, String imageResourceID) {
/* 279 */     NBTTagCompound compound = new NBTTagCompound();
/* 280 */     compound.func_74778_a("Summary", "§n" + plantStack.func_82833_r() + "§r");
/* 281 */     compound.func_74778_a("Details", Witchery.resource(descriptionResourceID));
/* 282 */     compound.func_74778_a("Image", imageResourceID);
/* 283 */     this.bookPages.func_74742_a(compound);
/*     */   }
/*     */   
/*     */   private void addPlantPage(Block plantBlock, String descriptionResourceID, String imageResourceID) {
/* 287 */     NBTTagCompound compound = new NBTTagCompound();
/* 288 */     compound.func_74778_a("Summary", "§n" + plantBlock.func_149732_F() + "§r");
/* 289 */     compound.func_74778_a("Details", Witchery.resource(descriptionResourceID));
/* 290 */     compound.func_74778_a("Image", imageResourceID);
/* 291 */     this.bookPages.func_74742_a(compound);
/*     */   }
/*     */   
/*     */ 
/*     */   private void storeCurrentPage()
/*     */   {
/* 297 */     if (this.itemstack.func_77978_p() == null) {
/* 298 */       this.itemstack.func_77982_d(new NBTTagCompound());
/*     */     }
/* 300 */     this.itemstack.func_77978_p().func_74768_a("CurrentPage", this.currPage);
/*     */   }
/*     */   
/*     */   public void func_73876_c()
/*     */   {
/* 305 */     super.func_73876_c();
/* 306 */     this.updateCount += 1;
/*     */   }
/*     */   
/*     */   public void func_73866_w_()
/*     */   {
/* 311 */     this.field_146292_n.clear();
/* 312 */     org.lwjgl.input.Keyboard.enableRepeatEvents(true);
/*     */     
/* 314 */     this.field_146292_n.add(this.buttonDone = new GuiButton(0, this.field_146294_l / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.func_135052_a("gui.done", new Object[0])));
/*     */     
/* 316 */     int i = (this.field_146294_l - this.bookImageWidth) / 2;
/* 317 */     byte b0 = 2;
/* 318 */     if (Witchery.Items.GENERIC.itemBookCircleMagic.isMatch(this.itemstack)) {
/* 319 */       this.field_146292_n.add(this.buttonNextPage = new GuiButtonNextPage(1, i + 180, b0 + 154, true));
/* 320 */       this.field_146292_n.add(this.buttonPreviousPage = new GuiButtonNextPage(2, i + 110, b0 + 154, false));
/*     */       
/* 322 */       this.field_146292_n.add(this.buttonJumpPage7 = new GuiButtonJumpPage(9, i + 214, b0 + 138, 69, 48, 248));
/* 323 */       this.field_146292_n.add(this.buttonJumpPage6 = new GuiButtonJumpPage(8, i + 214, b0 + 118, 58, 40, 248));
/* 324 */       this.field_146292_n.add(this.buttonJumpPage5 = new GuiButtonJumpPage(7, i + 214, b0 + 98, 47, 32, 248));
/* 325 */       this.field_146292_n.add(this.buttonJumpPage4 = new GuiButtonJumpPage(6, i + 214, b0 + 78, 29, 24, 248));
/* 326 */       this.field_146292_n.add(this.buttonJumpPage3 = new GuiButtonJumpPage(5, i + 214, b0 + 58, 23, 16, 248));
/* 327 */       this.field_146292_n.add(this.buttonJumpPage2 = new GuiButtonJumpPage(4, i + 214, b0 + 38, 17, 8, 248));
/* 328 */       this.field_146292_n.add(this.buttonJumpPage1 = new GuiButtonJumpPage(3, i + 214, b0 + 18, 2, 0, 248));
/*     */     } else {
/* 330 */       this.field_146292_n.add(this.buttonNextPage = new GuiButtonNextPage(1, i + 120, b0 + 154, true));
/* 331 */       this.field_146292_n.add(this.buttonPreviousPage = new GuiButtonNextPage(2, i + 38, b0 + 154, false));
/*     */     }
/* 333 */     updateButtons();
/*     */   }
/*     */   
/*     */   public void func_146281_b()
/*     */   {
/* 338 */     org.lwjgl.input.Keyboard.enableRepeatEvents(false);
/* 339 */     sendBookToServer(false);
/*     */   }
/*     */   
/*     */   private void updateButtons() {
/* 343 */     this.buttonNextPage.field_146125_m = (this.currPage < this.bookTotalPages - 1);
/* 344 */     this.buttonPreviousPage.field_146125_m = (this.currPage > 0);
/*     */   }
/*     */   
/*     */   private void sendBookToServer(boolean par1) {
/* 348 */     if ((this.player != null) && (this.currPage >= 0) && (this.currPage < 1000) && (this.player.field_71071_by.field_70461_c >= 0) && (this.player.field_71071_by.func_70448_g() != null)) {
/* 349 */       Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketItemUpdate(this.player.field_71071_by.field_70461_c, this.currPage, this.player.field_71071_by.func_70448_g()));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_146284_a(GuiButton par1GuiButton)
/*     */   {
/* 355 */     if (par1GuiButton.field_146124_l) {
/* 356 */       if (par1GuiButton.field_146127_k == 0) {
/* 357 */         this.field_146297_k.func_147108_a((GuiScreen)null);
/*     */       }
/* 359 */       else if (par1GuiButton.field_146127_k == 1) {
/* 360 */         if (this.currPage < this.bookTotalPages - 1) {
/* 361 */           this.currPage += 1;
/* 362 */           storeCurrentPage();
/*     */         }
/* 364 */       } else if (par1GuiButton.field_146127_k == 2) {
/* 365 */         if (this.currPage > 0) {
/* 366 */           this.currPage -= 1;
/* 367 */           storeCurrentPage();
/*     */         }
/* 369 */       } else if ((par1GuiButton instanceof GuiButtonJumpPage)) {
/* 370 */         GuiButtonJumpPage but = (GuiButtonJumpPage)par1GuiButton;
/* 371 */         this.currPage = (but.nextPage - 1);
/* 372 */         storeCurrentPage();
/*     */       }
/*     */       
/* 375 */       updateButtons();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_73869_a(char par1, int par2)
/*     */   {
/* 385 */     super.func_73869_a(par1, par2);
/*     */   }
/*     */   
/*     */   public void func_73863_a(int par1, int par2, float par3)
/*     */   {
/* 390 */     org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 392 */     if (Witchery.Items.GENERIC.itemBookCircleMagic.isMatch(this.itemstack)) {
/* 393 */       this.field_146297_k.func_110434_K().func_110577_a(DOUBLE_BOOK_TEXTURE);
/* 394 */       this.bookImageWidth = 256;
/* 395 */       int k = (this.field_146294_l - this.bookImageWidth) / 2;
/* 396 */       byte b0 = 2;
/* 397 */       func_73729_b(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
/*     */       
/*     */ 
/*     */ 
/* 401 */       String s3 = "";
/*     */       
/*     */ 
/* 404 */       String s = I18n.func_135052_a("book.pageIndicator", new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages) });
/* 405 */       String s1 = "";
/* 406 */       String s2 = "";
/*     */       
/* 408 */       if ((this.bookPages != null) && (this.currPage >= 0) && (this.currPage < this.bookPages.func_74745_c())) {
/* 409 */         NBTTagCompound compound = this.bookPages.func_150305_b(this.currPage);
/* 410 */         s1 = compound.func_74779_i("Summary");
/* 411 */         s2 = compound.func_74779_i("Summary2");
/* 412 */         if (compound.func_74764_b("Circles")) {
/* 413 */           byte[] circles = compound.func_74770_j("Circles");
/* 414 */           for (byte circle : circles) {
/* 415 */             this.field_146297_k.func_110434_K().func_110577_a(field_110405_b[circle]);
/* 416 */             func_73729_b(k, b0, 65388, -36, this.bookImageWidth, this.bookImageHeight);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 422 */       int l = this.field_146289_q.func_78256_a(s);
/* 423 */       this.field_146289_q.func_78276_b(s, k - l + this.bookImageWidth - 16, b0 + 16, 0);
/* 424 */       this.field_146289_q.func_78279_b(s1, k + 20, b0 + 16, 98, 0);
/*     */       
/* 426 */       if (!s2.isEmpty()) {
/* 427 */         int swidth = this.field_146289_q.func_78256_a(s2);
/*     */         
/* 429 */         if (swidth < 90) {
/* 430 */           this.field_146289_q.func_78279_b(s2, k + this.bookImageWidth / 4 * 3 - swidth / 2, b0 + 125, 98, 0);
/*     */         } else {
/* 432 */           this.field_146289_q.func_78279_b(s2, k + 142, b0 + 125, 98, 0);
/*     */         }
/*     */       }
/*     */     } else {
/* 436 */       this.field_146297_k.func_110434_K().func_110577_a(field_110405_a);
/* 437 */       int k = (this.field_146294_l - this.bookImageWidth) / 2;
/* 438 */       byte b0 = 2;
/* 439 */       func_73729_b(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
/*     */       
/*     */ 
/* 442 */       String s2 = "";
/*     */       
/*     */ 
/* 445 */       String s = I18n.func_135052_a("book.pageIndicator", new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages) });
/* 446 */       String s1 = "";
/*     */       
/* 448 */       boolean hasImage = false;
/* 449 */       if ((this.bookPages != null) && (this.currPage >= 0) && (this.currPage < this.bookPages.func_74745_c())) {
/* 450 */         NBTTagCompound compound = this.bookPages.func_150305_b(this.currPage);
/* 451 */         s1 = compound.func_74779_i("Summary");
/* 452 */         if (compound.func_74764_b("Circles")) {
/* 453 */           byte[] circles = compound.func_74770_j("Circles");
/* 454 */           for (byte circle : circles) {
/* 455 */             this.field_146297_k.func_110434_K().func_110577_a(field_110405_b[circle]);
/* 456 */             func_73729_b(k, b0, -62, -70, this.bookImageWidth, this.bookImageHeight);
/*     */           }
/*     */         }
/* 459 */         hasImage = compound.func_74764_b("Image");
/* 460 */         if (hasImage) {
/* 461 */           String loc = compound.func_74779_i("Image");
/* 462 */           ResourceLocation location = new ResourceLocation(loc);
/* 463 */           this.field_146297_k.func_110434_K().func_110577_a(location);
/*     */           
/* 465 */           drawTexturedQuadFit(k - 32 + this.bookImageWidth - 44, b0 + 32, 32.0D, 32.0D, this.field_73735_i);
/*     */         }
/*     */         
/* 468 */         if (compound.func_74764_b("Details")) {
/* 469 */           s2 = compound.func_74779_i("Details");
/*     */         }
/*     */       }
/*     */       
/* 473 */       int l = this.field_146289_q.func_78256_a(s);
/* 474 */       this.field_146289_q.func_78276_b(s, k - l + this.bookImageWidth - 44, b0 + 16, 0);
/* 475 */       this.field_146289_q.func_78279_b(s1, k + 36, b0 + 32, 116 - (hasImage ? 34 : 0), 0);
/* 476 */       if ((s2 != null) && (!s2.isEmpty())) {
/* 477 */         this.field_146289_q.func_78279_b(s2, k + 36, b0 + 32 + 34, 116, 0);
/*     */       }
/*     */     }
/*     */     
/* 481 */     super.func_73863_a(par1, par2, par3);
/*     */   }
/*     */   
/*     */   public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
/* 485 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 486 */     tessellator.func_78382_b();
/* 487 */     tessellator.func_78374_a(x + 0.0D, y + height, zLevel, 0.0D, 1.0D);
/* 488 */     tessellator.func_78374_a(x + width, y + height, zLevel, 1.0D, 1.0D);
/* 489 */     tessellator.func_78374_a(x + width, y + 0.0D, zLevel, 1.0D, 0.0D);
/* 490 */     tessellator.func_78374_a(x + 0.0D, y + 0.0D, zLevel, 0.0D, 0.0D);
/* 491 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   static ResourceLocation func_110404_g() {
/* 495 */     return field_110405_a;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/gui/GuiScreenWitchcraftBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */