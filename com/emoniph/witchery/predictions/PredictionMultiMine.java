/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*    */ 
/*    */ public class PredictionMultiMine extends PredictionAlwaysForced
/*    */ {
/*    */   protected final Block block;
/*    */   protected final ItemStack itemPrototype;
/*    */   protected final int minExtra;
/*    */   protected final int maxExtra;
/*    */   
/*    */   public PredictionMultiMine(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, Block block, ItemStack itemPrototype, int minExtra, int maxExtra)
/*    */   {
/* 17 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
/* 18 */     this.block = block;
/* 19 */     this.minExtra = minExtra;
/* 20 */     this.maxExtra = maxExtra;
/* 21 */     this.itemPrototype = itemPrototype;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, net.minecraft.entity.player.EntityPlayer player, BlockEvent.HarvestDropsEvent event, boolean isPastDue, boolean veryOld)
/*    */   {
/* 26 */     if ((!event.isCanceled()) && 
/* 27 */       ((event.block == this.block) || ((veryOld) && (event.block == net.minecraft.init.Blocks.field_150348_b))) && (shouldWeActivate(world, player, isPastDue))) {
/* 28 */       int optional = this.maxExtra - this.minExtra;
/* 29 */       int totalExtra = this.minExtra + (optional > 1 ? world.field_73012_v.nextInt(optional) + 1 : optional);
/* 30 */       for (int i = 0; i < totalExtra; i++) {
/* 31 */         event.drops.add(this.itemPrototype.func_77946_l());
/*    */       }
/* 33 */       return true;
/*    */     }
/*    */     
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionMultiMine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */