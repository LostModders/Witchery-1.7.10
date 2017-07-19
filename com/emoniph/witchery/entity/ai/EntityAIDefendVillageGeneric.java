/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ import net.minecraft.village.Village;
/*    */ 
/*    */ 
/*    */ public class EntityAIDefendVillageGeneric
/*    */   extends EntityAITarget
/*    */ {
/*    */   IVillageGuard defender;
/*    */   EntityLivingBase villageAgressorTarget;
/*    */   
/*    */   public EntityAIDefendVillageGeneric(IVillageGuard guard)
/*    */   {
/* 18 */     super(guard.getCreature(), false, true);
/* 19 */     this.defender = guard;
/* 20 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 24 */     Village village = this.defender.getVillage();
/*    */     
/* 26 */     if (village == null) {
/* 27 */       return false;
/*    */     }
/* 29 */     this.villageAgressorTarget = village.func_75571_b(this.defender.getCreature());
/*    */     
/* 31 */     if (!func_75296_a(this.villageAgressorTarget, false)) {
/* 32 */       if (this.field_75299_d.func_70681_au().nextInt(20) == 0) {
/* 33 */         this.villageAgressorTarget = village.func_82685_c(this.defender.getCreature());
/* 34 */         return func_75296_a(this.villageAgressorTarget, false);
/*    */       }
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 45 */     this.defender.getCreature().func_70624_b(this.villageAgressorTarget);
/* 46 */     super.func_75249_e();
/*    */   }
/*    */   
/*    */   public static abstract interface IVillageGuard
/*    */   {
/*    */     public abstract Village getVillage();
/*    */     
/*    */     public abstract EntityCreature getCreature();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIDefendVillageGeneric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */