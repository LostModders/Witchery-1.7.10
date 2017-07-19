/*      */ package com.emoniph.witchery.brewing;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.blocks.BlockCircleGlyph;
/*      */ import com.emoniph.witchery.blocks.BlockWitchCrop;
/*      */ import com.emoniph.witchery.brewing.action.BrewAction;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionBlockCircle;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionDispersal;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionImpactModifier;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionList;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionModifier;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionRitual;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionRitualEntityTarget;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe.Recipe;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionRitualSummonMob;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionRitualSummonMob.Recipe;
/*      */ import com.emoniph.witchery.brewing.action.BrewActionSetColor;
/*      */ import com.emoniph.witchery.brewing.action.BrewCurseEffect;
/*      */ import com.emoniph.witchery.brewing.action.BrewPotionEffect;
/*      */ import com.emoniph.witchery.brewing.action.effect.BrewActionBlight;
/*      */ import com.emoniph.witchery.brewing.action.effect.BrewActionLilify;
/*      */ import com.emoniph.witchery.brewing.action.effect.BrewActionRaiseLand;
/*      */ import com.emoniph.witchery.brewing.action.effect.BrewActionRaising;
/*      */ import com.emoniph.witchery.brewing.action.effect.BrewActionSprouting;
/*      */ import com.emoniph.witchery.brewing.action.effect.BrewActionTranspose;
/*      */ import com.emoniph.witchery.brewing.potions.PotionBase;
/*      */ import com.emoniph.witchery.brewing.potions.PotionSnowTrail;
/*      */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*      */ import com.emoniph.witchery.common.ExtendedPlayer;
/*      */ import com.emoniph.witchery.common.Shapeshift;
/*      */ import com.emoniph.witchery.entity.EntityDemon;
/*      */ import com.emoniph.witchery.entity.EntityLeonard;
/*      */ import com.emoniph.witchery.entity.EntityOwl;
/*      */ import com.emoniph.witchery.entity.EntitySummonedUndead;
/*      */ import com.emoniph.witchery.entity.EntityToad;
/*      */ import com.emoniph.witchery.familiar.Familiar;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*      */ import com.emoniph.witchery.integration.ModHookManager;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.network.PacketPushTarget;
/*      */ import com.emoniph.witchery.util.BlockActionCircle;
/*      */ import com.emoniph.witchery.util.BlockActionReplaceSphere;
/*      */ import com.emoniph.witchery.util.BlockActionSphere;
/*      */ import com.emoniph.witchery.util.BlockPosition;
/*      */ import com.emoniph.witchery.util.BlockProtect;
/*      */ import com.emoniph.witchery.util.BlockUtil;
/*      */ import com.emoniph.witchery.util.CircleUtil;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.Count;
/*      */ import com.emoniph.witchery.util.CreatureUtil;
/*      */ import com.emoniph.witchery.util.Dye;
/*      */ import com.emoniph.witchery.util.EntityPosition;
/*      */ import com.emoniph.witchery.util.EntityUtil;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.SpawnUtil;
/*      */ import com.emoniph.witchery.util.TimeUtil;
/*      */ import com.emoniph.witchery.util.TransformCreature;
/*      */ import com.emoniph.witchery.worldgen.WorldGenLargeWitchTree;
/*      */ import com.emoniph.witchery.worldgen.WorldGenWitchTree;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.Block.SoundType;
/*      */ import net.minecraft.block.BlockDoor;
/*      */ import net.minecraft.block.BlockFlower;
/*      */ import net.minecraft.block.IGrowable;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityBlaze;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.passive.EntityBat;
/*      */ import net.minecraft.entity.passive.EntityOcelot;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.ItemDye;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.pathfinding.PathEntity;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*      */ import net.minecraft.world.gen.feature.WorldGenCanopyTree;
/*      */ import net.minecraft.world.gen.feature.WorldGenForest;
/*      */ import net.minecraft.world.gen.feature.WorldGenSavannaTree;
/*      */ import net.minecraft.world.gen.feature.WorldGenTrees;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.common.IPlantable;
/*      */ import net.minecraftforge.common.util.FakePlayer;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ import net.minecraftforge.fluids.IFluidBlock;
/*      */ 
/*      */ public class WitcheryBrewRegistry
/*      */ {
/*  120 */   public static final WitcheryBrewRegistry INSTANCE = new WitcheryBrewRegistry();
/*      */   public static final int MAX_STRENGTH_BOOSTS = 7;
/*      */   public static final int MAX_DURATION_BOOSTS = 7;
/*      */   
/*      */   private WitcheryBrewRegistry()
/*      */   {
/*  126 */     BrewItemKey triggeredKey = new BrewItemKey(Items.field_151144_bL, 2);
/*  127 */     register(new BrewActionDispersal(new BrewItemKey(Items.field_151016_H), new AltarPower(0), new DispersalInstant()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(Witchery.Items.GENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  133 */     register(new BrewActionDispersal(Witchery.Items.GENERIC.itemArtichoke.getBrewItemKey(), new AltarPower(0), new DispersalInstant()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(Witchery.Items.GENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  139 */     register(new BrewActionDispersal(Witchery.Items.GENERIC.itemBatWool.getBrewItemKey(), new AltarPower(0), new DispersalGas()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(Witchery.Items.GENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  145 */     register(new BrewActionDispersal(Witchery.Items.GENERIC.itemWormwood.getBrewItemKey(), new AltarPower(0), new DispersalLiquid()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(Witchery.Items.GENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  151 */     register(new BrewActionDispersal(triggeredKey, new AltarPower(0), new DispersalTriggered()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(Witchery.Items.GENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(Witchery.Items.GENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  159 */     register(new BrewActionImpactModifier(Witchery.Items.GENERIC.itemAshWood.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 1, 0), new AltarPower(50))
/*      */     {
/*      */       protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
/*      */       {
/*  163 */         if (modifiers.extent < 1) {
/*  164 */           modifiers.extent += 1;
/*      */         }
/*      */         
/*      */       }
/*  168 */     });
/*  169 */     register(new BrewActionImpactModifier(Dye.COCOA_BEANS.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 1, 0), new AltarPower(100))
/*      */     {
/*      */       protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
/*      */       {
/*  173 */         if (modifiers.extent < 2) {
/*  174 */           modifiers.extent += 1;
/*      */         }
/*      */         
/*      */       }
/*  178 */     });
/*  179 */     register(new BrewActionImpactModifier(new BrewItemKey(Witchery.Blocks.WISPY_COTTON), new BrewNamePartModifier(0, 0, false, 1, 0), new AltarPower(150))
/*      */     {
/*      */       protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
/*      */       {
/*  183 */         if (modifiers.extent < 3) {
/*  184 */           modifiers.extent += 1;
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  189 */     });
/*  190 */     register(new BrewActionImpactModifier(Witchery.Items.GENERIC.itemBelladonnaFlower.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 0, 1), new AltarPower(50))
/*      */     {
/*      */       protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
/*      */       {
/*  194 */         if (modifiers.lifetime < 1) {
/*  195 */           modifiers.lifetime += 1;
/*      */         }
/*      */         
/*      */       }
/*  199 */     });
/*  200 */     register(new BrewActionImpactModifier(Dye.LAPIS_LAZULI.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 0, 1), new AltarPower(100))
/*      */     {
/*      */       protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
/*      */       {
/*  204 */         if (modifiers.lifetime < 2) {
/*  205 */           modifiers.lifetime += 1;
/*      */         }
/*      */         
/*      */       }
/*  209 */     });
/*  210 */     register(new BrewActionImpactModifier(new BrewItemKey(Blocks.field_150377_bs), new BrewNamePartModifier(0, 0, false, 0, 1), new AltarPower(150))
/*      */     {
/*      */       protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
/*      */       {
/*  214 */         if (modifiers.lifetime < 3) {
/*  215 */           modifiers.lifetime += 1;
/*      */         }
/*      */       }
/*      */     });
/*      */     
/*      */ 
/*  221 */     for (Dye dye : Dye.values()) {
/*  222 */       register(new BrewActionSetColor(new BrewItemKey(Blocks.field_150325_L, 15 - dye.damageValue), new AltarPower(0), dye.rgb));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  227 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151074_bl), null, new AltarPower(50))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers) {
/*  230 */         modifiers.noParticles = true;
/*      */       }
/*      */       
/*      */ 
/*  234 */     });
/*  235 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemRowanBerries.getBrewItemKey(), null, new AltarPower(50))
/*      */     {
/*      */       public int getDrinkSpeedModifiers()
/*      */       {
/*  239 */         return -8;
/*      */       }
/*      */       
/*  242 */     });
/*  243 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.getBrewItemKey(), null, new AltarPower(0))
/*      */     {
/*      */       public int getDrinkSpeedModifiers()
/*      */       {
/*  247 */         return -4;
/*      */       }
/*      */       
/*  250 */     });
/*  251 */     register(new BrewActionModifier(new BrewItemKey(Witchery.Blocks.SPANISH_MOSS), null, new AltarPower(50))
/*      */     {
/*      */       public int getDrinkSpeedModifiers() {
/*  254 */         return -4;
/*      */       }
/*      */       
/*      */ 
/*  258 */     });
/*  259 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemMandrakeRoot.getBrewItemKey(), null, new AltarPower(0))
/*      */     {
/*      */       public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*      */       {
/*  263 */         totalEffects.increaseAvailableLevelIf(new EffectLevel(1), new EffectLevel(1));
/*  264 */         return true;
/*      */       }
/*      */       
/*  267 */     });
/*  268 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151075_bm), null, new AltarPower(50))
/*      */     {
/*      */       public boolean augmentEffectLevels(EffectLevelCounter totalEffects) {
/*  271 */         totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(2));
/*  272 */         return true;
/*      */       }
/*      */       
/*  275 */     });
/*  276 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemTearOfTheGoddess.getBrewItemKey(), null, new AltarPower(100))
/*      */     {
/*      */       public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*      */       {
/*  280 */         totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(4));
/*  281 */         return true;
/*      */       }
/*      */       
/*  284 */     });
/*  285 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemDiamondVapour.getBrewItemKey(), null, new AltarPower(150))
/*      */     {
/*      */       public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*      */       {
/*  289 */         totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(6));
/*  290 */         return true;
/*      */       }
/*      */       
/*  293 */     });
/*  294 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151045_i), null, new AltarPower(150))
/*      */     {
/*      */       public boolean augmentEffectLevels(EffectLevelCounter totalEffects) {
/*  297 */         totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(8));
/*  298 */         return true; } }).setYieldModifier(new ModifierYield(-2));
/*      */     
/*      */ 
/*      */ 
/*  302 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151156_bN), new BrewNamePartModifier(0, 0, false, 0, 0, true), new AltarPower(150))
/*      */     {
/*      */       public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*      */       {
/*  306 */         totalEffects.increaseAvailableLevelIf(new EffectLevel(4), new EffectLevel(10));
/*  307 */         return true;
/*      */       }
/*      */       
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  312 */         modifiers.powerhCeilingDisabled = true;
/*      */       }
/*      */       
/*  315 */     });
/*  316 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemKobolditePentacle.getBrewItemKey(), null, new AltarPower(1000))
/*      */     {
/*      */       public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
/*      */       {
/*  320 */         totalEffects.increaseAvailableLevelIf(new EffectLevel(6), new EffectLevel(16));
/*  321 */         return true;
/*      */       }
/*      */       
/*      */ 
/*  325 */     });
/*  326 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151114_aO), new BrewNamePartModifier(1, 0, false, 0, 0), new AltarPower(50))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  330 */         if (modifiers.strength < 1) {
/*  331 */           modifiers.increaseStrength(1);
/*      */         }
/*      */         
/*      */       }
/*  335 */     });
/*  336 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151072_bj), new BrewNamePartModifier(1, 0, false, 0, 0), new AltarPower(100))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  340 */         if (modifiers.strength < 2) {
/*  341 */           modifiers.increaseStrength(1);
/*      */         }
/*      */         
/*      */       }
/*  345 */     });
/*  346 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemAttunedStoneCharged.getBrewItemKey(), new BrewNamePartModifier(1, 0, false, 0, 0), new AltarPower(150))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  350 */         if (modifiers.strength < 3) {
/*  351 */           modifiers.increaseStrength(1);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  356 */     });
/*  357 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151137_ax), new BrewNamePartModifier(0, 1, false, 0, 0), new AltarPower(50))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  361 */         if (modifiers.duration < 1) {
/*  362 */           modifiers.increaseDuration(1);
/*      */         }
/*      */         
/*      */       }
/*  366 */     });
/*  367 */     register(new BrewActionModifier(new BrewItemKey(Blocks.field_150343_Z), new BrewNamePartModifier(0, 1, false, 0, 0), new AltarPower(100))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  371 */         if (modifiers.duration < 2) {
/*  372 */           modifiers.increaseDuration(1);
/*      */         }
/*      */         
/*      */       }
/*  376 */     });
/*  377 */     register(new BrewActionModifier(new BrewItemKey(Witchery.Items.SEEDS_MINDRAKE), new BrewNamePartModifier(0, 1, false, 0, 0), new AltarPower(150))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  381 */         if (modifiers.duration < 3) {
/*  382 */           modifiers.increaseDuration(1);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  387 */     });
/*  388 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151071_bq), new BrewNamePartModifier(0, 0, true, 0, 0), new AltarPower(25))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers)
/*      */       {
/*  392 */         modifiers.inverted = true;
/*      */       }
/*      */       
/*      */ 
/*  396 */     });
/*  397 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151130_bT), null, new AltarPower(50))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers) {
/*  400 */         modifiers.disableBlockTarget = true;
/*      */       }
/*      */       
/*      */ 
/*  404 */     });
/*  405 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151118_aC), null, new AltarPower(50))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers) {
/*  408 */         modifiers.disableEntityTarget = true;
/*      */       }
/*      */       
/*      */ 
/*  412 */     });
/*  413 */     register(new BrewActionModifier(new BrewItemKey(Items.field_151115_aP, 2), null, new AltarPower(200))
/*      */     {
/*      */       public void augmentEffectModifiers(ModifiersEffect modifiers) {
/*  416 */         modifiers.strengthCeilingDisabled = true;
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  423 */     });
/*  424 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151126_ay), new BrewNamePart("witchery:brew.snow").setBaseDuration(TimeUtil.minsToTicks(3)), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/*  430 */         PotionSnowTrail.createSnowCovering(world, x, y, z, 2 + 2 * modifiers.getStrength(), modifiers.caster);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*  437 */         BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Witchery.Potions.SNOW_TRAIL, TimeUtil.minsToTicks(3), modifiers.noParticles, modifiers.caster);
/*      */       }
/*      */       
/*      */ 
/*  441 */     });
/*  442 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151115_aP, 0), new BrewNamePart("witchery:brew.swimming"), new AltarPower(0), new Probability(1.0D), Witchery.Potions.SWIMMING, TimeUtil.minsToTicks(3), new EffectLevel(1)));
/*      */     
/*      */ 
/*      */ 
/*  446 */     for (Dye dye : new Dye[] { Dye.ROSE_RED, Dye.CACTUS_GREEN, Dye.PURPLE_DYE, Dye.CYAN_DYE, Dye.LIGHT_GRAY_DYE, Dye.GRAY_DYE, Dye.PINK_DYE, Dye.LIME_DYE, Dye.DANDELION_YELLOW, Dye.LIGHT_BLUE_DYE, Dye.MAGENTA_DYE, Dye.ORANGE_DYE })
/*      */     {
/*      */ 
/*  449 */       register(new BrewCurseEffect(dye.getBrewItemKey(), new BrewNamePart("witchery:potion.colorful." + dye.unlocalizedName), new AltarPower(0), new Probability(1.0D), Witchery.Potions.COLORFUL, TimeUtil.secsToTicks(90), new EffectLevel(1), false)
/*      */       {
/*      */ 
/*      */         public void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */         {
/*  454 */           if (!modifiers.disableEntityTarget) {
/*  455 */             if (!modifiers.protectedFromNegativePotions) {
/*  456 */               targetEntity.func_70690_d(new PotionEffect(this.potion.field_76415_H, this.baseDuration, this.val$dye.ordinal(), true));
/*      */             }
/*      */             
/*  459 */             modifiers.reset();
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*      */     
/*  465 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemEnderDew.getBrewItemKey(), new BrewNamePart("witchery:potion.enderinhibition"), new AltarPower(200), new Probability(1.0D), Witchery.Potions.ENDER_INHIBITION, TimeUtil.secsToTicks(90), new EffectLevel(1)));
/*      */     
/*      */ 
/*      */ 
/*  469 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151015_O), new BrewNamePart("witchery:brew.moonshine"), new AltarPower(0), new Probability(1.0D), Witchery.Potions.FEEL_NO_PAIN, TimeUtil.secsToTicks(90), new EffectLevel(1)));
/*      */     
/*      */ 
/*      */ 
/*  473 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151044_h), new BrewNamePart("witchery:brew.extinguish"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*  478 */         if ((modifiers.getStrength() > 1) || (!world.field_73011_w.field_76575_d)) {
/*  479 */           if ((modifiers.getStrength() > 0) && ((targetEntity instanceof EntityBlaze))) {
/*  480 */             targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), (modifiers.getStrength() + 1) * 4.0F);
/*      */           }
/*      */           
/*      */ 
/*  484 */           if (targetEntity.func_70027_ad()) {
/*  485 */             targetEntity.func_70066_B();
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, final int radius, final ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*  493 */         if ((modifiers.getStrength() > 1) || (!world.field_73011_w.field_76575_d)) {
/*  494 */           new BlockActionCircle()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z) {
/*  497 */               for (int dy = y - radius; dy <= y + radius; dy++) {
/*  498 */                 Block block = world.func_147439_a(x, dy, z);
/*  499 */                 if ((block == Blocks.field_150480_ab) && 
/*  500 */                   (BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */                 {
/*      */ 
/*  503 */                   world.func_147468_f(x, dy, z);
/*  504 */                   SoundEffect.RANDOM_FIZZ.playAt(world, x, dy, z, 1.0F, 2.0F); } } } }.processFilledCircle(world, x, y, z, radius + (modifiers.ritualised ? 5 : 0));
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  513 */     });
/*  514 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150348_b), new BrewNamePart("witchery:brew.dissipate"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*  519 */         if ((targetEntity instanceof EntitySummonedUndead)) {
/*  520 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), (modifiers.getStrength() + 1) * 5.0F);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, final int radius, final ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*  529 */         new BlockActionCircle()
/*      */         {
/*      */           public void onBlock(World world, int x, int y, int z) {
/*  532 */             for (int dy = y - radius; dy <= y + radius; dy++) {
/*  533 */               Block block = world.func_147439_a(x, dy, z);
/*  534 */               if (block == Witchery.Blocks.BREW_GAS) {
/*  535 */                 if ((BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */                 {
/*      */ 
/*  538 */                   world.func_147468_f(x, dy, z);
/*  539 */                   SoundEffect.RANDOM_FIZZ.playAt(world, x, dy, z, 1.0F, 2.0F);
/*      */                 }
/*  541 */               } else if (((block instanceof IFluidBlock)) && 
/*  542 */                 (modifiers.getStrength() >= 1)) {
/*  543 */                 IFluidBlock fluidBlock = (IFluidBlock)block;
/*  544 */                 if ((fluidBlock.getFluid() != null) && (fluidBlock.getFluid().isGaseous()) && 
/*  545 */                   (BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */                 {
/*      */ 
/*  548 */                   world.func_147468_f(x, dy, z);
/*  549 */                   SoundEffect.RANDOM_FIZZ.playAt(world, x, dy, z, 1.0F, 2.0F); } } } } }.processFilledCircle(world, x, y, z, radius);
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  559 */     });
/*  560 */     register(new BrewActionBlockCircle(new BrewItemKey(Blocks.field_150327_N), new BrewNamePart("witchery:brew.flowers"), new AltarPower(200), new EffectLevel(1))
/*      */     {
/*  562 */       private final Block[] BLOCKS = { Blocks.field_150327_N, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O };
/*      */       
/*      */ 
/*      */ 
/*      */       protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
/*      */       {
/*  568 */         for (int dy = y - 1; dy <= y + 1; dy++) {
/*  569 */           if ((BlockUtil.isReplaceableBlock(world, x, dy, z, modifiers.caster)) && (!world.func_147439_a(x, dy, z).func_149688_o().func_76224_d()) && (Blocks.field_150327_N.func_149742_c(world, x, dy, z)))
/*      */           {
/*      */ 
/*  572 */             if (world.field_73012_v.nextInt(8 - modifiers.getStrength()) == 0) {
/*  573 */               int flowerIndex = world.field_73012_v.nextInt(this.BLOCKS.length);
/*  574 */               int flowerMeta = Math.max(flowerIndex - 1, 0);
/*  575 */               world.func_147465_d(x, dy, z, this.BLOCKS[flowerIndex], flowerMeta, 3);
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*      */       }
/*  581 */     });
/*  582 */     register(new BrewActionBlockCircle(Dye.BONE_MEAL.getBrewItemKey(), new BrewNamePart("witchery:brew.fertilization"), new AltarPower(250), new EffectLevel(1))
/*      */     {
/*  584 */       private final ItemStack BONEMEAL = Dye.BONE_MEAL.createStack();
/*      */       
/*      */       protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
/*      */       {
/*  588 */         for (int dy = y + 1; dy >= y - 1; dy--) {
/*  589 */           Block block = world.func_147439_a(x, dy, z);
/*  590 */           if (((block instanceof IGrowable)) || ((block instanceof IPlantable)) || ((block instanceof BlockWitchCrop)))
/*      */           {
/*  592 */             for (int i = 0; i <= modifiers.getStrength(); i++) {
/*  593 */               ItemDye.func_150919_a(this.BONEMEAL, world, x, dy, z);
/*      */             }
/*  595 */             break;
/*      */           }
/*      */           
/*      */         }
/*      */       }
/*  600 */     });
/*  601 */     register(new BrewActionBlockCircle(new BrewItemKey(Items.field_151034_e), new BrewNamePart("witchery:brew.harvesting"), new AltarPower(0), new EffectLevel(1))
/*      */     {
/*      */       protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
/*      */       {
/*  605 */         for (int dy = y - 1; dy <= y + 1; dy++) {
/*  606 */           Block block = world.func_147439_a(x, dy, z);
/*  607 */           if ((block instanceof net.minecraft.block.BlockBush)) {
/*  608 */             int meta = world.func_72805_g(x, dy, z);
/*  609 */             List<ItemStack> drops = new ArrayList();
/*  610 */             drops.addAll(block.getDrops(world, x, dy, z, meta, Math.max(modifiers.getStrength() - 1, 0)));
/*      */             
/*  612 */             world.func_147468_f(x, dy, z);
/*  613 */             counter.increment();
/*  614 */             if (world.field_73012_v.nextInt(counter.get()) == 0) {
/*  615 */               world.func_72926_e(2001, x, dy, z, Block.func_149682_b(block) + (meta << 12));
/*      */             }
/*  617 */             for (ItemStack drop : drops) {
/*  618 */               world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, drop.func_77946_l()));
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*      */       }
/*  624 */     });
/*  625 */     register(new BrewActionBlockCircle(new BrewItemKey(Blocks.field_150346_d), new BrewNamePart("witchery:brew.tilling"), new AltarPower(0), new EffectLevel(1))
/*      */     {
/*      */       protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
/*      */       {
/*  629 */         for (int dy = y - 1; dy <= y + 1; dy++) {
/*  630 */           Block block = world.func_147439_a(x, dy, z);
/*  631 */           if (((block == Blocks.field_150349_c) || (block == Blocks.field_150346_d) || ((block == Blocks.field_150354_m) && (modifiers.getStrength() > 0)) || ((block == Blocks.field_150424_aL) && (modifiers.getStrength() > 1)) || ((block == Blocks.field_150425_aM) && (modifiers.getStrength() > 2))) && (world.func_147437_c(x, dy + 1, z)))
/*      */           {
/*      */ 
/*      */ 
/*  635 */             world.func_147449_b(x, dy, z, Blocks.field_150458_ak);
/*  636 */             counter.increment();
/*  637 */             if (world.field_73012_v.nextInt(counter.get()) == 0) {
/*  638 */               world.func_72908_a(x + 0.5F, dy + 0.5F, z + 0.5F, block.field_149762_H.func_150498_e(), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F);
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*  646 */     });
/*  647 */     register(new com.emoniph.witchery.brewing.action.effect.BrewActionPlanting(new BrewItemKey(Items.field_151014_N), new BrewNamePart("witchery:brew.planting"), new AltarPower(0), new EffectLevel(1)));
/*      */     
/*      */ 
/*  650 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150338_P), new BrewNamePart("witchery:brew.pruning"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int posX, int posY, int posZ, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*  655 */         int BLOCK_RADIUS = radius - 1;
/*  656 */         int BLOCK_RADIUS_SQ = BLOCK_RADIUS * BLOCK_RADIUS;
/*  657 */         int blockX = MathHelper.func_76128_c(posX);
/*  658 */         int blockY = MathHelper.func_76128_c(posY);
/*  659 */         int blockZ = MathHelper.func_76128_c(posZ);
/*  660 */         for (int y = blockY - BLOCK_RADIUS; y <= blockY + BLOCK_RADIUS; y++) {
/*  661 */           for (int x = blockX - BLOCK_RADIUS; x <= blockX + BLOCK_RADIUS; x++) {
/*  662 */             for (int z = blockZ - BLOCK_RADIUS; z <= blockZ + BLOCK_RADIUS; z++) {
/*  663 */               if ((Coord.distanceSq(x, y, z, blockX, blockY, blockZ) <= BLOCK_RADIUS_SQ) && 
/*  664 */                 (BlockProtect.checkModsForBreakOK(world, x, y, z, modifiers.caster))) {
/*  665 */                 Material material = world.func_147439_a(x, y, z).func_149688_o();
/*  666 */                 if ((material != null) && ((material == Material.field_151584_j) || (((material == Material.field_151585_k) || (material == Material.field_151582_l)) && (material.func_76222_j()))))
/*      */                 {
/*      */ 
/*  669 */                   Block blockID = world.func_147439_a(x, y, z);
/*  670 */                   if ((!(blockID instanceof com.emoniph.witchery.blocks.BlockCircle)) && (!(blockID instanceof BlockCircleGlyph)))
/*      */                   {
/*  672 */                     blockID.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), modifiers.getStrength());
/*      */                     
/*  674 */                     world.func_147468_f(x, y, z);
/*      */                   }
/*      */                   
/*      */                 }
/*      */                 
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  684 */     });
/*  685 */     register(new com.emoniph.witchery.brewing.action.effect.BrewActionFelling(Items.field_151007_F, 0, new AltarPower(0), new EffectLevel(1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  696 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151145_ak), new BrewNamePart("witchery:brew.pulverisation"), new AltarPower(250), new Probability(1.0D), new EffectLevel(1))
/*      */     {
/*      */ 
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/*  703 */         if (!world.field_72995_K) {
/*  704 */           new BlockActionSphere()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z) {
/*  707 */               Block block = world.func_147439_a(x, y, z);
/*  708 */               if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */               {
/*      */ 
/*  711 */                 if (block == Blocks.field_150348_b) {
/*  712 */                   world.func_147449_b(x, y, z, Blocks.field_150347_e);
/*  713 */                 } else if (block == Blocks.field_150347_e) {
/*  714 */                   world.func_147449_b(x, y, z, Blocks.field_150351_n);
/*  715 */                 } else if ((block == Blocks.field_150351_n) || (block == Blocks.field_150322_A)) {
/*  716 */                   world.func_147449_b(x, y, z, Blocks.field_150354_m);
/*  717 */                 } else if (block == Blocks.field_150354_m) {
/*  718 */                   world.func_147468_f(x, y, z);
/*  719 */                   EntityUtil.spawnEntityInWorld(world, new EntityItem(world, x, y, z, new ItemStack(Blocks.field_150354_m)));
/*      */                 }
/*      */                 else {
/*  722 */                   return;
/*      */                 }
/*  724 */                 ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, y + 1, z, 0.25D, 0.25D, 16); } } }.drawFilledSphere(world, x, y, z, Math.max(radius - 1, 1));
/*      */           
/*      */ 
/*      */ 
/*  728 */           SoundEffect.MOB_GHAST_FIREBALL.playAt(world, x, y, z, 0.5F, 2.0F);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  733 */     });
/*  734 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150354_m), new BrewNamePart("witchery:brew.tidehold"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/*  740 */         if (!world.field_72995_K) {
/*  741 */           new BlockActionSphere()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z) {
/*  744 */               Block block = world.func_147439_a(x, y, z);
/*  745 */               if ((block == Blocks.field_150355_j) || (block == Blocks.field_150358_i))
/*  746 */                 Witchery.Blocks.SLURP.replaceBlockAt(world, x, y, z, modifiers.getModifiedDuration(TimeUtil.secsToTicks(10))); } }.drawFilledSphere(world, x, y, z, Math.max(radius + 2, 1));
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*  754 */     });
/*  755 */     register(new BrewActionLilify(new BrewItemKey(Blocks.field_150392_bi), new BrewNamePart("witchery:brew.lilify"), new AltarPower(200), new EffectLevel(1)));
/*      */     
/*      */ 
/*  758 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemWolfsbane.getBrewItemKey(), new BrewNamePart("witchery:potion.wolfsbane"), new AltarPower(0), new Probability(1.0D), Witchery.Potions.WOLFSBANE, TimeUtil.secsToTicks(60), new EffectLevel(1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  764 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemPurifiedMilk.getBrewItemKey(), new BrewNamePart("witchery:brew.removedebuffs"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*  769 */         List<Potion> effectsToRemove = new ArrayList();
/*  770 */         Collection<PotionEffect> effects = targetEntity.func_70651_bq();
/*  771 */         for (PotionEffect effect : effects) {
/*  772 */           Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/*  773 */           if (PotionBase.isDebuff(potion)) {
/*  774 */             if ((PotionBase.isCurable(potion)) && (effect.func_76458_c() <= modifiers.getStrength())) {
/*  775 */               effectsToRemove.add(potion);
/*      */             }
/*      */             
/*  778 */             if ((potion == Witchery.Potions.DISEASED) && (modifiers.getStrength() >= 2)) {
/*  779 */               effectsToRemove.add(potion);
/*  780 */               if ((targetEntity instanceof EntityPlayer)) {
/*  781 */                 EntityPlayer player = (EntityPlayer)targetEntity;
/*  782 */                 ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  783 */                 if (playerEx != null) {
/*  784 */                   playerEx.clearCachedIncurablePotionEffect(potion);
/*      */                 }
/*      */               }
/*  787 */               if (modifiers.getStrength() >= 3) {
/*  788 */                 int R = 9;
/*  789 */                 Coord coord = new Coord(targetEntity);
/*  790 */                 for (int x = -9; x <= 9; x++) {
/*  791 */                   for (int z = -9; z <= 9; z++) {
/*  792 */                     for (int y = -9; y <= 9; y++) {
/*  793 */                       Block block = world.func_147439_a(coord.x + x, coord.y + y, coord.z + z);
/*  794 */                       if (block == Witchery.Blocks.DISEASE) {
/*  795 */                         world.func_147468_f(coord.x + x, coord.y + y, coord.z + z);
/*      */                       }
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  805 */         for (Potion potion : effectsToRemove) {
/*  806 */           targetEntity.func_82170_o(potion.field_76415_H);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*  813 */         List<Potion> effectsToRemove = new ArrayList();
/*  814 */         List<PotionEffect> effectsToAdd = new ArrayList();
/*  815 */         Collection<PotionEffect> effects = targetEntity.func_70651_bq();
/*  816 */         int modifiedStrength = modifiers.getStrength();
/*  817 */         for (PotionEffect effect : effects) {
/*  818 */           Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/*  819 */           if (PotionBase.isDebuff(potion)) {
/*  820 */             if (effect.func_76458_c() < modifiedStrength) {
/*  821 */               effectsToRemove.add(potion);
/*  822 */               if (world.field_73012_v.nextDouble() < 0.01D) {
/*  823 */                 effectsToAdd.add(new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c() + 1));
/*      */               }
/*      */             }
/*  826 */             else if (effect.func_76458_c() == modifiedStrength) {
/*  827 */               effectsToRemove.add(potion);
/*  828 */               if (world.field_73012_v.nextDouble() < 0.25D) {
/*  829 */                 effectsToAdd.add(new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c() + 1));
/*      */               }
/*      */             }
/*      */             else {
/*  833 */               effectsToRemove.add(potion);
/*  834 */               if (world.field_73012_v.nextDouble() < 0.75D) {
/*  835 */                 effectsToAdd.add(new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c() + 1));
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  841 */         for (Potion potion : effectsToRemove) {
/*  842 */           targetEntity.func_82170_o(potion.field_76415_H);
/*  843 */           if ((!PotionBase.isCurable(potion)) && ((targetEntity instanceof EntityPlayer))) {
/*  844 */             EntityPlayer player = (EntityPlayer)targetEntity;
/*  845 */             ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  846 */             if (playerEx != null) {
/*  847 */               playerEx.clearCachedIncurablePotionEffect(potion);
/*      */             }
/*      */           }
/*      */         }
/*  851 */         for (PotionEffect potionEffect : effectsToAdd) {
/*  852 */           targetEntity.func_70690_d(potionEffect);
/*      */         }
/*      */         
/*  855 */         if (effectsToAdd.isEmpty()) {
/*  856 */           ParticleEffect.SPELL.send(SoundEffect.RANDOM_LEVELUP, targetEntity, 0.5D, 2.0D, 16);
/*      */         } else {
/*  858 */           ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_ENDERDRAGON_GROWL, targetEntity, 0.5D, 2.0D, 16);
/*      */         }
/*      */         
/*      */       }
/*  862 */     });
/*  863 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemReekOfMisfortune.getBrewItemKey(), new BrewNamePart("witchery:brew.removebuffs"), new AltarPower(250), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*      */ 
/*  869 */         List<Potion> effectsToRemove = new ArrayList();
/*  870 */         Collection<PotionEffect> effects = targetEntity.func_70651_bq();
/*  871 */         for (PotionEffect effect : effects) {
/*  872 */           Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/*  873 */           if ((!PotionBase.isDebuff(potion)) && (PotionBase.isCurable(potion)) && (effect.func_76458_c() <= modifiers.getStrength()))
/*      */           {
/*  875 */             effectsToRemove.add(potion);
/*      */           }
/*      */         }
/*  878 */         for (Potion potion : effectsToRemove) {
/*  879 */           targetEntity.func_82170_o(potion.field_76415_H);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  884 */     });
/*  885 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150347_e), new BrewNamePart("witchery:brew.lavahold"), new AltarPower(100), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/*  891 */         if (!world.field_72995_K) {
/*  892 */           new BlockActionSphere()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z) {
/*  895 */               Block block = world.func_147439_a(x, y, z);
/*  896 */               if ((block == Blocks.field_150353_l) || (block == Blocks.field_150356_k))
/*  897 */                 Witchery.Blocks.SLURP.replaceBlockAt(world, x, y, z, modifiers.getModifiedDuration(TimeUtil.secsToTicks(10))); } }.drawFilledSphere(world, x, y, z, Math.max(radius + 2, 1));
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*  905 */     });
/*  906 */     register(new BrewPotionEffect(new BrewItemKey(Witchery.Blocks.BRAMBLE, 0), new BrewNamePart("witchery:potion.repellattacker"), new AltarPower(250), new Probability(1.0D), Witchery.Potions.REPELL_ATTACKER, TimeUtil.secsToTicks(90), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  910 */     register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150351_n), new BrewNamePart("witchery:potion.gasmask"), new AltarPower(100), new Probability(1.0D), Witchery.Potions.GAS_MASK, TimeUtil.secsToTicks(90), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  914 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151070_bp), new BrewNamePart("witchery:brew.poison"), new AltarPower(0), new Probability(1.0D), Potion.field_76436_u, TimeUtil.secsToTicks(45), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  918 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151102_aT), new BrewNamePart("witchery:brew.movespeed", "witchery:brew.moveslow"), new AltarPower(100), new Probability(1.0D), Potion.field_76424_c, TimeUtil.minsToTicks(3), Potion.field_76421_d, TimeUtil.secsToTicks(90), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  922 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151115_aP, 3), new BrewNamePart("witchery:brew.waterbreathing"), new AltarPower(100), new Probability(1.0D), Potion.field_76427_o, TimeUtil.minsToTicks(3), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  926 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151064_bs), new BrewNamePart("witchery:brew.resistfire"), new AltarPower(100), new Probability(1.0D), Potion.field_76426_n, TimeUtil.minsToTicks(3), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  930 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151150_bK), new BrewNamePart("witchery:brew.nightvision", "witchery:brew.invisibility"), new AltarPower(200), new Probability(1.0D), Potion.field_76439_r, TimeUtil.minsToTicks(3), Potion.field_76441_p, TimeUtil.minsToTicks(3), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  935 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151073_bk), new BrewNamePart("witchery:brew.regeneration", "witchery:brew.poison"), new AltarPower(200), new Probability(1.0D), Potion.field_76428_l, TimeUtil.secsToTicks(45), Potion.field_76436_u, TimeUtil.secsToTicks(45), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  940 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151065_br), new BrewNamePart("witchery:brew.damageboost", "witchery:brew.weakness"), new AltarPower(200), new Probability(1.0D), Potion.field_76420_g, TimeUtil.minsToTicks(3), Potion.field_76437_t, TimeUtil.secsToTicks(90), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  945 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151060_bw), new BrewNamePart("witchery:brew.healing", "witchery:brew.harming"), new AltarPower(200), new Probability(1.0D), Potion.field_76432_h, 0L, Potion.field_76433_i, 0L, new EffectLevel(2)).setStrengthCeiling(1));
/*      */     
/*      */ 
/*      */ 
/*  949 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151120_aE), new BrewNamePart("witchery:brew.floating"), new AltarPower(250), new Probability(1.0D), Witchery.Potions.FLOATING, TimeUtil.secsToTicks(90), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  953 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151116_aA), new BrewNamePart("witchery:brew.jump"), new AltarPower(200), new Probability(1.0D), Potion.field_76430_j, TimeUtil.minsToTicks(3), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  957 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151008_G), new BrewNamePart("witchery:brew.featherfall"), new AltarPower(100), new Probability(1.0D), Witchery.Potions.FEATHER_FALL, TimeUtil.minsToTicks(1), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  961 */     register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150337_Q), new BrewNamePart("witchery:potion.poisonweapons"), new AltarPower(200), new Probability(1.0D), Witchery.Potions.POISON_WEAPONS, TimeUtil.secsToTicks(90), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*  965 */     register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150321_G), new BrewNamePart("witchery:potion.reflectprojectiles", "witchery:potion.attractprojectiles"), new AltarPower(250), new Probability(1.0D), Witchery.Potions.REFLECT_PROJECTILES, TimeUtil.secsToTicks(90), Witchery.Potions.ATTRACT_PROJECTILES, TimeUtil.secsToTicks(45), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  970 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemBatBall.getBrewItemKey(), new BrewNamePart("witchery:brew.batburst"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*  975 */         int BAT_COUNT = ((modifiers.powerScalingFactor != 1.0D) || (modifiers.isGlancing) || (modifiers.strengthPenalty > 0) ? 1 : 10) + modifiers.getStrength();
/*      */         
/*      */ 
/*  978 */         explodeBats(world, new Coord(x, y, z), side, BAT_COUNT);
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*  984 */         if ((!(targetEntity instanceof EntityOwl)) && (!(targetEntity instanceof EntityBat))) {
/*  985 */           int BAT_COUNT = ((modifiers.powerScalingFactor != 1.0D) || (modifiers.isGlancing) ? 1 : 10) + modifiers.getStrength();
/*      */           
/*  987 */           if (((modifiers.powerScalingFactor == 1.0D) && (!modifiers.isGlancing)) || (world.field_73012_v.nextInt(20) == 0))
/*      */           {
/*  989 */             explodeBats(world, new Coord(targetEntity), ForgeDirection.UP, BAT_COUNT);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */       private void explodeBats(World world, Coord coord, ForgeDirection side, int bats) {
/*  995 */         int x = coord.x + side.offsetX;
/*  996 */         int z = coord.z + side.offsetZ;
/*  997 */         int y = coord.y + side.offsetY;
/*      */         
/*  999 */         int NUM_BATS = bats;
/* 1000 */         for (int i = 0; i < NUM_BATS; i++) {
/* 1001 */           EntityBat bat = new EntityBat(world);
/* 1002 */           EntityUtil.setNoDrops(bat);
/* 1003 */           bat.func_70012_b(x, y, z, 0.0F, 0.0F);
/* 1004 */           world.func_72838_d(bat);
/*      */         }
/* 1006 */         ParticleEffect.EXPLODE.send(SoundEffect.MOB_ENDERMEN_PORTAL, world, 0.5D + x, 0.5D + y, 0.5D + z, 3.0D, 3.0D, 16);
/*      */       }
/*      */       
/*      */ 
/* 1010 */     });
/* 1011 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemOwletsWing.getBrewItemKey(), new BrewNamePart("witchery:brew.bodega"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1016 */         if ((modifiers.caster != null) && (Familiar.hasActiveBroomMasteryFamiliar(modifiers.caster)) && 
/* 1017 */           (!(targetEntity instanceof EntityOwl)) && (!(targetEntity instanceof EntityBat))) {
/* 1018 */           int BIRD_COUNT = ((modifiers.powerScalingFactor != 1.0D) || (modifiers.isGlancing) ? 1 : 3 + world.field_73012_v.nextInt(2)) + modifiers.getStrength();
/*      */           
/* 1020 */           if (((modifiers.powerScalingFactor == 1.0D) && (!modifiers.isGlancing)) || (world.field_73012_v.nextInt(20) == 0))
/*      */           {
/* 1022 */             explodeBirds(world, targetEntity, BIRD_COUNT);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */       private void explodeBirds(World world, EntityLivingBase victim, int birds)
/*      */       {
/* 1029 */         for (int i = 0; i < birds; i++) {
/* 1030 */           EntityOwl owl = new EntityOwl(world);
/* 1031 */           owl.func_70012_b(victim.field_70165_t - 2.0D + world.field_73012_v.nextInt(5), victim.field_70163_u + victim.field_70131_O + 1.0D + world.field_73012_v.nextInt(2), victim.field_70161_v - 2.0D + world.field_73012_v.nextInt(5), 0.0F, 0.0F);
/*      */           
/* 1033 */           owl.func_70624_b(victim);
/* 1034 */           owl.setTimeToLive(400);
/* 1035 */           world.func_72838_d(owl);
/*      */           
/* 1037 */           ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, owl, 1.0D, 1.0D, 16);
/*      */         }
/*      */         
/*      */       }
/* 1041 */     });
/* 1042 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemBreathOfTheGoddess.getBrewItemKey(), new BrewNamePart("witchery:brew.airhike"), new AltarPower(750), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 1048 */         double motionY = 0.6D + 0.2D * modifiers.getStrength();
/* 1049 */         targetEntity.field_70143_R = 0.0F;
/* 1050 */         if ((targetEntity instanceof EntityPlayer)) {
/* 1051 */           Witchery.packetPipeline.sendTo(new PacketPushTarget(0.0D, motionY, 0.0D), (EntityPlayer)targetEntity);
/*      */         }
/*      */         else
/*      */         {
/* 1055 */           targetEntity.field_70181_x = motionY;
/*      */         }
/*      */         
/*      */       }
/* 1059 */     });
/* 1060 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151123_aH), new BrewNamePart("witchery:brew.frogtongue"), new AltarPower(150), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1065 */         if ((modifiers.getStrength() > 0) && (!(modifiers.caster instanceof FakePlayer))) {
/* 1066 */           EntityUtil.pullTowards(world, targetEntity, new EntityPosition(modifiers.caster), 0.05D, 0.0D);
/*      */         } else {
/* 1068 */           EntityUtil.pullTowards(world, targetEntity, modifiers.impactLocation, 0.05D, 0.0D);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 1075 */         double R = radius;
/* 1076 */         double R_SQ = R * R;
/*      */         
/* 1078 */         EntityPosition position = modifiers.impactLocation;
/* 1079 */         AxisAlignedBB bb = AxisAlignedBB.func_72330_a(position.x - R, position.y - R, position.z - R, position.x + R, position.y + R, position.z + R);
/*      */         
/* 1081 */         List<Entity> list1 = world.func_72872_a(Entity.class, bb);
/*      */         
/* 1083 */         for (Entity targetEntity : list1) {
/* 1084 */           if (!(targetEntity instanceof EntityLivingBase)) {
/* 1085 */             double distanceSq = position.getDistanceSqToEntity(targetEntity);
/* 1086 */             if (distanceSq <= R_SQ) {
/* 1087 */               if ((modifiers.getStrength() > 0) && (!(modifiers.caster instanceof FakePlayer))) {
/* 1088 */                 EntityUtil.pullTowards(world, targetEntity, new EntityPosition(modifiers.caster), 0.05D, 0.0D);
/*      */               }
/*      */               else {
/* 1091 */                 EntityUtil.pullTowards(world, targetEntity, modifiers.impactLocation, 0.05D, 0.0D);
/*      */               }
/*      */               
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*      */       }
/* 1099 */     });
/* 1100 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150423_aK), new BrewNamePart("witchery:brew.harmundead"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 1106 */         int strength = Math.min(modifiers.getStrength(), modifiers.strengthCeilingDisabled ? 3 : 1);
/* 1107 */         if (CreatureUtil.isUndead(targetEntity)) {
/* 1108 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((10 << strength) * modifiers.powerScalingFactor));
/*      */         }
/*      */         else
/*      */         {
/* 1112 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((3 << strength) * modifiers.powerScalingFactor));
/*      */ 
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 1119 */     });
/* 1120 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150328_O, 1), new BrewNamePart("witchery:brew.harminsects"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1125 */         int strength = Math.min(modifiers.getStrength(), modifiers.strengthCeilingDisabled ? 3 : 1);
/* 1126 */         if (CreatureUtil.isInsect(targetEntity)) {
/* 1127 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((10 << strength) * modifiers.powerScalingFactor));
/*      */         }
/*      */         else
/*      */         {
/* 1131 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((3 << strength) * modifiers.powerScalingFactor));
/*      */ 
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 1138 */     });
/* 1139 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemOilOfVitriol.getBrewItemKey(), new BrewNamePart("witchery:brew.erosion"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1144 */         final Count obsidianCount = new Count();
/* 1145 */         for (int r = radius; r > 0; r--) {
/* 1146 */           int depth = radius - r;
/* 1147 */           new BlockActionCircle()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z) {
/* 1150 */               Block block = world.func_147439_a(x, y, z);
/* 1151 */               if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */               {
/*      */ 
/* 1154 */                 world.func_147468_f(x, y, z);
/* 1155 */                 ParticleEffect.SPLASH.send(SoundEffect.NONE, world, x, y, z, 0.5D, 0.5D, 16);
/* 1156 */                 obsidianCount.incrementBy(block == Blocks.field_150343_Z ? 1 : 0); } } }.processFilledCircle(world, x, y, z, r);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1161 */         SoundEffect.RANDOM_FIZZ.playAt(world, x, y, z, 1.0F, 2.0F);
/* 1162 */         SpawnUtil.spawnEntityItem(world, x, y, z, Blocks.field_150343_Z, obsidianCount.get());
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1168 */         if (world.field_73012_v.nextInt(MathHelper.func_76143_f(5.0D / modifiers.powerScalingFactor)) == 0) {
/* 1169 */           targetEntity.func_70097_a(DamageSource.func_76356_a(targetEntity, modifiers.caster), MathHelper.func_76143_f(8.0D * modifiers.powerScalingFactor));
/*      */         }
/*      */         
/* 1172 */         for (int slot = 0; slot < 5; slot++) {
/* 1173 */           ItemStack stack = targetEntity.func_71124_b(slot);
/* 1174 */           if ((stack != null) && (stack.func_77984_f())) {
/* 1175 */             stack.func_77972_a(MathHelper.func_76143_f((50.0D + world.field_73012_v.nextInt(25)) * modifiers.powerScalingFactor), modifiers.caster);
/*      */             
/*      */ 
/* 1178 */             if ((stack.func_77960_j() >= stack.func_77958_k()) || (stack.field_77994_a <= 0)) {
/* 1179 */               targetEntity.func_70062_b(slot, null);
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */       }
/* 1186 */     });
/* 1187 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150424_aL), new BrewNamePart("witchery:brew.levelling"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int r, final ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 1193 */         final int y0 = modifiers.ritualised ? y - 1 : y;
/* 1194 */         int radius = modifiers.ritualised ? r + (modifiers.getStrength() + 1) * 3 : r;
/*      */         
/* 1196 */         final Count dirt = new Count();
/* 1197 */         final Count stone = new Count();
/* 1198 */         final Count sand = new Count();
/* 1199 */         final Count sandstone = new Count();
/* 1200 */         final Count netherrack = new Count();
/* 1201 */         final Count endstone = new Count();
/*      */         
/* 1203 */         final int s = modifiers.getStrength();
/* 1204 */         int defaultAmount = modifiers.ritualised ? 64 + 32 * modifiers.getStrength() : 16;
/* 1205 */         Block hitBlock = world.func_147439_a(x, y0, z);
/* 1206 */         if (hitBlock == Blocks.field_150346_d) {
/* 1207 */           dirt.incrementBy(defaultAmount);
/* 1208 */         } else if (hitBlock == Blocks.field_150348_b) {
/* 1209 */           stone.incrementBy(defaultAmount);
/* 1210 */         } else if (hitBlock == Blocks.field_150354_m) {
/* 1211 */           sand.incrementBy(defaultAmount);
/* 1212 */         } else if (hitBlock == Blocks.field_150322_A) {
/* 1213 */           sandstone.incrementBy(defaultAmount);
/* 1214 */         } else if (hitBlock == Blocks.field_150424_aL) {
/* 1215 */           netherrack.incrementBy(defaultAmount);
/* 1216 */         } else if (hitBlock == Blocks.field_150377_bs) {
/* 1217 */           endstone.incrementBy(defaultAmount);
/*      */         }
/*      */         
/* 1220 */         new BlockActionCircle()
/*      */         {
/*      */           public void onBlock(World world, int x, int y, int z) {
/* 1223 */             for (int dy = y0 + 1; dy < y0 + 4 + s; dy++) {
/* 1224 */               Block block = world.func_147439_a(x, dy, z);
/* 1225 */               if ((block != Blocks.field_150350_a) && (BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */               {
/*      */ 
/*      */ 
/* 1229 */                 if (block == Blocks.field_150348_b) {
/* 1230 */                   stone.increment();
/* 1231 */                 } else if (block == Blocks.field_150354_m) {
/* 1232 */                   sand.increment();
/* 1233 */                 } else if (block == Blocks.field_150322_A) {
/* 1234 */                   sandstone.increment();
/* 1235 */                 } else if (block == Blocks.field_150424_aL) {
/* 1236 */                   netherrack.increment();
/* 1237 */                 } else if (block == Blocks.field_150377_bs) {
/* 1238 */                   endstone.increment();
/*      */                 } else {
/* 1240 */                   dirt.increment();
/*      */                 }
/* 1242 */                 world.func_147468_f(x, dy, z);
/* 1243 */                 ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, dy, z, 0.5D, 0.5D, 16); } } } }.processFilledCircle(world, x, y0, z, radius);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1249 */         for (int newy = y0; newy >= y0 - (4 + s); newy--) {
/* 1250 */           final int dy = newy;
/* 1251 */           new BlockActionCircle()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z)
/*      */             {
/* 1255 */               Block block = world.func_147439_a(x, dy, z);
/* 1256 */               if (BlockUtil.isReplaceableBlock(world, x, dy, z, modifiers.caster)) {
/* 1257 */                 if (endstone.get() > 0) {
/* 1258 */                   endstone.decrement();
/* 1259 */                   world.func_147449_b(x, dy, z, Blocks.field_150377_bs);
/* 1260 */                 } else if (netherrack.get() > 0) {
/* 1261 */                   netherrack.decrement();
/* 1262 */                   world.func_147449_b(x, dy, z, Blocks.field_150424_aL);
/* 1263 */                 } else if (sandstone.get() > 0) {
/* 1264 */                   sandstone.decrement();
/* 1265 */                   world.func_147449_b(x, dy, z, Blocks.field_150322_A);
/* 1266 */                 } else if (sand.get() > 0) {
/* 1267 */                   sand.decrement();
/* 1268 */                   world.func_147449_b(x, dy, z, Blocks.field_150354_m);
/* 1269 */                 } else if (stone.get() > 0) {
/* 1270 */                   stone.decrement();
/* 1271 */                   world.func_147449_b(x, dy, z, Blocks.field_150348_b);
/* 1272 */                 } else if (dirt.get() > 0) {
/* 1273 */                   dirt.decrement();
/* 1274 */                   world.func_147449_b(x, dy, z, Blocks.field_150346_d);
/*      */                 } else {
/* 1276 */                   return;
/*      */                 }
/*      */                 
/* 1279 */                 ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, dy, z, 0.5D, 0.5D, 16); } } }.processFilledCircle(world, x, y0, z, radius);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1286 */         SoundEffect.RANDOM_FIZZ.playAt(world, x, y0, z, 1.0F, 2.0F);
/*      */       }
/*      */       
/*      */ 
/* 1290 */     });
/* 1291 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemWeb.getBrewItemKey(), new BrewNamePart("witchery:brew.webs"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1296 */         placeWeb(world, new Coord(x, y, z), modifiers, side, modifiers.caster);
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1302 */         placeWeb(world, new Coord(targetEntity), modifiers, ForgeDirection.UNKNOWN, modifiers.caster);
/*      */       }
/*      */       
/*      */       private void placeWeb(World world, Coord coord, ModifiersEffect modifiers, ForgeDirection side, EntityPlayer source)
/*      */       {
/* 1307 */         Coord location = BlockUtil.getClosestPlantableBlock(world, coord.x, coord.y, coord.z, side, source, true);
/*      */         
/* 1309 */         if (location != null) {
/* 1310 */           Block web = Witchery.Blocks.WEB;
/* 1311 */           BlockUtil.setBlockIfReplaceable(world, location.x, location.y, location.z, web);
/*      */           
/* 1313 */           if (modifiers.getStrength() > 0) {
/* 1314 */             BlockUtil.setBlockIfReplaceable(world, location.x + 1, location.y, location.z, web);
/* 1315 */             BlockUtil.setBlockIfReplaceable(world, location.x - 1, location.y, location.z, web);
/* 1316 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y, location.z + 1, web);
/* 1317 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y, location.z - 1, web);
/* 1318 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y + 1, location.z, web);
/* 1319 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y - 1, location.z, web);
/*      */           }
/*      */           
/* 1322 */           if (modifiers.getStrength() > 1) {
/* 1323 */             BlockUtil.setBlockIfReplaceable(world, location.x + 1, location.y, location.z + 1, web);
/* 1324 */             BlockUtil.setBlockIfReplaceable(world, location.x - 1, location.y, location.z + 1, web);
/* 1325 */             BlockUtil.setBlockIfReplaceable(world, location.x + 1, location.y, location.z - 1, web);
/* 1326 */             BlockUtil.setBlockIfReplaceable(world, location.x - 1, location.y, location.z - 1, web);
/* 1327 */             BlockUtil.setBlockIfReplaceable(world, location.x + 1, location.y + 1, location.z, web);
/* 1328 */             BlockUtil.setBlockIfReplaceable(world, location.x - 1, location.y + 1, location.z, web);
/* 1329 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y + 1, location.z + 1, web);
/* 1330 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y + 1, location.z - 1, web);
/* 1331 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y - 1, location.z + 1, web);
/* 1332 */             BlockUtil.setBlockIfReplaceable(world, location.x, location.y - 1, location.z - 1, web);
/* 1333 */             BlockUtil.setBlockIfReplaceable(world, location.x + 1, location.y - 1, location.z, web);
/* 1334 */             BlockUtil.setBlockIfReplaceable(world, location.x - 1, location.y - 1, location.z, web);
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/* 1340 */     });
/* 1341 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150395_bd), new BrewNamePart("witchery:brew.vines").setBaseDuration(TimeUtil.secsToTicks(90)), new AltarPower(150), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 1347 */         int meta = 0;
/* 1348 */         switch (WitcheryBrewRegistry.82.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[side.ordinal()]) {
/*      */         case 1: 
/* 1350 */           meta = 2;
/* 1351 */           break;
/*      */         case 2: 
/* 1353 */           meta = 3;
/* 1354 */           break;
/*      */         case 3: 
/* 1356 */           meta = 4;
/* 1357 */           break;
/*      */         case 4: 
/* 1359 */           meta = 5;
/* 1360 */           break;
/*      */         default: 
/* 1362 */           return;
/*      */         }
/* 1364 */         Block vine = Witchery.Blocks.VINE;
/*      */         
/* 1366 */         int newX = x + side.offsetX;
/* 1367 */         int newZ = z + side.offsetZ;
/* 1368 */         int offsetY = 0;
/*      */         
/*      */ 
/* 1371 */         while ((BlockUtil.isReplaceableBlock(world, newX, y + offsetY, newZ)) && (y + offsetY > 0) && ((modifiers.getStrength() > 0) || (world.func_147439_a(x, y + offsetY, z).func_149688_o().func_76220_a())))
/*      */         {
/* 1373 */           world.func_147465_d(newX, y + offsetY, newZ, vine, meta, 3);
/* 1374 */           offsetY--;
/*      */         }
/*      */         
/* 1377 */         offsetY = 1;
/*      */         
/*      */ 
/* 1380 */         while (((BlockUtil.isReplaceableBlock(world, newX, y + offsetY, newZ)) || (world.func_147439_a(newX, y + offsetY, newZ).func_149688_o() == Material.field_151584_j)) && (world.func_147439_a(x, y + offsetY, z).func_149688_o().func_76220_a()) && (y + offsetY < 255)) {
/* 1381 */           world.func_147465_d(newX, y + offsetY, newZ, vine, meta, 3);
/* 1382 */           offsetY++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1389 */         BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Witchery.Potions.WRAPPED_IN_VINE, TimeUtil.secsToTicks(90), modifiers.noParticles, modifiers.caster);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/* 1394 */     });
/* 1395 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150434_aF), new BrewNamePart("witchery:brew.thorns").setBaseDuration(TimeUtil.secsToTicks(90)), new AltarPower(150), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 1401 */         Coord coord = null;
/* 1402 */         if (world.func_147439_a(x, y, z) == Witchery.Blocks.CACTUS) {
/* 1403 */           y++;
/* 1404 */           while (world.func_147439_a(x, y, z) == Witchery.Blocks.CACTUS) {
/* 1405 */             y++;
/*      */           }
/* 1407 */           if (BlockUtil.isReplaceableBlock(world, x, y, z)) {
/* 1408 */             coord = new Coord(x, y, z);
/*      */           }
/*      */         } else {
/* 1411 */           coord = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/*      */         }
/*      */         
/* 1414 */         if (coord != null) {
/* 1415 */           int i = 0; for (int height = 3 + modifiers.getStrength(); i < height; i++) {
/* 1416 */             if ((!BlockUtil.isReplaceableBlock(world, coord.x, coord.y + i, coord.z)) || (coord.y + i >= 255))
/*      */               break;
/* 1418 */             world.func_147449_b(coord.x, coord.y + i, coord.z, Witchery.Blocks.CACTUS);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1429 */         BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Witchery.Potions.SPIKED, TimeUtil.secsToTicks(90), modifiers.noParticles, modifiers.caster);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/* 1434 */     });
/* 1435 */     register(new BrewActionSprouting(Witchery.Items.GENERIC.itemBranchEnt.getBrewItemKey(), new BrewNamePart("witchery:brew.sprouting").setBaseDuration(TimeUtil.secsToTicks(15)), new AltarPower(350), new Probability(1.0D), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1440 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemIcyNeedle.getBrewItemKey(), new BrewNamePart("witchery:brew.cold").setBaseDuration(TimeUtil.minsToTicks(3)), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 1446 */         if (BlockProtect.checkModsForBreakOK(world, x, y, z, modifiers.caster)) {
/* 1447 */           new BlockActionReplaceSphere()
/*      */           {
/*      */             protected boolean onShouldReplace(World world, int x, int y, int z, Block block) {
/* 1450 */               return block.func_149688_o() == Material.field_151586_h;
/*      */             }
/*      */             
/*      */ 
/*      */ 
/* 1455 */             protected void onReplaceBlock(World world, int x, int y, int z, Block block) { world.func_147449_b(x, y, z, Blocks.field_150432_aD); } }.replaceBlocks(world, x, y, z, 2 + 2 * modifiers.getStrength());
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1464 */         BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Witchery.Potions.CHILLED, TimeUtil.minsToTicks(3), modifiers.noParticles, modifiers.caster);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/* 1469 */     });
/* 1470 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151055_y), new BrewNamePart("witchery:brew.knockback"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */         double radiusSq;
/*      */         EntityPosition position;
/* 1476 */         if (modifiers.impactLocation != null) {
/* 1477 */           EntityUtil.pushback(world, targetEntity, modifiers.impactLocation, 1.0D + modifiers.getStrength() * modifiers.powerScalingFactor, 0.5D + modifiers.getStrength() * 0.2D);
/*      */         }
/*      */         else
/*      */         {
/* 1481 */           double radius = 3 + modifiers.getStrength();
/* 1482 */           radiusSq = radius * radius;
/* 1483 */           position = new EntityPosition(targetEntity);
/* 1484 */           List<Entity> entities = world.func_72839_b(targetEntity, position.getBounds(radius));
/*      */           
/* 1486 */           for (Entity entity : entities) {
/* 1487 */             if ((((entity instanceof EntityLivingBase)) || ((entity instanceof EntityItem))) && (targetEntity.func_70068_e(entity) <= radiusSq))
/*      */             {
/* 1489 */               EntityUtil.pushback(world, entity, position, 1.0D + modifiers.getStrength() * modifiers.powerScalingFactor, 0.5D + modifiers.getStrength() * 0.2D);
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1498 */     });
/* 1499 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 0), new BrewNamePart("witchery:brew.treeoak"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 1505 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1506 */         if (location != null) {
/* 1507 */           (modifiers.getStrength() > 1 ? new net.minecraft.world.gen.feature.WorldGenBigTree(true) : new WorldGenTrees(true)).func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1512 */     });
/* 1513 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 1), new BrewNamePart("witchery:brew.treespruce"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1518 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1519 */         if (location != null) {
/* 1520 */           (modifiers.getStrength() > 1 ? new net.minecraft.world.gen.feature.WorldGenMegaPineTree(false, world.field_73012_v.nextBoolean()) : new net.minecraft.world.gen.feature.WorldGenTaiga2(true)).func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 1526 */     });
/* 1527 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 2), new BrewNamePart("witchery:brew.treebirch"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1532 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1533 */         if (location != null) {
/* 1534 */           new WorldGenForest(true, false).func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1539 */     });
/* 1540 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 3), new BrewNamePart("witchery:brew.treejungle"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1545 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1546 */         if (location != null) {
/* 1547 */           (modifiers.strength > 2 ? new net.minecraft.world.gen.feature.WorldGenMegaJungle(true, 10, 20, 3, 3) : new WorldGenTrees(true, 4 + world.field_73012_v.nextInt(7), 3, 3, false)).func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 1553 */     });
/* 1554 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 4), new BrewNamePart("witchery:brew.treeacacia"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1559 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1560 */         if (location != null) {
/* 1561 */           new WorldGenSavannaTree(true).func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/* 1565 */     });
/* 1566 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 5), new BrewNamePart("witchery:brew.treedarkoak"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1571 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1572 */         if (location != null) {
/* 1573 */           new WorldGenCanopyTree(true).func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/* 1577 */     });
/* 1578 */     register(new BrewActionEffect(new BrewItemKey(Witchery.Blocks.SAPLING, 0), new BrewNamePart("witchery:brew.treerowan"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1583 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1584 */         if (location != null) {
/* 1585 */           new WorldGenWitchTree(true, 5, 0, 0, 1, false).func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1590 */     });
/* 1591 */     register(new BrewActionEffect(new BrewItemKey(Witchery.Blocks.SAPLING, 1), new BrewNamePart("witchery:brew.treealder"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1596 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1597 */         if (location != null) {
/* 1598 */           WorldGenLargeWitchTree tree = new WorldGenLargeWitchTree(true, 1, 1, 0.5D);
/* 1599 */           tree.func_76487_a(0.6D, 0.5D, 0.5D);
/* 1600 */           tree.func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/* 1604 */     });
/* 1605 */     register(new BrewActionEffect(new BrewItemKey(Witchery.Blocks.SAPLING, 2), new BrewNamePart("witchery:brew.treehawthorn"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1610 */         Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster);
/* 1611 */         if (location != null) {
/* 1612 */           WorldGenLargeWitchTree tree = new WorldGenLargeWitchTree(true, 2, 2);
/* 1613 */           tree.func_76487_a(0.8D, 1.2D, 1.0D);
/* 1614 */           tree.func_76484_a(world, world.field_73012_v, location.x, location.y, location.z);
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1619 */     });
/* 1620 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemHeartOfGold.getBrewItemKey(), new BrewNamePart("witchery:brew.animalattraction", "witchery:brew.animalrepulsion"), new AltarPower(500), new Probability(1.0D), new EffectLevel(4))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1625 */         double radius = (modifiers.getStrength() + 1) * 16;
/* 1626 */         double radiusSq = radius * radius;
/* 1627 */         AxisAlignedBB bounds = targetEntity.field_70121_D.func_72314_b(radius, radius, radius);
/*      */         
/* 1629 */         int maxCreatures = (int)Math.ceil((modifiers.getStrength() + 1.0D) * 2.0D * modifiers.powerScalingFactor);
/*      */         
/*      */ 
/* 1632 */         List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, bounds);
/*      */         
/*      */ 
/* 1635 */         for (EntityLivingBase otherEntity : entities) {
/* 1636 */           if ((otherEntity != targetEntity) && (otherEntity.func_70068_e(targetEntity) < radiusSq)) {
/* 1637 */             if ((otherEntity instanceof EntityTameable)) {
/* 1638 */               EntityTameable tameable = (EntityTameable)otherEntity;
/* 1639 */               if (Familiar.couldBeFamiliar(tameable)) {
/*      */                 continue;
/*      */               }
/*      */               
/* 1643 */               if (!modifiers.inverted) {
/* 1644 */                 if (!tameable.func_70909_n()) {
/* 1645 */                   tameable.func_70903_f(true);
/* 1646 */                   tameable.func_70778_a((PathEntity)null);
/* 1647 */                   tameable.func_70624_b((EntityLivingBase)null);
/*      */                   
/* 1649 */                   tameable.func_70606_j(20.0F);
/* 1650 */                   tameable.func_152115_b(targetEntity.func_110124_au().toString());
/* 1651 */                   world.func_72960_a(tameable, (byte)7);
/* 1652 */                   if ((tameable instanceof EntityOcelot)) {
/* 1653 */                     ((EntityOcelot)tameable).func_70912_b(1 + world.field_73012_v.nextInt(3));
/*      */                   }
/*      */                 }
/*      */               }
/* 1657 */               else if ((tameable.func_70909_n()) && (!tameable.func_152114_e(targetEntity))) {
/* 1658 */                 tameable.func_70903_f(false);
/* 1659 */                 tameable.func_70778_a((PathEntity)null);
/* 1660 */                 tameable.func_70624_b((EntityLivingBase)null);
/*      */                 
/* 1662 */                 tameable.func_152115_b("");
/* 1663 */                 world.func_72960_a(tameable, (byte)6);
/* 1664 */                 if ((tameable instanceof EntityOcelot)) {
/* 1665 */                   ((EntityOcelot)tameable).func_70912_b(0);
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/*      */ 
/* 1671 */             if ((otherEntity instanceof EntityAnimal)) {
/* 1672 */               EntityAnimal animal = (EntityAnimal)otherEntity;
/* 1673 */               if (!modifiers.inverted) {
/* 1674 */                 if (!animal.func_70661_as().func_75492_a(targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, 1.0D))
/*      */                 {
/* 1676 */                   animal.func_70778_a(world.func_72844_a(animal, MathHelper.func_76128_c(targetEntity.field_70165_t), MathHelper.func_76128_c(targetEntity.field_70163_u), MathHelper.func_76128_c(targetEntity.field_70161_v), 10.0F, true, false, false, true));
/*      */                 }
/*      */                 
/*      */ 
/*      */               }
/*      */               else
/*      */               {
/*      */ 
/* 1684 */                 int RANGE = 6;
/* 1685 */                 int newX = MathHelper.func_76128_c(targetEntity.field_70165_t) + (world.field_73012_v.nextBoolean() ? 1 : -1) * (RANGE + world.field_73012_v.nextInt(RANGE));
/*      */                 
/*      */ 
/* 1688 */                 int newZ = MathHelper.func_76128_c(targetEntity.field_70161_v) + (world.field_73012_v.nextBoolean() ? 1 : -1) * (RANGE + world.field_73012_v.nextInt(RANGE));
/*      */                 
/*      */ 
/*      */ 
/*      */ 
/* 1693 */                 for (int newY = 62; !world.func_147437_c(newX, newY + 1, newZ); newY++) {}
/*      */                 
/*      */ 
/* 1696 */                 if (!animal.func_70661_as().func_75492_a(newX, newY, newZ, 1.0D)) {
/* 1697 */                   animal.func_70634_a(0.5D + newX, newY, 0.5D + newZ);
/*      */                 }
/*      */                 
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/* 1706 */     });
/* 1707 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemSilverDust.getBrewItemKey(), new BrewNamePart("witchery:brew.harmwerewolves"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(4))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1712 */         int strength = Math.min(modifiers.getStrength(), modifiers.strengthCeilingDisabled ? 3 : 1);
/* 1713 */         if (CreatureUtil.isWerewolf(targetEntity)) {
/* 1714 */           targetEntity.func_70097_a(new com.emoniph.witchery.util.EntityDamageSourceIndirectSilver(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((3 << strength) * modifiers.powerScalingFactor));
/*      */         }
/*      */         else
/*      */         {
/* 1718 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((1 << strength) * modifiers.powerScalingFactor));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 1727 */         if (!world.field_72995_K) {
/* 1728 */           WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
/* 1729 */           int i = (300 + world.field_73012_v.nextInt(600)) * 20;
/* 1730 */           if (!worldinfo.func_76059_o()) {
/* 1731 */             worldinfo.func_76080_g(i);
/* 1732 */             worldinfo.func_76084_b(true);
/*      */           }
/* 1734 */           if ((modifiers.getStrength() >= 1) && (!worldinfo.func_76061_m())) {
/* 1735 */             worldinfo.func_76090_f(i);
/* 1736 */             worldinfo.func_76069_a(true);
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/* 1742 */     });
/* 1743 */     register(new BrewActionEffect(new BrewItemKey(Witchery.Items.SEEDS_GARLIC), new BrewNamePart("witchery:brew.weakenvampires"), new AltarPower(500), new Probability(1.0D), new EffectLevel(4))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 1748 */         if (CreatureUtil.isVampire(targetEntity)) {
/* 1749 */           if ((targetEntity instanceof EntityPlayer)) {
/* 1750 */             ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)targetEntity);
/* 1751 */             playerEx.decreaseBloodPower(50 + 20 * modifiers.getStrength(), false);
/*      */           }
/* 1753 */           BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Potion.field_76437_t, TimeUtil.secsToTicks(90), false, modifiers.caster);
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1758 */     });
/* 1759 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151144_bL, 1), new BrewNamePart("witchery:brew.wither"), new AltarPower(200), new Probability(1.0D), Potion.field_82731_v, TimeUtil.secsToTicks(15), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1763 */     register(new BrewActionEffect(new BrewItemKey(Witchery.Blocks.GLINT_WEED), new BrewNamePart("witchery:brew.inferno"), new AltarPower(750), new Probability(1.0D), new EffectLevel(3))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*      */ 
/* 1769 */         if ((!world.field_72995_K) && (!modifiers.isGlancing)) {
/* 1770 */           EntitySplatter.splatter(world, new Coord(x, y, z, side), modifiers.getStrength());
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 1778 */         if ((!world.field_72995_K) && (!modifiers.isGlancing) && ((targetEntity instanceof EntityLivingBase))) {
/* 1779 */           Coord coord = new Coord(targetEntity);
/* 1780 */           targetEntity.func_70015_d(2 + 2 * modifiers.getStrength());
/* 1781 */           if (modifiers.powerScalingFactor == 1.0D) {
/* 1782 */             EntitySplatter.splatter(world, new Coord(targetEntity), modifiers.powerScalingFactor == 1.0D ? modifiers.getStrength() : 0);
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/* 1788 */     });
/* 1789 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemInfernalBlood.getBrewItemKey(), new BrewNamePart("witchery:brew.fear"), new AltarPower(500), new Probability(1.0D), Witchery.Potions.GROTESQUE, TimeUtil.minsToTicks(3), new EffectLevel(2)));
/*      */     
/*      */ 
/*      */ 
/* 1793 */     register(new BrewPotionEffect(Dye.INK_SAC.getBrewItemKey(), new BrewNamePart("witchery:brew.blindness"), new AltarPower(1000), new Probability(1.0D), Potion.field_76440_q, TimeUtil.secsToTicks(15), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1797 */     register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150328_O), new BrewNamePart("witchery:brew.love"), new AltarPower(500), new Probability(1.0D), Witchery.Potions.LOVE, TimeUtil.secsToTicks(10), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1801 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemDemonHeart.getBrewItemKey(), new BrewNamePart("witchery:brew.paralysis"), new AltarPower(750), new Probability(1.0D), Witchery.Potions.PARALYSED, TimeUtil.secsToTicks(10), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1805 */     register(new BrewCurseEffect(Witchery.Items.GENERIC.itemDropOfLuck.getBrewItemKey(), new BrewNamePart("witchery:brew.potionmaster", "witchery:brew.insanity"), new AltarPower(5000), new Probability(1.0D), Witchery.Potions.BREWING_EXPERT, TimeUtil.minsToTicks(6), Witchery.Potions.INSANITY, TimeUtil.minsToTicks(3), new EffectLevel(4), true));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1810 */     register(new BrewCurseEffect(new BrewItemKey(Items.field_151078_bh), new BrewNamePart("witchery:potion.diseased"), new AltarPower(2000), new Probability(1.0D), Witchery.Potions.DISEASED, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
/*      */     
/*      */ 
/*      */ 
/* 1814 */     register(new BrewCurseEffect(Witchery.Items.GENERIC.itemDisturbedCotton.getBrewItemKey(), new BrewNamePart("witchery:brew.sinking"), new AltarPower(3000), new Probability(1.0D), Witchery.Potions.SINKING, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
/*      */     
/*      */ 
/*      */ 
/* 1818 */     register(new BrewCurseEffect(new BrewItemKey(Witchery.Blocks.EMBER_MOSS), new BrewNamePart("witchery:brew.overheating"), new AltarPower(3000), new Probability(1.0D), Witchery.Potions.OVERHEATING, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
/*      */     
/*      */ 
/*      */ 
/* 1822 */     register(new BrewCurseEffect(Witchery.Items.GENERIC.itemMellifluousHunger.getBrewItemKey(), new BrewNamePart("witchery:brew.wakingnightmare"), new AltarPower(10000), new Probability(1.0D), Witchery.Potions.WAKING_NIGHTMARE, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
/*      */     
/*      */ 
/*      */ 
/* 1826 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemToeOfFrog.getBrewItemKey(), new BrewNamePart("witchery:brew.frogsleg"), new AltarPower(500), new Probability(1.0D), Witchery.Potions.DOUBLE_JUMP, TimeUtil.minsToTicks(6), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1830 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151153_ao), new BrewNamePart("witchery:brew.absorbsion"), new AltarPower(1000), new Probability(1.0D), Potion.field_76444_x, TimeUtil.secsToTicks(30), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1834 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151153_ao, 1), new BrewNamePart("witchery:brew.healthboost"), new AltarPower(1000), new Probability(1.0D), Potion.field_76434_w, TimeUtil.minsToTicks(2), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1838 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemSubduedSpirit.getBrewItemKey(), new BrewNamePart("witchery:brew.wasting", "witchery:brew.fullness"), new AltarPower(500), new Probability(1.0D), new EffectLevel(4))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*      */ 
/* 1844 */         int hungerTicks = modifiers.getModifiedDuration(TimeUtil.secsToTicks(20));
/* 1845 */         int poisonTicks = Math.max(modifiers.getModifiedDuration(TimeUtil.secsToTicks(3)), 40);
/* 1846 */         int strength = 1 + modifiers.getStrength() / 2;
/* 1847 */         if ((targetEntity instanceof EntityPlayer)) {
/* 1848 */           EntityPlayer victim = (EntityPlayer)targetEntity;
/* 1849 */           if (modifiers.inverted) {
/* 1850 */             int minLevel = 6 + 2 * modifiers.getStrength();
/*      */             
/* 1852 */             victim.func_71024_bL().func_75122_a(minLevel, minLevel);
/*      */           }
/*      */           else {
/* 1855 */             int minLevel = 10 - modifiers.getStrength();
/* 1856 */             if (victim.func_71024_bL().func_75116_a() > minLevel) {
/* 1857 */               victim.func_71024_bL().func_75122_a(-minLevel, 2.0F);
/*      */             }
/* 1859 */             victim.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, hungerTicks, strength));
/* 1860 */             victim.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, poisonTicks, 0));
/*      */           }
/*      */         }
/* 1863 */         else if (modifiers.inverted) {
/* 1864 */           targetEntity.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, TimeUtil.secsToTicks(30), modifiers.getStrength()));
/*      */         }
/*      */         else {
/* 1867 */           targetEntity.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, hungerTicks, strength));
/* 1868 */           targetEntity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, poisonTicks));
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1873 */     });
/* 1874 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemOdourOfPurity.getBrewItemKey(), new BrewNamePart("witchery:brew.revealing"), new AltarPower(100), new Probability(1.0D), new EffectLevel(4))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 1879 */         boolean doDamage = false;
/* 1880 */         if (targetEntity.func_70644_a(Potion.field_76441_p)) {
/* 1881 */           targetEntity.func_82170_o(Potion.field_76441_p.field_76415_H);
/*      */         }
/*      */         
/* 1884 */         if (((targetEntity instanceof EntityPlayer)) && (targetEntity.func_82150_aj())) {
/* 1885 */           targetEntity.func_82142_c(false);
/*      */         }
/*      */         
/* 1888 */         if ((targetEntity instanceof EntityPlayer)) {
/* 1889 */           EntityPlayer player = (EntityPlayer)targetEntity;
/* 1890 */           ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 1891 */           if ((playerEx != null) && (playerEx.getCreatureType() == TransformCreature.PLAYER)) {
/* 1892 */             ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
/* 1893 */             Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
/*      */           }
/*      */         }
/*      */         
/* 1897 */         if (((targetEntity instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)targetEntity).isObscured()))
/*      */         {
/* 1899 */           ((EntitySummonedUndead)targetEntity).setObscured(false);
/*      */         }
/* 1901 */         int strength = modifiers.getStrength();
/* 1902 */         if ((doDamage) && (strength > 0)) {
/* 1903 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), strength);
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1908 */     });
/* 1909 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemFoulFume.getBrewItemKey(), new BrewNamePart("witchery:potion.stoutbelly"), new AltarPower(1000), new Probability(1.0D), Witchery.Potions.STOUT_BELLY, TimeUtil.secsToTicks(90), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1913 */     register(new BrewActionBlight(new BrewItemKey(Items.field_151170_bI), new BrewNamePart("witchery:brew.blight"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(4)));
/*      */     
/*      */ 
/* 1916 */     register(new BrewActionTranspose(new BrewItemKey(Items.field_151079_bi), new BrewNamePart("witchery:brew.transpose"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(4)));
/*      */     
/*      */ 
/* 1919 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151042_j), new BrewNamePart("witchery:brew.transposeore"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(4))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 1924 */         int depth = radius + modifiers.strength;
/* 1925 */         Block[] blockTypes = { Blocks.field_150366_p, Blocks.field_150352_o, Blocks.field_150369_x, Blocks.field_150412_bA };
/*      */         
/* 1927 */         slurpOres(world, x, y, z, radius, depth, blockTypes, modifiers, y + 2);
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 1933 */         int r = ritualModifiers.covenSize + radius;
/* 1934 */         int maxDepth = 4 * ritualModifiers.covenSize * (1 + modifiers.getStrength());
/* 1935 */         int steps = 4;
/* 1936 */         Block[] blockTypes = { Blocks.field_150366_p, Blocks.field_150352_o, Blocks.field_150369_x, Blocks.field_150412_bA };
/*      */         
/*      */ 
/* 1939 */         slurpOres(world, x, y - (ritualModifiers.pulses - 1) * 4, z, r, 4, blockTypes, modifiers, y + 2);
/*      */         
/*      */ 
/* 1942 */         ParticleEffect.FLAME.send(SoundEffect.FIREWORKS_BLAST1, world, x, y, z, 1.0D, 1.0D, 16);
/*      */         
/* 1944 */         ritualModifiers.setRitualStatus(ritualModifiers.pulses * 4 < maxDepth ? RitualStatus.ONGOING : RitualStatus.COMPLETE);
/*      */       }
/*      */       
/*      */ 
/*      */       private void slurpOres(World world, int posX, int posY, int posZ, int radius, int depth, Block[] blockTypes, ModifiersEffect modifiers, int returnY)
/*      */       {
/* 1950 */         int r = radius;
/* 1951 */         int maxType = modifiers.getStrength();
/* 1952 */         for (int x = posX - r; x <= posX + r; x++) {
/* 1953 */           for (int z = posZ - r; z <= posZ + r; z++) {
/* 1954 */             for (int y = posY - depth; y <= posY + r; y++) {
/* 1955 */               Block blockID = world.func_147439_a(x, y, z);
/*      */               
/* 1957 */               for (int t = 0; (t < blockTypes.length) && (t < maxType); t++) {
/* 1958 */                 if (blockID == blockTypes[t]) {
/* 1959 */                   ItemStack newStack = new ItemStack(blockID);
/* 1960 */                   EntityItem entity = new EntityItem(world, posX + 0.5D, posY + 0.5D, posZ + 0.5D, newStack);
/*      */                   
/* 1962 */                   if (!world.field_72995_K) {
/* 1963 */                     world.func_147468_f(x, y, z);
/* 1964 */                     world.func_72838_d(entity);
/*      */                   }
/*      */                   
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/* 1973 */     });
/* 1974 */     register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150329_H, 0), new BrewNamePart("witchery:potion.volatility"), new AltarPower(1000), new Probability(1.0D), Witchery.Potions.VOLATILITY, TimeUtil.secsToTicks(180), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1978 */     register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150330_I), new BrewNamePart("witchery:potion.volatility"), new AltarPower(1000), new Probability(1.0D), Witchery.Potions.VOLATILITY, TimeUtil.secsToTicks(180), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1982 */     register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150425_aM), new BrewNamePart("witchery:brew.allergydark"), new AltarPower(4000), new Probability(1.0D), Witchery.Potions.DARKNESS_ALLERGY, TimeUtil.minsToTicks(2), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1986 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemWhiffOfMagic.getBrewItemKey(), new BrewNamePart("witchery:potion.absorbmagic"), new AltarPower(2000), new Probability(1.0D), Witchery.Potions.ABSORB_MAGIC, TimeUtil.secsToTicks(60), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/* 1990 */     register(new BrewActionRaising(Items.field_151103_aS, new AltarPower(2000), new EffectLevel(4)));
/*      */     
/* 1992 */     register(new BrewActionRaiseLand(new BrewItemKey(Items.field_151128_bU), new BrewNamePart("witchery:brew.raiseland"), new AltarPower(2000), new EffectLevel(4)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1998 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150432_aD), new BrewNamePart("witchery:brew.harmdemons"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 2003 */         int strength = Math.min(modifiers.getStrength(), modifiers.strengthCeilingDisabled ? 3 : 1);
/* 2004 */         if ((targetEntity instanceof EntityLeonard)) {
/* 2005 */           ((EntityLeonard)targetEntity).attackEntityFromWeakness(MathHelper.func_76143_f((10 << strength) * modifiers.powerScalingFactor));
/*      */         }
/* 2007 */         else if (CreatureUtil.isDemonic(targetEntity)) {
/* 2008 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((10 << strength) * modifiers.powerScalingFactor));
/*      */         }
/*      */         else
/*      */         {
/* 2012 */           targetEntity.func_70097_a(DamageSource.func_76354_b(modifiers.caster, modifiers.caster), MathHelper.func_76143_f((3 << strength) * modifiers.powerScalingFactor));
/*      */ 
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 2019 */     });
/* 2020 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemFrozenHeart.getBrewItemKey(), new BrewNamePart("witchery:brew.iceshell"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 2025 */         createSphere(world, modifiers, BlockUtil.getClosestPlantableBlock(world, x, y, z, side, modifiers.caster));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 2032 */         boolean resistent = ((targetEntity instanceof EntityDemon)) || ((targetEntity instanceof EntityBlaze)) || ((targetEntity instanceof net.minecraft.entity.boss.IBossDisplayData)) || ((targetEntity instanceof com.emoniph.witchery.entity.EntityEnt)) || ((targetEntity instanceof net.minecraft.entity.monster.EntityIronGolem));
/*      */         
/*      */ 
/* 2035 */         if (!resistent) {
/* 2036 */           BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Witchery.Potions.CHILLED, TimeUtil.secsToTicks(10), modifiers.noParticles, modifiers.caster);
/*      */           
/* 2038 */           if (!modifiers.isGlancing) {
/* 2039 */             createSphere(world, modifiers, new Coord(targetEntity));
/*      */           }
/* 2041 */         } else if (targetEntity.field_71093_bK != -1) {
/* 2042 */           Coord coord = new Coord(targetEntity);
/* 2043 */           BlockUtil.setBlockIfReplaceable(world, coord.x, coord.y, coord.z, Blocks.field_150358_i);
/*      */         }
/*      */       }
/*      */       
/*      */       public void createSphere(final World world, ModifiersEffect modifiers, final Coord coord) {
/* 2048 */         if (coord != null) {
/* 2049 */           final int iceRadius = modifiers.getStrength() + (modifiers.getStrength() > 3 ? 2 : 1);
/* 2050 */           new BlockActionSphere() {
/*      */             protected void onBlock(World world, int x, int y, int z) {
/* 2052 */               BlockUtil.setBlockIfReplaceable(world, x, y, z, Witchery.Blocks.PERPETUAL_ICE);
/*      */             }
/*      */             
/*      */ 
/* 2056 */             protected void onComplete() { fillWith(world, coord.x, coord.y, coord.z, iceRadius, Blocks.field_150350_a, Witchery.Blocks.PERPETUAL_ICE); } }.drawHollowSphere(world, coord.x, coord.y, coord.z, iceRadius);
/*      */ 
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 2063 */     });
/* 2064 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemSpectralDust.getBrewItemKey(), new BrewNamePart("witchery:potion.reflectdamage"), new AltarPower(2000), new Probability(1.0D), Witchery.Potions.REFLECT_DAMAGE, TimeUtil.secsToTicks(90), new EffectLevel(5)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2069 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemRefinedEvil.getBrewItemKey(), new BrewNamePart("witchery:brew.hellgate"), new AltarPower(3000), new Probability(1.0D), new EffectLevel(5))
/*      */     {
/*      */ 
/*      */       public boolean isRitualTargetLocationValid(MinecraftServer server, World world, int x, int y, int z, BlockPosition target, ModifiersRitual modifiers)
/*      */       {
/*      */ 
/* 2075 */         return CircleUtil.isMediumCircle(target.getWorld(server), target.x, target.y, target.z, Witchery.Blocks.GLYPH_INFERNAL);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 2082 */         InfusionInfernal.spawnCreature(world, EntityDemon.class, x, y, z, null, 0, 2, ParticleEffect.FLAME, SoundEffect.MOB_ENDERDRAGON_GROWL);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack) {}
/*      */       
/*      */ 
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 2094 */         BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Witchery.Potions.NETHER_BOUND, TimeUtil.minsToTicks(3), modifiers.noParticles, modifiers.caster);
/*      */       }
/*      */       
/*      */ 
/* 2098 */     });
/* 2099 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151043_k), new BrewNamePart("witchery:brew.blast"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 2104 */         if ((modifiers.powerScalingFactor == 1.0D) || (world.field_73012_v.nextDouble() < modifiers.powerScalingFactor * 0.2D))
/*      */         {
/* 2106 */           world.func_72876_a(modifiers.caster, targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, modifiers.getStrength(), true);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 2114 */         world.func_72876_a(modifiers.caster, x + side.offsetX + 0.5D, y + side.offsetY + 0.5D, z + side.offsetZ + 0.5D, modifiers.getStrength(), true);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/* 2119 */     });
/* 2120 */     register(new BrewActionEffect(new BrewItemKey(Blocks.field_150398_cm), new BrewNamePart("witchery:brew.poisontoad"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 2125 */         if (((!(targetEntity instanceof EntityToad)) && (modifiers.powerScalingFactor == 1.0D)) || (world.field_73012_v.nextDouble() < modifiers.powerScalingFactor * 0.2D))
/*      */         {
/* 2127 */           EntityToad toad = new EntityToad(world);
/* 2128 */           toad.func_70012_b(targetEntity.field_70165_t, targetEntity.field_70163_u + targetEntity.field_70131_O + 1.0D, targetEntity.field_70161_v, 0.0F, 0.0F);
/*      */           
/* 2130 */           toad.setTimeToLive(60 + modifiers.getStrength() * 40, true);
/* 2131 */           world.func_72838_d(toad);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 2138 */         EntityToad toad = new EntityToad(world);
/* 2139 */         toad.func_70012_b(0.5D + x, 2.5D + y, 0.5D + z, 0.0F, 0.0F);
/* 2140 */         toad.setTimeToLive(60 + modifiers.getStrength() * 40, true);
/* 2141 */         world.func_72838_d(toad);
/*      */       }
/*      */       
/*      */ 
/* 2145 */     });
/* 2146 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151061_bv), new BrewNamePart("witchery:brew.iceworld"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(5))
/*      */     {
/*      */ 
/*      */       protected void doApplyToBlock(World world, int x, int y0, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/*      */ 
/* 2152 */         if (!world.field_72995_K) {
/* 2153 */           new BlockActionSphere()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z) {
/* 2156 */               Block block = world.func_147439_a(x, y, z);
/* 2157 */               if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */               {
/*      */ 
/* 2160 */                 int meta = world.func_72805_g(x, y, z);
/* 2161 */                 if (block == Blocks.field_150466_ao) {
/* 2162 */                   int i1 = ((BlockDoor)block).func_150012_g(world, x, y, z);
/* 2163 */                   if ((i1 & 0x8) != 0) {
/* 2164 */                     y--;
/*      */                   }
/* 2166 */                   world.func_147468_f(x, y, z);
/* 2167 */                   world.func_147468_f(x, y + 1, z);
/* 2168 */                   net.minecraft.item.ItemDoor.func_150924_a(world, x, y, z, i1 & 0x3, Witchery.Blocks.PERPETUAL_ICE_DOOR);
/*      */                 }
/*      */                 else {
/* 2171 */                   return;
/*      */                 }
/* 2173 */                 ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, y + 1, z, 0.25D, 0.25D, 16); } } }.drawFilledSphere(world, x, y0, z, (int)Math.ceil(Math.max(radius * 1.5D, 1.0D)));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/* 2178 */           new BlockActionSphere()
/*      */           {
/*      */             public void onBlock(World world, int x, int y, int z) {
/* 2181 */               Block block = world.func_147439_a(x, y, z);
/* 2182 */               if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifiers.caster)) && (BlockProtect.canBreak(block, world)))
/*      */               {
/*      */ 
/* 2185 */                 int meta = world.func_72805_g(x, y, z);
/* 2186 */                 if ((block == Blocks.field_150346_d) || (block == Blocks.field_150349_c) || (block == Blocks.field_150391_bh) || (block == Blocks.field_150354_m))
/*      */                 {
/* 2188 */                   world.func_147449_b(x, y, z, Blocks.field_150433_aE);
/* 2189 */                 } else if ((block == Blocks.field_150347_e) || (block == Blocks.field_150341_Y) || (block == Blocks.field_150364_r) || (block == Blocks.field_150363_s) || (block == Witchery.Blocks.LOG))
/*      */                 {
/*      */ 
/* 2192 */                   world.func_147449_b(x, y, z, Blocks.field_150403_cj);
/* 2193 */                 } else if ((block == Blocks.field_150348_b) || (block == Blocks.field_150417_aV) || (block == Blocks.field_150336_V) || (block == Blocks.field_150344_f) || (block == Blocks.field_150362_t) || (block == Blocks.field_150361_u) || (block == Witchery.Blocks.LEAVES) || (block == Witchery.Blocks.PLANKS) || (block == Blocks.field_150322_A))
/*      */                 {
/*      */ 
/*      */ 
/*      */ 
/* 2198 */                   world.func_147449_b(x, y, z, Witchery.Blocks.PERPETUAL_ICE);
/* 2199 */                 } else if ((block == Blocks.field_150456_au) || (block == Blocks.field_150452_aw))
/*      */                 {
/* 2201 */                   world.func_147449_b(x, y, z, Witchery.Blocks.PERPETUAL_ICE_PRESSURE_PLATE);
/* 2202 */                 } else if ((block == Blocks.field_150446_ar) || (block == Blocks.field_150389_bf) || (block == Blocks.field_150390_bg) || (block == Blocks.field_150476_ad) || (block == Blocks.field_150485_bF) || (block == Blocks.field_150372_bz) || (block == Blocks.field_150487_bG) || (block == Blocks.field_150481_bH) || (block == Blocks.field_150401_cl) || (block == Blocks.field_150400_ck) || (block == Witchery.Blocks.STAIRS_ALDER) || (block == Witchery.Blocks.STAIRS_HAWTHORN) || (block == Witchery.Blocks.STAIRS_ROWAN))
/*      */                 {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2210 */                   world.func_147465_d(x, y, z, Witchery.Blocks.PERPETUAL_ICE_STAIRS, meta, 3);
/* 2211 */                 } else if ((block == Blocks.field_150333_U) || (block == Blocks.field_150376_bx) || (block == Witchery.Blocks.WOOD_SLAB_SINGLE))
/*      */                 {
/* 2213 */                   world.func_147449_b(x, y, z, Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE);
/* 2214 */                 } else if ((block == Blocks.field_150334_T) || (block == Blocks.field_150373_bw) || (block == Witchery.Blocks.WOOD_SLAB_DOUBLE))
/*      */                 {
/* 2216 */                   world.func_147449_b(x, y, z, Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE);
/* 2217 */                 } else if ((block == Blocks.field_150422_aJ) || (block == Blocks.field_150463_bK)) {
/* 2218 */                   world.func_147449_b(x, y, z, Witchery.Blocks.PERPETUAL_ICE_FENCE);
/* 2219 */                 } else if (block == Blocks.field_150396_be) {
/* 2220 */                   world.func_147449_b(x, y, z, Witchery.Blocks.PERPETUAL_ICE_FENCE_GATE);
/* 2221 */                 } else if (block == Witchery.Blocks.STOCKADE) {
/* 2222 */                   world.func_147449_b(x, y, z, Witchery.Blocks.STOCKADE_ICE);
/*      */                 } else {
/* 2224 */                   return;
/*      */                 }
/* 2226 */                 ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, y + 1, z, 0.25D, 0.25D, 16); } } }.drawFilledSphere(world, x, y0, z, (int)Math.ceil(Math.max(radius * 1.5D, 1.0D)));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/* 2231 */           SoundEffect.RANDOM_ORB.playAt(world, x, y0, z, 0.5F, 2.0F);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 2237 */     });
/* 2238 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemCondensedFear.getBrewItemKey(), new BrewNamePart("witchery:brew.drainmagic"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(6))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 2243 */         Witchery.modHooks.reducePowerLevels(targetEntity, 0.25F * (1.0F + modifiers.getStrength()) * (float)modifiers.powerScalingFactor);
/*      */       }
/*      */       
/*      */ 
/* 2247 */     });
/* 2248 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151119_aD), new BrewNamePart("witchery:potion.fortune"), new AltarPower(1000), new Probability(1.0D), Witchery.Potions.FORTUNE, TimeUtil.minsToTicks(3), new EffectLevel(6)));
/*      */     
/*      */ 
/*      */ 
/* 2252 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151115_aP, 1), new BrewNamePart("witchery:brew.allergysun"), new AltarPower(1000), new Probability(1.0D), Witchery.Potions.SUN_ALLERGY, TimeUtil.secsToTicks(60), new EffectLevel(6)));
/*      */     
/*      */ 
/*      */ 
/* 2256 */     register(new BrewPotionEffect(new BrewItemKey(Witchery.Blocks.BRAMBLE, 1), new BrewNamePart("witchery:potion.illfitting").setBaseDuration(TimeUtil.secsToTicks(6)), new AltarPower(8000), new Probability(1.0D), Witchery.Potions.ILL_FITTING, TimeUtil.secsToTicks(6), new EffectLevel(6)));
/*      */     
/*      */ 
/*      */ 
/* 2260 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemHintOfRebirth.getBrewItemKey(), new BrewNamePart("witchery:brew.reincarnate"), new AltarPower(2500), new Probability(1.0D), Witchery.Potions.REINCARNATE, TimeUtil.minsToTicks(3), new EffectLevel(6)));
/*      */     
/*      */ 
/*      */ 
/* 2264 */     register(new BrewActionEffect(Witchery.Items.GENERIC.itemCreeperHeart.getBrewItemKey(), new BrewNamePart("witchery:brew.durationboost"), new AltarPower(5000), new Probability(1.0D), new EffectLevel(6))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase target, ModifiersEffect modifiers, ItemStack actionStack)
/*      */       {
/* 2269 */         if (target.func_70644_a(Witchery.Potions.QUEASY)) {
/* 2270 */           if (target.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 2271 */             target.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, TimeUtil.minsToTicks(2), 0, true));
/*      */           }
/*      */           else {
/* 2274 */             target.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, TimeUtil.minsToTicks(5), 0, true));
/*      */           }
/*      */         }
/*      */         else {
/* 2278 */           Collection<PotionEffect> potionEffects = target.func_70651_bq();
/* 2279 */           if (!potionEffects.isEmpty()) {
/* 2280 */             List<PotionEffect> newEffects = new ArrayList();
/* 2281 */             int durationBoost = MathHelper.func_76143_f(modifiers.powerScalingFactor * TimeUtil.minsToTicks(3));
/*      */             
/* 2283 */             for (PotionEffect potionEffect : potionEffects) {
/* 2284 */               if (!Potion.field_76425_a[potionEffect.func_76456_a()].func_76403_b()) {
/* 2285 */                 int remainingTicks = potionEffect.func_76459_b();
/* 2286 */                 int newDuration = remainingTicks + Math.min(remainingTicks, durationBoost);
/* 2287 */                 newEffects.add(new PotionEffect(potionEffect.func_76456_a(), newDuration, potionEffect.func_76458_c(), potionEffect.func_82720_e()));
/*      */               }
/*      */             }
/*      */             
/*      */ 
/* 2292 */             target.func_70674_bp();
/* 2293 */             for (PotionEffect potionEffect : newEffects) {
/* 2294 */               target.func_70690_d(potionEffect);
/*      */             }
/*      */             
/* 2297 */             int mins = 3 * Math.max(1, 4 - modifiers.getStrength());
/* 2298 */             target.func_70690_d(new PotionEffect(Witchery.Potions.QUEASY.field_76415_H, TimeUtil.minsToTicks(mins), 0, true));
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/* 2304 */     });
/* 2305 */     register(new BrewPotionEffect(new BrewItemKey(Items.field_151166_bC), new BrewNamePart("witchery:brew.resizing"), new AltarPower(2500), new Probability(1.0D), Witchery.Potions.RESIZING, TimeUtil.secsToTicks(20), new EffectLevel(6)));
/*      */     
/*      */ 
/*      */ 
/* 2309 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151144_bL, 0), new BrewNamePart("witchery:brew.stealbuffs"), new AltarPower(100), new Probability(1.0D), new EffectLevel(6))
/*      */     {
/*      */ 
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/*      */ 
/* 2316 */         double radius = (modifiers.getStrength() + 1) * 8;
/* 2317 */         double radiusSq = radius * radius;
/* 2318 */         AxisAlignedBB bounds = targetEntity.field_70121_D.func_72314_b(radius, radius, radius);
/*      */         
/* 2320 */         int maxBuffs = (int)Math.ceil((modifiers.getStrength() + 1.0D) * 2.0D * modifiers.powerScalingFactor);
/*      */         
/*      */ 
/* 2323 */         List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, bounds);
/*      */         
/* 2325 */         for (EntityLivingBase otherEntity : entities) {
/* 2326 */           if ((otherEntity != targetEntity) && (otherEntity.func_70068_e(targetEntity) < radiusSq)) {
/* 2327 */             Collection<PotionEffect> potionEffects = otherEntity.func_70651_bq();
/* 2328 */             List<Integer> effectsToRemove = new ArrayList();
/* 2329 */             for (PotionEffect effect : potionEffects) {
/* 2330 */               Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/* 2331 */               if ((!PotionBase.isDebuff(potion)) && (PotionBase.isCurable(potion)) && (effect.func_76458_c() <= modifiers.getStrength()))
/*      */               {
/* 2333 */                 PotionEffect myEffect = targetEntity.func_70660_b(potion);
/* 2334 */                 if (myEffect != null) {
/* 2335 */                   if ((myEffect.func_76459_b() <= effect.func_76459_b()) && (myEffect.func_76458_c() <= effect.func_76458_c()))
/*      */                   {
/* 2337 */                     targetEntity.func_70690_d(new PotionEffect(effect));
/* 2338 */                     effectsToRemove.add(Integer.valueOf(effect.func_76456_a()));
/* 2339 */                     maxBuffs--;
/*      */                   }
/*      */                 } else {
/* 2342 */                   targetEntity.func_70690_d(new PotionEffect(effect));
/* 2343 */                   effectsToRemove.add(Integer.valueOf(effect.func_76456_a()));
/* 2344 */                   maxBuffs--;
/*      */                 }
/* 2346 */                 if (maxBuffs <= 0) {
/*      */                   break;
/*      */                 }
/*      */               }
/*      */             }
/* 2351 */             for (Iterator i$ = effectsToRemove.iterator(); i$.hasNext();) { int id = ((Integer)i$.next()).intValue();
/* 2352 */               otherEntity.func_82170_o(id);
/*      */             }
/* 2354 */             if (maxBuffs <= 0) {
/*      */               break;
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/* 2363 */     });
/* 2364 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemFocusedWill.getBrewItemKey(), new BrewNamePart("witchery:brew.keepinventory"), new AltarPower(10000), new Probability(1.0D), Witchery.Potions.KEEP_INVENTORY, TimeUtil.minsToTicks(6), new EffectLevel(8)));
/*      */     
/*      */ 
/*      */ 
/* 2368 */     register(new BrewPotionEffect(Witchery.Items.GENERIC.itemRedstoneSoup.getBrewItemKey(), new BrewNamePart("witchery:potion.keepeffects"), new AltarPower(10000), new Probability(1.0D), Witchery.Potions.KEEP_EFFECTS, TimeUtil.minsToTicks(6), new EffectLevel(8)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2373 */     register(new com.emoniph.witchery.brewing.action.effect.BrewActionBiomeChange(new BrewItemKey(Witchery.Items.BIOME_NOTE, 32767), new BrewNamePart("witchery:brew.seasons"), new AltarPower(5000), new Probability(1.0D), new EffectLevel(8)));
/*      */     
/*      */ 
/*      */ 
/* 2377 */     register(new BrewActionEffect(new BrewItemKey(Items.field_151144_bL, 4), new BrewNamePart("witchery:brew.spreaddebuffs"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(8))
/*      */     {
/*      */ 
/*      */       protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
/*      */       {
/* 2382 */         int strength = modifiers.getStrength();
/* 2383 */         double radius = (strength + 1) * 4;
/* 2384 */         double radiusSq = radius * radius;
/* 2385 */         AxisAlignedBB bounds = targetEntity.field_70121_D.func_72314_b(radius, radius, radius);
/*      */         
/* 2387 */         int maxBuffs = (int)Math.ceil((strength + 1.0D) * modifiers.powerScalingFactor);
/* 2388 */         List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, bounds);
/*      */         
/* 2390 */         List<EntityLivingBase> others = new ArrayList();
/* 2391 */         for (EntityLivingBase otherEntity : entities) {
/* 2392 */           if ((otherEntity != targetEntity) && (otherEntity.func_70068_e(targetEntity) < radiusSq)) {
/* 2393 */             others.add(otherEntity);
/*      */           }
/*      */         }
/*      */         
/* 2397 */         Collection<PotionEffect> effects = targetEntity.func_70651_bq();
/* 2398 */         List<Integer> effectsToRemove = new ArrayList();
/* 2399 */         for (PotionEffect effect : effects) {
/* 2400 */           Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/* 2401 */           if ((PotionBase.isDebuff(potion)) && (PotionBase.isCurable(potion)) && (effect.func_76458_c() <= Math.max(strength - 1, 1)))
/*      */           {
/* 2403 */             effectsToRemove.add(Integer.valueOf(potion.field_76415_H));
/* 2404 */             for (EntityLivingBase other : others) {
/* 2405 */               other.func_70690_d(new PotionEffect(effect));
/*      */             }
/* 2407 */             maxBuffs--;
/* 2408 */             if (maxBuffs <= 0) {
/*      */               break;
/*      */             }
/*      */           }
/*      */         }
/* 2413 */         for (Iterator i$ = effectsToRemove.iterator(); i$.hasNext();) { int id = ((Integer)i$.next()).intValue();
/* 2414 */           targetEntity.func_82170_o(id);
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 2419 */     });
/* 2420 */     register(new BrewActionRitualSummonMob(new BrewItemKey(Witchery.Items.WITCH_HAT), new AltarPower(10000), new BrewActionRitualSummonMob.Recipe[] { new BrewActionRitualSummonMob.Recipe(EntityLeonard.class, new ItemStack[] { new ItemStack(Items.field_151075_bm), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151156_bN) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2427 */     register(new BrewActionModifier(Witchery.Items.GENERIC.itemWaystoneBound.getBrewItemKey(), null, new AltarPower(100))
/*      */     {
/*      */       public void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack)
/*      */       {
/* 2431 */         modifiers.setTarget(stack);
/*      */       }
/*      */       
/*      */ 
/* 2435 */     });
/* 2436 */     register(new BrewActionRitual(Witchery.Items.GENERIC.itemDogTongue.getBrewItemKey(), new AltarPower(250), true));
/*      */     
/*      */ 
/*      */ 
/* 2440 */     register(new BrewActionRitualEntityTarget(new BrewItemKey(Witchery.Items.TAGLOCK_KIT, 32767), new AltarPower(1000)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2445 */     register(new BrewActionRitualRecipe(new BrewItemKey(Witchery.Items.CHALK_RITUAL), new AltarPower(2000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.CHALK_OTHERWHERE), new ItemStack[] { new ItemStack(Items.field_151075_bm), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Items.field_151079_bi) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.CHALK_GOLDEN), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151074_bl) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.CHALK_INFERNAL), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151065_br) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2454 */     register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151110_aK), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(Witchery.Items.GENERIC.itemMutandis.createStack(6), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack() }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2459 */     register(new BrewActionRitualRecipe(Witchery.Items.GENERIC.itemMutandis.getBrewItemKey(), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151075_bm), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), Witchery.Items.GENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151015_O) }), new BrewActionRitualRecipe.Recipe(Witchery.Items.GENERIC.itemMutandisExtremis.createStack(), new ItemStack[] { new ItemStack(Items.field_151075_bm) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2468 */     register(new BrewActionRitualRecipe(Witchery.Items.GENERIC.itemMutandisExtremis.getBrewItemKey(), new AltarPower(1800), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Blocks.field_150377_bs, 2), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Blocks.field_150348_b), new ItemStack(Blocks.field_150377_bs) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.MUTATING_SPRIG), new ItemStack[] { new ItemStack(Items.field_151075_bm), Witchery.Items.GENERIC.itemBranchEnt.createStack() }), new BrewActionRitualRecipe.Recipe(Witchery.Items.GENERIC.itemDropOfLuck.createStack(), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151075_bm), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), Witchery.Items.GENERIC.itemRefinedEvil.createStack() }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2479 */     register(new BrewActionRitualRecipe(new BrewItemKey(Blocks.field_150433_aE), new AltarPower(3000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_ENDLESS_WATER, 1, 0), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_ENDLESS_WATER, 1, 50), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_ENDLESS_WATER, 1, 80), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151114_aO) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_ENDLESS_WATER, 1, 95), new ItemStack[] { new ItemStack(Items.field_151075_bm) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2490 */     register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151044_h, 1), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_FUEL, 1, 3), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj), Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack() }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_FUEL, 1, 2), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_FUEL, 1, 1), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151114_aO) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.BREW_FUEL, 1, 0), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack() }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2505 */     register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151147_al), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151157_am), new ItemStack[0]) }));
/*      */     
/*      */ 
/* 2508 */     register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151076_bf), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151077_bg), new ItemStack[0]) }));
/*      */     
/*      */ 
/* 2511 */     register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151082_bd), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151083_be), new ItemStack[0]) }));
/*      */     
/*      */ 
/* 2514 */     register(new BrewActionRitualRecipe(Witchery.Items.GENERIC.itemOddPorkRaw.getBrewItemKey(), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(Witchery.Items.GENERIC.itemOddPorkCooked.createStack(), new ItemStack[0]) }));
/*      */     
/*      */ 
/*      */ 
/* 2518 */     register(new BrewActionRitualRecipe(Witchery.Items.GENERIC.itemMuttonRaw.getBrewItemKey(), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(Witchery.Items.GENERIC.itemMuttonCooked.createStack(), new ItemStack[0]) }));
/*      */     
/*      */ 
/* 2521 */     register(new BrewActionRitualRecipe(new BrewItemKey(Witchery.Items.WITCH_HAND), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151078_bh, 6), new ItemStack[0]) }));
/*      */     
/*      */ 
/* 2524 */     register(new BrewActionRitualRecipe(Witchery.Items.GENERIC.itemTormentedTwine.getBrewItemKey(), new AltarPower(4000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Blocks.PIT_GRASS, 4), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Blocks.field_150346_d), new ItemStack(Blocks.field_150327_N) }), new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Blocks.PIT_DIRT, 4), new ItemStack[] { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), new ItemStack(Blocks.field_150346_d) }) }));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2531 */     register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151111_aL), new AltarPower(5000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Witchery.Items.PLAYER_COMPASS), new ItemStack[] { new ItemStack(Items.field_151075_bm), Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Blocks.field_150395_bd), new ItemStack(Items.field_151070_bp) }) }));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2537 */   private final Hashtable<BrewItemKey, BrewAction> ingredients = new Hashtable();
/* 2538 */   private final List<BrewActionRitualRecipe> recipes = new ArrayList();
/*      */   
/*      */   public List<BrewActionRitualRecipe> getRecipes() {
/* 2541 */     return this.recipes;
/*      */   }
/*      */   
/*      */   private BrewAction register(BrewAction ingredient) {
/* 2545 */     if (!this.ingredients.containsKey(ingredient.ITEM_KEY)) {
/* 2546 */       this.ingredients.put(ingredient.ITEM_KEY, ingredient);
/*      */       
/* 2548 */       if ((ingredient instanceof BrewActionRitualRecipe)) {
/* 2549 */         this.recipes.add((BrewActionRitualRecipe)ingredient);
/*      */       }
/*      */     }
/* 2552 */     return ingredient;
/*      */   }
/*      */   
/*      */   public int getAltarPower(ItemStack stack) {
/* 2556 */     BrewItemKey key = BrewItemKey.fromStack(stack);
/* 2557 */     if (key != null) {
/* 2558 */       BrewAction action = (BrewAction)this.ingredients.get(key);
/* 2559 */       if (action != null) {
/* 2560 */         AltarPower power = new AltarPower(0);
/* 2561 */         action.accumulatePower(power);
/* 2562 */         return power.getPower();
/*      */       }
/*      */     }
/* 2565 */     return -1;
/*      */   }
/*      */   
/*      */   public BrewAction getActionForItemStack(ItemStack stack) {
/* 2569 */     return (BrewAction)this.ingredients.get(BrewItemKey.fromStack(stack));
/*      */   }
/*      */   
/*      */   public int getBrewColor(NBTTagCompound nbtRoot) {
/* 2573 */     return nbtRoot != null ? nbtRoot.func_74762_e("Color") : -16744448;
/*      */   }
/*      */   
/*      */   public AltarPower getPowerRequired(NBTTagCompound nbtBrew) {
/* 2577 */     BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2578 */     AltarPower totalPower = new AltarPower(0);
/* 2579 */     for (BrewAction action : actionList.actions) {
/* 2580 */       action.accumulatePower(totalPower);
/*      */     }
/* 2582 */     return totalPower;
/*      */   }
/*      */   
/*      */   public boolean isSplash(NBTTagCompound nbtBrew) {
/* 2586 */     if ((nbtBrew != null) && (!nbtBrew.func_74764_b("Splash"))) {
/* 2587 */       nbtBrew.func_74757_a("Splash", false);
/* 2588 */       BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2589 */       for (BrewAction action : actionList.actions) {
/* 2590 */         if (action.createsSplash()) {
/* 2591 */           nbtBrew.func_74757_a("Splash", true);
/* 2592 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 2597 */     return (nbtBrew != null) && (nbtBrew.func_74767_n("Splash"));
/*      */   }
/*      */   
/*      */   public String getBrewName(NBTTagCompound nbtRoot) {
/* 2601 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2602 */     BrewNameBuilder nameBuilder = new BrewNameBuilder(true);
/* 2603 */     for (BrewAction action : actionList.actions) {
/* 2604 */       BrewNamePart name = action.getNamePart();
/* 2605 */       if (name != null) {
/* 2606 */         name.applyTo(nameBuilder);
/*      */       }
/*      */     }
/* 2609 */     return nameBuilder.toString();
/*      */   }
/*      */   
/*      */   public String getBrewInformation(NBTTagCompound nbtRoot) {
/* 2613 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2614 */     BrewNameBuilder nameBuilder = new BrewNameBuilder(false);
/* 2615 */     for (BrewAction action : actionList.actions) {
/* 2616 */       BrewNamePart name = action.getNamePart();
/* 2617 */       if (name != null) {
/* 2618 */         name.applyTo(nameBuilder);
/*      */       }
/*      */     }
/* 2621 */     String tooltip = nameBuilder.toString();
/* 2622 */     int drinkSpeed = getBrewDrinkSpeed(nbtRoot);
/* 2623 */     if (drinkSpeed != 32) {
/* 2624 */       if (drinkSpeed > 48) {
/* 2625 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryslow") });
/*      */       }
/* 2627 */       else if (drinkSpeed > 32) {
/* 2628 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.slow") });
/*      */       }
/* 2630 */       else if (drinkSpeed < 16) {
/* 2631 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryfast") });
/*      */       }
/* 2633 */       else if (drinkSpeed < 32) {
/* 2634 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.fast") });
/*      */       }
/*      */     }
/*      */     
/* 2638 */     return tooltip;
/*      */   }
/*      */   
/*      */   public void updateBrewInformation(NBTTagCompound nbtRoot) {
/* 2642 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2643 */     BrewNameBuilder nameBuilder = new BrewNameBuilder(false);
/* 2644 */     for (BrewAction action : actionList.actions) {
/* 2645 */       BrewNamePart name = action.getNamePart();
/* 2646 */       if (name != null) {
/* 2647 */         name.applyTo(nameBuilder);
/*      */       }
/*      */     }
/* 2650 */     String tooltip = nameBuilder.toString();
/* 2651 */     int drinkSpeed = getBrewDrinkSpeed(nbtRoot);
/* 2652 */     if (drinkSpeed != 32) {
/* 2653 */       if (drinkSpeed > 48) {
/* 2654 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryslow") });
/*      */       }
/* 2656 */       else if (drinkSpeed > 32) {
/* 2657 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.slow") });
/*      */       }
/* 2659 */       else if (drinkSpeed < 16) {
/* 2660 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryfast") });
/*      */       }
/* 2662 */       else if (drinkSpeed < 32) {
/* 2663 */         tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.fast") });
/*      */       }
/*      */     }
/*      */     
/* 2667 */     nbtRoot.func_74778_a("BrewInfo", tooltip);
/*      */     
/* 2669 */     EffectLevelCounter effectCounter = new EffectLevelCounter();
/* 2670 */     boolean actionFound = false;
/* 2671 */     for (BrewAction action : actionList.actions) {
/* 2672 */       action.augmentEffectLevels(effectCounter);
/*      */     }
/*      */     
/* 2675 */     nbtRoot.func_74768_a("EffectCount", effectCounter.getEffectCount());
/* 2676 */     nbtRoot.func_74768_a("RemainingCapacity", effectCounter.remainingCapactiy());
/* 2677 */     nbtRoot.func_74768_a("UsedCapacity", effectCounter.usedCapacity());
/*      */   }
/*      */   
/*      */   public int getUsedCapacity(NBTTagCompound nbtRoot) {
/* 2681 */     if (nbtRoot != null) {
/* 2682 */       return nbtRoot.func_74762_e("UsedCapacity");
/*      */     }
/* 2684 */     return 0;
/*      */   }
/*      */   
/*      */   public int getBrewDrinkSpeed(NBTTagCompound nbtRoot) {
/* 2688 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2689 */     int drinkSpeed = 32;
/* 2690 */     for (BrewAction action : actionList.actions) {
/* 2691 */       drinkSpeed += action.getDrinkSpeedModifiers();
/*      */     }
/* 2693 */     return Math.max(drinkSpeed, 2);
/*      */   }
/*      */   
/*      */   public boolean canAdd(NBTTagCompound nbtRoot, BrewAction brewAction, boolean isCauldronFull) {
/* 2697 */     if (nbtRoot.func_74767_n("RitualTriggered")) {
/* 2698 */       return false;
/*      */     }
/*      */     
/* 2701 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2702 */     EffectLevelCounter effectCounter = new EffectLevelCounter();
/* 2703 */     boolean actionFound = false;
/* 2704 */     for (BrewAction action : actionList.actions) {
/* 2705 */       action.augmentEffectLevels(effectCounter);
/* 2706 */       if (action == brewAction) {
/* 2707 */         actionFound = true;
/* 2708 */       } else if (((action instanceof BrewActionEffect)) || ((action instanceof BrewPotionEffect))) {
/* 2709 */         actionFound = false;
/*      */       }
/*      */     }
/*      */     
/* 2713 */     if (!brewAction.canAdd(actionList, isCauldronFull, effectCounter.hasEffects())) {
/* 2714 */       return false;
/*      */     }
/*      */     
/* 2717 */     return (!actionFound) && (brewAction.augmentEffectLevels(effectCounter));
/*      */   }
/*      */   
/*      */   public EffectLevelCounter getBrewLevel(NBTTagCompound nbtRoot)
/*      */   {
/* 2722 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2723 */     EffectLevelCounter effectCounter = new EffectLevelCounter();
/*      */     
/* 2725 */     for (BrewAction action : actionList.actions) {
/* 2726 */       action.augmentEffectLevels(effectCounter);
/*      */     }
/*      */     
/*      */ 
/* 2730 */     return effectCounter;
/*      */   }
/*      */   
/*      */   public void nullifyItems(NBTTagCompound nbtRoot, NBTTagList nbtItems, BrewAction brewAction) {
/* 2734 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2735 */     actionList.nullifyItems(brewAction, nbtItems);
/*      */   }
/*      */   
/*      */   public void explodeBrew(World world, NBTTagCompound nbtRoot, Entity immuneEntity, double x, double y, double z) {
/* 2739 */     BrewActionList actionList = new BrewActionList(nbtRoot, this.ingredients);
/* 2740 */     world.func_72876_a(immuneEntity, x, y, z, Math.min(1.0F + actionList.size() * 0.5F, 10.0F), false);
/*      */   }
/*      */   
/*      */   public ModifierYield getYieldModifier(NBTTagCompound nbtBrew) {
/* 2744 */     BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2745 */     ModifierYield counter = new ModifierYield(0);
/* 2746 */     for (BrewAction action : actionList.actions) {
/* 2747 */       action.prepareYield(counter);
/*      */     }
/*      */     
/* 2750 */     return counter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RitualStatus updateRitual(MinecraftServer server, World world, int x, int y, int z, NBTTagCompound nbtBrew, int covenSize, int ritualPulses, boolean lennyPresent)
/*      */   {
/* 2757 */     BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2758 */     ModifiersRitual modifiers = new ModifiersRitual(new BlockPosition(world, x, y, z), covenSize, ritualPulses, lennyPresent);
/*      */     
/* 2760 */     actionList.prepareRitual(world, x, y, z, modifiers);
/* 2761 */     ModifiersImpact modifiersImpact = new ModifiersImpact(new EntityPosition(modifiers.getTarget()), true, modifiers.covenSize, null);
/*      */     
/* 2763 */     for (BrewAction action : actionList.actions) {
/* 2764 */       action.prepareSplashPotion(world, actionList, modifiersImpact);
/*      */     }
/* 2766 */     return actionList.getTopAction().updateRitual(server, actionList, world, x, y, z, modifiers, modifiersImpact);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean impactSplashPotion(World world, ItemStack stack, MovingObjectPosition mop, ModifiersImpact modifiers)
/*      */   {
/* 2772 */     return impactSplashPotion(world, new BrewActionList(stack.func_77978_p(), this.ingredients), mop, modifiers);
/*      */   }
/*      */   
/*      */   public boolean impactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
/*      */   {
/* 2777 */     return impactSplashPotion(world, new BrewActionList(nbtBrew, this.ingredients), mop, modifiers);
/*      */   }
/*      */   
/*      */   public boolean impactSplashPotion(World world, BrewActionList actionList, MovingObjectPosition mop, ModifiersImpact modifiers)
/*      */   {
/* 2782 */     for (BrewAction action : actionList.actions) {
/* 2783 */       action.prepareSplashPotion(world, actionList, modifiers);
/*      */     }
/* 2785 */     modifiers.getDispersal().onImpactSplashPotion(world, actionList.getTagCompound(), mop, modifiers);
/* 2786 */     return true;
/*      */   }
/*      */   
/*      */   public void applyToEntity(World world, EntityLivingBase targetEntity, NBTTagCompound nbtBrew, ModifiersEffect modifiers)
/*      */   {
/* 2791 */     BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2792 */     actionList.applyToEntity(world, targetEntity, modifiers);
/*      */   }
/*      */   
/*      */   public void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, NBTTagCompound nbtBrew, ModifiersEffect modifiers)
/*      */   {
/* 2797 */     BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2798 */     actionList.applyToBlock(world, x, y, z, side, radius, modifiers);
/*      */   }
/*      */   
/*      */   public void applyRitualToEntity(World world, EntityLivingBase target, NBTTagCompound nbtBrew, ModifiersRitual ritualModifiers, ModifiersEffect modifiers)
/*      */   {
/* 2803 */     BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2804 */     actionList.applyRitualToEnitity(world, target, ritualModifiers, modifiers);
/*      */   }
/*      */   
/*      */   public void applyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, NBTTagCompound nbtBrew, ModifiersRitual ritualModifiers, ModifiersEffect effectModifiers)
/*      */   {
/* 2809 */     BrewActionList actionList = new BrewActionList(nbtBrew, this.ingredients);
/* 2810 */     actionList.applyRitualToBlock(world, x, y, z, side, radius, ritualModifiers, effectModifiers);
/*      */   }
/*      */   
/*      */   public void init() {}
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/WitcheryBrewRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */