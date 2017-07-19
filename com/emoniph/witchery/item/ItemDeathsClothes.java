/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.client.model.ModelClothesDeath;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemDeathsClothes extends ItemArmor
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelClothesDeath modelClothesChest;
/*     */   private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
/*     */   
/*     */   public ItemDeathsClothes(int armorSlot)
/*     */   {
/*  35 */     super(ItemArmor.ArmorMaterial.IRON, 1, armorSlot);
/*     */     
/*  37 */     func_77656_e(ItemArmor.ArmorMaterial.DIAMOND.func_78046_a(armorSlot));
/*     */     
/*  39 */     func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public Item func_77655_b(String itemName)
/*     */   {
/*  44 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  45 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  50 */     if (stack != null) {
/*  51 */       return "witchery:textures/entities/deathsclothes" + (type == null ? "" : "_overlay") + ".png";
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
/*     */   {
/*  59 */     if ((!world.field_72995_K) && 
/*  60 */       (player.func_82169_q(3) == stack)) {
/*  61 */       int offset = (int)(world.func_82737_E() % 10L);
/*  62 */       if ((Config.instance().allowDeathsHoodToFreezeVictims) && (offset == 1)) {
/*  63 */         MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.raytraceEntities(world, player, true, 16.0D);
/*  64 */         if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLivingBase))) {
/*  65 */           EntityLivingBase victim = (EntityLivingBase)mop.field_72308_g;
/*  66 */           if ((victim.func_70685_l(player)) && 
/*  67 */             (!victim.func_70644_a(Potion.field_76421_d))) {
/*  68 */             victim.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 60, 2));
/*     */           }
/*     */         }
/*     */       }
/*  72 */       else if (offset == 2) {
/*  73 */         int x = MathHelper.func_76128_c(player.field_70165_t);
/*  74 */         int y = MathHelper.func_76128_c(player.field_70163_u);
/*  75 */         int z = MathHelper.func_76128_c(player.field_70161_v);
/*  76 */         if (world.func_72957_l(x, y, z) < 8) {
/*  77 */           PotionEffect potion = player.func_70660_b(Potion.field_76439_r);
/*  78 */           if ((potion == null) || (potion.func_76459_b() <= TimeUtil.secsToTicks(15))) {
/*  79 */             player.func_82170_o(Potion.field_76439_r.field_76415_H);
/*  80 */             player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, TimeUtil.secsToTicks(20), 0, true));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
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
/*  96 */     if (this.modelClothesChest == null) {
/*  97 */       this.modelClothesChest = new ModelClothesDeath(0.4F);
/*     */     }
/*     */     
/* 100 */     ModelBiped armorModel = null;
/* 101 */     if ((stack != null) && ((stack.func_77973_b() instanceof ItemDeathsClothes))) {
/* 102 */       int type = ((ItemArmor)stack.func_77973_b()).field_77881_a;
/*     */       
/* 104 */       armorModel = this.modelClothesChest;
/*     */       
/* 106 */       if (armorModel != null) {
/* 107 */         boolean isVisible = true;
/* 108 */         if ((entityLiving != null) && (entityLiving.func_82150_aj())) {
/* 109 */           String entityTypeName = entityLiving.getClass().getSimpleName();
/* 110 */           isVisible = (entityTypeName == null) || (entityTypeName.isEmpty()) || (entityTypeName.equals("AbstractSteve"));
/*     */         }
/*     */         
/*     */ 
/* 114 */         armorModel.field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 115 */         armorModel.field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 116 */         armorModel.field_78115_e.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 117 */         armorModel.field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 118 */         armorModel.field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 119 */         armorModel.field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/* 120 */         armorModel.field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/*     */         
/* 122 */         armorModel.field_78117_n = entityLiving.func_70093_af();
/* 123 */         armorModel.field_78093_q = entityLiving.func_70115_ae();
/* 124 */         armorModel.field_78091_s = entityLiving.func_70631_g_();
/*     */         
/* 126 */         ItemStack heldStack = entityLiving.func_71124_b(0);
/* 127 */         armorModel.field_78120_m = (heldStack != null ? 1 : 0);
/* 128 */         armorModel.field_78118_o = false;
/*     */         
/* 130 */         if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0)) {
/* 131 */           EnumAction enumaction = heldStack.func_77975_n();
/*     */           
/* 133 */           if (enumaction == EnumAction.block) {
/* 134 */             armorModel.field_78120_m = 3;
/*     */           }
/*     */           
/* 137 */           armorModel.field_78118_o = (enumaction == EnumAction.bow);
/*     */         }
/* 139 */         return armorModel;
/*     */       }
/*     */     }
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/* 148 */     return EnumRarity.epic;
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/* 153 */     String baseName = super.func_77653_i(stack);
/* 154 */     return baseName;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/* 159 */     if ((stack != null) && ((stack.func_77973_b() != Witchery.Items.DEATH_HOOD) || (Config.instance().allowDeathsHoodToFreezeVictims))) {
/* 160 */       String localText = Witchery.resource(func_77658_a() + ".tip");
/* 161 */       if (localText != null) {
/* 162 */         for (String s : localText.split("\n")) {
/* 163 */           if (!s.isEmpty()) {
/* 164 */             list.add(s);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isFullSetWorn(EntityLivingBase entity) {
/* 172 */     int count = 0;
/* 173 */     for (int i = 1; i <= 4; i++) {
/* 174 */       ItemStack item = entity.func_71124_b(i);
/* 175 */       if ((item != null) && ((item.func_77973_b() instanceof ItemDeathsClothes))) {
/* 176 */         count++;
/*     */       }
/*     */     }
/* 179 */     return count >= 3;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemDeathsClothes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */