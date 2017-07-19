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
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class RiteProtectionCircleAttractive
/*    */   extends RiteProtectionCircle
/*    */ {
/*    */   public RiteProtectionCircleAttractive(int radius, float upkeepPowerCost, int ticksToLive)
/*    */   {
/* 20 */     super(radius, upkeepPowerCost, ticksToLive);
/*    */   }
/*    */   
/*    */   protected void update(World world, int posX, int posY, int posZ, int radius, long ticks)
/*    */   {
/* 25 */     attract(world, posX, posY, posZ, radius);
/*    */   }
/*    */   
/*    */   private void attract(World world, int posX, int posY, int posZ, float radius) {
/* 29 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
/* 30 */     List list = world.func_72872_a(EntityCreature.class, bounds);
/*    */     
/* 32 */     for (Iterator iterator = list.iterator(); iterator.hasNext();) {
/* 33 */       Entity entity = (Entity)iterator.next();
/* 34 */       if (Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) < radius) {
/* 35 */         pull(world, entity, posX, posY, posZ, radius);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private void pull(World world, Entity entity, int posX, int posY, int posZ, float radius) {
/* 41 */     if ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityDragon)))
/*    */     {
/* 43 */       double distance = Coord.distance(entity.field_70165_t + entity.field_70159_w, entity.field_70163_u + entity.field_70181_x, entity.field_70161_v + entity.field_70179_y, posX, posY, posZ);
/* 44 */       if (distance >= radius - 1.0F)
/*    */       {
/* 46 */         Entity entity2 = entity;
/* 47 */         double d = posX - entity2.field_70165_t;
/* 48 */         double d1 = posY - entity2.field_70163_u;
/* 49 */         double d2 = posZ - entity2.field_70161_v;
/* 50 */         double d4 = d * d + d1 * d1 + d2 * d2;
/* 51 */         d4 *= d4;
/* 52 */         if (d4 <= Math.pow(6.0D, 4.0D)) {
/* 53 */           double d5 = -(d * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 54 */           double d6 = -(d1 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 55 */           double d7 = -(d2 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 56 */           if (d5 > 0.0D) {
/* 57 */             d5 = 0.22D;
/* 58 */           } else if (d5 < 0.0D) {
/* 59 */             d5 = -0.22D;
/*    */           }
/* 61 */           if (d6 > 0.2D) {
/* 62 */             d6 = 0.12D;
/* 63 */           } else if (d6 < -0.1D) {
/* 64 */             d6 = 0.12D;
/*    */           }
/* 66 */           if (d7 > 0.0D) {
/* 67 */             d7 = 0.22D;
/* 68 */           } else if (d7 < 0.0D) {
/* 69 */             d7 = -0.22D;
/*    */           }
/*    */           
/*    */ 
/*    */ 
/* 74 */           Vec3 vec = Vec3.func_72443_a(d5, d6, d7);
/* 75 */           vec.func_72442_b(180.0F);
/* 76 */           entity2.field_70159_w = vec.field_72450_a;
/* 77 */           entity2.field_70181_x = 0.0D;
/* 78 */           entity2.field_70179_y = vec.field_72449_c;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteProtectionCircleAttractive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */