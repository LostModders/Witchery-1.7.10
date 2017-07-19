/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import cpw.mods.fml.common.registry.GameRegistry;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class ItemBrewFuel extends ItemBase implements cpw.mods.fml.common.IFuelHandler
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected IIcon itemIconOverlay;
/*    */   
/*    */   public ItemBrewFuel()
/*    */   {
/* 20 */     func_77625_d(64);
/* 21 */     func_77656_e(0);
/* 22 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */   public net.minecraft.item.Item func_77655_b(String itemName)
/*    */   {
/* 27 */     GameRegistry.registerFuelHandler(this);
/* 28 */     return super.func_77655_b(itemName);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean hasEffect(ItemStack stack, int pass)
/*    */   {
/* 34 */     return pass == 0;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_77623_v()
/*    */   {
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon getIcon(ItemStack stack, int pass)
/*    */   {
/* 46 */     if (pass == 0) {
/* 47 */       return this.itemIconOverlay;
/*    */     }
/* 49 */     return this.field_77791_bV;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister iconRegister)
/*    */   {
/* 59 */     super.func_94581_a(iconRegister);
/* 60 */     this.itemIconOverlay = iconRegister.func_94245_a("witchery:brew_overlay");
/*    */   }
/*    */   
/* 63 */   private static final int[] COLORS = { 16754270, 16748088, 16740620, 14702848 };
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int pass)
/*    */   {
/* 68 */     if (pass == 0) {
/* 69 */       int color = Math.min(stack.func_77960_j(), COLORS.length);
/* 70 */       return COLORS[color];
/*    */     }
/* 72 */     return super.func_82790_a(stack, pass);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expanded)
/*    */   {
/* 79 */     String localText = Witchery.resource("item.witchery:brew.fuel." + Math.min(stack.func_77960_j(), BURN_TIMES.length));
/*    */     
/* 81 */     if (localText != null) {
/* 82 */       for (String s : localText.split("\n")) {
/* 83 */         if (!s.isEmpty()) {
/* 84 */           list.add(s);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/* 90 */   private static final int[] BURN_TIMES = { 2400, 5000, 10000, 50000 };
/*    */   
/*    */   public int getBurnTime(ItemStack fuel)
/*    */   {
/* 94 */     if (fuel.func_77973_b() == this) {
/* 95 */       int burnTime = BURN_TIMES[Math.min(fuel.func_77960_j(), BURN_TIMES.length)];
/* 96 */       return burnTime;
/*    */     }
/* 98 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBrewFuel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */