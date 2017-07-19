/*     */ package com.emoniph.witchery.brewing.action;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.EffectLevelCounter;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*     */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.RitualStatus;
/*     */ import com.emoniph.witchery.entity.EntityLeonard;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BrewActionRitualSummonMob extends BrewAction
/*     */ {
/*     */   private final Recipe[] recipes;
/*     */   
/*     */   public static class Recipe
/*     */   {
/*     */     public final Class<? extends EntityCreature> result;
/*     */     public final ItemStack[] ingredients;
/*     */     
/*     */     public Recipe(Class<? extends EntityCreature> result, ItemStack... ingredients)
/*     */     {
/*  37 */       this.result = result;
/*  38 */       this.ingredients = ingredients;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  43 */   private final List<Recipe> expandedRecipes = new ArrayList();
/*     */   
/*     */   public BrewActionRitualSummonMob(BrewItemKey itemKey, AltarPower powerCost, Recipe... recipes) {
/*  46 */     super(itemKey, null, powerCost, new Probability(1.0D), false);
/*  47 */     this.recipes = recipes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect effectModifiers, ItemStack stack) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public final boolean triggersRitual()
/*     */   {
/*  66 */     return true;
/*     */   }
/*     */   
/*     */   public final boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects)
/*     */   {
/*  71 */     return (isCauldronFull) && (getRecipeResult(actionList) != null);
/*     */   }
/*     */   
/*     */ 
/*     */   public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*     */   {
/*  77 */     Class<? extends EntityCreature> result = getRecipeResult(actionList);
/*  78 */     if (result != null)
/*     */     {
/*  80 */       EntityCreature creature = Infusion.spawnCreature(world, result, x, y + 1, z, null, 0, 0, ParticleEffect.EXPLODE, SoundEffect.MOB_WITHER_SPAWN);
/*  81 */       if (creature != null) {
/*  82 */         creature.func_110163_bv();
/*  83 */         if ((creature instanceof EntityLeonard)) {
/*  84 */           EntityLeonard leonard = (EntityLeonard)creature;
/*  85 */           leonard.setInvulnerableStart();
/*     */         }
/*     */       }
/*     */       
/*  89 */       return RitualStatus.COMPLETE;
/*     */     }
/*  91 */     return RitualStatus.FAILED;
/*     */   }
/*     */   
/*     */   private Class<? extends EntityCreature> getRecipeResult(BrewActionList actionList)
/*     */   {
/*  96 */     for (Recipe recipe : this.recipes) {
/*  97 */       if (recipe.ingredients.length > 0) {
/*  98 */         ArrayList<ItemStack> neededItems = new ArrayList();
/*  99 */         neededItems.addAll(Arrays.asList(recipe.ingredients));
/* 100 */         for (BrewAction action : actionList.actions) {
/* 101 */           removeFromNeededItems(neededItems, action.ITEM_KEY);
/*     */         }
/* 103 */         if (neededItems.size() == 0) {
/* 104 */           return recipe.result;
/*     */         }
/*     */       } else {
/* 107 */         return recipe.result;
/*     */       }
/*     */     }
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   private void removeFromNeededItems(ArrayList<ItemStack> neededItems, BrewItemKey item) {
/* 114 */     Iterator<ItemStack> iterator = neededItems.iterator();
/* 115 */     while (iterator.hasNext()) {
/* 116 */       ItemStack stack = (ItemStack)iterator.next();
/* 117 */       if ((stack.func_77973_b() == item.ITEM) && (stack.func_77960_j() == item.DAMAGE)) {
/* 118 */         iterator.remove();
/* 119 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*     */   {
/* 126 */     return true;
/*     */   }
/*     */   
/*     */   public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
/*     */   
/*     */   public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewActionRitualSummonMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */