/*    */ package com.emoniph.witchery.ritual;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.util.Const;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityList;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class SacrificeLiving extends Sacrifice
/*    */ {
/*    */   final Class<? extends EntityLiving> entityLivingClass;
/*    */   
/*    */   public SacrificeLiving(Class<? extends EntityLiving> entityLivingClass)
/*    */   {
/* 23 */     this.entityLivingClass = entityLivingClass;
/*    */   }
/*    */   
/*    */   public void addDescription(StringBuffer sb)
/*    */   {
/* 28 */     String s = (String)EntityList.field_75626_c.get(this.entityLivingClass);
/*    */     
/* 30 */     if (s == null)
/*    */     {
/* 32 */       s = "generic";
/*    */     }
/*    */     
/* 35 */     sb.append("§8>§0 ");
/* 36 */     sb.append(StatCollector.func_74838_a("entity." + s + ".name"));
/* 37 */     sb.append(Const.BOOK_NEWLINE);
/*    */   }
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
/*    */   public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
/*    */   {
/* 51 */     return true;
/*    */   }
/*    */   
/*    */   public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance)
/*    */   {
/* 56 */     steps.add(new StepSacrificeLiving(this, bounds, maxDistance));
/*    */   }
/*    */   
/*    */   private static class StepSacrificeLiving extends RitualStep
/*    */   {
/*    */     private final SacrificeLiving sacrifice;
/*    */     private final AxisAlignedBB bounds;
/*    */     private final int maxDistance;
/*    */     
/*    */     public StepSacrificeLiving(SacrificeLiving sacrifice, AxisAlignedBB bounds, int maxDistance) {
/* 66 */       super();
/* 67 */       this.sacrifice = sacrifice;
/* 68 */       this.bounds = bounds;
/* 69 */       this.maxDistance = (maxDistance + 1);
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World worldObj, int xCoord, int yCoord, int zCoord, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 74 */       if (ticks % 20L != 0L) {
/* 75 */         return RitualStep.Result.STARTING;
/*    */       }
/* 77 */       for (Object obj : worldObj.func_72872_a(EntityLiving.class, this.bounds)) {
/* 78 */         EntityLiving entity = (EntityLiving)obj;
/* 79 */         if ((this.sacrifice.entityLivingClass.isInstance(entity)) && (Sacrifice.distance(xCoord, yCoord, zCoord, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v) <= this.maxDistance)) {
/* 80 */           if (!worldObj.field_72995_K) {
/* 81 */             entity.func_70106_y();
/*    */             
/* 83 */             ParticleEffect.PORTAL.send(SoundEffect.RANDOM_POP, entity, 1.0D, 2.0D, 16);
/*    */           }
/*    */           
/* 86 */           return RitualStep.Result.COMPLETED;
/*    */         }
/*    */       }
/*    */       
/* 90 */       RiteRegistry.RiteError("witchery.rite.missinglivingsacrifice", ritual.getInitiatingPlayerName(), worldObj);
/* 91 */       return RitualStep.Result.ABORTED_REFUND;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/SacrificeLiving.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */