/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemGeneralContract extends ItemGeneral.SubItem
/*    */ {
/*    */   public ItemGeneralContract(int damageValue, String unlocalizedName)
/*    */   {
/* 14 */     super(damageValue, unlocalizedName);
/*    */   }
/*    */   
/*    */   public static boolean isBoundContract(ItemStack stack) {
/* 18 */     if (stack.func_77973_b() == Witchery.Items.GENERIC) {
/* 19 */       ItemGeneral.SubItem subItem = (ItemGeneral.SubItem)Witchery.Items.GENERIC.subItems.get(Math.max(stack.func_77960_j(), 0));
/* 20 */       if ((subItem instanceof ItemGeneralContract)) {
/* 21 */         return Witchery.Items.TAGLOCK_KIT.isTaglockPresent(stack, Integer.valueOf(1));
/*    */       }
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     return false;
/*    */   }
/*    */   
/*    */   public static EntityLivingBase getBoundEntity(World world, net.minecraft.entity.player.EntityPlayer player, ItemStack stack)
/*    */   {
/* 31 */     EntityLivingBase boundEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, player, stack, Integer.valueOf(1));
/* 32 */     return boundEntity;
/*    */   }
/*    */   
/*    */   public static ItemGeneralContract getContract(ItemStack stack) {
/* 36 */     ItemGeneral.SubItem subItem = (ItemGeneral.SubItem)Witchery.Items.GENERIC.subItems.get(stack.func_77960_j());
/* 37 */     if ((subItem instanceof ItemGeneralContract)) {
/* 38 */       return (ItemGeneralContract)subItem;
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public boolean activate(ItemStack stack, EntityLivingBase targetEntity)
/*    */   {
/* 45 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemGeneralContract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */