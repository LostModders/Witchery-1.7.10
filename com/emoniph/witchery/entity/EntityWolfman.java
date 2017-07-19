/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.common.Shapeshift;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.village.MerchantRecipeList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityWolfman extends EntityMob implements net.minecraft.command.IEntitySelector
/*     */ {
/*  41 */   private int formerProfession = -1;
/*     */   private int attackTimer;
/*     */   private boolean infectious;
/*     */   
/*     */   public EntityWolfman(World world) {
/*  46 */     super(world);
/*  47 */     func_70661_as().func_75498_b(true);
/*  48 */     func_70661_as().func_75491_a(true);
/*  49 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  50 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAIBreakDoor(this));
/*  51 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  52 */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  53 */     this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, EntityWitchHunter.class, 1.0D, true));
/*  54 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  55 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
/*  56 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
/*  57 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  58 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  59 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*  60 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, this));
/*     */     
/*  62 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
/*  63 */     func_70105_a(0.6F, 1.8F);
/*  64 */     this.field_70728_aV = 20;
/*     */   }
/*     */   
/*     */   public boolean func_82704_a(Entity target)
/*     */   {
/*  69 */     if ((target instanceof EntityPlayer)) {
/*  70 */       EntityPlayer player = (EntityPlayer)target;
/*  71 */       return !Shapeshift.INSTANCE.isAnimalForm(player);
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  78 */     super.func_110147_ax();
/*  79 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*  80 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/*  81 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*  82 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(80.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  87 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   boolean isSitting;
/*     */   private MerchantRecipeList buyingList;
/*     */   public boolean isSitting()
/*     */   {
/*  94 */     return this.isSitting;
/*     */   }
/*     */   
/*     */   public void setSitting(boolean p_70904_1_)
/*     */   {
/*  99 */     this.isSitting = p_70904_1_;
/*     */   }
/*     */   
/*     */ 
/*     */   private int wealth;
/*     */   
/*     */   public ItemStack itemInUse;
/*     */   public int itemInUseCount;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ResourceLocation skinOverride;
/*     */   public int func_70658_aO()
/*     */   {
/* 111 */     int i = super.func_70658_aO() + 10;
/*     */     
/* 113 */     if (i > 20) {
/* 114 */       i = 20;
/*     */     }
/*     */     
/* 117 */     return i;
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 122 */     if (func_94056_bM()) {
/* 123 */       return func_94057_bL();
/*     */     }
/* 125 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.wolfman.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70650_aV()
/*     */   {
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 136 */     if (this.attackTimer > 0) {
/* 137 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/* 140 */     if (!this.field_70170_p.field_72995_K) {
/* 141 */       if ((this.formerProfession != -1) && (this.field_70173_aa % 100 == 3) && (!CreatureUtil.isFullMoon(this.field_70170_p)) && (!func_70644_a(Witchery.Potions.WOLFSBANE)))
/*     */       {
/* 143 */         convertToVillager(this, this.formerProfession, this.infectious, this.wealth, this.buyingList);
/* 144 */       } else if ((this.field_70173_aa % 40 == 4) && 
/* 145 */         (func_70644_a(Potion.field_76436_u))) {
/* 146 */         func_82170_o(Potion.field_76436_u.field_76415_H);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 151 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 156 */     if (CreatureUtil.isSilverDamage(source)) {
/* 157 */       return super.func_70097_a(source, Math.min(damage * 1.5F, 15.0F));
/*     */     }
/* 159 */     return super.func_70097_a(source, Math.min(damage, 1.0F));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity targetEntity)
/*     */   {
/* 165 */     this.attackTimer = 10;
/* 166 */     this.field_70170_p.func_72960_a(this, (byte)4);
/*     */     
/* 168 */     boolean flag = super.func_70652_k(targetEntity);
/*     */     
/* 170 */     if (flag) {}
/*     */     
/*     */ 
/*     */ 
/* 174 */     return flag;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 180 */     if (par1 == 4) {
/* 181 */       this.attackTimer = 10;
/*     */     } else {
/* 183 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 189 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 194 */     return super.func_70627_aG() * 4;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 199 */     return this.field_70170_p.field_73012_v.nextInt(20) == 0 ? "witchery:mob.wolfman.howl" : "witchery:mob.wolfman.say";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 204 */     return "witchery:mob.wolfman.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 209 */     return "witchery:mob.wolfman.death";
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int fortune) {
/* 213 */     super.func_70628_a(p_70628_1_, fortune);
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 218 */     return Items.field_151103_aS;
/*     */   }
/*     */   
/*     */   protected void func_70600_l(int p_70600_1_)
/*     */   {
/* 223 */     switch (this.field_70146_Z.nextInt(3)) {
/*     */     case 0: 
/* 225 */       func_70099_a(Witchery.Items.GENERIC.itemSilverDust.createStack(this.field_70170_p.field_73012_v.nextInt(3) + 1), 0.0F);
/* 226 */       break;
/*     */     case 1: 
/* 228 */       func_145779_a(Items.field_151103_aS, 1);
/* 229 */       break;
/*     */     case 2: 
/* 231 */       func_145779_a(Items.field_151116_aA, 1);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFormerProfession(int profession, int wealth, MerchantRecipeList buyingList)
/*     */   {
/* 240 */     this.formerProfession = profession;
/* 241 */     this.buyingList = buyingList;
/* 242 */     this.wealth = wealth;
/*     */   }
/*     */   
/*     */   public int getFormerProfession() {
/* 246 */     return this.formerProfession;
/*     */   }
/*     */   
/*     */   public int getWealth() {
/* 250 */     return this.wealth;
/*     */   }
/*     */   
/*     */   public MerchantRecipeList getBuyingList() {
/* 254 */     return this.buyingList;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 259 */     super.func_70014_b(nbtRoot);
/* 260 */     nbtRoot.func_74768_a("FormerProfession", this.formerProfession);
/* 261 */     nbtRoot.func_74757_a("Infectious", this.infectious);
/*     */     
/* 263 */     nbtRoot.func_74768_a("Riches", this.wealth);
/*     */     
/* 265 */     if (this.buyingList != null) {
/* 266 */       nbtRoot.func_74782_a("Offers", this.buyingList.func_77202_a());
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 272 */     super.func_70037_a(nbtRoot);
/* 273 */     this.formerProfession = nbtRoot.func_74762_e("FormerProfession");
/* 274 */     this.infectious = nbtRoot.func_74767_n("Infectious");
/*     */     
/* 276 */     this.wealth = nbtRoot.func_74762_e("Riches");
/*     */     
/* 278 */     if (nbtRoot.func_150297_b("Offers", 10)) {
/* 279 */       NBTTagCompound nbttagcompound1 = nbtRoot.func_74775_l("Offers");
/* 280 */       this.buyingList = new MerchantRecipeList(nbttagcompound1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setInfectious() {
/* 285 */     this.infectious = true;
/*     */   }
/*     */   
/*     */   public boolean isInfectious() {
/* 289 */     return this.infectious;
/*     */   }
/*     */   
/*     */   public void func_70074_a(EntityLivingBase targetEntity)
/*     */   {
/* 294 */     super.func_70074_a(targetEntity);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 299 */     return false;
/*     */   }
/*     */   
/*     */   public static void convertToVillager(EntityLiving target, int profession, boolean infectious, int wealth, MerchantRecipeList buyingList) {
/* 303 */     if ((target != null) && (!target.field_70170_p.field_72995_K)) {
/* 304 */       EntityVillager entity = new EntityVillagerWere(target.field_70170_p, profession, infectious);
/* 305 */       entity.func_82149_j(target);
/* 306 */       entity.func_82187_q();
/* 307 */       entity.func_110163_bv();
/* 308 */       target.field_70170_p.func_72900_e(target);
/* 309 */       target.field_70170_p.func_72838_d(entity);
/* 310 */       target.field_70170_p.func_72889_a(null, 1017, (int)target.field_70165_t, (int)target.field_70163_u, (int)target.field_70161_v, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void convertToCuredVillager(EntityLiving target, int profession, int wealth, MerchantRecipeList buyingList)
/*     */   {
/* 316 */     if ((target != null) && (!target.field_70170_p.field_72995_K)) {
/* 317 */       EntityVillager entity = new EntityVillager(target.field_70170_p, profession);
/* 318 */       entity.func_82149_j(target);
/* 319 */       entity.func_82187_q();
/* 320 */       entity.func_110163_bv();
/* 321 */       target.field_70170_p.func_72900_e(target);
/* 322 */       target.field_70170_p.func_72838_d(entity);
/* 323 */       target.field_70170_p.func_72889_a(null, 1017, (int)target.field_70165_t, (int)target.field_70163_u, (int)target.field_70161_v, 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setItemInUse(ItemStack stack, int itemInUseCount)
/*     */   {
/* 332 */     this.itemInUse = stack;
/* 333 */     this.itemInUseCount = itemInUseCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void setSkinResource(ResourceLocation skinOverride)
/*     */   {
/* 342 */     this.skinOverride = skinOverride;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ResourceLocation getSkinResource() {
/* 347 */     return this.skinOverride;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityWolfman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */