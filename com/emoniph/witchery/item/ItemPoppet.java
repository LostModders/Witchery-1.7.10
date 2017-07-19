/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf;
/*     */ import com.emoniph.witchery.entity.EntityWitchHunter;
/*     */ import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.network.PacketPushTarget;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
/*     */ public class ItemPoppet
/*     */   extends ItemBase
/*     */ {
/*  66 */   private final ArrayList<PoppetType> poppetTypes = new ArrayList();
/*     */   
/*  68 */   public final PoppetType unboundPoppet = PoppetType.register(new PoppetType(0, "poppet", "Poppet", 0, null), this.poppetTypes);
/*  69 */   public final PoppetType earthPoppet = PoppetType.register(new PoppetType(1, "protectEarth", "Earth Protection Poppet", null).setDestroyOnUse(true), this.poppetTypes);
/*     */   
/*  71 */   public final PoppetType waterPoppet = PoppetType.register(new PoppetType(2, "protectWater", "Water Protection Poppet", null).setDestroyOnUse(true), this.poppetTypes);
/*     */   
/*  73 */   public final PoppetType firePoppet = PoppetType.register(new PoppetType(3, "protectFire", "Fire Protection Poppet", null).setDestroyOnUse(true), this.poppetTypes);
/*     */   
/*  75 */   public final PoppetType foodPoppet = PoppetType.register(new PoppetType(4, "protectStarvation", "Hunger Protection Poppet", null).setDestroyOnUse(true), this.poppetTypes);
/*     */   
/*  77 */   public final PoppetType toolPoppet = PoppetType.register(new PoppetType(5, "protectTool", "Tool Protection Poppet", null).setDestroyOnUse(true), this.poppetTypes);
/*     */   
/*  79 */   public final PoppetType deathPoppet = PoppetType.register(new PoppetType(6, "protectDeath", "Death Protection Poppet", 2, null).setDestroyOnUse(true), this.poppetTypes);
/*     */   
/*  81 */   public final PoppetType antiVoodooPoppet = PoppetType.register(new PoppetType(7, "protectVoodoo", "Voodoo Protection Poppet", null), this.poppetTypes);
/*  82 */   public final PoppetType voodooPoppet = PoppetType.register(new PoppetType(8, "voodoo", "Voodoo Poppet", null), this.poppetTypes);
/*  83 */   public final PoppetType vampiricPoppet = PoppetType.register(new PoppetType(9, "vampiric", "Vampiric Poppet", 2, null), this.poppetTypes);
/*  84 */   public final PoppetType poppetProtectionPoppet = PoppetType.register(new PoppetType(10, "protectPoppet", "Poppet Protection", 2, null), this.poppetTypes);
/*  85 */   public final PoppetType armorPoppet = PoppetType.register(new PoppetType(11, "protectArmor", "Armor Protection Poppet", null).setDestroyOnUse(true), this.poppetTypes);
/*     */   private static final String KEY_DAMAGE = "WITCDamage";
/*     */   
/*     */   public ItemPoppet() {
/*  89 */     setNoRepair();
/*  90 */     func_77625_d(1);
/*  91 */     func_77656_e(0);
/*  92 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  98 */     return EnumRarity.values()[((PoppetType)this.poppetTypes.get(itemstack.func_77960_j())).rarity];
/*     */   }
/*     */   
/*     */   public String func_77667_c(ItemStack itemStack)
/*     */   {
/* 103 */     int damage = itemStack.func_77960_j();
/* 104 */     assert ((damage >= 0) && (damage < this.poppetTypes.size())) : "damage value is too large";
/* 105 */     if ((damage >= 0) && (damage < this.poppetTypes.size())) {
/* 106 */       return ((PoppetType)this.poppetTypes.get(damage)).getUnlocalizedName();
/*     */     }
/* 108 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77653_i(ItemStack itemstack)
/*     */   {
/* 114 */     if (this.vampiricPoppet.isMatch(itemstack)) {
/* 115 */       String sourceID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(itemstack, Integer.valueOf(1));
/* 116 */       String targetID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(itemstack, Integer.valueOf(2));
/* 117 */       String localizedName = super.func_77653_i(itemstack);
/* 118 */       if ((!sourceID.isEmpty()) && (!targetID.isEmpty()))
/* 119 */         return String.format("%s (%s -> %s)", new Object[] { localizedName, sourceID, targetID });
/* 120 */       if (!sourceID.isEmpty())
/* 121 */         return String.format("%s (%s -> ??)", new Object[] { localizedName, sourceID });
/* 122 */       if (!targetID.isEmpty()) {
/* 123 */         return String.format("%s (?? -> %s)", new Object[] { localizedName, targetID });
/*     */       }
/* 125 */       return localizedName;
/*     */     }
/*     */     
/* 128 */     String entityID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(itemstack, Integer.valueOf(1));
/* 129 */     String localizedName = super.func_77653_i(itemstack);
/* 130 */     return !entityID.isEmpty() ? String.format("%s (%s)", new Object[] { localizedName, entityID }) : localizedName;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
/*     */   {
/* 136 */     if (this.vampiricPoppet.isMatch(stack)) {
/* 137 */       String sourceID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
/* 138 */       String targetID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(2));
/* 139 */       String localizedName = super.func_77653_i(stack);
/* 140 */       if ((!sourceID.isEmpty()) && (!targetID.isEmpty())) {
/* 141 */         list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { String.format("%s -> %s", new Object[] { sourceID, targetID }) }));
/* 142 */       } else if (!sourceID.isEmpty()) {
/* 143 */         list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { String.format("%s -> ??", new Object[] { sourceID }) }));
/* 144 */       } else if (!targetID.isEmpty()) {
/* 145 */         list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { String.format("?? -> %s", new Object[] { targetID }) }));
/*     */       } else {
/* 147 */         list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
/*     */       }
/*     */     } else {
/* 150 */       String entityID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
/* 151 */       String localizedName = super.func_77653_i(stack);
/* 152 */       if ((entityID != null) && (!entityID.isEmpty())) {
/* 153 */         list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { entityID }));
/*     */       } else {
/* 155 */         list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/* 162 */     for (PoppetType poppetType : this.poppetTypes) {
/* 163 */       poppetType.registerIcon(iconRegister, this);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int damage)
/*     */   {
/* 170 */     if ((damage < 0) || (damage >= this.poppetTypes.size())) {
/* 171 */       damage = 0;
/*     */     }
/* 173 */     return ((PoppetType)this.poppetTypes.get(damage)).icon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item item, CreativeTabs tab, List itemList)
/*     */   {
/* 179 */     for (PoppetType poppetType : this.poppetTypes) {
/* 180 */       itemList.add(new ItemStack(item, 1, poppetType.damageValue));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static final int MAX_DAMAGE = 1000;
/*     */   private static final float AUTO_REPAIR_THRESHOLD = 0.1F;
/*     */   public void func_77622_d(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 189 */     ensureNBT(stack);
/* 190 */     super.func_77622_d(stack, world, player);
/*     */   }
/*     */   
/*     */   public boolean func_77645_m()
/*     */   {
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isDamaged(ItemStack stack)
/*     */   {
/* 200 */     ensureNBT(stack);
/* 201 */     return getDamageNBT(stack) > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDamage(ItemStack stack, int damage) {}
/*     */   
/*     */ 
/*     */   public int getDisplayDamage(ItemStack stack)
/*     */   {
/* 211 */     ensureNBT(stack);
/* 212 */     return getDamageNBT(stack);
/*     */   }
/*     */   
/*     */   public int func_77612_l()
/*     */   {
/* 217 */     return 1000;
/*     */   }
/*     */   
/*     */   private int getDamageNBT(ItemStack stack) {
/* 221 */     ensureNBT(stack);
/* 222 */     return stack.func_77978_p().func_74762_e("WITCDamage");
/*     */   }
/*     */   
/*     */   private void setDamageNBT(IInventory inventory, ItemStack stack, int damage) {
/* 226 */     ensureNBT(stack);
/* 227 */     damage = Math.min(damage, 1000);
/* 228 */     stack.func_77978_p().func_74768_a("WITCDamage", damage);
/* 229 */     if (damage == 1000) {
/* 230 */       stack.field_77994_a = 0;
/* 231 */       if (inventory != null) {
/* 232 */         for (int i = 0; i < inventory.func_70302_i_(); i++) {
/* 233 */           if (inventory.func_70301_a(i) == stack) {
/* 234 */             inventory.func_70299_a(i, null);
/* 235 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void ensureNBT(ItemStack stack) {
/* 243 */     if (!stack.func_77942_o()) {
/* 244 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/*     */     
/* 247 */     if (!stack.func_77978_p().func_74764_b("WITCDamage")) {
/* 248 */       stack.func_77978_p().func_74768_a("WITCDamage", 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5)
/*     */   {
/* 254 */     if ((!world.field_72995_K) && (this.voodooPoppet.isMatch(stack)))
/*     */     {
/* 256 */       if ((entity.func_70055_a(Material.field_151586_h)) && (entity.func_70086_ai() <= 0)) {
/* 257 */         EntityLivingBase boundEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, entity, stack, Integer.valueOf(1));
/* 258 */         if ((boundEntity != null) && (boundEntity.func_70089_S())) {
/* 259 */           if (!voodooProtectionActivated((entity instanceof EntityPlayer) ? (EntityPlayer)entity : null, stack, boundEntity, true, false)) {
/* 260 */             if ((entity instanceof EntityPlayer)) {
/* 261 */               EntityWitchHunter.blackMagicPerformed((EntityPlayer)entity);
/*     */             }
/* 263 */             boolean damageDisabled = ((boundEntity instanceof EntityPlayer)) && (((EntityPlayer)boundEntity).field_71075_bZ.field_75102_a);
/* 264 */             if ((!ItemHunterClothes.isMagicalProtectionActive(boundEntity)) && (!boundEntity.func_70648_aU()) && (!boundEntity.func_82165_m(Potion.field_76427_o.field_76415_H)) && (!damageDisabled))
/*     */             {
/*     */ 
/*     */ 
/* 268 */               for (int i = 0; i < 8; i++) {
/* 269 */                 float f = world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat();
/* 270 */                 float f1 = world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat();
/* 271 */                 float f2 = world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat();
/* 272 */                 world.func_72869_a("bubble", boundEntity.field_70165_t + f, boundEntity.field_70163_u + f1, boundEntity.field_70161_v + f2, boundEntity.field_70159_w, boundEntity.field_70181_x, boundEntity.field_70179_y);
/*     */               }
/*     */               
/*     */ 
/* 276 */               boundEntity.func_70097_a(DamageSource.field_76369_e, 1.0F);
/*     */             }
/* 278 */             boundEntity.func_70066_B();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 284 */     super.func_77663_a(stack, world, entity, par4, par5);
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*     */   {
/* 289 */     if (this.voodooPoppet.isMatch(itemstack)) {
/* 290 */       player.func_71008_a(itemstack, func_77626_a(itemstack));
/*     */     }
/*     */     
/* 293 */     return super.func_77659_a(itemstack, world, player);
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack par1ItemStack)
/*     */   {
/* 298 */     return 80;
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack)
/*     */   {
/* 303 */     return EnumAction.bow;
/*     */   }
/*     */   
/*     */   private static class PoppetDamageSource extends EntityDamageSource {
/*     */     private PoppetDamageSource(DamageSource damageType, Entity source) {
/* 308 */       super(source);
/* 309 */       func_76348_h();
/* 310 */       func_82726_p();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int ticks)
/*     */   {
/* 316 */     if (!world.field_72995_K) {
/* 317 */       if (this.voodooPoppet.isMatch(itemstack)) {
/* 318 */         EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, player, itemstack, Integer.valueOf(1));
/* 319 */         if (entity != null) {
/* 320 */           EntityWitchHunter.blackMagicPerformed(player);
/*     */           
/*     */ 
/* 323 */           MovingObjectPosition hitObject = func_77621_a(world, player, true);
/* 324 */           if ((hitObject != null) && (hitObject.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 325 */             Material hitMaterial = world.func_147439_a(hitObject.field_72311_b, hitObject.field_72312_c, hitObject.field_72309_d).func_149688_o();
/* 326 */             Block hitBlock = world.func_147439_a(hitObject.field_72311_b, hitObject.field_72312_c, hitObject.field_72309_d);
/* 327 */             if (hitMaterial == Material.field_151587_i) {
/* 328 */               if ((!voodooProtectionActivated(player, itemstack, entity, true, false)) && (!ItemHunterClothes.isMagicalProtectionActive(player))) {
/* 329 */                 entity.func_70015_d(10);
/*     */               }
/* 331 */               setDamageNBT(player.field_71071_by, itemstack, 1000);
/* 332 */               world.func_72956_a(player, "random.fizz", 0.4F, 2.0F + world.field_73012_v.nextFloat() * 0.4F);
/* 333 */               return;
/*     */             }
/*     */           }
/*     */           
/* 337 */           if (player.func_70093_af()) {
/* 338 */             if ((player.field_71075_bZ.field_75098_d) || (Witchery.Items.GENERIC.itemBoneNeedle.isItemInInventory(player.field_71071_by))) {
/* 339 */               DamageSource damageSource = new PoppetDamageSource(DamageSource.field_76376_m, player, null);
/* 340 */               if (!voodooProtectionActivated(player, itemstack, entity, true, false)) {
/* 341 */                 entity.func_70097_a(damageSource, 0.5F);
/* 342 */                 if (!player.field_71075_bZ.field_75098_d) {
/* 343 */                   Witchery.Items.GENERIC.itemBoneNeedle.consumeItemFromInventory(player.field_71071_by);
/* 344 */                   setDamageNBT(player.field_71071_by, itemstack, getDamageNBT(itemstack) + 10);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/* 349 */             return;
/*     */           }
/* 351 */           if ((!voodooProtectionActivated(player, itemstack, entity, true, false)) && (!ItemHunterClothes.isMagicalProtectionActive(player))) {
/* 352 */             Vec3 look = player.func_70040_Z();
/* 353 */             float scaling = (func_77626_a(itemstack) - ticks) / 20;
/* 354 */             double motionX = look.field_72450_a * 0.9D * scaling;
/* 355 */             double motionY = look.field_72448_b * 0.3D * scaling;
/* 356 */             double motionZ = look.field_72449_c * 0.9D * scaling;
/* 357 */             if ((entity instanceof EntityPlayer)) {
/* 358 */               EntityPlayer targetPlayer = (EntityPlayer)entity;
/*     */               
/* 360 */               Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, motionY, motionZ), targetPlayer);
/*     */             } else {
/* 362 */               entity.field_70159_w = motionX;
/* 363 */               entity.field_70181_x = motionY;
/* 364 */               entity.field_70179_y = motionZ;
/*     */             }
/* 366 */             setDamageNBT(player.field_71071_by, itemstack, getDamageNBT(itemstack) + 10);
/*     */           }
/* 368 */           return;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 373 */       super.func_77615_a(itemstack, world, player, ticks);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean voodooProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, int strength) {
/* 378 */     if ((strength > 1) && ((!(targetEntity instanceof EntityPlayer)) || (!InfusedSpiritEffect.POPPET_ENHANCEMENT.isNearTo((EntityPlayer)targetEntity)))) {
/* 379 */       for (int i = 1; i <= strength; i++) {
/* 380 */         boolean allowLightning = i == strength;
/* 381 */         if (!voodooProtectionActivated(attackingEntity, voodooStack, targetEntity, allowLightning, false)) {
/* 382 */           return false;
/*     */         }
/*     */       }
/*     */     } else {
/* 386 */       return voodooProtectionActivated(attackingEntity, voodooStack, targetEntity, true, false);
/*     */     }
/* 388 */     return true;
/*     */   }
/*     */   
/*     */   public boolean voodooProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, boolean allowLightning, boolean onlyBoosted) {
/* 392 */     int ITEM_DAMAGE = 350;
/* 393 */     if ((targetEntity instanceof EntityPlayer)) {
/* 394 */       EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
/* 395 */       ItemStack defenseStack = findBoundPoppetInWorld(this.antiVoodooPoppet, targetPlayer, 350, false, onlyBoosted);
/* 396 */       if ((defenseStack != null) && (!targetPlayer.field_70170_p.field_72995_K)) {
/* 397 */         if ((attackingEntity != null) && (voodooStack != null)) {
/* 398 */           setDamageNBT(attackingEntity.field_71071_by, voodooStack, getDamageNBT(voodooStack) + 350);
/*     */         }
/*     */         
/* 401 */         if ((attackingEntity != null) && (allowLightning)) {
/* 402 */           EntityLightningBolt lightning = new EntityLightningBolt(attackingEntity.field_70170_p, attackingEntity.field_70165_t, attackingEntity.field_70163_u, attackingEntity.field_70161_v);
/*     */           
/* 404 */           attackingEntity.field_70170_p.func_72942_c(lightning);
/*     */         }
/*     */         
/* 407 */         return true;
/*     */       }
/*     */     }
/* 410 */     return false;
/*     */   }
/*     */   
/*     */   public boolean poppetProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, boolean allowLightning) {
/* 414 */     int ITEM_DAMAGE = 350;
/* 415 */     if ((targetEntity instanceof EntityPlayer)) {
/* 416 */       EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
/* 417 */       ItemStack defenseStack = findBoundPoppetInWorld(this.poppetProtectionPoppet, targetPlayer, 350);
/* 418 */       if ((defenseStack != null) && (!attackingEntity.field_70170_p.field_72995_K)) {
/* 419 */         if (voodooStack != null) {
/* 420 */           setDamageNBT(attackingEntity.field_71071_by, voodooStack, getDamageNBT(voodooStack) + 350);
/*     */         }
/*     */         
/* 423 */         if ((attackingEntity != null) && (allowLightning)) {
/* 424 */           EntityLightningBolt lightning = new EntityLightningBolt(attackingEntity.field_70170_p, attackingEntity.field_70165_t, attackingEntity.field_70163_u, attackingEntity.field_70161_v);
/*     */           
/* 426 */           attackingEntity.field_70170_p.func_72942_c(lightning);
/*     */         }
/*     */         
/* 429 */         return true;
/*     */       }
/*     */     }
/* 432 */     return false;
/*     */   }
/*     */   
/*     */   public void destroyAntiVoodooPoppets(EntityPlayer attackingEntity, EntityLivingBase targetEntity, int poppetsToDestroy) {
/* 436 */     int ITEM_DAMAGE = 1000;
/* 437 */     int MAX = 1000;
/* 438 */     if ((targetEntity instanceof EntityPlayer)) {
/* 439 */       EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
/*     */       
/* 441 */       for (int i = 0; i < poppetsToDestroy; i++) {
/* 442 */         ItemStack defenseStack = findBoundPoppetInWorld(this.antiVoodooPoppet, targetPlayer, 1000);
/* 443 */         if (defenseStack == null) {
/* 444 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static ItemStack findBoundPoppetInWorld(PoppetType poppetType, EntityPlayer player, int foundItemDamage)
/*     */   {
/* 452 */     return findBoundPoppetInWorld(poppetType, player, foundItemDamage, false, false);
/*     */   }
/*     */   
/*     */   public static ItemStack findBoundPoppetInWorld(PoppetType poppetType, EntityPlayer player, int foundItemDamage, boolean allIndices, boolean onlyBoosted)
/*     */   {
/* 457 */     if (ItemHunterClothes.isFullSetWorn(player, false)) {
/* 458 */       return null;
/*     */     }
/*     */     
/* 461 */     int damageValue = poppetType.damageValue;
/*     */     
/* 463 */     ItemStack poppetStack = findBoundPoppetInInventory(Witchery.Items.POPPET, damageValue, player, player.field_71071_by, foundItemDamage, allIndices, onlyBoosted);
/* 464 */     if (poppetStack != null) {
/* 465 */       return poppetStack;
/*     */     }
/*     */     
/* 468 */     if ((!player.field_70170_p.field_72995_K) && (!onlyBoosted)) {
/* 469 */       MinecraftServer server = MinecraftServer.func_71276_C();
/* 470 */       for (WorldServer world : server.field_71305_c) {
/* 471 */         if ((!Config.instance().restrictPoppetShelvesToVanillaAndSpiritDimensions) || (world.field_73011_w.field_76574_g == 0) || (world.field_73011_w.field_76574_g == -1) || (world.field_73011_w.field_76574_g == 1) || (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 476 */           for (Object obj : world.field_147482_g) {
/* 477 */             if ((obj instanceof BlockPoppetShelf.TileEntityPoppetShelf)) {
/* 478 */               poppetStack = findBoundPoppetInInventory(Witchery.Items.POPPET, damageValue, player, (IInventory)obj, foundItemDamage, allIndices, false);
/* 479 */               if (poppetStack != null) {
/* 480 */                 return poppetStack;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 487 */     return null;
/*     */   }
/*     */   
/*     */   private static ItemStack findBoundPoppetInInventory(Item item, int damage, EntityPlayer player, IInventory inventory, int foundItemDamage, boolean allIndices, boolean onlyBoosted) {
/* 491 */     for (int i = 0; i < inventory.func_70302_i_(); i++) {
/* 492 */       ItemStack itemstack = inventory.func_70301_a(i);
/* 493 */       if ((itemstack != null) && (itemstack.func_77973_b() == item) && (itemstack.func_77960_j() == damage) && (Witchery.Items.TAGLOCK_KIT.containsTaglockForEntity(itemstack, player, Integer.valueOf(1))) && ((!allIndices) || (Witchery.Items.TAGLOCK_KIT.isTaglockPresent(itemstack, Integer.valueOf(2)))))
/*     */       {
/*     */ 
/* 496 */         if (onlyBoosted) {
/* 497 */           if (InfusedSpiritEffect.POPPET_ENHANCEMENT.isNearTo(player)) {
/* 498 */             ((PoppetType)Witchery.Items.POPPET.poppetTypes.get(damage)).applyDamageOnUse(inventory, i, itemstack, foundItemDamage);
/* 499 */             return itemstack;
/*     */           }
/* 501 */           return null;
/*     */         }
/*     */         
/* 504 */         ((PoppetType)Witchery.Items.POPPET.poppetTypes.get(damage)).applyDamageOnUse(inventory, i, itemstack, foundItemDamage);
/* 505 */         return itemstack;
/*     */       }
/*     */     }
/*     */     
/* 509 */     return null;
/*     */   }
/*     */   
/*     */   public void addDamageToPoppet(ItemStack sourcePoppet, ItemStack destPoppet) {
/* 513 */     setDamageNBT(null, destPoppet, getDamageNBT(sourcePoppet));
/*     */   }
/*     */   
/*     */   public void cancelEventIfPoppetFound(EntityPlayer player, PoppetType poppetType, LivingHurtEvent event, boolean heal) {
/* 517 */     cancelEventIfPoppetFound(player, poppetType, event, heal, false);
/*     */   }
/*     */   
/*     */   public void cancelEventIfPoppetFound(EntityPlayer player, PoppetType poppetType, LivingHurtEvent event, boolean heal, boolean onlyHandheld) {
/* 521 */     ItemStack stack = findBoundPoppetInWorld(poppetType, player, 1000, false, onlyHandheld);
/* 522 */     if (stack != null) {
/* 523 */       event.setCanceled(true);
/* 524 */       if ((heal) && (player.func_110143_aJ() < 10.0F)) {
/* 525 */         player.func_70606_j(10.0F);
/*     */       }
/*     */       
/* 528 */       SoundEffect.RANDOM_ORB.playAtPlayer(player.field_70170_p, player);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void checkForArmorProtection(EntityPlayer player)
/*     */   {
/* 535 */     for (int i = 0; i < player.field_71071_by.field_70460_b.length; i++) {
/* 536 */       ItemStack armorPiece = player.field_71071_by.field_70460_b[i];
/* 537 */       if ((armorPiece != null) && (armorPiece.func_77984_f())) {
/* 538 */         int itemDamage = armorPiece.func_77960_j();
/* 539 */         int maxDamage = armorPiece.func_77958_k();
/* 540 */         int repairThreshold = (int)(maxDamage * 0.9F);
/* 541 */         if (itemDamage >= repairThreshold) {
/* 542 */           ItemStack protectStack = findBoundPoppetInWorld(Witchery.Items.POPPET.armorPoppet, player, 1000);
/* 543 */           if (protectStack != null) {
/* 544 */             armorPiece.func_77964_b(0);
/* 545 */             player.field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PoppetEventHooks {
/*     */     @SubscribeEvent
/*     */     public void onPlayerInteract(PlayerInteractEvent event) {
/* 555 */       if (!event.entityPlayer.field_70170_p.field_72995_K) {
/* 556 */         EntityPlayer player = event.entityPlayer;
/* 557 */         ItemStack heldItem = player.func_70694_bm();
/* 558 */         if ((heldItem != null) && (heldItem.func_77984_f())) {
/* 559 */           int itemDamage = heldItem.func_77960_j();
/* 560 */           int maxDamage = heldItem.func_77958_k();
/* 561 */           int repairThreshold = (int)(maxDamage * 0.9F);
/* 562 */           if (itemDamage >= repairThreshold) {
/* 563 */             ItemStack protectStack = ItemPoppet.findBoundPoppetInWorld(Witchery.Items.POPPET.toolPoppet, player, 1000);
/* 564 */             if (protectStack != null) {
/* 565 */               heldItem.func_77964_b(0);
/*     */               
/* 567 */               player.field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F)); } } } } } }
/*     */   
/*     */   public static class PoppetType { public final int damageValue;
/*     */     private final String unlocalizedName;
/*     */     private final String localizedName;
/*     */     private final int rarity;
/*     */     @SideOnly(Side.CLIENT)
/*     */     private IIcon icon;
/*     */     private boolean destroyOnUse;
/*     */     
/* 577 */     private static PoppetType register(PoppetType poppetType, ArrayList<PoppetType> poppetTypes) { poppetTypes.add(poppetType);
/* 578 */       return poppetType;
/*     */     }
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
/*     */     private PoppetType(int damageValue, String unlocalizedName, String localizedName)
/*     */     {
/* 592 */       this(damageValue, unlocalizedName, localizedName, 1);
/*     */     }
/*     */     
/*     */     private PoppetType(int damageValue, String unlocalizedName, String localizedName, int rarity) {
/* 596 */       this.damageValue = damageValue;
/* 597 */       this.unlocalizedName = unlocalizedName;
/* 598 */       this.localizedName = localizedName;
/* 599 */       this.rarity = rarity;
/*     */     }
/*     */     
/*     */     public ItemStack createStack() {
/* 603 */       return createStack(1);
/*     */     }
/*     */     
/*     */     public ItemStack createStack(int quantity) {
/* 607 */       return new ItemStack(Witchery.Items.POPPET, quantity, this.damageValue);
/*     */     }
/*     */     
/*     */     public boolean isMatch(ItemStack itemstack) {
/* 611 */       return (itemstack != null) && (itemstack.func_77960_j() == this.damageValue);
/*     */     }
/*     */     
/*     */     private String getUnlocalizedName() {
/* 615 */       if (this.damageValue > 0) {
/* 616 */         return String.format("%s.%s", new Object[] { Witchery.Items.POPPET.func_77658_a(), this.unlocalizedName });
/*     */       }
/* 618 */       return Witchery.Items.POPPET.func_77658_a();
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     private PoppetType registerIcon(IIconRegister iconRegister, ItemPoppet item)
/*     */     {
/* 624 */       if (this.unlocalizedName.equals("poppet")) {
/* 625 */         this.icon = iconRegister.func_94245_a(item.func_111208_A());
/*     */       } else {
/* 627 */         this.icon = iconRegister.func_94245_a(item.func_111208_A() + "." + this.unlocalizedName);
/*     */       }
/* 629 */       return this;
/*     */     }
/*     */     
/*     */     public PoppetType setDestroyOnUse(boolean destroyOnUse) {
/* 633 */       this.destroyOnUse = destroyOnUse;
/* 634 */       return this;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private boolean applyDamageOnUse(IInventory inventory, int i, ItemStack itemstack, int itemDamage)
/*     */     {
/* 642 */       if (this.destroyOnUse) {
/* 643 */         inventory.func_70299_a(i, null);
/* 644 */         itemstack.field_77994_a = 0;
/*     */       } else {
/* 646 */         Witchery.Items.POPPET.setDamageNBT(inventory, itemstack, ItemPoppet.access$1100(Witchery.Items.POPPET, itemstack) + itemDamage);
/*     */       }
/* 648 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemPoppet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */