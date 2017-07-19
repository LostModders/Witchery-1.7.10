/*     */ package com.emoniph.witchery.brewing.action;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.BrewNamePart;
/*     */ import com.emoniph.witchery.brewing.EffectLevelCounter;
/*     */ import com.emoniph.witchery.brewing.ModifierYield;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*     */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.RitualStatus;
/*     */ import com.emoniph.witchery.util.BlockPosition;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ public abstract class BrewAction
/*     */ {
/*     */   public final BrewItemKey ITEM_KEY;
/*     */   protected final boolean createsSplash;
/*     */   protected final AltarPower powerCost;
/*     */   protected final int forcedColor;
/*     */   protected final Probability baseProbability;
/*     */   protected final BrewNamePart namePart;
/*  31 */   private final ArrayList<BrewItemKey> nullifiers = new ArrayList();
/*  32 */   private final ArrayList<BrewItemKey> priorNullifiers = new ArrayList();
/*     */   private ModifierYield yieldModifier;
/*     */   
/*     */   protected BrewAction(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, boolean createsSplash)
/*     */   {
/*  37 */     this(itemKey, namePart, powerCost, baseProbability, createsSplash, -1);
/*     */   }
/*     */   
/*     */   protected BrewAction(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, boolean createsSplash, int forcedColor)
/*     */   {
/*  42 */     this.ITEM_KEY = itemKey;
/*  43 */     this.powerCost = powerCost;
/*  44 */     this.createsSplash = createsSplash;
/*  45 */     this.forcedColor = forcedColor;
/*  46 */     this.baseProbability = baseProbability;
/*  47 */     this.namePart = namePart;
/*     */   }
/*     */   
/*     */   public void setYieldModifier(ModifierYield counter) {
/*  51 */     this.yieldModifier = counter;
/*     */   }
/*     */   
/*     */   public void prepareYield(ModifierYield counter) {
/*  55 */     if (this.yieldModifier != null) {
/*  56 */       this.yieldModifier.applyTo(counter);
/*     */     }
/*     */   }
/*     */   
/*     */   public final BrewNamePart getNamePart() {
/*  61 */     return this.namePart;
/*     */   }
/*     */   
/*     */   public final void accumulatePower(AltarPower totalPower) {
/*  65 */     totalPower.accumulate(this.powerCost);
/*     */   }
/*     */   
/*     */   public final BrewAction addNullifier(BrewItemKey itemKey) {
/*  69 */     return addNullifier(itemKey, true);
/*     */   }
/*     */   
/*     */   public final BrewAction addNullifier(BrewItemKey itemKey, boolean onlyPrior) {
/*  73 */     if (onlyPrior) {
/*  74 */       this.priorNullifiers.add(itemKey);
/*     */     } else {
/*  76 */       this.nullifiers.add(itemKey);
/*     */     }
/*  78 */     return this;
/*     */   }
/*     */   
/*     */   public final boolean createsSplash() {
/*  82 */     return this.createsSplash;
/*     */   }
/*     */   
/*     */   public final int augmentColor(int color) {
/*  86 */     if (this.forcedColor == -1) {
/*  87 */       if (color == 0) {
/*  88 */         color = 17;
/*     */       }
/*  90 */       color = 37 * color + this.ITEM_KEY.hashCode();
/*  91 */       return color;
/*     */     }
/*  93 */     return this.forcedColor;
/*     */   }
/*     */   
/*     */   public boolean removeWhenAddedToCauldron(World world)
/*     */   {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public final void processNullifaction(ArrayList<BrewAction> actionStack, NBTTagList nbtItems) {
/* 102 */     if ((this.priorNullifiers.size() > 0) && 
/* 103 */       (actionStack.size() > 0) && (this.priorNullifiers.contains(((BrewAction)actionStack.get(actionStack.size() - 1)).ITEM_KEY)))
/*     */     {
/* 105 */       actionStack.remove(actionStack.size() - 1);
/* 106 */       nbtItems.func_74744_a(actionStack.size() - 1);
/*     */     }
/*     */     
/*     */ 
/* 110 */     if (this.nullifiers.size() > 0) {
/* 111 */       for (int i = actionStack.size() - 1; i >= 0; i--) {
/* 112 */         if (this.nullifiers.contains(((BrewAction)actionStack.get(i)).ITEM_KEY)) {
/* 113 */           actionStack.remove(i);
/* 114 */           nbtItems.func_74744_a(i);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean triggersRitual() {
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects) {
/* 125 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isRitualTargetLocationValid(MinecraftServer server, World world, int x, int y, int z, BlockPosition target, ModifiersRitual modifiers)
/*     */   {
/* 130 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public abstract boolean augmentEffectLevels(EffectLevelCounter paramEffectLevelCounter);
/*     */   
/*     */   public abstract void augmentEffectModifiers(ModifiersEffect paramModifiersEffect);
/*     */   
/*     */   public abstract void prepareSplashPotion(World paramWorld, BrewActionList paramBrewActionList, ModifiersImpact paramModifiersImpact);
/*     */   
/*     */   public abstract void prepareRitual(World paramWorld, int paramInt1, int paramInt2, int paramInt3, ModifiersRitual paramModifiersRitual, ItemStack paramItemStack);
/*     */   
/*     */   public abstract RitualStatus updateRitual(MinecraftServer paramMinecraftServer, BrewActionList paramBrewActionList, World paramWorld, int paramInt1, int paramInt2, int paramInt3, ModifiersRitual paramModifiersRitual, ModifiersImpact paramModifiersImpact);
/*     */   
/*     */   public void applyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/* 146 */     applyToEntity(world, targetEntity, modifiers, stack);
/*     */   }
/*     */   
/*     */ 
/*     */   public abstract void applyToEntity(World paramWorld, EntityLivingBase paramEntityLivingBase, ModifiersEffect paramModifiersEffect, ItemStack paramItemStack);
/*     */   
/*     */ 
/*     */   public void applyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/* 155 */     applyToBlock(world, x, y, z, side, radius, modifiers, stack);
/*     */   }
/*     */   
/*     */   public abstract void applyToBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection, int paramInt4, ModifiersEffect paramModifiersEffect, ItemStack paramItemStack);
/*     */   
/*     */   public int getDrinkSpeedModifiers()
/*     */   {
/* 162 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */