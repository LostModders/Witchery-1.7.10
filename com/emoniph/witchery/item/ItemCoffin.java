/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.blocks.BlockCoffin;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemCoffin extends ItemBase
/*    */ {
/*    */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*    */   {
/* 14 */     if (world.field_72995_K)
/* 15 */       return true;
/* 16 */     if (side != 1) {
/* 17 */       return false;
/*    */     }
/* 19 */     y++;
/* 20 */     BlockCoffin coffin = Witchery.Blocks.COFFIN;
/* 21 */     int i1 = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/* 22 */     byte b0 = 0;
/* 23 */     byte b1 = 0;
/*    */     
/* 25 */     if (i1 == 0) {
/* 26 */       b1 = 1;
/*    */     }
/*    */     
/* 29 */     if (i1 == 1) {
/* 30 */       b0 = -1;
/*    */     }
/*    */     
/* 33 */     if (i1 == 2) {
/* 34 */       b1 = -1;
/*    */     }
/*    */     
/* 37 */     if (i1 == 3) {
/* 38 */       b0 = 1;
/*    */     }
/*    */     
/* 41 */     if ((player.func_82247_a(x, y, z, side, stack)) && (player.func_82247_a(x + b0, y, z + b1, side, stack))) {
/* 42 */       if ((world.func_147437_c(x, y, z)) && (world.func_147437_c(x + b0, y, z + b1)) && (World.func_147466_a(world, x, y - 1, z)) && (World.func_147466_a(world, x + b0, y - 1, z + b1)))
/*    */       {
/*    */ 
/* 45 */         world.func_147465_d(x, y, z, coffin, i1, 3);
/*    */         
/* 47 */         if (world.func_147439_a(x, y, z) == coffin) {
/* 48 */           world.func_147465_d(x + b0, y, z + b1, coffin, i1 + 8, 3);
/*    */         }
/*    */         
/* 51 */         stack.field_77994_a -= 1;
/* 52 */         return true;
/*    */       }
/* 54 */       return false;
/*    */     }
/*    */     
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemCoffin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */