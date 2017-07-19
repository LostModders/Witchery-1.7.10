/*     */ package com.emoniph.witchery.predictions;
/*     */ 
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.monster.EntityBlaze;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ 
/*     */ public class PredictionNetherTrip extends Prediction
/*     */ {
/*     */   public PredictionNetherTrip(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey)
/*     */   {
/*  17 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
/*     */   }
/*     */   
/*     */   public boolean isPredictionPossible(World world, EntityPlayer player)
/*     */   {
/*     */     try {
/*  23 */       NBTTagCompound nbtPlayer = com.emoniph.witchery.infusion.Infusion.getNBT(player);
/*  24 */       boolean wasInNether = (nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCVisitedNether")) && (nbtPlayer.func_74767_n("WITCVisitedNether"));
/*  25 */       boolean hasBeenToNether = wasInNether;
/*  26 */       return (player.field_71093_bK != -1) && (hasBeenToNether);
/*     */     }
/*     */     catch (Throwable e)
/*     */     {
/*  30 */       Log.instance().warning(e, "Error occurred while checking if a nether visit has occurred for the nether prediction."); }
/*  31 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doSelfFulfillment(World world2, EntityPlayer player)
/*     */   {
/*  37 */     int FALL_DISTANCE = 4;
/*  38 */     int RADIUS = 1;
/*     */     
/*  40 */     int x = MathHelper.func_76128_c(player.field_70165_t);
/*  41 */     int y = MathHelper.func_76128_c(player.field_70163_u) - 1;
/*  42 */     int z = MathHelper.func_76128_c(player.field_70161_v);
/*     */     
/*  44 */     if ((!world2.field_72995_K) && (player.field_71093_bK != -1)) {
/*  45 */       ChatUtil.sendTranslated(net.minecraft.util.EnumChatFormatting.LIGHT_PURPLE, player, "witchery.prediction.tothenether.summoned", new Object[0]);
/*  46 */       player.func_70063_aa();
/*     */       
/*  48 */       World world = player.field_70170_p;
/*     */       
/*  50 */       int MAX_DISTANCE = 4;
/*  51 */       int MIN_DISTANCE = 2;
/*     */       
/*  53 */       int activeRadius = 2;
/*  54 */       int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  55 */       if (ax > activeRadius) {
/*  56 */         ax += 4;
/*     */       }
/*  58 */       int nx = x - 4 + ax;
/*     */       
/*  60 */       int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  61 */       if (az > activeRadius) {
/*  62 */         az += 4;
/*     */       }
/*     */       
/*  65 */       int nz = z - 4 + az;
/*     */       
/*  67 */       int ny = y;
/*  68 */       while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
/*  69 */         ny++;
/*     */       }
/*     */       
/*     */ 
/*  73 */       while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
/*  74 */         ny--;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  79 */       int hy = 0;
/*  80 */       while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
/*  81 */         hy++;
/*     */       }
/*     */       
/*  84 */       EntityBlaze entity = new EntityBlaze(world);
/*  85 */       if (hy >= entity.field_70131_O) {
/*  86 */         entity.func_70012_b(nx, ny, nz, 0.0F, 0.0F);
/*  87 */         world.func_72838_d(entity);
/*     */       }
/*  89 */       return true;
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld)
/*     */   {
/*  96 */     if (player.field_71093_bK == -1) {
/*  97 */       Log.instance().debug(String.format("Prediction for got to nether fulfilled as predicted", new Object[0]));
/*  98 */       return true;
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionNetherTrip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */