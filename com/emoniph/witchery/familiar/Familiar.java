/*     */ package com.emoniph.witchery.familiar;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityOwl;
/*     */ import com.emoniph.witchery.entity.EntityToad;
/*     */ import com.emoniph.witchery.entity.EntityWitchCat;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TameableUtil;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Familiar
/*     */ {
/*  29 */   private static final String[] NAMES_TOAD = { "Casper", "Wart", "Langston", "Croaker", "Prince Charming", "Frog-n-stien", "Randolph", "Evileye", "Churchill", "Santa", "Dillinger", "Spuds" };
/*     */   
/*  31 */   private static final String[] NAMES_CAT = { "Pyewackett", "Salem", "Gobbolino", "Sabbath", "Norris", "Crookshanks", "Binx", "Voodoo", "Raven", "Simpkin", "Fishbone", "Kismet" };
/*     */   
/*  33 */   private static final String[] NAMES_OWL = { "Archimedes", "Dumbledornithologist", "Al Travis", "Baltimore", "Cornelius", "Hadwig", "Hoot", "Merlin", "Owl Capone", "Pigwidgeon", "Athena", "Albertine" };
/*     */   private static final String FAMILIAR_TAG_KEY = "WITCFamiliar";
/*     */   private static final String FAMILIAR_UUID_MOST = "UUIDMost";
/*     */   private static final String FAMILIAR_UUID_LEAST = "UUIDLeast";
/*     */   private static final String FAMILIAR_NAME = "FamiliarName";
/*     */   private static final String FAMILIAR_TYPE = "FamiliarType";
/*     */   private static final String FAMILIAR_COLOR = "FamiliarColor";
/*     */   private static final String FAMILIAR_SUMMONED = "FamiliarSummoned";
/*     */   public static final int FAMILIAR_NONE = 0;
/*     */   public static final int FAMILIAR_CAT = 1;
/*     */   public static final int FAMILIAR_TOAD = 2;
/*     */   public static final int FAMILIAR_OWL = 3;
/*     */   private static final float REDIRECTED_DAMAGE_PCT_FAR = 0.01F;
/*     */   private static final float REDIRECTED_DAMAGE_PCT_NEAR = 0.1F;
/*     */   private static final float MAX_HEALTH = 50.0F;
/*     */   private static final float FAMILIAR_NEAR_DISTANCE_SQ = 576.0F;
/*     */   
/*  50 */   public static void bindToPlayer(EntityPlayer player, EntityTameable familiarEntity) { if ((canBecomeFamiliar(familiarEntity)) && (TameableUtil.isOwner(familiarEntity, player))) {
/*  51 */       NBTTagCompound nbtTag = Infusion.getNBT(player);
/*  52 */       if (nbtTag != null) {
/*  53 */         EntityTameable currentFamiliar = getFamiliarEntity(player);
/*  54 */         if (currentFamiliar != null) {
/*  55 */           ((IFamiliar)currentFamiliar).clearFamiliar();
/*     */         }
/*     */         
/*  58 */         if ((familiarEntity instanceof EntityOcelot)) {
/*  59 */           EntityOcelot oldCat = (EntityOcelot)familiarEntity;
/*  60 */           EntityWitchCat newCat = new EntityWitchCat(oldCat.field_70170_p);
/*  61 */           newCat.cloneOcelot(oldCat);
/*  62 */           newCat.func_70912_b(1);
/*  63 */           oldCat.func_70106_y();
/*  64 */           newCat.field_70170_p.func_72838_d(newCat);
/*  65 */           newCat.field_70170_p.func_72960_a(newCat, (byte)7);
/*  66 */           familiarEntity = newCat;
/*     */         }
/*     */         
/*  69 */         IFamiliar familiar = (IFamiliar)familiarEntity;
/*     */         
/*  71 */         NBTTagCompound nbtFamiliar = new NBTTagCompound();
/*  72 */         nbtFamiliar.func_74772_a("UUIDMost", familiarEntity.func_110124_au().getMostSignificantBits());
/*  73 */         nbtFamiliar.func_74772_a("UUIDLeast", familiarEntity.func_110124_au().getLeastSignificantBits());
/*     */         
/*  75 */         String name = "Familiar";
/*  76 */         if ((familiarEntity instanceof EntityOwl)) {
/*  77 */           name = NAMES_OWL[player.field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
/*  78 */           nbtFamiliar.func_74768_a("FamiliarType", 3);
/*  79 */           nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityOwl)familiar).getFeatherColor()).byteValue());
/*  80 */         } else if ((familiarEntity instanceof EntityToad)) {
/*  81 */           name = NAMES_TOAD[player.field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
/*  82 */           nbtFamiliar.func_74768_a("FamiliarType", 2);
/*  83 */           nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityToad)familiar).getSkinColor()).byteValue());
/*  84 */         } else if ((familiarEntity instanceof EntityOcelot)) {
/*  85 */           name = NAMES_CAT[player.field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
/*  86 */           nbtFamiliar.func_74768_a("FamiliarType", 1);
/*  87 */           nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)0).byteValue());
/*     */         }
/*     */         
/*  90 */         if ((!familiarEntity.func_94056_bM()) && (name != null) && (!name.isEmpty())) {
/*  91 */           familiarEntity.func_94058_c(name);
/*     */         }
/*     */         
/*  94 */         nbtFamiliar.func_74778_a("FamiliarName", familiarEntity.func_94057_bL());
/*  95 */         nbtFamiliar.func_74774_a("FamiliarSummoned", Byte.valueOf((byte)1).byteValue());
/*     */         
/*  97 */         nbtTag.func_74782_a("WITCFamiliar", nbtFamiliar);
/*     */         
/*  99 */         familiar.setMaxHealth(50.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean canBecomeFamiliar(EntityTameable familiarEntity) {
/* 105 */     return (familiarEntity != null) && (familiarEntity.func_70909_n()) && (((familiarEntity instanceof EntityWitchCat)) || ((familiarEntity instanceof EntityOcelot)) || ((familiarEntity instanceof EntityToad)) || ((familiarEntity instanceof EntityOwl)));
/*     */   }
/*     */   
/*     */ 
/*     */   public static EntityTameable getFamiliarEntityByID(EntityPlayer player, UUID uuidFamiliar)
/*     */   {
/* 111 */     if (uuidFamiliar != null) {
/* 112 */       List list = player.field_70170_p.field_72996_f;
/* 113 */       for (int i = 0; i < list.size(); i++) {
/* 114 */         Object obj = list.get(i);
/* 115 */         if ((obj instanceof EntityTameable)) {
/* 116 */           EntityTameable tameableEntity = (EntityTameable)obj;
/* 117 */           if (tameableEntity.func_110124_au().equals(uuidFamiliar)) {
/* 118 */             return tameableEntity;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 123 */       if (!player.field_70170_p.field_72995_K) {
/* 124 */         MinecraftServer server = MinecraftServer.func_71276_C();
/* 125 */         for (WorldServer worldServer : server.field_71305_c) {
/* 126 */           List list2 = worldServer.field_72996_f;
/* 127 */           for (int i = 0; i < list2.size(); i++) {
/* 128 */             Object obj = list2.get(i);
/* 129 */             if ((obj instanceof EntityTameable)) {
/* 130 */               EntityTameable tameableEntity = (EntityTameable)obj;
/* 131 */               if (tameableEntity.func_110124_au().equals(uuidFamiliar)) {
/* 132 */                 return tameableEntity;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   public static EntityTameable getFamiliarEntity(EntityPlayer player) {
/* 145 */     UUID uuidFamiliar = getFamiliarEntityID(player);
/* 146 */     EntityTameable familiar = getFamiliarEntityByID(player, uuidFamiliar);
/* 147 */     return familiar;
/*     */   }
/*     */   
/*     */   public static UUID getFamiliarEntityID(EntityPlayer player) {
/* 151 */     if (player != null) {
/* 152 */       NBTTagCompound nbtTag = Infusion.getNBT(player);
/* 153 */       if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
/* 154 */         NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
/* 155 */         if ((nbtFamiliar != null) && (nbtFamiliar.func_74764_b("UUIDMost")) && (nbtFamiliar.func_74764_b("UUIDLeast"))) {
/* 156 */           UUID uuidFamiliar = new UUID(nbtFamiliar.func_74763_f("UUIDMost"), nbtFamiliar.func_74763_f("UUIDLeast"));
/* 157 */           return uuidFamiliar;
/*     */         }
/*     */       }
/*     */     }
/* 161 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isPlayerBoundToFamiliar(EntityPlayer player, EntityTameable familiar) {
/* 165 */     if ((player != null) && (familiar != null)) {
/* 166 */       NBTTagCompound nbtTag = Infusion.getNBT(player);
/* 167 */       if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
/* 168 */         NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
/* 169 */         if ((nbtFamiliar != null) && (nbtFamiliar.func_74764_b("UUIDMost")) && (nbtFamiliar.func_74764_b("UUIDLeast"))) {
/* 170 */           UUID uuidFamiliar = new UUID(nbtFamiliar.func_74763_f("UUIDMost"), nbtFamiliar.func_74763_f("UUIDLeast"));
/* 171 */           return uuidFamiliar.equals(familiar.func_110124_au());
/*     */         }
/*     */       }
/*     */     }
/* 175 */     return false;
/*     */   }
/*     */   
/*     */   public static class FamiliarOwner
/*     */   {
/*     */     private final EntityPlayer player;
/*     */     private final boolean owner;
/*     */     
/*     */     public FamiliarOwner(EntityPlayer player, boolean owner) {
/* 184 */       this.player = player;
/* 185 */       this.owner = owner;
/*     */     }
/*     */     
/*     */     public EntityPlayer getPlayer() {
/* 189 */       return this.player;
/*     */     }
/*     */     
/*     */     public boolean isOwner() {
/* 193 */       return this.owner;
/*     */     }
/*     */     
/*     */     public EntityPlayer getCurrentOwner() {
/* 197 */       return this.owner ? this.player : null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static FamiliarOwner getOwnerForFamiliar(EntityTameable familiar) {
/* 202 */     if ((familiar != null) && (!familiar.field_70170_p.field_72995_K) && (familiar.func_70909_n())) {
/* 203 */       EntityLivingBase owner = familiar.func_70902_q();
/* 204 */       if ((owner != null) && ((owner instanceof EntityPlayer))) {
/* 205 */         EntityPlayer player = (EntityPlayer)owner;
/* 206 */         UUID uuidFamiliar = getFamiliarEntityID(player);
/* 207 */         if ((uuidFamiliar != null) && (uuidFamiliar.equals(familiar.func_110124_au()))) {
/* 208 */           return new FamiliarOwner(player, true);
/*     */         }
/* 210 */         return new FamiliarOwner(player, false);
/*     */       }
/*     */     }
/*     */     
/* 214 */     return new FamiliarOwner(null, false);
/*     */   }
/*     */   
/*     */   public static boolean hasActiveCurseMasteryFamiliar(EntityPlayer player) {
/* 218 */     int familiarType = getActiveFamiliarType(player);
/* 219 */     return familiarType == 1;
/*     */   }
/*     */   
/*     */   public static boolean hasActiveBrewMasteryFamiliar(EntityPlayer player) {
/* 223 */     int familiarType = getActiveFamiliarType(player);
/* 224 */     return familiarType == 2;
/*     */   }
/*     */   
/*     */   public static boolean hasActiveBroomMasteryFamiliar(EntityPlayer player) {
/* 228 */     int familiarType = getActiveFamiliarType(player);
/* 229 */     return familiarType == 3;
/*     */   }
/*     */   
/*     */   public static boolean hasActiveFamiliar(EntityPlayer player) {
/* 233 */     int familiarType = getActiveFamiliarType(player);
/* 234 */     return familiarType > 0;
/*     */   }
/*     */   
/*     */   public static int getActiveFamiliarType(EntityPlayer player) {
/* 238 */     if ((player != null) && (!player.field_70170_p.field_72995_K)) {
/* 239 */       NBTTagCompound nbtTag = Infusion.getNBT(player);
/* 240 */       if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
/* 241 */         NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
/* 242 */         if ((nbtFamiliar.func_74764_b("FamiliarSummoned")) && (nbtFamiliar.func_74764_b("FamiliarType")) && (nbtFamiliar.func_74764_b("FamiliarName"))) {
/* 243 */           int summoned = nbtFamiliar.func_74771_c("FamiliarSummoned");
/* 244 */           if (summoned == 1) {
/* 245 */             int type = nbtFamiliar.func_74762_e("FamiliarType");
/* 246 */             return type;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 251 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void handlePlayerHurt(LivingHurtEvent event, EntityPlayer player)
/*     */   {
/* 260 */     World world = event.entityLiving.field_70170_p;
/* 261 */     if ((!world.field_72995_K) && (!event.isCanceled()))
/*     */     {
/* 263 */       UUID familiarID = getFamiliarEntityID(player);
/* 264 */       if (familiarID != null) {
/* 265 */         float totalDamage = event.ammount;
/* 266 */         float redirectedDamage = totalDamage * 0.01F;
/* 267 */         EntityTameable familiar = getFamiliarEntityByID(player, familiarID);
/* 268 */         if (familiar != null) {
/* 269 */           if (familiar.func_70068_e(player) <= 576.0D) {
/* 270 */             redirectedDamage = totalDamage * 0.1F;
/*     */           }
/* 272 */           if (redirectedDamage >= 1.0F) {
/* 273 */             familiar.func_70097_a(event.source, redirectedDamage);
/*     */           }
/*     */         }
/* 276 */         event.ammount -= redirectedDamage;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void handleLivingDeath(LivingDeathEvent event) {
/* 282 */     World world = event.entityLiving.field_70170_p;
/* 283 */     if ((!world.field_72995_K) && (!event.isCanceled()))
/*     */     {
/* 285 */       if ((event.entityLiving instanceof EntityTameable)) {
/* 286 */         EntityTameable tameableEntity = (EntityTameable)event.entityLiving;
/* 287 */         if (couldBeFamiliar(tameableEntity)) {
/* 288 */           FamiliarOwner owner = getOwnerForFamiliar(tameableEntity);
/* 289 */           if ((owner.player != null) && (owner.isOwner())) {
/* 290 */             NBTTagCompound nbtTag = Infusion.getNBT(owner.player);
/* 291 */             owner.player.func_70097_a(DamageSource.field_76376_m, owner.player.func_110138_aP() * 2.0F);
/* 292 */             dismissFamiliar(owner.player, tameableEntity);
/* 293 */             event.setCanceled(true);
/* 294 */           } else if (owner.player == null) {
/* 295 */             tameableEntity.func_70606_j(1.0F);
/* 296 */             event.setCanceled(true);
/*     */           }
/*     */         }
/* 299 */       } else if ((event.entityLiving instanceof EntityPlayer)) {
/* 300 */         EntityPlayer player = (EntityPlayer)event.entityLiving;
/* 301 */         EntityTameable familiar = getFamiliarEntity(player);
/* 302 */         if ((familiar != null) && (!familiar.field_70128_L)) {
/* 303 */           dismissFamiliar(player, familiar);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void dismissFamiliar(EntityPlayer player, EntityTameable familiar) {
/* 310 */     if ((player != null) && (familiar != null) && (!player.field_70170_p.field_72995_K) && 
/* 311 */       (isPlayerBoundToFamiliar(player, familiar))) {
/* 312 */       NBTTagCompound nbtTag = Infusion.getNBT(player);
/* 313 */       if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
/* 314 */         NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
/* 315 */         nbtFamiliar.func_74778_a("FamiliarName", familiar.func_94057_bL());
/* 316 */         nbtFamiliar.func_74774_a("FamiliarSummoned", Byte.valueOf((byte)0).byteValue());
/* 317 */         if ((familiar instanceof EntityOwl)) {
/* 318 */           nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityOwl)familiar).getFeatherColor()).byteValue());
/* 319 */         } else if ((familiar instanceof EntityToad)) {
/* 320 */           nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityToad)familiar).getSkinColor()).byteValue());
/*     */         }
/*     */         
/* 323 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_ENDERMEN_PORTAL, familiar, 1.0D, 1.0D, 16);
/*     */         
/* 325 */         familiar.func_70106_y();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getFamiliarName(EntityPlayer player)
/*     */   {
/* 332 */     NBTTagCompound nbtTag = Infusion.getNBT(player);
/* 333 */     if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
/* 334 */       NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
/* 335 */       if ((nbtFamiliar.func_74764_b("FamiliarSummoned")) && (nbtFamiliar.func_74764_b("FamiliarType")) && (nbtFamiliar.func_74764_b("FamiliarName"))) {
/* 336 */         int summoned = nbtFamiliar.func_74771_c("FamiliarSummoned");
/* 337 */         String name = nbtFamiliar.func_74779_i("FamiliarName");
/* 338 */         return name;
/*     */       }
/*     */     }
/* 341 */     return null;
/*     */   }
/*     */   
/*     */   public static EntityTameable summonFamiliar(EntityPlayer player, double x, double y, double z) {
/* 345 */     if ((player != null) && (!player.field_70170_p.field_72995_K)) {
/* 346 */       NBTTagCompound nbtTag = Infusion.getNBT(player);
/* 347 */       if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
/* 348 */         NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
/* 349 */         if ((nbtFamiliar.func_74764_b("FamiliarSummoned")) && (nbtFamiliar.func_74764_b("FamiliarType")) && (nbtFamiliar.func_74764_b("FamiliarName"))) {
/* 350 */           int summoned = nbtFamiliar.func_74771_c("FamiliarSummoned");
/* 351 */           if (summoned == 0) {
/* 352 */             String name = nbtFamiliar.func_74779_i("FamiliarName");
/* 353 */             int type = nbtFamiliar.func_74762_e("FamiliarType");
/* 354 */             int color = nbtFamiliar.func_74771_c("FamiliarColor");
/*     */             
/* 356 */             EntityTameable familiar = null;
/* 357 */             switch (type) {
/*     */             case 1: 
/* 359 */               familiar = new EntityWitchCat(player.field_70170_p);
/* 360 */               break;
/*     */             case 2: 
/* 362 */               familiar = new EntityToad(player.field_70170_p);
/* 363 */               ((EntityToad)familiar).setSkinColor(color);
/* 364 */               break;
/*     */             case 3: 
/* 366 */               familiar = new EntityOwl(player.field_70170_p);
/* 367 */               ((EntityOwl)familiar).setFeatherColor(color);
/* 368 */               break;
/*     */             default: 
/* 370 */               return null;
/*     */             }
/*     */             
/* 373 */             familiar.func_70903_f(true);
/* 374 */             TameableUtil.setOwner(familiar, player);
/* 375 */             familiar.func_94058_c(name);
/* 376 */             ((IFamiliar)familiar).setMaxHealth(50.0F);
/* 377 */             familiar.func_70012_b(x, y, z, 0.0F, 0.0F);
/* 378 */             player.field_70170_p.func_72838_d(familiar);
/*     */             
/* 380 */             nbtFamiliar.func_74772_a("UUIDMost", familiar.func_110124_au().getMostSignificantBits());
/* 381 */             nbtFamiliar.func_74772_a("UUIDLeast", familiar.func_110124_au().getLeastSignificantBits());
/*     */             
/* 383 */             ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_ENDERMEN_PORTAL, familiar, 1.0D, 1.0D, 16);
/*     */             
/* 385 */             nbtFamiliar.func_74774_a("FamiliarSummoned", Byte.valueOf((byte)1).byteValue());
/* 386 */             return familiar;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 391 */     return null;
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
/*     */   public static boolean couldBeFamiliar(EntityTameable entity)
/*     */   {
/* 445 */     if ((entity instanceof IFamiliar)) {
/* 446 */       IFamiliar familiar = (IFamiliar)entity;
/* 447 */       return familiar.isFamiliar();
/*     */     }
/*     */     
/* 450 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/familiar/Familiar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */