/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.familiar.IFamiliar;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.item.ItemTaglockKit;
/*     */ import com.emoniph.witchery.item.ItemTaglockKit.BoundType;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityAmbientCreature;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityTreefyd extends EntityMob implements IEntitySelector
/*     */ {
/*     */   public EntityTreefyd(World par1World)
/*     */   {
/*  54 */     super(par1World);
/*  55 */     func_70105_a(0.4F, 1.8F);
/*  56 */     func_70661_as().func_75491_a(true);
/*  57 */     func_70661_as().func_75495_e(true);
/*  58 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*     */     
/*     */ 
/*  61 */     this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, false));
/*  62 */     this.field_70714_bg.func_75776_a(5, new com.emoniph.witchery.entity.ai.EntityAITreefydWander(this, 0.8D));
/*  63 */     this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  64 */     this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
/*  65 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, true, this));
/*     */     
/*     */ 
/*  68 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*     */   }
/*     */   
/*     */   private static class CreatureID {
/*     */     UUID id;
/*     */     String name;
/*     */     
/*     */     public CreatureID(UUID id, String name) {
/*  76 */       this.id = id;
/*  77 */       this.name = name;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj)
/*     */     {
/*  82 */       if (obj == null) {
/*  83 */         return false;
/*     */       }
/*     */       
/*  86 */       if (obj == this) {
/*  87 */         return true;
/*     */       }
/*     */       
/*  90 */       if ((obj instanceof UUID)) {
/*  91 */         return this.id.equals((UUID)obj);
/*     */       }
/*     */       
/*  94 */       if (obj.getClass() == getClass()) {
/*  95 */         return this.id.equals(((CreatureID)obj).id);
/*     */       }
/*     */       
/*  98 */       return false;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/* 103 */       return this.name;
/*     */     }
/*     */   }
/*     */   
/* 107 */   public CreatureID testID = new CreatureID(new UUID(0L, 0L), "");
/*     */   
/*     */   public boolean func_82704_a(Entity entity) {
/* 110 */     if ((entity.getClass() == getClass()) || ((entity instanceof EntityHornedHuntsman)) || ((entity instanceof EntityEnt)) || ((entity instanceof net.minecraft.entity.EntityFlying)) || ((entity instanceof EntityFlyingTameable)) || ((entity instanceof EntityAmbientCreature)) || ((entity instanceof net.minecraft.entity.passive.EntityWaterMob)) || (isFamiliar(entity)) || ((entity instanceof EntityCovenWitch)) || ((entity instanceof EntityCorpse)))
/*     */     {
/*     */ 
/*     */ 
/* 114 */       return false;
/*     */     }
/*     */     
/* 117 */     if ((entity instanceof EntityPlayer)) {
/* 118 */       EntityPlayer player = (EntityPlayer)entity;
/* 119 */       String ownerName = getOwnerName();
/* 120 */       if ((ownerName != null) && (!ownerName.isEmpty()) && (player.func_70005_c_().equals(ownerName))) {
/* 121 */         return false;
/*     */       }
/*     */       
/* 124 */       if ((this.knownPlayers != null) && (this.knownPlayers.contains(player.func_70005_c_()))) {
/* 125 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 129 */     if ((entity instanceof EntityLiving)) {
/* 130 */       EntityLiving creature = (EntityLiving)entity;
/* 131 */       if ((this.knownCreatureTypes != null) && (this.knownCreatureTypes.contains(creature.func_70005_c_()))) {
/* 132 */         return false;
/*     */       }
/* 134 */       this.testID.id = entity.func_110124_au();
/* 135 */       if ((this.knownCreatures != null) && (this.knownCreatures.contains(this.testID))) {
/* 136 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 145 */     return super.func_70627_aG() * 2;
/*     */   }
/*     */   
/*     */   protected boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 150 */     if ((!this.field_70170_p.field_72995_K) && (player != null) && (player.func_70005_c_().equals(getOwnerName()))) {
/* 151 */       ItemStack stack = player.func_70694_bm();
/* 152 */       if ((stack != null) && (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT)) {
/* 153 */         func_70624_b(null);
/* 154 */         ItemTaglockKit.BoundType boundEntityType = Witchery.Items.TAGLOCK_KIT.getBoundEntityType(stack, Integer.valueOf(1));
/*     */         
/* 156 */         switch (boundEntityType) {
/*     */         case PLAYER: 
/* 158 */           String otherUsername = Witchery.Items.TAGLOCK_KIT.getBoundUsername(stack, Integer.valueOf(1));
/* 159 */           if (!player.func_70005_c_().equals(otherUsername)) {
/* 160 */             if ((!player.func_70093_af()) && (!this.knownPlayers.contains(otherUsername))) {
/* 161 */               this.knownPlayers.add(otherUsername);
/* 162 */             } else if ((player.func_70093_af()) && (this.knownPlayers.contains(otherUsername))) {
/* 163 */               this.knownPlayers.remove(otherUsername);
/*     */             } else {
/* 165 */               showCurrentKnownEntities(player);
/*     */               
/* 167 */               return super.func_70085_c(player);
/*     */             }
/* 169 */             stack.field_77994_a -= 1;
/* 170 */             if (stack.field_77994_a <= 0) {
/* 171 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */             }
/* 173 */             if ((player instanceof EntityPlayerMP)) {
/* 174 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */             
/* 177 */             showCurrentKnownEntities(player);
/*     */             
/* 179 */             return true;
/*     */           }
/*     */           break;
/*     */         case CREATURE: 
/* 183 */           UUID otherCreature = Witchery.Items.TAGLOCK_KIT.getBoundCreatureID(stack, Integer.valueOf(1));
/* 184 */           String creatureName = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
/* 185 */           if (!otherCreature.equals(func_110124_au())) {
/* 186 */             if (isGroupableCreature(otherCreature, creatureName)) {
/* 187 */               if ((!player.func_70093_af()) && (!this.knownCreatureTypes.contains(creatureName))) {
/* 188 */                 this.knownCreatureTypes.add(creatureName);
/* 189 */               } else if ((player.func_70093_af()) && (this.knownCreatureTypes.contains(creatureName))) {
/* 190 */                 this.knownCreatureTypes.remove(creatureName);
/*     */               } else {
/* 192 */                 showCurrentKnownEntities(player);
/* 193 */                 return super.func_70085_c(player);
/*     */               }
/*     */             } else {
/* 196 */               CreatureID creatureID = new CreatureID(otherCreature, creatureName);
/* 197 */               if ((!player.func_70093_af()) && (!this.knownCreatures.contains(creatureID))) {
/* 198 */                 this.knownCreatures.add(creatureID);
/* 199 */               } else if ((player.func_70093_af()) && (this.knownCreatures.contains(creatureID))) {
/* 200 */                 this.knownCreatures.remove(creatureID);
/*     */               } else {
/* 202 */                 showCurrentKnownEntities(player);
/* 203 */                 return super.func_70085_c(player);
/*     */               }
/*     */             }
/* 206 */             stack.field_77994_a -= 1;
/* 207 */             if (stack.field_77994_a <= 0) {
/* 208 */               player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */             }
/* 210 */             if ((player instanceof EntityPlayerMP)) {
/* 211 */               ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*     */             }
/*     */           }
/* 214 */           showCurrentKnownEntities(player);
/* 215 */           return true;
/*     */         }
/*     */         
/*     */       }
/* 219 */       else if ((stack != null) && (Witchery.Items.GENERIC.itemSeedsTreefyd.isMatch(stack))) {
/* 220 */         if (!this.field_70170_p.field_72995_K) {
/* 221 */           EntityTreefyd entity = new EntityTreefyd(this.field_70170_p);
/* 222 */           entity.func_70012_b(0.5D + this.field_70165_t, this.field_70163_u, 0.5D + this.field_70161_v, 0.0F, 0.0F);
/* 223 */           entity.func_110161_a(null);
/* 224 */           entity.setOwner(player.func_70005_c_());
/* 225 */           entity.func_110163_bv();
/* 226 */           entity.knownPlayers = ((ArrayList)this.knownPlayers.clone());
/* 227 */           entity.knownCreatureTypes = ((ArrayList)this.knownCreatureTypes.clone());
/* 228 */           entity.knownCreatures = ((ArrayList)this.knownCreatures.clone());
/*     */           
/* 230 */           this.field_70170_p.func_72838_d(entity);
/*     */           
/* 232 */           ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, this, 1.0D, 2.0D, 16);
/* 233 */           ParticleEffect.EXPLODE.send(SoundEffect.NONE, this, 1.0D, 2.0D, 16);
/*     */         }
/* 235 */         if (!player.field_71075_bZ.field_75098_d) {
/* 236 */           stack.field_77994_a -= 1;
/*     */         }
/* 238 */       } else if ((stack != null) && (Witchery.Items.GENERIC.itemCreeperHeart.isMatch(stack))) {
/* 239 */         if (!this.field_70170_p.field_72995_K) {
/* 240 */           func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/* 241 */           func_70606_j(func_110138_aP());
/* 242 */           func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/* 243 */           ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, this, 0.5D, 2.0D, 16);
/*     */         }
/* 245 */         if (!player.field_71075_bZ.field_75098_d) {
/* 246 */           stack.field_77994_a -= 1;
/*     */         }
/* 248 */       } else if ((stack != null) && (Witchery.Items.GENERIC.itemDemonHeart.isMatch(stack))) {
/* 249 */         if (!this.field_70170_p.field_72995_K) {
/* 250 */           func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0D);
/* 251 */           func_70606_j(func_110138_aP());
/* 252 */           func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/* 253 */           ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, this, 0.5D, 2.0D, 16);
/*     */         }
/* 255 */         if (!player.field_71075_bZ.field_75098_d) {
/* 256 */           stack.field_77994_a -= 1;
/*     */         }
/* 258 */       } else if ((stack != null) && (stack.func_77973_b() == Witchery.Items.BOLINE) && 
/* 259 */         (!this.field_70170_p.field_72995_K)) {
/* 260 */         setSentinal(!isSentinal());
/*     */       }
/*     */       
/*     */ 
/* 264 */       showCurrentKnownEntities(player);
/*     */     }
/* 266 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/* 269 */   private static ArrayList<String> groupables = null;
/*     */   
/*     */   private boolean isGroupableCreature(UUID otherCreature, String creatureName) {
/* 272 */     if (groupables == null) {
/* 273 */       groupables = new ArrayList();
/* 274 */       addGroupableType(EntityVillager.class);
/* 275 */       addGroupableType(EntityGoblin.class);
/* 276 */       addGroupableType(net.minecraft.entity.passive.EntitySheep.class);
/* 277 */       addGroupableType(EntityCow.class);
/* 278 */       addGroupableType(net.minecraft.entity.passive.EntityMooshroom.class);
/* 279 */       addGroupableType(EntityChicken.class);
/* 280 */       addGroupableType(EntityPig.class);
/* 281 */       addGroupableType(EntityHorse.class);
/*     */     }
/* 283 */     return groupables.contains(creatureName);
/*     */   }
/*     */   
/*     */   private void addGroupableType(Class<? extends EntityLiving> className) {
/* 287 */     String name = (String)EntityList.field_75626_c.get(className);
/* 288 */     if (name != null) {
/* 289 */       String localName = StatCollector.func_74838_a("entity." + name + ".name");
/* 290 */       groupables.add(localName);
/*     */     }
/*     */   }
/*     */   
/*     */   private void showCurrentKnownEntities(EntityPlayer player) {
/* 295 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 297 */     String ownerName = getOwnerName();
/* 298 */     if ((ownerName != null) && (!ownerName.isEmpty())) {
/* 299 */       sb.append(getOwnerName());
/*     */     }
/* 301 */     for (String s : this.knownPlayers) {
/* 302 */       if (sb.length() > 0) {
/* 303 */         sb.append(", ");
/*     */       }
/* 305 */       sb.append(s);
/*     */     }
/*     */     
/* 308 */     for (String s : this.knownCreatureTypes) {
/* 309 */       if (sb.length() > 0) {
/* 310 */         sb.append(", ");
/*     */       }
/* 312 */       sb.append("#");
/* 313 */       sb.append(s);
/*     */     }
/*     */     
/* 316 */     for (CreatureID cid : this.knownCreatures) {
/* 317 */       if (sb.length() > 0) {
/* 318 */         sb.append(", ");
/*     */       }
/* 320 */       sb.append(cid.toString());
/*     */     }
/*     */     
/* 323 */     String message = func_70005_c_() + " (" + sb.toString() + ")";
/* 324 */     ChatUtil.sendPlain(player, message);
/*     */   }
/*     */   
/*     */   private boolean isFamiliar(Entity entity) {
/* 328 */     if ((entity instanceof IFamiliar)) {
/* 329 */       IFamiliar familiar = (IFamiliar)entity;
/* 330 */       return familiar.isFamiliar();
/*     */     }
/* 332 */     return false;
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 337 */     if (func_94056_bM()) {
/* 338 */       return func_94057_bL();
/*     */     }
/* 340 */     return StatCollector.func_74838_a("entity.witchery.treefyd.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 346 */     super.func_110147_ax();
/* 347 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/* 348 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/* 349 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 354 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity entity)
/*     */   {
/* 359 */     if ((!this.field_70170_p.field_72995_K) && 
/* 360 */       ((entity instanceof EntityPlayer))) {
/* 361 */       EntityPlayer player = (EntityPlayer)entity;
/* 362 */       if (!player.func_70644_a(Potion.field_76440_q)) {
/* 363 */         player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 100, 0));
/*     */       }
/*     */     }
/* 366 */     return super.func_70652_k(entity);
/*     */   }
/*     */   
/*     */   public int func_82143_as()
/*     */   {
/* 371 */     return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 376 */     super.func_70088_a();
/* 377 */     this.field_70180_af.func_75682_a(17, "");
/* 378 */     this.field_70180_af.func_75682_a(18, Integer.valueOf(Integer.valueOf(0).intValue()));
/*     */   }
/*     */   
/*     */   public boolean isSentinal() {
/* 382 */     return this.field_70180_af.func_75679_c(18) == 1;
/*     */   }
/*     */   
/*     */   protected void setSentinal(boolean screaming) {
/* 386 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(Integer.valueOf(screaming ? 1 : 0).intValue()));
/*     */   }
/*     */   
/* 389 */   private ArrayList<String> knownPlayers = new ArrayList();
/* 390 */   private ArrayList<String> knownCreatureTypes = new ArrayList();
/* 391 */   private ArrayList<CreatureID> knownCreatures = new ArrayList();
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 395 */     super.func_70014_b(nbtRoot);
/*     */     
/* 397 */     if (getOwnerName() == null) {
/* 398 */       nbtRoot.func_74778_a("Owner", "");
/*     */     } else {
/* 400 */       nbtRoot.func_74778_a("Owner", getOwnerName());
/*     */     }
/*     */     
/* 403 */     if (this.knownPlayers.size() > 0) {
/* 404 */       NBTTagList nbtPlayers = new NBTTagList();
/* 405 */       for (String playerName : this.knownPlayers) {
/* 406 */         NBTTagCompound nbtKnownPlayer = new NBTTagCompound();
/* 407 */         nbtKnownPlayer.func_74778_a("PlayerName", playerName);
/* 408 */         nbtPlayers.func_74742_a(nbtKnownPlayer);
/*     */       }
/* 410 */       nbtRoot.func_74782_a("KnownPlayers", nbtPlayers);
/*     */     }
/*     */     
/* 413 */     if (this.knownCreatureTypes.size() > 0) {
/* 414 */       NBTTagList nbtCreatureTypes = new NBTTagList();
/* 415 */       for (String typeName : this.knownCreatureTypes) {
/* 416 */         NBTTagCompound nbtKnownCreatureType = new NBTTagCompound();
/* 417 */         nbtKnownCreatureType.func_74778_a("CreatureTypeName", typeName);
/* 418 */         nbtCreatureTypes.func_74742_a(nbtKnownCreatureType);
/*     */       }
/* 420 */       nbtRoot.func_74782_a("KnownCreatureTypes", nbtCreatureTypes);
/*     */     }
/*     */     
/* 423 */     if (this.knownCreatures.size() > 0) {
/* 424 */       NBTTagList nbtCreatures = new NBTTagList();
/* 425 */       for (CreatureID creatureID : this.knownCreatures) {
/* 426 */         NBTTagCompound nbtKnownCreature = new NBTTagCompound();
/* 427 */         nbtKnownCreature.func_74772_a("CreatureMost", creatureID.id.getMostSignificantBits());
/* 428 */         nbtKnownCreature.func_74772_a("CreatureLeast", creatureID.id.getLeastSignificantBits());
/* 429 */         nbtKnownCreature.func_74778_a("CreatureName", creatureID.name);
/* 430 */         nbtCreatures.func_74742_a(nbtKnownCreature);
/*     */       }
/* 432 */       nbtRoot.func_74782_a("KnownCreatures", nbtCreatures);
/*     */     }
/*     */     
/* 435 */     nbtRoot.func_74757_a("SentinalPlant", isSentinal());
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 440 */     super.func_70037_a(nbtRoot);
/* 441 */     String s = nbtRoot.func_74779_i("Owner");
/*     */     
/* 443 */     if (s.length() > 0) {
/* 444 */       setOwner(s);
/*     */     }
/*     */     
/* 447 */     if (nbtRoot.func_74764_b("KnownPlayers")) {
/* 448 */       NBTTagList nbtPlayers = nbtRoot.func_150295_c("KnownPlayers", 10);
/* 449 */       this.knownPlayers = new ArrayList();
/* 450 */       for (int i = 0; i < nbtPlayers.func_74745_c(); i++) {
/* 451 */         NBTTagCompound nbtKnownPlayer = nbtPlayers.func_150305_b(i);
/* 452 */         String playerName = nbtKnownPlayer.func_74779_i("PlayerName");
/* 453 */         if ((playerName != null) && (!playerName.isEmpty())) {
/* 454 */           this.knownPlayers.add(playerName);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 459 */     if (nbtRoot.func_74764_b("KnownCreatureTypes")) {
/* 460 */       NBTTagList nbtCreatureTypes = nbtRoot.func_150295_c("KnownCreatureTypes", 10);
/* 461 */       this.knownCreatureTypes = new ArrayList();
/* 462 */       for (int i = 0; i < nbtCreatureTypes.func_74745_c(); i++) {
/* 463 */         NBTTagCompound nbtKnownCreatureType = nbtCreatureTypes.func_150305_b(i);
/* 464 */         String typeName = nbtKnownCreatureType.func_74779_i("CreatureTypeName");
/* 465 */         if ((typeName != null) && (!typeName.isEmpty())) {
/* 466 */           this.knownCreatureTypes.add(typeName);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 471 */     if (nbtRoot.func_74764_b("KnownCreatures")) {
/* 472 */       NBTTagList nbtCreatures = nbtRoot.func_150295_c("KnownCreatures", 10);
/* 473 */       this.knownCreatures = new ArrayList();
/* 474 */       for (int i = 0; i < nbtCreatures.func_74745_c(); i++) {
/* 475 */         NBTTagCompound nbtKnownCreature = nbtCreatures.func_150305_b(i);
/* 476 */         String playerName = nbtKnownCreature.func_74779_i("PlayerName");
/* 477 */         long uuidMost = nbtKnownCreature.func_74763_f("CreatureMost");
/* 478 */         long uuidLeast = nbtKnownCreature.func_74763_f("CreatureLeast");
/* 479 */         String cname = nbtKnownCreature.func_74779_i("CreatureName");
/* 480 */         if ((uuidMost != 0L) || (uuidLeast != 0L)) {
/* 481 */           UUID creatureID = new UUID(uuidMost, uuidLeast);
/* 482 */           this.knownCreatures.add(new CreatureID(creatureID, cname));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 487 */     if (nbtRoot.func_74764_b("SentinalPlant")) {
/* 488 */       setSentinal(nbtRoot.func_74767_n("SentinalPlant"));
/*     */     }
/*     */   }
/*     */   
/*     */   public String getOwnerName() {
/* 493 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setOwner(String par1Str) {
/* 497 */     func_110163_bv();
/* 498 */     this.field_70180_af.func_75692_b(17, par1Str);
/*     */   }
/*     */   
/*     */   public EntityPlayer getOwnerEntity() {
/* 502 */     return this.field_70170_p.func_72924_a(getOwnerName());
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 507 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 512 */     return "mob.silverfish.hit";
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 517 */     return "witchery:mob.treefyd.treefyd_say";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 522 */     return "mob.creeper.death";
/*     */   }
/*     */   
/*     */   protected Item func_146068_u()
/*     */   {
/* 527 */     return Item.func_150898_a(net.minecraft.init.Blocks.field_150328_O);
/*     */   }
/*     */   
/*     */   protected void func_70600_l(int par1) {
/* 531 */     func_70099_a(Witchery.Items.GENERIC.itemSeedsTreefyd.createStack(), 0.0F);
/*     */   }
/*     */   
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 536 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityTreefyd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */