/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ 
/*     */ public class WitcheryPotions
/*     */ {
/*     */   private final List<PotionBase> allEffects;
/*     */   private final List<IHandleLivingUpdate> livingUpdateEventHandlers;
/*     */   private final List<IHandleLivingJump> livingJumpEventHandlers;
/*     */   private final List<IHandleLivingHurt> livingHurtEventHandlers;
/*     */   private final List<IHandleLivingDeath> livingDeathEventHandlers;
/*     */   private final List<IHandlePlayerDrops> playerDropsEventHandlers;
/*     */   private final List<IHandleRenderLiving> renderLivingEventHandlers;
/*     */   private final List<IHandlePreRenderLiving> renderLivingPreEventHandlers;
/*     */   private final List<IHandleEnderTeleport> enderTeleportEventHandlers;
/*     */   private final List<IHandleHarvestDrops> harvestDropsEventHandlers;
/*     */   private final List<IHandleLivingSetAttackTarget> livingSetAttackTargetEventHandlers;
/*     */   private final List<IHandleLivingAttack> livingAttackEventHandlers;
/*     */   public final Potion PARALYSED;
/*     */   public final Potion WRAPPED_IN_VINE;
/*     */   public final Potion SPIKED;
/*     */   public final Potion SPROUTING;
/*     */   public final Potion GROTESQUE;
/*     */   public final Potion LOVE;
/*     */   public final Potion SUN_ALLERGY;
/*     */   public final Potion CHILLED;
/*     */   public final Potion SNOW_TRAIL;
/*     */   public final Potion FLOATING;
/*     */   public final Potion NETHER_BOUND;
/*     */   public final Potion BREWING_EXPERT;
/*     */   public final Potion DOUBLE_JUMP;
/*     */   public final Potion FEATHER_FALL;
/*     */   public final Potion DARKNESS_ALLERGY;
/*     */   public final Potion REINCARNATE;
/*     */   public final Potion INSANITY;
/*     */   public final Potion KEEP_INVENTORY;
/*     */   public final Potion SINKING;
/*     */   
/*     */   public WitcheryPotions()
/*     */   {
/*  49 */     this.allEffects = new ArrayList();
/*  50 */     this.livingUpdateEventHandlers = new ArrayList();
/*  51 */     this.livingJumpEventHandlers = new ArrayList();
/*  52 */     this.livingHurtEventHandlers = new ArrayList();
/*  53 */     this.livingDeathEventHandlers = new ArrayList();
/*  54 */     this.playerDropsEventHandlers = new ArrayList();
/*  55 */     this.renderLivingEventHandlers = new ArrayList();
/*  56 */     this.renderLivingPreEventHandlers = new ArrayList();
/*  57 */     this.enderTeleportEventHandlers = new ArrayList();
/*  58 */     this.harvestDropsEventHandlers = new ArrayList();
/*  59 */     this.livingSetAttackTargetEventHandlers = new ArrayList();
/*  60 */     this.livingAttackEventHandlers = new ArrayList();
/*     */     
/*  62 */     this.PARALYSED = register("witchery:potion.paralysed", PotionParalysis.class);
/*  63 */     this.WRAPPED_IN_VINE = register("witchery:potion.wrappedinvine", PotionWrappedInVine.class);
/*  64 */     this.SPIKED = register("witchery:potion.spiked", PotionSpiked.class);
/*  65 */     this.SPROUTING = register("witchery:potion.sprouting", PotionSprouting.class);
/*  66 */     this.GROTESQUE = register("witchery:potion.grotesque", PotionGrotesque.class);
/*  67 */     this.LOVE = register("witchery:potion.love", PotionLove.class);
/*  68 */     this.SUN_ALLERGY = register("witchery:potion.allergysun", PotionSunAllergy.class);
/*  69 */     this.CHILLED = register("witchery:potion.chilled", PotionChilled.class);
/*  70 */     this.SNOW_TRAIL = register("witchery:potion.snowtrail", PotionSnowTrail.class);
/*  71 */     this.FLOATING = register("witchery:potion.floating", PotionFloating.class);
/*  72 */     this.NETHER_BOUND = register("witchery:potion.hellishaura", PotionHellishAura.class);
/*  73 */     this.BREWING_EXPERT = register("witchery:potion.brewingexpertise", PotionBrewingExpertise.class);
/*  74 */     this.DOUBLE_JUMP = register("witchery:potion.doublejump", PotionBase.class);
/*  75 */     this.FEATHER_FALL = register("witchery:potion.featherfall", PotionFeatherFall.class);
/*  76 */     this.DARKNESS_ALLERGY = register("witchery:potion.allergydark", PotionDarknessAllergy.class);
/*  77 */     this.REINCARNATE = register("witchery:potion.reincarnate", PotionReincarnate.class);
/*  78 */     this.INSANITY = register("witchery:potion.insane", PotionInsanity.class);
/*  79 */     this.KEEP_INVENTORY = register("witchery:potion.keepinventory", PotionKeepInventory.class);
/*  80 */     this.SINKING = register("witchery:potion.sinking", PotionSinking.class);
/*  81 */     this.OVERHEATING = register("witchery:potion.overheating", PotionOverheating.class);
/*  82 */     this.WAKING_NIGHTMARE = register("witchery:potion.wakingnightmare", PotionWakingNightmare.class);
/*  83 */     this.QUEASY = register("witchery:potion.queasy", PotionQueasy.class);
/*  84 */     this.SWIMMING = register("witchery:potion.swimming", PotionSwimming.class);
/*  85 */     this.RESIZING = register("witchery:potion.resizing", PotionResizing.class);
/*  86 */     this.COLORFUL = register("witchery:potion.colorful", PotionColorful.class);
/*  87 */     this.ENDER_INHIBITION = register("witchery:potion.enderinhibition", PotionEnderInhibition.class);
/*  88 */     this.ILL_FITTING = register("witchery:potion.illfitting", PotionIllFitting.class);
/*  89 */     this.VOLATILITY = register("witchery:potion.volatility", PotionVolatility.class);
/*  90 */     this.ENSLAVED = register("witchery:potion.enslaved", PotionEnslaved.class);
/*  91 */     this.MORTAL_COIL = register("witchery:potion.mortalcoil", PotionMortalCoil.class);
/*  92 */     this.ABSORB_MAGIC = register("witchery:potion.absorbmagic", PotionAbsorbMagic.class);
/*  93 */     this.POISON_WEAPONS = register("witchery:potion.poisonweapons", PotionPoisonWeapons.class);
/*  94 */     this.REFLECT_PROJECTILES = register("witchery:potion.reflectprojectiles", PotionReflectProjectiles.class);
/*     */     
/*  96 */     this.REFLECT_DAMAGE = register("witchery:potion.reflectdamage", PotionReflectDamage.class);
/*  97 */     this.ATTRACT_PROJECTILES = register("witchery:potion.attractprojectiles", PotionAttractProjectiles.class);
/*     */     
/*  99 */     this.REPELL_ATTACKER = register("witchery:potion.repellattacker", PotionRepellAttacker.class);
/* 100 */     this.STOUT_BELLY = register("witchery:potion.stoutbelly", PotionStoutBelly.class);
/* 101 */     this.FEEL_NO_PAIN = register("witchery:potion.feelnopain", PotionFeelNoPain.class);
/* 102 */     this.GAS_MASK = register("witchery:potion.gasmask", PotionGasMask.class);
/*     */     
/* 104 */     this.DISEASED = register("witchery:potion.diseased", PotionDiseased.class);
/* 105 */     this.FORTUNE = register("witchery:potion.fortune", PotionFortune.class);
/* 106 */     this.WORSHIP = register("witchery:potion.worship", PotionWorship.class);
/* 107 */     this.KEEP_EFFECTS = register("witchery:potion.keepeffects", PotionKeepEffectsOnDeath.class);
/* 108 */     this.WOLFSBANE = register("witchery:potion.wolfsbane", PotionBase.class);
/*     */   }
/*     */   
/* 111 */   private Potion register(String unlocalisedName, Class<? extends PotionBase> clazz) { int potionID = -1;
/*     */     
/* 113 */     PotionArrayExtender.access$000();
/*     */     
/* 115 */     if (potionID < 1) {
/* 116 */       for (int i = com.emoniph.witchery.util.Config.instance().potionStartID; i < Potion.field_76425_a.length; i++) {
/* 117 */         if (Potion.field_76425_a[i] == null) {
/* 118 */           potionID = com.emoniph.witchery.util.Config.instance().configuration.get("potions", unlocalisedName, i).getInt();
/* 119 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 124 */     if ((potionID > 31) && (potionID < Potion.field_76425_a.length)) {
/*     */       try {
/* 126 */         if (Potion.field_76425_a[potionID] != null) {
/* 127 */           com.emoniph.witchery.util.Log.instance().warning(String.format("Potion slot %d already occupided by %s is being overwriting with %s, you may want to change potion ids in the config file!", new Object[] { Integer.valueOf(potionID), Potion.field_76425_a[potionID].func_76393_a(), unlocalisedName }));
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 134 */         if (potionID > 127) {
/* 135 */           com.emoniph.witchery.util.Log.instance().warning(String.format("Using potion slot %d (for potion %s), can lead to problems, since there is a client/server syncing restriction of max 128 potion IDs. Use the PotionStartID configuration setting to lower the range witchery uses.", new Object[] { Integer.valueOf(potionID), unlocalisedName }));
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */         java.lang.reflect.Constructor<? extends PotionBase> ctor = clazz.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE });
/* 143 */         PotionBase potion = (PotionBase)ctor.newInstance(new Object[] { Integer.valueOf(potionID), Integer.valueOf(unlocalisedName.hashCode()) });
/* 144 */         potion.func_76390_b(unlocalisedName);
/* 145 */         this.allEffects.add(potion);
/* 146 */         if ((potion instanceof IHandleLivingHurt)) {
/* 147 */           this.livingHurtEventHandlers.add((IHandleLivingHurt)potion);
/*     */         }
/* 149 */         if ((potion instanceof IHandleLivingDeath)) {
/* 150 */           this.livingDeathEventHandlers.add((IHandleLivingDeath)potion);
/*     */         }
/* 152 */         if ((potion instanceof IHandleLivingUpdate)) {
/* 153 */           this.livingUpdateEventHandlers.add((IHandleLivingUpdate)potion);
/*     */         }
/* 155 */         if ((potion instanceof IHandleRenderLiving)) {
/* 156 */           this.renderLivingEventHandlers.add((IHandleRenderLiving)potion);
/*     */         }
/*     */         
/* 159 */         if ((potion instanceof IHandlePreRenderLiving)) {
/* 160 */           this.renderLivingPreEventHandlers.add((IHandlePreRenderLiving)potion);
/*     */         }
/* 162 */         if ((potion instanceof IHandlePlayerDrops)) {
/* 163 */           this.playerDropsEventHandlers.add((IHandlePlayerDrops)potion);
/*     */         }
/*     */         
/* 166 */         if ((potion instanceof IHandleLivingJump)) {
/* 167 */           this.livingJumpEventHandlers.add((IHandleLivingJump)potion);
/*     */         }
/*     */         
/* 170 */         if ((potion instanceof IHandleEnderTeleport)) {
/* 171 */           this.enderTeleportEventHandlers.add((IHandleEnderTeleport)potion);
/*     */         }
/*     */         
/* 174 */         if ((potion instanceof IHandleLivingSetAttackTarget)) {
/* 175 */           this.livingSetAttackTargetEventHandlers.add((IHandleLivingSetAttackTarget)potion);
/*     */         }
/*     */         
/* 178 */         if ((potion instanceof IHandleHarvestDrops)) {
/* 179 */           this.harvestDropsEventHandlers.add((IHandleHarvestDrops)potion);
/*     */         }
/*     */         
/* 182 */         if ((potion instanceof IHandleLivingAttack)) {
/* 183 */           this.livingAttackEventHandlers.add((IHandleLivingAttack)potion);
/*     */         }
/*     */         
/* 186 */         potion.postContructInitialize();
/* 187 */         return potion;
/*     */       } catch (NoSuchMethodException ex) {
/* 189 */         return null;
/*     */       } catch (java.lang.reflect.InvocationTargetException ex) {
/* 191 */         return null;
/*     */       } catch (InstantiationException ex) {
/* 193 */         return null;
/*     */       } catch (IllegalAccessException ex) {
/* 195 */         return null;
/*     */       }
/*     */     }
/* 198 */     com.emoniph.witchery.util.Log.instance().warning(String.format("Failed to assign potion %s to slot %d, max slot id is %d, you may want to change the potion ids in the config file!", new Object[] { unlocalisedName, Integer.valueOf(potionID), Integer.valueOf(Potion.field_76425_a.length - 1) }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 203 */     return null; }
/*     */   
/*     */   public final Potion OVERHEATING;
/*     */   public final Potion WAKING_NIGHTMARE;
/*     */   
/* 208 */   public void preInit() { com.emoniph.witchery.util.Config.instance().saveIfChanged(); }
/*     */   
/*     */   public final Potion QUEASY;
/*     */   
/* 212 */   public void init() { for (Potion potion : this.allEffects)
/* 213 */       if ((potion.field_76415_H > 0) && (potion.field_76415_H < Potion.field_76425_a.length)) {
/* 214 */         if (Potion.field_76425_a[potion.field_76415_H] != potion) {
/* 215 */           com.emoniph.witchery.util.Log.instance().warning(String.format("Another mod has overwritten Witchery potion %s in slot %d! offender: %s.", new Object[] { potion.func_76393_a(), Integer.valueOf(potion.field_76415_H), Potion.field_76425_a[potion.field_76415_H].func_76393_a() }));
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/* 221 */         com.emoniph.witchery.util.Log.instance().warning(String.format("Witchery potion has not been registered: %s!", new Object[] { potion.func_76393_a() }));
/*     */   }
/*     */   
/*     */   public final Potion SWIMMING;
/*     */   public final Potion RESIZING;
/*     */   public final Potion COLORFUL;
/*     */   public final Potion ENDER_INHIBITION;
/*     */   
/*     */   private static class PotionArrayExtender {
/*     */     private static void extendPotionArray() {
/* 231 */       if (!potionArrayExtended) {
/* 232 */         int RESERVED = 32;
/* 233 */         int MAX_EXTRA = Math.min(com.emoniph.witchery.util.Config.instance().potionStartID - 32, 0) + 96;
/* 234 */         com.emoniph.witchery.util.Log.instance().debug("Extending the vanilla potions array");
/* 235 */         int existingArrayLength = Potion.field_76425_a.length;
/* 236 */         Potion[] newPotionArray = new Potion[existingArrayLength + MAX_EXTRA];
/* 237 */         System.arraycopy(Potion.field_76425_a, 0, newPotionArray, 0, existingArrayLength);
/* 238 */         setPrivateFinalValue(Potion.class, null, newPotionArray, new String[] { "potionTypes", "field_76425_a" });
/* 239 */         potionArrayExtended = true;
/*     */       }
/*     */     }
/*     */     
/*     */     private static boolean potionArrayExtended;
/*     */     private static <T, E> void setPrivateFinalValue(Class<? super T> classToAccess, T instance, E value, String... fieldNames) {
/* 245 */       java.lang.reflect.Field field = cpw.mods.fml.relauncher.ReflectionHelper.findField(classToAccess, cpw.mods.fml.common.ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));
/*     */       try
/*     */       {
/* 248 */         java.lang.reflect.Field modifiersField = java.lang.reflect.Field.class.getDeclaredField("modifiers");
/* 249 */         modifiersField.setAccessible(true);
/* 250 */         modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
/* 251 */         field.set(instance, value);
/*     */       } catch (Throwable e) {
/* 253 */         e.printStackTrace(); } } }
/*     */   
/*     */   public final Potion ILL_FITTING;
/*     */   public final Potion VOLATILITY;
/*     */   public final Potion ENSLAVED;
/*     */   public final Potion MORTAL_COIL;
/*     */   public final Potion ABSORB_MAGIC; public final Potion POISON_WEAPONS; public final Potion REFLECT_PROJECTILES;
/*     */   public static class EventHooks { @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.HIGHEST)
/* 261 */     public void onPlayerDrops(net.minecraftforge.event.entity.player.PlayerDropsEvent event) { for (IHandlePlayerDrops handler : Witchery.Potions.playerDropsEventHandlers) {
/* 262 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 265 */         if (event.entityLiving.func_70644_a(handler.getPotion())) {
/* 266 */           PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 267 */           handler.onPlayerDrops(event.entityPlayer.func_130014_f_(), event.entityPlayer, event, effect.func_76458_c());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.HIGH)
/*     */     public void onBlockHarvest(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
/*     */     {
/* 275 */       for (IHandleHarvestDrops handler : Witchery.Potions.harvestDropsEventHandlers) {
/* 276 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 279 */         if ((event.harvester != null) && (event.harvester.func_70644_a(handler.getPotion()))) {
/* 280 */           PotionEffect effect = event.harvester.func_70660_b(handler.getPotion());
/* 281 */           handler.onHarvestDrops(event.world, event.harvester, event, effect.func_76458_c());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent event) {
/* 288 */       for (IHandleLivingHurt handler : Witchery.Potions.livingHurtEventHandlers) {
/* 289 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 292 */         if ((handler.handleAllHurtEvents()) || (event.entityLiving.func_70644_a(handler.getPotion()))) {
/* 293 */           PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 294 */           handler.onLivingHurt(event.entityLiving.field_70170_p, event.entityLiving, event, effect != null ? effect.func_76458_c() : -1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onLivingUpdate(net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event)
/*     */     {
/* 302 */       for (IHandleLivingUpdate handler : Witchery.Potions.livingUpdateEventHandlers) {
/* 303 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 306 */         if (event.entityLiving.func_70644_a(handler.getPotion())) {
/* 307 */           PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 308 */           handler.onLivingUpdate(event.entityLiving.field_70170_p, event.entityLiving, event, effect.func_76458_c(), effect.func_76459_b());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onLivingAttack(LivingAttackEvent event)
/*     */     {
/* 316 */       for (IHandleLivingAttack handler : Witchery.Potions.livingAttackEventHandlers) {
/* 317 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 320 */         if (event.entityLiving.func_70644_a(handler.getPotion())) {
/* 321 */           PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 322 */           handler.onLivingAttack(event.entityLiving.field_70170_p, event.entityLiving, event, effect.func_76458_c());
/*     */         }
/*     */       }
/*     */       
/* 326 */       if ((Witchery.modHooks.isAM2Present) && (!event.isCanceled()) && (!event.entity.field_70170_p.field_72995_K) && (event.source == net.minecraft.util.DamageSource.field_76368_d) && ((event.entity instanceof EntityPlayer)) && ((com.emoniph.witchery.common.ExtendedPlayer.get((EntityPlayer)event.entity).getCreatureType() == com.emoniph.witchery.util.TransformCreature.WOLF) || (com.emoniph.witchery.common.ExtendedPlayer.get((EntityPlayer)event.entity).getCreatureType() == com.emoniph.witchery.util.TransformCreature.BAT)))
/*     */       {
/*     */ 
/*     */ 
/* 330 */         if (!event.entity.field_70170_p.func_147439_a(net.minecraft.util.MathHelper.func_76128_c(event.entity.field_70165_t), net.minecraft.util.MathHelper.func_76128_c(event.entity.field_70163_u), net.minecraft.util.MathHelper.func_76128_c(event.entity.field_70161_v)).func_149721_r())
/*     */         {
/*     */ 
/* 333 */           event.setCanceled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onLivingJump(net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent event) {
/* 340 */       for (IHandleLivingJump handler : Witchery.Potions.livingJumpEventHandlers) {
/* 341 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 344 */         if (event.entityLiving.func_70644_a(handler.getPotion())) {
/* 345 */           PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 346 */           handler.onLivingJump(event.entityLiving.field_70170_p, event.entityLiving, event, effect.func_76458_c());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onEnderTeleport(net.minecraftforge.event.entity.living.EnderTeleportEvent event)
/*     */     {
/* 354 */       if ((event.entityLiving != null) && ((event.entityLiving.field_70170_p.field_73011_w.field_76574_g == com.emoniph.witchery.util.Config.instance().dimensionTormentID) || (event.entityLiving.field_70170_p.field_73011_w.field_76574_g == com.emoniph.witchery.util.Config.instance().dimensionMirrorID)))
/*     */       {
/*     */ 
/* 357 */         event.setCanceled(true);
/* 358 */         return;
/*     */       }
/* 360 */       for (IHandleEnderTeleport handler : Witchery.Potions.enderTeleportEventHandlers) {
/* 361 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 364 */         if (event.entityLiving.func_70644_a(handler.getPotion())) {
/* 365 */           PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 366 */           handler.onEnderTeleport(event.entityLiving.field_70170_p, event.entityLiving, event, effect.func_76458_c());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onLivingSetAttackTarget(net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent event) {
/*     */       net.minecraft.entity.EntityLiving livingEntity;
/* 374 */       if ((event.entityLiving instanceof net.minecraft.entity.EntityLiving)) {
/* 375 */         livingEntity = (net.minecraft.entity.EntityLiving)event.entityLiving;
/* 376 */         if ((livingEntity != null) && (Witchery.Potions.ENSLAVED != null) && (event.target != null) && ((event.target instanceof EntityPlayer))) {
/* 377 */           EntityPlayer player = (EntityPlayer)event.target;
/* 378 */           if ((!livingEntity.func_70644_a(Witchery.Potions.ENSLAVED)) && (PotionEnslaved.isMobEnslavedBy(livingEntity, player)))
/*     */           {
/* 380 */             livingEntity.func_70624_b(null);
/*     */           }
/*     */         }
/*     */         
/* 384 */         for (IHandleLivingSetAttackTarget handler : Witchery.Potions.livingSetAttackTargetEventHandlers) {
/* 385 */           if (event.isCanceled()) {
/*     */             break;
/*     */           }
/* 388 */           if (event.entityLiving.func_70644_a(handler.getPotion())) {
/* 389 */             PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 390 */             handler.onLivingSetAttackTarget(event.entityLiving.field_70170_p, livingEntity, event, effect.func_76458_c());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.LOW)
/*     */     public void onLivingDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event)
/*     */     {
/* 399 */       for (IHandleLivingDeath handler : Witchery.Potions.livingDeathEventHandlers) {
/* 400 */         if (event.isCanceled()) {
/*     */           break;
/*     */         }
/* 403 */         if (event.entityLiving.func_70644_a(handler.getPotion())) {
/* 404 */           PotionEffect effect = event.entityLiving.func_70660_b(handler.getPotion());
/* 405 */           handler.onLivingDeath(event.entityLiving.field_70170_p, event.entityLiving, event, effect.func_76458_c());
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 410 */       if ((!event.entityLiving.field_70170_p.field_72995_K) && ((event.entityLiving instanceof EntityPlayer))) {
/* 411 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/* 412 */         java.util.Collection<PotionEffect> activeEffects = player.func_70651_bq();
/* 413 */         if (activeEffects.size() > 0) {
/* 414 */           ArrayList<PotionEffect> permenantEffectList = new ArrayList();
/* 415 */           int allPermentantLevel = -1;
/* 416 */           if (player.func_70644_a(Witchery.Potions.KEEP_EFFECTS)) {
/* 417 */             PotionEffect permAll = player.func_70660_b(Witchery.Potions.KEEP_EFFECTS);
/* 418 */             allPermentantLevel = permAll.func_76458_c();
/*     */           }
/* 420 */           for (PotionEffect effect : activeEffects) {
/* 421 */             int potionID = effect.func_76456_a();
/* 422 */             if ((potionID >= 0) && (potionID < Potion.field_76425_a.length) && (Potion.field_76425_a[potionID] != null))
/*     */             {
/*     */ 
/* 425 */               if ((Potion.field_76425_a[potionID] instanceof PotionBase)) {
/* 426 */                 PotionBase potion = (PotionBase)Potion.field_76425_a[potionID];
/* 427 */                 if (potion.isPermenant()) {
/* 428 */                   permenantEffectList.add(effect);
/* 429 */                   continue;
/*     */                 }
/*     */               }
/* 432 */               Potion potion = Potion.field_76425_a[potionID];
/* 433 */               if ((!PotionBase.isDebuff(potion)) && (allPermentantLevel >= effect.func_76458_c())) {
/* 434 */                 permenantEffectList.add(effect);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 439 */           if (permenantEffectList.size() > 0) {
/* 440 */             net.minecraft.nbt.NBTTagList nbtEffectList = new net.minecraft.nbt.NBTTagList();
/* 441 */             for (PotionEffect permenantEffect : permenantEffectList) {
/* 442 */               net.minecraft.nbt.NBTTagCompound nbtEffect = new net.minecraft.nbt.NBTTagCompound();
/* 443 */               permenantEffect.func_82719_a(nbtEffect);
/* 444 */               nbtEffectList.func_74742_a(nbtEffect);
/*     */             }
/* 446 */             net.minecraft.nbt.NBTTagCompound nbtPlayer = com.emoniph.witchery.infusion.Infusion.getNBT(player);
/* 447 */             nbtPlayer.func_74782_a("WITCPoSpawn", nbtEffectList);
/*     */           } } } } }
/*     */   
/*     */   public final Potion REFLECT_DAMAGE;
/*     */   public final Potion ATTRACT_PROJECTILES;
/*     */   public final Potion REPELL_ATTACKER;
/*     */   public final Potion STOUT_BELLY;
/*     */   public final Potion FEEL_NO_PAIN;
/*     */   
/*     */   public static class ClientEventHooks { @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.NORMAL)
/* 457 */     public void onRenderLiving(net.minecraftforge.client.event.RenderLivingEvent.Pre event) { if ((event.entity != null) && (event.entity.field_70170_p != null) && (event.entity.field_70170_p.field_72995_K)) {
/* 458 */         for (IHandlePreRenderLiving handler : Witchery.Potions.renderLivingPreEventHandlers) {
/* 459 */           if (event.isCanceled()) {
/*     */             break;
/*     */           }
/* 462 */           if (event.entity.func_82165_m(handler.getPotion().field_76415_H)) {
/* 463 */             PotionEffect effect = event.entity.func_70660_b(handler.getPotion());
/* 464 */             handler.onLivingRender(event.entity.field_70170_p, event.entity, event, effect.func_76458_c());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.NORMAL)
/*     */     public void onRenderLiving(net.minecraftforge.client.event.RenderLivingEvent.Post event) {
/* 472 */       if ((event.entity != null) && (event.entity.field_70170_p != null) && (event.entity.field_70170_p.field_72995_K)) {
/* 473 */         for (IHandleRenderLiving handler : Witchery.Potions.renderLivingEventHandlers) {
/* 474 */           if (event.isCanceled()) {
/*     */             break;
/*     */           }
/* 477 */           if (event.entity.func_82165_m(handler.getPotion().field_76415_H)) {
/* 478 */             PotionEffect effect = event.entity.func_70660_b(handler.getPotion());
/* 479 */             handler.onLivingRender(event.entity.field_70170_p, event.entity, event, effect.func_76458_c());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
/* 487 */       if ((event != null) && (!event.isCanceled()) && (event.player != null)) {
/* 488 */         if ((event.player.func_70644_a(Witchery.Potions.RESIZING)) || (!new com.emoniph.witchery.util.EntitySizeInfo(event.player).isDefault))
/*     */         {
/* 490 */           double reach = net.minecraft.client.Minecraft.func_71410_x().field_71442_b.func_78757_d();
/* 491 */           net.minecraft.util.MovingObjectPosition mop = event.player.func_70614_a(reach, event.partialTicks);
/* 492 */           if ((mop != null) && (!com.emoniph.witchery.blocks.BlockBeartrap.checkForHiddenTrap(event.player, mop))) {
/* 493 */             event.context.func_72731_b(event.player, mop, 0, event.partialTicks);
/*     */           }
/* 495 */           event.setCanceled(true);
/*     */         }
/* 497 */         else if (com.emoniph.witchery.blocks.BlockBeartrap.checkForHiddenTrap(event.player, event.target)) {
/* 498 */           event.setCanceled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final Potion GAS_MASK;
/*     */   public final Potion DISEASED;
/*     */   public final Potion FORTUNE;
/*     */   public final Potion WORSHIP;
/*     */   public final Potion KEEP_EFFECTS;
/*     */   public final Potion WOLFSBANE;
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/WitcheryPotions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */