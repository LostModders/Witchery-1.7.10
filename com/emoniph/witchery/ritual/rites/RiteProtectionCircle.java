/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockAltar.TileEntityAltar;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class RiteProtectionCircle extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final int radius;
/*     */   private final float upkeepPowerCost;
/*     */   private final int ticksToLive;
/*     */   
/*     */   public RiteProtectionCircle(int radius, float upkeepPowerCost, int ticksToLive)
/*     */   {
/*  26 */     this.radius = radius;
/*  27 */     this.upkeepPowerCost = upkeepPowerCost;
/*  28 */     this.ticksToLive = ticksToLive;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*     */   {
/*  33 */     steps.add(new ProtectionCircleStep(this, initialStage));
/*     */   }
/*     */   
/*     */   protected abstract void update(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */   
/*     */   private static class ProtectionCircleStep
/*     */     extends RitualStep
/*     */   {
/*     */     private final RiteProtectionCircle rite;
/*  42 */     private boolean activated = false;
/*     */     protected int ticksSoFar;
/*     */     
/*     */     public ProtectionCircleStep(RiteProtectionCircle rite, int ticksSoFar) {
/*  46 */       super();
/*  47 */       this.rite = rite;
/*  48 */       this.ticksSoFar = ticksSoFar;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  53 */       return this.ticksSoFar;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  58 */       if (!this.activated) {
/*  59 */         if (ticks % 20L != 0L) {
/*  60 */           return RitualStep.Result.STARTING;
/*     */         }
/*  62 */         this.activated = true;
/*  63 */         SoundEffect.RANDOM_FIZZ.playAt(world, this.sourceX, this.sourceY, this.sourceZ);
/*     */       }
/*     */       
/*     */ 
/*  67 */       if (this.rite.upkeepPowerCost > 0.0F) {
/*  68 */         IPowerSource powerSource = getPowerSource(world, this.sourceX, this.sourceY, this.sourceZ);
/*  69 */         if (powerSource == null) {
/*  70 */           return RitualStep.Result.ABORTED;
/*     */         }
/*     */         
/*  73 */         this.powerSourceCoord = powerSource.getLocation();
/*     */         
/*  75 */         if (!powerSource.consumePower(this.rite.upkeepPowerCost)) {
/*  76 */           return RitualStep.Result.ABORTED;
/*     */         }
/*     */       }
/*     */       
/*  80 */       if ((this.rite.ticksToLive > 0) && 
/*  81 */         (ticks % 20L == 0L) && (++this.ticksSoFar >= this.rite.ticksToLive)) {
/*  82 */         return RitualStep.Result.COMPLETED;
/*     */       }
/*     */       
/*     */ 
/*  86 */       this.rite.update(world, posX, posY, posZ, this.rite.radius, ticks);
/*     */       
/*  88 */       return RitualStep.Result.UPKEEP;
/*     */     }
/*     */     
/*     */ 
/*     */     IPowerSource getPowerSource(World world, int posX, int posY, int posZ)
/*     */     {
/*  94 */       if ((this.powerSourceCoord == null) || (world.field_73012_v.nextInt(5) == 0)) {
/*  95 */         return findNewPowerSource(world, posX, posY, posZ);
/*     */       }
/*  97 */       TileEntity tileEntity = this.powerSourceCoord.getBlockTileEntity(world);
/*  98 */       if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
/*  99 */         return findNewPowerSource(world, posX, posY, posZ);
/*     */       }
/* 101 */       BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
/* 102 */       if (!altarTileEntity.isValid()) {
/* 103 */         return findNewPowerSource(world, posX, posY, posZ);
/*     */       }
/* 105 */       return altarTileEntity;
/*     */     }
/*     */     
/*     */ 
/*     */     Coord powerSourceCoord;
/*     */     
/*     */     static final int POWER_SOURCE_RADIUS = 16;
/*     */     private IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
/*     */     {
/* 114 */       List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
/* 115 */       return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteProtectionCircle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */