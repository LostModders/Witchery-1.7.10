/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import WayofTime.alchemicalWizardry.api.event.SacrificeKnifeUsedEvent;
/*     */ import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ public class ModHookBloodMagic extends ModHook
/*     */ {
/*     */   public String getModID()
/*     */   {
/*  19 */     return "AWWayofTime";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doInit() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doPostInit()
/*     */   {
/*     */     try
/*     */     {
/*  42 */       MinecraftForge.EVENT_BUS.register(new EventHooks());
/*     */     }
/*     */     catch (Throwable ex) {
/*  45 */       Log.instance().debug(String.format("Tried and failed to install hooks for Blood Magic dagger event. %s", new Object[] { ex.toString() }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doReduceMagicPower(EntityLivingBase entity, float factor)
/*     */   {
/*  53 */     IntegrateBloodMagic.reduceMagicPower(entity, factor);
/*     */   }
/*     */   
/*     */   public void boostBloodPowers(EntityPlayer player, float health)
/*     */   {
/*  58 */     IntegrateBloodMagic.boostBloodPowers(player, health);
/*     */   }
/*     */   
/*     */   public static class EventHooks
/*     */   {
/*     */     @SubscribeEvent
/*     */     public void onSacrificeKnifeUsed(SacrificeKnifeUsedEvent event) {
/*  65 */       ExtendedPlayer playerEx = ExtendedPlayer.get(event.player);
/*  66 */       if ((playerEx != null) && (playerEx.isVampire())) {
/*  67 */         event.shouldDrainHealth = false;
/*  68 */         if ((!event.player.field_70170_p.field_72995_K) && 
/*  69 */           (!playerEx.decreaseBloodPower(event.healthDrained * 100, true))) {
/*  70 */           event.shouldFillAltar = false;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static class IntegrateBloodMagic
/*     */   {
/*     */     public static void reduceMagicPower(EntityLivingBase entity, float factor)
/*     */     {
/*  80 */       if ((entity != null) && ((entity instanceof EntityPlayer))) {
/*  81 */         EntityPlayer player = (EntityPlayer)entity;
/*  82 */         int essence = SoulNetworkHandler.getCurrentEssence(player.func_70005_c_());
/*  83 */         if (Config.instance().isDebugging()) {
/*  84 */           Log.instance().debug(String.format("reduceMagicPower for Blood Magic (%s lp=%d)", new Object[] { player.func_70005_c_(), Integer.valueOf(essence) }));
/*     */         }
/*     */         
/*     */         float reduction;
/*     */         
/*  89 */         if (essence <= 5000) {
/*  90 */           reduction = 5000.0F * factor; } else { float reduction;
/*  91 */           if (essence <= 25000) {
/*  92 */             reduction = 25000.0F * factor; } else { float reduction;
/*  93 */             if (essence <= 150000) {
/*  94 */               reduction = 150000.0F * factor; } else { float reduction;
/*  95 */               if (essence <= 1000000) {
/*  96 */                 reduction = 1000000.0F * factor; } else { float reduction;
/*  97 */                 if (essence <= 10000000) {
/*  98 */                   reduction = 1.0E7F * factor;
/*     */                 } else
/* 100 */                   reduction = 2.14748365E9F * factor;
/*     */               } } } }
/* 102 */         float reduction = Math.max(reduction, 1.0F);
/* 103 */         int newEssence = Math.max((int)(essence - reduction), 0);
/* 104 */         SoulNetworkHandler.setCurrentEssence(player.func_70005_c_(), newEssence);
/*     */       }
/*     */     }
/*     */     
/*     */     public static void boostBloodPowers(EntityPlayer player, float health) {
/* 109 */       int LP_PER_LIFE = 100;
/* 110 */       String playerName = player.func_70005_c_();
/* 111 */       int newlevel = SoulNetworkHandler.getCurrentEssence(playerName) + (int)health * 100;
/* 112 */       SoulNetworkHandler.setCurrentEssence(playerName, newlevel);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookBloodMagic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */