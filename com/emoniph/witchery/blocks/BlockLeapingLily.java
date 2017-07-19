/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.item.ItemLeapingLily;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockLilyPad;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class BlockLeapingLily
/*    */   extends BlockLilyPad
/*    */ {
/*    */   public BlockLeapingLily()
/*    */   {
/* 21 */     func_149711_c(0.0F);
/* 22 */     func_149715_a(0.4F);
/* 23 */     func_149672_a(Block.field_149779_h);
/*    */     
/* 25 */     func_149647_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Block func_149663_c(String blockName)
/*    */   {
/* 30 */     BlockUtil.registerBlock(this, ItemLeapingLily.class, blockName);
/* 31 */     return super.func_149663_c(blockName);
/*    */   }
/*    */   
/*    */   protected boolean func_149854_a(Block block)
/*    */   {
/* 36 */     return (block != null) && (block.func_149688_o() != null) && ((block.func_149688_o().func_76220_a()) || (block.func_149688_o() == Material.field_151586_h));
/*    */   }
/*    */   
/*    */   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
/* 40 */     Material material = par1World.func_147439_a(par2, par3, par4).func_149688_o();
/* 41 */     return (super.func_149742_c(par1World, par2, par3, par4)) && (material != null) && (!material.func_76224_d());
/*    */   }
/*    */   
/*    */   public boolean func_149718_j(World world, int posX, int posY, int posZ)
/*    */   {
/* 46 */     Material material = world.func_147439_a(posX, posY - 1, posZ).func_149688_o();
/* 47 */     return (material != null) && ((material.func_76220_a()) || (material.func_76224_d())) && (world.func_147437_c(posX, posY + 1, posZ));
/*    */   }
/*    */   
/*    */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*    */   {
/* 52 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase))) {
/* 53 */       EntityLivingBase livingEntity = (EntityLivingBase)entity;
/* 54 */       if (!livingEntity.func_70644_a(Potion.field_76424_c)) {
/* 55 */         livingEntity.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 10, 0));
/*    */       }
/*    */       
/* 58 */       if (!livingEntity.func_70644_a(Potion.field_76430_j)) {
/* 59 */         livingEntity.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 10, 4));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockLeapingLily.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */