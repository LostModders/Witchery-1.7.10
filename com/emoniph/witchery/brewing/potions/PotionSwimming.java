/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ 
/*    */ public class PotionSwimming extends PotionBase implements IHandleLivingUpdate
/*    */ {
/*    */   public PotionSwimming(int id, int color)
/*    */   {
/* 15 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 20 */     if ((entity instanceof EntityPlayer)) {
/* 21 */       EntityPlayer player = (EntityPlayer)entity;
/* 22 */       if ((world.field_72995_K) && 
/* 23 */         (player.func_70090_H()) && (!player.func_70644_a(Witchery.Potions.PARALYSED))) {
/* 24 */         Minecraft minecraft = Minecraft.func_71410_x();
/* 25 */         if (com.emoniph.witchery.util.KeyBindHelper.isKeyBindDown(Minecraft.func_71410_x().field_71474_y.field_74351_w)) {
/* 26 */           amplifier = 3;
/* 27 */           player.field_70159_w *= (1.15D + 0.03D * amplifier);
/* 28 */           player.field_70179_y *= (1.15D + 0.03D * amplifier);
/*    */         }
/*    */         
/*    */       }
/*    */     }
/* 33 */     else if ((world.field_72995_K) && (world.func_82737_E() % 10L == 3L) && 
/* 34 */       (entity.func_70090_H()) && (entity.func_70644_a(Witchery.Potions.PARALYSED))) {
/* 35 */       entity.field_70159_w *= (1.15D + 0.03D * amplifier);
/* 36 */       entity.field_70179_y *= (1.15D + 0.03D * amplifier);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionSwimming.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */