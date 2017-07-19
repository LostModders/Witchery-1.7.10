/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ public class AltarPower {
/*    */   private int power;
/*    */   
/*  6 */   public AltarPower(int power) { this.power = power; }
/*    */   
/*    */   public int getPower()
/*    */   {
/* 10 */     return this.power;
/*    */   }
/*    */   
/*    */   public void accumulate(AltarPower other) {
/* 14 */     this.power += other.power;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/AltarPower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */