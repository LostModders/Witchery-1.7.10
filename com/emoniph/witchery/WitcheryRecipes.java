/*      */ package com.emoniph.witchery;
/*      */ 
/*      */ import com.emoniph.witchery.crafting.DistilleryRecipes;
/*      */ import com.emoniph.witchery.crafting.KettleRecipes;
/*      */ import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
/*      */ import com.emoniph.witchery.crafting.RecipeAttachTaglock;
/*      */ import com.emoniph.witchery.crafting.RecipeShapelessAddColor;
/*      */ import com.emoniph.witchery.crafting.RecipeShapelessAddKeys;
/*      */ import com.emoniph.witchery.crafting.RecipeShapelessAddPotion;
/*      */ import com.emoniph.witchery.crafting.RecipeShapelessBiomeCopy;
/*      */ import com.emoniph.witchery.crafting.RecipeShapelessPoppet;
/*      */ import com.emoniph.witchery.crafting.RecipeShapelessRepair;
/*      */ import com.emoniph.witchery.crafting.SpinningRecipes;
/*      */ import com.emoniph.witchery.entity.EntityBabaYaga;
/*      */ import com.emoniph.witchery.entity.EntityDemon;
/*      */ import com.emoniph.witchery.entity.EntityEnt;
/*      */ import com.emoniph.witchery.entity.EntityFamiliar;
/*      */ import com.emoniph.witchery.entity.EntityImp;
/*      */ import com.emoniph.witchery.entity.EntityOwl;
/*      */ import com.emoniph.witchery.entity.EntityReflection;
/*      */ import com.emoniph.witchery.entity.EntityToad;
/*      */ import com.emoniph.witchery.infusion.Infusion;
/*      */ import com.emoniph.witchery.infusion.Infusion.Registry;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionLight;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionOverworld;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePower.Registry;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerBat;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerBlaze;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerCreeper;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerEnderman;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerHeal;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerJump;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerPigMan;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSkeleton;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSpeed;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSpider;
/*      */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSquid;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemGeneral.DreamWeave;
/*      */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*      */ import com.emoniph.witchery.item.ItemPoppet;
/*      */ import com.emoniph.witchery.item.ItemPoppet.PoppetType;
/*      */ import com.emoniph.witchery.item.ItemVanillaPotion;
/*      */ import com.emoniph.witchery.item.ItemVanillaPotion.SubItem;
/*      */ import com.emoniph.witchery.predictions.PredictionArrow;
/*      */ import com.emoniph.witchery.predictions.PredictionBuriedTreasure;
/*      */ import com.emoniph.witchery.predictions.PredictionFall;
/*      */ import com.emoniph.witchery.predictions.PredictionFallInLove;
/*      */ import com.emoniph.witchery.predictions.PredictionFight;
/*      */ import com.emoniph.witchery.predictions.PredictionManager;
/*      */ import com.emoniph.witchery.predictions.PredictionMultiMine;
/*      */ import com.emoniph.witchery.predictions.PredictionNetherTrip;
/*      */ import com.emoniph.witchery.predictions.PredictionRescue;
/*      */ import com.emoniph.witchery.predictions.PredictionWet;
/*      */ import com.emoniph.witchery.ritual.Circle;
/*      */ import com.emoniph.witchery.ritual.RiteRegistry;
/*      */ import com.emoniph.witchery.ritual.RiteRegistry.Ritual;
/*      */ import com.emoniph.witchery.ritual.RitualTraits;
/*      */ import com.emoniph.witchery.ritual.Sacrifice;
/*      */ import com.emoniph.witchery.ritual.SacrificeItem;
/*      */ import com.emoniph.witchery.ritual.SacrificeLiving;
/*      */ import com.emoniph.witchery.ritual.SacrificeMultiple;
/*      */ import com.emoniph.witchery.ritual.SacrificeOptionalItem;
/*      */ import com.emoniph.witchery.ritual.SacrificePower;
/*      */ import com.emoniph.witchery.ritual.rites.RiteBanishDemon;
/*      */ import com.emoniph.witchery.ritual.rites.RiteBindCircleToTalisman;
/*      */ import com.emoniph.witchery.ritual.rites.RiteBindFamiliar;
/*      */ import com.emoniph.witchery.ritual.rites.RiteBindSpiritsToFetish;
/*      */ import com.emoniph.witchery.ritual.rites.RiteBlindness;
/*      */ import com.emoniph.witchery.ritual.rites.RiteCallCreatures;
/*      */ import com.emoniph.witchery.ritual.rites.RiteCallFamiliar;
/*      */ import com.emoniph.witchery.ritual.rites.RiteClimateChange;
/*      */ import com.emoniph.witchery.ritual.rites.RiteCookItem;
/*      */ import com.emoniph.witchery.ritual.rites.RiteCurseCreature;
/*      */ import com.emoniph.witchery.ritual.rites.RiteCurseOfTheWolf;
/*      */ import com.emoniph.witchery.ritual.rites.RiteCursePoppets;
/*      */ import com.emoniph.witchery.ritual.rites.RiteEclipse;
/*      */ import com.emoniph.witchery.ritual.rites.RiteFertility;
/*      */ import com.emoniph.witchery.ritual.rites.RiteForestation;
/*      */ import com.emoniph.witchery.ritual.rites.RiteGlyphicTransformation;
/*      */ import com.emoniph.witchery.ritual.rites.RiteHellOnEarth;
/*      */ import com.emoniph.witchery.ritual.rites.RiteInfusePlayers;
/*      */ import com.emoniph.witchery.ritual.rites.RiteInfusionRecharge;
/*      */ import com.emoniph.witchery.ritual.rites.RiteNaturesPower;
/*      */ import com.emoniph.witchery.ritual.rites.RitePartEarth;
/*      */ import com.emoniph.witchery.ritual.rites.RitePriorIncarnation;
/*      */ import com.emoniph.witchery.ritual.rites.RiteProtectionCircleAttractive;
/*      */ import com.emoniph.witchery.ritual.rites.RiteProtectionCircleBarrier;
/*      */ import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
/*      */ import com.emoniph.witchery.ritual.rites.RiteRainOfToads;
/*      */ import com.emoniph.witchery.ritual.rites.RiteRaiseColumn;
/*      */ import com.emoniph.witchery.ritual.rites.RiteRaiseVolcano;
/*      */ import com.emoniph.witchery.ritual.rites.RiteSetNBT;
/*      */ import com.emoniph.witchery.ritual.rites.RiteSphereEffect;
/*      */ import com.emoniph.witchery.ritual.rites.RiteSummonCreature;
/*      */ import com.emoniph.witchery.ritual.rites.RiteSummonItem;
/*      */ import com.emoniph.witchery.ritual.rites.RiteSummonItem.Binding;
/*      */ import com.emoniph.witchery.ritual.rites.RiteSummonSpectralStone;
/*      */ import com.emoniph.witchery.ritual.rites.RiteTeleportEntity;
/*      */ import com.emoniph.witchery.ritual.rites.RiteTeleportToWaystone;
/*      */ import com.emoniph.witchery.ritual.rites.RiteTransposeOres;
/*      */ import com.emoniph.witchery.ritual.rites.RiteWeatherCallStorm;
/*      */ import com.emoniph.witchery.util.ClothColor;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.Dye;
/*      */ import cpw.mods.fml.common.registry.GameRegistry;
/*      */ import java.util.ArrayList;
/*      */ import java.util.EnumSet;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.entity.boss.EntityWither;
/*      */ import net.minecraft.entity.monster.EntityCaveSpider;
/*      */ import net.minecraft.entity.monster.EntityMagmaCube;
/*      */ import net.minecraft.entity.monster.EntitySilverfish;
/*      */ import net.minecraft.entity.monster.EntitySlime;
/*      */ import net.minecraft.entity.monster.EntitySpider;
/*      */ import net.minecraft.entity.monster.EntityWitch;
/*      */ import net.minecraft.entity.monster.EntityZombie;
/*      */ import net.minecraft.entity.passive.EntityBat;
/*      */ import net.minecraft.entity.passive.EntityChicken;
/*      */ import net.minecraft.entity.passive.EntityCow;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.passive.EntityMooshroom;
/*      */ import net.minecraft.entity.passive.EntityOcelot;
/*      */ import net.minecraft.entity.passive.EntityPig;
/*      */ import net.minecraft.entity.passive.EntitySheep;
/*      */ import net.minecraft.entity.passive.EntityVillager;
/*      */ import net.minecraft.entity.passive.EntityWolf;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.InventoryCrafting;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.CraftingManager;
/*      */ import net.minecraft.item.crafting.ShapedRecipes;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraftforge.oredict.OreDictionary;
/*      */ import net.minecraftforge.oredict.RecipeSorter;
/*      */ import net.minecraftforge.oredict.RecipeSorter.Category;
/*      */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*      */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*      */ 
/*      */ public class WitcheryRecipes
/*      */ {
/*      */   public Infusion infusionEnder;
/*      */   public Infusion infusionLight;
/*      */   public Infusion infusionWorld;
/*      */   public Infusion infusionBeast;
/*      */   
/*      */   public void preInit()
/*      */   {
/*  155 */     RecipeSorter.register("witchery:bindpoppet", RecipeShapelessPoppet.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
/*  156 */     RecipeSorter.register("witchery:addpotion", RecipeShapelessAddPotion.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
/*  157 */     RecipeSorter.register("witchery:repair", RecipeShapelessRepair.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
/*  158 */     RecipeSorter.register("witchery:addcolor", RecipeShapelessAddColor.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
/*  159 */     RecipeSorter.register("witchery:addkeys", RecipeShapelessAddKeys.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
/*  160 */     RecipeSorter.register("witchery:attachtaglock", RecipeAttachTaglock.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
/*  161 */     RecipeSorter.register("witchery:biomecopy", RecipeShapelessBiomeCopy.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
/*      */     
/*      */ 
/*  164 */     if (Config.instance().allowStatueGoddessRecipe) {
/*  165 */       GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.STATUE_GODDESS), new Object[] { "s#s", "shs", "###", Character.valueOf('h'), Witchery.Items.GENERIC.itemDemonHeart.createStack(), Character.valueOf('#'), new ItemStack(Blocks.field_150348_b), Character.valueOf('s'), new ItemStack(Items.field_151156_bN) });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  174 */     ItemStack ash = Witchery.Items.GENERIC.itemAshWood.createStack();
/*  175 */     ItemStack bone = new ItemStack(Items.field_151103_aS);
/*  176 */     GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(4), new Object[] { bone, ash, ash });
/*  177 */     GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(5), new Object[] { bone, ash, ash, ash, ash });
/*  178 */     GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(6), new Object[] { bone, ash, ash, ash, ash, ash, ash });
/*  179 */     GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(7), new Object[] { bone, ash, ash, ash, ash, ash, ash, ash, ash });
/*      */     
/*  181 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Blocks.WICKER_BUNDLE, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), "treeSapling" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  187 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.WICKER_BUNDLE, 5, 1), new Object[] { "#b#", "###", Character.valueOf('#'), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Character.valueOf('b'), Witchery.Items.GENERIC.itemInfernalBlood.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  195 */     addPlantMineRecipe(0, new ItemStack(Blocks.field_150328_O), Witchery.Items.GENERIC.itemBrewOfWebs.createStack());
/*  196 */     addPlantMineRecipe(1, new ItemStack(Blocks.field_150328_O), Witchery.Items.GENERIC.itemBrewOfInk.createStack());
/*  197 */     addPlantMineRecipe(2, new ItemStack(Blocks.field_150328_O), Witchery.Items.GENERIC.itemBrewOfThorns.createStack());
/*  198 */     addPlantMineRecipe(3, new ItemStack(Blocks.field_150328_O), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack());
/*  199 */     addPlantMineRecipe(4, new ItemStack(Blocks.field_150327_N), Witchery.Items.GENERIC.itemBrewOfWebs.createStack());
/*  200 */     addPlantMineRecipe(5, new ItemStack(Blocks.field_150327_N), Witchery.Items.GENERIC.itemBrewOfInk.createStack());
/*  201 */     addPlantMineRecipe(6, new ItemStack(Blocks.field_150327_N), Witchery.Items.GENERIC.itemBrewOfThorns.createStack());
/*  202 */     addPlantMineRecipe(7, new ItemStack(Blocks.field_150327_N), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack());
/*  203 */     addPlantMineRecipe(8, new ItemStack(Blocks.field_150330_I), Witchery.Items.GENERIC.itemBrewOfWebs.createStack());
/*  204 */     addPlantMineRecipe(9, new ItemStack(Blocks.field_150330_I), Witchery.Items.GENERIC.itemBrewOfInk.createStack());
/*  205 */     addPlantMineRecipe(10, new ItemStack(Blocks.field_150330_I), Witchery.Items.GENERIC.itemBrewOfThorns.createStack());
/*  206 */     addPlantMineRecipe(11, new ItemStack(Blocks.field_150330_I), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack());
/*      */     
/*  208 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151170_bI, 2), new Object[] { new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151070_bp) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  222 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.LEAPING_LILY, 5), new Object[] { "#p#", "c#c", "#b#", Character.valueOf('#'), new ItemStack(Blocks.field_150392_bi), Character.valueOf('p'), new ItemStack(Items.field_151068_bn, 1, 8194), Character.valueOf('b'), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151114_aO) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  232 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemBoneNeedle.createStack(8), new Object[] { "ab", Character.valueOf('a'), new ItemStack(Items.field_151103_aS), Character.valueOf('b'), new ItemStack(Items.field_151145_ak) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  238 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.TAGLOCK_KIT), new Object[] { "ab", Character.valueOf('b'), Witchery.Items.GENERIC.itemBoneNeedle.createStack(), Character.valueOf('a'), new ItemStack(Items.field_151069_bo) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  244 */     ItemStack taglocks = new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1);
/*  245 */     ItemStack unboundPoppet = Witchery.Items.POPPET.unboundPoppet.createStack();
/*      */     
/*  247 */     GameRegistry.addRecipe(unboundPoppet, new Object[] { "xyx", "ayb", "x x", Character.valueOf('x'), new ItemStack(Blocks.field_150325_L), Character.valueOf('y'), new ItemStack(Witchery.Blocks.SPANISH_MOSS), Character.valueOf('a'), Witchery.Items.GENERIC.itemBoneNeedle.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151007_F) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  257 */     ItemStack earthPoppet = Witchery.Items.POPPET.earthPoppet.createStack();
/*  258 */     GameRegistry.addRecipe(Witchery.Items.POPPET.earthPoppet.createStack(), new Object[] { " a ", "b#b", " c ", Character.valueOf('#'), Witchery.Items.POPPET.unboundPoppet.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('a'), new ItemStack(Items.field_151119_aD), Character.valueOf('c'), new ItemStack(Blocks.field_150346_d) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  268 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(earthPoppet, new ItemStack[] { taglocks, earthPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  273 */     ItemStack waterPoppet = Witchery.Items.POPPET.waterPoppet.createStack();
/*  274 */     GameRegistry.addRecipe(waterPoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), Witchery.Items.POPPET.unboundPoppet.createStack(), Character.valueOf('a'), Witchery.Items.GENERIC.itemArtichoke.createStack(), Character.valueOf('b'), Dye.INK_SAC.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  283 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(waterPoppet, new ItemStack[] { taglocks, waterPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  288 */     ItemStack foodPoppet = Witchery.Items.POPPET.foodPoppet.createStack();
/*  289 */     GameRegistry.addRecipe(foodPoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('b'), new ItemStack(Items.field_151060_bw), Character.valueOf('a'), new ItemStack(Items.field_151078_bh) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  298 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(foodPoppet, new ItemStack[] { taglocks, foodPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  303 */     ItemStack firePoppet = Witchery.Items.POPPET.firePoppet.createStack();
/*  304 */     GameRegistry.addRecipe(firePoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('b'), Witchery.Items.GENERIC.itemBatWool.createStack(), Character.valueOf('a'), new ItemStack(Witchery.Blocks.EMBER_MOSS) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  313 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(firePoppet, new ItemStack[] { taglocks, firePoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  318 */     ItemStack antiVoodooPoppet = Witchery.Items.POPPET.antiVoodooPoppet.createStack();
/*  319 */     GameRegistry.addRecipe(antiVoodooPoppet, new Object[] { "ced", "a#b", "dfc", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Character.valueOf('c'), new ItemStack(Blocks.field_150327_N), Character.valueOf('d'), new ItemStack(Blocks.field_150328_O), Character.valueOf('e'), new ItemStack(Blocks.field_150337_Q), Character.valueOf('f'), new ItemStack(Blocks.field_150338_P) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  332 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(antiVoodooPoppet, new ItemStack[] { taglocks, antiVoodooPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  337 */     ItemStack poppetProectionPoppet = Witchery.Items.POPPET.poppetProtectionPoppet.createStack();
/*  338 */     GameRegistry.addRecipe(poppetProectionPoppet, new Object[] { "gfg", "e#e", "glg", Character.valueOf('#'), antiVoodooPoppet, Character.valueOf('l'), Witchery.Items.GENERIC.itemDropOfLuck.createStack(), Character.valueOf('e'), Witchery.Items.GENERIC.itemEnderDew.createStack(), Character.valueOf('g'), new ItemStack(Items.field_151074_bl), Character.valueOf('f'), Witchery.Items.GENERIC.itemToeOfFrog.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  349 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(poppetProectionPoppet, new ItemStack[] { taglocks, poppetProectionPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  354 */     ItemStack voodooPoppet = Witchery.Items.POPPET.voodooPoppet.createStack();
/*  355 */     GameRegistry.addRecipe(voodooPoppet, new Object[] { " d ", "a#b", " c ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Character.valueOf('c'), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151071_bq) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  366 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(voodooPoppet, new ItemStack[] { taglocks, voodooPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  371 */     ItemStack toolPoppet = Witchery.Items.POPPET.toolPoppet.createStack();
/*  372 */     GameRegistry.addRecipe(toolPoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemDropOfLuck.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  381 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(toolPoppet, new ItemStack[] { taglocks, toolPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  387 */     ItemStack armorPoppet = Witchery.Items.POPPET.armorPoppet.createStack();
/*  388 */     GameRegistry.addRecipe(armorPoppet, new Object[] { " a ", "b#b", " d ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemDropOfLuck.createStack(), Character.valueOf('d'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  398 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(armorPoppet, new ItemStack[] { taglocks, armorPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  405 */     ItemStack avoidDeathPoppet = Witchery.Items.POPPET.deathPoppet.createStack();
/*  406 */     GameRegistry.addRecipe(avoidDeathPoppet, new Object[] { "axb", "x#x", " x ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), Witchery.Items.GENERIC.itemDropOfLuck.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Character.valueOf('x'), new ItemStack(Items.field_151074_bl) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  416 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(avoidDeathPoppet, new ItemStack[] { taglocks, avoidDeathPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  421 */     ItemStack vampiricPoppet = Witchery.Items.POPPET.vampiricPoppet.createStack();
/*  422 */     GameRegistry.addRecipe(vampiricPoppet, new Object[] { " b ", "c#c", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Character.valueOf('c'), Witchery.Items.GENERIC.itemBatWool.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  432 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(vampiricPoppet, new ItemStack[] { taglocks, taglocks, vampiricPoppet }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  438 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.POPPET_SHELF), new Object[] { "yzy", "zxz", "yzy", Character.valueOf('x'), ClothColor.GREEN.createStack(), Character.valueOf('y'), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Character.valueOf('z'), new ItemStack(Blocks.field_150385_bj) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  447 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.OVEN_IDLE), new Object[] { " z ", "xxx", "xzx", Character.valueOf('x'), new ItemStack(Items.field_151042_j), Character.valueOf('z'), new ItemStack(Blocks.field_150411_aY) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  463 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemSoftClayJar.createStack(4), new Object[] { " # ", "###", Character.valueOf('#'), new ItemStack(Items.field_151119_aD) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  470 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.PLANKS, 4, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(Witchery.Blocks.LOG, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  475 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.PLANKS, 4, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(Witchery.Blocks.LOG, 1, 1) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  480 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.PLANKS, 4, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(Witchery.Blocks.LOG, 1, 2) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  485 */     CraftingManager.func_77594_a().func_77592_b().add(0, getShapedRecipe(Witchery.Items.GENERIC.itemDoorRowan.createStack(), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Witchery.Blocks.PLANKS, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  492 */     CraftingManager.func_77594_a().func_77592_b().add(0, getShapedRecipe(Witchery.Items.GENERIC.itemDoorAlder.createStack(), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Witchery.Blocks.PLANKS, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  499 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.STAIRS_ALDER, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Witchery.Blocks.PLANKS, 1, 1) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  506 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.STAIRS_HAWTHORN, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Witchery.Blocks.PLANKS, 1, 2) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  513 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.STAIRS_ROWAN, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Witchery.Blocks.PLANKS, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  521 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.SNOW_STAIRS, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.field_150433_aE, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  528 */     CraftingManager.func_77594_a().func_77592_b().add(0, getShapedRecipe(new ItemStack(Witchery.Blocks.SNOW_SLAB_SINGLE, 6, 0), new Object[] { "###", "###", Character.valueOf('#'), new ItemStack(Blocks.field_150431_aC, 1, 0) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  534 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.SNOW_PRESSURE_PLATE, 1, 0), new Object[] { "##", Character.valueOf('#'), new ItemStack(Blocks.field_150433_aE, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  555 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemQuicklime.createStack(), new Object[] { "#", Character.valueOf('#'), Witchery.Items.GENERIC.itemAshWood.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  560 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.ALTAR, 3), new Object[] { "abc", "xyx", "xyx", Character.valueOf('a'), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151068_bn), Character.valueOf('c'), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('x'), new ItemStack(Blocks.field_150417_aV, 1, 0), Character.valueOf('y'), new ItemStack(Witchery.Blocks.LOG, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  571 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemAttunedStone.createStack(), new Object[] { "a", "b", "c", Character.valueOf('a'), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151045_i), Character.valueOf('c'), new ItemStack(Items.field_151129_at) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  580 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.DISTILLERY_IDLE), new Object[] { "bxb", "xxx", "yay", Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), Character.valueOf('y'), new ItemStack(Items.field_151043_k), Character.valueOf('x'), new ItemStack(Items.field_151042_j) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  590 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.KETTLE), new Object[] { "bxb", "xax", " y ", Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151055_y), Character.valueOf('x'), new ItemStack(Items.field_151007_F), Character.valueOf('y'), new ItemStack(Items.field_151066_bu) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  600 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.BRAZIER), new Object[] { "#a#", " w ", "www", Character.valueOf('a'), Witchery.Items.GENERIC.itemNecroStone.createStack(), Character.valueOf('w'), new ItemStack(Items.field_151055_y), Character.valueOf('#'), new ItemStack(Items.field_151042_j) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  609 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.CHALK_RITUAL, 2, 0), new Object[] { "xax", "xyx", "xyx", Character.valueOf('a'), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), Character.valueOf('x'), Witchery.Items.GENERIC.itemAshWood.createStack(), Character.valueOf('y'), Witchery.Items.GENERIC.itemGypsum.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  648 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemWaystone.createStack(), new Object[] { "ab", Character.valueOf('a'), new ItemStack(Items.field_151145_ak), Character.valueOf('b'), Witchery.Items.GENERIC.itemBoneNeedle.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  654 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.ARTHANA), new Object[] { " y ", "xbx", " a ", Character.valueOf('a'), new ItemStack(Items.field_151055_y), Character.valueOf('b'), new ItemStack(Items.field_151166_bC), Character.valueOf('y'), new ItemStack(Items.field_151043_k), Character.valueOf('x'), new ItemStack(Items.field_151074_bl) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  663 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.BOLINE), new Object[] { "y", "a", "b", Character.valueOf('a'), new ItemStack(Items.field_151103_aS), Character.valueOf('b'), new ItemStack(Items.field_151166_bC), Character.valueOf('y'), new ItemStack(Items.field_151042_j) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  671 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.CIRCLE_TALISMAN), new Object[] { "yxy", "xax", "yxy", Character.valueOf('a'), new ItemStack(Items.field_151045_i), Character.valueOf('x'), new ItemStack(Items.field_151043_k), Character.valueOf('y'), new ItemStack(Items.field_151074_bl) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  679 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemBroom.createStack(), new Object[] { " x ", " x ", "yyy", Character.valueOf('x'), new ItemStack(Items.field_151055_y), Character.valueOf('y'), new ItemStack(Witchery.Blocks.SAPLING, 1, 2) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  709 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemOddPorkRaw.createStack(), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151078_bh) });
/*  710 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), Witchery.Items.GENERIC.itemOddPorkRaw.createStack() });
/*  711 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151076_bf) });
/*  712 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151076_bf), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151082_bd) });
/*  713 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151082_bd), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151147_al) });
/*      */     
/*      */ 
/*  716 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), Witchery.Items.GENERIC.itemOddPorkCooked.createStack() });
/*  717 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151077_bg) });
/*  718 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151077_bg), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151083_be) });
/*  719 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151083_be), new Object[] { Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151157_am) });
/*      */     
/*      */ 
/*  722 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemOddPorkCooked.createStack(), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151078_bh) });
/*  723 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), Witchery.Items.GENERIC.itemOddPorkRaw.createStack() });
/*  724 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151076_bf) });
/*  725 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151077_bg), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151082_bd) });
/*  726 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151083_be), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151147_al) });
/*      */     
/*      */ 
/*  729 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemOddPorkRaw.createStack(), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151078_bh) });
/*  730 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), Witchery.Items.GENERIC.itemOddPorkRaw.createStack() });
/*  731 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151076_bf) });
/*  732 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151076_bf), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151082_bd) });
/*  733 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151082_bd), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151147_al) });
/*      */     
/*      */ 
/*  736 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), Witchery.Items.GENERIC.itemOddPorkCooked.createStack() });
/*  737 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151077_bg) });
/*  738 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151077_bg), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151083_be) });
/*  739 */     GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151083_be), new Object[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151157_am) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  750 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemCandelabra.createStack(), new Object[] { "xxx", "yay", " y ", Character.valueOf('x'), new ItemStack(Blocks.field_150478_aa), Character.valueOf('y'), new ItemStack(Items.field_151042_j), Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStone.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  758 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemChaliceEmpty.createStack(), new Object[] { "yay", "yxy", " x ", Character.valueOf('x'), new ItemStack(Items.field_151043_k), Character.valueOf('y'), new ItemStack(Items.field_151074_bl), Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStone.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  766 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemChaliceFull.createStack(), new Object[] { "b", "a", Character.valueOf('a'), Witchery.Items.GENERIC.itemChaliceEmpty.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemRedstoneSoup.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  773 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.DIVINER_WATER), new Object[] { "yay", "yay", "axa", Character.valueOf('a'), new ItemStack(Items.field_151055_y), Character.valueOf('y'), new ItemStack(Items.field_151068_bn), Character.valueOf('x'), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  781 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.DIVINER_LAVA), new Object[] { " a ", " x ", "a a", Character.valueOf('x'), new ItemStack(Witchery.Items.DIVINER_WATER), Character.valueOf('a'), new ItemStack(Items.field_151072_bj) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  788 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamMove.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16450), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 16458), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  801 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamMove.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16450), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 24650), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  814 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamDig.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16457), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 16456), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  827 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamDig.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16457), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 24648), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  840 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamEat.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16421), Character.valueOf('e'), Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  853 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamEat.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16421), Character.valueOf('e'), Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  866 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamNightmare.createStack(), new Object[] { "dxe", "bab", "cbc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16452), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 16454), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  878 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemDreamIntensity.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), Witchery.Items.GENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(), Character.valueOf('e'), Witchery.Items.GENERIC.itemBrewOfSleeping.createStack(), Character.valueOf('x'), Witchery.Items.GENERIC.itemDiamondVapour.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  891 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Items.CAULDRON_BOOK), new Object[] { " c ", "a#b", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), new ItemStack(Blocks.field_150346_d) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  900 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookHerbology.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), new ItemStack(Blocks.field_150328_O), Character.valueOf('d'), new ItemStack(Blocks.field_150327_N) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  911 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookWands.createStack(), new Object[] { " c ", "a#b", "   ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), Witchery.Items.GENERIC.itemBranchEnt.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  921 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookBiomes.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), new ItemStack(Blocks.field_150345_g), Character.valueOf('d'), new ItemStack(Blocks.field_150348_b) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  932 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Items.BIOME_BOOK), new Object[] { " d ", "d#d", " d ", Character.valueOf('#'), Witchery.Items.GENERIC.itemBookBiomes.createStack(), Character.valueOf('d'), new ItemStack(Blocks.field_150348_b) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  940 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookBurning.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), Witchery.Items.GENERIC.itemAshWood.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151033_d) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  951 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookOven.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151044_h, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  962 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookDistilling.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  973 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookCircleMagic.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  984 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBookInfusions.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), Witchery.Items.GENERIC.itemOdourOfPurity.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  995 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemWeb.createStack(), new Object[] { " s ", "sws", " s ", Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), new ItemStack(Blocks.field_150321_G) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1003 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.ALLURING_SKULL), new Object[] { " a ", "bcb", " d ", Character.valueOf('a'), Witchery.Items.GENERIC.itemNecroStone.createStack(), Character.valueOf('d'), Witchery.Items.POPPET.voodooPoppet.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151144_bL), Character.valueOf('b'), new ItemStack(Items.field_151114_aO) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1013 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.ALLURING_SKULL), new Object[] { " a ", "bcb", " d ", Character.valueOf('a'), Witchery.Items.GENERIC.itemNecroStone.createStack(), Character.valueOf('d'), Witchery.Items.POPPET.voodooPoppet.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151144_bL, 1, 1), Character.valueOf('b'), new ItemStack(Items.field_151114_aO) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1023 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemSeedsTreefyd.createStack(2), new Object[] { "xax", "cyd", "xbx", Character.valueOf('x'), Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), Character.valueOf('y'), Witchery.Items.GENERIC.itemArtichoke.createStack(), Character.valueOf('c'), new ItemStack(Witchery.Blocks.EMBER_MOSS), Character.valueOf('d'), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Character.valueOf('a'), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1035 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.POLYNESIA_CHARM, 1), new Object[] { "nin", "p#p", "nwn", Character.valueOf('#'), new ItemStack(Items.field_151115_aP), Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('p'), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Character.valueOf('w'), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(), Character.valueOf('n'), new ItemStack(Items.field_151075_bm) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1046 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.DEVILS_TONGUE_CHARM, 1), new Object[] { "b#b", "dse", "btb", Character.valueOf('#'), new ItemStack(Witchery.Items.POLYNESIA_CHARM), Character.valueOf('d'), Witchery.Items.GENERIC.itemDemonHeart.createStack(), Character.valueOf('t'), Witchery.Items.GENERIC.itemDogTongue.createStack(), Character.valueOf('e'), Witchery.Items.GENERIC.itemRefinedEvil.createStack(), Character.valueOf('s'), new ItemStack(Items.field_151144_bL), Character.valueOf('b'), new ItemStack(Items.field_151065_br) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1058 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.OVEN_FUMEFUNNEL), new Object[] { "ele", "ege", "bib", Character.valueOf('e'), new ItemStack(Items.field_151133_ar), Character.valueOf('l'), new ItemStack(Items.field_151129_at), Character.valueOf('b'), new ItemStack(Blocks.field_150339_S), Character.valueOf('g'), new ItemStack(Blocks.field_150426_aN), Character.valueOf('i'), new ItemStack(Blocks.field_150411_aY) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1069 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemFumeFilter.createStack(), new Object[] { "ggg", "sas", "ggg", Character.valueOf('g'), new ItemStack(Blocks.field_150359_w), Character.valueOf('s'), new ItemStack(Items.field_151042_j), Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1078 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED), new Object[] { "b", "f", Character.valueOf('b'), new ItemStack(Witchery.Blocks.OVEN_FUMEFUNNEL), Character.valueOf('f'), Witchery.Items.GENERIC.itemFumeFilter.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1085 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemPurifiedMilk.createStack(3), new Object[] { new ItemStack(Items.field_151117_aB), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), Witchery.Items.GENERIC.itemEmptyClayJar.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1093 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemPurifiedMilk.createStack(3), new Object[] { new ItemStack(Items.field_151105_aU), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), Witchery.Items.GENERIC.itemEmptyClayJar.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1103 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemImpregnatedLeather.createStack(4), new Object[] { "mlm", "ldl", "mlm", Character.valueOf('l'), new ItemStack(Items.field_151116_aA), Character.valueOf('d'), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Character.valueOf('m'), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1112 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.WITCH_HAT), new Object[] { " l ", "sls", "lgl", Character.valueOf('s'), Witchery.Items.GENERIC.itemGoldenThread.createStack(), Character.valueOf('l'), Witchery.Items.GENERIC.itemImpregnatedLeather.createStack(), Character.valueOf('g'), new ItemStack(Items.field_151114_aO) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1121 */     if (Config.instance().allowVoidBrambleRecipe) {
/* 1122 */       GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.VOID_BRAMBLE, 4), new Object[] { "lml", "r#r", "lml", Character.valueOf('#'), new ItemStack(Witchery.Blocks.BRAMBLE), Character.valueOf('r'), new ItemStack(Items.field_151156_bN), Character.valueOf('l'), Witchery.Items.GENERIC.itemDropOfLuck.createStack(), Character.valueOf('m'), Witchery.Items.GENERIC.itemMutandisExtremis.createStack() });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1134 */     GameRegistry.addRecipe(new ItemStack(Items.field_151016_H, 5), new Object[] { "#", Character.valueOf('#'), Witchery.Items.GENERIC.itemCreeperHeart.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1139 */     GameRegistry.addShapelessRecipe(new ItemStack(Blocks.field_150424_aL), new Object[] { Witchery.Items.GENERIC.itemInfernalBlood.createStack(), new ItemStack(Blocks.field_150351_n) });
/*      */     
/* 1141 */     ItemStack impregLeather = Witchery.Items.GENERIC.itemImpregnatedLeather.createStack();
/*      */     
/* 1143 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.WITCH_ROBES), new Object[] { "lsl", "l#l", "lll", Character.valueOf('s'), Witchery.Items.GENERIC.itemGoldenThread.createStack(), Character.valueOf('l'), impregLeather, Character.valueOf('#'), Witchery.Items.GENERIC.itemCreeperHeart.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1152 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.NECROMANCERS_ROBES), new Object[] { "lsl", "l#l", "lll", Character.valueOf('s'), Witchery.Items.GENERIC.itemGoldenThread.createStack(), Character.valueOf('l'), impregLeather, Character.valueOf('#'), Witchery.Items.GENERIC.itemNecroStone.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1161 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemFrozenHeart.createStack(), new Object[] { "n", "h", "t", Character.valueOf('h'), Witchery.Items.GENERIC.itemCreeperHeart.createStack(), Character.valueOf('n'), Witchery.Items.GENERIC.itemIcyNeedle.createStack(), Character.valueOf('t'), new ItemStack(Items.field_151073_bk) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1170 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.ICY_SLIPPERS), new Object[] { "lsl", "l#l", "dod", Character.valueOf('l'), impregLeather, Character.valueOf('s'), Witchery.Items.GENERIC.itemGoldenThread.createStack(), Character.valueOf('#'), Witchery.Items.GENERIC.itemFrozenHeart.createStack(), Character.valueOf('d'), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Character.valueOf('o'), Witchery.Items.GENERIC.itemOdourOfPurity.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1181 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.BITING_BELT), new Object[] { "#lh", "lsl", "l l", Character.valueOf('l'), impregLeather, Character.valueOf('s'), Witchery.Items.GENERIC.itemGoldenThread.createStack(), Character.valueOf('h'), Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), Character.valueOf('#'), new ItemStack(Witchery.Items.PARASYTIC_LOUSE) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1191 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.SEEPING_SHOES), new Object[] { "lsl", "hrh", "mmm", Character.valueOf('l'), impregLeather, Character.valueOf('s'), Witchery.Items.GENERIC.itemGoldenThread.createStack(), Character.valueOf('h'), new ItemStack(Witchery.Items.WITCH_HAND), Character.valueOf('r'), Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), Character.valueOf('m'), new ItemStack(Items.field_151117_aB) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1202 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.RUBY_SLIPPERS), new Object[] { "aba", "tst", "aba", Character.valueOf('s'), new ItemStack(Witchery.Items.SEEPING_SHOES), Character.valueOf('t'), Witchery.Items.GENERIC.itemGoldenThread.createStack(), Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Character.valueOf('b'), Witchery.Items.GENERIC.itemInfernalBlood.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1212 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.BARK_BELT), new Object[] { "ses", "gbg", "shs", Character.valueOf('b'), new ItemStack(Witchery.Items.BITING_BELT), Character.valueOf('s'), Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(), Character.valueOf('g'), Witchery.Items.GENERIC.itemBranchEnt.createStack(), Character.valueOf('h'), Witchery.Items.GENERIC.itemCreeperHeart.createStack(), Character.valueOf('e'), new ItemStack(Items.field_151166_bC) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1223 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemWormyApple.createStack(), new Object[] { new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151102_aT) });
/*      */     
/* 1225 */     ItemStack louse = new ItemStack(Witchery.Items.PARASYTIC_LOUSE, 1, 32767);
/* 1226 */     ItemStack belt = new ItemStack(Witchery.Items.BITING_BELT, 1, 32767);
/* 1227 */     int[] lousePotions = { 8200, 8202, 8264, 8266, 8193, 8194, 8196, 8225, 8226, 8227, 8228, 8229, 8230, 8232, 8233, 8234, 8236, 8238, 8257, 8258, 8259, 8260, 8261, 8262, 8264, 8265, 8266, 8268, 8270, 8201, 8206 };
/* 1228 */     for (int dv : lousePotions) {
/* 1229 */       GameRegistry.addShapelessRecipe(new ItemStack(Witchery.Items.PARASYTIC_LOUSE, 1, dv), new Object[] { louse, new ItemStack(Items.field_151068_bn, 1, dv) });
/* 1230 */       CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddPotion(new ItemStack(Witchery.Items.BITING_BELT, 1, dv), new ItemStack[] { belt, new ItemStack(Items.field_151068_bn, 1, dv) }));
/*      */     }
/*      */     
/* 1233 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.WITCH_ROBES), new ItemStack[] { new ItemStack(Witchery.Items.WITCH_ROBES), impregLeather, impregLeather, impregLeather, impregLeather }));
/* 1234 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.NECROMANCERS_ROBES), new ItemStack[] { new ItemStack(Witchery.Items.NECROMANCERS_ROBES), impregLeather, impregLeather, impregLeather, impregLeather }));
/* 1235 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.WITCH_HAT), new ItemStack[] { new ItemStack(Witchery.Items.WITCH_HAT), impregLeather, impregLeather, impregLeather }));
/* 1236 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.ICY_SLIPPERS), new ItemStack[] { new ItemStack(Witchery.Items.ICY_SLIPPERS), impregLeather, impregLeather, impregLeather }));
/* 1237 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.RUBY_SLIPPERS), new ItemStack[] { new ItemStack(Witchery.Items.RUBY_SLIPPERS), impregLeather, impregLeather, impregLeather }));
/* 1238 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.SEEPING_SHOES), new ItemStack[] { new ItemStack(Witchery.Items.SEEPING_SHOES), impregLeather, impregLeather, impregLeather }));
/* 1239 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.BITING_BELT), new ItemStack[] { new ItemStack(Witchery.Items.BITING_BELT), impregLeather, impregLeather, impregLeather, impregLeather }));
/* 1240 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.BARK_BELT), new ItemStack[] { new ItemStack(Witchery.Items.BARK_BELT), impregLeather, impregLeather, impregLeather, impregLeather }));
/* 1241 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(Witchery.Items.BABAS_HAT), new ItemStack[] { new ItemStack(Witchery.Items.BABAS_HAT), impregLeather, impregLeather, impregLeather }));
/*      */     
/* 1243 */     for (Dye dye : Dye.DYES) {
/* 1244 */       CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddColor(new ItemStack(Witchery.Items.BREW_BAG), new ItemStack[] { new ItemStack(Witchery.Items.BREW_BAG), dye.createStack() }));
/*      */     }
/*      */     
/* 1247 */     GameRegistry.addRecipe(new ItemStack(Witchery.Items.BREW_BAG), new Object[] { "lll", "lsl", "lll", Character.valueOf('l'), impregLeather, Character.valueOf('s'), Witchery.Items.GENERIC.itemGoldenThread.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1255 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemCharmOfDisruptedDreams.createStack(), new Object[] { "lll", "lsl", "lll", Character.valueOf('l'), new ItemStack(Items.field_151055_y), Character.valueOf('s'), Witchery.Items.GENERIC.itemFancifulThread.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1263 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddKeys(Witchery.Items.GENERIC.itemDoorKeyring.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemDoorKey.createStack(), Witchery.Items.GENERIC.itemDoorKey.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1268 */     CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddKeys(Witchery.Items.GENERIC.itemDoorKeyring.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemDoorKeyring.createStack(), Witchery.Items.GENERIC.itemDoorKey.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1273 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemQuartzSphere.createStack(), new Object[] { "qbq", "bgb", "qbq", Character.valueOf('q'), new ItemStack(Items.field_151128_bU), Character.valueOf('b'), new ItemStack(Blocks.field_150371_ca), Character.valueOf('g'), new ItemStack(Blocks.field_150359_w) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1282 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemSleepingApple.createStack(), new Object[] { " g ", "mam", "gsg", Character.valueOf('a'), Witchery.Items.GENERIC.itemWormyApple.createStack(), Character.valueOf('g'), Witchery.Items.GENERIC.itemMutandis.createStack(), Character.valueOf('m'), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('s'), Witchery.Items.GENERIC.itemBrewOfSleeping.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1291 */     GameRegistry.addRecipe(Witchery.Items.GENERIC.itemBatBall.createStack(), new Object[] { "sbs", "b b", "sbs", Character.valueOf('s'), new ItemStack(Items.field_151123_aH), Character.valueOf('b'), new ItemStack(Witchery.Blocks.CRITTER_SNARE, 1, 1) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1298 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Blocks.SPINNING_WHEEL), new Object[] { "aab", "aac", "wsw", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), new ItemStack(Blocks.field_150325_L), Character.valueOf('c'), "stickWood", Character.valueOf('w'), "plankWood", Character.valueOf('s'), Witchery.Items.GENERIC.itemAttunedStone.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1308 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemGraveyardDust.createStack(), new Object[] { Witchery.Items.GENERIC.itemSpectralDust.createStack(), Dye.BONE_MEAL.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1313 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Blocks.FETISH_SCARECROW), new Object[] { "w#w", "sls", "wsw", Character.valueOf('#'), new ItemStack(Blocks.field_150428_aP), Character.valueOf('w'), new ItemStack(Blocks.field_150325_L), Character.valueOf('s'), "stickWood", Character.valueOf('l'), Witchery.Items.GENERIC.itemTormentedTwine.createStack() }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1322 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.FETISH_WITCHS_LADDER), new Object[] { "fsf", "ftf", "fsf", Character.valueOf('f'), new ItemStack(Items.field_151008_G), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('t'), Witchery.Items.GENERIC.itemFancifulThread.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1330 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.FETISH_TREANT_IDOL), new Object[] { "o#o", "srs", "o o", Character.valueOf('#'), new ItemStack(Blocks.field_150428_aP), Character.valueOf('o'), new ItemStack(Blocks.field_150364_r, 1, 0), Character.valueOf('r'), new ItemStack(Witchery.Blocks.LOG, 1, 0), Character.valueOf('s'), Witchery.Items.GENERIC.itemTormentedTwine.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1339 */     SpinningRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemFancifulThread.createStack(), new ItemStack(Witchery.Blocks.WISPY_COTTON, 4), new ItemStack[] { new ItemStack(Items.field_151007_F), Witchery.Items.GENERIC.itemOdourOfPurity.createStack() });
/* 1340 */     SpinningRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Witchery.Items.GENERIC.itemDisturbedCotton.createStack(4), new ItemStack[] { new ItemStack(Items.field_151007_F), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() });
/* 1341 */     SpinningRecipes.instance().addRecipe(new ItemStack(Blocks.field_150321_G), new ItemStack(Items.field_151007_F, 8), new ItemStack[0]);
/* 1342 */     SpinningRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemGoldenThread.createStack(3), new ItemStack(Blocks.field_150407_cf), new ItemStack[] { Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() });
/*      */     
/*      */ 
/* 1345 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemNullCatalyst.createStack(2), new Object[] { new ItemStack(Items.field_151156_bN), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151145_ak), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1352 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemNullCatalyst.createStack(2), new Object[] { Witchery.Items.GENERIC.itemNullCatalyst.createStack(), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151065_br) });
/*      */     
/*      */ 
/*      */ 
/* 1356 */     GameRegistry.addShapedRecipe(Witchery.Items.GENERIC.itemNullifiedLeather.createStack(3), new Object[] { "lll", "lcl", "lll", Character.valueOf('l'), new ItemStack(Items.field_151116_aA), Character.valueOf('c'), Witchery.Items.GENERIC.itemNullCatalyst.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1363 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.HUNTER_HAT), new Object[] { "lll", "l l", Character.valueOf('l'), Witchery.Items.GENERIC.itemNullifiedLeather.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1368 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.HUNTER_COAT), new Object[] { "l l", "lll", "lll", Character.valueOf('l'), Witchery.Items.GENERIC.itemNullifiedLeather.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1374 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.HUNTER_LEGS), new Object[] { "lll", "l l", "l l", Character.valueOf('l'), Witchery.Items.GENERIC.itemNullifiedLeather.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1380 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.HUNTER_BOOTS), new Object[] { "l l", "l l", Character.valueOf('l'), Witchery.Items.GENERIC.itemNullifiedLeather.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1385 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.SHELF_COMPASS), new Object[] { "gdg", "d#d", "gcg", Character.valueOf('g'), new ItemStack(Items.field_151043_k), Character.valueOf('d'), new ItemStack(Items.field_151045_i), Character.valueOf('#'), new ItemStack(Items.field_151113_aN), Character.valueOf('c'), Witchery.Items.GENERIC.itemNullCatalyst.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1394 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBoltStake.createStack(9), new Object[] { " s ", "www", "fff", Character.valueOf('f'), new ItemStack(Items.field_151008_G), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1402 */     GameRegistry.addShapedRecipe(Witchery.Items.GENERIC.itemBoltSplitting.createStack(), new Object[] { " s ", "bbb", " f ", Character.valueOf('f'), new ItemStack(Items.field_151008_G), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('b'), Witchery.Items.GENERIC.itemBoltStake.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1410 */     GameRegistry.addShapedRecipe(Witchery.Items.GENERIC.itemBoltHoly.createStack(12), new Object[] { "aba", "ata", "aba", Character.valueOf('t'), new ItemStack(Items.field_151073_bk), Character.valueOf('a'), Witchery.Items.GENERIC.itemBoltStake.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151103_aS) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1418 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemBoltAntiMagic.createStack(3), new Object[] { Witchery.Items.GENERIC.itemNullCatalyst.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Witchery.Items.GENERIC.itemBoltHoly.createStack(), Witchery.Items.GENERIC.itemBoltHoly.createStack(), Witchery.Items.GENERIC.itemBoltHoly.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1426 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Items.CROSSBOW_PISTOL), new Object[] { "mbm", "swn", " m ", Character.valueOf('m'), new ItemStack(Items.field_151042_j), Character.valueOf('b'), new ItemStack(Items.field_151031_f), Character.valueOf('n'), Witchery.Items.GENERIC.itemBoneNeedle.createStack(), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), "stickWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1436 */     GameRegistry.addShapelessRecipe(Witchery.Items.POTIONS.potionAntidote.createStack(2), new Object[] { Witchery.Items.GENERIC.itemNullCatalyst.createStack(), new ItemStack(Items.field_151068_bn, 1, 8196), new ItemStack(Items.field_151068_bn, 1, 8196) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1443 */     GameRegistry.addShapedRecipe(Witchery.Items.GENERIC.itemContractOwnership.createStack(), new Object[] { "ppp", "pfp", "pps", Character.valueOf('f'), Witchery.Items.GENERIC.itemOddPorkRaw.createStack(), Character.valueOf('p'), new ItemStack(Items.field_151121_aF), Character.valueOf('s'), new ItemStack(Items.field_151007_F) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1451 */     GameRegistry.addRecipe(new RecipeAttachTaglock(Witchery.Items.GENERIC.itemContractOwnership.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemContractOwnership.createStack(), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/* 1455 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemContractBlaze.createStack(), new Object[] { Witchery.Items.GENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151072_bj), Witchery.Items.GENERIC.itemHintOfRebirth.createStack() });
/*      */     
/*      */ 
/*      */ 
/* 1459 */     GameRegistry.addRecipe(new RecipeAttachTaglock(Witchery.Items.GENERIC.itemContractBlaze.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemContractBlaze.createStack(), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/* 1463 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemContractResistFire.createStack(), new Object[] { Witchery.Items.GENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151065_br) });
/*      */     
/*      */ 
/*      */ 
/* 1467 */     GameRegistry.addRecipe(new RecipeAttachTaglock(Witchery.Items.GENERIC.itemContractResistFire.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemContractResistFire.createStack(), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/* 1471 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemContractEvaporate.createStack(), new Object[] { Witchery.Items.GENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151072_bj) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1476 */     GameRegistry.addRecipe(new RecipeAttachTaglock(Witchery.Items.GENERIC.itemContractEvaporate.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemContractEvaporate.createStack(), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/* 1480 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemContractFieryTouch.createStack(), new Object[] { Witchery.Items.GENERIC.itemContractOwnership.createStack(), new ItemStack(Witchery.Blocks.EMBER_MOSS), new ItemStack(Items.field_151072_bj) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1485 */     GameRegistry.addRecipe(new RecipeAttachTaglock(Witchery.Items.GENERIC.itemContractFieryTouch.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemContractFieryTouch.createStack(), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/* 1489 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemContractSmelting.createStack(), new Object[] { Witchery.Items.GENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151129_at) });
/*      */     
/*      */ 
/*      */ 
/* 1493 */     GameRegistry.addRecipe(new RecipeAttachTaglock(Witchery.Items.GENERIC.itemContractSmelting.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemContractSmelting.createStack(), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/* 1497 */     GameRegistry.addShapelessRecipe(new ItemStack(Witchery.Items.LEONARDS_URN, 1, 1), new Object[] { new ItemStack(Witchery.Items.LEONARDS_URN, 1, 0), new ItemStack(Witchery.Items.LEONARDS_URN, 1, 0) });
/*      */     
/*      */ 
/* 1500 */     GameRegistry.addShapelessRecipe(new ItemStack(Witchery.Items.LEONARDS_URN, 1, 2), new Object[] { new ItemStack(Witchery.Items.LEONARDS_URN, 1, 1), new ItemStack(Witchery.Items.LEONARDS_URN, 1, 0) });
/*      */     
/*      */ 
/* 1503 */     GameRegistry.addShapelessRecipe(new ItemStack(Witchery.Items.LEONARDS_URN, 1, 3), new Object[] { new ItemStack(Witchery.Items.LEONARDS_URN, 1, 2), new ItemStack(Witchery.Items.LEONARDS_URN, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/* 1507 */     GameRegistry.addRecipe(new RecipeAttachTaglock(new ItemStack(Witchery.Items.PLAYER_COMPASS), new ItemStack[] { new ItemStack(Witchery.Items.PLAYER_COMPASS, 1, 32767), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1514 */     ItemStack[] logs = { new ItemStack(Blocks.field_150364_r, 1, 0), new ItemStack(Blocks.field_150364_r, 1, 1), new ItemStack(Blocks.field_150364_r, 1, 2), new ItemStack(Blocks.field_150364_r, 1, 3), new ItemStack(Witchery.Blocks.LOG, 1, 0), new ItemStack(Witchery.Blocks.LOG, 1, 1), new ItemStack(Witchery.Blocks.LOG, 1, 2), new ItemStack(Blocks.field_150363_s, 1, 0), new ItemStack(Blocks.field_150363_s, 1, 1) };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1525 */     for (int i = 0; i < logs.length; i++) {
/* 1526 */       GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.STOCKADE, 9, i), new Object[] { " w ", "wfw", "www", Character.valueOf('f'), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('w'), logs[i] });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1534 */     ItemStack kobolditeIngot = Witchery.Items.GENERIC.itemKobolditeIngot.createStack();
/*      */     
/* 1536 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.KOBOLDITE_PICKAXE), new Object[] { "bab", "iii", " s ", Character.valueOf('i'), kobolditeIngot, Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151129_at), Character.valueOf('s'), new ItemStack(Items.field_151055_y) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1545 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.STATUE_OF_WORSHIP), new Object[] { "sks", " s ", "s s", Character.valueOf('k'), kobolditeIngot, Character.valueOf('s'), new ItemStack(Blocks.field_150348_b) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1552 */     GameRegistry.addShapedRecipe(Witchery.Items.GENERIC.itemKobolditePentacle.createStack(), new Object[] { "sks", "kdk", "sks", Character.valueOf('k'), kobolditeIngot, Character.valueOf('s'), Witchery.Items.GENERIC.itemKobolditeNugget.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151045_i) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1560 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.KOBOLDITE_HELM), new Object[] { "iii", "iai", Character.valueOf('i'), kobolditeIngot, Character.valueOf('a'), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1566 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.EARMUFFS), new Object[] { "iii", "i i", "w w", Character.valueOf('i'), new ItemStack(Items.field_151116_aA), Character.valueOf('w'), new ItemStack(Blocks.field_150325_L) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1575 */     GameRegistry.addRecipe(new RecipeShapelessBiomeCopy(new ItemStack(Witchery.Items.BIOME_NOTE), new ItemStack[] { new ItemStack(Witchery.Items.BIOME_BOOK.func_77642_a(Witchery.Items.BIOME_BOOK)), new ItemStack(Items.field_151121_aF) }));
/*      */     
/*      */ 
/*      */ 
/* 1579 */     GameRegistry.addShapelessRecipe(Witchery.Items.GENERIC.itemAnnointingPaste.createStack(), new Object[] { new ItemStack(Witchery.Items.SEEDS_ARTICHOKE), new ItemStack(Witchery.Items.SEEDS_MANDRAKE), new ItemStack(Witchery.Items.SEEDS_BELLADONNA), new ItemStack(Witchery.Items.SEEDS_SNOWBELL) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1585 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.SILVER_SWORD), new Object[] { "ddd", "dsd", "ddd", Character.valueOf('s'), new ItemStack(Items.field_151010_B), Character.valueOf('d'), Witchery.Items.GENERIC.itemSilverDust.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1592 */     Item[][] hunterItems = { { Witchery.Items.HUNTER_BOOTS, Witchery.Items.HUNTER_BOOTS_SILVERED }, { Witchery.Items.HUNTER_LEGS, Witchery.Items.HUNTER_LEGS_SILVERED }, { Witchery.Items.HUNTER_COAT, Witchery.Items.HUNTER_COAT_SILVERED }, { Witchery.Items.HUNTER_HAT, Witchery.Items.HUNTER_HAT_SILVERED } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1598 */     for (int i = 0; i < hunterItems.length; i++) {
/* 1599 */       CraftingManager.func_77594_a().func_92103_a(new ItemStack(hunterItems[i][1]), new Object[] { "dwd", "w#w", "dsd", Character.valueOf('#'), new ItemStack(hunterItems[i][0]), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), Witchery.Items.GENERIC.itemWolfsbane.createStack(), Character.valueOf('d'), Witchery.Items.GENERIC.itemSilverDust.createStack() }).func_92100_c();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1609 */     GameRegistry.addShapedRecipe(Witchery.Items.GENERIC.itemBoltSilver.createStack(3), new Object[] { " s ", "bbb", Character.valueOf('b'), Witchery.Items.GENERIC.itemBoltStake.createStack(), Character.valueOf('s'), Witchery.Items.GENERIC.itemSilverDust.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1615 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.WOLF_ALTAR), new Object[] { " w ", "w#w", "#d#", Character.valueOf('w'), new ItemStack(Witchery.Blocks.WOLFHEAD, 1, 32767), Character.valueOf('#'), new ItemStack(Blocks.field_150348_b), Character.valueOf('d'), Witchery.Items.GENERIC.itemWolfsbane.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1623 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.SILVER_VAT), new Object[] { "ibi", "ifi", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('b'), new ItemStack(Items.field_151131_as), Character.valueOf('f'), new ItemStack(Blocks.field_150460_al) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1630 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.BEARTRAP), new Object[] { "iii", "bpb", "iii", Character.valueOf('p'), new ItemStack(Blocks.field_150443_bT), Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('b'), new ItemStack(Items.field_151097_aZ) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1638 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.WOLFTRAP), new Object[] { "sns", "w#w", "sns", Character.valueOf('#'), new ItemStack(Witchery.Blocks.BEARTRAP), Character.valueOf('s'), Witchery.Items.GENERIC.itemSilverDust.createStack(), Character.valueOf('n'), Witchery.Items.GENERIC.itemNullCatalyst.createStack(), Character.valueOf('w'), Witchery.Items.GENERIC.itemWolfsbane.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1647 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Blocks.GARLIC_GARLAND), new Object[] { "s s", "GsG", "GGG", Character.valueOf('G'), "cropGarlic", Character.valueOf('s'), new ItemStack(Items.field_151007_F) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1655 */     ItemStack[] meats = { new ItemStack(Items.field_151082_bd), new ItemStack(Items.field_151076_bf), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151115_aP), new ItemStack(Items.field_151115_aP, 1), Witchery.Items.GENERIC.itemMuttonRaw.createStack() };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1663 */     for (ItemStack meat : meats) {
/* 1664 */       GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Witchery.Items.STEW_RAW), new Object[] { "cropGarlic", meat, new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151054_z), new ItemStack(Blocks.field_150338_P) }));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1674 */     Item[][] hunterItemsSilvered = { { Witchery.Items.HUNTER_BOOTS_SILVERED, Witchery.Items.HUNTER_BOOTS_GARLICKED }, { Witchery.Items.HUNTER_LEGS_SILVERED, Witchery.Items.HUNTER_LEGS_GARLICKED }, { Witchery.Items.HUNTER_COAT_SILVERED, Witchery.Items.HUNTER_COAT_GARLICKED }, { Witchery.Items.HUNTER_HAT_SILVERED, Witchery.Items.HUNTER_HAT_GARLICKED } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1680 */     for (int i = 0; i < hunterItemsSilvered.length; i++) {
/* 1681 */       CraftingManager.func_77594_a().func_92103_a(new ItemStack(hunterItemsSilvered[i][1]), new Object[] { " g ", "g#g", " s ", Character.valueOf('#'), new ItemStack(hunterItemsSilvered[i][0]), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('g'), new ItemStack(Witchery.Items.SEEDS_GARLIC) }).func_92100_c();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1691 */     for (int i = 0; i < 9; i++) {
/* 1692 */       GameRegistry.addShapelessRecipe(new ItemStack(Witchery.Items.VAMPIRE_BOOK, 1, i + 1), new Object[] { new ItemStack(Witchery.Items.VAMPIRE_BOOK, 1, i), Witchery.Items.GENERIC.itemVampireBookPage.createStack() });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1698 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.BLOOD_GOBLET), new Object[] { "b b", " b ", " g ", Character.valueOf('g'), new ItemStack(Blocks.field_150359_w), Character.valueOf('b'), new ItemStack(Items.field_151069_bo) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1705 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.BLOOD_CRUCIBLE), new Object[] { "s s", "blb", Character.valueOf('s'), new ItemStack(Blocks.field_150390_bg), Character.valueOf('b'), new ItemStack(Blocks.field_150417_aV), Character.valueOf('l'), new ItemStack(Blocks.field_150333_U, 1, 5) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1712 */     GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Items.COFFIN), new Object[] { "ppp", "lbl", "lll", Character.valueOf('b'), new ItemStack(Items.field_151104_aV), Character.valueOf('p'), "plankWood", Character.valueOf('l'), "logWood" }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1721 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.DAYLIGHT_COLLECTOR), new Object[] { "g g", " r ", "ici", Character.valueOf('g'), new ItemStack(Items.field_151043_k), Character.valueOf('r'), new ItemStack(Items.field_151107_aW), Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('c'), new ItemStack(Blocks.field_150453_bW) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1730 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_HELMET), new Object[] { " i ", "i#i", " i ", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('#'), new ItemStack(Witchery.Items.VAMPIRE_HAT) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1737 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_COAT_CHAIN), new Object[] { " i ", "i#i", " i ", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('#'), new ItemStack(Witchery.Items.VAMPIRE_COAT) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1744 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_COAT_FEMALE_CHAIN), new Object[] { " i ", "i#i", " i ", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('#'), new ItemStack(Witchery.Items.VAMPIRE_COAT_FEMALE) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1751 */     ItemStack cloth = Witchery.Items.GENERIC.itemDarkCloth.createStack();
/* 1752 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_HAT), new Object[] { "###", "# #", Character.valueOf('#'), cloth });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1757 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_COAT), new Object[] { "# #", "###", "###", Character.valueOf('#'), cloth });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1763 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_COAT_FEMALE), new Object[] { "# #", "#l#", "###", Character.valueOf('l'), new ItemStack(Items.field_151116_aA), Character.valueOf('#'), cloth });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1770 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_LEGS), new Object[] { "###", "# #", "# #", Character.valueOf('#'), cloth });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1776 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_LEGS_KILT), new Object[] { "###", "###", "# #", Character.valueOf('#'), cloth });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1782 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_BOOTS), new Object[] { "# #", "# #", Character.valueOf('#'), cloth });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1787 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.CANE_SWORD), new Object[] { " #g", "#d#", "## ", Character.valueOf('g'), new ItemStack(Items.field_151043_k), Character.valueOf('d'), new ItemStack(Items.field_151048_u), Character.valueOf('#'), cloth });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1795 */     GameRegistry.addShapedRecipe(new ItemStack(Witchery.Items.VAMPIRE_BOOK), new Object[] { "#s#", "#b#", "#g#", Character.valueOf('s'), new ItemStack(Items.field_151156_bN), Character.valueOf('b'), new ItemStack(Items.field_151122_aG), Character.valueOf('g'), new ItemStack(Witchery.Items.SEEDS_GARLIC), Character.valueOf('#'), new ItemStack(Items.field_151075_bm) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1804 */     for (int i = 0; i < 16; i++) {
/* 1805 */       GameRegistry.addShapedRecipe(new ItemStack(Witchery.Blocks.SHADED_GLASS, 8, i), new Object[] { "###", "#r#", "###", Character.valueOf('r'), new ItemStack(Items.field_151137_ax), Character.valueOf('#'), new ItemStack(Blocks.field_150399_cn, 1, i) });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1814 */     GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemWoodenStake.createStack(), new Object[] { "GGG", "GsG", "GGG", Character.valueOf('G'), "cropGarlic", Character.valueOf('s'), new ItemStack(Items.field_151055_y) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1827 */     OreDictionary.registerOre("plankWood", new ItemStack(Witchery.Blocks.PLANKS, 1, 32767));
/* 1828 */     OreDictionary.registerOre("treeSapling", new ItemStack(Witchery.Blocks.SAPLING, 1, 32767));
/* 1829 */     OreDictionary.registerOre("logWood", new ItemStack(Witchery.Blocks.LOG, 1, 32767));
/* 1830 */     OreDictionary.registerOre("treeLeaves", new ItemStack(Witchery.Blocks.LEAVES, 1, 32767));
/*      */     
/* 1832 */     OreDictionary.registerOre("stairWood", new ItemStack(Witchery.Blocks.STAIRS_ALDER, 1, 32767));
/* 1833 */     OreDictionary.registerOre("stairWood", new ItemStack(Witchery.Blocks.STAIRS_HAWTHORN, 1, 32767));
/* 1834 */     OreDictionary.registerOre("stairWood", new ItemStack(Witchery.Blocks.STAIRS_ROWAN, 1, 32767));
/*      */     
/* 1836 */     OreDictionary.registerOre("cropGarlic", new ItemStack(Witchery.Items.SEEDS_GARLIC, 1, 32767));
/*      */     
/* 1838 */     GameRegistry.addSmelting(Witchery.Items.GENERIC.itemSoftClayJar.createStack(), Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), 0.0F);
/* 1839 */     GameRegistry.addSmelting(Witchery.Items.GENERIC.itemOddPorkRaw.createStack(), Witchery.Items.GENERIC.itemOddPorkCooked.createStack(), 0.0F);
/* 1840 */     GameRegistry.addSmelting(Witchery.Items.GENERIC.itemGoldenThread.createStack(), new ItemStack(Items.field_151074_bl), 0.0F);
/* 1841 */     GameRegistry.addSmelting(Witchery.Items.GENERIC.itemMuttonRaw.createStack(), Witchery.Items.GENERIC.itemMuttonCooked.createStack(), 0.0F);
/* 1842 */     GameRegistry.addSmelting(new ItemStack(Witchery.Blocks.BLOODED_WOOL), Witchery.Items.GENERIC.itemDarkCloth.createStack(), 0.0F);
/*      */     
/* 1844 */     GameRegistry.addSmelting(new ItemStack(Witchery.Items.STEW_RAW), new ItemStack(Witchery.Items.STEW), 1.0F);
/*      */     
/* 1846 */     if (!Config.instance().smeltAllSaplingsToWoodAsh) {
/* 1847 */       GameRegistry.addSmelting(Blocks.field_150345_g, Witchery.Items.GENERIC.itemAshWood.createStack(), 0.0F);
/* 1848 */       GameRegistry.addSmelting(new ItemStack(Witchery.Blocks.SAPLING), Witchery.Items.GENERIC.itemAshWood.createStack(), 0.0F);
/*      */     }
/*      */     
/* 1851 */     GameRegistry.addSmelting(new ItemStack(Witchery.Blocks.LOG, 1, 0), new ItemStack(Items.field_151044_h, 1, 1), 0.0F);
/* 1852 */     GameRegistry.addSmelting(new ItemStack(Witchery.Blocks.LOG, 1, 1), new ItemStack(Items.field_151044_h, 1, 1), 0.0F);
/* 1853 */     GameRegistry.addSmelting(new ItemStack(Witchery.Blocks.LOG, 1, 2), new ItemStack(Items.field_151044_h, 1, 1), 0.0F);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1860 */     DistilleryRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemFoulFume.createStack(), Witchery.Items.GENERIC.itemQuicklime.createStack(), 1, Witchery.Items.GENERIC.itemGypsum.createStack(), Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), new ItemStack(Items.field_151123_aH), null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1869 */     DistilleryRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), Dye.LAPIS_LAZULI.createStack(), 3, Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(), new ItemStack(Items.field_151123_aH), Witchery.Items.GENERIC.itemFoulFume.createStack());
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1878 */     DistilleryRecipes.instance().addRecipe(new ItemStack(Items.field_151045_i), Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), 3, Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1887 */     DistilleryRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151073_bk), 3, Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), Witchery.Items.GENERIC.itemFoulFume.createStack(), Witchery.Items.GENERIC.itemRefinedEvil.createStack());
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1896 */     DistilleryRecipes.instance().addRecipe(new ItemStack(Items.field_151079_bi), null, 6, Witchery.Items.GENERIC.itemEnderDew.createStack(2), Witchery.Items.GENERIC.itemEnderDew.createStack(2), Witchery.Items.GENERIC.itemEnderDew.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack());
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1905 */     DistilleryRecipes.instance().addRecipe(new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151016_H), 1, new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151114_aO), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1914 */     DistilleryRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemDemonHeart.createStack(), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), 4, Witchery.Items.GENERIC.itemInfernalBlood.createStack(2), Witchery.Items.GENERIC.itemInfernalBlood.createStack(2), Witchery.Items.GENERIC.itemRefinedEvil.createStack(), null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1923 */     DistilleryRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemDemonHeart.createStack(), new ItemStack(Blocks.field_150424_aL), 2, new ItemStack(Blocks.field_150425_aM), Witchery.Items.GENERIC.itemInfernalBlood.createStack(), Witchery.Items.GENERIC.itemInfernalBlood.createStack(), null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1932 */     DistilleryRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(), Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), 2, Witchery.Items.GENERIC.itemFocusedWill.createStack(), Witchery.Items.GENERIC.itemCondensedFear.createStack(), Witchery.Items.GENERIC.itemBrewOfHollowTears.createStack(4), Witchery.Items.GENERIC.itemBrewOfHollowTears.createStack(4));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1945 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfVines.createStack(3), 1, 0, 0.0F, -16753913, 0, new ItemStack[] { new ItemStack(Blocks.field_150395_bd), new ItemStack(Blocks.field_150337_Q), new ItemStack(Blocks.field_150338_P), Witchery.Items.GENERIC.itemDogTongue.createStack(), new ItemStack(Items.field_151015_O), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1956 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfWebs.createStack(3), 1, 0, 0.0F, -1, 0, new ItemStack[] { Witchery.Items.GENERIC.itemWeb.createStack(), new ItemStack(Blocks.field_150337_Q), Witchery.Items.GENERIC.itemBatWool.createStack(), new ItemStack(Blocks.field_150327_N), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1967 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfThorns.createStack(3), 1, 0, 0.0F, -10027232, 0, new ItemStack[] { Dye.CACTUS_GREEN.createStack(), new ItemStack(Blocks.field_150338_P), Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), new ItemStack(Blocks.field_150328_O), Witchery.Items.GENERIC.itemMandrakeRoot.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1978 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfInk.createStack(3), 1, 0, 0.0F, -13421773, 0, new ItemStack[] { Dye.INK_SAC.createStack(), Witchery.Items.GENERIC.itemQuicklime.createStack(), Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), new ItemStack(Items.field_151123_aH), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Witchery.Items.GENERIC.itemRowanBerries.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1989 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(3), 1, 0, 0.0F, -11258073, 0, new ItemStack[] { new ItemStack(Witchery.Blocks.SAPLING, 1, 0), new ItemStack(Witchery.Blocks.SAPLING, 1, 1), new ItemStack(Witchery.Blocks.SAPLING, 1, 2), Witchery.Items.GENERIC.itemDogTongue.createStack(), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Blocks.field_150328_O) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2000 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfErosion.createStack(3), 1, 0, 0.0F, -4456656, 0, new ItemStack[] { Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), Witchery.Items.GENERIC.itemQuicklime.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Blocks.field_150327_N), new ItemStack(Items.field_151064_bs) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2011 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfRaising.createStack(3), 1, 0, 500.0F, -12120505, 0, new ItemStack[] { Witchery.Items.GENERIC.itemBatWool.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151137_ax), Witchery.Items.GENERIC.itemOilOfVitriol.createStack(), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151078_bh) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2022 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewGrotesque.createStack(3), 1, 0, 500.0F, -13491946, 0, new ItemStack[] { Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Witchery.Items.GENERIC.itemArtichoke.createStack(), Witchery.Items.GENERIC.itemDogTongue.createStack(), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151170_bI) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2033 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfLove.createStack(3), 1, 0, 0.0F, 42492, 0, new ItemStack[] { new ItemStack(Blocks.field_150328_O), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(), Witchery.Items.GENERIC.itemArtichoke.createStack(), new ItemStack(Items.field_151150_bK), new ItemStack(Blocks.field_150392_bi), Dye.COCOA_BEANS.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2044 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfIce.createStack(3), 1, 0, 1000.0F, -13565953, 0, new ItemStack[] { Witchery.Items.GENERIC.itemIcyNeedle.createStack(), new ItemStack(Items.field_151126_ay), Witchery.Items.GENERIC.itemArtichoke.createStack(), new ItemStack(Items.field_151060_bw), new ItemStack(Blocks.field_150337_Q), Witchery.Items.GENERIC.itemOdourOfPurity.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2055 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfTheDepths.createStack(3), 1, 0, 0.0F, -15260093, 0, new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Witchery.Items.GENERIC.itemArtichoke.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Blocks.field_150392_bi), Dye.INK_SAC.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2066 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfInfection.createStack(3), 0, 0, 0.0F, -11112850, 0, new ItemStack[] { Witchery.Items.GENERIC.itemToeOfFrog.createStack(), Witchery.Items.GENERIC.itemCreeperHeart.createStack(), Witchery.Items.GENERIC.itemWormyApple.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Items.field_151078_bh), Witchery.Items.GENERIC.itemMutandis.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2077 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfSleeping.createStack(3), 1, 0, 0.0F, -7710856, 0, new ItemStack[] { Witchery.Items.GENERIC.itemPurifiedMilk.createStack(), new ItemStack(Items.field_151106_aX), Witchery.Items.GENERIC.itemBrewOfLove.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(), Witchery.Items.GENERIC.itemIcyNeedle.createStack(), Witchery.Items.GENERIC.itemArtichoke.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2088 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(3), 0, 0, 0.0F, -16711834, Config.instance().dimensionDreamID, new ItemStack[] { Witchery.Items.GENERIC.itemFancifulThread.createStack(), Witchery.Items.GENERIC.itemArtichoke.createStack(), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Witchery.Blocks.SPANISH_MOSS), new ItemStack(Witchery.Blocks.GLINT_WEED), Witchery.Items.GENERIC.itemBatWool.createStack() }).setUnlocalizedName("witchery.brew.flowingspirit");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2100 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfWasting.createStack(3), 1, 0, 0.0F, -12440546, 0, new ItemStack[] { Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), new ItemStack(Items.field_151078_bh), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Witchery.Blocks.EMBER_MOSS), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151070_bp) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2111 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfBats.createStack(3), 1, 0, 0.0F, -9809858, 0, new ItemStack[] { Witchery.Items.GENERIC.itemBatBall.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack(), new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151102_aT), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151016_H) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2122 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewSubstitution.createStack(3), 1, 0, 0.0F, -7010720, 0, new ItemStack[] { Witchery.Items.GENERIC.itemEnderDew.createStack(), Witchery.Items.GENERIC.itemEnderDew.createStack(), Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151110_aK), new ItemStack(Items.field_151064_bs), Witchery.Items.GENERIC.itemBranchEnt.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2133 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewRevealing.createStack(3), 1, 0, 0.0F, -4079167, 0, new ItemStack[] { new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151068_bn, 1, 8198), new ItemStack(Blocks.field_150338_P), Witchery.Items.GENERIC.itemOdourOfPurity.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2145 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfSolidDirt.createStack(3), 1, 0, 2000.0F, -11720688, 0, true, new ItemStack[] { new ItemStack(Blocks.field_150346_d), Witchery.Items.GENERIC.itemFoulFume.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack(), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Witchery.Blocks.SPANISH_MOSS) }).setUnlocalizedName("witchery.brew.solidification");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2157 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfSolidRock.createStack(3), 1, 0, 2000.0F, -8355712, 0, false, new ItemStack[] { new ItemStack(Blocks.field_150348_b), Witchery.Items.GENERIC.itemFoulFume.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack(), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Witchery.Blocks.SPANISH_MOSS) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2169 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfSolidSand.createStack(3), 1, 0, 2000.0F, -3495323, 0, false, new ItemStack[] { new ItemStack(Blocks.field_150354_m), Witchery.Items.GENERIC.itemFoulFume.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack(), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Witchery.Blocks.SPANISH_MOSS) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2181 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfSolidSandstone.createStack(3), 1, 0, 2000.0F, -8427008, 0, false, new ItemStack[] { new ItemStack(Blocks.field_150322_A), Witchery.Items.GENERIC.itemFoulFume.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack(), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Witchery.Blocks.SPANISH_MOSS) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2193 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfSolidErosion.createStack(3), 1, 0, 2000.0F, 62236, 0, false, new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfErosion.createStack(), Witchery.Items.GENERIC.itemFoulFume.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack(), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Witchery.Blocks.SPANISH_MOSS) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2206 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfCursedLeaping.createStack(3), 1, 1, 0.0F, -16758145, 0, new ItemStack[] { new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151034_e), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151115_aP) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2217 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfFrogsTongue.createStack(3), 1, 2, 0.0F, -12938226, 0, new ItemStack[] { new ItemStack(Blocks.field_150337_Q), new ItemStack(Items.field_151015_O), Witchery.Items.GENERIC.itemBrewOfWebs.createStack(), Witchery.Items.GENERIC.itemArtichoke.createStack(), new ItemStack(Items.field_151123_aH), Witchery.Items.GENERIC.itemToeOfFrog.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2228 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemBrewOfHitchcock.createStack(3), 1, 3, 0.0F, -3908582, 0, new ItemStack[] { new ItemStack(Blocks.field_150338_P), new ItemStack(Items.field_151014_N), Witchery.Items.GENERIC.itemBrewOfThorns.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack(), new ItemStack(Items.field_151008_G), Witchery.Items.GENERIC.itemOwletsWing.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2240 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemCongealedSpirit.createStack(), 0, 0, 2000.0F, -3096310, 0, new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfHollowTears.createStack(), Witchery.Items.GENERIC.itemSubduedSpirit.createStack(), Witchery.Items.GENERIC.itemSubduedSpirit.createStack(), Witchery.Items.GENERIC.itemSubduedSpirit.createStack(), Witchery.Items.GENERIC.itemSubduedSpirit.createStack(), Witchery.Items.GENERIC.itemSubduedSpirit.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2252 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), 0, 0, 1000.0F, -59882, 0, new ItemStack[] { new ItemStack(Items.field_151137_ax), Witchery.Items.GENERIC.itemDropOfLuck.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack(), Witchery.Items.GENERIC.itemDogTongue.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), Witchery.Items.GENERIC.itemMandrakeRoot.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2263 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemFlyingOintment.createStack(), 0, 0, 3000.0F, 47916, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8258), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151008_G), Witchery.Items.GENERIC.itemBatWool.createStack(), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2274 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemMysticUnguent.createStack(), 0, 0, 3000.0F, -14333109, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8265), new ItemStack(Items.field_151045_i), new ItemStack(Witchery.Blocks.SAPLING, 1, 0), Witchery.Items.GENERIC.itemCreeperHeart.createStack(), Witchery.Items.GENERIC.itemInfernalBlood.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2285 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemHappenstanceOil.createStack(), 0, 0, 2000.0F, 8534058, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8262), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151150_bK), new ItemStack(Items.field_151070_bp), Witchery.Items.GENERIC.itemMandrakeRoot.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2296 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemGhostOfTheLight.createStack(2), 0, 0, 4000.0F, -5584658, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8270), new ItemStack(Items.field_151068_bn, 1, 8259), Witchery.Items.POPPET.firePoppet.createStack(), new ItemStack(Blocks.field_150478_aa), Witchery.Items.GENERIC.itemDogTongue.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2307 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemSoulOfTheWorld.createStack(2), 0, 0, 4000.0F, -16003328, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8257), new ItemStack(Items.field_151153_ao, 1, 1), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Witchery.Blocks.SAPLING, 1, 0) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2318 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemSpiritOfOtherwhere.createStack(2), 0, 0, 4000.0F, -7128833, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8258), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151061_bv), Witchery.Items.GENERIC.itemDropOfLuck.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2329 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemSpiritOfOtherwhere.createStack(2), 0, 0, 4000.0F, -7128833, 0, false, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 16210), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151061_bv), Witchery.Items.GENERIC.itemDropOfLuck.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2341 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemInfernalAnimus.createStack(2), 0, 0, 4000.0F, -7598080, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8236), Witchery.Items.POPPET.voodooPoppet.createStack(), Witchery.Items.GENERIC.itemDemonHeart.createStack(), Witchery.Items.GENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151072_bj) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2352 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemInfernalAnimus.createStack(2), 0, 0, 4000.0F, -7598080, 0, false, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 16172), Witchery.Items.POPPET.voodooPoppet.createStack(), Witchery.Items.GENERIC.itemDemonHeart.createStack(), Witchery.Items.GENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151072_bj) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2364 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemInfusionBase.createStack(), 1, 0, 3000.0F, -10520657, 0, new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(), Witchery.Items.GENERIC.itemCreeperHeart.createStack(), Witchery.Items.GENERIC.itemToeOfFrog.createStack(), Witchery.Items.GENERIC.itemOwletsWing.createStack(), Witchery.Items.GENERIC.itemDogTongue.createStack() });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2375 */     KettleRecipes.instance().addRecipe(Witchery.Items.GENERIC.itemInfusionBase.createStack(2), 0, 0, 3000.0F, -10520657, 0, new ItemStack[] { Witchery.Items.GENERIC.itemInfusionBase.createStack(), Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack(), Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack(), new ItemStack(Witchery.Blocks.BRAMBLE, 1, 1) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2390 */     CreaturePower.Registry.instance().add(new CreaturePowerSpider(1, EntityCaveSpider.class));
/* 2391 */     CreaturePower.Registry.instance().add(new CreaturePowerSpider(2, EntitySpider.class));
/* 2392 */     CreaturePower.Registry.instance().add(new CreaturePowerCreeper(3));
/* 2393 */     CreaturePower.Registry.instance().add(new CreaturePowerBat(4, EntityBat.class));
/* 2394 */     CreaturePower.Registry.instance().add(new CreaturePowerSquid(5));
/* 2395 */     CreaturePower.Registry.instance().add(new com.emoniph.witchery.infusion.infusions.creature.CreaturePowerGhast(6));
/* 2396 */     CreaturePower.Registry.instance().add(new CreaturePowerBlaze(7));
/* 2397 */     CreaturePower.Registry.instance().add(new CreaturePowerPigMan(8));
/* 2398 */     CreaturePower.Registry.instance().add(new com.emoniph.witchery.infusion.infusions.creature.CreaturePowerZombie(9));
/* 2399 */     CreaturePower.Registry.instance().add(new CreaturePowerSkeleton(10));
/* 2400 */     CreaturePower.Registry.instance().add(new CreaturePowerJump(11, EntityMagmaCube.class));
/* 2401 */     CreaturePower.Registry.instance().add(new CreaturePowerJump(12, EntitySlime.class));
/* 2402 */     CreaturePower.Registry.instance().add(new CreaturePowerSpeed(13, EntitySilverfish.class));
/* 2403 */     CreaturePower.Registry.instance().add(new CreaturePowerSpeed(14, EntityOcelot.class));
/* 2404 */     CreaturePower.Registry.instance().add(new CreaturePowerSpeed(15, EntityWolf.class));
/* 2405 */     CreaturePower.Registry.instance().add(new CreaturePowerSpeed(16, EntityHorse.class));
/* 2406 */     CreaturePower.Registry.instance().add(new CreaturePowerEnderman(17));
/* 2407 */     CreaturePower.Registry.instance().add(new CreaturePowerHeal(18, EntitySheep.class, 1));
/* 2408 */     CreaturePower.Registry.instance().add(new CreaturePowerHeal(19, EntityCow.class, 1));
/* 2409 */     CreaturePower.Registry.instance().add(new CreaturePowerHeal(20, EntityChicken.class, 1));
/* 2410 */     CreaturePower.Registry.instance().add(new CreaturePowerHeal(21, EntityPig.class, 1));
/* 2411 */     CreaturePower.Registry.instance().add(new CreaturePowerHeal(22, EntityVillager.class, 2));
/* 2412 */     CreaturePower.Registry.instance().add(new CreaturePowerHeal(23, EntityMooshroom.class, 2));
/* 2413 */     CreaturePower.Registry.instance().add(new CreaturePowerBat(24, EntityOwl.class));
/* 2414 */     CreaturePower.Registry.instance().add(new CreaturePowerJump(25, EntityToad.class));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2421 */     RiteRegistry.addRecipe(1, 0, new RiteBindCircleToTalisman(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.CIRCLE_TALISMAN), new ItemStack(Items.field_151137_ax) }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[0]).setUnlocalizedName("witchery.rite.bindcircle");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2430 */     RiteRegistry.addRecipe(2, 1, new RiteSummonItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack(), RiteSummonItem.Binding.LOCATION), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystone.createStack(), Witchery.Items.GENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151114_aO) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystone");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2441 */     RiteRegistry.addRecipe(3, 3, new RiteSummonItem(Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemAttunedStone.createStack(), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151137_ax), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemQuicklime.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.chargestone");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2455 */     RiteRegistry.addRecipe(4, 4, new RiteInfusionRecharge(10, 4, 40.0F, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151068_bn, 1, 8193) }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionrecharge");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2470 */     RiteRegistry.addRecipe(5, 5, new RiteTeleportToWaystone(3), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystoneBound.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0) }).setUnlocalizedName("witchery.rite.teleporttowaystone");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2477 */     RiteRegistry.addRecipe(6, 6, new RiteTeleportEntity(3), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystone.createStack(), new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151036_c) }), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 28, 0) }).setUnlocalizedName("witchery.rite.teleportentity");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2489 */     RiteRegistry.addRecipe(7, 7, new RiteTransposeOres(8, 30, new Block[] { Blocks.field_150366_p, Blocks.field_150352_o }), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151065_br), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 40, 0) }).setUnlocalizedName("witchery.rite.teleportironore");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2504 */     RiteRegistry.addRecipe(8, 8, new RiteProtectionCircleRepulsive(4, 0.8F, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151137_ax) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.protection");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2514 */     RiteRegistry.addRecipe(9, 9, new RiteProtectionCircleAttractive(4, 0.8F, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151123_aH), new ItemStack(Items.field_151137_ax) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.imprisonment");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2524 */     RiteRegistry.addRecipe(10, 10, new RiteProtectionCircleBarrier(4, 5, 1.2F, false, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150343_Z), new ItemStack(Items.field_151137_ax) }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.barrier");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2535 */     RiteRegistry.addRecipe(11, 11, new RiteProtectionCircleBarrier(6, 6, 1.4F, true, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150343_Z), new ItemStack(Items.field_151114_aO) }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.barrierlarge");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2546 */     RiteRegistry.addRecipe(12, 12, new RiteProtectionCircleBarrier(6, 4, 0.0F, true, 60), new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150343_Z), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.barrierportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2558 */     RiteRegistry.addRecipe(13, 13, new RiteRaiseVolcano(8, 8), new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150348_b), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151010_B), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.volcano");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2568 */     RiteRegistry.addRecipe(14, 14, new RiteWeatherCallStorm(0, 3, 8), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151041_m), Witchery.Items.GENERIC.itemAshWood.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.storm");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2579 */     RiteRegistry.addRecipe(15, 15, new RiteWeatherCallStorm(3, 7, 18), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151052_q), Witchery.Items.GENERIC.itemAshWood.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.stormlarge");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2590 */     RiteRegistry.addRecipe(16, 16, new RiteWeatherCallStorm(3, 7, 18), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151040_l), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.stormportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2599 */     RiteRegistry.addRecipe(17, 17, new RiteEclipse(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151049_t), Witchery.Items.GENERIC.itemQuicklime.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_DAY), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.eclipse");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2609 */     RiteRegistry.addRecipe(18, 18, new RiteEclipse(), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151036_c), Witchery.Items.GENERIC.itemQuicklime.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.of(RitualTraits.ONLY_AT_DAY), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.eclipseportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2618 */     RiteRegistry.addRecipe(19, 19, new RitePartEarth(60, 1, 10), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfErosion.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.partearth");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2625 */     RiteRegistry.addRecipe(20, 20, new RiteRaiseColumn(4, 8), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151016_H) }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.raiseearth");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2642 */     RiteRegistry.addRecipe(21, 23, new RiteBanishDemon(9), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151065_br), Witchery.Items.GENERIC.itemWaystone.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.banishdemonportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2651 */     RiteRegistry.addRecipe(22, 24, new RiteBanishDemon(9), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151065_br), Witchery.Items.GENERIC.itemWaystone.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.banishdemon");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2662 */     RiteRegistry.addRecipe(23, 25, new RiteSummonCreature(EntityDemon.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151079_bi) }), new SacrificeLiving(EntityVillager.class), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summondemon");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2674 */     RiteRegistry.addRecipe(24, 26, new RiteSummonCreature(EntityDemon.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151072_bj), new ItemStack(Items.field_151079_bi), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summondemonexpensive");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2687 */     RiteRegistry.addRecipe(25, 27, new RiteSummonCreature(EntityWither.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151144_bL, 1, 1), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151079_bi) }), new SacrificeLiving(EntityVillager.class), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28), new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summonwither");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2700 */     RiteRegistry.addRecipe(26, 28, new RiteSummonCreature(EntityWither.class, false), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151144_bL, 1, 1), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151079_bi), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28), new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summonwitherexpensive");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2716 */     this.infusionLight = new InfusionLight(1);
/* 2717 */     Infusion.Registry.instance().add(this.infusionLight);
/*      */     
/* 2719 */     RiteRegistry.addRecipe(27, 31, new RiteInfusePlayers(this.infusionLight, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemGhostOfTheLight.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionlight");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2729 */     this.infusionWorld = new InfusionOverworld(2);
/* 2730 */     Infusion.Registry.instance().add(this.infusionWorld);
/*      */     
/* 2732 */     RiteRegistry.addRecipe(28, 32, new RiteInfusePlayers(this.infusionWorld, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemSoulOfTheWorld.createStack() }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionearth");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2742 */     this.infusionEnder = new InfusionOtherwhere(3);
/* 2743 */     Infusion.Registry.instance().add(this.infusionEnder);
/*      */     
/* 2745 */     RiteRegistry.addRecipe(29, 33, new RiteInfusePlayers(this.infusionEnder, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemSpiritOfOtherwhere.createStack() }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0), new Circle(0, 28, 0) }).setUnlocalizedName("witchery.rite.infusionender");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2755 */     this.infusionBeast = new InfusionInfernal(4);
/* 2756 */     Infusion.Registry.instance().add(this.infusionBeast);
/*      */     
/* 2758 */     RiteRegistry.addRecipe(30, 34, new RiteInfusePlayers(this.infusionBeast, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemInfernalAnimus.createStack() }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16), new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.infusionhell");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2768 */     RiteRegistry.addRecipe(31, 35, new RiteSummonItem(Witchery.Items.GENERIC.itemBroomEnchanted.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBroom.createStack(), Witchery.Items.GENERIC.itemFlyingOintment.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionsky");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2780 */     RiteRegistry.addRecipe(32, 36, new RiteSummonItem(Witchery.Items.GENERIC.itemNecroStone.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemAttunedStone.createStack(), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151078_bh), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Items.field_151040_l), Witchery.Items.GENERIC.itemSpectralDust.createStack() }), new SacrificePower(1000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.necrostone");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2794 */     RiteRegistry.addRecipe(33, 30, new RiteSummonCreature(EntityFamiliar.class, true), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemDropOfLuck.createStack(), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151043_k), new ItemStack(Witchery.Items.ARTHANA) }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.summonfamiliar");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2806 */     RiteRegistry.addRecipe(34, 2, new RiteSummonItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack(2), RiteSummonItem.Binding.COPY_LOCATION), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystoneBound.createStack(), Witchery.Items.GENERIC.itemWaystone.createStack(), Witchery.Items.GENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151137_ax) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonecopy");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2818 */     RiteRegistry.addRecipe(35, 21, new RiteFertility(50, 15), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Dye.BONE_MEAL.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack(), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Witchery.Items.GENERIC.itemQuicklime.createStack(), Witchery.Items.GENERIC.itemGypsum.createStack(), Witchery.Items.GENERIC.itemMutandis.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.fertility");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2832 */     RiteRegistry.addRecipe(36, 22, new RiteFertility(50, 15), new SacrificeItem(new ItemStack[] { Dye.BONE_MEAL.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack(), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Witchery.Items.GENERIC.itemQuicklime.createStack(), Witchery.Items.GENERIC.itemGypsum.createStack(), Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.fertilityportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2845 */     RiteRegistry.addRecipe(37, 37, new com.emoniph.witchery.ritual.rites.RiteBlight(80, 15), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack(), Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151060_bw), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151045_i) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.curseblight");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2858 */     RiteRegistry.addRecipe(38, 38, new RiteBlindness(80, 15), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack(), Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Witchery.Items.GENERIC.itemBrewOfInk.createStack(), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151045_i) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.curseblindness");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2872 */     RiteRegistry.addRecipe(39, 39, new RiteHellOnEarth(20, 15, 200.0F), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), Witchery.Items.GENERIC.itemDemonHeart.createStack(), Witchery.Items.GENERIC.itemWaystone.createStack(), new ItemStack(Items.field_151156_bN) }), new SacrificeLiving(EntityVillager.class), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(5000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_OVERWORLD, RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(0, 0, 16), new Circle(0, 28, 0), new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.hellonearth");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2888 */     RiteRegistry.addRecipe(40, 29, new RiteSummonCreature(EntityWitch.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemDiamondVapour.createStack(), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(Items.field_151064_bs), new ItemStack(Witchery.Items.ARTHANA), new ItemStack(Items.field_151071_bq) }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.summonwitch");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2901 */     RiteRegistry.addRecipe(41, 1, new RiteSummonItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack(), RiteSummonItem.Binding.LOCATION), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystone.createStack(), Witchery.Items.GENERIC.itemEnderDew.createStack(), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystoneportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2911 */     RiteRegistry.addRecipe(42, 2, new RiteSummonItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack(2), RiteSummonItem.Binding.COPY_LOCATION), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystoneBound.createStack(), Witchery.Items.GENERIC.itemWaystone.createStack(), Witchery.Items.GENERIC.itemEnderDew.createStack(), Witchery.Items.GENERIC.itemQuicklime.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonecopyportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2922 */     RiteRegistry.addRecipe(43, 22, new RiteNaturesPower(14, 8, 150, 2), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), new ItemStack(Witchery.Blocks.SAPLING, 1, 0), new ItemStack(Witchery.Blocks.SAPLING, 1, 1), new ItemStack(Witchery.Blocks.SAPLING, 1, 2), new ItemStack(Blocks.field_150345_g, 1, 0), new ItemStack(Blocks.field_150345_g, 1, 1), new ItemStack(Blocks.field_150345_g, 1, 2), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.naturespower");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2936 */     RiteRegistry.addRecipe(44, 36, new RitePriorIncarnation(5, 16), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemNecroStone.createStack(), Witchery.Items.GENERIC.itemDogTongue.createStack(), new ItemStack(Items.field_151103_aS), Witchery.Items.GENERIC.itemSpectralDust.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.priorincarnation");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2947 */     RiteRegistry.addRecipe(45, 0, new RiteBindCircleToTalisman(), new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.CIRCLE_TALISMAN), new ItemStack(Items.field_151114_aO), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[0]).setUnlocalizedName("witchery.rite.bindcircleportable");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2955 */     RiteRegistry.addRecipe(46, 20, new RiteRaiseColumn(6, 8), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151137_ax) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.raiseearth");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2964 */     RiteRegistry.addRecipe(47, 20, new RiteRaiseColumn(9, 8), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151114_aO) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(40, 0, 0) }).setUnlocalizedName("witchery.rite.raiseearth");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2973 */     RiteRegistry.addRecipe(48, 48, new RiteCurseCreature(true, "witcheryCursed", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151016_H), Witchery.Items.GENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.cursecreature1");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2986 */     RiteRegistry.addRecipe(49, 49, new RiteCurseCreature(false, "witcheryCursed", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151016_H), Witchery.Items.GENERIC.itemBrewOfLove.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.removecurse1");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2999 */     RiteRegistry.addRecipe(50, 35, new RiteSummonItem(new ItemStack(Witchery.Items.MYSTIC_BRANCH), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBranchEnt.createStack(), Witchery.Items.GENERIC.itemMysticUnguent.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusiontree");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3010 */     RiteRegistry.addRecipe(51, 20, new RiteCookItem(5.0F, 0.08D), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151072_bj), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Items.field_151044_h) }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.cookfood");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3022 */     RiteRegistry.addRecipe(52, 48, new RiteCurseCreature(true, "witcheryInsanity", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151102_aT), Witchery.Items.GENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.curseinsanity1");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3035 */     RiteRegistry.addRecipe(53, 49, new RiteCurseCreature(false, "witcheryInsanity", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151102_aT), Witchery.Items.GENERIC.itemBrewOfLove.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.removeinsanity1");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3048 */     RiteRegistry.addRecipe(54, 1, new RiteBindFamiliar(7), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack(), new ItemStack(Items.field_151045_i), Witchery.Items.GENERIC.itemInfernalBlood.createStack() }), new SacrificePower(8000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfamiliar");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3061 */     RiteRegistry.addRecipe(55, 30, new RiteCallFamiliar(7), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.callfamiliar");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3072 */     RiteRegistry.addRecipe(56, 50, new RiteCursePoppets(1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Witchery.Items.POPPET.antiVoodooPoppet.createStack(), new ItemStack(Items.field_151065_br), Witchery.Items.GENERIC.itemSpectralDust.createStack() }), new SacrificePower(7000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.corruptvoodooprotection");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3085 */     RiteRegistry.addRecipe(57, 35, new RiteSummonItem(new ItemStack(Witchery.Blocks.CRYSTAL_BALL), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemQuartzSphere.createStack(), new ItemStack(Items.field_151043_k), Witchery.Items.GENERIC.itemHappenstanceOil.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionfuture");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3096 */     RiteRegistry.addRecipe(58, 20, new RiteCookItem(5.0F, 0.16D), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack(), new ItemStack(Items.field_151072_bj), Witchery.Items.GENERIC.itemAshWood.createStack(), new ItemStack(Items.field_151065_br) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.cookfood");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3107 */     RiteRegistry.addRecipe(59, 48, new RiteCurseCreature(true, "witcherySinking", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Dye.INK_SAC.createStack(), new ItemStack(Items.field_151075_bm), Witchery.Items.GENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.cursesinking1");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3120 */     RiteRegistry.addRecipe(60, 49, new RiteCurseCreature(false, "witcherySinking", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), Dye.BONE_MEAL.createStack(), new ItemStack(Items.field_151075_bm), Witchery.Items.GENERIC.itemBrewOfTheDepths.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.removesinking1");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3133 */     RiteRegistry.addRecipe(61, 35, new RiteSummonItem(Witchery.Items.GENERIC.itemSeerStone.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemQuartzSphere.createStack(), new ItemStack(Blocks.field_150343_Z), Witchery.Items.GENERIC.itemHappenstanceOil.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.infusionseerstone");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3144 */     RiteRegistry.addRecipe(62, 48, new RiteCurseCreature(true, "witcheryOverheating", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Witchery.Items.GENERIC.itemInfernalBlood.createStack(), new ItemStack(Items.field_151072_bj), Witchery.Items.GENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.curseoverheating");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3157 */     RiteRegistry.addRecipe(63, 49, new RiteCurseCreature(false, "witcheryOverheating", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), Witchery.Items.GENERIC.itemIcyNeedle.createStack(), new ItemStack(Items.field_151072_bj), Witchery.Items.GENERIC.itemBrewOfTheDepths.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.cureoverheating");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3170 */     RiteRegistry.addRecipe(64, 22, new RiteClimateChange(16), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151070_bp), Witchery.Items.GENERIC.itemToeOfFrog.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack(), Witchery.Items.GENERIC.itemDogTongue.createStack(), Witchery.Items.GENERIC.itemOwletsWing.createStack(), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(40, 0, 0) }).setUnlocalizedName("witchery.rite.climatechange");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3183 */     RiteRegistry.addRecipe(65, 12, new RiteSphereEffect(8, Witchery.Blocks.PERPETUAL_ICE), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151048_u), Witchery.Items.GENERIC.itemFrozenHeart.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.iceshell");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3192 */     RiteRegistry.addRecipe(66, 38, new RiteRainOfToads(5, 16, 10), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack(), Witchery.Items.GENERIC.itemRedstoneSoup.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack(), Witchery.Items.GENERIC.itemToeOfFrog.createStack(), new ItemStack(Items.field_151131_as), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.rainoffrogs");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3205 */     RiteRegistry.addRecipe(67, 4, new RiteGlyphicTransformation(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemGypsum.createStack(), new ItemStack(Witchery.Items.ARTHANA) }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[0]).setUnlocalizedName("witchery.rite.glyphictransform");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3214 */     RiteRegistry.addRecipe(68, 7, new RiteCallCreatures(64.0F, new Class[] { EntityPig.class, EntityChicken.class, EntityCow.class, EntitySheep.class, EntityMooshroom.class, EntityWolf.class, EntityOcelot.class }), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151117_aB), new ItemStack(Blocks.field_150407_cf), new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151082_bd), new ItemStack(Items.field_151115_aP), new ItemStack(Blocks.field_150337_Q), new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151014_N) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 40, 0) }).setUnlocalizedName("witchery.rite.callbeasts");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3231 */     RiteRegistry.addRecipe(69, 7, new RiteSetNBT(5, "WITCManifestDuration", 150, 25), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemSpectralDust.createStack(), Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), Witchery.Items.GENERIC.itemNecroStone.createStack(), new ItemStack(Items.field_151005_D), new ItemStack(Witchery.Items.ARTHANA), new ItemStack(Items.field_151016_H) }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0) }).setUnlocalizedName("witchery.rite.manifest");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3245 */     RiteRegistry.addRecipe(70, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 0), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3258 */     RiteRegistry.addRecipe(71, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 1), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3271 */     RiteRegistry.addRecipe(72, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 2), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 2), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3284 */     RiteRegistry.addRecipe(73, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 3), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 3), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3297 */     RiteRegistry.addRecipe(74, 22, new RiteForestation(20, 8, 60, Witchery.Blocks.SAPLING, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Blocks.SAPLING, 1, 0), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3310 */     RiteRegistry.addRecipe(75, 22, new RiteForestation(20, 8, 60, Witchery.Blocks.SAPLING, 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Blocks.SAPLING, 1, 1), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3323 */     RiteRegistry.addRecipe(76, 22, new RiteForestation(20, 8, 60, Witchery.Blocks.SAPLING, 2), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Blocks.SAPLING, 1, 2), new ItemStack(Witchery.Blocks.WICKER_BUNDLE), Witchery.Items.GENERIC.itemBrewOfSprouting.createStack(), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3336 */     RiteRegistry.addRecipe(77, 13, new RiteRaiseVolcano(8, 8), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150347_e), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151010_B) }), new SacrificeOptionalItem(Witchery.Items.GENERIC.itemWaystoneBound.createStack()), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.volcano");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3348 */     RiteRegistry.addRecipe(78, 48, new RiteCurseCreature(true, "witcheryWakingNightmare", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), new ItemStack(Items.field_151045_i), Witchery.Items.GENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.cursenightmare");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3362 */     RiteRegistry.addRecipe(79, 49, new RiteCurseCreature(false, "witcheryWakingNightmare", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Items.field_151150_bK), Witchery.Items.GENERIC.itemTormentedTwine.createStack(), Witchery.Items.GENERIC.itemBrewOfLove.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.curenightmare");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3375 */     RiteRegistry.addRecipe(80, 35, new RiteSummonItem(Witchery.Items.GENERIC.itemBrewOfSoaring.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemInfusionBase.createStack(), Witchery.Items.GENERIC.itemBroom.createStack(), new ItemStack(Items.field_151008_G), new ItemStack(Witchery.Items.ARTHANA) }), new SacrificeLiving(EntityOwl.class), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusebrewsoaring");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3390 */     RiteRegistry.addRecipe(81, 35, new RiteSummonItem(Witchery.Items.GENERIC.itemBrewGrave.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemInfusionBase.createStack(), new ItemStack(Items.field_151103_aS), Witchery.Items.GENERIC.itemWeb.createStack(), Witchery.Items.GENERIC.itemNecroStone.createStack() }), new SacrificeLiving(EntityZombie.class), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusebrewgrave");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3405 */     RiteRegistry.addRecipe(82, 36, new RiteSummonItem(new ItemStack(Witchery.Items.SPECTRAL_STONE), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemNecroStone.createStack(), Witchery.Items.GENERIC.itemCongealedSpirit.createStack(), Witchery.Items.GENERIC.itemCondensedFear.createStack(), Witchery.Items.GENERIC.itemSpectralDust.createStack(), new ItemStack(Witchery.Items.BOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.spectralstone").setConsumeNecroStone();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3419 */     RiteRegistry.addRecipe(83, 1, new RiteSummonSpectralStone(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.SPECTRAL_STONE), Witchery.Items.GENERIC.itemSpectralDust.createStack(), new ItemStack(Witchery.Items.BOLINE) }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindspectral");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3430 */     RiteRegistry.addRecipe(84, 1, new RiteBindSpiritsToFetish(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Blocks.FETISH_SCARECROW), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Witchery.Items.GENERIC.itemNecroStone.createStack(), new ItemStack(Witchery.Items.BOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfetish");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3442 */     RiteRegistry.addRecipe(85, 1, new RiteBindSpiritsToFetish(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Blocks.FETISH_TREANT_IDOL), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Witchery.Items.GENERIC.itemNecroStone.createStack(), new ItemStack(Witchery.Items.BOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfetish").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3454 */     RiteRegistry.addRecipe(86, 1, new RiteBindSpiritsToFetish(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Blocks.FETISH_WITCHS_LADDER), Witchery.Items.GENERIC.itemAttunedStone.createStack(), Witchery.Items.GENERIC.itemNecroStone.createStack(), new ItemStack(Witchery.Items.BOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfetish").setShowInBook(false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3466 */     RiteRegistry.addRecipe(87, 26, new RiteSummonCreature(EntityImp.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemRefinedEvil.createStack(), Witchery.Items.GENERIC.itemInfernalBlood.createStack(), new ItemStack(Items.field_151079_bi), Witchery.Items.GENERIC.itemAttunedStone.createStack() }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.summonimp");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3478 */     RiteRegistry.addRecipe(88, 1, new RiteSummonItem(Witchery.Items.GENERIC.itemWaystonePlayerBound.createStack(), RiteSummonItem.Binding.ENTITY), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystone.createStack(), Witchery.Items.GENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151123_aH), new ItemStack(Items.field_151126_ay) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonetoplayer");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3490 */     RiteRegistry.addRecipe(89, 1, new RiteSummonItem(Witchery.Items.GENERIC.itemWaystonePlayerBound.createStack(), RiteSummonItem.Binding.ENTITY), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystone.createStack(), Witchery.Items.GENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151123_aH), Witchery.Items.GENERIC.itemIcyNeedle.createStack(), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonetoplayer");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3501 */     RiteRegistry.addRecipe(90, 1, new RiteSummonItem(new ItemStack(Witchery.Blocks.STATUE_OF_WORSHIP), RiteSummonItem.Binding.PLAYER), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Blocks.STATUE_OF_WORSHIP), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Blocks.field_150328_O), new ItemStack(Blocks.field_150327_N) }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindstatuetoplayer");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3513 */     RiteRegistry.addRecipe(91, 5, new RiteTeleportToWaystone(3), new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemWaystonePlayerBound.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0) }).setUnlocalizedName("witchery.rite.teleporttowaystone");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3520 */     RiteRegistry.addRecipe(92, 48, new RiteCurseOfTheWolf(true), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(Witchery.Blocks.WOLFHEAD, 1, 1), Witchery.Items.GENERIC.itemWolfsbane.createStack(), new ItemStack(Items.field_151045_i), Witchery.Items.GENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.wolfcurse.book");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3534 */     RiteRegistry.addRecipe(93, 49, new RiteCurseOfTheWolf(false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Witchery.Items.SILVER_SWORD), Witchery.Items.GENERIC.itemWolfsbane.createStack(), new ItemStack(Items.field_151045_i), Witchery.Items.GENERIC.itemBrewOfLove.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.wolfcure.book");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3548 */     RiteRegistry.addRecipe(94, 49, new com.emoniph.witchery.ritual.rites.RiteRemoveVampirism(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Witchery.Items.SILVER_SWORD), new ItemStack(Witchery.Items.SEEDS_GARLIC), new ItemStack(Items.field_151045_i), Witchery.Items.GENERIC.itemBrewOfLove.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.vampirecure.book");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3562 */     RiteRegistry.addRecipe(95, 35, new RiteSummonItem(new ItemStack(Witchery.Items.MIRROR), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Witchery.Items.GENERIC.itemBrewOfHollowTears.createStack(), new ItemStack(Items.field_151043_k), new ItemStack(Blocks.field_150410_aZ) }), new SacrificePower(2000.0F, 20), new SacrificeLiving(EntityDemon.class) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionmirror");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3574 */     RiteRegistry.addRecipe(96, 28, new RiteSummonCreature(EntityReflection.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Witchery.Items.MIRROR), Witchery.Items.GENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151065_br), Witchery.Items.GENERIC.itemQuartzSphere.createStack() }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summonreflection");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3592 */     double DEFAULT_FORCE_CHANCE = 0.05D;
/*      */     
/* 3594 */     PredictionManager.instance().addPrediction(new PredictionFight(1, 13, 0.05D, "witchery.prediction.zombie", EntityZombie.class, false));
/* 3595 */     PredictionManager.instance().addPrediction(new PredictionArrow(2, 13, 0.05D, "witchery.prediction.arrowhit"));
/* 3596 */     PredictionManager.instance().addPrediction(new PredictionFight(3, 3, 0.05D, "witchery.prediction.ent", EntityEnt.class, false));
/* 3597 */     PredictionManager.instance().addPrediction(new PredictionFall(4, 13, 0.05D, "witchery.prediction.fall"));
/* 3598 */     PredictionManager.instance().addPrediction(new PredictionMultiMine(5, 8, 0.05D, "witchery.prediction.iron", 1212, 0.01D, Blocks.field_150366_p, new ItemStack(Blocks.field_150366_p), 8, 20));
/* 3599 */     PredictionManager.instance().addPrediction(new PredictionMultiMine(6, 3, 0.05D, "witchery.prediction.diamond", 1208, 0.01D, Blocks.field_150348_b, new ItemStack(Items.field_151045_i), 1, 1));
/* 3600 */     PredictionManager.instance().addPrediction(new PredictionMultiMine(7, 3, 0.05D, "witchery.prediction.emerald", 1208, 0.01D, Blocks.field_150348_b, new ItemStack(Items.field_151166_bC), 1, 1));
/* 3601 */     PredictionManager.instance().addPrediction(new PredictionBuriedTreasure(8, 2, 0.05D, "witchery.prediction.treasure", 1210, 0.01D, "mineshaftCorridor"));
/* 3602 */     PredictionManager.instance().addPrediction(new PredictionFallInLove(9, 2, 0.05D, "witchery.prediction.love", 1210, 0.01D));
/* 3603 */     PredictionManager.instance().addPrediction(new PredictionFight(10, 2, 0.05D, "witchery.prediction.bababad", EntityBabaYaga.class, false));
/* 3604 */     PredictionManager.instance().addPrediction(new PredictionFight(11, 2, 0.05D, "witchery.prediction.babagood", EntityBabaYaga.class, true));
/* 3605 */     PredictionManager.instance().addPrediction(new PredictionFight(12, 3, 0.05D, "witchery.prediction.friend", EntityWolf.class, true));
/* 3606 */     PredictionManager.instance().addPrediction(new PredictionRescue(13, 13, 0.05D, "witchery.prediction.rescued", 1208, 0.01D, EntityOwl.class));
/* 3607 */     PredictionManager.instance().addPrediction(new PredictionRescue(14, 13, 0.05D, "witchery.prediction.rescued", 1208, 0.01D, EntityWolf.class));
/* 3608 */     PredictionManager.instance().addPrediction(new PredictionWet(15, 13, 0.05D, "witchery.prediction.wet"));
/* 3609 */     PredictionManager.instance().addPrediction(new PredictionNetherTrip(16, 3, 0.05D, "witchery.prediction.tothenether"));
/* 3610 */     PredictionManager.instance().addPrediction(new PredictionMultiMine(17, 13, 0.05D, "witchery.prediction.coal", 1208, 0.01D, Blocks.field_150365_q, new ItemStack(Items.field_151044_h), 10, 20));
/*      */   }
/*      */   
/*      */   public void init() {
/* 3614 */     ItemStack dust = Witchery.Items.GENERIC.itemSilverDust.createStack();
/*      */     
/* 3616 */     List<ItemStack> silverDust = OreDictionary.getOres("dustSilver");
/* 3617 */     if ((silverDust != null) && (!silverDust.isEmpty())) {
/* 3618 */       GameRegistry.addShapelessRecipe(((ItemStack)silverDust.get(0)).func_77946_l(), new Object[] { dust, dust, dust, dust, dust, dust, dust, dust, dust });
/*      */     }
/*      */     
/*      */ 
/* 3622 */     List<ItemStack> silverIngots = OreDictionary.getOres("ingotSilver");
/* 3623 */     if ((silverIngots != null) && (!silverIngots.isEmpty()))
/*      */     {
/* 3625 */       GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Items.SILVER_SWORD), new Object[] { "s", "s", "b", Character.valueOf('s'), "ingotSilver", Character.valueOf('b'), new ItemStack(Items.field_151010_B) }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3633 */       GameRegistry.addRecipe(new ShapedOreRecipe(Witchery.Items.GENERIC.itemBoltSilver.createStack(6), new Object[] { " s ", "bbb", "bbb", Character.valueOf('s'), "ingotSilver", Character.valueOf('b'), Witchery.Items.GENERIC.itemBoltStake.createStack() }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3641 */       GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Witchery.Blocks.WOLFTRAP), new Object[] { "sns", "w#w", "sns", Character.valueOf('#'), new ItemStack(Witchery.Blocks.BEARTRAP), Character.valueOf('s'), "ingotSilver", Character.valueOf('n'), Witchery.Items.GENERIC.itemNullCatalyst.createStack(), Character.valueOf('w'), Witchery.Items.GENERIC.itemWolfsbane.createStack() }));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3651 */       Item[][] hunterItems = { { Witchery.Items.HUNTER_BOOTS, Witchery.Items.HUNTER_BOOTS_SILVERED }, { Witchery.Items.HUNTER_LEGS, Witchery.Items.HUNTER_LEGS_SILVERED }, { Witchery.Items.HUNTER_COAT, Witchery.Items.HUNTER_COAT_SILVERED }, { Witchery.Items.HUNTER_HAT, Witchery.Items.HUNTER_HAT_SILVERED } };
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3656 */       for (Item[] hunterItem : hunterItems) {
/* 3657 */         ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(hunterItem[1]), new Object[] { "dwd", "w#w", "dsd", Character.valueOf('#'), new ItemStack(hunterItem[0]), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), Witchery.Items.GENERIC.itemWolfsbane.createStack(), Character.valueOf('d'), "ingotSilver" })
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           public ItemStack func_77572_b(InventoryCrafting inv)
/*      */           {
/*      */ 
/*      */ 
/* 3666 */             ItemStack result = func_77571_b().func_77946_l();
/* 3667 */             for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 3668 */               ItemStack material = inv.func_70301_a(i);
/* 3669 */               if ((material != null) && (material.func_77942_o())) {
/* 3670 */                 result.func_77982_d((NBTTagCompound)material.field_77990_d.func_74737_b());
/*      */               }
/*      */             }
/* 3673 */             return result;
/*      */           }
/* 3675 */         };
/* 3676 */         GameRegistry.addRecipe(recipe);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void postInit() {
/* 3682 */     if (Config.instance().smeltAllSaplingsToWoodAsh) {
/* 3683 */       ArrayList<ItemStack> saplingTypes = OreDictionary.getOres("treeSapling");
/* 3684 */       for (ItemStack stack : saplingTypes) {
/* 3685 */         GameRegistry.addSmelting(stack, Witchery.Items.GENERIC.itemAshWood.createStack(), 0.0F);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void addPlantMineRecipe(int damageValue, ItemStack plant, ItemStack brew) {
/* 3691 */     GameRegistry.addRecipe(new ItemStack(Witchery.Blocks.TRAPPED_PLANT, 4, damageValue), new Object[] { "ccc", "bab", Character.valueOf('a'), plant, Character.valueOf('b'), new ItemStack(Blocks.field_150456_au), Character.valueOf('c'), brew });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static ShapedRecipes getShapedRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj)
/*      */   {
/* 3702 */     String s = "";
/* 3703 */     int i = 0;
/* 3704 */     int j = 0;
/* 3705 */     int k = 0;
/*      */     
/* 3707 */     if ((par2ArrayOfObj[i] instanceof String[]))
/*      */     {
/* 3709 */       String[] astring = (String[])(String[])par2ArrayOfObj[(i++)];
/*      */       
/* 3711 */       for (int l = 0; l < astring.length; l++)
/*      */       {
/* 3713 */         String s1 = astring[l];
/* 3714 */         k++;
/* 3715 */         j = s1.length();
/* 3716 */         s = s + s1;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 3721 */       while ((par2ArrayOfObj[i] instanceof String))
/*      */       {
/* 3723 */         String s2 = (String)par2ArrayOfObj[(i++)];
/* 3724 */         k++;
/* 3725 */         j = s2.length();
/* 3726 */         s = s + s2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3732 */     for (HashMap hashmap = new HashMap(); i < par2ArrayOfObj.length; i += 2)
/*      */     {
/* 3734 */       Character character = (Character)par2ArrayOfObj[i];
/* 3735 */       ItemStack itemstack1 = null;
/*      */       
/* 3737 */       if ((par2ArrayOfObj[(i + 1)] instanceof Item))
/*      */       {
/* 3739 */         itemstack1 = new ItemStack((Item)par2ArrayOfObj[(i + 1)]);
/*      */       }
/* 3741 */       else if ((par2ArrayOfObj[(i + 1)] instanceof Block))
/*      */       {
/* 3743 */         itemstack1 = new ItemStack((Block)par2ArrayOfObj[(i + 1)], 1, 32767);
/*      */       }
/* 3745 */       else if ((par2ArrayOfObj[(i + 1)] instanceof ItemStack))
/*      */       {
/* 3747 */         itemstack1 = (ItemStack)par2ArrayOfObj[(i + 1)];
/*      */       }
/*      */       
/* 3750 */       hashmap.put(character, itemstack1);
/*      */     }
/*      */     
/* 3753 */     ItemStack[] aitemstack = new ItemStack[j * k];
/*      */     
/* 3755 */     for (int i1 = 0; i1 < j * k; i1++)
/*      */     {
/* 3757 */       char c0 = s.charAt(i1);
/*      */       
/* 3759 */       if (hashmap.containsKey(Character.valueOf(c0)))
/*      */       {
/* 3761 */         aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).func_77946_l();
/*      */       }
/*      */       else
/*      */       {
/* 3765 */         aitemstack[i1] = null;
/*      */       }
/*      */     }
/*      */     
/* 3769 */     ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, par1ItemStack);
/*      */     
/* 3771 */     return shapedrecipes;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/WitcheryRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */