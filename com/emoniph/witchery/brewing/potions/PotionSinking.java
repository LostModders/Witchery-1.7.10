/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PotionSinking extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionSinking(int id, int color)
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
/* 23 */     if ((entity instanceof EntityPlayer)) {
/* 24 */       EntityPlayer player = (EntityPlayer)entity;
/* 25 */       if (world.field_72995_K) {
/* 26 */         if (player.func_70090_H()) {
/* 27 */           if ((player.field_70181_x < -0.03D) && (!player.field_70122_E)) {
/* 28 */             player.field_70181_x *= (1.5D + Math.min(0.05D * amplifier, 0.2D));
/* 29 */           } else if ((player.field_70122_E) || (!player.func_70055_a(net.minecraft.block.material.Material.field_151586_h)) || (player.field_70181_x <= 0.0D)) {}
/*    */ 
/*    */ 
/*    */         }
/* 33 */         else if ((!player.field_71075_bZ.field_75098_d) && (player.field_71075_bZ.field_75101_c) && (player.field_71075_bZ.field_75100_b))
/*    */         {
/* 35 */           player.field_70181_x = -0.20000000298023224D;
/*    */         }
/*    */         
/*    */       }
/*    */     }
/* 40 */     else if ((world.field_72995_K) && (world.func_82737_E() % 20L == 3L) && 
/* 41 */       (entity.func_70090_H())) {
/* 42 */       if (entity.field_70181_x < 0.0D) {
/* 43 */         entity.field_70181_x *= (1.0D + Math.min(0.1D * (amplifier + 2), 0.4D));
/* 44 */       } else if (entity.field_70181_x > 0.0D) {
/* 45 */         entity.field_70181_x *= (1.0D - Math.min(0.1D * (amplifier + 2), 0.4D));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionSinking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */