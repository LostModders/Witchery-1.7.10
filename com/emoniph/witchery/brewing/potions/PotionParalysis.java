/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIMoveTowardsVampire;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ public class PotionParalysis extends PotionBase implements IHandleLivingUpdate, IHandleLivingHurt
/*     */ {
/*     */   public PotionParalysis(int id, int color)
/*     */   {
/*  29 */     super(id, true, color);
/*  30 */     setIncurable();
/*     */   }
/*     */   
/*     */   public void postContructInitialize()
/*     */   {
/*  35 */     func_111184_a(SharedMonsterAttributes.field_111263_d, "E69059D5-CAE6-4695-9BE3-C6F0F22151E8", -40.0D, 2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_111185_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier)
/*     */   {
/*  41 */     if (canApplyToEntity(entity, amplifier)) {
/*  42 */       super.func_111185_a(entity, attributes, amplifier);
/*  43 */     } else if (isVillager(entity)) {
/*  44 */       EntityCreature creature = (EntityCreature)entity;
/*  45 */       creature.func_70624_b(null);
/*  46 */       creature.func_70604_c(null);
/*  47 */       creature.func_70784_b(null);
/*  48 */       creature.field_70714_bg.func_75776_a(0, new EntityAIMoveTowardsVampire(creature, 0.8D, 1.0F, 16.0F));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_111187_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier)
/*     */   {
/*  55 */     if (canApplyToEntity(entity, amplifier)) {
/*  56 */       super.func_111187_a(entity, attributes, amplifier);
/*  57 */     } else if ((isVillager(entity)) && (amplifier >= 5)) {
/*  58 */       EntityCreature creature = (EntityCreature)entity;
/*  59 */       Iterator itr = creature.field_70714_bg.field_75782_a.iterator();
/*  60 */       EntityAIBase task = null;
/*  61 */       while (itr.hasNext()) {
/*  62 */         EntityAITasks.EntityAITaskEntry entityaitaskentry = (EntityAITasks.EntityAITaskEntry)itr.next();
/*  63 */         EntityAIBase entityaibase1 = entityaitaskentry.field_75733_a;
/*  64 */         if ((entityaibase1 instanceof EntityAIMoveTowardsVampire)) {
/*  65 */           task = entityaibase1;
/*  66 */           break;
/*     */         }
/*     */       }
/*  69 */       if (task != null)
/*     */       {
/*  71 */         creature.field_70714_bg.func_85156_a(task);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*     */   {
/*  79 */     if (canApplyToEntity(entity, amplifier)) {
/*  80 */       if (!world.field_72995_K) {
/*  81 */         if ((entity instanceof EntityCreeper)) {
/*  82 */           ((EntityCreeper)entity).func_70829_a(-1);
/*     */         }
/*     */         
/*  85 */         if ((amplifier >= 4) && (duration <= 1) && ((entity instanceof EntityPlayer))) {
/*  86 */           EntityPlayer player = (EntityPlayer)entity;
/*  87 */           player.func_70690_d(new PotionEffect(Witchery.Potions.QUEASY.field_76415_H, TimeUtil.secsToTicks(90), 0, true));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  92 */       if ((entity.field_70173_aa % 20 != 2) || (!isVillager(entity)) || (amplifier < 5))
/*     */       {
/*     */ 
/*  95 */         entity.field_70181_x = -0.2D;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean canApplyToEntity(EntityLivingBase entity, int amplifier) {
/* 101 */     if ((entity instanceof IBossDisplayData))
/* 102 */       return false;
/* 103 */     if ((amplifier >= 5) && (isVillager(entity)))
/* 104 */       return false;
/* 105 */     if (((entity instanceof EntityPlayer)) && (amplifier < 2)) {
/* 106 */       return false;
/*     */     }
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isVillager(Entity entity)
/*     */   {
/* 113 */     return ((entity instanceof EntityVillager)) || ((entity instanceof EntityVillageGuard));
/*     */   }
/*     */   
/*     */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*     */   {
/* 118 */     if ((!world.field_72995_K) && (amplifier >= 4) && (event.ammount >= 1.0F)) {
/* 119 */       entity.func_82170_o(this.field_76415_H);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean handleAllHurtEvents()
/*     */   {
/* 125 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionParalysis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */