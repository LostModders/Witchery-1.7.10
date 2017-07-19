/*     */ package com.emoniph.witchery.util;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.WitcheryRecipes;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.entity.EntityBabaYaga;
/*     */ import com.emoniph.witchery.entity.EntityBolt;
/*     */ import com.emoniph.witchery.entity.EntityDemon;
/*     */ import com.emoniph.witchery.entity.EntityHornedHuntsman;
/*     */ import com.emoniph.witchery.entity.EntityLeonard;
/*     */ import com.emoniph.witchery.entity.EntityLilith;
/*     */ import com.emoniph.witchery.entity.EntityLordOfTorment;
/*     */ import com.emoniph.witchery.entity.EntityReflection;
/*     */ import com.emoniph.witchery.entity.EntityVampire;
/*     */ import com.emoniph.witchery.entity.EntityVillagerWere;
/*     */ import com.emoniph.witchery.entity.EntityWolfman;
/*     */ import com.emoniph.witchery.infusion.InfusedBrewEffect;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.integration.ModHookManager;
/*     */ import com.emoniph.witchery.item.ItemPoppet;
/*     */ import com.emoniph.witchery.item.ItemPoppet.PoppetType;
/*     */ import com.emoniph.witchery.item.ItemVampireClothes;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.boss.EntityWither;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.monster.EntityBlaze;
/*     */ import net.minecraft.entity.monster.EntityGhast;
/*     */ import net.minecraft.entity.monster.EntityGolem;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.FoodStats;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class CreatureUtil
/*     */ {
/*     */   private static Class classBloodMagicDemon;
/*     */   
/*     */   public static boolean isDemonic(Entity entity)
/*     */   {
/*  53 */     if (entity != null) {
/*  54 */       if (((entity instanceof EntityDemon)) || ((entity instanceof EntityGhast)) || ((entity instanceof EntityBlaze)) || ((entity instanceof net.minecraft.entity.monster.EntityMagmaCube)) || ((entity instanceof EntityLeonard)) || ((entity instanceof EntityLordOfTorment)) || ((entity instanceof com.emoniph.witchery.entity.EntityImp)) || ((entity instanceof EntityLilith)) || ((entity instanceof EntityWither)))
/*     */       {
/*     */ 
/*     */ 
/*  58 */         EntityLiving living = (EntityLiving)entity;
/*  59 */         return true; }
/*  60 */       if ((entity instanceof EntityPlayer))
/*  61 */         return false;
/*  62 */       if (isModDemon(entity)) {
/*  63 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  67 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static boolean isModDemon(Entity entity)
/*     */   {
/*  74 */     if (classBloodMagicDemon == null) {
/*     */       try {
/*  76 */         classBloodMagicDemon = Class.forName("WayofTime.alchemicalWizardry.common.entity.mob.EntityDemon");
/*     */       }
/*     */       catch (ClassNotFoundException ex) {}
/*     */     }
/*     */     
/*     */ 
/*  82 */     if (classBloodMagicDemon != null) {
/*  83 */       return classBloodMagicDemon.isAssignableFrom(entity.getClass());
/*     */     }
/*     */     
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isUndead(Entity entity) {
/*  90 */     if (entity != null) {
/*  91 */       if ((entity instanceof EntityLiving)) {
/*  92 */         EntityLiving living = (EntityLiving)entity;
/*  93 */         return living.func_70662_br(); }
/*  94 */       if ((entity instanceof EntityPlayer)) {
/*  95 */         EntityPlayer player = (EntityPlayer)entity;
/*  96 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  97 */         return (playerEx.isVampire()) || (InfusedBrewEffect.getActiveBrew(player) == InfusedBrewEffect.Grave);
/*     */       }
/*     */     }
/*     */     
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isInsect(EntityLivingBase entity) {
/* 105 */     return entity.func_70668_bt() == EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */   
/*     */   public static boolean isSpirit(EntityLivingBase entity) {
/* 109 */     return entity != null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static EntityLiving spawnWithEgg(EntityLiving entity, boolean requirePersistance)
/*     */   {
/* 115 */     if (entity != null) {
/* 116 */       entity.func_110161_a((IEntityLivingData)null);
/* 117 */       if (requirePersistance) {
/* 118 */         entity.func_110163_bv();
/*     */       }
/*     */     }
/* 121 */     return entity;
/*     */   }
/*     */   
/*     */   public static boolean isWitch(Entity entity) {
/* 125 */     if (entity != null) {
/* 126 */       if (((entity instanceof EntityWitch)) || ((entity instanceof EntityBabaYaga)))
/* 127 */         return true;
/* 128 */       if ((entity instanceof EntityPlayer)) {
/* 129 */         EntityPlayer player = (EntityPlayer)entity;
/* 130 */         if ((InvUtil.hasItem(player.field_71071_by, Witchery.Items.POPPET, Witchery.Items.POPPET.voodooPoppet.damageValue)) || (InvUtil.hasItem(player.field_71071_by, Witchery.Items.POPPET, Witchery.Items.POPPET.vampiricPoppet.damageValue)) || (Infusion.getInfusionID(player) == Witchery.Recipes.infusionBeast.infusionID))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 135 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isWoodenDamage(DamageSource source) {
/* 144 */     if ((source.func_76364_f() != null) && ((source.func_76364_f() instanceof EntityLivingBase))) {
/* 145 */       EntityLivingBase living = (EntityLivingBase)source.func_76364_f();
/* 146 */       if (((living instanceof EntityHornedHuntsman)) && (!source.func_76352_a()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 151 */         return true;
/*     */       }
/* 153 */       ItemStack stack = living.func_71124_b(0);
/* 154 */       if ((stack != null) && ((stack.func_77973_b() instanceof ItemSword))) {
/* 155 */         ItemSword sword = (ItemSword)stack.func_77973_b();
/* 156 */         if (sword.func_150932_j().equalsIgnoreCase(Item.ToolMaterial.WOOD.toString())) {
/* 157 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 163 */     if ((source instanceof BoltDamageSource)) {
/* 164 */       BoltDamageSource boltDamage = (BoltDamageSource)source;
/* 165 */       return boltDamage.isWooden;
/*     */     }
/*     */     
/* 168 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isSilverDamage(DamageSource source) {
/* 172 */     if ((source instanceof EntityDamageSourceIndirectSilver))
/* 173 */       return true;
/* 174 */     if ((source.func_76364_f() != null) && ((source.func_76364_f() instanceof EntityBolt))) {
/* 175 */       EntityBolt bolt = (EntityBolt)source.func_76364_f();
/* 176 */       return bolt.isSilverDamage(); }
/* 177 */     if ((!source.func_76352_a()) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase)))
/*     */     {
/* 179 */       EntityLivingBase entity = (EntityLivingBase)source.func_76346_g();
/* 180 */       ItemStack stack = entity.func_70694_bm();
/* 181 */       if ((stack != null) && ((stack.func_77973_b() instanceof ItemSword))) {
/* 182 */         ItemSword sword = (ItemSword)stack.func_77973_b();
/* 183 */         String materialName = sword.func_150932_j();
/* 184 */         if (materialName != null) {
/* 185 */           if (materialName.equals("SILVER")) {
/* 186 */             return true;
/*     */           }
/*     */           
/* 189 */           int colonPos = materialName.lastIndexOf(":");
/* 190 */           if ((colonPos >= 0) && (colonPos < materialName.length())) {
/* 191 */             return materialName.substring(colonPos).equals(":SILVER");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 196 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isWerewolf(Entity entity) {
/* 200 */     return isWerewolf(entity, false);
/*     */   }
/*     */   
/*     */   public static boolean isWerewolf(Entity entity, boolean includeUnshifted) {
/* 204 */     if (entity == null)
/* 205 */       return false;
/* 206 */     if ((entity instanceof EntityWolfman))
/* 207 */       return true;
/* 208 */     if ((entity instanceof EntityReflection))
/* 209 */       return ((EntityReflection)entity).getModel() == 1;
/* 210 */     if ((entity instanceof EntityVillagerWere))
/* 211 */       return includeUnshifted;
/* 212 */     if ((entity instanceof EntityPlayer)) {
/* 213 */       ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)entity);
/* 214 */       if ((includeUnshifted) && (playerEx.getWerewolfLevel() > 0)) {
/* 215 */         return true;
/*     */       }
/*     */       
/* 218 */       return (playerEx.getCreatureType() == TransformCreature.WOLF) || (playerEx.getCreatureType() == TransformCreature.WOLFMAN);
/*     */     }
/* 220 */     if ((entity instanceof EntityLiving)) {
/* 221 */       String name = entity.getClass().getSimpleName();
/* 222 */       return (name != null) && (name.toUpperCase().contains("WEREWOLF"));
/*     */     }
/* 224 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isVampire(Entity entity)
/*     */   {
/* 229 */     if (entity == null)
/* 230 */       return false;
/* 231 */     if ((entity instanceof EntityVampire))
/* 232 */       return true;
/* 233 */     if ((entity instanceof EntityReflection))
/* 234 */       return ((EntityReflection)entity).isVampire();
/* 235 */     if ((entity instanceof EntityPlayer)) {
/* 236 */       ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)entity);
/* 237 */       if (playerEx.isVampire()) {
/* 238 */         return true;
/*     */       }
/* 240 */       return false; }
/* 241 */     if ((entity instanceof EntityLiving)) {
/* 242 */       String name = entity.getClass().getSimpleName();
/* 243 */       return (name != null) && (name.toUpperCase().contains("VAMPIRE"));
/*     */     }
/* 245 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isFullMoon(World world)
/*     */   {
/* 250 */     return (world.func_130001_d() == 1.0D) && (!world.func_72935_r());
/*     */   }
/*     */   
/*     */   public static boolean isImmuneToDisease(EntityLivingBase livingEntity) {
/* 254 */     return (isUndead(livingEntity)) || (isDemonic(livingEntity)) || (isWerewolf(livingEntity, true)) || ((livingEntity instanceof IBossDisplayData)) || ((livingEntity instanceof EntityGolem));
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isImmuneToPoison(EntityLivingBase livingEntity)
/*     */   {
/* 260 */     return isWerewolf(livingEntity, false);
/*     */   }
/*     */   
/*     */   public static boolean checkForVampireDeath(EntityLivingBase creature, DamageSource source) {
/* 264 */     boolean dead = false;
/* 265 */     if ((source.func_76347_k()) || ((source instanceof EntityUtil.DamageSourceVampireFire))) {
/* 266 */       if (ItemVampireClothes.isExtendedFlameProtectionActive(creature)) {
/* 267 */         dead = creature.field_70170_p.field_73012_v.nextInt(4) == 0;
/* 268 */       } else if (ItemVampireClothes.isFlameProtectionActive(creature)) {
/* 269 */         dead = creature.field_70170_p.field_73012_v.nextInt(4) != 0;
/*     */       } else {
/* 271 */         dead = true;
/*     */       }
/* 273 */     } else if ((source instanceof EntityUtil.DamageSourceSunlight)) {
/* 274 */       dead = true;
/* 275 */     } else if (((creature instanceof EntityPlayer)) && (Witchery.modHooks.canVampireBeKilled((EntityPlayer)creature)))
/*     */     {
/* 277 */       dead = true;
/* 278 */     } else if ((source == DamageSource.field_76368_d) || (source == DamageSource.field_76380_i)) {
/* 279 */       dead = true;
/* 280 */     } else if ((source.func_76346_g() != null) && ((isWerewolf(source.func_76346_g())) || (isVampire(source.func_76346_g())) || ((source.func_76346_g() instanceof IBossDisplayData))))
/*     */     {
/*     */ 
/* 283 */       dead = true;
/* 284 */     } else if ((isWerewolf(creature, true)) && (isSilverDamage(source))) {
/* 285 */       dead = true;
/*     */     }
/*     */     
/* 288 */     if (!dead) {
/* 289 */       creature.func_70606_j(1.0F);
/* 290 */       if ((creature instanceof EntityPlayer)) {
/* 291 */         ((EntityPlayer)creature).func_71024_bL().func_75113_a(5.0F);
/*     */       }
/* 293 */       if ((source.func_94541_c()) && (creature.field_70170_p.field_73012_v.nextInt(4) == 0)) {
/* 294 */         creature.func_70015_d(2);
/*     */       }
/* 296 */       return false;
/*     */     }
/*     */     
/* 299 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isInSunlight(EntityLivingBase entity) {
/* 303 */     World world = entity.field_70170_p;
/* 304 */     if ((world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) || (world.field_73011_w.field_76574_g == Config.instance().dimensionTormentID) || (world.field_73011_w.field_76576_e) || (!world.field_73011_w.func_76569_d()) || (!world.func_72935_r()))
/*     */     {
/*     */ 
/* 307 */       return false;
/*     */     }
/*     */     
/* 310 */     int x = MathHelper.func_76128_c(entity.field_70165_t);
/* 311 */     int y = MathHelper.func_76128_c(entity.field_70163_u);
/* 312 */     int z = MathHelper.func_76128_c(entity.field_70161_v);
/*     */     
/* 314 */     BiomeGenBase biome = world.func_72807_a(x, z);
/*     */     
/* 316 */     if (biome.field_76791_y.equals("Ominous Woods")) {
/* 317 */       return false;
/*     */     }
/*     */     
/* 320 */     if ((world.func_72896_J()) && (biome.func_76738_d())) {
/* 321 */       return false;
/*     */     }
/*     */     
/* 324 */     if (!world.func_72937_j(x, y + MathHelper.func_76143_f(entity.field_70131_O), z)) {
/* 325 */       return false;
/*     */     }
/*     */     
/* 328 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/CreatureUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */