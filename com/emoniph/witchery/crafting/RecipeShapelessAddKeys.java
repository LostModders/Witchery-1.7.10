/*     */ package com.emoniph.witchery.crafting;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RecipeShapelessAddKeys implements IRecipe
/*     */ {
/*     */   final ItemStack prototype;
/*     */   final ItemStack[] pattern;
/*     */   
/*     */   public RecipeShapelessAddKeys(ItemStack result, ItemStack... pattern)
/*     */   {
/*  24 */     this.prototype = result;
/*  25 */     this.pattern = pattern;
/*     */   }
/*     */   
/*     */   public ItemStack func_77571_b()
/*     */   {
/*  30 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
/*     */   {
/*  35 */     ArrayList<ItemStack> arraylist = new ArrayList(Arrays.asList(this.pattern));
/*     */     
/*  37 */     for (int i = 0; i < 3; i++) {
/*  38 */       for (int j = 0; j < 3; j++) {
/*  39 */         ItemStack itemstack = par1InventoryCrafting.func_70463_b(j, i);
/*     */         
/*  41 */         if (itemstack != null) {
/*  42 */           boolean flag = false;
/*  43 */           Iterator iterator = arraylist.iterator();
/*     */           
/*  45 */           while (iterator.hasNext()) {
/*  46 */             ItemStack itemstack1 = (ItemStack)iterator.next();
/*     */             
/*  48 */             if ((itemstack.func_77973_b() == itemstack1.func_77973_b()) && ((!itemstack.func_77973_b().func_77614_k()) || (itemstack.func_77960_j() == itemstack1.func_77960_j())))
/*     */             {
/*  50 */               flag = true;
/*  51 */               arraylist.remove(itemstack1);
/*  52 */               break;
/*     */             }
/*     */           }
/*     */           
/*  56 */           if (!flag) {
/*  57 */             return false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  63 */     return arraylist.isEmpty();
/*     */   }
/*     */   
/*     */   public ItemStack func_77572_b(InventoryCrafting inv)
/*     */   {
/*  68 */     ItemStack ring = findRecipeItemStack(inv, this.prototype.func_77973_b(), this.prototype.func_77960_j());
/*  69 */     ItemStack result = ring != null ? ring.func_77946_l() : this.prototype.func_77946_l();
/*     */     
/*  71 */     for (int j = 0; j < inv.func_70302_i_(); j++) {
/*  72 */       ItemStack key = inv.func_70301_a(j);
/*  73 */       if ((key != null) && (Witchery.Items.GENERIC.itemDoorKey.isMatch(key)) && (key.func_77942_o())) {
/*  74 */         if (!result.func_77942_o()) {
/*  75 */           result.func_77982_d(new NBTTagCompound());
/*     */         }
/*     */         
/*  78 */         NBTTagCompound sourceTag = key.func_77978_p();
/*  79 */         int sourceX = sourceTag.func_74762_e("doorX");
/*  80 */         int sourceY = sourceTag.func_74762_e("doorY");
/*  81 */         int sourceZ = sourceTag.func_74762_e("doorZ");
/*  82 */         boolean sourceHasD = (sourceTag.func_74764_b("doorD")) && (sourceTag.func_74764_b("doorDN"));
/*     */         
/*  84 */         NBTTagCompound nbtTag = result.func_77978_p();
/*  85 */         if (!nbtTag.func_74764_b("doorKeys")) {
/*  86 */           nbtTag.func_74782_a("doorKeys", new NBTTagList());
/*     */         }
/*  88 */         NBTTagList keyList = nbtTag.func_150295_c("doorKeys", 10);
/*  89 */         for (int i = 0; i < keyList.func_74745_c(); i++) {
/*  90 */           NBTTagCompound keyTag = keyList.func_150305_b(i);
/*  91 */           if ((keyTag != null) && (keyTag.func_74764_b("doorX")) && (keyTag.func_74764_b("doorY")) && (keyTag.func_74764_b("doorZ"))) {
/*  92 */             int doorX = keyTag.func_74762_e("doorX");
/*  93 */             int doorY = keyTag.func_74762_e("doorY");
/*  94 */             int doorZ = keyTag.func_74762_e("doorZ");
/*  95 */             boolean doorHasD = (keyTag.func_74764_b("doorD")) && (keyTag.func_74764_b("doorDN"));
/*  96 */             if ((doorX == sourceX) && (doorY == sourceY) && (doorZ == sourceZ) && (sourceHasD == doorHasD) && ((!sourceHasD) || (sourceTag.func_74762_e("doorD") == keyTag.func_74762_e("doorD"))))
/*     */             {
/*  98 */               return result;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 104 */         NBTTagCompound nbtNewKey = new NBTTagCompound();
/* 105 */         nbtNewKey.func_74768_a("doorX", sourceX);
/* 106 */         nbtNewKey.func_74768_a("doorY", sourceY);
/* 107 */         nbtNewKey.func_74768_a("doorZ", sourceZ);
/* 108 */         if (sourceHasD) {
/* 109 */           nbtNewKey.func_74768_a("doorD", sourceTag.func_74762_e("doorD"));
/* 110 */           nbtNewKey.func_74778_a("doorDN", sourceTag.func_74779_i("doorDN"));
/*     */         }
/* 112 */         keyList.func_74742_a(nbtNewKey);
/*     */       }
/*     */     }
/* 115 */     return result;
/*     */   }
/*     */   
/*     */   private ItemStack findRecipeItemStack(InventoryCrafting inv, Item itemToFind, int meta) {
/* 119 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/* 120 */       ItemStack stack = inv.func_70301_a(i);
/* 121 */       if ((stack != null) && (stack.func_77973_b() == itemToFind) && (stack.func_77960_j() == meta)) {
/* 122 */         return stack;
/*     */       }
/*     */     }
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   public int func_77570_a()
/*     */   {
/* 130 */     return this.pattern.length;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/RecipeShapelessAddKeys.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */