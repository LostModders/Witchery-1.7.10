/*   */ package com.emoniph.witchery.brewing;
/*   */ 
/*   */ public class Probability {
/* 4 */   public static final Probability CERTAIN = new Probability(1.0D);
/*   */   private final double probability;
/*   */   
/*   */   public Probability(double propbability) {
/* 8 */     this.probability = propbability;
/*   */   }
/*   */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/Probability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */