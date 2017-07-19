/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.ItemColored;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemLeapingLily extends ItemColored
/*    */ {
/*    */   public ItemLeapingLily(Block par1)
/*    */   {
/* 20 */     super(par1, false);
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 24 */     MovingObjectPosition movingobjectposition = func_77621_a(par2World, par3EntityPlayer, true);
/*    */     
/* 26 */     if (movingobjectposition == null) {
/* 27 */       return par1ItemStack;
/*    */     }
/* 29 */     if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 30 */       int i = movingobjectposition.field_72311_b;
/* 31 */       int j = movingobjectposition.field_72312_c;
/* 32 */       int k = movingobjectposition.field_72309_d;
/*    */       
/* 34 */       if (!par2World.func_72962_a(par3EntityPlayer, i, j, k)) {
/* 35 */         return par1ItemStack;
/*    */       }
/*    */       
/* 38 */       if (!par3EntityPlayer.func_82247_a(i, j, k, movingobjectposition.field_72310_e, par1ItemStack)) {
/* 39 */         return par1ItemStack;
/*    */       }
/*    */       
/* 42 */       if ((par2World.func_147439_a(i, j, k).func_149688_o() == net.minecraft.block.material.Material.field_151586_h) && (par2World.func_72805_g(i, j, k) == 0) && (par2World.func_147437_c(i, j + 1, k)))
/*    */       {
/* 44 */         par2World.func_147449_b(i, j + 1, k, Witchery.Blocks.LEAPING_LILY);
/*    */         
/* 46 */         if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
/* 47 */           par1ItemStack.field_77994_a -= 1;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 52 */     return par1ItemStack;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack par1ItemStack, int par2)
/*    */   {
/* 58 */     return net.minecraft.init.Blocks.field_150392_bi.func_149741_i(par1ItemStack.func_77960_j());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemLeapingLily.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */