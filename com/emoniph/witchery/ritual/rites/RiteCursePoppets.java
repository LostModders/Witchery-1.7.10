/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.item.ItemPoppet;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*    */ import com.emoniph.witchery.util.ChatUtil;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteCursePoppets extends com.emoniph.witchery.ritual.Rite
/*    */ {
/*    */   private final int level;
/*    */   
/*    */   public RiteCursePoppets(int level)
/*    */   {
/* 23 */     this.level = level;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 28 */     steps.add(new StepCursePoppets(this));
/*    */   }
/*    */   
/*    */   private static class StepCursePoppets extends RitualStep
/*    */   {
/*    */     private final RiteCursePoppets rite;
/*    */     
/*    */     public StepCursePoppets(RiteCursePoppets rite) {
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
/* 46 */       if (!world.field_72995_K)
/*    */       {
/* 48 */         boolean complete = false;
/*    */         
/* 50 */         net.minecraft.entity.player.EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
/* 51 */         boolean curseMaster = (curseMasterPlayer != null) && (com.emoniph.witchery.familiar.Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer));
/*    */         
/* 53 */         if (curseMaster) {
/* 54 */           Iterator i$ = ritual.sacrificedItems.iterator(); if (i$.hasNext()) { RitualStep.SacrificedItem item = (RitualStep.SacrificedItem)i$.next();
/* 55 */             if ((item.itemstack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) && (item.itemstack.func_77960_j() == 1)) {
/* 56 */               net.minecraft.entity.EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, item.itemstack, Integer.valueOf(1));
/* 57 */               if ((entity != null) && 
/* 58 */                 (!Witchery.Items.POPPET.poppetProtectionActivated(curseMasterPlayer, null, entity, true))) {
/* 59 */                 Witchery.Items.POPPET.destroyAntiVoodooPoppets(curseMasterPlayer, entity, 10);
/*    */               }
/*    */               
/* 62 */               complete = true;
/*    */             }
/*    */             
/*    */           }
/*    */         }
/* 67 */         else if (curseMasterPlayer != null) {
/* 68 */           ChatUtil.sendTranslated(curseMasterPlayer, "witchery.rite.requirescursemastery", new Object[0]);
/*    */         }
/*    */         
/*    */ 
/*    */ 
/* 73 */         if (complete) {
/* 74 */           ParticleEffect.FLAME.send(com.emoniph.witchery.util.SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*    */         }
/*    */         else {
/* 77 */           return RitualStep.Result.ABORTED_REFUND;
/*    */         }
/*    */       }
/* 80 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteCursePoppets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */