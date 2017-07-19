/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class RiteCallCreatures extends Rite
/*     */ {
/*     */   private final float radius;
/*     */   private final List<Class<? extends EntityCreature>> creatureTypes;
/*     */   
/*     */   public RiteCallCreatures(float radius, Class<? extends EntityCreature>[] creatureTypes)
/*     */   {
/*  28 */     this.radius = radius;
/*  29 */     this.creatureTypes = Arrays.asList(creatureTypes);
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*     */   {
/*  34 */     steps.add(new StepCallCreatures(this, initialStage));
/*     */   }
/*     */   
/*     */   private static class StepCallCreatures extends RitualStep
/*     */   {
/*     */     private final RiteCallCreatures rite;
/*  40 */     private int stage = 0;
/*     */     
/*     */     public StepCallCreatures(RiteCallCreatures rite, int stage) {
/*  43 */       super();
/*  44 */       this.rite = rite;
/*  45 */       this.stage = stage;
/*     */     }
/*     */     
/*     */     private void allure(World world, double posX, double posY, double posZ, int quad) {
/*     */       try {
/*  50 */         float r = 128.0F;
/*  51 */         float dy = 10.0F;
/*  52 */         AxisAlignedBB bounds = null;
/*     */         
/*  54 */         switch (quad) {
/*     */         case 0: 
/*  56 */           bounds = AxisAlignedBB.func_72330_a(posX, posY - 10.0D, posZ - 128.0D, posX + 128.0D, posY, posZ);
/*  57 */           break;
/*     */         case 1: 
/*  59 */           bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY - 10.0D, posZ - 128.0D, posX, posY, posZ);
/*  60 */           break;
/*     */         case 2: 
/*  62 */           bounds = AxisAlignedBB.func_72330_a(posX, posY - 10.0D, posZ, posX + 128.0D, posY, posZ + 128.0D);
/*  63 */           break;
/*     */         case 3: 
/*  65 */           bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY - 10.0D, posZ, posX, posY, posZ + 128.0D);
/*  66 */           break;
/*     */         case 4: 
/*  68 */           bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY + 1.0D, posZ - 128.0D, posX, posY + 10.0D, posZ);
/*  69 */           break;
/*     */         case 5: 
/*  71 */           bounds = AxisAlignedBB.func_72330_a(posX, posY + 1.0D, posZ, posX + 128.0D, posY + 10.0D, posZ + 128.0D);
/*  72 */           break;
/*     */         case 6: 
/*  74 */           bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY + 1.0D, posZ, posX, posY + 10.0D, posZ + 128.0D);
/*  75 */           break;
/*     */         case 7: 
/*     */         default: 
/*  78 */           bounds = AxisAlignedBB.func_72330_a(posX, posY + 1.0D, posZ - 128.0D, posX + 128.0D, posY + 10.0D, posZ);
/*     */         }
/*     */         
/*     */         
/*  82 */         int count = 0;
/*  83 */         int minDistanceSq = 32;
/*  84 */         for (Iterator i$ = world.func_72872_a(EntityCreature.class, bounds).iterator(); i$.hasNext(); 
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  90 */             count >= 2)
/*     */         {
/*  84 */           Object obj = i$.next();
/*  85 */           EntityCreature creature = (EntityCreature)obj;
/*  86 */           if ((this.rite.creatureTypes.contains(creature.getClass())) && (creature.func_70092_e(posX, posY, posZ) > 32.0D) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(creature, 0)))
/*     */           {
/*     */ 
/*  89 */             com.emoniph.witchery.item.ItemGeneral.teleportToLocation(world, posX - 2.0D + world.field_73012_v.nextInt(5), posY, posZ - 2.0D + world.field_73012_v.nextInt(5), world.field_73011_w.field_76574_g, creature, true);
/*  90 */             count++;
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*  96 */         Log.instance().debug(String.format("Exception occurred alluring with a ritual! %s", new Object[] { e.toString() }));
/*     */       }
/*     */     }
/*     */     
/*     */     public int getCurrentStage()
/*     */     {
/* 102 */       return this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/* 107 */       if (ticks % 60L != 0L) {
/* 108 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/* 111 */       if (!world.field_72995_K)
/*     */       {
/* 113 */         if (ritual.covenSize < 3) {
/* 114 */           net.minecraft.entity.player.EntityPlayer player = ritual.getInitiatingPlayer(world);
/* 115 */           SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/* 116 */           if (player != null) {
/* 117 */             ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
/*     */           }
/* 119 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/* 122 */         allure(world, posX, posY, posZ, ++this.stage % 8);
/*     */       }
/* 124 */       return this.stage < 250 ? RitualStep.Result.UPKEEP : RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteCallCreatures.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */