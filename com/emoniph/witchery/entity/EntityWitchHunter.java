/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.network.PacketSound;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityWitchHunter extends EntityCreature implements IRangedAttackMob, IEntitySelector
/*     */ {
/*  48 */   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
/*  49 */   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.2D, false);
/*     */   private String targetPlayerName;
/*     */   private static final double HUNTER_NOTICE_CHANCE = 0.1D;
/*     */   
/*     */   public EntityWitchHunter(World par1World)
/*     */   {
/*  55 */     super(par1World);
/*  56 */     func_70661_as().func_75491_a(true);
/*  57 */     func_70661_as().func_75495_e(true);
/*  58 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  59 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  60 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  61 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  62 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  63 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, true, this));
/*     */     
/*     */ 
/*  66 */     this.field_70728_aV = 5;
/*  67 */     this.targetPlayerName = "";
/*     */     
/*  69 */     if ((par1World != null) && (!par1World.field_72995_K)) {
/*  70 */       setCombatTask();
/*     */     }
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  76 */     if (func_94056_bM()) {
/*  77 */       return func_94057_bL();
/*     */     }
/*  79 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.witchhunter.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82704_a(Entity entity)
/*     */   {
/*  85 */     if ((CreatureUtil.isUndead(entity)) || (CreatureUtil.isDemonic(entity)) || ((entity instanceof EntityWitch)) || (CreatureUtil.isWerewolf(entity)))
/*     */     {
/*  87 */       return true; }
/*  88 */     if ((entity instanceof EntityPlayer)) {
/*  89 */       EntityPlayer player = (EntityPlayer)entity;
/*  90 */       return (CreatureUtil.isWitch(entity)) || (CreatureUtil.isWerewolf(entity)) || (CreatureUtil.isVampire(entity)) || ((this.targetPlayerName != null) && (!this.targetPlayerName.isEmpty()) && (player.func_70005_c_().equals(this.targetPlayerName)));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 101 */     super.func_110147_ax();
/* 102 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/* 103 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/* 104 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 109 */     super.func_70088_a();
/* 110 */     this.field_70180_af.func_75682_a(13, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 115 */     return true;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 125 */     return "mob.villager.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 130 */     return "mob.villager.death";
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 135 */     func_85030_a("step.grass", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity targetEntity)
/*     */   {
/* 140 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 141 */     int i = 0;
/*     */     
/* 143 */     if ((targetEntity instanceof EntityLivingBase)) {
/* 144 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)targetEntity);
/* 145 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)targetEntity);
/*     */     }
/*     */     
/* 148 */     boolean flag = targetEntity.func_70097_a(DamageSource.func_76358_a(this), f);
/*     */     
/* 150 */     if (flag) {
/* 151 */       if (i > 0) {
/* 152 */         targetEntity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/*     */         
/* 154 */         this.field_70159_w *= 0.6D;
/* 155 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 158 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 160 */       if (j > 0) {
/* 161 */         targetEntity.func_70015_d(j * 4);
/*     */       }
/*     */       
/* 164 */       if ((targetEntity instanceof EntityLivingBase)) {
/* 165 */         EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, this);
/*     */       }
/*     */       
/* 168 */       EnchantmentHelper.func_151385_b(this, targetEntity);
/*     */     }
/*     */     
/* 171 */     return flag;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 176 */     func_82168_bl();
/* 177 */     float f = func_70013_c(1.0F);
/* 178 */     if (f > 0.5F) {
/* 179 */       this.field_70708_bq += 2;
/*     */     }
/*     */     
/* 182 */     if ((!this.field_70170_p.field_72995_K) && 
/* 183 */       (this.field_70173_aa % 20 == 2) && (func_70644_a(Potion.field_76436_u))) {
/* 184 */       func_82170_o(Potion.field_76436_u.field_76415_H);
/*     */     }
/*     */     
/* 187 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   protected String func_145776_H()
/*     */   {
/* 192 */     return "game.hostile.swim";
/*     */   }
/*     */   
/*     */   protected String func_145777_O()
/*     */   {
/* 197 */     return "game.hostile.swim.splash";
/*     */   }
/*     */   
/*     */   public void func_70098_U()
/*     */   {
/* 202 */     super.func_70098_U();
/*     */     
/* 204 */     if ((this.field_70154_o instanceof EntityCreature)) {
/* 205 */       EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
/* 206 */       this.field_70761_aq = entitycreature.field_70761_aq;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float damage)
/*     */   {
/* 212 */     if ((damageSource.func_76346_g() != null) && (((damageSource.func_76346_g() instanceof EntityVillageGuard)) || ((damageSource.func_76346_g() instanceof EntityWitchHunter))))
/*     */     {
/* 214 */       return false;
/*     */     }
/*     */     
/* 217 */     if (func_85032_ar())
/* 218 */       return false;
/* 219 */     if (super.func_70097_a(damageSource, Math.min(damage, 9.0F))) {
/* 220 */       Entity entity = damageSource.func_76346_g();
/*     */       
/* 222 */       if ((this.field_70153_n != entity) && (this.field_70154_o != entity)) {
/* 223 */         if (entity != this) {
/* 224 */           this.field_70789_a = entity;
/*     */         }
/*     */         
/* 227 */         return true;
/*     */       }
/* 229 */       return true;
/*     */     }
/*     */     
/* 232 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_146067_o(int distance)
/*     */   {
/* 238 */     return distance > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 246 */     int j = this.field_70146_Z.nextInt(3 + par2);
/*     */     
/* 248 */     for (int k = 0; k < j; k++) {
/* 249 */       func_70099_a(Witchery.Items.GENERIC.itemBoltStake.createStack(), 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70600_l(int par1)
/*     */   {
/* 255 */     func_70099_a(Witchery.Items.GENERIC.itemBoltAntiMagic.createStack(2), 0.0F);
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 260 */     par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
/*     */     
/* 262 */     setHunterType(this.field_70170_p.field_73012_v.nextInt(3));
/* 263 */     func_70062_b(0, new ItemStack(Witchery.Items.CROSSBOW_PISTOL));
/* 264 */     func_82162_bC();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 270 */     return par1EntityLivingData;
/*     */   }
/*     */   
/*     */   public void setCombatTask() {
/* 274 */     this.field_70714_bg.func_85156_a(this.aiAttackOnCollide);
/* 275 */     this.field_70714_bg.func_85156_a(this.aiArrowAttack);
/* 276 */     ItemStack itemstack = func_70694_bm();
/*     */     
/* 278 */     if ((itemstack != null) && (itemstack.func_77973_b() == Witchery.Items.CROSSBOW_PISTOL)) {
/* 279 */       this.field_70714_bg.func_75776_a(4, this.aiArrowAttack);
/*     */     } else {
/* 281 */       this.field_70714_bg.func_75776_a(4, this.aiAttackOnCollide);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 287 */     EntityBolt entityarrow = new EntityBolt(this.field_70170_p, this, par1EntityLivingBase, 1.6F, 14 - this.field_70170_p.field_73013_u.func_151525_a() * 4);
/*     */     
/* 289 */     int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
/* 290 */     int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
/* 291 */     entityarrow.setDamage(par2 * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.field_73013_u.func_151525_a() * 0.11F);
/*     */     
/*     */ 
/* 294 */     if (i > 0) {
/* 295 */       entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
/*     */     }
/*     */     
/* 298 */     if (j > 0) {
/* 299 */       entityarrow.setKnockbackStrength(j);
/*     */     }
/*     */     
/* 302 */     if ((EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, func_70694_bm()) > 0) || ((CreatureUtil.isVampire(func_70638_az())) && (this.field_70170_p.field_73012_v.nextInt(3) == 0)))
/*     */     {
/* 304 */       entityarrow.func_70015_d(100);
/*     */     }
/*     */     
/* 307 */     if (func_70638_az() != null) {
/* 308 */       if (CreatureUtil.isWerewolf(func_70638_az())) {
/* 309 */         entityarrow.setBoltType(4);
/* 310 */       } else if (CreatureUtil.isUndead(func_70638_az())) {
/* 311 */         entityarrow.setBoltType(3);
/* 312 */       } else if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 313 */         entityarrow.setBoltType(2);
/*     */       }
/*     */     }
/*     */     
/* 317 */     func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 318 */     this.field_70170_p.func_72838_d(entityarrow);
/*     */   }
/*     */   
/*     */   public int getHunterType() {
/* 322 */     return this.field_70180_af.func_75683_a(13);
/*     */   }
/*     */   
/*     */   public void setHunterType(int par1) {
/* 326 */     this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)par1));
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 331 */     super.func_70037_a(nbtRoot);
/*     */     
/* 333 */     if (nbtRoot.func_74764_b("HunterType")) {
/* 334 */       byte b0 = nbtRoot.func_74771_c("HunterType");
/* 335 */       setHunterType(b0);
/*     */     }
/*     */     
/* 338 */     this.targetPlayerName = nbtRoot.func_74779_i("HunterTarget");
/*     */     
/* 340 */     setCombatTask();
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 345 */     super.func_70014_b(nbtRoot);
/* 346 */     nbtRoot.func_74774_a("HunterType", (byte)getHunterType());
/* 347 */     nbtRoot.func_74778_a("HunterTarget", this.targetPlayerName);
/*     */   }
/*     */   
/*     */   public void func_70062_b(int slot, ItemStack stack)
/*     */   {
/* 352 */     super.func_70062_b(slot, stack);
/*     */     
/* 354 */     if ((!this.field_70170_p.field_72995_K) && (slot == 0)) {
/* 355 */       setCombatTask();
/*     */     }
/*     */   }
/*     */   
/*     */   public double func_70033_W()
/*     */   {
/* 361 */     return super.func_70033_W() - 0.5D;
/*     */   }
/*     */   
/*     */ 
/* 365 */   private static final long HUNTER_DELAY = TimeUtil.minsToTicks(2);
/*     */   private static final double HUNTER_TRIGGER_CHANCE = 0.01D;
/*     */   
/*     */   public static void blackMagicPerformed(EntityPlayer player) {
/* 369 */     if ((player != null) && (player.field_70170_p != null) && (!player.field_70170_p.field_72995_K)) {
/* 370 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 371 */       if ((nbtPlayer != null) && (nbtPlayer.func_74763_f("WITCHunterTrigger") <= 0L) && 
/* 372 */         (player.field_70170_p.field_73012_v.nextDouble() < 0.1D)) {
/* 373 */         long totalWorldTicks = TimeUtil.getServerTimeInTicks();
/* 374 */         nbtPlayer.func_74772_a("WITCHunterTrigger", totalWorldTicks);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void handleWitchHunterEffects(EntityPlayer player, long totalWorldTicks)
/*     */   {
/* 381 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 382 */     if (nbtPlayer != null) {
/* 383 */       long triggerTimeTicks = nbtPlayer.func_74763_f("WITCHunterTrigger");
/* 384 */       if (((triggerTimeTicks > 0L) && (totalWorldTicks >= triggerTimeTicks + HUNTER_DELAY) && (player.field_70170_p.field_73012_v.nextDouble() < 0.01D)) || (isVampireActive(player, totalWorldTicks)))
/*     */       {
/* 386 */         nbtPlayer.func_82580_o("WITCHunterTrigger");
/* 387 */         int MAX_SPAWNS = 2;
/* 388 */         int tries = 3;
/* 389 */         int spawned = 0;
/* 390 */         for (int i = 0; (i < 3) && (spawned < 2); i++) {
/* 391 */           EntityWitchHunter creature = (EntityWitchHunter)Infusion.spawnCreature(player.field_70170_p, EntityWitchHunter.class, MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), player, 3, 8, ParticleEffect.SMOKE, null);
/*     */           
/*     */ 
/*     */ 
/* 395 */           if (creature != null) {
/* 396 */             spawned++;
/* 397 */             creature.targetPlayerName = player.func_70005_c_();
/* 398 */             creature.func_110161_a(null);
/* 399 */             com.emoniph.witchery.util.EntityUtil.setTarget(creature, player);
/*     */           }
/*     */         }
/* 402 */         if (spawned > 0) {
/* 403 */           Witchery.packetPipeline.sendTo(new PacketSound(com.emoniph.witchery.util.SoundEffect.WITCHERY_RANDOM_THEYCOME, player, 1.0F, 1.0F), player);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isVampireActive(EntityPlayer player, long totalWorldTicks)
/*     */   {
/* 411 */     if ((Config.instance().vampireHunterSpawnChance <= 0.0D) || (player.field_71075_bZ.field_75098_d)) {
/* 412 */       return false;
/*     */     }
/*     */     
/* 415 */     ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 416 */     if (playerEx.getVampireLevel() < 10) {
/* 417 */       return false;
/*     */     }
/*     */     
/* 420 */     if (player.field_70170_p.field_73012_v.nextDouble() < Config.instance().vampireHunterSpawnChance) {
/* 421 */       Village village = player.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), 128);
/*     */       
/*     */ 
/*     */ 
/* 425 */       if ((village != null) && 
/* 426 */         (village.func_82684_a(player.func_70005_c_()) < -1)) {
/* 427 */         List hunters = player.field_70170_p.func_72872_a(EntityWitchHunter.class, player.field_70121_D.func_72314_b(64.0D, 16.0D, 64.0D));
/*     */         
/* 429 */         return (hunters == null) || (hunters.size() == 0);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 434 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70785_a(Entity p_70785_1_, float p_70785_2_)
/*     */   {
/* 439 */     if ((this.field_70724_aR <= 0) && (p_70785_2_ < 2.0F) && (p_70785_1_.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (p_70785_1_.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e))
/*     */     {
/* 441 */       this.field_70724_aR = 20;
/* 442 */       func_70652_k(p_70785_1_);
/*     */     }
/*     */   }
/*     */   
/*     */   public float func_70783_a(int p_70783_1_, int p_70783_2_, int p_70783_3_)
/*     */   {
/* 448 */     return 0.5F - this.field_70170_p.func_72801_o(p_70783_1_, p_70783_2_, p_70783_3_);
/*     */   }
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 452 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 453 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 454 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 456 */     if (this.field_70170_p.func_72972_b(net.minecraft.world.EnumSkyBlock.Sky, i, j, k) > this.field_70146_Z.nextInt(32)) {
/* 457 */       return false;
/*     */     }
/* 459 */     int l = this.field_70170_p.func_72957_l(i, j, k);
/*     */     
/* 461 */     if (this.field_70170_p.func_72911_I()) {
/* 462 */       int i1 = this.field_70170_p.field_73008_k;
/* 463 */       this.field_70170_p.field_73008_k = 10;
/* 464 */       l = this.field_70170_p.func_72957_l(i, j, k);
/* 465 */       this.field_70170_p.field_73008_k = i1;
/*     */     }
/*     */     
/* 468 */     return l <= this.field_70146_Z.nextInt(8);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 474 */     return (this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) && (isValidLightLevel()) && (super.func_70601_bi());
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_146066_aG()
/*     */   {
/* 480 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityWitchHunter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */