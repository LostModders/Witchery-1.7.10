/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.ItemUtil;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSilverSword
/*    */   extends ItemSword
/*    */ {
/*    */   public ItemSilverSword()
/*    */   {
/* 61 */     super(Item.ToolMaterial.GOLD);
/* 62 */     func_77637_a(WitcheryCreativeTab.INSTANCE);
/*    */   }
/*    */   
/*    */   public Item func_77655_b(String itemName)
/*    */   {
/* 67 */     ItemUtil.registerItem(this, itemName);
/* 68 */     return super.func_77655_b(itemName);
/*    */   }
/*    */   
/*    */   public String func_150932_j()
/*    */   {
/* 73 */     return "SILVER";
/*    */   }
/*    */   
/*    */   public int func_77619_b()
/*    */   {
/* 78 */     return Item.ToolMaterial.IRON.func_77995_e();
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
/*    */   {
/* 83 */     String localText = Witchery.resource(func_77658_a() + ".tip");
/* 84 */     if (localText != null) {
/* 85 */       for (String s : localText.split("\n")) {
/* 86 */         if (!s.isEmpty()) {
/* 87 */           list.add(s);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemSilverSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */