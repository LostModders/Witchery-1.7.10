/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import cpw.mods.fml.client.config.GuiConfig;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraftforge.common.config.ConfigElement;
/*    */ import net.minecraftforge.common.config.Configuration;
/*    */ 
/*    */ public class WitcheryConfigGui extends GuiConfig
/*    */ {
/*    */   public WitcheryConfigGui(GuiScreen parent)
/*    */   {
/* 13 */     super(parent, new ConfigElement(Witchery.config.getCategory("general")).getChildElements(), "witchery", false, false, GuiConfig.getAbridgedConfigPath(Witchery.config.toString()));
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/WitcheryConfigGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */