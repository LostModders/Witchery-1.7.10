/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.brewing.potions.PotionBase;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.BlockActionSphere;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.RandomCollection;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityLeonard extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IHandleDT
/*     */ {
/*     */   private int attackTimer;
/*     */   private boolean isImmune;
/*     */   private int spawnDelay;
/*     */   
/*     */   public EntityLeonard(World world)
/*     */   {
/*  58 */     super(world);
/*     */     
/*  60 */     func_70105_a(0.6F, 1.8F);
/*  61 */     this.field_70178_ae = true;
/*  62 */     func_70661_as().func_75491_a(true);
/*  63 */     func_70661_as().func_75495_e(true);
/*  64 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  65 */     this.field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 20, 60, 30.0F));
/*  66 */     this.field_70714_bg.func_75776_a(3, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  67 */     this.field_70714_bg.func_75776_a(4, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  68 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  69 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*     */     
/*     */ 
/*  72 */     this.field_70728_aV = 100;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  77 */     super.func_70088_a();
/*  78 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*  79 */     this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
/*  80 */     this.field_70180_af.func_75682_a(20, new Integer(0));
/*  81 */     this.field_70180_af.func_75682_a(21, new Integer(0));
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  86 */     super.func_110147_ax();
/*  87 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(600.0D);
/*  88 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
/*  89 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
/*  90 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/*  95 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 104 */     if (func_94056_bM()) {
/* 105 */       return func_94057_bL();
/*     */     }
/* 107 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.leonard.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 118 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */   public int getInvulnerableStartTicks() {
/* 122 */     return this.field_70180_af.func_75679_c(20);
/*     */   }
/*     */   
/*     */   public void setInvulnerableStartTicks(int par1) {
/* 126 */     this.field_70180_af.func_75692_b(20, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public int getLifetime() {
/* 130 */     return this.field_70180_af.func_75679_c(21);
/*     */   }
/*     */   
/*     */   public void setLifetime(int par1) {
/* 134 */     this.field_70180_af.func_75692_b(21, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public void setInvulnerableStart() {
/* 138 */     setInvulnerableStartTicks(150);
/* 139 */     func_70606_j(func_110138_aP() / 4.0F);
/*     */   }
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/* 144 */     if (getInvulnerableStartTicks() > 0) {
/* 145 */       int i = getInvulnerableStartTicks() - 1;
/*     */       
/* 147 */       if (i <= 0) {
/* 148 */         this.field_70170_p.func_82739_e(1013, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */       }
/*     */       
/* 151 */       setInvulnerableStartTicks(i);
/*     */       
/* 153 */       if (this.field_70173_aa % 10 == 0) {
/* 154 */         func_70691_i(func_110138_aP() * 0.75F / 15.0F);
/*     */       }
/*     */     } else {
/* 157 */       super.func_70619_bc();
/*     */       
/* 159 */       setLifetime(getLifetime() + 1);
/*     */       
/*     */ 
/* 162 */       if (this.field_70173_aa % 20 == 0) {
/* 163 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/*     */ 
/* 167 */       if ((this.field_70173_aa % 20 == 0) && (this.field_70170_p.field_73012_v.nextInt(5) == 0) && ((func_70638_az() != null) || (func_110144_aD() != null)))
/*     */       {
/* 169 */         if (!this.field_70170_p.field_72995_K) {
/* 170 */           int R = 40;
/* 171 */           double RY = 40.0D;
/* 172 */           double RSQ = 1600.0D;
/*     */           
/* 174 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 40.0D, this.field_70163_u - 40.0D, this.field_70161_v - 40.0D, this.field_70165_t + 40.0D, this.field_70163_u + 40.0D, this.field_70161_v + 40.0D);
/*     */           
/* 176 */           List<EntityPlayer> players = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds);
/* 177 */           boolean hexed = false;
/* 178 */           for (EntityPlayer player : players)
/*     */           {
/* 180 */             if ((func_70092_e(player.field_70165_t, this.field_70163_u, player.field_70161_v) <= 1600.0D) && (!player.field_70128_L) && (player.func_110143_aJ() > 0.0F))
/*     */             {
/* 182 */               if (!player.func_70644_a(Witchery.Potions.MORTAL_COIL)) {
/* 183 */                 hexed = true;
/* 184 */                 ParticleEffect.MOB_SPELL.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 2.0D, 40);
/* 185 */                 player.func_70690_d(new PotionEffect(Witchery.Potions.MORTAL_COIL.field_76415_H, TimeUtil.secsToTicks(90)));
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 191 */           if (hexed) {
/* 192 */             ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, this, 1.0D, 1.0D, 40, 39168);
/* 193 */           } else if ((this.field_70170_p.field_73012_v.nextInt(5) == 1) && (players.size() > 0)) {
/* 194 */             EntityPlayer player = (EntityPlayer)players.get(this.field_70170_p.field_73012_v.nextInt(players.size()));
/* 195 */             if ((player != null) && (func_70092_e(player.field_70165_t, this.field_70163_u, player.field_70161_v) <= 1600.0D) && (!player.field_70128_L) && (player.func_110143_aJ() > 0.0F))
/*     */             {
/* 197 */               ParticleEffect.MOB_SPELL.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 2.0D, 40);
/* 198 */               switch (this.field_70170_p.field_73012_v.nextInt(10)) {
/*     */               case 0: 
/*     */               case 1: 
/*     */               case 2: 
/* 202 */                 List<Potion> effectsToRemove = new ArrayList();
/* 203 */                 Collection<PotionEffect> effects = player.func_70651_bq();
/* 204 */                 for (PotionEffect effect : effects) {
/* 205 */                   Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/* 206 */                   if ((!PotionBase.isDebuff(potion)) && (PotionBase.isCurable(potion))) {
/* 207 */                     effectsToRemove.add(potion);
/*     */                   }
/*     */                 }
/* 210 */                 for (Potion potion : effectsToRemove) {
/* 211 */                   player.func_82170_o(potion.field_76415_H);
/*     */                 }
/* 213 */                 break;
/*     */               case 3: 
/*     */               case 4: 
/*     */               case 5: 
/* 217 */                 player.func_70690_d(new PotionEffect(Witchery.Potions.SINKING.field_76415_H, TimeUtil.secsToTicks(60), 3));
/*     */                 
/* 219 */                 ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, this, 1.0D, 1.0D, 40, 10027008);
/* 220 */                 break;
/*     */               case 6: 
/*     */               case 7: 
/*     */               case 8: 
/* 224 */                 player.func_70690_d(new PotionEffect(Witchery.Potions.INSANITY.field_76415_H, TimeUtil.secsToTicks(60), 3));
/*     */                 
/* 226 */                 ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, this, 1.0D, 1.0D, 40, 153);
/* 227 */                 break;
/*     */               case 9: 
/* 229 */                 player.func_70690_d(new PotionEffect(Witchery.Potions.OVERHEATING.field_76415_H, TimeUtil.secsToTicks(60), 3));
/*     */                 
/* 231 */                 ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, this, 1.0D, 1.0D, 40, 39321);
/*     */               }
/*     */               
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 240 */       if (this.field_70173_aa % 20 == 2) {
/* 241 */         if (this.field_70170_p.field_73012_v.nextInt(5) == 0) {
/* 242 */           new BlockActionSphere() {
/*     */             protected void onBlock(World world, int x, int y, int z) {
/* 244 */               Block block = world.func_147439_a(x, y, z);
/* 245 */               if ((block == Witchery.Blocks.BREW_GAS) || (block == Witchery.Blocks.BREW_LIQUID))
/* 246 */                 world.func_147449_b(x, y, z, net.minecraft.init.Blocks.field_150480_ab); } }.drawFilledSphere(this.field_70170_p, MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u) + 2, MathHelper.func_76128_c(this.field_70161_v), 4);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 253 */         if (func_110143_aJ() < func_110138_aP() * 0.5D) {
/* 254 */           if ((func_70638_az() != null) || (func_110144_aD() != null)) {
/* 255 */             if ((func_110143_aJ() < func_110138_aP() * 0.25D) && (this.field_70170_p.field_73012_v.nextInt(3) == 1) && (!func_70644_a(Witchery.Potions.RESIZING)))
/*     */             {
/* 257 */               func_70690_d(new PotionEffect(Witchery.Potions.RESIZING.field_76415_H, TimeUtil.secsToTicks(60), 3));
/*     */             }
/* 259 */             int SPAWN_DELAY = 10;
/* 260 */             int R = 15;
/* 261 */             double RY = 5.0D;
/* 262 */             double RSQ = 225.0D;
/*     */             
/* 264 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 15.0D, this.field_70163_u - 5.0D, this.field_70161_v - 15.0D, this.field_70165_t + 15.0D, this.field_70163_u + 5.0D, this.field_70161_v + 15.0D);
/*     */             
/* 266 */             List<EntityLostSoul> souls = this.field_70170_p.func_72872_a(EntityLostSoul.class, bounds);
/*     */             
/* 268 */             if (souls.size() == 0) {
/* 269 */               this.isImmune = false;
/* 270 */               if (--this.spawnDelay <= 0) {
/* 271 */                 removeCoilEffects(15, 5.0D);
/*     */                 
/* 273 */                 this.spawnDelay = 10;
/* 274 */                 int spawned = 0;
/* 275 */                 for (int i = 0; i < 4 + this.field_70170_p.field_73012_v.nextInt(2); i++) {
/* 276 */                   EntityLostSoul soul = (EntityLostSoul)Infusion.spawnCreature(this.field_70170_p, EntityLostSoul.class, (int)this.field_70165_t, (int)this.field_70163_u + 1, (int)this.field_70161_v, null, 1, 4, ParticleEffect.SMOKE, SoundEffect.RANDOM_POP);
/*     */                   
/*     */ 
/* 279 */                   if (soul != null) {
/* 280 */                     soul.setTimeToLive(TimeUtil.secsToTicks(60 + this.field_70170_p.field_73012_v.nextInt(30)));
/* 281 */                     spawned++;
/*     */                   }
/*     */                 }
/*     */                 
/* 285 */                 for (int i = spawned; i < 3; i++) {
/* 286 */                   EntityLostSoul soul = (EntityLostSoul)Infusion.spawnCreature(this.field_70170_p, EntityLostSoul.class, (int)this.field_70165_t, (int)this.field_70163_u + 1, (int)this.field_70161_v, null, 0, 0, ParticleEffect.SMOKE, SoundEffect.RANDOM_POP);
/*     */                   
/*     */ 
/* 289 */                   if (soul != null) {
/* 290 */                     soul.setTimeToLive(TimeUtil.secsToTicks(60 + this.field_70170_p.field_73012_v.nextInt(30)));
/*     */                   }
/*     */                 }
/*     */               }
/*     */             } else {
/* 295 */               this.isImmune = true;
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 300 */           this.isImmune = false;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource source)
/*     */   {
/* 308 */     super.func_70645_a(source);
/* 309 */     removeCoilEffects(40, 40.0D);
/*     */   }
/*     */   
/*     */   private void removeCoilEffects(int R, double RY) {
/* 313 */     AxisAlignedBB bounds2 = AxisAlignedBB.func_72330_a(this.field_70165_t - R, this.field_70163_u - RY, this.field_70161_v - R, this.field_70165_t + R, this.field_70163_u + RY, this.field_70161_v + R);
/*     */     
/* 315 */     List<EntityPlayer> players = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds2);
/* 316 */     for (EntityPlayer player : players) {
/* 317 */       if ((!player.field_70128_L) && (player.func_110143_aJ() > 0.0F) && (player.func_70644_a(Witchery.Potions.MORTAL_COIL))) {
/* 318 */         player.func_82170_o(Witchery.Potions.MORTAL_COIL.field_76415_H);
/* 319 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 320 */         if (playerEx != null) {
/* 321 */           playerEx.clearCachedIncurablePotionEffect(Witchery.Potions.MORTAL_COIL);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 329 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 334 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 339 */     super.func_70636_d();
/*     */     
/* 341 */     if (this.attackTimer > 0) {
/* 342 */       this.attackTimer -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 348 */     boolean immune = this.isImmune;
/* 349 */     if (immune) {
/* 350 */       return false;
/*     */     }
/* 352 */     if (source.func_76355_l().equals("player")) {
/* 353 */       if (func_110143_aJ() < func_110138_aP() * 0.25D) {
/* 354 */         boolean isLarge = (func_70644_a(Witchery.Potions.RESIZING)) && (func_70660_b(Witchery.Potions.RESIZING).func_76458_c() >= 2);
/*     */         
/* 356 */         return super.func_70097_a(source, Math.min(damage, isLarge ? 1.0F : 4.0F));
/*     */       }
/* 358 */       return super.func_70097_a(source, Math.min(damage, 12.0F));
/*     */     }
/*     */     
/* 361 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 368 */     return (this.isImmune) || (!source.func_76355_l().equals("player")) ? 0.0F : 2.0F;
/*     */   }
/*     */   
/*     */   public void attackEntityFromWeakness(int damage) {
/* 372 */     if (func_110143_aJ() < func_110138_aP() * 0.4D) {
/* 373 */       boolean isLarge = (func_70644_a(Witchery.Potions.RESIZING)) && (func_70660_b(Witchery.Potions.RESIZING).func_76458_c() >= 2);
/*     */       
/* 375 */       super.func_70097_a(DamageSource.field_76376_m, Math.min(damage, isLarge ? 8.0F : 15.0F));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70686_a(Class par1Class)
/*     */   {
/* 381 */     return super.func_70686_a(par1Class);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 386 */     super.func_70014_b(par1NBTTagCompound);
/* 387 */     par1NBTTagCompound.func_74757_a("PlayerCreated", isPlayerCreated());
/* 388 */     par1NBTTagCompound.func_74768_a("Invul", getInvulnerableStartTicks());
/* 389 */     par1NBTTagCompound.func_74772_a("Lifetime", getLifetime());
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 394 */     super.func_70037_a(par1NBTTagCompound);
/* 395 */     setPlayerCreated(par1NBTTagCompound.func_74767_n("PlayerCreated"));
/* 396 */     setInvulnerableStartTicks(par1NBTTagCompound.func_74762_e("Invul"));
/* 397 */     setLifetime(par1NBTTagCompound.func_74762_e("Lifetime"));
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 402 */     this.attackTimer = 10;
/* 403 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 404 */     boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), 7 + this.field_70146_Z.nextInt(15));
/*     */     
/* 406 */     if (flag) {
/* 407 */       par1Entity.field_70181_x += 0.4000000059604645D;
/*     */     }
/*     */     
/* 410 */     func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/* 411 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 417 */     if (par1 == 4) {
/* 418 */       this.attackTimer = 10;
/* 419 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     } else {
/* 421 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 427 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 432 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 437 */     return "witchery:mob.leonard.say";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 442 */     return "witchery:mob.leonard.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 447 */     return "witchery:mob.leonard.death";
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 452 */     super.func_145780_a(par1, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 458 */     Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
/*     */     
/* 460 */     int k = MathHelper.func_76136_a(this.field_70146_Z, Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), enchantment.func_77325_b());
/*     */     
/* 462 */     ItemStack itemstack = net.minecraft.init.Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
/*     */     
/* 464 */     func_70099_a(itemstack, 0.0F);
/* 465 */     func_70099_a(Witchery.Items.GENERIC.itemDemonHeart.createStack(), 0.0F);
/* 466 */     func_70099_a(new ItemStack(Witchery.Items.LEONARDS_URN), 0.0F);
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 471 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 475 */     return (this.field_70180_af.func_75683_a(16) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setPlayerCreated(boolean par1) {
/* 479 */     func_110163_bv();
/* 480 */     byte b0 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 482 */     if (par1) {
/* 483 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 485 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 491 */     return false;
/*     */   }
/*     */   
/* 494 */   private static final RandomCollection<SymbolEffect> SPELLS = ;
/*     */   
/*     */   private static RandomCollection<SymbolEffect> createSpells() {
/* 497 */     RandomCollection<SymbolEffect> spells = new RandomCollection();
/*     */     
/* 499 */     EffectRegistry.instance();spells.add(14.0D, EffectRegistry.Ignianima);
/* 500 */     EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Expelliarmus);
/* 501 */     EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Flipendo);
/* 502 */     EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Impedimenta);
/* 503 */     EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Confundus);
/*     */     
/* 505 */     return spells;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase targetEntity, float par2)
/*     */   {
/* 510 */     if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 511 */       this.attackTimer = 10;
/* 512 */       this.field_70170_p.func_72960_a(this, (byte)4);
/*     */       
/* 514 */       double d0 = targetEntity.field_70165_t - this.field_70165_t;
/* 515 */       double d1 = targetEntity.field_70121_D.field_72338_b + targetEntity.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/*     */       
/* 517 */       double d2 = targetEntity.field_70161_v - this.field_70161_v;
/*     */       
/* 519 */       float f1 = MathHelper.func_76129_c(par2) * 0.5F;
/*     */       
/* 521 */       if (!this.field_70170_p.field_72995_K) {
/* 522 */         this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */         
/* 524 */         int count = this.field_70146_Z.nextInt(10) == 0 ? 9 : 3;
/*     */         
/* 526 */         EntitySpellEffect effect = new EntitySpellEffect(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1, (SymbolEffect)SPELLS.next(), 1);
/*     */         
/* 528 */         double d8 = 1.0D;
/* 529 */         effect.field_70165_t = this.field_70165_t;
/* 530 */         effect.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F);
/* 531 */         effect.field_70161_v = this.field_70161_v;
/* 532 */         this.field_70170_p.func_72838_d(effect);
/* 533 */         effect.setShooter(this);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityLeonard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */