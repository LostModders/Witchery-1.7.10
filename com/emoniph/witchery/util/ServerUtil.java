/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.world.WorldServer;
/*    */ 
/*    */ public class ServerUtil
/*    */ {
/*    */   public static WorldServer getWorld(int dimension)
/*    */   {
/*  9 */     for (WorldServer world : net.minecraft.server.MinecraftServer.func_71276_C().field_71305_c) {
/* 10 */       if (world.field_73011_w.field_76574_g == dimension) {
/* 11 */         return world;
/*    */       }
/*    */     }
/* 14 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/ServerUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */