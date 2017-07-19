/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBaseContainer;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.IconFlipped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDoorBase extends BlockBaseContainer
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] field_150017_a;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] field_150016_b;
/*     */   
/*     */   public BlockDoorBase(Material p_i45402_1_)
/*     */   {
/*  32 */     super(p_i45402_1_, TileEntityCursedBlock.class);
/*  33 */     this.registerWithCreateTab = false;
/*  34 */     func_149711_c(3.0F);
/*  35 */     func_149672_a(field_149766_f);
/*     */     
/*  37 */     float f = 0.5F;
/*  38 */     float f1 = 1.0F;
/*  39 */     func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
/*     */   }
/*     */   
/*     */   public void replaceButton(World world, int x, int y, int z, ModifiersImpact impactModifiers, net.minecraft.nbt.NBTTagCompound nbtBrew)
/*     */   {
/*  44 */     int meta = world.func_72805_g(x, y, z);
/*  45 */     int i1 = ((BlockDoor)net.minecraft.init.Blocks.field_150466_ao).func_150012_g(world, x, y, z);
/*  46 */     if ((i1 & 0x8) != 0) {
/*  47 */       y--;
/*     */     }
/*  49 */     world.func_147468_f(x, y, z);
/*  50 */     world.func_147468_f(x, y + 1, z);
/*  51 */     net.minecraft.item.ItemDoor.func_150924_a(world, x, y, z, i1 & 0x3, this);
/*     */     
/*  53 */     TileEntityCursedBlock tile = (TileEntityCursedBlock)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
/*  54 */     if (tile != null) {
/*  55 */       tile.initalise(impactModifiers, nbtBrew);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
/*     */   {
/*  66 */     return this.field_150016_b[0];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
/*     */   {
/*  72 */     if ((p_149673_5_ != 1) && (p_149673_5_ != 0)) {
/*  73 */       int i1 = func_150012_g(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_);
/*  74 */       int j1 = i1 & 0x3;
/*  75 */       boolean flag = (i1 & 0x4) != 0;
/*  76 */       boolean flag1 = false;
/*  77 */       boolean flag2 = (i1 & 0x8) != 0;
/*     */       
/*  79 */       if (flag) {
/*  80 */         if ((j1 == 0) && (p_149673_5_ == 2)) {
/*  81 */           flag1 = !flag1;
/*  82 */         } else if ((j1 == 1) && (p_149673_5_ == 5)) {
/*  83 */           flag1 = !flag1;
/*  84 */         } else if ((j1 == 2) && (p_149673_5_ == 3)) {
/*  85 */           flag1 = !flag1;
/*  86 */         } else if ((j1 == 3) && (p_149673_5_ == 4)) {
/*  87 */           flag1 = !flag1;
/*     */         }
/*     */       } else {
/*  90 */         if ((j1 == 0) && (p_149673_5_ == 5)) {
/*  91 */           flag1 = !flag1;
/*  92 */         } else if ((j1 == 1) && (p_149673_5_ == 3)) {
/*  93 */           flag1 = !flag1;
/*  94 */         } else if ((j1 == 2) && (p_149673_5_ == 4)) {
/*  95 */           flag1 = !flag1;
/*  96 */         } else if ((j1 == 3) && (p_149673_5_ == 2)) {
/*  97 */           flag1 = !flag1;
/*     */         }
/*     */         
/* 100 */         if ((i1 & 0x10) != 0) {
/* 101 */           flag1 = !flag1;
/*     */         }
/*     */       }
/*     */       
/* 105 */       return flag2 ? this.field_150017_a[0] : this.field_150016_b[0];
/*     */     }
/* 107 */     return this.field_150016_b[0];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister p_149651_1_)
/*     */   {
/* 113 */     this.field_150017_a = new IIcon[2];
/* 114 */     this.field_150016_b = new IIcon[2];
/* 115 */     this.field_150017_a[0] = p_149651_1_.func_94245_a(func_149641_N() + "_upper");
/* 116 */     this.field_150016_b[0] = p_149651_1_.func_94245_a(func_149641_N() + "_lower");
/* 117 */     this.field_150017_a[1] = new IconFlipped(this.field_150017_a[0], true, false);
/* 118 */     this.field_150016_b[1] = new IconFlipped(this.field_150016_b[0], true, false);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
/* 126 */     int l = func_150012_g(p_149655_1_, p_149655_2_, p_149655_3_, p_149655_4_);
/* 127 */     return (l & 0x4) != 0;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/* 131 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/* 135 */     return 7;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
/*     */   {
/* 141 */     func_149719_a(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
/* 142 */     return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
/*     */   {
/* 147 */     func_149719_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
/* 148 */     return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
/*     */   {
/* 153 */     func_150011_b(func_150012_g(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_));
/*     */   }
/*     */   
/*     */   public int func_150013_e(IBlockAccess p_150013_1_, int p_150013_2_, int p_150013_3_, int p_150013_4_) {
/* 157 */     return func_150012_g(p_150013_1_, p_150013_2_, p_150013_3_, p_150013_4_) & 0x3;
/*     */   }
/*     */   
/*     */   public boolean func_150015_f(IBlockAccess p_150015_1_, int p_150015_2_, int p_150015_3_, int p_150015_4_) {
/* 161 */     return (func_150012_g(p_150015_1_, p_150015_2_, p_150015_3_, p_150015_4_) & 0x4) != 0;
/*     */   }
/*     */   
/*     */   private void func_150011_b(int p_150011_1_) {
/* 165 */     float f = 0.1875F;
/* 166 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/* 167 */     int j = p_150011_1_ & 0x3;
/* 168 */     boolean flag = (p_150011_1_ & 0x4) != 0;
/* 169 */     boolean flag1 = (p_150011_1_ & 0x10) != 0;
/*     */     
/* 171 */     if (j == 0) {
/* 172 */       if (flag) {
/* 173 */         if (!flag1) {
/* 174 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*     */         } else {
/* 176 */           func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */       } else {
/* 179 */         func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*     */       }
/* 181 */     } else if (j == 1) {
/* 182 */       if (flag) {
/* 183 */         if (!flag1) {
/* 184 */           func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         } else {
/* 186 */           func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*     */         }
/*     */       } else {
/* 189 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*     */       }
/* 191 */     } else if (j == 2) {
/* 192 */       if (flag) {
/* 193 */         if (!flag1) {
/* 194 */           func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*     */         } else {
/* 196 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*     */         }
/*     */       } else {
/* 199 */         func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/* 201 */     } else if (j == 3) {
/* 202 */       if (flag) {
/* 203 */         if (!flag1) {
/* 204 */           func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*     */         } else {
/* 206 */           func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */       } else {
/* 209 */         func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149699_a(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {}
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
/*     */   {
/* 220 */     if (this.field_149764_J == Material.field_151573_f) {
/* 221 */       return false;
/*     */     }
/* 223 */     int i1 = func_150012_g(world, x, y, z);
/* 224 */     int j1 = i1 & 0x7;
/* 225 */     j1 ^= 0x4;
/*     */     
/* 227 */     int offy = y;
/* 228 */     if ((i1 & 0x8) != 0) {
/* 229 */       offy--;
/*     */     }
/*     */     
/* 232 */     if (!world.field_72995_K) {
/* 233 */       TileEntityCursedBlock tile = (TileEntityCursedBlock)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, offy, z, TileEntityCursedBlock.class);
/*     */       
/* 235 */       if ((tile != null) && 
/* 236 */         (!tile.applyToEntityAndDestroy(player))) {
/* 237 */         world.func_147468_f(x, offy, z);
/* 238 */         world.func_147468_f(x, offy + 1, z);
/* 239 */         net.minecraft.item.ItemDoor.func_150924_a(world, x, offy, z, j1, net.minecraft.init.Blocks.field_150466_ao);
/* 240 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 245 */     if ((i1 & 0x8) == 0) {
/* 246 */       world.func_72921_c(x, y, z, j1, 2);
/* 247 */       world.func_147458_c(x, y, z, x, y, z);
/*     */     } else {
/* 249 */       world.func_72921_c(x, y - 1, z, j1, 2);
/* 250 */       world.func_147458_c(x, y - 1, z, x, y, z);
/*     */     }
/*     */     
/* 253 */     world.func_72889_a(player, 1003, x, y, z, 0);
/*     */     
/* 255 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_150014_a(World p_150014_1_, int p_150014_2_, int p_150014_3_, int p_150014_4_, boolean p_150014_5_)
/*     */   {
/* 261 */     int l = func_150012_g(p_150014_1_, p_150014_2_, p_150014_3_, p_150014_4_);
/* 262 */     boolean flag1 = (l & 0x4) != 0;
/*     */     
/* 264 */     if (flag1 != p_150014_5_) {
/* 265 */       int i1 = l & 0x7;
/* 266 */       i1 ^= 0x4;
/*     */       
/* 268 */       if ((l & 0x8) == 0) {
/* 269 */         p_150014_1_.func_72921_c(p_150014_2_, p_150014_3_, p_150014_4_, i1, 2);
/* 270 */         p_150014_1_.func_147458_c(p_150014_2_, p_150014_3_, p_150014_4_, p_150014_2_, p_150014_3_, p_150014_4_);
/*     */       }
/*     */       else {
/* 273 */         p_150014_1_.func_72921_c(p_150014_2_, p_150014_3_ - 1, p_150014_4_, i1, 2);
/* 274 */         p_150014_1_.func_147458_c(p_150014_2_, p_150014_3_ - 1, p_150014_4_, p_150014_2_, p_150014_3_, p_150014_4_);
/*     */       }
/*     */       
/*     */ 
/* 278 */       p_150014_1_.func_72889_a((EntityPlayer)null, 1003, p_150014_2_, p_150014_3_, p_150014_4_, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
/*     */   {
/* 284 */     int l = p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_);
/*     */     
/* 286 */     if ((l & 0x8) == 0) {
/* 287 */       boolean flag = false;
/*     */       
/* 289 */       if (p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_ + 1, p_149695_4_) != this) {
/* 290 */         p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
/* 291 */         flag = true;
/*     */       }
/*     */       
/* 294 */       if (!World.func_147466_a(p_149695_1_, p_149695_2_, p_149695_3_ - 1, p_149695_4_)) {
/* 295 */         p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
/* 296 */         flag = true;
/*     */         
/* 298 */         if (p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_ + 1, p_149695_4_) == this) {
/* 299 */           p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_ + 1, p_149695_4_);
/*     */         }
/*     */       }
/*     */       
/* 303 */       if (flag) {
/* 304 */         if (!p_149695_1_.field_72995_K) {
/* 305 */           func_149697_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, l, 0);
/*     */         }
/*     */       } else {
/* 308 */         boolean flag1 = (p_149695_1_.func_72864_z(p_149695_2_, p_149695_3_, p_149695_4_)) || (p_149695_1_.func_72864_z(p_149695_2_, p_149695_3_ + 1, p_149695_4_));
/*     */         
/*     */ 
/* 311 */         if (((flag1) || (p_149695_5_.func_149744_f())) && (p_149695_5_ != this)) {
/* 312 */           func_150014_a(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, flag1);
/*     */         }
/*     */       }
/*     */     } else {
/* 316 */       if (p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_ - 1, p_149695_4_) != this) {
/* 317 */         p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
/*     */       }
/*     */       
/* 320 */       if (p_149695_5_ != this) {
/* 321 */         func_149695_a(p_149695_1_, p_149695_2_, p_149695_3_ - 1, p_149695_4_, p_149695_5_);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
/* 327 */     return this.field_149764_J == Material.field_151573_f ? Items.field_151139_aw : (p_149650_1_ & 0x8) != 0 ? null : Items.field_151135_aq;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 333 */     return new ItemStack(this.field_149764_J == Material.field_151573_f ? Items.field_151139_aw : Items.field_151135_aq);
/*     */   }
/*     */   
/*     */   public MovingObjectPosition func_149731_a(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_, Vec3 p_149731_6_)
/*     */   {
/* 338 */     func_149719_a(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_);
/* 339 */     return super.func_149731_a(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
/*     */   {
/* 344 */     return p_149742_3_ < p_149742_1_.func_72800_K() - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149656_h()
/*     */   {
/* 351 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_150012_g(IBlockAccess p_150012_1_, int p_150012_2_, int p_150012_3_, int p_150012_4_) {
/* 355 */     int l = p_150012_1_.func_72805_g(p_150012_2_, p_150012_3_, p_150012_4_);
/* 356 */     boolean flag = (l & 0x8) != 0;
/*     */     int j1;
/*     */     int i1;
/*     */     int j1;
/* 360 */     if (flag) {
/* 361 */       int i1 = p_150012_1_.func_72805_g(p_150012_2_, p_150012_3_ - 1, p_150012_4_);
/* 362 */       j1 = l;
/*     */     } else {
/* 364 */       i1 = l;
/* 365 */       j1 = p_150012_1_.func_72805_g(p_150012_2_, p_150012_3_ + 1, p_150012_4_);
/*     */     }
/*     */     
/* 368 */     boolean flag1 = (j1 & 0x1) != 0;
/* 369 */     return i1 & 0x7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
/* 374 */     return this.field_149764_J == Material.field_151573_f ? Items.field_151139_aw : Items.field_151135_aq;
/*     */   }
/*     */   
/*     */   public void func_149681_a(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_)
/*     */   {
/* 379 */     if ((p_149681_6_.field_71075_bZ.field_75098_d) && ((p_149681_5_ & 0x8) != 0) && (p_149681_1_.func_147439_a(p_149681_2_, p_149681_3_ - 1, p_149681_4_) == this))
/*     */     {
/* 381 */       p_149681_1_.func_147468_f(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BlockDoorBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */