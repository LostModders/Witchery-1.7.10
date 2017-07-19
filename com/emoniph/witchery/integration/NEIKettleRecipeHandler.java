/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import codechicken.nei.PositionedStack;
/*    */ import codechicken.nei.recipe.TemplateRecipeHandler;
/*    */ import com.emoniph.witchery.crafting.KettleRecipes;
/*    */ import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class NEIKettleRecipeHandler extends TemplateRecipeHandler
/*    */ {
/*    */   public class CachedKettleRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
/*    */   {
/*    */     PositionedStack result;
/*    */     
/*    */     public CachedKettleRecipe(ItemStack result, KettleRecipes.KettleRecipe recipe)
/*    */     {
/* 18 */       super();
/* 19 */       this.result = new PositionedStack(result, 119, 24);
/* 20 */       for (int i = 0; i < recipe.inputs.length; i++) {
/* 21 */         if (recipe.inputs[i] != null) {
/* 22 */           this.inputs[i] = new PositionedStack(recipe.inputs[i], i < 3 ? 25 : 43, i * 18 % 54 + 6);
/*    */         } else {
/* 24 */           this.inputs[i] = null;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */     public PositionedStack getResult()
/*    */     {
/* 31 */       return this.result;
/*    */     }
/*    */     
/*    */     public ArrayList<PositionedStack> getIngredients()
/*    */     {
/* 36 */       ArrayList<PositionedStack> recipestacks = new ArrayList();
/* 37 */       recipestacks.add(this.result);
/* 38 */       for (PositionedStack posStack : this.inputs) {
/* 39 */         if (posStack != null)
/* 40 */           recipestacks.add(posStack);
/*    */       }
/* 42 */       return recipestacks;
/*    */     }
/*    */     
/*    */ 
/* 46 */     PositionedStack[] inputs = new PositionedStack[6];
/*    */   }
/*    */   
/*    */   public void loadTransferRects()
/*    */   {
/* 51 */     this.transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(84, 23, 24, 18), "witchery_brewing", new Object[0]));
/*    */   }
/*    */   
/*    */   public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
/*    */   {
/* 56 */     return net.minecraft.client.gui.inventory.GuiCrafting.class;
/*    */   }
/*    */   
/*    */   public String getRecipeName()
/*    */   {
/* 61 */     return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:kettle.name");
/*    */   }
/*    */   
/*    */   public void loadCraftingRecipes(String outputId, Object... results)
/*    */   {
/* 66 */     if ((outputId.equals("witchery_brewing")) && (getClass() == NEIKettleRecipeHandler.class)) {
/* 67 */       for (KettleRecipes.KettleRecipe recipe : KettleRecipes.instance().recipes) {
/* 68 */         this.arecipes.add(new CachedKettleRecipe(recipe.output, recipe));
/*    */       }
/*    */     } else {
/* 71 */       super.loadCraftingRecipes(outputId, results);
/*    */     }
/*    */   }
/*    */   
/*    */   public void loadCraftingRecipes(ItemStack result)
/*    */   {
/* 77 */     KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().findRecipeFor(result);
/* 78 */     if (recipe != null) {
/* 79 */       this.arecipes.add(new CachedKettleRecipe(recipe.output, recipe));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void loadUsageRecipes(ItemStack ingredient) {}
/*    */   
/*    */ 
/*    */   public String getGuiTexture()
/*    */   {
/* 89 */     return "textures/gui/container/crafting_table.png";
/*    */   }
/*    */   
/*    */ 
/*    */   public void drawExtras(int recipe) {}
/*    */   
/*    */ 
/*    */   public String getOverlayIdentifier()
/*    */   {
/* 98 */     return "witchery_brewing";
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/NEIKettleRecipeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */