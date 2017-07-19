/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.FurnaceRecipes;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteCookItem extends com.emoniph.witchery.ritual.Rite
/*    */ {
/*    */   private final float radius;
/*    */   private final double burnChance;
/*    */   
/*    */   public RiteCookItem(float radius, double burnChance)
/*    */   {
/* 24 */     this.radius = radius;
/* 25 */     this.burnChance = burnChance;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*    */   {
/* 30 */     steps.add(new StepCookItem(this));
/*    */   }
/*    */   
/*    */   private static class StepCookItem extends RitualStep
/*    */   {
/*    */     private final RiteCookItem rite;
/*    */     
/*    */     public StepCookItem(RiteCookItem rite) {
/* 38 */       super();
/* 39 */       this.rite = rite;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 44 */       if (ticks % 20L != 0L) {
/* 45 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 48 */       if (!world.field_72995_K)
/*    */       {
/* 50 */         ArrayList<EntityItem> items = this.rite.getItemsInRadius(world, posX, posY, posZ, this.rite.radius);
/* 51 */         int count = 0;
/* 52 */         for (EntityItem item : items) {
/* 53 */           ItemStack cookedStack = FurnaceRecipes.func_77602_a().func_151395_a(item.func_92059_d());
/* 54 */           if ((cookedStack != null) && ((cookedStack.func_77973_b() instanceof ItemFood)) && (item.func_92059_d().field_77994_a > 0)) {
/* 55 */             int size = item.func_92059_d().field_77994_a;
/* 56 */             int burnCount = 0;
/* 57 */             for (int i = 0; i < size; i++) {
/* 58 */               if (world.field_73012_v.nextDouble() < this.rite.burnChance) {
/* 59 */                 burnCount++;
/*    */               }
/*    */             }
/* 62 */             item.func_70106_y();
/* 63 */             if (size - burnCount > 0) {
/* 64 */               cookedStack.field_77994_a = (size - burnCount);
/* 65 */               EntityItem cookedEntity = new EntityItem(world, posX, posY + 0.05D, posZ, cookedStack);
/* 66 */               cookedEntity.field_70159_w = 0.0D;
/* 67 */               cookedEntity.field_70179_y = 0.0D;
/* 68 */               world.func_72838_d(cookedEntity);
/*    */             }
/* 70 */             if (burnCount > 0) {
/* 71 */               EntityItem burntEntity = new EntityItem(world, posX, posY + 0.05D, posZ, new ItemStack(Items.field_151044_h, burnCount, 1));
/* 72 */               burntEntity.field_70159_w = 0.0D;
/* 73 */               burntEntity.field_70179_y = 0.0D;
/* 74 */               world.func_72838_d(burntEntity);
/*    */             }
/* 76 */             count++;
/*    */           }
/*    */         }
/*    */         
/* 80 */         if (count == 0) {
/* 81 */           return RitualStep.Result.ABORTED_REFUND;
/*    */         }
/*    */         
/* 84 */         ParticleEffect.FLAME.send(SoundEffect.MOB_GHAST_FIREBALL, world, posX, posY, posZ, 3.0D, 2.0D, 16);
/*    */       }
/*    */       
/* 87 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteCookItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */