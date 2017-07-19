/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ public class Count {
/*    */   protected int count;
/*    */   
/*    */   public void increment() {
/*  7 */     this.count += 1;
/*    */   }
/*    */   
/*    */   public void decrement() {
/* 11 */     this.count -= 1;
/*    */   }
/*    */   
/*    */   public int get() {
/* 15 */     return this.count;
/*    */   }
/*    */   
/*    */   public void incrementBy(int quantity) {
/* 19 */     this.count += quantity;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/Count.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */