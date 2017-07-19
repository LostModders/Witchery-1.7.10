/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IProjectile;
/*    */ import net.minecraft.entity.projectile.EntityArrow;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class PotionReflectProjectiles
/*    */   extends PotionBase
/*    */ {
/*    */   public PotionReflectProjectiles(int id, int color)
/*    */   {
/* 18 */     super(id, color);
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 23 */     return true;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 28 */     World world = entity.field_70170_p;
/* 29 */     double RADIUS = 2.0D;
/* 30 */     double RADIUS_SQ = 4.0D;
/* 31 */     AxisAlignedBB bounds = entity.field_70121_D.func_72314_b(2.0D, 2.0D, 2.0D);
/* 32 */     List<IProjectile> projectileList = world.func_72872_a(IProjectile.class, bounds);
/* 33 */     for (IProjectile projectile : projectileList) {
/* 34 */       boolean isArrow = false;
/* 35 */       if ((projectile instanceof EntityArrow)) {
/* 36 */         EntityArrow arrow = (EntityArrow)projectile;
/* 37 */         isArrow = true;
/* 38 */         if (arrow.field_70250_c == entity) {
/*    */           continue;
/*    */         }
/* 41 */       } else if ((projectile instanceof EntityThrowable)) {
/* 42 */         EntityThrowable arrow = (EntityThrowable)projectile;
/* 43 */         if (arrow.func_85052_h() == entity) {
/*    */           continue;
/*    */         }
/*    */       }
/* 47 */       if ((projectile instanceof Entity)) {
/* 48 */         Entity projectileEntity = (Entity)projectile;
/* 49 */         projectileEntity.field_70159_w *= -0.25D * (1.0D + amplifier);
/* 50 */         if ((projectileEntity.field_70159_w > 0.0D) || (projectileEntity.field_70179_y > 0.0D)) {
/* 51 */           projectileEntity.field_70181_x *= -0.25D * (1.0D + amplifier);
/*    */         }
/* 53 */         projectileEntity.field_70179_y *= -0.25D * (1.0D + amplifier);
/* 54 */         if (!isArrow) {}
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionReflectProjectiles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */