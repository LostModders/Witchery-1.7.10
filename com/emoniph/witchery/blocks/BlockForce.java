/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockForce
/*    */   extends BlockBase
/*    */ {
/*    */   private final boolean transparent;
/*    */   
/*    */   public BlockForce(boolean transparent)
/*    */   {
/* 23 */     super(Material.field_151576_e);
/*    */     
/* 25 */     this.transparent = transparent;
/*    */     
/* 27 */     this.registerWithCreateTab = false;
/*    */     
/* 29 */     func_149722_s();
/* 30 */     func_149752_b(9999.0F);
/* 31 */     func_149713_g(0);
/* 32 */     func_149672_a(transparent ? field_149778_k : field_149769_e);
/*    */   }
/*    */   
/*    */   public int func_149645_b() {
/* 36 */     return this.transparent ? -1 : super.func_149645_b();
/*    */   }
/*    */   
/*    */   protected boolean func_149700_E()
/*    */   {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   public int func_149745_a(Random rand)
/*    */   {
/* 46 */     return 0;
/*    */   }
/*    */   
/*    */   public int func_149701_w()
/*    */   {
/* 51 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_149662_c()
/*    */   {
/* 62 */     return this.transparent ? false : super.func_149662_c();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*    */   {
/* 72 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockForce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */