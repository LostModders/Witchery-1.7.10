/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraftforge.common.BiomeDictionary;
/*    */ import net.minecraftforge.common.BiomeDictionary.Type;
/*    */ 
/*    */ public class ItemBook extends ItemBase
/*    */ {
/*    */   static final String CURRENT_PAGE_KEY = "CurrentPage";
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 21 */     int posX = MathHelper.func_76128_c(player.field_70165_t);
/* 22 */     int posY = MathHelper.func_76128_c(player.field_70163_u);
/* 23 */     int posZ = MathHelper.func_76128_c(player.field_70161_v);
/* 24 */     player.openGui(Witchery.instance, 6, world, posX, posY, posZ);
/* 25 */     return stack;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expandedTooltip)
/*    */   {
/* 30 */     list.add(String.format(Witchery.resource("witchery.biomebook.currentpage"), new Object[] { getSelectedBiome(getSelectedBiome(stack, 1000)).field_76791_y }));
/*    */     
/* 32 */     list.add("");
/* 33 */     for (String s : Witchery.resource("item.witchery:biomebook2.tip").split("\n")) {
/* 34 */       if (!s.isEmpty()) {
/* 35 */         list.add(s);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public static int getSelectedBiome(ItemStack stack, int maxPages)
/*    */   {
/* 43 */     NBTTagCompound stackCompound = stack.func_77978_p();
/* 44 */     if ((stackCompound != null) && (stackCompound.func_74764_b("CurrentPage"))) {
/* 45 */       return Math.min(Math.max(stackCompound.func_74762_e("CurrentPage"), 0), Math.max(maxPages, 1) - 1);
/*    */     }
/* 47 */     return 0;
/*    */   }
/*    */   
/* 50 */   public static final BiomeDictionary.Type[] BIOME_TYPES = { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.MAGICAL };
/*    */   
/*    */ 
/*    */   public static BiomeGenBase getSelectedBiome(int page)
/*    */   {
/* 55 */     ArrayList<BiomeGenBase> biomes = new ArrayList();
/* 56 */     int i = 0;
/* 57 */     for (BiomeDictionary.Type biomeType : BIOME_TYPES) {
/* 58 */       BiomeGenBase[] biomesInType = BiomeDictionary.getBiomesForType(biomeType);
/* 59 */       for (int j = 0; j < biomesInType.length; j++) {
/* 60 */         if (i++ == page) {
/* 61 */           return biomesInType[j];
/*    */         }
/*    */       }
/*    */     }
/* 65 */     return BiomeGenBase.field_76772_c;
/*    */   }
/*    */   
/*    */   public ItemStack getContainerItem(ItemStack stack)
/*    */   {
/* 70 */     if (!hasContainerItem(stack))
/*    */     {
/* 72 */       return null;
/*    */     }
/* 74 */     return stack.func_77946_l();
/*    */   }
/*    */   
/*    */   public static void setSelectedBiome(ItemStack itemstack, int pageIndex) {
/* 78 */     if (itemstack.func_77978_p() == null) {
/* 79 */       itemstack.func_77982_d(new NBTTagCompound());
/*    */     }
/* 81 */     itemstack.func_77978_p().func_74768_a("CurrentPage", pageIndex);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */