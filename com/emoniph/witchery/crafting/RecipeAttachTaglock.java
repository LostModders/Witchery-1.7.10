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
/*    */ public class RecipeAttachTaglock implements IRecipe
/*    */ {
/*    */   final ItemStack prototype;
/*    */   final ItemStack[] pattern;
/*    */   
/*    */   public RecipeAttachTaglock(ItemStack resultPoppet, ItemStack... pattern)
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
/*    */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*    */   {
/* 31 */     ArrayList<ItemStack> arraylist = new ArrayList(java.util.Arrays.asList(this.pattern));
/*    */     
/* 33 */     for (int i = 0; i < 3; i++) {
/* 34 */       for (int j = 0; j < 3; j++) {
/* 35 */         ItemStack itemstack = par1InventoryCrafting.func_70463_b(j, i);
/*    */         
/* 37 */         if (itemstack != null) {
/* 38 */           boolean flag = false;
/* 39 */           Iterator iterator = arraylist.iterator();
/*    */           
/* 41 */           while (iterator.hasNext()) {
/* 42 */             ItemStack itemstack1 = (ItemStack)iterator.next();
/*    */             
/* 44 */             if ((itemstack.func_77973_b() == itemstack1.func_77973_b()) && ((itemstack1.func_77960_j() == 32767) || (itemstack.func_77960_j() == itemstack1.func_77960_j())))
/*    */             {
/* 46 */               flag = true;
/* 47 */               arraylist.remove(itemstack1);
/* 48 */               break;
/*    */             }
/*    */           }
/*    */           
/* 52 */           if (!flag) {
/* 53 */             return false;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 59 */     return arraylist.isEmpty();
/*    */   }
/*    */   
/*    */   public ItemStack func_77572_b(InventoryCrafting inv)
/*    */   {
/* 64 */     ItemStack newPoppet = this.prototype.func_77946_l();
/* 65 */     int i = 0; for (int index = 1; i < inv.func_70302_i_(); i++) {
/* 66 */       ItemStack stack = inv.func_70301_a(i);
/* 67 */       if ((stack != null) && (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT)) {
/* 68 */         Witchery.Items.TAGLOCK_KIT.addTagLockToPoppet(stack, newPoppet, Integer.valueOf(index++));
/*    */       }
/*    */     }
/* 71 */     return newPoppet;
/*    */   }
/*    */   
/*    */   private ItemStack findRecipeItemStack(InventoryCrafting inv, Item itemToFind) {
/* 75 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 76 */       ItemStack stack = inv.func_70301_a(i);
/* 77 */       if ((stack != null) && (stack.func_77973_b() == itemToFind)) {
/* 78 */         return stack;
/*    */       }
/*    */     }
/* 81 */     return null;
/*    */   }
/*    */   
/*    */   public int func_77570_a()
/*    */   {
/* 86 */     return this.pattern.length;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/RecipeAttachTaglock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */