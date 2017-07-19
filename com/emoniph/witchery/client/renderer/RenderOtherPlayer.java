/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class RenderOtherPlayer extends net.minecraft.client.renderer.entity.RenderPlayer
/*    */ {
/*    */   protected net.minecraft.util.ResourceLocation func_110775_a(AbstractClientPlayer entity)
/*    */   {
/* 11 */     EntityPlayer player = entity;
/* 12 */     ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 13 */     return playerEx.getOtherPlayerSkinLocation();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderOtherPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */