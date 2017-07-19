/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityBolt;
/*    */ import net.minecraft.util.EntityDamageSourceIndirect;
/*    */ 
/*    */ public class BoltDamageSource extends EntityDamageSourceIndirect
/*    */ {
/*    */   public final boolean isWooden;
/*    */   public final boolean isHoly;
/*    */   public final boolean isPoweredDraining;
/*    */   
/*    */   public BoltDamageSource(EntityBolt bolt, net.minecraft.entity.Entity shooter)
/*    */   {
/* 14 */     super("arrow", bolt, shooter);
/* 15 */     func_76349_b();
/* 16 */     this.isWooden = bolt.isWoodenDamage();
/* 17 */     this.isPoweredDraining = bolt.isPoweredDraining();
/* 18 */     this.isHoly = bolt.isHolyDamage();
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 23 */     return super.toString() + String.format(" Bolt wood=%s holy=%s drain=%s", new Object[] { Boolean.valueOf(this.isWooden), Boolean.valueOf(this.isHoly), Boolean.valueOf(this.isPoweredDraining) });
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BoltDamageSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */