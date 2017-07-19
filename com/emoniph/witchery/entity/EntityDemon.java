/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import com.emoniph.witchery.entity.ai.EntityAILookAtDemonicBarginPlayer;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityGolem;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.village.MerchantRecipe;
/*     */ import net.minecraft.village.MerchantRecipeList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityDemon extends EntityGolem implements IRangedAttackMob, net.minecraft.entity.IMerchant
/*     */ {
/*     */   private int attackTimer;
/*     */   private EntityPlayer buyingPlayer;
/*     */   private MerchantRecipeList buyingList;
/*     */   
/*     */   public EntityDemon(World par1World)
/*     */   {
/*  57 */     super(par1World);
/*     */     
/*  59 */     func_70105_a(1.0F, 2.9F);
/*  60 */     this.field_70178_ae = true;
/*  61 */     func_70661_as().func_75491_a(true);
/*     */     
/*  63 */     this.field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAIAttackCloseTargetOnCollide(this, 1.0D, true, 3.0D));
/*  64 */     this.field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
/*  65 */     this.field_70714_bg.func_75776_a(3, new com.emoniph.witchery.entity.ai.EntityAIDemonicBarginPlayer(this));
/*  66 */     this.field_70714_bg.func_75776_a(4, new EntityAILookAtDemonicBarginPlayer(this));
/*  67 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
/*  68 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIMoveThroughVillage(this, 0.6D, true));
/*  69 */     this.field_70714_bg.func_75776_a(7, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  70 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAIWander(this, 0.6D));
/*  71 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  72 */     this.field_70714_bg.func_75776_a(10, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  73 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*     */     
/*     */ 
/*  76 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  77 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
/*  78 */     this.field_70728_aV = 10;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  83 */     super.func_70088_a();
/*  84 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*  85 */     this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  90 */     if (func_94056_bM()) {
/*  91 */       return func_94057_bL();
/*     */     }
/*  93 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.demon.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  99 */     if (this.field_71093_bK != Config.instance().dimensionDreamID) {
/* 100 */       ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
/* 101 */       boolean flag = (itemstack != null) && ((itemstack.func_77973_b() == Items.field_151063_bx) || (itemstack.func_77973_b() == Items.field_151057_cb));
/*     */       
/* 103 */       if ((!flag) && (func_70089_S()) && (!isTrading()) && (!func_70631_g_())) {
/* 104 */         if (!this.field_70170_p.field_72995_K) {
/* 105 */           func_70932_a_(par1EntityPlayer);
/* 106 */           par1EntityPlayer.func_71030_a(this, func_70005_c_());
/*     */         }
/*     */         
/* 109 */         return true;
/*     */       }
/* 111 */       return super.func_70085_c(par1EntityPlayer);
/*     */     }
/*     */     
/* 114 */     return super.func_70085_c(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 125 */     super.func_70629_bd();
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 130 */     return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0F));
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 135 */     super.func_110147_ax();
/* 136 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/* 137 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 142 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 151 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 156 */     super.func_70636_d();
/*     */     
/* 158 */     if (this.attackTimer > 0) {
/* 159 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/* 162 */     if ((this.field_71093_bK == Config.instance().dimensionDreamID) && ((this.field_70170_p.field_73011_w instanceof WorldProviderDreamWorld)) && (!((WorldProviderDreamWorld)this.field_70170_p.field_73011_w).isDemonicNightmare()))
/*     */     {
/* 164 */       func_70106_y();
/*     */     }
/*     */     
/* 167 */     if (this.tryEscape == 0) {
/* 168 */       this.tryEscape = -1;
/* 169 */       this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0F, true);
/* 170 */     } else if (this.tryEscape > 0) {
/* 171 */       this.tryEscape -= 1;
/*     */     }
/*     */     
/* 174 */     if ((this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D) && (this.field_70146_Z.nextInt(5) == 0)) {
/* 175 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 176 */       int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - this.field_70129_M);
/* 177 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/* 178 */       Block l = this.field_70170_p.func_147439_a(i, j, k);
/*     */       
/* 180 */       if (l != net.minecraft.init.Blocks.field_150350_a) {
/* 181 */         this.field_70170_p.func_72869_a("tilecrack_" + l + "_" + this.field_70170_p.func_72805_g(i, j, k), this.field_70165_t + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, this.field_70121_D.field_72338_b + 0.1D, this.field_70161_v + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70686_a(Class par1Class)
/*     */   {
/* 190 */     return super.func_70686_a(par1Class);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 195 */     super.func_70014_b(par1NBTTagCompound);
/* 196 */     par1NBTTagCompound.func_74757_a("PlayerCreated", isPlayerCreated());
/*     */     
/* 198 */     if (this.buyingList != null)
/*     */     {
/* 200 */       par1NBTTagCompound.func_74782_a("Bargains", this.buyingList.func_77202_a());
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 206 */     super.func_70037_a(par1NBTTagCompound);
/* 207 */     setPlayerCreated(par1NBTTagCompound.func_74767_n("PlayerCreated"));
/*     */     
/* 209 */     if (par1NBTTagCompound.func_74764_b("Bargains"))
/*     */     {
/* 211 */       NBTTagCompound nbttagcompound1 = par1NBTTagCompound.func_74775_l("Bargains");
/* 212 */       this.buyingList = new MerchantRecipeList(nbttagcompound1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 218 */     this.attackTimer = 10;
/* 219 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 220 */     boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), 7 + this.field_70146_Z.nextInt(15));
/*     */     
/* 222 */     if (flag) {
/* 223 */       par1Entity.field_70181_x += 0.4000000059604645D;
/*     */     }
/*     */     
/* 226 */     func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/* 227 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 233 */     if (par1 == 4) {
/* 234 */       this.attackTimer = 10;
/* 235 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     } else {
/* 237 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 243 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 248 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 253 */     return "mob.blaze.breathe";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 258 */     return "mob.wither.hurt";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 263 */     return "mob.wither.death";
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 268 */     func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 273 */     if (par1) {
/* 274 */       int j = this.field_70146_Z.nextInt(2 + par2);
/*     */       
/* 276 */       for (int k = 0; k < j; k++) {
/* 277 */         func_145779_a(Items.field_151064_bs, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 284 */     return Items.field_151064_bs;
/*     */   }
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 288 */     return (this.field_70180_af.func_75683_a(16) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setPlayerCreated(boolean par1) {
/* 292 */     func_110163_bv();
/* 293 */     byte b0 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 295 */     if (par1) {
/* 296 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 298 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 304 */     super.func_70645_a(par1DamageSource);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 309 */     return true;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase targetEntity, float par2)
/*     */   {
/* 314 */     if ((targetEntity.func_70694_bm() == null) || (targetEntity.func_70694_bm().func_77973_b() != Witchery.Items.DEVILS_TONGUE_CHARM) || (this.field_70170_p.field_73012_v.nextDouble() < 0.05D)) {
/* 315 */       double d0 = targetEntity.field_70165_t - this.field_70165_t;
/* 316 */       double d1 = targetEntity.field_70121_D.field_72338_b + targetEntity.field_70131_O / 2.0F - (this.field_70163_u + this.field_70131_O / 2.0F);
/* 317 */       double d2 = targetEntity.field_70161_v - this.field_70161_v;
/*     */       
/* 319 */       float f1 = MathHelper.func_76129_c(par2) * 0.5F;
/*     */       
/* 321 */       EntityLargeFireball fireballEntity = new EntityLargeFireball(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1);
/*     */       
/* 323 */       double d8 = 1.0D;
/* 324 */       Vec3 vec3 = func_70676_i(1.0F);
/* 325 */       fireballEntity.field_70165_t = (this.field_70165_t + vec3.field_72450_a * d8);
/* 326 */       fireballEntity.field_70163_u = (this.field_70163_u + this.field_70131_O / 2.0F + 0.5D);
/* 327 */       fireballEntity.field_70161_v = (this.field_70161_v + vec3.field_72449_c * d8);
/*     */       
/* 329 */       if (!this.field_70170_p.field_72995_K) {
/* 330 */         this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/* 331 */         this.field_70170_p.func_72838_d(fireballEntity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 338 */     func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, Integer.MAX_VALUE, 4));
/* 339 */     return super.func_110161_a(par1EntityLivingData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70932_a_(EntityPlayer par1EntityPlayer)
/*     */   {
/* 352 */     this.buyingPlayer = par1EntityPlayer;
/*     */   }
/*     */   
/*     */   public EntityPlayer func_70931_l_()
/*     */   {
/* 357 */     return this.buyingPlayer;
/*     */   }
/*     */   
/*     */   public boolean isTrading() {
/* 361 */     return this.buyingPlayer != null;
/*     */   }
/*     */   
/*     */   public void func_70933_a(MerchantRecipe par1MerchantRecipe)
/*     */   {
/* 366 */     par1MerchantRecipe.func_77399_f();
/*     */     
/* 368 */     Item itemToBuy = par1MerchantRecipe.func_77394_a().func_77973_b();
/* 369 */     if ((!this.field_70170_p.field_72995_K) && ((itemToBuy == Items.field_151064_bs) || (itemToBuy == Items.field_151072_bj))) {
/* 370 */       func_85030_a("mob.wither.shoot", func_70599_aP(), func_70647_i());
/* 371 */       this.tryEscape = 50;
/*     */     } else {
/* 373 */       func_85030_a("random.breath", func_70599_aP(), func_70647_i());
/*     */     }
/*     */     
/* 376 */     if ((func_70931_l_() != null) && (func_70931_l_().func_70694_bm() != null) && (func_70931_l_().func_70694_bm().func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM)) {
/* 377 */       func_70931_l_().func_70694_bm().func_77972_a(5, func_70931_l_());
/* 378 */       if (func_70931_l_().func_70694_bm().field_77994_a <= 0) {
/* 379 */         func_70931_l_().func_71028_bD();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 384 */   private int tryEscape = -1;
/*     */   
/*     */   public void func_110297_a_(ItemStack par1ItemStack)
/*     */   {
/* 388 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70757_a > -func_70627_aG() + 20)) {
/* 389 */       this.field_70757_a = (-func_70627_aG());
/*     */       
/* 391 */       if (par1ItemStack != null) {
/* 392 */         func_85030_a("random.breath", func_70599_aP(), func_70647_i());
/*     */       } else {
/* 394 */         func_85030_a("mob.wither.idle", func_70599_aP(), func_70647_i());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70785_a(Entity entity, float par2)
/*     */   {
/* 401 */     if ((this.field_70724_aR <= 0) && (par2 < 2.0F) && (entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e))
/*     */     {
/* 403 */       this.field_70724_aR = 20;
/* 404 */       func_70652_k(entity);
/*     */     }
/*     */     
/* 407 */     super.func_70785_a(entity, par2);
/*     */   }
/*     */   
/*     */   public MerchantRecipeList func_70934_b(EntityPlayer par1EntityPlayer)
/*     */   {
/* 412 */     if (this.buyingList == null) {
/* 413 */       addDefaultEquipmentAndRecipies(this.field_70146_Z.nextInt(4) + 6);
/*     */     }
/*     */     
/* 416 */     if ((func_70931_l_() != null) && (func_70931_l_().func_70694_bm() != null) && (func_70931_l_().func_70694_bm().func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM)) {
/* 417 */       MerchantRecipeList list = new MerchantRecipeList();
/* 418 */       for (Object recipeObj : this.buyingList) {
/* 419 */         MerchantRecipe recipe = (MerchantRecipe)recipeObj;
/* 420 */         NBTTagCompound nbtTag = recipe.func_77395_g();
/* 421 */         MerchantRecipe recipe2 = new MerchantRecipe(nbtTag);
/* 422 */         ItemStack cost = recipe2.func_77394_a();
/* 423 */         cost.field_77994_a = Math.max(cost.field_77994_a - (cost.func_77973_b() == Items.field_151045_i ? 0 : cost.func_77973_b() == Items.field_151166_bC ? 2 : cost.func_77973_b() == Items.field_151043_k ? 5 : 1), 1);
/* 424 */         list.add(recipe2);
/*     */       }
/* 426 */       return list;
/*     */     }
/* 428 */     return this.buyingList;
/*     */   }
/*     */   
/*     */   private Item getCurrency()
/*     */   {
/* 433 */     double chance = this.field_70146_Z.nextDouble();
/*     */     
/* 435 */     if (chance < 0.2D)
/* 436 */       return Items.field_151072_bj;
/* 437 */     if (chance < 0.4D)
/* 438 */       return Items.field_151064_bs;
/* 439 */     if (chance < 0.5D)
/* 440 */       return Items.field_151045_i;
/* 441 */     if (chance < 0.75D) {
/* 442 */       return Items.field_151166_bC;
/*     */     }
/* 444 */     return Items.field_151043_k;
/*     */   }
/*     */   
/*     */   private ItemStack getPrice(int basePriceInEmeralds)
/*     */   {
/* 449 */     Item currency = getCurrency();
/* 450 */     int multiplier = currency == Items.field_151045_i ? 5 : currency == Items.field_151166_bC ? 3 : currency == Items.field_151043_k ? 1 : 4;
/* 451 */     int quantity = Math.max(1, basePriceInEmeralds / multiplier);
/*     */     
/* 453 */     return new ItemStack(currency, quantity);
/*     */   }
/*     */   
/*     */   private void addDefaultEquipmentAndRecipies(int par1)
/*     */   {
/* 458 */     MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
/*     */     
/* 460 */     int STOCK_REDUCTION = -5;
/*     */     
/* 462 */     for (int i = 0; i < par1; i++) {
/* 463 */       Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
/* 464 */       int k = MathHelper.func_76136_a(this.field_70146_Z, enchantment.func_77319_d(), enchantment.func_77325_b());
/* 465 */       ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
/* 466 */       int j = 2 + this.field_70146_Z.nextInt(5 + k * 10) + 3 * k;
/* 467 */       MerchantRecipe recipe = new MerchantRecipe(getPrice(j), itemstack);
/* 468 */       recipe.func_82783_a(-5);
/* 469 */       merchantrecipelist.add(recipe);
/*     */     }
/*     */     
/* 472 */     if (this.field_70146_Z.nextDouble() < 0.25D) {
/* 473 */       MerchantRecipe recipe = new MerchantRecipe(getPrice(this.field_70146_Z.nextInt(3) + 8), Witchery.Items.GENERIC.itemSpectralDust.createStack(this.field_70146_Z.nextInt(4) + 3));
/* 474 */       recipe.func_82783_a(-5);
/* 475 */       merchantrecipelist.add(recipe);
/*     */     }
/*     */     
/* 478 */     if (this.field_70146_Z.nextDouble() < 0.25D) {
/* 479 */       MerchantRecipe recipe = new MerchantRecipe(getPrice(this.field_70146_Z.nextInt(3) + 8), Witchery.Items.GENERIC.itemDogTongue.createStack(this.field_70146_Z.nextInt(4) + 4));
/* 480 */       recipe.func_82783_a(-5);
/* 481 */       merchantrecipelist.add(recipe);
/*     */     }
/*     */     
/* 484 */     if (this.field_70146_Z.nextDouble() < 0.15D) {
/* 485 */       MerchantRecipe recipe = new MerchantRecipe(getPrice(this.field_70146_Z.nextInt(10) + 20), Witchery.Items.GENERIC.itemRedstoneSoup.createStack(1));
/* 486 */       recipe.func_82783_a(-5);
/* 487 */       merchantrecipelist.add(recipe);
/*     */     }
/*     */     
/* 490 */     if (this.field_70146_Z.nextDouble() < 0.15D) {
/* 491 */       MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151073_bk, 2));
/* 492 */       recipe.func_82783_a(-5);
/* 493 */       merchantrecipelist.add(recipe);
/*     */     }
/*     */     
/* 496 */     if (this.field_70146_Z.nextDouble() < 0.15D) {
/* 497 */       MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151079_bi, 2));
/* 498 */       recipe.func_82783_a(-5);
/* 499 */       merchantrecipelist.add(recipe);
/*     */     }
/*     */     
/*     */ 
/* 503 */     Collections.shuffle(merchantrecipelist);
/*     */     
/* 505 */     Item currencyForHeart = getCurrency();
/* 506 */     MerchantRecipe heartRecipe = new MerchantRecipe(new ItemStack(currencyForHeart, currencyForHeart == Items.field_151043_k ? 30 : 3), Witchery.Items.GENERIC.itemDemonHeart.createStack(1));
/* 507 */     heartRecipe.func_82783_a(-5);
/* 508 */     merchantrecipelist.add(this.field_70146_Z.nextInt(3), heartRecipe);
/*     */     
/* 510 */     if (this.buyingList == null) {
/* 511 */       this.buyingList = new MerchantRecipeList();
/*     */     }
/*     */     
/* 514 */     for (int j1 = 0; (j1 < par1) && (j1 < merchantrecipelist.size()); j1++) {
/* 515 */       this.buyingList.add(merchantrecipelist.get(j1));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70930_a(MerchantRecipeList par1MerchantRecipeList) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityDemon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */