/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class SpawnUtil
/*    */ {
/*    */   public static void spawnEntityItem(World world, double x, double y, double z, Block block, int quantity)
/*    */   {
/* 14 */     spawnEntityItem(world, x, y, z, Item.func_150898_a(block), quantity, 0);
/*    */   }
/*    */   
/*    */   public static void spawnEntityItem(World world, double x, double y, double z, Item item, int quantity) {
/* 18 */     spawnEntityItem(world, x, y, z, item, quantity, 0);
/*    */   }
/*    */   
/*    */   public static void spawnEntityItem(World world, double x, double y, double z, Item item, int quantity, int damageValue) {
/* 22 */     if (!world.field_72995_K)
/*    */     {
/* 24 */       int maxStackSize = item.getItemStackLimit(new ItemStack(item));
/* 25 */       for (int i = 0; i < quantity / maxStackSize; i++) {
/* 26 */         world.func_72838_d(new EntityItem(world, x, y, z, new ItemStack(item, maxStackSize, damageValue)));
/*    */       }
/*    */       
/* 29 */       int remainder = quantity % maxStackSize;
/* 30 */       if (remainder > 0) {
/* 31 */         world.func_72838_d(new EntityItem(world, x, y, z, new ItemStack(item, remainder, damageValue)));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void spawnEntityItem(World world, double x, double y, double z, ItemStack stack) {
/* 37 */     world.func_72838_d(new EntityItem(world, x, y, z, stack.func_77946_l()));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/SpawnUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */