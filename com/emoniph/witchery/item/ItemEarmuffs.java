/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.client.model.ModelEarmuffs;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.client.event.sound.PlaySoundEvent17;
/*     */ 
/*     */ public class ItemEarmuffs extends ItemArmor
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelEarmuffs modelClothesChest;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ModelEarmuffs modelClothesLegs;
/*     */   
/*     */   public ItemEarmuffs(int armorSlot)
/*     */   {
/*  29 */     super(ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
/*     */     
/*  31 */     func_77656_e(ItemArmor.ArmorMaterial.CLOTH.func_78046_a(armorSlot));
/*     */     
/*  33 */     func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public net.minecraft.item.Item func_77655_b(String itemName)
/*     */   {
/*  38 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  39 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   public boolean func_82816_b_(ItemStack stack)
/*     */   {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/*  50 */     return super.func_82790_a(stack, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  56 */     return false;
/*     */   }
/*     */   
/*     */   public int func_82814_b(ItemStack stack)
/*     */   {
/*  61 */     if (!func_82816_b_(stack)) {
/*  62 */       return 16777215;
/*     */     }
/*     */     
/*  65 */     return super.func_82814_b(stack);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  71 */     return EnumRarity.common;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/*  76 */     if (stack != null) {
/*  77 */       String localText = Witchery.resource(func_77658_a() + ".tip");
/*  78 */       if (localText != null) {
/*  79 */         for (String s : localText.split("\n")) {
/*  80 */           if (!s.isEmpty()) {
/*  81 */             list.add(s);
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
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
/*     */   {
/*  96 */     if (stack != null) {
/*  97 */       if (type == null) {
/*  98 */         return "witchery:textures/entities/earmuffs.png";
/*     */       }
/* 100 */       return "witchery:textures/entities/empty64x64_overlay.png";
/*     */     }
/*     */     
/* 103 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
/*     */   {
/* 110 */     if (this.modelClothesChest == null) {
/* 111 */       this.modelClothesChest = new ModelEarmuffs();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 118 */     ModelBiped armorModel = null;
/* 119 */     if ((stack != null) && ((stack.func_77973_b() instanceof ItemArmor))) {
/* 120 */       int type = ((ItemArmor)stack.func_77973_b()).field_77881_a;
/*     */       
/* 122 */       if (type != 2) {
/* 123 */         armorModel = this.modelClothesChest;
/*     */       } else {
/* 125 */         armorModel = this.modelClothesChest;
/*     */       }
/*     */       
/* 128 */       if (armorModel != null) {
/* 129 */         boolean isVisible = true;
/*     */         
/* 131 */         armorModel.field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 132 */         armorModel.field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
/* 133 */         armorModel.field_78115_e.field_78806_j = ((isVisible) && ((armorSlot == 1) || (armorSlot == 2)));
/* 134 */         armorModel.field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 135 */         armorModel.field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
/* 136 */         armorModel.field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/* 137 */         armorModel.field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
/*     */         
/* 139 */         armorModel.field_78117_n = entityLiving.func_70093_af();
/* 140 */         armorModel.field_78093_q = entityLiving.func_70115_ae();
/* 141 */         armorModel.field_78091_s = entityLiving.func_70631_g_();
/*     */         
/* 143 */         ItemStack heldStack = entityLiving.func_71124_b(0);
/* 144 */         armorModel.field_78120_m = (heldStack != null ? 1 : 0);
/* 145 */         armorModel.field_78118_o = false;
/*     */         
/* 147 */         if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0)) {
/* 148 */           EnumAction enumaction = heldStack.func_77975_n();
/*     */           
/* 150 */           if (enumaction == EnumAction.block) {
/* 151 */             armorModel.field_78120_m = 3;
/*     */           }
/*     */           
/* 154 */           armorModel.field_78118_o = (enumaction == EnumAction.bow);
/*     */         }
/* 156 */         return armorModel;
/*     */       }
/*     */     }
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isHelmWorn(EntityPlayer entity) {
/* 163 */     ItemStack currentArmor = entity.func_82169_q(3);
/* 164 */     return (currentArmor != null) && (currentArmor.func_77973_b() == Witchery.Items.EARMUFFS);
/*     */   }
/*     */   
/*     */   public static class ClientEventHooks {
/*     */     @cpw.mods.fml.common.eventhandler.SubscribeEvent
/*     */     public void onSound(PlaySoundEvent17 event) {
/* 170 */       Minecraft MC = Minecraft.func_71410_x();
/* 171 */       EntityPlayer player = MC.field_71439_g;
/* 172 */       if ((player != null) && (ItemEarmuffs.isHelmWorn(player))) {
/* 173 */         event.result = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemEarmuffs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */