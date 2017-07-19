/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.util.KeyBindHelper;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntitySquid;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreaturePowerSquid
/*    */   extends CreaturePower
/*    */ {
/*    */   public CreaturePowerSquid(int powerID)
/*    */   {
/* 27 */     super(powerID, EntitySquid.class);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 32 */     if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && 
/* 33 */       (mop.field_72308_g != null) && ((mop.field_72308_g instanceof EntityLivingBase))) {
/* 34 */       EntityLivingBase targetPlayer = (EntityLivingBase)mop.field_72308_g;
/* 35 */       world.func_72956_a(player, "random.fizz", 1.0F, 1.0F / (world.field_73012_v.nextFloat() * 0.4F + 1.2F) + 0.5F);
/* 36 */       targetPlayer.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 200));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void onUpdate(World world, EntityPlayer player)
/*    */   {
/* 43 */     if (player.func_70090_H()) {
/* 44 */       Minecraft minecraft = Minecraft.func_71410_x();
/* 45 */       if (KeyBindHelper.isKeyBindDown(Minecraft.func_71410_x().field_71474_y.field_74351_w))
/*    */       {
/*    */ 
/*    */ 
/* 49 */         player.field_70159_w *= 1.149999976158142D;
/* 50 */         player.field_70179_y *= 1.149999976158142D;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
/*    */   {
/* 57 */     if (event.source == DamageSource.field_76369_e) {
/* 58 */       int currentEnergy = Infusion.getCurrentEnergy(player);
/* 59 */       if (currentEnergy >= 1) {
/* 60 */         Infusion.setCurrentEnergy(player, currentEnergy - 1);
/* 61 */         SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/* 62 */         player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 1200, 1));
/* 63 */         player.func_70050_g(300);
/* 64 */         event.setCanceled(true);
/*    */       }
/*    */     }
/* 67 */     super.onDamage(world, player, event);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerSquid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */