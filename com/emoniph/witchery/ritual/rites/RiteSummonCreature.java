/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import com.emoniph.witchery.entity.EntityImp;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RiteRegistry;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TameableUtil;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteSummonCreature extends Rite
/*     */ {
/*     */   private final Class<? extends EntityCreature> creatureToSummon;
/*     */   private boolean bindTameable;
/*     */   
/*     */   public RiteSummonCreature(Class<? extends EntityCreature> creatureToSummon, boolean bindTameable)
/*     */   {
/*  34 */     this.creatureToSummon = creatureToSummon;
/*  35 */     this.bindTameable = bindTameable;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  40 */     steps.add(new StepSummonCreature(this));
/*     */   }
/*     */   
/*     */   private static class StepSummonCreature extends RitualStep
/*     */   {
/*     */     private final RiteSummonCreature rite;
/*     */     
/*     */     public StepSummonCreature(RiteSummonCreature rite) {
/*  48 */       super();
/*  49 */       this.rite = rite;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  54 */       if (ticks % 20L != 0L) {
/*  55 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  58 */       if (!world.field_72995_K)
/*     */       {
/*  60 */         int[][] PATTERN = { { 0, 0, 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 2, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1, 0, 0 } };
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */         int obstructions = 0;
/*  70 */         for (int y = posY + 1; y <= posY + 3; y++) {
/*  71 */           int offsetZ = (PATTERN.length - 1) / 2;
/*  72 */           for (int z = 0; z < PATTERN.length - 1; z++) {
/*  73 */             int worldZ = posZ - offsetZ + z;
/*  74 */             int offsetX = (PATTERN[z].length - 1) / 2;
/*  75 */             for (int x = 0; x < PATTERN[z].length; x++) {
/*  76 */               int worldX = posX - offsetX + x;
/*  77 */               int val = PATTERN[(PATTERN.length - 1 - z)][x];
/*  78 */               if (val == 1) {
/*  79 */                 Material material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
/*  80 */                 if ((material != null) && (material.func_76220_a())) {
/*  81 */                   obstructions++;
/*     */                 }
/*  83 */               } else if (val == 2) {
/*  84 */                 Material material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
/*  85 */                 if ((material != null) && (material.func_76220_a())) {
/*  86 */                   obstructions += 100;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  93 */         int MAX_OBSTRUCTIONS = 1;
/*  94 */         if (obstructions <= 1) {
/*     */           try {
/*  96 */             Constructor ctor = this.rite.creatureToSummon.getConstructor(new Class[] { World.class });
/*  97 */             EntityLiving entity = (EntityLiving)ctor.newInstance(new Object[] { world });
/*  98 */             if ((entity instanceof EntityDemon)) {
/*  99 */               ((EntityDemon)entity).setPlayerCreated(true);
/*     */             }
/*     */             else {
/* 102 */               if (((entity instanceof EntityImp)) && (ritual.covenSize == 0)) {
/* 103 */                 EntityPlayer player = ritual.getInitiatingPlayer(world);
/* 104 */                 SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/* 105 */                 if (player != null) {
/* 106 */                   ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
/*     */                 }
/* 108 */                 return RitualStep.Result.ABORTED_REFUND; }
/* 109 */               if ((this.rite.bindTameable) && ((entity instanceof EntityTameable))) {
/* 110 */                 ((EntityTameable)entity).func_70903_f(true);
/* 111 */                 TameableUtil.setOwner((EntityTameable)entity, ritual.getInitiatingPlayer(world));
/*     */               }
/*     */             }
/* 114 */             entity.func_70012_b(0.5D + posX, 1.0D + posY, 0.5D + posZ, 1.0F, 0.0F);
/*     */             
/* 116 */             world.func_72838_d(entity);
/* 117 */             IEntityLivingData entitylivingData = null;
/* 118 */             entitylivingData = entity.func_110161_a(entitylivingData);
/*     */             
/* 120 */             ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 1.0D, 16);
/*     */           }
/*     */           catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 127 */         ParticleEffect.LARGE_SMOKE.send(SoundEffect.NOTE_SNARE, world, posX, posY, posZ, 0.5D, 2.0D, 16);
/* 128 */         RiteRegistry.RiteError("witchery.rite.obstructedcircle", ritual.getInitiatingPlayerName(), world);
/* 129 */         return RitualStep.Result.ABORTED_REFUND;
/*     */       }
/*     */       
/* 132 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteSummonCreature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */