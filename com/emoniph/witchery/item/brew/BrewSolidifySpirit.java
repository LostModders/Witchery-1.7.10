/*     */ package com.emoniph.witchery.item.brew;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.item.ItemGeneral.Brew.BrewResult;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BrewSolidifySpirit extends com.emoniph.witchery.item.ItemGeneral.Brew
/*     */ {
/*     */   protected Block replacementBlock;
/*     */   
/*     */   public BrewSolidifySpirit(int subItemID, String unlocalisedName, Block block)
/*     */   {
/*  20 */     super(subItemID, unlocalisedName);
/*  21 */     this.replacementBlock = block;
/*     */   }
/*     */   
/*     */   public boolean isSolidifier()
/*     */   {
/*  26 */     return true;
/*     */   }
/*     */   
/*     */   public ItemGeneral.Brew.BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
/*  30 */     if (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) {
/*  31 */       return ItemGeneral.Brew.BrewResult.DROP_ITEM;
/*     */     }
/*     */     
/*  34 */     Block blockHit = BlockUtil.getBlock(world, mop);
/*  35 */     int x = mop.field_72311_b;
/*  36 */     int y = mop.field_72312_c;
/*  37 */     int z = mop.field_72309_d;
/*  38 */     if (blockHit != Witchery.Blocks.HOLLOW_TEARS) {
/*  39 */       switch (mop.field_72310_e) {
/*     */       case 0: 
/*  41 */         y--;
/*  42 */         break;
/*     */       case 1: 
/*  44 */         y++;
/*  45 */         break;
/*     */       
/*     */       case 2: 
/*  48 */         z--;
/*  49 */         break;
/*     */       case 3: 
/*  51 */         z++;
/*  52 */         break;
/*     */       case 4: 
/*  54 */         x--;
/*  55 */         break;
/*     */       case 5: 
/*  57 */         x++;
/*     */       }
/*     */       
/*     */       
/*  61 */       blockHit = BlockUtil.getBlock(world, x, y, z);
/*  62 */       if (blockHit != Witchery.Blocks.HOLLOW_TEARS) {
/*  63 */         return ItemGeneral.Brew.BrewResult.DROP_ITEM;
/*     */       }
/*     */     }
/*     */     
/*  67 */     SpreadEffect.spread(world, x, y, z, 64, new SpreadEffect(new Block[] { Witchery.Blocks.HOLLOW_TEARS })
/*     */     {
/*     */       public boolean doEffect(World world, int posX, int posY, int posZ, Block block) {
/*  70 */         ParticleEffect.INSTANT_SPELL.send(com.emoniph.witchery.util.SoundEffect.NONE, world, 0.5D + posX, 1.5D + posY, 0.5D + posZ, 2.0D, 2.0D, 16);
/*     */         
/*  72 */         if (BrewSolidifySpirit.this.replacementBlock == null) {
/*  73 */           world.func_147468_f(posX, posY, posZ);
/*  74 */           Block blockBelow = BlockUtil.getBlock(world, posX, posY - 1, posZ);
/*  75 */           if ((blockBelow != null) && (com.emoniph.witchery.util.BlockProtect.canBreak(blockBelow, world))) {
/*  76 */             world.func_147468_f(posX, posY - 1, posZ);
/*     */           }
/*     */         } else {
/*  79 */           BlockUtil.setBlock(world, posX, posY, posZ, BrewSolidifySpirit.this.replacementBlock, 0, 3);
/*     */         }
/*  81 */         return true;
/*     */       }
/*     */       
/*     */ 
/*  85 */     });
/*  86 */     return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
/*     */   }
/*     */   
/*     */   public static abstract class SpreadEffect {
/*     */     protected Block[] blocks;
/*     */     
/*  92 */     public SpreadEffect(Block... blocksToSpreadOver) { this.blocks = blocksToSpreadOver; }
/*     */     
/*     */     public abstract boolean doEffect(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Block paramBlock);
/*     */     
/*     */     public static void spread(World world, int x0, int y0, int z0, int range, SpreadEffect effect)
/*     */     {
/*  98 */       spread(world, x0, y0, z0, x0, y0, z0, range, effect);
/*     */     }
/*     */     
/*     */     private static void spread(World world, int x, int y, int z, int x0, int y0, int z0, int range, SpreadEffect effect) {
/* 102 */       if ((Math.abs(x0 - x) >= range) || (Math.abs(y0 - y) >= range) || (Math.abs(z0 - z) >= range)) {
/* 103 */         return;
/*     */       }
/* 105 */       if (checkEffect(world, x + 1, y, z, effect)) {
/* 106 */         spread(world, x + 1, y, z, x0, y0, z0, range, effect);
/*     */       }
/*     */       
/* 109 */       if (checkEffect(world, x - 1, y, z, effect)) {
/* 110 */         spread(world, x - 1, y, z, x0, y0, z0, range, effect);
/*     */       }
/*     */       
/* 113 */       if (checkEffect(world, x, y, z + 1, effect)) {
/* 114 */         spread(world, x, y, z + 1, x0, y0, z0, range, effect);
/*     */       }
/*     */       
/* 117 */       if (checkEffect(world, x, y, z - 1, effect)) {
/* 118 */         spread(world, x, y, z - 1, x0, y0, z0, range, effect);
/*     */       }
/*     */       
/* 121 */       if (checkEffect(world, x, y + 1, z, effect)) {
/* 122 */         spread(world, x, y + 1, z, x0, y0, z0, range, effect);
/*     */       }
/*     */       
/* 125 */       if (checkEffect(world, x, y - 1, z, effect)) {
/* 126 */         spread(world, x, y - 1, z, x0, y0, z0, range, effect);
/*     */       }
/*     */     }
/*     */     
/*     */     private static boolean checkEffect(World world, int x, int y, int z, SpreadEffect effect) {
/* 131 */       boolean continueSpread = false;
/*     */       
/* 133 */       Block foundBlock = BlockUtil.getBlock(world, x, y, z);
/* 134 */       if (foundBlock == null) {
/* 135 */         return continueSpread;
/*     */       }
/*     */       
/* 138 */       for (Block block : effect.blocks) {
/* 139 */         if (foundBlock == block) {
/* 140 */           continueSpread = effect.doEffect(world, x, y, z, block);
/* 141 */           break;
/*     */         }
/*     */       }
/*     */       
/* 145 */       return continueSpread;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/brew/BrewSolidifySpirit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */