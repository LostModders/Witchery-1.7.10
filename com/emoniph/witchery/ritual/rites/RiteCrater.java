/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.BlockProtect;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteCrater extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final int radius;
/*     */   private final int height;
/*     */   
/*     */   public RiteCrater(int radius, int height)
/*     */   {
/*  26 */     this.radius = radius;
/*  27 */     this.height = height;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  32 */     steps.add(new StepCrater(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepCrater extends RitualStep
/*     */   {
/*     */     private final RiteCrater rite;
/*  38 */     private int stage = 0;
/*     */     
/*     */     public StepCrater(RiteCrater rite, int initialStage) {
/*  41 */       super();
/*  42 */       this.rite = rite;
/*  43 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  48 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  53 */       if (ticks % 10L != 0L) {
/*  54 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  57 */       if (!world.field_72995_K)
/*     */       {
/*  59 */         if (++this.stage == 1) {
/*  60 */           ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 0.5D, 1.0D, 16);
/*     */         }
/*     */         
/*  63 */         int height = this.rite.height;
/*  64 */         float radius = this.rite.radius;
/*  65 */         if (this.stage <= height) {
/*  66 */           for (int y = 1; y <= this.stage; y++) {
/*  67 */             float r = radius - (height - this.stage - 1 + y) * radius / height;
/*  68 */             Log.instance().debug(String.format("Stage: %d, r=%f y=%d", new Object[] { Integer.valueOf(this.stage), Float.valueOf(r), Integer.valueOf(y) }));
/*  69 */             drawFilledCircle(world, posX, posZ, posY - y, Math.max((int)Math.ceil(r), 1), posY);
/*     */           }
/*     */         } else {
/*  72 */           return RitualStep.Result.COMPLETED;
/*     */         }
/*  74 */         return RitualStep.Result.UPKEEP;
/*     */       }
/*  76 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, int height) {
/*  80 */       int x = radius;
/*  81 */       int z = 0;
/*  82 */       int radiusError = 1 - x;
/*     */       
/*  84 */       while (x >= z) {
/*  85 */         drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius, height);
/*  86 */         drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius, height);
/*  87 */         drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius, height);
/*  88 */         drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius, height);
/*     */         
/*  90 */         z++;
/*     */         
/*  92 */         if (radiusError < 0) {
/*  93 */           radiusError += 2 * z + 1;
/*     */         } else {
/*  95 */           x--;
/*  96 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius, int midY) {
/* 102 */       int modX1 = (radius > 1) && (world.field_73012_v.nextInt(5) == 0) ? x1 + 1 : x1;
/* 103 */       int modX2 = (radius > 1) && (world.field_73012_v.nextInt(5) == 0) ? x2 - 1 : x2;
/* 104 */       boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
/*     */       
/* 106 */       for (int x = modX1; x <= modX2; x++) {
/* 107 */         drawPixel(world, x, z, y, midX, midY, midZ);
/*     */       }
/* 109 */       boolean done = true;
/*     */     }
/*     */     
/*     */     protected void drawPixel(World world, int x, int z, int y, int midX, int midY, int midZ) {
/* 113 */       if ((!world.field_72995_K) && 
/* 114 */         ((x != midX) || (z != midZ)) && ((y < midY - 3) || (Coord.distance(x, midY, z, midX, midY, midZ) > this.rite.radius - 3 - (midY - y)))) {
/* 115 */         Block blockID = world.func_147439_a(x, y, z);
/* 116 */         int blockMetadata = world.func_72805_g(x, y, z);
/* 117 */         if (BlockProtect.canBreak(x, y, z, world)) {
/* 118 */           world.func_147468_f(x, y, z);
/* 119 */           if ((blockID != Blocks.field_150350_a) && (blockID != Blocks.field_150348_b) && (blockID != Blocks.field_150346_d) && (blockID != Blocks.field_150349_c) && (blockID != Blocks.field_150354_m) && (blockID != Blocks.field_150322_A) && (blockID != Blocks.field_150351_n)) {
/* 120 */             ItemStack stack = new ItemStack(blockID, 1, blockMetadata);
/* 121 */             EntityItem entity = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, stack);
/* 122 */             world.func_72838_d(entity);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteCrater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */