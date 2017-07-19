/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.boss.EntityDragon;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteProtectionCircleRepulsive
/*    */   extends RiteProtectionCircle
/*    */ {
/*    */   public RiteProtectionCircleRepulsive(int radius, float upkeepPowerCost, int ticksTolive)
/*    */   {
/* 18 */     super(radius, upkeepPowerCost, ticksTolive);
/*    */   }
/*    */   
/*    */   protected void update(World world, int posX, int posY, int posZ, int radius, long ticks)
/*    */   {
/* 23 */     repulse(world, posX, posY, posZ, radius);
/*    */   }
/*    */   
/*    */   private void repulse(World world, int posX, int posY, int posZ, float radius) {
/* 27 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
/* 28 */     List list = world.func_72872_a(EntityCreature.class, bounds);
/*    */     
/* 30 */     for (Iterator iterator = list.iterator(); iterator.hasNext();) {
/* 31 */       Entity entity = (Entity)iterator.next();
/* 32 */       if (Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) < radius) {
/* 33 */         push(world, entity, posX, posY, posZ);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void push(World world, Entity entity, double posX, double posY, double posZ) {
/* 39 */     push(world, entity, posX, posY, posZ, true);
/*    */   }
/*    */   
/*    */   public static void push(World world, Entity entity, double posX, double posY, double posZ, boolean restricted) {
/* 43 */     if ((!restricted) || ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityDragon)))) {
/* 44 */       Entity entity2 = entity;
/* 45 */       double d = posX - entity2.field_70165_t;
/* 46 */       double d1 = posY - entity2.field_70163_u;
/* 47 */       double d2 = posZ - entity2.field_70161_v;
/* 48 */       double d4 = d * d + d1 * d1 + d2 * d2;
/* 49 */       d4 *= d4;
/* 50 */       if (d4 <= Math.pow(6.0D, 4.0D)) {
/* 51 */         double d5 = -(d * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 52 */         double d6 = -(d1 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 53 */         double d7 = -(d2 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 54 */         if (d5 > 0.0D) {
/* 55 */           d5 = 0.22D;
/* 56 */         } else if (d5 < 0.0D) {
/* 57 */           d5 = -0.22D;
/*    */         }
/* 59 */         if (d6 > 0.2D) {
/* 60 */           d6 = 0.12D;
/* 61 */         } else if (d6 < -0.1D) {
/* 62 */           d6 = 0.12D;
/*    */         }
/* 64 */         if (d7 > 0.0D) {
/* 65 */           d7 = 0.22D;
/* 66 */         } else if (d7 < 0.0D) {
/* 67 */           d7 = -0.22D;
/*    */         }
/* 69 */         entity2.field_70159_w += d5;
/* 70 */         entity2.field_70181_x += d6;
/* 71 */         entity2.field_70179_y += d7;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteProtectionCircleRepulsive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */