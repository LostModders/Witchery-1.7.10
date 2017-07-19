/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryCreativeTab;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemCaneSword extends ItemSword
/*     */ {
/*     */   public ItemCaneSword()
/*     */   {
/*  31 */     super(Item.ToolMaterial.EMERALD);
/*  32 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public Item func_77655_b(String itemName)
/*     */   {
/*  37 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  38 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/*  44 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */   public int func_77619_b()
/*     */   {
/*  49 */     return Item.ToolMaterial.IRON.func_77995_e();
/*     */   }
/*     */   
/*     */   public Multimap getAttributeModifiers(ItemStack stack) {
/*  53 */     Multimap multimap = HashMultimap.create();
/*  54 */     float damage = isDrawn(stack) ? 4.0F + func_150931_i() : 1.0F;
/*  55 */     multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", damage, 0));
/*     */     
/*  57 */     return multimap;
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  62 */     if (!stack.func_77942_o()) {
/*  63 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/*  65 */     NBTTagCompound nbtItem = stack.func_77978_p();
/*  66 */     boolean deployed = isDrawn(nbtItem);
/*  67 */     if (player.func_70093_af()) {
/*  68 */       if (!world.field_72995_K) {
/*  69 */         setDrawn(player, stack, nbtItem, !deployed);
/*     */         
/*  71 */         if (deployed) {
/*  72 */           SoundEffect.WITCHERY_RANDOM_SWORD_DRAW.playAtPlayer(world, player, 1.0F, 1.0F);
/*     */         } else {
/*  74 */           SoundEffect.WITCHERY_RANDOM_SWORD_SHEATHE.playAtPlayer(world, player, 1.0F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*  78 */     else if (deployed) {
/*  79 */       super.func_77659_a(stack, world, player);
/*     */     } else {
/*  81 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  82 */       if ((playerEx.isVampire()) && (playerEx.isBloodReserveReady()) && (playerEx.getBloodPower() < playerEx.getMaxBloodPower())) {
/*  83 */         ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, player.field_70165_t, player.field_70163_u + player.field_70131_O * 0.85D, player.field_70161_v, 0.5D, 0.5D, 16);
/*     */         
/*  85 */         playerEx.useBloodReserve();
/*     */       } else {
/*  87 */         SoundEffect.NOTE_SNARE.playOnlyTo(player);
/*     */       }
/*     */     }
/*     */     
/*  91 */     return stack;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
/*     */   {
/*  96 */     String localText = String.format(Witchery.resource(func_77658_a() + ".tip"), new Object[] { Integer.valueOf(ExtendedPlayer.get(player).getBloodReserve()) });
/*     */     
/*  98 */     if (localText != null) {
/*  99 */       for (String s : localText.split("\n")) {
/* 100 */         if (!s.isEmpty()) {
/* 101 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isDrawn(EntityLivingBase player) {
/* 108 */     ItemStack heldItem = player.func_70694_bm();
/* 109 */     if ((heldItem != null) && (heldItem.func_77973_b() == this)) {
/* 110 */       return isDrawn(heldItem);
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isDrawn(ItemStack stack)
/*     */   {
/* 117 */     return isDrawn(stack.func_77978_p());
/*     */   }
/*     */   
/*     */   private boolean isDrawn(NBTTagCompound nbtItem) {
/* 121 */     boolean deployed = (nbtItem != null) && (nbtItem.func_74767_n("WITCBladeDeployed"));
/* 122 */     return deployed;
/*     */   }
/*     */   
/*     */   private void setDrawn(EntityPlayer player, ItemStack stack, boolean deployed) {
/* 126 */     setDrawn(player, stack, stack.func_77978_p(), deployed);
/*     */   }
/*     */   
/*     */   private void setDrawn(EntityPlayer player, ItemStack stack, NBTTagCompound nbtItem, boolean deployed) {
/* 130 */     if ((player != null) && (!player.field_70170_p.field_72995_K) && (nbtItem != null)) {
/* 131 */       nbtItem.func_74757_a("WITCBladeDeployed", deployed);
/* 132 */       if ((player instanceof EntityPlayerMP)) {
/* 133 */         ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemCaneSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */