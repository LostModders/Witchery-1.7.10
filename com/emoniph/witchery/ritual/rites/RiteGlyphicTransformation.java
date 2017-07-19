/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class RiteGlyphicTransformation
/*     */   extends Rite
/*     */ {
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  27 */     steps.add(new StepGlyphicTransformation(this));
/*     */   }
/*     */   
/*     */   private static class StepGlyphicTransformation extends RitualStep
/*     */   {
/*     */     private final RiteGlyphicTransformation rite;
/*     */     
/*     */     public StepGlyphicTransformation(RiteGlyphicTransformation rite) {
/*  35 */       super();
/*  36 */       this.rite = rite;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  41 */       if (ticks % 30L != 0L) {
/*  42 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  45 */       if (!world.field_72995_K) {
/*  46 */         double RADIUS = 4.0D;
/*  47 */         List items = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(posX - 4.0D, posY - 2, posZ - 4.0D, posX + 4.0D, posY + 2, posZ + 4.0D));
/*     */         
/*  49 */         int whiteChalk = 0;
/*  50 */         int purpleChalk = 0;
/*  51 */         int redChalk = 0;
/*     */         
/*  53 */         for (Object obj : items) {
/*  54 */           EntityItem item = (EntityItem)obj;
/*  55 */           ItemStack stack = item.func_92059_d();
/*  56 */           if ((redChalk == 0) && (purpleChalk == 0) && (stack.func_77969_a(new ItemStack(Witchery.Items.CHALK_RITUAL, 1, 0)))) {
/*  57 */             boolean first = whiteChalk == 0;
/*  58 */             whiteChalk += stack.field_77994_a;
/*  59 */             if (first) {
/*  60 */               stack.field_77994_a -= 1;
/*  61 */               if (stack.field_77994_a <= 0) {
/*  62 */                 world.func_72900_e(item);
/*     */               }
/*     */             }
/*  65 */           } else if ((redChalk == 0) && (whiteChalk == 0) && (stack.func_77969_a(new ItemStack(Witchery.Items.CHALK_OTHERWHERE, 1, 0)))) {
/*  66 */             boolean first = purpleChalk == 0;
/*  67 */             purpleChalk += stack.field_77994_a;
/*  68 */             if (first) {
/*  69 */               stack.field_77994_a -= 1;
/*  70 */               if (stack.field_77994_a <= 0) {
/*  71 */                 world.func_72900_e(item);
/*     */               }
/*     */             }
/*     */           } else {
/*  75 */             if ((purpleChalk != 0) || (whiteChalk != 0) || (!stack.func_77969_a(new ItemStack(Witchery.Items.CHALK_INFERNAL, 1, 0)))) continue;
/*  76 */             boolean first = redChalk == 0;
/*  77 */             redChalk += stack.field_77994_a;
/*  78 */             if (first) {
/*  79 */               stack.field_77994_a -= 1;
/*  80 */               if (stack.field_77994_a <= 0) {
/*  81 */                 world.func_72900_e(item);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*  88 */           ParticleEffect.SMOKE.send(SoundEffect.RANDOM_POP, item, 1.0D, 1.0D, 16);
/*     */         }
/*     */         
/*  91 */         Block blockID = Blocks.field_150350_a;
/*  92 */         int size = 0;
/*     */         
/*  94 */         if ((whiteChalk == 0) && (redChalk == 0) && (purpleChalk == 0))
/*  95 */           return RitualStep.Result.ABORTED_REFUND;
/*  96 */         if (redChalk > 0) {
/*  97 */           blockID = Witchery.Blocks.GLYPH_INFERNAL;
/*  98 */           size = Math.min(redChalk, 3);
/*  99 */         } else if (purpleChalk > 0) {
/* 100 */           blockID = Witchery.Blocks.GLYPH_OTHERWHERE;
/* 101 */           size = Math.min(purpleChalk, 3);
/* 102 */         } else if (whiteChalk > 0) {
/* 103 */           blockID = Witchery.Blocks.GLYPH_RITUAL;
/* 104 */           size = Math.min(whiteChalk, 3);
/*     */         }
/*     */         
/* 107 */         int a = 1;
/* 108 */         int b = 2;
/* 109 */         int c = 3;
/* 110 */         int _ = 0;
/*     */         
/* 112 */         int[][] PATTERN = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0 }, { 0, 0, 0, 3, 0, 0, 2, 2, 2, 2, 2, 0, 0, 3, 0, 0, 0 }, { 0, 0, 3, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 3, 0, 0 }, { 0, 3, 0, 0, 2, 0, 0, 1, 1, 1, 0, 0, 2, 0, 0, 3, 0 }, { 0, 3, 0, 2, 0, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0, 3, 0 }, { 0, 3, 0, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2, 0, 3, 0 }, { 0, 3, 0, 2, 0, 1, 0, 0, 4, 0, 0, 1, 0, 2, 0, 3, 0 }, { 0, 3, 0, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2, 0, 3, 0 }, { 0, 3, 0, 2, 0, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0, 3, 0 }, { 0, 3, 0, 0, 2, 0, 0, 1, 1, 1, 0, 0, 2, 0, 0, 3, 0 }, { 0, 0, 3, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 3, 0, 0 }, { 0, 0, 0, 3, 0, 0, 2, 2, 2, 2, 2, 0, 0, 3, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 131 */         int offsetZ = (PATTERN.length - 1) / 2;
/* 132 */         for (int z = 0; z < PATTERN.length - 1; z++) {
/* 133 */           int worldZ = posZ - offsetZ + z;
/* 134 */           int offsetX = (PATTERN[z].length - 1) / 2;
/* 135 */           for (int x = 0; x < PATTERN[z].length; x++) {
/* 136 */             int worldX = posX - offsetX + x;
/* 137 */             int item = PATTERN[(PATTERN.length - 1 - z)][x];
/*     */             
/* 139 */             if (item == size) {
/* 140 */               Block currentBlockID = world.func_147439_a(worldX, posY, worldZ);
/* 141 */               if (((currentBlockID == Witchery.Blocks.GLYPH_INFERNAL) || (currentBlockID == Witchery.Blocks.GLYPH_OTHERWHERE) || (currentBlockID == Witchery.Blocks.GLYPH_RITUAL)) && (currentBlockID != blockID))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/* 146 */                 int meta = world.func_72805_g(worldX, posY, worldZ);
/* 147 */                 world.func_147465_d(worldX, posY, worldZ, blockID, meta, 3);
/* 148 */                 ParticleEffect.SMOKE.send(SoundEffect.NONE, world, worldX, posY + 1, worldZ, 0.5D, 1.0D, 16);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 154 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteGlyphicTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */