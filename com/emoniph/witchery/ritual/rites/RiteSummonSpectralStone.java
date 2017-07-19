/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.entity.EntitySummonedUndead;
/*    */ import com.emoniph.witchery.item.ItemSpectralStone;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RiteRegistry;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteSummonSpectralStone extends Rite
/*    */ {
/*    */   private final int radius;
/*    */   
/*    */   public RiteSummonSpectralStone(int radius)
/*    */   {
/* 26 */     this.radius = radius;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 31 */     steps.add(new StepSummonItem(this));
/*    */   }
/*    */   
/*    */   private static class StepSummonItem extends RitualStep
/*    */   {
/*    */     private final RiteSummonSpectralStone rite;
/*    */     
/*    */     public StepSummonItem(RiteSummonSpectralStone rite) {
/* 39 */       super();
/* 40 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 45 */       if (ticks % 20L != 0L) {
/* 46 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 49 */       if (!world.field_72995_K) {
/* 50 */         int r = this.rite.radius;
/* 51 */         int r2 = r * r;
/*    */         
/* 53 */         AxisAlignedBB bb = AxisAlignedBB.func_72330_a(posX - r, posY - r, posZ - r, posX + r, posY + r, posZ + r);
/* 54 */         java.util.List entities = world.func_72872_a(EntitySummonedUndead.class, bb);
/* 55 */         Class<? extends EntitySummonedUndead> entityType = null;
/* 56 */         int count = 0;
/* 57 */         for (Object obj : entities) {
/* 58 */           EntitySummonedUndead entity = (EntitySummonedUndead)obj;
/* 59 */           if (entity.func_70092_e(0.5D + posX, posY, 0.5D + posZ) <= r2) {
/* 60 */             Class<? extends EntitySummonedUndead> foundType = entity.getClass();
/* 61 */             if (entityType == null) {
/* 62 */               entityType = foundType;
/*    */             }
/*    */             
/* 65 */             if (entityType == foundType) {
/* 66 */               count++;
/* 67 */               if (!world.field_72995_K) {
/* 68 */                 entity.func_70106_y();
/* 69 */                 ParticleEffect.PORTAL.send(SoundEffect.RANDOM_POP, entity, 1.0D, 2.0D, 16);
/*    */               }
/* 71 */               if (count >= 3) {
/*    */                 break;
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */         
/* 78 */         if (count <= 0) {
/* 79 */           RiteRegistry.RiteError("witchery.rite.missinglivingsacrifice", ritual.getInitiatingPlayerName(), world);
/* 80 */           return RitualStep.Result.ABORTED_REFUND;
/*    */         }
/*    */         
/* 83 */         ItemStack stack = new ItemStack(Witchery.Items.SPECTRAL_STONE, 1, ItemSpectralStone.metaFromCreature(entityType, count));
/* 84 */         EntityItem entity = new EntityItem(world, 0.5D + posX, posY + 1.5D, 0.5D + posZ, stack);
/* 85 */         entity.field_70159_w = 0.0D;
/* 86 */         entity.field_70181_x = 0.3D;
/* 87 */         entity.field_70179_y = 0.0D;
/* 88 */         world.func_72838_d(entity);
/*    */         
/* 90 */         ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 0.5D, 16);
/*    */       }
/* 92 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteSummonSpectralStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */