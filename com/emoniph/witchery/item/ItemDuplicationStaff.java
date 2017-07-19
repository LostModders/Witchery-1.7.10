/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.BlockRotatedPillar;
/*     */ import net.minecraft.block.BlockStairs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemDuplicationStaff
/*     */   extends ItemBase
/*     */ {
/*     */   public ItemDuplicationStaff()
/*     */   {
/*  36 */     func_77625_d(1);
/*  37 */     func_77664_n();
/*     */   }
/*     */   
/*     */   public static enum Rotation {
/*  41 */     NONE,  DEGREES_90,  DEGREES_180,  DEGREES_270;
/*     */     
/*     */     private Rotation() {}
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  47 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.epic; }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  52 */     if ((!world.field_72995_K) && (player.func_70093_af())) {
/*  53 */       MovingObjectPosition pickedBlock = InfusionOtherwhere.doCustomRayTrace(world, player, true, 6.0D);
/*  54 */       if (((pickedBlock == null) || (pickedBlock.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)) && (stack.func_77942_o())) {
/*  55 */         NBTTagCompound nbtRoot = stack.func_77978_p();
/*  56 */         nbtRoot.func_82580_o("SavedSchematic");
/*  57 */         nbtRoot.func_82580_o("Marker");
/*  58 */         SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/*     */       }
/*     */     }
/*  61 */     return stack;
/*     */   }
/*     */   
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  66 */     if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("SavedSchematic"))) {
/*  67 */       if (!player.func_70093_af()) {
/*  68 */         int rotation = stack.func_77978_p().func_74762_e("Rotation");
/*  69 */         rotation++;
/*  70 */         if (rotation > 3) {
/*  71 */           rotation = 0;
/*     */         }
/*  73 */         stack.func_77978_p().func_74768_a("Rotation", rotation);
/*  74 */         placeSchematic(stack, world, x, y + 1, z, player, Rotation.values()[rotation], true);
/*     */       } else {
/*  76 */         placeSchematic(stack, world, x, y + 1, z, player, Rotation.NONE, true);
/*  77 */         stack.func_77978_p().func_74768_a("Rotation", 0);
/*     */       }
/*  79 */     } else if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("Marker"))) {
/*  80 */       saveSchematic(stack, world, x, y, z, player);
/*     */     } else {
/*  82 */       setMarker(stack, world, x, y, z, player);
/*     */     }
/*     */     
/*  85 */     return !world.field_72995_K;
/*     */   }
/*     */   
/*     */   private void setMarker(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
/*  89 */     if (!world.field_72995_K) {
/*  90 */       if (!stack.func_77942_o()) {
/*  91 */         stack.func_77982_d(new NBTTagCompound());
/*     */       }
/*  93 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/*  94 */       Coord marker = new Coord(x, y, z);
/*  95 */       nbtRoot.func_74782_a("Marker", marker.toTagNBT());
/*  96 */       ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, 0.5D + x, y, 0.5D + z, 1.0D, 1.0D, 16);
/*     */     }
/*     */   }
/*     */   
/*     */   private void saveSchematic(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
/* 101 */     if (!world.field_72995_K) {
/* 102 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 103 */       if (nbtRoot != null) {
/* 104 */         PrintWriter writer = null;
/* 105 */         PrintWriter writer2 = null;
/*     */         try {
/* 107 */           Coord marker = Coord.fromTagNBT(nbtRoot.func_74775_l("Marker"));
/* 108 */           NBTTagList nbtList = new NBTTagList();
/* 109 */           ArrayList<Byte> bytes = new ArrayList();
/*     */           
/* 111 */           int width = Math.max(marker.x, x) - Math.min(marker.x, x) - 1;
/* 112 */           int height = Math.max(marker.y, y) - Math.min(marker.y, y) - 1;
/* 113 */           int depth = Math.max(marker.z, z) - Math.min(marker.z, z) - 1;
/*     */           
/* 115 */           File file = Config.instance().dupStaffSaveTemplate ? new File(String.format("%s/schematic.txt", new Object[] { Witchery.configDirectoryPath })) : null;
/* 116 */           File file2 = Config.instance().dupStaffSaveTemplate ? new File(String.format("%s/schematic2.txt", new Object[] { Witchery.configDirectoryPath })) : null;
/* 117 */           if ((file != null) && (!file.exists())) {
/* 118 */             file.createNewFile();
/*     */           }
/*     */           
/*     */ 
/* 122 */           writer = new PrintWriter(file);
/* 123 */           if (writer != null) {
/* 124 */             writer.println(String.format("final NBTTagCompound nbtSchematic = new NBTTagCompound();", new Object[0]));
/* 125 */             writer.println(String.format("final NBTTagList nbtList = new NBTTagList();", new Object[0]));
/* 126 */             writer.println(String.format("NBTTagCompound nbtBlock;", new Object[0]));
/*     */           }
/*     */           
/* 129 */           if ((file2 != null) && (!file2.exists())) {
/* 130 */             file2.createNewFile();
/*     */           }
/*     */           
/*     */ 
/* 134 */           writer2 = new PrintWriter(file2);
/* 135 */           if ((writer2 == null) || (
/*     */           
/*     */ 
/* 138 */             (marker.x != x) && (marker.y != y) && (marker.z != z))) {
/* 139 */             int minX = Math.min(marker.x, x) + 1;
/* 140 */             int minZ = Math.min(marker.z, z) + 1;
/* 141 */             int minY = Math.min(marker.y, y) + 1;
/* 142 */             for (int dx = minX; dx < Math.max(marker.x, x); dx++) {
/* 143 */               for (int dz = minZ; dz < Math.max(marker.z, z); dz++) {
/* 144 */                 for (int dy = minY; dy < Math.max(marker.y, y); dy++)
/*     */                 {
/* 146 */                   Block block = BlockUtil.getBlock(world, dx, dy, dz);
/* 147 */                   int meta = world.func_72805_g(dx, dy, dz);
/* 148 */                   NBTTagCompound nbtBlock = new NBTTagCompound();
/* 149 */                   String blockName = Block.field_149771_c.func_148750_c(block);
/* 150 */                   nbtBlock.func_74778_a("n", blockName);
/* 151 */                   if (meta != 0) {
/* 152 */                     nbtBlock.func_74768_a("m", meta);
/*     */                   }
/* 154 */                   nbtList.func_74742_a(nbtBlock);
/*     */                   
/* 156 */                   if (writer != null) {
/* 157 */                     writer.println(String.format("nbtBlock = new NBTTagCompound();", new Object[0]));
/* 158 */                     writer.println(String.format("nbtBlock.setString(\"n\", \"%s\");", new Object[] { blockName }));
/* 159 */                     if (meta != 0) {
/* 160 */                       writer.println(String.format("nbtBlock.setInteger(\"m\", %d);", new Object[] { Integer.valueOf(meta) }));
/*     */                     }
/* 162 */                     writer.println(String.format("nbtList.appendTag(nbtBlock);", new Object[0]));
/*     */                   }
/*     */                   
/* 165 */                   if ((writer2 != null) && (block != Blocks.field_150350_a)) {
/* 166 */                     String blockNameStripped = blockName.substring(blockName.indexOf(':') + 1);
/* 167 */                     writer2.println(String.format("placeBlockAtCurrentPosition(world, Blocks.%s, %s, %d, %d, %d, bounds);", new Object[] { blockNameStripped, blockNameStripped, Integer.valueOf(meta), Integer.valueOf(dx - minX), Integer.valueOf(dy - minY), Integer.valueOf(dz - minZ) }));
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 181 */           if (nbtList.func_74745_c() > 0) {
/* 182 */             NBTTagCompound nbtSchematic = new NBTTagCompound();
/* 183 */             nbtSchematic.func_74782_a("blocks", nbtList);
/* 184 */             nbtSchematic.func_74768_a("xMax", width);
/* 185 */             nbtSchematic.func_74768_a("yMax", height);
/* 186 */             nbtSchematic.func_74768_a("zMax", depth);
/*     */             
/* 188 */             if (writer != null) {
/* 189 */               writer.println(String.format("nbtSchematic.setTag(\"blocks\", nbtList);", new Object[0]));
/* 190 */               writer.println(String.format("nbtSchematic.setInteger(\"xMax\", %d);", new Object[] { Integer.valueOf(width) }));
/* 191 */               writer.println(String.format("nbtSchematic.setInteger(\"yMax\", %d);", new Object[] { Integer.valueOf(height) }));
/* 192 */               writer.println(String.format("nbtSchematic.setInteger(\"zMax\", %d);", new Object[] { Integer.valueOf(depth) }));
/*     */             }
/*     */             
/* 195 */             nbtRoot.func_74782_a("SavedSchematic", nbtSchematic);
/* 196 */             nbtRoot.func_82580_o("Marker");
/*     */             
/*     */ 
/* 199 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, 0.5D + x, y, 0.5D + z, 1.0D, 1.0D, 16);
/*     */           } else {
/* 201 */             ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, y, 0.5D + z, 1.0D, 1.0D, 16);
/*     */           }
/*     */         }
/*     */         catch (IOException ex) {}finally
/*     */         {
/* 206 */           if (writer != null) {
/* 207 */             writer.close();
/*     */           }
/*     */           
/* 210 */           if (writer2 != null) {
/* 211 */             writer2.close();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void placeSchematic(ItemStack stack, World world, int x, int y, int z, EntityPlayer player, Rotation rotation, boolean clearAir) {
/* 219 */     if (!world.field_72995_K) {
/* 220 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 221 */       if (nbtRoot != null) {
/* 222 */         NBTTagCompound nbtSchematic = nbtRoot.func_74775_l("SavedSchematic");
/* 223 */         drawSchematicInWorld(world, x, y, z, rotation, clearAir, nbtSchematic);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void drawSchematicInWorld(World world, int x, int y, int z, Rotation rotation, boolean clearAir, NBTTagCompound nbtSchematic) {
/* 229 */     if (nbtSchematic != null) {
/* 230 */       NBTTagList nbtBlocks = nbtSchematic.func_150295_c("blocks", 10);
/* 231 */       int width = nbtSchematic.func_74762_e("xMax");
/* 232 */       int height = nbtSchematic.func_74762_e("yMax");
/* 233 */       int depth = nbtSchematic.func_74762_e("zMax");
/* 234 */       if ((nbtBlocks != null) && (width > 0) && (height > 0) && (depth > 0)) {
/* 235 */         int blockIndex = 0;
/* 236 */         if (rotation == Rotation.DEGREES_180) {
/* 237 */           for (int dx = width - 1; dx >= 0; dx--) {
/* 238 */             for (int dz = depth - 1; dz >= 0; dz--) {
/* 239 */               blockIndex = drawBlocks(world, dx + x, y, dz + z, nbtBlocks, height, blockIndex, rotation, clearAir);
/*     */             }
/*     */           }
/* 242 */         } else if (rotation == Rotation.DEGREES_90) {
/* 243 */           for (int dx = width - 1; dx >= 0; dx--) {
/* 244 */             for (int dz = 0; dz < depth; dz++) {
/* 245 */               blockIndex = drawBlocks(world, dz + x, y, dx + z, nbtBlocks, height, blockIndex, rotation, clearAir);
/*     */             }
/*     */             
/*     */           }
/* 249 */         } else if (rotation == Rotation.DEGREES_270) {
/* 250 */           for (int dx = 0; dx < width; dx++) {
/* 251 */             for (int dz = depth - 1; dz >= 0; dz--) {
/* 252 */               blockIndex = drawBlocks(world, dz + x, y, dx + z, nbtBlocks, height, blockIndex, rotation, clearAir);
/*     */             }
/*     */           }
/*     */         } else {
/* 256 */           for (int dx = 0; dx < width; dx++) {
/* 257 */             for (int dz = 0; dz < depth; dz++) {
/* 258 */               blockIndex = drawBlocks(world, dx + x, y, dz + z, nbtBlocks, height, blockIndex, rotation, clearAir);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static int drawBlocks(World world, int x, int y, int z, NBTTagList nbtBlocks, int height, int blockIndex, Rotation rotation, boolean clearAir) {
/* 267 */     for (int dy = 0; dy < height; dy++) {
/* 268 */       NBTTagCompound nbtBlock = nbtBlocks.func_150305_b(blockIndex++);
/* 269 */       String blockName = nbtBlock.func_74779_i("n");
/* 270 */       int blockMeta = nbtBlock.func_74762_e("m");
/*     */       
/* 272 */       Block block = Block.func_149684_b(blockName);
/* 273 */       if ((block instanceof BlockStairs)) {
/* 274 */         int direction = blockMeta & 0x3;
/* 275 */         int updown = blockMeta >> 2 & 0x1;
/*     */         
/* 277 */         if (rotation == Rotation.DEGREES_180) {
/* 278 */           direction = new int[] { 1, 0, 3, 2 }[direction];
/* 279 */         } else if (rotation == Rotation.DEGREES_90) {
/* 280 */           direction = new int[] { 3, 2, 0, 1 }[direction];
/* 281 */         } else if (rotation == Rotation.DEGREES_270) {
/* 282 */           direction = new int[] { 2, 3, 1, 0 }[direction];
/*     */         }
/* 284 */         blockMeta = direction | updown << 2;
/* 285 */       } else if ((block instanceof BlockRotatedPillar)) {
/* 286 */         int type = blockMeta & 0x3;
/* 287 */         int direction = blockMeta >> 2 & 0x3;
/* 288 */         int other = blockMeta >> 4;
/* 289 */         if ((rotation == Rotation.DEGREES_90) || (rotation == Rotation.DEGREES_270)) {
/* 290 */           direction = new int[] { 0, 2, 1, 3 }[direction];
/* 291 */           blockMeta = type | direction << 2 | other << 4;
/*     */         }
/* 293 */       } else if (((block instanceof BlockDoor)) && 
/* 294 */         ((blockMeta >> 4 & 0x1) == 0)) {
/* 295 */         int direction = blockMeta & 0x3;
/* 296 */         int other = blockMeta >> 2;
/* 297 */         if (rotation == Rotation.DEGREES_180) {
/* 298 */           direction = new int[] { 2, 3, 0, 1 }[direction];
/* 299 */         } else if (rotation == Rotation.DEGREES_90) {
/* 300 */           direction = new int[] { 3, 0, 1, 2 }[direction];
/* 301 */         } else if (rotation == Rotation.DEGREES_270) {
/* 302 */           direction = new int[] { 1, 2, 3, 0 }[direction];
/*     */         }
/* 304 */         blockMeta = direction | other << 2;
/*     */       }
/*     */       
/*     */ 
/* 308 */       if ((block != null) && ((clearAir) || (block != Blocks.field_150350_a))) {
/* 309 */         world.func_147465_d(x, dy + y, z, block, blockMeta, 2);
/*     */       }
/*     */     }
/* 312 */     return blockIndex;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemDuplicationStaff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */