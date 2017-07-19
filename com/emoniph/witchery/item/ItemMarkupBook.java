/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.RegistryNamespaced;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMarkupBook
/*    */   extends ItemBase
/*    */ {
/*    */   private final int dialogID;
/*    */   private final int[] creativeMetaValues;
/*    */   
/*    */   public ItemMarkupBook(int dialogID)
/*    */   {
/* 29 */     this(dialogID, new int[] { 0 });
/*    */   }
/*    */   
/*    */   public ItemMarkupBook(int dialogID, int[] creativeMetaValues) {
/* 33 */     this.dialogID = dialogID;
/* 34 */     this.field_77787_bX = true;
/* 35 */     this.creativeMetaValues = creativeMetaValues;
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 40 */     int posX = MathHelper.func_76128_c(player.field_70165_t);
/* 41 */     int posY = MathHelper.func_76128_c(player.field_70163_u);
/* 42 */     int posZ = MathHelper.func_76128_c(player.field_70161_v);
/* 43 */     player.openGui(Witchery.instance, this.dialogID, world, posX, posY, posZ);
/* 44 */     return stack;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expandedTooltip)
/*    */   {
/* 49 */     String itemName = Item.field_150901_e.func_148750_c(stack.func_77973_b());
/* 50 */     for (String s : Witchery.resource("item." + itemName + ".tip").split("\n")) {
/* 51 */       if (!s.isEmpty()) {
/* 52 */         list.add(s);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item item, CreativeTabs creativeTabs, List itemList)
/*    */   {
/* 60 */     for (int meta : this.creativeMetaValues) {
/* 61 */       itemList.add(new ItemStack(this, 1, meta));
/*    */     }
/*    */   }
/*    */   
/*    */   public void onBookRead(ItemStack stack, World world, EntityPlayer player) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemMarkupBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */