/*     */ package com.emoniph.witchery.util;
/*     */ 
/*     */ import com.emoniph.witchery.worldgen.ComponentVillageWatchTower;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Church;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Field1;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Field2;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Hall;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House2;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House3;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House4Garden;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.WoodHut;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.common.config.Property;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Config
/*     */ {
/* 109 */   private static final Config INSTANCE = new Config();
/*     */   public Configuration configuration;
/*     */   
/* 112 */   public static Config instance() { return INSTANCE; }
/*     */   
/*     */ 
/*     */   public boolean smeltAllSaplingsToWoodAsh;
/*     */   
/*     */   public boolean guiOnLeft;
/*     */   
/*     */   public boolean doubleFumeFilterChance;
/*     */   
/*     */   public boolean allowModIntegration;
/*     */   
/*     */   public boolean allowThaumcraft;
/*     */   
/*     */   public boolean allowMineFactoryReloaded;
/*     */   
/*     */   public boolean allowArsMagica2;
/*     */   
/*     */   public boolean allowForestry;
/*     */   
/*     */   public boolean allowTreecapitator;
/*     */   
/*     */   public boolean allowNotEnoughItems;
/*     */   
/*     */   public boolean generateApothecaries;
/*     */   
/*     */   public boolean generateWitchHuts;
/*     */   
/*     */   public boolean generateBookShops;
/*     */   
/*     */   public int apothecaryID;
/*     */   
/*     */   public boolean generateCovens;
/*     */   
/*     */   public boolean generateWickerMen;
/*     */   
/*     */   public boolean generateShacks;
/*     */   
/*     */   public boolean generateGoblinHuts;
/*     */   
/*     */   public boolean allowDeathItemRecoveryRite;
/*     */   
/*     */   public boolean respectOtherDeathChestMods;
/*     */   
/*     */   public int worldGenFrequency;
/*     */   
/*     */   public boolean worldGenTwilightForest;
/*     */   
/*     */   public boolean allowStatueGoddessRecipe;
/*     */   
/*     */   public String[] strawmanSpawnerRules;
/*     */   
/*     */   public boolean allowHellOnEarthFires;
/*     */   
/*     */   public boolean allowVoidBrambleRecipe;
/*     */   
/*     */   public boolean allowBiomeChanging;
/*     */   
/*     */   public int covenWitchSpawnWeight;
/*     */   
/*     */   public int goblinSpawnWeight;
/*     */   
/*     */   public int branchIconSet;
/*     */   
/*     */   public int dimensionDreamID;
/*     */   public int dimensionTormentID;
/*     */   public int dimensionMirrorID;
/*     */   public int percentageOfPlayersSleepingForBuff;
/*     */   public boolean render3dGlintEffect;
/*     */   public boolean renderHuntsmanGlintEffect;
/*     */   public boolean allowMystcraft;
/*     */   public boolean restrictPoppetShelvesToVanillaAndSpiritDimensions;
/*     */   public boolean allowBlockBreakEvents;
/*     */   public boolean allowDeathsHoodToFreezeVictims;
/*     */   public int decurseTeleportPullRadius;
/*     */   public int decurseDirectedRadius;
/*     */   public boolean allowDecurseTeleport;
/*     */   public boolean allowDecurseDirected;
/*     */   public boolean restrictTaglockCollectionOnNonPVPServers;
/*     */   public boolean restrictTaglockCollectionForStaffMembers;
/*     */   public int goblinSpawnRate;
/*     */   public String[] mutandisExtras;
/*     */   
/*     */   public void init(Configuration configuration, Configuration configuration_debug)
/*     */   {
/* 196 */     this.configuration = configuration;
/* 197 */     this.configuration.load();
/* 198 */     sync();
/*     */     
/* 200 */     configuration_debug.load();
/* 201 */     this.traceRitesEnabled = configuration_debug.get("Debug", "TraceRites", false).getBoolean(false);
/* 202 */     this.debugging = configuration_debug.get("Debug", "Debugging", false).getBoolean(false);
/* 203 */     this.dupStaffSaveTemplate = configuration_debug.get("Debug", "SaveDupStaffTemplate", false).getBoolean(false);
/*     */   }
/*     */   
/*     */   public void sync()
/*     */   {
/* 208 */     this.smeltAllSaplingsToWoodAsh = this.configuration.get("general", "AddSmeltingForAllSaplingsToWoodAsh", true).getBoolean(true);
/*     */     
/* 210 */     this.guiOnLeft = this.configuration.get("general", "GUIOnLeft", true).getBoolean(true);
/*     */     
/* 212 */     this.doubleFumeFilterChance = this.configuration.get("general", "DoubleFumeFilterChance", false).getBoolean(false);
/*     */     
/* 214 */     this.allowModIntegration = this.configuration.get("general", "AllowModIntegration", true).getBoolean(true);
/*     */     
/* 216 */     this.allowThaumcraft = this.configuration.get("general", "AllowModThaumcraft", true).getBoolean(true);
/*     */     
/* 218 */     this.allowMineFactoryReloaded = this.configuration.get("general", "AllowModMineFactoryReloaded", true).getBoolean(true);
/*     */     
/* 220 */     this.allowForestry = this.configuration.get("general", "AllowModForestry", true).getBoolean(true);
/*     */     
/* 222 */     this.allowTreecapitator = this.configuration.get("general", "AllowModTreecapitator", true).getBoolean(true);
/*     */     
/* 224 */     this.allowNotEnoughItems = this.configuration.get("general", "AllowModNEI", true).getBoolean(true);
/*     */     
/* 226 */     this.generateApothecaries = this.configuration.get("general", "GenerateApothecaries", true).getBoolean(true);
/*     */     
/* 228 */     this.apothecaryID = this.configuration.get("general", "ApothecaryVillagerID", 2435).getInt();
/*     */     
/* 230 */     this.generateCovens = this.configuration.get("general", "GenerateCovens", true).getBoolean(true);
/*     */     
/* 232 */     this.generateWickerMen = this.configuration.get("general", "GenerateWickerMen", true).getBoolean(true);
/*     */     
/* 234 */     this.generateShacks = this.configuration.get("general", "GenerateShacks", true).getBoolean(true);
/*     */     
/* 236 */     this.generateGoblinHuts = this.configuration.get("general", "GenerateHobgoblinHuts", true).getBoolean(true);
/*     */     
/* 238 */     this.allowDeathItemRecoveryRite = this.configuration.get("general", "AllowDeathItemRecoveryRite", true).getBoolean(true);
/*     */     
/* 240 */     this.respectOtherDeathChestMods = this.configuration.get("general", "RespectOtherDeathChestMods", true).getBoolean(true);
/*     */     
/* 242 */     this.worldGenTwilightForest = this.configuration.get("general", "WorldGenInTwilightForest", true).getBoolean(true);
/*     */     
/* 244 */     this.worldGenFrequency = this.configuration.get("general", "WorldGenFrequency", 12).getInt();
/*     */     
/* 246 */     this.allowStatueGoddessRecipe = this.configuration.get("general", "AllowGoddessStatueRecipe", false).getBoolean(false);
/*     */     
/* 248 */     this.allowHellOnEarthFires = this.configuration.get("general", "AllowHellOnEarthFires", true).getBoolean(true);
/*     */     
/* 250 */     this.allowVoidBrambleRecipe = this.configuration.get("general", "AllowVoidBrambleRecipe", false).getBoolean(false);
/*     */     
/* 252 */     this.allowBiomeChanging = this.configuration.get("general", "AllowBiomeModificationRituals", true).getBoolean(true);
/*     */     
/* 254 */     this.covenWitchSpawnWeight = this.configuration.get("general", "CovenWitchSpawnWeight", 2).getInt();
/*     */     
/* 256 */     this.goblinSpawnWeight = this.configuration.get("general", "HobgoblinSpawnChance", 2).getInt();
/*     */     
/* 258 */     this.goblinSpawnRate = this.configuration.get("general", "HobgoblinSpawnWeight", 4).getInt();
/*     */     
/* 260 */     this.goblinDespawnBlock = this.configuration.get("general", "HobgoblinDespawnBlock", true).getBoolean(true);
/*     */     
/*     */ 
/* 263 */     this.branchIconSet = this.configuration.get("general", "BranchGlyphSet", 0).getInt();
/* 264 */     this.dimensionDreamID = this.configuration.get("general", "DreamDimensionID", -37).getInt();
/*     */     
/* 266 */     this.dimensionTormentID = this.configuration.get("general", "TormentDimensionID", -38).getInt();
/*     */     
/* 268 */     this.dimensionMirrorID = this.configuration.get("general", "MirrorDimensionID", -39).getInt();
/*     */     
/* 270 */     this.percentageOfPlayersSleepingForBuff = Math.max(Math.min(this.configuration.get("general", "PercentageOfPlayersSleepingForBuff", 100).getInt(), 100), 1);
/*     */     
/*     */ 
/*     */ 
/* 274 */     this.render3dGlintEffect = this.configuration.get("general", "Render3dGlintEffect", true).getBoolean(true);
/*     */     
/* 276 */     this.renderHuntsmanGlintEffect = this.configuration.get("general", "RenderHuntsmanGlintEffect", true).getBoolean(true);
/*     */     
/* 278 */     this.allowMystcraft = this.configuration.get("general", "AllowModMystcraft", true).getBoolean(true);
/*     */     
/* 280 */     this.allowArsMagica2 = this.configuration.get("general", "AllowModArsMagica2", true).getBoolean(true);
/*     */     
/* 282 */     this.restrictPoppetShelvesToVanillaAndSpiritDimensions = this.configuration.get("general", "RestrictPoppetShelvesToVanillaAndSpiritDimensions", true).getBoolean(true);
/*     */     
/*     */ 
/* 285 */     this.allowBlockBreakEvents = this.configuration.get("general", "AllowInterModBlockBreakEvents", true).getBoolean(true);
/*     */     
/* 287 */     this.allowDeathsHoodToFreezeVictims = this.configuration.get("general", "AllowDeathsHoodToFreezeVictims", true).getBoolean(true);
/*     */     
/* 289 */     this.strawmanSpawnerRules = this.configuration.get("general", "StrawmanSpawnerRules", new String[] { "Zombie", "Zombie", "Skeleton" }).getStringList();
/*     */     
/* 291 */     this.generateWitchHuts = this.configuration.get("general", "GenerateWitchHuts", true).getBoolean(true);
/*     */     
/*     */ 
/* 294 */     this.generateBookShops = this.configuration.get("general", "GenerateBookShops", true).getBoolean(true);
/*     */     
/*     */ 
/* 297 */     this.decurseTeleportPullRadius = Math.min(Math.max(this.configuration.get("general", "DecurseTeleportPullProtectRadius", 32).getInt(), 0), 128);
/*     */     
/*     */ 
/*     */ 
/* 301 */     this.decurseDirectedRadius = Math.min(Math.max(this.configuration.get("general", "DecurseDirectedProtectRadius", 32).getInt(), 0), 128);
/*     */     
/*     */ 
/* 304 */     this.allowDecurseDirected = this.configuration.get("general", "DecurseDirectedEnabled", false).getBoolean(false);
/*     */     
/* 306 */     this.allowDecurseTeleport = this.configuration.get("general", "DecurseTeleportPullEnabled", false).getBoolean(false);
/*     */     
/*     */ 
/* 309 */     this.restrictTaglockCollectionOnNonPVPServers = this.configuration.get("general", "RestrictTaglockCollectionOnNonPVPServers", false).getBoolean(false);
/*     */     
/* 311 */     this.restrictTaglockCollectionForStaffMembers = this.configuration.get("general", "RestrictTaglockCollectionForOPs", false).getBoolean(false);
/*     */     
/*     */ 
/* 314 */     this.potionStartID = Math.max(this.configuration.get("general", "PotionStartID", 32).getInt(), 32);
/*     */     
/*     */ 
/* 317 */     this.mutandisExtras = this.configuration.get("general", "MutandisAdditionalBlocks", new String[] { "witchery:glintweed,0", "tallgrass,2" }).getStringList();
/*     */     
/* 319 */     this.hobgoblinGodSpawnChance = Math.max(Math.min(this.configuration.get("general", "HobgoblinGodSpawnChance", 10).getInt(), 100), 0);
/*     */     
/*     */ 
/* 322 */     this.hellhoundSpawnRate = this.configuration.get("general", "HellhoundSpawnWeight", 25).getInt();
/*     */     
/*     */ 
/* 325 */     this.spawnWeightSpirit = MathHelper.func_76125_a(this.configuration.get("general", "SpiritSpawnWeight", 1).getInt(), 1, 1000);
/*     */     
/*     */ 
/* 328 */     this.allowExplodingCreeperHearts = this.configuration.get("general", "CreeperHeartsExplodeWithDamageWhenEaten", true).getBoolean(true);
/*     */     
/* 330 */     this.mantrapAlpha = ((float)Math.min(1.0D, Math.max(0.1D, this.configuration.get("general", "MantrapOpacity", 0.3D).getDouble(0.3D))));
/*     */     
/*     */ 
/* 333 */     this.allowVolatilityPotionBlockDamage = this.configuration.get("general", "AllowVolatilityPotionBlockDamage", true).getBoolean(true);
/*     */     
/* 335 */     this.diseaseRemovalChance = this.configuration.get("general", "DiseaseBlockRemovalChance", 10).getInt();
/*     */     
/*     */ 
/* 338 */     this.vampireDeathItemKeepAliveMins = this.configuration.get("general", "VampireDeathItemKeepAliveMins", 12).getInt();
/*     */     
/*     */ 
/* 341 */     this.hudShowVampireTargetBloodText = this.configuration.get("general", "HUDShowVampireTargetBloodText", false).getBoolean(false);
/*     */     
/*     */ 
/* 344 */     this.vampireHunterSpawnChance = ((float)Math.min(1.0D, Math.max(0.0D, this.configuration.get("general", "VampireHunterSpawnChance", 0.01D).getDouble(0.01D))));
/*     */     
/*     */ 
/* 347 */     this.fairestSpawnChance = MathHelper.func_151237_a(this.configuration.get("general", "NewFairestOfThemAllSpawnChance", 0.01D).getDouble(0.01D), 0.01D, 1.0D);
/*     */     
/*     */ 
/*     */ 
/* 351 */     this.allowPlayerToPlayerWolfInfection = this.configuration.get("general", "AllowPlayerToPlayerWolfInfection", true).getBoolean(true);
/*     */     
/*     */ 
/* 354 */     this.riteOfEclipseCooldownInSecs = MathHelper.func_76125_a(this.configuration.get("general", "RiteOfEclipseCooldownInSecs", 0).getInt(), 0, 3600);
/*     */     
/*     */ 
/*     */ 
/* 358 */     this.allowChatMasquerading = this.configuration.get("general", "AllowChatMasquerading", true).getBoolean(true);
/*     */     
/*     */ 
/* 361 */     this.allowNameplateMasquerading = this.configuration.get("general", "AllowNameplateMasquerading", true).getBoolean(true);
/*     */     
/*     */ 
/* 364 */     this.shrinkMirrorWorld = this.configuration.get("general", "ShrinkMirrorWorld", false).getBoolean(false);
/*     */     
/*     */ 
/* 367 */     this.townZombieMode = Math.min(Math.max(this.configuration.get("general", "TownZombieAttackReductionMode", 1).getInt(), 0), 2);
/*     */     
/*     */ 
/* 370 */     this.townParts = new ArrayList();
/* 371 */     new Building(StructureVillagePieces.House4Garden.class, "GardenHouse", 3, 20, 3, 5, this);
/* 372 */     new Building(StructureVillagePieces.House1.class, "House", 3, 20, 3, 5, this);
/* 373 */     new Building(StructureVillagePieces.WoodHut.class, "WoodHut", 3, 20, 3, 5, this);
/* 374 */     new Building(StructureVillagePieces.Hall.class, "Hall", 3, 20, 3, 5, this);
/* 375 */     new Building(StructureVillagePieces.House3.class, "House3", 3, 20, 3, 5, this);
/* 376 */     new Building(StructureVillagePieces.Field1.class, "SingleField", 3, 20, 3, 5, this);
/* 377 */     new Building(StructureVillagePieces.Field2.class, "DoubleField", 3, 20, 3, 5, this);
/* 378 */     new Building(StructureVillagePieces.House2.class, "Blacksmith", 1, 5, 0, 1, this);
/* 379 */     new Building(StructureVillagePieces.Church.class, "Church", 0, 10, 0, 1, this);
/* 380 */     new Building(ComponentVillageWatchTower.class, "GuardTower", 4, 20, 0, 1, this);
/*     */     
/* 382 */     this.townWallChance = Math.min(Math.max(this.configuration.get("general", "TownWallMode", 1).getInt(), 0), 2);
/*     */     
/* 384 */     this.townWallWeight = Math.min(Math.max(this.configuration.get("general", "TownWallWeight", 100).getInt(), 0), 1000);
/*     */     
/*     */ 
/* 387 */     this.townKeepChance = Math.min(Math.max(this.configuration.get("general", "TownKeepMode", 1).getInt(), 0), 2);
/*     */     
/* 389 */     this.townKeepWeight = Math.min(Math.max(this.configuration.get("general", "TownKeepWeight", 100).getInt(), 0), 1000);
/*     */     
/*     */ 
/*     */ 
/* 393 */     this.townAllowSandy = this.configuration.get("general", "TownBiomeSandyAllowed", true).getBoolean(true);
/*     */     
/* 395 */     this.townAllowPlains = this.configuration.get("general", "TownBiomePlainsAllowed", true).getBoolean(true);
/*     */     
/* 397 */     this.townAllowMountain = this.configuration.get("general", "TownBiomeMountainAllowed", true).getBoolean(true);
/*     */     
/* 399 */     this.townAllowHills = this.configuration.get("general", "TownBiomeHillsAllowed", true).getBoolean(true);
/*     */     
/* 401 */     this.townAllowForest = this.configuration.get("general", "TownBiomeForestAllowed", true).getBoolean(true);
/*     */     
/* 403 */     this.townAllowSnowy = this.configuration.get("general", "TownBiomeSnowyAllowed", true).getBoolean(true);
/*     */     
/* 405 */     this.townAllowWasteland = this.configuration.get("general", "TownBiomeWastelandAllowed", true).getBoolean(true);
/*     */     
/* 407 */     this.townAllowMesa = this.configuration.get("general", "TownBiomeMesaAllowed", true).getBoolean(true);
/*     */     
/* 409 */     this.townAllowJungle = this.configuration.get("general", "TownBiomeJungleAllowed", false).getBoolean(false);
/*     */     
/*     */ 
/* 412 */     this.townBooks = this.configuration.get("general", "TownBookshopAllowedBooks", new String[] { "book", "witchery:ingredient,46", "witchery:ingredient,47", "witchery:ingredient,48", "witchery:ingredient,49", "witchery:ingredient,81", "witchery:ingredient,106", "witchery:ingredient,107", "witchery:ingredient,127", "witchery:bookbiomes2", "witchery:cauldronbook", "Thaumcraft:ItemThaumonomicon", "TConstruct:manualBook", "TConstruct:manualBook,1", "TConstruct:manualBook,2", "TConstruct:manualBook,3" }).getStringList();
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
/* 432 */     String[] replaceableBlocks = this.configuration.get("general", "NaturesPowerReplaceableBlocks", new String[] { "mycelium" }).getStringList();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 437 */     this.naturePowerReplaceableBlocks = new ArrayList();
/*     */     
/* 439 */     for (String extra : replaceableBlocks) {
/*     */       try {
/* 441 */         this.naturePowerReplaceableBlocks.add(new BlockMeta(extra));
/*     */       }
/*     */       catch (Throwable ex) {}
/*     */     }
/*     */     
/* 446 */     this.allowVampireWolfHybrids = this.configuration.get("general", "AllowVampireWerewolfHybrids", true).getBoolean(true);
/* 447 */     this.allowStakingVampires = this.configuration.get("general", "AllowStakingVampires", true).getBoolean(true);
/* 448 */     this.allowCovenWitchVisits = this.configuration.get("general", "AllowCovenWitchVisits", true).getBoolean(true);
/* 449 */     this.allowVampireQuests = this.configuration.get("general", "AllowVampireQuests", true).getBoolean(true);
/* 450 */     this.allowVampireRitual = this.configuration.get("general", "AllowVampireRitual", true).getBoolean(true);
/*     */     
/* 452 */     saveIfChanged();
/*     */   }
/*     */   
/*     */   private static class BlockMeta {
/*     */     private final Block block;
/*     */     private final int metadata;
/*     */     
/* 459 */     public BlockMeta(String extra) { String name = extra;
/* 460 */       int meta = 32767;
/* 461 */       int comma = extra.lastIndexOf(',');
/* 462 */       if (comma >= 0) {
/* 463 */         name = extra.substring(0, comma);
/*     */         
/* 465 */         String metaString = extra.substring(comma + 1);
/* 466 */         meta = Integer.parseInt(metaString);
/*     */       }
/*     */       
/*     */ 
/* 470 */       this.block = Block.func_149684_b(name);
/* 471 */       this.metadata = meta;
/*     */     }
/*     */     
/* 474 */     public boolean isMatch(Block b, int m) { return (b == this.block) && ((this.metadata == 32767) || (this.metadata == m)); } }
/*     */   
/*     */   public int hobgoblinGodSpawnChance;
/*     */   public boolean hudShowVampireTargetBloodText;
/*     */   public String[] townBooks;
/*     */   public double vampireHunterSpawnChance;
/*     */   public int potionStartID;
/*     */   public boolean dupStaffSaveTemplate;
/*     */   private boolean traceRitesEnabled;
/*     */   private boolean debugging;
/*     */   public int hellhoundSpawnRate;
/*     */   public boolean allowExplodingCreeperHearts;
/*     */   public float mantrapAlpha;
/*     */   public int townZombieMode;
/*     */   public boolean allowVolatilityPotionBlockDamage;
/*     */   public boolean allowStakingVampires;
/*     */   public boolean allowCovenWitchVisits;
/*     */   public boolean allowVampireRitual;
/*     */   public boolean allowVampireQuests;
/*     */   public boolean shrinkMirrorWorld;
/*     */   private static final String CategoryEntity = "Entity";
/*     */   private List<BlockMeta> naturePowerReplaceableBlocks;
/*     */   public boolean allowVampireWolfHybrids;
/*     */   public int townWallChance;
/*     */   public int townWallWeight;
/*     */   public int townKeepChance;
/*     */   public int townKeepWeight;
/*     */   public boolean townAllowSandy;
/*     */   public boolean townAllowPlains;
/*     */   public boolean townAllowMountain;
/*     */   public boolean townAllowHills;
/*     */   public boolean townAllowForest;
/*     */   public boolean townAllowSnowy;
/*     */   public boolean townAllowWasteland;
/*     */   public boolean townAllowJungle;
/*     */   public boolean townAllowMesa;
/*     */   public List<Building> townParts;
/*     */   public boolean goblinDespawnBlock;
/*     */   public int diseaseRemovalChance;
/*     */   public int vampireDeathItemKeepAliveMins;
/*     */   public int spawnWeightSpirit;
/*     */   public double fairestSpawnChance;
/*     */   public boolean allowPlayerToPlayerWolfInfection;
/*     */   public int riteOfEclipseCooldownInSecs;
/*     */   public boolean allowChatMasquerading;
/*     */   public boolean allowNameplateMasquerading;
/*     */   public static class Building { private static final String TOWN = "Town";
/*     */     public final int groups;
/*     */     public final int weight;
/*     */     public final int min;
/*     */     public final int max;
/* 525 */     public Building(Class<? extends StructureVillagePieces.Village> clazz, String name, int groups, int weight, int min, int max, Config config) { this.clazz = clazz;
/* 526 */       this.groups = config.configuration.get("general", "Town" + name + "ClusterGroups", groups).getInt();
/*     */       
/* 528 */       this.weight = config.configuration.get("general", "Town" + name + "ClusterWeight", weight).getInt();
/*     */       
/* 530 */       this.min = config.configuration.get("general", "Town" + name + "ClusterMin", min).getInt();
/*     */       
/* 532 */       this.max = config.configuration.get("general", "Town" + name + "ClusterMax", max).getInt();
/*     */       
/* 534 */       config.townParts.add(this);
/*     */     }
/*     */     
/*     */     public final Class<? extends StructureVillagePieces.Village> clazz; }
/*     */   
/* 539 */   public boolean isDebugging() { return this.debugging; }
/*     */   
/*     */ 
/*     */   public boolean traceRites() {
/* 543 */     return this.traceRitesEnabled;
/*     */   }
/*     */   
/*     */   public void saveIfChanged() {
/* 547 */     if (this.configuration.hasChanged()) {
/* 548 */       this.configuration.save();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isReduceZombeVillagerDamageActive() {
/* 553 */     return this.townZombieMode >= 1;
/*     */   }
/*     */   
/*     */   public boolean isZombeIgnoreVillagerActive() {
/* 557 */     return this.townZombieMode >= 2;
/*     */   }
/*     */   
/*     */   public boolean canReplaceNaturalBlock(Block block, int meta) {
/* 561 */     for (BlockMeta bm : this.naturePowerReplaceableBlocks) {
/* 562 */       if (bm.isMatch(block, meta)) {
/* 563 */         return true;
/*     */       }
/*     */     }
/* 566 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */