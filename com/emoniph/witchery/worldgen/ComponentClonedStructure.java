/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemDuplicationStaff.Rotation;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ 
/*     */ public abstract class ComponentClonedStructure extends WitcheryComponent
/*     */ {
/*     */   private ItemDuplicationStaff.Rotation rotation;
/*     */   
/*     */   public ComponentClonedStructure() {}
/*     */   
/*     */   public ComponentClonedStructure(int direction, Random random, int x, int z, int w, int h, int d)
/*     */   {
/*  26 */     super(direction, random, x, z, w, h, d);
/*  27 */     this.rotation = ItemDuplicationStaff.Rotation.values()[direction];
/*     */   }
/*     */   
/*     */   public boolean addComponentParts(World world, Random random)
/*     */   {
/*  32 */     BiomeGenBase biom = world.func_72807_a(func_74865_a(0, 0), func_74873_b(0, 0));
/*  33 */     int groundAvg = calcGroundHeight(world, this.field_74887_e);
/*     */     
/*  35 */     if (groundAvg < 0) {
/*  36 */       return true;
/*     */     }
/*     */     
/*  39 */     this.field_74887_e.func_78886_a(0, groundAvg - this.field_74887_e.field_78894_e + this.field_74887_e.func_78882_c() - 1, 0);
/*     */     
/*  41 */     if ((isWaterBelow(world, 0, -1, 0, this.field_74887_e)) || (isWaterBelow(world, 0, -1, this.field_74887_e.func_78880_d() - 1, this.field_74887_e)) || (isWaterBelow(world, this.field_74887_e.func_78883_b() - 1, -1, 0, this.field_74887_e)) || (isWaterBelow(world, this.field_74887_e.func_78883_b() - 1, -1, this.field_74887_e.func_78880_d() - 1, this.field_74887_e)))
/*     */     {
/*  43 */       return false;
/*     */     }
/*     */     
/*  46 */     Block groundID = Blocks.field_150349_c;
/*  47 */     Block undergroundID = Blocks.field_150346_d;
/*  48 */     if ((biom.field_76756_M == BiomeGenBase.field_76769_d.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76786_s.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76787_r.field_76756_M))
/*     */     {
/*  50 */       groundID = Blocks.field_150354_m;
/*  51 */       undergroundID = Blocks.field_150354_m;
/*     */     }
/*     */     
/*     */ 
/*  55 */     NBTTagCompound nbtSchematic = getSchematic(world, random);
/*  56 */     com.emoniph.witchery.item.ItemDuplicationStaff.drawSchematicInWorld(world, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.rotation, true, nbtSchematic);
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
/*  70 */     for (int i = 0; i < this.field_74887_e.func_78883_b(); i++) {
/*  71 */       for (int j = 0; j < this.field_74887_e.func_78880_d(); j++)
/*     */       {
/*     */ 
/*  74 */         func_151554_b(world, undergroundID, 0, j, 0, i, this.field_74887_e);
/*     */       }
/*     */     }
/*     */     
/*  78 */     spawnWitches(world, this.field_74887_e, this.field_74887_e.func_78883_b() - 3, 1, 3, 1);
/*     */     
/*  80 */     return true;
/*     */   }
/*     */   
/*     */   protected abstract NBTTagCompound getSchematic(World paramWorld, Random paramRandom);
/*     */   
/*  85 */   private int witchesSpawned = 0;
/*     */   
/*     */   private void spawnWitches(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {
/*  88 */     if (this.witchesSpawned < par6) {
/*  89 */       for (int i1 = this.witchesSpawned; i1 < par6; i1++) {
/*  90 */         int j1 = func_74865_a(par3 + i1, par5);
/*  91 */         int k1 = func_74862_a(par4);
/*  92 */         int l1 = func_74873_b(par3 + i1, par5);
/*     */         
/*  94 */         if (!par2StructureBoundingBox.func_78890_b(j1, k1, l1)) {
/*     */           break;
/*     */         }
/*     */         
/*  98 */         this.witchesSpawned += 1;
/*  99 */         spawnInhabitant(par1World, par2StructureBoundingBox);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 107 */   public static final WeightedRandomChestContent[] shackChestContents = { new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151034_e, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151101_aQ, 0, 1, 3, 10), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150345_g), 1, 1, 1, 15), new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemRowanBerries.damageValue, 1, 2, 10), new WeightedRandomChestContent(Items.field_151037_a, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151035_b, 0, 1, 1, 5) };
/*     */   
/*     */ 
/*     */   private boolean hasMadeChest;
/*     */   
/*     */   private static final String CHEST_KEY = "WITCShackChest";
/*     */   
/*     */ 
/*     */   protected abstract void spawnInhabitant(World paramWorld, StructureBoundingBox paramStructureBoundingBox);
/*     */   
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 120 */     super.func_143012_a(par1NBTTagCompound);
/* 121 */     par1NBTTagCompound.func_74757_a("WITCShackChest", this.hasMadeChest);
/* 122 */     par1NBTTagCompound.func_74768_a("WITCWCount", this.witchesSpawned);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 128 */     super.func_143011_b(par1NBTTagCompound);
/* 129 */     this.hasMadeChest = par1NBTTagCompound.func_74767_n("WITCShackChest");
/* 130 */     if (par1NBTTagCompound.func_74764_b("WITCWCount")) {
/* 131 */       this.witchesSpawned = par1NBTTagCompound.func_74762_e("WITCWCount");
/*     */     } else {
/* 133 */       this.witchesSpawned = 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentClonedStructure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */