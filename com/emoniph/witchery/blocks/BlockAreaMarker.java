/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.ritual.Rite;
/*     */ import com.emoniph.witchery.ritual.rites.RiteTeleportEntity;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.eventhandler.EventPriority;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ 
/*     */ public class BlockAreaMarker extends BlockBaseContainer
/*     */ {
/*     */   public static class TileEntityAreaCurseProtect extends BlockAreaMarker.TileEntityAreaMarker
/*     */   {
/*     */     public boolean activateBlock(World world, int x, int y, int z, EntityPlayer player, int side)
/*     */     {
/*  43 */       return false;
/*     */     }
/*     */     
/*     */     protected boolean isProtected(EntityLivingBase entity, boolean killer, Rite rite)
/*     */     {
/*  48 */       return (!killer) && (Config.instance().allowDecurseDirected) && ((rite == null) || ((rite instanceof com.emoniph.witchery.ritual.rites.RiteCurseCreature)));
/*     */     }
/*     */     
/*     */     protected boolean isNear(EntityLivingBase entity)
/*     */     {
/*  53 */       int RADIUS = Config.instance().decurseDirectedRadius;
/*  54 */       int RADIUS_SQ = RADIUS * RADIUS;
/*  55 */       boolean inRange = Coord.distanceSq(entity.field_70165_t, 1.0D, entity.field_70161_v, this.field_145851_c, 1.0D, this.field_145849_e) <= RADIUS_SQ;
/*  56 */       return (inRange) && (this.field_145850_b.field_73011_w.field_76574_g == entity.field_71093_bK);
/*     */     }
/*     */     
/*     */     protected Block getExpectedBlockType()
/*     */     {
/*  61 */       return Witchery.Blocks.DECURSE_DIRECTED;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TileEntityAreaTeleportPullProtect extends BlockAreaMarker.TileEntityAreaMarker
/*     */   {
/*     */     public boolean activateBlock(World world, int x, int y, int z, EntityPlayer player, int side) {
/*  68 */       return false;
/*     */     }
/*     */     
/*     */     protected boolean isProtected(EntityLivingBase entity, boolean killer, Rite rite)
/*     */     {
/*  73 */       return (!killer) && (Config.instance().allowDecurseTeleport) && ((rite == null) || ((rite instanceof RiteTeleportEntity)));
/*     */     }
/*     */     
/*     */     protected boolean isNear(EntityLivingBase entity)
/*     */     {
/*  78 */       int RADIUS = Config.instance().decurseTeleportPullRadius;
/*  79 */       int RADIUS_SQ = RADIUS * RADIUS;
/*  80 */       boolean inRange = Coord.distanceSq(entity.field_70165_t, 1.0D, entity.field_70161_v, this.field_145851_c, 1.0D, this.field_145849_e) <= RADIUS_SQ;
/*  81 */       return (inRange) && (this.field_145850_b.field_73011_w.field_76574_g == entity.field_71093_bK);
/*     */     }
/*     */     
/*     */     protected Block getExpectedBlockType()
/*     */     {
/*  86 */       return Witchery.Blocks.DECURSE_TELEPORT;
/*     */     }
/*     */   }
/*     */   
/*     */   public BlockAreaMarker(Class<? extends TileEntityAreaMarker> clazzTile) {
/*  91 */     super(Material.field_151576_e, clazzTile);
/*     */     
/*  93 */     func_149722_s();
/*  94 */     func_149752_b(9999.0F);
/*  95 */     func_149711_c(2.5F);
/*  96 */     func_149672_a(field_149769_e);
/*  97 */     func_149676_a(0.15F, 0.0F, 0.15F, 0.85F, 0.5F, 0.85F);
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
/*     */   {
/* 102 */     int l = MathHelper.func_76128_c(entity.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/* 104 */     if (l == 0) {
/* 105 */       world.func_72921_c(x, y, z, 2, 2);
/*     */     }
/*     */     
/* 108 */     if (l == 1) {
/* 109 */       world.func_72921_c(x, y, z, 5, 2);
/*     */     }
/*     */     
/* 112 */     if (l == 2) {
/* 113 */       world.func_72921_c(x, y, z, 3, 2);
/*     */     }
/*     */     
/* 116 */     if (l == 3) {
/* 117 */       world.func_72921_c(x, y, z, 4, 2);
/*     */     }
/*     */     
/*     */ 
/* 121 */     if ((!world.field_72995_K) && ((entity instanceof EntityPlayer))) {
/* 122 */       EntityPlayer player = (EntityPlayer)entity;
/* 123 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 124 */       if ((tile != null) && ((tile instanceof TileEntityAreaMarker))) {
/* 125 */         TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
/* 126 */         marker.setOwner(player.func_70005_c_());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149699_a(World world, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 133 */     if (((!world.field_72995_K ? 1 : 0) & (player != null ? 1 : 0)) != 0) {
/* 134 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 135 */       if ((tile != null) && ((tile instanceof TileEntityAreaMarker))) {
/* 136 */         TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
/* 137 */         if ((player.field_71075_bZ.field_75098_d) || ((player.func_70005_c_().equals(marker.getOwner())) && (player.func_70093_af()))) {
/* 138 */           int dy = y;
/* 139 */           while (world.func_147439_a(x, dy, z) == this) {
/* 140 */             world.func_147468_f(x, dy, z);
/* 141 */             world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, new ItemStack(this)));
/* 142 */             dy++;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/* 152 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 153 */     if ((tile != null) && ((tile instanceof TileEntityAreaMarker))) {
/* 154 */       TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
/* 155 */       return marker.activateBlock(world, x, y, z, player, side);
/*     */     }
/* 157 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 163 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 168 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/* 172 */     return 0;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/* 177 */     return 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 183 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/* 194 */   public void func_149651_a(IIconRegister par1IconRegister) { this.field_149761_L = par1IconRegister.func_94245_a("stone"); }
/*     */   
/*     */   public static class AreaMarkerRegistry { private static AreaMarkerRegistry INSTANCE_CLIENT;
/*     */     private static AreaMarkerRegistry INSTANCE_SERVER;
/*     */     
/* 199 */     public static AreaMarkerRegistry instance() { if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
/* 200 */         return INSTANCE_SERVER;
/*     */       }
/*     */       
/* 203 */       return INSTANCE_CLIENT;
/*     */     }
/*     */     
/*     */     public static void serverStart() {
/* 207 */       INSTANCE_CLIENT = new AreaMarkerRegistry();
/* 208 */       INSTANCE_SERVER = new AreaMarkerRegistry();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 214 */     private final ArrayList<BlockAreaMarker.TileEntityAreaMarker> tiles = new ArrayList();
/*     */     
/*     */     private void add(BlockAreaMarker.TileEntityAreaMarker tile) {
/* 217 */       if (!this.tiles.contains(tile)) {
/*     */         try {
/* 219 */           Iterator<BlockAreaMarker.TileEntityAreaMarker> it = this.tiles.iterator();
/* 220 */           while (it.hasNext()) {
/* 221 */             BlockAreaMarker.TileEntityAreaMarker source = (BlockAreaMarker.TileEntityAreaMarker)it.next();
/*     */             
/* 223 */             if ((source == null) || (source.func_145837_r()) || ((source.field_145851_c == tile.field_145851_c) && (source.field_145848_d == tile.field_145848_d) && (source.field_145849_e == tile.field_145849_e))) {
/* 224 */               it.remove();
/*     */             }
/*     */           }
/*     */         } catch (Throwable e) {
/* 228 */           Log.instance().warning(e, "Exception occured validating existing power source entries");
/*     */         }
/* 230 */         this.tiles.add(tile);
/*     */       }
/*     */     }
/*     */     
/*     */     private void remove(BlockAreaMarker.TileEntityAreaMarker tile) {
/* 235 */       if (this.tiles.contains(tile)) {
/* 236 */         this.tiles.remove(tile);
/*     */       }
/*     */       try
/*     */       {
/* 240 */         Iterator<BlockAreaMarker.TileEntityAreaMarker> it = this.tiles.iterator();
/* 241 */         while (it.hasNext()) {
/* 242 */           BlockAreaMarker.TileEntityAreaMarker source = (BlockAreaMarker.TileEntityAreaMarker)it.next();
/*     */           
/* 244 */           if ((source == null) || (source.func_145837_r())) {
/* 245 */             it.remove();
/* 246 */           } else if (source.func_145831_w().func_147438_o(source.field_145851_c, source.field_145848_d, source.field_145849_e) != source) {
/* 247 */             it.remove();
/*     */           }
/*     */         }
/*     */       } catch (Throwable e) {
/* 251 */         Log.instance().warning(e, "Exception occured removing existing power source entries");
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean isProtectionActive(EntityLivingBase entity, Rite rite) {
/* 256 */       for (BlockAreaMarker.TileEntityAreaMarker tile : this.tiles) {
/* 257 */         if (tile.checkIsProtected(entity, rite)) {
/* 258 */           return true;
/*     */         }
/*     */       }
/* 261 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class TileEntityAreaMarker extends TileEntityBase
/*     */   {
/*     */     private static final String OWNER_KEY = "WITCPlacer";
/*     */     private String owner;
/*     */     
/*     */     protected void initiate()
/*     */     {
/* 272 */       super.initiate();
/* 273 */       if (!this.field_145850_b.field_72995_K) {
/* 274 */         if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == getExpectedBlockType()) {
/* 275 */           BlockAreaMarker.AreaMarkerRegistry.access$000(BlockAreaMarker.AreaMarkerRegistry.instance(), this);
/*     */         } else {
/* 277 */           Log.instance().warning("Area Marker tile entity exists without a corresponding block at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145843_s()
/*     */     {
/* 284 */       super.func_145843_s();
/* 285 */       if (!this.field_145850_b.field_72995_K) {
/* 286 */         BlockAreaMarker.AreaMarkerRegistry.access$100(BlockAreaMarker.AreaMarkerRegistry.instance(), this);
/*     */       }
/*     */     }
/*     */     
/*     */     public void setOwner(String username) {
/* 291 */       this.owner = username;
/*     */     }
/*     */     
/*     */     public String getOwner() {
/* 295 */       return this.owner != null ? this.owner : "";
/*     */     }
/*     */     
/* 298 */     private ArrayList<String> killers = new ArrayList();
/*     */     
/*     */     public void setKiller(EntityPlayer player) {
/* 301 */       String username = player.func_70005_c_();
/* 302 */       if (!this.killers.contains(username)) {
/* 303 */         this.killers.add(username);
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean checkIsProtected(EntityLivingBase entity, Rite rite) {
/* 308 */       if (isNear(entity)) {
/* 309 */         boolean killer = ((entity instanceof EntityPlayer)) && (this.killers.contains(entity.func_70005_c_()));
/* 310 */         return isProtected(entity, killer, rite);
/*     */       }
/* 312 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 318 */       super.func_145841_b(nbtTag);
/* 319 */       nbtTag.func_74778_a("WITCPlacer", getOwner());
/*     */       
/* 321 */       NBTTagList nbtKillers = new NBTTagList();
/* 322 */       for (String killer : this.killers) {
/* 323 */         nbtKillers.func_74742_a(new NBTTagString(killer));
/*     */       }
/* 325 */       nbtTag.func_74782_a("Killers", nbtKillers);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 330 */       super.func_145839_a(nbtTag);
/* 331 */       if (nbtTag.func_74764_b("WITCPlacer")) {
/* 332 */         this.owner = nbtTag.func_74779_i("WITCPlacer");
/*     */       } else {
/* 334 */         this.owner = "";
/*     */       }
/* 336 */       NBTTagList nbtKillers = nbtTag.func_150295_c("Killers", 8);
/* 337 */       int i = 0; for (int count = nbtKillers.func_74745_c(); i < count; i++)
/* 338 */         this.killers.add(nbtKillers.func_150307_f(i));
/*     */     }
/*     */     
/*     */     public abstract boolean activateBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityPlayer paramEntityPlayer, int paramInt4);
/*     */     
/*     */     protected abstract boolean isNear(EntityLivingBase paramEntityLivingBase);
/*     */     
/*     */     protected abstract boolean isProtected(EntityLivingBase paramEntityLivingBase, boolean paramBoolean, Rite paramRite);
/*     */     
/*     */     protected abstract Block getExpectedBlockType();
/*     */   }
/*     */   
/*     */   public static class AreaMarkerEventHooks { @SubscribeEvent(priority=EventPriority.NORMAL)
/*     */     public void onLivingDeath(LivingDeathEvent event) { EntityPlayer attacker;
/* 352 */       if ((!event.isCanceled()) && (!event.entityLiving.field_70170_p.field_72995_K) && ((event.entityLiving instanceof EntityPlayer)) && 
/* 353 */         (event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityPlayer)) && (event.source.func_76364_f() != event.entityLiving))
/*     */       {
/*     */ 
/* 356 */         attacker = (EntityPlayer)event.source.func_76364_f();
/* 357 */         for (BlockAreaMarker.TileEntityAreaMarker tile : BlockAreaMarker.AreaMarkerRegistry.access$200(BlockAreaMarker.AreaMarkerRegistry.instance())) {
/* 358 */           if (tile.isNear(attacker)) {
/* 359 */             tile.setKiller(attacker);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockAreaMarker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */