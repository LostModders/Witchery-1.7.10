/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemBrewBucket extends com.emoniph.witchery.item.ItemBase
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   private net.minecraft.util.IIcon overlayIcon;
/*    */   
/*    */   public ItemBrewBucket()
/*    */   {
/* 14 */     func_77625_d(1);
/* 15 */     func_77656_e(0);
/* 16 */     this.registerWithCreativeTab = false;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int pass) {
/* 21 */     if (pass == 0) {
/* 22 */       net.minecraft.nbt.NBTTagCompound nbtRoot = stack.func_77978_p();
/* 23 */       int color = nbtRoot != null ? nbtRoot.func_74762_e("Color") : -16744448;
/* 24 */       return color;
/*    */     }
/* 26 */     return super.func_82790_a(stack, pass);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(net.minecraft.client.renderer.texture.IIconRegister iconRegister)
/*    */   {
/* 36 */     super.func_94581_a(iconRegister);
/* 37 */     this.overlayIcon = iconRegister.func_94245_a(func_111208_A() + "_overlay");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_77623_v() {
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public net.minecraft.util.IIcon func_77618_c(int damage, int pass) {
/* 46 */     if (pass == 0) {
/* 47 */       return this.overlayIcon;
/*    */     }
/* 49 */     return this.field_77791_bV;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean hasEffect(ItemStack par1ItemStack, int pass)
/*    */   {
/* 55 */     return pass == 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/ItemBrewBucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */