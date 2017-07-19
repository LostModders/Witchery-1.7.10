/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.ai.EntityAISit;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityFlyingTameable extends EntityTameable
/*     */ {
/*  15 */   protected EntityAISit field_70911_d = new EntityAISit(this);
/*     */   
/*  17 */   public ItemStack waypoint = null;
/*     */   public double homeX;
/*     */   public double homeY;
/*     */   public double homeZ;
/*     */   
/*     */   public com.emoniph.witchery.util.Waypoint getWaypoint() {
/*  23 */     return new com.emoniph.witchery.util.Waypoint(this.field_70170_p, this.waypoint, this.homeX, this.homeY, this.homeZ);
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/*  28 */     super.func_70014_b(nbtRoot);
/*  29 */     if (this.waypoint != null) {
/*  30 */       NBTTagCompound nbtWaypoint = new NBTTagCompound();
/*  31 */       this.waypoint.func_77955_b(nbtWaypoint);
/*  32 */       nbtRoot.func_74782_a("WITCWaypoint", nbtWaypoint);
/*     */     }
/*     */     
/*  35 */     nbtRoot.func_74780_a("HomeX", this.homeX);
/*  36 */     nbtRoot.func_74780_a("HomeY", this.homeY);
/*  37 */     nbtRoot.func_74780_a("HomeZ", this.homeZ);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/*  42 */     super.func_70037_a(nbtRoot);
/*     */     
/*  44 */     if (nbtRoot.func_74764_b("WITCWaypoint")) {
/*  45 */       NBTTagCompound nbtWaypoint = nbtRoot.func_74775_l("WITCWaypoint");
/*  46 */       this.waypoint = ItemStack.func_77949_a(nbtWaypoint);
/*     */     } else {
/*  48 */       this.waypoint = null;
/*     */     }
/*     */     
/*  51 */     this.homeX = nbtRoot.func_74769_h("HomeX");
/*  52 */     this.homeY = nbtRoot.func_74769_h("HomeY");
/*  53 */     this.homeZ = nbtRoot.func_74769_h("HomeZ");
/*     */   }
/*     */   
/*     */   public EntityFlyingTameable(World par1World) {
/*  57 */     super(par1World);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */   protected void func_70064_a(double par1, boolean par3) {}
/*     */   
/*     */ 
/*     */   public void func_70612_e(float par1, float par2)
/*     */   {
/*  70 */     if (func_70090_H()) {
/*  71 */       func_70060_a(par1, par2, 0.02F);
/*  72 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  73 */       this.field_70159_w *= 0.800000011920929D;
/*  74 */       this.field_70181_x *= 0.800000011920929D;
/*  75 */       this.field_70179_y *= 0.800000011920929D;
/*  76 */     } else if (func_70058_J()) {
/*  77 */       func_70060_a(par1, par2, 0.02F);
/*  78 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  79 */       this.field_70159_w *= 0.5D;
/*  80 */       this.field_70181_x *= 0.5D;
/*  81 */       this.field_70179_y *= 0.5D;
/*     */     } else {
/*  83 */       float f2 = 0.91F;
/*     */       
/*  85 */       if (this.field_70122_E) {
/*  86 */         f2 = 0.54600006F;
/*  87 */         Block i = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
/*     */         
/*     */ 
/*  90 */         if (i != Blocks.field_150350_a) {
/*  91 */           f2 = i.field_149765_K * 0.91F;
/*     */         }
/*     */       }
/*     */       
/*  95 */       float f3 = 0.16277136F / (f2 * f2 * f2);
/*  96 */       func_70060_a(par1, par2, this.field_70122_E ? 0.1F * f3 : 0.02F);
/*  97 */       f2 = 0.91F;
/*     */       
/*  99 */       if (this.field_70122_E) {
/* 100 */         f2 = 0.54600006F;
/* 101 */         Block j = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
/*     */         
/*     */ 
/* 104 */         if (j != Blocks.field_150350_a) {
/* 105 */           f2 = j.field_149765_K * 0.91F;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 111 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       
/* 113 */       this.field_70159_w *= f2;
/* 114 */       this.field_70181_x *= f2;
/* 115 */       this.field_70179_y *= f2;
/*     */     }
/*     */     
/* 118 */     this.field_70722_aY = this.field_70721_aZ;
/* 119 */     double d0 = this.field_70165_t - this.field_70169_q;
/* 120 */     double d1 = this.field_70161_v - this.field_70166_s;
/* 121 */     float f4 = MathHelper.func_76133_a(d0 * d0 + d1 * d1) * 4.0F;
/*     */     
/* 123 */     if (f4 > 1.0F) {
/* 124 */       f4 = 1.0F;
/*     */     }
/*     */     
/* 127 */     this.field_70721_aZ += (f4 - this.field_70721_aZ) * 0.4F;
/* 128 */     this.field_70754_ba += this.field_70721_aZ;
/*     */   }
/*     */   
/*     */   public boolean func_70617_f_()
/*     */   {
/* 133 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityFlyingTameable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */