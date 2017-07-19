/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteTransposeOres extends com.emoniph.witchery.ritual.Rite
/*    */ {
/*    */   protected final int radius;
/*    */   protected final int pulses;
/*    */   protected final Block[] blocks;
/*    */   
/*    */   public RiteTransposeOres(int radius, int pulses, Block[] blocks)
/*    */   {
/* 21 */     this.radius = radius;
/* 22 */     this.pulses = pulses;
/* 23 */     this.blocks = blocks;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int initialStep)
/*    */   {
/* 28 */     steps.add(new StepTeleportation(this, initialStep));
/*    */   }
/*    */   
/*    */   private static class StepTeleportation extends RitualStep
/*    */   {
/*    */     private final RiteTransposeOres rite;
/*    */     private int step;
/*    */     
/*    */     public StepTeleportation(RiteTransposeOres rite, int initialStep) {
/* 37 */       super();
/* 38 */       this.rite = rite;
/* 39 */       this.step = initialStep;
/*    */     }
/*    */     
/*    */     public int getCurrentStage()
/*    */     {
/* 44 */       return this.step;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 49 */       if (ticks % 10L != 0L) {
/* 50 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 53 */       this.step += 1;
/* 54 */       int r = this.rite.radius;
/* 55 */       int y = posY - this.step;
/* 56 */       int blockTypes = ritual.covenSize == 6 ? 2 : 1;
/* 57 */       for (int x = posX - r; x <= posX + r; x++) {
/* 58 */         for (int z = posZ - r; z <= posZ + r; z++) {
/* 59 */           Block blockID = world.func_147439_a(x, y, z);
/* 60 */           for (int t = 0; t < blockTypes; t++) {
/* 61 */             if (blockID == this.rite.blocks[t]) {
/* 62 */               ItemStack stack = new ItemStack(this.rite.blocks[t]);
/* 63 */               EntityItem entity = new EntityItem(world, posX - r + world.field_73012_v.nextInt(2 * r + 1), posY + 2, posZ - r + world.field_73012_v.nextInt(2 * r + 1), stack);
/* 64 */               if (!world.field_72995_K) {
/* 65 */                 world.func_147468_f(x, y, z);
/* 66 */                 world.func_72838_d(entity);
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 73 */       return (this.step >= this.rite.pulses + 5 * ritual.covenSize) || (y <= 2) ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteTransposeOres.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */