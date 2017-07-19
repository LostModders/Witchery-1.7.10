/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class PotionOverheating extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionOverheating(int id, int color)
/*    */   {
/* 12 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public void postContructInitialize()
/*    */   {
/* 17 */     setPermenant();
/* 18 */     setIncurable();
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 23 */     if ((!world.field_72995_K) && (world.func_82737_E() % 5L == 3L) && 
/* 24 */       (!entity.func_70027_ad())) if (world.field_73012_v.nextInt(amplifier > 0 ? 25 : amplifier > 1 ? 20 : 30) == 0) {
/* 25 */         int x = net.minecraft.util.MathHelper.func_76128_c(entity.field_70165_t);
/* 26 */         int z = net.minecraft.util.MathHelper.func_76128_c(entity.field_70161_v);
/* 27 */         BiomeGenBase biome = world.func_72807_a(x, z);
/* 28 */         if ((biome.field_76750_F >= 1.5D) && ((!biome.func_76738_d()) || (!world.func_72896_J())) && (!entity.func_70090_H()))
/*    */         {
/* 30 */           entity.func_70015_d(Math.min(world.field_73012_v.nextInt(amplifier < 3 ? 2 : amplifier) + 1, 4));
/*    */         }
/*    */       }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionOverheating.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */