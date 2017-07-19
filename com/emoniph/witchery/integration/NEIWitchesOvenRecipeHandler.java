/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import codechicken.nei.PositionedStack;
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ 
/*     */ public class NEIWitchesOvenRecipeHandler extends codechicken.nei.recipe.TemplateRecipeHandler
/*     */ {
/*     */   public static ArrayList<FuelPair> afuels;
/*     */   public static java.util.TreeSet<Integer> efuels;
/*     */   
/*     */   public class SmeltingPair extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
/*     */   {
/*     */     PositionedStack ingred;
/*     */     PositionedStack result;
/*     */     PositionedStack byproduct;
/*     */     PositionedStack jar;
/*     */     
/*     */     public SmeltingPair(ItemStack ingred, ItemStack result, ItemStack byproduct)
/*     */     {
/*  32 */       super();
/*  33 */       ingred.field_77994_a = 1;
/*  34 */       this.ingred = new PositionedStack(ingred, 51, 6);
/*  35 */       this.result = new PositionedStack(result, 113, 10);
/*  36 */       this.byproduct = new PositionedStack(byproduct, 113, 42);
/*  37 */       this.jar = new PositionedStack(Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), 78, 42);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public java.util.List<PositionedStack> getIngredients()
/*     */     {
/*  58 */       return getCycledIngredients(NEIWitchesOvenRecipeHandler.this.cycleticks / 48, java.util.Arrays.asList(new PositionedStack[] { this.ingred }));
/*     */     }
/*     */     
/*     */     public PositionedStack getResult()
/*     */     {
/*  63 */       return this.result;
/*     */     }
/*     */     
/*     */     public PositionedStack getOtherStack()
/*     */     {
/*     */       
/*  69 */       if ((NEIWitchesOvenRecipeHandler.afuels != null) && (NEIWitchesOvenRecipeHandler.afuels.size() > 0)) {
/*  70 */         return ((NEIWitchesOvenRecipeHandler.FuelPair)NEIWitchesOvenRecipeHandler.afuels.get(NEIWitchesOvenRecipeHandler.this.cycleticks / 48 % NEIWitchesOvenRecipeHandler.afuels.size())).stack;
/*     */       }
/*  72 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public java.util.List<PositionedStack> getOtherStacks()
/*     */     {
/*  78 */       ArrayList<PositionedStack> stacks = new ArrayList();
/*  79 */       PositionedStack stack = getOtherStack();
/*  80 */       if (stack != null) {
/*  81 */         stacks.add(stack);
/*     */       }
/*  83 */       stacks.add(this.byproduct);
/*  84 */       stacks.add(this.jar);
/*     */       
/*  86 */       return stacks;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FuelPair
/*     */   {
/*     */     public PositionedStack stack;
/*     */     public int burnTime;
/*     */     
/*     */     public FuelPair(ItemStack ingred, int burnTime)
/*     */     {
/*  97 */       this.stack = new PositionedStack(ingred, 51, 42);
/*  98 */       this.burnTime = burnTime;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
/*     */   {
/* 112 */     return com.emoniph.witchery.blocks.BlockWitchesOvenGUI.class;
/*     */   }
/*     */   
/*     */   public String getRecipeName()
/*     */   {
/* 117 */     return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:witchesovenidle.name");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void loadCraftingRecipes(String outputId, Object... results)
/*     */   {
/* 124 */     if ((outputId.equals("witchery_cooking")) && (getClass() == NEIWitchesOvenRecipeHandler.class)) {
/* 125 */       Map<ItemStack, ItemStack> recipes = FurnaceRecipes.func_77602_a().func_77599_b();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 130 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack()));
/* 131 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack()));
/* 132 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack()));
/* 133 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 3), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemFoulFume.createStack()));
/* 134 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack()));
/* 135 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack()));
/* 136 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack()));
/*     */       
/* 138 */       for (Map.Entry<ItemStack, ItemStack> recipe : recipes.entrySet()) {
/* 139 */         ItemStack result = (ItemStack)recipe.getValue();
/* 140 */         ItemStack ingred = new ItemStack(((ItemStack)recipe.getKey()).func_77973_b(), 1, -1);
/* 141 */         ItemStack byproduct = Witchery.Items.GENERIC.itemFoulFume.createStack();
/* 142 */         if (ingred.func_77973_b() == Item.func_150898_a(Blocks.field_150345_g)) {
/* 143 */           byproduct = ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemHintOfRebirth.createStack() : ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack() : ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack();
/*     */           
/*     */ 
/*     */ 
/* 147 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/* 148 */         } else if (ingred.func_77973_b() == Item.func_150898_a(Witchery.Blocks.SAPLING)) {
/* 149 */           byproduct = ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() : ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemOdourOfPurity.createStack() : ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack();
/*     */           
/*     */ 
/*     */ 
/* 153 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/* 154 */         } else if ((Witchery.Items.GENERIC.itemAshWood.isMatch(result)) || ((result.func_77973_b() == net.minecraft.init.Items.field_151044_h) && (result.func_77960_j() == 1)) || ((ingred.func_77973_b() instanceof net.minecraft.item.ItemFood))) {
/* 155 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 183 */       super.loadCraftingRecipes(outputId, results);
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadCraftingRecipes(ItemStack result2)
/*     */   {
/* 189 */     Map<ItemStack, ItemStack> recipes = FurnaceRecipes.func_77602_a().func_77599_b();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 204 */     for (Map.Entry<ItemStack, ItemStack> recipe : recipes.entrySet()) {
/* 205 */       ItemStack result = (ItemStack)recipe.getKey();
/* 206 */       if (codechicken.nei.NEIServerUtils.areStacksSameType(result, result2)) {
/* 207 */         ItemStack ingred = new ItemStack(((ItemStack)recipe.getValue()).func_77973_b(), 1, -1);
/* 208 */         ItemStack byproduct = Witchery.Items.GENERIC.itemFoulFume.createStack();
/* 209 */         if (ingred.func_77973_b() == Item.func_150898_a(Blocks.field_150345_g)) {
/* 210 */           byproduct = ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemHintOfRebirth.createStack() : ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack() : ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack();
/*     */           
/*     */ 
/*     */ 
/* 214 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/* 215 */         } else if (ingred.func_77973_b() == Item.func_150898_a(Witchery.Blocks.SAPLING)) {
/* 216 */           byproduct = ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() : ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemOdourOfPurity.createStack() : ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack();
/*     */           
/*     */ 
/*     */ 
/* 220 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/* 221 */         } else if ((Witchery.Items.GENERIC.itemAshWood.isMatch(result)) || (ingred.func_77973_b() == net.minecraft.init.Items.field_151044_h) || ((ingred.func_77973_b() instanceof net.minecraft.item.ItemFood))) {
/* 222 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 253 */     if (Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.isMatch(result2)) {
/* 254 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
/* 255 */     } else if (Witchery.Items.GENERIC.itemBreathOfTheGoddess.isMatch(result2)) {
/* 256 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
/* 257 */     } else if (Witchery.Items.GENERIC.itemHintOfRebirth.isMatch(result2)) {
/* 258 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
/* 259 */     } else if (Witchery.Items.GENERIC.itemWhiffOfMagic.isMatch(result2)) {
/* 260 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
/* 261 */     } else if (Witchery.Items.GENERIC.itemOdourOfPurity.isMatch(result2)) {
/* 262 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
/* 263 */     } else if (Witchery.Items.GENERIC.itemReekOfMisfortune.isMatch(result2)) {
/* 264 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
/* 265 */     } else if (Witchery.Items.GENERIC.itemFoulFume.isMatch(result2)) {
/* 266 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150364_r), new ItemStack(net.minecraft.init.Items.field_151044_h, 1), result2));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void loadUsageRecipes(String inputId, Object... ingredients)
/*     */   {
/* 273 */     if ((inputId.equals("fuel")) && (getClass() == NEIWitchesOvenRecipeHandler.class)) {
/* 274 */       loadCraftingRecipes("witchery_cooking", new Object[0]);
/*     */     } else {
/* 276 */       super.loadUsageRecipes(inputId, ingredients);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadUsageRecipes(ItemStack ingred)
/*     */   {
/* 295 */     Map<ItemStack, ItemStack> recipes = FurnaceRecipes.func_77602_a().func_77599_b();
/*     */     
/* 297 */     for (Map.Entry<ItemStack, ItemStack> recipe : recipes.entrySet()) {
/* 298 */       ItemStack result = (ItemStack)recipe.getValue();
/* 299 */       if (ingred.func_77973_b() == ((ItemStack)recipe.getKey()).func_77973_b())
/*     */       {
/* 301 */         ItemStack byproduct = Witchery.Items.GENERIC.itemFoulFume.createStack();
/* 302 */         if (ingred.func_77973_b() == Item.func_150898_a(Blocks.field_150345_g)) {
/* 303 */           byproduct = ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemHintOfRebirth.createStack() : ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack() : ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack();
/*     */           
/*     */ 
/*     */ 
/* 307 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/* 308 */         } else if (ingred.func_77973_b() == Item.func_150898_a(Witchery.Blocks.SAPLING)) {
/* 309 */           byproduct = ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() : ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemOdourOfPurity.createStack() : ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack();
/*     */           
/*     */ 
/*     */ 
/* 313 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/* 314 */         } else if ((Witchery.Items.GENERIC.itemAshWood.isMatch(result)) || (ingred.func_77973_b() == net.minecraft.init.Items.field_151044_h) || ((ingred.func_77973_b() instanceof net.minecraft.item.ItemFood))) {
/* 315 */           this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 322 */     if (Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(ingred))
/*     */     {
/* 324 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack()));
/* 325 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack()));
/* 326 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack()));
/* 327 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 3), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemFoulFume.createStack()));
/* 328 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack()));
/* 329 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack()));
/* 330 */       this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack()));
/* 331 */       this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150364_r), new ItemStack(net.minecraft.init.Items.field_151044_h, 1, 1), Witchery.Items.GENERIC.itemFoulFume.createStack()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getGuiTexture()
/*     */   {
/* 363 */     return "witchery:textures/gui/witchesOven.png";
/*     */   }
/*     */   
/*     */ 
/*     */   public void loadTransferRects()
/*     */   {
/* 369 */     this.transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(50, 23, 18, 18), "fuel", new Object[0]));
/* 370 */     this.transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(74, 9, 24, 18), "witchery_cooking", new Object[0]));
/*     */   }
/*     */   
/*     */ 
/*     */   public void drawExtras(int recipe)
/*     */   {
/* 376 */     drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
/* 377 */     drawProgressBar(74, 9, 176, 14, 24, 16, 48, 0);
/*     */   }
/*     */   
/*     */   private static Set<Item> excludedFuels() {
/* 381 */     Set<Item> efuels = new java.util.HashSet();
/* 382 */     efuels.add(Item.func_150898_a(Blocks.field_150338_P));
/* 383 */     efuels.add(Item.func_150898_a(Blocks.field_150337_Q));
/* 384 */     efuels.add(Item.func_150898_a(Blocks.field_150472_an));
/* 385 */     efuels.add(Item.func_150898_a(Blocks.field_150444_as));
/* 386 */     efuels.add(Item.func_150898_a(Blocks.field_150466_ao));
/* 387 */     efuels.add(Item.func_150898_a(Blocks.field_150447_bR));
/* 388 */     return efuels;
/*     */   }
/*     */   
/*     */   private static void findFuels() {
/* 392 */     afuels = new ArrayList();
/* 393 */     Set<Item> efuels = excludedFuels();
/* 394 */     for (ItemStack item : codechicken.nei.ItemList.items)
/* 395 */       if ((item != null) && (!efuels.contains(item.func_77973_b()))) {
/* 396 */         int burnTime = net.minecraft.tileentity.TileEntityFurnace.func_145952_a(item);
/* 397 */         if (burnTime > 0)
/* 398 */           afuels.add(new FuelPair(item.func_77946_l(), burnTime));
/*     */       }
/*     */   }
/*     */   
/*     */   private static void findFuelsOnce() {
/* 403 */     if (afuels == null) {
/* 404 */       findFuels();
/*     */     }
/*     */   }
/*     */   
/*     */   public String getOverlayIdentifier()
/*     */   {
/* 410 */     return "witchery_cooking";
/*     */   }
/*     */   
/*     */   public codechicken.nei.recipe.TemplateRecipeHandler newInstance()
/*     */   {
/* 415 */     findFuelsOnce();
/* 416 */     return super.newInstance();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/NEIWitchesOvenRecipeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */