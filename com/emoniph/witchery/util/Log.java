/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import org.apache.logging.log4j.Level;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Log
/*    */ {
/* 13 */   static final Log INSTANCE = new Log();
/*    */   
/*    */   public static Log instance() {
/* 16 */     return INSTANCE;
/*    */   }
/*    */   
/* 19 */   final Logger logger = LogManager.getLogger(getModPrefix() + FMLCommonHandler.instance().getEffectiveSide());
/*    */   
/*    */ 
/*    */ 
/*    */   static String getModPrefix()
/*    */   {
/* 25 */     return "witchery: ";
/*    */   }
/*    */   
/*    */   public void warning(String msg) {
/* 29 */     this.logger.log(Level.WARN, getModPrefix() + msg);
/*    */   }
/*    */   
/*    */   public void warning(Throwable exception, String msg) {
/* 33 */     this.logger.log(Level.WARN, getModPrefix() + msg);
/* 34 */     exception.printStackTrace();
/*    */   }
/*    */   
/*    */   public void debug(String msg) {
/* 38 */     if (Config.instance().isDebugging()) {
/* 39 */       this.logger.log(Level.INFO, getModPrefix() + msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public void traceRite(String msg) {
/* 44 */     if (Config.instance().traceRites()) {
/* 45 */       this.logger.log(Level.INFO, getModPrefix() + msg);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/Log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */