/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RitePartEarth extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final int length;
/*     */   private final int width;
/*     */   private final int depth;
/*     */   
/*     */   public RitePartEarth(int length, int width, int depth)
/*     */   {
/*  20 */     this.length = length;
/*  21 */     this.width = width;
/*  22 */     this.depth = depth;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*     */   {
/*  27 */     steps.add(new StepPartEarth(this, initialStage));
/*     */   }
/*     */   
/*     */   private static class StepPartEarth extends RitualStep
/*     */   {
/*     */     private final RitePartEarth rite;
/*  33 */     private int stage = 0;
/*     */     Coord coord;
/*     */     
/*  36 */     public StepPartEarth(RitePartEarth rite, int initialStage) { super();
/*  37 */       this.rite = rite;
/*  38 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public int getCurrentStage()
/*     */     {
/*  46 */       return this.stage;
/*     */     }
/*     */     
/*  49 */     ArrayList<Coord> coords = new ArrayList();
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  53 */       if ((this.stage == 0) && (ticks % 20L != 0L)) {
/*  54 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  57 */       int length = this.rite.length;
/*  58 */       int width = this.rite.width + (ritual.covenSize > 2 ? 2 : 0);
/*  59 */       int depth = this.rite.depth;
/*     */       
/*  61 */       if ((++this.stage == 1) || (this.coords.isEmpty())) {
/*  62 */         this.coords.clear();
/*  63 */         this.coord = new Coord(posX, posY - 1, posZ);
/*  64 */         this.coords.add(this.coord);
/*  65 */         int last = (ritual.sacrificedItems != null) && (!ritual.sacrificedItems.isEmpty()) ? this.coord.getHeading(((RitualStep.SacrificedItem)ritual.sacrificedItems.get(0)).location) : 0;
/*  66 */         int probability = 20;
/*  67 */         for (int l = 0; l < length - 1; l++)
/*     */         {
/*  69 */           last = move(world, last, this.coord, Math.max(probability - l / 2, 6));
/*  70 */           this.coords.add(this.coord);
/*     */         }
/*     */       }
/*     */       
/*  74 */       int DELAY = 4;
/*  75 */       if (!world.field_72995_K) {
/*  76 */         Coord c = (Coord)this.coords.get(this.stage + 4);
/*  77 */         drawFilledCircle(world, c.x, c.z, c.y, width + (world.field_73012_v.nextInt(3) == 0 ? 1 : 0), depth - 2 + world.field_73012_v.nextInt(5));
/*     */       }
/*     */       
/*  80 */       return this.stage >= this.coords.size() - 4 - 1 ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
/*     */     }
/*     */     
/*     */     protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, int depth) {
/*  84 */       int x = radius;
/*  85 */       int z = 0;
/*  86 */       int radiusError = 1 - x;
/*     */       
/*  88 */       while (x >= z) {
/*  89 */         drawLine(world, -x + x0, x + x0, z + z0, y, depth);
/*  90 */         drawLine(world, -z + x0, z + x0, x + z0, y, depth);
/*  91 */         drawLine(world, -x + x0, x + x0, -z + z0, y, depth);
/*  92 */         drawLine(world, -z + x0, z + x0, -x + z0, y, depth);
/*     */         
/*  94 */         z++;
/*     */         
/*  96 */         if (radiusError < 0) {
/*  97 */           radiusError += 2 * z + 1;
/*     */         } else {
/*  99 */           x--;
/* 100 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawLine(World world, int x1, int x2, int z, int y, int depth) {
/* 106 */       for (int x = x1; x <= x2; x++) {
/* 107 */         drawPixel(world, x, z, y, depth);
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawPixel(World world, int x, int z, int y, int depth) {
/* 112 */       for (int d = 0; d < depth; d++) {
/* 113 */         if (com.emoniph.witchery.util.BlockProtect.canBreak(x, y - d, z, world)) {
/* 114 */           world.func_147468_f(x, y - d, z);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private int move(World world, int last, Coord coord, int probability) {
/* 120 */       int val = world.field_73012_v.nextInt(probability);
/* 121 */       switch (last) {
/*     */       case 0: 
/* 123 */         if (val == 0) {
/* 124 */           this.coord = coord.northEast();
/* 125 */           return 1; }
/* 126 */         if (val == 1) {
/* 127 */           this.coord = coord.northWest();
/* 128 */           return 7;
/*     */         }
/* 130 */         this.coord = coord.north();
/* 131 */         return 0;
/*     */       
/*     */       case 1: 
/* 134 */         if (val == 0) {
/* 135 */           this.coord = coord.north();
/* 136 */           return 0; }
/* 137 */         if (val == 1) {
/* 138 */           this.coord = coord.east();
/* 139 */           return 2;
/*     */         }
/* 141 */         this.coord = coord.northEast();
/* 142 */         return 1;
/*     */       
/*     */       case 2: 
/* 145 */         if (val == 0) {
/* 146 */           this.coord = coord.northEast();
/* 147 */           return 1; }
/* 148 */         if (val == 1) {
/* 149 */           this.coord = coord.southEast();
/* 150 */           return 3;
/*     */         }
/* 152 */         this.coord = coord.east();
/* 153 */         return 2;
/*     */       
/*     */       case 3: 
/* 156 */         if (val == 0) {
/* 157 */           this.coord = coord.east();
/* 158 */           return 2; }
/* 159 */         if (val == 1) {
/* 160 */           this.coord = coord.south();
/* 161 */           return 4;
/*     */         }
/* 163 */         this.coord = coord.southEast();
/* 164 */         return 3;
/*     */       
/*     */       case 4: 
/* 167 */         if (val == 0) {
/* 168 */           this.coord = coord.southEast();
/* 169 */           return 3; }
/* 170 */         if (val == 1) {
/* 171 */           this.coord = coord.southWest();
/* 172 */           return 5;
/*     */         }
/* 174 */         this.coord = coord.south();
/* 175 */         return 4;
/*     */       
/*     */       case 5: 
/* 178 */         if (val == 0) {
/* 179 */           this.coord = coord.south();
/* 180 */           return 4; }
/* 181 */         if (val == 1) {
/* 182 */           this.coord = coord.west();
/* 183 */           return 6;
/*     */         }
/* 185 */         this.coord = coord.southWest();
/* 186 */         return 5;
/*     */       
/*     */       case 6: 
/* 189 */         if (val == 0) {
/* 190 */           this.coord = coord.southWest();
/* 191 */           return 5; }
/* 192 */         if (val == 1) {
/* 193 */           this.coord = coord.northWest();
/* 194 */           return 7;
/*     */         }
/* 196 */         this.coord = coord.west();
/* 197 */         return 6;
/*     */       }
/*     */       
/*     */       
/* 201 */       if (val == 0) {
/* 202 */         this.coord = coord.west();
/* 203 */         return 6; }
/* 204 */       if (val == 1) {
/* 205 */         this.coord = coord.north();
/* 206 */         return 0;
/*     */       }
/* 208 */       this.coord = coord.northWest();
/* 209 */       return 7;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RitePartEarth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */