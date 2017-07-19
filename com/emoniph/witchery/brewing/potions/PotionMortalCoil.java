/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.util.EntityUtil;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PotionMortalCoil
/*    */   extends PotionBase
/*    */ {
/*    */   public PotionMortalCoil(int id, int color)
/*    */   {
/* 14 */     super(id, true, 0);
/* 15 */     setIncurable();
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 20 */     return duration == 1;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 25 */     EntityUtil.instantDeath(entity, null);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionMortalCoil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */