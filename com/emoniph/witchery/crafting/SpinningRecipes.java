/*     */ package com.emoniph.witchery.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class SpinningRecipes
/*     */ {
/*   8 */   private static final SpinningRecipes INSTANCE = new SpinningRecipes();
/*     */   
/*     */   public static SpinningRecipes instance() {
/*  11 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public static class SpinningRecipe {
/*     */     public final ItemStack fibre;
/*     */     public final ItemStack[] modifiers;
/*     */     public final ItemStack result;
/*     */     
/*     */     private SpinningRecipe(ItemStack result, ItemStack fibre, ItemStack[] modifiers) {
/*  20 */       this.fibre = fibre;
/*  21 */       this.result = result;
/*  22 */       this.modifiers = modifiers;
/*     */     }
/*     */     
/*     */     public ItemStack getResult() {
/*  26 */       return this.result;
/*     */     }
/*     */     
/*     */     public ArrayList<ItemStack> getMutableModifiersList() {
/*  30 */       ArrayList<ItemStack> available = new ArrayList();
/*  31 */       for (ItemStack item : this.modifiers) {
/*  32 */         if (item != null) {
/*  33 */           available.add(item);
/*     */         }
/*     */       }
/*  36 */       return available;
/*     */     }
/*     */     
/*     */     private boolean isMatch(ItemStack fibre, ItemStack[] modifiers) {
/*  40 */       if ((fibre == null) || (!fibre.func_77969_a(this.fibre)) || (fibre.field_77994_a < this.fibre.field_77994_a)) {
/*  41 */         return false;
/*     */       }
/*     */       
/*  44 */       ArrayList<ItemStack> available = new ArrayList();
/*  45 */       for (ItemStack item : modifiers) {
/*  46 */         if (item != null) {
/*  47 */           available.add(item);
/*     */         }
/*     */       }
/*     */       
/*  51 */       for (ItemStack modifier : this.modifiers) {
/*  52 */         int index = indexOf(available, modifier);
/*  53 */         if (index == -1) {
/*  54 */           return false;
/*     */         }
/*  56 */         available.remove(index);
/*     */       }
/*  58 */       return true;
/*     */     }
/*     */     
/*     */     private int indexOf(ArrayList<ItemStack> list, ItemStack item) {
/*  62 */       for (int i = 0; i < list.size(); i++) {
/*  63 */         if (((ItemStack)list.get(i)).func_77969_a(item)) {
/*  64 */           return i;
/*     */         }
/*     */       }
/*  67 */       return -1;
/*     */     }
/*     */     
/*     */     public boolean uses(ItemStack ingredient)
/*     */     {
/*  72 */       if (ingredient == null) {
/*  73 */         return false;
/*     */       }
/*     */       
/*  76 */       if (this.fibre.func_77969_a(ingredient)) {
/*  77 */         return true;
/*     */       }
/*     */       
/*  80 */       for (ItemStack item : this.modifiers) {
/*  81 */         if ((item != null) && (item.func_77969_a(ingredient))) {
/*  82 */           return true;
/*     */         }
/*     */       }
/*     */       
/*  86 */       return false;
/*     */     }
/*     */   }
/*     */   
/*  90 */   public final ArrayList<SpinningRecipe> recipes = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SpinningRecipe addRecipe(ItemStack result, ItemStack fibre, ItemStack... modifiers)
/*     */   {
/*  97 */     SpinningRecipe recipe = new SpinningRecipe(result, fibre, modifiers, null);
/*  98 */     this.recipes.add(recipe);
/*  99 */     return recipe;
/*     */   }
/*     */   
/*     */   public SpinningRecipe getRecipe(ItemStack fibre, ItemStack[] modifiers) {
/* 103 */     for (SpinningRecipe recipe : this.recipes) {
/* 104 */       if (recipe.isMatch(fibre, modifiers)) {
/* 105 */         return recipe;
/*     */       }
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public SpinningRecipe findRecipeFor(ItemStack result) {
/* 112 */     for (SpinningRecipe recipe : this.recipes) {
/* 113 */       if (recipe.getResult().func_77969_a(result)) {
/* 114 */         return recipe;
/*     */       }
/*     */     }
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public SpinningRecipe findRecipeUsing(ItemStack ingredient) {
/* 121 */     for (SpinningRecipe recipe : this.recipes) {
/* 122 */       if (recipe.uses(ingredient)) {
/* 123 */         return recipe;
/*     */       }
/*     */     }
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   public SpinningRecipe findRecipeUsingFibre(ItemStack ingredient) {
/* 130 */     for (SpinningRecipe recipe : this.recipes) {
/* 131 */       if (recipe.fibre.func_77969_a(ingredient)) {
/* 132 */         return recipe;
/*     */       }
/*     */     }
/* 135 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/SpinningRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */