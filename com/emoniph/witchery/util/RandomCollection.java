/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import java.util.NavigableMap;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class RandomCollection<E>
/*    */ {
/*  8 */   private final NavigableMap<Double, E> map = new java.util.TreeMap();
/*    */   private final Random random;
/* 10 */   private double total = 0.0D;
/*    */   
/*    */   public RandomCollection() {
/* 13 */     this(new Random());
/*    */   }
/*    */   
/*    */   public RandomCollection(Random random) {
/* 17 */     this.random = random;
/*    */   }
/*    */   
/*    */   public void add(double weight, E result) {
/* 21 */     if (weight > 0.0D) {
/* 22 */       this.total += weight;
/* 23 */       this.map.put(Double.valueOf(this.total), result);
/*    */     }
/*    */   }
/*    */   
/*    */   public E next() {
/* 28 */     double value = this.random.nextDouble() * this.total;
/* 29 */     return (E)this.map.ceilingEntry(Double.valueOf(value)).getValue();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/RandomCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */