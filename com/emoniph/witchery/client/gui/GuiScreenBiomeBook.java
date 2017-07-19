/*     */ package com.emoniph.witchery.client.gui;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.item.ItemBook;
/*     */ import com.emoniph.witchery.network.PacketItemUpdate;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class GuiScreenBiomeBook extends GuiScreen
/*     */ {
/*  29 */   private static final ResourceLocation field_110405_a = new ResourceLocation("textures/gui/book.png");
/*     */   
/*     */   private final EntityPlayer player;
/*     */   
/*     */   private final ItemStack itemstack;
/*     */   private int updateCount;
/*  35 */   private int bookImageWidth = 192;
/*  36 */   private int bookImageHeight = 192;
/*     */   private int pageIndex;
/*     */   private NBTTagList bookPages;
/*  39 */   private String bookTitle = "";
/*     */   
/*     */   private GuiButtonNextPage buttonNextPage;
/*     */   
/*     */   private GuiButtonNextPage buttonPreviousPage;
/*     */   private GuiButton buttonDone;
/*     */   private GuiButtonJumpPage buttonJumpPage1;
/*     */   private GuiButtonJumpPage buttonJumpPage2;
/*     */   private GuiButtonJumpPage buttonJumpPage3;
/*     */   private GuiButtonJumpPage buttonJumpPage4;
/*     */   private GuiButtonJumpPage buttonJumpPage5;
/*     */   private GuiButtonJumpPage buttonJumpPage6;
/*     */   private GuiButtonJumpPage buttonJumpPage7;
/*  52 */   ArrayList<BiomeGenBase> biomes = new ArrayList();
/*  53 */   ArrayList<Integer> sections = new ArrayList();
/*  54 */   ArrayList<String> sectionNames = new ArrayList();
/*     */   
/*     */   public GuiScreenBiomeBook(EntityPlayer player, ItemStack itemstack) {
/*  57 */     this.player = player;
/*  58 */     this.itemstack = itemstack;
/*  59 */     this.bookTitle = itemstack.func_82833_r();
/*     */     
/*  61 */     for (BiomeDictionary.Type biomeType : ItemBook.BIOME_TYPES) {
/*  62 */       addBiomes(biomeType);
/*     */     }
/*     */     
/*  65 */     this.pageIndex = ItemBook.getSelectedBiome(itemstack, this.biomes.size());
/*     */   }
/*     */   
/*     */   private void addBiomes(BiomeDictionary.Type biomeType) {
/*  69 */     BiomeGenBase[] biomesInType = BiomeDictionary.getBiomesForType(biomeType);
/*  70 */     this.sections.add(Integer.valueOf(this.biomes.size()));
/*  71 */     this.sectionNames.add(Witchery.resource("witchery.book.biomes." + biomeType.toString().toLowerCase() + ".name"));
/*  72 */     for (int i = 0; i < biomesInType.length; i++) {
/*  73 */       this.biomes.add(biomesInType[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void storeCurrentPage() {
/*  78 */     ItemBook.setSelectedBiome(this.itemstack, this.pageIndex);
/*     */   }
/*     */   
/*     */   public void func_73876_c()
/*     */   {
/*  83 */     super.func_73876_c();
/*  84 */     this.updateCount += 1;
/*     */   }
/*     */   
/*     */   public void func_73866_w_()
/*     */   {
/*  89 */     this.field_146292_n.clear();
/*  90 */     Keyboard.enableRepeatEvents(true);
/*     */     
/*  92 */     byte b0 = 2;
/*  93 */     int mid = (this.field_146294_l - this.bookImageWidth) / 2;
/*  94 */     this.field_146292_n.add(this.buttonNextPage = new GuiButtonNextPage(1, mid + 120, b0 + 154, true));
/*  95 */     this.field_146292_n.add(this.buttonPreviousPage = new GuiButtonNextPage(2, mid + 38, b0 + 154, false));
/*     */     
/*  97 */     for (int i = this.sections.size() - 1; i >= 0; i--) {
/*  98 */       GuiButton button = new GuiButtonBookmark(3 + i, mid + 160, 12 * i + 10, ((Integer)this.sections.get(i)).intValue(), (String)this.sectionNames.get(i));
/*     */       
/* 100 */       button.field_146124_l = true;
/* 101 */       this.field_146292_n.add(button);
/*     */     }
/* 103 */     updateButtons();
/*     */   }
/*     */   
/*     */   public void func_146281_b()
/*     */   {
/* 108 */     Keyboard.enableRepeatEvents(false);
/* 109 */     sendBookToServer(false);
/*     */   }
/*     */   
/*     */   private void updateButtons() {
/* 113 */     this.buttonNextPage.field_146125_m = (this.pageIndex < this.biomes.size() - 1);
/* 114 */     this.buttonPreviousPage.field_146125_m = (this.pageIndex > 0);
/*     */   }
/*     */   
/*     */   private void sendBookToServer(boolean par1) {
/* 118 */     if ((this.player != null) && (this.pageIndex >= 0) && (this.pageIndex < 1000) && (this.player.field_71071_by.field_70461_c >= 0) && (this.player.field_71071_by.func_70448_g() != null))
/*     */     {
/* 120 */       Witchery.packetPipeline.sendToServer(new PacketItemUpdate(this.player.field_71071_by.field_70461_c, this.pageIndex, this.player.field_71071_by.func_70448_g()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_146284_a(GuiButton button)
/*     */   {
/* 127 */     if (button.field_146124_l) {
/* 128 */       if (button.field_146127_k == 0) {
/* 129 */         this.field_146297_k.func_147108_a((GuiScreen)null);
/*     */       }
/* 131 */       else if (button.field_146127_k == 1) {
/* 132 */         if (this.pageIndex < this.biomes.size() - 1) {
/* 133 */           this.pageIndex += 1;
/* 134 */           storeCurrentPage();
/*     */         }
/* 136 */       } else if (button.field_146127_k == 2) {
/* 137 */         if (this.pageIndex > 0) {
/* 138 */           this.pageIndex -= 1;
/* 139 */           storeCurrentPage();
/*     */         }
/* 141 */       } else if ((button instanceof GuiButtonBookmark)) {
/* 142 */         GuiButtonBookmark but = (GuiButtonBookmark)button;
/* 143 */         this.pageIndex = but.nextPage;
/* 144 */         storeCurrentPage();
/*     */       }
/*     */       
/* 147 */       updateButtons();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_73869_a(char par1, int par2)
/*     */   {
/* 153 */     super.func_73869_a(par1, par2);
/*     */   }
/*     */   
/*     */   public void func_73863_a(int par1, int par2, float par3)
/*     */   {
/* 158 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 160 */     this.field_146297_k.func_110434_K().func_110577_a(field_110405_a);
/* 161 */     int k = (this.field_146294_l - this.bookImageWidth) / 2;
/* 162 */     byte b0 = 2;
/* 163 */     func_73729_b(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
/*     */     
/* 165 */     if ((this.biomes.size() > 0) && (this.pageIndex >= 0) && (this.pageIndex < this.biomes.size())) {
/* 166 */       String pageNumberText = I18n.func_135052_a("book.pageIndicator", new Object[] { Integer.valueOf(this.pageIndex + 1), Integer.valueOf(this.biomes.size()) });
/* 167 */       int pageNumberTextWitdh = this.field_146289_q.func_78256_a(pageNumberText);
/* 168 */       this.field_146289_q.func_78276_b(pageNumberText, k - pageNumberTextWitdh + this.bookImageWidth - 44, b0 + 16, 0);
/*     */       
/* 170 */       BiomeGenBase biome = (BiomeGenBase)this.biomes.get(this.pageIndex);
/* 171 */       int maxWidth = 116;
/* 172 */       int defaultColor = 0;
/* 173 */       b0 = (byte)(b0 + drawSpiltString(biome.field_76791_y, k + 36, b0 + 32, 116, 0));
/* 174 */       b0 = (byte)(b0 + this.field_146289_q.field_78288_b);
/* 175 */       b0 = (byte)(b0 + drawSpiltString("> " + String.format(Witchery.resource("witchery.biomebook.rainfall"), new Object[] { Float.valueOf(biome.field_76751_G) }), k + 36, b0 + 32, 116, 0));
/*     */       
/* 177 */       String temperatureFormat = Witchery.resource(biome.func_76736_e() ? "witchery.biomebook.temperaturehot" : "witchery.biomebook.temperature");
/* 178 */       b0 = (byte)(b0 + drawSpiltString("> " + String.format(temperatureFormat, new Object[] { Float.valueOf(biome.field_76750_F) }), k + 36, b0 + 32, 116, 0));
/*     */       
/* 180 */       b0 = (byte)(b0 + drawSpiltString("> " + String.format(Witchery.resource("witchery.biomebook.snows"), new Object[] { toYesNo(biome.func_76746_c()) }), k + 36, b0 + 32, 116, 0));
/*     */       
/* 182 */       b0 = (byte)(b0 + drawSpiltString("> " + String.format(Witchery.resource("witchery.biomebook.lightning"), new Object[] { toYesNo(biome.func_76738_d()) }), k + 36, b0 + 32, 116, 0));
/*     */     }
/*     */     
/*     */ 
/* 186 */     super.func_73863_a(par1, par2, par3);
/*     */   }
/*     */   
/*     */   private int drawSpiltString(String text, int x, int y, int maxWidth, int color) {
/* 190 */     int height = this.field_146289_q.func_78267_b(text, maxWidth);
/* 191 */     this.field_146289_q.func_78279_b(text, x, y, maxWidth, color);
/* 192 */     return height;
/*     */   }
/*     */   
/*     */   private String toYesNo(boolean val) {
/* 196 */     return Witchery.resource(val ? "witchery.yes" : "witchery.no");
/*     */   }
/*     */   
/*     */   public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
/* 200 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 201 */     tessellator.func_78382_b();
/* 202 */     tessellator.func_78374_a(x + 0.0D, y + height, zLevel, 0.0D, 1.0D);
/* 203 */     tessellator.func_78374_a(x + width, y + height, zLevel, 1.0D, 1.0D);
/* 204 */     tessellator.func_78374_a(x + width, y + 0.0D, zLevel, 1.0D, 0.0D);
/* 205 */     tessellator.func_78374_a(x + 0.0D, y + 0.0D, zLevel, 0.0D, 0.0D);
/* 206 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   static ResourceLocation func_110404_g() {
/* 210 */     return field_110405_a;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/gui/GuiScreenBiomeBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */