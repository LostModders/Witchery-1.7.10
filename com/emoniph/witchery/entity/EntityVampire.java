/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockBloodCrucible.TileEntityBloodCrucible;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.ExtendedVillager;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityVampire extends EntityCreature implements IEntitySelector, IHandleDT
/*     */ {
/*     */   private Village villageObj;
/*     */   
/*     */   public EntityVampire(World world)
/*     */   {
/*  51 */     super(world);
/*  52 */     func_70661_as().func_75491_a(true);
/*  53 */     func_70661_as().func_75498_b(true);
/*     */     
/*  55 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  56 */     this.field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAIRestrictSun(this));
/*  57 */     this.field_70714_bg.func_75776_a(3, new net.minecraft.entity.ai.EntityAIFleeSun(this, 1.0D));
/*     */     
/*     */ 
/*  60 */     this.field_70714_bg.func_75776_a(8, new EntityAIRestrictOpenDoor(this));
/*  61 */     this.field_70714_bg.func_75776_a(8, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.2D, false));
/*  62 */     this.field_70714_bg.func_75776_a(9, new EntityAIOpenDoor(this, true));
/*  63 */     this.field_70714_bg.func_75776_a(10, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  64 */     this.field_70714_bg.func_75776_a(11, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  65 */     this.field_70714_bg.func_75776_a(12, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  66 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  67 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, false, true, this));
/*     */     
/*     */ 
/*  70 */     this.field_70728_aV = 20;
/*     */   }
/*     */   
/*     */   public boolean func_82704_a(Entity entity) {
/*  74 */     return (((entity instanceof EntityVillager)) && (this.villageObj != null)) || (((entity instanceof EntityPlayer)) && (!ExtendedPlayer.get((EntityPlayer)entity).isVampire()));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70629_bd()
/*     */   {
/*  80 */     super.func_70629_bd();
/*     */     
/*  82 */     if (!this.field_70170_p.field_72995_K) {
/*  83 */       if (this.field_70170_p.func_72935_r()) {
/*  84 */         if (func_70643_av() == null) {
/*  85 */           func_70624_b(null);
/*     */         }
/*  87 */         if (this.field_70173_aa % 100 == 2) {
/*  88 */           this.villageObj = null;
/*  89 */           this.damageDone = 0.0F;
/*  90 */           if (func_70092_e(this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c) > 16.0D) {
/*  91 */             ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
/*  92 */             EntityUtil.moveToBlockPositionAndUpdate(this, this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 8);
/*     */             
/*  94 */             ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
/*  95 */             func_110171_b(this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 4);
/*     */           }
/*     */         }
/*  98 */         if ((this.field_70173_aa % 20 == 2) && 
/*  99 */           (CreatureUtil.isInSunlight(this))) {
/* 100 */           func_70015_d(2);
/*     */         }
/*     */         
/*     */       }
/* 104 */       else if (this.damageDone >= 20.0F) {
/* 105 */         if (this.villageObj != null) {
/* 106 */           func_70624_b(null);
/* 107 */           func_70604_c(null);
/* 108 */           this.villageObj = null;
/* 109 */           ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
/* 110 */           EntityUtil.moveToBlockPositionAndUpdate(this, this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 8);
/*     */           
/* 112 */           ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
/* 113 */           func_110171_b(this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 4);
/* 114 */           tryFillBloodCrucible();
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 119 */       else if ((this.villageObj == null) && (this.field_70173_aa % 500 == 2)) {
/* 120 */         this.villageObj = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), 128);
/*     */         
/*     */ 
/* 123 */         if (this.villageObj != null) {
/* 124 */           ChunkCoordinates townPos = this.villageObj.func_75577_a();
/* 125 */           ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
/* 126 */           EntityUtil.moveToBlockPositionAndUpdate(this, townPos.field_71574_a, townPos.field_71572_b, townPos.field_71573_c, 8);
/*     */           
/* 128 */           ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
/* 129 */           func_110171_b(townPos.field_71574_a, townPos.field_71572_b, townPos.field_71573_c, this.villageObj.func_75568_b());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void tryFillBloodCrucible()
/*     */   {
/* 138 */     int r = 6;
/* 139 */     for (int x = this.coffinPos.field_71574_a - 6; x <= this.coffinPos.field_71574_a + 6; x++) {
/* 140 */       for (int z = this.coffinPos.field_71573_c - 6; z <= this.coffinPos.field_71573_c + 6; z++) {
/* 141 */         for (int y = this.coffinPos.field_71572_b - 6; y <= this.coffinPos.field_71572_b + 6; y++) {
/* 142 */           if (this.field_70170_p.func_147439_a(x, y, z) == Witchery.Blocks.BLOOD_CRUCIBLE) {
/* 143 */             BlockBloodCrucible.TileEntityBloodCrucible crucible = (BlockBloodCrucible.TileEntityBloodCrucible)BlockUtil.getTileEntity(this.field_70170_p, x, y, z, BlockBloodCrucible.TileEntityBloodCrucible.class);
/*     */             
/* 145 */             if (crucible != null) {
/* 146 */               crucible.increaseBloodLevel();
/*     */             }
/* 148 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public EnumCreatureAttribute func_70668_bt() {
/* 156 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */   
/*     */ 
/* 160 */   private ChunkCoordinates coffinPos = new ChunkCoordinates(0, 0, 0);
/*     */   
/*     */   public void setStalkingArea(int p_110171_1_, int p_110171_2_, int p_110171_3_) {
/* 163 */     this.coffinPos.func_71571_b(p_110171_1_, p_110171_2_, p_110171_3_);
/*     */   }
/*     */   
/*     */   protected String func_145776_H()
/*     */   {
/* 168 */     return "game.hostile.swim";
/*     */   }
/*     */   
/*     */   protected String func_145777_O()
/*     */   {
/* 173 */     return "game.hostile.swim.splash";
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 178 */     super.func_110147_ax();
/* 179 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 180 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/* 181 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/* 182 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/* 183 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 188 */     super.func_70088_a();
/* 189 */     this.field_70180_af.func_75682_a(13, new Byte((byte)0));
/* 190 */     this.field_70180_af.func_75682_a(14, new Integer(500));
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 200 */     return "mob.villager.idle";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 205 */     return "mob.villager.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 210 */     return "mob.villager.death";
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 215 */     return 0.6F;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 220 */     func_82168_bl();
/* 221 */     float f = func_70013_c(1.0F);
/*     */     
/* 223 */     if (f > 0.5F) {
/* 224 */       this.field_70708_bq += 2;
/*     */     }
/*     */     
/* 227 */     super.func_70636_d();
/*     */   }
/*     */   
/* 230 */   float damageDone = 0.0F;
/*     */   
/*     */   public boolean func_70652_k(Entity entity)
/*     */   {
/* 234 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 235 */     int i = 0;
/*     */     
/* 237 */     if ((entity instanceof EntityLivingBase)) {
/* 238 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)entity);
/* 239 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)entity);
/*     */     }
/*     */     boolean flag;
/*     */     boolean flag;
/* 243 */     if ((entity instanceof EntityVillager)) {
/* 244 */       ExtendedVillager villagerEx = ExtendedVillager.get((EntityVillager)entity);
/* 245 */       if ((villagerEx != null) && (this.field_70170_p.field_73012_v.nextInt(10) == 0)) {
/* 246 */         this.damageDone += 4.0F;
/* 247 */         int taken = villagerEx.takeBlood(30, this);
/* 248 */         if (taken > 0) {
/* 249 */           func_70691_i(4.0F);
/* 250 */           ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, this.field_70170_p, entity.field_70165_t, entity.field_70163_u + entity.field_70131_O * 0.8D, entity.field_70161_v, 0.5D, 0.2D, 16);
/*     */         }
/*     */       }
/*     */       
/* 254 */       flag = true;
/*     */     } else {
/* 256 */       boolean needsBlood = this.damageDone < 20.0F;
/* 257 */       flag = entity.func_70097_a(DamageSource.func_76358_a(this), f);
/*     */       
/* 259 */       if (flag) {
/* 260 */         if (i > 0) {
/* 261 */           entity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/*     */           
/* 263 */           this.field_70159_w *= 0.6D;
/* 264 */           this.field_70179_y *= 0.6D;
/*     */         }
/*     */         
/* 267 */         int j = EnchantmentHelper.func_90036_a(this);
/*     */         
/* 269 */         if (j > 0) {
/* 270 */           entity.func_70015_d(j * 4);
/*     */         }
/*     */         
/* 273 */         if ((entity instanceof EntityLivingBase)) {
/* 274 */           EnchantmentHelper.func_151384_a((EntityLivingBase)entity, this);
/*     */         }
/*     */         
/* 277 */         EnchantmentHelper.func_151385_b(this, entity);
/*     */       }
/*     */     }
/*     */     
/* 281 */     return flag;
/*     */   }
/*     */   
/*     */   protected void func_70785_a(Entity p_70785_1_, float p_70785_2_)
/*     */   {
/* 286 */     if ((this.field_70724_aR <= 0) && (p_70785_2_ < 2.0F) && (p_70785_1_.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (p_70785_1_.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e))
/*     */     {
/* 288 */       this.field_70724_aR = 20;
/* 289 */       func_70652_k(p_70785_1_);
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
/* 300 */     super.func_70098_U();
/*     */     
/* 302 */     if ((this.field_70154_o instanceof EntityCreature)) {
/* 303 */       EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
/* 304 */       this.field_70761_aq = entitycreature.field_70761_aq;
/*     */     }
/*     */   }
/*     */   
/*     */   protected String func_146067_o(int p_146067_1_)
/*     */   {
/* 310 */     return p_146067_1_ > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 315 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 320 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource source)
/*     */   {
/* 325 */     if (!CreatureUtil.checkForVampireDeath(this, source)) {
/* 326 */       return;
/*     */     }
/* 328 */     super.func_70645_a(source);
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 333 */     return net.minecraft.init.Items.field_151097_aZ;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 338 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70600_l(int p_70600_1_) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/* 349 */     func_70062_b(1, new ItemStack(Witchery.Items.VAMPIRE_BOOTS));
/* 350 */     boolean male = this.field_70170_p.field_73012_v.nextBoolean();
/* 351 */     if (male) {
/* 352 */       func_70062_b(2, new ItemStack(this.field_70170_p.field_73012_v.nextInt(3) == 0 ? Witchery.Items.VAMPIRE_LEGS_KILT : Witchery.Items.VAMPIRE_LEGS));
/*     */       
/*     */ 
/* 355 */       func_70062_b(3, new ItemStack(this.field_70170_p.field_73012_v.nextInt(3) == 0 ? Witchery.Items.VAMPIRE_COAT_CHAIN : Witchery.Items.VAMPIRE_COAT));
/*     */     }
/*     */     else
/*     */     {
/* 359 */       func_70062_b(2, new ItemStack(this.field_70170_p.field_73012_v.nextInt(4) != 0 ? Witchery.Items.VAMPIRE_LEGS_KILT : Witchery.Items.VAMPIRE_LEGS));
/*     */       
/*     */ 
/* 362 */       func_70062_b(3, new ItemStack(this.field_70170_p.field_73012_v.nextInt(3) == 0 ? Witchery.Items.VAMPIRE_COAT_FEMALE_CHAIN : Witchery.Items.VAMPIRE_COAT_FEMALE));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 370 */     if (func_94056_bM()) {
/* 371 */       return func_94057_bL();
/*     */     }
/* 373 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.vampire.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
/*     */   {
/* 379 */     p_110161_1_ = super.func_110161_a(p_110161_1_);
/*     */     
/* 381 */     func_82164_bB();
/* 382 */     this.coffinPos.func_71571_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
/*     */     
/* 384 */     return p_110161_1_;
/*     */   }
/*     */   
/*     */   public int getGuardType() {
/* 388 */     return this.field_70180_af.func_75683_a(13);
/*     */   }
/*     */   
/*     */   public void setGuardType(int p_82201_1_) {
/* 392 */     this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)p_82201_1_));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 398 */     super.func_70037_a(nbtRoot);
/*     */     
/* 400 */     if (nbtRoot.func_150297_b("GuardType", 99)) {
/* 401 */       byte b0 = nbtRoot.func_74771_c("GuardType");
/* 402 */       setGuardType(b0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 407 */     this.coffinPos.func_71571_b(nbtRoot.func_74762_e("BaseX"), nbtRoot.func_74762_e("BaseY"), nbtRoot.func_74762_e("BaseZ"));
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 412 */     super.func_70014_b(nbtRoot);
/* 413 */     nbtRoot.func_74774_a("GuardType", (byte)getGuardType());
/* 414 */     ChunkCoordinates home = func_110172_bL();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 419 */     nbtRoot.func_74768_a("BaseX", this.coffinPos.field_71574_a);
/* 420 */     nbtRoot.func_74768_a("BaseY", this.coffinPos.field_71572_b);
/* 421 */     nbtRoot.func_74768_a("BaseZ", this.coffinPos.field_71573_c);
/*     */   }
/*     */   
/*     */   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_)
/*     */   {
/* 426 */     super.func_70062_b(p_70062_1_, p_70062_2_);
/*     */   }
/*     */   
/*     */   public double func_70033_W()
/*     */   {
/* 431 */     return super.func_70033_W() - 0.5D;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityVampire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */