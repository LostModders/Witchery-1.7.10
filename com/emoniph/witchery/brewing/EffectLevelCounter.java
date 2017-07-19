/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ public class EffectLevelCounter {
/*    */   private int maxLevel;
/*    */   private int currentLevel;
/*    */   private int effects;
/*    */   
/*    */   public void increaseAvailableLevelIf(EffectLevel incement, EffectLevel ceilingLevel) {
/*  9 */     if (this.maxLevel < ceilingLevel.getLevel()) {
/* 10 */       this.maxLevel += incement.getLevel();
/*    */     }
/*    */   }
/*    */   
/*    */   public int remainingCapactiy() {
/* 15 */     return this.maxLevel - this.currentLevel;
/*    */   }
/*    */   
/*    */   public int usedCapacity() {
/* 19 */     return this.currentLevel;
/*    */   }
/*    */   
/*    */   public int getEffectCount() {
/* 23 */     return this.effects;
/*    */   }
/*    */   
/*    */   public boolean tryConsumeLevel(EffectLevel level) {
/* 27 */     if (canConsumeLevel(level)) {
/* 28 */       this.currentLevel += level.getLevel();
/* 29 */       this.effects += 1;
/* 30 */       return true;
/*    */     }
/* 32 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canConsumeLevel(EffectLevel level)
/*    */   {
/* 37 */     return level.getLevel() + this.currentLevel <= this.maxLevel;
/*    */   }
/*    */   
/*    */   public boolean hasEffects() {
/* 41 */     return this.currentLevel > 0;
/*    */   }
/*    */   
/*    */   public boolean canIncreasePlayerSkill(int currentSkillLevel) {
/* 45 */     if ((this.currentLevel > this.maxLevel) || (this.maxLevel == 0)) {
/* 46 */       return false;
/*    */     }
/*    */     
/* 49 */     if (currentSkillLevel < 30) {
/* 50 */       return (this.maxLevel > 1) && (this.currentLevel > 1);
/*    */     }
/*    */     
/* 53 */     if (currentSkillLevel < 60) {
/* 54 */       return (this.maxLevel >= 4) && (this.currentLevel >= 4);
/*    */     }
/*    */     
/* 57 */     if (currentSkillLevel < 90) {
/* 58 */       return (this.maxLevel >= 6) && (this.currentLevel >= 6);
/*    */     }
/*    */     
/* 61 */     if (currentSkillLevel != 100) {
/* 62 */       return (this.maxLevel >= 8) && (this.currentLevel >= 8);
/*    */     }
/*    */     
/* 65 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/EffectLevelCounter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */