/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import cpw.mods.fml.common.event.FMLInterModComms;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ 
/*    */ public class ModHookWaila
/*    */   extends ModHook
/*    */ {
/*    */   public String getModID()
/*    */   {
/* 12 */     return "Waila";
/*    */   }
/*    */   
/*    */   protected void doInit()
/*    */   {
/* 17 */     FMLInterModComms.sendMessage(getModID(), "register", "com.emoniph.witchery.integration.ModHookWailaRegistrar.callbackRegister");
/*    */   }
/*    */   
/*    */   protected void doPostInit() {}
/*    */   
/*    */   protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookWaila.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */