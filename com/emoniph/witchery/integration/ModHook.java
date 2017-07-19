/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import cpw.mods.fml.common.Loader;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ModHook
/*    */ {
/* 14 */   protected boolean initialized = false;
/*    */   
/*    */   public abstract String getModID();
/*    */   
/*    */   public void init() {
/* 19 */     this.initialized = ((Config.instance().allowModIntegration) && (Loader.isModLoaded(getModID())));
/* 20 */     if (this.initialized) {
/* 21 */       doInit();
/* 22 */       Log.instance().debug(String.format("Mod: %s support initialized", new Object[] { getModID() }));
/*    */     } else {
/* 24 */       Log.instance().debug(String.format("Mod: %s not found", new Object[] { getModID() }));
/*    */     }
/*    */   }
/*    */   
/*    */   protected abstract void doInit();
/*    */   
/*    */   public void postInit() {
/* 31 */     if (this.initialized) {
/* 32 */       doPostInit();
/* 33 */       Log.instance().debug(String.format("Mod: %s support post initialized", new Object[] { getModID() }));
/*    */     }
/*    */   }
/*    */   
/*    */   protected abstract void doPostInit();
/*    */   
/*    */   public void reduceMagicPower(EntityLivingBase entity, float factor) {
/* 40 */     if (this.initialized) {
/* 41 */       doReduceMagicPower(entity, factor);
/*    */     }
/*    */   }
/*    */   
/*    */   protected abstract void doReduceMagicPower(EntityLivingBase paramEntityLivingBase, float paramFloat);
/*    */   
/*    */   public void boostBloodPowers(EntityPlayer player, float health) {}
/*    */   
/*    */   public boolean canVampireBeKilled(EntityPlayer player)
/*    */   {
/* 51 */     return false;
/*    */   }
/*    */   
/*    */   public void tryMakeItemModProof(ItemStack stack) {
/* 55 */     if (this.initialized) {
/* 56 */       makeItemModProof(stack);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void makeItemModProof(ItemStack stack) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */