/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.ItemUtil;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ 
/*    */ public class ItemKobolditePickaxe extends ItemPickaxe
/*    */ {
/*    */   public ItemKobolditePickaxe()
/*    */   {
/* 11 */     super(net.minecraft.item.Item.ToolMaterial.EMERALD);
/* 12 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public net.minecraft.item.Item func_77655_b(String itemName)
/*    */   {
/* 17 */     ItemUtil.registerItem(this, itemName);
/* 18 */     return super.func_77655_b(itemName);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemKobolditePickaxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */