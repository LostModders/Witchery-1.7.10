/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockDragonEgg;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockInfinityEgg
/*    */   extends BlockDragonEgg
/*    */ {
/*    */   public BlockInfinityEgg()
/*    */   {
/* 22 */     func_149711_c(3.0F);
/* 23 */     func_149752_b(15.0F);
/* 24 */     func_149672_a(field_149780_i);
/* 25 */     func_149715_a(0.125F);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 30 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/* 31 */     BlockUtil.registerBlock(this, blockName);
/* 32 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*    */   {
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockInfinityEgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */