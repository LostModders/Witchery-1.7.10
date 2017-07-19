/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ 
/*     */ public class ComponentVillageWatchTower
/*     */   extends StructureVillagePieces.Village
/*     */ {
/*     */   public static ComponentVillageWatchTower construct(StructureVillagePieces.Start start, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
/*     */   {
/*  22 */     StructureBoundingBox bounds = StructureBoundingBox.func_78889_a(p1, p2, p3, 0, 0, 0, 8, 23, 8, p4);
/*     */     
/*  24 */     return (func_74895_a(bounds)) && (StructureComponent.func_74883_a(pieces, bounds) == null) ? new ComponentVillageWatchTower(start, p5, rand, bounds, p4) : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ComponentVillageWatchTower() {}
/*     */   
/*     */ 
/*     */   public ComponentVillageWatchTower(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int coordMode)
/*     */   {
/*  33 */     super(start, componentType);
/*  34 */     this.field_74885_f = coordMode;
/*  35 */     this.field_74887_e = bounds;
/*     */   }
/*     */   
/*     */   public boolean func_74875_a(World world, Random rand, StructureBoundingBox bounds) {
/*  39 */     int height = 23;
/*  40 */     if (this.field_143015_k < 0) {
/*  41 */       this.field_143015_k = func_74889_b(world, bounds);
/*     */       
/*  43 */       if (this.field_143015_k < 0) {
/*  44 */         return true;
/*     */       }
/*     */       
/*  47 */       this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 23 - 1, 0);
/*     */     }
/*     */     
/*  50 */     func_151549_a(world, bounds, 2, 0, 2, 6, 17, 6, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  52 */     func_151549_a(world, bounds, 3, 13, 3, 5, 14, 5, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/*  54 */     func_151549_a(world, bounds, 2, 16, 3, 6, 17, 5, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*  55 */     func_151549_a(world, bounds, 3, 16, 2, 5, 17, 6, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/*     */ 
/*  58 */     func_151549_a(world, bounds, 3, 15, 1, 5, 16, 1, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  59 */     func_151549_a(world, bounds, 4, 14, 1, 4, 17, 1, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  61 */     func_151549_a(world, bounds, 3, 15, 7, 5, 16, 7, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  62 */     func_151549_a(world, bounds, 4, 14, 7, 4, 17, 7, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  64 */     func_151549_a(world, bounds, 1, 15, 3, 1, 16, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  65 */     func_151549_a(world, bounds, 1, 14, 4, 1, 17, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  67 */     func_151549_a(world, bounds, 7, 15, 3, 7, 16, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  68 */     func_151549_a(world, bounds, 7, 14, 4, 7, 17, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*     */ 
/*  71 */     func_151550_a(world, Blocks.field_150422_aJ, 0, 2, 18, 2, bounds);
/*  72 */     func_151550_a(world, Blocks.field_150422_aJ, 0, 2, 18, 6, bounds);
/*  73 */     func_151550_a(world, Blocks.field_150422_aJ, 0, 6, 18, 2, bounds);
/*  74 */     func_151550_a(world, Blocks.field_150422_aJ, 0, 6, 18, 6, bounds);
/*     */     
/*  76 */     func_151549_a(world, bounds, 2, 19, 2, 6, 19, 6, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  77 */     func_151549_a(world, bounds, 3, 20, 3, 5, 20, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  78 */     func_151550_a(world, Blocks.field_150344_f, 0, 4, 19, 4, bounds);
/*     */     
/*  80 */     int n = func_151555_a(Blocks.field_150476_ad, 3);
/*  81 */     int s = func_151555_a(Blocks.field_150476_ad, 2);
/*  82 */     int w = func_151555_a(Blocks.field_150476_ad, 0);
/*  83 */     int e = func_151555_a(Blocks.field_150476_ad, 1);
/*     */     
/*  85 */     func_151550_a(world, Blocks.field_150476_ad, n, 2, 19, 1, bounds);
/*  86 */     func_151550_a(world, Blocks.field_150476_ad, n, 3, 19, 1, bounds);
/*  87 */     func_151550_a(world, Blocks.field_150476_ad, n, 4, 19, 1, bounds);
/*  88 */     func_151550_a(world, Blocks.field_150476_ad, n, 5, 19, 1, bounds);
/*  89 */     func_151550_a(world, Blocks.field_150476_ad, n, 6, 19, 1, bounds);
/*     */     
/*  91 */     func_151550_a(world, Blocks.field_150476_ad, n, 2, 20, 2, bounds);
/*  92 */     func_151550_a(world, Blocks.field_150476_ad, n, 3, 20, 2, bounds);
/*  93 */     func_151550_a(world, Blocks.field_150476_ad, n, 4, 20, 2, bounds);
/*  94 */     func_151550_a(world, Blocks.field_150476_ad, n, 5, 20, 2, bounds);
/*  95 */     func_151550_a(world, Blocks.field_150476_ad, n, 6, 20, 2, bounds);
/*     */     
/*  97 */     func_151550_a(world, Blocks.field_150476_ad, n, 3, 21, 3, bounds);
/*  98 */     func_151550_a(world, Blocks.field_150476_ad, n, 4, 21, 3, bounds);
/*  99 */     func_151550_a(world, Blocks.field_150476_ad, n, 5, 21, 3, bounds);
/*     */     
/* 101 */     func_151550_a(world, Blocks.field_150476_ad, s, 2, 19, 7, bounds);
/* 102 */     func_151550_a(world, Blocks.field_150476_ad, s, 3, 19, 7, bounds);
/* 103 */     func_151550_a(world, Blocks.field_150476_ad, s, 4, 19, 7, bounds);
/* 104 */     func_151550_a(world, Blocks.field_150476_ad, s, 5, 19, 7, bounds);
/* 105 */     func_151550_a(world, Blocks.field_150476_ad, s, 6, 19, 7, bounds);
/*     */     
/* 107 */     func_151550_a(world, Blocks.field_150476_ad, s, 2, 20, 6, bounds);
/* 108 */     func_151550_a(world, Blocks.field_150476_ad, s, 3, 20, 6, bounds);
/* 109 */     func_151550_a(world, Blocks.field_150476_ad, s, 4, 20, 6, bounds);
/* 110 */     func_151550_a(world, Blocks.field_150476_ad, s, 5, 20, 6, bounds);
/* 111 */     func_151550_a(world, Blocks.field_150476_ad, s, 6, 20, 6, bounds);
/*     */     
/* 113 */     func_151550_a(world, Blocks.field_150476_ad, s, 3, 21, 5, bounds);
/* 114 */     func_151550_a(world, Blocks.field_150476_ad, s, 4, 21, 5, bounds);
/* 115 */     func_151550_a(world, Blocks.field_150476_ad, s, 5, 21, 5, bounds);
/*     */     
/* 117 */     func_151550_a(world, Blocks.field_150476_ad, w, 1, 19, 2, bounds);
/* 118 */     func_151550_a(world, Blocks.field_150476_ad, w, 1, 19, 3, bounds);
/* 119 */     func_151550_a(world, Blocks.field_150476_ad, w, 1, 19, 4, bounds);
/* 120 */     func_151550_a(world, Blocks.field_150476_ad, w, 1, 19, 5, bounds);
/* 121 */     func_151550_a(world, Blocks.field_150476_ad, w, 1, 19, 6, bounds);
/*     */     
/* 123 */     func_151550_a(world, Blocks.field_150476_ad, w, 2, 20, 2, bounds);
/* 124 */     func_151550_a(world, Blocks.field_150476_ad, w, 2, 20, 3, bounds);
/* 125 */     func_151550_a(world, Blocks.field_150476_ad, w, 2, 20, 4, bounds);
/* 126 */     func_151550_a(world, Blocks.field_150476_ad, w, 2, 20, 5, bounds);
/* 127 */     func_151550_a(world, Blocks.field_150476_ad, w, 2, 20, 6, bounds);
/*     */     
/* 129 */     func_151550_a(world, Blocks.field_150476_ad, w, 3, 21, 3, bounds);
/* 130 */     func_151550_a(world, Blocks.field_150476_ad, w, 3, 21, 4, bounds);
/* 131 */     func_151550_a(world, Blocks.field_150476_ad, w, 3, 21, 5, bounds);
/*     */     
/* 133 */     func_151550_a(world, Blocks.field_150476_ad, e, 7, 19, 2, bounds);
/* 134 */     func_151550_a(world, Blocks.field_150476_ad, e, 7, 19, 3, bounds);
/* 135 */     func_151550_a(world, Blocks.field_150476_ad, e, 7, 19, 4, bounds);
/* 136 */     func_151550_a(world, Blocks.field_150476_ad, e, 7, 19, 5, bounds);
/* 137 */     func_151550_a(world, Blocks.field_150476_ad, e, 7, 19, 6, bounds);
/*     */     
/* 139 */     func_151550_a(world, Blocks.field_150476_ad, e, 6, 20, 2, bounds);
/* 140 */     func_151550_a(world, Blocks.field_150476_ad, e, 6, 20, 3, bounds);
/* 141 */     func_151550_a(world, Blocks.field_150476_ad, e, 6, 20, 4, bounds);
/* 142 */     func_151550_a(world, Blocks.field_150476_ad, e, 6, 20, 5, bounds);
/* 143 */     func_151550_a(world, Blocks.field_150476_ad, e, 6, 20, 6, bounds);
/*     */     
/* 145 */     func_151550_a(world, Blocks.field_150476_ad, e, 5, 21, 3, bounds);
/* 146 */     func_151550_a(world, Blocks.field_150476_ad, e, 5, 21, 4, bounds);
/* 147 */     func_151550_a(world, Blocks.field_150476_ad, e, 5, 21, 5, bounds);
/*     */     
/* 149 */     func_151550_a(world, Blocks.field_150376_bx, 0, 4, 22, 4, bounds);
/*     */     
/*     */ 
/* 152 */     func_151549_a(world, bounds, 4, 1, 2, 4, 2, 3, Blocks.field_150350_a, Blocks.field_150350_a, false);
/* 153 */     func_151550_a(world, Blocks.field_150478_aa, 0, 3, 2, 4, bounds);
/* 154 */     func_151550_a(world, Blocks.field_150478_aa, 0, 5, 2, 4, bounds);
/* 155 */     func_151550_a(world, Blocks.field_150478_aa, 0, 4, 14, 3, bounds);
/* 156 */     func_151550_a(world, Blocks.field_150478_aa, 0, 4, 16, 4, bounds);
/*     */     
/*     */ 
/* 159 */     func_151549_a(world, bounds, 2, 6, 2, 2, 14, 2, Blocks.field_150350_a, Blocks.field_150350_a, false);
/* 160 */     func_151549_a(world, bounds, 6, 6, 2, 6, 14, 2, Blocks.field_150350_a, Blocks.field_150350_a, false);
/* 161 */     func_151549_a(world, bounds, 6, 6, 6, 6, 14, 6, Blocks.field_150350_a, Blocks.field_150350_a, false);
/* 162 */     func_151549_a(world, bounds, 2, 6, 6, 2, 14, 6, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/* 164 */     func_151549_a(world, bounds, 4, 6, 2, 4, 12, 2, Blocks.field_150350_a, Blocks.field_150350_a, false);
/* 165 */     func_151549_a(world, bounds, 4, 6, 6, 4, 12, 6, Blocks.field_150350_a, Blocks.field_150350_a, false);
/* 166 */     func_151549_a(world, bounds, 6, 6, 4, 6, 12, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
/* 167 */     func_151549_a(world, bounds, 2, 6, 4, 2, 12, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/* 169 */     func_151549_a(world, bounds, 2, 9, 2, 6, 9, 6, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*     */ 
/* 172 */     func_151549_a(world, bounds, 3, 0, 1, 5, 4, 1, Blocks.field_150347_e, Blocks.field_150347_e, false);
/* 173 */     func_151549_a(world, bounds, 4, 1, 1, 4, 3, 1, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/* 175 */     func_151549_a(world, bounds, 3, 0, 7, 5, 4, 7, Blocks.field_150347_e, Blocks.field_150347_e, false);
/* 176 */     func_151549_a(world, bounds, 4, 1, 7, 4, 3, 7, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/* 178 */     func_151549_a(world, bounds, 1, 0, 3, 1, 4, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/* 179 */     func_151549_a(world, bounds, 1, 1, 4, 1, 3, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/* 181 */     func_151549_a(world, bounds, 7, 0, 3, 7, 4, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/* 182 */     func_151549_a(world, bounds, 7, 1, 4, 7, 3, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*     */     
/* 184 */     int offset = func_151555_a(Blocks.field_150468_ap, 3);
/*     */     
/* 186 */     for (int i = 1; i <= 12; i++) {
/* 187 */       func_151550_a(world, Blocks.field_150468_ap, offset, 4, i, 4, bounds);
/*     */     }
/*     */     
/* 190 */     for (int i = 13; i <= 15; i++) {
/* 191 */       func_151550_a(world, Blocks.field_150468_ap, offset, 3, i, 5, bounds);
/*     */     }
/*     */     
/* 194 */     if (!this.hasMadeChest) {
/* 195 */       int i = func_74862_a(13);
/* 196 */       int j = func_74865_a(5, 5);
/* 197 */       int k = func_74873_b(5, 5);
/*     */       
/* 199 */       if (bounds.func_78890_b(j, i, k)) {
/* 200 */         this.hasMadeChest = true;
/* 201 */         func_74879_a(world, bounds, rand, 5, 13, 5, villageTowerChestContents, 2 + rand.nextInt(4));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 206 */     for (int j = 1; j < 7; j++) {
/* 207 */       for (int k = 1; k < 7; k++) {
/* 208 */         func_74871_b(world, k, 23, j, bounds);
/* 209 */         func_151554_b(world, Blocks.field_150347_e, 0, k, -1, j, bounds);
/*     */       }
/*     */     }
/*     */     
/* 213 */     spawnGuards(world, bounds, 4, 16, 4, 3);
/* 214 */     return true;
/*     */   }
/*     */   
/* 217 */   public static final WeightedRandomChestContent[] villageTowerChestContents = { new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151034_e, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151115_aP, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151036_c, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151040_l, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151030_Z, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151028_Y, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151165_aa, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151167_ab, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151027_R, 0, 1, 1, 6), new WeightedRandomChestContent(Items.field_151024_Q, 0, 1, 1, 6), new WeightedRandomChestContent(Items.field_151026_S, 0, 1, 1, 6), new WeightedRandomChestContent(Items.field_151021_T, 0, 1, 1, 6), new WeightedRandomChestContent(Items.field_151031_f, 0, 1, 1, 8), new WeightedRandomChestContent(Items.field_151032_g, 0, 2, 6, 8), new WeightedRandomChestContent(Items.field_151141_av, 0, 1, 1, 3), new WeightedRandomChestContent(Items.field_151138_bX, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151136_bY, 0, 1, 1, 1) };
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
/*     */   private int guardsSpawned;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound nbtRoot)
/*     */   {
/* 241 */     super.func_143012_a(nbtRoot);
/* 242 */     nbtRoot.func_74757_a("Chest", this.hasMadeChest);
/* 243 */     nbtRoot.func_74768_a("Guards", this.guardsSpawned);
/*     */   }
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound nbtRoot) {
/* 247 */     super.func_143011_b(nbtRoot);
/* 248 */     this.hasMadeChest = nbtRoot.func_74767_n("Chest");
/* 249 */     this.guardsSpawned = nbtRoot.func_74762_e("Guards");
/*     */   }
/*     */   
/*     */   private void spawnGuards(World world, StructureBoundingBox bounds, int x, int y, int z, int count) {
/* 253 */     if (this.guardsSpawned < count) {
/* 254 */       for (int guardNumber = this.guardsSpawned; guardNumber <= count; guardNumber++) {
/* 255 */         int j1 = func_74865_a(x, z);
/* 256 */         int k1 = func_74862_a(y);
/* 257 */         int l1 = func_74873_b(x, z);
/*     */         
/* 259 */         if (!bounds.func_78890_b(j1, k1, l1)) {
/*     */           break;
/*     */         }
/*     */         
/* 263 */         this.guardsSpawned += 1;
/* 264 */         EntityVillageGuard guard = new EntityVillageGuard(world);
/* 265 */         guard.func_70012_b(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
/* 266 */         guard.func_110163_bv();
/* 267 */         guard.func_110161_a(null);
/* 268 */         world.func_72838_d(guard);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentVillageWatchTower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */