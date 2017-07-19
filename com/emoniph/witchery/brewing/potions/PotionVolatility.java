/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class PotionVolatility extends PotionBase implements IHandleLivingHurt
/*    */ {
/*    */   public PotionVolatility(int id, int color)
/*    */   {
/* 15 */     super(id, true, color);
/* 16 */     setIncurable();
/*    */   }
/*    */   
/*    */   public boolean handleAllHurtEvents()
/*    */   {
/* 21 */     return false;
/*    */   }
/*    */   
/*    */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 26 */     if ((!world.field_72995_K) && (isExplodableDamage(event.source))) {
/* 27 */       boolean breakable = Config.instance().allowVolatilityPotionBlockDamage;
/* 28 */       if (breakable) {
/* 29 */         Coord c = new Coord(entity);
/* 30 */         breakable = com.emoniph.witchery.util.BlockProtect.checkModsForBreakOK(world, c.x, c.y, c.z, entity);
/*    */       }
/* 32 */       if ((event.source.func_94541_c()) || (world.field_73012_v.nextInt(5 - Math.min(amplifier, 3)) == 0)) {
/* 33 */         if (world.field_73012_v.nextInt(amplifier + 3) == 0) {
/* 34 */           entity.func_82170_o(this.field_76415_H);
/*    */         }
/* 36 */         world.func_72876_a(event.source.func_94541_c() ? entity : null, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, Math.min(2.0F + 0.5F * amplifier, 3.0F), breakable);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean isExplodableDamage(DamageSource source)
/*    */   {
/* 43 */     return (source != DamageSource.field_76369_e) && (source != DamageSource.field_76368_d) && (source != DamageSource.field_76379_h) && (source != DamageSource.field_76380_i) && (source != DamageSource.field_76366_f);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionVolatility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */