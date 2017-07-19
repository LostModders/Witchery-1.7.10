/*     */ package com.emoniph.witchery.dimension;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
/*     */ 
/*     */ public class WorldChunkManagerTorment implements IChunkProvider
/*     */ {
/*     */   public static final int NUM_LEVELS = 6;
/*     */   public static final int BASE_LEVEL = 10;
/*     */   public static final int LEVEL_HEIGHT = 15;
/*     */   public static final int MAZE_SIZE = 31;
/*     */   private World world;
/*     */   
/*     */   public WorldChunkManagerTorment(World world)
/*     */   {
/*  28 */     this.world = world;
/*     */   }
/*     */   
/*     */   public boolean func_73149_a(int i, int j)
/*     */   {
/*  33 */     return true;
/*     */   }
/*     */   
/*     */   public Chunk func_73154_d(int x, int z)
/*     */   {
/*  38 */     Chunk chunk = new Chunk(this.world, x, z);
/*  39 */     byte[] abyte = chunk.func_76605_m();
/*     */     
/*  41 */     for (int k = 0; k < abyte.length; k++) {
/*  42 */       abyte[k] = ((byte)BiomeGenBase.field_76778_j.field_76756_M);
/*     */     }
/*     */     
/*  45 */     for (int y = 0; y < 255; y++)
/*     */     {
/*  47 */       int l = y >> 4;
/*     */       
/*  49 */       ExtendedBlockStorage extendedblockstorage = chunk.func_76587_i()[l];
/*     */       
/*  51 */       if (extendedblockstorage == null) {
/*  52 */         extendedblockstorage = new ExtendedBlockStorage(y, !this.world.field_73011_w.field_76576_e);
/*  53 */         chunk.func_76587_i()[l] = extendedblockstorage;
/*     */       }
/*     */       
/*  56 */       for (int _x = 0; _x < 16; _x++) {
/*  57 */         for (int _z = 0; _z < 16; _z++) {
/*  58 */           Block blockId = Blocks.field_150350_a;
/*  59 */           extendedblockstorage.func_150818_a(_x, y & 0xF, _z, blockId);
/*  60 */           extendedblockstorage.func_76654_b(_x, y & 0xF, _z, 0);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  65 */     return chunk;
/*     */   }
/*     */   
/*     */   public Chunk func_73158_c(int x, int z)
/*     */   {
/*  70 */     return func_73154_d(x, z);
/*     */   }
/*     */   
/*     */   public void func_73153_a(IChunkProvider ichunkprovider, int i, int j)
/*     */   {
/*  75 */     if ((i == 0) && (j == 0)) {
/*  76 */       for (int slot = 0; slot < 6; slot++) {
/*  77 */         GenerateMaze maze = new GenerateMaze(31, 31, this.world.field_73012_v);
/*  78 */         maze.display(this.world, i * 16 + 8 - 31, 10 + slot * 15, j * 16 + 8 - 2, Witchery.Blocks.FORCE, Witchery.Blocks.TORMENT_STONE);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_73151_a(boolean flag, IProgressUpdate iprogressupdate)
/*     */   {
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_73156_b()
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_73157_c()
/*     */   {
/*  95 */     return true;
/*     */   }
/*     */   
/*     */   public String func_73148_d()
/*     */   {
/* 100 */     return "TormentChunk";
/*     */   }
/*     */   
/*     */ 
/*     */   public List func_73155_a(EnumCreatureType enumcreaturetype, int i, int j, int k)
/*     */   {
/* 106 */     return null;
/*     */   }
/*     */   
/*     */   public ChunkPosition func_147416_a(World world, String s, int i, int j, int k)
/*     */   {
/* 111 */     return null;
/*     */   }
/*     */   
/*     */   public int func_73152_e()
/*     */   {
/* 116 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_82695_e(int i, int j) {}
/*     */   
/*     */   public void func_104112_b() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/dimension/WorldChunkManagerTorment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */