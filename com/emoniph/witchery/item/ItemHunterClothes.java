/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelHunterClothes;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
/*     */ 
/*     */ public class ItemHunterClothes extends ItemArmor implements net.minecraftforge.common.ISpecialArmor
/*     */ {
/*     */   private boolean silvered;
/*     */   private boolean garlicked;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconOverlaySilver;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconOverlaySilverGarlic;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelHunterClothes modelClothesChest;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelHunterClothes modelClothesLegs;
/*     */   private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
/*     */   
/*     */   public ItemHunterClothes(int armorSlot, boolean silvered, boolean garlicked)
/*     */   {
/*  37 */     super(ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
/*     */     
/*  39 */     this.silvered = silvered;
/*  40 */     this.garlicked = garlicked;
/*  41 */     func_77656_e(ItemArmor.ArmorMaterial.IRON.func_78046_a(armorSlot));
/*     */     
/*  43 */     func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public net.minecraft.item.Item func_77655_b(String itemName)
/*     */   {
/*  48 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  49 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  54 */     if ((stack != null) && (this.field_77881_a == 2)) {
/*  55 */       return "witchery:textures/entities/hunterclothes2" + (type == null ? "" : "_overlay") + ".png";
/*     */     }
/*  57 */     if (stack != null) {
/*  58 */       return "witchery:textures/entities/hunterclothes" + (type == null ? "" : "_overlay") + ".png";
/*     */     }
/*     */     
/*  61 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82816_b_(ItemStack stack)
/*     */   {
/*  67 */     return true;
/*     */   }
/*     */   
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
/*     */   {
/*  72 */     if ((!world.field_72995_K) && (player.field_70173_aa % 20 == 2)) {
/*  73 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  74 */       if (((this.silvered) && (playerEx.getWerewolfLevel() > 0)) || ((this.garlicked) && (playerEx.isVampire()))) {
/*  75 */         player.func_70097_a(DamageSource.field_76372_a, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_82814_b(ItemStack stack)
/*     */   {
/*  82 */     if (!func_82816_b_(stack)) {
/*  83 */       return super.func_82814_b(stack);
/*     */     }
/*     */     
/*  86 */     int color = super.func_82814_b(stack);
/*  87 */     if (color == 10511680) {
/*  88 */       if (stack.func_77973_b() == com.emoniph.witchery.Witchery.Items.HUNTER_BOOTS) {
/*  89 */         color = 1642763;
/*  90 */       } else if (stack.func_77973_b() == com.emoniph.witchery.Witchery.Items.HUNTER_LEGS) {
/*  91 */         color = 4798251;
/*     */       } else {
/*  93 */         color = 4139550;
/*     */       }
/*     */     }
/*  96 */     return color;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/* 102 */     return super.func_82790_a(stack, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 108 */     return (this.silvered) || (this.garlicked);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int damage, int renderPass)
/*     */   {
/* 114 */     if (renderPass == 1) {
/* 115 */       return this.garlicked ? this.iconOverlaySilverGarlic : this.iconOverlaySilver;
/*     */     }
/* 117 */     return func_77617_a(damage);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/* 130 */     super.func_94581_a(iconRegister);
/* 131 */     this.iconOverlaySilver = iconRegister.func_94245_a(func_111208_A() + "_silvered");
/* 132 */     this.iconOverlaySilverGarlic = iconRegister.func_94245_a(func_111208_A() + "_garlicked");
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
/*     */   {
/* 146 */     if (this.modelClothesChest == null) {
/* 147 */       this.modelClothesChest = new ModelHunterClothes(0.4F, false);
/*     */     }
/*     */     
/* 150 */     if (this.modelClothesLegs == null) {
/* 151 */       this.modelClothesLegs = new ModelHunterClothes(0.01F, false);
/*     */     }
/*     */     
/* 154 */     ModelBiped armorModel = null;
/* 155 */     if ((stack != null) && ((stack.func_77973_b() instanceof ItemHunterClothes))) {
/* 156 */       int type = ((ItemArmor)stack.func_77973_b()).field_77881_a;
/*     */       
/* 158 */       if ((type == 1) || (type == 3)) {
/* 159 */         armorModel = this.modelClothesChest;
/*     */       } else {
/* 161 */         armorModel = this.modelClothesLegs;
/*     */       }
/*     */       
/* 164 */       if (armorModel != null) {
/* 165 */         boolean isVisible = true;
/* 166 */         if ((entityLiving != null) && (entityLiving.func_82150_aj())) {
/* 167 */           String entityTypeName = entityLiving.getClass().getSimpleName();
/* 168 */           isVisible = (entityTypeName == null) || (entityTypeName.isEmpty()) || (entityTypeName.equals("AbstractSteve"));
/*     */         }
/*     */         
/*     */ 
/* 172 */         armorModel.field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 173 */         armorModel.field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 174 */         armorModel.field_78115_e.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 175 */         armorModel.field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 176 */         armorModel.field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 177 */         armorModel.field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/* 178 */         armorModel.field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/*     */         
/* 180 */         armorModel.field_78117_n = entityLiving.func_70093_af();
/* 181 */         armorModel.field_78093_q = entityLiving.func_70115_ae();
/* 182 */         armorModel.field_78091_s = entityLiving.func_70631_g_();
/*     */         
/*     */ 
/*     */ 
/* 186 */         ItemStack heldStack = entityLiving.func_71124_b(0);
/* 187 */         armorModel.field_78120_m = (heldStack != null ? 1 : 0);
/* 188 */         armorModel.field_78118_o = false;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 193 */         if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0))
/*     */         {
/* 195 */           net.minecraft.item.EnumAction enumaction = heldStack.func_77975_n();
/*     */           
/* 197 */           if (enumaction == net.minecraft.item.EnumAction.block) {
/* 198 */             armorModel.field_78120_m = 3;
/*     */           }
/*     */           
/* 201 */           armorModel.field_78118_o = (enumaction == net.minecraft.item.EnumAction.bow);
/*     */         }
/*     */         
/* 204 */         return armorModel;
/*     */       }
/*     */     }
/* 207 */     return null;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/* 213 */     return net.minecraft.item.EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/* 218 */     String baseName = super.func_77653_i(stack);
/* 219 */     return baseName;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, java.util.List list, boolean advancedTooltips)
/*     */   {
/* 224 */     String localText = com.emoniph.witchery.Witchery.resource(func_77658_a() + ".tip");
/* 225 */     if (localText != null) {
/* 226 */       for (String s : localText.split("\n")) {
/* 227 */         if (!s.isEmpty()) {
/* 228 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isFullSetWorn(EntityLivingBase entity, boolean silvered) {
/* 235 */     for (int i = 1; i <= 4; i++) {
/* 236 */       ItemStack item = entity.func_71124_b(i);
/* 237 */       if (item == null) {
/* 238 */         return false;
/*     */       }
/*     */       
/* 241 */       if ((item.func_77973_b() instanceof ItemHunterClothes)) {
/* 242 */         ItemHunterClothes clothes = (ItemHunterClothes)item.func_77973_b();
/* 243 */         if ((silvered) && (!clothes.silvered)) {
/* 244 */           return false;
/*     */         }
/*     */       } else {
/* 247 */         return false;
/*     */       }
/*     */     }
/* 250 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isMagicalProtectionActive(EntityLivingBase entity) {
/* 254 */     return (entity != null) && (isFullSetWorn(entity, false)) && (entity.field_70170_p != null) && (entity.field_70170_p.field_73012_v.nextDouble() < 0.25D);
/*     */   }
/*     */   
/*     */   public static boolean isCurseProtectionActive(EntityLivingBase entity)
/*     */   {
/* 259 */     return (entity != null) && (isFullSetWorn(entity, false)) && (entity.field_70170_p != null) && (entity.field_70170_p.field_73012_v.nextDouble() < 0.9D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
/*     */   {
/* 266 */     if ((this.silvered) && (source != null) && (CreatureUtil.isWerewolf(source.func_76346_g()))) {
/* 267 */       source.func_76346_g().func_70097_a(DamageSource.field_76372_a, 1.0F);
/* 268 */       return new ISpecialArmor.ArmorProperties(0, this.field_77879_b * 2.5D / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
/*     */     }
/* 270 */     if ((this.garlicked) && (source != null) && (CreatureUtil.isVampire(source.func_76346_g()))) {
/* 271 */       return new ISpecialArmor.ArmorProperties(0, this.field_77879_b * 2.5D / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
/*     */     }
/*     */     
/* 274 */     return new ISpecialArmor.ArmorProperties(0, this.field_77879_b / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
/*     */   {
/* 281 */     return this.field_77879_b;
/*     */   }
/*     */   
/*     */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
/*     */   {
/* 286 */     if ((this.silvered) && (source != null) && (CreatureUtil.isWerewolf(source.func_76346_g()))) {
/* 287 */       return;
/*     */     }
/*     */     
/* 290 */     if ((this.garlicked) && (source != null) && (CreatureUtil.isVampire(source.func_76346_g()))) {
/* 291 */       return;
/*     */     }
/*     */     
/* 294 */     stack.func_77972_a(damage, entity);
/*     */   }
/*     */   
/*     */   public static boolean isWolfProtectionActive(EntityLivingBase entity) {
/* 298 */     return (entity != null) && (isFullSetWorn(entity, true));
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemHunterClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */