/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIWanderWithRestriction.IHomeLocationProvider;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.item.ItemGeneralContract;
/*     */ import com.emoniph.witchery.item.ItemTaglockKit;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TameableUtil;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityImp extends EntityTameable implements IMob, IEntitySelector, EntityAIWanderWithRestriction.IHomeLocationProvider
/*     */ {
/*     */   private float field_70926_e;
/*     */   private float field_70924_f;
/*     */   private boolean field_70928_h;
/*     */   private static final int MAX_WANDER_RANGE = 16;
/*     */   private int secretsShared;
/*     */   private int homeX;
/*     */   private int homeY;
/*     */   private int homeZ;
/*     */   private long lastGiftTime;
/*     */   private long powerUpExpiry;
/*     */   
/*     */   public EntityImp(World par1World)
/*     */   {
/*  64 */     super(par1World);
/*  65 */     this.field_70178_ae = true;
/*  66 */     func_70105_a(0.4F, 1.3F);
/*  67 */     func_70661_as().func_75491_a(true);
/*  68 */     func_70661_as().func_75495_e(true);
/*  69 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  70 */     this.field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.4F));
/*  71 */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, 1.0D, true));
/*  72 */     this.field_70714_bg.func_75776_a(4, new com.emoniph.witchery.entity.ai.EntityAIWanderWithRestriction(this, 1.0D, this));
/*  73 */     this.field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  74 */     this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
/*  75 */     this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
/*  76 */     this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
/*  77 */     this.field_70715_bh.func_75776_a(3, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*  78 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, true, this));
/*  79 */     func_70903_f(false);
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  84 */     super.func_110147_ax();
/*  85 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
/*  86 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  91 */     super.func_70088_a();
/*  92 */     this.field_70180_af.func_75682_a(18, Integer.valueOf(0));
/*  93 */     this.field_70180_af.func_75682_a(19, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   private void setAffection(int affection) {
/*  97 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(affection));
/*     */   }
/*     */   
/*     */   private int getAffection() {
/* 101 */     return this.field_70180_af.func_75679_c(18);
/*     */   }
/*     */   
/*     */   private void setPowered(boolean powered) {
/* 105 */     if (!this.field_70170_p.field_72995_K) {
/* 106 */       this.field_70180_af.func_75692_b(19, Integer.valueOf(powered ? 1 : 0));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isPowered() {
/* 111 */     return this.field_70180_af.func_75679_c(19) == 1;
/*     */   }
/*     */   
/*     */   public boolean func_82704_a(Entity target)
/*     */   {
/* 116 */     if (!func_70909_n()) {
/* 117 */       return target instanceof EntityPlayer;
/*     */     }
/* 119 */     return target == func_70638_az();
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 125 */     if (func_94056_bM()) {
/* 126 */       return func_94057_bL();
/*     */     }
/* 128 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.imp.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 139 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/* 141 */     par1NBTTagCompound.func_74768_a("Affection", getAffection());
/* 142 */     par1NBTTagCompound.func_74768_a("SecretsShared", this.secretsShared);
/* 143 */     par1NBTTagCompound.func_74772_a("LastGiftTime", this.lastGiftTime);
/* 144 */     par1NBTTagCompound.func_74772_a("PowerUpUntil2", this.powerUpExpiry);
/* 145 */     par1NBTTagCompound.func_74768_a("HomeLocX", this.homeX);
/* 146 */     par1NBTTagCompound.func_74768_a("HomeLocY", this.homeY);
/* 147 */     par1NBTTagCompound.func_74768_a("HomeLocZ", this.homeZ);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 152 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/* 154 */     setAffection(par1NBTTagCompound.func_74762_e("Affection"));
/* 155 */     this.secretsShared = par1NBTTagCompound.func_74762_e("SecretsShared");
/* 156 */     this.lastGiftTime = par1NBTTagCompound.func_74763_f("LastGiftTime");
/*     */     
/* 158 */     long time = TimeUtil.getServerTimeInTicks();
/*     */     
/* 160 */     if (par1NBTTagCompound.func_74764_b("PowerUpUntil2")) {
/* 161 */       this.powerUpExpiry = par1NBTTagCompound.func_74763_f("PowerUpUntil2");
/* 162 */     } else if (par1NBTTagCompound.func_74764_b("PowerUpUntil")) {
/* 163 */       this.powerUpExpiry = par1NBTTagCompound.func_74763_f("PowerUpUntil");
/* 164 */       if (this.powerUpExpiry > 0L) {
/* 165 */         this.powerUpExpiry = (time + TimeUtil.minsToTicks(60));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 170 */     if (time < this.powerUpExpiry) {
/* 171 */       setPowered(true);
/*     */     }
/*     */     
/* 174 */     this.homeX = par1NBTTagCompound.func_74762_e("HomeLocX");
/* 175 */     this.homeY = par1NBTTagCompound.func_74762_e("HomeLocY");
/* 176 */     this.homeZ = par1NBTTagCompound.func_74762_e("HomeLocZ");
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 181 */     return "witchery:mob.imp.laugh";
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70647_i()
/*     */   {
/* 187 */     return isPowered() ? (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.7F : (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.1F;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 192 */     return "witchery:mob.imp.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 197 */     return "witchery:mob.imp.death";
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/* 202 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 207 */     return TimeUtil.secsToTicks(40);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 212 */     super.func_70636_d();
/*     */     
/* 214 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 216 */       if ((TimeUtil.secondsElapsed(300, this.field_70173_aa)) && (TameableUtil.hasOwner(this))) {
/* 217 */         EntityLivingBase owner = func_70902_q();
/* 218 */         if ((owner instanceof EntityPlayer)) {
/* 219 */           EntityPlayer player = (EntityPlayer)owner;
/* 220 */           setAffection(Math.max(0, getAffection() - 1));
/* 221 */           if ((getAffection() == 0) && (this.field_70173_aa > TimeUtil.minsToTicks(60)) && 
/* 222 */             (this.field_70170_p.field_73012_v.nextDouble() < 0.01D)) {
/* 223 */             ParticleEffect.FLAME.send(SoundEffect.WITCHERY_MOB_IMP_LAUGH, this, 1.0D, 1.0D, 16);
/* 224 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.goodbye", new Object[] { func_70005_c_() });
/* 225 */             func_70106_y();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 232 */     if ((!this.field_70170_p.field_72995_K) && (this.powerUpExpiry > 0L) && (isPowerupExpired())) {
/* 233 */       setPowered(false);
/* 234 */       this.powerUpExpiry = 0L;
/*     */     }
/* 236 */     if (this.field_70173_aa % 20 == 0)
/*     */     {
/* 238 */       if (isPowered()) {
/* 239 */         if (this.field_70130_N != 0.6D) {
/* 240 */           func_70105_a(0.6F, 1.3F);
/*     */         }
/* 242 */         if (!this.field_70170_p.field_72995_K) {
/* 243 */           func_70691_i(1.0F);
/*     */         }
/*     */       }
/* 246 */       else if (this.field_70130_N != 0.4D) {
/* 247 */         func_70105_a(0.4F, 1.3F);
/*     */       }
/*     */     }
/*     */     
/* 251 */     if (this.field_70173_aa % 400 == 0) {
/* 252 */       func_70691_i(1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isPowerupExpired() {
/* 257 */     return TimeUtil.getServerTimeInTicks() >= this.powerUpExpiry;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 262 */     super.func_70071_h_();
/* 263 */     if ((this.field_70170_p.field_72995_K) && (isPowered())) {
/* 264 */       this.field_70170_p.func_72869_a(ParticleEffect.FLAME.toString(), this.field_70165_t - this.field_70130_N * 0.5D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N, 0.1D + this.field_70163_u + this.field_70170_p.field_73012_v.nextDouble() * 2.0D, this.field_70161_v - this.field_70130_N * 0.5D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 271 */     return super.func_70097_a(source, Math.min(damage, isPowered() ? 5.0F : 15.0F));
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 276 */     return par1Entity.func_70097_a(DamageSource.func_76358_a(this), isPowered() ? 8.0F : 4.0F);
/*     */   }
/*     */   
/* 279 */   private static final HashMap<Item, Integer> shinies = new HashMap();
/*     */   
/* 281 */   static { shinies.put(new ItemStack(Items.field_151045_i).func_77973_b(), Integer.valueOf(8));
/* 282 */     shinies.put(new ItemStack(Items.field_151056_x).func_77973_b(), Integer.valueOf(24));
/* 283 */     shinies.put(new ItemStack(Items.field_151012_L).func_77973_b(), Integer.valueOf(16));
/* 284 */     shinies.put(new ItemStack(Items.field_151048_u).func_77973_b(), Integer.valueOf(16));
/* 285 */     shinies.put(new ItemStack(Items.field_151047_v).func_77973_b(), Integer.valueOf(8));
/* 286 */     shinies.put(new ItemStack(Items.field_151046_w).func_77973_b(), Integer.valueOf(24));
/* 287 */     shinies.put(new ItemStack(Items.field_151166_bC).func_77973_b(), Integer.valueOf(3));
/* 288 */     shinies.put(new ItemStack(Items.field_151043_k).func_77973_b(), Integer.valueOf(1));
/* 289 */     shinies.put(new ItemStack(Items.field_151156_bN).func_77973_b(), Integer.valueOf(16));
/* 290 */     shinies.put(new ItemStack(Items.field_151072_bj).func_77973_b(), Integer.valueOf(1));
/* 291 */     shinies.put(new ItemStack(Items.field_151073_bk).func_77973_b(), Integer.valueOf(4));
/* 292 */     shinies.put(new ItemStack(Items.field_151006_E).func_77973_b(), Integer.valueOf(3));
/* 293 */     shinies.put(new ItemStack(Items.field_151010_B).func_77973_b(), Integer.valueOf(2));
/* 294 */     shinies.put(new ItemStack(Items.field_151013_M).func_77973_b(), Integer.valueOf(2));
/* 295 */     shinies.put(new ItemStack(Items.field_151011_C).func_77973_b(), Integer.valueOf(1));
/* 296 */     shinies.put(new ItemStack(Items.field_151005_D).func_77973_b(), Integer.valueOf(3));
/* 297 */     shinies.put(new ItemStack(Blocks.field_150340_R).func_77973_b(), Integer.valueOf(9));
/* 298 */     shinies.put(new ItemStack(Blocks.field_150475_bE).func_77973_b(), Integer.valueOf(27));
/* 299 */     shinies.put(new ItemStack(Blocks.field_150484_ah).func_77973_b(), Integer.valueOf(72));
/* 300 */     shinies.put(new ItemStack(Blocks.field_150368_y).func_77973_b(), Integer.valueOf(7));
/* 301 */     shinies.put(new ItemStack(Blocks.field_150451_bX).func_77973_b(), Integer.valueOf(5));
/*     */   }
/*     */   
/*     */   private static final int REWARD_AFFECTION_LEVEL = 20;
/* 305 */   private static final long GIFT_DELAY_TICKS = TimeUtil.minsToTicks(3);
/*     */   
/* 307 */   private static final ItemStack[] EXTRA_DROPS = { Witchery.Items.GENERIC.itemBatWool.createStack(5), Witchery.Items.GENERIC.itemDogTongue.createStack(5), Witchery.Items.GENERIC.itemToeOfFrog.createStack(2), Witchery.Items.GENERIC.itemOwletsWing.createStack(2), Witchery.Items.GENERIC.itemBranchEnt.createStack(1), Witchery.Items.GENERIC.itemInfernalBlood.createStack(2), Witchery.Items.GENERIC.itemCreeperHeart.createStack(2) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 319 */     ItemStack stack = player.field_71071_by.func_70448_g();
/* 320 */     if (stack == null) {
/* 321 */       return true;
/*     */     }
/* 323 */     if (this.field_70170_p.field_72995_K) {
/* 324 */       return false;
/*     */     }
/*     */     
/* 327 */     if (func_70909_n()) {
/* 328 */       if (Witchery.Items.GENERIC.itemDemonHeart.isMatch(stack)) {
/* 329 */         if (!player.field_71075_bZ.field_75098_d) {
/* 330 */           stack.field_77994_a -= 1;
/* 331 */           if (stack.field_77994_a <= 0) {
/* 332 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */           }
/*     */         }
/* 335 */         if (!this.field_70170_p.field_72995_K) {
/* 336 */           this.powerUpExpiry = (TimeUtil.getServerTimeInTicks() + TimeUtil.minsToTicks(60));
/* 337 */           setPowered(true);
/* 338 */           SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 339 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.power", new Object[] { func_70005_c_() });
/*     */         }
/* 341 */       } else if (Witchery.Items.GENERIC.itemIcyNeedle.isMatch(stack)) {
/* 342 */         if (!player.field_71075_bZ.field_75098_d) {
/* 343 */           stack.field_77994_a -= 1;
/* 344 */           if (stack.field_77994_a <= 0) {
/* 345 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */           }
/*     */         }
/* 348 */         if (!this.field_70170_p.field_72995_K) {
/* 349 */           this.powerUpExpiry = 0L;
/* 350 */           setPowered(false);
/* 351 */           SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 352 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.powerloss", new Object[] { func_70005_c_() });
/*     */         }
/* 354 */       } else if (ItemGeneralContract.isBoundContract(stack)) {
/* 355 */         if (!this.field_70170_p.field_72995_K) {
/* 356 */           SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 357 */           if (!isPowered()) {
/* 358 */             if (getAffection() >= 20) {
/* 359 */               long timeNow = TimeUtil.getServerTimeInTicks();
/* 360 */               if ((timeNow > this.lastGiftTime + GIFT_DELAY_TICKS) || (player.field_71075_bZ.field_75098_d)) {
/* 361 */                 ItemGeneralContract contract = ItemGeneralContract.getContract(stack);
/* 362 */                 EntityLivingBase targetEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(this.field_70170_p, player, stack, Integer.valueOf(1));
/*     */                 
/* 364 */                 if (targetEntity != null) {
/* 365 */                   if (contract.activate(stack, targetEntity)) {
/* 366 */                     this.lastGiftTime = timeNow;
/* 367 */                     ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.feelthefire", new Object[] { func_70005_c_(), targetEntity.func_70005_c_() });
/* 368 */                     if (!player.field_71075_bZ.field_75098_d) {
/* 369 */                       stack.field_77994_a -= 1;
/* 370 */                       if (stack.field_77994_a <= 0) {
/* 371 */                         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */                       }
/*     */                     }
/*     */                   } else {
/* 375 */                     ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.failed", new Object[] { func_70005_c_(), targetEntity.func_70005_c_() });
/*     */                   }
/*     */                 } else {
/* 378 */                   String name = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
/* 379 */                   ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.cannotfind", new Object[] { func_70005_c_(), name });
/*     */                 }
/*     */               } else {
/* 382 */                 ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.toooften", new Object[] { func_70005_c_() });
/*     */               }
/*     */             } else {
/* 385 */               ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.notliked", new Object[] { func_70005_c_() });
/*     */             }
/*     */           } else {
/* 388 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.toomuchpower", new Object[] { func_70005_c_() });
/*     */           }
/*     */         }
/*     */       } else {
/* 392 */         if (!this.field_70170_p.field_72995_K) {
/* 393 */           Integer affectionBoost = (Integer)shinies.get(stack.func_77973_b());
/* 394 */           if ((affectionBoost != null) && (stack.func_77960_j() == 0)) {
/* 395 */             long timeNow = TimeUtil.getServerTimeInTicks();
/*     */             
/*     */ 
/* 398 */             if (!player.field_71075_bZ.field_75098_d) {
/* 399 */               stack.field_77994_a -= 1;
/* 400 */               if (stack.field_77994_a <= 0) {
/* 401 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */               }
/*     */             }
/*     */             
/* 405 */             int affection = getAffection() + affectionBoost.intValue();
/* 406 */             SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/*     */             
/* 408 */             if ((affection >= 20) && ((timeNow > this.lastGiftTime + GIFT_DELAY_TICKS) || (player.field_71075_bZ.field_75098_d)) && (this.field_70146_Z.nextInt(Math.max(1, 10 - Math.max(affection - 20, 0))) == 0))
/*     */             {
/* 410 */               this.lastGiftTime = timeNow;
/* 411 */               affection = 0;
/* 412 */               ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.reciprocate", new Object[] { func_70005_c_() });
/*     */               
/* 414 */               ItemStack stackForPlayer = null;
/* 415 */               switch (this.secretsShared) {
/*     */               case 0: 
/* 417 */                 stackForPlayer = Witchery.Items.GENERIC.itemBrewSoulHunger.createStack();
/* 418 */                 this.secretsShared += 1;
/* 419 */                 break;
/*     */               case 1: 
/* 421 */                 stackForPlayer = Witchery.Items.GENERIC.itemBrewSoulFear.createStack();
/* 422 */                 this.secretsShared += 1;
/* 423 */                 break;
/*     */               case 2: 
/* 425 */                 stackForPlayer = Witchery.Items.GENERIC.itemBrewSoulAnguish.createStack();
/* 426 */                 this.secretsShared += 1;
/* 427 */                 break;
/*     */               case 3: 
/* 429 */                 stackForPlayer = Witchery.Items.GENERIC.itemContractTorment.createStack();
/* 430 */                 this.secretsShared += 1;
/* 431 */                 break;
/*     */               default: 
/* 433 */                 stackForPlayer = EXTRA_DROPS[this.field_70146_Z.nextInt(EXTRA_DROPS.length)].func_77946_l();
/*     */               }
/*     */               
/* 436 */               if (stackForPlayer != null) {
/* 437 */                 ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_HARP, this, 1.0D, 1.0D, 16);
/* 438 */                 this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, stackForPlayer));
/*     */               }
/* 440 */             } else if (timeNow < this.lastGiftTime + GIFT_DELAY_TICKS) {
/* 441 */               ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.toomany", new Object[] { func_70005_c_() });
/*     */             } else {
/* 443 */               ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.like", new Object[] { func_70005_c_() });
/*     */             }
/*     */             
/* 446 */             setAffection(affection);
/*     */           }
/*     */           else {
/* 449 */             SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 450 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.hate", new Object[] { func_70005_c_() });
/*     */           }
/*     */         }
/* 453 */         return true;
/*     */       }
/* 455 */     } else if (Witchery.Items.GENERIC.itemContractOwnership.isMatch(stack)) {
/* 456 */       if (!this.field_70170_p.field_72995_K)
/*     */       {
/* 458 */         EntityLivingBase boundEntity = ItemGeneralContract.getBoundEntity(this.field_70170_p, player, stack);
/* 459 */         if (boundEntity == player) {
/* 460 */           int EXPERIENCE_NEEDED = 25;
/* 461 */           if ((player.field_71068_ca >= 25) || (player.field_71075_bZ.field_75098_d)) {
/* 462 */             if (!player.field_71075_bZ.field_75098_d) {
/* 463 */               stack.field_77994_a -= 1;
/* 464 */               if (stack.field_77994_a <= 0) {
/* 465 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */               }
/*     */             }
/* 468 */             player.func_82242_a(-25);
/*     */             
/* 470 */             func_70903_f(true);
/* 471 */             TameableUtil.setOwner(this, player);
/* 472 */             func_70624_b(null);
/* 473 */             func_70778_a(null);
/* 474 */             this.homeX = ((int)this.field_70165_t);
/* 475 */             this.homeY = ((int)this.field_70163_u);
/* 476 */             this.homeZ = ((int)this.field_70161_v);
/* 477 */             func_110163_bv();
/* 478 */             SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 479 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.deal", new Object[] { func_70005_c_() });
/* 480 */             func_94058_c(getDemonName(this.field_70146_Z));
/*     */           } else {
/* 482 */             SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 483 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.noxp", new Object[] { func_70005_c_() });
/*     */           }
/*     */         }
/* 486 */         else if (boundEntity != null) {
/* 487 */           SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 488 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.notowners", new Object[] { func_70005_c_() });
/*     */         } else {
/* 490 */           SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5F, func_70647_i());
/* 491 */           ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.unsigned", new Object[] { func_70005_c_() });
/*     */         }
/*     */       }
/*     */       
/* 495 */       return true;
/*     */     }
/*     */     
/* 498 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityImp createChild(EntityAgeable par1EntityAgeable)
/*     */   {
/* 504 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_70878_b(EntityAnimal par1EntityAnimal)
/*     */   {
/* 509 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 514 */     return true;
/*     */   }
/*     */   
/*     */   private static String getDemonName(Random rand) {
/* 518 */     if (rand.nextInt(5) == 0) {
/* 519 */       return DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)];
/*     */     }
/* 521 */     return DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)] + " " + DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)];
/*     */   }
/*     */   
/*     */ 
/* 525 */   private static final String[] DEMON_NAMES = { "Ppaironael", "Aethon", "Tyrnak", "Beelzebuth", "Botis", "Moloch", "Taet", "Epnanaet", "Unonom", "Hexpemsazon", "Thayax", "Ethahoat", "Pruslas", "Ahtuxies", "Laripael", "Elxar", "Tarihimal", "Sapanolr", "Sahaminapiel", "Honed", "Oghmus", "Zedeson", "Halmaneop", "Nopoz", "Ekarnahox", "Sacuhatakael", "Ticos", "Arametheus", "Azmodaeus", "Larhepeis", "Topriraiz", "Rarahaimzah", "Tedrahamael", "Osaselael", "Phlegon", "Nelokhiel", "Haristum", "Zul", "Larhepeis", "Aamon", "Tramater", "Ehhbes", "Kra`an", "Quarax", "Hotesiatrem", "Surgat", "Nu`uhn", "Litedabh", "Unonom", "Bolenoz", "Hilopael", "Haristum", "Uhn", "Hiepacth", "Pemcapso", "Ankou", "Pundohien", "Koit", "Montobulus", "Amsaset", "Aropet", "Isnal", "Solael", "Exroh", "Sidragrosam", "Pnecamob", "Malashim", "Beelzebuth", "Ehohit", "Izatap", "Olon", "Assoaz", "Agalierept", "Krakus", "Umlaboor", "Aknrar", "Damaz", "Rhysus", "Pundohien", "Ba`al", "Rasuniolpas", "Anhoor", "Nyarlathotep", "Krakus", "Larhepeis", "Itakup", "Erdok", "Umlaboor", "Ezon", "Krakus", "Glassyalabolas", "Kra`an", "Ehnnat", "Terxor", "Asramel", "Tadal", "Arpzih", "Azmodaeus", "Henbolaron", "Rhysus" };
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
/*     */   public double getHomeX()
/*     */   {
/* 538 */     return this.homeX;
/*     */   }
/*     */   
/*     */   public double getHomeY()
/*     */   {
/* 543 */     return this.homeY;
/*     */   }
/*     */   
/*     */   public double getHomeZ()
/*     */   {
/* 548 */     return this.homeZ;
/*     */   }
/*     */   
/*     */   public double getHomeRange()
/*     */   {
/* 553 */     return 16.0D;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityImp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */