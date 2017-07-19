/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ 
/*    */ public class PredictionWet extends Prediction
/*    */ {
/*    */   public PredictionWet(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey)
/*    */   {
/* 15 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
/*    */   }
/*    */   
/*    */   public boolean doSelfFulfillment(World world, EntityPlayer player)
/*    */   {
/* 20 */     int FALL_DISTANCE = 3;
/* 21 */     int RADIUS = 1;
/*    */     
/* 23 */     int x0 = MathHelper.func_76128_c(player.field_70165_t);
/* 24 */     int y0 = MathHelper.func_76128_c(player.field_70163_u) - 1;
/* 25 */     int z0 = MathHelper.func_76128_c(player.field_70161_v);
/*    */     
/* 27 */     if ((!world.field_72995_K) && (y0 > 5) && (!world.field_73011_w.field_76575_d)) {
/* 28 */       int dirtCount = 0;
/* 29 */       for (int x = x0 - 1; x <= x0 + 1; x++) {
/* 30 */         for (int z = z0 - 1; z <= z0 + 1; z++) {
/* 31 */           Material material = world.func_147439_a(x, y0, z).func_149688_o();
/* 32 */           if ((material == Material.field_151578_c) || (material == Material.field_151577_b)) {
/* 33 */             dirtCount++;
/*    */           }
/*    */         }
/*    */       }
/* 37 */       if (dirtCount == Math.pow(3.0D, 2.0D)) {
/* 38 */         for (int x = x0 - 1; x <= x0 + 1; x++) {
/* 39 */           for (int z = z0 - 1; z <= z0 + 1; z++) {
/* 40 */             for (int y = y0; y > y0 - 3; y--) {
/* 41 */               if (y == y0) {
/* 42 */                 world.func_147449_b(x, y, z, Blocks.field_150351_n);
/* 43 */               } else if (com.emoniph.witchery.util.BlockProtect.canBreak(world.func_147439_a(x, y, z), world)) {
/* 44 */                 world.func_147449_b(x, y, z, Blocks.field_150355_j);
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */         
/* 50 */         Log.instance().debug(String.format("Prediction for getting wet has been forced", new Object[0]));
/*    */         
/* 52 */         return true;
/*    */       }
/*    */     }
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld)
/*    */   {
/* 60 */     if (player.func_70026_G()) {
/* 61 */       Log.instance().debug(String.format("Prediction for WET fulfilled as predicted", new Object[0]));
/* 62 */       return true;
/*    */     }
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionWet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */