/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBreakDoor;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityNightmare extends EntityMob implements IEntitySelector
/*     */ {
/*     */   private int attackTimer;
/*     */   private int defenseTimer;
/*     */   
/*     */   public EntityNightmare(World par1World)
/*     */   {
/*  47 */     super(par1World);
/*     */     
/*  49 */     this.field_70178_ae = true;
/*     */     
/*  51 */     func_70105_a(0.6F, 1.8F);
/*     */     
/*  53 */     func_70661_as().func_75491_a(true);
/*  54 */     func_70661_as().func_75495_e(true);
/*  55 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  56 */     this.field_70714_bg.func_75776_a(2, new EntityAIBreakDoor(this));
/*  57 */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, 1.0D, true));
/*  58 */     this.field_70714_bg.func_75776_a(4, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
/*  59 */     this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  60 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIMoveThroughVillage(this, 1.0D, false));
/*  61 */     this.field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  62 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  63 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  64 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  65 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, this));
/*  66 */     this.field_70728_aV = 25;
/*     */   }
/*     */   
/*     */   public boolean func_82704_a(Entity entity)
/*     */   {
/*  71 */     if ((entity instanceof EntityPlayer)) {
/*  72 */       EntityPlayer player = (EntityPlayer)entity;
/*  73 */       String victim = getVictimName();
/*  74 */       return (victim == null) || (victim.isEmpty()) || (player.func_70005_c_().equalsIgnoreCase(victim));
/*     */     }
/*  76 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/*  82 */     return super.func_70627_aG() * 2;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  88 */     super.func_70088_a();
/*  89 */     this.field_70180_af.func_75682_a(17, "");
/*  90 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*  91 */     this.field_70180_af.func_75682_a(21, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */   public boolean isScreaming()
/*     */   {
/* 104 */     return this.field_70180_af.func_75683_a(16) > 0;
/*     */   }
/*     */   
/*     */   public void setScreaming(boolean par1)
/*     */   {
/* 109 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public boolean isDefended()
/*     */   {
/* 114 */     return this.field_70180_af.func_75683_a(21) > 0;
/*     */   }
/*     */   
/*     */   public void setDefended(boolean par1)
/*     */   {
/* 119 */     this.field_70180_af.func_75692_b(21, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 124 */     if (func_94056_bM()) {
/* 125 */       return func_94057_bL();
/*     */     }
/* 127 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.nightmare.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 138 */     super.func_70629_bd();
/*     */     
/* 140 */     if ((!this.field_70170_p.field_72995_K) && (func_70089_S())) {
/* 141 */       if (func_70638_az() != null) {
/* 142 */         setScreaming(true);
/*     */       } else {
/* 144 */         setScreaming(false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 153 */     super.func_110147_ax();
/* 154 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/* 155 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
/* 156 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/* 157 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */   protected void func_70626_be()
/*     */   {
/* 162 */     super.func_70626_be();
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 167 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 172 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 177 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/* 179 */     if (getVictimName() == null)
/*     */     {
/* 181 */       par1NBTTagCompound.func_74778_a("Victim", "");
/*     */     }
/*     */     else
/*     */     {
/* 185 */       par1NBTTagCompound.func_74778_a("Victim", getVictimName());
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 191 */     super.func_70037_a(par1NBTTagCompound);
/* 192 */     String s = par1NBTTagCompound.func_74779_i("Victim");
/*     */     
/* 194 */     if (s.length() > 0)
/*     */     {
/* 196 */       setVictim(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getVictimName()
/*     */   {
/* 202 */     String s = this.field_70180_af.func_75681_e(17);
/* 203 */     return s != null ? s : "";
/*     */   }
/*     */   
/*     */   public void setVictim(String par1Str)
/*     */   {
/* 208 */     this.field_70180_af.func_75692_b(17, par1Str);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 213 */     super.func_70636_d();
/*     */     
/* 215 */     if (!this.field_70170_p.field_72995_K) {
/* 216 */       if ((this.defenseTimer > 0) && 
/* 217 */         (--this.defenseTimer == 0)) {
/* 218 */         setDefended(false);
/*     */       }
/*     */       
/*     */ 
/* 222 */       if (((!this.field_70128_L) && (!getVictimName().isEmpty()) && ((func_70638_az() == null) || (func_70638_az().field_70128_L) || (func_70068_e(func_70638_az()) > 256.0D))) || ((this.field_70170_p.field_73012_v.nextInt(5) == 0) && ((func_70638_az() instanceof EntityPlayer)) && (WorldProviderDreamWorld.getPlayerHasNightmare((EntityPlayer)func_70638_az()) == 0) && (!isWakingNightmare((EntityPlayer)func_70638_az()))))
/*     */       {
/*     */ 
/* 225 */         ParticleEffect.EXPLODE.send(SoundEffect.NONE, this, 1.0D, 2.0D, 16);
/* 226 */         func_70106_y();
/*     */       }
/*     */     }
/*     */     
/* 230 */     if (this.attackTimer > 0) {
/* 231 */       this.attackTimer -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isWakingNightmare(EntityPlayer player) {
/* 236 */     NBTTagCompound nbtTag = Infusion.getNBT(player);
/* 237 */     if ((nbtTag != null) && (nbtTag.func_74764_b("witcheryWakingNightmare"))) {
/* 238 */       return nbtTag.func_74762_e("witcheryWakingNightmare") > 0;
/*     */     }
/* 240 */     return player.func_70644_a(Witchery.Potions.WAKING_NIGHTMARE);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 247 */     if (par1 == 4) {
/* 248 */       this.attackTimer = 15;
/*     */     } else {
/* 250 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity entity)
/*     */   {
/* 256 */     this.attackTimer = 15;
/* 257 */     this.field_70170_p.func_72960_a(this, (byte)4);
/* 258 */     if ((entity != null) && ((entity instanceof EntityPlayer))) {
/* 259 */       EntityPlayer player = (EntityPlayer)entity;
/* 260 */       if (!findInInventory(player.field_71071_by, Witchery.Items.GENERIC.itemCharmOfDisruptedDreams)) {
/* 261 */         int index = player.field_70170_p.field_73012_v.nextInt(player.field_71071_by.field_70460_b.length);
/* 262 */         if (player.field_71071_by.field_70460_b[index] != null) {
/* 263 */           Infusion.dropEntityItemWithRandomChoice(player, player.field_71071_by.field_70460_b[index], true);
/* 264 */           player.field_71071_by.field_70460_b[index] = null;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 269 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 270 */     if (this.field_71093_bK != Config.instance().dimensionDreamID) {
/* 271 */       f = 0.5F;
/*     */     }
/* 273 */     int i = 0;
/*     */     
/* 275 */     if ((entity instanceof EntityLivingBase))
/*     */     {
/* 277 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)entity);
/* 278 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)entity);
/*     */     }
/*     */     
/* 281 */     boolean flag = entity.func_70097_a(DamageSource.func_76358_a(this), f);
/*     */     
/* 283 */     if (flag)
/*     */     {
/* 285 */       if (i > 0)
/*     */       {
/* 287 */         entity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/* 288 */         this.field_70159_w *= 0.6D;
/* 289 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 292 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 294 */       if (j > 0)
/*     */       {
/* 296 */         entity.func_70015_d(j * 4);
/*     */       }
/*     */     }
/*     */     
/* 300 */     return flag;
/*     */   }
/*     */   
/*     */   private boolean findInInventory(InventoryPlayer inventory, ItemGeneral.SubItem item) {
/* 304 */     for (int i = 0; i < inventory.field_70462_a.length; i++) {
/* 305 */       ItemStack stack = inventory.field_70462_a[i];
/* 306 */       if ((stack != null) && (item.isMatch(stack))) {
/* 307 */         return true;
/*     */       }
/*     */     }
/* 310 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 315 */     if (isDefended()) {
/* 316 */       return false;
/*     */     }
/* 318 */     boolean weakeningWeapon = false;
/* 319 */     if (((source instanceof EntityDamageSource)) && (((EntityDamageSource)source).func_76346_g() != null) && ((((EntityDamageSource)source).func_76346_g() instanceof EntityLivingBase))) {
/* 320 */       EntityLivingBase living = (EntityLivingBase)((EntityDamageSource)source).func_76346_g();
/* 321 */       if ((living.func_70694_bm() != null) && (living.func_70694_bm().func_77973_b() == Witchery.Items.HUNTSMANS_SPEAR)) {
/* 322 */         weakeningWeapon = true;
/*     */       }
/*     */     }
/* 325 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)) != Witchery.Blocks.FLOWING_SPIRIT))
/*     */     {
/*     */ 
/* 328 */       this.defenseTimer = (weakeningWeapon ? 30 : this.field_71093_bK == Config.instance().dimensionDreamID ? 80 : weakeningWeapon ? 40 : 40);
/* 329 */       setDefended(true);
/*     */     }
/* 331 */     return super.func_70097_a(source, Math.min(damage, 15.0F));
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer()
/*     */   {
/* 338 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 343 */     return "witchery:mob.nightmare.nightmare_live";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 348 */     return "witchery:mob.nightmare.nightmare_dead";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 353 */     return "witchery:mob.nightmare.nightmare_hit";
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 358 */     if (this.field_71093_bK == Config.instance().dimensionDreamID) {
/* 359 */       int chance = this.field_70146_Z.nextInt(Math.max(10 - par2, 5));
/* 360 */       int quantity = (par2 > 0) && (chance == 0) ? 2 : 1;
/* 361 */       func_70099_a(Witchery.Items.GENERIC.itemMellifluousHunger.createStack(quantity), 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource source)
/*     */   {
/* 367 */     if ((!this.field_70170_p.field_72995_K) && (source != null) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
/* 368 */       EntityPlayer player = (EntityPlayer)source.func_76346_g();
/* 369 */       String victim = getVictimName();
/* 370 */       if ((victim != null) && (!victim.isEmpty()) && (player.func_70005_c_().equalsIgnoreCase(victim)) && (this.field_71093_bK == Config.instance().dimensionDreamID)) {
/* 371 */         WorldProviderDreamWorld.setPlayerLastNightmareKillNow(player);
/*     */       }
/*     */     }
/* 374 */     super.func_70645_a(source);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 379 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 387 */     return super.func_110161_a(par1EntityLivingData);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityNightmare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */