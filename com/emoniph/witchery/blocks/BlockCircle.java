/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.ritual.Circle;
/*     */ import com.emoniph.witchery.ritual.RiteRegistry;
/*     */ import com.emoniph.witchery.ritual.RiteRegistry.Ritual;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class BlockCircle extends BlockBaseContainer
/*     */ {
/*     */   public BlockCircle()
/*     */   {
/*  52 */     super(Material.field_151582_l, TileEntityCircle.class);
/*  53 */     this.registerWithCreateTab = false;
/*     */     
/*  55 */     func_149711_c(3.0F);
/*  56 */     func_149752_b(1000.0F);
/*  57 */     float f1 = 0.015625F;
/*  58 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  64 */     return this.field_149761_L;
/*     */   }
/*     */   
/*     */   public void func_149699_a(World world, int posX, int posY, int posZ, EntityPlayer player)
/*     */   {
/*  69 */     if (!world.field_72995_K) {
/*  70 */       ItemStack itemstack = player.func_70694_bm();
/*  71 */       if ((itemstack != null) && ((Witchery.Items.GENERIC.itemBroom.isMatch(itemstack)) || (Witchery.Items.GENERIC.itemBroomEnchanted.isMatch(itemstack)))) {
/*  72 */         world.func_147480_a(posX, posY, posZ, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  79 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  94 */     return 0;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  99 */     return new ItemStack(Witchery.Items.CHALK_GOLDEN);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 104 */     if (func_111046_k(par1World, par2, par3, par4)) {
/* 105 */       boolean flag = par1World.func_72864_z(par2, par3, par4);
/* 106 */       TileEntityCircle tileCircle = (TileEntityCircle)BlockUtil.getTileEntity(par1World, par2, par3, par4, TileEntityCircle.class);
/*     */       
/* 108 */       if ((tileCircle != null) && (tileCircle.previousRedstoneState != flag)) {
/* 109 */         if (flag) {
/* 110 */           activateBlock(par1World, par2, par3, par4, null, false);
/*     */         }
/*     */         
/* 113 */         tileCircle.previousRedstoneState = flag;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
/* 119 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/* 120 */       par1World.func_147468_f(par2, par3, par4);
/* 121 */       return false;
/*     */     }
/* 123 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/* 129 */     Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
/* 130 */     return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 136 */     return par5 == 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 142 */     int metadata = world.func_72805_g(x, y, z);
/* 143 */     if (metadata == 1) {
/* 144 */       double d0 = x + 0.4F + rand.nextFloat() * 0.2F;
/* 145 */       double d1 = y + 0.1F + rand.nextFloat() * 0.3F;
/* 146 */       double d2 = z + 0.4F + rand.nextFloat() * 0.2F;
/* 147 */       world.func_72869_a(ParticleEffect.REDDUST.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 153 */     ItemStack stack = player.func_70694_bm();
/* 154 */     activateBlock(world, x, y, z, player, (stack != null) && (Witchery.Items.GENERIC.itemSeerStone.isMatch(stack)));
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   private void activateBlock(World world, int posX, int posY, int posZ, EntityPlayer player, boolean summonCoven) {
/* 159 */     TileEntityCircle tileEntity = (TileEntityCircle)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityCircle.class);
/* 160 */     if (tileEntity == null) {
/* 161 */       return;
/*     */     }
/*     */     
/* 164 */     if (tileEntity.isRitualActive()) {
/* 165 */       tileEntity.deactivate();
/* 166 */       return;
/*     */     }
/*     */     
/* 169 */     if (world.field_72995_K) {
/* 170 */       return;
/*     */     }
/*     */     
/* 173 */     if ((PowerSources.instance().isAreaNulled(world, posX, posY, posZ)) || (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID)) {
/* 174 */       ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.nullfield", new Object[0]);
/* 175 */       SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/* 176 */       return;
/*     */     }
/*     */     
/* 179 */     Circle a = new Circle(16);
/* 180 */     Circle b = new Circle(28);
/* 181 */     Circle c = new Circle(40);
/* 182 */     Circle _ = new Circle(0);
/*     */     
/* 184 */     Circle[][] PATTERN = { { _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ }, { _, _, _, _, _, c, c, c, c, c, c, c, _, _, _, _, _ }, { _, _, _, _, c, _, _, _, _, _, _, _, c, _, _, _, _ }, { _, _, _, c, _, _, b, b, b, b, b, _, _, c, _, _, _ }, { _, _, c, _, _, b, _, _, _, _, _, b, _, _, c, _, _ }, { _, c, _, _, b, _, _, a, a, a, _, _, b, _, _, c, _ }, { _, c, _, b, _, _, a, _, _, _, a, _, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, a, _, _, _, _, _, a, _, b, _, c, _ }, { _, c, _, b, _, _, a, _, _, _, a, _, _, b, _, c, _ }, { _, c, _, _, b, _, _, a, a, a, _, _, b, _, _, c, _ }, { _, _, c, _, _, b, _, _, _, _, _, b, _, _, c, _, _ }, { _, _, _, c, _, _, b, b, b, b, b, _, _, c, _, _, _ }, { _, _, _, _, c, _, _, _, _, _, _, _, c, _, _, _, _ }, { _, _, _, _, _, c, c, c, c, c, c, c, _, _, _, _, _ }, { _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ } };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 203 */     int offsetZ = (PATTERN.length - 1) / 2;
/* 204 */     for (int z = 0; z < PATTERN.length - 1; z++) {
/* 205 */       int worldZ = posZ - offsetZ + z;
/* 206 */       int offsetX = (PATTERN[z].length - 1) / 2;
/* 207 */       for (int x = 0; x < PATTERN[z].length; x++) {
/* 208 */         int worldX = posX - offsetX + x;
/* 209 */         PATTERN[(PATTERN.length - 1 - z)][x].addGlyph(world, worldX, posY, worldZ);
/*     */       }
/*     */     }
/*     */     
/* 213 */     boolean isDaytime = world.func_72935_r();
/* 214 */     boolean isRainPossible = world.func_72807_a(posX, posZ).func_76738_d();
/* 215 */     boolean isRaining = (world.func_72896_J()) && (isRainPossible);
/* 216 */     boolean isThundering = world.func_72911_I();
/*     */     
/* 218 */     int maxRadius = PATTERN.length / 2;
/* 219 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - maxRadius, posY, posZ - maxRadius, posX + maxRadius, posY + 1, posZ + maxRadius);
/* 220 */     ArrayList<Entity> entities = new ArrayList();
/* 221 */     for (Object obj : world.func_72872_a(Entity.class, bounds)) {
/* 222 */       Entity item = (Entity)obj;
/* 223 */       entities.add(item);
/*     */     }
/*     */     
/* 226 */     ArrayList<ItemStack> grassperStacks = new ArrayList();
/*     */     
/* 228 */     int radius = 5;
/* 229 */     for (int x = posX - 5; x <= posX + 5; x++) {
/* 230 */       for (int z = posZ - 5; z <= posZ + 5; z++) {
/* 231 */         Block block = world.func_147439_a(x, posY, z);
/* 232 */         if (block == Witchery.Blocks.GRASSPER) {
/* 233 */           TileEntity tile = world.func_147438_o(x, posY, z);
/* 234 */           if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
/* 235 */             BlockGrassper.TileEntityGrassper grassper = (BlockGrassper.TileEntityGrassper)tile;
/*     */             
/* 237 */             ItemStack stack = grassper.func_70301_a(0);
/* 238 */             if (stack != null) {
/* 239 */               grassperStacks.add(stack);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 246 */     Circle[] circles = { a, b, c };
/* 247 */     boolean ritualFound = false;
/* 248 */     int covenSize = summonCoven ? EntityCovenWitch.getCovenSize(player) : 0;
/* 249 */     for (RiteRegistry.Ritual ritual : RiteRegistry.instance().getRituals()) {
/* 250 */       if (ritual.isMatch(world, posX, posY, posZ, circles, entities, grassperStacks, isDaytime, isRaining, isThundering))
/*     */       {
/* 252 */         tileEntity.queueRitual(ritual, bounds, player, covenSize, summonCoven);
/* 253 */         summonCoven = false;
/* 254 */         ritualFound = true;
/*     */       }
/*     */     }
/*     */     
/* 258 */     if ((!ritualFound) && (!world.field_72995_K)) {
/* 259 */       RiteRegistry.RiteError("witchery.rite.unknownritual", player, world);
/* 260 */       SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityCircle extends TileEntityBase
/*     */   {
/*     */     public boolean previousRedstoneState;
/*     */     private final ArrayList<ActivatedRitual> activeRituals;
/*     */     private final ArrayList<ActivatedRitual> upkeepRituals;
/*     */     private boolean abortNext;
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtTag) {
/* 272 */       super.func_145841_b(nbtTag);
/* 273 */       byte[] ritualIDs = new byte[this.upkeepRituals.size()];
/* 274 */       byte[] stages = new byte[this.upkeepRituals.size()];
/* 275 */       byte[] covenSizes = new byte[this.upkeepRituals.size()];
/* 276 */       NBTTagList nbtList = new NBTTagList();
/* 277 */       NBTTagList nbtLocationList = new NBTTagList();
/* 278 */       for (int i = 0; i < this.upkeepRituals.size(); i++) {
/* 279 */         ritualIDs[i] = ((ActivatedRitual)this.upkeepRituals.get(i)).ritual.getRitualID();
/* 280 */         stages[i] = ((byte)((ActivatedRitual)this.upkeepRituals.get(i)).getCurrentStage());
/* 281 */         covenSizes[i] = ((byte)((ActivatedRitual)this.upkeepRituals.get(i)).covenSize);
/* 282 */         nbtList.func_74742_a(new NBTTagString(((ActivatedRitual)this.upkeepRituals.get(i)).getInitiatingPlayerName()));
/* 283 */         nbtLocationList.func_74742_a(((ActivatedRitual)this.upkeepRituals.get(i)).getLocationTag());
/*     */       }
/* 285 */       nbtTag.func_74773_a("Rituals", ritualIDs);
/* 286 */       nbtTag.func_74773_a("RitualStages", stages);
/* 287 */       nbtTag.func_74782_a("Initiators", nbtList);
/* 288 */       nbtTag.func_74782_a("Locations", nbtLocationList);
/* 289 */       nbtTag.func_74773_a("RitualCovens", covenSizes);
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 295 */       super.func_145839_a(nbtTag);
/* 296 */       if ((nbtTag.func_74764_b("Rituals")) && (nbtTag.func_74764_b("RitualStages"))) {
/* 297 */         byte[] stages = nbtTag.func_74770_j("RitualStages");
/* 298 */         byte[] ritualIDs = nbtTag.func_74770_j("Rituals");
/*     */         
/* 300 */         Coord[] locations = new Coord[stages.length];
/* 301 */         if (nbtTag.func_74764_b("Locations")) {
/* 302 */           NBTTagList list = nbtTag.func_150295_c("Locations", 10);
/* 303 */           for (int i = 0; i < Math.min(list.func_74745_c(), locations.length); i++) {
/* 304 */             NBTTagCompound nbtListItem = list.func_150305_b(i);
/* 305 */             locations[i] = Coord.fromTagNBT(nbtListItem);
/*     */           }
/*     */         }
/*     */         
/* 309 */         String[] initators = new String[stages.length];
/* 310 */         if (nbtTag.func_74764_b("Initiators")) {
/* 311 */           NBTTagList list = nbtTag.func_150295_c("Initiators", 8);
/* 312 */           for (int i = 0; i < Math.min(list.func_74745_c(), initators.length); i++) {
/* 313 */             String nbtListItem = list.func_150307_f(i);
/* 314 */             initators[i] = ((nbtListItem != null) && (!nbtListItem.isEmpty()) ? nbtListItem : null);
/*     */           }
/*     */         }
/*     */         
/* 318 */         byte[] covenSizes = nbtTag.func_74764_b("RitualCovens") ? nbtTag.func_74770_j("RitualCovens") : null;
/*     */         
/* 320 */         for (int i = 0; i < ritualIDs.length; i++) {
/* 321 */           RiteRegistry.Ritual ritual = RiteRegistry.instance().getRitual(ritualIDs[i]);
/* 322 */           if (ritual != null) {
/* 323 */             ArrayList<RitualStep> ritualSteps = new ArrayList();
/* 324 */             ritual.addRiteSteps(ritualSteps, stages[i]);
/* 325 */             if (!ritualSteps.isEmpty()) {
/* 326 */               ActivatedRitual activatedRitual = new ActivatedRitual(ritual, ritualSteps, initators[i], covenSizes != null ? covenSizes[i] : 0, null);
/* 327 */               activatedRitual.setLocation(locations[i]);
/* 328 */               this.upkeepRituals.add(activatedRitual);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 337 */       super.func_145845_h();
/*     */       
/* 339 */       if (!this.field_145850_b.field_72995_K) {
/* 340 */         if (!this.upkeepRituals.isEmpty()) {
/* 341 */           for (ActivatedRitual upkeepRitual : this.upkeepRituals) {
/* 342 */             RitualStep.Result result = ((RitualStep)upkeepRitual.steps.get(0)).run(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.ticks, upkeepRitual);
/*     */             
/* 344 */             if ((result != RitualStep.Result.UPKEEP) && (Config.instance().traceRites())) {
/* 345 */               Log.instance().traceRite(String.format(" - Upkeep ritual=%s, step=%s, result=%s", new Object[] { upkeepRitual.ritual.getUnlocalizedName(), ((RitualStep)upkeepRitual.steps.get(0)).toString(), result.toString() }));
/*     */             }
/*     */             
/* 348 */             switch (BlockCircle.1.$SwitchMap$com$emoniph$witchery$ritual$RitualStep$Result[result.ordinal()]) {
/*     */             case 1: 
/* 350 */               upkeepRitual.steps.clear();
/* 351 */               break;
/*     */             case 2: 
/*     */             case 3: 
/* 354 */               upkeepRitual.steps.clear();
/* 355 */               SoundEffect.NOTE_SNARE.playAt(this);
/*     */             }
/*     */             
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 362 */           for (int i = this.upkeepRituals.size() - 1; i >= 0; i--) {
/* 363 */             if (((ActivatedRitual)this.upkeepRituals.get(i)).steps.isEmpty()) {
/* 364 */               this.upkeepRituals.remove(i);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 369 */         if (!this.activeRituals.isEmpty()) {
/* 370 */           ActivatedRitual ritual = (ActivatedRitual)this.activeRituals.get(0);
/* 371 */           RitualStep.Result result = ((RitualStep)ritual.steps.get(0)).run(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.ticks, ritual);
/* 372 */           ritual.postProcess(this.field_145850_b);
/* 373 */           if (this.abortNext) {
/* 374 */             this.abortNext = false;
/* 375 */             result = RitualStep.Result.ABORTED_REFUND;
/* 376 */             this.activeRituals.clear();
/*     */           }
/*     */           
/* 379 */           if ((result != RitualStep.Result.STARTING) && (Config.instance().traceRites())) {
/* 380 */             Log.instance().traceRite(String.format("Active ritual=%s, step=%s, result=%s", new Object[] { ritual.ritual.getUnlocalizedName(), ((RitualStep)ritual.steps.get(0)).toString(), result.toString() }));
/*     */           }
/*     */           
/* 383 */           switch (BlockCircle.1.$SwitchMap$com$emoniph$witchery$ritual$RitualStep$Result[result.ordinal()]) {
/*     */           case 4: 
/* 385 */             if (this.activeRituals.size() > 0) {
/* 386 */               this.activeRituals.remove(0);
/*     */             }
/* 388 */             this.upkeepRituals.add(ritual);
/* 389 */             break;
/*     */           case 1: 
/* 391 */             if (ritual.steps.size() > 0) {
/* 392 */               ritual.steps.remove(0);
/*     */             }
/* 394 */             if (ritual.steps.isEmpty()) {
/* 395 */               this.activeRituals.remove(0);
/*     */             }
/*     */             break;
/*     */           case 2: 
/*     */           case 3: 
/* 400 */             if (this.activeRituals.size() > 0) {
/* 401 */               this.activeRituals.remove(0);
/*     */             }
/* 403 */             if (!this.field_145850_b.field_72995_K) {
/* 404 */               SoundEffect.NOTE_SNARE.playAt(this);
/* 405 */               if (result == RitualStep.Result.ABORTED_REFUND)
/* 406 */                 for (RitualStep.SacrificedItem sacrificedItem : ritual.sacrificedItems)
/* 407 */                   this.field_145850_b.func_72838_d(new EntityItem(this.field_145850_b, 0.5D + sacrificedItem.location.x, 0.5D + sacrificedItem.location.y, 0.5D + sacrificedItem.location.z, sacrificedItem.itemstack));
/*     */             }
/* 409 */             break;
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 418 */         if ((!isRitualActive()) && (func_145832_p() != 0)) {
/* 419 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public TileEntityCircle()
/*     */     {
/* 267 */       this.activeRituals = new ArrayList();
/* 268 */       this.upkeepRituals = new ArrayList();
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
/* 424 */       this.abortNext = false; }
/*     */     
/* 426 */     public void deactivate() { if (!this.field_145850_b.field_72995_K)
/*     */       {
/* 428 */         if (this.activeRituals.size() > 0) {
/* 429 */           this.abortNext = true;
/*     */         }
/* 431 */         this.upkeepRituals.clear();
/* 432 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
/* 433 */         SoundEffect.NOTE_SNARE.playAt(this);
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean isRitualActive() {
/* 438 */       return (!this.activeRituals.isEmpty()) || (!this.upkeepRituals.isEmpty());
/*     */     }
/*     */     
/*     */     public static class ActivatedRitual {
/*     */       private final RiteRegistry.Ritual ritual;
/*     */       private final ArrayList<RitualStep> steps;
/*     */       public final String playerName;
/* 445 */       public final ArrayList<RitualStep.SacrificedItem> sacrificedItems = new ArrayList();
/*     */       public final int covenSize;
/*     */       private Coord coord;
/*     */       
/*     */       private ActivatedRitual(RiteRegistry.Ritual ritual, ArrayList<RitualStep> steps, String playerName, int covenSize) {
/* 450 */         this.ritual = ritual;
/* 451 */         this.steps = steps;
/* 452 */         this.playerName = playerName;
/* 453 */         this.covenSize = covenSize;
/*     */       }
/*     */       
/*     */       public Coord getLocation() {
/* 457 */         return this.coord;
/*     */       }
/*     */       
/*     */       public NBTTagCompound getLocationTag() {
/* 461 */         return this.coord != null ? this.coord.toTagNBT() : new NBTTagCompound();
/*     */       }
/*     */       
/*     */       public void setLocation(Coord coord) {
/* 465 */         this.coord = coord;
/*     */       }
/*     */       
/*     */       public String getInitiatingPlayerName() {
/* 469 */         return this.playerName != null ? this.playerName : "";
/*     */       }
/*     */       
/*     */       public EntityPlayer getInitiatingPlayer(World world) {
/* 473 */         return world.func_72924_a(getInitiatingPlayerName());
/*     */       }
/*     */       
/*     */       public void postProcess(World world)
/*     */       {
/* 478 */         for (int i = 0; i < this.sacrificedItems.size(); i++) {
/* 479 */           RitualStep.SacrificedItem sacrificedItem = (RitualStep.SacrificedItem)this.sacrificedItems.get(i);
/* 480 */           if ((sacrificedItem != null) && (sacrificedItem.itemstack != null)) {
/* 481 */             if ((!this.ritual.isConsumeAttunedStoneCharged()) && (Witchery.Items.GENERIC.itemAttunedStoneCharged.isMatch(sacrificedItem.itemstack))) {
/* 482 */               world.func_72838_d(new EntityItem(world, 0.5D + sacrificedItem.location.x, 0.5D + sacrificedItem.location.y, 0.5D + sacrificedItem.location.z, Witchery.Items.GENERIC.itemAttunedStone.createStack()));
/*     */               
/* 484 */               this.sacrificedItems.remove(i);
/* 485 */               break; }
/* 486 */             if (sacrificedItem.itemstack.func_77973_b() == Witchery.Items.ARTHANA) {
/* 487 */               world.func_72838_d(new EntityItem(world, 0.5D + sacrificedItem.location.x, 0.5D + sacrificedItem.location.y, 0.5D + sacrificedItem.location.z, sacrificedItem.itemstack));
/*     */               
/* 489 */               this.sacrificedItems.remove(i);
/* 490 */               break; }
/* 491 */             if (sacrificedItem.itemstack.func_77973_b() == Witchery.Items.BOLINE) {
/* 492 */               world.func_72838_d(new EntityItem(world, 0.5D + sacrificedItem.location.x, 0.5D + sacrificedItem.location.y, 0.5D + sacrificedItem.location.z, sacrificedItem.itemstack));
/*     */               
/* 494 */               this.sacrificedItems.remove(i);
/* 495 */               break; }
/* 496 */             if ((!this.ritual.isConsumeNecroStone()) && (Witchery.Items.GENERIC.itemNecroStone.isMatch(sacrificedItem.itemstack))) {
/* 497 */               world.func_72838_d(new EntityItem(world, 0.5D + sacrificedItem.location.x, 0.5D + sacrificedItem.location.y, 0.5D + sacrificedItem.location.z, sacrificedItem.itemstack));
/*     */               
/* 499 */               this.sacrificedItems.remove(i);
/* 500 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */       public int getCurrentStage()
/*     */       {
/* 508 */         if (!this.steps.isEmpty()) {
/* 509 */           return ((RitualStep)this.steps.get(0)).getCurrentStage();
/*     */         }
/* 511 */         return 0;
/*     */       }
/*     */     }
/*     */     
/*     */     public void queueRitual(RiteRegistry.Ritual ritual, AxisAlignedBB bounds, EntityPlayer player, int covenSize, boolean summonCoven)
/*     */     {
/* 517 */       ArrayList<RitualStep> ritualSteps = new ArrayList();
/* 518 */       if (summonCoven) {
/* 519 */         EntityCovenWitch.summonCoven(ritualSteps, player.field_70170_p, player, new int[][] { { this.field_145851_c - 2, this.field_145848_d, this.field_145849_e - 2 }, { this.field_145851_c + 2, this.field_145848_d, this.field_145849_e - 2 }, { this.field_145851_c - 2, this.field_145848_d, this.field_145849_e + 2 }, { this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 2 }, { this.field_145851_c, this.field_145848_d, this.field_145849_e + 3 }, { this.field_145851_c, this.field_145848_d, this.field_145849_e - 3 } });
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 527 */       ritual.addSteps(ritualSteps, bounds);
/* 528 */       if ((ritualSteps.size() > 0) && (!this.field_145850_b.field_72995_K)) {
/* 529 */         this.activeRituals.add(new ActivatedRitual(ritual, ritualSteps, player != null ? player.func_70005_c_() : null, covenSize, null));
/* 530 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
/*     */       }
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 536 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 537 */       func_145841_b(nbtTag);
/* 538 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 543 */       super.onDataPacket(net, packet);
/* 544 */       func_145839_a(packet.func_148857_g());
/* 545 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockCircle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */