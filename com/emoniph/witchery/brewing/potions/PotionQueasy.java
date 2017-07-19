/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ public class PotionQueasy extends PotionBase
/*    */ {
/*    */   public PotionQueasy(int id, int color) {
/*  6 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public void postContructInitialize()
/*    */   {
/* 11 */     setIncurable();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionQueasy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */