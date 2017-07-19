/*     */ package com.emoniph.witchery.brewing.action.effect;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.BrewNamePart;
/*     */ import com.emoniph.witchery.brewing.EffectLevel;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BrewActionSprouting extends BrewActionEffect
/*     */ {
/*     */   public BrewActionSprouting(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel)
/*     */   {
/*  34 */     super(itemKey, namePart, powerCost, baseProbability, effectLevel);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
/*     */   {
/*  40 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x, y, z, x + 1, y + 1, z + 1);
/*  41 */     growBranch(x, y, z, world, side.ordinal(), 8 + 2 * modifiers.getStrength(), bounds);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*     */   {
/*  47 */     targetEntity.func_70690_d(new PotionEffect(Witchery.Potions.SPROUTING.field_76415_H, modifiers.getModifiedDuration(TimeUtil.secsToTicks(15)), modifiers.getStrength()));
/*     */   }
/*     */   
/*     */   public static void growBranch(EntityLivingBase entity, int extent)
/*     */   {
/*  52 */     Coord coord = new Coord(entity);
/*  53 */     growBranch(coord.x, coord.y - 1, coord.z, entity.field_70170_p, ForgeDirection.UP.ordinal(), extent, entity.field_70121_D.func_72314_b(0.5D, 0.5D, 0.5D));
/*     */   }
/*     */   
/*     */ 
/*     */   public static void growBranch(int posX, int posY, int posZ, World world, int sideHit, int extent, AxisAlignedBB boundingBox)
/*     */   {
/*  59 */     if (world.field_72995_K) {
/*  60 */       return;
/*     */     }
/*     */     
/*  63 */     Block blockID = world.func_147439_a(posX, posY, posZ);
/*  64 */     int j1 = world.func_72805_g(posX, posY, posZ);
/*  65 */     Block logBlock; Block logBlock; if ((blockID == Blocks.field_150364_r) || (blockID == Blocks.field_150344_f) || (blockID == Blocks.field_150345_g) || (blockID == Blocks.field_150362_t))
/*     */     {
/*  67 */       logBlock = Blocks.field_150364_r; } else { Block logBlock;
/*  68 */       if ((blockID == Witchery.Blocks.LOG) || (blockID == Witchery.Blocks.PLANKS) || (blockID == Witchery.Blocks.SAPLING) || (blockID == Witchery.Blocks.LEAVES))
/*     */       {
/*  70 */         logBlock = Witchery.Blocks.LOG;
/*     */       } else {
/*  72 */         logBlock = world.field_73012_v.nextInt(2) == 0 ? Blocks.field_150364_r : Witchery.Blocks.LOG;
/*  73 */         j1 = world.field_73012_v.nextInt(Blocks.field_150364_r == logBlock ? 4 : 3);
/*     */       } }
/*  75 */     Block leavesBlock = Blocks.field_150364_r == logBlock ? Blocks.field_150362_t : Witchery.Blocks.LEAVES;
/*  76 */     int b0 = 0;
/*  77 */     j1 &= 0x3;
/*     */     
/*  79 */     switch (sideHit) {
/*     */     case 0: 
/*     */     case 1: 
/*  82 */       b0 = 0;
/*  83 */       break;
/*     */     case 2: 
/*     */     case 3: 
/*  86 */       b0 = 8;
/*  87 */       break;
/*     */     case 4: 
/*     */     case 5: 
/*  90 */       b0 = 4;
/*     */     }
/*     */     
/*  93 */     int meta = j1 | b0;
/*     */     
/*  95 */     ParticleEffect particleEffect = ParticleEffect.EXPLODE;
/*     */     
/*  97 */     int dx = sideHit == 4 ? -1 : sideHit == 5 ? 1 : 0;
/*  98 */     int dy = sideHit == 1 ? 1 : sideHit == 0 ? -1 : 0;
/*  99 */     int dz = sideHit == 2 ? -1 : sideHit == 3 ? 1 : 0;
/*     */     
/* 101 */     int sproutExtent = extent;
/*     */     
/* 103 */     boolean isInitialBlockSolid = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a();
/*     */     
/*     */ 
/* 106 */     for (int i = (sideHit == 1) && (!isInitialBlockSolid) ? 0 : 1; i <= sproutExtent; i++) {
/* 107 */       int x = posX + i * dx;
/* 108 */       int y = posY + i * dy;
/* 109 */       int z = posZ + i * dz;
/* 110 */       if (y >= 255) {
/*     */         break;
/*     */       }
/*     */       
/* 114 */       if (!BlockUtil.setBlockIfReplaceable(world, x, y, z, logBlock, meta)) {
/*     */         break;
/*     */       }
/* 117 */       int lx = (dx == 0) && (world.field_73012_v.nextInt(4) == 0) ? world.field_73012_v.nextInt(3) - 1 : 0;
/* 118 */       int ly = (dy == 0) && (lx == 0) && (world.field_73012_v.nextInt(4) == 0) ? world.field_73012_v.nextInt(3) - 1 : 0;
/* 119 */       int lz = (dz == 0) && (lx == 0) && (ly == 0) && (world.field_73012_v.nextInt(4) == 0) ? world.field_73012_v.nextInt(3) - 1 : 0;
/*     */       
/* 121 */       if ((lx != 0) || (ly != 0) || (lz != 0)) {
/* 122 */         BlockUtil.setBlockIfReplaceable(world, x + lx, y + ly, z + lz, leavesBlock, meta);
/*     */       }
/*     */     }
/*     */     
/* 126 */     if (sideHit == 1) {
/* 127 */       AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(0.0D, 2.0D, 0.0D);
/* 128 */       List<EntityLivingBase> list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
/*     */       
/* 130 */       if ((list1 != null) && (!list1.isEmpty())) {
/* 131 */         Iterator<EntityLivingBase> iterator = list1.iterator();
/* 132 */         int x = posX + i * dx;
/* 133 */         int y = Math.min(posY + i * dy, 255);
/* 134 */         int z = posZ + i * dz;
/* 135 */         while (iterator.hasNext()) {
/* 136 */           EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
/* 137 */           if ((!world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a()) && (!world.func_147439_a(x, y + 2, z).func_149688_o().func_76220_a()))
/*     */           {
/* 139 */             if ((entitylivingbase instanceof net.minecraft.entity.player.EntityPlayer)) {
/* 140 */               entitylivingbase.func_70634_a(0.5D + x, y + 2, 0.5D + z);
/*     */             } else {
/* 142 */               entitylivingbase.func_70634_a(0.5D + x, y + 2, 0.5D + z);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionSprouting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */