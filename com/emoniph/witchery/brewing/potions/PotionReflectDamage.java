/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PotionReflectDamage extends PotionBase implements IHandleLivingHurt
/*    */ {
/*    */   public PotionReflectDamage(int id, int color)
/*    */   {
/* 10 */     super(id, color);
/*    */   }
/*    */   
/*    */   public boolean handleAllHurtEvents()
/*    */   {
/* 15 */     return false;
/*    */   }
/*    */   
/*    */   public void onLivingHurt(net.minecraft.world.World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 20 */     if (!world.field_72995_K) {
/* 21 */       EntityLivingBase attacker = (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityLivingBase)) ? (EntityLivingBase)event.source.func_76346_g() : null;
/*    */       
/*    */ 
/* 24 */       if ((attacker != null) && (attacker != entity) && ((!event.source.func_76352_a()) || (amplifier >= 2))) {
/* 25 */         float amount = (float)Math.ceil(event.ammount * 0.1F * (amplifier + (!event.source.func_76352_a() ? 1 : 0)));
/*    */         
/* 27 */         attacker.func_70097_a(event.source, amount);
/* 28 */         event.ammount -= amount;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionReflectDamage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */