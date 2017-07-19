/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.entity.EntityDeath;
/*    */ import com.emoniph.witchery.entity.EntityDemon;
/*    */ import com.emoniph.witchery.entity.EntityImp;
/*    */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*    */ import com.emoniph.witchery.entity.EntityReflection;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteBanishDemon
/*    */   extends Rite
/*    */ {
/*    */   private final int radius;
/*    */   
/*    */   public RiteBanishDemon(int radius)
/*    */   {
/* 28 */     this.radius = radius;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*    */   {
/* 33 */     steps.add(new BanishDemonStep(this, initialStage));
/*    */   }
/*    */   
/*    */   private static class BanishDemonStep extends RitualStep {
/*    */     private final RiteBanishDemon rite;
/*    */     protected int ticksSoFar;
/*    */     
/*    */     public BanishDemonStep(RiteBanishDemon rite, int ticksSoFar) {
/* 41 */       super();
/* 42 */       this.rite = rite;
/* 43 */       this.ticksSoFar = ticksSoFar;
/*    */     }
/*    */     
/*    */     public int getCurrentStage()
/*    */     {
/* 48 */       return this.ticksSoFar;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 53 */       if (ticks % 20L != 0L) {
/* 54 */         return RitualStep.Result.STARTING;
/*    */       }
/* 56 */       SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
/*    */       
/* 58 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - this.rite.radius, posY - this.rite.radius, posZ - this.rite.radius, posX + this.rite.radius, posY + this.rite.radius, posZ + this.rite.radius);
/* 59 */       List<EntityLiving> list = world.func_72872_a(EntityLiving.class, bounds);
/* 60 */       for (EntityLiving entity : list) {
/* 61 */         if (((entity instanceof EntityDemon)) || ((entity instanceof EntityDeath)) || ((entity instanceof EntityLordOfTorment)) || ((entity instanceof EntityImp)) || ((entity instanceof EntityReflection)))
/*    */         {
/* 63 */           if (Coord.distanceSq(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) < this.rite.radius * this.rite.radius) {
/* 64 */             entity.func_70106_y();
/* 65 */             ParticleEffect.EXPLODE.send(SoundEffect.NONE, entity, 1.0D, 2.0D, 16);
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 70 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteBanishDemon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */