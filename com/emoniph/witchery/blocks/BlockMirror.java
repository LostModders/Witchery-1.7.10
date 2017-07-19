/*      */ package com.emoniph.witchery.blocks;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.common.ExtendedPlayer;
/*      */ import com.emoniph.witchery.common.IPowerSource;
/*      */ import com.emoniph.witchery.common.PowerSources;
/*      */ import com.emoniph.witchery.common.Shapeshift;
/*      */ import com.emoniph.witchery.entity.EntityFollower;
/*      */ import com.emoniph.witchery.entity.EntityMirrorFace;
/*      */ import com.emoniph.witchery.entity.EntityReflection;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemSunGrenade;
/*      */ import com.emoniph.witchery.item.ItemTaglockKit;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.util.BlockUtil;
/*      */ import com.emoniph.witchery.util.ChatUtil;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.TimeUtil;
/*      */ import com.emoniph.witchery.util.TransformCreature;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.nbt.NBTTagString;
/*      */ import net.minecraft.nbt.NBTUtil;
/*      */ import net.minecraft.network.NetworkManager;
/*      */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*      */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldServer;
/*      */ 
/*      */ public class BlockMirror extends BlockBaseContainer
/*      */ {
/*      */   private final boolean unbreakable;
/*      */   
/*      */   public BlockMirror(boolean unbreakable)
/*      */   {
/*   65 */     super(Material.field_151592_s, TileEntityMirror.class);
/*   66 */     this.unbreakable = unbreakable;
/*   67 */     this.registerWithCreateTab = false;
/*   68 */     func_149715_a(0.7F);
/*   69 */     func_149649_H();
/*   70 */     func_149672_a(field_149778_k);
/*   71 */     if (unbreakable) {
/*   72 */       func_149722_s();
/*   73 */       func_149752_b(9999.0F);
/*      */     } else {
/*   75 */       func_149711_c(1.0F);
/*   76 */       func_149752_b(9999.0F);
/*      */     }
/*      */   }
/*      */   
/*      */   public static int getDirection(int meta) {
/*   81 */     return meta & 0x3;
/*      */   }
/*      */   
/*      */   public static boolean isBlockTopOfMirror(int meta) {
/*   85 */     return (meta & 0x4) != 0;
/*      */   }
/*      */   
/*   88 */   private static String sendMeHome = null;
/*   89 */   private static String iGiveUp = null;
/*      */   
/*      */   public boolean trySayMirrorMirrorSendMeHome(EntityPlayer player, String message) {
/*   92 */     if ((player == null) || (player.field_70170_p.field_72995_K) || (player.field_71093_bK != Config.instance().dimensionMirrorID)) {
/*   93 */       return false;
/*      */     }
/*   95 */     if (sendMeHome == null) {
/*   96 */       sendMeHome = Witchery.resource("witchery.rite.mirrormirrorsendmehome").toLowerCase().replace("'", "").replace(",", "").trim();
/*      */     }
/*      */     
/*   99 */     if (iGiveUp == null) {
/*  100 */       iGiveUp = Witchery.resource("witchery.rite.mirrormirrorigiveup").toLowerCase().replace("'", "").replace(",", "").trim();
/*      */     }
/*      */     
/*  103 */     ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*      */     
/*  105 */     if (message.toLowerCase().replace("'", "").replace(",", "").trim().startsWith(sendMeHome)) {
/*  106 */       if (playerEx.canEscapeMirrorWorld(1)) {
/*  107 */         Coord c = playerEx.getMirrorWorldEntryPoint();
/*  108 */         if (c != null) {
/*  109 */           playerEx.escapedMirrorWorld(1);
/*  110 */           player.func_70080_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, 270.0F, player.field_70125_A);
/*  111 */           ItemGeneral.teleportToLocation(player.field_70170_p, 0.5D + c.x, 0.01D + c.y, 0.5D + c.z, player.field_71093_bK, player, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
/*      */           
/*  113 */           return true;
/*      */         }
/*      */       } else {
/*  116 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.mirrormirror.escapecooldown", new Object[] { Long.valueOf(playerEx.getCooldownSecs(1)).toString() });
/*      */         
/*      */ 
/*  119 */         return true;
/*      */       }
/*  121 */     } else if (message.toLowerCase().replace("'", "").replace(",", "").trim().startsWith(iGiveUp)) {
/*  122 */       if (playerEx.canEscapeMirrorWorld(2)) {
/*  123 */         ChunkCoordinates coords = player.getBedLocation(player.field_71093_bK);
/*  124 */         int dimension = player.field_71093_bK;
/*  125 */         World world = player.field_70170_p;
/*  126 */         if (coords == null) {
/*  127 */           coords = player.getBedLocation(0);
/*  128 */           dimension = 0;
/*  129 */           world = MinecraftServer.func_71276_C().func_71218_a(0);
/*  130 */           if (coords == null) {
/*  131 */             coords = world.func_72861_E();
/*      */             
/*  133 */             while ((world.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c).func_149721_r()) && (coords.field_71572_b < 255)) {
/*  134 */               coords.field_71572_b += 1;
/*      */             }
/*      */           }
/*      */         }
/*  138 */         if (coords != null) {
/*  139 */           coords = net.minecraft.init.Blocks.field_150324_C.getBedSpawnPosition(world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, null);
/*  140 */           if (coords != null) {
/*  141 */             playerEx.escapedMirrorWorld(2);
/*  142 */             ItemGeneral.teleportToLocation(player.field_70170_p, coords.field_71574_a, coords.field_71572_b + 1, coords.field_71573_c, dimension, player, true);
/*      */             
/*  144 */             return true;
/*      */           }
/*      */         }
/*      */       } else {
/*  148 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.mirrormirror.escapecooldown", new Object[] { Long.valueOf(playerEx.getCooldownSecs(2)).toString() });
/*      */         
/*      */ 
/*  151 */         return true;
/*      */       }
/*      */     }
/*  154 */     return false;
/*      */   }
/*      */   
/*      */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*      */   {
/*  159 */     if (!world.field_72995_K)
/*      */     {
/*  161 */       if ((entity.field_70173_aa % 5 == 1) && (isTransportableEntity(entity))) {
/*  162 */         int i1 = world.func_72805_g(x, y, z);
/*      */         
/*  164 */         int hitZoneyShift = 0;
/*  165 */         if (!isBlockTopOfMirror(i1)) {
/*  166 */           y++;
/*  167 */           if (entity.field_70131_O <= 1.0F) {
/*  168 */             hitZoneyShift = -1;
/*      */           }
/*  170 */           if (world.func_147439_a(x, y, z) != this) {
/*  171 */             return;
/*      */           }
/*      */         }
/*      */         
/*  175 */         AxisAlignedBB box = getServerSelectedBoundingBoxFromPool(world, x, y + hitZoneyShift, z);
/*      */         
/*  177 */         double f = entity.field_70130_N * 0.5D;
/*  178 */         double f1 = entity.field_70131_O;
/*  179 */         AxisAlignedBB entityBox = AxisAlignedBB.func_72330_a(entity.field_70165_t - f, entity.field_70163_u - entity.field_70129_M + entity.field_70139_V, entity.field_70161_v - f, entity.field_70165_t + f, entity.field_70163_u - entity.field_70129_M + entity.field_70139_V + f1, entity.field_70161_v + f);
/*      */         
/*      */ 
/*  182 */         if (entityBox.func_72326_a(box))
/*      */         {
/*  184 */           TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
/*  185 */           if (tile != null)
/*      */           {
/*  187 */             int side = getDirection(world.func_72805_g(x, y, z));
/*  188 */             int facing = MathHelper.func_76128_c(entity.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*      */             
/*  190 */             int dx = 0;
/*  191 */             int dz = 0;
/*  192 */             float shift = 0.7F;
/*  193 */             float xShift = 0.0F;
/*  194 */             float zShift = 0.0F;
/*      */             
/*  196 */             int scale = 1;
/*  197 */             int requiredSide = 0;
/*  198 */             boolean isLiving = entity instanceof EntityLivingBase;
/*      */             
/*  200 */             if (side == 0) {
/*  201 */               dz = scale;
/*  202 */               zShift = -shift;
/*  203 */               requiredSide = 1;
/*  204 */               if ((!isLiving) || (facing == 0)) {}
/*      */ 
/*      */             }
/*  207 */             else if (side == 1) {
/*  208 */               dz = -scale;
/*  209 */               zShift = shift;
/*  210 */               requiredSide = 0;
/*  211 */               if ((!isLiving) || (facing == 2)) {}
/*      */ 
/*      */             }
/*  214 */             else if (side == 2) {
/*  215 */               dx = scale;
/*  216 */               xShift = -shift;
/*  217 */               requiredSide = 3;
/*  218 */               if ((!isLiving) || (facing == 3)) {}
/*      */ 
/*      */             }
/*  221 */             else if (side == 3) {
/*  222 */               dx = -scale;
/*  223 */               requiredSide = 2;
/*  224 */               xShift = shift;
/*  225 */               if ((isLiving) && (facing != 1)) {
/*  226 */                 return;
/*      */               }
/*      */             }
/*      */             
/*  230 */             boolean inMirrorWorld = entity.field_71093_bK == Config.instance().dimensionMirrorID;
/*      */             
/*  232 */             if (!this.unbreakable)
/*      */             {
/*  234 */               if ((inMirrorWorld) || (tile.demonKilled))
/*      */               {
/*  236 */                 for (int i = 1; i < 32; i++) {
/*  237 */                   int nx = x + dx * i;
/*  238 */                   int ny = y;
/*  239 */                   int nz = z + dz * i;
/*  240 */                   Block block = world.func_147439_a(nx, ny, nz);
/*  241 */                   if ((block == this) && 
/*  242 */                     (getDirection(world.func_72805_g(nx, ny, nz)) == requiredSide)) {
/*  243 */                     ItemGeneral.teleportToLocation(world, 0.5D + nx - xShift, ny - 1 + 0.01D, 0.5D + nz - zShift, world.field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
/*      */                     
/*      */ 
/*      */ 
/*  247 */                     return;
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/*  253 */               if (inMirrorWorld)
/*      */               {
/*      */ 
/*      */ 
/*  257 */                 for (int i = 1; i < 10; i++) {
/*  258 */                   int nx = x + dx * i;
/*  259 */                   int ny = y;
/*  260 */                   int nz = z + dz * i;
/*  261 */                   if ((world.func_147437_c(nx, ny, nz)) && (world.func_147437_c(nx, ny - 1, nz))) {
/*  262 */                     boolean isPlayerEntryCell = false;
/*  263 */                     if ((entity instanceof EntityPlayer)) {
/*  264 */                       EntityPlayer player = (EntityPlayer)entity;
/*  265 */                       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  266 */                       isPlayerEntryCell = playerEx.isMirrorWorldEntryPoint(nx, ny, nz);
/*      */                     }
/*  268 */                     int cx = (nx >> 4 << 4) + 4;
/*  269 */                     int cy = (ny >> 4 << 4) + 8;
/*  270 */                     int cz = (nz >> 4 << 4) + 8;
/*  271 */                     boolean isEntryCell = world.func_147439_a(cx, cy, cz) == Witchery.Blocks.MIRROR_UNBREAKABLE;
/*  272 */                     if ((!isEntryCell) || (isPlayerEntryCell)) {
/*  273 */                       IPowerSource power = PowerSources.findClosestPowerSource(world, x, y, z);
/*      */                       
/*  275 */                       if ((power != null) && (power.consumePower(3000.0F))) {
/*  276 */                         ItemGeneral.teleportToLocation(world, 0.5D + nx - xShift, ny + 0.01D, 0.5D + nz - zShift, world.field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
/*      */                       }
/*      */                     }
/*      */                     
/*      */ 
/*      */ 
/*  282 */                     return;
/*      */                   }
/*      */                 }
/*  285 */               } else if (tile.demonKilled)
/*      */               {
/*  287 */                 for (int dy = 2; dy < 16; dy++) {
/*  288 */                   int nx = x;
/*  289 */                   int ny = y + dy;
/*  290 */                   int nz = z;
/*  291 */                   Block block = world.func_147439_a(nx, ny, nz);
/*  292 */                   if (block == this) {
/*  293 */                     int meta = world.func_72805_g(nx, ny, nz);
/*  294 */                     if (getDirection(meta) == side) {
/*  295 */                       if (isBlockTopOfMirror(meta)) {
/*  296 */                         ny--;
/*      */                       }
/*      */                       
/*  299 */                       ItemGeneral.teleportToLocation(world, 0.5D + nx + xShift, ny + 0.01D, 0.5D + nz + zShift, world.field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
/*      */                       
/*      */ 
/*      */ 
/*  303 */                       if ((entity instanceof EntityPlayer)) {
/*  304 */                         EntityPlayer player = (EntityPlayer)entity;
/*  305 */                         double yaw = player.field_70177_z + 180.0F;
/*  306 */                         float rev = (float)yaw % 360.0F;
/*  307 */                         S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(player.field_70165_t, player.field_70163_u, player.field_70161_v, rev, player.field_70125_A, false);
/*      */                         
/*      */ 
/*  310 */                         Witchery.packetPipeline.sendTo(packet, player);
/*      */                       }
/*      */                       
/*      */ 
/*  314 */                       return;
/*      */                     }
/*      */                   }
/*      */                 }
/*      */                 
/*      */ 
/*  320 */                 for (int dy = 2; dy < 16; dy++) {
/*  321 */                   int nx = x;
/*  322 */                   int ny = y - dy;
/*  323 */                   int nz = z;
/*  324 */                   Block block = world.func_147439_a(nx, ny, nz);
/*  325 */                   if (block == this) {
/*  326 */                     int meta = world.func_72805_g(nx, ny, nz);
/*  327 */                     if (getDirection(meta) == side) {
/*  328 */                       if (isBlockTopOfMirror(meta)) {
/*  329 */                         ny--;
/*      */                       }
/*  331 */                       ItemGeneral.teleportToLocation(world, 0.5D + nx + xShift, ny + 0.01D, 0.5D + nz + zShift, world.field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
/*      */                       
/*      */ 
/*      */ 
/*  335 */                       if ((entity instanceof EntityPlayer)) {
/*  336 */                         EntityPlayer player = (EntityPlayer)entity;
/*  337 */                         double yaw = player.field_70177_z + 180.0F;
/*  338 */                         float rev = (float)yaw % 360.0F;
/*  339 */                         S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(player.field_70165_t, player.field_70163_u, player.field_70161_v, rev, player.field_70125_A, false);
/*      */                         
/*      */ 
/*  342 */                         Witchery.packetPipeline.sendTo(packet, player);
/*      */                       }
/*      */                       
/*      */ 
/*  346 */                       return;
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*  355 */             if ((entity instanceof EntityPlayer)) {
/*  356 */               EntityPlayer player = (EntityPlayer)entity;
/*  357 */               ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  358 */               if ((!inMirrorWorld) || (playerEx.isMirrorWorldEntryPoint(x, y, z)))
/*      */               {
/*  360 */                 Coord dimCoords = tile.getDimCoords();
/*  361 */                 if (dimCoords != null)
/*      */                 {
/*  363 */                   float dimX = dimCoords.x + 0.5F;
/*  364 */                   float dimY = dimCoords.y + 0.01F;
/*  365 */                   float dimZ = dimCoords.z + 0.5F;
/*  366 */                   int targetDimension = !inMirrorWorld ? Config.instance().dimensionMirrorID : tile.dim;
/*      */                   
/*  368 */                   World otherWorld = MinecraftServer.func_71276_C().func_71218_a(targetDimension);
/*      */                   
/*  370 */                   float face = 0.0F;
/*  371 */                   if (otherWorld != null) {
/*  372 */                     Block block = otherWorld.func_147439_a(dimCoords.x, dimCoords.y, dimCoords.z);
/*  373 */                     if ((block == Witchery.Blocks.MIRROR) || (block == Witchery.Blocks.MIRROR_UNBREAKABLE))
/*      */                     {
/*  375 */                       int mside = getDirection(otherWorld.func_72805_g(dimCoords.x, dimCoords.y, dimCoords.z));
/*      */                       
/*      */ 
/*  378 */                       float distance = 1.0F;
/*  379 */                       if (mside == 0) {
/*  380 */                         face = 180.0F;
/*  381 */                         dimZ -= distance;
/*  382 */                       } else if (mside == 1) {
/*  383 */                         face = 0.0F;
/*  384 */                         dimZ += distance;
/*  385 */                       } else if (mside == 2) {
/*  386 */                         face = 90.0F;
/*  387 */                         dimX -= distance;
/*  388 */                       } else if (mside == 3) {
/*  389 */                         face = 270.0F;
/*  390 */                         dimX += distance;
/*      */                       }
/*      */                       
/*  393 */                       player.field_70177_z = face;
/*      */                       
/*  395 */                       TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, dimCoords.x, dimCoords.y, dimCoords.z, TileEntityMirror.class);
/*      */                       
/*  397 */                       if (otherTile != null) {
/*  398 */                         if (otherTile.onCooldown()) {
/*  399 */                           return;
/*      */                         }
/*  401 */                         otherTile.addCooldown(60);
/*      */                       }
/*      */                     }
/*      */                   }
/*      */                   
/*  406 */                   ParticleEffect.SPLASH.send(SoundEffect.RANDOM_SPLASH, entity, 0.5D, 2.0D, 16);
/*  407 */                   if (entity.field_71093_bK != Config.instance().dimensionMirrorID)
/*      */                   {
/*  409 */                     if (!tile.demonKilled) {
/*  410 */                       double R = 7.0D;
/*  411 */                       double RY = 6.0D;
/*  412 */                       float cellMidX = dimCoords.x + 4;
/*  413 */                       float cellMidY = dimCoords.y;
/*  414 */                       float cellMidZ = dimCoords.z;
/*  415 */                       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(cellMidX - R, cellMidY - RY, cellMidZ - R, cellMidX + R, cellMidY + RY, cellMidZ + R);
/*      */                       
/*      */ 
/*  418 */                       List<EntityReflection> EntityReflection = otherWorld.func_72872_a(EntityReflection.class, bounds);
/*      */                       
/*  420 */                       if (EntityReflection.size() == 0) {
/*  421 */                         EntityReflection reflection = new EntityReflection(otherWorld);
/*  422 */                         reflection.func_70080_a(0.5D + cellMidX, 1.1D + cellMidY, 0.5D + cellMidZ, 0.0F, 0.0F);
/*      */                         
/*  424 */                         reflection.func_110163_bv();
/*  425 */                         reflection.field_70170_p.func_72838_d(reflection);
/*      */                       }
/*      */                     }
/*      */                     
/*  429 */                     playerEx.setMirrorWorldEntryPoint(dimCoords.x, dimCoords.y, dimCoords.z);
/*  430 */                     player.func_70080_a(dimX, dimY - 1.0F, dimZ, face, player.field_70125_A);
/*      */                     
/*  432 */                     ItemGeneral.travelToDimension(player, Config.instance().dimensionMirrorID);
/*      */                     
/*  434 */                     player.func_70634_a(dimX, dimY - 1.0F, dimZ);
/*      */                   }
/*  436 */                   else if (tile.isConnected) {
/*  437 */                     player.func_70080_a(dimX, dimY - 1.0F, dimZ, face, player.field_70125_A);
/*      */                     
/*  439 */                     ItemGeneral.travelToDimension(player, tile.dim);
/*      */                     
/*  441 */                     player.func_70634_a(dimX, dimY - 1.0F, dimZ);
/*      */                   } else {
/*  443 */                     double targetX = dimX;
/*  444 */                     double targetY = dimY - 1.0F;
/*  445 */                     double targetZ = dimZ;
/*  446 */                     int targetDim = tile.dim;
/*  447 */                     boolean CHECK_PLAYER_INV = true;
/*      */                     
/*  449 */                     MinecraftServer server = MinecraftServer.func_71276_C();
/*  450 */                     for (WorldServer worldServer : server.field_71305_c) {
/*  451 */                       for (Object obj : worldServer.field_73010_i) {
/*  452 */                         EntityPlayer otherPlayer = (EntityPlayer)obj;
/*  453 */                         for (ItemStack stack : otherPlayer.field_71071_by.field_70462_a) {
/*  454 */                           if ((stack != null) && (stack.func_77973_b() == Witchery.Items.MIRROR))
/*      */                           {
/*  456 */                             boolean isMirror = tile.isTargettedBy(stack);
/*  457 */                             if (isMirror) {
/*  458 */                               if (otherPlayer.field_71093_bK == Config.instance().dimensionMirrorID) break;
/*  459 */                               targetX = otherPlayer.field_70165_t;
/*  460 */                               targetY = otherPlayer.field_70163_u;
/*  461 */                               targetZ = otherPlayer.field_70161_v;
/*  462 */                               targetDim = otherPlayer.field_71093_bK; break;
/*      */                             }
/*      */                           }
/*      */                         }
/*      */                       }
/*      */                     }
/*      */                     
/*      */ 
/*      */ 
/*  471 */                     player.func_70080_a(targetX, targetY, targetZ, face, player.field_70125_A);
/*      */                     
/*  473 */                     ItemGeneral.travelToDimension(player, targetDim);
/*  474 */                     player.func_70634_a(targetX, targetY, targetZ);
/*      */                   }
/*      */                   
/*  477 */                   ParticleEffect.SPLASH.send(SoundEffect.RANDOM_SPLASH, entity, 0.5D, 2.0D, 16);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void demonSlain(World world, double posX, double posY, double posZ) {
/*  488 */     if (!world.field_72995_K) {
/*  489 */       double R = 7.0D;
/*  490 */       double RY = 6.0D;
/*  491 */       int x = (MathHelper.func_76128_c(posX) >> 4 << 4) + 4;
/*  492 */       int xmid = x + 4;
/*  493 */       int y = (MathHelper.func_76128_c(posY) >> 4 << 4) + 8;
/*  494 */       int z = (MathHelper.func_76128_c(posZ) >> 4 << 4) + 8;
/*  495 */       if (world.func_147439_a(x, y, z) == Witchery.Blocks.MIRROR_UNBREAKABLE) {
/*  496 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(xmid - R, y - RY, z - R, xmid + R, y + RY, z + R);
/*      */         
/*  498 */         List<EntityReflection> reflections = world.func_72872_a(EntityReflection.class, bounds);
/*      */         
/*  500 */         int livingDemons = 0;
/*  501 */         for (EntityReflection entity : reflections) {
/*  502 */           if ((entity != null) && (entity.func_70089_S())) {
/*  503 */             livingDemons++;
/*      */           }
/*      */         }
/*  506 */         if (livingDemons == 0) {
/*  507 */           TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
/*  508 */           if (tile != null) {
/*  509 */             Coord dimCoords = tile.getDimCoords();
/*  510 */             int dim = tile.dim;
/*  511 */             World otherWorld = MinecraftServer.func_71276_C().func_71218_a(dim);
/*  512 */             if (otherWorld != null) {
/*  513 */               TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, dimCoords.x, dimCoords.y, dimCoords.z, TileEntityMirror.class);
/*      */               
/*  515 */               if (otherTile != null) {
/*  516 */                 otherTile.demonKilled = true;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean isTransportableEntity(Entity entity) {
/*  526 */     return (!(entity instanceof EntityMirrorFace)) && (((entity instanceof EntityLivingBase)) || ((entity instanceof EntityItem)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*      */   {
/*  533 */     if (world.field_72995_K) {
/*  534 */       return true;
/*      */     }
/*  536 */     if (!this.unbreakable) {
/*  537 */       int i1 = world.func_72805_g(x, y, z);
/*      */       
/*  539 */       int origX = x;int origZ = z;
/*  540 */       if (!isBlockTopOfMirror(i1)) {
/*  541 */         y++;
/*      */         
/*  543 */         if (world.func_147439_a(x, y, z) != this) {
/*  544 */           return true;
/*      */         }
/*      */       }
/*      */       
/*  548 */       TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
/*      */       
/*  550 */       if (tile != null) {
/*  551 */         tile.depolyDemon(player);
/*      */       }
/*      */       else {
/*  554 */         return true;
/*      */       }
/*      */     }
/*      */     
/*  558 */     return true;
/*      */   }
/*      */   
/*      */   public int func_149645_b()
/*      */   {
/*  563 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean func_149686_d()
/*      */   {
/*  568 */     return false;
/*      */   }
/*      */   
/*      */   public boolean func_149662_c()
/*      */   {
/*  573 */     return false;
/*      */   }
/*      */   
/*      */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*      */   {
/*  578 */     int side = getDirection(world.func_72805_g(x, y, z));
/*      */     
/*  580 */     float w = 0.15F;
/*      */     
/*  582 */     if (side == 0) {
/*  583 */       func_149676_a(0.0F, 0.0F, 0.85F, 1.0F, 1.0F, 1.0F);
/*  584 */     } else if (side == 1) {
/*  585 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.15F);
/*  586 */     } else if (side == 2) {
/*  587 */       func_149676_a(0.85F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  588 */     } else if (side == 3) {
/*  589 */       func_149676_a(0.0F, 0.0F, 0.0F, 0.15F, 1.0F, 1.0F);
/*      */     }
/*      */     
/*  592 */     AxisAlignedBB bounds = super.func_149668_a(world, x, y, z);
/*      */     
/*  594 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/*  596 */     return bounds;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z)
/*      */   {
/*  602 */     int side = getDirection(world.func_72805_g(x, y, z));
/*      */     
/*  604 */     float w = 0.32F;
/*      */     
/*  606 */     if (side == 0) {
/*  607 */       func_149676_a(0.0F, 0.0F, 0.68F, 1.0F, 1.0F, 1.0F);
/*  608 */     } else if (side == 1) {
/*  609 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.32F);
/*  610 */     } else if (side == 2) {
/*  611 */       func_149676_a(0.68F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  612 */     } else if (side == 3) {
/*  613 */       func_149676_a(0.0F, 0.0F, 0.0F, 0.32F, 1.0F, 1.0F);
/*      */     }
/*      */     
/*  616 */     AxisAlignedBB bounds = super.func_149633_g(world, x, y, z);
/*      */     
/*  618 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  619 */     return bounds;
/*      */   }
/*      */   
/*      */   public AxisAlignedBB getServerSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
/*  623 */     int side = getDirection(world.func_72805_g(x, y, z));
/*      */     
/*  625 */     float w = 0.32F;
/*      */     
/*  627 */     float minX = 0.0F;
/*  628 */     float minY = 0.0F;
/*  629 */     float minZ = 0.0F;
/*  630 */     float maxX = 1.0F;
/*  631 */     float maxY = 1.0F;
/*  632 */     float maxZ = 1.0F;
/*      */     
/*  634 */     if (side == 0) {
/*  635 */       minZ = 0.68F;
/*      */     }
/*  637 */     else if (side == 1) {
/*  638 */       maxZ = 0.32F;
/*  639 */     } else if (side == 2) {
/*  640 */       minX = 0.68F;
/*  641 */     } else if (side == 3) {
/*  642 */       maxZ = 0.32F;
/*      */     }
/*      */     
/*  645 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
/*      */     
/*      */ 
/*  648 */     return bounds;
/*      */   }
/*      */   
/*      */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*      */   {
/*  653 */     int l = world.func_72805_g(x, y, z);
/*  654 */     int i1 = getDirection(l);
/*      */     
/*  656 */     if (isBlockTopOfMirror(l)) {
/*  657 */       if (world.func_147439_a(x, y - 1, z) != this) {
/*  658 */         if (!world.field_72995_K) {
/*  659 */           func_149697_b(world, x, y, z, l, 0);
/*      */         }
/*  661 */         world.func_147468_f(x, y, z);
/*      */       }
/*  663 */     } else if (world.func_147439_a(x, y + 1, z) != this) {
/*  664 */       world.func_147468_f(x, y, z);
/*      */     }
/*      */   }
/*      */   
/*      */   public Item func_149650_a(int meta, Random rand, int p_149650_3_)
/*      */   {
/*  670 */     return isBlockTopOfMirror(meta) ? Witchery.Items.MIRROR : Item.func_150899_d(0);
/*      */   }
/*      */   
/*      */ 
/*      */   public void func_149690_a(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_)
/*      */   {
/*  676 */     if (isBlockTopOfMirror(p_149690_5_)) {
/*  677 */       super.func_149690_a(world, x, y, z, p_149690_5_, p_149690_6_, 0);
/*      */     }
/*      */   }
/*      */   
/*      */   public int func_149656_h()
/*      */   {
/*  683 */     return super.func_149656_h();
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public Item func_149694_d(World world, int x, int y, int z)
/*      */   {
/*  689 */     return Witchery.Items.MIRROR;
/*      */   }
/*      */   
/*      */ 
/*      */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {}
/*      */   
/*      */ 
/*      */   public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player)
/*      */   {
/*  698 */     if ((player.field_71075_bZ.field_75098_d) && 
/*  699 */       (isBlockTopOfMirror(meta))) {
/*  700 */       if (world.func_147439_a(x, y - 1, z) == this) {
/*  701 */         world.func_147468_f(x, y - 1, z);
/*      */       }
/*      */       
/*  704 */       meta |= 0x8;
/*  705 */       world.func_72921_c(x, y, z, meta, 4);
/*      */     }
/*      */     
/*      */ 
/*  709 */     func_149697_b(world, x, y, z, meta, 0);
/*      */     
/*  711 */     super.func_149681_a(world, x, y, z, meta, player);
/*      */   }
/*      */   
/*      */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
/*      */   {
/*  716 */     ArrayList<ItemStack> drops = new ArrayList();
/*  717 */     boolean brokenInCreativeMode = (meta & 0x8) != 0;
/*  718 */     if (!brokenInCreativeMode) {
/*  719 */       TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
/*  720 */       if (tile != null) {
/*  721 */         ItemStack stack = new ItemStack(Witchery.Items.MIRROR);
/*      */         
/*  723 */         NBTTagCompound nbtRoot = new NBTTagCompound();
/*  724 */         tile.writeItemDataToNBT(nbtRoot);
/*  725 */         stack.func_77982_d(nbtRoot);
/*      */         
/*  727 */         if ((world.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID) && (tile.isDimLinked())) {
/*  728 */           Coord dimCoords = tile.getDimCoords();
/*  729 */           World otherWorld = MinecraftServer.func_71276_C().func_71218_a(Config.instance().dimensionMirrorID);
/*      */           
/*  731 */           if ((otherWorld != null) && (otherWorld.func_147439_a(dimCoords.x, dimCoords.y, dimCoords.z) == Witchery.Blocks.MIRROR))
/*      */           {
/*  733 */             TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, dimCoords.x, dimCoords.y, dimCoords.z, TileEntityMirror.class);
/*      */             
/*  735 */             if (otherTile != null) {
/*  736 */               otherTile.isConnected = false;
/*  737 */               otherTile.markBlockForUpdate(false);
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  743 */         drops.add(stack);
/*      */       }
/*      */     }
/*  746 */     return drops;
/*      */   }
/*      */   
/*      */   public void loadFromItem(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) {
/*  750 */     TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
/*  751 */     if (tile != null) {
/*  752 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/*  753 */       if (nbtRoot != null) {
/*  754 */         tile.readItemDataFromNBT(nbtRoot);
/*  755 */         if ((world.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID) && (tile.isDimLinked())) {
/*  756 */           Coord dimCoords = tile.getDimCoords();
/*  757 */           World otherWorld = MinecraftServer.func_71276_C().func_71218_a(Config.instance().dimensionMirrorID);
/*      */           
/*  759 */           if ((otherWorld != null) && (otherWorld.func_147439_a(dimCoords.x, dimCoords.y, dimCoords.z) == Witchery.Blocks.MIRROR_UNBREAKABLE))
/*      */           {
/*  761 */             TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, dimCoords.x, dimCoords.y, dimCoords.z, TileEntityMirror.class);
/*      */             
/*  763 */             if (otherTile != null) {
/*  764 */               otherTile.isConnected = true;
/*  765 */               otherTile.dimCoords = new Coord(x, y, z);
/*  766 */               otherTile.markBlockForUpdate(false);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static class TileEntityMirror
/*      */     extends TileEntityBase
/*      */   {
/*      */     public int men;
/*      */     
/*      */     private Coord dimCoords;
/*      */     
/*      */     private int dim;
/*      */     
/*      */     private boolean isConnected;
/*      */     
/*      */     private boolean demonKilled;
/*      */     
/*      */     private GameProfile favorite;
/*      */     private UUID fairest;
/*  790 */     private Set<String> playersSeen = new java.util.HashSet();
/*      */     long cooldown;
/*      */     
/*      */     public void func_145845_h() {
/*  794 */       super.func_145845_h();
/*      */       
/*  796 */       if (this.ticks % (this.field_145850_b.field_72995_K ? 10 : 40) == 1L) {
/*  797 */         int side = BlockMirror.getDirection(this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e));
/*      */         
/*  799 */         int xMin = -1;int xMax = 1;int zMin = -1;int zMax = 1;
/*      */         
/*  801 */         int scale = 4;
/*  802 */         if (side == 0) {
/*  803 */           zMin = -4;
/*  804 */           zMax = 0;
/*  805 */         } else if (side == 1) {
/*  806 */           zMin = 0;
/*  807 */           zMax = 4;
/*  808 */         } else if (side == 2) {
/*  809 */           xMin = -4;
/*  810 */           xMax = 0;
/*  811 */         } else if (side == 3)
/*      */         {
/*  813 */           xMin = 0;
/*  814 */           xMax = 4;
/*      */         }
/*      */         
/*  817 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_145851_c + xMin, this.field_145848_d, this.field_145849_e + zMin, this.field_145851_c + xMax + 1, this.field_145848_d + 1, this.field_145849_e + zMax + 1);
/*      */         
/*  819 */         List<EntityLivingBase> entities = this.field_145850_b.func_72872_a(EntityLivingBase.class, bounds);
/*      */         
/*  821 */         this.men = entities.size();
/*  822 */         if (!this.field_145850_b.field_72995_K) {
/*  823 */           for (EntityLivingBase entity : entities) {
/*  824 */             if ((entity instanceof EntityPlayer)) {
/*  825 */               this.playersSeen.add(entity.func_70005_c_());
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public void addCooldown(int i)
/*      */     {
/*  836 */       this.cooldown = (this.ticks + i);
/*      */     }
/*      */     
/*      */     public boolean onCooldown() {
/*  840 */       return this.ticks < this.cooldown;
/*      */     }
/*      */     
/*      */     public boolean isTargettedBy(ItemStack stack) {
/*  844 */       if ((stack != null) && (stack.func_77973_b() == Witchery.Items.MIRROR) && (stack.func_77978_p() != null)) {
/*  845 */         NBTTagCompound nbtRoot = stack.func_77978_p();
/*  846 */         if ((nbtRoot.func_74764_b("DimCoords")) && (nbtRoot.func_74764_b("Dimension")))
/*      */         {
/*  848 */           if (this.field_145850_b.field_73011_w.field_76574_g == nbtRoot.func_74762_e("Dimension")) {
/*  849 */             Coord coords = Coord.fromTagNBT(nbtRoot.func_74775_l("DimCoords"));
/*  850 */             if (coords != null) {
/*  851 */               return coords.isMatch(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  856 */       return false;
/*      */     }
/*      */     
/*  859 */     long lastFairestSpawn = 0L;
/*      */     
/*      */     private void depolyDemon(EntityPlayer player) {
/*  862 */       if ((!this.demonKilled) && (this.field_145850_b.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID)) {
/*  863 */         if ((player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == Witchery.Items.TAGLOCK_KIT)) {
/*  864 */           ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  865 */           TransformCreature currentTransform = playerEx.getCreatureType();
/*  866 */           if ((currentTransform == TransformCreature.NONE) || (currentTransform == TransformCreature.PLAYER)) {
/*  867 */             String username = Witchery.Items.TAGLOCK_KIT.getBoundUsername(player.func_70694_bm(), Integer.valueOf(1));
/*      */             
/*  869 */             if ((username == null) || (username.isEmpty()) || (username.equals(player.func_70005_c_())))
/*      */             {
/*  871 */               if (currentTransform == TransformCreature.PLAYER) {
/*  872 */                 ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
/*  873 */                 Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
/*      */               } else {
/*  875 */                 ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, player, 0.5D, 2.0D, 16);
/*      */               }
/*      */             } else {
/*  878 */               IPowerSource power = PowerSources.findClosestPowerSource(this);
/*  879 */               if ((power != null) && (power.consumePower(4000.0F))) {
/*  880 */                 ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
/*  881 */                 playerEx.setOtherPlayerSkin(username);
/*  882 */                 Shapeshift.INSTANCE.shiftTo(player, TransformCreature.PLAYER);
/*      */               } else {
/*  884 */                 ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, player, 0.5D, 2.0D, 16);
/*      */               }
/*      */             }
/*      */           }
/*  888 */         } else if ((player.func_70694_bm() != null) && (Witchery.Items.GENERIC.itemQuartzSphere.isMatch(player.func_70694_bm())))
/*      */         {
/*      */ 
/*  891 */           IPowerSource power = PowerSources.findClosestPowerSource(this);
/*  892 */           if ((power != null) && (power.consumePower(2000.0F))) {
/*  893 */             ParticleEffect.SMOKE.send(SoundEffect.RANDOM_ORB, player, 0.5D, 2.0D, 16);
/*  894 */             ItemStack itemstack = player.func_70694_bm();
/*  895 */             if (itemstack.field_77994_a > 1) {
/*  896 */               ItemStack newStack = new ItemStack(Witchery.Items.DUP_GRENADE);
/*  897 */               ItemSunGrenade.setOwnerName(newStack, player.func_70005_c_());
/*      */               
/*  899 */               itemstack.field_77994_a -= 1;
/*  900 */               if (itemstack.field_77994_a <= 0) {
/*  901 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*      */               }
/*  903 */               if (!player.field_71071_by.func_70441_a(newStack)) {
/*  904 */                 this.field_145850_b.func_72838_d(new EntityItem(this.field_145850_b, player.field_70165_t + 0.5D, player.field_70163_u + 1.5D, player.field_70161_v + 0.5D, newStack));
/*  905 */               } else if ((player instanceof EntityPlayerMP)) {
/*  906 */                 ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*      */               }
/*      */             } else {
/*  909 */               ItemStack newStack = new ItemStack(Witchery.Items.DUP_GRENADE);
/*  910 */               ItemSunGrenade.setOwnerName(newStack, player.func_70005_c_());
/*  911 */               player.func_70062_b(0, newStack);
/*  912 */               if ((player instanceof EntityPlayerMP)) {
/*  913 */                 ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*      */               }
/*      */             }
/*      */           } else {
/*  917 */             ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, player, 0.5D, 2.0D, 16);
/*      */           }
/*      */         }
/*      */         else {
/*  921 */           List<EntityMirrorFace> faces = this.field_145850_b.func_72872_a(EntityMirrorFace.class, Witchery.Blocks.MIRROR.func_149668_a(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e));
/*      */           
/*      */ 
/*  924 */           if (faces.size() == 0) {
/*  925 */             showMirrorHead(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  926 */             ParticleEffect.SPELL_COLORED.send(SoundEffect.WITCHERY_MOB_REFLECTION_SPEECH, this, 0.5D, 0.5D, 16, 7829503);
/*      */             
/*      */ 
/*  929 */             double RANGE = 64.0D;
/*  930 */             List<EntityPlayer> players = this.field_145850_b.field_73010_i;
/*  931 */             for (EntityPlayer otherPlayer : players) {
/*  932 */               if (player.func_70092_e(this.field_145851_c, this.field_145848_d, this.field_145849_e) <= RANGE * RANGE) {
/*  933 */                 ChatUtil.sendTranslated(otherPlayer, "witchery.rite.mirrormirror", new Object[] { player.func_70005_c_() });
/*      */               }
/*      */             }
/*      */             
/*      */ 
/*  938 */             boolean fairestFound = false;
/*  939 */             if (this.fairest != null) {
/*  940 */               double R = 100.0D;
/*  941 */               double RY = 32.0D;
/*  942 */               AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_145851_c - 100.0D, this.field_145848_d - 32.0D, this.field_145849_e - 100.0D, this.field_145851_c + 100.0D, this.field_145848_d + 32.0D, this.field_145849_e + 100.0D);
/*      */               
/*  944 */               List<EntityFollower> followers = this.field_145850_b.func_72872_a(EntityFollower.class, bounds);
/*      */               
/*  946 */               for (EntityFollower follower : followers) {
/*  947 */                 if ((follower.getPersistentID().equals(this.fairest)) && (follower.func_70089_S())) {
/*  948 */                   sayNotFairest(player, follower);
/*  949 */                   fairestFound = true;
/*  950 */                   break;
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/*  955 */             if (!fairestFound) {
/*  956 */               boolean isFairestAllowed = this.field_145850_b.func_82737_E() > this.lastFairestSpawn + TimeUtil.minsToTicks(2);
/*      */               
/*      */ 
/*  959 */               this.fairest = null;
/*      */               
/*  961 */               if ((this.favorite == null) || (isFavorite(player))) {
/*  962 */                 this.favorite = player.func_146103_bH();
/*      */                 
/*  964 */                 double CHANCE_OF_NEW_FAIREST = Config.instance().fairestSpawnChance;
/*  965 */                 if ((isFairestAllowed) && (this.field_145850_b.field_73012_v.nextDouble() < CHANCE_OF_NEW_FAIREST)) {
/*  966 */                   EntityFollower follower = new EntityFollower(this.field_145850_b);
/*  967 */                   int followerType = this.field_145850_b.field_73012_v.nextInt(4) + 1;
/*  968 */                   follower.func_94058_c(EntityFollower.generateFollowerName(followerType));
/*  969 */                   follower.func_110163_bv();
/*  970 */                   follower.setFollowerType(followerType);
/*      */                   
/*  972 */                   Coord coord = null;
/*      */                   
/*  974 */                   int minRange = 50;
/*  975 */                   for (int i = 0; (i < 25) && (coord == null); i++) {
/*  976 */                     int x = this.field_145851_c + (this.field_145850_b.field_73012_v.nextBoolean() ? 1 : -1) * (50 + this.field_145850_b.field_73012_v.nextInt(50));
/*      */                     
/*  978 */                     int z = this.field_145849_e + (this.field_145850_b.field_73012_v.nextBoolean() ? 1 : -1) * (50 + this.field_145850_b.field_73012_v.nextInt(50));
/*      */                     
/*  980 */                     int y = Math.min(this.field_145848_d + 20, 250); for (int yMin = Math.max(this.field_145848_d - 20, 2); y >= yMin; y--) {
/*  981 */                       if ((this.field_145850_b.func_147439_a(x, y, z).func_149721_r()) && (this.field_145850_b.func_147439_a(x, y + 1, z).func_149688_o().func_76222_j()) && (this.field_145850_b.func_147437_c(x, y + 2, z)))
/*      */                       {
/*      */ 
/*  984 */                         coord = new Coord(x, y, z);
/*  985 */                         break;
/*      */                       }
/*      */                     }
/*      */                   }
/*      */                   
/*  990 */                   if (coord != null) {
/*  991 */                     follower.func_70080_a(0.5D + coord.x, 1.01D + coord.y, 0.5D + coord.z, 0.0F, 0.0F);
/*      */                     
/*  993 */                     this.field_145850_b.func_72838_d(follower);
/*  994 */                     this.fairest = follower.getPersistentID();
/*  995 */                     fairestFound = true;
/*  996 */                     this.lastFairestSpawn = this.field_145850_b.func_82737_E();
/*  997 */                     sayNotFairest(player, follower);
/*      */                   }
/*      */                 }
/*      */                 
/* 1001 */                 if (!fairestFound) {
/* 1002 */                   ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.you", new Object[0]);
/*      */                 }
/*      */               }
/*      */               else {
/* 1006 */                 ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.anotherplayer", new Object[0]);
/*      */                 
/* 1008 */                 EntityPlayer otherPlayer = this.field_145850_b.func_72924_a(this.favorite.getName());
/* 1009 */                 if (otherPlayer != null) {
/* 1010 */                   sayBearing(player, otherPlayer);
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/* 1015 */             if (this.playersSeen.size() > 1) {
/* 1016 */               List<String> seen = new ArrayList(this.playersSeen);
/* 1017 */               java.util.Collections.sort(seen);
/* 1018 */               StringBuffer sb = new StringBuffer();
/* 1019 */               for (String s : seen) {
/* 1020 */                 if (!s.equals(player.func_70005_c_())) {
/* 1021 */                   if (sb.length() > 0) {
/* 1022 */                     sb.append(", ");
/*      */                   }
/* 1024 */                   sb.append(s);
/*      */                 }
/*      */               }
/* 1027 */               if (sb.length() > 0) {
/* 1028 */                 ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.playersseen", new Object[] { sb.toString() });
/*      */               }
/*      */               else {
/* 1031 */                 ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.playersnotseen", new Object[0]);
/*      */               }
/*      */             }
/*      */             else {
/* 1035 */               ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.playersnotseen", new Object[0]);
/*      */             }
/*      */             
/* 1038 */             if (isFavorite(player)) {
/* 1039 */               this.playersSeen.clear();
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     public void sayNotFairest(EntityPlayer player, EntityFollower follower)
/*      */     {
/* 1049 */       if (follower.getFollowerType() == 4) {
/* 1050 */         ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.anotherm", new Object[0]);
/*      */       } else {
/* 1052 */         ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.anotherf", new Object[0]);
/*      */       }
/*      */       
/* 1055 */       sayBearing(player, follower);
/*      */     }
/*      */     
/*      */     public void sayBearing(EntityPlayer player, EntityLivingBase otherEntity) {
/* 1059 */       double bearingRadians = Math.atan2(0.5D + this.field_145849_e - otherEntity.field_70161_v, 0.5D + this.field_145851_c - otherEntity.field_70165_t);
/*      */       
/* 1061 */       double bearing = (Math.toDegrees(bearingRadians) + 180.0D + 90.0D) % 360.0D;
/* 1062 */       if (bearing < 0.0D) {
/* 1063 */         bearing += 360.0D;
/*      */       }
/*      */       
/* 1066 */       int bearingIndex = (int)bearing / 45;
/* 1067 */       if ((bearingIndex > 7) || (bearingIndex < 0)) {
/* 1068 */         bearingIndex = 0;
/*      */       }
/*      */       
/* 1071 */       ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.bearing" + bearingIndex, new Object[0]);
/*      */     }
/*      */     
/*      */     private void showMirrorHead(World world, int x, int y, int z)
/*      */     {
/* 1076 */       int side = BlockMirror.getDirection(world.func_72805_g(x, y, z));
/*      */       
/* 1078 */       float dx = 0.0F;float dz = 0.0F;
/*      */       
/* 1080 */       float scale = 0.4F;
/* 1081 */       float rotation = 0.0F;
/* 1082 */       if (side == 0) {
/* 1083 */         dz = 0.4F;
/* 1084 */         rotation = -90.0F;
/* 1085 */       } else if (side == 1) {
/* 1086 */         dz = -0.4F;
/* 1087 */         rotation = 90.0F;
/* 1088 */       } else if (side == 2) {
/* 1089 */         dx = 0.4F;
/* 1090 */         rotation = 0.0F;
/* 1091 */       } else if (side == 3) {
/* 1092 */         dx = -0.4F;
/* 1093 */         rotation = -90.0F;
/*      */       }
/* 1095 */       EntityMirrorFace face = new EntityMirrorFace(world);
/* 1096 */       face.func_70107_b(x + 0.5D + dx, y + 0.1D, z + 0.5D + dz);
/* 1097 */       world.func_72838_d(face);
/*      */     }
/*      */     
/*      */     private boolean isDimLinked() {
/* 1101 */       return this.dimCoords != null;
/*      */     }
/*      */     
/*      */     private Coord getDimCoords() {
/* 1105 */       if ((this.dimCoords == null) && (this.field_145850_b.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID)) {
/* 1106 */         World mworld = MinecraftServer.func_71276_C().func_71218_a(Config.instance().dimensionMirrorID);
/*      */         
/* 1108 */         if (mworld != null) {
/* 1109 */           int[][] map = { { 0, 1 }, { 1, 0 } };
/* 1110 */           int cellX = 0;
/* 1111 */           int cellZ = 0;
/* 1112 */           int sign = 1;
/* 1113 */           for (int i = 0; i < 256; i++) {
/* 1114 */             for (int spin = 0; spin <= i; spin++) {
/* 1115 */               for (int j = 0; j < map.length; j++) {
/* 1116 */                 if (i > 0) {
/* 1117 */                   cellX += map[j][0] * sign;
/* 1118 */                   cellZ += map[j][1] * sign;
/*      */                 }
/* 1120 */                 int Y_LEVELS = Config.instance().shrinkMirrorWorld ? 8 : 15;
/* 1121 */                 for (int cellY = 0; cellY < Y_LEVELS; cellY++) {
/* 1122 */                   int dimX = (cellX << 4) + 4;
/* 1123 */                   int dimY = (cellY << 4) + 8;
/* 1124 */                   int dimZ = (cellZ << 4) + 8;
/* 1125 */                   if ((mworld.func_147437_c(dimX, dimY, dimZ)) && (mworld.func_147437_c(dimX, dimY - 1, dimZ)))
/*      */                   {
/* 1127 */                     boolean stop = false;
/* 1128 */                     for (int y = dimY - 1; (y <= dimY + 6) && (!stop); y++) {
/* 1129 */                       for (int x = dimX; (x <= dimX + 8) && (!stop); x++) {
/* 1130 */                         for (int z = dimZ - 4; (z <= dimZ + 4) && (!stop); z++) {
/* 1131 */                           Block block = mworld.func_147439_a(x, y, z);
/* 1132 */                           if (!mworld.func_147437_c(x, y, z)) {
/* 1133 */                             stop = true;
/* 1134 */                             break;
/*      */                           }
/*      */                         }
/*      */                       }
/*      */                     }
/*      */                     
/* 1140 */                     if (!stop) {
/* 1141 */                       Block mirror = Witchery.Blocks.MIRROR_UNBREAKABLE;
/* 1142 */                       int meta = 3;
/* 1143 */                       mworld.func_147465_d(dimX, dimY, dimZ, mirror, meta | 0x4, 3);
/* 1144 */                       TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(mworld, dimX, dimY, dimZ, TileEntityMirror.class);
/*      */                       
/* 1146 */                       if (tile != null) {
/* 1147 */                         tile.dimCoords = new Coord(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 1148 */                         tile.dim = this.field_145850_b.field_73011_w.field_76574_g;
/*      */                       }
/* 1150 */                       if (mworld.func_147439_a(dimX, dimY, dimZ) == mirror) {
/* 1151 */                         mworld.func_147465_d(dimX, dimY - 1, dimZ, mirror, meta, 3);
/*      */                       }
/* 1153 */                       this.dimCoords = new Coord(dimX, dimY, dimZ);
/* 1154 */                       return this.dimCoords;
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/* 1160 */             sign *= -1;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1165 */       return this.dimCoords;
/*      */     }
/*      */     
/*      */     public net.minecraft.network.Packet func_145844_m()
/*      */     {
/* 1170 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 1171 */       func_145841_b(nbtTag);
/* 1172 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*      */     }
/*      */     
/*      */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*      */     {
/* 1177 */       super.onDataPacket(net, packet);
/* 1178 */       NBTTagCompound nbtTag = packet.func_148857_g();
/* 1179 */       func_145839_a(nbtTag);
/* 1180 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */     }
/*      */     
/*      */     public void func_145841_b(NBTTagCompound nbtRoot)
/*      */     {
/* 1185 */       super.func_145841_b(nbtRoot);
/* 1186 */       nbtRoot.func_74772_a("LastFairestSpawnTime", this.lastFairestSpawn);
/* 1187 */       writeItemDataToNBT(nbtRoot);
/*      */     }
/*      */     
/*      */     public void func_145839_a(NBTTagCompound nbtRoot)
/*      */     {
/* 1192 */       super.func_145839_a(nbtRoot);
/* 1193 */       this.lastFairestSpawn = nbtRoot.func_74763_f("LastFairestSpawnTime");
/* 1194 */       readItemDataFromNBT(nbtRoot);
/*      */     }
/*      */     
/*      */     private void writeItemDataToNBT(NBTTagCompound nbtRoot) {
/* 1198 */       if (this.dimCoords != null) {
/* 1199 */         NBTTagCompound nbtDim = this.dimCoords.toTagNBT();
/* 1200 */         nbtDim.func_74768_a("Dimension", this.dim);
/* 1201 */         nbtRoot.func_74782_a("DimCoords", nbtDim);
/*      */       }
/* 1203 */       nbtRoot.func_74757_a("DemonSlain", this.demonKilled);
/*      */       
/* 1205 */       if (this.favorite != null) {
/* 1206 */         NBTTagCompound nbtPlayer = new NBTTagCompound();
/* 1207 */         NBTUtil.func_152460_a(nbtPlayer, this.favorite);
/* 1208 */         nbtRoot.func_74782_a("Favorite", nbtPlayer);
/*      */       }
/*      */       
/* 1211 */       if (this.fairest != null) {
/* 1212 */         nbtRoot.func_74778_a("Fairest", this.fairest.toString());
/*      */       }
/*      */       
/* 1215 */       NBTTagList players = new NBTTagList();
/* 1216 */       for (String player : this.playersSeen) {
/* 1217 */         players.func_74742_a(new NBTTagString(player));
/*      */       }
/* 1219 */       nbtRoot.func_74782_a("PlayersSeen", players);
/*      */     }
/*      */     
/*      */     public void readItemDataFromNBT(NBTTagCompound nbtRoot) {
/* 1223 */       if (nbtRoot.func_74764_b("DimCoords")) {
/* 1224 */         NBTTagCompound nbtDim = nbtRoot.func_74775_l("DimCoords");
/* 1225 */         this.dimCoords = Coord.fromTagNBT(nbtDim);
/* 1226 */         this.dim = nbtDim.func_74762_e("Dimension");
/*      */       }
/* 1228 */       this.demonKilled = nbtRoot.func_74767_n("DemonSlain");
/*      */       
/* 1230 */       if (nbtRoot.func_150297_b("Favorite", 10)) {
/* 1231 */         this.favorite = NBTUtil.func_152459_a(nbtRoot.func_74775_l("Favorite"));
/*      */       } else {
/* 1233 */         this.favorite = null;
/*      */       }
/*      */       
/* 1236 */       if (nbtRoot.func_74764_b("Fairest")) {
/* 1237 */         this.fairest = UUID.fromString(nbtRoot.func_74779_i("Fairest"));
/*      */       } else {
/* 1239 */         this.fairest = null;
/*      */       }
/*      */       
/* 1242 */       this.playersSeen.clear();
/* 1243 */       if (nbtRoot.func_74764_b("PlayersSeen")) {
/* 1244 */         NBTTagList players = nbtRoot.func_150295_c("PlayersSeen", 8);
/* 1245 */         for (int i = 0; i < players.func_74745_c(); i++) {
/* 1246 */           this.playersSeen.add(players.func_150307_f(i));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     public boolean isFavorite(EntityPlayer player) {
/* 1252 */       return (this.favorite != null) && (player != null) && (this.favorite.equals(player.func_146103_bH()));
/*      */     }
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */