/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PredictionFall extends Prediction
/*    */ {
/*    */   public PredictionFall(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey)
/*    */   {
/* 16 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
/*    */   }
/*    */   
/*    */   public boolean doSelfFulfillment(World world, EntityPlayer player)
/*    */   {
/* 21 */     int FALL_DISTANCE = 6;
/* 22 */     int RADIUS = 1;
/*    */     
/* 24 */     int x0 = MathHelper.func_76128_c(player.field_70165_t);
/* 25 */     int y0 = MathHelper.func_76128_c(player.field_70163_u) - 1;
/* 26 */     int z0 = MathHelper.func_76128_c(player.field_70161_v);
/*    */     
/* 28 */     if ((!world.field_72995_K) && (y0 > 8)) {
/* 29 */       int dirtCount = 0;
/* 30 */       for (int x = x0 - 1; x <= x0 + 1; x++) {
/* 31 */         for (int z = z0 - 1; z <= z0 + 1; z++) {
/* 32 */           Material material = world.func_147439_a(x, y0, z).func_149688_o();
/* 33 */           if ((material == Material.field_151578_c) || (material == Material.field_151577_b)) {
/* 34 */             dirtCount++;
/*    */           }
/*    */         }
/*    */       }
/* 38 */       if (dirtCount == Math.pow(3.0D, 2.0D)) {
/* 39 */         for (int x = x0 - 1; x <= x0 + 1; x++) {
/* 40 */           for (int z = z0 - 1; z <= z0 + 1; z++) {
/* 41 */             for (int y = y0; y > y0 - 6; y--) {
/* 42 */               if (y == y0) {
/* 43 */                 world.func_147449_b(x, y, z, net.minecraft.init.Blocks.field_150351_n);
/* 44 */               } else if (com.emoniph.witchery.util.BlockProtect.canBreak(world.func_147439_a(x, y, z), world)) {
/* 45 */                 world.func_147468_f(x, y, z);
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */         
/* 51 */         Log.instance().debug(String.format("Prediction for falling has been forced", new Object[0]));
/*    */         
/* 53 */         return true;
/*    */       }
/*    */     }
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld)
/*    */   {
/* 61 */     if (!event.isCanceled()) {
/* 62 */       boolean fallen = event.source == DamageSource.field_76379_h;
/* 63 */       if (fallen) {
/* 64 */         Log.instance().debug(String.format("Prediction for FALL fulfilled as predicted", new Object[0]));
/*    */       }
/* 66 */       return fallen;
/*    */     }
/* 68 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionFall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */