/*      */ package com.emoniph.witchery.common;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.brewing.potions.PotionBase;
/*      */ import com.emoniph.witchery.entity.EntityAttackBat;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.network.PacketPlayerStyle;
/*      */ import com.emoniph.witchery.util.ChatUtil;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.Log;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.TimeUtil;
/*      */ import com.emoniph.witchery.util.TransformCreature;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*      */ import net.minecraft.client.renderer.texture.ITextureObject;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.entity.player.PlayerCapabilities;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EntityDamageSource;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.village.Village;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.common.IExtendedEntityProperties;
/*      */ 
/*      */ public class ExtendedPlayer implements IExtendedEntityProperties
/*      */ {
/*      */   private static final String EXT_PROP_NAME = "WitcheryExtendedPlayer";
/*      */   private final EntityPlayer player;
/*      */   
/*      */   public static final void register(EntityPlayer player)
/*      */   {
/*   64 */     player.registerExtendedProperties("WitcheryExtendedPlayer", new ExtendedPlayer(player));
/*      */   }
/*      */   
/*      */   public static final ExtendedPlayer get(EntityPlayer player) {
/*   68 */     return (ExtendedPlayer)player.getExtendedProperties("WitcheryExtendedPlayer");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*   73 */   private Hashtable<Integer, PotionEffect> incurablePotionEffectCache = new Hashtable();
/*      */   
/*      */   private static final int MAX_SKILL_LEVEL_POTION_BOTTLING = 100;
/*      */   
/*      */   private int skillLevelPotionBottling;
/*      */   private static final int MAX_SKILL_LEVEL_POTION_THROWING = 100;
/*      */   private int skillLevelPotionThrowing;
/*      */   public static final int MAX_HUMAN_BLOOD = 500;
/*      */   private int creatureType;
/*      */   private int werewolfLevel;
/*      */   private int vampireLevel;
/*      */   private int bloodPower;
/*      */   private int bloodReserve;
/*      */   private int vampireUltimate;
/*      */   private int vampireUltimateCharges;
/*      */   private int humanBlood;
/*      */   private int wolfmanQuestState;
/*      */   private int wolfmanQuestCounter;
/*      */   private long lastBoneFind;
/*      */   private long lastHowl;
/*   93 */   private VampirePower selectedVampirePower = VampirePower.NONE;
/*      */   
/*      */   private int vampireCooldown;
/*      */   private int vampireQuestCounter;
/*      */   
/*      */   public ExtendedPlayer(EntityPlayer player)
/*      */   {
/*  100 */     this.player = player;
/*      */   }
/*      */   
/*      */   private boolean vampVisionActive;
/*      */   private String lastPlayerSkin;
/*      */   @SideOnly(Side.CLIENT)
/*      */   private ThreadDownloadImageData downloadImageSkin;
/*      */   public void init(Entity entity, World world) {}
/*      */   
/*      */   public void saveNBTData(NBTTagCompound compound) {
/*  110 */     NBTTagCompound props = new NBTTagCompound();
/*      */     
/*  112 */     props.func_74768_a("PotionBottling", this.skillLevelPotionBottling);
/*  113 */     props.func_74768_a("PotionThrowing", this.skillLevelPotionThrowing);
/*  114 */     props.func_74768_a("CreatureType", this.creatureType);
/*  115 */     props.func_74768_a("WerewolfLevel", this.werewolfLevel);
/*  116 */     props.func_74768_a("WolfmanQuestState", this.wolfmanQuestState);
/*  117 */     props.func_74768_a("WolfmanQuestCounter", this.wolfmanQuestCounter);
/*  118 */     props.func_74772_a("LastBoneFind", this.lastBoneFind);
/*  119 */     props.func_74772_a("LastHowl", this.lastHowl);
/*      */     
/*  121 */     NBTTagList nbtChunks = new NBTTagList();
/*  122 */     for (Iterator i$ = this.visitedChunks.iterator(); i$.hasNext();) { long l = ((Long)i$.next()).longValue();
/*  123 */       NBTTagCompound tag = new NBTTagCompound();
/*  124 */       tag.func_74772_a("Location", l);
/*  125 */       nbtChunks.func_74742_a(tag);
/*      */     }
/*      */     
/*  128 */     props.func_74782_a("WolfmanQuestChunks", nbtChunks);
/*  129 */     props.func_74768_a("VampireLevel", this.vampireLevel);
/*  130 */     props.func_74768_a("BloodPower", this.bloodPower);
/*  131 */     props.func_74768_a("HumanBlood", this.humanBlood);
/*  132 */     props.func_74768_a("VampireUltimate", this.vampireUltimate);
/*  133 */     props.func_74768_a("VampireUltimateCharges", this.vampireUltimateCharges);
/*  134 */     props.func_74768_a("VampireLevelCap", this.vampireLevelCap);
/*  135 */     props.func_74768_a("VampireQuestCounter", this.vampireQuestCounter);
/*  136 */     NBTTagList nbtVampireChunks = new NBTTagList();
/*  137 */     for (Iterator i$ = this.visitedVampireChunks.iterator(); i$.hasNext();) { long l = ((Long)i$.next()).longValue();
/*  138 */       NBTTagCompound tag = new NBTTagCompound();
/*  139 */       tag.func_74772_a("Location", l);
/*  140 */       nbtVampireChunks.func_74742_a(tag);
/*      */     }
/*      */     
/*  143 */     props.func_74782_a("VampireQuestChunks", nbtVampireChunks);
/*  144 */     props.func_74768_a("BloodReserve", this.bloodReserve);
/*  145 */     props.func_74757_a("VampireVision", this.vampVisionActive);
/*      */     
/*  147 */     if (this.cachedInventory != null) {
/*  148 */       props.func_74782_a("CachedInventory2", this.cachedInventory.func_74737_b());
/*  149 */       props.func_74757_a("CanRestoreInventory", this.inventoryCanBeRestored);
/*      */     }
/*      */     
/*  152 */     if (this.mirrorWorldEntryPoint != null) {
/*  153 */       props.func_74782_a("MirrorWorldEntryPoint", this.mirrorWorldEntryPoint.toTagNBT());
/*      */     }
/*      */     
/*  156 */     if (this.lastPlayerSkin != null) {
/*  157 */       props.func_74778_a("LastPlayerSkin", this.lastPlayerSkin);
/*      */     }
/*      */     
/*  160 */     props.func_74772_a("MirrorEscape1", this.mirrorWorldEscapeCooldown1);
/*  161 */     props.func_74772_a("MirrorEscape2", this.mirrorWorldEscapeCooldown2);
/*      */     
/*  163 */     compound.func_74782_a("WitcheryExtendedPlayer", props);
/*      */   }
/*      */   
/*      */   public void loadNBTData(NBTTagCompound compound)
/*      */   {
/*  168 */     if (compound.func_74764_b("WitcheryExtendedPlayer")) {
/*  169 */       NBTTagCompound props = (NBTTagCompound)compound.func_74781_a("WitcheryExtendedPlayer");
/*  170 */       this.skillLevelPotionBottling = MathHelper.func_76125_a(props.func_74762_e("PotionBottling"), 0, 100);
/*      */       
/*  172 */       this.skillLevelPotionThrowing = MathHelper.func_76125_a(props.func_74762_e("PotionThrowing"), 0, 100);
/*      */       
/*  174 */       this.creatureType = MathHelper.func_76125_a(props.func_74762_e("CreatureType"), 0, 5);
/*  175 */       this.werewolfLevel = MathHelper.func_76125_a(props.func_74762_e("WerewolfLevel"), 0, 10);
/*  176 */       this.wolfmanQuestState = MathHelper.func_76125_a(props.func_74762_e("WolfmanQuestState"), 0, QuestState.values().length - 1);
/*      */       
/*  178 */       this.wolfmanQuestCounter = MathHelper.func_76125_a(props.func_74762_e("WolfmanQuestCounter"), 0, 100);
/*  179 */       this.visitedChunks.clear();
/*  180 */       NBTTagList nbtChunks = props.func_150295_c("WolfmanQuestChunks", 10);
/*  181 */       for (int i = 0; i < nbtChunks.func_74745_c(); i++) {
/*  182 */         this.visitedChunks.add(Long.valueOf(nbtChunks.func_150305_b(i).func_74763_f("Location")));
/*      */       }
/*      */       
/*  185 */       this.lastBoneFind = props.func_74763_f("LastBoneFind");
/*  186 */       this.lastHowl = props.func_74763_f("LastHowl");
/*  187 */       this.vampireLevel = MathHelper.func_76125_a(props.func_74762_e("VampireLevel"), 0, 10);
/*  188 */       this.bloodPower = MathHelper.func_76125_a(props.func_74762_e("BloodPower"), 0, getMaxBloodPower());
/*  189 */       this.humanBlood = MathHelper.func_76125_a(props.func_74762_e("HumanBlood"), 0, 500);
/*  190 */       this.vampireUltimate = props.func_74762_e("VampireUltimate");
/*  191 */       this.vampireUltimateCharges = props.func_74762_e("VampireUltimateCharges");
/*  192 */       this.vampireLevelCap = props.func_74762_e("VampireLevelCap");
/*  193 */       this.vampireQuestCounter = props.func_74762_e("VampireQuestCounter");
/*  194 */       NBTTagList nbtVampireChunks = props.func_150295_c("VampireQuestChunks", 10);
/*  195 */       for (int i = 0; i < nbtVampireChunks.func_74745_c(); i++) {
/*  196 */         this.visitedVampireChunks.add(Long.valueOf(nbtVampireChunks.func_150305_b(i).func_74763_f("Location")));
/*      */       }
/*  198 */       this.bloodReserve = props.func_74762_e("BloodReserve");
/*      */       
/*  200 */       this.vampVisionActive = props.func_74767_n("VampireVision");
/*      */       
/*  202 */       if (props.func_74764_b("CachedInventory2")) {
/*  203 */         this.cachedInventory = props.func_150295_c("CachedInventory2", 10);
/*  204 */         this.inventoryCanBeRestored = props.func_74767_n("CanRestoreInventory");
/*      */       }
/*      */       
/*  207 */       if (props.func_74764_b("MirrorWorldEntryPoint")) {
/*  208 */         this.mirrorWorldEntryPoint = Coord.fromTagNBT(props.func_74775_l("MirrorWorldEntryPoint"));
/*      */       }
/*      */       
/*  211 */       if (props.func_74764_b("LastPlayerSkin")) {
/*  212 */         this.lastPlayerSkin = props.func_74779_i("LastPlayerSkin");
/*      */       }
/*      */       
/*  215 */       this.mirrorWorldEscapeCooldown1 = props.func_74763_f("MirrorEscape1");
/*  216 */       this.mirrorWorldEscapeCooldown2 = props.func_74763_f("MirrorEscape2");
/*      */     }
/*      */   }
/*      */   
/*      */   public void setOtherPlayerSkin(String username) {
/*  221 */     this.lastPlayerSkin = username;
/*  222 */     this.locationSkin = null;
/*  223 */     sync();
/*      */   }
/*      */   
/*      */ 
/*  227 */   public String getOtherPlayerSkin() { return this.lastPlayerSkin != null ? this.lastPlayerSkin : ""; }
/*      */   
/*      */   private ResourceLocation locationSkin;
/*      */   private NBTTagList cachedInventory;
/*      */   private boolean inventoryCanBeRestored;
/*      */   private int vampireLevelCap;
/*      */   private static final int DEFAULT_ULTIMATE_CHARGES = 5;
/*      */   public int highlightTicks;
/*      */   @SideOnly(Side.CLIENT)
/*      */   public ResourceLocation getLocationSkin() {
/*  237 */     if (this.locationSkin == null) {
/*  238 */       setupCustomSkin();
/*      */     }
/*  240 */     if (this.locationSkin != null) {
/*  241 */       return this.locationSkin;
/*      */     }
/*  243 */     return null;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   private void setupCustomSkin()
/*      */   {
/*  249 */     String ownerName = getOtherPlayerSkin();
/*  250 */     if ((ownerName != null) && (!ownerName.isEmpty())) {
/*  251 */       this.locationSkin = net.minecraft.client.entity.AbstractClientPlayer.func_110311_f(ownerName);
/*  252 */       this.downloadImageSkin = getDownloadImageSkin(this.locationSkin, ownerName);
/*      */     } else {
/*  254 */       this.locationSkin = null;
/*  255 */       this.downloadImageSkin = null;
/*      */     }
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   private static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation location, String name) {
/*  261 */     TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
/*  262 */     Object object = texturemanager.func_110581_b(location);
/*      */     
/*  264 */     if (object == null) {
/*  265 */       object = new ThreadDownloadImageData((java.io.File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] { net.minecraft.util.StringUtils.func_76338_a(name) }), com.emoniph.witchery.client.renderer.RenderReflection.SKIN, new net.minecraft.client.renderer.ImageBufferDownload());
/*      */       
/*      */ 
/*      */ 
/*  269 */       texturemanager.func_110579_a(location, (ITextureObject)object);
/*      */     }
/*      */     
/*  272 */     return (ThreadDownloadImageData)object;
/*      */   }
/*      */   
/*      */   public ResourceLocation getOtherPlayerSkinLocation()
/*      */   {
/*  277 */     return getLocationSkin();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void cachePlayerInventory()
/*      */   {
/*  284 */     this.inventoryCanBeRestored = true;
/*      */   }
/*      */   
/*      */   public void backupPlayerInventory() {
/*  288 */     NBTTagList nbtInventory = new NBTTagList();
/*  289 */     this.player.field_71071_by.func_70442_a(nbtInventory);
/*  290 */     this.cachedInventory = nbtInventory;
/*      */   }
/*      */   
/*      */   public void restorePlayerInventoryFrom(ExtendedPlayer original) {
/*  294 */     if ((original != null) && (this.cachedInventory != null) && (this.inventoryCanBeRestored)) {
/*  295 */       this.player.field_71071_by.func_70443_b(original.cachedInventory);
/*  296 */       this.inventoryCanBeRestored = false;
/*  297 */       this.cachedInventory = null;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getSkillPotionBottling() {
/*  302 */     return this.skillLevelPotionBottling;
/*      */   }
/*      */   
/*      */   public int increaseSkillPotionBottling() {
/*  306 */     this.skillLevelPotionBottling = Math.min(this.skillLevelPotionBottling + 1, 100);
/*  307 */     if ((this.skillLevelPotionBottling == 30) || (this.skillLevelPotionBottling == 60) || (this.skillLevelPotionBottling == 90)) {
/*  308 */       ChatUtil.sendTranslated(this.player, "witchery:brew.skillincrease", new Object[0]);
/*      */     }
/*  310 */     return getSkillPotionBottling();
/*      */   }
/*      */   
/*      */   public int getSkillPotionThrowing() {
/*  314 */     return this.skillLevelPotionThrowing;
/*      */   }
/*      */   
/*      */   public int increaseSkillPotionThrowing() {
/*  318 */     this.skillLevelPotionThrowing = Math.min(this.skillLevelPotionThrowing + 1, 100);
/*  319 */     return getSkillPotionBottling();
/*      */   }
/*      */   
/*      */   public int getWerewolfLevel() {
/*  323 */     return this.werewolfLevel;
/*      */   }
/*      */   
/*      */   public void setWerewolfLevel(int level) {
/*  327 */     if ((this.werewolfLevel != level) && (level >= 0) && (level <= 10)) {
/*  328 */       this.werewolfLevel = level;
/*  329 */       this.wolfmanQuestState = 0;
/*  330 */       this.wolfmanQuestCounter = 0;
/*  331 */       this.visitedChunks.clear();
/*  332 */       if ((this.werewolfLevel == 0) && (!this.player.field_70170_p.field_72995_K) && (
/*  333 */         (this.creatureType == 1) || (this.creatureType == 2))) {
/*  334 */         Shapeshift.INSTANCE.shiftTo(this.player, TransformCreature.NONE);
/*      */       }
/*      */       
/*  337 */       sync();
/*      */     }
/*      */   }
/*      */   
/*      */   public void increaseWerewolfLevel() {
/*  342 */     if (this.werewolfLevel < 10) {
/*  343 */       setWerewolfLevel(this.werewolfLevel + 1);
/*  344 */       Shapeshift.INSTANCE.initCurrentShift(this.player);
/*      */     }
/*      */   }
/*      */   
/*      */   public int getHumanBlood() {
/*  349 */     return this.humanBlood;
/*      */   }
/*      */   
/*      */   public void setHumanBlood(int blood) {
/*  353 */     if (this.humanBlood != blood) {
/*  354 */       this.humanBlood = MathHelper.func_76125_a(blood, 0, 500);
/*  355 */       if (!this.player.field_70170_p.field_72995_K) {
/*  356 */         Witchery.packetPipeline.sendToAll(new com.emoniph.witchery.network.PacketPartialExtendedPlayerSync(this, this.player));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public int takeHumanBlood(int quantity, EntityLivingBase attacker) {
/*  362 */     if (!this.player.func_70608_bn()) {
/*  363 */       quantity = (int)Math.ceil(0.66F * quantity);
/*      */     }
/*  365 */     int remainder = Math.max(this.humanBlood - quantity, 0);
/*  366 */     int taken = this.humanBlood - remainder;
/*  367 */     setHumanBlood(remainder);
/*  368 */     if (this.humanBlood < (int)Math.ceil(250.0D)) {
/*  369 */       this.player.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker), 1.0F);
/*  370 */     } else if (!this.player.func_70608_bn()) {
/*  371 */       this.player.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker), 0.1F);
/*      */     }
/*  373 */     return taken;
/*      */   }
/*      */   
/*      */   public void giveHumanBlood(int quantity) {
/*  377 */     if (this.humanBlood < 500) {
/*  378 */       setHumanBlood(this.humanBlood + quantity);
/*      */     }
/*      */   }
/*      */   
/*      */   public int getVampireLevel() {
/*  383 */     return this.vampireLevel;
/*      */   }
/*      */   
/*      */   public boolean isVampire() {
/*  387 */     return getVampireLevel() > 0;
/*      */   }
/*      */   
/*      */   public void setVampireLevel(int level) {
/*  391 */     if ((this.vampireLevel != level) && (level >= 0) && (level <= 10)) {
/*  392 */       this.vampireLevel = level;
/*  393 */       this.vampireQuestCounter = 0;
/*  394 */       this.visitedVampireChunks.clear();
/*  395 */       if ((this.vampireLevel == 0) && (!this.player.field_70170_p.field_72995_K)) {
/*  396 */         if (this.creatureType == 3) {
/*  397 */           Shapeshift.INSTANCE.shiftTo(this.player, TransformCreature.NONE);
/*      */         } else {
/*  399 */           Shapeshift.INSTANCE.initCurrentShift(this.player);
/*      */         }
/*  401 */         this.bloodPower = 0;
/*  402 */         this.humanBlood = 50;
/*  403 */         this.vampireUltimate = 0;
/*  404 */         this.vampireUltimateCharges = 0;
/*      */       } else {
/*  406 */         Shapeshift.INSTANCE.initCurrentShift(this.player);
/*      */       }
/*      */       
/*      */ 
/*  410 */       this.selectedVampirePower = VampirePower.NONE;
/*      */       
/*  412 */       if (this.vampireLevel == 1) {
/*  413 */         this.bloodPower = 125;
/*      */       }
/*      */       
/*  416 */       if (this.vampireLevel > 0) {
/*  417 */         this.humanBlood = 0;
/*      */       }
/*  419 */       sync();
/*      */     }
/*      */   }
/*      */   
/*      */   public int getMaxBloodPower() {
/*  424 */     return 500 + (getWerewolfLevel() >= 2 ? (int)Math.floor(getVampireLevel() * 0.5D) : getVampireLevel()) * 250;
/*      */   }
/*      */   
/*      */   public int getBloodPower() {
/*  428 */     return this.bloodPower;
/*      */   }
/*      */   
/*      */   public boolean decreaseBloodPower(int quantity, boolean exact) {
/*  432 */     if (this.player.field_71075_bZ.field_75098_d)
/*  433 */       return true;
/*  434 */     if (this.bloodPower >= (exact ? quantity : 1)) {
/*  435 */       setBloodPower(this.bloodPower - quantity);
/*  436 */       return true;
/*      */     }
/*  438 */     return false;
/*      */   }
/*      */   
/*      */   public void increaseBloodPower(int quantity)
/*      */   {
/*  443 */     if (this.bloodPower < getMaxBloodPower()) {
/*  444 */       setBloodPower(this.bloodPower + quantity);
/*  445 */       if ((Config.instance().allowVampireQuests) && (getVampireLevel() == 1) && (getBloodPower() == getMaxBloodPower()))
/*      */       {
/*  447 */         increaseVampireLevel();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void increaseVampireLevel() {
/*  453 */     if (this.vampireLevel < 10) {
/*  454 */       setVampireLevel(this.vampireLevel + 1);
/*  455 */       if (!this.player.field_70170_p.field_72995_K) {
/*  456 */         ChatUtil.sendTranslated(EnumChatFormatting.GOLD, this.player, "Your thirst grows stronger!", new Object[0]);
/*  457 */         SoundEffect.RANDOM_LEVELUP.playOnlyTo(this.player);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void increaseVampireLevelCap(int levelCap)
/*      */   {
/*  465 */     if (levelCap > this.vampireLevelCap) {
/*  466 */       this.vampireLevelCap = Math.max(levelCap, 3);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean canIncreaseVampireLevel() {
/*  471 */     return (Config.instance().allowVampireQuests) && (this.vampireLevel < this.vampireLevelCap);
/*      */   }
/*      */   
/*      */   public void increaseBloodPower(int quantity, int maxIncrease) {
/*  475 */     if ((this.bloodPower < getMaxBloodPower()) && (this.bloodPower < maxIncrease)) {
/*  476 */       setBloodPower(Math.min(this.bloodPower + quantity, maxIncrease));
/*      */     }
/*      */   }
/*      */   
/*      */   public void setBloodPower(int bloodLevel) {
/*  481 */     if (this.bloodPower != bloodLevel) {
/*  482 */       this.bloodPower = MathHelper.func_76125_a(bloodLevel, 0, getMaxBloodPower());
/*  483 */       sync();
/*      */     }
/*      */   }
/*      */   
/*      */   public static enum VampireUltimate {
/*  488 */     NONE,  STORM,  SWARM,  FARM;
/*      */     
/*      */     private VampireUltimate() {} }
/*      */   
/*  492 */   public VampireUltimate getVampireUltimate() { return VampireUltimate.values()[this.vampireUltimate]; }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setVampireUltimate(VampireUltimate skill)
/*      */   {
/*  498 */     setVampireUltimate(skill, 5);
/*      */   }
/*      */   
/*      */   public void setVampireUltimate(VampireUltimate skill, int charges) {
/*  502 */     this.vampireUltimate = skill.ordinal();
/*  503 */     this.vampireUltimateCharges = charges;
/*  504 */     sync();
/*      */   }
/*      */   
/*      */   public int getVampireUltimateCharges() {
/*  508 */     return this.vampireUltimateCharges;
/*      */   }
/*      */   
/*      */   public static enum VampirePower {
/*  512 */     NONE(0, 0, 0),  DRINK(0, 0, 1),  MESMERIZE(50, 0, 2),  SPEED(10, 0, 4),  BAT(50, 1, 7),  ULTIMATE(50, 0, 10);
/*      */     
/*      */     public final int INITIAL_COST;
/*      */     public final int UPKEEP_COST;
/*      */     public final int LEVEL_CAP;
/*      */     
/*  518 */     private VampirePower(int initialCost, int upkeepCost, int levelCap) { this.INITIAL_COST = initialCost;
/*  519 */       this.UPKEEP_COST = upkeepCost;
/*  520 */       this.LEVEL_CAP = levelCap;
/*      */     }
/*      */     
/*  523 */     private static int[] levels = { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5 };
/*      */   }
/*      */   
/*      */   public VampirePower getSelectedVampirePower() {
/*  527 */     return this.selectedVampirePower;
/*      */   }
/*      */   
/*      */   public int getMaxAvailablePowerOrdinal() {
/*  531 */     return VampirePower.levels[this.vampireLevel];
/*      */   }
/*      */   
/*      */   public void useBloodReserve() {
/*  535 */     int temp = this.bloodReserve;
/*  536 */     if (this.bloodPower < getMaxBloodPower()) {
/*  537 */       this.bloodReserve = 0;
/*  538 */       increaseBloodPower(temp);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isBloodReserveReady() {
/*  543 */     return this.bloodReserve > 0;
/*      */   }
/*      */   
/*      */   public void fillBloodReserve(int quantity) {
/*  547 */     this.bloodReserve = Math.min(this.bloodReserve + quantity, 250);
/*  548 */     sync();
/*      */   }
/*      */   
/*      */   public int getBloodReserve() {
/*  552 */     return isVampire() ? this.bloodReserve : 0;
/*      */   }
/*      */   
/*      */   public void setBloodReserve(int blood) {
/*  556 */     this.bloodReserve = blood;
/*      */   }
/*      */   
/*      */   public boolean isVampireVisionActive() {
/*  560 */     return (this.vampireLevel >= 2) && (this.vampVisionActive);
/*      */   }
/*      */   
/*      */   public void toggleVampireVision() {
/*  564 */     this.vampVisionActive = (!this.vampVisionActive);
/*  565 */     if (!this.player.field_70170_p.field_72995_K) {
/*  566 */       if (!this.vampVisionActive) {
/*  567 */         this.player.func_82170_o(Potion.field_76439_r.field_76415_H);
/*      */       } else {
/*  569 */         this.player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 400, 0, true));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSelectedVampirePower(VampirePower power, boolean syncToServer)
/*      */   {
/*  577 */     if (this.selectedVampirePower != power) {
/*  578 */       this.selectedVampirePower = power;
/*      */       
/*  580 */       this.highlightTicks = (this.selectedVampirePower != VampirePower.NONE ? 100 : 0);
/*      */       
/*  582 */       if ((syncToServer) && (this.player.field_70170_p.field_72995_K)) {
/*  583 */         Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketSelectPlayerAbility(this, false));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void triggerSelectedVampirePower() {
/*  589 */     if (!this.player.field_70170_p.field_72995_K) {
/*  590 */       VampirePower power = getSelectedVampirePower();
/*  591 */       if (this.vampireCooldown <= 0) {
/*  592 */         this.vampireCooldown = 10;
/*  593 */         switch (power) {
/*      */         case MESMERIZE: 
/*  595 */           if (!this.player.func_70093_af()) break;
/*  596 */           toggleVampireVision(); break;
/*      */         
/*      */ 
/*      */         case SPEED: 
/*  600 */           if (getCreatureType() == TransformCreature.NONE) {
/*  601 */             PotionEffect effect = this.player.func_70660_b(Potion.field_76424_c);
/*  602 */             int currentLevel = effect == null ? 0 : (int)Math.ceil(Math.log(effect.func_76458_c() + 1) / Math.log(2.0D));
/*      */             
/*  604 */             if ((this.vampireLevel >= 4) && (currentLevel <= Math.ceil((this.vampireLevel - 3) / 2.0F))) {
/*  605 */               if (decreaseBloodPower(power.INITIAL_COST, true)) {
/*  606 */                 SoundEffect.RANDOM_FIZZ.playOnlyTo(this.player);
/*  607 */                 int level = effect == null ? 2 : (effect.func_76458_c() + 1) * 2;
/*  608 */                 int duration = effect == null ? TimeUtil.secsToTicks(10) : effect.func_76459_b() + 60;
/*      */                 
/*  610 */                 this.player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, duration, level - 1, true));
/*      */                 
/*  612 */                 this.player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, duration, currentLevel + 1, true));
/*      */               }
/*      */               else {
/*  615 */                 SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */               }
/*      */             } else {
/*  618 */               SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */             }
/*      */           } else {
/*  621 */             SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */           }
/*  623 */           break;
/*      */         case BAT: 
/*  625 */           if (this.vampireLevel >= 7) {
/*  626 */             if (getCreatureType() == TransformCreature.NONE) {
/*  627 */               if (decreaseBloodPower(power.INITIAL_COST, true)) {
/*  628 */                 SoundEffect.RANDOM_FIZZ.playOnlyTo(this.player);
/*  629 */                 Shapeshift.INSTANCE.shiftTo(this.player, TransformCreature.BAT);
/*      */               } else {
/*  631 */                 SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */               }
/*  633 */             } else if (getCreatureType() == TransformCreature.BAT) {
/*  634 */               SoundEffect.RANDOM_FIZZ.playOnlyTo(this.player);
/*  635 */               Shapeshift.INSTANCE.shiftTo(this.player, TransformCreature.NONE);
/*      */             } else {
/*  637 */               SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */             }
/*      */           } else {
/*  640 */             SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */           }
/*  642 */           break;
/*      */         case ULTIMATE: 
/*  644 */           if ((this.vampireLevel >= 10) && (this.vampireUltimateCharges > 0) && (getCreatureType() == TransformCreature.NONE))
/*      */           {
/*  646 */             switch (getVampireUltimate()) {
/*      */             case STORM: 
/*  648 */               WorldInfo worldinfo = ((WorldServer)this.player.field_70170_p).func_72912_H();
/*  649 */               if (!worldinfo.func_76059_o()) {
/*  650 */                 int i = (300 + this.player.field_70170_p.field_73012_v.nextInt(600)) * 20;
/*  651 */                 worldinfo.func_76090_f(i);
/*  652 */                 worldinfo.func_76069_a(true);
/*  653 */                 worldinfo.func_76080_g(i);
/*  654 */                 worldinfo.func_76084_b(true);
/*  655 */                 SoundEffect.RANDOM_FIZZ.playOnlyTo(this.player);
/*  656 */                 if (!this.player.field_71075_bZ.field_75098_d) {
/*  657 */                   this.vampireUltimateCharges -= 1;
/*  658 */                   sync();
/*      */                 }
/*      */               } else {
/*  661 */                 SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */               }
/*  663 */               break;
/*      */             
/*      */             case SWARM: 
/*  666 */               for (int i = 0; i < 15; i++) {
/*  667 */                 EntityLiving creature = spawnCreature(this.player.field_70170_p, EntityAttackBat.class, this.player.field_70165_t, this.player.field_70163_u + 3.0D + this.player.field_70170_p.field_73012_v.nextDouble(), this.player.field_70161_v, 1, 4, ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF);
/*      */                 
/*      */ 
/*  670 */                 if (creature != null) {
/*  671 */                   EntityAttackBat bat = (EntityAttackBat)creature;
/*  672 */                   bat.setOwner(this.player);
/*  673 */                   bat.func_82236_f(false);
/*  674 */                   NBTTagCompound nbtBat = bat.getEntityData();
/*  675 */                   nbtBat.func_74757_a("WITCNoDrops", true);
/*      */                 }
/*      */               }
/*  678 */               if (!this.player.field_71075_bZ.field_75098_d) {
/*  679 */                 this.vampireUltimateCharges -= 1;
/*  680 */                 sync();
/*      */               }
/*      */               
/*      */               break;
/*      */             case FARM: 
/*  685 */               boolean done = false;
/*  686 */               if (this.player.field_71093_bK != Config.instance().dimensionDreamID) {
/*  687 */                 ChunkCoordinates coords = this.player.getBedLocation(this.player.field_71093_bK);
/*  688 */                 int dimension = this.player.field_71093_bK;
/*  689 */                 World world = this.player.field_70170_p;
/*  690 */                 if (coords == null) {
/*  691 */                   coords = this.player.getBedLocation(0);
/*  692 */                   dimension = 0;
/*  693 */                   world = MinecraftServer.func_71276_C().func_71218_a(0);
/*  694 */                   if (coords == null) {
/*  695 */                     coords = world.func_72861_E();
/*      */                     
/*  697 */                     while ((world.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c).func_149721_r()) && (coords.field_71572_b < 255)) {
/*  698 */                       coords.field_71572_b += 1;
/*      */                     }
/*      */                   }
/*      */                 }
/*      */                 
/*  703 */                 if (coords != null) {
/*  704 */                   double HOME_DIST = 6.0D;
/*  705 */                   double HOME_DIST_SQ = 36.0D;
/*  706 */                   coords = net.minecraft.init.Blocks.field_150324_C.getBedSpawnPosition(world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, null);
/*      */                   
/*  708 */                   if (coords != null) {
/*  709 */                     if ((dimension == this.player.field_71093_bK) && (this.player.func_70092_e(coords.field_71574_a, this.player.field_70163_u, coords.field_71573_c) <= 36.0D))
/*      */                     {
/*  711 */                       Village village = world.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.player.field_70165_t), MathHelper.func_76128_c(this.player.field_70163_u), MathHelper.func_76128_c(this.player.field_70161_v), 512);
/*      */                       
/*      */ 
/*      */ 
/*  715 */                       if (village != null) {
/*  716 */                         ChunkCoordinates townPos = village.func_75577_a();
/*  717 */                         if (ItemGeneral.teleportToLocationSafely(this.player.field_70170_p, townPos.field_71574_a + 0.5D, townPos.field_71572_b + 1, townPos.field_71573_c + 0.5D, dimension, this.player, true))
/*      */                         {
/*      */ 
/*  720 */                           done = true;
/*      */                         }
/*      */                       }
/*      */                     }
/*  724 */                     else if (ItemGeneral.teleportToLocationSafely(this.player.field_70170_p, coords.field_71574_a + 0.5D, coords.field_71572_b + 1, coords.field_71573_c + 0.5D, dimension, this.player, true))
/*      */                     {
/*      */ 
/*  727 */                       done = true;
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/*  733 */               if (!done) {
/*  734 */                 SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */               }
/*  736 */               else if (!this.player.field_71075_bZ.field_75098_d) {
/*  737 */                 this.vampireUltimateCharges -= 1;
/*  738 */                 sync();
/*      */               }
/*      */               
/*      */               break;
/*      */             default: 
/*  743 */               SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */             }
/*      */             
/*      */           } else {
/*  747 */             SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */           }
/*  749 */           break;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  754 */         SoundEffect.NOTE_SNARE.playOnlyTo(this.player);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static EntityLiving spawnCreature(World world, Class<? extends EntityLiving> creatureType, double posX, double posY, double posZ, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound)
/*      */   {
/*  761 */     if (!world.field_72995_K) {
/*  762 */       int x = MathHelper.func_76128_c(posX);
/*  763 */       int y = MathHelper.func_76128_c(posY);
/*  764 */       int z = MathHelper.func_76128_c(posZ);
/*      */       
/*  766 */       int activeRadius = maxRange - minRange;
/*  767 */       int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  768 */       if (ax > activeRadius) {
/*  769 */         ax += minRange * 2;
/*      */       }
/*      */       
/*  772 */       int nx = x - maxRange + ax;
/*      */       
/*  774 */       int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  775 */       if (az > activeRadius) {
/*  776 */         az += minRange * 2;
/*      */       }
/*      */       
/*  779 */       int nz = z - maxRange + az;
/*      */       
/*  781 */       int ny = y;
/*  782 */       while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
/*  783 */         ny++;
/*      */       }
/*      */       
/*      */ 
/*  787 */       while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
/*  788 */         ny--;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  793 */       int hy = 0;
/*  794 */       while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
/*  795 */         hy++;
/*      */       }
/*      */       
/*  798 */       Log.instance().debug("Creature: hy: " + hy + " (" + nx + "," + ny + "," + nz + ")");
/*      */       
/*  800 */       if (hy >= 2) {
/*      */         try {
/*  802 */           Constructor ctor = creatureType.getConstructor(new Class[] { World.class });
/*  803 */           EntityLiving creature = (EntityLiving)ctor.newInstance(new Object[] { world });
/*      */           
/*  805 */           creature.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
/*  806 */           world.func_72838_d(creature);
/*  807 */           if (effect != null) {
/*  808 */             effect.send(effectSound, world, 0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 1.0D, creature.field_70131_O, 16);
/*      */           }
/*      */           
/*  811 */           return creature;
/*      */         }
/*      */         catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  819 */     return null;
/*      */   }
/*      */   
/*      */   public void tick() {
/*  823 */     if (this.vampireCooldown > 0) {
/*  824 */       this.vampireCooldown -= 1;
/*      */     }
/*      */   }
/*      */   
/*      */   public void updateWorship() {
/*  829 */     if (this.cachedWorship >= 0) {
/*  830 */       this.player.func_70690_d(new PotionEffect(Witchery.Potions.WORSHIP.field_76415_H, TimeUtil.secsToTicks(60), this.cachedWorship, true));
/*      */       
/*  832 */       this.cachedWorship = -1;
/*      */     }
/*  834 */     processSync();
/*      */   }
/*      */   
/*      */   public boolean cacheIncurablePotionEffect(Collection<PotionEffect> activePotionEffects) {
/*  838 */     boolean cached = false;
/*  839 */     for (PotionEffect activeEffect : activePotionEffects) {
/*  840 */       int potionID = activeEffect.func_76456_a();
/*  841 */       if ((potionID >= 0) && (potionID < Potion.field_76425_a.length) && (Potion.field_76425_a[potionID] != null) && ((Potion.field_76425_a[potionID] instanceof PotionBase)) && (activeEffect.func_76459_b() > 5))
/*      */       {
/*  843 */         PotionBase potion = (PotionBase)Potion.field_76425_a[potionID];
/*  844 */         if (!potion.isCurable()) {
/*  845 */           this.incurablePotionEffectCache.put(Integer.valueOf(activeEffect.func_76456_a()), activeEffect);
/*  846 */           cached = true;
/*      */         }
/*      */       }
/*      */     }
/*  850 */     return cached;
/*      */   }
/*      */   
/*      */   public void clearCachedIncurablePotionEffect(Potion potion) {
/*  854 */     this.incurablePotionEffectCache.remove(Integer.valueOf(potion.field_76415_H));
/*      */   }
/*      */   
/*      */   public void restoreIncurablePotionEffects() {
/*  858 */     if (this.incurablePotionEffectCache.size() > 0) {
/*  859 */       Collection<PotionEffect> activeEffectList = this.player.func_70651_bq();
/*  860 */       for (PotionEffect activeEffect : activeEffectList) {
/*  861 */         this.incurablePotionEffectCache.remove(Integer.valueOf(activeEffect.func_76456_a()));
/*      */       }
/*  863 */       for (PotionEffect restoredEffect : this.incurablePotionEffectCache.values()) {
/*  864 */         this.player.func_70690_d(new PotionEffect(restoredEffect));
/*      */       }
/*  866 */       this.incurablePotionEffectCache.clear();
/*      */     }
/*      */   }
/*      */   
/*  870 */   public int cachedWorship = -1;
/*      */   
/*      */   public void addWorship(int level) {
/*  873 */     this.cachedWorship = level;
/*      */   }
/*      */   
/*      */   public void sync() {
/*  877 */     if (!this.player.field_70170_p.field_72995_K) {
/*  878 */       Witchery.packetPipeline.sendTo(new com.emoniph.witchery.network.PacketExtendedPlayerSync(this), this.player);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void loadProxyData(EntityPlayer player) {
/*  883 */     if (player != null) {
/*  884 */       ExtendedPlayer playerEx = get(player);
/*  885 */       playerEx.sync();
/*      */     }
/*      */   }
/*      */   
/*      */   public int getCreatureTypeOrdinal() {
/*  890 */     return this.creatureType;
/*      */   }
/*      */   
/*      */   public TransformCreature getCreatureType() {
/*  894 */     return TransformCreature.values()[this.creatureType];
/*      */   }
/*      */   
/*      */   public void setCreatureType(TransformCreature type) {
/*  898 */     int ordinalType = type.ordinal();
/*  899 */     setCreatureTypeOrdinal(ordinalType);
/*      */   }
/*      */   
/*      */   public void setCreatureTypeOrdinal(int type) {
/*  903 */     if (type != this.creatureType) {
/*  904 */       this.creatureType = type;
/*  905 */       if (!this.player.field_70170_p.field_72995_K) {
/*  906 */         Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(this.player));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public long getLastBoneFind() {
/*  912 */     return this.lastBoneFind;
/*      */   }
/*      */   
/*      */   public void setLastBoneFind(long serverTime) {
/*  916 */     this.lastBoneFind = serverTime;
/*      */   }
/*      */   
/*      */   public long getLastHowl() {
/*  920 */     return this.lastHowl;
/*      */   }
/*      */   
/*      */   public void setLastHowl(long serverTime) {
/*  924 */     this.lastHowl = serverTime;
/*      */   }
/*      */   
/*      */   public static enum QuestState {
/*  928 */     NOT_STATED,  STARTED,  COMPLETE;
/*      */     
/*      */     private QuestState() {} }
/*      */   
/*  932 */   public QuestState getWolfmanQuestState() { return QuestState.values()[this.wolfmanQuestState]; }
/*      */   
/*      */   public void setWolfmanQuestState(QuestState state)
/*      */   {
/*  936 */     this.wolfmanQuestState = state.ordinal();
/*      */   }
/*      */   
/*      */   public int getWolfmanQuestCounter() {
/*  940 */     return this.wolfmanQuestCounter;
/*      */   }
/*      */   
/*      */   public void increaseWolfmanQuestCounter() {
/*  944 */     this.wolfmanQuestCounter += 1;
/*  945 */     if (this.wolfmanQuestCounter > 100) {
/*  946 */       this.wolfmanQuestCounter = 100;
/*      */     }
/*      */   }
/*      */   
/*  950 */   private final List<Long> visitedChunks = new ArrayList();
/*      */   
/*      */   public boolean storeWolfmanQuestChunk(int x, int z) {
/*  953 */     long location = x << 32 | z & 0xFFFFFFFF;
/*  954 */     if (this.visitedChunks.contains(Long.valueOf(location))) {
/*  955 */       return false;
/*      */     }
/*  957 */     this.visitedChunks.add(Long.valueOf(location));
/*      */     
/*      */ 
/*      */ 
/*  961 */     return true;
/*      */   }
/*      */   
/*  964 */   private final List<Long> visitedVampireChunks = new ArrayList();
/*      */   boolean getPlayerData;
/*      */   
/*  967 */   public boolean storeVampireQuestChunk(int x, int z) { long location = x << 32 | z & 0xFFFFFFFF;
/*  968 */     if (this.visitedVampireChunks.contains(Long.valueOf(location))) {
/*  969 */       return false;
/*      */     }
/*  971 */     this.visitedVampireChunks.add(Long.valueOf(location));
/*      */     
/*      */ 
/*      */ 
/*  975 */     return true;
/*      */   }
/*      */   
/*      */   public int getVampireQuestCounter() {
/*  979 */     return this.vampireQuestCounter;
/*      */   }
/*      */   
/*      */   public void increaseVampireQuestCounter() {
/*  983 */     this.vampireQuestCounter += 1;
/*  984 */     if (this.vampireQuestCounter > 10000) {
/*  985 */       this.vampireQuestCounter = 10000;
/*      */     }
/*      */   }
/*      */   
/*      */   public void resetVampireQuestCounter() {
/*  990 */     this.vampireQuestCounter = 0;
/*      */   }
/*      */   
/*      */   boolean resetSleep;
/*      */   public void scheduleSync()
/*      */   {
/*  996 */     this.getPlayerData = true;
/*      */   }
/*      */   
/*      */   public void processSync() {
/* 1000 */     if (this.getPlayerData) {
/* 1001 */       this.getPlayerData = false;
/* 1002 */       for (Object obj : this.player.field_70170_p.field_73010_i) {
/* 1003 */         EntityPlayer otherPlayer = (EntityPlayer)obj;
/* 1004 */         if (otherPlayer != this.player)
/*      */         {
/*      */ 
/* 1007 */           Witchery.packetPipeline.sendTo(new PacketPlayerStyle(otherPlayer), this.player);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   int cachedSky;
/*      */   private Coord mirrorWorldEntryPoint;
/*      */   public void checkSleep(boolean start)
/*      */   {
/* 1017 */     if (start) {
/* 1018 */       if ((isVampire()) && (this.player.field_71083_bS) && (this.player.field_70170_p.func_72935_r())) {
/* 1019 */         this.resetSleep = true;
/* 1020 */         this.cachedSky = this.player.field_70170_p.field_73008_k;
/* 1021 */         this.player.field_70170_p.field_73008_k = 4;
/*      */       }
/*      */     }
/* 1024 */     else if (this.resetSleep) {
/* 1025 */       this.resetSleep = false;
/* 1026 */       this.player.field_70170_p.field_73008_k = this.cachedSky;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean hasVampireBook()
/*      */   {
/* 1033 */     for (ItemStack stack : this.player.field_71071_by.field_70462_a) {
/* 1034 */       if ((stack != null) && (stack.func_77973_b() == Witchery.Items.VAMPIRE_BOOK)) {
/* 1035 */         return stack.func_77960_j() < 9;
/*      */       }
/*      */     }
/* 1038 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMirrorWorldEntryPoint(int x, int y, int z)
/*      */   {
/* 1044 */     this.mirrorWorldEntryPoint = new Coord(x, y, z);
/*      */   }
/*      */   
/*      */   public Coord getMirrorWorldEntryPoint() {
/* 1048 */     return this.mirrorWorldEntryPoint;
/*      */   }
/*      */   
/*      */   public boolean isMirrorWorldEntryPoint(int x, int y, int z) {
/* 1052 */     return (this.mirrorWorldEntryPoint == null) || (this.mirrorWorldEntryPoint.isMatch(x, y, z));
/*      */   }
/*      */   
/* 1055 */   static final long COOLDOWN_ESCAPE_1_TICKS = TimeUtil.minsToTicks(5);
/* 1056 */   static final long COOLDOWN_ESCAPE_2_TICKS = TimeUtil.minsToTicks(60);
/* 1057 */   long mirrorWorldEscapeCooldown1 = Long.MIN_VALUE;
/* 1058 */   long mirrorWorldEscapeCooldown2 = Long.MIN_VALUE;
/*      */   
/*      */   public boolean canEscapeMirrorWorld(int slot) {
/* 1061 */     if (slot == 1)
/* 1062 */       return this.player.field_70170_p.func_82737_E() >= this.mirrorWorldEscapeCooldown1 + COOLDOWN_ESCAPE_1_TICKS;
/* 1063 */     if (slot == 2) {
/* 1064 */       return this.player.field_70170_p.func_82737_E() >= this.mirrorWorldEscapeCooldown2 + COOLDOWN_ESCAPE_2_TICKS;
/*      */     }
/* 1066 */     return false;
/*      */   }
/*      */   
/*      */   public void escapedMirrorWorld(int slot)
/*      */   {
/* 1071 */     if (slot == 1) {
/* 1072 */       this.mirrorWorldEscapeCooldown1 = this.player.field_70170_p.func_82737_E();
/* 1073 */     } else if (slot == 2) {
/* 1074 */       this.mirrorWorldEscapeCooldown2 = this.player.field_70170_p.func_82737_E();
/*      */     }
/*      */   }
/*      */   
/*      */   public long getCooldownSecs(int i) {
/* 1079 */     if (i == 1)
/* 1080 */       return (this.mirrorWorldEscapeCooldown1 + COOLDOWN_ESCAPE_1_TICKS - this.player.field_70170_p.func_82737_E()) / 20L;
/* 1081 */     if (i == 2) {
/* 1082 */       return (this.mirrorWorldEscapeCooldown2 + COOLDOWN_ESCAPE_2_TICKS - this.player.field_70170_p.func_82737_E()) / 20L;
/*      */     }
/* 1084 */     return 0L;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/ExtendedPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */