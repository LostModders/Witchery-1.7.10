/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public final class TargetPointUtil
/*    */ {
/*    */   public static cpw.mods.fml.common.network.NetworkRegistry.TargetPoint from(Entity entity, double range)
/*    */   {
/*  9 */     if (entity != null) {
/* 10 */       return new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(entity.field_71093_bK, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, range);
/*    */     }
/* 12 */     return new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(0, 0.0D, 0.0D, 0.0D, range);
/*    */   }
/*    */   
/*    */   public static cpw.mods.fml.common.network.NetworkRegistry.TargetPoint from(net.minecraft.world.World world, double x, double y, double z, double range)
/*    */   {
/* 17 */     return new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, range);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/TargetPointUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */