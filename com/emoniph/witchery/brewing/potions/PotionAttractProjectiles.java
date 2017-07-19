/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IProjectile;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class PotionAttractProjectiles
/*    */   extends PotionBase
/*    */ {
/*    */   public PotionAttractProjectiles(int id, int color)
/*    */   {
/* 17 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 22 */     return true;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int amplifier)
/*    */   {
/* 27 */     World world = target.field_70170_p;
/* 28 */     double RADIUS = (1.0D + amplifier) * 3.0D;
/* 29 */     double RADIUS_SQ = RADIUS * RADIUS;
/* 30 */     AxisAlignedBB bounds = target.field_70121_D.func_72314_b(RADIUS, RADIUS, RADIUS);
/* 31 */     List<IProjectile> projectileList = world.func_72872_a(IProjectile.class, bounds);
/* 32 */     for (IProjectile projectile : projectileList)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 44 */       if ((projectile instanceof Entity)) {
/* 45 */         Entity arrow = (Entity)projectile;
/* 46 */         double velocitySq = arrow.field_70159_w * arrow.field_70159_w + arrow.field_70181_x * arrow.field_70181_x + arrow.field_70179_y * arrow.field_70179_y;
/* 47 */         double FAST_SQ = 0.25D;
/* 48 */         if (arrow.field_70173_aa >= (velocitySq > 0.25D ? 1 : 10))
/*    */         {
/*    */ 
/*    */ 
/*    */ 
/* 53 */           double d0 = target.field_70165_t - arrow.field_70165_t;
/* 54 */           double d1 = target.field_70121_D.field_72338_b + target.field_70131_O * 0.75D - arrow.field_70163_u;
/* 55 */           double d2 = target.field_70161_v - arrow.field_70161_v;
/* 56 */           double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/*    */           
/* 58 */           if (d3 >= 1.0E-7D)
/*    */           {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 68 */             projectile.func_70186_c(d0, d1, d2, 1.0F, 1.0F);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionAttractProjectiles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */