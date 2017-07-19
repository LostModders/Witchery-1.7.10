/*     */ package com.emoniph.witchery.crafting;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.util.Const;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class KettleRecipes
/*     */ {
/*  23 */   private static final KettleRecipes INSTANCE = new KettleRecipes();
/*     */   
/*     */ 
/*  26 */   public static KettleRecipes instance() { return INSTANCE; }
/*     */   
/*     */   public static class KettleRecipe {
/*     */     public final ItemStack[] inputs;
/*     */     public final ItemStack output;
/*     */     public final float power;
/*     */     final int color;
/*     */     final int hatBonus;
/*     */     final int familiarType;
/*     */     final int dimension;
/*     */     public final boolean inBook;
/*     */     private String unlocalizedName;
/*     */     
/*     */     private KettleRecipe(ItemStack output, int hatBonus, int familiarType, float power, int color, int dimension, boolean inBook, ItemStack... inputs) {
/*  40 */       this.inputs = inputs;
/*  41 */       this.output = output;
/*  42 */       this.power = power;
/*  43 */       this.color = color;
/*  44 */       this.hatBonus = hatBonus;
/*  45 */       this.familiarType = familiarType;
/*  46 */       this.dimension = dimension;
/*  47 */       this.inBook = inBook;
/*     */     }
/*     */     
/*     */     public int getColor() {
/*  51 */       return this.color;
/*     */     }
/*     */     
/*     */     private boolean isMatch(ItemStack[] current, int currentLength, boolean partial, World world) {
/*  55 */       if ((this.dimension != 0) && (this.dimension != world.field_73011_w.field_76574_g)) {
/*  56 */         return false;
/*     */       }
/*     */       
/*  59 */       if ((!partial) && (currentLength != this.inputs.length)) {
/*  60 */         return false;
/*     */       }
/*  62 */       ArrayList<ItemStack> inputsToFind = new ArrayList(Arrays.asList(this.inputs));
/*  63 */       for (int j = 0; j < currentLength; j++) {
/*  64 */         ItemStack itemstack = current[j];
/*  65 */         boolean foundOne = false;
/*  66 */         for (int i = 0; i < inputsToFind.size(); i++) {
/*  67 */           ItemStack input = (ItemStack)inputsToFind.get(i);
/*  68 */           if ((itemstack != null) && (input != null) && (itemstack.func_77969_a(input))) {
/*  69 */             inputsToFind.remove(i);
/*  70 */             foundOne = true;
/*  71 */             break;
/*     */           }
/*     */         }
/*  74 */         if (!foundOne) {
/*  75 */           if (itemstack == null) break;
/*  76 */           return false;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  82 */       return (inputsToFind.size() == 0) || ((partial) && (inputsToFind.size() < this.inputs.length));
/*     */     }
/*     */     
/*     */     private boolean allEmpty(ArrayList<ItemStack> items) {
/*  86 */       for (ItemStack stack : items) {
/*  87 */         if (stack != null) {
/*  88 */           return false;
/*     */         }
/*     */       }
/*  91 */       return true;
/*     */     }
/*     */     
/*     */     public ItemStack getOutput(EntityPlayer player, boolean createCopy) {
/*  95 */       if ((this.hatBonus > 0) && (player != null) && (player.field_71071_by != null) && (player.field_71071_by.func_70440_f(3) != null) && (player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.WITCH_HAT)) {
/*  96 */         ItemStack stack = this.output.func_77946_l();
/*  97 */         stack.field_77994_a += this.hatBonus;
/*  98 */         return stack;
/*     */       }
/* 100 */       return createCopy ? this.output.func_77946_l() : this.output;
/*     */     }
/*     */     
/*     */     public float getRequiredPower()
/*     */     {
/* 105 */       return this.power;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 109 */       StringBuffer sb = new StringBuffer();
/*     */       
/* 111 */       sb.append("§n");
/* 112 */       sb.append(this.output.func_82833_r());
/* 113 */       sb.append("§r");
/* 114 */       sb.append(Const.BOOK_NEWLINE);
/* 115 */       sb.append(Const.BOOK_NEWLINE);
/*     */       
/* 117 */       if ((this.unlocalizedName != null) && (!this.unlocalizedName.isEmpty())) {
/* 118 */         String localizedName = Witchery.resource(this.unlocalizedName);
/* 119 */         if (!localizedName.isEmpty()) {
/* 120 */           sb.append(localizedName);
/* 121 */           sb.append(Const.BOOK_NEWLINE);
/* 122 */           sb.append(Const.BOOK_NEWLINE);
/*     */         }
/*     */       }
/*     */       
/* 126 */       for (ItemStack stack : this.inputs) {
/* 127 */         sb.append("§8>§0 ");
/* 128 */         if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150337_Q)) {
/* 129 */           sb.append(Witchery.resource("witchery.book.mushroomred"));
/* 130 */         } else if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150338_P)) {
/* 131 */           sb.append(Witchery.resource("witchery.book.mushroombrown"));
/* 132 */         } else if (stack.func_77973_b() == Items.field_151068_bn) {
/* 133 */           List list = Items.field_151068_bn.func_77832_l(stack);
/* 134 */           if ((list != null) && (!list.isEmpty())) {
/* 135 */             PotionEffect effect = (PotionEffect)list.get(0);
/* 136 */             String s = stack.func_82833_r();
/* 137 */             if (effect.func_76458_c() > 0) {
/* 138 */               s = s + " " + net.minecraft.util.StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */             }
/*     */             
/* 141 */             if (effect.func_76459_b() > 20) {
/* 142 */               s = s + " (" + net.minecraft.potion.Potion.func_76389_a(effect) + ")";
/*     */             }
/* 144 */             sb.append(s);
/*     */           } else {
/* 146 */             sb.append(stack.func_82833_r());
/*     */           }
/*     */         } else {
/* 149 */           sb.append(stack.func_82833_r());
/*     */         }
/* 151 */         sb.append(Const.BOOK_NEWLINE);
/*     */       }
/*     */       
/* 154 */       if (this.power > 0.0F) {
/* 155 */         sb.append(String.format("\n§8%s§0 %s\n", new Object[] { Witchery.resource("witchery.book.altarpower"), Integer.valueOf(MathHelper.func_76141_d(this.power)) }));
/*     */       }
/*     */       
/* 158 */       return sb.toString();
/*     */     }
/*     */     
/*     */ 
/*     */     public KettleRecipe setUnlocalizedName(String unlocalizedName)
/*     */     {
/* 164 */       this.unlocalizedName = unlocalizedName;
/* 165 */       return this;
/*     */     }
/*     */     
/*     */     public boolean isBrewableBy(EntityPlayer player) {
/* 169 */       if (this.familiarType == 0) {
/* 170 */         return true;
/*     */       }
/*     */       
/* 173 */       if (player == null) {
/* 174 */         return false;
/*     */       }
/*     */       
/* 177 */       int familiarOfPlayer = com.emoniph.witchery.familiar.Familiar.getActiveFamiliarType(player);
/* 178 */       return familiarOfPlayer == this.familiarType;
/*     */     }
/*     */   }
/*     */   
/* 182 */   public final ArrayList<KettleRecipe> recipes = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KettleRecipe addRecipe(ItemStack output, int hatBonus, int familiarType, float powerRequired, int color, int dimension, boolean inBook, ItemStack... inputs)
/*     */   {
/* 189 */     KettleRecipe recipe = new KettleRecipe(output, hatBonus, familiarType, powerRequired, color, dimension, inBook, inputs, null);
/* 190 */     this.recipes.add(recipe);
/* 191 */     return recipe;
/*     */   }
/*     */   
/*     */   public KettleRecipe addRecipe(ItemStack output, int hatBonus, int familiarType, float powerRequired, int color, int dimension, ItemStack... inputs) {
/* 195 */     return addRecipe(output, hatBonus, familiarType, powerRequired, color, dimension, true, inputs);
/*     */   }
/*     */   
/*     */   public KettleRecipe getResult(ItemStack[] inputs, int length, boolean partial, World world) {
/* 199 */     for (KettleRecipe recipe : this.recipes) {
/* 200 */       if (recipe.isMatch(inputs, length, partial, world)) {
/* 201 */         return recipe;
/*     */       }
/*     */     }
/* 204 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHatBonus(ItemStack stack)
/*     */   {
/* 217 */     for (KettleRecipe recipe : this.recipes) {
/* 218 */       if (recipe.output.func_77969_a(stack)) {
/* 219 */         return recipe.hatBonus;
/*     */       }
/*     */     }
/* 222 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isBrewableBy(ItemStack stack, EntityPlayer player) {
/* 226 */     for (KettleRecipe recipe : this.recipes) {
/* 227 */       if (recipe.output.func_77969_a(stack)) {
/* 228 */         return recipe.isBrewableBy(player);
/*     */       }
/*     */     }
/* 231 */     return false;
/*     */   }
/*     */   
/*     */   public KettleRecipe findRecipeFor(ItemStack result) {
/* 235 */     for (KettleRecipe recipe : this.recipes) {
/* 236 */       if (recipe.output.func_77969_a(result)) {
/* 237 */         return recipe;
/*     */       }
/*     */     }
/* 240 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/crafting/KettleRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */