/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PotionPoisonWeapons extends PotionBase implements IHandleLivingHurt
/*    */ {
/*    */   public PotionPoisonWeapons(int id, int color)
/*    */   {
/* 14 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onLivingHurt(net.minecraft.world.World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 19 */     if ((!world.field_72995_K) && 
/* 20 */       (event.source.func_76346_g() != null) && ((event.source.func_76346_g() instanceof EntityLivingBase)) && (isValidDamageType(event.source.func_76355_l())))
/*    */     {
/* 22 */       EntityLivingBase attacker = (EntityLivingBase)event.source.func_76346_g();
/* 23 */       PotionEffect poisonedAttack = attacker.func_70660_b(this);
/* 24 */       if (poisonedAttack != null) {
/* 25 */         switch (poisonedAttack.func_76458_c()) {
/*    */         case 0: 
/* 27 */           entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, TimeUtil.secsToTicks(5), 0));
/* 28 */           break;
/*    */         case 1: 
/* 30 */           entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, TimeUtil.secsToTicks(5), 1));
/* 31 */           break;
/*    */         case 2: 
/* 33 */           entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, TimeUtil.secsToTicks(15), 1));
/* 34 */           break;
/*    */         case 3: 
/*    */         default: 
/* 37 */           entity.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, TimeUtil.secsToTicks(20), 0));
/*    */         }
/*    */         
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean isValidDamageType(String damageType)
/*    */   {
/* 46 */     return (damageType.equals("mob")) || (damageType.equals("player"));
/*    */   }
/*    */   
/*    */   public boolean handleAllHurtEvents()
/*    */   {
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionPoisonWeapons.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */