/*    */ package com.emoniph.witchery.entity;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class EntityFlyingMob extends net.minecraft.entity.monster.EntityMob
/*    */ {
/*    */   public EntityFlyingMob(World par1World)
/*    */   {
/* 12 */     super(par1World);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void func_70069_a(float par1) {}
/*    */   
/*    */ 
/*    */   protected void func_70064_a(double par1, boolean par3) {}
/*    */   
/*    */ 
/*    */   public void func_70612_e(float par1, float par2)
/*    */   {
/* 25 */     if (func_70090_H()) {
/* 26 */       func_70060_a(par1, par2, 0.02F);
/* 27 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 28 */       this.field_70159_w *= 0.800000011920929D;
/* 29 */       this.field_70181_x *= 0.800000011920929D;
/* 30 */       this.field_70179_y *= 0.800000011920929D;
/* 31 */     } else if (func_70058_J()) {
/* 32 */       func_70060_a(par1, par2, 0.02F);
/* 33 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 34 */       this.field_70159_w *= 0.5D;
/* 35 */       this.field_70181_x *= 0.5D;
/* 36 */       this.field_70179_y *= 0.5D;
/*    */     } else {
/* 38 */       float f2 = 0.91F;
/*    */       
/* 40 */       if (this.field_70122_E) {
/* 41 */         f2 = 0.54600006F;
/* 42 */         Block i = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
/*    */         
/*    */ 
/* 45 */         if (i != net.minecraft.init.Blocks.field_150350_a) {
/* 46 */           f2 = i.field_149765_K * 0.91F;
/*    */         }
/*    */       }
/*    */       
/* 50 */       float f3 = 0.16277136F / (f2 * f2 * f2);
/* 51 */       func_70060_a(par1, par2, this.field_70122_E ? 0.1F * f3 : 0.02F);
/* 52 */       f2 = 0.91F;
/*    */       
/* 54 */       if (this.field_70122_E) {
/* 55 */         f2 = 0.54600006F;
/* 56 */         Block j = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
/*    */         
/*    */ 
/* 59 */         if (j != net.minecraft.init.Blocks.field_150350_a) {
/* 60 */           f2 = j.field_149765_K * 0.91F;
/*    */         }
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 66 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*    */       
/* 68 */       this.field_70159_w *= f2;
/* 69 */       this.field_70181_x *= f2;
/* 70 */       this.field_70179_y *= f2;
/*    */     }
/*    */     
/* 73 */     this.field_70722_aY = this.field_70721_aZ;
/* 74 */     double d0 = this.field_70165_t - this.field_70169_q;
/* 75 */     double d1 = this.field_70161_v - this.field_70166_s;
/* 76 */     float f4 = MathHelper.func_76133_a(d0 * d0 + d1 * d1) * 4.0F;
/*    */     
/* 78 */     if (f4 > 1.0F) {
/* 79 */       f4 = 1.0F;
/*    */     }
/*    */     
/* 82 */     this.field_70721_aZ += (f4 - this.field_70721_aZ) * 0.4F;
/* 83 */     this.field_70754_ba += this.field_70721_aZ;
/*    */   }
/*    */   
/*    */   public boolean func_70617_f_()
/*    */   {
/* 88 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityFlyingMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */