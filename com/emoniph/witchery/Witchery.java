/*     */ package com.emoniph.witchery;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
/*     */ import com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf;
/*     */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.client.KeyboardHandler;
/*     */ import com.emoniph.witchery.client.PlayerRender;
/*     */ import com.emoniph.witchery.common.ChantCommand;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.ServerTickEvents;
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import com.emoniph.witchery.dimension.WorldProviderMirror;
/*     */ import com.emoniph.witchery.dimension.WorldProviderTorment;
/*     */ import com.emoniph.witchery.integration.ModHookArsMagica2;
/*     */ import com.emoniph.witchery.integration.ModHookBaubles;
/*     */ import com.emoniph.witchery.integration.ModHookBloodMagic;
/*     */ import com.emoniph.witchery.integration.ModHookForestry;
/*     */ import com.emoniph.witchery.integration.ModHookManager;
/*     */ import com.emoniph.witchery.integration.ModHookMineFactoryReloaded;
/*     */ import com.emoniph.witchery.integration.ModHookMorph;
/*     */ import com.emoniph.witchery.integration.ModHookMystCraft;
/*     */ import com.emoniph.witchery.integration.ModHookThaumcraft4;
/*     */ import com.emoniph.witchery.integration.ModHookTinkersConstruct;
/*     */ import com.emoniph.witchery.integration.ModHookTreecapitator;
/*     */ import com.emoniph.witchery.integration.ModHookWaila;
/*     */ import com.emoniph.witchery.item.DispenseBehaviourItemBrew;
/*     */ import com.emoniph.witchery.item.DispenseBehaviourItemGeneral;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.worldgen.BiomeManager;
/*     */ import com.emoniph.witchery.worldgen.ComponentVillageApothecary;
/*     */ import com.emoniph.witchery.worldgen.ComponentVillageBookShop;
/*     */ import com.emoniph.witchery.worldgen.ComponentVillageWitchHut;
/*     */ import com.emoniph.witchery.worldgen.WitcheryWorldGenerator;
/*     */ import com.emoniph.witchery.worldgen.WorldHandlerVillageApothecary;
/*     */ import com.emoniph.witchery.worldgen.WorldHandlerVillageBookShop;
/*     */ import com.emoniph.witchery.worldgen.WorldHandlerVillageDistrict;
/*     */ import com.emoniph.witchery.worldgen.WorldHandlerVillageWitchHut;
/*     */ import com.google.common.collect.Lists;
/*     */ import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Loader;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
/*     */ import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLServerStartingEvent;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry.Type;
/*     */ import cpw.mods.fml.common.registry.VillagerRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IRegistry;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.MapGenStructureIO;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.ForgeChunkManager;
/*     */ import net.minecraftforge.common.ForgeChunkManager.OrderedLoadingCallback;
/*     */ import net.minecraftforge.common.ForgeChunkManager.Ticket;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid="witchery", name="Witchery", version="0.24.1", guiFactory="com.emoniph.witchery.util.WitcheryGuiFactory", dependencies="required-after:Forge@[10.13.2.1277,);after:MineFactoryReloaded;after:NotEnoughItems;after:Waila;after:ForgeMultipart;after:AWWayofTime")
/*     */ public class Witchery
/*     */ {
/*     */   public static final String MOD_ID = "witchery";
/*     */   public static final String MOD_PREFIX = "WITC";
/*     */   @SidedProxy(clientSide="com.emoniph.witchery.client.ClientProxy", serverSide="com.emoniph.witchery.common.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */   @Mod.Instance("witchery")
/*     */   public static Witchery instance;
/*  95 */   public static final PacketPipeline packetPipeline = new PacketPipeline();
/*  96 */   public static WitcheryRecipes Recipes = new WitcheryRecipes();
/*  97 */   public static final ModHookManager modHooks = new ModHookManager();
/*     */   
/*     */   public static WitcheryPotions Potions;
/*     */   public static WitcheryFluids Fluids;
/*     */   public static WitcheryBlocks Blocks;
/*     */   public static WitcheryItems Items;
/*     */   public static WitcheryEntities Entities;
/*     */   private static WitcheryWorldGenerator worldGenerator;
/*     */   public static File configDirectoryPath;
/*     */   public static Configuration config;
/*     */   public static Configuration config_debug;
/*     */   public static boolean isDeathChestModInstalled;
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event)
/*     */   {
/* 113 */     if (instance != this) {
/* 114 */       Log.instance().warning("instance static not set");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 119 */     configDirectoryPath = event.getModConfigurationDirectory();
/* 120 */     config = new Configuration(new File(String.format("%s/%s.cfg", new Object[] { configDirectoryPath, "witchery" })));
/* 121 */     config_debug = new Configuration(new File(String.format("%s/%s_debug.cfg", new Object[] { configDirectoryPath, "witchery" })));
/* 122 */     Config.instance().init(config, config_debug);
/*     */     
/* 124 */     worldGenerator = new WitcheryWorldGenerator();
/* 125 */     GameRegistry.registerWorldGenerator(worldGenerator, 0);
/*     */     
/* 127 */     if (Config.instance().generateApothecaries) {
/* 128 */       WorldHandlerVillageApothecary villageApothecaryHandler = new WorldHandlerVillageApothecary();
/* 129 */       VillagerRegistry.instance().registerVillagerId(Config.instance().apothecaryID);
/* 130 */       proxy.registerVillagers();
/* 131 */       VillagerRegistry.instance().registerVillageCreationHandler(villageApothecaryHandler);
/* 132 */       VillagerRegistry.instance().registerVillageTradeHandler(Config.instance().apothecaryID, villageApothecaryHandler);
/*     */       try {
/* 134 */         MapGenStructureIO.func_143031_a(ComponentVillageApothecary.class, "witchery:Apothecary");
/*     */       }
/*     */       catch (Throwable e) {}
/*     */     }
/* 138 */     if (Config.instance().generateWitchHuts) {
/* 139 */       WorldHandlerVillageWitchHut villageWitchHutHandler = new WorldHandlerVillageWitchHut();
/* 140 */       VillagerRegistry.instance().registerVillageCreationHandler(villageWitchHutHandler);
/*     */       try {
/* 142 */         MapGenStructureIO.func_143031_a(ComponentVillageWitchHut.class, "witchery:witchhut");
/*     */       }
/*     */       catch (Throwable e) {}
/*     */     }
/* 146 */     if (Config.instance().generateBookShops) {
/* 147 */       WorldHandlerVillageBookShop villageBookShopHandler = new WorldHandlerVillageBookShop();
/* 148 */       VillagerRegistry.instance().registerVillageCreationHandler(villageBookShopHandler);
/*     */       try {
/* 150 */         MapGenStructureIO.func_143031_a(ComponentVillageBookShop.class, "witchery:bookshop");
/*     */       }
/*     */       catch (Throwable e) {}
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 160 */     WorldHandlerVillageDistrict.preInit();
/*     */     
/* 162 */     proxy.preInit();
/* 163 */     packetPipeline.preInit();
/*     */     
/* 165 */     Potions = new WitcheryPotions();
/* 166 */     Fluids = new WitcheryFluids();
/* 167 */     Blocks = new WitcheryBlocks();
/* 168 */     Items = new WitcheryItems();
/* 169 */     Entities = new WitcheryEntities();
/*     */     
/* 171 */     Recipes.preInit();
/* 172 */     Potions.preInit();
/*     */     
/* 174 */     FMLCommonHandler.instance().bus().register(new ServerTickEvents());
/*     */     
/* 176 */     if (event.getSide() == Side.CLIENT) {
/* 177 */       FMLCommonHandler.instance().bus().register(new PlayerRender());
/* 178 */       FMLCommonHandler.instance().bus().register(new KeyboardHandler());
/*     */     }
/*     */   }
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void onMissingMappings(FMLMissingMappingsEvent event) {
/* 184 */     for (FMLMissingMappingsEvent.MissingMapping missing : event.get()) {
/* 185 */       if (missing.name != null) {
/* 186 */         int index = missing.name.lastIndexOf(':');
/* 187 */         String strippedName = (index != -1) && (missing.name.length() > index) ? missing.name.substring(index + 1) : missing.name;
/* 188 */         if (missing.type == GameRegistry.Type.BLOCK) {
/* 189 */           Block replacement = GameRegistry.findBlock("witchery", strippedName);
/* 190 */           if (replacement != null) {
/* 191 */             missing.remap(replacement);
/*     */           }
/* 193 */         } else if (missing.type == GameRegistry.Type.ITEM) {
/* 194 */           Item replacement = GameRegistry.findItem("witchery", strippedName);
/* 195 */           if (replacement != null) {
/* 196 */             missing.remap(replacement);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
/* 205 */     if (event.modID.equals("witchery")) {
/* 206 */       Config.instance().sync();
/*     */     }
/*     */   }
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/* 212 */     FMLCommonHandler.instance().bus().register(instance);
/*     */     
/* 214 */     WorldHandlerVillageDistrict.init();
/*     */     
/* 216 */     packetPipeline.init();
/*     */     
/* 218 */     Entities.init();
/*     */     
/* 220 */     NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
/* 221 */     DimensionManager.registerProviderType(Config.instance().dimensionDreamID, WorldProviderDreamWorld.class, false);
/* 222 */     DimensionManager.registerDimension(Config.instance().dimensionDreamID, Config.instance().dimensionDreamID);
/*     */     
/* 224 */     DimensionManager.registerProviderType(Config.instance().dimensionTormentID, WorldProviderTorment.class, false);
/* 225 */     DimensionManager.registerDimension(Config.instance().dimensionTormentID, Config.instance().dimensionTormentID);
/*     */     
/* 227 */     DimensionManager.registerProviderType(Config.instance().dimensionMirrorID, WorldProviderMirror.class, false);
/* 228 */     DimensionManager.registerDimension(Config.instance().dimensionMirrorID, Config.instance().dimensionMirrorID);
/*     */     
/* 230 */     MinecraftForge.addGrassSeed(new ItemStack(Items.SEEDS_ARTICHOKE), 3);
/* 231 */     MinecraftForge.addGrassSeed(new ItemStack(Items.SEEDS_BELLADONNA), 4);
/* 232 */     MinecraftForge.addGrassSeed(new ItemStack(Items.SEEDS_MANDRAKE), 5);
/* 233 */     MinecraftForge.addGrassSeed(new ItemStack(Items.SEEDS_SNOWBELL), 2);
/* 234 */     MinecraftForge.addGrassSeed(new ItemStack(Items.SEEDS_WOLFSBANE), 1);
/* 235 */     MinecraftForge.addGrassSeed(new ItemStack(Items.SEEDS_GARLIC), 1);
/*     */     
/* 237 */     proxy.registerHandlers();
/* 238 */     proxy.registerServerHandlers();
/* 239 */     proxy.registerRenderers();
/*     */     
/* 241 */     isDeathChestModInstalled = (Config.instance().respectOtherDeathChestMods) && ((Loader.isModLoaded("tombstone")) || (Loader.isModLoaded("OpenBlocks")) || (Loader.isModLoaded("Taigore_InventorySaver")) || (Loader.isModLoaded("KeepItemsOnDeath")));
/*     */     
/* 243 */     modHooks.register(ModHookArsMagica2.class);
/* 244 */     modHooks.register(ModHookBloodMagic.class);
/* 245 */     modHooks.register(ModHookForestry.class);
/* 246 */     modHooks.register(ModHookMineFactoryReloaded.class);
/* 247 */     modHooks.register(ModHookMystCraft.class);
/* 248 */     modHooks.register(ModHookThaumcraft4.class);
/* 249 */     modHooks.register(ModHookTinkersConstruct.class);
/* 250 */     modHooks.register(ModHookTreecapitator.class);
/* 251 */     modHooks.register(ModHookWaila.class);
/* 252 */     modHooks.register(ModHookMorph.class);
/* 253 */     modHooks.register(ModHookBaubles.class);
/*     */     
/* 255 */     modHooks.init();
/*     */     
/* 257 */     Potions.init();
/* 258 */     Recipes.init();
/* 259 */     WitcheryBrewRegistry.INSTANCE.init();
/*     */   }
/*     */   
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void postInit(FMLPostInitializationEvent event)
/*     */   {
/* 266 */     Recipes.postInit();
/* 267 */     modHooks.postInit();
/*     */     try
/*     */     {
/* 270 */       BiomeManager.addModBiomes();
/*     */     } catch (Exception e) {
/* 272 */       Log.instance().warning(e, "Failed to add external mod biome postInit compatability");
/*     */     }
/*     */     
/* 275 */     proxy.postInit();
/* 276 */     proxy.registerEvents();
/* 277 */     BlockDispenser.field_149943_a.func_82595_a(Items.GENERIC, new DispenseBehaviourItemGeneral());
/* 278 */     BlockDispenser.field_149943_a.func_82595_a(Items.BREW, new DispenseBehaviourItemBrew());
/* 279 */     BlockDispenser.field_149943_a.func_82595_a(Items.BREW_ENDLESS_WATER, new DispenseBehaviourItemBrew());
/* 280 */     BlockDispenser.field_149943_a.func_82595_a(Items.field_151069_bo, new DispenseBehaviourItemBrew());
/* 281 */     BlockDispenser.field_149943_a.func_82595_a(Items.SUN_GRENADE, new DispenseBehaviourItemBrew());
/* 282 */     BlockDispenser.field_149943_a.func_82595_a(Items.DUP_GRENADE, new DispenseBehaviourItemBrew());
/* 283 */     ForgeChunkManager.setForcedChunkLoadingCallback(instance, new ChunkloadCallback());
/*     */   }
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void serverLoad(FMLServerStartingEvent event) {
/* 288 */     event.registerServerCommand(new ChantCommand());
/*     */     
/* 290 */     PowerSources.initiate();
/* 291 */     BlockAreaMarker.AreaMarkerRegistry.serverStart();
/* 292 */     worldGenerator.initiate();
/*     */   }
/*     */   
/*     */   public static class ChunkloadCallback implements ForgeChunkManager.OrderedLoadingCallback
/*     */   {
/*     */     public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
/* 298 */       for (ForgeChunkManager.Ticket ticket : tickets) {
/* 299 */         int posX = ticket.getModData().func_74762_e("poppetX");
/* 300 */         int posY = ticket.getModData().func_74762_e("poppetY");
/* 301 */         int posZ = ticket.getModData().func_74762_e("poppetZ");
/* 302 */         BlockPoppetShelf.TileEntityPoppetShelf tileEntity = (BlockPoppetShelf.TileEntityPoppetShelf)world.func_147438_o(posX, posY, posZ);
/* 303 */         tileEntity.forceChunkLoading(ticket);
/*     */       }
/*     */     }
/*     */     
/*     */     public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount)
/*     */     {
/* 309 */       List<ForgeChunkManager.Ticket> validTickets = Lists.newArrayList();
/* 310 */       for (ForgeChunkManager.Ticket ticket : tickets) {
/* 311 */         int posX = ticket.getModData().func_74762_e("poppetX");
/* 312 */         int posY = ticket.getModData().func_74762_e("poppetY");
/* 313 */         int posZ = ticket.getModData().func_74762_e("poppetZ");
/* 314 */         Block block = world.func_147439_a(posX, posY, posZ);
/* 315 */         if (block == Witchery.Blocks.POPPET_SHELF) {
/* 316 */           validTickets.add(ticket);
/*     */         }
/*     */       }
/* 319 */       return validTickets;
/*     */     }
/*     */   }
/*     */   
/*     */   public static String resource(String id) {
/* 324 */     String s = StatCollector.func_74838_a(id);
/* 325 */     return s.replace("|", "\n").replace("{", "§");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/Witchery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */