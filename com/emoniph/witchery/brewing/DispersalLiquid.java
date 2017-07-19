/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.util.BlockPosition;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import java.util.Random;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class DispersalLiquid extends Dispersal
/*    */ {
/*    */   public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
/*    */   {
/* 16 */     Coord coord = new Coord(mop, modifiers.impactPosition, true);
/* 17 */     boolean replaceable = com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(world, coord.x, coord.y, coord.z, modifiers.thrower);
/*    */     
/* 19 */     if (replaceable) {
/* 20 */       coord.setBlock(world, Witchery.Blocks.BREW_LIQUID);
/* 21 */       TileEntityBrewFluid gas = (TileEntityBrewFluid)coord.getTileEntity(world, TileEntityBrewFluid.class);
/* 22 */       if (gas != null) {
/* 23 */         gas.initalise(modifiers, nbtBrew);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public String getUnlocalizedName()
/*    */   {
/* 30 */     return "witchery:brew.dispersal.liquid";
/*    */   }
/*    */   
/*    */ 
/*    */   public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*    */   {
/* 36 */     BlockPosition target = modifiers.getTarget();
/* 37 */     World targetWorld = target.getWorld(MinecraftServer.func_71276_C());
/*    */     
/* 39 */     int radius = 16 + 8 * impactModifiers.extent;
/* 40 */     int maxQuantity = radius;
/* 41 */     int halfQuantity = maxQuantity / 4;
/* 42 */     int height = 100;
/*    */     
/* 44 */     double RSQ = radius * radius;
/* 45 */     int i = 0; for (int quantity = halfQuantity + world.field_73012_v.nextInt(halfQuantity); i < quantity; i++) {
/* 46 */       int ny = 100 + world.field_73012_v.nextInt(20);
/* 47 */       int nx = x - radius + world.field_73012_v.nextInt(2 * radius);
/* 48 */       int nz = z - radius + world.field_73012_v.nextInt(2 * radius);
/* 49 */       if (Coord.distanceSq(x, y, z, nx, y, nz) <= RSQ) {
/* 50 */         com.emoniph.witchery.util.EntityUtil.spawnEntityInWorld(targetWorld, new EntityDroplet(targetWorld, nx, ny, nz, nbtBrew));
/*    */       }
/*    */     }
/*    */     
/* 54 */     return modifiers.pulses < 10 + impactModifiers.lifetime * 5 ? RitualStatus.ONGOING : RitualStatus.COMPLETE;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/DispersalLiquid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */