/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import codechicken.nei.api.IHighlightHandler;
/*    */ import codechicken.nei.api.ItemInfo.Layout;
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class NEIHighlightHandler
/*    */   implements IHighlightHandler
/*    */ {
/* 20 */   private static final ItemStack yellowPlant = new ItemStack(Blocks.field_150327_N);
/* 21 */   private static final ItemStack redPlant = new ItemStack(Blocks.field_150328_O);
/* 22 */   private static final ItemStack shrubPlant = new ItemStack(Blocks.field_150330_I);
/* 23 */   private static final ItemStack door = new ItemStack(Blocks.field_150466_ao);
/* 24 */   private static final ItemStack dirt = new ItemStack(Blocks.field_150346_d);
/* 25 */   private static final ItemStack grass = new ItemStack(Blocks.field_150349_c);
/*    */   private final Block block;
/*    */   
/*    */   public NEIHighlightHandler(Block block) {
/* 29 */     this.block = block;
/*    */   }
/*    */   
/*    */   public ItemStack identifyHighlight(World world, EntityPlayer player, MovingObjectPosition mop)
/*    */   {
/* 34 */     if (this.block == Witchery.Blocks.TRAPPED_PLANT) {
/* 35 */       if ((mop == null) || (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY)) {
/* 36 */         return null;
/*    */       }
/* 38 */       int foundMeta = world.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 39 */       if ((foundMeta == 5) || (foundMeta == 6) || (foundMeta == 7) || (foundMeta == 4))
/* 40 */         return yellowPlant;
/* 41 */       if ((foundMeta == 1) || (foundMeta == 2) || (foundMeta == 3) || (foundMeta == 0))
/* 42 */         return redPlant;
/* 43 */       if ((foundMeta == 9) || (foundMeta == 10) || (foundMeta == 11) || (foundMeta == 8))
/* 44 */         return shrubPlant;
/*    */     } else {
/* 46 */       if (this.block == Witchery.Blocks.DOOR_ALDER)
/* 47 */         return door;
/* 48 */       if (this.block == Witchery.Blocks.PIT_DIRT)
/* 49 */         return dirt;
/* 50 */       if (this.block == Witchery.Blocks.PIT_GRASS)
/* 51 */         return grass;
/*    */     }
/* 53 */     return null;
/*    */   }
/*    */   
/*    */   public List<String> handleTextData(ItemStack itemStack, World world, EntityPlayer player, MovingObjectPosition mop, List<String> currenttip, ItemInfo.Layout layout)
/*    */   {
/* 58 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/NEIHighlightHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */