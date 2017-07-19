/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityEye
/*     */   extends EntityLiving
/*     */ {
/*     */   public EntityEye(World world)
/*     */   {
/*  23 */     super(world);
/*  24 */     func_70105_a(0.0F, 0.0F);
/*  25 */     func_70661_as().func_75495_e(true);
/*  26 */     this.field_70178_ae = true;
/*  27 */     func_82142_c(true);
/*  28 */     this.field_70145_X = true;
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
/*     */   public boolean func_70617_f_()
/*     */   {
/*  41 */     return false;
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/*  46 */     return par1;
/*     */   }
/*     */   
/*     */   protected boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  51 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/*  56 */     return true;
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  61 */     if (func_94056_bM()) {
/*  62 */       return func_94057_bL();
/*     */     }
/*  64 */     return StatCollector.func_74838_a("entity.witchery.eye.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/*  70 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  75 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  80 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  85 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  90 */     this.field_70181_x = 0.5D;
/*  91 */     super.func_70636_d();
/*     */     
/*  93 */     if (this.field_70173_aa > 200) {
/*  94 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70612_e(float par1, float par2)
/*     */   {
/* 100 */     if (func_70090_H()) {
/* 101 */       func_70060_a(par1, par2, 0.02F);
/* 102 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 103 */       this.field_70159_w *= 0.800000011920929D;
/* 104 */       this.field_70181_x *= 0.800000011920929D;
/* 105 */       this.field_70179_y *= 0.800000011920929D;
/* 106 */     } else if (func_70058_J()) {
/* 107 */       func_70060_a(par1, par2, 0.02F);
/* 108 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 109 */       this.field_70159_w *= 0.5D;
/* 110 */       this.field_70181_x *= 0.5D;
/* 111 */       this.field_70179_y *= 0.5D;
/*     */     } else {
/* 113 */       float f2 = 0.91F;
/*     */       
/* 115 */       if (this.field_70122_E) {
/* 116 */         f2 = 0.54600006F;
/* 117 */         Block i = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
/*     */         
/*     */ 
/* 120 */         if (i != Blocks.field_150350_a) {
/* 121 */           f2 = i.field_149765_K * 0.91F;
/*     */         }
/*     */       }
/*     */       
/* 125 */       float f3 = 0.16277136F / (f2 * f2 * f2);
/* 126 */       func_70060_a(par1, par2, this.field_70122_E ? 0.1F * f3 : 0.02F);
/* 127 */       f2 = 0.91F;
/*     */       
/* 129 */       if (this.field_70122_E) {
/* 130 */         f2 = 0.54600006F;
/* 131 */         Block j = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
/*     */         
/*     */ 
/* 134 */         if (j != Blocks.field_150350_a) {
/* 135 */           f2 = j.field_149765_K * 0.91F;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 141 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       
/* 143 */       this.field_70159_w *= f2;
/* 144 */       this.field_70181_x *= f2;
/* 145 */       this.field_70179_y *= f2;
/*     */     }
/*     */     
/* 148 */     this.field_70722_aY = this.field_70721_aZ;
/* 149 */     double d0 = this.field_70165_t - this.field_70169_q;
/* 150 */     double d1 = this.field_70161_v - this.field_70166_s;
/* 151 */     float f4 = MathHelper.func_76133_a(d0 * d0 + d1 * d1) * 4.0F;
/*     */     
/* 153 */     if (f4 > 1.0F) {
/* 154 */       f4 = 1.0F;
/*     */     }
/*     */     
/* 157 */     this.field_70721_aZ += (f4 - this.field_70721_aZ) * 0.4F;
/* 158 */     this.field_70754_ba += this.field_70721_aZ;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityEye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */