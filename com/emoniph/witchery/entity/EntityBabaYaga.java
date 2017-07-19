/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityCaveSpider;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityBabaYaga extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IEntitySelector, IOwnable, IHandleDT
/*     */ {
/*  55 */   private static final UUID field_110184_bp = UUID.fromString("ab0df555-0786-4951-a8df-ca61749f164e");
/*  56 */   private static final AttributeModifier field_110185_bq = new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0).func_111168_a(false);
/*     */   
/*     */ 
/*  59 */   private static final int[] witchDrops = { Witchery.Items.GENERIC.itemSpectralDust.damageValue, Witchery.Items.GENERIC.itemBatWool.damageValue, Witchery.Items.GENERIC.itemToeOfFrog.damageValue, Witchery.Items.GENERIC.itemOwletsWing.damageValue, Witchery.Items.GENERIC.itemDogTongue.damageValue, Witchery.Items.GENERIC.itemBrewOfVines.damageValue, Witchery.Items.GENERIC.itemBrewOfSprouting.damageValue, Witchery.Items.GENERIC.itemBrewOfHitchcock.damageValue, Witchery.Items.GENERIC.itemBrewOfCursedLeaping.damageValue, Witchery.Items.GENERIC.itemBrewOfFrogsTongue.damageValue };
/*     */   
/*     */   private int witchAttackTimer;
/*     */   
/*     */   private static final double MAX_HEALTH = 500.0D;
/*     */   
/*     */ 
/*     */   public EntityBabaYaga(World par1World)
/*     */   {
/*  68 */     super(par1World);
/*  69 */     func_70661_as().func_75491_a(true);
/*  70 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  71 */     this.field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
/*  72 */     this.field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
/*  73 */     this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  74 */     this.field_70714_bg.func_75776_a(3, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  75 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  76 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, this));
/*  77 */     this.field_70728_aV = 70;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  82 */     super.func_70088_a();
/*  83 */     func_70096_w().func_75682_a(21, Byte.valueOf((byte)0));
/*  84 */     this.field_70180_af.func_75682_a(17, "");
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  89 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/*  91 */     if (getOwnerName() == null) {
/*  92 */       par1NBTTagCompound.func_74778_a("Owner", "");
/*     */     } else {
/*  94 */       par1NBTTagCompound.func_74778_a("Owner", getOwnerName());
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 100 */     super.func_70037_a(par1NBTTagCompound);
/* 101 */     String s = par1NBTTagCompound.func_74779_i("Owner");
/*     */     
/* 103 */     if (s.length() > 0) {
/* 104 */       setOwner(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getOwnerName() {
/* 109 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setOwner(String par1Str) {
/* 113 */     this.field_70180_af.func_75692_b(17, par1Str);
/*     */   }
/*     */   
/*     */   public EntityPlayer getOwnerEntity() {
/* 117 */     return this.field_70170_p.func_72924_a(getOwnerName());
/*     */   }
/*     */   
/*     */   public boolean func_82704_a(Entity entity)
/*     */   {
/* 122 */     if ((entity != null) && 
/* 123 */       ((entity instanceof EntityPlayer))) {
/* 124 */       String ownerName = getOwnerName();
/* 125 */       boolean isOwned = ((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase(ownerName);
/* 126 */       return !isOwned;
/*     */     }
/*     */     
/*     */ 
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 135 */     if (func_94056_bM()) {
/* 136 */       return func_94057_bL();
/*     */     }
/* 138 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.babayaga.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 144 */     return 4;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 149 */     return "witchery:mob.baba.baba_living";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 154 */     return "mob.witch.hurt";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 159 */     return "witchery:mob.baba.baba_death";
/*     */   }
/*     */   
/*     */   public void setAggressive(boolean par1) {
/* 163 */     func_70096_w().func_75692_b(21, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public boolean getAggressive() {
/* 167 */     return func_70096_w().func_75683_a(21) == 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 174 */     super.func_110147_ax();
/* 175 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
/* 176 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 182 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 192 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 197 */     if (!this.field_70170_p.field_72995_K) {
/* 198 */       if (getAggressive()) {
/* 199 */         if (this.witchAttackTimer-- <= 0) {
/* 200 */           setAggressive(false);
/* 201 */           ItemStack itemstack = func_70694_bm();
/* 202 */           func_70062_b(0, (ItemStack)null);
/*     */           
/* 204 */           if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151068_bn)) {
/* 205 */             List list = Items.field_151068_bn.func_77832_l(itemstack);
/*     */             
/* 207 */             if (list != null) {
/* 208 */               Iterator iterator = list.iterator();
/*     */               
/* 210 */               while (iterator.hasNext()) {
/* 211 */                 PotionEffect potioneffect = (PotionEffect)iterator.next();
/* 212 */                 func_70690_d(new PotionEffect(potioneffect));
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 217 */           func_110148_a(SharedMonsterAttributes.field_111263_d).func_111124_b(field_110185_bq);
/*     */         }
/*     */       } else {
/* 220 */         short short1 = -1;
/*     */         
/* 222 */         if ((this.field_70146_Z.nextFloat() < 0.15F) && (func_70027_ad()) && (!func_70644_a(Potion.field_76426_n))) {
/* 223 */           short1 = 16307;
/* 224 */         } else if ((this.field_70146_Z.nextFloat() < 0.01F) && (func_110143_aJ() < func_110138_aP())) {
/* 225 */           short1 = 16341;
/* 226 */         } else if ((this.field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
/*     */         {
/* 228 */           short1 = 16274;
/* 229 */         } else if ((this.field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
/*     */         {
/* 231 */           short1 = 16274;
/*     */         }
/*     */         
/* 234 */         if (short1 > -1) {
/* 235 */           func_70062_b(0, new ItemStack(Items.field_151068_bn, 1, short1));
/* 236 */           this.witchAttackTimer = (func_70694_bm().func_77988_m() - 20);
/* 237 */           setAggressive(true);
/* 238 */           IAttributeInstance attributeinstance = func_110148_a(SharedMonsterAttributes.field_111263_d);
/* 239 */           attributeinstance.func_111124_b(field_110185_bq);
/* 240 */           attributeinstance.func_111121_a(field_110185_bq);
/*     */         }
/*     */       }
/*     */       
/* 244 */       if (this.field_70146_Z.nextFloat() < 7.5E-4F) {
/* 245 */         this.field_70170_p.func_72960_a(this, (byte)15);
/*     */       }
/*     */       
/* 248 */       if (((func_70661_as().func_75500_f()) || (this.field_70170_p.field_73012_v.nextDouble() < 0.02D)) && (func_70638_az() != null) && (this.field_70173_aa - this.ticksSinceTeleport > 100L))
/*     */       {
/* 250 */         this.ticksSinceTeleport = this.field_70173_aa;
/* 251 */         teleportToEntity(func_70638_az());
/*     */       }
/*     */       
/* 254 */       if ((this.field_70170_p.field_73012_v.nextDouble() < 0.05D) && (func_70638_az() != null) && ((func_70638_az().field_70160_al) || (((func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)func_70638_az()).field_71075_bZ.field_75100_b))) && (!func_70638_az().func_70644_a(Potion.field_76421_d)))
/*     */       {
/*     */ 
/*     */ 
/* 258 */         func_70638_az().func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 5));
/*     */       }
/*     */       
/* 261 */       EntityPlayer owner = getOwnerEntity();
/* 262 */       if (owner != null) {
/* 263 */         double distance = func_70068_e(owner);
/* 264 */         if ((distance < 64.0D) && (this.field_70173_aa % 100 == 0)) {
/* 265 */           int l = this.field_70146_Z.nextInt(3);
/* 266 */           int i1 = witchDrops[this.field_70146_Z.nextInt(witchDrops.length - 3)];
/* 267 */           for (int j1 = 0; j1 < l; j1++) {
/* 268 */             func_70099_a(new ItemStack(Witchery.Items.GENERIC, 1, i1), 0.0F);
/*     */           }
/*     */         }
/*     */         
/* 272 */         if (this.field_70173_aa > 600) {
/* 273 */           func_70106_y();
/* 274 */           ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 279 */     super.func_70636_d();
/*     */   }
/*     */   
/* 282 */   long ticksSinceTeleport = 0L;
/*     */   
/*     */   protected boolean teleportToEntity(Entity par1Entity) {
/* 285 */     Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t - par1Entity.field_70165_t, this.field_70121_D.field_72338_b + this.field_70131_O / 2.0F - par1Entity.field_70163_u + par1Entity.func_70047_e(), this.field_70161_v - par1Entity.field_70161_v);
/*     */     
/* 287 */     vec3 = vec3.func_72432_b();
/* 288 */     double d0 = 8.0D;
/* 289 */     double d1 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72450_a * d0;
/* 290 */     double d2 = this.field_70163_u + (this.field_70146_Z.nextInt(16) - 8) - vec3.field_72448_b * d0;
/* 291 */     double d3 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72449_c * d0;
/* 292 */     return teleportTo(d1, d2, d3);
/*     */   }
/*     */   
/*     */   protected boolean teleportTo(double par1, double par3, double par5) {
/* 296 */     double d3 = this.field_70165_t;
/* 297 */     double d4 = this.field_70163_u;
/* 298 */     double d5 = this.field_70161_v;
/* 299 */     this.field_70165_t = par1;
/* 300 */     this.field_70163_u = par3;
/* 301 */     this.field_70161_v = par5;
/* 302 */     boolean flag = false;
/* 303 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 304 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 305 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 307 */     if (this.field_70170_p.func_72899_e(i, j, k)) {
/* 308 */       boolean flag1 = false;
/*     */       
/* 310 */       while ((!flag1) && (j > 0)) {
/* 311 */         Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
/*     */         
/* 313 */         if (block.func_149688_o().func_76230_c()) {
/* 314 */           flag1 = true;
/*     */         } else {
/* 316 */           this.field_70163_u -= 1.0D;
/* 317 */           j--;
/*     */         }
/*     */       }
/*     */       
/* 321 */       if (flag1) {
/* 322 */         func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */         
/* 324 */         if ((this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */         {
/* 326 */           flag = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 331 */     if (!flag) {
/* 332 */       func_70107_b(d3, d4, d5);
/* 333 */       return false;
/*     */     }
/* 335 */     short short1 = 128;
/*     */     
/* 337 */     for (int l = 0; l < short1; l++) {
/* 338 */       double d6 = l / (short1 - 1.0D);
/* 339 */       float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 340 */       float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 341 */       float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 342 */       double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 343 */       double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * this.field_70131_O;
/* 344 */       double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 345 */       this.field_70170_p.func_72869_a("portal", d7, d8, d9, f, f1, f2);
/*     */     }
/*     */     
/* 348 */     this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 349 */     func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
/* 350 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70672_c(DamageSource par1DamageSource, float par2)
/*     */   {
/* 356 */     par2 = super.func_70672_c(par1DamageSource, par2);
/*     */     
/* 358 */     if (par1DamageSource.func_76346_g() == this) {
/* 359 */       par2 = 0.0F;
/*     */     }
/*     */     
/* 362 */     if (par1DamageSource.func_82725_o()) {
/* 363 */       par2 = (float)(par2 * 0.15D);
/*     */     }
/*     */     
/* 366 */     return par2;
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 372 */     if (par1 == 15) {
/* 373 */       for (int i = 0; i < this.field_70146_Z.nextInt(35) + 10; i++) {
/* 374 */         this.field_70170_p.func_72869_a("witchMagic", this.field_70165_t + this.field_70146_Z.nextGaussian() * 0.12999999523162842D, this.field_70121_D.field_72337_e + 0.5D + this.field_70146_Z.nextGaussian() * 0.12999999523162842D, this.field_70161_v + this.field_70146_Z.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 379 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 385 */     boolean result = super.func_70097_a(source, Math.min(damage, 15.0F));
/* 386 */     if ((!this.field_70170_p.field_72995_K) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLiving))) {
/* 387 */       EntityLiving attacker = (EntityLiving)source.func_76346_g();
/* 388 */       if ((attacker.func_70668_bt() == EnumCreatureAttribute.UNDEAD) || (((attacker instanceof EntityTameable)) && (!((EntityTameable)attacker).func_70909_n())))
/*     */       {
/* 390 */         EntityCaveSpider spider = new EntityCaveSpider(this.field_70170_p);
/* 391 */         spider.func_70012_b(attacker.field_70165_t, attacker.field_70163_u, attacker.field_70161_v, attacker.field_70125_A, attacker.field_70177_z);
/* 392 */         EntityLivingBase target = func_70638_az();
/*     */         
/* 394 */         spider.func_70624_b(target);
/* 395 */         spider.func_70604_c(target);
/* 396 */         spider.func_70784_b(target);
/* 397 */         this.field_70170_p.func_72838_d(spider);
/*     */         
/* 399 */         ParticleEffect.MOB_SPELL.send(SoundEffect.RANDOM_POP, spider, 2.0D, 2.0D, 16);
/* 400 */         attacker.func_70106_y();
/*     */       }
/*     */     }
/*     */     
/* 404 */     if ((!this.field_70170_p.field_72995_K) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
/* 405 */       EntityPlayer player = (EntityPlayer)source.func_76346_g();
/* 406 */       if (!com.emoniph.witchery.util.CreatureUtil.isWoodenDamage(source)) {
/* 407 */         player.func_70097_a(DamageSource.func_76354_b(this, player), damage * 0.25F);
/*     */       }
/*     */     }
/* 410 */     return result;
/*     */   }
/*     */   
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 415 */     return 15.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 421 */     super.func_70645_a(par1DamageSource);
/* 422 */     if (!this.field_70170_p.field_72995_K) {
/* 423 */       ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/*     */     }
/* 425 */     func_70106_y();
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 430 */     int j = this.field_70146_Z.nextInt(3) + 2;
/*     */     
/* 432 */     for (int k = 0; k < j; k++) {
/* 433 */       int l = this.field_70146_Z.nextInt(3) + 1;
/* 434 */       int i1 = witchDrops[this.field_70146_Z.nextInt(witchDrops.length)];
/*     */       
/* 436 */       if (par2 > 0) {
/* 437 */         l += this.field_70146_Z.nextInt(par2 + 1);
/*     */       }
/*     */       
/* 440 */       for (int j1 = 0; j1 < l; j1++) {
/* 441 */         func_70099_a(new ItemStack(Witchery.Items.GENERIC, 1, i1), 0.0F);
/*     */       }
/*     */     }
/*     */     
/* 445 */     Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
/* 446 */     int k = MathHelper.func_76136_a(this.field_70146_Z, Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), enchantment.func_77325_b());
/*     */     
/* 448 */     ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
/* 449 */     func_70099_a(itemstack, 0.0F);
/* 450 */     func_70099_a(new ItemStack(Witchery.Items.BABAS_HAT), 0.0F);
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 455 */     if (!getAggressive()) {
/* 456 */       if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 457 */         ItemGeneral.SubItem brew = null;
/* 458 */         switch (this.field_70170_p.field_73012_v.nextInt(12)) {
/*     */         case 0: 
/*     */         case 1: 
/* 461 */           brew = Witchery.Items.GENERIC.itemBrewOfWebs;
/* 462 */           break;
/*     */         case 2: 
/*     */         case 3: 
/* 465 */           brew = Witchery.Items.GENERIC.itemBrewOfThorns;
/* 466 */           break;
/*     */         case 4: 
/*     */         case 5: 
/* 469 */           brew = Witchery.Items.GENERIC.itemBrewOfFrogsTongue;
/* 470 */           break;
/*     */         case 6: 
/*     */         case 7: 
/* 473 */           brew = Witchery.Items.GENERIC.itemBrewOfInk;
/* 474 */           break;
/*     */         case 8: 
/*     */         case 9: 
/* 477 */           brew = Witchery.Items.GENERIC.itemBrewOfHitchcock;
/* 478 */           break;
/*     */         case 10: 
/* 480 */           brew = Witchery.Items.GENERIC.itemBrewOfBats;
/* 481 */           break;
/*     */         case 11: 
/* 483 */           brew = Witchery.Items.GENERIC.itemBrewOfWasting;
/* 484 */           break;
/*     */         default: 
/* 486 */           return;
/*     */         }
/* 488 */         EntityWitchProjectile entitypotion = new EntityWitchProjectile(this.field_70170_p, this, brew);
/* 489 */         entitypotion.field_70125_A -= -20.0F;
/* 490 */         double d0 = par1EntityLivingBase.field_70165_t + par1EntityLivingBase.field_70159_w - this.field_70165_t;
/* 491 */         double d1 = par1EntityLivingBase.field_70163_u + par1EntityLivingBase.func_70047_e() - 1.100000023841858D - this.field_70163_u;
/* 492 */         double d2 = par1EntityLivingBase.field_70161_v + par1EntityLivingBase.field_70179_y - this.field_70161_v;
/* 493 */         float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 494 */         entitypotion.func_70186_c(d0, d1 + f1 * 0.2F, d2, 0.75F, 8.0F);
/* 495 */         this.field_70170_p.func_72838_d(entitypotion);
/*     */       } else {
/* 497 */         EntityPotion entitypotion = new EntityPotion(this.field_70170_p, this, 32732);
/* 498 */         entitypotion.field_70125_A -= -20.0F;
/* 499 */         double d0 = par1EntityLivingBase.field_70165_t + par1EntityLivingBase.field_70159_w - this.field_70165_t;
/* 500 */         double d1 = par1EntityLivingBase.field_70163_u + par1EntityLivingBase.func_70047_e() - 1.100000023841858D - this.field_70163_u;
/* 501 */         double d2 = par1EntityLivingBase.field_70161_v + par1EntityLivingBase.field_70179_y - this.field_70161_v;
/* 502 */         float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/*     */         
/* 504 */         if ((f1 >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76421_d))) {
/* 505 */           entitypotion.func_82340_a(32698);
/* 506 */         } else if ((par1EntityLivingBase.func_110143_aJ() >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76436_u))) {
/* 507 */           entitypotion.func_82340_a(32660);
/* 508 */         } else if ((f1 <= 3.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76437_t)) && (this.field_70146_Z.nextFloat() < 0.25F)) {
/* 509 */           entitypotion.func_82340_a(32696);
/*     */         }
/*     */         
/* 512 */         entitypotion.func_70186_c(d0, d1 + f1 * 0.2F, d2, 0.75F, 8.0F);
/* 513 */         this.field_70170_p.func_72838_d(entitypotion);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityBabaYaga.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */