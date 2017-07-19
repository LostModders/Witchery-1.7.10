/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.monster.EntityMob;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteTransposeMobs extends com.emoniph.witchery.ritual.Rite
/*    */ {
/*    */   protected final int radius;
/*    */   protected final int pulses;
/*    */   protected final int minDistance;
/*    */   
/*    */   public RiteTransposeMobs(int radius, int minDistance, int pulses)
/*    */   {
/* 22 */     this.radius = radius;
/* 23 */     this.pulses = pulses;
/* 24 */     this.minDistance = minDistance;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int initialStep)
/*    */   {
/* 29 */     steps.add(new StepTeleportation(this, initialStep));
/*    */   }
/*    */   
/*    */   private static class StepTeleportation extends RitualStep
/*    */   {
/*    */     private final RiteTransposeMobs rite;
/*    */     private int step;
/*    */     
/*    */     public StepTeleportation(RiteTransposeMobs rite, int initialStep) {
/* 38 */       super();
/* 39 */       this.rite = rite;
/* 40 */       this.step = initialStep;
/*    */     }
/*    */     
/*    */     public int getCurrentStage()
/*    */     {
/* 45 */       return this.step;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 50 */       if (ticks % 20L != 0L) {
/* 51 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 54 */       this.step += 1;
/* 55 */       int r = this.rite.radius;
/* 56 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, 1.0D, posZ - r, posX + r, posY - 1, posZ + r);
/* 57 */       for (Object obj : world.func_72872_a(EntityMob.class, bounds)) {
/* 58 */         EntityMob entity = (EntityMob)obj;
/* 59 */         world.func_72900_e(entity);
/* 60 */         entity.field_70128_L = false;
/* 61 */         int activeRadius = this.rite.radius;
/* 62 */         int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 63 */         if (ax > activeRadius) {
/* 64 */           ax += this.rite.minDistance * 2;
/*    */         }
/* 66 */         int x = posX - this.rite.radius - this.rite.minDistance + ax;
/*    */         
/* 68 */         int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/* 69 */         if (az > activeRadius) {
/* 70 */           az += this.rite.minDistance * 2;
/*    */         }
/*    */         
/* 73 */         int z = posZ - this.rite.radius - this.rite.minDistance + az;
/* 74 */         entity.func_70012_b(x, posY, z, 0.0F, 0.0F);
/* 75 */         world.func_72838_d(entity);
/* 76 */         ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 2.0D, 16);
/*    */       }
/*    */       
/* 79 */       return this.step >= this.rite.pulses ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteTransposeMobs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */