/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
/*     */ import com.emoniph.witchery.blocks.BlockLeechChest.TileEntityLeechChest;
/*     */ import com.emoniph.witchery.blocks.BlockWitchCrop;
/*     */ import com.emoniph.witchery.entity.EntityMandrake;
/*     */ import com.emoniph.witchery.entity.EntityOwl;
/*     */ import com.emoniph.witchery.entity.EntityParasyticLouse;
/*     */ import com.emoniph.witchery.entity.EntityToad;
/*     */ import com.emoniph.witchery.entity.EntityWingedMonkey;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Dye;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockCocoa;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemMutator extends ItemBase
/*     */ {
/*     */   private static final int MAX_DAMAGE = 15;
/*     */   private static final int DAMAGE_PER_USE = 1;
/*     */   
/*     */   public ItemMutator()
/*     */   {
/*  48 */     func_77625_d(1);
/*  49 */     func_77656_e(15);
/*  50 */     func_77664_n();
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  56 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  61 */     Block block = world.func_147439_a(x, y, z);
/*  62 */     Material materialAbove = world.func_147439_a(x, y + 1, z).func_149688_o();
/*  63 */     if (block == Blocks.field_150349_c) {
/*  64 */       if (!world.field_72995_K) {
/*  65 */         world.func_147449_b(x, y, z, Blocks.field_150391_bh);
/*  66 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, 1.0D + y, 0.5D + z, 1.0D, 1.0D, 8);
/*     */       }
/*  68 */       stack.func_77972_a(1, player);
/*  69 */     } else if (block == Blocks.field_150391_bh) {
/*  70 */       if (!world.field_72995_K) {
/*  71 */         world.func_147449_b(x, y, z, Blocks.field_150349_c);
/*  72 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, 1.0D + y, 0.5D + z, 1.0D, 1.0D, 8);
/*     */       }
/*  74 */       stack.func_77972_a(1, player);
/*  75 */     } else if ((block == Blocks.field_150346_d) && (materialAbove == Material.field_151586_h)) {
/*  76 */       if (!world.field_72995_K) {
/*  77 */         ItemGeneral.setBlockToClay(world, x, y, z);
/*  78 */         ItemGeneral.setBlockToClay(world, x + 1, y, z);
/*  79 */         ItemGeneral.setBlockToClay(world, x - 1, y, z);
/*  80 */         ItemGeneral.setBlockToClay(world, x, y, z + 1);
/*  81 */         ItemGeneral.setBlockToClay(world, x, y, z - 1);
/*     */       }
/*  83 */       stack.func_77972_a(1, player);
/*  84 */     } else if (isMutatableTrapChest(world, x, y, z)) {
/*  85 */       if (!world.field_72995_K) {
/*  86 */         world.func_147468_f(x, y, z);
/*  87 */         world.func_147468_f(x + 1, y, z);
/*  88 */         world.func_147468_f(x - 1, y, z);
/*  89 */         world.func_147468_f(x, y, z + 1);
/*  90 */         world.func_147468_f(x, y, z - 1);
/*  91 */         world.func_147449_b(x, y, z, Witchery.Blocks.LEECH_CHEST);
/*  92 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 1.0D, 2.0D, 8);
/*     */       }
/*  94 */       stack.func_77972_a(1, player);
/*  95 */     } else if (isMutatableReed(world, x, y, z)) {
/*  96 */       if (!world.field_72995_K)
/*     */       {
/*     */ 
/*  99 */         world.func_147468_f(x + 1, y, z);
/* 100 */         world.func_147468_f(x - 1, y, z);
/* 101 */         world.func_147468_f(x, y, z + 1);
/* 102 */         world.func_147468_f(x, y, z - 1);
/*     */         
/* 104 */         clearGrassperAt(world, x + 1, y, z + 1);
/* 105 */         clearGrassperAt(world, x + 1, y, z - 1);
/* 106 */         clearGrassperAt(world, x - 1, y, z + 1);
/* 107 */         clearGrassperAt(world, x - 1, y, z - 1);
/*     */         
/* 109 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */         
/* 111 */         for (int i = 15; i >= 0; i--) {
/* 112 */           int adjY = y + i;
/* 113 */           if (world.func_147439_a(x, adjY, z) == Blocks.field_150436_aH) {
/* 114 */             world.func_147449_b(x, adjY, z, Witchery.Blocks.BRAMBLE);
/* 115 */             ParticleEffect.SLIME.send(SoundEffect.NONE, world, 0.5D + x, adjY, 0.5D + z, 1.0D, 1.0D, 16);
/*     */           }
/*     */         }
/*     */       }
/* 119 */       stack.func_77972_a(1, player);
/* 120 */     } else if (isMutatableWheat(world, x, y, z)) {
/* 121 */       if (!world.field_72995_K) {
/* 122 */         world.func_147468_f(x, y, z);
/* 123 */         world.func_147468_f(x + 1, y, z);
/* 124 */         world.func_147468_f(x - 1, y, z);
/* 125 */         world.func_147468_f(x, y, z + 1);
/* 126 */         world.func_147468_f(x, y, z - 1);
/* 127 */         BlockUtil.setBlock(world, x, y, z, Witchery.Blocks.CROP_WORMWOOD, 0, 3);
/*     */         
/* 129 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 131 */       stack.func_77972_a(1, player);
/* 132 */     } else if (isMutatableCactus(world, x, y, z)) {
/* 133 */       if (!world.field_72995_K) {
/* 134 */         world.func_147468_f(x + 1, y, z);
/* 135 */         world.func_147468_f(x - 1, y, z);
/* 136 */         world.func_147468_f(x, y, z + 1);
/* 137 */         world.func_147468_f(x, y, z - 1);
/*     */         
/* 139 */         clearGrassperAt(world, x + 1, y, z + 1);
/* 140 */         clearGrassperAt(world, x + 1, y, z - 1);
/* 141 */         clearGrassperAt(world, x - 1, y, z + 1);
/* 142 */         clearGrassperAt(world, x - 1, y, z - 1);
/*     */         
/* 144 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */         
/* 146 */         for (int i = 4; i >= 0; i--) {
/* 147 */           int adjY = y + i;
/* 148 */           if (world.func_147439_a(x, adjY, z) == Blocks.field_150434_aF) {
/* 149 */             world.func_147465_d(x, adjY, z, Witchery.Blocks.BRAMBLE, 1, 3);
/* 150 */             ParticleEffect.SLIME.send(SoundEffect.NONE, world, 0.5D + x, adjY, 0.5D + z, 1.0D, 1.0D, 16);
/*     */           }
/*     */         }
/*     */       }
/* 154 */       stack.func_77972_a(1, player);
/* 155 */     } else if (isMutatableChest(world, x, y, z)) {
/* 156 */       if (!world.field_72995_K) {
/* 157 */         world.func_147468_f(x, y, z);
/* 158 */         world.func_147449_b(x + 1, y, z, Witchery.Blocks.GRASSPER);
/* 159 */         world.func_147449_b(x - 1, y, z, Witchery.Blocks.GRASSPER);
/* 160 */         world.func_147449_b(x, y, z + 1, Witchery.Blocks.GRASSPER);
/* 161 */         world.func_147449_b(x, y, z - 1, Witchery.Blocks.GRASSPER);
/*     */         
/* 163 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 165 */       stack.func_77972_a(1, player);
/* 166 */     } else if (isMutatableLeechChest(world, x, y, z)) {
/* 167 */       if (!world.field_72995_K) {
/* 168 */         world.func_147468_f(x, y, z);
/* 169 */         world.func_147449_b(x + 1, y, z, Witchery.Blocks.BLOOD_ROSE);
/* 170 */         world.func_147449_b(x - 1, y, z, Witchery.Blocks.BLOOD_ROSE);
/* 171 */         world.func_147449_b(x, y, z + 1, Witchery.Blocks.BLOOD_ROSE);
/* 172 */         world.func_147449_b(x, y, z - 1, Witchery.Blocks.BLOOD_ROSE);
/*     */         
/* 174 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 1.0D, 2.0D, 8);
/*     */       }
/* 176 */       stack.func_77972_a(1, player);
/* 177 */     } else if (isMutatableWeb(world, x, y, z)) {
/* 178 */       if (!world.field_72995_K) {
/* 179 */         world.func_147468_f(x, y, z);
/* 180 */         world.func_147449_b(x + 1, y, z, Witchery.Blocks.CRITTER_SNARE);
/* 181 */         world.func_147449_b(x - 1, y, z, Witchery.Blocks.CRITTER_SNARE);
/* 182 */         world.func_147449_b(x, y, z + 1, Witchery.Blocks.CRITTER_SNARE);
/* 183 */         world.func_147449_b(x, y, z - 1, Witchery.Blocks.CRITTER_SNARE);
/*     */         
/* 185 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 187 */       stack.func_77972_a(1, player);
/* 188 */     } else if (isMutatableToOwl(world, x, y, z)) {
/* 189 */       if (!world.field_72995_K) {
/* 190 */         world.func_147468_f(x, y, z);
/* 191 */         convertToEntity(world, x + 1, y, z, Witchery.Blocks.CRITTER_SNARE, 1, EntityOwl.class);
/* 192 */         convertToEntity(world, x - 1, y, z, Witchery.Blocks.CRITTER_SNARE, 1, EntityOwl.class);
/* 193 */         convertToEntity(world, x, y, z + 1, Witchery.Blocks.CRITTER_SNARE, 1, EntityOwl.class);
/* 194 */         convertToEntity(world, x, y, z - 1, Witchery.Blocks.CRITTER_SNARE, 1, EntityOwl.class);
/*     */         
/* 196 */         clearGrassperAt(world, x + 1, y, z + 1);
/* 197 */         clearGrassperAt(world, x + 1, y, z - 1);
/* 198 */         clearGrassperAt(world, x - 1, y, z + 1);
/* 199 */         clearGrassperAt(world, x - 1, y, z - 1);
/*     */         
/* 201 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 203 */       stack.func_77972_a(1, player);
/* 204 */     } else if (isMutatableToToad(world, x, y, z)) {
/* 205 */       if (!world.field_72995_K) {
/* 206 */         world.func_147468_f(x, y, z);
/* 207 */         convertToEntity(world, x + 1, y, z, Witchery.Blocks.CRITTER_SNARE, 3, EntityToad.class);
/* 208 */         convertToEntity(world, x - 1, y, z, Witchery.Blocks.CRITTER_SNARE, 3, EntityToad.class);
/* 209 */         convertToEntity(world, x, y, z + 1, Witchery.Blocks.CRITTER_SNARE, 3, EntityToad.class);
/* 210 */         convertToEntity(world, x, y, z - 1, Witchery.Blocks.CRITTER_SNARE, 3, EntityToad.class);
/*     */         
/* 212 */         clearGrassperAt(world, x + 1, y, z + 1);
/* 213 */         clearGrassperAt(world, x + 1, y, z - 1);
/* 214 */         clearGrassperAt(world, x - 1, y, z + 1);
/* 215 */         clearGrassperAt(world, x - 1, y, z - 1);
/*     */         
/* 217 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 219 */       stack.func_77972_a(1, player);
/* 220 */     } else if (isMutatableToMindrake(world, x, y, z)) {
/* 221 */       if (!world.field_72995_K) {
/* 222 */         world.func_147468_f(x, y, z);
/* 223 */         world.func_147449_b(x + 1, y, z, Witchery.Blocks.CROP_MINDRAKE);
/* 224 */         world.func_147449_b(x - 1, y, z, Witchery.Blocks.CROP_MINDRAKE);
/* 225 */         world.func_147449_b(x, y, z + 1, Witchery.Blocks.CROP_MINDRAKE);
/* 226 */         world.func_147449_b(x, y, z - 1, Witchery.Blocks.CROP_MINDRAKE);
/*     */         
/* 228 */         clearGrassperAt(world, x + 1, y, z + 1);
/* 229 */         clearGrassperAt(world, x + 1, y, z - 1);
/* 230 */         clearGrassperAt(world, x - 1, y, z + 1);
/* 231 */         clearGrassperAt(world, x - 1, y, z - 1);
/*     */         
/* 233 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 235 */       stack.func_77972_a(1, player);
/* 236 */     } else if (isMutatableToMonkey(world, x, y, z)) {
/* 237 */       if (!world.field_72995_K) {
/* 238 */         world.func_147468_f(x, y, z);
/* 239 */         world.func_147468_f(x + 1, y, z);
/* 240 */         world.func_147468_f(x - 1, y, z);
/* 241 */         world.func_147468_f(x, y, z + 1);
/* 242 */         world.func_147468_f(x, y, z - 1);
/*     */         
/* 244 */         clearGrassperAt(world, x + 1, y, z + 1);
/* 245 */         clearGrassperAt(world, x + 1, y, z - 1);
/* 246 */         clearGrassperAt(world, x - 1, y, z + 1);
/* 247 */         clearGrassperAt(world, x - 1, y, z - 1);
/*     */         
/* 249 */         EntityWingedMonkey monkey = new EntityWingedMonkey(world);
/* 250 */         monkey.func_70080_a(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
/* 251 */         monkey.func_110163_bv();
/* 252 */         world.func_72838_d(monkey);
/*     */         
/* 254 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 256 */       stack.func_77972_a(1, player);
/* 257 */     } else if (isMutatableToLouse(world, x, y, z)) {
/* 258 */       if (!world.field_72995_K) {
/* 259 */         world.func_147468_f(x + 1, y, z);
/* 260 */         world.func_147468_f(x - 1, y, z);
/* 261 */         world.func_147468_f(x, y, z + 1);
/* 262 */         world.func_147468_f(x, y, z - 1);
/* 263 */         convertToEntity(world, x, y, z, Witchery.Blocks.CRITTER_SNARE, 2, EntityParasyticLouse.class);
/*     */         
/* 265 */         clearGrassperAt(world, x + 1, y, z + 1);
/* 266 */         clearGrassperAt(world, x + 1, y, z - 1);
/* 267 */         clearGrassperAt(world, x - 1, y, z + 1);
/* 268 */         clearGrassperAt(world, x - 1, y, z - 1);
/*     */         
/* 270 */         ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
/*     */       }
/* 272 */       stack.func_77972_a(1, player);
/*     */     } else {
/* 274 */       return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */     }
/*     */     
/* 277 */     return !world.field_72995_K;
/*     */   }
/*     */   
/*     */   private boolean isMutatableToLouse(World world, int x, int y, int z) {
/* 281 */     Block block = world.func_147439_a(x, y, z);
/* 282 */     int meta = world.func_72805_g(x, y, z);
/* 283 */     if ((block != Witchery.Blocks.CRITTER_SNARE) || (meta != 2)) {
/* 284 */       return false;
/*     */     }
/*     */     
/* 287 */     int vineCount = 0;
/* 288 */     vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150392_bi ? 1 : 0);
/* 289 */     vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150392_bi ? 1 : 0);
/* 290 */     vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150392_bi ? 1 : 0);
/* 291 */     vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150392_bi ? 1 : 0);
/*     */     
/* 293 */     int watercount = 0;
/* 294 */     watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 295 */     watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 296 */     watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
/* 297 */     watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/* 299 */     int grasperAttunedCount = 0;
/* 300 */     ItemStack pearl = Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack();
/* 301 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 302 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 303 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 304 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 306 */     int grasperCount = 0;
/* 307 */     pearl = Witchery.Items.GENERIC.itemMutandis.createStack();
/* 308 */     grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 309 */     grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 310 */     grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 311 */     grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 313 */     int grasperTongueCount = 0;
/* 314 */     pearl = Witchery.Items.GENERIC.itemDogTongue.createStack();
/* 315 */     grasperTongueCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 316 */     grasperTongueCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 317 */     grasperTongueCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 318 */     grasperTongueCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 320 */     if ((vineCount < 2) || (watercount < 1) || (grasperCount < 2) || (grasperAttunedCount < 1) || (grasperTongueCount < 1)) {
/* 321 */       return false;
/*     */     }
/*     */     
/* 324 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableToToad(World world, int x, int y, int z)
/*     */   {
/* 329 */     Block blockID = world.func_147439_a(x, y, z);
/* 330 */     if (blockID != Blocks.field_150321_G) {
/* 331 */       return false;
/*     */     }
/*     */     
/* 334 */     int vineCount = 0;
/* 335 */     vineCount += ((world.func_147439_a(x + 1, y, z) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x + 1, y, z) == 3) ? 1 : 0);
/* 336 */     vineCount += ((world.func_147439_a(x - 1, y, z) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x - 1, y, z) == 3) ? 1 : 0);
/* 337 */     vineCount += ((world.func_147439_a(x, y, z + 1) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x, y, z + 1) == 3) ? 1 : 0);
/* 338 */     vineCount += ((world.func_147439_a(x, y, z - 1) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x, y, z - 1) == 3) ? 1 : 0);
/*     */     
/* 340 */     int watercount = 0;
/* 341 */     watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/*     */ 
/* 344 */     int grasperAttunedCount = 0;
/* 345 */     ItemStack pearl = Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack();
/* 346 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 347 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 348 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 349 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 351 */     int grasperCount = 0;
/* 352 */     pearl = Witchery.Items.GENERIC.itemMutandisExtremis.createStack();
/* 353 */     grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 354 */     grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 355 */     grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 356 */     grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 358 */     if ((vineCount < 2) || (watercount < 1) || (grasperCount < 3) || (grasperAttunedCount < 1)) {
/* 359 */       return false;
/*     */     }
/*     */     
/* 362 */     AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
/* 363 */     List list = world.func_72872_a(EntityOcelot.class, aabb);
/* 364 */     if (list.size() == 0) {
/* 365 */       return false;
/*     */     }
/* 367 */     if (!world.field_72995_K) {
/* 368 */       EntityOcelot ocelot = (EntityOcelot)list.get(0);
/* 369 */       ParticleEffect.SLIME.send(SoundEffect.MOB_OCELOT_DEATH, ocelot, 3.0D, 2.0D, 8);
/* 370 */       ocelot.func_70106_y();
/*     */     }
/*     */     
/*     */ 
/* 374 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableToMonkey(World world, int x, int y, int z) {
/* 378 */     Block blockID = world.func_147439_a(x, y, z);
/* 379 */     if (blockID != Blocks.field_150321_G) {
/* 380 */       return false;
/*     */     }
/*     */     
/* 383 */     int vineCount = 0;
/* 384 */     vineCount += ((world.func_147439_a(x + 1, y, z) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x + 1, y, z)) == 2) ? 1 : 0);
/* 385 */     vineCount += ((world.func_147439_a(x - 1, y, z) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x - 1, y, z)) == 2) ? 1 : 0);
/* 386 */     vineCount += ((world.func_147439_a(x, y, z + 1) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x, y, z + 1)) == 2) ? 1 : 0);
/* 387 */     vineCount += ((world.func_147439_a(x, y, z - 1) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x, y, z - 1)) == 2) ? 1 : 0);
/*     */     
/* 389 */     int watercount = 0;
/* 390 */     watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/* 392 */     int grasperAttunedCount = 0;
/* 393 */     ItemStack pearl = Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack();
/* 394 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 395 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 396 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 397 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 399 */     int grasperCount = 0;
/* 400 */     pearl = Witchery.Items.GENERIC.itemMutandisExtremis.createStack();
/* 401 */     grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 402 */     grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 403 */     grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 404 */     grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 406 */     int grasperTongueCount = 0;
/* 407 */     pearl = new ItemStack(Blocks.field_150328_O);
/* 408 */     grasperTongueCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 409 */     grasperTongueCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 410 */     grasperTongueCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 411 */     grasperTongueCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 413 */     if ((vineCount < 4) || (watercount < 1) || (grasperCount < 2) || (grasperAttunedCount < 1) || (grasperTongueCount < 1)) {
/* 414 */       return false;
/*     */     }
/*     */     
/* 417 */     EntityOwl owl = null;
/* 418 */     AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
/* 419 */     List<EntityOwl> owlList = world.func_72872_a(EntityOwl.class, aabb);
/* 420 */     if (owlList.size() == 0) {
/* 421 */       return false;
/*     */     }
/* 423 */     if (!world.field_72995_K) {
/* 424 */       owl = (EntityOwl)owlList.get(0);
/*     */     }
/*     */     
/*     */ 
/* 428 */     EntityWolf wolf = null;
/* 429 */     aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
/* 430 */     List<EntityWolf> wolfList = world.func_72872_a(EntityWolf.class, aabb);
/* 431 */     if (wolfList.size() == 0) {
/* 432 */       return false;
/*     */     }
/* 434 */     if (!world.field_72995_K) {
/* 435 */       wolf = (EntityWolf)wolfList.get(0);
/*     */     }
/*     */     
/*     */ 
/* 439 */     if ((owl != null) && (wolf != null)) {
/* 440 */       ParticleEffect.SLIME.send(SoundEffect.MOB_CREEPER_DEATH, owl, 3.0D, 2.0D, 8);
/* 441 */       owl.func_70106_y();
/*     */       
/* 443 */       ParticleEffect.SLIME.send(SoundEffect.MOB_GHAST_DEATH, wolf, 3.0D, 2.0D, 8);
/* 444 */       wolf.func_70106_y();
/*     */     } else {
/* 446 */       return false;
/*     */     }
/*     */     
/* 449 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableToOwl(World world, int x, int y, int z) {
/* 453 */     Block blockID = world.func_147439_a(x, y, z);
/* 454 */     if (blockID != Blocks.field_150321_G) {
/* 455 */       return false;
/*     */     }
/*     */     
/* 458 */     int vineCount = 0;
/* 459 */     vineCount += ((world.func_147439_a(x + 1, y, z) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x + 1, y, z) == 1) ? 1 : 0);
/* 460 */     vineCount += ((world.func_147439_a(x - 1, y, z) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x - 1, y, z) == 1) ? 1 : 0);
/* 461 */     vineCount += ((world.func_147439_a(x, y, z + 1) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x, y, z + 1) == 1) ? 1 : 0);
/* 462 */     vineCount += ((world.func_147439_a(x, y, z - 1) == Witchery.Blocks.CRITTER_SNARE) && (world.func_72805_g(x, y, z - 1) == 1) ? 1 : 0);
/*     */     
/* 464 */     int watercount = 0;
/* 465 */     watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/*     */ 
/* 468 */     int grasperAttunedCount = 0;
/* 469 */     ItemStack pearl = Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack();
/* 470 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 471 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 472 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 473 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 475 */     int grasperCount = 0;
/* 476 */     pearl = Witchery.Items.GENERIC.itemMutandisExtremis.createStack();
/* 477 */     grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 478 */     grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 479 */     grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 480 */     grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 482 */     if ((vineCount < 2) || (watercount < 1) || (grasperCount < 3) || (grasperAttunedCount < 1)) {
/* 483 */       return false;
/*     */     }
/*     */     
/* 486 */     AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
/* 487 */     List list = world.func_72872_a(EntityWolf.class, aabb);
/* 488 */     if (list.size() == 0) {
/* 489 */       return false;
/*     */     }
/* 491 */     if (!world.field_72995_K) {
/* 492 */       EntityWolf wolf = (EntityWolf)list.get(0);
/* 493 */       ParticleEffect.SLIME.send(SoundEffect.MOB_WOLF_DEATH, wolf, 3.0D, 2.0D, 8);
/* 494 */       wolf.func_70106_y();
/*     */     }
/*     */     
/*     */ 
/* 498 */     return true;
/*     */   }
/*     */   
/*     */   private void convertToEntity(World world, int x, int y, int z, Block block, int blockMeta, Class<? extends EntityLiving> entityClass) {
/* 502 */     Block foundBlock = world.func_147439_a(x, y, z);
/* 503 */     int foundBlockMeta = world.func_72805_g(x, y, z);
/* 504 */     if ((foundBlock == block) && (foundBlockMeta == blockMeta) && (entityClass != null)) {
/* 505 */       world.func_147468_f(x, y, z);
/*     */       try {
/* 507 */         Constructor ctor = entityClass.getConstructor(new Class[] { World.class });
/* 508 */         EntityLiving entity = (EntityLiving)ctor.newInstance(new Object[] { world });
/* 509 */         entity.func_70012_b(0.5D + x, 0.001D + y, 0.5D + z, 1.0F, 0.0F);
/*     */         
/* 511 */         world.func_72838_d(entity);
/* 512 */         IEntityLivingData entitylivingData = null;
/* 513 */         entity.func_110163_bv();
/* 514 */         entitylivingData = entity.func_110161_a(entitylivingData);
/*     */       }
/*     */       catch (Throwable e) {
/* 517 */         Log.instance().warning(e, "Error occurred while mutating a creature with a sprig");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isMutatableWeb(World world, int x, int y, int z) {
/* 523 */     Block blockID = world.func_147439_a(x, y, z);
/* 524 */     if (blockID != Blocks.field_150321_G) {
/* 525 */       return false;
/*     */     }
/*     */     
/* 528 */     int vineCount = 0;
/* 529 */     vineCount += ((world.func_147439_a(x + 1, y, z) == Witchery.Blocks.SAPLING) && ((world.func_72805_g(x + 1, y, z) & 0x3) == 1) ? 1 : 0);
/* 530 */     vineCount += ((world.func_147439_a(x - 1, y, z) == Witchery.Blocks.SAPLING) && ((world.func_72805_g(x - 1, y, z) & 0x3) == 1) ? 1 : 0);
/* 531 */     vineCount += ((world.func_147439_a(x, y, z + 1) == Witchery.Blocks.SAPLING) && ((world.func_72805_g(x, y, z + 1) & 0x3) == 1) ? 1 : 0);
/* 532 */     vineCount += ((world.func_147439_a(x, y, z - 1) == Witchery.Blocks.SAPLING) && ((world.func_72805_g(x, y, z - 1) & 0x3) == 1) ? 1 : 0);
/*     */     
/* 534 */     int watercount = 0;
/* 535 */     watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/* 537 */     if ((vineCount < 4) || (watercount < 1)) {
/* 538 */       return false;
/*     */     }
/*     */     
/* 541 */     AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
/* 542 */     List list = world.func_72872_a(EntityZombie.class, aabb);
/* 543 */     if (list.size() == 0) {
/* 544 */       return false;
/*     */     }
/* 546 */     if (!world.field_72995_K) {
/* 547 */       EntityZombie zombie = (EntityZombie)list.get(0);
/* 548 */       ParticleEffect.SLIME.send(SoundEffect.MOB_ZOMBIE_DEATH, zombie, 3.0D, 2.0D, 8);
/* 549 */       zombie.func_70106_y();
/*     */     }
/*     */     
/*     */ 
/* 553 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableToMindrake(World world, int x, int y, int z) {
/* 557 */     Block blockID = world.func_147439_a(x, y, z);
/* 558 */     if (blockID != Blocks.field_150321_G) {
/* 559 */       return false;
/*     */     }
/*     */     
/* 562 */     int vineCount = 0;
/* 563 */     vineCount += ((world.func_147439_a(x + 1, y, z) == Witchery.Blocks.CROP_MANDRAKE) && (world.func_72805_g(x + 1, y, z) == Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
/* 564 */     vineCount += ((world.func_147439_a(x - 1, y, z) == Witchery.Blocks.CROP_MANDRAKE) && (world.func_72805_g(x - 1, y, z) == Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
/* 565 */     vineCount += ((world.func_147439_a(x, y, z + 1) == Witchery.Blocks.CROP_MANDRAKE) && (world.func_72805_g(x, y, z + 1) == Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
/* 566 */     vineCount += ((world.func_147439_a(x, y, z - 1) == Witchery.Blocks.CROP_MANDRAKE) && (world.func_72805_g(x, y, z - 1) == Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
/*     */     
/* 568 */     int watercount = 0;
/* 569 */     watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/* 571 */     int grasperAttunedCount = 0;
/* 572 */     ItemStack pearl = Witchery.Items.GENERIC.itemAttunedStoneCharged.createStack();
/* 573 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 574 */     grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 575 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 576 */     grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 578 */     int grasperCount = 0;
/* 579 */     pearl = Witchery.Items.GENERIC.itemMutandisExtremis.createStack();
/* 580 */     grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 581 */     grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 582 */     grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 583 */     grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 585 */     int grasperTongueCount = 0;
/* 586 */     pearl = Witchery.Items.GENERIC.itemFocusedWill.createStack();
/* 587 */     grasperTongueCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 588 */     grasperTongueCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 589 */     grasperTongueCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 590 */     grasperTongueCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 592 */     if ((vineCount < 4) || (watercount < 1) || (grasperCount < 2) || (grasperAttunedCount < 1) || (grasperTongueCount < 1)) {
/* 593 */       return false;
/*     */     }
/*     */     
/* 596 */     EntityCreeper creeper = null;
/* 597 */     AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
/* 598 */     List list = world.func_72872_a(EntityCreeper.class, aabb);
/* 599 */     if (list.size() == 0) {
/* 600 */       return false;
/*     */     }
/* 602 */     if (!world.field_72995_K) {
/* 603 */       creeper = (EntityCreeper)list.get(0);
/*     */     }
/*     */     
/*     */ 
/* 607 */     EntityMandrake mandrake = null;
/* 608 */     aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
/* 609 */     list = world.func_72872_a(EntityMandrake.class, aabb);
/* 610 */     if (list.size() == 0) {
/* 611 */       return false;
/*     */     }
/* 613 */     if (!world.field_72995_K) {
/* 614 */       mandrake = (EntityMandrake)list.get(0);
/*     */     }
/*     */     
/*     */ 
/* 618 */     if ((creeper != null) && (mandrake != null)) {
/* 619 */       ParticleEffect.SLIME.send(SoundEffect.MOB_CREEPER_DEATH, creeper, 3.0D, 2.0D, 8);
/* 620 */       creeper.func_70106_y();
/*     */       
/* 622 */       ParticleEffect.SLIME.send(SoundEffect.MOB_GHAST_DEATH, mandrake, 3.0D, 2.0D, 8);
/* 623 */       mandrake.func_70106_y();
/*     */     } else {
/* 625 */       return false;
/*     */     }
/*     */     
/* 628 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableTrapChest(World world, int x, int y, int z) {
/* 632 */     Block blockID = world.func_147439_a(x, y, z);
/* 633 */     if (blockID != Blocks.field_150447_bR) {
/* 634 */       return false;
/*     */     }
/*     */     
/* 637 */     int vineCount = 0;
/* 638 */     vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150395_bd ? 1 : 0);
/* 639 */     vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150395_bd ? 1 : 0);
/* 640 */     vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150395_bd ? 1 : 0);
/* 641 */     vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150395_bd ? 1 : 0);
/*     */     
/* 643 */     int watercount = 0;
/* 644 */     watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 645 */     watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 646 */     watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
/* 647 */     watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/* 649 */     if ((vineCount < 4) || (watercount < 4)) {
/* 650 */       return false;
/*     */     }
/*     */     
/* 653 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 654 */     if ((tile == null) || (!(tile instanceof TileEntityChest))) {
/* 655 */       return false;
/*     */     }
/*     */     
/* 658 */     TileEntityChest chest = (TileEntityChest)tile;
/* 659 */     for (int i = 0; i < chest.func_70302_i_(); i++) {
/* 660 */       if (chest.func_70301_a(i) != null) {
/* 661 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 665 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableWheat(World world, int x, int y, int z) {
/* 669 */     Block block = BlockUtil.getBlock(world, x, y, z);
/* 670 */     if ((block != Blocks.field_150464_aj) || (BlockUtil.getBlockMetadata(world, x, y, z) != 7)) {
/* 671 */       return false;
/*     */     }
/*     */     
/* 674 */     int vineCount = 0;
/* 675 */     vineCount += (BlockUtil.getBlock(world, x + 1, y, z) == Witchery.Blocks.WISPY_COTTON ? 1 : 0);
/* 676 */     vineCount += (BlockUtil.getBlock(world, x - 1, y, z) == Witchery.Blocks.WISPY_COTTON ? 1 : 0);
/* 677 */     vineCount += (BlockUtil.getBlock(world, x, y, z + 1) == Witchery.Blocks.WISPY_COTTON ? 1 : 0);
/* 678 */     vineCount += (BlockUtil.getBlock(world, x, y, z - 1) == Witchery.Blocks.WISPY_COTTON ? 1 : 0);
/*     */     
/* 680 */     int watercount = 0;
/* 681 */     watercount += (BlockUtil.getBlock(world, x + 1, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
/* 682 */     watercount += (BlockUtil.getBlock(world, x + 1, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
/* 683 */     watercount += (BlockUtil.getBlock(world, x - 1, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
/* 684 */     watercount += (BlockUtil.getBlock(world, x - 1, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/* 686 */     if ((vineCount < 4) || (watercount < 4)) {
/* 687 */       return false;
/*     */     }
/*     */     
/* 690 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableLeechChest(World world, int x, int y, int z) {
/* 694 */     Block blockID = world.func_147439_a(x, y, z);
/* 695 */     if (blockID != Witchery.Blocks.LEECH_CHEST) {
/* 696 */       return false;
/*     */     }
/*     */     
/* 699 */     int vineCount = 0;
/* 700 */     vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150328_O ? 1 : 0);
/* 701 */     vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150328_O ? 1 : 0);
/* 702 */     vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150328_O ? 1 : 0);
/* 703 */     vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150328_O ? 1 : 0);
/*     */     
/* 705 */     int watercount = 0;
/* 706 */     watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/*     */ 
/* 709 */     if ((vineCount < 4) || (watercount < 1)) {
/* 710 */       return false;
/*     */     }
/*     */     
/* 713 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 714 */     if ((tile == null) || (!(tile instanceof BlockLeechChest.TileEntityLeechChest))) {
/* 715 */       return false;
/*     */     }
/*     */     
/* 718 */     BlockLeechChest.TileEntityLeechChest chest = (BlockLeechChest.TileEntityLeechChest)tile;
/* 719 */     for (int i = 0; i < chest.func_70302_i_(); i++) {
/* 720 */       if (chest.func_70301_a(i) != null) {
/* 721 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 725 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableChest(World world, int x, int y, int z) {
/* 729 */     Block blockID = world.func_147439_a(x, y, z);
/* 730 */     if (blockID != Blocks.field_150486_ae) {
/* 731 */       return false;
/*     */     }
/*     */     
/* 734 */     int vineCount = 0;
/* 735 */     vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150329_H ? 1 : 0);
/* 736 */     vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150329_H ? 1 : 0);
/* 737 */     vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150329_H ? 1 : 0);
/* 738 */     vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150329_H ? 1 : 0);
/*     */     
/* 740 */     int watercount = 0;
/* 741 */     watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/* 743 */     if ((vineCount < 4) || (watercount < 1)) {
/* 744 */       return false;
/*     */     }
/*     */     
/* 747 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 748 */     if ((tile == null) || (!(tile instanceof TileEntityChest))) {
/* 749 */       return false;
/*     */     }
/*     */     
/* 752 */     TileEntityChest chest = (TileEntityChest)tile;
/* 753 */     for (int i = 0; i < chest.func_70302_i_(); i++) {
/* 754 */       if (chest.func_70301_a(i) != null) {
/* 755 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 759 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableReed(World world, int x, int y, int z) {
/* 763 */     Block blockID = world.func_147439_a(x, y, z);
/* 764 */     if (blockID != Blocks.field_150436_aH) {
/* 765 */       return false;
/*     */     }
/*     */     
/* 768 */     int vineCount = 0;
/* 769 */     vineCount += (world.func_147439_a(x + 1, y, z) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/* 770 */     vineCount += (world.func_147439_a(x - 1, y, z) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/* 771 */     vineCount += (world.func_147439_a(x, y, z + 1) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/* 772 */     vineCount += (world.func_147439_a(x, y, z - 1) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/*     */     
/* 774 */     int watercount = 0;
/* 775 */     watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 776 */     watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 777 */     watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
/* 778 */     watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/*     */ 
/* 781 */     int grasperCount = 0;
/* 782 */     ItemStack pearl = new ItemStack(Items.field_151079_bi);
/* 783 */     grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
/* 784 */     grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
/* 785 */     grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
/* 786 */     grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
/*     */     
/* 788 */     if ((vineCount < 4) || (watercount < 4) || (grasperCount < 4)) {
/* 789 */       return false;
/*     */     }
/*     */     
/* 792 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isMutatableCactus(World world, int x, int y, int z) {
/* 796 */     Block blockID = world.func_147439_a(x, y, z);
/* 797 */     if (blockID != Blocks.field_150434_aF) {
/* 798 */       return false;
/*     */     }
/*     */     
/* 801 */     int vineCount = 0;
/* 802 */     vineCount += (world.func_147439_a(x + 1, y, z) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/* 803 */     vineCount += (world.func_147439_a(x - 1, y, z) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/* 804 */     vineCount += (world.func_147439_a(x, y, z + 1) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/* 805 */     vineCount += (world.func_147439_a(x, y, z - 1) == Witchery.Blocks.SPANISH_MOSS ? 1 : 0);
/*     */     
/* 807 */     int watercount = 0;
/* 808 */     watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 809 */     watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
/* 810 */     watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
/* 811 */     watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
/*     */     
/*     */ 
/* 814 */     int boneMeal = 0;
/* 815 */     ItemStack bone = Dye.BONE_MEAL.createStack();
/* 816 */     boneMeal += (isGrasperWith(world, x + 1, y, z + 1, bone) ? 1 : 0);
/* 817 */     boneMeal += (isGrasperWith(world, x + 1, y, z - 1, bone) ? 1 : 0);
/* 818 */     boneMeal += (isGrasperWith(world, x - 1, y, z + 1, bone) ? 1 : 0);
/* 819 */     boneMeal += (isGrasperWith(world, x - 1, y, z - 1, bone) ? 1 : 0);
/*     */     
/* 821 */     int blazePowder = 0;
/* 822 */     ItemStack blaze = new ItemStack(Items.field_151065_br);
/* 823 */     blazePowder += (isGrasperWith(world, x + 1, y, z + 1, blaze) ? 1 : 0);
/* 824 */     blazePowder += (isGrasperWith(world, x + 1, y, z - 1, blaze) ? 1 : 0);
/* 825 */     blazePowder += (isGrasperWith(world, x - 1, y, z + 1, blaze) ? 1 : 0);
/* 826 */     blazePowder += (isGrasperWith(world, x - 1, y, z - 1, blaze) ? 1 : 0);
/*     */     
/* 828 */     if ((vineCount < 4) || (watercount < 4) || (boneMeal < 2) || (blazePowder < 2)) {
/* 829 */       return false;
/*     */     }
/*     */     
/* 832 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isGrasperWith(World world, int x, int y, int z, ItemStack stack) {
/* 836 */     Block blockID = world.func_147439_a(x, y, z);
/* 837 */     if (blockID == Witchery.Blocks.GRASSPER) {
/* 838 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 839 */       if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
/* 840 */         BlockGrassper.TileEntityGrassper grassperTile = (BlockGrassper.TileEntityGrassper)tile;
/* 841 */         ItemStack foundStack = grassperTile.func_70301_a(0);
/* 842 */         return (foundStack != null) && (foundStack.func_77969_a(stack));
/*     */       }
/*     */     }
/*     */     
/* 846 */     return false;
/*     */   }
/*     */   
/*     */   private void clearGrassperAt(World world, int x, int y, int z) {
/* 850 */     Block blockID = world.func_147439_a(x, y, z);
/* 851 */     if (blockID == Witchery.Blocks.GRASSPER) {
/* 852 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 853 */       if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
/* 854 */         BlockGrassper.TileEntityGrassper grassperTile = (BlockGrassper.TileEntityGrassper)tile;
/* 855 */         grassperTile.func_70299_a(0, null);
/* 856 */         ParticleEffect.SLIME.send(SoundEffect.NONE, world, 0.5D + x, y, 0.5D + z, 1.0D, 2.0D, 8);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemMutator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */