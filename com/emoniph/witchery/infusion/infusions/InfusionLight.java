/*     */ package com.emoniph.witchery.infusion.infusions;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.blocks.BlockBarrier;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.BlockSide;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class InfusionLight extends Infusion
/*     */ {
/*     */   private static final int BARRIER_RADIUS = 2;
/*     */   private static final int BARRIER_HEIGHT = 3;
/*     */   private static final boolean BARRIER_BLOCKS_PLAYERS = true;
/*     */   private static final int AGGRO_DROP_RADIUS = 20;
/*     */   protected static final int BARRIER_TICKS_TO_LIVE_ = 200;
/*     */   
/*     */   public InfusionLight(int infusionID)
/*     */   {
/*  37 */     super(infusionID);
/*     */   }
/*     */   
/*     */   public IIcon getPowerBarIcon(EntityPlayer player, int index)
/*     */   {
/*  42 */     return Blocks.field_150433_aE.func_149691_a(0, 0);
/*     */   }
/*     */   
/*     */   public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
/*     */   {
/*  47 */     if (world.field_72995_K) {
/*  48 */       return;
/*     */     }
/*     */     
/*  51 */     if ((otherEntity instanceof EntityLivingBase)) {
/*  52 */       EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
/*  53 */       int UPSHIFT = 4;
/*  54 */       int posX = (int)otherEntity.field_70165_t;
/*  55 */       int posY = (int)otherEntity.field_70163_u + 4;
/*  56 */       int posZ = (int)otherEntity.field_70161_v;
/*     */       
/*  58 */       if ((world.func_147437_c(posX, posY, posZ)) && (world.func_147437_c(posX, posY + 1, posZ)) && (world.func_147437_c(posX, posY + 2, posZ)) && (world.func_147437_c(posX + 1, posY, posZ)) && (world.func_147437_c(posX + 1, posY + 1, posZ)) && (world.func_147437_c(posX + 1, posY + 2, posZ)) && (world.func_147437_c(posX, posY, posZ + 1)) && (world.func_147437_c(posX, posY + 1, posZ + 1)) && (world.func_147437_c(posX, posY + 2, posZ + 1)) && (world.func_147437_c(posX - 1, posY, posZ)) && (world.func_147437_c(posX - 1, posY + 1, posZ)) && (world.func_147437_c(posX - 1, posY + 2, posZ)) && (world.func_147437_c(posX, posY, posZ - 1)) && (world.func_147437_c(posX, posY + 1, posZ - 1)) && (world.func_147437_c(posX, posY + 2, posZ - 1)) && (consumeCharges(world, player, 5, true)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  68 */         drawFilledCircle(world, posX, posZ, posY - 1, 2, null);
/*     */         
/*  70 */         for (int y = posY; y < posY + 3; y++) {
/*  71 */           drawCircle(world, posX, posZ, y, 2, null);
/*     */         }
/*     */         
/*  74 */         drawFilledCircle(world, posX, posZ, posY + 3, 2, null);
/*  75 */         otherLivingEntity.func_70634_a(posX, posY, posZ);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/*  82 */     if (world.field_72995_K) {
/*  83 */       if (!player.func_70115_ae()) {
/*  84 */         int var5 = MathHelper.func_76128_c(player.field_70165_t);
/*  85 */         int var6 = MathHelper.func_76128_c(player.field_70163_u - 2.0D);
/*  86 */         int var7 = MathHelper.func_76128_c(player.field_70161_v);
/*  87 */         if (world.func_147439_a(var5, var6, var7) != Blocks.field_150432_aD) {
/*  88 */           if (player.field_70122_E) {
/*  89 */             if (!player.func_70090_H()) {
/*  90 */               player.field_70159_w *= 1.6500000476837158D;
/*  91 */               player.field_70179_y *= 1.6500000476837158D;
/*     */             } else {
/*  93 */               player.field_70159_w *= 1.100000023841858D;
/*  94 */               player.field_70179_y *= 1.100000023841858D;
/*     */             }
/*     */           }
/*     */         } else {
/*  98 */           player.field_70159_w *= 1.100000023841858D;
/*  99 */           player.field_70179_y *= 1.100000023841858D;
/*     */         }
/*     */       }
/* 102 */       return;
/*     */     }
/*     */     
/* 105 */     int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/* 106 */     if ((elapsedTicks % 30 == 0) && (elapsedTicks > 19)) {
/* 107 */       if (consumeCharges(world, player, 1, true)) {
/* 108 */         bendLightAroundPlayer(world, player, true);
/*     */       } else {
/* 110 */         bendLightAroundPlayer(world, player, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void bendLightAroundPlayer(World world, EntityPlayer player, boolean active) {
/* 116 */     if (active) {
/* 117 */       player.func_70690_d(new PotionEffect(Potion.field_76441_p.field_76415_H, 30, 0, true));
/*     */       
/*     */ 
/*     */ 
/* 121 */       int r = 20;
/* 122 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 20.0D, player.field_70163_u, player.field_70161_v - 20.0D, player.field_70165_t + 20.0D, player.field_70163_u + 2.0D, player.field_70161_v + 20.0D);
/*     */       
/* 124 */       for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
/* 125 */         EntityLiving entity = (EntityLiving)obj;
/* 126 */         if ((entity.func_70638_az() == player) && (Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, player.field_70165_t, player.field_70163_u, player.field_70161_v) <= 20.0D)) {
/* 127 */           EntityUtil.dropAttackTarget(entity);
/*     */         }
/*     */       }
/*     */     } else {
/* 131 */       player.func_82170_o(Potion.field_76441_p.field_76415_H);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 140 */     if (world.field_72995_K) {
/* 141 */       return;
/*     */     }
/*     */     
/* 144 */     bendLightAroundPlayer(world, player, false);
/*     */     
/* 146 */     int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
/* 147 */     if ((elapsedTicks < 20) && (player.func_70093_af()))
/*     */     {
/* 149 */       MovingObjectPosition hitMOP = InfusionOtherwhere.doCustomRayTrace(world, player, true, 16.0D);
/* 150 */       if (hitMOP != null) {
/* 151 */         switch (hitMOP.field_72313_a) {
/*     */         case ENTITY: 
/* 153 */           if ((hitMOP.field_72308_g instanceof EntityLivingBase)) {
/* 154 */             EntityLivingBase otherLivingEntity = (EntityLivingBase)hitMOP.field_72308_g;
/* 155 */             if (consumeCharges(world, player, 3, true))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/* 160 */               int posX = (int)otherLivingEntity.field_70165_t;
/* 161 */               int posY = (int)otherLivingEntity.field_70163_u;
/* 162 */               int posZ = (int)otherLivingEntity.field_70161_v;
/* 163 */               drawFilledCircle(world, posX, posZ, posY - 1, 1, player);
/*     */               
/* 165 */               for (int y = posY; y < posY + 3; y++) {
/* 166 */                 drawCircle(world, posX, posZ, y, 2, player);
/*     */               }
/*     */               
/* 169 */               drawFilledCircle(world, posX, posZ, posY + 3, 2, player);
/*     */             } }
/* 171 */           break;
/*     */         
/*     */         case BLOCK: 
/* 174 */           if ((BlockSide.TOP.isEqual(hitMOP.field_72310_e)) && (consumeCharges(world, player, 3, true))) {
/* 175 */             placeBarrierShield(world, player, hitMOP);
/* 176 */           } else if ((!BlockSide.TOP.isEqual(hitMOP.field_72310_e)) && (!BlockSide.TOP.isEqual(hitMOP.field_72310_e)) && (consumeCharges(world, player, 3, true)))
/*     */           {
/* 178 */             int b0 = 0;
/*     */             
/* 180 */             switch (hitMOP.field_72310_e) {
/*     */             case 0: 
/*     */             case 1: 
/* 183 */               b0 = 0;
/* 184 */               break;
/*     */             case 2: 
/*     */             case 3: 
/* 187 */               b0 = 8;
/* 188 */               break;
/*     */             case 4: 
/*     */             case 5: 
/* 191 */               b0 = 4;
/*     */             }
/*     */             
/* 194 */             int dx = hitMOP.field_72310_e == 4 ? -1 : hitMOP.field_72310_e == 5 ? 1 : 0;
/* 195 */             int dy = hitMOP.field_72310_e == 1 ? 1 : hitMOP.field_72310_e == 0 ? -1 : 0;
/* 196 */             int dz = hitMOP.field_72310_e == 2 ? -1 : hitMOP.field_72310_e == 3 ? 1 : 0;
/*     */             
/* 198 */             int sproutExtent = 16;
/*     */             
/* 200 */             boolean isInitialBlockSolid = world.func_147439_a(hitMOP.field_72311_b, hitMOP.field_72312_c, hitMOP.field_72309_d).func_149688_o().func_76220_a();
/*     */             
/*     */ 
/* 203 */             for (int i = (hitMOP.field_72310_e == 1) && (!isInitialBlockSolid) ? 0 : 1; i < 16; i++) {
/* 204 */               int x = hitMOP.field_72311_b + i * dx;
/* 205 */               int y = hitMOP.field_72312_c + i * dy;
/* 206 */               int z = hitMOP.field_72309_d + i * dz;
/* 207 */               if (!setBlockIfNotSolid(world, x, y, z))
/*     */                 break;
/*     */             }
/*     */           }
/* 211 */           break;
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void placeBarrierShield(World world, EntityPlayer player, MovingObjectPosition hitMOP)
/*     */   {
/* 221 */     double f1 = MathHelper.func_76134_b(-player.field_70177_z * 0.017453292F - 3.1415927F);
/* 222 */     double f2 = MathHelper.func_76126_a(-player.field_70177_z * 0.017453292F - 3.1415927F);
/* 223 */     Vec3 loc = Vec3.func_72443_a(f2, 0.0D, f1);
/*     */     
/* 225 */     Material material = world.func_147439_a(hitMOP.field_72311_b, hitMOP.field_72312_c, hitMOP.field_72309_d).func_149688_o();
/* 226 */     int yPlus = 1;
/* 227 */     if ((material != null) && (!material.func_76220_a())) {
/* 228 */       yPlus = 0;
/*     */     }
/* 230 */     drawBarrierBlockColumn(world, player, hitMOP.field_72311_b, hitMOP.field_72312_c + yPlus, hitMOP.field_72309_d, 3);
/*     */     
/* 232 */     loc.func_72442_b((float)Math.toRadians(90.0D));
/* 233 */     int newX = MathHelper.func_76128_c(hitMOP.field_72311_b + 0.5D + loc.field_72450_a * 1.0D);
/* 234 */     int newZ = MathHelper.func_76128_c(hitMOP.field_72309_d + 0.5D + loc.field_72449_c * 1.0D);
/* 235 */     drawBarrierBlockColumn(world, player, newX, hitMOP.field_72312_c + yPlus, newZ, 3);
/*     */     
/* 237 */     loc.func_72442_b((float)Math.toRadians(180.0D));
/* 238 */     newX = MathHelper.func_76128_c(hitMOP.field_72311_b + 0.5D + loc.field_72450_a * 1.0D);
/* 239 */     newZ = MathHelper.func_76128_c(hitMOP.field_72309_d + 0.5D + loc.field_72449_c * 1.0D);
/* 240 */     drawBarrierBlockColumn(world, player, newX, hitMOP.field_72312_c + yPlus, newZ, 3);
/*     */   }
/*     */   
/*     */   private static boolean setBlockIfNotSolid(World world, int x, int y, int z) {
/* 244 */     if (world.func_147439_a(x, y, z).func_149688_o().func_76222_j()) {
/* 245 */       BlockBarrier.setBlock(world, x, y, z, 200, true, null);
/* 246 */       return true;
/*     */     }
/* 248 */     return false;
/*     */   }
/*     */   
/*     */   private static void drawBarrierBlockColumn(World world, EntityPlayer player, int posX, int posY, int posZ, int height)
/*     */   {
/* 253 */     for (int offsetPosY = posY; offsetPosY < posY + height; offsetPosY++) {
/* 254 */       Material material = world.func_147439_a(posX, offsetPosY, posZ).func_149688_o();
/* 255 */       Block blockID = world.func_147439_a(posX, offsetPosY, posZ);
/* 256 */       if ((material.func_76222_j()) || (blockID == Witchery.Blocks.BARRIER)) {
/* 257 */         BlockBarrier.setBlock(world, posX, offsetPosY, posZ, 200, true, player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void drawCircle(World world, int x0, int z0, int y, int radius, EntityPlayer player) {
/* 263 */     int x = radius;
/* 264 */     int z = 0;
/* 265 */     int radiusError = 1 - x;
/*     */     
/* 267 */     while (x >= z) {
/* 268 */       drawPixel(world, x + x0, z + z0, y, player);
/* 269 */       drawPixel(world, z + x0, x + z0, y, player);
/* 270 */       drawPixel(world, -x + x0, z + z0, y, player);
/* 271 */       drawPixel(world, -z + x0, x + z0, y, player);
/* 272 */       drawPixel(world, -x + x0, -z + z0, y, player);
/* 273 */       drawPixel(world, -z + x0, -x + z0, y, player);
/* 274 */       drawPixel(world, x + x0, -z + z0, y, player);
/* 275 */       drawPixel(world, z + x0, -x + z0, y, player);
/*     */       
/* 277 */       z++;
/* 278 */       if (radiusError < 0) {
/* 279 */         radiusError += 2 * z + 1;
/*     */       } else {
/* 281 */         x--;
/* 282 */         radiusError += 2 * (z - x + 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, EntityPlayer player) {
/* 288 */     int x = radius;
/* 289 */     int z = 0;
/* 290 */     int radiusError = 1 - x;
/*     */     
/* 292 */     while (x >= z) {
/* 293 */       drawLine(world, -x + x0, x + x0, z + z0, y, player);
/* 294 */       drawLine(world, -z + x0, z + x0, x + z0, y, player);
/* 295 */       drawLine(world, -x + x0, x + x0, -z + z0, y, player);
/* 296 */       drawLine(world, -z + x0, z + x0, -x + z0, y, player);
/*     */       
/* 298 */       z++;
/*     */       
/* 300 */       if (radiusError < 0) {
/* 301 */         radiusError += 2 * z + 1;
/*     */       } else {
/* 303 */         x--;
/* 304 */         radiusError += 2 * (z - x + 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void drawLine(World world, int x1, int x2, int z, int y, EntityPlayer player) {
/* 310 */     for (int x = x1; x <= x2; x++) {
/* 311 */       drawPixel(world, x, z, y, player);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void drawPixel(World world, int x, int z, int y, EntityPlayer player) {
/* 316 */     Block blockID = world.func_147439_a(x, y, z);
/* 317 */     Material material = world.func_147439_a(x, y, z).func_149688_o();
/* 318 */     if ((!material.func_76220_a()) || (blockID == Witchery.Blocks.BARRIER) || (material == Material.field_151579_a)) {
/* 319 */       BlockBarrier.setBlock(world, x, y, z, 200, true, player);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/InfusionLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */