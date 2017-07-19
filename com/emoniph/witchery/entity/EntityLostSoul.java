/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerAttackOnCollide;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
/*     */ import com.emoniph.witchery.entity.ai.EntityAISitAndStay;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityLostSoul
/*     */   extends EntitySpirit
/*     */ {
/*  34 */   private int timeToLive = -1;
/*     */   
/*     */   public void setTimeToLive(int i) {
/*  37 */     this.timeToLive = i;
/*     */   }
/*     */   
/*     */   public boolean isTemp() {
/*  41 */     return this.timeToLive != -1;
/*     */   }
/*     */   
/*     */   public EntityLostSoul(World world) {
/*  45 */     super(world);
/*  46 */     this.field_70714_bg.field_75782_a.clear();
/*  47 */     this.field_70715_bh.field_75782_a.clear();
/*  48 */     this.field_70714_bg.func_75776_a(1, new EntityAISitAndStay(this));
/*  49 */     this.field_70714_bg.func_75776_a(2, new EntityAIFlyerAttackOnCollide(this, 1.0D, true));
/*  50 */     this.field_70714_bg.func_75776_a(9, new EntityAIFlyerLand(this, 0.8D, true));
/*     */     
/*  52 */     this.field_70714_bg.func_75776_a(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F, 0.2F));
/*     */     
/*  54 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/*  58 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/*  60 */     par1NBTTagCompound.func_74768_a("FeatherColor", getFeatherColor());
/*  61 */     par1NBTTagCompound.func_74774_a("SoulType", (byte)getSoulType());
/*  62 */     par1NBTTagCompound.func_74768_a("SuicideIn", this.timeToLive);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/*  66 */     super.func_70037_a(par1NBTTagCompound);
/*  67 */     if (par1NBTTagCompound.func_74764_b("FeatherColor")) {
/*  68 */       setFeatherColor(par1NBTTagCompound.func_74762_e("FeatherColor"));
/*     */     }
/*     */     
/*  71 */     if (par1NBTTagCompound.func_74764_b("SoulType")) {
/*  72 */       setSoulType(par1NBTTagCompound.func_74771_c("SoulType"));
/*     */     }
/*     */     
/*  75 */     if (par1NBTTagCompound.func_74764_b("SuicideIn")) {
/*  76 */       this.timeToLive = par1NBTTagCompound.func_74762_e("SuicideIn");
/*     */     } else {
/*  78 */       this.timeToLive = -1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70110_aj() {}
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  88 */     super.func_70088_a();
/*  89 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(this.field_70170_p.field_73012_v.nextInt(3)));
/*  90 */     switch (getSoulType()) {
/*     */     case 0: 
/*  92 */       setFeatherColor(16711680);
/*  93 */       break;
/*     */     case 1: 
/*  95 */       setFeatherColor(65280);
/*  96 */       break;
/*     */     case 2: 
/*  98 */       setFeatherColor(255);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getSoulType()
/*     */   {
/* 104 */     return this.field_70180_af.func_75679_c(22);
/*     */   }
/*     */   
/*     */   public void setSoulType(int par1) {
/* 108 */     this.field_70180_af.func_75692_b(22, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 113 */     super.func_110147_ax();
/* 114 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/* 115 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/*     */     
/* 117 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70652_k(Entity targetEntity)
/*     */   {
/* 123 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 124 */     int i = 0;
/*     */     
/* 126 */     if ((targetEntity instanceof EntityLivingBase)) {
/* 127 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)targetEntity);
/* 128 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)targetEntity);
/*     */     }
/*     */     
/* 131 */     DamageSource source = null;
/* 132 */     if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
/* 133 */       switch (getSoulType()) {
/*     */       case 0: 
/* 135 */         source = DamageSource.field_76372_a;
/* 136 */         break;
/*     */       case 1: 
/* 138 */         source = DamageSource.func_76358_a(this);
/* 139 */         break;
/*     */       case 2: 
/* 141 */         source = DamageSource.field_76376_m;
/*     */       }
/*     */       
/*     */     }
/* 145 */     if (source == null) {
/* 146 */       source = DamageSource.func_76358_a(this);
/*     */     }
/*     */     
/* 149 */     boolean flag = targetEntity.func_70097_a(source, f);
/*     */     
/* 151 */     if (flag) {
/* 152 */       if (i > 0) {
/* 153 */         targetEntity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/*     */         
/* 155 */         this.field_70159_w *= 0.6D;
/* 156 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 159 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 161 */       if (j > 0) {
/* 162 */         targetEntity.func_70015_d(j * 4);
/*     */       }
/*     */       
/* 165 */       if ((targetEntity instanceof EntityLivingBase)) {
/* 166 */         EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, this);
/*     */       }
/*     */       
/* 169 */       EnchantmentHelper.func_151385_b(this, targetEntity);
/*     */     }
/*     */     
/* 172 */     return flag;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage)
/*     */   {
/* 177 */     float MAX_DAMAGE = 15.0F;
/* 178 */     switch (getSoulType()) {
/*     */     case 0: 
/* 180 */       if ((source.func_76347_k()) || (source.func_94541_c())) {
/* 181 */         return super.func_70097_a(source, Math.min(damage, 15.0F));
/*     */       }
/*     */       break;
/*     */     case 1: 
/* 185 */       if ((!source.func_76352_a()) && (!source.func_82725_o()) && (!source.func_76347_k()) && (!source.func_94541_c()) && (source != DamageSource.field_76368_d) && (source != DamageSource.field_76367_g) && (source != DamageSource.field_76369_e) && (source != DamageSource.field_82727_n))
/*     */       {
/* 187 */         return super.func_70097_a(source, Math.min(damage, 15.0F));
/*     */       }
/*     */       break;
/*     */     case 2: 
/* 191 */       if (source.func_82725_o()) {
/* 192 */         return super.func_70097_a(source, Math.min(damage, 15.0F));
/*     */       }
/*     */       break;
/*     */     }
/* 196 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70629_bd()
/*     */   {
/* 202 */     super.func_70629_bd();
/* 203 */     if ((!this.field_70170_p.field_72995_K) && (this.timeToLive != -1) && 
/* 204 */       (--this.timeToLive <= 0)) {
/* 205 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 212 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 217 */     if (func_94056_bM()) {
/* 218 */       return func_94057_bL();
/*     */     }
/* 220 */     return StatCollector.func_74838_a("entity.witchery.lostsoul.name");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityLostSoul.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */