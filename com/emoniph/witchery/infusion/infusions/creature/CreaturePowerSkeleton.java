/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.monster.EntitySkeleton;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.projectile.EntityArrow;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreaturePowerSkeleton
/*    */   extends CreaturePower
/*    */ {
/*    */   public CreaturePowerSkeleton(int powerID)
/*    */   {
/* 24 */     super(powerID, EntitySkeleton.class);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 29 */     if (!world.field_72995_K)
/*    */     {
/* 31 */       int j = elapsedTicks;
/*    */       
/* 33 */       float f = j / 20.0F;
/* 34 */       f = (f * f + f * 2.0F) / 3.0F;
/*    */       
/* 36 */       if (f > 1.0F)
/*    */       {
/* 38 */         f = 1.0F;
/*    */       }
/*    */       
/* 41 */       world.func_72956_a(player, "random.bow", 1.0F, 1.0F / (world.field_73012_v.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*    */       
/* 43 */       EntityArrow entityarrow = new EntityArrow(world, player, f * 2.0F);
/*    */       
/* 45 */       if (f == 1.0F)
/*    */       {
/* 47 */         entityarrow.func_70243_d(true);
/*    */       }
/*    */       
/* 50 */       int EXTRA_PUNCH = 1;
/* 51 */       entityarrow.func_70240_a(1);
/* 52 */       int EXTRA_DAMAGE = 1;
/* 53 */       entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5D + 0.5D);
/*    */       
/*    */ 
/* 56 */       world.func_72838_d(entityarrow);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
/*    */   {
/* 62 */     if ((!world.field_72995_K) && 
/* 63 */       (event.source == DamageSource.field_76369_e)) {
/* 64 */       int currentEnergy = Infusion.getCurrentEnergy(player);
/* 65 */       if (currentEnergy >= 5) {
/* 66 */         Infusion.setCurrentEnergy(player, currentEnergy - 5);
/* 67 */         SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/* 68 */         player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 600, 0));
/* 69 */         player.func_70050_g(30);
/* 70 */         event.setCanceled(true);
/*    */       }
/*    */     }
/*    */     
/* 74 */     super.onDamage(world, player, event);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerSkeleton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */