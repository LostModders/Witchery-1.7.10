/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockBeartrap extends BlockBaseContainer
/*     */ {
/*     */   private final boolean silvered;
/*     */   
/*     */   public BlockBeartrap(boolean silvered)
/*     */   {
/*  48 */     super(Material.field_151573_f, TileEntityBeartrap.class);
/*  49 */     this.silvered = silvered;
/*  50 */     func_149711_c(5.0F);
/*  51 */     func_149752_b(10.0F);
/*  52 */     func_149672_a(field_149777_j);
/*  53 */     float w = 0.3F;
/*  54 */     func_149676_a(0.19999999F, 0.01F, 0.19999999F, 0.8F, 0.1F, 0.8F);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/*  59 */     TileEntityBeartrap tile = new TileEntityBeartrap(this.silvered);
/*  60 */     return tile;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/*  65 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  70 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
/*     */   {
/*  90 */     switch (MathHelper.func_76128_c(entity.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3) {
/*     */     case 0: 
/*  92 */       world.func_72921_c(x, y, z, 2, 2);
/*  93 */       break;
/*     */     case 1: 
/*  95 */       world.func_72921_c(x, y, z, 5, 2);
/*  96 */       break;
/*     */     case 2: 
/*  98 */       world.func_72921_c(x, y, z, 3, 2);
/*  99 */       break;
/*     */     case 3: 
/* 101 */       world.func_72921_c(x, y, z, 4, 2);
/*     */     }
/*     */     
/*     */     
/* 105 */     if ((!world.field_72995_K) && ((entity instanceof EntityPlayer))) {
/* 106 */       EntityPlayer player = (EntityPlayer)entity;
/* 107 */       TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(world, x, y, z, TileEntityBeartrap.class);
/* 108 */       if (tile != null) {
/* 109 */         tile.owner = player.func_146103_bH();
/* 110 */         tile.sprung = true;
/* 111 */         tile.markBlockForUpdate(false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 117 */     if (this.silvered) {
/* 118 */       return new ArrayList();
/*     */     }
/*     */     
/* 121 */     return super.getDrops(world, x, y, z, metadata, fortune);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 126 */     if ((!world.field_72995_K) && ((entity instanceof EntityLivingBase))) {
/* 127 */       EntityLivingBase living = (EntityLivingBase)entity;
/* 128 */       TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(world, x, y, z, TileEntityBeartrap.class);
/* 129 */       if ((tile != null) && (!tile.sprung) && (world.func_82737_E() > tile.setTime + 20L) && ((!this.silvered) || (CreatureUtil.isWerewolf(entity, false)))) {
/* 130 */         AxisAlignedBB trapBounds = AxisAlignedBB.func_72330_a(x + this.field_149759_B, y + this.field_149760_C, z + this.field_149754_D, x + this.field_149755_E, y + this.field_149756_F, z + this.field_149757_G);
/*     */         
/*     */ 
/* 133 */         if ((trapBounds.func_72326_a(entity.field_70121_D)) && (
/* 134 */           (!this.silvered) || (tile.tryTrapWolf(living)))) {
/* 135 */           boolean isCreative = ((entity instanceof EntityPlayer)) && (((EntityPlayer)entity).field_71075_bZ.field_75098_d);
/*     */           
/* 137 */           if (!isCreative) {
/* 138 */             living.func_70690_d(new PotionEffect(Witchery.Potions.PARALYSED.field_76415_H, TimeUtil.secsToTicks(30), 2, true));
/*     */           }
/*     */           
/* 141 */           living.func_70097_a(DamageSource.field_82728_o, 4.0F);
/* 142 */           ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_MANTRAP, world, 0.5D + x, 0.5D + y, 0.5D + z, 0.25D, 0.5D, 16);
/*     */           
/* 144 */           tile.sprung = true;
/* 145 */           tile.markBlockForUpdate(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 155 */     if (!world.field_72995_K) {
/* 156 */       TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(world, x, y, z, TileEntityBeartrap.class);
/* 157 */       if (tile != null) {
/* 158 */         SoundEffect.WITCHERY_RANDOM_CLICK.playAtPlayer(world, player);
/* 159 */         tile.sprung = (!tile.sprung);
/* 160 */         if (!tile.sprung) {
/* 161 */           tile.setTime = world.func_82737_E();
/*     */         }
/* 163 */         tile.markBlockForUpdate(false);
/*     */       }
/*     */     }
/*     */     
/* 167 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static boolean checkForHiddenTrap(EntityPlayer player, MovingObjectPosition mop) {
/* 172 */     if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && 
/* 173 */       (player.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Witchery.Blocks.BEARTRAP)) {
/* 174 */       TileEntityBeartrap tile = (TileEntityBeartrap)BlockUtil.getTileEntity(player.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, TileEntityBeartrap.class);
/*     */       
/* 176 */       if (tile != null) {
/* 177 */         return !tile.isVisibleTo(player);
/*     */       }
/*     */     }
/*     */     
/* 181 */     return false;
/*     */   }
/*     */   
/*     */   public static class TileEntityBeartrap extends TileEntityBase {
/*     */     private final boolean silvered;
/*     */     private GameProfile owner;
/* 187 */     private boolean sprung = true;
/* 188 */     private long setTime = 0L;
/* 189 */     private long startTime = 0L;
/* 190 */     private UUID spawnedWolfID = null;
/*     */     
/*     */     public TileEntityBeartrap() {
/* 193 */       this.silvered = false;
/*     */     }
/*     */     
/*     */     public TileEntityBeartrap(boolean silvered) {
/* 197 */       this.silvered = silvered;
/*     */     }
/*     */     
/*     */     public boolean tryTrapWolf(EntityLivingBase living) {
/* 201 */       if ((this.silvered) && 
/* 202 */         ((living instanceof EntityWolfman))) {
/* 203 */         EntityWolfman wolf = (EntityWolfman)living;
/* 204 */         if ((this.spawnedWolfID != null) && (wolf != null) && (wolf.getPersistentID().equals(this.spawnedWolfID))) {
/* 205 */           SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playAt(this, 1.0F);
/* 206 */           wolf.setInfectious();
/* 207 */           return true;
/*     */         }
/*     */       }
/*     */       
/* 211 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isSprung() {
/* 215 */       return this.sprung;
/*     */     }
/*     */     
/*     */     public boolean canUpdate()
/*     */     {
/* 220 */       return this.silvered;
/*     */     }
/*     */     
/* 223 */     private static final int MIN_LURE_TIME = TimeUtil.minsToTicks(1);
/* 224 */     private static final int LURE_EXTRA = TimeUtil.minsToTicks(1);
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 228 */       super.func_145845_h();
/*     */       
/* 230 */       if ((!this.field_145850_b.field_72995_K) && (this.silvered) && (!this.sprung) && (this.spawnedWolfID == null) && (TimeUtil.secondsElapsed(10, this.ticks)))
/*     */       {
/* 232 */         if ((baitFound()) && (CreatureUtil.isFullMoon(this.field_145850_b))) {
/* 233 */           long time = this.field_145850_b.func_82737_E();
/* 234 */           if (this.startTime > 0L) {
/* 235 */             long activateTime = this.startTime;
/* 236 */             if ((time > activateTime) && (CreatureUtil.isFullMoon(this.field_145850_b))) {
/* 237 */               EntityCreature creature = InfusionInfernal.spawnCreature(this.field_145850_b, EntityWolfman.class, this.field_145851_c, this.field_145848_d, this.field_145849_e, null, 16, 32, ParticleEffect.SMOKE, SoundEffect.WITCHERY_MOB_WOLFMAN_TALK);
/*     */               
/*     */ 
/* 240 */               if (creature != null) {
/* 241 */                 creature.func_110163_bv();
/* 242 */                 this.spawnedWolfID = creature.getPersistentID();
/*     */               }
/*     */             }
/*     */           } else {
/* 246 */             this.startTime = time;
/*     */           }
/*     */         } else {
/* 249 */           this.startTime = 0L;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private boolean baitFound() {
/* 255 */       double R = 8.0D;
/* 256 */       double RSQ = 64.0D;
/* 257 */       boolean foundSheep = false;
/* 258 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(0.5D + this.field_145851_c - 8.0D, 0.5D + this.field_145848_d - 8.0D, 0.5D + this.field_145849_e - 8.0D, 0.5D + this.field_145851_c + 8.0D, 0.5D + this.field_145848_d + 8.0D, 0.5D + this.field_145849_e + 8.0D);
/*     */       
/* 260 */       List<EntitySheep> sheep = this.field_145850_b.func_72872_a(EntitySheep.class, bounds);
/* 261 */       for (EntitySheep aSheep : sheep) {
/* 262 */         if ((aSheep.func_70092_e(0.5D + this.field_145851_c, 0.5D + this.field_145848_d, 0.5D + this.field_145849_e) <= 64.0D) && 
/* 263 */           (aSheep.func_110167_bD())) {
/* 264 */           foundSheep = true;
/* 265 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 270 */       boolean wolfaltar = (this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == Witchery.Blocks.WOLF_ALTAR) || (this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == Witchery.Blocks.WOLF_ALTAR) || (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == Witchery.Blocks.WOLF_ALTAR) || (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == Witchery.Blocks.WOLF_ALTAR);
/*     */       
/*     */ 
/*     */ 
/* 274 */       return (wolfaltar) && (foundSheep);
/*     */     }
/*     */     
/*     */     public boolean isVisibleTo(EntityPlayer player) {
/* 278 */       if ((this.sprung) || (this.owner == null) || (this.silvered))
/* 279 */         return true;
/* 280 */       if (player == null)
/* 281 */         return false;
/* 282 */       if (player.func_146103_bH().equals(this.owner)) {
/* 283 */         return true;
/*     */       }
/* 285 */       return false;
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 290 */       super.func_145841_b(nbtRoot);
/* 291 */       nbtRoot.func_74757_a("Sprung", this.sprung);
/* 292 */       nbtRoot.func_74772_a("WolftrapStart", this.startTime);
/* 293 */       if (this.spawnedWolfID != null) {
/* 294 */         nbtRoot.func_74772_a("WolfLeast", this.spawnedWolfID.getLeastSignificantBits());
/* 295 */         nbtRoot.func_74772_a("WolfMost", this.spawnedWolfID.getMostSignificantBits());
/*     */       }
/*     */       
/* 298 */       if (this.owner != null) {
/* 299 */         NBTTagCompound nbtPlayer = new NBTTagCompound();
/* 300 */         NBTUtil.func_152460_a(nbtPlayer, this.owner);
/* 301 */         nbtRoot.func_74782_a("Owner", nbtPlayer);
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot) {
/* 306 */       super.func_145839_a(nbtRoot);
/* 307 */       this.sprung = nbtRoot.func_74767_n("Sprung");
/* 308 */       this.startTime = nbtRoot.func_74763_f("WolftrapStart");
/* 309 */       if (nbtRoot.func_150297_b("Owner", 10)) {
/* 310 */         this.owner = NBTUtil.func_152459_a(nbtRoot.func_74775_l("Owner"));
/*     */       } else {
/* 312 */         this.owner = null;
/*     */       }
/*     */       
/* 315 */       if ((nbtRoot.func_74764_b("WolfLeast")) && (nbtRoot.func_74764_b("WolfMost"))) {
/* 316 */         this.spawnedWolfID = new UUID(nbtRoot.func_74763_f("WolfMost"), nbtRoot.func_74763_f("WolfLeast"));
/*     */       } else {
/* 318 */         this.spawnedWolfID = null;
/*     */       }
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 324 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 325 */       func_145841_b(nbtTag);
/* 326 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 331 */       super.onDataPacket(net, packet);
/* 332 */       func_145839_a(packet.func_148857_g());
/* 333 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBeartrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */