/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.util.BlockPosition;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModifiersRitual
/*    */ {
/*    */   public int permenance;
/* 13 */   public RitualStatus status = RitualStatus.COMPLETE;
/*    */   
/* 15 */   public final ArrayList<BlockPosition> targetLocations = new ArrayList();
/*    */   
/*    */   public final int covenSize;
/*    */   public final int pulses;
/*    */   public final BlockPosition cauldronLocation;
/*    */   public final boolean leonard;
/*    */   public boolean taglockUsed;
/*    */   
/*    */   public ModifiersRitual(BlockPosition cauldronLocation, int covenSize, int ritualPulses, boolean lennyPresent)
/*    */   {
/* 25 */     this.covenSize = covenSize;
/* 26 */     this.pulses = ritualPulses;
/* 27 */     this.cauldronLocation = cauldronLocation;
/* 28 */     this.leonard = lennyPresent;
/*    */   }
/*    */   
/*    */   public void setTarget(ItemStack stack) {
/* 32 */     setTarget(BlockPosition.from(stack));
/*    */   }
/*    */   
/*    */   public BlockPosition getTarget() {
/* 36 */     return getTarget(0);
/*    */   }
/*    */   
/*    */   public BlockPosition getTarget(int index) {
/* 40 */     if ((this.targetLocations.isEmpty()) || (index >= this.targetLocations.size())) {
/* 41 */       return this.cauldronLocation;
/*    */     }
/* 43 */     return (BlockPosition)this.targetLocations.get(index);
/*    */   }
/*    */   
/*    */   public void setTarget(BlockPosition target)
/*    */   {
/* 48 */     this.targetLocations.add(target);
/*    */   }
/*    */   
/*    */   public ModifiersRitual setRitualStatus(RitualStatus status) {
/* 52 */     this.status = status;
/* 53 */     return this;
/*    */   }
/*    */   
/*    */   public RitualStatus getRitualStatus() {
/* 57 */     return this.status;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/ModifiersRitual.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */