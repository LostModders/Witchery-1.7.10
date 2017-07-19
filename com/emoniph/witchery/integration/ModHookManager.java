/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ModHookManager
/*    */ {
/* 13 */   private ArrayList<ModHook> hooks = new ArrayList();
/*    */   public boolean isTinkersPresent;
/*    */   
/*    */   public void register(Class<? extends ModHook> clazz) {
/* 17 */     try { ModHook hook = (ModHook)clazz.newInstance();
/* 18 */       this.hooks.add(hook);
/*    */     } catch (Throwable e) {
/* 20 */       Log.instance().warning(e, "unhandled exception loading ModHook");
/*    */     } }
/*    */   
/*    */   public boolean isAM2Present;
/*    */   public boolean isMorphPresent;
/* 25 */   public void init() { for (ModHook hook : this.hooks) {
/*    */       try {
/* 27 */         hook.init();
/*    */       } catch (Throwable e) {
/* 29 */         Log.instance().warning(e, String.format("unhandled exception init for hook %s", new Object[] { hook.getModID() }));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void postInit() {
/* 35 */     for (ModHook hook : this.hooks) {
/*    */       try {
/* 37 */         hook.postInit();
/*    */       } catch (Throwable e) {
/* 39 */         Log.instance().warning(e, String.format("unhandled exception post init for hook %s", new Object[] { hook.getModID() }));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void reducePowerLevels(EntityLivingBase entity, float reduction) {
/* 45 */     if ((entity == null) || (entity.field_70170_p == null) || (entity.field_70170_p.field_72995_K)) {
/* 46 */       return;
/*    */     }
/*    */     
/*    */ 
/* 50 */     if ((entity instanceof EntityPlayer)) {
/* 51 */       EntityPlayer player = (EntityPlayer)entity;
/* 52 */       int maxEnergy = Infusion.getMaxEnergy(player);
/* 53 */       int currentEnergy = Infusion.getCurrentEnergy(player);
/* 54 */       if ((maxEnergy > 0) && (currentEnergy > 0)) {
/* 55 */         int reduceBy = Math.max((int)(maxEnergy * reduction), 1);
/* 56 */         int newMana = Math.max(currentEnergy - reduceBy, 0);
/* 57 */         Infusion.setCurrentEnergy(player, newMana);
/*    */       }
/*    */     }
/*    */     
/* 61 */     for (ModHook hook : this.hooks) {
/*    */       try {
/* 63 */         hook.reduceMagicPower(entity, reduction);
/*    */       } catch (Throwable e) {
/* 65 */         Log.instance().warning(e, String.format("unhandled exception post init for hook %s", new Object[] { hook.getModID() }));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void boostBloodPowers(EntityPlayer player, float health) {
/* 71 */     for (ModHook hook : this.hooks) {
/*    */       try {
/* 73 */         hook.boostBloodPowers(player, health);
/*    */       } catch (Throwable e) {
/* 75 */         Log.instance().warning(e, String.format("unhandled exception post init for hook %s", new Object[] { hook.getModID() }));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean canVampireBeKilled(EntityPlayer player) {
/* 81 */     for (ModHook hook : this.hooks) {
/*    */       try {
/* 83 */         if (hook.canVampireBeKilled(player)) {
/* 84 */           return true;
/*    */         }
/*    */       } catch (Throwable e) {
/* 87 */         Log.instance().warning(e, String.format("unhandled exception post init for hook %s", new Object[] { hook.getModID() }));
/*    */       }
/*    */     }
/* 90 */     return false;
/*    */   }
/*    */   
/*    */   public void makeItemModProof(ItemStack stack) {
/* 94 */     for (ModHook hook : this.hooks) {
/*    */       try {
/* 96 */         hook.tryMakeItemModProof(stack);
/*    */       } catch (Throwable e) {
/* 98 */         Log.instance().warning(e, String.format("unhandled exception post init for hook %s", new Object[] { hook.getModID() }));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */