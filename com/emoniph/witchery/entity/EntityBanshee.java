/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityBanshee extends EntitySummonedUndead
/*     */ {
/*     */   public EntityBanshee(World par1World)
/*     */   {
/*  30 */     super(par1World);
/*  31 */     func_70661_as().func_75491_a(true);
/*  32 */     func_70661_as().func_75504_d(true);
/*  33 */     func_70661_as().func_75498_b(true);
/*  34 */     this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
/*  35 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 0.3D, false));
/*  36 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
/*  37 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  38 */     this.field_70714_bg.func_75776_a(5, new EntityAILookIdle(this));
/*  39 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
/*  40 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  45 */     super.func_110147_ax();
/*  46 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*  47 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2D);
/*  48 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  53 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   protected boolean func_70650_aV()
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  63 */     super.func_70636_d();
/*     */     
/*  65 */     boolean startedScreaming = false;
/*  66 */     if ((!this.field_70170_p.field_72995_K) && ((TimeUtil.secondsElapsed(5, this.field_70173_aa)) || ((isScreaming()) && (TimeUtil.ticksElapsed(20, this.field_70173_aa))))) {
/*  67 */       double RADIUS = 6.0D;
/*  68 */       double RADIUS_SQ = 36.0D;
/*  69 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 6.0D, this.field_70163_u - 6.0D, this.field_70161_v - 6.0D, this.field_70165_t + 6.0D, this.field_70163_u + 6.0D, this.field_70161_v + 6.0D);
/*  70 */       List players = this.field_70170_p.func_72872_a(EntityLivingBase.class, bounds);
/*  71 */       boolean playersFound = false;
/*  72 */       for (Object obj : players) {
/*  73 */         EntityLivingBase player = (EntityLivingBase)obj;
/*  74 */         if ((func_70068_e(player) <= 36.0D) && ((player == func_70638_az()) || (player == this.field_70789_a) || ((player instanceof EntityPlayer)))) {
/*  75 */           playersFound = true;
/*  76 */           if (!isScreaming()) {
/*  77 */             setScreaming(true);
/*  78 */             startedScreaming = true;
/*     */           }
/*     */           
/*  81 */           if ((!(player instanceof EntityPlayer)) || 
/*  82 */             (!com.emoniph.witchery.item.ItemEarmuffs.isHelmWorn((EntityPlayer)player)))
/*     */           {
/*     */ 
/*     */ 
/*  86 */             float maxHealth = player.func_110138_aP();
/*  87 */             flag = com.emoniph.witchery.util.EntityUtil.touchOfDeath(player, this, Math.max(0.1F * maxHealth, 1.0F));
/*     */           }
/*     */         }
/*     */       }
/*     */       boolean flag;
/*  92 */       if ((!playersFound) && (isScreaming())) {
/*  93 */         setScreaming(false);
/*     */       }
/*     */     }
/*     */     
/*  97 */     if (((startedScreaming) || (TimeUtil.secondsElapsed(3, this.field_70173_aa))) && (isScreaming())) {
/*  98 */       func_85030_a("witchery:mob.banshee.banshee_scream", 1.0F, this.field_70170_p.field_73012_v.nextFloat() * 0.3F + 0.7F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 105 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 110 */     boolean flag = super.func_70652_k(par1Entity);
/*     */     
/* 112 */     return flag;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 122 */     return "witchery:mob.spectre.spectre_hit";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 127 */     return "witchery:mob.spectre.spectre_hit";
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 133 */     if (func_94056_bM()) {
/* 134 */       return func_94057_bL();
/*     */     }
/* 136 */     return StatCollector.func_74838_a("entity.witchery.banshee.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 142 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 148 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 153 */     Object par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
/*     */     
/* 155 */     return (IEntityLivingData)par1EntityLivingData1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityBanshee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */