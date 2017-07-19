/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class CreaturePowerZombie extends CreaturePower
/*    */ {
/*    */   public CreaturePowerZombie(int powerID)
/*    */   {
/* 14 */     super(powerID, EntityZombie.class);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 19 */     if (!world.field_72995_K) {
/* 20 */       player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 600, 1));
/* 21 */       player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 600, 0));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */