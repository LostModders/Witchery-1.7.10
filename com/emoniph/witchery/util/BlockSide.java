/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ 
/*    */ public enum BlockSide
/*    */ {
/*  6 */   NONE(-2),  RAYTRACE_FULL_LENGTH(-1),  BOTTOM(0),  TOP(1),  EAST(2),  WEST(3),  NORTH(4),  SOUTH(5);
/*    */   
/*    */   final int sideID;
/*    */   
/*    */   private BlockSide(int sideID) {
/* 11 */     this.sideID = sideID;
/*    */   }
/*    */   
/*    */   public boolean isEqual(int side) {
/* 15 */     return this.sideID == side;
/*    */   }
/*    */   
/*    */   public int getSideID() {
/* 19 */     return this.sideID;
/*    */   }
/*    */   
/*    */   public static BlockSide fromInteger(int integer) {
/* 23 */     switch (integer) {
/*    */     case -2: 
/*    */     default: 
/* 26 */       return NONE;
/*    */     case -1: 
/* 28 */       return RAYTRACE_FULL_LENGTH;
/*    */     case 0: 
/* 30 */       return BOTTOM;
/*    */     case 1: 
/* 32 */       return TOP;
/*    */     case 2: 
/* 34 */       return EAST;
/*    */     case 3: 
/* 36 */       return WEST;
/*    */     case 4: 
/* 38 */       return NORTH;
/*    */     }
/* 40 */     return SOUTH;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BlockSide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */