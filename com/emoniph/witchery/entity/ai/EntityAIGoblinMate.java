/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIGoblinMate extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityGoblin goblinObj;
/*     */   private EntityGoblin mate;
/*     */   private World worldObj;
/*     */   private int matingTimeout;
/*     */   Village villageObj;
/*     */   
/*     */   public EntityAIGoblinMate(EntityGoblin goblin)
/*     */   {
/*  21 */     this.goblinObj = goblin;
/*  22 */     this.worldObj = goblin.field_70170_p;
/*  23 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a() {
/*  27 */     if (this.goblinObj.func_70874_b() != 0)
/*  28 */       return false;
/*  29 */     if (this.goblinObj.func_70681_au().nextInt(500) != 0) {
/*  30 */       return false;
/*     */     }
/*  32 */     this.villageObj = this.worldObj.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.goblinObj.field_70165_t), MathHelper.func_76128_c(this.goblinObj.field_70163_u), MathHelper.func_76128_c(this.goblinObj.field_70161_v), 0);
/*     */     
/*     */ 
/*  35 */     if (this.villageObj == null)
/*  36 */       return false;
/*  37 */     if (!checkSufficientDoorsPresentForNewVillager()) {
/*  38 */       return false;
/*     */     }
/*  40 */     net.minecraft.entity.Entity entity = this.worldObj.func_72857_a(EntityGoblin.class, this.goblinObj.field_70121_D.func_72314_b(8.0D, 3.0D, 8.0D), this.goblinObj);
/*     */     
/*     */ 
/*  43 */     if (entity == null) {
/*  44 */       return false;
/*     */     }
/*  46 */     this.mate = ((EntityGoblin)entity);
/*  47 */     return this.mate.func_70874_b() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  54 */     this.matingTimeout = 300;
/*  55 */     this.goblinObj.setMating(true);
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/*  59 */     this.villageObj = null;
/*  60 */     this.mate = null;
/*  61 */     this.goblinObj.setMating(false);
/*     */   }
/*     */   
/*     */   public boolean func_75253_b() {
/*  65 */     return (this.matingTimeout >= 0) && (checkSufficientDoorsPresentForNewVillager()) && (this.goblinObj.func_70874_b() == 0);
/*     */   }
/*     */   
/*     */   public void func_75246_d() {
/*  69 */     this.matingTimeout -= 1;
/*  70 */     this.goblinObj.func_70671_ap().func_75651_a(this.mate, 10.0F, 30.0F);
/*     */     
/*  72 */     if (this.goblinObj.func_70068_e(this.mate) > 2.25D) {
/*  73 */       this.goblinObj.func_70661_as().func_75497_a(this.mate, 0.25D);
/*  74 */     } else if ((this.matingTimeout == 0) && (this.mate.isMating())) {
/*  75 */       giveBirth();
/*     */     }
/*     */     
/*  78 */     if (this.goblinObj.func_70681_au().nextInt(35) == 0) {
/*  79 */       this.worldObj.func_72960_a(this.goblinObj, (byte)12);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean checkSufficientDoorsPresentForNewVillager() {
/*  84 */     if (!this.villageObj.func_82686_i()) {
/*  85 */       return false;
/*     */     }
/*  87 */     int i = (int)(this.villageObj.func_75567_c() * 0.35D);
/*  88 */     return getNumVillagers() < i;
/*     */   }
/*     */   
/*     */   private int getNumVillagers()
/*     */   {
/*  93 */     if ((this.worldObj == null) || (this.goblinObj == null)) {
/*  94 */       return 0;
/*     */     }
/*  96 */     List list = this.worldObj.func_72872_a(EntityGoblin.class, this.goblinObj.field_70121_D.func_72314_b(32.0D, 3.0D, 32.0D));
/*  97 */     return list != null ? list.size() : 0;
/*     */   }
/*     */   
/*     */   private void giveBirth()
/*     */   {
/* 102 */     EntityGoblin entityvillager = this.goblinObj.createChild(this.mate);
/* 103 */     this.mate.func_70873_a(6000);
/* 104 */     this.goblinObj.func_70873_a(6000);
/* 105 */     entityvillager.func_70873_a(41536);
/* 106 */     entityvillager.func_70012_b(this.goblinObj.field_70165_t, this.goblinObj.field_70163_u, this.goblinObj.field_70161_v, 0.0F, 0.0F);
/* 107 */     this.worldObj.func_72838_d(entityvillager);
/* 108 */     this.worldObj.func_72960_a(entityvillager, (byte)12);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIGoblinMate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */