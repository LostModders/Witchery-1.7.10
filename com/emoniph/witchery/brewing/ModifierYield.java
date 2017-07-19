/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ public class ModifierYield {
/*    */   private int bonus;
/*    */   private int penalty;
/*    */   
/*  7 */   public ModifierYield(int modifier) { if (modifier < 0) {
/*  8 */       this.penalty = Math.abs(modifier);
/*  9 */     } else if (modifier > 0) {
/* 10 */       this.bonus = modifier;
/*    */     }
/*    */   }
/*    */   
/*    */   public int getYieldModification() {
/* 15 */     return this.penalty - Math.min(this.bonus, this.penalty);
/*    */   }
/*    */   
/*    */   public void applyTo(ModifierYield counter) {
/* 19 */     counter.bonus += this.bonus;
/* 20 */     counter.penalty += this.penalty;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/ModifierYield.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */