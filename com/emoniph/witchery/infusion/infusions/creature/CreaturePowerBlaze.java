/*     */ package com.emoniph.witchery.infusion.infusions.creature;
/*     */ 
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ public class CreaturePowerBlaze extends CreaturePower
/*     */ {
/*     */   public CreaturePowerBlaze(int powerID)
/*     */   {
/*  20 */     super(powerID, net.minecraft.entity.monster.EntityBlaze.class);
/*     */   }
/*     */   
/*     */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*     */   {
/*  25 */     if (!world.field_72995_K) {
/*  26 */       world.func_72889_a((EntityPlayer)null, 1009, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 0);
/*  27 */       int BALLS = 3;
/*  28 */       for (int i = 0; i < 3; i++)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */         float f = 1.0F;
/*  57 */         double motionX = -MathHelper.func_76126_a(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F) * f;
/*     */         
/*  59 */         double motionZ = MathHelper.func_76134_b(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F) * f;
/*     */         
/*  61 */         double motionY = -MathHelper.func_76126_a(player.field_70125_A / 180.0F * 3.1415927F) * f;
/*     */         
/*  63 */         EntitySmallFireball fireball = new EntitySmallFireball(world, player, motionX, motionY, motionZ);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  72 */         fireball.func_70012_b(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v, fireball.field_70177_z, fireball.field_70125_A);
/*     */         
/*  74 */         fireball.func_70107_b(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/*     */         
/*  76 */         motionX += world.field_73012_v.nextDouble() * 0.2D;
/*  77 */         motionZ += world.field_73012_v.nextDouble() * 0.2D;
/*     */         
/*  79 */         double d6 = MathHelper.func_76133_a(motionX * motionX + motionY * motionY + motionZ * motionZ);
/*  80 */         fireball.field_70232_b = (motionX / d6 * 0.1D);
/*  81 */         fireball.field_70233_c = (motionY / d6 * 0.1D);
/*  82 */         fireball.field_70230_d = (motionZ / d6 * 0.1D);
/*  83 */         double d8 = 1.0D;
/*  84 */         Vec3 vec3 = player.func_70676_i(1.0F);
/*  85 */         fireball.field_70165_t = (player.field_70165_t + vec3.field_72450_a * d8);
/*  86 */         fireball.field_70163_u = (player.field_70163_u + player.field_70131_O / 2.0F + 0.5D);
/*  87 */         fireball.field_70161_v = (player.field_70161_v + vec3.field_72449_c * d8);
/*  88 */         world.func_72838_d(fireball);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
/*     */   {
/*  95 */     if ((event.source.func_76347_k()) && (event.isCancelable())) {
/*  96 */       int currentEnergy = Infusion.getCurrentEnergy(player);
/*  97 */       if ((currentEnergy >= 3) && (!player.func_82165_m(Potion.field_76426_n.field_76415_H))) {
/*  98 */         Infusion.setCurrentEnergy(player, currentEnergy - 3);
/*  99 */         player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 200, 0));
/* 100 */         SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerBlaze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */