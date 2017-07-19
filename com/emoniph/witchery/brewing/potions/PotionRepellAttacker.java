/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.util.EntityPosition;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PotionRepellAttacker extends PotionBase implements IHandleLivingHurt
/*    */ {
/*    */   public PotionRepellAttacker(int id, int color)
/*    */   {
/* 13 */     super(id, color);
/*    */   }
/*    */   
/*    */   public boolean handleAllHurtEvents()
/*    */   {
/* 18 */     return false;
/*    */   }
/*    */   
/*    */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 23 */     if (!world.field_72995_K) {
/* 24 */       EntityLivingBase attacker = (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityLivingBase)) ? (EntityLivingBase)event.source.func_76346_g() : null;
/*    */       
/*    */ 
/* 27 */       double MAX_RANGE = 3.0D;
/* 28 */       double MAX_RANGE_SQ = 9.0D;
/* 29 */       if ((attacker != null) && (attacker != entity) && (!event.source.func_76352_a()) && (attacker.func_70068_e(entity) < 9.0D)) {
/* 30 */         com.emoniph.witchery.util.EntityUtil.pushback(world, attacker, new EntityPosition(entity), 1.0D + amplifier * 0.75D, 0.5D + amplifier * 0.2D);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionRepellAttacker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */