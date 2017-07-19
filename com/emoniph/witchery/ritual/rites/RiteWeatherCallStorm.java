/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ 
/*     */ public class RiteWeatherCallStorm extends Rite
/*     */ {
/*     */   private final int minRadius;
/*     */   private final int maxRadius;
/*     */   private final int bolts;
/*     */   
/*     */   public RiteWeatherCallStorm(int minRadius, int maxRadius, int bolts)
/*     */   {
/*  22 */     this.minRadius = minRadius;
/*  23 */     this.maxRadius = maxRadius;
/*  24 */     this.bolts = bolts;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*     */   {
/*  29 */     steps.add(new StepWeatherCallStorm(this, initialStage));
/*     */   }
/*     */   
/*     */   private static class StepWeatherCallStorm extends RitualStep
/*     */   {
/*     */     private final RiteWeatherCallStorm rite;
/*     */     private int stage;
/*     */     
/*     */     public StepWeatherCallStorm(RiteWeatherCallStorm rite, int initialStage) {
/*  38 */       super();
/*  39 */       this.rite = rite;
/*  40 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  45 */       return this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  50 */       if (ticks % 30L != 0L) {
/*  51 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  54 */       if (!world.field_72995_K) {
/*  55 */         this.stage += 1;
/*     */         
/*  57 */         switch (this.stage) {
/*     */         case 1: 
/*  59 */           spawnBolt(world, posX, posY, posZ);
/*  60 */           break;
/*     */         case 2: 
/*  62 */           spawnBolt(world, posX, posY, posZ);
/*  63 */           break;
/*     */         case 3: 
/*  65 */           spawnBolt(world, posX, posY, posZ);
/*  66 */           spawnBolt(world, posX, posY, posZ);
/*  67 */           break;
/*     */         case 4: 
/*  69 */           if (((world instanceof WorldServer)) && (!world.func_72911_I())) {
/*  70 */             WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
/*  71 */             int i = (300 + world.field_73012_v.nextInt(600)) * 20;
/*  72 */             worldinfo.func_76080_g(i);
/*  73 */             worldinfo.func_76090_f(i);
/*  74 */             worldinfo.func_76084_b(true);
/*  75 */             worldinfo.func_76069_a(true);
/*     */           }
/*  77 */           spawnBolt(world, posX, posY, posZ);
/*  78 */           break;
/*     */         default: 
/*  80 */           for (int i = 0; i < world.field_73012_v.nextInt(4); i++) {
/*  81 */             spawnBolt(world, posX, posY, posZ);
/*  82 */             if (i > 0) {
/*  83 */               this.stage += 1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  88 */       return this.stage < this.rite.bolts ? RitualStep.Result.STARTING : RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     private void spawnBolt(World world, int posX, int posY, int posZ) {
/*  92 */       int activeRadius = this.rite.maxRadius - this.rite.minRadius;
/*  93 */       int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  94 */       if (ax > activeRadius) {
/*  95 */         ax += this.rite.minRadius * 2;
/*     */       }
/*  97 */       int x = posX - this.rite.maxRadius + ax;
/*     */       
/*  99 */       int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 100 */       if (az > activeRadius) {
/* 101 */         az += this.rite.minRadius * 2;
/*     */       }
/*     */       
/* 104 */       int z = posZ - this.rite.maxRadius + az;
/*     */       
/* 106 */       EntityLightningBolt bolt = new EntityLightningBolt(world, x, posY, z);
/* 107 */       world.func_72942_c(bolt);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteWeatherCallStorm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */