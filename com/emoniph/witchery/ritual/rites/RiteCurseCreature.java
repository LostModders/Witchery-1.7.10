/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteCurseCreature extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private final boolean curse;
/*     */   private final int level;
/*     */   private final String curseType;
/*     */   
/*     */   public RiteCurseCreature(boolean curse, String curseType, int level)
/*     */   {
/*  32 */     this.curse = curse;
/*  33 */     this.level = level;
/*  34 */     this.curseType = curseType;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  39 */     steps.add(new StepCurseCreature(this));
/*     */   }
/*     */   
/*     */   private static class StepCurseCreature extends RitualStep {
/*     */     private final RiteCurseCreature rite;
/*     */     private static final int CURSE_MASTER_BONUS_LEVELS = 1;
/*     */     
/*     */     public StepCurseCreature(RiteCurseCreature rite) {
/*  47 */       super();
/*  48 */       this.rite = rite;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  55 */       if (ticks % 20L != 0L) {
/*  56 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  59 */       if (!world.field_72995_K)
/*     */       {
/*  61 */         boolean complete = false;
/*  62 */         boolean cursed = false;
/*     */         
/*  64 */         EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
/*  65 */         int levelBuff = (curseMasterPlayer != null) && (com.emoniph.witchery.familiar.Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer)) ? 1 : 0;
/*  66 */         if (ritual.covenSize == 6) {
/*  67 */           levelBuff += 2;
/*  68 */         } else if (ritual.covenSize >= 3) {
/*  69 */           levelBuff++;
/*     */         }
/*     */         
/*  72 */         for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
/*  73 */           if ((item.itemstack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) && (item.itemstack.func_77960_j() == 1)) {
/*  74 */             EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, item.itemstack, Integer.valueOf(1));
/*  75 */             if (entity == null) break;
/*  76 */             NBTTagCompound nbtTag = (entity instanceof EntityPlayer) ? Infusion.getNBT(entity) : entity.getEntityData();
/*  77 */             if (nbtTag != null) {
/*  78 */               int currentLevel = nbtTag.func_74764_b(this.rite.curseType) ? nbtTag.func_74762_e(this.rite.curseType) : 0;
/*  79 */               if (this.rite.curse) {
/*  80 */                 com.emoniph.witchery.entity.EntityWitchHunter.blackMagicPerformed(curseMasterPlayer);
/*     */                 
/*  82 */                 boolean isImmune = (com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(entity)) && ((this.rite.curseType.equals("witcheryCursed")) || (this.rite.curseType.equals("witcheryWakingNightmare")));
/*  83 */                 if (!isImmune) {
/*  84 */                   isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, this.rite);
/*     */                 }
/*     */                 
/*  87 */                 if (!isImmune) { if (!Witchery.Items.POPPET.voodooProtectionActivated(curseMasterPlayer, null, entity, levelBuff > 0 ? 3 : 1)) {
/*  88 */                     nbtTag.func_74768_a(this.rite.curseType, Math.max(this.rite.level + levelBuff, currentLevel));
/*  89 */                     cursed = true;
/*  90 */                     if ((entity instanceof EntityPlayer)) {
/*  91 */                       Infusion.syncPlayer(entity.field_70170_p, (EntityPlayer)entity);
/*     */                     }
/*     */                   }
/*     */                 }
/*  95 */                 if (isImmune) {
/*  96 */                   if (curseMasterPlayer != null) {
/*  97 */                     ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.blackmagicdampening", new Object[0]);
/*     */                   }
/*     */                 } else {
/* 100 */                   complete = true;
/*     */                 }
/*     */               } else {
/* 103 */                 int newLevel = 0;
/* 104 */                 if (currentLevel > 0) {
/* 105 */                   if (this.rite.level + levelBuff > currentLevel) {
/* 106 */                     newLevel = world.field_73012_v.nextInt(20) == 0 ? currentLevel + 1 : 0;
/* 107 */                   } else if (this.rite.level + levelBuff < currentLevel) {
/* 108 */                     newLevel = world.field_73012_v.nextInt(4) == 0 ? 0 : currentLevel + 1;
/*     */                   } else {
/* 110 */                     newLevel = world.field_73012_v.nextInt(4) == 0 ? currentLevel + 1 : 0;
/*     */                   }
/*     */                 }
/* 113 */                 if (newLevel == 0) {
/* 114 */                   if (nbtTag.func_74764_b(this.rite.curseType)) {
/* 115 */                     nbtTag.func_82580_o(this.rite.curseType);
/*     */                   }
/* 117 */                   if (entity.func_70644_a(Potion.field_76436_u)) {
/* 118 */                     entity.func_82170_o(Potion.field_76436_u.field_76415_H);
/*     */                   }
/* 120 */                   if (entity.func_70644_a(Potion.field_76437_t)) {
/* 121 */                     entity.func_82170_o(Potion.field_76437_t.field_76415_H);
/*     */                   }
/* 123 */                   if (entity.func_70644_a(Potion.field_76440_q)) {
/* 124 */                     entity.func_82170_o(Potion.field_76440_q.field_76415_H);
/*     */                   }
/* 126 */                   if (entity.func_70644_a(Potion.field_76419_f)) {
/* 127 */                     entity.func_82170_o(Potion.field_76419_f.field_76415_H);
/*     */                   }
/* 129 */                   if (entity.func_70644_a(Potion.field_76421_d)) {
/* 130 */                     entity.func_82170_o(Potion.field_76421_d.field_76415_H);
/*     */                   }
/*     */                 } else {
/* 133 */                   nbtTag.func_74768_a(this.rite.curseType, newLevel);
/* 134 */                   cursed = true;
/*     */                 }
/*     */                 
/* 137 */                 if ((entity instanceof EntityPlayer)) {
/* 138 */                   Infusion.syncPlayer(entity.field_70170_p, (EntityPlayer)entity);
/*     */                 }
/* 140 */                 complete = true;
/*     */               }
/*     */             }
/*     */             
/* 144 */             break;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 149 */         if (complete) {
/* 150 */           if (cursed) {
/* 151 */             ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*     */           } else {
/* 153 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_LEVELUP, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*     */           }
/*     */         } else {
/* 156 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */       }
/* 159 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteCurseCreature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */