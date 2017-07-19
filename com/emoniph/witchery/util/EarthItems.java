/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ 
/*    */ 
/*    */ public class EarthItems
/*    */ {
/* 15 */   private static final EarthItems INSTANCE = new EarthItems();
/*    */   
/* 17 */   public static EarthItems instance() { return INSTANCE; }
/*    */   
/*    */ 
/*    */   private final List<Item> itemList;
/*    */   private EarthItems()
/*    */   {
/* 23 */     Item[] list = { Items.field_151040_l, Items.field_151010_B, Items.field_151036_c, Items.field_151006_E, Items.field_151035_b, Items.field_151005_D, Items.field_151013_M, Items.field_151019_K, Items.field_151037_a, Items.field_151011_C, Items.field_151043_k, Items.field_151074_bl, Items.field_151042_j, Items.field_151028_Y, Items.field_151030_Z, Items.field_151165_aa, Items.field_151167_ab, Items.field_151169_ag, Items.field_151149_ai, Items.field_151171_ah, Items.field_151151_aj, Items.field_151020_U, Items.field_151022_W, Items.field_151023_V, Items.field_151029_X };
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 51 */     this.itemList = Arrays.asList(list);
/*    */   }
/*    */   
/*    */   public boolean isMatch(ItemStack itemstack) {
/* 55 */     if (itemstack == null) {
/* 56 */       return false;
/*    */     }
/* 58 */     return this.itemList.contains(itemstack.func_77973_b());
/*    */   }
/*    */   
/*    */   public boolean isOre(Block block) {
/* 62 */     return (block == Blocks.field_150366_p) || (block == Blocks.field_150352_o);
/*    */   }
/*    */   
/*    */   public Item oreToIngot(Block block) {
/* 66 */     if (block == Blocks.field_150366_p)
/* 67 */       return Items.field_151042_j;
/* 68 */     if (block == Blocks.field_150352_o) {
/* 69 */       return Items.field_151043_k;
/*    */     }
/* 71 */     int oreID = OreDictionary.getOreID(new ItemStack(block));
/* 72 */     if (oreID != -1) {
/* 73 */       String oreName = OreDictionary.getOreName(oreID);
/* 74 */       if (oreName.startsWith("ore")) {
/* 75 */         String ingotName = oreName.replace("ore", "ingot");
/*    */         
/* 77 */         String[] oreNames = OreDictionary.getOreNames();
/* 78 */         if (Arrays.asList(oreNames).contains(ingotName)) {
/* 79 */           int ingotID = OreDictionary.getOreID(ingotName);
/* 80 */           List<ItemStack> ingotStacks = OreDictionary.getOres(Integer.valueOf(ingotID));
/* 81 */           if (!ingotStacks.isEmpty()) {
/* 82 */             return ((ItemStack)ingotStacks.get(0)).func_77973_b();
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 88 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/EarthItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */