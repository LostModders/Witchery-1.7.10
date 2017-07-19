/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import com.emoniph.witchery.entity.EntityGoblinGulg;
/*     */ import com.emoniph.witchery.entity.EntityGoblinMog;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.management.ServerConfigurationManager;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockStatueOfWorship extends BlockBaseContainer
/*     */ {
/*     */   public static class ClassItemBlock extends ItemBlock
/*     */   {
/*     */     public ClassItemBlock(Block block)
/*     */     {
/*  49 */       super();
/*     */     }
/*     */     
/*     */     public String func_77653_i(ItemStack stack)
/*     */     {
/*  54 */       String s = super.func_77653_i(stack);
/*  55 */       String player = BlockStatueOfWorship.getBoundPlayerName(stack);
/*  56 */       if ((player != null) && (!player.isEmpty())) {
/*  57 */         return s + " (" + player + ")";
/*     */       }
/*  59 */       return s;
/*     */     }
/*     */   }
/*     */   
/*     */   public BlockStatueOfWorship()
/*     */   {
/*  65 */     super(Material.field_151576_e, TileEntityStatueOfWorship.class, ClassItemBlock.class);
/*  66 */     func_149711_c(3.5F);
/*  67 */     func_149752_b(20.0F);
/*  68 */     func_149672_a(field_149769_e);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  83 */     return false;
/*     */   }
/*     */   
/*     */   public String func_149732_F()
/*     */   {
/*  88 */     return super.func_149732_F();
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase player, ItemStack stack)
/*     */   {
/*  93 */     int l = MathHelper.func_76128_c(player.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*  94 */     if (l == 0) {
/*  95 */       world.func_72921_c(posX, posY, posZ, 2, 2);
/*  96 */     } else if (l == 1) {
/*  97 */       world.func_72921_c(posX, posY, posZ, 5, 2);
/*  98 */     } else if (l == 2) {
/*  99 */       world.func_72921_c(posX, posY, posZ, 3, 2);
/* 100 */     } else if (l == 3) {
/* 101 */       world.func_72921_c(posX, posY, posZ, 4, 2);
/*     */     }
/*     */     
/* 104 */     if ((stack != null) && ((player instanceof EntityPlayer))) {
/* 105 */       TileEntityStatueOfWorship tile = (TileEntityStatueOfWorship)BlockUtil.getTileEntity(world, posX, posY, posZ, TileEntityStatueOfWorship.class);
/* 106 */       if (tile != null) {
/* 107 */         NBTTagCompound nbtRoot = stack.func_77978_p();
/* 108 */         if ((nbtRoot != null) && (nbtRoot.func_74764_b("WITCBoundPlayer"))) {
/* 109 */           String playerName = nbtRoot.func_74779_i("WITCBoundPlayer");
/* 110 */           if ((playerName != null) && (!playerName.isEmpty())) {
/* 111 */             tile.setOwner(playerName);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int posX, int posY, int posZ)
/*     */   {
/* 120 */     super.func_149726_b(world, posX, posY, posZ);
/* 121 */     BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
/*     */   }
/*     */   
/*     */   public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 126 */     if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
/* 127 */       par5 |= 0x8;
/* 128 */       world.func_72921_c(posX, posY, posZ, par5, 4);
/*     */     }
/*     */     
/* 131 */     func_149697_b(world, posX, posY, posZ, par5, 0);
/*     */     
/* 133 */     super.func_149681_a(world, posX, posY, posZ, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 138 */     ArrayList<ItemStack> drops = new ArrayList();
/* 139 */     if ((metadata & 0x8) == 0) {
/* 140 */       TileEntityStatueOfWorship tile = (TileEntityStatueOfWorship)BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueOfWorship.class);
/* 141 */       if (tile != null) {
/* 142 */         ItemStack stack = new ItemStack(tile.func_145838_q());
/* 143 */         NBTTagCompound nbtRoot = new NBTTagCompound();
/* 144 */         stack.func_77982_d(nbtRoot);
/* 145 */         nbtRoot.func_74778_a("WITCBoundPlayer", tile.owner != null ? tile.owner : "");
/* 146 */         drops.add(stack);
/*     */       }
/*     */     }
/* 149 */     return drops;
/*     */   }
/*     */   
/*     */   private static String getBoundPlayerName(ItemStack stack) {
/* 153 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/* 154 */     if (nbtRoot != null) {
/* 155 */       return nbtRoot.func_74779_i("WITCBoundPlayer");
/*     */     }
/* 157 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hiyY, float hitZ)
/*     */   {
/* 163 */     if (!world.field_72995_K) {
/* 164 */       ItemStack item = player.func_70694_bm();
/* 165 */       if ((item != null) && (item.func_77973_b() == Items.field_151156_bN)) {
/* 166 */         TileEntityStatueOfWorship tile = (TileEntityStatueOfWorship)BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueOfWorship.class);
/* 167 */         if ((tile != null) && (tile.owner != null) && (tile.owner.equals(player.func_70005_c_())))
/*     */         {
/* 169 */           int worshippers = tile.updateWorshippersAndGetLevel();
/* 170 */           if ((worshippers >= 5) && 
/* 171 */             (tile.summonGoblinGods(player, 16.0D, 8))) {
/* 172 */             if (!player.field_71075_bZ.field_75098_d) {
/* 173 */               if (--item.field_77994_a <= 0) {
/* 174 */                 player.func_70062_b(0, null);
/*     */               }
/* 176 */               double R = 8.0D;
/* 177 */               AxisAlignedBB bb = AxisAlignedBB.func_72330_a(x + 0.5D - R, y + 0.5D - R, z + 0.5D - R, x + 0.5D + R, y + 0.5D + R, z + 0.5D + R);
/*     */               
/* 179 */               List entities = world.func_72872_a(EntityGoblin.class, bb);
/* 180 */               int i = 0; for (int iMax = Math.min(entities.size(), 5); i < iMax; i++) {
/* 181 */                 if ((entities.get(i) instanceof EntityGoblin)) {
/* 182 */                   EntityGoblin goblin = (EntityGoblin)entities.get(i);
/* 183 */                   goblin.func_70097_a(DamageSource.field_76376_m, goblin.func_110138_aP());
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/* 188 */             ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + x, 0.5D + y, 0.5D + z, 0.5D, 0.5D, 16);
/* 189 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 194 */       ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, 0.5D + y, 0.5D + z, 0.5D, 0.5D, 16);
/*     */     }
/* 196 */     return false; }
/*     */   
/*     */   public static class TileEntityStatueOfWorship extends TileEntityBase { private String owner;
/*     */     @SideOnly(Side.CLIENT)
/*     */     private ThreadDownloadImageData downloadImageSkin;
/*     */     @SideOnly(Side.CLIENT)
/*     */     private ResourceLocation locationSkin;
/*     */     
/* 204 */     public void setOwner(EntityPlayer player) { this.owner = player.func_70005_c_();
/* 205 */       if (!this.field_145850_b.field_72995_K) {
/* 206 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public void setOwner(String player) {
/* 211 */       this.owner = player;
/* 212 */       if (!this.field_145850_b.field_72995_K) {
/* 213 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean hasOwner() {
/* 218 */       return (this.owner != null) && (!this.owner.isEmpty());
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 223 */       super.func_145841_b(nbtRoot);
/* 224 */       nbtRoot.func_74778_a("Owner", this.owner != null ? this.owner : "");
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 229 */       super.func_145839_a(nbtRoot);
/* 230 */       this.owner = nbtRoot.func_74779_i("Owner");
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 235 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 236 */       func_145841_b(nbtTag);
/* 237 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 242 */       super.onDataPacket(net, packet);
/* 243 */       func_145839_a(packet.func_148857_g());
/* 244 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     @SideOnly(Side.CLIENT)
/*     */     public ResourceLocation getLocationSkin()
/*     */     {
/* 255 */       if (this.locationSkin == null) {
/* 256 */         setupCustomSkin();
/*     */       }
/* 258 */       if (this.locationSkin != null) {
/* 259 */         return this.locationSkin;
/*     */       }
/* 261 */       return AbstractClientPlayer.field_110314_b;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     private void setupCustomSkin()
/*     */     {
/* 267 */       if ((this.owner != null) && (!this.owner.isEmpty())) {
/* 268 */         this.locationSkin = AbstractClientPlayer.func_110311_f(this.owner);
/* 269 */         this.downloadImageSkin = AbstractClientPlayer.func_110304_a(this.locationSkin, this.owner);
/*     */       }
/*     */     }
/*     */     
/*     */     public int updateWorshippersAndGetLevel() {
/* 274 */       double R = 8.0D;
/* 275 */       AxisAlignedBB bb = AxisAlignedBB.func_72330_a(this.field_145851_c + 0.5D - 8.0D, this.field_145848_d + 0.5D - 8.0D, this.field_145849_e + 0.5D - 8.0D, this.field_145851_c + 0.5D + 8.0D, this.field_145848_d + 0.5D + 8.0D, this.field_145849_e + 0.5D + 8.0D);
/*     */       
/* 277 */       List entities = this.field_145850_b.func_72872_a(EntityGoblin.class, bb);
/* 278 */       int worshipCount = 0;
/* 279 */       for (Object entity : entities) {
/* 280 */         if ((entity instanceof EntityGoblin)) {
/* 281 */           EntityGoblin goblin = (EntityGoblin)entity;
/* 282 */           if (goblin.isWorshipping()) {
/* 283 */             worshipCount++;
/*     */           } else {
/* 285 */             goblin.beginWorship(this);
/*     */           }
/*     */         }
/*     */       }
/* 289 */       return worshipCount;
/*     */     }
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 294 */       super.func_145845_h();
/* 295 */       if ((!this.field_145850_b.field_72995_K) && (hasOwner())) {
/* 296 */         int PULSE_INTERVAL_IN_SECS = 5;
/* 297 */         if (TimeUtil.secondsElapsed(5, this.ticks)) {
/* 298 */           int worshipCount = updateWorshippersAndGetLevel();
/*     */           
/* 300 */           EntityPlayer player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(this.owner);
/* 301 */           if (player != null) {
/* 302 */             NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 303 */             int WORSHIP_LEVEL_1 = 5;
/* 304 */             int WORSHIP_LEVEL_2 = 10;
/* 305 */             int WORSHIP_LEVEL_3 = 15;
/*     */             
/* 307 */             if (worshipCount >= 5) {
/* 308 */               int RECHARGE_RADIUS = 64;
/* 309 */               int RECHARGE_RADIUS_SQ = 4096;
/* 310 */               if (player.func_70092_e(0.5D + this.field_145851_c, 0.5D + this.field_145848_d, 0.5D + this.field_145849_e) <= 4096.0D) {
/* 311 */                 int currentEnergy = Infusion.getCurrentEnergy(player);
/* 312 */                 int maxEnergy = Infusion.getMaxEnergy(player);
/* 313 */                 if (currentEnergy < maxEnergy) {
/* 314 */                   int ENERGY_PER_PULSE = 30;
/* 315 */                   Infusion.setCurrentEnergy(player, Math.min(currentEnergy + 30, maxEnergy));
/* 316 */                   ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_PLING, player, 1.0D, 2.0D, 8);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/* 321 */             if (worshipCount >= 10)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 329 */               ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 330 */               if (playerEx != null) {
/* 331 */                 playerEx.addWorship(worshipCount >= 15 ? 1 : 0);
/*     */               }
/*     */             }
/*     */             
/* 335 */             double GODS_SUMMON_CHANCE = 0.01D * Config.instance().hobgoblinGodSpawnChance * 0.01D;
/*     */             
/*     */ 
/* 338 */             if ((worshipCount >= 15) && (Config.instance().hobgoblinGodSpawnChance > 0) && (this.field_145850_b.field_73012_v.nextDouble() < GODS_SUMMON_CHANCE))
/*     */             {
/* 340 */               summonGoblinGods(player, 64.0D, 16);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean summonGoblinGods(EntityPlayer player, double detectDistance, int spawnDistance) {
/* 348 */       double R2 = detectDistance;
/* 349 */       AxisAlignedBB bb2 = AxisAlignedBB.func_72330_a(this.field_145851_c + 0.5D - R2, this.field_145848_d + 0.5D - R2, this.field_145849_e + 0.5D - R2, this.field_145851_c + 0.5D + R2, this.field_145848_d + 0.5D + R2, this.field_145849_e + 0.5D + R2);
/*     */       
/* 351 */       if ((this.field_145850_b.func_72872_a(EntityGoblinMog.class, bb2).size() == 0) && (this.field_145850_b.func_72872_a(EntityGoblinGulg.class, bb2).size() == 0))
/*     */       {
/* 353 */         EntityCreature mog = InfusionInfernal.spawnCreature(this.field_145850_b, EntityGoblinMog.class, this.field_145851_c, this.field_145848_d, this.field_145849_e, player, 0, spawnDistance, ParticleEffect.FLAME, SoundEffect.MOB_WITHER_SPAWN);
/*     */         
/* 355 */         if (mog != null) {
/* 356 */           mog.func_110161_a(null);
/* 357 */           mog.func_70624_b(player);
/*     */           
/* 359 */           EntityCreature gulg = new EntityGoblinGulg(this.field_145850_b);
/* 360 */           gulg.func_70012_b(mog.field_70165_t, mog.field_70163_u, mog.field_70161_v, 0.0F, 0.0F);
/* 361 */           this.field_145850_b.func_72838_d(gulg);
/* 362 */           gulg.func_110161_a(null);
/* 363 */           gulg.func_70624_b(player);
/*     */           
/* 365 */           return true;
/*     */         }
/*     */       }
/*     */       
/* 369 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockStatueOfWorship.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */