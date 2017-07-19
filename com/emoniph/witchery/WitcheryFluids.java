/*    */ package com.emoniph.witchery;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.FluidBrew;
/*    */ import com.emoniph.witchery.item.ItemBucketSpirit;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*    */ import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
/*    */ import net.minecraftforge.fluids.FluidRegistry;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ public final class WitcheryFluids
/*    */ {
/* 17 */   public final Fluid FLOWING_SPIRIT = register(new Fluid("witchery:fluidspirit")).setDensity(500).setViscosity(2000);
/* 18 */   public final Fluid HOLLOW_TEARS = register(new Fluid("witchery:hollowtears")).setDensity(100).setViscosity(1500);
/* 19 */   public final Fluid BREW = register(new FluidBrew("witchery:brew")).setDensity(100).setViscosity(1500);
/* 20 */   public final Fluid BREW_GAS = register(new Fluid("witchery:brewgas")).setGaseous(true);
/* 21 */   public final Fluid BREW_LIQUID = register(new Fluid("witchery:brewliquid"));
/* 22 */   public final Fluid DISEASE = register(new Fluid("witchery:fluiddisease")).setDensity(50).setViscosity(3000);
/*    */   
/*    */   private static Fluid register(Fluid fluid) {
/* 25 */     FluidRegistry.registerFluid(fluid);
/* 26 */     return fluid;
/*    */   }
/*    */   
/*    */   public static ItemBucketSpirit bind(ItemBucketSpirit bucket, Fluid fluid, Block block) {
/* 30 */     fluid.setBlock(block);
/* 31 */     bucket.setFluidBlock(block);
/* 32 */     bind(bucket, fluid, 1000);
/* 33 */     return bucket;
/*    */   }
/*    */   
/*    */   public static Item bind(Item bucket, Fluid fluid, int quantity) {
/* 37 */     FluidContainerRegistry.registerFluidContainer(new FluidContainerRegistry.FluidContainerData(new FluidStack(fluid, quantity), new ItemStack(bucket), new ItemStack(Items.field_151133_ar)));
/* 38 */     return bucket;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/WitcheryFluids.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */