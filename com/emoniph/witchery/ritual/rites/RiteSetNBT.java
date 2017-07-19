/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteSetNBT extends Rite
/*    */ {
/*    */   private final int radius;
/*    */   private final String nbtKey;
/*    */   private final int nbtValue;
/*    */   private final int nbtCovenBonus;
/*    */   
/*    */   public RiteSetNBT(int radius, String nbtKey, int value, int covenMemberBonus)
/*    */   {
/* 26 */     this.radius = radius;
/* 27 */     this.nbtKey = nbtKey;
/* 28 */     this.nbtValue = value;
/* 29 */     this.nbtCovenBonus = covenMemberBonus;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 34 */     steps.add(new StepSetNBT(this));
/*    */   }
/*    */   
/*    */   private static class StepSetNBT extends RitualStep
/*    */   {
/*    */     private final RiteSetNBT rite;
/*    */     
/*    */     public StepSetNBT(RiteSetNBT rite) {
/* 42 */       super();
/* 43 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 48 */       if (ticks % 20L != 0L) {
/* 49 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 52 */       if (!world.field_72995_K) {
/* 53 */         int r = this.rite.radius;
/* 54 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
/*    */         
/* 56 */         boolean bound = false;
/*    */         
/* 58 */         ArrayList<EntityPlayer> boundPlayers = new ArrayList();
/*    */         
/* 60 */         for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
/* 61 */           EntityPlayer player = (EntityPlayer)obj;
/* 62 */           if (Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= r) {
/* 63 */             NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 64 */             if (nbtPlayer != null) {
/* 65 */               nbtPlayer.func_74768_a(this.rite.nbtKey, this.rite.nbtValue + ritual.covenSize * this.rite.nbtCovenBonus);
/* 66 */               bound = true;
/*    */             }
/*    */           }
/*    */         }
/* 70 */         if (bound) {
/* 71 */           ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0D, 3.0D, 16);
/*    */         } else {
/* 73 */           return RitualStep.Result.ABORTED_REFUND;
/*    */         }
/*    */       }
/* 76 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteSetNBT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */