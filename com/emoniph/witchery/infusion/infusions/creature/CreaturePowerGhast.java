/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import net.minecraft.entity.monster.EntityGhast;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreaturePowerGhast
/*    */   extends CreaturePower
/*    */ {
/*    */   public CreaturePowerGhast(int powerID)
/*    */   {
/* 24 */     super(powerID, EntityGhast.class);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 29 */     if (!world.field_72995_K) {
/* 30 */       world.func_72889_a((EntityPlayer)null, 1008, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 0);
/* 31 */       float f = 1.0F;
/* 32 */       double motionX = -MathHelper.func_76126_a(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F) * f;
/*    */       
/* 34 */       double motionZ = MathHelper.func_76134_b(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F) * f;
/*    */       
/* 36 */       double motionY = -MathHelper.func_76126_a(player.field_70125_A / 180.0F * 3.1415927F) * f;
/* 37 */       EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, player, motionX, motionY, motionZ);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 47 */       entitylargefireball.func_70012_b(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v, entitylargefireball.field_70177_z, entitylargefireball.field_70125_A);
/*    */       
/* 49 */       entitylargefireball.func_70107_b(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/* 50 */       double d6 = MathHelper.func_76133_a(motionX * motionX + motionY * motionY + motionZ * motionZ);
/* 51 */       entitylargefireball.field_70232_b = (motionX / d6 * 0.1D);
/* 52 */       entitylargefireball.field_70233_c = (motionY / d6 * 0.1D);
/* 53 */       entitylargefireball.field_70230_d = (motionZ / d6 * 0.1D);
/* 54 */       double d8 = 1.0D;
/* 55 */       Vec3 vec3 = player.func_70676_i(1.0F);
/* 56 */       entitylargefireball.field_70165_t = (player.field_70165_t + vec3.field_72450_a * d8);
/* 57 */       entitylargefireball.field_70163_u = (player.field_70163_u + player.field_70131_O / 2.0F + 0.5D);
/* 58 */       entitylargefireball.field_70161_v = (player.field_70161_v + vec3.field_72449_c * d8);
/* 59 */       world.func_72838_d(entitylargefireball);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
/*    */   {
/* 65 */     if ((event.source.func_76347_k()) && (event.isCancelable())) {
/* 66 */       int currentEnergy = Infusion.getCurrentEnergy(player);
/* 67 */       if ((currentEnergy >= 3) && (!player.func_82165_m(Potion.field_76426_n.field_76415_H))) {
/* 68 */         Infusion.setCurrentEnergy(player, currentEnergy - 3);
/* 69 */         player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 200, 0));
/* 70 */         SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerGhast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */