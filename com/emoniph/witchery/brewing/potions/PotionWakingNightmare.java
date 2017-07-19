/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityNightmare;
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ 
/*    */ public class PotionWakingNightmare extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionWakingNightmare(int id, int color)
/*    */   {
/* 19 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public void postContructInitialize()
/*    */   {
/* 24 */     setPermenant();
/* 25 */     setIncurable();
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 30 */     if ((!world.field_72995_K) && (world.func_82737_E() % 20L == 3L) && 
/* 31 */       (entity.field_71093_bK != Config.instance().dimensionDreamID)) {
/* 32 */       if (world.field_73012_v.nextInt(amplifier > 1 ? 60 : amplifier > 3 ? 30 : 180) == 0) {
/* 33 */         double R = 16.0D;
/* 34 */         double H = 8.0D;
/* 35 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(entity.field_70165_t - 16.0D, entity.field_70163_u - 8.0D, entity.field_70161_v - 16.0D, entity.field_70165_t + 16.0D, entity.field_70163_u + 8.0D, entity.field_70161_v + 16.0D);
/*    */         
/* 37 */         List<EntityNightmare> entities = world.func_72872_a(EntityNightmare.class, bounds);
/*    */         
/* 39 */         boolean doNothing = false;
/* 40 */         for (EntityNightmare nightmare : entities) {
/* 41 */           if (nightmare.getVictimName().equalsIgnoreCase(entity.func_70005_c_())) {
/* 42 */             doNothing = true;
/* 43 */             break;
/*    */           }
/*    */         }
/* 46 */         if (!doNothing) {
/* 47 */           Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70163_u), MathHelper.func_76128_c(entity.field_70161_v), entity, 2, 6, null, SoundEffect.NONE);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionWakingNightmare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */