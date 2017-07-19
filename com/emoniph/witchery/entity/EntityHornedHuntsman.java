/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityHornedHuntsman extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IHandleDT
/*     */ {
/*     */   private int attackTimer;
/*     */   private boolean explosiveEntrance;
/*     */   
/*     */   public EntityHornedHuntsman(World par1World)
/*     */   {
/*  49 */     super(par1World);
/*     */     
/*  51 */     func_70105_a(1.4F, 3.2F);
/*  52 */     this.field_70178_ae = true;
/*  53 */     func_70661_as().func_75491_a(true);
/*  54 */     func_70661_as().func_75495_e(true);
/*  55 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  56 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, true));
/*  57 */     this.field_70714_bg.func_75776_a(3, new net.minecraft.entity.ai.EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
/*  58 */     this.field_70714_bg.func_75776_a(4, new EntityAIArrowAttack(this, 1.0D, 20, 60, 30.0F));
/*  59 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  60 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  61 */     this.field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  62 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  63 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  64 */     this.field_70728_aV = 70;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  69 */     super.func_70088_a();
/*  70 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*  71 */     this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
/*  72 */     this.field_70180_af.func_75682_a(20, new Integer(0));
/*     */   }
/*     */   
/*     */   public void causeExplosiveEntrance() {
/*  76 */     this.explosiveEntrance = true;
/*     */   }
/*     */   
/*     */   public int func_70658_aO() {
/*  80 */     return 4;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  88 */     if (func_94056_bM()) {
/*  89 */       return func_94057_bL();
/*     */     }
/*  91 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.hornedHuntsman.name");
/*     */   }
/*     */   
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
/* 126 */         if (this.explosiveEntrance) {
/* 127 */           this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u + func_70047_e(), this.field_70161_v, 6.0F, false, this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"));
/*     */         }
/*     */         
/* 130 */         this.field_70170_p.func_82739_e(1013, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */       }
/*     */       
/* 133 */       func_82215_s(i);
/*     */       
/* 135 */       if (this.field_70173_aa % 10 == 0) {
/* 136 */         func_70691_i(20.0F);
/*     */       }
/*     */     } else {
/* 139 */       super.func_70619_bc();
/*     */       
/* 141 */       if (this.field_70173_aa % 20 == 0) {
/* 142 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/* 145 */       if ((this.field_70173_aa % 20 == 0) && (this.field_70170_p.field_73012_v.nextInt(5) == 0) && (func_70638_az() != null) && 
/* 146 */         (!this.field_70170_p.field_72995_K) && (func_70635_at().func_75522_a(func_70638_az()))) {
/* 147 */         double d0 = func_70092_e(func_70638_az().field_70165_t, func_70638_az().field_70121_D.field_72338_b, func_70638_az().field_70161_v);
/* 148 */         func_70671_ap().func_75651_a(func_70638_az(), 30.0F, 30.0F);
/* 149 */         float range = 30.0F;
/* 150 */         float f = MathHelper.func_76133_a(d0) / range;
/* 151 */         float f1 = f;
/*     */         
/* 153 */         if (f < 0.1F)
/*     */         {
/* 155 */           f1 = 0.1F;
/*     */         }
/*     */         
/* 158 */         if (f1 > 1.0F)
/*     */         {
/* 160 */           f1 = 1.0F;
/*     */         }
/* 162 */         func_82196_d(func_70638_az(), f);
/*     */       }
/*     */       
/*     */ 
/* 166 */       if ((this.field_70173_aa % (200 + this.field_70170_p.field_73012_v.nextInt(4) * 100) == 0) && (func_70638_az() != null) && (func_70068_e(func_70638_az()) <= 256.0D) && 
/* 167 */         (!this.field_70170_p.field_72995_K) && (func_70635_at().func_75522_a(func_70638_az()))) {
/* 168 */         EntityWolf wolf = new EntityWolf(this.field_70170_p);
/* 169 */         wolf.func_70012_b(this.field_70165_t - 0.5D + this.field_70170_p.field_73012_v.nextDouble(), this.field_70163_u, this.field_70161_v - 0.5D + this.field_70170_p.field_73012_v.nextDouble(), this.field_70759_as, this.field_70125_A);
/* 170 */         wolf.func_70916_h(true);
/* 171 */         wolf.func_70624_b(func_70638_az());
/* 172 */         wolf.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 20000, 1));
/* 173 */         ParticleEffect.INSTANT_SPELL.send(com.emoniph.witchery.util.SoundEffect.RANDOM_FIZZ, wolf, 2.0D, 2.0D, 10);
/* 174 */         this.field_70170_p.func_72838_d(wolf);
/*     */       }
/*     */       
/*     */ 
/* 178 */       if ((!this.field_70170_p.field_72995_K) && (func_70661_as().func_75500_f()) && (func_70638_az() != null) && (this.field_70173_aa - this.ticksSinceTeleport > 200L)) {
/* 179 */         this.ticksSinceTeleport = this.field_70173_aa;
/* 180 */         teleportToEntity(func_70638_az());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 185 */   long ticksSinceTeleport = 0L;
/*     */   
/*     */   protected boolean teleportToEntity(Entity par1Entity) {
/* 188 */     Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t - par1Entity.field_70165_t, this.field_70121_D.field_72338_b + this.field_70131_O / 2.0F - par1Entity.field_70163_u + par1Entity.func_70047_e(), this.field_70161_v - par1Entity.field_70161_v);
/*     */     
/* 190 */     vec3 = vec3.func_72432_b();
/* 191 */     double d0 = 8.0D;
/* 192 */     double d1 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72450_a * d0;
/* 193 */     double d2 = this.field_70163_u + (this.field_70146_Z.nextInt(16) - 8) - vec3.field_72448_b * d0;
/* 194 */     double d3 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72449_c * d0;
/* 195 */     return teleportTo(d1, d2, d3);
/*     */   }
/*     */   
/*     */   protected boolean teleportTo(double par1, double par3, double par5) {
/* 199 */     double d3 = this.field_70165_t;
/* 200 */     double d4 = this.field_70163_u;
/* 201 */     double d5 = this.field_70161_v;
/* 202 */     this.field_70165_t = par1;
/* 203 */     this.field_70163_u = par3;
/* 204 */     this.field_70161_v = par5;
/* 205 */     boolean flag = false;
/* 206 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 207 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 208 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 210 */     if (this.field_70170_p.func_72899_e(i, j, k)) {
/* 211 */       boolean flag1 = false;
/*     */       
/* 213 */       while ((!flag1) && (j > 0)) {
/* 214 */         Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
/*     */         
/* 216 */         if (block.func_149688_o().func_76230_c()) {
/* 217 */           flag1 = true;
/*     */         } else {
/* 219 */           this.field_70163_u -= 1.0D;
/* 220 */           j--;
/*     */         }
/*     */       }
/*     */       
/* 224 */       if (flag1) {
/* 225 */         func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */         
/* 227 */         if ((this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */         {
/* 229 */           flag = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 234 */     if (!flag) {
/* 235 */       func_70107_b(d3, d4, d5);
/* 236 */       return false;
/*     */     }
/* 238 */     short short1 = 128;
/*     */     
/* 240 */     for (int l = 0; l < short1; l++) {
/* 241 */       double d6 = l / (short1 - 1.0D);
/* 242 */       float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 243 */       float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 244 */       float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
/* 245 */       double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 246 */       double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * this.field_70131_O;
/* 247 */       double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N * 2.0D;
/* 248 */       this.field_70170_p.func_72869_a("portal", d7, d8, d9, f, f1, f2);
/*     */     }
/*     */     
/* 251 */     this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 252 */     func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 258 */     super.func_110147_ax();
/* 259 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
/* 260 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
/* 261 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
/* 262 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 267 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 276 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 281 */     super.func_70636_d();
/*     */     
/* 283 */     if (this.attackTimer > 0) {
/* 284 */       this.attackTimer -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 290 */     return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0F));
/*     */   }
/*     */   
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 295 */     return 15.0F;
/*     */   }
/*     */   
/*     */   public boolean func_70686_a(Class par1Class)
/*     */   {
/* 300 */     return super.func_70686_a(par1Class);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 305 */     super.func_70014_b(par1NBTTagCompound);
/* 306 */     par1NBTTagCompound.func_74757_a("PlayerCreated", isPlayerCreated());
/* 307 */     par1NBTTagCompound.func_74768_a("Invul", func_82212_n());
/* 308 */     par1NBTTagCompound.func_74757_a("explosiveEntrance", this.explosiveEntrance);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 314 */     super.func_70037_a(par1NBTTagCompound);
/* 315 */     setPlayerCreated(par1NBTTagCompound.func_74767_n("PlayerCreated"));
/* 316 */     func_82215_s(par1NBTTagCompound.func_74762_e("Invul"));
/* 317 */     if (par1NBTTagCompound.func_74764_b("explosiveEntrance")) {
/* 318 */       this.explosiveEntrance = par1NBTTagCompound.func_74767_n("explosiveEntrance");
/*     */     } else {
/* 320 */       this.explosiveEntrance = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 328 */     this.attackTimer = 10;
/* 329 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 330 */     boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), 7 + this.field_70146_Z.nextInt(15));
/*     */     
/* 332 */     if (flag) {
/* 333 */       par1Entity.field_70181_x += 0.4000000059604645D;
/*     */     }
/*     */     
/* 336 */     func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/* 337 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 343 */     if (par1 == 4) {
/* 344 */       this.attackTimer = 10;
/* 345 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     } else {
/* 347 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 353 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 358 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 363 */     return "mob.enderdragon.growl";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 368 */     return "mob.horse.zombie.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 373 */     return "mob.wither.death";
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 378 */     func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 383 */     func_70099_a(new ItemStack(Items.field_151144_bL, this.field_70170_p.field_73012_v.nextInt(3) == 0 ? 3 : 2, 1), 0.0F);
/* 384 */     Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
/* 385 */     int k = MathHelper.func_76136_a(this.field_70146_Z, Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), enchantment.func_77325_b());
/* 386 */     ItemStack itemstack = Items.field_151134_bR.func_92111_a(new net.minecraft.enchantment.EnchantmentData(enchantment, k));
/* 387 */     func_70099_a(itemstack, 0.0F);
/* 388 */     func_70099_a(Witchery.Items.GENERIC.itemInfernalBlood.createStack(), 0.0F);
/* 389 */     if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 390 */       func_70099_a(new ItemStack(Witchery.Items.HUNTSMANS_SPEAR), 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 396 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 400 */     return (this.field_70180_af.func_75683_a(16) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setPlayerCreated(boolean par1) {
/* 404 */     func_110163_bv();
/* 405 */     byte b0 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 407 */     if (par1) {
/* 408 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 410 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 416 */     return false;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase targetEntity, float par2)
/*     */   {
/* 421 */     EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, targetEntity, 1.6F, 14 - this.field_70170_p.field_73013_u.func_151525_a() * 4);
/* 422 */     entityarrow.func_70239_b(par2 * 8.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.field_73013_u.func_151525_a() * 0.11F);
/* 423 */     entityarrow.func_70240_a(2);
/*     */     
/* 425 */     func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 426 */     this.field_70170_p.func_72838_d(entityarrow);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityHornedHuntsman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */