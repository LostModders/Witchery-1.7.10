/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import com.emoniph.witchery.brewing.BrewItemKey;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public enum Dye
/*    */ {
/*  9 */   INK_SAC(0, 1118481, "black"), 
/* 10 */   ROSE_RED(1, 12464176, "red"), 
/* 11 */   CACTUS_GREEN(2, 5732898, "green"), 
/* 12 */   COCOA_BEANS(3, 5057301, "brown"), 
/* 13 */   LAPIS_LAZULI(4, 2247599, "blue"), 
/* 14 */   PURPLE_DYE(5, 8532146, "purple"), 
/* 15 */   CYAN_DYE(6, 3968688, "cyan"), 
/* 16 */   LIGHT_GRAY_DYE(7, 11513789, "lightgray"), 
/* 17 */   GRAY_DYE(8, 7763574, "gray"), 
/* 18 */   PINK_DYE(9, 15574987, "pink"), 
/* 19 */   LIME_DYE(10, 8639516, "lime"), 
/* 20 */   DANDELION_YELLOW(11, 15197994, "yellow"), 
/* 21 */   LIGHT_BLUE_DYE(12, 12179199, "lightblue"), 
/* 22 */   MAGENTA_DYE(13, 14383829, "magenta"), 
/* 23 */   ORANGE_DYE(14, 15113780, "orange"), 
/* 24 */   BONE_MEAL(15, 16777215, "white");
/*    */   
/*    */ 
/*    */   public final int damageValue;
/*    */   public final int rgb;
/*    */   public final String unlocalizedName;
/* 30 */   public static final Dye[] DYES = { INK_SAC, ROSE_RED, CACTUS_GREEN, COCOA_BEANS, LAPIS_LAZULI, PURPLE_DYE, CYAN_DYE, LIGHT_GRAY_DYE, GRAY_DYE, PINK_DYE, LIME_DYE, DANDELION_YELLOW, LIGHT_BLUE_DYE, MAGENTA_DYE, ORANGE_DYE, BONE_MEAL };
/*    */   
/*    */   private Dye(int damageValue, int rgb, String unlocalizedName)
/*    */   {
/* 34 */     this.damageValue = damageValue;
/* 35 */     this.rgb = rgb;
/* 36 */     this.unlocalizedName = unlocalizedName;
/*    */   }
/*    */   
/*    */   public ItemStack createStack() {
/* 40 */     return createStack(1);
/*    */   }
/*    */   
/*    */   public ItemStack createStack(int quantity) {
/* 44 */     return new ItemStack(Items.field_151100_aR, quantity, this.damageValue);
/*    */   }
/*    */   
/*    */   public static Dye fromStack(ItemStack potion) {
/* 48 */     if ((potion.func_77973_b() == Items.field_151100_aR) && (potion.func_77960_j() >= 0) && (potion.func_77960_j() < DYES.length)) {
/* 49 */       return DYES[potion.func_77960_j()];
/*    */     }
/* 51 */     return BONE_MEAL;
/*    */   }
/*    */   
/*    */   public BrewItemKey getBrewItemKey() {
/* 55 */     return new BrewItemKey(Items.field_151100_aR, this.damageValue);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/Dye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */