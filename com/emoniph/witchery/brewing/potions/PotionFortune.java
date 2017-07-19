/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*    */ 
/*    */ public class PotionFortune extends PotionBase implements IHandleHarvestDrops
/*    */ {
/*    */   public PotionFortune(int id, int color)
/*    */   {
/* 13 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onHarvestDrops(World world, EntityPlayer player, BlockEvent.HarvestDropsEvent event, int amplifier)
/*    */   {
/* 18 */     if ((!event.world.field_72995_K) && (!event.isSilkTouching) && (event.block != null) && (!event.block.hasTileEntity(event.blockMetadata)) && (event.drops.size() > 0))
/*    */     {
/* 20 */       ArrayList<net.minecraft.item.ItemStack> drops = event.block.getDrops(event.world, event.x, event.y, event.z, event.blockMetadata, event.fortuneLevel + (amplifier > 2 ? 2 : 1));
/*    */       
/* 22 */       event.drops.clear();
/* 23 */       event.drops.addAll(drops);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionFortune.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */