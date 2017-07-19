/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIFlyerAttackOnCollide
/*     */   extends EntityAIBase
/*     */ {
/*     */   World worldObj;
/*     */   EntityCreature attacker;
/*     */   int attackTick;
/*     */   double speedTowardsTarget;
/*     */   boolean longMemory;
/*     */   PathEntity entityPathEntity;
/*     */   Class classTarget;
/*     */   private int field_75445_i;
/*     */   private int failedPathFindingPenalty;
/*     */   
/*     */   public EntityAIFlyerAttackOnCollide(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par5)
/*     */   {
/*  39 */     this(par1EntityCreature, par3, par5);
/*  40 */     this.classTarget = par2Class;
/*     */   }
/*     */   
/*     */   public EntityAIFlyerAttackOnCollide(EntityCreature par1EntityCreature, double par2, boolean par4)
/*     */   {
/*  45 */     this.attacker = par1EntityCreature;
/*  46 */     this.worldObj = par1EntityCreature.field_70170_p;
/*  47 */     this.speedTowardsTarget = par2;
/*  48 */     this.longMemory = par4;
/*  49 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  57 */     EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
/*     */     
/*  59 */     if (entitylivingbase == null)
/*     */     {
/*  61 */       return false;
/*     */     }
/*  63 */     if (!entitylivingbase.func_70089_S())
/*     */     {
/*  65 */       return false;
/*     */     }
/*  67 */     if ((this.classTarget != null) && (!this.classTarget.isAssignableFrom(entitylivingbase.getClass())))
/*     */     {
/*  69 */       return false;
/*     */     }
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
/*  81 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  91 */     EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
/*  92 */     return !this.longMemory ? false : !this.attacker.func_70661_as().func_75500_f() ? true : !entitylivingbase.func_70089_S() ? false : entitylivingbase == null ? false : this.attacker.func_110176_b(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70163_u), MathHelper.func_76128_c(entitylivingbase.field_70161_v));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 101 */     this.attacker.func_70661_as().func_75484_a(this.entityPathEntity, this.speedTowardsTarget);
/* 102 */     this.field_75445_i = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 110 */     this.attacker.func_70661_as().func_75499_g();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 118 */     EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
/* 119 */     this.attacker.func_70671_ap().func_75651_a(entitylivingbase, 30.0F, 30.0F);
/*     */     
/* 121 */     if (((this.longMemory) || (this.attacker.func_70635_at().func_75522_a(entitylivingbase))) && (--this.field_75445_i <= 0))
/*     */     {
/* 123 */       this.field_75445_i = (this.failedPathFindingPenalty + 4 + this.attacker.func_70681_au().nextInt(7));
/*     */       
/* 125 */       double d0 = entitylivingbase.field_70165_t - this.attacker.field_70165_t;
/* 126 */       double d1 = entitylivingbase.field_70163_u - this.attacker.field_70163_u;
/* 127 */       double d2 = entitylivingbase.field_70161_v - this.attacker.field_70161_v;
/* 128 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 129 */       d3 = MathHelper.func_76133_a(d3);
/* 130 */       if (isCourseTraversable(entitylivingbase.field_70165_t, entitylivingbase.field_70163_u, entitylivingbase.field_70161_v, d3)) {
/* 131 */         this.attacker.field_70159_w += d0 / d3 * 0.15D;
/* 132 */         this.attacker.field_70181_x += d1 / d3 * 0.15D;
/* 133 */         this.attacker.field_70179_y += d2 / d3 * 0.15D;
/* 134 */         this.failedPathFindingPenalty = 0;
/*     */       } else {
/* 136 */         this.failedPathFindingPenalty += 10;
/*     */       }
/*     */       
/* 139 */       this.attacker.field_70761_aq = (this.attacker.field_70177_z = -(float)Math.atan2(this.attacker.field_70159_w, this.attacker.field_70179_y) * 180.0F / 3.1415927F);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */     this.attackTick = Math.max(this.attackTick - 1, 0);
/* 160 */     double d0 = this.attacker.field_70130_N * 2.0F * this.attacker.field_70130_N * 2.0F + entitylivingbase.field_70130_N;
/*     */     
/* 162 */     if (this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v) <= d0)
/*     */     {
/* 164 */       if (this.attackTick <= 0)
/*     */       {
/* 166 */         this.attackTick = 20;
/*     */         
/* 168 */         if (this.attacker.func_70694_bm() != null)
/*     */         {
/* 170 */           this.attacker.func_71038_i();
/*     */         }
/*     */         
/* 173 */         this.attacker.func_70652_k(entitylivingbase);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/* 179 */     double d4 = (par1 - this.attacker.field_70165_t) / par7;
/* 180 */     double d5 = (par3 - this.attacker.field_70163_u) / par7;
/* 181 */     double d6 = (par5 - this.attacker.field_70161_v) / par7;
/*     */     
/* 183 */     AxisAlignedBB axisalignedbb = this.attacker.field_70121_D.func_72329_c();
/*     */     
/* 185 */     for (int i = 1; i < par7; i++) {
/* 186 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 188 */       if (!this.attacker.field_70170_p.func_72945_a(this.attacker, axisalignedbb).isEmpty()) {
/* 189 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 193 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyerAttackOnCollide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */