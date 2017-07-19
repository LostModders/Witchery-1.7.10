/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockPressurePlate;
/*    */ import net.minecraft.block.BlockPressurePlate.Sensitivity;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockPerpetualIcePressurePlate extends BlockPressurePlate
/*    */ {
/*    */   public BlockPerpetualIcePressurePlate(Material material)
/*    */   {
/* 22 */     super(material == Material.field_151588_w ? "ice" : "snow", material, BlockPressurePlate.Sensitivity.everything);
/* 23 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/* 24 */     if (material == Material.field_151588_w) {
/* 25 */       func_149713_g(3);
/* 26 */       func_149711_c(2.0F);
/* 27 */       func_149752_b(5.0F);
/*    */     } else {
/* 29 */       func_149711_c(0.2F);
/* 30 */       func_149672_a(field_149773_n);
/*    */     }
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 36 */     BlockUtil.registerBlock(this, blockName);
/* 37 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149701_w()
/*    */   {
/* 43 */     return this.field_149764_J == Material.field_151588_w ? 1 : 0;
/*    */   }
/*    */   
/*    */   protected int func_150065_e(World world, int x, int y, int z)
/*    */   {
/* 48 */     if (this.field_149764_J == Material.field_151588_w) {
/* 49 */       java.util.List<EntityLivingBase> list = world.func_72872_a(EntityLivingBase.class, func_150061_a(x, y, z));
/*    */       
/* 51 */       for (EntityLivingBase entity : list) {
/* 52 */         ItemStack footwear = entity.func_71124_b(1);
/* 53 */         if ((footwear != null) && (footwear.func_77973_b() == Witchery.Items.ICY_SLIPPERS)) {
/* 54 */           return 15;
/*    */         }
/*    */       }
/* 57 */       return 0;
/*    */     }
/* 59 */     return super.func_150065_e(world, x, y, z);
/*    */   }
/*    */   
/*    */   public boolean func_149742_c(World world, int x, int y, int z)
/*    */   {
/* 64 */     return (super.func_149742_c(world, x, y, z)) || (world.func_147439_a(x, y - 1, z) == Witchery.Blocks.PERPETUAL_ICE_FENCE);
/*    */   }
/*    */   
/*    */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*    */   {
/* 69 */     if (!func_149742_c(world, x, y, z)) {
/* 70 */       func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 71 */       world.func_147468_f(x, y, z);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockPerpetualIcePressurePlate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */