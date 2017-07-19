/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelVampireArmor;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
/*     */ 
/*     */ public class ItemVampireClothes extends ItemArmor implements net.minecraftforge.common.ISpecialArmor
/*     */ {
/*     */   boolean female;
/*     */   boolean metal;
/*     */   private int realDamageReduction;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconUnderlay;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelVampireArmor modelClothesChest;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelVampireArmor modelClothesLegs;
/*     */   private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
/*     */   
/*     */   public ItemVampireClothes(int armorSlot, boolean female, boolean metal)
/*     */   {
/*  36 */     super(ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
/*  37 */     this.female = female;
/*  38 */     this.metal = metal;
/*     */     
/*  40 */     func_77656_e(ItemArmor.ArmorMaterial.IRON.func_78046_a(armorSlot));
/*     */     
/*  42 */     this.realDamageReduction = (metal ? ItemArmor.ArmorMaterial.IRON.func_78044_b(armorSlot) : this.field_77879_b);
/*     */     
/*  44 */     func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public net.minecraft.item.Item func_77655_b(String itemName)
/*     */   {
/*  49 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  50 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   public int func_77619_b() {
/*  54 */     return ItemArmor.ArmorMaterial.GOLD.func_78045_a();
/*     */   }
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  59 */     if ((stack != null) && (this.field_77881_a == 2))
/*  60 */       return "witchery:textures/entities/vampirearmor.png";
/*  61 */     if (stack != null) {
/*  62 */       return type == null ? "witchery:textures/entities/vampirearmor_over_first.png" : "witchery:textures/entities/vampirearmor_over.png";
/*     */     }
/*     */     
/*  65 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82816_b_(ItemStack stack)
/*     */   {
/*  71 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onArmorTick(net.minecraft.world.World world, EntityPlayer player, ItemStack stack) {}
/*     */   
/*     */ 
/*     */   public int func_82814_b(ItemStack stack)
/*     */   {
/*  81 */     if (!func_82816_b_(stack)) {
/*  82 */       return super.func_82814_b(stack);
/*     */     }
/*     */     
/*  85 */     int color = super.func_82814_b(stack);
/*  86 */     if (color == 10511680) {
/*  87 */       color = 13369344;
/*     */     }
/*  89 */     return color;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/*  95 */     return super.func_82790_a(stack, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int damage, int renderPass)
/*     */   {
/* 107 */     if (renderPass == 0) {
/* 108 */       return this.iconUnderlay;
/*     */     }
/* 110 */     return func_77617_a(damage);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/* 120 */     super.func_94581_a(iconRegister);
/* 121 */     this.iconUnderlay = iconRegister.func_94245_a(func_111208_A() + "_first");
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
/* 135 */     if (this.modelClothesChest == null) {
/* 136 */       this.modelClothesChest = new ModelVampireArmor(0.3F, false, this.female, this.metal);
/*     */     }
/*     */     
/* 139 */     if (this.modelClothesLegs == null) {
/* 140 */       this.modelClothesLegs = new ModelVampireArmor(0.02F, true, this.female, this.metal);
/*     */     }
/*     */     
/* 143 */     ModelBiped armorModel = null;
/* 144 */     if ((stack != null) && ((stack.func_77973_b() instanceof ItemVampireClothes))) {
/* 145 */       int type = ((ItemArmor)stack.func_77973_b()).field_77881_a;
/*     */       
/* 147 */       if (type == 2) {
/* 148 */         armorModel = this.modelClothesLegs;
/*     */       } else {
/* 150 */         armorModel = this.modelClothesChest;
/*     */       }
/*     */       
/* 153 */       if (armorModel != null) {
/* 154 */         boolean isVisible = true;
/* 155 */         if ((entityLiving != null) && (entityLiving.func_82150_aj())) {
/* 156 */           String entityTypeName = entityLiving.getClass().getSimpleName();
/* 157 */           isVisible = (entityTypeName == null) || (entityTypeName.isEmpty()) || (entityTypeName.equals("AbstractSteve"));
/*     */         }
/*     */         
/*     */ 
/* 161 */         armorModel.field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 162 */         armorModel.field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 163 */         armorModel.field_78115_e.field_78806_j = ((isVisible) && ((armorSlot == 1) || (armorSlot == 2)));
/* 164 */         armorModel.field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 165 */         armorModel.field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 166 */         armorModel.field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/* 167 */         armorModel.field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/*     */         
/* 169 */         armorModel.field_78117_n = entityLiving.func_70093_af();
/* 170 */         armorModel.field_78093_q = entityLiving.func_70115_ae();
/* 171 */         armorModel.field_78091_s = entityLiving.func_70631_g_();
/*     */         
/* 173 */         ItemStack heldStack = entityLiving.func_71124_b(0);
/* 174 */         armorModel.field_78120_m = (heldStack != null ? 1 : 0);
/* 175 */         armorModel.field_78118_o = false;
/*     */         
/* 177 */         if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0))
/*     */         {
/* 179 */           EnumAction enumaction = heldStack.func_77975_n();
/*     */           
/* 181 */           if (enumaction == EnumAction.block) {
/* 182 */             armorModel.field_78120_m = 3;
/*     */           }
/*     */           
/* 185 */           armorModel.field_78118_o = (enumaction == EnumAction.bow);
/*     */         }
/* 187 */         return armorModel;
/*     */       }
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/* 196 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/* 201 */     String baseName = super.func_77653_i(stack);
/* 202 */     return baseName;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/* 207 */     String localText = com.emoniph.witchery.Witchery.resource(func_77658_a() + ".tip");
/* 208 */     if (localText != null) {
/* 209 */       for (String s : localText.split("\n")) {
/* 210 */         if (!s.isEmpty()) {
/* 211 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static int numLightPiecesWorn(EntityLivingBase entity, boolean light) {
/* 218 */     int pieces = 0;
/* 219 */     for (int i = 1; i <= 4; i++) {
/* 220 */       ItemStack item = entity.func_71124_b(i);
/* 221 */       if ((item != null) && ((item.func_77973_b() instanceof ItemVampireClothes)) && 
/* 222 */         (!((ItemVampireClothes)item.func_77973_b()).metal) && (light)) {
/* 223 */         pieces++;
/*     */       }
/*     */     }
/*     */     
/* 227 */     return pieces;
/*     */   }
/*     */   
/*     */   public static boolean isFlameProtectionActive(EntityLivingBase entity) {
/* 231 */     return (numLightPiecesWorn(entity, true) >= 3) || (numLightPiecesWorn(entity, true) >= 2);
/*     */   }
/*     */   
/*     */   public static boolean isExtendedFlameProtectionActive(EntityLivingBase entity) {
/* 235 */     return numLightPiecesWorn(entity, true) >= 4;
/*     */   }
/*     */   
/*     */   public static boolean isDrinkBoostActive(EntityLivingBase entity) {
/* 239 */     return numLightPiecesWorn(entity, true) >= 2;
/*     */   }
/*     */   
/*     */   public static boolean isMezmeriseBoostActive(EntityLivingBase entity) {
/* 243 */     return numLightPiecesWorn(entity, true) >= 3;
/*     */   }
/*     */   
/*     */ 
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
/*     */   {
/* 249 */     return new ISpecialArmor.ArmorProperties(0, this.realDamageReduction / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
/*     */   }
/*     */   
/*     */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
/*     */   {
/* 254 */     return this.realDamageReduction;
/*     */   }
/*     */   
/*     */ 
/*     */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
/*     */   {
/* 260 */     stack.func_77972_a(damage, entity);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemVampireClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */