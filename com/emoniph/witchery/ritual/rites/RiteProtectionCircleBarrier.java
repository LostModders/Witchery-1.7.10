/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RiteProtectionCircleBarrier extends RiteProtectionCircle
/*    */ {
/*    */   protected final int height;
/*    */   protected final boolean blockPlayers;
/*    */   protected static final int TICKS_TO_LIVE_WITHOUT_PULSE = 30;
/*    */   
/*    */   public RiteProtectionCircleBarrier(int radius, int height, float upkeepPowerCost, boolean blockPlayers, int ticksToLive)
/*    */   {
/* 16 */     super(radius, upkeepPowerCost, ticksToLive);
/* 17 */     this.height = height;
/* 18 */     this.blockPlayers = blockPlayers;
/*    */   }
/*    */   
/*    */   protected void update(World world, int posX, int posY, int posZ, int radius, long ticks)
/*    */   {
/* 23 */     if (ticks % 20L == 0L) {
/* 24 */       drawFilledCircle(world, posX, posZ, posY - 1, radius);
/*    */       
/*    */ 
/* 27 */       drawCircleCylinder(world, posX, posZ, posY, radius);
/*    */       
/*    */ 
/* 30 */       drawFilledCircle(world, posX, posZ, posY + this.height, radius);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void drawCircleCylinder(World world, int x0, int z0, int y, int radius) {
/* 35 */     int x = radius;
/* 36 */     int z = 0;
/* 37 */     int radiusError = 1 - x;
/*    */     
/* 39 */     while (x >= z) {
/* 40 */       drawPixelColumn(world, x + x0, z + z0, y);
/* 41 */       drawPixelColumn(world, z + x0, x + z0, y);
/* 42 */       drawPixelColumn(world, -x + x0, z + z0, y);
/* 43 */       drawPixelColumn(world, -z + x0, x + z0, y);
/* 44 */       drawPixelColumn(world, -x + x0, -z + z0, y);
/* 45 */       drawPixelColumn(world, -z + x0, -x + z0, y);
/* 46 */       drawPixelColumn(world, x + x0, -z + z0, y);
/* 47 */       drawPixelColumn(world, z + x0, -x + z0, y);
/*    */       
/* 49 */       z++;
/* 50 */       if (radiusError < 0) {
/* 51 */         radiusError += 2 * z + 1;
/*    */       } else {
/* 53 */         x--;
/* 54 */         radiusError += 2 * (z - x + 1);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected void drawPixelColumn(World world, int x, int z, int y) {
/* 60 */     for (int dy = y; dy < y + this.height; dy++) {
/* 61 */       drawPixel(world, x, z, dy);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void drawPixel(World world, int x, int z, int y) {
/* 66 */     Block blockID = world.func_147439_a(x, y, z);
/* 67 */     boolean isBarrier = blockID == Witchery.Blocks.BARRIER;
/* 68 */     if ((blockID == net.minecraft.init.Blocks.field_150350_a) || (blockID.func_149688_o().func_76222_j()) || (isBarrier)) {
/* 69 */       com.emoniph.witchery.blocks.BlockBarrier.setBlock(world, x, y, z, 30, this.blockPlayers, null, isBarrier);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void drawFilledCircle(World world, int x0, int z0, int y, int radius) {
/* 74 */     int x = radius;
/* 75 */     int z = 0;
/* 76 */     int radiusError = 1 - x;
/*    */     
/* 78 */     while (x >= z) {
/* 79 */       drawLine(world, -x + x0, x + x0, z + z0, y);
/* 80 */       drawLine(world, -z + x0, z + x0, x + z0, y);
/* 81 */       drawLine(world, -x + x0, x + x0, -z + z0, y);
/* 82 */       drawLine(world, -z + x0, z + x0, -x + z0, y);
/*    */       
/* 84 */       z++;
/*    */       
/* 86 */       if (radiusError < 0) {
/* 87 */         radiusError += 2 * z + 1;
/*    */       } else {
/* 89 */         x--;
/* 90 */         radiusError += 2 * (z - x + 1);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected void drawLine(World world, int x1, int x2, int z, int y) {
/* 96 */     for (int x = x1; x <= x2; x++) {
/* 97 */       drawPixel(world, x, z, y);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteProtectionCircleBarrier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */