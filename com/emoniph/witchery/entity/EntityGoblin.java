/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIAvoidEntityConditionally;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIAvoidEntityConditionally.IAvoidEntities;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIMoveIndoorsLeashAware;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIWorship;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IMerchant;
/*     */ import net.minecraft.entity.INpc;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest2;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Tuple;
/*     */ import net.minecraft.village.MerchantRecipe;
/*     */ import net.minecraft.village.MerchantRecipeList;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.village.VillageCollection;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityGoblin extends EntityAgeable implements IMerchant, INpc, IEntitySelector, EntityAIAvoidEntityConditionally.IAvoidEntities
/*     */ {
/*     */   private int randomTickDivider;
/*     */   private boolean isMating;
/*     */   Village villageObj;
/*     */   private EntityPlayer buyingPlayer;
/*     */   private MerchantRecipeList buyingList;
/*     */   private int timeUntilReset;
/*     */   private boolean needsInitilization;
/*     */   private int wealth;
/*     */   private String lastBuyingPlayer;
/*     */   private boolean isLookingForHome;
/*     */   private float field_82191_bN;
/*  83 */   public static final Map villagersSellingList = new HashMap();
/*  84 */   public static final Map blacksmithSellingList = new HashMap();
/*     */   private EntityAIWorship aiWorship;
/*     */   private boolean preventDespawn;
/*     */   private static final double KOBOLDITE_HARVEST_CHANCE = 0.02D;
/*     */   private boolean testingLeashRange;
/*     */   
/*     */   public EntityGoblin(World par1World) {
/*  91 */     this(par1World, 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityGoblin(World par1World, int par2)
/*     */   {
/*  97 */     super(par1World);
/*  98 */     setProfession(this.field_70146_Z.nextInt(4));
/*  99 */     func_70105_a(0.6F, 0.95F);
/* 100 */     func_70661_as().func_75498_b(true);
/* 101 */     func_70661_as().func_75491_a(true);
/* 102 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/* 103 */     this.field_70714_bg.func_75776_a(1, this.aiWorship = new EntityAIWorship(this, TimeUtil.secsToTicks(30) + this.field_70146_Z.nextInt(10)));
/*     */     
/* 105 */     this.field_70714_bg.func_75776_a(2, new com.emoniph.witchery.entity.ai.EntityAIPickUpBlocks(this, 24.0D));
/* 106 */     this.field_70714_bg.func_75776_a(2, new com.emoniph.witchery.entity.ai.EntityAIDropOffBlocks(this, 24.0D));
/* 107 */     this.field_70714_bg.func_75776_a(2, new EntityAIDigBlocks(this, 16.0D, 0.02D));
/* 108 */     this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.6D, 0.6D));
/* 109 */     this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntityConditionally(this, EntityVillageGuard.class, 12.0F, 0.8D, 0.8D, this));
/*     */     
/* 111 */     this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, true));
/* 112 */     this.field_70714_bg.func_75776_a(4, new com.emoniph.witchery.entity.ai.EntityAITradePlayerGeneric(this, this));
/* 113 */     this.field_70714_bg.func_75776_a(4, new com.emoniph.witchery.entity.ai.EntityAILookAtTradePlayerGeneric(this, this));
/* 114 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveIndoorsLeashAware(this));
/*     */     
/* 116 */     this.field_70714_bg.func_75776_a(5, new EntityAIRestrictOpenDoor(this));
/* 117 */     this.field_70714_bg.func_75776_a(6, new EntityAIOpenDoor(this, true));
/* 118 */     this.field_70714_bg.func_75776_a(7, new EntityAIMoveTowardsRestriction(this, 0.6D));
/* 119 */     this.field_70714_bg.func_75776_a(8, new com.emoniph.witchery.entity.ai.EntityAIGoblinMate(this));
/*     */     
/* 121 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
/* 122 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityGoblin.class, 5.0F, 0.02F));
/* 123 */     this.field_70714_bg.func_75776_a(9, new EntityAIWander(this, 0.6D));
/* 124 */     this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
/* 125 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/* 126 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true, true, this));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82704_a(Entity entity)
/*     */   {
/* 132 */     double R = 8.0D;
/* 133 */     if ((entity instanceof EntityVillager)) {
/* 134 */       return this.field_70170_p.func_72872_a(EntityGoblin.class, AxisAlignedBB.func_72330_a(this.field_70165_t - 8.0D, this.field_70163_u - 8.0D, this.field_70161_v - 8.0D, this.field_70165_t + 8.0D, this.field_70163_u + 8.0D, this.field_70161_v + 8.0D)).size() >= 3;
/*     */     }
/*     */     
/*     */ 
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldAvoid()
/*     */   {
/* 143 */     double R = 8.0D;
/* 144 */     return this.field_70170_p.func_72872_a(EntityGoblin.class, AxisAlignedBB.func_72330_a(this.field_70165_t - 8.0D, this.field_70163_u - 8.0D, this.field_70161_v - 8.0D, this.field_70165_t + 8.0D, this.field_70163_u + 8.0D, this.field_70161_v + 8.0D)).size() >= 3;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 150 */     if (func_94056_bM()) {
/* 151 */       return func_94057_bL();
/*     */     }
/* 153 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.goblin.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 159 */     super.func_110147_ax();
/* 160 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/* 161 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity targetEntity)
/*     */   {
/* 166 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 167 */     int i = 0;
/*     */     
/* 169 */     if ((targetEntity instanceof EntityLivingBase)) {
/* 170 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)targetEntity);
/* 171 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)targetEntity);
/*     */     }
/*     */     
/* 174 */     boolean flag = targetEntity.func_70097_a(DamageSource.func_76358_a(this), f);
/*     */     
/* 176 */     if (flag) {
/* 177 */       if (i > 0) {
/* 178 */         targetEntity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/*     */         
/* 180 */         this.field_70159_w *= 0.6D;
/* 181 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 184 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 186 */       if (j > 0) {
/* 187 */         targetEntity.func_70015_d(j * 4);
/*     */       }
/*     */       
/* 190 */       if ((targetEntity instanceof EntityLivingBase)) {
/* 191 */         EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, this);
/*     */       }
/*     */       
/* 194 */       EnchantmentHelper.func_151385_b(this, targetEntity);
/*     */     }
/*     */     
/* 197 */     return flag;
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 202 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 207 */     if (--this.randomTickDivider <= 0) {
/* 208 */       this.field_70170_p.field_72982_D.func_75551_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
/*     */       
/* 210 */       this.randomTickDivider = (70 + this.field_70146_Z.nextInt(50));
/* 211 */       this.villageObj = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), 32);
/*     */       
/*     */ 
/*     */ 
/* 215 */       if (this.villageObj == null) {
/* 216 */         func_110177_bN();
/*     */       } else {
/* 218 */         this.preventDespawn = true;
/* 219 */         ChunkCoordinates chunkcoordinates = this.villageObj.func_75577_a();
/* 220 */         func_110171_b(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, (int)(this.villageObj.func_75568_b() * 0.6F));
/*     */         
/*     */ 
/* 223 */         if (this.isLookingForHome) {
/* 224 */           this.isLookingForHome = false;
/* 225 */           this.villageObj.func_82683_b(5);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 230 */     if ((!isTrading()) && (this.timeUntilReset > 0)) {
/* 231 */       this.timeUntilReset -= 1;
/*     */       
/* 233 */       if (this.timeUntilReset <= 0) {
/* 234 */         if (this.needsInitilization) {
/* 235 */           if (this.buyingList.size() > 1) {
/* 236 */             Iterator iterator = this.buyingList.iterator();
/*     */             
/* 238 */             while (iterator.hasNext()) {
/* 239 */               MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();
/*     */               
/* 241 */               if (merchantrecipe.func_82784_g()) {
/* 242 */                 merchantrecipe.func_82783_a(this.field_70146_Z.nextInt(6) + this.field_70146_Z.nextInt(6) + 2);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 247 */           addDefaultEquipmentAndRecipies(1);
/* 248 */           this.needsInitilization = false;
/*     */           
/* 250 */           if ((this.villageObj != null) && (this.lastBuyingPlayer != null)) {
/* 251 */             this.field_70170_p.func_72960_a(this, (byte)14);
/* 252 */             this.villageObj.func_82688_a(this.lastBuyingPlayer, 1);
/*     */           }
/*     */         }
/*     */         
/* 256 */         func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, 0));
/*     */       }
/*     */     }
/*     */     
/* 260 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110159_bB()
/*     */   {
/*     */     try
/*     */     {
/* 268 */       this.testingLeashRange = true;
/* 269 */       if (func_110167_bD()) {
/* 270 */         this.preventDespawn = true;
/*     */       }
/* 272 */       super.func_110159_bB();
/*     */     } finally {
/* 274 */       this.testingLeashRange = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public float func_70032_d(Entity par1Entity)
/*     */   {
/* 280 */     float distance = super.func_70032_d(par1Entity);
/* 281 */     if ((this.testingLeashRange) && (distance < 9.0F)) {
/* 282 */       distance *= 0.5F;
/*     */     }
/* 284 */     return distance;
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 289 */     super.func_70071_h_();
/*     */     
/* 291 */     if (!this.field_70170_p.field_72995_K) {
/* 292 */       setBesideClimbableBlock(this.field_70123_F);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70617_f_()
/*     */   {
/* 298 */     return super.func_70617_f_();
/*     */   }
/*     */   
/*     */   public boolean isWorking()
/*     */   {
/* 303 */     return this.field_70180_af.func_75683_a(18) == 1;
/*     */   }
/*     */   
/*     */   public void setWorking(boolean par1) {
/* 307 */     byte b0 = this.field_70180_af.func_75683_a(18);
/* 308 */     if (((par1) && (b0 != 1)) || ((!par1) && (b0 == 1))) {
/* 309 */       this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isWorshipping() {
/* 314 */     return this.field_70180_af.func_75683_a(18) == 2;
/*     */   }
/*     */   
/*     */   public void setWorshipping(boolean worshiping) {
/* 318 */     byte b0 = this.field_70180_af.func_75683_a(18);
/* 319 */     if (((worshiping) && (b0 != 2)) || ((!worshiping) && (b0 == 2))) {
/* 320 */       this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)(worshiping ? 2 : 0)));
/*     */     }
/*     */   }
/*     */   
/*     */   public void beginWorship(TileEntity tile) {
/* 325 */     this.aiWorship.begin(tile);
/*     */   }
/*     */   
/*     */   public boolean isBesideClimbableBlock() {
/* 329 */     return (this.field_70180_af.func_75683_a(17) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setBesideClimbableBlock(boolean par1) {
/* 333 */     byte b0 = this.field_70180_af.func_75683_a(17);
/*     */     
/* 335 */     if (par1) {
/* 336 */       b0 = (byte)(b0 | 0x1);
/*     */     } else {
/* 338 */       b0 = (byte)(b0 & 0xFFFFFFFE);
/*     */     }
/*     */     
/* 341 */     this.field_70180_af.func_75692_b(17, Byte.valueOf(b0));
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 346 */     ItemStack stack = player.field_71071_by.func_70448_g();
/* 347 */     boolean heldSpawnEgg = (stack != null) && (stack.func_77973_b() == Items.field_151063_bx);
/*     */     
/* 349 */     if ((!heldSpawnEgg) && (func_70089_S()) && (!isTrading()) && (!func_70631_g_()) && (!player.func_70093_af())) {
/* 350 */       if (func_110167_bD()) {
/* 351 */         if (func_70694_bm() == null) {
/* 352 */           if ((stack != null) && ((stack.func_77973_b() instanceof net.minecraft.item.ItemPickaxe))) {
/* 353 */             func_70062_b(0, stack);
/* 354 */             player.func_70062_b(0, null);
/*     */           }
/*     */         } else {
/* 357 */           if (!this.field_70170_p.field_72995_K) {
/* 358 */             ItemStack goblinItem = func_70694_bm();
/* 359 */             if (!player.field_71071_by.func_70441_a(goblinItem)) {
/* 360 */               func_70099_a(goblinItem, 1.0F);
/* 361 */             } else if ((player instanceof EntityPlayerMP)) {
/* 362 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */           }
/* 365 */           func_70062_b(0, null);
/*     */         }
/* 367 */       } else if ((!isWorking()) && (!isWorshipping()) && (this.villageObj != null) && 
/* 368 */         (!this.field_70170_p.field_72995_K)) {
/* 369 */         func_70932_a_(player);
/* 370 */         player.func_71030_a(this, func_70005_c_());
/*     */       }
/*     */       
/*     */ 
/* 374 */       return true;
/*     */     }
/* 376 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/* 382 */     super.func_70088_a();
/* 383 */     this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
/* 384 */     this.field_70180_af.func_75682_a(17, Byte.valueOf((byte)0));
/* 385 */     this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
/* 386 */     this.field_70180_af.func_75682_a(19, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 392 */     super.func_70014_b(nbtRoot);
/* 393 */     nbtRoot.func_74768_a("Profession", getProfession());
/* 394 */     nbtRoot.func_74768_a("Riches", this.wealth);
/* 395 */     nbtRoot.func_74757_a("Worshipping", isWorshipping());
/*     */     
/* 397 */     if (this.buyingList != null) {
/* 398 */       nbtRoot.func_74782_a("Offers", this.buyingList.func_77202_a());
/*     */     }
/*     */     
/* 401 */     nbtRoot.func_74757_a("PreventDespawn", this.preventDespawn);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 406 */     super.func_70037_a(nbtRoot);
/* 407 */     setProfession(nbtRoot.func_74762_e("Profession"));
/* 408 */     this.wealth = nbtRoot.func_74762_e("Riches");
/* 409 */     if ((nbtRoot.func_74767_n("Worshipping")) && (!this.field_70170_p.field_72995_K)) {
/* 410 */       setWorshipping(true);
/*     */     }
/*     */     
/* 413 */     if (nbtRoot.func_150297_b("Offers", 10)) {
/* 414 */       NBTTagCompound nbttagcompound1 = nbtRoot.func_74775_l("Offers");
/* 415 */       this.buyingList = new MerchantRecipeList(nbttagcompound1);
/*     */     }
/*     */     
/* 418 */     this.preventDespawn = nbtRoot.func_74767_n("PreventDespawn");
/*     */   }
/*     */   
/*     */   protected float func_70647_i()
/*     */   {
/* 423 */     return 1.2F;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 428 */     return (!Config.instance().goblinDespawnBlock) && (this.villageObj == null) && (!this.preventDespawn) && (!isWorshipping());
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 433 */     return isTrading() ? "witchery:mob.goblin.haggle" : "witchery:mob.goblin.idle";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 438 */     return "witchery:mob.goblin.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 443 */     return "witchery:mob.goblin.death";
/*     */   }
/*     */   
/*     */   public void setProfession(int par1) {
/* 447 */     this.field_70180_af.func_75692_b(16, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public int getProfession() {
/* 451 */     return this.field_70180_af.func_75679_c(16);
/*     */   }
/*     */   
/*     */   public boolean isMating() {
/* 455 */     return this.isMating;
/*     */   }
/*     */   
/*     */   public void setMating(boolean par1) {
/* 459 */     this.isMating = par1;
/*     */   }
/*     */   
/*     */   public void func_70604_c(EntityLivingBase par1EntityLivingBase)
/*     */   {
/* 464 */     super.func_70604_c(par1EntityLivingBase);
/*     */     
/* 466 */     if ((this.villageObj != null) && (par1EntityLivingBase != null)) {
/* 467 */       this.villageObj.func_75575_a(par1EntityLivingBase);
/*     */       
/* 469 */       if ((par1EntityLivingBase instanceof EntityPlayer))
/*     */       {
/*     */ 
/* 472 */         if (func_70631_g_())
/*     */         {
/* 474 */           this.villageObj.func_82688_a(par1EntityLivingBase.func_70005_c_(), -3);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 480 */         if (func_70089_S()) {
/* 481 */           this.field_70170_p.func_72960_a(this, (byte)13);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 489 */     if (this.villageObj != null) {
/* 490 */       Entity entity = par1DamageSource.func_76346_g();
/*     */       
/* 492 */       if (entity != null) {
/* 493 */         if (!(entity instanceof EntityPlayer))
/*     */         {
/*     */ 
/* 496 */           if ((entity instanceof IMob))
/* 497 */             this.villageObj.func_82692_h();
/*     */         }
/* 499 */       } else if (entity == null) {
/* 500 */         EntityPlayer entityplayer = this.field_70170_p.func_72890_a(this, 16.0D);
/*     */         
/* 502 */         if (entityplayer != null) {
/* 503 */           this.villageObj.func_82692_h();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 508 */     super.func_70645_a(par1DamageSource);
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi()
/*     */   {
/* 513 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 514 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 515 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/* 516 */     return (this.field_70170_p.func_147439_a(i, j - 1, k) == Blocks.field_150349_c) && (this.field_70170_p.func_72883_k(i, j, k) > 8) && (super.func_70601_bi());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70932_a_(EntityPlayer par1EntityPlayer)
/*     */   {
/* 526 */     this.buyingPlayer = par1EntityPlayer;
/*     */   }
/*     */   
/*     */   public EntityPlayer func_70931_l_()
/*     */   {
/* 531 */     return this.buyingPlayer;
/*     */   }
/*     */   
/*     */   public boolean isTrading() {
/* 535 */     return this.buyingPlayer != null;
/*     */   }
/*     */   
/*     */   public void func_70933_a(MerchantRecipe par1MerchantRecipe)
/*     */   {
/* 540 */     par1MerchantRecipe.func_77399_f();
/* 541 */     this.field_70757_a = (-func_70627_aG());
/* 542 */     func_85030_a("witchery:mob.goblin.yes", func_70599_aP(), func_70647_i());
/*     */     
/* 544 */     if (par1MerchantRecipe.func_77393_a((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) {
/* 545 */       this.timeUntilReset = 40;
/* 546 */       this.needsInitilization = true;
/*     */       
/* 548 */       if (this.buyingPlayer != null) {
/* 549 */         this.lastBuyingPlayer = this.buyingPlayer.func_70005_c_();
/*     */       } else {
/* 551 */         this.lastBuyingPlayer = null;
/*     */       }
/*     */     }
/*     */     
/* 555 */     if (par1MerchantRecipe.func_77394_a().func_77973_b() == Items.field_151166_bC) {
/* 556 */       this.wealth += par1MerchantRecipe.func_77394_a().field_77994_a;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_110297_a_(ItemStack par1ItemStack)
/*     */   {
/* 562 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70757_a > -func_70627_aG() + 20)) {
/* 563 */       this.field_70757_a = (-func_70627_aG());
/*     */       
/* 565 */       if (par1ItemStack != null) {
/* 566 */         func_85030_a("witchery:mob.goblin.yes", func_70599_aP(), func_70647_i());
/*     */       } else {
/* 568 */         func_85030_a("witchery:mob.goblin.no", func_70599_aP(), func_70647_i());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public MerchantRecipeList func_70934_b(EntityPlayer par1EntityPlayer)
/*     */   {
/* 575 */     if (this.buyingList == null) {
/* 576 */       addDefaultEquipmentAndRecipies(1);
/*     */     }
/*     */     
/* 579 */     return this.buyingList;
/*     */   }
/*     */   
/*     */   private float adjustProbability(float par1) {
/* 583 */     float f1 = par1 + this.field_82191_bN;
/* 584 */     return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
/*     */   }
/*     */   
/*     */   private void addDefaultEquipmentAndRecipies(int par1) {
/* 588 */     if (this.buyingList != null) {
/* 589 */       this.field_82191_bN = (MathHelper.func_76129_c(this.buyingList.size()) * 0.2F);
/*     */     } else {
/* 591 */       this.field_82191_bN = 0.0F;
/*     */     }
/*     */     
/* 594 */     MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
/*     */     
/*     */ 
/* 597 */     boolean shuffle = true;
/* 598 */     switch (getProfession()) {
/*     */     case 0: 
/* 600 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151015_O, this.field_70146_Z, adjustProbability(0.9F));
/* 601 */       addItemToSwapForAnEmerald(merchantrecipelist, Item.func_150898_a(Blocks.field_150325_L), this.field_70146_Z, adjustProbability(0.5F));
/*     */       
/* 603 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151076_bf, this.field_70146_Z, adjustProbability(0.5F));
/* 604 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151101_aQ, this.field_70146_Z, adjustProbability(0.4F));
/* 605 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151025_P, this.field_70146_Z, adjustProbability(0.9F));
/* 606 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151127_ba, this.field_70146_Z, adjustProbability(0.3F));
/* 607 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151034_e, this.field_70146_Z, adjustProbability(0.3F));
/* 608 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151106_aX, this.field_70146_Z, adjustProbability(0.3F));
/* 609 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151097_aZ, this.field_70146_Z, adjustProbability(0.3F));
/* 610 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151033_d, this.field_70146_Z, adjustProbability(0.3F));
/* 611 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151077_bg, this.field_70146_Z, adjustProbability(0.3F));
/* 612 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151032_g, this.field_70146_Z, adjustProbability(0.5F));
/*     */       
/* 614 */       if (this.field_70146_Z.nextFloat() < adjustProbability(0.5F)) {
/* 615 */         merchantrecipelist.add(new MerchantRecipe(new ItemStack(Blocks.field_150351_n, 10), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151145_ak, 4 + this.field_70146_Z.nextInt(2), 0)));
/*     */       }
/*     */       
/*     */ 
/*     */       break;
/*     */     case 1: 
/*     */     case 2: 
/* 622 */       shuffle = false;
/* 623 */       if (this.buyingList == null) {
/* 624 */         merchantrecipelist.add(new MerchantRecipe(Witchery.Items.GENERIC.itemKobolditeDust.createStack(9), new ItemStack(Items.field_151074_bl, 5), Witchery.Items.GENERIC.itemKobolditeNugget.createStack()));
/*     */ 
/*     */       }
/* 627 */       else if (this.buyingList.size() == 1) {
/* 628 */         merchantrecipelist.add(new MerchantRecipe(Witchery.Items.GENERIC.itemKobolditeDust.createStack(16), new ItemStack(Items.field_151043_k), Witchery.Items.GENERIC.itemKobolditeNugget.createStack(2)));
/*     */ 
/*     */       }
/* 631 */       else if (this.buyingList.size() == 2) {
/* 632 */         merchantrecipelist.add(new MerchantRecipe(Witchery.Items.GENERIC.itemKobolditeNugget.createStack(9), new ItemStack(Items.field_151166_bC), Witchery.Items.GENERIC.itemKobolditeIngot.createStack()));
/*     */       }
/*     */       
/*     */ 
/*     */       break;
/*     */     case 3: 
/* 638 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151044_h, this.field_70146_Z, adjustProbability(0.7F));
/* 639 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151042_j, this.field_70146_Z, adjustProbability(0.5F));
/* 640 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151043_k, this.field_70146_Z, adjustProbability(0.5F));
/* 641 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151045_i, this.field_70146_Z, adjustProbability(0.5F));
/* 642 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151040_l, this.field_70146_Z, adjustProbability(0.5F));
/* 643 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151048_u, this.field_70146_Z, adjustProbability(0.5F));
/* 644 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151036_c, this.field_70146_Z, adjustProbability(0.3F));
/* 645 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151056_x, this.field_70146_Z, adjustProbability(0.3F));
/* 646 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151035_b, this.field_70146_Z, adjustProbability(0.5F));
/* 647 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151046_w, this.field_70146_Z, adjustProbability(0.5F));
/* 648 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151037_a, this.field_70146_Z, adjustProbability(0.2F));
/* 649 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151047_v, this.field_70146_Z, adjustProbability(0.2F));
/* 650 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151019_K, this.field_70146_Z, adjustProbability(0.2F));
/* 651 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151012_L, this.field_70146_Z, adjustProbability(0.2F));
/* 652 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151167_ab, this.field_70146_Z, adjustProbability(0.2F));
/* 653 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151175_af, this.field_70146_Z, adjustProbability(0.2F));
/* 654 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151028_Y, this.field_70146_Z, adjustProbability(0.2F));
/* 655 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151161_ac, this.field_70146_Z, adjustProbability(0.2F));
/* 656 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151030_Z, this.field_70146_Z, adjustProbability(0.2F));
/* 657 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151163_ad, this.field_70146_Z, adjustProbability(0.2F));
/* 658 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151165_aa, this.field_70146_Z, adjustProbability(0.2F));
/* 659 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151173_ae, this.field_70146_Z, adjustProbability(0.2F));
/* 660 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151029_X, this.field_70146_Z, adjustProbability(0.1F));
/* 661 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151020_U, this.field_70146_Z, adjustProbability(0.1F));
/* 662 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151023_V, this.field_70146_Z, adjustProbability(0.1F));
/* 663 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151022_W, this.field_70146_Z, adjustProbability(0.1F));
/* 664 */       break;
/*     */     case 4: 
/* 666 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151044_h, this.field_70146_Z, adjustProbability(0.7F));
/* 667 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151147_al, this.field_70146_Z, adjustProbability(0.5F));
/* 668 */       addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151082_bd, this.field_70146_Z, adjustProbability(0.5F));
/* 669 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151141_av, this.field_70146_Z, adjustProbability(0.1F));
/* 670 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151027_R, this.field_70146_Z, adjustProbability(0.3F));
/* 671 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151021_T, this.field_70146_Z, adjustProbability(0.3F));
/* 672 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151024_Q, this.field_70146_Z, adjustProbability(0.3F));
/* 673 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151026_S, this.field_70146_Z, adjustProbability(0.3F));
/* 674 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151157_am, this.field_70146_Z, adjustProbability(0.3F));
/* 675 */       addItemToBuyOrSell(merchantrecipelist, Items.field_151083_be, this.field_70146_Z, adjustProbability(0.3F));
/*     */     }
/*     */     
/* 678 */     if (merchantrecipelist.isEmpty()) {
/* 679 */       merchantrecipelist.add(new MerchantRecipe(getItemStackToSwapForAnEmerald(Items.field_151043_k, this.field_70146_Z), Items.field_151166_bC));
/*     */     }
/*     */     
/*     */ 
/* 683 */     if (shuffle) {
/* 684 */       Collections.shuffle(merchantrecipelist);
/*     */     }
/*     */     
/* 687 */     if (this.buyingList == null) {
/* 688 */       this.buyingList = new MerchantRecipeList();
/*     */     }
/*     */     
/* 691 */     for (int l = 0; (l < par1) && (l < merchantrecipelist.size()); l++) {
/* 692 */       this.buyingList.func_77205_a((MerchantRecipe)merchantrecipelist.get(l));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70930_a(MerchantRecipeList par1MerchantRecipeList) {}
/*     */   
/*     */ 
/*     */   public static void addItemToSwapForAnEmerald(MerchantRecipeList buyList, Item item, Random rand, float probability)
/*     */   {
/* 703 */     if (rand.nextFloat() < probability) {
/* 704 */       if (rand.nextInt(3) == 0) {
/* 705 */         buyList.add(new MerchantRecipe(getItemStackToSwapForAnEmerald(item, rand), Witchery.Items.GENERIC.itemKobolditeDust.createStack()));
/*     */       }
/*     */       else {
/* 708 */         buyList.add(new MerchantRecipe(getItemStackToSwapForAnEmerald(item, rand), Items.field_151166_bC));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static ItemStack getItemStackToSwapForAnEmerald(Item item, Random rand) {
/* 714 */     return new ItemStack(item, getQuantityToSwapForAnEmerald(item, rand), 0);
/*     */   }
/*     */   
/*     */   private static int getQuantityToSwapForAnEmerald(Item item, Random rand) {
/* 718 */     Tuple tuple = (Tuple)villagersSellingList.get(item);
/* 719 */     return ((Integer)tuple.func_76341_a()).intValue() >= ((Integer)tuple.func_76340_b()).intValue() ? ((Integer)tuple.func_76341_a()).intValue() : tuple == null ? 1 : ((Integer)tuple.func_76341_a()).intValue() + rand.nextInt(((Integer)tuple.func_76340_b()).intValue() - ((Integer)tuple.func_76341_a()).intValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addItemToBuyOrSell(MerchantRecipeList list, Item item, Random rand, float probability)
/*     */   {
/* 727 */     if (rand.nextFloat() < probability) {
/* 728 */       int i = quantityToBuyOrSell(item, rand);
/*     */       ItemStack itemstack1;
/*     */       ItemStack itemstack;
/*     */       ItemStack itemstack1;
/* 732 */       if (i < 0)
/*     */       {
/* 734 */         ItemStack itemstack = Witchery.Items.GENERIC.itemKobolditeNugget.createStack(1);
/* 735 */         itemstack1 = new ItemStack(item, -i, 0);
/*     */       }
/*     */       else {
/* 738 */         itemstack = Witchery.Items.GENERIC.itemKobolditeNugget.createStack(i);
/* 739 */         itemstack1 = new ItemStack(item, 1, 0);
/*     */       }
/*     */       
/* 742 */       list.add(new MerchantRecipe(itemstack, itemstack1));
/*     */     }
/*     */   }
/*     */   
/*     */   private static int quantityToBuyOrSell(Item item, Random rand) {
/* 747 */     Tuple tuple = (Tuple)blacksmithSellingList.get(item);
/* 748 */     return ((Integer)tuple.func_76341_a()).intValue() >= ((Integer)tuple.func_76340_b()).intValue() ? ((Integer)tuple.func_76341_a()).intValue() : tuple == null ? 1 : ((Integer)tuple.func_76341_a()).intValue() + rand.nextInt(((Integer)tuple.func_76340_b()).intValue() - ((Integer)tuple.func_76341_a()).intValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 758 */     if (par1 == 12) {
/* 759 */       generateRandomParticles("heart");
/* 760 */     } else if (par1 == 13) {
/* 761 */       generateRandomParticles("angryVillager");
/* 762 */     } else if (par1 == 14) {
/* 763 */       generateRandomParticles("happyVillager");
/*     */     } else {
/* 765 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 771 */     par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
/* 772 */     int trade = this.field_70146_Z.nextInt(5);
/* 773 */     setProfession(trade);
/* 774 */     return par1EntityLivingData;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void generateRandomParticles(String par1Str) {
/* 779 */     for (int i = 0; i < 5; i++) {
/* 780 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 781 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 782 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 783 */       this.field_70170_p.func_72869_a(par1Str, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 1.0D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLookingForHome()
/*     */   {
/* 790 */     this.isLookingForHome = true;
/*     */   }
/*     */   
/*     */   public EntityGoblin createChild(EntityAgeable par1EntityAgeable)
/*     */   {
/* 795 */     EntityGoblin entityvillager = new EntityGoblin(this.field_70170_p);
/* 796 */     entityvillager.func_110161_a((IEntityLivingData)null);
/* 797 */     return entityvillager;
/*     */   }
/*     */   
/*     */   static {
/* 801 */     villagersSellingList.put(Items.field_151044_h, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
/* 802 */     villagersSellingList.put(Items.field_151042_j, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 803 */     villagersSellingList.put(Items.field_151043_k, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 804 */     villagersSellingList.put(Items.field_151045_i, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 805 */     villagersSellingList.put(Items.field_151121_aF, new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
/* 806 */     villagersSellingList.put(Items.field_151122_aG, new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
/* 807 */     villagersSellingList.put(Items.field_151164_bB, new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
/* 808 */     villagersSellingList.put(Items.field_151079_bi, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 809 */     villagersSellingList.put(Items.field_151061_bv, new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
/* 810 */     villagersSellingList.put(Items.field_151147_al, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
/* 811 */     villagersSellingList.put(Items.field_151082_bd, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
/* 812 */     villagersSellingList.put(Items.field_151076_bf, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
/* 813 */     villagersSellingList.put(Items.field_151101_aQ, new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
/* 814 */     villagersSellingList.put(Items.field_151014_N, new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
/* 815 */     villagersSellingList.put(Items.field_151081_bc, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
/* 816 */     villagersSellingList.put(Items.field_151080_bb, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
/* 817 */     villagersSellingList.put(Items.field_151015_O, new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
/* 818 */     villagersSellingList.put(Item.func_150898_a(Blocks.field_150325_L), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
/*     */     
/* 820 */     villagersSellingList.put(Items.field_151078_bh, new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
/*     */     
/* 822 */     blacksmithSellingList.put(Items.field_151033_d, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 823 */     blacksmithSellingList.put(Items.field_151097_aZ, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 824 */     blacksmithSellingList.put(Items.field_151040_l, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
/* 825 */     blacksmithSellingList.put(Items.field_151048_u, new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
/* 826 */     blacksmithSellingList.put(Items.field_151036_c, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
/* 827 */     blacksmithSellingList.put(Items.field_151056_x, new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
/* 828 */     blacksmithSellingList.put(Items.field_151035_b, new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
/* 829 */     blacksmithSellingList.put(Items.field_151046_w, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 830 */     blacksmithSellingList.put(Items.field_151037_a, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 831 */     blacksmithSellingList.put(Items.field_151047_v, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 832 */     blacksmithSellingList.put(Items.field_151019_K, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 833 */     blacksmithSellingList.put(Items.field_151012_L, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 834 */     blacksmithSellingList.put(Items.field_151167_ab, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 835 */     blacksmithSellingList.put(Items.field_151175_af, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 836 */     blacksmithSellingList.put(Items.field_151028_Y, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 837 */     blacksmithSellingList.put(Items.field_151161_ac, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 838 */     blacksmithSellingList.put(Items.field_151030_Z, new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
/* 839 */     blacksmithSellingList.put(Items.field_151163_ad, new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
/* 840 */     blacksmithSellingList.put(Items.field_151165_aa, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 841 */     blacksmithSellingList.put(Items.field_151173_ae, new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
/* 842 */     blacksmithSellingList.put(Items.field_151029_X, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/* 843 */     blacksmithSellingList.put(Items.field_151020_U, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/* 844 */     blacksmithSellingList.put(Items.field_151023_V, new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
/* 845 */     blacksmithSellingList.put(Items.field_151022_W, new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
/* 846 */     blacksmithSellingList.put(Items.field_151025_P, new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
/* 847 */     blacksmithSellingList.put(Items.field_151127_ba, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
/* 848 */     blacksmithSellingList.put(Items.field_151034_e, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
/* 849 */     blacksmithSellingList.put(Items.field_151106_aX, new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
/* 850 */     blacksmithSellingList.put(Item.func_150898_a(Blocks.field_150359_w), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
/*     */     
/* 852 */     blacksmithSellingList.put(Item.func_150898_a(Blocks.field_150342_X), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/*     */     
/* 854 */     blacksmithSellingList.put(Items.field_151027_R, new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
/* 855 */     blacksmithSellingList.put(Items.field_151021_T, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 856 */     blacksmithSellingList.put(Items.field_151024_Q, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 857 */     blacksmithSellingList.put(Items.field_151026_S, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 858 */     blacksmithSellingList.put(Items.field_151141_av, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
/* 859 */     blacksmithSellingList.put(Items.field_151062_by, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
/* 860 */     blacksmithSellingList.put(Items.field_151137_ax, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
/* 861 */     blacksmithSellingList.put(Items.field_151111_aL, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 862 */     blacksmithSellingList.put(Items.field_151113_aN, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 863 */     blacksmithSellingList.put(Item.func_150898_a(Blocks.field_150426_aN), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
/*     */     
/* 865 */     blacksmithSellingList.put(Items.field_151157_am, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
/* 866 */     blacksmithSellingList.put(Items.field_151083_be, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
/* 867 */     blacksmithSellingList.put(Items.field_151077_bg, new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
/* 868 */     blacksmithSellingList.put(Items.field_151061_bv, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
/* 869 */     blacksmithSellingList.put(Items.field_151032_g, new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityGoblin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */