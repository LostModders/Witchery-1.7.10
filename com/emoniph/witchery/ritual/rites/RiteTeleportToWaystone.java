/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*    */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteTeleportToWaystone extends RiteTeleportation
/*    */ {
/*    */   public RiteTeleportToWaystone(int radius)
/*    */   {
/* 20 */     super(radius);
/*    */   }
/*    */   
/*    */   protected boolean teleport(World world, int posX, int posY, int posZ, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */   {
/* 25 */     if (!world.field_72995_K) {
/* 26 */       net.minecraft.item.ItemStack waystoneStack = null;
/* 27 */       for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
/* 28 */         if ((Witchery.Items.GENERIC.itemWaystoneBound.isMatch(item.itemstack)) || (Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(item.itemstack)))
/*    */         {
/* 30 */           waystoneStack = item.itemstack;
/* 31 */           break;
/*    */         }
/*    */       }
/*    */       
/* 35 */       if (waystoneStack != null) {
/* 36 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - this.radius, posY - this.radius, posZ - this.radius, posX + this.radius, posY + this.radius, posZ + this.radius);
/*    */         
/* 38 */         List list = world.func_72872_a(Entity.class, bounds);
/*    */         
/* 40 */         boolean sent = false;
/* 41 */         for (Iterator iterator = list.iterator(); iterator.hasNext();) {
/* 42 */           Entity entity = (Entity)iterator.next();
/* 43 */           if ((Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) < this.radius) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(entity, 1)))
/*    */           {
/* 45 */             if (Witchery.Items.GENERIC.teleportToLocation(world, waystoneStack, entity, this.radius, true)) {
/* 46 */               sent = true;
/*    */             }
/*    */           }
/*    */         }
/*    */         
/* 51 */         return sent;
/*    */       }
/*    */     }
/*    */     
/* 55 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteTeleportToWaystone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */