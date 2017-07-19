/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.Array;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class HashCodeUtil
/*    */ {
/*    */   public static final int SEED = 23;
/*    */   private static final int fODD_PRIME_NUMBER = 37;
/*    */   
/*    */   public static int hash(int aSeed, boolean aBoolean)
/*    */   {
/* 18 */     System.out.println("boolean...");
/* 19 */     return firstTerm(aSeed) + (aBoolean ? 1 : 0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static int hash(int aSeed, char aChar)
/*    */   {
/* 26 */     System.out.println("char...");
/* 27 */     return firstTerm(aSeed) + aChar;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static int hash(int aSeed, int aInt)
/*    */   {
/* 38 */     System.out.println("int...");
/* 39 */     return firstTerm(aSeed) + aInt;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static int hash(int aSeed, long aLong)
/*    */   {
/* 46 */     System.out.println("long...");
/* 47 */     return firstTerm(aSeed) + (int)(aLong ^ aLong >>> 32);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static int hash(int aSeed, float aFloat)
/*    */   {
/* 54 */     return hash(aSeed, Float.floatToIntBits(aFloat));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static int hash(int aSeed, double aDouble)
/*    */   {
/* 61 */     return hash(aSeed, Double.doubleToLongBits(aDouble));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static int hash(int aSeed, Object aObject)
/*    */   {
/* 72 */     int result = aSeed;
/* 73 */     if (aObject == null) {
/* 74 */       result = hash(result, 0);
/* 75 */     } else if (!isArray(aObject)) {
/* 76 */       result = hash(result, aObject.hashCode());
/*    */     } else {
/* 78 */       int length = Array.getLength(aObject);
/* 79 */       for (int idx = 0; idx < length; idx++) {
/* 80 */         Object item = Array.get(aObject, idx);
/*    */         
/* 82 */         result = hash(result, item);
/*    */       }
/*    */     }
/* 85 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private static int firstTerm(int aSeed)
/*    */   {
/* 92 */     return 37 * aSeed;
/*    */   }
/*    */   
/*    */   private static boolean isArray(Object aObject) {
/* 96 */     return aObject.getClass().isArray();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/HashCodeUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */