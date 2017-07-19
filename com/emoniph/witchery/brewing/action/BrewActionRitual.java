/*    */ package com.emoniph.witchery.brewing.action;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.AltarPower;
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import com.emoniph.witchery.brewing.Dispersal;
/*    */ import com.emoniph.witchery.brewing.EffectLevelCounter;
/*    */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*    */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*    */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*    */ import com.emoniph.witchery.brewing.Probability;
/*    */ import com.emoniph.witchery.brewing.RitualStatus;
/*    */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*    */ import com.emoniph.witchery.util.BlockPosition;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class BrewActionRitual
/*    */   extends BrewAction
/*    */ {
/*    */   private boolean aoeOnly;
/*    */   
/*    */   public BrewActionRitual(BrewItemKey itemKey, AltarPower powerCost, boolean aoeOnly)
/*    */   {
/* 29 */     super(itemKey, null, powerCost, new Probability(1.0D), false);
/* 30 */     this.aoeOnly = aoeOnly;
/*    */   }
/*    */   
/*    */   public final boolean triggersRitual()
/*    */   {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public final boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects)
/*    */   {
/* 40 */     return (isCauldronFull) && (hasEffects) && ((!this.aoeOnly) || (WitcheryBrewRegistry.INSTANCE.isSplash(actionList.getTagCompound())));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*    */   {
/* 48 */     BlockPosition target = modifiers.getTarget();
/*    */     
/* 50 */     if (!isDistanceAllowed(world, x, y, z, target.x, target.y, target.z, target.dimension, modifiers.covenSize, modifiers.leonard)) {
/* 51 */       return RitualStatus.FAILED_DISTANCE;
/*    */     }
/*    */     
/* 54 */     if (!actionList.isTargetLocationValid(server, world, x, y, z, target, modifiers)) {
/* 55 */       return RitualStatus.FAILED_INVALID_CIRCLES;
/*    */     }
/*    */     
/* 58 */     return impactModifiers.getDispersal().onUpdateRitual(world, x, y, z, actionList.getTagCompound(), modifiers, impactModifiers);
/*    */   }
/*    */   
/*    */   public static boolean isDistanceAllowed(World world, int x, int y, int z, BlockPosition target, int covenSize, boolean leonard)
/*    */   {
/* 63 */     return isDistanceAllowed(world, x, y, z, target.x, target.y, target.z, target.dimension, covenSize, leonard);
/*    */   }
/*    */   
/*    */   public static boolean isDistanceAllowed(World world, int x, int y, int z, double newX, double newY, double newZ, int newD, int covenSize, boolean leonard)
/*    */   {
/* 68 */     if (world.field_73011_w.field_76574_g != newD) {
/* 69 */       return (covenSize >= 6) && (leonard);
/*    */     }
/*    */     
/* 72 */     if (covenSize >= 6) {
/* 73 */       return true;
/*    */     }
/*    */     
/* 76 */     double rangeSq = Coord.distanceSq(x, y, z, newX, newY, newZ);
/*    */     
/* 78 */     int rangeScale = (1 + covenSize) * 4;
/* 79 */     int allowedRange = 2 * rangeScale * rangeScale;
/* 80 */     if (rangeSq <= allowedRange * allowedRange) {
/* 81 */       return true;
/*    */     }
/* 83 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*    */   {
/* 89 */     return true;
/*    */   }
/*    */   
/*    */   public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
/*    */   
/*    */   public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
/*    */   
/*    */   public final void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {}
/*    */   
/*    */   public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect effectModifiers, ItemStack stack) {}
/*    */   
/*    */   public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewActionRitual.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */