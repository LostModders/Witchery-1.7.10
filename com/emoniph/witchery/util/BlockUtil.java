/*     */ package com.emoniph.witchery.util;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockUtil
/*     */ {
/*     */   public static Block registerBlock(Block block, String blockName)
/*     */   {
/*  24 */     int index = blockName.indexOf(':');
/*  25 */     if (index != -1) {
/*  26 */       blockName = blockName.substring(index + 1);
/*     */     }
/*  28 */     return GameRegistry.registerBlock(block, blockName);
/*     */   }
/*     */   
/*     */   public static Block registerBlock(Block block, Class<? extends ItemBlock> clazzItem, String blockName)
/*     */   {
/*  33 */     int index = blockName.indexOf(':');
/*  34 */     if (index != -1) {
/*  35 */       blockName = blockName.substring(index + 1);
/*     */     }
/*  37 */     return GameRegistry.registerBlock(block, clazzItem, blockName);
/*     */   }
/*     */   
/*     */   public static Block getBlock(World world, int posX, int posY, int posZ) {
/*  41 */     return world.func_147439_a(posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public static Block getBlock(World world, double posX, double posY, double posZ) {
/*  45 */     int x = MathHelper.func_76128_c(posX);
/*  46 */     int y = MathHelper.func_76128_c(posY);
/*  47 */     int z = MathHelper.func_76128_c(posZ);
/*  48 */     return getBlock(world, x, y, z);
/*     */   }
/*     */   
/*     */   public static Block getBlock(World world, MovingObjectPosition mop) {
/*  52 */     return getBlock(world, mop, false);
/*     */   }
/*     */   
/*     */   public static boolean isReplaceableBlock(World world, int posX, int posY, int posZ) {
/*  56 */     return isReplaceableBlock(world, posX, posY, posZ, null);
/*     */   }
/*     */   
/*     */   public static boolean isReplaceableBlock(World world, int posX, int posY, int posZ, EntityLivingBase player) {
/*  60 */     Block block = getBlock(world, posX, posY, posZ);
/*  61 */     if (player != null) {
/*  62 */       int meta = world.func_72805_g(posX, posY, posZ);
/*  63 */       if (!BlockProtect.checkModsForBreakOK(world, posX, posY, posZ, block, meta, player)) {
/*  64 */         return false;
/*     */       }
/*     */     }
/*     */     
/*  68 */     if (block != null) {
/*  69 */       return block.func_149688_o().func_76222_j();
/*     */     }
/*  71 */     return true;
/*     */   }
/*     */   
/*     */   public static Material getBlockMaterial(EntityPlayer player)
/*     */   {
/*  76 */     return getBlockMaterial(player, 0);
/*     */   }
/*     */   
/*     */   public static Material getBlockMaterial(EntityPlayer player, int yOffset) {
/*  80 */     int posX = MathHelper.func_76128_c(player.field_70165_t);
/*  81 */     int posY = MathHelper.func_76128_c(player.field_70121_D.field_72338_b) + yOffset;
/*  82 */     int posZ = MathHelper.func_76128_c(player.field_70161_v);
/*  83 */     return getBlockMaterial(player.field_70170_p, posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public static Material getBlockMaterial(World world, int posX, int posY, int posZ) {
/*  87 */     Block block = getBlock(world, posX, posY, posZ);
/*  88 */     if (block != null) {
/*  89 */       return block.func_149688_o();
/*     */     }
/*  91 */     return Material.field_151579_a;
/*     */   }
/*     */   
/*     */   public static Block getBlock(World world, MovingObjectPosition mop, boolean before)
/*     */   {
/*  96 */     if (mop == null)
/*  97 */       return null;
/*  98 */     if (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) {
/*  99 */       if (before) {
/* 100 */         int x = mop.field_72311_b + (mop.field_72310_e == 5 ? 1 : mop.field_72310_e == 4 ? -1 : 0);
/* 101 */         int z = mop.field_72309_d + (mop.field_72310_e == 3 ? 1 : mop.field_72310_e == 2 ? -1 : 0);
/* 102 */         int y = mop.field_72312_c + (mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0);
/* 103 */         if ((mop.field_72310_e == 1) && (!world.func_147439_a(x, mop.field_72312_c, z).func_149688_o().func_76220_a())) {
/* 104 */           y--;
/*     */         }
/* 106 */         return getBlock(world, x, y, z);
/*     */       }
/* 108 */       return getBlock(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */     }
/*     */     
/* 111 */     int posX = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/* 112 */     int posY = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u) - 1;
/* 113 */     int posZ = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/* 114 */     return getBlock(world, posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public static int[] getBlockCoords(World world, MovingObjectPosition mop, boolean before)
/*     */   {
/* 119 */     if (mop == null)
/* 120 */       return null;
/* 121 */     if (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) {
/* 122 */       if (before) {
/* 123 */         int x = mop.field_72311_b + (mop.field_72310_e == 5 ? 1 : mop.field_72310_e == 4 ? -1 : 0);
/* 124 */         int z = mop.field_72309_d + (mop.field_72310_e == 3 ? 1 : mop.field_72310_e == 2 ? -1 : 0);
/* 125 */         int y = mop.field_72312_c + (mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0);
/* 126 */         if ((mop.field_72310_e == 1) && (!world.func_147439_a(x, mop.field_72312_c, z).func_149688_o().func_76220_a())) {
/* 127 */           y--;
/*     */         }
/* 129 */         return new int[] { x, y, z };
/*     */       }
/* 131 */       return new int[] { mop.field_72311_b, mop.field_72312_c, mop.field_72309_d };
/*     */     }
/*     */     
/* 134 */     int posX = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/* 135 */     int posY = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u) - 1;
/* 136 */     int posZ = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/* 137 */     return new int[] { posX, posY, posZ };
/*     */   }
/*     */   
/*     */   public static int getBlockMetadata(World world, int posX, int posY, int posZ)
/*     */   {
/* 142 */     int blockMetadata = world.func_72805_g(posX, posY, posZ);
/* 143 */     return blockMetadata;
/*     */   }
/*     */   
/*     */   public static <T> T getTileEntity(IBlockAccess world, int posX, int posY, int posZ, Class<T> clazz) {
/* 147 */     TileEntity tile = world.func_147438_o(posX, posY, posZ);
/* 148 */     if ((tile != null) && (clazz.isAssignableFrom(tile.getClass()))) {
/* 149 */       return (T)clazz.cast(tile);
/*     */     }
/* 151 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void setBlock(World world, int posX, int posY, int posZ, Block newBlock, int newMetadata, int updateFlags)
/*     */   {
/* 157 */     world.func_147465_d(posX, posY, posZ, newBlock != null ? newBlock : Blocks.field_150350_a, newMetadata, updateFlags);
/*     */   }
/*     */   
/*     */   public static void setBlock(World world, int posX, int posY, int posZ, Block newBlock) {
/* 161 */     world.func_147449_b(posX, posY, posZ, newBlock != null ? newBlock : Blocks.field_150350_a);
/*     */   }
/*     */   
/*     */   public static void setBlock(World world, double posX, double posY, double posZ, Block block) {
/* 165 */     int x = MathHelper.func_76128_c(posX);
/* 166 */     int y = MathHelper.func_76128_c(posY);
/* 167 */     int z = MathHelper.func_76128_c(posZ);
/* 168 */     setBlock(world, x, y, z, block);
/*     */   }
/*     */   
/*     */   public static void setBlock(World world, int posX, int posY, int posZ, ItemBlock item, int damage, int updateFlags) {
/* 172 */     world.func_147465_d(posX, posY, posZ, item.field_150939_a, damage, updateFlags);
/*     */   }
/*     */   
/*     */   public static void setMetadata(World world, int posX, int posY, int posZ, int newMetadata) {
/* 176 */     setMetadata(world, posX, posY, posZ, newMetadata, 3);
/*     */   }
/*     */   
/*     */   public static void setMetadata(World world, int posX, int posY, int posZ, int newMetadata, int updateFlags) {
/* 180 */     world.func_72921_c(posX, posY, posZ, newMetadata, updateFlags);
/*     */   }
/*     */   
/*     */   public static void setAirBlock(World world, int x, int y, int z) {
/* 184 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */   
/*     */   public static void setAirBlock(World world, int x, int y, int z, int updateFlags) {
/* 188 */     world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, updateFlags);
/*     */   }
/*     */   
/*     */   public static void notifyNeighborsOfBlockChange(World world, int xCoord, int yCoord, int zCoord, Block blockType) {
/* 192 */     world.func_147459_d(xCoord, yCoord, zCoord, blockType);
/*     */   }
/*     */   
/*     */   public static boolean isImmovableBlock(Block block) {
/* 196 */     if ((block == Witchery.Blocks.ALTAR) || (block == Witchery.Blocks.VOID_BRAMBLE)) {
/* 197 */       return true;
/*     */     }
/* 199 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isImmovableBlock(TileEntity tile)
/*     */   {
/* 204 */     if ((tile instanceof BlockFetish.TileEntityFetish)) {
/* 205 */       return ((BlockFetish.TileEntityFetish)tile).isSpectral();
/*     */     }
/* 207 */     return false;
/*     */   }
/*     */   
/*     */   public static void setBlockDefaultDirection(World world, int posX, int posY, int posZ)
/*     */   {
/* 212 */     if (!world.field_72995_K) {
/* 213 */       Block l = world.func_147439_a(posX, posY, posZ - 1);
/* 214 */       Block i1 = world.func_147439_a(posX, posY, posZ + 1);
/* 215 */       Block j1 = world.func_147439_a(posX - 1, posY, posZ);
/* 216 */       Block k1 = world.func_147439_a(posX + 1, posY, posZ);
/* 217 */       byte b0 = 3;
/*     */       
/* 219 */       if ((l.func_149662_c()) && (!i1.func_149662_c())) {
/* 220 */         b0 = 3;
/*     */       }
/*     */       
/* 223 */       if ((i1.func_149662_c()) && (!l.func_149662_c())) {
/* 224 */         b0 = 2;
/*     */       }
/*     */       
/* 227 */       if ((j1.func_149662_c()) && (!k1.func_149662_c())) {
/* 228 */         b0 = 5;
/*     */       }
/*     */       
/* 231 */       if ((k1.func_149662_c()) && (!j1.func_149662_c())) {
/* 232 */         b0 = 4;
/*     */       }
/*     */       
/* 235 */       world.func_72921_c(posX, posY, posZ, b0, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isSolid(World world, int posX, int posY, int posZ)
/*     */   {
/* 241 */     Block block = getBlock(world, posX, posY, posZ);
/* 242 */     if (block != null) {
/* 243 */       return !block.func_149688_o().func_76222_j();
/*     */     }
/* 245 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isNormalCube(Block block)
/*     */   {
/* 250 */     return (block.func_149688_o().func_76230_c()) && (block.func_149686_d());
/*     */   }
/*     */   
/*     */   public static Coord getClosestPlantableBlock(World world, int x, int y, int z, ForgeDirection side, EntityLivingBase entity) {
/* 254 */     return getClosestPlantableBlock(world, x, y, z, side, entity, false);
/*     */   }
/*     */   
/*     */   public static Coord getClosestPlantableBlock(World world, int x, int y, int z, ForgeDirection side, EntityLivingBase entity, boolean allowAir) {
/* 258 */     boolean foundBase = false;
/* 259 */     if ((isReplaceableBlock(world, x, y, z)) && ((!allowAir) || (!world.func_147437_c(x, y, z)))) {
/*     */       do {
/* 261 */         y--;
/* 262 */       } while (isReplaceableBlock(world, x, y, z));
/* 263 */       foundBase = true;
/* 264 */     } else if ((side == ForgeDirection.UP) || (side == ForgeDirection.UNKNOWN)) {
/* 265 */       foundBase = true;
/* 266 */     } else if (side != ForgeDirection.DOWN) {
/* 267 */       x += side.offsetX;
/* 268 */       z += side.offsetZ;
/* 269 */       if (isReplaceableBlock(world, x, y, z)) {
/* 270 */         y--;
/* 271 */         foundBase = !isReplaceableBlock(world, x, y, z);
/*     */       }
/*     */     }
/*     */     
/* 275 */     if (foundBase) {
/* 276 */       Block replaceBlock = world.func_147439_a(x, y + 1, z);
/* 277 */       int replaceMeta = world.func_72805_g(x, y + 1, z);
/* 278 */       if (BlockProtect.checkModsForBreakOK(world, x, y + 1, z, replaceBlock, replaceMeta, entity)) {
/* 279 */         return new Coord(x, y + 1, z);
/*     */       }
/*     */     }
/* 282 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 287 */   public static boolean setBlockIfReplaceable(World world, int x, int y, int z, Block block) { return setBlockIfReplaceable(world, x, y, z, block, 0); }
/*     */   
/*     */   public static boolean setBlockIfReplaceable(World world, int x, int y, int z, Block block, int meta) {
/* 290 */     Block currentBlock = world.func_147439_a(x, y, z);
/* 291 */     if ((currentBlock != null) && (currentBlock.isReplaceable(world, x, y, z))) {
/* 292 */       world.func_147465_d(x, y, z, block, meta, 3);
/* 293 */       return true;
/*     */     }
/* 295 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/BlockUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */