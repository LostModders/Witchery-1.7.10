/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockMirror;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
/*     */ import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
/*     */ import com.emoniph.witchery.integration.ModHookManager;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import com.emoniph.witchery.util.IHandleDT;
/*     */ import com.emoniph.witchery.util.RandomCollection;
/*     */ import com.emoniph.witchery.util.TransformCreature;
/*     */ import com.google.common.collect.Multimap;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.io.File;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ImageBufferDownload;
/*     */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityReflection extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IHandleDT
/*     */ {
/*     */   private int attackTimer;
/*     */   private boolean freeSpawn;
/*     */   private boolean isVampire;
/*  69 */   private int livingTicks = -1;
/*  70 */   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
/*  71 */   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.2D, false);
/*     */   
/*     */   public EntityReflection(World world)
/*     */   {
/*  75 */     super(world);
/*     */     
/*  77 */     func_70105_a(0.6F, 1.8F);
/*  78 */     this.field_70178_ae = true;
/*  79 */     func_70661_as().func_75491_a(true);
/*  80 */     func_70661_as().func_75495_e(true);
/*  81 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  82 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
/*  83 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  84 */     this.field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*     */     
/*  86 */     this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  87 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  88 */     this.field_70728_aV = 50;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  93 */     super.func_70088_a();
/*  94 */     this.field_70180_af.func_75682_a(17, "");
/*  95 */     this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*  99 */   private String owner = "";
/*     */   
/*     */   public String getOwnerSkin() {
/* 102 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public String getOwnerName() {
/* 106 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setOwnerSkin(String skinName) {
/* 110 */     this.field_70180_af.func_75692_b(17, skinName);
/*     */   }
/*     */   
/*     */   public void setOwner(String par1Str) {
/* 114 */     func_110163_bv();
/* 115 */     this.owner = par1Str;
/*     */   }
/*     */   
/*     */   public EntityPlayer getOwnerEntity() {
/* 119 */     return this.field_70170_p.func_72924_a(getOwnerName());
/*     */   }
/*     */   
/*     */   public void setModel(int model) {
/* 123 */     this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)model));
/*     */   }
/*     */   
/*     */   public int getModel() {
/* 127 */     return this.field_70180_af.func_75683_a(18);
/*     */   }
/*     */   
/*     */   public void setLifetime(int ticks) {
/* 131 */     this.livingTicks = ticks;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 136 */     super.func_110147_ax();
/* 137 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/* 138 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
/* 139 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
/* 140 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/* 141 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 150 */     if (func_94056_bM()) {
/* 151 */       return func_94057_bL();
/*     */     }
/* 153 */     String owner = getOwnerName();
/* 154 */     return (owner == null) || (owner.isEmpty()) ? net.minecraft.util.StatCollector.func_74838_a("entity.witchery.reflection.name") : owner;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 165 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 170 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 175 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/*     */   private static enum Task {
/* 179 */     NONE,  MELEE,  RANGED;
/*     */     
/*     */     private Task() {} }
/* 182 */   private Task task = Task.NONE;
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 186 */     super.func_70636_d();
/*     */     
/* 188 */     if (this.attackTimer > 0) {
/* 189 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/* 192 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa % 30 == 1))
/*     */     {
/* 194 */       if ((!this.freeSpawn) && (this.field_71093_bK != Config.instance().dimensionMirrorID)) {
/* 195 */         func_70106_y();
/* 196 */         return;
/*     */       }
/*     */       
/* 199 */       if ((this.livingTicks > -1) && (--this.livingTicks == 0)) {
/* 200 */         func_70106_y();
/* 201 */         return;
/*     */       }
/*     */       
/* 204 */       double R = 10.0D;
/* 205 */       double RY = 8.0D;
/* 206 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - R, this.field_70163_u - RY, this.field_70161_v - R, this.field_70165_t + R, this.field_70163_u + RY, this.field_70161_v + R);
/*     */       
/* 208 */       List<EntityPlayer> players = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds);
/*     */       
/* 210 */       EntityPlayer ownerEntity = getOwnerEntity();
/* 211 */       boolean ownerFound = false;
/*     */       
/* 213 */       EntityPlayer closest = null;
/* 214 */       double distance = Double.MAX_VALUE;
/* 215 */       for (EntityPlayer player : players) {
/* 216 */         double newDistance = player.func_70068_e(this);
/* 217 */         if ((closest == null) || (newDistance < distance)) {
/* 218 */           closest = player;
/* 219 */           distance = newDistance;
/*     */         }
/* 221 */         if (ownerEntity == player) {
/* 222 */           ownerFound = true;
/*     */         }
/*     */       }
/*     */       
/* 226 */       if ((ownerEntity == null) || (!ownerFound)) {
/* 227 */         if (closest != null) {
/* 228 */           setOwner(closest.func_70005_c_());
/*     */         } else {
/* 230 */           setOwner("");
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 236 */       boolean resetGear = true;
/* 237 */       String skinName = getOwnerName();
/* 238 */       if (!getOwnerName().isEmpty()) {
/* 239 */         EntityPlayer owner = (ownerEntity == null) || (!ownerFound) ? getOwnerEntity() : ownerEntity;
/* 240 */         if (owner != null)
/*     */         {
/*     */ 
/*     */ 
/* 244 */           for (int slot = 1; slot <= 4; slot++) {
/* 245 */             ItemStack stack = owner.func_71124_b(slot);
/* 246 */             if (stack != null) {
/* 247 */               stack = stack.func_77946_l();
/*     */             }
/* 249 */             func_70062_b(slot, stack);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 254 */           ItemStack bestWeapon = null;
/* 255 */           double bestDamage = 0.0D;
/* 256 */           for (int hot = 0; hot < 9; hot++) {
/* 257 */             ItemStack stack = owner.field_71071_by.func_70301_a(hot);
/* 258 */             if (stack != null) {
/* 259 */               Multimap modifierMap = stack.func_111283_C();
/* 260 */               Iterator itr = modifierMap.get(SharedMonsterAttributes.field_111264_e.func_111108_a()).iterator();
/*     */               
/* 262 */               double damage = 0.0D;
/* 263 */               while (itr.hasNext()) {
/* 264 */                 AttributeModifier modifier = (AttributeModifier)itr.next();
/* 265 */                 if (modifier.func_111169_c() == 0) {
/* 266 */                   damage += modifier.func_111164_d();
/*     */                 }
/*     */               }
/*     */               
/* 270 */               if (damage > bestDamage) {
/* 271 */                 bestWeapon = stack;
/* 272 */                 bestDamage = damage;
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 278 */           ExtendedPlayer playerEx = ExtendedPlayer.get(owner);
/* 279 */           if (playerEx != null) {
/* 280 */             setModel(playerEx.getCreatureType() == TransformCreature.WOLFMAN ? 1 : 0);
/* 281 */             this.isVampire = playerEx.isVampire();
/* 282 */             if (playerEx.getCreatureType() == TransformCreature.PLAYER) {
/* 283 */               skinName = playerEx.getOtherPlayerSkin();
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 289 */           ItemStack stack = bestWeapon != null ? bestWeapon : owner.func_71124_b(0);
/* 290 */           if (stack != null) {
/* 291 */             stack = stack.func_77946_l();
/* 292 */             Witchery.modHooks.makeItemModProof(stack);
/*     */           }
/*     */           
/* 295 */           if (getModel() == 1) {
/* 296 */             stack = null;
/* 297 */             func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
/*     */           } else {
/* 299 */             func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*     */           }
/* 301 */           func_70062_b(0, stack);
/*     */           
/* 303 */           resetGear = false;
/*     */           
/* 305 */           if (this.field_70173_aa % 60 == 1)
/*     */           {
/* 307 */             func_70674_bp();
/* 308 */             Iterator effects = owner.func_70651_bq().iterator();
/* 309 */             while (effects.hasNext()) {
/* 310 */               PotionEffect effect = (PotionEffect)effects.next();
/* 311 */               func_70690_d(new PotionEffect(effect));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 317 */       if (resetGear) {
/* 318 */         for (int slot = 0; slot <= 4; slot++) {
/* 319 */           func_70062_b(slot, null);
/*     */         }
/*     */       }
/*     */       
/* 323 */       setOwnerSkin(skinName);
/*     */       
/*     */ 
/* 326 */       ItemStack held = func_70694_bm();
/* 327 */       if (held != null) {
/* 328 */         if (held.func_77973_b() == Witchery.Items.MYSTIC_BRANCH) {
/* 329 */           if (this.task == Task.MELEE) {
/* 330 */             this.field_70714_bg.func_85156_a(this.aiAttackOnCollide);
/*     */           }
/* 332 */           this.field_70714_bg.func_75776_a(2, this.aiArrowAttack);
/* 333 */           this.task = Task.RANGED;
/*     */         }
/* 335 */         else if ((held.func_77973_b() == Witchery.Items.CROSSBOW_PISTOL) || ((held.func_77973_b() instanceof ItemBow)))
/*     */         {
/* 337 */           if (this.task == Task.MELEE) {
/* 338 */             this.field_70714_bg.func_85156_a(this.aiAttackOnCollide);
/*     */           }
/* 340 */           this.field_70714_bg.func_75776_a(2, this.aiArrowAttack);
/* 341 */           this.task = Task.RANGED;
/*     */         } else {
/* 343 */           if (this.task == Task.RANGED) {
/* 344 */             this.field_70714_bg.func_85156_a(this.aiArrowAttack);
/*     */           }
/* 346 */           this.field_70714_bg.func_75776_a(2, this.aiAttackOnCollide);
/* 347 */           this.task = Task.MELEE;
/*     */         }
/*     */       } else {
/* 350 */         if (this.task == Task.RANGED) {
/* 351 */           this.field_70714_bg.func_85156_a(this.aiArrowAttack);
/*     */         }
/* 353 */         this.field_70714_bg.func_75776_a(2, this.aiAttackOnCollide);
/* 354 */         this.task = Task.MELEE;
/*     */       }
/*     */       
/* 357 */       if ((func_70089_S()) && (func_70638_az() != null) && (func_70661_as().func_75500_f()) && (func_70635_at().func_75522_a(func_70638_az()))) {
/* 358 */         EffectRegistry.instance();castSpell(func_70638_az(), 1.0F, EffectRegistry.Attraho);
/*     */       }
/*     */     }
/*     */     
/* 362 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.field_73012_v.nextDouble() < 0.05D) && (func_70638_az() != null) && ((func_70638_az().field_70160_al) || (((func_70638_az() instanceof EntityPlayer)) && (((EntityPlayer)func_70638_az()).field_71075_bZ.field_75100_b))) && (!func_70638_az().func_70644_a(Potion.field_76421_d)))
/*     */     {
/*     */ 
/*     */ 
/* 366 */       func_70638_az().func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 5));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource p_70645_1_)
/*     */   {
/* 373 */     super.func_70645_a(p_70645_1_);
/* 374 */     Witchery.Blocks.MIRROR.demonSlain(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 379 */     return super.func_70097_a(source, Math.min(damage, 6.0F));
/*     */   }
/*     */   
/*     */   public float getCapDT(DamageSource source, float damage)
/*     */   {
/* 384 */     return 2.0F;
/*     */   }
/*     */   
/*     */   public boolean func_70686_a(Class par1Class)
/*     */   {
/* 389 */     return super.func_70686_a(par1Class);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 394 */     super.func_70014_b(nbtRoot);
/* 395 */     nbtRoot.func_74778_a("Owner", getOwnerName());
/* 396 */     nbtRoot.func_74778_a("OwnerSkin", getOwnerSkin());
/* 397 */     nbtRoot.func_74768_a("Model", getModel());
/* 398 */     nbtRoot.func_74757_a("FreeSpawn", this.freeSpawn);
/* 399 */     nbtRoot.func_74757_a("Vampire", this.isVampire);
/* 400 */     nbtRoot.func_74768_a("LivingTicks", this.livingTicks);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 406 */     super.func_70037_a(nbtRoot);
/* 407 */     setOwner(nbtRoot.func_74779_i("Owner"));
/* 408 */     setOwnerSkin(nbtRoot.func_74779_i("OwnerSkin"));
/* 409 */     this.freeSpawn = nbtRoot.func_74767_n("FreeSpawn");
/* 410 */     this.livingTicks = nbtRoot.func_74762_e("LivingTicks");
/* 411 */     this.isVampire = nbtRoot.func_74767_n("Vampire");
/* 412 */     setModel(nbtRoot.func_74762_e("Model"));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 418 */     this.attackTimer = 10;
/*     */     
/* 420 */     boolean flag = super.func_70652_k(par1Entity);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 427 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 433 */     if (par1 == 4) {
/* 434 */       this.attackTimer = 10;
/*     */     }
/*     */     else {
/* 437 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 443 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 448 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 453 */     return "witchery:mob.reflection.say";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 458 */     return "witchery:mob.reflection.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 463 */     return "witchery:mob.reflection.death";
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 468 */     super.func_145780_a(par1, par2, par3, par4);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 473 */     func_70099_a(Witchery.Items.GENERIC.itemDemonHeart.createStack(), 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_82160_b(boolean p_82160_1_, int p_82160_2_) {}
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 483 */     return null;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 488 */     return false;
/*     */   }
/*     */   
/* 491 */   private static final RandomCollection<SymbolEffect> SPELLS = ;
/*     */   
/*     */   private static RandomCollection<SymbolEffect> createSpells() {
/* 494 */     RandomCollection<SymbolEffect> spells = new RandomCollection();
/*     */     
/* 496 */     EffectRegistry.instance();spells.add(14.0D, EffectRegistry.Ignianima);
/* 497 */     EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Expelliarmus);
/* 498 */     EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Flipendo);
/* 499 */     EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Impedimenta);
/* 500 */     EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Confundus);
/*     */     
/* 502 */     return spells;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_82196_d(EntityLivingBase targetEntity, float par2)
/*     */   {
/* 508 */     ItemStack held = func_70694_bm();
/* 509 */     if (held == null) {
/* 510 */       return;
/*     */     }
/* 512 */     this.attackTimer = 10;
/* 513 */     this.field_70170_p.func_72960_a(this, (byte)4);
/*     */     
/* 515 */     if (held.func_77973_b() == Witchery.Items.MYSTIC_BRANCH) {
/* 516 */       if (this.field_70170_p.field_73012_v.nextBoolean()) {
/* 517 */         castSpell(targetEntity, par2, (SymbolEffect)SPELLS.next());
/*     */       }
/* 519 */     } else if (held.func_77973_b() == Witchery.Items.CROSSBOW_PISTOL) {
/* 520 */       EntityBolt entityarrow = new EntityBolt(this.field_70170_p, this, targetEntity, 1.6F, 14 - this.field_70170_p.field_73013_u.func_151525_a() * 4);
/*     */       
/* 522 */       int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
/* 523 */       int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
/* 524 */       entityarrow.setDamage(par2 * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.field_73013_u.func_151525_a() * 0.11F);
/*     */       
/*     */ 
/* 527 */       if (i > 0) {
/* 528 */         entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 531 */       if (j > 0) {
/* 532 */         entityarrow.setKnockbackStrength(j);
/*     */       }
/*     */       
/* 535 */       if ((EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, func_70694_bm()) > 0) || ((CreatureUtil.isVampire(func_70638_az())) && (this.field_70170_p.field_73012_v.nextInt(3) == 0)))
/*     */       {
/* 537 */         entityarrow.func_70015_d(100);
/*     */       }
/*     */       
/* 540 */       if (func_70638_az() != null) {
/* 541 */         if (CreatureUtil.isWerewolf(func_70638_az())) {
/* 542 */           entityarrow.setBoltType(4);
/* 543 */         } else if (CreatureUtil.isUndead(func_70638_az())) {
/* 544 */           entityarrow.setBoltType(3);
/* 545 */         } else if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 546 */           entityarrow.setBoltType(2);
/*     */         }
/*     */       }
/*     */       
/* 550 */       func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 551 */       this.field_70170_p.func_72838_d(entityarrow);
/*     */     } else {
/* 553 */       EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, targetEntity, 1.6F, 14 - this.field_70170_p.field_73013_u.func_151525_a() * 3);
/*     */       
/* 555 */       int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
/* 556 */       int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
/* 557 */       entityarrow.func_70239_b(par2 * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.field_73013_u.func_151525_a() * 0.11F);
/*     */       
/*     */ 
/* 560 */       if (i > 0) {
/* 561 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + i * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 564 */       if (j > 0) {
/* 565 */         entityarrow.func_70240_a(j);
/*     */       }
/*     */       
/* 568 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, func_70694_bm()) > 0) {
/* 569 */         entityarrow.func_70015_d(100);
/*     */       }
/*     */       
/* 572 */       func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 573 */       this.field_70170_p.func_72838_d(entityarrow);
/*     */     }
/*     */   }
/*     */   
/*     */   private void castSpell(EntityLivingBase targetEntity, float par2, SymbolEffect spell) {
/* 578 */     double d0 = targetEntity.field_70165_t - this.field_70165_t;
/* 579 */     double d1 = targetEntity.field_70121_D.field_72338_b + targetEntity.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/*     */     
/* 581 */     double d2 = targetEntity.field_70161_v - this.field_70161_v;
/*     */     
/* 583 */     float f1 = net.minecraft.util.MathHelper.func_76129_c(par2) * 0.5F;
/*     */     
/* 585 */     if (!this.field_70170_p.field_72995_K) {
/* 586 */       this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/*     */       
/* 588 */       int count = this.field_70146_Z.nextInt(10) == 0 ? 9 : 3;
/*     */       
/* 590 */       EntitySpellEffect effect = new EntitySpellEffect(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1, spell, 1);
/*     */       
/*     */ 
/* 593 */       double d8 = 1.0D;
/* 594 */       effect.field_70165_t = this.field_70165_t;
/* 595 */       effect.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F);
/* 596 */       effect.field_70161_v = this.field_70161_v;
/* 597 */       this.field_70170_p.func_72838_d(effect);
/* 598 */       effect.setShooter(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ThreadDownloadImageData downloadImageSkin;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ResourceLocation locationSkin;
/*     */   private String lastSkinOwner;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ResourceLocation getLocationSkin()
/*     */   {
/* 612 */     if ((this.locationSkin == null) || (!this.lastSkinOwner.equals(getOwnerName()))) {
/* 613 */       setupCustomSkin();
/*     */     }
/* 615 */     if (this.locationSkin != null) {
/* 616 */       return this.locationSkin;
/*     */     }
/* 618 */     return null;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void setupCustomSkin()
/*     */   {
/* 624 */     String ownerName = getOwnerSkin();
/* 625 */     if ((ownerName != null) && (!ownerName.isEmpty())) {
/* 626 */       this.locationSkin = net.minecraft.client.entity.AbstractClientPlayer.func_110311_f(ownerName);
/* 627 */       this.downloadImageSkin = getDownloadImageSkin(this.locationSkin, ownerName);
/* 628 */       this.lastSkinOwner = ownerName;
/*     */     } else {
/* 630 */       this.locationSkin = null;
/* 631 */       this.downloadImageSkin = null;
/* 632 */       this.lastSkinOwner = "";
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation location, String name) {
/* 638 */     TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
/* 639 */     Object object = texturemanager.func_110581_b(location);
/*     */     
/* 641 */     if (object == null) {
/* 642 */       object = new ThreadDownloadImageData((File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] { net.minecraft.util.StringUtils.func_76338_a(name) }), com.emoniph.witchery.client.renderer.RenderReflection.SKIN, new ImageBufferDownload());
/*     */       
/*     */ 
/*     */ 
/* 646 */       texturemanager.func_110579_a(location, (ITextureObject)object);
/*     */     }
/*     */     
/* 649 */     return (ThreadDownloadImageData)object;
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData data)
/*     */   {
/* 654 */     this.freeSpawn = true;
/* 655 */     return super.func_110161_a(data);
/*     */   }
/*     */   
/*     */   public boolean isVampire() {
/* 659 */     return this.isVampire;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityReflection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */