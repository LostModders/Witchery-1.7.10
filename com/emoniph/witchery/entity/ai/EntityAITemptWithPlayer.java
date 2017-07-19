/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAITemptWithPlayer
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityCreature temptedEntity;
/*     */   private double field_75282_b;
/*     */   private double field_75283_c;
/*     */   private double field_75280_d;
/*     */   private double field_75281_e;
/*     */   private double field_75278_f;
/*     */   private double field_75279_g;
/*     */   private EntityPlayer temptingPlayer;
/*     */   private int delayTemptCounter;
/*     */   private boolean field_75287_j;
/*     */   private boolean field_75286_m;
/*     */   
/*     */   public EntityAITemptWithPlayer(EntityCreature par1EntityCreature, double par2)
/*     */   {
/*  35 */     this.temptedEntity = par1EntityCreature;
/*  36 */     this.field_75282_b = par2;
/*  37 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  45 */     if (this.delayTemptCounter > 0) {
/*  46 */       this.delayTemptCounter -= 1;
/*  47 */       return false;
/*     */     }
/*  49 */     this.temptingPlayer = this.temptedEntity.field_70170_p.func_72890_a(this.temptedEntity, 10.0D);
/*     */     
/*  51 */     if (this.temptingPlayer == null) {
/*  52 */       return false;
/*     */     }
/*     */     
/*  55 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  95 */     return func_75250_a();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 103 */     this.field_75283_c = this.temptingPlayer.field_70165_t;
/* 104 */     this.field_75280_d = this.temptingPlayer.field_70163_u;
/* 105 */     this.field_75281_e = this.temptingPlayer.field_70161_v;
/* 106 */     this.field_75287_j = true;
/* 107 */     this.field_75286_m = this.temptedEntity.func_70661_as().func_75486_a();
/* 108 */     this.temptedEntity.func_70661_as().func_75491_a(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 116 */     this.temptingPlayer = null;
/* 117 */     this.temptedEntity.func_70661_as().func_75499_g();
/* 118 */     this.delayTemptCounter = 100;
/* 119 */     this.field_75287_j = false;
/* 120 */     this.temptedEntity.func_70661_as().func_75491_a(this.field_75286_m);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 128 */     this.temptedEntity.func_70671_ap().func_75651_a(this.temptingPlayer, 30.0F, this.temptedEntity.func_70646_bf());
/*     */     
/* 130 */     if (this.temptedEntity.func_70068_e(this.temptingPlayer) < 6.25D) {
/* 131 */       this.temptedEntity.func_70661_as().func_75499_g();
/*     */     } else {
/* 133 */       this.temptedEntity.func_70661_as().func_75497_a(this.temptingPlayer, this.field_75282_b);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_75277_f() {
/* 138 */     return this.field_75287_j;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAITemptWithPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */