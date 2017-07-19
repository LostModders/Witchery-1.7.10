/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.entity.EntityToad;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ 
/*     */ public class RiteRainOfToads extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final int minRadius;
/*     */   private final int maxRadius;
/*     */   private final int bolts;
/*     */   
/*     */   public RiteRainOfToads(int minRadius, int maxRadius, int bolts)
/*     */   {
/*  26 */     this.minRadius = minRadius;
/*  27 */     this.maxRadius = maxRadius;
/*  28 */     this.bolts = bolts;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*     */   {
/*  33 */     steps.add(new StepRainOfToads(this, initialStage));
/*     */   }
/*     */   
/*     */   private static class StepRainOfToads extends RitualStep
/*     */   {
/*     */     private final RiteRainOfToads rite;
/*     */     private int stage;
/*     */     
/*     */     public StepRainOfToads(RiteRainOfToads rite, int initialStage) {
/*  42 */       super();
/*  43 */       this.rite = rite;
/*  44 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  49 */       return this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  54 */       if (ticks % 30L != 0L) {
/*  55 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  58 */       if (ritual.covenSize < 1) {
/*  59 */         EntityPlayer player = ritual.getInitiatingPlayer(world);
/*  60 */         SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/*  61 */         if (player != null) {
/*  62 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
/*     */         }
/*  64 */         return RitualStep.Result.ABORTED_REFUND;
/*     */       }
/*     */       
/*  67 */       this.stage += 1;
/*  68 */       switch (this.stage) {
/*     */       case 1: 
/*  70 */         spawnBolt(world, posX, posY, posZ);
/*  71 */         return RitualStep.Result.STARTING;
/*     */       case 2: 
/*  73 */         spawnBolt(world, posX, posY, posZ);
/*  74 */         return RitualStep.Result.STARTING;
/*     */       case 3: 
/*  76 */         spawnBolt(world, posX, posY, posZ);
/*  77 */         return RitualStep.Result.STARTING;
/*     */       case 4: 
/*  79 */         if (((world instanceof WorldServer)) && (!world.func_72896_J())) {
/*  80 */           WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
/*  81 */           int i = (300 + world.field_73012_v.nextInt(600)) * 20;
/*  82 */           worldinfo.func_76080_g(i);
/*  83 */           worldinfo.func_76084_b(true);
/*     */         }
/*  85 */         spawnBolt(world, posX, posY, posZ);
/*  86 */         return RitualStep.Result.STARTING;
/*     */       }
/*  88 */       int activeRadius = this.rite.maxRadius - this.rite.minRadius;
/*  89 */       for (int n = 0; n < world.field_73012_v.nextInt(this.rite.bolts) + 8; n++) {
/*  90 */         int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  91 */         if (ax > activeRadius) {
/*  92 */           ax += this.rite.minRadius * 2;
/*     */         }
/*  94 */         int x = posX - this.rite.maxRadius + ax;
/*     */         
/*  96 */         int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  97 */         if (az > activeRadius) {
/*  98 */           az += this.rite.minRadius * 2;
/*     */         }
/*     */         
/* 101 */         int z = posZ - this.rite.maxRadius + az;
/* 102 */         int y = world.func_72825_h(x, z);
/* 103 */         if (world.func_147437_c(x, y, z)) {
/* 104 */           EntityToad toad = new EntityToad(world);
/* 105 */           toad.func_70012_b(x, y + 8 + world.field_73012_v.nextInt(7), z, 0.0F, 0.0F);
/* 106 */           toad.setTimeToLive(30, true);
/* 107 */           world.func_72838_d(toad);
/*     */         }
/*     */       }
/* 110 */       return this.stage < 200 ? RitualStep.Result.UPKEEP : RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     private void spawnBolt(World world, int posX, int posY, int posZ)
/*     */     {
/* 115 */       int activeRadius = this.rite.maxRadius - this.rite.minRadius;
/* 116 */       int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 117 */       if (ax > activeRadius) {
/* 118 */         ax += this.rite.minRadius * 2;
/*     */       }
/* 120 */       int x = posX - this.rite.maxRadius + ax;
/*     */       
/* 122 */       int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 123 */       if (az > activeRadius) {
/* 124 */         az += this.rite.minRadius * 2;
/*     */       }
/*     */       
/* 127 */       int z = posZ - this.rite.maxRadius + az;
/*     */       
/* 129 */       EntityLightningBolt bolt = new EntityLightningBolt(world, x, posY, z);
/* 130 */       world.func_72942_c(bolt);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteRainOfToads.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */