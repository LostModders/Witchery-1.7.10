/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.ExtendedVillager;
/*     */ import com.emoniph.witchery.entity.EntityFollower;
/*     */ import com.emoniph.witchery.entity.EntityVampire;
/*     */ import com.emoniph.witchery.entity.EntityVillageGuard;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.player.EntityInteractEvent;
/*     */ 
/*     */ public class ItemGlassGoblet extends ItemBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon iconFull;
/*     */   
/*     */   public ItemGlassGoblet()
/*     */   {
/*  52 */     func_77625_d(1);
/*  53 */     func_77656_e(0);
/*  54 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  59 */     if (!world.field_72995_K) {
/*  60 */       ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  61 */       if (!hasBlood(stack)) {
/*  62 */         if (playerEx.getVampireLevel() >= 9) {
/*  63 */           if (playerEx.decreaseBloodPower(125, true)) {
/*  64 */             setBloodOwner(stack, player);
/*  65 */             ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, player.field_70165_t, player.field_70163_u + player.field_70131_O * 0.85F, player.field_70161_v, 0.8D, 0.3D, 16);
/*     */           }
/*     */           else {
/*  68 */             ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.notenoughblood", new Object[0]);
/*     */             
/*  70 */             SoundEffect.NOTE_SNARE.playOnlyTo(player);
/*     */           }
/*  72 */         } else if (playerEx.isVampire()) {
/*  73 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.nothighenoughlevel", new Object[0]);
/*     */           
/*  75 */           SoundEffect.NOTE_SNARE.playOnlyTo(player);
/*     */         } else {
/*  77 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.nothinghappens", new Object[0]);
/*     */           
/*  79 */           SoundEffect.NOTE_SNARE.playOnlyTo(player);
/*     */         }
/*     */       } else {
/*  82 */         world.func_72956_a(player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*  83 */         if ((!playerEx.isVampire()) && (!hasBloodType(stack, BloodSource.CHICKEN))) {
/*  84 */           if ((!Config.instance().allowVampireWolfHybrids) && (playerEx.getWerewolfLevel() > 0)) {
/*  85 */             ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.wolfcurse.hybridsnotallow", new Object[0]);
/*     */             
/*  87 */             return stack;
/*     */           }
/*  89 */           if (playerEx.getBloodPower() == 0) {
/*  90 */             playerEx.setVampireLevel(1);
/*  91 */             ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.8D, 1.5D, 16);
/*  92 */           } else if (CreatureUtil.isInSunlight(player)) {
/*  93 */             player.func_70015_d(5);
/*     */           } else {
/*  95 */             player.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, TimeUtil.secsToTicks(30)));
/*  96 */             player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, TimeUtil.secsToTicks(30), 1));
/*     */           }
/*     */         }
/*  99 */         setBloodOwner(stack, BloodSource.EMPTY);
/*     */       }
/*     */     }
/* 102 */     return stack;
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack stack)
/*     */   {
/* 107 */     if (hasBlood(stack)) {
/* 108 */       return ("" + net.minecraft.util.StatCollector.func_74838_a(new StringBuilder().append(func_77657_g(stack)).append(".full").toString())).trim();
/*     */     }
/* 110 */     return super.func_77653_i(stack);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
/*     */   {
/* 116 */     super.func_77624_a(stack, player, list, advTooltips);
/* 117 */     if (hasBlood(stack)) {
/* 118 */       list.add(String.format(Witchery.resource(func_77657_g(stack) + ".tip"), new Object[] { getBloodName(stack) }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77626_a(ItemStack stack)
/*     */   {
/* 125 */     return 32;
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack stack)
/*     */   {
/* 130 */     return hasBlood(stack) ? EnumAction.drink : EnumAction.block;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 136 */     MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.raytraceBlocks(world, player, true, 2.0D);
/* 137 */     if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && 
/* 138 */       (world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Blocks.field_150465_bP)) {
/* 139 */       TileEntitySkull skull = (TileEntitySkull)BlockUtil.getTileEntity(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, TileEntitySkull.class);
/*     */       
/* 141 */       if ((!world.field_72995_K) && 
/* 142 */         (skull != null) && (skull.func_145904_a() == 0)) {
/* 143 */         if ((hasBloodType(stack, BloodSource.CHICKEN)) && (world.field_73011_w.field_76574_g == 0) && (isRitual(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) && (world.func_72937_j(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) && (!world.func_72935_r()) && (Config.instance().allowVampireRitual) && (!isElleNear(world, mop.field_72311_b, mop.field_72312_c - 1, mop.field_72309_d, 32.0D)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 151 */           setBloodOwner(stack, BloodSource.EMPTY);
/* 152 */           EntityLightningBolt bolt = new EntityLightningBolt(world, 0.5D + mop.field_72311_b, mop.field_72312_c + 0.05D, 0.5D + mop.field_72309_d);
/*     */           
/* 154 */           world.func_147468_f(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 155 */           world.func_72942_c(bolt);
/* 156 */           EntityFollower follower = new EntityFollower(world);
/* 157 */           follower.setFollowerType(0);
/* 158 */           follower.func_110163_bv();
/* 159 */           follower.func_70080_a(0.5D + mop.field_72311_b, mop.field_72312_c + 1.05D, 0.5D + mop.field_72309_d, 0.0F, 0.0F);
/*     */           
/* 161 */           follower.setOwner(player);
/* 162 */           ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_MOB_LILITH_TALK, world, 0.5D + mop.field_72311_b, mop.field_72312_c + 1.05D, 0.5D + mop.field_72309_d, 1.0D, 2.0D, 16);
/*     */           
/* 164 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.lilithquest", new Object[0]);
/*     */           
/* 166 */           world.func_72838_d(follower);
/*     */ 
/*     */         }
/* 169 */         else if (!world.field_72995_K) {
/* 170 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.seemswrong", new Object[0]);
/*     */           
/* 172 */           SoundEffect.NOTE_SNARE.playOnlyTo(player);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 177 */       return stack;
/*     */     }
/*     */     
/*     */ 
/* 181 */     player.func_71008_a(stack, func_77626_a(stack));
/* 182 */     return stack;
/*     */   }
/*     */   
/*     */   private boolean isElleNear(World world, double x, double y, double z, double range) {
/* 186 */     double r = range;
/* 187 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(0.5D + x - r, y - r, 0.5D + z - r, 0.5D + x + r, y + r, 0.5D + z + r);
/*     */     
/* 189 */     List<EntityFollower> followers = world.func_72872_a(EntityFollower.class, bb);
/* 190 */     if ((followers != null) && (followers.size() > 0)) {
/* 191 */       return true;
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isRitual(World world, int x, int y, int z) {
/* 197 */     if (world.func_147439_a(x, y, z) != Blocks.field_150465_bP) {
/* 198 */       return false;
/*     */     }
/* 200 */     TileEntitySkull skull = (TileEntitySkull)BlockUtil.getTileEntity(world, x, y, z, TileEntitySkull.class);
/* 201 */     if ((skull == null) || (skull.func_145904_a() != 0)) {
/* 202 */       return false;
/*     */     }
/*     */     
/* 205 */     Block string = Blocks.field_150473_bD;
/* 206 */     boolean b = true;
/* 207 */     b &= world.func_147439_a(x, y, z - 3) == string;
/* 208 */     b &= world.func_147439_a(x + 1, y, z - 3) == string;
/* 209 */     b &= world.func_147439_a(x + 2, y, z - 3) == string;
/* 210 */     b &= world.func_147439_a(x + 2, y, z - 2) == string;
/* 211 */     b &= world.func_147439_a(x + 3, y, z - 2) == string;
/* 212 */     b &= world.func_147439_a(x + 3, y, z - 1) == string;
/* 213 */     b &= world.func_147439_a(x + 3, y, z) == string;
/* 214 */     b &= world.func_147439_a(x + 3, y, z + 1) == string;
/* 215 */     b &= world.func_147439_a(x + 3, y, z + 2) == string;
/* 216 */     b &= world.func_147439_a(x + 2, y, z + 2) == string;
/*     */     
/* 218 */     if (!b) {
/* 219 */       return false;
/*     */     }
/*     */     
/* 222 */     b &= world.func_147439_a(x + 2, y, z + 3) == string;
/* 223 */     b &= world.func_147439_a(x + 1, y, z + 3) == string;
/* 224 */     b &= world.func_147439_a(x, y, z + 3) == string;
/* 225 */     b &= world.func_147439_a(x - 1, y, z + 3) == string;
/* 226 */     b &= world.func_147439_a(x - 2, y, z + 3) == string;
/* 227 */     b &= world.func_147439_a(x - 2, y, z + 2) == string;
/* 228 */     b &= world.func_147439_a(x - 3, y, z + 2) == string;
/* 229 */     b &= world.func_147439_a(x - 3, y, z + 1) == string;
/* 230 */     b &= world.func_147439_a(x - 3, y, z) == string;
/* 231 */     b &= world.func_147439_a(x - 3, y, z + 1) == string;
/* 232 */     b &= world.func_147439_a(x - 3, y, z + 2) == string;
/* 233 */     b &= world.func_147439_a(x - 2, y, z + 2) == string;
/* 234 */     b &= world.func_147439_a(x - 2, y, z + 3) == string;
/* 235 */     b &= world.func_147439_a(x - 1, y, z + 3) == string;
/*     */     
/* 237 */     if (!b) {
/* 238 */       return false;
/*     */     }
/*     */     
/* 241 */     Block candle = Blocks.field_150478_aa;
/* 242 */     b &= world.func_147439_a(x - 3, y, z + 3) == candle;
/* 243 */     b &= world.func_147439_a(x - 3, y, z - 3) == candle;
/* 244 */     b &= world.func_147439_a(x + 3, y, z + 3) == candle;
/* 245 */     b &= world.func_147439_a(x + 3, y, z - 3) == candle;
/*     */     
/* 247 */     Block redstone = Blocks.field_150488_af;
/* 248 */     b &= world.func_147439_a(x - 1, y, z) == redstone;
/* 249 */     b &= world.func_147439_a(x + 1, y, z) == redstone;
/* 250 */     b &= world.func_147439_a(x, y, z + 1) == redstone;
/* 251 */     b &= world.func_147439_a(x, y, z - 1) == redstone;
/* 252 */     b &= world.func_147439_a(x - 1, y, z - 1) == redstone;
/* 253 */     b &= world.func_147439_a(x - 1, y, z + 1) == redstone;
/* 254 */     b &= world.func_147439_a(x + 1, y, z - 1) == redstone;
/* 255 */     b &= world.func_147439_a(x + 1, y, z + 1) == redstone;
/*     */     
/* 257 */     for (int dx = x - 3; dx <= x + 3; dx++) {
/* 258 */       for (int dz = z - 3; dz <= z + 3; dz++) {
/* 259 */         if (!world.func_147439_a(dx, y - 1, dz).func_149721_r()) {
/* 260 */           return false;
/*     */         }
/*     */         
/* 263 */         if (!world.func_147437_c(dx, y + 1, dz)) {
/* 264 */           return false;
/*     */         }
/*     */         
/* 267 */         if (!world.func_147437_c(dx, y + 2, dz)) {
/* 268 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 273 */     return b;
/*     */   }
/*     */   
/*     */   public void handleCreatureDeath(World world, EntityPlayer player, EntityLivingBase victim) {
/* 277 */     if (((victim instanceof net.minecraft.entity.passive.EntityChicken)) && 
/* 278 */       (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == Witchery.Items.BOLINE)) {
/* 279 */       for (int i = 0; i < 9; i++) {
/* 280 */         ItemStack stack = player.field_71071_by.func_70301_a(i);
/* 281 */         if ((stack != null) && (stack.func_77973_b() == this)) {
/* 282 */           int x = MathHelper.func_76128_c(victim.field_70165_t);
/* 283 */           int y = MathHelper.func_76128_c(victim.field_70163_u);
/* 284 */           int z = MathHelper.func_76128_c(victim.field_70161_v);
/* 285 */           for (int dx = x - 1; dx <= x + 1; dx++) {
/* 286 */             for (int dz = z - 1; dz <= z + 1; dz++) {
/* 287 */               for (int dy = y - 1; dy <= y + 1; dy++) {
/* 288 */                 if (isRitual(world, dx, dy, dz)) {
/* 289 */                   setBloodOwner(stack, BloodSource.CHICKEN);
/* 290 */                   ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, victim.field_70165_t, victim.field_70163_u + victim.field_70131_O * 0.85F, victim.field_70161_v, 0.5D, 0.5D, 16);
/*     */                   
/*     */ 
/* 293 */                   return;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 298 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void onEntityInteract(World world, EntityPlayer player, ItemStack stack, EntityInteractEvent event)
/*     */   {
/* 306 */     if ((!event.entityPlayer.field_70170_p.field_72995_K) && (hasBlood(stack)) && (!CreatureUtil.isWerewolf(event.target, true)) && (!CreatureUtil.isVampire(event.target)))
/*     */     {
/* 308 */       boolean success = false;
/* 309 */       if ((event.target instanceof EntityVillager)) {
/* 310 */         EntityVillager entity = (EntityVillager)event.target;
/* 311 */         if (tryConvertToVampire(entity, ExtendedVillager.get(entity).getBlood(), player, stack)) {
/* 312 */           success = true;
/*     */         }
/* 314 */       } else if ((event.target instanceof EntityVillageGuard)) {
/* 315 */         EntityVillageGuard entity = (EntityVillageGuard)event.target;
/* 316 */         if (tryConvertToVampire(entity, entity.getBlood(), player, stack)) {
/* 317 */           success = true;
/*     */         }
/*     */       }
/*     */       
/* 321 */       if (success) {
/* 322 */         ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, event.target.field_70165_t, event.target.field_70163_u, event.target.field_70161_v, event.target.field_70130_N, event.target.field_70131_O, 16);
/*     */         
/* 324 */         setBloodOwner(stack, BloodSource.EMPTY);
/*     */       } else {
/* 326 */         SoundEffect.NOTE_SNARE.playOnlyTo(player);
/*     */       }
/* 328 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean tryConvertToVampire(EntityLiving target, int blood, EntityPlayer player, ItemStack stack) {
/* 333 */     PotionEffect effect = target.func_70660_b(Witchery.Potions.PARALYSED);
/* 334 */     if ((effect != null) && (effect.func_76458_c() >= 5)) {
/* 335 */       if (blood == 0) {
/* 336 */         if (isCoffinNear(player.field_70170_p, target, 4)) {
/* 337 */           convertToVampire(target);
/* 338 */           ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 339 */           if ((playerEx.getVampireLevel() == 9) && (playerEx.canIncreaseVampireLevel()) && 
/* 340 */             (getBloodOwner(stack, player.field_70170_p) == player)) {
/* 341 */             playerEx.increaseVampireLevel();
/*     */           }
/*     */           
/* 344 */           return true;
/*     */         }
/* 346 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.nocoffinnear", new Object[0]);
/*     */       }
/*     */       else
/*     */       {
/* 350 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.targetnotdrained", new Object[0]);
/*     */       }
/*     */     }
/*     */     else {
/* 354 */       ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:glassgoblet.targetnottransfixed", new Object[0]);
/*     */     }
/*     */     
/* 357 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isCoffinNear(World world, Entity entity, int radius) {
/* 361 */     int x = MathHelper.func_76128_c(entity.field_70165_t);
/* 362 */     int y = MathHelper.func_76128_c(entity.field_70163_u);
/* 363 */     int z = MathHelper.func_76128_c(entity.field_70161_v);
/* 364 */     for (int dx = x - radius; dx <= x + radius; dx++) {
/* 365 */       for (int dz = z - radius; dz <= z + radius; dz++) {
/* 366 */         for (int dy = y - radius; dy <= y + radius; dy++) {
/* 367 */           if (world.func_147439_a(dx, dy, dz) == Witchery.Blocks.COFFIN) {
/* 368 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 373 */     return false;
/*     */   }
/*     */   
/*     */   private void convertToVampire(EntityLiving entity) {
/* 377 */     EntityVampire vampire = new EntityVampire(entity.field_70170_p);
/* 378 */     vampire.func_110163_bv();
/* 379 */     vampire.func_82149_j(entity);
/* 380 */     vampire.func_110161_a(null);
/* 381 */     entity.field_70170_p.func_72900_e(entity);
/* 382 */     entity.field_70170_p.func_72838_d(vampire);
/* 383 */     entity.field_70170_p.func_72889_a(null, 1017, (int)vampire.field_70165_t, (int)vampire.field_70163_u, (int)vampire.field_70161_v, 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_77617_a(int meta)
/*     */   {
/* 389 */     if (meta == 0) {
/* 390 */       return super.func_77617_a(meta);
/*     */     }
/* 392 */     return this.iconFull;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/* 402 */     super.func_94581_a(iconRegister);
/* 403 */     this.iconFull = iconRegister.func_94245_a(func_111208_A() + "full");
/*     */   }
/*     */   
/*     */   private boolean hasBlood(ItemStack stack) {
/* 407 */     return stack.func_77960_j() == 1;
/*     */   }
/*     */   
/*     */   private boolean hasBloodType(ItemStack stack, BloodSource source) {
/* 411 */     return (stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("WITCBloodUUID")) && (stack.func_77978_p().func_74779_i("WITCBloodUUID").equals(source.KEY));
/*     */   }
/*     */   
/*     */   private EntityPlayer getBloodOwner(ItemStack stack, World world)
/*     */   {
/* 416 */     if (stack.func_77942_o()) {
/* 417 */       String s = stack.func_77978_p().func_74779_i("WITCBloodUUID");
/* 418 */       if ((s != null) && (!s.isEmpty())) {
/* 419 */         if (BloodSource.isOneOf(s)) {
/* 420 */           return null;
/*     */         }
/* 422 */         UUID uuid = UUID.fromString(s);
/* 423 */         return uuid != null ? world.func_152378_a(uuid) : null;
/*     */       }
/*     */     }
/*     */     
/* 427 */     return null;
/*     */   }
/*     */   
/*     */   private String getBloodName(ItemStack stack) {
/* 431 */     if (stack.func_77942_o()) {
/* 432 */       return stack.func_77978_p().func_74779_i("WITCBloodName");
/*     */     }
/* 434 */     return "";
/*     */   }
/*     */   
/*     */   public void setBloodOwner(ItemStack stack, EntityPlayer player) {
/* 438 */     if (!stack.func_77942_o()) {
/* 439 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/* 441 */     if (player != null) {
/* 442 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 443 */       nbtRoot.func_74778_a("WITCBloodUUID", player.func_146103_bH().getId().toString());
/* 444 */       nbtRoot.func_74778_a("WITCBloodName", player.func_146103_bH().getName());
/* 445 */       stack.func_77964_b(1);
/*     */     } else {
/* 447 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 448 */       nbtRoot.func_82580_o("WITCBloodUUID");
/* 449 */       nbtRoot.func_82580_o("WITCBloodName");
/* 450 */       stack.func_77964_b(0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBloodOwner(ItemStack stack, BloodSource source) {
/* 455 */     if (!stack.func_77942_o()) {
/* 456 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/* 458 */     if (source == BloodSource.EMPTY) {
/* 459 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 460 */       nbtRoot.func_82580_o("WITCBloodUUID");
/* 461 */       nbtRoot.func_82580_o("WITCBloodName");
/* 462 */       stack.func_77964_b(0);
/*     */     } else {
/* 464 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 465 */       nbtRoot.func_74778_a("WITCBloodUUID", source.KEY);
/* 466 */       nbtRoot.func_74778_a("WITCBloodName", source.DISPLAY_NAME);
/* 467 */       stack.func_77964_b(1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static enum BloodSource {
/* 472 */     EMPTY("", ""),  CHICKEN("__chicken", "item.witchery:glassgoblet.chicken"),  LILITH("__lilith", "item.witchery:glassgoblet.lilith");
/*     */     
/*     */     public final String KEY;
/*     */     public final String DISPLAY_NAME;
/*     */     
/*     */     private BloodSource(String nbtKey, String resourceKey)
/*     */     {
/* 479 */       this.KEY = nbtKey;
/* 480 */       this.DISPLAY_NAME = Witchery.resource(resourceKey);
/*     */     }
/*     */     
/*     */     public static boolean isOneOf(String key) {
/* 484 */       return (CHICKEN.KEY.equals(key)) || (LILITH.KEY.equals(key));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemGlassGoblet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */