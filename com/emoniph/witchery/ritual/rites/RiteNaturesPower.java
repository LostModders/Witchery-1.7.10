/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Dye;
/*     */ import com.emoniph.witchery.util.MutableBlock;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemDye;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ 
/*     */ public class RiteNaturesPower extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final int radius;
/*     */   private final int height;
/*     */   private final int duration;
/*     */   private final int expanse;
/*     */   
/*     */   public RiteNaturesPower(int radius, int height, int duration, int expanse)
/*     */   {
/*  32 */     this.radius = radius;
/*  33 */     this.height = height;
/*  34 */     this.duration = duration;
/*  35 */     this.expanse = expanse;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  40 */     steps.add(new StepNaturesPower(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepNaturesPower extends RitualStep
/*     */   {
/*     */     private final RiteNaturesPower rite;
/*  46 */     private int stage = 0;
/*     */     
/*     */     public StepNaturesPower(RiteNaturesPower rite, int initialStage) {
/*  49 */       super();
/*  50 */       this.rite = rite;
/*  51 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  56 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  61 */       if (ticks % 20L != 0L) {
/*  62 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  65 */       if (!world.field_72995_K)
/*     */       {
/*  67 */         if (++this.stage < this.rite.duration + ritual.covenSize * 5)
/*     */         {
/*  69 */           int modradius = this.rite.radius + ritual.covenSize * 2;
/*  70 */           posY--;
/*  71 */           int x = posX - modradius + world.field_73012_v.nextInt(modradius * 2);
/*  72 */           int z = posZ - modradius + world.field_73012_v.nextInt(modradius * 2);
/*  73 */           int y = -1;
/*     */           
/*  75 */           world.func_72926_e(2005, posX, posY + 2, posZ, 0);
/*     */           
/*  77 */           Material material = world.func_147439_a(x, posY, z).func_149688_o();
/*  78 */           if ((material == null) || (!material.func_76220_a()) || (!world.func_147437_c(x, posY + 1, z))) {
/*  79 */             for (int h = 1; h < this.rite.height; h++) {
/*  80 */               material = world.func_147439_a(x, posY + h, z).func_149688_o();
/*  81 */               if ((material != null) && (material.func_76220_a()) && (world.func_147437_c(x, posY + h + 1, z))) {
/*  82 */                 y = posY + h;
/*  83 */                 break;
/*     */               }
/*     */               
/*  86 */               material = world.func_147439_a(x, posY - h, z).func_149688_o();
/*  87 */               if ((material != null) && (material.func_76220_a()) && ((world.func_147437_c(x, posY - h + 1, z)) || (world.func_147439_a(x, posY - h + 1, z) == Blocks.field_150433_aE))) {
/*  88 */                 y = posY - h;
/*  89 */                 break;
/*     */               }
/*     */             }
/*     */           } else {
/*  93 */             y = posY;
/*     */           }
/*     */           
/*  96 */           if (y != -1) {
/*  97 */             world.func_72926_e(2005, x, y + 1, z, 0);
/*     */             
/*  99 */             drawFilledCircle(world, x, y, z, this.rite.expanse + 1);
/*     */           }
/*     */           
/* 102 */           return RitualStep.Result.UPKEEP;
/*     */         }
/* 104 */         return RitualStep.Result.COMPLETED;
/*     */       }
/*     */       
/* 107 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */     
/*     */     protected void drawFilledCircle(World world, int x0, int y, int z0, int radius) {
/* 111 */       int x = radius;
/* 112 */       int z = 0;
/* 113 */       int radiusError = 1 - x;
/*     */       
/* 115 */       while (x >= z) {
/* 116 */         drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius);
/* 117 */         drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius);
/* 118 */         drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius);
/* 119 */         drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius);
/*     */         
/* 121 */         z++;
/*     */         
/* 123 */         if (radiusError < 0) {
/* 124 */           radiusError += 2 * z + 1;
/*     */         } else {
/* 126 */           x--;
/* 127 */           radiusError += 2 * (z - x + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius) {
/* 133 */       int modX1 = (radius > 1) && (world.field_73012_v.nextInt(5) == 0) ? x1 + 1 : x1;
/* 134 */       int modX2 = (radius > 1) && (world.field_73012_v.nextInt(5) == 0) ? x2 - 1 : x2;
/* 135 */       boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
/*     */       
/* 137 */       for (int x = modX1; x <= modX2; x++)
/*     */       {
/* 139 */         drawPixel(world, x, z, y, (x == modX1) || (x == modX2) || (edgeZ));
/*     */       }
/*     */       
/* 142 */       boolean done = true;
/*     */     }
/*     */     
/*     */     private boolean isNeighbourBlockID(World world, int x, int y, int z, Block blockID) {
/* 146 */       if (world.func_147439_a(x + 1, y, z) == blockID) {
/* 147 */         return true;
/*     */       }
/*     */       
/* 150 */       if (world.func_147439_a(x - 1, y, z) == blockID) {
/* 151 */         return true;
/*     */       }
/*     */       
/* 154 */       if (world.func_147439_a(x, y, z + 1) == blockID) {
/* 155 */         return true;
/*     */       }
/*     */       
/* 158 */       if (world.func_147439_a(x, y, z - 1) == blockID) {
/* 159 */         return true;
/*     */       }
/*     */       
/* 162 */       return false;
/*     */     }
/*     */     
/* 165 */     private EntityPlayer fakePlayer = null;
/*     */     
/*     */     protected void drawPixel(World world, int x, int z, int y, boolean lower) {
/* 168 */       Block blockID = world.func_147439_a(x, y, z);
/* 169 */       int meta = world.func_72805_g(x, y, z);
/* 170 */       boolean wasGrass = blockID == Blocks.field_150349_c;
/* 171 */       Material materialAbove = world.func_147439_a(x, y + 1, z).func_149688_o();
/* 172 */       if ((materialAbove != null) && (!materialAbove.func_76220_a())) {
/* 173 */         if (((blockID == Blocks.field_150348_b) || (blockID == Blocks.field_150354_m) || (blockID == Blocks.field_150351_n) || (Config.instance().canReplaceNaturalBlock(blockID, meta))) && (world.field_73012_v.nextInt(8) != 0))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 179 */           if (materialAbove != Material.field_151582_l) if (world.field_73012_v.nextDouble() <= (isNeighbourBlockID(world, x, y, z, Blocks.field_150355_j) ? 0.7D : 0.02D)) {
/* 180 */               world.func_147449_b(x, y, z, Blocks.field_150355_j);
/*     */               break label182; }
/* 182 */           world.func_147449_b(x, y, z, Blocks.field_150349_c);
/*     */           label182:
/* 184 */           blockID = Blocks.field_150349_c;
/*     */         }
/* 186 */         if ((materialAbove != Material.field_151582_l) && (blockID != Blocks.field_150350_a) && (blockID != Blocks.field_150362_t) && (blockID != Witchery.Blocks.LEAVES) && (world.field_73012_v.nextInt(4) == 0)) {
/* 187 */           MutableBlock[] blocks = { new MutableBlock(Blocks.field_150345_g, 0), new MutableBlock(Blocks.field_150345_g, 1), new MutableBlock(Blocks.field_150345_g, 2), new MutableBlock(Blocks.field_150345_g, 3), new MutableBlock(Witchery.Blocks.SAPLING, 0), new MutableBlock(Witchery.Blocks.SAPLING, 1), new MutableBlock(Witchery.Blocks.SAPLING, 2), new MutableBlock(Witchery.Blocks.EMBER_MOSS, 0), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150338_P), new MutableBlock(Blocks.field_150337_Q), new MutableBlock(Blocks.field_150328_O), new MutableBlock(Blocks.field_150327_N), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150423_aK, 0), new MutableBlock(Blocks.field_150440_ba, 0), new MutableBlock(Witchery.Blocks.GLINT_WEED, 0) };
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
/* 198 */           blocks[world.field_73012_v.nextInt(blocks.length)].mutate(world, x, y + 1, z, false);
/*     */         }
/* 200 */         if (world.field_73012_v.nextInt(3) == 0) {
/* 201 */           int count = 0;
/* 202 */           if (((this.fakePlayer == null) || (this.fakePlayer.field_70170_p != world)) && ((world instanceof WorldServer))) {
/* 203 */             this.fakePlayer = new FakePlayer((WorldServer)world, new GameProfile(java.util.UUID.randomUUID(), "[Minecraft]"));
/*     */           }
/*     */           
/* 206 */           ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, this.fakePlayer);
/* 207 */           Block saplingBlockID = world.func_147439_a(x, y + 1, z);
/* 208 */           while (((saplingBlockID == Blocks.field_150345_g) || (saplingBlockID == Witchery.Blocks.SAPLING)) && (count++ < 8)) {
/* 209 */             ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, this.fakePlayer);
/* 210 */             saplingBlockID = world.func_147439_a(x, y + 1, z);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteNaturesPower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */