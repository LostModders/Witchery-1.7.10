/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySpectre extends EntitySummonedUndead
/*     */ {
/*     */   public EntitySpectre(World par1World)
/*     */   {
/*  31 */     super(par1World);
/*  32 */     func_70661_as().func_75491_a(true);
/*  33 */     this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
/*  34 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
/*  35 */     this.field_70714_bg.func_75776_a(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
/*  36 */     this.field_70714_bg.func_75776_a(4, new EntityAIWander(this, 1.0D));
/*  37 */     this.field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  38 */     this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
/*  39 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
/*  40 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  46 */     super.func_110147_ax();
/*  47 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*  48 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/*  49 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  54 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  60 */     int i = super.func_70658_aO() + 2;
/*     */     
/*  62 */     if (i > 20) {
/*  63 */       i = 20;
/*     */     }
/*     */     
/*  66 */     return i;
/*     */   }
/*     */   
/*     */   protected boolean func_70650_aV()
/*     */   {
/*  71 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  76 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  82 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/*  87 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*  88 */     int i = 0;
/*     */     
/*  90 */     if ((par1Entity instanceof EntityLivingBase)) {
/*  91 */       EntityLivingBase living = (EntityLivingBase)par1Entity;
/*  92 */       float maxHealth = living.func_110138_aP();
/*  93 */       f = Math.max(maxHealth * 0.15F, 1.0F);
/*     */     }
/*     */     
/*  96 */     if ((par1Entity instanceof EntityLivingBase))
/*     */     {
/*  98 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)par1Entity);
/*  99 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)par1Entity);
/*     */     }
/*     */     
/* 102 */     boolean flag = com.emoniph.witchery.util.EntityUtil.touchOfDeath(par1Entity, this, f);
/*     */     
/*     */ 
/* 105 */     if (flag)
/*     */     {
/* 107 */       if (i > 0)
/*     */       {
/* 109 */         par1Entity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/* 110 */         this.field_70159_w *= 0.6D;
/* 111 */         this.field_70179_y *= 0.6D;
/*     */       }
/*     */       
/* 114 */       int j = EnchantmentHelper.func_90036_a(this);
/*     */       
/* 116 */       if (j > 0)
/*     */       {
/* 118 */         par1Entity.func_70015_d(j * 4);
/*     */       }
/*     */     }
/*     */     
/* 122 */     return flag;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 127 */     return "witchery:mob.spectre.spectre_say";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 132 */     return "witchery:mob.spectre.spectre_die";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 137 */     return "witchery:mob.spectre.spectre_die";
/*     */   }
/*     */   
/*     */   protected void func_82164_bB()
/*     */   {
/* 142 */     if (this.field_70146_Z.nextFloat() < 0.15F * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v)) {
/* 143 */       int i = this.field_70146_Z.nextInt(2);
/* 144 */       float f = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.1F : 0.25F;
/*     */       
/* 146 */       if (this.field_70146_Z.nextFloat() < 0.095F) {
/* 147 */         i++;
/*     */       }
/*     */       
/* 150 */       if (this.field_70146_Z.nextFloat() < 0.095F) {
/* 151 */         i++;
/*     */       }
/*     */       
/* 154 */       if (this.field_70146_Z.nextFloat() < 0.095F) {
/* 155 */         i++;
/*     */       }
/*     */       
/* 158 */       for (int j = 3; j >= 2; j--) {
/* 159 */         ItemStack itemstack = func_130225_q(j);
/*     */         
/* 161 */         if ((j < 3) && (this.field_70146_Z.nextFloat() < f)) {
/*     */           break;
/*     */         }
/*     */         
/* 165 */         if (itemstack == null) {
/* 166 */           Item item = func_82161_a(j + 1, i);
/*     */           
/* 168 */           if (item != null) {
/* 169 */             func_70062_b(j + 1, new ItemStack(item));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 178 */     if (func_94056_bM()) {
/* 179 */       return func_94057_bL();
/*     */     }
/* 181 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.spectre.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 187 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 193 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 198 */     Object par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
/*     */     
/* 200 */     func_82164_bB();
/* 201 */     func_82162_bC();
/*     */     
/* 203 */     setObscured(true);
/*     */     
/*     */ 
/*     */ 
/* 207 */     return (IEntityLivingData)par1EntityLivingData1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntitySpectre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */