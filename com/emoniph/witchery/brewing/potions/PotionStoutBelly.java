/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PotionStoutBelly extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionStoutBelly(int id, int color)
/*    */   {
/* 11 */     super(id, color);
/* 12 */     setIncurable();
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 17 */     if ((!world.field_72995_K) && (world.func_72820_D() % 20L == 3L) && 
/* 18 */       (amplifier > 0) && (entity.func_70644_a(Potion.field_76431_k))) {
/* 19 */       entity.func_82170_o(Potion.field_76431_k.field_76415_H);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionStoutBelly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */