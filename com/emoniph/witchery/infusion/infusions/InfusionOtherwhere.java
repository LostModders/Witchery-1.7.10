/*     */ package com.emoniph.witchery.infusion.infusions;
/*     */ 
/*     */ import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.DimensionalLocation;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class InfusionOtherwhere extends Infusion
/*     */ {
/*     */   private static final String RECALL_LOCATON_KEY = "WITCRecall";
/*     */   private static final int SAVE_RECALL_POINT_THRESHOLD = 60;
/*     */   
/*     */   public InfusionOtherwhere(int infusionID)
/*     */   {
/*  36 */     super(infusionID);
/*     */   }
/*     */   
/*     */   public IIcon getPowerBarIcon(EntityPlayer player, int index)
/*     */   {
/*  41 */     return net.minecraft.init.Blocks.field_150427_aO.func_149691_a(0, 0);
/*     */   }
/*     */   
/*     */   public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
/*     */   {
/*  46 */     if (world.field_72995_K) {
/*  47 */       return;
/*     */     }
/*     */     
/*  50 */     if ((otherEntity instanceof EntityLivingBase)) {
/*  51 */       EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
/*     */       
/*  53 */       if (player.func_70093_af()) {
/*  54 */         DimensionalLocation recallLocation = recallLocation(getNBT(player), "WITCRecall");
/*  55 */         if ((recallLocation != null) && (recallLocation.dimension != Config.instance().dimensionDreamID) && (recallLocation.dimension != Config.instance().dimensionTormentID) && (recallLocation.dimension != Config.instance().dimensionMirrorID) && (world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID) && (world.field_73011_w.field_76574_g != Config.instance().dimensionTormentID) && (world.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID) && (!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 4, false)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  64 */           if (((player instanceof EntityPlayerMP)) && (!isConnectionClosed((EntityPlayerMP)player))) {
/*  65 */             player.field_70143_R = 0.0F;
/*  66 */             ItemGeneral.teleportToLocation(world, recallLocation.posX, recallLocation.posY, recallLocation.posZ, recallLocation.dimension, player, true);
/*     */             
/*  68 */             otherLivingEntity.field_70143_R = 0.0F;
/*     */             
/*     */ 
/*     */ 
/*  72 */             if (!PotionEnderInhibition.isActive(otherLivingEntity, 2)) {
/*  73 */               ItemGeneral.teleportToLocation(world, recallLocation.posX, recallLocation.posY, recallLocation.posZ, recallLocation.dimension, otherLivingEntity, true);
/*     */             }
/*     */           }
/*     */         } else {
/*  77 */           world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/*     */         }
/*     */       }
/*  80 */       else if ((!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 2, true))) {
/*  81 */         double HIKE_HEIGHT = 8.0D;
/*     */         
/*  83 */         MovingObjectPosition hitMOP = raytraceUpBlocks(world, player, true, 8.0D);
/*  84 */         double hikeModified = hitMOP == null ? 8.0D : Math.min(hitMOP.field_72312_c - otherLivingEntity.field_70163_u - 2.0D, 8.0D);
/*     */         
/*  86 */         MovingObjectPosition hitMOP2 = raytraceUpBlocks(world, otherLivingEntity, true, 8.0D);
/*  87 */         double hikeModified2 = hitMOP2 == null ? 8.0D : Math.min(hitMOP2.field_72312_c - otherLivingEntity.field_70163_u - 2.0D, 8.0D);
/*     */         
/*  89 */         if (((player instanceof EntityPlayerMP)) && (!isConnectionClosed((EntityPlayerMP)player)) && 
/*  90 */           (hikeModified > 0.0D) && (hikeModified2 > 0.0D)) {
/*  91 */           ItemGeneral.teleportToLocation(world, player.field_70165_t, player.field_70163_u + hikeModified, player.field_70161_v, player.field_71093_bK, player, true);
/*  92 */           if (!PotionEnderInhibition.isActive(otherLivingEntity, 2)) {
/*  93 */             ItemGeneral.teleportToLocation(world, otherLivingEntity.field_70165_t, otherLivingEntity.field_70163_u + hikeModified2, otherLivingEntity.field_70161_v, otherLivingEntity.field_71093_bK, otherLivingEntity, true);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 104 */     int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/* 105 */     if ((player.func_70093_af()) && (elapsedTicks == 60)) {
/* 106 */       if (!world.field_72995_K) {
/* 107 */         ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.infuse.cansetrecall", new Object[0]);
/*     */       }
/* 109 */       player.field_70170_p.func_72956_a(player, "note.pling", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/* 110 */     } else if ((!player.func_70093_af()) && (elapsedTicks > 0) && (elapsedTicks % 20 == 0)) {
/* 111 */       int MAX_TELEPORT_DISTANCE = 40 + 20 * (elapsedTicks / 20);
/* 112 */       MovingObjectPosition hitMOP = doCustomRayTrace(world, player, true, MAX_TELEPORT_DISTANCE);
/* 113 */       if (hitMOP != null) {
/* 114 */         player.field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/* 115 */         if (!world.field_72995_K) {
/* 116 */           ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.infuse.canteleport", new Object[0]);
/*     */         }
/*     */       } else {
/* 119 */         player.field_70170_p.func_72956_a(player, "random.pop", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 128 */     if (world.field_72995_K) {
/* 129 */       return;
/*     */     }
/*     */     
/* 132 */     int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/*     */     
/* 134 */     if ((player.func_70093_af()) && (elapsedTicks >= 60)) {
/* 135 */       storeLocation(getNBT(player), "WITCRecall", player);
/* 136 */       SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
/* 137 */     } else if (player.func_70093_af()) {
/* 138 */       DimensionalLocation recallLocation = recallLocation(getNBT(player), "WITCRecall");
/* 139 */       if ((recallLocation != null) && (recallLocation.dimension != Config.instance().dimensionDreamID) && (recallLocation.dimension != Config.instance().dimensionTormentID) && (recallLocation.dimension != Config.instance().dimensionMirrorID) && (world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID) && (world.field_73011_w.field_76574_g != Config.instance().dimensionTormentID) && (world.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID) && (!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 2, false)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 148 */         if (((player instanceof EntityPlayerMP)) && (!isConnectionClosed((EntityPlayerMP)player))) {
/* 149 */           player.field_70143_R = 0.0F;
/* 150 */           ItemGeneral.teleportToLocation(world, recallLocation.posX, recallLocation.posY, recallLocation.posZ, recallLocation.dimension, player, true);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 156 */           Infusion.setCooldown(world, itemstack, 1500);
/*     */         }
/*     */       } else {
/* 159 */         world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/*     */       }
/*     */     }
/*     */     else {
/* 163 */       int MAX_TELEPORT_DISTANCE = 40 + 20 * (elapsedTicks / 20);
/* 164 */       MovingObjectPosition hitMOP = doCustomRayTrace(world, player, true, MAX_TELEPORT_DISTANCE);
/*     */       
/* 166 */       if ((hitMOP != null) && (!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 1, false))) {
/* 167 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 0.5D, 2.0D, 16);
/*     */         
/* 169 */         teleportEntity(player, hitMOP);
/*     */         
/* 171 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 0.5D, 2.0D, 16);
/*     */         
/* 173 */         Infusion.setCooldown(world, itemstack, 1500);
/*     */       } else {
/* 175 */         world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
/* 176 */         if ((hitMOP == null) && (!world.field_72995_K)) {
/* 177 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.cannotteleport", new Object[0]);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void storeLocation(NBTTagCompound nbt, String key, EntityPlayer player) {
/* 184 */     DimensionalLocation location = new DimensionalLocation(player);
/* 185 */     location.saveToNBT(nbt, key);
/*     */     
/* 187 */     if (!player.field_70170_p.field_72995_K) {
/* 188 */       ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.infuse.setrecall", new Object[] { player.field_70170_p.field_73011_w.func_80007_l(), Integer.valueOf(MathHelper.func_76128_c(location.posX)).toString(), Integer.valueOf(MathHelper.func_76128_c(location.posY)).toString(), Integer.valueOf(MathHelper.func_76128_c(location.posZ)).toString() });
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private DimensionalLocation recallLocation(NBTTagCompound nbtTag, String key)
/*     */   {
/* 197 */     DimensionalLocation location = new DimensionalLocation(nbtTag, key);
/* 198 */     if (!location.isValid) {
/* 199 */       return null;
/*     */     }
/*     */     
/* 202 */     return location;
/*     */   }
/*     */   
/*     */   public static void teleportEntity(EntityPlayer entityPlayer, MovingObjectPosition hitMOP) {
/* 206 */     if ((hitMOP != null) && ((entityPlayer instanceof EntityPlayerMP))) {
/* 207 */       EntityPlayerMP player = (EntityPlayerMP)entityPlayer;
/* 208 */       if (!isConnectionClosed(player)) {
/* 209 */         switch (hitMOP.field_72313_a) {
/*     */         case ENTITY: 
/* 211 */           player.func_70634_a(hitMOP.field_72307_f.field_72450_a, hitMOP.field_72307_f.field_72448_b, hitMOP.field_72307_f.field_72449_c);
/* 212 */           break;
/*     */         case BLOCK: 
/* 214 */           double hitx = hitMOP.field_72307_f.field_72450_a;
/* 215 */           double hity = hitMOP.field_72307_f.field_72448_b;
/* 216 */           double hitz = hitMOP.field_72307_f.field_72449_c;
/* 217 */           switch (hitMOP.field_72310_e) {
/*     */           case 0: 
/* 219 */             hity -= 2.0D;
/* 220 */             break;
/*     */           case 1: 
/*     */             break;
/*     */           
/*     */           case 2: 
/* 225 */             hitz -= 0.5D;
/* 226 */             break;
/*     */           case 3: 
/* 228 */             hitz += 0.5D;
/* 229 */             break;
/*     */           case 4: 
/* 231 */             hitx -= 0.5D;
/* 232 */             break;
/*     */           case 5: 
/* 234 */             hitx += 0.5D;
/*     */           }
/*     */           
/*     */           
/* 238 */           player.field_70143_R = 0.0F;
/* 239 */           player.func_70634_a(hitx, hity, hitz);
/* 240 */           break;
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static MovingObjectPosition doCustomRayTrace(World world, EntityPlayer player, boolean collisionFlag, double reachDistance)
/*     */   {
/* 250 */     MovingObjectPosition pickedBlock = raytraceBlocks(world, player, collisionFlag, reachDistance);
/* 251 */     MovingObjectPosition pickedEntity = raytraceEntities(world, player, collisionFlag, reachDistance);
/*     */     
/* 253 */     if (pickedBlock == null)
/* 254 */       return pickedEntity;
/* 255 */     if (pickedEntity == null) {
/* 256 */       return pickedBlock;
/*     */     }
/* 258 */     Vec3 playerPosition = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/* 259 */     double dBlock = pickedBlock.field_72307_f.func_72438_d(playerPosition);
/* 260 */     double dEntity = pickedEntity.field_72307_f.func_72438_d(playerPosition);
/* 261 */     if (dEntity < dBlock) {
/* 262 */       return pickedEntity;
/*     */     }
/* 264 */     return pickedBlock;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static MovingObjectPosition raytraceEntities(World world, EntityPlayer player, boolean collisionFlag, double reachDistance)
/*     */   {
/* 271 */     MovingObjectPosition pickedEntity = null;
/* 272 */     Vec3 playerPosition = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/* 273 */     Vec3 playerLook = player.func_70040_Z();
/*     */     
/* 275 */     Vec3 playerViewOffset = Vec3.func_72443_a(playerPosition.field_72450_a + playerLook.field_72450_a * reachDistance, playerPosition.field_72448_b + playerLook.field_72448_b * reachDistance, playerPosition.field_72449_c + playerLook.field_72449_c * reachDistance);
/*     */     
/*     */ 
/* 278 */     double playerBorder = 1.1D * reachDistance;
/* 279 */     AxisAlignedBB boxToScan = player.field_70121_D.func_72314_b(playerBorder, playerBorder, playerBorder);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 285 */     List entitiesHit = world.func_72839_b(player, boxToScan);
/* 286 */     double closestEntity = reachDistance;
/*     */     
/* 288 */     if ((entitiesHit == null) || (entitiesHit.isEmpty())) {
/* 289 */       return null;
/*     */     }
/*     */     
/* 292 */     for (Entity entityHit : entitiesHit) {
/* 293 */       if ((entityHit != null) && (entityHit.func_70067_L()) && (entityHit.field_70121_D != null)) {
/* 294 */         float border = entityHit.func_70111_Y();
/* 295 */         AxisAlignedBB aabb = entityHit.field_70121_D.func_72314_b(border, border, border);
/* 296 */         MovingObjectPosition hitMOP = aabb.func_72327_a(playerPosition, playerViewOffset);
/*     */         
/* 298 */         if (hitMOP != null) {
/* 299 */           if (aabb.func_72318_a(playerPosition)) {
/* 300 */             if ((0.0D < closestEntity) || (closestEntity == 0.0D)) {
/* 301 */               pickedEntity = new MovingObjectPosition(entityHit);
/* 302 */               if (pickedEntity != null) {
/* 303 */                 pickedEntity.field_72307_f = hitMOP.field_72307_f;
/* 304 */                 closestEntity = 0.0D;
/*     */               }
/*     */             }
/*     */           } else {
/* 308 */             double distance = playerPosition.func_72438_d(hitMOP.field_72307_f);
/*     */             
/* 310 */             if ((distance < closestEntity) || (closestEntity == 0.0D)) {
/* 311 */               pickedEntity = new MovingObjectPosition(entityHit);
/* 312 */               pickedEntity.field_72307_f = hitMOP.field_72307_f;
/* 313 */               closestEntity = distance;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 319 */     return pickedEntity;
/*     */   }
/*     */   
/*     */   private static boolean isConnectionClosed(EntityPlayerMP player) {
/* 323 */     return !player.field_71135_a.field_147371_a.func_150724_d();
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition raytraceBlocks(World world, EntityPlayer player, boolean collisionFlag, double reachDistance) {
/* 327 */     Vec3 playerPosition = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/* 328 */     Vec3 playerLook = player.func_70040_Z();
/*     */     
/* 330 */     Vec3 playerViewOffset = Vec3.func_72443_a(playerPosition.field_72450_a + playerLook.field_72450_a * reachDistance, playerPosition.field_72448_b + playerLook.field_72448_b * reachDistance, playerPosition.field_72449_c + playerLook.field_72449_c * reachDistance);
/*     */     
/*     */ 
/* 333 */     return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
/*     */   }
/*     */   
/*     */   private static MovingObjectPosition raytraceUpBlocks(World world, EntityLivingBase player, boolean collisionFlag, double reachDistance) {
/* 337 */     Vec3 playerPosition = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/* 338 */     Vec3 playerUp = Vec3.func_72443_a(0.0D, 1.0D, 0.0D);
/*     */     
/* 340 */     Vec3 playerViewOffset = Vec3.func_72443_a(playerPosition.field_72450_a + playerUp.field_72450_a * reachDistance, playerPosition.field_72448_b + playerUp.field_72448_b * reachDistance, playerPosition.field_72449_c + playerUp.field_72449_c * reachDistance);
/*     */     
/* 342 */     return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/InfusionOtherwhere.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */