/*     */ package com.emoniph.witchery.predictions;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import com.emoniph.witchery.entity.IOwnable;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ public class PredictionFight extends Prediction
/*     */ {
/*     */   private final Class<? extends EntityLiving> entityClass;
/*     */   private final boolean bindTameable;
/*     */   
/*     */   public PredictionFight(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, Class<? extends EntityLiving> entityClass, boolean bindTameable)
/*     */   {
/*  27 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
/*  28 */     this.entityClass = entityClass;
/*  29 */     this.bindTameable = bindTameable;
/*     */   }
/*     */   
/*     */   public boolean doSelfFulfillment(World world, EntityPlayer player)
/*     */   {
/*     */     try {
/*  35 */       int x = MathHelper.func_76128_c(player.field_70165_t);
/*  36 */       int y = MathHelper.func_76128_c(player.field_70163_u);
/*  37 */       int z = MathHelper.func_76128_c(player.field_70161_v);
/*     */       
/*  39 */       if (!world.field_72995_K) {
/*  40 */         int MAX_DISTANCE = 4;
/*  41 */         int MIN_DISTANCE = 2;
/*     */         
/*  43 */         int activeRadius = 2;
/*  44 */         int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  45 */         if (ax > activeRadius) {
/*  46 */           ax += 4;
/*     */         }
/*  48 */         int nx = x - 4 + ax;
/*     */         
/*  50 */         int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  51 */         if (az > activeRadius) {
/*  52 */           az += 4;
/*     */         }
/*     */         
/*  55 */         int nz = z - 4 + az;
/*     */         
/*  57 */         int ny = y;
/*  58 */         while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
/*  59 */           ny++;
/*     */         }
/*     */         
/*     */ 
/*  63 */         while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
/*  64 */           ny--;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  69 */         int hy = 0;
/*  70 */         while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
/*  71 */           hy++;
/*     */         }
/*     */         
/*  74 */         Constructor ctor = this.entityClass.getConstructor(new Class[] { World.class });
/*  75 */         EntityLiving entity = (EntityLiving)ctor.newInstance(new Object[] { world });
/*  76 */         if (hy >= entity.field_70131_O) {
/*  77 */           boolean bound = false;
/*  78 */           if ((entity instanceof EntityDemon)) {
/*  79 */             ((EntityDemon)entity).setPlayerCreated(true);
/*  80 */           } else if ((this.bindTameable) && ((entity instanceof EntityTameable))) {
/*  81 */             ((EntityTameable)entity).func_70903_f(true);
/*  82 */             com.emoniph.witchery.util.TameableUtil.setOwner((EntityTameable)entity, player);
/*  83 */             bound = true;
/*  84 */           } else if ((this.bindTameable) && ((entity instanceof IOwnable))) {
/*  85 */             ((IOwnable)entity).setOwner(player.func_70005_c_());
/*  86 */             bound = true;
/*     */           }
/*  88 */           entity.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
/*  89 */           world.func_72838_d(entity);
/*  90 */           Log.instance().debug(String.format("Forcing prediction for attack by %s", new Object[] { entity.toString() }));
/*     */           
/*  92 */           IEntityLivingData entitylivingData = null;
/*  93 */           entitylivingData = entity.func_110161_a(entitylivingData);
/*     */           
/*  95 */           if (!bound) {
/*  96 */             entity.func_70624_b(player);
/*     */           }
/*     */           
/*  99 */           ParticleEffect.SMOKE.send(com.emoniph.witchery.util.SoundEffect.NONE, entity, 0.5D, 2.0D, 16);
/*     */         } else {
/* 101 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
/*     */     
/*     */ 
/*     */ 
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld)
/*     */   {
/* 114 */     if (!event.isCanceled()) {
/* 115 */       Entity attackingEntity = event.source.func_76346_g();
/* 116 */       if (attackingEntity != null) {
/* 117 */         boolean attackedByCreature = this.entityClass.isAssignableFrom(attackingEntity.getClass());
/* 118 */         if (attackedByCreature) {
/* 119 */           Log.instance().debug(String.format("Prediction for attack by %s fulfilled as predicted", new Object[] { attackingEntity.toString() }));
/*     */         }
/* 121 */         return attackedByCreature;
/*     */       }
/*     */     }
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionFight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */