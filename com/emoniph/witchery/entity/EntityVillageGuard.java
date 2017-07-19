/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIDefendVillageGeneric.IVillageGuard;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
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
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityVillageGuard extends EntityCreature implements IRangedAttackMob, EntityAIDefendVillageGeneric.IVillageGuard, IEntitySelector
/*     */ {
/*  52 */   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
/*  53 */   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
/*     */   private int homeCheckTimer;
/*     */   Village villageObj;
/*     */   
/*  57 */   public EntityVillageGuard(World world) { super(world);
/*  58 */     func_70661_as().func_75491_a(true);
/*  59 */     func_70661_as().func_75498_b(true);
/*  60 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*     */     
/*     */ 
/*  63 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIMoveThroughVillage(this, 0.6D, true));
/*  64 */     this.field_70714_bg.func_75776_a(7, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  65 */     this.field_70714_bg.func_75776_a(8, new EntityAIRestrictOpenDoor(this));
/*  66 */     this.field_70714_bg.func_75776_a(9, new EntityAIOpenDoor(this, true));
/*  67 */     this.field_70714_bg.func_75776_a(10, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  68 */     this.field_70714_bg.func_75776_a(11, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  69 */     this.field_70714_bg.func_75776_a(12, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  70 */     this.field_70715_bh.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAIDefendVillageGeneric(this));
/*  71 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*  72 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, true, this));
/*     */     
/*     */ 
/*  75 */     if ((world != null) && (!world.field_72995_K)) {
/*  76 */       setCombatTask();
/*     */     }
/*     */     
/*  79 */     this.field_70728_aV = 5;
/*     */   }
/*     */   
/*     */   public boolean func_70686_a(Class p_70686_1_)
/*     */   {
/*  84 */     return (EntityCreeper.class != p_70686_1_) && (getClass() != p_70686_1_);
/*     */   }
/*     */   
/*     */   public boolean func_82704_a(Entity entity) {
/*  88 */     if ((((entity instanceof IMob)) && (!(entity instanceof EntityWitchHunter))) || ((entity instanceof EntityGoblin))) {
/*  89 */       return true;
/*     */     }
/*     */     
/*  92 */     if ((this.villageObj != null) && ((entity instanceof EntityPlayer))) {
/*  93 */       EntityPlayer player = (EntityPlayer)entity;
/*  94 */       EntityLivingBase target = player.func_70643_av();
/*  95 */       if (((target instanceof EntityPlayer)) && 
/*  96 */         (this.villageObj.func_82684_a(target.func_70005_c_()) == 10)) {
/*  97 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 102 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getBlood()
/*     */   {
/* 109 */     return this.field_70180_af.func_75679_c(14);
/*     */   }
/*     */   
/*     */   public void setBlood(int blood) {
/* 113 */     this.field_70180_af.func_75692_b(14, Integer.valueOf(MathHelper.func_76125_a(blood, 0, 500)));
/*     */   }
/*     */   
/*     */   public int takeBlood(int quantity, EntityLivingBase player)
/*     */   {
/* 118 */     PotionEffect effect = func_70660_b(Witchery.Potions.PARALYSED);
/* 119 */     boolean transfixed = (effect != null) && (effect.func_76458_c() >= 4);
/*     */     
/* 121 */     int blood = getBlood();
/*     */     
/* 123 */     quantity = (int)Math.ceil(0.66F * quantity);
/* 124 */     int remainder = Math.max(blood - quantity, 0);
/* 125 */     int taken = blood - remainder;
/* 126 */     setBlood(remainder);
/* 127 */     if (blood < (int)Math.ceil(250.0D)) {
/* 128 */       func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), player), 2.0F);
/* 129 */     } else if (!transfixed) {
/* 130 */       func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), player), 0.5F);
/*     */     }
/* 132 */     return taken;
/*     */   }
/*     */   
/*     */   public void giveBlood(int quantity) {
/* 136 */     int blood = getBlood();
/* 137 */     if (blood < 500) {
/* 138 */       setBlood(blood + quantity);
/*     */     }
/*     */   }
/*     */   
/*     */   public Village getVillage()
/*     */   {
/* 144 */     return this.villageObj;
/*     */   }
/*     */   
/*     */   public EntityCreature getCreature()
/*     */   {
/* 149 */     return this;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 154 */     if (--this.homeCheckTimer <= 0) {
/* 155 */       this.homeCheckTimer = (70 + this.field_70146_Z.nextInt(50));
/* 156 */       this.villageObj = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), 32);
/*     */       
/*     */ 
/*     */ 
/* 160 */       if (this.villageObj == null) {
/* 161 */         func_110177_bN();
/*     */       } else {
/* 163 */         ChunkCoordinates chunkcoordinates = this.villageObj.func_75577_a();
/* 164 */         func_110171_b(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, (int)(this.villageObj.func_75568_b() * 1.5F));
/*     */         
/*     */ 
/* 167 */         if (func_70638_az() == null) {
/* 168 */           func_70691_i(1.0F);
/* 169 */           if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 170 */             giveBlood(1);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 176 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */   protected String func_145776_H()
/*     */   {
/* 181 */     return "game.hostile.swim";
/*     */   }
/*     */   
/*     */   protected String func_145777_O()
/*     */   {
/* 186 */     return "game.hostile.swim.splash";
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 191 */     super.func_110147_ax();
/* 192 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 193 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/* 194 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/* 195 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
/* 196 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 201 */     super.func_70088_a();
/* 202 */     this.field_70180_af.func_75682_a(13, new Byte((byte)0));
/* 203 */     this.field_70180_af.func_75682_a(14, new Integer(500));
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 208 */     return true;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 213 */     return "mob.villager.idle";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 218 */     return "mob.villager.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 223 */     return "mob.villager.death";
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 228 */     return 0.8F;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 233 */     func_82168_bl();
/* 234 */     float f = func_70013_c(1.0F);
/*     */     
/* 236 */     if (f > 0.5F) {
/* 237 */       this.field_70708_bq += 2;
/*     */     }
/*     */     
/* 240 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float damage)
/*     */   {
/* 245 */     if ((damageSource.func_76346_g() != null) && (((damageSource.func_76346_g() instanceof EntityVillageGuard)) || ((damageSource.func_76346_g() instanceof EntityWitchHunter))))
/*     */     {
/* 247 */       return false;
/*     */     }
/*     */     
/* 250 */     return super.func_70097_a(damageSource, damage);
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 255 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 256 */     int i = 0;
/*     */     
/* 258 */     if ((p_70652_1_ instanceof EntityLivingBase)) {
/* 259 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)p_70652_1_);
/* 260 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)p_70652_1_);
/*     */     }
/*     */     
/* 263 */     boolean flag = p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), f);
/*     */     
/* 265 */     if (flag) {
/* 266 */       if (i > 0) {
/* 267 */         p_70652_1_.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/*     */         
/* 269 */         this.field_70159_w *= 0.6D;
/* 270 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 273 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 275 */       if (j > 0) {
/* 276 */         p_70652_1_.func_70015_d(j * 4);
/*     */       }
/*     */       
/* 279 */       if ((p_70652_1_ instanceof EntityLivingBase)) {
/* 280 */         EnchantmentHelper.func_151384_a((EntityLivingBase)p_70652_1_, this);
/*     */       }
/*     */       
/* 283 */       EnchantmentHelper.func_151385_b(this, p_70652_1_);
/*     */     }
/*     */     
/* 286 */     return flag;
/*     */   }
/*     */   
/*     */   protected void func_70785_a(Entity p_70785_1_, float p_70785_2_)
/*     */   {
/* 291 */     if ((this.field_70724_aR <= 0) && (p_70785_2_ < 2.0F) && (p_70785_1_.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (p_70785_1_.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e))
/*     */     {
/* 293 */       this.field_70724_aR = 20;
/* 294 */       func_70652_k(p_70785_1_);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {}
/*     */   
/*     */ 
/*     */   public void func_70098_U()
/*     */   {
/* 305 */     super.func_70098_U();
/*     */     
/* 307 */     if ((this.field_70154_o instanceof EntityCreature)) {
/* 308 */       EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
/* 309 */       this.field_70761_aq = entitycreature.field_70761_aq;
/*     */     }
/*     */   }
/*     */   
/*     */   protected String func_146067_o(int p_146067_1_)
/*     */   {
/* 315 */     return p_146067_1_ > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource p_70645_1_)
/*     */   {
/* 320 */     if ((this.field_70717_bb != null) && (this.villageObj != null)) {
/* 321 */       this.villageObj.func_82688_a(this.field_70717_bb.func_70005_c_(), -5);
/*     */     }
/*     */     
/* 324 */     super.func_70645_a(p_70645_1_);
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 329 */     return Items.field_151032_g;
/*     */   }
/*     */   
/*     */   protected void func_70600_l(int p_70600_1_)
/*     */   {
/* 334 */     func_70099_a(new ItemStack(Items.field_151027_R, 1), 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_82164_bB()
/*     */   {
/* 339 */     func_70062_b(0, new ItemStack(Items.field_151031_f));
/*     */     
/* 341 */     func_70062_b(1, new ItemStack(Items.field_151021_T));
/* 342 */     func_70062_b(2, new ItemStack(Items.field_151026_S));
/*     */     
/* 344 */     func_70062_b(3, new ItemStack(this.field_70170_p.field_73012_v.nextInt(5) == 0 ? Items.field_151030_Z : Items.field_151027_R));
/*     */     
/*     */ 
/* 347 */     func_70062_b(4, new ItemStack(this.field_70170_p.field_73012_v.nextInt(5) == 0 ? Items.field_151028_Y : Items.field_151024_Q));
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 353 */     if (func_94056_bM()) {
/* 354 */       return func_94057_bL();
/*     */     }
/* 356 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.villageguard.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/* 362 */     p_110161_1_ = super.func_110161_a(p_110161_1_);
/*     */     
/* 364 */     func_82164_bB();
/*     */     
/* 366 */     return p_110161_1_;
/*     */   }
/*     */   
/*     */   public void setCombatTask() {
/* 370 */     this.field_70714_bg.func_85156_a(this.aiAttackOnCollide);
/* 371 */     this.field_70714_bg.func_85156_a(this.aiArrowAttack);
/* 372 */     ItemStack itemstack = func_70694_bm();
/*     */     
/* 374 */     if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151031_f)) {
/* 375 */       this.field_70714_bg.func_75776_a(4, this.aiArrowAttack);
/*     */     } else {
/* 377 */       this.field_70714_bg.func_75776_a(4, this.aiAttackOnCollide);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase target, float p_82196_2_)
/*     */   {
/* 383 */     PotionEffect effect = func_70660_b(Witchery.Potions.PARALYSED);
/* 384 */     if ((effect != null) && (effect.func_76458_c() >= 4)) {
/* 385 */       return;
/*     */     }
/*     */     
/* 388 */     EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, target, 1.6F, 16 - this.field_70170_p.field_73013_u.func_151525_a() * 4);
/*     */     
/* 390 */     int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
/* 391 */     int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
/* 392 */     entityarrow.func_70239_b(p_82196_2_ * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.field_73013_u.func_151525_a() * 0.11F);
/*     */     
/*     */ 
/* 395 */     if (i > 0) {
/* 396 */       entityarrow.func_70239_b(entityarrow.func_70242_d() + i * 0.5D + 0.5D);
/*     */     }
/*     */     
/* 399 */     if (j > 0) {
/* 400 */       entityarrow.func_70240_a(j);
/*     */     }
/*     */     
/* 403 */     if ((EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, func_70694_bm()) > 0) || (getGuardType() == 1))
/*     */     {
/* 405 */       entityarrow.func_70015_d(100);
/*     */     }
/*     */     
/* 408 */     func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 409 */     this.field_70170_p.func_72838_d(entityarrow);
/*     */   }
/*     */   
/*     */   public int getGuardType() {
/* 413 */     return this.field_70180_af.func_75683_a(13);
/*     */   }
/*     */   
/*     */   public void setGuardType(int p_82201_1_) {
/* 417 */     this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)p_82201_1_));
/* 418 */     this.field_70178_ae = (p_82201_1_ == 1);
/*     */     
/* 420 */     if (p_82201_1_ == 1) {
/* 421 */       func_70105_a(0.72F, 2.34F);
/*     */     } else {
/* 423 */       func_70105_a(0.6F, 1.8F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 429 */     super.func_70037_a(nbtRoot);
/*     */     
/* 431 */     if (nbtRoot.func_150297_b("GuardType", 99)) {
/* 432 */       byte b0 = nbtRoot.func_74771_c("GuardType");
/* 433 */       setGuardType(b0);
/*     */     }
/*     */     
/* 436 */     setCombatTask();
/*     */     
/* 438 */     if (nbtRoot.func_74764_b("BloodLevel")) {
/* 439 */       setBlood(nbtRoot.func_74762_e("BloodLevel"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 445 */     super.func_70014_b(nbtRoot);
/* 446 */     nbtRoot.func_74774_a("GuardType", (byte)getGuardType());
/* 447 */     nbtRoot.func_74768_a("BloodLevel", getBlood());
/*     */   }
/*     */   
/*     */   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_)
/*     */   {
/* 452 */     super.func_70062_b(p_70062_1_, p_70062_2_);
/*     */     
/* 454 */     if ((!this.field_70170_p.field_72995_K) && (p_70062_1_ == 0)) {
/* 455 */       setCombatTask();
/*     */     }
/*     */   }
/*     */   
/*     */   public double func_70033_W()
/*     */   {
/* 461 */     return super.func_70033_W() - 0.5D;
/*     */   }
/*     */   
/*     */   public static void createFrom(EntityVillager villager) {
/* 465 */     World world = villager.field_70170_p;
/* 466 */     EntityVillageGuard entity = new EntityVillageGuard(world);
/* 467 */     entity.func_110163_bv();
/* 468 */     entity.func_82149_j(villager);
/* 469 */     entity.func_110161_a(null);
/* 470 */     world.func_72900_e(villager);
/* 471 */     world.func_72838_d(entity);
/* 472 */     ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, entity, entity.field_70130_N, entity.field_70131_O, 16);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityVillageGuard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */