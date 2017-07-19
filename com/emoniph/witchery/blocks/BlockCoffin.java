/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirectional;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayer.EnumStatus;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockCoffin extends BlockBaseContainer
/*     */ {
/*  34 */   private static final int[][] DIRECTIONS = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
/*     */   
/*     */   public BlockCoffin() {
/*  37 */     super(Material.field_151575_d, TileEntityCoffin.class);
/*  38 */     this.registerWithCreateTab = false;
/*  39 */     func_149711_c(1.0F);
/*  40 */     func_149649_H();
/*  41 */     setupBounds();
/*     */   }
/*     */   
/*     */   public static class TileEntityCoffin extends TileEntity
/*     */   {
/*     */     private boolean open;
/*     */     public float lidAngle;
/*     */     public float prevLidAngle;
/*     */     
/*     */     public void func_145845_h() {
/*  51 */       this.prevLidAngle = this.lidAngle;
/*     */       
/*  53 */       if ((this.open) && (this.lidAngle == 0.0F)) {
/*  54 */         double d1 = this.field_145851_c + 0.5D;
/*  55 */         double d0 = this.field_145849_e + 0.5D;
/*  56 */         this.field_145850_b.func_72908_a(d1, this.field_145848_d + 0.5D, d0, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       }
/*     */       
/*     */ 
/*  60 */       if (((!this.open) && (this.lidAngle > 0.0F)) || ((this.open) && (this.lidAngle < 1.0F))) {
/*  61 */         float f = 0.1F;
/*  62 */         float f1 = this.lidAngle;
/*     */         
/*  64 */         if (this.open) {
/*  65 */           this.lidAngle += 0.1F;
/*     */         } else {
/*  67 */           this.lidAngle -= 0.1F;
/*     */         }
/*     */         
/*  70 */         if (this.lidAngle > 1.0F) {
/*  71 */           this.lidAngle = 1.0F;
/*     */         }
/*     */         
/*  74 */         float f2 = 0.5F;
/*     */         
/*  76 */         if ((this.lidAngle < f2) && (f1 >= f2)) {
/*  77 */           double d0 = this.field_145851_c + 0.5D;
/*  78 */           double d2 = this.field_145849_e + 0.5D;
/*     */           
/*  80 */           this.field_145850_b.func_72908_a(d0, this.field_145848_d + 0.5D, d2, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */         }
/*     */         
/*     */ 
/*  84 */         if (this.lidAngle < 0.0F) {
/*  85 */           this.lidAngle = 0.0F;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/*  92 */       NBTTagCompound nbtTag = new NBTTagCompound();
/*  93 */       func_145841_b(nbtTag);
/*  94 */       nbtTag.func_74776_a("Angle", this.lidAngle);
/*  95 */       nbtTag.func_74776_a("AnglePrev", this.prevLidAngle);
/*  96 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 101 */       super.onDataPacket(net, packet);
/* 102 */       NBTTagCompound nbtTag = packet.func_148857_g();
/* 103 */       func_145839_a(nbtTag);
/* 104 */       this.lidAngle = nbtTag.func_74760_g("Angle");
/* 105 */       this.prevLidAngle = nbtTag.func_74760_g("AnglePrev");
/* 106 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 111 */       super.func_145841_b(nbtRoot);
/* 112 */       nbtRoot.func_74757_a("Opened", this.open);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 117 */       super.func_145839_a(nbtRoot);
/* 118 */       this.open = nbtRoot.func_74767_n("Opened");
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getDirection(int meta) {
/* 123 */     return meta & 0x3;
/*     */   }
/*     */   
/*     */   public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player)
/*     */   {
/* 128 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 134 */     if (world.field_72995_K) {
/* 135 */       return true;
/*     */     }
/* 137 */     int i1 = world.func_72805_g(x, y, z);
/*     */     
/* 139 */     int origX = x;int origZ = z;
/* 140 */     if (!isBlockHeadOfBed(i1)) {
/* 141 */       int j1 = getDirection(i1);
/* 142 */       x += DIRECTIONS[j1][0];
/* 143 */       z += DIRECTIONS[j1][1];
/*     */       
/* 145 */       if (world.func_147439_a(x, y, z) != this) {
/* 146 */         return true;
/*     */       }
/*     */       
/* 149 */       i1 = world.func_72805_g(x, y, z);
/*     */     } else {
/* 151 */       int j1 = getDirection(i1);
/* 152 */       origX -= DIRECTIONS[j1][0];
/* 153 */       origZ -= DIRECTIONS[j1][1];
/*     */     }
/*     */     
/* 156 */     if (player.func_70093_af()) {
/* 157 */       TileEntityCoffin tile = (TileEntityCoffin)BlockUtil.getTileEntity(world, x, y, z, TileEntityCoffin.class);
/* 158 */       if (tile != null) {
/* 159 */         if ((tile.open) && (isBedOccupied(i1))) {
/* 160 */           EntityPlayer entityplayer1 = null;
/* 161 */           Iterator iterator = world.field_73010_i.iterator();
/*     */           
/* 163 */           while (iterator.hasNext()) {
/* 164 */             EntityPlayer entityplayer2 = (EntityPlayer)iterator.next();
/*     */             
/* 166 */             if (entityplayer2.func_70608_bn()) {
/* 167 */               ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
/*     */               
/* 169 */               if ((chunkcoordinates.field_71574_a == x) && (chunkcoordinates.field_71572_b == y) && (chunkcoordinates.field_71573_c == z))
/*     */               {
/* 171 */                 entityplayer1 = entityplayer2;
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 176 */           if (entityplayer1 != null) {
/* 177 */             return true;
/*     */           }
/*     */         }
/*     */         
/* 181 */         if ((world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN)) || (world.isSideSolid(origX, y + 1, origZ, ForgeDirection.DOWN)))
/*     */         {
/* 183 */           return true;
/*     */         }
/*     */         
/* 186 */         tile.open = (!tile.open);
/* 187 */         if (!isBlockHeadOfBed(i1)) {
/* 188 */           int j1 = getDirection(i1);
/*     */           
/* 190 */           TileEntityCoffin tile2 = (TileEntityCoffin)BlockUtil.getTileEntity(world, x + DIRECTIONS[j1][0], y, z + DIRECTIONS[j1][1], TileEntityCoffin.class);
/*     */           
/* 192 */           if (tile2 != null) {
/* 193 */             tile2.open = tile.open;
/* 194 */             world.func_147471_g(tile2.field_145851_c, tile2.field_145848_d, tile2.field_145849_e);
/*     */           }
/*     */         }
/*     */         else {
/* 198 */           int j1 = getDirection(i1);
/*     */           
/* 200 */           TileEntityCoffin tile2 = (TileEntityCoffin)BlockUtil.getTileEntity(world, x - DIRECTIONS[j1][0], y, z - DIRECTIONS[j1][1], TileEntityCoffin.class);
/*     */           
/* 202 */           if (tile2 != null) {
/* 203 */             tile2.open = tile.open;
/* 204 */             world.func_147471_g(tile2.field_145851_c, tile2.field_145848_d, tile2.field_145849_e);
/*     */           }
/*     */         }
/* 207 */         world.func_147471_g(x, y, z);
/*     */       }
/* 209 */       return true;
/*     */     }
/*     */     
/* 212 */     TileEntityCoffin tile = (TileEntityCoffin)BlockUtil.getTileEntity(world, x, y, z, TileEntityCoffin.class);
/*     */     
/* 214 */     if (tile != null) {
/* 215 */       if (!tile.open) {
/* 216 */         player.func_146105_b(new ChatComponentTranslation("witchery.nosleep.closedcoffin", new Object[0]));
/*     */         
/* 218 */         return true;
/*     */       }
/*     */     } else {
/* 221 */       return true;
/*     */     }
/*     */     
/* 224 */     if ((world.field_73011_w.func_76567_e()) && (world.func_72807_a(x, z) != net.minecraft.world.biome.BiomeGenBase.field_76778_j)) {
/* 225 */       if (isBedOccupied(i1)) {
/* 226 */         EntityPlayer entityplayer1 = null;
/* 227 */         Iterator iterator = world.field_73010_i.iterator();
/*     */         
/* 229 */         while (iterator.hasNext()) {
/* 230 */           EntityPlayer entityplayer2 = (EntityPlayer)iterator.next();
/*     */           
/* 232 */           if (entityplayer2.func_70608_bn()) {
/* 233 */             ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
/*     */             
/* 235 */             if ((chunkcoordinates.field_71574_a == x) && (chunkcoordinates.field_71572_b == y) && (chunkcoordinates.field_71573_c == z))
/*     */             {
/* 237 */               entityplayer1 = entityplayer2;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 242 */         if (entityplayer1 != null) {
/* 243 */           player.func_146105_b(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
/*     */           
/* 245 */           return true;
/*     */         }
/*     */         
/* 248 */         setBedOccupied(world, x, y, z, false);
/*     */       }
/*     */       
/* 251 */       EntityPlayer.EnumStatus enumstatus = player.func_71018_a(x, y, z);
/*     */       
/* 253 */       if (enumstatus == EntityPlayer.EnumStatus.OK) {
/* 254 */         setBedOccupied(world, x, y, z, true);
/* 255 */         return true;
/*     */       }
/* 257 */       if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
/* 258 */         player.func_146105_b(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
/*     */       }
/* 260 */       else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
/* 261 */         player.func_146105_b(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
/*     */       }
/*     */       
/*     */ 
/* 265 */       return true;
/*     */     }
/*     */     
/* 268 */     double d2 = x + 0.5D;
/* 269 */     double d0 = y + 0.5D;
/* 270 */     double d1 = z + 0.5D;
/* 271 */     world.func_147468_f(x, y, z);
/* 272 */     int k1 = getDirection(i1);
/* 273 */     x += DIRECTIONS[k1][0];
/* 274 */     z += DIRECTIONS[k1][1];
/*     */     
/* 276 */     if (world.func_147439_a(x, y, z) == this) {
/* 277 */       world.func_147468_f(x, y, z);
/* 278 */       d2 = (d2 + x + 0.5D) / 2.0D;
/* 279 */       d0 = (d0 + y + 0.5D) / 2.0D;
/* 280 */       d1 = (d1 + z + 0.5D) / 2.0D;
/*     */     }
/*     */     
/* 283 */     world.func_72885_a((Entity)null, x + 0.5F, y + 0.5F, z + 0.5F, 5.0F, true, true);
/* 284 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/* 291 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 296 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 301 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 306 */     super.func_149719_a(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 313 */     int l = world.func_72805_g(x, y, z);
/* 314 */     int i1 = getDirection(l);
/*     */     
/* 316 */     if (isBlockHeadOfBed(l)) {
/* 317 */       if (world.func_147439_a(x - DIRECTIONS[i1][0], y, z - DIRECTIONS[i1][1]) != this) {
/* 318 */         world.func_147468_f(x, y, z);
/*     */       }
/* 320 */     } else if (world.func_147439_a(x + DIRECTIONS[i1][0], y, z + DIRECTIONS[i1][1]) != this) {
/* 321 */       world.func_147468_f(x, y, z);
/*     */       
/* 323 */       if (!world.field_72995_K) {
/* 324 */         func_149697_b(world, x, y, z, l, 0);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int meta, Random rand, int p_149650_3_)
/*     */   {
/* 331 */     return isBlockHeadOfBed(meta) ? Item.func_150899_d(0) : Witchery.Items.COFFIN;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void setupBounds() {}
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB mask, List boxes, Entity entity)
/*     */   {
/* 341 */     TileEntityCoffin tile = (TileEntityCoffin)BlockUtil.getTileEntity(world, x, y, z, TileEntityCoffin.class);
/* 342 */     if ((tile != null) && (!tile.open)) {
/* 343 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 344 */       super.func_149743_a(world, x, y, z, mask, boxes, entity);
/* 345 */       return;
/*     */     }
/*     */     
/* 348 */     int meta = world.func_72805_g(x, y, z);
/* 349 */     float baseHeight = 0.4375F;
/* 350 */     float wallThick = 0.05F;
/* 351 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, baseHeight, 1.0F);
/* 352 */     super.func_149743_a(world, x, y, z, mask, boxes, entity);
/*     */     
/* 354 */     int direction = getDirection(meta);
/*     */     
/* 356 */     boolean head = isBlockHeadOfBed(meta);
/*     */     
/* 358 */     boolean n = true;boolean s = true;boolean e = true;boolean w = true;
/* 359 */     boolean n1 = false;boolean s1 = false;boolean e1 = false;boolean w1 = false;
/*     */     
/* 361 */     switch (direction) {
/*     */     case 0: 
/* 363 */       n = !head;
/* 364 */       s = head;
/* 365 */       e1 = true;
/* 366 */       break;
/*     */     case 1: 
/* 368 */       e = !head;
/* 369 */       w = head;
/* 370 */       s1 = true;
/* 371 */       break;
/*     */     case 2: 
/* 373 */       s = !head;
/* 374 */       n = head;
/* 375 */       w1 = true;
/* 376 */       break;
/*     */     case 3: 
/* 378 */       w = !head;
/* 379 */       e = head;
/* 380 */       n1 = true;
/*     */     }
/*     */     
/*     */     
/* 384 */     if (n) {
/* 385 */       func_149676_a(0.0F, baseHeight, 0.0F, 1.0F, n1 ? 2.0F : 1.0F, wallThick);
/* 386 */       super.func_149743_a(world, x, y, z, mask, boxes, entity);
/*     */     }
/*     */     
/* 389 */     if (s) {
/* 390 */       func_149676_a(0.0F, baseHeight, 1.0F - wallThick, 1.0F, s1 ? 2.0F : 1.0F, 1.0F);
/* 391 */       super.func_149743_a(world, x, y, z, mask, boxes, entity);
/*     */     }
/*     */     
/* 394 */     if (w) {
/* 395 */       func_149676_a(0.0F, baseHeight, 0.0F, wallThick, w1 ? 2.0F : 1.0F, 1.0F);
/* 396 */       super.func_149743_a(world, x, y, z, mask, boxes, entity);
/*     */     }
/*     */     
/* 399 */     if (e) {
/* 400 */       func_149676_a(1.0F - wallThick, baseHeight, 0.0F, 1.0F, e1 ? 2.0F : 1.0F, 1.0F);
/* 401 */       super.func_149743_a(world, x, y, z, mask, boxes, entity);
/*     */     }
/*     */     
/* 404 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/* 405 */     setupBounds();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 410 */     return AxisAlignedBB.func_72330_a(x + this.field_149759_B, y + this.field_149760_C, z + this.field_149754_D, x + this.field_149755_E, y + 1.0D, z + this.field_149757_G);
/*     */   }
/*     */   
/*     */   public static boolean isBlockHeadOfBed(int meta)
/*     */   {
/* 415 */     return (meta & 0x8) != 0;
/*     */   }
/*     */   
/*     */   public static boolean isBedOccupied(int meta) {
/* 419 */     return (meta & 0x4) != 0;
/*     */   }
/*     */   
/*     */   public static void setBedOccupied(World world, int x, int y, int z, boolean p_149979_4_) {
/* 423 */     int l = world.func_72805_g(x, y, z);
/*     */     
/* 425 */     if (p_149979_4_) {
/* 426 */       l |= 0x4;
/*     */     } else {
/* 428 */       l &= 0xFFFFFFFB;
/*     */     }
/*     */     
/* 431 */     world.func_72921_c(x, y, z, l, 3);
/*     */   }
/*     */   
/*     */   public static ChunkCoordinates func_149977_a(World world, int x, int y, int z, int p_149977_4_) {
/* 435 */     int i1 = world.func_72805_g(x, y, z);
/* 436 */     int j1 = BlockDirectional.func_149895_l(i1);
/*     */     
/* 438 */     for (int k1 = 0; k1 <= 1; k1++) {
/* 439 */       int l1 = x - DIRECTIONS[j1][0] * k1 - 1;
/* 440 */       int i2 = z - DIRECTIONS[j1][1] * k1 - 1;
/* 441 */       int j2 = l1 + 2;
/* 442 */       int k2 = i2 + 2;
/*     */       
/* 444 */       for (int l2 = l1; l2 <= j2; l2++) {
/* 445 */         for (int i3 = i2; i3 <= k2; i3++) {
/* 446 */           if ((World.func_147466_a(world, l2, y - 1, i3)) && (!world.func_147439_a(l2, y, i3).func_149688_o().func_76218_k()) && (!world.func_147439_a(l2, y + 1, i3).func_149688_o().func_76218_k()))
/*     */           {
/*     */ 
/* 449 */             if (p_149977_4_ <= 0) {
/* 450 */               return new ChunkCoordinates(l2, y, i3);
/*     */             }
/*     */             
/* 453 */             p_149977_4_--;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 459 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149690_a(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_)
/*     */   {
/* 465 */     if (!isBlockHeadOfBed(p_149690_5_)) {
/* 466 */       super.func_149690_a(world, x, y, z, p_149690_5_, p_149690_6_, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_149656_h()
/*     */   {
/* 472 */     return 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World world, int x, int y, int z)
/*     */   {
/* 478 */     return Witchery.Items.COFFIN;
/*     */   }
/*     */   
/*     */   public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player)
/*     */   {
/* 483 */     if ((player.field_71075_bZ.field_75098_d) && (isBlockHeadOfBed(meta))) {
/* 484 */       int i1 = getDirection(meta);
/* 485 */       x -= DIRECTIONS[i1][0];
/* 486 */       z -= DIRECTIONS[i1][1];
/*     */       
/* 488 */       if (world.func_147439_a(x, y, z) == this) {
/* 489 */         world.func_147468_f(x, y, z);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockCoffin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */