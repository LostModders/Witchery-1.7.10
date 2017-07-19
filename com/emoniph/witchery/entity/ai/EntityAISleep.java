/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import com.emoniph.witchery.common.ExtendedVillager;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.village.VillageDoorInfo;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAISleep extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityVillager villager;
/*     */   private VillageDoorInfo doorInfo;
/*  16 */   private int insidePosX = -1;
/*  17 */   private int insidePosZ = -1;
/*     */   private World world;
/*     */   Village village;
/*     */   
/*     */   public EntityAISleep(EntityVillager villager) {
/*  22 */     this.villager = villager;
/*  23 */     this.world = villager.field_70170_p;
/*  24 */     func_75248_a(7);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a() {
/*  28 */     long time = this.world.func_72820_D() % 24000L;
/*  29 */     if ((time < 13000L) || (time >= 23999L) || (this.villager.field_70737_aN > 0)) {
/*  30 */       return false;
/*     */     }
/*     */     
/*  33 */     if (this.villager.func_70681_au().nextInt(50) != 0) {
/*  34 */       return false;
/*     */     }
/*     */     
/*  37 */     int i = MathHelper.func_76128_c(this.villager.field_70165_t);
/*  38 */     int j = MathHelper.func_76128_c(this.villager.field_70163_u);
/*  39 */     int k = MathHelper.func_76128_c(this.villager.field_70161_v);
/*     */     
/*  41 */     Village village = this.world.field_72982_D.func_75550_a(i, j, k, 14);
/*     */     
/*  43 */     if (village == null) {
/*  44 */       return false;
/*     */     }
/*  46 */     this.doorInfo = village.func_75569_c(i, j, k);
/*     */     
/*  48 */     float DOOR_DIST = 4.0F;
/*  49 */     boolean inside = this.villager.func_70092_e(this.doorInfo.func_75471_a() + 0.5D, this.doorInfo.func_75473_b(), this.doorInfo.func_75472_c() + 0.5D) < 16.0D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */     if (this.villager.field_70170_p.func_72937_j(i, j, k)) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     int count = 0;
/*  61 */     for (int x = i - 1; x <= i + 1; x++) {
/*  62 */       for (int z = k - 1; z <= k + 1; z++) {
/*  63 */         if ((!this.villager.field_70170_p.func_72937_j(x, j, z)) && (this.villager.field_70170_p.func_147439_a(x, j + 1, z).func_149688_o().func_76222_j()))
/*     */         {
/*  65 */           count++;
/*     */         }
/*     */       }
/*     */     }
/*  69 */     if (count < 4) {
/*  70 */       return false;
/*     */     }
/*     */     
/*  73 */     count = 6;
/*     */     
/*  75 */     count = 0;
/*  76 */     for (int x = -1; x <= 1; x++) {
/*  77 */       for (int z = -1; z <= 1; z++) {
/*  78 */         if (!this.world.func_147439_a(x + i, j - 1, z + k).isReplaceable(this.world, x + i, j - 1, z + k)) {
/*  79 */           count++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  85 */     return count >= 6;
/*     */   }
/*     */   
/*     */   public void func_75249_e()
/*     */   {
/*  90 */     ExtendedVillager ext = ExtendedVillager.get(this.villager);
/*  91 */     if (ext != null) {
/*  92 */       ext.setSleeping(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/*  97 */     this.village = null;
/*  98 */     ExtendedVillager ext = ExtendedVillager.get(this.villager);
/*  99 */     if (ext != null) {
/* 100 */       ext.setSleeping(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_75253_b() {
/* 105 */     long time = this.world.func_72820_D() % 24000L;
/* 106 */     return (time > 13000L) && (time < 23999L) && (this.villager.field_70737_aN == 0);
/*     */   }
/*     */   
/*     */   public void func_75246_d() {
/* 110 */     ExtendedVillager ext = ExtendedVillager.get(this.villager);
/* 111 */     if (ext != null) {
/* 112 */       ext.incrementSleepingTicks();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAISleep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */