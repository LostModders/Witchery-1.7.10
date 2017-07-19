/*      */ package com.emoniph.witchery.dimension;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.blocks.BlockFetish.ClassItemBlock;
/*      */ import com.emoniph.witchery.entity.EntityCorpse;
/*      */ import com.emoniph.witchery.entity.EntityDemon;
/*      */ import com.emoniph.witchery.entity.EntityNightmare;
/*      */ import com.emoniph.witchery.infusion.Infusion;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.network.PacketPlayerStyle;
/*      */ import com.emoniph.witchery.network.PacketPushTarget;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.Log;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*      */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.chunk.IChunkProvider;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ 
/*      */ public class WorldProviderDreamWorld extends WorldProvider
/*      */ {
/*      */   public void setDimension(int dim)
/*      */   {
/*   57 */     this.field_76574_g = dim;
/*   58 */     super.setDimension(dim);
/*      */   }
/*      */   
/*      */   public long getSeed()
/*      */   {
/*   63 */     Long seed = Long.valueOf(super.getSeed());
/*   64 */     return seed.longValue();
/*      */   }
/*      */   
/*      */   public IChunkProvider func_76555_c()
/*      */   {
/*   69 */     WorldProvider overworldProvider = net.minecraftforge.common.DimensionManager.getProvider(0);
/*   70 */     return overworldProvider.field_76577_b.getChunkGenerator(this.field_76579_a, this.field_76579_a.func_72912_H().func_82571_y());
/*      */   }
/*      */   
/*      */   public void func_76572_b()
/*      */   {
/*   75 */     super.func_76572_b();
/*   76 */     this.field_76574_g = Config.instance().dimensionDreamID;
/*      */   }
/*      */   
/*      */   public String getWelcomeMessage()
/*      */   {
/*   81 */     if ((this instanceof WorldProviderDreamWorld)) {
/*   82 */       return "Entering the " + func_80007_l();
/*      */     }
/*   84 */     return null;
/*      */   }
/*      */   
/*      */   public String getDepartMessage()
/*      */   {
/*   89 */     if ((this instanceof WorldProviderDreamWorld)) {
/*   90 */       return "Leaving the " + func_80007_l();
/*      */     }
/*   92 */     return null;
/*      */   }
/*      */   
/*      */   public String func_80007_l()
/*      */   {
/*   97 */     return "Spirit World";
/*      */   }
/*      */   
/*      */   public float getStarBrightness(float par1)
/*      */   {
/*  102 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public boolean func_76567_e()
/*      */   {
/*  107 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getMovementFactor()
/*      */   {
/*  113 */     return 1.0D;
/*      */   }
/*      */   
/*  116 */   int nightmare = 0;
/*      */   private static final String SPIRIT_WORLD_KEY = "WITCSpiritWorld";
/*      */   private static final String SPIRIT_WORLD_WALKING_KEY = "WITCSpiritWalking";
/*      */   
/*  120 */   public float func_76563_a(long par1, float par3) { return this.nightmare > 0 ? 0.5F : 1.0F; }
/*      */   
/*      */ 
/*      */   public float func_76571_f()
/*      */   {
/*  125 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public boolean func_76566_a(int par1, int par2)
/*      */   {
/*  130 */     int var3 = this.field_76579_a.func_72825_h(par1, par2);
/*  131 */     return var3 != -1;
/*      */   }
/*      */   
/*      */   public ChunkCoordinates func_76554_h()
/*      */   {
/*  136 */     return new ChunkCoordinates(100, 50, 0);
/*      */   }
/*      */   
/*      */   public int func_76557_i()
/*      */   {
/*  141 */     return 64;
/*      */   }
/*      */   
/*      */   public double getHorizon()
/*      */   {
/*  146 */     return 0.0D;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public boolean hasVoidParticles(boolean var1) {
/*  151 */     return false;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public boolean func_76561_g()
/*      */   {
/*  157 */     return true;
/*      */   }
/*      */   
/*      */   public double func_76565_k()
/*      */   {
/*  162 */     return 1.0D;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public Vec3 func_76562_b(float par1, float par2)
/*      */   {
/*  168 */     float var3 = MathHelper.func_76134_b(par1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*      */     
/*  170 */     if (var3 < 0.0F) {
/*  171 */       var3 = 0.0F;
/*      */     }
/*      */     
/*  174 */     if (var3 > 1.0F)
/*  175 */       var3 = 1.0F;
/*      */     float var6;
/*      */     float var4;
/*      */     float var5;
/*  179 */     float var6; if (this.nightmare == 0) {
/*  180 */       float var4 = 0.8F;
/*  181 */       float var5 = 0.2F;
/*  182 */       var6 = 0.6F; } else { float var6;
/*  183 */       if (this.nightmare == 1) {
/*  184 */         float var4 = 0.0F;
/*  185 */         float var5 = 1.0F;
/*  186 */         var6 = 0.0F;
/*      */       } else {
/*  188 */         var4 = 1.0F;
/*  189 */         var5 = 0.0F;
/*  190 */         var6 = 0.0F;
/*      */       } }
/*  192 */     var4 *= (var3 * 0.94F + 0.06F);
/*  193 */     var5 *= (var3 * 0.94F + 0.06F);
/*  194 */     var6 *= (var3 * 0.91F + 0.09F);
/*  195 */     return Vec3.func_72443_a(var4, var5, var6);
/*      */   }
/*      */   
/*      */   public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
/*      */   {
/*  200 */     allowPeaceful = true;
/*      */   }
/*      */   
/*      */ 
/*      */   private static final String SPIRIT_WORLD_NIGHTMARE_KEY = "Nightmare";
/*      */   private static final String SPIRIT_WORLD_DEMONIC_KEY = "Demonic";
/*      */   private static final String SPIRIT_WORLD_OVERWORLD_BODY_KEY = "OverworldBody";
/*      */   private static final String SPIRIT_WORLD_OVERWORLD_HEALTH_KEY = "OverworldHealth";
/*      */   private static final String SPIRIT_WORLD_SPIRIT_HEALTH_KEY = "SpiritHealth";
/*      */   private static final String SPIRIT_WORLD_OVERWORLD_HUNGER_FOOD_KEY = "OverworldHunger";
/*      */   private static final String SPIRIT_WORLD_SPIRIT_HUNGER_FOOD_KEY = "SpiritHunger";
/*      */   private static final String SPIRIT_WORLD_OVERWORLD_INVENTORY_KEY = "OverworldInventory";
/*      */   private static final String SPIRIT_WORLD_SPIRIT_INVENTORY_KEY = "SpiritInventory";
/*      */   private static final String SPIRIT_WORLD_MANIFEST_GHOST_KEY = "WITCManifested";
/*      */   public static final String SPIRIT_WORLD_MANIFEST_TIME_KEY = "WITCManifestDuration";
/*      */   public static final String SPIRIT_WORLD_AWAKEN_PLAYER_KEY = "WITCForceAwaken";
/*      */   private static final String SPIRIT_WORLD_LAST_NIGHTMARE_KILL_KEY = "LastNightmareKillTime";
/*      */   public static final String SPIRIT_WORLD_MANIFEST_SKIP_TIME_TICK_KEY = "WITCManifestSkipTick";
/*      */   public void updateWeather()
/*      */   {
/*  220 */     if ((this.field_76579_a != null) && (this.field_76579_a.field_73012_v.nextInt(20) == 0)) {
/*  221 */       int playerHasNightmare = 0;
/*  222 */       for (Object obj : this.field_76579_a.field_73010_i) {
/*  223 */         EntityPlayer player = (EntityPlayer)obj;
/*  224 */         int level = getPlayerHasNightmare(player);
/*  225 */         if (level > playerHasNightmare) {
/*  226 */           playerHasNightmare = level;
/*  227 */           break;
/*      */         }
/*      */       }
/*  230 */       if (this.nightmare != playerHasNightmare) {
/*  231 */         this.nightmare = playerHasNightmare;
/*      */       }
/*      */     }
/*  234 */     super.updateWeather();
/*      */   }
/*      */   
/*      */   public boolean isNightmare() {
/*  238 */     return this.nightmare > 0;
/*      */   }
/*      */   
/*      */   public boolean isDemonicNightmare() {
/*  242 */     return this.nightmare > 1;
/*      */   }
/*      */   
/*      */   public static int getPlayerHasNightmare(EntityPlayer player) {
/*  246 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  247 */     return getPlayerHasNightmare(nbtPlayer);
/*      */   }
/*      */   
/*      */   public static int getPlayerHasNightmare(NBTTagCompound nbtPlayer) {
/*  251 */     if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  252 */       return 0;
/*      */     }
/*  254 */     NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*  255 */     boolean nightmare = nbtSpirit.func_74767_n("Nightmare");
/*  256 */     boolean demonic = nbtSpirit.func_74767_n("Demonic");
/*  257 */     return nightmare ? 1 : (nightmare) && (demonic) ? 2 : 0;
/*      */   }
/*      */   
/*      */   public static void setPlayerHasNightmare(EntityPlayer player, boolean nightmare, boolean demonic) {
/*  261 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  262 */     setPlayerHasNightmare(nbtPlayer, nightmare, demonic);
/*      */   }
/*      */   
/*      */   public static void setPlayerHasNightmare(NBTTagCompound nbtPlayer, boolean nightmare, boolean demonic) {
/*  266 */     if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  267 */       nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
/*      */     }
/*  269 */     NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*  270 */     nbtSpirit.func_74757_a("Nightmare", nightmare);
/*  271 */     nbtSpirit.func_74757_a("Demonic", demonic);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void setPlayerLastNightmareKillNow(EntityPlayer player)
/*      */   {
/*  277 */     if (player != null) {
/*  278 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  279 */       long time = MinecraftServer.func_130071_aq();
/*  280 */       setPlayerLastNightmareKill(nbtPlayer, time);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void setPlayerLastNightmareKill(NBTTagCompound nbtPlayer, long time) {
/*  285 */     if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  286 */       nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
/*      */     }
/*  288 */     NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*  289 */     nbtSpirit.func_74772_a("LastNightmareKillTime", time);
/*      */   }
/*      */   
/*      */   public static long getPlayerLastNightmareKill(NBTTagCompound nbtPlayer) {
/*  293 */     if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  294 */       return 0L;
/*      */     }
/*  296 */     NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*  297 */     if (!nbtSpirit.func_74764_b("LastNightmareKillTime")) {
/*  298 */       return 0L;
/*      */     }
/*  300 */     long time = nbtSpirit.func_74763_f("LastNightmareKillTime");
/*  301 */     return time;
/*      */   }
/*      */   
/*      */   public static boolean getPlayerIsSpiritWalking(EntityPlayer player) {
/*  305 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  306 */     return getPlayerIsSpiritWalking(nbtPlayer);
/*      */   }
/*      */   
/*      */   public static boolean getPlayerIsSpiritWalking(NBTTagCompound nbtPlayer) {
/*  310 */     boolean walking = nbtPlayer.func_74767_n("WITCSpiritWalking");
/*  311 */     return walking;
/*      */   }
/*      */   
/*      */   public static void setPlayerIsSpiritWalking(EntityPlayer player, boolean walking) {
/*  315 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  316 */     setPlayerIsSpiritWalking(nbtPlayer, walking);
/*      */   }
/*      */   
/*      */   public static void setPlayerIsSpiritWalking(NBTTagCompound nbtPlayer, boolean walking) {
/*  320 */     nbtPlayer.func_74757_a("WITCSpiritWalking", walking);
/*      */   }
/*      */   
/*      */   private static void addItemToInventory(EntityPlayer player, ItemStack protoStack, int totalQuantity) {
/*  324 */     if (totalQuantity > 0) {
/*  325 */       int itemsRemaining = totalQuantity;
/*  326 */       int maxStack = protoStack.func_77976_d();
/*  327 */       while (itemsRemaining > 0) {
/*  328 */         int quantity = itemsRemaining > maxStack ? maxStack : itemsRemaining;
/*  329 */         itemsRemaining -= quantity;
/*  330 */         ItemStack newStack = new ItemStack(protoStack.func_77973_b(), quantity, protoStack.func_77960_j());
/*  331 */         player.field_71071_by.func_70441_a(newStack);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static void addItemToInventory(EntityPlayer player, ArrayList<ItemStack> stacks) {
/*  337 */     for (ItemStack stack : stacks) {
/*  338 */       if (!player.field_71071_by.func_70441_a(stack)) {
/*  339 */         player.field_70170_p.func_72838_d(new EntityItem(player.field_70170_p, player.field_70165_t, 0.5D + player.field_70163_u, player.field_70161_v, stack));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static void sendPlayerToSpiritWorld(EntityPlayer player, double nightmareChance) {
/*  345 */     if ((player != null) && (!player.field_70170_p.field_72995_K)) {
/*  346 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  347 */       if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  348 */         nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
/*      */       }
/*  350 */       NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*      */       
/*      */ 
/*  353 */       Coord posBody = new Coord(player);
/*  354 */       posBody.setNBT(nbtSpirit, "OverworldBody");
/*      */       
/*  356 */       int fireFound = 0;
/*  357 */       int heartsFound = 0;
/*  358 */       int spiritPoolFound = 0;
/*  359 */       int cottonFound = 0;
/*  360 */       boolean nightmareCatcherFound = false;
/*      */       
/*  362 */       double modifiedNightmareChance = nightmareChance;
/*  363 */       if ((nightmareChance > 0.0D) && (nightmareChance < 1.0D))
/*      */       {
/*  365 */         int R = 8;
/*  366 */         int posX = MathHelper.func_76128_c(player.field_70165_t);
/*  367 */         int posY = MathHelper.func_76128_c(player.field_70163_u);
/*  368 */         int posZ = MathHelper.func_76128_c(player.field_70161_v);
/*  369 */         for (int x = posX - 8; x <= posX + 8; x++) {
/*  370 */           for (int z = posZ - 8; z <= posZ + 8; z++) {
/*  371 */             for (int y = posY - 8; y <= posY + 8; y++) {
/*  372 */               Block block = player.field_70170_p.func_147439_a(x, y, z);
/*  373 */               if ((!nightmareCatcherFound) && (block == Witchery.Blocks.DREAM_CATCHER) && (com.emoniph.witchery.blocks.BlockDreamCatcher.causesNightmares(player.field_70170_p, x, y, z)))
/*      */               {
/*  375 */                 modifiedNightmareChance -= 0.5D;
/*  376 */                 nightmareCatcherFound = true;
/*      */               }
/*      */               
/*  379 */               if ((spiritPoolFound < 3) && (block == Witchery.Blocks.FLOWING_SPIRIT) && (player.field_70170_p.func_72805_g(x, y, z) == 0)) {
/*  380 */                 spiritPoolFound++;
/*  381 */                 modifiedNightmareChance -= 0.1D;
/*      */               }
/*      */               
/*  384 */               if ((cottonFound < 2) && (block == Witchery.Blocks.WISPY_COTTON)) {
/*  385 */                 cottonFound++;
/*  386 */                 modifiedNightmareChance -= 0.1D;
/*      */               }
/*      */               
/*      */ 
/*  390 */               if ((heartsFound < 2) && (block == Witchery.Blocks.DEMON_HEART)) {
/*  391 */                 heartsFound++;
/*  392 */                 modifiedNightmareChance += 0.35D;
/*      */               }
/*  394 */               if ((fireFound < 3) && (block == Blocks.field_150480_ab)) {
/*  395 */                 fireFound++;
/*  396 */                 modifiedNightmareChance += 0.1D;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*  401 */         modifiedNightmareChance = nightmareCatcherFound ? Math.min(Math.max(modifiedNightmareChance, 0.0D), 1.0D) : nightmareChance;
/*      */       }
/*      */       
/*      */ 
/*  405 */       boolean nightmare = (modifiedNightmareChance != 0.0D) && ((modifiedNightmareChance == 1.0D) || (player.field_70170_p.field_73012_v.nextDouble() < modifiedNightmareChance));
/*  406 */       boolean demonic = (nightmare) && (nightmareCatcherFound) && (spiritPoolFound > 0) && (heartsFound > 0) && (player.field_70170_p.field_73012_v.nextDouble() < heartsFound * 0.35D + fireFound * 0.1D);
/*  407 */       setPlayerHasNightmare(nbtPlayer, nightmare, demonic);
/*  408 */       setPlayerIsSpiritWalking(nbtPlayer, true);
/*      */       
/*      */ 
/*  411 */       EntityCorpse corpse = new EntityCorpse(player.field_70170_p);
/*  412 */       corpse.func_70606_j(player.func_110143_aJ());
/*  413 */       corpse.func_94058_c(player.func_70005_c_());
/*  414 */       corpse.setOwner(player.func_70005_c_());
/*  415 */       corpse.func_70012_b(0.5D + MathHelper.func_76128_c(player.field_70165_t), player.field_70163_u, 0.5D + MathHelper.func_76128_c(player.field_70161_v), 0.0F, 0.0F);
/*  416 */       player.field_70170_p.func_72838_d(corpse);
/*      */       
/*      */ 
/*  419 */       int boneNeedles = player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
/*  420 */       int mutandis = player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemMutandis.damageValue);
/*      */       
/*      */ 
/*  423 */       dropBetterBackpacks(player);
/*      */       
/*      */ 
/*  426 */       NBTTagList nbtOverworldInventory = new NBTTagList();
/*  427 */       player.field_71071_by.func_70442_a(nbtOverworldInventory);
/*  428 */       nbtSpirit.func_74782_a("OverworldInventory", nbtOverworldInventory);
/*      */       
/*      */ 
/*  431 */       if (nbtSpirit.func_74764_b("SpiritInventory")) {
/*  432 */         NBTTagList nbtSpiritInventory = nbtSpirit.func_150295_c("SpiritInventory", 10);
/*  433 */         player.field_71071_by.func_70443_b(nbtSpiritInventory);
/*      */         
/*      */ 
/*  436 */         nbtSpirit.func_82580_o("SpiritInventory");
/*      */       } else {
/*  438 */         player.field_71071_by.func_146027_a(null, -1);
/*      */       }
/*      */       
/*      */ 
/*  442 */       addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
/*  443 */       addItemToInventory(player, Witchery.Items.GENERIC.itemMutandis.createStack(), mutandis);
/*      */       
/*      */ 
/*  446 */       nbtSpirit.func_74776_a("OverworldHealth", Math.max(player.func_110143_aJ(), 1.0F));
/*      */       
/*      */ 
/*  449 */       if (nbtSpirit.func_74764_b("SpiritHealth")) {
/*  450 */         float health = Math.max(nbtSpirit.func_74760_g("SpiritHealth"), 10.0F);
/*  451 */         player.func_70606_j(health);
/*      */         
/*      */ 
/*  454 */         nbtSpirit.func_82580_o("SpiritHealth");
/*      */       }
/*      */       
/*      */ 
/*  458 */       NBTTagCompound nbtOverworldFood = new NBTTagCompound();
/*  459 */       player.func_71024_bL().func_75117_b(nbtOverworldFood);
/*  460 */       nbtSpirit.func_74782_a("OverworldHunger", nbtOverworldFood);
/*      */       
/*      */ 
/*  463 */       if (nbtSpirit.func_74764_b("SpiritHunger")) {
/*  464 */         NBTTagCompound nbtSpiritFood = nbtSpirit.func_74775_l("SpiritHunger");
/*  465 */         player.func_71024_bL().func_75112_a(nbtSpiritFood);
/*  466 */         player.func_71024_bL().func_75122_a(16, 0.8F);
/*      */         
/*      */ 
/*  469 */         nbtSpirit.func_82580_o("SpiritHunger");
/*      */       }
/*      */       
/*      */ 
/*  473 */       changeDimension(player, Config.instance().dimensionDreamID);
/*  474 */       findTopAndSetPosition(player.field_70170_p, player);
/*      */       
/*  476 */       Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
/*  477 */       Witchery.packetPipeline.sendTo(new PacketPushTarget(0.0D, 0.1D, 0.0D), player);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void dropBetterBackpacks(EntityPlayer player) {
/*      */     try {
/*  483 */       Class classItemBackpack = Class.forName("net.mcft.copy.betterstorage.item.ItemBackpack");
/*  484 */       Method[] methods = classItemBackpack.getDeclaredMethods();
/*  485 */       if (Config.instance().isDebugging()) {
/*  486 */         for (Method method : methods) {
/*  487 */           Log.instance().debug(method.toString());
/*      */         }
/*      */       }
/*      */       
/*  491 */       Method methodGetPackpack = classItemBackpack.getMethod("getBackpack", new Class[] { EntityLivingBase.class });
/*  492 */       if (methodGetPackpack == null) {
/*  493 */         Log.instance().debug("No getBackpack method found");
/*      */       } else {
/*  495 */         Log.instance().debug("using method: " + methodGetPackpack.toString());
/*      */       }
/*  497 */       ItemStack stackBackpack = (ItemStack)methodGetPackpack.invoke(null, new Object[] { player });
/*  498 */       if (stackBackpack == null) {
/*  499 */         Log.instance().debug("No backpack stack found");
/*      */       } else {
/*  501 */         Log.instance().debug("got backpack stack: " + stackBackpack.toString());
/*      */       }
/*      */       
/*  504 */       Method methodPlaceBackpack = classItemBackpack.getDeclaredMethod("placeBackpack", new Class[] { EntityLivingBase.class, EntityPlayer.class, ItemStack.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, ForgeDirection.class, Boolean.TYPE, Boolean.TYPE });
/*  505 */       if (methodPlaceBackpack == null) {
/*  506 */         Log.instance().debug("No placebackpack method found");
/*      */       } else {
/*  508 */         Log.instance().debug("using method: " + methodPlaceBackpack.toString());
/*      */       }
/*      */       
/*  511 */       World w = player.field_70170_p;
/*  512 */       int x = MathHelper.func_76128_c(player.field_70165_t);
/*  513 */       int y = MathHelper.func_76128_c(player.field_70163_u);
/*  514 */       int z = MathHelper.func_76128_c(player.field_70161_v);
/*  515 */       boolean found = true;
/*  516 */       if (isReplaceable(w, x + 1, y, z)) {
/*  517 */         x++;
/*  518 */       } else if (isReplaceable(w, x - 1, y, z)) {
/*  519 */         x--;
/*  520 */       } else if (isReplaceable(w, x, y, z + 1)) {
/*  521 */         z++;
/*  522 */       } else if (isReplaceable(w, x - 1, y, z - 1)) {
/*  523 */         z--;
/*  524 */       } else if (isReplaceable(w, x + 1, y, z + 1)) {
/*  525 */         x++;
/*  526 */         z++;
/*  527 */       } else if (isReplaceable(w, x - 1, y, z + 1)) {
/*  528 */         x--;
/*  529 */         z++;
/*  530 */       } else if (isReplaceable(w, x + 1, y, z - 1)) {
/*  531 */         x++;
/*  532 */         z--;
/*  533 */       } else if (isReplaceable(w, x - 1, y, z - 1)) {
/*  534 */         x--;
/*  535 */         z--;
/*      */       } else {
/*  537 */         found = false;
/*      */       }
/*  539 */       if (found) {
/*  540 */         if (!w.func_147439_a(x, y - 1, z).func_149662_c()) {
/*  541 */           w.func_147449_b(x, y - 1, z, Blocks.field_150348_b);
/*      */         }
/*      */       } else {
/*  544 */         found = true;
/*  545 */         y++;
/*  546 */         if (isReplaceable(w, x + 1, y, z)) {
/*  547 */           x++;
/*  548 */         } else if (isReplaceable(w, x - 1, y, z)) {
/*  549 */           x--;
/*  550 */         } else if (isReplaceable(w, x, y, z + 1)) {
/*  551 */           z++;
/*  552 */         } else if (isReplaceable(w, x - 1, y, z - 1)) {
/*  553 */           z--;
/*  554 */         } else if (isReplaceable(w, x + 1, y, z + 1)) {
/*  555 */           x++;
/*  556 */           z++;
/*  557 */         } else if (isReplaceable(w, x - 1, y, z + 1)) {
/*  558 */           x--;
/*  559 */           z++;
/*  560 */         } else if (isReplaceable(w, x + 1, y, z - 1)) {
/*  561 */           x++;
/*  562 */           z--;
/*  563 */         } else if (isReplaceable(w, x - 1, y, z - 1)) {
/*  564 */           x--;
/*  565 */           z--;
/*      */         } else {
/*  567 */           found = false;
/*      */         }
/*  569 */         if (!found) {
/*  570 */           x++;
/*  571 */           y++;
/*  572 */           w.func_147468_f(x, y, z);
/*  573 */           if (!w.func_147439_a(x, y - 1, z).func_149662_c()) {
/*  574 */             w.func_147449_b(x, y - 1, z, Blocks.field_150348_b);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  579 */       Boolean result = (Boolean)methodPlaceBackpack.invoke(null, new Object[] { player, player, stackBackpack, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(1), ForgeDirection.NORTH, Boolean.valueOf(false), Boolean.valueOf(false) });
/*  580 */       if (result.equals(Boolean.FALSE)) {
/*  581 */         Log.instance().debug("Backpack could not be placed");
/*      */       } else {
/*  583 */         Method methodSetBackpack = classItemBackpack.getDeclaredMethod("setBackpack", new Class[] { EntityLivingBase.class, ItemStack.class, ItemStack[].class });
/*  584 */         if (methodSetBackpack == null) {
/*  585 */           Log.instance().debug("No setBackpack method found");
/*      */         } else {
/*  587 */           Log.instance().debug("using method: " + methodPlaceBackpack.toString());
/*      */         }
/*  589 */         methodSetBackpack.invoke(null, new Object[] { player, null, null });
/*      */       }
/*      */     } catch (ClassNotFoundException ex) {
/*  592 */       Log.instance().debug("No class found for ItemBackpack");
/*      */     } catch (NoSuchMethodException ex) {
/*  594 */       Log.instance().debug("No onPlaceBackpack method found: " + ex.toString());
/*      */     } catch (InvocationTargetException ex) {
/*  596 */       Log.instance().debug("No onPlaceBackpack target found");
/*      */     } catch (IllegalAccessException ex) {
/*  598 */       Log.instance().debug("No onPlaceBackpack method access allowed");
/*      */     } catch (IndexOutOfBoundsException ex) {
/*  600 */       Log.instance().debug("No onPlaceBackpack method index");
/*      */     } catch (Throwable ex) {
/*  602 */       Log.instance().debug("Unexpected onPlaceBackpack error: " + ex.toString());
/*      */     }
/*      */   }
/*      */   
/*      */   private static boolean isReplaceable(World world, int x, int y, int z) {
/*  607 */     Material m = world.func_147439_a(x, y, z).func_149688_o();
/*  608 */     if (m == null) {
/*  609 */       return false;
/*      */     }
/*  611 */     return m.func_76222_j();
/*      */   }
/*      */   
/*      */   public static void changeDimension(EntityPlayer player, int dimension) {
/*  615 */     dismountEntity(player);
/*  616 */     ItemGeneral.travelToDimension(player, dimension);
/*      */   }
/*      */   
/*      */   private static void dismountEntity(EntityPlayer player) {
/*  620 */     if (player.func_70115_ae()) {
/*  621 */       player.func_70078_a(null);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void findTopAndSetPosition(World world, EntityPlayer player)
/*      */   {
/*  627 */     findTopAndSetPosition(world, player, player.field_70165_t, player.field_70163_u, player.field_70161_v);
/*      */   }
/*      */   
/*      */   private static void findTopAndSetPosition(World world, EntityPlayer player, double posX, double posY, double posZ) {
/*  631 */     int x = MathHelper.func_76128_c(posX);
/*  632 */     int y = MathHelper.func_76128_c(posY);
/*  633 */     int z = MathHelper.func_76128_c(posZ);
/*  634 */     if (!isValidSpawnPoint(world, x, y, z)) {
/*  635 */       for (int i = 1; i <= 256; i++) {
/*  636 */         int yPlus = y + i;
/*  637 */         int yMinus = y - i;
/*  638 */         if ((yPlus < 256) && (isValidSpawnPoint(world, x, yPlus, z))) {
/*  639 */           y = yPlus;
/*      */ 
/*      */ 
/*      */         }
/*  643 */         else if ((yMinus > 2) && (isValidSpawnPoint(world, x, yMinus, z))) {
/*  644 */           y = yMinus;
/*      */         }
/*      */         else
/*      */         {
/*  648 */           if ((yMinus <= 2) && (yPlus >= 255))
/*      */             break;
/*      */         }
/*      */       }
/*      */     }
/*  653 */     player.func_70634_a(0.5D + x, 0.1D + y, 0.5D + z);
/*      */   }
/*      */   
/*      */   private static boolean isValidSpawnPoint(World world, int x, int y, int z) {
/*  657 */     Material materialBelow = world.func_147439_a(x, y - 1, z).func_149688_o();
/*  658 */     return (!world.func_147437_c(x, y - 1, z)) && (materialBelow != Material.field_151587_i) && (world.func_147437_c(x, y, z)) && (world.func_147437_c(x, y + 1, z));
/*      */   }
/*      */   
/*      */   public static void returnPlayerToOverworld(EntityPlayer player) {
/*  662 */     if ((player != null) && (!player.field_70170_p.field_72995_K)) {
/*  663 */       if (player.field_71093_bK != Config.instance().dimensionDreamID) {
/*  664 */         Log.instance().warning("Player " + player.getDisplayName() + " is in incorrect dimension when returning frmo spirit world, dimension=" + player.field_71093_bK);
/*      */       }
/*  666 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  667 */       if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  668 */         nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
/*      */       }
/*  670 */       NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*      */       
/*      */ 
/*  673 */       boolean isSpiritWorld = player.field_71093_bK == Config.instance().dimensionDreamID;
/*  674 */       int cottonRemoved = isSpiritWorld ? player.field_71071_by.func_146027_a(Item.func_150898_a(Witchery.Blocks.WISPY_COTTON), 0) : 0;
/*  675 */       int disturbedCottonRemoved = isSpiritWorld ? player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemDisturbedCotton.damageValue) : 0;
/*  676 */       int hunger = isSpiritWorld ? player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemMellifluousHunger.damageValue) : 0;
/*  677 */       int spirit = isSpiritWorld ? player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.damageValue) : 0;
/*  678 */       int subduedSpirits = player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemSubduedSpirit.damageValue);
/*  679 */       int boneNeedles = player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
/*      */       
/*      */ 
/*      */ 
/*  683 */       dropBetterBackpacks(player);
/*      */       
/*  685 */       if (player.field_71093_bK == Config.instance().dimensionDreamID)
/*      */       {
/*  687 */         NBTTagList nbtSpiritInventory = new NBTTagList();
/*  688 */         player.field_71071_by.func_70442_a(nbtSpiritInventory);
/*  689 */         nbtSpirit.func_74782_a("SpiritInventory", nbtSpiritInventory);
/*      */       }
/*      */       
/*      */ 
/*  693 */       if (nbtSpirit.func_74764_b("OverworldInventory")) {
/*  694 */         NBTTagList nbtOverworldInventory = nbtSpirit.func_150295_c("OverworldInventory", 10);
/*  695 */         player.field_71071_by.func_70443_b(nbtOverworldInventory);
/*      */         
/*      */ 
/*  698 */         nbtSpirit.func_82580_o("OverworldInventory");
/*      */       } else {
/*  700 */         player.field_71071_by.func_146027_a(null, -1);
/*      */       }
/*      */       
/*      */ 
/*  704 */       addItemToInventory(player, new ItemStack(Witchery.Blocks.WISPY_COTTON, 1, 0), cottonRemoved);
/*  705 */       addItemToInventory(player, Witchery.Items.GENERIC.itemDisturbedCotton.createStack(), disturbedCottonRemoved);
/*  706 */       addItemToInventory(player, Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), hunger);
/*  707 */       addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
/*  708 */       addItemToInventory(player, Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(), spirit);
/*  709 */       addItemToInventory(player, Witchery.Items.GENERIC.itemSubduedSpirit.createStack(), subduedSpirits);
/*      */       
/*      */ 
/*  712 */       nbtSpirit.func_74776_a("SpiritHealth", Math.max(player.func_110143_aJ(), 10.0F));
/*      */       
/*      */ 
/*  715 */       if (nbtSpirit.func_74764_b("OverworldHealth")) {
/*  716 */         float health = nbtSpirit.func_74760_g("OverworldHealth");
/*  717 */         player.func_70606_j(health);
/*      */         
/*      */ 
/*  720 */         nbtSpirit.func_82580_o("OverworldHealth");
/*      */       }
/*      */       
/*      */ 
/*  724 */       NBTTagCompound nbtSpiritFood = new NBTTagCompound();
/*  725 */       player.func_71024_bL().func_75117_b(nbtSpiritFood);
/*  726 */       nbtSpirit.func_74782_a("SpiritHunger", nbtSpiritFood);
/*      */       
/*      */ 
/*  729 */       if (nbtSpirit.func_74764_b("OverworldHunger")) {
/*  730 */         NBTTagCompound nbtOverworldFood = nbtSpirit.func_74775_l("OverworldHunger");
/*  731 */         player.func_71024_bL().func_75112_a(nbtOverworldFood);
/*      */         
/*      */ 
/*  734 */         nbtSpirit.func_82580_o("OverworldHunger");
/*      */       }
/*      */       
/*      */ 
/*  738 */       setPlayerHasNightmare(nbtPlayer, false, false);
/*  739 */       setPlayerIsGhost(nbtPlayer, false);
/*  740 */       setPlayerIsSpiritWalking(nbtPlayer, false);
/*      */       
/*  742 */       player.func_70066_B();
/*      */       
/*  744 */       Coord posBody = Coord.createFrom(nbtSpirit, "OverworldBody");
/*      */       
/*      */ 
/*  747 */       if (player.field_71093_bK != 0) {
/*  748 */         if (posBody != null) {
/*  749 */           dismountEntity(player);
/*  750 */           player.func_70634_a(posBody.x, posBody.y, posBody.z);
/*      */         }
/*      */         
/*  753 */         changeDimension(player, 0);
/*      */       }
/*      */       
/*  756 */       World world = player.field_70170_p;
/*  757 */       if (posBody != null) {
/*  758 */         findTopAndSetPosition(player.field_70170_p, player, posBody.x, posBody.y, posBody.z);
/*  759 */         nbtSpirit.func_82580_o("OverworldBody");
/*      */       } else {
/*  761 */         findTopAndSetPosition(player.field_70170_p, player);
/*      */       }
/*      */       
/*      */ 
/*  765 */       for (Object obj : player.field_70170_p.field_72996_f) {
/*  766 */         if ((obj instanceof EntityCorpse)) {
/*  767 */           EntityCorpse corpse = (EntityCorpse)obj;
/*  768 */           String owner = corpse.getOwnerName();
/*  769 */           if ((owner != null) && (owner.equalsIgnoreCase(player.func_70005_c_()))) {
/*  770 */             player.field_70170_p.func_72900_e(corpse);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  775 */       Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
/*  776 */       Witchery.packetPipeline.sendTo(new PacketPushTarget(0.0D, 0.1D, 0.0D), player);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void manifestPlayerInOverworldAsGhost(EntityPlayer player) {
/*  781 */     if ((player != null) && (!player.field_70170_p.field_72995_K)) {
/*  782 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  783 */       if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  784 */         nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
/*      */       }
/*  786 */       NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*      */       
/*      */ 
/*  789 */       int boneNeedles = player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
/*      */       
/*      */ 
/*  792 */       dropBetterBackpacks(player);
/*      */       
/*      */ 
/*  795 */       NBTTagList nbtSpiritInventory = new NBTTagList();
/*  796 */       player.field_71071_by.func_70442_a(nbtSpiritInventory);
/*  797 */       nbtSpirit.func_74782_a("SpiritInventory", nbtSpiritInventory);
/*      */       
/*      */ 
/*  800 */       player.field_71071_by.func_146027_a(null, -1);
/*      */       
/*      */ 
/*  803 */       addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
/*      */       
/*      */ 
/*  806 */       nbtSpirit.func_74776_a("SpiritHealth", Math.max(player.func_110143_aJ(), 1.0F));
/*      */       
/*      */ 
/*  809 */       setPlayerIsGhost(nbtPlayer, true);
/*      */       
/*      */ 
/*  812 */       changeDimension(player, 0);
/*  813 */       findTopAndSetPosition(player.field_70170_p, player);
/*      */       
/*  815 */       Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
/*      */     }
/*      */   }
/*      */   
/*      */   public static void returnGhostPlayerToSpiritWorld(EntityPlayer player) {
/*  820 */     if ((player != null) && (!player.field_70170_p.field_72995_K)) {
/*  821 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  822 */       if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
/*  823 */         nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
/*      */       }
/*  825 */       NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
/*      */       
/*      */ 
/*  828 */       int boneNeedles = player.field_71071_by.func_146027_a(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
/*  829 */       ArrayList<ItemStack> fetishes = getBoundFetishes(player.field_71071_by);
/*      */       
/*      */ 
/*  832 */       player.field_71071_by.func_70436_m();
/*      */       
/*      */ 
/*  835 */       dropBetterBackpacks(player);
/*      */       
/*      */ 
/*  838 */       if (nbtSpirit.func_74764_b("SpiritInventory")) {
/*  839 */         NBTTagList nbtSpiritInventory = nbtSpirit.func_150295_c("SpiritInventory", 10);
/*  840 */         player.field_71071_by.func_70443_b(nbtSpiritInventory);
/*      */         
/*      */ 
/*  843 */         nbtSpirit.func_82580_o("SpiritInventory");
/*      */       }
/*      */       
/*      */ 
/*  847 */       addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
/*  848 */       addItemToInventory(player, fetishes);
/*      */       
/*      */ 
/*  851 */       setPlayerIsGhost(nbtPlayer, false);
/*      */       
/*      */ 
/*  854 */       changeDimension(player, Config.instance().dimensionDreamID);
/*      */       
/*  856 */       findTopAndSetPosition(player.field_70170_p, player);
/*      */       
/*  858 */       Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
/*      */     }
/*      */   }
/*      */   
/*      */   private static ArrayList<ItemStack> getBoundFetishes(InventoryPlayer inventory) {
/*  863 */     ArrayList<ItemStack> stacks = new ArrayList();
/*  864 */     for (int i = 0; i < inventory.func_70302_i_(); i++) {
/*  865 */       ItemStack stack = inventory.func_70301_a(i);
/*  866 */       if ((stack != null) && ((stack.func_77973_b() instanceof BlockFetish.ClassItemBlock)) && 
/*  867 */         (com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect.getEffectID(stack) > 0)) {
/*  868 */         stacks.add(stack);
/*      */       }
/*      */     }
/*      */     
/*  872 */     return stacks;
/*      */   }
/*      */   
/*      */   public static void updatePlayerEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, long time, long counter) {
/*  876 */     if (!world.field_72995_K) {
/*  877 */       boolean done = false;
/*  878 */       if (counter % 20L == 0L) {
/*  879 */         boolean mustAwaken = getPlayerMustAwaken(nbtPlayer);
/*  880 */         if (mustAwaken) {
/*  881 */           setPlayerMustAwaken(nbtPlayer, false);
/*  882 */           if ((player.field_71093_bK != Config.instance().dimensionDreamID) && (getPlayerIsSpiritWalking(player)) && (!getPlayerIsGhost(player)))
/*      */           {
/*  884 */             returnPlayerToOverworld(player);
/*  885 */           } else if (player.field_71093_bK == Config.instance().dimensionDreamID) {
/*  886 */             returnPlayerToOverworld(player);
/*      */           }
/*      */         }
/*      */       }
/*  890 */       if ((!done) && (counter % 100L == 0L)) {
/*  891 */         int nightmareLevel = getPlayerHasNightmare(nbtPlayer);
/*  892 */         if ((player.field_71093_bK == Config.instance().dimensionDreamID) && (nightmareLevel > 0)) {
/*  893 */           double R = 18.0D;
/*  894 */           double H = 18.0D;
/*  895 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 18.0D, player.field_70163_u - 18.0D, player.field_70161_v - 18.0D, player.field_70165_t + 18.0D, player.field_70163_u + 18.0D, player.field_70161_v + 18.0D);
/*      */           
/*      */ 
/*  898 */           if (nightmareLevel > 1) {
/*  899 */             double chance = world.field_73012_v.nextDouble();
/*  900 */             if (chance < 0.5D) {
/*  901 */               EntitySmallFireball fireball = new EntitySmallFireball(world, player.field_70165_t - 2.0D + world.field_73012_v.nextInt(5), player.field_70163_u + 15.0D, player.field_70161_v - 2.0D + world.field_73012_v.nextInt(5), 0.0D, -0.2D, 0.0D);
/*  902 */               world.func_72838_d(fireball);
/*  903 */             } else if (chance < 0.65D) {
/*  904 */               EntityLargeFireball fireball = new EntityLargeFireball(world);
/*      */               
/*  906 */               double par2 = player.field_70165_t - 2.0D + world.field_73012_v.nextInt(5);
/*  907 */               double par4 = player.field_70163_u + 15.0D;
/*  908 */               double par6 = player.field_70161_v - 2.0D + world.field_73012_v.nextInt(5);
/*  909 */               double par8 = 0.0D;
/*  910 */               double par10 = -0.2D;
/*  911 */               double par12 = 0.0D;
/*  912 */               fireball.func_70012_b(par2, par4, par6, fireball.field_70177_z, fireball.field_70125_A);
/*  913 */               fireball.func_70107_b(par2, par4, par6);
/*  914 */               double d6 = MathHelper.func_76133_a(par8 * par8 + par10 * par10 + par12 * par12);
/*  915 */               fireball.field_70232_b = (par8 / d6 * 0.1D);
/*  916 */               fireball.field_70233_c = (par10 / d6 * 0.1D);
/*  917 */               fireball.field_70230_d = (par12 / d6 * 0.1D);
/*      */               
/*  919 */               world.func_72838_d(fireball);
/*  920 */             } else if (chance < 0.75D) {
/*  921 */               List entities = world.func_72872_a(net.minecraft.entity.monster.EntityMob.class, bounds);
/*  922 */               if ((entities.size() < 10) && (!containsDemons(entities, 2))) {
/*  923 */                 EntityDemon blaze = new EntityDemon(world);
/*  924 */                 Infusion.spawnCreature(world, EntityDemon.class, MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), player, 4, 8, ParticleEffect.SMOKE, com.emoniph.witchery.util.SoundEffect.MOB_WITHER_DEATH);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  931 */           List entities = world.func_72872_a(EntityNightmare.class, bounds);
/*  932 */           for (Object obj : entities) {
/*  933 */             EntityNightmare nightmare = (EntityNightmare)obj;
/*  934 */             if (nightmare.getVictimName().equalsIgnoreCase(player.func_70005_c_())) {
/*  935 */               return;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  940 */           long currentTime = MinecraftServer.func_130071_aq();
/*  941 */           long lastKillTime = getPlayerLastNightmareKill(nbtPlayer);
/*  942 */           if (lastKillTime < currentTime - 30000L) {
/*  943 */             Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), player, 2, 6);
/*      */           }
/*      */         }
/*  946 */         else if ((player.field_71093_bK != Config.instance().dimensionDreamID) && (getPlayerIsGhost(nbtPlayer))) {
/*  947 */           int timeRemaining = 0;
/*  948 */           boolean skipNext = getPlayerSkipNextManifestTick(nbtPlayer);
/*  949 */           if (nbtPlayer.func_74764_b("WITCManifestDuration")) {
/*  950 */             timeRemaining = nbtPlayer.func_74762_e("WITCManifestDuration");
/*  951 */             timeRemaining = Math.max(0, timeRemaining - 5);
/*  952 */             if (((timeRemaining >= 60) && (timeRemaining <= 64)) || ((timeRemaining >= 30) && (timeRemaining <= 34)) || ((timeRemaining >= 15) && (timeRemaining <= 19) && 
/*  953 */               (!skipNext))) {
/*  954 */               com.emoniph.witchery.util.ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, "witchery.rite.manifestation.countdown", new Object[] { Integer.valueOf(timeRemaining).toString() });
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  959 */           if (timeRemaining == 0) {
/*  960 */             if (nbtPlayer.func_74764_b("WITCManifestDuration")) {
/*  961 */               nbtPlayer.func_82580_o("WITCManifestDuration");
/*      */             }
/*  963 */             returnGhostPlayerToSpiritWorld(player);
/*      */           }
/*  965 */           else if (!skipNext) {
/*  966 */             nbtPlayer.func_74768_a("WITCManifestDuration", timeRemaining);
/*      */           } else {
/*  968 */             setPlayerSkipNextManifestationReduction(nbtPlayer, false);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void setPlayerSkipNextManifestationReduction(EntityPlayer player)
/*      */   {
/*  979 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  980 */     setPlayerSkipNextManifestationReduction(nbtPlayer, true);
/*      */   }
/*      */   
/*      */   public static void setPlayerSkipNextManifestationReduction(NBTTagCompound nbtPlayer, boolean skip) {
/*  984 */     nbtPlayer.func_74757_a("WITCManifestSkipTick", skip);
/*      */   }
/*      */   
/*      */   public static boolean getPlayerSkipNextManifestTick(NBTTagCompound nbtPlayer) {
/*  988 */     return nbtPlayer.func_74767_n("WITCManifestSkipTick");
/*      */   }
/*      */   
/*      */   private static boolean containsDemons(List entities, int max) {
/*  992 */     int count = 0;
/*  993 */     for (Object obj : entities) {
/*  994 */       if ((obj instanceof EntityDemon)) {
/*  995 */         count++; if (count >= max) {
/*  996 */           return true;
/*      */         }
/*      */       }
/*      */     }
/* 1000 */     return false;
/*      */   }
/*      */   
/*      */   public static void setPlayerIsGhost(NBTTagCompound nbtPlayer, boolean ghost) {
/* 1004 */     nbtPlayer.func_74757_a("WITCManifested", ghost);
/*      */   }
/*      */   
/*      */   public static boolean getPlayerIsGhost(EntityPlayer player) {
/* 1008 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 1009 */     return getPlayerIsGhost(nbtPlayer);
/*      */   }
/*      */   
/*      */   public static boolean getPlayerIsGhost(NBTTagCompound nbtPlayer) {
/* 1013 */     return nbtPlayer.func_74767_n("WITCManifested");
/*      */   }
/*      */   
/*      */   public static void setPlayerMustAwaken(EntityPlayer player, boolean awaken) {
/* 1017 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 1018 */     setPlayerMustAwaken(nbtPlayer, awaken);
/*      */   }
/*      */   
/*      */   public static void setPlayerMustAwaken(NBTTagCompound nbtPlayer, boolean ghost) {
/* 1022 */     nbtPlayer.func_74757_a("WITCForceAwaken", ghost);
/*      */   }
/*      */   
/*      */   public static boolean getPlayerMustAwaken(EntityPlayer player) {
/* 1026 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 1027 */     return getPlayerMustAwaken(nbtPlayer);
/*      */   }
/*      */   
/*      */   public static boolean getPlayerMustAwaken(NBTTagCompound nbtPlayer) {
/* 1031 */     return nbtPlayer.func_74767_n("WITCForceAwaken");
/*      */   }
/*      */   
/*      */   public static boolean canPlayerManifest(EntityPlayer player) {
/* 1035 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 1036 */     int timeRemaining = 0;
/* 1037 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCManifestDuration"))) {
/* 1038 */       timeRemaining = nbtPlayer.func_74762_e("WITCManifestDuration");
/*      */     }
/* 1040 */     return timeRemaining >= 5;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/dimension/WorldProviderDreamWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */