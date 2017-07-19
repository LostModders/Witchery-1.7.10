/*     */ package com.emoniph.witchery.common;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerEventHooks;
/*     */ import com.emoniph.witchery.blocks.BlockDistillery.ContainerDistillery;
/*     */ import com.emoniph.witchery.blocks.BlockDistillery.TileEntityDistillery;
/*     */ import com.emoniph.witchery.blocks.BlockSpinningWheel.ContainerSpinningWheel;
/*     */ import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
/*     */ import com.emoniph.witchery.blocks.BlockWitchesOven.ContainerWitchesOven;
/*     */ import com.emoniph.witchery.blocks.BlockWitchesOven.TileEntityWitchesOven;
/*     */ import com.emoniph.witchery.brewing.DispersalTriggered.EventHooks;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions.EventHooks;
/*     */ import com.emoniph.witchery.entity.EntityBroom.EventHooks;
/*     */ import com.emoniph.witchery.infusion.Infusion.EventHooks;
/*     */ import com.emoniph.witchery.item.ItemBrewBag.ContainerBrewBag;
/*     */ import com.emoniph.witchery.item.ItemBrewBag.InventoryBrewBag;
/*     */ import com.emoniph.witchery.item.ItemGoblinClothes.EventHooks;
/*     */ import com.emoniph.witchery.item.ItemLeonardsUrn.ContainerLeonardsUrn;
/*     */ import com.emoniph.witchery.item.ItemLeonardsUrn.InventoryLeonardsUrn;
/*     */ import com.emoniph.witchery.item.ItemPoppet.PoppetEventHooks;
/*     */ import com.emoniph.witchery.item.ItemWitchHand.EventHooks;
/*     */ import com.emoniph.witchery.ritual.rites.RitePriorIncarnation.EventHooks;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.worldgen.WorldHandlerVillageDistrict.EventHooks;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.network.IGuiHandler;
/*     */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ public class CommonProxy
/*     */   implements IGuiHandler
/*     */ {
/*  40 */   private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap();
/*     */   
/*     */   public static void storeEntityData(String name, NBTTagCompound compound) {
/*  43 */     extendedEntityData.put(name, compound);
/*     */   }
/*     */   
/*     */   public static NBTTagCompound getEntityData(String name) {
/*  47 */     return (NBTTagCompound)extendedEntityData.remove(name);
/*     */   }
/*     */   
/*     */ 
/*     */   public void preInit() {}
/*     */   
/*     */   public void registerEvents()
/*     */   {
/*  55 */     MinecraftForge.EVENT_BUS.register(new ItemPoppet.PoppetEventHooks());
/*  56 */     MinecraftForge.EVENT_BUS.register(new Infusion.EventHooks());
/*  57 */     MinecraftForge.EVENT_BUS.register(new ItemWitchHand.EventHooks());
/*  58 */     MinecraftForge.EVENT_BUS.register(new EntityBroom.EventHooks());
/*  59 */     MinecraftForge.EVENT_BUS.register(new RitePriorIncarnation.EventHooks());
/*  60 */     MinecraftForge.EVENT_BUS.register(new BlockAreaMarker.AreaMarkerEventHooks());
/*  61 */     MinecraftForge.EVENT_BUS.register(new GenericEvents());
/*  62 */     MinecraftForge.EVENT_BUS.register(new ItemGoblinClothes.EventHooks());
/*  63 */     MinecraftForge.EVENT_BUS.register(new WitcheryPotions.EventHooks());
/*  64 */     MinecraftForge.EVENT_BUS.register(new DispersalTriggered.EventHooks());
/*  65 */     MinecraftForge.TERRAIN_GEN_BUS.register(new WorldHandlerVillageDistrict.EventHooks());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void registerRenderers() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void registerServerHandlers() {}
/*     */   
/*     */ 
/*     */   public void registerHandlers() {}
/*     */   
/*     */ 
/*     */   public void postInit() {}
/*     */   
/*     */ 
/*     */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/*  85 */     switch (ID) {
/*     */     case 0: 
/*  87 */       return null;
/*     */     
/*     */ 
/*     */     case 2: 
/*  91 */       return new BlockWitchesOven.ContainerWitchesOven(player.field_71071_by, (BlockWitchesOven.TileEntityWitchesOven)world.func_147438_o(x, y, z));
/*     */     case 3: 
/*  93 */       return new BlockDistillery.ContainerDistillery(player.field_71071_by, (BlockDistillery.TileEntityDistillery)world.func_147438_o(x, y, z));
/*     */     case 4: 
/*  95 */       return new BlockSpinningWheel.ContainerSpinningWheel(player.field_71071_by, (BlockSpinningWheel.TileEntitySpinningWheel)world.func_147438_o(x, y, z));
/*     */     
/*     */     case 5: 
/*  98 */       return new ItemBrewBag.ContainerBrewBag(player.field_71071_by, new ItemBrewBag.InventoryBrewBag(player), player.func_70694_bm());
/*     */     case 8: 
/* 100 */       return new ItemLeonardsUrn.ContainerLeonardsUrn(player.field_71071_by, new ItemLeonardsUrn.InventoryLeonardsUrn(player), player.func_70694_bm());
/*     */     }
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*     */   {
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   public boolean getGraphicsLevel() {
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public int getStockageRenderId()
/*     */   {
/* 116 */     return 0;
/*     */   }
/*     */   
/*     */   public int getGasRenderId() {
/* 120 */     return 0;
/*     */   }
/*     */   
/*     */   public int getPitGrassRenderId() {
/* 124 */     return 0;
/*     */   }
/*     */   
/*     */   public int getBrewLiquidRenderId() {
/* 128 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void registerVillagers() {}
/*     */   
/*     */ 
/*     */   public void generateParticle(World worldObj, double posX, double posY, double posZ, float f, float g, float h, int i, float j) {}
/*     */   
/*     */   public EntityPlayer getPlayer(MessageContext ctx)
/*     */   {
/* 139 */     if (ctx.side == Side.SERVER) {
/* 140 */       return ctx.getServerHandler().field_147369_b;
/*     */     }
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   public int getVineRenderId()
/*     */   {
/* 147 */     return 0;
/*     */   }
/*     */   
/*     */   public void showParticleEffect(World world, double x, double y, double z, double width, double height, SoundEffect sound, int color, ParticleEffect particle) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/CommonProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */