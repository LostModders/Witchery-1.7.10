/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*     */ 
/*     */ public class ComponentVillageKeep
/*     */   extends StructureVillagePieces.Village
/*     */ {
/*     */   public static ComponentVillageKeep construct(StructureVillagePieces.Start start, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
/*     */   {
/*  22 */     StructureBoundingBox bounds = StructureBoundingBox.func_78889_a(p1, p2, p3, 0, 0, 0, 16, 26, 16, p4);
/*     */     
/*  24 */     return (func_74895_a(bounds)) && (StructureComponent.func_74883_a(pieces, bounds) == null) ? new ComponentVillageKeep(start, p5, rand, bounds, p4) : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComponentVillageKeep() {}
/*     */   
/*     */ 
/*     */   public ComponentVillageKeep(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int coordMode)
/*     */   {
/*  33 */     super(start, componentType);
/*  34 */     this.field_74885_f = coordMode;
/*  35 */     this.field_74887_e = bounds;
/*     */   }
/*     */   
/*     */   private void fill(World world, StructureBoundingBox bounds, int x, int y, int z, int w, int h, int d, Block block)
/*     */   {
/*  40 */     func_151549_a(world, bounds, x, y, z, x + w - 1, y + h - 1, z + d - 1, block, block, false);
/*     */   }
/*     */   
/*     */   public boolean func_74875_a(World world, Random rand, StructureBoundingBox bounds) {
/*  44 */     int height = 26;
/*  45 */     if (this.field_143015_k < 0) {
/*  46 */       this.field_143015_k = func_74889_b(world, bounds);
/*     */       
/*  48 */       if (this.field_143015_k < 0) {
/*  49 */         return true;
/*     */       }
/*     */       
/*  52 */       this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 26 - 1, 0);
/*     */     }
/*     */     
/*     */ 
/*  56 */     func_151549_a(world, bounds, 1, 1, 1, 14, 26, 14, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/*     */ 
/*  59 */     drawTower(world, bounds, 0, 0);
/*     */     
/*  61 */     drawTower(world, bounds, 8, 4);
/*     */     
/*     */ 
/*  64 */     fill(world, bounds, 7, 0, 2, 3, 1, 3, Blocks.field_150347_e);
/*  65 */     fill(world, bounds, 7, 4, 3, 3, 1, 2, Blocks.field_150347_e);
/*  66 */     fill(world, bounds, 7, 5, 2, 3, 1, 1, Blocks.field_150347_e);
/*  67 */     func_151550_a(world, Blocks.field_150347_e, 0, 8, 6, 2, bounds);
/*     */     
/*     */ 
/*  70 */     int meta = func_151555_a(Blocks.field_150364_r, 8);
/*  71 */     for (int x = 7; x <= 9; x++) {
/*  72 */       func_151550_a(world, Blocks.field_150364_r, meta, x, 4, 2, bounds);
/*     */     }
/*     */     
/*  75 */     fill(world, bounds, 7, 3, 3, 3, 1, 1, Blocks.field_150422_aJ);
/*     */     
/*  77 */     func_151550_a(world, Blocks.field_150333_U, 11, 7, 3, 2, bounds);
/*  78 */     func_151550_a(world, Blocks.field_150333_U, 11, 7, 3, 4, bounds);
/*     */     
/*  80 */     func_151550_a(world, Blocks.field_150333_U, 11, 9, 3, 2, bounds);
/*  81 */     func_151550_a(world, Blocks.field_150333_U, 11, 9, 3, 4, bounds);
/*     */     
/*  83 */     meta = func_151555_a(Blocks.field_150446_ar, 3);
/*  84 */     int meta2 = func_151555_a(Blocks.field_150446_ar, 2);
/*  85 */     for (x = 7; x <= 9; x++) {
/*  86 */       func_151550_a(world, Blocks.field_150446_ar, meta, x, 0, 1, bounds);
/*  87 */       func_151550_a(world, Blocks.field_150446_ar, meta2, x, 0, 4, bounds);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  92 */     fill(world, bounds, 2, 0, 9, 4, 16, 1, Blocks.field_150347_e);
/*  93 */     fill(world, bounds, 2, 0, 14, 4, 16, 1, Blocks.field_150347_e);
/*  94 */     fill(world, bounds, 1, 0, 10, 1, 16, 4, Blocks.field_150347_e);
/*  95 */     fill(world, bounds, 6, 0, 10, 1, 16, 4, Blocks.field_150347_e);
/*     */     
/*     */ 
/*  98 */     fill(world, bounds, 2, 0, 10, 4, 1, 4, Blocks.field_150347_e);
/*  99 */     fill(world, bounds, 1, 4, 9, 6, 1, 6, Blocks.field_150347_e);
/* 100 */     fill(world, bounds, 1, 9, 9, 6, 1, 6, Blocks.field_150347_e);
/* 101 */     fill(world, bounds, 1, 14, 9, 6, 1, 6, Blocks.field_150347_e);
/*     */     
/*     */ 
/* 104 */     fill(world, bounds, 3, 16, 9, 2, 1, 1, Blocks.field_150347_e);
/* 105 */     fill(world, bounds, 3, 16, 14, 2, 1, 1, Blocks.field_150347_e);
/*     */     
/* 107 */     fill(world, bounds, 1, 16, 11, 1, 1, 2, Blocks.field_150347_e);
/* 108 */     fill(world, bounds, 6, 16, 11, 1, 1, 2, Blocks.field_150347_e);
/*     */     
/*     */ 
/* 111 */     fill(world, bounds, 3, 1, 14, 2, 3, 1, Blocks.field_150364_r);
/* 112 */     fill(world, bounds, 1, 1, 11, 1, 3, 2, Blocks.field_150364_r);
/*     */     
/*     */ 
/* 115 */     fill(world, bounds, 3, 11, 9, 2, 2, 1, Blocks.field_150411_aY);
/*     */     
/* 117 */     fill(world, bounds, 3, 6, 14, 2, 2, 1, Blocks.field_150411_aY);
/* 118 */     fill(world, bounds, 3, 11, 14, 2, 2, 1, Blocks.field_150411_aY);
/*     */     
/* 120 */     fill(world, bounds, 1, 6, 11, 1, 2, 2, Blocks.field_150411_aY);
/* 121 */     fill(world, bounds, 1, 11, 11, 1, 2, 2, Blocks.field_150411_aY);
/*     */     
/* 123 */     fill(world, bounds, 6, 11, 11, 1, 2, 2, Blocks.field_150411_aY);
/*     */     
/*     */ 
/* 126 */     fill(world, bounds, 4, 1, 9, 1, 2, 1, Blocks.field_150350_a);
/* 127 */     fill(world, bounds, 4, 5, 9, 1, 2, 1, Blocks.field_150350_a);
/*     */     
/* 129 */     fill(world, bounds, 6, 1, 11, 1, 2, 1, Blocks.field_150350_a);
/* 130 */     fill(world, bounds, 6, 5, 11, 1, 2, 1, Blocks.field_150350_a);
/*     */     
/* 132 */     func_151550_a(world, Blocks.field_150364_r, func_151555_a(Blocks.field_150364_r, 8), 4, 7, 9, bounds);
/* 133 */     func_151550_a(world, Blocks.field_150364_r, func_151555_a(Blocks.field_150364_r, 4), 6, 7, 11, bounds);
/*     */     
/*     */ 
/* 136 */     meta = func_151555_a(Blocks.field_150468_ap, 2);
/* 137 */     for (int h = 1; h <= 14; h++) {
/* 138 */       func_151550_a(world, Blocks.field_150468_ap, meta, 2, h, 10, bounds);
/*     */     }
/*     */     
/* 141 */     func_151550_a(world, Blocks.field_150478_aa, 0, 2, 2, 13, bounds);
/* 142 */     func_151550_a(world, Blocks.field_150478_aa, 0, 2, 6, 13, bounds);
/* 143 */     func_151550_a(world, Blocks.field_150478_aa, 0, 2, 11, 13, bounds);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 148 */     fill(world, bounds, 11, 0, 9, 3, 19, 1, Blocks.field_150347_e);
/* 149 */     fill(world, bounds, 11, 0, 13, 3, 19, 1, Blocks.field_150347_e);
/* 150 */     fill(world, bounds, 10, 0, 10, 1, 19, 3, Blocks.field_150347_e);
/* 151 */     fill(world, bounds, 14, 0, 10, 1, 19, 3, Blocks.field_150347_e);
/*     */     
/*     */ 
/* 154 */     fill(world, bounds, 11, 0, 10, 3, 1, 3, Blocks.field_150347_e);
/* 155 */     fill(world, bounds, 10, 4, 9, 5, 1, 5, Blocks.field_150347_e);
/* 156 */     fill(world, bounds, 10, 9, 9, 5, 1, 5, Blocks.field_150347_e);
/* 157 */     fill(world, bounds, 10, 14, 9, 5, 1, 5, Blocks.field_150347_e);
/* 158 */     fill(world, bounds, 10, 19, 9, 5, 1, 5, Blocks.field_150347_e);
/*     */     
/*     */ 
/* 161 */     fill(world, bounds, 12, 1, 13, 1, 3, 1, Blocks.field_150364_r);
/* 162 */     fill(world, bounds, 14, 1, 11, 1, 3, 1, Blocks.field_150364_r);
/*     */     
/*     */ 
/* 165 */     fill(world, bounds, 12, 6, 13, 1, 2, 1, Blocks.field_150411_aY);
/* 166 */     fill(world, bounds, 12, 11, 9, 1, 2, 1, Blocks.field_150411_aY);
/* 167 */     fill(world, bounds, 12, 16, 9, 1, 2, 1, Blocks.field_150411_aY);
/*     */     
/* 169 */     fill(world, bounds, 12, 11, 13, 1, 2, 1, Blocks.field_150411_aY);
/* 170 */     fill(world, bounds, 12, 16, 13, 1, 2, 1, Blocks.field_150411_aY);
/*     */     
/* 172 */     fill(world, bounds, 14, 6, 11, 1, 2, 1, Blocks.field_150411_aY);
/* 173 */     fill(world, bounds, 14, 11, 11, 1, 2, 1, Blocks.field_150411_aY);
/* 174 */     fill(world, bounds, 14, 16, 11, 1, 2, 1, Blocks.field_150411_aY);
/*     */     
/* 176 */     fill(world, bounds, 10, 11, 11, 1, 2, 1, Blocks.field_150411_aY);
/* 177 */     fill(world, bounds, 10, 16, 11, 1, 2, 1, Blocks.field_150411_aY);
/*     */     
/*     */ 
/* 180 */     fill(world, bounds, 12, 5, 9, 1, 2, 1, Blocks.field_150350_a);
/* 181 */     fill(world, bounds, 12, 1, 9, 1, 2, 1, Blocks.field_150350_a);
/* 182 */     fill(world, bounds, 10, 5, 11, 1, 2, 1, Blocks.field_150350_a);
/* 183 */     fill(world, bounds, 10, 1, 11, 1, 2, 1, Blocks.field_150350_a);
/*     */     
/* 185 */     func_151550_a(world, Blocks.field_150364_r, func_151555_a(Blocks.field_150364_r, 8), 12, 7, 9, bounds);
/* 186 */     func_151550_a(world, Blocks.field_150364_r, func_151555_a(Blocks.field_150364_r, 4), 10, 7, 11, bounds);
/*     */     
/*     */ 
/* 189 */     meta = func_151555_a(Blocks.field_150468_ap, 2);
/* 190 */     for (int h = 1; h <= 14; h++) {
/* 191 */       func_151550_a(world, Blocks.field_150468_ap, meta, 11, h, 10, bounds);
/*     */     }
/*     */     
/* 194 */     func_151550_a(world, Blocks.field_150478_aa, 0, 11, 2, 12, bounds);
/* 195 */     func_151550_a(world, Blocks.field_150478_aa, 0, 11, 6, 12, bounds);
/* 196 */     func_151550_a(world, Blocks.field_150478_aa, 0, 11, 11, 12, bounds);
/* 197 */     func_151550_a(world, Blocks.field_150478_aa, 0, 11, 16, 12, bounds);
/*     */     
/*     */ 
/* 200 */     func_151550_a(world, Blocks.field_150364_r, 0, 11, 19, 10, bounds);
/*     */     
/*     */ 
/* 203 */     fill(world, bounds, 10, 20, 9, 5, 2, 5, Blocks.field_150344_f);
/* 204 */     fill(world, bounds, 11, 22, 10, 3, 2, 3, Blocks.field_150344_f);
/* 205 */     fill(world, bounds, 12, 24, 11, 1, 2, 1, Blocks.field_150344_f);
/* 206 */     fill(world, bounds, 11, 20, 10, 3, 2, 3, Blocks.field_150350_a);
/*     */     
/* 208 */     int n = func_151555_a(Blocks.field_150476_ad, 3);
/* 209 */     int s = func_151555_a(Blocks.field_150476_ad, 2);
/* 210 */     int w = func_151555_a(Blocks.field_150476_ad, 0);
/* 211 */     int e = func_151555_a(Blocks.field_150476_ad, 1);
/*     */     
/* 213 */     for (x = 9; x <= 15; x++) {
/* 214 */       func_151550_a(world, Blocks.field_150476_ad, n, x, 20, 8, bounds);
/* 215 */       func_151550_a(world, Blocks.field_150476_ad, s, x, 20, 14, bounds);
/*     */     }
/*     */     
/* 218 */     for (x = 10; x <= 14; x++) {
/* 219 */       func_151550_a(world, Blocks.field_150476_ad, n, x, 22, 9, bounds);
/* 220 */       func_151550_a(world, Blocks.field_150476_ad, s, x, 22, 13, bounds);
/*     */     }
/*     */     
/* 223 */     for (x = 11; x <= 13; x++) {
/* 224 */       func_151550_a(world, Blocks.field_150476_ad, n, x, 24, 10, bounds);
/* 225 */       func_151550_a(world, Blocks.field_150476_ad, s, x, 24, 12, bounds);
/*     */     }
/*     */     
/*     */ 
/* 229 */     for (int z = 9; z <= 13; z++) {
/* 230 */       func_151550_a(world, Blocks.field_150476_ad, w, 9, 20, z, bounds);
/* 231 */       func_151550_a(world, Blocks.field_150476_ad, e, 15, 20, z, bounds);
/*     */     }
/*     */     
/* 234 */     for (z = 10; z <= 12; z++) {
/* 235 */       func_151550_a(world, Blocks.field_150476_ad, w, 10, 22, z, bounds);
/* 236 */       func_151550_a(world, Blocks.field_150476_ad, e, 14, 22, z, bounds);
/*     */     }
/*     */     
/* 239 */     func_151550_a(world, Blocks.field_150476_ad, w, 11, 24, 11, bounds);
/* 240 */     func_151550_a(world, Blocks.field_150476_ad, e, 13, 24, 11, bounds);
/*     */     
/*     */ 
/* 243 */     fill(world, bounds, 7, 0, 11, 3, 1, 2, Blocks.field_150347_e);
/* 244 */     fill(world, bounds, 7, 4, 11, 3, 1, 1, Blocks.field_150347_e);
/* 245 */     fill(world, bounds, 7, 1, 12, 3, 5, 1, Blocks.field_150347_e);
/*     */     
/* 247 */     func_151550_a(world, Blocks.field_150347_e, 0, 8, 6, 12, bounds);
/*     */     
/* 249 */     fill(world, bounds, 7, 1, 12, 1, 4, 1, Blocks.field_150364_r);
/* 250 */     func_151550_a(world, Blocks.field_150478_aa, 0, 8, 2, 11, bounds);
/* 251 */     fill(world, bounds, 9, 1, 12, 1, 4, 1, Blocks.field_150364_r);
/*     */     
/* 253 */     meta = func_151555_a(Blocks.field_150446_ar, 3);
/* 254 */     for (x = 7; x <= 9; x++) {
/* 255 */       func_151550_a(world, Blocks.field_150446_ar, meta, x, 0, 10, bounds);
/*     */     }
/*     */     
/* 258 */     func_151550_a(world, Blocks.field_150333_U, 11, 7, 3, 11, bounds);
/*     */     
/* 260 */     func_151550_a(world, Blocks.field_150333_U, 11, 9, 3, 11, bounds);
/*     */     
/*     */ 
/* 263 */     fill(world, bounds, 3, 0, 6, 2, 1, 3, Blocks.field_150347_e);
/* 264 */     fill(world, bounds, 4, 4, 6, 1, 1, 3, Blocks.field_150347_e);
/* 265 */     fill(world, bounds, 3, 1, 6, 1, 5, 3, Blocks.field_150347_e);
/*     */     
/* 267 */     func_151550_a(world, Blocks.field_150347_e, 0, 3, 6, 7, bounds);
/*     */     
/* 269 */     fill(world, bounds, 3, 1, 6, 1, 4, 1, Blocks.field_150364_r);
/* 270 */     func_151550_a(world, Blocks.field_150478_aa, 0, 4, 2, 7, bounds);
/* 271 */     fill(world, bounds, 3, 1, 8, 1, 4, 1, Blocks.field_150364_r);
/*     */     
/* 273 */     meta = func_151555_a(Blocks.field_150446_ar, 1);
/* 274 */     for (z = 6; z <= 8; z++) {
/* 275 */       func_151550_a(world, Blocks.field_150446_ar, meta, 5, 0, z, bounds);
/*     */     }
/*     */     
/* 278 */     func_151550_a(world, Blocks.field_150333_U, 11, 4, 3, 6, bounds);
/*     */     
/* 280 */     func_151550_a(world, Blocks.field_150333_U, 11, 4, 3, 8, bounds);
/*     */     
/*     */ 
/* 283 */     fill(world, bounds, 12, 0, 6, 2, 1, 3, Blocks.field_150347_e);
/* 284 */     fill(world, bounds, 12, 4, 6, 1, 1, 3, Blocks.field_150347_e);
/* 285 */     fill(world, bounds, 13, 1, 6, 1, 5, 3, Blocks.field_150347_e);
/*     */     
/* 287 */     func_151550_a(world, Blocks.field_150347_e, 0, 13, 6, 7, bounds);
/*     */     
/* 289 */     fill(world, bounds, 13, 1, 6, 1, 4, 1, Blocks.field_150364_r);
/* 290 */     func_151550_a(world, Blocks.field_150478_aa, 0, 12, 2, 7, bounds);
/* 291 */     fill(world, bounds, 13, 1, 8, 1, 4, 1, Blocks.field_150364_r);
/*     */     
/* 293 */     meta = func_151555_a(Blocks.field_150446_ar, 0);
/* 294 */     for (z = 6; z <= 8; z++) {
/* 295 */       func_151550_a(world, Blocks.field_150446_ar, meta, 11, 0, z, bounds);
/*     */     }
/*     */     
/* 298 */     func_151550_a(world, Blocks.field_150333_U, 11, 12, 3, 6, bounds);
/* 299 */     func_151550_a(world, Blocks.field_150333_U, 11, 12, 3, 8, bounds);
/*     */     
/* 301 */     if (!this.hasMadeChest) {
/* 302 */       x = 13;
/* 303 */       z = 12;
/* 304 */       int y = 20;
/* 305 */       int i = func_74862_a(y);
/* 306 */       int j = func_74865_a(x, z);
/* 307 */       int k = func_74873_b(x, z);
/*     */       
/* 309 */       if (bounds.func_78890_b(j, i, k)) {
/* 310 */         this.hasMadeChest = true;
/* 311 */         func_74879_a(world, bounds, rand, x, y, z, villageTowerChestContents, 3 + rand.nextInt(6));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 316 */     for (int j = 1; j < 15; j++) {
/* 317 */       for (int k = 1; k < 15; k++) {
/* 318 */         func_74871_b(world, k, 26, j, bounds);
/* 319 */         func_151554_b(world, Blocks.field_150347_e, 0, k, -1, j, bounds);
/*     */       }
/*     */     }
/*     */     
/* 323 */     spawnGuards(world, bounds, 7, 1, 7, 3);
/* 324 */     spawnGuards(world, bounds, 5, 10, 4, 4);
/* 325 */     spawnGuards(world, bounds, 13, 10, 4, 5);
/* 326 */     return true;
/*     */   }
/*     */   
/*     */   public void drawTower(World world, StructureBoundingBox bounds, int offsetX, int flipX)
/*     */   {
/* 331 */     fill(world, bounds, 3 + offsetX, 0, 1, 3, 11, 1, Blocks.field_150347_e);
/* 332 */     fill(world, bounds, 3 + offsetX, 0, 5, 3, 11, 1, Blocks.field_150347_e);
/* 333 */     fill(world, bounds, 2 + offsetX, 0, 2, 1, 11, 3, Blocks.field_150347_e);
/* 334 */     fill(world, bounds, 6 + offsetX, 0, 2, 1, 11, 3, Blocks.field_150347_e);
/*     */     
/*     */ 
/* 337 */     fill(world, bounds, 3 + offsetX, 0, 2, 3, 1, 3, Blocks.field_150347_e);
/* 338 */     fill(world, bounds, 2 + offsetX, 4, 1, 5, 1, 5, Blocks.field_150347_e);
/* 339 */     fill(world, bounds, 2 + offsetX, 9, 1, 5, 1, 5, Blocks.field_150347_e);
/*     */     
/*     */ 
/* 342 */     func_151550_a(world, Blocks.field_150347_e, 0, 4 + offsetX, 11, 1, bounds);
/* 343 */     func_151550_a(world, Blocks.field_150347_e, 0, 4 + offsetX, 11, 5, bounds);
/* 344 */     func_151550_a(world, Blocks.field_150347_e, 0, 2 + offsetX, 11, 3, bounds);
/* 345 */     func_151550_a(world, Blocks.field_150347_e, 0, 6 + offsetX, 11, 3, bounds);
/*     */     
/*     */ 
/* 348 */     fill(world, bounds, 4 + offsetX, 1, 1, 1, 3, 1, Blocks.field_150364_r);
/* 349 */     fill(world, bounds, 2 + offsetX + flipX, 1, 3, 1, 3, 1, Blocks.field_150364_r);
/*     */     
/*     */ 
/* 352 */     fill(world, bounds, 4 + offsetX, 6, 1, 1, 2, 1, Blocks.field_150411_aY);
/* 353 */     fill(world, bounds, 2 + offsetX + flipX, 6, 3, 1, 2, 1, Blocks.field_150411_aY);
/*     */     
/*     */ 
/* 356 */     func_151550_a(world, Blocks.field_150364_r, func_151555_a(Blocks.field_150364_r, 8), 4 + offsetX, 7, 5, bounds);
/* 357 */     func_151550_a(world, Blocks.field_150364_r, func_151555_a(Blocks.field_150364_r, 4), 6 + offsetX - flipX, 7, 3, bounds);
/* 358 */     fill(world, bounds, 4 + offsetX, 5, 5, 1, 2, 1, Blocks.field_150350_a);
/*     */     
/* 360 */     fill(world, bounds, 4 + offsetX, 1, 5, 1, 2, 1, Blocks.field_150350_a);
/*     */     
/*     */ 
/* 363 */     fill(world, bounds, 6 + offsetX - flipX, 5, 3, 1, 2, 1, Blocks.field_150350_a);
/*     */     
/*     */ 
/* 366 */     int meta = func_151555_a(Blocks.field_150468_ap, 2);
/* 367 */     for (int h = 1; h <= 9; h++) {
/* 368 */       func_151550_a(world, Blocks.field_150468_ap, meta, 3 + offsetX, h, 2, bounds);
/*     */     }
/*     */     
/* 371 */     func_151550_a(world, Blocks.field_150478_aa, 0, 3 + offsetX, 2, 4, bounds);
/* 372 */     func_151550_a(world, Blocks.field_150478_aa, 0, 3 + offsetX, 6, 4, bounds);
/*     */   }
/*     */   
/*     */   protected int func_151555_a(Block block, int meta) {
/* 376 */     if (block == Blocks.field_150364_r) {
/* 377 */       int rawMeta = meta / 4;
/* 378 */       if (rawMeta == 0) {
/* 379 */         return meta;
/*     */       }
/* 381 */       switch (this.field_74885_f)
/*     */       {
/*     */       case 0: 
/*     */       case 2: 
/* 385 */         return rawMeta == 2 ? 4 : 8;
/*     */       }
/*     */       
/*     */       
/*     */ 
/*     */ 
/* 391 */       return rawMeta == 1 ? 8 : 4;
/*     */     }
/*     */     
/* 394 */     return super.func_151555_a(block, meta);
/*     */   }
/*     */   
/*     */ 
/* 398 */   public static final WeightedRandomChestContent[] villageTowerChestContents = { new WeightedRandomChestContent(Items.field_151043_k, 0, 1, 6, 10), new WeightedRandomChestContent(Items.field_151074_bl, 0, 1, 15, 20), new WeightedRandomChestContent(Items.field_151006_E, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151010_B, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151171_ah, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151169_ag, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151149_ai, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151151_aj, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151125_bZ, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151136_bY, 0, 1, 1, 1) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean hasMadeChest;
/*     */   
/*     */ 
/*     */ 
/*     */   private int guardsSpawned;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound nbtRoot)
/*     */   {
/* 414 */     super.func_143012_a(nbtRoot);
/* 415 */     nbtRoot.func_74757_a("Chest", this.hasMadeChest);
/* 416 */     nbtRoot.func_74768_a("Guards", this.guardsSpawned);
/*     */   }
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound nbtRoot) {
/* 420 */     super.func_143011_b(nbtRoot);
/* 421 */     this.hasMadeChest = nbtRoot.func_74767_n("Chest");
/* 422 */     this.guardsSpawned = nbtRoot.func_74762_e("Guards");
/*     */   }
/*     */   
/*     */   private void spawnGuards(World world, StructureBoundingBox bounds, int x, int y, int z, int count) {
/* 426 */     if (this.guardsSpawned < count) {
/* 427 */       for (int guardNumber = this.guardsSpawned; guardNumber <= count; guardNumber++) {
/* 428 */         int j1 = func_74865_a(x, z);
/* 429 */         int k1 = func_74862_a(y);
/* 430 */         int l1 = func_74873_b(x, z);
/*     */         
/* 432 */         if (!bounds.func_78890_b(j1, k1, l1)) {
/*     */           break;
/*     */         }
/*     */         
/* 436 */         this.guardsSpawned += 1;
/* 437 */         EntityVillageGuard guard = new EntityVillageGuard(world);
/* 438 */         guard.func_70012_b(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
/* 439 */         guard.func_110163_bv();
/* 440 */         guard.func_110161_a(null);
/* 441 */         world.func_72838_d(guard);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentVillageKeep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */