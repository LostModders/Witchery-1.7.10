/*    */ package com.emoniph.witchery.infusion.infusions.spirit;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ public class InfusedSpiritEnhancedPoppetEffect extends InfusedSpiritEffect
/*    */ {
/*    */   public InfusedSpiritEnhancedPoppetEffect(int id, int spirits, int spectres, int banshees, int poltergeists)
/*    */   {
/* 11 */     super(id, "enhancedpoppets", spirits, spectres, banshees, poltergeists);
/*    */   }
/*    */   
/*    */   public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities)
/*    */   {
/* 16 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/spirit/InfusedSpiritEnhancedPoppetEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */