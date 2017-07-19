/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.IPowerSource;
/*     */ import com.emoniph.witchery.common.ServerTickEvents.ServerTickTask;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.EntityPosition;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityItemWaystone extends EntityItem
/*     */ {
/*     */   public EntityItemWaystone(World world)
/*     */   {
/*  33 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityItemWaystone(World world, double x, double y, double z) {
/*  37 */     super(world, x, y, z);
/*     */   }
/*     */   
/*     */   public EntityItemWaystone(World world, double x, double y, double z, ItemStack stack) {
/*  41 */     super(world, x, y, z, stack);
/*     */   }
/*     */   
/*     */   public EntityItemWaystone(EntityItem entityItem) {
/*  45 */     super(entityItem.field_70170_p, entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, entityItem.func_92059_d());
/*  46 */     this.field_145804_b = entityItem.field_145804_b;
/*  47 */     this.field_70159_w = entityItem.field_70159_w;
/*  48 */     this.field_70181_x = entityItem.field_70181_x;
/*  49 */     this.field_70179_y = entityItem.field_70179_y;
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer player)
/*     */   {
/*  54 */     double minPickupRange = 0.75D;
/*  55 */     double minPickupRangeSq = 0.5625D;
/*  56 */     if (func_70068_e(player) <= 0.5625D) {
/*  57 */       super.func_70100_b_(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  63 */     super.func_70071_h_();
/*     */     
/*  65 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70292_b > com.emoniph.witchery.util.TimeUtil.secsToTicks(2)) && (this.field_70292_b % 40 == 0))
/*  66 */       if (Witchery.Items.GENERIC.itemWaystone.isMatch(func_92059_d())) {
/*  67 */         Block glyph = Witchery.Blocks.GLYPH_OTHERWHERE;
/*  68 */         Coord center = isTinyBlockCircle(this.field_70170_p, new Coord(this), glyph);
/*  69 */         if (center != null) {
/*  70 */           int originalStackSize = func_92059_d().field_77994_a;
/*  71 */           int remainingStackSize = 0;
/*  72 */           double R = 2.0D;
/*  73 */           double RSq = 4.0D;
/*  74 */           EntityPosition centerPoint = new EntityPosition(center.x + 0.5D, center.y + 0.5D, center.z + 0.5D);
/*  75 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(centerPoint.x - 2.0D, centerPoint.y - 2.0D, centerPoint.z - 2.0D, centerPoint.x + 2.0D, centerPoint.y + 2.0D, centerPoint.z + 2.0D);
/*     */           
/*     */ 
/*  78 */           ItemStack boundStone = null;
/*  79 */           EntityLivingBase target = null;
/*  80 */           double targetDistSq = -1.0D;
/*  81 */           List<EntityPlayer> nearbyPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds);
/*  82 */           for (EntityPlayer player : nearbyPlayers) {
/*  83 */             double distSq = player.func_70092_e(centerPoint.x, player.field_70163_u, centerPoint.z);
/*  84 */             if ((distSq <= 4.0D) && (
/*  85 */               (target == null) || (distSq < targetDistSq))) {
/*  86 */               target = player;
/*  87 */               targetDistSq = distSq;
/*     */             }
/*     */           }
/*     */           
/*  91 */           if (target == null) {
/*  92 */             List<EntityLiving> nearbyCreatures = this.field_70170_p.func_72872_a(EntityLiving.class, bounds);
/*  93 */             for (EntityLiving creature : nearbyCreatures) {
/*  94 */               double distSq = creature.func_70092_e(centerPoint.x, creature.field_70163_u, centerPoint.z);
/*  95 */               if ((distSq <= 4.0D) && (
/*  96 */                 (target == null) || (distSq < targetDistSq))) {
/*  97 */                 target = creature;
/*  98 */                 targetDistSq = distSq;
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 104 */           if (target != null) {
/* 105 */             IPowerSource power = com.emoniph.witchery.common.PowerSources.findClosestPowerSource(this.field_70170_p, center);
/* 106 */             if (power != null) {
/* 107 */               if (power.consumePower(4000.0F)) {
/* 108 */                 int convertableStackSize = Math.min(originalStackSize, 1);
/* 109 */                 remainingStackSize = originalStackSize - convertableStackSize;
/* 110 */                 boundStone = Witchery.Items.GENERIC.itemWaystonePlayerBound.createStack(convertableStackSize);
/*     */                 
/* 112 */                 Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(boundStone, null, target, false, Integer.valueOf(1));
/*     */               } else {
/* 114 */                 ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, this.field_70170_p, center, 1.0D, 1.0D, 16);
/*     */               }
/*     */             } else {
/* 117 */               ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, this.field_70170_p, center, 1.0D, 1.0D, 16);
/*     */             }
/*     */           } else {
/* 120 */             int convertableStackSize = Math.min(originalStackSize, 8);
/* 121 */             remainingStackSize = originalStackSize - convertableStackSize;
/* 122 */             boundStone = Witchery.Items.GENERIC.itemWaystoneBound.createStack(convertableStackSize);
/* 123 */             Witchery.Items.GENERIC.bindToLocation(this.field_70170_p, center.x, center.y, center.z, this.field_71093_bK, this.field_70170_p.field_73011_w.func_80007_l(), boundStone);
/*     */           }
/*     */           
/*     */ 
/* 127 */           if (boundStone != null) {
/* 128 */             EntityUtil.spawnEntityInWorld(this.field_70170_p, new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, boundStone));
/* 129 */             if (remainingStackSize > 0) {
/* 130 */               EntityUtil.spawnEntityInWorld(this.field_70170_p, new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, Witchery.Items.GENERIC.itemWaystone.createStack(remainingStackSize)));
/*     */             }
/*     */             
/*     */ 
/* 134 */             ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, this, 1.0D, 1.0D, 16);
/* 135 */             isInnerTinyBlockCircle(this.field_70170_p, center.x, center.y, center.z, glyph, true);
/* 136 */             func_70106_y();
/*     */           } } } else { Coord center;
/*     */         ItemStack usedStone;
/* 139 */         if ((Witchery.Items.GENERIC.itemWaystoneBound.isMatch(func_92059_d())) || (Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(func_92059_d())))
/*     */         {
/* 141 */           Block glyph = Witchery.Blocks.GLYPH_OTHERWHERE;
/* 142 */           center = isSmallBlockCircle(this.field_70170_p, new Coord(this), glyph);
/* 143 */           if (center != null) {
/* 144 */             double R = 4.0D;
/* 145 */             double RSq = 16.0D;
/* 146 */             usedStone = func_92059_d().func_77979_a(1);
/* 147 */             if (func_92059_d().field_77994_a > 0) {
/* 148 */               EntityUtil.spawnEntityInWorld(this.field_70170_p, new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, func_92059_d()));
/*     */             }
/*     */             
/* 151 */             func_70106_y();
/* 152 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(center.x + 0.5D - 4.0D, center.y + 0.5D - 4.0D, center.z + 0.5D - 4.0D, center.x + 0.5D + 4.0D, center.y + 0.5D + 4.0D, center.z + 0.5D + 4.0D);
/*     */             
/* 154 */             List<Entity> list = this.field_70170_p.func_72872_a(Entity.class, bounds);
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 160 */             for (Entity entity : list) {
/* 161 */               if ((!entity.field_70128_L) && (entity.func_70092_e(0.5D + center.x, entity.field_70163_u, 0.5D + center.z) <= 16.0D) && (((entity instanceof EntityLivingBase)) || ((entity instanceof EntityItem))) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(entity, 1)))
/*     */               {
/*     */ 
/* 164 */                 com.emoniph.witchery.common.ServerTickEvents.TASKS.add(new TeleportTask(this.field_70170_p, usedStone, entity));
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 169 */         else if (Witchery.Items.GENERIC.itemAttunedStone.isMatch(func_92059_d())) {
/* 170 */           Block glyph = Witchery.Blocks.GLYPH_RITUAL;
/* 171 */           Coord center = isTinyBlockCircle(this.field_70170_p, new Coord(this), glyph);
/* 172 */           if (center != null) {
/* 173 */             int originalStackSize = func_92059_d().field_77994_a;
/* 174 */             int remainingStackSize = 0;
/* 175 */             double R = 2.0D;
/* 176 */             double RSq = 4.0D;
/* 177 */             EntityPosition centerPoint = new EntityPosition(center.x + 0.5D, center.y + 0.5D, center.z + 0.5D);
/* 178 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(centerPoint.x - 2.0D, centerPoint.y - 2.0D, centerPoint.z - 2.0D, centerPoint.x + 2.0D, centerPoint.y + 2.0D, centerPoint.z + 2.0D);
/*     */             
/*     */ 
/*     */ 
/* 182 */             int convertableStackSize = Math.min(originalStackSize, 1);
/* 183 */             remainingStackSize = originalStackSize - convertableStackSize;
/*     */             
/* 185 */             EntityCreature creature = Infusion.spawnCreature(this.field_70170_p, EntitySpirit.class, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
/* 186 */             if (creature != null) {
/* 187 */               EntitySpirit spirit = (EntitySpirit)creature;
/* 188 */               creature.func_110163_bv();
/* 189 */               spirit.setTarget("Village", 2);
/*     */             }
/*     */             
/* 192 */             if (remainingStackSize > 0) {
/* 193 */               EntityUtil.spawnEntityInWorld(this.field_70170_p, new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, Witchery.Items.GENERIC.itemAttunedStone.createStack(remainingStackSize)));
/*     */             }
/*     */             
/*     */ 
/* 197 */             ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, this, 1.0D, 1.0D, 16);
/* 198 */             isInnerTinyBlockCircle(this.field_70170_p, center.x, center.y, center.z, glyph, true);
/* 199 */             func_70106_y();
/*     */           }
/* 201 */         } else if (Witchery.Items.GENERIC.itemSubduedSpirit.isMatch(func_92059_d())) {
/* 202 */           Block glyph = Witchery.Blocks.GLYPH_RITUAL;
/* 203 */           Coord center = isTinyBlockCircle(this.field_70170_p, new Coord(this), glyph);
/* 204 */           if (center != null) {
/* 205 */             int originalStackSize = func_92059_d().field_77994_a;
/* 206 */             int remainingStackSize = 0;
/* 207 */             double R = 2.0D;
/* 208 */             double RSq = 4.0D;
/* 209 */             EntityPosition centerPoint = new EntityPosition(center.x + 0.5D, center.y + 0.5D, center.z + 0.5D);
/* 210 */             AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(centerPoint.x - 2.0D, centerPoint.y - 2.0D, centerPoint.z - 2.0D, centerPoint.x + 2.0D, centerPoint.y + 2.0D, centerPoint.z + 2.0D);
/*     */             
/*     */ 
/*     */ 
/* 214 */             int convertableStackSize = Math.min(originalStackSize, 1);
/* 215 */             remainingStackSize = originalStackSize - convertableStackSize;
/*     */             
/* 217 */             EntityCreature creature = Infusion.spawnCreature(this.field_70170_p, EntitySpirit.class, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
/* 218 */             if (creature != null) {
/* 219 */               EntitySpirit spirit = (EntitySpirit)creature;
/* 220 */               creature.func_110163_bv();
/* 221 */               spirit.setTarget("Village", 2);
/*     */             }
/*     */             
/* 224 */             if (remainingStackSize > 0) {
/* 225 */               EntityUtil.spawnEntityInWorld(this.field_70170_p, new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, Witchery.Items.GENERIC.itemSubduedSpirit.createStack(remainingStackSize)));
/*     */             }
/*     */             
/*     */ 
/* 229 */             ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, this, 1.0D, 1.0D, 16);
/* 230 */             isInnerTinyBlockCircle(this.field_70170_p, center.x, center.y, center.z, glyph, true);
/* 231 */             func_70106_y();
/*     */           }
/*     */         }
/*     */       }
/*     */   }
/*     */   
/*     */   private static class TeleportTask extends ServerTickEvents.ServerTickTask {
/*     */     ItemStack stone;
/*     */     Entity entity;
/*     */     
/* 241 */     public TeleportTask(World world, ItemStack stone, Entity entity) { super();
/* 242 */       this.stone = stone;
/* 243 */       this.entity = entity;
/*     */     }
/*     */     
/*     */     public boolean process()
/*     */     {
/* 248 */       if (!Witchery.Items.GENERIC.teleportToLocation(this.world, this.stone, this.entity, 0, true))
/*     */       {
/*     */ 
/* 251 */         ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, this.world, this.entity.field_70165_t, this.entity.field_70163_u, this.entity.field_70161_v, 1.0D, 1.0D, 16);
/*     */       }
/*     */       
/* 254 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   private static Coord isTinyBlockCircle(World world, Coord coord, Block runeBlock) {
/* 259 */     int x = coord.x;
/* 260 */     int y = coord.y;
/* 261 */     int z = coord.z;
/* 262 */     if (isInnerTinyBlockCircle(world, x, y, z, runeBlock, false)) {
/* 263 */       return coord;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 271 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean isInnerTinyBlockCircle(World world, int x, int y, int z, Block runeBlock, boolean explode)
/*     */   {
/* 276 */     int[][] circle = { { x, z - 1 }, { x + 1, z - 1 }, { x + 1, z }, { x + 1, z + 1 }, { x, z + 1 }, { x - 1, z + 1 }, { x - 1, z }, { x - 1, z - 1 } };
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
/* 287 */     for (int[] coord : circle) {
/* 288 */       if (world.func_147439_a(coord[0], y, coord[1]) != runeBlock) {
/* 289 */         return false;
/*     */       }
/*     */     }
/* 292 */     if (explode) {
/* 293 */       for (int[] coord : circle) {
/* 294 */         world.func_147468_f(coord[0], y, coord[1]);
/* 295 */         ParticleEffect.EXPLODE.send(SoundEffect.NONE, world, 0.5D + coord[0], y, 0.5D + coord[1], 0.5D, 0.5D, 16);
/*     */       }
/*     */     }
/* 298 */     return true;
/*     */   }
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
/*     */   private static Coord isSmallBlockCircle(World world, Coord coord, Block runeBlock)
/*     */   {
/* 327 */     int x = coord.x;
/* 328 */     int z = coord.z;
/* 329 */     int[][] circle = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
/*     */     
/*     */ 
/* 332 */     for (int[] co : circle) {
/* 333 */       if (com.emoniph.witchery.util.CircleUtil.isSmallCircle(world, coord.x + co[0], coord.y, coord.z + co[1], runeBlock)) {
/* 334 */         return new Coord(coord.x - co[0], coord.y, coord.z - co[1]);
/*     */       }
/*     */     }
/* 337 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityItemWaystone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */