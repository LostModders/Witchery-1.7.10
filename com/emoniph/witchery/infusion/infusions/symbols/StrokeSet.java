/*    */ package com.emoniph.witchery.infusion.infusions.symbols;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ public class StrokeSet
/*    */ {
/*    */   private final byte[] strokes;
/*    */   private final int level;
/*    */   
/*    */   public static class Stroke {
/*    */     public static final byte UP = 0;
/*    */     public static final byte DOWN = 1;
/*    */     public static final byte LEFT = 3;
/*    */     public static final byte RIGHT = 2;
/*    */     public static final byte UP_LEFT = 6;
/*    */     public static final byte UP_RIGHT = 4;
/*    */     public static final byte DOWN_LEFT = 5;
/*    */     public static final byte DOWN_RIGHT = 7;
/* 19 */     public static final int[] STROKE_TO_INDEX = { 0, 4, 2, 6, 1, 5, 7, 3 };
/* 20 */     public static final int[] INDEX_TO_STROKE = { 0, 4, 2, 7, 1, 5, 3, 6 };
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public StrokeSet(int level, byte... strokes)
/*    */   {
/* 30 */     this.strokes = strokes;
/* 31 */     this.level = level;
/*    */   }
/*    */   
/*    */   public StrokeSet(byte... strokes) {
/* 35 */     this(1, strokes);
/*    */   }
/*    */   
/*    */   public void addTo(java.util.Hashtable<ByteBuffer, SymbolEffect> table, java.util.Hashtable<ByteBuffer, Integer> enhanced, SymbolEffect effect) {
/* 39 */     ByteBuffer bb = ByteBuffer.wrap(this.strokes);
/* 40 */     table.put(bb, effect);
/* 41 */     enhanced.put(bb, Integer.valueOf(this.level));
/*    */   }
/*    */   
/*    */   public void setDefaultFor(SymbolEffect effect) {
/* 45 */     effect.setDefaultStrokes(this.strokes);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/symbols/StrokeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */