/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemPoppetShelfCompass extends ItemBase
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   private IIcon[] icons;
/*    */   
/*    */   public ItemPoppetShelfCompass()
/*    */   {
/* 23 */     func_77656_e(0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister iconRegister)
/*    */   {
/* 33 */     this.icons = new IIcon[6];
/* 34 */     for (int i = 0; i < this.icons.length; i++) {
/* 35 */       this.icons[i] = iconRegister.func_94245_a(func_111208_A() + "_" + i);
/*    */     }
/* 37 */     this.field_77791_bV = this.icons[0];
/*    */   }
/*    */   
/*    */   public IIcon func_77617_a(int damageValue)
/*    */   {
/* 42 */     if ((damageValue > 0) && (damageValue < this.icons.length)) {
/* 43 */       return this.icons[damageValue];
/*    */     }
/* 45 */     return this.icons[0];
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isHeldItem)
/*    */   {
/* 51 */     if ((world.field_72995_K) && (world.field_73012_v.nextInt(20) == 0)) {
/* 52 */       List<TileEntity> list = world.field_147482_g;
/* 53 */       double closest = Double.MAX_VALUE;
/* 54 */       for (TileEntity tile : list) {
/* 55 */         if ((tile instanceof BlockPoppetShelf.TileEntityPoppetShelf)) {
/* 56 */           double distSq = entity.func_70092_e(tile.field_145851_c, entity.field_70163_u, tile.field_145849_e);
/* 57 */           if (distSq < closest) {
/* 58 */             closest = distSq;
/*    */           }
/*    */         }
/*    */       }
/* 62 */       if (closest < 64.0D) {
/* 63 */         stack.func_77964_b(5);
/* 64 */       } else if (closest < 256.0D) {
/* 65 */         stack.func_77964_b(4);
/* 66 */       } else if (closest < 1024.0D) {
/* 67 */         stack.func_77964_b(3);
/* 68 */       } else if (closest < 4096.0D) {
/* 69 */         stack.func_77964_b(2);
/* 70 */       } else if (closest < 16384.0D) {
/* 71 */         stack.func_77964_b(1);
/*    */       } else {
/* 73 */         stack.func_77964_b(0);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
/*    */   {
/* 80 */     item.func_77964_b(0);
/* 81 */     return super.onDroppedByPlayer(item, player);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemPoppetShelfCompass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */