/*    */ package com.emoniph.witchery.crafting;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RecipeShapelessPoppet implements IRecipe
/*    */ {
/*    */   final ItemStack prototype;
/*    */   final ItemStack[] pattern;
/*    */   
/*    */   public RecipeShapelessPoppet(ItemStack resultPoppet, ItemStack... pattern)
/*    */   {
/* 20 */     this.prototype = resultPoppet;
/* 21 */     this.pattern = pattern;
/*    */   }
/*    */   
/*    */   public ItemStack func_77571_b()
/*    */   {
/* 26 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*    */   {
/* 34 */     ArrayList<ItemStack> arraylist = new ArrayList(java.util.Arrays.asList(this.pattern));
/*    */     
/* 36 */     for (int i = 0; i < 3; i++) {
/* 37 */       for (int j = 0; j < 3; j++) {
/* 38 */         ItemStack itemstack = par1InventoryCrafting.func_70463_b(j, i);
/*    */         
/* 40 */         if (itemstack != null) {
/* 41 */           boolean flag = false;
/* 42 */           Iterator iterator = arraylist.iterator();
/*    */           
/* 44 */           while (iterator.hasNext()) {
/* 45 */             ItemStack itemstack1 = (ItemStack)iterator.next();
/*    */             
/* 47 */             if ((itemstack.func_77973_b() == itemstack1.func_77973_b()) && ((itemstack1.func_77960_j() == 32767) || (itemstack.func_77960_j() == itemstack1.func_77960_j())))
/*    */             {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 53 */               flag = true;
/* 54 */               arraylist.remove(itemstack1);
/* 55 */               break;
/*    */             }
/*    */           }
/*    */           
/*    */ 
/* 60 */           if (!flag) {
/* 61 */             return false;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 67 */     return arraylist.isEmpty();
/*    */   }
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting inv)
/*    */   {
/* 72 */     ItemStack newPoppet = this.prototype.func_77946_l();
/* 73 */     int i = 0; for (int index = 1; i < inv.func_70302_i_(); i++) {
/* 74 */       ItemStack stack = inv.func_70301_a(i);
/* 75 */       if ((stack != null) && (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT)) {
/* 76 */         Witchery.Items.TAGLOCK_KIT.addTagLockToPoppet(stack, newPoppet, Integer.valueOf(index++));
/*    */       }
/*    */     }
/* 79 */     ItemStack recipePoppet = findRecipeItemStack(inv, Witchery.Items.POPPET);
/* 80 */     if (recipePoppet != null) {
/* 81 */       Witchery.Items.POPPET.addDamageToPoppet(recipePoppet, newPoppet);
/*    */     }
/* 83 */     return newPoppet;
/*    */   }
/*    */   
/*    */   private ItemStack findRecipeItemStack(InventoryCrafting inv, Item itemToFind) {
/* 87 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 88 */       ItemStack stack = inv.func_70301_a(i);
/* 89 */       if ((stack != null) && (stack.func_77973_b() == itemToFind)) {
/* 90 */         return stack;
/*    */       }
/*    */     }
/* 93 */     return null;
/*    */   }
/*    */   
/*    */   public int func_77570_a()
/*    */   {
/* 98 */     return this.pattern.length;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/RecipeShapelessPoppet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */