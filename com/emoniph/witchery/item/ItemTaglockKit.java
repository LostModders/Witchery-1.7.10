/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockBloodRose.TileEntityBloodRose;
/*     */ import com.emoniph.witchery.blocks.BlockLeechChest.TileEntityLeechChest;
/*     */ import com.emoniph.witchery.entity.EntityEye;
/*     */ import com.emoniph.witchery.entity.EntityTreefyd;
/*     */ import com.emoniph.witchery.entity.EntityWingedMonkey;
/*     */ import com.emoniph.witchery.network.PacketCamPos;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBed;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.event.entity.player.EntityInteractEvent;
/*     */ 
/*     */ public class ItemTaglockKit extends ItemBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon emptyIcon;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon fullIcon;
/*     */   static final String KEY_PLAYER_NAME = "WITCPlayer";
/*     */   static final String KEY_DISPLAY_NAME = "WITCDisplayName";
/*     */   static final String KEY_ENTITY_ID_MOST = "WITCEntityIDm";
/*     */   static final String KEY_ENTITY_ID_LEAST = "WITCEntityIDl";
/*     */   
/*     */   public ItemTaglockKit()
/*     */   {
/*  59 */     func_77625_d(16);
/*  60 */     func_77656_e(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77653_i(ItemStack itemStack)
/*     */   {
/*  66 */     String entityID = getBoundEntityDisplayName(itemStack, Integer.valueOf(1));
/*  67 */     String localizedName = super.func_77653_i(itemStack);
/*  68 */     return !entityID.isEmpty() ? String.format("%s (%s)", new Object[] { localizedName, entityID }) : localizedName;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
/*     */   {
/*  73 */     super.func_77624_a(stack, player, list, advTooltips);
/*  74 */     String entityID = getBoundEntityDisplayName(stack, Integer.valueOf(1));
/*  75 */     if ((entityID != null) && (!entityID.isEmpty())) {
/*  76 */       list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { entityID }));
/*     */     } else {
/*  78 */       list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  84 */     this.emptyIcon = par1IconRegister.func_94245_a(func_111208_A());
/*  85 */     this.fullIcon = par1IconRegister.func_94245_a(func_111208_A() + ".full");
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack stack)
/*     */   {
/*  90 */     return 1200;
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*     */   {
/*  95 */     World world = player.field_70170_p;
/*  96 */     int elapsedTicks = func_77626_a(stack) - countdown;
/*  97 */     if ((!world.field_72995_K) && 
/*  98 */       (elapsedTicks % 20 == 0)) {
/*  99 */       EntityLivingBase entity = getBoundEntity(world, player, stack, Integer.valueOf(1));
/* 100 */       if ((entity != null) && (entity.field_71093_bK == player.field_71093_bK)) {
/* 101 */         if (entity == player) {
/* 102 */           if (elapsedTicks == 0) {
/* 103 */             EntityEye eye = new EntityEye(world);
/* 104 */             eye.func_70012_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, 90.0F);
/* 105 */             world.func_72838_d(eye);
/*     */             
/* 107 */             Witchery.packetPipeline.sendTo(new PacketCamPos(true, elapsedTicks == 0, eye), player);
/*     */           }
/*     */           else {
/* 110 */             Witchery.packetPipeline.sendTo(new PacketCamPos(true, false, null), player);
/*     */           }
/*     */         } else {
/* 113 */           Witchery.packetPipeline.sendTo(new PacketCamPos(true, elapsedTicks == 0, entity), player);
/*     */         }
/*     */       } else {
/* 116 */         Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumAction func_77661_b(ItemStack stack)
/*     */   {
/* 124 */     return EnumAction.none;
/*     */   }
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 129 */     if (!world.field_72995_K) {
/* 130 */       Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), player);
/*     */     }
/* 132 */     return stack;
/*     */   }
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 137 */     if (!world.field_72995_K) {
/* 138 */       Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), player);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int damageValue)
/*     */   {
/* 145 */     return damageValue == 1 ? this.fullIcon : this.emptyIcon;
/*     */   }
/*     */   
/*     */   public static class PlayerComparitor implements java.util.Comparator<EntityPlayer>
/*     */   {
/*     */     public int compare(EntityPlayer p1, EntityPlayer p2) {
/* 151 */       return p1.func_70005_c_().compareTo(p2.func_70005_c_());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 157 */     Block block = world.func_147439_a(x, y, z);
/* 158 */     if ((block == Blocks.field_150324_C) || (block == Witchery.Blocks.COFFIN) || (block.func_149739_a().equals("tile.blockCarpentersBed")) || (block.isBed(world, x, y, z, player))) {
/* 159 */       int i1 = world.func_72805_g(x, y, z);
/* 160 */       Log.instance().debug(String.format("Using taglock on bed [%s] meta %d", new Object[] { block.func_149739_a(), Integer.valueOf(i1) }));
/*     */       
/* 162 */       if ((block == Blocks.field_150324_C) && (!BlockBed.func_149975_b(i1))) {
/* 163 */         int j1 = BlockBed.func_149895_l(i1);
/* 164 */         x += BlockBed.field_149981_a[j1][0];
/* 165 */         z += BlockBed.field_149981_a[j1][1];
/*     */         
/* 167 */         if (world.func_147439_a(x, y, z) != Blocks.field_150324_C) {
/* 168 */           SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */           
/* 170 */           return !world.field_72995_K;
/*     */         }
/*     */       }
/*     */       
/* 174 */       ChunkCoordinates clickedBedLocation = new ChunkCoordinates(x, y, z);
/*     */       
/* 176 */       if (player.func_70093_af()) {
/* 177 */         if (!world.field_72995_K) {
/* 178 */           setTaglockForEntity(world, player, itemstack, player);
/*     */         }
/* 180 */         return !world.field_72995_K;
/*     */       }
/* 182 */       if (!world.field_72995_K) {
/* 183 */         boolean taglockSaved = tryBindTaglockFromBed(itemstack, player, world, clickedBedLocation);
/* 184 */         if ((!taglockSaved) && (block != Blocks.field_150324_C)) {
/* 185 */           if (world.func_147439_a(x + 1, y, z) == block) {
/* 186 */             taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x + 1, y, z));
/*     */           }
/* 188 */           if ((!taglockSaved) && (world.func_147439_a(x, y, z + 1) == block)) {
/* 189 */             taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x, y, z + 1));
/*     */           }
/* 191 */           if ((!taglockSaved) && (world.func_147439_a(x - 1, y, z) == block)) {
/* 192 */             taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x - 1, y, z));
/*     */           }
/* 194 */           if ((!taglockSaved) && (world.func_147439_a(x, y, z - 1) == block)) {
/* 195 */             taglockSaved = tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x, y, z - 1));
/*     */           }
/*     */         }
/*     */         
/* 199 */         if (taglockSaved) {
/* 200 */           return !world.field_72995_K;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 205 */       if (!world.field_72995_K) {
/* 206 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */       }
/*     */       
/* 209 */       return !world.field_72995_K; }
/* 210 */     if (block == Witchery.Blocks.LEECH_CHEST) {
/* 211 */       if (!world.field_72995_K) {
/* 212 */         TileEntity tile = world.func_147438_o(x, y, z);
/* 213 */         if ((tile != null) && ((tile instanceof BlockLeechChest.TileEntityLeechChest))) {
/* 214 */           BlockLeechChest.TileEntityLeechChest chest = (BlockLeechChest.TileEntityLeechChest)tile;
/* 215 */           String username = chest.popUserExcept(player);
/* 216 */           if ((username != null) && (!username.isEmpty())) {
/* 217 */             setTaglockForEntity(world, player, itemstack, username);
/* 218 */             return !world.field_72995_K;
/*     */           }
/*     */         }
/*     */         
/* 222 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */       }
/*     */       
/* 225 */       return !world.field_72995_K; }
/* 226 */     if (block == Witchery.Blocks.BLOOD_ROSE) {
/* 227 */       if (!world.field_72995_K) {
/* 228 */         TileEntity tile = world.func_147438_o(x, y, z);
/* 229 */         if ((tile != null) && ((tile instanceof BlockBloodRose.TileEntityBloodRose))) {
/* 230 */           BlockBloodRose.TileEntityBloodRose chest = (BlockBloodRose.TileEntityBloodRose)tile;
/* 231 */           String username = chest.popUserExcept(player, false);
/* 232 */           if ((username != null) && (!username.isEmpty())) {
/* 233 */             setTaglockForEntity(world, player, itemstack, username);
/* 234 */             return !world.field_72995_K;
/*     */           }
/*     */         }
/*     */         
/* 238 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */       }
/*     */       
/* 241 */       return !world.field_72995_K; }
/* 242 */     if (block == Witchery.Blocks.CRYSTAL_BALL) {
/* 243 */       if (itemstack.func_77960_j() > 0) {
/* 244 */         if ((!world.field_72995_K) && (com.emoniph.witchery.blocks.BlockCrystalBall.tryConsumePower(world, player, x, y, z))) {
/* 245 */           player.func_71008_a(itemstack, func_77626_a(itemstack));
/* 246 */         } else if (world.field_72995_K) {
/* 247 */           player.func_71008_a(itemstack, func_77626_a(itemstack));
/*     */         }
/*     */       } else {
/* 250 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */       }
/* 252 */       return !world.field_72995_K;
/*     */     }
/*     */     
/* 255 */     return super.onItemUseFirst(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean tryBindTaglockFromBed(ItemStack itemstack, EntityPlayer player, World world, ChunkCoordinates clickedBedLocation)
/*     */   {
/* 261 */     String boundName = "";
/* 262 */     EntityLivingBase boundEntity = getBoundEntity(world, player, itemstack, Integer.valueOf(1));
/* 263 */     if ((boundEntity != null) && ((boundEntity instanceof EntityPlayer))) {
/* 264 */       boundName = ((EntityPlayer)boundEntity).func_70005_c_();
/*     */     }
/*     */     
/*     */ 
/* 268 */     ArrayList<EntityPlayer> players = new ArrayList();
/*     */     
/* 270 */     for (Object obj : world.field_73010_i) {
/* 271 */       EntityPlayer worldPlayer = (EntityPlayer)obj;
/* 272 */       ChunkCoordinates playerBedLocation = worldPlayer.getBedLocation(player.field_71093_bK);
/* 273 */       if ((playerBedLocation != null) && (playerBedLocation.equals(clickedBedLocation))) {
/* 274 */         players.add(worldPlayer);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 279 */     java.util.Collections.sort(players, new PlayerComparitor());
/*     */     
/* 281 */     boolean taglockSaved = false;
/*     */     
/*     */ 
/* 284 */     if (players.size() > 0) {
/* 285 */       if (boundName.isEmpty()) {
/* 286 */         taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
/*     */       } else {
/* 288 */         boolean found = false;
/* 289 */         for (int i = 0; (i < players.size()) && (!found); i++) {
/* 290 */           if (((EntityPlayer)players.get(i)).func_70005_c_().equals(boundName)) {
/* 291 */             if (i == players.size() - 1) {
/* 292 */               taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
/*     */             } else {
/* 294 */               taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(i + 1));
/*     */             }
/* 296 */             found = true;
/*     */           }
/*     */         }
/* 299 */         if (!found) {
/* 300 */           taglockSaved = setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
/*     */         }
/*     */       }
/*     */     }
/* 304 */     return taglockSaved;
/*     */   }
/*     */   
/*     */   public static boolean isTaglockRestricted(EntityPlayer collector, EntityLivingBase target) {
/* 308 */     if ((!(target instanceof EntityPlayer)) || (collector.equals(target))) {
/* 309 */       return false;
/*     */     }
/* 311 */     if ((Config.instance().restrictTaglockCollectionOnNonPVPServers) && (!MinecraftServer.func_71276_C().func_71219_W())) {
/* 312 */       return true;
/*     */     }
/* 314 */     EntityPlayer targetPlayer = (EntityPlayer)target;
/* 315 */     if ((Config.instance().restrictTaglockCollectionForStaffMembers) && (MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(targetPlayer.func_146103_bH()))) {
/* 316 */       return true;
/*     */     }
/* 318 */     return false;
/*     */   }
/*     */   
/*     */   private boolean setTaglockForEntity(World world, EntityPlayer player, ItemStack itemstack, EntityPlayer victim) {
/* 322 */     if (!isTaglockRestricted(player, victim)) {
/* 323 */       setTaglockForEntity(world, player, itemstack, victim.func_70005_c_());
/* 324 */       return true;
/*     */     }
/* 326 */     return false;
/*     */   }
/*     */   
/*     */   private void setTaglockForEntity(World world, EntityPlayer player, ItemStack itemstack, String victimUsername)
/*     */   {
/* 331 */     if (!world.field_72995_K) {
/* 332 */       if (itemstack.field_77994_a > 1) {
/* 333 */         ItemStack newStack = new ItemStack(this, 1, 1);
/* 334 */         setTaglockForEntity(newStack, player, victimUsername, true, Integer.valueOf(1));
/*     */         
/* 336 */         itemstack.field_77994_a -= 1;
/* 337 */         if (itemstack.field_77994_a <= 0) {
/* 338 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */         }
/* 340 */         if (!player.field_71071_by.func_70441_a(newStack)) {
/* 341 */           world.func_72838_d(new EntityItem(world, player.field_70165_t + 0.5D, player.field_70163_u + 1.5D, player.field_70161_v + 0.5D, newStack));
/* 342 */         } else if ((player instanceof EntityPlayerMP)) {
/* 343 */           ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */         }
/*     */       } else {
/* 346 */         setTaglockForEntity(itemstack, player, victimUsername, true, Integer.valueOf(1));
/* 347 */         itemstack.func_77964_b(1);
/* 348 */         if ((player instanceof EntityPlayerMP)) {
/* 349 */           ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void onEntityInteract(World world, EntityPlayer player, ItemStack stack, EntityInteractEvent event) {
/* 356 */     if ((!world.field_72995_K) && 
/* 357 */       (stack != null) && (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) && (event.target != null) && ((event.target instanceof EntityLivingBase)))
/*     */     {
/* 359 */       EntityLivingBase target = (EntityLivingBase)event.target;
/* 360 */       if ((!(target instanceof EntityPlayer)) || (isSneakSuccessful(player, target))) {
/* 361 */         if ((!(target instanceof EntityTreefyd)) && (!(target instanceof com.emoniph.witchery.entity.EntityImp)) && ((!(target instanceof EntityWingedMonkey)) || (player.func_70093_af()))) {
/* 362 */           if (stack.field_77994_a > 1) {
/* 363 */             ItemStack newStack = new ItemStack(Witchery.Items.TAGLOCK_KIT, 1, 1);
/* 364 */             Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(newStack, player, target, true, Integer.valueOf(1));
/* 365 */             stack.field_77994_a -= 1;
/* 366 */             if (stack.field_77994_a <= 0) {
/* 367 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */             }
/* 369 */             if (!player.field_71071_by.func_70441_a(newStack)) {
/* 370 */               world.func_72838_d(new EntityItem(world, player.field_70165_t + 0.5D, player.field_70163_u + 1.5D, player.field_70161_v + 0.5D, newStack));
/*     */             }
/* 372 */             else if ((player instanceof EntityPlayerMP)) {
/* 373 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */           } else {
/* 376 */             Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(stack, player, target, true, Integer.valueOf(1));
/* 377 */             stack.func_77964_b(1);
/* 378 */             if ((player instanceof EntityPlayerMP)) {
/* 379 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 386 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, event.entityPlayer, "witchery.taglockkit.taglockfailed", new Object[0]);
/*     */         
/* 388 */         if ((target instanceof EntityPlayer)) {
/* 389 */           ChatUtil.sendTranslated(EnumChatFormatting.GREEN, (EntityPlayer)target, "witchery.taglockkit.taglockdiscovered", new Object[0]);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 394 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isSneakSuccessful(EntityPlayer sneaker, EntityLivingBase target)
/*     */   {
/* 401 */     if (isTaglockRestricted(sneaker, target)) {
/* 402 */       return false;
/*     */     }
/*     */     
/* 405 */     double sneakerFacing = (sneaker.field_70759_as + 90.0F) % 360.0F;
/* 406 */     if (sneakerFacing < 0.0D) {
/* 407 */       sneakerFacing += 360.0D;
/*     */     }
/*     */     
/* 410 */     double targetFacing = (target.field_70759_as + 90.0F) % 360.0F;
/* 411 */     if (targetFacing < 0.0D) {
/* 412 */       targetFacing += 360.0D;
/*     */     }
/*     */     
/* 415 */     double ARC = 45.0D;
/* 416 */     double diff = Math.abs(targetFacing - sneakerFacing);
/*     */     
/*     */ 
/*     */ 
/* 420 */     double chance = 0.0D;
/*     */     
/* 422 */     if ((360.0D - diff % 360.0D < 45.0D) || (diff % 360.0D < 45.0D)) {
/* 423 */       chance = sneaker.func_70093_af() ? 0.6D : 0.3D;
/*     */     } else {
/* 425 */       chance = sneaker.func_70093_af() ? 0.1D : 0.01D;
/* 426 */       if (sneaker.func_82150_aj()) {
/* 427 */         chance += 0.1D;
/*     */       }
/*     */     }
/* 430 */     return sneaker.field_70170_p.field_73012_v.nextDouble() < chance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTaglockForEntity(ItemStack stack, EntityPlayer player, Entity entity, boolean playSoundAtPlayer, Integer index)
/*     */   {
/* 440 */     if (!stack.func_77942_o()) {
/* 441 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/*     */     
/* 444 */     if ((entity instanceof EntityPlayer)) {
/* 445 */       EntityPlayer hitPlayer = (EntityPlayer)entity;
/* 446 */       stack.func_77978_p().func_74778_a("WITCPlayer" + index.toString(), hitPlayer.func_70005_c_());
/* 447 */       stack.func_77978_p().func_82580_o("WITCEntityIDm" + index.toString());
/* 448 */       stack.func_77978_p().func_82580_o("WITCEntityIDl" + index.toString());
/* 449 */     } else if ((entity instanceof EntityLiving)) {
/* 450 */       stack.func_77978_p().func_82580_o("WITCPlayer" + index.toString());
/* 451 */       UUID id = entity.getPersistentID();
/* 452 */       ((EntityLiving)entity).func_110163_bv();
/*     */       
/*     */ 
/* 455 */       stack.func_77978_p().func_74772_a("WITCEntityIDm" + index.toString(), id.getMostSignificantBits());
/* 456 */       stack.func_77978_p().func_74772_a("WITCEntityIDl" + index.toString(), id.getLeastSignificantBits());
/* 457 */       stack.func_77978_p().func_74778_a("WITCDisplayName" + index.toString(), entity.func_70005_c_());
/*     */     } else {
/* 459 */       return;
/*     */     }
/*     */     
/* 462 */     if (playSoundAtPlayer) {
/* 463 */       player.field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearTaglock(ItemStack stack, Integer index) {
/* 468 */     if (stack != null) {
/* 469 */       stack.func_77978_p().func_82580_o("WITCEntityIDm" + index.toString());
/* 470 */       stack.func_77978_p().func_82580_o("WITCEntityIDl" + index.toString());
/* 471 */       stack.func_77978_p().func_82580_o("WITCPlayer" + index.toString());
/* 472 */       stack.func_77978_p().func_82580_o("WITCDisplayName" + index.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTaglockForEntity(ItemStack stack, EntityPlayer player, String username, boolean playSoundAtPlayer, Integer index)
/*     */   {
/* 478 */     if (!stack.func_77942_o()) {
/* 479 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/*     */     
/* 482 */     if ((username != null) && (!username.isEmpty())) {
/* 483 */       stack.func_77978_p().func_74778_a("WITCPlayer" + index.toString(), username);
/* 484 */       stack.func_77978_p().func_82580_o("WITCEntityIDm" + index.toString());
/* 485 */       stack.func_77978_p().func_82580_o("WITCEntityIDl" + index.toString());
/*     */     } else {
/* 487 */       return;
/*     */     }
/*     */     
/* 490 */     if (playSoundAtPlayer) {
/* 491 */       player.field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isTaglockPresent(ItemStack itemStack, Integer index) {
/* 496 */     if (itemStack.func_77942_o()) {
/* 497 */       if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
/* 498 */         String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
/* 499 */         if ((playerName != null) && (!playerName.isEmpty())) {
/* 500 */           return true;
/*     */         }
/*     */       }
/* 503 */       if ((itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())))
/*     */       {
/* 505 */         return true;
/*     */       }
/*     */     }
/* 508 */     return false;
/*     */   }
/*     */   
/*     */   public String getBoundEntityDisplayName(ItemStack itemStack, Integer index) {
/* 512 */     if (itemStack.func_77942_o()) {
/* 513 */       if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
/* 514 */         String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
/* 515 */         return playerName; }
/* 516 */       if ((itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCDisplayName" + index.toString())))
/*     */       {
/*     */ 
/* 519 */         String displayName = itemStack.func_77978_p().func_74779_i("WITCDisplayName" + index.toString());
/* 520 */         return displayName;
/*     */       }
/*     */     }
/* 523 */     return "";
/*     */   }
/*     */   
/*     */   public static enum BoundType {
/* 527 */     NONE,  PLAYER,  CREATURE;
/*     */     
/*     */     private BoundType() {} }
/*     */   
/* 531 */   public BoundType getBoundEntityType(ItemStack itemStack, Integer index) { if (itemStack.func_77942_o()) {
/* 532 */       if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString()))
/* 533 */         return BoundType.PLAYER;
/* 534 */       if ((itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCDisplayName" + index.toString())))
/*     */       {
/*     */ 
/* 537 */         return BoundType.CREATURE;
/*     */       }
/*     */     }
/* 540 */     return BoundType.NONE;
/*     */   }
/*     */   
/*     */   public String getBoundUsername(ItemStack itemStack, Integer index) {
/* 544 */     if ((itemStack.func_77942_o()) && 
/* 545 */       (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString()))) {
/* 546 */       String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
/* 547 */       return playerName;
/*     */     }
/*     */     
/* 550 */     return "";
/*     */   }
/*     */   
/*     */   public UUID getBoundCreatureID(ItemStack itemStack, Integer index) {
/* 554 */     if ((itemStack.func_77942_o()) && 
/* 555 */       (itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCDisplayName" + index.toString())))
/*     */     {
/*     */ 
/*     */ 
/* 559 */       String displayName = itemStack.func_77978_p().func_74779_i("WITCDisplayName" + index.toString());
/* 560 */       UUID entityID = new UUID(itemStack.func_77978_p().func_74763_f("WITCEntityIDm" + index.toString()), itemStack.func_77978_p().func_74763_f("WITCEntityIDl" + index.toString()));
/*     */       
/* 562 */       return entityID;
/*     */     }
/*     */     
/* 565 */     return new UUID(0L, 0L);
/*     */   }
/*     */   
/*     */   public void addTagLockToPoppet(ItemStack stackTaglockKit, ItemStack stackPoppet, Integer index) {
/* 569 */     assert (stackTaglockKit != null) : "stack of taglock kit cannot be null";
/* 570 */     assert (stackPoppet != null) : "stack poppet cannot be null";
/*     */     
/* 572 */     Integer tagLockIndex = Integer.valueOf(1);
/*     */     
/* 574 */     if (!stackPoppet.func_77942_o()) {
/* 575 */       stackPoppet.func_77982_d(new NBTTagCompound());
/*     */     }
/*     */     
/* 578 */     if (stackTaglockKit.func_77942_o()) {
/* 579 */       if (stackTaglockKit.func_77978_p().func_74764_b("WITCPlayer" + tagLockIndex.toString())) {
/* 580 */         String playerName = stackTaglockKit.func_77978_p().func_74779_i("WITCPlayer" + tagLockIndex.toString());
/* 581 */         if (playerName != null) {
/* 582 */           stackPoppet.func_77978_p().func_74778_a("WITCPlayer" + index.toString(), playerName);
/*     */         }
/* 584 */       } else if ((stackTaglockKit.func_77978_p().func_74764_b("WITCEntityIDm" + tagLockIndex.toString())) && (stackTaglockKit.func_77978_p().func_74764_b("WITCEntityIDl" + tagLockIndex.toString())) && (stackTaglockKit.func_77978_p().func_74764_b("WITCDisplayName" + tagLockIndex.toString())))
/*     */       {
/*     */ 
/* 587 */         stackPoppet.func_77978_p().func_74772_a("WITCEntityIDm" + index.toString(), stackTaglockKit.func_77978_p().func_74763_f("WITCEntityIDm" + tagLockIndex.toString()));
/*     */         
/* 589 */         stackPoppet.func_77978_p().func_74772_a("WITCEntityIDl" + index.toString(), stackTaglockKit.func_77978_p().func_74763_f("WITCEntityIDl" + tagLockIndex.toString()));
/*     */         
/* 591 */         stackPoppet.func_77978_p().func_74778_a("WITCDisplayName" + index.toString(), stackTaglockKit.func_77978_p().func_74779_i("WITCDisplayName" + tagLockIndex.toString()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean containsTaglockForEntity(ItemStack itemStack, Entity entity, Integer index)
/*     */   {
/* 599 */     if (itemStack.func_77942_o()) {
/* 600 */       if ((entity instanceof EntityPlayer)) {
/* 601 */         EntityPlayer player = (EntityPlayer)entity;
/* 602 */         if (itemStack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
/* 603 */           String playerName = itemStack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
/* 604 */           if ((playerName != null) && (playerName.equals(player.func_70005_c_()))) {
/* 605 */             return true;
/*     */           }
/*     */         }
/* 608 */       } else if (((entity instanceof EntityLiving)) && 
/* 609 */         (itemStack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (itemStack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString())))
/*     */       {
/* 611 */         UUID entityID = new UUID(itemStack.func_77978_p().func_74763_f("WITCEntityIDm" + index.toString()), itemStack.func_77978_p().func_74763_f("WITCEntityIDl" + index.toString()));
/*     */         
/* 613 */         if (entityID.equals(entity.getPersistentID())) {
/* 614 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 619 */     return false;
/*     */   }
/*     */   
/*     */   public EntityLivingBase getBoundEntity(World world, Entity entity, ItemStack stack, Integer index) {
/* 623 */     if (stack.func_77942_o()) {
/* 624 */       if (stack.func_77978_p().func_74764_b("WITCPlayer" + index.toString())) {
/* 625 */         String playerName = stack.func_77978_p().func_74779_i("WITCPlayer" + index.toString());
/* 626 */         if ((playerName != null) && (!playerName.isEmpty())) {
/* 627 */           if (!world.field_72995_K) {
/* 628 */             MinecraftServer server = MinecraftServer.func_71276_C();
/* 629 */             for (WorldServer worldServer : server.field_71305_c) {
/* 630 */               for (Object obj : worldServer.field_73010_i) {
/* 631 */                 EntityPlayer player = (EntityPlayer)obj;
/* 632 */                 if (player.func_70005_c_().equalsIgnoreCase(playerName)) {
/* 633 */                   return player;
/*     */                 }
/*     */               }
/*     */             }
/*     */           } else {
/* 638 */             for (Object obj : world.field_73010_i) {
/* 639 */               EntityPlayer player = (EntityPlayer)obj;
/* 640 */               if (player.func_70005_c_().equalsIgnoreCase(playerName)) {
/* 641 */                 return player;
/*     */               }
/*     */             }
/*     */           }
/* 645 */           return null;
/*     */         }
/*     */       }
/* 648 */       if ((stack.func_77978_p().func_74764_b("WITCEntityIDm" + index.toString())) && (stack.func_77978_p().func_74764_b("WITCEntityIDl" + index.toString()))) {
/* 649 */         UUID entityID = new UUID(stack.func_77978_p().func_74763_f("WITCEntityIDm" + index.toString()), stack.func_77978_p().func_74763_f("WITCEntityIDl" + index.toString()));
/*     */         
/* 651 */         if (!world.field_72995_K) {
/* 652 */           MinecraftServer server = MinecraftServer.func_71276_C();
/* 653 */           for (WorldServer worldServer : server.field_71305_c) {
/* 654 */             for (Object obj : worldServer.field_72996_f) {
/* 655 */               if ((obj instanceof EntityLivingBase)) {
/* 656 */                 EntityLivingBase living = (EntityLivingBase)obj;
/* 657 */                 UUID livingID = living.getPersistentID();
/* 658 */                 if ((entityID.equals(livingID)) && (living.func_70089_S())) {
/* 659 */                   return living;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         } else {
/* 665 */           for (Object obj : world.field_72996_f) {
/* 666 */             if ((obj instanceof EntityLivingBase)) {
/* 667 */               EntityLivingBase living = (EntityLivingBase)obj;
/* 668 */               UUID livingID = living.getPersistentID();
/* 669 */               if ((entityID.equals(livingID)) && (living.func_70089_S())) {
/* 670 */                 return living;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 675 */         return null;
/*     */       }
/*     */     }
/*     */     
/* 679 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemTaglockKit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */