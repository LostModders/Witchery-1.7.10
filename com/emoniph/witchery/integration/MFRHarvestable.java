/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import powercrystals.minefactoryreloaded.api.HarvestType;
/*    */ import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
/*    */ 
/*    */ public class MFRHarvestable implements IFactoryHarvestable
/*    */ {
/*    */   private Block source;
/*    */   private HarvestType harvestType;
/*    */   private int stages;
/*    */   
/*    */   public MFRHarvestable(Block source, HarvestType harvestType, int stages)
/*    */   {
/* 20 */     this.source = source;
/* 21 */     this.harvestType = harvestType;
/* 22 */     this.stages = stages;
/*    */   }
/*    */   
/*    */   public Block getPlant()
/*    */   {
/* 27 */     return this.source;
/*    */   }
/*    */   
/*    */   public HarvestType getHarvestType()
/*    */   {
/* 32 */     return this.harvestType;
/*    */   }
/*    */   
/*    */   public boolean breakBlock()
/*    */   {
/* 37 */     return this.stages == 0;
/*    */   }
/*    */   
/*    */   public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
/*    */   {
/* 42 */     return (this.stages == 0) || ((this.stages > 0) && (world.func_72805_g(x, y, z) == this.stages));
/*    */   }
/*    */   
/*    */   public java.util.List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z)
/*    */   {
/* 47 */     if ((harvesterSettings.get("silkTouch") != null) && (((Boolean)harvesterSettings.get("silkTouch")).booleanValue()) && (this.harvestType == HarvestType.TreeLeaf)) {
/* 48 */       ArrayList<ItemStack> drops = new ArrayList();
/* 49 */       drops.add(new ItemStack(this.source, 1, world.func_72805_g(x, y, z) & 0x3));
/* 50 */       return drops;
/*    */     }
/* 52 */     return this.source.getDrops(world, x, y, z, world.func_72805_g(x, y, z), 0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void preHarvest(World world, int x, int y, int z) {}
/*    */   
/*    */ 
/*    */   public void postHarvest(World world, int x, int y, int z)
/*    */   {
/* 62 */     if ((this.stages > 0) && (world.func_72805_g(x, y, z) == this.stages)) {
/* 63 */       world.func_147468_f(x, y, z);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/MFRHarvestable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */