/*    */ package com.emoniph.witchery.brewing.action;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.AltarPower;
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import com.emoniph.witchery.brewing.BrewNamePart;
/*    */ import com.emoniph.witchery.brewing.EffectLevel;
/*    */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*    */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*    */ import com.emoniph.witchery.brewing.Probability;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class BrewCurseEffect
/*    */   extends BrewPotionEffect
/*    */ {
/*    */   private boolean onlyCurseInverted;
/*    */   
/*    */   public BrewCurseEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, EffectLevel effectLevel, boolean onlyCurseInverted)
/*    */   {
/* 23 */     super(itemKey, namePart, powerCost, baseProbability, effect, baseDuration, effect, baseDuration, effectLevel);
/*    */     
/* 25 */     this.onlyCurseInverted = onlyCurseInverted;
/*    */   }
/*    */   
/*    */ 
/*    */   public BrewCurseEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, Potion invertedEffect, long invertedDuration, EffectLevel effectLevel, boolean onlyCurseInverted)
/*    */   {
/* 31 */     super(itemKey, namePart, powerCost, baseProbability, effect, baseDuration, invertedEffect, invertedDuration, effectLevel);
/*    */     
/* 33 */     this.onlyCurseInverted = onlyCurseInverted;
/*    */   }
/*    */   
/*    */ 
/*    */   public void applyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*    */   {
/* 39 */     int oldDuration = modifiers.duration;
/* 40 */     boolean oldNoParticles = modifiers.noParticles;
/* 41 */     if ((ritualModifiers.taglockUsed) && ((!this.onlyCurseInverted) || (modifiers.inverted))) {
/* 42 */       modifiers.duration = 10000;
/* 43 */       modifiers.noParticles = true;
/*    */     }
/* 45 */     applyToEntity(world, targetEntity, modifiers, stack);
/* 46 */     modifiers.duration = oldDuration;
/* 47 */     modifiers.noParticles = oldNoParticles;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewCurseEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */