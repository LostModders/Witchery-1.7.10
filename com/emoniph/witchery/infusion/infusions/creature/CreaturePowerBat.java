/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.util.KeyBindHelper;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreaturePowerBat
/*    */   extends CreaturePower
/*    */ {
/*    */   public CreaturePowerBat(int powerID, Class<? extends EntityLiving> creatureType)
/*    */   {
/* 25 */     super(powerID, creatureType);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 30 */     player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 400));
/*    */   }
/*    */   
/*    */   public void onUpdate(World world, EntityPlayer player)
/*    */   {
/* 35 */     Minecraft minecraft = Minecraft.func_71410_x();
/* 36 */     if ((KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74314_A)) && (player.field_70181_x > 0.0D)) {
/* 37 */       player.field_70181_x += 0.06699999910593032D;
/*    */     }
/*    */     
/* 40 */     if ((!player.field_70122_E) && 
/* 41 */       (KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74314_A))) {
/* 42 */       player.field_70181_x = 0.41999998688697815D;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void onFalling(World worldObj, EntityPlayer player, LivingFallEvent event)
/*    */   {
/* 49 */     if (event.distance > 5.0F) {
/* 50 */       event.distance = 5.0F;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */