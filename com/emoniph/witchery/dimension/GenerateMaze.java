/*     */ package com.emoniph.witchery.dimension;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class GenerateMaze
/*     */ {
/*     */   private final int width;
/*     */   private final int depth;
/*     */   private final int[][] maze;
/*     */   public static final int WALL_HEIGHT = 6;
/*     */   
/*     */   public GenerateMaze(int width, int depth, Random rand)
/*     */   {
/*  19 */     this.width = width;
/*  20 */     this.depth = depth;
/*  21 */     this.maze = new int[width][depth];
/*  22 */     generateMaze(0, 0, rand);
/*     */   }
/*     */   
/*     */   public void display(World world, int origX, int origY, int origZ, Block walls, Block floor)
/*     */   {
/*  27 */     for (int i = 0; i < this.depth; i++)
/*     */     {
/*     */ 
/*  30 */       for (int j = 0; j < this.width; j++) {
/*  31 */         if ((this.maze[j][i] & 0x1) == 0) {
/*  32 */           drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
/*  33 */           drawWall(world, origX + j * 2 + 1, origY, origZ + 2 * i, walls, floor);
/*     */         } else {
/*  35 */           drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
/*  36 */           drawPassage(world, origX + j * 2 + 1, origY, origZ + 2 * i, walls, floor);
/*     */         }
/*     */       }
/*     */       
/*  40 */       drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
/*     */       
/*     */ 
/*  43 */       for (j = 0; j < this.width; j++) {
/*  44 */         if ((this.maze[j][i] & 0x8) == 0) {
/*  45 */           drawWall(world, origX + j * 2, origY, origZ + 2 * i + 1, walls, floor);
/*  46 */           drawPassage(world, origX + j * 2 + 1, origY, origZ + 2 * i + 1, walls, floor);
/*     */         } else {
/*  48 */           drawPassage(world, origX + j * 2, origY, origZ + 2 * i + 1, walls, floor);
/*  49 */           drawPassage(world, origX + j * 2 + 1, origY, origZ + 2 * i + 1, walls, floor);
/*     */         }
/*     */       }
/*     */       
/*  53 */       drawWall(world, origX + j * 2, origY, origZ + 2 * i + 1, walls, floor);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  58 */     for (int j = 0; j < this.width; j++) {
/*  59 */       drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
/*  60 */       drawWall(world, origX + j * 2 + 1, origY, origZ + 2 * i, walls, floor);
/*     */     }
/*     */     
/*  63 */     drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
/*     */     
/*     */ 
/*     */ 
/*  67 */     int CHAMBER_WIDTH = 7;
/*  68 */     int CHAMBER_WIDTH_HALF = CHAMBER_WIDTH / 2;
/*     */     
/*     */ 
/*  71 */     for (int x = 0; x < CHAMBER_WIDTH; x++) {
/*  72 */       for (int y = 0; y < CHAMBER_WIDTH; y++) {
/*  73 */         drawPassage(world, origX + this.width + x - CHAMBER_WIDTH_HALF, origY, origZ + y + 1, walls, floor);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  78 */     for (int x = 0; x < CHAMBER_WIDTH; x++) {
/*  79 */       for (int y = 0; y < CHAMBER_WIDTH + 2; y++) {
/*  80 */         drawPassage(world, origX + this.width + x - CHAMBER_WIDTH_HALF, origY, origZ + 2 * this.depth + y - CHAMBER_WIDTH, walls, floor);
/*     */       }
/*     */     }
/*     */     
/*  84 */     drawPortal(world, origX + this.width, origY, origZ + 2 * this.depth, walls, floor);
/*     */     
/*  86 */     CHAMBER_WIDTH = 5;
/*  87 */     CHAMBER_WIDTH_HALF = CHAMBER_WIDTH / 2;
/*     */     
/*  89 */     int MAX_SHIFT = 5;
/*     */     
/*  91 */     int shift = world.field_73012_v.nextInt(11) - 5;
/*     */     
/*     */ 
/*  94 */     for (int x = 0; x < CHAMBER_WIDTH; x++) {
/*  95 */       for (int y = 0; y < CHAMBER_WIDTH; y++) {
/*  96 */         drawPassage(world, origX + x + 1, origY, origZ + y + this.depth - CHAMBER_WIDTH_HALF + shift, walls, floor);
/*     */       }
/*     */     }
/*     */     
/* 100 */     drawChest(world, origX + CHAMBER_WIDTH_HALF + 1, origY, origZ + this.depth + shift, walls, floor);
/*     */     
/* 102 */     shift = world.field_73012_v.nextInt(11) - 5;
/*     */     
/*     */ 
/* 105 */     for (int x = 0; x < CHAMBER_WIDTH; x++) {
/* 106 */       for (int y = 0; y < CHAMBER_WIDTH; y++) {
/* 107 */         drawPassage(world, origX + 2 * this.width + x - CHAMBER_WIDTH, origY, origZ + y + this.depth - CHAMBER_WIDTH_HALF + shift, walls, floor);
/*     */       }
/*     */     }
/*     */     
/* 111 */     drawChest(world, origX + 2 * this.width - CHAMBER_WIDTH_HALF - 1, origY, origZ + this.depth + shift, walls, floor);
/*     */     
/* 113 */     int ROOM_WIDTH = 7;
/* 114 */     int ROOM_WIDTH_HALF = 3;
/*     */     
/*     */ 
/* 117 */     for (int x = 0; x < 7; x++) {
/* 118 */       for (int y = 0; y < 7; y++) {
/* 119 */         drawPassage(world, origX + this.width + x - 3, origY, origZ + this.depth + y - 3, walls, floor);
/*     */       }
/*     */     }
/*     */     
/* 123 */     drawChest(world, origX + this.width, origY, origZ + this.depth, walls, floor);
/*     */   }
/*     */   
/*     */   private void drawPortal(World world, int x, int y, int z, Block wallBlock, Block floorBlock) {
/* 127 */     world.func_147449_b(x, y + 1, z, Witchery.Blocks.TORMENT_PORTAL);
/* 128 */     world.func_147449_b(x, y + 2, z, Witchery.Blocks.TORMENT_PORTAL);
/* 129 */     world.func_147449_b(x, y + 3, z, floorBlock);
/*     */     
/* 131 */     world.func_147449_b(x - 1, y + 1, z, floorBlock);
/* 132 */     world.func_147449_b(x - 1, y + 2, z, floorBlock);
/* 133 */     world.func_147449_b(x - 1, y + 3, z, floorBlock);
/*     */     
/* 135 */     world.func_147449_b(x + 1, y + 1, z, floorBlock);
/* 136 */     world.func_147449_b(x + 1, y + 2, z, floorBlock);
/* 137 */     world.func_147449_b(x + 1, y + 3, z, floorBlock);
/*     */   }
/*     */   
/*     */   private static void drawChest(World world, int x, int y, int z, Block wallBlock, Block floorBlock) {
/* 141 */     world.func_147449_b(x, y, z, Witchery.Blocks.REFILLING_CHEST);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void drawWall(World world, int x, int y, int z, Block wallBlock, Block floorBlock)
/*     */   {
/* 148 */     for (int h = 0; h < 6; h++) {
/* 149 */       world.func_147449_b(x, y + h, z, wallBlock);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawPassage(World world, int x, int y, int z, Block wallBlock, Block floorBlock) {
/* 154 */     world.func_147449_b(x, y - 1, z, floorBlock);
/* 155 */     if (world.field_73012_v.nextInt(100) == 0) {
/* 156 */       world.func_147449_b(x, y, z, net.minecraft.init.Blocks.field_150391_bh);
/*     */     } else {
/* 158 */       world.func_147449_b(x, y, z, floorBlock);
/*     */     }
/* 160 */     for (int h = 1; h < 5; h++) {
/* 161 */       world.func_147468_f(x, y + h, z);
/*     */     }
/* 163 */     world.func_147449_b(x, y + 6 - 1, z, wallBlock);
/*     */   }
/*     */   
/*     */   private void generateMaze(int cx, int cy, Random rand) {
/* 167 */     DIR[] dirs = DIR.values();
/* 168 */     java.util.Collections.shuffle(Arrays.asList(dirs), rand);
/* 169 */     for (DIR dir : dirs) {
/* 170 */       int nx = cx + dir.dx;
/* 171 */       int ny = cy + dir.dy;
/* 172 */       if ((between(nx, this.width)) && (between(ny, this.depth)) && (this.maze[nx][ny] == 0)) {
/* 173 */         this.maze[cx][cy] |= dir.bit;
/* 174 */         this.maze[nx][ny] |= DIR.access$300(dir).bit;
/* 175 */         generateMaze(nx, ny, rand);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean between(int v, int upper) {
/* 181 */     return (v >= 0) && (v < upper);
/*     */   }
/*     */   
/*     */   private static enum DIR {
/* 185 */     N(1, 0, -1),  S(2, 0, 1),  E(4, 1, 0),  W(8, -1, 0);
/*     */     
/*     */     private final int bit;
/*     */     private final int dx;
/*     */     private final int dy;
/*     */     private DIR opposite;
/*     */     
/*     */     static {
/* 193 */       N.opposite = S;
/* 194 */       S.opposite = N;
/* 195 */       E.opposite = W;
/* 196 */       W.opposite = E;
/*     */     }
/*     */     
/*     */     private DIR(int bit, int dx, int dy) {
/* 200 */       this.bit = bit;
/* 201 */       this.dx = dx;
/* 202 */       this.dy = dy;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/dimension/GenerateMaze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */