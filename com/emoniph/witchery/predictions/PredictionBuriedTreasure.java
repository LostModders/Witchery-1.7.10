/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.tileentity.TileEntityChest;
/*    */ import net.minecraft.util.WeightedRandomChestContent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.ChestGenHooks;
/*    */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*    */ 
/*    */ public class PredictionBuriedTreasure extends PredictionAlwaysForced
/*    */ {
/*    */   protected final String chestGenHook;
/*    */   
/*    */   public PredictionBuriedTreasure(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, String chestGenHook)
/*    */   {
/* 17 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
/* 18 */     this.chestGenHook = chestGenHook;
/*    */   }
/*    */   
/*    */   public boolean shouldTrySelfFulfill(World world, EntityPlayer player)
/*    */   {
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public boolean doSelfFulfillment(World world, EntityPlayer player)
/*    */   {
/* 28 */     return false;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, BlockEvent.HarvestDropsEvent event, boolean isPastDue, boolean veryOld)
/*    */   {
/* 33 */     if ((!event.isCanceled()) && 
/* 34 */       ((event.block == Blocks.field_150349_c) || (event.block == Blocks.field_150346_d) || (event.block == Blocks.field_150354_m) || (event.block == Blocks.field_150391_bh)) && (event.y > 6) && (shouldWeActivate(world, player, isPastDue)))
/*    */     {
/* 36 */       if ((!world.func_147437_c(event.x + 1, event.y - 1, event.z)) && (!world.func_147437_c(event.x - 1, event.y - 1, event.z)) && (!world.func_147437_c(event.x, event.y - 1, event.z + 1)) && (!world.func_147437_c(event.x, event.y - 1, event.z - 1)) && (!world.func_147437_c(event.x, event.y - 2, event.z)))
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/* 41 */         world.func_147449_b(event.x, event.y - 1, event.z, Blocks.field_150486_ae);
/* 42 */         net.minecraft.tileentity.TileEntity tile = world.func_147438_o(event.x, event.y - 1, event.z);
/* 43 */         if ((tile != null) && ((tile instanceof TileEntityChest))) {
/* 44 */           TileEntityChest chest = (TileEntityChest)tile;
/* 45 */           ChestGenHooks info = ChestGenHooks.getInfo(this.chestGenHook);
/* 46 */           WeightedRandomChestContent.func_76293_a(world.field_73012_v, info.getItems(world.field_73012_v), chest, info.getCount(world.field_73012_v));
/*    */         }
/* 48 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionBuriedTreasure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */