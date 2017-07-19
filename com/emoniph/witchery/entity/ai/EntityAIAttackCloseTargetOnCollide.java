/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*    */ 
/*    */ public class EntityAIAttackCloseTargetOnCollide extends EntityAIAttackOnCollide
/*    */ {
/*    */   EntityCreature field_75441_b;
/*    */   Class field_75444_h;
/*    */   double maxDistance;
/*    */   
/*    */   public EntityAIAttackCloseTargetOnCollide(EntityCreature par1EntityLiving, Class par2Class, double par3, boolean par4, double maxDistance)
/*    */   {
/* 15 */     this(par1EntityLiving, par3, par4, maxDistance);
/* 16 */     this.field_75444_h = par2Class;
/*    */   }
/*    */   
/*    */   public EntityAIAttackCloseTargetOnCollide(EntityCreature par1EntityLiving, double par2, boolean par3, double maxDistance) {
/* 20 */     super(par1EntityLiving, par2, par3);
/* 21 */     this.field_75441_b = par1EntityLiving;
/* 22 */     this.maxDistance = maxDistance;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 27 */     boolean execute = super.func_75250_a();
/* 28 */     if ((execute) && (!isTargetNearby())) {
/* 29 */       execute = false;
/*    */     }
/*    */     
/* 32 */     return execute;
/*    */   }
/*    */   
/*    */   protected boolean isTargetNearby() {
/* 36 */     EntityLivingBase entityTarget = this.field_75441_b != null ? this.field_75441_b.func_70638_az() : null;
/* 37 */     return (entityTarget != null) && (this.field_75441_b.func_70068_e(entityTarget) <= this.maxDistance * this.maxDistance) && (this.field_75441_b.func_70661_as().func_75494_a(entityTarget) != null) && ((entityTarget.func_70694_bm() == null) || (entityTarget.func_70694_bm().func_77973_b() != com.emoniph.witchery.Witchery.Items.DEVILS_TONGUE_CHARM));
/*    */   }
/*    */   
/*    */   public boolean func_75253_b()
/*    */   {
/* 42 */     boolean execute = super.func_75253_b();
/* 43 */     if ((execute) && (!isTargetNearby())) {
/* 44 */       execute = false;
/*    */     }
/* 46 */     return execute;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIAttackCloseTargetOnCollide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */