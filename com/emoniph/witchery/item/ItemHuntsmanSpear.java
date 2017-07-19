/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.ItemUtil;
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.IAttribute;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ 
/*    */ public class ItemHuntsmanSpear extends ItemSword
/*    */ {
/* 25 */   protected static final UUID UUID_KB = UUID.fromString("032f4b80-ad10-11e3-a5e2-0800200c9a66");
/*    */   
/*    */   private float effectiveWeaponDamage;
/*    */   private Item.ToolMaterial effectiveMaterial;
/*    */   private static final float BONUS_DAMAGE = 1.0F;
/*    */   
/*    */   public ItemHuntsmanSpear()
/*    */   {
/* 33 */     super(Item.ToolMaterial.WOOD);
/*    */     
/* 35 */     this.effectiveMaterial = Item.ToolMaterial.EMERALD;
/* 36 */     this.effectiveWeaponDamage = (4.0F + this.effectiveMaterial.func_78000_c() + 1.0F);
/*    */     
/* 38 */     func_77656_e(this.effectiveMaterial.func_77997_a());
/* 39 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Item func_77655_b(String itemName)
/*    */   {
/* 44 */     ItemUtil.registerItem(this, itemName);
/* 45 */     return super.func_77655_b(itemName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public EnumRarity func_77613_e(ItemStack stack)
/*    */   {
/* 51 */     return EnumRarity.epic;
/*    */   }
/*    */   
/*    */   public boolean func_77636_d(ItemStack stack)
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
/*    */   {
/* 61 */     list.add(Witchery.resource("item.witchery:huntsmanspear.tip"));
/*    */   }
/*    */   
/*    */   public Multimap func_111205_h()
/*    */   {
/* 66 */     Multimap multimap = HashMultimap.create();
/* 67 */     multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", this.effectiveWeaponDamage, 0));
/*    */     
/* 69 */     multimap.put(SharedMonsterAttributes.field_111266_c.func_111108_a(), new AttributeModifier(UUID_KB, "Knockback resist", 1.0D, 0));
/*    */     
/* 71 */     return multimap;
/*    */   }
/*    */   
/*    */   public boolean canHarvestBlock(Block par1Block, ItemStack stack)
/*    */   {
/* 76 */     return false;
/*    */   }
/*    */   
/*    */   public float func_150931_i()
/*    */   {
/* 81 */     return this.effectiveMaterial.func_78000_c();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemHuntsmanSpear.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */