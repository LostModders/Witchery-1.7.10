/*    */ package com.emoniph.witchery.infusion.infusions.spirit;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntitySpectre;
/*    */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*    */ import com.emoniph.witchery.util.EntityUtil;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IEntityLivingData;
/*    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class InfusedSpiritSentinalEffect extends InfusedSpiritEffect
/*    */ {
/*    */   public InfusedSpiritSentinalEffect(int id, int spirits, int spectres, int banshees, int poltergeists)
/*    */   {
/* 21 */     super(id, "sentinal", spirits, spectres, banshees, poltergeists);
/*    */   }
/*    */   
/*    */   public int getCooldownTicks()
/*    */   {
/* 26 */     return TimeUtil.secsToTicks(30);
/*    */   }
/*    */   
/*    */   public double getRadius()
/*    */   {
/* 31 */     return 8.0D;
/*    */   }
/*    */   
/*    */   public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities) {
/*    */     int number;
/* 36 */     if (triggered) {
/* 37 */       number = foundEntities.size() > 1 ? 1 : 2;
/* 38 */       for (EntityLivingBase entity : foundEntities) {
/* 39 */         for (int i = 0; i < number; i++) {
/* 40 */           int blockX = MathHelper.func_76128_c(entity.field_70165_t);
/* 41 */           int blockY = MathHelper.func_76128_c(entity.field_70163_u);
/* 42 */           int blockZ = MathHelper.func_76128_c(entity.field_70161_v);
/* 43 */           EntitySpectre creature = (EntitySpectre)InfusionInfernal.spawnCreature(tile.func_145831_w(), EntitySpectre.class, blockX, blockY, blockZ, entity, 1, 1, ParticleEffect.INSTANT_SPELL, SoundEffect.WITCHERY_MOB_SPECTRE_SPECTRE_SAY);
/*    */           
/*    */ 
/* 46 */           if (creature != null) {
/* 47 */             EntityUtil.setTarget(creature, entity);
/* 48 */             creature.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(creature, entity.getClass(), 0, true));
/* 49 */             creature.setTimeToLive(TimeUtil.secsToTicks(30));
/* 50 */             creature.func_110161_a((IEntityLivingData)null);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 56 */     return triggered;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/spirit/InfusedSpiritSentinalEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */