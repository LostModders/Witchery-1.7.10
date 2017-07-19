/*    */ package com.emoniph.witchery.ritual;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.util.ChatUtil;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public abstract class RitualStep
/*    */ {
/* 13 */   protected boolean canRelocate = false;
/*    */   protected int sourceX;
/*    */   
/* 16 */   protected RitualStep(boolean canRelocate) { this.canRelocate = canRelocate; }
/*    */   
/*    */   public static enum Result
/*    */   {
/* 20 */     STARTING,  UPKEEP,  COMPLETED,  ABORTED,  ABORTED_REFUND;
/*    */     
/*    */     private Result() {}
/*    */   }
/*    */   
/*    */   public static class SacrificedItem { public final ItemStack itemstack;
/*    */     public final Coord location;
/*    */     
/* 28 */     public SacrificedItem(ItemStack itemstack, Coord location) { this.itemstack = itemstack;
/* 29 */       this.location = location;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected int sourceY;
/*    */   protected int sourceZ;
/*    */   public Result run(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */   {
/* 38 */     this.sourceX = posX;
/* 39 */     this.sourceZ = posZ;
/* 40 */     this.sourceY = posY;
/*    */     
/* 42 */     if ((this.canRelocate) && (ritual.getLocation() != null)) {
/* 43 */       Coord l = ritual.getLocation();
/* 44 */       int maxDistance = 50 + 50 * ritual.covenSize;
/* 45 */       int maxDistanceSq = maxDistance * maxDistance;
/* 46 */       if (l.distanceSqTo(this.sourceX, this.sourceY, this.sourceZ) > maxDistanceSq) {
/* 47 */         EntityPlayer player = ritual.getInitiatingPlayer(world);
/* 48 */         if (player != null) {
/* 49 */           ChatUtil.sendTranslated(player, "witchery.rite.toofaraway", new Object[0]);
/*    */         }
/* 51 */         return Result.ABORTED_REFUND;
/*    */       }
/* 53 */       posX = l.x;
/* 54 */       posY = l.y;
/* 55 */       posZ = l.z;
/*    */     }
/* 57 */     return process(world, posX, posY, posZ, ticks, ritual);
/*    */   }
/*    */   
/*    */   public abstract Result process(World paramWorld, int paramInt1, int paramInt2, int paramInt3, long paramLong, BlockCircle.TileEntityCircle.ActivatedRitual paramActivatedRitual);
/*    */   
/*    */   public int getCurrentStage() {
/* 63 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/RitualStep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */