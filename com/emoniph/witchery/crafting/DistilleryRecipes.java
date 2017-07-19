/*     */ package com.emoniph.witchery.crafting;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Const;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.StatCollector;
/*     */ 
/*     */ 
/*     */ public class DistilleryRecipes
/*     */ {
/*  22 */   private static final DistilleryRecipes INSTANCE = new DistilleryRecipes();
/*     */   
/*     */   public static DistilleryRecipes instance() {
/*  25 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public static class DistilleryRecipe {
/*     */     public final ItemStack[] inputs;
/*     */     public final int jars;
/*     */     public final ItemStack[] outputs;
/*     */     
/*     */     private DistilleryRecipe(ItemStack input1, ItemStack input2, int jars, ItemStack output1, ItemStack output2, ItemStack output3, ItemStack output4) {
/*  34 */       this.inputs = new ItemStack[] { input1, input2 };
/*  35 */       this.jars = jars;
/*  36 */       this.outputs = new ItemStack[] { output1, output2, output3, output4 };
/*     */     }
/*     */     
/*     */     private boolean isMatch(ItemStack input1, ItemStack input2, ItemStack jars) {
/*  40 */       return ((this.jars == 0) || ((jars != null) && (jars.field_77994_a >= this.jars))) && (((isMatch(input1, this.inputs[0])) && (isMatch(input2, this.inputs[1]))) || ((isMatch(input1, this.inputs[1])) && (isMatch(input2, this.inputs[0]))));
/*     */     }
/*     */     
/*     */     private boolean isMatch(ItemStack a, ItemStack b)
/*     */     {
/*  45 */       return ((a == null) && (b == null)) || ((a != null) && (b != null) && (a.func_77973_b() == b.func_77973_b()) && ((!a.func_77981_g()) || (a.func_77960_j() == b.func_77960_j())));
/*     */     }
/*     */     
/*     */     public int getJars()
/*     */     {
/*  50 */       return this.jars;
/*     */     }
/*     */     
/*     */     public ItemStack[] getOutputs() {
/*  54 */       return this.outputs;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/*  58 */       StringBuffer sb = new StringBuffer();
/*     */       
/*  60 */       sb.append(Witchery.resource("witchery.book.distillery.items"));
/*  61 */       sb.append(Const.BOOK_NEWLINE);
/*  62 */       sb.append(Const.BOOK_NEWLINE);
/*     */       
/*  64 */       for (ItemStack stack : this.inputs) {
/*  65 */         if (stack != null) {
/*  66 */           sb.append("§8>§0 ");
/*  67 */           if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150337_Q)) {
/*  68 */             sb.append(Witchery.resource("witchery.book.mushroomred"));
/*  69 */           } else if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150338_P)) {
/*  70 */             sb.append(Witchery.resource("witchery.book.mushroombrown"));
/*  71 */           } else if (stack.func_77973_b() == Items.field_151068_bn) {
/*  72 */             List list = Items.field_151068_bn.func_77832_l(stack);
/*  73 */             if ((list != null) && (!list.isEmpty())) {
/*  74 */               PotionEffect effect = (PotionEffect)list.get(0);
/*  75 */               String s = stack.func_82833_r();
/*  76 */               if (effect.func_76458_c() > 0) {
/*  77 */                 s = s + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */               }
/*     */               
/*  80 */               if (effect.func_76459_b() > 20) {
/*  81 */                 s = s + " (" + Potion.func_76389_a(effect) + ")";
/*     */               }
/*  83 */               sb.append(s);
/*     */             } else {
/*  85 */               sb.append(stack.func_82833_r());
/*     */             }
/*     */           } else {
/*  88 */             sb.append(stack.func_82833_r());
/*     */           }
/*  90 */           sb.append(Const.BOOK_NEWLINE);
/*     */         }
/*     */       }
/*     */       
/*  94 */       sb.append(String.format("\n§8%s§0 %d\n", new Object[] { Witchery.resource("witchery.book.distillery.jars"), Integer.valueOf(this.jars) }));
/*     */       
/*  96 */       sb.append(Const.BOOK_NEWLINE);
/*  97 */       sb.append(Witchery.resource("witchery.book.distillery.results"));
/*  98 */       sb.append(Const.BOOK_NEWLINE);
/*  99 */       sb.append(Const.BOOK_NEWLINE);
/*     */       
/* 101 */       for (ItemStack stack : this.outputs) {
/* 102 */         if (stack != null) {
/* 103 */           sb.append("§8>§0 ");
/* 104 */           if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150337_Q)) {
/* 105 */             sb.append(Witchery.resource("witchery.book.mushroomred"));
/* 106 */           } else if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150338_P)) {
/* 107 */             sb.append(Witchery.resource("witchery.book.mushroombrown"));
/* 108 */           } else if (stack.func_77973_b() == Items.field_151068_bn) {
/* 109 */             List list = Items.field_151068_bn.func_77832_l(stack);
/* 110 */             if ((list != null) && (!list.isEmpty())) {
/* 111 */               PotionEffect effect = (PotionEffect)list.get(0);
/* 112 */               String s = stack.func_82833_r();
/* 113 */               if (effect.func_76458_c() > 0) {
/* 114 */                 s = s + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */               }
/*     */               
/* 117 */               if (effect.func_76459_b() > 20) {
/* 118 */                 s = s + " (" + Potion.func_76389_a(effect) + ")";
/*     */               }
/* 120 */               sb.append(s);
/*     */             } else {
/* 122 */               sb.append(stack.func_82833_r());
/*     */             }
/*     */           } else {
/* 125 */             sb.append(stack.func_82833_r());
/*     */           }
/* 127 */           sb.append(Const.BOOK_NEWLINE);
/*     */         }
/*     */       }
/*     */       
/* 131 */       return sb.toString();
/*     */     }
/*     */     
/*     */     public boolean resultsIn(ItemStack result) {
/* 135 */       for (ItemStack stack : this.outputs) {
/* 136 */         if ((stack != null) && (stack.func_77969_a(result))) {
/* 137 */           return true;
/*     */         }
/*     */       }
/* 140 */       return false;
/*     */     }
/*     */     
/*     */     public boolean uses(ItemStack ingredient) {
/* 144 */       for (ItemStack stack : this.inputs) {
/* 145 */         if ((stack != null) && (stack.func_77969_a(ingredient))) {
/* 146 */           return true;
/*     */         }
/*     */       }
/* 149 */       if ((Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(ingredient)) && (this.jars > 0)) {
/* 150 */         return true;
/*     */       }
/* 152 */       return false;
/*     */     }
/*     */   }
/*     */   
/* 156 */   public final ArrayList<DistilleryRecipe> recipes = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DistilleryRecipe addRecipe(ItemStack input1, ItemStack input2, int jars, ItemStack output1, ItemStack output2, ItemStack output3, ItemStack output4)
/*     */   {
/* 163 */     DistilleryRecipe recipe = new DistilleryRecipe(input1, input2, jars, output1, output2, output3, output4, null);
/* 164 */     this.recipes.add(recipe);
/* 165 */     return recipe;
/*     */   }
/*     */   
/*     */   public DistilleryRecipe getDistillingResult(ItemStack input1, ItemStack intput2, ItemStack jars) {
/* 169 */     for (DistilleryRecipe recipe : this.recipes) {
/* 170 */       if (recipe.isMatch(input1, intput2, jars)) {
/* 171 */         return recipe;
/*     */       }
/*     */     }
/* 174 */     return null;
/*     */   }
/*     */   
/*     */   public DistilleryRecipe findRecipeFor(ItemStack result) {
/* 178 */     for (DistilleryRecipe recipe : this.recipes) {
/* 179 */       if (recipe.resultsIn(result)) {
/* 180 */         return recipe;
/*     */       }
/*     */     }
/* 183 */     return null;
/*     */   }
/*     */   
/*     */   public DistilleryRecipe findRecipeUsing(ItemStack ingredient) {
/* 187 */     for (DistilleryRecipe recipe : this.recipes) {
/* 188 */       if (recipe.uses(ingredient)) {
/* 189 */         return recipe;
/*     */       }
/*     */     }
/* 192 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/DistilleryRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */