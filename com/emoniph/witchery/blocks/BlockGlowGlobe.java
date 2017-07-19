/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockGlowGlobe
/*    */   extends BlockBase
/*    */ {
/*    */   public BlockGlowGlobe()
/*    */   {
/* 19 */     super(Material.field_151592_s);
/* 20 */     this.registerWithCreateTab = false;
/*    */     
/* 22 */     func_149711_c(0.0F);
/* 23 */     func_149715_a(0.9375F);
/* 24 */     func_149649_H();
/*    */     
/* 26 */     float f = 0.1F;
/* 27 */     func_149676_a(0.4F, 0.4F, 0.4F, 0.6F, 0.6F, 0.6F);
/*    */   }
/*    */   
/*    */   public boolean func_149662_c()
/*    */   {
/* 32 */     return false;
/*    */   }
/*    */   
/*    */   public boolean func_149686_d()
/*    */   {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*    */   {
/* 42 */     return null;
/*    */   }
/*    */   
/*    */   public int func_149645_b()
/*    */   {
/* 47 */     return 1;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149701_w()
/*    */   {
/* 53 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*    */   {
/* 58 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*    */   {
/* 64 */     if (rand.nextInt(3) != 0) {
/* 65 */       double d0 = x + 0.45F + rand.nextInt(3) * 0.05F;
/* 66 */       double d1 = y + 0.4F + rand.nextInt(4) * 0.1F;
/* 67 */       double d2 = z + 0.45F + rand.nextInt(3) * 0.05F;
/* 68 */       world.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   public int func_149745_a(Random par1Random)
/*    */   {
/* 74 */     return 0;
/*    */   }
/*    */   
/*    */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*    */   {
/* 79 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockGlowGlobe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */