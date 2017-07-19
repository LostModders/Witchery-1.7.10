/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.brewing.potions.PotionBase;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.RandomCollection;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityLilith extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IHandleDT
/*     */ {
/*     */   private int attackTimer;
/*     */   
/*     */   public EntityLilith(World world)
/*     */   {
/*  63 */     super(world);
/*     */     
/*  65 */     func_70105_a(0.8F, 2.5F);
/*  66 */     this.field_70178_ae = true;
/*  67 */     func_70661_as().func_75491_a(true);
/*  68 */     func_70661_as().func_75495_e(true);
/*  69 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  70 */     this.field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 20, 60, 30.0F));
/*  71 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
/*  72 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  73 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  74 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  75 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  76 */     this.field_70728_aV = 60;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  81 */     super.func_70088_a();
/*  82 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*  83 */     this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
/*  84 */     this.field_70180_af.func_75682_a(20, new Integer(0));
/*  85 */     this.field_70180_af.func_75682_a(21, new Integer(0));
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  90 */     super.func_110147_ax();
/*  91 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
/*  92 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
/*  93 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
/*  94 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/*  99 */     return 8;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 108 */     if (func_94056_bM()) {
/* 109 */       return func_94057_bL();
/*     */     }
/* 111 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.lilith.name");
/*     */   }
/*     */   
/*     */ 
/* 115 */   boolean isFriendly = false;
/*     */   int weaknessTimer;
/*     */   
/*     */   public boolean func_70650_aV() {
/* 119 */     return !this.isFriendly;
/*     */   }
/*     */   
/*     */   protected Entity func_70782_k()
/*     */   {
/* 124 */     return this.isFriendly ? null : super.func_70782_k();
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 129 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */   public int getInvulnerableStartTicks() {
/* 133 */     return this.field_70180_af.func_75679_c(20);
/*     */   }
/*     */   
/*     */   public void setInvulnerableStartTicks(int par1) {
/* 137 */     this.field_70180_af.func_75692_b(20, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public int getLifetime() {
/* 141 */     return this.field_70180_af.func_75679_c(21);
/*     */   }
/*     */   
/*     */   public void setLifetime(int par1) {
/* 145 */     this.field_70180_af.func_75692_b(21, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public void setInvulnerableStart() {
/* 149 */     setInvulnerableStartTicks(150);
/* 150 */     func_70606_j(func_110138_aP() / 4.0F);
/*     */   }
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/* 155 */     if (getInvulnerableStartTicks() > 0) {
/* 156 */       int i = getInvulnerableStartTicks() - 1;
/*     */       
/* 158 */       if (i <= 0) {
/* 159 */         this.field_70170_p.func_82739_e(1013, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */       }
/*     */       
/* 162 */       setInvulnerableStartTicks(i);
/*     */       
/* 164 */       if (this.field_70173_aa % 10 == 0) {
/* 165 */         func_70691_i(func_110138_aP() * 0.75F / 15.0F);
/*     */       }
/*     */     } else {
/* 168 */       super.func_70619_bc();
/*     */       
/* 170 */       if ((!this.field_70170_p.field_72995_K) && (!func_70644_a(Witchery.Potions.RESIZING))) {
/* 171 */         func_70690_d(new PotionEffect(Witchery.Potions.RESIZING.field_76415_H, 10000, 3, true));
/*     */       }
/* 173 */       setLifetime(getLifetime() + 1);
/*     */       
/*     */ 
/* 176 */       if (this.field_70173_aa % 20 == 0) {
/* 177 */         if (this.weaknessTimer > 0) {
/* 178 */           this.weaknessTimer -= 1;
/*     */         }
/* 180 */         if ((!func_70644_a(Witchery.Potions.CHILLED)) && (!func_70644_a(Potion.field_76437_t)) && (this.weaknessTimer == 0))
/*     */         {
/* 182 */           func_70691_i(5.0F);
/*     */         }
/* 184 */         else if (this.weaknessTimer == 0) {
/* 185 */           func_70691_i(1.0F);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 191 */       if ((this.field_70173_aa % 20 == 0) && (this.field_70170_p.field_73012_v.nextInt(5) == 0) && ((func_70638_az() != null) || (func_110144_aD() != null)))
/*     */       {
/* 193 */         if (!this.field_70170_p.field_72995_K)
/*     */         {
/* 195 */           int R = 32;
/* 196 */           double RY = 16.0D;
/* 197 */           double RSQ = 1024.0D;
/*     */           
/* 199 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 32.0D, this.field_70163_u - 16.0D, this.field_70161_v - 32.0D, this.field_70165_t + 32.0D, this.field_70163_u + 16.0D, this.field_70161_v + 32.0D);
/*     */           
/* 201 */           List<EntityPlayer> players = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds);
/* 202 */           for (EntityPlayer player : players) {
/* 203 */             if (player.func_70644_a(Potion.field_76426_n)) {
/* 204 */               player.func_82170_o(Potion.field_76426_n.field_76415_H);
/*     */             }
/* 206 */             if (this.field_70170_p.field_73012_v.nextInt(2) == 0) {
/* 207 */               SoundEffect.MOB_ENDERDRAGON_GROWL.playAtPlayer(this.field_70170_p, player);
/* 208 */               for (int i = 0; i < 3 + this.field_70146_Z.nextInt(4); i++) {
/* 209 */                 EntitySmallFireball fireball = new EntitySmallFireball(this.field_70170_p, player.field_70165_t + this.field_70146_Z.nextDouble() * 4.0D - 2.0D, player.field_70163_u + this.field_70146_Z.nextInt(2) + 14.0D, player.field_70161_v + this.field_70146_Z.nextDouble() * 4.0D - 2.0D, 0.0D, -0.2D, 0.0D);
/*     */                 
/*     */ 
/* 212 */                 this.field_70170_p.func_72838_d(fireball);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 223 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 228 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 233 */     super.func_70636_d();
/*     */     
/* 235 */     if (this.attackTimer > 0) {
/* 236 */       this.attackTimer -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 244 */     boolean immune = false;
/* 245 */     if (immune) {
/* 246 */       return false;
/*     */     }
/* 248 */     if ((source.func_76346_g() != null) && 
/* 249 */       ((source.func_76364_f() instanceof EntityLargeFireball)) && ((source.func_76346_g() instanceof EntityPlayer)))
/*     */     {
/* 251 */       this.weaknessTimer = 10;
/*     */     }
/*     */     
/* 254 */     return super.func_70097_a(source, Math.min(damage, 12.0F));
/*     */   }
/*     */   
/*     */ 
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 260 */     return 12.0F;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 265 */     super.func_70014_b(nbtRoot);
/* 266 */     nbtRoot.func_74768_a("Invul", getInvulnerableStartTicks());
/* 267 */     nbtRoot.func_74772_a("Lifetime", getLifetime());
/* 268 */     nbtRoot.func_74757_a("Friendly", this.isFriendly);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 273 */     super.func_70037_a(nbtRoot);
/* 274 */     setInvulnerableStartTicks(nbtRoot.func_74762_e("Invul"));
/* 275 */     setLifetime(nbtRoot.func_74762_e("Lifetime"));
/* 276 */     this.isFriendly = nbtRoot.func_74767_n("Friendly");
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 281 */     this.attackTimer = 10;
/* 282 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 283 */     boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), 7 + this.field_70146_Z.nextInt(15));
/*     */     
/* 285 */     if (flag) {
/* 286 */       par1Entity.field_70181_x += 0.4000000059604645D;
/*     */     }
/*     */     
/* 289 */     func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/* 290 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 296 */     if (par1 == 4) {
/* 297 */       this.attackTimer = 10;
/* 298 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     } else {
/* 300 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 306 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 311 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 316 */     return this.isFriendly ? null : "witchery:mob.lilith.say";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 321 */     return "witchery:mob.lilith.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 326 */     return this.isFriendly ? "witchery:mob.lilith.hit" : "witchery:mob.lilith.death";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean par1, int par2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70645_a(DamageSource source)
/*     */   {
/* 349 */     if (!this.field_70170_p.field_72995_K) {
/* 350 */       this.field_70128_L = false;
/* 351 */       ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/*     */       
/* 353 */       func_70606_j(func_110138_aP());
/* 354 */       this.isFriendly = true;
/* 355 */       List<Potion> effectsToRemove = new ArrayList();
/* 356 */       Collection<PotionEffect> effects = func_70651_bq();
/* 357 */       for (PotionEffect effect : effects) {
/* 358 */         Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/* 359 */         if (PotionBase.isCurable(potion)) {
/* 360 */           effectsToRemove.add(potion);
/*     */         }
/*     */       }
/* 363 */       for (Potion potion : effectsToRemove) {
/* 364 */         func_82170_o(potion.field_76415_H);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 370 */       EntityPlayer player = null;
/* 371 */       if ((source != null) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
/* 372 */         player = (EntityPlayer)source.func_76346_g();
/* 373 */         if ((player.field_71093_bK != this.field_71093_bK) || (player.field_70128_L) || (player.func_70068_e(this) > 4096.0D)) {
/* 374 */           player = null;
/*     */         }
/*     */       }
/*     */       double distSq;
/* 378 */       if (player == null) {
/* 379 */         int R = 32;
/* 380 */         double RY = 16.0D;
/* 381 */         double RSQ = 1024.0D;
/*     */         
/* 383 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 32.0D, this.field_70163_u - 16.0D, this.field_70161_v - 32.0D, this.field_70165_t + 32.0D, this.field_70163_u + 16.0D, this.field_70161_v + 32.0D);
/*     */         
/* 385 */         List<EntityPlayer> players = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds);
/* 386 */         distSq = 0.0D;
/* 387 */         for (EntityPlayer player2 : players) {
/* 388 */           if (player == null) {
/* 389 */             distSq = func_70068_e(player2);
/* 390 */             player = player2;
/*     */           } else {
/* 392 */             double newDist = func_70068_e(player2);
/* 393 */             if (newDist < distSq) {
/* 394 */               distSq = newDist;
/* 395 */               player = player2;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 401 */       if (player != null) {
/* 402 */         func_70634_a(player.field_70165_t - 1.0D + this.field_70146_Z.nextDouble() * 2.0D, player.field_70163_u + 0.05D, player.field_70161_v - 1.0D + this.field_70146_Z.nextDouble() * 2.0D);
/*     */         
/* 404 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/* 405 */         ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcomplete", new Object[0]);
/*     */         
/* 407 */         SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt(this);
/*     */       } else {
/* 409 */         func_70106_y();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 416 */     return null;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 421 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 426 */     if ((!this.field_70170_p.field_72995_K) && 
/* 427 */       (this.isFriendly)) {
/* 428 */       ItemStack stack = player.func_70694_bm();
/* 429 */       SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt(this, 1.0F);
/* 430 */       boolean vanish = false;
/* 431 */       if (stack == null) {
/* 432 */         ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcomplete2", new Object[0]);
/*     */       }
/* 434 */       else if (stack.func_77973_b() == Witchery.Items.BLOOD_GOBLET) {
/* 435 */         if (!ExtendedPlayer.get(player).isVampire()) {
/* 436 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletelife", new Object[0]);
/*     */           
/* 438 */           player.func_70062_b(0, null);
/* 439 */           ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, player.field_70170_p, player.field_70165_t, player.field_70163_u + player.field_70131_O * 0.85D, player.field_70161_v, 0.8D, 0.8D, 16);
/*     */           
/* 441 */           Witchery.Items.BLOOD_GOBLET.setBloodOwner(stack, com.emoniph.witchery.item.ItemGlassGoblet.BloodSource.LILITH);
/* 442 */           this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, stack));
/*     */           
/* 444 */           ExtendedPlayer.get(player).setHumanBlood(0);
/* 445 */           vanish = true;
/*     */         } else {
/* 447 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletelifefail", new Object[0]);
/*     */         }
/*     */       }
/* 450 */       else if (stack.func_77973_b() == Witchery.Items.SEEDS_GARLIC) {
/* 451 */         if (ExtendedPlayer.get(player).isVampire()) {
/* 452 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletecure", new Object[0]);
/*     */           
/* 454 */           player.func_70062_b(0, null);
/* 455 */           ExtendedPlayer.get(player).setVampireLevel(0);
/* 456 */           ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 1.5D, 16);
/* 457 */           vanish = true;
/*     */         } else {
/* 459 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletecurefail", new Object[0]);
/*     */         }
/*     */       }
/* 462 */       else if ((stack.func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150328_O)) && (stack.func_77960_j() == 0)) {
/* 463 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 464 */         if ((playerEx.getVampireLevel() == 6) && (playerEx.canIncreaseVampireLevel())) {
/* 465 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletebatflight", new Object[0]);
/*     */           
/* 467 */           player.func_70062_b(0, null);
/* 468 */           playerEx.increaseVampireLevel();
/* 469 */           ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 1.5D, 16);
/* 470 */           vanish = true;
/*     */         } else {
/* 472 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletebatflightfail", new Object[0]);
/*     */         }
/*     */       }
/*     */       else {
/* 476 */         List enchants = EnchantmentHelper.func_77513_b(this.field_70170_p.field_73012_v, stack, 40);
/* 477 */         if ((enchants != null) && (enchants.size() > 0)) {
/* 478 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletemagic", new Object[0]);
/*     */           
/* 480 */           player.func_70062_b(0, null);
/* 481 */           addEnchantmentsFromList(stack, enchants);
/* 482 */           if (stack.func_77984_f()) {
/* 483 */             stack.func_77964_b(0);
/*     */           }
/* 485 */           this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, stack));
/*     */           
/*     */ 
/* 488 */           vanish = true;
/*     */         } else {
/* 490 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcomplete2", new Object[0]);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 495 */       if (vanish) {
/* 496 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/* 497 */         func_70106_y();
/*     */       }
/* 499 */       return true;
/*     */     }
/*     */     
/* 502 */     return false;
/*     */   }
/*     */   
/*     */   private static void addEnchantmentsFromList(ItemStack stack, List list) {
/* 506 */     boolean flag = stack.func_77973_b() == Items.field_151122_aG;
/*     */     
/* 508 */     if (flag) {
/* 509 */       stack.func_150996_a(Items.field_151134_bR);
/*     */     }
/*     */     
/* 512 */     Map enchants = EnchantmentHelper.func_82781_a(stack);
/*     */     
/* 514 */     if (list != null) {
/* 515 */       Iterator iterator = list.iterator();
/*     */       
/* 517 */       while (iterator.hasNext()) {
/* 518 */         EnchantmentData enchantmentdata = (EnchantmentData)iterator.next();
/* 519 */         if (flag) {
/* 520 */           Items.field_151134_bR.func_92115_a(stack, enchantmentdata);
/*     */         }
/*     */         else {
/* 523 */           if (stack.func_77978_p() == null) {
/* 524 */             stack.func_77982_d(new NBTTagCompound());
/*     */           }
/*     */           
/* 527 */           if (!stack.func_77978_p().func_150297_b("ench", 9)) {
/* 528 */             stack.func_77978_p().func_74782_a("ench", new NBTTagList());
/*     */           }
/*     */           
/* 531 */           NBTTagList nbttaglist = stack.func_77978_p().func_150295_c("ench", 10);
/*     */           
/* 533 */           boolean addEnchant = true;
/*     */           
/* 535 */           for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 536 */             NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
/*     */             
/* 538 */             if (nbttagcompound.func_74765_d("id") == enchantmentdata.field_76302_b.field_77352_x) {
/* 539 */               if (nbttagcompound.func_74765_d("lvl") < enchantmentdata.field_76303_c) {
/* 540 */                 nbttagcompound.func_74777_a("lvl", (short)enchantmentdata.field_76303_c);
/*     */               }
/*     */               
/* 543 */               addEnchant = false;
/* 544 */               break;
/*     */             }
/*     */           }
/*     */           
/* 548 */           if (addEnchant)
/*     */           {
/* 550 */             NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 551 */             nbttagcompound.func_74777_a("id", (short)enchantmentdata.field_76302_b.field_77352_x);
/* 552 */             nbttagcompound.func_74777_a("lvl", (short)(byte)enchantmentdata.field_76303_c);
/* 553 */             nbttaglist.func_74742_a(nbttagcompound);
/*     */           }
/*     */           
/* 556 */           stack.func_77978_p().func_74782_a("ench", nbttaglist);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 563 */   private static final RandomCollection<SymbolEffect> SPELLS = ;
/*     */   
/*     */   private static RandomCollection<SymbolEffect> createSpells() {
/* 566 */     RandomCollection<SymbolEffect> spells = new RandomCollection();
/*     */     
/* 568 */     EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Ignianima);
/* 569 */     EffectRegistry.instance();spells.add(5.0D, EffectRegistry.Flipendo);
/* 570 */     EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Impedimenta);
/* 571 */     EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Confundus);
/* 572 */     EffectRegistry.instance();spells.add(5.0D, EffectRegistry.Attraho);
/*     */     
/* 574 */     return spells;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase targetEntity, float par2)
/*     */   {
/* 579 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 580 */       this.attackTimer = 10;
/* 581 */       this.field_70170_p.func_72960_a(this, (byte)4);
/*     */       
/* 583 */       double d0 = targetEntity.field_70165_t - this.field_70165_t;
/* 584 */       double d1 = targetEntity.field_70121_D.field_72338_b + targetEntity.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/*     */       
/* 586 */       double d2 = targetEntity.field_70161_v - this.field_70161_v;
/*     */       
/* 588 */       float f1 = net.minecraft.util.MathHelper.func_76129_c(par2) * 0.5F;
/*     */       
/* 590 */       if (!this.field_70170_p.field_72995_K) {
/* 591 */         if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 592 */           EntityLargeFireball fireballEntity = new EntityLargeFireball(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1);
/*     */           
/* 594 */           double d8 = 1.0D;
/* 595 */           Vec3 vec3 = func_70676_i(1.0F);
/* 596 */           fireballEntity.field_70165_t = (this.field_70165_t + vec3.field_72450_a * d8);
/* 597 */           fireballEntity.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F + 0.5D);
/* 598 */           fireballEntity.field_70161_v = (this.field_70161_v + vec3.field_72449_c * d8);
/*     */           
/* 600 */           if (!this.field_70170_p.field_72995_K) {
/* 601 */             this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */             
/* 603 */             this.field_70170_p.func_72838_d(fireballEntity);
/*     */           }
/*     */         } else {
/* 606 */           this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */           
/* 608 */           int count = this.field_70146_Z.nextInt(10) == 0 ? 9 : 3;
/*     */           
/* 610 */           EntitySpellEffect effect = new EntitySpellEffect(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1, (SymbolEffect)SPELLS.next(), 1);
/*     */           
/*     */ 
/* 613 */           double d8 = 1.0D;
/* 614 */           effect.field_70165_t = this.field_70165_t;
/* 615 */           effect.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F);
/* 616 */           effect.field_70161_v = this.field_70161_v;
/* 617 */           this.field_70170_p.func_72838_d(effect);
/* 618 */           effect.setShooter(this);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityLilith.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */