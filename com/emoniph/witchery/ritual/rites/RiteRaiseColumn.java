/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteRaiseColumn extends Rite
/*     */ {
/*     */   private final int radius;
/*     */   private final int height;
/*     */   
/*     */   public RiteRaiseColumn(int radius, int height)
/*     */   {
/*  27 */     this.radius = radius;
/*  28 */     this.height = height;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  33 */     steps.add(new StepRaiseColumn(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepRaiseColumn extends RitualStep
/*     */   {
/*     */     private final RiteRaiseColumn rite;
/*  39 */     private int stage = 0;
/*     */     
/*     */     public StepRaiseColumn(RiteRaiseColumn rite, int initialStage) {
/*  42 */       super();
/*  43 */       this.rite = rite;
/*  44 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  49 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  54 */       if (!world.field_72995_K) {
/*  55 */         if (ticks % 5L != 0L) {
/*  56 */           return RitualStep.Result.STARTING;
/*     */         }
/*     */         
/*  59 */         if (++this.stage == 1) {
/*  60 */           ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 0.5D, 1.0D, 16);
/*     */         }
/*     */         
/*  63 */         int height = this.rite.height;
/*  64 */         int radius = this.rite.radius + ritual.covenSize * 2;
/*  65 */         int AIR_SPACE = this.rite.radius * 2;
/*     */         
/*  67 */         for (int depth = posY + AIR_SPACE; depth >= posY - height; depth--) {
/*  68 */           drawFilledCircle(world, posX, depth, posZ, radius, depth == posY - 1);
/*     */         }
/*     */         
/*  71 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - radius, posY, posZ - radius, posX + radius, posY + AIR_SPACE, posZ + radius);
/*  72 */         for (Object obj : world.func_72872_a(Entity.class, bounds)) {
/*  73 */           Entity entity = (Entity)obj;
/*  74 */           if (Coord.distanceSq(entity.field_70165_t, posY, entity.field_70161_v, posX, posY, posZ) <= radius * radius) {
/*  75 */             if ((entity instanceof EntityLivingBase)) {
/*  76 */               ((EntityLivingBase)entity).func_70634_a(entity.field_70165_t, entity.field_70163_u + 1.0D, entity.field_70161_v);
/*     */             } else {
/*  78 */               entity.field_70145_X = true;
/*  79 */               entity.func_70107_b(entity.field_70165_t, entity.field_70163_u + 1.0D, entity.field_70161_v);
/*  80 */               entity.field_70145_X = false;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  85 */         if (this.stage < height - 1) {
/*  86 */           return RitualStep.Result.UPKEEP;
/*     */         }
/*  88 */         return RitualStep.Result.COMPLETED;
/*     */       }
/*     */       
/*  91 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     protected void drawFilledCircle(World world, int x0, int y0, int z0, int radius, boolean topLayer)
/*     */     {
/*  96 */       int x = radius;
/*  97 */       int z = 0;
/*  98 */       int radiusError = 1 - x;
/*     */       
/* 100 */       while (x >= z) {
/* 101 */         drawLine(world, -x + x0, x + x0, y0, z + z0, topLayer, radius, z0);
/* 102 */         drawLine(world, -z + x0, z + x0, y0, x + z0, topLayer, radius, z0);
/* 103 */         drawLine(world, -x + x0, x + x0, y0, -z + z0, topLayer, radius, z0);
/* 104 */         drawLine(world, -z + x0, z + x0, y0, -x + z0, topLayer, radius, z0);
/*     */         
/* 106 */         z++;
/*     */         
/* 108 */         if (radiusError < 0) {
/* 109 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 111 */           x--;
/* 112 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawLine(World world, int x1, int x2, int y, int z, boolean topLayer, int radius, int midZ) {
/* 118 */       for (int x = x1; x <= x2; x++) {
/* 119 */         Block block = BlockUtil.getBlock(world, x, y, z);
/* 120 */         Block highBlock = BlockUtil.getBlock(world, x, y + 1, z);
/* 121 */         Block lowBlock = BlockUtil.getBlock(world, x, y - 1, z);
/*     */         
/* 123 */         if ((block != null) && (block != net.minecraft.init.Blocks.field_150350_a) && (!BlockUtil.isImmovableBlock(block)) && (!BlockUtil.isImmovableBlock(highBlock)) && (!BlockUtil.isImmovableBlock(lowBlock))) {
/* 124 */           boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
/* 125 */           int blockMeta = world.func_72805_g(x, y, z);
/*     */           
/* 127 */           if ((topLayer) || ((!edgeZ) && (x != x1) && (x != x2)) || (world.field_73012_v.nextInt(7) != 0)) {
/* 128 */             if (block.hasTileEntity(0)) {
/* 129 */               TileEntity tileEntity = world.func_147438_o(x, y, z);
/* 130 */               if ((tileEntity != null) && (!BlockUtil.isImmovableBlock(tileEntity))) {
/* 131 */                 world.func_147475_p(x, y, z);
/* 132 */                 BlockUtil.setBlock(world, x, y + 1, z, block);
/* 133 */                 world.func_72921_c(x, y + 1, z, blockMeta, 2);
/* 134 */                 tileEntity.func_145829_t();
/* 135 */                 world.func_147455_a(x, y + 1, z, tileEntity);
/* 136 */                 BlockUtil.setAirBlock(world, x, y, z, 2);
/*     */               }
/*     */             } else {
/* 139 */               BlockUtil.setBlock(world, x, y + 1, z, block, blockMeta, 2);
/* 140 */               BlockUtil.setAirBlock(world, x, y, z, 2);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteRaiseColumn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */