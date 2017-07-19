/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryCreativeTab;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.entity.EntityBolt;
/*     */ import com.emoniph.witchery.integration.ModHookManager;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.InvUtil;
/*     */ import com.emoniph.witchery.util.ItemUtil;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import com.emoniph.witchery.util.TransformCreature;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.HashSet;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class ItemHandBow
/*     */   extends ItemBow
/*     */ {
/*     */   private static final int TICKS_TO_LOAD = 10;
/*     */   private static final String BOLT_TYPE_CURRENT = "WITCBoltTypeCurrent";
/*     */   private static final String BOLT_TYPE_PREFERRED = "WITCBoltTypePreferred";
/*     */   
/*     */   public ItemHandBow()
/*     */   {
/*  46 */     func_77656_e(768);
/*  47 */     func_77664_n();
/*  48 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public Item func_77655_b(String itemName)
/*     */   {
/*  53 */     ItemUtil.registerItem(this, itemName);
/*  54 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  59 */     ItemGeneral.SubItem loadedBoltType = getBoltType("WITCBoltTypeCurrent", stack);
/*  60 */     ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  61 */     if (((loadedBoltType != null) || (player.func_70093_af())) && (playerEx.getCreatureType() == TransformCreature.NONE)) {
/*  62 */       player.func_71008_a(stack, func_77626_a(stack));
/*     */     }
/*     */     
/*  65 */     return stack;
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  70 */     int elapsed = func_77626_a(stack) - count;
/*  71 */     ItemGeneral.SubItem loadedBoltType = getBoltType("WITCBoltTypeCurrent", stack);
/*  72 */     if ((player.func_70093_af()) && ((elapsed == 5) || (elapsed == 10) || (elapsed == 15))) {
/*  73 */       int boltTypesCount = getBoltTypesInInventory(player.field_71071_by, loadedBoltType);
/*  74 */       if (boltTypesCount > 0) {
/*  75 */         SoundEffect.WITCHERY_RANDOM_CLICK.playOnlyTo(player, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*  78 */     super.onUsingTick(stack, player, count);
/*     */   }
/*     */   
/*     */   private int getBoltTypesInInventory(IInventory inventory, ItemGeneral.SubItem typeToIgnore) {
/*  82 */     HashSet<ItemGeneral.BoltType> typesFound = new HashSet();
/*  83 */     for (int slot = 0; slot < inventory.func_70302_i_(); slot++) {
/*  84 */       ItemStack stack = inventory.func_70301_a(slot);
/*  85 */       ItemGeneral.BoltType boltType = ItemGeneral.BoltType.getBolt(stack);
/*  86 */       if ((boltType != null) && (boltType != typeToIgnore)) {
/*  87 */         typesFound.add(boltType);
/*     */       }
/*     */     }
/*  90 */     return typesFound.size();
/*     */   }
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int count)
/*     */   {
/*  95 */     int elapsed = func_77626_a(stack) - count;
/*  96 */     ItemGeneral.SubItem loadedBoltType = getBoltType("WITCBoltTypeCurrent", stack);
/*  97 */     if ((loadedBoltType != null) && ((!player.func_70093_af()) || (elapsed < 10))) {
/*  98 */       if (launchBolt(stack, world, player, elapsed >= TimeUtil.secsToTicks(1) ? 20 : 19)) {
/*  99 */         setBoltType("WITCBoltTypeCurrent", stack, null);
/*     */       }
/* 101 */     } else if ((player.func_70093_af()) && (elapsed >= 10)) {
/* 102 */       int boltTypesCount = getBoltTypesInInventory(player.field_71071_by, loadedBoltType);
/* 103 */       if ((loadedBoltType != null) && (boltTypesCount > 0)) {
/* 104 */         ItemGeneral.SubItem boltTypeToUse = getNextBoltType(loadedBoltType);
/* 105 */         if (!InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue)) {
/* 106 */           boltTypeToUse = null;
/* 107 */           ItemGeneral.SubItem currentBoltType = loadedBoltType;
/* 108 */           while ((currentBoltType = getNextBoltType(currentBoltType)) != loadedBoltType) {
/* 109 */             if (InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, currentBoltType.damageValue)) {
/* 110 */               boltTypeToUse = currentBoltType;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 115 */         if (boltTypeToUse != null) {
/* 116 */           setBoltType("WITCBoltTypeCurrent", stack, boltTypeToUse);
/* 117 */           setBoltType("WITCBoltTypePreferred", stack, boltTypeToUse);
/* 118 */           SoundEffect.WITCHERY_RANDOM_WINDUP.playOnlyTo(player, 1.0F, 1.0F);
/*     */           
/* 120 */           if (!player.field_71075_bZ.field_75098_d) {
/* 121 */             InvUtil.consumeItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue);
/* 122 */             ItemStack unloadedBolt = loadedBoltType.createStack();
/* 123 */             if (!player.field_71071_by.func_70441_a(unloadedBolt)) {
/* 124 */               EntityUtil.spawnEntityInWorld(world, new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, unloadedBolt));
/*     */             }
/*     */           }
/*     */         }
/* 128 */       } else if ((loadedBoltType == null) && (boltTypesCount > 0)) {
/* 129 */         ItemGeneral.SubItem preferredBoltType = getBoltType("WITCBoltTypePreferred", stack);
/* 130 */         if (preferredBoltType == null) {
/* 131 */           preferredBoltType = Witchery.Items.GENERIC.itemBoltStake;
/*     */         }
/* 133 */         ItemGeneral.SubItem boltTypeToUse = preferredBoltType;
/* 134 */         if (!InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue)) {
/* 135 */           boltTypeToUse = null;
/* 136 */           ItemGeneral.SubItem currentBoltType = preferredBoltType;
/* 137 */           while ((currentBoltType = getNextBoltType(currentBoltType)) != preferredBoltType) {
/* 138 */             if (InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, currentBoltType.damageValue)) {
/* 139 */               boltTypeToUse = currentBoltType;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 144 */         if (boltTypeToUse != null) {
/* 145 */           SoundEffect.WITCHERY_RANDOM_WINDUP.playOnlyTo(player, 1.0F, 1.0F);
/* 146 */           setBoltType("WITCBoltTypeCurrent", stack, boltTypeToUse);
/* 147 */           if (!player.field_71075_bZ.field_75098_d) {
/* 148 */             InvUtil.consumeItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean launchBolt(ItemStack stack, World world, EntityPlayer player, int ticks) {
/* 156 */     boolean isInfinite = (EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, stack) > 0) && (world.field_73012_v.nextDouble() < 0.25D);
/*     */     
/* 158 */     ItemGeneral.SubItem boltType = getBoltType("WITCBoltTypeCurrent", stack);
/*     */     
/* 160 */     if (boltType != null) {
/* 161 */       float f = ticks / 20.0F;
/* 162 */       f = (f * f + f * 2.0F) / 3.0F;
/*     */       
/* 164 */       if (f < 0.1D) {
/* 165 */         return true;
/*     */       }
/*     */       
/* 168 */       if (f > 1.0F) {
/* 169 */         f = 1.0F;
/*     */       }
/*     */       
/* 172 */       int boltID = 0;
/* 173 */       int boltCount = 1;
/* 174 */       float arcStart = 0.0F;
/* 175 */       float arcInc = 0.0F;
/* 176 */       float damage = 2.0F;
/*     */       
/* 178 */       if (boltType == Witchery.Items.GENERIC.itemBoltSilver) {
/* 179 */         boltID = 4;
/* 180 */       } else if (boltType == Witchery.Items.GENERIC.itemBoltHoly) {
/* 181 */         boltID = 3;
/* 182 */       } else if (boltType == Witchery.Items.GENERIC.itemBoltAntiMagic) {
/* 183 */         if (ItemHunterClothes.isFullSetWorn(player, false)) {
/* 184 */           boltID = 2;
/* 185 */           Witchery.modHooks.reducePowerLevels(player, 1.0F);
/*     */         } else {
/* 187 */           boltID = 1;
/*     */         }
/* 189 */       } else if (boltType == Witchery.Items.GENERIC.itemBoltSplitting) {
/* 190 */         boltCount = 3;
/* 191 */         arcStart = -20.0F;
/* 192 */         arcInc = 20.0F;
/* 193 */         damage = 1.0F;
/*     */       }
/*     */       
/* 196 */       for (int i = 0; i < boltCount; i++) {
/* 197 */         EntityBolt bolt = new EntityBolt(world, player, f * 2.0F, arcStart + i * arcInc);
/* 198 */         bolt.setShooter(player);
/* 199 */         bolt.setBoltType(boltID);
/* 200 */         bolt.setDamage(damage);
/* 201 */         bolt.canBePickedUp = ((isInfinite) || (player.field_71075_bZ.field_75098_d) ? 0 : 1);
/*     */         
/* 203 */         if (f == 1.0F) {
/* 204 */           bolt.setIsCritical(true);
/*     */         }
/*     */         
/* 207 */         if (boltType != Witchery.Items.GENERIC.itemBoltAntiMagic) {
/* 208 */           int powerBonus = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, stack);
/* 209 */           if (powerBonus > 0) {
/* 210 */             bolt.setDamage(bolt.getDamage() + powerBonus * 0.5D + 0.5D);
/*     */           }
/*     */           
/* 213 */           int knockbackBonus = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack);
/*     */           
/* 215 */           if (knockbackBonus > 0) {
/* 216 */             bolt.setKnockbackStrength(knockbackBonus);
/*     */           }
/*     */           
/* 219 */           if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, stack) > 0) {
/* 220 */             bolt.func_70015_d(100);
/*     */           }
/*     */         }
/*     */         
/* 224 */         EntityUtil.spawnEntityInWorld(world, bolt);
/* 225 */         EntityUtil.correctProjectileTrackerSync(world, bolt);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 230 */       stack.func_77972_a(2, player);
/*     */       
/* 232 */       world.func_72956_a(player, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*     */       
/* 234 */       if ((isInfinite) && (!world.field_72995_K)) {
/* 235 */         if ((player instanceof EntityPlayerMP)) {
/* 236 */           ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */         }
/*     */         
/* 239 */         return false;
/*     */       }
/*     */     }
/* 242 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 247 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack par1ItemStack)
/*     */   {
/* 252 */     return 72000;
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack)
/*     */   {
/* 257 */     return EnumAction.bow;
/*     */   }
/*     */   
/*     */   public int func_77619_b()
/*     */   {
/* 262 */     return 1;
/*     */   }
/*     */   
/*     */   public static ItemGeneral.BoltType getLoadedBoltType(ItemStack stack) {
/* 266 */     ItemGeneral.SubItem boltType = getBoltType("WITCBoltTypeCurrent", stack);
/* 267 */     if (boltType != null) {
/* 268 */       return (ItemGeneral.BoltType)boltType;
/*     */     }
/* 270 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static ItemGeneral.SubItem getBoltType(String key, ItemStack stack)
/*     */   {
/* 279 */     if (!stack.func_77942_o()) {
/* 280 */       return null;
/*     */     }
/* 282 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/* 283 */     int boltID = nbtRoot.func_74762_e(key);
/* 284 */     return intToBoltType(boltID);
/*     */   }
/*     */   
/*     */   private void setBoltType(String key, ItemStack stack, ItemGeneral.SubItem boltType) {
/* 288 */     if (!stack.func_77942_o()) {
/* 289 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/* 291 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/* 292 */     int boltID = boltTypeToInt(boltType);
/* 293 */     nbtRoot.func_74768_a(key, boltID);
/*     */   }
/*     */   
/* 296 */   private static ItemGeneral.SubItem[] BOLT_TYPES = null;
/*     */   
/*     */   private static ItemGeneral.SubItem[] getBoltTypes() {
/* 299 */     if (BOLT_TYPES == null) {
/* 300 */       BOLT_TYPES = new ItemGeneral.SubItem[] { Witchery.Items.GENERIC.itemBoltStake, Witchery.Items.GENERIC.itemBoltAntiMagic, Witchery.Items.GENERIC.itemBoltHoly, Witchery.Items.GENERIC.itemBoltSplitting, Witchery.Items.GENERIC.itemBoltSilver };
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 308 */     return BOLT_TYPES;
/*     */   }
/*     */   
/*     */   private static ItemGeneral.SubItem intToBoltType(int boltID) {
/* 312 */     if ((boltID > 0) && (boltID <= getBoltTypes().length)) {
/* 313 */       return BOLT_TYPES[(boltID - 1)];
/*     */     }
/* 315 */     return null;
/*     */   }
/*     */   
/*     */   private int boltTypeToInt(ItemGeneral.SubItem boltType)
/*     */   {
/* 320 */     for (int i = 0; i < getBoltTypes().length; i++) {
/* 321 */       if (getBoltTypes()[i] == boltType) {
/* 322 */         return i + 1;
/*     */       }
/*     */     }
/* 325 */     return 0;
/*     */   }
/*     */   
/*     */ 
/* 329 */   private ItemGeneral.SubItem getNextBoltType(ItemGeneral.SubItem boltType) { return intToBoltType(getNextBoltTypeID(boltTypeToInt(boltType))); }
/*     */   
/*     */   private int getNextBoltTypeID(int boltID) {
/*     */     
/* 333 */     if (boltID > getBoltTypes().length) {
/* 334 */       boltID = 1;
/*     */     }
/* 336 */     return boltID;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/* 342 */     this.field_77791_bV = par1IconRegister.func_94245_a(func_111208_A());
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_94599_c(int par1)
/*     */   {
/* 348 */     return this.field_77791_bV;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemHandBow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */