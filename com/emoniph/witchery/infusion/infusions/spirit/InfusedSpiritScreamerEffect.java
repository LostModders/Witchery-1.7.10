/*    */ package com.emoniph.witchery.infusion.infusions.spirit;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ public class InfusedSpiritScreamerEffect extends InfusedSpiritEffect
/*    */ {
/*    */   public InfusedSpiritScreamerEffect(int id, int spirits, int spectres, int banshees, int poltergeists)
/*    */   {
/* 15 */     super(id, "screamer", spirits, spectres, banshees, poltergeists);
/*    */   }
/*    */   
/*    */   public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities)
/*    */   {
/* 20 */     if (triggered) {
/* 21 */       ParticleEffect.REDDUST.send(tile.func_145838_q() != Witchery.Blocks.FETISH_WITCHS_LADDER ? SoundEffect.WITCHERY_MOB_SPECTRE_SPECTRE_HIT : SoundEffect.NONE, tile.func_145831_w(), 0.5D + tile.field_145851_c, 0.3D + tile.field_145848_d, 0.5D + tile.field_145849_e, 0.2D, 0.5D, 16);
/*    */     }
/*    */     
/* 24 */     return triggered;
/*    */   }
/*    */   
/*    */   public boolean isRedstoneSignaller()
/*    */   {
/* 29 */     return true;
/*    */   }
/*    */   
/*    */   public double getRadius() {
/* 33 */     return 16.0D;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/spirit/InfusedSpiritScreamerEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */