/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIMoveTowardsVampire
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntity;
/*    */   private EntityLivingBase targetEntity;
/*    */   private double movePosX;
/*    */   private double movePosY;
/*    */   private double movePosZ;
/*    */   private double speed;
/*    */   private float maxTargetDistance;
/*    */   private float minTargetDistance;
/*    */   
/*    */   public EntityAIMoveTowardsVampire(EntityCreature par1EntityCreature, double par2, float min, float max)
/*    */   {
/* 29 */     this.theEntity = par1EntityCreature;
/* 30 */     this.speed = par2;
/* 31 */     this.minTargetDistance = min;
/* 32 */     this.maxTargetDistance = max;
/*    */     
/* 34 */     func_75248_a(1);
/*    */   }
/*    */   
/*    */   private EntityLivingBase getDistanceSqToPartner() {
/* 38 */     double R = this.maxTargetDistance;
/* 39 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.theEntity.field_70165_t - R, this.theEntity.field_70163_u - R, this.theEntity.field_70161_v - R, this.theEntity.field_70165_t + R, this.theEntity.field_70163_u + R, this.theEntity.field_70161_v + R);
/*    */     
/* 41 */     List<EntityPlayer> mogs = this.theEntity.field_70170_p.func_72872_a(EntityPlayer.class, bb);
/* 42 */     double minDistance = Double.MAX_VALUE;
/* 43 */     EntityLivingBase target = null;
/* 44 */     for (EntityPlayer player : mogs) {
/* 45 */       if (ExtendedPlayer.get(player).getVampireLevel() >= 8) {
/* 46 */         double distance = this.theEntity.func_70068_e(player);
/* 47 */         if (distance < minDistance) {
/* 48 */           minDistance = distance;
/* 49 */           target = player;
/*    */         }
/*    */       }
/*    */     }
/* 53 */     return target;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 58 */     this.targetEntity = getDistanceSqToPartner();
/*    */     
/* 60 */     if (this.targetEntity == null) {
/* 61 */       return false;
/*    */     }
/*    */     
/* 64 */     double dist = this.targetEntity.func_70068_e(this.theEntity);
/*    */     
/* 66 */     if (dist > this.maxTargetDistance * this.maxTargetDistance)
/* 67 */       return false;
/* 68 */     if (dist < this.minTargetDistance * this.minTargetDistance) {
/* 69 */       return false;
/*    */     }
/*    */     
/* 72 */     return true;
/*    */   }
/*    */   
/*    */   public boolean func_75253_b()
/*    */   {
/* 77 */     if (this.theEntity.field_70173_aa % 20 == 0) {
/* 78 */       this.theEntity.func_70661_as().func_75492_a(this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v, this.speed);
/*    */     }
/* 80 */     return true;
/*    */   }
/*    */   
/*    */   public void func_75251_c() {
/* 84 */     this.targetEntity = null;
/*    */   }
/*    */   
/*    */   public void func_75249_e() {
/* 88 */     this.theEntity.func_70661_as().func_75492_a(this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v, this.speed);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIMoveTowardsVampire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */