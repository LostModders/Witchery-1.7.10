/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import codechicken.nei.PositionedStack;
/*     */ import codechicken.nei.recipe.TemplateRecipeHandler;
/*     */ import com.emoniph.witchery.crafting.DistilleryRecipes;
/*     */ import com.emoniph.witchery.crafting.DistilleryRecipes.DistilleryRecipe;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class NEIDistilleryRecipeHandler extends TemplateRecipeHandler
/*     */ {
/*     */   public class CachedDistillingRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
/*     */   {
/*     */     PositionedStack ingred1;
/*     */     PositionedStack ingred2;
/*     */     PositionedStack jars;
/*     */     
/*     */     public CachedDistillingRecipe(ItemStack result, DistilleryRecipes.DistilleryRecipe recipe)
/*     */     {
/*  20 */       super();
/*  21 */       this.ingred1 = new PositionedStack(recipe.inputs[0], 43, 5);
/*  22 */       if (recipe.inputs[1] != null) {
/*  23 */         this.ingred2 = new PositionedStack(recipe.inputs[1], 43, 23);
/*     */       }
/*     */       
/*  26 */       if (recipe.jars > 0) {
/*  27 */         this.jars = new PositionedStack(com.emoniph.witchery.Witchery.Items.GENERIC.itemEmptyClayJar.createStack(recipe.jars), 43, 43);
/*     */       }
/*     */       
/*     */ 
/*  31 */       if (recipe.outputs[0] != null) {
/*  32 */         this.outputs[0] = new PositionedStack(recipe.outputs[0], 105, 5);
/*     */       }
/*     */       
/*  35 */       if (recipe.outputs[1] != null) {
/*  36 */         this.outputs[1] = new PositionedStack(recipe.outputs[1], 123, 5);
/*     */       }
/*     */       
/*  39 */       if (recipe.outputs[2] != null) {
/*  40 */         this.outputs[2] = new PositionedStack(recipe.outputs[2], 105, 23);
/*     */       }
/*     */       
/*  43 */       if (recipe.outputs[3] != null) {
/*  44 */         this.outputs[3] = new PositionedStack(recipe.outputs[3], 123, 23);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public PositionedStack getResult()
/*     */     {
/*  51 */       return this.outputs[0];
/*     */     }
/*     */     
/*     */     public ArrayList<PositionedStack> getIngredients()
/*     */     {
/*  56 */       ArrayList<PositionedStack> recipestacks = new ArrayList();
/*  57 */       recipestacks.add(this.ingred1);
/*  58 */       if (this.ingred2 != null)
/*  59 */         recipestacks.add(this.ingred2);
/*  60 */       if (this.jars != null)
/*  61 */         recipestacks.add(this.jars);
/*  62 */       for (PositionedStack posStack : this.outputs) {
/*  63 */         if (posStack != null)
/*  64 */           recipestacks.add(posStack);
/*     */       }
/*  66 */       return recipestacks;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  72 */     PositionedStack[] outputs = new PositionedStack[6];
/*     */   }
/*     */   
/*     */   public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
/*     */   {
/*  77 */     return com.emoniph.witchery.blocks.BlockDistilleryGUI.class;
/*     */   }
/*     */   
/*     */   public String getRecipeName()
/*     */   {
/*  82 */     return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:distilleryidle.name");
/*     */   }
/*     */   
/*     */   public void loadTransferRects()
/*     */   {
/*  87 */     this.transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(63, 4, 39, 35), "witchery_distilling", new Object[0]));
/*     */   }
/*     */   
/*     */   public void loadCraftingRecipes(String outputId, Object... results)
/*     */   {
/*  92 */     if ((outputId.equals("witchery_distilling")) && (getClass() == NEIDistilleryRecipeHandler.class)) {
/*  93 */       for (DistilleryRecipes.DistilleryRecipe recipe : DistilleryRecipes.instance().recipes) {
/*  94 */         this.arecipes.add(new CachedDistillingRecipe(recipe.outputs[0], recipe));
/*     */       }
/*     */     } else {
/*  97 */       super.loadCraftingRecipes(outputId, results);
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadCraftingRecipes(ItemStack result)
/*     */   {
/* 103 */     DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().findRecipeFor(result);
/* 104 */     if (recipe != null) {
/* 105 */       this.arecipes.add(new CachedDistillingRecipe(result, recipe));
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadUsageRecipes(ItemStack ingredient)
/*     */   {
/* 111 */     DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().findRecipeUsing(ingredient);
/* 112 */     if (recipe != null) {
/* 113 */       this.arecipes.add(new CachedDistillingRecipe(ingredient, recipe));
/*     */     }
/*     */   }
/*     */   
/*     */   public String getGuiTexture()
/*     */   {
/* 119 */     return "witchery:textures/gui/distiller.png";
/*     */   }
/*     */   
/*     */   public void drawExtras(int recipe)
/*     */   {
/* 124 */     drawProgressBar(63, 3, 176, 29, 39, 35, 200, 0);
/* 125 */     drawProgressBar(28, 8, 185, -2, 12, 30, 35, 3);
/*     */   }
/*     */   
/*     */   public String getOverlayIdentifier()
/*     */   {
/* 130 */     return "witchery_distilling";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/NEIDistilleryRecipeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */