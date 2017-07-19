/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryCreativeTab;
/*    */ import com.emoniph.witchery.util.ItemUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBase
/*    */   extends Item
/*    */ {
/* 18 */   protected boolean registerWithCreativeTab = true;
/* 19 */   protected boolean autoGenerateTooltip = false;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Item func_77655_b(String itemName)
/*    */   {
/* 26 */     ItemUtil.registerItem(this, itemName);
/*    */     
/* 28 */     if (this.registerWithCreativeTab) {
/* 29 */       func_77637_a(WitcheryCreativeTab.INSTANCE);
/*    */     }
/*    */     
/* 32 */     return super.func_77655_b(itemName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
/*    */   {
/* 38 */     if (this.autoGenerateTooltip) {
/* 39 */       String localText = Witchery.resource(func_77658_a() + ".tip");
/* 40 */       if (localText != null) {
/* 41 */         for (String s : localText.split("\n")) {
/* 42 */           if (!s.isEmpty()) {
/* 43 */             list.add(s);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */