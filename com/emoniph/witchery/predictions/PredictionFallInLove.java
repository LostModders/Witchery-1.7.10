/*    */ package com.emoniph.witchery.predictions;
/*    */ 
/*    */ import com.emoniph.witchery.entity.ai.EntityAIMateWithPlayer;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ 
/*    */ public class PredictionFallInLove extends PredictionAlwaysForced
/*    */ {
/*    */   public PredictionFallInLove(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability)
/*    */   {
/* 17 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
/*    */   }
/*    */   
/*    */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld)
/*    */   {
/* 22 */     if (shouldWeActivate(world, player, isPastDue)) {
/* 23 */       int x = MathHelper.func_76128_c(player.field_70165_t);
/* 24 */       int y = MathHelper.func_76128_c(player.field_70163_u);
/* 25 */       int z = MathHelper.func_76128_c(player.field_70161_v);
/*    */       
/* 27 */       int MAX_DISTANCE = 6;
/* 28 */       int MIN_DISTANCE = 4;
/*    */       
/* 30 */       int activeRadius = 2;
/* 31 */       int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 32 */       if (ax > activeRadius) {
/* 33 */         ax += 8;
/*    */       }
/* 35 */       int nx = x - 6 + ax;
/*    */       
/* 37 */       int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 38 */       if (az > activeRadius) {
/* 39 */         az += 8;
/*    */       }
/*    */       
/* 42 */       int nz = z - 6 + az;
/*    */       
/* 44 */       int ny = y;
/* 45 */       while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
/* 46 */         ny++;
/*    */       }
/*    */       
/*    */ 
/* 50 */       while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
/* 51 */         ny--;
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 56 */       int hy = 0;
/* 57 */       while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
/* 58 */         hy++;
/*    */       }
/*    */       
/* 61 */       EntityVillager entity = new EntityVillager(world, 0);
/* 62 */       if ((hy >= entity.field_70131_O) && (world.func_147439_a(nx, ny - 1, nz).func_149721_r()))
/*    */       {
/* 64 */         entity.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
/* 65 */         world.func_72838_d(entity);
/* 66 */         com.emoniph.witchery.util.Log.instance().debug(String.format("Forcing prediction for lover by %s", new Object[] { entity.toString() }));
/*    */         
/* 68 */         EntityAIMateWithPlayer task = new EntityAIMateWithPlayer(entity);
/* 69 */         task.forceTask(player);
/* 70 */         entity.field_70714_bg.func_75776_a(1, task);
/*    */         
/* 72 */         ParticleEffect.SMOKE.send(SoundEffect.NONE, entity, 0.5D, 2.0D, 16);
/* 73 */         SoundEffect.WITCHERY_RANDOM_LOVED.playAtPlayer(world, player);
/*    */         
/* 75 */         return true;
/*    */       }
/* 77 */       return false;
/*    */     }
/*    */     
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionFallInLove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */