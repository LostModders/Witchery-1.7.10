/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
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
/*     */ public class EntityAIFlyingTempt
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityCreature temptedEntity;
/*     */   private double field_75282_b;
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private double field_75278_f;
/*     */   private double field_75279_g;
/*     */   private EntityPlayer temptingPlayer;
/*     */   private int delayTemptCounter;
/*     */   private boolean isRunning;
/*     */   private ItemStack[] breedingFood;
/*     */   private boolean scaredByPlayerMovement;
/*     */   private boolean field_75286_m;
/*     */   
/*     */   public EntityAIFlyingTempt(EntityCreature par1EntityCreature, double par2, ItemStack[] par4, boolean par5)
/*     */   {
/*  54 */     this.temptedEntity = par1EntityCreature;
/*  55 */     this.field_75282_b = par2;
/*  56 */     this.breedingFood = par4;
/*  57 */     this.scaredByPlayerMovement = par5;
/*  58 */     func_75248_a(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  65 */     boolean isTame = ((this.temptedEntity instanceof EntityTameable)) && (((EntityTameable)this.temptedEntity).func_70909_n());
/*  66 */     if (isTame)
/*     */     {
/*  68 */       return false;
/*     */     }
/*  70 */     if (this.delayTemptCounter > 0) {
/*  71 */       this.delayTemptCounter -= 1;
/*  72 */       return false;
/*     */     }
/*  74 */     this.temptingPlayer = this.temptedEntity.field_70170_p.func_72890_a(this.temptedEntity, 10.0D);
/*     */     
/*  76 */     if (this.temptingPlayer == null) {
/*  77 */       return false;
/*     */     }
/*  79 */     ItemStack itemstack = this.temptingPlayer.func_71045_bC();
/*  80 */     return itemstack == null ? false : isBreedingFood(itemstack);
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isBreedingFood(ItemStack stack)
/*     */   {
/*  86 */     for (ItemStack possibleFoods : this.breedingFood) {
/*  87 */       if (possibleFoods.func_77969_a(stack)) {
/*  88 */         return true;
/*     */       }
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  98 */     if (this.scaredByPlayerMovement) {
/*  99 */       if (this.temptedEntity.func_70068_e(this.temptingPlayer) < 36.0D) {
/* 100 */         if (this.temptingPlayer.func_70092_e(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D) {
/* 101 */           return false;
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 109 */         this.targetX = this.temptingPlayer.field_70165_t;
/* 110 */         this.targetY = this.temptingPlayer.field_70163_u;
/* 111 */         this.targetZ = this.temptingPlayer.field_70161_v;
/*     */       }
/*     */       
/* 114 */       this.field_75278_f = this.temptingPlayer.field_70125_A;
/* 115 */       this.field_75279_g = this.temptingPlayer.field_70177_z;
/*     */     }
/*     */     
/* 118 */     return func_75250_a();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 128 */     this.isRunning = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 138 */     this.temptingPlayer = null;
/*     */     
/* 140 */     this.delayTemptCounter = 100;
/* 141 */     this.isRunning = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 149 */     if (this.temptedEntity.func_70068_e(this.temptingPlayer) >= 3.0D) {
/* 150 */       double d0 = this.targetX - this.temptedEntity.field_70165_t;
/* 151 */       double d1 = this.targetY - this.temptedEntity.field_70163_u;
/* 152 */       double d2 = this.targetZ - this.temptedEntity.field_70161_v;
/* 153 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 154 */       d3 = MathHelper.func_76133_a(d3);
/* 155 */       if (isCourseTraversable(this.targetX, this.targetY, this.targetZ, d3))
/*     */       {
/* 157 */         this.temptedEntity.field_70159_w += d0 / d3 * 0.05D;
/* 158 */         if (this.temptedEntity.field_70163_u < this.targetY + 1.0D) {
/* 159 */           this.temptedEntity.field_70181_x += d1 / d3 * 0.05D + 0.025D;
/*     */         } else {
/* 161 */           this.temptedEntity.field_70181_x += d1 / d3 * 0.05D;
/*     */         }
/* 163 */         this.temptedEntity.field_70179_y += d2 / d3 * 0.05D;
/*     */       }
/* 165 */       this.temptedEntity.field_70761_aq = (this.temptedEntity.field_70177_z = -(float)Math.atan2(this.temptedEntity.field_70159_w, this.temptedEntity.field_70179_y) * 180.0F / 3.1415927F);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/* 170 */     double d4 = (par1 - this.temptedEntity.field_70165_t) / par7;
/* 171 */     double d5 = (par3 - this.temptedEntity.field_70163_u) / par7;
/* 172 */     double d6 = (par5 - this.temptedEntity.field_70161_v) / par7;
/*     */     
/* 174 */     AxisAlignedBB axisalignedbb = this.temptedEntity.field_70121_D.func_72329_c();
/*     */     
/* 176 */     for (int i = 1; i < par7; i++) {
/* 177 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 179 */       if (!this.temptedEntity.field_70170_p.func_72945_a(this.temptedEntity, axisalignedbb).isEmpty()) {
/* 180 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 184 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isRunning()
/*     */   {
/* 191 */     return this.isRunning;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyingTempt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */