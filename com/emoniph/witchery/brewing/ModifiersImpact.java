/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.util.EntityPosition;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModifiersImpact
/*    */ {
/*    */   public int extent;
/*    */   public int lifetime;
/*    */   private Dispersal dispersal;
/*    */   private boolean onlyInstant;
/*    */   public final EntityPlayer thrower;
/*    */   public final EntityPosition impactPosition;
/*    */   public final boolean ritualised;
/*    */   public final int covenSize;
/*    */   
/*    */   public ModifiersImpact(EntityPosition impactPosition, boolean ritualised, int covenSize, EntityPlayer thrower)
/*    */   {
/* 21 */     this.thrower = thrower;
/* 22 */     this.impactPosition = impactPosition;
/* 23 */     this.ritualised = ritualised;
/* 24 */     this.covenSize = covenSize;
/*    */   }
/*    */   
/*    */   public void setGeneralDispersal(Dispersal dispersal) {
/* 28 */     this.dispersal = dispersal;
/*    */   }
/*    */   
/*    */   public Dispersal getDispersal() {
/* 32 */     if (this.onlyInstant) {
/* 33 */       return new DispersalInstant();
/*    */     }
/* 35 */     return this.dispersal;
/*    */   }
/*    */   
/*    */   public ModifiersImpact setOnlyInstant()
/*    */   {
/* 40 */     this.onlyInstant = true;
/* 41 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/ModifiersImpact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */