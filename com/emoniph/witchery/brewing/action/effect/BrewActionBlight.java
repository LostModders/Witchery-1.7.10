/*     */ package com.emoniph.witchery.brewing.action.effect;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.BrewNamePart;
/*     */ import com.emoniph.witchery.brewing.EffectLevel;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*     */ import com.emoniph.witchery.util.BlockActionCircle;
/*     */ import com.emoniph.witchery.util.BlockProtect;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityMooshroom;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
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
/*     */ public class BrewActionBlight
/*     */   extends BrewActionEffect
/*     */ {
/*     */   public BrewActionBlight(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel)
/*     */   {
/*  44 */     super(itemKey, namePart, powerCost, baseProbability, effectLevel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  51 */     if (BlockUtil.isReplaceableBlock(world, x, y, z)) {
/*  52 */       y--;
/*     */     }
/*  54 */     new BlockActionCircle()
/*     */     {
/*     */       public void onBlock(World world, int x, int y, int z) {
/*  57 */         if (BlockProtect.checkModsForBreakOK(world, x, y, z, modifiers.caster)) {
/*  58 */           Block blockID = world.func_147439_a(x, y, z);
/*  59 */           Block blockBelowID = world.func_147439_a(x, y - 1, z);
/*  60 */           if (blockID == Blocks.field_150329_H) {
/*  61 */             world.func_147468_f(x, y, z);
/*  62 */             BrewActionBlight.this.blightGround(world, x, y - 1, z, blockBelowID);
/*  63 */           } else if ((blockID == Blocks.field_150328_O) || (blockID == Blocks.field_150327_N) || (blockID == Blocks.field_150459_bM) || (blockID == Blocks.field_150464_aj) || (blockID == Blocks.field_150469_bN) || (blockID == Blocks.field_150393_bb) || (blockID == Blocks.field_150394_bc) || (blockID == Blocks.field_150440_ba) || (blockID == Blocks.field_150423_aK) || (blockID == Blocks.field_150398_cm))
/*     */           {
/*     */ 
/*     */ 
/*  67 */             world.func_147449_b(x, y, z, Blocks.field_150330_I);
/*  68 */             BrewActionBlight.this.blightGround(world, x, y - 1, z, blockBelowID);
/*  69 */           } else if (blockID == Blocks.field_150458_ak) {
/*  70 */             world.func_147449_b(x, y, z, Blocks.field_150354_m);
/*  71 */           } else if (blockID.func_149688_o().func_76220_a()) {
/*  72 */             BrewActionBlight.this.blightGround(world, x, y, z, blockID);
/*  73 */           } else if (blockBelowID.func_149688_o().func_76220_a()) {
/*  74 */             BrewActionBlight.this.blightGround(world, x, y - 1, z, blockBelowID); } } } }.processFilledCircle(world, x, y + 1, z, radius);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*     */   {
/*  84 */     if (((targetEntity instanceof EntityVillager)) && (world.field_73012_v.nextInt(10 - modifiers.getStrength() * 2) == 0)) {
/*  85 */       EntityZombie entityzombie = new EntityZombie(world);
/*  86 */       entityzombie.func_82149_j(targetEntity);
/*  87 */       world.func_72900_e(targetEntity);
/*  88 */       entityzombie.func_110161_a((IEntityLivingData)null);
/*  89 */       entityzombie.func_82229_g(true);
/*  90 */       if (targetEntity.func_70631_g_()) {
/*  91 */         entityzombie.func_82227_f(true);
/*     */       }
/*  93 */       world.func_72838_d(entityzombie);
/*  94 */       world.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
/*  95 */     } else if (((targetEntity instanceof EntityCow)) && (world.field_73012_v.nextInt(20 - modifiers.getStrength() * 3) == 0)) {
/*  96 */       EntityMooshroom entityzombie = new EntityMooshroom(world);
/*  97 */       entityzombie.func_82149_j(targetEntity);
/*  98 */       world.func_72900_e(targetEntity);
/*  99 */       entityzombie.func_110161_a((IEntityLivingData)null);
/* 100 */       world.func_72838_d(entityzombie);
/* 101 */       world.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
/* 102 */     } else if ((targetEntity instanceof EntityAnimal)) {
/* 103 */       if (world.field_73012_v.nextInt(modifiers.getStrength() > 1 ? 2 : 3) == 0) {
/* 104 */         targetEntity.func_70097_a(DamageSource.field_76376_m, 20.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void blightGround(World world, int posX, int posY, int posZ, Block blockBelowID) {
/* 110 */     if ((blockBelowID == Blocks.field_150346_d) || (blockBelowID == Blocks.field_150349_c) || (blockBelowID == Blocks.field_150391_bh) || (blockBelowID == Blocks.field_150458_ak))
/*     */     {
/* 112 */       int rand = world.field_73012_v.nextInt(5);
/* 113 */       if (rand == 0) {
/* 114 */         world.func_147449_b(posX, posY, posZ, Blocks.field_150354_m);
/* 115 */       } else if (rand == 1) {
/* 116 */         world.func_147449_b(posX, posY, posZ, Blocks.field_150346_d);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionBlight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */