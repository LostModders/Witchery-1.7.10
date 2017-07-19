/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.monster.EntityGhast;
/*     */ import net.minecraft.entity.monster.EntityPigZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteHellOnEarth extends RiteExpandingEffect
/*     */ {
/*     */   private final float upkeepCost;
/*     */   static final int POWER_SOURCE_RADIUS = 16;
/*     */   
/*     */   public RiteHellOnEarth(int radius, int height, float upkeepCost)
/*     */   {
/*  30 */     super(radius, height, true);
/*     */     
/*  32 */     this.upkeepCost = upkeepCost;
/*     */   }
/*     */   
/*     */   public boolean isComplete(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, long ticks, boolean fullyExpanded, boolean enhanced)
/*     */   {
/*  37 */     if ((fullyExpanded) && (ticks % 40L == 0L)) {
/*  38 */       IPowerSource powerSource = findNewPowerSource(world, posX, posY, posZ);
/*  39 */       if (powerSource == null) {
/*  40 */         return true;
/*     */       }
/*     */       
/*  43 */       if (!powerSource.consumePower(this.upkeepCost)) {
/*  44 */         return true;
/*     */       }
/*     */       
/*  47 */       double roll = world.field_73012_v.nextDouble();
/*  48 */       EntityLiving entity = null;
/*  49 */       if (roll < 0.02D) {
/*  50 */         entity = new EntityDemon(world);
/*  51 */       } else if (roll < 0.1D) {
/*  52 */         entity = new EntityGhast(world);
/*  53 */       } else if (roll < 0.4D) {
/*  54 */         entity = new net.minecraft.entity.monster.EntityBlaze(world);
/*  55 */       } else if (roll < 0.6D) {
/*  56 */         entity = new net.minecraft.entity.monster.EntityMagmaCube(world);
/*     */       } else {
/*  58 */         entity = new EntityPigZombie(world);
/*     */       }
/*     */       
/*  61 */       if (entity != null) {
/*  62 */         entity.func_110161_a((IEntityLivingData)null);
/*  63 */         entity.func_70012_b(0.5D + posX, 2.0D + posY, 0.5D + posZ, 0.0F, 0.0F);
/*  64 */         world.func_72838_d(entity);
/*  65 */         ParticleEffect.LARGE_EXPLODE.send(SoundEffect.MOB_BLAZE_DEATH, world, 0.5D + posX, 2.0D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*     */       }
/*     */     }
/*     */     
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced)
/*     */   {
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced)
/*     */   {
/*  79 */     if (!world.field_72995_K) {
/*  80 */       Block blockID = world.func_147439_a(posX, posY, posZ);
/*  81 */       Block blockBelowID = world.func_147439_a(posX, posY - 1, posZ);
/*  82 */       if (blockID == Blocks.field_150329_H) {
/*  83 */         if ((Config.instance().allowHellOnEarthFires) && (enhanced)) {
/*  84 */           world.func_147449_b(posX, posY, posZ, Blocks.field_150480_ab);
/*     */         }
/*  86 */         blightGround(world, posX, posY - 1, posZ, blockBelowID, currentRadius);
/*  87 */       } else if ((blockID == Blocks.field_150328_O) || (blockID == Blocks.field_150327_N) || (blockID == Blocks.field_150459_bM) || (blockID == Blocks.field_150464_aj) || (blockID == Blocks.field_150469_bN) || (blockID == Blocks.field_150393_bb) || (blockID == Blocks.field_150394_bc) || (blockID == Blocks.field_150440_ba) || (blockID == Blocks.field_150423_aK))
/*     */       {
/*     */ 
/*  90 */         if ((Config.instance().allowHellOnEarthFires) && (enhanced)) {
/*  91 */           world.func_147449_b(posX, posY, posZ, Blocks.field_150480_ab);
/*     */         }
/*  93 */         blightGround(world, posX, posY - 1, posZ, blockBelowID, currentRadius);
/*  94 */       } else if (blockID.func_149688_o().func_76220_a()) {
/*  95 */         blightGround(world, posX, posY, posZ, blockID, currentRadius);
/*  96 */       } else if (blockBelowID.func_149688_o().func_76220_a()) {
/*  97 */         blightGround(world, posX, posY - 1, posZ, blockBelowID, currentRadius);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void blightGround(World world, int posX, int posY, int posZ, Block blockBelowID, int currentRadius) {
/* 103 */     if ((blockBelowID == Blocks.field_150346_d) || (blockBelowID == Blocks.field_150349_c) || (blockBelowID == Blocks.field_150391_bh) || (blockBelowID == Blocks.field_150458_ak) || (blockBelowID == Blocks.field_150354_m))
/*     */     {
/* 105 */       int rand = world.field_73012_v.nextInt(currentRadius < this.maxRadius / 2 ? 4 : currentRadius < this.maxRadius / 3 ? 2 : 6);
/* 106 */       if (rand == 0) {
/* 107 */         world.func_147449_b(posX, posY, posZ, Blocks.field_150424_aL);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
/*     */   {
/* 115 */     List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
/* 116 */     return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteHellOnEarth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */