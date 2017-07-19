/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityDispenser;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ 
/*     */ public class WitcheryComponent extends StructureComponent
/*     */ {
/*     */   public WitcheryComponent() {}
/*     */   
/*     */   public WitcheryComponent(int direction, Random random, int x, int z, int dimX, int dimY, int dimZ)
/*     */   {
/*  26 */     super(direction);
/*  27 */     this.field_74885_f = direction;
/*  28 */     this.field_74887_e = calcBox(direction, x + (16 - dimX) / 2, 64, z + (16 - dimZ) / 2, dimX, dimY, dimZ, 0);
/*     */   }
/*     */   
/*     */   public boolean addComponentParts(World world, Random random) {
/*  32 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_151554_b(World par1World, Block par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
/*     */   {
/*  38 */     int j1 = func_74865_a(par4, par6);
/*  39 */     int k0 = func_74862_a(par5);
/*  40 */     int k1 = k0;
/*  41 */     int l1 = func_74873_b(par4, par6);
/*     */     
/*  43 */     if (par7StructureBoundingBox.func_78890_b(j1, k1, l1)) {
/*  44 */       if (par1World.func_147437_c(j1, k1, l1)) {
/*  45 */         return;
/*     */       }
/*  47 */       k1--;
/*     */       
/*  49 */       while (((par1World.func_147437_c(j1, k1, l1)) || (!par1World.func_147439_a(j1, k1, l1).func_149688_o().func_76220_a()) || (par1World.func_147439_a(j1, k1, l1) == Blocks.field_150432_aD)) && (k1 > 1)) {
/*  50 */         par1World.func_147465_d(j1, k1, l1, par2, par3, 2);
/*     */         
/*  52 */         k1--;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_74871_b(World par1World, int par2, int par3, int par4, StructureBoundingBox par5StructureBoundingBox)
/*     */   {
/*  59 */     int l = func_74865_a(par2, par4);
/*  60 */     int i1 = func_74862_a(par3);
/*  61 */     int j1 = func_74873_b(par2, par4);
/*     */     
/*  63 */     if (par5StructureBoundingBox.func_78890_b(l, i1, j1)) {
/*  64 */       int i = 0;
/*  65 */       for (;;) { i++; if (((i >= 20) && (par1World.func_147437_c(l, i1, j1))) || (i1 >= 255)) break;
/*  66 */         par1World.func_147465_d(l, i1, j1, Blocks.field_150350_a, 0, 2);
/*  67 */         i1++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean isWaterBelow(World par1World, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox) {
/*  73 */     int j1 = func_74865_a(par4, par6);
/*  74 */     int k1 = func_74862_a(par5);
/*  75 */     int l1 = func_74873_b(par4, par6);
/*     */     
/*     */ 
/*  78 */     for (int i = 0; i < 10; i++) {
/*  79 */       Material material = par1World.func_147439_a(j1, k1, l1).func_149688_o();
/*  80 */       if ((material.func_76224_d()) || (material == Material.field_151588_w))
/*  81 */         return true;
/*  82 */       if (!par1World.func_147437_c(j1, k1, l1)) {
/*  83 */         return false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public void setDispenser(int x, int y, int z, Random random, World world, int facing) {
/*  92 */     int i1 = func_74865_a(x, z);
/*  93 */     int j1 = func_74862_a(y);
/*  94 */     int k1 = func_74873_b(x, z);
/*     */     
/*  96 */     world.func_147465_d(i1, j1, k1, Blocks.field_150367_z, facing, 0);
/*  97 */     TileEntity tileDispenser = world.func_147438_o(i1, j1, k1);
/*  98 */     if ((tileDispenser != null) && ((tileDispenser instanceof TileEntityDispenser))) {
/*  99 */       ChestGenHooks info = ChestGenHooks.getInfo("mineshaftCorridor");
/* 100 */       WeightedRandomChestContent.func_76293_a(random, info.getItems(random), (TileEntityDispenser)tileDispenser, info.getCount(random));
/*     */     } else {
/* 102 */       Log.instance().warning("Failed to fetch dispenser entity at (" + i1 + ", " + j1 + ", " + k1 + ")");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setSpawner(int x, int y, int z, String mobName, World world) {
/* 107 */     int i1 = func_74865_a(x, z);
/* 108 */     int j1 = func_74862_a(y);
/* 109 */     int k1 = func_74873_b(x, z);
/*     */     
/* 111 */     world.func_147465_d(i1, j1, k1, Blocks.field_150474_ac, 0, 2);
/* 112 */     TileEntity tileSpawner = world.func_147438_o(i1, j1, k1);
/* 113 */     if ((tileSpawner != null) && ((tileSpawner instanceof TileEntityMobSpawner))) {
/* 114 */       ((TileEntityMobSpawner)tileSpawner).func_145881_a().func_98272_a(mobName);
/*     */     } else {
/* 116 */       Log.instance().warning("Failed to fetch mob spawner entity at (" + i1 + ", " + j1 + ", " + k1 + ")");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setFurnace(int x, int y, int z, World world) {
/* 121 */     int i1 = func_74865_a(x, z);
/* 122 */     int j1 = func_74862_a(y);
/* 123 */     int k1 = func_74873_b(x, z);
/*     */     
/* 125 */     world.func_147465_d(i1, j1, k1, Blocks.field_150460_al, func_151555_a(Blocks.field_150331_J, 3), 2);
/*     */   }
/*     */   
/*     */   protected void placeAirBlockAtPos(int x, int y, int z, StructureBoundingBox bounds, World world) {
/* 129 */     func_151550_a(world, Blocks.field_150350_a, 0, x, y, z, bounds);
/*     */   }
/*     */   
/*     */   protected void place(Block block, int meta, int x, int y, int z, StructureBoundingBox bounds, World world) {
/* 133 */     func_151550_a(world, block, meta, x, y, z, bounds);
/*     */   }
/*     */   
/*     */   protected StructureBoundingBox calcBox(int direction, int x, int y, int z, int xLength, int height, int zLength, int xShift) {
/* 137 */     int minX = 0;
/* 138 */     int maxX = 0;
/* 139 */     int minY = y;
/* 140 */     int maxY = y + height;
/* 141 */     int minZ = 0;
/* 142 */     int maxZ = 0;
/*     */     
/* 144 */     switch (direction) {
/*     */     case 0: 
/* 146 */       minX = x - xShift;
/* 147 */       maxX = x - xShift + xLength;
/* 148 */       minZ = z;
/* 149 */       maxZ = z + zLength;
/* 150 */       break;
/*     */     
/*     */     case 1: 
/* 153 */       minX = x - zLength;
/* 154 */       maxX = x;
/* 155 */       minZ = z - xShift;
/* 156 */       maxZ = z - xShift + xLength;
/* 157 */       break;
/*     */     
/*     */     case 2: 
/* 160 */       minX = x - xShift;
/* 161 */       maxX = x - xShift + xLength;
/* 162 */       minZ = z - zLength;
/* 163 */       maxZ = z;
/* 164 */       break;
/*     */     
/*     */     case 3: 
/* 167 */       minX = x;
/* 168 */       maxX = x + zLength;
/* 169 */       minZ = z - xShift;
/* 170 */       maxZ = z - xShift + xLength;
/*     */     }
/*     */     
/*     */     
/* 174 */     return new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
/*     */   }
/*     */   
/*     */   protected int calcGroundHeight(World world, StructureBoundingBox boundingBox) {
/* 178 */     int height = 0;
/* 179 */     int count = 0;
/*     */     
/* 181 */     for (int z = boundingBox.field_78896_c; z <= boundingBox.field_78892_f; z++) {
/* 182 */       for (int x = boundingBox.field_78897_a; x <= boundingBox.field_78893_d; x++) {
/* 183 */         if (boundingBox.func_78890_b(x, 64, z)) {
/* 184 */           height += Math.max(world.func_72825_h(x, z), world.field_73011_w.func_76557_i());
/* 185 */           count++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 190 */     if (count == 0) {
/* 191 */       return -1;
/*     */     }
/* 193 */     return height / count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_143011_b(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_74875_a(World world, Random random, StructureBoundingBox structureboundingbox)
/*     */   {
/* 208 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WitcheryComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */