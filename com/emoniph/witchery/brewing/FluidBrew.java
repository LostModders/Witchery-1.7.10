/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ public class FluidBrew extends net.minecraftforge.fluids.Fluid
/*    */ {
/*    */   public FluidBrew(String fluidName) {
/*  8 */     super(fluidName);
/*    */   }
/*    */   
/*    */   public int getColor(FluidStack stack)
/*    */   {
/* 13 */     int color = WitcheryBrewRegistry.INSTANCE.getBrewColor(stack.tag);
/* 14 */     return color;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/FluidBrew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */