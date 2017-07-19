/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIEnslaverHurtByTarget extends EntityAITarget
/*    */ {
/*    */   EntityCreature enslavedEntity;
/*    */   EntityLivingBase enslaversAttacker;
/*    */   private int enslaversRevengeTimer;
/*    */   
/*    */   public EntityAIEnslaverHurtByTarget(EntityCreature enslavedCreature)
/*    */   {
/* 16 */     super(enslavedCreature, false);
/* 17 */     this.enslavedEntity = enslavedCreature;
/* 18 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 22 */     if (!this.enslavedEntity.func_70644_a(com.emoniph.witchery.Witchery.Potions.ENSLAVED)) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     String ownerName = PotionEnslaved.getMobEnslaverName(this.enslavedEntity);
/* 27 */     if ((ownerName == null) || (ownerName.isEmpty())) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     EntityLivingBase enslaver = this.enslavedEntity.field_70170_p.func_72924_a(ownerName);
/*    */     
/* 33 */     if (enslaver == null) {
/* 34 */       return false;
/*    */     }
/* 36 */     this.enslaversAttacker = enslaver.func_70643_av();
/* 37 */     int revengeTimer = enslaver.func_142015_aE();
/* 38 */     if (revengeTimer == this.enslaversRevengeTimer)
/* 39 */       return false;
/* 40 */     if (this.enslaversAttacker == null) {
/* 41 */       return false;
/*    */     }
/* 43 */     return func_75296_a(this.enslaversAttacker, false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 50 */     com.emoniph.witchery.util.EntityUtil.setTarget(this.field_75299_d, this.enslaversAttacker);
/* 51 */     String enslaverName = PotionEnslaved.getMobEnslaverName(this.enslavedEntity);
/* 52 */     EntityLivingBase enslaver = this.enslavedEntity.field_70170_p.func_72924_a(enslaverName);
/*    */     
/* 54 */     if (enslaver != null) {
/* 55 */       this.enslaversRevengeTimer = enslaver.func_142015_aE();
/*    */     }
/*    */     
/* 58 */     super.func_75249_e();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/EntityAIEnslaverHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */