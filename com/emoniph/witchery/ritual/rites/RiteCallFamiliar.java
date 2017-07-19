/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.familiar.Familiar;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteCallFamiliar extends Rite
/*    */ {
/*    */   private final int radius;
/*    */   
/*    */   public RiteCallFamiliar(int radius)
/*    */   {
/* 23 */     this.radius = radius;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 28 */     steps.add(new StepCallFamiliar(this));
/*    */   }
/*    */   
/*    */   private static class StepCallFamiliar extends RitualStep
/*    */   {
/*    */     private final RiteCallFamiliar rite;
/*    */     
/*    */     public StepCallFamiliar(RiteCallFamiliar rite) {
/* 36 */       super();
/* 37 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 42 */       if (ticks % 20L != 0L) {
/* 43 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 46 */       if (!world.field_72995_K) {
/* 47 */         int r = this.rite.radius;
/* 48 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
/*    */         
/* 50 */         boolean bound = false;
/*    */         
/* 52 */         ArrayList<EntityPlayer> boundPlayers = new ArrayList();
/*    */         
/* 54 */         for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
/* 55 */           EntityPlayer player = (EntityPlayer)obj;
/* 56 */           EntityTameable entity = Familiar.getFamiliarEntity(player);
/* 57 */           if (entity != null) {
/* 58 */             ItemGeneral.teleportToLocation(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_71093_bK, entity, false);
/* 59 */             bound = true;
/*    */           } else {
/* 61 */             EntityTameable familiar = Familiar.summonFamiliar(player, 0.5D + posX, 0.001D + posY, 0.5D + posZ);
/* 62 */             if (familiar != null) {
/* 63 */               bound = true;
/*    */             }
/*    */           }
/*    */         }
/* 67 */         if (bound) {
/* 68 */           ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 1.0D, 2.0D, 16);
/*    */         } else {
/* 70 */           return RitualStep.Result.ABORTED_REFUND;
/*    */         }
/*    */       }
/* 73 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteCallFamiliar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */