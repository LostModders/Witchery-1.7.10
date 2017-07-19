/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.familiar.Familiar;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteBindFamiliar extends Rite
/*    */ {
/*    */   private final int radius;
/*    */   
/*    */   public RiteBindFamiliar(int radius)
/*    */   {
/* 24 */     this.radius = radius;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 29 */     steps.add(new StepBindFamiliar(this));
/*    */   }
/*    */   
/*    */   private static class StepBindFamiliar extends RitualStep
/*    */   {
/*    */     private final RiteBindFamiliar rite;
/*    */     
/*    */     public StepBindFamiliar(RiteBindFamiliar rite) {
/* 37 */       super();
/* 38 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 43 */       if (ticks % 20L != 0L) {
/* 44 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 47 */       if (!world.field_72995_K) {
/* 48 */         int r = this.rite.radius;
/* 49 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
/*    */         
/* 51 */         boolean bound = false;
/*    */         
/* 53 */         ArrayList<EntityPlayer> boundPlayers = new ArrayList();
/*    */         
/* 55 */         for (Object obj : world.func_72872_a(EntityTameable.class, bounds)) {
/* 56 */           EntityTameable tameable = (EntityTameable)obj;
/* 57 */           if ((tameable.func_70909_n()) && (Familiar.canBecomeFamiliar(tameable)) && (Coord.distance(tameable.field_70165_t, tameable.field_70163_u, tameable.field_70161_v, posX, posY, posZ) <= r)) {
/* 58 */             EntityLivingBase player = tameable.func_70902_q();
/* 59 */             if ((player != null) && ((player instanceof EntityPlayer)) && (Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= r) && (!boundPlayers.contains(player))) {
/* 60 */               Familiar.bindToPlayer((EntityPlayer)player, tameable);
/* 61 */               boundPlayers.add((EntityPlayer)player);
/* 62 */               bound = true;
/*    */             }
/*    */           }
/*    */         }
/* 66 */         if (bound) {
/* 67 */           ParticleEffect.HEART.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0D, 3.0D, 16);
/*    */         } else {
/* 69 */           return RitualStep.Result.ABORTED_REFUND;
/*    */         }
/*    */       }
/* 72 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteBindFamiliar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */