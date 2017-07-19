/*    */ package com.emoniph.witchery.crafting;
/*    */ 
/*    */ import com.emoniph.witchery.item.ItemBrewBag;
/*    */ import com.emoniph.witchery.util.Dye;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RecipeShapelessAddColor
/*    */   implements IRecipe
/*    */ {
/*    */   final ItemStack prototype;
/*    */   final ItemStack[] pattern;
/*    */   
/*    */   public RecipeShapelessAddColor(ItemStack resultPoppet, ItemStack... pattern)
/*    */   {
/* 22 */     this.prototype = resultPoppet;
/* 23 */     this.pattern = pattern;
/*    */   }
/*    */   
/*    */   public ItemStack func_77571_b()
/*    */   {
/* 28 */     return null;
/*    */   }
/*    */   
/*    */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*    */   {
/* 33 */     ArrayList<ItemStack> arraylist = new ArrayList(Arrays.asList(this.pattern));
/*    */     
/* 35 */     for (int i = 0; i < 3; i++) {
/* 36 */       for (int j = 0; j < 3; j++) {
/* 37 */         ItemStack itemstack = par1InventoryCrafting.func_70463_b(j, i);
/*    */         
/* 39 */         if (itemstack != null) {
/* 40 */           boolean flag = false;
/* 41 */           Iterator iterator = arraylist.iterator();
/*    */           
/* 43 */           while (iterator.hasNext()) {
/* 44 */             ItemStack itemstack1 = (ItemStack)iterator.next();
/*    */             
/* 46 */             if ((itemstack.func_77973_b() == itemstack1.func_77973_b()) && ((!itemstack.func_77973_b().func_77614_k()) || (itemstack.func_77960_j() == itemstack1.func_77960_j())))
/*    */             {
/* 48 */               flag = true;
/* 49 */               arraylist.remove(itemstack1);
/* 50 */               break;
/*    */             }
/*    */           }
/*    */           
/* 54 */           if (!flag) {
/* 55 */             return false;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 61 */     return arraylist.isEmpty();
/*    */   }
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting inv)
/*    */   {
/* 66 */     ItemStack item = findRecipeItemStack(inv, this.prototype.func_77973_b());
/* 67 */     ItemStack potion = findRecipeItemStack(inv, this.pattern[1].func_77973_b());
/* 68 */     ItemStack result = null;
/* 69 */     if (item != null) {
/* 70 */       result = item.func_77946_l();
/* 71 */       ItemBrewBag.setColor(result, Dye.fromStack(potion));
/*    */     }
/* 73 */     return result;
/*    */   }
/*    */   
/*    */   private ItemStack findRecipeItemStack(InventoryCrafting inv, Item itemToFind) {
/* 77 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 78 */       ItemStack stack = inv.func_70301_a(i);
/* 79 */       if ((stack != null) && (stack.func_77973_b() == itemToFind)) {
/* 80 */         return stack;
/*    */       }
/*    */     }
/* 83 */     return null;
/*    */   }
/*    */   
/*    */   public int func_77570_a()
/*    */   {
/* 88 */     return this.pattern.length;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/RecipeShapelessAddColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */