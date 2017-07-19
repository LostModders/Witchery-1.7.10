/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.IPlantable;
/*    */ import powercrystals.minefactoryreloaded.api.ReplacementBlock;
/*    */ 
/*    */ public class MFRPlantable implements powercrystals.minefactoryreloaded.api.IFactoryPlantable
/*    */ {
/*    */   protected Item seedItemID;
/*    */   protected Block cropBlockID;
/*    */   
/*    */   public MFRPlantable(Item seeds, Block plantedBlock)
/*    */   {
/* 17 */     this.seedItemID = seeds;
/* 18 */     this.cropBlockID = plantedBlock;
/*    */   }
/*    */   
/*    */   public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack)
/*    */   {
/* 23 */     Block blockID = world.func_147439_a(x, y - 1, z);
/* 24 */     if (world.func_147437_c(x, y, z)) {
/* 25 */       return ((this.cropBlockID.func_149742_c(world, x, y, z)) && (this.cropBlockID.func_149718_j(world, x, y, z))) || (((this.cropBlockID instanceof IPlantable)) && (this.cropBlockID.canSustainPlant(world, x, y, z, net.minecraftforge.common.util.ForgeDirection.UP, (IPlantable)this.cropBlockID)));
/*    */     }
/*    */     
/*    */ 
/* 29 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void prePlant(World world, int x, int y, int z, ItemStack stack) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void postPlant(World world, int x, int y, int z, ItemStack stack) {}
/*    */   
/*    */ 
/*    */   public Item getSeed()
/*    */   {
/* 43 */     return this.seedItemID;
/*    */   }
/*    */   
/*    */   public boolean canBePlanted(ItemStack stack, boolean forFermenting)
/*    */   {
/* 48 */     return stack.func_77973_b() == this.seedItemID;
/*    */   }
/*    */   
/*    */   public ReplacementBlock getPlantedBlock(World world, int x, int y, int z, ItemStack stack)
/*    */   {
/* 53 */     return new ReplacementBlock(this.cropBlockID);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/MFRPlantable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */