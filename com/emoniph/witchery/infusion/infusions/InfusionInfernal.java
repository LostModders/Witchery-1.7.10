/*     */ package com.emoniph.witchery.infusion.infusions;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.potions.PotionEnslaved;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
/*     */ import com.emoniph.witchery.infusion.infusions.creature.CreaturePower.Registry;
/*     */ import com.emoniph.witchery.util.BlockSide;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.lang.reflect.Field;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.monster.EntityGhast;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ public class InfusionInfernal extends Infusion
/*     */ {
/*     */   private static final int MAX_CHARGES = 20;
/*     */   
/*     */   public InfusionInfernal(int infusionID)
/*     */   {
/*  37 */     super(infusionID);
/*     */   }
/*     */   
/*     */   public IIcon getPowerBarIcon(EntityPlayer player, int index)
/*     */   {
/*  42 */     return net.minecraft.init.Blocks.field_150424_aL.func_149691_a(0, 0);
/*     */   }
/*     */   
/*     */   public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
/*     */   {
/*  47 */     if ((!world.field_72995_K) && 
/*  48 */       ((otherEntity instanceof EntityLivingBase))) {
/*  49 */       EntityLivingBase entityLivingBase = (EntityLivingBase)otherEntity;
/*  50 */       if (player.func_70093_af()) {
/*  51 */         if (PotionEnslaved.canCreatureBeEnslaved(entityLivingBase)) {
/*  52 */           EntityLiving entityLiving = (EntityLiving)entityLivingBase;
/*  53 */           if (PotionEnslaved.isMobEnslavedBy(entityLiving, player))
/*     */           {
/*  55 */             if (consumeCharges(world, player, 1, true)) {
/*  56 */               trySacrificeCreature(world, player, entityLiving);
/*     */             }
/*     */             
/*     */           }
/*  60 */           else if (consumeCharges(world, player, 5, true)) {
/*  61 */             PotionEnslaved.setEnslaverForMob(entityLiving, player);
/*  62 */             com.emoniph.witchery.util.EntityUtil.dropAttackTarget((EntityLiving)otherEntity);
/*     */             
/*  64 */             ParticleEffect.SPELL.send(SoundEffect.MOB_ZOMBIE_INFECT, entityLiving, 1.0D, 2.0D, 16);
/*     */           }
/*     */         }
/*     */         else {
/*  68 */           SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */         }
/*     */       }
/*     */       else {
/*  72 */         int r = 50;
/*  73 */         if (consumeCharges(world, player, 1, true)) {
/*  74 */           int minionCount = 0;
/*  75 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 50.0D, player.field_70163_u - 15.0D, player.field_70161_v - 50.0D, player.field_70165_t + 50.0D, player.field_70163_u + 15.0D, player.field_70161_v + 50.0D);
/*     */           
/*  77 */           for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
/*  78 */             EntityLiving nearbyLivingEntity = (EntityLiving)obj;
/*  79 */             if (PotionEnslaved.isMobEnslavedBy(nearbyLivingEntity, player)) {
/*  80 */               minionCount++;
/*  81 */               nearbyLivingEntity.func_70624_b(entityLivingBase);
/*  82 */               if ((nearbyLivingEntity instanceof EntityGhast)) {
/*     */                 try {
/*  84 */                   EntityGhast ghastEntity = (EntityGhast)nearbyLivingEntity;
/*  85 */                   Field[] fields = EntityGhast.class.getDeclaredFields();
/*     */                   
/*  87 */                   Field fieldTargetedEntity = fields[4];
/*  88 */                   fieldTargetedEntity.setAccessible(true);
/*  89 */                   fieldTargetedEntity.set(ghastEntity, entityLivingBase);
/*     */                   
/*  91 */                   Field fieldAggroCooldown = fields[5];
/*  92 */                   fieldAggroCooldown.setAccessible(true);
/*  93 */                   fieldAggroCooldown.set(ghastEntity, Integer.valueOf(20000));
/*     */                 } catch (IllegalAccessException e) {
/*  95 */                   Log.instance().warning(e, "Exception occurred setting ghast target.");
/*     */                 } catch (Exception e) {
/*  97 */                   Log.instance().debug(String.format("Exception occurred setting ghast target. %s", new Object[] { e.toString() }));
/*     */                 }
/*     */               }
/* 100 */               if ((nearbyLivingEntity instanceof EntityCreature)) {
/* 101 */                 EntityCreature nearbyCreatureEntity = (EntityCreature)obj;
/* 102 */                 nearbyCreatureEntity.func_70784_b(entityLivingBase);
/* 103 */                 nearbyCreatureEntity.func_70604_c(entityLivingBase);
/* 104 */                 if (((nearbyCreatureEntity instanceof net.minecraft.entity.monster.EntityZombie)) || ((nearbyCreatureEntity instanceof net.minecraft.entity.monster.EntityCreeper))) {
/* 105 */                   nearbyCreatureEntity.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(nearbyCreatureEntity, entityLivingBase.getClass(), 1.0D, false));
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 111 */           if (minionCount > 0) {
/* 112 */             ParticleEffect.CRIT.send(SoundEffect.RANDOM_BREATH, entityLivingBase, 0.5D, 2.0D, 16);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void trySacrificeCreature(World world, EntityPlayer player, EntityLiving creature)
/*     */   {
/* 123 */     CreaturePower power = CreaturePower.Registry.instance().get(creature);
/* 124 */     if (power != null) {
/* 125 */       int currentCreaturePowerID = CreaturePower.getCreaturePowerID(player);
/* 126 */       if (currentCreaturePowerID == power.getCreaturePowerID()) {
/* 127 */         int currentCharges = CreaturePower.getCreaturePowerCharges(player);
/* 128 */         CreaturePower.setCreaturePowerCharges(player, MathHelper.func_76128_c(Math.min(currentCharges + power.getChargesPerSacrifice(), 20)));
/*     */       } else {
/* 130 */         CreaturePower.setCreaturePowerID(player, power.getCreaturePowerID(), power.getChargesPerSacrifice());
/*     */       }
/* 132 */       syncPlayer(world, player);
/* 133 */       creature.func_70097_a(net.minecraft.util.DamageSource.func_76354_b(player, null), creature.func_110143_aJ() + 1.0F);
/*     */     } else {
/* 135 */       playFailSound(world, player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onHurt(World worldObj, EntityPlayer player, LivingHurtEvent event)
/*     */   {
/* 141 */     int creaturePowerID = CreaturePower.getCreaturePowerID(player);
/* 142 */     if (creaturePowerID > 0) {
/* 143 */       CreaturePower.Registry.instance().get(creaturePowerID).onDamage(player.field_70170_p, player, event);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onFalling(World world, EntityPlayer player, LivingFallEvent event)
/*     */   {
/* 149 */     int creaturePowerID = CreaturePower.getCreaturePowerID(player);
/* 150 */     if (creaturePowerID > 0) {
/* 151 */       CreaturePower.Registry.instance().get(creaturePowerID).onFalling(world, player, event);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 157 */     int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/*     */   }
/*     */   
/*     */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 162 */     if (!world.field_72995_K) {
/* 163 */       int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/* 164 */       double MAX_TARGET_RANGE = 15.0D;
/* 165 */       MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 15.0D);
/*     */       
/* 167 */       if (player.func_70093_af()) {
/* 168 */         if (mop != null) {
/* 169 */           switch (mop.field_72313_a) {
/*     */           case ENTITY: 
/* 171 */             playFailSound(world, player);
/* 172 */             break;
/*     */           case BLOCK: 
/* 174 */             if (BlockSide.TOP.isEqual(mop.field_72310_e)) {
/* 175 */               int minionCount = 0;
/* 176 */               int r = 50;
/* 177 */               AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 50.0D, player.field_70163_u - 15.0D, player.field_70161_v - 50.0D, player.field_70165_t + 50.0D, player.field_70163_u + 15.0D, player.field_70161_v + 50.0D);
/*     */               
/* 179 */               for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
/* 180 */                 EntityLiving creature = (EntityLiving)obj;
/* 181 */                 EntityCreature creature2 = (creature instanceof EntityCreature) ? (EntityCreature)creature : null;
/* 182 */                 if (PotionEnslaved.isMobEnslavedBy(creature, player)) {
/* 183 */                   minionCount++;
/* 184 */                   creature.func_70624_b(null);
/* 185 */                   creature.func_70604_c(null);
/* 186 */                   if (creature2 != null) {
/* 187 */                     creature2.func_70784_b(null);
/*     */                   }
/* 189 */                   if ((((creature instanceof net.minecraft.entity.monster.EntitySpider)) || (!creature.func_70661_as().func_75492_a(mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 1.0D))) && 
/* 190 */                     (creature2 != null)) {
/* 191 */                     creature2.func_70778_a(world.func_72844_a(creature, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 10.0F, true, false, false, true));
/*     */                   }
/*     */                 }
/*     */               }
/*     */               
/*     */ 
/* 197 */               if (minionCount > 0)
/* 198 */                 ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 0.5D, 2.0D, 16);
/*     */             }
/* 200 */             break;
/*     */           
/*     */ 
/*     */           }
/*     */           
/*     */         } else {
/* 206 */           playFailSound(world, player);
/*     */         }
/*     */       } else {
/* 209 */         int beastPowerID = CreaturePower.getCreaturePowerID(player);
/* 210 */         if (beastPowerID > 0) {
/* 211 */           CreaturePower power = CreaturePower.Registry.instance().get(beastPowerID);
/* 212 */           int chargesRequired = power.activateCost(world, player, elapsedTicks, mop);
/* 213 */           int currentCharges = CreaturePower.getCreaturePowerCharges(player);
/* 214 */           if ((currentCharges - chargesRequired >= 0) && (consumeCharges(world, player, 1, true))) {
/* 215 */             power.onActivate(world, player, elapsedTicks, mop);
/* 216 */             if (!player.field_71075_bZ.field_75098_d) {
/* 217 */               CreaturePower.setCreaturePowerCharges(player, currentCharges - chargesRequired);
/* 218 */               syncPlayer(world, player);
/*     */             }
/*     */           } else {
/* 221 */             playFailSound(world, player);
/*     */           }
/*     */         } else {
/* 224 */           playFailSound(world, player);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/InfusionInfernal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */