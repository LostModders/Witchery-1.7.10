/*     */ package com.emoniph.witchery.dimension;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
/*     */ 
/*     */ public class WorldChunkManagerMirror implements IChunkProvider
/*     */ {
/*     */   private World world;
/*     */   
/*     */   public WorldChunkManagerMirror(World world)
/*     */   {
/*  23 */     this.world = world;
/*     */   }
/*     */   
/*     */   public boolean func_73149_a(int i, int j)
/*     */   {
/*  28 */     return true;
/*     */   }
/*     */   
/*     */   public Chunk func_73154_d(int x, int z)
/*     */   {
/*  33 */     Chunk chunk = new Chunk(this.world, x, z);
/*  34 */     byte[] abyte = chunk.func_76605_m();
/*     */     
/*  36 */     Block wall = Witchery.Blocks.MIRROR_WALL;
/*  37 */     Block air = net.minecraft.init.Blocks.field_150350_a;
/*     */     
/*  39 */     for (int k = 0; k < abyte.length; k++) {
/*  40 */       abyte[k] = ((byte)BiomeGenBase.field_76778_j.field_76756_M);
/*     */     }
/*     */     
/*  43 */     int[] wallPointsXZ = { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  63 */     int[] wallPointsY = { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */     for (int y = 0; y < 255; y++) {
/*  83 */       int l = y >> 4;
/*     */       
/*  85 */       ExtendedBlockStorage extendedblockstorage = chunk.func_76587_i()[l];
/*  86 */       if (extendedblockstorage == null) {
/*  87 */         extendedblockstorage = new ExtendedBlockStorage(y, !this.world.field_73011_w.field_76576_e);
/*  88 */         chunk.func_76587_i()[l] = extendedblockstorage;
/*     */       }
/*     */       
/*  91 */       int _y = y & 0xF;
/*     */       
/*  93 */       for (int _x = 0; _x < 16; _x++) {
/*  94 */         for (int _z = 0; _z < 16; _z++)
/*     */         {
/*  96 */           if (((!Config.instance().shrinkMirrorWorld) || (y < 128)) && ((wallPointsY[_y] == 1) || (wallPointsXZ[_x] == 1) || (wallPointsXZ[_z] == 1))) {
/*  97 */             extendedblockstorage.func_150818_a(_x, _y, _z, wall);
/*  98 */             extendedblockstorage.func_76654_b(_x, _y, _z, 0);
/*     */           } else {
/* 100 */             extendedblockstorage.func_150818_a(_x, _y, _z, air);
/* 101 */             extendedblockstorage.func_76654_b(_x, _y, _z, 0);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 108 */     chunk.func_76603_b();
/*     */     
/* 110 */     return chunk;
/*     */   }
/*     */   
/*     */   public Chunk func_73158_c(int x, int z)
/*     */   {
/* 115 */     return func_73154_d(x, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_73153_a(IChunkProvider ichunkprovider, int i, int j) {}
/*     */   
/*     */ 
/*     */   public boolean func_73151_a(boolean flag, IProgressUpdate iprogressupdate)
/*     */   {
/* 125 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_73156_b()
/*     */   {
/* 130 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_73157_c()
/*     */   {
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public String func_73148_d()
/*     */   {
/* 140 */     return "MirrorChunk";
/*     */   }
/*     */   
/*     */ 
/*     */   public List func_73155_a(EnumCreatureType enumcreaturetype, int i, int j, int k)
/*     */   {
/* 146 */     return null;
/*     */   }
/*     */   
/*     */   public ChunkPosition func_147416_a(World world, String s, int i, int j, int k)
/*     */   {
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   public int func_73152_e()
/*     */   {
/* 156 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_82695_e(int i, int j) {}
/*     */   
/*     */   public void func_104112_b() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/dimension/WorldChunkManagerMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */