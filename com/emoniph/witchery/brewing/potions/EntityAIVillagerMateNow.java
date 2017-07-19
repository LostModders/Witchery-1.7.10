/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIVillagerMateNow extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityVillager villagerObj;
/*     */   private EntityVillager mate;
/*     */   private World worldObj;
/*     */   private int matingTimeout;
/*     */   private boolean begin;
/*     */   Village villageObj;
/*     */   
/*     */   public EntityAIVillagerMateNow(EntityVillager p_i1634_1_)
/*     */   {
/*  22 */     this.villagerObj = p_i1634_1_;
/*  23 */     this.worldObj = p_i1634_1_.field_70170_p;
/*  24 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */   public void beginMating() {
/*  28 */     this.begin = true;
/*     */   }
/*     */   
/*     */   public boolean func_75250_a() {
/*  32 */     if ((this.villagerObj.func_70874_b() != 0) || (!this.begin)) {
/*  33 */       return false;
/*     */     }
/*  35 */     this.villageObj = this.worldObj.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.villagerObj.field_70165_t), MathHelper.func_76128_c(this.villagerObj.field_70163_u), MathHelper.func_76128_c(this.villagerObj.field_70161_v), 0);
/*     */     
/*     */ 
/*     */ 
/*  39 */     if (this.villageObj == null)
/*  40 */       return false;
/*  41 */     if (!checkSufficientDoorsPresentForNewVillager()) {
/*  42 */       return false;
/*     */     }
/*  44 */     net.minecraft.entity.Entity entity = this.worldObj.func_72857_a(EntityVillager.class, this.villagerObj.field_70121_D.func_72314_b(8.0D, 3.0D, 8.0D), this.villagerObj);
/*     */     
/*     */ 
/*  47 */     if (entity == null) {
/*  48 */       return false;
/*     */     }
/*  50 */     this.mate = ((EntityVillager)entity);
/*  51 */     return this.mate.func_70874_b() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  58 */     this.matingTimeout = 300;
/*  59 */     this.villagerObj.func_70947_e(true);
/*  60 */     this.begin = false;
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/*  64 */     this.villageObj = null;
/*  65 */     this.mate = null;
/*  66 */     this.villagerObj.func_70947_e(false);
/*  67 */     this.begin = false;
/*     */   }
/*     */   
/*     */   public boolean func_75253_b() {
/*  71 */     boolean keepGoing = (this.matingTimeout >= 0) && (checkSufficientDoorsPresentForNewVillager()) && (this.villagerObj.func_70874_b() == 0);
/*     */     
/*     */ 
/*  74 */     return keepGoing;
/*     */   }
/*     */   
/*     */   public void func_75246_d() {
/*  78 */     this.matingTimeout -= 1;
/*  79 */     this.villagerObj.func_70671_ap().func_75651_a(this.mate, 10.0F, 30.0F);
/*     */     
/*  81 */     if (this.villagerObj.func_70068_e(this.mate) > 2.25D) {
/*  82 */       this.villagerObj.func_70661_as().func_75497_a(this.mate, 0.25D);
/*  83 */     } else if ((this.matingTimeout == 0) && (this.mate.func_70941_o())) {
/*  84 */       giveBirth();
/*     */     }
/*     */     
/*  87 */     if (this.villagerObj.func_70681_au().nextInt(35) == 0) {
/*  88 */       this.worldObj.func_72960_a(this.villagerObj, (byte)12);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean checkSufficientDoorsPresentForNewVillager() {
/*  93 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void giveBirth()
/*     */   {
/* 100 */     EntityVillager entityvillager = this.villagerObj.func_90011_a(this.mate);
/* 101 */     this.mate.func_70873_a(500);
/* 102 */     this.villagerObj.func_70873_a(500);
/* 103 */     entityvillager.func_70873_a(41536);
/* 104 */     entityvillager.func_70012_b(this.villagerObj.field_70165_t, this.villagerObj.field_70163_u, this.villagerObj.field_70161_v, 0.0F, 0.0F);
/*     */     
/* 106 */     this.worldObj.func_72838_d(entityvillager);
/* 107 */     this.worldObj.func_72960_a(entityvillager, (byte)12);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/EntityAIVillagerMateNow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */