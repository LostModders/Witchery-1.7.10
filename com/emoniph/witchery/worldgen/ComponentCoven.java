/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ 
/*     */ 
/*     */ public class ComponentCoven
/*     */   extends WitcheryComponent
/*     */ {
/*     */   public static final int DIM_X = 11;
/*     */   public static final int DIM_Y = 4;
/*     */   public static final int DIM_Z = 11;
/*     */   
/*     */   public ComponentCoven() {}
/*     */   
/*     */   public ComponentCoven(int direction, Random random, int x, int z)
/*     */   {
/*  24 */     super(direction, random, x, z, 11, 4, 11);
/*     */   }
/*     */   
/*  27 */   private int witchesSpawned = 0;
/*     */   
/*     */   private void spawnWitches(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {
/*  30 */     if (this.witchesSpawned < par6)
/*     */     {
/*  32 */       for (int i1 = this.witchesSpawned; i1 < par6; i1++)
/*     */       {
/*  34 */         int j1 = func_74865_a(par3 + i1, par5);
/*  35 */         int k1 = func_74862_a(par4);
/*  36 */         int l1 = func_74873_b(par3 + i1, par5);
/*     */         
/*  38 */         if (!par2StructureBoundingBox.func_78890_b(j1, k1, l1)) {
/*     */           break;
/*     */         }
/*     */         
/*     */ 
/*  43 */         this.witchesSpawned += 1;
/*  44 */         if (par1World.field_73012_v.nextInt(10) != 0) {
/*  45 */           EntityCovenWitch entityvillager = new EntityCovenWitch(par1World);
/*  46 */           entityvillager.func_110163_bv();
/*  47 */           entityvillager.func_70012_b(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
/*  48 */           par1World.func_72838_d(entityvillager);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean addComponentParts(World world, Random random) {
/*  55 */     BiomeGenBase biom = world.func_72807_a(func_74865_a(0, 0), func_74873_b(0, 0));
/*  56 */     int groundAvg = calcGroundHeight(world, this.field_74887_e);
/*     */     
/*  58 */     if (groundAvg < 0) {
/*  59 */       return true;
/*     */     }
/*     */     
/*  62 */     this.field_74887_e.func_78886_a(0, groundAvg - this.field_74887_e.field_78894_e + 4 - 1, 0);
/*  63 */     if ((isWaterBelow(world, 0, -1, 0, this.field_74887_e)) || (isWaterBelow(world, 0, -1, 10, this.field_74887_e)) || (isWaterBelow(world, 10, -1, 0, this.field_74887_e)) || (isWaterBelow(world, 10, -1, 10, this.field_74887_e)))
/*     */     {
/*  65 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  71 */     Block stone = Blocks.field_150347_e;
/*  72 */     int stoneMeta = 0;
/*  73 */     Block brick = Blocks.field_150417_aV;
/*  74 */     int brickMeta = 2;
/*     */     Block groundID;
/*  76 */     Block undergroundID; if ((biom.field_76756_M == BiomeGenBase.field_76769_d.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76786_s.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76787_r.field_76756_M)) {
/*  77 */       Block groundID = Blocks.field_150354_m;
/*  78 */       Block undergroundID = Blocks.field_150354_m;
/*  79 */       stone = Blocks.field_150322_A;
/*  80 */       stoneMeta = 0;
/*  81 */       brick = Blocks.field_150322_A;
/*  82 */       brickMeta = 2;
/*     */     } else {
/*  84 */       groundID = Blocks.field_150349_c;
/*  85 */       undergroundID = Blocks.field_150346_d;
/*     */     }
/*     */     
/*  88 */     func_74878_a(world, this.field_74887_e, 3, 1, 0, 7, 3, 10);
/*  89 */     func_74878_a(world, this.field_74887_e, 0, 1, 3, 10, 3, 7);
/*  90 */     func_74878_a(world, this.field_74887_e, 1, 1, 1, 9, 3, 9);
/*     */     
/*  92 */     func_151549_a(world, this.field_74887_e, 3, 0, 0, 7, 0, 10, groundID, groundID, false);
/*  93 */     func_151549_a(world, this.field_74887_e, 0, 0, 3, 10, 0, 7, groundID, groundID, false);
/*  94 */     func_151549_a(world, this.field_74887_e, 1, 0, 1, 9, 0, 9, groundID, groundID, false);
/*     */     
/*  96 */     int ground = 1;
/*     */     
/*     */ 
/*     */ 
/* 100 */     Block altarBrick = Blocks.field_150417_aV;
/* 101 */     int altarMeta = 3;
/* 102 */     place(altarBrick, 3, 4, ground, 4, this.field_74887_e, world);
/* 103 */     place(altarBrick, 3, 4, ground, 5, this.field_74887_e, world);
/* 104 */     place(altarBrick, 3, 4, ground, 6, this.field_74887_e, world);
/*     */     
/* 106 */     place(altarBrick, 3, 5, ground, 4, this.field_74887_e, world);
/*     */     
/* 108 */     place(Blocks.field_150355_j, 0, 5, ground, 5, this.field_74887_e, world);
/*     */     
/* 110 */     setDispenser(5, ground - 1, 5, random, world, 5);
/*     */     
/* 112 */     place(altarBrick, 3, 5, ground, 6, this.field_74887_e, world);
/*     */     
/* 114 */     place(altarBrick, 3, 6, ground, 4, this.field_74887_e, world);
/* 115 */     place(altarBrick, 3, 6, ground, 5, this.field_74887_e, world);
/* 116 */     place(altarBrick, 3, 6, ground, 6, this.field_74887_e, world);
/*     */     
/* 118 */     String mobType = "Witch";
/* 119 */     setSpawner(4, ground - 1, 4, "Witch", world);
/*     */     
/*     */ 
/* 122 */     setSpawner(6, ground - 1, 6, "Witch", world);
/*     */     
/*     */ 
/*     */ 
/* 126 */     place(stone, stoneMeta, 1, ground, 2, this.field_74887_e, world);
/* 127 */     place(stone, stoneMeta, 2, ground, 1, this.field_74887_e, world);
/*     */     
/* 129 */     place(Blocks.field_150321_G, 0, 3, ground, 0, this.field_74887_e, world);
/*     */     
/* 131 */     place(stone, stoneMeta, 4, ground, 0, this.field_74887_e, world);
/* 132 */     place(stone, stoneMeta, 6, ground, 0, this.field_74887_e, world);
/*     */     
/* 134 */     place(stone, stoneMeta, 8, ground, 1, this.field_74887_e, world);
/* 135 */     place(stone, stoneMeta, 9, ground, 2, this.field_74887_e, world);
/*     */     
/* 137 */     place(stone, stoneMeta, 10, ground, 4, this.field_74887_e, world);
/* 138 */     place(stone, stoneMeta, 10, ground, 6, this.field_74887_e, world);
/*     */     
/* 140 */     place(stone, stoneMeta, 9, ground, 8, this.field_74887_e, world);
/* 141 */     place(stone, stoneMeta, 8, ground, 9, this.field_74887_e, world);
/*     */     
/* 143 */     place(stone, stoneMeta, 6, ground, 10, this.field_74887_e, world);
/* 144 */     place(stone, stoneMeta, 4, ground, 10, this.field_74887_e, world);
/*     */     
/* 146 */     place(stone, stoneMeta, 2, ground, 9, this.field_74887_e, world);
/* 147 */     place(stone, stoneMeta, 1, ground, 8, this.field_74887_e, world);
/*     */     
/* 149 */     place(stone, stoneMeta, 0, ground, 4, this.field_74887_e, world);
/* 150 */     place(stone, stoneMeta, 0, ground, 6, this.field_74887_e, world);
/*     */     
/* 152 */     ground++;
/*     */     
/* 154 */     place(brick, brickMeta, 1, ground, 2, this.field_74887_e, world);
/* 155 */     place(brick, brickMeta, 2, ground, 1, this.field_74887_e, world);
/*     */     
/* 157 */     place(stone, stoneMeta, 4, ground, 0, this.field_74887_e, world);
/* 158 */     place(stone, stoneMeta, 6, ground, 0, this.field_74887_e, world);
/*     */     
/* 160 */     place(brick, brickMeta, 8, ground, 1, this.field_74887_e, world);
/* 161 */     place(brick, brickMeta, 9, ground, 2, this.field_74887_e, world);
/*     */     
/* 163 */     place(stone, stoneMeta, 10, ground, 4, this.field_74887_e, world);
/* 164 */     place(stone, stoneMeta, 10, ground, 6, this.field_74887_e, world);
/*     */     
/* 166 */     place(brick, brickMeta, 9, ground, 8, this.field_74887_e, world);
/* 167 */     place(brick, brickMeta, 8, ground, 9, this.field_74887_e, world);
/*     */     
/* 169 */     place(stone, stoneMeta, 6, ground, 10, this.field_74887_e, world);
/* 170 */     place(stone, stoneMeta, 4, ground, 10, this.field_74887_e, world);
/*     */     
/* 172 */     place(brick, brickMeta, 2, ground, 9, this.field_74887_e, world);
/* 173 */     place(brick, brickMeta, 1, ground, 8, this.field_74887_e, world);
/*     */     
/* 175 */     place(stone, stoneMeta, 0, ground, 4, this.field_74887_e, world);
/* 176 */     place(stone, stoneMeta, 0, ground, 6, this.field_74887_e, world);
/*     */     
/* 178 */     ground++;
/*     */     
/* 180 */     place(stone, stoneMeta, 1, ground, 2, this.field_74887_e, world);
/* 181 */     place(stone, stoneMeta, 2, ground, 1, this.field_74887_e, world);
/*     */     
/* 183 */     place(brick, brickMeta, 3, ground, 1, this.field_74887_e, world);
/* 184 */     place(brick, brickMeta, 4, ground, 0, this.field_74887_e, world);
/* 185 */     place(brick, brickMeta, 5, ground, 0, this.field_74887_e, world);
/* 186 */     place(brick, brickMeta, 6, ground, 0, this.field_74887_e, world);
/* 187 */     place(brick, brickMeta, 7, ground, 1, this.field_74887_e, world);
/*     */     
/* 189 */     place(stone, stoneMeta, 8, ground, 1, this.field_74887_e, world);
/* 190 */     place(stone, stoneMeta, 9, ground, 2, this.field_74887_e, world);
/*     */     
/* 192 */     place(brick, brickMeta, 9, ground, 3, this.field_74887_e, world);
/* 193 */     place(brick, brickMeta, 10, ground, 4, this.field_74887_e, world);
/* 194 */     place(brick, brickMeta, 10, ground, 5, this.field_74887_e, world);
/* 195 */     place(brick, brickMeta, 10, ground, 6, this.field_74887_e, world);
/* 196 */     place(brick, brickMeta, 9, ground, 7, this.field_74887_e, world);
/*     */     
/* 198 */     place(Blocks.field_150321_G, 0, 10, ground - 1, 7, this.field_74887_e, world);
/*     */     
/* 200 */     place(stone, stoneMeta, 9, ground, 8, this.field_74887_e, world);
/* 201 */     place(stone, stoneMeta, 8, ground, 9, this.field_74887_e, world);
/*     */     
/* 203 */     place(brick, brickMeta, 7, ground, 9, this.field_74887_e, world);
/* 204 */     place(brick, brickMeta, 6, ground, 10, this.field_74887_e, world);
/* 205 */     place(brick, brickMeta, 5, ground, 10, this.field_74887_e, world);
/* 206 */     place(brick, brickMeta, 4, ground, 10, this.field_74887_e, world);
/* 207 */     place(brick, brickMeta, 3, ground, 9, this.field_74887_e, world);
/*     */     
/* 209 */     place(stone, stoneMeta, 2, ground, 9, this.field_74887_e, world);
/* 210 */     place(stone, stoneMeta, 1, ground, 8, this.field_74887_e, world);
/*     */     
/* 212 */     place(Blocks.field_150321_G, 0, 0, ground, 7, this.field_74887_e, world);
/*     */     
/* 214 */     place(brick, brickMeta, 1, ground, 3, this.field_74887_e, world);
/* 215 */     place(brick, brickMeta, 0, ground, 4, this.field_74887_e, world);
/* 216 */     place(brick, brickMeta, 0, ground, 5, this.field_74887_e, world);
/* 217 */     place(brick, brickMeta, 0, ground, 6, this.field_74887_e, world);
/* 218 */     place(brick, brickMeta, 1, ground, 7, this.field_74887_e, world);
/*     */     
/* 220 */     for (int x = 0; x < 11; x++) {
/* 221 */       for (int z = 0; z < 11; z++) {
/* 222 */         func_151554_b(world, undergroundID, 0, x, 0, z, this.field_74887_e);
/* 223 */         func_74871_b(world, x, 4, z, this.field_74887_e);
/*     */       }
/*     */     }
/*     */     
/* 227 */     spawnWitches(world, this.field_74887_e, 7, 1, 4, 1);
/*     */     
/* 229 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 234 */     super.func_143012_a(par1NBTTagCompound);
/* 235 */     par1NBTTagCompound.func_74768_a("WITCWCount", this.witchesSpawned);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 241 */     super.func_143011_b(par1NBTTagCompound);
/* 242 */     if (par1NBTTagCompound.func_74764_b("WITCWCount")) {
/* 243 */       this.witchesSpawned = par1NBTTagCompound.func_74762_e("WITCWCount");
/*     */     } else {
/* 245 */       this.witchesSpawned = 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentCoven.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */