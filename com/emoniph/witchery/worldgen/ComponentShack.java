/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ 
/*     */ public class ComponentShack extends WitcheryComponent
/*     */ {
/*     */   public static final int DIM_X = 7;
/*     */   public static final int DIM_Y = 10;
/*     */   public static final int DIM_Z = 7;
/*     */   
/*     */   public ComponentShack() {}
/*     */   
/*     */   public ComponentShack(int direction, Random random, int x, int z)
/*     */   {
/*  26 */     super(direction, random, x, z, 7, 10, 7);
/*     */   }
/*     */   
/*     */   public boolean addComponentParts(World world, Random random)
/*     */   {
/*  31 */     BiomeGenBase biom = world.func_72807_a(func_74865_a(0, 0), func_74873_b(0, 0));
/*  32 */     int groundAvg = calcGroundHeight(world, this.field_74887_e);
/*     */     
/*  34 */     if (groundAvg < 0) {
/*  35 */       return true;
/*     */     }
/*     */     
/*  38 */     this.field_74887_e.func_78886_a(0, groundAvg - this.field_74887_e.field_78894_e + 10 - 1, 0);
/*     */     
/*  40 */     if ((isWaterBelow(world, 0, -1, 0, this.field_74887_e)) || (isWaterBelow(world, 0, -1, 6, this.field_74887_e)) || (isWaterBelow(world, 6, -1, 0, this.field_74887_e)) || (isWaterBelow(world, 6, -1, 6, this.field_74887_e)))
/*     */     {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     Block groundID = Blocks.field_150349_c;
/*  46 */     Block undergroundID = Blocks.field_150346_d;
/*  47 */     if ((biom.field_76756_M == BiomeGenBase.field_76769_d.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76786_s.field_76756_M) || (biom.field_76756_M == BiomeGenBase.field_76787_r.field_76756_M)) {
/*  48 */       groundID = Blocks.field_150354_m;
/*  49 */       undergroundID = Blocks.field_150354_m;
/*     */     }
/*     */     
/*     */ 
/*  53 */     func_74878_a(world, this.field_74887_e, 0, 1, 0, 6, 9, 6);
/*  54 */     func_151549_a(world, this.field_74887_e, 0, 0, 1, 6, 1, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  55 */     func_151549_a(world, this.field_74887_e, 0, 2, 1, 6, 3, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
/*  56 */     func_74878_a(world, this.field_74887_e, 1, 1, 2, 5, 3, 4);
/*     */     
/*  58 */     place(Blocks.field_150364_r, 1, 0, 1, 1, this.field_74887_e, world);
/*  59 */     place(Blocks.field_150364_r, 1, 0, 2, 1, this.field_74887_e, world);
/*  60 */     place(Blocks.field_150364_r, 1, 0, 3, 1, this.field_74887_e, world);
/*     */     
/*  62 */     place(Blocks.field_150364_r, 1, 0, 1, 5, this.field_74887_e, world);
/*  63 */     place(Blocks.field_150364_r, 1, 0, 2, 5, this.field_74887_e, world);
/*  64 */     place(Blocks.field_150364_r, 1, 0, 3, 5, this.field_74887_e, world);
/*     */     
/*  66 */     place(Blocks.field_150364_r, 1, 6, 1, 1, this.field_74887_e, world);
/*  67 */     place(Blocks.field_150364_r, 1, 6, 2, 1, this.field_74887_e, world);
/*  68 */     place(Blocks.field_150364_r, 1, 6, 3, 1, this.field_74887_e, world);
/*     */     
/*  70 */     place(Blocks.field_150364_r, 1, 6, 1, 5, this.field_74887_e, world);
/*  71 */     place(Blocks.field_150364_r, 1, 6, 2, 5, this.field_74887_e, world);
/*  72 */     place(Blocks.field_150364_r, 1, 6, 3, 5, this.field_74887_e, world);
/*     */     
/*  74 */     int meta = (this.field_74885_f == 3) || (this.field_74885_f == 1) ? 4 : 8;
/*  75 */     place(Blocks.field_150364_r, 0x1 | meta, 0, 4, 2, this.field_74887_e, world);
/*  76 */     place(Blocks.field_150364_r, 0x1 | meta, 0, 4, 3, this.field_74887_e, world);
/*  77 */     place(Blocks.field_150364_r, 0x1 | meta, 0, 4, 4, this.field_74887_e, world);
/*     */     
/*  79 */     place(Blocks.field_150364_r, 0x1 | meta, 6, 4, 2, this.field_74887_e, world);
/*  80 */     place(Blocks.field_150364_r, 0x1 | meta, 6, 4, 3, this.field_74887_e, world);
/*  81 */     place(Blocks.field_150364_r, 0x1 | meta, 6, 4, 4, this.field_74887_e, world);
/*     */     
/*  83 */     for (int x = 0; x < 7; x++) {
/*  84 */       place(Blocks.field_150485_bF, func_151555_a(Blocks.field_150476_ad, 3), x, 3, 0, this.field_74887_e, world);
/*  85 */       place(Blocks.field_150485_bF, func_151555_a(Blocks.field_150476_ad, 3), x, 4, 1, this.field_74887_e, world);
/*  86 */       place(Blocks.field_150485_bF, func_151555_a(Blocks.field_150476_ad, 3), x, 5, 2, this.field_74887_e, world);
/*  87 */       place(Blocks.field_150344_f, 1, x, 5, 3, this.field_74887_e, world);
/*  88 */       place(Blocks.field_150485_bF, func_151555_a(Blocks.field_150476_ad, 2), x, 5, 4, this.field_74887_e, world);
/*  89 */       place(Blocks.field_150485_bF, func_151555_a(Blocks.field_150476_ad, 2), x, 4, 5, this.field_74887_e, world);
/*  90 */       place(Blocks.field_150485_bF, func_151555_a(Blocks.field_150476_ad, 2), x, 3, 6, this.field_74887_e, world);
/*     */     }
/*     */     
/*  93 */     place(Blocks.field_150410_aZ, 0, 2, 2, 1, this.field_74887_e, world);
/*  94 */     place(Blocks.field_150410_aZ, 0, 2, 2, 5, this.field_74887_e, world);
/*  95 */     place(Blocks.field_150410_aZ, 0, 4, 2, 5, this.field_74887_e, world);
/*  96 */     place(Blocks.field_150410_aZ, 0, 0, 2, 3, this.field_74887_e, world);
/*  97 */     place(Blocks.field_150410_aZ, 0, 6, 2, 3, this.field_74887_e, world);
/*     */     
/*  99 */     func_74881_a(world, this.field_74887_e, random, 4, 1, 1, func_151555_a(Blocks.field_150466_ao, 1));
/*     */     
/*     */ 
/* 102 */     place(Blocks.field_150344_f, 2, 1, 1, 4, this.field_74887_e, world);
/* 103 */     place(Blocks.field_150478_aa, 0, 1, 2, 4, this.field_74887_e, world);
/* 104 */     place(Blocks.field_150487_bG, func_151555_a(Blocks.field_150476_ad, 1), 1, 1, 3, this.field_74887_e, world);
/* 105 */     place(Blocks.field_150487_bG, func_151555_a(Blocks.field_150476_ad, 3), 2, 1, 4, this.field_74887_e, world);
/* 106 */     place(Blocks.field_150422_aJ, 0, 2, 1, 3, this.field_74887_e, world);
/* 107 */     place(Blocks.field_150452_aw, 0, 2, 2, 3, this.field_74887_e, world);
/*     */     
/* 109 */     if (!this.hasMadeChest) {
/* 110 */       int ic = func_74862_a(0);
/* 111 */       int jc = func_74865_a(7, 1);
/* 112 */       int kc = func_74873_b(7, 1);
/*     */       
/* 114 */       if (this.field_74887_e.func_78890_b(jc, ic, kc))
/*     */       {
/* 116 */         this.hasMadeChest = true;
/* 117 */         func_74879_a(world, this.field_74887_e, random, 1, 1, 2, shackChestContents, 1 + random.nextInt(3));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 123 */     for (int i = 0; i < 7; i++)
/*     */     {
/* 125 */       for (int j = 0; j < 7; j++)
/*     */       {
/* 127 */         func_74871_b(world, j, 6, i, this.field_74887_e);
/* 128 */         func_151554_b(world, undergroundID, 0, j, 0, i, this.field_74887_e);
/*     */       }
/*     */     }
/*     */     
/* 132 */     spawnWitches(world, this.field_74887_e, 4, 1, 3, 1);
/*     */     
/* 134 */     return true;
/*     */   }
/*     */   
/* 137 */   private int witchesSpawned = 0;
/*     */   
/*     */   private void spawnWitches(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {
/* 140 */     if (this.witchesSpawned < par6)
/*     */     {
/* 142 */       for (int i1 = this.witchesSpawned; i1 < par6; i1++)
/*     */       {
/* 144 */         int j1 = func_74865_a(par3 + i1, par5);
/* 145 */         int k1 = func_74862_a(par4);
/* 146 */         int l1 = func_74873_b(par3 + i1, par5);
/*     */         
/* 148 */         if (!par2StructureBoundingBox.func_78890_b(j1, k1, l1)) {
/*     */           break;
/*     */         }
/*     */         
/*     */ 
/* 153 */         this.witchesSpawned += 1;
/* 154 */         if (par1World.field_73012_v.nextInt(4) != 0) {
/* 155 */           EntityCovenWitch entityvillager = new EntityCovenWitch(par1World);
/* 156 */           entityvillager.func_110163_bv();
/* 157 */           entityvillager.func_70012_b(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
/* 158 */           par1World.func_72838_d(entityvillager);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 164 */   public static final WeightedRandomChestContent[] shackChestContents = { new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151034_e, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151101_aQ, 0, 1, 3, 10), new WeightedRandomChestContent(net.minecraft.item.Item.func_150898_a(Blocks.field_150345_g), 1, 1, 1, 15), new WeightedRandomChestContent(Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemRowanBerries.damageValue, 1, 2, 10), new WeightedRandomChestContent(Items.field_151037_a, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151035_b, 0, 1, 1, 5) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean hasMadeChest;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final String CHEST_KEY = "WITCShackChest";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 181 */     super.func_143012_a(par1NBTTagCompound);
/* 182 */     par1NBTTagCompound.func_74757_a("WITCShackChest", this.hasMadeChest);
/* 183 */     par1NBTTagCompound.func_74768_a("WITCWCount", this.witchesSpawned);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 189 */     super.func_143011_b(par1NBTTagCompound);
/* 190 */     this.hasMadeChest = par1NBTTagCompound.func_74767_n("WITCShackChest");
/* 191 */     if (par1NBTTagCompound.func_74764_b("WITCWCount")) {
/* 192 */       this.witchesSpawned = par1NBTTagCompound.func_74762_e("WITCWCount");
/*     */     } else {
/* 194 */       this.witchesSpawned = 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentShack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */