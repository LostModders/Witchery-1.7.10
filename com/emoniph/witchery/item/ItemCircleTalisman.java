/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ItemCircleTalisman extends ItemBase
/*     */ {
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item0;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item1;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item2;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item3;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item4;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item5;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item6;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item7;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item8;
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   net.minecraft.util.IIcon item9;
/*     */   
/*     */   public ItemCircleTalisman() {
/*  29 */     func_77625_d(16);
/*  30 */     func_77656_e(0);
/*  31 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  37 */     return net.minecraft.item.EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public String func_77667_c(ItemStack itemStack)
/*     */   {
/*  42 */     Integer damage = Integer.valueOf(itemStack.func_77960_j());
/*  43 */     return func_77658_a() + "." + damage.toString();
/*     */   }
/*     */   
/*     */   public String func_77657_g(ItemStack par1ItemStack)
/*     */   {
/*  48 */     return super.func_77667_c(par1ItemStack);
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack itemStack)
/*     */   {
/*  53 */     String localizedName = super.func_77653_i(itemStack);
/*     */     
/*  55 */     int damage = itemStack.func_77960_j();
/*  56 */     return damage > 0 ? String.format("%s (%s)", new Object[] { localizedName, getChalkDisplayName(damage) }) : localizedName;
/*     */   }
/*     */   
/*     */   private String getChalkDisplayName(int damage) {
/*  60 */     int small = damage & 0x7;
/*  61 */     int medium = damage >>> 3 & 0x7;
/*  62 */     int large = damage >>> 6 & 0x7;
/*     */     
/*  64 */     StringBuilder result = new StringBuilder();
/*     */     
/*  66 */     if (small > 0) {
/*  67 */       result.append(net.minecraft.util.StatCollector.func_74838_a("circletalisman.small." + Integer.valueOf(small).toString()));
/*  68 */       result.append(", ");
/*     */     }
/*     */     
/*  71 */     if (medium > 0) {
/*  72 */       result.append(net.minecraft.util.StatCollector.func_74838_a("circletalisman.medium." + Integer.valueOf(medium).toString()));
/*  73 */       result.append(", ");
/*     */     }
/*     */     
/*  76 */     if (large > 0) {
/*  77 */       result.append(net.minecraft.util.StatCollector.func_74838_a("circletalisman.large." + Integer.valueOf(large).toString()));
/*  78 */       result.append(", ");
/*     */     }
/*  80 */     if (result.length() > 0) {
/*  81 */       result.setLength(result.length() - 2);
/*     */     }
/*  83 */     return result.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_94581_a(net.minecraft.client.renderer.texture.IIconRegister iconRegister)
/*     */   {
/* 110 */     super.func_94581_a(iconRegister);
/* 111 */     this.item0 = this.field_77791_bV;
/* 112 */     this.item1 = iconRegister.func_94245_a(func_111208_A() + ".1");
/* 113 */     this.item2 = iconRegister.func_94245_a(func_111208_A() + ".2");
/* 114 */     this.item3 = iconRegister.func_94245_a(func_111208_A() + ".3");
/* 115 */     this.item4 = iconRegister.func_94245_a(func_111208_A() + ".4");
/* 116 */     this.item5 = iconRegister.func_94245_a(func_111208_A() + ".5");
/* 117 */     this.item6 = iconRegister.func_94245_a(func_111208_A() + ".6");
/* 118 */     this.item7 = iconRegister.func_94245_a(func_111208_A() + ".7");
/* 119 */     this.item8 = iconRegister.func_94245_a(func_111208_A() + ".8");
/* 120 */     this.item9 = iconRegister.func_94245_a(func_111208_A() + ".9");
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public net.minecraft.util.IIcon func_77617_a(int damage)
/*     */   {
/* 126 */     int small = damage & 0x7;
/* 127 */     int medium = damage >>> 3 & 0x7;
/* 128 */     int large = damage >>> 6 & 0x7;
/*     */     
/* 130 */     switch (medium > 0 ? medium + 3 : large > 0 ? large + 6 : small) {
/*     */     case 0: 
/*     */     default: 
/* 133 */       return this.item0;
/*     */     case 1: 
/* 135 */       return this.item1;
/*     */     case 2: 
/* 137 */       return this.item2;
/*     */     case 3: 
/* 139 */       return this.item3;
/*     */     case 4: 
/* 141 */       return this.item4;
/*     */     case 5: 
/* 143 */       return this.item5;
/*     */     case 6: 
/* 145 */       return this.item6;
/*     */     case 7: 
/* 147 */       return this.item7;
/*     */     case 8: 
/* 149 */       return this.item8;
/*     */     }
/* 151 */     return this.item9;
/*     */   }
/*     */   
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_150895_a(net.minecraft.item.Item itemID, net.minecraft.creativetab.CreativeTabs tab, java.util.List itemList)
/*     */   {
/* 158 */     itemList.add(new ItemStack(itemID, 1, 0));
/* 159 */     itemList.add(new ItemStack(itemID, 1, 1));
/* 160 */     itemList.add(new ItemStack(itemID, 1, 2));
/* 161 */     itemList.add(new ItemStack(itemID, 1, 3));
/* 162 */     itemList.add(new ItemStack(itemID, 1, 8));
/* 163 */     itemList.add(new ItemStack(itemID, 1, 16));
/* 164 */     itemList.add(new ItemStack(itemID, 1, 24));
/* 165 */     itemList.add(new ItemStack(itemID, 1, 64));
/* 166 */     itemList.add(new ItemStack(itemID, 1, 128));
/* 167 */     itemList.add(new ItemStack(itemID, 1, 192));
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, net.minecraft.world.World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 172 */     if (((com.emoniph.witchery.util.BlockSide.TOP.isEqual(side)) && (world.func_147439_a(posX, posY, posZ) == com.emoniph.witchery.Witchery.Blocks.CIRCLE)) || (com.emoniph.witchery.Witchery.Blocks.CIRCLE.func_149718_j(world, posX, posY + 1, posZ))) {
/* 173 */       int damage = itemstack.func_77960_j();
/* 174 */       if (damage > 0) {
/* 175 */         if (!world.field_72995_K) {
/* 176 */           int a = damage & 0x7;
/* 177 */           int b = damage >>> 3 & 0x7;
/* 178 */           int c = damage >>> 6 & 0x7;
/* 179 */           int _ = 0;
/*     */           
/* 181 */           int[][] PATTERN = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, c, c, c, c, c, c, c, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, c, 0, 0, 0, 0, 0, 0, 0, c, 0, 0, 0, 0 }, { 0, 0, 0, c, 0, 0, b, b, b, b, b, 0, 0, c, 0, 0, 0 }, { 0, 0, c, 0, 0, b, 0, 0, 0, 0, 0, b, 0, 0, c, 0, 0 }, { 0, c, 0, 0, b, 0, 0, a, a, a, 0, 0, b, 0, 0, c, 0 }, { 0, c, 0, b, 0, 0, a, 0, 0, 0, a, 0, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, a, 0, 0, 0, 0, 0, a, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, a, 0, 0, 4, 0, 0, a, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, a, 0, 0, 0, 0, 0, a, 0, b, 0, c, 0 }, { 0, c, 0, b, 0, 0, a, 0, 0, 0, a, 0, 0, b, 0, c, 0 }, { 0, c, 0, 0, b, 0, 0, a, a, a, 0, 0, b, 0, 0, c, 0 }, { 0, 0, c, 0, 0, b, 0, 0, 0, 0, 0, b, 0, 0, c, 0, 0 }, { 0, 0, 0, c, 0, 0, b, b, b, b, b, 0, 0, c, 0, 0, 0 }, { 0, 0, 0, 0, c, 0, 0, 0, 0, 0, 0, 0, c, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, c, c, c, c, c, c, c, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 200 */           int y = world.func_147439_a(posX, posY, posZ) == com.emoniph.witchery.Witchery.Blocks.CIRCLE ? posY : posY + 1;
/*     */           
/* 202 */           int pass = 0;
/* 203 */           boolean fail = false;
/* 204 */           while ((pass < 2) && (!fail)) {
/* 205 */             int offsetZ = (PATTERN.length - 1) / 2;
/* 206 */             for (int z = 0; z < PATTERN.length - 1; z++) {
/* 207 */               int worldZ = posZ - offsetZ + z;
/* 208 */               int offsetX = (PATTERN[z].length - 1) / 2;
/* 209 */               for (int x = 0; x < PATTERN[z].length; x++) {
/* 210 */                 int worldX = posX - offsetX + x;
/* 211 */                 int item = PATTERN[(PATTERN.length - 1 - z)][x];
/* 212 */                 net.minecraft.block.material.Material material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
/* 213 */                 boolean solidBlock = (material != null) && ((material.func_76218_k()) || (material.func_76220_a()));
/* 214 */                 switch (item) {
/*     */                 case 1: 
/* 216 */                   if ((!solidBlock) && (com.emoniph.witchery.Witchery.Blocks.GLYPH_RITUAL.func_149718_j(world, worldX, y, worldZ))) {
/* 217 */                     if (pass == 1) {
/* 218 */                       world.func_147465_d(worldX, y, worldZ, com.emoniph.witchery.Witchery.Blocks.GLYPH_RITUAL, world.field_73012_v.nextInt(12), 3);
/*     */                     }
/*     */                   } else {
/* 221 */                     fail = true;
/*     */                   }
/* 223 */                   break;
/*     */                 case 2: 
/* 225 */                   if ((!solidBlock) && (com.emoniph.witchery.Witchery.Blocks.GLYPH_OTHERWHERE.func_149718_j(world, worldX, y, worldZ))) {
/* 226 */                     if (pass == 1) {
/* 227 */                       world.func_147465_d(worldX, y, worldZ, com.emoniph.witchery.Witchery.Blocks.GLYPH_OTHERWHERE, world.field_73012_v.nextInt(12), 3);
/*     */                     }
/*     */                   } else {
/* 230 */                     fail = true;
/*     */                   }
/* 232 */                   break;
/*     */                 case 3: 
/* 234 */                   if ((!solidBlock) && (com.emoniph.witchery.Witchery.Blocks.GLYPH_INFERNAL.func_149718_j(world, worldX, y, worldZ))) {
/* 235 */                     if (pass == 1) {
/* 236 */                       world.func_147465_d(worldX, y, worldZ, com.emoniph.witchery.Witchery.Blocks.GLYPH_INFERNAL, world.field_73012_v.nextInt(12), 3);
/*     */                     }
/*     */                   } else {
/* 239 */                     fail = true;
/*     */                   }
/* 241 */                   break;
/*     */                 case 4: 
/* 243 */                   if (y != posY) {
/* 244 */                     if (com.emoniph.witchery.Witchery.Blocks.CIRCLE.func_149718_j(world, worldX, y, worldZ)) {
/* 245 */                       if (pass == 1) {
/* 246 */                         world.func_147449_b(worldX, y, worldZ, com.emoniph.witchery.Witchery.Blocks.CIRCLE);
/*     */                       }
/*     */                     } else {
/* 249 */                       fail = true;
/*     */                     }
/*     */                   }
/*     */                   
/*     */                   break;
/*     */                 }
/*     */                 
/*     */                 
/* 257 */                 if (pass == 1) {
/* 258 */                   com.emoniph.witchery.util.ParticleEffect.SMOKE.send(com.emoniph.witchery.util.SoundEffect.NONE, world, worldX, posY + 1, worldZ, 0.5D, 1.0D, 16);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 265 */             pass++;
/*     */           }
/* 267 */           if (fail) {
/* 268 */             world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)world.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */           }
/* 270 */           else if (itemstack.field_77994_a > 1) {
/* 271 */             ItemStack newStack = new ItemStack(this);
/* 272 */             if (!player.field_71071_by.func_70441_a(newStack)) {
/* 273 */               world.func_72838_d(new net.minecraft.entity.item.EntityItem(world, player.field_70165_t + 0.5D, player.field_70163_u + 1.5D, player.field_70161_v + 0.5D, newStack));
/* 274 */             } else if ((player instanceof net.minecraft.entity.player.EntityPlayerMP)) {
/* 275 */               ((net.minecraft.entity.player.EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/* 277 */             itemstack.field_77994_a -= 1;
/* 278 */             if (itemstack.field_77994_a <= 0) {
/* 279 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */             }
/*     */           } else {
/* 282 */             itemstack.func_77964_b(0);
/*     */           }
/*     */         }
/*     */         else {
/* 286 */           world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)world.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */         }
/*     */       }
/*     */       
/* 290 */       return true;
/*     */     }
/* 292 */     com.emoniph.witchery.util.SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/* 293 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemCircleTalisman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */