/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.familiar.Familiar;
/*     */ import com.emoniph.witchery.item.ItemTaglockKit;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RiteRemoveVampirism extends Rite
/*     */ {
/*     */   public void addSteps(ArrayList<RitualStep> steps, int intialStage)
/*     */   {
/*  29 */     steps.add(new StepCurseCreature(this));
/*     */   }
/*     */   
/*     */   private static class StepCurseCreature extends RitualStep
/*     */   {
/*     */     private final RiteRemoveVampirism rite;
/*     */     
/*     */     public StepCurseCreature(RiteRemoveVampirism rite) {
/*  37 */       super();
/*  38 */       this.rite = rite;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/*  43 */       if (ticks % 20L != 0L) {
/*  44 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/*  47 */       if (!world.field_72995_K)
/*     */       {
/*  49 */         boolean complete = false;
/*  50 */         boolean cursed = false;
/*     */         
/*  52 */         EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
/*     */         
/*  54 */         if (!Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer)) {
/*  55 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requirescat", new Object[0]);
/*     */           
/*  57 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/*  60 */         if (ritual.covenSize < 6) {
/*  61 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requiresfullcoven", new Object[0]);
/*     */           
/*  63 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */         
/*  66 */         for (Iterator i$ = ritual.sacrificedItems.iterator(); i$.hasNext(); 
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
/*  93 */             goto 327)
/*     */         {
/*  66 */           RitualStep.SacrificedItem item = (RitualStep.SacrificedItem)i$.next();
/*  67 */           if ((item.itemstack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) && (item.itemstack.func_77960_j() == 1))
/*     */           {
/*  69 */             EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, item.itemstack, Integer.valueOf(1));
/*     */             
/*  71 */             if (entity == null) break;
/*  72 */             if ((entity instanceof EntityPlayer)) {
/*  73 */               EntityPlayer player = (EntityPlayer)entity;
/*  74 */               ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  75 */               if (playerEx.isVampire()) {
/*  76 */                 double MAX_RANGE_SQ = 64.0D;
/*  77 */                 if (player.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ) <= 64.0D) {
/*  78 */                   if (world.field_73012_v.nextInt(4) != 0) {
/*  79 */                     playerEx.setVampireLevel(0);
/*     */                   } else {
/*  81 */                     cursed = true;
/*     */                   }
/*  83 */                   complete = true;
/*     */                 } else {
/*  85 */                   ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.toofar", new Object[0]);
/*     */                 }
/*     */               }
/*     */               else {
/*  89 */                 ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
/*     */               }
/*     */             }
/*     */             else {
/*  93 */               ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 101 */         if (complete) {
/* 102 */           if (cursed) {
/* 103 */             ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*     */           }
/*     */           else {
/* 106 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_LEVELUP, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
/*     */           }
/*     */         }
/*     */         else {
/* 110 */           return RitualStep.Result.ABORTED_REFUND;
/*     */         }
/*     */       }
/* 113 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteRemoveVampirism.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */