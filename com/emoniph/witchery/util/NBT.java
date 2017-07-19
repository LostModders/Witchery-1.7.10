/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class NBT
/*    */ {
/*    */   public static NBTTagCompound get(ItemStack stack)
/*    */   {
/* 11 */     if (!stack.func_77942_o()) {
/* 12 */       stack.func_77982_d(new NBTTagCompound());
/*    */     }
/* 14 */     return stack.func_77978_p();
/*    */   }
/*    */   
/*    */   public static NBTTagCompound get(net.minecraft.entity.item.EntityItem entity) {
/* 18 */     return entity.getEntityData();
/*    */   }
/*    */   
/*    */   public static NBTTagCompound get(net.minecraft.entity.EntityLiving entity) {
/* 22 */     return entity.getEntityData();
/*    */   }
/*    */   
/*    */   public static NBTTagCompound get(EntityPlayer player) {
/* 26 */     NBTTagCompound nbtPlayer = player.getEntityData();
/* 27 */     NBTTagCompound nbtPersistant = nbtPlayer.func_74775_l("PlayerPersisted");
/* 28 */     if (!nbtPlayer.func_74764_b("PlayerPersisted")) {
/* 29 */       nbtPlayer.func_74782_a("PlayerPersisted", nbtPersistant);
/*    */     }
/* 31 */     return nbtPersistant;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/NBT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */