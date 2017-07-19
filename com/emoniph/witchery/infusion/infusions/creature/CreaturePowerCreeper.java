/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ public class CreaturePowerCreeper extends CreaturePower
/*    */ {
/*    */   private static final float WEB_DAMAGE = 1.0F;
/*    */   
/*    */   public CreaturePowerCreeper(int powerID)
/*    */   {
/* 17 */     super(powerID, net.minecraft.entity.monster.EntityCreeper.class);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 22 */   private final int explosionRadius = 3;
/*    */   
/*    */   public int activateCost(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 26 */     int baseCost = super.activateCost(world, player, elapsedTicks, mop);
/* 27 */     return elapsedTicks >= 60 ? baseCost * 2 : baseCost;
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 32 */     if (!world.field_72995_K) {
/* 33 */       getClass();getClass();world.func_72876_a(player, player.field_70165_t, player.field_70163_u, player.field_70161_v, elapsedTicks >= 60 ? 3.0F * 2.0F : 3.0F, world.func_82736_K().func_82766_b("mobGriefing"));
/*    */     }
/*    */   }
/*    */   
/*    */   public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
/*    */   {
/* 39 */     if (event.source.func_76347_k()) {
/* 40 */       StackTraceElement[] stack = Thread.currentThread().getStackTrace();
/* 41 */       for (StackTraceElement element : stack) {
/* 42 */         if (element.getMethodName().equals("onStruckByLightning"))
/*    */         {
/* 44 */           int power = Infusion.getNBT(player).func_74762_e("witcheryInfusionCharges");
/* 45 */           Infusion.getNBT(player).func_74768_a("witcheryInfusionCharges", Math.min(power + 25, 200));
/* 46 */           Infusion.syncPlayer(world, player);
/* 47 */           if (event.isCancelable()) {
/* 48 */             event.setCanceled(true);
/*    */           }
/* 50 */           return;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */