/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.entity.passive.EntityAnimal;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ 
/*    */ public class PotionLove extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionLove(int id, int color)
/*    */   {
/* 18 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 23 */     if ((!world.field_72995_K) && (world.func_82737_E() % 20L == 7L)) {
/* 24 */       if ((entity instanceof EntityAnimal)) {
/* 25 */         EntityAnimal animal = (EntityAnimal)entity;
/* 26 */         if ((animal.func_70874_b() >= 0) && (!animal.func_70880_s())) {
/* 27 */           animal.func_70873_a(0);
/* 28 */           animal.func_146082_f(null);
/*    */         }
/* 30 */       } else if ((entity instanceof EntityZombie)) {
/* 31 */         EntityZombie zombie = (EntityZombie)entity;
/* 32 */         if (!zombie.func_70631_g_()) {
/* 33 */           for (Object obj : zombie.field_70714_bg.field_75782_a) {
/* 34 */             EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
/* 35 */             if ((task.field_75733_a instanceof EntityAIZombieMateNow)) {
/* 36 */               ((EntityAIZombieMateNow)task.field_75733_a).beginMating();
/* 37 */               return;
/*    */             }
/*    */           }
/* 40 */           EntityAIZombieMateNow ai = new EntityAIZombieMateNow(zombie);
/* 41 */           ai.beginMating();
/* 42 */           zombie.field_70714_bg.func_75776_a(1, ai);
/*    */         }
/* 44 */       } else if ((entity instanceof EntityVillager)) {
/* 45 */         EntityVillager villager = (EntityVillager)entity;
/* 46 */         if ((!villager.func_70631_g_()) && (!villager.func_70941_o())) {
/* 47 */           for (Object obj : villager.field_70714_bg.field_75782_a) {
/* 48 */             EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
/* 49 */             if ((task.field_75733_a instanceof EntityAIVillagerMateNow)) {
/* 50 */               ((EntityAIVillagerMateNow)task.field_75733_a).beginMating();
/* 51 */               return;
/*    */             }
/*    */           }
/* 54 */           EntityAIVillagerMateNow ai = new EntityAIVillagerMateNow(villager);
/* 55 */           ai.beginMating();
/* 56 */           villager.field_70714_bg.func_75776_a(1, ai);
/*    */         }
/* 58 */       } else if (((entity instanceof net.minecraft.entity.player.EntityPlayer)) && 
/* 59 */         (world.field_73012_v.nextInt(2) == 0)) {
/* 60 */         ParticleEffect.HEART.send(com.emoniph.witchery.util.SoundEffect.NONE, world, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0D, event.entityLiving.field_70161_v, 0.6D, 2.0D, 16);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionLove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */