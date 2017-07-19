/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityCaveSpider;
/*    */ import net.minecraft.entity.monster.EntityCreeper;
/*    */ import net.minecraft.entity.monster.EntitySkeleton;
/*    */ import net.minecraft.entity.monster.EntitySpider;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.entity.passive.EntityAnimal;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ 
/*    */ public class PotionReincarnate extends PotionBase implements IHandleLivingDeath
/*    */ {
/*    */   public PotionReincarnate(int id, int color)
/*    */   {
/* 23 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onLivingDeath(World world, EntityLivingBase entity, LivingDeathEvent event, int amplifier)
/*    */   {
/* 28 */     if (!world.field_72995_K) {
/* 29 */       Class<? extends EntityCreature> creatureToSpawn = null;
/* 30 */       if (((entity instanceof EntityAnimal)) || ((entity instanceof EntitySpider))) {
/* 31 */         if (amplifier > 2) {
/* 32 */           creatureToSpawn = EntityCreeper.class;
/* 33 */         } else if (amplifier > 1) {
/* 34 */           creatureToSpawn = EntityCaveSpider.class;
/*    */         } else {
/* 36 */           creatureToSpawn = EntitySpider.class;
/*    */         }
/*    */       }
/* 39 */       else if (amplifier > 2) {
/* 40 */         creatureToSpawn = net.minecraft.entity.monster.EntityBlaze.class;
/* 41 */       } else if (amplifier > 1) {
/* 42 */         creatureToSpawn = EntitySkeleton.class;
/*    */       } else {
/* 44 */         creatureToSpawn = EntityZombie.class;
/*    */       }
/*    */       
/*    */ 
/* 48 */       Entity attacker = event.source.func_76346_g();
/* 49 */       Infusion.spawnCreature(world, creatureToSpawn, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, (attacker != null) && ((attacker instanceof EntityLivingBase)) ? (EntityLivingBase)attacker : null, 0, 0, ParticleEffect.INSTANT_SPELL, SoundEffect.MOB_ZOMBIE_SAY);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionReincarnate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */