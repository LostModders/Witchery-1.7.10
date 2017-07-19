/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class PotionFeatherFall extends PotionBase
/*    */ {
/*    */   public PotionFeatherFall(int id, int color) {
/*  8 */     super(id, color);
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 13 */     return true;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 18 */     int activationDistance = amplifier >= 1 ? 4 : amplifier >= 2 ? 3 : 5;
/* 19 */     int maxFallDistance = amplifier >= 1 ? 5 : amplifier >= 2 ? 4 : amplifier >= 3 ? 3 : 6;
/* 20 */     if ((entity.field_70143_R >= activationDistance) && (entity.field_70181_x < -0.2D)) {
/* 21 */       entity.field_70181_x = -0.2D;
/*    */       
/* 23 */       if (entity.field_70143_R > maxFallDistance) {
/* 24 */         entity.field_70143_R = maxFallDistance;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionFeatherFall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */