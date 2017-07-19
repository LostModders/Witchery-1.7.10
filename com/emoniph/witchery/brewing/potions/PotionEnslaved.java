/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityEnt;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.monster.EntityGolem;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
/*     */ 
/*     */ public class PotionEnslaved extends PotionBase implements IHandleLivingSetAttackTarget, IHandleLivingUpdate
/*     */ {
/*     */   private static final String ENSLAVER_KEY = "WITCEnslaverName";
/*     */   
/*     */   public PotionEnslaved(int id, int color)
/*     */   {
/*  25 */     super(id, true, color);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onLivingSetAttackTarget(World world, EntityLiving entity, LivingSetAttackTargetEvent event, int amplifier)
/*     */   {
/*  31 */     if ((event.target != null) && ((event.target instanceof EntityPlayer)) && ((entity instanceof EntityLiving))) {
/*  32 */       String enslaverName = getMobEnslaverName(entity);
/*  33 */       if (enslaverName.equals(event.target.func_70005_c_())) {
/*  34 */         entity.func_70624_b(null);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean setEnslaverForMob(EntityLiving entity, EntityPlayer player)
/*     */   {
/*  42 */     if ((entity == null) || (player == null)) {
/*  43 */       return false;
/*     */     }
/*  45 */     String enslaverName = entity.getEntityData().func_74779_i("WITCEnslaverName");
/*  46 */     boolean isEnslaved = (enslaverName != null) && (!enslaverName.isEmpty());
/*  47 */     if ((!isEnslaved) || (!player.func_70005_c_().equals(enslaverName))) {
/*  48 */       entity.getEntityData().func_74778_a("WITCEnslaverName", player.func_70005_c_());
/*  49 */       entity.func_70690_d(new PotionEffect(com.emoniph.witchery.Witchery.Potions.ENSLAVED.field_76415_H, Integer.MAX_VALUE));
/*  50 */       com.emoniph.witchery.util.EntityUtil.dropAttackTarget(entity);
/*  51 */       return true;
/*     */     }
/*  53 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isMobEnslavedBy(EntityLiving entity, EntityPlayer player)
/*     */   {
/*  59 */     return (player != null) && (entity != null) && (entity.getEntityData() != null) && (player.func_70005_c_().equals(entity.getEntityData().func_74779_i("WITCEnslaverName")));
/*     */   }
/*     */   
/*     */   public static boolean canCreatureBeEnslaved(EntityLivingBase entityLiving)
/*     */   {
/*  64 */     if ((entityLiving instanceof EntityLiving)) {
/*  65 */       if (((entityLiving instanceof IBossDisplayData)) || ((entityLiving instanceof EntityGolem)) || ((entityLiving instanceof com.emoniph.witchery.entity.EntityDemon)) || ((entityLiving instanceof EntityWitch)) || ((entityLiving instanceof com.emoniph.witchery.entity.EntityImp)) || ((entityLiving instanceof EntityEnt)))
/*     */       {
/*     */ 
/*  68 */         return false;
/*     */       }
/*  70 */       return true;
/*     */     }
/*     */     
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isMobEnslaved(EntityLiving entity)
/*     */   {
/*  78 */     if (entity == null) {
/*  79 */       return false;
/*     */     }
/*  81 */     String enslaverName = entity.getEntityData().func_74779_i("WITCEnslaverName");
/*  82 */     return (enslaverName != null) && (!enslaverName.isEmpty());
/*     */   }
/*     */   
/*     */   public static String getMobEnslaverName(EntityLiving entity)
/*     */   {
/*  87 */     if (entity == null) {
/*  88 */       return "";
/*     */     }
/*  90 */     String enslaverName = entity.getEntityData().func_74779_i("WITCEnslaverName");
/*  91 */     return enslaverName;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*     */   {
/*  97 */     if ((!world.field_72995_K) && (world.func_82737_E() % 20L == 3L) && ((entity instanceof EntityCreature))) {
/*  98 */       EntityCreature creature = (EntityCreature)entity;
/*  99 */       for (Object obj : creature.field_70715_bh.field_75782_a) {
/* 100 */         EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
/* 101 */         if ((task.field_75733_a instanceof EntityAIEnslaverHurtByTarget)) {
/* 102 */           return;
/*     */         }
/*     */       }
/* 105 */       creature.field_70715_bh.func_75776_a(1, new EntityAIEnslaverHurtByTarget(creature));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionEnslaved.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */