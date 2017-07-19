/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryCreativeTab;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ 
/*     */ public class ItemBoline extends ItemSword
/*     */ {
/*  34 */   public static final Block[] blocksEffectiveAgainst = { Blocks.field_150344_f, Blocks.field_150342_X, Blocks.field_150344_f, Blocks.field_150486_ae, Blocks.field_150333_U, Blocks.field_150423_aK, Blocks.field_150428_aP };
/*     */   
/*     */   private float effectiveWeaponDamage;
/*     */   
/*     */   public ItemBoline()
/*     */   {
/*  40 */     super(Item.ToolMaterial.IRON);
/*  41 */     this.effectiveWeaponDamage = (4.0F + Item.ToolMaterial.WOOD.func_78000_c());
/*  42 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*     */   }
/*     */   
/*     */   public Item func_77655_b(String itemName)
/*     */   {
/*  47 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/*  48 */     return super.func_77655_b(itemName);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/*  54 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
/*     */   {
/*  59 */     String localText = Witchery.resource("item.witchery:boline.tip");
/*  60 */     if (localText != null) {
/*  61 */       for (String s : localText.split("\n")) {
/*  62 */         if (!s.isEmpty()) {
/*  63 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Multimap func_111205_h()
/*     */   {
/*  71 */     Multimap multimap = HashMultimap.create();
/*  72 */     multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", this.effectiveWeaponDamage, 0));
/*     */     
/*  74 */     return multimap;
/*     */   }
/*     */   
/*     */   public float func_150931_i()
/*     */   {
/*  79 */     return Item.ToolMaterial.WOOD.func_78000_c();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_150894_a(ItemStack stack, World world, Block block, int posX, int posY, int posZ, EntityLivingBase entity)
/*     */   {
/*  85 */     if ((block != null) && (block != Blocks.field_150362_t) && (block != Blocks.field_150321_G) && (block != Blocks.field_150329_H) && (block != Blocks.field_150395_bd) && (block != Blocks.field_150473_bD) && (!(block instanceof IShearable)))
/*     */     {
/*     */ 
/*  88 */       if (block.func_149712_f(world, posX, posY, posZ) != 0.0F) {
/*  89 */         stack.func_77972_a(2, entity);
/*     */       }
/*     */     }
/*     */     
/*  93 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canHarvestBlock(Block par1Block, ItemStack stack)
/*     */   {
/*  98 */     return (par1Block == Witchery.Blocks.WEB) || (par1Block == Blocks.field_150321_G) || (par1Block == Blocks.field_150488_af) || (par1Block == Blocks.field_150473_bD);
/*     */   }
/*     */   
/*     */   public float func_150893_a(ItemStack stack, Block block)
/*     */   {
/* 103 */     if ((block == Witchery.Blocks.WEB) || (block == Blocks.field_150321_G) || (block == Blocks.field_150362_t))
/* 104 */       return 15.0F;
/* 105 */     if ((block == Blocks.field_150325_L) || (block == Witchery.Blocks.TRAPPED_PLANT)) {
/* 106 */       return 5.0F;
/*     */     }
/* 108 */     return super.func_150893_a(stack, block);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_111207_a(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
/*     */   {
/* 114 */     if (entity.field_70170_p.field_72995_K) {
/* 115 */       return false;
/*     */     }
/* 117 */     if ((entity instanceof IShearable)) {
/* 118 */       IShearable target = (IShearable)entity;
/* 119 */       if (target.isShearable(itemstack, entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {
/* 120 */         ArrayList<ItemStack> drops = target.onSheared(itemstack, entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, itemstack));
/*     */         
/*     */ 
/* 123 */         Random rand = new Random();
/* 124 */         for (ItemStack stack : drops) {
/* 125 */           EntityItem ent = entity.func_70099_a(stack, 1.0F);
/* 126 */           ent.field_70181_x += rand.nextFloat() * 0.05F;
/* 127 */           ent.field_70159_w += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/* 128 */           ent.field_70179_y += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
/*     */         }
/* 130 */         itemstack.func_77972_a(1, entity);
/*     */       }
/* 132 */       return true;
/*     */     }
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 139 */     if (player.field_70170_p.field_72995_K) {
/* 140 */       return false;
/*     */     }
/*     */     
/* 143 */     World world = player.field_70170_p;
/* 144 */     Block block = BlockUtil.getBlock(world, x, y, z);
/* 145 */     if (block == null)
/* 146 */       return false;
/* 147 */     if (block == Blocks.field_150321_G) {
/* 148 */       world.func_147468_f(x, y, z);
/* 149 */       world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + y, 0.5D + z, new ItemStack(block)));
/* 150 */       func_150894_a(itemstack, world, block, x, y, z, player);
/* 151 */       if (itemstack.field_77994_a == 0) {
/* 152 */         player.func_71028_bD();
/*     */       }
/* 154 */       return true; }
/* 155 */     if (block == Witchery.Blocks.TRAPPED_PLANT) {
/* 156 */       int meta = world.func_72805_g(x, y, z);
/* 157 */       world.func_147468_f(x, y, z);
/* 158 */       world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + y, 0.5D + z, new ItemStack(block, 1, meta)));
/* 159 */       func_150894_a(itemstack, world, block, x, y, z, player);
/* 160 */       if (itemstack.field_77994_a == 0) {
/* 161 */         player.func_71028_bD();
/*     */       }
/* 163 */       return true; }
/* 164 */     if (block == Witchery.Blocks.BLOOD_ROSE) {
/* 165 */       int meta = world.func_72805_g(x, y, z);
/* 166 */       world.func_147468_f(x, y, z);
/* 167 */       world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + y, 0.5D + z, new ItemStack(block, 1, meta)));
/* 168 */       func_150894_a(itemstack, world, block, x, y, z, player);
/* 169 */       if (itemstack.field_77994_a == 0) {
/* 170 */         player.func_71028_bD();
/*     */       }
/* 172 */       return true; }
/* 173 */     if ((block instanceof IShearable)) {
/* 174 */       IShearable target = (IShearable)block;
/* 175 */       if (target.isShearable(itemstack, player.field_70170_p, x, y, z)) {
/* 176 */         ArrayList<ItemStack> drops = target.onSheared(itemstack, player.field_70170_p, x, y, z, EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, itemstack));
/*     */         
/* 178 */         Random rand = new Random();
/*     */         
/* 180 */         for (ItemStack stack : drops) {
/* 181 */           float f = 0.7F;
/* 182 */           double d = rand.nextFloat() * f + (1.0F - f) * 0.5D;
/* 183 */           double d1 = rand.nextFloat() * f + (1.0F - f) * 0.5D;
/* 184 */           double d2 = rand.nextFloat() * f + (1.0F - f) * 0.5D;
/* 185 */           EntityItem entityitem = new EntityItem(player.field_70170_p, x + d, y + d1, z + d2, stack);
/* 186 */           entityitem.field_145804_b = 10;
/* 187 */           player.field_70170_p.func_72838_d(entityitem);
/*     */         }
/*     */         
/* 190 */         itemstack.func_77972_a(1, player);
/*     */       }
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBoline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */