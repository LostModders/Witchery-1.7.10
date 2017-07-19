/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockLilyPad;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockWitchLilyPad
/*    */   extends BlockLilyPad
/*    */ {
/*    */   public BlockWitchLilyPad()
/*    */   {
/* 18 */     func_149711_c(0.0F);
/* 19 */     func_149672_a(field_149779_h);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 24 */     func_149647_a(null);
/* 25 */     BlockUtil.registerBlock(this, blockName);
/* 26 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/* 29 */   private static final int[][] DIRECTIONS = { { -1, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 1, 1 } };
/*    */   
/*    */ 
/*    */   public void func_149726_b(World world, int x, int y, int z)
/*    */   {
/* 34 */     world.func_147464_a(x, y, z, this, 20);
/*    */   }
/*    */   
/*    */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*    */   {
/* 39 */     if (!world.field_72995_K) {
/* 40 */       int meta = world.func_72805_g(x, y, z);
/* 41 */       if (canSpread(meta)) {
/* 42 */         int[] d = DIRECTIONS[world.field_73012_v.nextInt(DIRECTIONS.length)];
/* 43 */         if (func_149718_j(world, x + d[0], y, z + d[1])) {
/* 44 */           int growth = meta >> 2 & 0x3;
/* 45 */           int facing = meta & 0x3;
/* 46 */           if (world.field_73012_v.nextInt(growth) == 0) {
/* 47 */             meta = ((growth - 1 & 0x3) << 2) + facing;
/* 48 */             world.func_72921_c(x, y, z, meta, 3);
/*    */           }
/* 50 */           world.func_147465_d(x + d[0], y, z + d[1], this, (growth - 1 & 0x3) << 2, 3);
/*    */         }
/* 52 */         world.func_147464_a(x, y, z, this, 20);
/*    */       }
/*    */     }
/* 55 */     super.func_149674_a(world, x, y, z, rand);
/*    */   }
/*    */   
/*    */   private boolean canSpread(int meta) {
/* 59 */     boolean flag = (meta >> 2 & 0x3) > 0;
/* 60 */     return flag;
/*    */   }
/*    */   
/*    */   public int func_149745_a(Random rand)
/*    */   {
/* 65 */     return 1;
/*    */   }
/*    */   
/*    */   public Item func_149650_a(int metadata, Random rand, int fortune)
/*    */   {
/* 70 */     return rand.nextInt(4) == 0 ? Item.func_150898_a(Blocks.field_150392_bi) : null;
/*    */   }
/*    */   
/*    */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*    */   {
/* 75 */     return new ItemStack(Blocks.field_150392_bi);
/*    */   }
/*    */   
/*    */   protected boolean func_149700_E()
/*    */   {
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockWitchLilyPad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */