/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.DreamWeave;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class BlockDreamCatcher extends BlockBaseContainer
/*     */ {
/*     */   public BlockDreamCatcher()
/*     */   {
/*  37 */     super(Material.field_151582_l, TileEntityDreamCatcher.class);
/*  38 */     this.registerWithCreateTab = false;
/*     */     
/*  40 */     func_149649_H();
/*  41 */     func_149711_c(1.0F);
/*  42 */     func_149672_a(field_149766_f);
/*     */     
/*  44 */     float f = 0.25F;
/*  45 */     float f1 = 1.0F;
/*  46 */     func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  52 */     return net.minecraft.init.Blocks.field_150344_f.func_149733_h(par1);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/*  63 */     func_149719_a(par1World, par2, par3, par4);
/*  64 */     return super.func_149633_g(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ)
/*     */   {
/*  69 */     int side = par1IBlockAccess.func_72805_g(posX, posY, posZ);
/*  70 */     float bottom = 0.28125F;
/*  71 */     float top = 0.78125F;
/*  72 */     float left = 0.0F;
/*  73 */     float width = 1.0F;
/*  74 */     float depth = 0.125F;
/*  75 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  77 */     float minY = 0.0F;
/*  78 */     float maxY = 0.87F;
/*  79 */     float minX = 0.0F;
/*  80 */     float maxX = 0.08F;
/*  81 */     float minZ = 0.25F;
/*  82 */     float maxZ = 0.75F;
/*     */     
/*  84 */     if (side == 2) {
/*  85 */       func_149676_a(minZ, minY, 1.0F - minX, maxZ, maxY, 1.0F - maxX);
/*  86 */     } else if (side == 3) {
/*  87 */       func_149676_a(1.0F - maxZ, minY, minX, 1.0F - minZ, maxY, maxX);
/*  88 */     } else if (side == 4) {
/*  89 */       func_149676_a(1.0F - minX, minY, minZ, 1.0F - maxX, maxY, maxZ);
/*  90 */     } else if (side == 5) {
/*  91 */       func_149676_a(minX, minY, minZ, maxX, maxY, maxZ);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*     */   {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 112 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149749_a(World world, int posX, int posY, int posZ, Block par5, int par6)
/*     */   {
/* 118 */     if (!world.field_72995_K) {
/* 119 */       TileEntity tileEntity = world.func_147438_o(posX, posY, posZ);
/* 120 */       if ((tileEntity != null) && ((tileEntity instanceof TileEntityDreamCatcher))) {
/* 121 */         TileEntityDreamCatcher tileEntityDreamCatcher = (TileEntityDreamCatcher)tileEntity;
/*     */         
/* 123 */         ItemGeneral.DreamWeave weave = tileEntityDreamCatcher.getWeave();
/* 124 */         if (weave != null) {
/* 125 */           world.func_72838_d(new EntityItem(world, posX, posY, posZ, weave.createStack()));
/*     */         }
/*     */       }
/*     */     }
/* 129 */     super.func_149749_a(world, posX, posY, posZ, par5, par6);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int posX, int posY, int posZ, int metadata, int fortune)
/*     */   {
/* 134 */     ArrayList<ItemStack> ret = new ArrayList();
/* 135 */     return ret;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int posX, int posY, int posZ, Block par5)
/*     */   {
/* 140 */     int metadata = world.func_72805_g(posX, posY, posZ);
/* 141 */     boolean flag = true;
/*     */     
/* 143 */     if ((metadata == 2) && (world.func_147439_a(posX, posY, posZ + 1).func_149688_o().func_76220_a())) {
/* 144 */       flag = false;
/*     */     }
/*     */     
/* 147 */     if ((metadata == 3) && (world.func_147439_a(posX, posY, posZ - 1).func_149688_o().func_76220_a())) {
/* 148 */       flag = false;
/*     */     }
/*     */     
/* 151 */     if ((metadata == 4) && (world.func_147439_a(posX + 1, posY, posZ).func_149688_o().func_76220_a())) {
/* 152 */       flag = false;
/*     */     }
/*     */     
/* 155 */     if ((metadata == 5) && (world.func_147439_a(posX - 1, posY, posZ).func_149688_o().func_76220_a())) {
/* 156 */       flag = false;
/*     */     }
/*     */     
/* 159 */     if (flag) {
/* 160 */       func_149697_b(world, posX, posY, posZ, world.func_72805_g(posX, posY, posZ), 0);
/* 161 */       world.func_147468_f(posX, posY, posZ);
/*     */     }
/*     */     
/* 164 */     super.func_149695_a(world, posX, posY, posZ, par5);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister) {}
/*     */   
/*     */   public static boolean causesNightmares(World world, int posX, int posY, int posZ)
/*     */   {
/* 173 */     TileEntity tileEntity = world.func_147438_o(posX, posY, posZ);
/* 174 */     if ((tileEntity != null) && ((tileEntity instanceof TileEntityDreamCatcher))) {
/* 175 */       TileEntityDreamCatcher tileEntityDreamCatcher = (TileEntityDreamCatcher)tileEntity;
/* 176 */       return tileEntityDreamCatcher.dreamWeave == Witchery.Items.GENERIC.itemDreamNightmare;
/*     */     }
/* 178 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean enhancesDreams(World world, int x, int y, int z)
/*     */   {
/* 183 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/* 184 */     if ((tileEntity != null) && ((tileEntity instanceof TileEntityDreamCatcher))) {
/* 185 */       TileEntityDreamCatcher tileEntityDreamCatcher = (TileEntityDreamCatcher)tileEntity;
/* 186 */       return tileEntityDreamCatcher.dreamWeave == Witchery.Items.GENERIC.itemDreamIntensity;
/*     */     }
/* 188 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/* 194 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/* 195 */     if ((tileEntity != null) && ((tileEntity instanceof TileEntityDreamCatcher))) {
/* 196 */       TileEntityDreamCatcher catcherEntity = (TileEntityDreamCatcher)tileEntity;
/* 197 */       if (catcherEntity.getWeave() != null) {
/* 198 */         return catcherEntity.getWeave().createStack();
/*     */       }
/*     */     }
/*     */     
/* 202 */     return Witchery.Items.GENERIC.itemDreamMove.createStack();
/*     */   }
/*     */   
/*     */   public static class TileEntityDreamCatcher extends TileEntity {
/*     */     private boolean buffIfDay;
/*     */     private boolean buffIfNight;
/*     */     private ItemGeneral.DreamWeave dreamWeave;
/*     */     private static final String DREAM_WEAVE_KEY = "WITCWeaveID";
/*     */     
/*     */     public void setEffect(ItemGeneral.DreamWeave dreamWeave) {
/* 212 */       this.dreamWeave = dreamWeave;
/* 213 */       if (!this.field_145850_b.field_72995_K) {
/* 214 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */     
/*     */     public ItemGeneral.DreamWeave getWeave() {
/* 219 */       return this.dreamWeave;
/*     */     }
/*     */     
/*     */     public void func_145845_h()
/*     */     {
/* 224 */       super.func_145845_h();
/*     */       
/* 226 */       if ((!this.field_145850_b.field_72995_K) && (this.dreamWeave != null)) {
/* 227 */         if ((this.buffIfDay) || (this.buffIfNight)) {
/* 228 */           boolean day = this.field_145850_b.func_72935_r();
/* 229 */           boolean isDream; boolean isEnhanced; if (((this.buffIfDay) && (day)) || ((this.buffIfNight) && (!day))) {
/* 230 */             isDream = true;
/* 231 */             isEnhanced = false;
/* 232 */             int r = 5;
/*     */             
/* 234 */             boolean done = false;
/* 235 */             for (int y = this.field_145848_d - 5; (y <= this.field_145848_d + 5) && (!done); y++) {
/* 236 */               for (int x = this.field_145851_c - 5; (x <= this.field_145851_c + 5) && (!done); x++) {
/* 237 */                 for (int z = this.field_145849_e - 5; (z <= this.field_145849_e + 5) && (!done); z++) {
/* 238 */                   if (((y != this.field_145848_d) || (x != this.field_145851_c) || (z != this.field_145849_e)) && 
/* 239 */                     (this.field_145850_b.func_147439_a(x, y, z) == Witchery.Blocks.DREAM_CATCHER)) {
/* 240 */                     if (BlockDreamCatcher.causesNightmares(this.field_145850_b, x, y, z)) {
/* 241 */                       isDream = false;
/* 242 */                       done = isEnhanced;
/* 243 */                     } else if (BlockDreamCatcher.enhancesDreams(this.field_145850_b, x, y, z)) {
/* 244 */                       isEnhanced = true;
/* 245 */                       done = !isDream;
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */ 
/* 253 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_145851_c - 5, this.field_145848_d - 5, this.field_145849_e - 5, this.field_145851_c + 5, this.field_145848_d + 5, this.field_145849_e + 5);
/*     */             
/* 255 */             List<EntityPlayer> list = this.field_145850_b.func_72872_a(EntityPlayer.class, bounds);
/* 256 */             for (EntityPlayer player : list) {
/* 257 */               ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 258 */               if (((day) && (!playerEx.isVampire())) || ((!day) && (playerEx.isVampire()))) {
/* 259 */                 this.dreamWeave.applyEffect(player, isDream, isEnhanced);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 265 */           this.buffIfDay = (this.buffIfNight = 0);
/*     */         }
/*     */         
/* 268 */         if ((!this.buffIfDay) && (!this.buffIfNight) && (areAllPlayersAsleep(this.field_145850_b))) {
/* 269 */           this.buffIfDay = (!this.field_145850_b.field_73011_w.isDaytime());
/* 270 */           this.buffIfNight = (!this.buffIfDay);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private boolean areAllPlayersAsleep(World world)
/*     */     {
/* 277 */       Iterator iterator = world.field_73010_i.iterator();
/*     */       
/* 279 */       int sleepThreshold = MathHelper.func_76141_d(0.01F * Config.instance().percentageOfPlayersSleepingForBuff * world.field_73010_i.size());
/*     */       
/* 281 */       while (iterator.hasNext()) {
/* 282 */         EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*     */         
/* 284 */         if (entityplayer.func_70608_bn()) {
/* 285 */           sleepThreshold--; if (sleepThreshold <= 0) {
/* 286 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 293 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 300 */       super.func_145841_b(nbtTag);
/* 301 */       if (this.dreamWeave != null) {
/* 302 */         nbtTag.func_74768_a("WITCWeaveID", this.dreamWeave.weaveID);
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 308 */       super.func_145839_a(nbtTag);
/* 309 */       if (nbtTag.func_74764_b("WITCWeaveID")) {
/* 310 */         int dreamWeaveID = nbtTag.func_74762_e("WITCWeaveID");
/* 311 */         this.dreamWeave = ((ItemGeneral.DreamWeave)Witchery.Items.GENERIC.weaves.get(dreamWeaveID));
/*     */       }
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 317 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 318 */       func_145841_b(nbtTag);
/* 319 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 324 */       super.onDataPacket(net, packet);
/* 325 */       func_145839_a(packet.func_148857_g());
/* 326 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockDreamCatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */