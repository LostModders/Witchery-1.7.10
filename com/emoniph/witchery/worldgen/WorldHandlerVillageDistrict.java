/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.blocks.TileEntityBase;
/*     */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Config.Building;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import cpw.mods.fml.common.eventhandler.Event.Result;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.registry.VillagerRegistry;
/*     */ import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.structure.MapGenStructureIO;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Church;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Field1;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Field2;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Hall;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House2;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House3;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House4Garden;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Path;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.WoodHut;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import net.minecraftforge.common.BiomeManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
/*     */ import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockMeta;
/*     */ 
/*     */ public class WorldHandlerVillageDistrict implements VillagerRegistry.IVillageCreationHandler
/*     */ {
/*     */   private final Class<? extends StructureVillagePieces.Village> pieceClazz;
/*     */   private final int weight;
/*     */   private final int quantityMin;
/*     */   private final int quantityMax;
/*     */   
/*     */   public WorldHandlerVillageDistrict(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min)
/*     */   {
/*  58 */     this(clazz, weight, min, min);
/*     */   }
/*     */   
/*     */   public WorldHandlerVillageDistrict(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min, int max)
/*     */   {
/*  63 */     this.pieceClazz = clazz;
/*  64 */     this.weight = weight;
/*  65 */     this.quantityMin = min;
/*  66 */     this.quantityMax = max;
/*     */   }
/*     */   
/*     */   public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random rand, int size)
/*     */   {
/*  71 */     return new StructureVillagePieces.PieceWeight(this.pieceClazz, this.weight, this.quantityMax <= this.quantityMin ? this.quantityMin : this.quantityMin + rand.nextInt(this.quantityMax - this.quantityMin + 1));
/*     */   }
/*     */   
/*     */ 
/*     */   public Class getComponentClass()
/*     */   {
/*  77 */     return this.pieceClazz;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object buildComponent(StructureVillagePieces.PieceWeight weight, StructureVillagePieces.Start startPiece, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
/*     */   {
/*  83 */     Object object = null;
/*     */     
/*  85 */     if (this.pieceClazz == StructureVillagePieces.House4Garden.class) {
/*  86 */       object = StructureVillagePieces.House4Garden.func_74912_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*  87 */     } else if (this.pieceClazz == StructureVillagePieces.Church.class) {
/*  88 */       object = StructureVillagePieces.Church.func_74919_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*  89 */     } else if (this.pieceClazz == StructureVillagePieces.House1.class) {
/*  90 */       object = StructureVillagePieces.House1.func_74898_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*  91 */     } else if (this.pieceClazz == StructureVillagePieces.WoodHut.class) {
/*  92 */       object = StructureVillagePieces.WoodHut.func_74908_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*  93 */     } else if (this.pieceClazz == StructureVillagePieces.Hall.class) {
/*  94 */       object = StructureVillagePieces.Hall.func_74906_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*  95 */     } else if (this.pieceClazz == StructureVillagePieces.Field1.class) {
/*  96 */       object = StructureVillagePieces.Field1.func_74900_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*  97 */     } else if (this.pieceClazz == StructureVillagePieces.Field2.class) {
/*  98 */       object = StructureVillagePieces.Field2.func_74902_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*  99 */     } else if (this.pieceClazz == StructureVillagePieces.House2.class) {
/* 100 */       object = StructureVillagePieces.House2.func_74915_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/* 101 */     } else if (this.pieceClazz == StructureVillagePieces.House3.class) {
/* 102 */       object = StructureVillagePieces.House3.func_74921_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/* 103 */     } else if (this.pieceClazz == Wall.class) {
/* 104 */       object = Wall.func_74921_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/* 105 */     } else if (this.pieceClazz == ComponentVillageWatchTower.class) {
/* 106 */       object = ComponentVillageWatchTower.construct(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/* 107 */     } else if (this.pieceClazz == ComponentVillageKeep.class) {
/* 108 */       object = ComponentVillageKeep.construct(startPiece, pieces, rand, p1, p2, p3, p4, p5);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 114 */     return object == null ? null : (StructureVillagePieces.Village)object;
/*     */   }
/*     */   
/*     */   public static void registerComponent(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min, int max) {
/* 118 */     VillagerRegistry.instance().registerVillageCreationHandler(new WorldHandlerVillageDistrict(clazz, weight, min, max));
/*     */   }
/*     */   
/*     */   public static void preInit()
/*     */   {
/*     */     try {
/* 124 */       MapGenStructureIO.func_143031_a(Wall.class, "witchery:villagewall");
/* 125 */       MapGenStructureIO.func_143031_a(ComponentVillageKeep.class, "witchery:villagekeep");
/* 126 */       MapGenStructureIO.func_143031_a(ComponentVillageWatchTower.class, "witchery:villagewatchtower");
/*     */     }
/*     */     catch (Throwable e) {}
/*     */     
/* 130 */     if (Config.instance().townWallChance > 0) {
/* 131 */       registerComponent(Wall.class, Config.instance().townWallWeight, Config.instance().townWallChance == 2 ? 1 : 0, 1);
/*     */     }
/*     */     
/*     */ 
/* 135 */     if (Config.instance().townKeepChance > 0) {
/* 136 */       registerComponent(ComponentVillageKeep.class, Config.instance().townKeepWeight, Config.instance().townKeepChance == 2 ? 1 : 0, 1);
/*     */     }
/*     */     
/*     */ 
/* 140 */     VillagerRegistry register = VillagerRegistry.instance();
/*     */     
/* 142 */     for (Config.Building building : Config.instance().townParts) {
/* 143 */       for (int i = 0; i < building.groups; i++) {
/* 144 */         register.registerVillageCreationHandler(new WorldHandlerVillageDistrict(building.clazz, building.weight, building.min, building.max));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void init()
/*     */   {
/* 152 */     for (BiomeGenBase biome : )
/* 153 */       if (biome != null)
/*     */       {
/*     */ 
/*     */ 
/* 157 */         if ((!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WET)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.OCEAN)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.BEACH)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.END)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.RIVER)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WATER)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 167 */           boolean disallowed = ((!Config.instance().townAllowSandy) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY))) || ((!Config.instance().townAllowPlains) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS))) || ((!Config.instance().townAllowMountain) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MOUNTAIN))) || ((!Config.instance().townAllowHills) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HILLS))) || ((!Config.instance().townAllowForest) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST))) || ((!Config.instance().townAllowSnowy) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY))) || ((!Config.instance().townAllowWasteland) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WASTELAND))) || ((!Config.instance().townAllowJungle) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE))) || ((!Config.instance().townAllowMesa) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MESA)));
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
/* 180 */           if (!disallowed)
/*     */           {
/*     */ 
/*     */ 
/* 184 */             BiomeManager.addVillageBiome(biome, true); }
/*     */         } }
/*     */   }
/*     */   
/*     */   public static class EventHooks {
/*     */     @SubscribeEvent
/*     */     public void onGetVillageBlock(BiomeEvent.GetVillageBlockID event) {
/* 191 */       if (event.biome == null) {
/* 192 */         return;
/*     */       }
/*     */       
/* 195 */       Block b = event.original;
/* 196 */       if (BiomeDictionary.isBiomeOfType(event.biome, BiomeDictionary.Type.SANDY)) {
/* 197 */         if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
/* 198 */           event.replacement = Blocks.field_150322_A;
/* 199 */         } else if (b == Blocks.field_150347_e) {
/* 200 */           event.replacement = Blocks.field_150322_A;
/* 201 */         } else if (b == Blocks.field_150344_f) {
/* 202 */           event.replacement = Blocks.field_150344_f;
/* 203 */           event.setResult(Event.Result.DENY);
/* 204 */         } else if (b == Blocks.field_150476_ad) {
/* 205 */           event.replacement = Blocks.field_150487_bG;
/* 206 */         } else if (b == Blocks.field_150446_ar) {
/* 207 */           event.replacement = Blocks.field_150372_bz;
/* 208 */         } else if (b == Blocks.field_150351_n) {
/* 209 */           event.replacement = Blocks.field_150322_A;
/* 210 */         } else if (b == Blocks.field_150417_aV) {
/* 211 */           event.replacement = Blocks.field_150322_A;
/* 212 */         } else if (b == Blocks.field_150376_bx) {
/* 213 */           event.replacement = Blocks.field_150376_bx;
/* 214 */         } else if (b == Blocks.field_150390_bg) {
/* 215 */           event.replacement = Blocks.field_150372_bz;
/*     */         }
/* 217 */       } else if (BiomeDictionary.isBiomeOfType(event.biome, BiomeDictionary.Type.SNOWY)) {
/* 218 */         if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
/* 219 */           event.replacement = Blocks.field_150403_cj;
/* 220 */         } else if (b == Blocks.field_150347_e) {
/* 221 */           event.replacement = Blocks.field_150433_aE;
/* 222 */         } else if (b == Blocks.field_150344_f) {
/* 223 */           event.replacement = Blocks.field_150433_aE;
/* 224 */         } else if (b == Blocks.field_150476_ad) {
/* 225 */           event.replacement = Witchery.Blocks.SNOW_STAIRS;
/* 226 */         } else if (b == Blocks.field_150446_ar) {
/* 227 */           event.replacement = Witchery.Blocks.SNOW_STAIRS;
/* 228 */         } else if (b == Blocks.field_150351_n) {
/* 229 */           event.replacement = Blocks.field_150403_cj;
/* 230 */         } else if (b == Blocks.field_150417_aV) {
/* 231 */           event.replacement = Blocks.field_150433_aE;
/* 232 */         } else if (b == Blocks.field_150333_U) {
/* 233 */           event.replacement = Witchery.Blocks.SNOW_SLAB_SINGLE;
/* 234 */         } else if (b == Blocks.field_150376_bx) {
/* 235 */           event.replacement = Witchery.Blocks.SNOW_SLAB_SINGLE;
/* 236 */         } else if (b == Blocks.field_150422_aJ) {
/* 237 */           event.replacement = Witchery.Blocks.PERPETUAL_ICE_FENCE;
/* 238 */         } else if (b == Blocks.field_150346_d) {
/* 239 */           event.replacement = Blocks.field_150433_aE;
/* 240 */         } else if (b == Blocks.field_150452_aw) {
/* 241 */           event.replacement = Witchery.Blocks.SNOW_PRESSURE_PLATE;
/* 242 */         } else if (b == Blocks.field_150390_bg) {
/* 243 */           event.replacement = Witchery.Blocks.SNOW_STAIRS;
/*     */         }
/*     */       }
/*     */       
/* 247 */       if ((event.replacement != null) && (event.replacement != event.original)) {
/* 248 */         event.setResult(Event.Result.DENY);
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onGetVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event) {
/* 254 */       Block b = event.original;
/* 255 */       if (event.biome == null) {
/* 256 */         return;
/*     */       }
/* 258 */       if (BiomeDictionary.isBiomeOfType(event.biome, BiomeDictionary.Type.SANDY)) {
/* 259 */         if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
/* 260 */           event.replacement = 2;
/* 261 */           event.setResult(Event.Result.DENY);
/* 262 */         } else if (b == Blocks.field_150347_e) {
/* 263 */           event.replacement = 0;
/* 264 */           event.setResult(Event.Result.DENY);
/* 265 */         } else if (b == Blocks.field_150344_f) {
/* 266 */           event.replacement = 2;
/* 267 */           event.setResult(Event.Result.DENY);
/* 268 */         } else if (b == Blocks.field_150376_bx) {
/* 269 */           event.replacement = 2;
/* 270 */           event.setResult(Event.Result.DENY);
/* 271 */         } else if (b == Blocks.field_150333_U) {
/* 272 */           if ((event.type == 3) || (event.type == 0)) {
/* 273 */             event.replacement = 1;
/* 274 */             event.setResult(Event.Result.DENY);
/* 275 */           } else if ((event.type == 11) || (event.type == 8)) {
/* 276 */             event.replacement = 9;
/* 277 */             event.setResult(Event.Result.DENY);
/*     */           }
/* 279 */         } else if (b == Blocks.field_150417_aV) {
/* 280 */           event.replacement = 2;
/* 281 */           event.setResult(Event.Result.DENY);
/*     */         }
/* 283 */       } else if (BiomeDictionary.isBiomeOfType(event.biome, BiomeDictionary.Type.SNOWY)) {
/* 284 */         if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
/* 285 */           event.replacement = 0;
/* 286 */           event.setResult(Event.Result.DENY);
/* 287 */         } else if (b == Blocks.field_150347_e) {
/* 288 */           event.replacement = 0;
/* 289 */           event.setResult(Event.Result.DENY);
/* 290 */         } else if (b == Blocks.field_150344_f) {
/* 291 */           event.replacement = 0;
/* 292 */           event.setResult(Event.Result.DENY);
/* 293 */         } else if (b == Blocks.field_150333_U) {
/* 294 */           if (event.type >= 8) {
/* 295 */             event.replacement = 8;
/* 296 */             event.setResult(Event.Result.DENY);
/*     */           } else {
/* 298 */             event.replacement = 0;
/* 299 */             event.setResult(Event.Result.DENY);
/*     */           }
/* 301 */         } else if (b == Blocks.field_150417_aV) {
/* 302 */           event.replacement = 0;
/* 303 */           event.setResult(Event.Result.DENY);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Wall extends StructureVillagePieces.Village
/*     */   {
/*     */     private StructureVillagePieces.Start start;
/*     */     private List pieces;
/*     */     private boolean hasMadeWallBlock;
/*     */     
/*     */     public Wall() {}
/*     */     
/*     */     public Wall(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int baseMode) {
/* 318 */       super(componentType);
/* 319 */       this.field_74885_f = baseMode;
/* 320 */       this.field_74887_e = bounds;
/* 321 */       this.start = start;
/*     */     }
/*     */     
/*     */     public static Wall func_74921_a(StructureVillagePieces.Start startPiece, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
/*     */     {
/* 326 */       StructureBoundingBox bounds = StructureBoundingBox.func_78889_a(p1, p2, p3, 0, 0, 0, 2, 7, 2, p4);
/*     */       
/* 328 */       boolean create = (func_74895_a(bounds)) && (StructureComponent.func_74883_a(pieces, bounds) == null) && (!containsWalls(pieces));
/*     */       
/* 330 */       return create ? new Wall(startPiece, p5, rand, bounds, p4) : null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private static boolean containsWalls(List pieces2)
/*     */     {
/* 339 */       return false;
/*     */     }
/*     */     
/*     */     public void func_74861_a(StructureComponent component, List pieces, Random rand)
/*     */     {
/* 344 */       super.func_74861_a(component, pieces, rand);
/* 345 */       this.pieces = pieces;
/*     */     }
/*     */     
/*     */     public boolean func_74875_a(World world, Random rand, StructureBoundingBox bounds)
/*     */     {
/* 350 */       if (this.field_143015_k < 0) {
/* 351 */         this.field_143015_k = func_74889_b(world, bounds);
/*     */         
/* 353 */         if (this.field_143015_k < 0) {
/* 354 */           return true;
/*     */         }
/*     */         
/* 357 */         this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 7 - 1, 0);
/*     */       }
/*     */       
/* 360 */       if (!this.hasMadeWallBlock) {
/* 361 */         int x = 1;int z = 1;
/* 362 */         int xCoord = func_74865_a(x, z);
/* 363 */         int yCoord = func_74862_a(1);
/* 364 */         int zCoord = func_74873_b(x, z);
/*     */         
/* 366 */         if ((this.pieces != null) && (bounds.func_78890_b(xCoord, yCoord, zCoord))) {
/* 367 */           this.hasMadeWallBlock = true;
/* 368 */           world.func_147449_b(xCoord, yCoord, zCoord, Witchery.Blocks.WALLGEN);
/* 369 */           WorldHandlerVillageDistrict.Wall.BlockVillageWallGen.TileEntityVillageWallGen tile = (WorldHandlerVillageDistrict.Wall.BlockVillageWallGen.TileEntityVillageWallGen)BlockUtil.getTileEntity(world, xCoord, yCoord, zCoord, WorldHandlerVillageDistrict.Wall.BlockVillageWallGen.TileEntityVillageWallGen.class);
/*     */           
/* 371 */           if (tile != null) {
/* 372 */             tile.setStructure(this.pieces, this.start);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 377 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */     protected void func_143012_a(NBTTagCompound nbtRoot)
/*     */     {
/* 383 */       super.func_143012_a(nbtRoot);
/* 384 */       nbtRoot.func_74757_a("WallBlock", this.hasMadeWallBlock);
/*     */     }
/*     */     
/*     */     protected void func_143011_b(NBTTagCompound nbtRoot) {
/* 388 */       super.func_143011_b(nbtRoot);
/* 389 */       this.hasMadeWallBlock = nbtRoot.func_74767_n("WallBlock");
/*     */     }
/*     */     
/*     */     public static class StructureBounds extends StructureBoundingBox {
/*     */       public final boolean ew;
/*     */       
/*     */       public StructureBounds(StructureVillagePieces.Path path, int expansionX, int expansionZ) {
/* 396 */         this(path.func_74874_b(), expansionX, expansionZ);
/*     */       }
/*     */       
/*     */       public StructureBounds(StructureBoundingBox bb, int expansionX, int expansionZ) {
/* 400 */         this(bb.field_78897_a, bb.field_78895_b, bb.field_78896_c, bb.field_78893_d, bb.field_78894_e, bb.field_78892_f, expansionX, expansionZ);
/*     */       }
/*     */       
/*     */       public StructureBounds(int x, int y, int z, int x2, int y2, int z2, int expansionX, int expansionZ) {
/* 404 */         this.ew = (x2 - x > z2 - z);
/* 405 */         if (this.ew) {
/* 406 */           this.field_78897_a = (x - expansionZ);
/* 407 */           this.field_78893_d = (x2 + expansionZ);
/* 408 */           this.field_78896_c = (z - expansionX);
/* 409 */           this.field_78892_f = (z2 + expansionX);
/*     */         } else {
/* 411 */           this.field_78897_a = (x - expansionX);
/* 412 */           this.field_78893_d = (x2 + expansionX);
/* 413 */           this.field_78896_c = (z - expansionZ);
/* 414 */           this.field_78892_f = (z2 + expansionZ);
/*     */         }
/*     */         
/* 417 */         this.field_78895_b = y;
/* 418 */         this.field_78894_e = y2;
/*     */       }
/*     */     }
/*     */     
/*     */     public static void placeWalls(World world, List<StructureBounds> bb, int xCoord, int yCoord, int zCoord, BiomeGenBase biome, boolean desert)
/*     */     {
/* 424 */       int minX = Integer.MAX_VALUE;
/* 425 */       int minZ = Integer.MAX_VALUE;
/* 426 */       int maxX = Integer.MIN_VALUE;
/* 427 */       int maxZ = Integer.MIN_VALUE;
/*     */       
/* 429 */       Log.instance().debug(String.format("Generating town walls at [%d %d %d]", new Object[] { Integer.valueOf(xCoord), Integer.valueOf(yCoord), Integer.valueOf(zCoord) }));
/*     */       
/*     */ 
/* 432 */       for (int i = 0; i < bb.size(); i++) {
/* 433 */         minX = Math.min(((StructureBounds)bb.get(i)).field_78897_a, minX);
/* 434 */         minZ = Math.min(((StructureBounds)bb.get(i)).field_78896_c, minZ);
/* 435 */         maxX = Math.max(((StructureBounds)bb.get(i)).field_78893_d, maxX);
/* 436 */         maxZ = Math.max(((StructureBounds)bb.get(i)).field_78892_f, maxZ);
/*     */       }
/*     */       
/* 439 */       if ((maxX != Integer.MIN_VALUE) && (minX != Integer.MAX_VALUE) && (maxZ != Integer.MIN_VALUE) && (minZ != Integer.MAX_VALUE))
/*     */       {
/*     */ 
/* 442 */         byte[][] a = new byte[maxX - minX + 3][maxZ - minZ + 3];
/* 443 */         short[][] b = new short[maxX - minX + 3][maxZ - minZ + 3];
/* 444 */         for (int i = 0; i < bb.size(); i++) {
/* 445 */           int w = ((StructureBounds)bb.get(i)).field_78893_d - ((StructureBounds)bb.get(i)).field_78897_a + 1;
/* 446 */           int wMid = w / 2 + ((StructureBounds)bb.get(i)).field_78897_a - 1;
/* 447 */           int h = ((StructureBounds)bb.get(i)).field_78892_f - ((StructureBounds)bb.get(i)).field_78896_c + 1;
/* 448 */           int hMid = h / 2 + ((StructureBounds)bb.get(i)).field_78896_c - 1;
/* 449 */           for (int x = ((StructureBounds)bb.get(i)).field_78897_a; x <= ((StructureBounds)bb.get(i)).field_78893_d; x++) {
/* 450 */             for (int z = ((StructureBounds)bb.get(i)).field_78896_c; z <= ((StructureBounds)bb.get(i)).field_78892_f; z++) {
/* 451 */               int mx = x - minX + 1;
/* 452 */               int mz = z - minZ + 1;
/* 453 */               if ((!((StructureBounds)bb.get(i)).ew) && ((z == ((StructureBounds)bb.get(i)).field_78896_c) || (z == ((StructureBounds)bb.get(i)).field_78892_f)) && (x >= wMid - 1) && (x <= wMid + 1))
/*     */               {
/* 455 */                 a[mx][mz] = 3;
/* 456 */               } else if ((((StructureBounds)bb.get(i)).ew) && ((x == ((StructureBounds)bb.get(i)).field_78897_a) || (x == ((StructureBounds)bb.get(i)).field_78893_d)) && (z >= hMid - 1) && (z <= hMid + 1))
/*     */               {
/* 458 */                 a[mx][mz] = 3;
/*     */               } else {
/* 460 */                 a[mx][mz] = 2;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 467 */         int range = 7;
/* 468 */         for (int x = 1; x < a.length - range; x++) {
/* 469 */           for (int z = 1; z < a[x].length - range; z++) {
/* 470 */             if (a[x][z] == 2) {
/* 471 */               for (int p = 1; p < range; p++)
/*     */               {
/* 473 */                 if ((a[(x + p)][z] == 2) && (a[(x + p - 1)][z] == 0)) {
/* 474 */                   for (int p2 = p; p2 > 0; p2--) {
/* 475 */                     a[(x + p2)][z] = 2;
/*     */                   }
/*     */                 }
/*     */                 
/* 479 */                 if ((a[x][(z + p)] == 2) && (a[x][(z + p - 1)] == 0)) {
/* 480 */                   for (int p2 = p; p2 > 0; p2--) {
/* 481 */                     a[x][(z + p2)] = 2;
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 490 */         for (int x = 1; x < a.length - 1; x++) {
/* 491 */           for (int z = 1; z < a[x].length - 1; z++) {
/* 492 */             boolean n = a[x][(z - 1)] == 0;
/* 493 */             boolean s = a[x][(z + 1)] == 0;
/* 494 */             boolean e = a[(x + 1)][z] == 0;
/* 495 */             boolean w = a[(x - 1)][z] == 0;
/*     */             
/* 497 */             boolean ne = a[(x + 1)][(z - 1)] == 0;
/* 498 */             boolean sw = a[(x - 1)][(z + 1)] == 0;
/* 499 */             boolean se = a[(x + 1)][(z + 1)] == 0;
/* 500 */             boolean nw = a[(x - 1)][(z - 1)] == 0;
/* 501 */             if ((!n) && (!s) && (!e) && (!w) && (!ne) && (!se) && (!nw) && (!sw))
/*     */             {
/*     */ 
/* 504 */               a[x][z] = 1;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 509 */         Block blockBase = Blocks.field_150417_aV;
/* 510 */         Block blockFence = Blocks.field_150422_aJ;
/* 511 */         Block stairsBlock = Blocks.field_150390_bg;
/* 512 */         int blockBaseMeta = 0;
/*     */         
/* 514 */         BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(biome, blockBase, blockBaseMeta);
/*     */         
/* 516 */         MinecraftForge.TERRAIN_GEN_BUS.post(event);
/* 517 */         if (event.getResult() == Event.Result.DENY) {
/* 518 */           blockBase = event.replacement;
/* 519 */         } else if (desert) {
/* 520 */           blockBase = Blocks.field_150322_A;
/*     */         }
/*     */         
/* 523 */         event = new BiomeEvent.GetVillageBlockID(biome, blockFence, 0);
/* 524 */         MinecraftForge.TERRAIN_GEN_BUS.post(event);
/* 525 */         if (event.getResult() == Event.Result.DENY) {
/* 526 */           blockFence = event.replacement;
/*     */         }
/*     */         
/* 529 */         event = new BiomeEvent.GetVillageBlockID(biome, stairsBlock, 0);
/* 530 */         MinecraftForge.TERRAIN_GEN_BUS.post(event);
/* 531 */         if (event.getResult() == Event.Result.DENY) {
/* 532 */           stairsBlock = event.replacement;
/* 533 */         } else if (desert) {
/* 534 */           stairsBlock = Blocks.field_150372_bz;
/*     */         }
/*     */         
/* 537 */         BiomeEvent.GetVillageBlockMeta event2 = new BiomeEvent.GetVillageBlockMeta(biome, blockBase, blockBaseMeta);
/*     */         
/* 539 */         MinecraftForge.TERRAIN_GEN_BUS.post(event2);
/* 540 */         if (event2.getResult() == Event.Result.DENY) {
/* 541 */           blockBaseMeta = event2.replacement;
/* 542 */         } else if (desert) {
/* 543 */           blockBaseMeta = 2;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 548 */         int guardDist = 0;
/*     */         
/* 550 */         for (int x = 1; x < a.length - 1; x++) {
/* 551 */           for (int z = 1; z < a[x].length - 1; z++) {
/* 552 */             boolean n = a[x][(z - 1)] >= 2;
/* 553 */             boolean s = a[x][(z + 1)] >= 2;
/* 554 */             boolean e = a[(x + 1)][z] >= 2;
/* 555 */             boolean w = a[(x - 1)][z] >= 2;
/*     */             
/* 557 */             boolean ne = a[(x + 1)][(z - 1)] >= 2;
/* 558 */             boolean sw = a[(x - 1)][(z + 1)] >= 2;
/* 559 */             boolean se = a[(x + 1)][(z + 1)] >= 2;
/* 560 */             boolean nw = a[(x - 1)][(z - 1)] >= 2;
/*     */             
/* 562 */             if (a[x][z] >= 2) {
/* 563 */               int dx = minX + x;
/* 564 */               int dz = minZ + z;
/* 565 */               int solidCount = 0;
/*     */               
/* 567 */               for (int dy = yCoord; (dy > 1) && (solidCount < 9); dy--) {
/* 568 */                 solidCount = 0;
/* 569 */                 for (int ddx = dx - 1; ddx <= dx + 1; ddx++) {
/* 570 */                   for (int ddz = dz - 1; ddz <= dz + 1; ddz++) {
/* 571 */                     Block block = world.func_147439_a(ddx, dy, ddz);
/* 572 */                     boolean replaceable = (block.func_149688_o().func_76222_j()) || (block.func_149688_o() == Material.field_151584_j) || (block.func_149688_o() == Material.field_151575_d) || (block.func_149688_o() == Material.field_151585_k);
/*     */                     
/*     */ 
/*     */ 
/* 576 */                     if ((block.func_149721_r()) && (!replaceable)) {
/* 577 */                       solidCount++;
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */               
/* 583 */               int minHeight = 9;
/*     */               
/* 585 */               int startY = dy + 9;
/*     */               
/* 587 */               int near = Math.max(Math.max(Math.max(b[(x - 1)][z], b[(x + 1)][z]), b[x][(z + 1)]), b[x][(z - 1)]);
/*     */               
/* 589 */               if (near > 0) {
/* 590 */                 if (near > startY) {
/* 591 */                   startY = near - 1;
/* 592 */                 } else if (near < startY) {
/* 593 */                   startY = near + 1;
/*     */                 }
/*     */               }
/*     */               
/* 597 */               int lowestY = dy;
/*     */               
/* 599 */               if (startY - lowestY > 0) {
/* 600 */                 b[x][z] = ((short)Math.min(Math.max(startY, 0), 32767));
/*     */               }
/*     */               
/* 603 */               for (dy = startY; dy > lowestY; dy--)
/*     */               {
/*     */ 
/* 606 */                 if (dy == startY) {
/* 607 */                   if ((!ne) && (!n) && (!e)) {
/* 608 */                     setBlock(world, dx + 2, dy, dz - 2, blockBase, blockBaseMeta);
/* 609 */                     setBlock(world, dx + 2, dy, dz - 1, blockBase, blockBaseMeta);
/* 610 */                     setBlock(world, dx + 1, dy, dz - 2, blockBase, blockBaseMeta);
/*     */                     
/* 612 */                     setBlock(world, dx + 2, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
/* 613 */                     setBlock(world, dx + 2, dy + 1, dz - 1, blockBase, blockBaseMeta, false);
/* 614 */                     setBlock(world, dx + 1, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
/*     */                   }
/*     */                   
/* 617 */                   if ((!nw) && (!n) && (!w)) {
/* 618 */                     setBlock(world, dx - 2, dy, dz - 2, blockBase, blockBaseMeta);
/* 619 */                     setBlock(world, dx - 1, dy, dz - 2, blockBase, blockBaseMeta);
/* 620 */                     setBlock(world, dx - 2, dy, dz - 1, blockBase, blockBaseMeta);
/*     */                     
/* 622 */                     setBlock(world, dx - 2, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
/* 623 */                     setBlock(world, dx - 1, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
/* 624 */                     setBlock(world, dx - 2, dy + 1, dz - 1, blockBase, blockBaseMeta, false);
/*     */                   }
/*     */                   
/* 627 */                   if ((!se) && (!s) && (!e)) {
/* 628 */                     setBlock(world, dx + 2, dy, dz + 2, blockBase, blockBaseMeta);
/* 629 */                     setBlock(world, dx + 1, dy, dz + 2, blockBase, blockBaseMeta);
/* 630 */                     setBlock(world, dx + 2, dy, dz + 1, blockBase, blockBaseMeta);
/*     */                     
/* 632 */                     setBlock(world, dx + 2, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
/* 633 */                     setBlock(world, dx + 1, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
/* 634 */                     setBlock(world, dx + 2, dy + 1, dz + 1, blockBase, blockBaseMeta, false);
/*     */                   }
/*     */                   
/* 637 */                   if ((!sw) && (!s) && (!w)) {
/* 638 */                     setBlock(world, dx - 2, dy, dz + 2, blockBase, blockBaseMeta);
/* 639 */                     setBlock(world, dx - 1, dy, dz + 2, blockBase, blockBaseMeta);
/* 640 */                     setBlock(world, dx - 2, dy, dz + 1, blockBase, blockBaseMeta);
/*     */                     
/* 642 */                     setBlock(world, dx - 2, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
/* 643 */                     setBlock(world, dx - 1, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
/* 644 */                     setBlock(world, dx - 2, dy + 1, dz + 1, blockBase, blockBaseMeta, false);
/*     */                   }
/*     */                   
/* 647 */                   if ((!n) && (!ne) && (!nw)) {
/* 648 */                     setBlock(world, dx, dy, dz - 2, blockBase, blockBaseMeta);
/* 649 */                     setBlock(world, dx, dy + 1, dz - 2, stairsBlock, 0, false);
/*     */                   }
/*     */                   
/* 652 */                   if ((!e) && (!se) && (!ne)) {
/* 653 */                     setBlock(world, dx + 2, dy, dz, blockBase, blockBaseMeta);
/* 654 */                     setBlock(world, dx + 2, dy + 1, dz, stairsBlock, 2, false);
/*     */                   }
/*     */                   
/* 657 */                   if ((!s) && (!se) && (!sw)) {
/* 658 */                     setBlock(world, dx, dy, dz + 2, blockBase, blockBaseMeta);
/* 659 */                     setBlock(world, dx, dy + 1, dz + 2, stairsBlock, 0, false);
/*     */                   }
/*     */                   
/* 662 */                   if ((!w) && (!nw) && (!sw)) {
/* 663 */                     setBlock(world, dx - 2, dy, dz, blockBase, blockBaseMeta);
/* 664 */                     setBlock(world, dx - 2, dy + 1, dz, stairsBlock, 2, false);
/*     */                   }
/*     */                   
/* 667 */                   guardDist++; if (guardDist > 200) {
/* 668 */                     spawnGuard(world, dx, dy, dz);
/* 669 */                     guardDist = 0;
/*     */                   }
/*     */                 }
/*     */                 else {
/* 673 */                   int distCheck = 4;
/* 674 */                   boolean gate = (a[x][z] == 3) && (((x > distCheck) && (x < a.length - distCheck) && (a[(x - distCheck)][z] == 2) && (a[(x + distCheck)][z] == 2)) || ((z > distCheck) && (z < a[x].length - distCheck) && (a[x][(z - distCheck)] == 2) && (a[x][(z + distCheck)] == 2)));
/*     */                   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 680 */                   if ((gate) && 
/* 681 */                     (dy == startY - 3))
/*     */                   {
/* 683 */                     world.func_147449_b(dx, dy, dz, blockFence);
/* 684 */                     if ((a[(x + 1)][z] != 3) || (a[(x - 1)][z] != 3))
/*     */                     {
/* 686 */                       if (a[(x + 1)][z] == 3) {
/* 687 */                         world.func_147465_d(dx, dy, dz - 1, stairsBlock, 5, 2);
/* 688 */                         world.func_147465_d(dx, dy, dz + 1, stairsBlock, 5, 2);
/* 689 */                       } else if (a[(x - 1)][z] == 3) {
/* 690 */                         world.func_147465_d(dx, dy, dz - 1, stairsBlock, 4, 2);
/* 691 */                         world.func_147465_d(dx, dy, dz + 1, stairsBlock, 4, 2);
/* 692 */                       } else if ((a[x][(z + 1)] != 3) || (a[x][(z - 1)] != 3))
/*     */                       {
/* 694 */                         if (a[x][(z - 1)] == 3) {
/* 695 */                           world.func_147465_d(dx - 1, dy, dz, stairsBlock, 6, 2);
/* 696 */                           world.func_147465_d(dx + 1, dy, dz, stairsBlock, 6, 2);
/* 697 */                         } else if (a[x][(z + 1)] == 3) {
/* 698 */                           world.func_147465_d(dx - 1, dy, dz, stairsBlock, 7, 2);
/* 699 */                           world.func_147465_d(dx + 1, dy, dz, stairsBlock, 7, 2);
/*     */                         }
/*     */                       }
/*     */                     }
/*     */                   }
/* 704 */                   if ((!gate) || (dy > startY - 3)) {
/* 705 */                     setBlock(world, dx, dy, dz, blockBase, blockBaseMeta);
/*     */                     
/* 707 */                     boolean ng = a[x][(z - 1)] == 3;
/* 708 */                     boolean sg = a[x][(z + 1)] == 3;
/* 709 */                     boolean eg = a[(x + 1)][z] == 3;
/* 710 */                     boolean wg = a[(x - 1)][z] == 3;
/*     */                     
/* 712 */                     if (!ng) {
/* 713 */                       setBlock(world, dx, dy, dz - 1, blockBase, blockBaseMeta);
/*     */                     }
/* 715 */                     if ((!ng) && (!eg)) {
/* 716 */                       setBlock(world, dx + 1, dy, dz - 1, blockBase, blockBaseMeta);
/*     */                     }
/* 718 */                     if ((!ng) && (!wg)) {
/* 719 */                       setBlock(world, dx - 1, dy, dz - 1, blockBase, blockBaseMeta);
/*     */                     }
/* 721 */                     if (!eg) {
/* 722 */                       setBlock(world, dx + 1, dy, dz, blockBase, blockBaseMeta);
/*     */                     }
/* 724 */                     if (!sg) {
/* 725 */                       setBlock(world, dx, dy, dz + 1, blockBase, blockBaseMeta);
/*     */                     }
/* 727 */                     if ((!sg) && (!eg)) {
/* 728 */                       setBlock(world, dx + 1, dy, dz + 1, blockBase, blockBaseMeta);
/*     */                     }
/* 730 */                     if ((!sg) && (!wg)) {
/* 731 */                       setBlock(world, dx - 1, dy, dz + 1, blockBase, blockBaseMeta);
/*     */                     }
/* 733 */                     if (!wg)
/* 734 */                       setBlock(world, dx - 1, dy, dz, blockBase, blockBaseMeta);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private static void spawnGuard(World world, int x, int y, int z) {
/* 745 */       EntityVillageGuard guard = new EntityVillageGuard(world);
/* 746 */       guard.func_70012_b(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
/* 747 */       guard.func_110163_bv();
/* 748 */       guard.func_110161_a(null);
/* 749 */       world.func_72838_d(guard);
/*     */     }
/*     */     
/*     */     private static void setBlock(World world, int x, int y, int z, Block block, int meta) {
/* 753 */       setBlock(world, x, y, z, block, meta, true);
/*     */     }
/*     */     
/*     */     private static void setBlock(World world, int x, int y, int z, Block block, int meta, boolean notStacked) {
/* 757 */       Block replaceBlock = world.func_147439_a(x, y, z);
/* 758 */       Material material = replaceBlock.func_149688_o();
/* 759 */       if ((material.func_76222_j()) || (material == Material.field_151584_j) || (material == Material.field_151575_d) || (material == Material.field_151585_k))
/*     */       {
/*     */ 
/* 762 */         world.func_147465_d(x, y, z, block, meta, 2);
/*     */       }
/*     */     }
/*     */     
/*     */     public static class BlockVillageWallGen extends BlockBaseContainer {
/*     */       public BlockVillageWallGen() {
/* 768 */         super(TileEntityVillageWallGen.class);
/* 769 */         this.registerWithCreateTab = false;
/* 770 */         func_149722_s();
/* 771 */         func_149752_b(10000.0F);
/*     */       }
/*     */       
/*     */       public static class TileEntityVillageWallGen extends TileEntityBase
/*     */       {
/*     */         private List<WorldHandlerVillageDistrict.Wall.StructureBounds> bb;
/*     */         private BiomeGenBase biome;
/*     */         private boolean desert;
/*     */         
/*     */         public void func_145845_h() {
/* 781 */           super.func_145845_h();
/* 782 */           if ((!this.field_145850_b.field_72995_K) && (this.bb != null) && (this.ticks > 40L)) {
/* 783 */             WorldHandlerVillageDistrict.Wall.placeWalls(this.field_145850_b, this.bb, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.biome, this.desert);
/*     */             
/* 785 */             this.bb = null;
/* 786 */             this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           }
/* 788 */           else if ((!this.field_145850_b.field_72995_K) && (this.ticks > 1000L)) {
/* 789 */             this.bb = null;
/* 790 */             this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           }
/*     */         }
/*     */         
/*     */         public void setStructure(List pieces, StructureVillagePieces.Start start)
/*     */         {
/* 796 */           this.biome = start.biome;
/* 797 */           this.desert = start.field_74927_b;
/* 798 */           this.bb = new ArrayList();
/* 799 */           for (Object obj : pieces) {
/* 800 */             if ((obj instanceof StructureVillagePieces.Path)) {
/* 801 */               this.bb.add(new WorldHandlerVillageDistrict.Wall.StructureBounds((StructureVillagePieces.Path)obj, 20, 7));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WorldHandlerVillageDistrict.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */