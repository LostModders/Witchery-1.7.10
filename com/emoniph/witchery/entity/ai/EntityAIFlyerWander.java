/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIFlyerWander extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private double xPosition;
/*     */   private double yPosition;
/*     */   private double zPosition;
/*     */   private double speed;
/*     */   World worldObj;
/*     */   public int courseChangeCooldown;
/*     */   public double waypointX;
/*     */   public double waypointY;
/*     */   public double waypointZ;
/*     */   public double fleeDistance;
/*     */   EntityLiving living;
/*     */   
/*     */   public EntityAIFlyerWander(EntityLiving par1EntityCreature, double par2, double fleeDistance)
/*     */   {
/*  25 */     this.living = par1EntityCreature;
/*  26 */     this.worldObj = this.living.field_70170_p;
/*  27 */     this.speed = par2;
/*  28 */     this.fleeDistance = fleeDistance;
/*  29 */     func_75248_a(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  36 */     boolean isTame = ((this.living instanceof EntityTameable)) && (((EntityTameable)this.living).func_70909_n());
/*  37 */     if ((!isTame) && (this.living.field_70170_p.func_72890_a(this.living, this.fleeDistance) != null))
/*  38 */       return true;
/*  39 */     if (this.living.func_70654_ax() >= 100)
/*  40 */       return false;
/*  41 */     if ((this.living.func_70681_au().nextInt(this.living.field_70170_p.field_73011_w.isDaytime() ? 300 : 100) != 0) || (((this.living instanceof EntityTameable)) && (((EntityTameable)this.living).func_70906_o()))) {
/*  42 */       return false;
/*     */     }
/*  44 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  52 */     return (((this.living instanceof EntityTameable)) && (!((EntityTameable)this.living).func_70906_o())) || (this.living.func_70681_au().nextInt(40) != 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  66 */     double d0 = this.waypointX - this.living.field_70165_t;
/*  67 */     double d1 = this.waypointY - this.living.field_70163_u;
/*  68 */     double d2 = this.waypointZ - this.living.field_70161_v;
/*  69 */     double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */     
/*  71 */     if ((d3 < 1.0D) || (d3 > 3600.0D)) {
/*  72 */       float distance = ((this.living instanceof EntityTameable)) && (((EntityTameable)this.living).func_70909_n()) ? 2.0F : 6.0F;
/*  73 */       this.waypointX = (this.living.field_70165_t + (this.worldObj.field_73012_v.nextFloat() * 8.0F - 4.0F) * distance);
/*  74 */       this.waypointY = (this.living.field_70163_u + (this.worldObj.field_73012_v.nextFloat() * 2.0F - 1.0F) * distance);
/*  75 */       this.waypointZ = (this.living.field_70161_v + (this.worldObj.field_73012_v.nextFloat() * 8.0F - 4.0F) * distance);
/*     */     }
/*     */     
/*  78 */     if (this.courseChangeCooldown-- <= 0) {
/*  79 */       this.courseChangeCooldown += this.worldObj.field_73012_v.nextInt(2) + 2;
/*  80 */       d3 = net.minecraft.util.MathHelper.func_76133_a(d3);
/*     */       
/*  82 */       if (isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
/*  83 */         this.living.field_70159_w += d0 / d3 * 0.1D;
/*  84 */         this.living.field_70181_x += d1 / d3 * 0.1D;
/*  85 */         this.living.field_70179_y += d2 / d3 * 0.1D;
/*     */       } else {
/*  87 */         this.waypointX = this.living.field_70165_t;
/*  88 */         this.waypointY = this.living.field_70163_u;
/*  89 */         this.waypointZ = this.living.field_70161_v;
/*     */       }
/*     */     }
/*     */     
/*  93 */     this.living.field_70761_aq = (this.living.field_70177_z = -(float)Math.atan2(this.living.field_70159_w, this.living.field_70179_y) * 180.0F / 3.1415927F);
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/*  97 */     double d4 = (par1 - this.living.field_70165_t) / par7;
/*  98 */     double d5 = (par3 - this.living.field_70163_u) / par7;
/*  99 */     double d6 = (par5 - this.living.field_70161_v) / par7;
/*     */     
/* 101 */     AxisAlignedBB axisalignedbb = this.living.field_70121_D.func_72329_c();
/*     */     
/* 103 */     for (int i = 1; i < par7; i++) {
/* 104 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 106 */       if (!this.living.field_70170_p.func_72945_a(this.living, axisalignedbb).isEmpty()) {
/* 107 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 111 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyerWander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */