/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class PredictionAlwaysForced extends Prediction
/*    */ {
/*    */   protected final int regularFulfillmentDurationInTicks;
/*    */   protected final double regularFulfillmentProbability;
/*    */   
/*    */   public PredictionAlwaysForced(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability) {
/* 11 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
/* 12 */     this.regularFulfillmentDurationInTicks = regularFulfillmentDurationInTicks;
/* 13 */     this.regularFulfillmentProbability = regularFulfillmentProbability;
/*    */   }
/*    */   
/*    */   public boolean isPredictionPastDue(long predictionTime, long currentTime)
/*    */   {
/* 18 */     return currentTime - predictionTime > this.regularFulfillmentDurationInTicks;
/*    */   }
/*    */   
/*    */   protected boolean shouldWeActivate(World world, net.minecraft.entity.player.EntityPlayer player, boolean isPastDue) {
/* 22 */     return world.field_73012_v.nextDouble() < (isPastDue ? this.selfFulfillmentProbabilityPerSec : this.regularFulfillmentProbability);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionAlwaysForced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */