/*    */ package com.emoniph.witchery.entity;
/*    */ 
/*    */ import com.emoniph.witchery.util.DemonicDamageSource;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntitySoulfire extends EntitySmallFireball
/*    */ {
/*    */   public EntitySoulfire(World par1World)
/*    */   {
/* 14 */     super(par1World);
/* 15 */     func_70105_a(0.3125F, 0.3125F);
/*    */   }
/*    */   
/*    */   public EntitySoulfire(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
/* 19 */     super(par1World, par2EntityLivingBase, par3, par5, par7);
/* 20 */     func_70105_a(0.3125F, 0.3125F);
/*    */   }
/*    */   
/*    */   public EntitySoulfire(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/* 24 */     super(par1World, par2, par4, par6, par8, par10, par12);
/* 25 */     func_70105_a(0.3125F, 0.3125F);
/*    */   }
/*    */   
/*    */   protected void func_70227_a(MovingObjectPosition par1MovingObjectPosition) {
/* 29 */     super.func_70227_a(par1MovingObjectPosition);
/* 30 */     if ((!this.field_70170_p.field_72995_K) && 
/* 31 */       (par1MovingObjectPosition.field_72308_g != null)) {
/* 32 */       par1MovingObjectPosition.field_72308_g.func_70097_a(new DemonicDamageSource(this.field_70235_a), 6.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntitySoulfire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */