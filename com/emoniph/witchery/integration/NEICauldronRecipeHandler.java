/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import codechicken.nei.PositionedStack;
/*     */ import codechicken.nei.recipe.TemplateRecipeHandler;
/*     */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe.Recipe;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class NEICauldronRecipeHandler extends TemplateRecipeHandler
/*     */ {
/*     */   public class CachedKettleRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
/*     */   {
/*     */     PositionedStack result;
/*     */     
/*     */     public CachedKettleRecipe(ItemStack result, ItemStack[] recipe)
/*     */     {
/*  19 */       super();
/*  20 */       this.result = new PositionedStack(result, 119, 31);
/*  21 */       for (int i = 0; i < recipe.length; i++) {
/*  22 */         if (recipe[i] != null) {
/*  23 */           this.inputs[i] = new PositionedStack(recipe[i], i * 18 + 10, 6);
/*     */         } else {
/*  25 */           this.inputs[i] = null;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public PositionedStack getResult()
/*     */     {
/*  32 */       return this.result;
/*     */     }
/*     */     
/*     */     public ArrayList<PositionedStack> getIngredients()
/*     */     {
/*  37 */       ArrayList<PositionedStack> recipestacks = new ArrayList();
/*  38 */       recipestacks.add(this.result);
/*  39 */       for (PositionedStack posStack : this.inputs) {
/*  40 */         if (posStack != null)
/*  41 */           recipestacks.add(posStack);
/*     */       }
/*  43 */       return recipestacks;
/*     */     }
/*     */     
/*     */ 
/*  47 */     PositionedStack[] inputs = new PositionedStack[6];
/*     */   }
/*     */   
/*     */   public void loadTransferRects()
/*     */   {
/*  52 */     this.transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(92, 31, 24, 18), "witchery_brewing_plus", new Object[0]));
/*     */   }
/*     */   
/*     */   public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
/*     */   {
/*  57 */     return net.minecraft.client.gui.inventory.GuiCrafting.class;
/*     */   }
/*     */   
/*     */   public String getRecipeName()
/*     */   {
/*  62 */     return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:cauldron.name");
/*     */   }
/*     */   
/*     */   public void loadCraftingRecipes(String outputId, Object... results)
/*     */   {
/*  67 */     if ((outputId.equals("witchery_brewing_plus")) && (getClass() == NEICauldronRecipeHandler.class))
/*     */     {
/*  69 */       for (BrewActionRitualRecipe ritual : WitcheryBrewRegistry.INSTANCE.getRecipes()) {
/*  70 */         for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
/*  71 */           this.arecipes.add(new CachedKettleRecipe(recipe.result, recipe.ingredients));
/*     */         }
/*     */         
/*     */       }
/*     */     } else {
/*  76 */       super.loadCraftingRecipes(outputId, results);
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadCraftingRecipes(ItemStack result)
/*     */   {
/*  82 */     for (BrewActionRitualRecipe ritual : WitcheryBrewRegistry.INSTANCE.getRecipes()) {
/*  83 */       for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
/*  84 */         if (result.func_77969_a(recipe.result)) {
/*  85 */           this.arecipes.add(new CachedKettleRecipe(recipe.result, recipe.ingredients));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadUsageRecipes(ItemStack ingredient)
/*     */   {
/*  93 */     for (BrewActionRitualRecipe ritual : WitcheryBrewRegistry.INSTANCE.getRecipes()) {
/*  94 */       for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
/*  95 */         for (ItemStack stack : recipe.ingredients) {
/*  96 */           if (stack.func_77969_a(ingredient)) {
/*  97 */             this.arecipes.add(new CachedKettleRecipe(recipe.result, recipe.ingredients));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String getGuiTexture()
/*     */   {
/* 107 */     return "witchery:textures/gui/witchesCauldron.png";
/*     */   }
/*     */   
/*     */ 
/*     */   public void drawExtras(int recipe) {}
/*     */   
/*     */ 
/*     */   public String getOverlayIdentifier()
/*     */   {
/* 116 */     return "witchery_brewing_plus";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/NEICauldronRecipeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */