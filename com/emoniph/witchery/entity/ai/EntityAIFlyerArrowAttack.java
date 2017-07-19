/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class EntityAIFlyerArrowAttack
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityLiving entityHost;
/*     */   private final IRangedAttackMob rangedAttackEntityHost;
/*     */   private EntityLivingBase attackTarget;
/*     */   private int rangedAttackTime;
/*     */   private double entityMoveSpeed;
/*     */   private int field_75318_f;
/*     */   private int field_96561_g;
/*     */   private int maxRangedAttackTime;
/*     */   private float field_96562_i;
/*     */   private float field_82642_h;
/*     */   private static final String __OBFID = "CL_00001609";
/*     */   private int field_75445_i;
/*     */   private int failedPathFindingPenalty;
/*     */   
/*     */   public EntityAIFlyerArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, float par5)
/*     */   {
/*  36 */     this(par1IRangedAttackMob, par2, par4, par4, par5);
/*     */   }
/*     */   
/*     */   public EntityAIFlyerArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, int par5, float par6) {
/*  40 */     this.rangedAttackTime = -1;
/*     */     
/*  42 */     if (!(par1IRangedAttackMob instanceof EntityLivingBase)) {
/*  43 */       throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
/*     */     }
/*  45 */     this.rangedAttackEntityHost = par1IRangedAttackMob;
/*  46 */     this.entityHost = ((EntityLiving)par1IRangedAttackMob);
/*  47 */     this.entityMoveSpeed = par2;
/*  48 */     this.field_96561_g = par4;
/*  49 */     this.maxRangedAttackTime = par5;
/*  50 */     this.field_96562_i = par6;
/*  51 */     this.field_82642_h = (par6 * par6);
/*  52 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a()
/*     */   {
/*  57 */     EntityLivingBase entitylivingbase = this.entityHost.func_70638_az();
/*     */     
/*  59 */     if (entitylivingbase == null) {
/*  60 */       return false;
/*     */     }
/*  62 */     this.attackTarget = entitylivingbase;
/*  63 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_75253_b()
/*     */   {
/*  68 */     return (func_75250_a()) || (!this.entityHost.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/*  72 */     this.attackTarget = null;
/*  73 */     this.field_75318_f = 0;
/*  74 */     this.rangedAttackTime = -1;
/*  75 */     this.field_75445_i = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  83 */     double d0 = this.entityHost.func_70092_e(this.attackTarget.field_70165_t, this.attackTarget.field_70121_D.field_72338_b, this.attackTarget.field_70161_v);
/*  84 */     boolean flag = this.entityHost.func_70635_at().func_75522_a(this.attackTarget);
/*     */     
/*  86 */     if (flag) {
/*  87 */       this.field_75318_f += 1;
/*     */     } else {
/*  89 */       this.field_75318_f = 0;
/*     */     }
/*     */     
/*  92 */     if (d0 > this.field_82642_h)
/*     */     {
/*     */ 
/*  95 */       if (--this.field_75445_i <= 0) {
/*  96 */         this.field_75445_i = (this.failedPathFindingPenalty + 4 + this.entityHost.func_70681_au().nextInt(7));
/*     */         
/*  98 */         double d = this.attackTarget.field_70165_t - this.entityHost.field_70165_t;
/*  99 */         double d1 = this.attackTarget.field_70163_u - this.entityHost.field_70163_u;
/* 100 */         double d2 = this.attackTarget.field_70161_v - this.entityHost.field_70161_v;
/* 101 */         double d3 = d * d + d1 * d1 + d2 * d2;
/* 102 */         d3 = MathHelper.func_76133_a(d3);
/* 103 */         if (isCourseTraversable(this.attackTarget.field_70165_t, this.attackTarget.field_70163_u, this.attackTarget.field_70161_v, d3)) {
/* 104 */           this.entityHost.field_70159_w += d / d3 * 0.15D;
/* 105 */           this.entityHost.field_70181_x += d1 / d3 * 0.15D;
/* 106 */           this.entityHost.field_70179_y += d2 / d3 * 0.15D;
/* 107 */           this.failedPathFindingPenalty = 0;
/*     */         } else {
/* 109 */           this.failedPathFindingPenalty += 10;
/*     */         }
/*     */         
/* 112 */         this.entityHost.field_70761_aq = (this.entityHost.field_70177_z = -(float)Math.atan2(this.entityHost.field_70159_w, this.entityHost.field_70179_y) * 180.0F / 3.1415927F);
/*     */         
/* 114 */         this.entityHost.func_70661_as().func_75497_a(this.attackTarget, this.entityMoveSpeed);
/*     */       }
/*     */     }
/*     */     
/* 118 */     this.entityHost.func_70671_ap().func_75651_a(this.attackTarget, 30.0F, 30.0F);
/*     */     
/*     */ 
/* 121 */     if (--this.rangedAttackTime == 0) {
/* 122 */       if ((d0 > this.field_82642_h) || (!flag)) {
/* 123 */         return;
/*     */       }
/*     */       
/* 126 */       float f = MathHelper.func_76133_a(d0) / this.field_96562_i;
/* 127 */       float f1 = f;
/*     */       
/* 129 */       if (f < 0.1F) {
/* 130 */         f1 = 0.1F;
/*     */       }
/*     */       
/* 133 */       if (f1 > 1.0F) {
/* 134 */         f1 = 1.0F;
/*     */       }
/*     */       
/* 137 */       this.rangedAttackEntityHost.func_82196_d(this.attackTarget, f1);
/* 138 */       this.rangedAttackTime = MathHelper.func_76141_d(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/*     */     }
/* 140 */     else if (this.rangedAttackTime < 0) {
/* 141 */       float f = MathHelper.func_76133_a(d0) / this.field_96562_i;
/* 142 */       this.rangedAttackTime = MathHelper.func_76141_d(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
/*     */   {
/* 148 */     double d4 = (par1 - this.attackTarget.field_70165_t) / par7;
/* 149 */     double d5 = (par3 - this.attackTarget.field_70163_u) / par7;
/* 150 */     double d6 = (par5 - this.attackTarget.field_70161_v) / par7;
/*     */     
/* 152 */     AxisAlignedBB axisalignedbb = this.attackTarget.field_70121_D.func_72329_c();
/*     */     
/* 154 */     for (int i = 1; i < par7; i++) {
/* 155 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 157 */       if (!this.attackTarget.field_70170_p.func_72945_a(this.attackTarget, axisalignedbb).isEmpty()) {
/* 158 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 162 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyerArrowAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */