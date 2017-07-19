/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class EntityAIDimensionalFollowOwner extends EntityAIBase
/*     */ {
/*     */   private EntityTameable thePet;
/*     */   private EntityLivingBase theOwner;
/*     */   World theWorld;
/*     */   private double field_75336_f;
/*     */   private PathNavigate petPathfinder;
/*     */   private int field_75343_h;
/*     */   float maxDist;
/*     */   float minDist;
/*     */   private boolean field_75344_i;
/*     */   
/*     */   public EntityAIDimensionalFollowOwner(EntityTameable par1EntityTameable, double par2, float par4, float par5)
/*     */   {
/*  28 */     this.thePet = par1EntityTameable;
/*  29 */     this.theWorld = par1EntityTameable.field_70170_p;
/*  30 */     this.field_75336_f = par2;
/*  31 */     this.petPathfinder = par1EntityTameable.func_70661_as();
/*  32 */     this.minDist = par4;
/*  33 */     this.maxDist = par5;
/*  34 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  42 */     EntityLivingBase entitylivingbase = this.thePet.func_70902_q();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  47 */     if (entitylivingbase == null)
/*     */     {
/*  49 */       return false;
/*     */     }
/*  51 */     if (this.thePet.func_70906_o())
/*     */     {
/*  53 */       return false;
/*     */     }
/*  55 */     if ((this.thePet.field_71093_bK != entitylivingbase.field_71093_bK) || (this.thePet.func_70068_e(entitylivingbase) < this.minDist * this.minDist))
/*     */     {
/*  57 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  61 */     this.theOwner = entitylivingbase;
/*     */     
/*  63 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  72 */     return (!this.petPathfinder.func_75500_f()) && (this.thePet.func_70068_e(this.theOwner) > this.maxDist * this.maxDist) && (!this.thePet.func_70906_o());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  80 */     this.field_75343_h = 0;
/*  81 */     this.field_75344_i = this.thePet.func_70661_as().func_75486_a();
/*  82 */     this.thePet.func_70661_as().func_75491_a(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  90 */     this.theOwner = null;
/*  91 */     this.petPathfinder.func_75499_g();
/*  92 */     this.thePet.func_70661_as().func_75491_a(this.field_75344_i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 100 */     this.thePet.func_70671_ap().func_75651_a(this.theOwner, 10.0F, this.thePet.func_70646_bf());
/*     */     
/* 102 */     if (!this.thePet.func_70906_o())
/*     */     {
/* 104 */       if (--this.field_75343_h <= 0)
/*     */       {
/* 106 */         this.field_75343_h = 10;
/*     */         
/* 108 */         if (!this.petPathfinder.func_75497_a(this.theOwner, this.field_75336_f))
/*     */         {
/* 110 */           if (!this.thePet.func_110167_bD())
/*     */           {
/* 112 */             if ((this.thePet.field_71093_bK != this.theOwner.field_71093_bK) || (this.thePet.func_70068_e(this.theOwner) >= 144.0D))
/*     */             {
/* 114 */               int i = MathHelper.func_76128_c(this.theOwner.field_70165_t) - 2;
/* 115 */               int j = MathHelper.func_76128_c(this.theOwner.field_70161_v) - 2;
/* 116 */               int k = MathHelper.func_76128_c(this.theOwner.field_70121_D.field_72338_b);
/*     */               
/* 118 */               for (int l = 0; l <= 4; l++)
/*     */               {
/* 120 */                 for (int i1 = 0; i1 <= 4; i1++)
/*     */                 {
/* 122 */                   if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (this.theOwner.field_70170_p.func_147439_a(i + l, k - 1, j + i1).isSideSolid(this.theOwner.field_70170_p, i + l, k - 1, j + i1, ForgeDirection.UP)) && (!this.theOwner.field_70170_p.func_147439_a(i + l, k, j + i1).func_149721_r()) && (!this.theOwner.field_70170_p.func_147439_a(i + l, k + 1, j + i1).func_149721_r()))
/*     */                   {
/*     */ 
/* 125 */                     if (this.thePet.field_71093_bK == this.theOwner.field_71093_bK)
/*     */                     {
/*     */ 
/* 128 */                       this.thePet.func_70012_b(i + l + 0.5F, k, j + i1 + 0.5F, this.thePet.field_70177_z, this.thePet.field_70125_A);
/*     */                     }
/*     */                     
/* 131 */                     this.petPathfinder.func_75499_g();
/* 132 */                     return;
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIDimensionalFollowOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */