/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import codechicken.nei.api.API;
/*     */ import codechicken.nei.api.IConfigureNEI;
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class NEIWitcheryConfig implements IConfigureNEI
/*     */ {
/*     */   public void loadConfig()
/*     */   {
/*  16 */     if ((Config.instance().allowModIntegration) && (Config.instance().allowNotEnoughItems)) {
/*  17 */       API.hideItem(new ItemStack(Witchery.Blocks.OVEN_BURNING));
/*  18 */       API.hideItem(new ItemStack(Witchery.Blocks.DISTILLERY_BURNING));
/*  19 */       API.hideItem(new ItemStack(Witchery.Blocks.BARRIER));
/*  20 */       API.hideItem(new ItemStack(Witchery.Blocks.FORCE));
/*  21 */       API.hideItem(new ItemStack(Witchery.Blocks.CIRCLE));
/*  22 */       API.hideItem(new ItemStack(Witchery.Blocks.GLYPH_RITUAL));
/*  23 */       API.hideItem(new ItemStack(Witchery.Blocks.GLYPH_INFERNAL));
/*  24 */       API.hideItem(new ItemStack(Witchery.Blocks.GLYPH_OTHERWHERE));
/*  25 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_BELLADONNA));
/*  26 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_MANDRAKE));
/*  27 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_ARTICHOKE));
/*  28 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_SNOWBELL));
/*  29 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_WORMWOOD));
/*  30 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_MINDRAKE));
/*  31 */       API.hideItem(new ItemStack(Witchery.Blocks.CHALICE));
/*  32 */       API.hideItem(new ItemStack(Witchery.Blocks.CANDELABRA));
/*  33 */       API.hideItem(new ItemStack(Witchery.Blocks.DREAM_CATCHER));
/*  34 */       API.hideItem(new ItemStack(Witchery.Blocks.DOOR_ALDER));
/*  35 */       API.hideItem(new ItemStack(Witchery.Blocks.DOOR_ROWAN));
/*  36 */       API.hideItem(new ItemStack(Witchery.Blocks.PERPETUAL_ICE_DOOR));
/*  37 */       API.hideItem(new ItemStack(Witchery.Blocks.GLOW_GLOBE));
/*  38 */       API.hideItem(new ItemStack(Witchery.Blocks.PLACED_ITEMSTACK));
/*  39 */       API.hideItem(new ItemStack(Witchery.Blocks.DEMON_HEART));
/*  40 */       API.hideItem(new ItemStack(Witchery.Blocks.FORCE));
/*  41 */       API.hideItem(new ItemStack(Witchery.Blocks.WEB));
/*  42 */       API.hideItem(new ItemStack(Witchery.Blocks.VINE));
/*  43 */       API.hideItem(new ItemStack(Witchery.Blocks.CACTUS));
/*  44 */       API.hideItem(new ItemStack(Witchery.Blocks.LILY));
/*  45 */       API.hideItem(new ItemStack(Witchery.Blocks.BREW_GAS));
/*  46 */       API.hideItem(new ItemStack(Witchery.Blocks.BREW_LIQUID));
/*  47 */       API.hideItem(new ItemStack(Witchery.Blocks.BREW));
/*  48 */       API.hideItem(new ItemStack(Witchery.Blocks.SLURP));
/*  49 */       API.hideItem(new ItemStack(Witchery.Items.BREW));
/*  50 */       API.hideItem(new ItemStack(Witchery.Items.BUCKET_BREW));
/*  51 */       API.hideItem(new ItemStack(Witchery.Blocks.CURSED_BUTTON_STONE));
/*  52 */       API.hideItem(new ItemStack(Witchery.Blocks.CURSED_BUTTON_WOOD));
/*  53 */       API.hideItem(new ItemStack(Witchery.Blocks.CURSED_LEVER));
/*  54 */       API.hideItem(new ItemStack(Witchery.Blocks.CURSED_SNOW_PRESSURE_PLATE));
/*  55 */       API.hideItem(new ItemStack(Witchery.Blocks.CURSED_STONE_PRESSURE_PLATE));
/*  56 */       API.hideItem(new ItemStack(Witchery.Blocks.CURSED_WOODEN_DOOR));
/*  57 */       API.hideItem(new ItemStack(Witchery.Blocks.CURSED_WOODEN_PRESSURE_PLATE));
/*  58 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_WOLFSBANE));
/*  59 */       API.hideItem(new ItemStack(Witchery.Blocks.CROP_GARLIC));
/*  60 */       API.hideItem(new ItemStack(Witchery.Blocks.WALLGEN));
/*  61 */       API.hideItem(new ItemStack(Witchery.Blocks.LIGHT));
/*  62 */       API.hideItem(new ItemStack(Witchery.Blocks.SHADED_GLASS_ON));
/*  63 */       API.hideItem(new ItemStack(Witchery.Blocks.MIRROR));
/*  64 */       API.hideItem(new ItemStack(Witchery.Blocks.MIRROR_UNBREAKABLE));
/*     */       
/*     */ 
/*  67 */       API.registerRecipeHandler(new NEIWitchesOvenRecipeHandler());
/*  68 */       API.registerUsageHandler(new NEIWitchesOvenRecipeHandler());
/*     */       
/*  70 */       API.registerRecipeHandler(new NEIDistilleryRecipeHandler());
/*  71 */       API.registerUsageHandler(new NEIDistilleryRecipeHandler());
/*     */       
/*  73 */       API.registerRecipeHandler(new NEIKettleRecipeHandler());
/*  74 */       API.registerRecipeHandler(new NEICauldronRecipeHandler());
/*  75 */       API.registerUsageHandler(new NEICauldronRecipeHandler());
/*     */       
/*  77 */       API.registerRecipeHandler(new NEISpinningWheelRecipeHandler());
/*  78 */       API.registerUsageHandler(new NEISpinningWheelRecipeHandler());
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
/*  97 */       API.registerHighlightIdentifier(Witchery.Blocks.TRAPPED_PLANT, new NEIHighlightHandler(Witchery.Blocks.TRAPPED_PLANT));
/*  98 */       API.registerHighlightIdentifier(Witchery.Blocks.DOOR_ALDER, new NEIHighlightHandler(Witchery.Blocks.DOOR_ALDER));
/*  99 */       API.registerHighlightIdentifier(Witchery.Blocks.PIT_DIRT, new NEIHighlightHandler(Witchery.Blocks.PIT_DIRT));
/* 100 */       API.registerHighlightIdentifier(Witchery.Blocks.PIT_GRASS, new NEIHighlightHandler(Witchery.Blocks.PIT_GRASS));
/*     */     }
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/* 106 */     return ((Mod)Witchery.class.getAnnotation(Mod.class)).name();
/*     */   }
/*     */   
/*     */   public String getVersion()
/*     */   {
/* 111 */     return ((Mod)Witchery.class.getAnnotation(Mod.class)).version();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/NEIWitcheryConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */