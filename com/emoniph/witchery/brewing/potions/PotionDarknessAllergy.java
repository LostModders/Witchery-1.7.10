/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class PotionDarknessAllergy extends PotionBase
/*    */ {
/*    */   public PotionDarknessAllergy(int id, int color)
/*    */   {
/*  9 */     super(id, true, color);
/* 10 */     setIncurable();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 16 */     return duration % 20 == 4;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 21 */     int x = net.minecraft.util.MathHelper.func_76128_c(entity.field_70165_t);
/* 22 */     int y = net.minecraft.util.MathHelper.func_76128_c(entity.field_70163_u);
/* 23 */     int z = net.minecraft.util.MathHelper.func_76128_c(entity.field_70161_v);
/* 24 */     int lightLevel = entity.field_70170_p.func_72957_l(x, y, z);
/* 25 */     if (lightLevel < 2 + amplifier * 2) {
/* 26 */       entity.func_70097_a(net.minecraft.util.DamageSource.field_76380_i, 1.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionDarknessAllergy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */