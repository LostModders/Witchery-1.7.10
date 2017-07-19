/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.blocks.BlockWitchCrop;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemSeeds;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.EnumPlantType;
/*    */ 
/*    */ public class ItemWitchSeeds extends ItemSeeds
/*    */ {
/*    */   private final boolean waterPlant;
/*    */   
/*    */   public ItemWitchSeeds(BlockWitchCrop plantedBlock, ItemStack cropItemStack, Block soilBlock, boolean waterPlant)
/*    */   {
/* 22 */     super(plantedBlock, soilBlock);
/*    */     
/* 24 */     this.waterPlant = waterPlant;
/* 25 */     func_77656_e(0);
/* 26 */     func_77625_d(64);
/* 27 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*    */     
/* 29 */     plantedBlock.setSeedItem(new ItemStack(this));
/* 30 */     plantedBlock.setCropItem(cropItemStack);
/*    */   }
/*    */   
/*    */   public Item func_77655_b(String itemName)
/*    */   {
/* 35 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/* 36 */     return super.func_77655_b(itemName);
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 41 */     if (this.waterPlant) {
/* 42 */       MovingObjectPosition mop = func_77621_a(world, player, true);
/* 43 */       if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && (mop.field_72310_e == 1)) {
/* 44 */         float f = (float)mop.field_72307_f.field_72450_a - mop.field_72311_b;
/* 45 */         float f1 = (float)mop.field_72307_f.field_72448_b - mop.field_72312_c;
/* 46 */         float f2 = (float)mop.field_72307_f.field_72449_c - mop.field_72309_d;
/* 47 */         stack.func_77943_a(player, world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, f, f1, f2);
/*    */       }
/*    */     }
/* 50 */     return super.func_77659_a(stack, world, player);
/*    */   }
/*    */   
/*    */   public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
/*    */   {
/* 55 */     return this.waterPlant ? EnumPlantType.Water : super.getPlantType(world, x, y, z);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemWitchSeeds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */