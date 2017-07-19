/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteSphereEffect extends Rite
/*     */ {
/*     */   protected final int maxRadius;
/*     */   protected final Block block;
/*     */   
/*     */   public RiteSphereEffect(int radius, Block block)
/*     */   {
/*  24 */     this.maxRadius = radius;
/*  25 */     this.block = block;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  30 */     steps.add(new StepExpansion(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepExpansion extends RitualStep
/*     */   {
/*     */     private final RiteSphereEffect rite;
/*  36 */     private int stage = 0;
/*     */     private boolean activated;
/*     */     
/*     */     public StepExpansion(RiteSphereEffect rite, int initialStage) {
/*  40 */       super();
/*  41 */       this.rite = rite;
/*  42 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  47 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  52 */       if (!this.activated) {
/*  53 */         if (ticks % 20L != 0L) {
/*  54 */           return RitualStep.Result.STARTING;
/*     */         }
/*  56 */         this.activated = true;
/*  57 */         SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
/*     */       }
/*     */       
/*     */ 
/*  61 */       if (!world.field_72995_K)
/*     */       {
/*     */ 
/*  64 */         if (ticks % 5L == 0L) {
/*  65 */           EntityPlayer player = ritual.getInitiatingPlayer(world);
/*     */           
/*  67 */           if (ritual.covenSize < 2) {
/*  68 */             SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/*  69 */             if (player != null) {
/*  70 */               ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
/*     */             }
/*  72 */             return RitualStep.Result.ABORTED_REFUND;
/*     */           }
/*     */           
/*  75 */           this.stage += 1;
/*     */           
/*  77 */           int maxRadius = (int)(ritual.covenSize <= 5 ? 1.5D * this.rite.maxRadius : ritual.covenSize <= 2 ? this.rite.maxRadius : 2.0D * this.rite.maxRadius);
/*     */           
/*  79 */           int currentRadius = this.stage + 4;
/*     */           
/*  81 */           if (currentRadius <= maxRadius) {
/*  82 */             if (this.stage % 2 == 0) {
/*  83 */               drawSphere(world, posX, posY, posZ, currentRadius, this.rite.block);
/*     */               
/*  85 */               drawSphere(world, posX, posY, posZ, currentRadius - 2, Blocks.field_150350_a);
/*     */             }
/*     */             
/*  88 */             if (currentRadius == maxRadius) {
/*  89 */               fillWithAir(world, posX, posY, posZ, maxRadius, this.rite.block);
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*  94 */           return (this.stage > 250) || (currentRadius >= maxRadius) ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
/*     */         }
/*  96 */         return RitualStep.Result.UPKEEP;
/*     */       }
/*     */       
/*  99 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     private static void fillWithAir(World world, int posX, int posY, int posZ, int radius, Block removalBlock)
/*     */     {
/* 104 */       fillHalfWithAirY(world, posX, posY, posZ, 1, radius, removalBlock);
/* 105 */       fillHalfWithAirY(world, posX, posY - 1, posZ, -1, radius, removalBlock);
/*     */     }
/*     */     
/*     */     private static void fillHalfWithAirY(World world, int posX, int posY, int posZ, int dy, int radius, Block removalBlock) {
/* 109 */       for (int y = 0; y <= radius; y++) {
/* 110 */         int realY = posY + y * dy;
/* 111 */         if (world.func_147439_a(posX, realY, posZ) == removalBlock) {
/*     */           break;
/*     */         }
/* 114 */         fillSliceWithAir(world, posX, realY, posZ, radius, removalBlock);
/*     */       }
/*     */     }
/*     */     
/*     */     private static void fillSliceWithAir(World world, int posX, int posY, int posZ, int radius, Block removalBlock) {
/* 119 */       fillHalfWithAirX(world, posX, posY, posZ, 1, radius, removalBlock);
/* 120 */       fillHalfWithAirX(world, posX - 1, posY, posZ, -1, radius, removalBlock);
/*     */     }
/*     */     
/*     */     private static void fillHalfWithAirX(World world, int posX, int posY, int posZ, int dx, int radius, Block removalBlock) {
/* 124 */       for (int x = 0; x <= radius; x++) {
/* 125 */         int realX = posX + x * dx;
/* 126 */         if (world.func_147439_a(realX, x, posZ) == removalBlock) {
/*     */           break;
/*     */         }
/* 129 */         fillLineWithAir(world, realX, posY, posZ, radius, removalBlock);
/*     */       }
/*     */     }
/*     */     
/*     */     private static void fillLineWithAir(World world, int posX, int posY, int posZ, int radius, Block removalBlock) {
/* 134 */       fillHalfWithAirZ(world, posX, posY, posZ, 1, radius, removalBlock);
/* 135 */       fillHalfWithAirZ(world, posX, posY, posZ - 1, -1, radius, removalBlock);
/*     */     }
/*     */     
/*     */     private static void fillHalfWithAirZ(World world, int posX, int posY, int posZ, int dz, int radius, Block removalBlock) {
/* 139 */       for (int z = 0; z <= radius; z++) {
/* 140 */         int realZ = posZ + z * dz;
/* 141 */         Block foundBlock = world.func_147439_a(posX, posY, realZ);
/* 142 */         if (foundBlock == removalBlock) {
/*     */           break;
/*     */         }
/* 145 */         if ((foundBlock == Blocks.field_150355_j) || (foundBlock == Blocks.field_150358_i)) {
/* 146 */           world.func_147449_b(posX, posY, realZ, Blocks.field_150350_a);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public static void drawSphere(World world, int x0, int y0, int z0, int radius, Block blockID) {
/* 152 */       int x = radius;
/* 153 */       int y = 0;
/* 154 */       int radiusError = 1 - x;
/*     */       
/* 156 */       while (x >= y) {
/* 157 */         drawCircle(world, x0, y0, z0, y, x, radiusError, blockID);
/* 158 */         y++;
/* 159 */         if (radiusError < 0) {
/* 160 */           radiusError += 2 * y + 1;
/*     */         } else {
/* 162 */           x--;
/* 163 */           radiusError += 2 * (y - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected static boolean drawCircle(World world, int x0, int y0, int z0, int y1, int radius, int error0, Block blockID)
/*     */     {
/* 170 */       int x = radius;
/* 171 */       int z = 0;
/* 172 */       int radiusError = error0;
/*     */       
/* 174 */       while (x >= z)
/*     */       {
/* 176 */         drawPixel(world, x0 + x, z0 + z, y0 + y1, blockID);
/* 177 */         drawPixel(world, x0 - x, z0 + z, y0 + y1, blockID);
/* 178 */         drawPixel(world, x0 + x, z0 + z, y0 - y1, blockID);
/* 179 */         drawPixel(world, x0 - x, z0 + z, y0 - y1, blockID);
/* 180 */         drawPixel(world, x0 + x, z0 - z, y0 + y1, blockID);
/* 181 */         drawPixel(world, x0 - x, z0 - z, y0 + y1, blockID);
/* 182 */         drawPixel(world, x0 + x, z0 - z, y0 - y1, blockID);
/* 183 */         drawPixel(world, x0 - x, z0 - z, y0 - y1, blockID);
/*     */         
/* 185 */         drawPixel(world, x0 + z, z0 + x, y0 + y1, blockID);
/* 186 */         drawPixel(world, x0 - z, z0 + x, y0 + y1, blockID);
/* 187 */         drawPixel(world, x0 + z, z0 + x, y0 - y1, blockID);
/* 188 */         drawPixel(world, x0 - z, z0 + x, y0 - y1, blockID);
/* 189 */         drawPixel(world, x0 + z, z0 - x, y0 + y1, blockID);
/* 190 */         drawPixel(world, x0 - z, z0 - x, y0 + y1, blockID);
/* 191 */         drawPixel(world, x0 + z, z0 - x, y0 - y1, blockID);
/* 192 */         drawPixel(world, x0 - z, z0 - x, y0 - y1, blockID);
/*     */         
/* 194 */         drawPixel(world, x0 + y1, z0 + z, y0 + x, blockID);
/* 195 */         drawPixel(world, x0 - y1, z0 + z, y0 + x, blockID);
/* 196 */         drawPixel(world, x0 + y1, z0 + z, y0 - x, blockID);
/* 197 */         drawPixel(world, x0 - y1, z0 + z, y0 - x, blockID);
/* 198 */         drawPixel(world, x0 + y1, z0 - z, y0 + x, blockID);
/* 199 */         drawPixel(world, x0 - y1, z0 - z, y0 + x, blockID);
/* 200 */         drawPixel(world, x0 + y1, z0 - z, y0 - x, blockID);
/* 201 */         drawPixel(world, x0 - y1, z0 - z, y0 - x, blockID);
/*     */         
/* 203 */         drawPixel(world, x0 + z, z0 + y1, y0 + x, blockID);
/* 204 */         drawPixel(world, x0 - z, z0 + y1, y0 + x, blockID);
/* 205 */         drawPixel(world, x0 + z, z0 + y1, y0 - x, blockID);
/* 206 */         drawPixel(world, x0 - z, z0 + y1, y0 - x, blockID);
/* 207 */         drawPixel(world, x0 + z, z0 - y1, y0 + x, blockID);
/* 208 */         drawPixel(world, x0 - z, z0 - y1, y0 + x, blockID);
/* 209 */         drawPixel(world, x0 + z, z0 - y1, y0 - x, blockID);
/* 210 */         drawPixel(world, x0 - z, z0 - y1, y0 - x, blockID);
/*     */         
/* 212 */         z++;
/*     */         
/* 214 */         if (radiusError < 0) {
/* 215 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 217 */           x--;
/* 218 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */       
/* 222 */       return true;
/*     */     }
/*     */     
/*     */     protected static void drawPixel(World world, int x, int z, int y, Block replaceBlockID) {
/* 226 */       Block blockID = world.func_147439_a(x, y, z);
/* 227 */       if ((blockID == Blocks.field_150355_j) || (blockID == Blocks.field_150358_i) || (blockID == Blocks.field_150350_a) || (blockID == Blocks.field_150432_aD) || (blockID == Blocks.field_150433_aE) || (blockID == Blocks.field_150329_H) || (blockID == Blocks.field_150395_bd) || (blockID == Blocks.field_150392_bi) || (blockID == Blocks.field_150328_O) || (blockID == Blocks.field_150327_N) || (blockID == Blocks.field_150434_aF) || (blockID == Blocks.field_150330_I) || (blockID == Witchery.Blocks.PERPETUAL_ICE))
/*     */       {
/*     */ 
/*     */ 
/* 231 */         if (blockID != replaceBlockID) {
/* 232 */           world.func_147449_b(x, y, z, replaceBlockID);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteSphereEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */