/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import net.minecraft.entity.monster.EntityPigZombie;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class CreaturePowerPigMan extends CreaturePower
/*    */ {
/*    */   public CreaturePowerPigMan(int powerID)
/*    */   {
/* 18 */     super(powerID, EntityPigZombie.class);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 23 */     if (!world.field_72995_K) {
/* 24 */       player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 600, 2));
/* 25 */       player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 600, 2));
/* 26 */       SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
/*    */   {
/* 32 */     if ((event.source.func_76347_k()) && (event.isCancelable())) {
/* 33 */       int currentEnergy = Infusion.getCurrentEnergy(player);
/* 34 */       if (currentEnergy >= 3) {
/* 35 */         Infusion.setCurrentEnergy(player, currentEnergy - 3);
/* 36 */         player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 1200, 0));
/* 37 */         SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/* 38 */         event.setCanceled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerPigMan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */