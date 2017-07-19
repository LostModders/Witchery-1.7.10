/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Dye;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class EntityEnt extends EntityMob implements IEntitySelector
/*     */ {
/*     */   private int attackTimer;
/*     */   
/*     */   public EntityEnt(World par1World)
/*     */   {
/*  44 */     super(par1World);
/*     */     
/*  46 */     func_70105_a(1.2F, 3.0F);
/*     */     
/*  48 */     func_70661_as().func_75491_a(true);
/*  49 */     func_70661_as().func_75495_e(true);
/*  50 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  51 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, true));
/*  52 */     this.field_70714_bg.func_75776_a(3, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
/*  53 */     this.field_70714_bg.func_75776_a(4, new EntityAIWander(this, 0.6D));
/*  54 */     this.field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  55 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  56 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  57 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, this));
/*  58 */     this.field_70728_aV = 25;
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
/*     */ 
/*     */ 
/*     */   public boolean func_82704_a(Entity entity)
/*     */   {
/*  73 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  78 */     super.func_70088_a();
/*  79 */     this.field_70180_af.func_75682_a(17, "");
/*  80 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public boolean isScreaming()
/*     */   {
/*  85 */     return this.field_70180_af.func_75683_a(16) > 0;
/*     */   }
/*     */   
/*     */   public void setScreaming(boolean par1)
/*     */   {
/*  90 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  95 */     if (func_94056_bM()) {
/*  96 */       return func_94057_bL();
/*     */     }
/*  98 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.ent.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 109 */     super.func_70629_bd();
/*     */     
/* 111 */     if ((!this.field_70170_p.field_72995_K) && (func_70089_S())) {
/* 112 */       if (func_70638_az() != null) {
/* 113 */         setScreaming(true);
/*     */       } else {
/* 115 */         setScreaming(false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 122 */     super.func_110147_ax();
/* 123 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
/* 124 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/* 125 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/* 126 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 131 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_82167_n(Entity par1Entity)
/*     */   {
/* 136 */     super.func_82167_n(par1Entity);
/*     */   }
/*     */   
/* 139 */   EntityPlayer fakePlayer = null;
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 143 */     super.func_70636_d();
/*     */     
/* 145 */     if (this.attackTimer > 0) {
/* 146 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/* 149 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70170_p.field_73012_v.nextInt(300) == 0)) {
/*     */       try {
/* 151 */         int i = MathHelper.func_76128_c(this.field_70165_t);
/* 152 */         int j = MathHelper.func_76128_c(this.field_70163_u) - 1;
/* 153 */         int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */         
/* 155 */         if (((this.fakePlayer == null) || (this.fakePlayer.field_70170_p != this.field_70170_p)) && 
/* 156 */           ((this.field_70170_p instanceof WorldServer))) {
/* 157 */           this.fakePlayer = new net.minecraftforge.common.util.FakePlayer((WorldServer)this.field_70170_p, new GameProfile(UUID.randomUUID(), "[Minecraft]"));
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 162 */         if (this.fakePlayer != null) {
/* 163 */           net.minecraft.item.ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), this.field_70170_p, i, j, k, this.fakePlayer);
/*     */         }
/*     */       }
/*     */       catch (Throwable e) {}
/*     */     }
/*     */     
/*     */ 
/* 170 */     if ((this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D) && (this.field_70146_Z.nextInt(5) == 0)) {
/* 171 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 172 */       int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - this.field_70129_M);
/* 173 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/* 174 */       Block l = this.field_70170_p.func_147439_a(i, j, k);
/*     */       
/* 176 */       if (l != net.minecraft.init.Blocks.field_150350_a) {
/* 177 */         this.field_70170_p.func_72869_a("tilecrack_" + l + "_" + this.field_70170_p.func_72805_g(i, j, k), this.field_70165_t + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, this.field_70121_D.field_72338_b + 0.1D, this.field_70161_v + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70686_a(Class par1Class)
/*     */   {
/* 188 */     return super.func_70686_a(par1Class);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 193 */     super.func_70014_b(par1NBTTagCompound);
/* 194 */     if (getOwnerName() == null) {
/* 195 */       par1NBTTagCompound.func_74778_a("Owner", "");
/*     */     } else {
/* 197 */       par1NBTTagCompound.func_74778_a("Owner", getOwnerName());
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 203 */     super.func_70037_a(par1NBTTagCompound);
/* 204 */     String s = par1NBTTagCompound.func_74779_i("Owner");
/*     */     
/* 206 */     if (s.length() > 0) {
/* 207 */       setOwner(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getOwnerName() {
/* 212 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setOwner(String par1Str) {
/* 216 */     func_110163_bv();
/* 217 */     this.field_70180_af.func_75692_b(17, par1Str);
/*     */   }
/*     */   
/*     */   public EntityPlayer getOwnerEntity() {
/* 221 */     return this.field_70170_p.func_72924_a(getOwnerName());
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 226 */     this.attackTimer = 10;
/* 227 */     this.field_70170_p.func_72960_a(this, (byte)4);
/*     */     
/* 229 */     boolean flag = super.func_70652_k(par1Entity);
/*     */     
/*     */ 
/* 232 */     if (flag) {
/* 233 */       par1Entity.field_70181_x += 0.4000000059604645D;
/*     */     }
/*     */     
/* 236 */     func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/* 237 */     return flag;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 244 */     if (par1 == 4) {
/* 245 */       this.attackTimer = 10;
/* 246 */       func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     } else {
/* 248 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 254 */     Entity entity = par1DamageSource.func_76346_g();
/* 255 */     par2 = Math.min(par2, 15.0F);
/* 256 */     if ((entity != null) && ((entity instanceof EntityLivingBase)) && ((par1DamageSource.field_76373_n == "mob") || (par1DamageSource.field_76373_n == "player")) && (((EntityLivingBase)entity).func_70694_bm() != null) && ((((EntityLivingBase)entity).func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemAxe)))
/*     */     {
/*     */ 
/*     */ 
/* 260 */       par2 *= 3.0F;
/*     */     }
/* 262 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/* 267 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 272 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 277 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 282 */     return "mob.horse.zombie.hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 287 */     return "mob.horse.zombie.death";
/*     */   }
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4)
/*     */   {
/* 292 */     func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 297 */     func_70099_a(Witchery.Items.GENERIC.itemBranchEnt.createStack(), 0.0F);
/* 298 */     func_70099_a(new ItemStack(Witchery.Blocks.SAPLING, 1, this.field_70170_p.field_73012_v.nextInt(3)), 0.0F);
/*     */   }
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 302 */     return (this.field_70180_af.func_75683_a(16) & 0x1) != 0;
/*     */   }
/*     */   
/*     */   public void setPlayerCreated(boolean par1) {
/* 306 */     func_110163_bv();
/* 307 */     byte b0 = this.field_70180_af.func_75683_a(16);
/*     */     
/* 309 */     if (par1) {
/* 310 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 312 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 318 */     super.func_70645_a(par1DamageSource);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 323 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 329 */     return super.func_110161_a(par1EntityLivingData);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityEnt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */