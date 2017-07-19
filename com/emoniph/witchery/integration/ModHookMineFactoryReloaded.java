/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockWitchCrop;
/*     */ import com.emoniph.witchery.entity.EntityBabaYaga;
/*     */ import com.emoniph.witchery.entity.EntityBanshee;
/*     */ import com.emoniph.witchery.entity.EntityCorpse;
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import com.emoniph.witchery.entity.EntityDeath;
/*     */ import com.emoniph.witchery.entity.EntityDeathsHorse;
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import com.emoniph.witchery.entity.EntityEnt;
/*     */ import com.emoniph.witchery.entity.EntityFamiliar;
/*     */ import com.emoniph.witchery.entity.EntityFollower;
/*     */ import com.emoniph.witchery.entity.EntityGoblinGulg;
/*     */ import com.emoniph.witchery.entity.EntityGoblinMog;
/*     */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*     */ import com.emoniph.witchery.entity.EntityIllusionCreeper;
/*     */ import com.emoniph.witchery.entity.EntityIllusionSpider;
/*     */ import com.emoniph.witchery.entity.EntityIllusionZombie;
/*     */ import com.emoniph.witchery.entity.EntityLeonard;
/*     */ import com.emoniph.witchery.entity.EntityLilith;
/*     */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*     */ import com.emoniph.witchery.entity.EntityLostSoul;
/*     */ import com.emoniph.witchery.entity.EntityMindrake;
/*     */ import com.emoniph.witchery.entity.EntityNightmare;
/*     */ import com.emoniph.witchery.entity.EntityPoltergeist;
/*     */ import com.emoniph.witchery.entity.EntityReflection;
/*     */ import com.emoniph.witchery.entity.EntitySpectre;
/*     */ import com.emoniph.witchery.entity.EntitySpirit;
/*     */ import com.emoniph.witchery.entity.EntityVampire;
/*     */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import powercrystals.minefactoryreloaded.api.FactoryRegistry;
/*     */ 
/*     */ public class ModHookMineFactoryReloaded extends ModHook
/*     */ {
/*     */   public String getModID()
/*     */   {
/*  49 */     return "MineFactoryReloaded";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doInit() {}
/*     */   
/*     */ 
/*     */   protected void doPostInit() {}
/*     */   
/*     */ 
/*     */   protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
/*     */   
/*     */ 
/*     */   public static class IntegrateMineFactoryReloaded
/*     */   {
/*     */     private static NBTTagCompound getFertilizableCrop(BlockWitchCrop crop)
/*     */     {
/*  67 */       NBTTagCompound nbtRoot = new NBTTagCompound();
/*  68 */       nbtRoot.func_74778_a("plant", Block.field_149771_c.func_148750_c(crop));
/*  69 */       nbtRoot.func_74768_a("meta", crop.getNumGrowthStages());
/*  70 */       return nbtRoot;
/*     */     }
/*     */     
/*     */     private static NBTTagCompound getPlantableCrop(BlockWitchCrop crop, Item seeds) {
/*  74 */       NBTTagCompound nbtRoot = new NBTTagCompound();
/*  75 */       nbtRoot.func_74778_a("seed", Item.field_150901_e.func_148750_c(seeds));
/*  76 */       nbtRoot.func_74778_a("crop", Block.field_149771_c.func_148750_c(crop));
/*  77 */       return nbtRoot;
/*     */     }
/*     */     
/*     */     private static NBTTagCompound getPlantableSapling(Block sapling) {
/*  81 */       NBTTagCompound nbtRoot = new NBTTagCompound();
/*  82 */       nbtRoot.func_74778_a("sapling", Block.field_149771_c.func_148750_c(sapling));
/*  83 */       return nbtRoot;
/*     */     }
/*     */     
/*     */     private static NBTTagCompound getFertilizableSapling(Block sapling) {
/*  87 */       NBTTagCompound nbtRoot = new NBTTagCompound();
/*  88 */       nbtRoot.func_74778_a("plant", Block.field_149771_c.func_148750_c(sapling));
/*  89 */       return nbtRoot;
/*     */     }
/*     */     
/*     */     public static void register()
/*     */     {
/*  94 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_ARTICHOKE, 1, Witchery.Blocks.CROP_ARTICHOKE.getNumGrowthStages()));
/*     */       
/*  96 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_MANDRAKE, 1, Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages()));
/*     */       
/*  98 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_BELLADONNA, 1, Witchery.Blocks.CROP_BELLADONNA.getNumGrowthStages()));
/*     */       
/* 100 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_SNOWBELL, 1, Witchery.Blocks.CROP_SNOWBELL.getNumGrowthStages()));
/*     */       
/* 102 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_WORMWOOD, 1, Witchery.Blocks.CROP_WORMWOOD.getNumGrowthStages()));
/*     */       
/* 104 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_MINDRAKE, 1, Witchery.Blocks.CROP_MINDRAKE.getNumGrowthStages()));
/*     */       
/* 106 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_WOLFSBANE, 1, Witchery.Blocks.CROP_WOLFSBANE.getNumGrowthStages()));
/*     */       
/* 108 */       FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(Witchery.Blocks.CROP_GARLIC, 1, Witchery.Blocks.CROP_GARLIC.getNumGrowthStages()));
/*     */       
/*     */ 
/* 111 */       FactoryRegistry.sendMessage("registerHarvestable_Log", Block.field_149771_c.func_148750_c(Witchery.Blocks.LOG));
/* 112 */       FactoryRegistry.sendMessage("registerHarvestable_Leaves", Block.field_149771_c.func_148750_c(Witchery.Blocks.LEAVES));
/*     */       
/* 114 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_ARTICHOKE));
/* 115 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_MANDRAKE));
/* 116 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_BELLADONNA));
/* 117 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_SNOWBELL));
/* 118 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_WORMWOOD));
/* 119 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_MINDRAKE));
/* 120 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_WOLFSBANE));
/* 121 */       FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(Witchery.Blocks.CROP_GARLIC));
/*     */       
/* 123 */       FactoryRegistry.sendMessage("registerFertilizable_Standard", getFertilizableSapling(Witchery.Blocks.SAPLING));
/*     */       
/* 125 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_ARTICHOKE, Witchery.Items.SEEDS_ARTICHOKE));
/*     */       
/* 127 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_MANDRAKE, Witchery.Items.SEEDS_MANDRAKE));
/*     */       
/* 129 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_BELLADONNA, Witchery.Items.SEEDS_BELLADONNA));
/*     */       
/* 131 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_SNOWBELL, Witchery.Items.SEEDS_SNOWBELL));
/*     */       
/* 133 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_WORMWOOD, Witchery.Items.SEEDS_WORMWOOD));
/*     */       
/* 135 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_MINDRAKE, Witchery.Items.SEEDS_MINDRAKE));
/*     */       
/* 137 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_WOLFSBANE, Witchery.Items.SEEDS_WOLFSBANE));
/*     */       
/* 139 */       FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(Witchery.Blocks.CROP_GARLIC, Witchery.Items.SEEDS_GARLIC));
/*     */       
/*     */ 
/* 142 */       FactoryRegistry.sendMessage("registerPlantable_Sapling", getPlantableSapling(Witchery.Blocks.SAPLING));
/*     */       try
/*     */       {
/* 145 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityCovenWitch.class);
/* 146 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityNightmare.class);
/* 147 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityDemon.class);
/* 148 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityEnt.class);
/* 149 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityBabaYaga.class);
/* 150 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityHornedHuntsman.class);
/* 151 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityIllusionSpider.class);
/* 152 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityIllusionZombie.class);
/* 153 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityIllusionCreeper.class);
/* 154 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityFamiliar.class);
/* 155 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityCorpse.class);
/* 156 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntitySpirit.class);
/* 157 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntitySpectre.class);
/* 158 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityPoltergeist.class);
/* 159 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityBanshee.class);
/* 160 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityDeath.class);
/* 161 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityWitchHunter.class);
/* 162 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", com.emoniph.witchery.entity.EntityImp.class);
/* 163 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLordOfTorment.class);
/* 164 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityGoblinMog.class);
/* 165 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityGoblinGulg.class);
/* 166 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityDeathsHorse.class);
/* 167 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLeonard.class);
/* 168 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLostSoul.class);
/* 169 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityVampire.class);
/* 170 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityWolfman.class);
/* 171 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLilith.class);
/* 172 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityFollower.class);
/* 173 */         FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityReflection.class);
/*     */       } catch (Throwable e) {
/* 175 */         Log.instance().warning(e, "Exception occurred setting up MFR grinder blacklist");
/*     */       }
/*     */       try
/*     */       {
/* 179 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityCovenWitch.class);
/* 180 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityNightmare.class);
/* 181 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityDemon.class);
/* 182 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityEnt.class);
/* 183 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityBabaYaga.class);
/* 184 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityHornedHuntsman.class);
/* 185 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityIllusionCreeper.class);
/* 186 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityIllusionSpider.class);
/* 187 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityIllusionZombie.class);
/* 188 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityFamiliar.class);
/* 189 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityCorpse.class);
/* 190 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntitySpirit.class);
/* 191 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntitySpectre.class);
/* 192 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityBanshee.class);
/* 193 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityPoltergeist.class);
/* 194 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityDeath.class);
/* 195 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLordOfTorment.class);
/* 196 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityGoblinMog.class);
/* 197 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityGoblinGulg.class);
/* 198 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityDeathsHorse.class);
/* 199 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLeonard.class);
/* 200 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLostSoul.class);
/* 201 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLilith.class);
/* 202 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityFollower.class);
/* 203 */         FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityReflection.class);
/*     */       } catch (Throwable e) {
/* 205 */         Log.instance().warning(e, "Exception occurred setting up MFR safari net blacklist");
/*     */       }
/*     */       
/*     */       try
/*     */       {
/* 210 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityCovenWitch.class);
/* 211 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityNightmare.class);
/* 212 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityDemon.class);
/* 213 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityEnt.class);
/* 214 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityBabaYaga.class);
/* 215 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityHornedHuntsman.class);
/* 216 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityIllusionCreeper.class);
/* 217 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityIllusionSpider.class);
/* 218 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityIllusionZombie.class);
/* 219 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityFamiliar.class);
/* 220 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntitySpirit.class);
/* 221 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntitySpectre.class);
/* 222 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityBanshee.class);
/* 223 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityPoltergeist.class);
/* 224 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityDeath.class);
/* 225 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityWitchHunter.class);
/* 226 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityMindrake.class);
/* 227 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", com.emoniph.witchery.entity.EntityImp.class);
/* 228 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLordOfTorment.class);
/* 229 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityMindrake.class);
/* 230 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityDeathsHorse.class);
/* 231 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", com.emoniph.witchery.entity.EntityGoblin.class);
/* 232 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityGoblinGulg.class);
/* 233 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityGoblinMog.class);
/* 234 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLeonard.class);
/* 235 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLostSoul.class);
/* 236 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityWolfman.class);
/* 237 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityVampire.class);
/* 238 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLilith.class);
/* 239 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityFollower.class);
/* 240 */         FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityReflection.class);
/*     */       }
/*     */       catch (Throwable e) {
/* 243 */         Log.instance().warning(e, "Exception occurred setting up MFR autospawner blacklist");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookMineFactoryReloaded.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */