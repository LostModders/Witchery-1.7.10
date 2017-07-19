/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*    */ 
/*    */ public abstract class Prediction
/*    */ {
/*    */   public final int predictionID;
/*    */   public final double itemWeight;
/*    */   protected final String translationKey;
/*    */   protected final double selfFulfillmentProbabilityPerSec;
/*    */   
/*    */   public Prediction(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey)
/*    */   {
/* 20 */     this.predictionID = id;
/* 21 */     this.itemWeight = itemWeight;
/* 22 */     this.translationKey = translationKey;
/* 23 */     this.selfFulfillmentProbabilityPerSec = selfFulfillmentProbabilityPerSec;
/*    */   }
/*    */   
/*    */   public boolean shouldTrySelfFulfill(World world, EntityPlayer player) {
/* 27 */     return world.field_73012_v.nextDouble() < this.selfFulfillmentProbabilityPerSec;
/*    */   }
/*    */   
/*    */   public boolean doSelfFulfillment(World world, EntityPlayer player) {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld) {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, PlayerInteractEvent event, boolean isPastDue, boolean veryOld) {
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld) {
/* 43 */     return false;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World worldObj, EntityPlayer player, BlockEvent.HarvestDropsEvent event, boolean isPastDue, boolean veryOld) {
/* 47 */     return false;
/*    */   }
/*    */   
/*    */   public NBTTagCompound createTagCompound(World world) {
/* 51 */     NBTTagCompound compound = new NBTTagCompound();
/* 52 */     compound.func_74768_a("WITCPreID", this.predictionID);
/* 53 */     compound.func_74772_a("WITCPreTime", com.emoniph.witchery.util.TimeUtil.getServerTimeInTicks());
/* 54 */     return compound;
/*    */   }
/*    */   
/*    */   public String getTranslationKey() {
/* 58 */     return this.translationKey;
/*    */   }
/*    */   
/*    */   public boolean isPredictionPastDue(long predictionTime, long currentTime) {
/* 62 */     return currentTime - predictionTime > 9600L;
/*    */   }
/*    */   
/*    */   public boolean isPredictionPossible(World world, EntityPlayer player) {
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/Prediction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */