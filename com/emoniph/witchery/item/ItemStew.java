/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemStew extends ItemFood
/*    */ {
/*    */   public ItemStew(int hunger, float saturation)
/*    */   {
/* 18 */     super(hunger, saturation, false);
/*    */   }
/*    */   
/*    */   public Item func_77655_b(String itemName)
/*    */   {
/* 23 */     com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
/* 24 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/* 25 */     return super.func_77655_b(itemName);
/*    */   }
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 30 */     super.func_77654_b(stack, world, player);
/* 31 */     ItemStack bowlStack = new ItemStack(Items.field_151054_z);
/* 32 */     if (!player.field_71071_by.func_70441_a(bowlStack)) {
/* 33 */       world.func_72838_d(new EntityItem(world, player.field_70165_t + 0.5D, player.field_70163_u + 1.5D, player.field_70161_v + 0.5D, bowlStack));
/*    */     }
/* 35 */     else if ((player instanceof EntityPlayerMP)) {
/* 36 */       ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*    */     }
/* 38 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemStew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */