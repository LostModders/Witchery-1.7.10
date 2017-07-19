/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
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
/*     */ public class BlockLeverBase
/*     */   extends BlockBaseContainer
/*     */ {
/*     */   public BlockLeverBase()
/*     */   {
/*  37 */     super(Material.field_151594_q, TileEntityCursedBlock.class);
/*  38 */     this.registerWithCreateTab = false;
/*  39 */     func_149711_c(0.5F);
/*  40 */     func_149672_a(field_149766_f);
/*  41 */     func_149658_d("lever");
/*     */   }
/*     */   
/*     */   public void replaceButton(World world, int x, int y, int z, ModifiersImpact impactModifiers, NBTTagCompound nbtBrew) {
/*  45 */     int meta = world.func_72805_g(x, y, z);
/*  46 */     world.func_147465_d(x, y, z, this, meta, 3);
/*  47 */     TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
/*  48 */     if (tile != null) {
/*  49 */       tile.initalise(impactModifiers, nbtBrew);
/*     */     }
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/*  55 */     return Item.func_150898_a(Blocks.field_150442_at);
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  60 */     return new ItemStack(Blocks.field_150442_at);
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
/*     */   {
/*  66 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  81 */     return 12;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149707_d(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_)
/*     */   {
/*  87 */     ForgeDirection dir = ForgeDirection.getOrientation(p_149707_5_);
/*  88 */     return ((dir == ForgeDirection.DOWN) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_ + 1, p_149707_4_, ForgeDirection.DOWN))) || ((dir == ForgeDirection.UP) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_ - 1, p_149707_4_, ForgeDirection.UP))) || ((dir == ForgeDirection.NORTH) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_, p_149707_4_ + 1, ForgeDirection.NORTH))) || ((dir == ForgeDirection.SOUTH) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_, p_149707_4_ - 1, ForgeDirection.SOUTH))) || ((dir == ForgeDirection.WEST) && (p_149707_1_.isSideSolid(p_149707_2_ + 1, p_149707_3_, p_149707_4_, ForgeDirection.WEST))) || ((dir == ForgeDirection.EAST) && (p_149707_1_.isSideSolid(p_149707_2_ - 1, p_149707_3_, p_149707_4_, ForgeDirection.EAST)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
/*     */   {
/*  98 */     return (p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, ForgeDirection.EAST)) || (p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, ForgeDirection.WEST)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, ForgeDirection.SOUTH)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, ForgeDirection.NORTH)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ - 1, p_149742_4_, ForgeDirection.UP)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ + 1, p_149742_4_, ForgeDirection.DOWN));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
/*     */   {
/* 109 */     int k1 = p_149660_9_ & 0x8;
/* 110 */     int j1 = p_149660_9_ & 0x7;
/* 111 */     byte b0 = -1;
/*     */     
/* 113 */     if ((p_149660_5_ == 0) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ + 1, p_149660_4_, ForgeDirection.DOWN))) {
/* 114 */       b0 = 0;
/*     */     }
/*     */     
/* 117 */     if ((p_149660_5_ == 1) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ - 1, p_149660_4_, ForgeDirection.UP))) {
/* 118 */       b0 = 5;
/*     */     }
/*     */     
/* 121 */     if ((p_149660_5_ == 2) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ + 1, ForgeDirection.NORTH))) {
/* 122 */       b0 = 4;
/*     */     }
/*     */     
/* 125 */     if ((p_149660_5_ == 3) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ - 1, ForgeDirection.SOUTH))) {
/* 126 */       b0 = 3;
/*     */     }
/*     */     
/* 129 */     if ((p_149660_5_ == 4) && (p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_, p_149660_4_, ForgeDirection.WEST))) {
/* 130 */       b0 = 2;
/*     */     }
/*     */     
/* 133 */     if ((p_149660_5_ == 5) && (p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_, p_149660_4_, ForgeDirection.EAST))) {
/* 134 */       b0 = 1;
/*     */     }
/*     */     
/* 137 */     return b0 + k1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
/*     */   {
/* 143 */     int l = p_149689_1_.func_72805_g(p_149689_2_, p_149689_3_, p_149689_4_);
/* 144 */     int i1 = l & 0x7;
/* 145 */     int j1 = l & 0x8;
/*     */     
/* 147 */     if (i1 == invertMetadata(1)) {
/* 148 */       if ((MathHelper.func_76128_c(p_149689_5_.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x1) == 0) {
/* 149 */         p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x5 | j1, 2);
/*     */       } else {
/* 151 */         p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x6 | j1, 2);
/*     */       }
/* 153 */     } else if (i1 == invertMetadata(0)) {
/* 154 */       if ((MathHelper.func_76128_c(p_149689_5_.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x1) == 0) {
/* 155 */         p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x7 | j1, 2);
/*     */       } else {
/* 157 */         p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x0 | j1, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static int invertMetadata(int p_149819_0_) {
/* 163 */     switch (p_149819_0_) {
/*     */     case 0: 
/* 165 */       return 0;
/*     */     case 1: 
/* 167 */       return 5;
/*     */     case 2: 
/* 169 */       return 4;
/*     */     case 3: 
/* 171 */       return 3;
/*     */     case 4: 
/* 173 */       return 2;
/*     */     case 5: 
/* 175 */       return 1;
/*     */     }
/* 177 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
/*     */   {
/* 184 */     if (func_149820_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_)) {
/* 185 */       int l = p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_) & 0x7;
/* 186 */       boolean flag = false;
/*     */       
/* 188 */       if ((!p_149695_1_.isSideSolid(p_149695_2_ - 1, p_149695_3_, p_149695_4_, ForgeDirection.EAST)) && (l == 1)) {
/* 189 */         flag = true;
/*     */       }
/*     */       
/* 192 */       if ((!p_149695_1_.isSideSolid(p_149695_2_ + 1, p_149695_3_, p_149695_4_, ForgeDirection.WEST)) && (l == 2)) {
/* 193 */         flag = true;
/*     */       }
/*     */       
/* 196 */       if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_, p_149695_4_ - 1, ForgeDirection.SOUTH)) && (l == 3)) {
/* 197 */         flag = true;
/*     */       }
/*     */       
/* 200 */       if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_, p_149695_4_ + 1, ForgeDirection.NORTH)) && (l == 4)) {
/* 201 */         flag = true;
/*     */       }
/*     */       
/* 204 */       if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ - 1, p_149695_4_, ForgeDirection.UP)) && (l == 5)) {
/* 205 */         flag = true;
/*     */       }
/*     */       
/* 208 */       if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ - 1, p_149695_4_, ForgeDirection.UP)) && (l == 6)) {
/* 209 */         flag = true;
/*     */       }
/*     */       
/* 212 */       if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ + 1, p_149695_4_, ForgeDirection.DOWN)) && (l == 0)) {
/* 213 */         flag = true;
/*     */       }
/*     */       
/* 216 */       if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ + 1, p_149695_4_, ForgeDirection.DOWN)) && (l == 7)) {
/* 217 */         flag = true;
/*     */       }
/*     */       
/* 220 */       if (flag) {
/* 221 */         func_149697_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_), 0);
/*     */         
/* 223 */         p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean func_149820_e(World p_149820_1_, int p_149820_2_, int p_149820_3_, int p_149820_4_) {
/* 229 */     if (!func_149742_c(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_)) {
/* 230 */       func_149697_b(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_, p_149820_1_.func_72805_g(p_149820_2_, p_149820_3_, p_149820_4_), 0);
/*     */       
/* 232 */       p_149820_1_.func_147468_f(p_149820_2_, p_149820_3_, p_149820_4_);
/* 233 */       return false;
/*     */     }
/* 235 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
/*     */   {
/* 242 */     int l = p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_) & 0x7;
/* 243 */     float f = 0.1875F;
/*     */     
/* 245 */     if (l == 1) {
/* 246 */       func_149676_a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
/* 247 */     } else if (l == 2) {
/* 248 */       func_149676_a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
/* 249 */     } else if (l == 3) {
/* 250 */       func_149676_a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
/* 251 */     } else if (l == 4) {
/* 252 */       func_149676_a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
/* 253 */     } else if ((l != 5) && (l != 6)) {
/* 254 */       if ((l == 0) || (l == 7)) {
/* 255 */         f = 0.25F;
/* 256 */         func_149676_a(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
/*     */       }
/*     */     } else {
/* 259 */       f = 0.25F;
/* 260 */       func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
/*     */   {
/* 267 */     if (world.field_72995_K) {
/* 268 */       return true;
/*     */     }
/* 270 */     int i1 = world.func_72805_g(x, y, z);
/* 271 */     int j1 = i1 & 0x7;
/* 272 */     int k1 = 8 - (i1 & 0x8);
/* 273 */     world.func_72921_c(x, y, z, j1 + k1, 3);
/* 274 */     world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
/*     */     
/* 276 */     world.func_147459_d(x, y, z, this);
/*     */     
/* 278 */     if (j1 == 1) {
/* 279 */       world.func_147459_d(x - 1, y, z, this);
/* 280 */     } else if (j1 == 2) {
/* 281 */       world.func_147459_d(x + 1, y, z, this);
/* 282 */     } else if (j1 == 3) {
/* 283 */       world.func_147459_d(x, y, z - 1, this);
/* 284 */     } else if (j1 == 4) {
/* 285 */       world.func_147459_d(x, y, z + 1, this);
/* 286 */     } else if ((j1 != 5) && (j1 != 6)) {
/* 287 */       if ((j1 == 0) || (j1 == 7)) {
/* 288 */         world.func_147459_d(x, y + 1, z, this);
/*     */       }
/*     */     } else {
/* 291 */       world.func_147459_d(x, y - 1, z, this);
/*     */     }
/*     */     
/* 294 */     if (!world.field_72995_K) {
/* 295 */       TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
/* 296 */       if ((tile != null) && (tile.nbtEffect != null) && 
/* 297 */         (!tile.applyToEntityAndDestroy(player))) {
/* 298 */         world.func_147465_d(x, y, z, Blocks.field_150442_at, j1 + k1, 3);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 303 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
/*     */   {
/* 310 */     if ((p_149749_6_ & 0x8) > 0) {
/* 311 */       p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_, p_149749_4_, this);
/* 312 */       int i1 = p_149749_6_ & 0x7;
/*     */       
/* 314 */       if (i1 == 1) {
/* 315 */         p_149749_1_.func_147459_d(p_149749_2_ - 1, p_149749_3_, p_149749_4_, this);
/* 316 */       } else if (i1 == 2) {
/* 317 */         p_149749_1_.func_147459_d(p_149749_2_ + 1, p_149749_3_, p_149749_4_, this);
/* 318 */       } else if (i1 == 3) {
/* 319 */         p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_, p_149749_4_ - 1, this);
/* 320 */       } else if (i1 == 4) {
/* 321 */         p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_, p_149749_4_ + 1, this);
/* 322 */       } else if ((i1 != 5) && (i1 != 6)) {
/* 323 */         if ((i1 == 0) || (i1 == 7)) {
/* 324 */           p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_ + 1, p_149749_4_, this);
/*     */         }
/*     */       } else {
/* 327 */         p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_ - 1, p_149749_4_, this);
/*     */       }
/*     */     }
/*     */     
/* 331 */     super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149709_b(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
/*     */   {
/* 337 */     return (p_149709_1_.func_72805_g(p_149709_2_, p_149709_3_, p_149709_4_) & 0x8) > 0 ? 15 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149748_c(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
/*     */   {
/* 343 */     int i1 = p_149748_1_.func_72805_g(p_149748_2_, p_149748_3_, p_149748_4_);
/*     */     
/* 345 */     if ((i1 & 0x8) == 0) {
/* 346 */       return 0;
/*     */     }
/* 348 */     int j1 = i1 & 0x7;
/* 349 */     return (j1 == 1) && (p_149748_5_ == 5) ? 15 : (j1 == 2) && (p_149748_5_ == 4) ? 15 : (j1 == 3) && (p_149748_5_ == 3) ? 15 : (j1 == 4) && (p_149748_5_ == 2) ? 15 : (j1 == 5) && (p_149748_5_ == 1) ? 15 : (j1 == 6) && (p_149748_5_ == 1) ? 15 : (j1 == 7) && (p_149748_5_ == 0) ? 15 : (j1 == 0) && (p_149748_5_ == 0) ? 15 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_149744_f()
/*     */   {
/* 358 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockLeverBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */