/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public abstract class MultiItemBlock extends ItemBlock
/*    */ {
/*  8 */   private String[] names = null;
/*    */   
/*    */   public MultiItemBlock(net.minecraft.block.Block block) {
/* 11 */     super(block);
/* 12 */     func_77656_e(0);
/* 13 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */   private String[] internalGetNames() {
/* 17 */     if (this.names == null) {
/* 18 */       this.names = getNames();
/*    */     }
/* 20 */     return this.names;
/*    */   }
/*    */   
/*    */   protected abstract String[] getNames();
/*    */   
/*    */   public int func_77647_b(int par1) {
/* 26 */     return par1;
/*    */   }
/*    */   
/*    */   public String func_77667_c(ItemStack par1ItemStack) {
/* 30 */     int i = par1ItemStack.func_77960_j();
/* 31 */     String[] names = internalGetNames();
/* 32 */     if ((i < 0) || (i >= names.length)) {
/* 33 */       i = 0;
/*    */     }
/*    */     
/* 36 */     return super.func_77658_a() + "." + names[i];
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/MultiItemBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */