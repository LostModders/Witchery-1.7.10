/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.util.EntityPosition;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModifiersEffect
/*    */ {
/*    */   public int strength;
/*    */   public int strengthPenalty;
/*    */   public int duration;
/*    */   public boolean noParticles;
/* 17 */   public final EffectLevelCounter effectLevel = new EffectLevelCounter();
/*    */   
/*    */   public boolean inverted;
/*    */   
/*    */   public boolean disableBlockTarget;
/*    */   
/*    */   public boolean disableEntityTarget;
/*    */   
/*    */   public boolean strengthCeilingDisabled;
/*    */   
/*    */   public boolean powerhCeilingDisabled;
/*    */   public final double powerScalingFactor;
/*    */   public final double durationScalingFactor;
/*    */   public final boolean isGlancing;
/*    */   public final EntityPosition impactLocation;
/*    */   public final boolean ritualised;
/*    */   public final int covenSize;
/*    */   public final EntityPlayer caster;
/*    */   public int totalStrength;
/*    */   public int totalDuration;
/*    */   public int permenance;
/*    */   public boolean protectedFromNegativePotions;
/*    */   
/*    */   public ModifiersEffect(double powerScalingFactor, double durationScalingFactor, boolean glancing, EntityPosition position, boolean ritualised, int covenSize, EntityPlayer caster)
/*    */   {
/* 42 */     this.powerScalingFactor = powerScalingFactor;
/* 43 */     this.durationScalingFactor = durationScalingFactor;
/* 44 */     this.isGlancing = glancing;
/* 45 */     this.impactLocation = position;
/* 46 */     this.ritualised = ritualised;
/* 47 */     this.caster = caster;
/* 48 */     this.covenSize = covenSize;
/*    */   }
/*    */   
/*    */   public ModifiersEffect(double powerScalingFactor, double durationScalingFactor, boolean glancing, EntityPosition position, ModifiersImpact impactModifiers)
/*    */   {
/* 53 */     this(powerScalingFactor, durationScalingFactor, glancing, position, impactModifiers.ritualised, impactModifiers.covenSize, impactModifiers.thrower);
/*    */   }
/*    */   
/*    */ 
/* 57 */   private static final int[] covenToMaxStrength = { 1, 1, 2, 2, 3, 3, 4 };
/*    */   
/*    */   public int getStrength() {
/* 60 */     if (this.ritualised) {
/* 61 */       return Math.min(Math.max(this.strength - this.strengthPenalty, 0), covenToMaxStrength[Math.min(this.covenSize, covenToMaxStrength.length - 1)]);
/*    */     }
/*    */     
/* 64 */     return Math.max(this.strength - this.strengthPenalty, 0);
/*    */   }
/*    */   
/*    */   public int getModifiedDuration(int ticks)
/*    */   {
/* 69 */     return Math.min(MathHelper.func_76143_f(this.durationScalingFactor * ticks * (this.duration + 1)), Integer.MAX_VALUE);
/*    */   }
/*    */   
/*    */   public void reset() {
/* 73 */     this.inverted = false;
/* 74 */     this.strength = 0;
/* 75 */     this.duration = 0;
/* 76 */     this.noParticles = false;
/*    */   }
/*    */   
/*    */   public void increaseStrength(int strength) {
/* 80 */     if ((this.totalStrength < 7) || (this.powerhCeilingDisabled)) {
/* 81 */       this.strength += strength;
/* 82 */       this.totalStrength += strength;
/*    */     }
/*    */   }
/*    */   
/*    */   public void increaseDuration(int duration) {
/* 87 */     if ((this.totalDuration < 7) || (this.powerhCeilingDisabled)) {
/* 88 */       this.duration += duration;
/* 89 */       this.totalDuration += duration;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/ModifiersEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */