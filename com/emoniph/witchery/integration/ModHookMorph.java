/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class ModHookMorph extends ModHook
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   private static Method methodHasMorph;
/*    */   
/*    */   public String getModID()
/*    */   {
/* 17 */     return "Morph";
/*    */   }
/*    */   
/*    */   protected void doInit()
/*    */   {
/* 22 */     Witchery.modHooks.isMorphPresent = true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void doPostInit() {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected void doReduceMagicPower(net.minecraft.entity.EntityLivingBase entity, float factor) {}
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public static boolean isMorphed(EntityPlayer player, boolean client)
/*    */   {
/* 39 */     if (!Witchery.modHooks.isMorphPresent) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     if (methodHasMorph == null) {
/*    */       try {
/* 45 */         methodHasMorph = Class.forName("morph.common.core.ApiHandler").getDeclaredMethod("hasMorph", new Class[] { String.class, Boolean.TYPE });
/*    */       }
/*    */       catch (ClassNotFoundException ex) {}catch (NoSuchMethodException ex) {}
/*    */     }
/*    */     
/*    */ 
/* 51 */     if (methodHasMorph != null) {
/*    */       try {
/* 53 */         return ((Boolean)methodHasMorph.invoke(null, new Object[] { player.func_70005_c_(), Boolean.valueOf(client) })).booleanValue();
/*    */       }
/*    */       catch (IllegalAccessException ex) {}catch (InvocationTargetException ex) {}
/*    */     }
/*    */     
/* 58 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookMorph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */