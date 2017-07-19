/*    */ package com.emoniph.witchery.infusion.infusions.spirit;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ public class InfusedSpiritTwisterEffect extends InfusedSpiritEffect
/*    */ {
/*    */   private static final double RANDOM_SPIN_RADIUS = 3.0D;
/*    */   private static final double RANDOM_SPIN_RADIUS_SQ = 9.0D;
/*    */   
/*    */   public InfusedSpiritTwisterEffect(int id, int spirits, int spectres, int banshees, int poltergeists)
/*    */   {
/* 18 */     super(id, "twister", spirits, spectres, banshees, poltergeists);
/*    */   }
/*    */   
/*    */   public int getCooldownTicks()
/*    */   {
/* 23 */     return 10;
/*    */   }
/*    */   
/*    */ 
/*    */   public double getRadius()
/*    */   {
/* 29 */     return 8.0D;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities)
/*    */   {
/* 37 */     if (triggered) {
/* 38 */       for (EntityLivingBase entity : foundEntities) {
/* 39 */         if ((entity instanceof EntityPlayer)) {
/* 40 */           EntityPlayer player = (EntityPlayer)entity;
/* 41 */           if ((player.field_71071_by.func_70440_f(0) != null) || (player.field_71071_by.func_70440_f(1) != null) || (player.field_71071_by.func_70440_f(2) != null) || (player.field_71071_by.func_70440_f(3) != null) || (player.func_70694_bm() != null))
/*    */           {
/*    */ 
/*    */ 
/* 45 */             double yawRadians = Math.atan2(player.field_70161_v - (0.5D + tile.field_145849_e), player.field_70165_t - (0.5D + tile.field_145851_c));
/* 46 */             double yaw = Math.toDegrees(yawRadians) + 180.0D;
/* 47 */             double playerYaw = (player.field_70177_z + 90.0F) % 360.0F;
/* 48 */             if (playerYaw < 0.0D) {
/* 49 */               playerYaw += 360.0D;
/*    */             }
/* 51 */             float rev = ((float)yaw + 90.0F) % 360.0F;
/* 52 */             double ARC = 45.0D;
/* 53 */             double diff = Math.abs(yaw - playerYaw);
/* 54 */             if ((360.0D - diff % 360.0D < 45.0D) || (diff % 360.0D < 45.0D))
/*    */             {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 63 */               if ((player instanceof net.minecraft.entity.player.EntityPlayerMP)) {
/* 64 */                 S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(player.field_70165_t, player.field_70163_u, player.field_70161_v, rev, player.field_70125_A, false);
/* 65 */                 com.emoniph.witchery.Witchery.packetPipeline.sendTo(packet, player);
/*    */               }
/*    */               
/*    */             }
/*    */             
/*    */           }
/*    */           
/*    */         }
/* 73 */         else if ((entity instanceof EntityLiving)) {
/* 74 */           EntityLiving creature = (EntityLiving)entity;
/* 75 */           if (creature.func_110138_aP() < 50.0F) {
/* 76 */             com.emoniph.witchery.util.EntityUtil.dropAttackTarget(creature);
/* 77 */             if (foundEntities.size() > 1) {
/* 78 */               com.emoniph.witchery.util.EntityUtil.setTarget(creature, (EntityLivingBase)foundEntities.get(tile.func_145831_w().field_73012_v.nextInt(foundEntities.size())));
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 84 */     return triggered;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/spirit/InfusedSpiritTwisterEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */