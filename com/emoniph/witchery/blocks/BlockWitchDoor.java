/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class BlockWitchDoor extends BlockDoor
/*     */ {
/*     */   public BlockWitchDoor()
/*     */   {
/*  26 */     super(net.minecraft.block.material.Material.field_151575_d);
/*  27 */     disableStatsThunk();
/*  28 */     func_149672_a(Block.field_149766_f);
/*     */   }
/*     */   
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  33 */     com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
/*  34 */     return super.func_149663_c(blockName);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int posX, int posY, int posZ, Block block, int metadata)
/*     */   {
/*  39 */     if (block == Witchery.Blocks.DOOR_ALDER) {
/*  40 */       int i1 = func_150012_g(world, posX, posY, posZ);
/*  41 */       if ((i1 & 0x8) != 0) {
/*  42 */         posY--;
/*     */       }
/*  44 */       notifyNeighborsOfBlockChange(world, posX, posY, posZ);
/*     */     }
/*  46 */     super.func_149749_a(world, posX, posY, posZ, block, metadata);
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/*  51 */     if (this == Witchery.Blocks.DOOR_ALDER) {
/*  52 */       boolean result = super.func_149727_a(world, posX, posY, posZ, player, par6, par7, par8, par9);
/*  53 */       int i1 = func_150012_g(world, posX, posY, posZ);
/*  54 */       if ((i1 & 0x8) != 0) {
/*  55 */         posY--;
/*     */       }
/*  57 */       notifyNeighborsOfBlockChange(world, posX, posY, posZ);
/*  58 */       return result;
/*     */     }
/*  60 */     if (hasKeyForDoor(world, posX, posY, posZ, player)) {
/*  61 */       return super.func_149727_a(world, posX, posY, posZ, player, par6, par7, par8, par9);
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer player)
/*     */   {
/*  70 */     if (this == Witchery.Blocks.DOOR_ROWAN) { ItemStack stack;
/*     */       ItemStack stack;
/*  72 */       if (hasKeyForDoor(world, posX, posY, posZ, player)) {
/*  73 */         stack = Witchery.Items.GENERIC.itemDoorRowan.createStack();
/*     */       } else {
/*  75 */         stack = new ItemStack(net.minecraft.init.Items.field_151055_y, 24);
/*     */       }
/*     */       
/*  78 */       float f = 0.7F;
/*  79 */       double d0 = world.field_73012_v.nextFloat() * 0.7F + 0.15000000596046448D;
/*  80 */       double d1 = world.field_73012_v.nextFloat() * 0.7F + 0.15000000596046448D;
/*  81 */       double d2 = world.field_73012_v.nextFloat() * 0.7F + 0.15000000596046448D;
/*  82 */       EntityItem entityitem = new EntityItem(world, posX + d0, posY + d1, posZ + d2, stack);
/*  83 */       entityitem.field_145804_b = 10;
/*  84 */       if (!world.field_72995_K) {
/*  85 */         world.func_72838_d(entityitem);
/*     */       }
/*     */     }
/*     */     
/*  89 */     super.func_149681_a(world, posX, posY, posZ, par5, player);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/*  94 */     if (this == Witchery.Blocks.DOOR_ROWAN) {
/*  95 */       ArrayList<ItemStack> drops = new ArrayList();
/*  96 */       return drops;
/*     */     }
/*  98 */     return super.getDrops(world, x, y, z, metadata, fortune);
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean hasKeyForDoor(World world, int posX, int posY, int posZ, EntityPlayer player)
/*     */   {
/* 104 */     for (int slot = 0; slot < player.field_71071_by.field_70462_a.length; slot++) {
/* 105 */       ItemStack itemstack = player.field_71071_by.field_70462_a[slot];
/* 106 */       if (itemstack != null) {
/* 107 */         NBTTagCompound nbtTag = itemstack.func_77978_p();
/* 108 */         if ((itemstack != null) && (nbtTag != null)) {
/* 109 */           int i1 = func_150012_g(world, posX, posY, posZ);
/* 110 */           if (Witchery.Items.GENERIC.itemDoorKey.isMatch(itemstack)) {
/* 111 */             if ((nbtTag.func_74764_b("doorX")) && (nbtTag.func_74764_b("doorY")) && (nbtTag.func_74764_b("doorZ"))) {
/* 112 */               int doorX = nbtTag.func_74762_e("doorX");
/* 113 */               int doorY = nbtTag.func_74762_e("doorY") + ((i1 & 0x8) != 0 ? 1 : 0);
/* 114 */               int doorZ = nbtTag.func_74762_e("doorZ");
/* 115 */               if ((doorX == posX) && (doorY == posY) && (doorZ == posZ) && ((!nbtTag.func_74764_b("doorD")) || (nbtTag.func_74762_e("doorD") == world.field_73011_w.field_76574_g)))
/*     */               {
/* 117 */                 return true;
/*     */               }
/*     */             }
/* 120 */           } else if ((Witchery.Items.GENERIC.itemDoorKeyring.isMatch(itemstack)) && 
/* 121 */             (nbtTag.func_74764_b("doorKeys"))) {
/* 122 */             NBTTagList keyList = nbtTag.func_150295_c("doorKeys", 10);
/* 123 */             if (keyList != null) {
/* 124 */               for (int i = 0; i < keyList.func_74745_c(); i++) {
/* 125 */                 NBTTagCompound keyTag = keyList.func_150305_b(i);
/* 126 */                 if ((keyTag != null) && (keyTag.func_74764_b("doorX")) && (keyTag.func_74764_b("doorY")) && (keyTag.func_74764_b("doorZ")))
/*     */                 {
/* 128 */                   int doorX = keyTag.func_74762_e("doorX");
/* 129 */                   int doorY = keyTag.func_74762_e("doorY") + ((i1 & 0x8) != 0 ? 1 : 0);
/* 130 */                   int doorZ = keyTag.func_74762_e("doorZ");
/* 131 */                   if ((doorX == posX) && (doorY == posY) && (doorZ == posZ) && ((!keyTag.func_74764_b("doorD")) || (keyTag.func_74762_e("doorD") == world.field_73011_w.field_76574_g)))
/*     */                   {
/*     */ 
/*     */ 
/* 135 */                     return true;
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 151 */     int l = world.func_72805_g(x, y, z);
/*     */     
/* 153 */     if ((l & 0x8) == 0) {
/* 154 */       boolean flag = false;
/*     */       
/* 156 */       if (world.func_147439_a(x, y + 1, z) != this) {
/* 157 */         world.func_147468_f(x, y, z);
/* 158 */         flag = true;
/*     */       }
/*     */       
/* 161 */       if ((World.func_147466_a(world, x, y - 1, z)) || 
/*     */       
/*     */ 
/*     */ 
/* 165 */         (flag)) {
/* 166 */         if (!world.field_72995_K) {
/* 167 */           func_149697_b(world, x, y, z, l, 0);
/*     */         }
/*     */       } else {
/* 170 */         boolean flag1 = (world.func_72864_z(x, y, z)) || (world.func_72864_z(x, y + 1, z));
/*     */         
/* 172 */         if (((flag1) || (block.func_149744_f())) && (block != this)) {
/* 173 */           func_150014_a(world, x, y, z, flag1);
/*     */         }
/*     */       }
/*     */     } else {
/* 177 */       super.func_149695_a(world, x, y, z, block);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_149642_a(World world, int x, int y, int z, ItemStack stack)
/*     */   {
/* 183 */     super.func_149642_a(world, x, y, z, stack);
/*     */   }
/*     */   
/*     */   public boolean onBlockActivatedNormally(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/* 188 */     boolean result = super.func_149727_a(world, posX, posY, posZ, player, par6, par7, par8, par9);
/* 189 */     if (this == Witchery.Blocks.DOOR_ALDER) {
/* 190 */       int i1 = func_150012_g(world, posX, posY, posZ);
/* 191 */       if ((i1 & 0x8) != 0) {
/* 192 */         posY--;
/*     */       }
/* 194 */       notifyNeighborsOfBlockChange(world, posX, posY, posZ);
/*     */     }
/*     */     
/* 197 */     return result;
/*     */   }
/*     */   
/*     */   private void notifyNeighborsOfBlockChange(World world, int posX, int posY, int posZ) {
/* 201 */     world.func_147459_d(posX, posY, posZ, this);
/* 202 */     world.func_147459_d(posX, posY - 1, posZ, this);
/*     */   }
/*     */   
/*     */   public Block disableStatsThunk() {
/* 206 */     return func_149649_H();
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 211 */     Block block = world.func_147439_a(x, y, z);
/* 212 */     if (block == Witchery.Blocks.DOOR_ALDER) {
/* 213 */       return Witchery.Items.GENERIC.itemDoorAlder.createStack();
/*     */     }
/* 215 */     return Witchery.Items.GENERIC.itemDoorRowan.createStack();
/*     */   }
/*     */   
/*     */   public void func_150014_a(World world, int x, int y, int z, boolean par5)
/*     */   {
/* 220 */     if ((this != Witchery.Blocks.DOOR_ALDER) && 
/* 221 */       (!par5)) {
/* 222 */       super.func_150014_a(world, x, y, z, par5);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149709_b(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/* 229 */     if (this == Witchery.Blocks.DOOR_ALDER) {
/* 230 */       return func_150015_f(world, x, y, z) ? 15 : 0;
/*     */     }
/* 232 */     return super.func_149709_b(world, x, y, z, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149748_c(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side)
/*     */   {
/* 238 */     if (this == Witchery.Blocks.DOOR_ALDER) {
/* 239 */       return side == 1 ? func_149709_b(par1IBlockAccess, posX, posY, posZ, side) : 0;
/*     */     }
/* 241 */     return super.func_149748_c(par1IBlockAccess, posX, posY, posZ, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149744_f()
/*     */   {
/* 247 */     return this == Witchery.Blocks.DOOR_ALDER;
/*     */   }
/*     */   
/*     */   public net.minecraft.item.Item func_149650_a(int metadata, Random rand, int fortune)
/*     */   {
/* 252 */     return (metadata & 0x8) != 0 ? null : Witchery.Items.GENERIC;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 257 */     return this == Witchery.Blocks.DOOR_ALDER ? Witchery.Items.GENERIC.itemDoorAlder.damageValue : (metadata & 0x8) != 0 ? 0 : Witchery.Items.GENERIC.itemDoorRowan.damageValue;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockWitchDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */