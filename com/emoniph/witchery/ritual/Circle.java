/*     */ package com.emoniph.witchery.ritual;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class Circle
/*     */ {
/*     */   int numRitualGlyphs;
/*     */   int numOtherwhereGlyphs;
/*     */   int numInfernalGlyphs;
/*     */   final int requiredGlyphs;
/*     */   
/*     */   public Circle(int requiredGlyphs)
/*     */   {
/*  18 */     this.requiredGlyphs = requiredGlyphs;
/*     */   }
/*     */   
/*     */   public Circle(int numRitualGlyphs, int numOtherwhereGlyphs, int numInfernalGlyphs) {
/*  22 */     this.requiredGlyphs = (numRitualGlyphs + numOtherwhereGlyphs + numInfernalGlyphs);
/*  23 */     this.numRitualGlyphs = numRitualGlyphs;
/*  24 */     this.numOtherwhereGlyphs = numOtherwhereGlyphs;
/*  25 */     this.numInfernalGlyphs = numInfernalGlyphs;
/*     */   }
/*     */   
/*     */   public void addGlyph(World world, int posX, int posY, int posZ) {
/*  29 */     addGlyph(world, posX, posY, posZ, false);
/*     */   }
/*     */   
/*     */   public void addGlyph(World world, int posX, int posY, int posZ, boolean remove) {
/*  33 */     if (this.requiredGlyphs > 0) {
/*  34 */       Block blockID = world.func_147439_a(posX, posY, posZ);
/*  35 */       boolean found = false;
/*  36 */       if (Witchery.Blocks.GLYPH_RITUAL == blockID) {
/*  37 */         this.numRitualGlyphs += 1;
/*  38 */         found = true;
/*  39 */       } else if (Witchery.Blocks.GLYPH_OTHERWHERE == blockID) {
/*  40 */         this.numOtherwhereGlyphs += 1;
/*  41 */         found = true;
/*  42 */       } else if (Witchery.Blocks.GLYPH_INFERNAL == blockID) {
/*  43 */         this.numInfernalGlyphs += 1;
/*  44 */         found = true;
/*     */       }
/*     */       
/*  47 */       if ((remove) && (found)) {
/*  48 */         world.func_147468_f(posX, posY, posZ);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeIfRequired(ArrayList<Circle> circlesToFind) {
/*  54 */     if (isComplete()) {
/*  55 */       for (int i = 0; i < circlesToFind.size(); i++) {
/*  56 */         if (isMatch((Circle)circlesToFind.get(i))) {
/*  57 */           circlesToFind.remove(i);
/*  58 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isMatch(Circle other) {
/*  65 */     return (this.numRitualGlyphs == other.numRitualGlyphs) && (this.numOtherwhereGlyphs == other.numOtherwhereGlyphs) && (this.numInfernalGlyphs == other.numInfernalGlyphs);
/*     */   }
/*     */   
/*     */   public boolean isComplete()
/*     */   {
/*  70 */     return this.requiredGlyphs == getGlyphCount();
/*     */   }
/*     */   
/*     */   private int getGlyphCount() {
/*  74 */     return this.numRitualGlyphs + this.numOtherwhereGlyphs + this.numInfernalGlyphs;
/*     */   }
/*     */   
/*     */   public int getRadius() {
/*  78 */     return (this.requiredGlyphs + 2) / 6 + 1;
/*     */   }
/*     */   
/*     */   public int getExclusiveMetadataValue() {
/*  82 */     if (this.numRitualGlyphs == this.requiredGlyphs)
/*  83 */       return 1;
/*  84 */     if (this.numOtherwhereGlyphs == this.requiredGlyphs)
/*  85 */       return 2;
/*  86 */     if (this.numInfernalGlyphs == this.requiredGlyphs) {
/*  87 */       return 3;
/*     */     }
/*  89 */     return 0;
/*     */   }
/*     */   
/*     */   public int getTextureIndex()
/*     */   {
/*  94 */     int size = getGlyphCount();
/*  95 */     if (size == 40)
/*  96 */       return getExclusiveMetadataValue() - 1;
/*  97 */     if (size == 28) {
/*  98 */       return getExclusiveMetadataValue() + 3 - 1;
/*     */     }
/* 100 */     return getExclusiveMetadataValue() + 6 - 1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/Circle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */