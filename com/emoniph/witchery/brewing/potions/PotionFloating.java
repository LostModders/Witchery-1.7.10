/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PotionFloating extends PotionBase
/*    */ {
/*    */   public PotionFloating(int id, int color)
/*    */   {
/* 10 */     super(id, color);
/*    */   }
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier)
/*    */   {
/* 15 */     return true;
/*    */   }
/*    */   
/*    */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*    */   {
/* 20 */     int height = 3 + amplifier;
/* 21 */     int x = net.minecraft.util.MathHelper.func_76128_c(entity.field_70165_t);
/* 22 */     int y = net.minecraft.util.MathHelper.func_76128_c(entity.field_70163_u);
/* 23 */     int z = net.minecraft.util.MathHelper.func_76128_c(entity.field_70161_v);
/* 24 */     boolean isPlayer = entity instanceof net.minecraft.entity.player.EntityPlayer;
/* 25 */     boolean activeOnSide = ((isPlayer) && (entity.field_70170_p.field_72995_K)) || ((!isPlayer) && (!entity.field_70170_p.field_72995_K));
/*    */     
/* 27 */     entity.field_70143_R = 0.0F;
/* 28 */     if (activeOnSide) {
/* 29 */       boolean raised = false;
/* 30 */       for (int i = 1; i <= height; i++) {
/* 31 */         if (!entity.field_70170_p.func_147437_c(x, y - i, z)) {
/* 32 */           entity.field_70181_x = 0.25D;
/* 33 */           raised = true;
/* 34 */           break;
/*    */         }
/*    */       }
/* 37 */       if (!raised) {
/* 38 */         entity.field_70181_x = (entity.field_70170_p.field_73012_v.nextInt(5) == 0 ? -0.05D : 0.0D);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionFloating.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */