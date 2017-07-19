/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ 
/*    */ public class PotionSprouting extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionSprouting(int id, int color)
/*    */   {
/* 12 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 17 */     if ((!world.field_72995_K) && (world.func_82737_E() % 20L == 9L) && (world.field_73012_v.nextInt(4) == 0)) {
/* 18 */       com.emoniph.witchery.brewing.action.effect.BrewActionSprouting.growBranch(entity, 1 + amplifier);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionSprouting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */