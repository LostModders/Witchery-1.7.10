/*     */ package com.emoniph.witchery.brewing.action;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.BrewNamePart;
/*     */ import com.emoniph.witchery.brewing.EffectLevel;
/*     */ import com.emoniph.witchery.brewing.EffectLevelCounter;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*     */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.RitualStatus;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public abstract class BrewActionEffect extends BrewAction
/*     */ {
/*     */   protected final EffectLevel effectLevel;
/*     */   
/*     */   public BrewActionEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel)
/*     */   {
/*  25 */     super(itemKey, namePart, powerCost, baseProbability, false);
/*  26 */     this.effectLevel = effectLevel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
/*     */   
/*     */ 
/*     */   public final void applyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  40 */     if (!modifiers.disableEntityTarget) {
/*  41 */       doApplyRitualToEntity(world, targetEntity, ritualModifiers, modifiers, stack);
/*  42 */       modifiers.reset();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public final void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  49 */     if (!modifiers.disableEntityTarget) {
/*  50 */       doApplyToEntity(world, targetEntity, modifiers, stack);
/*  51 */       modifiers.reset();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doApplyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  57 */     doApplyToEntity(world, targetEntity, modifiers, stack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {}
/*     */   
/*     */ 
/*     */   public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  67 */     if (!modifiers.disableBlockTarget) {
/*  68 */       doApplyToBlock(world, x, y, z, side, radius, modifiers, stack);
/*  69 */       modifiers.reset();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public final void applyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  76 */     if (!modifiers.disableBlockTarget) {
/*  77 */       doApplyRitualToBlock(world, x, y, z, side, radius, ritualModifiers, modifiers, stack);
/*  78 */       modifiers.reset();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  84 */     doApplyToBlock(world, x, y, z, side, radius, modifiers, stack);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack) {}
/*     */   
/*     */ 
/*     */   public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*     */   {
/*  93 */     return totalEffects.tryConsumeLevel(this.effectLevel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
/*     */   
/*     */ 
/*     */   public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*     */   {
/* 103 */     return RitualStatus.COMPLETE;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewActionEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */