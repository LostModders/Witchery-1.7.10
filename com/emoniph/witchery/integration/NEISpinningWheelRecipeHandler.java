/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import codechicken.nei.PositionedStack;
/*     */ import com.emoniph.witchery.crafting.SpinningRecipes;
/*     */ import com.emoniph.witchery.crafting.SpinningRecipes.SpinningRecipe;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class NEISpinningWheelRecipeHandler extends codechicken.nei.recipe.TemplateRecipeHandler
/*     */ {
/*     */   public class CachedSpinningRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
/*     */   {
/*     */     PositionedStack fibre;
/*     */     PositionedStack output;
/*     */     PositionedStack add1;
/*     */     PositionedStack add2;
/*     */     PositionedStack add3;
/*     */     
/*     */     public CachedSpinningRecipe(net.minecraft.item.ItemStack result, SpinningRecipes.SpinningRecipe recipe)
/*     */     {
/*  20 */       super();
/*  21 */       this.fibre = new PositionedStack(recipe.fibre, 51, 9);
/*  22 */       this.output = new PositionedStack(recipe.getResult(), 113, 9);
/*     */       
/*  24 */       if ((recipe.modifiers.length > 0) && (recipe.modifiers[0] != null)) {
/*  25 */         this.add1 = new PositionedStack(recipe.modifiers[0], 51, 42);
/*     */       }
/*     */       
/*  28 */       if ((recipe.modifiers.length > 1) && (recipe.modifiers[1] != null)) {
/*  29 */         this.add2 = new PositionedStack(recipe.modifiers[1], 69, 42);
/*     */       }
/*     */       
/*  32 */       if ((recipe.modifiers.length > 2) && (recipe.modifiers[2] != null)) {
/*  33 */         this.add3 = new PositionedStack(recipe.modifiers[2], 87, 42);
/*     */       }
/*     */     }
/*     */     
/*     */     public PositionedStack getResult()
/*     */     {
/*  39 */       return this.output;
/*     */     }
/*     */     
/*     */     public ArrayList<PositionedStack> getIngredients()
/*     */     {
/*  44 */       ArrayList<PositionedStack> recipestacks = new ArrayList();
/*  45 */       recipestacks.add(this.fibre);
/*  46 */       recipestacks.add(this.output);
/*  47 */       if (this.add1 != null) {
/*  48 */         recipestacks.add(this.add1);
/*     */       }
/*  50 */       if (this.add2 != null) {
/*  51 */         recipestacks.add(this.add2);
/*     */       }
/*  53 */       if (this.add3 != null) {
/*  54 */         recipestacks.add(this.add3);
/*     */       }
/*     */       
/*  57 */       return recipestacks;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
/*     */   {
/*  69 */     return com.emoniph.witchery.blocks.BlockSpinningWheelGUI.class;
/*     */   }
/*     */   
/*     */   public String getRecipeName()
/*     */   {
/*  74 */     return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:spinningwheel.name");
/*     */   }
/*     */   
/*     */   public void loadTransferRects()
/*     */   {
/*  79 */     this.transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(74, 9, 24, 18), "witchery_spinning", new Object[0]));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void loadCraftingRecipes(String outputId, Object... results)
/*     */   {
/*  86 */     if ((outputId.equals("witchery_spinning")) && (getClass() == NEISpinningWheelRecipeHandler.class)) {
/*  87 */       for (SpinningRecipes.SpinningRecipe recipe : SpinningRecipes.instance().recipes) {
/*  88 */         this.arecipes.add(new CachedSpinningRecipe(recipe.getResult(), recipe));
/*     */       }
/*     */     } else {
/*  91 */       super.loadCraftingRecipes(outputId, results);
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadCraftingRecipes(net.minecraft.item.ItemStack result)
/*     */   {
/*  97 */     SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().findRecipeFor(result);
/*  98 */     if (recipe != null) {
/*  99 */       this.arecipes.add(new CachedSpinningRecipe(result, recipe));
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadUsageRecipes(net.minecraft.item.ItemStack ingredient)
/*     */   {
/* 105 */     SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().findRecipeUsing(ingredient);
/* 106 */     if (recipe != null) {
/* 107 */       this.arecipes.add(new CachedSpinningRecipe(ingredient, recipe));
/*     */     }
/*     */   }
/*     */   
/*     */   public String getGuiTexture()
/*     */   {
/* 113 */     return "witchery:textures/gui/spinningwheel.png";
/*     */   }
/*     */   
/*     */   public void drawExtras(int recipe)
/*     */   {
/* 118 */     drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
/*     */   }
/*     */   
/*     */   public String getOverlayIdentifier()
/*     */   {
/* 123 */     return "witchery_spinning";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/NEISpinningWheelRecipeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */