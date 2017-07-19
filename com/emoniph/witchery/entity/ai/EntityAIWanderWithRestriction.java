/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIWanderWithRestriction
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature entity;
/*    */   private double xPosition;
/*    */   private double yPosition;
/*    */   private double zPosition;
/*    */   private double speed;
/*    */   private IHomeLocationProvider home;
/*    */   
/*    */   public EntityAIWanderWithRestriction(EntityCreature creature, double speed, IHomeLocationProvider home)
/*    */   {
/* 26 */     this.entity = creature;
/* 27 */     this.speed = speed;
/* 28 */     func_75248_a(1);
/* 29 */     this.home = home;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 33 */     if (this.entity.func_70654_ax() >= 100)
/* 34 */       return false;
/* 35 */     if (this.entity.func_70681_au().nextInt(120) != 0) {
/* 36 */       return false;
/*    */     }
/* 38 */     Vec3 vec3 = RandomPositionGenerator.func_75463_a(this.entity, 10, 7);
/*    */     
/* 40 */     if (vec3 == null)
/* 41 */       return false;
/* 42 */     if (Coord.distanceSq(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c, this.home.getHomeX(), this.home.getHomeY(), this.home.getHomeZ()) > this.home.getHomeRange() * this.home.getHomeRange()) {
/* 43 */       return false;
/*    */     }
/* 45 */     this.xPosition = vec3.field_72450_a;
/* 46 */     this.yPosition = vec3.field_72448_b;
/* 47 */     this.zPosition = vec3.field_72449_c;
/* 48 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 54 */     return !this.entity.func_70661_as().func_75500_f();
/*    */   }
/*    */   
/*    */   public void func_75249_e() {
/* 58 */     this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.speed);
/*    */   }
/*    */   
/*    */   public static abstract interface IHomeLocationProvider
/*    */   {
/*    */     public abstract double getHomeX();
/*    */     
/*    */     public abstract double getHomeY();
/*    */     
/*    */     public abstract double getHomeZ();
/*    */     
/*    */     public abstract double getHomeRange();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIWanderWithRestriction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */