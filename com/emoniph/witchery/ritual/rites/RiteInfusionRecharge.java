/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockAltar.TileEntityAltar;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteInfusionRecharge extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final float upkeepPowerCost;
/*     */   private final int charges;
/*     */   private final int radius;
/*     */   private final int ticksToLive;
/*     */   
/*     */   public RiteInfusionRecharge(int charges, int radius, float upkeepPowerCost, int ticksToLive)
/*     */   {
/*  31 */     this.charges = charges;
/*  32 */     this.radius = radius;
/*  33 */     this.upkeepPowerCost = upkeepPowerCost;
/*  34 */     this.ticksToLive = ticksToLive;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  39 */     steps.add(new StepInfusePlayers(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepInfusePlayers extends RitualStep
/*     */   {
/*     */     private final RiteInfusionRecharge rite;
/*  45 */     private boolean activated = false;
/*     */     protected int ticksSoFar;
/*     */     
/*     */     public StepInfusePlayers(RiteInfusionRecharge rite, int ticksSoFar) {
/*  49 */       super();
/*  50 */       this.rite = rite;
/*  51 */       this.ticksSoFar = ticksSoFar;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  56 */       return this.ticksSoFar;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  61 */       if (ticks % 20L != 0L) {
/*  62 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       int r;
/*  65 */       if (!world.field_72995_K) {
/*  66 */         if (this.rite.upkeepPowerCost > 0.0F) {
/*  67 */           IPowerSource powerSource = getPowerSource(world, posX, posY, posZ);
/*  68 */           if (powerSource == null) {
/*  69 */             return RitualStep.Result.ABORTED;
/*     */           }
/*     */           
/*  72 */           this.powerSourceCoord = powerSource.getLocation();
/*     */           
/*  74 */           if (!powerSource.consumePower(this.rite.upkeepPowerCost)) {
/*  75 */             return RitualStep.Result.ABORTED;
/*     */           }
/*     */         }
/*     */         
/*  79 */         if ((this.rite.ticksToLive > 0) && 
/*  80 */           (ticks % 20L == 0L) && (++this.ticksSoFar >= this.rite.ticksToLive)) {
/*  81 */           return RitualStep.Result.COMPLETED;
/*     */         }
/*     */         
/*     */ 
/*  85 */         r = this.rite.radius;
/*  86 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
/*     */         
/*  88 */         for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
/*  89 */           EntityPlayer player = (EntityPlayer)obj;
/*  90 */           if (Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= r) {
/*  91 */             int currentEnergy = Infusion.getCurrentEnergy(player);
/*  92 */             int maxEnergy = Infusion.getMaxEnergy(player);
/*  93 */             if (currentEnergy < maxEnergy) {
/*  94 */               Infusion.setCurrentEnergy(player, Math.min(currentEnergy + this.rite.charges, maxEnergy));
/*  95 */               ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_PLING, player, 1.0D, 2.0D, 8);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 100 */       return RitualStep.Result.UPKEEP;
/*     */     }
/*     */     
/*     */ 
/*     */     IPowerSource getPowerSource(World world, int posX, int posY, int posZ)
/*     */     {
/* 106 */       if ((this.powerSourceCoord == null) || (world.field_73012_v.nextInt(5) == 0)) {
/* 107 */         return findNewPowerSource(world, posX, posY, posZ);
/*     */       }
/* 109 */       TileEntity tileEntity = this.powerSourceCoord.getBlockTileEntity(world);
/* 110 */       if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
/* 111 */         return findNewPowerSource(world, posX, posY, posZ);
/*     */       }
/* 113 */       BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
/* 114 */       if (!altarTileEntity.isValid()) {
/* 115 */         return findNewPowerSource(world, posX, posY, posZ);
/*     */       }
/* 117 */       return altarTileEntity;
/*     */     }
/*     */     
/*     */ 
/*     */     Coord powerSourceCoord;
/*     */     
/*     */     static final int POWER_SOURCE_RADIUS = 16;
/*     */     private IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
/*     */     {
/* 126 */       List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
/* 127 */       return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteInfusionRecharge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */