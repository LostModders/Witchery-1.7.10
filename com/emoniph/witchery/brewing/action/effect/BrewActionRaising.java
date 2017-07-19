/*     */ package com.emoniph.witchery.brewing.action.effect;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.AltarPower;
/*     */ import com.emoniph.witchery.brewing.BrewItemKey;
/*     */ import com.emoniph.witchery.brewing.BrewNamePart;
/*     */ import com.emoniph.witchery.brewing.EffectLevel;
/*     */ import com.emoniph.witchery.brewing.ModifiersEffect;
/*     */ import com.emoniph.witchery.brewing.Probability;
/*     */ import com.emoniph.witchery.brewing.action.BrewActionEffect;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.monster.EntityPigZombie;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BrewActionRaising extends BrewActionEffect
/*     */ {
/*     */   public BrewActionRaising(Item axe, AltarPower powerCost, EffectLevel effectLevel)
/*     */   {
/*  34 */     super(new BrewItemKey(axe, 32767), new BrewNamePart("witchery:brew.raising"), powerCost, new Probability(1.0D), effectLevel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void doApplyToBlock(World world, int posX, int posY, int posZ, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
/*     */   {
/*  41 */     raiseDead(world, new Coord(posX, posY, posZ, side), modifiers.ritualised ? 0 : modifiers.getStrength(), modifiers.caster, modifiers.ritualised ? com.emoniph.witchery.util.TimeUtil.secsToTicks(10 * (modifiers.getStrength() + 1)) : 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void raiseDead(World world, Coord coord, int strength, EntityPlayer raiser, int lifetime)
/*     */   {
/*  48 */     int MAX_DISTANCE = 3;
/*  49 */     int MAX_DROP = 6;
/*     */     
/*  51 */     raiseUndead(world, coord, raiser, lifetime);
/*     */     
/*  53 */     int extraCount = 0;
/*  54 */     double chance = world.field_73012_v.nextDouble();
/*  55 */     if ((strength >= 1) && (world.field_73012_v.nextDouble() < strength * 0.5D)) {
/*  56 */       extraCount++;
/*     */     }
/*     */     
/*  59 */     if ((strength >= 2) && (world.field_73012_v.nextDouble() < strength * 0.25D)) {
/*  60 */       extraCount++;
/*     */     }
/*     */     
/*  63 */     if ((strength >= 3) && (world.field_73012_v.nextDouble() < strength * 0.25D)) {
/*  64 */       extraCount++;
/*     */     }
/*     */     
/*  67 */     for (int i = 0; i < extraCount; i++) {
/*  68 */       int x = coord.x - 3 + world.field_73012_v.nextInt(6) + 1;
/*  69 */       int z = coord.z - 3 + world.field_73012_v.nextInt(6) + 1;
/*  70 */       int dy = coord.y + 6; for (int minY = coord.y - 6; dy >= minY; dy--) {
/*  71 */         if ((world.func_147439_a(x, dy - 1, z).func_149688_o().func_76220_a()) && (world.func_147437_c(x, dy, z))) {
/*  72 */           raiseUndead(world, new Coord(x, dy, z), raiser, lifetime);
/*  73 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void raiseUndead(World world, Coord coord, EntityPlayer thrower, int lifetime) {
/*  80 */     if (!world.field_72995_K) {
/*  81 */       EntityLiving undeadEntity = createUndeadCreature(world);
/*  82 */       undeadEntity.func_70012_b(0.5D + coord.x, 0.1D + coord.y, 0.5D + coord.z, 0.0F, 0.0F);
/*  83 */       IEntityLivingData entitylivingData = null;
/*  84 */       entitylivingData = undeadEntity.func_110161_a(entitylivingData);
/*  85 */       EntityUtil.persistanceRequired(undeadEntity);
/*  86 */       EntityUtil.setNoDrops(undeadEntity);
/*  87 */       if (lifetime > 0) {
/*  88 */         undeadEntity.func_70690_d(new PotionEffect(Witchery.Potions.MORTAL_COIL.field_76415_H, lifetime));
/*     */       }
/*     */       
/*  91 */       if (thrower != null) {
/*     */         try {
/*  93 */           com.emoniph.witchery.brewing.potions.PotionEnslaved.setEnslaverForMob(undeadEntity, thrower);
/*     */         } catch (Exception e) {
/*  95 */           Log.instance().warning(e, "Unhandled exception occurred setting enslaver from raiseUnded potion.");
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 100 */       world.func_72838_d(undeadEntity);
/* 101 */       ParticleEffect.LARGE_SMOKE.send(com.emoniph.witchery.util.SoundEffect.NONE, undeadEntity, 0.5D, 2.0D, 16);
/*     */     }
/*     */   }
/*     */   
/*     */   private static EntityLiving createUndeadCreature(World world) {
/* 106 */     double value = world.field_73012_v.nextDouble();
/* 107 */     if (value < 0.6D)
/* 108 */       return new EntityZombie(world);
/* 109 */     if (value < 0.97D) {
/* 110 */       return new net.minecraft.entity.monster.EntitySkeleton(world);
/*     */     }
/* 112 */     return new EntityPigZombie(world);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/action/effect/BrewActionRaising.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */