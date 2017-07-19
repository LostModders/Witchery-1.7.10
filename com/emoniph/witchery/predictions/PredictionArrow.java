/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PredictionArrow extends PredictionFight
/*    */ {
/*    */   public PredictionArrow(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey)
/*    */   {
/* 12 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, net.minecraft.entity.monster.EntitySkeleton.class, false);
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld)
/*    */   {
/* 17 */     if (!event.isCanceled()) {
/* 18 */       boolean hitByArrow = event.source.field_76373_n == "arrow";
/*    */       
/* 20 */       if (hitByArrow) {
/* 21 */         Log.instance().debug(String.format("Prediction for hit by arrow came true", new Object[0]));
/*    */       }
/* 23 */       return hitByArrow;
/*    */     }
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */