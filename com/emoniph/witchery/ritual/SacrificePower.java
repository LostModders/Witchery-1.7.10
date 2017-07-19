/*    */ package com.emoniph.witchery.ritual;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.common.IPowerSource;
/*    */ import com.emoniph.witchery.common.PowerSources;
/*    */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SacrificePower
/*    */   extends Sacrifice
/*    */ {
/*    */   public final float powerRequired;
/*    */   public final int powerFrequencyInTicks;
/*    */   
/*    */   public SacrificePower(float powerRequired, int powerFrequencyInTicks)
/*    */   {
/* 29 */     this.powerRequired = powerRequired;
/* 30 */     this.powerFrequencyInTicks = powerFrequencyInTicks;
/*    */   }
/*    */   
/*    */   public void addDescription(StringBuffer sb)
/*    */   {
/* 35 */     sb.append(String.format("\n§8%s§0 %s\n", new Object[] { Witchery.resource("witchery.book.altarpower"), Integer.valueOf(MathHelper.func_76141_d(this.powerRequired)) }));
/*    */   }
/*    */   
/*    */   public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 45 */   public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance) { steps.add(new SacrificePowerStep(this)); }
/*    */   
/*    */   private static class SacrificePowerStep extends RitualStep {
/*    */     private final SacrificePower sacrifice;
/*    */     private static final int POWER_SOURCE_RADIUS = 16;
/*    */     
/*    */     public SacrificePowerStep(SacrificePower sacrifice) {
/* 52 */       super();
/* 53 */       this.sacrifice = sacrifice;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 58 */       if (ticks % this.sacrifice.powerFrequencyInTicks != 0L) {
/* 59 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 62 */       IPowerSource powerSource = findNewPowerSource(world, posX, posY, posZ);
/* 63 */       if (powerSource == null) {
/* 64 */         RiteRegistry.RiteError("witchery.rite.missingpowersource", ritual.getInitiatingPlayerName(), world);
/* 65 */         return RitualStep.Result.ABORTED_REFUND;
/*    */       }
/*    */       
/* 68 */       if (powerSource.consumePower(this.sacrifice.powerRequired)) {
/* 69 */         return RitualStep.Result.COMPLETED;
/*    */       }
/* 71 */       RiteRegistry.RiteError("witchery.rite.insufficientpower", ritual.getInitiatingPlayerName(), world);
/* 72 */       return RitualStep.Result.ABORTED_REFUND;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */     private IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
/*    */     {
/* 79 */       List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
/*    */       
/* 81 */       return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/SacrificePower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */