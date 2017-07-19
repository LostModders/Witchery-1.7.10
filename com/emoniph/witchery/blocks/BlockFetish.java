/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.EntityCorpse;
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import com.emoniph.witchery.entity.EntityIllusion;
/*     */ import com.emoniph.witchery.familiar.IFamiliar;
/*     */ import com.emoniph.witchery.infusion.infusions.spirit.IFetishTile;
/*     */ import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
/*     */ import com.emoniph.witchery.item.ItemTaglockKit;
/*     */ import com.emoniph.witchery.item.ItemTaglockKit.BoundType;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockColored;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntitySquid;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class BlockFetish extends BlockBaseContainer
/*     */ {
/*     */   public static class ClassItemBlock extends ItemBlock
/*     */   {
/*     */     public ClassItemBlock(Block block)
/*     */     {
/*  67 */       super();
/*     */     }
/*     */     
/*     */     public String func_77653_i(ItemStack stack)
/*     */     {
/*  72 */       String s = super.func_77653_i(stack);
/*  73 */       String effect = InfusedSpiritEffect.getEffectDisplayName(stack);
/*  74 */       if (effect != null) {
/*  75 */         return s + " (" + effect + ")";
/*     */       }
/*  77 */       return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public BlockFetish()
/*     */   {
/*  83 */     super(Material.field_151575_d, TileEntityFetish.class, ClassItemBlock.class);
/*  84 */     func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
/*  85 */     func_149752_b(100000.0F);
/*  86 */     func_149711_c(3.5F);
/*  87 */     func_149672_a(field_149766_f);
/*     */   }
/*     */   
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List list)
/*     */   {
/*  92 */     super.func_149666_a(item, tabs, list);
/*  93 */     if (Item.func_150898_a(Witchery.Blocks.FETISH_SCARECROW) == item) {
/*  94 */       list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.POPPET_ENHANCEMENT));
/*     */     }
/*     */     
/*  97 */     if ((Item.func_150898_a(Witchery.Blocks.FETISH_SCARECROW) == item) || (Item.func_150898_a(Witchery.Blocks.FETISH_WITCHS_LADDER) == item)) {
/*  98 */       list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.SCREAMER));
/*     */     }
/*     */     
/* 101 */     if (Item.func_150898_a(Witchery.Blocks.FETISH_SCARECROW) == item) {
/* 102 */       list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.SENTINAL));
/*     */     }
/*     */     
/* 105 */     if (Item.func_150898_a(Witchery.Blocks.FETISH_SCARECROW) == item) {
/* 106 */       list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.TWISTER));
/*     */     }
/*     */     
/* 109 */     if (Item.func_150898_a(Witchery.Blocks.FETISH_SCARECROW) == item) {
/* 110 */       list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.GHOST_WALKER));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int posX, int posY, int posZ)
/*     */   {
/* 116 */     super.func_149726_b(world, posX, posY, posZ);
/* 117 */     BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int posX, int posY, int posZ)
/*     */   {
/* 122 */     TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityFetish.class);
/* 123 */     if ((this == Witchery.Blocks.FETISH_WITCHS_LADDER) || ((tile != null) && (tile.isSpectral()))) {
/* 124 */       return null;
/*     */     }
/* 126 */     return super.func_149668_a(world, posX, posY, posZ);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_149712_f(World world, int posX, int posY, int posZ)
/*     */   {
/* 132 */     TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityFetish.class);
/* 133 */     if ((tile == null) || (!tile.isSpectral())) {
/* 134 */       return super.func_149712_f(world, posX, posY, posZ);
/*     */     }
/* 136 */     return -1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase player, ItemStack stack)
/*     */   {
/* 142 */     int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/* 144 */     if (l == 0) {
/* 145 */       world.func_72921_c(posX, posY, posZ, 2, 2);
/* 146 */     } else if (l == 1) {
/* 147 */       world.func_72921_c(posX, posY, posZ, 5, 2);
/* 148 */     } else if (l == 2) {
/* 149 */       world.func_72921_c(posX, posY, posZ, 3, 2);
/* 150 */     } else if (l == 3) {
/* 151 */       world.func_72921_c(posX, posY, posZ, 4, 2);
/*     */     }
/*     */     
/* 154 */     if (stack != null) {
/* 155 */       TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityFetish.class);
/* 156 */       if (tile != null) {
/* 157 */         NBTTagCompound nbtRoot = stack.func_77978_p();
/* 158 */         tile.setEffectType(InfusedSpiritEffect.getEffectID(stack));
/* 159 */         if ((nbtRoot != null) && (nbtRoot.func_74764_b("TileData"))) {
/* 160 */           NBTTagCompound nbtTileData = nbtRoot.func_74775_l("TileData");
/* 161 */           tile.readSubDataFromNBT(nbtTileData);
/* 162 */           if ((tile.getEffectType() == 0) && (InfusedSpiritEffect.getEffectID(stack) != 0)) {
/* 163 */             tile.setEffectType(InfusedSpiritEffect.getEffectID(stack));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 170 */     if ((!world.field_72995_K) && (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID)) {
/* 171 */       World overworld = MinecraftServer.func_71276_C().func_71218_a(0);
/* 172 */       if ((overworld != null) && (overworld.func_147437_c(posX, posY, posZ))) {
/* 173 */         BlockUtil.setBlock(overworld, posX, posY, posZ, this);
/* 174 */         func_149689_a(overworld, posX, posY, posZ, player, stack);
/* 175 */         TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(overworld, posX, posY, posZ, TileEntityFetish.class);
/* 176 */         if (tile != null) {
/* 177 */           tile.setSpectral(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 185 */     if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
/* 186 */       par5 |= 0x8;
/* 187 */       world.func_72921_c(posX, posY, posZ, par5, 4);
/*     */     }
/*     */     
/* 190 */     func_149697_b(world, posX, posY, posZ, par5, 0);
/*     */     
/* 192 */     super.func_149681_a(world, posX, posY, posZ, par5, par6EntityPlayer);
/*     */     
/* 194 */     if ((!world.field_72995_K) && (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID)) {
/* 195 */       World overworld = MinecraftServer.func_71276_C().func_71218_a(0);
/* 196 */       if ((overworld != null) && (BlockUtil.getBlock(overworld, posX, posY, posZ) == this)) {
/* 197 */         overworld.func_147468_f(posX, posY, posZ);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 204 */     ArrayList<ItemStack> drops = new ArrayList();
/* 205 */     if ((metadata & 0x8) == 0) {
/* 206 */       TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, x, y, z, TileEntityFetish.class);
/* 207 */       if (tile != null) {
/* 208 */         ItemStack stack = new ItemStack(tile.func_145838_q());
/* 209 */         NBTTagCompound nbtRoot = new NBTTagCompound();
/* 210 */         stack.func_77982_d(nbtRoot);
/* 211 */         nbtRoot.func_74774_a("BlockColor", (byte)tile.getColor());
/* 212 */         InfusedSpiritEffect.setEffectID(stack, tile.getEffectType());
/*     */         
/* 214 */         NBTTagCompound nbtTileData = new NBTTagCompound();
/* 215 */         tile.writeSubDataToNBT(nbtTileData);
/* 216 */         nbtRoot.func_74782_a("TileData", nbtTileData);
/*     */         
/* 218 */         drops.add(stack);
/*     */       }
/*     */     }
/* 221 */     return drops;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 226 */     return this == Witchery.Blocks.FETISH_WITCHS_LADDER ? 1 : super.func_149645_b();
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 231 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 236 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 242 */     return this == Witchery.Blocks.FETISH_WITCHS_LADDER;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
/*     */   {
/* 247 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 252 */     TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(world, x, y, z, TileEntityFetish.class);
/* 253 */     if ((tile != null) && (player != null)) {
/* 254 */       ItemStack stack = player.func_70694_bm();
/* 255 */       if (stack != null) {
/* 256 */         if (!tile.isSpectral()) {
/* 257 */           if (stack.func_77973_b() == Items.field_151100_aR) {
/* 258 */             int color = BlockColored.func_150032_b(stack.func_77960_j());
/* 259 */             tile.setColor(color);
/* 260 */             if (!player.field_71075_bZ.field_75098_d) {
/* 261 */               if (--stack.field_77994_a == 0) {
/* 262 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */               }
/*     */             }
/* 265 */             return true; }
/* 266 */           if (stack.func_77973_b() == Items.field_151131_as) {
/* 267 */             tile.clearBoundEntities(stack, player);
/* 268 */             SoundEffect.WATER_SWIM.playAtPlayer(world, player);
/* 269 */             return true; }
/* 270 */           if (stack.func_77973_b() == Witchery.Items.BOLINE) {
/* 271 */             tile.cycleBoundMode(player);
/* 272 */             return true;
/*     */           }
/*     */         }
/*     */         
/* 276 */         if (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) {
/* 277 */           tile.setBoundEntity(stack, player, tile.isSpectral());
/* 278 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 282 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149709_b(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side)
/*     */   {
/* 287 */     TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(par1IBlockAccess, posX, posY, posZ, TileEntityFetish.class);
/* 288 */     if (tile != null) {
/* 289 */       return tile.getPowerLevel();
/*     */     }
/* 291 */     return super.func_149709_b(par1IBlockAccess, posX, posY, posZ, side);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149748_c(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side)
/*     */   {
/* 297 */     return side == 1 ? func_149709_b(par1IBlockAccess, posX, posY, posZ, side) : 0;
/*     */   }
/*     */   
/*     */   public boolean func_149744_f()
/*     */   {
/* 302 */     return true;
/*     */   }
/*     */   
/*     */   public static class TileEntityFetish extends TileEntityBase implements IFetishTile
/*     */   {
/* 307 */     private BlockFetish.CreatureID testID = new BlockFetish.CreatureID(new UUID(0L, 0L), "");
/*     */     
/*     */     boolean lastRaiseAlarm;
/*     */     
/*     */     long lastActivationTime;
/* 312 */     final int TRIGGER_WHEN_PLAYER_NOT_IN_WHITELIST = 0;
/* 313 */     final int TRIGGER_WHEN_PLAYER_IN_BLACKLIST = 1;
/* 314 */     final int TRIGGER_WHEN_CREATURE_NOT_IN_WHITELIST = 2;
/* 315 */     final int TRIGGER_WHEN_NOT_ALL_CREATURES_FOUND = 3;
/* 316 */     final int TRIGGER_WHEN_SOME_CREATURES_NOT_FOUND = 4;
/* 317 */     final int TRIGGER_OFF = 5;
/*     */     
/* 319 */     int alarmMode = 5;
/*     */     private Block expectedBlock;
/*     */     private boolean spectral;
/*     */     
/*     */     public TileEntityFetish setExpectedBlock(Block block) {
/* 324 */       this.expectedBlock = block;
/* 325 */       return this;
/*     */     }
/*     */     
/*     */     public Block getExpectedBlock() {
/* 329 */       return this.expectedBlock;
/*     */     }
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 334 */       super.func_145845_h();
/*     */       
/* 336 */       if ((!this.field_145850_b.field_72995_K) && (TimeUtil.secondsElapsed(1, this.ticks))) {
/* 337 */         InfusedSpiritEffect effect = InfusedSpiritEffect.getEffect(this);
/* 338 */         if ((effect != null) && (effect.getRadius() > 0.0D)) {
/* 339 */           boolean someFound = false;
/* 340 */           int found = 0;
/* 341 */           int someLeft = 0;
/* 342 */           HashSet<String> foundTypes = new HashSet();
/* 343 */           List entities = null;
/* 344 */           ArrayList<EntityLivingBase> foundEntities = new ArrayList();
/* 345 */           if (this.alarmMode != 5) {
/* 346 */             double RADIUS = effect.getRadius();
/* 347 */             double RADIUS_SQ = RADIUS * RADIUS;
/* 348 */             AxisAlignedBB bb = AxisAlignedBB.func_72330_a(0.5D + this.field_145851_c - RADIUS, 0.5D + this.field_145848_d - RADIUS, 0.5D + this.field_145849_e - RADIUS, 0.5D + this.field_145851_c + RADIUS, 0.5D + this.field_145848_d + RADIUS, 0.5D + this.field_145849_e + RADIUS);
/*     */             
/* 350 */             if ((this.alarmMode == 0) || (this.alarmMode == 1)) {
/* 351 */               entities = this.field_145850_b.func_72872_a(EntityPlayer.class, bb);
/*     */             } else {
/* 353 */               entities = this.field_145850_b.func_72872_a(EntityLivingBase.class, bb);
/*     */             }
/* 355 */             someLeft = entities.size();
/* 356 */             for (Object obj : entities) {
/* 357 */               if ((obj instanceof EntityPlayer)) {
/* 358 */                 EntityPlayer player = (EntityPlayer)obj;
/* 359 */                 if ((this.knownPlayers != null) && (this.knownPlayers.contains(player.func_70005_c_()))) {
/* 360 */                   someFound = true;
/* 361 */                   found++;
/* 362 */                   someLeft--;
/* 363 */                   if (this.alarmMode == 1) {
/* 364 */                     foundEntities.add(player);
/*     */                   }
/* 366 */                 } else if ((this.alarmMode == 2) || (this.alarmMode == 0)) {
/* 367 */                   foundEntities.add(player);
/*     */                 }
/* 369 */               } else if (((obj instanceof EntityLiving)) && (!isIgnorableEntity((EntityLiving)obj))) {
/* 370 */                 EntityLiving creature = (EntityLiving)obj;
/* 371 */                 if ((this.knownCreatureTypes != null) && (this.knownCreatureTypes.contains(creature.func_70005_c_()))) {
/* 372 */                   someFound = true;
/* 373 */                   foundTypes.add(creature.func_70005_c_());
/* 374 */                   someLeft--;
/*     */                 } else {
/* 376 */                   this.testID.id = creature.func_110124_au();
/* 377 */                   if ((this.knownCreatures != null) && (this.knownCreatures.contains(this.testID))) {
/* 378 */                     someFound = true;
/* 379 */                     found++;
/* 380 */                     someLeft--;
/* 381 */                   } else if (this.alarmMode == 2) {
/* 382 */                     foundEntities.add(creature);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 389 */           boolean raiseAlarm = false;
/* 390 */           switch (this.alarmMode) {
/*     */           case 0: 
/*     */           case 2: 
/* 393 */             raiseAlarm = someLeft > 0;
/* 394 */             break;
/*     */           case 1: 
/* 396 */             raiseAlarm = someFound;
/* 397 */             break;
/*     */           case 3: 
/* 399 */             raiseAlarm = (found != this.knownCreatures.size() + this.knownPlayers.size()) || (this.knownCreatureTypes.size() != foundTypes.size());
/* 400 */             break;
/*     */           case 4: 
/* 402 */             raiseAlarm = !someFound;
/*     */           }
/*     */           
/*     */           
/* 406 */           int cooldown = effect.getCooldownTicks();
/* 407 */           long currentTime = this.field_145850_b.func_82737_E();
/* 408 */           if (((cooldown == -1) || (currentTime > this.lastActivationTime + cooldown)) && 
/* 409 */             (effect.doUpdateEffect(this, raiseAlarm, foundEntities))) {
/* 410 */             this.lastActivationTime = currentTime;
/*     */           }
/*     */           
/*     */ 
/* 414 */           if (this.lastRaiseAlarm != raiseAlarm) {
/* 415 */             this.lastRaiseAlarm = raiseAlarm;
/* 416 */             if (effect.isRedstoneSignaller()) {
/* 417 */               BlockUtil.notifyNeighborsOfBlockChange(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private boolean isFamiliar(Entity entity) {
/* 425 */       if ((entity instanceof IFamiliar)) {
/* 426 */         IFamiliar familiar = (IFamiliar)entity;
/* 427 */         return familiar.isFamiliar();
/*     */       }
/* 429 */       return false;
/*     */     }
/*     */     
/*     */     private boolean isIgnorableEntity(EntityLiving entity) {
/* 433 */       return ((entity instanceof EntityCorpse)) || ((entity instanceof EntityIllusion)) || ((entity instanceof com.emoniph.witchery.entity.EntitySpirit)) || (isFamiliar(entity));
/*     */     }
/*     */     
/*     */ 
/*     */     public boolean isSpectral()
/*     */     {
/* 439 */       return this.spectral;
/*     */     }
/*     */     
/*     */     public void setSpectral(boolean spectral) {
/* 443 */       this.spectral = spectral;
/* 444 */       if ((this.field_145850_b != null) && (!this.field_145850_b.field_72995_K)) {
/* 445 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public int getPowerLevel() {
/* 450 */       InfusedSpiritEffect effect = InfusedSpiritEffect.getEffect(this);
/* 451 */       if ((effect != null) && (effect.isRedstoneSignaller())) {
/* 452 */         return this.lastRaiseAlarm ? 15 : 0;
/*     */       }
/* 454 */       return 0;
/*     */     }
/*     */     
/*     */ 
/* 458 */     private int color = 0;
/*     */     
/*     */     public void setColor(int dyeColor) {
/* 461 */       this.color = dyeColor;
/* 462 */       if ((this.field_145850_b != null) && (!this.field_145850_b.field_72995_K)) {
/* 463 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 464 */         syncSpectralEntities();
/*     */       }
/*     */     }
/*     */     
/*     */     public int getColor() {
/* 469 */       return this.color;
/*     */     }
/*     */     
/* 472 */     private int effectType = 0;
/*     */     
/*     */     public int getEffectType() {
/* 475 */       return this.effectType;
/*     */     }
/*     */     
/*     */     public void setEffectType(int effectID) {
/* 479 */       this.effectType = effectID;
/* 480 */       if ((this.field_145850_b != null) && (!this.field_145850_b.field_72995_K)) {
/* 481 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public int getX() {
/* 486 */       return this.field_145851_c;
/*     */     }
/*     */     
/*     */     public int getY() {
/* 490 */       return this.field_145848_d;
/*     */     }
/*     */     
/*     */     public int getZ() {
/* 494 */       return this.field_145849_e;
/*     */     }
/*     */     
/*     */     public void syncSpectralEntities() {
/* 498 */       if ((this.field_145850_b != null) && (!this.field_145850_b.field_72995_K) && (this.field_145850_b.field_73011_w.field_76574_g == Config.instance().dimensionDreamID)) {
/* 499 */         World overworld = MinecraftServer.func_71276_C().func_71218_a(0);
/* 500 */         if ((overworld != null) && (BlockUtil.getBlock(overworld, this.field_145851_c, this.field_145848_d, this.field_145849_e) == func_145838_q())) {
/* 501 */           TileEntityFetish tile = (TileEntityFetish)BlockUtil.getTileEntity(overworld, this.field_145851_c, this.field_145848_d, this.field_145849_e, TileEntityFetish.class);
/* 502 */           if (tile != null) {
/* 503 */             NBTTagCompound nbtOurData = new NBTTagCompound();
/* 504 */             writeSubDataToNBT(nbtOurData);
/* 505 */             tile.readSubDataFromNBT(nbtOurData);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 513 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 514 */       func_145841_b(nbtTag);
/* 515 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 520 */       super.onDataPacket(net, packet);
/* 521 */       func_145839_a(packet.func_148857_g());
/* 522 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 527 */       super.func_145839_a(nbtRoot);
/* 528 */       this.lastActivationTime = nbtRoot.func_74763_f("LastActivation");
/* 529 */       if (nbtRoot.func_74764_b("Spectral")) {
/* 530 */         this.spectral = nbtRoot.func_74767_n("Spectral");
/*     */       }
/* 532 */       readSubDataFromNBT(nbtRoot);
/*     */     }
/*     */     
/*     */     public void readSubDataFromNBT(NBTTagCompound nbtRoot) {
/* 536 */       if (nbtRoot.func_74764_b("BlockColor")) {
/* 537 */         this.color = nbtRoot.func_74771_c("BlockColor");
/*     */       }
/*     */       
/* 540 */       if (nbtRoot.func_74764_b("EffectTypeID")) {
/* 541 */         this.effectType = nbtRoot.func_74762_e("EffectTypeID");
/*     */       }
/*     */       
/* 544 */       if (nbtRoot.func_74764_b("AlarmMode")) {
/* 545 */         this.alarmMode = nbtRoot.func_74762_e("AlarmMode");
/*     */       } else {
/* 547 */         this.alarmMode = 5;
/*     */       }
/*     */       
/* 550 */       if (nbtRoot.func_74764_b("KnownPlayers")) {
/* 551 */         NBTTagList nbtPlayers = nbtRoot.func_150295_c("KnownPlayers", 10);
/* 552 */         this.knownPlayers = new ArrayList();
/* 553 */         for (int i = 0; i < nbtPlayers.func_74745_c(); i++) {
/* 554 */           NBTTagCompound nbtKnownPlayer = nbtPlayers.func_150305_b(i);
/* 555 */           String playerName = nbtKnownPlayer.func_74779_i("PlayerName");
/* 556 */           if ((playerName != null) && (!playerName.isEmpty())) {
/* 557 */             this.knownPlayers.add(playerName);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 562 */       if (nbtRoot.func_74764_b("KnownCreatureTypes")) {
/* 563 */         NBTTagList nbtCreatureTypes = nbtRoot.func_150295_c("KnownCreatureTypes", 10);
/* 564 */         this.knownCreatureTypes = new ArrayList();
/* 565 */         for (int i = 0; i < nbtCreatureTypes.func_74745_c(); i++) {
/* 566 */           NBTTagCompound nbtKnownCreatureType = nbtCreatureTypes.func_150305_b(i);
/* 567 */           String typeName = nbtKnownCreatureType.func_74779_i("CreatureTypeName");
/* 568 */           if ((typeName != null) && (!typeName.isEmpty())) {
/* 569 */             this.knownCreatureTypes.add(typeName);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 574 */       if (nbtRoot.func_74764_b("KnownCreatures")) {
/* 575 */         NBTTagList nbtCreatures = nbtRoot.func_150295_c("KnownCreatures", 10);
/* 576 */         this.knownCreatures = new ArrayList();
/* 577 */         for (int i = 0; i < nbtCreatures.func_74745_c(); i++) {
/* 578 */           NBTTagCompound nbtKnownCreature = nbtCreatures.func_150305_b(i);
/* 579 */           String playerName = nbtKnownCreature.func_74779_i("PlayerName");
/* 580 */           long uuidMost = nbtKnownCreature.func_74763_f("CreatureMost");
/* 581 */           long uuidLeast = nbtKnownCreature.func_74763_f("CreatureLeast");
/* 582 */           String cname = nbtKnownCreature.func_74779_i("CreatureName");
/* 583 */           if ((uuidMost != 0L) || (uuidLeast != 0L)) {
/* 584 */             UUID creatureID = new UUID(uuidMost, uuidLeast);
/* 585 */             this.knownCreatures.add(new BlockFetish.CreatureID(creatureID, cname));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 593 */       super.func_145841_b(nbtRoot);
/* 594 */       nbtRoot.func_74772_a("LastActivation", this.lastActivationTime);
/* 595 */       nbtRoot.func_74757_a("Spectral", this.spectral);
/* 596 */       writeSubDataToNBT(nbtRoot);
/*     */     }
/*     */     
/*     */     public void writeSubDataToNBT(NBTTagCompound nbtRoot) {
/* 600 */       nbtRoot.func_74774_a("BlockColor", (byte)this.color);
/* 601 */       nbtRoot.func_74768_a("EffectTypeID", this.effectType);
/* 602 */       nbtRoot.func_74768_a("AlarmMode", this.alarmMode);
/* 603 */       if (this.knownPlayers.size() > 0) {
/* 604 */         NBTTagList nbtPlayers = new NBTTagList();
/* 605 */         for (String playerName : this.knownPlayers) {
/* 606 */           NBTTagCompound nbtKnownPlayer = new NBTTagCompound();
/* 607 */           nbtKnownPlayer.func_74778_a("PlayerName", playerName);
/* 608 */           nbtPlayers.func_74742_a(nbtKnownPlayer);
/*     */         }
/* 610 */         nbtRoot.func_74782_a("KnownPlayers", nbtPlayers);
/*     */       }
/*     */       
/* 613 */       if (this.knownCreatureTypes.size() > 0) {
/* 614 */         NBTTagList nbtCreatureTypes = new NBTTagList();
/* 615 */         for (String typeName : this.knownCreatureTypes) {
/* 616 */           NBTTagCompound nbtKnownCreatureType = new NBTTagCompound();
/* 617 */           nbtKnownCreatureType.func_74778_a("CreatureTypeName", typeName);
/* 618 */           nbtCreatureTypes.func_74742_a(nbtKnownCreatureType);
/*     */         }
/* 620 */         nbtRoot.func_74782_a("KnownCreatureTypes", nbtCreatureTypes);
/*     */       }
/*     */       
/* 623 */       if (this.knownCreatures.size() > 0) {
/* 624 */         NBTTagList nbtCreatures = new NBTTagList();
/* 625 */         for (BlockFetish.CreatureID creatureID : this.knownCreatures) {
/* 626 */           NBTTagCompound nbtKnownCreature = new NBTTagCompound();
/* 627 */           nbtKnownCreature.func_74772_a("CreatureMost", creatureID.id.getMostSignificantBits());
/* 628 */           nbtKnownCreature.func_74772_a("CreatureLeast", creatureID.id.getLeastSignificantBits());
/* 629 */           nbtKnownCreature.func_74778_a("CreatureName", creatureID.name);
/* 630 */           nbtCreatures.func_74742_a(nbtKnownCreature);
/*     */         }
/* 632 */         nbtRoot.func_74782_a("KnownCreatures", nbtCreatures);
/*     */       }
/*     */     }
/*     */     
/* 636 */     private static ArrayList<String> groupables = null;
/* 637 */     private ArrayList<String> knownPlayers = new ArrayList();
/* 638 */     private ArrayList<String> knownCreatureTypes = new ArrayList();
/* 639 */     private ArrayList<BlockFetish.CreatureID> knownCreatures = new ArrayList();
/*     */     
/*     */     private boolean isGroupableCreature(UUID otherCreature, String creatureName) {
/* 642 */       if (groupables == null) {
/* 643 */         groupables = new ArrayList();
/* 644 */         addGroupableType(EntityVillager.class);
/* 645 */         addGroupableType(EntityGoblin.class);
/* 646 */         addGroupableType(net.minecraft.entity.passive.EntitySheep.class);
/* 647 */         addGroupableType(EntityCow.class);
/* 648 */         addGroupableType(net.minecraft.entity.passive.EntityMooshroom.class);
/* 649 */         addGroupableType(EntityChicken.class);
/* 650 */         addGroupableType(EntityPig.class);
/* 651 */         addGroupableType(EntityHorse.class);
/* 652 */         addGroupableType(EntityBat.class);
/* 653 */         addGroupableType(EntitySquid.class);
/* 654 */         addGroupableType(com.emoniph.witchery.entity.EntityCovenWitch.class);
/*     */       }
/* 656 */       return groupables.contains(creatureName);
/*     */     }
/*     */     
/*     */     public void setBoundEntity(ItemStack stack, EntityPlayer player, boolean readonly) {
/* 660 */       if ((!this.field_145850_b.field_72995_K) && 
/* 661 */         (stack != null)) {
/* 662 */         ItemTaglockKit.BoundType boundEntityType = Witchery.Items.TAGLOCK_KIT.getBoundEntityType(stack, Integer.valueOf(1));
/* 663 */         switch (BlockFetish.1.$SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType[boundEntityType.ordinal()]) {
/*     */         case 1: 
/* 665 */           if (!readonly) {
/* 666 */             String otherUsername = Witchery.Items.TAGLOCK_KIT.getBoundUsername(stack, Integer.valueOf(1));
/*     */             
/* 668 */             if (!this.knownPlayers.contains(otherUsername)) {
/* 669 */               this.knownPlayers.add(otherUsername);
/*     */             } else {
/* 671 */               this.knownPlayers.remove(otherUsername);
/*     */             }
/* 673 */             if (!player.field_71075_bZ.field_75098_d) if (--stack.field_77994_a <= 0) {
/* 674 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */               }
/* 676 */             if ((player instanceof EntityPlayerMP)) {
/* 677 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */             
/* 680 */             syncSpectralEntities();
/*     */           }
/* 682 */           showCurrentKnownEntities(player);
/* 683 */           break;
/*     */         case 2: 
/* 685 */           if (!readonly) {
/* 686 */             UUID otherCreature = Witchery.Items.TAGLOCK_KIT.getBoundCreatureID(stack, Integer.valueOf(1));
/* 687 */             String creatureName = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
/*     */             
/* 689 */             if (isGroupableCreature(otherCreature, creatureName)) {
/* 690 */               if (!this.knownCreatureTypes.contains(creatureName)) {
/* 691 */                 this.knownCreatureTypes.add(creatureName);
/*     */               } else {
/* 693 */                 this.knownCreatureTypes.remove(creatureName);
/*     */               }
/*     */             } else {
/* 696 */               BlockFetish.CreatureID creatureID = new BlockFetish.CreatureID(otherCreature, creatureName);
/* 697 */               if (!this.knownCreatures.contains(creatureID)) {
/* 698 */                 this.knownCreatures.add(creatureID);
/*     */               } else {
/* 700 */                 this.knownCreatures.remove(creatureID);
/*     */               }
/*     */             }
/*     */             
/* 704 */             if (!player.field_71075_bZ.field_75098_d) if (--stack.field_77994_a <= 0) {
/* 705 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */               }
/* 707 */             if ((player instanceof EntityPlayerMP)) {
/* 708 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */             
/* 711 */             syncSpectralEntities();
/*     */           }
/* 713 */           showCurrentKnownEntities(player);
/* 714 */           break;
/*     */         case 3: 
/* 716 */           showCurrentKnownEntities(player);
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     
/*     */     public void clearBoundEntities(ItemStack stack, EntityPlayer player)
/*     */     {
/* 724 */       if ((player != null) && (!player.field_70170_p.field_72995_K) && (stack != null)) {
/* 725 */         this.knownCreatureTypes.clear();
/* 726 */         this.knownCreatures.clear();
/* 727 */         this.knownPlayers.clear();
/*     */         
/* 729 */         if (!player.field_71075_bZ.field_75098_d) {
/* 730 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, new ItemStack(Items.field_151133_ar));
/*     */         }
/*     */         
/* 733 */         if ((player instanceof EntityPlayerMP)) {
/* 734 */           ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */         }
/*     */         
/* 737 */         syncSpectralEntities();
/*     */         
/* 739 */         showCurrentKnownEntities(player);
/*     */       }
/*     */     }
/*     */     
/*     */     public void cycleBoundMode(EntityPlayer player) {
/* 744 */       if (!this.field_145850_b.field_72995_K) {
/* 745 */         if (++this.alarmMode > 5) {
/* 746 */           this.alarmMode = 0;
/*     */         }
/*     */         
/* 749 */         syncSpectralEntities();
/*     */         
/* 751 */         showCurrentKnownEntities(player);
/*     */       }
/*     */     }
/*     */     
/*     */     private void addGroupableType(Class<? extends EntityLiving> className) {
/* 756 */       String name = (String)EntityList.field_75626_c.get(className);
/* 757 */       if (name != null) {
/* 758 */         String localName = net.minecraft.util.StatCollector.func_74838_a("entity." + name + ".name");
/* 759 */         groupables.add(localName);
/*     */       }
/*     */     }
/*     */     
/*     */     private void showCurrentKnownEntities(EntityPlayer player) {
/* 764 */       StringBuffer sb = new StringBuffer();
/*     */       
/* 766 */       for (String s : this.knownPlayers) {
/* 767 */         if (sb.length() > 0) {
/* 768 */           sb.append(", ");
/*     */         }
/* 770 */         sb.append(s);
/*     */       }
/*     */       
/* 773 */       for (String s : this.knownCreatureTypes) {
/* 774 */         if (sb.length() > 0) {
/* 775 */           sb.append(", ");
/*     */         }
/* 777 */         sb.append("#");
/* 778 */         sb.append(s);
/*     */       }
/*     */       
/* 781 */       for (BlockFetish.CreatureID cid : this.knownCreatures) {
/* 782 */         if (sb.length() > 0) {
/* 783 */           sb.append(", ");
/*     */         }
/* 785 */         sb.append(cid.toString());
/*     */       }
/*     */       
/* 788 */       String message = sb.toString();
/* 789 */       String key = "";
/* 790 */       switch (this.alarmMode) {
/*     */       case 0: 
/* 792 */         key = "tile.witchery.scarecrow.operation.playerwhitelist";
/* 793 */         break;
/*     */       case 1: 
/* 795 */         key = "tile.witchery.scarecrow.operation.playerblacklist";
/* 796 */         break;
/*     */       case 2: 
/* 798 */         key = "tile.witchery.scarecrow.operation.creaturewhitelist";
/* 799 */         break;
/*     */       case 3: 
/* 801 */         key = "tile.witchery.scarecrow.operation.allnotfound";
/* 802 */         break;
/*     */       case 4: 
/* 804 */         key = "tile.witchery.scarecrow.operation.onenotfound";
/* 805 */         break;
/*     */       case 5: 
/* 807 */         key = "tile.witchery.scarecrow.operation.off";
/*     */       }
/*     */       
/*     */       
/* 811 */       ChatUtil.sendTranslated(player, key, new Object[] { message });
/*     */     }
/*     */   }
/*     */   
/*     */   private static class CreatureID {
/*     */     UUID id;
/*     */     String name;
/*     */     
/*     */     public CreatureID(UUID id, String name) {
/* 820 */       this.id = id;
/* 821 */       this.name = name;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj)
/*     */     {
/* 826 */       if (obj == null) {
/* 827 */         return false;
/*     */       }
/*     */       
/* 830 */       if (obj == this) {
/* 831 */         return true;
/*     */       }
/*     */       
/* 834 */       if ((obj instanceof UUID)) {
/* 835 */         return this.id.equals((UUID)obj);
/*     */       }
/*     */       
/* 838 */       if (obj.getClass() == getClass()) {
/* 839 */         return this.id.equals(((CreatureID)obj).id);
/*     */       }
/*     */       
/* 842 */       return false;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/* 847 */       return this.name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockFetish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */