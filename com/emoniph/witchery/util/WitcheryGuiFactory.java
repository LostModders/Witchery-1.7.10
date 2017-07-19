/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import cpw.mods.fml.client.IModGuiFactory;
/*    */ import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
/*    */ import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ public class WitcheryGuiFactory
/*    */   implements IModGuiFactory
/*    */ {
/*    */   public void initialize(Minecraft minecraftInstance) {}
/*    */   
/*    */   public Class<? extends GuiScreen> mainConfigGuiClass()
/*    */   {
/* 17 */     return WitcheryConfigGui.class;
/*    */   }
/*    */   
/*    */   public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories()
/*    */   {
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element)
/*    */   {
/* 27 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/WitcheryGuiFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */