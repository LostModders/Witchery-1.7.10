/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockAltar.TileEntityAltar;
/*     */ import com.emoniph.witchery.blocks.BlockAltarGUI;
/*     */ import com.emoniph.witchery.blocks.BlockAreaMarker.TileEntityAreaCurseProtect;
/*     */ import com.emoniph.witchery.blocks.BlockAreaMarker.TileEntityAreaTeleportPullProtect;
/*     */ import com.emoniph.witchery.blocks.BlockBeartrap.TileEntityBeartrap;
/*     */ import com.emoniph.witchery.blocks.BlockBloodCrucible.TileEntityBloodCrucible;
/*     */ import com.emoniph.witchery.blocks.BlockBrazier.TileEntityBrazier;
/*     */ import com.emoniph.witchery.blocks.BlockCandelabra.TileEntityCandelabra;
/*     */ import com.emoniph.witchery.blocks.BlockChalice.TileEntityChalice;
/*     */ import com.emoniph.witchery.blocks.BlockCoffin.TileEntityCoffin;
/*     */ import com.emoniph.witchery.blocks.BlockCrystalBall.TileEntityCrystalBall;
/*     */ import com.emoniph.witchery.blocks.BlockDemonHeart.TileEntityDemonHeart;
/*     */ import com.emoniph.witchery.blocks.BlockDistillery.TileEntityDistillery;
/*     */ import com.emoniph.witchery.blocks.BlockDistilleryGUI;
/*     */ import com.emoniph.witchery.blocks.BlockDreamCatcher.TileEntityDreamCatcher;
/*     */ import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
/*     */ import com.emoniph.witchery.blocks.BlockFumeFunnel.TileEntityFumeFunnel;
/*     */ import com.emoniph.witchery.blocks.BlockGarlicGarland.TileEntityGarlicGarland;
/*     */ import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
/*     */ import com.emoniph.witchery.blocks.BlockKettle.TileEntityKettle;
/*     */ import com.emoniph.witchery.blocks.BlockLeechChest.TileEntityLeechChest;
/*     */ import com.emoniph.witchery.blocks.BlockMirror.TileEntityMirror;
/*     */ import com.emoniph.witchery.blocks.BlockPlacedItem.TileEntityPlacedItem;
/*     */ import com.emoniph.witchery.blocks.BlockSilverVat.TileEntitySilverVat;
/*     */ import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
/*     */ import com.emoniph.witchery.blocks.BlockSpinningWheelGUI;
/*     */ import com.emoniph.witchery.blocks.BlockStatueGoddess.TileEntityStatueGoddess;
/*     */ import com.emoniph.witchery.blocks.BlockStatueOfWorship.TileEntityStatueOfWorship;
/*     */ import com.emoniph.witchery.blocks.BlockStatueWerewolf.TileEntityStatueWerewolf;
/*     */ import com.emoniph.witchery.blocks.BlockWitchesOven.TileEntityWitchesOven;
/*     */ import com.emoniph.witchery.blocks.BlockWitchesOvenGUI;
/*     */ import com.emoniph.witchery.blocks.BlockWolfHead.TileEntityWolfHead;
/*     */ import com.emoniph.witchery.brewing.EntityBrew;
/*     */ import com.emoniph.witchery.brewing.EntityDroplet;
/*     */ import com.emoniph.witchery.brewing.EntitySplatter;
/*     */ import com.emoniph.witchery.brewing.RenderBrew;
/*     */ import com.emoniph.witchery.brewing.RenderBrewGas;
/*     */ import com.emoniph.witchery.brewing.RenderBrewLiquid;
/*     */ import com.emoniph.witchery.brewing.RenderCauldron;
/*     */ import com.emoniph.witchery.brewing.RenderDroplet;
/*     */ import com.emoniph.witchery.brewing.RenderSplatter;
/*     */ import com.emoniph.witchery.brewing.RenderWitchVine;
/*     */ import com.emoniph.witchery.brewing.TileEntityCauldron;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions.ClientEventHooks;
/*     */ import com.emoniph.witchery.client.gui.GuiScreenBiomeBook;
/*     */ import com.emoniph.witchery.client.gui.GuiScreenMarkupBook;
/*     */ import com.emoniph.witchery.client.gui.GuiScreenWitchcraftBook;
/*     */ import com.emoniph.witchery.client.model.ModelDemon;
/*     */ import com.emoniph.witchery.client.model.ModelEnt;
/*     */ import com.emoniph.witchery.client.model.ModelFamiliarPig;
/*     */ import com.emoniph.witchery.client.model.ModelGoblin;
/*     */ import com.emoniph.witchery.client.model.ModelGoblinGulg;
/*     */ import com.emoniph.witchery.client.model.ModelGoblinMog;
/*     */ import com.emoniph.witchery.client.model.ModelHellhound;
/*     */ import com.emoniph.witchery.client.model.ModelHornedAvatar;
/*     */ import com.emoniph.witchery.client.model.ModelLeonard;
/*     */ import com.emoniph.witchery.client.model.ModelLilith;
/*     */ import com.emoniph.witchery.client.model.ModelMandrake;
/*     */ import com.emoniph.witchery.client.model.ModelMonkey;
/*     */ import com.emoniph.witchery.client.model.ModelOwl;
/*     */ import com.emoniph.witchery.client.model.ModelToad;
/*     */ import com.emoniph.witchery.client.model.ModelTreefyd;
/*     */ import com.emoniph.witchery.client.model.ModelWolfman;
/*     */ import com.emoniph.witchery.client.particle.NaturePowerFX;
/*     */ import com.emoniph.witchery.client.renderer.RenderAlluringSkull;
/*     */ import com.emoniph.witchery.client.renderer.RenderBabaYaga;
/*     */ import com.emoniph.witchery.client.renderer.RenderBanshee;
/*     */ import com.emoniph.witchery.client.renderer.RenderBeartrap;
/*     */ import com.emoniph.witchery.client.renderer.RenderBlockItem;
/*     */ import com.emoniph.witchery.client.renderer.RenderBloodCrucible;
/*     */ import com.emoniph.witchery.client.renderer.RenderBolt;
/*     */ import com.emoniph.witchery.client.renderer.RenderBrazier;
/*     */ import com.emoniph.witchery.client.renderer.RenderBrewBottle;
/*     */ import com.emoniph.witchery.client.renderer.RenderBroom;
/*     */ import com.emoniph.witchery.client.renderer.RenderCandelabra;
/*     */ import com.emoniph.witchery.client.renderer.RenderCaneSword;
/*     */ import com.emoniph.witchery.client.renderer.RenderChalice;
/*     */ import com.emoniph.witchery.client.renderer.RenderCoffin;
/*     */ import com.emoniph.witchery.client.renderer.RenderCorpse;
/*     */ import com.emoniph.witchery.client.renderer.RenderCovenWitch;
/*     */ import com.emoniph.witchery.client.renderer.RenderCrystalBall;
/*     */ import com.emoniph.witchery.client.renderer.RenderDarkMark;
/*     */ import com.emoniph.witchery.client.renderer.RenderDeath;
/*     */ import com.emoniph.witchery.client.renderer.RenderDeathsHand;
/*     */ import com.emoniph.witchery.client.renderer.RenderDemon;
/*     */ import com.emoniph.witchery.client.renderer.RenderDemonHeart;
/*     */ import com.emoniph.witchery.client.renderer.RenderDreamCatcher;
/*     */ import com.emoniph.witchery.client.renderer.RenderEnt;
/*     */ import com.emoniph.witchery.client.renderer.RenderFamiliar;
/*     */ import com.emoniph.witchery.client.renderer.RenderFetish;
/*     */ import com.emoniph.witchery.client.renderer.RenderFetish.RenderFetishBlockItem;
/*     */ import com.emoniph.witchery.client.renderer.RenderFollower;
/*     */ import com.emoniph.witchery.client.renderer.RenderFumeFunnel;
/*     */ import com.emoniph.witchery.client.renderer.RenderGarlicGarland;
/*     */ import com.emoniph.witchery.client.renderer.RenderGoblin;
/*     */ import com.emoniph.witchery.client.renderer.RenderGoblinGulg;
/*     */ import com.emoniph.witchery.client.renderer.RenderGoblinMog;
/*     */ import com.emoniph.witchery.client.renderer.RenderGoddess;
/*     */ import com.emoniph.witchery.client.renderer.RenderGrassper;
/*     */ import com.emoniph.witchery.client.renderer.RenderGrenade;
/*     */ import com.emoniph.witchery.client.renderer.RenderHandBow;
/*     */ import com.emoniph.witchery.client.renderer.RenderHellhound;
/*     */ import com.emoniph.witchery.client.renderer.RenderHornedAvatar;
/*     */ import com.emoniph.witchery.client.renderer.RenderHuntsmanSpear;
/*     */ import com.emoniph.witchery.client.renderer.RenderIllusion;
/*     */ import com.emoniph.witchery.client.renderer.RenderImp;
/*     */ import com.emoniph.witchery.client.renderer.RenderKettle;
/*     */ import com.emoniph.witchery.client.renderer.RenderLeechChest;
/*     */ import com.emoniph.witchery.client.renderer.RenderLeonard;
/*     */ import com.emoniph.witchery.client.renderer.RenderLilith;
/*     */ import com.emoniph.witchery.client.renderer.RenderLordOfTorment;
/*     */ import com.emoniph.witchery.client.renderer.RenderMandrake;
/*     */ import com.emoniph.witchery.client.renderer.RenderMindrake;
/*     */ import com.emoniph.witchery.client.renderer.RenderMirror;
/*     */ import com.emoniph.witchery.client.renderer.RenderMirrorFace;
/*     */ import com.emoniph.witchery.client.renderer.RenderMysticBranch;
/*     */ import com.emoniph.witchery.client.renderer.RenderNightmare;
/*     */ import com.emoniph.witchery.client.renderer.RenderOwl;
/*     */ import com.emoniph.witchery.client.renderer.RenderParasyticLouse;
/*     */ import com.emoniph.witchery.client.renderer.RenderPitGrass;
/*     */ import com.emoniph.witchery.client.renderer.RenderPlacedItem;
/*     */ import com.emoniph.witchery.client.renderer.RenderPoltergeist;
/*     */ import com.emoniph.witchery.client.renderer.RenderPoppetChest;
/*     */ import com.emoniph.witchery.client.renderer.RenderSilverVat;
/*     */ import com.emoniph.witchery.client.renderer.RenderSpectre;
/*     */ import com.emoniph.witchery.client.renderer.RenderSpellEffect;
/*     */ import com.emoniph.witchery.client.renderer.RenderSpinningWheel;
/*     */ import com.emoniph.witchery.client.renderer.RenderSpirit;
/*     */ import com.emoniph.witchery.client.renderer.RenderStatueMandrake;
/*     */ import com.emoniph.witchery.client.renderer.RenderStatueOfWorship;
/*     */ import com.emoniph.witchery.client.renderer.RenderStatueWerewolf;
/*     */ import com.emoniph.witchery.client.renderer.RenderStatueWolf;
/*     */ import com.emoniph.witchery.client.renderer.RenderStockade;
/*     */ import com.emoniph.witchery.client.renderer.RenderToad;
/*     */ import com.emoniph.witchery.client.renderer.RenderTreefyd;
/*     */ import com.emoniph.witchery.client.renderer.RenderVampire;
/*     */ import com.emoniph.witchery.client.renderer.RenderVillageGuard;
/*     */ import com.emoniph.witchery.client.renderer.RenderWingedMonkey;
/*     */ import com.emoniph.witchery.client.renderer.RenderWitchCat;
/*     */ import com.emoniph.witchery.client.renderer.RenderWitchHand;
/*     */ import com.emoniph.witchery.client.renderer.RenderWitchHunter;
/*     */ import com.emoniph.witchery.client.renderer.RenderWitchProjectile;
/*     */ import com.emoniph.witchery.client.renderer.RenderWitchesOven;
/*     */ import com.emoniph.witchery.client.renderer.RenderWolfHead;
/*     */ import com.emoniph.witchery.client.renderer.RenderWolfman;
/*     */ import com.emoniph.witchery.common.CommonProxy;
/*     */ import com.emoniph.witchery.entity.EntityAttackBat;
/*     */ import com.emoniph.witchery.entity.EntityBabaYaga;
/*     */ import com.emoniph.witchery.entity.EntityBanshee;
/*     */ import com.emoniph.witchery.entity.EntityBolt;
/*     */ import com.emoniph.witchery.entity.EntityBroom;
/*     */ import com.emoniph.witchery.entity.EntityCorpse;
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import com.emoniph.witchery.entity.EntityDarkMark;
/*     */ import com.emoniph.witchery.entity.EntityDeath;
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import com.emoniph.witchery.entity.EntityEnt;
/*     */ import com.emoniph.witchery.entity.EntityFamiliar;
/*     */ import com.emoniph.witchery.entity.EntityFollower;
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import com.emoniph.witchery.entity.EntityGoblinGulg;
/*     */ import com.emoniph.witchery.entity.EntityGoblinMog;
/*     */ import com.emoniph.witchery.entity.EntityGrenade;
/*     */ import com.emoniph.witchery.entity.EntityHellhound;
/*     */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*     */ import com.emoniph.witchery.entity.EntityIllusionCreeper;
/*     */ import com.emoniph.witchery.entity.EntityIllusionSpider;
/*     */ import com.emoniph.witchery.entity.EntityIllusionZombie;
/*     */ import com.emoniph.witchery.entity.EntityImp;
/*     */ import com.emoniph.witchery.entity.EntityLeonard;
/*     */ import com.emoniph.witchery.entity.EntityLilith;
/*     */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*     */ import com.emoniph.witchery.entity.EntityLostSoul;
/*     */ import com.emoniph.witchery.entity.EntityMandrake;
/*     */ import com.emoniph.witchery.entity.EntityMindrake;
/*     */ import com.emoniph.witchery.entity.EntityMirrorFace;
/*     */ import com.emoniph.witchery.entity.EntityNightmare;
/*     */ import com.emoniph.witchery.entity.EntityOwl;
/*     */ import com.emoniph.witchery.entity.EntityParasyticLouse;
/*     */ import com.emoniph.witchery.entity.EntityPoltergeist;
/*     */ import com.emoniph.witchery.entity.EntityReflection;
/*     */ import com.emoniph.witchery.entity.EntitySpectre;
/*     */ import com.emoniph.witchery.entity.EntitySpellEffect;
/*     */ import com.emoniph.witchery.entity.EntitySpirit;
/*     */ import com.emoniph.witchery.entity.EntityToad;
/*     */ import com.emoniph.witchery.entity.EntityTreefyd;
/*     */ import com.emoniph.witchery.entity.EntityVampire;
/*     */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*     */ import com.emoniph.witchery.entity.EntityVillagerWere;
/*     */ import com.emoniph.witchery.entity.EntityWingedMonkey;
/*     */ import com.emoniph.witchery.entity.EntityWitchCat;
/*     */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*     */ import com.emoniph.witchery.entity.EntityWitchProjectile;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import com.emoniph.witchery.item.ItemBrewBag.InventoryBrewBag;
/*     */ import com.emoniph.witchery.item.ItemBrewBagGUI;
/*     */ import com.emoniph.witchery.item.ItemEarmuffs.ClientEventHooks;
/*     */ import com.emoniph.witchery.item.ItemLeonardsUrn.InventoryLeonardsUrn;
/*     */ import com.emoniph.witchery.item.ItemLeonardsUrnGUI;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.client.registry.ClientRegistry;
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*     */ import cpw.mods.fml.common.registry.VillagerRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelCreeper;
/*     */ import net.minecraft.client.model.ModelOcelot;
/*     */ import net.minecraft.client.model.ModelSpider;
/*     */ import net.minecraft.client.model.ModelZombie;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.particle.EntitySmokeFX;
/*     */ import net.minecraft.client.renderer.entity.RenderVillager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ClientProxy extends CommonProxy
/*     */ {
/*     */   public static int RENDER_ID;
/*     */   
/*     */   public void registerRenderers()
/*     */   {
/* 244 */     RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
/*     */     
/* 246 */     MinecraftForgeClient.registerItemRenderer(Witchery.Items.WITCH_HAND, new RenderWitchHand());
/* 247 */     MinecraftForgeClient.registerItemRenderer(Witchery.Items.DEATH_HAND, new RenderDeathsHand());
/* 248 */     MinecraftForgeClient.registerItemRenderer(Witchery.Items.BREW_BAG, new RenderBrewBottle());
/* 249 */     MinecraftForgeClient.registerItemRenderer(Witchery.Items.HUNTSMANS_SPEAR, new RenderHuntsmanSpear());
/* 250 */     MinecraftForgeClient.registerItemRenderer(Witchery.Items.MYSTIC_BRANCH, new RenderMysticBranch());
/* 251 */     MinecraftForgeClient.registerItemRenderer(Witchery.Items.CROSSBOW_PISTOL, new RenderHandBow());
/* 252 */     MinecraftForgeClient.registerItemRenderer(Witchery.Items.CANE_SWORD, new RenderCaneSword());
/*     */     
/* 254 */     RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, new RenderDemon(new ModelDemon(), 0.5F));
/*     */     
/* 256 */     RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, new RenderBroom());
/* 257 */     RenderingRegistry.registerEntityRenderingHandler(EntityWitchProjectile.class, new RenderWitchProjectile(Witchery.Items.GENERIC));
/*     */     
/* 259 */     RenderingRegistry.registerEntityRenderingHandler(EntityFamiliar.class, new RenderFamiliar(new ModelFamiliarPig(), 0.8F));
/*     */     
/* 261 */     RenderingRegistry.registerEntityRenderingHandler(EntityMandrake.class, new RenderMandrake(new ModelMandrake(), 0.5F));
/*     */     
/* 263 */     RenderingRegistry.registerEntityRenderingHandler(EntityTreefyd.class, new RenderTreefyd(new ModelTreefyd(), 0.5F));
/*     */     
/* 265 */     RenderingRegistry.registerEntityRenderingHandler(EntityHornedHuntsman.class, new RenderHornedAvatar(new ModelHornedAvatar(), 0.5F));
/*     */     
/* 267 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpellEffect.class, new RenderSpellEffect(0.5F));
/* 268 */     RenderingRegistry.registerEntityRenderingHandler(EntityEnt.class, new RenderEnt(new ModelEnt(), 0.5F));
/* 269 */     RenderingRegistry.registerEntityRenderingHandler(EntityIllusionCreeper.class, new RenderIllusion(new ModelCreeper(), new ResourceLocation("textures/entity/creeper/creeper.png")));
/*     */     
/* 271 */     RenderingRegistry.registerEntityRenderingHandler(EntityIllusionSpider.class, new RenderIllusion(new ModelSpider(), new ResourceLocation("textures/entity/spider/spider.png")));
/*     */     
/* 273 */     RenderingRegistry.registerEntityRenderingHandler(EntityIllusionZombie.class, new RenderIllusion(new ModelZombie(), new ResourceLocation("textures/entity/zombie/zombie.png")));
/*     */     
/* 275 */     RenderingRegistry.registerEntityRenderingHandler(EntityOwl.class, new RenderOwl(new ModelOwl(), 0.5F));
/* 276 */     RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, new RenderToad(new ModelToad(), 0.5F));
/* 277 */     RenderingRegistry.registerEntityRenderingHandler(EntityWitchCat.class, new RenderWitchCat(new ModelOcelot(), 0.5F));
/*     */     
/* 279 */     RenderingRegistry.registerEntityRenderingHandler(EntityParasyticLouse.class, new RenderParasyticLouse());
/* 280 */     RenderingRegistry.registerEntityRenderingHandler(EntityBabaYaga.class, new RenderBabaYaga());
/* 281 */     RenderingRegistry.registerEntityRenderingHandler(EntityCovenWitch.class, new RenderCovenWitch());
/* 282 */     RenderingRegistry.registerEntityRenderingHandler(EntityCorpse.class, new RenderCorpse());
/* 283 */     RenderingRegistry.registerEntityRenderingHandler(EntityNightmare.class, new RenderNightmare());
/* 284 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpectre.class, new RenderSpectre());
/* 285 */     RenderingRegistry.registerEntityRenderingHandler(EntityPoltergeist.class, new RenderPoltergeist());
/* 286 */     RenderingRegistry.registerEntityRenderingHandler(EntityBanshee.class, new RenderBanshee());
/* 287 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpirit.class, new RenderSpirit());
/* 288 */     RenderingRegistry.registerEntityRenderingHandler(EntityDeath.class, new RenderDeath());
/* 289 */     RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, new RenderBolt());
/* 290 */     RenderingRegistry.registerEntityRenderingHandler(EntityWitchHunter.class, new RenderWitchHunter());
/* 291 */     RenderingRegistry.registerEntityRenderingHandler(EntityLordOfTorment.class, new RenderLordOfTorment());
/* 292 */     RenderingRegistry.registerEntityRenderingHandler(EntityImp.class, new RenderImp());
/* 293 */     RenderingRegistry.registerEntityRenderingHandler(EntityDarkMark.class, new RenderDarkMark());
/* 294 */     RenderingRegistry.registerEntityRenderingHandler(EntityMindrake.class, new RenderMindrake());
/* 295 */     RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, new RenderGoblin(new ModelGoblin(), 0.5F));
/*     */     
/* 297 */     RenderingRegistry.registerEntityRenderingHandler(EntityGoblinMog.class, new RenderGoblinMog(new ModelGoblinMog(), 0.5F));
/*     */     
/* 299 */     RenderingRegistry.registerEntityRenderingHandler(EntityGoblinGulg.class, new RenderGoblinGulg(new ModelGoblinGulg(), 0.5F));
/*     */     
/*     */ 
/* 302 */     RenderingRegistry.registerEntityRenderingHandler(EntityBrew.class, new RenderBrew(Witchery.Items.BREW));
/* 303 */     RenderingRegistry.registerEntityRenderingHandler(EntityDroplet.class, new RenderDroplet(Witchery.Items.BREW));
/*     */     
/*     */ 
/* 306 */     RenderingRegistry.registerEntityRenderingHandler(EntitySplatter.class, new RenderSplatter(Witchery.Items.BREW));
/*     */     
/*     */ 
/* 309 */     RenderingRegistry.registerEntityRenderingHandler(EntityLeonard.class, new RenderLeonard(new ModelLeonard(), 0.5F));
/*     */     
/* 311 */     RenderingRegistry.registerEntityRenderingHandler(EntityLostSoul.class, new RenderSpirit());
/* 312 */     RenderingRegistry.registerEntityRenderingHandler(EntityWolfman.class, new RenderWolfman(new ModelWolfman(), 0.5F));
/*     */     
/*     */ 
/* 315 */     RenderingRegistry.registerEntityRenderingHandler(EntityHellhound.class, new RenderHellhound(new ModelHellhound(), 0.5F));
/*     */     
/*     */ 
/* 318 */     RenderingRegistry.registerEntityRenderingHandler(EntityVillagerWere.class, new RenderVillager());
/*     */     
/* 320 */     RenderingRegistry.registerEntityRenderingHandler(EntityVillageGuard.class, new RenderVillageGuard());
/* 321 */     RenderingRegistry.registerEntityRenderingHandler(EntityVampire.class, new RenderVampire());
/*     */     
/* 323 */     RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(Witchery.Items.SUN_GRENADE));
/*     */     
/*     */ 
/* 326 */     RenderingRegistry.registerEntityRenderingHandler(EntityLilith.class, new RenderLilith(new ModelLilith(), 0.5F));
/*     */     
/*     */ 
/* 329 */     RenderingRegistry.registerEntityRenderingHandler(EntityFollower.class, new RenderFollower(new ModelBiped()));
/*     */     
/*     */ 
/* 332 */     RenderingRegistry.registerEntityRenderingHandler(EntityWingedMonkey.class, new RenderWingedMonkey(new ModelMonkey(), 0.5F));
/*     */     
/*     */ 
/* 335 */     RenderingRegistry.registerEntityRenderingHandler(EntityAttackBat.class, new net.minecraft.client.renderer.entity.RenderBat());
/*     */     
/* 337 */     RenderingRegistry.registerEntityRenderingHandler(EntityMirrorFace.class, new RenderMirrorFace());
/* 338 */     RenderingRegistry.registerEntityRenderingHandler(EntityReflection.class, new com.emoniph.witchery.client.renderer.RenderReflection());
/*     */     
/* 340 */     bindRenderer(com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf.class, new RenderPoppetChest(), new Item[] { Item.func_150898_a(Witchery.Blocks.POPPET_SHELF) });
/*     */     
/* 342 */     bindRenderer(BlockGrassper.TileEntityGrassper.class, new RenderGrassper(), new Item[] { Item.func_150898_a(Witchery.Blocks.GRASSPER) });
/*     */     
/* 344 */     bindRenderer(BlockDistillery.TileEntityDistillery.class, new com.emoniph.witchery.client.renderer.RenderDistillery(), new Item[] { Item.func_150898_a(Witchery.Blocks.DISTILLERY_IDLE) });
/*     */     
/* 346 */     bindRenderer(BlockWitchesOven.TileEntityWitchesOven.class, new RenderWitchesOven(), new Item[] { Item.func_150898_a(Witchery.Blocks.OVEN_IDLE) });
/*     */     
/* 348 */     bindRenderer(BlockDreamCatcher.TileEntityDreamCatcher.class, new RenderDreamCatcher(), new Item[] { Item.func_150898_a(Witchery.Blocks.DREAM_CATCHER) });
/*     */     
/* 350 */     bindRenderer(BlockChalice.TileEntityChalice.class, new RenderChalice(), new Item[] { Item.func_150898_a(Witchery.Blocks.CHALICE) });
/* 351 */     bindRenderer(BlockCandelabra.TileEntityCandelabra.class, new RenderCandelabra(), new Item[] { Item.func_150898_a(Witchery.Blocks.CANDELABRA) });
/*     */     
/* 353 */     bindRenderer(BlockCrystalBall.TileEntityCrystalBall.class, new RenderCrystalBall(), new Item[] { Item.func_150898_a(Witchery.Blocks.CRYSTAL_BALL) });
/*     */     
/* 355 */     bindRenderer(BlockKettle.TileEntityKettle.class, new RenderKettle(), new Item[] { Item.func_150898_a(Witchery.Blocks.KETTLE) });
/* 356 */     bindRenderer(BlockLeechChest.TileEntityLeechChest.class, new RenderLeechChest(), new Item[] { Item.func_150898_a(Witchery.Blocks.LEECH_CHEST) });
/*     */     
/* 358 */     bindRenderer(BlockStatueGoddess.TileEntityStatueGoddess.class, new RenderGoddess(), new Item[] { Item.func_150898_a(Witchery.Blocks.STATUE_GODDESS) });
/*     */     
/* 360 */     bindRenderer(BlockSpinningWheel.TileEntitySpinningWheel.class, new RenderSpinningWheel(), new Item[] { Item.func_150898_a(Witchery.Blocks.SPINNING_WHEEL) });
/*     */     
/* 362 */     bindRenderer(BlockBrazier.TileEntityBrazier.class, new RenderBrazier(), new Item[] { Item.func_150898_a(Witchery.Blocks.BRAZIER) });
/* 363 */     bindRenderer(BlockAreaMarker.TileEntityAreaCurseProtect.class, new RenderStatueWolf(), new Item[] { Item.func_150898_a(Witchery.Blocks.DECURSE_DIRECTED) });
/*     */     
/* 365 */     bindRenderer(BlockAreaMarker.TileEntityAreaTeleportPullProtect.class, new RenderStatueMandrake(), new Item[] { Item.func_150898_a(Witchery.Blocks.DECURSE_TELEPORT) });
/*     */     
/*     */ 
/* 368 */     bindRenderer(BlockStatueOfWorship.TileEntityStatueOfWorship.class, new RenderStatueOfWorship(), new Item[] { Item.func_150898_a(Witchery.Blocks.STATUE_OF_WORSHIP) });
/*     */     
/*     */ 
/* 371 */     bindRenderer(BlockPlacedItem.TileEntityPlacedItem.class, new RenderPlacedItem(), new Item[0]);
/* 372 */     bindRenderer(com.emoniph.witchery.blocks.BlockAlluringSkull.TileEntityAlluringSkull.class, new RenderAlluringSkull(), new Item[0]);
/* 373 */     bindRenderer(BlockDemonHeart.TileEntityDemonHeart.class, new RenderDemonHeart(), new Item[0]);
/*     */     
/* 375 */     bindRenderer(TileEntityCauldron.class, new RenderCauldron(), new Item[] { Item.func_150898_a(Witchery.Blocks.CAULDRON) });
/*     */     
/*     */ 
/* 378 */     bindRenderer(BlockStatueWerewolf.TileEntityStatueWerewolf.class, new RenderStatueWerewolf(), new Item[] { Item.func_150898_a(Witchery.Blocks.WOLF_ALTAR) });
/*     */     
/*     */ 
/* 381 */     bindRenderer(BlockSilverVat.TileEntitySilverVat.class, new RenderSilverVat(), new Item[] { Item.func_150898_a(Witchery.Blocks.SILVER_VAT) });
/*     */     
/*     */ 
/* 384 */     bindRenderer(BlockBeartrap.TileEntityBeartrap.class, new RenderBeartrap(), new Item[] { Item.func_150898_a(Witchery.Blocks.BEARTRAP), Item.func_150898_a(Witchery.Blocks.WOLFTRAP) });
/*     */     
/*     */ 
/* 387 */     bindRenderer(BlockWolfHead.TileEntityWolfHead.class, new RenderWolfHead(), new Item[0]);
/* 388 */     bindRenderer(BlockCoffin.TileEntityCoffin.class, new RenderCoffin(), new Item[0]);
/* 389 */     bindRenderer(BlockGarlicGarland.TileEntityGarlicGarland.class, new RenderGarlicGarland(), new Item[0]);
/*     */     
/* 391 */     bindRenderer(BlockBloodCrucible.TileEntityBloodCrucible.class, new RenderBloodCrucible(), new Item[] { Item.func_150898_a(Witchery.Blocks.BLOOD_CRUCIBLE), Item.func_150898_a(Witchery.Blocks.BLOOD_CRUCIBLE) });
/*     */     
/*     */ 
/*     */ 
/* 395 */     bindRenderer(BlockMirror.TileEntityMirror.class, new RenderMirror(), new Item[0]);
/*     */     
/* 397 */     RenderFumeFunnel funnelRenderer = new RenderFumeFunnel(false);
/* 398 */     bindRenderer(BlockFumeFunnel.TileEntityFumeFunnel.class, funnelRenderer, new Item[0]);
/* 399 */     BlockFumeFunnel.TileEntityFumeFunnel dummyFunnelTile = new BlockFumeFunnel.TileEntityFumeFunnel();
/* 400 */     MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(Witchery.Blocks.OVEN_FUMEFUNNEL), new RenderBlockItem(funnelRenderer, dummyFunnelTile));
/*     */     
/* 402 */     MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED), new RenderBlockItem(funnelRenderer, new BlockFumeFunnel.TileEntityFumeFunnel()));
/*     */     
/*     */ 
/* 405 */     RenderFetish fetishRenderer = new RenderFetish();
/* 406 */     bindRenderer(BlockFetish.TileEntityFetish.class, fetishRenderer, new Item[0]);
/* 407 */     BlockFetish.TileEntityFetish dummyFetishTile = new BlockFetish.TileEntityFetish();
/* 408 */     MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(Witchery.Blocks.FETISH_SCARECROW), new RenderFetish.RenderFetishBlockItem(Witchery.Blocks.FETISH_SCARECROW, fetishRenderer, dummyFetishTile));
/*     */     
/* 410 */     MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(Witchery.Blocks.FETISH_TREANT_IDOL), new RenderFetish.RenderFetishBlockItem(Witchery.Blocks.FETISH_TREANT_IDOL, fetishRenderer, dummyFetishTile));
/*     */     
/*     */ 
/* 413 */     RenderingRegistry.registerBlockHandler(STOCKADE_RENDER_ID, new RenderStockade());
/* 414 */     RenderingRegistry.registerBlockHandler(GAS_RENDER_ID, new RenderBrewGas());
/* 415 */     RenderingRegistry.registerBlockHandler(BREW_LIQUID_RENDER_ID, new RenderBrewLiquid());
/* 416 */     RenderingRegistry.registerBlockHandler(VINE_RENDER_ID, new RenderWitchVine());
/*     */     
/* 418 */     RenderingRegistry.registerBlockHandler(PITGRASS_RENDER_ID, new RenderPitGrass());
/*     */   }
/*     */   
/* 421 */   private static final int STOCKADE_RENDER_ID = ;
/* 422 */   private static final int GAS_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
/* 423 */   private static final int BREW_LIQUID_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
/* 424 */   private static final int VINE_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
/* 425 */   private static final int PITGRASS_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
/*     */   
/*     */   public int getStockageRenderId()
/*     */   {
/* 429 */     return STOCKADE_RENDER_ID;
/*     */   }
/*     */   
/*     */   public int getPitGrassRenderId()
/*     */   {
/* 434 */     return PITGRASS_RENDER_ID;
/*     */   }
/*     */   
/*     */   public int getGasRenderId()
/*     */   {
/* 439 */     return GAS_RENDER_ID;
/*     */   }
/*     */   
/*     */   public int getBrewLiquidRenderId() {
/* 443 */     return BREW_LIQUID_RENDER_ID;
/*     */   }
/*     */   
/*     */   public int getVineRenderId() {
/* 447 */     return VINE_RENDER_ID;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void bindRenderer(Class<? extends TileEntity> clazz, TileEntitySpecialRenderer render, Item... items)
/*     */   {
/* 456 */     ClientRegistry.bindTileEntitySpecialRenderer(clazz, render);
/* 457 */     for (Item item : items) {
/* 458 */       if (item != null) {
/*     */         try {
/* 460 */           MinecraftForgeClient.registerItemRenderer(item, new RenderBlockItem(render, (TileEntity)clazz.newInstance()));
/*     */         }
/*     */         catch (IllegalAccessException ex) {}catch (InstantiationException ex) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void registerHandlers()
/*     */   {
/* 471 */     super.registerHandlers();
/*     */   }
/*     */   
/*     */   public void registerEvents()
/*     */   {
/* 476 */     super.registerEvents();
/* 477 */     MinecraftForge.EVENT_BUS.register(new ClientEvents());
/* 478 */     MinecraftForge.EVENT_BUS.register(new WitcheryPotions.ClientEventHooks());
/* 479 */     MinecraftForge.EVENT_BUS.register(new ItemEarmuffs.ClientEventHooks());
/*     */   }
/*     */   
/*     */ 
/*     */   public void postInit() {}
/*     */   
/*     */ 
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/* 488 */     switch (ID) {
/*     */     case 0: 
/* 490 */       return new BlockAltarGUI((BlockAltar.TileEntityAltar)world.func_147438_o(x, y, z));
/*     */     case 1: 
/* 492 */       return new GuiScreenWitchcraftBook(player, player.func_70694_bm());
/*     */     case 2: 
/* 494 */       return new BlockWitchesOvenGUI(player.field_71071_by, (BlockWitchesOven.TileEntityWitchesOven)world.func_147438_o(x, y, z));
/*     */     case 3: 
/* 496 */       return new BlockDistilleryGUI(player.field_71071_by, (BlockDistillery.TileEntityDistillery)world.func_147438_o(x, y, z));
/*     */     case 4: 
/* 498 */       return new BlockSpinningWheelGUI(player.field_71071_by, (BlockSpinningWheel.TileEntitySpinningWheel)world.func_147438_o(x, y, z));
/*     */     
/*     */     case 5: 
/* 501 */       return new ItemBrewBagGUI(player.field_71071_by, new ItemBrewBag.InventoryBrewBag(player));
/*     */     case 6: 
/* 503 */       return new GuiScreenBiomeBook(player, player.func_70694_bm());
/*     */     case 7: 
/* 505 */       return new GuiScreenMarkupBook(player, player.func_70694_bm());
/*     */     case 8: 
/* 507 */       return new ItemLeonardsUrnGUI(player.field_71071_by, new ItemLeonardsUrn.InventoryLeonardsUrn(player));
/*     */     }
/* 509 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getGraphicsLevel()
/*     */   {
/* 515 */     return Minecraft.func_71410_x().field_71474_y.field_74347_j;
/*     */   }
/*     */   
/* 518 */   public static final ResourceLocation APOTHECARY_TEXTURE = new ResourceLocation("witchery:textures/entities/apothecary.png");
/*     */   
/*     */ 
/*     */   public void registerVillagers()
/*     */   {
/* 523 */     super.registerVillagers();
/* 524 */     if (Config.instance().generateApothecaries) {
/* 525 */       VillagerRegistry.instance().registerVillagerSkin(Config.instance().apothecaryID, APOTHECARY_TEXTURE);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void generateParticle(World worldObj, double posX, double posY, double posZ, float r, float g, float b, int ttl, float gravity)
/*     */   {
/* 532 */     if (worldObj.field_72995_K) {
/* 533 */       NaturePowerFX sparkle = new NaturePowerFX(worldObj, posX, posY, posZ);
/* 534 */       sparkle.setMaxAge(ttl);
/* 535 */       sparkle.field_70145_X = true;
/* 536 */       sparkle.func_70538_b(r, g, b);
/* 537 */       sparkle.setGravity(gravity);
/* 538 */       Minecraft.func_71410_x().field_71452_i.func_78873_a(sparkle);
/*     */     }
/*     */   }
/*     */   
/*     */   public EntityPlayer getPlayer(MessageContext ctx)
/*     */   {
/* 544 */     if (ctx.side == Side.SERVER) {
/* 545 */       return ctx.getServerHandler().field_147369_b;
/*     */     }
/* 547 */     return Minecraft.func_71410_x().field_71439_g;
/*     */   }
/*     */   
/*     */ 
/*     */   public void showParticleEffect(World world, double x, double y, double z, double width, double height, SoundEffect sound, int color, ParticleEffect particle)
/*     */   {
/* 553 */     if (sound != SoundEffect.NONE) {
/* 554 */       world.func_72980_b(x, y, z, sound.toString(), 0.5F, 0.4F / ((float)world.field_73012_v.nextDouble() * 0.4F + 0.8F), false);
/*     */     }
/*     */     
/*     */ 
/* 558 */     int effectCount = Math.min(MathHelper.func_76143_f(Math.max(width, 1.0D) * 20.0D), 300);
/* 559 */     for (int i = 0; i < effectCount; i++) {
/* 560 */       double d0 = world.field_73012_v.nextGaussian() * 0.02D;
/* 561 */       double d1 = world.field_73012_v.nextGaussian() * 0.02D;
/* 562 */       double d2 = world.field_73012_v.nextGaussian() * 0.02D;
/* 563 */       if (particle == ParticleEffect.SPELL_COLORED) {
/* 564 */         EntitySmokeFX sparkle = new EntitySmokeFX(world, x + world.field_73012_v.nextDouble() * width * 2.0D - width, y + world.field_73012_v.nextDouble() * height, z + world.field_73012_v.nextFloat() * width * 2.0D - width, 0.0D, 0.0D, 0.0D);
/*     */         
/*     */ 
/* 567 */         sparkle.field_70145_X = true;
/* 568 */         float red = (color >>> 16 & 0xFF) / 256.0F;
/* 569 */         float green = (color >>> 8 & 0xFF) / 256.0F;
/* 570 */         float blue = (color & 0xFF) / 256.0F;
/* 571 */         sparkle.func_70538_b(red, green, blue);
/* 572 */         Minecraft.func_71410_x().field_71452_i.func_78873_a(sparkle);
/*     */       } else {
/* 574 */         world.func_72869_a(particle.toString(), x + world.field_73012_v.nextDouble() * width * 2.0D - width, y + world.field_73012_v.nextDouble() * height, z + world.field_73012_v.nextFloat() * width * 2.0D - width, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/ClientProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */