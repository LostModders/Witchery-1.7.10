/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockDoor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockPerpetualIceDoor extends BlockDoor
/*    */ {
/*    */   public BlockPerpetualIceDoor()
/*    */   {
/* 19 */     super(net.minecraft.block.material.Material.field_151588_w);
/* 20 */     func_149649_H();
/*    */     
/* 22 */     func_149711_c(2.0F);
/* 23 */     func_149752_b(5.0F);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 28 */     com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
/* 29 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149701_w()
/*    */   {
/* 35 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*    */   {
/* 41 */     ArrayList<ItemStack> ret = new ArrayList();
/*    */     
/* 43 */     int count = quantityDropped(metadata, fortune, world.field_73012_v);
/* 44 */     for (int i = 0; i < count; i++) {
/* 45 */       ret.add(Witchery.Items.GENERIC.itemDoorIce.createStack());
/*    */     }
/* 47 */     return ret;
/*    */   }
/*    */   
/*    */   public boolean func_149742_c(World world, int x, int y, int z)
/*    */   {
/* 52 */     return (super.func_149742_c(world, x, y, z)) || (world.func_147439_a(x, y - 1, z) == Witchery.Blocks.PERPETUAL_ICE);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*    */   {
/* 58 */     int l = world.func_72805_g(x, y, z);
/*    */     
/* 60 */     if ((l & 0x8) == 0) {
/* 61 */       boolean flag = false;
/*    */       
/* 63 */       if (world.func_147439_a(x, y + 1, z) != this) {
/* 64 */         world.func_147468_f(x, y, z);
/* 65 */         flag = true;
/*    */       }
/*    */       
/* 68 */       if ((!World.func_147466_a(world, x, y - 1, z)) && (!func_149718_j(world, x, y - 1, z))) {
/* 69 */         world.func_147468_f(x, y, z);
/* 70 */         flag = true;
/*    */         
/* 72 */         if (world.func_147439_a(x, y + 1, z) == this) {
/* 73 */           world.func_147468_f(x, y + 1, z);
/*    */         }
/*    */       }
/*    */       
/* 77 */       if (flag) {
/* 78 */         if (!world.field_72995_K) {
/* 79 */           func_149697_b(world, x, y, z, l, 0);
/*    */         }
/*    */       } else {
/* 82 */         boolean flag1 = (world.func_72864_z(x, y, z)) || (world.func_72864_z(x, y + 1, z));
/*    */         
/*    */ 
/* 85 */         if (((flag1) || (block.func_149744_f())) && (block != this)) {
/* 86 */           func_150014_a(world, x, y, z, flag1);
/*    */         }
/*    */       }
/*    */     } else {
/* 90 */       if (world.func_147439_a(x, y - 1, z) != this) {
/* 91 */         world.func_147468_f(x, y, z);
/*    */       }
/*    */       
/* 94 */       if (block != this) {
/* 95 */         func_149695_a(world, x, y - 1, z, block);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPerpetualIceDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */