/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.dimension.WorldProviderTorment;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.DemonicDamageSource;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityLordOfTorment extends EntityFlyingMob implements IRangedAttackMob, IBossDisplayData, IHandleDT
/*     */ {
/*     */   private int attackTimer;
/*     */   
/*     */   public EntityLordOfTorment(World world)
/*     */   {
/*  56 */     super(world);
/*  57 */     func_70105_a(0.6F, 1.9F);
/*  58 */     this.field_70178_ae = true;
/*  59 */     this.field_70728_aV = 50;
/*  60 */     func_70661_as().func_75495_e(true);
/*  61 */     func_70661_as().func_75491_a(true);
/*  62 */     this.field_70728_aV = 80;
/*     */     
/*  64 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  65 */     this.field_70714_bg.func_75776_a(2, new com.emoniph.witchery.entity.ai.EntityAIFlyerArrowAttack(this, 1.0D, 20, 60, 12.0F));
/*     */     
/*     */ 
/*  68 */     this.field_70714_bg.func_75776_a(5, new EntityAIFlyerLand(this, 0.8D, false));
/*  69 */     this.field_70714_bg.func_75776_a(6, new com.emoniph.witchery.entity.ai.EntityAIFlyerWander(this, 0.2D, 10.0D));
/*  70 */     this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  71 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  72 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  73 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  78 */     super.func_110147_ax();
/*  79 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
/*  80 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2D);
/*  81 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(8.0D);
/*  82 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  88 */     super.func_70088_a();
/*  89 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  94 */     super.func_70071_h_();
/*  95 */     if (this.field_70170_p.field_72995_K) {
/*  96 */       this.field_70170_p.func_72869_a(ParticleEffect.FLAME.toString(), this.field_70165_t - this.field_70130_N * 0.5D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N, 0.1D + this.field_70163_u + this.field_70170_p.field_73012_v.nextDouble() * 2.0D, this.field_70161_v - this.field_70130_N * 0.5D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_110182_bF()
/*     */   {
/* 103 */     return this.field_70180_af.func_75683_a(16) != 0;
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int air)
/*     */   {
/* 113 */     return air;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 123 */     super.func_70636_d();
/*     */     
/* 125 */     if (this.attackTimer > 0) {
/* 126 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/* 129 */     if ((this.field_70170_p.field_73012_v.nextDouble() < 0.05D) && (func_70638_az() != null) && ((func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)func_70638_az()).field_71075_bZ.field_75100_b) && (!func_70638_az().func_70644_a(Potion.field_76421_d)))
/*     */     {
/*     */ 
/*     */ 
/* 133 */       func_70638_az().func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 5));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte state)
/*     */   {
/* 140 */     if (state == 4) {
/* 141 */       this.attackTimer = 10;
/* 142 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     } else {
/* 144 */       super.func_70103_a(state);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity target)
/*     */   {
/* 150 */     this.attackTimer = 10;
/* 151 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 152 */     boolean flag = target.func_70097_a(DamageSource.func_76358_a(this), 7 + this.field_70146_Z.nextInt(15));
/*     */     
/* 154 */     if (flag) {
/* 155 */       target.field_70181_x += 0.4000000059604645D;
/*     */     }
/*     */     
/* 158 */     func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/* 159 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 164 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 169 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 174 */     if (func_94056_bM()) {
/* 175 */       return func_94057_bL();
/*     */     }
/* 177 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.lordoftorment.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70020_e(NBTTagCompound nbtRoot)
/*     */   {
/* 183 */     super.func_70020_e(nbtRoot);
/* 184 */     if (nbtRoot.func_74764_b("WITCAttackers")) {
/* 185 */       NBTTagList nbtAttackers = nbtRoot.func_150295_c("WITCAttackers", 8);
/* 186 */       for (int i = 0; i < nbtAttackers.func_74745_c(); i++) {
/* 187 */         String attacker = nbtAttackers.func_150307_f(i);
/* 188 */         if (!this.attackers.contains(attacker)) {
/* 189 */           this.attackers.add(attacker);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70109_d(NBTTagCompound nbtRoot)
/*     */   {
/* 197 */     super.func_70109_d(nbtRoot);
/*     */     
/* 199 */     NBTTagList nbtAttackers = new NBTTagList();
/* 200 */     int i = 0;
/* 201 */     for (String attacker : this.attackers) {
/* 202 */       nbtAttackers.func_74742_a(new NBTTagString(attacker));
/*     */     }
/* 204 */     nbtRoot.func_74782_a("WITCAttackers", nbtAttackers);
/*     */   }
/*     */   
/* 207 */   private final HashSet<String> attackers = new HashSet();
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 211 */     if (source.func_94541_c()) {
/* 212 */       return false;
/*     */     }
/*     */     
/* 215 */     if ((source.func_76364_f() != null) && ((source.func_76364_f() instanceof EntityPlayer))) {
/* 216 */       EntityPlayer attacker = (EntityPlayer)source.func_76364_f();
/* 217 */       if (!this.attackers.contains(attacker.func_70005_c_())) {
/* 218 */         this.attackers.add(attacker.func_70005_c_());
/*     */       }
/*     */     }
/*     */     
/* 222 */     float damageCap = (source instanceof DemonicDamageSource) ? 8.0F : 5.0F;
/* 223 */     boolean damaged = super.func_70097_a(source, Math.min(damage, damageCap));
/*     */     
/* 225 */     if ((!this.field_70170_p.field_72995_K) && (this.field_71093_bK != Config.instance().dimensionTormentID) && 
/* 226 */       (func_110143_aJ() <= func_110138_aP() * 0.5F)) {
/* 227 */       int tormentlevel = WorldProviderTorment.getRandomTormentLevel(this.field_70170_p);
/*     */       
/* 229 */       double R = 16.0D;
/* 230 */       double Ry = 32.0D;
/* 231 */       AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.field_70165_t - 16.0D, this.field_70163_u - 32.0D, this.field_70161_v - 16.0D, this.field_70165_t + 16.0D, this.field_70163_u + 32.0D, this.field_70161_v + 16.0D);
/* 232 */       List players = this.field_70170_p.func_72872_a(EntityPlayer.class, bb);
/* 233 */       for (Object obj : players) {
/* 234 */         EntityPlayer otherPlayer = (EntityPlayer)obj;
/* 235 */         WorldProviderTorment.setPlayerMustTorment(otherPlayer, 2, tormentlevel);
/*     */       }
/*     */       
/* 238 */       for (String playerName : this.attackers) {
/* 239 */         EntityPlayer otherPlayer = this.field_70170_p.func_72924_a(playerName);
/* 240 */         if ((otherPlayer != null) && (otherPlayer.field_71093_bK == this.field_71093_bK)) {
/* 241 */           WorldProviderTorment.setPlayerMustTorment(otherPlayer, 2, tormentlevel);
/*     */         }
/*     */       }
/*     */       
/* 245 */       ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/* 246 */       func_70106_y();
/*     */     }
/*     */     
/*     */ 
/* 250 */     return damaged;
/*     */   }
/*     */   
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 255 */     return 5.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 260 */     return "witchery:mob.torment.laugh";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 265 */     return "witchery:mob.torment.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 270 */     return "witchery:mob.torment.death";
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 275 */     return TimeUtil.secsToTicks(10);
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 280 */     return null;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 285 */     Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
/* 286 */     int k = MathHelper.func_76136_a(this.field_70146_Z, Math.min(enchantment.func_77319_d() + 3, enchantment.func_77325_b()), enchantment.func_77325_b());
/*     */     
/* 288 */     ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
/* 289 */     func_70099_a(itemstack, 0.0F);
/*     */     
/* 291 */     Enchantment enchantment2 = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
/* 292 */     int k2 = MathHelper.func_76136_a(this.field_70146_Z, Math.min(enchantment2.func_77319_d() + 1, enchantment.func_77325_b()), enchantment.func_77325_b());
/*     */     
/* 294 */     ItemStack itemstack2 = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k2));
/* 295 */     func_70099_a(itemstack, 0.0F);
/*     */     
/* 297 */     func_70099_a(Witchery.Items.GENERIC.itemDemonHeart.createStack(), 0.0F);
/* 298 */     func_70099_a(Witchery.Items.GENERIC.itemBrewSoulTorment.createStack(), 0.0F);
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/* 303 */     return 2.0F;
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi()
/*     */   {
/* 308 */     return true;
/*     */   }
/*     */   
/*     */   public int func_70641_bl()
/*     */   {
/* 313 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 318 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 324 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_82196_d(EntityLivingBase targetEntity, float par2)
/*     */   {
/* 331 */     this.attackTimer = 10;
/* 332 */     this.field_70170_p.func_72960_a(this, (byte)4);
/*     */     
/* 334 */     double d0 = targetEntity.field_70165_t - this.field_70165_t;
/* 335 */     double d1 = targetEntity.field_70121_D.field_72338_b + targetEntity.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/* 336 */     double d2 = targetEntity.field_70161_v - this.field_70161_v;
/*     */     
/* 338 */     float f1 = MathHelper.func_76129_c(par2) * 0.5F;
/*     */     
/* 340 */     if (!this.field_70170_p.field_72995_K) {
/* 341 */       this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/* 342 */       int count = this.field_70146_Z.nextInt(10) == 0 ? 9 : 3;
/*     */       
/* 344 */       EntitySpellEffect effect = new EntitySpellEffect(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1, EffectRegistry.instance().getEffect(39), 1);
/*     */       
/* 346 */       double d8 = 1.0D;
/* 347 */       effect.field_70165_t = this.field_70165_t;
/* 348 */       effect.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F);
/* 349 */       effect.field_70161_v = this.field_70161_v;
/* 350 */       this.field_70170_p.func_72838_d(effect);
/* 351 */       effect.setShooter(this);
/*     */       
/* 353 */       for (int i = 0; i < count; i++) {
/* 354 */         EntitySoulfire fireballEntity = new EntitySoulfire(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1);
/*     */         
/* 356 */         d8 = 1.0D;
/* 357 */         fireballEntity.field_70165_t = this.field_70165_t;
/* 358 */         fireballEntity.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F + 0.5D);
/* 359 */         fireballEntity.field_70161_v = this.field_70161_v;
/* 360 */         this.field_70170_p.func_72838_d(fireballEntity);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityLordOfTorment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */