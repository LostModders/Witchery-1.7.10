/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ 
/*    */ public class BrewNamePart {
/*  6 */   public static enum Position { NONE,  PREFIX,  POSTFIX;
/*    */     
/*    */     private Position() {} }
/*    */   
/*    */   protected final String text;
/*    */   protected final String invertedText;
/*    */   protected final Position position;
/*    */   protected long baseDuration;
/*    */   protected long invertedDuration;
/* 15 */   public BrewNamePart(String resourceId) { this(resourceId, resourceId, Position.NONE); }
/*    */   
/*    */   public BrewNamePart(String resourceId, Position position)
/*    */   {
/* 19 */     this(resourceId, resourceId, position);
/*    */   }
/*    */   
/*    */   public BrewNamePart(String resourceId, String invertedResourceId) {
/* 23 */     this(resourceId, invertedResourceId, Position.NONE);
/*    */   }
/*    */   
/*    */   public BrewNamePart(String resourceId, String invertedResourceId, Position position) {
/* 27 */     this.text = Witchery.resource(resourceId);
/* 28 */     this.invertedText = Witchery.resource(invertedResourceId);
/* 29 */     this.position = position;
/*    */   }
/*    */   
/*    */   public void applyTo(BrewNameBuilder nameBuilder) {
/* 33 */     switch (this.position) {
/*    */     case NONE: 
/* 35 */       nameBuilder.append(this.text, this.invertedText, this.baseDuration, this.invertedDuration);
/* 36 */       break;
/*    */     case PREFIX: 
/* 38 */       nameBuilder.appendPrefix(this.text);
/* 39 */       break;
/*    */     case POSTFIX: 
/* 41 */       nameBuilder.appendPostfix(this.text);
/*    */     }
/*    */   }
/*    */   
/*    */   public BrewNamePart setBaseDuration(long baseDuration, long invertedDuration)
/*    */   {
/* 47 */     this.baseDuration = baseDuration;
/* 48 */     this.invertedDuration = invertedDuration;
/* 49 */     return this;
/*    */   }
/*    */   
/*    */   public BrewNamePart setBaseDuration(int baseDuration) {
/* 53 */     return setBaseDuration(baseDuration, baseDuration);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BrewNamePart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */