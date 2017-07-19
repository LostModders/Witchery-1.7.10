/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.network.PacketPipeline;
/*    */ import com.emoniph.witchery.network.PacketSound;
/*    */ import com.emoniph.witchery.util.KeyBindHelper;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import com.emoniph.witchery.util.TargetPointUtil;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreaturePowerJump
/*    */   extends CreaturePower
/*    */ {
/*    */   public CreaturePowerJump(int powerID, Class<? extends EntityLiving> creatureType)
/*    */   {
/* 26 */     super(powerID, creatureType);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 31 */     player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 400, 3));
/* 32 */     SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/*    */   }
/*    */   
/*    */   public void onUpdate(World world, EntityPlayer player)
/*    */   {
/* 37 */     Minecraft minecraft = Minecraft.func_71410_x();
/* 38 */     if ((KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74314_A)) && (player.field_70181_x > 0.0D)) {
/* 39 */       player.field_70181_x += 0.06D;
/*    */     }
/*    */   }
/*    */   
/*    */   public void onFalling(World world, EntityPlayer player, LivingFallEvent event)
/*    */   {
/* 45 */     if (!world.field_72995_K) {
/* 46 */       Witchery.packetPipeline.sendToAllAround(new PacketSound(SoundEffect.MOB_SLIME_BIG, player), TargetPointUtil.from(player, 8.0D));
/* 47 */       event.distance = 0.0F;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerJump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */