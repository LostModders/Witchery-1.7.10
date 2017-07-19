/*     */ package com.emoniph.witchery.brewing.action;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
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
/*     */ import com.emoniph.witchery.brewing.potions.PotionBase;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BrewPotionEffect extends BrewAction
/*     */ {
/*     */   private static final int DEFAULT_STRENGTH_CEILING = 10;
/*     */   public final Potion potion;
/*     */   public final Potion invertedPotion;
/*     */   public final int baseDuration;
/*     */   public final int invertedDuration;
/*     */   public final EffectLevel effectLevel;
/*  34 */   protected int strengthCeiling = 10;
/*     */   
/*     */   public BrewPotionEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, EffectLevel effectLevel)
/*     */   {
/*  38 */     this(itemKey, namePart, powerCost, baseProbability, effect, baseDuration, effect, baseDuration, effectLevel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BrewPotionEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, Potion invertedEffect, long invertedDuration, EffectLevel effectLevel)
/*     */   {
/*  45 */     super(itemKey, namePart.setBaseDuration(baseDuration, invertedDuration), powerCost, baseProbability, false);
/*     */     
/*  47 */     this.potion = effect;
/*  48 */     this.invertedPotion = invertedEffect;
/*  49 */     this.baseDuration = ((int)baseDuration);
/*  50 */     this.invertedDuration = ((int)invertedDuration);
/*  51 */     this.effectLevel = effectLevel;
/*     */   }
/*     */   
/*     */   public BrewPotionEffect setStrengthCeiling(int ceiling) {
/*  55 */     this.strengthCeiling = ceiling;
/*  56 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  62 */     if (!modifiers.disableEntityTarget) {
/*  63 */       if (modifiers.inverted) {
/*  64 */         if ((!PotionBase.isDebuff(this.invertedPotion)) || (!modifiers.protectedFromNegativePotions)) {
/*  65 */           applyPotionEffect(targetEntity, modifiers, this.invertedPotion, this.invertedDuration, modifiers.noParticles, modifiers.caster, this.strengthCeiling);
/*     */         }
/*     */         
/*     */       }
/*  69 */       else if ((!PotionBase.isDebuff(this.potion)) || (!modifiers.protectedFromNegativePotions)) {
/*  70 */         if (this.potion == Witchery.Potions.DOUBLE_JUMP) {
/*  71 */           if ((modifiers.caster == null) || (!com.emoniph.witchery.familiar.Familiar.hasActiveBrewMasteryFamiliar(modifiers.caster))) {
/*  72 */             modifiers.reset();
/*     */           }
/*     */         }
/*  75 */         else if ((this.potion == Witchery.Potions.DISEASED) && 
/*  76 */           (CreatureUtil.isImmuneToDisease(targetEntity))) {
/*  77 */           modifiers.reset();
/*  78 */           return;
/*     */         }
/*     */         
/*  81 */         applyPotionEffect(targetEntity, modifiers, this.potion, this.baseDuration, modifiers.noParticles, modifiers.caster, this.strengthCeiling);
/*     */       }
/*     */       
/*  84 */       modifiers.reset();
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*     */   {
/*  90 */     return totalEffects.tryConsumeLevel(this.effectLevel);
/*     */   }
/*     */   
/*     */   public static void applyPotionEffect(EntityLivingBase entity, ModifiersEffect modifiers, Potion potion, int duration, boolean noParticles, EntityPlayer thrower)
/*     */   {
/*  95 */     applyPotionEffect(entity, modifiers, potion, duration, noParticles, thrower, 10);
/*     */   }
/*     */   
/*     */   public static void applyPotionEffect(EntityLivingBase entity, ModifiersEffect modifiers, Potion potion, int duration, boolean noParticles, EntityPlayer thrower, int strengthCeiling)
/*     */   {
/* 100 */     int strength = Math.min(modifiers.getStrength(), modifiers.strengthCeilingDisabled ? 10 : strengthCeiling);
/*     */     
/* 102 */     if (potion.func_76403_b()) {
/* 103 */       potion.func_76402_a(thrower, entity, strength, modifiers.powerScalingFactor);
/*     */     } else {
/* 105 */       entity.func_70690_d(new PotionEffect(potion.field_76415_H, modifiers.getModifiedDuration(duration), strength, noParticles));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*     */   {
/* 129 */     return RitualStatus.COMPLETE;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewPotionEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */