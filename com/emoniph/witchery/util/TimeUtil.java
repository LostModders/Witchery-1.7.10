/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class TimeUtil {
/*    */   public static int secsToTicks(int seconds) {
/*  7 */     return seconds * 20;
/*    */   }
/*    */   
/*    */   public static int minsToTicks(int minutes) {
/* 11 */     return minutes * 1200;
/*    */   }
/*    */   
/*    */   public static boolean secondsElapsed(int seconds, long ticksExisted) {
/* 15 */     return ticksExisted % secsToTicks(seconds) == 0L;
/*    */   }
/*    */   
/*    */   public static boolean ticksElapsed(int ticks, long ticksExisted) {
/* 19 */     return ticksExisted % ticks == 0L;
/*    */   }
/*    */   
/*    */   public static long ticksToSecs(long ticks) {
/* 23 */     return ticks / 20L;
/*    */   }
/*    */   
/*    */   public static long minsToMillisecs(int mins) {
/* 27 */     return mins * 60000;
/*    */   }
/*    */   
/*    */   public static long secsToMillisecs(int secs) {
/* 31 */     return secs * 1000;
/*    */   }
/*    */   
/*    */   public static long getServerTimeInTicks() {
/* 35 */     return MinecraftServer.func_130071_aq() / 50L;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/TimeUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */