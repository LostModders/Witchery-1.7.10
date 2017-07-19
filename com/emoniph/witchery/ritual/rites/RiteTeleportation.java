/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class RiteTeleportation
/*    */   extends Rite
/*    */ {
/*    */   protected final int radius;
/*    */   
/*    */   public RiteTeleportation(int radius)
/*    */   {
/* 17 */     this.radius = radius;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 22 */     steps.add(new StepTeleportation(this));
/*    */   }
/*    */   
/*    */   protected abstract boolean teleport(World paramWorld, int paramInt1, int paramInt2, int paramInt3, BlockCircle.TileEntityCircle.ActivatedRitual paramActivatedRitual);
/*    */   
/*    */   private static class StepTeleportation extends RitualStep
/*    */   {
/*    */     private final RiteTeleportation rite;
/*    */     
/*    */     public StepTeleportation(RiteTeleportation rite) {
/* 32 */       super();
/* 33 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 38 */       if (ticks % 20L != 0L) {
/* 39 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 42 */       if (this.rite.teleport(world, posX, posY, posZ, ritual)) {
/* 43 */         return RitualStep.Result.COMPLETED;
/*    */       }
/* 45 */       return RitualStep.Result.ABORTED_REFUND;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteTeleportation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */