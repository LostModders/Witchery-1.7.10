/*     */ package com.emoniph.witchery.common;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*     */ import com.emoniph.witchery.item.ItemHunterClothes;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.network.PacketPushTarget;
/*     */ import com.emoniph.witchery.network.PacketSyncEntitySize;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import com.emoniph.witchery.util.EntitySizeInfo;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import com.emoniph.witchery.util.TransformCreature;
/*     */ import com.google.common.collect.Multimap;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.FoodStats;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*     */ 
/*     */ public class Shapeshift
/*     */ {
/*  56 */   public static final Shapeshift INSTANCE = new Shapeshift();
/*     */   
/*     */   public static class StatBoost {
/*     */     public final double jump;
/*     */     public final double leap;
/*     */     public final int health;
/*     */     public final float damage;
/*     */     public final float resistance;
/*     */     public final float speed;
/*     */     public int fall;
/*     */     public final float damageCap;
/*     */     public boolean flying;
/*     */     
/*     */     public StatBoost(float damage) {
/*  70 */       this.jump = 0.0D;
/*  71 */       this.leap = 0.0D;
/*  72 */       this.health = 0;
/*  73 */       this.damage = damage;
/*  74 */       this.resistance = 0.0F;
/*  75 */       this.speed = 0.0F;
/*  76 */       this.fall = 0;
/*  77 */       this.damageCap = 0.0F;
/*     */     }
/*     */     
/*     */     public StatBoost(float speed, double jump, double leap, int health, float damage, float resistance, int fall, float damageCap)
/*     */     {
/*  82 */       this.jump = jump;
/*  83 */       this.leap = leap;
/*  84 */       this.health = health;
/*  85 */       this.damage = damage;
/*  86 */       this.resistance = resistance;
/*  87 */       this.speed = speed;
/*  88 */       this.fall = fall;
/*  89 */       this.damageCap = damageCap;
/*     */     }
/*     */     
/*     */     public StatBoost setFlying(boolean active) {
/*  93 */       this.flying = active;
/*  94 */       this.fall = -1;
/*  95 */       return this;
/*     */     }
/*     */   }
/*     */   
/*  99 */   public final StatBoost[] boostWolfman = { new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 4.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 4.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 3.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 3.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 3.0F), new StatBoost(0.2F, 0.20000000298023224D, 0.20000000298023224D, 20, 4.0F, 3.0F, 3, 2.0F), new StatBoost(0.2F, 0.30000001192092896D, 0.20000000298023224D, 20, 4.0F, 3.0F, 4, 2.0F), new StatBoost(0.4F, 0.4000000059604645D, 0.4000000059604645D, 20, 5.0F, 4.0F, 5, 2.0F), new StatBoost(0.4F, 0.5D, 0.4000000059604645D, 30, 6.0F, 4.0F, 6, 2.0F), new StatBoost(0.5F, 0.6000000238418579D, 0.6000000238418579D, 40, 7.0F, 5.0F, 7, 2.0F), new StatBoost(0.5F, 0.6000000238418579D, 0.6000000238418579D, 40, 7.0F, 5.0F, 7, 2.0F) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 109 */   public final StatBoost[] boostWolf = { new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 4.0F), new StatBoost(0.5F, 0.20000000298023224D, 0.20000000298023224D, 0, 1.0F, 0.0F, 2, 4.0F), new StatBoost(0.5F, 0.20000000298023224D, 0.20000000298023224D, 0, 1.0F, 0.0F, 2, 3.0F), new StatBoost(0.75F, 0.20000000298023224D, 0.30000001192092896D, 0, 2.0F, 0.0F, 2, 3.0F), new StatBoost(0.75F, 0.20000000298023224D, 0.4000000059604645D, 0, 2.0F, 0.0F, 3, 3.0F), new StatBoost(0.75F, 0.20000000298023224D, 0.5D, 0, 2.0F, 0.0F, 3, 2.0F), new StatBoost(1.0F, 0.20000000298023224D, 0.6000000238418579D, 0, 2.0F, 1.0F, 3, 2.0F), new StatBoost(1.25F, 0.30000001192092896D, 0.699999988079071D, 4, 2.0F, 1.0F, 4, 2.0F), new StatBoost(1.5F, 0.30000001192092896D, 0.800000011920929D, 8, 3.0F, 2.0F, 4, 2.0F), new StatBoost(1.75F, 0.30000001192092896D, 0.8999999761581421D, 12, 3.0F, 3.0F, 5, 2.0F), new StatBoost(1.75F, 0.30000001192092896D, 1.0D, 12, 3.0F, 3.0F, 5, 2.0F) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 118 */   public final StatBoost[] boostVampire = { new StatBoost(0.0F), new StatBoost(1.0F), new StatBoost(1.0F), new StatBoost(1.0F), new StatBoost(2.0F), new StatBoost(2.0F), new StatBoost(2.0F), new StatBoost(3.0F), new StatBoost(3.0F), new StatBoost(3.0F), new StatBoost(3.0F) };
/*     */   
/*     */ 
/*     */ 
/* 122 */   public final StatBoost[] boostBat = { new StatBoost(0.0F), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 129 */   public static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("10536417-7AA6-4033-A598-8E934CA77D98"), "witcheryWolfSpeed", 0.5D, 2);
/*     */   
/* 131 */   public static final AttributeModifier DAMAGE_MODIFIER = new AttributeModifier(UUID.fromString("46C5271C-193B-4D41-9CAB-D071AAEE9D4A"), "witcheryWolfDamage", 6.0D, 2);
/*     */   
/* 133 */   public static final AttributeModifier HEALTH_MODIFIER = new AttributeModifier(UUID.fromString("615920F9-6675-4779-8B18-6A62A3671E94"), "witcheryWolfHealth", 40.0D, 0);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Field fieldExperienceValue;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initCurrentShift(EntityPlayer player)
/*     */   {
/* 150 */     if (!player.field_70170_p.field_72995_K) {
/* 151 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 152 */       EntitySizeInfo sizeInfo = new EntitySizeInfo(player);
/* 153 */       com.emoniph.witchery.brewing.potions.PotionResizing.setEntitySize(player, sizeInfo.defaultWidth, sizeInfo.defaultHeight);
/* 154 */       player.field_70138_W = sizeInfo.stepSize;
/* 155 */       player.eyeHeight = sizeInfo.eyeHeight;
/*     */       
/* 157 */       BaseAttributeMap playerAttributes = player.func_110140_aT();
/* 158 */       StatBoost boost = getStatBoost(player, playerEx);
/*     */       
/* 160 */       if (boost != null) {
/* 161 */         applyModifier(SharedMonsterAttributes.field_111263_d, SPEED_MODIFIER, boost.speed, playerAttributes);
/* 162 */         applyModifier(SharedMonsterAttributes.field_111264_e, DAMAGE_MODIFIER, boost.damage, playerAttributes);
/*     */         
/* 164 */         applyModifier(SharedMonsterAttributes.field_111267_a, HEALTH_MODIFIER, boost.health, playerAttributes);
/*     */       } else {
/* 166 */         removeModifier(SharedMonsterAttributes.field_111263_d, SPEED_MODIFIER, playerAttributes);
/* 167 */         removeModifier(SharedMonsterAttributes.field_111264_e, DAMAGE_MODIFIER, playerAttributes);
/* 168 */         removeModifier(SharedMonsterAttributes.field_111267_a, HEALTH_MODIFIER, playerAttributes);
/*     */       }
/*     */       
/* 171 */       if (!player.field_71075_bZ.field_75098_d) {
/* 172 */         player.field_71075_bZ.field_75101_c = ((boost != null) && (boost.flying));
/* 173 */         if ((!player.field_71075_bZ.field_75101_c) && (player.field_71075_bZ.field_75100_b)) {
/* 174 */           player.field_71075_bZ.field_75100_b = false;
/* 175 */         } else if (player.field_71075_bZ.field_75101_c) {
/* 176 */           player.field_71075_bZ.field_75100_b = true;
/*     */         }
/*     */         
/* 179 */         player.func_71016_p();
/*     */       }
/*     */       
/* 182 */       Witchery.packetPipeline.sendToAll(new PacketSyncEntitySize(player));
/* 183 */       Witchery.packetPipeline.sendTo(new PacketSyncEntitySize(player), player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void updatePlayerState(EntityPlayer player, ExtendedPlayer playerEx) {
/* 188 */     if (playerEx.getCreatureType() == TransformCreature.BAT) {
/* 189 */       if (player.field_71075_bZ.field_75100_b) {
/* 190 */         player.field_70143_R = 0.0F;
/*     */       }
/*     */       
/* 193 */       if ((!player.field_71075_bZ.field_75101_c) && (!player.field_71075_bZ.field_75098_d)) {
/* 194 */         player.field_71075_bZ.field_75101_c = true;
/* 195 */         player.func_71016_p();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public float updateFallState(EntityPlayer player, float distance) {
/* 201 */     ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 202 */     StatBoost boost = getStatBoost(player, playerEx);
/* 203 */     if (boost != null) {
/* 204 */       if (boost.fall == -1) {
/* 205 */         return 0.0F;
/*     */       }
/* 207 */       return Math.max(0.0F, distance - boost.fall);
/*     */     }
/*     */     
/* 210 */     return distance;
/*     */   }
/*     */   
/*     */   public float getResistance(EntityPlayer player, ExtendedPlayer playerEx)
/*     */   {
/* 215 */     StatBoost boost = getStatBoost(player, playerEx);
/* 216 */     if (boost != null) {
/* 217 */       return boost.resistance;
/*     */     }
/* 219 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public float getDamageCap(EntityPlayer player, ExtendedPlayer playerEx)
/*     */   {
/* 224 */     StatBoost boost = getStatBoost(player, playerEx);
/* 225 */     if (boost != null) {
/* 226 */       return boost.damageCap;
/*     */     }
/* 228 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void updateJump(EntityPlayer player)
/*     */   {
/* 233 */     ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 234 */     StatBoost boost = getStatBoost(player, playerEx);
/* 235 */     if (boost != null) {
/* 236 */       player.field_70181_x += boost.jump;
/* 237 */       if (player.func_70051_ag()) {
/* 238 */         float f = player.field_70177_z * 0.017453292F;
/* 239 */         player.field_70159_w -= MathHelper.func_76126_a(f) * boost.leap;
/* 240 */         player.field_70179_y += MathHelper.func_76134_b(f) * boost.leap;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateChargeDamage(LivingHurtEvent event, EntityPlayer player, ExtendedPlayer playerEx) {
/* 246 */     if (isWolfAnimalForm(playerEx)) {
/* 247 */       if (itemHasDamageAttribute(player.func_70694_bm())) {
/* 248 */         event.ammount = 2.0F;
/*     */       } else {
/* 250 */         StatBoost boost = getStatBoost(player, playerEx);
/* 251 */         if ((boost != null) && (player.func_70051_ag())) {
/* 252 */           event.ammount += boost.damage;
/*     */         }
/*     */       }
/*     */     }
/* 256 */     if ((playerEx.getVampireLevel() >= 3) && (playerEx.getCreatureType() == TransformCreature.NONE) && (player.func_70093_af()))
/*     */     {
/*     */ 
/* 259 */       double ACCELERATION = 3.0D;
/* 260 */       Vec3 look = player.func_70040_Z();
/* 261 */       double motionX = look.field_72450_a * 0.6D * 3.0D;
/* 262 */       double motionY = 0.8999999999999999D;
/* 263 */       double motionZ = look.field_72449_c * 0.6D * 3.0D;
/* 264 */       if ((event.entityLiving instanceof EntityPlayer)) {
/* 265 */         EntityPlayer targetPlayer = (EntityPlayer)event.entityLiving;
/* 266 */         Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, 0.8999999999999999D, motionZ), targetPlayer);
/*     */       } else {
/* 268 */         event.entityLiving.field_70159_w = motionX;
/* 269 */         event.entityLiving.field_70181_x = 0.8999999999999999D;
/* 270 */         event.entityLiving.field_70179_y = motionZ;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean itemHasDamageAttribute(ItemStack item) {
/* 276 */     if (item == null) {
/* 277 */       return false;
/*     */     }
/*     */     
/* 280 */     Multimap modifiers = item.func_111283_C();
/* 281 */     if (modifiers == null) {
/* 282 */       return false;
/*     */     }
/*     */     
/* 285 */     boolean hasDamage = modifiers.containsKey(SharedMonsterAttributes.field_111264_e.func_111108_a());
/*     */     
/* 287 */     return hasDamage;
/*     */   }
/*     */   
/*     */   public void rendArmor(EntityLivingBase victim, EntityPlayer player, ExtendedPlayer playerEx) {
/* 291 */     if ((playerEx.getCreatureType() == TransformCreature.WOLFMAN) && (playerEx.getWerewolfLevel() >= 9)) {
/* 292 */       int slot = 1 + victim.field_70170_p.field_73012_v.nextInt(4);
/* 293 */       ItemStack armor = victim.func_71124_b(slot);
/* 294 */       if (armor != null) {
/* 295 */         boolean ripOffArmor = !armor.func_77984_f();
/* 296 */         if (!ripOffArmor) {
/* 297 */           int damage = armor.func_77960_j();
/* 298 */           int rendAmount = (int)Math.ceil(armor.func_77958_k() * 0.25F);
/* 299 */           armor.func_77972_a(rendAmount, player);
/* 300 */           if (((victim instanceof EntityPlayer)) && ((armor.func_77973_b() instanceof ItemArmor))) {
/* 301 */             ItemArmor armorItem = (ItemArmor)armor.func_77973_b();
/* 302 */             armorItem.onArmorTick(victim.field_70170_p, (EntityPlayer)victim, armor);
/*     */           }
/* 304 */           ripOffArmor = armor.func_77960_j() <= damage;
/*     */         }
/*     */         
/*     */ 
/* 308 */         if ((ripOffArmor) && ((victim instanceof EntityPlayer))) {
/* 309 */           victim.func_70062_b(slot, null);
/* 310 */           EntityItem droppedItem = victim.func_70099_a(armor, 1.0F);
/* 311 */           if (droppedItem != null) {
/* 312 */             droppedItem.field_145804_b = TimeUtil.secsToTicks(5);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void processCreatureKilled(LivingDeathEvent event, EntityPlayer attacker, ExtendedPlayer playerEx) {
/* 320 */     if ((isWolfAnimalForm(playerEx)) && (playerEx.getWerewolfLevel() >= 4) && (!CreatureUtil.isUndead(event.entityLiving)))
/*     */     {
/* 322 */       ParticleEffect.REDDUST.send(attacker.field_70170_p.field_73012_v.nextInt(3) == 0 ? SoundEffect.WITCHERY_MOB_WOLFMAN_EAT : SoundEffect.NONE, event.entityLiving, 1.0D, 2.0D, 16);
/*     */       
/*     */ 
/* 325 */       attacker.func_71024_bL().func_75122_a(8, 0.8F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void processDigging(BlockEvent.HarvestDropsEvent event, EntityPlayer player, ExtendedPlayer playerEx) {
/* 330 */     if ((playerEx.getCreatureType() == TransformCreature.WOLF) && (playerEx.getWerewolfLevel() >= 3) && 
/* 331 */       (event.drops.size() == 1) && (event.drops.get(0) != null) && (((ItemStack)event.drops.get(0)).func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150346_d)))
/*     */     {
/* 333 */       long lastFind = playerEx.getLastBoneFind();
/* 334 */       long serverTime = MinecraftServer.func_130071_aq();
/* 335 */       if ((lastFind + TimeUtil.secsToMillisecs(60) < serverTime) && 
/* 336 */         (player.field_70170_p.field_73012_v.nextInt(20) == 0)) {
/* 337 */         playerEx.setLastBoneFind(serverTime);
/* 338 */         event.drops.add(new ItemStack(net.minecraft.init.Items.field_151103_aS, player.field_70170_p.field_73012_v.nextInt(5) == 0 ? 2 : 1));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void checkForHowling(EntityPlayer player, ExtendedPlayer playerEx)
/*     */   {
/* 348 */     if ((playerEx.getWerewolfLevel() == 6) && (isWolfAnimalForm(playerEx)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED) && (!player.field_70170_p.func_72935_r()))
/*     */     {
/* 350 */       int x = MathHelper.func_76128_c(player.field_70165_t) >> 4;
/* 351 */       int z = MathHelper.func_76128_c(player.field_70161_v) >> 4;
/* 352 */       SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAtPlayer(player.field_70170_p, player, 1.0F);
/* 353 */       if (playerEx.storeWolfmanQuestChunk(x, z)) {
/* 354 */         playerEx.increaseWolfmanQuestCounter();
/*     */       } else {
/* 356 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.werewolf.chunkvisited", new Object[0]);
/*     */       }
/* 358 */     } else if ((playerEx.getCreatureType() == TransformCreature.WOLF) && (playerEx.getWerewolfLevel() >= 8)) {
/* 359 */       long lastHowl = playerEx.getLastHowl();
/* 360 */       long serverTime = MinecraftServer.func_130071_aq();
/* 361 */       if ((player.field_71075_bZ.field_75098_d) || (lastHowl + TimeUtil.secsToMillisecs(60) < serverTime)) {
/* 362 */         SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAtPlayer(player.field_70170_p, player, 1.0F);
/* 363 */         playerEx.setLastHowl(serverTime);
/*     */         
/* 365 */         for (int i = 0; i < 2 + player.field_70170_p.field_73012_v.nextInt(playerEx.getWerewolfLevel() - 7); i++) {
/* 366 */           EntityCreature creature = InfusionInfernal.spawnCreature(player.field_70170_p, EntityWolf.class, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, player.func_110144_aD(), 1, 6, ParticleEffect.SMOKE, SoundEffect.NONE);
/*     */           
/*     */ 
/* 369 */           if (creature != null) {
/* 370 */             creature.func_70690_d(new PotionEffect(Witchery.Potions.MORTAL_COIL.field_76415_H, TimeUtil.secsToTicks(10)));
/*     */             
/* 372 */             EntityWolf wolf = (EntityWolf)creature;
/* 373 */             wolf.func_70903_f(true);
/* 374 */             wolf.func_152115_b(player.func_110124_au().toString());
/* 375 */             if (fieldExperienceValue == null) {
/* 376 */               fieldExperienceValue = ReflectionHelper.findField(EntityLiving.class, new String[] { "experienceValue", "field_70728_aV", "aV" });
/*     */             }
/*     */             try
/*     */             {
/* 380 */               if (fieldExperienceValue != null) {
/* 381 */                 fieldExperienceValue.set(wolf, Integer.valueOf(0));
/*     */               }
/*     */             }
/*     */             catch (IllegalAccessException ex) {}
/* 385 */             com.emoniph.witchery.util.EntityUtil.setNoDrops(wolf);
/*     */           }
/*     */         }
/*     */       } else {
/* 389 */         SoundEffect.NOTE_SNARE.playAtPlayer(player.field_70170_p, player);
/*     */       }
/* 391 */     } else if ((playerEx.getCreatureType() == TransformCreature.WOLFMAN) && (playerEx.getWerewolfLevel() >= 7)) {
/* 392 */       long lastHowl = playerEx.getLastHowl();
/* 393 */       long serverTime = MinecraftServer.func_130071_aq();
/* 394 */       if ((player.field_71075_bZ.field_75098_d) || (lastHowl + TimeUtil.secsToMillisecs(60) < serverTime)) {
/* 395 */         SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAtPlayer(player.field_70170_p, player, 1.0F);
/* 396 */         playerEx.setLastHowl(serverTime);
/*     */         
/* 398 */         double radius = 16.0D;
/* 399 */         java.util.List<EntityLivingBase> entities = player.field_70170_p.func_72872_a(EntityLivingBase.class, player.field_70121_D.func_72314_b(radius, radius, radius));
/*     */         
/* 401 */         for (EntityLivingBase entity : entities) {
/* 402 */           if ((!CreatureUtil.isWerewolf(entity, true)) && (!CreatureUtil.isVampire(entity))) {
/* 403 */             entity.func_70690_d(new PotionEffect(Witchery.Potions.PARALYSED.field_76415_H, TimeUtil.secsToTicks(4 + player.field_70170_p.field_73012_v.nextInt(playerEx.getWerewolfLevel() - 6)), 3));
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 408 */         SoundEffect.NOTE_SNARE.playAtPlayer(player.field_70170_p, player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void processWolfInfection(EntityLivingBase entityLiving, EntityPlayer attackingPlayer, ExtendedPlayer playerEx, float health)
/*     */   {
/* 415 */     if ((playerEx.getWerewolfLevel() >= 10) && (isWolfAnimalForm(playerEx))) {
/* 416 */       if ((entityLiving instanceof EntityVillager)) {
/* 417 */         if ((health < entityLiving.func_110138_aP() * 0.25F) && (health > 0.0F) && (entityLiving.field_70170_p.field_73012_v.nextInt(4) == 1))
/*     */         {
/* 419 */           EntityVillager villager = (EntityVillager)entityLiving;
/* 420 */           EntityWolfman.convertToVillager(villager, villager.func_70946_n(), false, villager.field_70956_bz, villager.field_70963_i);
/*     */         }
/*     */       }
/* 423 */       else if (((entityLiving instanceof EntityPlayer)) && 
/* 424 */         (Config.instance().allowPlayerToPlayerWolfInfection)) {
/* 425 */         EntityPlayer victim = (EntityPlayer)entityLiving;
/* 426 */         ExtendedPlayer victimEx = ExtendedPlayer.get(victim);
/* 427 */         if ((health < entityLiving.func_110138_aP() * 0.25F) && (health > 0.0F) && (!ItemHunterClothes.isWolfProtectionActive(entityLiving)) && (entityLiving.field_70170_p.field_73012_v.nextInt(4) == 1))
/*     */         {
/*     */ 
/* 430 */           if ((Config.instance().allowVampireWolfHybrids) || (!playerEx.isVampire()))
/*     */           {
/* 432 */             if (victimEx.getWerewolfLevel() == 0) {
/* 433 */               victimEx.setWerewolfLevel(1);
/* 434 */               ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, victim, "witchery.werewolf.infection", new Object[0]);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void processWolfInfection(EntityLivingBase entityLiving, EntityWolfman attackingEntity, float health)
/*     */   {
/* 444 */     if (attackingEntity.isInfectious()) {
/* 445 */       if ((entityLiving instanceof EntityVillager)) {
/* 446 */         if (health < entityLiving.func_110138_aP() * 0.25F) {
/* 447 */           EntityVillager villager = (EntityVillager)entityLiving;
/* 448 */           EntityWolfman.convertToVillager(villager, villager.func_70946_n(), false, villager.field_70956_bz, villager.field_70963_i);
/*     */         }
/*     */       }
/* 451 */       else if ((entityLiving instanceof EntityPlayer)) {
/* 452 */         EntityPlayer victim = (EntityPlayer)entityLiving;
/* 453 */         ExtendedPlayer victimEx = ExtendedPlayer.get(victim);
/* 454 */         if ((Config.instance().allowVampireWolfHybrids) || (!victimEx.isVampire()))
/*     */         {
/* 456 */           if (victimEx.getWerewolfLevel() == 0) {
/* 457 */             victimEx.setWerewolfLevel(1);
/* 458 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, victim, "witchery.werewolf.infection", new Object[0]);
/*     */           } }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isAnimalForm(EntityPlayer player) {
/* 465 */     return isWolfAnimalForm(ExtendedPlayer.get(player));
/*     */   }
/*     */   
/*     */   public boolean isWolfAnimalForm(ExtendedPlayer playerEx) {
/* 469 */     return (playerEx.getCreatureType() == TransformCreature.WOLFMAN) || (playerEx.getCreatureType() == TransformCreature.WOLF);
/*     */   }
/*     */   
/*     */   public boolean isWolfmanAllowed(ExtendedPlayer playerEx)
/*     */   {
/* 474 */     return playerEx.getWerewolfLevel() >= 5;
/*     */   }
/*     */   
/*     */   public boolean canControlTransform(ExtendedPlayer playerEx) {
/* 478 */     return playerEx.getWerewolfLevel() >= 2;
/*     */   }
/*     */   
/*     */   public StatBoost getStatBoost(EntityPlayer player, ExtendedPlayer playerEx) {
/* 482 */     TransformCreature creature = playerEx.getCreatureType();
/* 483 */     switch (creature) {
/*     */     case WOLF: 
/* 485 */       return this.boostWolf[playerEx.getWerewolfLevel()];
/*     */     case WOLFMAN: 
/* 487 */       return this.boostWolfman[playerEx.getWerewolfLevel()];
/*     */     case BAT: 
/* 489 */       return this.boostBat[playerEx.getVampireLevel()];
/*     */     }
/* 491 */     return playerEx.isVampire() ? this.boostVampire[playerEx.getVampireLevel()] : null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void applyModifier(IAttribute attribute, AttributeModifier modifier, double modification, BaseAttributeMap playerAttributes)
/*     */   {
/* 497 */     IAttributeInstance attributeInstance = playerAttributes.func_111151_a(attribute);
/* 498 */     AttributeModifier speedModifier = new AttributeModifier(modifier.func_111167_a(), modifier.func_111166_b(), modification, modifier.func_111169_c());
/*     */     
/* 500 */     attributeInstance.func_111124_b(speedModifier);
/* 501 */     attributeInstance.func_111121_a(speedModifier);
/*     */   }
/*     */   
/*     */   public void removeModifier(IAttribute attribute, AttributeModifier modifier, BaseAttributeMap playerAttributes) {
/* 505 */     IAttributeInstance attributeInstance = playerAttributes.func_111151_a(attribute);
/* 506 */     attributeInstance.func_111124_b(modifier);
/*     */   }
/*     */   
/*     */   public void shiftTo(EntityPlayer player, TransformCreature creature) {
/* 510 */     ExtendedPlayer.get(player).setCreatureType(creature);
/* 511 */     initCurrentShift(player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/Shapeshift.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */