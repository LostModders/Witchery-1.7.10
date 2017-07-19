/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class ModHookTinkersConstruct
/*    */   extends ModHook
/*    */ {
/*    */   public String getModID()
/*    */   {
/* 11 */     return "TConstruct";
/*    */   }
/*    */   
/*    */   protected void doInit()
/*    */   {
/* 16 */     Witchery.modHooks.isTinkersPresent = true;
/*    */   }
/*    */   
/*    */   protected void doPostInit() {}
/*    */   
/*    */   protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookTinkersConstruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */