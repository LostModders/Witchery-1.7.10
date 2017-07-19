/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.EnderTeleportEvent;
/*    */ 
/*    */ public class PotionEnderInhibition extends PotionBase implements IHandleEnderTeleport
/*    */ {
/*    */   public PotionEnderInhibition(int id, int color)
/*    */   {
/* 13 */     super(id, true, color);
/*    */   }
/*    */   
/*    */   public void onEnderTeleport(World world, EntityLivingBase entity, EnderTeleportEvent event, int amplifier)
/*    */   {
/* 18 */     event.setCanceled(true);
/*    */   }
/*    */   
/*    */   public static boolean isActive(Entity entity, int amplifier) {
/* 22 */     if ((entity != null) && ((entity instanceof EntityLivingBase))) {
/* 23 */       EntityLivingBase living = (EntityLivingBase)entity;
/* 24 */       return (living.func_70644_a(Witchery.Potions.ENDER_INHIBITION)) && (living.func_70660_b(Witchery.Potions.ENDER_INHIBITION).func_76458_c() >= amplifier);
/*    */     }
/*    */     
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionEnderInhibition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */