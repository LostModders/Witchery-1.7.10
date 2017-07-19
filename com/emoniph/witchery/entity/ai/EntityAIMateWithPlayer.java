/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.ai.EntityLookHelper;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIMateWithPlayer extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   private EntityVillager villagerObj;
/*    */   private EntityPlayer mate;
/*    */   private World worldObj;
/*    */   private int matingTimeout;
/*    */   private boolean forceExecute;
/*    */   
/*    */   public EntityAIMateWithPlayer(EntityVillager par1EntityVillager)
/*    */   {
/* 19 */     this.villagerObj = par1EntityVillager;
/* 20 */     this.worldObj = par1EntityVillager.field_70170_p;
/* 21 */     func_75248_a(3);
/*    */   }
/*    */   
/*    */   public void forceTask(EntityPlayer player) {
/* 25 */     this.forceExecute = true;
/* 26 */     this.mate = player;
/*    */   }
/*    */   
/*    */   public boolean func_75250_a() {
/* 30 */     if (this.villagerObj.func_70874_b() != 0)
/* 31 */       return false;
/* 32 */     if ((!this.forceExecute) && (this.villagerObj.func_70681_au().nextInt(500) != 0)) {
/* 33 */       return false;
/*    */     }
/* 35 */     if (!this.forceExecute) {
/* 36 */       net.minecraft.entity.Entity entity = this.worldObj.func_72857_a(EntityPlayer.class, this.villagerObj.field_70121_D.func_72314_b(16.0D, 3.0D, 16.0D), this.villagerObj);
/*    */       
/*    */ 
/* 39 */       if (entity == null) {
/* 40 */         return false;
/*    */       }
/* 42 */       this.mate = ((EntityPlayer)entity);
/* 43 */       return true;
/*    */     }
/*    */     
/* 46 */     this.forceExecute = false;
/* 47 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 54 */     this.matingTimeout = 1000;
/* 55 */     this.villagerObj.func_70947_e(true);
/*    */   }
/*    */   
/*    */   public void func_75251_c() {
/* 59 */     this.mate = null;
/* 60 */     this.villagerObj.func_70947_e(false);
/*    */   }
/*    */   
/*    */   public boolean func_75253_b() {
/* 64 */     return (this.matingTimeout >= 0) && (this.villagerObj.func_70874_b() == 0);
/*    */   }
/*    */   
/*    */   public void func_75246_d() {
/* 68 */     if (this.matingTimeout > 0) {
/* 69 */       this.matingTimeout -= 1;
/*    */     }
/*    */     
/* 72 */     this.villagerObj.func_70671_ap().func_75651_a(this.mate, 10.0F, 30.0F);
/*    */     
/* 74 */     if (this.villagerObj.func_70068_e(this.mate) > 2.25D) {
/* 75 */       this.villagerObj.func_70661_as().func_75497_a(this.mate, 0.3D);
/* 76 */     } else if ((this.matingTimeout > 0) && (this.villagerObj.func_70068_e(this.mate) <= 2.25D)) {
/* 77 */       this.matingTimeout = 0;
/* 78 */       giveBirth();
/*    */     }
/*    */     
/* 81 */     if (this.villagerObj.func_70681_au().nextInt(20) == 0) {
/* 82 */       this.worldObj.func_72960_a(this.villagerObj, (byte)12);
/*    */     }
/*    */   }
/*    */   
/*    */   private void giveBirth() {
/* 87 */     EntityVillager entityvillager = this.villagerObj.func_90011_a(this.villagerObj);
/* 88 */     this.villagerObj.func_70873_a(6000);
/* 89 */     entityvillager.func_70873_a(41536);
/* 90 */     entityvillager.func_70012_b(this.villagerObj.field_70165_t, this.villagerObj.field_70163_u, this.villagerObj.field_70161_v, 0.0F, 0.0F);
/* 91 */     this.worldObj.func_72838_d(entityvillager);
/* 92 */     this.worldObj.func_72960_a(entityvillager, (byte)12);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIMateWithPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */