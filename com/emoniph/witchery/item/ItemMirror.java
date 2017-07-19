/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.blocks.BlockMirror;
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ 
/*    */ public class ItemMirror extends ItemBase
/*    */ {
/*    */   public ItemMirror()
/*    */   {
/* 21 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
/*    */   {
/* 27 */     super.func_77624_a(stack, player, list, advTooltips);
/*    */     
/* 29 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/* 30 */     if ((nbtRoot == null) || (!nbtRoot.func_74767_n("DemonSlain"))) {
/* 31 */       list.add(Witchery.resource("item.witchery:mirror.tip.inhabited"));
/*    */     }
/* 33 */     else if (!Config.instance().isDebugging()) {
/* 34 */       list.add(Witchery.resource("item.witchery:mirror.tip.bridge"));
/*    */     }
/*    */     
/*    */ 
/* 38 */     if ((Config.instance().isDebugging()) && 
/* 39 */       (nbtRoot != null) && 
/* 40 */       (nbtRoot.func_74764_b("DimCoords"))) {
/* 41 */       int dim = nbtRoot.func_74762_e("Dimension");
/* 42 */       Coord coord = Coord.fromTagNBT(nbtRoot.func_74775_l("DimCoords"));
/*    */       
/* 44 */       WorldProvider prov = WorldProvider.func_76570_a(dim);
/* 45 */       String dimName = prov != null ? prov.func_80007_l() : Integer.valueOf(dim).toString();
/* 46 */       list.add(String.format(Witchery.resource("item.witchery:mirror.tip.bridgeplus"), new Object[] { dimName, Integer.valueOf(coord.x), Integer.valueOf(coord.y), Integer.valueOf(coord.z) }));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*    */   {
/* 56 */     if (world.field_72995_K) {
/* 57 */       return true;
/*    */     }
/* 59 */     int meta = 0;
/* 60 */     BlockMirror mirror = Witchery.Blocks.MIRROR;
/* 61 */     switch (side) {
/*    */     case 0: 
/*    */       break;
/*    */     case 1: 
/*    */       break;
/*    */     case 2: 
/* 67 */       meta = 0;
/* 68 */       z--;
/* 69 */       break;
/*    */     case 3: 
/* 71 */       meta = 1;
/* 72 */       z++;
/* 73 */       break;
/*    */     case 4: 
/* 75 */       meta = 2;
/* 76 */       x--;
/* 77 */       break;
/*    */     case 5: 
/* 79 */       meta = 3;
/* 80 */       x++;
/*    */     }
/*    */     
/* 83 */     if ((player.func_82247_a(x, y, z, side, stack)) && (player.func_82247_a(x, y - 1, z, side, stack))) {
/* 84 */       if ((world.func_147437_c(x, y, z)) && (world.func_147437_c(x, y - 1, z)))
/*    */       {
/* 86 */         world.func_147465_d(x, y, z, mirror, meta | 0x4, 3);
/* 87 */         if (world.func_147439_a(x, y, z) == mirror) {
/* 88 */           world.func_147465_d(x, y - 1, z, mirror, meta, 3);
/* 89 */           Witchery.Blocks.MIRROR.loadFromItem(stack, player, world, x, y, z);
/*    */         }
/*    */         
/* 92 */         stack.field_77994_a -= 1;
/* 93 */         return true;
/*    */       }
/* 95 */       return false;
/*    */     }
/*    */     
/* 98 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */