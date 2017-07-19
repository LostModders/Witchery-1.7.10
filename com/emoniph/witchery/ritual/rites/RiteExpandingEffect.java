/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*     */ import com.emoniph.witchery.familiar.Familiar;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class RiteExpandingEffect extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   protected final int maxRadius;
/*     */   protected final int height;
/*     */   protected final boolean curse;
/*     */   
/*     */   public RiteExpandingEffect(int radius, int height, boolean curse)
/*     */   {
/*  24 */     this.maxRadius = radius;
/*  25 */     this.height = height;
/*  26 */     this.curse = curse;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  31 */     steps.add(new StepExpansion(this, intialStage));
/*     */   }
/*     */   
/*     */   public abstract void doBlockAction(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityPlayer paramEntityPlayer, boolean paramBoolean);
/*     */   
/*     */   public abstract boolean doRadiusAction(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityPlayer paramEntityPlayer, boolean paramBoolean);
/*     */   
/*  38 */   public boolean isComplete(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, long ticks, boolean fullyExpanded, boolean enhanced) { return fullyExpanded; }
/*     */   
/*     */   private static class StepExpansion
/*     */     extends RitualStep
/*     */   {
/*     */     private final RiteExpandingEffect rite;
/*  44 */     private int stage = 0;
/*     */     private boolean activated;
/*     */     
/*     */     public StepExpansion(RiteExpandingEffect rite, int initialStage) {
/*  48 */       super();
/*  49 */       this.rite = rite;
/*  50 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  55 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  60 */       if (!this.activated) {
/*  61 */         if (ticks % 20L != 0L) {
/*  62 */           return RitualStep.Result.STARTING;
/*     */         }
/*  64 */         this.activated = true;
/*  65 */         SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  70 */       if (!world.field_72995_K)
/*     */       {
/*  72 */         if (ticks % 5L == 0L) {
/*  73 */           this.stage += 1;
/*     */           
/*  75 */           if ((this.stage == 1) && (this.rite.curse)) {
/*  76 */             EntityWitchHunter.blackMagicPerformed(ritual.getInitiatingPlayer(world));
/*     */           }
/*     */           
/*  79 */           int height = this.rite.height;
/*  80 */           float maxRadius = this.rite.maxRadius + 2 * ritual.covenSize;
/*     */           
/*  82 */           EntityPlayer player = ritual.getInitiatingPlayer(world);
/*     */           
/*  84 */           int currentRadius = this.stage + 3;
/*     */           
/*  86 */           boolean enhanced = (player != null) && (Familiar.hasActiveCurseMasteryFamiliar(player));
/*     */           
/*  88 */           if ((currentRadius <= maxRadius) && 
/*  89 */             (!applyCircle(world, posX, posZ, posY, currentRadius, height, player, enhanced))) {
/*  90 */             return RitualStep.Result.ABORTED;
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*  95 */           if (this.stage <= 250) {} return this.rite.isComplete(world, posX, posY, posZ, currentRadius, player, ticks, currentRadius >= maxRadius, enhanced) ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
/*     */         }
/*  97 */         return RitualStep.Result.UPKEEP;
/*     */       }
/*     */       
/* 100 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     protected boolean applyCircle(World world, int x0, int z0, int y0, int radius, int height, EntityPlayer player, boolean enhanced)
/*     */     {
/* 105 */       if (!this.rite.doRadiusAction(world, x0, y0, z0, radius, player, enhanced)) {
/* 106 */         return false;
/*     */       }
/*     */       
/* 109 */       int x = radius;
/* 110 */       int z = 0;
/* 111 */       int radiusError = 1 - x;
/*     */       
/* 113 */       while (x >= z) {
/* 114 */         drawPixel(world, x + x0, z + z0, y0, height, radius, player, enhanced);
/* 115 */         drawPixel(world, z + x0, x + z0, y0, height, radius, player, enhanced);
/* 116 */         drawPixel(world, -x + x0, z + z0, y0, height, radius, player, enhanced);
/* 117 */         drawPixel(world, -z + x0, x + z0, y0, height, radius, player, enhanced);
/* 118 */         drawPixel(world, -x + x0, -z + z0, y0, height, radius, player, enhanced);
/* 119 */         drawPixel(world, -z + x0, -x + z0, y0, height, radius, player, enhanced);
/* 120 */         drawPixel(world, x + x0, -z + z0, y0, height, radius, player, enhanced);
/* 121 */         drawPixel(world, z + x0, -x + z0, y0, height, radius, player, enhanced);
/*     */         
/* 123 */         z++;
/*     */         
/* 125 */         if (radiusError < 0) {
/* 126 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 128 */           x--;
/* 129 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */       
/* 133 */       return true;
/*     */     }
/*     */     
/*     */     protected void drawPixel(World world, int x, int z, int y, int height, int currentRadius, EntityPlayer player, boolean enhanced) {
/* 137 */       for (int i = 0; i < height; i++) {
/* 138 */         if ((world.func_147439_a(x, y + i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y + i + 1, z))) {
/* 139 */           if (this.rite.curse) {
/* 140 */             ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + i + 1, 0.5D + z, 1.0D, 1.0D, 16);
/*     */           } else {
/* 142 */             ParticleEffect.SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + i + 1, 0.5D + z, 1.0D, 1.0D, 16);
/*     */           }
/*     */           
/* 145 */           this.rite.doBlockAction(world, x, y + i, z, currentRadius, player, enhanced);
/* 146 */           break;
/*     */         }
/* 148 */         if ((i > 0) && (world.func_147439_a(x, y - i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y - i + 1, z))) {
/* 149 */           if (this.rite.curse) {
/* 150 */             ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5D + x, y - i + 1, 0.5D + z, 1.0D, 1.0D, 32);
/*     */           } else {
/* 152 */             ParticleEffect.SPELL.send(SoundEffect.NONE, world, 0.5D + x, y - i + 1, 0.5D + z, 1.0D, 1.0D, 32);
/*     */           }
/*     */           
/* 155 */           this.rite.doBlockAction(world, x, y - i, z, currentRadius, player, enhanced);
/* 156 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteExpandingEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */