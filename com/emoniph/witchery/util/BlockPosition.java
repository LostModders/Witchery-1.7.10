/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ 
/*    */ public class BlockPosition
/*    */ {
/*    */   public final int dimension;
/*    */   public final int x;
/*    */   public final int y;
/*    */   public final int z;
/*    */   
/*    */   public BlockPosition(int dimension, int x, int y, int z)
/*    */   {
/* 17 */     this.dimension = dimension;
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.z = z;
/*    */   }
/*    */   
/*    */   public BlockPosition(World world, int x, int y, int z) {
/* 24 */     this(world.field_73011_w.field_76574_g, x, y, z);
/*    */   }
/*    */   
/*    */   public BlockPosition(World world, Coord coord) {
/* 28 */     this(world, coord.x, coord.y, coord.z);
/*    */   }
/*    */   
/*    */   public BlockPosition(World world, double x, double y, double z) {
/* 32 */     this(world.field_73011_w.field_76574_g, net.minecraft.util.MathHelper.func_76128_c(x), net.minecraft.util.MathHelper.func_76128_c(y), net.minecraft.util.MathHelper.func_76128_c(z));
/*    */   }
/*    */   
/*    */   public BlockPosition(World world, EntityPosition position) {
/* 36 */     this(world, position.x, position.y, position.z);
/*    */   }
/*    */   
/*    */   public static BlockPosition from(net.minecraft.item.ItemStack stack) {
/* 40 */     NBTTagCompound tag = stack.func_77978_p();
/* 41 */     if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
/* 42 */       int newX = tag.func_74762_e("PosX");
/* 43 */       int newY = tag.func_74762_e("PosY");
/* 44 */       int newZ = tag.func_74762_e("PosZ");
/* 45 */       int newD = tag.func_74762_e("PosD");
/* 46 */       return new BlockPosition(newD, newX, newY, newZ);
/*    */     }
/* 48 */     return null;
/*    */   }
/*    */   
/*    */   public World getWorld(MinecraftServer server)
/*    */   {
/* 53 */     for (net.minecraft.world.WorldServer world : server.field_71305_c) {
/* 54 */       if (world.field_73011_w.field_76574_g == this.dimension) {
/* 55 */         return world;
/*    */       }
/*    */     }
/* 58 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BlockPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */