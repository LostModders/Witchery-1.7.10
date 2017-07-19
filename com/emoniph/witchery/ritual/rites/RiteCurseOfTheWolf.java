/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.entity.EntityVillagerWere;
/*     */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import com.emoniph.witchery.familiar.Familiar;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteCurseOfTheWolf extends Rite
/*     */ {
/*     */   private final boolean curse;
/*     */   
/*     */   public RiteCurseOfTheWolf(boolean curse)
/*     */   {
/*  36 */     this.curse = curse;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  41 */     steps.add(new StepCurseCreature(this));
/*     */   }
/*     */   
/*     */   private static class StepCurseCreature extends RitualStep
/*     */   {
/*     */     private final RiteCurseOfTheWolf rite;
/*     */     
/*     */     public StepCurseCreature(RiteCurseOfTheWolf rite) {
/*  49 */       super();
/*  50 */       this.rite = rite;
/*     */     }
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
/*     */         
/*  66 */         if (!CreatureUtil.isFullMoon(world)) {
/*  67 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requiresfullmoon", new Object[0]);
/*     */           
/*  69 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/*  72 */         if (!Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer)) {
/*  73 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requirescat", new Object[0]);
/*     */           
/*  75 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/*  78 */         if (ritual.covenSize < 6) {
/*  79 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requiresfullcoven", new Object[0]);
/*     */           
/*  81 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/*  84 */         for (Iterator i$ = ritual.sacrificedItems.iterator(); i$.hasNext(); 
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
/* 167 */             goto 713)
/*     */         {
/*  84 */           RitualStep.SacrificedItem item = (RitualStep.SacrificedItem)i$.next();
/*  85 */           if ((item.itemstack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) && (item.itemstack.func_77960_j() == 1))
/*     */           {
/*  87 */             net.minecraft.entity.EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, item.itemstack, Integer.valueOf(1));
/*     */             
/*  89 */             if (entity == null) break;
/*  90 */             if (this.rite.curse) {
/*  91 */               EntityWitchHunter.blackMagicPerformed(curseMasterPlayer);
/*     */               
/*  93 */               boolean isImmune = com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(entity);
/*  94 */               if (!isImmune) {
/*  95 */                 isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, this.rite);
/*     */               }
/*     */               
/*     */ 
/*  99 */               if ((!isImmune) && (!Witchery.Items.POPPET.voodooProtectionActivated(curseMasterPlayer, null, entity, 3)))
/*     */               {
/*     */ 
/* 102 */                 if ((entity instanceof EntityPlayer)) {
/* 103 */                   EntityPlayer player = (EntityPlayer)entity;
/* 104 */                   ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*     */                   
/* 106 */                   if ((!Config.instance().allowVampireWolfHybrids) && (playerEx.isVampire())) {
/* 107 */                     ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.hybridsnotallow", new Object[0]);
/*     */                   }
/* 109 */                   else if (playerEx.getWerewolfLevel() == 0) {
/* 110 */                     playerEx.setWerewolfLevel(1);
/* 111 */                     ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "witchery.werewolf.infection", new Object[0]);
/*     */                     
/* 113 */                     complete = true;
/* 114 */                     cursed = true;
/*     */                   } else {
/* 116 */                     ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.alreadyactive", new Object[0]);
/*     */                   }
/*     */                 }
/* 119 */                 else if (((entity instanceof EntityVillager)) && (!(entity instanceof EntityVillagerWere)))
/*     */                 {
/* 121 */                   EntityVillager villager = (EntityVillager)entity;
/* 122 */                   EntityWolfman.convertToCuredVillager(villager, villager.func_70946_n(), villager.field_70956_bz, villager.field_70963_i);
/* 123 */                   complete = true;
/* 124 */                   cursed = true;
/*     */                 } else {
/* 126 */                   ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.nothuman", new Object[0]);
/*     */                 }
/*     */               }
/*     */               
/*     */ 
/* 131 */               if ((isImmune) && 
/* 132 */                 (curseMasterPlayer != null)) {
/* 133 */                 ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.blackmagicdampening", new Object[0]);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 138 */             else if ((entity instanceof EntityPlayer)) {
/* 139 */               EntityPlayer player = (EntityPlayer)entity;
/* 140 */               ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 141 */               if (playerEx.getWerewolfLevel() > 0) {
/* 142 */                 double MAX_RANGE_SQ = 64.0D;
/* 143 */                 if ((playerEx.getWerewolfLevel() == 1) || (player.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ) <= 64.0D)) {
/* 144 */                   if (world.field_73012_v.nextInt(4) != 0) {
/* 145 */                     playerEx.setWerewolfLevel(0);
/*     */                   } else {
/* 147 */                     cursed = true;
/*     */                   }
/* 149 */                   complete = true;
/*     */                 } else {
/* 151 */                   ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.toofar", new Object[0]);
/*     */                 }
/*     */               }
/*     */               else {
/* 155 */                 ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
/*     */               }
/*     */             }
/* 158 */             else if ((entity instanceof EntityVillagerWere)) {
/* 159 */               EntityVillagerWere villager = (EntityVillagerWere)entity;
/* 160 */               EntityWolfman.convertToCuredVillager(villager, villager.func_70946_n(), villager.field_70956_bz, villager.field_70963_i);
/* 161 */               complete = true;
/* 162 */             } else if ((entity instanceof EntityWolfman)) {
/* 163 */               EntityWolfman villager = (EntityWolfman)entity;
/* 164 */               EntityWolfman.convertToCuredVillager(villager, villager.getFormerProfession(), villager.getWealth(), villager.getBuyingList());
/* 165 */               complete = true;
/*     */             } else {
/* 167 */               ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 176 */         if (complete) {
/* 177 */           if (cursed) {
/* 178 */             ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*     */           }
/*     */           else {
/* 181 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_LEVELUP, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*     */           }
/*     */         }
/*     */         else {
/* 185 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */       }
/* 188 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteCurseOfTheWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */