/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.model.ModelWitchesClothes;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemWitchesClothes extends ItemArmor
/*     */ {
/*     */   private static final int CHARGE_PER_PIECE = 2;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelWitchesClothes modelClothesChest;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelWitchesClothes modelNecroChest;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelWitchesClothes modelClothesLegs;
/*     */   private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
/*     */   
/*     */   public ItemWitchesClothes(int armorSlot)
/*     */   {
/*  44 */     super(net.minecraft.item.ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
/*  45 */     func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public net.minecraft.item.Item func_77655_b(String itemName)
/*     */   {
/*  50 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  51 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  56 */     if ((stack != null) && ((stack.func_77973_b() == Witchery.Items.WITCH_HAT) || (stack.func_77973_b() == Witchery.Items.WITCH_ROBES) || (stack.func_77973_b() == Witchery.Items.NECROMANCERS_ROBES) || (stack.func_77973_b() == Witchery.Items.ICY_SLIPPERS) || (stack.func_77973_b() == Witchery.Items.RUBY_SLIPPERS) || (stack.func_77973_b() == Witchery.Items.SEEPING_SHOES) || (stack.func_77973_b() == Witchery.Items.BABAS_HAT)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  61 */       return "witchery:textures/entities/witchclothes" + (type == null ? "" : "_overlay") + ".png"; }
/*  62 */     if ((stack != null) && (stack.func_77973_b() == Witchery.Items.BITING_BELT))
/*  63 */       return "witchery:textures/entities/witchclothes_legs" + (type == null ? "" : "_overlay") + ".png";
/*  64 */     if ((stack != null) && (stack.func_77973_b() == Witchery.Items.BARK_BELT)) {
/*  65 */       return "witchery:textures/entities/witchclothes" + (type == null ? "2_legs" : "_legs_overlay") + ".png";
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getMaxChargeLevel(EntityLivingBase entity)
/*     */   {
/*  74 */     int level = 0;
/*  75 */     for (int i = 1; i <= 4; i++) {
/*  76 */       ItemStack stack = entity.func_71124_b(i);
/*  77 */       if ((stack != null) && ((stack.func_77973_b() instanceof ItemWitchesClothes))) {
/*  78 */         level += 2;
/*     */       }
/*     */     }
/*     */     
/*  82 */     return level;
/*     */   }
/*     */   
/*     */   public void setChargeLevel(ItemStack stack, int level) {
/*  86 */     if (!stack.func_77942_o()) {
/*  87 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/*  89 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/*  90 */     nbtRoot.func_74768_a("WITCBarkPieces", level);
/*     */   }
/*     */   
/*     */   public int getChargeLevel(ItemStack stack) {
/*  94 */     if (stack.func_77942_o()) {
/*  95 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/*  96 */       if (nbtRoot.func_74764_b("WITCBarkPieces")) {
/*  97 */         return nbtRoot.func_74762_e("WITCBarkPieces");
/*     */       }
/*     */     }
/*     */     
/* 101 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean func_82816_b_(ItemStack stack)
/*     */   {
/* 106 */     return (stack == null) || (stack.func_77973_b() != Witchery.Items.BABAS_HAT);
/*     */   }
/*     */   
/*     */   public int func_82814_b(ItemStack stack)
/*     */   {
/* 111 */     if (!func_82816_b_(stack)) {
/* 112 */       return super.func_82814_b(stack);
/*     */     }
/* 114 */     if (stack.func_77973_b() == Witchery.Items.RUBY_SLIPPERS) {
/* 115 */       return 14483456;
/*     */     }
/* 117 */     int color = super.func_82814_b(stack);
/* 118 */     if (color == 10511680) {
/* 119 */       if (this == Witchery.Items.ICY_SLIPPERS) {
/* 120 */         color = 7842303;
/* 121 */       } else if (this == Witchery.Items.SEEPING_SHOES) {
/* 122 */         color = 2254387;
/* 123 */       } else if (this == Witchery.Items.BARK_BELT) {
/* 124 */         color = 6968628;
/*     */       } else {
/* 126 */         color = 2628115;
/*     */       }
/*     */     }
/* 129 */     return color;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/* 136 */     return super.func_82790_a(stack, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public net.minecraft.util.IIcon func_77618_c(int par1, int par2)
/*     */   {
/* 148 */     return func_77617_a(par1);
/*     */   }
/*     */   
/*     */   public boolean isHatWorn(EntityPlayer player) {
/* 152 */     return (player.field_71071_by.func_70440_f(3) != null) && (player.field_71071_by.func_70440_f(3).func_77973_b() == this);
/*     */   }
/*     */   
/*     */   public boolean isRobeWorn(EntityPlayer player) {
/* 156 */     return (player.field_71071_by.func_70440_f(2) != null) && (player.field_71071_by.func_70440_f(2).func_77973_b() == this);
/*     */   }
/*     */   
/*     */   public boolean isBeltWorn(EntityPlayer player) {
/* 160 */     return (player.field_71071_by.func_70440_f(1) != null) && (player.field_71071_by.func_70440_f(1).func_77973_b() == this);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
/*     */   {
/* 177 */     if (this.modelClothesChest == null) {
/* 178 */       this.modelClothesChest = new ModelWitchesClothes(0.61F, false);
/*     */     }
/*     */     
/* 181 */     if (this.modelNecroChest == null) {
/* 182 */       this.modelNecroChest = new ModelWitchesClothes(0.61F, true);
/*     */     }
/*     */     
/* 185 */     if (this.modelClothesLegs == null) {
/* 186 */       this.modelClothesLegs = new ModelWitchesClothes(0.45F, false);
/*     */     }
/*     */     
/* 189 */     ModelBiped armorModel = null;
/* 190 */     if ((stack != null) && ((stack.func_77973_b() instanceof ItemWitchesClothes))) {
/* 191 */       int type = ((ItemArmor)stack.func_77973_b()).field_77881_a;
/*     */       
/* 193 */       if ((type == 1) || (type == 3)) {
/* 194 */         armorModel = stack.func_77973_b() == Witchery.Items.NECROMANCERS_ROBES ? this.modelNecroChest : this.modelClothesChest;
/*     */       } else {
/* 196 */         armorModel = this.modelClothesLegs;
/*     */       }
/*     */       
/* 199 */       if (armorModel != null) {
/* 200 */         boolean isVisible = true;
/* 201 */         if ((entityLiving != null) && (entityLiving.func_82150_aj())) {
/* 202 */           String entityTypeName = entityLiving.getClass().getSimpleName();
/* 203 */           isVisible = (entityTypeName == null) || (entityTypeName.isEmpty()) || (entityTypeName.equals("AbstractSteve"));
/*     */         }
/*     */         
/*     */ 
/* 207 */         armorModel.field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 208 */         armorModel.field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 209 */         armorModel.field_78115_e.field_78806_j = ((isVisible) && ((armorSlot == 1) || (armorSlot == 2)));
/* 210 */         armorModel.field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 211 */         armorModel.field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 212 */         armorModel.field_78123_h.field_78806_j = ((isVisible) && (armorSlot == 3));
/* 213 */         armorModel.field_78124_i.field_78806_j = ((isVisible) && (armorSlot == 3));
/*     */         
/* 215 */         armorModel.field_78117_n = entityLiving.func_70093_af();
/* 216 */         armorModel.field_78093_q = entityLiving.func_70115_ae();
/* 217 */         armorModel.field_78091_s = entityLiving.func_70631_g_();
/*     */         
/* 219 */         ItemStack heldStack = entityLiving.func_71124_b(0);
/* 220 */         armorModel.field_78120_m = (heldStack != null ? 1 : 0);
/* 221 */         armorModel.field_78118_o = false;
/*     */         
/* 223 */         if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0)) {
/* 224 */           EnumAction enumaction = heldStack.func_77975_n();
/*     */           
/* 226 */           if (enumaction == EnumAction.block) {
/* 227 */             armorModel.field_78120_m = 3;
/*     */           }
/*     */           
/* 230 */           armorModel.field_78118_o = (enumaction == EnumAction.bow);
/*     */         }
/* 232 */         return armorModel;
/*     */       }
/*     */     }
/* 235 */     return null;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/* 241 */     if (stack == null)
/* 242 */       return EnumRarity.common;
/* 243 */     if (stack.func_77973_b() == Witchery.Items.BABAS_HAT)
/* 244 */       return EnumRarity.epic;
/* 245 */     if (stack.func_77973_b() == Witchery.Items.BARK_BELT) {
/* 246 */       return EnumRarity.rare;
/*     */     }
/* 248 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/* 254 */     String baseName = super.func_77653_i(stack);
/* 255 */     return baseName;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/* 260 */     String localText = Witchery.resource(func_77658_a() + ".tip");
/* 261 */     if (localText != null) {
/* 262 */       for (String s : localText.split("\n")) {
/* 263 */         if (!s.isEmpty()) {
/* 264 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/* 268 */     if ((stack != null) && (stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("WITCPotion"))) {
/* 269 */       int potion = stack.func_77978_p().func_74762_e("WITCPotion");
/* 270 */       List effects = net.minecraft.init.Items.field_151068_bn.func_77834_f(potion);
/* 271 */       if ((effects != null) && (!effects.isEmpty())) {
/* 272 */         PotionEffect effect = (PotionEffect)effects.get(0);
/* 273 */         String s1 = effect.func_76453_d();
/* 274 */         s1 = s1 + ".postfix";
/* 275 */         String s2 = "§6" + StatCollector.func_74838_a(s1).trim() + "§r";
/*     */         
/* 277 */         if (effect.func_76458_c() > 0) {
/* 278 */           s2 = s2 + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */         }
/*     */         
/* 281 */         if (effect.func_76459_b() > 20) {
/* 282 */           s2 = s2 + " [" + net.minecraft.potion.Potion.func_76389_a(effect) + "]";
/*     */         }
/*     */         
/* 285 */         list.add(s2);
/*     */       }
/*     */     }
/*     */     
/* 289 */     if ((stack != null) && (stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("WITCPotion2"))) {
/* 290 */       int potion = stack.func_77978_p().func_74762_e("WITCPotion2");
/* 291 */       List effects = net.minecraft.init.Items.field_151068_bn.func_77834_f(potion);
/* 292 */       if ((effects != null) && (!effects.isEmpty())) {
/* 293 */         PotionEffect effect = (PotionEffect)effects.get(0);
/* 294 */         String s1 = effect.func_76453_d();
/* 295 */         s1 = s1 + ".postfix";
/* 296 */         String s2 = "§6" + StatCollector.func_74838_a(s1).trim() + "§r";
/*     */         
/* 298 */         if (effect.func_76458_c() > 0) {
/* 299 */           s2 = s2 + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
/*     */         }
/*     */         
/* 302 */         if (effect.func_76459_b() > 20) {
/* 303 */           s2 = s2 + " [" + net.minecraft.potion.Potion.func_76389_a(effect) + "]";
/*     */         }
/*     */         
/* 306 */         list.add(s2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 311 */   private static String noPlaceLikeHome = null;
/*     */   private static final double WILD_EFFECT_CHANCE = 0.01D;
/*     */   
/*     */   public boolean trySayTheresNoPlaceLikeHome(EntityPlayer player, String message) {
/* 315 */     if ((player == null) || (player.field_70170_p.field_72995_K)) {
/* 316 */       return false;
/*     */     }
/* 318 */     if (noPlaceLikeHome == null) {
/* 319 */       noPlaceLikeHome = Witchery.resource("witchery.rite.noplacelikehome").toLowerCase().replace("'", "");
/*     */     }
/* 321 */     if (message.toLowerCase().replace("'", "").startsWith(noPlaceLikeHome)) {
/* 322 */       ItemStack stack = player.func_71124_b(1);
/* 323 */       if ((stack != null) && (stack.func_77973_b() == Witchery.Items.RUBY_SLIPPERS) && (player.field_71093_bK != Config.instance().dimensionDreamID)) {
/* 324 */         NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 325 */         if (nbtPlayer != null) {
/* 326 */           int R = 3;
/* 327 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 3.0D, player.field_70163_u - 3.0D, player.field_70161_v - 3.0D, player.field_70165_t + 3.0D, player.field_70163_u + 3.0D, player.field_70161_v + 3.0D);
/*     */           
/* 329 */           List list = player.field_70170_p.func_72872_a(EntityItem.class, bounds);
/* 330 */           for (Object obj : list) {
/* 331 */             EntityItem waystoneEntity = (EntityItem)obj;
/* 332 */             ItemStack waystoneStack = waystoneEntity.func_92059_d();
/* 333 */             if ((waystoneStack != null) && (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(waystoneStack))) {
/* 334 */               if (nbtPlayer.func_74764_b("WITCLastRubySlipperWayTime")) {
/* 335 */                 long lastTime = nbtPlayer.func_74763_f("WITCLastRubySlipperWayTime");
/* 336 */                 long currentTime = MinecraftServer.func_130071_aq();
/* 337 */                 long timeSince = currentTime - lastTime;
/* 338 */                 long COOLDOWN = 60000L;
/* 339 */                 if (timeSince < 60000L) {
/* 340 */                   int cooldownRemaining = Math.max(1, (int)(60000L - timeSince) / 60000);
/* 341 */                   com.emoniph.witchery.util.ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.slippersoncooldown", new Object[] { Integer.valueOf(cooldownRemaining).toString() });
/* 342 */                   return true;
/*     */                 }
/*     */               }
/*     */               
/* 346 */               int waystoneDimension = ItemGeneral.getWaystoneDimension(waystoneStack);
/* 347 */               if (Infusion.aquireEnergy(player.field_70170_p, player, nbtPlayer, waystoneDimension != player.field_71093_bK ? 80 : 40, true)) {
/* 348 */                 waystoneEntity.func_70106_y();
/* 349 */                 nbtPlayer.func_74772_a("WITCLastRubySlipperWayTime", MinecraftServer.func_130071_aq());
/* 350 */                 if (player.field_70170_p.field_73012_v.nextDouble() < 0.01D) {
/* 351 */                   com.emoniph.witchery.blocks.BlockVoidBramble.teleportRandomly(player.field_70170_p, MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), player, 500);
/*     */                 }
/*     */                 else {
/* 354 */                   Witchery.Items.GENERIC.teleportToLocation(player.field_70170_p, waystoneStack, player, 2, true);
/*     */                 }
/*     */               }
/* 357 */               return true;
/*     */             }
/*     */           }
/*     */           
/* 361 */           if (nbtPlayer.func_74764_b("WITCLastRubySlipperTime")) {
/* 362 */             long lastTime = nbtPlayer.func_74763_f("WITCLastRubySlipperTime");
/* 363 */             long currentTime = MinecraftServer.func_130071_aq();
/* 364 */             long timeSince = currentTime - lastTime;
/* 365 */             long COOLDOWN = 1800000L;
/* 366 */             if ((timeSince < 1800000L) && (!player.field_71075_bZ.field_75098_d)) {
/* 367 */               int cooldownRemaining = Math.max(1, (int)(1800000L - timeSince) / 60000);
/* 368 */               com.emoniph.witchery.util.ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.slippersoncooldown", new Object[] { Integer.valueOf(cooldownRemaining).toString() });
/* 369 */               return true;
/*     */             }
/*     */           }
/* 372 */           ChunkCoordinates coords = player.getBedLocation(player.field_71093_bK);
/* 373 */           int dimension = player.field_71093_bK;
/* 374 */           World world = player.field_70170_p;
/* 375 */           if (coords == null) {
/* 376 */             coords = player.getBedLocation(0);
/* 377 */             dimension = 0;
/* 378 */             world = MinecraftServer.func_71276_C().func_71218_a(0);
/* 379 */             if (coords == null) {
/* 380 */               coords = world.func_72861_E();
/* 381 */               while ((world.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c).func_149721_r()) && (coords.field_71572_b < 255)) {
/* 382 */                 coords.field_71572_b += 1;
/*     */               }
/*     */             }
/*     */           }
/* 386 */           if (coords != null) {
/* 387 */             nbtPlayer.func_74772_a("WITCLastRubySlipperTime", MinecraftServer.func_130071_aq());
/* 388 */             coords = net.minecraft.init.Blocks.field_150324_C.getBedSpawnPosition(world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, null);
/* 389 */             if (coords != null) {
/* 390 */               if (Infusion.aquireEnergy(player.field_70170_p, player, nbtPlayer, dimension != player.field_71093_bK ? 120 : 80, true)) {
/* 391 */                 ItemGeneral.teleportToLocation(player.field_70170_p, coords.field_71574_a, coords.field_71572_b + 1, coords.field_71573_c, dimension, player, true);
/*     */               }
/* 393 */               return true;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 399 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemWitchesClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */