/*     */ package com.emoniph.witchery.util;
/*     */ 
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.network.PacketSound;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public enum SoundEffect
/*     */ {
/*  13 */   NONE(""), 
/*  14 */   RANDOM_ORB("random.orb"), 
/*  15 */   RANDOM_FIZZ("random.fizz"), 
/*  16 */   NOTE_SNARE("note.snare"), 
/*  17 */   WATER_SPLASH("game.player.swim.splash"), 
/*  18 */   DAMAGE_HIT("damage.hit"), 
/*  19 */   FIREWORKS_BLAST1("fireworks.blast"), 
/*  20 */   WATER_SWIM("game.player.swim"), 
/*  21 */   NOTE_HARP("note.harp"), 
/*  22 */   NOTE_PLING("note.pling"), 
/*  23 */   RANDOM_EXPLODE("random.explode"), 
/*  24 */   RANDOM_POP("random.pop"), 
/*  25 */   DIG_CLOTH("dig.cloth"), 
/*  26 */   MOB_SLIME_BIG("mob.slime.big"), 
/*  27 */   MOB_SLIME_SMALL("mob.slime.small"), 
/*  28 */   MOB_ZOMBIE_DEATH("mob.zombie.death"), 
/*  29 */   MOB_ENDERMEN_PORTAL("mob.endermen.portal"), 
/*  30 */   FIRE_FIRE("fire.fire"), 
/*  31 */   FIRE_IGNITE("fire.ignite"), 
/*  32 */   MOB_GHAST_FIREBALL("mob.ghast.fireball"), 
/*  33 */   MOB_WITHER_SPAWN("mob.wither.spawn"), 
/*  34 */   MOB_HORSE_SKELETON_DEATH("mob.horse.skeleton.death"), 
/*     */   
/*  36 */   RANDOM_SPLASH("witchery:random.splash"), 
/*  37 */   MOB_SILVERFISH_KILL("mob.silverfish.kill"), 
/*  38 */   MOB_ZOMBIE_INFECT("mob.zombie.infect"), 
/*  39 */   MOB_WOLF_DEATH("mob.wolf.death"), 
/*  40 */   MOB_OCELOT_DEATH("mob.ocelot.death"), 
/*  41 */   MOB_ENDERDRAGON_GROWL("mob.enderdragon.growl"), 
/*  42 */   MOB_HORSE_SKELETON_HIT("mob.horse.skeleton.hit"), 
/*  43 */   RANDOM_LEVELUP("random.levelup"), 
/*  44 */   MOB_SPIDER_SAY("mob.spider.say"), 
/*  45 */   MOB_ZOMBIE_SAY("mob.zombie.say"), 
/*  46 */   WITCHERY_RANDOM_THEYCOME("witchery:random.theycome"), 
/*  47 */   MOB_ENDERDRAGON_HIT("mob.enderdragon.hit"), 
/*  48 */   WITCHERY_MOB_BABA_DEATH("witchery:mob.baba.baba_death"), 
/*  49 */   WITCHERY_MOB_BABA_LIVING("witchery:mob.baba.baba_living"), 
/*  50 */   WITCHERY_RANDOM_CLICK("witchery:random.click"), 
/*  51 */   WITCHERY_RANDOM_WINDUP("witchery:random.wind_up"), 
/*  52 */   WITCHERY_RANDOM_LOVED("witchery:random.loved"), 
/*  53 */   MOB_ENDERMAN_IDLE("mob.enderman.idle"), 
/*  54 */   MOB_WITHER_DEATH("mob.wither.death"), 
/*  55 */   RANDOM_BREATH("random.breath"), 
/*  56 */   WITCHERY_MOB_SPECTRE_SPECTRE_HIT("witchery:mob.spectre.spectre_hit"), 
/*  57 */   WITCHERY_MOB_SPECTRE_SPECTRE_SAY("witchery:mob.spectre.spectre_say"), 
/*  58 */   MOB_BLAZE_DEATH("mob.blaze.death"), 
/*  59 */   WITCHERY_MOB_IMP_LAUGH("witchery:mob.imp.laugh"), 
/*  60 */   MOB_GHAST_DEATH("mob.ghast.death"), 
/*  61 */   MOB_CREEPER_DEATH("mob.creeper.death"), 
/*  62 */   WITCHERY_RANDOM_CHALK("witchery:random.chalk"), 
/*  63 */   WITCHERY_MOB_WOLFMAN_HOWL("witchery:mob.wolfman.howl"), 
/*  64 */   WITCHERY_MOB_WOLFMAN_EAT("witchery:mob.wolfman.eat"), 
/*  65 */   WITCHERY_MOB_WOLFMAN_LORD("witchery:mob.wolfman.lord"), 
/*  66 */   WITCHERY_RANDOM_HORN("witchery:random.horn"), 
/*  67 */   WITCHERY_RANDOM_MANTRAP("witchery:random.mantrap"), 
/*  68 */   WITCHERY_MOB_WOLFMAN_TALK("witchery:mob.wolfman.say"), 
/*  69 */   WITCHERY_RANDOM_HYPNOSIS("witchery:random.hypnosis"), 
/*  70 */   WITCHERY_RANDOM_DRINK("witchery:random.drink"), 
/*  71 */   WITCHERY_RANDOM_POOF("witchery:random.poof"), 
/*  72 */   WITCHERY_MOB_LILITH_TALK("witchery:mob.lilith.say"), 
/*  73 */   WITCHERY_RANDOM_SWORD_DRAW("witchery:random.sworddraw"), 
/*  74 */   WITCHERY_RANDOM_SWORD_SHEATHE("witchery:random.swordsheathe"), 
/*  75 */   WITCHERY_MOB_REFLECTION_SPEECH("witchery:mob.reflection.speech");
/*     */   
/*     */   final String sound;
/*     */   
/*     */   private SoundEffect(String sound)
/*     */   {
/*  81 */     this.sound = sound;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  86 */     return this.sound;
/*     */   }
/*     */   
/*     */   public void playAtPlayer(World world, EntityPlayer player) {
/*  90 */     playAtPlayer(world, player, 0.5F);
/*     */   }
/*     */   
/*     */   public void playAtPlayer(World world, EntityPlayer player, float volume) {
/*  94 */     if (!world.field_72995_K) {
/*  95 */       world.func_72956_a(player, this.sound, volume, 0.4F / ((float)world.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */     }
/*     */   }
/*     */   
/*     */   public void playAtPlayer(World world, EntityPlayer player, float volume, float pitch) {
/* 100 */     if (!world.field_72995_K) {
/* 101 */       world.func_72956_a(player, this.sound, volume, pitch);
/*     */     }
/*     */   }
/*     */   
/*     */   public void playAt(EntityLiving entity) {
/* 106 */     playAt(entity, 0.5F);
/*     */   }
/*     */   
/*     */   public void playAt(EntityLiving entity, float volume) {
/* 110 */     playAt(entity, volume, 0.4F / ((float)entity.field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */   }
/*     */   
/*     */   public void playAt(EntityLiving entity, float volume, float pitch) {
/* 114 */     if (!entity.field_70170_p.field_72995_K) {
/* 115 */       entity.field_70170_p.func_72956_a(entity, this.sound, volume, pitch);
/*     */     }
/*     */   }
/*     */   
/*     */   public void playAt(TileEntity tile) {
/* 120 */     playAt(tile, 0.5F);
/*     */   }
/*     */   
/*     */   public void playAt(TileEntity tile, float volume) {
/* 124 */     playAt(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, volume);
/*     */   }
/*     */   
/*     */   public void playAt(World world, double x, double y, double z) {
/* 128 */     playAt(world, x, y, z, 0.5F);
/*     */   }
/*     */   
/*     */   public void playAt(World world, double x, double y, double z, float volume) {
/* 132 */     playAt(world, x, y, z, volume, 0.4F / ((float)world.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */   }
/*     */   
/*     */   public void playAt(World world, double x, double y, double z, float volume, float pitch) {
/* 136 */     if (!world.field_72995_K) {
/* 137 */       world.func_72908_a(x, y, z, this.sound, volume, pitch);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 142 */   public void playOnlyTo(EntityPlayer player) { playOnlyTo(player, -1.0F, -1.0F); }
/*     */   
/*     */   public void playOnlyTo(EntityPlayer player, float volume, float pitch) {
/* 145 */     if (this != NONE) {
/* 146 */       com.emoniph.witchery.Witchery.packetPipeline.sendTo(new PacketSound(this, player, volume, pitch), player);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/SoundEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */