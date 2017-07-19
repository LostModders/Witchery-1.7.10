/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemEntityLocator extends ItemBase
/*    */ {
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   private IIcon[] icons;
/*    */   
/*    */   public ItemEntityLocator()
/*    */   {
/* 22 */     func_77656_e(0);
/* 23 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister iconRegister)
/*    */   {
/* 32 */     this.icons = new IIcon[33];
/* 33 */     for (int i = 0; i < this.icons.length; i++) {
/* 34 */       this.icons[i] = iconRegister.func_94245_a(func_111208_A() + i);
/*    */     }
/* 36 */     this.field_77791_bV = this.icons[0];
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
/*    */   {
/* 41 */     super.func_77624_a(stack, player, list, advTooltips);
/* 42 */     String entityID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
/* 43 */     if ((entityID != null) && (!entityID.isEmpty())) {
/* 44 */       list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { entityID }));
/*    */     } else {
/* 46 */       list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
/*    */     }
/*    */   }
/*    */   
/*    */   public IIcon func_77617_a(int damageValue)
/*    */   {
/* 52 */     if ((damageValue > 0) && (damageValue < this.icons.length)) {
/* 53 */       return this.icons[damageValue];
/*    */     }
/* 55 */     return this.icons[0];
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
/*    */   {
/* 61 */     item.func_77964_b(0);
/* 62 */     return super.onDroppedByPlayer(item, player);
/*    */   }
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity player, int inventorySlot, boolean isHeldItem)
/*    */   {
/* 67 */     if ((world != null) && (world.field_72995_K) && (world.func_72820_D() % 10L == 2L)) {
/* 68 */       if (Witchery.Items.TAGLOCK_KIT.isTaglockPresent(stack, Integer.valueOf(1))) {
/* 69 */         double d3 = 0.0D;
/* 70 */         EntityLivingBase target = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, stack, Integer.valueOf(1));
/* 71 */         if ((target != null) && ((target.field_71093_bK == player.field_71093_bK) || ((target.field_71093_bK == 0) && (player.field_71093_bK == com.emoniph.witchery.util.Config.instance().dimensionDreamID))))
/*    */         {
/*    */ 
/* 74 */           double playerX = player.field_70165_t;
/* 75 */           double playerZ = player.field_70161_v;
/* 76 */           double d4 = target.field_70165_t - playerX;
/* 77 */           double d5 = target.field_70161_v - playerZ;
/* 78 */           double playerYaw = player.field_70177_z;
/* 79 */           playerYaw %= 360.0D;
/* 80 */           d3 = -((playerYaw - 90.0D) * 3.141592653589793D / 180.0D - Math.atan2(d5, d4));
/*    */         } else {
/* 82 */           d3 = Math.random() * 3.141592653589793D * 2.0D;
/*    */         }
/*    */         
/*    */ 
/* 86 */         int SIZE = this.icons.length - 1;
/* 87 */         for (int i = (int)((d3 / 6.283185307179586D + 1.0D) * SIZE) % SIZE; i < 0; i = (i + SIZE) % SIZE) {}
/*    */         
/*    */ 
/*    */ 
/* 91 */         stack.func_77964_b(i + 1);
/*    */       } else {
/* 93 */         stack.func_77964_b(0);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemEntityLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */