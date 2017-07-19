/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint.CarryRequirement;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFollowOwner;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerMate;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyingTempt;
/*     */ import com.emoniph.witchery.familiar.Familiar;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TameableUtil;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
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
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityOwl extends EntityFlyingTameable implements com.emoniph.witchery.familiar.IFamiliar
/*     */ {
/*     */   public EntityAIFlyingTempt aiTempt;
/*  52 */   private int timeToLive = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  59 */   private static final ItemStack[] TEMPTATIONS = { new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151082_bd) };
/*     */   
/*     */   public EntityOwl(World world) {
/*  62 */     super(world);
/*  63 */     func_70105_a(0.6F, 0.8F);
/*  64 */     func_70661_as().func_75495_e(true);
/*  65 */     this.field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
/*  66 */     this.field_70714_bg.func_75776_a(2, new EntityAIFlyerFlyToWaypoint(this, EntityAIFlyerFlyToWaypoint.CarryRequirement.HELD_ITEM));
/*  67 */     this.field_70714_bg.func_75776_a(3, new com.emoniph.witchery.entity.ai.EntityAIFlyerAttackOnCollide(this, 1.0D, true));
/*  68 */     this.field_70714_bg.func_75776_a(4, this.aiTempt = new EntityAIFlyingTempt(this, 0.6D, TEMPTATIONS, true));
/*  69 */     this.field_70714_bg.func_75776_a(5, new EntityAIFlyerFollowOwner(this, 1.0D, 14.0F, 5.0F));
/*  70 */     this.field_70714_bg.func_75776_a(8, new EntityAIFlyerMate(this, 0.8D));
/*  71 */     this.field_70714_bg.func_75776_a(9, new EntityAIFlyerLand(this, 0.8D, true));
/*  72 */     this.field_70714_bg.func_75776_a(10, new com.emoniph.witchery.entity.ai.EntityAIFlyerWander(this, 0.8D, 10.0D));
/*  73 */     this.field_70714_bg.func_75776_a(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F, 0.2F));
/*  74 */     this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
/*  75 */     this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
/*  76 */     this.field_70715_bh.func_75776_a(3, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/*  81 */     return super.func_70658_aO() + (isFamiliar() ? 5 : 1);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot) {
/*  85 */     super.func_70014_b(nbtRoot);
/*     */     
/*  87 */     nbtRoot.func_74774_a("FeatherColor", (byte)getFeatherColor());
/*  88 */     nbtRoot.func_74774_a("Familiar", (byte)(isFamiliar() ? 1 : 0));
/*  89 */     nbtRoot.func_74768_a("SuicideIn", this.timeToLive);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/*  94 */     super.func_70037_a(nbtRoot);
/*     */     
/*  96 */     if (nbtRoot.func_74764_b("FeatherColor")) {
/*  97 */       setFeatherColor(nbtRoot.func_74771_c("FeatherColor"));
/*     */     }
/*  99 */     if (nbtRoot.func_74764_b("Familiar")) {
/* 100 */       setFamiliar(nbtRoot.func_74771_c("Familiar") > 0);
/*     */     }
/*     */     
/* 103 */     if (nbtRoot.func_74764_b("SuicideIn")) {
/* 104 */       this.timeToLive = nbtRoot.func_74762_e("SuicideIn");
/*     */     } else {
/* 106 */       this.timeToLive = -1;
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 111 */     this.field_70178_ae = isFamiliar();
/* 112 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 117 */     super.func_70088_a();
/* 118 */     this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
/* 119 */     this.field_70180_af.func_75682_a(21, Byte.valueOf((byte)(this.field_70170_p.field_73012_v.nextInt(100) == 0 ? 0 : this.field_70170_p.field_73012_v.nextInt(15) + 1)));
/* 120 */     this.field_70180_af.func_75682_a(26, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public boolean isFamiliar()
/*     */   {
/* 125 */     return this.field_70180_af.func_75683_a(26) > 0;
/*     */   }
/*     */   
/*     */   public void setFamiliar(boolean familiar) {
/* 129 */     this.field_70180_af.func_75692_b(26, Byte.valueOf((byte)(familiar ? 1 : 0)));
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 134 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 139 */     super.func_110147_ax();
/* 140 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
/*     */     
/* 142 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
/*     */     
/* 144 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 145 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */   public void setMaxHealth(float maxHealth)
/*     */   {
/* 150 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth);
/* 151 */     func_70606_j(maxHealth);
/* 152 */     setFamiliar(true);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 157 */     return false;
/*     */   }
/*     */   
/*     */   public EntityLivingBase func_70902_q()
/*     */   {
/* 162 */     if ((isFamiliar()) && (!this.field_70170_p.field_72995_K)) {
/* 163 */       return TameableUtil.getOwnerAccrossDimensions(this);
/*     */     }
/* 165 */     return super.func_70902_q();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70629_bd()
/*     */   {
/* 176 */     func_70661_as().func_75499_g();
/* 177 */     super.func_70629_bd();
/* 178 */     if ((this.field_70170_p != null) && (!this.field_70128_L) && (!this.field_70170_p.field_72995_K) && (this.timeToLive != -1) && ((--this.timeToLive == 0) || (func_70638_az() == null) || (func_70638_az().field_70128_L))) {
/* 179 */       ParticleEffect.EXPLODE.send(SoundEffect.NONE, this, 1.0D, 1.0D, 16);
/* 180 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 186 */     if (!isTemp()) {
/* 187 */       int var3 = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + par2);
/*     */       
/* 189 */       for (int var4 = 0; var4 < var3; var4++) {
/* 190 */         func_70099_a(new ItemStack(Items.field_151008_G), 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 197 */     return super.func_70627_aG() * 2;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 202 */     return "witchery:mob.owl.owl_hoot";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 207 */     return "witchery:mob.owl.owl_hurt";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 212 */     return "witchery:mob.owl.owl_hurt";
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 217 */     return par1Entity.func_70097_a(DamageSource.func_76358_a(this), 2.0F);
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 222 */     if (func_85032_ar()) {
/* 223 */       return false;
/*     */     }
/* 225 */     if (!isFamiliar()) {
/* 226 */       func_70904_g(false);
/*     */     }
/* 228 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 234 */     if (isTemp()) {
/* 235 */       return true;
/*     */     }
/*     */     
/* 238 */     ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
/*     */     
/* 240 */     if (func_70909_n()) {
/* 241 */       if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (isFamiliar()) && (par1EntityPlayer.func_70093_af()) && (func_70906_o())) {
/* 242 */         if (!this.field_70170_p.field_72995_K) {
/* 243 */           Familiar.dismissFamiliar(par1EntityPlayer, this);
/*     */         }
/* 245 */         return true;
/*     */       }
/*     */       
/* 248 */       if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (!func_70877_b(itemstack)))
/*     */       {
/* 250 */         if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151100_aR)) {
/* 251 */           if (!this.field_70170_p.field_72995_K) {
/* 252 */             int i = net.minecraft.block.BlockColored.func_150032_b(itemstack.func_77960_j());
/* 253 */             setFeatherColor(i);
/*     */             
/* 255 */             if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
/* 256 */               itemstack.field_77994_a -= 1;
/*     */             }
/*     */             
/* 259 */             if (itemstack.field_77994_a <= 0)
/* 260 */               par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */           }
/*     */         } else {
/* 263 */           if ((itemstack != null) && ((itemstack.func_77973_b() == Items.field_151057_cb) || (itemstack.func_77973_b() == Witchery.Items.POLYNESIA_CHARM) || (itemstack.func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM)))
/* 264 */             return false;
/* 265 */           if ((itemstack != null) && (func_70694_bm() == null)) {
/* 266 */             if (itemstack.field_77994_a == 1) {
/* 267 */               func_70062_b(0, itemstack);
/* 268 */               par1EntityPlayer.func_70062_b(0, null);
/*     */             } else {
/* 270 */               func_70062_b(0, itemstack.func_77979_a(1));
/*     */             }
/* 272 */           } else if ((Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(itemstack)) || (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(itemstack))) {
/* 273 */             this.waypoint = itemstack.func_77946_l();
/* 274 */             this.homeX = this.field_70165_t;
/* 275 */             this.homeY = this.field_70163_u;
/* 276 */             this.homeZ = this.field_70161_v;
/* 277 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_ORB, this, 1.0D, 1.0D, 16);
/* 278 */           } else if ((func_70694_bm() != null) && (this.waypoint == null)) {
/* 279 */             ItemStack heldStack = func_70694_bm();
/* 280 */             func_70062_b(0, null);
/* 281 */             if (!this.field_70170_p.field_72995_K) {
/* 282 */               this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, heldStack));
/*     */             }
/* 284 */           } else if ((func_70068_e(par1EntityPlayer) < 9.0D) && 
/* 285 */             (!this.field_70170_p.field_72995_K)) {
/* 286 */             func_70904_g(!func_70906_o());
/*     */           }
/*     */         }
/*     */         
/* 290 */         return true;
/*     */       }
/* 292 */       if ((TameableUtil.isOwner(this, par1EntityPlayer)) && 
/* 293 */         (func_70877_b(itemstack)) && (func_110143_aJ() < func_110138_aP())) {
/* 294 */         if (!this.field_70170_p.field_72995_K) {
/* 295 */           func_70691_i(10.0F);
/* 296 */           if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
/* 297 */             itemstack.field_77994_a -= 1;
/*     */           }
/*     */           
/* 300 */           if (itemstack.field_77994_a <= 0) {
/* 301 */             par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */           }
/*     */         }
/* 304 */         return true;
/*     */       }
/*     */       
/*     */ 
/* 308 */       return super.func_70085_c(par1EntityPlayer);
/*     */     }
/* 310 */     if ((itemstack != null) && ((itemstack.func_77973_b() == Items.field_151147_al) || (itemstack.func_77973_b() == Items.field_151082_bd)) && (par1EntityPlayer.func_70068_e(this) < 9.0D))
/*     */     {
/* 312 */       if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
/* 313 */         itemstack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 316 */       if (itemstack.field_77994_a <= 0) {
/* 317 */         par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */       }
/*     */       
/* 320 */       if (!this.field_70170_p.field_72995_K) {
/* 321 */         if (this.field_70146_Z.nextInt(3) == 0) {
/* 322 */           func_70903_f(true);
/* 323 */           setTameSkin(1 + this.field_70170_p.field_73012_v.nextInt(3));
/* 324 */           TameableUtil.setOwner(this, par1EntityPlayer);
/* 325 */           func_110163_bv();
/* 326 */           func_70908_e(true);
/* 327 */           func_70904_g(true);
/* 328 */           this.field_70170_p.func_72960_a(this, (byte)7);
/*     */         } else {
/* 330 */           func_70908_e(false);
/* 331 */           this.field_70170_p.func_72960_a(this, (byte)6);
/*     */         }
/*     */       }
/*     */       
/* 335 */       return true;
/*     */     }
/* 337 */     if (!func_70877_b(itemstack)) {
/* 338 */       return super.func_70085_c(par1EntityPlayer);
/*     */     }
/*     */     
/* 341 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public IIcon func_70620_b(ItemStack stack, int pass) {
/* 346 */     return stack.func_77973_b().func_77623_v() ? stack.func_77973_b().getIcon(stack, pass) : stack.func_77954_c();
/*     */   }
/*     */   
/*     */   public EntityOwl spawnBabyAnimal(EntityAgeable par1EntityAgeable)
/*     */   {
/* 351 */     EntityOwl entityocelot = new EntityOwl(this.field_70170_p);
/*     */     
/* 353 */     if (func_70909_n())
/*     */     {
/*     */ 
/* 356 */       entityocelot.func_110163_bv();
/* 357 */       entityocelot.setTameSkin(getTameSkin());
/* 358 */       entityocelot.setFeatherColor(getFeatherColor());
/*     */     }
/*     */     
/* 361 */     return entityocelot;
/*     */   }
/*     */   
/*     */   public boolean func_70877_b(ItemStack itemstack)
/*     */   {
/* 366 */     return (itemstack != null) && ((itemstack.func_77973_b() == Items.field_151147_al) || (itemstack.func_77973_b() == Items.field_151082_bd));
/*     */   }
/*     */   
/*     */   public boolean func_70878_b(EntityAnimal par1EntityAnimal)
/*     */   {
/* 371 */     if (par1EntityAnimal == this)
/* 372 */       return false;
/* 373 */     if (!func_70909_n())
/* 374 */       return false;
/* 375 */     if (!(par1EntityAnimal instanceof EntityOwl)) {
/* 376 */       return false;
/*     */     }
/* 378 */     EntityOwl entityocelot = (EntityOwl)par1EntityAnimal;
/* 379 */     return entityocelot.func_70909_n();
/*     */   }
/*     */   
/*     */   public int getFeatherColor()
/*     */   {
/* 384 */     return this.field_70180_af.func_75683_a(21) & 0xF;
/*     */   }
/*     */   
/*     */   public void setFeatherColor(int par1) {
/* 388 */     byte b0 = this.field_70180_af.func_75683_a(16);
/* 389 */     this.field_70180_af.func_75692_b(21, Byte.valueOf((byte)(b0 & 0xF0 | par1 & 0xF)));
/*     */   }
/*     */   
/*     */   public int getTameSkin() {
/* 393 */     return this.field_70180_af.func_75683_a(18);
/*     */   }
/*     */   
/*     */   public void setTameSkin(int par1) {
/* 397 */     this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)par1));
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi()
/*     */   {
/* 402 */     if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
/* 403 */       return false;
/*     */     }
/* 405 */     if ((this.field_70170_p.func_72855_b(this.field_70121_D)) && (this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D)))
/*     */     {
/* 407 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 408 */       int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 409 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */       
/* 411 */       if (j < 63) {
/* 412 */         return false;
/*     */       }
/*     */       
/* 415 */       Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
/*     */       
/* 417 */       if ((block == net.minecraft.init.Blocks.field_150349_c) || ((block != null) && (block.isLeaves(this.field_70170_p, i, j - 1, k)))) {
/* 418 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 422 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 428 */     if (func_94056_bM()) {
/* 429 */       return func_94057_bL();
/*     */     }
/* 431 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.owl.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 437 */     par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
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
/*     */ 
/* 449 */     return par1EntityLivingData;
/*     */   }
/*     */   
/*     */   public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable)
/*     */   {
/* 454 */     return spawnBabyAnimal(par1EntityAgeable);
/*     */   }
/*     */   
/*     */   public void setTimeToLive(int i) {
/* 458 */     this.timeToLive = i;
/*     */   }
/*     */   
/*     */   public boolean isTemp() {
/* 462 */     return this.timeToLive != -1;
/*     */   }
/*     */   
/*     */   public void clearFamiliar()
/*     */   {
/* 467 */     setFamiliar(false);
/* 468 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
/* 469 */     func_70606_j(15.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityOwl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */