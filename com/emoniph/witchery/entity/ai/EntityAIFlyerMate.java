/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIFlyerMate extends EntityAIBase
/*     */ {
/*     */   private EntityAnimal theAnimal;
/*     */   World theWorld;
/*     */   private EntityAnimal targetMate;
/*     */   int spawnBabyDelay;
/*  20 */   int updateDelay = 0;
/*     */   double moveSpeed;
/*     */   
/*     */   public EntityAIFlyerMate(EntityAnimal par1EntityAnimal, double par2) {
/*  24 */     this.theAnimal = par1EntityAnimal;
/*  25 */     this.theWorld = par1EntityAnimal.field_70170_p;
/*  26 */     this.moveSpeed = par2;
/*  27 */     func_75248_a(1);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a() {
/*  31 */     if (!this.theAnimal.func_70880_s()) {
/*  32 */       return false;
/*     */     }
/*  34 */     this.targetMate = getNearbyMate();
/*  35 */     return this.targetMate != null;
/*     */   }
/*     */   
/*     */   public boolean func_75253_b()
/*     */   {
/*  40 */     return (this.targetMate.func_70089_S()) && (this.targetMate.func_70880_s()) && (this.spawnBabyDelay < 60);
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/*  44 */     this.targetMate = null;
/*  45 */     this.spawnBabyDelay = 0;
/*  46 */     this.updateDelay = 0;
/*     */   }
/*     */   
/*     */   public void func_75246_d() {
/*  50 */     if (--this.updateDelay <= 0) {
/*  51 */       double d0 = this.targetMate.field_70165_t - this.theAnimal.field_70165_t;
/*  52 */       double d1 = this.targetMate.field_70163_u - this.theAnimal.field_70163_u;
/*  53 */       double d2 = this.targetMate.field_70161_v - this.theAnimal.field_70161_v;
/*  54 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*  55 */       d3 = MathHelper.func_76133_a(d3);
/*  56 */       if (isCourseTraversable(this.targetMate.field_70165_t, this.targetMate.field_70163_u, this.targetMate.field_70161_v, d3)) {
/*  57 */         this.theAnimal.field_70159_w += d0 / d3 * 0.25D;
/*  58 */         this.theAnimal.field_70181_x += d1 / d3 * 0.25D;
/*  59 */         this.theAnimal.field_70179_y += d2 / d3 * 0.25D;
/*     */       }
/*  61 */       this.updateDelay = 10;
/*     */     }
/*     */     
/*  64 */     this.theAnimal.field_70761_aq = (this.theAnimal.field_70177_z = -(float)Math.atan2(this.theAnimal.field_70159_w, this.theAnimal.field_70179_y) * 180.0F / 3.1415927F);
/*     */     
/*  66 */     this.spawnBabyDelay += 1;
/*     */     
/*  68 */     if ((this.spawnBabyDelay >= 60) && (this.theAnimal.func_70068_e(this.targetMate) < 9.0D)) {
/*  69 */       spawnBaby();
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/*  74 */     double d4 = (par1 - this.theAnimal.field_70165_t) / par7;
/*  75 */     double d5 = (par3 - this.theAnimal.field_70163_u) / par7;
/*  76 */     double d6 = (par5 - this.theAnimal.field_70161_v) / par7;
/*     */     
/*  78 */     AxisAlignedBB axisalignedbb = this.theAnimal.field_70121_D.func_72329_c();
/*     */     
/*  80 */     for (int i = 1; i < par7; i++) {
/*  81 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/*  83 */       if (!this.theAnimal.field_70170_p.func_72945_a(this.theAnimal, axisalignedbb).isEmpty()) {
/*  84 */         return false;
/*     */       }
/*     */     }
/*     */     
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   private EntityAnimal getNearbyMate() {
/*  92 */     float f = 8.0F;
/*  93 */     List list = this.theWorld.func_72872_a(this.theAnimal.getClass(), this.theAnimal.field_70121_D.func_72314_b(f, f, f));
/*  94 */     double d0 = Double.MAX_VALUE;
/*  95 */     EntityAnimal entityanimal = null;
/*  96 */     Iterator iterator = list.iterator();
/*     */     
/*  98 */     while (iterator.hasNext()) {
/*  99 */       EntityAnimal entityanimal1 = (EntityAnimal)iterator.next();
/*     */       
/* 101 */       if ((this.theAnimal.func_70878_b(entityanimal1)) && (this.theAnimal.func_70068_e(entityanimal1) < d0)) {
/* 102 */         entityanimal = entityanimal1;
/* 103 */         d0 = this.theAnimal.func_70068_e(entityanimal1);
/*     */       }
/*     */     }
/*     */     
/* 107 */     return entityanimal;
/*     */   }
/*     */   
/*     */   private void spawnBaby() {
/* 111 */     EntityAgeable entityageable = this.theAnimal.func_90011_a(this.targetMate);
/*     */     
/* 113 */     if (entityageable != null) {
/* 114 */       this.theAnimal.func_70873_a(6000);
/* 115 */       this.targetMate.func_70873_a(6000);
/* 116 */       this.theAnimal.func_70875_t();
/* 117 */       this.targetMate.func_70875_t();
/* 118 */       entityageable.func_70873_a(41536);
/* 119 */       entityageable.func_70012_b(this.theAnimal.field_70165_t, this.theAnimal.field_70163_u, this.theAnimal.field_70161_v, 0.0F, 0.0F);
/* 120 */       this.theWorld.func_72838_d(entityageable);
/* 121 */       Random random = this.theAnimal.func_70681_au();
/*     */       
/* 123 */       for (int i = 0; i < 7; i++) {
/* 124 */         double d0 = random.nextGaussian() * 0.02D;
/* 125 */         double d1 = random.nextGaussian() * 0.02D;
/* 126 */         double d2 = random.nextGaussian() * 0.02D;
/* 127 */         this.theWorld.func_72869_a("heart", this.theAnimal.field_70165_t + random.nextFloat() * this.theAnimal.field_70130_N * 2.0F - this.theAnimal.field_70130_N, this.theAnimal.field_70163_u + 0.5D + random.nextFloat() * this.theAnimal.field_70131_O, this.theAnimal.field_70161_v + random.nextFloat() * this.theAnimal.field_70130_N * 2.0F - this.theAnimal.field_70130_N, d0, d1, d2);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 132 */       this.theWorld.func_72838_d(new EntityXPOrb(this.theWorld, this.theAnimal.field_70165_t, this.theAnimal.field_70163_u, this.theAnimal.field_70161_v, random.nextInt(7) + 1));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyerMate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */