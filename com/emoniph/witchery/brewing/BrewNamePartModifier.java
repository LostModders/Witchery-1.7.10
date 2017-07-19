/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ public class BrewNamePartModifier extends BrewNamePart
/*    */ {
/*    */   int dispersalExtent;
/*    */   int dispersalDuration;
/*    */   int strength;
/*    */   int duration;
/*    */   boolean inverted;
/*    */   boolean removePowerCeiling;
/*    */   
/*    */   public BrewNamePartModifier(int strength, int duration, boolean invert, int dispersalExtent, int dispersalDuration)
/*    */   {
/* 14 */     this(strength, duration, invert, dispersalExtent, dispersalDuration, false);
/*    */   }
/*    */   
/*    */   public BrewNamePartModifier(int strength, int duration, boolean invert, int dispersalExtent, int dispersalDuration, boolean removeCeiling) {
/* 18 */     super("");
/* 19 */     this.strength = strength;
/* 20 */     this.duration = duration;
/* 21 */     this.inverted = invert;
/* 22 */     this.dispersalExtent = dispersalExtent;
/* 23 */     this.dispersalDuration = dispersalDuration;
/* 24 */     this.removePowerCeiling = removeCeiling;
/*    */   }
/*    */   
/*    */   public void applyTo(BrewNameBuilder nameBuilder)
/*    */   {
/* 29 */     nameBuilder.dispersalExtent += this.dispersalExtent;
/* 30 */     nameBuilder.dispersalDuration += this.dispersalDuration;
/* 31 */     nameBuilder.addStrength(this.strength);
/* 32 */     nameBuilder.addDuration(this.duration);
/* 33 */     if (this.inverted) {
/* 34 */       nameBuilder.inverted = true;
/*    */     }
/* 36 */     if (this.removePowerCeiling) {
/* 37 */       nameBuilder.removePowerCeiling = true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BrewNamePartModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */