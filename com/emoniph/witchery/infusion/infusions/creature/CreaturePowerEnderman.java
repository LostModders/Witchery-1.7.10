/*    */ package com.emoniph.witchery.infusion.infusions.creature;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.network.PacketParticles;
/*    */ import com.emoniph.witchery.network.PacketPipeline;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import com.emoniph.witchery.util.TargetPointUtil;
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class CreaturePowerEnderman extends CreaturePower
/*    */ {
/*    */   public CreaturePowerEnderman(int powerID)
/*    */   {
/* 18 */     super(powerID, EntityEnderman.class);
/*    */   }
/*    */   
/*    */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
/*    */   {
/* 23 */     if (!world.field_72995_K) {
/* 24 */       if (mop != null) {
/* 25 */         Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.PORTAL, SoundEffect.MOB_ENDERMEN_PORTAL, player, 0.5D, 2.0D), TargetPointUtil.from(player, 16.0D));
/*    */         
/*    */ 
/* 28 */         com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.teleportEntity(player, mop);
/*    */         
/* 30 */         Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.PORTAL, SoundEffect.MOB_ENDERMEN_PORTAL, player, 0.5D, 2.0D), TargetPointUtil.from(player, 16.0D));
/*    */       }
/*    */       else {
/* 33 */         world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePowerEnderman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */