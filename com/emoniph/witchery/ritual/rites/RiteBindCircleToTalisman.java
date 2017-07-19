/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.ritual.Circle;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RiteBindCircleToTalisman
/*    */   extends Rite
/*    */ {
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 25 */     steps.add(new StepSummonItem(this));
/*    */   }
/*    */   
/*    */   private static class StepSummonItem extends RitualStep
/*    */   {
/*    */     private final RiteBindCircleToTalisman rite;
/*    */     
/*    */     public StepSummonItem(RiteBindCircleToTalisman rite) {
/* 33 */       super();
/* 34 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 39 */       if (ticks % 20L != 0L) {
/* 40 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 43 */       if (!world.field_72995_K) {
/* 44 */         Circle a = new Circle(16);
/* 45 */         Circle b = new Circle(28);
/* 46 */         Circle c = new Circle(40);
/* 47 */         Circle _ = new Circle(0);
/*    */         
/* 49 */         Circle[][] PATTERN = { { _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ }, { _, _, _, _, _, c, c, c, c, c, c, c, _, _, _, _, _ }, { _, _, _, _, c, _, _, _, _, _, _, _, c, _, _, _, _ }, { _, _, _, c, _, _, b, b, b, b, b, _, _, c, _, _, _ }, { _, _, c, _, _, b, _, _, _, _, _, b, _, _, c, _, _ }, { _, c, _, _, b, _, _, a, a, a, _, _, b, _, _, c, _ }, { _, c, _, b, _, _, a, _, _, _, a, _, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, _, a, _, _, _, a, _, _, b, _, c, _ }, { _, c, _, _, b, _, _, a, a, a, _, _, b, _, _, c, _ }, { _, _, c, _, _, b, _, _, _, _, _, b, _, _, c, _, _ }, { _, _, _, c, _, _, b, b, b, b, b, _, _, c, _, _, _ }, { _, _, _, _, c, _, _, _, _, _, _, _, c, _, _, _, _ }, { _, _, _, _, _, c, c, c, c, c, c, c, _, _, _, _, _ }, { _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ } };
/*    */         
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 68 */         int offsetZ = (PATTERN.length - 1) / 2;
/* 69 */         for (int z = 0; z < PATTERN.length - 1; z++) {
/* 70 */           int worldZ = posZ - offsetZ + z;
/* 71 */           int offsetX = (PATTERN[z].length - 1) / 2;
/* 72 */           for (int x = 0; x < PATTERN[z].length; x++) {
/* 73 */             int worldX = posX - offsetX + x;
/* 74 */             PATTERN[(PATTERN.length - 1 - z)][x].addGlyph(world, worldX, posY, worldZ, true);
/*    */           }
/*    */         }
/*    */         
/* 78 */         int metadata = c.getExclusiveMetadataValue() << 6 | b.getExclusiveMetadataValue() << 3 | a.getExclusiveMetadataValue();
/*    */         
/* 80 */         ItemStack itemstack = new ItemStack(Witchery.Items.CIRCLE_TALISMAN, 1, metadata);
/* 81 */         EntityItem entity = new EntityItem(world, posX, posY + 0.05D, posZ, itemstack);
/* 82 */         world.func_72838_d(entity);
/* 83 */         ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 1.0D, 16);
/*    */         
/* 85 */         if (metadata > 0) {
/* 86 */           world.func_147468_f(posX, posY, posZ);
/*    */         }
/*    */       }
/* 89 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteBindCircleToTalisman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */