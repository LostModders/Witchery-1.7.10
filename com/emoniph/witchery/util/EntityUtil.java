/*     */ package com.emoniph.witchery.util;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIAttackOnCollide2;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.network.PacketPushTarget;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EntityTracker;
/*     */ import net.minecraft.entity.EntityTrackerEntry;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
/*     */ import net.minecraft.entity.boss.EntityDragon;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityGhast;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ 
/*     */ public class EntityUtil
/*     */ {
/*     */   public static EntityPlayer playerOrFake(World world, String thrower)
/*     */   {
/*  53 */     return playerOrFake(world, world != null ? world.func_72924_a(thrower) : null);
/*     */   }
/*     */   
/*     */   public static EntityPlayer playerOrFake(World world, EntityLivingBase entity) {
/*  57 */     if ((entity != null) && ((entity instanceof EntityPlayer)))
/*  58 */       return (EntityPlayer)entity;
/*  59 */     if ((world == null) || (!(world instanceof WorldServer))) {
/*  60 */       return null;
/*     */     }
/*  62 */     return FakePlayerFactory.getMinecraft((WorldServer)world);
/*     */   }
/*     */   
/*     */ 
/*     */   public static <T extends Entity> T findNearestEntityWithinAABB(World world, Class<T> clazz, AxisAlignedBB bounds, Entity entity)
/*     */   {
/*  68 */     Entity foundEntity = world.func_72857_a(clazz, bounds, entity);
/*  69 */     if (foundEntity != null) {
/*  70 */       return foundEntity;
/*     */     }
/*  72 */     return null;
/*     */   }
/*     */   
/*     */   public static void spawnEntityInWorld(World world, Entity entity)
/*     */   {
/*  77 */     if ((entity != null) && (world != null) && (!world.field_72995_K)) {
/*  78 */       world.func_72838_d(entity);
/*     */     }
/*     */   }
/*     */   
/*  82 */   private static Field fieldTrackedEntities = null;
/*     */   public static Field fieldGhastTargetedEntity;
/*     */   
/*  85 */   public static void correctProjectileTrackerSync(World world, Entity projectile) { if ((!world.field_72995_K) && ((world instanceof WorldServer))) {
/*     */       try {
/*  87 */         if (fieldTrackedEntities == null) {
/*  88 */           fieldTrackedEntities = ReflectionHelper.findField(EntityTracker.class, new String[] { "trackedEntities", "field_72793_b", "b" });
/*     */         }
/*     */         
/*     */ 
/*  92 */         if (fieldTrackedEntities != null) {
/*  93 */           EntityTracker tracker = ((WorldServer)world).func_73039_n();
/*  94 */           Set trackedEntities = (Set)fieldTrackedEntities.get(tracker);
/*  95 */           Iterator iterator = trackedEntities.iterator();
/*  96 */           while (iterator.hasNext()) {
/*  97 */             EntityTrackerEntry next = (EntityTrackerEntry)iterator.next();
/*  98 */             if (next.field_73132_a == projectile) {
/*  99 */               next.field_73136_m = 1;
/* 100 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       } catch (IllegalAccessException e) {
/* 105 */         Log.instance().warning(e, "Exception occurred setting entity tracking for bolt.");
/*     */       } catch (Exception e) {
/* 107 */         Log.instance().debug(String.format("Exception occurred setting entity tracking for bolt. %s", new Object[] { e.toString() }));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void push(World world, Entity entity, EntityPosition position, double power)
/*     */   {
/* 114 */     Entity entity2 = entity;
/* 115 */     double d = position.x - entity2.field_70165_t;
/* 116 */     double d1 = position.y - entity2.field_70163_u;
/* 117 */     double d2 = position.z - entity2.field_70161_v;
/* 118 */     double d4 = d * d + d1 * d1 + d2 * d2;
/* 119 */     d4 *= d4;
/* 120 */     if (d4 <= Math.pow(6.0D, 4.0D)) {
/* 121 */       double d5 = -(d * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 122 */       double d6 = -(d1 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 123 */       double d7 = -(d2 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
/* 124 */       if (d5 > 0.0D) {
/* 125 */         d5 = 0.22D;
/* 126 */       } else if (d5 < 0.0D) {
/* 127 */         d5 = -0.22D;
/*     */       }
/* 129 */       if (d6 > 0.2D) {
/* 130 */         d6 = 0.12D;
/* 131 */       } else if (d6 < -0.1D) {
/* 132 */         d6 = 0.12D;
/*     */       }
/* 134 */       if (d7 > 0.0D) {
/* 135 */         d7 = 0.22D;
/* 136 */       } else if (d7 < 0.0D) {
/* 137 */         d7 = -0.22D;
/*     */       }
/* 139 */       entity2.field_70159_w += d5 * power;
/* 140 */       entity2.field_70181_x += d6 * (power / 3.0D);
/* 141 */       entity2.field_70179_y += d7 * power;
/*     */     }
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
/*     */   public static Field fieldGhastAggroCooldown;
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
/*     */   public static void pullTowards(World world, Entity entity, EntityPosition target, double dy, double yy)
/*     */   {
/* 172 */     if (((entity instanceof EntityDragon)) || ((entity instanceof EntityHornedHuntsman)) || (target.occupiedBy(entity))) {
/* 173 */       return;
/*     */     }
/*     */     
/* 176 */     double d = target.x - entity.field_70165_t;
/* 177 */     double d1 = target.y - entity.field_70163_u;
/* 178 */     double d2 = target.z - entity.field_70161_v;
/*     */     
/*     */ 
/* 181 */     float distance = MathHelper.func_76133_a(d * d + d1 * d1 + d2 * d2);
/* 182 */     if (distance < 0.01D) {
/* 183 */       return;
/*     */     }
/* 185 */     float f2 = 0.1F + (float)dy;
/*     */     
/* 187 */     double mx = d / distance * f2 * distance;
/*     */     
/* 189 */     double my = yy == 0.0D ? 0.4D : d1 / distance * distance * 0.2D + 0.2D + yy;
/* 190 */     double mz = d2 / distance * f2 * distance;
/*     */     
/* 192 */     if ((entity instanceof EntityLivingBase)) {
/* 193 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 20, 1));
/*     */     }
/*     */     
/* 196 */     if ((entity instanceof EntityPlayer)) {
/* 197 */       Witchery.packetPipeline.sendTo(new PacketPushTarget(mx, my, mz), (EntityPlayer)entity);
/*     */     } else {
/* 199 */       entity.field_70159_w = mx;
/* 200 */       entity.field_70181_x = my;
/* 201 */       entity.field_70179_y = mz;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void pushback(World world, Entity entity, EntityPosition hit, double xyScale, double ySpeed) {
/* 206 */     double d = hit.x - entity.field_70165_t;
/* 207 */     double d1 = hit.y - entity.field_70163_u;
/* 208 */     double d2 = hit.z - entity.field_70161_v;
/*     */     
/* 210 */     Vec3 vec = Vec3.func_72443_a(d, d1, d2).func_72432_b();
/* 211 */     double dx = -vec.field_72450_a * xyScale;
/* 212 */     double dy = Math.max(-vec.field_72448_b, ySpeed);
/* 213 */     double dz = -vec.field_72449_c * xyScale;
/*     */     
/* 215 */     if ((entity instanceof EntityPlayer)) {
/* 216 */       Witchery.packetPipeline.sendTo(new PacketPushTarget(dx, dy, dz), (EntityPlayer)entity);
/*     */     } else {
/* 218 */       entity.field_70159_w = dx;
/* 219 */       entity.field_70181_x = dy;
/* 220 */       entity.field_70179_y = dz;
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T extends Entity> List<T> getEntitiesInRadius(Class<T> clazz, TileEntity tile, double radius) {
/* 225 */     return getEntitiesInRadius(clazz, tile.func_145831_w(), 0.5D + tile.field_145851_c, 0.5D + tile.field_145848_d, 0.5D + tile.field_145849_e, radius);
/*     */   }
/*     */   
/*     */ 
/*     */   public static <T extends Entity> List<T> getEntitiesInRadius(Class<T> clazz, World world, double x, double y, double z, double radius)
/*     */   {
/* 231 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
/*     */     
/* 233 */     List<T> entities = world.func_72872_a(clazz, bounds);
/* 234 */     ArrayList<T> nearbyEntities = new ArrayList();
/* 235 */     double radiusSq = radius * radius;
/* 236 */     for (T entity : entities) {
/* 237 */       if (entity.func_70092_e(x, entity.field_70163_u, z) <= radiusSq) {
/* 238 */         nearbyEntities.add(entity);
/*     */       }
/*     */     }
/* 241 */     return nearbyEntities;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void setTarget(EntityLiving attacker, EntityLivingBase victim)
/*     */   {
/* 248 */     attacker.func_70624_b(victim);
/* 249 */     if ((attacker instanceof EntityGhast)) {
/*     */       try {
/* 251 */         EntityGhast ghastEntity = (EntityGhast)attacker;
/*     */         
/* 253 */         if (fieldGhastTargetedEntity == null) {
/* 254 */           fieldGhastTargetedEntity = ReflectionHelper.findField(EntityGhast.class, new String[] { "targetedEntity", "field_70792_g", "g" });
/*     */         }
/*     */         
/* 257 */         fieldGhastTargetedEntity.set(ghastEntity, victim);
/*     */         
/* 259 */         if (fieldGhastAggroCooldown == null) {
/* 260 */           fieldGhastAggroCooldown = ReflectionHelper.findField(EntityGhast.class, new String[] { "aggroCooldown", "field_70798_h", "h" });
/*     */         }
/*     */         
/* 263 */         fieldGhastAggroCooldown.set(ghastEntity, Integer.valueOf(20000));
/*     */       } catch (IllegalAccessException e) {
/* 265 */         Log.instance().warning(e, "Exception occurred setting ghast target.");
/*     */       } catch (Exception e) {
/* 267 */         Log.instance().debug(String.format("Exception occurred setting ghast target. %s", new Object[] { e.toString() }));
/*     */       }
/*     */     }
/* 270 */     if ((attacker instanceof EntityCreature)) {
/* 271 */       EntityCreature attackerCreature = (EntityCreature)attacker;
/* 272 */       attackerCreature.func_70784_b(victim);
/* 273 */       attackerCreature.func_70604_c(victim);
/* 274 */       if (((attackerCreature instanceof EntityZombie)) || ((attackerCreature instanceof EntityCreeper))) {
/* 275 */         boolean found = false;
/* 276 */         Class victimClass = victim.getClass();
/* 277 */         for (Object obj : attackerCreature.field_70715_bh.field_75782_a) {
/* 278 */           EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
/* 279 */           if ((task.field_75733_a instanceof EntityAIAttackOnCollide2)) {
/* 280 */             EntityAIAttackOnCollide2 ai = (EntityAIAttackOnCollide2)task.field_75733_a;
/* 281 */             if ((ai == null) || (!ai.appliesToClass(victimClass))) break;
/* 282 */             found = true; break;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 288 */         if (!found) {
/* 289 */           attacker.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide2(attackerCreature, victimClass, 1.0D, false));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void dropAttackTarget(EntityLiving entity)
/*     */   {
/* 297 */     entity.func_70624_b(null);
/* 298 */     if ((entity instanceof EntityCreature)) {
/* 299 */       EntityCreature creatureEntity = (EntityCreature)entity;
/* 300 */       creatureEntity.func_70784_b(null);
/* 301 */       creatureEntity.func_70604_c(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void syncInventory(EntityPlayer player) {
/* 306 */     if ((player instanceof EntityPlayerMP)) {
/* 307 */       ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void persistanceRequired(EntityLiving entity) {
/* 312 */     entity.func_110163_bv();
/*     */   }
/*     */   
/*     */   public static void setNoDrops(EntityLiving entity) {
/* 316 */     if (entity != null) {
/* 317 */       NBTTagCompound nbtEntity = entity.getEntityData();
/* 318 */       nbtEntity.func_74757_a("WITCNoDrops", true);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isNoDrops(EntityLivingBase entity) {
/* 323 */     if ((entity == null) || ((entity instanceof EntityPlayer))) {
/* 324 */       return false;
/*     */     }
/* 326 */     NBTTagCompound nbtEntity = entity.getEntityData();
/* 327 */     return nbtEntity.func_74767_n("WITCNoDrops");
/*     */   }
/*     */   
/*     */ 
/*     */   public static float getHealthAfterDamage(LivingHurtEvent event, float currentHealth, EntityLivingBase entity)
/*     */   {
/* 333 */     if (event.source.func_76363_c()) {
/* 334 */       return currentHealth - event.ammount;
/*     */     }
/*     */     
/* 337 */     float damage = event.ammount;
/* 338 */     int i = 25 - entity.func_70658_aO();
/* 339 */     float f1 = damage * i;
/* 340 */     damage = f1 / 25.0F;
/*     */     
/*     */ 
/*     */ 
/* 344 */     if ((entity.func_70644_a(Potion.field_76429_m)) && (event.source != DamageSource.field_76380_i)) {
/* 345 */       i = (entity.func_70660_b(Potion.field_76429_m).func_76458_c() + 1) * 5;
/* 346 */       float j = 25 - i;
/* 347 */       f1 = damage * j;
/* 348 */       damage = f1 / 25.0F;
/*     */     }
/*     */     
/* 351 */     if (damage <= 0.0F) {
/* 352 */       damage = 0.0F;
/*     */     } else {
/* 354 */       i = EnchantmentHelper.func_77508_a(entity.func_70035_c(), event.source);
/*     */       
/* 356 */       if (i > 20) {
/* 357 */         i = 20;
/*     */       }
/*     */       
/* 360 */       if ((i > 0) && (i <= 20)) {
/* 361 */         float j = 25 - i;
/* 362 */         f1 = damage * j;
/* 363 */         damage = f1 / 25.0F;
/*     */       }
/*     */     }
/*     */     
/* 367 */     return currentHealth - damage;
/*     */   }
/*     */   
/*     */   public static class DamageSourceSunlight extends EntityDamageSource
/*     */   {
/* 372 */     public static final DamageSourceSunlight SUN = new DamageSourceSunlight(null);
/*     */     
/* 374 */     public DamageSourceSunlight(Entity attacker) { super(attacker);
/* 375 */       func_76348_h();
/* 376 */       func_82726_p();
/*     */     }
/*     */     
/* 379 */     public IChatComponent func_151519_b(EntityLivingBase p_151519_1_) { EntityLivingBase entitylivingbase1 = p_151519_1_.func_94060_bK();
/* 380 */       String s = "witchery:death.attack." + this.field_76373_n;
/* 381 */       String s1 = s + ".player";
/* 382 */       return (entitylivingbase1 != null) && (StatCollector.func_94522_b(s1)) ? new ChatComponentTranslation(s1, new Object[] { p_151519_1_.func_145748_c_(), entitylivingbase1.func_145748_c_() }) : new ChatComponentTranslation(s, new Object[] { p_151519_1_.func_145748_c_() });
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DamageSourceVampireFire
/*     */     extends DamageSource
/*     */   {
/* 389 */     public static final DamageSourceVampireFire SOURCE = new DamageSourceVampireFire();
/*     */     
/* 391 */     public DamageSourceVampireFire() { super();
/* 392 */       func_76348_h();
/* 393 */       func_82726_p();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void instantDeath(EntityLivingBase entity, EntityLivingBase attacker) {
/* 398 */     if ((entity != null) && (entity.field_70170_p != null) && (!entity.field_70170_p.field_72995_K)) {
/* 399 */       if ((entity instanceof EntityLiving)) {
/* 400 */         entity.func_70606_j(0.0F);
/* 401 */         if (attacker == null) {
/* 402 */           entity.func_70645_a(DamageSource.field_76376_m);
/*     */         } else {
/* 404 */           entity.func_70645_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker));
/*     */         }
/* 406 */         entity.func_70106_y();
/* 407 */       } else if ((entity instanceof EntityPlayer)) {
/* 408 */         EntityPlayer player = (EntityPlayer)entity;
/* 409 */         if (!player.field_71075_bZ.field_75098_d) {
/* 410 */           if (player.func_70608_bn()) {
/* 411 */             player.func_70999_a(true, true, false);
/*     */           }
/* 413 */           entity.func_70606_j(0.0F);
/* 414 */           if (ExtendedPlayer.get(player).isVampire()) {
/* 415 */             entity.func_70645_a(attacker == null ? DamageSourceSunlight.SUN : new DamageSourceSunlight(attacker));
/*     */           } else {
/* 417 */             entity.func_70645_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean touchOfDeath(Entity victim, EntityLivingBase attacker, float damage) {
/* 425 */     if ((victim != null) && (victim.func_85032_ar())) {
/* 426 */       return false;
/*     */     }
/*     */     
/* 429 */     if ((victim != null) && (victim.field_70170_p != null) && (!victim.field_70170_p.field_72995_K)) {
/* 430 */       if ((victim instanceof EntityLiving)) {
/* 431 */         DamageSource source = new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker);
/* 432 */         EntityLiving creature = (EntityLiving)victim;
/* 433 */         float cap = 10000.0F;
/* 434 */         if ((victim instanceof IHandleDT)) {
/* 435 */           cap = ((IHandleDT)victim).getCapDT(source, damage);
/* 436 */           if (cap <= 0.0F)
/* 437 */             return false;
/* 438 */           if ((attacker instanceof EntityLiving)) {
/* 439 */             cap = Math.min(6.0F, cap);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 444 */         creature.func_70097_a(source, 0.0F);
/* 445 */         creature.func_70606_j(Math.max(creature.func_110143_aJ() - Math.min(damage, cap), 0.0F));
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
/* 456 */         creature.func_70097_a(source, 0.0F);
/*     */       }
/* 458 */       else if ((victim instanceof EntityPlayer)) {
/* 459 */         EntityPlayer player = (EntityPlayer)victim;
/* 460 */         if (!player.field_71075_bZ.field_75098_d) {
/* 461 */           player.func_70606_j(Math.max(player.func_110143_aJ() - damage, 0.0F));
/*     */           
/*     */ 
/*     */ 
/* 465 */           if (player.func_110143_aJ() <= 0.0F) {
/* 466 */             if (attacker == null) {
/* 467 */               player.func_70645_a(DamageSource.field_76376_m);
/*     */             } else {
/* 469 */               player.func_70645_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker));
/*     */             }
/*     */           } else {
/* 472 */             player.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker), 0.0F);
/*     */           }
/*     */         }
/*     */         else {
/* 476 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 480 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean moveToBlockPositionAndUpdate(EntityLiving entity, int x, int y, int z, int maxDY) {
/* 484 */     World world = entity.field_70170_p;
/* 485 */     boolean done = false;
/* 486 */     int mod = 0;
/* 487 */     int sign = -1;
/* 488 */     while ((!done) && (mod <= 2 * maxDY) && (y < 250) && (y > 2)) {
/* 489 */       if ((BlockUtil.isNormalCube(world.func_147439_a(x, y, z))) && (world.func_147437_c(x, y + 1, z)) && (world.func_147437_c(x, y + 2, z)))
/*     */       {
/*     */ 
/* 492 */         done = true;
/*     */       } else {
/* 494 */         mod++;
/* 495 */         sign *= -1;
/* 496 */         y += mod * sign;
/*     */       }
/*     */     }
/*     */     
/* 500 */     if (done) {
/* 501 */       entity.func_70634_a(0.5D + x, 1.05D + y, 0.5D + z);
/*     */     }
/*     */     
/* 504 */     return done;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/EntityUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */