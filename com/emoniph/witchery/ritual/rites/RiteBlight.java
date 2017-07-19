/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityMooshroom;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteBlight extends RiteExpandingEffect
/*     */ {
/*     */   public RiteBlight(int radius, int height)
/*     */   {
/*  26 */     super(radius, height, true);
/*     */   }
/*     */   
/*     */   public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced)
/*     */   {
/*  31 */     double radiusSq = radius * radius;
/*  32 */     double minSq = Math.max(0, (radius - 1) * (radius - 1));
/*  33 */     for (Object obj : world.field_73010_i) {
/*  34 */       EntityPlayer victim = (EntityPlayer)obj;
/*  35 */       double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/*  36 */       if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
/*  37 */         if (com.emoniph.witchery.Witchery.Items.POPPET.voodooProtectionActivated(player, null, victim, 6))
/*  38 */           return false;
/*  39 */         if (!victim.func_70644_a(Potion.field_76431_k)) {
/*  40 */           victim.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 2400, 1));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  45 */     ArrayList<EntityVillager> villagersToZombify = new ArrayList();
/*     */     
/*  47 */     ArrayList<EntityCow> cowsToSchroom = new ArrayList();
/*     */     
/*  49 */     ArrayList<EntityAnimal> animalsToSlay = new ArrayList();
/*     */     
/*  51 */     for (Object obj : world.field_72996_f) {
/*  52 */       if ((obj instanceof EntityVillager)) {
/*  53 */         EntityVillager victim = (EntityVillager)obj;
/*  54 */         double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/*  55 */         if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
/*  56 */           Log.instance().debug(String.format("Try Adding zombie %f %f %f", new Object[] { Double.valueOf(distanceSq), Double.valueOf(minSq), Double.valueOf(radiusSq) }));
/*  57 */           if (world.field_73012_v.nextInt(10) == 0) {
/*  58 */             Log.instance().debug("Added zombie");
/*  59 */             villagersToZombify.add(victim);
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*  64 */       else if ((obj instanceof EntityCow)) {
/*  65 */         EntityCow victim = (EntityCow)obj;
/*  66 */         double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/*  67 */         if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
/*  68 */           Log.instance().debug(String.format("Try Adding mooschroom %f %f %f", new Object[] { Double.valueOf(distanceSq), Double.valueOf(minSq), Double.valueOf(radiusSq) }));
/*  69 */           if (world.field_73012_v.nextInt(20) == 0) {
/*  70 */             Log.instance().debug("Added mooschroom");
/*  71 */             cowsToSchroom.add(victim);
/*  72 */           } else if (world.field_73012_v.nextInt(3) == 0) {
/*  73 */             animalsToSlay.add(victim);
/*     */           }
/*     */         }
/*  76 */       } else if ((obj instanceof EntityAnimal)) {
/*  77 */         EntityAnimal victim = (EntityAnimal)obj;
/*  78 */         double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
/*  79 */         if ((distanceSq > minSq) && (distanceSq <= radiusSq) && 
/*  80 */           (world.field_73012_v.nextInt(3) == 0)) {
/*  81 */           animalsToSlay.add(victim);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  87 */     for (EntityVillager zombieWannabe : villagersToZombify) {
/*  88 */       EntityZombie entityzombie = new EntityZombie(world);
/*  89 */       entityzombie.func_82149_j(zombieWannabe);
/*  90 */       world.func_72900_e(zombieWannabe);
/*  91 */       entityzombie.func_110161_a((IEntityLivingData)null);
/*  92 */       entityzombie.func_82229_g(true);
/*  93 */       if (zombieWannabe.func_70631_g_()) {
/*  94 */         entityzombie.func_82227_f(true);
/*     */       }
/*  96 */       world.func_72838_d(entityzombie);
/*  97 */       world.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
/*     */     }
/*     */     
/* 100 */     for (EntityCow zombieWannabe : cowsToSchroom) {
/* 101 */       EntityMooshroom entityzombie = new EntityMooshroom(world);
/* 102 */       entityzombie.func_82149_j(zombieWannabe);
/* 103 */       world.func_72900_e(zombieWannabe);
/* 104 */       entityzombie.func_110161_a((IEntityLivingData)null);
/* 105 */       world.func_72838_d(entityzombie);
/* 106 */       world.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
/*     */     }
/*     */     
/* 109 */     for (EntityAnimal animal : animalsToSlay) {
/* 110 */       animal.func_70097_a(DamageSource.field_76376_m, 20.0F);
/*     */     }
/*     */     
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced)
/*     */   {
/* 118 */     if (!world.field_72995_K) {
/* 119 */       Block blockID = world.func_147439_a(posX, posY, posZ);
/* 120 */       Block blockBelowID = world.func_147439_a(posX, posY - 1, posZ);
/* 121 */       if (blockID == Blocks.field_150329_H) {
/* 122 */         world.func_147468_f(posX, posY, posZ);
/* 123 */         blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
/* 124 */       } else if ((blockID == Blocks.field_150328_O) || (blockID == Blocks.field_150327_N) || (blockID == Blocks.field_150459_bM) || (blockID == Blocks.field_150464_aj) || (blockID == Blocks.field_150469_bN) || (blockID == Blocks.field_150393_bb) || (blockID == Blocks.field_150394_bc) || (blockID == Blocks.field_150440_ba) || (blockID == Blocks.field_150423_aK))
/*     */       {
/*     */ 
/* 127 */         world.func_147449_b(posX, posY, posZ, Blocks.field_150330_I);
/* 128 */         blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
/* 129 */       } else if (blockID == Blocks.field_150458_ak) {
/* 130 */         world.func_147449_b(posX, posY, posZ, Blocks.field_150354_m);
/* 131 */       } else if (blockID.func_149688_o().func_76220_a()) {
/* 132 */         blightGround(world, posX, posY, posZ, blockID, enhanced);
/* 133 */       } else if (blockBelowID.func_149688_o().func_76220_a()) {
/* 134 */         blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void blightGround(World world, int posX, int posY, int posZ, Block blockBelowID, boolean enhanced) {
/* 140 */     if ((blockBelowID == Blocks.field_150346_d) || (blockBelowID == Blocks.field_150349_c) || (blockBelowID == Blocks.field_150391_bh) || (blockBelowID == Blocks.field_150458_ak))
/*     */     {
/* 142 */       int rand = world.field_73012_v.nextInt(enhanced ? 4 : 5);
/* 143 */       if (rand == 0) {
/* 144 */         world.func_147449_b(posX, posY, posZ, Blocks.field_150354_m);
/* 145 */       } else if (rand == 1) {
/* 146 */         world.func_147449_b(posX, posY, posZ, Blocks.field_150346_d);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteBlight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */