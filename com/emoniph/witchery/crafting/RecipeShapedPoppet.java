/*    */ package com.emoniph.witchery.crafting;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RecipeShapedPoppet implements net.minecraft.item.crafting.IRecipe
/*    */ {
/*    */   final ItemStack prototype;
/*    */   final Item[] pattern;
/*    */   
/*    */   public RecipeShapedPoppet(ItemStack resultPoppet, Item[] pattern)
/*    */   {
/* 17 */     this.prototype = resultPoppet;
/* 18 */     this.pattern = pattern;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_77569_a(InventoryCrafting inv, World world)
/*    */   {
/* 24 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 25 */       ItemStack stack = inv.func_70301_a(i);
/* 26 */       if ((this.pattern[i] != null) || (stack != null))
/*    */       {
/* 28 */         if ((stack == null) || (this.pattern[i] == null))
/* 29 */           return false;
/* 30 */         if (stack.func_77973_b() != this.pattern[i])
/* 31 */           return false;
/* 32 */         if ((stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) && 
/* 33 */           (stack.func_77960_j() != 1))
/*    */         {
/*    */ 
/*    */ 
/* 37 */           return false;
/*    */         }
/*    */       }
/*    */     }
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting inv)
/*    */   {
/* 46 */     ItemStack stackPoppet = this.prototype.func_77946_l();
/* 47 */     ItemStack stackTaglockKit = findTaglockKit(inv);
/* 48 */     if (stackTaglockKit != null) {
/* 49 */       Witchery.Items.TAGLOCK_KIT.addTagLockToPoppet(stackTaglockKit, stackPoppet, Integer.valueOf(1));
/*    */     }
/* 51 */     return stackPoppet;
/*    */   }
/*    */   
/*    */   private ItemStack findTaglockKit(InventoryCrafting inv) {
/* 55 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 56 */       ItemStack stack = inv.func_70301_a(1);
/* 57 */       if (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) {
/* 58 */         return stack;
/*    */       }
/*    */     }
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   public int func_77570_a()
/*    */   {
/* 66 */     return this.pattern.length;
/*    */   }
/*    */   
/*    */   public ItemStack func_77571_b()
/*    */   {
/* 71 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/RecipeShapedPoppet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */