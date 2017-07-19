/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockPitGrass;
/*     */ import com.emoniph.witchery.blocks.BlockPlantMine;
/*     */ import com.emoniph.witchery.blocks.BlockWitchDoor;
/*     */ import com.emoniph.witchery.entity.EntityIllusionCreeper;
/*     */ import com.emoniph.witchery.entity.EntityIllusionSpider;
/*     */ import com.emoniph.witchery.entity.EntityIllusionZombie;
/*     */ import com.emoniph.witchery.entity.EntityVillagerWere;
/*     */ import java.util.List;
/*     */ import mcp.mobius.waila.api.IWailaConfigHandler;
/*     */ import mcp.mobius.waila.api.IWailaDataAccessor;
/*     */ import mcp.mobius.waila.api.IWailaDataProvider;
/*     */ import mcp.mobius.waila.api.IWailaEntityAccessor;
/*     */ import mcp.mobius.waila.api.IWailaEntityProvider;
/*     */ import mcp.mobius.waila.api.IWailaRegistrar;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ModHookWailaRegistrar implements IWailaDataProvider, IWailaEntityProvider
/*     */ {
/*     */   public static void callbackRegister(IWailaRegistrar registrar)
/*     */   {
/*  32 */     ModHookWailaRegistrar provider = new ModHookWailaRegistrar();
/*  33 */     registrar.registerStackProvider(provider, BlockPlantMine.class);
/*  34 */     registrar.registerStackProvider(provider, BlockWitchDoor.class);
/*  35 */     registrar.registerStackProvider(provider, com.emoniph.witchery.blocks.BlockPitDirt.class);
/*  36 */     registrar.registerStackProvider(provider, BlockPitGrass.class);
/*  37 */     registrar.registerOverrideEntityProvider(provider, EntityIllusionCreeper.class);
/*  38 */     registrar.registerOverrideEntityProvider(provider, EntityIllusionSpider.class);
/*  39 */     registrar.registerOverrideEntityProvider(provider, EntityIllusionZombie.class);
/*  40 */     registrar.registerOverrideEntityProvider(provider, EntityVillagerWere.class);
/*     */   }
/*     */   
/*  43 */   private static final ItemStack yellowPlant = new ItemStack(Blocks.field_150327_N);
/*  44 */   private static final ItemStack redPlant = new ItemStack(Blocks.field_150328_O);
/*  45 */   private static final ItemStack shrubPlant = new ItemStack(Blocks.field_150330_I);
/*  46 */   private static final ItemStack door = new ItemStack(Items.field_151135_aq);
/*  47 */   private static final ItemStack dirt = new ItemStack(Blocks.field_150346_d);
/*  48 */   private static final ItemStack grass = new ItemStack(Blocks.field_150349_c);
/*  49 */   private static final ItemStack rowandoor = new ItemStack(Witchery.Blocks.DOOR_ROWAN);
/*     */   private static Entity CREEPER;
/*     */   private static Entity ZOMBIE;
/*     */   
/*  53 */   public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) { if (accessor.getBlock() == Witchery.Blocks.TRAPPED_PLANT) {
/*  54 */       int foundMeta = accessor.getMetadata();
/*  55 */       if ((foundMeta == 5) || (foundMeta == 6) || (foundMeta == 7) || (foundMeta == 4))
/*     */       {
/*     */ 
/*     */ 
/*  59 */         return yellowPlant; }
/*  60 */       if ((foundMeta == 1) || (foundMeta == 2) || (foundMeta == 3) || (foundMeta == 0))
/*     */       {
/*     */ 
/*  63 */         return redPlant; }
/*  64 */       if ((foundMeta == 9) || (foundMeta == 10) || (foundMeta == 11) || (foundMeta == 8))
/*     */       {
/*     */ 
/*  67 */         return shrubPlant; }
/*     */     } else {
/*  69 */       if (accessor.getBlock() == Witchery.Blocks.DOOR_ALDER)
/*  70 */         return door;
/*  71 */       if (accessor.getBlock() == Witchery.Blocks.DOOR_ROWAN)
/*  72 */         return rowandoor;
/*  73 */       if (accessor.getBlock() == Witchery.Blocks.PIT_DIRT)
/*  74 */         return dirt;
/*  75 */       if (accessor.getBlock() == Witchery.Blocks.PIT_GRASS)
/*  76 */         return grass;
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
/*     */   {
/*  84 */     return currenttip;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
/*     */   {
/*  90 */     return currenttip;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
/*     */   {
/*  97 */     return currenttip;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static Entity SPIDER;
/*     */   
/*     */   private static EntityVillager VILLAGER;
/*     */   
/*     */   public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config)
/*     */   {
/* 108 */     if ((accessor.getEntity() instanceof EntityIllusionCreeper)) {
/* 109 */       if ((CREEPER == null) || (CREEPER.field_70170_p != accessor.getWorld())) {
/* 110 */         CREEPER = new EntityCreeper(accessor.getWorld());
/*     */       }
/* 112 */       return CREEPER; }
/* 113 */     if ((accessor.getEntity() instanceof EntityIllusionZombie)) {
/* 114 */       if ((ZOMBIE == null) || (ZOMBIE.field_70170_p != accessor.getWorld())) {
/* 115 */         ZOMBIE = new EntityZombie(accessor.getWorld());
/*     */       }
/* 117 */       return ZOMBIE; }
/* 118 */     if ((accessor.getEntity() instanceof EntityIllusionSpider)) {
/* 119 */       if ((SPIDER == null) || (SPIDER.field_70170_p != accessor.getWorld())) {
/* 120 */         SPIDER = new EntitySpider(accessor.getWorld());
/*     */       }
/* 122 */       return SPIDER; }
/* 123 */     if ((accessor.getEntity() instanceof EntityVillagerWere)) {
/* 124 */       EntityVillagerWere were = (EntityVillagerWere)accessor.getEntity();
/* 125 */       if ((VILLAGER == null) || (VILLAGER.field_70170_p != accessor.getWorld())) {
/* 126 */         VILLAGER = new EntityVillager(accessor.getWorld());
/*     */       }
/* 128 */       VILLAGER.func_70938_b(were.func_70946_n());
/* 129 */       return VILLAGER;
/*     */     }
/* 131 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
/*     */   {
/* 137 */     return currenttip;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
/*     */   {
/* 143 */     return currenttip;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
/*     */   {
/* 149 */     return currenttip;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookWailaRegistrar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */