/*    */ package com.emoniph.witchery.brewing.action;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.AltarPower;
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import com.emoniph.witchery.brewing.BrewNamePart;
/*    */ import com.emoniph.witchery.brewing.EffectLevelCounter;
/*    */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*    */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*    */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*    */ import com.emoniph.witchery.brewing.RitualStatus;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public abstract class BrewActionImpactModifier extends BrewAction
/*    */ {
/*    */   public BrewActionImpactModifier(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost)
/*    */   {
/* 21 */     super(itemKey, namePart, powerCost, com.emoniph.witchery.brewing.Probability.CERTAIN, false);
/*    */   }
/*    */   
/*    */   public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers)
/*    */   {
/* 26 */     onPrepareSplashPotion(world, modifiers);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected abstract void onPrepareSplashPotion(World paramWorld, ModifiersImpact paramModifiersImpact);
/*    */   
/*    */ 
/*    */ 
/*    */   public final void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {}
/*    */   
/*    */ 
/*    */   public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect effectModifiers, ItemStack stack) {}
/*    */   
/*    */ 
/*    */   public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*    */   {
/* 43 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
/*    */   
/*    */ 
/*    */   public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*    */   {
/* 57 */     return RitualStatus.COMPLETE;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewActionImpactModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */