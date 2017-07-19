/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityDeath extends EntityMob implements IBossDisplayData, com.emoniph.witchery.util.IHandleDT
/*     */ {
/*  39 */   private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("e942c510-c256-11e3-8a33-0800200c9a66");
/*  40 */   private static final AttributeModifier attackingSpeedBoostModifier = new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 6.199999809265137D, 0).func_111168_a(false);
/*     */   
/*     */ 
/*     */   private int teleportDelay;
/*     */   
/*     */ 
/*     */   private int stareTimer;
/*     */   
/*     */ 
/*     */   private Entity lastEntityToAttack;
/*     */   
/*     */ 
/*     */   private boolean isAggressive;
/*     */   
/*     */ 
/*     */ 
/*     */   public EntityDeath(World par1World)
/*     */   {
/*  58 */     super(par1World);
/*  59 */     func_70105_a(0.6F, 1.8F);
/*  60 */     this.field_70138_W = 1.0F;
/*  61 */     this.field_70178_ae = true;
/*  62 */     this.field_70728_aV = 80;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int func_70682_h(int par1)
/*     */   {
/*  70 */     return par1;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  80 */     if (func_94056_bM()) {
/*  81 */       return func_94057_bL();
/*     */     }
/*  83 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.death.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  89 */     super.func_110147_ax();
/*  90 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0D);
/*  91 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
/*  92 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  97 */     super.func_70088_a();
/*  98 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*  99 */     this.field_70180_af.func_75682_a(17, new Byte((byte)0));
/* 100 */     this.field_70180_af.func_75682_a(18, new Byte((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 108 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 114 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   protected Entity func_70782_k()
/*     */   {
/* 120 */     EntityPlayer entityplayer = this.field_70170_p.func_72856_b(this, 64.0D);
/*     */     
/* 122 */     if (entityplayer != null) {
/* 123 */       if (shouldAttackPlayer(entityplayer)) {
/* 124 */         this.isAggressive = true;
/*     */         
/* 126 */         if (this.stareTimer == 0) {
/* 127 */           this.field_70170_p.func_72956_a(entityplayer, "mob.wither.spawn", 1.0F, 1.0F);
/*     */         }
/*     */         
/* 130 */         if (this.stareTimer++ == 5) {
/* 131 */           this.stareTimer = 0;
/* 132 */           setScreaming(true);
/* 133 */           return entityplayer;
/*     */         }
/*     */       } else {
/* 136 */         this.stareTimer = 0;
/*     */       }
/*     */     }
/*     */     
/* 140 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer)
/*     */   {
/* 147 */     ItemStack itemstack = par1EntityPlayer.field_71071_by.field_70460_b[3];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 155 */     Vec3 vec3 = par1EntityPlayer.func_70676_i(1.0F).func_72432_b();
/* 156 */     Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t - par1EntityPlayer.field_70165_t, this.field_70121_D.field_72338_b + this.field_70131_O / 2.0F - (par1EntityPlayer.field_70163_u + par1EntityPlayer.func_70047_e()), this.field_70161_v - par1EntityPlayer.field_70161_v);
/*     */     
/*     */ 
/* 159 */     double d0 = vec31.func_72433_c();
/* 160 */     vec31 = vec31.func_72432_b();
/* 161 */     double d1 = vec3.func_72430_b(vec31);
/* 162 */     return d1 > 1.0D - 0.025D / d0;
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
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 176 */     if (this.field_70173_aa % 20 == 0) {
/* 177 */       func_70691_i(1.0F);
/*     */     }
/*     */     
/* 180 */     if (this.lastEntityToAttack != this.field_70789_a) {
/* 181 */       IAttributeInstance attributeinstance = func_110148_a(SharedMonsterAttributes.field_111263_d);
/* 182 */       attributeinstance.func_111124_b(attackingSpeedBoostModifier);
/*     */       
/* 184 */       if (this.field_70789_a != null) {
/* 185 */         attributeinstance.func_111121_a(attackingSpeedBoostModifier);
/*     */       }
/*     */     }
/*     */     
/* 189 */     this.lastEntityToAttack = this.field_70789_a;
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
/* 239 */     for (int i = 0; i < 2; i++) {
/* 240 */       this.field_70170_p.func_72869_a("portal", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
/*     */     }
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
/* 271 */     if ((isScreaming()) && (!this.isAggressive))
/*     */     {
/*     */ 
/*     */ 
/* 275 */       setScreaming(false);
/*     */     }
/*     */     
/* 278 */     this.field_70703_bu = false;
/*     */     
/* 280 */     if (this.field_70789_a != null) {
/* 281 */       func_70625_a(this.field_70789_a, 100.0F, 100.0F);
/*     */     }
/*     */     
/* 284 */     if ((!this.field_70170_p.field_72995_K) && (func_70089_S())) {
/* 285 */       if (this.field_70789_a != null) {
/* 286 */         if ((((this.field_70789_a instanceof EntityPlayer)) && (shouldAttackPlayer((EntityPlayer)this.field_70789_a))) || (this.field_70170_p.field_73012_v.nextInt(100) == 0))
/*     */         {
/* 288 */           if (this.field_70789_a.func_70068_e(this) < 16.0D) {
/* 289 */             teleportRandomly();
/*     */           }
/*     */           
/* 292 */           this.teleportDelay = 0;
/* 293 */         } else if ((this.field_70789_a.func_70068_e(this) > 256.0D) && (this.teleportDelay++ >= 30) && (teleportToEntity(this.field_70789_a)))
/*     */         {
/* 295 */           this.teleportDelay = 0;
/*     */         }
/*     */       } else {
/* 298 */         setScreaming(false);
/* 299 */         this.teleportDelay = 0;
/*     */       }
/*     */     }
/*     */     
/* 303 */     if ((this.field_70170_p.field_73012_v.nextDouble() < 0.05D) && (func_70638_az() != null) && ((func_70638_az().field_70160_al) || (((func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)func_70638_az()).field_71075_bZ.field_75100_b))) && (!func_70638_az().func_70644_a(Potion.field_76421_d)))
/*     */     {
/*     */ 
/*     */ 
/* 307 */       func_70638_az().func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 5));
/*     */     }
/*     */     
/* 310 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   protected boolean teleportRandomly() {
/* 314 */     double d0 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 32.0D;
/* 315 */     double d1 = this.field_70163_u + (this.field_70146_Z.nextInt(64) - 32);
/* 316 */     double d2 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 32.0D;
/* 317 */     return teleportTo(d0, d1, d2);
/*     */   }
/*     */   
/*     */   protected boolean teleportToEntity(Entity par1Entity) {
/* 321 */     Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t - par1Entity.field_70165_t, this.field_70121_D.field_72338_b + this.field_70131_O / 2.0F - par1Entity.field_70163_u + par1Entity.func_70047_e(), this.field_70161_v - par1Entity.field_70161_v);
/*     */     
/*     */ 
/* 324 */     vec3 = vec3.func_72432_b();
/* 325 */     double d0 = 16.0D;
/* 326 */     double d1 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72450_a * d0;
/* 327 */     double d2 = this.field_70163_u + (this.field_70146_Z.nextInt(16) - 8) - vec3.field_72448_b * d0;
/* 328 */     double d3 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72449_c * d0;
/* 329 */     return teleportTo(d1, d2, d3);
/*     */   }
/*     */   
/*     */   protected boolean teleportTo(double par1, double par3, double par5) {
/* 333 */     double d3 = this.field_70165_t;
/* 334 */     double d4 = this.field_70163_u;
/* 335 */     double d5 = this.field_70161_v;
/* 336 */     this.field_70165_t = par1;
/* 337 */     this.field_70163_u = par3;
/* 338 */     this.field_70161_v = par5;
/* 339 */     boolean flag = false;
/* 340 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 341 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 342 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 344 */     if (this.field_70170_p.func_72899_e(i, j, k)) {
/* 345 */       boolean flag1 = false;
/*     */       
/* 347 */       while ((!flag1) && (j > 0)) {
/* 348 */         Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
/*     */         
/* 350 */         if (block.func_149688_o().func_76230_c()) {
/* 351 */           flag1 = true;
/*     */         } else {
/* 353 */           this.field_70163_u -= 1.0D;
/* 354 */           j--;
/*     */         }
/*     */       }
/*     */       
/* 358 */       if (flag1) {
/* 359 */         func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */         
/* 361 */         if ((this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */         {
/* 363 */           flag = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 368 */     if (!flag) {
/* 369 */       func_70107_b(d3, d4, d5);
/* 370 */       return false;
/*     */     }
/* 372 */     short short1 = 128;
/*     */     
/* 374 */     for (int l = 0; l < short1; l++) {
/* 375 */       double d6 = l / (short1 - 1.0D);
/* 376 */       float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 377 */       float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 378 */       float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 379 */       double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 380 */       double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * this.field_70131_O;
/* 381 */       double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 382 */       this.field_70170_p.func_72869_a("portal", d7, d8, d9, f, f1, f2);
/*     */     }
/*     */     
/* 385 */     this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 386 */     func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
/* 387 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 393 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 398 */     return "mob.skeleton.hurt";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 403 */     return "mob.skeleton.death";
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 408 */     return Items.field_151103_aS;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 413 */     func_70099_a(new ItemStack(Items.field_151144_bL, 1, 0), 0.0F);
/* 414 */     func_70099_a(new ItemStack(Items.field_151103_aS, 5, 0), 0.0F);
/* 415 */     Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
/* 416 */     int k = MathHelper.func_76136_a(this.field_70146_Z, Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), enchantment.func_77325_b());
/*     */     
/* 418 */     ItemStack itemstack = Items.field_151134_bR.func_92111_a(new net.minecraft.enchantment.EnchantmentData(enchantment, k));
/* 419 */     func_70099_a(itemstack, 0.0F);
/*     */     
/* 421 */     if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 422 */       ItemStack sword = new ItemStack(Items.field_151048_u);
/* 423 */       EnchantmentHelper.func_77504_a(this.field_70170_p.field_73012_v, sword, 40);
/* 424 */       sword.func_151001_c(Witchery.resource("item.witchery.swordofdeath.customname"));
/* 425 */       func_70099_a(sword, 0.0F);
/*     */     }
/*     */     
/* 428 */     switch (this.field_70170_p.field_73012_v.nextInt(5)) {
/*     */     case 0: 
/* 430 */       func_70099_a(new ItemStack(Items.field_151141_av), 0.0F);
/* 431 */       func_70099_a(Witchery.Items.GENERIC.itemBinkyHead.createStack(), 0.0F);
/* 432 */       break;
/*     */     case 1: 
/* 434 */       func_70099_a(new ItemStack(Witchery.Items.DEATH_HOOD), 0.0F);
/* 435 */       break;
/*     */     case 2: 
/* 437 */       func_70099_a(new ItemStack(Witchery.Items.DEATH_ROBE), 0.0F);
/* 438 */       break;
/*     */     case 3: 
/* 440 */       func_70099_a(new ItemStack(Witchery.Items.DEATH_FEET), 0.0F);
/* 441 */       break;
/*     */     case 4: 
/* 443 */       func_70099_a(new ItemStack(Witchery.Items.DEATH_HAND), 0.0F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 450 */     return 15.0F;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 455 */     if (func_85032_ar()) {
/* 456 */       return false;
/*     */     }
/* 458 */     setScreaming(true);
/*     */     
/* 460 */     if (((par1DamageSource instanceof EntityDamageSource)) && ((par1DamageSource.func_76346_g() instanceof EntityPlayer))) {
/* 461 */       this.isAggressive = true;
/*     */     }
/*     */     
/* 464 */     if ((par1DamageSource instanceof EntityDamageSourceIndirect)) {
/* 465 */       this.isAggressive = false;
/*     */       
/* 467 */       for (int i = 0; i < 64; i++) {
/* 468 */         if (teleportRandomly()) {
/* 469 */           return true;
/*     */         }
/*     */       }
/*     */       
/* 473 */       return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0F));
/*     */     }
/* 475 */     return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0F));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 482 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 483 */     int i = 0;
/*     */     
/* 485 */     if ((par1Entity instanceof EntityLivingBase)) {
/* 486 */       EntityLivingBase living = (EntityLivingBase)par1Entity;
/* 487 */       float maxHealth = living.func_110138_aP();
/* 488 */       f = Math.max(maxHealth * 0.15F, 1.0F);
/*     */     }
/*     */     
/* 491 */     if ((par1Entity instanceof EntityLivingBase)) {
/* 492 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)par1Entity);
/* 493 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)par1Entity);
/*     */     }
/*     */     
/* 496 */     boolean flag = com.emoniph.witchery.util.EntityUtil.touchOfDeath(par1Entity, this, f);
/*     */     
/*     */ 
/* 499 */     if (flag) {
/* 500 */       if (i > 0) {
/* 501 */         par1Entity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/*     */         
/* 503 */         this.field_70159_w *= 0.6D;
/* 504 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 507 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 509 */       if (j > 0) {
/* 510 */         par1Entity.func_70015_d(j * 4);
/*     */       }
/*     */     }
/*     */     
/* 514 */     return flag;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 520 */     super.func_70645_a(par1DamageSource);
/* 521 */     if (!this.field_70170_p.field_72995_K) {
/* 522 */       ParticleEffect.PORTAL.send(com.emoniph.witchery.util.SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/*     */     }
/* 524 */     func_70106_y();
/*     */   }
/*     */   
/*     */   public boolean isScreaming() {
/* 528 */     return this.field_70180_af.func_75683_a(18) > 0;
/*     */   }
/*     */   
/*     */   public void setScreaming(boolean par1) {
/* 532 */     this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityDeath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */