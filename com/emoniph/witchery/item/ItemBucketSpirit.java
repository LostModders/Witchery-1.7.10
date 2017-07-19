/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemBucketSpirit extends ItemBase
/*    */ {
/* 16 */   private Block fluidBlock = net.minecraft.init.Blocks.field_150350_a;
/*    */   
/*    */   public ItemBucketSpirit()
/*    */   {
/* 20 */     func_77625_d(1);
/* 21 */     func_77656_e(0);
/*    */   }
/*    */   
/*    */   public ItemBucketSpirit setFluidBlock(Block fluidBlock) {
/* 25 */     this.fluidBlock = fluidBlock;
/* 26 */     return this;
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player)
/*    */   {
/* 31 */     MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
/* 32 */     if (movingobjectposition == null) {
/* 33 */       return item;
/*    */     }
/* 35 */     if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 36 */       int x = movingobjectposition.field_72311_b;
/* 37 */       int y = movingobjectposition.field_72312_c;
/* 38 */       int z = movingobjectposition.field_72309_d;
/*    */       
/* 40 */       if (!world.func_72962_a(player, x, y, z)) {
/* 41 */         return item;
/*    */       }
/*    */       
/* 44 */       if (movingobjectposition.field_72310_e == 0) {
/* 45 */         y--;
/*    */       }
/*    */       
/* 48 */       if (movingobjectposition.field_72310_e == 1) {
/* 49 */         y++;
/*    */       }
/*    */       
/* 52 */       if (movingobjectposition.field_72310_e == 2) {
/* 53 */         z--;
/*    */       }
/*    */       
/* 56 */       if (movingobjectposition.field_72310_e == 3) {
/* 57 */         z++;
/*    */       }
/*    */       
/* 60 */       if (movingobjectposition.field_72310_e == 4) {
/* 61 */         x--;
/*    */       }
/*    */       
/* 64 */       if (movingobjectposition.field_72310_e == 5) {
/* 65 */         x++;
/*    */       }
/*    */       
/* 68 */       if (!player.func_82247_a(x, y, z, movingobjectposition.field_72310_e, item)) {
/* 69 */         return item;
/*    */       }
/*    */       
/* 72 */       if ((tryPlaceContainedLiquid(world, x, y, z)) && (!player.field_71075_bZ.field_75098_d)) {
/* 73 */         return new ItemStack(Items.field_151133_ar);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 78 */     return item;
/*    */   }
/*    */   
/*    */   private boolean tryPlaceContainedLiquid(World world, int x, int y, int z)
/*    */   {
/* 83 */     Material material = world.func_147439_a(x, y, z).func_149688_o();
/* 84 */     boolean flag = !material.func_76220_a();
/*    */     
/* 86 */     if ((!world.func_147437_c(x, y, z)) && (!flag)) {
/* 87 */       return false;
/*    */     }
/*    */     
/* 90 */     if ((!world.field_72995_K) && (flag) && (!material.func_76224_d())) {
/* 91 */       world.func_147480_a(x, y, z, true);
/*    */     }
/*    */     
/* 94 */     BlockUtil.setBlock(world, x, y, z, this.fluidBlock, 0, 3);
/*    */     
/* 96 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBucketSpirit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */