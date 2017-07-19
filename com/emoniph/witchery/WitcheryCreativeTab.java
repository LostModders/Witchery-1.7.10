/*    */ package com.emoniph.witchery;
/*    */ 
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public final class WitcheryCreativeTab extends CreativeTabs
/*    */ {
/*  8 */   public static final WitcheryCreativeTab INSTANCE = new WitcheryCreativeTab();
/*    */   
/*    */   private WitcheryCreativeTab() {
/* 11 */     super("tabWitchery");
/*    */   }
/*    */   
/*    */   public net.minecraft.item.Item func_78016_d()
/*    */   {
/* 16 */     return Witchery.Items.POPPET;
/*    */   }
/*    */   
/*    */   public net.minecraft.item.ItemStack func_151244_d()
/*    */   {
/* 21 */     return Witchery.Items.GENERIC.itemBroomEnchanted.createStack();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/WitcheryCreativeTab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */