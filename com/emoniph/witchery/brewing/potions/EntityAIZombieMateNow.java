/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.ai.EntityLookHelper;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIZombieMateNow extends EntityAIBase
/*    */ {
/*    */   private EntityZombie zombieObj;
/*    */   private EntityZombie mate;
/*    */   private World worldObj;
/*    */   private int matingTimeout;
/*    */   private boolean mating;
/*    */   private boolean begin;
/*    */   
/*    */   public EntityAIZombieMateNow(EntityZombie zombie)
/*    */   {
/* 21 */     this.zombieObj = zombie;
/* 22 */     this.worldObj = zombie.field_70170_p;
/* 23 */     func_75248_a(3);
/*    */   }
/*    */   
/*    */   public void beginMating() {
/* 27 */     this.begin = true;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 31 */     if (!this.begin) {
/* 32 */       return false;
/*    */     }
/* 34 */     EntityZombie zombie = (EntityZombie)com.emoniph.witchery.util.EntityUtil.findNearestEntityWithinAABB(this.worldObj, EntityZombie.class, this.zombieObj.field_70121_D.func_72314_b(8.0D, 3.0D, 8.0D), this.zombieObj);
/*    */     
/*    */ 
/* 37 */     if ((zombie == null) || (zombie.func_70631_g_())) {
/* 38 */       return false;
/*    */     }
/* 40 */     this.mate = zombie;
/* 41 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 47 */     this.matingTimeout = 600;
/* 48 */     this.mating = true;
/* 49 */     this.begin = false;
/*    */   }
/*    */   
/*    */   public void func_75251_c() {
/* 53 */     this.mate = null;
/* 54 */     this.mating = false;
/* 55 */     this.begin = false;
/*    */   }
/*    */   
/*    */   public boolean func_75253_b() {
/* 59 */     boolean keepGoing = this.matingTimeout >= 0;
/* 60 */     return keepGoing;
/*    */   }
/*    */   
/*    */   public void func_75246_d() {
/* 64 */     this.matingTimeout -= 1;
/* 65 */     this.zombieObj.func_70671_ap().func_75651_a(this.mate, 10.0F, 30.0F);
/*    */     
/* 67 */     if (this.zombieObj.func_70068_e(this.mate) > 2.25D) {
/* 68 */       this.zombieObj.func_70661_as().func_75497_a(this.mate, 1.4D);
/* 69 */     } else if ((this.matingTimeout == 0) && (this.mating)) {
/* 70 */       giveBirth();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private void giveBirth()
/*    */   {
/* 79 */     ParticleEffect.HEART.send(com.emoniph.witchery.util.SoundEffect.NONE, this.mate, 1.0D, 2.0D, 8);
/* 80 */     this.zombieObj.func_82229_g(true);
/* 81 */     this.mate.func_82229_g(true);
/* 82 */     EntityZombie baby = new EntityZombie(this.worldObj);
/* 83 */     baby.func_70012_b(this.mate.field_70165_t, this.mate.field_70163_u, this.mate.field_70161_v, 0.0F, 0.0F);
/* 84 */     baby.func_82227_f(true);
/* 85 */     this.worldObj.func_72838_d(baby);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/EntityAIZombieMateNow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */