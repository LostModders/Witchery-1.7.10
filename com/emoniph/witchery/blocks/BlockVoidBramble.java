/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.common.INullSource;
/*     */ import com.emoniph.witchery.common.PowerSources;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class BlockVoidBramble extends BlockBaseContainer
/*     */ {
/*     */   public BlockVoidBramble()
/*     */   {
/*  34 */     super(Material.field_151585_k, TileEntityVoidBramble.class);
/*     */     
/*  36 */     func_149722_s();
/*  37 */     func_149752_b(1000.0F);
/*  38 */     func_149715_a(0.125F);
/*  39 */     func_149672_a(Block.field_149779_h);
/*     */     
/*  41 */     float f = 0.45F;
/*  42 */     func_149676_a(0.050000012F, 0.0F, 0.050000012F, 0.95F, 1.0F, 0.95F);
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  47 */     return 6;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  52 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  62 */     return null;
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*     */   {
/*  67 */     if ((!world.field_72995_K) && 
/*  68 */       ((entity instanceof EntityLivingBase)) && 
/*  69 */       ((entity instanceof EntityLivingBase))) {
/*  70 */       teleportRandomly(world, posX, posY, posZ, entity, 500);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void teleportRandomly(World world, int posX, int posY, int posZ, Entity entity, int distance)
/*     */   {
/*  77 */     int doubleDistance = distance * 2;
/*     */     
/*     */ 
/*  80 */     posX += world.field_73012_v.nextInt(doubleDistance) - distance;
/*  81 */     posZ += world.field_73012_v.nextInt(doubleDistance) - distance;
/*  82 */     int maxY = Math.min(posY + 64, 250);
/*  83 */     while ((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) && (posY >= 0)) {
/*  84 */       posY--;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  90 */     while (((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) || (world.func_147439_a(posX, posY, posZ) == net.minecraft.init.Blocks.field_150357_h) || (!world.func_147437_c(posX, posY + 1, posZ)) || (!world.func_147437_c(posX, posY + 2, posZ)) || (!world.func_147437_c(posX, posY + 3, posZ))) && (posY < maxY)) {
/*  91 */       posY++;
/*     */     }
/*  93 */     if ((posY > 0) && (posY < maxY)) {
/*  94 */       ItemGeneral.teleportToLocation(world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, world.field_73011_w.field_76574_g, entity, true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 103 */     if (rand.nextInt(2) == 0) {
/* 104 */       double d0 = x + rand.nextFloat();
/* 105 */       double d1 = y + 0.15F + rand.nextFloat() * 0.3F + 0.5D;
/* 106 */       double d2 = z + rand.nextFloat();
/* 107 */       world.func_72869_a(ParticleEffect.PORTAL.toString(), d0, d1, d2, 0.0D, -1.2D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149699_a(World world, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 113 */     if (!world.field_72995_K) {
/* 114 */       TileEntityVoidBramble tile = (TileEntityVoidBramble)world.func_147438_o(x, y, z);
/* 115 */       if ((tile != null) && (
/* 116 */         (player.field_71075_bZ.field_75098_d) || (player.func_70005_c_().equals(tile.getOwner())))) {
/* 117 */         int dy = y;
/* 118 */         while (world.func_147439_a(x, dy, z) == this) {
/* 119 */           world.func_147468_f(x, dy, z);
/* 120 */           world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, new ItemStack(this)));
/* 121 */           dy++;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
/*     */   {
/* 130 */     if ((!world.field_72995_K) && ((entity instanceof EntityPlayer))) {
/* 131 */       EntityPlayer player = (EntityPlayer)entity;
/* 132 */       TileEntityVoidBramble tile = (TileEntityVoidBramble)world.func_147438_o(x, y, z);
/* 133 */       if (tile != null) {
/* 134 */         tile.setOwner(player.func_70005_c_());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/* 141 */     return new TileEntityVoidBramble();
/*     */   }
/*     */   
/*     */   public static class TileEntityVoidBramble extends TileEntityBase implements INullSource {
/*     */     private String owner;
/*     */     private static final String OWNER_KEY = "WITCPlacer";
/*     */     
/* 148 */     public boolean isPowerInvalid() { return func_145837_r(); }
/*     */     
/*     */ 
/*     */     protected void initiate()
/*     */     {
/* 153 */       super.initiate();
/* 154 */       if (!this.field_145850_b.field_72995_K) {
/* 155 */         if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == Witchery.Blocks.VOID_BRAMBLE) {
/* 156 */           Log.instance().debug("Initiating void bramble tile at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
/* 157 */           PowerSources.instance().registerNullSource(this);
/*     */         } else {
/* 159 */           Log.instance().warning("Void bramble tile entity exists without a corresponding block at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_145843_s()
/*     */     {
/* 167 */       super.func_145843_s();
/* 168 */       if (!this.field_145850_b.field_72995_K) {
/* 169 */         Log.instance().debug("Invalidating void bramble tile at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
/* 170 */         PowerSources.instance().removeNullSource(this);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public void setOwner(String username)
/*     */     {
/* 177 */       this.owner = username;
/*     */     }
/*     */     
/*     */     public String getOwner() {
/* 181 */       return this.owner != null ? this.owner : "";
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 188 */       super.func_145841_b(nbtTag);
/* 189 */       nbtTag.func_74778_a("WITCPlacer", getOwner());
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 194 */       super.func_145839_a(nbtTag);
/* 195 */       if (nbtTag.func_74764_b("WITCPlacer")) {
/* 196 */         this.owner = nbtTag.func_74779_i("WITCPlacer");
/*     */       } else {
/* 198 */         this.owner = "";
/*     */       }
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 204 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 205 */       func_145841_b(nbtTag);
/* 206 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 211 */       super.onDataPacket(net, packet);
/* 212 */       func_145839_a(packet.func_148857_g());
/* 213 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public World getWorld()
/*     */     {
/* 218 */       return this.field_145850_b;
/*     */     }
/*     */     
/*     */     public int getPosX()
/*     */     {
/* 223 */       return this.field_145851_c;
/*     */     }
/*     */     
/*     */     public int getPosY()
/*     */     {
/* 228 */       return this.field_145848_d;
/*     */     }
/*     */     
/*     */     public int getPosZ()
/*     */     {
/* 233 */       return this.field_145849_e;
/*     */     }
/*     */     
/*     */     public float getRange()
/*     */     {
/* 238 */       return 32.0F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockVoidBramble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */