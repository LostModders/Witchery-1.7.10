/*     */ package com.emoniph.witchery.brewing.action.effect;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.BrewNamePart;
/*     */ import com.emoniph.witchery.brewing.EffectLevel;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.ModifiersRitual;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionRitual;
/*     */ import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.BlockPosition;
/*     */ import com.emoniph.witchery.util.BlockProtect;
/*     */ import com.emoniph.witchery.util.CircleUtil;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BrewActionTranspose extends BrewActionEffect
/*     */ {
/*     */   public BrewActionTranspose(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel)
/*     */   {
/*  31 */     super(itemKey, namePart, powerCost, baseProbability, effectLevel);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isRitualTargetLocationValid(MinecraftServer server, World world, int x, int y, int z, BlockPosition target, ModifiersRitual modifiers)
/*     */   {
/*  37 */     return (BrewActionRitual.isDistanceAllowed(world, x, y, z, target, modifiers.covenSize, modifiers.leonard)) && (CircleUtil.isMediumCircle(target.getWorld(server), target.x, target.y, target.z, com.emoniph.witchery.Witchery.Blocks.GLYPH_OTHERWHERE));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritual, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  45 */     int height = 3;
/*  46 */     BlockPosition midSource = ritual.getTarget();
/*  47 */     BlockPosition midTarget = ritual.getTarget(1);
/*  48 */     World worldSource = midSource.getWorld(MinecraftServer.func_71276_C());
/*  49 */     World worldTarget = midTarget.getWorld(MinecraftServer.func_71276_C());
/*  50 */     for (int dy = 0; dy < 3; dy++) {
/*  51 */       for (int dx = -3; dx <= 3; dx++) {
/*  52 */         for (int dz = -3; dz <= 3; dz++) {
/*  53 */           if (dx * dx + dy * dz < 9) {
/*  54 */             int sx = midSource.x + dx;
/*  55 */             int sy = midSource.y + dy;
/*  56 */             int sz = midSource.z + dz;
/*  57 */             int tx = midTarget.x + dx;
/*  58 */             int ty = midTarget.y + dy;
/*  59 */             int tz = midTarget.z + dz;
/*  60 */             Block block = world.func_147439_a(sx, sy, sz);
/*  61 */             int meta = world.func_72805_g(sx, sy, sz);
/*  62 */             if ((BlockProtect.checkModsForBreakOK(worldSource, sx, sy, sz, block, meta, modifiers.caster)) && (BlockProtect.canBreak(block, worldSource)) && (BlockProtect.canBreak(tx, ty, tz, worldTarget)) && (BlockProtect.checkModsForBreakOK(worldTarget, tx, ty, tz, modifiers.caster)))
/*     */             {
/*     */ 
/*     */ 
/*  66 */               world.func_147465_d(tx, ty, tz, block, meta, 3);
/*  67 */               world.func_147468_f(sx, sy, sz);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doApplyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  78 */     if (!PotionEnderInhibition.isActive(targetEntity, 3)) {
/*  79 */       BlockPosition target = ritualModifiers.getTarget();
/*  80 */       ItemGeneral.teleportToLocation(targetEntity.field_70170_p, target.x, target.y, target.z, target.dimension, targetEntity, true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
/*     */   {
/*  93 */     if (!PotionEnderInhibition.isActive(targetEntity, modifiers.getStrength())) {
/*  94 */       teleportAway(world, new BlockPosition(world, modifiers.impactLocation != null ? modifiers.impactLocation : new com.emoniph.witchery.util.EntityPosition(targetEntity)), targetEntity, 10 * (modifiers.getStrength() + 1));
/*     */     }
/*     */   }
/*     */   
/*     */   private void teleportAway(World world, BlockPosition position, EntityLivingBase entity, int range)
/*     */   {
/* 100 */     if (!world.field_72995_K) {
/* 101 */       int distance = range;
/* 102 */       int doubleDistance = distance * 2;
/* 103 */       int posX = position.x;
/* 104 */       int posY = position.y;
/* 105 */       int posZ = position.z;
/*     */       
/* 107 */       for (int attempt = 0; attempt < 3; attempt++) {
/* 108 */         posX += world.field_73012_v.nextInt(doubleDistance) - distance;
/* 109 */         posZ += world.field_73012_v.nextInt(doubleDistance) - distance;
/* 110 */         int maxY = Math.min(posY + 64, 250);
/* 111 */         while ((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) && (posY >= 0)) {
/* 112 */           posY--;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 117 */         while (((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) || (world.func_147439_a(posX, posY, posZ) == net.minecraft.init.Blocks.field_150357_h) || (!world.func_147437_c(posX, posY + 1, posZ)) || (!world.func_147437_c(posX, posY + 2, posZ)) || (!world.func_147437_c(posX, posY + 3, posZ))) && (posY < maxY)) {
/* 118 */           posY++;
/*     */         }
/* 120 */         if ((posY > 0) && (posY < maxY)) {
/* 121 */           ItemGeneral.teleportToLocation(world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, world.field_73011_w.field_76574_g, entity, true);
/*     */           
/* 123 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionTranspose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */