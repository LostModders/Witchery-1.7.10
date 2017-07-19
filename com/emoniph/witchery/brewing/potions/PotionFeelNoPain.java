/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.FoodStats;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PotionFeelNoPain extends PotionBase implements IHandleLivingHurt, IHandleLivingUpdate
/*    */ {
/*    */   public PotionFeelNoPain(int id, int color)
/*    */   {
/* 18 */     super(id, color);
/* 19 */     setIncurable();
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 24 */     if ((!world.field_72995_K) && (world.func_72820_D() % 20L == 2L) && 
/* 25 */       (amplifier > 0) && (!entity.func_70644_a(Potion.field_76431_k)) && (!entity.func_70644_a(com.emoniph.witchery.Witchery.Potions.STOUT_BELLY)) && (world.field_73012_v.nextInt(5 - Math.min(amplifier, 3)) == 0))
/*    */     {
/*    */ 
/* 28 */       entity.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, com.emoniph.witchery.util.TimeUtil.secsToTicks(6 + amplifier * 2)));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean handleAllHurtEvents()
/*    */   {
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 41 */     if ((!world.field_72995_K) && ((entity instanceof EntityPlayer)) && ((event.source.func_76355_l() == "mob") || (event.source.func_76355_l() == "player") || ((event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityLivingBase)))))
/*    */     {
/*    */ 
/*    */ 
/* 45 */       EntityPlayer player = (EntityPlayer)entity;
/* 46 */       float currentHealth = entity.func_110143_aJ();
/* 47 */       float newHealth = com.emoniph.witchery.util.EntityUtil.getHealthAfterDamage(event, currentHealth, entity);
/* 48 */       float damage = currentHealth - newHealth;
/* 49 */       int food = player.func_71024_bL().func_75116_a();
/* 50 */       if (food > 0) {
/* 51 */         int modifiedDamage = (int)Math.ceil(amplifier > 0 ? Math.max(damage / amplifier, amplifier > 1 ? 1.0F : 2.0F) : Math.max(damage * 2.0F, 3.0F));
/*    */         
/* 53 */         int foodPenalty = Math.min(modifiedDamage, food);
/* 54 */         player.func_71024_bL().func_75122_a(-foodPenalty, 2.0F);
/* 55 */         event.setCanceled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionFeelNoPain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */