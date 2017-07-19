/*    */ package com.emoniph.witchery.infusion.infusions.spirit;
/*    */ 
/*    */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ public class InfusedSpiritGhostWalkerEffect
/*    */   extends InfusedSpiritEffect
/*    */ {
/*    */   public InfusedSpiritGhostWalkerEffect(int id, int spirits, int spectres, int banshees, int poltergeists)
/*    */   {
/* 14 */     super(id, "ghostwalker", spirits, spectres, banshees, poltergeists);
/*    */   }
/*    */   
/*    */   public double getRadius()
/*    */   {
/* 19 */     return 8.0D;
/*    */   }
/*    */   
/*    */   public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities)
/*    */   {
/* 24 */     if (triggered) {
/* 25 */       for (EntityLivingBase entity : foundEntities) {
/* 26 */         if ((entity instanceof EntityPlayer)) {
/* 27 */           EntityPlayer player = (EntityPlayer)entity;
/* 28 */           if (WorldProviderDreamWorld.getPlayerIsGhost(player)) {
/* 29 */             WorldProviderDreamWorld.setPlayerSkipNextManifestationReduction(player);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 35 */     return triggered;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/spirit/InfusedSpiritGhostWalkerEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */