/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockWitchSapling;
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.world.World;
/*    */ import powercrystals.minefactoryreloaded.api.FertilizerType;
/*    */ import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
/*    */ 
/*    */ public class MFRFertilizable
/*    */   implements IFactoryFertilizable
/*    */ {
/*    */   private Block block;
/*    */   private int stages;
/*    */   
/*    */   public MFRFertilizable(Block block, int stages)
/*    */   {
/* 19 */     this.block = block;
/* 20 */     this.stages = stages;
/*    */   }
/*    */   
/*    */   public Block getPlant()
/*    */   {
/* 25 */     return this.block;
/*    */   }
/*    */   
/*    */   public boolean canFertilize(World world, int x, int y, int z, FertilizerType fertilizerType)
/*    */   {
/* 30 */     return (fertilizerType == FertilizerType.GrowPlant) && ((this.stages == 0) || (world.func_72805_g(x, y, z) < this.stages));
/*    */   }
/*    */   
/*    */   public boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType)
/*    */   {
/* 35 */     Block blockID = world.func_147439_a(x, y, z);
/* 36 */     Log.instance().debug(String.format("Fertilize %d, %d", new Object[] { blockID.func_149739_a(), Integer.valueOf(this.stages) }));
/* 37 */     if (this.stages > 0) {
/* 38 */       int meta = world.func_72805_g(x, y, z);
/* 39 */       if (meta < this.stages) {
/* 40 */         int output = meta + rand.nextInt(3) + 1;
/* 41 */         if (output > this.stages) {
/* 42 */           output = this.stages;
/*    */         }
/* 44 */         world.func_72921_c(x, y, z, output, 3);
/*    */         
/* 46 */         return true;
/*    */       }
/* 48 */     } else if ((this.block instanceof BlockWitchSapling)) {
/* 49 */       ((BlockWitchSapling)this.block);BlockWitchSapling.growTree(world, x, y, z, world.field_73012_v);
/* 50 */       return world.func_147439_a(x, y, z) != this.block;
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/MFRFertilizable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */