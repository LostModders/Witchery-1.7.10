/*    */ package com.emoniph.witchery.crafting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RecipeShapelessRepair implements IRecipe
/*    */ {
/*    */   final ItemStack prototype;
/*    */   final ItemStack[] pattern;
/*    */   
/*    */   public RecipeShapelessRepair(ItemStack resultPoppet, ItemStack... pattern)
/*    */   {
/* 19 */     this.prototype = resultPoppet;
/* 20 */     this.pattern = pattern;
/*    */   }
/*    */   
/*    */   public ItemStack func_77571_b()
/*    */   {
/* 25 */     return null;
/*    */   }
/*    */   
/*    */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*    */   {
/* 30 */     ArrayList<ItemStack> arraylist = new ArrayList(Arrays.asList(this.pattern));
/*    */     
/* 32 */     for (int i = 0; i < 3; i++) {
/* 33 */       for (int j = 0; j < 3; j++) {
/* 34 */         ItemStack itemstack = par1InventoryCrafting.func_70463_b(j, i);
/*    */         
/* 36 */         if (itemstack != null) {
/* 37 */           boolean flag = false;
/* 38 */           Iterator iterator = arraylist.iterator();
/*    */           
/* 40 */           while (iterator.hasNext()) {
/* 41 */             ItemStack itemstack1 = (ItemStack)iterator.next();
/*    */             
/* 43 */             if ((itemstack.func_77973_b() == itemstack1.func_77973_b()) && ((!itemstack.func_77973_b().func_77614_k()) || (itemstack.func_77960_j() == itemstack1.func_77960_j())))
/*    */             {
/* 45 */               flag = true;
/* 46 */               arraylist.remove(itemstack1);
/* 47 */               break;
/*    */             }
/*    */           }
/*    */           
/* 51 */           if (!flag) {
/* 52 */             return false;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 58 */     return arraylist.isEmpty();
/*    */   }
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting inv)
/*    */   {
/* 63 */     ItemStack item = findRecipeItemStack(inv, this.prototype.func_77973_b());
/* 64 */     ItemStack result = null;
/* 65 */     if (item != null) {
/* 66 */       result = item.func_77946_l();
/* 67 */       result.func_77964_b(0);
/*    */     }
/* 69 */     return result;
/*    */   }
/*    */   
/*    */   private ItemStack findRecipeItemStack(InventoryCrafting inv, Item itemToFind) {
/* 73 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 74 */       ItemStack stack = inv.func_70301_a(i);
/* 75 */       if ((stack != null) && (stack.func_77973_b() == itemToFind)) {
/* 76 */         return stack;
/*    */       }
/*    */     }
/* 79 */     return null;
/*    */   }
/*    */   
/*    */   public int func_77570_a()
/*    */   {
/* 84 */     return this.pattern.length;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/RecipeShapelessRepair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */