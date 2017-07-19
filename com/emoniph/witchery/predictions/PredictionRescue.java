/*     */ package com.emoniph.witchery.predictions;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityOwl;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ public class PredictionRescue extends PredictionAlwaysForced
/*     */ {
/*     */   private final Class<? extends EntityLiving> entityClass;
/*     */   
/*     */   public PredictionRescue(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, Class<? extends EntityLiving> entityClass)
/*     */   {
/*  27 */     super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
/*  28 */     this.entityClass = entityClass;
/*     */   }
/*     */   
/*     */   public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld)
/*     */   {
/*  33 */     if (!event.isCanceled()) {
/*  34 */       Entity attackingEntity = event.source.func_76346_g();
/*  35 */       if ((attackingEntity != null) && ((attackingEntity instanceof EntityLivingBase))) {
/*     */         try {
/*  37 */           int x = MathHelper.func_76128_c(player.field_70165_t);
/*  38 */           int y = MathHelper.func_76128_c(player.field_70163_u);
/*  39 */           int z = MathHelper.func_76128_c(player.field_70161_v);
/*     */           
/*  41 */           if (!world.field_72995_K) {
/*  42 */             int MAX_DISTANCE = 4;
/*  43 */             int MIN_DISTANCE = 2;
/*     */             
/*  45 */             int activeRadius = 2;
/*  46 */             int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  47 */             if (ax > activeRadius) {
/*  48 */               ax += 4;
/*     */             }
/*  50 */             int nx = x - 4 + ax;
/*     */             
/*  52 */             int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  53 */             if (az > activeRadius) {
/*  54 */               az += 4;
/*     */             }
/*     */             
/*  57 */             int nz = z - 4 + az;
/*     */             
/*  59 */             int ny = y;
/*  60 */             while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
/*  61 */               ny++;
/*     */             }
/*     */             
/*     */ 
/*  65 */             while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
/*  66 */               ny--;
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*  71 */             int hy = 0;
/*  72 */             while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
/*  73 */               hy++;
/*     */             }
/*     */             
/*  76 */             Constructor ctor = this.entityClass.getConstructor(new Class[] { World.class });
/*  77 */             EntityLiving entity = (EntityLiving)ctor.newInstance(new Object[] { world });
/*  78 */             if (hy >= entity.field_70131_O)
/*     */             {
/*  80 */               entity.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
/*  81 */               world.func_72838_d(entity);
/*     */               
/*  83 */               IEntityLivingData entitylivingData = null;
/*  84 */               entitylivingData = entity.func_110161_a(entitylivingData);
/*     */               
/*  86 */               if ((entity instanceof EntityOwl)) {
/*  87 */                 ((EntityOwl)entity).setTimeToLive(300);
/*     */               }
/*     */               
/*  90 */               entity.func_70624_b((EntityLivingBase)attackingEntity);
/*  91 */               if ((entity instanceof EntityCreature)) {
/*  92 */                 ((EntityCreature)entity).func_70784_b((EntityLivingBase)attackingEntity);
/*  93 */                 ((EntityCreature)entity).func_70604_c((EntityLivingBase)attackingEntity);
/*     */               }
/*     */               
/*  96 */               ParticleEffect.SMOKE.send(SoundEffect.NONE, entity, 0.5D, 2.0D, 16);
/*     */               
/*  98 */               return true;
/*     */             }
/* 100 */             return false;
/*     */           }
/*     */         }
/*     */         catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 109 */         Log.instance().debug(String.format("Prediction for rescue by fulfilled as predicted", new Object[] { attackingEntity.toString() }));
/*     */         
/* 111 */         return false;
/*     */       }
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionRescue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */