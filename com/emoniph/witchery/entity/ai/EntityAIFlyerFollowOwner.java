/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import com.emoniph.witchery.familiar.Familiar;
/*     */ import com.emoniph.witchery.familiar.Familiar.FamiliarOwner;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIFlyerFollowOwner extends EntityAIBase
/*     */ {
/*     */   private EntityTameable thePet;
/*     */   private EntityLivingBase theOwner;
/*     */   World theWorld;
/*     */   private double field_75336_f;
/*     */   private int field_75343_h;
/*     */   float maxDist;
/*     */   float minDist;
/*     */   private boolean field_75344_i;
/*     */   
/*     */   public EntityAIFlyerFollowOwner(EntityTameable par1EntityTameable, double par2, float par4, float par5)
/*     */   {
/*  28 */     this.thePet = par1EntityTameable;
/*  29 */     this.theWorld = par1EntityTameable.field_70170_p;
/*  30 */     this.field_75336_f = par2;
/*  31 */     this.minDist = par4;
/*  32 */     this.maxDist = par5;
/*  33 */     func_75248_a(1);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a()
/*     */   {
/*  38 */     EntityLivingBase entitylivingbase = this.thePet.func_70902_q();
/*  39 */     if ((entitylivingbase == null) && (Familiar.couldBeFamiliar(this.thePet))) {
/*  40 */       entitylivingbase = Familiar.getOwnerForFamiliar(this.thePet).getCurrentOwner();
/*     */     }
/*     */     
/*  43 */     if (entitylivingbase == null)
/*  44 */       return false;
/*  45 */     if (this.thePet.func_70906_o())
/*  46 */       return false;
/*  47 */     if ((this.thePet.field_71093_bK != entitylivingbase.field_71093_bK) || (this.thePet.func_70068_e(entitylivingbase) < this.minDist * this.minDist)) {
/*  48 */       return false;
/*     */     }
/*  50 */     this.theOwner = entitylivingbase;
/*  51 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_75253_b()
/*     */   {
/*  56 */     return (this.thePet.func_70068_e(this.theOwner) > this.maxDist * this.maxDist) && (!this.thePet.func_70906_o());
/*     */   }
/*     */   
/*     */   public void func_75249_e() {
/*  60 */     this.field_75343_h = 0;
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/*  64 */     this.theOwner = null;
/*     */   }
/*     */   
/*     */   public void func_75246_d() {
/*  68 */     if ((!this.thePet.func_70906_o()) && 
/*  69 */       (--this.field_75343_h <= 0)) {
/*  70 */       this.field_75343_h = 10;
/*     */       
/*  72 */       if ((this.thePet.field_71093_bK != this.theOwner.field_71093_bK) || (this.thePet.func_70068_e(this.theOwner) >= 256.0D)) {
/*  73 */         int x = MathHelper.func_76128_c(this.theOwner.field_70165_t) - 2;
/*  74 */         int z = MathHelper.func_76128_c(this.theOwner.field_70161_v) - 2;
/*  75 */         int y = MathHelper.func_76128_c(this.theOwner.field_70121_D.field_72338_b) - 2;
/*     */         
/*  77 */         for (int dx = 0; dx <= 4; dx++) {
/*  78 */           for (int dz = 0; dz <= 4; dz++) {
/*  79 */             for (int dy = 0; dy <= 4; dy++) {
/*  80 */               int newX = x + dz;
/*  81 */               int newY = y + dy;
/*  82 */               int newZ = z + dz;
/*  83 */               if ((this.theOwner.field_70170_p.func_147439_a(newX, newY - 1, newZ).isSideSolid(this.theOwner.field_70170_p, newX, newY - 1, newZ, net.minecraftforge.common.util.ForgeDirection.UP)) && (!this.theOwner.field_70170_p.func_147439_a(newX, newY, newZ).func_149721_r()) && (!this.theOwner.field_70170_p.func_147439_a(newX, newY + 1, newZ).func_149721_r()))
/*     */               {
/*     */ 
/*  86 */                 ItemGeneral.teleportToLocation(this.theWorld, 0.5D + newX, 0.01D + newY, 0.5D + newZ, this.theOwner.field_71093_bK, this.thePet, true);
/*  87 */                 return;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       } else {
/*  93 */         double d0 = this.theOwner.field_70165_t - this.thePet.field_70165_t;
/*  94 */         double d1 = this.theOwner.field_70163_u - this.thePet.field_70163_u;
/*  95 */         double d2 = this.theOwner.field_70161_v - this.thePet.field_70161_v;
/*  96 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*  97 */         d3 = MathHelper.func_76133_a(d3);
/*  98 */         if (isCourseTraversable(this.theOwner.field_70165_t, this.theOwner.field_70163_u, this.theOwner.field_70161_v, d3)) {
/*  99 */           this.thePet.field_70159_w += d0 / d3 * 0.1D;
/* 100 */           if (this.thePet.field_70163_u < this.theOwner.field_70163_u + 2.0D) {
/* 101 */             this.thePet.field_70181_x += d1 / d3 * 0.1D + 0.1D;
/*     */           } else {
/* 103 */             this.thePet.field_70181_x += d1 / d3 * 0.1D;
/*     */           }
/* 105 */           this.thePet.field_70179_y += d2 / d3 * 0.1D;
/*     */         }
/*     */         else {
/* 108 */           double newX = this.thePet.field_70165_t + (this.thePet.field_70170_p.field_73012_v.nextFloat() * 8.0F - 4.0F) * 6.0F;
/* 109 */           double newY = this.thePet.field_70163_u + (this.thePet.field_70170_p.field_73012_v.nextFloat() * 2.0F - 1.0F) * 6.0F;
/* 110 */           double newZ = this.thePet.field_70161_v + (this.thePet.field_70170_p.field_73012_v.nextFloat() * 8.0F - 4.0F) * 6.0F;
/* 111 */           d0 = newX - this.thePet.field_70165_t;
/* 112 */           d1 = newY - this.thePet.field_70163_u;
/* 113 */           d2 = newZ - this.thePet.field_70161_v;
/* 114 */           d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 115 */           d3 = MathHelper.func_76133_a(d3);
/*     */           
/* 117 */           this.thePet.field_70159_w += d0 / d3 * 0.1D;
/* 118 */           this.thePet.field_70181_x += d1 / d3 * 0.1D + 0.1D;
/* 119 */           this.thePet.field_70179_y += d2 / d3 * 0.1D;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 124 */       this.thePet.field_70761_aq = (this.thePet.field_70177_z = -(float)Math.atan2(this.thePet.field_70159_w, this.thePet.field_70179_y) * 180.0F / 3.1415927F);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
/*     */   {
/* 130 */     double d4 = (par1 - this.thePet.field_70165_t) / par7;
/* 131 */     double d5 = (par3 - this.thePet.field_70163_u) / par7;
/* 132 */     double d6 = (par5 - this.thePet.field_70161_v) / par7;
/*     */     
/* 134 */     AxisAlignedBB axisalignedbb = this.thePet.field_70121_D.func_72329_c();
/*     */     
/* 136 */     for (int i = 1; i < par7; i++) {
/* 137 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 139 */       if (!this.thePet.field_70170_p.func_72945_a(this.thePet, axisalignedbb).isEmpty()) {
/* 140 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 144 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyerFollowOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */