/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIDimensionalFollowOwner;
/*     */ import com.emoniph.witchery.familiar.Familiar;
/*     */ import com.emoniph.witchery.familiar.IFamiliar;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TameableUtil;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockColored;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMate;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityJumpHelper;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityGhast;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityToad extends EntityTameable implements IFamiliar
/*     */ {
/*  52 */   private int timeToLive = -1;
/*  53 */   private boolean poisoned = false;
/*     */   
/*     */   public EntityToad(World par1World) {
/*  56 */     super(par1World);
/*  57 */     func_70105_a(0.8F, 0.8F);
/*  58 */     func_70661_as().func_75495_e(true);
/*  59 */     func_70661_as().func_75491_a(true);
/*  60 */     this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
/*  61 */     this.field_70714_bg.func_75776_a(2, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  66 */     this.field_70714_bg.func_75776_a(3, new EntityAIDimensionalFollowOwner(this, 1.0D, 10.0F, 2.0F));
/*     */     
/*     */ 
/*     */ 
/*  70 */     this.field_70714_bg.func_75776_a(4, new EntityAITempt(this, 1.25D, Items.field_151078_bh, false));
/*  71 */     this.field_70714_bg.func_75776_a(6, new EntityAIMate(this, 1.0D));
/*  72 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
/*  73 */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  74 */     this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
/*  75 */     func_70903_f(false);
/*     */   }
/*     */   
/*     */   public void setTimeToLive(int i, boolean poisoned) {
/*  79 */     this.timeToLive = i;
/*  80 */     this.poisoned = poisoned;
/*     */   }
/*     */   
/*     */   public boolean isTemp() {
/*  84 */     return this.timeToLive != -1;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  89 */     super.func_110147_ax();
/*  90 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000001192092895D);
/*  91 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
/*     */   }
/*     */   
/*     */   public int func_70658_aO()
/*     */   {
/*  96 */     return super.func_70658_aO() + (isFamiliar() ? 5 : 0);
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 101 */     return super.func_70627_aG() * 2;
/*     */   }
/*     */   
/*     */   public void setMaxHealth(float maxHealth)
/*     */   {
/* 106 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth);
/* 107 */     func_70606_j(maxHealth);
/* 108 */     setFamiliar(true);
/*     */   }
/*     */   
/*     */   public EntityLivingBase func_70902_q()
/*     */   {
/* 113 */     if ((isFamiliar()) && (!this.field_70170_p.field_72995_K)) {
/* 114 */       return TameableUtil.getOwnerAccrossDimensions(this);
/*     */     }
/* 116 */     return super.func_70902_q();
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 122 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 136 */     super.func_70629_bd();
/* 137 */     this.field_70180_af.func_75692_b(18, Float.valueOf(func_110143_aJ()));
/* 138 */     if ((this.field_70170_p != null) && (!this.field_70128_L) && (!this.field_70170_p.field_72995_K) && (this.timeToLive != -1) && (--this.timeToLive == 0))
/*     */     {
/* 140 */       func_70106_y();
/*     */       
/* 142 */       if (this.poisoned) {
/* 143 */         AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(3.0D, 2.0D, 3.0D);
/* 144 */         List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
/*     */         
/* 146 */         if ((list1 != null) && (!list1.isEmpty()))
/*     */         {
/* 148 */           Iterator iterator = list1.iterator();
/*     */           
/* 150 */           while (iterator.hasNext())
/*     */           {
/* 152 */             EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
/* 153 */             double d0 = func_70068_e(entitylivingbase);
/*     */             
/* 155 */             if (d0 < 9.0D)
/*     */             {
/* 157 */               double d1 = 1.0D - Math.sqrt(d0) / 3.0D;
/* 158 */               entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 60, 0));
/*     */             }
/*     */           }
/*     */         }
/* 162 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, this, 1.0D, 1.0D, 16);
/*     */       }
/* 164 */       ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, this, 0.5D, 0.5D, 16);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 170 */     super.func_70088_a();
/* 171 */     this.field_70180_af.func_75682_a(18, new Float(func_110143_aJ()));
/* 172 */     this.field_70180_af.func_75682_a(19, new Byte((byte)0));
/* 173 */     this.field_70180_af.func_75682_a(20, new Byte((byte)BlockColored.func_150032_b(this.field_70170_p != null ? this.field_70170_p.field_73012_v.nextInt(16) : new Random().nextInt(16))));
/* 174 */     this.field_70180_af.func_75682_a(26, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public boolean isFamiliar()
/*     */   {
/* 179 */     return this.field_70180_af.func_75683_a(26) > 0;
/*     */   }
/*     */   
/*     */   public void setFamiliar(boolean familiar) {
/* 183 */     this.field_70180_af.func_75692_b(26, Byte.valueOf((byte)(familiar ? 1 : 0)));
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 188 */     func_85030_a("mob.slime.small", 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 193 */     super.func_70014_b(par1NBTTagCompound);
/* 194 */     par1NBTTagCompound.func_74774_a("SkinColor", (byte)getSkinColor());
/* 195 */     par1NBTTagCompound.func_74774_a("Familiar", (byte)(isFamiliar() ? 1 : 0));
/* 196 */     par1NBTTagCompound.func_74768_a("SuicideIn", this.timeToLive);
/* 197 */     par1NBTTagCompound.func_74757_a("Poisonous", this.poisoned);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 202 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/* 204 */     if (par1NBTTagCompound.func_74764_b("SkinColor")) {
/* 205 */       setSkinColor(par1NBTTagCompound.func_74771_c("SkinColor"));
/*     */     }
/*     */     
/* 208 */     if (par1NBTTagCompound.func_74764_b("Familiar")) {
/* 209 */       setFamiliar(par1NBTTagCompound.func_74771_c("Familiar") > 0);
/*     */     }
/*     */     
/* 212 */     if (par1NBTTagCompound.func_74764_b("SuicideIn")) {
/* 213 */       this.timeToLive = par1NBTTagCompound.func_74762_e("SuicideIn");
/*     */     } else {
/* 215 */       this.timeToLive = -1;
/*     */     }
/*     */     
/* 218 */     if (par1NBTTagCompound.func_74764_b("Poisonous")) {
/* 219 */       this.poisoned = par1NBTTagCompound.func_74767_n("Poisonous");
/*     */     } else {
/* 221 */       this.poisoned = false;
/*     */     }
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 227 */     return "witchery:mob.toad.toad_croak";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 232 */     return "witchery:mob.toad.toad_hurt";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 237 */     return "witchery:mob.toad.toad_hurt";
/*     */   }
/*     */   
/*     */   protected float func_70599_aP()
/*     */   {
/* 242 */     return 0.4F;
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 247 */     if (!isTemp()) {
/* 248 */       return Items.field_151123_aH;
/*     */     }
/* 250 */     return super.func_146068_u();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 257 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 262 */     this.field_70178_ae = isFamiliar();
/* 263 */     super.func_70071_h_();
/* 264 */     if ((!func_70906_o()) && (!this.field_70170_p.field_72995_K) && ((this.field_70159_w != 0.0D) || (this.field_70179_y != 0.0D)) && (!func_70090_H())) {
/* 265 */       func_70683_ar().func_75660_a();
/*     */     }
/*     */   }
/*     */   
/*     */   public float func_70047_e()
/*     */   {
/* 271 */     return this.field_70131_O * 0.8F;
/*     */   }
/*     */   
/*     */   public int func_70646_bf()
/*     */   {
/* 276 */     return func_70906_o() ? 20 : super.func_70646_bf();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 284 */     if (func_85032_ar()) {
/* 285 */       return false;
/*     */     }
/* 287 */     Entity entity = par1DamageSource.func_76346_g();
/* 288 */     if (!isFamiliar())
/*     */     {
/* 290 */       func_70904_g(false);
/*     */     }
/*     */     
/* 293 */     if ((entity != null) && (!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityArrow))) {
/* 294 */       par2 = (par2 + 1.0F) / 2.0F;
/*     */     }
/*     */     
/* 297 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70903_f(boolean par1)
/*     */   {
/* 303 */     super.func_70903_f(par1);
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 308 */     if (isTemp()) {
/* 309 */       return true;
/*     */     }
/*     */     
/* 312 */     ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
/*     */     
/* 314 */     if (func_70909_n()) {
/* 315 */       if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (isFamiliar()) && (par1EntityPlayer.func_70093_af()) && (func_70906_o())) {
/* 316 */         if (!this.field_70170_p.field_72995_K) {
/* 317 */           Familiar.dismissFamiliar(par1EntityPlayer, this);
/*     */         }
/* 319 */         return true;
/*     */       }
/*     */       
/* 322 */       if (itemstack != null) {
/* 323 */         if ((itemstack.func_77973_b() == Items.field_151078_bh) && (func_110143_aJ() < func_110138_aP())) {
/* 324 */           if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
/* 325 */             itemstack.field_77994_a -= 1;
/*     */           }
/*     */           
/* 328 */           func_70691_i(10.0F);
/*     */           
/* 330 */           if (itemstack.field_77994_a <= 0) {
/* 331 */             par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */           }
/*     */           
/* 334 */           return true; }
/* 335 */         if (itemstack.func_77973_b() == Items.field_151100_aR) {
/* 336 */           int i = BlockColored.func_150032_b(itemstack.func_77960_j());
/*     */           
/* 338 */           if (i != getSkinColor()) {
/* 339 */             setSkinColor(i);
/*     */             
/* 341 */             if (!par1EntityPlayer.field_71075_bZ.field_75098_d) { if (--itemstack.field_77994_a <= 0) {
/* 342 */                 par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */               }
/*     */             }
/* 345 */             return true;
/*     */           }
/* 347 */         } else if ((itemstack.func_77973_b() == Items.field_151057_cb) || (itemstack.func_77973_b() == Witchery.Items.POLYNESIA_CHARM) || (itemstack.func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM))
/*     */         {
/* 349 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 353 */       if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (!func_70877_b(itemstack))) {
/* 354 */         if (!this.field_70170_p.field_72995_K)
/*     */         {
/* 356 */           func_70904_g(!func_70906_o());
/* 357 */           func_70683_ar().func_75661_b();
/* 358 */           this.field_70703_bu = false;
/* 359 */           func_70778_a((PathEntity)null);
/* 360 */           func_70784_b((Entity)null);
/* 361 */           func_70624_b((EntityLivingBase)null);
/*     */         }
/* 363 */         return true;
/*     */       }
/* 365 */     } else if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151078_bh)) {
/* 366 */       if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
/* 367 */         itemstack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 370 */       if (itemstack.field_77994_a <= 0) {
/* 371 */         par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
/*     */       }
/*     */       
/* 374 */       if (!this.field_70170_p.field_72995_K) {
/* 375 */         if (this.field_70146_Z.nextInt(3) == 0) {
/* 376 */           func_70903_f(true);
/* 377 */           func_110163_bv();
/* 378 */           func_70778_a((PathEntity)null);
/* 379 */           func_70624_b((EntityLivingBase)null);
/*     */           
/* 381 */           func_70904_g(true);
/* 382 */           TameableUtil.setOwner(this, par1EntityPlayer);
/* 383 */           func_70908_e(true);
/* 384 */           this.field_70170_p.func_72960_a(this, (byte)7);
/*     */         } else {
/* 386 */           func_70908_e(false);
/* 387 */           this.field_70170_p.func_72960_a(this, (byte)6);
/*     */         }
/*     */       }
/*     */       
/* 391 */       return true;
/*     */     }
/*     */     
/* 394 */     return super.func_70085_c(par1EntityPlayer);
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 399 */     if (func_94056_bM()) {
/* 400 */       return func_94057_bL();
/*     */     }
/* 402 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.toad.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70877_b(ItemStack par1ItemStack)
/*     */   {
/* 408 */     return (par1ItemStack != null) && (par1ItemStack.func_77973_b() == Items.field_151078_bh);
/*     */   }
/*     */   
/*     */   public int getSkinColor() {
/* 412 */     return this.field_70180_af.func_75683_a(20) & 0xF;
/*     */   }
/*     */   
/*     */   public void setSkinColor(int par1) {
/* 416 */     this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)(par1 & 0xF)));
/*     */   }
/*     */   
/*     */   public EntityToad spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/* 420 */     EntityToad entity = new EntityToad(this.field_70170_p);
/*     */     
/* 422 */     if (TameableUtil.hasOwner(this)) {
/* 423 */       entity.func_110163_bv();
/* 424 */       entity.setSkinColor(getSkinColor());
/*     */     }
/*     */     
/* 427 */     return entity;
/*     */   }
/*     */   
/*     */   public boolean func_70878_b(EntityAnimal par1EntityAnimal)
/*     */   {
/* 432 */     if (par1EntityAnimal == this)
/* 433 */       return false;
/* 434 */     if (!func_70909_n())
/* 435 */       return false;
/* 436 */     if (!(par1EntityAnimal instanceof EntityToad)) {
/* 437 */       return false;
/*     */     }
/* 439 */     EntityToad entity = (EntityToad)par1EntityAnimal;
/* 440 */     return entity.func_70909_n();
/*     */   }
/*     */   
/*     */   public boolean func_70922_bv()
/*     */   {
/* 445 */     return this.field_70180_af.func_75683_a(19) == 1;
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 450 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase)
/*     */   {
/* 455 */     if ((!(par1EntityLivingBase instanceof EntityCreeper)) && (!(par1EntityLivingBase instanceof EntityGhast))) {
/* 456 */       if ((par1EntityLivingBase instanceof EntityToad)) {
/* 457 */         EntityToad entity = (EntityToad)par1EntityLivingBase;
/*     */         
/* 459 */         if ((entity.func_70909_n()) && (entity.func_70902_q() == par2EntityLivingBase)) {
/* 460 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 464 */       return (!(par1EntityLivingBase instanceof EntityPlayer)) || (!(par2EntityLivingBase instanceof EntityPlayer)) || (((EntityPlayer)par2EntityLivingBase).func_96122_a((EntityPlayer)par1EntityLivingBase));
/*     */     }
/*     */     
/*     */ 
/* 468 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable)
/*     */   {
/* 474 */     return spawnBabyAnimal(par1EntityAgeable);
/*     */   }
/*     */   
/*     */   public void clearFamiliar()
/*     */   {
/* 479 */     setFamiliar(false);
/* 480 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
/* 481 */     func_70606_j(10.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityToad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */