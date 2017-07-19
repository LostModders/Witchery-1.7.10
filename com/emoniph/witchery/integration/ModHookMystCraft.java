/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryFluids;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ 
/*    */ public class ModHookMystCraft extends ModHook
/*    */ {
/*    */   public String getModID()
/*    */   {
/* 13 */     return "Mystcraft";
/*    */   }
/*    */   
/*    */   protected void doInit()
/*    */   {
/* 18 */     removeMystCraftFluid(Witchery.Fluids.FLOWING_SPIRIT.getName());
/* 19 */     removeMystCraftFluid(Witchery.Fluids.HOLLOW_TEARS.getName());
/* 20 */     removeMystCraftFluid(Witchery.Fluids.BREW.getName());
/* 21 */     removeMystCraftFluid(Witchery.Fluids.BREW_LIQUID.getName());
/* 22 */     removeMystCraftFluid(Witchery.Fluids.BREW_GAS.getName());
/*    */   }
/*    */   
/*    */   private void removeMystCraftFluid(String fluid) {
/* 26 */     NBTTagCompound nbtRoot = new NBTTagCompound();
/* 27 */     nbtRoot.func_74782_a("fluidsymbol", new NBTTagCompound());
/* 28 */     NBTTagCompound nbtSymbol = nbtRoot.func_74775_l("fluidsymbol");
/* 29 */     nbtSymbol.func_74778_a("fluidname", fluid);
/* 30 */     nbtSymbol.func_74776_a("rarity", 0.0F);
/* 31 */     nbtSymbol.func_74776_a("grammarweight", 0.0F);
/* 32 */     nbtSymbol.func_74776_a("instabilityPerBlock", 10000.0F);
/* 33 */     cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "fluidsymbol", nbtRoot);
/*    */   }
/*    */   
/*    */   protected void doPostInit() {}
/*    */   
/*    */   protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookMystCraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */