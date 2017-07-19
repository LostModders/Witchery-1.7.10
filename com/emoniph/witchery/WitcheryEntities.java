/*     */ package com.emoniph.witchery;
/*     */ 
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ 
/*     */ public class WitcheryEntities
/*     */ {
/*     */   private final ArrayList<EntityRef> entities;
/*     */   public final EntityRef DEMON;
/*     */   public final EntityRef BROOM;
/*     */   public final EntityRef BREW;
/*     */   public final EntityRef SPECTRAL_FAMILIAR;
/*     */   public final EntityRef MANDRAKE;
/*     */   public final EntityRef TREEFYD;
/*     */   public final EntityRef HUNTSMAN;
/*     */   public final EntityRef SPELL;
/*     */   public final EntityRef ENT;
/*     */   public final EntityRef ILLUSION_CREEPER;
/*     */   public final EntityRef ILLUSION_SPIDER;
/*     */   public final EntityRef ILLUSION_ZOMBIE;
/*     */   public final EntityRef OWL;
/*     */   public final EntityRef TOAD;
/*     */   public final EntityRef CAT_FAMILIAR;
/*     */   public final EntityRef LOUSE;
/*     */   public final EntityRef EYE;
/*     */   public final EntityRef BABA_YAGA;
/*     */   public final EntityRef COVEN_WITCH;
/*     */   public final EntityRef PLAYER_CORPSE;
/*     */   public final EntityRef NIGHTMARE;
/*     */   public final EntityRef SPECTRE;
/*     */   public final EntityRef POLTERGEIST;
/*     */   public final EntityRef BANSHEE;
/*     */   private static final int MIN_SPIRIT_GROUP = 2;
/*     */   private static final int MAX_SPIRIT_GROUP = 5;
/*     */   public final EntityRef SPIRIT;
/*     */   public final EntityRef DEATH;
/*     */   public final EntityRef CROSSBOW_BOLT;
/*     */   public final EntityRef WITCH_HUNTER;
/*     */   public final EntityRef BINKY_HORSE;
/*     */   public final EntityRef LORD_OF_TORMENT;
/*     */   public final EntityRef SOULFIRE;
/*     */   public final EntityRef IMP;
/*     */   public final EntityRef DARK_MARK;
/*     */   public final EntityRef MINDRAKE;
/*     */   public final EntityRef GOBLIN;
/*     */   public final EntityRef GOBLIN_MOG;
/*     */   public final EntityRef GOBLIN_GULG;
/*     */   public final EntityRef BREW2;
/*     */   public final EntityRef ITEM_WAYSTONE;
/*     */   public final EntityRef DROPLET;
/*     */   public final EntityRef SPLATTER;
/*     */   public final EntityRef LEONARD;
/*     */   public final EntityRef LOST_SOUL;
/*     */   public final EntityRef WOLFMAN;
/*     */   public final EntityRef HELLHOUND;
/*     */   public final EntityRef WERE_VILLAGER;
/*     */   public final EntityRef VILLAGE_GUARD;
/*     */   public final EntityRef VAMPIRE_VILLAGER;
/*     */   public final EntityRef GRENADE;
/*     */   public final EntityRef LILITH;
/*     */   public final EntityRef FOLLOWER;
/*     */   public final EntityRef WINGED_MONKEY;
/*     */   public final EntityRef ATTACK_BAT;
/*     */   public final EntityRef MIRROR_FACE;
/*     */   public final EntityRef REFLECTION;
/*     */   
/*     */   public WitcheryEntities()
/*     */   {
/*  74 */     this.entities = new ArrayList();
/*     */     
/*  76 */     this.DEMON = new LivingRef(92, com.emoniph.witchery.entity.EntityDemon.class, "demon", this.entities).setEgg(9502720, 11430927);
/*     */     
/*  78 */     this.BROOM = new EntityRef(93, com.emoniph.witchery.entity.EntityBroom.class, "broom", this.entities);
/*  79 */     this.BREW = new EntityRef(94, com.emoniph.witchery.entity.EntityWitchProjectile.class, "brew", 64, 3, this.entities);
/*  80 */     this.SPECTRAL_FAMILIAR = new LivingRef(95, com.emoniph.witchery.entity.EntityFamiliar.class, "familiar", this.entities);
/*  81 */     this.MANDRAKE = new LivingRef(96, com.emoniph.witchery.entity.EntityMandrake.class, "mandrake", this.entities).setEgg(128271104, 311408);
/*     */     
/*  83 */     this.TREEFYD = new LivingRef(97, com.emoniph.witchery.entity.EntityTreefyd.class, "treefyd", this.entities).setEgg(5781801, 11217964);
/*     */     
/*  85 */     this.HUNTSMAN = new LivingRef(98, com.emoniph.witchery.entity.EntityHornedHuntsman.class, "hornedHuntsman", this.entities).setEgg(11523, 4007964);
/*     */     
/*  87 */     this.SPELL = new EntityRef(99, com.emoniph.witchery.entity.EntitySpellEffect.class, "spellEffect", 64, 3, this.entities);
/*  88 */     this.ENT = new LivingRef(100, com.emoniph.witchery.entity.EntityEnt.class, "ent", this.entities).setEgg(5338965, 5724240);
/*  89 */     this.ILLUSION_CREEPER = new LivingRef(101, com.emoniph.witchery.entity.EntityIllusionCreeper.class, "illusionCreeper", this.entities);
/*     */     
/*  91 */     this.ILLUSION_SPIDER = new LivingRef(102, com.emoniph.witchery.entity.EntityIllusionSpider.class, "illusionSpider", this.entities);
/*     */     
/*  93 */     this.ILLUSION_ZOMBIE = new LivingRef(103, com.emoniph.witchery.entity.EntityIllusionZombie.class, "illusionZombie", this.entities);
/*     */     
/*  95 */     this.OWL = new LivingRef(104, com.emoniph.witchery.entity.EntityOwl.class, "owl", this.entities).setEgg(14869218, 6049609);
/*  96 */     this.TOAD = new LivingRef(105, com.emoniph.witchery.entity.EntityToad.class, "toad", this.entities).setEgg(5780254, 3090974);
/*     */     
/*  98 */     this.CAT_FAMILIAR = new LivingRef(106, com.emoniph.witchery.entity.EntityWitchCat.class, "cat", this.entities);
/*  99 */     this.LOUSE = new LivingRef(107, com.emoniph.witchery.entity.EntityParasyticLouse.class, "louse", this.entities);
/* 100 */     this.EYE = new LivingRef(108, com.emoniph.witchery.entity.EntityEye.class, "eye", 150, 1, this.entities);
/* 101 */     this.BABA_YAGA = new LivingRef(109, com.emoniph.witchery.entity.EntityBabaYaga.class, "babayaga", this.entities).setEgg(7232598, 3881787);
/*     */     
/* 103 */     this.COVEN_WITCH = new LivingRef(110, com.emoniph.witchery.entity.EntityCovenWitch.class, "covenwitch", this.entities).addSpawn(2, 1, 1, EnumCreatureType.creature, new net.minecraft.world.biome.BiomeGenBase[] { net.minecraft.world.biome.BiomeGenBase.field_76780_h }).addSpawn(1, 1, 1, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST)).setEgg(1118481, 11523);
/*     */     
/*     */ 
/*     */ 
/* 107 */     this.PLAYER_CORPSE = new LivingRef(111, com.emoniph.witchery.entity.EntityCorpse.class, "corpse", this.entities);
/* 108 */     this.NIGHTMARE = new LivingRef(112, com.emoniph.witchery.entity.EntityNightmare.class, "nightmare", this.entities).setEgg(983101, 0);
/*     */     
/* 110 */     this.SPECTRE = new LivingRef(113, com.emoniph.witchery.entity.EntitySpectre.class, "spectre", this.entities).setEgg(1052688, 16299031);
/*     */     
/* 112 */     this.POLTERGEIST = new LivingRef(114, com.emoniph.witchery.entity.EntityPoltergeist.class, "poltergeist", this.entities).setEgg(12844917, 12844917);
/*     */     
/* 114 */     this.BANSHEE = new LivingRef(115, com.emoniph.witchery.entity.EntityBanshee.class, "banshee", this.entities).setEgg(13683116, 10136945);
/*     */     
/*     */ 
/*     */ 
/* 118 */     this.SPIRIT = new LivingRef(116, com.emoniph.witchery.entity.EntitySpirit.class, "spirit", this.entities).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MESA)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MOUNTAIN)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.HILLS)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SANDY)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SNOWY)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.WASTELAND)).setEgg(16753968, 15649280);
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
/* 137 */     this.DEATH = new LivingRef(117, com.emoniph.witchery.entity.EntityDeath.class, "death", this.entities).setEgg(0, 0);
/*     */     
/* 139 */     this.CROSSBOW_BOLT = new EntityRef(118, com.emoniph.witchery.entity.EntityBolt.class, "bolt", 64, 10, this.entities);
/* 140 */     this.WITCH_HUNTER = new LivingRef(119, com.emoniph.witchery.entity.EntityWitchHunter.class, "witchhunter", this.entities).setEgg(7893099, 2300953);
/*     */     
/* 142 */     this.BINKY_HORSE = new LivingRef(120, com.emoniph.witchery.entity.EntityDeathsHorse.class, "deathhorse", this.entities);
/* 143 */     this.LORD_OF_TORMENT = new LivingRef(121, com.emoniph.witchery.entity.EntityLordOfTorment.class, "lordoftorment", this.entities).setEgg(9502720, 3346705);
/*     */     
/* 145 */     this.SOULFIRE = new EntityRef(122, com.emoniph.witchery.entity.EntitySoulfire.class, "soulfire", 64, 3, this.entities);
/* 146 */     this.IMP = new LivingRef(123, com.emoniph.witchery.entity.EntityImp.class, "imp", 64, 3, this.entities).setEgg(5776143, 16738816);
/*     */     
/* 148 */     this.DARK_MARK = new LivingRef(124, com.emoniph.witchery.entity.EntityDarkMark.class, "darkmark", 64, 3, this.entities);
/* 149 */     this.MINDRAKE = new LivingRef(125, com.emoniph.witchery.entity.EntityMindrake.class, "mindrake", 64, 3, this.entities).setEgg(19463, 4200704);
/*     */     
/* 151 */     this.GOBLIN = new LivingRef(126, com.emoniph.witchery.entity.EntityGoblin.class, "goblin", 64, 3, this.entities).addSpawn(Math.max(Config.instance().goblinSpawnRate, 1), 1, 2, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP)).addSpawn(Math.max(Config.instance().goblinSpawnRate, 1), 1, 3, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST)).addSpawn(Math.max(Config.instance().goblinSpawnRate - 1, 1), 1, 2, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS)).setEgg(10752, 15616);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 158 */     this.GOBLIN_MOG = new LivingRef(127, com.emoniph.witchery.entity.EntityGoblinMog.class, "goblinmog", 64, 3, this.entities).setEgg(10752, 15616);
/*     */     
/* 160 */     this.GOBLIN_GULG = new LivingRef(128, com.emoniph.witchery.entity.EntityGoblinGulg.class, "goblingulg", 64, 3, this.entities).setEgg(10752, 15616);
/*     */     
/* 162 */     this.BREW2 = new EntityRef(129, com.emoniph.witchery.brewing.EntityBrew.class, "brew2", 64, 1, this.entities);
/* 163 */     this.ITEM_WAYSTONE = new EntityRef(130, com.emoniph.witchery.entity.EntityItemWaystone.class, "item", 64, 20, this.entities);
/* 164 */     this.DROPLET = new EntityRef(131, com.emoniph.witchery.brewing.EntityDroplet.class, "droplet", 64, 20, this.entities);
/* 165 */     this.SPLATTER = new EntityRef(132, com.emoniph.witchery.brewing.EntitySplatter.class, "splatter", 64, 20, this.entities);
/* 166 */     this.LEONARD = new LivingRef(133, com.emoniph.witchery.entity.EntityLeonard.class, "leonard", this.entities).setEgg(12152634, 2818048);
/*     */     
/* 168 */     this.LOST_SOUL = new LivingRef(134, com.emoniph.witchery.entity.EntityLostSoul.class, "lostsoul", this.entities).setEgg(12152634, 2818116);
/*     */     
/* 170 */     this.WOLFMAN = new LivingRef(135, com.emoniph.witchery.entity.EntityWolfman.class, "wolfman", this.entities).setEgg(2960685, 6316128);
/*     */     
/* 172 */     this.HELLHOUND = new LivingRef(136, com.emoniph.witchery.entity.EntityHellhound.class, "hellhound", this.entities).addSpawn(Math.max(Config.instance().hellhoundSpawnRate, 1), 1, 3, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER)).setEgg(14181632, 5968392);
/*     */     
/*     */ 
/*     */ 
/* 176 */     this.WERE_VILLAGER = new LivingRef(137, com.emoniph.witchery.entity.EntityVillagerWere.class, "werevillager", this.entities).setEgg(5651507, 12422002);
/*     */     
/*     */ 
/* 179 */     this.VILLAGE_GUARD = new LivingRef(138, com.emoniph.witchery.entity.EntityVillageGuard.class, "villageguard", this.entities).setEgg(2236962, 5322800);
/*     */     
/*     */ 
/* 182 */     this.VAMPIRE_VILLAGER = new LivingRef(139, com.emoniph.witchery.entity.EntityVampire.class, "vampire", this.entities).setEgg(5322800, 13369344);
/*     */     
/* 184 */     this.GRENADE = new EntityRef(140, com.emoniph.witchery.entity.EntityGrenade.class, "grenade", 64, 1, this.entities);
/*     */     
/* 186 */     this.LILITH = new LivingRef(141, com.emoniph.witchery.entity.EntityLilith.class, "lilith", this.entities).setEgg(0, 2818048);
/*     */     
/* 188 */     this.FOLLOWER = new LivingRef(142, com.emoniph.witchery.entity.EntityFollower.class, "follower", this.entities);
/*     */     
/* 190 */     this.WINGED_MONKEY = new LivingRef(143, com.emoniph.witchery.entity.EntityWingedMonkey.class, "wingedmonkey", this.entities).setEgg(6846848, 7574709);
/*     */     
/*     */ 
/* 193 */     this.ATTACK_BAT = new LivingRef(144, com.emoniph.witchery.entity.EntityAttackBat.class, "attackbat", this.entities);
/*     */     
/* 195 */     this.MIRROR_FACE = new LivingRef(145, com.emoniph.witchery.entity.EntityMirrorFace.class, "mirrorface", this.entities);
/* 196 */     this.REFLECTION = new LivingRef(146, com.emoniph.witchery.entity.EntityReflection.class, "reflection", this.entities).setEgg(5596842, 6715391);
/*     */   }
/*     */   
/*     */ 
/* 200 */   public java.util.List<EntityRef> getEntites() { return this.entities; }
/*     */   
/*     */   public void init() {}
/*     */   
/*     */   public static class EntityRef {
/*     */     public final Class<? extends Entity> entity_class;
/*     */     public final String entity_name;
/*     */     public boolean can_capture;
/*     */     public boolean can_spawn;
/*     */     public boolean can_grind;
/*     */     
/* 211 */     public EntityRef(int id, Class<? extends Entity> clazz, String name, ArrayList<EntityRef> registry) { this(id, clazz, name, 80, 3, registry); }
/*     */     
/*     */ 
/*     */     public EntityRef(int id, Class<? extends Entity> clazz, String name, int range, int updates, ArrayList<EntityRef> registry)
/*     */     {
/* 216 */       this.entity_class = clazz;
/* 217 */       this.entity_name = name;
/* 218 */       cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(clazz, name, id, Witchery.instance, range, updates, true);
/* 219 */       registry.add(this);
/*     */     }
/*     */     
/*     */     public EntityRef setPropsMFR(boolean canCapture, boolean canSpawn, boolean canGrind) {
/* 223 */       this.can_capture = canCapture;
/* 224 */       this.can_spawn = canSpawn;
/* 225 */       this.can_grind = canGrind;
/* 226 */       return this;
/*     */     }
/*     */     
/*     */     public EntityRef setEgg(int color1, int color2) {
/* 230 */       int eggID = getUniqueEggId();
/* 231 */       net.minecraft.entity.EntityList.field_75623_d.put(Integer.valueOf(eggID), this.entity_class);
/* 232 */       net.minecraft.entity.EntityList.field_75627_a.put(Integer.valueOf(eggID), new net.minecraft.entity.EntityList.EntityEggInfo(eggID, color1, color2));
/* 233 */       return this;
/*     */     }
/*     */     
/* 236 */     private static int eggRoot = 6395;
/*     */     
/*     */     private static int getUniqueEggId() {
/*     */       do {
/* 240 */         eggRoot += 1;
/* 241 */       } while (net.minecraft.entity.EntityList.func_75617_a(eggRoot) != null);
/*     */       
/* 243 */       return eggRoot;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class LivingRef extends WitcheryEntities.EntityRef {
/*     */     public final Class<? extends EntityLiving> living_class;
/*     */     
/*     */     public LivingRef(int id, Class<? extends EntityLiving> clazz, String name, ArrayList<WitcheryEntities.EntityRef> registry) {
/* 251 */       super(clazz, name, 80, 3, registry);
/* 252 */       this.living_class = clazz;
/*     */     }
/*     */     
/*     */     public LivingRef(int id, Class<? extends EntityLiving> clazz, String name, int range, int updates, ArrayList<WitcheryEntities.EntityRef> registry)
/*     */     {
/* 257 */       super(clazz, name, range, updates, registry);
/* 258 */       this.living_class = clazz;
/*     */     }
/*     */     
/*     */     public LivingRef addSpawn(int weight, int min, int max, EnumCreatureType type, net.minecraft.world.biome.BiomeGenBase... biomes) {
/* 262 */       cpw.mods.fml.common.registry.EntityRegistry.addSpawn(this.living_class, weight, min, max, type, biomes);
/* 263 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/WitcheryEntities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */