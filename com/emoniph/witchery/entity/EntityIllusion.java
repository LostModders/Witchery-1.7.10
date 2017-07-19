/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.network.PacketSound;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityIllusion extends EntityMob
/*     */ {
/*     */   public EntityIllusion(World world)
/*     */   {
/*  25 */     super(world);
/*  26 */     this.field_70178_ae = true;
/*  27 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  28 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, false));
/*  29 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 0.8D));
/*  30 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  31 */     this.field_70714_bg.func_75776_a(5, new EntityAILookIdle(this));
/*  32 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/*  38 */     if (func_94056_bM()) {
/*  39 */       return func_94057_bL();
/*     */     }
/*  41 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.illusion.name");
/*     */   }
/*     */   
/*     */   protected SoundEffect getFakeLivingSound()
/*     */   {
/*  46 */     return SoundEffect.NONE;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  51 */     super.func_110147_ax();
/*  52 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*  53 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/*  63 */     return super.func_70627_aG() * 2;
/*     */   }
/*     */   
/*     */   public net.minecraft.entity.EntityLivingBase func_70638_az()
/*     */   {
/*  68 */     return this.field_70170_p.func_72924_a(getVictimName());
/*     */   }
/*     */   
/*     */   public int func_82143_as()
/*     */   {
/*  73 */     return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  78 */     super.func_70088_a();
/*  79 */     this.field_70180_af.func_75682_a(17, "");
/*  80 */     this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  85 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/*  87 */     if (getVictimName() == null) {
/*  88 */       par1NBTTagCompound.func_74778_a("Victim", "");
/*     */     } else {
/*  90 */       par1NBTTagCompound.func_74778_a("Victim", getVictimName());
/*     */     }
/*     */     
/*  93 */     par1NBTTagCompound.func_74768_a("IllusionType", getIllusionType());
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  98 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/* 100 */     String s = par1NBTTagCompound.func_74779_i("Victim");
/*     */     
/* 102 */     if (s.length() > 0) {
/* 103 */       setVictim(s);
/*     */     }
/*     */     
/* 106 */     setIllusionType(par1NBTTagCompound.func_74762_e("IllusionType"));
/*     */   }
/*     */   
/*     */   public String getVictimName() {
/* 110 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setVictim(String par1Str) {
/* 114 */     this.field_70180_af.func_75692_b(17, par1Str);
/*     */   }
/*     */   
/*     */   public int getIllusionType() {
/* 118 */     return this.field_70180_af.func_75683_a(18);
/*     */   }
/*     */   
/*     */   public void setIllusionType(int par1) {
/* 122 */     this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)par1));
/*     */   }
/*     */   
/* 125 */   private EntityPlayer victimPlayer = null;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 129 */     super.func_70071_h_();
/* 130 */     if (!this.field_70170_p.field_72995_K) {
/* 131 */       if (this.field_70170_p.field_73012_v.nextInt(15) == 0) {
/* 132 */         float newHealth = func_110143_aJ() - 1.0F;
/* 133 */         if (newHealth <= 0.5D) {
/* 134 */           func_70106_y();
/*     */         } else {
/* 136 */           func_70606_j(newHealth);
/*     */         }
/*     */       }
/* 139 */       if (this.field_70170_p.field_73012_v.nextInt(40) == 0) {
/* 140 */         SoundEffect sound = getFakeLivingSound();
/* 141 */         if (this.victimPlayer == null) {
/* 142 */           this.victimPlayer = this.field_70170_p.func_72924_a(getVictimName());
/*     */         }
/* 144 */         if ((this.victimPlayer != null) && (sound != null) && (sound != SoundEffect.NONE) && (this.victimPlayer.func_70068_e(this) < 64.0D)) {
/* 145 */           com.emoniph.witchery.Witchery.packetPipeline.sendTo(new PacketSound(sound, this, 1.0F, 1.0F), this.victimPlayer);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 153 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 158 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity entity)
/*     */   {
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/* 173 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityIllusion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */