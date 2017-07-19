/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.item.ItemPoppet;
/*     */ import com.emoniph.witchery.item.ItemPoppet.PoppetType;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntitySign;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ 
/*     */ public class ComponentVillageApothecary extends StructureVillagePieces.House1
/*     */ {
/*  24 */   private int averageGroundLevel = -1;
/*     */   
/*     */   public ComponentVillageApothecary() {}
/*     */   
/*     */   public ComponentVillageApothecary(StructureVillagePieces.Start componentVillageStartPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction)
/*     */   {
/*  30 */     super(componentVillageStartPiece, componentType, random, structureBoundingBox, direction);
/*  31 */     this.field_74885_f = direction;
/*  32 */     this.field_74887_e = structureBoundingBox;
/*     */   }
/*     */   
/*     */   public static ComponentVillageApothecary buildComponent(StructureVillagePieces.Start startPiece, List list, Random random, int par3, int par4, int par5, int par6, int par7)
/*     */   {
/*  37 */     StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(par3, par4, par5, 0, 0, 0, 9, 9, 6, par6);
/*  38 */     return (func_74895_a(structureboundingbox)) && (net.minecraft.world.gen.structure.StructureComponent.func_74883_a(list, structureboundingbox) == null) ? new ComponentVillageApothecary(startPiece, par7, random, structureboundingbox, par6) : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_74875_a(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
/*     */   {
/*  44 */     if (this.averageGroundLevel < 0) {
/*  45 */       this.averageGroundLevel = func_74889_b(par1World, par3StructureBoundingBox);
/*     */       
/*  47 */       if (this.averageGroundLevel < 0) {
/*  48 */         return true;
/*     */       }
/*     */       
/*  51 */       this.field_74887_e.func_78886_a(0, this.averageGroundLevel - this.field_74887_e.field_78894_e + 9 - 1, 0);
/*     */     }
/*     */     
/*  54 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 1, 7, 5, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*  55 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 0, 0, 8, 0, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  56 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 5, 0, 8, 5, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  57 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 6, 1, 8, 6, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  58 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 7, 2, 8, 7, 3, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  59 */     int i = func_151555_a(Blocks.field_150476_ad, 3);
/*  60 */     int j = func_151555_a(Blocks.field_150476_ad, 2);
/*     */     
/*     */ 
/*     */ 
/*  64 */     for (int k = -1; k <= 2; k++) {
/*  65 */       for (int l = 0; l <= 8; l++) {
/*  66 */         func_151550_a(par1World, Blocks.field_150476_ad, i, l, 6 + k, k, par3StructureBoundingBox);
/*  67 */         func_151550_a(par1World, Blocks.field_150476_ad, j, l, 6 + k, 5 - k, par3StructureBoundingBox);
/*     */       }
/*     */     }
/*     */     
/*  71 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 1, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  72 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 5, 8, 1, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  73 */     func_151549_a(par1World, par3StructureBoundingBox, 8, 1, 0, 8, 1, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  74 */     func_151549_a(par1World, par3StructureBoundingBox, 2, 1, 0, 7, 1, 0, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  75 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 4, 0, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  76 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 2, 5, 0, 4, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  77 */     func_151549_a(par1World, par3StructureBoundingBox, 8, 2, 5, 8, 4, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  78 */     func_151549_a(par1World, par3StructureBoundingBox, 8, 2, 0, 8, 4, 0, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  79 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 2, 1, 0, 4, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  80 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 2, 5, 7, 4, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  81 */     func_151549_a(par1World, par3StructureBoundingBox, 8, 2, 1, 8, 4, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  82 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 4, 0, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  83 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 4, 2, 0, par3StructureBoundingBox);
/*  84 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 2, 0, par3StructureBoundingBox);
/*  85 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 2, 0, par3StructureBoundingBox);
/*  86 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 4, 3, 0, par3StructureBoundingBox);
/*  87 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 3, 0, par3StructureBoundingBox);
/*  88 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 3, 0, par3StructureBoundingBox);
/*  89 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 2, 2, par3StructureBoundingBox);
/*  90 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 2, 3, par3StructureBoundingBox);
/*  91 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 3, 2, par3StructureBoundingBox);
/*  92 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 3, 3, par3StructureBoundingBox);
/*  93 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 2, 2, par3StructureBoundingBox);
/*  94 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 2, 3, par3StructureBoundingBox);
/*  95 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 3, 2, par3StructureBoundingBox);
/*  96 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 3, 3, par3StructureBoundingBox);
/*  97 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 2, 2, 5, par3StructureBoundingBox);
/*  98 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 3, 2, 5, par3StructureBoundingBox);
/*  99 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 2, 5, par3StructureBoundingBox);
/* 100 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 2, 5, par3StructureBoundingBox);
/*     */     
/* 102 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 2, 3, 5, par3StructureBoundingBox);
/* 103 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 3, 3, 5, par3StructureBoundingBox);
/* 104 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 3, 5, par3StructureBoundingBox);
/* 105 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 3, 5, par3StructureBoundingBox);
/*     */     
/* 107 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 4, 1, 7, 4, 1, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 108 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 4, 4, 7, 4, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*     */     
/* 110 */     func_151550_a(par1World, Blocks.field_150383_bp, 3, 7, 1, 1, par3StructureBoundingBox);
/* 111 */     func_151550_a(par1World, Blocks.field_150487_bG, func_151555_a(Blocks.field_150476_ad, 3) | 0x4, 5, 1, 3, par3StructureBoundingBox);
/*     */     
/* 113 */     func_151550_a(par1World, Blocks.field_150487_bG, func_151555_a(Blocks.field_150476_ad, 2) | 0x4, 5, 1, 1, par3StructureBoundingBox);
/*     */     
/* 115 */     func_151550_a(par1World, Blocks.field_150376_bx, 10, 5, 1, 2, par3StructureBoundingBox);
/* 116 */     func_151550_a(par1World, Blocks.field_150478_aa, 0, 5, 2, 3, par3StructureBoundingBox);
/* 117 */     func_151550_a(par1World, Blocks.field_150457_bL, 9, 5, 2, 1, par3StructureBoundingBox);
/*     */     
/* 119 */     if (!this.hasMadeChest) {
/* 120 */       int ic = func_74862_a(0);
/* 121 */       int jc = func_74865_a(7, 1);
/* 122 */       int kc = func_74873_b(7, 1);
/*     */       
/* 124 */       if (par3StructureBoundingBox.func_78890_b(jc, ic, kc))
/*     */       {
/* 126 */         this.hasMadeChest = true;
/* 127 */         func_74879_a(par1World, par3StructureBoundingBox, par2Random, 7, 0, 1, villageApothecaryChestContents, 2 + par2Random.nextInt(4));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 132 */     func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 1, 0, par3StructureBoundingBox);
/* 133 */     func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 2, 0, par3StructureBoundingBox);
/* 134 */     func_74881_a(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, func_151555_a(Blocks.field_150466_ao, 1));
/*     */     
/* 136 */     generateStructureSign(par1World, par3StructureBoundingBox, par2Random, 1, 3, -1, net.minecraft.util.StatCollector.func_74838_a("witchery.structure.apothecary.name"));
/*     */     
/* 138 */     if ((func_151548_a(par1World, 1, 0, -1, par3StructureBoundingBox) == Blocks.field_150350_a) && (func_151548_a(par1World, 1, -1, -1, par3StructureBoundingBox) != Blocks.field_150350_a))
/*     */     {
/* 140 */       func_151550_a(par1World, Blocks.field_150446_ar, func_151555_a(Blocks.field_150446_ar, 3), 1, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/*     */ 
/* 144 */     for (int l = 0; l < 6; l++) {
/* 145 */       for (int i1 = 0; i1 < 9; i1++) {
/* 146 */         func_74871_b(par1World, i1, 9, l, par3StructureBoundingBox);
/* 147 */         func_151554_b(par1World, Blocks.field_150347_e, 0, i1, -1, l, par3StructureBoundingBox);
/*     */       }
/*     */     }
/*     */     
/* 151 */     func_74893_a(par1World, par3StructureBoundingBox, 2, 1, 2, 1);
/* 152 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean generateStructureSign(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, String text)
/*     */   {
/* 157 */     int i1 = func_74865_a(par4, par6);
/* 158 */     int j1 = func_74862_a(par5);
/* 159 */     int k1 = func_74873_b(par4, par6);
/*     */     
/* 161 */     if ((par2StructureBoundingBox.func_78890_b(i1, j1, k1)) && (par1World.func_147439_a(i1, j1, k1) != Blocks.field_150444_as)) {
/* 162 */       int metadata = 4;
/* 163 */       switch (this.field_74885_f) {
/*     */       case 0: 
/* 165 */         metadata = 2;
/* 166 */         break;
/*     */       case 1: 
/* 168 */         metadata = 5;
/* 169 */         break;
/*     */       case 2: 
/*     */       default: 
/* 172 */         metadata = 3;
/* 173 */         break;
/*     */       case 3: 
/* 175 */         metadata = 4;
/*     */       }
/*     */       
/* 178 */       par1World.func_147465_d(i1, j1, k1, Blocks.field_150444_as, metadata, 2);
/* 179 */       TileEntitySign tileentitysign = (TileEntitySign)par1World.func_147438_o(i1, j1, k1);
/*     */       
/* 181 */       if (tileentitysign != null) {
/* 182 */         tileentitysign.field_145915_a = new String[] { "", text, "", "" };
/*     */       }
/*     */       
/* 185 */       return true;
/*     */     }
/* 187 */     return false;
/*     */   }
/*     */   
/*     */ 
/* 191 */   public static final WeightedRandomChestContent[] villageApothecaryChestContents = { new WeightedRandomChestContent(Items.field_151045_i, 0, 1, 2, 3), new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 10, 10), new WeightedRandomChestContent(Items.field_151043_k, 0, 1, 3, 5), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151034_e, 0, 1, 3, 15), new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemBatWool.damageValue, 1, 5, 5), new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemDogTongue.damageValue, 1, 5, 5), new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemRowanBerries.damageValue, 1, 5, 5), new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemSpectralDust.damageValue, 1, 1, 3), new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemMutandis.damageValue, 1, 5, 5), new WeightedRandomChestContent(Items.field_151119_aD, 0, 4, 10, 6), new WeightedRandomChestContent(Items.field_151144_bL, 0, 1, 1, 1), new WeightedRandomChestContent(net.minecraft.item.Item.func_150898_a(Blocks.field_150345_g), 0, 3, 7, 5), new WeightedRandomChestContent(Witchery.Items.DIVINER_WATER, 0, 1, 1, 1), new WeightedRandomChestContent(Witchery.Items.POPPET, Witchery.Items.POPPET.voodooPoppet.damageValue, 1, 1, 1), new WeightedRandomChestContent(Witchery.Items.POPPET, Witchery.Items.POPPET.firePoppet.damageValue, 1, 1, 1) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean hasMadeChest;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final String CHEST_KEY = "WITCApocChest";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 218 */     super.func_143012_a(par1NBTTagCompound);
/* 219 */     par1NBTTagCompound.func_74757_a("WITCApocChest", this.hasMadeChest);
/*     */   }
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 224 */     super.func_143011_b(par1NBTTagCompound);
/* 225 */     this.hasMadeChest = par1NBTTagCompound.func_74767_n("WITCApocChest");
/*     */   }
/*     */   
/*     */   protected int func_74888_b(int par1)
/*     */   {
/* 230 */     return Config.instance().apothecaryID;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentVillageApothecary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */