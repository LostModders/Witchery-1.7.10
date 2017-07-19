/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint.CarryRequirement;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFollowOwner;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyingTempt;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TameableUtil;
/*     */ import com.emoniph.witchery.util.Waypoint;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class EntityWingedMonkey extends EntityFlyingTameable
/*     */ {
/*     */   private int attackTimer;
/*     */   public EntityAIFlyingTempt aiTempt;
/*  47 */   private int timeToLive = -1;
/*     */   
/*  49 */   private static final ItemStack[] TEMPTATIONS = { new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151082_bd) };
/*     */   
/*     */   public EntityWingedMonkey(World world)
/*     */   {
/*  53 */     super(world);
/*  54 */     func_70105_a(0.6F, 0.8F);
/*  55 */     func_70661_as().func_75495_e(true);
/*  56 */     this.field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
/*  57 */     this.field_70714_bg.func_75776_a(2, new EntityAIFlyerFlyToWaypoint(this, EntityAIFlyerFlyToWaypoint.CarryRequirement.ENTITY_LIVING));
/*  58 */     this.field_70714_bg.func_75776_a(3, new com.emoniph.witchery.entity.ai.EntityAIFlyerAttackOnCollide(this, 1.0D, true));
/*  59 */     this.field_70714_bg.func_75776_a(4, this.aiTempt = new EntityAIFlyingTempt(this, 0.6D, TEMPTATIONS, true));
/*  60 */     this.field_70714_bg.func_75776_a(5, new EntityAIFlyerFollowOwner(this, 1.0D, 14.0F, 5.0F));
/*  61 */     this.field_70714_bg.func_75776_a(8, new com.emoniph.witchery.entity.ai.EntityAIFlyerMate(this, 0.8D));
/*  62 */     this.field_70714_bg.func_75776_a(9, new EntityAIFlyerLand(this, 0.8D, true));
/*  63 */     this.field_70714_bg.func_75776_a(10, new com.emoniph.witchery.entity.ai.EntityAIFlyerWander(this, 0.8D, 10.0D));
/*  64 */     this.field_70714_bg.func_75776_a(11, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 10.0F, 0.2F));
/*  65 */     this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
/*  66 */     this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
/*  67 */     this.field_70715_bh.func_75776_a(3, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/*  72 */     return super.func_70658_aO() + 5;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/*  77 */     super.func_70014_b(nbtRoot);
/*     */     
/*  79 */     nbtRoot.func_74774_a("FeatherColor", (byte)getFeatherColor());
/*  80 */     nbtRoot.func_74768_a("SuicideIn", this.timeToLive);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/*  86 */     super.func_70037_a(nbtRoot);
/*     */     
/*  88 */     if (nbtRoot.func_74764_b("FeatherColor")) {
/*  89 */       setFeatherColor(nbtRoot.func_74771_c("FeatherColor"));
/*     */     }
/*     */     
/*  92 */     if (nbtRoot.func_74764_b("SuicideIn")) {
/*  93 */       this.timeToLive = nbtRoot.func_74762_e("SuicideIn");
/*     */     } else {
/*  95 */       this.timeToLive = -1;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 101 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 106 */     super.func_70088_a();
/* 107 */     this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
/* 108 */     this.field_70180_af.func_75682_a(21, Byte.valueOf((byte)(this.field_70170_p.field_73012_v.nextInt(100) == 0 ? 0 : this.field_70170_p.field_73012_v.nextInt(15) + 1)));
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 114 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 119 */     super.func_110147_ax();
/* 120 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*     */     
/* 122 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
/*     */     
/* 124 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 125 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 130 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70629_bd()
/*     */   {
/* 140 */     func_70661_as().func_75499_g();
/* 141 */     super.func_70629_bd();
/* 142 */     if ((this.field_70170_p != null) && (!this.field_70128_L) && (!this.field_70170_p.field_72995_K) && (this.timeToLive != -1) && ((--this.timeToLive == 0) || (func_70638_az() == null) || (func_70638_az().field_70128_L)))
/*     */     {
/* 144 */       ParticleEffect.EXPLODE.send(SoundEffect.NONE, this, 1.0D, 1.0D, 16);
/* 145 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 151 */     super.func_70636_d();
/*     */     
/* 153 */     if (this.attackTimer > 0) {
/* 154 */       this.attackTimer -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 161 */     if (par1 == 4) {
/* 162 */       this.attackTimer = 10;
/*     */     } else {
/* 164 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 170 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 175 */     if (!isTemp()) {
/* 176 */       int var3 = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + par2);
/*     */       
/* 178 */       for (int var4 = 0; var4 < var3; var4++) {
/* 179 */         func_70099_a(new ItemStack(Items.field_151008_G), 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 186 */     return super.func_70627_aG() * 2;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 191 */     return "witchery:mob.monkey.say";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 196 */     return "witchery:mob.monkey.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 201 */     return "witchery:mob.monkey.death";
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 206 */     this.attackTimer = 10;
/* 207 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 208 */     return par1Entity.func_70097_a(DamageSource.func_76358_a(this), 2.0F);
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 213 */     if (func_85032_ar()) {
/* 214 */       return false;
/*     */     }
/* 216 */     func_70904_g(false);
/* 217 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public double func_70042_X()
/*     */   {
/* 223 */     return this.field_70153_n != null ? -this.field_70153_n.field_70131_O * 0.6D : 0.0D;
/*     */   }
/*     */   
/*     */   public boolean shouldRiderSit()
/*     */   {
/* 228 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 233 */     if (isTemp()) {
/* 234 */       return true;
/*     */     }
/*     */     
/* 237 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*     */     
/* 239 */     if (func_70909_n()) {
/* 240 */       if ((TameableUtil.isOwner(this, player)) && (!func_70877_b(itemstack)))
/*     */       {
/* 242 */         if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151100_aR)) {
/* 243 */           if (!this.field_70170_p.field_72995_K) {
/* 244 */             int i = net.minecraft.block.BlockColored.func_150032_b(itemstack.func_77960_j());
/* 245 */             setFeatherColor(i);
/*     */             
/* 247 */             if (!player.field_71075_bZ.field_75098_d) {
/* 248 */               itemstack.field_77994_a -= 1;
/*     */             }
/*     */             
/* 251 */             if (itemstack.field_77994_a <= 0) {
/* 252 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*     */             }
/*     */           }
/*     */         } else {
/* 256 */           if ((itemstack != null) && ((itemstack.func_77973_b() == Items.field_151057_cb) || (itemstack.func_77973_b() == Witchery.Items.POLYNESIA_CHARM) || (itemstack.func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM)))
/*     */           {
/*     */ 
/* 259 */             return false; }
/* 260 */           if ((Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(itemstack)) || (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(itemstack)))
/*     */           {
/* 262 */             this.waypoint = itemstack.func_77946_l();
/* 263 */             this.homeX = 0.0D;
/* 264 */             this.homeY = 0.0D;
/* 265 */             this.homeZ = 0.0D;
/* 266 */             Waypoint wp = getWaypoint();
/* 267 */             this.homeX = wp.X;
/* 268 */             this.homeY = wp.Y;
/* 269 */             this.homeZ = wp.Z;
/* 270 */             Witchery.Items.GENERIC.bindToLocation(this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, this.field_70170_p.field_73011_w.field_76574_g, this.field_70170_p.field_73011_w.func_80007_l(), this.waypoint);
/*     */             
/*     */ 
/* 273 */             if ((!this.field_70170_p.field_72995_K) && (func_70906_o())) {
/* 274 */               func_70904_g(false);
/*     */             }
/* 276 */             player.func_70078_a(this);
/*     */             
/* 278 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_ORB, this, 1.0D, 1.0D, 16);
/* 279 */           } else if ((itemstack != null) && (Witchery.Items.TAGLOCK_KIT.isTaglockPresent(itemstack, Integer.valueOf(1)))) {
/* 280 */             this.waypoint = itemstack.func_77946_l();
/* 281 */             this.homeX = this.field_70165_t;
/* 282 */             this.homeY = this.field_70163_u;
/* 283 */             this.homeZ = this.field_70161_v;
/* 284 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_ORB, this, 1.0D, 1.0D, 16);
/* 285 */             if (!player.field_71075_bZ.field_75098_d) {
/* 286 */               itemstack.field_77994_a -= 1;
/*     */             }
/* 288 */           } else if ((func_70068_e(player) < 9.0D) && 
/* 289 */             (!this.field_70170_p.field_72995_K)) {
/* 290 */             func_70904_g(!func_70906_o());
/*     */           }
/*     */         }
/*     */         
/* 294 */         return true;
/*     */       }
/* 296 */       if ((TameableUtil.isOwner(this, player)) && 
/* 297 */         (func_70877_b(itemstack)) && (func_110143_aJ() < func_110138_aP())) {
/* 298 */         if (!this.field_70170_p.field_72995_K) {
/* 299 */           func_70691_i(10.0F);
/* 300 */           if (!player.field_71075_bZ.field_75098_d) {
/* 301 */             itemstack.field_77994_a -= 1;
/*     */           }
/*     */           
/* 304 */           if (itemstack.field_77994_a <= 0) {
/* 305 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*     */           }
/*     */         }
/*     */         
/* 309 */         return true;
/*     */       }
/*     */       
/*     */ 
/* 313 */       return super.func_70085_c(player);
/*     */     }
/* 315 */     if ((itemstack != null) && ((itemstack.func_77973_b() == Items.field_151147_al) || (itemstack.func_77973_b() == Items.field_151082_bd)) && (player.func_70068_e(this) < 9.0D))
/*     */     {
/*     */ 
/* 318 */       if (!player.field_71075_bZ.field_75098_d) {
/* 319 */         itemstack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 322 */       if (itemstack.field_77994_a <= 0) {
/* 323 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*     */       }
/*     */       
/* 326 */       if (!this.field_70170_p.field_72995_K) {
/* 327 */         if (this.field_70146_Z.nextInt(3) == 0) {
/* 328 */           func_70903_f(true);
/* 329 */           setTameSkin(1 + this.field_70170_p.field_73012_v.nextInt(3));
/* 330 */           TameableUtil.setOwner(this, player);
/* 331 */           func_110163_bv();
/* 332 */           func_70908_e(true);
/* 333 */           func_70904_g(true);
/* 334 */           this.field_70170_p.func_72960_a(this, (byte)7);
/*     */         } else {
/* 336 */           func_70908_e(false);
/* 337 */           this.field_70170_p.func_72960_a(this, (byte)6);
/*     */         }
/*     */       }
/*     */       
/* 341 */       return true;
/*     */     }
/* 343 */     if (!func_70877_b(itemstack)) {
/* 344 */       return super.func_70085_c(player);
/*     */     }
/*     */     
/* 347 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public net.minecraft.util.IIcon func_70620_b(ItemStack stack, int pass)
/*     */   {
/* 353 */     return stack.func_77973_b().func_77623_v() ? stack.func_77973_b().getIcon(stack, pass) : stack.func_77954_c();
/*     */   }
/*     */   
/*     */   public EntityWingedMonkey spawnBabyAnimal(EntityAgeable par1EntityAgeable)
/*     */   {
/* 358 */     EntityWingedMonkey entityocelot = new EntityWingedMonkey(this.field_70170_p);
/*     */     
/* 360 */     if (func_70909_n())
/*     */     {
/*     */ 
/* 363 */       entityocelot.func_110163_bv();
/* 364 */       entityocelot.setTameSkin(getTameSkin());
/* 365 */       entityocelot.setFeatherColor(getFeatherColor());
/*     */     }
/*     */     
/* 368 */     return entityocelot;
/*     */   }
/*     */   
/*     */   public boolean func_70877_b(ItemStack itemstack)
/*     */   {
/* 373 */     return (itemstack != null) && ((itemstack.func_77973_b() == Items.field_151147_al) || (itemstack.func_77973_b() == Items.field_151082_bd));
/*     */   }
/*     */   
/*     */   public boolean func_70878_b(EntityAnimal par1EntityAnimal)
/*     */   {
/* 378 */     if (par1EntityAnimal == this)
/* 379 */       return false;
/* 380 */     if (!func_70909_n())
/* 381 */       return false;
/* 382 */     if (!(par1EntityAnimal instanceof EntityWingedMonkey)) {
/* 383 */       return false;
/*     */     }
/* 385 */     EntityWingedMonkey entityocelot = (EntityWingedMonkey)par1EntityAnimal;
/* 386 */     return entityocelot.func_70909_n();
/*     */   }
/*     */   
/*     */   public int getFeatherColor()
/*     */   {
/* 391 */     return this.field_70180_af.func_75683_a(21) & 0xF;
/*     */   }
/*     */   
/*     */   public void setFeatherColor(int par1) {
/* 395 */     byte b0 = this.field_70180_af.func_75683_a(16);
/* 396 */     this.field_70180_af.func_75692_b(21, Byte.valueOf((byte)(b0 & 0xF0 | par1 & 0xF)));
/*     */   }
/*     */   
/*     */   public int getTameSkin() {
/* 400 */     return this.field_70180_af.func_75683_a(18);
/*     */   }
/*     */   
/*     */   public void setTameSkin(int par1) {
/* 404 */     this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)par1));
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi()
/*     */   {
/* 409 */     if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 410 */       return false;
/*     */     }
/* 412 */     if ((this.field_70170_p.func_72855_b(this.field_70121_D)) && (this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/*     */ 
/* 415 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 416 */       int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 417 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */       
/* 419 */       if (j < 63) {
/* 420 */         return false;
/*     */       }
/*     */       
/* 423 */       Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
/*     */       
/* 425 */       if ((block == net.minecraft.init.Blocks.field_150349_c) || ((block != null) && (block.isLeaves(this.field_70170_p, i, j - 1, k)))) {
/* 426 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 430 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 436 */     if (func_94056_bM()) {
/* 437 */       return func_94057_bL();
/*     */     }
/* 439 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.wingedmonkey.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable)
/*     */   {
/* 445 */     return spawnBabyAnimal(par1EntityAgeable);
/*     */   }
/*     */   
/*     */   public void setTimeToLive(int i) {
/* 449 */     this.timeToLive = i;
/*     */   }
/*     */   
/*     */   public boolean isTemp() {
/* 453 */     return this.timeToLive != -1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityWingedMonkey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */