/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.RiteRegistry;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.BlockProtect;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteRaiseVolcano extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final int radius;
/*     */   private final int height;
/*     */   
/*     */   public RiteRaiseVolcano(int radius, int height)
/*     */   {
/*  28 */     this.radius = radius;
/*  29 */     this.height = height;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  34 */     steps.add(new StepRaiseVolcano(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepRaiseVolcano extends RitualStep
/*     */   {
/*     */     private final RiteRaiseVolcano rite;
/*  40 */     private int stage = 0;
/*     */     
/*     */     public StepRaiseVolcano(RiteRaiseVolcano rite, int initialStage) {
/*  43 */       super();
/*  44 */       this.rite = rite;
/*  45 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  50 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  55 */       if (ticks % 15L != 0L) {
/*  56 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  59 */       if (!world.field_72995_K)
/*     */       {
/*  61 */         if (++this.stage == 1) {
/*  62 */           boolean lavaFound = false;
/*  63 */           for (int y = posY; (y > 0) && (!lavaFound); y--) {
/*  64 */             Block blockID = world.func_147439_a(posX, y, posZ);
/*  65 */             if ((blockID == Blocks.field_150353_l) && (surroundedByBlocks(world, posX, y, posZ, Blocks.field_150353_l, 2)))
/*  66 */               lavaFound = true; else {
/*  67 */               if (blockID == Blocks.field_150357_h) {
/*     */                 break;
/*     */               }
/*     */             }
/*     */           }
/*  72 */           if (lavaFound) {
/*  73 */             ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 0.5D, 1.0D, 16);
/*     */           } else {
/*  75 */             SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/*  76 */             RiteRegistry.RiteError("witchery.rite.missinglava", ritual.getInitiatingPlayerName(), world);
/*  77 */             return RitualStep.Result.ABORTED_REFUND;
/*     */           }
/*     */         }
/*     */         
/*  81 */         int height = this.rite.height + 4 * ritual.covenSize;
/*  82 */         float radius = this.rite.radius + 2 * ritual.covenSize;
/*  83 */         if (this.stage <= height) { float r;
/*  84 */           for (int y = 1; y <= this.stage; y++) {
/*  85 */             r = radius - (height - this.stage - 1 + y) * radius / height;
/*  86 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, y + posY, posZ - r, posX + r, y + posY, posZ + r);
/*     */             
/*  88 */             drawFilledCircle(world, posX, posZ, y + posY - 1, Math.max((int)Math.ceil(r), 1), y, true);
/*  89 */             if (this.stage == height) {
/*  90 */               int minusY = posY - 1; for (int reduce = 0; minusY > posY - 5; reduce++) {
/*  91 */                 drawFilledCircle(world, posX, posZ, minusY, Math.max((int)radius - reduce, 2), y, false);minusY--;
/*     */               }
/*     */             }
/*     */             
/*  95 */             for (Object obj : world.func_72872_a(Entity.class, bounds)) {
/*  96 */               Entity entity = (Entity)obj;
/*  97 */               if (Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, y + posY, posZ) <= r) {
/*  98 */                 Material material = world.func_147439_a((int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v).func_149688_o();
/*  99 */                 if (material.func_76220_a()) {
/* 100 */                   if ((entity instanceof EntityLivingBase)) {
/* 101 */                     ((EntityLivingBase)entity).func_70634_a(entity.field_70165_t, entity.field_70163_u + 1.0D, entity.field_70161_v);
/*     */                   } else {
/* 103 */                     entity.func_70107_b(entity.field_70165_t, entity.field_70163_u + 1.0D, entity.field_70161_v);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 109 */         } else if (this.stage < height * 2) {
/* 110 */           if (this.stage == height * 2 - 1) {
/* 111 */             world.func_147449_b(posX, posY + this.stage - height, posZ, Blocks.field_150356_k);
/* 112 */             world.func_147449_b(posX, posY + 1, posZ, Blocks.field_150353_l);
/* 113 */             if (this.rite.radius == 16) {
/* 114 */               if (world.field_73012_v.nextInt(4) == 0) {
/* 115 */                 world.func_147449_b(posX, posY + 1 + this.stage - height, posZ, Blocks.field_150356_k);
/*     */               }
/*     */             } else {
/* 118 */               switch (world.field_73012_v.nextInt(8)) {
/*     */               case 0: 
/* 120 */                 world.func_147468_f(posX + 1, posY + height - 1, posZ);
/* 121 */                 break;
/*     */               case 1: 
/* 123 */                 world.func_147468_f(posX, posY + height - 1, posZ + 1);
/* 124 */                 break;
/*     */               case 2: 
/* 126 */                 world.func_147468_f(posX - 1, posY + height - 1, posZ);
/* 127 */                 break;
/*     */               case 3: 
/* 129 */                 world.func_147468_f(posX, posY + height - 1, posZ - 1);
/*     */               }
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 135 */             world.func_147449_b(posX, posY + 1, posZ, Blocks.field_150348_b);
/* 136 */             world.func_147449_b(posX, posY + this.stage - height, posZ, Blocks.field_150353_l);
/*     */           }
/*     */         }
/*     */         else {
/* 140 */           for (int y = posY; y > 0; y--) {
/* 141 */             Block blockID = world.func_147439_a(posX, y, posZ);
/* 142 */             if ((blockID == Blocks.field_150353_l) || (blockID == Blocks.field_150356_k) || (blockID == Blocks.field_150357_h)) {
/* 143 */               while ((blockID == Blocks.field_150353_l) || (blockID == Blocks.field_150356_k)) {
/* 144 */                 setToAirIfLava(world, posX, y, posZ);
/* 145 */                 setToAirIfLava(world, posX + 1, y, posZ);
/* 146 */                 setToAirIfLava(world, posX - 1, y, posZ);
/* 147 */                 setToAirIfLava(world, posX, y, posZ + 1);
/* 148 */                 setToAirIfLava(world, posX, y, posZ - 1);
/* 149 */                 blockID = world.func_147439_a(posX, --y, posZ);
/*     */               }
/*     */             }
/*     */             
/* 153 */             world.func_147468_f(posX, y, posZ);
/*     */           }
/*     */           
/* 156 */           return RitualStep.Result.COMPLETED;
/*     */         }
/* 158 */         return RitualStep.Result.UPKEEP;
/*     */       }
/* 160 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     private boolean surroundedByBlocks(World world, int x, int y, int z, Block blockID, int minCount) {
/* 164 */       int count = 0;
/* 165 */       count += (world.func_147439_a(x, y - 1, z) == blockID ? 1 : 0);
/* 166 */       count += (world.func_147439_a(x - 1, y, z) == blockID ? 1 : 0);
/* 167 */       count += (world.func_147439_a(x + 1, y - 1, z) == blockID ? 1 : 0);
/* 168 */       count += (world.func_147439_a(x, y, z - 1) == blockID ? 1 : 0);
/* 169 */       count += (world.func_147439_a(x, y, z + 1) == blockID ? 1 : 0);
/* 170 */       count += (world.func_147439_a(x, y + 1, z + 1) == blockID ? 1 : 0);
/*     */       
/* 172 */       return count >= minCount;
/*     */     }
/*     */     
/*     */     private void setToAirIfLava(World world, int posX, int posY, int posZ) {
/* 176 */       Block blockID = world.func_147439_a(posX, posY, posZ);
/* 177 */       if ((blockID == Blocks.field_150353_l) || (blockID == Blocks.field_150356_k)) {
/* 178 */         world.func_147468_f(posX, posY, posZ);
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, int height, boolean replaceBlocks) {
/* 183 */       int x = radius;
/* 184 */       int z = 0;
/* 185 */       int radiusError = 1 - x;
/*     */       
/* 187 */       while (x >= z) {
/* 188 */         drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius, height, replaceBlocks);
/* 189 */         drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius, height, replaceBlocks);
/* 190 */         drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius, height, replaceBlocks);
/* 191 */         drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius, height, replaceBlocks);
/*     */         
/* 193 */         z++;
/*     */         
/* 195 */         if (radiusError < 0) {
/* 196 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 198 */           x--;
/* 199 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius, int height, boolean replaceBlocks) {
/* 205 */       int modX1 = (radius > 1) && (world.field_73012_v.nextInt(5) == 0) ? x1 + 1 : x1;
/* 206 */       int modX2 = (radius > 1) && (world.field_73012_v.nextInt(5) == 0) ? x2 - 1 : x2;
/* 207 */       boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
/*     */       
/* 209 */       for (int x = modX1; x <= modX2; x++) {
/* 210 */         if ((x != midX) || (z != midZ)) {
/* 211 */           drawPixel(world, x, z, y, ((x == modX1) || (x == modX2) || (edgeZ)) && (height < 3), replaceBlocks);
/*     */         }
/*     */       }
/* 214 */       boolean done = true;
/*     */     }
/*     */     
/*     */     protected void drawPixel(World world, int x, int z, int y, boolean lower, boolean replaceBlocks) {
/* 218 */       if (((replaceBlocks) && (BlockProtect.canBreak(x, y, z, world))) || (world.func_147437_c(x, y, z))) {
/* 219 */         world.func_147449_b(x, y, z, (lower) && (world.field_73012_v.nextInt(5) != 0) ? Blocks.field_150349_c : Blocks.field_150348_b);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteRaiseVolcano.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */