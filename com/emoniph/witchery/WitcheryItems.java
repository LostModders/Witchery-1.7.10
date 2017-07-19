/*     */ package com.emoniph.witchery;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.ItemBrew;
/*     */ import com.emoniph.witchery.brewing.ItemBrewBucket;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.item.ItemArthana;
/*     */ import com.emoniph.witchery.item.ItemBiomeNote;
/*     */ import com.emoniph.witchery.item.ItemBoline;
/*     */ import com.emoniph.witchery.item.ItemBook;
/*     */ import com.emoniph.witchery.item.ItemBrewBag;
/*     */ import com.emoniph.witchery.item.ItemBrewEndlessWater;
/*     */ import com.emoniph.witchery.item.ItemBrewFuel;
/*     */ import com.emoniph.witchery.item.ItemBucketSpirit;
/*     */ import com.emoniph.witchery.item.ItemCaneSword;
/*     */ import com.emoniph.witchery.item.ItemChalk;
/*     */ import com.emoniph.witchery.item.ItemCircleTalisman;
/*     */ import com.emoniph.witchery.item.ItemCoffin;
/*     */ import com.emoniph.witchery.item.ItemDeathsClothes;
/*     */ import com.emoniph.witchery.item.ItemDeathsHand;
/*     */ import com.emoniph.witchery.item.ItemDiviner;
/*     */ import com.emoniph.witchery.item.ItemDuplicationStaff;
/*     */ import com.emoniph.witchery.item.ItemEarmuffs;
/*     */ import com.emoniph.witchery.item.ItemEntityLocator;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.item.ItemGlassGoblet;
/*     */ import com.emoniph.witchery.item.ItemGoblinClothes;
/*     */ import com.emoniph.witchery.item.ItemHandBow;
/*     */ import com.emoniph.witchery.item.ItemHornOfTheHunt;
/*     */ import com.emoniph.witchery.item.ItemHunterClothes;
/*     */ import com.emoniph.witchery.item.ItemHuntsmanSpear;
/*     */ import com.emoniph.witchery.item.ItemKobolditePickaxe;
/*     */ import com.emoniph.witchery.item.ItemLeonardsUrn;
/*     */ import com.emoniph.witchery.item.ItemMarkupBook;
/*     */ import com.emoniph.witchery.item.ItemMirror;
/*     */ import com.emoniph.witchery.item.ItemMoonCharm;
/*     */ import com.emoniph.witchery.item.ItemMutator;
/*     */ import com.emoniph.witchery.item.ItemMysticBranch;
/*     */ import com.emoniph.witchery.item.ItemParasyticLouse;
/*     */ import com.emoniph.witchery.item.ItemPolynesiaCharm;
/*     */ import com.emoniph.witchery.item.ItemPoppet;
/*     */ import com.emoniph.witchery.item.ItemPoppetShelfCompass;
/*     */ import com.emoniph.witchery.item.ItemSilverSword;
/*     */ import com.emoniph.witchery.item.ItemSpectralStone;
/*     */ import com.emoniph.witchery.item.ItemStew;
/*     */ import com.emoniph.witchery.item.ItemSunGrenade;
/*     */ import com.emoniph.witchery.item.ItemTaglockKit;
/*     */ import com.emoniph.witchery.item.ItemVampireClothes;
/*     */ import com.emoniph.witchery.item.ItemVanillaPotion;
/*     */ import com.emoniph.witchery.item.ItemWitchHand;
/*     */ import com.emoniph.witchery.item.ItemWitchSeeds;
/*     */ import com.emoniph.witchery.item.ItemWitchSlab;
/*     */ import com.emoniph.witchery.item.ItemWitchesClothes;
/*     */ import com.emoniph.witchery.item.ItemWolfToken;
/*     */ import net.minecraft.block.BlockSlab;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public final class WitcheryItems
/*     */ {
/*  65 */   public final ItemGeneral GENERIC = (ItemGeneral)new ItemGeneral().func_77655_b("witchery:ingredient").func_111206_d("witchery:ingredient");
/*  66 */   public final ItemTaglockKit TAGLOCK_KIT = (ItemTaglockKit)new ItemTaglockKit().func_77655_b("witchery:taglockkit").func_111206_d("witchery:taglockKit");
/*  67 */   public final ItemPoppet POPPET = (ItemPoppet)new ItemPoppet().func_77655_b("witchery:poppet").func_111206_d("witchery:poppet");
/*  68 */   public final ItemVanillaPotion POTIONS = (ItemVanillaPotion)new ItemVanillaPotion().func_77655_b("witchery:potion").func_111206_d("potion");
/*  69 */   public final Item SLAB_SINGLE = new ItemWitchSlab((BlockSlab)Witchery.Blocks.WOOD_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.WOOD_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.WOOD_SLAB_DOUBLE).func_77655_b("witchery:witchwoodslab");
/*  70 */   public final Item SLAB_DOUBLE = new ItemWitchSlab((BlockSlab)Witchery.Blocks.WOOD_SLAB_DOUBLE, (BlockSlab)Witchery.Blocks.WOOD_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.WOOD_SLAB_DOUBLE).func_77655_b("witchery:witchwooddoubleslab");
/*     */   
/*  72 */   public final Item PERPERTUAL_ICE_SLAB_SINGLE = new ItemWitchSlab((BlockSlab)Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE).func_77655_b("witchery:iceslab");
/*  73 */   public final Item PERPERTUAL_ICE_SLAB_DOUBLE = new ItemWitchSlab((BlockSlab)Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE, (BlockSlab)Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE).func_77655_b("witchery:icedoubleslab");
/*     */   
/*  75 */   public final Item SNOW_SLAB_SINGLE = new ItemWitchSlab((BlockSlab)Witchery.Blocks.SNOW_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.SNOW_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.SNOW_SLAB_DOUBLE).func_77655_b("witchery:snowslab");
/*  76 */   public final Item SNOW_SLAB_DOUBLE = new ItemWitchSlab((BlockSlab)Witchery.Blocks.SNOW_SLAB_DOUBLE, (BlockSlab)Witchery.Blocks.SNOW_SLAB_SINGLE, (BlockSlab)Witchery.Blocks.SNOW_SLAB_DOUBLE).func_77655_b("witchery:snowdoubleslab");
/*     */   
/*  78 */   public final Item COFFIN = new ItemCoffin().func_77655_b("witchery:coffin").func_111206_d("witchery:coffin");
/*     */   
/*  80 */   public final Item STEW = new ItemStew(12, 1.0F).func_77655_b("witchery:stew").func_111206_d("witchery:stew");
/*  81 */   public final Item STEW_RAW = new ItemStew(1, 0.1F).func_77655_b("witchery:stewraw").func_111206_d("witchery:stewraw");
/*     */   
/*  83 */   public final Item MIRROR = new ItemMirror().func_77655_b("witchery:mirror").func_111206_d("witchery:mirror");
/*     */   
/*     */ 
/*  86 */   public final Item CHALK_RITUAL = new ItemChalk(Witchery.Blocks.GLYPH_RITUAL).func_77655_b("witchery:chalkritual").func_111206_d("witchery:chalkRitual");
/*  87 */   public final Item CHALK_OTHERWHERE = new ItemChalk(Witchery.Blocks.GLYPH_OTHERWHERE).func_77655_b("witchery:chalkotherwhere").func_111206_d("witchery:chalkOtherwhere");
/*  88 */   public final Item CHALK_INFERNAL = new ItemChalk(Witchery.Blocks.GLYPH_INFERNAL).func_77655_b("witchery:chalkinfernal").func_111206_d("witchery:chalkInfernal");
/*  89 */   public final Item CHALK_GOLDEN = new ItemChalk(Witchery.Blocks.CIRCLE).func_77655_b("witchery:chalkheart").func_111206_d("witchery:chalkHeart");
/*     */   
/*     */ 
/*  92 */   public final Item BOLINE = new ItemBoline().func_77655_b("witchery:boline").func_111206_d("witchery:boline");
/*  93 */   public final Item ARTHANA = new ItemArthana().func_77655_b("witchery:arthana").func_111206_d("witchery:arthana");
/*  94 */   public final Item HUNTSMANS_SPEAR = new ItemHuntsmanSpear().func_77655_b("witchery:huntsmanspear").func_111206_d("witchery:huntsmanspear");
/*  95 */   public final Item CROSSBOW_PISTOL = new ItemHandBow().func_77655_b("witchery:handbow").func_111206_d("witchery:handbow");
/*  96 */   public final ItemDeathsHand DEATH_HAND = (ItemDeathsHand)new ItemDeathsHand().func_77655_b("witchery:deathshand").func_111206_d("witchery:deathshand");
/*     */   
/*  98 */   public final Item SILVER_SWORD = new ItemSilverSword().func_77655_b("witchery:silversword").func_111206_d("witchery:silversword");
/*  99 */   public final ItemCaneSword CANE_SWORD = (ItemCaneSword)new ItemCaneSword().func_77655_b("witchery:canesword").func_111206_d("witchery:canesword");
/*     */   
/*     */ 
/* 102 */   public final Item WITCH_HAND = new ItemWitchHand().func_77655_b("witchery:witchhand").func_111206_d("witchery:witchHand");
/* 103 */   public final Item CIRCLE_TALISMAN = new ItemCircleTalisman().func_77655_b("witchery:circletalisman").func_111206_d("witchery:circleTalisman");
/* 104 */   public final Item MYSTIC_BRANCH = new ItemMysticBranch().func_77655_b("witchery:mysticbranch").func_111206_d("witchery:mysticbranch");
/* 105 */   public final Item DIVINER_WATER = new ItemDiviner(Blocks.field_150355_j).func_77655_b("witchery:divinerwater").func_111206_d("witchery:divinerWater");
/* 106 */   public final Item DIVINER_LAVA = new ItemDiviner(Blocks.field_150353_l).func_77655_b("witchery:divinerlava").func_111206_d("witchery:divinerLava");
/* 107 */   public final Item POLYNESIA_CHARM = new ItemPolynesiaCharm(false).func_77655_b("witchery:polynesiacharm").func_111206_d("witchery:polynesiaCharm");
/* 108 */   public final Item DEVILS_TONGUE_CHARM = new ItemPolynesiaCharm(true).func_77655_b("witchery:devilstonguecharm").func_111206_d("witchery:devilsTongueCharm");
/* 109 */   public final Item MUTATING_SPRIG = new ItemMutator().func_77655_b("witchery:mutator").func_111206_d("witchery:mutator");
/* 110 */   public final Item PARASYTIC_LOUSE = new ItemParasyticLouse().func_77655_b("witchery:louse").func_111206_d("witchery:louse");
/* 111 */   public final Item BREW_BAG = new ItemBrewBag().func_77655_b("witchery:brewbag").func_111206_d("witchery:brewbag");
/* 112 */   public final Item SPECTRAL_STONE = new ItemSpectralStone().func_77655_b("witchery:spectralstone").func_111206_d("witchery:witchery.spectralstone");
/* 113 */   public final Item SHELF_COMPASS = new ItemPoppetShelfCompass().func_77655_b("witchery:shelfcompass").func_111206_d("witchery:shelfcompass");
/* 114 */   public final Item KOBOLDITE_PICKAXE = new ItemKobolditePickaxe().func_77655_b("witchery:kobolditepickaxe").func_111206_d("witchery:kobolditepickaxe");
/* 115 */   public final Item DUP_STAFF = new ItemDuplicationStaff().func_77655_b("witchery:dupstaff").func_111206_d("witchery:dupstaff");
/*     */   
/* 117 */   public final Item BREW = new ItemBrew().func_77655_b("witchery:brewbottle").func_111206_d("witchery:brew_drinkable");
/* 118 */   public final Item BREW_FUEL = new ItemBrewFuel().func_77655_b("witchery:brew.fuel").func_111206_d("witchery:brew_drinkable");
/* 119 */   public final Item BREW_ENDLESS_WATER = new ItemBrewEndlessWater().func_77655_b("witchery:brew.water").func_111206_d("witchery:brew_drinkable");
/*     */   
/* 121 */   public final Item BIOME_BOOK = new ItemBook().func_77655_b("witchery:bookbiomes2").func_111206_d("witchery:biomebook2");
/* 122 */   public final Item BIOME_NOTE = new ItemBiomeNote().func_77655_b("witchery:biomenote").func_111206_d("witchery:biomenote");
/*     */   
/* 124 */   public final Item CAULDRON_BOOK = new ItemMarkupBook(7).func_77655_b("witchery:cauldronbook").func_111206_d("witchery:bookcauldron");
/*     */   
/* 126 */   public final Item LEONARDS_URN = new ItemLeonardsUrn().func_77655_b("witchery:leonardsurn").func_111206_d("witchery:leonardsurn");
/* 127 */   public final Item PLAYER_COMPASS = new ItemEntityLocator().func_77655_b("witchery:playercompass").func_111206_d("witchery:playercompass");
/*     */   
/* 129 */   public final Item MOON_CHARM = new ItemMoonCharm().func_77655_b("witchery:mooncharm").func_111206_d("witchery:mooncharm");
/* 130 */   public final Item HORN_OF_THE_HUNT = new ItemHornOfTheHunt().func_77655_b("witchery:hornofthehunt").func_111206_d("witchery:hornofthehunt");
/* 131 */   public final Item CREATIVE_WOLF_TOKEN = new ItemWolfToken().func_77655_b("witchery:wolftoken").func_111206_d("witchery:wolftoken");
/*     */   
/* 133 */   public final ItemGlassGoblet BLOOD_GOBLET = (ItemGlassGoblet)new ItemGlassGoblet().func_77655_b("witchery:glassgoblet").func_111206_d("witchery:glassgoblet");
/* 134 */   public final Item SUN_GRENADE = new ItemSunGrenade(0).func_77655_b("witchery:sungrenade").func_111206_d("witchery:sungrenade");
/*     */   
/* 136 */   public final Item VAMPIRE_BOOK = new ItemMarkupBook(7, new int[] { 0, 9 })
/*     */   {
/*     */     public void onBookRead(ItemStack stack, World world, EntityPlayer player) {
/* 139 */       ExtendedPlayer.get(player).increaseVampireLevelCap(stack.func_77960_j() + 1);
/*     */     }
/* 136 */   }.func_77655_b("witchery:vampirebook").func_111206_d("witchery:vbook");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 143 */   public final Item DUP_GRENADE = new ItemSunGrenade(1).func_77655_b("witchery:dupgrenade").func_111206_d("witchery:dupgrenade");
/*     */   
/*     */ 
/* 146 */   public final ItemWitchesClothes WITCH_HAT = (ItemWitchesClothes)new ItemWitchesClothes(0).func_77655_b("witchery:witchhat").func_111206_d("witchery:witchesHat");
/* 147 */   public final ItemWitchesClothes BABAS_HAT = (ItemWitchesClothes)new ItemWitchesClothes(0).func_77655_b("witchery:babashat").func_111206_d("witchery:babasHat");
/* 148 */   public final ItemWitchesClothes WITCH_ROBES = (ItemWitchesClothes)new ItemWitchesClothes(1).func_77655_b("witchery:witchrobe").func_111206_d("witchery:witchesRobes");
/* 149 */   public final ItemWitchesClothes NECROMANCERS_ROBES = (ItemWitchesClothes)new ItemWitchesClothes(1).func_77655_b("witchery:necromancerrobe").func_111206_d("witchery:necromancerRobes");
/* 150 */   public final ItemWitchesClothes BITING_BELT = (ItemWitchesClothes)new ItemWitchesClothes(2).func_77655_b("witchery:bitingbelt").func_111206_d("witchery:bitingBelt");
/* 151 */   public final ItemWitchesClothes BARK_BELT = (ItemWitchesClothes)new ItemWitchesClothes(2).func_77655_b("witchery:barkbelt").func_111206_d("witchery:barkBelt");
/* 152 */   public final ItemWitchesClothes ICY_SLIPPERS = (ItemWitchesClothes)new ItemWitchesClothes(3).func_77655_b("witchery:iceslippers").func_111206_d("witchery:iceSlippers");
/* 153 */   public final ItemWitchesClothes RUBY_SLIPPERS = (ItemWitchesClothes)new ItemWitchesClothes(3).func_77655_b("witchery:rubyslippers").func_111206_d("witchery:rubySlippers");
/* 154 */   public final ItemWitchesClothes SEEPING_SHOES = (ItemWitchesClothes)new ItemWitchesClothes(3).func_77655_b("witchery:seepingshoes").func_111206_d("witchery:seepingShoes");
/*     */   
/* 156 */   public final ItemHunterClothes HUNTER_HAT = (ItemHunterClothes)new ItemHunterClothes(0, false, false).func_77655_b("witchery:hunterhat").func_111206_d("witchery:hunterhat");
/* 157 */   public final ItemHunterClothes HUNTER_COAT = (ItemHunterClothes)new ItemHunterClothes(1, false, false).func_77655_b("witchery:huntercoat").func_111206_d("witchery:huntercoat");
/* 158 */   public final ItemHunterClothes HUNTER_LEGS = (ItemHunterClothes)new ItemHunterClothes(2, false, false).func_77655_b("witchery:hunterlegs").func_111206_d("witchery:hunterlegs");
/* 159 */   public final ItemHunterClothes HUNTER_BOOTS = (ItemHunterClothes)new ItemHunterClothes(3, false, false).func_77655_b("witchery:hunterboots").func_111206_d("witchery:hunterboots");
/*     */   
/* 161 */   public final ItemHunterClothes HUNTER_HAT_SILVERED = (ItemHunterClothes)new ItemHunterClothes(0, true, false).func_77655_b("witchery:hunterhatsilvered").func_111206_d("witchery:hunterhat");
/* 162 */   public final ItemHunterClothes HUNTER_COAT_SILVERED = (ItemHunterClothes)new ItemHunterClothes(1, true, false).func_77655_b("witchery:huntercoatsilvered").func_111206_d("witchery:huntercoat");
/* 163 */   public final ItemHunterClothes HUNTER_LEGS_SILVERED = (ItemHunterClothes)new ItemHunterClothes(2, true, false).func_77655_b("witchery:hunterlegssilvered").func_111206_d("witchery:hunterlegs");
/* 164 */   public final ItemHunterClothes HUNTER_BOOTS_SILVERED = (ItemHunterClothes)new ItemHunterClothes(3, true, false).func_77655_b("witchery:hunterbootssilvered").func_111206_d("witchery:hunterboots");
/*     */   
/* 166 */   public final ItemHunterClothes HUNTER_HAT_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(0, true, true).func_77655_b("witchery:hunterhatgarlicked").func_111206_d("witchery:hunterhat");
/* 167 */   public final ItemHunterClothes HUNTER_COAT_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(1, true, true).func_77655_b("witchery:huntercoatgarlicked").func_111206_d("witchery:huntercoat");
/* 168 */   public final ItemHunterClothes HUNTER_LEGS_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(2, true, true).func_77655_b("witchery:hunterlegsgarlicked").func_111206_d("witchery:hunterlegs");
/* 169 */   public final ItemHunterClothes HUNTER_BOOTS_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(3, true, true).func_77655_b("witchery:hunterbootsgarlicked").func_111206_d("witchery:hunterboots");
/*     */   
/* 171 */   public final ItemDeathsClothes DEATH_HOOD = (ItemDeathsClothes)new ItemDeathsClothes(0).func_77655_b("witchery:deathscowl").func_111206_d("witchery:deathscowl");
/* 172 */   public final ItemDeathsClothes DEATH_ROBE = (ItemDeathsClothes)new ItemDeathsClothes(1).func_77655_b("witchery:deathsrobe").func_111206_d("witchery:deathsrobe");
/* 173 */   public final ItemDeathsClothes DEATH_FEET = (ItemDeathsClothes)new ItemDeathsClothes(3).func_77655_b("witchery:deathsfeet").func_111206_d("witchery:deathsfeet");
/*     */   
/* 175 */   public final Item MOGS_QUIVER = new ItemGoblinClothes(1).func_77655_b("witchery:quiverofmog").func_111206_d("witchery:mogquiver");
/* 176 */   public final Item GULGS_GURDLE = new ItemGoblinClothes(2).func_77655_b("witchery:gurdleofgulg").func_111206_d("witchery:gulggurdle");
/* 177 */   public final Item KOBOLDITE_HELM = new ItemGoblinClothes(0).func_77655_b("witchery:kobolditehelm").func_111206_d("witchery:kobolditehelm");
/*     */   
/* 179 */   public final Item EARMUFFS = new ItemEarmuffs(0).func_77655_b("witchery:earmuffs").func_111206_d("witchery:earmuffs");
/*     */   
/* 181 */   public final ItemVampireClothes VAMPIRE_HAT = (ItemVampireClothes)new ItemVampireClothes(0, false, false).func_77655_b("witchery:vampirehat").func_111206_d("witchery:vampirehat");
/* 182 */   public final ItemVampireClothes VAMPIRE_HELMET = (ItemVampireClothes)new ItemVampireClothes(0, false, true).func_77655_b("witchery:vampirehelmet").func_111206_d("witchery:vampirehelmet");
/* 183 */   public final ItemVampireClothes VAMPIRE_COAT = (ItemVampireClothes)new ItemVampireClothes(1, false, false).func_77655_b("witchery:vampirecoat").func_111206_d("witchery:vampirecoat");
/* 184 */   public final ItemVampireClothes VAMPIRE_COAT_FEMALE = (ItemVampireClothes)new ItemVampireClothes(1, true, false).func_77655_b("witchery:vampirecoat_female").func_111206_d("witchery:vampirecoat");
/*     */   
/* 186 */   public final ItemVampireClothes VAMPIRE_COAT_CHAIN = (ItemVampireClothes)new ItemVampireClothes(1, false, true).func_77655_b("witchery:vampirechaincoat").func_111206_d("witchery:vampirechaincoat");
/* 187 */   public final ItemVampireClothes VAMPIRE_COAT_FEMALE_CHAIN = (ItemVampireClothes)new ItemVampireClothes(1, true, true).func_77655_b("witchery:vampirechaincoat_female").func_111206_d("witchery:vampirechaincoat");
/*     */   
/* 189 */   public final ItemVampireClothes VAMPIRE_LEGS = (ItemVampireClothes)new ItemVampireClothes(2, false, false).func_77655_b("witchery:vampirelegs").func_111206_d("witchery:vampirelegs");
/* 190 */   public final ItemVampireClothes VAMPIRE_LEGS_KILT = (ItemVampireClothes)new ItemVampireClothes(2, true, false).func_77655_b("witchery:vampirelegs_kilt").func_111206_d("witchery:vampirelegs_kilt");
/* 191 */   public final ItemVampireClothes VAMPIRE_BOOTS = (ItemVampireClothes)new ItemVampireClothes(3, false, false).func_77655_b("witchery:vampireboots").func_111206_d("witchery:vampireboots");
/*     */   
/*     */ 
/* 194 */   public final Item SEEDS_BELLADONNA = new ItemWitchSeeds(Witchery.Blocks.CROP_BELLADONNA, new ItemStack(this.GENERIC, 1, this.GENERIC.itemBelladonnaFlower.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedsbelladonna").func_111206_d("witchery:ingredient.seedsBelladonna");
/* 195 */   public final Item SEEDS_MANDRAKE = new ItemWitchSeeds(Witchery.Blocks.CROP_MANDRAKE, new ItemStack(this.GENERIC, 1, this.GENERIC.itemMandrakeRoot.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedsmandrake").func_111206_d("witchery:ingredient.seedsMandrake");
/* 196 */   public final Item SEEDS_ARTICHOKE = new ItemWitchSeeds(Witchery.Blocks.CROP_ARTICHOKE, new ItemStack(this.GENERIC, 1, this.GENERIC.itemArtichoke.damageValue), Blocks.field_150458_ak, true).func_77655_b("witchery:seedsartichoke").func_111206_d("witchery:ingredient.seedsArtichoke");
/* 197 */   public final Item SEEDS_SNOWBELL = new ItemWitchSeeds(Witchery.Blocks.CROP_SNOWBELL, new ItemStack(Items.field_151126_ay), Blocks.field_150458_ak, false).func_77655_b("witchery:seedssnowbell").func_111206_d("witchery:ingredient.seedsSnowbell");
/* 198 */   public final Item SEEDS_WORMWOOD = new ItemWitchSeeds(Witchery.Blocks.CROP_WORMWOOD, new ItemStack(this.GENERIC, 1, this.GENERIC.itemWormwood.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedswormwood").func_111206_d("witchery:ingredient.seedswormwood");
/* 199 */   public final Item SEEDS_MINDRAKE = new ItemWitchSeeds(Witchery.Blocks.CROP_MINDRAKE, null, Blocks.field_150458_ak, false).func_77655_b("witchery:seedsmindrake").func_111206_d("witchery:ingredient.mindrakebulb");
/* 200 */   public final Item SEEDS_WOLFSBANE = new ItemWitchSeeds(Witchery.Blocks.CROP_WOLFSBANE, new ItemStack(this.GENERIC, 1, this.GENERIC.itemWolfsbane.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedswolfsbane").func_111206_d("witchery:ingredient.seedswolfsbane");
/* 201 */   public final Item SEEDS_GARLIC = new ItemWitchSeeds(Witchery.Blocks.CROP_GARLIC, null, Blocks.field_150458_ak, false).func_77655_b("witchery:garlic").func_111206_d("witchery:garlic");
/*     */   
/*     */ 
/* 204 */   public final Item BUCKET_FLOWINGSPIRIT = WitcheryFluids.bind((ItemBucketSpirit)new ItemBucketSpirit().func_77655_b("witchery:bucketspirit").func_111206_d("witchery:bucket_spirit").func_77642_a(Items.field_151133_ar), Witchery.Fluids.FLOWING_SPIRIT, Witchery.Blocks.FLOWING_SPIRIT);
/*     */   
/*     */ 
/* 207 */   public final Item BUCKET_HOLLOWTEARS = WitcheryFluids.bind((ItemBucketSpirit)new ItemBucketSpirit().func_77655_b("witchery:buckethollowtears").func_111206_d("witchery:bucket_hollowtears").func_77642_a(Items.field_151133_ar), Witchery.Fluids.HOLLOW_TEARS, Witchery.Blocks.HOLLOW_TEARS);
/*     */   
/*     */ 
/*     */ 
/* 211 */   public final Item BUCKET_BREW = WitcheryFluids.bind(new ItemBrewBucket().func_77655_b("witchery:bucketbrew").func_111206_d("witchery:bucket_brew").func_77642_a(Items.field_151133_ar), Witchery.Fluids.BREW, 1000);
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/WitcheryItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */