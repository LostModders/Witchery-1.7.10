/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import com.emoniph.witchery.entity.EntityFamiliar;
/*     */ import com.emoniph.witchery.entity.EntityImp;
/*     */ import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
/*     */ import com.emoniph.witchery.util.Dye;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IMerchant;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.passive.EntityAmbientCreature;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityMooshroom;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.passive.EntitySquid;
/*     */ import net.minecraft.entity.passive.EntityWaterMob;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.village.MerchantRecipe;
/*     */ import net.minecraft.village.MerchantRecipeList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class ItemPolynesiaCharm
/*     */   extends ItemBase
/*     */ {
/*     */   private final boolean charmDemons;
/*     */   
/*     */   public ItemPolynesiaCharm(boolean charmDemons)
/*     */   {
/*  55 */     this.charmDemons = charmDemons;
/*  56 */     func_77656_e(50);
/*  57 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*     */   {
/*  62 */     if (!world.field_72995_K) {
/*  63 */       boolean success = false;
/*  64 */       double MAX_TARGET_RANGE = 5.0D;
/*  65 */       MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 5.0D);
/*  66 */       if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLiving))) {
/*  67 */         EntityLiving living = (EntityLiving)mop.field_72308_g;
/*  68 */         if ((((living instanceof EntityAnimal)) || ((living instanceof EntityAmbientCreature)) || ((living instanceof EntitySpider)) || ((living instanceof EntityWaterMob)) || (((living instanceof EntityCreeper)) && (Witchery.Items.WITCH_ROBES.isRobeWorn(player))) || ((living.func_70662_br()) && (Witchery.Items.NECROMANCERS_ROBES.isRobeWorn(player)))) && (!(living instanceof EntityFamiliar)) && (!(living instanceof EntityCovenWitch)) && (!(living instanceof EntityImp)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */           if ((living.func_70089_S()) && (!living.func_70631_g_()) && (living.func_70638_az() == null) && (
/*  75 */             (!(living instanceof EntityBat)) || (canBatDrop(living)))) {
/*  76 */             AnimalMerchant merchant = new AnimalMerchant(living);
/*  77 */             merchant.playIntro(player);
/*  78 */             merchant.func_70932_a_(player);
/*  79 */             String animalName = living.func_70005_c_();
/*  80 */             player.func_71030_a(merchant, animalName.substring(0, Math.min(30, animalName.length())));
/*  81 */             success = true;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  87 */       if ((!success) || ((mop != null) && ((mop.field_72308_g instanceof EntityDemon)))) {
/*  88 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */       } else {
/*  90 */         itemstack.func_77972_a(1, player);
/*  91 */         if (itemstack.field_77994_a <= 0) {
/*  92 */           player.func_71028_bD();
/*     */         }
/*     */       }
/*     */     }
/*  96 */     return super.func_77659_a(itemstack, world, player);
/*     */   }
/*     */   
/*     */   private boolean canBatDrop(EntityLiving living) {
/* 100 */     NBTTagCompound nbtBat = living.getEntityData();
/* 101 */     return (nbtBat == null) || (!nbtBat.func_74764_b("WITCNoDrops")) || (!nbtBat.func_74767_n("WITCNoDrops"));
/*     */   }
/*     */   
/*     */   public boolean canCharmDemons() {
/* 105 */     return this.charmDemons;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 111 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 116 */     String localText = Witchery.resource(func_77658_a() + ".tip");
/* 117 */     if (localText != null) {
/* 118 */       for (String s : localText.split("\n")) {
/* 119 */         if (!s.isEmpty()) {
/* 120 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean hasStockInventory(EntityLiving entity) {
/* 127 */     if (entity == null) {
/* 128 */       return false;
/*     */     }
/* 130 */     NBTTagCompound nbtTag = entity.getEntityData();
/* 131 */     boolean hasKey = (nbtTag != null) && (nbtTag.func_74764_b("WitcheryShopStock"));
/* 132 */     return hasKey;
/*     */   }
/*     */   
/*     */   public static void setEmptyStockInventory(World world, EntityLiving entity) {
/* 136 */     if ((entity != null) && (!world.field_72995_K)) {
/* 137 */       NBTTagCompound nbtTag = entity.getEntityData();
/* 138 */       nbtTag.func_74782_a("WitcheryShopStock", new NBTTagCompound());
/*     */     }
/*     */   }
/*     */   
/*     */   private static class AnimalMerchant implements IMerchant {
/*     */     private final EntityLiving animal;
/*     */     private EntityPlayer customer;
/*     */     static final String STOCKS_KEY = "WitcheryShopStock";
/*     */     
/*     */     public AnimalMerchant(EntityLiving animal) {
/* 148 */       this.animal = animal;
/*     */     }
/*     */     
/*     */     public void playIntro(EntityPlayer player) {
/* 152 */       playGreeting(this.animal, player);
/*     */     }
/*     */     
/*     */     public void func_70932_a_(EntityPlayer player)
/*     */     {
/* 157 */       this.customer = player;
/*     */     }
/*     */     
/*     */     public EntityPlayer func_70931_l_()
/*     */     {
/* 162 */       return this.customer;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 167 */     private MerchantRecipeList currentList = null;
/*     */     
/*     */     public MerchantRecipeList func_70934_b(EntityPlayer player)
/*     */     {
/* 171 */       NBTTagCompound nbtTag = this.animal.getEntityData();
/* 172 */       if (this.currentList != null)
/* 173 */         return this.currentList;
/* 174 */       if (nbtTag.func_74764_b("WitcheryShopStock")) {
/* 175 */         NBTTagCompound nbtTagStocks = nbtTag.func_74775_l("WitcheryShopStock");
/* 176 */         if (nbtTagStocks.func_82582_d()) {
/* 177 */           this.currentList = new MerchantRecipeList();
/*     */         } else {
/* 179 */           this.currentList = new MerchantRecipeList(nbtTagStocks);
/*     */         }
/* 181 */         return this.currentList;
/*     */       }
/* 183 */       this.currentList = new MerchantRecipeList();
/* 184 */       populateList(this.animal, this.currentList);
/* 185 */       nbtTag.func_74782_a("WitcheryShopStock", this.currentList.func_77202_a());
/* 186 */       return this.currentList;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_70933_a(MerchantRecipe recipe)
/*     */     {
/* 192 */       if ((this.animal != null) && (this.animal.func_70089_S()) && (!this.animal.field_70170_p.field_72995_K)) {
/* 193 */         recipe.func_77399_f();
/* 194 */         if (this.currentList != null) {
/* 195 */           NBTTagCompound nbtTag = this.animal.getEntityData();
/* 196 */           nbtTag.func_74782_a("WitcheryShopStock", this.currentList.func_77202_a());
/*     */         }
/*     */       }
/* 199 */       this.animal.func_70642_aH();
/*     */     }
/*     */     
/*     */     public void func_110297_a_(ItemStack itemstack)
/*     */     {
/* 204 */       this.animal.func_70642_aH();
/*     */     }
/*     */     
/*     */ 
/*     */     @SideOnly(Side.CLIENT)
/*     */     public void func_70930_a(MerchantRecipeList list) {}
/*     */     
/*     */ 
/*     */     private static void populateList(EntityLiving animal, MerchantRecipeList finalList)
/*     */     {
/* 214 */       Random r = animal.field_70170_p.field_73012_v;
/*     */       
/* 216 */       MerchantRecipeList list = new MerchantRecipeList();
/*     */       
/* 218 */       ItemStack[] stacks = { Witchery.Items.GENERIC.itemMandrakeRoot.createStack(3), Witchery.Items.GENERIC.itemBelladonnaFlower.createStack(3), Witchery.Items.GENERIC.itemArtichoke.createStack(3), new ItemStack(Blocks.field_150345_g, 4, 0), new ItemStack(Blocks.field_150345_g, 4, 1), new ItemStack(Blocks.field_150345_g, 4, 2), new ItemStack(Blocks.field_150345_g, 4, 3), new ItemStack(Blocks.field_150436_aH, 2), new ItemStack(Blocks.field_150434_aF, 2), new ItemStack(Items.field_151074_bl, 5), new ItemStack(Items.field_151042_j, 2), new ItemStack(Items.field_151103_aS, 4), new ItemStack(Items.field_151145_ak, 5), Witchery.Items.GENERIC.itemDogTongue.createStack(2), new ItemStack(Items.field_151174_bG, 5), new ItemStack(Items.field_151170_bI, 2), new ItemStack(Items.field_151172_bF, 5), new ItemStack(Items.field_151119_aD, 10) };
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 225 */       ArrayList<ItemStack> currencies = new ArrayList();
/* 226 */       ArrayList<ItemStack> items = new ArrayList();
/*     */       
/* 228 */       items.add(stacks[r.nextInt(stacks.length)]);
/* 229 */       if (animal.field_70170_p.field_73012_v.nextDouble() < 0.03D) {
/* 230 */         items.add(Witchery.Items.GENERIC.itemSeedsTreefyd.createStack());
/*     */       }
/*     */       
/* 233 */       if ((animal instanceof EntityPig)) {
/* 234 */         currencies.add(new ItemStack(Items.field_151172_bF));
/* 235 */         currencies.add(new ItemStack(Items.field_151034_e));
/* 236 */         currencies.add(new ItemStack(Items.field_151174_bG));
/*     */         
/* 238 */         items.add(new ItemStack(Blocks.field_150337_Q, 5));
/* 239 */         items.add(new ItemStack(Blocks.field_150338_P, 5));
/* 240 */         if (r.nextDouble() < 0.02D) {
/* 241 */           items.add(new ItemStack(Items.field_151166_bC, 1));
/*     */         }
/* 243 */         if (r.nextDouble() < 0.01D) {
/* 244 */           items.add(new ItemStack(Items.field_151045_i, 1));
/*     */         }
/*     */       }
/* 247 */       else if ((animal instanceof EntityHorse)) {
/* 248 */         currencies.add(new ItemStack(Items.field_151172_bF));
/* 249 */         currencies.add(new ItemStack(Items.field_151034_e));
/* 250 */         currencies.add(new ItemStack(Items.field_151015_O));
/*     */         
/* 252 */         if (r.nextDouble() < 0.01D) {
/* 253 */           items.add(new ItemStack(Items.field_151141_av, 1));
/*     */         }
/* 255 */       } else if ((animal instanceof EntityWolf)) {
/* 256 */         currencies.add(new ItemStack(Items.field_151082_bd));
/* 257 */         currencies.add(new ItemStack(Items.field_151147_al));
/* 258 */         currencies.add(new ItemStack(Items.field_151076_bf));
/*     */         
/* 260 */         items.add(new ItemStack(Items.field_151103_aS, 5));
/*     */         
/* 262 */         if (r.nextDouble() < 0.02D) {
/* 263 */           items.add(new ItemStack(Items.field_151166_bC, 1));
/*     */         }
/* 265 */         if (r.nextDouble() < 0.01D) {
/* 266 */           items.add(new ItemStack(Items.field_151045_i, 1));
/*     */         }
/* 268 */       } else if ((animal instanceof EntityOcelot)) {
/* 269 */         currencies.add(new ItemStack(Items.field_151117_aB));
/* 270 */         currencies.add(new ItemStack(Items.field_151115_aP));
/* 271 */       } else if ((animal instanceof EntityCow)) {
/* 272 */         currencies.add(new ItemStack(Items.field_151015_O));
/* 273 */       } else if ((animal instanceof EntityChicken)) {
/* 274 */         currencies.add(new ItemStack(Items.field_151014_N));
/*     */         
/* 276 */         items.add(new ItemStack(Items.field_151008_G, 10));
/* 277 */         items.add(new ItemStack(Items.field_151110_aK, 5));
/*     */       }
/* 279 */       else if ((animal instanceof EntityMooshroom)) {
/* 280 */         currencies.add(new ItemStack(Blocks.field_150337_Q));
/* 281 */         currencies.add(new ItemStack(Blocks.field_150338_P));
/* 282 */       } else if ((animal instanceof EntitySheep)) {
/* 283 */         currencies.add(new ItemStack(Items.field_151015_O));
/* 284 */       } else if ((animal instanceof EntitySquid)) {
/* 285 */         currencies.add(new ItemStack(Items.field_151115_aP));
/* 286 */         items.add(Dye.INK_SAC.createStack(10));
/* 287 */       } else if ((animal instanceof EntityBat)) {
/* 288 */         currencies.add(new ItemStack(Items.field_151014_N));
/* 289 */         currencies.add(new ItemStack(Items.field_151015_O));
/* 290 */         currencies.add(new ItemStack(Items.field_151082_bd));
/* 291 */         currencies.add(new ItemStack(Items.field_151147_al));
/*     */         
/* 293 */         items.add(Witchery.Items.GENERIC.itemBatWool.createStack(5));
/*     */       }
/* 295 */       else if ((animal instanceof EntitySpider)) {
/* 296 */         currencies.add(new ItemStack(Items.field_151082_bd));
/* 297 */         currencies.add(new ItemStack(Items.field_151147_al));
/* 298 */         currencies.add(new ItemStack(Items.field_151076_bf));
/* 299 */         currencies.add(new ItemStack(Items.field_151115_aP));
/*     */         
/* 301 */         items.add(new ItemStack(Items.field_151007_F, 8));
/* 302 */         items.add(Witchery.Items.GENERIC.itemWeb.createStack(4));
/* 303 */       } else if ((animal instanceof EntityCreeper)) {
/* 304 */         currencies.add(new ItemStack(Items.field_151016_H));
/* 305 */         currencies.add(new ItemStack(Items.field_151115_aP));
/*     */         
/* 307 */         if (r.nextDouble() < 0.05D) {
/* 308 */           items.add(Witchery.Items.GENERIC.itemSpectralDust.createStack(2));
/*     */         }
/*     */         
/* 311 */         if (animal.field_70170_p.field_73012_v.nextDouble() < 0.1D) {
/* 312 */           items.add(Witchery.Items.GENERIC.itemSeedsTreefyd.createStack());
/*     */         }
/*     */         
/* 315 */         if (r.nextDouble() < 0.02D) {
/* 316 */           items.add(Witchery.Items.GENERIC.itemCreeperHeart.createStack(1));
/*     */         }
/* 318 */       } else if (animal.func_70662_br()) {
/* 319 */         currencies.add(new ItemStack(Items.field_151103_aS));
/*     */         
/* 321 */         items.add(Witchery.Items.GENERIC.itemSpectralDust.createStack(1));
/*     */       } else {
/* 323 */         currencies.add(new ItemStack(Items.field_151082_bd));
/* 324 */         currencies.add(new ItemStack(Items.field_151147_al));
/* 325 */         currencies.add(new ItemStack(Items.field_151076_bf));
/* 326 */         currencies.add(new ItemStack(Items.field_151115_aP));
/* 327 */         currencies.add(new ItemStack(Items.field_151015_O));
/* 328 */         currencies.add(new ItemStack(Items.field_151014_N));
/* 329 */         currencies.add(new ItemStack(Items.field_151172_bF));
/* 330 */         currencies.add(new ItemStack(Items.field_151034_e));
/* 331 */         currencies.add(new ItemStack(Items.field_151174_bG));
/*     */       }
/*     */       
/* 334 */       for (ItemStack itemstack : items) {
/* 335 */         if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
/* 336 */           ItemStack goods = itemstack.func_77946_l();
/* 337 */           goods.field_77994_a = Math.min(r.nextInt(itemstack.field_77994_a) + (itemstack.field_77994_a > 4 ? 3 : 1), goods.func_77976_d());
/* 338 */           ItemStack currency = (ItemStack)currencies.get(r.nextInt(currencies.size()));
/* 339 */           ItemStack cost = currency.func_77946_l();
/* 340 */           int multiplier = 1;
/* 341 */           if ((goods.func_77973_b() == Items.field_151045_i) || (goods.func_77973_b() == Items.field_151166_bC) || (goods.func_77973_b() == Items.field_151141_av) || (Witchery.Items.GENERIC.itemSeedsTreefyd.isMatch(goods)) || (animal.func_70662_br()))
/*     */           {
/* 343 */             multiplier = 2;
/*     */           }
/* 345 */           int factor = goods.field_77994_a > 4 ? 1 : 2;
/* 346 */           cost.field_77994_a = Math.min(r.nextInt(2) + goods.field_77994_a * multiplier * (r.nextInt(2) + factor), currency.func_77976_d());
/* 347 */           MerchantRecipe recipe = new MerchantRecipe(cost, goods);
/* 348 */           recipe.func_82783_a(0 - (6 - r.nextInt(2)));
/* 349 */           list.add(recipe);
/*     */         }
/*     */       }
/*     */       
/* 353 */       Collections.shuffle(list);
/*     */       
/* 355 */       int MAX_ITEMS = r.nextInt(2) + 1;
/*     */       
/* 357 */       for (int i = 0; (i < MAX_ITEMS) && (i < list.size()); i++) {
/* 358 */         finalList.add(list.get(i));
/*     */       }
/*     */     }
/*     */     
/*     */     private void playGreeting(EntityLiving animal, EntityPlayer player) {
/* 363 */       animal.func_70642_aH();
/* 364 */       animal.func_70642_aH();
/* 365 */       animal.func_70642_aH();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemPolynesiaCharm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */