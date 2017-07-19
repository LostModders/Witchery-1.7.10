/*      */ package com.emoniph.witchery.item;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryFluids;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.blocks.BlockChalice.TileEntityChalice;
/*      */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*      */ import com.emoniph.witchery.blocks.BlockCrystalBall;
/*      */ import com.emoniph.witchery.blocks.BlockDreamCatcher.TileEntityDreamCatcher;
/*      */ import com.emoniph.witchery.blocks.BlockPlacedItem;
/*      */ import com.emoniph.witchery.blocks.BlockWickerBundle;
/*      */ import com.emoniph.witchery.blocks.BlockWitchCrop;
/*      */ import com.emoniph.witchery.brewing.BrewItemKey;
/*      */ import com.emoniph.witchery.brewing.potions.PotionEnslaved;
/*      */ import com.emoniph.witchery.common.ExtendedPlayer;
/*      */ import com.emoniph.witchery.common.Shapeshift;
/*      */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*      */ import com.emoniph.witchery.entity.EntityBroom;
/*      */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*      */ import com.emoniph.witchery.entity.EntityDeathsHorse;
/*      */ import com.emoniph.witchery.entity.EntityEye;
/*      */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*      */ import com.emoniph.witchery.entity.EntitySpirit;
/*      */ import com.emoniph.witchery.entity.EntitySummonedUndead;
/*      */ import com.emoniph.witchery.entity.EntityTreefyd;
/*      */ import com.emoniph.witchery.infusion.InfusedBrew;
/*      */ import com.emoniph.witchery.infusion.InfusedBrewEffect;
/*      */ import com.emoniph.witchery.infusion.Infusion;
/*      */ import com.emoniph.witchery.infusion.PlayerEffects;
/*      */ import com.emoniph.witchery.infusion.PlayerEffects.PlayerEffect;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
/*      */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*      */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*      */ import com.emoniph.witchery.item.brew.BrewFluid;
/*      */ import com.emoniph.witchery.item.brew.BrewSolidifySpirit;
/*      */ import com.emoniph.witchery.item.brew.BrewSoul;
/*      */ import com.emoniph.witchery.network.PacketCamPos;
/*      */ import com.emoniph.witchery.network.PacketParticles;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.util.BlockProtect;
/*      */ import com.emoniph.witchery.util.BlockSide;
/*      */ import com.emoniph.witchery.util.BlockUtil;
/*      */ import com.emoniph.witchery.util.ChatUtil;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.CreatureUtil;
/*      */ import com.emoniph.witchery.util.EffectSpiral;
/*      */ import com.emoniph.witchery.util.MutableBlock;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.TargetPointUtil;
/*      */ import com.emoniph.witchery.util.TimeUtil;
/*      */ import com.emoniph.witchery.util.TransformCreature;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.Block.SoundType;
/*      */ import net.minecraft.block.BlockTallGrass;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.client.renderer.texture.IIconRegister;
/*      */ import net.minecraft.creativetab.CreativeTabs;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.EnumCreatureAttribute;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntitySpider;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.entity.player.PlayerCapabilities;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.EnumAction;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemBlock;
/*      */ import net.minecraft.item.ItemPotion;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.play.server.S14PacketEntity.S17PacketEntityLookMove;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.profiler.Profiler;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.management.ServerConfigurationManager;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.GameRules;
/*      */ import net.minecraft.world.Teleporter;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldServer;
/*      */ 
/*      */ public class ItemGeneral extends ItemBase
/*      */ {
/*  117 */   public final ArrayList<SubItem> subItems = new ArrayList();
/*  118 */   public final ArrayList<DreamWeave> weaves = new ArrayList();
/*      */   
/*  120 */   public final SubItem itemCandelabra = SubItem.register(new SubItem(0, "candelabra"), this.subItems);
/*  121 */   public final SubItem itemChaliceEmpty = SubItem.register(new SubItem(1, "chalice"), this.subItems);
/*  122 */   public final SubItem itemChaliceFull = SubItem.register(new SubItem(2, "chaliceFull"), this.subItems);
/*      */   
/*  124 */   public final DreamWeave itemDreamMove = DreamWeave.register(new DreamWeave(3, 0, "weaveMoveFast", Potion.field_76424_c, Potion.field_76421_d, 7200, 0, 17, 10, null), this.subItems, this.weaves);
/*      */   
/*  126 */   public final DreamWeave itemDreamDig = DreamWeave.register(new DreamWeave(4, 1, "weaveDigFast", Potion.field_76422_e, Potion.field_76419_f, 7200, 0, 17, 4, null), this.subItems, this.weaves);
/*      */   
/*  128 */   public final DreamWeave itemDreamEat = DreamWeave.register(new DreamWeave(5, 2, "weaveSaturation", Potion.field_76443_y, Potion.field_76438_s, 4800, 0, 17, 16, null), this.subItems, this.weaves);
/*      */   
/*  130 */   public final DreamWeave itemDreamNightmare = DreamWeave.register(new DreamWeave(6, 3, "weaveNightmares", Potion.field_76437_t, Potion.field_76440_q, 1200, 0, 4, 4, null), this.subItems, this.weaves);
/*      */   
/*      */ 
/*  133 */   public final SubItem itemBoneNeedle = SubItem.register(new SubItem(7, "boneNeedle"), this.subItems);
/*      */   
/*  135 */   public final SubItem itemBroom = SubItem.register(new SubItem(8, "broom"), this.subItems);
/*  136 */   public final SubItem itemBroomEnchanted = SubItem.register(new SubItem(9, "broomEnchanted", 3, null).setEnchanted(true), this.subItems);
/*      */   
/*  138 */   public final SubItem itemAttunedStone = SubItem.register(new SubItem(10, "attunedStone"), this.subItems);
/*  139 */   public final SubItem itemAttunedStoneCharged = SubItem.register(new SubItem(11, "attunedStoneCharged").setEnchanted(true), this.subItems);
/*      */   
/*  141 */   public final SubItem itemWaystone = SubItem.register(new SubItem(12, "waystone"), this.subItems);
/*  142 */   public final SubItem itemWaystoneBound = SubItem.register(new SubItem(13, "waystoneBound", 1, false, null), this.subItems);
/*      */   
/*  144 */   public final SubItem itemMutandis = SubItem.register(new SubItem(14, "mutandis"), this.subItems);
/*  145 */   public final SubItem itemMutandisExtremis = SubItem.register(new SubItem(15, "mutandisExtremis"), this.subItems);
/*      */   
/*  147 */   public final SubItem itemQuicklime = SubItem.register(new SubItem(16, "quicklime"), this.subItems);
/*  148 */   public final SubItem itemGypsum = SubItem.register(new SubItem(17, "gypsum"), this.subItems);
/*  149 */   public final SubItem itemAshWood = SubItem.register(new SubItem(18, "ashWood"), this.subItems);
/*      */   
/*  151 */   public final SubItem itemBelladonnaFlower = SubItem.register(new SubItem(21, "belladonna"), this.subItems);
/*  152 */   public final SubItem itemMandrakeRoot = SubItem.register(new SubItem(22, "mandrakeRoot"), this.subItems);
/*      */   
/*      */   private static final int DEMON_FOOD_DURATION = 2400;
/*  155 */   public final SubItem itemDemonHeart = SubItem.register(new Drinkable(23, "demonHeart", 2, EnumAction.eat, new PotionEffect[] { new PotionEffect(Potion.field_76434_w.field_76415_H, 2400, 4), new PotionEffect(Potion.field_76428_l.field_76415_H, 2400, 1), new PotionEffect(Potion.field_76420_g.field_76415_H, 2400, 2), new PotionEffect(Potion.field_76424_c.field_76415_H, 2400, 2), new PotionEffect(Potion.field_76426_n.field_76415_H, 2400, 2), new PotionEffect(Potion.field_76431_k.field_76415_H, 2400), new PotionEffect(Potion.field_76438_s.field_76415_H, 3600, 1) }), this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  160 */   public final SubItem itemBatWool = SubItem.register(new SubItem(24, "batWool"), this.subItems);
/*  161 */   public final SubItem itemDogTongue = SubItem.register(new SubItem(25, "dogTongue"), this.subItems);
/*      */   
/*  163 */   public final SubItem itemSoftClayJar = SubItem.register(new SubItem(26, "clayJarSoft"), this.subItems);
/*  164 */   public final SubItem itemEmptyClayJar = SubItem.register(new SubItem(27, "clayJar"), this.subItems);
/*      */   
/*  166 */   public final SubItem itemFoulFume = SubItem.register(new SubItem(28, "foulFume"), this.subItems);
/*  167 */   public final SubItem itemDiamondVapour = SubItem.register(new SubItem(29, "diamondVapour"), this.subItems);
/*      */   
/*  169 */   public final SubItem itemOilOfVitriol = SubItem.register(new SubItem(30, "oilOfVitriol"), this.subItems);
/*      */   
/*  171 */   public final SubItem itemExhaleOfTheHornedOne = SubItem.register(new SubItem(31, "exhaleOfTheHornedOne"), this.subItems);
/*  172 */   public final SubItem itemBreathOfTheGoddess = SubItem.register(new SubItem(32, "breathOfTheGoddess"), this.subItems);
/*  173 */   public final SubItem itemHintOfRebirth = SubItem.register(new SubItem(33, "hintOfRebirth"), this.subItems);
/*  174 */   public final SubItem itemWhiffOfMagic = SubItem.register(new SubItem(34, "whiffOfMagic"), this.subItems);
/*  175 */   public final SubItem itemReekOfMisfortune = SubItem.register(new SubItem(35, "reekOfMisfortune"), this.subItems);
/*  176 */   public final SubItem itemOdourOfPurity = SubItem.register(new SubItem(36, "odourOfPurity"), this.subItems);
/*  177 */   public final SubItem itemTearOfTheGoddess = SubItem.register(new SubItem(37, "tearOfTheGoddess"), this.subItems);
/*  178 */   public final SubItem itemRefinedEvil = SubItem.register(new SubItem(38, "refinedEvil"), this.subItems);
/*  179 */   public final SubItem itemDropOfLuck = SubItem.register(new SubItem(39, "dropOfLuck"), this.subItems);
/*      */   
/*  181 */   public final SubItem itemRedstoneSoup = SubItem.register(new Drinkable(40, "redstoneSoup", 1, new PotionEffect[] { new PotionEffect(Potion.field_76434_w.field_76415_H, 2400, 1) }), this.subItems);
/*      */   
/*      */ 
/*  184 */   public final SubItem itemFlyingOintment = SubItem.register(new Drinkable(41, "flyingOintment", 2, new PotionEffect[] { new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 2) }), this.subItems);
/*      */   
/*  186 */   public final SubItem itemGhostOfTheLight = SubItem.register(new Drinkable(42, "ghostOfTheLight", 2, new PotionEffect[] { new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1) }), this.subItems);
/*      */   
/*  188 */   public final SubItem itemSoulOfTheWorld = SubItem.register(new Drinkable(43, "soulOfTheWorld", 2, new PotionEffect[] { new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1) }), this.subItems);
/*      */   
/*  190 */   public final SubItem itemSpiritOfOtherwhere = SubItem.register(new Drinkable(44, "spiritOfOtherwhere", 2, new PotionEffect[] { new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1) }), this.subItems);
/*      */   
/*  192 */   public final SubItem itemInfernalAnimus = SubItem.register(new Drinkable(45, "infernalAnimus", 2, new PotionEffect[] { new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1), new PotionEffect(Potion.field_82731_v.field_76415_H, 3600, 2) }), this.subItems);
/*      */   
/*      */ 
/*  195 */   public final SubItem itemBookOven = SubItem.register(new SubItem(46, "bookOven"), this.subItems);
/*  196 */   public final SubItem itemBookDistilling = SubItem.register(new SubItem(47, "bookDistilling"), this.subItems);
/*  197 */   public final SubItem itemBookCircleMagic = SubItem.register(new SubItem(48, "bookCircleMagic"), this.subItems);
/*  198 */   public final SubItem itemBookInfusions = SubItem.register(new SubItem(49, "bookInfusions"), this.subItems);
/*      */   
/*  200 */   public final SubItem itemOddPorkRaw = SubItem.register(new Edible(50, "oddPorkchopRaw", 3, 0.3F, true, null), this.subItems);
/*  201 */   public final SubItem itemOddPorkCooked = SubItem.register(new Edible(51, "oddPorkchopCooked", 8, 0.8F, true, null), this.subItems);
/*      */   
/*  203 */   public final SubItem itemDoorRowan = SubItem.register(new SubItem(52, "doorRowan"), this.subItems);
/*  204 */   public final SubItem itemDoorAlder = SubItem.register(new SubItem(53, "doorAlder"), this.subItems);
/*      */   
/*  206 */   public final SubItem itemDoorKey = SubItem.register(new SubItem(54, "doorKey"), this.subItems);
/*      */   
/*  208 */   public final SubItem itemRock = SubItem.register(new SubItem(55, "rock"), this.subItems);
/*  209 */   public final SubItem itemWeb = SubItem.register(new SubItem(56, "web"), this.subItems);
/*      */   
/*  211 */   public final SubItem itemBrewOfVines = SubItem.register(new Brew(57, "brewVines"), this.subItems);
/*  212 */   public final SubItem itemBrewOfWebs = SubItem.register(new Brew(58, "brewWeb"), this.subItems);
/*  213 */   public final SubItem itemBrewOfThorns = SubItem.register(new Brew(59, "brewThorns"), this.subItems);
/*  214 */   public final SubItem itemBrewOfInk = SubItem.register(new Brew(60, "brewInk"), this.subItems);
/*  215 */   public final SubItem itemBrewOfSprouting = SubItem.register(new Brew(61, "brewSprouting"), this.subItems);
/*  216 */   public final SubItem itemBrewOfErosion = SubItem.register(new Brew(62, "brewErosion"), this.subItems);
/*      */   
/*  218 */   public final SubItem itemRowanBerries = SubItem.register(new Edible(63, "berriesRowan", 1, 6.0F, false, null), this.subItems);
/*      */   
/*  220 */   public final SubItem itemNecroStone = SubItem.register(new SubItem(64, "necroStone", 1, null).setEnchanted(true), this.subItems);
/*  221 */   public final SubItem itemBrewOfRaising = SubItem.register(new Brew(65, "brewRaising"), this.subItems);
/*      */   
/*  223 */   public final SubItem itemSpectralDust = SubItem.register(new SubItem(66, "spectralDust"), this.subItems);
/*  224 */   public final SubItem itemEnderDew = SubItem.register(new SubItem(67, "enderDew"), this.subItems);
/*      */   
/*  226 */   public final SubItem itemArtichoke = SubItem.register(new Edible(69, "artichoke", 20, 0.0F, false, null), this.subItems);
/*      */   
/*  228 */   public final SubItem itemSeedsTreefyd = SubItem.register(new SubItem(70, "seedsTreefyd"), this.subItems);
/*      */   
/*  230 */   public final SubItem itemBrewGrotesque = SubItem.register(new Drinkable(71, "brewGrotesque", 1, new PotionEffect[0]).setPotion(true), this.subItems);
/*  231 */   public final SubItem itemImpregnatedLeather = SubItem.register(new SubItem(72, "impregnatedLeather"), this.subItems);
/*  232 */   public final SubItem itemFumeFilter = SubItem.register(new SubItem(73, "fumeFilter"), this.subItems);
/*      */   
/*  234 */   public final SubItem itemCreeperHeart = SubItem.register(new Drinkable(74, "creeperHeart", 1, EnumAction.eat, new PotionEffect[] { new PotionEffect(Potion.field_76426_n.field_76415_H, 20, 0) }), this.subItems);
/*      */   
/*  236 */   public final SubItem itemBrewOfLove = SubItem.register(new Brew(75, "brewLove"), this.subItems);
/*  237 */   public final SubItem itemBrewOfIce = SubItem.register(new Brew(76, "brewIce"), this.subItems);
/*  238 */   public final SubItem itemBrewOfTheDepths = SubItem.register(new Drinkable(77, "brewDepths", 1, new PotionEffect[0]).setPotion(true), this.subItems);
/*  239 */   public final SubItem itemIcyNeedle = SubItem.register(new SubItem(78, "icyNeedle"), this.subItems);
/*  240 */   public final SubItem itemFrozenHeart = SubItem.register(new Drinkable(79, "frozenHeart", 1, EnumAction.eat, new PotionEffect[] { new PotionEffect(Potion.field_76426_n.field_76415_H, 20, 0) }), this.subItems);
/*      */   
/*  242 */   public final SubItem itemInfernalBlood = SubItem.register(new SubItem(80, "infernalBlood"), this.subItems);
/*      */   
/*  244 */   public final SubItem itemBookHerbology = SubItem.register(new SubItem(81, "bookHerbology"), this.subItems);
/*      */   
/*  246 */   public final SubItem itemBranchEnt = SubItem.register(new SubItem(82, "entbranch"), this.subItems);
/*  247 */   public final SubItem itemMysticUnguent = SubItem.register(new Drinkable(83, "mysticunguent", 2, new PotionEffect[] { new PotionEffect(Potion.field_76437_t.field_76415_H, 1200, 1) }), this.subItems);
/*      */   
/*      */ 
/*  250 */   public final SubItem itemDoorKeyring = SubItem.register(new SubItem(84, "doorKeyring"), this.subItems);
/*      */   
/*  252 */   public final SubItem itemBrewOfFrogsTongue = SubItem.register(new Brew(85, "brewFrogsTongue"), this.subItems);
/*  253 */   public final SubItem itemBrewOfCursedLeaping = SubItem.register(new Brew(86, "brewCursedLeaping"), this.subItems);
/*  254 */   public final SubItem itemBrewOfHitchcock = SubItem.register(new Brew(87, "brewHitchcock"), this.subItems);
/*  255 */   public final SubItem itemBrewOfInfection = SubItem.register(new Brew(88, "brewInfection"), this.subItems);
/*  256 */   public final SubItem itemOwletsWing = SubItem.register(new SubItem(89, "owletsWing"), this.subItems);
/*  257 */   public final SubItem itemToeOfFrog = SubItem.register(new SubItem(90, "toeOfFrog"), this.subItems);
/*  258 */   public final SubItem itemWormyApple = SubItem.register(new Drinkable(91, "appleWormy", 0, EnumAction.eat, new PotionEffect[] { new PotionEffect(Potion.field_76436_u.field_76415_H, 60) }), this.subItems);
/*  259 */   public final SubItem itemQuartzSphere = SubItem.register(new SubItem(92, "quartzSphere"), this.subItems);
/*  260 */   public final SubItem itemHappenstanceOil = SubItem.register(new Drinkable(93, "happenstanceOil", 1, new PotionEffect[] { new PotionEffect(Potion.field_76439_r.field_76415_H, 1200) }), this.subItems);
/*  261 */   public final SubItem itemSeerStone = SubItem.register(new SubItem(94, "seerStone", 1, null).setEnchanted(true), this.subItems);
/*  262 */   public final SubItem itemBrewOfSleeping = SubItem.register(new Drinkable(95, "brewSleep", 1, new PotionEffect[0]).setPotion(true), this.subItems);
/*  263 */   public final SubItem itemBrewOfFlowingSpirit = SubItem.register(new BrewFluid(96, "brewFlowingSpirit", Witchery.Fluids.FLOWING_SPIRIT), this.subItems);
/*  264 */   public final SubItem itemBrewOfWasting = SubItem.register(new Brew(97, "brewWasting"), this.subItems);
/*  265 */   public final SubItem itemSleepingApple = SubItem.register(new Edible(98, "sleepingApple", 3, 3.0F, false, true, null), this.subItems);
/*  266 */   public final SubItem itemDisturbedCotton = SubItem.register(new SubItem(99, "disturbedCotton"), this.subItems);
/*  267 */   public final SubItem itemFancifulThread = SubItem.register(new SubItem(100, "fancifulThread"), this.subItems);
/*  268 */   public final SubItem itemTormentedTwine = SubItem.register(new SubItem(101, "tormentedTwine"), this.subItems);
/*  269 */   public final SubItem itemGoldenThread = SubItem.register(new SubItem(102, "goldenThread"), this.subItems);
/*  270 */   public final SubItem itemMellifluousHunger = SubItem.register(new SubItem(103, "mellifluousHunger"), this.subItems);
/*      */   
/*  272 */   public final DreamWeave itemDreamIntensity = DreamWeave.register(new DreamWeave(104, 4, "weaveIntensity", Potion.field_76439_r, Potion.field_76440_q, 300, 0, 17, 22, null), this.subItems, this.weaves);
/*      */   
/*      */ 
/*  275 */   public final SubItem itemPurifiedMilk = SubItem.register(new Drinkable(105, "purifiedMilk", 1, new PotionEffect[0]), this.subItems);
/*  276 */   public final SubItem itemBookBiomes = SubItem.register(new SubItem(106, "bookBiomes"), this.subItems);
/*  277 */   public final SubItem itemBookWands = SubItem.register(new SubItem(107, "bookWands"), this.subItems);
/*  278 */   public final SubItem itemBatBall = SubItem.register(new SubItem(108, "batBall"), this.subItems);
/*  279 */   public final SubItem itemBrewOfBats = SubItem.register(new Brew(109, "brewBats"), this.subItems);
/*  280 */   public final SubItem itemCharmOfDisruptedDreams = SubItem.register(new SubItem(110, "charmDisruptedDreams"), this.subItems);
/*  281 */   public final SubItem itemWormwood = SubItem.register(new SubItem(111, "wormwood"), this.subItems);
/*  282 */   public final SubItem itemSubduedSpirit = SubItem.register(new SubItem(112, "subduedSpirit"), this.subItems);
/*  283 */   public final SubItem itemFocusedWill = SubItem.register(new SubItem(113, "focusedWill"), this.subItems);
/*  284 */   public final SubItem itemCondensedFear = SubItem.register(new SubItem(114, "condensedFear"), this.subItems);
/*  285 */   public final SubItem itemBrewOfHollowTears = SubItem.register(new BrewFluid(115, "brewHollowTears", Witchery.Fluids.HOLLOW_TEARS), this.subItems);
/*  286 */   public final SubItem itemBrewOfSolidRock = SubItem.register(new BrewSolidifySpirit(116, "brewSolidStone", Blocks.field_150348_b), this.subItems);
/*  287 */   public final SubItem itemBrewOfSolidDirt = SubItem.register(new BrewSolidifySpirit(117, "brewSolidDirt", Blocks.field_150346_d), this.subItems);
/*  288 */   public final SubItem itemBrewOfSolidSand = SubItem.register(new BrewSolidifySpirit(118, "brewSolidSand", Blocks.field_150354_m), this.subItems);
/*  289 */   public final SubItem itemBrewOfSolidSandstone = SubItem.register(new BrewSolidifySpirit(119, "brewSolidSandstone", Blocks.field_150322_A), this.subItems);
/*  290 */   public final SubItem itemBrewOfSolidErosion = SubItem.register(new BrewSolidifySpirit(120, "brewSolidErosion", null), this.subItems);
/*      */   
/*  292 */   public final SubItem itemInfusionBase = SubItem.register(new Drinkable(121, "infusionBase", 2, new PotionEffect[] { new PotionEffect(Potion.field_82731_v.field_76415_H, 200, 3) }), this.subItems);
/*  293 */   public final SubItem itemBrewOfSoaring = SubItem.register(new InfusedBrew(122, "brewSoaring", InfusedBrewEffect.Soaring), this.subItems);
/*  294 */   public final SubItem itemBrewGrave = SubItem.register(new InfusedBrew(123, "brewGrave", InfusedBrewEffect.Grave), this.subItems);
/*  295 */   public final SubItem itemBrewRevealing = SubItem.register(new Brew(124, "brewRevealing") {
/*      */     public ItemGeneral.Brew.BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
/*  297 */       double RADIUS = enhanced ? 8.0D : 5.0D;
/*  298 */       double RADIUS_SQ = RADIUS * RADIUS;
/*  299 */       AxisAlignedBB areaOfEffect = brewBounds.func_72314_b(RADIUS, RADIUS, RADIUS);
/*  300 */       List entities = world.func_72872_a(EntityLivingBase.class, areaOfEffect);
/*  301 */       if ((entities != null) && (!entities.isEmpty())) {
/*  302 */         Iterator entityIterator = entities.iterator();
/*  303 */         while (entityIterator.hasNext()) {
/*  304 */           EntityLivingBase entityLiving = (EntityLivingBase)entityIterator.next();
/*  305 */           double distanceSq = entityLiving.func_70092_e(brewX, brewY, brewZ);
/*  306 */           if (distanceSq <= RADIUS_SQ) {
/*  307 */             double scalingFactor = 1.0D - Math.sqrt(distanceSq) / RADIUS;
/*  308 */             if (entityLiving == mop.field_72308_g) {
/*  309 */               scalingFactor = 1.0D;
/*      */             }
/*      */             
/*  312 */             if (entityLiving.func_70644_a(Potion.field_76441_p)) {
/*  313 */               entityLiving.func_82170_o(Potion.field_76441_p.field_76415_H);
/*      */             }
/*      */             
/*  316 */             if (((entityLiving instanceof EntityPlayerMP)) && (entityLiving.func_82150_aj())) {
/*  317 */               entityLiving.func_82142_c(false);
/*      */             }
/*      */             
/*  320 */             if ((entityLiving instanceof EntityPlayer)) {
/*  321 */               EntityPlayer player = (EntityPlayer)entityLiving;
/*  322 */               ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  323 */               if ((playerEx != null) && (playerEx.getCreatureType() == TransformCreature.PLAYER)) {
/*  324 */                 ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
/*  325 */                 Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
/*      */               }
/*      */             }
/*      */             
/*  329 */             if (((entityLiving instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)entityLiving).isObscured())) {
/*  330 */               ((EntitySummonedUndead)entityLiving).setObscured(false);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  335 */       return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
/*      */     }
/*  295 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  338 */   public final SubItem itemBrewSubstitution = SubItem.register(new Brew(125, "brewSubstitution") {
/*      */     public ItemGeneral.Brew.BrewResult onImpact(World world, EntityLivingBase thrower, final MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
/*  340 */       if ((mop == null) || (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY)) {
/*  341 */         return ItemGeneral.Brew.BrewResult.DROP_ITEM;
/*      */       }
/*      */       
/*  344 */       int RADIUS = enhanced ? 6 : 4;
/*  345 */       final double RADIUS_SQ = RADIUS * RADIUS;
/*  346 */       AxisAlignedBB areaOfEffect = brewBounds.func_72314_b(RADIUS, RADIUS, RADIUS);
/*  347 */       List entities = world.func_72872_a(EntityItem.class, areaOfEffect);
/*  348 */       if ((entities != null) && (!entities.isEmpty())) {
/*  349 */         final ArrayList<EntityItem> items = new ArrayList();
/*  350 */         Iterator entityIterator = entities.iterator();
/*  351 */         while (entityIterator.hasNext()) {
/*  352 */           EntityItem item = (EntityItem)entityIterator.next();
/*  353 */           double distanceSq = item.func_70092_e(brewX, brewY, brewZ);
/*  354 */           if (distanceSq <= RADIUS_SQ) {
/*  355 */             ItemStack stack = item.func_92059_d();
/*  356 */             if ((stack.func_77973_b() instanceof ItemBlock)) {
/*  357 */               items.add(item);
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  362 */         Block refBlock = BlockUtil.getBlock(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  363 */         final int refMeta = BlockUtil.getBlockMetadata(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*      */         
/*  365 */         if ((items.size() > 0) && (refBlock != null) && (BlockProtect.canBreak(refBlock, world))) {
/*  366 */           new EffectSpiral(new com.emoniph.witchery.util.ISpiralBlockAction() {
/*  367 */             int stackIndex = 0;
/*  368 */             int subCount = 0;
/*      */             
/*      */ 
/*      */             public void onSpiralActionStart(World world, int posX, int posY, int posZ) {}
/*      */             
/*      */ 
/*      */             public boolean onSpiralBlockAction(World world, int posX, int posY, int posZ)
/*      */             {
/*  376 */               if (Coord.distanceSq(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, posX, posY, posZ) < RADIUS_SQ) {
/*  377 */                 boolean found = false;
/*  378 */                 if ((BlockUtil.getBlock(world, posX, posY, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY + 1, posZ))) {
/*  379 */                   found = true;
/*  380 */                 } else if ((BlockUtil.getBlock(world, posX, posY + 1, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY + 2, posZ))) {
/*  381 */                   posY++;
/*  382 */                   found = true;
/*  383 */                 } else if ((BlockUtil.getBlock(world, posX, posY - 1, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY, posZ))) {
/*  384 */                   posY--;
/*  385 */                   found = true;
/*  386 */                 } else if ((BlockUtil.getBlock(world, posX, posY + 2, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY + 3, posZ))) {
/*  387 */                   posY += 2;
/*  388 */                   found = true;
/*  389 */                 } else if ((BlockUtil.getBlock(world, posX, posY - 2, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY - 1, posZ))) {
/*  390 */                   posY -= 2;
/*  391 */                   found = true;
/*      */                 }
/*      */                 
/*  394 */                 if (found) {
/*  395 */                   this.subCount += 1;
/*  396 */                   ItemStack stack = ((EntityItem)refMeta.get(this.stackIndex)).func_92059_d();
/*  397 */                   BlockUtil.setBlock(world, posX, posY, posZ, (ItemBlock)stack.func_77973_b(), stack.func_77960_j(), 3);
/*  398 */                   ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, posX, posY, posZ, 1.0D, 1.0D, 16);
/*      */                   
/*  400 */                   if (--stack.field_77994_a == 0) {
/*  401 */                     ((EntityItem)refMeta.get(this.stackIndex)).func_70106_y();
/*  402 */                     this.stackIndex += 1;
/*      */                   }
/*      */                 }
/*      */               }
/*  406 */               return this.stackIndex < refMeta.size();
/*      */             }
/*      */             
/*      */             public void onSpiralActionStop(World world, int posX, int posY, int posZ)
/*      */             {
/*  411 */               while (this.subCount > 0) {
/*  412 */                 int quantity = this.subCount > 64 ? 64 : this.subCount;
/*  413 */                 this.subCount -= quantity;
/*  414 */                 world.func_72838_d(new EntityItem(world, 0.5D + posX, 1.5D + posY, 0.5D + posZ, new ItemStack(items, quantity, this.val$refMeta))); } } }).apply(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, RADIUS * 2, RADIUS * 2);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  419 */           return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
/*      */         }
/*      */       }
/*  422 */       return ItemGeneral.Brew.BrewResult.DROP_ITEM;
/*      */     }
/*  338 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  427 */   public final SubItem itemCongealedSpirit = SubItem.register(new Drinkable(126, "brewCongealedSpirit", 1, new PotionEffect[] { new PotionEffect(Potion.field_76439_r.field_76415_H, TimeUtil.secsToTicks(30), 1) }).setPotion(true), this.subItems);
/*  428 */   public final SubItem itemBookBurning = SubItem.register(new SubItem(127, "bookBurning"), this.subItems);
/*  429 */   public final SubItem itemGraveyardDust = SubItem.register(new SubItem(128, "graveyardDust"), this.subItems);
/*  430 */   public final SubItem itemBinkyHead = SubItem.register(new BoltType(129, "binkyhead", null), this.subItems);
/*  431 */   public final SubItem itemNullCatalyst = SubItem.register(new BoltType(130, "nullcatalyst", null), this.subItems);
/*  432 */   public final SubItem itemNullifiedLeather = SubItem.register(new BoltType(131, "nullifiedleather", null), this.subItems);
/*  433 */   public final SubItem itemBoltStake = SubItem.register(new BoltType(132, "boltStake", null), this.subItems);
/*  434 */   public final SubItem itemBoltAntiMagic = SubItem.register(new BoltType(133, "boltAntiMagic", null), this.subItems);
/*  435 */   public final SubItem itemBoltHoly = SubItem.register(new BoltType(134, "boltHoly", null), this.subItems);
/*  436 */   public final SubItem itemBoltSplitting = SubItem.register(new BoltType(135, "boltSplitting", null), this.subItems);
/*  437 */   public final SubItem itemBrewSoulHunger = SubItem.register(new BrewSoul(136, "brewSoulHunger", EffectRegistry.CarnosaDiem), this.subItems);
/*  438 */   public final SubItem itemBrewSoulAnguish = SubItem.register(new BrewSoul(137, "brewSoulAnguish", EffectRegistry.Ignianima), this.subItems);
/*  439 */   public final SubItem itemBrewSoulFear = SubItem.register(new BrewSoul(138, "brewSoulFear", EffectRegistry.MORSMORDRE), this.subItems);
/*  440 */   public final SubItem itemBrewSoulTorment = SubItem.register(new BrewSoul(139, "brewSoulTorment", EffectRegistry.Tormentum), this.subItems);
/*  441 */   public final SubItem itemContractOwnership = SubItem.register(new SubItem(140, "contract"), this.subItems);
/*  442 */   public final SubItem itemContractTorment = SubItem.register(new SubItem(141, "contractTorment"), this.subItems);
/*  443 */   public final SubItem itemContractBlaze = SubItem.register(new ItemGeneralContract(142, "contractBlaze") {
/*      */     public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
/*  445 */       EntityCreature blaze = InfusionInfernal.spawnCreature(targetEntity.field_70170_p, net.minecraft.entity.monster.EntityBlaze.class, targetEntity, 1, 2, ParticleEffect.FLAME, SoundEffect.RANDOM_FIZZ);
/*  446 */       if (blaze != null) {
/*  447 */         blaze.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*  448 */         blaze.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*  449 */         return true;
/*      */       }
/*  451 */       return false;
/*      */     }
/*  443 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  455 */   public final SubItem itemContractResistFire = SubItem.register(new ItemGeneralContract(143, "contractResistFire") {
/*      */     public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
/*  457 */       targetEntity.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, TimeUtil.minsToTicks(15)));
/*  458 */       return true;
/*      */     }
/*  455 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  461 */   public final SubItem itemContractEvaporate = SubItem.register(new ItemGeneralContract(144, "contractEvaporate") {
/*      */     public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
/*  463 */       if ((targetEntity instanceof EntityPlayer)) {
/*  464 */         PlayerEffects.IMP_EVAPORATION.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
/*  465 */         return true;
/*      */       }
/*  467 */       return false;
/*      */     }
/*  461 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  471 */   public final SubItem itemContractFieryTouch = SubItem.register(new ItemGeneralContract(145, "contractFieryTouch") {
/*      */     public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
/*  473 */       if ((targetEntity instanceof EntityPlayer)) {
/*  474 */         PlayerEffects.IMP_FIRE_TOUCH.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
/*  475 */         return true;
/*      */       }
/*  477 */       return false;
/*      */     }
/*  471 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  481 */   public final SubItem itemContractSmelting = SubItem.register(new ItemGeneralContract(146, "contractSmelting") {
/*      */     public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
/*  483 */       if ((targetEntity instanceof EntityPlayer)) {
/*  484 */         PlayerEffects.IMP_METLING_TOUCH.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
/*  485 */         return true;
/*      */       }
/*  487 */       return false;
/*      */     }
/*  481 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  491 */   public final SubItem itemWaystonePlayerBound = SubItem.register(new SubItem(147, "waystoneCreatureBound", 1, false, null), this.subItems);
/*  492 */   public final SubItem itemKobolditeDust = SubItem.register(new SubItem(148, "kobolditedust"), this.subItems);
/*  493 */   public final SubItem itemKobolditeNugget = SubItem.register(new SubItem(149, "kobolditenugget"), this.subItems);
/*  494 */   public final SubItem itemKobolditeIngot = SubItem.register(new SubItem(150, "kobolditeingot"), this.subItems);
/*  495 */   public final SubItem itemKobolditePentacle = SubItem.register(new SubItem(151, "pentacle"), this.subItems);
/*  496 */   public final SubItem itemDoorIce = SubItem.register(new SubItem(152, "doorIce"), this.subItems);
/*  497 */   public final SubItem itemAnnointingPaste = SubItem.register(new SubItem(153, "annointingPaste"), this.subItems);
/*  498 */   public final SubItem itemSubduedSpiritVillage = SubItem.register(new SubItem(154, "subduedSpiritVillage"), this.subItems);
/*  499 */   public final SubItem itemBoltSilver = SubItem.register(new BoltType(155, "boltSilver", null), this.subItems);
/*  500 */   public final SubItem itemWolfsbane = SubItem.register(new SubItem(156, "wolfsbane"), this.subItems);
/*  501 */   public final SubItem itemSilverDust = SubItem.register(new SubItem(157, "silverdust"), this.subItems);
/*  502 */   public final SubItem itemMuttonRaw = SubItem.register(new Edible(158, "muttonraw", 2, 0.2F, true, null), this.subItems);
/*  503 */   public final SubItem itemMuttonCooked = SubItem.register(new Edible(159, "muttoncooked", 6, 0.8F, true, null), this.subItems);
/*  504 */   public final SubItem itemVampireBookPage = SubItem.register(new SubItem(160, "vbookPage"), this.subItems);
/*  505 */   public final SubItem itemDarkCloth = SubItem.register(new SubItem(161, "darkCloth"), this.subItems);
/*  506 */   public final SubItem itemWoodenStake = SubItem.register(new SubItem(162, "stake"), this.subItems);
/*  507 */   public final SubItem itemBloodWarm = SubItem.register(new Drinkable(163, "warmBlood", 1, new PotionEffect[0]) {
/*      */     public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {
/*  509 */       if (!world.field_72995_K) {
/*  510 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  511 */         if (playerEx.isVampire()) {
/*  512 */           playerEx.increaseBloodPower(500);
/*      */         } else {
/*  514 */           player.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, TimeUtil.secsToTicks(6)));
/*      */         }
/*      */       }
/*      */     }
/*  507 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  519 */   public final SubItem itemBloodLiliths = SubItem.register(new Drinkable(164, "lilithsBlood", 2, new PotionEffect[0]) {
/*      */     public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {
/*  521 */       if (!world.field_72995_K) {
/*  522 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  523 */         int level = playerEx.getVampireLevel();
/*  524 */         if (level == 10) {
/*  525 */           playerEx.increaseBloodPower(2000);
/*      */         } else {
/*  527 */           playerEx.increaseVampireLevel();
/*      */         }
/*      */       }
/*      */     }
/*  519 */   }, this.subItems);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  532 */   public final SubItem itemHeartOfGold = SubItem.register(new SubItem(165, "heartofgold"), this.subItems);
/*      */   @SideOnly(Side.CLIENT)
/*      */   private IIcon overlayGenericIcon;
/*      */   
/*  536 */   public ItemGeneral() { func_77656_e(0);
/*  537 */     func_77625_d(64);
/*  538 */     func_77627_a(true);
/*      */   }
/*      */   
/*      */   public boolean isBrew(int itemDamage) {
/*  542 */     return this.subItems.get(itemDamage) instanceof Brew;
/*      */   }
/*      */   
/*      */   public boolean isBrew(ItemStack stack) {
/*  546 */     return (stack != null) && (stack.func_77973_b() == this) && (isBrew(stack.func_77960_j()));
/*      */   }
/*      */   
/*      */   public String func_77667_c(ItemStack itemStack)
/*      */   {
/*  551 */     return func_77658_a() + "." + ((SubItem)this.subItems.get(itemStack.func_77960_j())).unlocalizedName;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   @SideOnly(Side.CLIENT)
/*      */   private IIcon overlayBroomIcon;
/*      */   
/*      */ 
/*      */   @SideOnly(Side.CLIENT)
/*      */   private IIcon overlaySolidifierIcon;
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   private IIcon overlayInfusedBrewIcon;
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public void func_94581_a(IIconRegister iconRegister)
/*      */   {
/*  569 */     String defaultIconName = func_111208_A();
/*  570 */     for (SubItem subItem : this.subItems) {
/*  571 */       if (subItem != null) {
/*  572 */         subItem.registerIcon(iconRegister, this);
/*      */       }
/*      */     }
/*  575 */     this.overlayGenericIcon = iconRegister.func_94245_a(defaultIconName + ".genericoverlay");
/*  576 */     this.overlayBroomIcon = iconRegister.func_94245_a(defaultIconName + ".broomOverlay");
/*  577 */     this.overlaySolidifierIcon = iconRegister.func_94245_a(defaultIconName + ".brewSolidOverlay");
/*  578 */     this.overlayInfusedBrewIcon = iconRegister.func_94245_a(defaultIconName + ".brewInfusedOverlay");
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public IIcon func_77617_a(int damage)
/*      */   {
/*  584 */     return ((SubItem)this.subItems.get(Math.max(damage, 0))).icon;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public IIcon func_77618_c(int damage, int pass)
/*      */   {
/*  590 */     if (pass == 0)
/*  591 */       return super.func_77618_c(damage, pass);
/*  592 */     if (((SubItem)this.subItems.get(damage)).isSolidifier())
/*  593 */       return this.overlaySolidifierIcon;
/*  594 */     if (((SubItem)this.subItems.get(damage)).isInfused())
/*  595 */       return this.overlayInfusedBrewIcon;
/*  596 */     if (((SubItem)this.subItems.get(damage)).isPotion())
/*  597 */       return Items.field_151068_bn.func_77618_c((this.subItems.get(damage) instanceof Brew) ? 16384 : 0, pass);
/*  598 */     if (this.itemBroomEnchanted.damageValue == damage) {
/*  599 */       return this.overlayBroomIcon;
/*      */     }
/*  601 */     return this.overlayGenericIcon;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public int func_82790_a(ItemStack stack, int pass)
/*      */   {
/*  607 */     if ((!this.itemBroomEnchanted.isMatch(stack)) || (pass == 0)) {
/*  608 */       return super.func_82790_a(stack, pass);
/*      */     }
/*      */     
/*  611 */     int j = getBroomItemColor(stack);
/*      */     
/*  613 */     if (j > 15)
/*  614 */       return super.func_82790_a(stack, pass);
/*  615 */     if (j < 0) {
/*  616 */       j = 12;
/*      */     }
/*      */     
/*  619 */     return net.minecraft.item.ItemDye.field_150922_c[net.minecraft.block.BlockColored.func_150031_c(j)];
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public boolean func_77623_v()
/*      */   {
/*  625 */     return true;
/*      */   }
/*      */   
/*      */   public boolean hasEffect(ItemStack stack, int pass)
/*      */   {
/*  630 */     return ((pass == 0) && (((SubItem)this.subItems.get(stack.func_77960_j())).isEnchanted())) || (this.itemBroomEnchanted.isMatch(stack)) || (this.itemSubduedSpirit.isMatch(stack)) || (this.itemSubduedSpiritVillage.isMatch(stack));
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public void func_150895_a(Item item, CreativeTabs tab, List itemList)
/*      */   {
/*  636 */     for (SubItem subItem : this.subItems) {
/*  637 */       if ((subItem != null) && (subItem.showInCreativeTab)) {
/*  638 */         itemList.add(subItem.createStack());
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*      */   {
/*  646 */     return net.minecraft.item.EnumRarity.values()[((SubItem)this.subItems.get(itemstack.func_77960_j())).rarity];
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean includeHandlers)
/*      */   {
/*  652 */     String location = getBoundDisplayName(stack);
/*  653 */     if ((location != null) && (!location.isEmpty())) {
/*  654 */       list.add(location);
/*      */     }
/*      */     
/*  657 */     String taglock = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
/*  658 */     if (!taglock.isEmpty()) {
/*  659 */       list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { taglock }));
/*      */     }
/*      */     
/*  662 */     if (this.itemDoorKey.isMatch(stack)) {
/*  663 */       NBTTagCompound nbtTag = stack.func_77978_p();
/*  664 */       if ((nbtTag != null) && (nbtTag.func_74764_b("doorX")) && (nbtTag.func_74764_b("doorY")) && (nbtTag.func_74764_b("doorZ"))) {
/*  665 */         int doorX = nbtTag.func_74762_e("doorX");
/*  666 */         int doorY = nbtTag.func_74762_e("doorY");
/*  667 */         int doorZ = nbtTag.func_74762_e("doorZ");
/*  668 */         if ((nbtTag.func_74764_b("doorD")) && (nbtTag.func_74764_b("doorDN"))) {
/*  669 */           list.add(String.format("%s: %d, %d, %d", new Object[] { nbtTag.func_74779_i("doorDN"), Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
/*      */         } else
/*  671 */           list.add(String.format("%d, %d, %d", new Object[] { Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
/*      */       }
/*  673 */     } else if (this.itemDoorKeyring.isMatch(stack)) {
/*  674 */       NBTTagCompound nbtTag = stack.func_77978_p();
/*  675 */       if ((nbtTag != null) && (nbtTag.func_74764_b("doorKeys"))) {
/*  676 */         NBTTagList keyList = nbtTag.func_150295_c("doorKeys", 10);
/*  677 */         if (keyList != null) {
/*  678 */           for (int i = 0; i < keyList.func_74745_c(); i++) {
/*  679 */             NBTTagCompound keyTag = keyList.func_150305_b(i);
/*  680 */             if ((keyTag != null) && (keyTag.func_74764_b("doorX")) && (keyTag.func_74764_b("doorY")) && (keyTag.func_74764_b("doorZ"))) {
/*  681 */               int doorX = keyTag.func_74762_e("doorX");
/*  682 */               int doorY = keyTag.func_74762_e("doorY");
/*  683 */               int doorZ = keyTag.func_74762_e("doorZ");
/*  684 */               if ((keyTag.func_74764_b("doorD")) && (keyTag.func_74764_b("doorDN"))) {
/*  685 */                 list.add(String.format("%s: %d, %d, %d", new Object[] { keyTag.func_74779_i("doorDN"), Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
/*      */               } else {
/*  687 */                 list.add(String.format("%d, %d, %d", new Object[] { Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  695 */     if (stack.func_77960_j() == this.itemContractTorment.damageValue) {
/*  696 */       String localText = Witchery.resource("item.witchery:ingredient.contractTorment.tip");
/*  697 */       if (localText != null) {
/*  698 */         for (String s : localText.split("\n")) {
/*  699 */           if (!s.isEmpty()) {
/*  700 */             list.add(s);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public String getBoundDisplayName(ItemStack itemstack) {
/*  708 */     NBTTagCompound tag = itemstack.func_77978_p();
/*  709 */     if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("NameD"))) {
/*  710 */       return String.format("%s: %d, %d, %d", new Object[] { tag.func_74779_i("NameD"), Integer.valueOf(tag.func_74762_e("PosX")), Integer.valueOf(tag.func_74762_e("PosY")), Integer.valueOf(tag.func_74762_e("PosZ")) });
/*      */     }
/*  712 */     return "";
/*      */   }
/*      */   
/*      */   public void bindToLocation(World world, int posX, int posY, int posZ, int dimension, String dimensionName, ItemStack itemstack)
/*      */   {
/*  717 */     if (!itemstack.func_77942_o()) {
/*  718 */       itemstack.func_77982_d(new NBTTagCompound());
/*      */     }
/*      */     
/*  721 */     itemstack.func_77978_p().func_74768_a("PosX", posX);
/*  722 */     itemstack.func_77978_p().func_74768_a("PosY", posY);
/*  723 */     itemstack.func_77978_p().func_74768_a("PosZ", posZ);
/*  724 */     itemstack.func_77978_p().func_74768_a("PosD", dimension);
/*  725 */     itemstack.func_77978_p().func_74778_a("NameD", dimensionName);
/*      */   }
/*      */   
/*      */   public boolean hasLocationBinding(ItemStack itemstack) {
/*  729 */     if (itemstack.func_77942_o()) {
/*  730 */       NBTTagCompound nbtTag = itemstack.func_77978_p();
/*  731 */       return (nbtTag.func_74764_b("PosX")) && (nbtTag.func_74764_b("PosY")) && (nbtTag.func_74764_b("PosZ")) && (nbtTag.func_74764_b("PosD")) && (nbtTag.func_74764_b("NameD"));
/*      */     }
/*  733 */     return false;
/*      */   }
/*      */   
/*      */   public void copyLocationBinding(ItemStack from, ItemStack to)
/*      */   {
/*  738 */     if (hasLocationBinding(from)) {
/*  739 */       NBTTagCompound nbtTagFrom = from.func_77978_p();
/*  740 */       if (!to.func_77942_o()) {
/*  741 */         to.func_77982_d(new NBTTagCompound());
/*      */       }
/*  743 */       NBTTagCompound nbtTagTo = to.func_77978_p();
/*  744 */       nbtTagTo.func_74768_a("PosX", nbtTagFrom.func_74762_e("PosX"));
/*  745 */       nbtTagTo.func_74768_a("PosY", nbtTagFrom.func_74762_e("PosY"));
/*  746 */       nbtTagTo.func_74768_a("PosZ", nbtTagFrom.func_74762_e("PosZ"));
/*  747 */       nbtTagTo.func_74768_a("PosD", nbtTagFrom.func_74762_e("PosD"));
/*  748 */       nbtTagTo.func_74778_a("NameD", nbtTagFrom.func_74779_i("NameD"));
/*  749 */       if (from.func_82837_s()) {
/*  750 */         to.func_151001_c(from.func_82833_r());
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean copyLocationBinding(World world, ItemStack from, BlockCircle.TileEntityCircle.ActivatedRitual to) {
/*  756 */     if (!hasLocationBinding(from)) {
/*  757 */       return false;
/*      */     }
/*      */     
/*  760 */     NBTTagCompound nbtTagFrom = from.func_77978_p();
/*  761 */     if (nbtTagFrom.func_74762_e("PosD") != world.field_73011_w.field_76574_g) {
/*  762 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  766 */     Coord coord = new Coord(nbtTagFrom.func_74762_e("PosX"), nbtTagFrom.func_74762_e("PosY"), nbtTagFrom.func_74762_e("PosZ"));
/*  767 */     to.setLocation(coord);
/*  768 */     return true;
/*      */   }
/*      */   
/*      */   public ItemStack func_77654_b(ItemStack itemstack, World world, EntityPlayer player)
/*      */   {
/*  773 */     SubItem subItem = (SubItem)this.subItems.get(itemstack.func_77960_j());
/*      */     
/*  775 */     if (this.itemWaystoneBound.isMatch(itemstack)) {
/*  776 */       if ((!world.field_72995_K) && ((player instanceof EntityPlayerMP))) {
/*  777 */         Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), (EntityPlayerMP)player);
/*      */       }
/*  779 */       return itemstack;
/*      */     }
/*      */     
/*  782 */     if ((subItem instanceof Edible)) {
/*  783 */       if (!player.field_71075_bZ.field_75098_d) {
/*  784 */         itemstack.field_77994_a -= 1;
/*  785 */         if (itemstack.field_77994_a <= 0) {
/*  786 */           itemstack.field_77994_a = 0;
/*  787 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*      */         }
/*      */       }
/*  790 */       Edible edible = (Edible)subItem;
/*      */       
/*  792 */       if (subItem == this.itemArtichoke) {
/*  793 */         int foodLevel = player.func_71024_bL().func_75116_a();
/*  794 */         player.func_71024_bL().func_75122_a(edible.healAmount, edible.saturationModifier);
/*  795 */         int healed = player.func_71024_bL().func_75116_a() - foodLevel;
/*  796 */         player.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, 3 * healed * 20, 2));
/*  797 */       } else if (subItem == this.itemSleepingApple) {
/*  798 */         player.func_71024_bL().func_75122_a(edible.healAmount, edible.saturationModifier);
/*  799 */         if ((player.field_71093_bK == 0) && (!world.field_72995_K) && (!WorldProviderDreamWorld.getPlayerIsGhost(player))) {
/*  800 */           WorldProviderDreamWorld.sendPlayerToSpiritWorld(player, 1.0D);
/*  801 */           itemstack.field_77994_a = 0;
/*  802 */           world.func_72956_a(player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*  803 */           return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
/*      */         }
/*      */       } else {
/*  806 */         player.func_71024_bL().func_75122_a(edible.healAmount, edible.saturationModifier);
/*      */       }
/*  808 */       world.func_72956_a(player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*  809 */     } else if ((subItem instanceof Drinkable)) {
/*  810 */       if ((this.itemDemonHeart.isMatch(itemstack)) && (player.func_70093_af()))
/*      */       {
/*  812 */         return itemstack;
/*      */       }
/*  814 */       if (!player.field_71075_bZ.field_75098_d) {
/*  815 */         itemstack.field_77994_a -= 1;
/*  816 */         if (itemstack.field_77994_a <= 0) {
/*  817 */           itemstack.field_77994_a = 0;
/*  818 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*      */         }
/*      */       }
/*  821 */       Drinkable drinkable = (Drinkable)subItem;
/*  822 */       for (PotionEffect effect : drinkable.effects) {
/*  823 */         player.func_70690_d(new PotionEffect(effect));
/*      */       }
/*  825 */       if (this.itemDemonHeart.isMatch(itemstack)) {
/*  826 */         player.func_70015_d(2640);
/*  827 */       } else if (this.itemBrewGrotesque.isMatch(itemstack)) {
/*  828 */         if (!world.field_72995_K) {
/*  829 */           Infusion.getNBT(player).func_74768_a("witcheryGrotesque", 1200);
/*  830 */           Witchery.packetPipeline.sendToDimension(new com.emoniph.witchery.network.PacketPlayerStyle(player), player.field_71093_bK);
/*      */         }
/*  832 */       } else if (this.itemBrewOfSleeping.isMatch(itemstack)) {
/*  833 */         if ((player.field_71093_bK == 0) && (!world.field_72995_K) && (!WorldProviderDreamWorld.getPlayerIsGhost(player))) {
/*  834 */           WorldProviderDreamWorld.sendPlayerToSpiritWorld(player, 0.998D);
/*  835 */           itemstack.field_77994_a = 0;
/*  836 */           world.func_72956_a(player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*  837 */           return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
/*      */         }
/*  839 */       } else if (this.itemPurifiedMilk.isMatch(itemstack)) {
/*  840 */         if ((!world.field_72995_K) && 
/*  841 */           (world.field_73012_v.nextInt(2) == 0)) {
/*  842 */           Collection c = player.func_70651_bq();
/*  843 */           if ((c != null) && (!c.isEmpty())) {
/*  844 */             Object[] objs = c.toArray();
/*      */             
/*  846 */             int itemIndex = world.field_73012_v.nextInt(c.size());
/*  847 */             PotionEffect effect = (PotionEffect)objs[itemIndex];
/*  848 */             player.func_82170_o(effect.func_76456_a());
/*      */           }
/*      */         }
/*      */       }
/*  852 */       else if (this.itemBrewOfTheDepths.isMatch(itemstack)) {
/*  853 */         if (!world.field_72995_K) {
/*  854 */           Infusion.getNBT(player).func_74768_a("witcheryDepths", 300);
/*      */         }
/*      */       }
/*  857 */       else if (this.itemCreeperHeart.isMatch(itemstack)) {
/*  858 */         if (!world.field_72995_K) {
/*  859 */           if (Config.instance().allowExplodingCreeperHearts) {
/*  860 */             world.func_72876_a(player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 3.0F, true);
/*      */           } else {
/*  862 */             world.func_72876_a(player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, false);
/*      */           }
/*      */         }
/*  865 */       } else if (this.itemFrozenHeart.isMatch(itemstack)) {
/*  866 */         if (!world.field_72995_K) {
/*  867 */           PlayerEffects.onDeath(player);
/*      */         }
/*      */       } else {
/*  870 */         drinkable.onDrunk(world, player, itemstack);
/*      */       }
/*  872 */       world.func_72956_a(player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*      */     }
/*  874 */     return itemstack;
/*      */   }
/*      */   
/*      */   public int func_77626_a(ItemStack itemstack)
/*      */   {
/*  879 */     SubItem subItem = (SubItem)this.subItems.get(itemstack.func_77960_j());
/*  880 */     if (((subItem instanceof Edible)) || ((subItem instanceof Drinkable)))
/*  881 */       return 32;
/*  882 */     if (this.itemWaystoneBound.isMatch(itemstack))
/*  883 */       return 1200;
/*  884 */     if (this.itemContractTorment.isMatch(itemstack))
/*  885 */       return 1200;
/*  886 */     if (this.itemSeerStone.isMatch(itemstack)) {
/*  887 */       return 1200;
/*      */     }
/*  889 */     return super.func_77626_a(itemstack);
/*      */   }
/*      */   
/*      */ 
/*      */   public EnumAction func_77661_b(ItemStack itemstack)
/*      */   {
/*  895 */     SubItem subItem = (SubItem)this.subItems.get(itemstack.func_77960_j());
/*  896 */     if ((subItem instanceof Edible))
/*  897 */       return EnumAction.eat;
/*  898 */     if ((subItem instanceof Drinkable))
/*  899 */       return ((Drinkable)subItem).useAction;
/*  900 */     if (this.itemContractTorment.isMatch(itemstack))
/*  901 */       return EnumAction.bow;
/*  902 */     if (this.itemSeerStone.isMatch(itemstack)) {
/*  903 */       return EnumAction.bow;
/*      */     }
/*  905 */     return super.func_77661_b(itemstack);
/*      */   }
/*      */   
/*      */ 
/*      */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*      */   {
/*  911 */     World world = player.field_70170_p;
/*  912 */     int elapsedTicks = func_77626_a(stack) - countdown;
/*  913 */     if ((!world.field_72995_K) && ((player instanceof EntityPlayerMP))) {
/*  914 */       if (this.itemWaystoneBound.isMatch(stack)) {
/*  915 */         if (elapsedTicks % 20 == 0) {
/*  916 */           if (elapsedTicks == 0) {
/*  917 */             NBTTagCompound tag = stack.func_77978_p();
/*  918 */             if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
/*  919 */               int newX = tag.func_74762_e("PosX");
/*  920 */               int newY = tag.func_74762_e("PosY");
/*  921 */               int newZ = tag.func_74762_e("PosZ");
/*  922 */               int newD = tag.func_74762_e("PosD");
/*      */               
/*  924 */               EntityEye eye = new EntityEye(world);
/*  925 */               eye.func_70012_b(newX, newY, newZ, player.field_70177_z, 90.0F);
/*  926 */               world.func_72838_d(eye);
/*      */               
/*  928 */               Witchery.packetPipeline.sendTo(new PacketCamPos(true, elapsedTicks == 0, eye), (EntityPlayerMP)player);
/*      */             }
/*      */           } else {
/*  931 */             Witchery.packetPipeline.sendTo(new PacketCamPos(true, false, null), (EntityPlayerMP)player);
/*      */           }
/*      */         }
/*  934 */       } else if (this.itemContractTorment.isMatch(stack)) {
/*  935 */         if (!world.field_72995_K) {
/*  936 */           if ((elapsedTicks == 0) || (elapsedTicks == 40)) {
/*  937 */             if (Infusion.aquireEnergy(world, player, 10, true)) {
/*  938 */               if ((elapsedTicks > 0) || (circleNear(world, player))) {
/*  939 */                 SoundEffect.MOB_BLAZE_DEATH.playAtPlayer(world, player);
/*      */               } else {
/*  941 */                 SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*  942 */                 ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:ingredient.contractTorment.nostones", new Object[0]);
/*  943 */                 player.func_71041_bz();
/*      */               }
/*      */             }
/*  946 */           } else if ((elapsedTicks == 80) || (elapsedTicks == 120)) {
/*  947 */             if (Infusion.aquireEnergy(world, player, 10, true))
/*      */             {
/*  949 */               ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_BLAZE_DEATH, player, 1.0D, 2.0D, 16);
/*      */ 
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*  955 */           else if ((elapsedTicks == 160) || (elapsedTicks == 200) || (elapsedTicks == 240)) {
/*  956 */             if (Infusion.aquireEnergy(world, player, 10, true))
/*      */             {
/*  958 */               ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_BLAZE_DEATH, player, 1.0D, 2.0D, 16);
/*  959 */               ParticleEffect.FLAME.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);
/*      */ 
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*  965 */           else if ((elapsedTicks == 280) && 
/*  966 */             (Infusion.aquireEnergy(world, player, 10, true))) {
/*  967 */             if (circleNear(world, player)) {
/*  968 */               ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);
/*  969 */               ParticleEffect.FLAME.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);
/*  970 */               ParticleEffect.FLAME.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);
/*  971 */               player.func_71041_bz();
/*  972 */               EntityLiving living = InfusionInfernal.spawnCreature(world, EntityLordOfTorment.class, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, null, 2, 4, ParticleEffect.FLAME, SoundEffect.MOB_ENDERDRAGON_GROWL);
/*  973 */               if (living != null) {
/*  974 */                 if (!player.field_71075_bZ.field_75098_d) {
/*  975 */                   stack.field_77994_a -= 1;
/*  976 */                   if (stack.field_77994_a <= 0) {
/*  977 */                     player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*      */                   }
/*      */                 }
/*  980 */                 living.func_110163_bv();
/*  981 */                 world.func_72885_a(living, living.field_70165_t, living.field_70163_u + living.func_70047_e(), living.field_70161_v, 7.0F, false, world.func_82736_K().func_82766_b("mobGriefing"));
/*      */               } else {
/*  983 */                 SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */               }
/*      */             }
/*      */             else {
/*  987 */               SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*  988 */               player.func_71041_bz();
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  993 */       else if (this.itemSeerStone.isMatch(stack))
/*      */       {
/*  995 */         EntityCovenWitch.summonCoven(world, player, new Coord(player), elapsedTicks);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean circleNear(World world, EntityPlayer player) {
/* 1001 */     int midX = MathHelper.func_76128_c(player.field_70165_t);
/* 1002 */     int midY = MathHelper.func_76128_c(player.field_70163_u);
/* 1003 */     int midZ = MathHelper.func_76128_c(player.field_70161_v);
/*      */     
/* 1005 */     int[][] PATTERN = { { 0, 0, 0, 0, 4, 3, 4, 0, 0, 0, 0 }, { 0, 0, 4, 3, 1, 1, 1, 3, 4, 0, 0 }, { 0, 4, 1, 1, 1, 1, 1, 1, 1, 4, 0 }, { 0, 3, 1, 1, 1, 1, 1, 1, 1, 3, 0 }, { 4, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4 }, { 3, 1, 1, 1, 2, 1, 2, 1, 1, 1, 3 }, { 4, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4 }, { 0, 3, 1, 1, 1, 1, 1, 1, 1, 3, 0 }, { 0, 4, 1, 1, 1, 1, 1, 1, 1, 4, 0 }, { 0, 0, 4, 3, 1, 1, 1, 3, 4, 0, 0 }, { 0, 0, 0, 0, 4, 3, 4, 0, 0, 0, 0 } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1019 */     int offsetZ = (PATTERN.length - 1) / 2;
/* 1020 */     for (int z = 0; z < PATTERN.length - 1; z++) {
/* 1021 */       int worldZ = midZ - offsetZ + z;
/* 1022 */       int offsetX = (PATTERN[z].length - 1) / 2;
/* 1023 */       for (int x = 0; x < PATTERN[z].length; x++) {
/* 1024 */         int worldX = midX - offsetX + x;
/* 1025 */         int value = PATTERN[(PATTERN.length - 1 - z)][x];
/* 1026 */         if (value != 0) {
/* 1027 */           if (!isPost(world, worldX, midY, worldZ, (value == 2) || (value == 4), value == 4, (value == 3) || (value == 4))) {
/* 1028 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1034 */     return true;
/*      */   }
/*      */   
/*      */   private boolean isPost(World world, int x, int y, int z, boolean bottomSolid, boolean midSolid, boolean topSolid) {
/* 1038 */     Block blockBelow = BlockUtil.getBlock(world, x, y - 1, z);
/* 1039 */     Block blockBottom = BlockUtil.getBlock(world, x, y, z);
/* 1040 */     Block blockMid = BlockUtil.getBlock(world, x, y + 1, z);
/* 1041 */     Block blockTop = BlockUtil.getBlock(world, x, y + 2, z);
/* 1042 */     Block blockAbove = BlockUtil.getBlock(world, x, y + 3, z);
/*      */     
/* 1044 */     if ((blockBelow == null) || (!blockBelow.func_149688_o().func_76220_a())) {
/* 1045 */       return false;
/*      */     }
/*      */     
/* 1048 */     if (bottomSolid) {
/* 1049 */       if ((blockBottom == null) || (!blockBottom.func_149688_o().func_76220_a())) {
/* 1050 */         return false;
/*      */       }
/*      */       
/*      */     }
/* 1054 */     else if ((blockBottom != null) && (blockBottom.func_149688_o().func_76220_a())) {
/* 1055 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1059 */     if (midSolid) {
/* 1060 */       if ((blockMid == null) || (!blockMid.func_149688_o().func_76220_a())) {
/* 1061 */         return false;
/*      */       }
/*      */       
/*      */     }
/* 1065 */     else if ((blockMid != null) && (blockMid.func_149688_o().func_76220_a())) {
/* 1066 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1070 */     if (topSolid) {
/* 1071 */       if ((blockTop == null) || (!blockTop.func_149688_o().func_76220_a())) {
/* 1072 */         return false;
/*      */       }
/*      */       
/*      */     }
/* 1076 */     else if ((blockTop != null) && (blockTop.func_149688_o().func_76220_a())) {
/* 1077 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1081 */     return (blockAbove == null) || (!blockAbove.func_149688_o().func_76220_a());
/*      */   }
/*      */   
/*      */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*      */   {
/* 1086 */     Block block = BlockUtil.getBlock(world, x, y, z);
/* 1087 */     if ((this.itemWaystoneBound.isMatch(stack)) && (block == Witchery.Blocks.CRYSTAL_BALL)) {
/* 1088 */       if ((!world.field_72995_K) && (BlockCrystalBall.tryConsumePower(world, player, x, y, z))) {
/* 1089 */         NBTTagCompound tag = stack.func_77978_p();
/* 1090 */         if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
/* 1091 */           int newX = tag.func_74762_e("PosX");
/* 1092 */           int newY = tag.func_74762_e("PosY");
/* 1093 */           int newZ = tag.func_74762_e("PosZ");
/* 1094 */           int newD = tag.func_74762_e("PosD");
/* 1095 */           double MAX_DISTANCE = 22500.0D;
/* 1096 */           if ((newD == player.field_71093_bK) && (player.func_70092_e(newX, newY, newZ) <= 22500.0D)) {
/* 1097 */             player.func_71008_a(stack, func_77626_a(stack));
/*      */           } else {
/* 1099 */             SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */           }
/*      */         } else {
/* 1102 */           SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */         }
/* 1104 */       } else if (world.field_72995_K) {
/* 1105 */         player.func_71008_a(stack, func_77626_a(stack));
/*      */       }
/* 1107 */       return !world.field_72995_K;
/*      */     }
/* 1109 */     return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*      */   }
/*      */   
/*      */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
/*      */   {
/* 1114 */     if ((!world.field_72995_K) && (this.itemWaystoneBound.isMatch(stack)) && ((player instanceof EntityPlayerMP))) {
/* 1115 */       Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), (EntityPlayerMP)player);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isBook(ItemStack itemstack) {
/* 1120 */     return (this.itemBookOven.isMatch(itemstack)) || (this.itemBookDistilling.isMatch(itemstack)) || (this.itemBookCircleMagic.isMatch(itemstack)) || (this.itemBookInfusions.isMatch(itemstack)) || (this.itemBookHerbology.isMatch(itemstack)) || (this.itemBookBiomes.isMatch(itemstack)) || (this.itemBookWands.isMatch(itemstack)) || (this.itemBookBurning.isMatch(itemstack));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*      */   {
/* 1127 */     SubItem subItem = (SubItem)this.subItems.get(itemstack.func_77960_j());
/* 1128 */     if (isBook(itemstack)) {
/* 1129 */       openWitchcraftBook(world, player, itemstack);
/* 1130 */     } else if (this.itemWolfsbane.isMatch(itemstack)) {
/* 1131 */       if (!world.field_72995_K) {
/* 1132 */         MovingObjectPosition mop = InfusionOtherwhere.raytraceEntities(world, player, true, 2.0D);
/* 1133 */         if ((mop != null) && (mop.field_72308_g != null)) {
/* 1134 */           if (CreatureUtil.isWerewolf(mop.field_72308_g, true)) {
/* 1135 */             ParticleEffect.FLAME.send(SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL, mop.field_72308_g, 0.5D, 1.5D, 16);
/*      */           } else {
/* 1137 */             SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */           }
/* 1139 */           itemstack.field_77994_a -= 1;
/* 1140 */           if (itemstack.field_77994_a <= 0) {
/* 1141 */             itemstack.field_77994_a = 0;
/* 1142 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*      */           }
/*      */         }
/*      */       }
/* 1146 */     } else if ((subItem instanceof Edible)) {
/* 1147 */       if ((player.func_71043_e(false)) || (((Edible)subItem).eatAnyTime)) {
/* 1148 */         player.func_71008_a(itemstack, func_77626_a(itemstack));
/*      */       }
/* 1150 */     } else if ((subItem instanceof Drinkable)) {
/* 1151 */       player.func_71008_a(itemstack, func_77626_a(itemstack));
/* 1152 */     } else if (this.itemContractTorment.isMatch(itemstack)) {
/* 1153 */       player.func_71008_a(itemstack, func_77626_a(itemstack));
/* 1154 */     } else if ((subItem instanceof Brew)) {
/* 1155 */       throwBrew(itemstack, world, player, subItem);
/* 1156 */     } else if (this.itemQuicklime.isMatch(itemstack)) {
/* 1157 */       throwBrew(itemstack, world, player, this.itemQuicklime);
/* 1158 */     } else if (this.itemNecroStone.isMatch(itemstack)) {
/* 1159 */       useNecroStone(world, player, itemstack);
/*      */     }
/* 1161 */     else if (this.itemBatBall.isMatch(itemstack)) {
/* 1162 */       itemstack.field_77994_a -= 1;
/* 1163 */       if (itemstack.field_77994_a <= 0) {
/* 1164 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*      */       }
/* 1166 */       if (!world.field_72995_K) {
/* 1167 */         EntityItem item = new EntityItem(world, player.field_70165_t, player.field_70163_u + 1.3D, player.field_70161_v, this.itemBatBall.createStack());
/* 1168 */         item.field_145804_b = 5;
/* 1169 */         item.func_70012_b(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v, player.field_70177_z, player.field_70125_A);
/* 1170 */         item.field_70165_t -= MathHelper.func_76134_b(item.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 1171 */         item.field_70163_u -= 0.10000000149011612D;
/* 1172 */         item.field_70161_v -= MathHelper.func_76126_a(item.field_70177_z / 180.0F * 3.1415927F) * 0.16F;
/* 1173 */         item.func_70107_b(item.field_70165_t, item.field_70163_u, item.field_70161_v);
/* 1174 */         item.field_70129_M = 0.0F;
/* 1175 */         float f = 0.4F;
/* 1176 */         item.field_70159_w = (-MathHelper.func_76126_a(item.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(item.field_70125_A / 180.0F * 3.1415927F) * f);
/* 1177 */         item.field_70179_y = (MathHelper.func_76134_b(item.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(item.field_70125_A / 180.0F * 3.1415927F) * f);
/* 1178 */         item.field_70181_x = (-MathHelper.func_76126_a((item.field_70125_A + 0.0F) / 180.0F * 3.1415927F) * f);
/* 1179 */         setThrowableHeading(item, item.field_70159_w, item.field_70181_x, item.field_70179_y, 1.0F, 1.0F);
/* 1180 */         world.func_72838_d(item);
/*      */       }
/* 1182 */     } else if (this.itemSeerStone.isMatch(itemstack)) {
/* 1183 */       useSeerStone(world, player, itemstack);
/* 1184 */     } else if (this.itemIcyNeedle.isMatch(itemstack)) {
/* 1185 */       useIcyNeedle(world, player, itemstack);
/* 1186 */       return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
/*      */     }
/*      */     
/* 1189 */     return super.func_77659_a(itemstack, world, player);
/*      */   }
/*      */   
/*      */   public void setThrowableHeading(EntityItem item, double par1, double par3, double par5, float par7, float par8)
/*      */   {
/* 1194 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/* 1195 */     par1 /= f2;
/* 1196 */     par3 /= f2;
/* 1197 */     par5 /= f2;
/* 1198 */     par1 += item.field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * par8;
/* 1199 */     par3 += item.field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * par8;
/* 1200 */     par5 += item.field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * par8;
/* 1201 */     par1 *= par7;
/* 1202 */     par3 *= par7;
/* 1203 */     par5 *= par7;
/* 1204 */     item.field_70159_w = par1;
/* 1205 */     item.field_70181_x = par3;
/* 1206 */     item.field_70179_y = par5;
/* 1207 */     float f3 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
/* 1208 */     item.field_70126_B = (item.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
/* 1209 */     item.field_70127_C = (item.field_70125_A = (float)(Math.atan2(par3, f3) * 180.0D / 3.141592653589793D));
/*      */   }
/*      */   
/*      */   private void useIcyNeedle(World world, EntityPlayer player, ItemStack itemstack)
/*      */   {
/* 1214 */     if (!player.field_71075_bZ.field_75098_d) {
/* 1215 */       itemstack.field_77994_a -= 1;
/* 1216 */       if (itemstack.field_77994_a <= 0) {
/* 1217 */         itemstack.field_77994_a = 0;
/* 1218 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*      */       }
/*      */     }
/*      */     
/* 1222 */     if (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) {
/* 1223 */       WorldProviderDreamWorld.returnPlayerToOverworld(player);
/* 1224 */       itemstack.field_77994_a = 0;
/* 1225 */     } else if (WorldProviderDreamWorld.getPlayerIsGhost(player)) {
/* 1226 */       WorldProviderDreamWorld.returnGhostPlayerToSpiritWorld(player);
/* 1227 */       itemstack.field_77994_a = 0;
/*      */     } else {
/* 1229 */       player.func_70097_a(DamageSource.func_76365_a(player), 1.0F);
/*      */     }
/*      */   }
/*      */   
/*      */   public void throwBrew(ItemStack itemstack, World world, EntityPlayer player) {
/* 1234 */     if ((itemstack != null) && (itemstack.func_77973_b() == this)) {
/* 1235 */       SubItem subItem = (SubItem)this.subItems.get(itemstack.func_77960_j());
/* 1236 */       throwBrew(itemstack, world, player, subItem);
/*      */     }
/*      */   }
/*      */   
/*      */   private void throwBrew(ItemStack itemstack, World world, EntityPlayer player, SubItem item) {
/* 1241 */     if (!player.field_71075_bZ.field_75098_d) {
/* 1242 */       itemstack.field_77994_a -= 1;
/*      */     }
/*      */     
/* 1245 */     world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*      */     
/* 1247 */     if (!world.field_72995_K) {
/* 1248 */       world.func_72838_d(new com.emoniph.witchery.entity.EntityWitchProjectile(world, player, item));
/*      */     }
/*      */   }
/*      */   
/*      */   private void openWitchcraftBook(World world, EntityPlayer player, ItemStack itemstack) {
/* 1253 */     int posX = MathHelper.func_76128_c(player.field_70165_t);
/* 1254 */     int posY = MathHelper.func_76128_c(player.field_70163_u);
/* 1255 */     int posZ = MathHelper.func_76128_c(player.field_70161_v);
/* 1256 */     player.openGui(Witchery.instance, 1, world, posX, posY, posZ);
/*      */   }
/*      */   
/*      */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
/*      */   {
/* 1261 */     if (this.itemMutandis.isMatch(itemstack))
/* 1262 */       return useMutandis(false, itemstack, player, world, posX, posY, posZ);
/* 1263 */     if (this.itemAnnointingPaste.isMatch(itemstack))
/* 1264 */       return useAnnointingPaste(itemstack, player, world, posX, posY, posZ);
/* 1265 */     if (this.itemKobolditePentacle.isMatch(itemstack)) {
/* 1266 */       if ((world.func_147439_a(posX, posY, posZ) == Witchery.Blocks.ALTAR) && (side == 1) && (world.func_147439_a(posX, posY + 1, posZ) == Blocks.field_150350_a)) {
/* 1267 */         BlockPlacedItem.placeItemInWorld(itemstack, player, world, posX, posY + 1, posZ);
/* 1268 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/* 1269 */         return !world.field_72995_K;
/*      */       }
/* 1271 */       return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10); }
/* 1272 */     if (this.itemMutandisExtremis.isMatch(itemstack))
/* 1273 */       return useMutandis(true, itemstack, player, world, posX, posY, posZ);
/* 1274 */     if ((this.itemChaliceEmpty.isMatch(itemstack)) || (this.itemChaliceFull.isMatch(itemstack)))
/* 1275 */       return placeBlock(Witchery.Blocks.CHALICE, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/* 1276 */     if (this.itemCandelabra.isMatch(itemstack))
/* 1277 */       return placeBlock(Witchery.Blocks.CANDELABRA, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/* 1278 */     if (this.itemBroomEnchanted.isMatch(itemstack))
/* 1279 */       return placeBroom(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/* 1280 */     if ((this.subItems.get(itemstack.func_77960_j()) instanceof DreamWeave))
/* 1281 */       return placeDreamCatcher(world, player, itemstack, posX, posY, posZ, side);
/* 1282 */     if (this.itemDoorRowan.isMatch(itemstack))
/* 1283 */       return placeDoor(world, player, itemstack, posX, posY, posZ, side, Witchery.Blocks.DOOR_ROWAN);
/* 1284 */     if (this.itemDoorAlder.isMatch(itemstack))
/* 1285 */       return placeDoor(world, player, itemstack, posX, posY, posZ, side, Witchery.Blocks.DOOR_ALDER);
/* 1286 */     if (this.itemDoorIce.isMatch(itemstack))
/* 1287 */       return placeDoor(world, player, itemstack, posX, posY, posZ, side, Witchery.Blocks.PERPETUAL_ICE_DOOR);
/* 1288 */     if ((this.itemSubduedSpirit.isMatch(itemstack)) || (this.itemSubduedSpiritVillage.isMatch(itemstack)))
/* 1289 */       return useSubduedSpirit(world, player, itemstack, posX, posY, posZ, side);
/* 1290 */     if (this.itemSeedsTreefyd.isMatch(itemstack))
/* 1291 */       return placeTreefyd(world, player, itemstack, posX, posY, posZ, side);
/* 1292 */     if (this.itemBinkyHead.isMatch(itemstack))
/* 1293 */       return placeBinky(world, player, itemstack, posX, posY, posZ, side);
/* 1294 */     if (this.itemInfernalBlood.isMatch(itemstack))
/* 1295 */       return placeInfernalBlood(world, player, itemstack, posX, posY, posZ, side);
/* 1296 */     if ((this.itemDemonHeart.isMatch(itemstack)) && (player.func_70093_af()))
/* 1297 */       return placeBlock(Witchery.Blocks.DEMON_HEART, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/* 1298 */     if ((this.itemBoneNeedle.isMatch(itemstack)) && (ExtendedPlayer.get(player).isVampire())) {
/* 1299 */       Block block = world.func_147439_a(posX, posY, posZ);
/* 1300 */       if ((block == Blocks.field_150325_L) && (world.func_72805_g(posX, posY, posZ) == 0)) {
/* 1301 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 1302 */         if ((playerEx.getVampireLevel() >= 4) && 
/* 1303 */           (playerEx.decreaseBloodPower(125, true))) {
/* 1304 */           world.func_147449_b(posX, posY, posZ, Witchery.Blocks.BLOODED_WOOL);
/* 1305 */           ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, posX + 0.5D, posY + 0.5D, posZ + 0.5D, 1.0D, 1.0D, 16);
/*      */           
/* 1307 */           return true;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1312 */       SoundEffect.NOTE_SNARE.playOnlyTo(player);
/* 1313 */       return true;
/*      */     }
/* 1315 */     return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/*      */   }
/*      */   
/*      */   private boolean placeBinky(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
/*      */   {
/* 1320 */     if (side != 1) {
/* 1321 */       return false;
/*      */     }
/* 1323 */     posY++;
/* 1324 */     Material material = world.func_147439_a(posX, posY, posZ).func_149688_o();
/* 1325 */     if ((material == null) || (!material.func_76220_a())) {
/* 1326 */       if (!world.field_72995_K) {
/* 1327 */         EntityDeathsHorse horse = new EntityDeathsHorse(world);
/* 1328 */         horse.func_110234_j(true);
/* 1329 */         horse.func_110214_p(4);
/* 1330 */         horse.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/* 1331 */         horse.func_110163_bv();
/* 1332 */         horse.func_94058_c(Witchery.resource("item.witchery.horseofdeath.customname"));
/* 1333 */         horse.func_70012_b(0.5D + posX, 0.01D + posY, 0.5D + posZ, 0.0F, 0.0F);
/* 1334 */         NBTTagCompound nbtHorse = horse.getEntityData();
/* 1335 */         if (nbtHorse != null) {
/* 1336 */           nbtHorse.func_74757_a("WITCIsBinky", true);
/*      */         }
/*      */         
/* 1339 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5D + posX, posY, 0.5D + posZ, 1.0D, 1.0D, 16);
/*      */         
/*      */ 
/* 1342 */         world.func_72838_d(horse);
/*      */       }
/* 1344 */       itemstack.field_77994_a -= 1;
/*      */     }
/* 1346 */     return true;
/*      */   }
/*      */   
/*      */   private boolean placeInfernalBlood(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
/*      */   {
/* 1351 */     Block block = world.func_147439_a(posX, posY, posZ);
/* 1352 */     int meta = world.func_72805_g(posX, posY, posZ);
/* 1353 */     if ((block == Witchery.Blocks.WICKER_BUNDLE) && (BlockWickerBundle.limitToValidMetadata(meta) == 0)) {
/* 1354 */       if (!world.field_72995_K) {
/* 1355 */         int uses = 5;
/* 1356 */         for (int y = posY - 1; (y <= posY + 1) && (uses > 0); y++) {
/* 1357 */           for (int x = posX - 1; (x <= posX + 1) && (uses > 0); x++) {
/* 1358 */             for (int z = posZ - 1; (z <= posZ + 1) && (uses > 0); z++) {
/* 1359 */               Block b = world.func_147439_a(x, y, z);
/* 1360 */               int m = world.func_72805_g(x, y, z);
/* 1361 */               if ((b == Witchery.Blocks.WICKER_BUNDLE) && (BlockWickerBundle.limitToValidMetadata(m) == 0)) {
/* 1362 */                 world.func_147465_d(x, y, z, b, m | 0x1, 3);
/* 1363 */                 uses--;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1370 */       itemstack.field_77994_a -= 1;
/* 1371 */       return true;
/*      */     }
/* 1373 */     return false;
/*      */   }
/*      */   
/*      */   private boolean placeTreefyd(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
/*      */   {
/* 1378 */     if (side != 1) {
/* 1379 */       return false;
/*      */     }
/* 1381 */     posY++;
/* 1382 */     Material material = world.func_147439_a(posX, posY, posZ).func_149688_o();
/* 1383 */     if ((Blocks.field_150329_H.func_149718_j(world, posX, posY, posZ)) && ((material == null) || (!material.func_76220_a()))) {
/* 1384 */       if (!world.field_72995_K) {
/* 1385 */         world.func_147465_d(posX, posY, posZ, Blocks.field_150329_H, 1, 3);
/* 1386 */         EntityTreefyd entity = new EntityTreefyd(world);
/* 1387 */         entity.func_70012_b(0.5D + posX, posY, 0.5D + posZ, 0.0F, 0.0F);
/* 1388 */         entity.func_110161_a(null);
/* 1389 */         entity.func_110163_bv();
/* 1390 */         entity.setOwner(player.func_70005_c_());
/* 1391 */         world.func_72838_d(entity);
/*      */         
/* 1393 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, entity, 1.0D, 2.0D, 16);
/* 1394 */         ParticleEffect.EXPLODE.send(SoundEffect.NONE, entity, 1.0D, 2.0D, 16);
/*      */       }
/* 1396 */       itemstack.field_77994_a -= 1;
/*      */     }
/* 1398 */     return true;
/*      */   }
/*      */   
/*      */   private boolean useSeerStone(World world, EntityPlayer player, ItemStack stack)
/*      */   {
/* 1403 */     if (player.func_70093_af()) {
/* 1404 */       if (!world.field_72995_K) {
/* 1405 */         double MAX_TARGET_RANGE = 3.0D;
/* 1406 */         MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 3.0D);
/* 1407 */         if (mop != null) {
/* 1408 */           switch (mop.field_72313_a) {
/*      */           case ENTITY: 
/* 1410 */             if (!(mop.field_72308_g instanceof EntityPlayer)) break;
/* 1411 */             readPlayer(player, (EntityPlayer)mop.field_72308_g);
/* 1412 */             return true;
/*      */           
/*      */ 
/*      */           default: 
/* 1416 */             readPlayer(player, player);
/* 1417 */             break;
/*      */           }
/*      */         } else {
/* 1420 */           readPlayer(player, player);
/*      */         }
/*      */       }
/*      */     } else {
/* 1424 */       player.func_71008_a(stack, func_77626_a(stack));
/*      */     }
/*      */     
/* 1427 */     return true;
/*      */   }
/*      */   
/*      */   private void readPlayer(EntityPlayer player, EntityPlayer targetPlayer) {
/* 1431 */     String reading = "";
/* 1432 */     NBTTagCompound nbtPlayer = Infusion.getNBT(targetPlayer);
/*      */     
/* 1434 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCManifestDuration"))) {
/* 1435 */       int timeRemaining = nbtPlayer.func_74762_e("WITCManifestDuration");
/* 1436 */       if (timeRemaining > 0) {
/* 1437 */         reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.manifestationtime"), new Object[] { Integer.valueOf(timeRemaining).toString() }) + " ";
/*      */       } else {
/* 1439 */         reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nomanifestationtime") + " ";
/*      */       }
/*      */     } else {
/* 1442 */       reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nomanifestationtime") + " ";
/*      */     }
/*      */     
/* 1445 */     String familiarName = com.emoniph.witchery.familiar.Familiar.getFamiliarName(targetPlayer);
/* 1446 */     if ((familiarName != null) && (!familiarName.isEmpty())) {
/* 1447 */       reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.familiar"), new Object[] { familiarName }) + " ";
/*      */     } else {
/* 1449 */       reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nofamiliar") + " ";
/*      */     }
/*      */     
/* 1452 */     int covenSize = EntityCovenWitch.getCovenSize(targetPlayer);
/* 1453 */     if (covenSize > 0) {
/* 1454 */       reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.covensize"), new Object[] { Integer.valueOf(covenSize).toString() }) + " ";
/*      */     } else {
/* 1456 */       reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nocoven") + " ";
/*      */     }
/*      */     
/* 1459 */     String spellKnowledge = SymbolEffect.getKnowledge(targetPlayer);
/* 1460 */     if (!spellKnowledge.isEmpty()) {
/* 1461 */       reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.knownspells"), new Object[] { spellKnowledge }) + " ";
/*      */     } else {
/* 1463 */       reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nospells") + " ";
/*      */     }
/*      */     
/* 1466 */     ExtendedPlayer playerEx = ExtendedPlayer.get(targetPlayer);
/* 1467 */     if (playerEx != null) {
/* 1468 */       int bottlingSkill = playerEx.getSkillPotionBottling();
/* 1469 */       reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.bottlingskill"), new Object[] { Integer.valueOf(bottlingSkill).toString() }) + " ";
/*      */     }
/*      */     
/* 1472 */     if ((nbtPlayer != null) && ((nbtPlayer.func_74764_b("witcheryCursed")) || (nbtPlayer.func_74764_b("witcheryInsanity")) || (nbtPlayer.func_74764_b("witcherySinking")) || (nbtPlayer.func_74764_b("witcheryOverheating")) || (nbtPlayer.func_74764_b("witcheryWakingNightmare"))))
/*      */     {
/* 1474 */       if (nbtPlayer.func_74764_b("witcheryCursed")) {
/* 1475 */         int level = nbtPlayer.func_74762_e("witcheryCursed");
/* 1476 */         if (!reading.isEmpty()) {
/* 1477 */           reading = reading + ", ";
/*      */         }
/* 1479 */         reading = reading + String.format(Witchery.resource("witchery.item.seerstone.misfortune"), new Object[] { Integer.valueOf(level) });
/*      */       }
/*      */       
/* 1482 */       if (nbtPlayer.func_74764_b("witcheryInsanity")) {
/* 1483 */         int level = nbtPlayer.func_74762_e("witcheryInsanity");
/* 1484 */         if (!reading.isEmpty()) {
/* 1485 */           reading = reading + ", ";
/*      */         }
/* 1487 */         reading = reading + String.format(Witchery.resource("witchery.item.seerstone.insanity"), new Object[] { Integer.valueOf(level) });
/*      */       }
/*      */       
/* 1490 */       if (nbtPlayer.func_74764_b("witcherySinking")) {
/* 1491 */         int level = nbtPlayer.func_74762_e("witcherySinking");
/* 1492 */         if (!reading.isEmpty()) {
/* 1493 */           reading = reading + ", ";
/*      */         }
/* 1495 */         reading = reading + String.format(Witchery.resource("witchery.item.seerstone.sinking"), new Object[] { Integer.valueOf(level) });
/*      */       }
/*      */       
/* 1498 */       if (nbtPlayer.func_74764_b("witcheryOverheating")) {
/* 1499 */         int level = nbtPlayer.func_74762_e("witcheryOverheating");
/* 1500 */         if (!reading.isEmpty()) {
/* 1501 */           reading = reading + ", ";
/*      */         }
/* 1503 */         reading = reading + String.format(Witchery.resource("witchery.item.seerstone.overheating"), new Object[] { Integer.valueOf(level) });
/*      */       }
/*      */       
/* 1506 */       if (nbtPlayer.func_74764_b("witcheryWakingNightmare")) {
/* 1507 */         int level = nbtPlayer.func_74762_e("witcheryWakingNightmare");
/* 1508 */         if (!reading.isEmpty()) {
/* 1509 */           reading = reading + ", ";
/*      */         }
/* 1511 */         reading = reading + String.format(Witchery.resource("witchery.item.seerstone.nightmare"), new Object[] { Integer.valueOf(level) });
/*      */       }
/*      */     }
/*      */     else {
/* 1515 */       reading = reading + Witchery.resource("witchery.item.seerstone.notcursed");
/*      */     }
/*      */     
/* 1518 */     ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, reading);
/*      */   }
/*      */   
/*      */   private boolean useSubduedSpirit(World world, EntityPlayer player, ItemStack itemstack, int x, int y, int z, int side) {
/* 1522 */     if (!world.field_72995_K) {
/* 1523 */       EntityCreature creature = Infusion.spawnCreature(world, EntitySpirit.class, x, y, z, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
/* 1524 */       if (creature != null) {
/* 1525 */         EntitySpirit spirit = (EntitySpirit)creature;
/* 1526 */         creature.func_110163_bv();
/*      */         
/* 1528 */         if (this.itemSubduedSpiritVillage.isMatch(itemstack)) {
/* 1529 */           spirit.setTarget("Village", 1);
/*      */         }
/*      */         
/* 1532 */         if (!player.field_71075_bZ.field_75098_d) {
/* 1533 */           if (--itemstack.field_77994_a == 0) {
/* 1534 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/* 1535 */             if ((player instanceof EntityPlayerMP)) {
/* 1536 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/* 1541 */       return true;
/*      */     }
/* 1543 */     return false;
/*      */   }
/*      */   
/*      */   private boolean useNecroStone(World world, EntityPlayer player, ItemStack itemstack)
/*      */   {
/* 1548 */     if (world.field_72995_K) {
/* 1549 */       return false;
/*      */     }
/*      */     
/* 1552 */     double MAX_TARGET_RANGE = 15.0D;
/* 1553 */     MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 15.0D);
/*      */     
/* 1555 */     if (mop != null) {
/* 1556 */       switch (mop.field_72313_a) {
/*      */       case ENTITY: 
/* 1558 */         if ((mop.field_72308_g instanceof EntityLivingBase)) {
/* 1559 */           if (!player.func_70093_af()) {
/* 1560 */             EntityLivingBase targetEntityLivingBase = (EntityLivingBase)mop.field_72308_g;
/* 1561 */             int r = 50;
/* 1562 */             int minionCount = 0;
/* 1563 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 50.0D, player.field_70163_u - 15.0D, player.field_70161_v - 50.0D, player.field_70165_t + 50.0D, player.field_70163_u + 15.0D, player.field_70161_v + 50.0D);
/*      */             
/* 1565 */             for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
/* 1566 */               EntityLiving nearbyLivingEntity = (EntityLiving)obj;
/* 1567 */               if ((nearbyLivingEntity.func_70668_bt() == EnumCreatureAttribute.UNDEAD) && 
/* 1568 */                 (PotionEnslaved.isMobEnslavedBy(nearbyLivingEntity, player))) {
/* 1569 */                 minionCount++;
/*      */                 
/* 1571 */                 com.emoniph.witchery.util.EntityUtil.setTarget(nearbyLivingEntity, targetEntityLivingBase);
/*      */               }
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1586 */             if (minionCount > 0) {
/* 1587 */               Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.CRIT, SoundEffect.MOB_ZOMBIE_DEATH, targetEntityLivingBase, 0.5D, 2.0D), TargetPointUtil.from(targetEntityLivingBase, 16.0D));
/*      */               
/* 1589 */               return true;
/*      */             }
/*      */           } else {
/* 1592 */             if ((InfusedBrewEffect.Grave.isActive(player)) && 
/* 1593 */               (InfusedBrewEffect.Grave.tryUseEffect(player, mop))) {
/* 1594 */               Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.MOB_SPELL, SoundEffect.MOB_ZOMBIE_INFECT, mop.field_72308_g, 1.0D, 1.0D), TargetPointUtil.from(mop.field_72308_g, 16.0D));
/*      */               
/* 1596 */               return true;
/*      */             }
/*      */             
/*      */ 
/* 1600 */             Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.NOTE_SNARE, mop.field_72308_g, 1.0D, 1.0D), TargetPointUtil.from(mop.field_72308_g, 16.0D));
/*      */           }
/*      */         }
/*      */         
/*      */         break;
/*      */       case BLOCK: 
/* 1606 */         if (world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Witchery.Blocks.ALLURING_SKULL) {
/* 1607 */           return false;
/*      */         }
/* 1609 */         if (BlockSide.TOP.isEqual(mop.field_72310_e)) {
/* 1610 */           int minionCount = 0;
/* 1611 */           int r = 50;
/* 1612 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 50.0D, player.field_70163_u - 15.0D, player.field_70161_v - 50.0D, player.field_70165_t + 50.0D, player.field_70163_u + 15.0D, player.field_70161_v + 50.0D);
/*      */           
/* 1614 */           for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
/* 1615 */             EntityLiving creature = (EntityLiving)obj;
/* 1616 */             EntityCreature creature2 = (creature instanceof EntityCreature) ? (EntityCreature)creature : null;
/* 1617 */             if ((creature.func_70668_bt() == EnumCreatureAttribute.UNDEAD) && 
/* 1618 */               (PotionEnslaved.isMobEnslavedBy(creature, player))) {
/* 1619 */               minionCount++;
/* 1620 */               creature.func_70624_b(null);
/* 1621 */               creature.func_70604_c(null);
/* 1622 */               if (creature2 != null)
/* 1623 */                 creature2.func_70784_b(null);
/* 1624 */               if ((((creature instanceof EntitySpider)) || (!creature.func_70661_as().func_75492_a(mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 1.0D))) && 
/* 1625 */                 (creature2 != null)) {
/* 1626 */                 creature2.func_70778_a(world.func_72844_a(creature, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 10.0F, true, false, false, true));
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1633 */           if (minionCount > 0) {
/* 1634 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, 0.5D + mop.field_72311_b, mop.field_72312_c + 1, 0.5D + mop.field_72309_d, 1.0D, 1.0D, 16);
/*      */             
/* 1636 */             return true;
/*      */           } }
/* 1638 */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1647 */     SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */     
/* 1649 */     return false;
/*      */   }
/*      */   
/*      */   private boolean placeDoor(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side, Block block) {
/* 1653 */     if (side != 1) {
/* 1654 */       return false;
/*      */     }
/* 1656 */     posY++;
/*      */     
/* 1658 */     if ((player.func_82247_a(posX, posY, posZ, side, itemstack)) && (player.func_82247_a(posX, posY + 1, posZ, side, itemstack))) {
/* 1659 */       if (!block.func_149742_c(world, posX, posY, posZ)) {
/* 1660 */         return false;
/*      */       }
/* 1662 */       int i1 = MathHelper.func_76128_c((player.field_70177_z + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;
/* 1663 */       net.minecraft.item.ItemDoor.func_150924_a(world, posX, posY, posZ, i1, block);
/*      */       
/* 1665 */       if ((!world.field_72995_K) && (this.itemDoorRowan.isMatch(itemstack))) {
/* 1666 */         ItemStack keyStack = Witchery.Items.GENERIC.itemDoorKey.createStack();
/* 1667 */         if (!keyStack.func_77942_o()) {
/* 1668 */           keyStack.func_77982_d(new NBTTagCompound());
/*      */         }
/* 1670 */         NBTTagCompound nbtTag = keyStack.func_77978_p();
/* 1671 */         nbtTag.func_74768_a("doorX", posX);
/* 1672 */         nbtTag.func_74768_a("doorY", posY);
/* 1673 */         nbtTag.func_74768_a("doorZ", posZ);
/* 1674 */         nbtTag.func_74768_a("doorD", world.field_73011_w.field_76574_g);
/* 1675 */         nbtTag.func_74778_a("doorDN", world.field_73011_w.func_80007_l());
/* 1676 */         world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u + 0.5D, player.field_70161_v, keyStack));
/*      */       }
/*      */       
/* 1679 */       itemstack.field_77994_a -= 1;
/*      */       
/* 1681 */       return true;
/*      */     }
/*      */     
/* 1684 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public static void setBlockToClay(World world, int x, int y, int z)
/*      */   {
/* 1690 */     Block block = world.func_147439_a(x, y, z);
/* 1691 */     Block blockAbove = world.func_147439_a(x, y + 1, z);
/* 1692 */     if ((block == Blocks.field_150346_d) && ((blockAbove == Blocks.field_150355_j) || (blockAbove == Blocks.field_150358_i))) {
/* 1693 */       world.func_147449_b(x, y, z, Blocks.field_150435_aG);
/* 1694 */       if (!world.field_72995_K) {
/* 1695 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, 1.5D + y, 0.5D + z, 1.0D, 1.0D, 16);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean useAnnointingPaste(ItemStack stack, EntityPlayer player, World world, int x, int y, int z)
/*      */   {
/* 1702 */     if (!world.field_72995_K) {
/* 1703 */       Block block = world.func_147439_a(x, y, z);
/* 1704 */       int meta = world.func_72805_g(x, y, z);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1714 */       if (block == Blocks.field_150383_bp)
/*      */       {
/*      */ 
/*      */ 
/* 1718 */         world.func_147449_b(x, y, z, Witchery.Blocks.CAULDRON);
/* 1719 */         stack.field_77994_a -= 1;
/* 1720 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, x, y, z, 1.0D, 1.0D, 16);
/* 1721 */         ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_LEVELUP, world, x, y, z, 1.0D, 1.0D, 16);
/*      */       }
/*      */     }
/* 1724 */     return true;
/*      */   }
/*      */   
/*      */   private boolean useMutandis(boolean extremis, ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ) {
/* 1728 */     if (!world.field_72995_K)
/*      */     {
/* 1730 */       Block block = world.func_147439_a(posX, posY, posZ);
/* 1731 */       Block blockAbove = world.func_147439_a(posX, posY + 1, posZ);
/*      */       
/* 1733 */       if ((extremis) && ((block == Blocks.field_150349_c) || (block == Blocks.field_150391_bh))) {
/* 1734 */         if (world.field_73012_v.nextInt(2) == 0) {
/* 1735 */           world.func_147449_b(posX, posY, posZ, block == Blocks.field_150349_c ? Blocks.field_150391_bh : Blocks.field_150349_c);
/*      */         }
/*      */         
/* 1738 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY + 1, posZ, 1.0D, 1.0D, 16);
/*      */         
/* 1740 */         itemstack.field_77994_a -= 1;
/* 1741 */       } else if ((extremis) && (block == Blocks.field_150346_d) && ((blockAbove == Blocks.field_150355_j) || (blockAbove == Blocks.field_150358_i))) {
/* 1742 */         if (world.field_73012_v.nextInt(2) == 0) {
/* 1743 */           setBlockToClay(world, posX, posY, posZ);
/* 1744 */           setBlockToClay(world, posX + 1, posY, posZ);
/* 1745 */           setBlockToClay(world, posX - 1, posY, posZ);
/* 1746 */           setBlockToClay(world, posX, posY, posZ + 1);
/* 1747 */           setBlockToClay(world, posX, posY, posZ - 1);
/*      */         } else {
/* 1749 */           ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY + 1, posZ, 1.0D, 1.0D, 16);
/*      */         }
/* 1751 */         itemstack.field_77994_a -= 1;
/*      */       } else {
/* 1753 */         int metadata = world.func_72805_g(posX, posY, posZ);
/*      */         
/*      */         ArrayList<MutableBlock> list;
/*      */         ArrayList<MutableBlock> list;
/* 1757 */         if ((block == Blocks.field_150457_bL) && (metadata > 0)) {
/* 1758 */           MutableBlock[] blocks = { new MutableBlock(Blocks.field_150457_bL, 1), new MutableBlock(Blocks.field_150457_bL, 2), new MutableBlock(Blocks.field_150457_bL, 3), new MutableBlock(Blocks.field_150457_bL, 4), new MutableBlock(Blocks.field_150457_bL, 5), new MutableBlock(Blocks.field_150457_bL, 6), new MutableBlock(Blocks.field_150457_bL, 7), new MutableBlock(Blocks.field_150457_bL, 8), new MutableBlock(Blocks.field_150457_bL, 9), new MutableBlock(Blocks.field_150457_bL, 10), new MutableBlock(Blocks.field_150457_bL, 11) };
/*      */           
/*      */ 
/*      */ 
/* 1762 */           list = new ArrayList(Arrays.asList(blocks));
/*      */         } else {
/* 1764 */           MutableBlock[] blocks = { new MutableBlock(Blocks.field_150345_g, 0), new MutableBlock(Blocks.field_150345_g, 1), new MutableBlock(Blocks.field_150345_g, 2), new MutableBlock(Blocks.field_150345_g, 3), new MutableBlock(Blocks.field_150345_g, 4), new MutableBlock(Blocks.field_150345_g, 5), new MutableBlock(Witchery.Blocks.SAPLING, 0), new MutableBlock(Witchery.Blocks.SAPLING, 1), new MutableBlock(Witchery.Blocks.SAPLING, 2), new MutableBlock(Witchery.Blocks.EMBER_MOSS, 0), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150392_bi), new MutableBlock(Blocks.field_150338_P), new MutableBlock(Blocks.field_150337_Q), new MutableBlock(Blocks.field_150328_O, 0), new MutableBlock(Blocks.field_150327_N), new MutableBlock(Witchery.Blocks.SPANISH_MOSS, 1) };
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1771 */           list = new ArrayList(Arrays.asList(blocks));
/*      */           
/* 1773 */           for (String extra : Config.instance().mutandisExtras) {
/*      */             try {
/* 1775 */               list.add(new MutableBlock(extra));
/*      */             }
/*      */             catch (Throwable ex) {}
/*      */           }
/*      */           
/* 1780 */           if (extremis) {
/* 1781 */             MutableBlock[] extremisBlocks = { new MutableBlock(Blocks.field_150459_bM, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150469_bN, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150464_aj, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150436_aH, -1, Math.min(metadata, 7)), new MutableBlock(Witchery.Blocks.CROP_BELLADONNA, -1, Math.min(metadata, Witchery.Blocks.CROP_BELLADONNA.getNumGrowthStages())), new MutableBlock(Witchery.Blocks.CROP_MANDRAKE, -1, Math.min(metadata, Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages())), new MutableBlock(Witchery.Blocks.CROP_ARTICHOKE, -1, Math.min(metadata, Witchery.Blocks.CROP_ARTICHOKE.getNumGrowthStages())), new MutableBlock(Blocks.field_150393_bb, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150434_aF), new MutableBlock(Blocks.field_150394_bc, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150388_bm, -1, Math.min(metadata, 3)) };
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1789 */             list.addAll(Arrays.asList(extremisBlocks));
/* 1790 */           } else if (player.field_71093_bK == Config.instance().dimensionDreamID) {
/* 1791 */             MutableBlock[] spiritBlocks = { new MutableBlock(Blocks.field_150388_bm, -1, 3) };
/* 1792 */             list.addAll(Arrays.asList(spiritBlocks));
/*      */           }
/*      */         }
/*      */         
/* 1796 */         MutableBlock mutableBlock = new MutableBlock(block, metadata, 0);
/* 1797 */         int index = list.indexOf(mutableBlock);
/* 1798 */         if (index != -1) {
/* 1799 */           list.remove(index);
/* 1800 */           ((MutableBlock)list.get(world.field_73012_v.nextInt(list.size()))).mutate(world, posX, posY, posZ);
/* 1801 */           ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 1.0D, 1.0D, 16);
/* 1802 */           itemstack.field_77994_a -= 1;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1807 */     return true;
/*      */   }
/*      */   
/*      */   private boolean placeBroom(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
/*      */   {
/* 1812 */     float f = 1.0F;
/* 1813 */     float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * 1.0F;
/* 1814 */     float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * 1.0F;
/* 1815 */     double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * 1.0D;
/* 1816 */     double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * 1.0D + 1.62D - player.field_70129_M;
/* 1817 */     double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * 1.0D;
/* 1818 */     Vec3 vec3 = Vec3.func_72443_a(d0, d1, d2);
/* 1819 */     float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
/* 1820 */     float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
/* 1821 */     float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
/* 1822 */     float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
/* 1823 */     float f7 = f4 * f5;
/* 1824 */     float f8 = f3 * f5;
/* 1825 */     double d3 = 5.0D;
/* 1826 */     Vec3 vec31 = vec3.func_72441_c(f7 * 5.0D, f6 * 5.0D, f8 * 5.0D);
/* 1827 */     MovingObjectPosition movingobjectposition = world.func_72901_a(vec3, vec31, true);
/*      */     
/* 1829 */     if (movingobjectposition == null) {
/* 1830 */       return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/*      */     }
/* 1832 */     Vec3 vec32 = player.func_70676_i(1.0F);
/* 1833 */     boolean flag = false;
/* 1834 */     float f9 = 1.0F;
/* 1835 */     List list = world.func_72839_b(player, player.field_70121_D.func_72321_a(vec32.field_72450_a * 5.0D, vec32.field_72448_b * 5.0D, vec32.field_72449_c * 5.0D).func_72314_b(1.0D, 1.0D, 1.0D));
/*      */     
/*      */ 
/*      */ 
/* 1839 */     for (int i = 0; i < list.size(); i++) {
/* 1840 */       Entity entity = (Entity)list.get(i);
/*      */       
/* 1842 */       if (entity.func_70067_L()) {
/* 1843 */         float f10 = entity.func_70111_Y();
/* 1844 */         AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f10, f10, f10);
/*      */         
/* 1846 */         if (axisalignedbb.func_72318_a(vec3)) {
/* 1847 */           flag = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1852 */     if (flag) {
/* 1853 */       return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/*      */     }
/* 1855 */     if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 1856 */       i = movingobjectposition.field_72311_b;
/* 1857 */       int j = movingobjectposition.field_72312_c;
/* 1858 */       int k = movingobjectposition.field_72309_d;
/*      */       
/* 1860 */       if (world.func_147439_a(i, j, k) == Blocks.field_150433_aE) {
/* 1861 */         j--;
/*      */       }
/*      */       
/* 1864 */       EntityBroom broomEntity = new EntityBroom(world, i + 0.5F, j + 1.0F, k + 0.5F);
/* 1865 */       if (itemstack.func_82837_s()) {
/* 1866 */         broomEntity.setCustomNameTag(itemstack.func_82833_r());
/*      */       }
/* 1868 */       setBroomEntityColor(broomEntity, itemstack);
/*      */       
/* 1870 */       broomEntity.field_70177_z = player.field_70177_z;
/*      */       
/* 1872 */       if (!world.func_72945_a(broomEntity, broomEntity.field_70121_D.func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty()) {
/* 1873 */         super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/*      */       }
/*      */       
/* 1876 */       broomEntity.field_70177_z = (((MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3) - 1) * 90);
/*      */       
/* 1878 */       if (!world.field_72995_K) {
/* 1879 */         world.func_72838_d(broomEntity);
/* 1880 */         int l = MathHelper.func_76141_d(broomEntity.field_70177_z * 256.0F / 360.0F);
/*      */         
/*      */ 
/* 1883 */         Witchery.packetPipeline.sendToAllAround(new S14PacketEntity.S17PacketEntityLookMove(broomEntity.func_145782_y(), (byte)0, (byte)0, (byte)0, (byte)Math.max(Math.min(l, 255), 0), (byte)0), world, TargetPointUtil.from(broomEntity, 128.0D));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1893 */       if (!player.field_71075_bZ.field_75098_d) {
/* 1894 */         itemstack.field_77994_a -= 1;
/*      */       }
/*      */     }
/*      */     
/* 1898 */     return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
/*      */   }
/*      */   
/*      */ 
/*      */   private void setBroomEntityColor(EntityBroom broomEntity, ItemStack itemstack)
/*      */   {
/* 1904 */     broomEntity.setBrushColor(getBroomItemColor(itemstack));
/*      */   }
/*      */   
/*      */   public void setBroomItemColor(ItemStack itemstack, int brushColor) {
/* 1908 */     if ((brushColor >= 0) && (brushColor <= 15)) {
/* 1909 */       if (!itemstack.func_77942_o()) {
/* 1910 */         itemstack.func_77982_d(new NBTTagCompound());
/*      */       }
/* 1912 */       NBTTagCompound nbtTag = itemstack.func_77978_p();
/* 1913 */       nbtTag.func_74774_a("BrushColor", Byte.valueOf((byte)brushColor).byteValue());
/*      */     }
/*      */   }
/*      */   
/*      */   public int getBroomItemColor(ItemStack stack) {
/* 1918 */     NBTTagCompound nbtTag = stack.func_77978_p();
/* 1919 */     if ((nbtTag != null) && (nbtTag.func_74764_b("BrushColor"))) {
/* 1920 */       return nbtTag.func_74771_c("BrushColor") & 0xF;
/*      */     }
/* 1922 */     return -1;
/*      */   }
/*      */   
/*      */   private boolean placeBlock(Block spawnBlock, ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
/*      */   {
/* 1927 */     Block block = world.func_147439_a(posX, posY, posZ);
/*      */     
/* 1929 */     if ((block == Blocks.field_150433_aE) && ((world.func_72805_g(posX, posY, posZ) & 0x7) < 1)) {
/* 1930 */       side = 1;
/* 1931 */     } else if ((block != Blocks.field_150395_bd) && (block != Blocks.field_150329_H) && (block != Blocks.field_150330_I)) {
/* 1932 */       if (side == 0) {
/* 1933 */         posY--;
/* 1934 */       } else if (side == 1) {
/* 1935 */         posY++;
/* 1936 */       } else if (side == 2) {
/* 1937 */         posZ--;
/* 1938 */       } else if (side == 3) {
/* 1939 */         posZ++;
/* 1940 */       } else if (side == 4) {
/* 1941 */         posX--;
/* 1942 */       } else if (side == 5) {
/* 1943 */         posX++;
/*      */       }
/*      */     }
/*      */     
/* 1947 */     if (!player.func_82247_a(posX, posY, posZ, side, itemstack))
/* 1948 */       return false;
/* 1949 */     if (itemstack.field_77994_a == 0) {
/* 1950 */       return false;
/*      */     }
/* 1952 */     if (world.func_147472_a(spawnBlock, posX, posY, posZ, false, side, (Entity)null, itemstack)) {
/* 1953 */       int j1 = spawnBlock.func_149660_a(world, posX, posY, posZ, side, par8, par9, par10, 0);
/*      */       
/* 1955 */       if (world.func_147465_d(posX, posY, posZ, spawnBlock, j1, 3)) {
/* 1956 */         if (world.func_147439_a(posX, posY, posZ) == spawnBlock) {
/* 1957 */           spawnBlock.func_149689_a(world, posX, posY, posZ, player, itemstack);
/* 1958 */           spawnBlock.func_149714_e(world, posX, posY, posZ, j1);
/* 1959 */           if (spawnBlock == Witchery.Blocks.CHALICE) {
/* 1960 */             BlockChalice.TileEntityChalice tileEntity = (BlockChalice.TileEntityChalice)world.func_147438_o(posX, posY, posZ);
/* 1961 */             if (tileEntity != null) {
/* 1962 */               tileEntity.setFilled(this.itemChaliceFull.isMatch(itemstack));
/*      */             }
/*      */           }
/*      */         }
/*      */         
/* 1967 */         world.func_72908_a(posX + 0.5F, posY + 0.5F, posZ + 0.5F, block.field_149762_H.func_150496_b(), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F);
/*      */         
/* 1969 */         itemstack.field_77994_a -= 1;
/*      */       }
/*      */     }
/*      */     
/* 1973 */     return true;
/*      */   }
/*      */   
/*      */   private boolean placeDreamCatcher(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
/*      */   {
/* 1978 */     if (side == 0)
/* 1979 */       return false;
/* 1980 */     if (!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) {
/* 1981 */       return false;
/*      */     }
/* 1983 */     if (side == 1) {
/* 1984 */       posY++;
/* 1985 */     } else if (side == 2) {
/* 1986 */       posZ--;
/* 1987 */     } else if (side == 3) {
/* 1988 */       posZ++;
/* 1989 */     } else if (side == 4) {
/* 1990 */       posX--;
/* 1991 */     } else if (side == 5) {
/* 1992 */       posX++;
/*      */     }
/*      */     
/* 1995 */     if (!player.func_82247_a(posX, posY, posZ, side, itemstack))
/* 1996 */       return false;
/* 1997 */     if (!Witchery.Blocks.DREAM_CATCHER.func_149742_c(world, posX, posY, posZ))
/* 1998 */       return false;
/* 1999 */     if (world.field_72995_K) {
/* 2000 */       return true;
/*      */     }
/* 2002 */     if (side != 1) {
/* 2003 */       world.func_147465_d(posX, posY, posZ, Witchery.Blocks.DREAM_CATCHER, side, 3);
/* 2004 */       itemstack.field_77994_a -= 1;
/* 2005 */       BlockDreamCatcher.TileEntityDreamCatcher tileEntity = (BlockDreamCatcher.TileEntityDreamCatcher)world.func_147438_o(posX, posY, posZ);
/* 2006 */       if (tileEntity != null) {
/* 2007 */         DreamWeave weave = (DreamWeave)this.subItems.get(itemstack.func_77960_j());
/* 2008 */         weave.setEffect(tileEntity);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2013 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean isWaystoneBound(ItemStack stack)
/*      */   {
/* 2018 */     NBTTagCompound tag = stack.func_77978_p();
/* 2019 */     return (tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"));
/*      */   }
/*      */   
/* 2022 */   public static int getWaystoneDimension(ItemStack stack) { NBTTagCompound tag = stack.func_77978_p();
/* 2023 */     if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
/* 2024 */       int newX = tag.func_74762_e("PosX");
/* 2025 */       int newY = tag.func_74762_e("PosY");
/* 2026 */       int newZ = tag.func_74762_e("PosZ");
/* 2027 */       int newD = tag.func_74762_e("PosD");
/*      */       
/* 2029 */       return newD;
/*      */     }
/* 2031 */     return 0;
/*      */   }
/*      */   
/*      */   private boolean isRestrictedTeleportTarget(int source, int target) {
/* 2035 */     if (source == target) {
/* 2036 */       return false;
/*      */     }
/*      */     
/* 2039 */     return (source == Config.instance().dimensionDreamID) || (source == Config.instance().dimensionMirrorID) || (target == Config.instance().dimensionDreamID) || (target == Config.instance().dimensionMirrorID);
/*      */   }
/*      */   
/*      */   public boolean teleportToLocation(World world, ItemStack itemstack, Entity entity, int radius, boolean presetPosition)
/*      */   {
/* 2044 */     NBTTagCompound tag = itemstack.func_77978_p();
/* 2045 */     if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
/* 2046 */       int newX = tag.func_74762_e("PosX") - radius + world.field_73012_v.nextInt(radius * 2 + 1);
/* 2047 */       int newY = tag.func_74762_e("PosY");
/* 2048 */       int newZ = tag.func_74762_e("PosZ") - radius + world.field_73012_v.nextInt(radius * 2 + 1);
/* 2049 */       int newD = tag.func_74762_e("PosD");
/*      */       
/* 2051 */       if (!isRestrictedTeleportTarget(entity.field_71093_bK, newD)) {
/* 2052 */         teleportToLocation(world, newX, newY, newZ, newD, entity, presetPosition);
/* 2053 */         return true;
/*      */       }
/* 2055 */     } else if (tag != null) {
/* 2056 */       EntityLivingBase target = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, itemstack, Integer.valueOf(1));
/* 2057 */       if ((entity != null) && (target != null) && 
/* 2058 */         (!isRestrictedTeleportTarget(entity.field_71093_bK, target.field_71093_bK))) {
/* 2059 */         teleportToLocation(world, target.field_70165_t, target.field_70163_u, target.field_70161_v, target.field_71093_bK, entity, presetPosition);
/* 2060 */         return true;
/*      */       }
/*      */     }
/*      */     
/* 2064 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean teleportToLocationSafely(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition) {
/* 2068 */     World targetWorld = MinecraftServer.func_71276_C().func_71218_a(dimension);
/* 2069 */     int x = MathHelper.func_76128_c(posX);
/* 2070 */     int y = MathHelper.func_76128_c(posY);
/* 2071 */     int z = MathHelper.func_76128_c(posZ);
/* 2072 */     for (int i = 0; i < 16; i++) {
/* 2073 */       int dy = y + i;
/* 2074 */       if ((dy < 250) && (!BlockUtil.isReplaceableBlock(targetWorld, x, dy, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 1, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 2, z)))
/*      */       {
/*      */ 
/* 2077 */         teleportToLocation(world, x, dy + 1, z, dimension, entity, presetPosition);
/* 2078 */         return true;
/*      */       }
/*      */       
/* 2081 */       dy = y - i;
/* 2082 */       if ((i > 0) && (dy > 1) && (!BlockUtil.isReplaceableBlock(targetWorld, x, dy, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 1, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 2, z)))
/*      */       {
/*      */ 
/* 2085 */         teleportToLocation(world, x, dy + 1, z, dimension, entity, presetPosition);
/* 2086 */         return true;
/*      */       }
/*      */     }
/*      */     
/* 2090 */     return false;
/*      */   }
/*      */   
/*      */   public static void teleportToLocation(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition) {
/* 2094 */     teleportToLocation(world, posX, posY, posZ, dimension, entity, presetPosition, ParticleEffect.PORTAL, SoundEffect.MOB_ENDERMEN_PORTAL);
/*      */   }
/*      */   
/*      */   public static void teleportToLocation(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition, ParticleEffect particle, SoundEffect sound) {
/* 2098 */     boolean isVampire = CreatureUtil.isVampire(entity);
/* 2099 */     if (isVampire) {
/* 2100 */       Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
/*      */     }
/*      */     else {
/* 2103 */       Witchery.packetPipeline.sendToAllAround(new PacketParticles(particle, sound, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
/*      */     }
/*      */     
/*      */ 
/* 2107 */     if ((entity instanceof EntityPlayer)) {
/* 2108 */       EntityPlayer player = (EntityPlayer)entity;
/* 2109 */       if (entity.field_71093_bK != dimension)
/*      */       {
/* 2111 */         if (presetPosition) {
/* 2112 */           player.func_70107_b(posX, posY, posZ);
/*      */         }
/* 2114 */         travelToDimension(player, dimension);
/*      */       }
/* 2116 */       player.func_70634_a(posX, posY, posZ);
/* 2117 */     } else if ((entity instanceof EntityLiving)) {
/* 2118 */       if (entity.field_71093_bK != dimension) {
/* 2119 */         travelToDimension(entity, dimension, posX, posY, posZ);
/*      */       } else {
/* 2121 */         entity.func_70012_b(posX, posY, posZ, entity.field_70177_z, entity.field_70125_A);
/*      */       }
/*      */     }
/* 2124 */     else if (entity.field_71093_bK != dimension) {
/* 2125 */       travelToDimension(entity, dimension, posX, posY, posZ);
/*      */     } else {
/* 2127 */       entity.func_70012_b(posX, posY, posZ, entity.field_70177_z, entity.field_70125_A);
/*      */     }
/*      */     
/*      */ 
/* 2131 */     if (isVampire) {
/* 2132 */       Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
/*      */     }
/*      */     else {
/* 2135 */       Witchery.packetPipeline.sendToAllAround(new PacketParticles(particle, sound, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static void travelToDimension(EntityPlayer player, int dimension)
/*      */   {
/* 2142 */     if ((!player.field_70170_p.field_72995_K & player instanceof EntityPlayerMP)) {
/* 2143 */       MinecraftServer server = MinecraftServer.func_71276_C();
/* 2144 */       WorldServer newWorldServer = server.func_71218_a(dimension);
/*      */       
/*      */ 
/*      */ 
/* 2148 */       server.func_71203_ab().transferPlayerToDimension((EntityPlayerMP)player, dimension, new Teleporter2(newWorldServer));
/*      */     }
/*      */   }
/*      */   
/*      */   private static Entity travelToDimension(Entity thisE, int par1, double posX, double posY, double posZ) {
/* 2153 */     if ((!thisE.field_70170_p.field_72995_K) && (!thisE.field_70128_L)) {
/* 2154 */       thisE.field_70170_p.field_72984_F.func_76320_a("changeDimension");
/* 2155 */       MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
/* 2156 */       int j = thisE.field_71093_bK;
/* 2157 */       WorldServer worldserver = minecraftserver.func_71218_a(j);
/* 2158 */       WorldServer worldserver1 = minecraftserver.func_71218_a(par1);
/* 2159 */       thisE.field_71093_bK = par1;
/*      */       
/* 2161 */       if ((j == 1) && (par1 == 1)) {
/* 2162 */         worldserver1 = minecraftserver.func_71218_a(0);
/* 2163 */         thisE.field_71093_bK = 0;
/*      */       }
/*      */       
/* 2166 */       thisE.field_70170_p.func_72900_e(thisE);
/* 2167 */       thisE.field_70128_L = false;
/* 2168 */       thisE.field_70170_p.field_72984_F.func_76320_a("reposition");
/* 2169 */       minecraftserver.func_71203_ab().transferEntityToWorld(thisE, j, worldserver, worldserver1, new Teleporter2(worldserver1));
/* 2170 */       thisE.field_70170_p.field_72984_F.func_76318_c("reloading");
/* 2171 */       Entity entity = EntityList.func_75620_a(EntityList.func_75621_b(thisE), worldserver1);
/*      */       
/* 2173 */       if (entity != null) {
/* 2174 */         entity.func_82141_a(thisE, true);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2186 */         entity.func_70012_b(posX, posY, posZ, entity.field_70177_z, entity.field_70125_A);
/*      */         
/*      */ 
/* 2189 */         worldserver1.func_72838_d(entity);
/*      */       }
/*      */       
/* 2192 */       thisE.field_70128_L = true;
/* 2193 */       thisE.field_70170_p.field_72984_F.func_76319_b();
/* 2194 */       worldserver.func_82742_i();
/* 2195 */       worldserver1.func_82742_i();
/* 2196 */       thisE.field_70170_p.field_72984_F.func_76319_b();
/*      */       
/* 2198 */       return entity;
/*      */     }
/*      */     
/* 2201 */     return null;
/*      */   }
/*      */   
/*      */   private static class Teleporter2 extends Teleporter {
/*      */     public Teleporter2(WorldServer server) {
/* 2206 */       super();
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean func_85188_a(Entity par1Entity)
/*      */     {
/* 2212 */       return false;
/*      */     }
/*      */     
/*      */     public boolean func_77184_b(Entity par1Entity, double par2, double par4, double par6, float par8)
/*      */     {
/* 2217 */       return false;
/*      */     }
/*      */     
/*      */     public void func_77185_a(Entity par1Entity, double par2, double par4, double par6, float par8) {}
/*      */     
/*      */     public void func_85189_a(long par1) {}
/*      */   }
/*      */   
/*      */   public static class SubItem {
/*      */     public final int damageValue;
/*      */     private final String unlocalizedName;
/*      */     private final int rarity;
/*      */     
/*      */     private static <T extends SubItem> T register(T subItem, ArrayList<SubItem> subItems) {
/* 2231 */       assert (subItems.size() == subItem.damageValue) : "Misalignement with subItem registration";
/* 2232 */       while (subItems.size() <= subItem.damageValue) {
/* 2233 */         subItems.add(null);
/*      */       }
/* 2235 */       subItems.set(subItem.damageValue, subItem);
/* 2236 */       return subItem;
/*      */     }
/*      */     
/*      */     public boolean isSolidifier() {
/* 2240 */       return false;
/*      */     }
/*      */     
/*      */     public boolean isInfused() {
/* 2244 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */     private final boolean showInCreativeTab;
/*      */     
/*      */     protected boolean enchanted;
/*      */     
/*      */     protected boolean potion;
/*      */     
/*      */     @SideOnly(Side.CLIENT)
/*      */     private IIcon icon;
/*      */     
/*      */     public SubItem(int damageValue, String unlocalizedName)
/*      */     {
/* 2259 */       this(damageValue, unlocalizedName, 0, true);
/*      */     }
/*      */     
/*      */     private SubItem(int damageValue, String unlocalizedName, int rarity) {
/* 2263 */       this(damageValue, unlocalizedName, rarity, true);
/*      */     }
/*      */     
/*      */     private SubItem(int damageValue, String unlocalizedName, int rarity, boolean showInCreativeTab) {
/* 2267 */       this.damageValue = damageValue;
/* 2268 */       this.unlocalizedName = unlocalizedName;
/* 2269 */       this.rarity = rarity;
/* 2270 */       this.showInCreativeTab = showInCreativeTab;
/* 2271 */       this.enchanted = false;
/* 2272 */       this.potion = false;
/*      */     }
/*      */     
/*      */     @SideOnly(Side.CLIENT)
/*      */     private void registerIcon(IIconRegister iconRegister, ItemGeneral itemIngredient) {
/* 2277 */       this.icon = iconRegister.func_94245_a(itemIngredient.func_111208_A() + "." + this.unlocalizedName);
/*      */     }
/*      */     
/*      */     public boolean isMatch(ItemStack itemstack) {
/* 2281 */       return (itemstack != null) && (Witchery.Items.GENERIC == itemstack.func_77973_b()) && (itemstack.func_77960_j() == this.damageValue);
/*      */     }
/*      */     
/*      */     public ItemStack createStack(int stackSize) {
/* 2285 */       return new ItemStack(Witchery.Items.GENERIC, stackSize, this.damageValue);
/*      */     }
/*      */     
/*      */     public ItemStack createStack() {
/* 2289 */       return createStack(1);
/*      */     }
/*      */     
/*      */     public boolean isItemInInventory(InventoryPlayer inventory) {
/* 2293 */       return getItemSlotFromInventory(inventory) != -1;
/*      */     }
/*      */     
/*      */     public int getItemSlotFromInventory(InventoryPlayer inventory) {
/* 2297 */       for (int k = 0; k < inventory.field_70462_a.length; k++) {
/* 2298 */         if ((inventory.field_70462_a[k] != null) && (inventory.field_70462_a[k].func_77973_b() == Witchery.Items.GENERIC) && (inventory.field_70462_a[k].func_77960_j() == this.damageValue))
/*      */         {
/* 2300 */           return k;
/*      */         }
/*      */       }
/*      */       
/* 2304 */       return -1;
/*      */     }
/*      */     
/*      */     public boolean consumeItemFromInventory(InventoryPlayer inventory) {
/* 2308 */       int j = getItemSlotFromInventory(inventory);
/* 2309 */       if (j < 0) {
/* 2310 */         return false;
/*      */       }
/* 2312 */       if (--inventory.field_70462_a[j].field_77994_a <= 0) {
/* 2313 */         inventory.field_70462_a[j] = null;
/*      */       }
/*      */       
/* 2316 */       return true;
/*      */     }
/*      */     
/*      */     public boolean isEnchanted()
/*      */     {
/* 2321 */       return (this.enchanted) || (this.potion);
/*      */     }
/*      */     
/*      */     public SubItem setEnchanted(boolean enchanted) {
/* 2325 */       this.enchanted = enchanted;
/* 2326 */       return this;
/*      */     }
/*      */     
/*      */     public SubItem setPotion(boolean potion) {
/* 2330 */       this.potion = potion;
/* 2331 */       return this;
/*      */     }
/*      */     
/*      */     public boolean isPotion() {
/* 2335 */       return this.potion;
/*      */     }
/*      */     
/*      */     public BrewItemKey getBrewItemKey() {
/* 2339 */       return new BrewItemKey(Witchery.Items.GENERIC, this.damageValue);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class BoltType extends ItemGeneral.SubItem {
/*      */     private BoltType(int damageValue, String unlocalizedName) {
/* 2345 */       super(unlocalizedName);
/*      */     }
/*      */     
/*      */     public static BoltType getBolt(ItemStack stack) {
/* 2349 */       if ((stack != null) && (stack.func_77973_b() == Witchery.Items.GENERIC)) {
/* 2350 */         ItemGeneral.SubItem item = (ItemGeneral.SubItem)Witchery.Items.GENERIC.subItems.get(stack.func_77960_j());
/* 2351 */         if ((item instanceof BoltType)) {
/* 2352 */           return (BoltType)item;
/*      */         }
/*      */       }
/* 2355 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Edible extends ItemGeneral.SubItem {
/*      */     public final boolean eatAnyTime;
/*      */     private final int healAmount;
/*      */     private final float saturationModifier;
/*      */     private final boolean wolfsFavorite;
/*      */     
/*      */     private Edible(int damageValue, String unlocalizedName, int healAmount, float saturationModifier, boolean wolfsFavorite) {
/* 2366 */       this(damageValue, unlocalizedName, healAmount, saturationModifier, wolfsFavorite, false);
/*      */     }
/*      */     
/*      */     private Edible(int damageValue, String unlocalizedName, int healAmount, float saturationModifier, boolean wolfsFavorite, boolean eatAnyTime) {
/* 2370 */       super(unlocalizedName);
/* 2371 */       this.healAmount = healAmount;
/* 2372 */       this.saturationModifier = saturationModifier;
/* 2373 */       this.wolfsFavorite = wolfsFavorite;
/* 2374 */       this.eatAnyTime = eatAnyTime;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Brew extends ItemGeneral.SubItem {
/*      */     public static enum BrewResult {
/* 2380 */       DROP_ITEM, 
/* 2381 */       SHOW_EFFECT, 
/* 2382 */       HIDE_EFFECT;
/*      */       
/*      */       private BrewResult() {} }
/*      */     
/* 2386 */     public Brew(int damageValue, String unlocalizedName) { super(unlocalizedName);
/* 2387 */       setPotion(true);
/*      */     }
/*      */     
/*      */     public BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
/* 2391 */       return BrewResult.SHOW_EFFECT;
/*      */     }
/*      */     
/*      */     protected static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block) {
/* 2395 */       return setBlockIfNotSolid(world, x, y, z, block, 0);
/*      */     }
/*      */     
/*      */     protected static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block, int metadata) {
/* 2399 */       if ((!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) || ((block == Blocks.field_150321_G) && (BlockUtil.getBlock(world, x, y, z) == Blocks.field_150433_aE))) {
/* 2400 */         BlockUtil.setBlock(world, x, y, z, block, metadata, 3);
/*      */         
/* 2402 */         ParticleEffect.EXPLODE.send(SoundEffect.NONE, world, 0.5D + x, 0.5D + y, 0.5D + z, 1.0D, 1.0D, 16);
/*      */         
/* 2404 */         return true;
/*      */       }
/* 2406 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Drinkable extends ItemGeneral.SubItem
/*      */   {
/*      */     protected PotionEffect[] effects;
/*      */     protected EnumAction useAction;
/*      */     
/*      */     protected Drinkable(int damageValue, String unlocalizedName, int rarity, PotionEffect... effects) {
/* 2416 */       this(damageValue, unlocalizedName, rarity, EnumAction.drink, effects);
/*      */     }
/*      */     
/*      */     protected Drinkable(int damageValue, String unlocalizedName, int rarity, EnumAction useAction, PotionEffect... effects) {
/* 2420 */       super(unlocalizedName, rarity, null);
/* 2421 */       this.effects = effects;
/* 2422 */       this.useAction = useAction;
/*      */     }
/*      */     
/*      */     public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {}
/*      */   }
/*      */   
/*      */   public static class DreamWeave extends ItemGeneral.SubItem { public final int weaveID;
/*      */     public final int textureOffsetX;
/*      */     public final int textureOffsetY;
/*      */     
/* 2432 */     private static DreamWeave register(DreamWeave subItem, ArrayList<ItemGeneral.SubItem> subItems, ArrayList<DreamWeave> weaves) { weaves.add(ItemGeneral.SubItem.access$000(subItem, subItems));
/* 2433 */       return subItem;
/*      */     }
/*      */     
/*      */ 
/*      */     private final Potion potionDream;
/*      */     
/*      */     private final Potion potionNightmare;
/*      */     
/*      */     private final int duration;
/*      */     
/*      */     private final int amplifier;
/*      */     
/*      */     private DreamWeave(int damageValue, int weaveID, String unlocalizedName, Potion potionDream, Potion potionNightmare, int duration, int amplifier, int textureX, int textureY)
/*      */     {
/* 2447 */       super(unlocalizedName, 1, null);
/* 2448 */       this.potionDream = potionDream;
/* 2449 */       this.potionNightmare = potionNightmare;
/* 2450 */       this.duration = duration;
/* 2451 */       this.amplifier = amplifier;
/* 2452 */       this.textureOffsetX = textureX;
/* 2453 */       this.textureOffsetY = textureY;
/* 2454 */       this.weaveID = weaveID;
/*      */     }
/*      */     
/*      */     public void setEffect(BlockDreamCatcher.TileEntityDreamCatcher dreamCatcherEntity) {
/* 2458 */       dreamCatcherEntity.setEffect(this);
/*      */     }
/*      */     
/*      */     public void applyEffect(EntityPlayer player, boolean isDream, boolean isEnhanced) {
/* 2462 */       if (isDream) {
/* 2463 */         player.func_70690_d(new PotionEffect(this.potionDream.func_76396_c(), isEnhanced ? this.duration - 2400 : (isEnhanced) && (this.potionDream.field_76415_H == Potion.field_76443_y.field_76415_H) ? this.duration + 2400 : this.duration, (isEnhanced) && (this.potionDream.field_76415_H != Potion.field_76443_y.field_76415_H) ? this.amplifier + 1 : this.amplifier));
/*      */       } else {
/* 2465 */         player.func_70690_d(new PotionEffect(this.potionNightmare.func_76396_c(), this.duration, isEnhanced ? this.amplifier + 1 : this.amplifier));
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemGeneral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */