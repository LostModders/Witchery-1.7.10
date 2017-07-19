/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityHellhound extends EntityMob implements IEntitySelector
/*     */ {
/*     */   private float field_70926_e;
/*     */   private float field_70924_f;
/*     */   private boolean isShaking;
/*     */   private boolean field_70928_h;
/*     */   private float timeWolfIsShaking;
/*     */   private float prevTimeWolfIsShaking;
/*     */   private int conversionTime;
/*     */   
/*     */   public EntityHellhound(World world)
/*     */   {
/*  50 */     super(world);
/*  51 */     this.field_70178_ae = true;
/*  52 */     func_70105_a(0.9F, 0.9F);
/*  53 */     func_70661_as().func_75491_a(true);
/*  54 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  55 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAILeapAtTarget(this, 0.4F));
/*  56 */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, 1.0D, true));
/*  57 */     this.field_70714_bg.func_75776_a(4, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  58 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  59 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  60 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*  61 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.passive.EntitySheep.class, 0, true, true));
/*  62 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, true, this));
/*     */     
/*  64 */     this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTarget(this, EntityFollower.class, 0, true, true, this));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82704_a(Entity entity)
/*     */   {
/*  70 */     double AGGRO_RANGE = 5.0D;
/*  71 */     return (entity != null) && (entity.func_70068_e(this) < 25.0D);
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  76 */     super.func_110147_ax();
/*  77 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
/*  78 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  79 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */   public int func_70658_aO() {
/*  83 */     int i = super.func_70658_aO() + 2;
/*     */     
/*  85 */     if (i > 20) {
/*  86 */       i = 20;
/*     */     }
/*     */     
/*  89 */     return i;
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/*  94 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/*  99 */     this.field_70180_af.func_75692_b(18, Float.valueOf(func_110143_aJ()));
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 104 */     super.func_70088_a();
/* 105 */     this.field_70180_af.func_75682_a(18, new Float(func_110143_aJ()));
/* 106 */     this.field_70180_af.func_75682_a(19, new Byte((byte)0));
/* 107 */     func_70096_w().func_75682_a(14, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   protected void convertToWolf() {
/* 111 */     EntityWolf entityvillager = new EntityWolf(this.field_70170_p);
/* 112 */     entityvillager.func_82149_j(this);
/* 113 */     entityvillager.func_110161_a((IEntityLivingData)null);
/* 114 */     this.field_70170_p.func_72900_e(this);
/* 115 */     this.field_70170_p.func_72838_d(entityvillager);
/* 116 */     entityvillager.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
/* 117 */     this.field_70170_p.func_72889_a((EntityPlayer)null, 1017, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer p_70085_1_)
/*     */   {
/* 122 */     ItemStack itemstack = p_70085_1_.func_71045_bC();
/*     */     
/* 124 */     if ((itemstack != null) && (itemstack.func_77973_b() == net.minecraft.init.Items.field_151153_ao) && (itemstack.func_77960_j() == 0) && (func_70644_a(Potion.field_76437_t)))
/*     */     {
/* 126 */       if (!p_70085_1_.field_71075_bZ.field_75098_d) {
/* 127 */         itemstack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 130 */       if (itemstack.field_77994_a <= 0) {
/* 131 */         p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
/*     */       }
/*     */       
/* 134 */       if (!this.field_70170_p.field_72995_K) {
/* 135 */         startConversion(this.field_70146_Z.nextInt(1000) + 3600);
/*     */       }
/*     */       
/* 138 */       return true;
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
/*     */   {
/* 146 */     func_85030_a("mob.wolf.step", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 151 */     super.func_70014_b(nbtRoot);
/*     */     
/* 153 */     nbtRoot.func_74768_a("ConversionTime", isConverting() ? this.conversionTime : -1);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 158 */     super.func_70037_a(nbtRoot);
/*     */     
/* 160 */     if ((nbtRoot.func_150297_b("ConversionTime", 99)) && (nbtRoot.func_74762_e("ConversionTime") > -1)) {
/* 161 */       startConversion(nbtRoot.func_74762_e("ConversionTime"));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void startConversion(int p_82228_1_) {
/* 166 */     this.conversionTime = p_82228_1_;
/* 167 */     func_70096_w().func_75692_b(14, Byte.valueOf((byte)1));
/* 168 */     func_82170_o(Potion.field_76437_t.field_76415_H);
/* 169 */     func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, p_82228_1_, Math.min(this.field_70170_p.field_73013_u.func_151525_a() - 1, 0)));
/*     */     
/* 171 */     this.field_70170_p.func_72960_a(this, (byte)16);
/*     */   }
/*     */   
/*     */   protected int getConversionTimeBoost() {
/* 175 */     int i = 1;
/*     */     
/* 177 */     if (this.field_70146_Z.nextFloat() < 0.01F) {
/* 178 */       int j = 0;
/*     */       
/* 180 */       for (int k = (int)this.field_70165_t - 4; (k < (int)this.field_70165_t + 4) && (j < 14); k++) {
/* 181 */         for (int l = (int)this.field_70163_u - 4; (l < (int)this.field_70163_u + 4) && (j < 14); l++) {
/* 182 */           for (int i1 = (int)this.field_70161_v - 4; (i1 < (int)this.field_70161_v + 4) && (j < 14); i1++) {
/* 183 */             Block block = this.field_70170_p.func_147439_a(k, l, i1);
/*     */             
/* 185 */             if ((block == Blocks.field_150411_aY) || (block == Blocks.field_150324_C)) {
/* 186 */               if (this.field_70146_Z.nextFloat() < 0.3F) {
/* 187 */                 i++;
/*     */               }
/*     */               
/* 190 */               j++;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 197 */     return i;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 202 */     return "mob.wolf.growl";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 207 */     return "mob.wolf.hurt";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 212 */     return "mob.wolf.death";
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/* 217 */     return 0.4F;
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 222 */     return Item.func_150899_d(-1);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean recentlyHitByPlayer, int looting) {
/* 226 */     func_70099_a(Witchery.Items.GENERIC.itemDogTongue.createStack(), 0.0F);
/* 227 */     if (this.field_70170_p.field_73012_v.nextInt(12) <= Math.min(looting, 3)) {
/* 228 */       func_70099_a(new ItemStack(Witchery.Blocks.WOLFHEAD, 1, 1), 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 234 */     super.func_70636_d();
/* 235 */     if ((!this.field_70170_p.field_72995_K) && (!this.field_70128_L) && (this.isShaking) && (!this.field_70928_h) && (!func_70781_l()) && (this.field_70122_E)) {
/* 236 */       this.field_70928_h = true;
/* 237 */       this.timeWolfIsShaking = 0.0F;
/* 238 */       this.prevTimeWolfIsShaking = 0.0F;
/* 239 */       this.field_70170_p.func_72960_a(this, (byte)8);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 245 */     if ((!this.field_70170_p.field_72995_K) && (isConverting())) {
/* 246 */       int i = getConversionTimeBoost();
/* 247 */       this.conversionTime -= i;
/*     */       
/* 249 */       if (this.conversionTime <= 0) {
/* 250 */         convertToWolf();
/*     */       }
/*     */     }
/*     */     
/* 254 */     super.func_70071_h_();
/* 255 */     if (!this.field_70128_L) {
/* 256 */       this.field_70924_f = this.field_70926_e;
/*     */       
/* 258 */       if (func_70922_bv()) {
/* 259 */         this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
/*     */       } else {
/* 261 */         this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
/*     */       }
/*     */       
/* 264 */       if (func_70922_bv()) {
/* 265 */         this.field_70700_bx = 10;
/*     */       }
/*     */       
/* 268 */       if (func_70026_G()) {
/* 269 */         this.isShaking = true;
/* 270 */         this.field_70928_h = false;
/* 271 */         this.timeWolfIsShaking = 0.0F;
/* 272 */         this.prevTimeWolfIsShaking = 0.0F;
/* 273 */       } else if (((this.isShaking) || (this.field_70928_h)) && (this.field_70928_h)) {
/* 274 */         if (this.timeWolfIsShaking == 0.0F) {
/* 275 */           func_85030_a("mob.wolf.shake", func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/*     */         }
/*     */         
/*     */ 
/* 279 */         this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
/* 280 */         this.timeWolfIsShaking += 0.05F;
/*     */         
/* 282 */         if (this.prevTimeWolfIsShaking >= 2.0F) {
/* 283 */           this.isShaking = false;
/* 284 */           this.field_70928_h = false;
/* 285 */           this.prevTimeWolfIsShaking = 0.0F;
/* 286 */           this.timeWolfIsShaking = 0.0F;
/*     */         }
/*     */         
/* 289 */         if (this.timeWolfIsShaking > 0.4F) {
/* 290 */           float f = (float)this.field_70121_D.field_72338_b;
/* 291 */           int i = (int)(MathHelper.func_76126_a((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);
/*     */           
/* 293 */           for (int j = 0; j < i; j++) {
/* 294 */             float f1 = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N * 0.5F;
/* 295 */             float f2 = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N * 0.5F;
/* 296 */             this.field_70170_p.func_72869_a("splash", this.field_70165_t + f1, f + 0.8F, this.field_70161_v + f2, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 302 */       if ((this.field_70170_p.field_72995_K) && (this.field_70173_aa % 2 == 0)) {
/* 303 */         this.field_70170_p.func_72869_a(ParticleEffect.FLAME.toString(), this.field_70165_t - this.field_70130_N * 0.35D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N * 0.7D, 0.5D + this.field_70163_u + this.field_70170_p.field_73012_v.nextDouble() * (this.field_70131_O - 0.1D), this.field_70161_v - this.field_70130_N * 0.35D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N * 0.7D, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean getWolfShaking()
/*     */   {
/* 313 */     return this.isShaking;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getShadingWhileShaking(float p_70915_1_) {
/* 318 */     return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70915_1_) / 2.0F * 0.25F;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getShakeAngle(float p_70923_1_, float p_70923_2_)
/*     */   {
/* 324 */     float f2 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;
/*     */     
/*     */ 
/* 327 */     if (f2 < 0.0F) {
/* 328 */       f2 = 0.0F;
/* 329 */     } else if (f2 > 1.0F) {
/* 330 */       f2 = 1.0F;
/*     */     }
/*     */     
/* 333 */     return MathHelper.func_76126_a(f2 * 3.1415927F) * MathHelper.func_76126_a(f2 * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 339 */     return this.field_70131_O * 0.8F;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getInterestedAngle(float p_70917_1_) {
/* 344 */     return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * p_70917_1_) * 0.15F * 3.1415927F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
/*     */   {
/* 350 */     if (func_85032_ar()) {
/* 351 */       return false;
/*     */     }
/* 353 */     Entity entity = p_70097_1_.func_76346_g();
/*     */     
/* 355 */     if ((entity != null) && (!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityArrow))) {
/* 356 */       p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
/*     */     }
/*     */     
/* 359 */     return super.func_70097_a(p_70097_1_, p_70097_2_);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity p_70652_1_)
/*     */   {
/* 365 */     boolean flag = super.func_70652_k(p_70652_1_);
/*     */     
/* 367 */     if (flag) {
/* 368 */       int i = this.field_70170_p.field_73013_u.func_151525_a();
/*     */       
/* 370 */       if (this.field_70146_Z.nextFloat() < i * 0.1F) {
/* 371 */         p_70652_1_.func_70015_d(2 * i);
/*     */       }
/*     */     }
/*     */     
/* 375 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_)
/*     */   {
/* 381 */     if (p_70103_1_ == 8) {
/* 382 */       this.field_70928_h = true;
/* 383 */       this.timeWolfIsShaking = 0.0F;
/* 384 */       this.prevTimeWolfIsShaking = 0.0F;
/*     */     } else {
/* 386 */       super.func_70103_a(p_70103_1_);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getTailRotation() {
/* 392 */     return 1.5393804F;
/*     */   }
/*     */   
/*     */   public int func_70641_bl()
/*     */   {
/* 397 */     return super.func_70641_bl();
/*     */   }
/*     */   
/*     */   public void func_70918_i(boolean p_70918_1_) {
/* 401 */     if (p_70918_1_) {
/* 402 */       this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)1));
/*     */     } else {
/* 404 */       this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)0));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70922_bv() {
/* 409 */     return this.field_70180_af.func_75683_a(19) == 1;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 414 */     return !isConverting();
/*     */   }
/*     */   
/*     */   public boolean isConverting() {
/* 418 */     return func_70096_w().func_75683_a(14) == 1;
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi() {
/* 422 */     return (this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) && (this.field_70170_p.func_72855_b(this.field_70121_D)) && (this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityHellhound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */