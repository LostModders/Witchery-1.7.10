/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.Coord;
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
/*     */ public class RiteForestation extends Rite
/*     */ {
/*     */   private final int radius;
/*     */   private final int height;
/*     */   private final int duration;
/*     */   private final Block block;
/*     */   private final int metadata;
/*     */   
/*     */   public RiteForestation(int radius, int height, int duration, Block block, int protoMeta)
/*     */   {
/*  33 */     this.radius = radius;
/*  34 */     this.height = height;
/*  35 */     this.duration = duration;
/*  36 */     this.block = block;
/*  37 */     this.metadata = protoMeta;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  42 */     steps.add(new StepForestation(this, intialStage));
/*     */   }
/*     */   
/*     */   private static class StepForestation extends RitualStep
/*     */   {
/*     */     private final RiteForestation rite;
/*  48 */     private int stage = 0;
/*     */     
/*     */     public StepForestation(RiteForestation rite, int initialStage) {
/*  51 */       super();
/*  52 */       this.rite = rite;
/*  53 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/*  58 */       return (byte)this.stage;
/*     */     }
/*     */     
/*     */     public boolean isAirOrReplaceableBlock(World world, int x, int y, int z) {
/*  62 */       Block blockID = world.func_147439_a(x, y, z);
/*  63 */       if (blockID == Blocks.field_150350_a) {
/*  64 */         return true;
/*     */       }
/*     */       
/*  67 */       Material block = blockID.func_149688_o();
/*  68 */       if (block == null)
/*  69 */         return false;
/*  70 */       if (block.func_76224_d())
/*  71 */         return false;
/*  72 */       if (block.func_76222_j()) {
/*  73 */         return true;
/*     */       }
/*  75 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  82 */       if (ticks % 20L != 0L) {
/*  83 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  86 */       if (!world.field_72995_K)
/*     */       {
/*  88 */         if (++this.stage < this.rite.duration + ritual.covenSize * 5)
/*     */         {
/*  90 */           int modradius = this.rite.radius + ritual.covenSize * 2;
/*  91 */           int modradiussq = (modradius + 1) * (modradius + 1);
/*  92 */           posY--;
/*  93 */           int x = posX - modradius + world.field_73012_v.nextInt(modradius * 2);
/*  94 */           int z = posZ - modradius + world.field_73012_v.nextInt(modradius * 2);
/*  95 */           int y = -1;
/*     */           
/*  97 */           if (Coord.distanceSq(x, 1.0D, z, posX, 1.0D, posZ) > modradiussq) {
/*  98 */             x = posX - modradius + world.field_73012_v.nextInt(modradius * 2);
/*  99 */             z = posZ - modradius + world.field_73012_v.nextInt(modradius * 2);
/* 100 */             if (Coord.distanceSq(x, 1.0D, z, posX, 1.0D, posZ) > modradiussq) {
/* 101 */               return RitualStep.Result.UPKEEP;
/*     */             }
/*     */           }
/*     */           
/* 105 */           world.func_72926_e(2005, posX, posY + 2, posZ, 0);
/*     */           
/* 107 */           Material material = world.func_147439_a(x, posY, z).func_149688_o();
/* 108 */           if ((material == null) || (!material.func_76220_a()) || (!world.func_147437_c(x, posY + 1, z))) {
/* 109 */             for (int h = 1; h < this.rite.height; h++) {
/* 110 */               material = world.func_147439_a(x, posY + h, z).func_149688_o();
/* 111 */               if ((material != null) && (material.func_76220_a()) && (isAirOrReplaceableBlock(world, x, posY + h + 1, z))) {
/* 112 */                 y = posY + h;
/* 113 */                 break;
/*     */               }
/*     */               
/* 116 */               material = world.func_147439_a(x, posY - h, z).func_149688_o();
/* 117 */               if ((material != null) && (material.func_76220_a()) && (isAirOrReplaceableBlock(world, x, posY - h + 1, z))) {
/* 118 */                 y = posY - h;
/* 119 */                 break;
/*     */               }
/*     */             }
/*     */           } else {
/* 123 */             y = posY;
/*     */           }
/*     */           
/* 126 */           if (y != -1) {
/* 127 */             world.func_72926_e(2005, x, y + 1, z, 0);
/*     */             
/*     */ 
/* 130 */             drawPixel(world, x, z, y, false);
/*     */           }
/*     */           
/* 133 */           return RitualStep.Result.UPKEEP;
/*     */         }
/* 135 */         return RitualStep.Result.COMPLETED;
/*     */       }
/*     */       
/* 138 */       return RitualStep.Result.COMPLETED;
/*     */     }
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
/* 196 */     private EntityPlayer fakePlayer = null;
/*     */     
/*     */     protected void drawPixel(World world, int x, int z, int y, boolean lower) {
/* 199 */       Block blockID = world.func_147439_a(x, y, z);
/* 200 */       boolean wasGrass = blockID == Blocks.field_150349_c;
/* 201 */       Material materialAbove = world.func_147439_a(x, y + 1, z).func_149688_o();
/* 202 */       if ((materialAbove != null) && (!materialAbove.func_76220_a()))
/*     */       {
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
/*     */ 
/*     */ 
/*     */ 
/* 226 */         new MutableBlock(this.rite.block, this.rite.metadata).mutate(world, x, y + 1, z, false);
/*     */         
/* 228 */         int count = 0;
/* 229 */         if (((this.fakePlayer == null) || (this.fakePlayer.field_70170_p != world)) && ((world instanceof WorldServer))) {
/* 230 */           this.fakePlayer = new FakePlayer((WorldServer)world, new GameProfile(java.util.UUID.randomUUID(), "[Minecraft]"));
/*     */         }
/*     */         
/* 233 */         ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, this.fakePlayer);
/* 234 */         Block saplingBlockID = world.func_147439_a(x, y + 1, z);
/* 235 */         while (((saplingBlockID == Blocks.field_150345_g) || (saplingBlockID == Witchery.Blocks.SAPLING)) && (count++ < 10)) {
/* 236 */           ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, this.fakePlayer);
/* 237 */           saplingBlockID = world.func_147439_a(x, y + 1, z);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteForestation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */