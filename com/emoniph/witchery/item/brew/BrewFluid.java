/*    */ package com.emoniph.witchery.item.brew;
/*    */ 
/*    */ import com.emoniph.witchery.item.ItemGeneral.Brew.BrewResult;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ 
/*    */ public class BrewFluid extends com.emoniph.witchery.item.ItemGeneral.Brew
/*    */ {
/*    */   protected final Fluid fluid;
/*    */   
/*    */   public BrewFluid(int damageValue, String unlocalisedName, Fluid fluid)
/*    */   {
/* 17 */     super(damageValue, unlocalisedName);
/* 18 */     this.fluid = fluid;
/*    */   }
/*    */   
/*    */   public ItemGeneral.Brew.BrewResult onImpact(World world, net.minecraft.entity.EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds)
/*    */   {
/* 23 */     switch (mop.field_72313_a) {
/*    */     case BLOCK: 
/* 25 */       depositLiquid(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, enhanced);
/* 26 */       break;
/*    */     case ENTITY: 
/* 28 */       int x = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/* 29 */       int y = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/* 30 */       int z = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/* 31 */       depositLiquid(world, x, y, z, -1, enhanced);
/* 32 */       break;
/*    */     }
/*    */     
/*    */     
/*    */ 
/* 37 */     return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
/*    */   }
/*    */   
/*    */   public void depositLiquid(World world, int posX, int posY, int posZ, int side, boolean enhanced) {
/* 41 */     int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
/* 42 */     int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
/* 43 */     int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0);
/* 44 */     if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
/* 45 */       y--;
/*    */     }
/* 47 */     setBlockIfNotSolid(world, x, y, z, this.fluid.getBlock());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/brew/BrewFluid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */