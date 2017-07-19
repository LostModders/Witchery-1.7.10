/*     */ package com.emoniph.witchery.util;
/*     */ 
/*     */ import com.emoniph.witchery.common.INullSource;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public final class Coord
/*     */ {
/*     */   public final int x;
/*     */   public final int y;
/*     */   public final int z;
/*     */   
/*     */   public Coord(int x, int y, int z)
/*     */   {
/*  22 */     this.x = x;
/*  23 */     this.y = y;
/*  24 */     this.z = z;
/*     */   }
/*     */   
/*     */   public Coord(int x, int y, int z, ForgeDirection side) {
/*  28 */     this.x = (x + side.offsetX);
/*  29 */     this.y = (y + side.offsetY);
/*  30 */     this.z = (z + side.offsetZ);
/*     */   }
/*     */   
/*     */   public Coord(TileEntity tileEntity) {
/*  34 */     this(tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/*     */   }
/*     */   
/*     */   public Coord(Entity entity) {
/*  38 */     this(MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70163_u), MathHelper.func_76128_c(entity.field_70161_v));
/*     */   }
/*     */   
/*     */   public Coord(INullSource entity) {
/*  42 */     this(entity.getPosX(), entity.getPosY(), entity.getPosZ());
/*     */   }
/*     */   
/*     */   public Coord(MovingObjectPosition mop, EntityPosition alternativePos, boolean before) {
/*  46 */     if (mop != null) {
/*  47 */       switch (mop.field_72313_a) {
/*     */       case BLOCK: 
/*  49 */         if (before) {
/*  50 */           this.x = (mop.field_72311_b + (mop.field_72310_e == 5 ? 1 : mop.field_72310_e == 4 ? -1 : 0));
/*  51 */           this.y = (mop.field_72312_c + (mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0));
/*  52 */           this.z = (mop.field_72309_d + (mop.field_72310_e == 3 ? 1 : mop.field_72310_e == 2 ? -1 : 0));
/*     */         } else {
/*  54 */           this.x = mop.field_72311_b;
/*  55 */           this.y = mop.field_72312_c;
/*  56 */           this.z = mop.field_72309_d;
/*     */         }
/*  58 */         break;
/*     */       case ENTITY: 
/*  60 */         this.x = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/*  61 */         this.y = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/*  62 */         this.z = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/*  63 */         break;
/*     */       case MISS: 
/*     */       default: 
/*  66 */         if (alternativePos != null) {
/*  67 */           this.x = MathHelper.func_76128_c(alternativePos.x);
/*  68 */           this.y = MathHelper.func_76128_c(alternativePos.y);
/*  69 */           this.z = MathHelper.func_76128_c(alternativePos.z);
/*     */         } else {
/*  71 */           this.x = 0;
/*  72 */           this.y = 0;
/*  73 */           this.z = 0;
/*     */         }
/*  75 */         break;
/*     */       }
/*     */     }
/*  78 */     else if (alternativePos != null) {
/*  79 */       this.x = MathHelper.func_76128_c(alternativePos.x);
/*  80 */       this.y = MathHelper.func_76128_c(alternativePos.y);
/*  81 */       this.z = MathHelper.func_76128_c(alternativePos.z);
/*     */     } else {
/*  83 */       this.x = 0;
/*  84 */       this.y = 0;
/*  85 */       this.z = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  92 */     if (obj == this) {
/*  93 */       return true;
/*     */     }
/*     */     
/*  96 */     if ((obj == null) || (obj.getClass() != getClass())) {
/*  97 */       return false;
/*     */     }
/*     */     
/* 100 */     Coord other = (Coord)obj;
/*     */     
/* 102 */     return (this.x == other.x) && (this.y == other.y) && (this.z == other.z);
/*     */   }
/*     */   
/*     */   public boolean isAtPosition(TileEntity tileEntity) {
/* 106 */     return (tileEntity != null) && (this.x == tileEntity.field_145851_c) && (this.y == tileEntity.field_145848_d) && (this.z == tileEntity.field_145849_e);
/*     */   }
/*     */   
/*     */   public Coord north() {
/* 110 */     return north(1);
/*     */   }
/*     */   
/*     */   public Coord north(int n) {
/* 114 */     return new Coord(this.x, this.y, this.z - n);
/*     */   }
/*     */   
/*     */   public Coord south() {
/* 118 */     return south(1);
/*     */   }
/*     */   
/*     */   public Coord south(int n) {
/* 122 */     return new Coord(this.x, this.y, this.z + n);
/*     */   }
/*     */   
/*     */   public Coord east() {
/* 126 */     return east(1);
/*     */   }
/*     */   
/*     */   public Coord east(int n) {
/* 130 */     return new Coord(this.x + n, this.y, this.z);
/*     */   }
/*     */   
/*     */   public Coord west() {
/* 134 */     return west(1);
/*     */   }
/*     */   
/*     */   public Coord west(int n) {
/* 138 */     return new Coord(this.x - n, this.y, this.z);
/*     */   }
/*     */   
/*     */   public Coord northEast() {
/* 142 */     return new Coord(this.x + 1, this.y, this.z - 1);
/*     */   }
/*     */   
/*     */   public Coord northWest() {
/* 146 */     return new Coord(this.x - 1, this.y, this.z - 1);
/*     */   }
/*     */   
/*     */   public Coord southEast() {
/* 150 */     return new Coord(this.x + 1, this.y, this.z + 1);
/*     */   }
/*     */   
/*     */   public Coord southWest() {
/* 154 */     return new Coord(this.x - 1, this.y, this.z + 1);
/*     */   }
/*     */   
/*     */   public Block getBlock(World world) {
/* 158 */     return getBlock(world, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public Block getBlock(World world, int offsetX, int offsetY, int offsetZ) {
/* 162 */     return world.func_147439_a(this.x + offsetX, this.y + offsetY, this.z + offsetZ);
/*     */   }
/*     */   
/*     */   public TileEntity getBlockTileEntity(World world) {
/* 166 */     return getBlockTileEntity(world, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public TileEntity getBlockTileEntity(World world, int offsetX, int offsetY, int offsetZ) {
/* 170 */     return world.func_147438_o(this.x + offsetX, this.y + offsetY, this.z + offsetZ);
/*     */   }
/*     */   
/*     */   public <T> T getTileEntity(IBlockAccess world, Class<T> clazz) {
/* 174 */     return (T)BlockUtil.getTileEntity(world, this.x, this.y, this.z, clazz);
/*     */   }
/*     */   
/*     */   public int getBlockMetadata(World world) {
/* 178 */     return getBlockMetadata(world, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public int getBlockMetadata(World world, int offsetX, int offsetY, int offsetZ) {
/* 182 */     return world.func_72805_g(this.x + offsetX, this.y + offsetY, this.z + offsetZ);
/*     */   }
/*     */   
/*     */   public void setNBT(NBTTagCompound nbtTag, String key) {
/* 186 */     nbtTag.func_74768_a(key + "X", this.x);
/* 187 */     nbtTag.func_74768_a(key + "Y", this.y);
/* 188 */     nbtTag.func_74768_a(key + "Z", this.z);
/*     */   }
/*     */   
/*     */   public static double distance(Coord first, Coord second) {
/* 192 */     double dX = first.x - second.x;
/* 193 */     double dY = first.y - second.y;
/* 194 */     double dZ = first.z - second.z;
/* 195 */     return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
/*     */   }
/*     */   
/*     */   public static double distance(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ) {
/* 199 */     double dX = firstX - secondX;
/* 200 */     double dY = firstY - secondY;
/* 201 */     double dZ = firstZ - secondZ;
/* 202 */     return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
/*     */   }
/*     */   
/*     */   public static double distanceSq(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ) {
/* 206 */     double dX = firstX - secondX;
/* 207 */     double dY = firstY - secondY;
/* 208 */     double dZ = firstZ - secondZ;
/* 209 */     return dX * dX + dY * dY + dZ * dZ;
/*     */   }
/*     */   
/*     */   public double distanceTo(Coord other) {
/* 213 */     double dX = other.x - this.x;
/* 214 */     double dY = other.y - this.y;
/* 215 */     double dZ = other.z - this.z;
/* 216 */     return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
/*     */   }
/*     */   
/*     */   public double distanceSqTo(Coord other) {
/* 220 */     double dX = other.x - this.x;
/* 221 */     double dY = other.y - this.y;
/* 222 */     double dZ = other.z - this.z;
/* 223 */     return dX * dX + dY * dY + dZ * dZ;
/*     */   }
/*     */   
/*     */   public double distanceSqTo(int x, int y, int z) {
/* 227 */     double dX = x - this.x;
/* 228 */     double dY = y - this.y;
/* 229 */     double dZ = z - this.z;
/* 230 */     return dX * dX + dY * dY + dZ * dZ;
/*     */   }
/*     */   
/*     */   public static Coord createFrom(NBTTagCompound nbtTag, String key) {
/* 234 */     if ((nbtTag.func_74764_b(key + "X")) && (nbtTag.func_74764_b(key + "Y")) && (nbtTag.func_74764_b(key + "Z"))) {
/* 235 */       return new Coord(nbtTag.func_74762_e(key + "X"), nbtTag.func_74762_e(key + "Y"), nbtTag.func_74762_e(key + "Z"));
/*     */     }
/* 237 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isWestOf(Coord coord)
/*     */   {
/* 242 */     return this.x < coord.x;
/*     */   }
/*     */   
/*     */   public boolean isNorthOf(Coord coord) {
/* 246 */     return this.z < coord.z;
/*     */   }
/*     */   
/*     */   public boolean setBlock(World world, Block block) {
/* 250 */     return world.func_147449_b(this.x, this.y, this.z, block);
/*     */   }
/*     */   
/*     */   public boolean setBlock(World world, Block block, int metadata, int flags) {
/* 254 */     return world.func_147465_d(this.x, this.y, this.z, block, metadata, flags);
/*     */   }
/*     */   
/*     */   public void setAir(World world) {
/* 258 */     world.func_147468_f(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public void markBlockForUpdate(World world) {
/* 262 */     world.func_147471_g(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public int getHeading(Coord destination) {
/* 266 */     double dX = this.x - destination.x;
/* 267 */     double dZ = this.z - destination.z;
/* 268 */     double yaw = Math.atan2(dZ, dX);
/*     */     
/* 270 */     double PI8 = 0.39269908169872414D;
/* 271 */     double PI2 = 1.5707963267948966D;
/* 272 */     if ((yaw > -0.39269908169872414D) && (yaw <= 0.39269908169872414D))
/* 273 */       return 6;
/* 274 */     if ((yaw > 0.39269908169872414D) && (yaw <= 1.1780972450961724D))
/* 275 */       return 7;
/* 276 */     if ((yaw > 1.1780972450961724D) && (yaw <= 1.9634954084936207D))
/* 277 */       return 0;
/* 278 */     if ((yaw > 1.9634954084936207D) && (yaw <= 2.748893571891069D))
/* 279 */       return 1;
/* 280 */     if ((yaw > 2.748893571891069D) || (yaw <= -2.748893571891069D))
/* 281 */       return 2;
/* 282 */     if ((yaw > -2.748893571891069D) && (yaw <= -1.9634954084936207D))
/* 283 */       return 3;
/* 284 */     if ((yaw > -1.9634954084936207D) && (yaw <= -1.1780972450961724D)) {
/* 285 */       return 4;
/*     */     }
/* 287 */     return 5;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 293 */     return String.format("%d, %d, %d", new Object[] { Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z) });
/*     */   }
/*     */   
/*     */   public NBTTagCompound toTagNBT() {
/* 297 */     NBTTagCompound nbt = new NBTTagCompound();
/* 298 */     nbt.func_74768_a("posX", this.x);
/* 299 */     nbt.func_74768_a("posY", this.y);
/* 300 */     nbt.func_74768_a("posZ", this.z);
/* 301 */     return nbt;
/*     */   }
/*     */   
/*     */   public static Coord fromTagNBT(NBTTagCompound nbt) {
/* 305 */     if ((nbt.func_74764_b("posX")) && (nbt.func_74764_b("posY")) && (nbt.func_74764_b("posZ"))) {
/* 306 */       return new Coord(nbt.func_74762_e("posX"), nbt.func_74762_e("posY"), nbt.func_74762_e("posZ"));
/*     */     }
/* 308 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isMatch(int x, int y, int z)
/*     */   {
/* 313 */     return (this.x == x) && (this.y == y) && (this.z == z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/Coord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */