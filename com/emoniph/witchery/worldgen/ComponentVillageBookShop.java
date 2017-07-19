/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*     */ 
/*     */ public class ComponentVillageBookShop extends StructureVillagePieces.Village
/*     */ {
/*     */   public static ComponentVillageBookShop construct(StructureVillagePieces.Start start, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
/*     */   {
/*  31 */     StructureBoundingBox bounds = StructureBoundingBox.func_78889_a(p1, p2, p3, 0, 0, 0, 10, 8, 9, p4);
/*     */     
/*  33 */     return (func_74895_a(bounds)) && (StructureComponent.func_74883_a(pieces, bounds) == null) ? new ComponentVillageBookShop(start, p5, rand, bounds, p4) : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComponentVillageBookShop() {}
/*     */   
/*     */ 
/*     */   public ComponentVillageBookShop(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int coordMode)
/*     */   {
/*  42 */     super(start, componentType);
/*  43 */     this.field_74885_f = coordMode;
/*  44 */     this.field_74887_e = bounds;
/*     */   }
/*     */   
/*     */   public boolean func_74875_a(World world, Random rand, StructureBoundingBox bounds) {
/*  48 */     int height = 8;
/*  49 */     if (this.field_143015_k < 0) {
/*  50 */       this.field_143015_k = func_74889_b(world, bounds);
/*     */       
/*  52 */       if (this.field_143015_k < 0) {
/*  53 */         return true;
/*     */       }
/*     */       
/*  56 */       this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 8 - 1, 0);
/*     */     }
/*     */     
/*  59 */     func_151549_a(world, bounds, 1, 0, 1, 8, 0, 6, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  60 */     func_151549_a(world, bounds, 2, 0, 2, 7, 0, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*     */     
/*  62 */     func_151549_a(world, bounds, 1, 1, 0, 8, 7, 6, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/*  64 */     func_151549_a(world, bounds, 1, 1, 3, 8, 5, 6, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  66 */     func_151549_a(world, bounds, 1, 6, 4, 8, 6, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  68 */     func_151549_a(world, bounds, 1, 4, 1, 8, 4, 2, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  70 */     func_151549_a(world, bounds, 2, 1, 4, 7, 4, 5, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/*  72 */     func_151549_a(world, bounds, 2, 1, 3, 7, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*     */     
/*  74 */     func_151549_a(world, bounds, 3, 2, 3, 6, 3, 3, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*  75 */     func_151550_a(world, Blocks.field_150350_a, 0, 6, 1, 3, bounds);
/*     */     
/*  77 */     func_151550_a(world, Blocks.field_150478_aa, 0, 3, 4, 4, bounds);
/*  78 */     func_151550_a(world, Blocks.field_150478_aa, 0, 6, 4, 4, bounds);
/*     */     
/*  80 */     func_151549_a(world, bounds, 1, 2, 4, 1, 4, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  81 */     func_151549_a(world, bounds, 8, 2, 4, 8, 4, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*     */     
/*  83 */     func_151549_a(world, bounds, 2, 2, 6, 7, 4, 6, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*     */     
/*  85 */     func_151549_a(world, bounds, 1, 1, 1, 1, 3, 1, Blocks.field_150422_aJ, Blocks.field_150422_aJ, false);
/*     */     
/*  87 */     func_151549_a(world, bounds, 8, 1, 1, 8, 3, 1, Blocks.field_150422_aJ, Blocks.field_150422_aJ, false);
/*     */     
/*  89 */     int n = func_151555_a(Blocks.field_150476_ad, 3);
/*  90 */     int s = func_151555_a(Blocks.field_150476_ad, 2);
/*  91 */     int w = func_151555_a(Blocks.field_150476_ad, 0);
/*  92 */     int e = func_151555_a(Blocks.field_150476_ad, 1);
/*     */     
/*  94 */     func_151550_a(world, Blocks.field_150446_ar, n, 3, 0, 0, bounds);
/*  95 */     func_151550_a(world, Blocks.field_150446_ar, n, 4, 0, 0, bounds);
/*  96 */     func_151550_a(world, Blocks.field_150446_ar, n, 5, 0, 0, bounds);
/*  97 */     func_151550_a(world, Blocks.field_150446_ar, n, 6, 0, 0, bounds);
/*     */     
/*  99 */     for (int i = 1; i <= 8; i++) {
/* 100 */       func_151550_a(world, Blocks.field_150476_ad, n, i, 5, 2, bounds);
/* 101 */       func_151550_a(world, Blocks.field_150476_ad, n, i, 6, 3, bounds);
/* 102 */       func_151550_a(world, Blocks.field_150476_ad, n, i, 7, 4, bounds);
/*     */       
/* 104 */       func_151550_a(world, Blocks.field_150476_ad, s, i, 5, 7, bounds);
/* 105 */       func_151550_a(world, Blocks.field_150476_ad, s, i, 6, 6, bounds);
/* 106 */       func_151550_a(world, Blocks.field_150476_ad, s, i, 7, 5, bounds);
/*     */     }
/*     */     
/* 109 */     if (!this.hasMadeChest) {
/* 110 */       int i = func_74862_a(1);
/* 111 */       int j = func_74865_a(2, 4);
/* 112 */       int k = func_74873_b(2, 4);
/*     */       
/* 114 */       if (bounds.func_78890_b(j, i, k)) {
/* 115 */         Log.instance().debug(String.format("Bookshop %d %d %d - dir %d", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(this.field_74885_f) }));
/*     */         
/* 117 */         if (bookshopChestContents == null) {
/* 118 */           List<WeightedRandomChestContent> list = new ArrayList();
/* 119 */           list.add(new WeightedRandomChestContent(Items.field_151122_aG, 0, 1, 1, 1));
/* 120 */           list.add(new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemVampireBookPage.damageValue, 1, 1, 3));
/* 121 */           list.add(new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemVampireBookPage.damageValue, 1, 1, 2));
/* 122 */           list.add(new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemVampireBookPage.damageValue, 1, 1, 1));
/* 123 */           for (String bonusBook : Config.instance().townBooks) {
/*     */             try {
/* 125 */               String name = bonusBook;
/* 126 */               int meta = 0;
/* 127 */               int comma = bonusBook.lastIndexOf(',');
/* 128 */               if (comma >= 0) {
/* 129 */                 name = bonusBook.substring(0, comma);
/* 130 */                 String metaString = bonusBook.substring(comma + 1);
/* 131 */                 meta = Integer.parseInt(metaString);
/*     */               }
/* 133 */               Item item = (Item)Item.field_150901_e.func_82594_a(name);
/* 134 */               if (item != null) {
/* 135 */                 list.add(new WeightedRandomChestContent(item, meta, 1, 1, 1));
/*     */               }
/*     */             }
/*     */             catch (Throwable ex) {}
/*     */           }
/* 140 */           bookshopChestContents = (WeightedRandomChestContent[])list.toArray(new WeightedRandomChestContent[list.size()]);
/*     */         }
/*     */         
/* 143 */         this.hasMadeChest = true;
/* 144 */         generateStructureChestContents(world, bounds, rand, 2, 1, 4, bookshopChestContents, 5 + rand.nextInt(6), new ItemStack[] { new ItemStack(Witchery.Items.VAMPIRE_BOOK) });
/*     */         
/*     */ 
/* 147 */         addBookInFrame(world, 3, 3, 6, bookshopChestContents[rand.nextInt(bookshopChestContents.length)].field_76297_b.func_77946_l());
/* 148 */         addBookInFrame(world, 4, 3, 6, bookshopChestContents[rand.nextInt(bookshopChestContents.length)].field_76297_b.func_77946_l());
/* 149 */         addBookInFrame(world, 5, 3, 6, bookshopChestContents[rand.nextInt(bookshopChestContents.length)].field_76297_b.func_77946_l());
/* 150 */         addBookInFrame(world, 6, 3, 6, bookshopChestContents[rand.nextInt(bookshopChestContents.length)].field_76297_b.func_77946_l());
/*     */       }
/*     */     }
/*     */     
/* 154 */     for (int j = 1; j < 7; j++) {
/* 155 */       for (int k = 1; k < 7; k++) {
/* 156 */         func_74871_b(world, k, 8, j, bounds);
/* 157 */         func_151554_b(world, Blocks.field_150347_e, 0, k, -1, j, bounds);
/*     */       }
/*     */     }
/*     */     
/* 161 */     return true;
/*     */   }
/*     */   
/*     */   private boolean generateStructureChestContents(World world, StructureBoundingBox bounds, Random rand, int x, int y, int z, WeightedRandomChestContent[] contents, int quantity, ItemStack[] extraItems)
/*     */   {
/* 166 */     int i1 = func_74865_a(x, z);
/* 167 */     int j1 = func_74862_a(y);
/* 168 */     int k1 = func_74873_b(x, z);
/*     */     
/* 170 */     if ((bounds.func_78890_b(i1, j1, k1)) && (world.func_147439_a(i1, j1, k1) != Blocks.field_150486_ae)) {
/* 171 */       world.func_147465_d(i1, j1, k1, Blocks.field_150486_ae, 0, 2);
/* 172 */       TileEntityChest chest = (TileEntityChest)world.func_147438_o(i1, j1, k1);
/*     */       
/* 174 */       if (chest != null) {
/* 175 */         WeightedRandomChestContent.func_76293_a(rand, contents, chest, quantity);
/* 176 */         if (extraItems != null) {
/* 177 */           for (ItemStack stack : extraItems) {
/* 178 */             chest.func_70299_a(rand.nextInt(chest.func_70302_i_()), stack.func_77946_l());
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 183 */       return true;
/*     */     }
/* 185 */     return false;
/*     */   }
/*     */   
/*     */   private void addBookInFrame(World world, int x, int y, int z, ItemStack stack)
/*     */   {
/* 190 */     int xWorld = func_74865_a(x, z);
/* 191 */     int yWorld = func_74862_a(y);
/* 192 */     int zWorld = func_74873_b(x, z);
/*     */     
/* 194 */     int direction = 0;
/* 195 */     switch (this.field_74885_f) {
/*     */     case 0: 
/*     */     default: 
/* 198 */       direction = 2;
/* 199 */       break;
/*     */     case 1: 
/* 201 */       direction = 3;
/* 202 */       break;
/*     */     case 2: 
/* 204 */       direction = 0;
/* 205 */       break;
/*     */     case 3: 
/* 207 */       direction = 1;
/*     */     }
/*     */     
/*     */     
/* 211 */     EntityItemFrame frame = new EntityItemFrame(world, xWorld, yWorld, zWorld, direction);
/* 212 */     if ((frame != null) && (frame.func_70518_d()) && 
/* 213 */       (!world.field_72995_K)) {
/* 214 */       world.func_72838_d(frame);
/* 215 */       frame.func_82334_a(stack);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 220 */   public static WeightedRandomChestContent[] bookshopChestContents = null;
/*     */   private boolean hasMadeChest;
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound nbtRoot)
/*     */   {
/* 225 */     super.func_143012_a(nbtRoot);
/* 226 */     nbtRoot.func_74757_a("Chest", this.hasMadeChest);
/*     */   }
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound nbtRoot) {
/* 230 */     super.func_143011_b(nbtRoot);
/* 231 */     this.hasMadeChest = nbtRoot.func_74767_n("Chest");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentVillageBookShop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */