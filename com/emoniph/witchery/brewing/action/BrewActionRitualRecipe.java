/*     */ package com.emoniph.witchery.brewing.action;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.EffectLevelCounter;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.ModifiersImpact;
/*     */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*     */ import com.emoniph.witchery.brewing.RitualStatus;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BrewActionRitualRecipe extends BrewAction
/*     */ {
/*     */   private final Recipe[] recipes;
/*     */   
/*     */   public static class Recipe
/*     */   {
/*     */     public final ItemStack result;
/*     */     public final ItemStack[] ingredients;
/*     */     
/*     */     public Recipe(ItemStack result, ItemStack... ingredients)
/*     */     {
/*  32 */       this.result = result;
/*  33 */       this.ingredients = ingredients;
/*     */     }
/*     */     
/*     */     public Recipe(ItemStack result, ItemStack[] ingredients, ItemStack finalIngredient) {
/*  37 */       this.result = result;
/*  38 */       this.ingredients = ((ItemStack[])Arrays.copyOf(ingredients, ingredients.length + 1));
/*  39 */       this.ingredients[(this.ingredients.length - 1)] = finalIngredient;
/*     */     }
/*     */     
/*     */     private Recipe getExpandedRecipe(BrewItemKey itemKey) {
/*  43 */       return new Recipe(this.result, this.ingredients, itemKey.toStack());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  48 */   private final List<Recipe> expandedRecipes = new ArrayList();
/*     */   
/*     */   public BrewActionRitualRecipe(BrewItemKey itemKey, AltarPower powerCost, Recipe... recipes) {
/*  51 */     super(itemKey, null, powerCost, new com.emoniph.witchery.brewing.Probability(1.0D), false);
/*  52 */     this.recipes = recipes;
/*  53 */     for (Recipe recipe : recipes) {
/*  54 */       this.expandedRecipes.add(recipe.getExpandedRecipe(itemKey));
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Recipe> getExpandedRecipes() {
/*  59 */     return this.expandedRecipes;
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
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   public final boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects)
/*     */   {
/*  83 */     return (isCauldronFull) && (getRecipeResult(actionList) != null);
/*     */   }
/*     */   
/*     */ 
/*     */   public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*     */   {
/*  89 */     ItemStack result = getRecipeResult(actionList);
/*  90 */     if (result != null) {
/*  91 */       EntityItem item = new EntityItem(world, x + 0.5D, y + 1.5D, z + 0.5D, result.func_77946_l());
/*  92 */       item.field_70159_w = 0.0D;
/*  93 */       item.field_70181_x = 0.2D;
/*  94 */       item.field_70179_y = 0.0D;
/*  95 */       com.emoniph.witchery.util.EntityUtil.spawnEntityInWorld(world, item);
/*     */       
/*  97 */       return RitualStatus.COMPLETE;
/*     */     }
/*  99 */     return RitualStatus.FAILED;
/*     */   }
/*     */   
/*     */   private ItemStack getRecipeResult(BrewActionList actionList)
/*     */   {
/* 104 */     for (Recipe recipe : this.recipes) {
/* 105 */       if (recipe.ingredients.length > 0) {
/* 106 */         ArrayList<ItemStack> neededItems = new ArrayList();
/* 107 */         neededItems.addAll(Arrays.asList(recipe.ingredients));
/* 108 */         for (BrewAction action : actionList.actions) {
/* 109 */           removeFromNeededItems(neededItems, action.ITEM_KEY);
/*     */         }
/* 111 */         if (neededItems.size() == 0) {
/* 112 */           return recipe.result;
/*     */         }
/*     */       } else {
/* 115 */         return recipe.result;
/*     */       }
/*     */     }
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   private void removeFromNeededItems(ArrayList<ItemStack> neededItems, BrewItemKey item) {
/* 122 */     Iterator<ItemStack> iterator = neededItems.iterator();
/* 123 */     while (iterator.hasNext()) {
/* 124 */       ItemStack stack = (ItemStack)iterator.next();
/* 125 */       if ((stack.func_77973_b() == item.ITEM) && (stack.func_77960_j() == item.DAMAGE)) {
/* 126 */         iterator.remove();
/* 127 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*     */   {
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
/*     */   
/*     */   public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/BrewActionRitualRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */