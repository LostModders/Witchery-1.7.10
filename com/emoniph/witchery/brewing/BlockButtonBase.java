/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
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
/*     */ public abstract class BlockButtonBase
/*     */   extends BlockBaseContainer
/*     */ {
/*     */   private final boolean isWood;
/*     */   
/*     */   protected BlockButtonBase(boolean wooden)
/*     */   {
/*  45 */     super(Material.field_151594_q, TileEntityCursedBlock.class);
/*  46 */     func_149675_a(true);
/*  47 */     this.isWood = wooden;
/*  48 */     this.registerWithCreateTab = false;
/*  49 */     func_149711_c(0.5F);
/*  50 */     func_149672_a(field_149780_i);
/*     */   }
/*     */   
/*     */   public void replaceButton(World world, int x, int y, int z, ModifiersImpact impactModifiers, NBTTagCompound nbtBrew) {
/*  54 */     int meta = world.func_72805_g(x, y, z);
/*  55 */     world.func_147465_d(x, y, z, this, meta & 0x7, 3);
/*  56 */     TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
/*  57 */     if (tile != null) {
/*  58 */       tile.initalise(impactModifiers, nbtBrew);
/*     */     }
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/*  64 */     return Item.func_150898_a(this.isWood ? Blocks.field_150471_bO : Blocks.field_150430_aB);
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  69 */     return new ItemStack(this.isWood ? Blocks.field_150471_bO : Blocks.field_150430_aB);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  74 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149738_a(World world)
/*     */   {
/*  79 */     return this.isWood ? 30 : 20;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149707_d(World world, int x, int y, int z, int side)
/*     */   {
/*  94 */     ForgeDirection dir = ForgeDirection.getOrientation(side);
/*  95 */     return ((dir == ForgeDirection.NORTH) && (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH))) || ((dir == ForgeDirection.SOUTH) && (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH))) || ((dir == ForgeDirection.WEST) && (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST))) || ((dir == ForgeDirection.EAST) && (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149742_c(World world, int x, int y, int z)
/*     */   {
/* 103 */     return (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST)) || (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST)) || (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH)) || (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149660_a(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
/*     */   {
/* 110 */     int j1 = world.func_72805_g(x, y, z);
/* 111 */     int k1 = j1 & 0x8;
/* 112 */     j1 &= 0x7;
/*     */     
/* 114 */     ForgeDirection dir = ForgeDirection.getOrientation(side);
/*     */     
/* 116 */     if ((dir == ForgeDirection.NORTH) && (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH))) {
/* 117 */       j1 = 4;
/* 118 */     } else if ((dir == ForgeDirection.SOUTH) && (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH))) {
/* 119 */       j1 = 3;
/* 120 */     } else if ((dir == ForgeDirection.WEST) && (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST))) {
/* 121 */       j1 = 2;
/* 122 */     } else if ((dir == ForgeDirection.EAST) && (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST))) {
/* 123 */       j1 = 1;
/*     */     } else {
/* 125 */       j1 = func_150045_e(world, x, y, z);
/*     */     }
/*     */     
/* 128 */     return j1 + k1;
/*     */   }
/*     */   
/*     */   private int func_150045_e(World world, int x, int y, int z) {
/* 132 */     if (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST))
/* 133 */       return 1;
/* 134 */     if (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST))
/* 135 */       return 2;
/* 136 */     if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH))
/* 137 */       return 3;
/* 138 */     if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH))
/* 139 */       return 4;
/* 140 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 145 */     if (func_150044_m(world, x, y, z)) {
/* 146 */       int l = world.func_72805_g(x, y, z) & 0x7;
/* 147 */       boolean flag = false;
/*     */       
/* 149 */       if ((!world.isSideSolid(x - 1, y, z, ForgeDirection.EAST)) && (l == 1)) {
/* 150 */         flag = true;
/*     */       }
/*     */       
/* 153 */       if ((!world.isSideSolid(x + 1, y, z, ForgeDirection.WEST)) && (l == 2)) {
/* 154 */         flag = true;
/*     */       }
/*     */       
/* 157 */       if ((!world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH)) && (l == 3)) {
/* 158 */         flag = true;
/*     */       }
/*     */       
/* 161 */       if ((!world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH)) && (l == 4)) {
/* 162 */         flag = true;
/*     */       }
/*     */       
/* 165 */       if (flag) {
/* 166 */         func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 167 */         world.func_147468_f(x, y, z);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean func_150044_m(World world, int x, int y, int z) {
/* 173 */     if (!func_149742_c(world, x, y, z)) {
/* 174 */       func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 175 */       world.func_147468_f(x, y, z);
/* 176 */       return false;
/*     */     }
/* 178 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 184 */     int l = world.func_72805_g(x, y, z);
/* 185 */     func_150043_b(l);
/*     */   }
/*     */   
/*     */   private void func_150043_b(int p_150043_1_) {
/* 189 */     int j = p_150043_1_ & 0x7;
/* 190 */     boolean flag = (p_150043_1_ & 0x8) > 0;
/* 191 */     float f = 0.375F;
/* 192 */     float f1 = 0.625F;
/* 193 */     float f2 = 0.1875F;
/* 194 */     float f3 = 0.125F;
/*     */     
/* 196 */     if (flag) {
/* 197 */       f3 = 0.0625F;
/*     */     }
/*     */     
/* 200 */     if (j == 1) {
/* 201 */       func_149676_a(0.0F, f, 0.5F - f2, f3, f1, 0.5F + f2);
/* 202 */     } else if (j == 2) {
/* 203 */       func_149676_a(1.0F - f3, f, 0.5F - f2, 1.0F, f1, 0.5F + f2);
/* 204 */     } else if (j == 3) {
/* 205 */       func_149676_a(0.5F - f2, f, 0.0F, 0.5F + f2, f1, f3);
/* 206 */     } else if (j == 4) {
/* 207 */       func_149676_a(0.5F - f2, f, 1.0F - f3, 0.5F + f2, f1, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {}
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
/*     */   {
/* 218 */     int i1 = world.func_72805_g(x, y, z);
/* 219 */     int j1 = i1 & 0x7;
/* 220 */     int k1 = 8 - (i1 & 0x8);
/*     */     
/* 222 */     if (k1 == 0) {
/* 223 */       return true;
/*     */     }
/* 225 */     if (!world.field_72995_K)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 231 */       TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
/* 232 */       if ((tile != null) && (tile.nbtEffect != null) && 
/* 233 */         (!tile.applyToEntityAndDestroy(player))) {
/* 234 */         world.func_147465_d(x, y, z, this.isWood ? Blocks.field_150471_bO : Blocks.field_150430_aB, j1 + k1, 3);
/* 235 */         world.func_147458_c(x, y, z, x, y, z);
/* 236 */         world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.6F);
/* 237 */         func_150042_a(world, x, y, z, j1);
/* 238 */         world.func_72921_c(x, y, z, j1 + k1, 3);
/* 239 */         world.func_147464_a(x, y, z, this.isWood ? Blocks.field_150471_bO : Blocks.field_150430_aB, func_149738_a(world));
/* 240 */         return true;
/*     */       }
/*     */       
/* 243 */       world.func_147458_c(x, y, z, x, y, z);
/* 244 */       world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.6F);
/* 245 */       func_150042_a(world, x, y, z, j1);
/* 246 */       world.func_72921_c(x, y, z, j1 + k1, 3);
/* 247 */       world.func_147464_a(x, y, z, this, func_149738_a(world));
/*     */     }
/*     */     
/*     */ 
/* 251 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block, int p_149749_6_)
/*     */   {
/* 257 */     if ((p_149749_6_ & 0x8) > 0) {
/* 258 */       int i1 = p_149749_6_ & 0x7;
/* 259 */       func_150042_a(world, x, y, z, i1);
/*     */     }
/*     */     
/* 262 */     super.func_149749_a(world, x, y, z, block, p_149749_6_);
/*     */   }
/*     */   
/*     */   public int func_149709_b(IBlockAccess world, int x, int y, int z, int p_149709_5_)
/*     */   {
/* 267 */     return (world.func_72805_g(x, y, z) & 0x8) > 0 ? 15 : 0;
/*     */   }
/*     */   
/*     */   public int func_149748_c(IBlockAccess world, int x, int y, int z, int p_149748_5_)
/*     */   {
/* 272 */     int i1 = world.func_72805_g(x, y, z);
/*     */     
/* 274 */     if ((i1 & 0x8) == 0) {
/* 275 */       return 0;
/*     */     }
/* 277 */     int j1 = i1 & 0x7;
/* 278 */     return (j1 == 1) && (p_149748_5_ == 5) ? 15 : (j1 == 2) && (p_149748_5_ == 4) ? 15 : (j1 == 3) && (p_149748_5_ == 3) ? 15 : (j1 == 4) && (p_149748_5_ == 2) ? 15 : (j1 == 5) && (p_149748_5_ == 1) ? 15 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149744_f()
/*     */   {
/* 286 */     return true;
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/* 291 */     if (!world.field_72995_K) {
/* 292 */       int l = world.func_72805_g(x, y, z);
/*     */       
/* 294 */       if ((l & 0x8) != 0) {
/* 295 */         if (this.isWood) {
/* 296 */           func_150046_n(world, x, y, z);
/*     */         } else {
/* 298 */           world.func_72921_c(x, y, z, l & 0x7, 3);
/* 299 */           int i1 = l & 0x7;
/* 300 */           func_150042_a(world, x, y, z, i1);
/* 301 */           world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.5F);
/* 302 */           world.func_147458_c(x, y, z, x, y, z);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149683_g()
/*     */   {
/* 310 */     float f = 0.1875F;
/* 311 */     float f1 = 0.125F;
/* 312 */     float f2 = 0.125F;
/* 313 */     func_149676_a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 318 */     if ((!world.field_72995_K) && 
/* 319 */       (this.isWood) && 
/* 320 */       ((world.func_72805_g(x, y, z) & 0x8) == 0)) {
/* 321 */       func_150046_n(world, x, y, z);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void func_150046_n(World world, int x, int y, int z)
/*     */   {
/* 328 */     int l = world.func_72805_g(x, y, z);
/* 329 */     int i1 = l & 0x7;
/* 330 */     boolean flag = (l & 0x8) != 0;
/* 331 */     func_150043_b(l);
/* 332 */     List list = world.func_72872_a(EntityArrow.class, AxisAlignedBB.func_72330_a(x + this.field_149759_B, y + this.field_149760_C, z + this.field_149754_D, x + this.field_149755_E, y + this.field_149756_F, z + this.field_149757_G));
/*     */     
/*     */ 
/*     */ 
/* 336 */     boolean flag1 = !list.isEmpty();
/*     */     
/* 338 */     if ((flag1) && (!flag)) {
/* 339 */       world.func_72921_c(x, y, z, i1 | 0x8, 3);
/* 340 */       func_150042_a(world, x, y, z, i1);
/* 341 */       world.func_147458_c(x, y, z, x, y, z);
/* 342 */       world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.6F);
/*     */     }
/*     */     
/* 345 */     if ((!flag1) && (flag)) {
/* 346 */       world.func_72921_c(x, y, z, i1, 3);
/* 347 */       func_150042_a(world, x, y, z, i1);
/* 348 */       world.func_147458_c(x, y, z, x, y, z);
/* 349 */       world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.5F);
/*     */     }
/*     */     
/* 352 */     if (flag1) {
/* 353 */       world.func_147464_a(x, y, z, this, func_149738_a(world));
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_150042_a(World world, int x, int y, int z, int p_150042_5_) {
/* 358 */     world.func_147459_d(x, y, z, this);
/*     */     
/* 360 */     if (p_150042_5_ == 1) {
/* 361 */       world.func_147459_d(x - 1, y, z, this);
/* 362 */     } else if (p_150042_5_ == 2) {
/* 363 */       world.func_147459_d(x + 1, y, z, this);
/* 364 */     } else if (p_150042_5_ == 3) {
/* 365 */       world.func_147459_d(x, y, z - 1, this);
/* 366 */     } else if (p_150042_5_ == 4) {
/* 367 */       world.func_147459_d(x, y, z + 1, this);
/*     */     } else {
/* 369 */       world.func_147459_d(x, y - 1, z, this);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockButtonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */