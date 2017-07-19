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
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteInfusePlayers extends Rite
/*    */ {
/*    */   private final Infusion infusion;
/*    */   private final int charges;
/*    */   private final int radius;
/*    */   
/*    */   public RiteInfusePlayers(Infusion infusion, int charges, int radius)
/*    */   {
/* 25 */     this.infusion = infusion;
/* 26 */     this.charges = charges;
/* 27 */     this.radius = radius;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 32 */     steps.add(new StepInfusePlayers(this));
/*    */   }
/*    */   
/*    */   private static class StepInfusePlayers extends RitualStep
/*    */   {
/*    */     private final RiteInfusePlayers rite;
/*    */     
/*    */     public StepInfusePlayers(RiteInfusePlayers rite) {
/* 40 */       super();
/* 41 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 46 */       if (ticks % 20L != 0L) {
/* 47 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 50 */       if (!world.field_72995_K) {
/* 51 */         int r = this.rite.radius;
/* 52 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
/*    */         
/* 54 */         for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
/* 55 */           EntityPlayer player = (EntityPlayer)obj;
/* 56 */           if (Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= r) {
/* 57 */             player.func_70097_a(DamageSource.field_76376_m, 100.0F);
/* 58 */             if (player.func_110143_aJ() > 0.1F) {
/* 59 */               this.rite.infusion.infuse(player, this.rite.charges);
/*    */             }
/*    */           }
/*    */         }
/* 63 */         ParticleEffect.HUGE_EXPLOSION.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0D, 3.0D, 16);
/*    */       }
/* 65 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteInfusePlayers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */