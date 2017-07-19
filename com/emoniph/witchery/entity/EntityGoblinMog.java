/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityGoblinMog extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IHandleDT
/*     */ {
/*     */   private int attackTimer;
/*     */   
/*     */   public EntityGoblinMog(World world)
/*     */   {
/*  47 */     super(world);
/*  48 */     func_70105_a(0.8F, 1.8F);
/*  49 */     this.field_70178_ae = true;
/*  50 */     func_70661_as().func_75491_a(true);
/*  51 */     func_70661_as().func_75495_e(true);
/*  52 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*     */     
/*     */ 
/*  55 */     this.field_70714_bg.func_75776_a(4, new EntityAIArrowAttack(this, 1.0D, 40, 80, 30.0F));
/*  56 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  57 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  58 */     this.field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  59 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  60 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  61 */     this.field_70728_aV = 35;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  66 */     super.func_70088_a();
/*  67 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*  68 */     this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
/*  69 */     this.field_70180_af.func_75682_a(20, new Integer(0));
/*     */   }
/*     */   
/*     */   public int func_70658_aO() {
/*  73 */     return 5;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  81 */     if (func_94056_bM()) {
/*  82 */       return func_94057_bL();
/*     */     }
/*  84 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.goblinmog.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70604_c(EntityLivingBase entity)
/*     */   {
/*  90 */     if ((!(entity instanceof EntityGoblinMog)) && (!(entity instanceof EntityGoblin)) && (!(entity instanceof EntityGoblinGulg))) {
/*  91 */       super.func_70604_c(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/*  97 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 102 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */   public int func_82212_n() {
/* 106 */     return this.field_70180_af.func_75679_c(20);
/*     */   }
/*     */   
/*     */   public void func_82215_s(int par1) {
/* 110 */     this.field_70180_af.func_75692_b(20, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public void func_82206_m() {
/* 114 */     func_82215_s(150);
/* 115 */     func_70606_j(func_110138_aP() / 4.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70619_bc()
/*     */   {
/* 122 */     if (func_82212_n() > 0) {
/* 123 */       int i = func_82212_n() - 1;
/*     */       
/* 125 */       if (i <= 0) {
/* 126 */         this.field_70170_p.func_82739_e(1013, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */       }
/*     */       
/* 129 */       func_82215_s(i);
/*     */       
/* 131 */       if (this.field_70173_aa % 10 == 0) {
/* 132 */         func_70691_i(20.0F);
/*     */       }
/*     */     } else {
/* 135 */       super.func_70619_bc();
/*     */       
/* 137 */       if (this.field_70173_aa % 20 == 0) {
/* 138 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/* 141 */       if ((!this.field_70170_p.field_72995_K) && (func_70661_as().func_75500_f()) && (func_70638_az() != null) && (this.field_70173_aa - this.ticksSinceTeleport > 300L))
/*     */       {
/* 143 */         this.ticksSinceTeleport = this.field_70173_aa;
/* 144 */         teleportToEntity(func_70638_az());
/*     */       }
/*     */       
/* 147 */       if ((func_70694_bm() == null) && (this.field_70146_Z.nextInt(100) == 0)) {
/* 148 */         func_70062_b(0, new ItemStack(Items.field_151031_f));
/* 149 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_ORB, this, 0.5D, 0.5D, 16);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 154 */   long ticksSinceTeleport = 0L;
/*     */   private static final double INVULNRABLE = 9.0D;
/*     */   
/* 157 */   protected boolean teleportToEntity(Entity par1Entity) { Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t - par1Entity.field_70165_t, this.field_70121_D.field_72338_b + this.field_70131_O / 2.0F - par1Entity.field_70163_u + par1Entity.func_70047_e(), this.field_70161_v - par1Entity.field_70161_v);
/*     */     
/*     */ 
/* 160 */     vec3 = vec3.func_72432_b();
/* 161 */     double d0 = 16.0D;
/* 162 */     double d1 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72450_a * d0;
/* 163 */     double d2 = this.field_70163_u + (this.field_70146_Z.nextInt(16) - 8) - vec3.field_72448_b * d0;
/* 164 */     double d3 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72449_c * d0;
/* 165 */     return teleportTo(d1, d2, d3);
/*     */   }
/*     */   
/*     */   protected boolean teleportTo(double par1, double par3, double par5) {
/* 169 */     double d3 = this.field_70165_t;
/* 170 */     double d4 = this.field_70163_u;
/* 171 */     double d5 = this.field_70161_v;
/* 172 */     this.field_70165_t = par1;
/* 173 */     this.field_70163_u = par3;
/* 174 */     this.field_70161_v = par5;
/* 175 */     boolean flag = false;
/* 176 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 177 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 178 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 180 */     if (this.field_70170_p.func_72899_e(i, j, k)) {
/* 181 */       boolean flag1 = false;
/*     */       
/* 183 */       while ((!flag1) && (j > 0)) {
/* 184 */         Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
/*     */         
/* 186 */         if (block.func_149688_o().func_76230_c()) {
/* 187 */           flag1 = true;
/*     */         } else {
/* 189 */           this.field_70163_u -= 1.0D;
/* 190 */           j--;
/*     */         }
/*     */       }
/*     */       
/* 194 */       if (flag1) {
/* 195 */         func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */         
/* 197 */         if ((this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */         {
/* 199 */           flag = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 204 */     if (!flag) {
/* 205 */       func_70107_b(d3, d4, d5);
/* 206 */       return false;
/*     */     }
/* 208 */     short short1 = 128;
/*     */     
/* 210 */     for (int l = 0; l < short1; l++) {
/* 211 */       double d6 = l / (short1 - 1.0D);
/* 212 */       float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 213 */       float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 214 */       float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 215 */       double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 216 */       double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * this.field_70131_O;
/* 217 */       double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 218 */       this.field_70170_p.func_72869_a("portal", d7, d8, d9, f, f1, f2);
/*     */     }
/*     */     
/* 221 */     this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 222 */     func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
/* 223 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 228 */     super.func_110147_ax();
/* 229 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
/* 230 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
/* 231 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 237 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 243 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 248 */     super.func_70636_d();
/*     */     
/* 250 */     if (this.attackTimer > 0) {
/* 251 */       this.attackTimer -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static final double PERCENT_20 = 36.0D;
/*     */   
/*     */   private static final double PERCENT_50 = 81.0D;
/*     */   private static final double PERCENT_80 = 256.0D;
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 262 */     double distance = getDistanceSqToPartner();
/* 263 */     double scale = 1.0D;
/* 264 */     if (distance <= 9.0D)
/* 265 */       return false;
/* 266 */     if (distance <= 36.0D) {
/* 267 */       scale = 0.2D;
/* 268 */     } else if (distance <= 81.0D) {
/* 269 */       scale = 0.5D;
/* 270 */     } else if (distance <= 256.0D) {
/* 271 */       scale = 0.8D;
/*     */     }
/* 273 */     return super.func_70097_a(source, (float)Math.min(damage * scale, 15.0D));
/*     */   }
/*     */   
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 278 */     return 15.0F;
/*     */   }
/*     */   
/*     */   private double getDistanceSqToPartner() {
/* 282 */     double R = 16.0D;
/* 283 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.field_70165_t - 16.0D, this.field_70163_u - 16.0D, this.field_70161_v - 16.0D, this.field_70165_t + 16.0D, this.field_70163_u + 16.0D, this.field_70161_v + 16.0D);
/* 284 */     List mogs = this.field_70170_p.func_72872_a(EntityGoblinGulg.class, bb);
/* 285 */     double minDistance = Double.MAX_VALUE;
/* 286 */     for (Object obj : mogs) {
/* 287 */       EntityGoblinGulg mog = (EntityGoblinGulg)obj;
/* 288 */       double distance = func_70068_e(mog);
/* 289 */       if (distance < minDistance) {
/* 290 */         minDistance = distance;
/*     */       }
/*     */     }
/* 293 */     return minDistance;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 298 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/*     */ 
/* 301 */     par1NBTTagCompound.func_74768_a("Invul", func_82212_n());
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 307 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/* 309 */     func_82215_s(par1NBTTagCompound.func_74762_e("Invul"));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 315 */     this.attackTimer = 10;
/* 316 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 317 */     boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), 7 + this.field_70146_Z.nextInt(15));
/*     */     
/* 319 */     if (flag) {
/* 320 */       par1Entity.field_70181_x += 0.4000000059604645D;
/*     */     }
/*     */     
/* 323 */     func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/* 324 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 330 */     if (par1 == 4) {
/* 331 */       this.attackTimer = 10;
/* 332 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     } else {
/* 334 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 340 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 345 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 350 */     return "witchery:mob.goblin.mog_idle";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 355 */     return "mob.horse.zombie.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 360 */     return "mob.wither.death";
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 365 */     func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 370 */     func_70099_a(Witchery.Items.GENERIC.itemKobolditeNugget.createStack(this.field_70146_Z.nextInt(3) + 1), 0.0F);
/* 371 */     ItemStack armor = null;
/* 372 */     switch (this.field_70146_Z.nextInt(4)) {
/*     */     case 0: 
/* 374 */       armor = new ItemStack(Items.field_151029_X);
/* 375 */       break;
/*     */     case 1: 
/* 377 */       armor = new ItemStack(Items.field_151022_W);
/* 378 */       break;
/*     */     case 2: 
/* 380 */       armor = new ItemStack(Items.field_151023_V);
/* 381 */       break;
/*     */     case 3: 
/* 383 */       armor = new ItemStack(Items.field_151020_U);
/*     */     }
/*     */     
/* 386 */     if (armor != null) {
/* 387 */       EnchantmentHelper.func_77504_a(this.field_70170_p.field_73012_v, armor, 30);
/* 388 */       func_70099_a(armor, 0.0F);
/*     */     }
/* 390 */     if (this.field_70170_p.field_73012_v.nextInt(2) == 0) {
/* 391 */       func_70099_a(new ItemStack(Witchery.Items.MOGS_QUIVER), 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 397 */     return null;
/*     */   }
/*     */   
/*     */   public EntityItem func_70099_a(ItemStack stack, float distance)
/*     */   {
/* 402 */     if ((stack != null) && (stack.func_77973_b() == Items.field_151031_f)) {
/* 403 */       EntityItem item = super.func_70099_a(stack, distance);
/* 404 */       item.field_145804_b = 100;
/* 405 */       item.lifespan = 100;
/* 406 */       return item;
/*     */     }
/* 408 */     return super.func_70099_a(stack, distance);
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 414 */     func_110163_bv();
/* 415 */     func_70062_b(0, new ItemStack(Items.field_151031_f));
/* 416 */     return super.func_110161_a(par1EntityLivingData);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 421 */     return false;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase targetEntity, float par2)
/*     */   {
/* 426 */     if (func_70694_bm() != null) {
/* 427 */       EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, targetEntity, 1.6F, 14 - this.field_70170_p.field_73013_u.func_151525_a() * 4);
/*     */       
/* 429 */       double factor = targetEntity.field_70160_al ? 2.5D : 1.5D;
/* 430 */       entityarrow.field_70159_w *= factor;
/* 431 */       entityarrow.field_70181_x *= factor;
/* 432 */       entityarrow.field_70179_y *= factor;
/* 433 */       entityarrow.func_70239_b(par2 * 8.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.field_73013_u.func_151525_a() * 0.11F);
/*     */       
/* 435 */       entityarrow.func_70240_a(0);
/*     */       
/* 437 */       func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 438 */       this.field_70170_p.func_72838_d(entityarrow);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityGoblinMog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */