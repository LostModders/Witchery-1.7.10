/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockBase;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockWitchWeb extends BlockBase
/*    */ {
/*    */   public BlockWitchWeb()
/*    */   {
/* 17 */     super(Material.field_151580_n);
/* 18 */     func_149713_g(1);
/* 19 */     func_149711_c(8.0F);
/* 20 */     this.registerWithCreateTab = false;
/*    */   }
/*    */   
/*    */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*    */   {
/* 25 */     entity.func_70110_aj();
/*    */   }
/*    */   
/*    */   public boolean func_149662_c()
/*    */   {
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*    */   {
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   public int func_149645_b()
/*    */   {
/* 40 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean func_149686_d()
/*    */   {
/* 45 */     return false;
/*    */   }
/*    */   
/*    */   public int func_149745_a(Random rand)
/*    */   {
/* 50 */     return 0;
/*    */   }
/*    */   
/*    */   public Item func_149650_a(int metadata, Random rand, int fortune)
/*    */   {
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*    */   {
/* 60 */     return null;
/*    */   }
/*    */   
/*    */   protected boolean func_149700_E()
/*    */   {
/* 65 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockWitchWeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */