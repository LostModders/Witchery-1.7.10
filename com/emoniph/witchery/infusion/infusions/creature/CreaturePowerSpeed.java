/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.util.KeyBindHelper;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreaturePowerSpeed
/*    */   extends CreaturePower
/*    */ {
/*    */   public CreaturePowerSpeed(int powerID, Class<? extends EntityLiving> creatureType)
/*    */   {
/* 23 */     super(powerID, creatureType);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 28 */     player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 400, 3));
/* 29 */     SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/*    */   }
/*    */   
/*    */   public void onUpdate(World world, EntityPlayer player)
/*    */   {
/* 34 */     Minecraft minecraft = Minecraft.func_71410_x();
/* 35 */     int var5 = MathHelper.func_76128_c(player.field_70165_t);
/* 36 */     int var6 = MathHelper.func_76128_c(player.field_70163_u - 2.0D);
/* 37 */     int var7 = MathHelper.func_76128_c(player.field_70161_v);
/*    */     
/* 39 */     if ((KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74351_w)) || (KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74368_y)) || (KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74370_x)) || (KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74366_z)))
/*    */     {
/* 41 */       if (world.func_147439_a(var5, var6, var7) != Blocks.field_150432_aD) {
/* 42 */         if ((player.field_70122_E) && 
/* 43 */           (!player.func_70090_H())) {
/* 44 */           player.field_70159_w *= 1.4500000476837158D;
/* 45 */           player.field_70179_y *= 1.4500000476837158D;
/*    */         }
/*    */       }
/*    */       else {
/* 49 */         player.field_70159_w *= 1.100000023841858D;
/* 50 */         player.field_70179_y *= 1.100000023841858D;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerSpeed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */