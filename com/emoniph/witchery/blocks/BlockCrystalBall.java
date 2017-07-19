/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
/*     */ import com.emoniph.witchery.predictions.PredictionManager;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCrystalBall extends BlockBaseContainer
/*     */ {
/*     */   private static final float ALTAR_POWER_PER_READING = 500.0F;
/*     */   private static final int POWER_SOURCE_RADIUS = 16;
/*     */   
/*     */   public BlockCrystalBall()
/*     */   {
/*  34 */     super(Material.field_151574_g, TileEntityCrystalBall.class);
/*     */     
/*  36 */     func_149711_c(2.0F);
/*  37 */     func_149672_a(field_149777_j);
/*  38 */     func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  45 */     if (!world.field_72995_K) {
/*  46 */       TileEntity tile = world.func_147438_o(x, y, z);
/*  47 */       if ((tile != null) && ((tile instanceof TileEntityCrystalBall))) {
/*  48 */         TileEntityCrystalBall ball = (TileEntityCrystalBall)tile;
/*  49 */         if (ball.canBeUsed()) {
/*  50 */           if (tryConsumePower(world, player, x, y, z)) {
/*  51 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(0.5D + x - 5.0D, 0.5D + y - 2.0D, 0.5D + z - 5.0D, 0.5D + x + 5.0D, 0.5D + y + 2.0D, 0.5D + z + 5.0D);
/*  52 */             List players = world.func_72872_a(EntityPlayer.class, bounds);
/*  53 */             EntityPlayer victim = player;
/*  54 */             double closest = 10000.0D;
/*  55 */             for (int i = 0; i < players.size(); i++) {
/*  56 */               EntityPlayer nearbyPlayer = players.get(i) != null ? (EntityPlayer)players.get(i) : null;
/*  57 */               if ((nearbyPlayer != null) && (nearbyPlayer != player)) {
/*  58 */                 double distSq = player.func_70092_e(0.5D + x, 0.5D + y, 0.5D + z);
/*  59 */                 if (distSq < closest) {
/*  60 */                   victim = nearbyPlayer;
/*  61 */                   closest = distSq;
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*  66 */             PredictionManager.instance().makePrediction(victim, player, true);
/*  67 */             ball.onUsed();
/*  68 */             ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_LEVELUP, world, 0.5D + x, 0.2D + y, 0.5D + z, 0.2D, 0.2D, 16);
/*     */           }
/*     */         } else {
/*  71 */           ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.prediction.recharging", new Object[0]);
/*  72 */           SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */         }
/*     */       }
/*     */       
/*  76 */       return true;
/*     */     }
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean tryConsumePower(World world, EntityPlayer player, int x, int y, int z) {
/*  82 */     IPowerSource powerSource = findNewPowerSource(world, x, y, z);
/*  83 */     if ((powerSource != null) && (powerSource.consumePower(500.0F))) {
/*  84 */       return true;
/*     */     }
/*  86 */     if (!world.field_72995_K) {
/*  87 */       ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.prediction.nopower", new Object[0]);
/*  88 */       SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */     }
/*  90 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
/*     */   {
/*  97 */     List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
/*     */     
/*  99 */     return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/* 114 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 119 */     func_111046_k(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
/* 123 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/* 124 */       func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/* 125 */       par1World.func_147468_f(par2, par3, par4);
/* 126 */       return false;
/*     */     }
/* 128 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149718_j(World world, int x, int y, int z)
/*     */   {
/* 134 */     Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
/* 135 */     return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 141 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */   public static class TileEntityCrystalBall
/*     */     extends TileEntity
/*     */   {
/* 151 */     private long lastUsedTime = 0L;
/*     */     
/*     */     public boolean canUpdate()
/*     */     {
/* 155 */       return false;
/*     */     }
/*     */     
/*     */     public void onUsed() {
/* 159 */       this.lastUsedTime = this.field_145850_b.func_82737_E();
/*     */     }
/*     */     
/*     */     public boolean canBeUsed() {
/* 163 */       return this.field_145850_b.func_82737_E() - this.lastUsedTime > 100L;
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 168 */       super.func_145841_b(nbtTag);
/*     */       
/* 170 */       nbtTag.func_74772_a("LastUsedTime", this.lastUsedTime);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 175 */       super.func_145839_a(nbtTag);
/*     */       
/* 177 */       this.lastUsedTime = nbtTag.func_74763_f("LastUsedTime");
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 182 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 183 */       func_145841_b(nbtTag);
/* 184 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 189 */       super.onDataPacket(net, packet);
/* 190 */       func_145839_a(packet.func_148857_g());
/* 191 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockCrystalBall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */